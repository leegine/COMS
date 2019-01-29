head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.04.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoMarketRequestSenderServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP市場リクエスト送信サービス(WEB3IfoMarketRequestSenderServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/14 李強(中訊)  新規建注文送信       新規作成
Revesion History : 2004/06/16 李強(中訊)  返済注文送信         新規作成
Revesion History : 2004/06/16 李強(中訊)  取消注文送信         新規作成
Revesion History : 2004/06/16 李強(中訊)  訂正確定             新規作成
Revesion History : 2004/06/16 李強(中訊)  取消確定             新規作成
Revesion History : 2004/06/17 李強(中訊)  新規建訂正注文送信    新規作成
Revesion History : 2004/06/17 李強(中訊)  返済訂正注文送信      新規作成
Revesion History : 2006/07/06 徐宏偉 (中訊) 【先物オプション】仕様変更モデル511
Revesion History : 2007/01/25 徐大方 (中訊) 仕様変更588,595,597,598,602,603,607
*/

package webbroker3.ifo.marketadaptor;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MarketRequestSendResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TooLateException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.NewOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.SubAccountImpl;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoClosingContractSpecParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoClosingContractSpecRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.market.messages.DefaultIfoChangeOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.xbifo.market.messages.DefaultIfoChangeSettleContractOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.xbifo.market.messages.DefaultIfoOpenContractOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.xbifo.market.messages.DefaultIfoSettleContractOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.xbifo.market.messages.IfoOpenContractOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.xbifo.market.messages.IfoSettleContractOrderMarketRequestMessage;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3FrontOrderSystemCodeDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3SubmitMqTriggerDef;
import webbroker3.common.define.WEB3SubmitOrderRouteDivDef;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.CalendarRow;
import webbroker3.gentrade.data.OrderSwitchingParams;
import webbroker3.gentrade.data.OrderSwitchingRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.ifo.data.HostFotypeOrderAllDao;
import webbroker3.ifo.data.HostFotypeOrderAllParams;
import webbroker3.ifo.data.HostFotypeOrderAllRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mqgateway.WEB3MQMessageSpec;
import webbroker3.mqgateway.stdimpls.WEB3DefaultMQSendResultForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (先物OP市場リクエスト送信サービス )<BR>
 *
 * @@author 徐大方
 * @@version 1.0
 */
public class WEB3IfoMarketRequestSenderServiceImplTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(
             WEB3IfoMarketRequestSenderServiceImplTest.class);

    public WEB3IfoMarketRequestSenderServiceImplTest(String name)
    {
        super(name);
    }
    protected void setUp() throws Exception
    {
        super.setUp();
        TestDBUtility.deleteAllAndCommit(CalendarRow.TYPE);
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
        l_calendar.set(Calendar.YEAR,2007);
        l_calendar.set(Calendar.MONTH,2);
        l_calendar.set(Calendar.DAY_OF_MONTH,14);
        l_calendar.set(Calendar.HOUR_OF_DAY,15);
        l_calendar.set(Calendar.MINUTE,00);
        l_calendar.set(Calendar.SECOND,01);

        Timestamp l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_tsBizDate);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
            "getSystemTimestamp",
            new Class[] {},
            l_tsBizDate);
    }
    public void testSendCase1()
    {
        final String STR_METHOD_NAME = "testSendCase1()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        IfoOpenContractOrderMarketRequestMessage l_request = null;
        try
        {
            l_impl.send(l_request);
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            assertEquals("パラメータ値不正。", l_ex.getErrorInfo().getErrorMessage());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    public void testSendCase2()
    {
        final String STR_METHOD_NAME = "testSendCase2()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        
        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        try
        {
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        DefaultIfoOpenContractOrderMarketRequestMessage l_request =
            new DefaultIfoOpenContractOrderMarketRequestMessage(l_subAccount, l_lngOrderId, l_ifoOrderUnitParams);
        try
        {
            MarketRequestSendResult l_result = l_impl.send(l_request);
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    public void testSendCase3()
    {
        final String STR_METHOD_NAME = "testSendCase3()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        ProcessingResult l_processingResult =
            ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80006);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoMarketResponseReceiverCallbackServiceImpl",
            "process", new Class[] {NewOrderAcceptedMarketResponseMessage.class},
            l_processingResult);

        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();

        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        try
        {
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        DefaultIfoOpenContractOrderMarketRequestMessage l_request =
            new DefaultIfoOpenContractOrderMarketRequestMessage(l_subAccount, l_lngOrderId, l_ifoOrderUnitParams);
        try
        {
            MarketRequestSendResult l_result = l_impl.send(l_request);
            assertFalse(l_result.getProcessingResult().isSuccessfulResult());
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    public void testSendCase4()
    {
        final String STR_METHOD_NAME = "testSendCase4()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();

        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderConditionType("1");
        try
        {
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        DefaultIfoOpenContractOrderMarketRequestMessage l_request =
            new DefaultIfoOpenContractOrderMarketRequestMessage(l_subAccount, l_lngOrderId, l_ifoOrderUnitParams);
        try
        {
            MarketRequestSendResult l_result = l_impl.send(l_request);
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    public void testSendCase5()
    {
        final String STR_METHOD_NAME = "testSendCase5()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "insertOpenContractHostOrder", new Class[] {long.class},
            null);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrderMQDataCode", new Class[] {IfoOrderUnit.class},
            null);
        
        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        ProductParams l_productParams = TestDBUtility.getProductRow();
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
        IfoProductParams l_ifoProductParams = new IfoProductParams();
        
        l_hostFotypeOrderAllParams.setInstitutionCode("0D");
        l_hostFotypeOrderAllParams.setBranchCode("381");
        l_hostFotypeOrderAllParams.setOrderRequestNumber("000003006");
        l_hostFotypeOrderAllParams.setCorpCode("11111111111111101111");
        l_hostFotypeOrderAllParams.setStatus("0");
        l_hostFotypeOrderAllParams.setCancelDiv("0");
        l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
        l_hostFotypeOrderAllParams.setFrontOrderSystemCode("2");
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getHostFotypeOrderAll", new Class[] {IfoOrderUnit.class},
            l_hostFotypeOrderAllParams);
        
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setSubmitMarketTrigger("1");
        
        l_marketParams.setMarketId(1002);
        
        l_ifoProductParams.setProductId(1006169090018L);
        l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setUnderlyingProductCode("1");
        l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.FUTURES);
        l_ifoProductParams.setStrikePrice(11);
        l_ifoProductParams.setMonthOfDelivery("1111");
        l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        
        
        l_productParams.setProductId(1006169090018L);
        l_productParams.setProductType(ProductTypeEnum.IFO);

        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setReceivedDateTime(GtlUtils.getSystemTimestamp());
        l_ifoOrderUnitParams.setSubmitOrderRouteDiv("0");
        try
        {
            TestDBUtility.deleteAll(CalendarRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        DefaultIfoOpenContractOrderMarketRequestMessage l_request =
            new DefaultIfoOpenContractOrderMarketRequestMessage(l_subAccount, l_lngOrderId, l_ifoOrderUnitParams);
        try
        {
            MarketRequestSendResult l_result = l_impl.send(l_request);
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSendCase6()
    {
        final String STR_METHOD_NAME = "testSendCase6()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "insertOpenContractHostOrder", new Class[] {long.class},
            null);
        WEB3DefaultMQSendResultForMock l_mqSendResult = WEB3DefaultMQSendResultForMock.newSuccessResultInstance();
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mqgateway.stdimpls.WEB3MQGatewayServiceImpl",
            "send", new Class[] {WEB3MQMessageSpec.class},
            l_mqSendResult);
        
        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        ProductParams l_productParams = TestDBUtility.getProductRow();
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        
        
        
        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        l_orderSwitchingParams.setMarketCode("SP");
        l_orderSwitchingParams.setFrontOrderSystemCode("2");
        
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
        IfoProductParams l_ifoProductParams = new IfoProductParams();
        
        l_hostFotypeOrderAllParams.setInstitutionCode("0D");
        l_hostFotypeOrderAllParams.setBranchCode("381");
        l_hostFotypeOrderAllParams.setOrderRequestNumber("000003006");
        l_hostFotypeOrderAllParams.setCorpCode("11111111111111101111");
        l_hostFotypeOrderAllParams.setStatus("0");
        l_hostFotypeOrderAllParams.setCancelDiv("0");
        l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
        l_hostFotypeOrderAllParams.setFrontOrderSystemCode("2");
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getHostFotypeOrderAll", new Class[] {IfoOrderUnit.class},
            l_hostFotypeOrderAllParams);
        
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setSubmitMarketTrigger("1");
        
        l_marketParams.setMarketId(1002);
        
        l_ifoProductParams.setProductId(1006169090018L);
        l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setUnderlyingProductCode("1");
        l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.FUTURES);
        l_ifoProductParams.setStrikePrice(11);
        l_ifoProductParams.setMonthOfDelivery("1111");
        l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        
        
        l_productParams.setProductId(1006169090018L);
        l_productParams.setProductType(ProductTypeEnum.IFO);

        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setReceivedDateTime(GtlUtils.getSystemTimestamp());
        l_ifoOrderUnitParams.setSubmitOrderRouteDiv("0");
        try
        {
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
            TestDBUtility.deleteAll(OrderSwitchingRow.TYPE);
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        DefaultIfoOpenContractOrderMarketRequestMessage l_request =
            new DefaultIfoOpenContractOrderMarketRequestMessage(l_subAccount, l_lngOrderId, l_ifoOrderUnitParams);
        try
        {
            MarketRequestSendResult l_result = l_impl.send(l_request);
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSendCase7()
    {
        final String STR_METHOD_NAME = "testSendCase7()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "insertOpenContractHostOrder", new Class[] {long.class},
            null);
        WEB3DefaultMQSendResultForMock l_mqSendResult = WEB3DefaultMQSendResultForMock.newFailedResultInstance(new ErrorInfo());
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mqgateway.stdimpls.WEB3MQGatewayServiceImpl",
            "send", new Class[] {WEB3MQMessageSpec.class},
            l_mqSendResult);
        
        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        ProductParams l_productParams = TestDBUtility.getProductRow();
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        l_orderSwitchingParams.setInstitutionCode("0D");
        l_orderSwitchingParams.setProductType(ProductTypeEnum.IFO);
        l_orderSwitchingParams.setMarketCode("SP");
        l_orderSwitchingParams.setSubmitOrderRouteDiv("0");
        l_orderSwitchingParams.setFrontOrderSystemCode("1");
        l_orderSwitchingParams.setSubmitMqTrigger("1");
        
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
        IfoProductParams l_ifoProductParams = new IfoProductParams();
        
        l_hostFotypeOrderAllParams.setInstitutionCode("0D");
        l_hostFotypeOrderAllParams.setBranchCode("381");
        l_hostFotypeOrderAllParams.setOrderRequestNumber("000003006");
        l_hostFotypeOrderAllParams.setCorpCode("11111111111111101111");
        l_hostFotypeOrderAllParams.setStatus("0");
        l_hostFotypeOrderAllParams.setCancelDiv("0");
        l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
        l_hostFotypeOrderAllParams.setFrontOrderSystemCode("1");
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getHostFotypeOrderAll", new Class[] {IfoOrderUnit.class},
            l_hostFotypeOrderAllParams);
        
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setSubmitMarketTrigger("1");
        
        l_marketParams.setMarketId(1002);
        
        l_ifoProductParams.setProductId(1006169090018L);
        l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setUnderlyingProductCode("1");
        l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.FUTURES);
        l_ifoProductParams.setStrikePrice(11);
        l_ifoProductParams.setMonthOfDelivery("1111");
        l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        
        
        l_productParams.setProductId(1006169090018L);
        l_productParams.setProductType(ProductTypeEnum.IFO);

        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setReceivedDateTime(GtlUtils.getSystemTimestamp());
        l_ifoOrderUnitParams.setSubmitOrderRouteDiv("0");

        try
        {
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
            TestDBUtility.deleteAll(OrderSwitchingRow.TYPE);
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        DefaultIfoOpenContractOrderMarketRequestMessage l_request =
            new DefaultIfoOpenContractOrderMarketRequestMessage(l_subAccount, l_lngOrderId, l_ifoOrderUnitParams);
        try
        {
            MarketRequestSendResult l_result = l_impl.send(l_request);
            assertFalse(l_result.getProcessingResult().isSuccessfulResult());
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSendCase8()
    {
        final String STR_METHOD_NAME = "testSendCase8()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "insertOpenContractHostOrder", new Class[] {long.class},
            null);


        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        ProductParams l_productParams = TestDBUtility.getProductRow();
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
        IfoProductParams l_ifoProductParams = new IfoProductParams();
        
        l_hostFotypeOrderAllParams.setInstitutionCode("0D");
        l_hostFotypeOrderAllParams.setBranchCode("381");
        l_hostFotypeOrderAllParams.setOrderRequestNumber("000003006");
        l_hostFotypeOrderAllParams.setCorpCode("11111111111111101111");
        l_hostFotypeOrderAllParams.setStatus("0");
        l_hostFotypeOrderAllParams.setCancelDiv("0");
        l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
        l_hostFotypeOrderAllParams.setFrontOrderSystemCode("2");
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getHostFotypeOrderAll", new Class[] {IfoOrderUnit.class},
            l_hostFotypeOrderAllParams);
        
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setProductCode("12");
        
        l_marketParams.setMarketId(1002);
        
        l_ifoProductParams.setProductId(1006169090018L);
        l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setUnderlyingProductCode("1");
        l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.FUTURES);
        l_ifoProductParams.setStrikePrice(11);
        l_ifoProductParams.setMonthOfDelivery("1111");
        l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        
        
        l_productParams.setProductId(1006169090018L);
        l_productParams.setProductType(ProductTypeEnum.IFO);

        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setReceivedDateTime(GtlUtils.getSystemTimestamp());
        try
        {
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        DefaultIfoOpenContractOrderMarketRequestMessage l_request =
            new DefaultIfoOpenContractOrderMarketRequestMessage(l_subAccount, l_lngOrderId, l_ifoOrderUnitParams);
        try
        {
            MarketRequestSendResult l_result = l_impl.send(l_request);
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSendCase9()
    {
        final String STR_METHOD_NAME = "testSendCase9()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        IfoSettleContractOrderMarketRequestMessage l_request = null;
        try
        {
            l_impl.send(l_request);
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            assertEquals("パラメータ値不正。", l_ex.getErrorInfo().getErrorMessage());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    public void testSendCase10()
    {
        final String STR_METHOD_NAME = "testSendCase10()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();

        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        try
        {
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        IfoClosingContractSpecRow[] l_closingContractRows = new IfoClosingContractSpecParams[1];
        DefaultIfoSettleContractOrderMarketRequestMessage l_request =
            new DefaultIfoSettleContractOrderMarketRequestMessage(
                l_subAccount, l_lngOrderId, l_ifoOrderUnitParams, l_closingContractRows);
        try
        {
            MarketRequestSendResult l_result = l_impl.send(l_request);
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    public void testSendCase11()
    {
        final String STR_METHOD_NAME = "testSendCase11()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        ProcessingResult l_processingResult =
            ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80006);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoMarketResponseReceiverCallbackServiceImpl",
            "process", new Class[] {NewOrderAcceptedMarketResponseMessage.class},
            l_processingResult);

        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();

        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        try
        {
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        IfoClosingContractSpecRow[] l_closingContractRows = new IfoClosingContractSpecParams[1];
        DefaultIfoSettleContractOrderMarketRequestMessage l_request =
            new DefaultIfoSettleContractOrderMarketRequestMessage(
                l_subAccount, l_lngOrderId, l_ifoOrderUnitParams, l_closingContractRows);
        try
        {
            MarketRequestSendResult l_result = l_impl.send(l_request);
            assertFalse(l_result.getProcessingResult().isSuccessfulResult());
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    public void testSendCase12()
    {
        final String STR_METHOD_NAME = "testSendCase12()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();

        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderConditionType("1");
        try
        {
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        IfoClosingContractSpecRow[] l_closingContractRows = new IfoClosingContractSpecParams[1];
        DefaultIfoSettleContractOrderMarketRequestMessage l_request =
            new DefaultIfoSettleContractOrderMarketRequestMessage(
                l_subAccount, l_lngOrderId, l_ifoOrderUnitParams, l_closingContractRows);
        try
        {
            MarketRequestSendResult l_result = l_impl.send(l_request);
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    public void testSendCase13()
    {
        final String STR_METHOD_NAME = "testSendCase13()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
            "insertSettleContractHostOrder", new Class[] {long.class},
            null);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrderMQDataCode", new Class[] {IfoOrderUnit.class},
            null);
        
        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        ProductParams l_productParams = TestDBUtility.getProductRow();
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
        IfoProductParams l_ifoProductParams = new IfoProductParams();
        
        l_hostFotypeOrderAllParams.setInstitutionCode("0D");
        l_hostFotypeOrderAllParams.setBranchCode("381");
        l_hostFotypeOrderAllParams.setOrderRequestNumber("000003006");
        l_hostFotypeOrderAllParams.setCorpCode("11111111111111101111");
        l_hostFotypeOrderAllParams.setStatus("0");
        l_hostFotypeOrderAllParams.setCancelDiv("0");
        l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
        l_hostFotypeOrderAllParams.setFrontOrderSystemCode("2");
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getHostFotypeOrderAll", new Class[] {IfoOrderUnit.class},
            l_hostFotypeOrderAllParams);
        
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setSubmitMarketTrigger("1");
        
        l_marketParams.setMarketId(1002);
        
        l_ifoProductParams.setProductId(1006169090018L);
        l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setUnderlyingProductCode("1");
        l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.FUTURES);
        l_ifoProductParams.setStrikePrice(11);
        l_ifoProductParams.setMonthOfDelivery("1111");
        l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        
        
        l_productParams.setProductId(1006169090018L);
        l_productParams.setProductType(ProductTypeEnum.IFO);

        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setReceivedDateTime(GtlUtils.getSystemTimestamp());
        l_ifoOrderUnitParams.setSubmitOrderRouteDiv("0");
        try
        {
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        IfoClosingContractSpecRow[] l_closingContractRows = new IfoClosingContractSpecParams[1];
        DefaultIfoSettleContractOrderMarketRequestMessage l_request =
            new DefaultIfoSettleContractOrderMarketRequestMessage(
                l_subAccount, l_lngOrderId, l_ifoOrderUnitParams, l_closingContractRows);
        try
        {
            MarketRequestSendResult l_result = l_impl.send(l_request);
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSendCase14()
    {
        final String STR_METHOD_NAME = "testSendCase14()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
            "insertSettleContractHostOrder", new Class[] {long.class},
            null);
        WEB3DefaultMQSendResultForMock l_mqSendResult = WEB3DefaultMQSendResultForMock.newSuccessResultInstance();
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mqgateway.stdimpls.WEB3MQGatewayServiceImpl",
            "send", new Class[] {WEB3MQMessageSpec.class},
            l_mqSendResult);
        
        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        ProductParams l_productParams = TestDBUtility.getProductRow();
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
        IfoProductParams l_ifoProductParams = new IfoProductParams();
        
        l_hostFotypeOrderAllParams.setInstitutionCode("0D");
        l_hostFotypeOrderAllParams.setBranchCode("381");
        l_hostFotypeOrderAllParams.setOrderRequestNumber("000003006");
        l_hostFotypeOrderAllParams.setCorpCode("11111111111111101111");
        l_hostFotypeOrderAllParams.setStatus("0");
        l_hostFotypeOrderAllParams.setCancelDiv("0");
        l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
        l_hostFotypeOrderAllParams.setFrontOrderSystemCode("2");
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getHostFotypeOrderAll", new Class[] {IfoOrderUnit.class},
            l_hostFotypeOrderAllParams);
        
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setSubmitMarketTrigger("1");
        
        l_marketParams.setMarketId(1002);
        
        l_ifoProductParams.setProductId(1006169090018L);
        l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setUnderlyingProductCode("1");
        l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.FUTURES);
        l_ifoProductParams.setStrikePrice(11);
        l_ifoProductParams.setMonthOfDelivery("1111");
        l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        
        
        l_productParams.setProductId(1006169090018L);
        l_productParams.setProductType(ProductTypeEnum.IFO);

        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setReceivedDateTime(GtlUtils.getSystemTimestamp());
        l_ifoOrderUnitParams.setSubmitOrderRouteDiv("0");
        try
        {
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        IfoClosingContractSpecRow[] l_closingContractRows = new IfoClosingContractSpecParams[1];
        DefaultIfoSettleContractOrderMarketRequestMessage l_request =
            new DefaultIfoSettleContractOrderMarketRequestMessage(
                l_subAccount, l_lngOrderId, l_ifoOrderUnitParams, l_closingContractRows);
        try
        {
            MarketRequestSendResult l_result = l_impl.send(l_request);
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSendCase15()
    {
        final String STR_METHOD_NAME = "testSendCase15()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
            "insertSettleContractHostOrder", new Class[] {long.class},
            null);
        WEB3DefaultMQSendResultForMock l_mqSendResult = WEB3DefaultMQSendResultForMock.newFailedResultInstance(new ErrorInfo());
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mqgateway.stdimpls.WEB3MQGatewayServiceImpl",
            "send", new Class[] {WEB3MQMessageSpec.class},
            l_mqSendResult);
        
        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        ProductParams l_productParams = TestDBUtility.getProductRow();
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
        IfoProductParams l_ifoProductParams = new IfoProductParams();
        
        l_hostFotypeOrderAllParams.setInstitutionCode("0D");
        l_hostFotypeOrderAllParams.setBranchCode("381");
        l_hostFotypeOrderAllParams.setOrderRequestNumber("000003006");
        l_hostFotypeOrderAllParams.setCorpCode("11111111111111101111");
        l_hostFotypeOrderAllParams.setStatus("0");
        l_hostFotypeOrderAllParams.setCancelDiv("0");
        l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
        l_hostFotypeOrderAllParams.setFrontOrderSystemCode("2");
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getHostFotypeOrderAll", new Class[] {IfoOrderUnit.class},
            l_hostFotypeOrderAllParams);
        
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setSubmitMarketTrigger("1");
        
        l_marketParams.setMarketId(1002);
        
        l_ifoProductParams.setProductId(1006169090018L);
        l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setUnderlyingProductCode("1");
        l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.FUTURES);
        l_ifoProductParams.setStrikePrice(11);
        l_ifoProductParams.setMonthOfDelivery("1111");
        l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        
        
        l_productParams.setProductId(1006169090018L);
        l_productParams.setProductType(ProductTypeEnum.IFO);

        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setReceivedDateTime(GtlUtils.getSystemTimestamp());
        l_ifoOrderUnitParams.setSubmitOrderRouteDiv("0");
        try
        {
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        IfoClosingContractSpecRow[] l_closingContractRows = new IfoClosingContractSpecParams[1];
        DefaultIfoSettleContractOrderMarketRequestMessage l_request =
            new DefaultIfoSettleContractOrderMarketRequestMessage(
                l_subAccount, l_lngOrderId, l_ifoOrderUnitParams, l_closingContractRows);
        try
        {
            MarketRequestSendResult l_result = l_impl.send(l_request);
            assertFalse(l_result.getProcessingResult().isSuccessfulResult());
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSendCase16()
    {
        final String STR_METHOD_NAME = "testSendCase16()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
            "insertSettleContractHostOrder", new Class[] {long.class},
            null);


        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        ProductParams l_productParams = TestDBUtility.getProductRow();
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
        IfoProductParams l_ifoProductParams = new IfoProductParams();
        
        l_hostFotypeOrderAllParams.setInstitutionCode("0D");
        l_hostFotypeOrderAllParams.setBranchCode("381");
        l_hostFotypeOrderAllParams.setOrderRequestNumber("000003006");
        l_hostFotypeOrderAllParams.setCorpCode("11111111111111101111");
        l_hostFotypeOrderAllParams.setStatus("0");
        l_hostFotypeOrderAllParams.setCancelDiv("0");
        l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
        l_hostFotypeOrderAllParams.setFrontOrderSystemCode("2");
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getHostFotypeOrderAll", new Class[] {IfoOrderUnit.class},
            l_hostFotypeOrderAllParams);
        
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setProductCode("12");
        
        l_marketParams.setMarketId(1002);
        
        l_ifoProductParams.setProductId(1006169090018L);
        l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setUnderlyingProductCode("1");
        l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.FUTURES);
        l_ifoProductParams.setStrikePrice(11);
        l_ifoProductParams.setMonthOfDelivery("1111");
        l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        
        
        l_productParams.setProductId(1006169090018L);
        l_productParams.setProductType(ProductTypeEnum.IFO);

        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setReceivedDateTime(GtlUtils.getSystemTimestamp());
        try
        {
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        IfoClosingContractSpecRow[] l_closingContractRows = new IfoClosingContractSpecParams[1];
        DefaultIfoSettleContractOrderMarketRequestMessage l_request =
            new DefaultIfoSettleContractOrderMarketRequestMessage(
                l_subAccount, l_lngOrderId, l_ifoOrderUnitParams, l_closingContractRows);
        try
        {
            MarketRequestSendResult l_result = l_impl.send(l_request);
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSendCase17()
    {
        final String STR_METHOD_NAME = "testSendCase17()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        DefaultIfoChangeOrderMarketRequestMessage l_request = null;
        try
        {
            l_impl.send(l_request, true);
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            assertEquals("パラメータ値不正。", l_ex.getErrorInfo().getErrorMessage());
        }
        catch (TooLateException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSendCase18()
    {
        final String STR_METHOD_NAME = "testSendCase18()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();

        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        try
        {
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        DefaultIfoChangeOrderMarketRequestMessage l_request =
            new DefaultIfoChangeOrderMarketRequestMessage(l_subAccount, l_lngOrderId, l_ifoOrderUnitParams);
        try
        {
            MarketRequestSendResult l_result = l_impl.send(l_request, true);
            assertFalse(l_result.getProcessingResult().isSuccessfulResult());
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (TooLateException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSendCase19()
    {
        final String STR_METHOD_NAME = "testSendCase19()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
        
        l_hostFotypeOrderAllParams.setInstitutionCode("0D");
        l_hostFotypeOrderAllParams.setBranchCode("381");
        l_hostFotypeOrderAllParams.setOrderRequestNumber("000003006");
        l_hostFotypeOrderAllParams.setCorpCode("11111111111111101111");
        l_hostFotypeOrderAllParams.setStatus("0");
        l_hostFotypeOrderAllParams.setCancelDiv("0");
        l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
        l_hostFotypeOrderAllParams.setFrontOrderSystemCode("2");
        l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("1");
        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRev("01");
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
        l_ifoOrderUnitParams.setConfirmedQuantity(222);
        l_ifoOrderUnitParams.setLastOrderActionSerialNo(1);
        l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
        l_ifoOrderUnitParams.setQuantity(102);
        try
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        DefaultIfoChangeOrderMarketRequestMessage l_request =
            new DefaultIfoChangeOrderMarketRequestMessage(l_subAccount, l_lngOrderId, l_ifoOrderUnitParams);
        try
        {
            MarketRequestSendResult l_result = l_impl.send(l_request, true);
            List l_lstRows = HostFotypeOrderAllDao.findRowsByStatusSubmitOrderRouteDiv("0", "1");
            HostFotypeOrderAllRow l_row = (HostFotypeOrderAllRow)l_lstRows.get(0);
            assertEquals(1, l_row.getOrderActionSerialNo());
            assertEquals("1", l_row.getSubmitOrderRouteDiv());
            assertEquals("102", WEB3StringTypeUtility.formatNumber(l_row.getBuyOrderQuantity()));
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_row.getSellOrderQuantity()));
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_row.getLimitPrice()));
            //次期デリバ対応 執行条件の説明を変更
            //assertEquals("1", l_row.getExecutionCondition());
            System.out.println("getLastUpdatedTimestampIsSet=" + l_row.getLastUpdatedTimestamp());
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (TooLateException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSendCase19b()
    {
        final String STR_METHOD_NAME = "testSendCase19b()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
        
        l_hostFotypeOrderAllParams.setInstitutionCode("0D");
        l_hostFotypeOrderAllParams.setBranchCode("381");
        l_hostFotypeOrderAllParams.setOrderRequestNumber("000003006");
        l_hostFotypeOrderAllParams.setCorpCode("11111111111111101111");
        l_hostFotypeOrderAllParams.setStatus("0");
        l_hostFotypeOrderAllParams.setCancelDiv("0");
        l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
        l_hostFotypeOrderAllParams.setFrontOrderSystemCode("2");
        l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("1");
        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRev("01");
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN);
        l_ifoOrderUnitParams.setConfirmedQuantity(222);
        l_ifoOrderUnitParams.setLastOrderActionSerialNo(1);
        l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
        l_ifoOrderUnitParams.setQuantity(102);
        try
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        DefaultIfoChangeOrderMarketRequestMessage l_request =
            new DefaultIfoChangeOrderMarketRequestMessage(l_subAccount, l_lngOrderId, l_ifoOrderUnitParams);
        try
        {
            MarketRequestSendResult l_result = l_impl.send(l_request, true);
            List l_lstRows = HostFotypeOrderAllDao.findRowsByStatusSubmitOrderRouteDiv("0", "1");
            HostFotypeOrderAllRow l_row = (HostFotypeOrderAllRow)l_lstRows.get(0);
            assertEquals(1, l_row.getOrderActionSerialNo());
            assertEquals("1", l_row.getSubmitOrderRouteDiv());
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_row.getBuyOrderQuantity()));
            assertEquals("102", WEB3StringTypeUtility.formatNumber(l_row.getSellOrderQuantity()));
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_row.getLimitPrice()));
            //次期デリバ対応 執行条件の説明を変更
            //assertEquals("1", l_row.getExecutionCondition());
            System.out.println("getLastUpdatedTimestampIsSet=" + l_row.getLastUpdatedTimestamp());
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (TooLateException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSendCase20()
    {
        final String STR_METHOD_NAME = "testSendCase20()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
        
        l_hostFotypeOrderAllParams.setInstitutionCode("0D");
        l_hostFotypeOrderAllParams.setBranchCode("381");
        l_hostFotypeOrderAllParams.setOrderRequestNumber("000003006");
        l_hostFotypeOrderAllParams.setCorpCode("11111111111111101111");
        l_hostFotypeOrderAllParams.setStatus("0");
        l_hostFotypeOrderAllParams.setCancelDiv("0");
        l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
        l_hostFotypeOrderAllParams.setFrontOrderSystemCode("2");
        l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("1");
        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRev("01");
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
        l_ifoOrderUnitParams.setConfirmedQuantity(222);
        l_ifoOrderUnitParams.setLastOrderActionSerialNo(1);
        l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
        l_ifoOrderUnitParams.setOrderConditionType("1");
        l_ifoOrderUnitParams.setRequestType("2");
        try
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        DefaultIfoChangeOrderMarketRequestMessage l_request =
            new DefaultIfoChangeOrderMarketRequestMessage(l_subAccount, l_lngOrderId, l_ifoOrderUnitParams);
        try
        {
            MarketRequestSendResult l_result = l_impl.send(l_request, true);
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (TooLateException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSendCase21()
    {
        final String STR_METHOD_NAME = "testSendCase21()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "isNotifyEndOrder", new Class[] {OrderUnit.class},
            Boolean.TRUE);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getCorpCode", new Class[] {IfoOrderUnit.class},
            "11111111111111101111");
        
        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
        IfoProductParams l_ifoProductParams = new IfoProductParams();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setSubmitMarketTrigger("0");
        
        l_hostFotypeOrderAllParams.setInstitutionCode("0D");
        l_hostFotypeOrderAllParams.setBranchCode("381");
        l_hostFotypeOrderAllParams.setOrderRequestNumber("000003006");
        l_hostFotypeOrderAllParams.setCorpCode("11111111111111101111");
        l_hostFotypeOrderAllParams.setStatus("0");
        l_hostFotypeOrderAllParams.setCancelDiv("0");
        l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
        l_hostFotypeOrderAllParams.setFrontOrderSystemCode("2");
        l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("1");
        
        l_mainAccountParams.setAccountId(101001010010L);
        
        l_ifoProductParams.setProductId(1006169090018L);
        l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setUnderlyingProductCode("1");
        l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.FUTURES);
        l_ifoProductParams.setStrikePrice(11);
        l_ifoProductParams.setMonthOfDelivery("200702");
        l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setProductCode("112");
        l_ifoProductParams.setSplitType("4");
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRev("01");
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
        l_ifoOrderUnitParams.setConfirmedQuantity(222);
        l_ifoOrderUnitParams.setLastOrderActionSerialNo(1);
        l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
        l_ifoOrderUnitParams.setOrderConditionType("1");
        l_ifoOrderUnitParams.setRequestType("2");
        l_ifoOrderUnitParams.setConfirmedExecConditionType(IfoOrderExecutionConditionType.AT_MARKET_OPEN);
        try
        {
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        DefaultIfoChangeOrderMarketRequestMessage l_request =
            new DefaultIfoChangeOrderMarketRequestMessage(l_subAccount, l_lngOrderId, l_ifoOrderUnitParams);
        try
        {
            MarketRequestSendResult l_result = l_impl.send(l_request, false);
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (TooLateException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSendCase22()
    {
        final String STR_METHOD_NAME = "testSendCase22()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrderMQDataCode", new Class[] {IfoOrderUnit.class},
            null);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "isNotifyEndOrder", new Class[] {OrderUnit.class},
            Boolean.FALSE);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getFrontOrderExchangeCode", new Class[] {String.class},
            "7");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getFrontOrderSystemCode", new Class[] {String.class},
            "8");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getCorpCode", new Class[] {IfoOrderUnit.class},
            "33381600000300601999");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrgCorpCode", new Class[] {IfoOrderUnit.class},
            "3338160000030062999");
        
        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
        IfoProductParams l_ifoProductParams = new IfoProductParams();
        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setSubmitMarketTrigger("0");
        
        l_ifoProductParams.setProductId(1006169090018L);
        l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setUnderlyingProductCode("1");
        l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.FUTURES);
        l_ifoProductParams.setStrikePrice(11);
        l_ifoProductParams.setMonthOfDelivery("200702");
        l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setProductCode("112");
        l_ifoProductParams.setSplitType("4");
        
        l_hostFotypeOrderAllParams.setInstitutionCode("0D");
        l_hostFotypeOrderAllParams.setBranchCode("381");
        l_hostFotypeOrderAllParams.setOrderRequestNumber("000003006");
        l_hostFotypeOrderAllParams.setCorpCode("11111111111111101111");
        l_hostFotypeOrderAllParams.setStatus("0");
        l_hostFotypeOrderAllParams.setCancelDiv("0");
        l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
        l_hostFotypeOrderAllParams.setFrontOrderSystemCode("2");
        l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("1");
        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRev("01");
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
        l_ifoOrderUnitParams.setConfirmedQuantity(222);
        l_ifoOrderUnitParams.setLastOrderActionSerialNo(1);
        l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
        l_ifoOrderUnitParams.setOrderConditionType("1");
        l_ifoOrderUnitParams.setRequestType("2");
        l_ifoOrderUnitParams.setConfirmedExecConditionType(IfoOrderExecutionConditionType.AT_MARKET_OPEN);
        l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.AT_MARKET_OPEN);
        SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
        String l_strCreateDate = l_format.format(GtlUtils.getSystemTimestamp());
        l_ifoOrderUnitParams.setBizDate(l_strCreateDate);
        l_ifoOrderUnitParams.setSonarTraderCode("2");
        l_ifoOrderUnitParams.setSonarMarketCode("6");
        l_ifoOrderUnitParams.setProductId(1006169090018L);
        l_ifoOrderUnitParams.setConfirmedPrice(new Double(333));
        try
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        DefaultIfoChangeOrderMarketRequestMessage l_request =
            new DefaultIfoChangeOrderMarketRequestMessage(l_subAccount, l_lngOrderId, l_ifoOrderUnitParams);
        try
        {
            MarketRequestSendResult l_result = l_impl.send(l_request, false);
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());
            List l_lisRows = HostFotypeOrderAllDao.findRowsByStatusSubmitOrderRouteDiv("0", "1");
            HostFotypeOrderAllRow l_row = (HostFotypeOrderAllRow)l_lisRows.get(0);
            System.out.println(l_row.getRequestCode());
            assertEquals("EI804", l_row.getRequestCode());
            System.out.println(l_row.getAccountId());
            assertEquals(101001010010L, l_row.getAccountId());
            System.out.println(l_row.getInstitutionCode());
            assertEquals("0D", l_row.getInstitutionCode());
            System.out.println(l_row.getBranchCode());
            assertEquals("381", l_row.getBranchCode());
            System.out.println(l_row.getAccountCode());
            assertEquals("2512246", l_row.getAccountCode());
            System.out.println(l_row.getTraderCode());
            assertEquals("2", l_row.getTraderCode());
            System.out.println(l_row.getReceivedDateTimeDiv());
            assertEquals("0", l_row.getReceivedDateTimeDiv());
            System.out.println(l_row.getOrderRequestNumber());
            assertEquals("000003006", l_row.getOrderRequestNumber());
            System.out.println(l_row.getSonarMarketCode());
            assertEquals("6", l_row.getSonarMarketCode());
            //次期デリバ対応 執行条件の説明を変更
            //System.out.println(l_row.getProductCode());
            //assertEquals("112", l_row.getProductCode());
            System.out.println(l_row.getReceivedDateTime());
            assertEquals(WEB3DateUtility.formatDate(
                GtlUtils.getSystemTimestamp(), "yyyyMMdd"),
                WEB3DateUtility.formatDate(l_row.getReceivedDateTime(), "yyyyMMdd"));
            System.out.println(l_row.getOrderActionSerialNo());
            assertEquals(1, l_row.getOrderActionSerialNo());
            System.out.println(l_row.getSubmitOrderRouteDiv());
            assertEquals("1", l_row.getSubmitOrderRouteDiv());
            System.out.println(l_row.getTargetProductCode());
            assertEquals("1", l_row.getTargetProductCode());
            System.out.println(l_row.getDeliveryMonthYyyy());
            assertEquals("2007", l_row.getDeliveryMonthYyyy());
            System.out.println(l_row.getDeliveryMonthMm());
            assertEquals("02", l_row.getDeliveryMonthMm());
            System.out.println(l_row.getFutureOptionProductType());
            assertEquals("F", l_row.getFutureOptionProductType());
            System.out.println(l_row.getStrikePrice());
            assertEquals("11", WEB3StringTypeUtility.formatNumber(l_row.getStrikePrice()));
            System.out.println(l_row.getSplitType());
            assertEquals("4", l_row.getSplitType());
            System.out.println(l_row.getSellOrderQuantity());
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_row.getSellOrderQuantity()));
            System.out.println(l_row.getBuyOrderQuantity());
            assertEquals("222", WEB3StringTypeUtility.formatNumber(l_row.getBuyOrderQuantity()));
            System.out.println(l_row.getLimitPrice());
            assertEquals("333", WEB3StringTypeUtility.formatNumber(l_row.getLimitPrice()));
            //次期デリバ対応 執行条件の説明を変更
            //System.out.println(l_row.getExecutionCondition());
            //assertEquals("3", l_row.getExecutionCondition());
            System.out.println(l_row.getStopOrderPrice());
            assertTrue(l_row.getStopOrderPriceIsNull());
            System.out.println(l_row.getWLimitPrice());
            assertTrue(l_row.getWLimitPriceIsNull());
            System.out.println(l_row.getTransactionType());
            assertEquals(null, l_row.getTransactionType());
            System.out.println(l_row.getTicketNumber());
            assertEquals(null, l_row.getTicketNumber());
            System.out.println(l_row.getContractCheck());
            assertEquals(null, l_row.getContractCheck());
            System.out.println(l_row.getOrderChanel());
            assertEquals(null, l_row.getOrderChanel());
            System.out.println(l_row.getCommisionNumber());
            assertEquals(null, l_row.getCommisionNumber());
            System.out.println(l_row.getCommisionBranchNumber());
            assertEquals(null, l_row.getCommisionBranchNumber());
            System.out.println(l_row.getCommisionProductCode());
            assertEquals(null, l_row.getCommisionProductCode());
            System.out.println(l_row.getChangeQuantity());
            assertEquals("100", WEB3StringTypeUtility.formatNumber(l_row.getChangeQuantity()));
            System.out.println(l_row.getChangeLimitPrice());
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_row.getChangeLimitPrice()));
            //次期デリバ対応 説明を変更
            //System.out.println(l_row.getChangeExecutionCondition());
            //assertEquals("3", l_row.getChangeExecutionCondition());
            System.out.println(l_row.getChangeStopOrderPrice());
            assertTrue(l_row.getChangeStopOrderPriceIsNull());
            System.out.println(l_row.getChangeWLimitPrice());
            assertTrue(l_row.getChangeWLimitPriceIsNull());
            System.out.println(l_row.getCancelDiv());
            assertEquals("0", l_row.getCancelDiv());
            System.out.println(l_row.getFrontOrderExchangeCode());
            assertEquals("7", l_row.getFrontOrderExchangeCode());
            System.out.println(l_row.getFrontOrderSystemCode());
            assertEquals("8", l_row.getFrontOrderSystemCode());
            System.out.println(l_row.getFrontOrderTradeCode());
            assertEquals("1", l_row.getFrontOrderTradeCode());
            System.out.println(l_row.getTradeauditCode());
            assertEquals(null, l_row.getTradeauditCode());
            System.out.println(l_row.getCorpCode());
            assertEquals("33381600000300601999", l_row.getCorpCode());
            System.out.println(l_row.getOrgCorpCode());
            assertEquals("3338160000030062999", l_row.getOrgCorpCode());
            System.out.println(l_row.getVirtualServerNumberJsoes());
            assertEquals(null, l_row.getVirtualServerNumberJsoes());
            System.out.println(l_row.getMarketOrderNumber());
            assertTrue(l_row.getMarketOrderNumberIsNull());
            System.out.println(l_row.getAmgSendTime());
            assertEquals(null, l_row.getAmgSendTime());
            System.out.println(l_row.getAmgAckTime());
            assertEquals(null, l_row.getAmgAckTime());
            System.out.println(l_row.getMarketAckTime());
            assertEquals(null, l_row.getMarketAckTime());
            System.out.println(l_row.getAllOrderChangeDiv());
            assertEquals("0", l_row.getAllOrderChangeDiv());
            System.out.println(l_row.getMarketCloseFlag());
            assertEquals("0", l_row.getMarketCloseFlag());
            System.out.println(l_row.getStatus());
            assertEquals("0", l_row.getStatus());
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (TooLateException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSendCase23()
    {
        final String STR_METHOD_NAME = "testSendCase23()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrderMQDataCode", new Class[] {IfoOrderUnit.class},
            null);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "isNotifyEndOrder", new Class[] {OrderUnit.class},
            Boolean.FALSE);
        
        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
        IfoProductParams l_ifoProductParams = new IfoProductParams();
        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setSubmitMarketTrigger("0");
        
        l_ifoProductParams.setProductId(1006169090018L);
        l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setUnderlyingProductCode("1");
        l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.CALL_OPTIONS);
        l_ifoProductParams.setStrikePrice(11);
        l_ifoProductParams.setMonthOfDelivery("200702");
        l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setProductCode("112");
        l_ifoProductParams.setSplitType("4");
        
        l_hostFotypeOrderAllParams.setInstitutionCode("0D");
        l_hostFotypeOrderAllParams.setBranchCode("381");
        l_hostFotypeOrderAllParams.setOrderRequestNumber("000003006");
        l_hostFotypeOrderAllParams.setCorpCode("11111111111111101111");
        l_hostFotypeOrderAllParams.setStatus("0");
        l_hostFotypeOrderAllParams.setCancelDiv("0");
        l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
        l_hostFotypeOrderAllParams.setFrontOrderSystemCode("2");
        l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("1");
        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRev("01");
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
        l_ifoOrderUnitParams.setConfirmedQuantity(222);
        l_ifoOrderUnitParams.setLastOrderActionSerialNo(1);
        l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
        l_ifoOrderUnitParams.setOrderConditionType("1");
        l_ifoOrderUnitParams.setRequestType("2");
        l_ifoOrderUnitParams.setConfirmedExecConditionType(IfoOrderExecutionConditionType.AT_MARKET_OPEN);
        l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.AT_MARKET_CLOSE);
        l_ifoOrderUnitParams.setBizDate("20020202");
        l_ifoOrderUnitParams.setSonarTradedCode("2");
        l_ifoOrderUnitParams.setSonarMarketCode("6");
        l_ifoOrderUnitParams.setProductId(1006169090018L);
        l_ifoOrderUnitParams.setConfirmedPrice(new Double(333));
        l_ifoOrderUnitParams.setFutureOptionDiv("2");
        try
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        DefaultIfoChangeOrderMarketRequestMessage l_request =
            new DefaultIfoChangeOrderMarketRequestMessage(l_subAccount, l_lngOrderId, l_ifoOrderUnitParams);
        try
        {
            MarketRequestSendResult l_result = l_impl.send(l_request, false);
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());
            List l_lisRows = HostFotypeOrderAllDao.findRowsByStatusSubmitOrderRouteDiv("0", "1");
            HostFotypeOrderAllRow l_row = (HostFotypeOrderAllRow)l_lisRows.get(0);
            System.out.println(l_row.getRequestCode());
            assertEquals("EI802", l_row.getRequestCode());
            System.out.println(l_row.getReceivedDateTimeDiv());
            assertEquals("1", l_row.getReceivedDateTimeDiv());
                     System.out.println(l_row.getFutureOptionProductType());
            assertEquals("C", l_row.getFutureOptionProductType());
            //次期デリバ対応 執行条件の説明を変更
            //System.out.println(l_row.getChangeExecutionCondition());
            //assertEquals("4", l_row.getChangeExecutionCondition());
            System.out.println(l_row.getBuyOrderQuantity());
            assertEquals("222", WEB3StringTypeUtility.formatNumber(l_row.getBuyOrderQuantity()));
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (TooLateException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSendCase24()
    {
        final String STR_METHOD_NAME = "testSendCase24()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrderMQDataCode", new Class[] {IfoOrderUnit.class},
            null);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "isNotifyEndOrder", new Class[] {OrderUnit.class},
            Boolean.FALSE);
        
        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
        IfoProductParams l_ifoProductParams = new IfoProductParams();
        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setSubmitMarketTrigger("0");
        
        l_ifoProductParams.setProductId(1006169090018L);
        l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setUnderlyingProductCode("1");
        l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
        l_ifoProductParams.setStrikePrice(11);
        l_ifoProductParams.setMonthOfDelivery("200702");
        l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setProductCode("112");
        l_ifoProductParams.setSplitType("4");
        
        l_hostFotypeOrderAllParams.setInstitutionCode("0D");
        l_hostFotypeOrderAllParams.setBranchCode("381");
        l_hostFotypeOrderAllParams.setOrderRequestNumber("000003006");
        l_hostFotypeOrderAllParams.setCorpCode("11111111111111101111");
        l_hostFotypeOrderAllParams.setStatus("0");
        l_hostFotypeOrderAllParams.setCancelDiv("0");
        l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
        l_hostFotypeOrderAllParams.setFrontOrderSystemCode("2");
        l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("1");
        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRev("01");
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN);
        l_ifoOrderUnitParams.setConfirmedQuantity(222);
        l_ifoOrderUnitParams.setLastOrderActionSerialNo(1);
        l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
        l_ifoOrderUnitParams.setOrderConditionType("1");
        l_ifoOrderUnitParams.setRequestType("2");
        l_ifoOrderUnitParams.setConfirmedExecConditionType(IfoOrderExecutionConditionType.AT_MARKET_OPEN);
        l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.AT_MARKET_CLOSE);
        l_ifoOrderUnitParams.setBizDate("20020202");
        l_ifoOrderUnitParams.setSonarTradedCode("2");
        l_ifoOrderUnitParams.setSonarMarketCode("6");
        l_ifoOrderUnitParams.setProductId(1006169090018L);
        l_ifoOrderUnitParams.setConfirmedPrice(new Double(333));
        l_ifoOrderUnitParams.setFutureOptionDiv("2");
        try
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        DefaultIfoChangeOrderMarketRequestMessage l_request =
            new DefaultIfoChangeOrderMarketRequestMessage(l_subAccount, l_lngOrderId, l_ifoOrderUnitParams);
        try
        {
            MarketRequestSendResult l_result = l_impl.send(l_request, false);
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());
            List l_lisRows = HostFotypeOrderAllDao.findRowsByStatusSubmitOrderRouteDiv("0", "1");
            HostFotypeOrderAllRow l_row = (HostFotypeOrderAllRow)l_lisRows.get(0);
            assertEquals("P", l_row.getFutureOptionProductType());
            assertEquals("222", WEB3StringTypeUtility.formatNumber(l_row.getSellOrderQuantity()));
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (TooLateException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSendCase25()
    {
        final String STR_METHOD_NAME = "testSendCase25()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrderMQDataCode", new Class[] {IfoOrderUnit.class},
            null);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "isNotifyEndOrder", new Class[] {OrderUnit.class},
            Boolean.FALSE);
        
        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
        IfoProductParams l_ifoProductParams = new IfoProductParams();
        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setSubmitMarketTrigger("0");
        
        l_ifoProductParams.setProductId(1006169090018L);
        l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setUnderlyingProductCode("1");
        l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
        l_ifoProductParams.setStrikePrice(11);
        l_ifoProductParams.setMonthOfDelivery("200702");
        l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setProductCode("112");
        l_ifoProductParams.setSplitType("4");
        
        l_hostFotypeOrderAllParams.setInstitutionCode("0D");
        l_hostFotypeOrderAllParams.setBranchCode("381");
        l_hostFotypeOrderAllParams.setOrderRequestNumber("000003006");
        l_hostFotypeOrderAllParams.setCorpCode("11111111111111101111");
        l_hostFotypeOrderAllParams.setStatus("0");
        l_hostFotypeOrderAllParams.setCancelDiv("0");
        l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
        l_hostFotypeOrderAllParams.setFrontOrderSystemCode("2");
        l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("1");
        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRev("01");
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN);
        l_ifoOrderUnitParams.setConfirmedQuantity(222);
        l_ifoOrderUnitParams.setLastOrderActionSerialNo(1);
        l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
        l_ifoOrderUnitParams.setOrderConditionType("1");
        l_ifoOrderUnitParams.setRequestType("2");
        l_ifoOrderUnitParams.setConfirmedExecConditionType(IfoOrderExecutionConditionType.AT_MARKET_OPEN);
        l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.AT_MARKET_CLOSE);
        l_ifoOrderUnitParams.setBizDate("20020202");
        l_ifoOrderUnitParams.setSonarTradedCode("2");
        l_ifoOrderUnitParams.setSonarMarketCode("6");
        l_ifoOrderUnitParams.setProductId(1006169090018L);
        l_ifoOrderUnitParams.setConfirmedPrice(new Double(333));
        l_ifoOrderUnitParams.setFutureOptionDiv("2");
        try
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        DefaultIfoChangeOrderMarketRequestMessage l_request =
            new DefaultIfoChangeOrderMarketRequestMessage(l_subAccount, l_lngOrderId, l_ifoOrderUnitParams);
        try
        {
            MarketRequestSendResult l_result = l_impl.send(l_request, false);
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());
            List l_lisRows = HostFotypeOrderAllDao.findRowsByStatusSubmitOrderRouteDiv("0", "1");
            HostFotypeOrderAllRow l_row = (HostFotypeOrderAllRow)l_lisRows.get(0);
            assertEquals("222", WEB3StringTypeUtility.formatNumber(l_row.getSellOrderQuantity()));
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (TooLateException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSendCase26()
    {
        final String STR_METHOD_NAME = "testSendCase26()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrderMQDataCode", new Class[] {IfoOrderUnit.class},
            "321");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "isNotifyEndOrder", new Class[] {OrderUnit.class},
            Boolean.FALSE);
        WEB3DefaultMQSendResultForMock l_defaultMQSendResult = WEB3DefaultMQSendResultForMock.newSuccessResultInstance();
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mqgateway.stdimpls.WEB3MQGatewayServiceImpl",
            "send", new Class[] {WEB3MQMessageSpec.class},
            l_defaultMQSendResult);
        
        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
        IfoProductParams l_ifoProductParams = new IfoProductParams();
        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setSubmitMarketTrigger("0");
        
        l_ifoProductParams.setProductId(1006169090018L);
        l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setUnderlyingProductCode("1");
        l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
        l_ifoProductParams.setStrikePrice(11);
        l_ifoProductParams.setMonthOfDelivery("200702");
        l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setProductCode("112");
        l_ifoProductParams.setSplitType("4");
        
        l_hostFotypeOrderAllParams.setInstitutionCode("0D");
        l_hostFotypeOrderAllParams.setBranchCode("381");
        l_hostFotypeOrderAllParams.setOrderRequestNumber("000003006");
        l_hostFotypeOrderAllParams.setCorpCode("11111111111111101111");
        l_hostFotypeOrderAllParams.setStatus("0");
        l_hostFotypeOrderAllParams.setCancelDiv("0");
        l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
        l_hostFotypeOrderAllParams.setFrontOrderSystemCode("2");
        l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("1");
        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRev("01");
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
        l_ifoOrderUnitParams.setConfirmedQuantity(222);
        l_ifoOrderUnitParams.setLastOrderActionSerialNo(1);
        l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
        l_ifoOrderUnitParams.setOrderConditionType("1");
        l_ifoOrderUnitParams.setRequestType("2");
        l_ifoOrderUnitParams.setConfirmedExecConditionType(IfoOrderExecutionConditionType.AT_MARKET_OPEN);
        l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED);
        l_ifoOrderUnitParams.setBizDate("20020202");
        l_ifoOrderUnitParams.setSonarTradedCode("2");
        l_ifoOrderUnitParams.setSonarMarketCode("6");
        l_ifoOrderUnitParams.setProductId(1006169090018L);
        l_ifoOrderUnitParams.setConfirmedPrice(new Double(333));
        l_ifoOrderUnitParams.setFutureOptionDiv("2");
        try
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        DefaultIfoChangeOrderMarketRequestMessage l_request =
            new DefaultIfoChangeOrderMarketRequestMessage(l_subAccount, l_lngOrderId, l_ifoOrderUnitParams);
        try
        {
            MarketRequestSendResult l_result = l_impl.send(l_request, false);
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());
            List l_lisRows = HostFotypeOrderAllDao.findRowsByStatusSubmitOrderRouteDiv("0", "1");
            HostFotypeOrderAllRow l_row = (HostFotypeOrderAllRow)l_lisRows.get(0);
            //次期デリバ対応 執行条件の説明を変更
            //assertEquals("7", l_row.getChangeExecutionCondition());
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (TooLateException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSendCase27()
    {
        final String STR_METHOD_NAME = "testSendCase27()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrderMQDataCode", new Class[] {IfoOrderUnit.class},
            "321");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "isNotifyEndOrder", new Class[] {OrderUnit.class},
            Boolean.FALSE);
        WEB3DefaultMQSendResultForMock l_defaultMQSendResult = WEB3DefaultMQSendResultForMock.newFailedResultInstance(new ErrorInfo());
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mqgateway.stdimpls.WEB3MQGatewayServiceImpl",
            "send", new Class[] {WEB3MQMessageSpec.class},
            l_defaultMQSendResult);
        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
        IfoProductParams l_ifoProductParams = new IfoProductParams();
        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setSubmitMarketTrigger("0");
        
        l_ifoProductParams.setProductId(1006169090018L);
        l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setUnderlyingProductCode("1");
        l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
        l_ifoProductParams.setStrikePrice(11);
        l_ifoProductParams.setMonthOfDelivery("200702");
        l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setProductCode("112");
        l_ifoProductParams.setSplitType("4");
        
        l_hostFotypeOrderAllParams.setInstitutionCode("0D");
        l_hostFotypeOrderAllParams.setBranchCode("381");
        l_hostFotypeOrderAllParams.setOrderRequestNumber("000003006");
        l_hostFotypeOrderAllParams.setCorpCode("11111111111111101111");
        l_hostFotypeOrderAllParams.setStatus("0");
        l_hostFotypeOrderAllParams.setCancelDiv("0");
        l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
        l_hostFotypeOrderAllParams.setFrontOrderSystemCode("2");
        l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("1");
        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRev("01");
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
        l_ifoOrderUnitParams.setConfirmedQuantity(222);
        l_ifoOrderUnitParams.setLastOrderActionSerialNo(1);
        l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
        l_ifoOrderUnitParams.setOrderConditionType("1");
        l_ifoOrderUnitParams.setRequestType("2");
        l_ifoOrderUnitParams.setConfirmedExecConditionType(IfoOrderExecutionConditionType.AT_MARKET_OPEN);
        l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.FOK);
        l_ifoOrderUnitParams.setBizDate("20020202");
        l_ifoOrderUnitParams.setSonarTradedCode("2");
        l_ifoOrderUnitParams.setSonarMarketCode("6");
        l_ifoOrderUnitParams.setProductId(1006169090018L);
        l_ifoOrderUnitParams.setConfirmedPrice(new Double(333));
        l_ifoOrderUnitParams.setFutureOptionDiv("2");
        l_ifoOrderUnitParams.setFirstOrderUnitId(1111);
        try
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        DefaultIfoChangeOrderMarketRequestMessage l_request =
            new DefaultIfoChangeOrderMarketRequestMessage(l_subAccount, l_lngOrderId, l_ifoOrderUnitParams);
        try
        {
            MarketRequestSendResult l_result = l_impl.send(l_request, false);
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());
            List l_lisRows = HostFotypeOrderAllDao.findRowsByStatusSubmitOrderRouteDiv("0", "1");
            HostFotypeOrderAllRow l_row = (HostFotypeOrderAllRow)l_lisRows.get(0);
            //次期デリバ対応 執行条件の説明を変更
            //assertEquals("2", l_row.getChangeExecutionCondition());
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (TooLateException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSendCase28()
    {
        final String STR_METHOD_NAME = "testSendCase28()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrderMQDataCode", new Class[] {IfoOrderUnit.class},
            null);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "isNotifyEndOrder", new Class[] {OrderUnit.class},
            Boolean.FALSE);
        
        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
        IfoProductParams l_ifoProductParams = new IfoProductParams();
        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setSubmitMarketTrigger("0");
        
        l_ifoProductParams.setProductId(1006169090018L);
        l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setUnderlyingProductCode("1");
        l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
        l_ifoProductParams.setStrikePrice(11);
        l_ifoProductParams.setMonthOfDelivery("200702");
        l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setProductCode("112");
        l_ifoProductParams.setSplitType("4");
        
        l_hostFotypeOrderAllParams.setInstitutionCode("0D");
        l_hostFotypeOrderAllParams.setBranchCode("381");
        l_hostFotypeOrderAllParams.setOrderRequestNumber("000003006");
        l_hostFotypeOrderAllParams.setCorpCode("11111111111111101111");
        l_hostFotypeOrderAllParams.setStatus("0");
        l_hostFotypeOrderAllParams.setCancelDiv("0");
        l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
        l_hostFotypeOrderAllParams.setFrontOrderSystemCode("2");
        l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("1");
        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRev("01");
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
        l_ifoOrderUnitParams.setConfirmedQuantity(222);
        l_ifoOrderUnitParams.setLastOrderActionSerialNo(1);
        l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
        l_ifoOrderUnitParams.setOrderConditionType("1");
        l_ifoOrderUnitParams.setRequestType("2");
        l_ifoOrderUnitParams.setConfirmedExecConditionType(IfoOrderExecutionConditionType.AT_MARKET_OPEN);
        l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.FOK);
        l_ifoOrderUnitParams.setBizDate("20020202");
        l_ifoOrderUnitParams.setSonarTradedCode("2");
        l_ifoOrderUnitParams.setSonarMarketCode("6");
        l_ifoOrderUnitParams.setProductId(1006169090018L);
        l_ifoOrderUnitParams.setConfirmedPrice(new Double(333));
        l_ifoOrderUnitParams.setFutureOptionDiv("2");
        try
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        DefaultIfoChangeOrderMarketRequestMessage l_request =
            new DefaultIfoChangeOrderMarketRequestMessage(l_subAccount, l_lngOrderId, l_ifoOrderUnitParams);
        try
        {
            MarketRequestSendResult l_result = l_impl.send(l_request, false);
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());
            List l_lisRows = HostFotypeOrderAllDao.findRowsByStatusSubmitOrderRouteDiv("0", "1");
            HostFotypeOrderAllRow l_row = (HostFotypeOrderAllRow)l_lisRows.get(0);
            //次期デリバ対応 執行条件の説明を変更
            //assertEquals("1", l_row.getChangeExecutionCondition());
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (TooLateException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSendCase29()
    {
        final String STR_METHOD_NAME = "testSendCase29()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrderMQDataCode", new Class[] {IfoOrderUnit.class},
            "321");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "isNotifyEndOrder", new Class[] {OrderUnit.class},
            Boolean.FALSE);
        WEB3DefaultMQSendResultForMock l_defaultMQSendResult = WEB3DefaultMQSendResultForMock.newSuccessResultInstance();
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mqgateway.stdimpls.WEB3MQGatewayServiceImpl",
            "send", new Class[] {WEB3MQMessageSpec.class},
            l_defaultMQSendResult);
        
        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
        IfoProductParams l_ifoProductParams = new IfoProductParams();
        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setSubmitMarketTrigger("1");
        
        l_ifoProductParams.setProductId(1006169090018L);
        l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setUnderlyingProductCode("1");
        l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
        l_ifoProductParams.setStrikePrice(11);
        l_ifoProductParams.setMonthOfDelivery("200702");
        l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setProductCode("112");
        l_ifoProductParams.setSplitType("4");
        
        l_hostFotypeOrderAllParams.setInstitutionCode("0D");
        l_hostFotypeOrderAllParams.setBranchCode("381");
        l_hostFotypeOrderAllParams.setOrderRequestNumber("000003006");
        l_hostFotypeOrderAllParams.setCorpCode("11111111111111101111");
        l_hostFotypeOrderAllParams.setStatus("0");
        l_hostFotypeOrderAllParams.setCancelDiv("0");
        l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
        l_hostFotypeOrderAllParams.setFrontOrderSystemCode("2");
        l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("1");
        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRev("01");
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
        l_ifoOrderUnitParams.setConfirmedQuantity(222);
        l_ifoOrderUnitParams.setLastOrderActionSerialNo(1);
        l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
        l_ifoOrderUnitParams.setOrderConditionType("1");
        l_ifoOrderUnitParams.setRequestType("2");
        l_ifoOrderUnitParams.setConfirmedExecConditionType(IfoOrderExecutionConditionType.AT_MARKET_OPEN);
        l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED);
        l_ifoOrderUnitParams.setBizDate("20020202");
        l_ifoOrderUnitParams.setSonarTradedCode("2");
        l_ifoOrderUnitParams.setSonarMarketCode("6");
        l_ifoOrderUnitParams.setProductId(1006169090018L);
        l_ifoOrderUnitParams.setConfirmedPrice(new Double(333));
        l_ifoOrderUnitParams.setFutureOptionDiv("2");
        try
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        DefaultIfoChangeOrderMarketRequestMessage l_request =
            new DefaultIfoChangeOrderMarketRequestMessage(l_subAccount, l_lngOrderId, l_ifoOrderUnitParams);
        try
        {
            MarketRequestSendResult l_result = l_impl.send(l_request, false);
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (TooLateException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSendCase30()
    {
        final String STR_METHOD_NAME = "testSendCase30()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getChangeCancelMQDataCode", new Class[] {IfoOrderUnit.class},
            "321");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
            "isNotifyEndOrder", new Class[] {OrderUnit.class},
            new Boolean(false));
        WEB3DefaultMQSendResultForMock l_defaultMQSendResult = WEB3DefaultMQSendResultForMock.newFailedResultInstance(new ErrorInfo());
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mqgateway.stdimpls.WEB3MQGatewayServiceImpl",
            "send", new Class[] {WEB3MQMessageSpec.class},
            l_defaultMQSendResult);
        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
        IfoProductParams l_ifoProductParams = new IfoProductParams();
        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        l_orderSwitchingParams.setInstitutionCode("0D");
        l_orderSwitchingParams.setProductType(ProductTypeEnum.IFO);
        l_orderSwitchingParams.setMarketCode("SP");
        l_orderSwitchingParams.setFrontOrderSystemCode("9");
        l_orderSwitchingParams.setSubmitOrderRouteDiv("1");
        l_orderSwitchingParams.setSubmitMqTrigger("1");
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setSubmitMarketTrigger("1");
        
        l_ifoProductParams.setProductId(1006169090018L);
        l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setUnderlyingProductCode("1");
        l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
        l_ifoProductParams.setStrikePrice(11);
        l_ifoProductParams.setMonthOfDelivery("200702");
        l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setProductCode("112");
        l_ifoProductParams.setSplitType("4");
        
        l_hostFotypeOrderAllParams.setInstitutionCode("0D");
        l_hostFotypeOrderAllParams.setBranchCode("381");
        l_hostFotypeOrderAllParams.setOrderRequestNumber("000003006");
        l_hostFotypeOrderAllParams.setCorpCode("11111111111111101111");
        l_hostFotypeOrderAllParams.setStatus("0");
        l_hostFotypeOrderAllParams.setCancelDiv("0");
        l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
        l_hostFotypeOrderAllParams.setFrontOrderSystemCode("9");
        l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("1");
        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRev("01");
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
        l_ifoOrderUnitParams.setConfirmedQuantity(222);
        l_ifoOrderUnitParams.setLastOrderActionSerialNo(1);
        l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
        l_ifoOrderUnitParams.setOrderConditionType("2");
        l_ifoOrderUnitParams.setRequestType("2");
        l_ifoOrderUnitParams.setConfirmedExecConditionType(IfoOrderExecutionConditionType.AT_MARKET_OPEN);
        l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.FOK);
        l_ifoOrderUnitParams.setBizDate("20020202");
        l_ifoOrderUnitParams.setSonarTradedCode("2");
        l_ifoOrderUnitParams.setSonarMarketCode("6");
        l_ifoOrderUnitParams.setProductId(1006169090018L);
        l_ifoOrderUnitParams.setConfirmedPrice(new Double(333));
        l_ifoOrderUnitParams.setFutureOptionDiv("2");
        l_ifoOrderUnitParams.setFirstOrderUnitId(1111);
        try
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.deleteAll(OrderSwitchingRow.TYPE);
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        DefaultIfoChangeOrderMarketRequestMessage l_request =
            new DefaultIfoChangeOrderMarketRequestMessage(l_subAccount, l_lngOrderId, l_ifoOrderUnitParams);
        try
        {
            MarketRequestSendResult l_result = l_impl.send(l_request, false);
            assertFalse(l_result.getProcessingResult().isSuccessfulResult());
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (TooLateException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSendCase31()
    {
        final String STR_METHOD_NAME = "testSendCase31()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrderMQDataCode", new Class[] {IfoOrderUnit.class},
            "321");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
            "isNotifyEndOrder", new Class[] {OrderUnit.class},
            Boolean.TRUE);
        WEB3DefaultMQSendResultForMock l_defaultMQSendResult = WEB3DefaultMQSendResultForMock.newFailedResultInstance(new ErrorInfo());
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mqgateway.stdimpls.WEB3MQGatewayServiceImpl",
            "send", new Class[] {WEB3MQMessageSpec.class},
            l_defaultMQSendResult);
        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
        IfoProductParams l_ifoProductParams = new IfoProductParams();
        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setSubmitMarketTrigger("1");
        
        l_ifoProductParams.setProductId(1006169090018L);
        l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setUnderlyingProductCode("1");
        l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
        l_ifoProductParams.setStrikePrice(11);
        l_ifoProductParams.setMonthOfDelivery("200702");
        l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setProductCode("112");
        l_ifoProductParams.setSplitType("4");
        
        l_hostFotypeOrderAllParams.setInstitutionCode("0D");
        l_hostFotypeOrderAllParams.setBranchCode("381");
        l_hostFotypeOrderAllParams.setOrderRequestNumber("000003006");
        l_hostFotypeOrderAllParams.setCorpCode("11111111111111101111");
        l_hostFotypeOrderAllParams.setStatus("0");
        l_hostFotypeOrderAllParams.setCancelDiv("0");
        l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
        l_hostFotypeOrderAllParams.setFrontOrderSystemCode("2");
        l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("1");
        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRev("01");
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
        l_ifoOrderUnitParams.setConfirmedQuantity(222);
        l_ifoOrderUnitParams.setLastOrderActionSerialNo(1);
        l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
        l_ifoOrderUnitParams.setOrderConditionType("1");
        l_ifoOrderUnitParams.setRequestType("2");
        l_ifoOrderUnitParams.setConfirmedExecConditionType(IfoOrderExecutionConditionType.AT_MARKET_OPEN);
        l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.FOK);
        l_ifoOrderUnitParams.setBizDate("20020202");
        l_ifoOrderUnitParams.setSonarTradedCode("2");
        l_ifoOrderUnitParams.setSonarMarketCode("6");
        l_ifoOrderUnitParams.setProductId(1006169090018L);
        l_ifoOrderUnitParams.setConfirmedPrice(new Double(333));
        l_ifoOrderUnitParams.setFutureOptionDiv("2");
        l_ifoOrderUnitParams.setFirstOrderUnitId(1111);
        try
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        DefaultIfoChangeOrderMarketRequestMessage l_request =
            new DefaultIfoChangeOrderMarketRequestMessage(l_subAccount, l_lngOrderId, l_ifoOrderUnitParams);
        try
        {
            MarketRequestSendResult l_result = l_impl.send(l_request, false);
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (TooLateException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSendCase32()
    {
        final String STR_METHOD_NAME = "testSendCase32()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrderMQDataCode", new Class[] {IfoOrderUnit.class},
            "321");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "isNotifyEndOrder", new Class[] {OrderUnit.class},
            Boolean.FALSE);
        WEB3DefaultMQSendResultForMock l_defaultMQSendResult = WEB3DefaultMQSendResultForMock.newFailedResultInstance(new ErrorInfo());
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mqgateway.stdimpls.WEB3MQGatewayServiceImpl",
            "send", new Class[] {WEB3MQMessageSpec.class},
            l_defaultMQSendResult);
        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
        IfoProductParams l_ifoProductParams = new IfoProductParams();
        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setSubmitMarketTrigger("1");
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("381");
        l_tradingTimeParams.setMarketCode("SP");
        l_tradingTimeParams.setBizdateCalcParameter("0");
        l_tradingTimeParams.setStartTime("000000");
        l_tradingTimeParams.setEndTime("135959");
        
        l_ifoProductParams.setProductId(1006169090018L);
        l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setUnderlyingProductCode("1");
        l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
        l_ifoProductParams.setStrikePrice(11);
        l_ifoProductParams.setMonthOfDelivery("200702");
        l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setProductCode("112");
        l_ifoProductParams.setSplitType("4");
        
        l_hostFotypeOrderAllParams.setInstitutionCode("0D");
        l_hostFotypeOrderAllParams.setBranchCode("381");
        l_hostFotypeOrderAllParams.setOrderRequestNumber("000003006");
        l_hostFotypeOrderAllParams.setCorpCode("11111111111111101111");
        l_hostFotypeOrderAllParams.setStatus("0");
        l_hostFotypeOrderAllParams.setCancelDiv("0");
        l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
        l_hostFotypeOrderAllParams.setFrontOrderSystemCode("2");
        l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("1");
        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRev("01");
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
        l_ifoOrderUnitParams.setConfirmedQuantity(222);
        l_ifoOrderUnitParams.setLastOrderActionSerialNo(1);
        l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
        l_ifoOrderUnitParams.setOrderConditionType("1");
        l_ifoOrderUnitParams.setRequestType("2");
        l_ifoOrderUnitParams.setConfirmedExecConditionType(IfoOrderExecutionConditionType.AT_MARKET_OPEN);
        l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.FOK);
        l_ifoOrderUnitParams.setBizDate("20020202");
        l_ifoOrderUnitParams.setSonarTradedCode("2");
        l_ifoOrderUnitParams.setSonarMarketCode("6");
        l_ifoOrderUnitParams.setProductId(1006169090018L);
        l_ifoOrderUnitParams.setConfirmedPrice(new Double(333));
        l_ifoOrderUnitParams.setFutureOptionDiv("2");
        l_ifoOrderUnitParams.setFirstOrderUnitId(1111);
        try
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        DefaultIfoChangeOrderMarketRequestMessage l_request =
            new DefaultIfoChangeOrderMarketRequestMessage(l_subAccount, l_lngOrderId, l_ifoOrderUnitParams);
        try
        {
            MarketRequestSendResult l_result = l_impl.send(l_request, false);
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (TooLateException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSendCase33()
    {
        final String STR_METHOD_NAME = "testSendCase33()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        DefaultIfoChangeSettleContractOrderMarketRequestMessage l_request = null;
        try
        {
            l_impl.send(l_request, true);
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            assertEquals("パラメータ値不正。", l_ex.getErrorInfo().getErrorMessage());
        }
        catch (TooLateException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSendCase34()
    {
        final String STR_METHOD_NAME = "testSendCase34()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();

        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        try
        {
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        IfoClosingContractSpecRow[] l_closingContractRows = new IfoClosingContractSpecParams[1];
        DefaultIfoChangeSettleContractOrderMarketRequestMessage l_request =
            new DefaultIfoChangeSettleContractOrderMarketRequestMessage(
                l_subAccount, l_lngOrderId, l_ifoOrderUnitParams, l_closingContractRows);
        try
        {
            MarketRequestSendResult l_result = l_impl.send(l_request, true);
            assertFalse(l_result.getProcessingResult().isSuccessfulResult());
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (TooLateException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSendCase35()
    {
        final String STR_METHOD_NAME = "testSendCase35()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
        
        l_hostFotypeOrderAllParams.setInstitutionCode("0D");
        l_hostFotypeOrderAllParams.setBranchCode("381");
        l_hostFotypeOrderAllParams.setOrderRequestNumber("000003006");
        l_hostFotypeOrderAllParams.setCorpCode("11111111111111101111");
        l_hostFotypeOrderAllParams.setStatus("0");
        l_hostFotypeOrderAllParams.setCancelDiv("0");
        l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
        l_hostFotypeOrderAllParams.setFrontOrderSystemCode("2");
        l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("1");
        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRev("01");
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE);
        l_ifoOrderUnitParams.setConfirmedQuantity(222);
        l_ifoOrderUnitParams.setLastOrderActionSerialNo(1);
        l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
        try
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        IfoClosingContractSpecRow[] l_closingContractRows = new IfoClosingContractSpecParams[1];
        DefaultIfoChangeSettleContractOrderMarketRequestMessage l_request =
            new DefaultIfoChangeSettleContractOrderMarketRequestMessage(
                l_subAccount, l_lngOrderId, l_ifoOrderUnitParams, l_closingContractRows);
        try
        {
            MarketRequestSendResult l_result = l_impl.send(l_request, true);
            List l_lstRows = HostFotypeOrderAllDao.findRowsByStatusSubmitOrderRouteDiv("0", "1");
            HostFotypeOrderAllRow l_row = (HostFotypeOrderAllRow)l_lstRows.get(0);
            assertEquals(1, l_row.getOrderActionSerialNo());
            assertEquals("1", l_row.getSubmitOrderRouteDiv());
            assertEquals("100", WEB3StringTypeUtility.formatNumber(l_row.getBuyOrderQuantity()));
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_row.getSellOrderQuantity()));
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_row.getLimitPrice()));
            //次期デリバ対応 執行条件の説明を変更
            //assertEquals("1", l_row.getExecutionCondition());
            System.out.println("getLastUpdatedTimestampIsSet=" + l_row.getLastUpdatedTimestamp());
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (TooLateException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSendCase36()
    {
        final String STR_METHOD_NAME = "testSendCase36()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
        
        l_hostFotypeOrderAllParams.setInstitutionCode("0D");
        l_hostFotypeOrderAllParams.setBranchCode("381");
        l_hostFotypeOrderAllParams.setOrderRequestNumber("000003006");
        l_hostFotypeOrderAllParams.setCorpCode("11111111111111101111");
        l_hostFotypeOrderAllParams.setStatus("0");
        l_hostFotypeOrderAllParams.setCancelDiv("0");
        l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
        l_hostFotypeOrderAllParams.setFrontOrderSystemCode("2");
        l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("1");
        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRev("01");
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE);
        l_ifoOrderUnitParams.setConfirmedQuantity(222);
        l_ifoOrderUnitParams.setLastOrderActionSerialNo(1);
        l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
        l_ifoOrderUnitParams.setOrderConditionType("1");
        l_ifoOrderUnitParams.setRequestType("2");
        try
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        IfoClosingContractSpecRow[] l_closingContractRows = new IfoClosingContractSpecParams[1];
        DefaultIfoChangeSettleContractOrderMarketRequestMessage l_request =
            new DefaultIfoChangeSettleContractOrderMarketRequestMessage(
                l_subAccount, l_lngOrderId, l_ifoOrderUnitParams, l_closingContractRows);
        try
        {
            MarketRequestSendResult l_result = l_impl.send(l_request, true);
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (TooLateException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSendCase37()
    {
        final String STR_METHOD_NAME = "testSendCase37()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getHostFotypeOrderAll", new Class[] {IfoOrderUnit.class},
            null);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "isNotifyEndOrder", new Class[] {OrderUnit.class},
            Boolean.TRUE);
        
        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
        
        l_hostFotypeOrderAllParams.setInstitutionCode("0D");
        l_hostFotypeOrderAllParams.setBranchCode("381");
        l_hostFotypeOrderAllParams.setOrderRequestNumber("000003006");
        l_hostFotypeOrderAllParams.setCorpCode("11111111111111101111");
        l_hostFotypeOrderAllParams.setStatus("0");
        l_hostFotypeOrderAllParams.setCancelDiv("0");
        l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
        l_hostFotypeOrderAllParams.setFrontOrderSystemCode("2");
        l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("1");
        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRev("01");
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE);
        l_ifoOrderUnitParams.setConfirmedQuantity(222);
        l_ifoOrderUnitParams.setLastOrderActionSerialNo(1);
        l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
        l_ifoOrderUnitParams.setOrderConditionType("1");
        l_ifoOrderUnitParams.setRequestType("2");
        try
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        IfoClosingContractSpecRow[] l_closingContractRows = new IfoClosingContractSpecParams[1];
        DefaultIfoChangeSettleContractOrderMarketRequestMessage l_request =
            new DefaultIfoChangeSettleContractOrderMarketRequestMessage(
                l_subAccount, l_lngOrderId, l_ifoOrderUnitParams, l_closingContractRows);
        try
        {
            MarketRequestSendResult l_result = l_impl.send(l_request, false);
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (TooLateException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSendCase38()
    {
        final String STR_METHOD_NAME = "testSendCase38()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrderMQDataCode", new Class[] {IfoOrderUnit.class},
            null);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "isNotifyEndOrder", new Class[] {OrderUnit.class},
            Boolean.FALSE);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getFrontOrderExchangeCode", new Class[] {String.class},
            "7");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getFrontOrderSystemCode", new Class[] {String.class},
            "8");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getCorpCode", new Class[] {IfoOrderUnit.class},
            "33381600000300601999");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrgCorpCode", new Class[] {IfoOrderUnit.class},
            "3338160000030062999");
        
        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
        IfoProductParams l_ifoProductParams = new IfoProductParams();
        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setSubmitMarketTrigger("0");
        
        l_ifoProductParams.setProductId(1006169090018L);
        l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setUnderlyingProductCode("1");
        l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.FUTURES);
        l_ifoProductParams.setStrikePrice(11);
        l_ifoProductParams.setMonthOfDelivery("200702");
        l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setProductCode("112");
        l_ifoProductParams.setSplitType("4");
        
        l_hostFotypeOrderAllParams.setInstitutionCode("0D");
        l_hostFotypeOrderAllParams.setBranchCode("381");
        l_hostFotypeOrderAllParams.setOrderRequestNumber("000003006");
        l_hostFotypeOrderAllParams.setCorpCode("11111111111111101111");
        l_hostFotypeOrderAllParams.setStatus("0");
        l_hostFotypeOrderAllParams.setCancelDiv("0");
        l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
        l_hostFotypeOrderAllParams.setFrontOrderSystemCode("2");
        l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("1");
        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRev("01");
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE);
        l_ifoOrderUnitParams.setConfirmedQuantity(222);
        l_ifoOrderUnitParams.setLastOrderActionSerialNo(1);
        l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
        l_ifoOrderUnitParams.setOrderConditionType("1");
        l_ifoOrderUnitParams.setRequestType("2");
        l_ifoOrderUnitParams.setConfirmedExecConditionType(IfoOrderExecutionConditionType.AT_MARKET_OPEN);
        l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.AT_MARKET_OPEN);
        SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
        String l_strCreateDate = l_format.format(GtlUtils.getSystemTimestamp());
        l_ifoOrderUnitParams.setBizDate(l_strCreateDate);
        l_ifoOrderUnitParams.setSonarTraderCode("2");
        l_ifoOrderUnitParams.setSonarMarketCode("6");
        l_ifoOrderUnitParams.setProductId(1006169090018L);
        l_ifoOrderUnitParams.setConfirmedPrice(new Double(333));
        try
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        IfoClosingContractSpecRow[] l_closingContractRows = new IfoClosingContractSpecParams[1];
        DefaultIfoChangeSettleContractOrderMarketRequestMessage l_request =
            new DefaultIfoChangeSettleContractOrderMarketRequestMessage(
                l_subAccount, l_lngOrderId, l_ifoOrderUnitParams, l_closingContractRows);
        try
        {
            MarketRequestSendResult l_result = l_impl.send(l_request, false);
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());
            List l_lisRows = HostFotypeOrderAllDao.findRowsByStatusSubmitOrderRouteDiv("0", "1");
            HostFotypeOrderAllRow l_row = (HostFotypeOrderAllRow)l_lisRows.get(0);
            System.out.println(l_row.getRequestCode());
            assertEquals("EI804", l_row.getRequestCode());
            System.out.println(l_row.getAccountId());
            assertEquals(101001010010L, l_row.getAccountId());
            System.out.println(l_row.getInstitutionCode());
            assertEquals("0D", l_row.getInstitutionCode());
            System.out.println(l_row.getBranchCode());
            assertEquals("381", l_row.getBranchCode());
            System.out.println(l_row.getAccountCode());
            assertEquals("2512246", l_row.getAccountCode());
            System.out.println(l_row.getTraderCode());
            assertEquals("2", l_row.getTraderCode());
            System.out.println(l_row.getReceivedDateTimeDiv());
            assertEquals("0", l_row.getReceivedDateTimeDiv());
            System.out.println(l_row.getOrderRequestNumber());
            assertEquals("000003006", l_row.getOrderRequestNumber());
            System.out.println(l_row.getSonarMarketCode());
            assertEquals("6", l_row.getSonarMarketCode());
            //次期デリバ対応 執行条件の説明を変更
            //System.out.println(l_row.getProductCode());
            //assertEquals("112", l_row.getProductCode());
            System.out.println(l_row.getReceivedDateTime());
            assertEquals(WEB3DateUtility.formatDate(
                GtlUtils.getSystemTimestamp(), "yyyyMMdd"),
                WEB3DateUtility.formatDate(l_row.getReceivedDateTime(), "yyyyMMdd"));
            System.out.println(l_row.getOrderActionSerialNo());
            assertEquals(1, l_row.getOrderActionSerialNo());
            System.out.println(l_row.getSubmitOrderRouteDiv());
            assertEquals("1", l_row.getSubmitOrderRouteDiv());
            System.out.println(l_row.getTargetProductCode());
            assertEquals("1", l_row.getTargetProductCode());
            System.out.println(l_row.getDeliveryMonthYyyy());
            assertEquals("2007", l_row.getDeliveryMonthYyyy());
            System.out.println(l_row.getDeliveryMonthMm());
            assertEquals("02", l_row.getDeliveryMonthMm());
            System.out.println(l_row.getFutureOptionProductType());
            assertEquals("F", l_row.getFutureOptionProductType());
            System.out.println(l_row.getStrikePrice());
            assertEquals("11", WEB3StringTypeUtility.formatNumber(l_row.getStrikePrice()));
            System.out.println(l_row.getSplitType());
            assertEquals("4", l_row.getSplitType());
            System.out.println(l_row.getSellOrderQuantity());
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_row.getSellOrderQuantity()));
            System.out.println(l_row.getBuyOrderQuantity());
            assertEquals("222", WEB3StringTypeUtility.formatNumber(l_row.getBuyOrderQuantity()));
            System.out.println(l_row.getLimitPrice());
            assertEquals("333", WEB3StringTypeUtility.formatNumber(l_row.getLimitPrice()));
            //次期デリバ対応 説明を変更
            //System.out.println(l_row.getExecutionCondition());
            //assertEquals("3", l_row.getExecutionCondition());
            System.out.println(l_row.getStopOrderPrice());
            assertTrue(l_row.getStopOrderPriceIsNull());
            System.out.println(l_row.getWLimitPrice());
            assertTrue(l_row.getWLimitPriceIsNull());
            System.out.println(l_row.getTransactionType());
            assertEquals(null, l_row.getTransactionType());
            System.out.println(l_row.getTicketNumber());
            assertEquals(null, l_row.getTicketNumber());
            System.out.println(l_row.getContractCheck());
            assertEquals(null, l_row.getContractCheck());
            System.out.println(l_row.getOrderChanel());
            assertEquals(null, l_row.getOrderChanel());
            System.out.println(l_row.getCommisionNumber());
            assertEquals(null, l_row.getCommisionNumber());
            System.out.println(l_row.getCommisionBranchNumber());
            assertEquals(null, l_row.getCommisionBranchNumber());
            System.out.println(l_row.getCommisionProductCode());
            assertEquals(null, l_row.getCommisionProductCode());
            System.out.println(l_row.getChangeQuantity());
            assertEquals("100", WEB3StringTypeUtility.formatNumber(l_row.getChangeQuantity()));
            System.out.println(l_row.getChangeLimitPrice());
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_row.getChangeLimitPrice()));
            //次期デリバ対応 説明を変更
            //System.out.println(l_row.getChangeExecutionCondition());
            //assertEquals("3", l_row.getChangeExecutionCondition());
            System.out.println(l_row.getChangeStopOrderPrice());
            assertTrue(l_row.getChangeStopOrderPriceIsNull());
            System.out.println(l_row.getChangeWLimitPrice());
            assertTrue(l_row.getChangeWLimitPriceIsNull());
            System.out.println(l_row.getCancelDiv());
            assertEquals("0", l_row.getCancelDiv());
            System.out.println(l_row.getFrontOrderExchangeCode());
            assertEquals("7", l_row.getFrontOrderExchangeCode());
            System.out.println(l_row.getFrontOrderSystemCode());
            assertEquals("8", l_row.getFrontOrderSystemCode());
            System.out.println(l_row.getFrontOrderTradeCode());
            assertEquals("1", l_row.getFrontOrderTradeCode());
            System.out.println(l_row.getTradeauditCode());
            assertEquals(null, l_row.getTradeauditCode());
            System.out.println(l_row.getCorpCode());
            assertEquals("33381600000300601999", l_row.getCorpCode());
            System.out.println(l_row.getOrgCorpCode());
            assertEquals("3338160000030062999", l_row.getOrgCorpCode());
            System.out.println(l_row.getVirtualServerNumberJsoes());
            assertEquals(null, l_row.getVirtualServerNumberJsoes());
            System.out.println(l_row.getMarketOrderNumber());
            assertTrue(l_row.getMarketOrderNumberIsNull());
            System.out.println(l_row.getAmgSendTime());
            assertEquals(null, l_row.getAmgSendTime());
            System.out.println(l_row.getAmgAckTime());
            assertEquals(null, l_row.getAmgAckTime());
            System.out.println(l_row.getMarketAckTime());
            assertEquals(null, l_row.getMarketAckTime());
            System.out.println(l_row.getAllOrderChangeDiv());
            assertEquals("0", l_row.getAllOrderChangeDiv());
            System.out.println(l_row.getMarketCloseFlag());
            assertEquals("0", l_row.getMarketCloseFlag());
            System.out.println(l_row.getStatus());
            assertEquals("0", l_row.getStatus());
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (TooLateException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSendCase39()
    {
        final String STR_METHOD_NAME = "testSendCase39()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrderMQDataCode", new Class[] {IfoOrderUnit.class},
            null);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "isNotifyEndOrder", new Class[] {OrderUnit.class},
            Boolean.FALSE);
        
        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
        IfoProductParams l_ifoProductParams = new IfoProductParams();
        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setSubmitMarketTrigger("0");
        
        l_ifoProductParams.setProductId(1006169090018L);
        l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setUnderlyingProductCode("1");
        l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.CALL_OPTIONS);
        l_ifoProductParams.setStrikePrice(11);
        l_ifoProductParams.setMonthOfDelivery("200702");
        l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setProductCode("112");
        l_ifoProductParams.setSplitType("4");
        
        l_hostFotypeOrderAllParams.setInstitutionCode("0D");
        l_hostFotypeOrderAllParams.setBranchCode("381");
        l_hostFotypeOrderAllParams.setOrderRequestNumber("000003006");
        l_hostFotypeOrderAllParams.setCorpCode("11111111111111101111");
        l_hostFotypeOrderAllParams.setStatus("0");
        l_hostFotypeOrderAllParams.setCancelDiv("0");
        l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
        l_hostFotypeOrderAllParams.setFrontOrderSystemCode("2");
        l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("1");
        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRev("01");
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_CLOSE);
        l_ifoOrderUnitParams.setConfirmedQuantity(222);
        l_ifoOrderUnitParams.setLastOrderActionSerialNo(1);
        l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
        l_ifoOrderUnitParams.setOrderConditionType("1");
        l_ifoOrderUnitParams.setRequestType("2");
        l_ifoOrderUnitParams.setConfirmedExecConditionType(IfoOrderExecutionConditionType.AT_MARKET_OPEN);
        l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.AT_MARKET_CLOSE);
        l_ifoOrderUnitParams.setBizDate("20020202");
        l_ifoOrderUnitParams.setSonarTradedCode("2");
        l_ifoOrderUnitParams.setSonarMarketCode("6");
        l_ifoOrderUnitParams.setProductId(1006169090018L);
        l_ifoOrderUnitParams.setConfirmedPrice(new Double(333));
        l_ifoOrderUnitParams.setFutureOptionDiv("2");
        try
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        IfoClosingContractSpecRow[] l_closingContractRows = new IfoClosingContractSpecParams[1];
        DefaultIfoChangeSettleContractOrderMarketRequestMessage l_request =
            new DefaultIfoChangeSettleContractOrderMarketRequestMessage(
                l_subAccount, l_lngOrderId, l_ifoOrderUnitParams, l_closingContractRows);
        try
        {
            MarketRequestSendResult l_result = l_impl.send(l_request, false);
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());
            List l_lisRows = HostFotypeOrderAllDao.findRowsByStatusSubmitOrderRouteDiv("0", "1");
            HostFotypeOrderAllRow l_row = (HostFotypeOrderAllRow)l_lisRows.get(0);
            System.out.println(l_row.getRequestCode());
            assertEquals("EI802", l_row.getRequestCode());
            System.out.println(l_row.getReceivedDateTimeDiv());
            assertEquals("1", l_row.getReceivedDateTimeDiv());
            System.out.println(l_row.getFutureOptionProductType());
            assertEquals("C", l_row.getFutureOptionProductType());
            //次期デリバ対応 執行条件の説明を変更
            //System.out.println(l_row.getChangeExecutionCondition());
            //assertEquals("4", l_row.getChangeExecutionCondition());
            System.out.println(l_row.getBuyOrderQuantity());
            assertEquals("222", WEB3StringTypeUtility.formatNumber(l_row.getBuyOrderQuantity()));
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (TooLateException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSendCase40()
    {
        final String STR_METHOD_NAME = "testSendCase40()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrderMQDataCode", new Class[] {IfoOrderUnit.class},
            null);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "isNotifyEndOrder", new Class[] {OrderUnit.class},
            Boolean.FALSE);
        
        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
        IfoProductParams l_ifoProductParams = new IfoProductParams();
        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setSubmitMarketTrigger("0");
        
        l_ifoProductParams.setProductId(1006169090018L);
        l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setUnderlyingProductCode("1");
        l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
        l_ifoProductParams.setStrikePrice(11);
        l_ifoProductParams.setMonthOfDelivery("200702");
        l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setProductCode("112");
        l_ifoProductParams.setSplitType("4");
        
        l_hostFotypeOrderAllParams.setInstitutionCode("0D");
        l_hostFotypeOrderAllParams.setBranchCode("381");
        l_hostFotypeOrderAllParams.setOrderRequestNumber("000003006");
        l_hostFotypeOrderAllParams.setCorpCode("11111111111111101111");
        l_hostFotypeOrderAllParams.setStatus("0");
        l_hostFotypeOrderAllParams.setCancelDiv("0");
        l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
        l_hostFotypeOrderAllParams.setFrontOrderSystemCode("2");
        l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("1");
        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRev("01");
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_SELL_TO_CLOSE);
        l_ifoOrderUnitParams.setConfirmedQuantity(222);
        l_ifoOrderUnitParams.setLastOrderActionSerialNo(1);
        l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
        l_ifoOrderUnitParams.setOrderConditionType("1");
        l_ifoOrderUnitParams.setRequestType("2");
        l_ifoOrderUnitParams.setConfirmedExecConditionType(IfoOrderExecutionConditionType.AT_MARKET_OPEN);
        l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.AT_MARKET_CLOSE);
        l_ifoOrderUnitParams.setBizDate("20020202");
        l_ifoOrderUnitParams.setSonarTradedCode("2");
        l_ifoOrderUnitParams.setSonarMarketCode("6");
        l_ifoOrderUnitParams.setProductId(1006169090018L);
        l_ifoOrderUnitParams.setConfirmedPrice(new Double(333));
        l_ifoOrderUnitParams.setFutureOptionDiv("2");
        try
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        IfoClosingContractSpecRow[] l_closingContractRows = new IfoClosingContractSpecParams[1];
        DefaultIfoChangeSettleContractOrderMarketRequestMessage l_request =
            new DefaultIfoChangeSettleContractOrderMarketRequestMessage(
                l_subAccount, l_lngOrderId, l_ifoOrderUnitParams, l_closingContractRows);
        try
        {
            MarketRequestSendResult l_result = l_impl.send(l_request, false);
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());
            List l_lisRows = HostFotypeOrderAllDao.findRowsByStatusSubmitOrderRouteDiv("0", "1");
            HostFotypeOrderAllRow l_row = (HostFotypeOrderAllRow)l_lisRows.get(0);
            assertEquals("P", l_row.getFutureOptionProductType());
            assertEquals("222", WEB3StringTypeUtility.formatNumber(l_row.getSellOrderQuantity()));
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (TooLateException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSendCase41()
    {
        final String STR_METHOD_NAME = "testSendCase41()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrderMQDataCode", new Class[] {IfoOrderUnit.class},
            null);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "isNotifyEndOrder", new Class[] {OrderUnit.class},
            Boolean.FALSE);
        
        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
        IfoProductParams l_ifoProductParams = new IfoProductParams();
        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setSubmitMarketTrigger("0");
        
        l_ifoProductParams.setProductId(1006169090018L);
        l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setUnderlyingProductCode("1");
        l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
        l_ifoProductParams.setStrikePrice(11);
        l_ifoProductParams.setMonthOfDelivery("200702");
        l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setProductCode("112");
        l_ifoProductParams.setSplitType("4");
        
        l_hostFotypeOrderAllParams.setInstitutionCode("0D");
        l_hostFotypeOrderAllParams.setBranchCode("381");
        l_hostFotypeOrderAllParams.setOrderRequestNumber("000003006");
        l_hostFotypeOrderAllParams.setCorpCode("11111111111111101111");
        l_hostFotypeOrderAllParams.setStatus("0");
        l_hostFotypeOrderAllParams.setCancelDiv("0");
        l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
        l_hostFotypeOrderAllParams.setFrontOrderSystemCode("2");
        l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("1");
        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRev("01");
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_SELL_TO_CLOSE);
        l_ifoOrderUnitParams.setConfirmedQuantity(222);
        l_ifoOrderUnitParams.setLastOrderActionSerialNo(1);
        l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
        l_ifoOrderUnitParams.setOrderConditionType("1");
        l_ifoOrderUnitParams.setRequestType("2");
        l_ifoOrderUnitParams.setConfirmedExecConditionType(IfoOrderExecutionConditionType.AT_MARKET_OPEN);
        l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.AT_MARKET_CLOSE);
        l_ifoOrderUnitParams.setBizDate("20020202");
        l_ifoOrderUnitParams.setSonarTradedCode("2");
        l_ifoOrderUnitParams.setSonarMarketCode("6");
        l_ifoOrderUnitParams.setProductId(1006169090018L);
        l_ifoOrderUnitParams.setConfirmedPrice(new Double(333));
        l_ifoOrderUnitParams.setFutureOptionDiv("2");
        try
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        IfoClosingContractSpecRow[] l_closingContractRows = new IfoClosingContractSpecParams[1];
        DefaultIfoChangeSettleContractOrderMarketRequestMessage l_request =
            new DefaultIfoChangeSettleContractOrderMarketRequestMessage(
                l_subAccount, l_lngOrderId, l_ifoOrderUnitParams, l_closingContractRows);
        try
        {
            MarketRequestSendResult l_result = l_impl.send(l_request, false);
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());
            List l_lisRows = HostFotypeOrderAllDao.findRowsByStatusSubmitOrderRouteDiv("0", "1");
            HostFotypeOrderAllRow l_row = (HostFotypeOrderAllRow)l_lisRows.get(0);
            assertEquals("222", WEB3StringTypeUtility.formatNumber(l_row.getSellOrderQuantity()));
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (TooLateException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSendCase42()
    {
        final String STR_METHOD_NAME = "testSendCase42()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrderMQDataCode", new Class[] {IfoOrderUnit.class},
            "321");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "isNotifyEndOrder", new Class[] {OrderUnit.class},
            Boolean.FALSE);
        WEB3DefaultMQSendResultForMock l_defaultMQSendResult = WEB3DefaultMQSendResultForMock.newSuccessResultInstance();
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mqgateway.stdimpls.WEB3MQGatewayServiceImpl",
            "send", new Class[] {WEB3MQMessageSpec.class},
            l_defaultMQSendResult);
        
        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
        IfoProductParams l_ifoProductParams = new IfoProductParams();
        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setSubmitMarketTrigger("0");
        
        l_ifoProductParams.setProductId(1006169090018L);
        l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setUnderlyingProductCode("1");
        l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
        l_ifoProductParams.setStrikePrice(11);
        l_ifoProductParams.setMonthOfDelivery("200702");
        l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setProductCode("112");
        l_ifoProductParams.setSplitType("4");
        
        l_hostFotypeOrderAllParams.setInstitutionCode("0D");
        l_hostFotypeOrderAllParams.setBranchCode("381");
        l_hostFotypeOrderAllParams.setOrderRequestNumber("000003006");
        l_hostFotypeOrderAllParams.setCorpCode("11111111111111101111");
        l_hostFotypeOrderAllParams.setStatus("0");
        l_hostFotypeOrderAllParams.setCancelDiv("0");
        l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
        l_hostFotypeOrderAllParams.setFrontOrderSystemCode("2");
        l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("1");
        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRev("01");
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE);
        l_ifoOrderUnitParams.setConfirmedQuantity(222);
        l_ifoOrderUnitParams.setLastOrderActionSerialNo(1);
        l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
        l_ifoOrderUnitParams.setOrderConditionType("1");
        l_ifoOrderUnitParams.setRequestType("2");
        l_ifoOrderUnitParams.setConfirmedExecConditionType(IfoOrderExecutionConditionType.AT_MARKET_OPEN);
        l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED);
        l_ifoOrderUnitParams.setBizDate("20020202");
        l_ifoOrderUnitParams.setSonarTradedCode("2");
        l_ifoOrderUnitParams.setSonarMarketCode("6");
        l_ifoOrderUnitParams.setProductId(1006169090018L);
        l_ifoOrderUnitParams.setConfirmedPrice(new Double(333));
        l_ifoOrderUnitParams.setFutureOptionDiv("2");
        try
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        IfoClosingContractSpecRow[] l_closingContractRows = new IfoClosingContractSpecParams[1];
        DefaultIfoChangeSettleContractOrderMarketRequestMessage l_request =
            new DefaultIfoChangeSettleContractOrderMarketRequestMessage(
                l_subAccount, l_lngOrderId, l_ifoOrderUnitParams, l_closingContractRows);
        try
        {
            MarketRequestSendResult l_result = l_impl.send(l_request, false);
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());
            List l_lisRows = HostFotypeOrderAllDao.findRowsByStatusSubmitOrderRouteDiv("0", "1");
            HostFotypeOrderAllRow l_row = (HostFotypeOrderAllRow)l_lisRows.get(0);
            //次期デリバ対応 執行条件の説明を変更
            //assertEquals("7", l_row.getChangeExecutionCondition());
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (TooLateException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSendCase43()
    {
        final String STR_METHOD_NAME = "testSendCase43()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrderMQDataCode", new Class[] {IfoOrderUnit.class},
            "321");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "isNotifyEndOrder", new Class[] {OrderUnit.class},
            Boolean.FALSE);
        WEB3DefaultMQSendResultForMock l_defaultMQSendResult = WEB3DefaultMQSendResultForMock.newFailedResultInstance(new ErrorInfo());
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mqgateway.stdimpls.WEB3MQGatewayServiceImpl",
            "send", new Class[] {WEB3MQMessageSpec.class},
            l_defaultMQSendResult);
        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
        IfoProductParams l_ifoProductParams = new IfoProductParams();
        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setSubmitMarketTrigger("0");
        
        l_ifoProductParams.setProductId(1006169090018L);
        l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setUnderlyingProductCode("1");
        l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
        l_ifoProductParams.setStrikePrice(11);
        l_ifoProductParams.setMonthOfDelivery("200702");
        l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setProductCode("112");
        l_ifoProductParams.setSplitType("4");
        
        l_hostFotypeOrderAllParams.setInstitutionCode("0D");
        l_hostFotypeOrderAllParams.setBranchCode("381");
        l_hostFotypeOrderAllParams.setOrderRequestNumber("000003006");
        l_hostFotypeOrderAllParams.setCorpCode("11111111111111101111");
        l_hostFotypeOrderAllParams.setStatus("0");
        l_hostFotypeOrderAllParams.setCancelDiv("0");
        l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
        l_hostFotypeOrderAllParams.setFrontOrderSystemCode("2");
        l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("1");
        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRev("01");
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE);
        l_ifoOrderUnitParams.setConfirmedQuantity(222);
        l_ifoOrderUnitParams.setLastOrderActionSerialNo(1);
        l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
        l_ifoOrderUnitParams.setOrderConditionType("1");
        l_ifoOrderUnitParams.setRequestType("2");
        l_ifoOrderUnitParams.setConfirmedExecConditionType(IfoOrderExecutionConditionType.AT_MARKET_OPEN);
        l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.FOK);
        l_ifoOrderUnitParams.setBizDate("20020202");
        l_ifoOrderUnitParams.setSonarTradedCode("2");
        l_ifoOrderUnitParams.setSonarMarketCode("6");
        l_ifoOrderUnitParams.setProductId(1006169090018L);
        l_ifoOrderUnitParams.setConfirmedPrice(new Double(333));
        l_ifoOrderUnitParams.setFutureOptionDiv("2");
        l_ifoOrderUnitParams.setFirstOrderUnitId(1111);
        try
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        IfoClosingContractSpecRow[] l_closingContractRows = new IfoClosingContractSpecParams[1];
        DefaultIfoChangeSettleContractOrderMarketRequestMessage l_request =
            new DefaultIfoChangeSettleContractOrderMarketRequestMessage(
                l_subAccount, l_lngOrderId, l_ifoOrderUnitParams, l_closingContractRows);
        try
        {
            MarketRequestSendResult l_result = l_impl.send(l_request, false);
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());
            List l_lisRows = HostFotypeOrderAllDao.findRowsByStatusSubmitOrderRouteDiv("0", "1");
            HostFotypeOrderAllRow l_row = (HostFotypeOrderAllRow)l_lisRows.get(0);
            //次期デリバ対応 執行条件の説明を変更
            //assertEquals("2", l_row.getChangeExecutionCondition());
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (TooLateException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSendCase44()
    {
        final String STR_METHOD_NAME = "testSendCase44()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrderMQDataCode", new Class[] {IfoOrderUnit.class},
            null);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "isNotifyEndOrder", new Class[] {OrderUnit.class},
            Boolean.FALSE);
        
        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
        IfoProductParams l_ifoProductParams = new IfoProductParams();
        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setSubmitMarketTrigger("0");
        
        l_ifoProductParams.setProductId(1006169090018L);
        l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setUnderlyingProductCode("1");
        l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
        l_ifoProductParams.setStrikePrice(11);
        l_ifoProductParams.setMonthOfDelivery("200702");
        l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setProductCode("112");
        l_ifoProductParams.setSplitType("4");
        
        l_hostFotypeOrderAllParams.setInstitutionCode("0D");
        l_hostFotypeOrderAllParams.setBranchCode("381");
        l_hostFotypeOrderAllParams.setOrderRequestNumber("000003006");
        l_hostFotypeOrderAllParams.setCorpCode("11111111111111101111");
        l_hostFotypeOrderAllParams.setStatus("0");
        l_hostFotypeOrderAllParams.setCancelDiv("0");
        l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
        l_hostFotypeOrderAllParams.setFrontOrderSystemCode("2");
        l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("1");
        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRev("01");
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE);
        l_ifoOrderUnitParams.setConfirmedQuantity(222);
        l_ifoOrderUnitParams.setLastOrderActionSerialNo(1);
        l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
        l_ifoOrderUnitParams.setOrderConditionType("1");
        l_ifoOrderUnitParams.setRequestType("2");
        l_ifoOrderUnitParams.setConfirmedExecConditionType(IfoOrderExecutionConditionType.AT_MARKET_OPEN);
        l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.FOK);
        l_ifoOrderUnitParams.setBizDate("20020202");
        l_ifoOrderUnitParams.setSonarTradedCode("2");
        l_ifoOrderUnitParams.setSonarMarketCode("6");
        l_ifoOrderUnitParams.setProductId(1006169090018L);
        l_ifoOrderUnitParams.setConfirmedPrice(new Double(333));
        l_ifoOrderUnitParams.setFutureOptionDiv("2");
        try
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        IfoClosingContractSpecRow[] l_closingContractRows = new IfoClosingContractSpecParams[1];
        DefaultIfoChangeSettleContractOrderMarketRequestMessage l_request =
            new DefaultIfoChangeSettleContractOrderMarketRequestMessage(
                l_subAccount, l_lngOrderId, l_ifoOrderUnitParams, l_closingContractRows);
        try
        {
            MarketRequestSendResult l_result = l_impl.send(l_request, false);
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());
            List l_lisRows = HostFotypeOrderAllDao.findRowsByStatusSubmitOrderRouteDiv("0", "1");
            HostFotypeOrderAllRow l_row = (HostFotypeOrderAllRow)l_lisRows.get(0);
            //次期デリバ対応 執行条件の説明を変更
            //assertEquals("1", l_row.getChangeExecutionCondition());
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (TooLateException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSendCase45()
    {
        final String STR_METHOD_NAME = "testSendCase45()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrderMQDataCode", new Class[] {IfoOrderUnit.class},
            "321");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "isNotifyEndOrder", new Class[] {OrderUnit.class},
            Boolean.FALSE);
        WEB3DefaultMQSendResultForMock l_defaultMQSendResult = WEB3DefaultMQSendResultForMock.newSuccessResultInstance();
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mqgateway.stdimpls.WEB3MQGatewayServiceImpl",
            "send", new Class[] {WEB3MQMessageSpec.class},
            l_defaultMQSendResult);
        
        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
        IfoProductParams l_ifoProductParams = new IfoProductParams();
        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setSubmitMarketTrigger("1");
        
        l_ifoProductParams.setProductId(1006169090018L);
        l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setUnderlyingProductCode("1");
        l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
        l_ifoProductParams.setStrikePrice(11);
        l_ifoProductParams.setMonthOfDelivery("200702");
        l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setProductCode("112");
        l_ifoProductParams.setSplitType("4");
        
        l_hostFotypeOrderAllParams.setInstitutionCode("0D");
        l_hostFotypeOrderAllParams.setBranchCode("381");
        l_hostFotypeOrderAllParams.setOrderRequestNumber("000003006");
        l_hostFotypeOrderAllParams.setCorpCode("11111111111111101111");
        l_hostFotypeOrderAllParams.setStatus("0");
        l_hostFotypeOrderAllParams.setCancelDiv("0");
        l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
        l_hostFotypeOrderAllParams.setFrontOrderSystemCode("2");
        l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("1");
        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRev("01");
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE);
        l_ifoOrderUnitParams.setConfirmedQuantity(222);
        l_ifoOrderUnitParams.setLastOrderActionSerialNo(1);
        l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
        l_ifoOrderUnitParams.setOrderConditionType("1");
        l_ifoOrderUnitParams.setRequestType("2");
        l_ifoOrderUnitParams.setConfirmedExecConditionType(IfoOrderExecutionConditionType.AT_MARKET_OPEN);
        l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED);
        l_ifoOrderUnitParams.setBizDate("20020202");
        l_ifoOrderUnitParams.setSonarTradedCode("2");
        l_ifoOrderUnitParams.setSonarMarketCode("6");
        l_ifoOrderUnitParams.setProductId(1006169090018L);
        l_ifoOrderUnitParams.setConfirmedPrice(new Double(333));
        l_ifoOrderUnitParams.setFutureOptionDiv("2");
        try
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        IfoClosingContractSpecRow[] l_closingContractRows = new IfoClosingContractSpecParams[1];
        DefaultIfoChangeSettleContractOrderMarketRequestMessage l_request =
            new DefaultIfoChangeSettleContractOrderMarketRequestMessage(
                l_subAccount, l_lngOrderId, l_ifoOrderUnitParams, l_closingContractRows);
        try
        {
            MarketRequestSendResult l_result = l_impl.send(l_request, false);
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (TooLateException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSendCase46()
    {
        final String STR_METHOD_NAME = "testSendCase46()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getChangeCancelMQDataCode", new Class[] {IfoOrderUnit.class},
            "321");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
            "isNotifyEndOrder", new Class[] {OrderUnit.class},
            new Boolean(false));
        WEB3DefaultMQSendResultForMock l_defaultMQSendResult = WEB3DefaultMQSendResultForMock.newFailedResultInstance(new ErrorInfo());
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mqgateway.stdimpls.WEB3MQGatewayServiceImpl",
            "send", new Class[] {WEB3MQMessageSpec.class},
            l_defaultMQSendResult);
        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
        IfoProductParams l_ifoProductParams = new IfoProductParams();
        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        l_orderSwitchingParams.setInstitutionCode("0D");
        l_orderSwitchingParams.setProductType(ProductTypeEnum.IFO);
        l_orderSwitchingParams.setMarketCode("SP");
        l_orderSwitchingParams.setSubmitMqTrigger("1");
        l_orderSwitchingParams.setSubmitOrderRouteDiv("1");
        l_orderSwitchingParams.setFrontOrderSystemCode("9");
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setSubmitMarketTrigger("1");
        
        l_ifoProductParams.setProductId(1006169090018L);
        l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setUnderlyingProductCode("1");
        l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
        l_ifoProductParams.setStrikePrice(11);
        l_ifoProductParams.setMonthOfDelivery("200702");
        l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setProductCode("112");
        l_ifoProductParams.setSplitType("4");
        
        l_hostFotypeOrderAllParams.setInstitutionCode("0D");
        l_hostFotypeOrderAllParams.setBranchCode("381");
        l_hostFotypeOrderAllParams.setOrderRequestNumber("000003006");
        l_hostFotypeOrderAllParams.setCorpCode("11111111111111101111");
        l_hostFotypeOrderAllParams.setStatus("0");
        l_hostFotypeOrderAllParams.setCancelDiv("0");
        l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
        l_hostFotypeOrderAllParams.setFrontOrderSystemCode("2");
        l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("1");
        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRev("01");
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE);
        l_ifoOrderUnitParams.setConfirmedQuantity(222);
        l_ifoOrderUnitParams.setLastOrderActionSerialNo(1);
        l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
        l_ifoOrderUnitParams.setOrderConditionType("2");
        l_ifoOrderUnitParams.setRequestType("2");
        l_ifoOrderUnitParams.setConfirmedExecConditionType(IfoOrderExecutionConditionType.AT_MARKET_OPEN);
        l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.FOK);
        l_ifoOrderUnitParams.setBizDate("20020202");
        l_ifoOrderUnitParams.setSonarTradedCode("2");
        l_ifoOrderUnitParams.setSonarMarketCode("6");
        l_ifoOrderUnitParams.setProductId(1006169090018L);
        l_ifoOrderUnitParams.setConfirmedPrice(new Double(333));
        l_ifoOrderUnitParams.setFutureOptionDiv("2");
        l_ifoOrderUnitParams.setFirstOrderUnitId(1111);
        try
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.deleteAll(OrderSwitchingRow.TYPE);
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        IfoClosingContractSpecRow[] l_closingContractRows = new IfoClosingContractSpecParams[1];
        DefaultIfoChangeSettleContractOrderMarketRequestMessage l_request =
            new DefaultIfoChangeSettleContractOrderMarketRequestMessage(
                l_subAccount, l_lngOrderId, l_ifoOrderUnitParams, l_closingContractRows);
        try
        {
            MarketRequestSendResult l_result = l_impl.send(l_request, false);
            assertFalse(l_result.getProcessingResult().isSuccessfulResult());
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (TooLateException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSendCase47()
    {
        final String STR_METHOD_NAME = "testSendCase47()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrderMQDataCode", new Class[] {IfoOrderUnit.class},
            "321");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
            "isNotifyEndOrder", new Class[] {OrderUnit.class},
            Boolean.TRUE);
        WEB3DefaultMQSendResultForMock l_defaultMQSendResult = WEB3DefaultMQSendResultForMock.newFailedResultInstance(new ErrorInfo());
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mqgateway.stdimpls.WEB3MQGatewayServiceImpl",
            "send", new Class[] {WEB3MQMessageSpec.class},
            l_defaultMQSendResult);
        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
        IfoProductParams l_ifoProductParams = new IfoProductParams();
        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setSubmitMarketTrigger("1");
        
        l_ifoProductParams.setProductId(1006169090018L);
        l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setUnderlyingProductCode("1");
        l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
        l_ifoProductParams.setStrikePrice(11);
        l_ifoProductParams.setMonthOfDelivery("200702");
        l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setProductCode("112");
        l_ifoProductParams.setSplitType("4");
        
        l_hostFotypeOrderAllParams.setInstitutionCode("0D");
        l_hostFotypeOrderAllParams.setBranchCode("381");
        l_hostFotypeOrderAllParams.setOrderRequestNumber("000003006");
        l_hostFotypeOrderAllParams.setCorpCode("11111111111111101111");
        l_hostFotypeOrderAllParams.setStatus("0");
        l_hostFotypeOrderAllParams.setCancelDiv("0");
        l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
        l_hostFotypeOrderAllParams.setFrontOrderSystemCode("2");
        l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("1");
        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRev("01");
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE);
        l_ifoOrderUnitParams.setConfirmedQuantity(222);
        l_ifoOrderUnitParams.setLastOrderActionSerialNo(1);
        l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
        l_ifoOrderUnitParams.setOrderConditionType("1");
        l_ifoOrderUnitParams.setRequestType("2");
        l_ifoOrderUnitParams.setConfirmedExecConditionType(IfoOrderExecutionConditionType.AT_MARKET_OPEN);
        l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.FOK);
        l_ifoOrderUnitParams.setBizDate("20020202");
        l_ifoOrderUnitParams.setSonarTradedCode("2");
        l_ifoOrderUnitParams.setSonarMarketCode("6");
        l_ifoOrderUnitParams.setProductId(1006169090018L);
        l_ifoOrderUnitParams.setConfirmedPrice(new Double(333));
        l_ifoOrderUnitParams.setFutureOptionDiv("2");
        l_ifoOrderUnitParams.setFirstOrderUnitId(1111);
        try
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        IfoClosingContractSpecRow[] l_closingContractRows = new IfoClosingContractSpecParams[1];
        DefaultIfoChangeSettleContractOrderMarketRequestMessage l_request =
            new DefaultIfoChangeSettleContractOrderMarketRequestMessage(
                l_subAccount, l_lngOrderId, l_ifoOrderUnitParams, l_closingContractRows);
        try
        {
            MarketRequestSendResult l_result = l_impl.send(l_request, false);
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (TooLateException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSendCase48()
    {
        final String STR_METHOD_NAME = "testSendCase48()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrderMQDataCode", new Class[] {IfoOrderUnit.class},
            "321");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "isNotifyEndOrder", new Class[] {OrderUnit.class},
            Boolean.FALSE);
        WEB3DefaultMQSendResultForMock l_defaultMQSendResult = WEB3DefaultMQSendResultForMock.newFailedResultInstance(new ErrorInfo());
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mqgateway.stdimpls.WEB3MQGatewayServiceImpl",
            "send", new Class[] {WEB3MQMessageSpec.class},
            l_defaultMQSendResult);
        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
        IfoProductParams l_ifoProductParams = new IfoProductParams();
        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setSubmitMarketTrigger("1");
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("381");
        l_tradingTimeParams.setMarketCode("SP");
        l_tradingTimeParams.setBizdateCalcParameter("0");
        l_tradingTimeParams.setStartTime("000000");
        l_tradingTimeParams.setEndTime("135959");
        
        l_ifoProductParams.setProductId(1006169090018L);
        l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setUnderlyingProductCode("1");
        l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
        l_ifoProductParams.setStrikePrice(11);
        l_ifoProductParams.setMonthOfDelivery("200702");
        l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setProductCode("112");
        l_ifoProductParams.setSplitType("4");
        
        l_hostFotypeOrderAllParams.setInstitutionCode("0D");
        l_hostFotypeOrderAllParams.setBranchCode("381");
        l_hostFotypeOrderAllParams.setOrderRequestNumber("000003006");
        l_hostFotypeOrderAllParams.setCorpCode("11111111111111101111");
        l_hostFotypeOrderAllParams.setStatus("0");
        l_hostFotypeOrderAllParams.setCancelDiv("0");
        l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
        l_hostFotypeOrderAllParams.setFrontOrderSystemCode("2");
        l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("1");
        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRev("01");
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE);
        l_ifoOrderUnitParams.setConfirmedQuantity(222);
        l_ifoOrderUnitParams.setLastOrderActionSerialNo(1);
        l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
        l_ifoOrderUnitParams.setOrderConditionType("1");
        l_ifoOrderUnitParams.setRequestType("2");
        l_ifoOrderUnitParams.setConfirmedExecConditionType(IfoOrderExecutionConditionType.AT_MARKET_OPEN);
        l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.FOK);
        l_ifoOrderUnitParams.setBizDate("20020202");
        l_ifoOrderUnitParams.setSonarTradedCode("2");
        l_ifoOrderUnitParams.setSonarMarketCode("6");
        l_ifoOrderUnitParams.setProductId(1006169090018L);
        l_ifoOrderUnitParams.setConfirmedPrice(new Double(333));
        l_ifoOrderUnitParams.setFutureOptionDiv("2");
        l_ifoOrderUnitParams.setFirstOrderUnitId(1111);
        try
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        IfoClosingContractSpecRow[] l_closingContractRows = new IfoClosingContractSpecParams[1];
        DefaultIfoChangeSettleContractOrderMarketRequestMessage l_request =
            new DefaultIfoChangeSettleContractOrderMarketRequestMessage(
                l_subAccount, l_lngOrderId, l_ifoOrderUnitParams, l_closingContractRows);
        try
        {
            MarketRequestSendResult l_result = l_impl.send(l_request, false);
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (TooLateException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSendCase49()
    {
        final String STR_METHOD_NAME = "testSendCase49()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        DefaultCancelOrderMarketRequestMessage l_request = null;
        try
        {
            l_impl.send(l_request, true);
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            assertEquals("パラメータ値不正。", l_ex.getErrorInfo().getErrorMessage());
        }
        catch (TooLateException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSendCase50()
    {
        final String STR_METHOD_NAME = "testSendCase50()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();

        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        try
        {
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        DefaultCancelOrderMarketRequestMessage l_request =
            new DefaultCancelOrderMarketRequestMessage(
                l_subAccount, l_lngOrderId);
        try
        {
            MarketRequestSendResult l_result = l_impl.send(l_request, true);
            assertFalse(l_result.getProcessingResult().isSuccessfulResult());
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (TooLateException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSendCase51()
    {
        final String STR_METHOD_NAME = "testSendCase51()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
        
        l_hostFotypeOrderAllParams.setInstitutionCode("0D");
        l_hostFotypeOrderAllParams.setBranchCode("381");
        l_hostFotypeOrderAllParams.setOrderRequestNumber("000003006");
        l_hostFotypeOrderAllParams.setCorpCode("11111111111111101111");
        l_hostFotypeOrderAllParams.setStatus("0");
        l_hostFotypeOrderAllParams.setCancelDiv("0");
        l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
        l_hostFotypeOrderAllParams.setFrontOrderSystemCode("2");
        l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("1");
        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRev("01");
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE);
        l_ifoOrderUnitParams.setConfirmedQuantity(222);
        l_ifoOrderUnitParams.setLastOrderActionSerialNo(1);
        l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
        try
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        DefaultCancelOrderMarketRequestMessage l_request =
            new DefaultCancelOrderMarketRequestMessage(
                l_subAccount, l_lngOrderId);
        try
        {
            MarketRequestSendResult l_result = l_impl.send(l_request, true);
            List l_lstRows = HostFotypeOrderAllDao.findRowsByStatusSubmitOrderRouteDiv("0", "1");
            assertEquals(0, l_lstRows.size());
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (TooLateException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSendCase52()
    {
        final String STR_METHOD_NAME = "testSendCase52()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
        
        l_hostFotypeOrderAllParams.setInstitutionCode("0D");
        l_hostFotypeOrderAllParams.setBranchCode("381");
        l_hostFotypeOrderAllParams.setOrderRequestNumber("000003006");
        l_hostFotypeOrderAllParams.setCorpCode("11111111111111101111");
        l_hostFotypeOrderAllParams.setStatus("0");
        l_hostFotypeOrderAllParams.setCancelDiv("0");
        l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
        l_hostFotypeOrderAllParams.setFrontOrderSystemCode("2");
        l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("1");
        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRev("01");
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE);
        l_ifoOrderUnitParams.setConfirmedQuantity(222);
        l_ifoOrderUnitParams.setLastOrderActionSerialNo(1);
        l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
        l_ifoOrderUnitParams.setOrderConditionType("1");
        l_ifoOrderUnitParams.setRequestType("2");
        try
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        DefaultCancelOrderMarketRequestMessage l_request =
            new DefaultCancelOrderMarketRequestMessage(
                l_subAccount, l_lngOrderId);
        try
        {
            MarketRequestSendResult l_result = l_impl.send(l_request, true);
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (TooLateException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSendCase53()
    {
        final String STR_METHOD_NAME = "testSendCase53()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrderMQDataCode", new Class[] {IfoOrderUnit.class},
            "321");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "isNotifyEndOrder", new Class[] {OrderUnit.class},
            Boolean.FALSE);
        WEB3DefaultMQSendResultForMock l_defaultMQSendResult = WEB3DefaultMQSendResultForMock.newSuccessResultInstance();
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mqgateway.stdimpls.WEB3MQGatewayServiceImpl",
            "send", new Class[] {WEB3MQMessageSpec.class},
            l_defaultMQSendResult);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrgCorpCode", new Class[] {IfoOrderUnit.class},
            "11111111111111101111");
        
        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
        IfoProductParams l_ifoProductParams = new IfoProductParams();
        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setSubmitMarketTrigger("0");
        
        l_ifoProductParams.setProductId(1006169090018L);
        l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setUnderlyingProductCode("1");
        l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.FUTURES);
        l_ifoProductParams.setStrikePrice(11);
        l_ifoProductParams.setMonthOfDelivery("200702");
        l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setProductCode("112");
        l_ifoProductParams.setSplitType("4");
        
        l_hostFotypeOrderAllParams.setInstitutionCode("0D");
        l_hostFotypeOrderAllParams.setBranchCode("381");
        l_hostFotypeOrderAllParams.setOrderRequestNumber("000003006");
        l_hostFotypeOrderAllParams.setCorpCode("11111111111111101111");
        l_hostFotypeOrderAllParams.setStatus("0");
        l_hostFotypeOrderAllParams.setCancelDiv("0");
        l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
        l_hostFotypeOrderAllParams.setFrontOrderSystemCode("2");
        l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("1");

        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRev("01");
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE);
        l_ifoOrderUnitParams.setConfirmedQuantity(222);
        l_ifoOrderUnitParams.setLastOrderActionSerialNo(1);
        l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
        l_ifoOrderUnitParams.setOrderConditionType("1");
        l_ifoOrderUnitParams.setRequestType("2");
        l_ifoOrderUnitParams.setConfirmedExecConditionType(IfoOrderExecutionConditionType.AT_MARKET_OPEN);
        l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED);
        l_ifoOrderUnitParams.setBizDate("20020202");
        l_ifoOrderUnitParams.setSonarTradedCode("2");
        l_ifoOrderUnitParams.setSonarMarketCode("6");
        l_ifoOrderUnitParams.setProductId(1006169090018L);
        l_ifoOrderUnitParams.setConfirmedPrice(new Double(333));
        l_ifoOrderUnitParams.setFutureOptionDiv("1");
        try
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        DefaultCancelOrderMarketRequestMessage l_request =
            new DefaultCancelOrderMarketRequestMessage(
                l_subAccount, l_lngOrderId);
        try
        {
            MarketRequestSendResult l_result = l_impl.send(l_request, false);
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());
            List l_lisRows = HostFotypeOrderAllDao.findRowsByStatusSubmitOrderRouteDiv("0", "1");
            HostFotypeOrderAllRow l_row = (HostFotypeOrderAllRow)l_lisRows.get(0);
            assertEquals(WEB3DateUtility.formatDate(
                GtlUtils.getSystemTimestamp(), "yyyyMMdd"),
                WEB3DateUtility.formatDate(l_row.getReceivedDateTime(), "yyyyMMdd"));
            assertEquals(1, l_row.getOrderActionSerialNo());
            assertEquals("1", l_row.getSubmitOrderRouteDiv());
            assertEquals("1", l_row.getCancelDiv());
            assertEquals("11111111111111101111", l_row.getCorpCode());
            assertEquals("11111111111111101111", l_row.getOrgCorpCode());
            assertEquals(WEB3DateUtility.formatDate(
                GtlUtils.getSystemTimestamp(), "yyyyMMdd"),
                WEB3DateUtility.formatDate(l_row.getLastUpdatedTimestamp(), "yyyyMMdd"));
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (TooLateException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSendCase54()
    {
        final String STR_METHOD_NAME = "testSendCase54()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrderMQDataCode", new Class[] {IfoOrderUnit.class},
            null);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "isNotifyEndOrder", new Class[] {OrderUnit.class},
            Boolean.FALSE);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getFrontOrderExchangeCode", new Class[] {String.class},
            "7");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getFrontOrderSystemCode", new Class[] {String.class},
            "8");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrgCorpCode", new Class[] {IfoOrderUnit.class},
            "11111111111111101111");
        
        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
        IfoProductParams l_ifoProductParams = new IfoProductParams();
        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setSubmitMarketTrigger("0");
        
        l_ifoProductParams.setProductId(1006169090018L);
        l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setUnderlyingProductCode("1");
        l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.FUTURES);
        l_ifoProductParams.setStrikePrice(11);
        l_ifoProductParams.setMonthOfDelivery("200702");
        l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setProductCode("112");
        l_ifoProductParams.setSplitType("4");
        
        l_hostFotypeOrderAllParams.setInstitutionCode("0D");
        l_hostFotypeOrderAllParams.setBranchCode("381");
        l_hostFotypeOrderAllParams.setOrderRequestNumber("000003006");
        l_hostFotypeOrderAllParams.setCorpCode("11111111111111101111");
        l_hostFotypeOrderAllParams.setStatus("0");
        l_hostFotypeOrderAllParams.setCancelDiv("0");
        l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
        l_hostFotypeOrderAllParams.setFrontOrderSystemCode("2");
        l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("1");
        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRev("01");
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
        l_ifoOrderUnitParams.setConfirmedQuantity(222);
        l_ifoOrderUnitParams.setLastOrderActionSerialNo(1);
        l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
        l_ifoOrderUnitParams.setOrderConditionType("1");
        l_ifoOrderUnitParams.setRequestType("2");
        l_ifoOrderUnitParams.setConfirmedExecConditionType(IfoOrderExecutionConditionType.AT_MARKET_OPEN);
        l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.AT_MARKET_OPEN);
        SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
        String l_strCreateDate = l_format.format(GtlUtils.getSystemTimestamp());
        l_ifoOrderUnitParams.setBizDate(l_strCreateDate);
        l_ifoOrderUnitParams.setSonarTraderCode("2");
        l_ifoOrderUnitParams.setSonarMarketCode("6");
        l_ifoOrderUnitParams.setProductId(1006169090018L);
        l_ifoOrderUnitParams.setConfirmedPrice(new Double(333));
        try
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        DefaultCancelOrderMarketRequestMessage l_request =
            new DefaultCancelOrderMarketRequestMessage(
                l_subAccount, l_lngOrderId);
        try
        {
            MarketRequestSendResult l_result = l_impl.send(l_request, false);
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());
            List l_lisRows = HostFotypeOrderAllDao.findRowsByStatusSubmitOrderRouteDiv("0", "1");
            HostFotypeOrderAllRow l_row = (HostFotypeOrderAllRow)l_lisRows.get(0);
            System.out.println(l_row.getRequestCode());
            assertEquals("EI804", l_row.getRequestCode());
            System.out.println(l_row.getAccountId());
            assertEquals(101001010010L, l_row.getAccountId());
            System.out.println(l_row.getInstitutionCode());
            assertEquals("0D", l_row.getInstitutionCode());
            System.out.println(l_row.getBranchCode());
            assertEquals("381", l_row.getBranchCode());
            System.out.println(l_row.getAccountCode());
            assertEquals("2512246", l_row.getAccountCode());
            System.out.println(l_row.getTraderCode());
            assertEquals("2", l_row.getTraderCode());
            System.out.println(l_row.getReceivedDateTimeDiv());
            assertEquals("0", l_row.getReceivedDateTimeDiv());
            System.out.println(l_row.getOrderRequestNumber());
            assertEquals("000003006", l_row.getOrderRequestNumber());
            System.out.println(l_row.getSonarMarketCode());
            assertEquals("6", l_row.getSonarMarketCode());
            //次期デリバ対応 執行条件の説明を変更
            //System.out.println(l_row.getProductCode());
            //assertEquals("112", l_row.getProductCode());
            System.out.println(l_row.getReceivedDateTime());
            assertEquals(WEB3DateUtility.formatDate(
                GtlUtils.getSystemTimestamp(), "yyyyMMdd"),
                WEB3DateUtility.formatDate(l_row.getReceivedDateTime(), "yyyyMMdd"));
            System.out.println(l_row.getOrderActionSerialNo());
            assertEquals(1, l_row.getOrderActionSerialNo());
            System.out.println(l_row.getSubmitOrderRouteDiv());
            assertEquals("1", l_row.getSubmitOrderRouteDiv());
            System.out.println(l_row.getTargetProductCode());
            assertEquals("1", l_row.getTargetProductCode());
            System.out.println(l_row.getDeliveryMonthYyyy());
            assertEquals("2007", l_row.getDeliveryMonthYyyy());
            System.out.println(l_row.getDeliveryMonthMm());
            assertEquals("02", l_row.getDeliveryMonthMm());
            System.out.println(l_row.getFutureOptionProductType());
            assertEquals("F", l_row.getFutureOptionProductType());
            System.out.println(l_row.getStrikePrice());
            assertEquals("11", WEB3StringTypeUtility.formatNumber(l_row.getStrikePrice()));
            System.out.println(l_row.getSplitType());
            assertEquals("4", l_row.getSplitType());
            System.out.println(l_row.getSellOrderQuantity());
            assertEquals("0", WEB3StringTypeUtility.formatNumber(l_row.getSellOrderQuantity()));
            System.out.println(l_row.getBuyOrderQuantity());
            assertEquals("222", WEB3StringTypeUtility.formatNumber(l_row.getBuyOrderQuantity()));
            System.out.println(l_row.getLimitPrice());
            assertEquals("333", WEB3StringTypeUtility.formatNumber(l_row.getLimitPrice()));
            //次期デリバ対応 説明を変更
            //System.out.println(l_row.getExecutionCondition());
            //assertEquals("3", l_row.getExecutionCondition());
            System.out.println(l_row.getStopOrderPrice());
            assertTrue(l_row.getStopOrderPriceIsNull());
            System.out.println(l_row.getWLimitPrice());
            assertTrue(l_row.getWLimitPriceIsNull());
            System.out.println(l_row.getTransactionType());
            assertEquals(null, l_row.getTransactionType());
            System.out.println(l_row.getTicketNumber());
            assertEquals(null, l_row.getTicketNumber());
            System.out.println(l_row.getContractCheck());
            assertEquals(null, l_row.getContractCheck());
            System.out.println(l_row.getOrderChanel());
            assertEquals(null, l_row.getOrderChanel());
            System.out.println(l_row.getCommisionNumber());
            assertEquals(null, l_row.getCommisionNumber());
            System.out.println(l_row.getCommisionBranchNumber());
            assertEquals(null, l_row.getCommisionBranchNumber());
            System.out.println(l_row.getCommisionProductCode());
            assertEquals(null, l_row.getCommisionProductCode());
            System.out.println(l_row.getChangeQuantity());
            assertTrue(l_row.getChangeQuantityIsNull());
            System.out.println(l_row.getChangeLimitPrice());
            assertTrue(l_row.getChangeLimitPriceIsNull());
            System.out.println(l_row.getChangeExecutionCondition());
            assertNull(l_row.getChangeExecutionCondition());
            System.out.println(l_row.getChangeStopOrderPrice());
            assertTrue(l_row.getChangeStopOrderPriceIsNull());
            System.out.println(l_row.getChangeWLimitPrice());
            assertTrue(l_row.getChangeWLimitPriceIsNull());
            System.out.println(l_row.getCancelDiv());
            assertEquals("1", l_row.getCancelDiv());
            System.out.println(l_row.getFrontOrderExchangeCode());
            assertEquals("7", l_row.getFrontOrderExchangeCode());
            System.out.println(l_row.getFrontOrderSystemCode());
            assertEquals("8", l_row.getFrontOrderSystemCode());
            System.out.println(l_row.getFrontOrderTradeCode());
            assertEquals("1", l_row.getFrontOrderTradeCode());
            System.out.println(l_row.getTradeauditCode());
            assertEquals(null, l_row.getTradeauditCode());
            System.out.println(l_row.getCorpCode());
            assertEquals("11111111111111101111", l_row.getCorpCode());
            System.out.println(l_row.getOrgCorpCode());
            assertEquals("11111111111111101111", l_row.getOrgCorpCode());
            System.out.println(l_row.getVirtualServerNumberJsoes());
            assertEquals(null, l_row.getVirtualServerNumberJsoes());
            System.out.println(l_row.getMarketOrderNumber());
            assertTrue(l_row.getMarketOrderNumberIsNull());
            System.out.println(l_row.getAmgSendTime());
            assertEquals(null, l_row.getAmgSendTime());
            System.out.println(l_row.getAmgAckTime());
            assertEquals(null, l_row.getAmgAckTime());
            System.out.println(l_row.getMarketAckTime());
            assertEquals(null, l_row.getMarketAckTime());
            System.out.println(l_row.getAllOrderChangeDiv());
            assertEquals("0", l_row.getAllOrderChangeDiv());
            System.out.println(l_row.getMarketCloseFlag());
            assertEquals("0", l_row.getMarketCloseFlag());
            System.out.println(l_row.getStatus());
            assertEquals("0", l_row.getStatus());
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (TooLateException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSendCase55()
    {
        final String STR_METHOD_NAME = "testSendCase55()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrderMQDataCode", new Class[] {IfoOrderUnit.class},
            null);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "isNotifyEndOrder", new Class[] {OrderUnit.class},
            Boolean.FALSE);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrgCorpCode", new Class[] {IfoOrderUnit.class},
            "11111111111111101111");

        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
        IfoProductParams l_ifoProductParams = new IfoProductParams();
        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setSubmitMarketTrigger("0");
        
        l_ifoProductParams.setProductId(1006169090018L);
        l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setUnderlyingProductCode("1");
        l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.CALL_OPTIONS);
        l_ifoProductParams.setStrikePrice(11);
        l_ifoProductParams.setMonthOfDelivery("200702");
        l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setProductCode("112");
        l_ifoProductParams.setSplitType("4");
        
        l_hostFotypeOrderAllParams.setInstitutionCode("0D");
        l_hostFotypeOrderAllParams.setBranchCode("381");
        l_hostFotypeOrderAllParams.setOrderRequestNumber("000003006");
        l_hostFotypeOrderAllParams.setCorpCode("11111111111111101111");
        l_hostFotypeOrderAllParams.setStatus("0");
        l_hostFotypeOrderAllParams.setCancelDiv("0");
        l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
        l_hostFotypeOrderAllParams.setFrontOrderSystemCode("2");
        l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("1");
        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRev("01");
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
        l_ifoOrderUnitParams.setConfirmedQuantity(222);
        l_ifoOrderUnitParams.setLastOrderActionSerialNo(1);
        l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
        l_ifoOrderUnitParams.setOrderConditionType("1");
        l_ifoOrderUnitParams.setRequestType("2");
        l_ifoOrderUnitParams.setConfirmedExecConditionType(IfoOrderExecutionConditionType.AT_MARKET_OPEN);
        l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.AT_MARKET_CLOSE);
        l_ifoOrderUnitParams.setBizDate("20020202");
        l_ifoOrderUnitParams.setSonarTradedCode("2");
        l_ifoOrderUnitParams.setSonarMarketCode("6");
        l_ifoOrderUnitParams.setProductId(1006169090018L);
        l_ifoOrderUnitParams.setConfirmedPrice(new Double(333));
        l_ifoOrderUnitParams.setFutureOptionDiv("2");
        try
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        DefaultCancelOrderMarketRequestMessage l_request =
            new DefaultCancelOrderMarketRequestMessage(
                l_subAccount, l_lngOrderId);
        try
        {
            MarketRequestSendResult l_result = l_impl.send(l_request, false);
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());
            List l_lisRows = HostFotypeOrderAllDao.findRowsByStatusSubmitOrderRouteDiv("0", "1");
            HostFotypeOrderAllRow l_row = (HostFotypeOrderAllRow)l_lisRows.get(0);
            System.out.println(l_row.getRequestCode());
            assertEquals("EI802", l_row.getRequestCode());
            System.out.println(l_row.getReceivedDateTimeDiv());
            assertEquals("1", l_row.getReceivedDateTimeDiv());
                     System.out.println(l_row.getFutureOptionProductType());
            assertEquals("C", l_row.getFutureOptionProductType());
            System.out.println(l_row.getBuyOrderQuantity());
            assertEquals("222", WEB3StringTypeUtility.formatNumber(l_row.getBuyOrderQuantity()));
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (TooLateException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSendCase56()
    {
        final String STR_METHOD_NAME = "testSendCase56()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrderMQDataCode", new Class[] {IfoOrderUnit.class},
            "321");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "isNotifyEndOrder", new Class[] {OrderUnit.class},
            Boolean.FALSE);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrgCorpCode", new Class[] {IfoOrderUnit.class},
            "11111111111111101111");
        
        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
        IfoProductParams l_ifoProductParams = new IfoProductParams();
        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setSubmitMarketTrigger("0");
        
        l_ifoProductParams.setProductId(1006169090018L);
        l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setUnderlyingProductCode("1");
        l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
        l_ifoProductParams.setStrikePrice(11);
        l_ifoProductParams.setMonthOfDelivery("200702");
        l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setProductCode("112");
        l_ifoProductParams.setSplitType("4");
        
        l_hostFotypeOrderAllParams.setInstitutionCode("0D");
        l_hostFotypeOrderAllParams.setBranchCode("381");
        l_hostFotypeOrderAllParams.setOrderRequestNumber("000003006");
        l_hostFotypeOrderAllParams.setCorpCode("11111111111111101111");
        l_hostFotypeOrderAllParams.setStatus("0");
        l_hostFotypeOrderAllParams.setCancelDiv("0");
        l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
        l_hostFotypeOrderAllParams.setFrontOrderSystemCode("2");
        l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("1");
        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRev("01");
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN);
        l_ifoOrderUnitParams.setConfirmedQuantity(222);
        l_ifoOrderUnitParams.setLastOrderActionSerialNo(1);
        l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
        l_ifoOrderUnitParams.setOrderConditionType("1");
        l_ifoOrderUnitParams.setRequestType("2");
        l_ifoOrderUnitParams.setConfirmedExecConditionType(IfoOrderExecutionConditionType.AT_MARKET_OPEN);
        l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.AT_MARKET_CLOSE);
        l_ifoOrderUnitParams.setBizDate("20020202");
        l_ifoOrderUnitParams.setSonarTradedCode("2");
        l_ifoOrderUnitParams.setSonarMarketCode("6");
        l_ifoOrderUnitParams.setProductId(1006169090018L);
        l_ifoOrderUnitParams.setConfirmedPrice(new Double(333));
        l_ifoOrderUnitParams.setFutureOptionDiv("2");
        try
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        DefaultCancelOrderMarketRequestMessage l_request =
            new DefaultCancelOrderMarketRequestMessage(
                l_subAccount, l_lngOrderId);
        try
        {
            MarketRequestSendResult l_result = l_impl.send(l_request, false);
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());
            List l_lisRows = HostFotypeOrderAllDao.findRowsByStatusSubmitOrderRouteDiv("0", "1");
            HostFotypeOrderAllRow l_row = (HostFotypeOrderAllRow)l_lisRows.get(0);
            assertEquals("P", l_row.getFutureOptionProductType());
            assertEquals("222", WEB3StringTypeUtility.formatNumber(l_row.getSellOrderQuantity()));
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (TooLateException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSendCase57()
    {
        final String STR_METHOD_NAME = "testSendCase57()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrderMQDataCode", new Class[] {IfoOrderUnit.class},
            "321");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "isNotifyEndOrder", new Class[] {OrderUnit.class},
            Boolean.FALSE);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrgCorpCode", new Class[] {IfoOrderUnit.class},
            "11111111111111101111");
        
        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
        IfoProductParams l_ifoProductParams = new IfoProductParams();
        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setSubmitMarketTrigger("0");
        
        l_ifoProductParams.setProductId(1006169090018L);
        l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setUnderlyingProductCode("1");
        l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
        l_ifoProductParams.setStrikePrice(11);
        l_ifoProductParams.setMonthOfDelivery("200702");
        l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setProductCode("112");
        l_ifoProductParams.setSplitType("4");
        
        l_hostFotypeOrderAllParams.setInstitutionCode("0D");
        l_hostFotypeOrderAllParams.setBranchCode("381");
        l_hostFotypeOrderAllParams.setOrderRequestNumber("000003006");
        l_hostFotypeOrderAllParams.setCorpCode("11111111111111101111");
        l_hostFotypeOrderAllParams.setStatus("0");
        l_hostFotypeOrderAllParams.setCancelDiv("0");
        l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
        l_hostFotypeOrderAllParams.setFrontOrderSystemCode("2");
        l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("1");
        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRev("01");
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN);
        l_ifoOrderUnitParams.setConfirmedQuantity(222);
        l_ifoOrderUnitParams.setLastOrderActionSerialNo(1);
        l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
        l_ifoOrderUnitParams.setOrderConditionType("1");
        l_ifoOrderUnitParams.setRequestType("2");
        l_ifoOrderUnitParams.setConfirmedExecConditionType(IfoOrderExecutionConditionType.AT_MARKET_OPEN);
        l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.AT_MARKET_CLOSE);
        l_ifoOrderUnitParams.setBizDate("20020202");
        l_ifoOrderUnitParams.setSonarTradedCode("2");
        l_ifoOrderUnitParams.setSonarMarketCode("6");
        l_ifoOrderUnitParams.setProductId(1006169090018L);
        l_ifoOrderUnitParams.setConfirmedPrice(new Double(333));
        l_ifoOrderUnitParams.setFutureOptionDiv("2");
        try
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        DefaultCancelOrderMarketRequestMessage l_request =
            new DefaultCancelOrderMarketRequestMessage(
                l_subAccount, l_lngOrderId);
        try
        {
            MarketRequestSendResult l_result = l_impl.send(l_request, false);
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());
            List l_lisRows = HostFotypeOrderAllDao.findRowsByStatusSubmitOrderRouteDiv("0", "1");
            HostFotypeOrderAllRow l_row = (HostFotypeOrderAllRow)l_lisRows.get(0);
            assertEquals("222", WEB3StringTypeUtility.formatNumber(l_row.getSellOrderQuantity()));
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (TooLateException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSendCase58()
    {
        final String STR_METHOD_NAME = "testSendCase58()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrderMQDataCode", new Class[] {IfoOrderUnit.class},
            null);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "isNotifyEndOrder", new Class[] {OrderUnit.class},
            Boolean.FALSE);
        WEB3DefaultMQSendResultForMock l_defaultMQSendResult =
            WEB3DefaultMQSendResultForMock.newSuccessResultInstance();
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mqgateway.stdimpls.WEB3MQGatewayServiceImpl",
            "send", new Class[] {WEB3MQMessageSpec.class},
            l_defaultMQSendResult);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrgCorpCode", new Class[] {IfoOrderUnit.class},
            "11111111111111101111");
        
        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
        IfoProductParams l_ifoProductParams = new IfoProductParams();
        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setSubmitMarketTrigger("1");
        
        l_ifoProductParams.setProductId(1006169090018L);
        l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setUnderlyingProductCode("1");
        l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
        l_ifoProductParams.setStrikePrice(11);
        l_ifoProductParams.setMonthOfDelivery("200702");
        l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setProductCode("112");
        l_ifoProductParams.setSplitType("4");
        
        l_hostFotypeOrderAllParams.setInstitutionCode("0D");
        l_hostFotypeOrderAllParams.setBranchCode("381");
        l_hostFotypeOrderAllParams.setOrderRequestNumber("000003006");
        l_hostFotypeOrderAllParams.setCorpCode("11111111111111101111");
        l_hostFotypeOrderAllParams.setStatus("0");
        l_hostFotypeOrderAllParams.setCancelDiv("0");
        l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
        l_hostFotypeOrderAllParams.setFrontOrderSystemCode("2");
        l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("1");
        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRev("01");
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN);
        l_ifoOrderUnitParams.setConfirmedQuantity(222);
        l_ifoOrderUnitParams.setLastOrderActionSerialNo(1);
        l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
        l_ifoOrderUnitParams.setOrderConditionType("2");
        l_ifoOrderUnitParams.setRequestType("2");
        l_ifoOrderUnitParams.setConfirmedExecConditionType(IfoOrderExecutionConditionType.AT_MARKET_OPEN);
        l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.AT_MARKET_CLOSE);
        l_ifoOrderUnitParams.setBizDate("20020202");
        l_ifoOrderUnitParams.setSonarTradedCode("2");
        l_ifoOrderUnitParams.setSonarMarketCode("6");
        l_ifoOrderUnitParams.setProductId(1006169090018L);
        l_ifoOrderUnitParams.setConfirmedPrice(new Double(333));
        l_ifoOrderUnitParams.setFutureOptionDiv("2");
        try
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        DefaultCancelOrderMarketRequestMessage l_request =
            new DefaultCancelOrderMarketRequestMessage(
                l_subAccount, l_lngOrderId);
        try
        {
            MarketRequestSendResult l_result = l_impl.send(l_request, false);
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());
            List l_lisRows = HostFotypeOrderAllDao.findRowsByStatusSubmitOrderRouteDiv("0", "1");
            HostFotypeOrderAllRow l_row = (HostFotypeOrderAllRow)l_lisRows.get(0);
            assertEquals("222", WEB3StringTypeUtility.formatNumber(l_row.getSellOrderQuantity()));
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (TooLateException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSendCase59()
    {
        final String STR_METHOD_NAME = "testSendCase59()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getChangeCancelMQDataCode", new Class[] {IfoOrderUnit.class},
            "321");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "isNotifyEndOrder", new Class[] {OrderUnit.class},
            Boolean.FALSE);
        WEB3DefaultMQSendResultForMock l_defaultMQSendResult =
            WEB3DefaultMQSendResultForMock.newFailedResultInstance(new ErrorInfo());
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mqgateway.stdimpls.WEB3MQGatewayServiceImpl",
            "send", new Class[] {WEB3MQMessageSpec.class},
            l_defaultMQSendResult);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrgCorpCode", new Class[] {IfoOrderUnit.class},
            "11111111111111101111");
        
        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
        IfoProductParams l_ifoProductParams = new IfoProductParams();
        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setSubmitMarketTrigger("1");
        
        l_ifoProductParams.setProductId(1006169090018L);
        l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setUnderlyingProductCode("1");
        l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
        l_ifoProductParams.setStrikePrice(11);
        l_ifoProductParams.setMonthOfDelivery("200702");
        l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setProductCode("112");
        l_ifoProductParams.setSplitType("4");
        
        l_hostFotypeOrderAllParams.setInstitutionCode("0D");
        l_hostFotypeOrderAllParams.setBranchCode("381");
        l_hostFotypeOrderAllParams.setOrderRequestNumber("000003006");
        l_hostFotypeOrderAllParams.setCorpCode("11111111111111101111");
        l_hostFotypeOrderAllParams.setStatus("0");
        l_hostFotypeOrderAllParams.setCancelDiv("0");
        l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
        l_hostFotypeOrderAllParams.setFrontOrderSystemCode("2");
        l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("1");
        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRev("01");
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN);
        l_ifoOrderUnitParams.setConfirmedQuantity(222);
        l_ifoOrderUnitParams.setLastOrderActionSerialNo(1);
        l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
        l_ifoOrderUnitParams.setOrderConditionType("2");
        l_ifoOrderUnitParams.setRequestType("2");
        l_ifoOrderUnitParams.setConfirmedExecConditionType(IfoOrderExecutionConditionType.AT_MARKET_OPEN);
        l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.AT_MARKET_CLOSE);
        l_ifoOrderUnitParams.setBizDate("20020202");
        l_ifoOrderUnitParams.setSonarTradedCode("2");
        l_ifoOrderUnitParams.setSonarMarketCode("6");
        l_ifoOrderUnitParams.setProductId(1006169090018L);
        l_ifoOrderUnitParams.setConfirmedPrice(new Double(333));
        l_ifoOrderUnitParams.setFutureOptionDiv("2");
        try
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        DefaultCancelOrderMarketRequestMessage l_request =
            new DefaultCancelOrderMarketRequestMessage(
                l_subAccount, l_lngOrderId);
        try
        {
            MarketRequestSendResult l_result = l_impl.send(l_request, false);
            assertFalse(l_result.getProcessingResult().isSuccessfulResult());
            List l_lisRows = HostFotypeOrderAllDao.findRowsByStatusSubmitOrderRouteDiv("0", "1");
            HostFotypeOrderAllRow l_row = (HostFotypeOrderAllRow)l_lisRows.get(0);
            assertEquals("222", WEB3StringTypeUtility.formatNumber(l_row.getSellOrderQuantity()));
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (TooLateException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSendCase60()
    {
        final String STR_METHOD_NAME = "testSendCase60()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrderMQDataCode", new Class[] {IfoOrderUnit.class},
            "321");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "isNotifyEndOrder", new Class[] {OrderUnit.class},
            Boolean.FALSE);
        WEB3DefaultMQSendResultForMock l_defaultMQSendResult =
            WEB3DefaultMQSendResultForMock.newSuccessResultInstance();
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mqgateway.stdimpls.WEB3MQGatewayServiceImpl",
            "send", new Class[] {WEB3MQMessageSpec.class},
            l_defaultMQSendResult);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrgCorpCode", new Class[] {IfoOrderUnit.class},
            "11111111111111101111");
        
        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
        IfoProductParams l_ifoProductParams = new IfoProductParams();
        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setSubmitMarketTrigger("1");
        
        l_ifoProductParams.setProductId(1006169090018L);
        l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setUnderlyingProductCode("1");
        l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
        l_ifoProductParams.setStrikePrice(11);
        l_ifoProductParams.setMonthOfDelivery("200702");
        l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setProductCode("112");
        l_ifoProductParams.setSplitType("4");
        
        l_hostFotypeOrderAllParams.setInstitutionCode("0D");
        l_hostFotypeOrderAllParams.setBranchCode("381");
        l_hostFotypeOrderAllParams.setOrderRequestNumber("000003006");
        l_hostFotypeOrderAllParams.setCorpCode("11111111111111101111");
        l_hostFotypeOrderAllParams.setStatus("0");
        l_hostFotypeOrderAllParams.setCancelDiv("0");
        l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
        l_hostFotypeOrderAllParams.setFrontOrderSystemCode("2");
        l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("1");
        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRev("01");
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN);
        l_ifoOrderUnitParams.setConfirmedQuantity(222);
        l_ifoOrderUnitParams.setLastOrderActionSerialNo(1);
        l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
        l_ifoOrderUnitParams.setOrderConditionType("2");
        l_ifoOrderUnitParams.setRequestType("2");
        l_ifoOrderUnitParams.setConfirmedExecConditionType(IfoOrderExecutionConditionType.AT_MARKET_OPEN);
        l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.AT_MARKET_CLOSE);
        l_ifoOrderUnitParams.setBizDate("20020202");
        l_ifoOrderUnitParams.setSonarTradedCode("2");
        l_ifoOrderUnitParams.setSonarMarketCode("6");
        l_ifoOrderUnitParams.setProductId(1006169090018L);
        l_ifoOrderUnitParams.setConfirmedPrice(new Double(333));
        l_ifoOrderUnitParams.setFutureOptionDiv("2");
        try
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        DefaultCancelOrderMarketRequestMessage l_request =
            new DefaultCancelOrderMarketRequestMessage(
                l_subAccount, l_lngOrderId);
        try
        {
            MarketRequestSendResult l_result = l_impl.send(l_request, false);
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());
            List l_lisRows = HostFotypeOrderAllDao.findRowsByStatusSubmitOrderRouteDiv("0", "1");
            HostFotypeOrderAllRow l_row = (HostFotypeOrderAllRow)l_lisRows.get(0);
            assertEquals("222", WEB3StringTypeUtility.formatNumber(l_row.getSellOrderQuantity()));
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (TooLateException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSendCase61()
    {
        final String STR_METHOD_NAME = "testSendCase61()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrderMQDataCode", new Class[] {IfoOrderUnit.class},
            "321");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "isNotifyEndOrder", new Class[] {OrderUnit.class},
            Boolean.FALSE);
        WEB3DefaultMQSendResultForMock l_defaultMQSendResult =
            WEB3DefaultMQSendResultForMock.newSuccessResultInstance();
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mqgateway.stdimpls.WEB3MQGatewayServiceImpl",
            "send", new Class[] {WEB3MQMessageSpec.class},
            l_defaultMQSendResult);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrgCorpCode", new Class[] {IfoOrderUnit.class},
            "11111111111111101111");
        
        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
        IfoProductParams l_ifoProductParams = new IfoProductParams();
        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setSubmitMarketTrigger("1");
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("381");
        l_tradingTimeParams.setMarketCode("SP");
        l_tradingTimeParams.setBizdateCalcParameter("0");
        l_tradingTimeParams.setStartTime("000000");
        l_tradingTimeParams.setEndTime("135959");
        
        l_ifoProductParams.setProductId(1006169090018L);
        l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setUnderlyingProductCode("1");
        l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
        l_ifoProductParams.setStrikePrice(11);
        l_ifoProductParams.setMonthOfDelivery("200702");
        l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setProductCode("112");
        l_ifoProductParams.setSplitType("4");
        
        l_hostFotypeOrderAllParams.setInstitutionCode("0D");
        l_hostFotypeOrderAllParams.setBranchCode("381");
        l_hostFotypeOrderAllParams.setOrderRequestNumber("000003006");
        l_hostFotypeOrderAllParams.setCorpCode("11111111111111101111");
        l_hostFotypeOrderAllParams.setStatus("0");
        l_hostFotypeOrderAllParams.setCancelDiv("0");
        l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
        l_hostFotypeOrderAllParams.setFrontOrderSystemCode("2");
        l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("1");
        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRev("01");
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN);
        l_ifoOrderUnitParams.setConfirmedQuantity(222);
        l_ifoOrderUnitParams.setLastOrderActionSerialNo(1);
        l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
        l_ifoOrderUnitParams.setOrderConditionType("1");
        l_ifoOrderUnitParams.setRequestType("2");
        l_ifoOrderUnitParams.setConfirmedExecConditionType(IfoOrderExecutionConditionType.AT_MARKET_OPEN);
        l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.AT_MARKET_CLOSE);
        l_ifoOrderUnitParams.setBizDate("20020202");
        l_ifoOrderUnitParams.setSonarTradedCode("2");
        l_ifoOrderUnitParams.setSonarMarketCode("6");
        l_ifoOrderUnitParams.setProductId(1006169090018L);
        l_ifoOrderUnitParams.setConfirmedPrice(new Double(333));
        l_ifoOrderUnitParams.setFutureOptionDiv("2");
        try
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        DefaultCancelOrderMarketRequestMessage l_request =
            new DefaultCancelOrderMarketRequestMessage(
                l_subAccount, l_lngOrderId);
        try
        {
            MarketRequestSendResult l_result = l_impl.send(l_request, false);
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (TooLateException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSendCase62()
    {
        final String STR_METHOD_NAME = "testSendCase62()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrderMQDataCode", new Class[] {IfoOrderUnit.class},
            "321");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "isNotifyEndOrder", new Class[] {OrderUnit.class},
            Boolean.FALSE);
        WEB3DefaultMQSendResultForMock l_defaultMQSendResult =
            WEB3DefaultMQSendResultForMock.newSuccessResultInstance();
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mqgateway.stdimpls.WEB3MQGatewayServiceImpl",
            "send", new Class[] {WEB3MQMessageSpec.class},
            l_defaultMQSendResult);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrgCorpCode", new Class[] {IfoOrderUnit.class},
            "11111111111111101111");
        
        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
        IfoProductParams l_ifoProductParams = new IfoProductParams();
        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setSubmitMarketTrigger("1");
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("381");
        l_tradingTimeParams.setMarketCode("SP");
        l_tradingTimeParams.setBizdateCalcParameter("0");
        l_tradingTimeParams.setStartTime("000000");
        l_tradingTimeParams.setEndTime("135959");
        
        l_ifoProductParams.setProductId(1006169090018L);
        l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setUnderlyingProductCode("1");
        l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
        l_ifoProductParams.setStrikePrice(11);
        l_ifoProductParams.setMonthOfDelivery("200702");
        l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setProductCode("112");
        l_ifoProductParams.setSplitType("4");
        
        l_hostFotypeOrderAllParams.setInstitutionCode("0D");
        l_hostFotypeOrderAllParams.setBranchCode("381");
        l_hostFotypeOrderAllParams.setOrderRequestNumber("000003006");
        l_hostFotypeOrderAllParams.setCorpCode("11111111111111101111");
        l_hostFotypeOrderAllParams.setStatus("0");
        l_hostFotypeOrderAllParams.setCancelDiv("0");
        l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
        l_hostFotypeOrderAllParams.setFrontOrderSystemCode("2");
        l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("1");
        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRev("01");
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN);
        l_ifoOrderUnitParams.setConfirmedQuantity(222);
        l_ifoOrderUnitParams.setLastOrderActionSerialNo(1);
        l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
        l_ifoOrderUnitParams.setOrderConditionType("1");
        l_ifoOrderUnitParams.setRequestType("2");
        l_ifoOrderUnitParams.setConfirmedExecConditionType(IfoOrderExecutionConditionType.AT_MARKET_OPEN);
        l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.AT_MARKET_CLOSE);
        l_ifoOrderUnitParams.setBizDate("20020202");
        l_ifoOrderUnitParams.setSonarTradedCode("2");
        l_ifoOrderUnitParams.setSonarMarketCode("6");
        l_ifoOrderUnitParams.setProductId(1006169090018L);
        l_ifoOrderUnitParams.setConfirmedPrice(new Double(333));
        l_ifoOrderUnitParams.setFutureOptionDiv("2");
        try
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        DefaultCancelOrderMarketRequestMessage l_request =
            new DefaultCancelOrderMarketRequestMessage(
                l_subAccount, l_lngOrderId);
        try
        {
            MarketRequestSendResult l_result = l_impl.send(l_request, false);
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (TooLateException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSendCase63()
    {
        final String STR_METHOD_NAME = "testSendCase63()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrderMQDataCode", new Class[] {IfoOrderUnit.class},
            "321");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "isNotifyEndOrder", new Class[] {OrderUnit.class},
            Boolean.FALSE);
        WEB3DefaultMQSendResultForMock l_defaultMQSendResult = WEB3DefaultMQSendResultForMock.newSuccessResultInstance();
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mqgateway.stdimpls.WEB3MQGatewayServiceImpl",
            "send", new Class[] {WEB3MQMessageSpec.class},
            l_defaultMQSendResult);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrgCorpCode", new Class[] {IfoOrderUnit.class},
            "11111111111111101111");
        
        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
