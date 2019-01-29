head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.11.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualFixedBuyConditionListServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信定時定額買付条件一覧サービスImplTest(WEB3MutualFixedBuyConditionListServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/08/30 趙林鵬(中訊) 新規作成
*/

package webbroker3.mf.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketCalendarRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductCalendarRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.InstitutionImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.SubAccountImpl;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundTradedProductRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3BizDateCalcParameterDef;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.common.define.WEB3ChangeDivDef;
import webbroker3.common.define.WEB3EnableOrderDef;
import webbroker3.common.define.WEB3MFStatusDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3SystemPreferencesNameDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.ExchangeRateRow;
import webbroker3.gentrade.data.FrgnMmfExchangeRateRow;
import webbroker3.gentrade.data.InsiderParams;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.mf.WEB3MutualFixedBuyCommonServiceImpl;
import webbroker3.mf.data.MfFixedBuyingChangeParams;
import webbroker3.mf.data.MfFixedBuyingChangeRow;
import webbroker3.mf.data.MfFixedBuyingCondParams;
import webbroker3.mf.data.MfFixedBuyingCondRow;
import webbroker3.mf.data.MfFixedBuyingDrawAccountRow;
import webbroker3.mf.data.MfSubAssetRow;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionListRequest;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionListResponse;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionUnit;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (投信定時定額買付条件一覧サービスImplTest)<BR>
 * 
 * @@author 趙林鵬(中訊)
 * @@version 1.0 
 */

public class WEB3MutualFixedBuyConditionListServiceImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFixedBuyConditionListServiceImplTest.class);

    public WEB3MutualFixedBuyConditionListServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
            "xblocks.gtl.attributes.systemtimestamp",
            new Timestamp(WEB3DateUtility.getDate(
                "20080708121212",
                WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HMS).getTime()));
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
        ThreadLocalSystemAttributesRegistry.setAttribute(
            "xblocks.gtl.attributes.systemtimestamp",
            GtlUtils.getSystemTimestamp());
    }

    /**
     * 投信定時定額買付条件行オブジェクトの配列の件数 == 0件 の場合
     * 定時定額買付引落口座が取不到得場合
     */
   public void testExecute_C0001()
    {
        final String STR_METHOD_NAME = "testExecute_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.deleteAll();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},new Long(101001010010L));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123));

            TestDBUtility.deleteAll(InsiderParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(123);
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(1234);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD);
            l_systemPreferencesParams.setValue("07");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT);
            l_systemPreferencesParams.setValue("02");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);


            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("0");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            SubAccount l_subAccount1 = new SubAccountImpl(l_subAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "getSubAccount",
                new Class[]{ SubAccountTypeEnum.class },
                l_subAccount1);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(MfFixedBuyingCondRow.TYPE);
            MfFixedBuyingCondParams l_mfFixedBuyingCondParams =
                TestDBUtility.getMfFixedBuyingCondRow();
            l_mfFixedBuyingCondParams.setAccountCode("123");
            l_mfFixedBuyingCondParams.setProductCode("123");
            l_mfFixedBuyingCondParams.setDrawDate(
                WEB3DateUtility.getDate("20080512", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setValidStartDate(
                WEB3DateUtility.getDate("20080511", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setIncreaseBuyAmount(200);
            TestDBUtility.insertWithDel(l_mfFixedBuyingCondParams);

            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
            MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("1");
            l_mfFixedBuyingChangeParams.setProductCode("123");
            l_mfFixedBuyingChangeParams.setValidStartDate(
                WEB3DateUtility.getDate("20080510", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingChangeParams.setChangeDiv(WEB3ChangeDivDef.RELEASE);
            l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(100);
            l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(100);
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("123");
            l_mutualFundProductParams.setProductIssueCode("0");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(32768);
            TestDBUtility.insertWithDel(l_productParams);

            WEB3MutualFixedBuyConditionListServiceImpl l_impl = new WEB3MutualFixedBuyConditionListServiceImpl();
            
            WEB3MutualFixedBuyConditionListRequest l_request = new WEB3MutualFixedBuyConditionListRequest();
            
            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);

            WEB3MutualFixedBuyConditionListResponse l_response =
                (WEB3MutualFixedBuyConditionListResponse)l_impl.execute(l_request);

            assertNull(l_response.conditionList);
            assertNull(l_response.totalList);

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
     * 投信定時定額買付条件行オブジェクトの配列の件数 != 0件 の場合
     * 定時定額買付引落口座が取得できた場合
     */
    public void testExecute_C0002()
    {
        final String STR_METHOD_NAME = "testExecute_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.deleteAll();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},new Long(101001010010L));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123));

            TestDBUtility.deleteAll(InsiderParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(123);
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(1234);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD);
            l_systemPreferencesParams.setValue("07");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT);
            l_systemPreferencesParams.setValue("02");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);


            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("0");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            SubAccount l_subAccount1 = new SubAccountImpl(l_subAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "getSubAccount",
                new Class[]{ SubAccountTypeEnum.class },
                l_subAccount1);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(MfFixedBuyingCondRow.TYPE);
            MfFixedBuyingCondParams l_mfFixedBuyingCondParams =
                TestDBUtility.getMfFixedBuyingCondRow();
            l_mfFixedBuyingCondParams.setAccountCode("123");
            l_mfFixedBuyingCondParams.setProductCode("123");
            l_mfFixedBuyingCondParams.setDrawDate(
                WEB3DateUtility.getDate("20080712", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setValidStartDate(
                WEB3DateUtility.getDate("20080711", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setIncreaseBuyAmount(200);
            TestDBUtility.insertWithDel(l_mfFixedBuyingCondParams);
            l_mfFixedBuyingCondParams =
                TestDBUtility.getMfFixedBuyingCondRow();
            l_mfFixedBuyingCondParams.setAccountCode("123");
            l_mfFixedBuyingCondParams.setProductCode("1234");
            l_mfFixedBuyingCondParams.setDrawDate(
                WEB3DateUtility.getDate("20080812", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setValidStartDate(
                WEB3DateUtility.getDate("20080811", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setIncreaseBuyAmount(200);
            TestDBUtility.insertWithDel(l_mfFixedBuyingCondParams);

            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
            MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("1");
            l_mfFixedBuyingChangeParams.setProductCode("123");
            l_mfFixedBuyingChangeParams.setValidStartDate(
                WEB3DateUtility.getDate("20080710", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingChangeParams.setChangeDiv(WEB3ChangeDivDef.RELEASE);
            l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(100);
            l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(100);
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);
            l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("1");
            l_mfFixedBuyingChangeParams.setProductCode("1234");
            l_mfFixedBuyingChangeParams.setValidStartDate(
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingChangeParams.setChangeDiv(WEB3ChangeDivDef.ADD);
            l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(50);
            l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(100);
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setProductId(123);
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("123");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("test1");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setProductId(124);
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("1234");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("test2");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(32768);
            TestDBUtility.insertWithDel(l_productParams);
            l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123);
            TestDBUtility.insertWithDel(l_productParams);
            l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(124);
            TestDBUtility.insertWithDel(l_productParams);

            WEB3MutualFixedBuyConditionListServiceImpl l_impl =
                new WEB3MutualFixedBuyConditionListServiceImpl();
            
            WEB3MutualFixedBuyConditionListRequest l_request =
                new WEB3MutualFixedBuyConditionListRequest();
            
            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);

            WEB3MutualFixedBuyConditionListResponse l_response =
                (WEB3MutualFixedBuyConditionListResponse)l_impl.execute(l_request);

            assertEquals(l_response.conditionList.length, 1);
            assertNull(l_response.conditionList[0].categoryCode);
            assertNull(l_response.conditionList[0].checkResult);
            int l_intCompare = WEB3DateUtility.compareToDay(
                l_response.conditionList[0].debitAccountYM,
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_intCompare, 0);
            l_intCompare = WEB3DateUtility.compareToDay(
                l_response.conditionList[0].validStartDate,
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_intCompare, 0);
            assertEquals(l_response.conditionList[0].mutualProductCode, "1234");
            assertEquals(l_response.conditionList[0].mutualProductName, "test2");
            assertEquals(l_response.conditionList[0].increaseBuyAmount, "50");
            assertEquals(l_response.conditionList[0].monthlyBuyAmount, "100");

            assertEquals(l_response.totalList.length, 1);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);     
    }

    public void testExecute_C0003()
    {
        final String STR_METHOD_NAME = "testExecute_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.deleteAll();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},new Long(101001010010L));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123));

            TestDBUtility.deleteAll(InsiderParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(123);
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(1234);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD);
            l_systemPreferencesParams.setValue("07");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT);
            l_systemPreferencesParams.setValue("02");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);


            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("0");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            SubAccount l_subAccount1 = new SubAccountImpl(l_subAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "getSubAccount",
                new Class[]{ SubAccountTypeEnum.class },
                l_subAccount1);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(MfFixedBuyingCondRow.TYPE);
            MfFixedBuyingCondParams l_mfFixedBuyingCondParams =
                TestDBUtility.getMfFixedBuyingCondRow();
            l_mfFixedBuyingCondParams.setAccountCode("123");
            l_mfFixedBuyingCondParams.setProductCode("123");
            l_mfFixedBuyingCondParams.setDrawDate(
                WEB3DateUtility.getDate("20080712", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setValidStartDate(
                WEB3DateUtility.getDate("20080711", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setIncreaseBuyAmount(200);
            TestDBUtility.insertWithDel(l_mfFixedBuyingCondParams);
            l_mfFixedBuyingCondParams =
                TestDBUtility.getMfFixedBuyingCondRow();
            l_mfFixedBuyingCondParams.setAccountCode("123");
            l_mfFixedBuyingCondParams.setProductCode("1234");
            l_mfFixedBuyingCondParams.setDrawDate(
                WEB3DateUtility.getDate("20080812", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setValidStartDate(
                WEB3DateUtility.getDate("20080811", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setIncreaseBuyAmount(200);
            TestDBUtility.insertWithDel(l_mfFixedBuyingCondParams);

            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
            MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("3");
            l_mfFixedBuyingChangeParams.setProductCode("123");
            l_mfFixedBuyingChangeParams.setValidStartDate(
                WEB3DateUtility.getDate("20080710", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingChangeParams.setChangeDiv(WEB3ChangeDivDef.RELEASE);
            l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(100);
            l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(100);
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);
            l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("1");
            l_mfFixedBuyingChangeParams.setProductCode("1234");
            l_mfFixedBuyingChangeParams.setValidStartDate(
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingChangeParams.setChangeDiv(WEB3ChangeDivDef.ADD);
            l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(50);
            l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(100);
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setProductId(123);
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("123");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("test1");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setProductId(124);
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("1234");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("test2");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(32768);
            TestDBUtility.insertWithDel(l_productParams);
            l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123);
            TestDBUtility.insertWithDel(l_productParams);
            l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(124);
            TestDBUtility.insertWithDel(l_productParams);

            WEB3MutualFixedBuyConditionListServiceImpl l_impl =
                new WEB3MutualFixedBuyConditionListServiceImpl();
            
            WEB3MutualFixedBuyConditionListRequest l_request =
                new WEB3MutualFixedBuyConditionListRequest();
            
            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);

            WEB3MutualFixedBuyConditionListResponse l_response =
                (WEB3MutualFixedBuyConditionListResponse)l_impl.execute(l_request);

            assertEquals(l_response.conditionList.length, 1);
            assertNull(l_response.conditionList[0].categoryCode);
            assertNull(l_response.conditionList[0].checkResult);
            int l_intCompare = WEB3DateUtility.compareToDay(
                l_response.conditionList[0].debitAccountYM,
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_intCompare, 0);
            l_intCompare = WEB3DateUtility.compareToDay(
                l_response.conditionList[0].validStartDate,
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_intCompare, 0);
            assertEquals(l_response.conditionList[0].mutualProductCode, "1234");
            assertEquals(l_response.conditionList[0].mutualProductName, "test2");
            assertEquals(l_response.conditionList[0].increaseBuyAmount, "50");
            assertEquals(l_response.conditionList[0].monthlyBuyAmount, "100");

            assertEquals(l_response.totalList.length, 1);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);     
    }

    public void testExecute_C0004()
    {
        final String STR_METHOD_NAME = "testExecute_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.deleteAll();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},new Long(101001010010L));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123));

            TestDBUtility.deleteAll(InsiderParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(123);
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(1234);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD);
            l_systemPreferencesParams.setValue("07");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT);
            l_systemPreferencesParams.setValue("02");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);


            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("0");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            SubAccount l_subAccount1 = new SubAccountImpl(l_subAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "getSubAccount",
                new Class[]{ SubAccountTypeEnum.class },
                l_subAccount1);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(MfFixedBuyingCondRow.TYPE);
            MfFixedBuyingCondParams l_mfFixedBuyingCondParams =
                TestDBUtility.getMfFixedBuyingCondRow();
            l_mfFixedBuyingCondParams.setAccountCode("123");
            l_mfFixedBuyingCondParams.setProductCode("123");
            l_mfFixedBuyingCondParams.setDrawDate(
                WEB3DateUtility.getDate("20080712", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setValidStartDate(
                WEB3DateUtility.getDate("20080711", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setIncreaseBuyAmount(200);
            TestDBUtility.insertWithDel(l_mfFixedBuyingCondParams);
            l_mfFixedBuyingCondParams =
                TestDBUtility.getMfFixedBuyingCondRow();
            l_mfFixedBuyingCondParams.setAccountCode("123");
            l_mfFixedBuyingCondParams.setProductCode("1234");
            l_mfFixedBuyingCondParams.setDrawDate(
                WEB3DateUtility.getDate("20080812", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setValidStartDate(
                WEB3DateUtility.getDate("20080811", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setIncreaseBuyAmount(200);
            TestDBUtility.insertWithDel(l_mfFixedBuyingCondParams);

            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
            MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("3");
            l_mfFixedBuyingChangeParams.setProductCode("123");
            l_mfFixedBuyingChangeParams.setValidStartDate(
                WEB3DateUtility.getDate("20080710", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingChangeParams.setChangeDiv(WEB3ChangeDivDef.TEMP_STOP);
            l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(100);
            l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(100);
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);
            l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("2");
            l_mfFixedBuyingChangeParams.setProductCode("1234");
            l_mfFixedBuyingChangeParams.setValidStartDate(
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingChangeParams.setChangeDiv(WEB3ChangeDivDef.CHANGE);
            l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(50);
            l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(100);
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setProductId(123);
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("123");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("test1");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setProductId(124);
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("1234");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("test2");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(32768);
            TestDBUtility.insertWithDel(l_productParams);
            l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123);
            TestDBUtility.insertWithDel(l_productParams);
            l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(124);
            TestDBUtility.insertWithDel(l_productParams);

            WEB3MutualFixedBuyConditionListServiceImpl l_impl =
                new WEB3MutualFixedBuyConditionListServiceImpl();
            
            WEB3MutualFixedBuyConditionListRequest l_request =
                new WEB3MutualFixedBuyConditionListRequest();
            
            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);

            WEB3MutualFixedBuyConditionListResponse l_response =
                (WEB3MutualFixedBuyConditionListResponse)l_impl.execute(l_request);

            assertEquals(l_response.conditionList.length, 1);
            assertNull(l_response.conditionList[0].categoryCode);
            assertNull(l_response.conditionList[0].checkResult);
            int l_intCompare = WEB3DateUtility.compareToDay(
                l_response.conditionList[0].debitAccountYM,
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_intCompare, 0);
            l_intCompare = WEB3DateUtility.compareToDay(
                l_response.conditionList[0].validStartDate,
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_intCompare, 0);
            assertEquals(l_response.conditionList[0].mutualProductCode, "1234");
            assertEquals(l_response.conditionList[0].mutualProductName, "test2");
            assertEquals(l_response.conditionList[0].increaseBuyAmount, "50");
            assertEquals(l_response.conditionList[0].monthlyBuyAmount, "100");

            assertEquals(l_response.totalList.length, 1);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);     
    }

    public void testExecute_C0005()
    {
        final String STR_METHOD_NAME = "testExecute_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.deleteAll();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},new Long(101001010010L));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123));

            TestDBUtility.deleteAll(InsiderParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(123);
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(1234);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD);
            l_systemPreferencesParams.setValue("07");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT);
            l_systemPreferencesParams.setValue("02");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);


            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("0");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            SubAccount l_subAccount1 = new SubAccountImpl(l_subAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "getSubAccount",
                new Class[]{ SubAccountTypeEnum.class },
                l_subAccount1);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(MfFixedBuyingCondRow.TYPE);
            MfFixedBuyingCondParams l_mfFixedBuyingCondParams =
                TestDBUtility.getMfFixedBuyingCondRow();
            l_mfFixedBuyingCondParams.setAccountCode("123");
            l_mfFixedBuyingCondParams.setProductCode("1234");
            l_mfFixedBuyingCondParams.setDrawDate(
                WEB3DateUtility.getDate("20080812", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setValidStartDate(
                WEB3DateUtility.getDate("20080811", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setIncreaseBuyAmount(200);
            TestDBUtility.insertWithDel(l_mfFixedBuyingCondParams);

            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
            MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("3");
            l_mfFixedBuyingChangeParams.setProductCode("123");
            l_mfFixedBuyingChangeParams.setValidStartDate(
                WEB3DateUtility.getDate("20080710", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingChangeParams.setChangeDiv(WEB3ChangeDivDef.TEMP_STOP);
            l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(100);
            l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(100);
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);
            l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("2");
            l_mfFixedBuyingChangeParams.setProductCode("1234");
            l_mfFixedBuyingChangeParams.setValidStartDate(
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingChangeParams.setChangeDiv(WEB3ChangeDivDef.CHANGE);
            l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(50);
            l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(100);
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setProductId(123);
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("123");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("test1");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setProductId(124);
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("1234");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("test2");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(32768);
            TestDBUtility.insertWithDel(l_productParams);
            l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123);
            TestDBUtility.insertWithDel(l_productParams);
            l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(124);
            TestDBUtility.insertWithDel(l_productParams);

            WEB3MutualFixedBuyConditionListServiceImpl l_impl =
                new WEB3MutualFixedBuyConditionListServiceImpl();
            
            WEB3MutualFixedBuyConditionListRequest l_request =
                new WEB3MutualFixedBuyConditionListRequest();
            
            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);

            WEB3MutualFixedBuyConditionListResponse l_response =
                (WEB3MutualFixedBuyConditionListResponse)l_impl.execute(l_request);

            assertEquals(l_response.conditionList.length, 1);
            assertNull(l_response.conditionList[0].categoryCode);
            assertNull(l_response.conditionList[0].checkResult);
            int l_intCompare = WEB3DateUtility.compareToDay(
                l_response.conditionList[0].debitAccountYM,
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_intCompare, 0);
            l_intCompare = WEB3DateUtility.compareToDay(
                l_response.conditionList[0].validStartDate,
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_intCompare, 0);
            assertEquals(l_response.conditionList[0].mutualProductCode, "1234");
            assertEquals(l_response.conditionList[0].mutualProductName, "test2");
            assertEquals(l_response.conditionList[0].increaseBuyAmount, "50");
            assertEquals(l_response.conditionList[0].monthlyBuyAmount, "100");

            assertEquals(l_response.totalList.length, 1);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);     
    }

    public void testExecute_C0006()
    {
        final String STR_METHOD_NAME = "testExecute_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.deleteAll();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},new Long(101001010010L));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123));

            TestDBUtility.deleteAll(InsiderParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(123);
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(1234);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD);
            l_systemPreferencesParams.setValue("07");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT);
            l_systemPreferencesParams.setValue("02");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);


            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("0");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            SubAccount l_subAccount1 = new SubAccountImpl(l_subAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "getSubAccount",
                new Class[]{ SubAccountTypeEnum.class },
                l_subAccount1);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
            MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("3");
            l_mfFixedBuyingChangeParams.setProductCode("123");
            l_mfFixedBuyingChangeParams.setValidStartDate(
                WEB3DateUtility.getDate("20080710", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingChangeParams.setChangeDiv(WEB3ChangeDivDef.TEMP_STOP);
            l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(100);
            l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(100);
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);
            l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("2");
            l_mfFixedBuyingChangeParams.setProductCode("1234");
            l_mfFixedBuyingChangeParams.setValidStartDate(
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingChangeParams.setChangeDiv(WEB3ChangeDivDef.CHANGE);
            l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(50);
            l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(100);
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setProductId(123);
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("123");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("test1");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setProductId(124);
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("1234");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("test2");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(32768);
            TestDBUtility.insertWithDel(l_productParams);
            l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123);
            TestDBUtility.insertWithDel(l_productParams);
            l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(124);
            TestDBUtility.insertWithDel(l_productParams);

            WEB3MutualFixedBuyConditionListServiceImpl l_impl =
                new WEB3MutualFixedBuyConditionListServiceImpl();
            
            WEB3MutualFixedBuyConditionListRequest l_request =
                new WEB3MutualFixedBuyConditionListRequest();
            
            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);

            WEB3MutualFixedBuyConditionListResponse l_response =
                (WEB3MutualFixedBuyConditionListResponse)l_impl.execute(l_request);

            assertEquals(l_response.conditionList.length, 1);
            assertNull(l_response.conditionList[0].categoryCode);
            assertNull(l_response.conditionList[0].checkResult);
            int l_intCompare = WEB3DateUtility.compareToDay(
                l_response.conditionList[0].debitAccountYM,
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_intCompare, 0);
            l_intCompare = WEB3DateUtility.compareToDay(
                l_response.conditionList[0].validStartDate,
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_intCompare, 0);
            assertEquals(l_response.conditionList[0].mutualProductCode, "1234");
            assertEquals(l_response.conditionList[0].mutualProductName, "test2");
            assertEquals(l_response.conditionList[0].increaseBuyAmount, "50");
            assertEquals(l_response.conditionList[0].monthlyBuyAmount, "100");

            assertEquals(l_response.totalList.length, 1);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);     
    }

    public void testExecute_C0007()
    {
        final String STR_METHOD_NAME = "testExecute_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.deleteAll();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},new Long(101001010010L));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123));

            TestDBUtility.deleteAll(InsiderParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(123);
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(1234);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD);
            l_systemPreferencesParams.setValue("07");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT);
            l_systemPreferencesParams.setValue("02");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);


            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("0");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            SubAccount l_subAccount1 = new SubAccountImpl(l_subAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "getSubAccount",
                new Class[]{ SubAccountTypeEnum.class },
                l_subAccount1);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(MfFixedBuyingCondRow.TYPE);
            MfFixedBuyingCondParams l_mfFixedBuyingCondParams =
                TestDBUtility.getMfFixedBuyingCondRow();
            l_mfFixedBuyingCondParams.setAccountCode("123");
            l_mfFixedBuyingCondParams.setProductCode("1234");
            l_mfFixedBuyingCondParams.setDrawDate(
                WEB3DateUtility.getDate("20080912", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setValidStartDate(
                WEB3DateUtility.getDate("20080911", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setIncreaseBuyAmount(200);
            l_mfFixedBuyingCondParams.setMonthlyBuyAmount(225);
            TestDBUtility.insertWithDel(l_mfFixedBuyingCondParams);

            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
            MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("3");
            l_mfFixedBuyingChangeParams.setProductCode("123");
            l_mfFixedBuyingChangeParams.setValidStartDate(
                WEB3DateUtility.getDate("20080710", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingChangeParams.setChangeDiv(WEB3ChangeDivDef.TEMP_STOP);
            l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(100);
            l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(100);
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);
            l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("2");
            l_mfFixedBuyingChangeParams.setProductCode("1234");
            l_mfFixedBuyingChangeParams.setValidStartDate(
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingChangeParams.setChangeDiv(WEB3ChangeDivDef.CHANGE);
            l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(50);
            l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(100);
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setProductId(123);
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("123");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("test1");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setProductId(124);
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("1234");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("test2");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(32768);
            TestDBUtility.insertWithDel(l_productParams);
            l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123);
            TestDBUtility.insertWithDel(l_productParams);
            l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(124);
            TestDBUtility.insertWithDel(l_productParams);

            WEB3MutualFixedBuyConditionListServiceImpl l_impl =
                new WEB3MutualFixedBuyConditionListServiceImpl();
            
            WEB3MutualFixedBuyConditionListRequest l_request =
                new WEB3MutualFixedBuyConditionListRequest();
            
            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);

            WEB3MutualFixedBuyConditionListResponse l_response =
                (WEB3MutualFixedBuyConditionListResponse)l_impl.execute(l_request);

            assertEquals(l_response.conditionList.length, 2);
            assertNull(l_response.conditionList[0].categoryCode);
            assertNull(l_response.conditionList[0].checkResult);
            int l_intCompare = WEB3DateUtility.compareToDay(
                l_response.conditionList[0].debitAccountYM,
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_intCompare, 0);
            l_intCompare = WEB3DateUtility.compareToDay(
                l_response.conditionList[0].validStartDate,
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_intCompare, 0);
            assertEquals(l_response.conditionList[0].mutualProductCode, "1234");
            assertEquals(l_response.conditionList[0].mutualProductName, "test2");
            assertEquals(l_response.conditionList[0].increaseBuyAmount, "50");
            assertEquals(l_response.conditionList[0].monthlyBuyAmount, "100");

            assertNull(l_response.conditionList[1].categoryCode);
            assertNull(l_response.conditionList[1].checkResult);
            l_intCompare = WEB3DateUtility.compareToDay(
                l_response.conditionList[1].debitAccountYM,
                WEB3DateUtility.getDate("20080912", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_intCompare, 0);
            l_intCompare = WEB3DateUtility.compareToDay(
                l_response.conditionList[1].validStartDate,
                WEB3DateUtility.getDate("20080911", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_intCompare, 0);
            assertEquals(l_response.conditionList[1].mutualProductCode, "1234");
            assertEquals(l_response.conditionList[1].mutualProductName, "test2");
            assertEquals(l_response.conditionList[1].increaseBuyAmount, "200");
            assertEquals(l_response.conditionList[1].monthlyBuyAmount, "225");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);     
    }

    public void testExecute_C0008()
    {
        final String STR_METHOD_NAME = "testExecute_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.deleteAll();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},new Long(101001010010L));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123));

            TestDBUtility.deleteAll(InsiderParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(123);
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(1234);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD);
            l_systemPreferencesParams.setValue("07");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT);
            l_systemPreferencesParams.setValue("02");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);


            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("0");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            SubAccount l_subAccount1 = new SubAccountImpl(l_subAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "getSubAccount",
                new Class[]{ SubAccountTypeEnum.class },
                l_subAccount1);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_mainAccountParams);


            TestDBUtility.deleteAll(MfFixedBuyingCondRow.TYPE);
            MfFixedBuyingCondParams l_mfFixedBuyingCondParams =
                TestDBUtility.getMfFixedBuyingCondRow();
            l_mfFixedBuyingCondParams.setAccountCode("123");
            l_mfFixedBuyingCondParams.setProductCode("1234");
            l_mfFixedBuyingCondParams.setDrawDate(
                WEB3DateUtility.getDate("20081012", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setValidStartDate(
                WEB3DateUtility.getDate("20081011", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setIncreaseBuyAmount(235);
            l_mfFixedBuyingCondParams.setMonthlyBuyAmount(125);
            TestDBUtility.insertWithDel(l_mfFixedBuyingCondParams);

            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
            MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("3");
            l_mfFixedBuyingChangeParams.setProductCode("123");
            l_mfFixedBuyingChangeParams.setValidStartDate(
                WEB3DateUtility.getDate("20080710", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingChangeParams.setChangeDiv(WEB3ChangeDivDef.TEMP_STOP);
            l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(100);
            l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(100);
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);
            l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("2");
            l_mfFixedBuyingChangeParams.setProductCode("1234");
            l_mfFixedBuyingChangeParams.setValidStartDate(
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingChangeParams.setChangeDiv(WEB3ChangeDivDef.CHANGE);
            l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(50);
            l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(100);
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);
            l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("2");
            l_mfFixedBuyingChangeParams.setProductCode("12345");
            l_mfFixedBuyingChangeParams.setValidStartDate(
                WEB3DateUtility.getDate("20080910", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingChangeParams.setChangeDiv(WEB3ChangeDivDef.CHANGE);
            l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(250);
            l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(300);
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setProductId(123);
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("123");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("test1");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setProductId(124);
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("1234");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("test2");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setProductId(125);
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("12345");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("test3");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(32768);
            TestDBUtility.insertWithDel(l_productParams);
            l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123);
            TestDBUtility.insertWithDel(l_productParams);
            l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(124);
            TestDBUtility.insertWithDel(l_productParams);
            l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(125);
            TestDBUtility.insertWithDel(l_productParams);

            WEB3MutualFixedBuyConditionListServiceImpl l_impl =
                new WEB3MutualFixedBuyConditionListServiceImpl();
            
            WEB3MutualFixedBuyConditionListRequest l_request =
                new WEB3MutualFixedBuyConditionListRequest();
            
            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);

            WEB3MutualFixedBuyConditionListResponse l_response =
                (WEB3MutualFixedBuyConditionListResponse)l_impl.execute(l_request);

            assertEquals(l_response.conditionList.length, 5);
            assertNull(l_response.conditionList[0].categoryCode);
            assertNull(l_response.conditionList[0].checkResult);
            int l_intCompare = WEB3DateUtility.compareToDay(
                l_response.conditionList[0].debitAccountYM,
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_intCompare, 0);
            l_intCompare = WEB3DateUtility.compareToDay(
                l_response.conditionList[0].validStartDate,
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_intCompare, 0);
            assertEquals(l_response.conditionList[0].mutualProductCode, "1234");
            assertEquals(l_response.conditionList[0].mutualProductName, "test2");
            assertEquals(l_response.conditionList[0].increaseBuyAmount, "50");
            assertEquals(l_response.conditionList[0].monthlyBuyAmount, "100");

            assertNull(l_response.conditionList[1].categoryCode);
            assertNull(l_response.conditionList[1].checkResult);
            l_intCompare = WEB3DateUtility.compareToDay(
                l_response.conditionList[1].debitAccountYM,
                WEB3DateUtility.getDate("20080910", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_intCompare, 0);
            l_intCompare = WEB3DateUtility.compareToDay(
                l_response.conditionList[1].validStartDate,
                WEB3DateUtility.getDate("20080810", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_intCompare, 0);
            assertEquals(l_response.conditionList[1].mutualProductCode, "1234");
            assertEquals(l_response.conditionList[1].mutualProductName, "test2");
            assertEquals(l_response.conditionList[1].increaseBuyAmount, "50");
            assertEquals(l_response.conditionList[1].monthlyBuyAmount, "100");

            assertNull(l_response.conditionList[2].categoryCode);
            assertNull(l_response.conditionList[2].checkResult);
            l_intCompare = WEB3DateUtility.compareToDay(
                l_response.conditionList[2].debitAccountYM,
                WEB3DateUtility.getDate("20080910", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_intCompare, 0);
            l_intCompare = WEB3DateUtility.compareToDay(
                l_response.conditionList[2].validStartDate,
                WEB3DateUtility.getDate("20080910", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_intCompare, 0);
            assertEquals(l_response.conditionList[2].mutualProductCode, "12345");
            assertEquals(l_response.conditionList[2].mutualProductName, "test3");
            assertEquals(l_response.conditionList[2].increaseBuyAmount, "250");
            assertEquals(l_response.conditionList[2].monthlyBuyAmount, "300");


            assertNull(l_response.conditionList[3].categoryCode);
            assertNull(l_response.conditionList[3].checkResult);
            l_intCompare = WEB3DateUtility.compareToDay(
                l_response.conditionList[3].debitAccountYM,
                WEB3DateUtility.getDate("20080910", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_intCompare, 0);
            l_intCompare = WEB3DateUtility.compareToDay(
                l_response.conditionList[3].validStartDate,
                WEB3DateUtility.getDate("20080910", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_intCompare, 0);
            assertEquals(l_response.conditionList[3].mutualProductCode, "12345");
            assertEquals(l_response.conditionList[3].mutualProductName, "test3");
            assertEquals(l_response.conditionList[3].increaseBuyAmount, "250");
            assertEquals(l_response.conditionList[3].monthlyBuyAmount, "300");


            assertNull(l_response.conditionList[4].categoryCode);
            assertNull(l_response.conditionList[4].checkResult);
            l_intCompare = WEB3DateUtility.compareToDay(
                l_response.conditionList[4].debitAccountYM,
                WEB3DateUtility.getDate("20081012", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_intCompare, 0);
            l_intCompare = WEB3DateUtility.compareToDay(
                l_response.conditionList[4].validStartDate,
                WEB3DateUtility.getDate("20081011", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_intCompare, 0);
            assertEquals(l_response.conditionList[4].mutualProductCode, "1234");
            assertEquals(l_response.conditionList[4].mutualProductName, "test2");
            assertEquals(l_response.conditionList[4].increaseBuyAmount, "235");
            assertEquals(l_response.conditionList[4].monthlyBuyAmount, "125");
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
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
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
            TestDBUtility.deleteAll(MfFixedBuyingDrawAccountRow.TYPE);
            TestDBUtility.deleteAll(MfFixedBuyingCondRow.TYPE);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info("*******************deleteAll***************** !!");
    }

    public void testMergeMutualFixedBuyConditionMonth_C0002()
    {
        final String STR_METHOD_NAME = "testMergeMutualFixedBuyCondition_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionListServiceImpl l_impl = new WEB3MutualFixedBuyConditionListServiceImpl();

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getAccountId",
            new Class[] {},
            new Long(123));
        
        WEB3MutualFixedBuyCommonServiceImpl l_mutualFixedBuyCommonServiceImpl = 
            new WEB3MutualFixedBuyCommonServiceImpl();
        
        try
        {
            TestDBUtility.deleteAll(InsiderParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            Institution l_institution = new InstitutionImpl(l_institutionParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(123);
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(1234);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            SubAccount l_subAccount1 = new SubAccountImpl(l_subAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "getSubAccount",
                new Class[]{ SubAccountTypeEnum.class },
                l_subAccount1);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(MfFixedBuyingCondRow.TYPE);
            MfFixedBuyingCondParams l_mfFixedBuyingCondParams =
                TestDBUtility.getMfFixedBuyingCondRow();
            l_mfFixedBuyingCondParams.setAccountCode("123");
            TestDBUtility.insertWithDel(l_mfFixedBuyingCondParams);

            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
            MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("1");
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);

            List l_lisFixedBuyConditionChangeList = null;

            SubAccount l_subAccount = l_impl.getSubAccount();
            String l_strBranchCode =
                l_subAccount.getMainAccount().getBranch().getBranchCode();
            String l_strAccountCode =
                l_subAccount.getMainAccount().getAccountCode();
            String l_strInstitutionCode =
                l_subAccount.getInstitution().getInstitutionCode();

            List l_lisFixedBuyConditionList =
                l_mutualFixedBuyCommonServiceImpl.getFixedBuyConditionList(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode,
                    null, null);

            l_impl.mergeMutualFixedBuyConditionMonth(
                l_institution,
                l_lisFixedBuyConditionList,
                l_lisFixedBuyConditionChangeList);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
            assertEquals("パラメータ値不正。", l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testMergeMutualFixedBuyConditionMonth_C0003()
    {
        final String STR_METHOD_NAME = "testMergeMutualFixedBuyCondition_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionListServiceImpl l_impl = new WEB3MutualFixedBuyConditionListServiceImpl();

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getAccountId",
            new Class[] {},
            new Long(123));
        
        WEB3MutualFixedBuyCommonServiceImpl l_mutualFixedBuyCommonServiceImpl = 
            new WEB3MutualFixedBuyCommonServiceImpl();
        
        try
        {
            TestDBUtility.deleteAll(InsiderParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            Institution l_institution = new InstitutionImpl(l_institutionParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(123);
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(1234);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            SubAccount l_subAccount1 = new SubAccountImpl(l_subAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "getSubAccount",
                new Class[]{ SubAccountTypeEnum.class },
                l_subAccount1);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(MfFixedBuyingCondRow.TYPE);
            MfFixedBuyingCondParams l_mfFixedBuyingCondParams =
                TestDBUtility.getMfFixedBuyingCondRow();
            l_mfFixedBuyingCondParams.setAccountCode("123");
            TestDBUtility.insertWithDel(l_mfFixedBuyingCondParams);

            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
            MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("1");
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);

            

            SubAccount l_subAccount = l_impl.getSubAccount();
            String l_strBranchCode =
                l_subAccount.getMainAccount().getBranch().getBranchCode();
            String l_strAccountCode =
                l_subAccount.getMainAccount().getAccountCode();
            String l_strInstitutionCode =
                l_subAccount.getInstitution().getInstitutionCode();

            String l_strQueryString = " status in (?, ?) ";
            Object[] l_objQueryValues = new Object[]{
                WEB3MFStatusDef.SONAR_NOT_SEND,
                WEB3MFStatusDef.SONAR_SENDED};
            List l_lisFixedBuyConditionChangeList =
                l_mutualFixedBuyCommonServiceImpl.getFixedBuyConditionChangeList(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode,
                    l_strQueryString,
                    l_objQueryValues);

            List l_lisFixedBuyConditionList = null;

            l_impl.mergeMutualFixedBuyConditionMonth(
                l_institution,
                l_lisFixedBuyConditionList,
                l_lisFixedBuyConditionChangeList);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
            assertEquals("パラメータ値不正。", l_ex.getErrorMessage());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testMergeMutualFixedBuyConditionMonth_C0004()
    {
        final String STR_METHOD_NAME = "testMergeMutualFixedBuyCondition_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionListServiceImpl l_impl = new WEB3MutualFixedBuyConditionListServiceImpl();

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123));
        
        WEB3MutualFixedBuyCommonServiceImpl l_mutualFixedBuyCommonServiceImpl = 
            new WEB3MutualFixedBuyCommonServiceImpl();
        
        try
        {
            TestDBUtility.deleteAll(InsiderParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            Institution l_institution = new InstitutionImpl(l_institutionParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(123);
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(1234);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            

            SubAccount l_subAccount1 = new SubAccountImpl(l_subAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "getSubAccount",
                new Class[]{ SubAccountTypeEnum.class },
                l_subAccount1);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(MfFixedBuyingCondRow.TYPE);
            MfFixedBuyingCondParams l_mfFixedBuyingCondParams =
                TestDBUtility.getMfFixedBuyingCondRow();
            l_mfFixedBuyingCondParams.setAccountCode("123");
            l_mfFixedBuyingCondParams.setProductCode("123");
            l_mfFixedBuyingCondParams.setDrawDate(
                WEB3DateUtility.getDate("20081012", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setValidStartDate(
                WEB3DateUtility.getDate("20081011", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setIncreaseBuyAmount(200);
            TestDBUtility.insertWithDel(l_mfFixedBuyingCondParams);

            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
            MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("1");
            l_mfFixedBuyingChangeParams.setProductCode("123");
            l_mfFixedBuyingChangeParams.setValidStartDate(
                WEB3DateUtility.getDate("20081010", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingChangeParams.setChangeDiv(WEB3ChangeDivDef.ADD);
            l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(100);
            l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(100);
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("123");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("test");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(32768);
            TestDBUtility.insertWithDel(l_productParams);

            SubAccount l_subAccount = l_impl.getSubAccount();
            String l_strBranchCode =
                l_subAccount.getMainAccount().getBranch().getBranchCode();
            String l_strAccountCode =
                l_subAccount.getMainAccount().getAccountCode();
            String l_strInstitutionCode =
                l_subAccount.getInstitution().getInstitutionCode();

            String l_strQueryString = " status in (?, ?) ";
            Object[] l_objQueryValues = new Object[]{
                WEB3MFStatusDef.SONAR_NOT_SEND,
                WEB3MFStatusDef.SONAR_SENDED};
            List l_lisFixedBuyConditionChangeList =
                l_mutualFixedBuyCommonServiceImpl.getFixedBuyConditionChangeList(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode,
                    l_strQueryString,
                    l_objQueryValues);

            List l_lisFixedBuyConditionList =
                l_mutualFixedBuyCommonServiceImpl.getFixedBuyConditionList(
                    l_strInstitutionCode, 
                    l_strBranchCode, 
                    l_strAccountCode, 
                    null, null);

            WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits =
                l_impl.mergeMutualFixedBuyConditionMonth(
                    l_institution,
                    l_lisFixedBuyConditionList,
                    l_lisFixedBuyConditionChangeList);

            assertEquals(l_mutualFixedBuyConditionUnits.length, 1);
            assertEquals(l_mutualFixedBuyConditionUnits[0].monthlyBuyAmount, "100");
            assertEquals(l_mutualFixedBuyConditionUnits[0].increaseBuyAmount, "100");
            int l_intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].validStartDate,
                WEB3DateUtility.getDate("20081010", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_intCompreResult, 0);
            l_intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].debitAccountYM,
                WEB3DateUtility.getDate("20081010", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_intCompreResult, 0);
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductCode, "123");
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductName, "test");
            assertNull(l_mutualFixedBuyConditionUnits[0].updateDate);
            assertNull(l_mutualFixedBuyConditionUnits[0].checkResult);
            assertFalse(l_mutualFixedBuyConditionUnits[0].suspensionFlag);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testMergeMutualFixedBuyConditionMonth_C0005()
    {
        final String STR_METHOD_NAME = "testMergeMutualFixedBuyCondition_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionListServiceImpl l_impl = new WEB3MutualFixedBuyConditionListServiceImpl();

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123));
        
        WEB3MutualFixedBuyCommonServiceImpl l_mutualFixedBuyCommonServiceImpl = 
            new WEB3MutualFixedBuyCommonServiceImpl();
        
        try
        {
            TestDBUtility.deleteAll(InsiderParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            Institution l_institution = new InstitutionImpl(l_institutionParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(123);
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(1234);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            

            SubAccount l_subAccount1 = new SubAccountImpl(l_subAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "getSubAccount",
                new Class[]{ SubAccountTypeEnum.class },
                l_subAccount1);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(MfFixedBuyingCondRow.TYPE);
            MfFixedBuyingCondParams l_mfFixedBuyingCondParams =
                TestDBUtility.getMfFixedBuyingCondRow();
            l_mfFixedBuyingCondParams.setAccountCode("123");
            l_mfFixedBuyingCondParams.setProductCode("123");
            l_mfFixedBuyingCondParams.setDrawDate(
                WEB3DateUtility.getDate("20081012", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setValidStartDate(
                WEB3DateUtility.getDate("20081011", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setIncreaseBuyAmount(200);
            TestDBUtility.insertWithDel(l_mfFixedBuyingCondParams);

            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
            MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("1");
            l_mfFixedBuyingChangeParams.setProductCode("123");
            l_mfFixedBuyingChangeParams.setValidStartDate(
                WEB3DateUtility.getDate("20081010", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingChangeParams.setChangeDiv(WEB3ChangeDivDef.CHANGE);
            l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(100);
            l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(100);
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("123");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("test");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(32768);
            TestDBUtility.insertWithDel(l_productParams);

            SubAccount l_subAccount = l_impl.getSubAccount();
            String l_strBranchCode =
                l_subAccount.getMainAccount().getBranch().getBranchCode();
            String l_strAccountCode =
                l_subAccount.getMainAccount().getAccountCode();
            String l_strInstitutionCode =
                l_subAccount.getInstitution().getInstitutionCode();

            String l_strQueryString = " status in (?, ?) ";
            Object[] l_objQueryValues = new Object[]{
                WEB3MFStatusDef.SONAR_NOT_SEND,
                WEB3MFStatusDef.SONAR_SENDED};
            List l_lisFixedBuyConditionChangeList =
                l_mutualFixedBuyCommonServiceImpl.getFixedBuyConditionChangeList(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode,
                    l_strQueryString,
                    l_objQueryValues);

            List l_lisFixedBuyConditionList =
                l_mutualFixedBuyCommonServiceImpl.getFixedBuyConditionList(
                    l_strInstitutionCode, 
                    l_strBranchCode, 
                    l_strAccountCode, 
                    null, null);

            WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits =
                l_impl.mergeMutualFixedBuyConditionMonth(
                    l_institution,
                    l_lisFixedBuyConditionList,
                    l_lisFixedBuyConditionChangeList);

            assertEquals(l_mutualFixedBuyConditionUnits.length, 1);
            assertEquals(l_mutualFixedBuyConditionUnits[0].monthlyBuyAmount, "100");
            assertEquals(l_mutualFixedBuyConditionUnits[0].increaseBuyAmount, "100");
            int l_intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].validStartDate,
                WEB3DateUtility.getDate("20081010", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_intCompreResult, 0);
            l_intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].debitAccountYM,
                WEB3DateUtility.getDate("20081010", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_intCompreResult, 0);
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductCode, "123");
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductName, "test");
            assertNull(l_mutualFixedBuyConditionUnits[0].updateDate);
            assertNull(l_mutualFixedBuyConditionUnits[0].checkResult);
            assertFalse(l_mutualFixedBuyConditionUnits[0].suspensionFlag);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testMergeMutualFixedBuyConditionMonth_C0006()
    {
        final String STR_METHOD_NAME = "testMergeMutualFixedBuyCondition_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionListServiceImpl l_impl = new WEB3MutualFixedBuyConditionListServiceImpl();

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123));
        
        WEB3MutualFixedBuyCommonServiceImpl l_mutualFixedBuyCommonServiceImpl = 
            new WEB3MutualFixedBuyCommonServiceImpl();
        
        try
        {
            TestDBUtility.deleteAll(InsiderParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            Institution l_institution = new InstitutionImpl(l_institutionParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(123);
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(1234);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            

            SubAccount l_subAccount1 = new SubAccountImpl(l_subAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "getSubAccount",
                new Class[]{ SubAccountTypeEnum.class },
                l_subAccount1);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(MfFixedBuyingCondRow.TYPE);
            MfFixedBuyingCondParams l_mfFixedBuyingCondParams =
                TestDBUtility.getMfFixedBuyingCondRow();
            l_mfFixedBuyingCondParams.setAccountCode("123");
            l_mfFixedBuyingCondParams.setProductCode("123");
            l_mfFixedBuyingCondParams.setDrawDate(
                WEB3DateUtility.getDate("20081012", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setValidStartDate(
                WEB3DateUtility.getDate("20081011", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setIncreaseBuyAmount(200);
            TestDBUtility.insertWithDel(l_mfFixedBuyingCondParams);

            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
            MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("1");
            l_mfFixedBuyingChangeParams.setProductCode("123");
            l_mfFixedBuyingChangeParams.setValidStartDate(
                WEB3DateUtility.getDate("20081010", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingChangeParams.setChangeDiv(WEB3ChangeDivDef.RELEASE);
            l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(100);
            l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(100);
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            l_mfFixedBuyingChangeParams.setFinDrawIncreaseBuyAmount(100);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("123");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("test");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(32768);
            TestDBUtility.insertWithDel(l_productParams);

            SubAccount l_subAccount = l_impl.getSubAccount();
            String l_strBranchCode =
                l_subAccount.getMainAccount().getBranch().getBranchCode();
            String l_strAccountCode =
                l_subAccount.getMainAccount().getAccountCode();
            String l_strInstitutionCode =
                l_subAccount.getInstitution().getInstitutionCode();

            String l_strQueryString = " status in (?, ?) ";
            Object[] l_objQueryValues = new Object[]{
                WEB3MFStatusDef.SONAR_NOT_SEND,
                WEB3MFStatusDef.SONAR_SENDED};
            List l_lisFixedBuyConditionChangeList =
                l_mutualFixedBuyCommonServiceImpl.getFixedBuyConditionChangeList(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode,
                    l_strQueryString,
                    l_objQueryValues);

            List l_lisFixedBuyConditionList =
                l_mutualFixedBuyCommonServiceImpl.getFixedBuyConditionList(
                    l_strInstitutionCode, 
                    l_strBranchCode, 
                    l_strAccountCode, 
                    null, null);

            WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits =
                l_impl.mergeMutualFixedBuyConditionMonth(
                    l_institution,
                    l_lisFixedBuyConditionList,
                    l_lisFixedBuyConditionChangeList);

            assertEquals(l_mutualFixedBuyConditionUnits.length, 1);
            assertEquals(l_mutualFixedBuyConditionUnits[0].monthlyBuyAmount, "0");
            assertEquals(l_mutualFixedBuyConditionUnits[0].increaseBuyAmount, "0");
            int l_intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].validStartDate,
                WEB3DateUtility.getDate("20081010", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_intCompreResult, 0);
            l_intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].debitAccountYM,
                WEB3DateUtility.getDate("20081010", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_intCompreResult, 0);
            assertEquals(l_mutualFixedBuyConditionUnits[0].definiteIncreaseBuyAmount, "100");
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductCode, "123");
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductName, "test");
            assertNull(l_mutualFixedBuyConditionUnits[0].updateDate);
            assertNull(l_mutualFixedBuyConditionUnits[0].checkResult);
            assertTrue(l_mutualFixedBuyConditionUnits[0].suspensionFlag);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testMergeMutualFixedBuyConditionMonth_C0007()
    {
        final String STR_METHOD_NAME = "testMergeMutualFixedBuyCondition_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionListServiceImpl l_impl = new WEB3MutualFixedBuyConditionListServiceImpl();

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123));
        
        WEB3MutualFixedBuyCommonServiceImpl l_mutualFixedBuyCommonServiceImpl = 
            new WEB3MutualFixedBuyCommonServiceImpl();
        
        try
        {
            TestDBUtility.deleteAll(InsiderParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            Institution l_institution = new InstitutionImpl(l_institutionParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(123);
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(1234);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            

            SubAccount l_subAccount1 = new SubAccountImpl(l_subAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "getSubAccount",
                new Class[]{ SubAccountTypeEnum.class },
                l_subAccount1);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(MfFixedBuyingCondRow.TYPE);
            MfFixedBuyingCondParams l_mfFixedBuyingCondParams =
                TestDBUtility.getMfFixedBuyingCondRow();
            l_mfFixedBuyingCondParams.setAccountCode("123");
            l_mfFixedBuyingCondParams.setProductCode("123");
            l_mfFixedBuyingCondParams.setDrawDate(
                WEB3DateUtility.getDate("20081012", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setValidStartDate(
                WEB3DateUtility.getDate("20081011", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setIncreaseBuyAmount(200);
            TestDBUtility.insertWithDel(l_mfFixedBuyingCondParams);

            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
            MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("1");
            l_mfFixedBuyingChangeParams.setProductCode("123");
            l_mfFixedBuyingChangeParams.setValidStartDate(
                WEB3DateUtility.getDate("20081010", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingChangeParams.setChangeDiv(WEB3ChangeDivDef.TEMP_STOP);
            l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(100);
            l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(100);
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            l_mfFixedBuyingChangeParams.setFinDrawIncreaseBuyAmount(100);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("123");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("test");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(32768);
            TestDBUtility.insertWithDel(l_productParams);

            SubAccount l_subAccount = l_impl.getSubAccount();
            String l_strBranchCode =
                l_subAccount.getMainAccount().getBranch().getBranchCode();
            String l_strAccountCode =
                l_subAccount.getMainAccount().getAccountCode();
            String l_strInstitutionCode =
                l_subAccount.getInstitution().getInstitutionCode();

            String l_strQueryString = " status in (?, ?) ";
            Object[] l_objQueryValues = new Object[]{
                WEB3MFStatusDef.SONAR_NOT_SEND,
                WEB3MFStatusDef.SONAR_SENDED};
            List l_lisFixedBuyConditionChangeList =
                l_mutualFixedBuyCommonServiceImpl.getFixedBuyConditionChangeList(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode,
                    l_strQueryString,
                    l_objQueryValues);

            List l_lisFixedBuyConditionList =
                l_mutualFixedBuyCommonServiceImpl.getFixedBuyConditionList(
                    l_strInstitutionCode, 
                    l_strBranchCode, 
                    l_strAccountCode, 
                    null, null);

            WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits =
                l_impl.mergeMutualFixedBuyConditionMonth(
                    l_institution,
                    l_lisFixedBuyConditionList,
                    l_lisFixedBuyConditionChangeList);

            assertEquals(l_mutualFixedBuyConditionUnits.length, 1);
            assertEquals(l_mutualFixedBuyConditionUnits[0].monthlyBuyAmount, "0");
            assertEquals(l_mutualFixedBuyConditionUnits[0].increaseBuyAmount, "0");
            int l_intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].validStartDate,
                WEB3DateUtility.getDate("20081010", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_intCompreResult, 0);
            l_intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].debitAccountYM,
                WEB3DateUtility.getDate("20081010", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_intCompreResult, 0);
            assertEquals(l_mutualFixedBuyConditionUnits[0].definiteIncreaseBuyAmount, "100");
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductCode, "123");
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductName, "test");
            assertNull(l_mutualFixedBuyConditionUnits[0].updateDate);
            assertNull(l_mutualFixedBuyConditionUnits[0].checkResult);
            assertTrue(l_mutualFixedBuyConditionUnits[0].suspensionFlag);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testMergeMutualFixedBuyConditionMonth_C0008()
    {
        final String STR_METHOD_NAME = "testMergeMutualFixedBuyCondition_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionListServiceImpl l_impl = new WEB3MutualFixedBuyConditionListServiceImpl();

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123));
        
        WEB3MutualFixedBuyCommonServiceImpl l_mutualFixedBuyCommonServiceImpl = 
            new WEB3MutualFixedBuyCommonServiceImpl();
        
        try
        {
            TestDBUtility.deleteAll(InsiderParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            Institution l_institution = new InstitutionImpl(l_institutionParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(123);
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(1234);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            

            SubAccount l_subAccount1 = new SubAccountImpl(l_subAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "getSubAccount",
                new Class[]{ SubAccountTypeEnum.class },
                l_subAccount1);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(MfFixedBuyingCondRow.TYPE);
            MfFixedBuyingCondParams l_mfFixedBuyingCondParams =
                TestDBUtility.getMfFixedBuyingCondRow();
            l_mfFixedBuyingCondParams.setAccountCode("123");
            l_mfFixedBuyingCondParams.setProductCode("123");
            l_mfFixedBuyingCondParams.setDrawDate(
                WEB3DateUtility.getDate("20081012", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setValidStartDate(
                WEB3DateUtility.getDate("20081011", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setIncreaseBuyAmount(200);
            TestDBUtility.insertWithDel(l_mfFixedBuyingCondParams);

            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
            MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("1");
            l_mfFixedBuyingChangeParams.setProductCode("123");
            l_mfFixedBuyingChangeParams.setValidStartDate(
                WEB3DateUtility.getDate("20081010", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingChangeParams.setChangeDiv(WEB3ChangeDivDef.TEMP_STOP);
            l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(100);
            l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(100);
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            l_mfFixedBuyingChangeParams.setFinDrawIncreaseBuyAmount(null);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("123");
            l_mutualFundProductParams.setProductIssueCode("0");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(32768);
            TestDBUtility.insertWithDel(l_productParams);

            SubAccount l_subAccount = l_impl.getSubAccount();
            String l_strBranchCode =
                l_subAccount.getMainAccount().getBranch().getBranchCode();
            String l_strAccountCode =
                l_subAccount.getMainAccount().getAccountCode();
            String l_strInstitutionCode =
                l_subAccount.getInstitution().getInstitutionCode();

            String l_strQueryString = " status in (?, ?) ";
            Object[] l_objQueryValues = new Object[]{
                WEB3MFStatusDef.SONAR_NOT_SEND,
                WEB3MFStatusDef.SONAR_SENDED};
            List l_lisFixedBuyConditionChangeList =
                l_mutualFixedBuyCommonServiceImpl.getFixedBuyConditionChangeList(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode,
                    l_strQueryString,
                    l_objQueryValues);

            List l_lisFixedBuyConditionList =
                l_mutualFixedBuyCommonServiceImpl.getFixedBuyConditionList(
                    l_strInstitutionCode, 
                    l_strBranchCode, 
                    l_strAccountCode, 
                    null, null);

            WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits =
                l_impl.mergeMutualFixedBuyConditionMonth(
                    l_institution,
                    l_lisFixedBuyConditionList,
                    l_lisFixedBuyConditionChangeList);

            assertEquals(l_mutualFixedBuyConditionUnits.length, 0);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testMergeMutualFixedBuyConditionMonth_C0009()
    {
        final String STR_METHOD_NAME = "testMergeMutualFixedBuyCondition_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionListServiceImpl l_impl = new WEB3MutualFixedBuyConditionListServiceImpl();

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123));
        
        WEB3MutualFixedBuyCommonServiceImpl l_mutualFixedBuyCommonServiceImpl = 
            new WEB3MutualFixedBuyCommonServiceImpl();
        
        try
        {
            TestDBUtility.deleteAll(InsiderParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            Institution l_institution = new InstitutionImpl(l_institutionParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(123);
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(1234);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            

            SubAccount l_subAccount1 = new SubAccountImpl(l_subAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "getSubAccount",
                new Class[]{ SubAccountTypeEnum.class },
                l_subAccount1);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(MfFixedBuyingCondRow.TYPE);
            MfFixedBuyingCondParams l_mfFixedBuyingCondParams =
                TestDBUtility.getMfFixedBuyingCondRow();
            l_mfFixedBuyingCondParams.setAccountCode("123");
            l_mfFixedBuyingCondParams.setProductCode("123");
            l_mfFixedBuyingCondParams.setDrawDate(
                WEB3DateUtility.getDate("20081012", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setValidStartDate(
                WEB3DateUtility.getDate("20081011", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setIncreaseBuyAmount(200);
            l_mfFixedBuyingCondParams.setMonthlyBuyAmount(150);
            TestDBUtility.insertWithDel(l_mfFixedBuyingCondParams);

            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
            MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("1");
            l_mfFixedBuyingChangeParams.setProductCode("1234");
            l_mfFixedBuyingChangeParams.setValidStartDate(
                WEB3DateUtility.getDate("20081010", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingChangeParams.setChangeDiv(WEB3ChangeDivDef.ADD);
            l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(100);
            l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(50);
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            l_mfFixedBuyingChangeParams.setFinDrawIncreaseBuyAmount(100);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setProductId(123);
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("123");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("test1");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setProductId(124);
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("1234");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("test2");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(32768);
            TestDBUtility.insertWithDel(l_productParams);
            l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123);
            TestDBUtility.insertWithDel(l_productParams);
            l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(124);
            TestDBUtility.insertWithDel(l_productParams);

            SubAccount l_subAccount = l_impl.getSubAccount();
            String l_strBranchCode =
                l_subAccount.getMainAccount().getBranch().getBranchCode();
            String l_strAccountCode =
                l_subAccount.getMainAccount().getAccountCode();
            String l_strInstitutionCode =
                l_subAccount.getInstitution().getInstitutionCode();

            String l_strQueryString = " status in (?, ?) ";
            Object[] l_objQueryValues = new Object[]{
                WEB3MFStatusDef.SONAR_NOT_SEND,
                WEB3MFStatusDef.SONAR_SENDED};
            List l_lisFixedBuyConditionChangeList =
                l_mutualFixedBuyCommonServiceImpl.getFixedBuyConditionChangeList(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode,
                    l_strQueryString,
                    l_objQueryValues);

            List l_lisFixedBuyConditionList =
                l_mutualFixedBuyCommonServiceImpl.getFixedBuyConditionList(
                    l_strInstitutionCode, 
                    l_strBranchCode, 
                    l_strAccountCode, 
                    null, null);

            WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits =
                l_impl.mergeMutualFixedBuyConditionMonth(
                    l_institution,
                    l_lisFixedBuyConditionList,
                    l_lisFixedBuyConditionChangeList);

            assertEquals(l_mutualFixedBuyConditionUnits.length, 2);
            assertEquals(l_mutualFixedBuyConditionUnits[0].monthlyBuyAmount, "150");
            assertEquals(l_mutualFixedBuyConditionUnits[0].increaseBuyAmount, "200");
            int l_intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].validStartDate,
                WEB3DateUtility.getDate("20081011", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_intCompreResult, 0);
            l_intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].debitAccountYM,
                WEB3DateUtility.getDate("20081012", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_intCompreResult, 0);
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductCode, "123");
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductName, "test1");
            assertNull(l_mutualFixedBuyConditionUnits[0].updateDate);
            assertNull(l_mutualFixedBuyConditionUnits[0].checkResult);
            assertFalse(l_mutualFixedBuyConditionUnits[0].suspensionFlag);


            assertEquals(l_mutualFixedBuyConditionUnits[1].monthlyBuyAmount, "50");
            assertEquals(l_mutualFixedBuyConditionUnits[1].increaseBuyAmount, "100");
            l_intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[1].validStartDate,
                WEB3DateUtility.getDate("20081010", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_intCompreResult, 0);
            l_intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[1].debitAccountYM,
                WEB3DateUtility.getDate("20081010", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_intCompreResult, 0);
            assertNull(l_mutualFixedBuyConditionUnits[1].definiteIncreaseBuyAmount);
            assertEquals(l_mutualFixedBuyConditionUnits[1].mutualProductCode, "1234");
            assertEquals(l_mutualFixedBuyConditionUnits[1].mutualProductName, "test2");
            assertNull(l_mutualFixedBuyConditionUnits[1].updateDate);
            assertNull(l_mutualFixedBuyConditionUnits[1].checkResult);
            assertFalse(l_mutualFixedBuyConditionUnits[1].suspensionFlag);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testMergeMutualFixedBuyConditionMonth_C0010()
    {
        final String STR_METHOD_NAME = "testMergeMutualFixedBuyCondition_C0010()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionListServiceImpl l_impl = new WEB3MutualFixedBuyConditionListServiceImpl();

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123));
        
        WEB3MutualFixedBuyCommonServiceImpl l_mutualFixedBuyCommonServiceImpl = 
            new WEB3MutualFixedBuyCommonServiceImpl();
        
        try
        {
            TestDBUtility.deleteAll(InsiderParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            Institution l_institution = new InstitutionImpl(l_institutionParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(123);
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(1234);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            

            SubAccount l_subAccount1 = new SubAccountImpl(l_subAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "getSubAccount",
                new Class[]{ SubAccountTypeEnum.class },
                l_subAccount1);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(MfFixedBuyingCondRow.TYPE);
            MfFixedBuyingCondParams l_mfFixedBuyingCondParams =
                TestDBUtility.getMfFixedBuyingCondRow();
            l_mfFixedBuyingCondParams.setAccountCode("123");
            l_mfFixedBuyingCondParams.setProductCode("123");
            l_mfFixedBuyingCondParams.setDrawDate(
                WEB3DateUtility.getDate("20081012", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setValidStartDate(
                WEB3DateUtility.getDate("20081011", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setIncreaseBuyAmount(200);
            l_mfFixedBuyingCondParams.setMonthlyBuyAmount(150);
            TestDBUtility.insertWithDel(l_mfFixedBuyingCondParams);

            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
            MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("1");
            l_mfFixedBuyingChangeParams.setProductCode("1234");
            l_mfFixedBuyingChangeParams.setValidStartDate(
                WEB3DateUtility.getDate("20081010", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingChangeParams.setChangeDiv(WEB3ChangeDivDef.CHANGE);
            l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(100);
            l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(50);
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            l_mfFixedBuyingChangeParams.setFinDrawIncreaseBuyAmount(100);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setProductId(123);
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("123");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("test1");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setProductId(124);
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("1234");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("test2");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(32768);
            TestDBUtility.insertWithDel(l_productParams);
            l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123);
            TestDBUtility.insertWithDel(l_productParams);
            l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(124);
            TestDBUtility.insertWithDel(l_productParams);

            SubAccount l_subAccount = l_impl.getSubAccount();
            String l_strBranchCode =
                l_subAccount.getMainAccount().getBranch().getBranchCode();
            String l_strAccountCode =
                l_subAccount.getMainAccount().getAccountCode();
            String l_strInstitutionCode =
                l_subAccount.getInstitution().getInstitutionCode();

            String l_strQueryString = " status in (?, ?) ";
            Object[] l_objQueryValues = new Object[]{
                WEB3MFStatusDef.SONAR_NOT_SEND,
                WEB3MFStatusDef.SONAR_SENDED};
            List l_lisFixedBuyConditionChangeList =
                l_mutualFixedBuyCommonServiceImpl.getFixedBuyConditionChangeList(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode,
                    l_strQueryString,
                    l_objQueryValues);

            List l_lisFixedBuyConditionList =
                l_mutualFixedBuyCommonServiceImpl.getFixedBuyConditionList(
                    l_strInstitutionCode, 
                    l_strBranchCode, 
                    l_strAccountCode, 
                    null, null);

            WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits =
                l_impl.mergeMutualFixedBuyConditionMonth(
                    l_institution,
                    l_lisFixedBuyConditionList,
                    l_lisFixedBuyConditionChangeList);

            assertEquals(l_mutualFixedBuyConditionUnits.length, 2);
            assertEquals(l_mutualFixedBuyConditionUnits[0].monthlyBuyAmount, "150");
            assertEquals(l_mutualFixedBuyConditionUnits[0].increaseBuyAmount, "200");
            int l_intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].validStartDate,
                WEB3DateUtility.getDate("20081011", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_intCompreResult, 0);
            l_intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].debitAccountYM,
                WEB3DateUtility.getDate("20081012", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_intCompreResult, 0);
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductCode, "123");
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductName, "test1");
            assertNull(l_mutualFixedBuyConditionUnits[0].updateDate);
            assertNull(l_mutualFixedBuyConditionUnits[0].checkResult);
            assertFalse(l_mutualFixedBuyConditionUnits[0].suspensionFlag);


            assertEquals(l_mutualFixedBuyConditionUnits[1].monthlyBuyAmount, "50");
            assertEquals(l_mutualFixedBuyConditionUnits[1].increaseBuyAmount, "100");
            l_intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[1].validStartDate,
                WEB3DateUtility.getDate("20081010", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_intCompreResult, 0);
            l_intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[1].debitAccountYM,
                WEB3DateUtility.getDate("20081010", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_intCompreResult, 0);
            assertNull(l_mutualFixedBuyConditionUnits[1].definiteIncreaseBuyAmount);
            assertEquals(l_mutualFixedBuyConditionUnits[1].mutualProductCode, "1234");
            assertEquals(l_mutualFixedBuyConditionUnits[1].mutualProductName, "test2");
            assertNull(l_mutualFixedBuyConditionUnits[1].updateDate);
            assertNull(l_mutualFixedBuyConditionUnits[1].checkResult);
            assertFalse(l_mutualFixedBuyConditionUnits[1].suspensionFlag);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testMergeMutualFixedBuyConditionMonth_C0011()
    {
        final String STR_METHOD_NAME = "testMergeMutualFixedBuyCondition_C0011()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionListServiceImpl l_impl = new WEB3MutualFixedBuyConditionListServiceImpl();

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123));
        
        WEB3MutualFixedBuyCommonServiceImpl l_mutualFixedBuyCommonServiceImpl = 
            new WEB3MutualFixedBuyCommonServiceImpl();
        
        try
        {
            TestDBUtility.deleteAll(InsiderParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            Institution l_institution = new InstitutionImpl(l_institutionParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(123);
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(1234);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            

            SubAccount l_subAccount1 = new SubAccountImpl(l_subAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "getSubAccount",
                new Class[]{ SubAccountTypeEnum.class },
                l_subAccount1);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(MfFixedBuyingCondRow.TYPE);
            MfFixedBuyingCondParams l_mfFixedBuyingCondParams =
                TestDBUtility.getMfFixedBuyingCondRow();
            l_mfFixedBuyingCondParams.setAccountCode("123");
            l_mfFixedBuyingCondParams.setProductCode("123");
            l_mfFixedBuyingCondParams.setDrawDate(
                WEB3DateUtility.getDate("20081012", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setValidStartDate(
                WEB3DateUtility.getDate("20081011", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingCondParams.setIncreaseBuyAmount(200);
            l_mfFixedBuyingCondParams.setMonthlyBuyAmount(150);
            TestDBUtility.insertWithDel(l_mfFixedBuyingCondParams);

            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
            MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("1");
            l_mfFixedBuyingChangeParams.setProductCode("1234");
            l_mfFixedBuyingChangeParams.setValidStartDate(
                WEB3DateUtility.getDate("20081010", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingChangeParams.setChangeDiv(WEB3ChangeDivDef.RELEASE);
            l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(100);
            l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(50);
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            l_mfFixedBuyingChangeParams.setFinDrawIncreaseBuyAmount(100);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setProductId(123);
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("123");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("test1");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setProductId(124);
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("1234");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("test2");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(32768);
            TestDBUtility.insertWithDel(l_productParams);
            l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123);
            TestDBUtility.insertWithDel(l_productParams);
            l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(124);
            TestDBUtility.insertWithDel(l_productParams);

            SubAccount l_subAccount = l_impl.getSubAccount();
            String l_strBranchCode =
                l_subAccount.getMainAccount().getBranch().getBranchCode();
            String l_strAccountCode =
                l_subAccount.getMainAccount().getAccountCode();
            String l_strInstitutionCode =
                l_subAccount.getInstitution().getInstitutionCode();

            String l_strQueryString = " status in (?, ?) ";
            Object[] l_objQueryValues = new Object[]{
                WEB3MFStatusDef.SONAR_NOT_SEND,
                WEB3MFStatusDef.SONAR_SENDED};
            List l_lisFixedBuyConditionChangeList =
                l_mutualFixedBuyCommonServiceImpl.getFixedBuyConditionChangeList(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode,
                    l_strQueryString,
                    l_objQueryValues);

            List l_lisFixedBuyConditionList =
                l_mutualFixedBuyCommonServiceImpl.getFixedBuyConditionList(
                    l_strInstitutionCode, 
                    l_strBranchCode, 
                    l_strAccountCode, 
                    null, null);

            WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits =
                l_impl.mergeMutualFixedBuyConditionMonth(
                    l_institution,
                    l_lisFixedBuyConditionList,
                    l_lisFixedBuyConditionChangeList);

            assertEquals(l_mutualFixedBuyConditionUnits.length, 2);
            assertEquals(l_mutualFixedBuyConditionUnits[0].monthlyBuyAmount, "150");
            assertEquals(l_mutualFixedBuyConditionUnits[0].increaseBuyAmount, "200");
            int l_intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].validStartDate,
                WEB3DateUtility.getDate("20081011", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_intCompreResult, 0);
            l_intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].debitAccountYM,
                WEB3DateUtility.getDate("20081012", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_intCompreResult, 0);
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductCode, "123");
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductName, "test1");
            assertNull(l_mutualFixedBuyConditionUnits[0].updateDate);
            assertNull(l_mutualFixedBuyConditionUnits[0].checkResult);
            assertFalse(l_mutualFixedBuyConditionUnits[0].suspensionFlag);


            assertEquals(l_mutualFixedBuyConditionUnits[1].monthlyBuyAmount, "0");
            assertEquals(l_mutualFixedBuyConditionUnits[1].increaseBuyAmount, "0");
            l_intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[1].validStartDate,
                WEB3DateUtility.getDate("20081010", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_intCompreResult, 0);
            l_intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[1].debitAccountYM,
                WEB3DateUtility.getDate("20081010", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_intCompreResult, 0);
            assertEquals(l_mutualFixedBuyConditionUnits[1].definiteIncreaseBuyAmount, "100");
            assertEquals(l_mutualFixedBuyConditionUnits[1].mutualProductCode, "1234");
            assertEquals(l_mutualFixedBuyConditionUnits[1].mutualProductName, "test2");
            assertNull(l_mutualFixedBuyConditionUnits[1].updateDate);
            assertNull(l_mutualFixedBuyConditionUnits[1].checkResult);
            assertTrue(l_mutualFixedBuyConditionUnits[1].suspensionFlag);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testMergeMutualFixedBuyConditionThreeMonth_C0001()
    {
        final String STR_METHOD_NAME = "testMergeMutualFixedBuyConditionThreeMonth_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionListServiceImpl l_impl = new WEB3MutualFixedBuyConditionListServiceImpl();

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123));
        
        WEB3MutualFixedBuyCommonServiceImpl l_mutualFixedBuyCommonServiceImpl = 
            new WEB3MutualFixedBuyCommonServiceImpl();
        
        try
        {
            TestDBUtility.deleteAll(InsiderParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            Institution l_institution = new InstitutionImpl(l_institutionParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(123);
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(1234);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            

            SubAccount l_subAccount1 = new SubAccountImpl(l_subAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "getSubAccount",
                new Class[]{ SubAccountTypeEnum.class },
                l_subAccount1);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
            MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("1");
            l_mfFixedBuyingChangeParams.setProductCode("123");
            l_mfFixedBuyingChangeParams.setValidStartDate(
                WEB3DateUtility.getDate("20081010", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingChangeParams.setChangeDiv(WEB3ChangeDivDef.ADD);
            l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(100);
            l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(50);
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            l_mfFixedBuyingChangeParams.setFinDrawIncreaseBuyAmount(null);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setProductId(123);
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("123");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("test1");

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(32768);
            TestDBUtility.insertWithDel(l_productParams);
            l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123);
            TestDBUtility.insertWithDel(l_productParams);
            l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(124);
            TestDBUtility.insertWithDel(l_productParams);

            SubAccount l_subAccount = l_impl.getSubAccount();
            String l_strBranchCode =
                l_subAccount.getMainAccount().getBranch().getBranchCode();
            String l_strAccountCode =
                l_subAccount.getMainAccount().getAccountCode();
            String l_strInstitutionCode =
                l_subAccount.getInstitution().getInstitutionCode();

            String l_strQueryString = " status in (?, ?) ";
            Object[] l_objQueryValues = new Object[]{
                WEB3MFStatusDef.SONAR_NOT_SEND,
                WEB3MFStatusDef.SONAR_SENDED};
            List l_lisFixedBuyConditionChangeList =
                l_mutualFixedBuyCommonServiceImpl.getFixedBuyConditionChangeList(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode,
                    l_strQueryString,
                    l_objQueryValues);

            WEB3MutualFixedBuyConditionUnit l_mutualFixedBuyConditionUnit =
                new WEB3MutualFixedBuyConditionUnit();
            l_mutualFixedBuyConditionUnit.categoryCode = "123";
            l_mutualFixedBuyConditionUnit.checkResult = null;
            l_mutualFixedBuyConditionUnit.debitAccountYM =
                WEB3DateUtility.getDate("20081010", WEB3GentradeTimeDef.DATE_FORMAT_YMD);
            l_mutualFixedBuyConditionUnit.definiteIncreaseBuyAmount = "100";
            l_mutualFixedBuyConditionUnit.displayOrder = "1";
            l_mutualFixedBuyConditionUnit.increaseBuyAmount = "100";
            l_mutualFixedBuyConditionUnit.monthlyBuyAmount = "150";
            l_mutualFixedBuyConditionUnit.mutualProductCode = "123";
            l_mutualFixedBuyConditionUnit.mutualProductName = "CASE";
            l_mutualFixedBuyConditionUnit.suspensionFlag = true;
            l_mutualFixedBuyConditionUnit.updateDate = null;
            l_mutualFixedBuyConditionUnit.validStartDate =
                WEB3DateUtility.getDate("20081011", WEB3GentradeTimeDef.DATE_FORMAT_YMD);

            WEB3MutualFixedBuyConditionUnit[] l_conditionUnits =
                new WEB3MutualFixedBuyConditionUnit[1];
            l_conditionUnits[0] = l_mutualFixedBuyConditionUnit;

            WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits =
                l_impl.mergeMutualFixedBuyConditionThreeMonth(
                    l_institution,
                    l_conditionUnits,
                    l_lisFixedBuyConditionChangeList);

            assertEquals(l_mutualFixedBuyConditionUnits.length, 1);
            assertEquals(l_mutualFixedBuyConditionUnits[0].monthlyBuyAmount, "50");
            assertEquals(l_mutualFixedBuyConditionUnits[0].increaseBuyAmount, "100");
            int l_intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].validStartDate,
                WEB3DateUtility.getDate("20081010", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_intCompreResult, 0);
            l_intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].debitAccountYM,
                WEB3DateUtility.getDate("20081010", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_intCompreResult, 0);
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductCode, "123");
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductName, "CASE");
            assertNull(l_mutualFixedBuyConditionUnits[0].updateDate);
            assertNull(l_mutualFixedBuyConditionUnits[0].checkResult);
            assertFalse(l_mutualFixedBuyConditionUnits[0].suspensionFlag);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testMergeMutualFixedBuyConditionThreeMonth_C0002()
    {
        final String STR_METHOD_NAME = "testMergeMutualFixedBuyConditionThreeMonth_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionListServiceImpl l_impl = new WEB3MutualFixedBuyConditionListServiceImpl();

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123));
        
        WEB3MutualFixedBuyCommonServiceImpl l_mutualFixedBuyCommonServiceImpl = 
            new WEB3MutualFixedBuyCommonServiceImpl();
        
        try
        {
            TestDBUtility.deleteAll(InsiderParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            Institution l_institution = new InstitutionImpl(l_institutionParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(123);
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(1234);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            

            SubAccount l_subAccount1 = new SubAccountImpl(l_subAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "getSubAccount",
                new Class[]{ SubAccountTypeEnum.class },
                l_subAccount1);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
            MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("1");
            l_mfFixedBuyingChangeParams.setProductCode("123");
            l_mfFixedBuyingChangeParams.setValidStartDate(
                WEB3DateUtility.getDate("20081010", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingChangeParams.setChangeDiv(WEB3ChangeDivDef.CHANGE);
            l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(100);
            l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(50);
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            l_mfFixedBuyingChangeParams.setFinDrawIncreaseBuyAmount(null);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setProductId(123);
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("123");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("test1");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(32768);
            TestDBUtility.insertWithDel(l_productParams);
            l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123);
            TestDBUtility.insertWithDel(l_productParams);
            l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(124);
            TestDBUtility.insertWithDel(l_productParams);

            SubAccount l_subAccount = l_impl.getSubAccount();
            String l_strBranchCode =
                l_subAccount.getMainAccount().getBranch().getBranchCode();
            String l_strAccountCode =
                l_subAccount.getMainAccount().getAccountCode();
            String l_strInstitutionCode =
                l_subAccount.getInstitution().getInstitutionCode();

            String l_strQueryString = " status in (?, ?) ";
            Object[] l_objQueryValues = new Object[]{
                WEB3MFStatusDef.SONAR_NOT_SEND,
                WEB3MFStatusDef.SONAR_SENDED};
            List l_lisFixedBuyConditionChangeList =
                l_mutualFixedBuyCommonServiceImpl.getFixedBuyConditionChangeList(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode,
                    l_strQueryString,
                    l_objQueryValues);
            WEB3MutualFixedBuyConditionUnit l_mutualFixedBuyConditionUnit =
                new WEB3MutualFixedBuyConditionUnit();
            l_mutualFixedBuyConditionUnit.categoryCode = "123";
            l_mutualFixedBuyConditionUnit.checkResult = "0";
            l_mutualFixedBuyConditionUnit.debitAccountYM =
                WEB3DateUtility.getDate("20081010", WEB3GentradeTimeDef.DATE_FORMAT_YMD);
            l_mutualFixedBuyConditionUnit.definiteIncreaseBuyAmount = "100";
            l_mutualFixedBuyConditionUnit.displayOrder = "1";
            l_mutualFixedBuyConditionUnit.increaseBuyAmount = "100";
            l_mutualFixedBuyConditionUnit.monthlyBuyAmount = "150";
            l_mutualFixedBuyConditionUnit.mutualProductCode = "123";
            l_mutualFixedBuyConditionUnit.mutualProductName = "CASE";
            l_mutualFixedBuyConditionUnit.suspensionFlag = true;
            l_mutualFixedBuyConditionUnit.updateDate = null;
            l_mutualFixedBuyConditionUnit.validStartDate =
                WEB3DateUtility.getDate("20081011", WEB3GentradeTimeDef.DATE_FORMAT_YMD);

            WEB3MutualFixedBuyConditionUnit[] l_conditionUnits =
                new WEB3MutualFixedBuyConditionUnit[1];
            l_conditionUnits[0] = l_mutualFixedBuyConditionUnit;

            WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits =
                l_impl.mergeMutualFixedBuyConditionThreeMonth(
                    l_institution,
                    l_conditionUnits,
                    l_lisFixedBuyConditionChangeList);

            assertEquals(l_mutualFixedBuyConditionUnits.length, 1);
            assertEquals(l_mutualFixedBuyConditionUnits[0].monthlyBuyAmount, "50");
            assertEquals(l_mutualFixedBuyConditionUnits[0].increaseBuyAmount, "100");
            int l_intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].validStartDate,
                WEB3DateUtility.getDate("20081010", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_intCompreResult, 0);
            l_intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].debitAccountYM,
                WEB3DateUtility.getDate("20081010", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_intCompreResult, 0);
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductCode, "123");
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductName, "CASE");
            assertNull(l_mutualFixedBuyConditionUnits[0].updateDate);
            assertEquals (l_mutualFixedBuyConditionUnits[0].checkResult, "0");
            assertFalse(l_mutualFixedBuyConditionUnits[0].suspensionFlag);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testMergeMutualFixedBuyConditionThreeMonth_C0003()
    {
        final String STR_METHOD_NAME = "testMergeMutualFixedBuyConditionThreeMonth_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionListServiceImpl l_impl = new WEB3MutualFixedBuyConditionListServiceImpl();

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123));
        
        WEB3MutualFixedBuyCommonServiceImpl l_mutualFixedBuyCommonServiceImpl = 
            new WEB3MutualFixedBuyCommonServiceImpl();
        
        try
        {
            TestDBUtility.deleteAll(InsiderParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            Institution l_institution = new InstitutionImpl(l_institutionParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(123);
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(1234);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            

            SubAccount l_subAccount1 = new SubAccountImpl(l_subAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "getSubAccount",
                new Class[]{ SubAccountTypeEnum.class },
                l_subAccount1);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
            MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("1");
            l_mfFixedBuyingChangeParams.setProductCode("123");
            l_mfFixedBuyingChangeParams.setValidStartDate(
                WEB3DateUtility.getDate("20081010", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingChangeParams.setChangeDiv(WEB3ChangeDivDef.RELEASE);
            l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(100);
            l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(50);
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            l_mfFixedBuyingChangeParams.setFinDrawIncreaseBuyAmount(null);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setProductId(123);
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("123");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("test1");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(32768);
            TestDBUtility.insertWithDel(l_productParams);
            l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123);
            TestDBUtility.insertWithDel(l_productParams);
            l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(124);
            TestDBUtility.insertWithDel(l_productParams);

            SubAccount l_subAccount = l_impl.getSubAccount();
            String l_strBranchCode =
                l_subAccount.getMainAccount().getBranch().getBranchCode();
            String l_strAccountCode =
                l_subAccount.getMainAccount().getAccountCode();
            String l_strInstitutionCode =
                l_subAccount.getInstitution().getInstitutionCode();

            String l_strQueryString = " status in (?, ?) ";
            Object[] l_objQueryValues = new Object[]{
                WEB3MFStatusDef.SONAR_NOT_SEND,
                WEB3MFStatusDef.SONAR_SENDED};
            List l_lisFixedBuyConditionChangeList =
                l_mutualFixedBuyCommonServiceImpl.getFixedBuyConditionChangeList(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode,
                    l_strQueryString,
                    l_objQueryValues);

            WEB3MutualFixedBuyConditionUnit l_mutualFixedBuyConditionUnit =
                new WEB3MutualFixedBuyConditionUnit();
            l_mutualFixedBuyConditionUnit.categoryCode = "123";
            l_mutualFixedBuyConditionUnit.checkResult = "0";
            l_mutualFixedBuyConditionUnit.debitAccountYM =
                WEB3DateUtility.getDate("20081010", WEB3GentradeTimeDef.DATE_FORMAT_YMD);
            l_mutualFixedBuyConditionUnit.definiteIncreaseBuyAmount = "100";
            l_mutualFixedBuyConditionUnit.displayOrder = "1";
            l_mutualFixedBuyConditionUnit.increaseBuyAmount = "100";
            l_mutualFixedBuyConditionUnit.monthlyBuyAmount = "150";
            l_mutualFixedBuyConditionUnit.mutualProductCode = "123";
            l_mutualFixedBuyConditionUnit.mutualProductName = "CASE";
            l_mutualFixedBuyConditionUnit.suspensionFlag = false;
            l_mutualFixedBuyConditionUnit.updateDate =
                WEB3DateUtility.getDate("20081020", WEB3GentradeTimeDef.DATE_FORMAT_YMD);
            l_mutualFixedBuyConditionUnit.validStartDate =
                WEB3DateUtility.getDate("20081011", WEB3GentradeTimeDef.DATE_FORMAT_YMD);

            WEB3MutualFixedBuyConditionUnit[] l_conditionUnits =
                new WEB3MutualFixedBuyConditionUnit[1];
            l_conditionUnits[0] = l_mutualFixedBuyConditionUnit;

            WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits =
                l_impl.mergeMutualFixedBuyConditionThreeMonth(
                    l_institution,
                    l_conditionUnits,
                    l_lisFixedBuyConditionChangeList);

            assertEquals(l_mutualFixedBuyConditionUnits.length, 0);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testMergeMutualFixedBuyConditionThreeMonth_C0004()
    {
        final String STR_METHOD_NAME = "testMergeMutualFixedBuyConditionThreeMonth_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionListServiceImpl l_impl = new WEB3MutualFixedBuyConditionListServiceImpl();

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123));
        
        WEB3MutualFixedBuyCommonServiceImpl l_mutualFixedBuyCommonServiceImpl = 
            new WEB3MutualFixedBuyCommonServiceImpl();
        
        try
        {
            TestDBUtility.deleteAll(InsiderParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            Institution l_institution = new InstitutionImpl(l_institutionParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(123);
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(1234);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            

            SubAccount l_subAccount1 = new SubAccountImpl(l_subAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "getSubAccount",
                new Class[]{ SubAccountTypeEnum.class },
                l_subAccount1);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
            MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("1");
            l_mfFixedBuyingChangeParams.setProductCode("123");
            l_mfFixedBuyingChangeParams.setValidStartDate(
                WEB3DateUtility.getDate("20081010", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingChangeParams.setChangeDiv(WEB3ChangeDivDef.TEMP_STOP);
            l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(100);
            l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(50);
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            l_mfFixedBuyingChangeParams.setFinDrawIncreaseBuyAmount(null);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setProductId(123);
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("123");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("test1");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(32768);
            TestDBUtility.insertWithDel(l_productParams);
            l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123);
            TestDBUtility.insertWithDel(l_productParams);
            l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(124);
            TestDBUtility.insertWithDel(l_productParams);

            SubAccount l_subAccount = l_impl.getSubAccount();
            String l_strBranchCode =
                l_subAccount.getMainAccount().getBranch().getBranchCode();
            String l_strAccountCode =
                l_subAccount.getMainAccount().getAccountCode();
            String l_strInstitutionCode =
                l_subAccount.getInstitution().getInstitutionCode();

            String l_strQueryString = " status in (?, ?) ";
            Object[] l_objQueryValues = new Object[]{
                WEB3MFStatusDef.SONAR_NOT_SEND,
                WEB3MFStatusDef.SONAR_SENDED};
            List l_lisFixedBuyConditionChangeList =
                l_mutualFixedBuyCommonServiceImpl.getFixedBuyConditionChangeList(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode,
                    l_strQueryString,
                    l_objQueryValues);

            WEB3MutualFixedBuyConditionUnit l_mutualFixedBuyConditionUnit =
                new WEB3MutualFixedBuyConditionUnit();
            l_mutualFixedBuyConditionUnit.categoryCode = "123";
            l_mutualFixedBuyConditionUnit.checkResult = "0";
            l_mutualFixedBuyConditionUnit.debitAccountYM =
                WEB3DateUtility.getDate("20081010", WEB3GentradeTimeDef.DATE_FORMAT_YMD);
            l_mutualFixedBuyConditionUnit.definiteIncreaseBuyAmount = "100";
            l_mutualFixedBuyConditionUnit.displayOrder = "1";
            l_mutualFixedBuyConditionUnit.increaseBuyAmount = "100";
            l_mutualFixedBuyConditionUnit.monthlyBuyAmount = "150";
            l_mutualFixedBuyConditionUnit.mutualProductCode = "123";
            l_mutualFixedBuyConditionUnit.mutualProductName = "CASE";
            l_mutualFixedBuyConditionUnit.suspensionFlag = false;
            l_mutualFixedBuyConditionUnit.updateDate =
                WEB3DateUtility.getDate("20081020", WEB3GentradeTimeDef.DATE_FORMAT_YMD);
            l_mutualFixedBuyConditionUnit.validStartDate =
                WEB3DateUtility.getDate("20081011", WEB3GentradeTimeDef.DATE_FORMAT_YMD);

            WEB3MutualFixedBuyConditionUnit[] l_conditionUnits =
                new WEB3MutualFixedBuyConditionUnit[1];
            l_conditionUnits[0] = l_mutualFixedBuyConditionUnit;

            WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits =
                l_impl.mergeMutualFixedBuyConditionThreeMonth(
                    l_institution,
                    l_conditionUnits,
                    l_lisFixedBuyConditionChangeList);

            assertEquals(l_mutualFixedBuyConditionUnits.length, 0);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testMergeMutualFixedBuyConditionThreeMonth_C0005()
    {
        final String STR_METHOD_NAME = "testMergeMutualFixedBuyConditionThreeMonth_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionListServiceImpl l_impl = new WEB3MutualFixedBuyConditionListServiceImpl();

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123));
        
        WEB3MutualFixedBuyCommonServiceImpl l_mutualFixedBuyCommonServiceImpl = 
            new WEB3MutualFixedBuyCommonServiceImpl();
        
        try
        {
            TestDBUtility.deleteAll(InsiderParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            Institution l_institution = new InstitutionImpl(l_institutionParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(123);
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(1234);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            

            SubAccount l_subAccount1 = new SubAccountImpl(l_subAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "getSubAccount",
                new Class[]{ SubAccountTypeEnum.class },
                l_subAccount1);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
            MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("1");
            l_mfFixedBuyingChangeParams.setProductCode("1234");
            l_mfFixedBuyingChangeParams.setValidStartDate(
                WEB3DateUtility.getDate("20081010", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingChangeParams.setChangeDiv(WEB3ChangeDivDef.ADD);
            l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(100);
            l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(50);
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            l_mfFixedBuyingChangeParams.setFinDrawIncreaseBuyAmount(100);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setProductId(123);
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("123");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("test1");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setProductId(124);
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("1234");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("test2");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(32768);
            TestDBUtility.insertWithDel(l_productParams);
            l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123);
            TestDBUtility.insertWithDel(l_productParams);
            l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(124);
            TestDBUtility.insertWithDel(l_productParams);

            SubAccount l_subAccount = l_impl.getSubAccount();
            String l_strBranchCode =
                l_subAccount.getMainAccount().getBranch().getBranchCode();
            String l_strAccountCode =
                l_subAccount.getMainAccount().getAccountCode();
            String l_strInstitutionCode =
                l_subAccount.getInstitution().getInstitutionCode();

            String l_strQueryString = " status in (?, ?) ";
            Object[] l_objQueryValues = new Object[]{
                WEB3MFStatusDef.SONAR_NOT_SEND,
                WEB3MFStatusDef.SONAR_SENDED};
            List l_lisFixedBuyConditionChangeList =
                l_mutualFixedBuyCommonServiceImpl.getFixedBuyConditionChangeList(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode,
                    l_strQueryString,
                    l_objQueryValues);

            WEB3MutualFixedBuyConditionUnit l_mutualFixedBuyConditionUnit =
                new WEB3MutualFixedBuyConditionUnit();
            l_mutualFixedBuyConditionUnit.categoryCode = "123";
            l_mutualFixedBuyConditionUnit.checkResult = "0";
            l_mutualFixedBuyConditionUnit.debitAccountYM =
                WEB3DateUtility.getDate("20081012", WEB3GentradeTimeDef.DATE_FORMAT_YMD);
            l_mutualFixedBuyConditionUnit.definiteIncreaseBuyAmount = "100";
            l_mutualFixedBuyConditionUnit.displayOrder = "1";
            l_mutualFixedBuyConditionUnit.increaseBuyAmount = "100";
            l_mutualFixedBuyConditionUnit.monthlyBuyAmount = "150";
            l_mutualFixedBuyConditionUnit.mutualProductCode = "123";
            l_mutualFixedBuyConditionUnit.mutualProductName = "CASE";
            l_mutualFixedBuyConditionUnit.suspensionFlag = true;
            l_mutualFixedBuyConditionUnit.updateDate = null;
            l_mutualFixedBuyConditionUnit.validStartDate =
                WEB3DateUtility.getDate("20081011", WEB3GentradeTimeDef.DATE_FORMAT_YMD);

            WEB3MutualFixedBuyConditionUnit[] l_conditionUnits =
                new WEB3MutualFixedBuyConditionUnit[1];
            l_conditionUnits[0] = l_mutualFixedBuyConditionUnit;

            WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits =
                l_impl.mergeMutualFixedBuyConditionThreeMonth(
                    l_institution,
                    l_conditionUnits,
                    l_lisFixedBuyConditionChangeList);

            assertEquals(l_mutualFixedBuyConditionUnits.length, 2);

            assertEquals(l_mutualFixedBuyConditionUnits[0].monthlyBuyAmount, "150");
            assertEquals(l_mutualFixedBuyConditionUnits[0].increaseBuyAmount, "100");
            int l_intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].validStartDate,
                WEB3DateUtility.getDate("20081011", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_intCompreResult, 0);
            l_intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].debitAccountYM,
                WEB3DateUtility.getDate("20081112", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_intCompreResult, 0);
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductCode, "123");
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductName, "CASE");
            assertNull(l_mutualFixedBuyConditionUnits[0].updateDate);
            assertEquals(l_mutualFixedBuyConditionUnits[0].checkResult, "0");
            assertTrue(l_mutualFixedBuyConditionUnits[0].suspensionFlag);


            assertEquals(l_mutualFixedBuyConditionUnits[1].monthlyBuyAmount, "50");
            assertEquals(l_mutualFixedBuyConditionUnits[1].increaseBuyAmount, "100");
            l_intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[1].validStartDate,
                WEB3DateUtility.getDate("20081010", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_intCompreResult, 0);
            l_intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[1].debitAccountYM,
                WEB3DateUtility.getDate("20081010", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_intCompreResult, 0);
            assertEquals(l_mutualFixedBuyConditionUnits[1].definiteIncreaseBuyAmount, "100");
            assertEquals(l_mutualFixedBuyConditionUnits[1].mutualProductCode, "1234");
            assertEquals(l_mutualFixedBuyConditionUnits[1].mutualProductName, "test2");
            assertNull(l_mutualFixedBuyConditionUnits[1].updateDate);
            assertNull(l_mutualFixedBuyConditionUnits[1].checkResult);
            assertFalse(l_mutualFixedBuyConditionUnits[1].suspensionFlag);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testMergeMutualFixedBuyConditionThreeMonth_C0006()
    {
        final String STR_METHOD_NAME = "testMergeMutualFixedBuyConditionThreeMonth_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionListServiceImpl l_impl = new WEB3MutualFixedBuyConditionListServiceImpl();

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123));
        
        WEB3MutualFixedBuyCommonServiceImpl l_mutualFixedBuyCommonServiceImpl = 
            new WEB3MutualFixedBuyCommonServiceImpl();
        
        try
        {
            TestDBUtility.deleteAll(InsiderParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            Institution l_institution = new InstitutionImpl(l_institutionParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(123);
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(1234);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            

            SubAccount l_subAccount1 = new SubAccountImpl(l_subAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "getSubAccount",
                new Class[]{ SubAccountTypeEnum.class },
                l_subAccount1);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
            MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("1");
            l_mfFixedBuyingChangeParams.setProductCode("1234");
            l_mfFixedBuyingChangeParams.setValidStartDate(
                WEB3DateUtility.getDate("20081010", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingChangeParams.setChangeDiv(WEB3ChangeDivDef.CHANGE);
            l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(100);
            l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(50);
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            l_mfFixedBuyingChangeParams.setFinDrawIncreaseBuyAmount(100);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setProductId(123);
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("123");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("test1");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setProductId(124);
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("1234");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("test2");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(32768);
            TestDBUtility.insertWithDel(l_productParams);
            l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123);
            TestDBUtility.insertWithDel(l_productParams);
            l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(124);
            TestDBUtility.insertWithDel(l_productParams);

            SubAccount l_subAccount = l_impl.getSubAccount();
            String l_strBranchCode =
                l_subAccount.getMainAccount().getBranch().getBranchCode();
            String l_strAccountCode =
                l_subAccount.getMainAccount().getAccountCode();
            String l_strInstitutionCode =
                l_subAccount.getInstitution().getInstitutionCode();

            String l_strQueryString = " status in (?, ?) ";
            Object[] l_objQueryValues = new Object[]{
                WEB3MFStatusDef.SONAR_NOT_SEND,
                WEB3MFStatusDef.SONAR_SENDED};
            List l_lisFixedBuyConditionChangeList =
                l_mutualFixedBuyCommonServiceImpl.getFixedBuyConditionChangeList(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode,
                    l_strQueryString,
                    l_objQueryValues);

            WEB3MutualFixedBuyConditionUnit l_mutualFixedBuyConditionUnit =
                new WEB3MutualFixedBuyConditionUnit();
            l_mutualFixedBuyConditionUnit.categoryCode = "123";
            l_mutualFixedBuyConditionUnit.checkResult = "0";
            l_mutualFixedBuyConditionUnit.debitAccountYM =
                WEB3DateUtility.getDate("20081012", WEB3GentradeTimeDef.DATE_FORMAT_YMD);
            l_mutualFixedBuyConditionUnit.definiteIncreaseBuyAmount = "100";
            l_mutualFixedBuyConditionUnit.displayOrder = "1";
            l_mutualFixedBuyConditionUnit.increaseBuyAmount = "100";
            l_mutualFixedBuyConditionUnit.monthlyBuyAmount = "150";
            l_mutualFixedBuyConditionUnit.mutualProductCode = "123";
            l_mutualFixedBuyConditionUnit.mutualProductName = "CASE";
            l_mutualFixedBuyConditionUnit.suspensionFlag = true;
            l_mutualFixedBuyConditionUnit.updateDate =
                WEB3DateUtility.getDate("20081020", WEB3GentradeTimeDef.DATE_FORMAT_YMD);
            l_mutualFixedBuyConditionUnit.validStartDate =
                WEB3DateUtility.getDate("20081011", WEB3GentradeTimeDef.DATE_FORMAT_YMD);

            WEB3MutualFixedBuyConditionUnit[] l_conditionUnits =
                new WEB3MutualFixedBuyConditionUnit[1];
            l_conditionUnits[0] = l_mutualFixedBuyConditionUnit;

            WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits =
                l_impl.mergeMutualFixedBuyConditionThreeMonth(
                    l_institution,
                    l_conditionUnits,
                    l_lisFixedBuyConditionChangeList);

            assertEquals(l_mutualFixedBuyConditionUnits.length, 2);

            assertEquals(l_mutualFixedBuyConditionUnits[0].monthlyBuyAmount, "150");
            assertEquals(l_mutualFixedBuyConditionUnits[0].increaseBuyAmount, "100");
            int l_l_intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].validStartDate,
                WEB3DateUtility.getDate("20081011", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_l_intCompreResult, 0);
            l_l_intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].debitAccountYM,
                WEB3DateUtility.getDate("20081112", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_l_intCompreResult, 0);
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductCode, "123");
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductName, "CASE");
            l_l_intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].updateDate,
                WEB3DateUtility.getDate("20081020", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_l_intCompreResult, 0);
            assertEquals(l_mutualFixedBuyConditionUnits[0].checkResult, "0");
            assertTrue(l_mutualFixedBuyConditionUnits[0].suspensionFlag);


            assertEquals(l_mutualFixedBuyConditionUnits[1].monthlyBuyAmount, "50");
            assertEquals(l_mutualFixedBuyConditionUnits[1].increaseBuyAmount, "100");
            l_l_intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[1].validStartDate,
                WEB3DateUtility.getDate("20081010", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_l_intCompreResult, 0);
            l_l_intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[1].debitAccountYM,
                WEB3DateUtility.getDate("20081010", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_l_intCompreResult, 0);
            assertEquals(l_mutualFixedBuyConditionUnits[1].definiteIncreaseBuyAmount, "100");
            assertEquals(l_mutualFixedBuyConditionUnits[1].mutualProductCode, "1234");
            assertEquals(l_mutualFixedBuyConditionUnits[1].mutualProductName, "test2");
            assertNull(l_mutualFixedBuyConditionUnits[1].updateDate);
            assertNull(l_mutualFixedBuyConditionUnits[1].checkResult);
            assertFalse(l_mutualFixedBuyConditionUnits[1].suspensionFlag);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testMergeMutualFixedBuyConditionThreeMonth_C0007()
    {
        final String STR_METHOD_NAME = "testMergeMutualFixedBuyConditionThreeMonth_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionListServiceImpl l_impl = new WEB3MutualFixedBuyConditionListServiceImpl();

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123));
        
        WEB3MutualFixedBuyCommonServiceImpl l_mutualFixedBuyCommonServiceImpl = 
            new WEB3MutualFixedBuyCommonServiceImpl();
        
        try
        {
            TestDBUtility.deleteAll(InsiderParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            Institution l_institution = new InstitutionImpl(l_institutionParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(123);
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(1234);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            

            SubAccount l_subAccount1 = new SubAccountImpl(l_subAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "getSubAccount",
                new Class[]{ SubAccountTypeEnum.class },
                l_subAccount1);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
            MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("1");
            l_mfFixedBuyingChangeParams.setProductCode("1234");
            l_mfFixedBuyingChangeParams.setValidStartDate(
                WEB3DateUtility.getDate("20081010", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingChangeParams.setChangeDiv(WEB3ChangeDivDef.RELEASE);
            l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(100);
            l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(50);
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            l_mfFixedBuyingChangeParams.setFinDrawIncreaseBuyAmount(null);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setProductId(123);
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("123");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("test1");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setProductId(124);
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("1234");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("test2");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(32768);
            TestDBUtility.insertWithDel(l_productParams);
            l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123);
            TestDBUtility.insertWithDel(l_productParams);
            l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(124);
            TestDBUtility.insertWithDel(l_productParams);

            SubAccount l_subAccount = l_impl.getSubAccount();
            String l_strBranchCode =
                l_subAccount.getMainAccount().getBranch().getBranchCode();
            String l_strAccountCode =
                l_subAccount.getMainAccount().getAccountCode();
            String l_strInstitutionCode =
                l_subAccount.getInstitution().getInstitutionCode();

            String l_strQueryString = " status in (?, ?) ";
            Object[] l_objQueryValues = new Object[]{
                WEB3MFStatusDef.SONAR_NOT_SEND,
                WEB3MFStatusDef.SONAR_SENDED};
            List l_lisFixedBuyConditionChangeList =
                l_mutualFixedBuyCommonServiceImpl.getFixedBuyConditionChangeList(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode,
                    l_strQueryString,
                    l_objQueryValues);

            WEB3MutualFixedBuyConditionUnit l_mutualFixedBuyConditionUnit =
                new WEB3MutualFixedBuyConditionUnit();
            l_mutualFixedBuyConditionUnit.categoryCode = "123";
            l_mutualFixedBuyConditionUnit.checkResult = "0";
            l_mutualFixedBuyConditionUnit.debitAccountYM =
                WEB3DateUtility.getDate("20081012", WEB3GentradeTimeDef.DATE_FORMAT_YMD);
            l_mutualFixedBuyConditionUnit.definiteIncreaseBuyAmount = "100";
            l_mutualFixedBuyConditionUnit.displayOrder = "1";
            l_mutualFixedBuyConditionUnit.increaseBuyAmount = "100";
            l_mutualFixedBuyConditionUnit.monthlyBuyAmount = "150";
            l_mutualFixedBuyConditionUnit.mutualProductCode = "123";
            l_mutualFixedBuyConditionUnit.mutualProductName = "CASE";
            l_mutualFixedBuyConditionUnit.suspensionFlag = true;
            l_mutualFixedBuyConditionUnit.updateDate = null;
            l_mutualFixedBuyConditionUnit.validStartDate =
                WEB3DateUtility.getDate("20081011", WEB3GentradeTimeDef.DATE_FORMAT_YMD);

            WEB3MutualFixedBuyConditionUnit[] l_conditionUnits =
                new WEB3MutualFixedBuyConditionUnit[1];
            l_conditionUnits[0] = l_mutualFixedBuyConditionUnit;

            WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits =
                l_impl.mergeMutualFixedBuyConditionThreeMonth(
                    l_institution,
                    l_conditionUnits,
                    l_lisFixedBuyConditionChangeList);

            assertEquals(l_mutualFixedBuyConditionUnits.length, 1);

            assertEquals(l_mutualFixedBuyConditionUnits[0].monthlyBuyAmount, "150");
            assertEquals(l_mutualFixedBuyConditionUnits[0].increaseBuyAmount, "100");
            int l_intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].validStartDate,
                WEB3DateUtility.getDate("20081011", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_intCompreResult, 0);
            l_intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].debitAccountYM,
                WEB3DateUtility.getDate("20081112", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_intCompreResult, 0);
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductCode, "123");
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductName, "CASE");
            assertNull(l_mutualFixedBuyConditionUnits[0].updateDate);
            assertEquals(l_mutualFixedBuyConditionUnits[0].checkResult, "0");
            assertTrue(l_mutualFixedBuyConditionUnits[0].suspensionFlag);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testMergeMutualFixedBuyConditionThreeMonth_C0008()
    {
        final String STR_METHOD_NAME = "testMergeMutualFixedBuyConditionThreeMonth_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3MutualFixedBuyConditionListServiceImpl l_impl = new WEB3MutualFixedBuyConditionListServiceImpl();

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123));
        
        WEB3MutualFixedBuyCommonServiceImpl l_mutualFixedBuyCommonServiceImpl = 
            new WEB3MutualFixedBuyCommonServiceImpl();
        
        try
        {
            TestDBUtility.deleteAll(InsiderParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_institutionParams);

            Institution l_institution = new InstitutionImpl(l_institutionParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountId(123);
            l_subAccountParams.setAccountId(123);
            l_subAccountParams.setInstitutionId(123);
            l_subAccountParams.setBranchId(1234);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            

            SubAccount l_subAccount1 = new SubAccountImpl(l_subAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeMainAccount",
                "getSubAccount",
                new Class[]{ SubAccountTypeEnum.class },
                l_subAccount1);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountCode("123");
            l_mainAccountParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(MfFixedBuyingChangeRow.TYPE);
            MfFixedBuyingChangeParams l_mfFixedBuyingChangeParams =
                TestDBUtility.getMfFixedBuyingChangeRow();
            l_mfFixedBuyingChangeParams.setAccountCode("123");
            l_mfFixedBuyingChangeParams.setStatus("1");
            l_mfFixedBuyingChangeParams.setProductCode("1234");
            l_mfFixedBuyingChangeParams.setValidStartDate(
                WEB3DateUtility.getDate("20081010", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            l_mfFixedBuyingChangeParams.setChangeDiv(WEB3ChangeDivDef.TEMP_STOP);
            l_mfFixedBuyingChangeParams.setIncreaseBuyAmount(100);
            l_mfFixedBuyingChangeParams.setMonthlyBuyAmount(50);
            l_mfFixedBuyingChangeParams.setDeleteFlag(BooleanEnum.FALSE);
            l_mfFixedBuyingChangeParams.setFinDrawIncreaseBuyAmount(null);
            TestDBUtility.insertWithDel(l_mfFixedBuyingChangeParams);

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setProductId(123);
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("123");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("test1");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setProductId(124);
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("1234");
            l_mutualFundProductParams.setProductIssueCode("0");
            l_mutualFundProductParams.setStandardName("test2");
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(32768);
            TestDBUtility.insertWithDel(l_productParams);
            l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123);
            TestDBUtility.insertWithDel(l_productParams);
            l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(124);
            TestDBUtility.insertWithDel(l_productParams);

            SubAccount l_subAccount = l_impl.getSubAccount();
            String l_strBranchCode =
                l_subAccount.getMainAccount().getBranch().getBranchCode();
            String l_strAccountCode =
                l_subAccount.getMainAccount().getAccountCode();
            String l_strInstitutionCode =
                l_subAccount.getInstitution().getInstitutionCode();

            String l_strQueryString = " status in (?, ?) ";
            Object[] l_objQueryValues = new Object[]{
                WEB3MFStatusDef.SONAR_NOT_SEND,
                WEB3MFStatusDef.SONAR_SENDED};
            List l_lisFixedBuyConditionChangeList =
                l_mutualFixedBuyCommonServiceImpl.getFixedBuyConditionChangeList(
                    l_strInstitutionCode,
                    l_strBranchCode,
                    l_strAccountCode,
                    l_strQueryString,
                    l_objQueryValues);

            WEB3MutualFixedBuyConditionUnit l_mutualFixedBuyConditionUnit =
                new WEB3MutualFixedBuyConditionUnit();
            l_mutualFixedBuyConditionUnit.categoryCode = "123";
            l_mutualFixedBuyConditionUnit.checkResult = "0";
            l_mutualFixedBuyConditionUnit.debitAccountYM =
                WEB3DateUtility.getDate("20081012", WEB3GentradeTimeDef.DATE_FORMAT_YMD);
            l_mutualFixedBuyConditionUnit.definiteIncreaseBuyAmount = "100";
            l_mutualFixedBuyConditionUnit.displayOrder = "1";
            l_mutualFixedBuyConditionUnit.increaseBuyAmount = "100";
            l_mutualFixedBuyConditionUnit.monthlyBuyAmount = "150";
            l_mutualFixedBuyConditionUnit.mutualProductCode = "123";
            l_mutualFixedBuyConditionUnit.mutualProductName = "CASE";
            l_mutualFixedBuyConditionUnit.suspensionFlag = true;
            l_mutualFixedBuyConditionUnit.updateDate = null;
            l_mutualFixedBuyConditionUnit.validStartDate =
                WEB3DateUtility.getDate("20081011", WEB3GentradeTimeDef.DATE_FORMAT_YMD);

            WEB3MutualFixedBuyConditionUnit[] l_conditionUnits =
                new WEB3MutualFixedBuyConditionUnit[1];
            l_conditionUnits[0] = l_mutualFixedBuyConditionUnit;

            WEB3MutualFixedBuyConditionUnit[] l_mutualFixedBuyConditionUnits =
                l_impl.mergeMutualFixedBuyConditionThreeMonth(
                    l_institution,
                    l_conditionUnits,
                    l_lisFixedBuyConditionChangeList);

            assertEquals(l_mutualFixedBuyConditionUnits.length, 1);

            assertEquals(l_mutualFixedBuyConditionUnits[0].monthlyBuyAmount, "150");
            assertEquals(l_mutualFixedBuyConditionUnits[0].increaseBuyAmount, "100");
            int l_intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].validStartDate,
                WEB3DateUtility.getDate("20081011", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_intCompreResult, 0);
            l_intCompreResult = WEB3DateUtility.compareToDay(
                l_mutualFixedBuyConditionUnits[0].debitAccountYM,
                WEB3DateUtility.getDate("20081112", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_intCompreResult, 0);
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductCode, "123");
            assertEquals(l_mutualFixedBuyConditionUnits[0].mutualProductName, "CASE");
            assertNull(l_mutualFixedBuyConditionUnits[0].updateDate);
            assertEquals(l_mutualFixedBuyConditionUnits[0].checkResult, "0");
            assertTrue(l_mutualFixedBuyConditionUnits[0].suspensionFlag);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
