head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.09.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminEquityPTSCancelExecServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 
 Author Name      : Daiwa Institute of Research
 Revesion History : 
 */
package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeAsset;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderExecutionParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeOrderUnitImpl;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.PersistenceManagerImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AssetUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSCancelExecCompleteRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSCancelExecCompleteResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSCancelExecConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSCancelExecConfirmResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSCancelExecInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSCancelExecInputResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSExecHistory;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSInputCancelExecCommonRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSOrderDetailUnit;
import webbroker3.equity.WEB3EquityPTSTradingTimeManagement;
import webbroker3.equity.data.HostEquityOrderExecNotifyParams;
import webbroker3.equity.data.HostEquityOrderExecNotifyRow;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorTypeParams;
import webbroker3.gentrade.data.PtsOrderexecutionEndRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminEquityPTSCancelExecServiceImplTest extends TestBaseForMock
{

    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminEquityPTSCancelExecServiceImplTest.class);

    private double l_dbLockedQuantity = 0;

    public double l_dbQuantity = 0;

    public WEB3AdminEquityPTSCancelExecServiceImplTest(String name)
    {
        super(name);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderExecutionParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(HostEquityOrderExecNotifyParams.TYPE);
            TestDBUtility.deleteAll(MarketPreferencesParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
        }
        catch (WEB3BaseException e)
        {
            fail();
        }
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderExecutionParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(HostEquityOrderExecNotifyParams.TYPE);
            TestDBUtility.deleteAll(MarketPreferencesParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
        }
        catch (WEB3BaseException e)
        {
            fail();
        }
    }

    /**
     * get入力画面 管理者・株式（PTS）出来取消入力リクエスト.validate()例外をスローする
     * 管理者・株式(PTS)出来取消リクエスト注文ID=null 「BUSINESS_ERROR_00600」の例外をスローする
     */
    public void test_getInputScreen_0001()
    {
        final String STR_METHOD_NAME = "test_getInputScreen_0001";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSCancelExecServiceImpl l_service = new WEB3AdminEquityPTSCancelExecServiceImpl();
        WEB3AdminEquityPTSCancelExecInputRequest l_request = new WEB3AdminEquityPTSCancelExecInputRequest();
        try
        {
            l_service.getInputScreen(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00600, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * get入力画面 validate管理者権限
     * 
     * 「BUSINESS_ERROR_01056」の例外をスローする
     */
    public void test_getInputScreen_0002()
    {
        final String STR_METHOD_NAME = "test_getInputScreen_0002";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSCancelExecServiceImpl l_service = new WEB3AdminEquityPTSCancelExecServiceImpl();
        WEB3AdminEquityPTSCancelExecInputRequest l_request = new WEB3AdminEquityPTSCancelExecInputRequest();

        // 注文ID
        l_request.orderId = "1";
        try
        {
            // LoginInfoImpl l_LoginInfo = new LoginInfoImpl();
            // TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            // "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            // "getLoginInfo",
            // new Class[] {},
            // l_LoginInfo);

            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);

            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(1);

            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_service.getInputScreen(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * get入力画面 validate出来入力出来取消可能時間帯
     * 
     * 「BUSINESS_ERROR_03015」の例外をスローする
     */
    public void test_getInputScreen_0003()
    {
        final String STR_METHOD_NAME = "test_getInputScreen_0003";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSCancelExecServiceImpl l_service = new WEB3AdminEquityPTSCancelExecServiceImpl();
        WEB3AdminEquityPTSCancelExecInputRequest l_request = new WEB3AdminEquityPTSCancelExecInputRequest();

        // 注文ID
        l_request.orderId = "1";
        try
        {
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            WEB3AdministratorForMock.mockValidateAuthority(l_expectAdministrator, "C0109", true, true);

            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(1);

            l_eqtypeOrderUnitParams.setBizDate("20080128");

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
            // l_tradingTimeParams.setBizDateType("1");

            TestDBUtility.deleteAll(MarketPreferencesRow.TYPE);
            MarketPreferencesParams l_marketPreferencesRow = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesRow.setValue("1");
            l_marketPreferencesRow.setName("equity.pts.market.div");
            TestDBUtility.insertWithDel(l_marketPreferencesRow);

            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(PtsOrderexecutionEndRow.TYPE);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl", "getBizDate", new Class[]
                    {}, WEB3DateUtility.getDate("20080128", "yyyyMMdd"));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl", "getSystemTimestamp",
                    new Class[]
                    {}, new Timestamp(WEB3DateUtility.getDate("20080127", "yyyyMMdd").getTime()));

            l_service.getInputScreen(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03015, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * get入力画面 validate出来取消可能注文
     * 
     * 「BUSINESS_ERROR_03003」の例外をスローする
     */
    public void test_getInputScreen_0004()
    {
        final String STR_METHOD_NAME = "test_getInputScreen_0004()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSCancelExecServiceImpl l_service = new WEB3AdminEquityPTSCancelExecServiceImpl();
        WEB3AdminEquityPTSCancelExecInputRequest l_request = new WEB3AdminEquityPTSCancelExecInputRequest();

        // 注文ID
        l_request.orderId = "1";
        try
        {
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            WEB3AdministratorForMock.mockValidateAuthority(l_expectAdministrator, "C0109", true, true);

            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(1);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");

            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl", "getSystemTimestamp",
                    new Class[]
                    {}, new Timestamp(WEB3DateUtility.getDate("20080130", "yyyyMMdd").getTime()));

            l_service.getInputScreen(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03003, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * get入力画面 get約定履歴()の戻り値がnullの場合、 「データ不整合」の例外をスローする。
     */
    public void test_getInputScreen_0005()
    {
        final String STR_METHOD_NAME = "test_getInputScreen_0005()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSCancelExecServiceImpl l_service = new WEB3AdminEquityPTSCancelExecServiceImpl();
        WEB3AdminEquityPTSCancelExecInputRequest l_request = new WEB3AdminEquityPTSCancelExecInputRequest();

        // 注文ID
        l_request.orderId = "1";
        try
        {
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080001L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);

            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            WEB3AdministratorForMock.mockValidateAuthority(l_expectAdministrator, "C0109", true, true);

            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderRequestNumber("256");
            l_eqtypeOrderUnitParams.setOrderId(1);
            l_eqtypeOrderUnitParams.setBizDate("20070128");

            l_eqtypeOrderUnitParams.setExecutedQuantity(100);
            l_eqtypeOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.NONE);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("0");

            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setTradingTimeType("01");
            l_tradingTimeParams1.setBizDateType("1");
            l_tradingTimeParams1.setStartTime("10");

            TradingTimeParams l_tradingTimeParams2 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams2.setTradingTimeType("01");
            l_tradingTimeParams2.setBizDateType("2");
            l_tradingTimeParams2.setStartTime("20");

            TradingTimeParams l_tradingTimeParams3 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams3.setTradingTimeType("01");
            l_tradingTimeParams3.setBizDateType("3");
            l_tradingTimeParams3.setStartTime("30");

            TradingTimeParams l_tradingTimeParams4 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams4.setTradingTimeType("01");
            l_tradingTimeParams4.setBizDateType("4");
            l_tradingTimeParams4.setStartTime("40");

            TradingTimeParams l_tradingTimeParams5 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams5.setTradingTimeType("01");
            l_tradingTimeParams5.setBizDateType("5");
            l_tradingTimeParams5.setStartTime("50");

            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            TestDBUtility.insertWithDel(l_tradingTimeParams2);
            TestDBUtility.insertWithDel(l_tradingTimeParams3);
            TestDBUtility.insertWithDel(l_tradingTimeParams4);
            TestDBUtility.insertWithDel(l_tradingTimeParams5);

            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("equity.pts.market.div");
            l_marketPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_marketPreferencesParams);

            TestDBUtility.deleteAll(PtsOrderexecutionEndRow.TYPE);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl", "getBizDate", new Class[]
                    {}, WEB3DateUtility.getDate("20070128", "yyyyMMdd"));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl", "getSystemTimestamp",
                    new Class[]
                    {}, new Timestamp(GtlUtils.getSystemTimestamp().getTime()));

            l_service.getInputScreen(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * get入力画面
     */
    public void test_getInputScreen_0006()
    {
        final String STR_METHOD_NAME = "test_getInputScreen_0006()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSCancelExecServiceImpl l_service = new WEB3AdminEquityPTSCancelExecServiceImpl();
        WEB3AdminEquityPTSCancelExecInputRequest l_request = new WEB3AdminEquityPTSCancelExecInputRequest();

        // 注文ID
        l_request.orderId = "1";
        try
        {
            HostEquityOrderExecNotifyParams l_hostEquityOrderExecNotifyParams = new HostEquityOrderExecNotifyParams();
            l_hostEquityOrderExecNotifyParams.setOrderRequestNumber("12");

            TestDBUtility.insertWithDel(l_hostEquityOrderExecNotifyParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080001L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);

            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080001L);

            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            WEB3AdministratorForMock.mockValidateAuthority(l_expectAdministrator, "C0109", true, true);

            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderRequestNumber("256");
            l_eqtypeOrderUnitParams.setOrderId(1);

            l_eqtypeOrderUnitParams.setBizDate("20070128");
            l_eqtypeOrderUnitParams.setExecutedQuantity(100);
            l_eqtypeOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.NONE);
            l_eqtypeOrderUnitParams.setOrderRequestNumber("12");

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("0");

            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setTradingTimeType("01");
            l_tradingTimeParams1.setBizDateType("1");
            l_tradingTimeParams1.setStartTime("10");

            TradingTimeParams l_tradingTimeParams2 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams2.setTradingTimeType("01");
            l_tradingTimeParams2.setBizDateType("2");
            l_tradingTimeParams2.setStartTime("20");

            TradingTimeParams l_tradingTimeParams3 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams3.setTradingTimeType("01");
            l_tradingTimeParams3.setBizDateType("3");
            l_tradingTimeParams3.setStartTime("30");

            TradingTimeParams l_tradingTimeParams4 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams4.setTradingTimeType("01");
            l_tradingTimeParams4.setBizDateType("4");
            l_tradingTimeParams4.setStartTime("40");

            TradingTimeParams l_tradingTimeParams5 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams5.setTradingTimeType("01");
            l_tradingTimeParams5.setBizDateType("5");
            l_tradingTimeParams5.setStartTime("50");

            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            TestDBUtility.insertWithDel(l_tradingTimeParams2);
            TestDBUtility.insertWithDel(l_tradingTimeParams3);
            TestDBUtility.insertWithDel(l_tradingTimeParams4);
            TestDBUtility.insertWithDel(l_tradingTimeParams5);

            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("equity.pts.market.div");
            l_marketPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_marketPreferencesParams);

            TestDBUtility.deleteAll(PtsOrderexecutionEndRow.TYPE);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl", "getBizDate", new Class[]
                    {}, WEB3DateUtility.getDate("20070128", "yyyyMMdd"));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl", "getSystemTimestamp",
                    new Class[]
                    {}, new Timestamp(GtlUtils.getSystemTimestamp().getTime()));

            WEB3AdminEquityPTSCancelExecInputResponse l_response = l_service.getInputScreen(l_request);
            WEB3AdminEquityPTSOrderDetailUnit l_unit = l_response.orderDetail;
            WEB3AdminEquityPTSExecHistory[] l_history = l_response.execHistories;

            WEB3AdminEquityPTSExecHistory l_execHistory = l_history[0];
            assertEquals("N8080", l_unit.productCode);

            assertFalse(l_execHistory.cancelFlag);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validate管理者権限 管理者.validate権限() WEB3ErrorCatalog.BUSINESS_ERROR_01056
     */
    public void test_validateManagerAuthority_0001()
    {
        final String STR_METHOD_NAME = "test_validateManagerAuthority_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSCancelExecServiceImpl l_service = new WEB3AdminEquityPTSCancelExecServiceImpl();

        AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
        WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
        try
        {
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            l_service.validateManagerAuthority(l_expectAdministrator, 33381L);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validate管理者権限 管理者.validate部店権限() WEB3ErrorCatalog.BUSINESS_ERROR_01074
     */
    public void test_validateManagerAuthority_0002()
    {
        final String STR_METHOD_NAME = "test_validateManagerAuthority_0002()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSCancelExecServiceImpl l_service = new WEB3AdminEquityPTSCancelExecServiceImpl();

        AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
        l_administratorParams.setBranchCode("382");
        WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
        try
        {
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0109");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            l_service.validateManagerAuthority(l_expectAdministrator, 33381L);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01074, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validate管理者権限 正常結束
     */
    public void test_validateManagerAuthority_0003()
    {
        final String STR_METHOD_NAME = "test_validateManagerAuthority_0003()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSCancelExecServiceImpl l_service = new WEB3AdminEquityPTSCancelExecServiceImpl();

        AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
        l_administratorParams.setBranchCode("381");
        WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
        try
        {
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0109");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            l_service.validateManagerAuthority(l_expectAdministrator, 33381L);
            assertTrue(true);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * insert出来通知 正常結束
     */
    public void test_insertExecNotify_0001()
    {
        final String STR_METHOD_NAME = "test_insertExecNotify_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSCancelExecServiceImpl l_service = new WEB3AdminEquityPTSCancelExecServiceImpl();

        AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
        l_administratorParams.setBranchCode("382");
        WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
        try
        {
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            EqtypeOrderExecutionParams l_eqtypeOrderExecutionParams = TestDBUtility.getEqtypeOrderExecutionRow();
            l_eqtypeOrderExecutionParams.setExecQuantity(2.0D);
            l_eqtypeOrderExecutionParams.setExecPrice(231.0D);
            l_eqtypeOrderExecutionParams.setOrderUnitId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeOrderExecutionParams);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(33L, "381", "2512246");

            PersistenceManagerImpl pm = new PersistenceManagerImpl();
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderRequestNumber("256");
            EqTypeOrderUnitImpl l_impl = new EqTypeOrderUnitImpl(pm, l_eqtypeOrderUnitParams);
            OrderExecution[] l_orderExecution = l_impl.getExecutions();

            l_service.insertExecNotify(l_impl, l_orderExecution[0], l_mainAccount, l_expectAdministrator);

            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" request_code = ? ");

            Object[] l_objWheres =
            {"AI811"};

            List l_lisHostEquityOrderExecNotifyParams = new ArrayList();

            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_lisHostEquityOrderExecNotifyParams = l_processor.doFindAllQuery(HostEquityOrderExecNotifyParams.TYPE,
                    l_sbWhere.toString(), l_objWheres);
            HostEquityOrderExecNotifyRow l_row = (HostEquityOrderExecNotifyRow) l_lisHostEquityOrderExecNotifyParams
                    .get(0);
            assertEquals("0D", l_row.getInstitutionCode());
            assertEquals("381", l_row.getBranchCode());
            assertEquals("2512246", l_row.getAccountCode());
            assertNull(l_row.getTraderCode());
            assertEquals("256", l_row.getOrderRequestNumber());
            assertEquals("2.0", "" + l_row.getExecQuantity());
            assertEquals("231.0", "" + l_row.getExecPrice());
            assertEquals("0", ""
                    + WEB3DateUtility.compareToDay(Calendar.getInstance().getTime(), l_row.getExecTimestamp()));
            assertEquals("4", l_row.getDealedType());
            assertNull(l_row.getVirtualServerNumberMarket());
            assertNull(l_row.getNoticeType());
            assertEquals("0", "" + l_row.getNoticeNumber());
            assertEquals("0", "" + l_row.getExecNumber());
            assertEquals("0", l_row.getStatus());
            assertEquals("0", ""
                    + WEB3DateUtility.compareToDay(Calendar.getInstance().getTime(), l_row.getCreatedTimestamp()));
            assertEquals("0", ""
                    + WEB3DateUtility.compareToDay(Calendar.getInstance().getTime(), l_row.getLastUpdatedTimestamp()));
            assertEquals("330001", l_row.getLastUpdater());
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * get約定履歴（確認・完了）
     */
    public void test_getHistoryConfirmComplete_0001()
    {
        final String STR_METHOD_NAME = "test_getHistoryConfirmComplete_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSCancelExecServiceImpl l_service = new WEB3AdminEquityPTSCancelExecServiceImpl();

        AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
        l_administratorParams.setBranchCode("382");
        WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
        try
        {
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            EqtypeOrderExecutionParams l_eqtypeOrderExecutionParams = TestDBUtility.getEqtypeOrderExecutionRow();
            l_eqtypeOrderExecutionParams.setExecQuantity(2.0D);
            l_eqtypeOrderExecutionParams.setExecPrice(231.0D);
            l_eqtypeOrderExecutionParams.setOrderUnitId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeOrderExecutionParams);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            // WEB3GentradeMainAccount l_mainAccount = new
            // WEB3GentradeMainAccount(33L, "381", "2512246");

            PersistenceManagerImpl pm = new PersistenceManagerImpl();
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderRequestNumber("256");
            EqTypeOrderUnitImpl l_impl = new EqTypeOrderUnitImpl(pm, l_eqtypeOrderUnitParams);
            OrderExecution[] l_orderExecution = l_impl.getExecutions();

            WEB3AdminEquityPTSExecHistory l_history0 = new WEB3AdminEquityPTSExecHistory();
            WEB3AdminEquityPTSExecHistory l_history1 = new WEB3AdminEquityPTSExecHistory();
            WEB3AdminEquityPTSExecHistory l_history2 = new WEB3AdminEquityPTSExecHistory();

            WEB3AdminEquityPTSExecHistory[] l_histors = new WEB3AdminEquityPTSExecHistory[]
            {l_history0, l_history1, l_history2};

            WEB3AdminEquityPTSExecHistory[] l_ptsExecHistory = l_service.getHistoryConfirmComplete(l_histors,
                    l_orderExecution[0], l_expectAdministrator);

            assertFalse(l_ptsExecHistory[3].cancelFlag);
            assertEquals("0", ""
                    + WEB3DateUtility.compareToDay(Calendar.getInstance().getTime(),
                            l_ptsExecHistory[3].executionTimeStamp));
            assertEquals("2", l_ptsExecHistory[3].execQuantity);
            assertEquals("231", l_ptsExecHistory[3].execPrice);
            assertEquals("4", l_ptsExecHistory[3].inputExecCancelExecDiv);
            assertEquals("330001", l_ptsExecHistory[3].updaterCode);
            assertEquals("0", l_ptsExecHistory[3].inputExecCancelExecProcDiv);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * get約定履歴（入力）
     */
    public void test_getHistoryInput_0001()
    {
        final String STR_METHOD_NAME = "test_getHistoryInput_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSCancelExecServiceImpl l_service = new WEB3AdminEquityPTSCancelExecServiceImpl();

        try
        {
            WEB3AdminEquityPTSExecHistory l_history0 = new WEB3AdminEquityPTSExecHistory();
            l_history0.inputExecCancelExecDiv = "2";
            l_history0.inputExecCancelExecProcDiv = "1";
            l_history0.cancelFlag = false;
            WEB3AdminEquityPTSExecHistory l_history1 = new WEB3AdminEquityPTSExecHistory();
            l_history1.inputExecCancelExecDiv = "4";
            l_history1.inputExecCancelExecProcDiv = "1";
            l_history1.cancelFlag = false;
            WEB3AdminEquityPTSExecHistory l_history2 = new WEB3AdminEquityPTSExecHistory();
            l_history2.inputExecCancelExecDiv = "2";
            l_history2.inputExecCancelExecProcDiv = "2";
            l_history2.cancelFlag = false;

            WEB3AdminEquityPTSExecHistory[] l_histors = new WEB3AdminEquityPTSExecHistory[]
            {l_history0, l_history1, l_history2};

            WEB3AdminEquityPTSExecHistory[] l_ptsExecHistory = l_service.getHistoryInput(l_histors);

            assertTrue(l_ptsExecHistory[0].cancelFlag);
            assertFalse(l_ptsExecHistory[1].cancelFlag);
            assertFalse(l_ptsExecHistory[2].cancelFlag);
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * set取引カレンダコンテキスト
     */
    public void test_setTradingClendarContext_0001()
    {
        final String STR_METHOD_NAME = "test_setTradingClendarContext_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSCancelExecServiceImpl l_service = new WEB3AdminEquityPTSCancelExecServiceImpl();

        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl", "getSystemTimestamp",
                    new Class[]
                    {}, new Timestamp(WEB3DateUtility.getDate("20080130", "yyyyMMdd").getTime()));

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("0");

            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setTradingTimeType("01");
            l_tradingTimeParams1.setBizDateType("1");
            l_tradingTimeParams1.setStartTime("10");

            TradingTimeParams l_tradingTimeParams2 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams2.setTradingTimeType("01");
            l_tradingTimeParams2.setBizDateType("2");
            l_tradingTimeParams2.setStartTime("20");

            TradingTimeParams l_tradingTimeParams3 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams3.setTradingTimeType("01");
            l_tradingTimeParams3.setBizDateType("3");
            l_tradingTimeParams3.setStartTime("30");

            TradingTimeParams l_tradingTimeParams4 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams4.setTradingTimeType("01");
            l_tradingTimeParams4.setBizDateType("4");
            l_tradingTimeParams4.setStartTime("40");

            TradingTimeParams l_tradingTimeParams5 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams5.setTradingTimeType("01");
            l_tradingTimeParams5.setBizDateType("5");
            l_tradingTimeParams5.setStartTime("50");

            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            TestDBUtility.insertWithDel(l_tradingTimeParams2);
            TestDBUtility.insertWithDel(l_tradingTimeParams3);
            TestDBUtility.insertWithDel(l_tradingTimeParams4);
            TestDBUtility.insertWithDel(l_tradingTimeParams5);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            EqtypeOrderExecutionParams l_eqtypeOrderExecutionParams = TestDBUtility.getEqtypeOrderExecutionRow();
            l_eqtypeOrderExecutionParams.setExecQuantity(2.0D);
            l_eqtypeOrderExecutionParams.setExecPrice(231.0D);
            l_eqtypeOrderExecutionParams.setOrderUnitId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeOrderExecutionParams);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);

            PersistenceManagerImpl pm = new PersistenceManagerImpl();
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderRequestNumber("256");
            EqTypeOrderUnitImpl l_impl = new EqTypeOrderUnitImpl(pm, l_eqtypeOrderUnitParams);

            l_service.setTradingClendarContext(l_impl);

            WEB3GentradeTradingClendarContext l_context = (WEB3GentradeTradingClendarContext) ThreadLocalSystemAttributesRegistry
                    .getAttribute(WEB3EquityPTSTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
            assertEquals("0D", l_context.getInstitutionCode());
            assertEquals("381", l_context.getBranchCode());
            assertEquals("SP", l_context.getMarketCode());
            assertEquals("01", l_context.getTradingTimeType());
            assertEquals("0", l_context.getProductCode());
            assertEquals("01", l_context.getOrderAcceptProduct());
            assertNull(l_context.getOrderAcceptTransaction());

        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * set取引カレンダコンテキスト ThreadLocalSystemAttributesRegistry.setAttribute( )抛出異常信息
     */
    public void test_setTradingClendarContext_0002()
    {
        final String STR_METHOD_NAME = "test_setTradingClendarContext_0002()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSCancelExecServiceImpl l_service = new WEB3AdminEquityPTSCancelExecServiceImpl();

        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl", "getSystemTimestamp",
                    new Class[]
                    {}, new Timestamp(WEB3DateUtility.getDate("20080130", "yyyyMMdd").getTime()));

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            // TradingTimeParams l_tradingTimeParams =
            // TestDBUtility.getTradingTimeRow();
            // l_tradingTimeParams.setTradingTimeType("01");
            // l_tradingTimeParams.setBizDateType("1");
            // TestDBUtility.insertWithDel(l_tradingTimeParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            EqtypeOrderExecutionParams l_eqtypeOrderExecutionParams = TestDBUtility.getEqtypeOrderExecutionRow();
            l_eqtypeOrderExecutionParams.setExecQuantity(2.0D);
            l_eqtypeOrderExecutionParams.setExecPrice(231.0D);
            l_eqtypeOrderExecutionParams.setOrderUnitId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeOrderExecutionParams);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);

            PersistenceManagerImpl pm = new PersistenceManagerImpl();
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderRequestNumber("256");
            EqTypeOrderUnitImpl l_impl = new EqTypeOrderUnitImpl(pm, l_eqtypeOrderUnitParams);

            l_service.setTradingClendarContext(l_impl);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validate出来取消可能約定
     */
    public void test_validateCancelExecEnableExecute_0001()
    {
        final String STR_METHOD_NAME = "test_validateCancelExecEnableExecute_0001()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminEquityPTSCancelExecServiceImpl l_service = new WEB3AdminEquityPTSCancelExecServiceImpl();

        WEB3AdminEquityPTSInputCancelExecCommonRequest l_request = new WEB3AdminEquityPTSInputCancelExecCommonRequest();

        l_request.executionTimeStamp = Calendar.getInstance().getTime();
        l_request.execPrice = "231";
        l_request.execQuantity = "2";
        try
        {
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            EqtypeOrderExecutionParams l_eqtypeOrderExecutionParams = TestDBUtility.getEqtypeOrderExecutionRow();
            l_eqtypeOrderExecutionParams.setExecQuantity(2.0D);
            l_eqtypeOrderExecutionParams.setExecPrice(231.0D);
            l_eqtypeOrderExecutionParams.setOrderUnitId(3304148080001L);
            l_eqtypeOrderExecutionParams.setOrderExecutionId(4213L);
            l_eqtypeOrderExecutionParams.setExecSerialNo(5);
            l_eqtypeOrderExecutionParams.setExecTimestamp(Calendar.getInstance().getTime());

            EqtypeOrderExecutionParams l_eqtypeOrderExecutionParams1 = TestDBUtility.getEqtypeOrderExecutionRow();
            l_eqtypeOrderExecutionParams1.setExecQuantity(2.0D);
            l_eqtypeOrderExecutionParams1.setExecPrice(231.0D);
            l_eqtypeOrderExecutionParams1.setOrderUnitId(3304148080001L);
            l_eqtypeOrderExecutionParams1.setExecSerialNo(2);
            l_eqtypeOrderExecutionParams1.setExecTimestamp(Calendar.getInstance().getTime());

            TestDBUtility.insertWithDel(l_eqtypeOrderExecutionParams);
            TestDBUtility.insertWithDel(l_eqtypeOrderExecutionParams1);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            PersistenceManagerImpl pm = new PersistenceManagerImpl();
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderRequestNumber("256");
            EqTypeOrderUnitImpl l_impl = new EqTypeOrderUnitImpl(pm, l_eqtypeOrderUnitParams);

            OrderExecution l_orderExecution = l_service.validateCancelExecEnableExecute(l_request, l_impl);
            assertEquals("2", "" + l_orderExecution.getExecutionSerialNo());

        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validate出来取消可能約定 約定データ.約定数量 !＝ 引数.リクエストデータ.約定数量
     * WEB3ErrorCatalog.BUSINESS_ERROR_00676
     */
    public void test_validateCancelExecEnableExecute_0002()
    {
        final String STR_METHOD_NAME = "test_validateCancelExecEnableExecute_0002()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminEquityPTSCancelExecServiceImpl l_service = new WEB3AdminEquityPTSCancelExecServiceImpl();

        WEB3AdminEquityPTSInputCancelExecCommonRequest l_request = new WEB3AdminEquityPTSInputCancelExecCommonRequest();

        l_request.executionTimeStamp = Calendar.getInstance().getTime();
        l_request.execPrice = "231";
        l_request.execQuantity = "2";
        try
        {
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            EqtypeOrderExecutionParams l_eqtypeOrderExecutionParams = TestDBUtility.getEqtypeOrderExecutionRow();
            l_eqtypeOrderExecutionParams.setExecQuantity(12.0D);
            l_eqtypeOrderExecutionParams.setExecPrice(231.0D);
            l_eqtypeOrderExecutionParams.setOrderUnitId(3304148080001L);
            l_eqtypeOrderExecutionParams.setOrderExecutionId(4213L);
            l_eqtypeOrderExecutionParams.setExecSerialNo(5);
            l_eqtypeOrderExecutionParams.setExecTimestamp(Calendar.getInstance().getTime());

            TestDBUtility.insertWithDel(l_eqtypeOrderExecutionParams);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            PersistenceManagerImpl pm = new PersistenceManagerImpl();
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderRequestNumber("256");
            EqTypeOrderUnitImpl l_impl = new EqTypeOrderUnitImpl(pm, l_eqtypeOrderUnitParams);

            l_service.validateCancelExecEnableExecute(l_request, l_impl);
            fail();

        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00676, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validate出来取消可能約定 約定データ.約定単価 !＝ 引数.リクエストデータ.約定単価
     * WEB3ErrorCatalog.BUSINESS_ERROR_00676
     */
    public void test_validateCancelExecEnableExecute_0003()
    {
        final String STR_METHOD_NAME = "test_validateCancelExecEnableExecute_0003()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminEquityPTSCancelExecServiceImpl l_service = new WEB3AdminEquityPTSCancelExecServiceImpl();

        WEB3AdminEquityPTSInputCancelExecCommonRequest l_request = new WEB3AdminEquityPTSInputCancelExecCommonRequest();

        l_request.executionTimeStamp = Calendar.getInstance().getTime();
        l_request.execPrice = "231";
        l_request.execQuantity = "2";

        try
        {
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            EqtypeOrderExecutionParams l_eqtypeOrderExecutionParams = TestDBUtility.getEqtypeOrderExecutionRow();
            l_eqtypeOrderExecutionParams.setExecQuantity(2.0D);
            l_eqtypeOrderExecutionParams.setExecPrice(23.0D);
            l_eqtypeOrderExecutionParams.setOrderUnitId(3304148080001L);
            l_eqtypeOrderExecutionParams.setOrderExecutionId(4213L);
            l_eqtypeOrderExecutionParams.setExecSerialNo(5);
            l_eqtypeOrderExecutionParams.setExecTimestamp(Calendar.getInstance().getTime());

            TestDBUtility.insertWithDel(l_eqtypeOrderExecutionParams);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            PersistenceManagerImpl pm = new PersistenceManagerImpl();
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderRequestNumber("256");
            EqTypeOrderUnitImpl l_impl = new EqTypeOrderUnitImpl(pm, l_eqtypeOrderUnitParams);

            l_service.validateCancelExecEnableExecute(l_request, l_impl);
            fail();

        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00676, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validate出来取消可能約定 約定データ.約定日時 !＝ 引数.リクエストデータ.約定日時
     * WEB3ErrorCatalog.BUSINESS_ERROR_00676
     */
    public void test_validateCancelExecEnableExecute_0004()
    {
        final String STR_METHOD_NAME = "test_validateCancelExecEnableExecute_0004()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminEquityPTSCancelExecServiceImpl l_service = new WEB3AdminEquityPTSCancelExecServiceImpl();

        WEB3AdminEquityPTSInputCancelExecCommonRequest l_request = new WEB3AdminEquityPTSInputCancelExecCommonRequest();

        l_request.executionTimeStamp = WEB3DateUtility.getDate("20080505102206", "yyyyMMddHHmmss");
        l_request.execPrice = "231";
        l_request.execQuantity = "2";
        try
        {
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            EqtypeOrderExecutionParams l_eqtypeOrderExecutionParams = TestDBUtility.getEqtypeOrderExecutionRow();
            l_eqtypeOrderExecutionParams.setExecQuantity(2.0D);
            l_eqtypeOrderExecutionParams.setExecPrice(231.0D);
            l_eqtypeOrderExecutionParams.setOrderUnitId(3304148080001L);
            l_eqtypeOrderExecutionParams.setOrderExecutionId(4213L);
            l_eqtypeOrderExecutionParams.setExecSerialNo(5);
            l_eqtypeOrderExecutionParams.setExecTimestamp(Calendar.getInstance().getTime());

            TestDBUtility.insertWithDel(l_eqtypeOrderExecutionParams);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            PersistenceManagerImpl pm = new PersistenceManagerImpl();
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderRequestNumber("256");
            EqTypeOrderUnitImpl l_impl = new EqTypeOrderUnitImpl(pm, l_eqtypeOrderUnitParams);

            l_service.validateCancelExecEnableExecute(l_request, l_impl);
            fail();

        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00676, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validate出来取消可能注文 PTS市場でない場合(市場.isPTS市場() == false)、
     * 「PTS市場でない場合は出来取消不可」の例外をスローする。
     */
    public void test_validateCancelExecEnableOrder_0001()
    {
        final String STR_METHOD_NAME = "test_validateCancelExecEnableOrder_0001()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminEquityPTSCancelExecServiceImpl l_service = new WEB3AdminEquityPTSCancelExecServiceImpl();
        try
        {
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            EqtypeOrderExecutionParams l_eqtypeOrderExecutionParams = TestDBUtility.getEqtypeOrderExecutionRow();
            l_eqtypeOrderExecutionParams.setExecQuantity(2.0D);
            l_eqtypeOrderExecutionParams.setExecPrice(231.0D);
            l_eqtypeOrderExecutionParams.setOrderUnitId(3304148080001L);
            l_eqtypeOrderExecutionParams.setOrderExecutionId(4213L);
            l_eqtypeOrderExecutionParams.setExecSerialNo(5);
            l_eqtypeOrderExecutionParams.setExecTimestamp(Calendar.getInstance().getTime());

            TestDBUtility.insertWithDel(l_eqtypeOrderExecutionParams);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            PersistenceManagerImpl pm = new PersistenceManagerImpl();
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderRequestNumber("256");
            EqTypeOrderUnitImpl l_impl = new EqTypeOrderUnitImpl(pm, l_eqtypeOrderUnitParams);

            l_service.validateCancelExecEnableOrder(l_impl);
            fail();

        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03003, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validate出来取消可能注文 注文単位.isUnExecuted() == true 「注文状態が出来取消不可」の例外をスローする。
     */
    public void test_validateCancelExecEnableOrder_0002()
    {
        final String STR_METHOD_NAME = "test_validateCancelExecEnableOrder_0002()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminEquityPTSCancelExecServiceImpl l_service = new WEB3AdminEquityPTSCancelExecServiceImpl();
        try
        {
            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("equity.pts.market.div");
            l_marketPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_marketPreferencesParams);

            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            EqtypeOrderExecutionParams l_eqtypeOrderExecutionParams = TestDBUtility.getEqtypeOrderExecutionRow();
            l_eqtypeOrderExecutionParams.setExecQuantity(2.0D);
            l_eqtypeOrderExecutionParams.setExecPrice(231.0D);
            l_eqtypeOrderExecutionParams.setOrderUnitId(3304148080001L);
            l_eqtypeOrderExecutionParams.setOrderExecutionId(4213L);
            l_eqtypeOrderExecutionParams.setExecSerialNo(5);
            l_eqtypeOrderExecutionParams.setExecTimestamp(Calendar.getInstance().getTime());

            TestDBUtility.insertWithDel(l_eqtypeOrderExecutionParams);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            PersistenceManagerImpl pm = new PersistenceManagerImpl();
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderRequestNumber("256");

            String str = WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), "yyyyMMdd");

            l_eqtypeOrderUnitParams.setBizDate(str);
            l_eqtypeOrderUnitParams.setExecutedQuantity(0);
            EqTypeOrderUnitImpl l_impl = new EqTypeOrderUnitImpl(pm, l_eqtypeOrderUnitParams);

            l_service.validateCancelExecEnableOrder(l_impl);
            fail();

        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02986, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validate出来取消可能注文 注文IDの発注日 != 業務日付 「注文状態が出来取消不可」の例外をスローする。
     */
    public void test_validateCancelExecEnableOrder_0003()
    {
        final String STR_METHOD_NAME = "test_validateCancelExecEnableOrder_0003()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminEquityPTSCancelExecServiceImpl l_service = new WEB3AdminEquityPTSCancelExecServiceImpl();
        try
        {
            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("equity.pts.market.div");
            l_marketPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_marketPreferencesParams);

            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            EqtypeOrderExecutionParams l_eqtypeOrderExecutionParams = TestDBUtility.getEqtypeOrderExecutionRow();
            l_eqtypeOrderExecutionParams.setExecQuantity(2.0D);
            l_eqtypeOrderExecutionParams.setExecPrice(231.0D);
            l_eqtypeOrderExecutionParams.setOrderUnitId(3304148080001L);
            l_eqtypeOrderExecutionParams.setOrderExecutionId(4213L);
            l_eqtypeOrderExecutionParams.setExecSerialNo(5);
            l_eqtypeOrderExecutionParams.setExecTimestamp(Calendar.getInstance().getTime());

            TestDBUtility.insertWithDel(l_eqtypeOrderExecutionParams);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            PersistenceManagerImpl pm = new PersistenceManagerImpl();
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderRequestNumber("256");

            l_eqtypeOrderUnitParams.setBizDate("20080130");
            l_eqtypeOrderUnitParams.setExecutedQuantity(100);
            EqTypeOrderUnitImpl l_impl = new EqTypeOrderUnitImpl(pm, l_eqtypeOrderUnitParams);

            l_service.validateCancelExecEnableOrder(l_impl);
            fail();

        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02986, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validate出来取消可能注文 注文単位.isUnExecuted() == true 注文IDの発注日 != 業務日付
     * 「注文状態が出来取消不可」の例外をスローする。
     */
    public void test_validateCancelExecEnableOrder_0004()
    {
        final String STR_METHOD_NAME = "test_validateCancelExecEnableOrder_0004()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminEquityPTSCancelExecServiceImpl l_service = new WEB3AdminEquityPTSCancelExecServiceImpl();
        try
        {
            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("equity.pts.market.div");
            l_marketPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_marketPreferencesParams);

            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            EqtypeOrderExecutionParams l_eqtypeOrderExecutionParams = TestDBUtility.getEqtypeOrderExecutionRow();
            l_eqtypeOrderExecutionParams.setExecQuantity(2.0D);
            l_eqtypeOrderExecutionParams.setExecPrice(231.0D);
            l_eqtypeOrderExecutionParams.setOrderUnitId(3304148080001L);
            l_eqtypeOrderExecutionParams.setOrderExecutionId(4213L);
            l_eqtypeOrderExecutionParams.setExecSerialNo(5);
            l_eqtypeOrderExecutionParams.setExecTimestamp(Calendar.getInstance().getTime());

            TestDBUtility.insertWithDel(l_eqtypeOrderExecutionParams);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            PersistenceManagerImpl pm = new PersistenceManagerImpl();
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderRequestNumber("256");

            l_eqtypeOrderUnitParams.setBizDate("20080130");
            l_eqtypeOrderUnitParams.setExecutedQuantity(0);
            EqTypeOrderUnitImpl l_impl = new EqTypeOrderUnitImpl(pm, l_eqtypeOrderUnitParams);

            l_service.validateCancelExecEnableOrder(l_impl);
            fail();

        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02986, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validate出来取消可能注文
     */
    public void test_validateCancelExecEnableOrder_0005()
    {
        final String STR_METHOD_NAME = "test_validateCancelExecEnableOrder_0005()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminEquityPTSCancelExecServiceImpl l_service = new WEB3AdminEquityPTSCancelExecServiceImpl();
        try
        {
            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("equity.pts.market.div");
            l_marketPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_marketPreferencesParams);

            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            EqtypeOrderExecutionParams l_eqtypeOrderExecutionParams = TestDBUtility.getEqtypeOrderExecutionRow();
            l_eqtypeOrderExecutionParams.setExecQuantity(2.0D);
            l_eqtypeOrderExecutionParams.setExecPrice(231.0D);
            l_eqtypeOrderExecutionParams.setOrderUnitId(3304148080001L);
            l_eqtypeOrderExecutionParams.setOrderExecutionId(4213L);
            l_eqtypeOrderExecutionParams.setExecSerialNo(5);
            l_eqtypeOrderExecutionParams.setExecTimestamp(Calendar.getInstance().getTime());

            TestDBUtility.insertWithDel(l_eqtypeOrderExecutionParams);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            PersistenceManagerImpl pm = new PersistenceManagerImpl();
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderRequestNumber("256");

            String str = WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), "yyyyMMdd");

            l_eqtypeOrderUnitParams.setBizDate(str);
            l_eqtypeOrderUnitParams.setExecutedQuantity(100);
            EqTypeOrderUnitImpl l_impl = new EqTypeOrderUnitImpl(pm, l_eqtypeOrderUnitParams);

            l_service.validateCancelExecEnableOrder(l_impl);
            assertTrue(true);

        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validate出来取消 管理者・株式（PTS）出来取消確認リクエスト.validate()例外をスローする
     * 管理者・株式（PTS）出来取消確認リクエスト約定株数=null
     * 「WEB3ErrorCatalog.BUSINESS_ERROR_02989」の例外をスローする
     */
    public void test_validateCancelExec_0001()
    {
        final String STR_METHOD_NAME = "test_validateCancelExec_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSCancelExecServiceImpl l_service = new WEB3AdminEquityPTSCancelExecServiceImpl();
        WEB3AdminEquityPTSCancelExecConfirmRequest l_request = new WEB3AdminEquityPTSCancelExecConfirmRequest();
        try
        {
            l_service.validateCancelExec(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02989, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validate出来取消 validate管理者権限
     * 
     * 「BUSINESS_ERROR_01056」の例外をスローする
     */
    public void test_validateCancelExec_0002()
    {
        final String STR_METHOD_NAME = "test_validateCancelExec_0002";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSCancelExecServiceImpl l_service = new WEB3AdminEquityPTSCancelExecServiceImpl();
        WEB3AdminEquityPTSCancelExecConfirmRequest l_request = new WEB3AdminEquityPTSCancelExecConfirmRequest();

        // 注文ID
        l_request.orderId = "1";
        // 約定株数
        l_request.execQuantity = "100";
        // 約定単価
        l_request.execPrice = "100";
        // 約定日時
        l_request.executionTimeStamp = Calendar.getInstance().getTime();
        try
        {
            // LoginInfoImpl l_LoginInfo = new LoginInfoImpl();
            // TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            // "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            // "getLoginInfo",
            // new Class[] {},
            // l_LoginInfo);

            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);

            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(1);

            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_service.validateCancelExec(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validate出来取消 validate出来入力出来取消可能時間帯
     * 
     * 「BUSINESS_ERROR_03015」の例外をスローする
     */
    public void test_validateCancelExec_0003()
    {
        final String STR_METHOD_NAME = "test_validateCancelExec_0003";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSCancelExecServiceImpl l_service = new WEB3AdminEquityPTSCancelExecServiceImpl();
        WEB3AdminEquityPTSCancelExecConfirmRequest l_request = new WEB3AdminEquityPTSCancelExecConfirmRequest();

        // 注文ID
        l_request.orderId = "1";
        // 約定株数
        l_request.execQuantity = "100";
        // 約定単価
        l_request.execPrice = "100";
        // 約定日時
        l_request.executionTimeStamp = Calendar.getInstance().getTime();
        try
        {
            HostEquityOrderExecNotifyParams l_hostEquityOrderExecNotifyParams = new HostEquityOrderExecNotifyParams();
            l_hostEquityOrderExecNotifyParams.setOrderRequestNumber("256");

            TestDBUtility.insertWithDel(l_hostEquityOrderExecNotifyParams);

            EqtypeOrderExecutionParams l_eqtypeOrderExecutionParams = TestDBUtility.getEqtypeOrderExecutionRow();
            l_eqtypeOrderExecutionParams.setExecQuantity(100.0D);
            l_eqtypeOrderExecutionParams.setExecPrice(100.0D);
            l_eqtypeOrderExecutionParams.setOrderUnitId(3304148080001L);
            l_eqtypeOrderExecutionParams.setOrderExecutionId(4213L);
            l_eqtypeOrderExecutionParams.setExecSerialNo(5);
            l_eqtypeOrderExecutionParams.setExecTimestamp(Calendar.getInstance().getTime());

            TestDBUtility.insertWithDel(l_eqtypeOrderExecutionParams);

            PersistenceManagerImpl pm = new PersistenceManagerImpl();
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderRequestNumber("256");
            l_eqtypeOrderUnitParams.setOrderId(1);

            l_eqtypeOrderUnitParams.setBizDate("20080128");
            l_eqtypeOrderUnitParams.setExecutedQuantity(100);
            l_eqtypeOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.NONE);
            EqTypeOrderUnitImpl l_impl = new EqTypeOrderUnitImpl(pm, l_eqtypeOrderUnitParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080001L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);

            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            WEB3AdministratorForMock.mockValidateAuthority(l_expectAdministrator, "C0109", true, true);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("0");

            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setTradingTimeType("01");
            l_tradingTimeParams1.setBizDateType("1");
            l_tradingTimeParams1.setStartTime("10");

            TradingTimeParams l_tradingTimeParams2 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams2.setTradingTimeType("01");
            l_tradingTimeParams2.setBizDateType("2");
            l_tradingTimeParams2.setStartTime("20");

            TradingTimeParams l_tradingTimeParams3 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams3.setTradingTimeType("01");
            l_tradingTimeParams3.setBizDateType("3");
            l_tradingTimeParams3.setStartTime("30");

            TradingTimeParams l_tradingTimeParams4 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams4.setTradingTimeType("01");
            l_tradingTimeParams4.setBizDateType("4");
            l_tradingTimeParams4.setStartTime("40");

            TradingTimeParams l_tradingTimeParams5 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams5.setTradingTimeType("01");
            l_tradingTimeParams5.setBizDateType("5");
            l_tradingTimeParams5.setStartTime("50");

            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            TestDBUtility.insertWithDel(l_tradingTimeParams2);
            TestDBUtility.insertWithDel(l_tradingTimeParams3);
            TestDBUtility.insertWithDel(l_tradingTimeParams4);
            TestDBUtility.insertWithDel(l_tradingTimeParams5);

            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("equity.pts.market.div");
            l_marketPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_marketPreferencesParams);

            TestDBUtility.deleteAll(PtsOrderexecutionEndRow.TYPE);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl", "getBizDate", new Class[]
                    {}, WEB3DateUtility.getDate("20080128", "yyyyMMdd"));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl", "getSystemTimestamp",
                    new Class[]
                    {}, new Timestamp(WEB3DateUtility.getDate("20080127", "yyyyMMdd").getTime()));

            l_service.validateCancelExec(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03015, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validate出来取消 validate出来取消可能注文
     * 
     * 「BUSINESS_ERROR_03003」の例外をスローする
     */
    public void test_validateCancelExec_0004()
    {
        final String STR_METHOD_NAME = "test_validateCancelExec_0004()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSCancelExecServiceImpl l_service = new WEB3AdminEquityPTSCancelExecServiceImpl();
        WEB3AdminEquityPTSCancelExecConfirmRequest l_request = new WEB3AdminEquityPTSCancelExecConfirmRequest();

        // 注文ID
        l_request.orderId = "1";
        // 約定株数
        l_request.execQuantity = "100";
        // 約定単価
        l_request.execPrice = "100";
        // 約定日時
        l_request.executionTimeStamp = Calendar.getInstance().getTime();
        try
        {
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            WEB3AdministratorForMock.mockValidateAuthority(l_expectAdministrator, "C0109", true, true);

            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(1);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");

            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl", "getSystemTimestamp",
                    new Class[]
                    {}, new Timestamp(WEB3DateUtility.getDate("20080130", "yyyyMMdd").getTime()));

            l_service.validateCancelExec(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03003, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validate出来取消 validate出来取消可能約定
     * 
     * 「WEB3ErrorCatalog.BUSINESS_ERROR_00676」の例外をスローする。
     */
    public void test_validateCancelExec_0005()
    {
        final String STR_METHOD_NAME = "test_validateCancelExec_0005()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSCancelExecServiceImpl l_service = new WEB3AdminEquityPTSCancelExecServiceImpl();
        WEB3AdminEquityPTSCancelExecConfirmRequest l_request = new WEB3AdminEquityPTSCancelExecConfirmRequest();

        // 注文ID
        l_request.orderId = "1";
        // 約定株数
        l_request.execQuantity = "100";
        // 約定単価
        l_request.execPrice = "100";
        // 約定日時
        l_request.executionTimeStamp = Calendar.getInstance().getTime();
        try
        {
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080001L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);

            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            WEB3AdministratorForMock.mockValidateAuthority(l_expectAdministrator, "C0109", true, true);

            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderRequestNumber("256");
            l_eqtypeOrderUnitParams.setOrderId(1);

            String str = WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), "yyyyMMdd");

            l_eqtypeOrderUnitParams.setBizDate(str);
            l_eqtypeOrderUnitParams.setExecutedQuantity(100);
            l_eqtypeOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.NONE);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("0");

            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setTradingTimeType("01");
            l_tradingTimeParams1.setBizDateType("1");
            l_tradingTimeParams1.setStartTime("10");

            TradingTimeParams l_tradingTimeParams2 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams2.setTradingTimeType("01");
            l_tradingTimeParams2.setBizDateType("2");
            l_tradingTimeParams2.setStartTime("20");

            TradingTimeParams l_tradingTimeParams3 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams3.setTradingTimeType("01");
            l_tradingTimeParams3.setBizDateType("3");
            l_tradingTimeParams3.setStartTime("30");

            TradingTimeParams l_tradingTimeParams4 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams4.setTradingTimeType("01");
            l_tradingTimeParams4.setBizDateType("4");
            l_tradingTimeParams4.setStartTime("40");

            TradingTimeParams l_tradingTimeParams5 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams5.setTradingTimeType("01");
            l_tradingTimeParams5.setBizDateType("5");
            l_tradingTimeParams5.setStartTime("50");

            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            TestDBUtility.insertWithDel(l_tradingTimeParams2);
            TestDBUtility.insertWithDel(l_tradingTimeParams3);
            TestDBUtility.insertWithDel(l_tradingTimeParams4);
            TestDBUtility.insertWithDel(l_tradingTimeParams5);

            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("equity.pts.market.div");
            l_marketPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_marketPreferencesParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl", "getSystemTimestamp",
                    new Class[]
                    {}, new Timestamp(GtlUtils.getSystemTimestamp().getTime()));

            l_service.validateCancelExec(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00676, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validate出来取消 get約定履歴()の戻り値がnullの場合、 「データ不整合」の例外をスローする。
     */
    public void test_validateCancelExec_0006()
    {
        final String STR_METHOD_NAME = "test_validateCancelExec_0006()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSCancelExecServiceImpl l_service = new WEB3AdminEquityPTSCancelExecServiceImpl();
        WEB3AdminEquityPTSCancelExecConfirmRequest l_request = new WEB3AdminEquityPTSCancelExecConfirmRequest();

        // 注文ID
        l_request.orderId = "1";
        // 約定株数
        l_request.execQuantity = "100";
        // 約定単価
        l_request.execPrice = "100";
        // 約定日時
        l_request.executionTimeStamp = Calendar.getInstance().getTime();
        try
        {
            HostEquityOrderExecNotifyParams l_hostEquityOrderExecNotifyParams = new HostEquityOrderExecNotifyParams();
            l_hostEquityOrderExecNotifyParams.setOrderRequestNumber("256");

            TestDBUtility.insertWithDel(l_hostEquityOrderExecNotifyParams);

            EqtypeOrderExecutionParams l_eqtypeOrderExecutionParams = TestDBUtility.getEqtypeOrderExecutionRow();
            l_eqtypeOrderExecutionParams.setExecQuantity(100.0D);
            l_eqtypeOrderExecutionParams.setExecPrice(100.0D);
            l_eqtypeOrderExecutionParams.setOrderUnitId(3304148080001L);
            l_eqtypeOrderExecutionParams.setOrderExecutionId(4213L);
            l_eqtypeOrderExecutionParams.setExecSerialNo(5);
            l_eqtypeOrderExecutionParams.setExecTimestamp(Calendar.getInstance().getTime());

            TestDBUtility.insertWithDel(l_eqtypeOrderExecutionParams);

            PersistenceManagerImpl pm = new PersistenceManagerImpl();
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderRequestNumber("256");
            l_eqtypeOrderUnitParams.setOrderId(1);

            l_eqtypeOrderUnitParams.setBizDate("20070128");
            l_eqtypeOrderUnitParams.setExecutedQuantity(100);
            l_eqtypeOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.NONE);
            EqTypeOrderUnitImpl l_impl = new EqTypeOrderUnitImpl(pm, l_eqtypeOrderUnitParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080001L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);

            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            WEB3AdministratorForMock.mockValidateAuthority(l_expectAdministrator, "C0109", true, true);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("0");

            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setTradingTimeType("01");
            l_tradingTimeParams1.setBizDateType("1");
            l_tradingTimeParams1.setStartTime("10");

            TradingTimeParams l_tradingTimeParams2 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams2.setTradingTimeType("01");
            l_tradingTimeParams2.setBizDateType("2");
            l_tradingTimeParams2.setStartTime("20");

            TradingTimeParams l_tradingTimeParams3 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams3.setTradingTimeType("01");
            l_tradingTimeParams3.setBizDateType("3");
            l_tradingTimeParams3.setStartTime("30");

            TradingTimeParams l_tradingTimeParams4 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams4.setTradingTimeType("01");
            l_tradingTimeParams4.setBizDateType("4");
            l_tradingTimeParams4.setStartTime("40");

            TradingTimeParams l_tradingTimeParams5 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams5.setTradingTimeType("01");
            l_tradingTimeParams5.setBizDateType("5");
            l_tradingTimeParams5.setStartTime("50");

            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            TestDBUtility.insertWithDel(l_tradingTimeParams2);
            TestDBUtility.insertWithDel(l_tradingTimeParams3);
            TestDBUtility.insertWithDel(l_tradingTimeParams4);
            TestDBUtility.insertWithDel(l_tradingTimeParams5);

            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(HostEquityOrderExecNotifyRow.TYPE);

            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("equity.pts.market.div");
            l_marketPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_marketPreferencesParams);

            TestDBUtility.deleteAll(PtsOrderexecutionEndRow.TYPE);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl", "getBizDate", new Class[]
                    {}, WEB3DateUtility.getDate("20070128", "yyyyMMdd"));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl", "getSystemTimestamp",
                    new Class[]
                    {}, new Timestamp(GtlUtils.getSystemTimestamp().getTime()));
            l_service.validateCancelExec(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * validate出来取消
     */
    public void test_validateCancelExec_0007()
    {
        final String STR_METHOD_NAME = "test_validateCancelExec_0007()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSCancelExecServiceImpl l_service = new WEB3AdminEquityPTSCancelExecServiceImpl();
        WEB3AdminEquityPTSCancelExecConfirmRequest l_request = new WEB3AdminEquityPTSCancelExecConfirmRequest();

        // 注文ID
        l_request.orderId = "1";
        // 約定株数
        l_request.execQuantity = "100";
        // 約定単価
        l_request.execPrice = "100";
        // 約定日時
        l_request.executionTimeStamp = Calendar.getInstance().getTime();
        try
        {
            HostEquityOrderExecNotifyParams l_hostEquityOrderExecNotifyParams = new HostEquityOrderExecNotifyParams();
            l_hostEquityOrderExecNotifyParams.setOrderRequestNumber("256");

            TestDBUtility.insertWithDel(l_hostEquityOrderExecNotifyParams);

            EqtypeOrderExecutionParams l_eqtypeOrderExecutionParams = TestDBUtility.getEqtypeOrderExecutionRow();
            l_eqtypeOrderExecutionParams.setExecQuantity(100.0D);
            l_eqtypeOrderExecutionParams.setExecPrice(100.0D);
            l_eqtypeOrderExecutionParams.setOrderUnitId(3304148080001L);
            l_eqtypeOrderExecutionParams.setOrderExecutionId(4213L);
            l_eqtypeOrderExecutionParams.setExecSerialNo(5);
            l_eqtypeOrderExecutionParams.setExecTimestamp(Calendar.getInstance().getTime());

            TestDBUtility.insertWithDel(l_eqtypeOrderExecutionParams);

            PersistenceManagerImpl pm = new PersistenceManagerImpl();
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderRequestNumber("256");
            l_eqtypeOrderUnitParams.setOrderId(1);

            l_eqtypeOrderUnitParams.setBizDate("20070128");
            l_eqtypeOrderUnitParams.setExecutedQuantity(100);
            l_eqtypeOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.NONE);
            EqTypeOrderUnitImpl l_impl = new EqTypeOrderUnitImpl(pm, l_eqtypeOrderUnitParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080001L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);

            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            WEB3AdministratorForMock.mockValidateAuthority(l_expectAdministrator, "C0109", true, true);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("0");

            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setTradingTimeType("01");
            l_tradingTimeParams1.setBizDateType("1");
            l_tradingTimeParams1.setStartTime("10");

            TradingTimeParams l_tradingTimeParams2 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams2.setTradingTimeType("01");
            l_tradingTimeParams2.setBizDateType("2");
            l_tradingTimeParams2.setStartTime("20");

            TradingTimeParams l_tradingTimeParams3 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams3.setTradingTimeType("01");
            l_tradingTimeParams3.setBizDateType("3");
            l_tradingTimeParams3.setStartTime("30");

            TradingTimeParams l_tradingTimeParams4 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams4.setTradingTimeType("01");
            l_tradingTimeParams4.setBizDateType("4");
            l_tradingTimeParams4.setStartTime("40");

            TradingTimeParams l_tradingTimeParams5 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams5.setTradingTimeType("01");
            l_tradingTimeParams5.setBizDateType("5");
            l_tradingTimeParams5.setStartTime("50");

            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            TestDBUtility.insertWithDel(l_tradingTimeParams2);
            TestDBUtility.insertWithDel(l_tradingTimeParams3);
            TestDBUtility.insertWithDel(l_tradingTimeParams4);
            TestDBUtility.insertWithDel(l_tradingTimeParams5);

            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("equity.pts.market.div");
            l_marketPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_marketPreferencesParams);

            TestDBUtility.deleteAll(PtsOrderexecutionEndRow.TYPE);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl", "getBizDate", new Class[]
                    {}, WEB3DateUtility.getDate("20070128", "yyyyMMdd"));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl", "getSystemTimestamp",
                    new Class[]
                    {}, new Timestamp(GtlUtils.getSystemTimestamp().getTime()));

            WEB3AdminEquityPTSCancelExecConfirmResponse l_response = l_service.validateCancelExec(l_request);
            WEB3AdminEquityPTSOrderDetailUnit l_unit = l_response.orderDetail;
            WEB3AdminEquityPTSExecHistory[] l_history = l_response.execHistories;

            WEB3AdminEquityPTSExecHistory l_execHistory = l_history[0];
            assertEquals("N8080", l_unit.productCode);

            assertFalse(l_execHistory.cancelFlag);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * submit出来取消 管理者・株式（PTS）出来取消完了リクエスト.validate()例外をスローする
     * 管理者・株式（PTS）出来取消完了リクエスト約定株数=null
     * 「WEB3ErrorCatalog.BUSINESS_ERROR_02989」の例外をスローする
     */
    public void test_submitCancelExec_0001()
    {
        final String STR_METHOD_NAME = "test_submitCancelExec_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSCancelExecServiceImpl l_service = new WEB3AdminEquityPTSCancelExecServiceImpl();
        WEB3AdminEquityPTSCancelExecCompleteRequest l_request = new WEB3AdminEquityPTSCancelExecCompleteRequest();
        try
        {
            l_service.submitCancelExec(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02989, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * submit出来取消 validate管理者権限
     * 
     * 「BUSINESS_ERROR_01056」の例外をスローする
     */
    public void test_submitCancelExec_0002()
    {
        final String STR_METHOD_NAME = "test_submitCancelExec_0002";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSCancelExecServiceImpl l_service = new WEB3AdminEquityPTSCancelExecServiceImpl();
        WEB3AdminEquityPTSCancelExecCompleteRequest l_request = new WEB3AdminEquityPTSCancelExecCompleteRequest();

        // 注文ID
        l_request.orderId = "1";
        // 約定株数
        l_request.execQuantity = "100";
        // 約定単価
        l_request.execPrice = "100";
        // 約定日時
        l_request.executionTimeStamp = Calendar.getInstance().getTime();
        try
        {

            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);

            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(1);

            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            l_service.submitCancelExec(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * submit出来取消 validate取引パスワード
     * 
     * 「WEB3ErrorCatalog.BUSINESS_ERROR_00009」の例外をスローする
     */
    public void test_submitCancelExec_0003()
    {
        final String STR_METHOD_NAME = "test_submitCancelExec_0003";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSCancelExecServiceImpl l_service = new WEB3AdminEquityPTSCancelExecServiceImpl();
        WEB3AdminEquityPTSCancelExecCompleteRequest l_request = new WEB3AdminEquityPTSCancelExecCompleteRequest();

        // 注文ID
        l_request.orderId = "1";
        // 約定株数
        l_request.execQuantity = "100";
        // 約定単価
        l_request.execPrice = "100";
        // 約定日時
        l_request.executionTimeStamp = Calendar.getInstance().getTime();

        l_request.password = "1";
        try
        {
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            WEB3AdministratorForMock.mockValidateAuthority(l_expectAdministrator, "C0109", true, true);
            WEB3AdministratorForMock.mockValidateTradingPassword("1", false);

            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(1);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
            // l_tradingTimeParams.setBizDateType("1");

            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl", "getSystemTimestamp",
                    new Class[]
                    {}, new Timestamp(WEB3DateUtility.getDate("20080127", "yyyyMMdd").getTime()));

            l_service.submitCancelExec(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00009, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * submit出来取消 validate出来入力出来取消可能時間帯
     * 
     * 「BUSINESS_ERROR_03015」の例外をスローする
     */
    public void test_submitCancelExec_0004()
    {
        final String STR_METHOD_NAME = "test_submitCancelExec_0004";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSCancelExecServiceImpl l_service = new WEB3AdminEquityPTSCancelExecServiceImpl();
        WEB3AdminEquityPTSCancelExecCompleteRequest l_request = new WEB3AdminEquityPTSCancelExecCompleteRequest();

        // 注文ID
        l_request.orderId = "1";
        // 約定株数
        l_request.execQuantity = "100";
        // 約定単価
        l_request.execPrice = "100";
        // 約定日時
        l_request.executionTimeStamp = Calendar.getInstance().getTime();
        try
        {
            HostEquityOrderExecNotifyParams l_hostEquityOrderExecNotifyParams = new HostEquityOrderExecNotifyParams();
            l_hostEquityOrderExecNotifyParams.setOrderRequestNumber("256");

            TestDBUtility.insertWithDel(l_hostEquityOrderExecNotifyParams);

            EqtypeOrderExecutionParams l_eqtypeOrderExecutionParams = TestDBUtility.getEqtypeOrderExecutionRow();
            l_eqtypeOrderExecutionParams.setExecQuantity(100.0D);
            l_eqtypeOrderExecutionParams.setExecPrice(100.0D);
            l_eqtypeOrderExecutionParams.setOrderUnitId(3304148080001L);
            l_eqtypeOrderExecutionParams.setOrderExecutionId(4213L);
            l_eqtypeOrderExecutionParams.setExecSerialNo(5);
            l_eqtypeOrderExecutionParams.setExecTimestamp(Calendar.getInstance().getTime());

            TestDBUtility.insertWithDel(l_eqtypeOrderExecutionParams);

            PersistenceManagerImpl pm = new PersistenceManagerImpl();
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderRequestNumber("256");
            l_eqtypeOrderUnitParams.setOrderId(1);

            l_eqtypeOrderUnitParams.setBizDate("20080128");
            l_eqtypeOrderUnitParams.setExecutedQuantity(100);
            l_eqtypeOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.NONE);
            EqTypeOrderUnitImpl l_impl = new EqTypeOrderUnitImpl(pm, l_eqtypeOrderUnitParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080001L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);

            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            WEB3AdministratorForMock.mockValidateAuthority(l_expectAdministrator, "C0109", true, true);
            WEB3AdministratorForMock.mockValidateTradingPassword("1", true);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("0");

            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setTradingTimeType("01");
            l_tradingTimeParams1.setBizDateType("1");
            l_tradingTimeParams1.setStartTime("10");

            TradingTimeParams l_tradingTimeParams2 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams2.setTradingTimeType("01");
            l_tradingTimeParams2.setBizDateType("2");
            l_tradingTimeParams2.setStartTime("20");

            TradingTimeParams l_tradingTimeParams3 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams3.setTradingTimeType("01");
            l_tradingTimeParams3.setBizDateType("3");
            l_tradingTimeParams3.setStartTime("30");

            TradingTimeParams l_tradingTimeParams4 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams4.setTradingTimeType("01");
            l_tradingTimeParams4.setBizDateType("4");
            l_tradingTimeParams4.setStartTime("40");

            TradingTimeParams l_tradingTimeParams5 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams5.setTradingTimeType("01");
            l_tradingTimeParams5.setBizDateType("5");
            l_tradingTimeParams5.setStartTime("50");

            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            TestDBUtility.insertWithDel(l_tradingTimeParams2);
            TestDBUtility.insertWithDel(l_tradingTimeParams3);
            TestDBUtility.insertWithDel(l_tradingTimeParams4);
            TestDBUtility.insertWithDel(l_tradingTimeParams5);

            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("equity.pts.market.div");
            l_marketPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_marketPreferencesParams);

            TestDBUtility.deleteAll(PtsOrderexecutionEndRow.TYPE);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl", "getBizDate", new Class[]
                    {}, WEB3DateUtility.getDate("20080128", "yyyyMMdd"));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl", "getSystemTimestamp",
                    new Class[]
                    {}, new Timestamp(WEB3DateUtility.getDate("20080127", "yyyyMMdd").getTime()));

            l_service.submitCancelExec(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03015, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * submit出来取消 validate出来取消可能注文
     * 
     * 「BUSINESS_ERROR_03003」の例外をスローする
     */
    public void test_submitCancelExec_0005()
    {
        final String STR_METHOD_NAME = "test_submitCancelExec_0005()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSCancelExecServiceImpl l_service = new WEB3AdminEquityPTSCancelExecServiceImpl();
        WEB3AdminEquityPTSCancelExecCompleteRequest l_request = new WEB3AdminEquityPTSCancelExecCompleteRequest();

        // 注文ID
        l_request.orderId = "1";
        // 約定株数
        l_request.execQuantity = "100";
        // 約定単価
        l_request.execPrice = "100";
        // 約定日時
        l_request.executionTimeStamp = Calendar.getInstance().getTime();
        try
        {
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);

            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            WEB3AdministratorForMock.mockValidateAuthority(l_expectAdministrator, "C0109", true, true);
            WEB3AdministratorForMock.mockValidateTradingPassword("1", true);

            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(1);
            l_eqtypeOrderUnitParams.setAccountId(333812512203L);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");

            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl", "getSystemTimestamp",
                    new Class[]
                    {}, new Timestamp(WEB3DateUtility.getDate("20080130", "yyyyMMdd").getTime()));

            l_service.submitCancelExec(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03003, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * submit出来取消 validate出来取消可能約定
     * 
     * 「WEB3ErrorCatalog.BUSINESS_ERROR_00676」の例外をスローする。
     */
    public void test_submitCancelExec_0006()
    {
        final String STR_METHOD_NAME = "test_submitCancelExec_0006()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSCancelExecServiceImpl l_service = new WEB3AdminEquityPTSCancelExecServiceImpl();
        WEB3AdminEquityPTSCancelExecCompleteRequest l_request = new WEB3AdminEquityPTSCancelExecCompleteRequest();

        // 注文ID
        l_request.orderId = "1";
        // 約定株数
        l_request.execQuantity = "100";
        // 約定単価
        l_request.execPrice = "100";
        // 約定日時
        l_request.executionTimeStamp = Calendar.getInstance().getTime();
        try
        {
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080001L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);

            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            WEB3AdministratorForMock.mockValidateAuthority(l_expectAdministrator, "C0109", true, true);
            WEB3AdministratorForMock.mockValidateTradingPassword("1", true);

            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderRequestNumber("256");
            l_eqtypeOrderUnitParams.setOrderId(1);

            String str = WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), "yyyyMMdd");

            l_eqtypeOrderUnitParams.setBizDate(str);
            l_eqtypeOrderUnitParams.setExecutedQuantity(100);
            l_eqtypeOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.NONE);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("0");

            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setTradingTimeType("01");
            l_tradingTimeParams1.setBizDateType("1");
            l_tradingTimeParams1.setStartTime("10");

            TradingTimeParams l_tradingTimeParams2 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams2.setTradingTimeType("01");
            l_tradingTimeParams2.setBizDateType("2");
            l_tradingTimeParams2.setStartTime("20");

            TradingTimeParams l_tradingTimeParams3 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams3.setTradingTimeType("01");
            l_tradingTimeParams3.setBizDateType("3");
            l_tradingTimeParams3.setStartTime("30");

            TradingTimeParams l_tradingTimeParams4 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams4.setTradingTimeType("01");
            l_tradingTimeParams4.setBizDateType("4");
            l_tradingTimeParams4.setStartTime("40");

            TradingTimeParams l_tradingTimeParams5 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams5.setTradingTimeType("01");
            l_tradingTimeParams5.setBizDateType("5");
            l_tradingTimeParams5.setStartTime("50");

            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            TestDBUtility.insertWithDel(l_tradingTimeParams2);
            TestDBUtility.insertWithDel(l_tradingTimeParams3);
            TestDBUtility.insertWithDel(l_tradingTimeParams4);
            TestDBUtility.insertWithDel(l_tradingTimeParams5);

            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("equity.pts.market.div");
            l_marketPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_marketPreferencesParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl", "getSystemTimestamp",
                    new Class[]
                    {}, new Timestamp(GtlUtils.getSystemTimestamp().getTime()));

            l_service.submitCancelExec(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00676, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * submit出来取消 get約定履歴()の戻り値がnullの場合、 「データ不整合」の例外をスローする。
     */
    public void test_submitCancelExec_0007()
    {
        final String STR_METHOD_NAME = "test_submitCancelExec_0007()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSCancelExecServiceImpl l_service = new WEB3AdminEquityPTSCancelExecServiceImpl();
        WEB3AdminEquityPTSCancelExecCompleteRequest l_request = new WEB3AdminEquityPTSCancelExecCompleteRequest();

        // 注文ID
        l_request.orderId = "1";
        // 約定株数
        l_request.execQuantity = "100";
        // 約定単価
        l_request.execPrice = "100";
        // 約定日時
        l_request.executionTimeStamp = Calendar.getInstance().getTime();
        try
        {
            HostEquityOrderExecNotifyParams l_hostEquityOrderExecNotifyParams = new HostEquityOrderExecNotifyParams();
            l_hostEquityOrderExecNotifyParams.setOrderRequestNumber("256");

            TestDBUtility.insertWithDel(l_hostEquityOrderExecNotifyParams);

            EqtypeOrderExecutionParams l_eqtypeOrderExecutionParams = TestDBUtility.getEqtypeOrderExecutionRow();
            l_eqtypeOrderExecutionParams.setExecQuantity(100.0D);
            l_eqtypeOrderExecutionParams.setExecPrice(100.0D);
            l_eqtypeOrderExecutionParams.setOrderUnitId(3304148080001L);
            l_eqtypeOrderExecutionParams.setOrderExecutionId(4213L);
            l_eqtypeOrderExecutionParams.setExecSerialNo(5);
            l_eqtypeOrderExecutionParams.setExecTimestamp(Calendar.getInstance().getTime());

            TestDBUtility.insertWithDel(l_eqtypeOrderExecutionParams);

            PersistenceManagerImpl pm = new PersistenceManagerImpl();
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderRequestNumber("256");
            l_eqtypeOrderUnitParams.setOrderId(1);

            l_eqtypeOrderUnitParams.setBizDate("20080128");
            l_eqtypeOrderUnitParams.setExecutedQuantity(100);
            l_eqtypeOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.NONE);
            EqTypeOrderUnitImpl l_impl = new EqTypeOrderUnitImpl(pm, l_eqtypeOrderUnitParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080001L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);

            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            WEB3AdministratorForMock.mockValidateAuthority(l_expectAdministrator, "C0109", true, true);
            WEB3AdministratorForMock.mockValidateTradingPassword("1", true);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("0");

            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setTradingTimeType("01");
            l_tradingTimeParams1.setBizDateType("1");
            l_tradingTimeParams1.setStartTime("10");

            TradingTimeParams l_tradingTimeParams2 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams2.setTradingTimeType("01");
            l_tradingTimeParams2.setBizDateType("2");
            l_tradingTimeParams2.setStartTime("20");

            TradingTimeParams l_tradingTimeParams3 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams3.setTradingTimeType("01");
            l_tradingTimeParams3.setBizDateType("3");
            l_tradingTimeParams3.setStartTime("30");

            TradingTimeParams l_tradingTimeParams4 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams4.setTradingTimeType("01");
            l_tradingTimeParams4.setBizDateType("4");
            l_tradingTimeParams4.setStartTime("40");

            TradingTimeParams l_tradingTimeParams5 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams5.setTradingTimeType("01");
            l_tradingTimeParams5.setBizDateType("5");
            l_tradingTimeParams5.setStartTime("50");

            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            TestDBUtility.insertWithDel(l_tradingTimeParams2);
            TestDBUtility.insertWithDel(l_tradingTimeParams3);
            TestDBUtility.insertWithDel(l_tradingTimeParams4);
            TestDBUtility.insertWithDel(l_tradingTimeParams5);

            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("equity.pts.market.div");
            l_marketPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_marketPreferencesParams);

            TestDBUtility.deleteAll(HostEquityOrderExecNotifyRow.TYPE);
            TestDBUtility.deleteAll(PtsOrderexecutionEndRow.TYPE);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl", "getBizDate", new Class[]
                    {}, WEB3DateUtility.getDate("20080128", "yyyyMMdd"));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl", "getSystemTimestamp",
                    new Class[]
                    {}, new Timestamp(GtlUtils.getSystemTimestamp().getTime()));

            l_service.submitCancelExec(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * submit出来取消
     */
    public void test_submitCancelExec_0008()
    {
        final String STR_METHOD_NAME = "test_submitCancelExec_0008()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSCancelExecServiceImpl l_service = new WEB3AdminEquityPTSCancelExecServiceImpl();
        WEB3AdminEquityPTSCancelExecCompleteRequest l_request = new WEB3AdminEquityPTSCancelExecCompleteRequest();

        // 注文ID
        l_request.orderId = "1";
        // 約定株数
        l_request.execQuantity = "100";
        // 約定単価
        l_request.execPrice = "100";
        // 約定日時
        l_request.executionTimeStamp = Calendar.getInstance().getTime();
        try
        {
            HostEquityOrderExecNotifyParams l_hostEquityOrderExecNotifyParams = new HostEquityOrderExecNotifyParams();
            l_hostEquityOrderExecNotifyParams.setOrderRequestNumber("256");

            TestDBUtility.insertWithDel(l_hostEquityOrderExecNotifyParams);

            EqtypeOrderExecutionParams l_eqtypeOrderExecutionParams = TestDBUtility.getEqtypeOrderExecutionRow();
            l_eqtypeOrderExecutionParams.setExecQuantity(100.0D);
            l_eqtypeOrderExecutionParams.setExecPrice(100.0D);
            l_eqtypeOrderExecutionParams.setOrderUnitId(3304148080001L);
            l_eqtypeOrderExecutionParams.setOrderExecutionId(4213L);
            l_eqtypeOrderExecutionParams.setExecSerialNo(5);
            l_eqtypeOrderExecutionParams.setExecTimestamp(Calendar.getInstance().getTime());

            TestDBUtility.insertWithDel(l_eqtypeOrderExecutionParams);

            PersistenceManagerImpl pm = new PersistenceManagerImpl();
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderRequestNumber("256");
            l_eqtypeOrderUnitParams.setOrderId(1);

            l_eqtypeOrderUnitParams.setBizDate("20080128");
            l_eqtypeOrderUnitParams.setExecutedQuantity(100);
            l_eqtypeOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.NONE);
            EqTypeOrderUnitImpl l_impl = new EqTypeOrderUnitImpl(pm, l_eqtypeOrderUnitParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080001L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);

            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080001L);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            WEB3Administrator l_expectAdministrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_expectAdministrator);
            WEB3AdministratorForMock.mockValidateAuthority(l_expectAdministrator, "C0109", true, true);
            WEB3AdministratorForMock.mockValidateTradingPassword("1", true);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("0");

            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setTradingTimeType("01");
            l_tradingTimeParams1.setBizDateType("1");
            l_tradingTimeParams1.setStartTime("10");

            TradingTimeParams l_tradingTimeParams2 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams2.setTradingTimeType("01");
            l_tradingTimeParams2.setBizDateType("2");
            l_tradingTimeParams2.setStartTime("20");

            TradingTimeParams l_tradingTimeParams3 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams3.setTradingTimeType("01");
            l_tradingTimeParams3.setBizDateType("3");
            l_tradingTimeParams3.setStartTime("30");

            TradingTimeParams l_tradingTimeParams4 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams4.setTradingTimeType("01");
            l_tradingTimeParams4.setBizDateType("4");
            l_tradingTimeParams4.setStartTime("40");

            TradingTimeParams l_tradingTimeParams5 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams5.setTradingTimeType("01");
            l_tradingTimeParams5.setBizDateType("5");
            l_tradingTimeParams5.setStartTime("50");

            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            TestDBUtility.insertWithDel(l_tradingTimeParams2);
            TestDBUtility.insertWithDel(l_tradingTimeParams3);
            TestDBUtility.insertWithDel(l_tradingTimeParams4);
            TestDBUtility.insertWithDel(l_tradingTimeParams5);

            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("equity.pts.market.div");
            l_marketPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_marketPreferencesParams);

            TestDBUtility.deleteAll(PtsOrderexecutionEndRow.TYPE);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl", "getBizDate", new Class[]
                    {}, WEB3DateUtility.getDate("20080128", "yyyyMMdd"));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl", "getSystemTimestamp",
                    new Class[]
                    {}, new Timestamp(GtlUtils.getSystemTimestamp().getTime()));

            WEB3AdminEquityPTSCancelExecCompleteResponse l_response = l_service.submitCancelExec(l_request);
            WEB3AdminEquityPTSOrderDetailUnit l_unit = l_response.orderDetail;
            WEB3AdminEquityPTSExecHistory[] l_history = l_response.execHistories;

            WEB3AdminEquityPTSExecHistory l_execHistory = l_history[0];
            assertEquals("N8080", l_unit.productCode);

            assertFalse(l_execHistory.cancelFlag);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * execute 管理者・株式（PTS）出来取消入力リクエストの場合
     */
    public void test_execute_0001()
    {
        final String STR_METHOD_NAME = "test_execute_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSCancelExecServiceImpl l_service = new WEB3AdminEquityPTSCancelExecServiceImpl();
        WEB3AdminEquityPTSCancelExecInputRequest l_request = new WEB3AdminEquityPTSCancelExecInputRequest();

        try
        {
            l_service.execute(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00600, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * execute 管理者・株式（PTS）出来取消確認リクエストの場合
     */
    public void test_execute_0002()
    {
        final String STR_METHOD_NAME = "test_execute_0002()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSCancelExecServiceImpl l_service = new WEB3AdminEquityPTSCancelExecServiceImpl();
        WEB3AdminEquityPTSCancelExecConfirmRequest l_request = new WEB3AdminEquityPTSCancelExecConfirmRequest();

        try
        {
            l_service.execute(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02989, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * execute 管理者・株式（PTS）出来取消完了リクエストの場合
     */
    public void test_execute_0003()
    {
        final String STR_METHOD_NAME = "test_execute_0003()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSCancelExecServiceImpl l_service = new WEB3AdminEquityPTSCancelExecServiceImpl();
        WEB3AdminEquityPTSCancelExecCompleteRequest l_request = new WEB3AdminEquityPTSCancelExecCompleteRequest();

        try
        {
            l_service.execute(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02989, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * execute 管理者・株式（PTS）出来取消入力リクエストの場合 管理者・株式（PTS）出来取消確認リクエストの場合
     * 管理者・株式（PTS）出来取消完了リクエストの場合 以上以外的場合
     */
    public void test_execute_0004()
    {
        final String STR_METHOD_NAME = "test_execute_0004()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSCancelExecServiceImpl l_service = new WEB3AdminEquityPTSCancelExecServiceImpl();
        WEB3AdminEquityManualLapseConfirmRequest l_request = new WEB3AdminEquityManualLapseConfirmRequest();

        try
        {
            l_service.execute(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * execute l_request = null
     */
    public void test_execute_0005()
    {
        final String STR_METHOD_NAME = "test_execute_0005()";
        log.entering(STR_METHOD_NAME);
        WEB3AdminEquityPTSCancelExecServiceImpl l_service = new WEB3AdminEquityPTSCancelExecServiceImpl();

        try
        {
            l_service.execute(null);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    // ３－１）株式ポジションマネージャ.get保有資産()をコールし、
    // 保有資産オブジェクトを取得する。
    // [引数]
    // 口座ID：引数.注文単位.口座ID
    // 補助口座ID：引数.注文単位.補助口座ID
    // 銘柄ID：引数.注文単位.銘柄ID
    // 税区分：引数.注文単位.税区分
    // ※保有資産が取得できない場合、
    // 「保有資産該当データなし」の例外をスローする。
    public void test_validateCancelExecEnableExecute_0005()
    {
        final String STR_METHOD_NAME = "test_validateCancelExecEnableExecute_0005()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminEquityPTSCancelExecServiceImpl l_service = new WEB3AdminEquityPTSCancelExecServiceImpl();

        WEB3AdminEquityPTSInputCancelExecCommonRequest l_request = new WEB3AdminEquityPTSInputCancelExecCommonRequest();

        l_request.executionTimeStamp = WEB3DateUtility.getDate("20080505102206", "yyyyMMddHHmmss");
        l_request.execPrice = "231";
        l_request.execQuantity = "2";
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            EqtypeOrderExecutionParams l_eqtypeOrderExecutionParams = TestDBUtility.getEqtypeOrderExecutionRow();
            l_eqtypeOrderExecutionParams.setExecQuantity(2.0D);
            l_eqtypeOrderExecutionParams.setExecPrice(231.0D);
            l_eqtypeOrderExecutionParams.setOrderUnitId(3304148080001L);
            l_eqtypeOrderExecutionParams.setOrderExecutionId(4213L);
            l_eqtypeOrderExecutionParams.setExecSerialNo(5);
            l_eqtypeOrderExecutionParams.setExecTimestamp(WEB3DateUtility.getDate("20080505102206", "yyyyMMddHHmmss"));

            TestDBUtility.insertWithDel(l_eqtypeOrderExecutionParams);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            PersistenceManagerImpl pm = new PersistenceManagerImpl();
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderRequestNumber("256");

            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_BUY);

            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_EqtypeOrderParams = TestDBUtility.getEqtypeOrderUnitRow();
            //l_EqtypeOrderParams.setOrderRequestNumber("256");
            TestDBUtility.insertWithDel(l_EqtypeOrderParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_ProductParams = TestDBUtility.getProductRow();
            l_ProductParams.setProductId(l_EqtypeOrderParams.getProductId());
            l_ProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_ProductParams);

            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_EqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_EqtypeProductParams.setProductId(l_EqtypeOrderParams.getProductId());
            l_EqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_EqtypeProductParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.equity.WEB3EquityPositionManager",
                    "getAsset", new Class[]
                    {long.class, long.class, long.class, TaxTypeEnum.class}, null);

            TestDBUtility.deleteAll(AssetRow.TYPE);
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            TestDBUtility.insertWithDel(l_assetParams);

            EqTypeOrderUnitImpl l_impl = new EqTypeOrderUnitImplForTest(pm, l_eqtypeOrderUnitParams);
            l_service.validateCancelExecEnableExecute(l_request, l_impl);
            fail();

        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00204, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    // ３－２）保有資産.数量－保有資産.ロック中数量 ＜ 引数.リクエストデータ.約定数量
    // の場合、「保有資産残数量チェックエラー。」の例外をスローする。
    public void test_validateCancelExecEnableExecute_0006()
    {
        final String STR_METHOD_NAME = "test_validateCancelExecEnableExecute_0006()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminEquityPTSCancelExecServiceImpl l_service = new WEB3AdminEquityPTSCancelExecServiceImpl();

        WEB3AdminEquityPTSInputCancelExecCommonRequest l_request = new WEB3AdminEquityPTSInputCancelExecCommonRequest();

        l_request.executionTimeStamp = WEB3DateUtility.getDate("20080505102206", "yyyyMMddHHmmss");
        l_request.execPrice = "231";
        l_request.execQuantity = "20000";
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            EqtypeOrderExecutionParams l_eqtypeOrderExecutionParams = TestDBUtility.getEqtypeOrderExecutionRow();
            l_eqtypeOrderExecutionParams.setExecQuantity(20000.0D);
            l_eqtypeOrderExecutionParams.setExecPrice(231.0D);
            l_eqtypeOrderExecutionParams.setOrderUnitId(3304148080001L);
            l_eqtypeOrderExecutionParams.setOrderExecutionId(4213L);
            l_eqtypeOrderExecutionParams.setExecSerialNo(5);
            l_eqtypeOrderExecutionParams.setExecTimestamp(WEB3DateUtility.getDate("20080505102206", "yyyyMMddHHmmss"));

            TestDBUtility.insertWithDel(l_eqtypeOrderExecutionParams);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            PersistenceManagerImpl pm = new PersistenceManagerImpl();
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderRequestNumber("256");

            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_EqtypeOrderParams = TestDBUtility.getEqtypeOrderUnitRow();
            //l_EqtypeOrderParams.setOrderRequestNumber("256");
            TestDBUtility.insertWithDel(l_EqtypeOrderParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_ProductParams = TestDBUtility.getProductRow();
            l_ProductParams.setProductId(l_EqtypeOrderParams.getProductId());
            l_ProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_ProductParams);

            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_EqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_EqtypeProductParams.setProductId(l_EqtypeOrderParams.getProductId());
            l_EqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_EqtypeProductParams);

            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_BUY);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.equity.WEB3EquityPositionManager",
                    "getAsset", new Class[]
                    {long.class, long.class, long.class, TaxTypeEnum.class}, new EqTypeAssetForTest());

            TestDBUtility.deleteAll(AssetRow.TYPE);
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_dbQuantity = 200;
            l_dbLockedQuantity = 200;
            TestDBUtility.insertWithDel(l_assetParams);

            EqTypeOrderUnitImpl l_impl = new EqTypeOrderUnitImplForTest(pm, l_eqtypeOrderUnitParams);
            l_service.validateCancelExecEnableExecute(l_request, l_impl);
            fail();

        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01931, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    // normal case
    public void test_validateCancelExecEnableExecute_0007()
    {
        final String STR_METHOD_NAME = "test_validateCancelExecEnableExecute_0007()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminEquityPTSCancelExecServiceImpl l_service = new WEB3AdminEquityPTSCancelExecServiceImpl();

        WEB3AdminEquityPTSInputCancelExecCommonRequest l_request = new WEB3AdminEquityPTSInputCancelExecCommonRequest();

        l_request.executionTimeStamp = WEB3DateUtility.getDate("20080505102206", "yyyyMMddHHmmss");
        l_request.execPrice = "231";
        l_request.execQuantity = "2";
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            EqtypeOrderExecutionParams l_eqtypeOrderExecutionParams = TestDBUtility.getEqtypeOrderExecutionRow();
            l_eqtypeOrderExecutionParams.setExecQuantity(2.0D);
            l_eqtypeOrderExecutionParams.setExecPrice(231.0D);
            l_eqtypeOrderExecutionParams.setOrderUnitId(3304148080001L);
            l_eqtypeOrderExecutionParams.setOrderExecutionId(4213L);
            l_eqtypeOrderExecutionParams.setExecSerialNo(5);
            l_eqtypeOrderExecutionParams.setExecTimestamp(WEB3DateUtility.getDate("20080505102206", "yyyyMMddHHmmss"));

            TestDBUtility.insertWithDel(l_eqtypeOrderExecutionParams);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            PersistenceManagerImpl pm = new PersistenceManagerImpl();
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderRequestNumber("256");

            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_EqtypeOrderParams = TestDBUtility.getEqtypeOrderUnitRow();
            //l_EqtypeOrderParams.setOrderRequestNumber("256");
            TestDBUtility.insertWithDel(l_EqtypeOrderParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_ProductParams = TestDBUtility.getProductRow();
            l_ProductParams.setProductId(l_EqtypeOrderParams.getProductId());
            l_ProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_ProductParams);

            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_EqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_EqtypeProductParams.setProductId(l_EqtypeOrderParams.getProductId());
            l_EqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_EqtypeProductParams);

            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_BUY);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.equity.WEB3EquityPositionManager",
                    "getAsset", new Class[]
                    {long.class, long.class, long.class, TaxTypeEnum.class}, new EqTypeAssetForTest());

            TestDBUtility.deleteAll(AssetRow.TYPE);
            AssetParams l_assetParams = TestDBUtility.getAssetRow();
            l_dbQuantity = 20;
            l_dbLockedQuantity = 10;
            TestDBUtility.insertWithDel(l_assetParams);

            EqTypeOrderUnitImpl l_impl = new EqTypeOrderUnitImplForTest(pm, l_eqtypeOrderUnitParams);
            l_service.validateCancelExecEnableExecute(l_request, l_impl);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    // 引数.注文単位.注文種別 !＝ 現物買注文 の場合
    public void test_validateCancelExecEnableExecute_0008()
    {
        final String STR_METHOD_NAME = "test_validateCancelExecEnableExecute_0008()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminEquityPTSCancelExecServiceImpl l_service = new WEB3AdminEquityPTSCancelExecServiceImpl();

        WEB3AdminEquityPTSInputCancelExecCommonRequest l_request = new WEB3AdminEquityPTSInputCancelExecCommonRequest();

        l_request.executionTimeStamp = WEB3DateUtility.getDate("20080505102206", "yyyyMMddHHmmss");
        l_request.execPrice = "231";
        l_request.execQuantity = "2";
        try
        {
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            EqtypeOrderExecutionParams l_eqtypeOrderExecutionParams = TestDBUtility.getEqtypeOrderExecutionRow();
            l_eqtypeOrderExecutionParams.setExecQuantity(2.0D);
            l_eqtypeOrderExecutionParams.setExecPrice(231.0D);
            l_eqtypeOrderExecutionParams.setOrderUnitId(3304148080001L);
            l_eqtypeOrderExecutionParams.setOrderExecutionId(4213L);
            l_eqtypeOrderExecutionParams.setExecSerialNo(5);
            l_eqtypeOrderExecutionParams.setExecTimestamp(WEB3DateUtility.getDate("20080505102206", "yyyyMMddHHmmss"));

            TestDBUtility.insertWithDel(l_eqtypeOrderExecutionParams);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            PersistenceManagerImpl pm = new PersistenceManagerImpl();
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderRequestNumber("256");

            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_EqtypeOrderParams = TestDBUtility.getEqtypeOrderUnitRow();
            //l_EqtypeOrderParams.setOrderRequestNumber("256");
            TestDBUtility.insertWithDel(l_EqtypeOrderParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_ProductParams = TestDBUtility.getProductRow();
            l_ProductParams.setProductId(l_EqtypeOrderParams.getProductId());
            l_ProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_ProductParams);

            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_EqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_EqtypeProductParams.setProductId(l_EqtypeOrderParams.getProductId());
            l_EqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_EqtypeProductParams);

            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.CASH_IN);

            EqTypeOrderUnitImpl l_impl = new EqTypeOrderUnitImplForTest(pm, l_eqtypeOrderUnitParams);
            l_service.validateCancelExecEnableExecute(l_request, l_impl);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    private class EqTypeAssetForTest implements EqTypeAsset
    {

        public long getAssetId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public double getAveragePrice()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public double getBookValue()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public double getManagementFee()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public double getManagementFeeTax()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public double getSetupFee()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public double getSetupFeeTax()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public double getLockedQuantity()
        {
            // TODO Auto-generated method stub
            return l_dbLockedQuantity;
        }

        public TaxTypeEnum getTaxType()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public AssetUnit[] getAssetUnits()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Product getProduct()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public ProductTypeEnum getProductType()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public double getQuantity()
        {
            // TODO Auto-generated method stub
            return l_dbQuantity;
        }

        public SubAccount getSubAccount()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Object getDataSourceObject()
        {
            // TODO Auto-generated method stub
            return null;
        }

    }

    private class EqTypeOrderUnitImplForTest extends EqTypeOrderUnitImpl
    {

        public EqTypeOrderUnitImplForTest(PersistenceManagerImpl arg0, EqtypeOrderUnitRow arg1)
        {
            super(arg0, arg1);
            // TODO Auto-generated constructor stub
        }

        public TradedProduct getTradedProduct()
        {
            return new TradedProductForTest();
        }

    }

    private class TradedProductForTest implements EqTypeTradedProduct
    {

        public long getTradedProductId()
        {
            // TODO Auto-generated method stub
            return 12345;
        }

        public Product getProduct()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Market getMarket()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public boolean isTradingSuspended()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public double getMarginRatio()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public Date getBaseDate()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Date getDailyDeliveryDate()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public boolean isCollateralizable()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public Institution getInstitution()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Object getDataSourceObject()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public double getLastClosingPrice()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public double getDailyStopHigh()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public double getDailyStopLow()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public double getStopHighPrice()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public double getStopLowPrice()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public boolean isMarginable()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean isShortable()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public Date getLastUpdatedTimestamp()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public double getTickValueUnit()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public double getTickValueUnit(double arg0)
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public boolean isValidPriceAsPerTickValue(double arg0)
        {
            // TODO Auto-generated method stub
            return false;
        }

        public Date getListedDate()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Date getUnlistedDate()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public boolean isListedCurrently()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean isMiniStockTradable()
        {
            // TODO Auto-generated method stub
            return false;
        }

    }

}
@
