head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.10.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualOrderReferenceServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託注文照会サービス実装クラス(WEB3MutualOrderReferenceServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/14 徐大方 (中訊) 新規作成                   
*/

package webbroker3.mf.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;

import test.util.TestDBUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mf.define.WEB3MFSortkeyItemDef;
import webbroker3.mf.define.WEB3MutualFrgnMmfDisplayDivDef;
import webbroker3.mf.define.WEB3ReferenceDivDef;
import webbroker3.mf.message.WEB3MutualOrderReferenceRequest;
import webbroker3.mf.message.WEB3MutualOrderReferenceResponse;
import webbroker3.mf.message.WEB3MutualSortKey;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * 投資信託注文照会サービス実装クラス<BR>
 * 
 * @@author 徐大方(中訊)
 * @@version 1.0
 */
public class WEB3MutualOrderReferenceServiceImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualOrderReferenceServiceImplTest.class);

    public WEB3MutualOrderReferenceServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestDBUtility.deleteAll(MainAccountRow.TYPE);
        TestDBUtility.deleteAll(SubAccountRow.TYPE);
        TestDBUtility.deleteAll(MutualFundOrderUnitRow.TYPE);
        TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
        TestDBUtility.deleteAll(InstitutionRow.TYPE);
        TestDBUtility.deleteAll(TradingTimeRow.TYPE);
        TestDBUtility.deleteAll(TraderRow.TYPE);
        TestDBUtility.deleteAll(MarketRow.TYPE);
        TestDBUtility.deleteAll(BranchRow.TYPE);
        TestDBUtility.deleteAll(ProductRow.TYPE);
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getAccountId",
            new Class[] {},new Long(333812512246L));
        LoginInfoImplForMock l_loginInfo = new LoginInfoImplForMock();
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getLoginInfo",
            new Class[] {},
            l_loginInfo);

        WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
        l_clendarContext.setInstitutionCode("0D");
        l_clendarContext.setBranchCode("381");
        l_clendarContext.setMarketCode("SP");
        l_clendarContext.setProductCode("12");
        l_clendarContext.setTradingTimeType("01");
        l_clendarContext.setOrderAcceptProduct("01");
        l_clendarContext.setBizDateType("1");

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_clendarContext);

        Calendar l_calendar = Calendar.getInstance();
        l_calendar.set(Calendar.HOUR_OF_DAY,15);
        l_calendar.set(Calendar.MINUTE,00);
        l_calendar.set(Calendar.SECOND,01);

        Timestamp l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_tsBizDate);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testExecuteCase1()
    {
        final String STR_METHOD_NAME = "testExecuteCase1()";
        log.entering(STR_METHOD_NAME);

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        l_subAccountParams.setAccountId(333812512246L);
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        MutualFundOrderUnitParams l_mutualFundOrderUnitParams = TestDBUtility.getMutualFundOrderUnitRow();
        l_mutualFundOrderUnitParams.setAccountId(333812512246L);
        l_mutualFundOrderUnitParams.setSubAccountId(33381251220301L);
        l_mutualFundOrderUnitParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
        l_mutualFundOrderUnitParams.setBizDate("20040716");
        l_mutualFundOrderUnitParams.setExecStatus("1");
        l_mutualFundOrderUnitParams.setTaxType(TaxTypeEnum.NORMAL);
        l_mutualFundOrderUnitParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
        l_mutualFundOrderUnitParams.setCalcConstantValue(222);
        MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
        l_mutualFundProductParams.setProductId(4003000900000000L);
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("381");
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setMarketCode("SP");
        l_tradingTimeParams.setProductCode("12");
        TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams1.setInstitutionCode("0D");
        l_tradingTimeParams1.setBranchCode("381");
        l_tradingTimeParams1.setTradingTimeType("01");
        l_tradingTimeParams1.setBizDateType("1");
        l_tradingTimeParams1.setMarketCode("SP");
        l_tradingTimeParams1.setProductCode("0001000");
        TraderParams l_traderParams = TestDBUtility.getTraderRow();
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(4003000900000000L);

        try
        {
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mutualFundOrderUnitParams);
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            TestDBUtility.insertWithDel(l_traderParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_productParams);
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        WEB3MutualOrderReferenceServiceImpl l_impl = new WEB3MutualOrderReferenceServiceImpl();
        WEB3MutualOrderReferenceRequest l_request = new WEB3MutualOrderReferenceRequest();
        l_request.referenceType = WEB3ReferenceDivDef.ORDER_REFERENCE;
        l_request.pageIndex = "1";
        l_request.pageSize = "5";
        WEB3MutualSortKey[] l_sortKeys = new WEB3MutualSortKey[1];
        WEB3MutualSortKey l_sortKey = new WEB3MutualSortKey();
        l_sortKey.keyItem = WEB3MFSortkeyItemDef.TAX_TYPE;
        l_sortKey.ascDesc = WEB3AscDescDef.ASC;
        l_sortKeys[0] = l_sortKey;
        l_request.sortKeys = l_sortKeys;
        l_request.mutualFrgnMmfDisplayDiv = WEB3MutualFrgnMmfDisplayDivDef.FRGN_MMF;
        try
        {
            WEB3MutualOrderReferenceResponse l_response= (WEB3MutualOrderReferenceResponse)l_impl.execute(l_request);
            log.debug(l_response.mutualOrderGroups[0].taxType);
            assertEquals("0", l_response.mutualOrderGroups[0].taxType);
//            System.out.println(l_response.mutualOrderGroups[0].frgnMmfFlag);
            assertTrue(l_response.mutualOrderGroups[0].frgnMmfFlag);
            log.debug(l_response.mutualOrderGroups[0].constantValue);
            assertEquals("222", l_response.mutualOrderGroups[0].constantValue);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testExecuteCase2()
    {
        final String STR_METHOD_NAME = "testExecuteCase2()";
        log.entering(STR_METHOD_NAME);

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        l_subAccountParams.setAccountId(333812512246L);
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        MutualFundOrderUnitParams l_mutualFundOrderUnitParams = TestDBUtility.getMutualFundOrderUnitRow();
        l_mutualFundOrderUnitParams.setAccountId(333812512246L);
        l_mutualFundOrderUnitParams.setSubAccountId(33381251220301L);
        Date l_dateBizDate =
            GtlUtils.getTradingSystem().getBizDate();
        SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
        String l_strBizDate = l_format.format(l_dateBizDate);
        l_mutualFundOrderUnitParams.setBizDate(l_strBizDate);
        l_mutualFundOrderUnitParams.setExecStatus("1");
        l_mutualFundOrderUnitParams.setTaxType(TaxTypeEnum.SPECIAL);
        l_mutualFundOrderUnitParams.setFundType(MutualFundTypeEnum.FOREIGN);
        MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
        l_mutualFundProductParams.setProductId(4003000900000000L);
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("381");
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setMarketCode("SP");
        l_tradingTimeParams.setProductCode("12");
        TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams1.setInstitutionCode("0D");
        l_tradingTimeParams1.setBranchCode("381");
        l_tradingTimeParams1.setTradingTimeType("01");
        l_tradingTimeParams1.setBizDateType("1");
        l_tradingTimeParams1.setMarketCode("SP");
        l_tradingTimeParams1.setProductCode("0001000");
        TraderParams l_traderParams = TestDBUtility.getTraderRow();
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(4003000900000000L);
        try
        {
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mutualFundOrderUnitParams);
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            TestDBUtility.insertWithDel(l_traderParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_productParams);
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        WEB3MutualOrderReferenceServiceImpl l_impl = new WEB3MutualOrderReferenceServiceImpl();
        WEB3MutualOrderReferenceRequest l_request = new WEB3MutualOrderReferenceRequest();
        l_request.referenceType = WEB3ReferenceDivDef.ORDER_REFERENCE;
        l_request.pageIndex = "1";
        l_request.pageSize = "5";
        WEB3MutualSortKey[] l_sortKeys = new WEB3MutualSortKey[1];
        WEB3MutualSortKey l_sortKey = new WEB3MutualSortKey();
        l_sortKey.keyItem = WEB3MFSortkeyItemDef.TAX_TYPE;
        l_sortKey.ascDesc = WEB3AscDescDef.ASC;
        l_sortKeys[0] = l_sortKey;
        l_request.sortKeys = l_sortKeys;
        try
        {
            WEB3MutualOrderReferenceResponse l_response= (WEB3MutualOrderReferenceResponse)l_impl.execute(l_request);
            log.debug(l_response.mutualOrderGroups[0].taxType);
            assertEquals("1", l_response.mutualOrderGroups[0].taxType);
//            System.out.println(l_response.mutualOrderGroups[0].frgnMmfFlag);
            assertFalse(l_response.mutualOrderGroups[0].frgnMmfFlag);
            assertNull(l_response.mutualOrderGroups[0].constantValue);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testExecuteCase3()
    {
        final String STR_METHOD_NAME = "testExecuteCase3()";
        log.entering(STR_METHOD_NAME);

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        l_subAccountParams.setAccountId(333812512246L);
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        MutualFundOrderUnitParams l_mutualFundOrderUnitParams = TestDBUtility.getMutualFundOrderUnitRow();
        l_mutualFundOrderUnitParams.setAccountId(333812512246L);
        l_mutualFundOrderUnitParams.setSubAccountId(33381251220301L);
        Date l_dateBizDate =
            GtlUtils.getTradingSystem().getBizDate();
        SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
        String l_strBizDate = l_format.format(l_dateBizDate);
        l_mutualFundOrderUnitParams.setBizDate(l_strBizDate);
        l_mutualFundOrderUnitParams.setExecStatus("1");
        l_mutualFundOrderUnitParams.setTaxType(TaxTypeEnum.UNDEFINED);
        l_mutualFundOrderUnitParams.setFundType(MutualFundTypeEnum.FOREIGN);
        MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
        l_mutualFundProductParams.setProductId(4003000900000000L);
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("381");
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setMarketCode("SP");
        l_tradingTimeParams.setProductCode("12");
        TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams1.setInstitutionCode("0D");
        l_tradingTimeParams1.setBranchCode("381");
        l_tradingTimeParams1.setTradingTimeType("01");
        l_tradingTimeParams1.setBizDateType("1");
        l_tradingTimeParams1.setMarketCode("SP");
        l_tradingTimeParams1.setProductCode("0001000");
        TraderParams l_traderParams = TestDBUtility.getTraderRow();
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(4003000900000000L);
        try
        {
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mutualFundOrderUnitParams);
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            TestDBUtility.insertWithDel(l_traderParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_productParams);
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        WEB3MutualOrderReferenceServiceImpl l_impl = new WEB3MutualOrderReferenceServiceImpl();
        WEB3MutualOrderReferenceRequest l_request = new WEB3MutualOrderReferenceRequest();
        l_request.referenceType = WEB3ReferenceDivDef.ORDER_REFERENCE;
        l_request.pageIndex = "1";
        l_request.pageSize = "5";
        WEB3MutualSortKey[] l_sortKeys = new WEB3MutualSortKey[1];
        WEB3MutualSortKey l_sortKey = new WEB3MutualSortKey();
        l_sortKey.keyItem = WEB3MFSortkeyItemDef.TAX_TYPE;
        l_sortKey.ascDesc = WEB3AscDescDef.ASC;
        l_sortKeys[0] = l_sortKey;
        l_request.sortKeys = l_sortKeys;
        try
        {
            WEB3MutualOrderReferenceResponse l_response= (WEB3MutualOrderReferenceResponse)l_impl.execute(l_request);
            log.debug(l_response.mutualOrderGroups[0].taxType);
            assertEquals("2", l_response.mutualOrderGroups[0].taxType);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testExecuteCase4()
    {
        final String STR_METHOD_NAME = "testExecuteCase4()";
        log.entering(STR_METHOD_NAME);

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        l_subAccountParams.setAccountId(333812512246L);
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        MutualFundOrderUnitParams l_mutualFundOrderUnitParams = TestDBUtility.getMutualFundOrderUnitRow();
        l_mutualFundOrderUnitParams.setAccountId(333812512246L);
        l_mutualFundOrderUnitParams.setSubAccountId(33381251220301L);
        Date l_dateBizDate =
            GtlUtils.getTradingSystem().getBizDate();
        SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
        String l_strBizDate = l_format.format(l_dateBizDate);
        l_mutualFundOrderUnitParams.setBizDate(l_strBizDate);
        l_mutualFundOrderUnitParams.setExecStatus("1");
        l_mutualFundOrderUnitParams.setTaxType(TaxTypeEnum.STOCK_OPTION);
        l_mutualFundOrderUnitParams.setFundType(MutualFundTypeEnum.FOREIGN);
        MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
        l_mutualFundProductParams.setProductId(4003000900000000L);
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("381");
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setMarketCode("SP");
        l_tradingTimeParams.setProductCode("12");
        TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams1.setInstitutionCode("0D");
        l_tradingTimeParams1.setBranchCode("381");
        l_tradingTimeParams1.setTradingTimeType("01");
        l_tradingTimeParams1.setBizDateType("1");
        l_tradingTimeParams1.setMarketCode("SP");
        l_tradingTimeParams1.setProductCode("0001000");
        TraderParams l_traderParams = TestDBUtility.getTraderRow();
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(4003000900000000L);
        try
        {
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mutualFundOrderUnitParams);
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            TestDBUtility.insertWithDel(l_traderParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_productParams);
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        WEB3MutualOrderReferenceServiceImpl l_impl = new WEB3MutualOrderReferenceServiceImpl();
        WEB3MutualOrderReferenceRequest l_request = new WEB3MutualOrderReferenceRequest();
        l_request.referenceType = WEB3ReferenceDivDef.ORDER_REFERENCE;
        l_request.pageIndex = "1";
        l_request.pageSize = "5";
        WEB3MutualSortKey[] l_sortKeys = new WEB3MutualSortKey[1];
        WEB3MutualSortKey l_sortKey = new WEB3MutualSortKey();
        l_sortKey.keyItem = WEB3MFSortkeyItemDef.TAX_TYPE;
        l_sortKey.ascDesc = WEB3AscDescDef.ASC;
        l_sortKeys[0] = l_sortKey;
        l_request.sortKeys = l_sortKeys;
        try
        {
            WEB3MutualOrderReferenceResponse l_response= (WEB3MutualOrderReferenceResponse)l_impl.execute(l_request);
            assertNull(l_response.mutualOrderGroups[0].taxType);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
