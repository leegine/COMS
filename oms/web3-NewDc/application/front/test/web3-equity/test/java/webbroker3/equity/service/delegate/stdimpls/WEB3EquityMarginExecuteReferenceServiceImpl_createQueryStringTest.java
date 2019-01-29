head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.57.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityMarginExecuteReferenceServiceImpl_createQueryStringTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : (WEB3EquityMarginExecuteReferenceServiceImplTest.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2007/01/11  吉麗ナ(中訊)　@新規作成
 */

package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderManager;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeOrderUnitImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3EquityPTSMarketDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3MarketPreferencesNameDef;
import webbroker3.common.define.WEB3RepaymentDivDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.message.WEB3EquityMarginSortKey;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityMarginExecuteReferenceServiceImpl.OrderStatus;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeBranchForMock;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.AccountProductOrderStopParams;
import webbroker3.gentrade.data.AccountProductOrderStopRow;
import webbroker3.gentrade.data.BranchMarketDealtCondRow;
import webbroker3.gentrade.data.BranchMarketPtsDealtCondParams;
import webbroker3.gentrade.data.BranchMarketRepayDealtCondParams;
import webbroker3.gentrade.data.CalendarRow;
import webbroker3.gentrade.data.OrderAcceptStatusParams;
import webbroker3.gentrade.data.OrderexecutionEndRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.mock.util.WEB3MockObjectRuntimeException;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * @@author ji-lina
 *
 */
public class WEB3EquityMarginExecuteReferenceServiceImpl_createQueryStringTest extends TestBaseForMock
{
    
    public static String l_strPath = "application/front/test/web3-equity/java";

    public WEB3EquityMarginExecuteReferenceServiceImpl_createQueryStringTest(String name)
    {
        super(name);
    }
    WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();

    protected void setUp() throws Exception
    {

        super.setUp();
        EqtypeOrderUnitParams l_eqtypeOrderUnitParams =new EqtypeOrderUnitParams();
        QueryProcessor l_processor = Processors.getDefaultProcessor();
        OrderAcceptStatusParams l_orderAcceptStatusParams =new OrderAcceptStatusParams();
        l_processor.doDeleteAllQuery(l_orderAcceptStatusParams.getRowType());


        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_processor.doDeleteAllQuery(l_tradingTimeParams.getRowType());
        l_processor.doDeleteAllQuery(l_eqtypeOrderUnitParams.getRowType());
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

        l_clendarContext.setInstitutionCode("0D");
        l_clendarContext.setBranchCode("381");
        l_clendarContext.setProductCode("12");
        l_clendarContext.setTradingTimeType("01");
        l_clendarContext.setOrderAcceptProduct("01");
        l_clendarContext.setBizDateType("1");
        l_clendarContext.setMarketCode("SP");

        WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_clendarContext);

        Calendar l_calendar = Calendar.getInstance();
        l_calendar.set(Calendar.HOUR_OF_DAY, 15);
        l_calendar.set(Calendar.MINUTE, 00);
        l_calendar.set(Calendar.SECOND, 01);

        Timestamp l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_tsBizDate);
        //accountId= 1906, SubAccount Id : 1906
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        l_subAccountParams.setSubAccountId(1906);
        l_subAccountParams.setAccountId(1906);
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        TestDBUtility.insertWithDel(l_subAccountParams);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3EquityMarginExecuteReferenceServiceImpl_createQueryStringTest.class);

    /**
     * testCreateQueryString_C0001<BR>
     *
     */
    public void testCreateQueryString_C0001()
    {
        //log
        final String STR_METHOD_NAME = "testCreateQueryString_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        EqtypeOrderUnitParams l_eqtypeOrderUnitParams = getEqtypeOrderUnitRow();

        //発注日
        Date l_orderBizDate = WEB3DateUtility.getDate("20070101","yyyyMMdd");

        String l_productCode = null;

        String l_marketCode = null;

        //株式・信用注文約定照会サービスImpl
        WEB3EquityMarginExecuteReferenceServiceImpl l_equityMarginExecuteReferenceServiceImpl =
            new WEB3EquityMarginExecuteReferenceServiceImpl();

        //注文受付状態
        OrderStatus l_orderStatus = l_equityMarginExecuteReferenceServiceImpl.new OrderStatus();

        l_orderStatus.interestEquityFlag = true;
        // create検索条件文字列
        String l_strSearchCond;
        try
        {
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setOrderUnitId(1907);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070102","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setOrderUnitId(1908);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070103","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            //create検索条件文字列
            l_strSearchCond = l_equityMarginExecuteReferenceServiceImpl.createQueryString(
                l_productCode,
                l_marketCode,
                l_orderBizDate,
                l_orderStatus,
                null);
            log.debug(l_strSearchCond);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            WEB3GentradeSubAccount l_subAccount;
            l_subAccount = (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(1906, 1906);

            //ソートキー:発注日 ASC
            WEB3EquityMarginSortKey[] l_sortKeys = new WEB3EquityMarginSortKey[1];

            l_sortKeys[0] = new WEB3EquityMarginSortKey();
            l_sortKeys[0].keyItem = "orderBizDate";
            l_sortKeys[0].ascDesc = "A";
            // create検索条件データコンテナ
            Object[] l_searchCondContainers =
                l_equityMarginExecuteReferenceServiceImpl.createQueryDataContainer(l_subAccount, l_productCode,
                    l_marketCode, l_orderBizDate, l_orderStatus,null);

            // createソート条件
            String l_strSortCond = l_equityMarginExecuteReferenceServiceImpl.createSortCond(l_sortKeys);
            log.debug(l_strSortCond);

            //doFindAllQuery(
            //final String tableName, final String where, final String oderby,
            //final String conditions, final Object bindVars[])
            List l_result = Processors.getDefaultProcessor().doFindAllQuery(
                EqtypeOrderUnitRow.TYPE, l_strSearchCond, l_strSortCond, null, l_searchCondContainers);

            assertEquals(l_result.size(), 3);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(0)).getOrderUnitId(), 1906);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(1)).getOrderUnitId(), 1907);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(2)).getOrderUnitId(), 1908);
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

    }

    /**
     * testCreateQueryString_C0002<BR>
     *
     */
    public void testCreateQueryString_C0002()
    {
        //log
        final String STR_METHOD_NAME = "testCreateQueryString_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        EqtypeOrderUnitParams l_eqtypeOrderUnitParams = getEqtypeOrderUnitRow();

        //発注日
        Date l_orderBizDate = null;

        String l_productCode = null;

        String l_marketCode = null;

        //株式・信用注文約定照会サービスImpl
        WEB3EquityMarginExecuteReferenceServiceImpl l_equityMarginExecuteReferenceServiceImpl =
            new WEB3EquityMarginExecuteReferenceServiceImpl();

        //注文受付状態
        OrderStatus l_orderStatus = l_equityMarginExecuteReferenceServiceImpl.new OrderStatus();

        l_orderStatus.interestEquityFlag = true;
        // create検索条件文字列
        String l_strSearchCond;
        try
        {
            Date l_datBusinessData = GtlUtils.getTradingSystem().getBizDate();
            l_eqtypeOrderUnitParams.setBizDate(WEB3DateUtility.formatDate(l_datBusinessData, "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setOrderUnitId(1907);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams.setBizDate("20070101");
            l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070102","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setOrderUnitId(1908);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams.setBizDate(WEB3DateUtility.formatDate(l_datBusinessData, "yyyyMMdd"));
            l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070103","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            //create検索条件文字列
            l_strSearchCond = l_equityMarginExecuteReferenceServiceImpl.createQueryString(
                l_productCode,
                l_marketCode,
                l_orderBizDate,
                l_orderStatus,
                null);
            log.debug(l_strSearchCond);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            WEB3GentradeSubAccount l_subAccount;
            l_subAccount = (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(1906, 1906);

            //ソートキー:発注日 ASC
            WEB3EquityMarginSortKey[] l_sortKeys = new WEB3EquityMarginSortKey[1];

            l_sortKeys[0] = new WEB3EquityMarginSortKey();
            l_sortKeys[0].keyItem = "orderBizDate";
            l_sortKeys[0].ascDesc = "A";
            // create検索条件データコンテナ
            Object[] l_searchCondContainers =
                l_equityMarginExecuteReferenceServiceImpl.createQueryDataContainer(l_subAccount, l_productCode,
                    l_marketCode, l_orderBizDate, l_orderStatus,null);

            // createソート条件
            String l_strSortCond = l_equityMarginExecuteReferenceServiceImpl.createSortCond(l_sortKeys);
            log.debug(l_strSortCond);
            //doFindAllQuery(
            //final String tableName, final String where, final String oderby,
            //final String conditions, final Object bindVars[])
            List l_result = Processors.getDefaultProcessor().doFindAllQuery(
                EqtypeOrderUnitRow.TYPE, l_strSearchCond, l_strSortCond, null, l_searchCondContainers);

            assertEquals(l_result.size(), 2);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(0)).getOrderUnitId(), 1906);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(1)).getOrderUnitId(), 1908);
            log.exiting(STR_METHOD_NAME);

        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

    }

    /**
     * testCreateQueryString_C0003<BR>
     *
     */
    public void testCreateQueryString_C0003()
    {
        //log
        final String STR_METHOD_NAME = "testCreateQueryString_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        EqtypeOrderUnitParams l_eqtypeOrderUnitParams = getEqtypeOrderUnitRow();

        //発注日
        Date l_orderBizDate = WEB3DateUtility.getDate("20070101","yyyyMMdd");

        String l_productCode = null;

        String l_marketCode = null;

        //株式・信用注文約定照会サービスImpl
        WEB3EquityMarginExecuteReferenceServiceImpl l_equityMarginExecuteReferenceServiceImpl =
            new WEB3EquityMarginExecuteReferenceServiceImpl();

        //注文受付状態
        OrderStatus l_orderStatus = l_equityMarginExecuteReferenceServiceImpl.new OrderStatus();

        //信用取引フラグ
        l_orderStatus.interestMarginFlag = true;
        // create検索条件文字列
        String l_strSearchCond;
        try
        {
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.CLOSE_MARGIN_LONG);
            l_eqtypeOrderUnitParams.setSonarTradedCode("51");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setOrderUnitId(1907);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.MARGIN_LONG);
            l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070102","yyyyMMdd"));
            l_eqtypeOrderUnitParams.setSonarTradedCode("52");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setOrderUnitId(1908);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.MARGIN_LONG);
            l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070103","yyyyMMdd"));
            l_eqtypeOrderUnitParams.setSonarTradedCode("51");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            //create検索条件文字列
            l_strSearchCond = l_equityMarginExecuteReferenceServiceImpl.createQueryString(
                l_productCode,
                l_marketCode,
                l_orderBizDate,
                l_orderStatus,
                null);
            log.debug(l_strSearchCond);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            WEB3GentradeSubAccount l_subAccount;
            l_subAccount = (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(1906, 1906);

            //ソートキー:発注日 ASC
            WEB3EquityMarginSortKey[] l_sortKeys = new WEB3EquityMarginSortKey[1];

            l_sortKeys[0] = new WEB3EquityMarginSortKey();
            l_sortKeys[0].keyItem = "tradingType";
            l_sortKeys[0].ascDesc = "A";
            // create検索条件データコンテナ
            Object[] l_searchCondContainers =
                l_equityMarginExecuteReferenceServiceImpl.createQueryDataContainer(l_subAccount, l_productCode,
                    l_marketCode, l_orderBizDate, l_orderStatus,null);

            // createソート条件
            String l_strSortCond = l_equityMarginExecuteReferenceServiceImpl.createSortCond(l_sortKeys);
            log.debug(l_strSortCond);

            //doFindAllQuery(
            //final String tableName, final String where, final String oderby, 
            //final String conditions, final Object bindVars[])
            List l_result = Processors.getDefaultProcessor().doFindAllQuery(
                EqtypeOrderUnitRow.TYPE, l_strSearchCond, l_strSortCond, null, l_searchCondContainers);

            assertEquals(l_result.size(), 3);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(0)).getOrderUnitId(), 1908);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(1)).getOrderUnitId(), 1907);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(2)).getOrderUnitId(), 1906);
            log.exiting(STR_METHOD_NAME);

        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

    }

    /**
     * testCreateQueryString_C0004<BR>
     *
     */
    public void testCreateQueryString_C0004()
    {
        //log
        final String STR_METHOD_NAME = "testCreateQueryString_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        EqtypeOrderUnitParams l_eqtypeOrderUnitParams = getEqtypeOrderUnitRow();

        //発注日
        Date l_orderBizDate = WEB3DateUtility.getDate("20070101","yyyyMMdd");

        String l_productCode = null;

        String l_marketCode = null;

        //株式・信用注文約定照会サービスImpl
        WEB3EquityMarginExecuteReferenceServiceImpl l_equityMarginExecuteReferenceServiceImpl =
            new WEB3EquityMarginExecuteReferenceServiceImpl();

        //注文受付状態
        OrderStatus l_orderStatus = l_equityMarginExecuteReferenceServiceImpl.new OrderStatus();

        l_orderStatus.offFloor = true;
        // create検索条件文字列
        String l_strSearchCond;
        try
        {
            l_eqtypeOrderUnitParams.setSonarTradedCode("16");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setOrderUnitId(1907);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070102","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setOrderUnitId(1908);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070103","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            //create検索条件文字列
            l_strSearchCond = l_equityMarginExecuteReferenceServiceImpl.createQueryString(
                l_productCode,
                l_marketCode,
                l_orderBizDate,
                l_orderStatus,null);
            log.debug(l_strSearchCond);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            WEB3GentradeSubAccount l_subAccount;
            l_subAccount = (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(1906, 1906);

            //ソートキー:発注日 ASC
            WEB3EquityMarginSortKey[] l_sortKeys = new WEB3EquityMarginSortKey[1];

            l_sortKeys[0] = new WEB3EquityMarginSortKey();
            l_sortKeys[0].keyItem = "orderBizDate";
            l_sortKeys[0].ascDesc = "A";
            // create検索条件データコンテナ
            Object[] l_searchCondContainers =
                l_equityMarginExecuteReferenceServiceImpl.createQueryDataContainer(l_subAccount, l_productCode,
                    l_marketCode, l_orderBizDate, l_orderStatus,null);

            // createソート条件
            String l_strSortCond = l_equityMarginExecuteReferenceServiceImpl.createSortCond(l_sortKeys);
            log.debug(l_strSortCond);

            //doFindAllQuery(
            //final String tableName, final String where, final String oderby,
            //final String conditions, final Object bindVars[])
            List l_result = Processors.getDefaultProcessor().doFindAllQuery(
                EqtypeOrderUnitRow.TYPE, l_strSearchCond, l_strSortCond, null, l_searchCondContainers);

            assertEquals(l_result.size(), 3);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(0)).getOrderUnitId(), 1906);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(1)).getOrderUnitId(), 1907);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(2)).getOrderUnitId(), 1908);
            log.exiting(STR_METHOD_NAME);

        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

    }

    /**
     * testCreateQueryString_C0005<BR>
     *
     */
    public void testCreateQueryString_C0005()
    {

        //log
        final String STR_METHOD_NAME = "testCreateQueryString_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        EqtypeOrderUnitParams l_eqtypeOrderUnitParams = getEqtypeOrderUnitRow();

        //発注日
        Date l_orderBizDate = WEB3DateUtility.getDate("20070101","yyyyMMdd");

        String l_productCode = "2099";

        String l_marketCode = "SP";

        //株式・信用注文約定照会サービスImpl
        WEB3EquityMarginExecuteReferenceServiceImpl l_equityMarginExecuteReferenceServiceImpl =
            new WEB3EquityMarginExecuteReferenceServiceImpl();

        //注文受付状態
        OrderStatus l_orderStatus = l_equityMarginExecuteReferenceServiceImpl.new OrderStatus();

        //立会外分売フラグ
        l_orderStatus.offFloor = true;
        // create検索条件文字列
        String l_strSearchCond;
        try
        {
            l_eqtypeOrderUnitParams.setSonarTradedCode("16");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setOrderUnitId(1907);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.SWAP_MARGIN_LONG);
            l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070102","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setOrderUnitId(1908);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.SWAP_MARGIN_SHORT);
            l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070103","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1906);
            TestDBUtility.insertWithDel(l_marketParams);

            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            mainAccountParams.setAccountId(1906);
            TestDBUtility.insertWithDel(mainAccountParams);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(1906);
            l_subAccountParams.setSubAccountId(1906);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1906);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            EqtypeProductParams l_eqtypeProductParams = getEqtypeProductRow();
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            l_productParams.setProductId(1906);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            //create検索条件文字列

            l_strSearchCond = l_equityMarginExecuteReferenceServiceImpl.createQueryString(
                l_productCode,
                l_marketCode,
                l_orderBizDate,
                l_orderStatus,
                null);
            log.debug(l_strSearchCond);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            WEB3GentradeSubAccount l_subAccount;
            l_subAccount = (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(1906, 1906);

            //ソートキー:発注日 ASC
            WEB3EquityMarginSortKey[] l_sortKeys = new WEB3EquityMarginSortKey[1];

            l_sortKeys[0] = new WEB3EquityMarginSortKey();
            l_sortKeys[0].keyItem = "orderBizDate";
            l_sortKeys[0].ascDesc = "A";
            // create検索条件データコンテナ
            Object[] l_searchCondContainers =
                l_equityMarginExecuteReferenceServiceImpl.createQueryDataContainer(l_subAccount, l_productCode,
                    l_marketCode, l_orderBizDate, l_orderStatus,null);

            // createソート条件
            String l_strSortCond = l_equityMarginExecuteReferenceServiceImpl.createSortCond(l_sortKeys);
            log.debug(l_strSortCond);

            //doFindAllQuery(
            //final String tableName, final String where, final String oderby,
            //final String conditions, final Object bindVars[])
            List l_result = Processors.getDefaultProcessor().doFindAllQuery(
                EqtypeOrderUnitRow.TYPE, l_strSearchCond, l_strSortCond, null, l_searchCondContainers);

            assertEquals(l_result.size(), 1);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(0)).getOrderUnitId(), 1906);
            log.exiting(STR_METHOD_NAME);

        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }


    }

    /**
     * testCreateQueryString_C0006<BR>
     *
     */
    public void testCreateQueryString_C0006()
    {
        //log
        final String STR_METHOD_NAME = "testCreateQueryString_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        EqtypeOrderUnitParams l_eqtypeOrderUnitParams = getEqtypeOrderUnitRow();

        //発注日
        Date l_orderBizDate = WEB3DateUtility.getDate("20070101","yyyyMMdd");

        String l_productCode = null;

        String l_marketCode = null;

        //株式・信用注文約定照会サービスImpl
        WEB3EquityMarginExecuteReferenceServiceImpl l_equityMarginExecuteReferenceServiceImpl =
            new WEB3EquityMarginExecuteReferenceServiceImpl();

        //注文受付状態
        OrderStatus l_orderStatus = l_equityMarginExecuteReferenceServiceImpl.new OrderStatus();

        l_orderStatus.interestEquityFlag = true;
        // create検索条件文字列
        String l_strSearchCond;
        try
        {
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setOrderUnitId(1907);
            l_eqtypeOrderUnitParams.setProductId(1909);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070102","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setProductId(1908);
            l_eqtypeOrderUnitParams.setOrderUnitId(1908);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070103","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            //create検索条件文字列
            l_strSearchCond = l_equityMarginExecuteReferenceServiceImpl.createQueryString(
                l_productCode,
                l_marketCode,
                l_orderBizDate,
                l_orderStatus,
                null);
            log.debug(l_strSearchCond);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            WEB3GentradeSubAccount l_subAccount;
            l_subAccount = (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(1906, 1906);

            //ソートキー:発注日 ASC
            WEB3EquityMarginSortKey[] l_sortKeys = new WEB3EquityMarginSortKey[1];

            l_sortKeys[0] = new WEB3EquityMarginSortKey();
            l_sortKeys[0].keyItem = "productCode";
            l_sortKeys[0].ascDesc = "A";
            // create検索条件データコンテナ
            Object[] l_searchCondContainers =
                l_equityMarginExecuteReferenceServiceImpl.createQueryDataContainer(l_subAccount, l_productCode,
                    l_marketCode, l_orderBizDate, l_orderStatus,null);

            // createソート条件
            String l_strSortCond = l_equityMarginExecuteReferenceServiceImpl.createSortCond(l_sortKeys);
            log.debug(l_strSortCond);
            
            //doFindAllQuery(
            //final String tableName, final String where, final String oderby, 
            //final String conditions, final Object bindVars[])
            List l_result = Processors.getDefaultProcessor().doFindAllQuery(
                EqtypeOrderUnitRow.TYPE, l_strSearchCond, l_strSortCond, null, l_searchCondContainers);

            assertEquals(l_result.size(), 3);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(0)).getOrderUnitId(), 1906);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(1)).getOrderUnitId(), 1908);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(2)).getOrderUnitId(), 1907);
            log.exiting(STR_METHOD_NAME);

        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

    }

    /**
     * testCreateQueryString_C0007<BR>
     *
     */
    public void testCreateQueryString_C0007()
    {
        //log
        final String STR_METHOD_NAME = "testCreateQueryString_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        EqtypeOrderUnitParams l_eqtypeOrderUnitParams = getEqtypeOrderUnitRow();

        //発注日
        Date l_orderBizDate = WEB3DateUtility.getDate("20070101","yyyyMMdd");

        String l_productCode = null;

        String l_marketCode = null;

        //株式・信用注文約定照会サービスImpl
        WEB3EquityMarginExecuteReferenceServiceImpl l_equityMarginExecuteReferenceServiceImpl =
            new WEB3EquityMarginExecuteReferenceServiceImpl();

        //注文受付状態
        OrderStatus l_orderStatus = l_equityMarginExecuteReferenceServiceImpl.new OrderStatus();

        l_orderStatus.interestEquityFlag = true;
        // create検索条件文字列
        String l_strSearchCond;
        try
        {
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setOrderUnitId(1907);
            l_eqtypeOrderUnitParams.setProductId(1909);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070102","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setProductId(1908);
            l_eqtypeOrderUnitParams.setOrderUnitId(1908);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070103","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            //create検索条件文字列
            l_strSearchCond = l_equityMarginExecuteReferenceServiceImpl.createQueryString(
                l_productCode,
                l_marketCode,
                l_orderBizDate,
                l_orderStatus,null);
            log.debug(l_strSearchCond);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            WEB3GentradeSubAccount l_subAccount;
            l_subAccount = (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(1906, 1906);

            //ソートキー:発注日 ASC
            WEB3EquityMarginSortKey[] l_sortKeys = new WEB3EquityMarginSortKey[1];

            l_sortKeys[0] = new WEB3EquityMarginSortKey();
            l_sortKeys[0].keyItem = "productCode";
            l_sortKeys[0].ascDesc = "D";
            // create検索条件データコンテナ
            Object[] l_searchCondContainers =
                l_equityMarginExecuteReferenceServiceImpl.createQueryDataContainer(l_subAccount, l_productCode,
                    l_marketCode, l_orderBizDate, l_orderStatus,null);

            // createソート条件
            String l_strSortCond = l_equityMarginExecuteReferenceServiceImpl.createSortCond(l_sortKeys);
            log.debug(l_strSortCond);
            
            //doFindAllQuery(
            //final String tableName, final String where, final String oderby, 
            //final String conditions, final Object bindVars[])
            List l_result = Processors.getDefaultProcessor().doFindAllQuery(
                EqtypeOrderUnitRow.TYPE, l_strSearchCond, l_strSortCond, null, l_searchCondContainers);

            assertEquals(l_result.size(), 3);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(0)).getOrderUnitId(), 1907);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(1)).getOrderUnitId(), 1908);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(2)).getOrderUnitId(), 1906);

            log.exiting(STR_METHOD_NAME);

        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

    }
    
    /**
     * testCreateQueryString_C0008<BR>
     *
     */
    public void testCreateQueryString_C0008()
    {
        //log
        final String STR_METHOD_NAME = "testCreateQueryString_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        EqtypeOrderUnitParams l_eqtypeOrderUnitParams = getEqtypeOrderUnitRow();

        //発注日
        Date l_orderBizDate = WEB3DateUtility.getDate("20070101","yyyyMMdd");

        String l_productCode = null;

        String l_marketCode = null;

        //株式・信用注文約定照会サービスImpl
        WEB3EquityMarginExecuteReferenceServiceImpl l_equityMarginExecuteReferenceServiceImpl =
            new WEB3EquityMarginExecuteReferenceServiceImpl();

        //注文受付状態
        OrderStatus l_orderStatus = l_equityMarginExecuteReferenceServiceImpl.new OrderStatus();

        l_orderStatus.interestEquityFlag = true;
        // create検索条件文字列
        String l_strSearchCond;
        try
        {
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setTaxType(TaxTypeEnum.SPECIAL_WITHHOLD);
            l_eqtypeOrderUnitParams.setOrderUnitId(1907);
            l_eqtypeOrderUnitParams.setProductId(1909);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070102","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setTaxType(TaxTypeEnum.SPECIAL);
            l_eqtypeOrderUnitParams.setProductId(1908);
            l_eqtypeOrderUnitParams.setOrderUnitId(1908);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070103","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            //create検索条件文字列
            l_strSearchCond = l_equityMarginExecuteReferenceServiceImpl.createQueryString(
                l_productCode,
                l_marketCode,
                l_orderBizDate,
                l_orderStatus,
                null);
            log.debug(l_strSearchCond);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            WEB3GentradeSubAccount l_subAccount;
            l_subAccount = (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(1906, 1906);

            //ソートキー:発注日 ASC
            WEB3EquityMarginSortKey[] l_sortKeys = new WEB3EquityMarginSortKey[1];

            l_sortKeys[0] = new WEB3EquityMarginSortKey();
            l_sortKeys[0].keyItem = "taxType";
            l_sortKeys[0].ascDesc = "A";
            // create検索条件データコンテナ
            Object[] l_searchCondContainers =
                l_equityMarginExecuteReferenceServiceImpl.createQueryDataContainer(l_subAccount, l_productCode,
                    l_marketCode, l_orderBizDate, l_orderStatus,null);

            // createソート条件
            String l_strSortCond = l_equityMarginExecuteReferenceServiceImpl.createSortCond(l_sortKeys);
            log.debug(l_strSortCond);
            
            //doFindAllQuery(
            //final String tableName, final String where, final String oderby, 
            //final String conditions, final Object bindVars[])
            List l_result = Processors.getDefaultProcessor().doFindAllQuery(
                EqtypeOrderUnitRow.TYPE, l_strSearchCond, l_strSortCond, null, l_searchCondContainers);

            assertEquals(l_result.size(), 3);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(0)).getOrderUnitId(), 1906);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(1)).getOrderUnitId(), 1908);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(2)).getOrderUnitId(), 1907);
            log.exiting(STR_METHOD_NAME);

        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

    }

    /**
     * testCreateQueryString_C0009<BR>
     *
     */
    public void testCreateQueryString_C0009()
    {
        //log
        final String STR_METHOD_NAME = "testCreateQueryString_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        EqtypeOrderUnitParams l_eqtypeOrderUnitParams = getEqtypeOrderUnitRow();

        //発注日
        Date l_orderBizDate = WEB3DateUtility.getDate("20070101","yyyyMMdd");

        String l_productCode = null;

        String l_marketCode = null;

        //株式・信用注文約定照会サービスImpl
        WEB3EquityMarginExecuteReferenceServiceImpl l_equityMarginExecuteReferenceServiceImpl =
            new WEB3EquityMarginExecuteReferenceServiceImpl();

        //注文受付状態
        OrderStatus l_orderStatus = l_equityMarginExecuteReferenceServiceImpl.new OrderStatus();

        l_orderStatus.interestEquityFlag = true;
        // create検索条件文字列
        String l_strSearchCond;
        try
        {
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setTaxType(TaxTypeEnum.SPECIAL_WITHHOLD);
            l_eqtypeOrderUnitParams.setOrderUnitId(1907);
            l_eqtypeOrderUnitParams.setProductId(1909);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070102","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setTaxType(TaxTypeEnum.SPECIAL);
            l_eqtypeOrderUnitParams.setProductId(1908);
            l_eqtypeOrderUnitParams.setOrderUnitId(1908);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070103","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            //create検索条件文字列
            l_strSearchCond = l_equityMarginExecuteReferenceServiceImpl.createQueryString(
                l_productCode,
                l_marketCode,
                l_orderBizDate,
                l_orderStatus,
                null);
            log.debug(l_strSearchCond);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            WEB3GentradeSubAccount l_subAccount;
            l_subAccount = (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(1906, 1906);

            //ソートキー:発注日 ASC
            WEB3EquityMarginSortKey[] l_sortKeys = new WEB3EquityMarginSortKey[1];

            l_sortKeys[0] = new WEB3EquityMarginSortKey();
            l_sortKeys[0].keyItem = "taxType";
            l_sortKeys[0].ascDesc = "D";
            // create検索条件データコンテナ
            Object[] l_searchCondContainers =
                l_equityMarginExecuteReferenceServiceImpl.createQueryDataContainer(l_subAccount, l_productCode,
                    l_marketCode, l_orderBizDate, l_orderStatus,null);

            // createソート条件
            String l_strSortCond = l_equityMarginExecuteReferenceServiceImpl.createSortCond(l_sortKeys);
            log.debug(l_strSortCond);
            
            //doFindAllQuery(
            //final String tableName, final String where, final String oderby, 
            //final String conditions, final Object bindVars[])
            List l_result = Processors.getDefaultProcessor().doFindAllQuery(
                EqtypeOrderUnitRow.TYPE, l_strSearchCond, l_strSortCond, null, l_searchCondContainers);

            assertEquals(l_result.size(), 3);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(0)).getOrderUnitId(), 1907);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(1)).getOrderUnitId(), 1908);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(2)).getOrderUnitId(), 1906);
            log.exiting(STR_METHOD_NAME);

        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

    }
    
    /**
     * testCreateQueryString_C0010<BR>
     *
     */
    public void testCreateQueryString_C0010()
    {
        //log
        final String STR_METHOD_NAME = "testCreateQueryString_C0010()";
        log.entering(TEST_START + STR_METHOD_NAME);

        EqtypeOrderUnitParams l_eqtypeOrderUnitParams = getEqtypeOrderUnitRow();

        //発注日
        Date l_orderBizDate = WEB3DateUtility.getDate("20070101","yyyyMMdd");

        String l_productCode = null;

        String l_marketCode = null;

        //株式・信用注文約定照会サービスImpl
        WEB3EquityMarginExecuteReferenceServiceImpl l_equityMarginExecuteReferenceServiceImpl =
            new WEB3EquityMarginExecuteReferenceServiceImpl();

        //注文受付状態
        OrderStatus l_orderStatus = l_equityMarginExecuteReferenceServiceImpl.new OrderStatus();

        l_orderStatus.interestEquityFlag = true;
        // create検索条件文字列
        String l_strSearchCond;
        try
        {
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setTaxType(TaxTypeEnum.SPECIAL_WITHHOLD);
            l_eqtypeOrderUnitParams.setOrderUnitId(1907);
            l_eqtypeOrderUnitParams.setProductId(1909);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070102","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setTaxType(TaxTypeEnum.SPECIAL);
            l_eqtypeOrderUnitParams.setProductId(1908);
            l_eqtypeOrderUnitParams.setOrderUnitId(1908);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070103","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            //create検索条件文字列
            l_strSearchCond = l_equityMarginExecuteReferenceServiceImpl.createQueryString(
                l_productCode,
                l_marketCode,
                l_orderBizDate,
                l_orderStatus,
                null);
            log.debug(l_strSearchCond);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            WEB3GentradeSubAccount l_subAccount;
            l_subAccount = (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(1906, 1906);

            //ソートキー:発注日 ASC
            WEB3EquityMarginSortKey[] l_sortKeys = new WEB3EquityMarginSortKey[1];

            l_sortKeys[0] = new WEB3EquityMarginSortKey();
            l_sortKeys[0].keyItem = "marketCode";
            l_sortKeys[0].ascDesc = "A";
            // create検索条件データコンテナ
            Object[] l_searchCondContainers =
                l_equityMarginExecuteReferenceServiceImpl.createQueryDataContainer(l_subAccount, l_productCode,
                    l_marketCode, l_orderBizDate, l_orderStatus,null);

            // createソート条件
            String l_strSortCond = l_equityMarginExecuteReferenceServiceImpl.createSortCond(l_sortKeys);
            log.debug(l_strSortCond);
            
            //doFindAllQuery(
            //final String tableName, final String where, final String oderby, 
            //final String conditions, final Object bindVars[])
            List l_result = Processors.getDefaultProcessor().doFindAllQuery(
                EqtypeOrderUnitRow.TYPE, l_strSearchCond, l_strSortCond, null, l_searchCondContainers);

            assertEquals(l_result.size(), 3);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(0)).getOrderUnitId(), 1906);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(1)).getOrderUnitId(), 1907);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(2)).getOrderUnitId(), 1908);
            log.exiting(STR_METHOD_NAME);

        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

    }

    /**
     * testCreateQueryString_C0011<BR>
     *
     */
    public void testCreateQueryString_C0011()
    {
        //log
        final String STR_METHOD_NAME = "testCreateQueryString_C0011()";
        log.entering(TEST_START + STR_METHOD_NAME);

        EqtypeOrderUnitParams l_eqtypeOrderUnitParams = getEqtypeOrderUnitRow();

        //発注日
        Date l_orderBizDate = WEB3DateUtility.getDate("20070101","yyyyMMdd");

        String l_productCode = null;

        String l_marketCode = null;

        //株式・信用注文約定照会サービスImpl
        WEB3EquityMarginExecuteReferenceServiceImpl l_equityMarginExecuteReferenceServiceImpl =
            new WEB3EquityMarginExecuteReferenceServiceImpl();

        //注文受付状態
        OrderStatus l_orderStatus = l_equityMarginExecuteReferenceServiceImpl.new OrderStatus();

        l_orderStatus.interestEquityFlag = true;
        // create検索条件文字列
        String l_strSearchCond;
        try
        {
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setTaxType(TaxTypeEnum.SPECIAL_WITHHOLD);
            l_eqtypeOrderUnitParams.setOrderUnitId(1907);
            l_eqtypeOrderUnitParams.setProductId(1909);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070102","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setTaxType(TaxTypeEnum.SPECIAL);
            l_eqtypeOrderUnitParams.setProductId(1908);
            l_eqtypeOrderUnitParams.setOrderUnitId(1908);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070103","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            //create検索条件文字列
            l_strSearchCond = l_equityMarginExecuteReferenceServiceImpl.createQueryString(
                l_productCode,
                l_marketCode,
                l_orderBizDate,
                l_orderStatus,
                null);
            log.debug(l_strSearchCond);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            WEB3GentradeSubAccount l_subAccount;
            l_subAccount = (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(1906, 1906);

            //ソートキー:発注日 ASC
            WEB3EquityMarginSortKey[] l_sortKeys = new WEB3EquityMarginSortKey[1];

            l_sortKeys[0] = new WEB3EquityMarginSortKey();
            l_sortKeys[0].keyItem = "marketCode";
            l_sortKeys[0].ascDesc = "D";
            // create検索条件データコンテナ
            Object[] l_searchCondContainers =
                l_equityMarginExecuteReferenceServiceImpl.createQueryDataContainer(l_subAccount, l_productCode,
                    l_marketCode, l_orderBizDate, l_orderStatus,null);

            // createソート条件
            String l_strSortCond = l_equityMarginExecuteReferenceServiceImpl.createSortCond(l_sortKeys);
            log.debug(l_strSortCond);
            
            //doFindAllQuery(
            //final String tableName, final String where, final String oderby, 
            //final String conditions, final Object bindVars[])
            List l_result = Processors.getDefaultProcessor().doFindAllQuery(
                EqtypeOrderUnitRow.TYPE, l_strSearchCond, l_strSortCond, null, l_searchCondContainers);

            assertEquals(l_result.size(), 3);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(0)).getOrderUnitId(), 1906);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(1)).getOrderUnitId(), 1907);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(2)).getOrderUnitId(), 1908);
            log.exiting(STR_METHOD_NAME);

        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

    }
    
    /**
     * testCreateQueryString_C0012<BR>
     *
     */
    public void testCreateQueryString_C0012()
    {
        //log
        final String STR_METHOD_NAME = "testCreateQueryString_C0012()";
        log.entering(TEST_START + STR_METHOD_NAME);

        EqtypeOrderUnitParams l_eqtypeOrderUnitParams = getEqtypeOrderUnitRow();

        //発注日
        Date l_orderBizDate = WEB3DateUtility.getDate("20070101","yyyyMMdd");

        String l_productCode = null;

        String l_marketCode = null;

        //株式・信用注文約定照会サービスImpl
        WEB3EquityMarginExecuteReferenceServiceImpl l_equityMarginExecuteReferenceServiceImpl =
            new WEB3EquityMarginExecuteReferenceServiceImpl();

        //注文受付状態
        OrderStatus l_orderStatus = l_equityMarginExecuteReferenceServiceImpl.new OrderStatus();

        l_orderStatus.interestEquityFlag = true;
        // create検索条件文字列
        String l_strSearchCond;
        try
        {
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setTaxType(TaxTypeEnum.SPECIAL_WITHHOLD);
            l_eqtypeOrderUnitParams.setOrderUnitId(1907);
            l_eqtypeOrderUnitParams.setProductId(1909);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070102","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setTaxType(TaxTypeEnum.SPECIAL);
            l_eqtypeOrderUnitParams.setProductId(1908);
            l_eqtypeOrderUnitParams.setOrderUnitId(1908);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070103","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            //create検索条件文字列
            l_strSearchCond = l_equityMarginExecuteReferenceServiceImpl.createQueryString(
                l_productCode,
                l_marketCode,
                l_orderBizDate,
                l_orderStatus,
                null);
            log.debug(l_strSearchCond);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            WEB3GentradeSubAccount l_subAccount;
            l_subAccount = (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(1906, 1906);

            //ソートキー:発注日 ASC
            WEB3EquityMarginSortKey[] l_sortKeys = new WEB3EquityMarginSortKey[1];

            l_sortKeys[0] = new WEB3EquityMarginSortKey();
            //値段条件
            l_sortKeys[0].keyItem = "priceCondType";
            l_sortKeys[0].ascDesc = "A";
            // create検索条件データコンテナ
            Object[] l_searchCondContainers =
                l_equityMarginExecuteReferenceServiceImpl.createQueryDataContainer(l_subAccount, l_productCode,
                    l_marketCode, l_orderBizDate, l_orderStatus,null);

            // createソート条件
            String l_strSortCond = l_equityMarginExecuteReferenceServiceImpl.createSortCond(l_sortKeys);
            log.debug(l_strSortCond);
            
            //doFindAllQuery(
            //final String tableName, final String where, final String oderby, 
            //final String conditions, final Object bindVars[])
            List l_result = Processors.getDefaultProcessor().doFindAllQuery(
                EqtypeOrderUnitRow.TYPE, l_strSearchCond, l_strSortCond, null, l_searchCondContainers);

            assertEquals(l_result.size(), 3);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(0)).getOrderUnitId(), 1906);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(1)).getOrderUnitId(), 1907);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(2)).getOrderUnitId(), 1908);
            log.exiting(STR_METHOD_NAME);

        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

    }

    /**
     * testCreateQueryString_C0013<BR>
     *
     */
    public void testCreateQueryString_C0013()
    {
        //log
        final String STR_METHOD_NAME = "testCreateQueryString_C0013()";
        log.entering(TEST_START + STR_METHOD_NAME);

        EqtypeOrderUnitParams l_eqtypeOrderUnitParams = getEqtypeOrderUnitRow();

        //発注日
        Date l_orderBizDate = WEB3DateUtility.getDate("20070101","yyyyMMdd");

        String l_productCode = null;

        String l_marketCode = null;

        //株式・信用注文約定照会サービスImpl
        WEB3EquityMarginExecuteReferenceServiceImpl l_equityMarginExecuteReferenceServiceImpl =
            new WEB3EquityMarginExecuteReferenceServiceImpl();

        //注文受付状態
        OrderStatus l_orderStatus = l_equityMarginExecuteReferenceServiceImpl.new OrderStatus();

        l_orderStatus.interestEquityFlag = true;
        // create検索条件文字列
        String l_strSearchCond;
        try
        {
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setTaxType(TaxTypeEnum.SPECIAL_WITHHOLD);
            l_eqtypeOrderUnitParams.setOrderUnitId(1907);
            l_eqtypeOrderUnitParams.setProductId(1909);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070102","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setTaxType(TaxTypeEnum.SPECIAL);
            l_eqtypeOrderUnitParams.setProductId(1908);
            l_eqtypeOrderUnitParams.setOrderUnitId(1908);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070103","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            //create検索条件文字列
            l_strSearchCond = l_equityMarginExecuteReferenceServiceImpl.createQueryString(
                l_productCode,
                l_marketCode,
                l_orderBizDate,
                l_orderStatus,
                null);
            log.debug(l_strSearchCond);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            WEB3GentradeSubAccount l_subAccount;
            l_subAccount = (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(1906, 1906);

            //ソートキー:発注日 ASC
            WEB3EquityMarginSortKey[] l_sortKeys = new WEB3EquityMarginSortKey[1];

            l_sortKeys[0] = new WEB3EquityMarginSortKey();
            //値段条件
            l_sortKeys[0].keyItem = "priceCondType";
            l_sortKeys[0].ascDesc = "D";
            // create検索条件データコンテナ
            Object[] l_searchCondContainers =
                l_equityMarginExecuteReferenceServiceImpl.createQueryDataContainer(l_subAccount, l_productCode,
                    l_marketCode, l_orderBizDate, l_orderStatus,null);

            // createソート条件
            String l_strSortCond = l_equityMarginExecuteReferenceServiceImpl.createSortCond(l_sortKeys);
            log.debug(l_strSortCond);
            
            //doFindAllQuery(
            //final String tableName, final String where, final String oderby, 
            //final String conditions, final Object bindVars[])
            List l_result = Processors.getDefaultProcessor().doFindAllQuery(
                EqtypeOrderUnitRow.TYPE, l_strSearchCond, l_strSortCond, null, l_searchCondContainers);

            assertEquals(l_result.size(), 3);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(0)).getOrderUnitId(), 1906);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(1)).getOrderUnitId(), 1907);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(2)).getOrderUnitId(), 1908);
            log.exiting(STR_METHOD_NAME);

        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

    }
    
    /**
     * testCreateQueryString_C0014<BR>
     *
     */
    public void testCreateQueryString_C0014()
    {
        //log
        final String STR_METHOD_NAME = "testCreateQueryString_C0014()";
        log.entering(TEST_START + STR_METHOD_NAME);

        EqtypeOrderUnitParams l_eqtypeOrderUnitParams = getEqtypeOrderUnitRow();

        //発注日
        Date l_orderBizDate = WEB3DateUtility.getDate("20070101","yyyyMMdd");

        String l_productCode = null;

        String l_marketCode = null;

        //株式・信用注文約定照会サービスImpl
        WEB3EquityMarginExecuteReferenceServiceImpl l_equityMarginExecuteReferenceServiceImpl =
            new WEB3EquityMarginExecuteReferenceServiceImpl();

        //注文受付状態
        OrderStatus l_orderStatus = l_equityMarginExecuteReferenceServiceImpl.new OrderStatus();

        l_orderStatus.interestEquityFlag = true;
        // create検索条件文字列
        String l_strSearchCond;
        try
        {
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setTaxType(TaxTypeEnum.SPECIAL_WITHHOLD);
            l_eqtypeOrderUnitParams.setOrderUnitId(1907);
            l_eqtypeOrderUnitParams.setProductId(1909);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070102","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setTaxType(TaxTypeEnum.SPECIAL);
            l_eqtypeOrderUnitParams.setProductId(1908);
            l_eqtypeOrderUnitParams.setOrderUnitId(1908);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070103","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            //create検索条件文字列
            l_strSearchCond = l_equityMarginExecuteReferenceServiceImpl.createQueryString(
                l_productCode,
                l_marketCode,
                l_orderBizDate,
                l_orderStatus,
                null);
            log.debug(l_strSearchCond);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            WEB3GentradeSubAccount l_subAccount;
            l_subAccount = (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(1906, 1906);

            //ソートキー:発注日 ASC
            WEB3EquityMarginSortKey[] l_sortKeys = new WEB3EquityMarginSortKey[1];

            l_sortKeys[0] = new WEB3EquityMarginSortKey();
            //執行条件
            l_sortKeys[0].keyItem = "execCondType";
            l_sortKeys[0].ascDesc = "A";
            // create検索条件データコンテナ
            Object[] l_searchCondContainers =
                l_equityMarginExecuteReferenceServiceImpl.createQueryDataContainer(l_subAccount, l_productCode,
                    l_marketCode, l_orderBizDate, l_orderStatus,null);

            // createソート条件
            String l_strSortCond = l_equityMarginExecuteReferenceServiceImpl.createSortCond(l_sortKeys);
            log.debug(l_strSortCond);
            
            //doFindAllQuery(
            //final String tableName, final String where, final String oderby, 
            //final String conditions, final Object bindVars[])
            List l_result = Processors.getDefaultProcessor().doFindAllQuery(
                EqtypeOrderUnitRow.TYPE, l_strSearchCond, l_strSortCond, null, l_searchCondContainers);

            assertEquals(l_result.size(), 3);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(0)).getOrderUnitId(), 1906);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(1)).getOrderUnitId(), 1907);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(2)).getOrderUnitId(), 1908);
            log.exiting(STR_METHOD_NAME);

        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

    }

    /**
     * testCreateQueryString_C0015<BR>
     *
     */
    public void testCreateQueryString_C0015()
    {
        //log
        final String STR_METHOD_NAME = "testCreateQueryString_C0015()";
        log.entering(TEST_START + STR_METHOD_NAME);

        EqtypeOrderUnitParams l_eqtypeOrderUnitParams = getEqtypeOrderUnitRow();

        //発注日
        Date l_orderBizDate = WEB3DateUtility.getDate("20070101","yyyyMMdd");

        String l_productCode = null;

        String l_marketCode = null;

        //株式・信用注文約定照会サービスImpl
        WEB3EquityMarginExecuteReferenceServiceImpl l_equityMarginExecuteReferenceServiceImpl =
            new WEB3EquityMarginExecuteReferenceServiceImpl();

        //注文受付状態
        OrderStatus l_orderStatus = l_equityMarginExecuteReferenceServiceImpl.new OrderStatus();

        l_orderStatus.interestEquityFlag = true;
        // create検索条件文字列
        String l_strSearchCond;
        try
        {
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setTaxType(TaxTypeEnum.SPECIAL_WITHHOLD);
            l_eqtypeOrderUnitParams.setOrderUnitId(1907);
            l_eqtypeOrderUnitParams.setProductId(1909);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070102","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setTaxType(TaxTypeEnum.SPECIAL);
            l_eqtypeOrderUnitParams.setProductId(1908);
            l_eqtypeOrderUnitParams.setOrderUnitId(1908);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070103","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            //create検索条件文字列
            l_strSearchCond = l_equityMarginExecuteReferenceServiceImpl.createQueryString(
                l_productCode,
                l_marketCode,
                l_orderBizDate,
                l_orderStatus,
                null);
            log.debug(l_strSearchCond);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            WEB3GentradeSubAccount l_subAccount;
            l_subAccount = (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(1906, 1906);

            //ソートキー:発注日 ASC
            WEB3EquityMarginSortKey[] l_sortKeys = new WEB3EquityMarginSortKey[1];

            l_sortKeys[0] = new WEB3EquityMarginSortKey();
            //執行条件
            l_sortKeys[0].keyItem = "execCondType";
            l_sortKeys[0].ascDesc = "D";
            // create検索条件データコンテナ
            Object[] l_searchCondContainers =
                l_equityMarginExecuteReferenceServiceImpl.createQueryDataContainer(l_subAccount, l_productCode,
                    l_marketCode, l_orderBizDate, l_orderStatus,null);

            // createソート条件
            String l_strSortCond = l_equityMarginExecuteReferenceServiceImpl.createSortCond(l_sortKeys);
            log.debug(l_strSortCond);
            
            //doFindAllQuery(
            //final String tableName, final String where, final String oderby, 
            //final String conditions, final Object bindVars[])
            List l_result = Processors.getDefaultProcessor().doFindAllQuery(
                EqtypeOrderUnitRow.TYPE, l_strSearchCond, l_strSortCond, null, l_searchCondContainers);

            assertEquals(l_result.size(), 3);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(0)).getOrderUnitId(), 1906);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(1)).getOrderUnitId(), 1907);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(2)).getOrderUnitId(), 1908);
            log.exiting(STR_METHOD_NAME);

        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

    }
    
    /**
     * testCreateQueryString_C0016<BR>
     *
     */
    public void testCreateQueryString_C0016()
    {
        //log
        final String STR_METHOD_NAME = "testCreateQueryString_C0016()";
        log.entering(TEST_START + STR_METHOD_NAME);

        EqtypeOrderUnitParams l_eqtypeOrderUnitParams = getEqtypeOrderUnitRow();

        //発注日
        Date l_orderBizDate = WEB3DateUtility.getDate("20070101","yyyyMMdd");

        String l_productCode = null;

        String l_marketCode = null;

        //株式・信用注文約定照会サービスImpl
        WEB3EquityMarginExecuteReferenceServiceImpl l_equityMarginExecuteReferenceServiceImpl =
            new WEB3EquityMarginExecuteReferenceServiceImpl();

        //注文受付状態
        OrderStatus l_orderStatus = l_equityMarginExecuteReferenceServiceImpl.new OrderStatus();

        l_orderStatus.interestEquityFlag = true;
        // create検索条件文字列
        String l_strSearchCond;
        try
        {
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setTaxType(TaxTypeEnum.SPECIAL_WITHHOLD);
            l_eqtypeOrderUnitParams.setOrderUnitId(1907);
            l_eqtypeOrderUnitParams.setProductId(1909);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070102","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setTaxType(TaxTypeEnum.SPECIAL);
            l_eqtypeOrderUnitParams.setProductId(1908);
            l_eqtypeOrderUnitParams.setOrderUnitId(1908);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070103","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            //create検索条件文字列
            l_strSearchCond = l_equityMarginExecuteReferenceServiceImpl.createQueryString(
                l_productCode,
                l_marketCode,
                l_orderBizDate,
                l_orderStatus,
                null);
            log.debug(l_strSearchCond);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            WEB3GentradeSubAccount l_subAccount;
            l_subAccount = (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(1906, 1906);

            //ソートキー:発注日 ASC
            WEB3EquityMarginSortKey[] l_sortKeys = new WEB3EquityMarginSortKey[1];

            l_sortKeys[0] = new WEB3EquityMarginSortKey();
            //発注条件
            l_sortKeys[0].keyItem = "orderCondType";
            l_sortKeys[0].ascDesc = "A";
            // create検索条件データコンテナ
            Object[] l_searchCondContainers =
                l_equityMarginExecuteReferenceServiceImpl.createQueryDataContainer(l_subAccount, l_productCode,
                    l_marketCode, l_orderBizDate, l_orderStatus,null);

            // createソート条件
            String l_strSortCond = l_equityMarginExecuteReferenceServiceImpl.createSortCond(l_sortKeys);
            log.debug(l_strSortCond);
            
            //doFindAllQuery(
            //final String tableName, final String where, final String oderby, 
            //final String conditions, final Object bindVars[])
            List l_result = Processors.getDefaultProcessor().doFindAllQuery(
                EqtypeOrderUnitRow.TYPE, l_strSearchCond, l_strSortCond, null, l_searchCondContainers);

            assertEquals(l_result.size(), 3);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(0)).getOrderUnitId(), 1906);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(1)).getOrderUnitId(), 1907);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(2)).getOrderUnitId(), 1908);
            log.exiting(STR_METHOD_NAME);

        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

    }

    /**
     * testCreateQueryString_C0017<BR>
     *
     */
    public void testCreateQueryString_C0017()
    {
        //log
        final String STR_METHOD_NAME = "testCreateQueryString_C0017()";
        log.entering(TEST_START + STR_METHOD_NAME);

        EqtypeOrderUnitParams l_eqtypeOrderUnitParams = getEqtypeOrderUnitRow();

        //発注日
        Date l_orderBizDate = WEB3DateUtility.getDate("20070101","yyyyMMdd");

        String l_productCode = null;

        String l_marketCode = null;

        //株式・信用注文約定照会サービスImpl
        WEB3EquityMarginExecuteReferenceServiceImpl l_equityMarginExecuteReferenceServiceImpl =
            new WEB3EquityMarginExecuteReferenceServiceImpl();

        //注文受付状態
        OrderStatus l_orderStatus = l_equityMarginExecuteReferenceServiceImpl.new OrderStatus();

        l_orderStatus.interestEquityFlag = true;
        // create検索条件文字列
        String l_strSearchCond;
        try
        {
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setTaxType(TaxTypeEnum.SPECIAL_WITHHOLD);
            l_eqtypeOrderUnitParams.setOrderUnitId(1907);
            l_eqtypeOrderUnitParams.setProductId(1909);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070102","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setTaxType(TaxTypeEnum.SPECIAL);
            l_eqtypeOrderUnitParams.setProductId(1908);
            l_eqtypeOrderUnitParams.setOrderUnitId(1908);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070103","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            //create検索条件文字列
            l_strSearchCond = l_equityMarginExecuteReferenceServiceImpl.createQueryString(
                l_productCode,
                l_marketCode,
                l_orderBizDate,
                l_orderStatus,
                null);
            log.debug(l_strSearchCond);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            WEB3GentradeSubAccount l_subAccount;
            l_subAccount = (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(1906, 1906);

            //ソートキー:発注日 ASC
            WEB3EquityMarginSortKey[] l_sortKeys = new WEB3EquityMarginSortKey[1];

            l_sortKeys[0] = new WEB3EquityMarginSortKey();
            //発注条件
            l_sortKeys[0].keyItem = "orderCondType";
            l_sortKeys[0].ascDesc = "D";
            // create検索条件データコンテナ
            Object[] l_searchCondContainers =
                l_equityMarginExecuteReferenceServiceImpl.createQueryDataContainer(l_subAccount, l_productCode,
                    l_marketCode, l_orderBizDate, l_orderStatus,null);

            // createソート条件
            String l_strSortCond = l_equityMarginExecuteReferenceServiceImpl.createSortCond(l_sortKeys);
            log.debug(l_strSortCond);
            
            //doFindAllQuery(
            //final String tableName, final String where, final String oderby, 
            //final String conditions, final Object bindVars[])
            List l_result = Processors.getDefaultProcessor().doFindAllQuery(
                EqtypeOrderUnitRow.TYPE, l_strSearchCond, l_strSortCond, null, l_searchCondContainers);

            assertEquals(l_result.size(), 3);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(0)).getOrderUnitId(), 1906);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(1)).getOrderUnitId(), 1907);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(2)).getOrderUnitId(), 1908);
            log.exiting(STR_METHOD_NAME);

        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

    }
    
    /**
     * testCreateQueryString_C0018<BR>
     *
     */
    public void testCreateQueryString_C0018()
    {
        //log
        final String STR_METHOD_NAME = "testCreateQueryString_C0018()";
        log.entering(TEST_START + STR_METHOD_NAME);

        EqtypeOrderUnitParams l_eqtypeOrderUnitParams = getEqtypeOrderUnitRow();

        //発注日
        Date l_orderBizDate = WEB3DateUtility.getDate("20070101","yyyyMMdd");

        String l_productCode = null;

        String l_marketCode = null;

        //株式・信用注文約定照会サービスImpl
        WEB3EquityMarginExecuteReferenceServiceImpl l_equityMarginExecuteReferenceServiceImpl =
            new WEB3EquityMarginExecuteReferenceServiceImpl();

        //注文受付状態
        OrderStatus l_orderStatus = l_equityMarginExecuteReferenceServiceImpl.new OrderStatus();

        l_orderStatus.interestEquityFlag = true;
        // create検索条件文字列
        String l_strSearchCond;
        try
        {
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setTaxType(TaxTypeEnum.SPECIAL_WITHHOLD);
            l_eqtypeOrderUnitParams.setOrderUnitId(1907);
            l_eqtypeOrderUnitParams.setProductId(1909);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070102","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setTaxType(TaxTypeEnum.SPECIAL);
            l_eqtypeOrderUnitParams.setProductId(1908);
            l_eqtypeOrderUnitParams.setOrderUnitId(1908);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070103","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            //create検索条件文字列
            l_strSearchCond = l_equityMarginExecuteReferenceServiceImpl.createQueryString(
                l_productCode,
                l_marketCode,
                l_orderBizDate,
                l_orderStatus,
                null);
            log.debug(l_strSearchCond);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            WEB3GentradeSubAccount l_subAccount;
            l_subAccount = (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(1906, 1906);

            //ソートキー:発注日 ASC
            WEB3EquityMarginSortKey[] l_sortKeys = new WEB3EquityMarginSortKey[1];

            l_sortKeys[0] = new WEB3EquityMarginSortKey();
            //作成日付
            l_sortKeys[0].keyItem = "orderDate";
            l_sortKeys[0].ascDesc = "A";
            // create検索条件データコンテナ
            Object[] l_searchCondContainers =
                l_equityMarginExecuteReferenceServiceImpl.createQueryDataContainer(l_subAccount, l_productCode,
                    l_marketCode, l_orderBizDate, l_orderStatus,null);

            // createソート条件
            String l_strSortCond = l_equityMarginExecuteReferenceServiceImpl.createSortCond(l_sortKeys);
            log.debug(l_strSortCond);
            
            //doFindAllQuery(
            //final String tableName, final String where, final String oderby, 
            //final String conditions, final Object bindVars[])
            List l_result = Processors.getDefaultProcessor().doFindAllQuery(
                EqtypeOrderUnitRow.TYPE, l_strSearchCond, l_strSortCond, null, l_searchCondContainers);

            assertEquals(l_result.size(), 3);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(0)).getOrderUnitId(), 1906);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(1)).getOrderUnitId(), 1907);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(2)).getOrderUnitId(), 1908);
            log.exiting(STR_METHOD_NAME);

        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

    }

    /**
     * testCreateQueryString_C0019<BR>
     *
     */
    public void testCreateQueryString_C0019()
    {
        //log
        final String STR_METHOD_NAME = "testCreateQueryString_C0019()";
        log.entering(TEST_START + STR_METHOD_NAME);

        EqtypeOrderUnitParams l_eqtypeOrderUnitParams = getEqtypeOrderUnitRow();

        //発注日
        Date l_orderBizDate = WEB3DateUtility.getDate("20070101","yyyyMMdd");

        String l_productCode = null;

        String l_marketCode = null;

        //株式・信用注文約定照会サービスImpl
        WEB3EquityMarginExecuteReferenceServiceImpl l_equityMarginExecuteReferenceServiceImpl =
            new WEB3EquityMarginExecuteReferenceServiceImpl();

        //注文受付状態
        OrderStatus l_orderStatus = l_equityMarginExecuteReferenceServiceImpl.new OrderStatus();

        l_orderStatus.interestEquityFlag = true;
        // create検索条件文字列
        String l_strSearchCond;
        try
        {
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setTaxType(TaxTypeEnum.SPECIAL_WITHHOLD);
            l_eqtypeOrderUnitParams.setOrderUnitId(1907);
            l_eqtypeOrderUnitParams.setProductId(1909);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070102","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setTaxType(TaxTypeEnum.SPECIAL);
            l_eqtypeOrderUnitParams.setProductId(1908);
            l_eqtypeOrderUnitParams.setOrderUnitId(1908);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070103","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            //create検索条件文字列
            l_strSearchCond = l_equityMarginExecuteReferenceServiceImpl.createQueryString(
                l_productCode,
                l_marketCode,
                l_orderBizDate,
                l_orderStatus,
                null);
            log.debug(l_strSearchCond);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            WEB3GentradeSubAccount l_subAccount;
            l_subAccount = (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(1906, 1906);

            //ソートキー:発注日 ASC
            WEB3EquityMarginSortKey[] l_sortKeys = new WEB3EquityMarginSortKey[1];

            l_sortKeys[0] = new WEB3EquityMarginSortKey();
            //作成日付
            l_sortKeys[0].keyItem = "orderDate";
            l_sortKeys[0].ascDesc = "D";
            // create検索条件データコンテナ
            Object[] l_searchCondContainers =
                l_equityMarginExecuteReferenceServiceImpl.createQueryDataContainer(l_subAccount, l_productCode,
                    l_marketCode, l_orderBizDate, l_orderStatus,null);

            // createソート条件
            String l_strSortCond = l_equityMarginExecuteReferenceServiceImpl.createSortCond(l_sortKeys);
            log.debug(l_strSortCond);
            
            //doFindAllQuery(
            //final String tableName, final String where, final String oderby, 
            //final String conditions, final Object bindVars[])
            List l_result = Processors.getDefaultProcessor().doFindAllQuery(
                EqtypeOrderUnitRow.TYPE, l_strSearchCond, l_strSortCond, null, l_searchCondContainers);

            assertEquals(l_result.size(), 3);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(0)).getOrderUnitId(), 1906);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(1)).getOrderUnitId(), 1907);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(2)).getOrderUnitId(), 1908);
            log.exiting(STR_METHOD_NAME);

        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

    }

    /**
     * testCreateQueryString_C0020<BR>
     *
     */
    public void testCreateQueryString_C0020()
    {
        //log
        final String STR_METHOD_NAME = "testCreateQueryString_C0020()";
        log.entering(TEST_START + STR_METHOD_NAME);

        EqtypeOrderUnitParams l_eqtypeOrderUnitParams = getEqtypeOrderUnitRow();

        //発注日
        Date l_orderBizDate = WEB3DateUtility.getDate("20070101","yyyyMMdd");

        String l_productCode = null;

        String l_marketCode = null;

        //株式・信用注文約定照会サービスImpl
        WEB3EquityMarginExecuteReferenceServiceImpl l_equityMarginExecuteReferenceServiceImpl =
            new WEB3EquityMarginExecuteReferenceServiceImpl();

        //注文受付状態
        OrderStatus l_orderStatus = l_equityMarginExecuteReferenceServiceImpl.new OrderStatus();

        l_orderStatus.interestEquityFlag = true;
        // create検索条件文字列
        String l_strSearchCond;
        try
        {
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setTaxType(TaxTypeEnum.SPECIAL_WITHHOLD);
            l_eqtypeOrderUnitParams.setOrderUnitId(1907);
            l_eqtypeOrderUnitParams.setProductId(1909);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070102","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setTaxType(TaxTypeEnum.SPECIAL);
            l_eqtypeOrderUnitParams.setProductId(1908);
            l_eqtypeOrderUnitParams.setOrderUnitId(1908);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070103","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            //create検索条件文字列
            l_strSearchCond = l_equityMarginExecuteReferenceServiceImpl.createQueryString(
                l_productCode,
                l_marketCode,
                l_orderBizDate,
                l_orderStatus,
                null);
            log.debug(l_strSearchCond);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            WEB3GentradeSubAccount l_subAccount;
            l_subAccount = (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(1906, 1906);

            //ソートキー:発注日 ASC
            WEB3EquityMarginSortKey[] l_sortKeys = new WEB3EquityMarginSortKey[1];

            l_sortKeys[0] = new WEB3EquityMarginSortKey();
            //注文失効日付
            l_sortKeys[0].keyItem = "expirationDate";
            l_sortKeys[0].ascDesc = "A";
            // create検索条件データコンテナ
            Object[] l_searchCondContainers =
                l_equityMarginExecuteReferenceServiceImpl.createQueryDataContainer(l_subAccount, l_productCode,
                    l_marketCode, l_orderBizDate, l_orderStatus,null);

            // createソート条件
            String l_strSortCond = l_equityMarginExecuteReferenceServiceImpl.createSortCond(l_sortKeys);
            log.debug(l_strSortCond);
            
            //doFindAllQuery(
            //final String tableName, final String where, final String oderby, 
            //final String conditions, final Object bindVars[])
            List l_result = Processors.getDefaultProcessor().doFindAllQuery(
                EqtypeOrderUnitRow.TYPE, l_strSearchCond, l_strSortCond, null, l_searchCondContainers);

            assertEquals(l_result.size(), 3);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(0)).getOrderUnitId(), 1906);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(1)).getOrderUnitId(), 1907);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(2)).getOrderUnitId(), 1908);
            log.exiting(STR_METHOD_NAME);

        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

    }

    /**
     * testCreateQueryString_C0021<BR>
     *
     */
    public void testCreateQueryString_C0021()
    {
        //log
        final String STR_METHOD_NAME = "testCreateQueryString_C0021()";
        log.entering(TEST_START + STR_METHOD_NAME);

        EqtypeOrderUnitParams l_eqtypeOrderUnitParams = getEqtypeOrderUnitRow();

        //発注日
        Date l_orderBizDate = WEB3DateUtility.getDate("20070101","yyyyMMdd");

        String l_productCode = null;

        String l_marketCode = null;

        //株式・信用注文約定照会サービスImpl
        WEB3EquityMarginExecuteReferenceServiceImpl l_equityMarginExecuteReferenceServiceImpl =
            new WEB3EquityMarginExecuteReferenceServiceImpl();

        //注文受付状態
        OrderStatus l_orderStatus = l_equityMarginExecuteReferenceServiceImpl.new OrderStatus();

        l_orderStatus.interestEquityFlag = true;
        // create検索条件文字列
        String l_strSearchCond;
        try
        {
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setTaxType(TaxTypeEnum.SPECIAL_WITHHOLD);
            l_eqtypeOrderUnitParams.setOrderUnitId(1907);
            l_eqtypeOrderUnitParams.setProductId(1909);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070102","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setTaxType(TaxTypeEnum.SPECIAL);
            l_eqtypeOrderUnitParams.setProductId(1908);
            l_eqtypeOrderUnitParams.setOrderUnitId(1908);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070103","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            //create検索条件文字列
            l_strSearchCond = l_equityMarginExecuteReferenceServiceImpl.createQueryString(
                l_productCode,
                l_marketCode,
                l_orderBizDate,
                l_orderStatus,
                null);
            log.debug(l_strSearchCond);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            WEB3GentradeSubAccount l_subAccount;
            l_subAccount = (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(1906, 1906);

            //ソートキー:発注日 ASC
            WEB3EquityMarginSortKey[] l_sortKeys = new WEB3EquityMarginSortKey[1];

            l_sortKeys[0] = new WEB3EquityMarginSortKey();
            //注文失効日付
            l_sortKeys[0].keyItem = "expirationDate";
            l_sortKeys[0].ascDesc = "D";
            // create検索条件データコンテナ
            Object[] l_searchCondContainers =
                l_equityMarginExecuteReferenceServiceImpl.createQueryDataContainer(l_subAccount, l_productCode,
                    l_marketCode, l_orderBizDate, l_orderStatus,null);

            // createソート条件
            String l_strSortCond = l_equityMarginExecuteReferenceServiceImpl.createSortCond(l_sortKeys);
            log.debug(l_strSortCond);
            
            //doFindAllQuery(
            //final String tableName, final String where, final String oderby, 
            //final String conditions, final Object bindVars[])
            List l_result = Processors.getDefaultProcessor().doFindAllQuery(
                EqtypeOrderUnitRow.TYPE, l_strSearchCond, l_strSortCond, null, l_searchCondContainers);

            assertEquals(l_result.size(), 3);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(0)).getOrderUnitId(), 1906);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(1)).getOrderUnitId(), 1907);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(2)).getOrderUnitId(), 1908);
            log.exiting(STR_METHOD_NAME);

        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

    }

    /**
     * testCreateQueryString_C0022<BR>
     *
     */
    public void testCreateQueryString_C0022()
    {
        //log
        final String STR_METHOD_NAME = "testCreateQueryString_C0022()";
        log.entering(TEST_START + STR_METHOD_NAME);

        EqtypeOrderUnitParams l_eqtypeOrderUnitParams = getEqtypeOrderUnitRow();

        //発注日
        Date l_orderBizDate = WEB3DateUtility.getDate("20070101","yyyyMMdd");

        String l_productCode = null;

        String l_marketCode = null;

        //株式・信用注文約定照会サービスImpl
        WEB3EquityMarginExecuteReferenceServiceImpl l_equityMarginExecuteReferenceServiceImpl =
            new WEB3EquityMarginExecuteReferenceServiceImpl();

        //注文受付状態
        OrderStatus l_orderStatus = l_equityMarginExecuteReferenceServiceImpl.new OrderStatus();

        l_orderStatus.interestEquityFlag = true;
        // create検索条件文字列
        String l_strSearchCond;
        try
        {
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setTaxType(TaxTypeEnum.SPECIAL_WITHHOLD);
            l_eqtypeOrderUnitParams.setOrderUnitId(1907);
            l_eqtypeOrderUnitParams.setProductId(1909);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070102","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setTaxType(TaxTypeEnum.SPECIAL);
            l_eqtypeOrderUnitParams.setProductId(1908);
            l_eqtypeOrderUnitParams.setOrderUnitId(1908);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070103","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            //create検索条件文字列
            l_strSearchCond = l_equityMarginExecuteReferenceServiceImpl.createQueryString(
                l_productCode,
                l_marketCode,
                l_orderBizDate,
                l_orderStatus,
                null);
            log.debug(l_strSearchCond);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            WEB3GentradeSubAccount l_subAccount;
            l_subAccount = (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(1906, 1906);

            //ソートキー:発注日 ASC
            WEB3EquityMarginSortKey[] l_sortKeys = new WEB3EquityMarginSortKey[1];

            l_sortKeys[0] = new WEB3EquityMarginSortKey();
            //弁済区分
            l_sortKeys[0].keyItem = "repaymentDiv";
            l_sortKeys[0].ascDesc = "A";
            // create検索条件データコンテナ
            Object[] l_searchCondContainers =
                l_equityMarginExecuteReferenceServiceImpl.createQueryDataContainer(l_subAccount, l_productCode,
                    l_marketCode, l_orderBizDate, l_orderStatus,null);

            // createソート条件
            String l_strSortCond = l_equityMarginExecuteReferenceServiceImpl.createSortCond(l_sortKeys);
            log.debug(l_strSortCond);
            
            //doFindAllQuery(
            //final String tableName, final String where, final String oderby, 
            //final String conditions, final Object bindVars[])
            List l_result = Processors.getDefaultProcessor().doFindAllQuery(
                EqtypeOrderUnitRow.TYPE, l_strSearchCond, l_strSortCond, null, l_searchCondContainers);

            assertEquals(l_result.size(), 3);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(0)).getOrderUnitId(), 1906);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(1)).getOrderUnitId(), 1907);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(2)).getOrderUnitId(), 1908);
            log.exiting(STR_METHOD_NAME);

        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

    }

    /**
     * testCreateQueryString_C0023<BR>
     *
     */
    public void testCreateQueryString_C0023()
    {
        //log
        final String STR_METHOD_NAME = "testCreateQueryString_C0023()";
        log.entering(TEST_START + STR_METHOD_NAME);

        EqtypeOrderUnitParams l_eqtypeOrderUnitParams = getEqtypeOrderUnitRow();

        //発注日
        Date l_orderBizDate = WEB3DateUtility.getDate("20070101","yyyyMMdd");

        String l_productCode = null;

        String l_marketCode = null;

        //株式・信用注文約定照会サービスImpl
        WEB3EquityMarginExecuteReferenceServiceImpl l_equityMarginExecuteReferenceServiceImpl =
            new WEB3EquityMarginExecuteReferenceServiceImpl();

        //注文受付状態
        OrderStatus l_orderStatus = l_equityMarginExecuteReferenceServiceImpl.new OrderStatus();

        l_orderStatus.interestEquityFlag = true;
        // create検索条件文字列
        String l_strSearchCond;
        try
        {
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setTaxType(TaxTypeEnum.SPECIAL_WITHHOLD);
            l_eqtypeOrderUnitParams.setOrderUnitId(1907);
            l_eqtypeOrderUnitParams.setProductId(1909);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070102","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setTaxType(TaxTypeEnum.SPECIAL);
            l_eqtypeOrderUnitParams.setProductId(1908);
            l_eqtypeOrderUnitParams.setOrderUnitId(1908);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070103","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            //create検索条件文字列
            l_strSearchCond = l_equityMarginExecuteReferenceServiceImpl.createQueryString(
                l_productCode,
                l_marketCode,
                l_orderBizDate,
                l_orderStatus,
                null);
            log.debug(l_strSearchCond);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            WEB3GentradeSubAccount l_subAccount;
            l_subAccount = (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(1906, 1906);

            //ソートキー:発注日 ASC
            WEB3EquityMarginSortKey[] l_sortKeys = new WEB3EquityMarginSortKey[1];

            l_sortKeys[0] = new WEB3EquityMarginSortKey();
            //弁済区分
            l_sortKeys[0].keyItem = "repaymentDiv";
            l_sortKeys[0].ascDesc = "D";
            // create検索条件データコンテナ
            Object[] l_searchCondContainers =
                l_equityMarginExecuteReferenceServiceImpl.createQueryDataContainer(l_subAccount, l_productCode,
                    l_marketCode, l_orderBizDate, l_orderStatus,null);

            // createソート条件
            String l_strSortCond = l_equityMarginExecuteReferenceServiceImpl.createSortCond(l_sortKeys);
            log.debug(l_strSortCond);
            
            //doFindAllQuery(
            //final String tableName, final String where, final String oderby, 
            //final String conditions, final Object bindVars[])
            List l_result = Processors.getDefaultProcessor().doFindAllQuery(
                EqtypeOrderUnitRow.TYPE, l_strSearchCond, l_strSortCond, null, l_searchCondContainers);

            assertEquals(l_result.size(), 3);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(0)).getOrderUnitId(), 1906);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(1)).getOrderUnitId(), 1907);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(2)).getOrderUnitId(), 1908);
            log.exiting(STR_METHOD_NAME);

        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

    }

    /**
     * testCreateQueryString_C0024<BR>
     *
     */
    public void testCreateQueryString_C0024()
    {
        //log
        final String STR_METHOD_NAME = "testCreateQueryString_C0024()";
        log.entering(TEST_START + STR_METHOD_NAME);

        EqtypeOrderUnitParams l_eqtypeOrderUnitParams = getEqtypeOrderUnitRow();

        //発注日
        Date l_orderBizDate = WEB3DateUtility.getDate("20070101","yyyyMMdd");

        String l_productCode = null;

        String l_marketCode = null;

        //株式・信用注文約定照会サービスImpl
        WEB3EquityMarginExecuteReferenceServiceImpl l_equityMarginExecuteReferenceServiceImpl =
            new WEB3EquityMarginExecuteReferenceServiceImpl();

        //注文受付状態
        OrderStatus l_orderStatus = l_equityMarginExecuteReferenceServiceImpl.new OrderStatus();

        l_orderStatus.interestEquityFlag = true;
        // create検索条件文字列
        String l_strSearchCond;
        try
        {
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setTaxType(TaxTypeEnum.SPECIAL_WITHHOLD);
            l_eqtypeOrderUnitParams.setOrderUnitId(1907);
            l_eqtypeOrderUnitParams.setProductId(1909);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070102","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setTaxType(TaxTypeEnum.SPECIAL);
            l_eqtypeOrderUnitParams.setProductId(1908);
            l_eqtypeOrderUnitParams.setOrderUnitId(1908);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070103","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            //create検索条件文字列
            l_strSearchCond = l_equityMarginExecuteReferenceServiceImpl.createQueryString(
                l_productCode,
                l_marketCode,
                l_orderBizDate,
                l_orderStatus,
                null);
            log.debug(l_strSearchCond);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            WEB3GentradeSubAccount l_subAccount;
            l_subAccount = (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(1906, 1906);

            //ソートキー:発注日 ASC
            WEB3EquityMarginSortKey[] l_sortKeys = new WEB3EquityMarginSortKey[1];

            l_sortKeys[0] = new WEB3EquityMarginSortKey();
            //弁済期限値
            l_sortKeys[0].keyItem = "repaymentTimeLimit";
            l_sortKeys[0].ascDesc = "A";
            // create検索条件データコンテナ
            Object[] l_searchCondContainers =
                l_equityMarginExecuteReferenceServiceImpl.createQueryDataContainer(l_subAccount, l_productCode,
                    l_marketCode, l_orderBizDate, l_orderStatus,null);

            // createソート条件
            String l_strSortCond = l_equityMarginExecuteReferenceServiceImpl.createSortCond(l_sortKeys);
            log.debug(l_strSortCond);
            
            //doFindAllQuery(
            //final String tableName, final String where, final String oderby, 
            //final String conditions, final Object bindVars[])
            List l_result = Processors.getDefaultProcessor().doFindAllQuery(
                EqtypeOrderUnitRow.TYPE, l_strSearchCond, l_strSortCond, null, l_searchCondContainers);

            assertEquals(l_result.size(), 3);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(0)).getOrderUnitId(), 1906);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(1)).getOrderUnitId(), 1907);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(2)).getOrderUnitId(), 1908);
            log.exiting(STR_METHOD_NAME);

        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

    }

    /**
     * testCreateQueryString_C0025<BR>
     *
     */
    public void testCreateQueryString_C0025()
    {
        //log
        final String STR_METHOD_NAME = "testCreateQueryString_C0025()";
        log.entering(TEST_START + STR_METHOD_NAME);

        EqtypeOrderUnitParams l_eqtypeOrderUnitParams = getEqtypeOrderUnitRow();

        //発注日
        Date l_orderBizDate = WEB3DateUtility.getDate("20070101","yyyyMMdd");

        String l_productCode = null;

        String l_marketCode = null;

        //株式・信用注文約定照会サービスImpl
        WEB3EquityMarginExecuteReferenceServiceImpl l_equityMarginExecuteReferenceServiceImpl =
            new WEB3EquityMarginExecuteReferenceServiceImpl();

        //注文受付状態
        OrderStatus l_orderStatus = l_equityMarginExecuteReferenceServiceImpl.new OrderStatus();

        l_orderStatus.interestEquityFlag = true;
        // create検索条件文字列
        String l_strSearchCond;
        try
        {
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setTaxType(TaxTypeEnum.SPECIAL_WITHHOLD);
            l_eqtypeOrderUnitParams.setOrderUnitId(1907);
            l_eqtypeOrderUnitParams.setProductId(1909);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070102","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setTaxType(TaxTypeEnum.SPECIAL);
            l_eqtypeOrderUnitParams.setProductId(1908);
            l_eqtypeOrderUnitParams.setOrderUnitId(1908);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070103","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            //create検索条件文字列
            l_strSearchCond = l_equityMarginExecuteReferenceServiceImpl.createQueryString(
                l_productCode,
                l_marketCode,
                l_orderBizDate,
                l_orderStatus,
                null);
            log.debug(l_strSearchCond);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            WEB3GentradeSubAccount l_subAccount;
            l_subAccount = (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(1906, 1906);

            //ソートキー:発注日 ASC
            WEB3EquityMarginSortKey[] l_sortKeys = new WEB3EquityMarginSortKey[1];

            l_sortKeys[0] = new WEB3EquityMarginSortKey();
            //弁済期限値
            l_sortKeys[0].keyItem = "repaymentTimeLimit";
            l_sortKeys[0].ascDesc = "D";
            // create検索条件データコンテナ
            Object[] l_searchCondContainers =
                l_equityMarginExecuteReferenceServiceImpl.createQueryDataContainer(l_subAccount, l_productCode,
                    l_marketCode, l_orderBizDate, l_orderStatus,null);

            // createソート条件
            String l_strSortCond = l_equityMarginExecuteReferenceServiceImpl.createSortCond(l_sortKeys);
            log.debug(l_strSortCond);
            
            //doFindAllQuery(
            //final String tableName, final String where, final String oderby, 
            //final String conditions, final Object bindVars[])
            List l_result = Processors.getDefaultProcessor().doFindAllQuery(
                EqtypeOrderUnitRow.TYPE, l_strSearchCond, l_strSortCond, null, l_searchCondContainers);

            assertEquals(l_result.size(), 3);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(0)).getOrderUnitId(), 1906);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(1)).getOrderUnitId(), 1907);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(2)).getOrderUnitId(), 1908);
            log.exiting(STR_METHOD_NAME);

        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

    }

    /**
     * testCreateQueryString_C0026<BR>
     *
     */
    public void testCreateQueryString_C0026()
    {
        //log
        final String STR_METHOD_NAME = "testCreateQueryString_C0026()";
        log.entering(TEST_START + STR_METHOD_NAME);

        EqtypeOrderUnitParams l_eqtypeOrderUnitParams = getEqtypeOrderUnitRow();

        //発注日
        Date l_orderBizDate = WEB3DateUtility.getDate("20070101","yyyyMMdd");

        String l_productCode = null;

        String l_marketCode = null;

        //株式・信用注文約定照会サービスImpl
        WEB3EquityMarginExecuteReferenceServiceImpl l_equityMarginExecuteReferenceServiceImpl =
            new WEB3EquityMarginExecuteReferenceServiceImpl();

        //注文受付状態
        OrderStatus l_orderStatus = l_equityMarginExecuteReferenceServiceImpl.new OrderStatus();

        l_orderStatus.interestEquityFlag = true;
        // create検索条件文字列
        String l_strSearchCond;
        try
        {
            l_eqtypeOrderUnitParams.setRepaymentNum(100);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setRepaymentType(WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS);
            l_eqtypeOrderUnitParams.setRepaymentNum(700);
            l_eqtypeOrderUnitParams.setOrderUnitId(1907);
            l_eqtypeOrderUnitParams.setProductId(1909);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070102","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setRepaymentNum(600);
            l_eqtypeOrderUnitParams.setRepaymentType(WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN);
            l_eqtypeOrderUnitParams.setTaxType(TaxTypeEnum.SPECIAL);
            l_eqtypeOrderUnitParams.setProductId(1908);
            l_eqtypeOrderUnitParams.setOrderUnitId(1908);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070103","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setRepaymentNum(500);
            l_eqtypeOrderUnitParams.setRepaymentType(WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN);
            l_eqtypeOrderUnitParams.setOrderUnitId(1909);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setRepaymentNum(550);
            l_eqtypeOrderUnitParams.setRepaymentType(WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN);
            l_eqtypeOrderUnitParams.setOrderUnitId(1910);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            //create検索条件文字列
            l_strSearchCond = l_equityMarginExecuteReferenceServiceImpl.createQueryString(
                l_productCode,
                l_marketCode,
                l_orderBizDate,
                l_orderStatus,
                null);
            log.debug(l_strSearchCond);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            WEB3GentradeSubAccount l_subAccount;
            l_subAccount = (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(1906, 1906);

            //ソートキー:発注日 ASC
            WEB3EquityMarginSortKey[] l_sortKeys = new WEB3EquityMarginSortKey[2];

            l_sortKeys[0] = new WEB3EquityMarginSortKey();
            l_sortKeys[1] = new WEB3EquityMarginSortKey();
            //弁済区分
            l_sortKeys[0].keyItem = "repaymentDiv";
            l_sortKeys[0].ascDesc = "A";
            //弁済期限値
            l_sortKeys[1].keyItem = "repaymentTimeLimit";
            l_sortKeys[1].ascDesc = "D";
            // create検索条件データコンテナ
            Object[] l_searchCondContainers =
                l_equityMarginExecuteReferenceServiceImpl.createQueryDataContainer(l_subAccount, l_productCode,
                    l_marketCode, l_orderBizDate, l_orderStatus,null);

            // createソート条件
            String l_strSortCond = l_equityMarginExecuteReferenceServiceImpl.createSortCond(l_sortKeys);
            log.debug(l_strSortCond);
            
            //doFindAllQuery(
            //final String tableName, final String where, final String oderby, 
            //final String conditions, final Object bindVars[])
            List l_result = Processors.getDefaultProcessor().doFindAllQuery(
                EqtypeOrderUnitRow.TYPE, l_strSearchCond, l_strSortCond, null, l_searchCondContainers);

            assertEquals(l_result.size(), 5);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(0)).getOrderUnitId(), 1907);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(1)).getOrderUnitId(), 1906);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(2)).getOrderUnitId(), 1908);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(3)).getOrderUnitId(), 1910);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(4)).getOrderUnitId(), 1909);
            log.exiting(STR_METHOD_NAME);

        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

    }

    /**
     * testCreateQueryString_C0027<BR>
     *
     */
    public void testCreateQueryString_C0027()
    {
        //log
        final String STR_METHOD_NAME = "testCreateQueryString_C0027()";
        log.entering(TEST_START + STR_METHOD_NAME);

        EqtypeOrderUnitParams l_eqtypeOrderUnitParams = getEqtypeOrderUnitRow();

        //発注日
        Date l_orderBizDate = WEB3DateUtility.getDate("20070101","yyyyMMdd");

        String l_productCode = null;

        String l_marketCode = null;

        //株式・信用注文約定照会サービスImpl
        WEB3EquityMarginExecuteReferenceServiceImpl l_equityMarginExecuteReferenceServiceImpl =
            new WEB3EquityMarginExecuteReferenceServiceImpl();

        //注文受付状態
        OrderStatus l_orderStatus = l_equityMarginExecuteReferenceServiceImpl.new OrderStatus();

        l_orderStatus.interestEquityFlag = true;
        l_orderStatus.interestMarginFlag = true;
        l_orderStatus.offFloor = true;
        l_orderStatus.swapFlag = true;
        // create検索条件文字列
        String l_strSearchCond;
        try
        {
            l_eqtypeOrderUnitParams.setRepaymentNum(100);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setRepaymentType(WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS);
            l_eqtypeOrderUnitParams.setRepaymentNum(700);
            l_eqtypeOrderUnitParams.setOrderUnitId(1907);
            l_eqtypeOrderUnitParams.setProductId(1909);
            l_eqtypeOrderUnitParams.setSonarTradedCode("16");
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
            l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070102","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setRepaymentNum(600);
            l_eqtypeOrderUnitParams.setRepaymentType(WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN);
            l_eqtypeOrderUnitParams.setTaxType(TaxTypeEnum.SPECIAL);
            l_eqtypeOrderUnitParams.setProductId(1908);
            l_eqtypeOrderUnitParams.setOrderUnitId(1908);
            l_eqtypeOrderUnitParams.setSonarTradedCode("51");
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.CLOSE_MARGIN_LONG);
            l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070103","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setRepaymentNum(500);
            l_eqtypeOrderUnitParams.setRepaymentType(WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.CLOSE_MARGIN_SHORT);
            l_eqtypeOrderUnitParams.setOrderUnitId(1909);
            l_eqtypeOrderUnitParams.setSonarTradedCode("52");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_eqtypeOrderUnitParams.setRepaymentNum(550);
            l_eqtypeOrderUnitParams.setRepaymentType(WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN);
            l_eqtypeOrderUnitParams.setOrderUnitId(1910);
            l_eqtypeOrderUnitParams.setSonarTradedCode("53");
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.MARGIN_SHORT);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            //create検索条件文字列
            l_strSearchCond = l_equityMarginExecuteReferenceServiceImpl.createQueryString(
                l_productCode,
                l_marketCode,
                l_orderBizDate,
                l_orderStatus,
                null);
            log.debug(l_strSearchCond);
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            WEB3GentradeSubAccount l_subAccount;
            l_subAccount = (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(1906, 1906);

            //ソートキー:発注日 ASC
            WEB3EquityMarginSortKey[] l_sortKeys = new WEB3EquityMarginSortKey[2];

            l_sortKeys[0] = new WEB3EquityMarginSortKey();
            l_sortKeys[1] = new WEB3EquityMarginSortKey();
            //弁済区分
            l_sortKeys[0].keyItem = "repaymentDiv";
            l_sortKeys[0].ascDesc = "A";
            //弁済期限値
            l_sortKeys[1].keyItem = "repaymentTimeLimit";
            l_sortKeys[1].ascDesc = "D";
            // create検索条件データコンテナ
            Object[] l_searchCondContainers =
                l_equityMarginExecuteReferenceServiceImpl.createQueryDataContainer(l_subAccount, l_productCode,
                    l_marketCode, l_orderBizDate, l_orderStatus,null);
            for (int i = 0; i < l_searchCondContainers.length; i++)
            {
                log.debug(l_searchCondContainers[i].toString());
            }
 
            // createソート条件
            String l_strSortCond = l_equityMarginExecuteReferenceServiceImpl.createSortCond(l_sortKeys);
            log.debug(l_strSortCond);
            
            //doFindAllQuery(
            //final String tableName, final String where, final String oderby, 
            //final String conditions, final Object bindVars[])
            List l_result = Processors.getDefaultProcessor().doFindAllQuery(
                EqtypeOrderUnitRow.TYPE, l_strSearchCond, l_strSortCond, null, l_searchCondContainers);

            assertEquals(l_result.size(), 5);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(0)).getOrderUnitId(), 1907);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(1)).getOrderUnitId(), 1906);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(2)).getOrderUnitId(), 1908);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(3)).getOrderUnitId(), 1910);
            assertEquals(((EqtypeOrderUnitRow)l_result.get(4)).getOrderUnitId(), 1909);
            log.exiting(STR_METHOD_NAME);

        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

    }

    /**
     * testIsChangeCancelEnableTimeZone_C0001<BR>
     *
     */
    public void testIsChangeCancelEnableTimeZone_C0001()
    {
        //log
        final String STR_METHOD_NAME = "testIsChangeCancelEnableTimeZone_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = getEqtypeOrderUnitRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1906);
            TestDBUtility.insertWithDel(l_marketParams);

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            EqTypeOrderManager l_eqTypeOrderManager =
                (EqTypeOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            EqTypeOrderUnit l_eqTypeOrderUnit = (EqTypeOrderUnit) l_eqTypeOrderManager.getOrderUnit(1906);
            
            //株式・信用注文約定照会サービスImpl
            WEB3EquityMarginExecuteReferenceServiceImpl l_equityMarginExecuteReferenceServiceImpl =
                new WEB3EquityMarginExecuteReferenceServiceImpl();

            l_clendarContext.setOrderAcceptTransaction("07");  
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_clendarContext);
            
            boolean l_blnResult =
                l_equityMarginExecuteReferenceServiceImpl.isChangeCancelEnableTimeZone(l_eqTypeOrderUnit);

            assertEquals(false, l_blnResult);
            log.exiting(STR_METHOD_NAME);
        }
        catch (WEB3MockObjectRuntimeException e)
        {
            WEB3SystemLayerException l_ex1 = (WEB3SystemLayerException) e.getCause();
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017.getErrorTag(), l_ex1.getErrorInfo().getErrorTag());

        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);

    }

    /**
     * testIsChangeCancelEnableTimeZone_C0002<BR>
     *
     */
    public void testIsChangeCancelEnableTimeZone_C0002()
    {
        //log
        final String STR_METHOD_NAME = "testIsChangeCancelEnableTimeZone_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = getEqtypeOrderUnitRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1906);
            TestDBUtility.insertWithDel(l_marketParams);

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            EqTypeOrderManager l_eqTypeOrderManager =
                (EqTypeOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            EqTypeOrderUnit l_eqTypeOrderUnit = (EqTypeOrderUnit) l_eqTypeOrderManager.getOrderUnit(1906);
            
            //株式・信用注文約定照会サービスImpl
            WEB3EquityMarginExecuteReferenceServiceImpl l_equityMarginExecuteReferenceServiceImpl =
                new WEB3EquityMarginExecuteReferenceServiceImpl();

            l_clendarContext.setOrderAcceptTransaction("06");  
            
            if (l_clendarContext != null)
            {
                log.debug("l_clendarContext.getBranchCode() = " + l_clendarContext.getBranchCode());    
            }                
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_clendarContext);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(l_tradingTimeParams.getRowType());
            l_tradingTimeParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_tradingTimeParams.setBranchCode(l_clendarContext.getBranchCode());
            
            l_tradingTimeParams.setBizDateType(l_clendarContext.getBizDateType());
            l_tradingTimeParams.setMarketCode(l_clendarContext.getMarketCode());
            l_tradingTimeParams.setProductCode(l_clendarContext.getProductCode());
            l_tradingTimeParams.setBizdateCalcParameter("1");
            l_tradingTimeParams.setSubmitMarketTrigger("0");
            
            l_tradingTimeParams.setTradingTimeType("03");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            l_tradingTimeParams.setTradingTimeType("19");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setBizDateType("0");
            l_tradingTimeParams.setMarketCode("SP");
            l_tradingTimeParams.setProductCode("N8080");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            OrderAcceptStatusParams l_orderAcceptStatusParams = 
                TestDBUtility.getOrderAcceptStatusRow();
            l_orderAcceptStatusParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_orderAcceptStatusParams.setBranchCode(l_clendarContext.getBranchCode());
            l_orderAcceptStatusParams.setOrderAccProduct(l_clendarContext.getOrderAcceptProduct());
            l_orderAcceptStatusParams.setOrderAccTransaction(l_clendarContext.getOrderAcceptTransaction());
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            //受付時間区分： ”株式・信用”
            //注文受付商品： ”株式”
            //注文受付トランザクション： ”照会”
            //”受付可”  
            l_orderAcceptStatusParams.setOrderAccProduct("01");
            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);


            //注文受付商品： ”立会外分売”
            //注文受付トランザクション： ”照会”
            //”受付可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
            l_orderAcceptStatusParams.setOrderAccProduct("25");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            //注文受付商品： ”信用取引”
            //注文受付トランザクション： ”照会”
            //”受付可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
            l_orderAcceptStatusParams.setOrderAccProduct("03");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);
            
            Date l_datExpect = WEB3DateUtility.getDate("20070102","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);

            //Time = mockTime
            Timestamp l_tsOrderAcceptDate = WEB3GentradeTradingTimeManagementForMock.getTimestampTag();
            String l_strExpectValue = "1";
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(l_tsOrderAcceptDate, l_strExpectValue);

            boolean l_blnResult =
                l_equityMarginExecuteReferenceServiceImpl.isChangeCancelEnableTimeZone(l_eqTypeOrderUnit);

            assertEquals(true, l_blnResult);
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);

    }

    /**
     * testIsChangeCancelEnableTimeZone_C0003<BR>
     *
     */
    public void testIsChangeCancelEnableTimeZone_C0003()
    {
        //log
        final String STR_METHOD_NAME = "testIsChangeCancelEnableTimeZone_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = getEqtypeOrderUnitRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1906);
            TestDBUtility.insertWithDel(l_marketParams);

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            EqTypeOrderManager l_eqTypeOrderManager =
                (EqTypeOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            EqTypeOrderUnit l_eqTypeOrderUnit = (EqTypeOrderUnit) l_eqTypeOrderManager.getOrderUnit(1906);
            
            //株式・信用注文約定照会サービスImpl
            WEB3EquityMarginExecuteReferenceServiceImpl l_equityMarginExecuteReferenceServiceImpl =
                new WEB3EquityMarginExecuteReferenceServiceImpl();

            
            l_clendarContext.setOrderAcceptTransaction("07");  
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_clendarContext);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_tradingTimeParams.setBranchCode(l_clendarContext.getBranchCode());
            l_tradingTimeParams.setTradingTimeType(l_clendarContext.getTradingTimeType());
            l_tradingTimeParams.setBizDateType(l_clendarContext.getBizDateType());
            l_tradingTimeParams.setMarketCode(l_clendarContext.getMarketCode());
            l_tradingTimeParams.setProductCode(l_clendarContext.getProductCode());
            
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            OrderAcceptStatusParams l_orderAcceptStatusParams = 
                TestDBUtility.getOrderAcceptStatusRow();
            l_orderAcceptStatusParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_orderAcceptStatusParams.setBranchCode(l_clendarContext.getBranchCode());
            l_orderAcceptStatusParams.setOrderAccProduct(l_clendarContext.getOrderAcceptProduct());
            l_orderAcceptStatusParams.setOrderAccTransaction(l_clendarContext.getOrderAcceptTransaction());
            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
            //受付時間区分： ”株式・信用”
            //注文受付商品： ”株式”
            //注文受付トランザクション： ”照会”
            //”受付可”  
            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);


            //注文受付商品： ”立会外分売”
            //注文受付トランザクション： ”照会”
            //”受付可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
            l_orderAcceptStatusParams.setOrderAccProduct("25");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            //注文受付商品： ”信用取引”
            //注文受付トランザクション： ”照会”
            //”受付可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
            l_orderAcceptStatusParams.setOrderAccProduct("03");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);
            
            Date l_datExpect = WEB3DateUtility.getDate("20060101","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);
            
            //Time = mockTime
            Timestamp l_tsOrderAcceptDate = WEB3GentradeTradingTimeManagementForMock.getTimestampTag();
            String l_strExpectValue = "1";
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(l_tsOrderAcceptDate, l_strExpectValue);
            
            boolean l_blnResult = l_equityMarginExecuteReferenceServiceImpl.isChangeCancelEnableTimeZone(l_eqTypeOrderUnit);

            assertEquals(true, l_blnResult);
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);

    }

    /**
     * testIsChangeCancelEnableTimeZone_C0004<BR>
     *
     */
    public void testIsChangeCancelEnableTimeZone_C0004()
    {
        //log
        final String STR_METHOD_NAME = "testIsChangeCancelEnableTimeZone_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = getEqtypeOrderUnitRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1906);
            TestDBUtility.insertWithDel(l_marketParams);

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            EqTypeOrderManager l_eqTypeOrderManager =
                (EqTypeOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            EqTypeOrderUnit l_eqTypeOrderUnit = (EqTypeOrderUnit) l_eqTypeOrderManager.getOrderUnit(1906);
            
            //株式・信用注文約定照会サービスImpl
            WEB3EquityMarginExecuteReferenceServiceImpl l_equityMarginExecuteReferenceServiceImpl =
                new WEB3EquityMarginExecuteReferenceServiceImpl();

            l_clendarContext.setOrderAcceptTransaction("06");
            l_clendarContext.setBizDateType("0"); 
            l_clendarContext.setTradingTimeType("0"); 
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_clendarContext);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(l_tradingTimeParams.getRowType());
            l_tradingTimeParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_tradingTimeParams.setBranchCode(l_clendarContext.getBranchCode());
            
            l_tradingTimeParams.setBizDateType(l_clendarContext.getBizDateType());
            l_tradingTimeParams.setMarketCode(l_clendarContext.getMarketCode());
            l_tradingTimeParams.setProductCode(l_clendarContext.getProductCode());
            l_tradingTimeParams.setBizdateCalcParameter("1");
            l_tradingTimeParams.setSubmitMarketTrigger("0");
            
            l_tradingTimeParams.setTradingTimeType("03");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            l_tradingTimeParams.setTradingTimeType("19");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            l_tradingTimeParams.setTradingTimeType("01");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            OrderAcceptStatusParams l_orderAcceptStatusParams = 
                TestDBUtility.getOrderAcceptStatusRow();
            l_orderAcceptStatusParams.setInstitutionCode(l_clendarContext.getInstitutionCode());
            l_orderAcceptStatusParams.setBranchCode(l_clendarContext.getBranchCode());
            l_orderAcceptStatusParams.setOrderAccProduct(l_clendarContext.getOrderAcceptProduct());
            l_orderAcceptStatusParams.setOrderAccTransaction(l_clendarContext.getOrderAcceptTransaction());
            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
            //受付時間区分： ”株式・信用”
            //注文受付商品： ”株式”
            //注文受付トランザクション： ”照会”
            //”受付可”  
            l_orderAcceptStatusParams.setOrderAccProduct("01");
            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParam = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParam);
            
            //注文受付商品： ”立会外分売”
            //注文受付トランザクション： ”照会”
            //”受付可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
            l_orderAcceptStatusParams.setOrderAccProduct("25");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            //注文受付商品： ”信用取引”
            //注文受付トランザクション： ”照会”
            //”受付可”
            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
            l_orderAcceptStatusParams.setOrderAccProduct("03");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);
            
            //Time = mockTime
            Timestamp l_tsOrderAcceptDate = WEB3GentradeTradingTimeManagementForMock.getTimestampTag();
            String l_strExpectValue = "0";
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(l_tsOrderAcceptDate, l_strExpectValue);
            
            TestDBUtility.deleteAll(OrderexecutionEndRow.TYPE);
            
            boolean l_blnResult =
                l_equityMarginExecuteReferenceServiceImpl.isChangeCancelEnableTimeZone(l_eqTypeOrderUnit);

            assertEquals(false, l_blnResult);
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);

    }

    
    /////////////////////////////////////////////////////////////////////////////////
    //isChangeCancelEnableTimeZone
    //isPTS市場( )=true
    //validate注文受付可能( )異常 return false
    public void testIsChangeCancelEnableTimeZone1()
    {
        final String STR_METHOD_NAME = "testIsChangeCancelEnableTimeZone1()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams1 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams1.setOrderUnitId(3304148080001L);
            l_eqtypeOrderUnitParams1.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams1.setRequestType(WEB3RequestTypeDef.DEFAULT);
            l_eqtypeOrderUnitParams1.setTraderId(3338111121L);
            l_eqtypeOrderUnitParams1.setOrderRev("00");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams1);
            
            TestDBUtility.deleteAll(MarketPreferencesParams.TYPE);
            MarketPreferencesParams l_MarketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_MarketPreferencesParams.setName(WEB3MarketPreferencesNameDef.EQUITY_PTS_MARKET_DIV);
            l_MarketPreferencesParams.setNameSerialNo(1);
            l_MarketPreferencesParams.setValue(WEB3EquityPTSMarketDivDef.PTS_MARKET_YES);
            l_MarketPreferencesParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_MarketPreferencesParams);
            
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode(WEB3MarketCodeDef.TOKYO);
            TestDBUtility.insertWithDel(l_marketParams);
            
            EqTypeOrderUnit l_orderUnit = new EqTypeOrderUnitImpl(null, 3304148080001L);
            WEB3EquityMarginExecuteReferenceServiceImpl l_equityMarginExecuteReferenceServiceImpl =
                new WEB3EquityMarginExecuteReferenceServiceImpl();
            boolean l_blnIsChangeCancelEnableTimeZone = l_equityMarginExecuteReferenceServiceImpl.isChangeCancelEnableTimeZone(l_orderUnit);
            assertFalse(l_blnIsChangeCancelEnableTimeZone);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //isChangeCancelEnableTimeZone
    //isPTS市場( )= true
    //return false
    public void testIsChangeCancelEnableTimeZone2()
    {
        final String STR_METHOD_NAME = "testIsChangeCancelEnableTimeZone2()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setMarketCode("1");
            l_clendarContext.setTradingTimeType("01");
            l_clendarContext.setBizDateType("4");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext", l_clendarContext);
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams1 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams1.setOrderUnitId(3304148080001L);
            l_eqtypeOrderUnitParams1.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams1.setRequestType(WEB3RequestTypeDef.DEFAULT);
            l_eqtypeOrderUnitParams1.setTraderId(3338111121L);
            l_eqtypeOrderUnitParams1.setOrderRev("00");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams1);
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode("1");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("3");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(MarketPreferencesParams.TYPE);
            MarketPreferencesParams l_MarketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_MarketPreferencesParams.setName(WEB3MarketPreferencesNameDef.EQUITY_PTS_MARKET_DIV);
            l_MarketPreferencesParams.setNameSerialNo(1);
            l_MarketPreferencesParams.setValue(WEB3EquityPTSMarketDivDef.PTS_MARKET_YES);
            l_MarketPreferencesParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_MarketPreferencesParams);
            
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode(WEB3MarketCodeDef.TOKYO);
            TestDBUtility.insertWithDel(l_marketParams);
            
            EqTypeOrderUnit l_orderUnit = new EqTypeOrderUnitImpl(null, 3304148080001L);
            WEB3EquityMarginExecuteReferenceServiceImpl l_equityMarginExecuteReferenceServiceImpl =
                new WEB3EquityMarginExecuteReferenceServiceImpl();
            boolean l_blnIsChangeCancelEnableTimeZone = l_equityMarginExecuteReferenceServiceImpl.isChangeCancelEnableTimeZone(l_orderUnit);
            assertFalse(l_blnIsChangeCancelEnableTimeZone);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //isChangeCancelEnableTimeZone
    //isPTS市場( )= false
    //return true
    public void testIsChangeCancelEnableTimeZone3()
    {
        final String STR_METHOD_NAME = "testIsChangeCancelEnableTimeZone3()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setMarketCode("1");
            l_clendarContext.setTradingTimeType("01");
            l_clendarContext.setBizDateType("4");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext", l_clendarContext);
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams1 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams1.setOrderUnitId(3304148080001L);
            l_eqtypeOrderUnitParams1.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams1.setRequestType(WEB3RequestTypeDef.DEFAULT);
            l_eqtypeOrderUnitParams1.setTraderId(3338111121L);
            l_eqtypeOrderUnitParams1.setOrderRev("00");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams1);
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode("1");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("4");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(MarketPreferencesParams.TYPE);
            
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode(WEB3MarketCodeDef.TOKYO);
            TestDBUtility.insertWithDel(l_marketParams);
            
            EqTypeOrderUnit l_orderUnit = new EqTypeOrderUnitImpl(null, 3304148080001L);
            WEB3EquityMarginExecuteReferenceServiceImpl l_equityMarginExecuteReferenceServiceImpl =
                new WEB3EquityMarginExecuteReferenceServiceImpl();
            boolean l_blnIsChangeCancelEnableTimeZone = l_equityMarginExecuteReferenceServiceImpl.isChangeCancelEnableTimeZone(l_orderUnit);
            assertFalse(l_blnIsChangeCancelEnableTimeZone);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * validateOrderForChangeability_C0001<BR>
     *
     */
    public void testValidateOrderForChangeability_C0001()
    {
        //log
        final String STR_METHOD_NAME = "validateOrderForChangeability_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.equity.WEB3EquityOrderManager",
            "validateOrderForChangeability",
            new Class[] {Order.class}, new WEB3BaseException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, ""));
        //sub_accountテーブル
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        //main_accountテーブル
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        //marketテーブル
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        //productテーブル
        ProductParams l_equityProductParams = TestDBUtility.getProductRow();
        //eqtype_orderテーブル
        EqtypeOrderParams l_eqtypeOrderParams = getEqtypeOrderRow();
        
        //株式・信用注文約定照会サービスImpl
        WEB3EquityMarginExecuteReferenceServiceImpl l_equityMarginExecuteReferenceServiceImpl =
            new WEB3EquityMarginExecuteReferenceServiceImpl();
        try
        {
            l_subAccountParams.setSubAccountId(1906);
            l_subAccountParams.setAccountId(1906);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            l_mainAccountParams.setAccountId(1906);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            l_marketParams.setMarketId(1906);
            TestDBUtility.insertWithDel(l_marketParams);
            l_equityProductParams.setProductId(1906);
            TestDBUtility.insertWithDel(l_equityProductParams);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(2099);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            //accountId, subAccountId
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(1906, 1906);
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
            WEB3GentradeBranchForMock l_branch = new WEB3GentradeBranchForMock(33381);
            
            WEB3GentradeMarket l_market = new WEB3GentradeMarket(1906);
            WEB3EquityProduct l_product = new WEB3EquityProduct(1906);
            EqTypeOrderManager l_eqTypeOrderManager =
                (EqTypeOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            EqTypeOrderUnit l_eqTypeOrderUnit = (EqTypeOrderUnit) l_eqTypeOrderManager.getOrderUnit(1906);
            //WEB3GentradeSubAccount l_subAccount, WEB3EquityProduct l_product,
            //WEB3GentradeMarket l_market, WEB3GentradeBranch l_branch, EqTypeOrderUnit l_orderUnit
            l_equityMarginExecuteReferenceServiceImpl.validateOrderForChangeability(
                l_subAccount, l_product, l_market, l_branch, l_eqTypeOrderUnit);
            fail();
        }
        catch (WEB3BaseException l_web3BaseException)
        {
//            WEB3SystemLayerException l_ex1 = (WEB3SystemLayerException) e.getCause();
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_web3BaseException.getErrorInfo());

        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validateOrderForChangeability_C0002<BR>
     *
     */
    public void testValidateOrderForChangeability_C0002()
    {
        //log
        final String STR_METHOD_NAME = "validateOrderForChangeability_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.equity.WEB3EquityOrderManager",
            "validateOrderForChangeability",
            new Class[] {Order.class}, new Class[] {});
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.equity.WEB3EquityOrderManager",
            "validateTradedProduct",
            new Class[] {EqTypeProduct.class, Market.class},
            new WEB3BaseException(new ErrorInfo(),STR_METHOD_NAME));
        //sub_accountテーブル
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        //main_accountテーブル
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        //marketテーブル
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        //productテーブル
        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        //eqtype_orderテーブル
        EqtypeOrderParams l_eqtypeOrderParams = getEqtypeOrderRow();
        
        //株式・信用注文約定照会サービスImpl
        WEB3EquityMarginExecuteReferenceServiceImpl l_equityMarginExecuteReferenceServiceImpl =
            new WEB3EquityMarginExecuteReferenceServiceImpl();
        try
        {
            l_subAccountParams.setSubAccountId(1906);
            l_subAccountParams.setAccountId(1906);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            l_mainAccountParams.setAccountId(1906);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            l_marketParams.setMarketId(1906);
            TestDBUtility.insertWithDel(l_marketParams);
            
            ProductParams l_productParams1 = TestDBUtility.getProductRow();
            l_productParams1.setProductId(1906);
            l_productParams1.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams1);

            EqtypeProductParams l_eqProductParams1 = new EqtypeProductParams();
            l_eqProductParams1.setProductId(1906);
            l_eqProductParams1.setProductType(ProductTypeEnum.EQUITY);
            l_eqProductParams1.setInstitutionCode("A11");
            l_eqProductParams1.setProductCode("A11");
            l_eqProductParams1.setYearlyBooksClosingDate(Calendar.getInstance().getTime());
            l_eqProductParams1.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_eqProductParams1.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_eqProductParams1);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(2099);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            //銘柄ID：[1906] 市場ID：[1906]
            l_tradedProductParams.setProductId(1906);
            l_tradedProductParams.setMarketId(1906);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            //accountId, subAccountId
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(1906, 1906);
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
            WEB3GentradeBranchForMock l_branch = new WEB3GentradeBranchForMock(33381);
            
            WEB3GentradeMarket l_market = new WEB3GentradeMarket(1906);
            WEB3EquityProduct l_product = new WEB3EquityProduct(1906);
            EqTypeOrderManager l_eqTypeOrderManager =
                (EqTypeOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            EqTypeOrderUnit l_eqTypeOrderUnit = (EqTypeOrderUnit) l_eqTypeOrderManager.getOrderUnit(1906);
            //WEB3GentradeSubAccount l_subAccount, WEB3EquityProduct l_product,
            //WEB3GentradeMarket l_market, WEB3GentradeBranch l_branch, EqTypeOrderUnit l_orderUnit
            l_equityMarginExecuteReferenceServiceImpl.validateOrderForChangeability(
                l_subAccount, l_product, l_market, l_branch, l_eqTypeOrderUnit);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3BaseException.class, l_ex.getClass());

        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validateOrderForChangeability_C0003<BR>
     *
     */
    //PTS
    public void testValidateOrderForChangeability_C0003()
    {
        //log
        final String STR_METHOD_NAME = "validateOrderForChangeability_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.equity.WEB3EquityOrderManager",
            "validateOrderForChangeability",
            new Class[] {Order.class}, "");
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.equity.WEB3EquityOrderManager",
            "validateTradedProduct",
            new Class[] {EqTypeProduct.class, Market.class},
            null);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.equity.WEB3EquityOrderManager",
            "validateInsider",
            new Class[] {SubAccount.class, EqTypeProduct.class},
            "");

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.equity.WEB3EquityOrderManager",
            "validateAccountProductOrderStop",
            new Class[] {SubAccount.class,
                long.class,
                OrderTypeEnum.class},
            "");

        //sub_accountテーブル
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        //main_accountテーブル
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        //marketテーブル
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        //productテーブル
        ProductParams l_equityProductParams = TestDBUtility.getProductRow();
        //productテーブル
        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        //eqtype_orderテーブル
        EqtypeOrderParams l_eqtypeOrderParams = getEqtypeOrderRow();
        
        //株式・信用注文約定照会サービスImpl
        WEB3EquityMarginExecuteReferenceServiceImpl l_equityMarginExecuteReferenceServiceImpl =
            new WEB3EquityMarginExecuteReferenceServiceImpl();
        try
        {
            l_subAccountParams.setSubAccountId(1906);
            l_subAccountParams.setAccountId(1906);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            l_mainAccountParams.setAccountId(1906);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            l_marketParams.setMarketId(1906);
            TestDBUtility.insertWithDel(l_marketParams);
            //
            l_equityProductParams.setProductId(1906);
            TestDBUtility.insertWithDel(l_equityProductParams);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(2099);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            //銘柄ID：[1906] 市場ID：[1906]
            l_tradedProductParams.setProductId(1906);
            l_tradedProductParams.setMarketId(1906);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            ProductParams l_productParams1 = TestDBUtility.getProductRow();
            l_productParams1.setProductId(1906);
            l_productParams1.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams1);

            EqtypeProductParams l_eqProductParams1 = new EqtypeProductParams();
            l_eqProductParams1.setProductId(1906);
            l_eqProductParams1.setProductType(ProductTypeEnum.EQUITY);
            l_eqProductParams1.setInstitutionCode("A11");
            l_eqProductParams1.setProductCode("A11");
            l_eqProductParams1.setYearlyBooksClosingDate(Calendar.getInstance().getTime());
            l_eqProductParams1.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_eqProductParams1.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_eqProductParams1);
            
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            //accountId, subAccountId
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(1906, 1906);
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
            WEB3GentradeBranchForMock l_branch = new WEB3GentradeBranchForMock(33381);
            
            WEB3GentradeMarket l_market = new WEB3GentradeMarket(1906);
            WEB3EquityProduct l_product = new WEB3EquityProduct(1906);
            EqTypeOrderManager l_eqTypeOrderManager =
                (EqTypeOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            EqTypeOrderUnit l_eqTypeOrderUnit = (EqTypeOrderUnit) l_eqTypeOrderManager.getOrderUnit(1906);
            //WEB3GentradeSubAccount l_subAccount, WEB3EquityProduct l_product,
            //WEB3GentradeMarket l_market, WEB3GentradeBranch l_branch, EqTypeOrderUnit l_orderUnit
            l_equityMarginExecuteReferenceServiceImpl.validateOrderForChangeability(
                l_subAccount, l_product, l_market, l_branch, l_eqTypeOrderUnit);
            assertTrue(true);
        }
        catch (Exception l_ex)
        {
            l_ex.printStackTrace();
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validateOrderForChangeability_C0004<BR>
     *
     */
    public void testValidateOrderForChangeability_C0004()
    {
        //log
        final String STR_METHOD_NAME = "validateOrderForChangeability_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.equity.WEB3EquityOrderManager",
            "validateOrderForChangeability",
            new Class[] {Order.class}, "");
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.equity.WEB3EquityOrderManager",
            "validateTradedProduct",
            new Class[] {EqTypeProduct.class, Market.class},
            null);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.equity.WEB3EquityOrderManager",
            "validateInsider",
            new Class[] {SubAccount.class, EqTypeProduct.class},
            new WEB3BaseException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01356,
                "インサイダーチェックのため取引停止中です。"));

        //sub_accountテーブル
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        //main_accountテーブル
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        //marketテーブル
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        //productテーブル
        ProductParams l_equityProductParams = TestDBUtility.getProductRow();
        //productテーブル
        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        //eqtype_orderテーブル
        EqtypeOrderParams l_eqtypeOrderParams = getEqtypeOrderRow();
        
        //株式・信用注文約定照会サービスImpl
        WEB3EquityMarginExecuteReferenceServiceImpl l_equityMarginExecuteReferenceServiceImpl =
            new WEB3EquityMarginExecuteReferenceServiceImpl();
        try
        {
            l_subAccountParams.setSubAccountId(1906);
            l_subAccountParams.setAccountId(1906);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            l_mainAccountParams.setAccountId(1906);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            l_marketParams.setMarketId(1906);
            TestDBUtility.insertWithDel(l_marketParams);
            l_equityProductParams.setProductId(1906);
            TestDBUtility.insertWithDel(l_equityProductParams);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(2099);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            //銘柄ID：[1906] 市場ID：[1906]
            l_tradedProductParams.setProductId(1906);
            l_tradedProductParams.setMarketId(1906);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            ProductParams l_productParams1 = TestDBUtility.getProductRow();
            l_productParams1.setProductId(1906);
            l_productParams1.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams1);

            EqtypeProductParams l_eqProductParams1 = new EqtypeProductParams();
            l_eqProductParams1.setProductId(1906);
            l_eqProductParams1.setProductType(ProductTypeEnum.EQUITY);
            l_eqProductParams1.setInstitutionCode("A11");
            l_eqProductParams1.setProductCode("A11");
            l_eqProductParams1.setYearlyBooksClosingDate(Calendar.getInstance().getTime());
            l_eqProductParams1.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_eqProductParams1.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_eqProductParams1);
            
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            //accountId, subAccountId
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(1906, 1906);
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
            WEB3GentradeBranchForMock l_branch = new WEB3GentradeBranchForMock(33381);
            
            WEB3GentradeMarket l_market = new WEB3GentradeMarket(1906);
            WEB3EquityProduct l_product = new WEB3EquityProduct(1906);
            EqTypeOrderManager l_eqTypeOrderManager =
                (EqTypeOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            EqTypeOrderUnit l_eqTypeOrderUnit = (EqTypeOrderUnit) l_eqTypeOrderManager.getOrderUnit(1906);
            //WEB3GentradeSubAccount l_subAccount, WEB3EquityProduct l_product,
            //WEB3GentradeMarket l_market, WEB3GentradeBranch l_branch, EqTypeOrderUnit l_orderUnit
            l_equityMarginExecuteReferenceServiceImpl.validateOrderForChangeability(
                l_subAccount, l_product, l_market, l_branch, l_eqTypeOrderUnit);
            fail();
        }
        catch (WEB3BaseException l_web3BaseException)
        {
            log.error(STR_METHOD_NAME, l_web3BaseException);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01356, l_web3BaseException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validateOrderForChangeability_C0005<BR>
     *
     */
    public void testValidateOrderForChangeability_C0005()
    {
        //log
        final String STR_METHOD_NAME = "validateOrderForChangeability_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.equity.WEB3EquityOrderManager",
            "validateOrderForChangeability",
            new Class[] {Order.class}, "");

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
            "validateInsider",
            new Class[] {SubAccount.class, EqTypeProduct.class},
            "");

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.equity.WEB3EquityOrderManager",
            "validateAccountProductOrderStop",
            new Class[] {SubAccount.class,
                long.class,
                OrderTypeEnum.class},
            "");

        //sub_accountテーブル
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        //main_accountテーブル
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        //marketテーブル
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        //productテーブル
        ProductParams l_equityProductParams = TestDBUtility.getProductRow();
        //productテーブル
        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        //eqtype_orderテーブル
        EqtypeOrderParams l_eqtypeOrderParams = getEqtypeOrderRow();
        
        //株式・信用注文約定照会サービスImpl
        WEB3EquityMarginExecuteReferenceServiceImpl l_equityMarginExecuteReferenceServiceImpl =
            new WEB3EquityMarginExecuteReferenceServiceImpl();
        try
        {
            l_subAccountParams.setSubAccountId(1906);
            l_subAccountParams.setAccountId(1906);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            l_mainAccountParams.setAccountId(1906);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            l_marketParams.setMarketId(1906);
            TestDBUtility.insertWithDel(l_marketParams);
            l_equityProductParams.setProductId(1906);
            TestDBUtility.insertWithDel(l_equityProductParams);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(2099);
            l_eqtypeOrderUnitParams.setSonarTradedCode("51");
            //odercalog=3
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            //銘柄ID：[1906] 市場ID：[1906]
            l_tradedProductParams.setProductId(1906);
            l_tradedProductParams.setMarketId(1906);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            ProductParams l_productParams1 = TestDBUtility.getProductRow();
            l_productParams1.setProductId(1906);
            l_productParams1.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams1);

            EqtypeProductParams l_eqProductParams1 = new EqtypeProductParams();
            l_eqProductParams1.setProductId(1906);
            l_eqProductParams1.setProductType(ProductTypeEnum.EQUITY);
            l_eqProductParams1.setInstitutionCode("A11");
            l_eqProductParams1.setProductCode("A11");
            l_eqProductParams1.setYearlyBooksClosingDate(Calendar.getInstance().getTime());
            l_eqProductParams1.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_eqProductParams1.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_eqProductParams1);
            
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            //accountId, subAccountId
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(1906, 1906);
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
            WEB3GentradeBranchForMock l_branch = new WEB3GentradeBranchForMock(33381);
            
            WEB3GentradeMarket l_market = new WEB3GentradeMarket(1906);
            WEB3EquityProduct l_product = new WEB3EquityProduct(1906);
            EqTypeOrderManager l_eqTypeOrderManager =
                (EqTypeOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            EqTypeOrderUnit l_eqTypeOrderUnit = (EqTypeOrderUnit) l_eqTypeOrderManager.getOrderUnit(1906);
            //WEB3GentradeSubAccount l_subAccount, WEB3EquityProduct l_product,
            //WEB3GentradeMarket l_market, WEB3GentradeBranch l_branch, EqTypeOrderUnit l_orderUnit
            l_equityMarginExecuteReferenceServiceImpl.validateOrderForChangeability(
                l_subAccount, l_product, l_market, l_branch, l_eqTypeOrderUnit);
            WEB3MockObjectParamsValue l_value = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "validateTradedProductForMarginTrading",
                new Class[] {SubAccount.class,
                    WEB3EquityProduct.class, 
                    WEB3GentradeMarket.class, 
                    WEB3GentradeBranch.class, 
                    String.class, 
                    OrderCategEnum.class, 
                    boolean.class,
                    boolean.class});
            
            assertEquals("1", l_value.getCalled(0)[4]);
            assertEquals(Boolean.FALSE, l_value.getCalled(0)[6]);

        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validateOrderForChangeability_C0006<BR>
     *
     */
    public void testValidateOrderForChangeability_C0006()
    {
        //log
        final String STR_METHOD_NAME = "validateOrderForChangeability_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.equity.WEB3EquityOrderManager",
            "validateOrderForChangeability",
            new Class[] {Order.class}, "");

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
                new WEB3BaseException(new ErrorInfo(),STR_METHOD_NAME));
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.equity.WEB3EquityOrderManager",
            "validateInsider",
            new Class[] {SubAccount.class, EqTypeProduct.class},
            "");

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.equity.WEB3EquityOrderManager",
            "validateAccountProductOrderStop",
            new Class[] {SubAccount.class,
                long.class,
                OrderTypeEnum.class},
            "");

        //sub_accountテーブル
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        //main_accountテーブル
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        //marketテーブル
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        //productテーブル
        ProductParams l_equityProductParams = TestDBUtility.getProductRow();
        //productテーブル
        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        //eqtype_orderテーブル
        EqtypeOrderParams l_eqtypeOrderParams = getEqtypeOrderRow();
        
        //株式・信用注文約定照会サービスImpl
        WEB3EquityMarginExecuteReferenceServiceImpl l_equityMarginExecuteReferenceServiceImpl =
            new WEB3EquityMarginExecuteReferenceServiceImpl();
        try
        {
            l_subAccountParams.setSubAccountId(1906);
            l_subAccountParams.setAccountId(1906);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            l_mainAccountParams.setAccountId(1906);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            l_marketParams.setMarketId(1906);
            TestDBUtility.insertWithDel(l_marketParams);
            l_equityProductParams.setProductId(1906);
            TestDBUtility.insertWithDel(l_equityProductParams);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(2099);
            l_eqtypeOrderUnitParams.setSonarTradedCode("51");
            //odercalog=3
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            //銘柄ID：[1906] 市場ID：[1906]
            l_tradedProductParams.setProductId(1906);
            l_tradedProductParams.setMarketId(1906);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            ProductParams l_productParams1 = TestDBUtility.getProductRow();
            l_productParams1.setProductId(1906);
            l_productParams1.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams1);

            EqtypeProductParams l_eqProductParams1 = new EqtypeProductParams();
            l_eqProductParams1.setProductId(1906);
            l_eqProductParams1.setProductType(ProductTypeEnum.EQUITY);
            l_eqProductParams1.setInstitutionCode("A11");
            l_eqProductParams1.setProductCode("A11");
            l_eqProductParams1.setYearlyBooksClosingDate(Calendar.getInstance().getTime());
            l_eqProductParams1.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_eqProductParams1.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_eqProductParams1);
            
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            //accountId, subAccountId
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(1906, 1906);
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
            WEB3GentradeBranchForMock l_branch = new WEB3GentradeBranchForMock(33381);
            
            WEB3GentradeMarket l_market = new WEB3GentradeMarket(1906);
            WEB3EquityProduct l_product = new WEB3EquityProduct(1906);
            EqTypeOrderManager l_eqTypeOrderManager =
                (EqTypeOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            EqTypeOrderUnit l_eqTypeOrderUnit = (EqTypeOrderUnit) l_eqTypeOrderManager.getOrderUnit(1906);
            //WEB3GentradeSubAccount l_subAccount, WEB3EquityProduct l_product,
            //WEB3GentradeMarket l_market, WEB3GentradeBranch l_branch, EqTypeOrderUnit l_orderUnit
            l_equityMarginExecuteReferenceServiceImpl.validateOrderForChangeability(
                l_subAccount, l_product, l_market, l_branch, l_eqTypeOrderUnit);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3BaseException.class, l_ex.getClass());

        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * validateOrderForChangeability_C0007<BR>
     *
     */
    public void testValidateOrderForChangeability_C0007()
    {
        //log
        final String STR_METHOD_NAME = "validateOrderForChangeability_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.equity.WEB3EquityOrderManager",
            "validateOrderForChangeability",
            new Class[] {Order.class}, "");

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
            "validateInsider",
            new Class[] {SubAccount.class, EqTypeProduct.class},
            "");

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.equity.WEB3EquityOrderManager",
            "validateAccountProductOrderStop",
            new Class[] {SubAccount.class,
                long.class,
                OrderTypeEnum.class},
            "");
        
        //sub_accountテーブル
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        //main_accountテーブル
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        //marketテーブル
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        //productテーブル
        ProductParams l_equityProductParams = TestDBUtility.getProductRow();
        //productテーブル
        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        //eqtype_orderテーブル
        EqtypeOrderParams l_eqtypeOrderParams = getEqtypeOrderRow();
        
        //株式・信用注文約定照会サービスImpl
        WEB3EquityMarginExecuteReferenceServiceImpl l_equityMarginExecuteReferenceServiceImpl =
            new WEB3EquityMarginExecuteReferenceServiceImpl();
        try
        {
            l_subAccountParams.setSubAccountId(1906);
            l_subAccountParams.setAccountId(1906);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            l_mainAccountParams.setAccountId(1906);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            l_marketParams.setMarketId(1906);
            TestDBUtility.insertWithDel(l_marketParams);
            l_equityProductParams.setProductId(1906);
            TestDBUtility.insertWithDel(l_equityProductParams);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(2099);
            l_eqtypeOrderUnitParams.setSonarTradedCode("51");
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);

            //odercalog=3
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            //銘柄ID：[1906] 市場ID：[1906]
            l_tradedProductParams.setProductId(1906);
            l_tradedProductParams.setMarketId(1906);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            ProductParams l_productParams1 = TestDBUtility.getProductRow();
            l_productParams1.setProductId(1906);
            l_productParams1.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams1);

            EqtypeProductParams l_eqProductParams1 = new EqtypeProductParams();
            l_eqProductParams1.setProductId(1906);
            l_eqProductParams1.setProductType(ProductTypeEnum.EQUITY);
            l_eqProductParams1.setInstitutionCode("0D");
            l_eqProductParams1.setProductCode("2099");
            l_eqProductParams1.setYearlyBooksClosingDate(Calendar.getInstance().getTime());
            l_eqProductParams1.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_eqProductParams1.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_eqProductParams1);
            
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            //accountId, subAccountId
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(1906, 1906);
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
            WEB3GentradeBranchForMock l_branch = new WEB3GentradeBranchForMock(33381);
            
            WEB3GentradeMarket l_market = new WEB3GentradeMarket(1906);
            WEB3EquityProduct l_product = new WEB3EquityProduct(1906);
            EqTypeOrderManager l_eqTypeOrderManager =
                (EqTypeOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            EqTypeOrderUnit l_eqTypeOrderUnit = (EqTypeOrderUnit) l_eqTypeOrderManager.getOrderUnit(1906);
            //WEB3GentradeSubAccount l_subAccount, WEB3EquityProduct l_product,
            //WEB3GentradeMarket l_market, WEB3GentradeBranch l_branch, EqTypeOrderUnit l_orderUnit
            l_equityMarginExecuteReferenceServiceImpl.validateOrderForChangeability(
                l_subAccount, l_product, l_market, l_branch, l_eqTypeOrderUnit);
            
            WEB3MockObjectParamsValue l_value = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "validateTradedProductForMarginTrading",
                new Class[] {SubAccount.class,
                    WEB3EquityProduct.class, 
                    WEB3GentradeMarket.class, 
                    WEB3GentradeBranch.class, 
                    String.class, 
                    OrderCategEnum.class, 
                    boolean.class,
                    boolean.class});
            
            assertEquals("1", l_value.getCalled(0)[4]);
            assertEquals(Boolean.TRUE, l_value.getCalled(0)[6]);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * validateOrderForChangeability_C0008<BR>
     *
     */
    public void testValidateOrderForChangeability_C0008()
    {
        //log
        final String STR_METHOD_NAME = "validateOrderForChangeability_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.equity.WEB3EquityOrderManager",
            "validateOrderForChangeability",
            new Class[] {Order.class}, "");

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
            "validateInsider",
            new Class[] {SubAccount.class, EqTypeProduct.class},
            "");

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.equity.WEB3EquityOrderManager",
            "validateAccountProductOrderStop",
            new Class[] {SubAccount.class,
                long.class,
                OrderTypeEnum.class},
            "");

        //sub_accountテーブル
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        //main_accountテーブル
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        //marketテーブル
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        //productテーブル
        ProductParams l_equityProductParams = TestDBUtility.getProductRow();
        //productテーブル
        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        //eqtype_orderテーブル
        EqtypeOrderParams l_eqtypeOrderParams = getEqtypeOrderRow();
        
        //株式・信用注文約定照会サービスImpl
        WEB3EquityMarginExecuteReferenceServiceImpl l_equityMarginExecuteReferenceServiceImpl =
            new WEB3EquityMarginExecuteReferenceServiceImpl();
        try
        {
            l_subAccountParams.setSubAccountId(1906);
            l_subAccountParams.setAccountId(1906);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            l_mainAccountParams.setAccountId(1906);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            l_marketParams.setMarketId(1906);
            TestDBUtility.insertWithDel(l_marketParams);
            l_equityProductParams.setProductId(1906);
            TestDBUtility.insertWithDel(l_equityProductParams);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(2099);
            l_eqtypeOrderUnitParams.setSonarTradedCode("51");
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);    //2

            //odercalog=3
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            //銘柄ID：[1906] 市場ID：[1906]
            l_tradedProductParams.setProductId(1906);
            l_tradedProductParams.setMarketId(1906);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            ProductParams l_productParams1 = TestDBUtility.getProductRow();
            l_productParams1.setProductId(1906);
            l_productParams1.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams1);

            EqtypeProductParams l_eqProductParams1 = new EqtypeProductParams();
            l_eqProductParams1.setProductId(1906);
            l_eqProductParams1.setProductType(ProductTypeEnum.EQUITY);
            l_eqProductParams1.setInstitutionCode("0D");
            l_eqProductParams1.setProductCode("2099");
            l_eqProductParams1.setYearlyBooksClosingDate(Calendar.getInstance().getTime());
            l_eqProductParams1.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_eqProductParams1.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_eqProductParams1);
            
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            //accountId, subAccountId
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(1906, 1906);
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
            WEB3GentradeBranchForMock l_branch = new WEB3GentradeBranchForMock(33381);
            
            WEB3GentradeMarket l_market = new WEB3GentradeMarket(1906);
            WEB3EquityProduct l_product = new WEB3EquityProduct(1906);
            EqTypeOrderManager l_eqTypeOrderManager =
                (EqTypeOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            EqTypeOrderUnit l_eqTypeOrderUnit = (EqTypeOrderUnit) l_eqTypeOrderManager.getOrderUnit(1906);
            //WEB3GentradeSubAccount l_subAccount, WEB3EquityProduct l_product,
            //WEB3GentradeMarket l_market, WEB3GentradeBranch l_branch, EqTypeOrderUnit l_orderUnit
            l_equityMarginExecuteReferenceServiceImpl.validateOrderForChangeability(
                l_subAccount, l_product, l_market, l_branch, l_eqTypeOrderUnit);
            WEB3MockObjectParamsValue l_value = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "validateTradedProductForMarginTrading",
                new Class[] {SubAccount.class,
                    WEB3EquityProduct.class, 
                    WEB3GentradeMarket.class, 
                    WEB3GentradeBranch.class, 
                    String.class, 
                    OrderCategEnum.class, 
                    boolean.class,
                    boolean.class});
            
            assertEquals("1", l_value.getCalled(0)[4]);
            assertEquals(Boolean.FALSE, l_value.getCalled(0)[6]);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * validateOrderForChangeability_C0009<BR>
     *
     */
    public void testValidateOrderForChangeability_C0009()
    {
        //log
        final String STR_METHOD_NAME = "validateOrderForChangeability_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.equity.WEB3EquityOrderManager",
            "validateOrderForChangeability",
            new Class[] {Order.class}, "");

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
            "validateInsider",
            new Class[] {SubAccount.class, EqTypeProduct.class},
            "");

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.equity.WEB3EquityOrderManager",
            "validateAccountProductOrderStop",
            new Class[] {SubAccount.class,
                long.class,
                OrderTypeEnum.class},
            "");

        //sub_accountテーブル
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        //main_accountテーブル
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        //marketテーブル
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        //productテーブル
        ProductParams l_equityProductParams = TestDBUtility.getProductRow();
        //productテーブル
        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        //eqtype_orderテーブル
        EqtypeOrderParams l_eqtypeOrderParams = getEqtypeOrderRow();
        
        //株式・信用注文約定照会サービスImpl
        WEB3EquityMarginExecuteReferenceServiceImpl l_equityMarginExecuteReferenceServiceImpl =
            new WEB3EquityMarginExecuteReferenceServiceImpl();
        try
        {
            l_subAccountParams.setSubAccountId(1906);
            l_subAccountParams.setAccountId(1906);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            l_mainAccountParams.setAccountId(1906);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            l_marketParams.setMarketId(1906);
            TestDBUtility.insertWithDel(l_marketParams);
            l_equityProductParams.setProductId(1906);
            TestDBUtility.insertWithDel(l_equityProductParams);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(2099);
            l_eqtypeOrderUnitParams.setSonarTradedCode("51");
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);    //2

            //odercalog=3
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            //銘柄ID：[1906] 市場ID：[1906]
            l_tradedProductParams.setProductId(1906);
            l_tradedProductParams.setMarketId(1906);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            ProductParams l_productParams1 = TestDBUtility.getProductRow();
            l_productParams1.setProductId(1906);
            l_productParams1.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams1);

            EqtypeProductParams l_eqProductParams1 = new EqtypeProductParams();
            l_eqProductParams1.setProductId(1906);
            l_eqProductParams1.setProductType(ProductTypeEnum.EQUITY);
            l_eqProductParams1.setInstitutionCode("0D");
            l_eqProductParams1.setProductCode("2099");
            l_eqProductParams1.setYearlyBooksClosingDate(Calendar.getInstance().getTime());
            l_eqProductParams1.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_eqProductParams1.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_eqProductParams1);
            
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            //accountId, subAccountId
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(1906, 1906);
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
            WEB3GentradeBranchForMock l_branch = new WEB3GentradeBranchForMock(33381);
            
            WEB3GentradeMarket l_market = new WEB3GentradeMarket(1906);
            WEB3EquityProduct l_product = new WEB3EquityProduct(1906);
            EqTypeOrderManager l_eqTypeOrderManager =
                (EqTypeOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            EqTypeOrderUnit l_eqTypeOrderUnit = (EqTypeOrderUnit) l_eqTypeOrderManager.getOrderUnit(1906);
            //WEB3GentradeSubAccount l_subAccount, WEB3EquityProduct l_product,
            //WEB3GentradeMarket l_market, WEB3GentradeBranch l_branch, EqTypeOrderUnit l_orderUnit
            l_equityMarginExecuteReferenceServiceImpl.validateOrderForChangeability(
                l_subAccount, l_product, l_market, l_branch, l_eqTypeOrderUnit);
            
            WEB3MockObjectParamsValue l_value = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "validateTradedProductForMarginTrading",
                new Class[] {SubAccount.class,
                    WEB3EquityProduct.class, 
                    WEB3GentradeMarket.class, 
                    WEB3GentradeBranch.class, 
                    String.class, 
                    OrderCategEnum.class, 
                    boolean.class,
                    boolean.class});
            
            assertEquals("1", l_value.getCalled(0)[4]);
            assertEquals(Boolean.FALSE, l_value.getCalled(0)[6]);

        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * validateOrderForChangeability_C0010<BR>
     *
     */
    public void testValidateOrderForChangeability_C0010()
    {
        //log
        final String STR_METHOD_NAME = "testValidateOrderForChangeability_C0010()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.equity.WEB3EquityOrderManager",
            "validateOrderForChangeability",
            new Class[] {Order.class}, "");

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
            "validateInsider",
            new Class[] {SubAccount.class, EqTypeProduct.class},
            "");

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.equity.WEB3EquityOrderManager",
            "validateAccountProductOrderStop",
            new Class[] {SubAccount.class,
                long.class,
                OrderTypeEnum.class},
            "");
        
        //sub_accountテーブル
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        //main_accountテーブル
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        //marketテーブル
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        //productテーブル
        ProductParams l_equityProductParams = TestDBUtility.getProductRow();
        //productテーブル
        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        //eqtype_orderテーブル
        EqtypeOrderParams l_eqtypeOrderParams = getEqtypeOrderRow();
        
        //株式・信用注文約定照会サービスImpl
        WEB3EquityMarginExecuteReferenceServiceImpl l_equityMarginExecuteReferenceServiceImpl =
            new WEB3EquityMarginExecuteReferenceServiceImpl();
        try
        {
            l_subAccountParams.setSubAccountId(1906);
            l_subAccountParams.setAccountId(1906);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            l_mainAccountParams.setAccountId(1906);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            l_marketParams.setMarketId(1906);
            TestDBUtility.insertWithDel(l_marketParams);
            l_equityProductParams.setProductId(1906);
            TestDBUtility.insertWithDel(l_equityProductParams);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(2099);
            l_eqtypeOrderUnitParams.setSonarTradedCode("51");
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.OPEN_MARGIN);

            //odercalog=3
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            //銘柄ID：[1906] 市場ID：[1906]
            l_tradedProductParams.setProductId(1906);
            l_tradedProductParams.setMarketId(1906);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            ProductParams l_productParams1 = TestDBUtility.getProductRow();
            l_productParams1.setProductId(1906);
            l_productParams1.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams1);

            EqtypeProductParams l_eqProductParams1 = new EqtypeProductParams();
            l_eqProductParams1.setProductId(1906);
            l_eqProductParams1.setProductType(ProductTypeEnum.EQUITY);
            l_eqProductParams1.setInstitutionCode("0D");
            l_eqProductParams1.setProductCode("2099");
            l_eqProductParams1.setYearlyBooksClosingDate(Calendar.getInstance().getTime());
            l_eqProductParams1.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_eqProductParams1.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_eqProductParams1);
            
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            //accountId, subAccountId
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(1906, 1906);
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
            WEB3GentradeBranchForMock l_branch = new WEB3GentradeBranchForMock(33381);
            
            WEB3GentradeMarket l_market = new WEB3GentradeMarket(1906);
            WEB3EquityProduct l_product = new WEB3EquityProduct(1906);
            EqTypeOrderManager l_eqTypeOrderManager =
                (EqTypeOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            EqTypeOrderUnit l_eqTypeOrderUnit = (EqTypeOrderUnit) l_eqTypeOrderManager.getOrderUnit(1906);
            //WEB3GentradeSubAccount l_subAccount, WEB3EquityProduct l_product,
            //WEB3GentradeMarket l_market, WEB3GentradeBranch l_branch, EqTypeOrderUnit l_orderUnit
            l_equityMarginExecuteReferenceServiceImpl.validateOrderForChangeability(
                l_subAccount, l_product, l_market, l_branch, l_eqTypeOrderUnit);
            
            WEB3MockObjectParamsValue l_value = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "validateInsider",
                new Class[] {SubAccount.class,
                    EqTypeProduct.class});
            
            assertEquals(l_subAccount, l_value.getCalled(0)[0]);
            assertEquals(l_product, l_value.getCalled(0)[1]);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }


    private EqtypeProductParams getEqtypeProductRow()
    {
        EqtypeProductParams l_eqtypeProductParams = new EqtypeProductParams();
        l_eqtypeProductParams.setProductId(1906);
        l_eqtypeProductParams.setInstitutionCode("0D");
        l_eqtypeProductParams.setProductCode("2099");
        l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
        l_eqtypeProductParams.setYearlyBooksClosingDate(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
        l_eqtypeProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
        l_eqtypeProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
        
        return l_eqtypeProductParams;
    }
    private EqtypeOrderParams getEqtypeOrderRow()
    {
        EqtypeOrderParams l_eqtypeOrderParams = new EqtypeOrderParams();
        l_eqtypeOrderParams.setAccountId(1906);
        l_eqtypeOrderParams.setSubAccountId(1906);
        l_eqtypeOrderParams.setProductType(ProductTypeEnum.EQUITY);
        l_eqtypeOrderParams.setOrderId(2099);
        l_eqtypeOrderParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
        l_eqtypeOrderParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
        return l_eqtypeOrderParams;
        
    }

    /**
     * 株式注文単位Rowを作成.<BR>
     */
    private EqtypeOrderUnitParams getEqtypeOrderUnitRow()
    {
        EqtypeOrderUnitParams l_eqtypeOrderUnitParams = new EqtypeOrderUnitParams();

        l_eqtypeOrderUnitParams.setOrderUnitId(1906);
        l_eqtypeOrderUnitParams.setAccountId(1906);
        l_eqtypeOrderUnitParams.setSubAccountId(1906);
        l_eqtypeOrderUnitParams.setBranchId(2099);
        l_eqtypeOrderUnitParams.setTraderId(2099);
        l_eqtypeOrderUnitParams.setOrderId(2099);
        l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_BUY);    //1
        l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.OPEN_MARGIN); //3
        l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY); //1
        l_eqtypeOrderUnitParams.setMarketId(1906); //1
        l_eqtypeOrderUnitParams.setQuantity(1906D);
        l_eqtypeOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
        l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERED);   //3
        l_eqtypeOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.OPEN);//1
        l_eqtypeOrderUnitParams.setTaxType(TaxTypeEnum.NORMAL);//1
        l_eqtypeOrderUnitParams.setSwapTaxType(TaxTypeEnum.NORMAL);//1
        l_eqtypeOrderUnitParams.setBizDate("20070101");
        l_eqtypeOrderUnitParams.setProductId(1906);
        l_eqtypeOrderUnitParams.setQuantityType(QuantityTypeEnum.AMOUNT);//2
        l_eqtypeOrderUnitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));//2
        l_eqtypeOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
        l_eqtypeOrderUnitParams.setSonarTradedCode("11");
        l_eqtypeOrderUnitParams.setRepaymentType("1");

        return l_eqtypeOrderUnitParams;
    }

    /**Tim*/
    private Timestamp getTimeStamp(int year, int month, int day)
    {
        Calendar l_canlendar = Calendar.getInstance();
        l_canlendar.set(Calendar.YEAR, year);
        l_canlendar.set(Calendar.DAY_OF_MONTH, month);
        l_canlendar.set(Calendar.DAY_OF_YEAR, day);
        Timestamp l_tsTimeStamp = new Timestamp(l_canlendar.getTimeInMillis());
        return l_tsTimeStamp;
    }
    
    //////////////
    //取引時間管理.get市場閉局警告市場()をコールし、市場コードの配列 null
    //PTS取引時間管理.get市場閉局警告市場()をコールし、市場コードの配列 null
    public void testGetTradeCloseMarket1()
    {
        final String STR_METHOD_NAME = "testGetTradeCloseMarket1()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams1 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams1.setOrderUnitId(3304148080001L);
            l_eqtypeOrderUnitParams1.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams1.setRequestType(WEB3RequestTypeDef.DEFAULT);
            l_eqtypeOrderUnitParams1.setTraderId(3338111121L);
            l_eqtypeOrderUnitParams1.setOrderRev("00");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams1);
            
            TestDBUtility.deleteAll(MarketPreferencesParams.TYPE);
            MarketPreferencesParams l_MarketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_MarketPreferencesParams.setName(WEB3MarketPreferencesNameDef.EQUITY_PTS_MARKET_DIV);
            l_MarketPreferencesParams.setNameSerialNo(1);
            l_MarketPreferencesParams.setValue(WEB3EquityPTSMarketDivDef.PTS_MARKET_YES);
            l_MarketPreferencesParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_MarketPreferencesParams);
            
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode(WEB3MarketCodeDef.TOKYO);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setCloseWorngEquityMargin(0);
            TestDBUtility.insertWithDel(l_branchParams);

            WEB3EquityMarginExecuteReferenceServiceImpl l_equityMarginExecuteReferenceServiceImpl =
                new WEB3EquityMarginExecuteReferenceServiceImpl();
            //        WEB3GentradeBranch l_gentradeBranch,
            //ProductTypeEnum l_productType,
            //String l_strMarginTradeDiv
            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(33381L);
            String[] l_strTradeCloseMarkets =
                l_equityMarginExecuteReferenceServiceImpl.getTradeCloseMarket(l_branch,ProductTypeEnum.EQUITY,"0");
           assertNull(l_strTradeCloseMarkets);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //取引時間管理.get市場閉局警告市場()をコールし、市場コードの配列を取得
    public void testGetTradeCloseMarket2()
    {
        final String STR_METHOD_NAME = "testGetTradeCloseMarket2()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "xblocks.gtl.attributes.systemtimestamp",
                    new Timestamp(WEB3DateUtility.getDate("20071225","yyyyMMdd").getTime()));
            
            TestDBUtility.deleteAll(CalendarRow.TYPE);
            
            TestDBUtility.deleteAll(BranchMarketRepayDealtCondParams.TYPE);
            BranchMarketRepayDealtCondParams l_BranchMarketRepayDealtCondParams1 =
                TestDBUtility.getBranchMarketRepayDealtCondRow();
            l_BranchMarketRepayDealtCondParams1.setInstitutionCode("0D");
            l_BranchMarketRepayDealtCondParams1.setBranchCode("381");
            l_BranchMarketRepayDealtCondParams1.setMarketCode("2");
            l_BranchMarketRepayDealtCondParams1.setMartCanDealt("1");
            TestDBUtility.insertWithDel(l_BranchMarketRepayDealtCondParams1);
            BranchMarketRepayDealtCondParams l_BranchMarketRepayDealtCondParams2 =
                TestDBUtility.getBranchMarketRepayDealtCondRow();
            l_BranchMarketRepayDealtCondParams2.setInstitutionCode("0D");
            l_BranchMarketRepayDealtCondParams2.setBranchCode("381");
            l_BranchMarketRepayDealtCondParams2.setMarketCode("3");
            l_BranchMarketRepayDealtCondParams2.setMartCanDealt("1");
            TestDBUtility.insertWithDel(l_BranchMarketRepayDealtCondParams2);
            BranchMarketRepayDealtCondParams l_BranchMarketRepayDealtCondParams3 =
                TestDBUtility.getBranchMarketRepayDealtCondRow();
            l_BranchMarketRepayDealtCondParams3.setInstitutionCode("0D");
            l_BranchMarketRepayDealtCondParams3.setBranchCode("381");
            l_BranchMarketRepayDealtCondParams3.setMarketCode("1");
            l_BranchMarketRepayDealtCondParams3.setMartCanDealt("1");
            TestDBUtility.insertWithDel(l_BranchMarketRepayDealtCondParams3);
                
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams1 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams1.setOrderUnitId(3304148080001L);
            l_eqtypeOrderUnitParams1.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams1.setRequestType(WEB3RequestTypeDef.DEFAULT);
            l_eqtypeOrderUnitParams1.setTraderId(3338111121L);
            l_eqtypeOrderUnitParams1.setOrderRev("00");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams1);
            
            TestDBUtility.deleteAll(MarketPreferencesParams.TYPE);
            MarketPreferencesParams l_MarketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_MarketPreferencesParams.setName(WEB3MarketPreferencesNameDef.EQUITY_PTS_MARKET_DIV);
            l_MarketPreferencesParams.setNameSerialNo(1);
            l_MarketPreferencesParams.setValue(WEB3EquityPTSMarketDivDef.PTS_MARKET_YES);
            l_MarketPreferencesParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_MarketPreferencesParams);
            
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode(WEB3MarketCodeDef.TOKYO);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setCloseWorngEquityMargin(10000);
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutuionParams = TestDBUtility.getInstitutionRow();
            l_institutuionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutuionParams);

            WEB3EquityMarginExecuteReferenceServiceImpl l_equityMarginExecuteReferenceServiceImpl =
                new WEB3EquityMarginExecuteReferenceServiceImpl();
            //        WEB3GentradeBranch l_gentradeBranch,
            //ProductTypeEnum l_productType,
            //String l_strMarginTradeDiv
            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(33381L);
            String[] l_strTradeCloseMarkets =
                l_equityMarginExecuteReferenceServiceImpl.getTradeCloseMarket(l_branch,ProductTypeEnum.EQUITY,"3");
           assertEquals("1", l_strTradeCloseMarkets[0]);
           assertEquals("2", l_strTradeCloseMarkets[1]);
           assertEquals("3", l_strTradeCloseMarkets[2]);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //
    public void testGetTradeCloseMarket3()
    {
        final String STR_METHOD_NAME = "testGetTradeCloseMarket3()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "xblocks.gtl.attributes.systemtimestamp",
                    new Timestamp(WEB3DateUtility.getDate("20071226","yyyyMMdd").getTime()));
            
            
            TestDBUtility.deleteAll(BranchMarketRepayDealtCondParams.TYPE);
            BranchMarketRepayDealtCondParams l_BranchMarketRepayDealtCondParams1 =
                TestDBUtility.getBranchMarketRepayDealtCondRow();
            l_BranchMarketRepayDealtCondParams1.setInstitutionCode("0D");
            l_BranchMarketRepayDealtCondParams1.setBranchCode("381");
            l_BranchMarketRepayDealtCondParams1.setMarketCode("2");
            l_BranchMarketRepayDealtCondParams1.setMartCanDealt("1");
            TestDBUtility.insertWithDel(l_BranchMarketRepayDealtCondParams1);
            BranchMarketRepayDealtCondParams l_BranchMarketRepayDealtCondParams2 =
                TestDBUtility.getBranchMarketRepayDealtCondRow();
            l_BranchMarketRepayDealtCondParams2.setInstitutionCode("0D");
            l_BranchMarketRepayDealtCondParams2.setBranchCode("381");
            l_BranchMarketRepayDealtCondParams2.setMarketCode("3");
            l_BranchMarketRepayDealtCondParams2.setMartCanDealt("1");
            TestDBUtility.insertWithDel(l_BranchMarketRepayDealtCondParams2);
            BranchMarketRepayDealtCondParams l_BranchMarketRepayDealtCondParams3 =
                TestDBUtility.getBranchMarketRepayDealtCondRow();
            l_BranchMarketRepayDealtCondParams3.setInstitutionCode("0D");
            l_BranchMarketRepayDealtCondParams3.setBranchCode("381");
            l_BranchMarketRepayDealtCondParams3.setMarketCode("1");
            l_BranchMarketRepayDealtCondParams3.setMartCanDealt("1");
            TestDBUtility.insertWithDel(l_BranchMarketRepayDealtCondParams3);
                
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams1 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams1.setOrderUnitId(3304148080001L);
            l_eqtypeOrderUnitParams1.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams1.setRequestType(WEB3RequestTypeDef.DEFAULT);
            l_eqtypeOrderUnitParams1.setTraderId(3338111121L);
            l_eqtypeOrderUnitParams1.setOrderRev("00");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams1);
            
            TestDBUtility.deleteAll(MarketPreferencesParams.TYPE);
            MarketPreferencesParams l_MarketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_MarketPreferencesParams.setName(WEB3MarketPreferencesNameDef.EQUITY_PTS_MARKET_DIV);
            l_MarketPreferencesParams.setNameSerialNo(1);
            l_MarketPreferencesParams.setValue(WEB3EquityPTSMarketDivDef.PTS_MARKET_YES);
            l_MarketPreferencesParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_MarketPreferencesParams);
            
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode(WEB3MarketCodeDef.TOKYO);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setCloseWorngEquityMargin(10000);
            TestDBUtility.insertWithDel(l_branchParams);
            
            WEB3EquityMarginExecuteReferenceServiceImpl l_equityMarginExecuteReferenceServiceImpl =
                new WEB3EquityMarginExecuteReferenceServiceImpl();
            //        WEB3GentradeBranch l_gentradeBranch,
            //ProductTypeEnum l_productType,
            //String l_strMarginTradeDiv
            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(33381L);
            String[] l_strTradeCloseMarkets =
                l_equityMarginExecuteReferenceServiceImpl.getTradeCloseMarket(l_branch,ProductTypeEnum.EQUITY,"3");
           assertEquals("1", l_strTradeCloseMarkets[0]);
           assertEquals("2", l_strTradeCloseMarkets[1]);
           assertEquals("3", l_strTradeCloseMarkets[2]);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //引数.市場.isPTS市場() == true PTS注文マネージャ.validatePTS注文取消可能状態()をコール
    //正常結束
    public void testValidateOrderForCancellation1()
    {
        final String STR_METHOD_NAME = "testValidateOrderForCancellation1()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams1 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams1.setOrderUnitId(3304148080001L);
            l_eqtypeOrderUnitParams1.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams1.setOrderId(1234L);
            l_eqtypeOrderUnitParams1.setRequestType(WEB3RequestTypeDef.DEFAULT);
            l_eqtypeOrderUnitParams1.setTraderId(3338111121L);
            l_eqtypeOrderUnitParams1.setOrderRev("00");
            l_eqtypeOrderUnitParams1.setOrderStatus(OrderStatusEnum.CANCELLED);
            l_eqtypeOrderUnitParams1.setConfirmedQuantity(100);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams1);
            
            TestDBUtility.deleteAll(EqtypeOrderParams.TYPE);
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(1234L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            TestDBUtility.deleteAll(MarketPreferencesParams.TYPE);
            MarketPreferencesParams l_MarketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_MarketPreferencesParams.setName(WEB3MarketPreferencesNameDef.EQUITY_PTS_MARKET_DIV);
            l_MarketPreferencesParams.setNameSerialNo(1);
            l_MarketPreferencesParams.setValue(WEB3EquityPTSMarketDivDef.PTS_MARKET_YES);
            l_MarketPreferencesParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_MarketPreferencesParams);
            
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode(WEB3MarketCodeDef.TOKYO);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            WEB3EquityMarginExecuteReferenceServiceImpl l_equityMarginExecuteReferenceServiceImpl =
                new WEB3EquityMarginExecuteReferenceServiceImpl();
            WEB3GentradeMarket l_market = new WEB3GentradeMarket(3303L);
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            EqTypeOrderManager l_eqTypeOrderManager =
                (EqTypeOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            EqTypeOrderUnit l_eqTypeOrderUnit = (EqTypeOrderUnit)l_eqTypeOrderManager.getOrderUnit(3304148080001L);
            Order l_order = l_eqTypeOrderUnit.getOrder();
            l_equityMarginExecuteReferenceServiceImpl.validateOrderForCancellation(l_order,l_market);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //引数.市場.isPTS市場() == false拡張株式注文マネージャ.validate注文取消可能状態()をコール
    //異常
    public void testValidateOrderForCancellation2()
    {
        final String STR_METHOD_NAME = "testValidateOrderForCancellation2()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams1 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams1.setOrderUnitId(3304148080001L);
            l_eqtypeOrderUnitParams1.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams1.setOrderId(1234L);
            l_eqtypeOrderUnitParams1.setRequestType(WEB3RequestTypeDef.DEFAULT);
            l_eqtypeOrderUnitParams1.setTraderId(3338111121L);
            l_eqtypeOrderUnitParams1.setOrderRev("00");
            l_eqtypeOrderUnitParams1.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams1);
            
            TestDBUtility.deleteAll(EqtypeOrderParams.TYPE);
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(1234L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            TestDBUtility.deleteAll(MarketPreferencesParams.TYPE);
            MarketPreferencesParams l_MarketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_MarketPreferencesParams.setName(WEB3MarketPreferencesNameDef.EQUITY_PTS_MARKET_DIV);
            l_MarketPreferencesParams.setNameSerialNo(1);
            l_MarketPreferencesParams.setValue(WEB3EquityPTSMarketDivDef.PTS_MARKET_NOT);
            l_MarketPreferencesParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_MarketPreferencesParams);
            
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode(WEB3MarketCodeDef.TOKYO);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            WEB3EquityMarginExecuteReferenceServiceImpl l_equityMarginExecuteReferenceServiceImpl =
                new WEB3EquityMarginExecuteReferenceServiceImpl();
            WEB3GentradeMarket l_market = new WEB3GentradeMarket(3303L);
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            EqTypeOrderManager l_eqTypeOrderManager =
                (EqTypeOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            EqTypeOrderUnit l_eqTypeOrderUnit = (EqTypeOrderUnit)l_eqTypeOrderManager.getOrderUnit(3304148080001L);
            Order l_order = l_eqTypeOrderUnit.getOrder();
            l_equityMarginExecuteReferenceServiceImpl.validateOrderForCancellation(l_order,l_market);

        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00820);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //引数.市場.isPTS市場() == true PTS注文マネージャ.validate取扱可能PTS市場()をコール
    //正常結束
    public void testValidateHandlingMarket1()
    {
        final String STR_METHOD_NAME = "testValidateHandlingMarket1()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondParams.TYPE);
            BranchMarketPtsDealtCondParams l_BranchMarketPtsDealtCondParams =
                TestDBUtility.getBranchMarketPtsDealtCondRow();
            l_BranchMarketPtsDealtCondParams.setBranchCode("381");
            l_BranchMarketPtsDealtCondParams.setInstitutionCode("0D");
            l_BranchMarketPtsDealtCondParams.setMarketCode("1");
            l_BranchMarketPtsDealtCondParams.setMartCanDealtEquity("1");
            TestDBUtility.insertWithDel(l_BranchMarketPtsDealtCondParams);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams1 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams1.setOrderUnitId(3304148080001L);
            l_eqtypeOrderUnitParams1.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams1.setOrderId(1234L);
            l_eqtypeOrderUnitParams1.setRequestType(WEB3RequestTypeDef.DEFAULT);
            l_eqtypeOrderUnitParams1.setTraderId(3338111121L);
            l_eqtypeOrderUnitParams1.setOrderRev("00");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams1);
            
            TestDBUtility.deleteAll(EqtypeOrderParams.TYPE);
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(1234L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            TestDBUtility.deleteAll(MarketPreferencesParams.TYPE);
            MarketPreferencesParams l_MarketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_MarketPreferencesParams.setName(WEB3MarketPreferencesNameDef.EQUITY_PTS_MARKET_DIV);
            l_MarketPreferencesParams.setNameSerialNo(1);
            l_MarketPreferencesParams.setValue(WEB3EquityPTSMarketDivDef.PTS_MARKET_YES);
            l_MarketPreferencesParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_MarketPreferencesParams);
            
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode(WEB3MarketCodeDef.TOKYO);
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqProductParams1 = TestDBUtility.getEqtypeProductRow();
            l_eqProductParams1.setProductId(3304148080000L);
            l_eqProductParams1.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_eqProductParams1);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams1 = TestDBUtility.getProductRow();
            l_productParams1.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_productParams1);
            
            WEB3EquityMarginExecuteReferenceServiceImpl l_equityMarginExecuteReferenceServiceImpl =
                new WEB3EquityMarginExecuteReferenceServiceImpl();
            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(33381L);
            
            WEB3EquityTradedProduct l_equityTradedProduct = null;
            TradedProductParams l_tradedProductParams =TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setInstitutionCode("0D");
            l_tradedProductParams.setMarketId(3303L);
            try
            {
                l_equityTradedProduct = new WEB3EquityTradedProduct(l_tradedProductParams);
            }
            catch(Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(TEST_END);
                fail();
            }
            l_equityMarginExecuteReferenceServiceImpl.validateHandlingMarket(l_branch, l_equityTradedProduct);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //引数.市場.isPTS市場() == false 拡張株式注文マネージャ.validate取扱可能PTS市場
    //異常
    public void testValidateHandlingMarket2()
    {
        final String STR_METHOD_NAME = "testValidateOrderForCancellation2()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(BranchMarketDealtCondRow.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams1 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams1.setOrderUnitId(3304148080001L);
            l_eqtypeOrderUnitParams1.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams1.setOrderId(1234L);
            l_eqtypeOrderUnitParams1.setRequestType(WEB3RequestTypeDef.DEFAULT);
            l_eqtypeOrderUnitParams1.setTraderId(3338111121L);
            l_eqtypeOrderUnitParams1.setOrderRev("00");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams1);
            
            TestDBUtility.deleteAll(EqtypeOrderParams.TYPE);
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(1234L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            TestDBUtility.deleteAll(MarketPreferencesParams.TYPE);
            MarketPreferencesParams l_MarketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_MarketPreferencesParams.setName(WEB3MarketPreferencesNameDef.EQUITY_PTS_MARKET_DIV);
            l_MarketPreferencesParams.setNameSerialNo(1);
            l_MarketPreferencesParams.setValue(WEB3EquityPTSMarketDivDef.PTS_MARKET_NOT);
            l_MarketPreferencesParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_MarketPreferencesParams);
            
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode(WEB3MarketCodeDef.TOKYO);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqProductParams1 = TestDBUtility.getEqtypeProductRow();
            l_eqProductParams1.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_eqProductParams1);
            
            WEB3EquityMarginExecuteReferenceServiceImpl l_equityMarginExecuteReferenceServiceImpl =
                new WEB3EquityMarginExecuteReferenceServiceImpl();
            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(33381L);
            
            WEB3EquityTradedProduct l_equityTradedProduct = null;
            TradedProductParams l_tradedProductParams =TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setInstitutionCode("0D");
            l_tradedProductParams.setMarketId(3303L);
            try
            {
                l_equityTradedProduct = new WEB3EquityTradedProduct(l_tradedProductParams);
            }
            catch(Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(TEST_END);
                fail();
            }
            l_equityMarginExecuteReferenceServiceImpl.validateHandlingMarket(l_branch, l_equityTradedProduct);

        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80003);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //引数.市場.isPTS市場() == true PTS注文マネージャ.validate顧客銘柄別取引停止(PTS)をコール
    //正常結束
    public void testValidateAccountProductOrderStop1()
    {
        final String STR_METHOD_NAME = "testValidateAccountProductOrderStop1()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512203L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams1 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams1.setOrderUnitId(3304148080001L);
            l_eqtypeOrderUnitParams1.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams1.setOrderId(1234L);
            l_eqtypeOrderUnitParams1.setRequestType(WEB3RequestTypeDef.DEFAULT);
            l_eqtypeOrderUnitParams1.setTraderId(3338111121L);
            l_eqtypeOrderUnitParams1.setOrderRev("00");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams1);
            
            TestDBUtility.deleteAll(EqtypeOrderParams.TYPE);
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(1234L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            TestDBUtility.deleteAll(MarketPreferencesParams.TYPE);
            MarketPreferencesParams l_MarketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_MarketPreferencesParams.setName(WEB3MarketPreferencesNameDef.EQUITY_PTS_MARKET_DIV);
            l_MarketPreferencesParams.setNameSerialNo(1);
            l_MarketPreferencesParams.setValue(WEB3EquityPTSMarketDivDef.PTS_MARKET_YES);
            l_MarketPreferencesParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_MarketPreferencesParams);
            
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode(WEB3MarketCodeDef.TOKYO);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            WEB3EquityMarginExecuteReferenceServiceImpl l_equityMarginExecuteReferenceServiceImpl =
                new WEB3EquityMarginExecuteReferenceServiceImpl();

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeSubAccount l_subAccount = null;
            try
            {
                //取得補助口座
                l_subAccount =
                    (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(
                        333812512203L,
                        33381251220301L);
            }
            catch (NotFoundException l_nfe)
            {
                log.error("データ不整合エラー。", l_nfe);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe); 
            }
            WEB3GentradeMarket l_market = new WEB3GentradeMarket(3303L);
            l_equityMarginExecuteReferenceServiceImpl.validateAccountProductOrderStop(
                    l_subAccount, 3304148080000L, OrderTypeEnum.ASSET_IN, l_market);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //引数.市場.isPTS市場() ==  false PTS注文マネージャ.validate顧客銘柄別取引停止(PTS)をコール
    //異常
    public void testValidateAccountProductOrderStop2()
    {
        final String STR_METHOD_NAME = "testValidateAccountProductOrderStop2()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(AccountProductOrderStopRow.TYPE);
            AccountProductOrderStopParams l_AccountProductOrderStopParams =
                TestDBUtility.getAccountProductOrderStopRow();
            l_AccountProductOrderStopParams.setAccountId(333812512203L);
            l_AccountProductOrderStopParams.setBranchId(33381L);
            l_AccountProductOrderStopParams.setInstitutionCode("0D");
            l_AccountProductOrderStopParams.setProductId(3304148080000L);
            l_AccountProductOrderStopParams.setApplyEndDate(WEB3DateUtility.getDate("21150731","yyyyMMdd"));
            l_AccountProductOrderStopParams.setStopTradeDivBuyCash("1");
            TestDBUtility.insertWithDel(l_AccountProductOrderStopParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512203L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams1 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams1.setOrderUnitId(3304148080001L);
            l_eqtypeOrderUnitParams1.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams1.setOrderId(1234L);
            l_eqtypeOrderUnitParams1.setRequestType(WEB3RequestTypeDef.DEFAULT);
            l_eqtypeOrderUnitParams1.setTraderId(3338111121L);
            l_eqtypeOrderUnitParams1.setOrderRev("00");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams1);
            
            TestDBUtility.deleteAll(EqtypeOrderParams.TYPE);
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(1234L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            TestDBUtility.deleteAll(MarketPreferencesParams.TYPE);
            MarketPreferencesParams l_MarketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_MarketPreferencesParams.setName(WEB3MarketPreferencesNameDef.EQUITY_PTS_MARKET_DIV);
            l_MarketPreferencesParams.setNameSerialNo(1);
            l_MarketPreferencesParams.setValue(WEB3EquityPTSMarketDivDef.PTS_MARKET_NOT);
            l_MarketPreferencesParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_MarketPreferencesParams);
            
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode(WEB3MarketCodeDef.TOKYO);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(Calendar.getInstance().getTime());

            WEB3EquityMarginExecuteReferenceServiceImpl l_equityMarginExecuteReferenceServiceImpl =
                new WEB3EquityMarginExecuteReferenceServiceImpl();

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeSubAccount l_subAccount = null;
            try
            {
                //取得補助口座
                l_subAccount =
                    (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(
                        333812512203L,
                        33381251220301L);
            }
            catch (NotFoundException l_nfe)
            {
                log.error("データ不整合エラー。", l_nfe);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe); 
            }
            WEB3GentradeMarket l_market = new WEB3GentradeMarket(3303L);
            l_equityMarginExecuteReferenceServiceImpl.validateAccountProductOrderStop(
                    l_subAccount, 3304148080000L, OrderTypeEnum.EQUITY_BUY, l_market);

        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01357, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