//        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
        IfoProductParams l_ifoProductParams = new IfoProductParams();
        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setSubmitMarketTrigger("0");
        
        l_ifoProductParams.setProductId(1006169090018L);
        l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setUnderlyingProductCode("1");
        l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.FUTURES);
        l_ifoProductParams.setStrikePrice(11);
        l_ifoProductParams.setMonthOfDelivery("200702");
        l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setProductCode("112");
        l_ifoProductParams.setSplitType("4");

        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRev("01");
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE);
        l_ifoOrderUnitParams.setConfirmedQuantity(222);
        l_ifoOrderUnitParams.setLastOrderActionSerialNo(1);
        l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
        l_ifoOrderUnitParams.setOrderConditionType("1");
        l_ifoOrderUnitParams.setRequestType("2");
        l_ifoOrderUnitParams.setConfirmedExecConditionType(IfoOrderExecutionConditionType.AT_MARKET_OPEN);
        l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED);
        l_ifoOrderUnitParams.setBizDate("20020202");
        l_ifoOrderUnitParams.setSonarTradedCode("2");
        l_ifoOrderUnitParams.setSonarMarketCode("6");
        l_ifoOrderUnitParams.setProductId(1006169090018L);
        l_ifoOrderUnitParams.setConfirmedPrice(new Double(333));
        l_ifoOrderUnitParams.setFutureOptionDiv("1");
        //指値注文の場合
        l_ifoOrderUnitParams.setConfirmedPrice(12);
        l_ifoOrderUnitParams.setAcceptNumber("100001");
        l_ifoOrderUnitParams.setBuySellDiv("2");
        l_ifoOrderUnitParams.setProductCode("600001010");
        
        try
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        DefaultCancelOrderMarketRequestMessage l_request =
            new DefaultCancelOrderMarketRequestMessage(
                l_subAccount, l_lngOrderId);
        try
        {
            MarketRequestSendResult l_result = l_impl.send(l_request, false);
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());
            List l_lisRows = HostFotypeOrderAllDao.findRowsByStatusSubmitOrderRouteDiv("0", "1");
            HostFotypeOrderAllRow l_row = (HostFotypeOrderAllRow)l_lisRows.get(0);

            assertEquals("A", l_row.getExecutionCondition());
            assertEquals("100001", l_row.getAcceptNumber());
            assertEquals("2", l_row.getBuySellDiv());
            assertEquals("600001010", l_row.getProductCode());
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (TooLateException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSendCase64()
    {
        final String STR_METHOD_NAME = "testSendCase64()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrderMQDataCode", new Class[] {IfoOrderUnit.class},
            "321");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "isNotifyEndOrder", new Class[] {OrderUnit.class},
            Boolean.FALSE);
        WEB3DefaultMQSendResultForMock l_defaultMQSendResult = WEB3DefaultMQSendResultForMock.newSuccessResultInstance();
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mqgateway.stdimpls.WEB3MQGatewayServiceImpl",
            "send", new Class[] {WEB3MQMessageSpec.class},
            l_defaultMQSendResult);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrgCorpCode", new Class[] {IfoOrderUnit.class},
            "11111111111111101111");
        
        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        IfoProductParams l_ifoProductParams = new IfoProductParams();
        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setSubmitMarketTrigger("0");
        
        l_ifoProductParams.setProductId(1006169090018L);
        l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setUnderlyingProductCode("1");
        l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.FUTURES);
        l_ifoProductParams.setStrikePrice(11);
        l_ifoProductParams.setMonthOfDelivery("200702");
        l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setProductCode("112");
        l_ifoProductParams.setSplitType("4");

        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRev("01");
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE);
        l_ifoOrderUnitParams.setConfirmedQuantity(222);
        l_ifoOrderUnitParams.setLastOrderActionSerialNo(1);
        l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
        l_ifoOrderUnitParams.setOrderConditionType("1");
        l_ifoOrderUnitParams.setRequestType("2");
        l_ifoOrderUnitParams.setConfirmedExecConditionType(IfoOrderExecutionConditionType.AT_MARKET_OPEN);
        l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED);
        l_ifoOrderUnitParams.setBizDate("20020202");
        l_ifoOrderUnitParams.setSonarTradedCode("2");
        l_ifoOrderUnitParams.setSonarMarketCode("6");
        l_ifoOrderUnitParams.setProductId(1006169090018L);
        l_ifoOrderUnitParams.setConfirmedPrice(new Double(333));
        l_ifoOrderUnitParams.setFutureOptionDiv("1");
        //成行注文の場合
        l_ifoOrderUnitParams.setConfirmedPrice(0);
        l_ifoOrderUnitParams.setAcceptNumber("100002");
        l_ifoOrderUnitParams.setBuySellDiv("1");
        
        try
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        DefaultCancelOrderMarketRequestMessage l_request =
            new DefaultCancelOrderMarketRequestMessage(
                l_subAccount, l_lngOrderId);
        try
        {
            MarketRequestSendResult l_result = l_impl.send(l_request, false);
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());
            List l_lisRows = HostFotypeOrderAllDao.findRowsByStatusSubmitOrderRouteDiv("0", "1");
            HostFotypeOrderAllRow l_row = (HostFotypeOrderAllRow)l_lisRows.get(0);

            assertEquals("D", l_row.getExecutionCondition());
            assertEquals("100002", l_row.getAcceptNumber());
            assertEquals("1", l_row.getBuySellDiv());

        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (TooLateException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSendCase65()
    {
        final String STR_METHOD_NAME = "testSendCase65()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrderMQDataCode", new Class[] {IfoOrderUnit.class},
            null);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "isNotifyEndOrder", new Class[] {OrderUnit.class},
            Boolean.FALSE);
        
        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
        IfoProductParams l_ifoProductParams = new IfoProductParams();
        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setSubmitMarketTrigger("0");
        
        l_ifoProductParams.setProductId(1006169090018L);
        l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setUnderlyingProductCode("1");
        l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
        l_ifoProductParams.setStrikePrice(11);
        l_ifoProductParams.setMonthOfDelivery("200702");
        l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setProductCode("112");
        l_ifoProductParams.setSplitType("4");
        
        l_hostFotypeOrderAllParams.setInstitutionCode("0D");
        l_hostFotypeOrderAllParams.setBranchCode("381");
        l_hostFotypeOrderAllParams.setOrderRequestNumber("000003006");
        l_hostFotypeOrderAllParams.setCorpCode("11111111111111101111");
        l_hostFotypeOrderAllParams.setStatus("0");
        l_hostFotypeOrderAllParams.setCancelDiv("0");
        l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
        l_hostFotypeOrderAllParams.setFrontOrderSystemCode("2");
        l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("1");
        l_hostFotypeOrderAllParams.setExecutionCondition("A");
        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRev("01");
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
        l_ifoOrderUnitParams.setConfirmedQuantity(222);
        l_ifoOrderUnitParams.setLastOrderActionSerialNo(1);
        l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
        l_ifoOrderUnitParams.setRequestType("2");
        l_ifoOrderUnitParams.setConfirmedExecConditionType(IfoOrderExecutionConditionType.AT_MARKET_OPEN);
        l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.FOK);
        l_ifoOrderUnitParams.setBizDate("20020202");
        l_ifoOrderUnitParams.setSonarTradedCode("2");
        l_ifoOrderUnitParams.setSonarMarketCode("6");
        l_ifoOrderUnitParams.setProductId(1006169090018L);
        l_ifoOrderUnitParams.setConfirmedPrice(new Double(333));
        l_ifoOrderUnitParams.setFutureOptionDiv("2");
        try
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        DefaultIfoChangeOrderMarketRequestMessage l_request =
            new DefaultIfoChangeOrderMarketRequestMessage(l_subAccount, l_lngOrderId, l_ifoOrderUnitParams);
        try
        {
            //市場未送信
            MarketRequestSendResult l_result = l_impl.send(l_request, true);
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());
            List l_lisRows = HostFotypeOrderAllDao.findRowsByStatusSubmitOrderRouteDiv("0", "1");
            HostFotypeOrderAllRow l_row = (HostFotypeOrderAllRow)l_lisRows.get(0);
            assertEquals("A", l_row.getExecutionCondition());
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (TooLateException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSendCase66()
    {
        final String STR_METHOD_NAME = "testSendCase66()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrderMQDataCode", new Class[] {IfoOrderUnit.class},
            null);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "isNotifyEndOrder", new Class[] {OrderUnit.class},
            Boolean.FALSE);
        
        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        IfoProductParams l_ifoProductParams = new IfoProductParams();
        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setSubmitMarketTrigger("0");
        
        l_ifoProductParams.setProductId(1006169090018L);
        l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setUnderlyingProductCode("1");
        l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
        l_ifoProductParams.setStrikePrice(11);
        l_ifoProductParams.setMonthOfDelivery("200702");
        l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setProductCode("112");
        l_ifoProductParams.setSplitType("4");

        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRev("01");
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
        l_ifoOrderUnitParams.setLastOrderActionSerialNo(1);
        l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
        l_ifoOrderUnitParams.setRequestType("2");
        l_ifoOrderUnitParams.setConfirmedExecConditionType(IfoOrderExecutionConditionType.AT_MARKET_OPEN);
        l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.FOK);
        l_ifoOrderUnitParams.setBizDate("20020202");
        l_ifoOrderUnitParams.setSonarTradedCode("2");
        l_ifoOrderUnitParams.setSonarMarketCode("6");
        l_ifoOrderUnitParams.setProductId(1006169090018L);
        l_ifoOrderUnitParams.setFutureOptionDiv("2");
        //注文数量
        l_ifoOrderUnitParams.setQuantity(500);
        //市場から確認済みの数量
        l_ifoOrderUnitParams.setConfirmedQuantity(200);
        //指値
        l_ifoOrderUnitParams.setLimitPrice(8);
        //市場から確認済みの指値
        l_ifoOrderUnitParams.setConfirmedPrice(8);
        //注文受付番号
        l_ifoOrderUnitParams.setAcceptNumber("10001");
        //売買区分
        l_ifoOrderUnitParams.setBuySellDiv("2");
        //銘柄コード
        l_ifoOrderUnitParams.setProductCode("600001003");
        try
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        DefaultIfoChangeOrderMarketRequestMessage l_request =
            new DefaultIfoChangeOrderMarketRequestMessage(l_subAccount, l_lngOrderId, l_ifoOrderUnitParams);
        try
        {
            //市場送信済、データなし
            MarketRequestSendResult l_result = l_impl.send(l_request, false);
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());
            List l_lisRows = HostFotypeOrderAllDao.findRowsByStatusSubmitOrderRouteDiv("0", "1");
            HostFotypeOrderAllRow l_row = (HostFotypeOrderAllRow)l_lisRows.get(0);
            assertEquals("A", l_row.getExecutionCondition());
            assertEquals("500.0", "" + l_row.getChangeQuantity());
            assertEquals("0.0", "" + l_row.getChangeLimitPrice());
            assertEquals("0", l_row.getChangeExecutionCondition());
            assertEquals("10001", l_row.getAcceptNumber());
            assertEquals("2", l_row.getBuySellDiv());
            assertEquals("600001003", l_row.getProductCode());
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (TooLateException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSendCase67()
    {
        final String STR_METHOD_NAME = "testSendCase67()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrderMQDataCode", new Class[] {IfoOrderUnit.class},
            null);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "isNotifyEndOrder", new Class[] {OrderUnit.class},
            Boolean.FALSE);
        
        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        IfoProductParams l_ifoProductParams = new IfoProductParams();
        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setSubmitMarketTrigger("0");
        
        l_ifoProductParams.setProductId(1006169090018L);
        l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setUnderlyingProductCode("1");
        l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
        l_ifoProductParams.setStrikePrice(11);
        l_ifoProductParams.setMonthOfDelivery("200702");
        l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setProductCode("112");
        l_ifoProductParams.setSplitType("4");

        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRev("01");
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
        l_ifoOrderUnitParams.setLastOrderActionSerialNo(1);
        l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
        l_ifoOrderUnitParams.setRequestType("2");
        l_ifoOrderUnitParams.setConfirmedExecConditionType(IfoOrderExecutionConditionType.AT_MARKET_OPEN);
        l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.FOK);
        l_ifoOrderUnitParams.setBizDate("20020202");
        l_ifoOrderUnitParams.setSonarTradedCode("2");
        l_ifoOrderUnitParams.setSonarMarketCode("6");
        l_ifoOrderUnitParams.setProductId(1006169090018L);
        l_ifoOrderUnitParams.setFutureOptionDiv("2");
        //注文数量
        l_ifoOrderUnitParams.setQuantity(500);
        //市場から確認済みの数量
        l_ifoOrderUnitParams.setConfirmedQuantity(500);
        //指値
        l_ifoOrderUnitParams.setLimitPrice(8);
        //市場から確認済みの指値
        l_ifoOrderUnitParams.setConfirmedPrice(0);
        //注文受付番号
        l_ifoOrderUnitParams.setAcceptNumber("10002");
        //売買区分
        l_ifoOrderUnitParams.setBuySellDiv("1");
        try
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        DefaultIfoChangeOrderMarketRequestMessage l_request =
            new DefaultIfoChangeOrderMarketRequestMessage(l_subAccount, l_lngOrderId, l_ifoOrderUnitParams);
        try
        {
            //市場送信済、データなし
            MarketRequestSendResult l_result = l_impl.send(l_request, false);
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());
            List l_lisRows = HostFotypeOrderAllDao.findRowsByStatusSubmitOrderRouteDiv("0", "1");
            HostFotypeOrderAllRow l_row = (HostFotypeOrderAllRow)l_lisRows.get(0);
            assertEquals("D", l_row.getExecutionCondition());
            assertEquals("0.0", "" + l_row.getChangeQuantity());
            assertEquals("8.0", "" + l_row.getChangeLimitPrice());
            assertEquals("0", l_row.getChangeExecutionCondition());
            assertEquals("10002", l_row.getAcceptNumber());
            assertEquals("1", l_row.getBuySellDiv());
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (TooLateException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSendCase68()
    {
        final String STR_METHOD_NAME = "testSendCase68()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrderMQDataCode", new Class[] {IfoOrderUnit.class},
            null);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "isNotifyEndOrder", new Class[] {OrderUnit.class},
            Boolean.FALSE);
        
        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
        IfoProductParams l_ifoProductParams = new IfoProductParams();
        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setSubmitMarketTrigger("0");
        
        l_ifoProductParams.setProductId(1006169090018L);
        l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setUnderlyingProductCode("1");
        l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
        l_ifoProductParams.setStrikePrice(11);
        l_ifoProductParams.setMonthOfDelivery("200702");
        l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setProductCode("112");
        l_ifoProductParams.setSplitType("4");
        
        l_hostFotypeOrderAllParams.setInstitutionCode("0D");
        l_hostFotypeOrderAllParams.setBranchCode("381");
        l_hostFotypeOrderAllParams.setOrderRequestNumber("000003006");
        l_hostFotypeOrderAllParams.setCorpCode("11111111111111101111");
        l_hostFotypeOrderAllParams.setStatus("0");
        l_hostFotypeOrderAllParams.setCancelDiv("0");
        l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
        l_hostFotypeOrderAllParams.setFrontOrderSystemCode("2");
        l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("1");
        l_hostFotypeOrderAllParams.setExecutionCondition("A");
        l_hostFotypeOrderAllParams.setAcceptNumber("10001");
        l_hostFotypeOrderAllParams.setBuySellDiv("2");
        l_hostFotypeOrderAllParams.setChangeExecutionCondition("1");
        l_hostFotypeOrderAllParams.setChangeLimitPrice(6);
        l_hostFotypeOrderAllParams.setChangeQuantity(50);
        
        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRev("01");
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
        l_ifoOrderUnitParams.setLastOrderActionSerialNo(1);
        l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
        l_ifoOrderUnitParams.setRequestType("2");
        l_ifoOrderUnitParams.setConfirmedExecConditionType(IfoOrderExecutionConditionType.AT_MARKET_OPEN);
        l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.FOK);
        l_ifoOrderUnitParams.setBizDate("20020202");
        l_ifoOrderUnitParams.setSonarTradedCode("2");
        l_ifoOrderUnitParams.setSonarMarketCode("6");
        l_ifoOrderUnitParams.setProductId(1006169090018L);
        l_ifoOrderUnitParams.setConfirmedPrice(new Double(333));
        l_ifoOrderUnitParams.setFutureOptionDiv("2");
        l_ifoOrderUnitParams.setQuantity(500);
        l_ifoOrderUnitParams.setConfirmedQuantity(200);
        l_ifoOrderUnitParams.setLimitPrice(8);
        l_ifoOrderUnitParams.setConfirmedPrice(8);
        try
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        DefaultIfoChangeOrderMarketRequestMessage l_request =
            new DefaultIfoChangeOrderMarketRequestMessage(l_subAccount, l_lngOrderId, l_ifoOrderUnitParams);
        try
        {
            //市場送信済、データあり
            MarketRequestSendResult l_result = l_impl.send(l_request, false);
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());
            List l_lisRows = HostFotypeOrderAllDao.findRowsByStatusSubmitOrderRouteDiv("0", "1");
            HostFotypeOrderAllRow l_row = (HostFotypeOrderAllRow)l_lisRows.get(0);
            assertEquals("A", l_row.getExecutionCondition());
            assertEquals("10001", l_row.getAcceptNumber());
            assertEquals("2", l_row.getBuySellDiv());
            assertEquals("500.0", "" + l_row.getChangeQuantity());
            assertEquals("0.0", "" + l_row.getChangeLimitPrice());
            assertEquals("0", l_row.getChangeExecutionCondition());
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (TooLateException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSendCase69()
    {
        final String STR_METHOD_NAME = "testSendCase69()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrderMQDataCode", new Class[] {IfoOrderUnit.class},
            null);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "isNotifyEndOrder", new Class[] {OrderUnit.class},
            Boolean.FALSE);
        
        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
        IfoProductParams l_ifoProductParams = new IfoProductParams();
        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setSubmitMarketTrigger("0");
        
        l_ifoProductParams.setProductId(1006169090018L);
        l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setUnderlyingProductCode("1");
        l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
        l_ifoProductParams.setStrikePrice(11);
        l_ifoProductParams.setMonthOfDelivery("200702");
        l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setProductCode("112");
        l_ifoProductParams.setSplitType("4");
        
        l_hostFotypeOrderAllParams.setInstitutionCode("0D");
        l_hostFotypeOrderAllParams.setBranchCode("381");
        l_hostFotypeOrderAllParams.setOrderRequestNumber("000003006");
        l_hostFotypeOrderAllParams.setCorpCode("11111111111111101111");
        l_hostFotypeOrderAllParams.setStatus("0");
        l_hostFotypeOrderAllParams.setCancelDiv("0");
        l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
        l_hostFotypeOrderAllParams.setFrontOrderSystemCode("2");
        l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("1");
        l_hostFotypeOrderAllParams.setExecutionCondition("A");
        l_hostFotypeOrderAllParams.setAcceptNumber("10001");
        l_hostFotypeOrderAllParams.setBuySellDiv("2");
        
        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRev("01");
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
        l_ifoOrderUnitParams.setLastOrderActionSerialNo(1);
        l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
        l_ifoOrderUnitParams.setRequestType("2");
        l_ifoOrderUnitParams.setConfirmedExecConditionType(IfoOrderExecutionConditionType.AT_MARKET_OPEN);
        l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.FOK);
        l_ifoOrderUnitParams.setBizDate("20020202");
        l_ifoOrderUnitParams.setSonarTradedCode("2");
        l_ifoOrderUnitParams.setSonarMarketCode("6");
        l_ifoOrderUnitParams.setProductId(1006169090018L);
        l_ifoOrderUnitParams.setConfirmedPrice(new Double(333));
        l_ifoOrderUnitParams.setFutureOptionDiv("2");
        l_ifoOrderUnitParams.setQuantity(500);
        l_ifoOrderUnitParams.setConfirmedQuantity(500);
        l_ifoOrderUnitParams.setLimitPrice(1);
        l_ifoOrderUnitParams.setConfirmedPrice(8);
        try
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        DefaultIfoChangeOrderMarketRequestMessage l_request =
            new DefaultIfoChangeOrderMarketRequestMessage(l_subAccount, l_lngOrderId, l_ifoOrderUnitParams);
        try
        {
            //市場送信済、データあり
            MarketRequestSendResult l_result = l_impl.send(l_request, false);
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());
            List l_lisRows = HostFotypeOrderAllDao.findRowsByStatusSubmitOrderRouteDiv("0", "1");
            HostFotypeOrderAllRow l_row = (HostFotypeOrderAllRow)l_lisRows.get(0);
            assertEquals("0.0", "" + l_row.getChangeQuantity());
            assertEquals("1.0", "" + l_row.getChangeLimitPrice());
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (TooLateException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSendCase70()
    {
        final String STR_METHOD_NAME = "testSendCase70()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrderMQDataCode", new Class[] {IfoOrderUnit.class},
            null);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "isNotifyEndOrder", new Class[] {OrderUnit.class},
            Boolean.FALSE);
        
        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
        IfoProductParams l_ifoProductParams = new IfoProductParams();
        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setSubmitMarketTrigger("0");
        
        l_ifoProductParams.setProductId(1006169090018L);
        l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setUnderlyingProductCode("1");
        l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
        l_ifoProductParams.setStrikePrice(11);
        l_ifoProductParams.setMonthOfDelivery("200702");
        l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setProductCode("112");
        l_ifoProductParams.setSplitType("4");
        
        l_hostFotypeOrderAllParams.setInstitutionCode("0D");
        l_hostFotypeOrderAllParams.setBranchCode("381");
        l_hostFotypeOrderAllParams.setOrderRequestNumber("000003006");
        l_hostFotypeOrderAllParams.setCorpCode("11111111111111101111");
        l_hostFotypeOrderAllParams.setStatus("0");
        l_hostFotypeOrderAllParams.setCancelDiv("0");
        l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
        l_hostFotypeOrderAllParams.setFrontOrderSystemCode("2");
        l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("1");
        l_hostFotypeOrderAllParams.setExecutionCondition("D");
        
        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRev("01");
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE);
        l_ifoOrderUnitParams.setConfirmedQuantity(222);
        l_ifoOrderUnitParams.setLastOrderActionSerialNo(1);
        l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
        l_ifoOrderUnitParams.setRequestType("2");
        l_ifoOrderUnitParams.setConfirmedExecConditionType(IfoOrderExecutionConditionType.AT_MARKET_OPEN);
        l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.FOK);
        l_ifoOrderUnitParams.setBizDate("20020202");
        l_ifoOrderUnitParams.setSonarTradedCode("2");
        l_ifoOrderUnitParams.setSonarMarketCode("6");
        l_ifoOrderUnitParams.setProductId(1006169090018L);
        l_ifoOrderUnitParams.setConfirmedPrice(new Double(333));
        l_ifoOrderUnitParams.setFutureOptionDiv("2");
        try
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        IfoClosingContractSpecRow[] l_closingContractRows = new IfoClosingContractSpecParams[1];
        DefaultIfoChangeSettleContractOrderMarketRequestMessage l_request =
            new DefaultIfoChangeSettleContractOrderMarketRequestMessage(
                l_subAccount, l_lngOrderId, l_ifoOrderUnitParams, l_closingContractRows);
        try
        {
            //市場未送信
            MarketRequestSendResult l_result = l_impl.send(l_request, true);
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());
            List l_lisRows = HostFotypeOrderAllDao.findRowsByStatusSubmitOrderRouteDiv("0", "1");
            HostFotypeOrderAllRow l_row = (HostFotypeOrderAllRow)l_lisRows.get(0);
            assertEquals("D", l_row.getExecutionCondition());
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (TooLateException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSendCase71()
    {
        final String STR_METHOD_NAME = "testSendCase71()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrderMQDataCode", new Class[] {IfoOrderUnit.class},
            null);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "isNotifyEndOrder", new Class[] {OrderUnit.class},
            Boolean.FALSE);
        
        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        IfoProductParams l_ifoProductParams = new IfoProductParams();
        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setSubmitMarketTrigger("0");
        
        l_ifoProductParams.setProductId(1006169090018L);
        l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setUnderlyingProductCode("1");
        l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
        l_ifoProductParams.setStrikePrice(11);
        l_ifoProductParams.setMonthOfDelivery("200702");
        l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setProductCode("112");
        l_ifoProductParams.setSplitType("4");

        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRev("01");
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE);
        l_ifoOrderUnitParams.setLastOrderActionSerialNo(1);
        l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
        l_ifoOrderUnitParams.setRequestType("2");
        l_ifoOrderUnitParams.setConfirmedExecConditionType(IfoOrderExecutionConditionType.AT_MARKET_OPEN);
        l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.FOK);
        l_ifoOrderUnitParams.setBizDate("20020202");
        l_ifoOrderUnitParams.setSonarTradedCode("2");
        l_ifoOrderUnitParams.setSonarMarketCode("6");
        l_ifoOrderUnitParams.setProductId(1006169090018L);
        l_ifoOrderUnitParams.setFutureOptionDiv("2");
        l_ifoOrderUnitParams.setQuantity(500);
        l_ifoOrderUnitParams.setConfirmedQuantity(200);
        l_ifoOrderUnitParams.setLimitPrice(8);
        l_ifoOrderUnitParams.setConfirmedPrice(8);
        l_ifoOrderUnitParams.setAcceptNumber("10001");
        l_ifoOrderUnitParams.setBuySellDiv("2");
        l_ifoOrderUnitParams.setProductCode("600001004");
        
        try
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        IfoClosingContractSpecRow[] l_closingContractRows = new IfoClosingContractSpecParams[1];
        DefaultIfoChangeSettleContractOrderMarketRequestMessage l_request =
            new DefaultIfoChangeSettleContractOrderMarketRequestMessage(
                l_subAccount, l_lngOrderId, l_ifoOrderUnitParams, l_closingContractRows);
        try
        {
            //市場送信済、データなし
            MarketRequestSendResult l_result = l_impl.send(l_request, false);
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());
            List l_lisRows = HostFotypeOrderAllDao.findRowsByStatusSubmitOrderRouteDiv("0", "1");
            HostFotypeOrderAllRow l_row = (HostFotypeOrderAllRow)l_lisRows.get(0);
            assertEquals("A", l_row.getExecutionCondition());
            assertEquals("500.0", "" + l_row.getChangeQuantity());
            assertEquals("0.0", "" + l_row.getChangeLimitPrice());
            assertEquals("0", l_row.getChangeExecutionCondition());
            assertEquals("10001", l_row.getAcceptNumber());
            assertEquals("2", l_row.getBuySellDiv());
            assertEquals("600001004", l_row.getProductCode());
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (TooLateException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSendCase72()
    {
        final String STR_METHOD_NAME = "testSendCase72()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrderMQDataCode", new Class[] {IfoOrderUnit.class},
            null);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "isNotifyEndOrder", new Class[] {OrderUnit.class},
            Boolean.FALSE);
        
        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        IfoProductParams l_ifoProductParams = new IfoProductParams();
        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setSubmitMarketTrigger("0");
        
        l_ifoProductParams.setProductId(1006169090018L);
        l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setUnderlyingProductCode("1");
        l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
        l_ifoProductParams.setStrikePrice(11);
        l_ifoProductParams.setMonthOfDelivery("200702");
        l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setProductCode("112");
        l_ifoProductParams.setSplitType("4");

        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRev("01");
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE);
        l_ifoOrderUnitParams.setLastOrderActionSerialNo(1);
        l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
        l_ifoOrderUnitParams.setRequestType("2");
        l_ifoOrderUnitParams.setConfirmedExecConditionType(IfoOrderExecutionConditionType.AT_MARKET_OPEN);
        l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.FOK);
        l_ifoOrderUnitParams.setBizDate("20020202");
        l_ifoOrderUnitParams.setSonarTradedCode("2");
        l_ifoOrderUnitParams.setSonarMarketCode("6");
        l_ifoOrderUnitParams.setProductId(1006169090018L);
        l_ifoOrderUnitParams.setFutureOptionDiv("2");
        l_ifoOrderUnitParams.setQuantity(500);
        l_ifoOrderUnitParams.setConfirmedQuantity(500);
        l_ifoOrderUnitParams.setLimitPrice(8);
        l_ifoOrderUnitParams.setConfirmedPrice(0);
        
        try
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        IfoClosingContractSpecRow[] l_closingContractRows = new IfoClosingContractSpecParams[1];
        DefaultIfoChangeSettleContractOrderMarketRequestMessage l_request =
            new DefaultIfoChangeSettleContractOrderMarketRequestMessage(
                l_subAccount, l_lngOrderId, l_ifoOrderUnitParams, l_closingContractRows);
        try
        {
            //市場送信済、データなし
            MarketRequestSendResult l_result = l_impl.send(l_request, false);
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());
            List l_lisRows = HostFotypeOrderAllDao.findRowsByStatusSubmitOrderRouteDiv("0", "1");
            HostFotypeOrderAllRow l_row = (HostFotypeOrderAllRow)l_lisRows.get(0);
            assertEquals("D", l_row.getExecutionCondition());
            assertEquals("0.0", "" + l_row.getChangeQuantity());
            assertEquals("8.0", "" + l_row.getChangeLimitPrice());
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (TooLateException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSendCase73()
    {
        final String STR_METHOD_NAME = "testSendCase73()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrderMQDataCode", new Class[] {IfoOrderUnit.class},
            null);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "isNotifyEndOrder", new Class[] {OrderUnit.class},
            Boolean.FALSE);
        
        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
        IfoProductParams l_ifoProductParams = new IfoProductParams();
        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setSubmitMarketTrigger("0");
        
        l_ifoProductParams.setProductId(1006169090018L);
        l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setUnderlyingProductCode("1");
        l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
        l_ifoProductParams.setStrikePrice(11);
        l_ifoProductParams.setMonthOfDelivery("200702");
        l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setProductCode("112");
        l_ifoProductParams.setSplitType("4");
        
        l_hostFotypeOrderAllParams.setInstitutionCode("0D");
        l_hostFotypeOrderAllParams.setBranchCode("381");
        l_hostFotypeOrderAllParams.setOrderRequestNumber("000003006");
        l_hostFotypeOrderAllParams.setCorpCode("11111111111111101111");
        l_hostFotypeOrderAllParams.setStatus("0");
        l_hostFotypeOrderAllParams.setCancelDiv("0");
        l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
        l_hostFotypeOrderAllParams.setFrontOrderSystemCode("2");
        l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("1");
        l_hostFotypeOrderAllParams.setExecutionCondition("A");
        l_hostFotypeOrderAllParams.setAcceptNumber("10002");
        l_hostFotypeOrderAllParams.setBuySellDiv("1");
        l_hostFotypeOrderAllParams.setChangeExecutionCondition("1");
        l_hostFotypeOrderAllParams.setChangeLimitPrice(5);
        l_hostFotypeOrderAllParams.setChangeQuantity(50);
        
        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRev("01");
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE);
        l_ifoOrderUnitParams.setLastOrderActionSerialNo(1);
        l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
        l_ifoOrderUnitParams.setRequestType("2");
        l_ifoOrderUnitParams.setConfirmedExecConditionType(IfoOrderExecutionConditionType.AT_MARKET_OPEN);
        l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.FOK);
        l_ifoOrderUnitParams.setBizDate("20020202");
        l_ifoOrderUnitParams.setSonarTradedCode("2");
        l_ifoOrderUnitParams.setSonarMarketCode("6");
        l_ifoOrderUnitParams.setProductId(1006169090018L);
        l_ifoOrderUnitParams.setFutureOptionDiv("2");
        l_ifoOrderUnitParams.setQuantity(500);
        l_ifoOrderUnitParams.setConfirmedQuantity(200);
        l_ifoOrderUnitParams.setLimitPrice(8);
        l_ifoOrderUnitParams.setConfirmedPrice(8);
        try
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        IfoClosingContractSpecRow[] l_closingContractRows = new IfoClosingContractSpecParams[1];
        DefaultIfoChangeSettleContractOrderMarketRequestMessage l_request =
            new DefaultIfoChangeSettleContractOrderMarketRequestMessage(
                l_subAccount, l_lngOrderId, l_ifoOrderUnitParams, l_closingContractRows);
        try
        {
            //市場送信済、データあり
            MarketRequestSendResult l_result = l_impl.send(l_request, false);
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());
            List l_lisRows = HostFotypeOrderAllDao.findRowsByStatusSubmitOrderRouteDiv("0", "1");
            HostFotypeOrderAllRow l_row = (HostFotypeOrderAllRow)l_lisRows.get(0);
            assertEquals("A", l_row.getExecutionCondition());
            assertEquals("500.0", "" + l_row.getChangeQuantity());
            assertEquals("0.0", "" + l_row.getChangeLimitPrice());
            assertEquals("0", l_row.getChangeExecutionCondition());
            assertEquals("10002", l_row.getAcceptNumber());
            assertEquals("1", l_row.getBuySellDiv());
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (TooLateException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSendCase74()
    {
        final String STR_METHOD_NAME = "testSendCase74()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getOrderMQDataCode", new Class[] {IfoOrderUnit.class},
            null);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3OptionOrderManagerImpl",
            "isNotifyEndOrder", new Class[] {OrderUnit.class},
            Boolean.FALSE);
        
        WEB3IfoMarketRequestSenderServiceImpl l_impl = new WEB3IfoMarketRequestSenderServiceImpl();
        long l_lngOrderId = 1001;

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = new HostFotypeOrderAllParams();
        IfoProductParams l_ifoProductParams = new IfoProductParams();
        OrderSwitchingParams l_orderSwitchingParams = TestDBUtility.getOrderSwitchingRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setSubmitMarketTrigger("0");
        
        l_ifoProductParams.setProductId(1006169090018L);
        l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setUnderlyingProductCode("1");
        l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
        l_ifoProductParams.setStrikePrice(11);
        l_ifoProductParams.setMonthOfDelivery("200702");
        l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
        l_ifoProductParams.setProductCode("112");
        l_ifoProductParams.setSplitType("4");
        
        l_hostFotypeOrderAllParams.setInstitutionCode("0D");
        l_hostFotypeOrderAllParams.setBranchCode("381");
        l_hostFotypeOrderAllParams.setOrderRequestNumber("000003006");
        l_hostFotypeOrderAllParams.setCorpCode("11111111111111101111");
        l_hostFotypeOrderAllParams.setStatus("0");
        l_hostFotypeOrderAllParams.setCancelDiv("0");
        l_hostFotypeOrderAllParams.setAllOrderChangeDiv("0");
        l_hostFotypeOrderAllParams.setFrontOrderSystemCode("2");
        l_hostFotypeOrderAllParams.setSubmitOrderRouteDiv("1");
        l_hostFotypeOrderAllParams.setExecutionCondition("A");
        l_hostFotypeOrderAllParams.setAcceptNumber("10002");
        l_hostFotypeOrderAllParams.setBuySellDiv("1");
        l_hostFotypeOrderAllParams.setChangeExecutionCondition("1");
        l_hostFotypeOrderAllParams.setChangeLimitPrice(5);
        l_hostFotypeOrderAllParams.setChangeQuantity(50);
        
        l_mainAccountParams.setAccountId(101001010010L);
        
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        SubAccountImpl l_subAccount = new SubAccountImpl(l_subAccountParams);

        l_ifoOrderParams.setOrderId(l_lngOrderId);
        l_ifoOrderParams.setAccountId(101001010010L);
        l_ifoOrderParams.setSubAccountId(10100101001007L);
        l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        l_ifoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        l_ifoOrderUnitParams = this.ifoOrderUnit();
        l_ifoOrderUnitParams.setOrderRev("01");
        l_ifoOrderUnitParams.setOrderRootDiv("9");
        l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE);
        l_ifoOrderUnitParams.setLastOrderActionSerialNo(1);
        l_ifoOrderUnitParams.setSubmitOrderRouteDiv("1");
        l_ifoOrderUnitParams.setRequestType("2");
        l_ifoOrderUnitParams.setConfirmedExecConditionType(IfoOrderExecutionConditionType.AT_MARKET_OPEN);
        l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.FOK);
        l_ifoOrderUnitParams.setBizDate("20020202");
        l_ifoOrderUnitParams.setSonarTradedCode("2");
        l_ifoOrderUnitParams.setSonarMarketCode("6");
        l_ifoOrderUnitParams.setProductId(1006169090018L);
        l_ifoOrderUnitParams.setFutureOptionDiv("2");
        l_ifoOrderUnitParams.setQuantity(500);
        l_ifoOrderUnitParams.setConfirmedQuantity(500);
        l_ifoOrderUnitParams.setLimitPrice(1);
        l_ifoOrderUnitParams.setConfirmedPrice(8);
        try
        {
            TestDBUtility.deleteAll(HostFotypeOrderAllRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_orderSwitchingParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_hostFotypeOrderAllParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        IfoClosingContractSpecRow[] l_closingContractRows = new IfoClosingContractSpecParams[1];
        DefaultIfoChangeSettleContractOrderMarketRequestMessage l_request =
            new DefaultIfoChangeSettleContractOrderMarketRequestMessage(
                l_subAccount, l_lngOrderId, l_ifoOrderUnitParams, l_closingContractRows);
        try
        {
            //市場送信済、データあり
            MarketRequestSendResult l_result = l_impl.send(l_request, false);
            assertTrue(l_result.getProcessingResult().isSuccessfulResult());
            List l_lisRows = HostFotypeOrderAllDao.findRowsByStatusSubmitOrderRouteDiv("0", "1");
            HostFotypeOrderAllRow l_row = (HostFotypeOrderAllRow)l_lisRows.get(0);
            assertEquals("0.0", "" + l_row.getChangeQuantity());
            assertEquals("1.0", "" + l_row.getChangeLimitPrice());
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (TooLateException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public IfoOrderUnitParams ifoOrderUnit()
    {
        IfoOrderUnitParams l_params = new IfoOrderUnitParams();
        l_params.setOrderUnitId(1001);
        l_params.setAccountId(101001010010L);
        l_params.setSubAccountId(10100101001007L);
        l_params.setBranchId(33381);
        l_params.setTraderId(null);
        l_params.setOrderId(1001);
        l_params.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
        l_params.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
        l_params.setLastOrderActionSerialNo(1);
        l_params.setLastExecutionSerialNo(0);
        l_params.setProductType(ProductTypeEnum.IFO);
        l_params.setFutureOptionDiv("1");
        l_params.setMarketId(1002);
        l_params.setQuantity(100);
        l_params.setLimitPrice(0);
        l_params.setExecutionConditionType(IfoOrderExecutionConditionType.NONE);
        l_params.setOrderConditionType("2");
        l_params.setOrderCondOperator(null);
        l_params.setStopPriceType(null);
        l_params.setStopOrderPrice(null);
        l_params.setWLimitPrice(null);
        l_params.setDeliveryDate(WEB3DateUtility.getDate("20040101", "yyyyMMdd"));
        l_params.setOrderStatus(OrderStatusEnum.ACCEPTED);
        l_params.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
        l_params.setExpirationStatus(OrderExpirationStatusEnum.EXPIRED);
        l_params.setTaxType(TaxTypeEnum.NORMAL);
        l_params.setBizDate("20040101");
        l_params.setProductId(1006169090018L);
        l_params.setCreatedTimestamp(WEB3DateUtility.getDate("20040101", "yyyyMMdd"));
        l_params.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040101", "yyyyMMdd"));
        l_params.setOrderRequestNumber("000003006");
        l_params.setConfirmedOrderRev("2");
        l_params.setOrderRev("1");
        return l_params;
    }

}
@
