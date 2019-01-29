head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.51.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3SrvRegiServiceUseApplicationServiceImplTest_validateUseAppli.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : (WEB3SrvRegiServiceUseApplicationServiceImplTest_validateUseAppli.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2007/06/16 崔遠鵬(中訊) 新規作成
 */
package webbroker3.srvregi.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.CalendarParams;
import webbroker3.gentrade.data.CalendarRow;
import webbroker3.gentrade.data.OrderAcceptStatusParams;
import webbroker3.gentrade.data.OrderAcceptStatusRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.srvregi.data.SrvAppliAttributeParams;
import webbroker3.srvregi.data.SrvAppliAttributeRow;
import webbroker3.srvregi.data.SrvRegiLotInfoRow;
import webbroker3.srvregi.data.SrvRegiMasterParams;
import webbroker3.srvregi.data.SrvRegiMasterRow;
import webbroker3.srvregi.data.SrvRegiSetupParams;
import webbroker3.srvregi.data.SrvRegiSetupRow;
import webbroker3.srvregi.message.WEB3SrvRegiApplyConfirmRequest;
import webbroker3.srvregi.message.WEB3SrvRegiApplyConfirmResponse;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3SrvRegiServiceUseApplicationServiceImplTest_validateUseAppli extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3SrvRegiServiceUseApplicationServiceImplTest_validateUseAppli.class);

    WEB3SrvRegiServiceUseApplicationServiceImpl impl = new WEB3SrvRegiServiceUseApplicationServiceImpl();

    public WEB3SrvRegiServiceUseApplicationServiceImplTest_validateUseAppli(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testValidateUseAppli01()
    {
        final String STR_METHOD_NAME = "testValidateUseAppli01()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.gentrade.WEB3GentradeOrderValidator",
            "validateSubAccountForTrading",
            new Class[] {SubAccount.class},
            new OrderValidationResult(ProcessingResult.newSuccessResultInstance()));
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getAccountId",
            new Class[] {},
            new Long(333812512246L));
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
            "getServiceRegist",
            new Class[] {String.class, String.class, String.class, String.class, String.class, String.class,
                boolean.class},
            null);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
            "getInitializeAppliDiv",
            new Class[] {String.class, String.class, String.class, String.class},
            "0");
        try
        {
            MainAccountParams l_mainAccountParams = new MainAccountParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
            l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountParams = new SubAccountParams();
            l_processor.doDeleteAllQuery(SubAccountRow.TYPE);
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            InstitutionParams l_institutionParams = new InstitutionParams();
            l_processor.doDeleteAllQuery(InstitutionRow.TYPE);
            l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams = new BranchParams();
            l_processor.doDeleteAllQuery(BranchRow.TYPE);
            l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            SrvRegiMasterParams l_srvRegiMasterParams = new SrvRegiMasterParams();
            l_processor.doDeleteAllQuery(SrvRegiMasterRow.TYPE);
            l_srvRegiMasterParams = TestDBUtility.getSrvRegiMasterRow();
            l_srvRegiMasterParams.setSrvStatus("2");
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            SrvAppliAttributeParams l_srvAppliAttributeParams = new SrvAppliAttributeParams();
            l_processor.doDeleteAllQuery(SrvAppliAttributeRow.TYPE);
            l_srvAppliAttributeParams = TestDBUtility.getSrvAppliAttributeRow();
            l_srvAppliAttributeParams.setInstitutionCode("0D");
            l_srvAppliAttributeParams.setBranchCode("381");
            l_srvAppliAttributeParams.setAccountCode("2512246");
            l_srvAppliAttributeParams.setAppliAttribute("2");
            TestDBUtility.insertWithDel(l_srvAppliAttributeParams);

            SrvRegiSetupParams l_srvRegiSetupParams = new SrvRegiSetupParams();
            l_processor.doDeleteAllQuery(SrvRegiSetupRow.TYPE);
            l_srvRegiSetupParams = TestDBUtility.getSrvRegiSetupRow();
            l_srvRegiSetupParams.setAppliDateFrom(null);
            l_srvRegiSetupParams.setLotDiv("3");
            TestDBUtility.insertWithDel(l_srvRegiSetupParams);

            OrderAcceptStatusParams l_orderAcceptStatusParams = new OrderAcceptStatusParams();
            l_processor.doDeleteAllQuery(OrderAcceptStatusRow.TYPE);
            l_orderAcceptStatusParams = TestDBUtility.getOrderAcceptStatusRow();
            l_orderAcceptStatusParams.setOrderAccTransaction("07");
            l_orderAcceptStatusParams.setOrderAcceptStatus("7");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007, 6-1, 14);
            Timestamp l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsAppliyDate);

            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                null);

            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setMarketCode("SP");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setTradingTimeType("3");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setOrderAcceptProduct("01");
            l_clendarContext.setOrderAcceptTransaction("07");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            TradingTimeParams l_tradingTimeParams = new TradingTimeParams();
            l_processor.doDeleteAllQuery(TradingTimeRow.TYPE);
            l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("3");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            CalendarParams l_calendarParams = new CalendarParams();
            l_processor.doDeleteAllQuery(CalendarRow.TYPE);
            l_calendar.set(2007, 6-1, 14);
            l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
            l_calendarParams.setHoliday(l_tsAppliyDate);
            l_calendarParams.setBizDateType("0");
            l_calendarParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_calendarParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_calendarParams);

            
            WEB3SrvRegiApplyConfirmRequest l_request = new WEB3SrvRegiApplyConfirmRequestForTest();
            l_request.chargeId = null;
            l_request.bidAmt = null;
            l_request.serviceDiv = "1234";
            l_request.freeAttributeApplyDiv = "1";
            l_request.applyKindDiv = "2";
            impl.validateUseAppli(l_request);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02805, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidateUseAppli02()
    {
        final String STR_METHOD_NAME = "testValidateUseAppli02()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.gentrade.WEB3GentradeOrderValidator",
            "validateSubAccountForTrading",
            new Class[] {SubAccount.class},
            new OrderValidationResult(ProcessingResult.newSuccessResultInstance()));
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getAccountId",
            new Class[] {},
            new Long(333812512246L));
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
            "getServiceRegist",
            new Class[] {String.class, String.class, String.class, String.class, String.class, String.class,
                boolean.class},
            null);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
            "getInitializeAppliDiv",
            new Class[] {String.class, String.class, String.class, String.class},
            "0");
        try
        {
            MainAccountParams l_mainAccountParams = new MainAccountParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
            l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountParams = new SubAccountParams();
            l_processor.doDeleteAllQuery(SubAccountRow.TYPE);
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            InstitutionParams l_institutionParams = new InstitutionParams();
            l_processor.doDeleteAllQuery(InstitutionRow.TYPE);
            l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams = new BranchParams();
            l_processor.doDeleteAllQuery(BranchRow.TYPE);
            l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            SrvRegiMasterParams l_srvRegiMasterParams = new SrvRegiMasterParams();
            l_processor.doDeleteAllQuery(SrvRegiMasterRow.TYPE);
            l_srvRegiMasterParams = TestDBUtility.getSrvRegiMasterRow();
            l_srvRegiMasterParams.setSrvStatus("2");
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            SrvAppliAttributeParams l_srvAppliAttributeParams = new SrvAppliAttributeParams();
            l_processor.doDeleteAllQuery(SrvAppliAttributeRow.TYPE);
            l_srvAppliAttributeParams = TestDBUtility.getSrvAppliAttributeRow();
            l_srvAppliAttributeParams.setInstitutionCode("0D");
            l_srvAppliAttributeParams.setBranchCode("381");
            l_srvAppliAttributeParams.setAccountCode("2512246");
            l_srvAppliAttributeParams.setAppliAttribute("3");
            TestDBUtility.insertWithDel(l_srvAppliAttributeParams);

            SrvRegiSetupParams l_srvRegiSetupParams = new SrvRegiSetupParams();
            l_processor.doDeleteAllQuery(SrvRegiSetupRow.TYPE);
            l_srvRegiSetupParams = TestDBUtility.getSrvRegiSetupRow();
            l_srvRegiSetupParams.setAppliDateFrom(null);
            l_srvRegiSetupParams.setLotDiv("3");
            TestDBUtility.insertWithDel(l_srvRegiSetupParams);

            OrderAcceptStatusParams l_orderAcceptStatusParams = new OrderAcceptStatusParams();
            l_processor.doDeleteAllQuery(OrderAcceptStatusRow.TYPE);
            l_orderAcceptStatusParams = TestDBUtility.getOrderAcceptStatusRow();
            l_orderAcceptStatusParams.setOrderAccTransaction("07");
            l_orderAcceptStatusParams.setOrderAcceptStatus("7");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007, 6-1, 14);
            Timestamp l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsAppliyDate);

            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                null);

            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setMarketCode("SP");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setTradingTimeType("3");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setOrderAcceptProduct("01");
            l_clendarContext.setOrderAcceptTransaction("07");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            TradingTimeParams l_tradingTimeParams = new TradingTimeParams();
            l_processor.doDeleteAllQuery(TradingTimeRow.TYPE);
            l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("3");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            CalendarParams l_calendarParams = new CalendarParams();
            l_processor.doDeleteAllQuery(CalendarRow.TYPE);
            l_calendar.set(2007, 6-1, 14);
            l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
            l_calendarParams.setHoliday(l_tsAppliyDate);
            l_calendarParams.setBizDateType("0");
            l_calendarParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_calendarParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_calendarParams);

            WEB3SrvRegiApplyConfirmRequest l_request = new WEB3SrvRegiApplyConfirmRequestForTest();
            l_request.chargeId = null;
            l_request.bidAmt = null;
            l_request.serviceDiv = "1234";
            l_request.freeAttributeApplyDiv = "1";
            l_request.applyKindDiv = "2";
            impl.validateUseAppli(l_request);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02806, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
//
//    public void testValidateUseAppli03()
//    {
//        final String STR_METHOD_NAME = "testValidateUseAppli03()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.gentrade.WEB3GentradeOrderValidator",
//            "validateSubAccountForTrading",
//            new Class[] {SubAccount.class},
//            new OrderValidationResult(ProcessingResult.newSuccessResultInstance()));
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//            "getAccountId",
//            new Class[] {},
//            new Long(333812512246L));
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
//            "getServiceRegist",
//            new Class[] {String.class, String.class, String.class, String.class, String.class, String.class,
//                boolean.class},
//            null);
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
//            "getInitializeAppliDiv",
//            new Class[] {String.class, String.class, String.class, String.class},
//            "0");
//        try
//        {
//            MainAccountParams l_mainAccountParams = new MainAccountParams();
//            QueryProcessor l_processor = Processors.getDefaultProcessor();
//            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
//            l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//
//            SubAccountParams l_subAccountParams = new SubAccountParams();
//            l_processor.doDeleteAllQuery(SubAccountRow.TYPE);
//            l_subAccountParams = TestDBUtility.getSubAccountRow();
//            l_subAccountParams.setAccountId(333812512246L);
//            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
//            TestDBUtility.insertWithDel(l_subAccountParams);
//
//            InstitutionParams l_institutionParams = new InstitutionParams();
//            l_processor.doDeleteAllQuery(InstitutionRow.TYPE);
//            l_institutionParams = TestDBUtility.getInstitutionRow();
//            TestDBUtility.insertWithDel(l_institutionParams);
//
//            BranchParams l_branchParams = new BranchParams();
//            l_processor.doDeleteAllQuery(BranchRow.TYPE);
//            l_branchParams = TestDBUtility.getBranchRow();
//            TestDBUtility.insertWithDel(l_branchParams);
//
//            SrvRegiMasterParams l_srvRegiMasterParams = new SrvRegiMasterParams();
//            l_processor.doDeleteAllQuery(SrvRegiMasterRow.TYPE);
//            l_srvRegiMasterParams = TestDBUtility.getSrvRegiMasterRow();
//            l_srvRegiMasterParams.setSrvStatus("2");
//            TestDBUtility.insertWithDel(l_srvRegiMasterParams);
//
//            SrvAppliAttributeParams l_srvAppliAttributeParams = new SrvAppliAttributeParams();
//            l_processor.doDeleteAllQuery(SrvAppliAttributeRow.TYPE);
//            l_srvAppliAttributeParams = TestDBUtility.getSrvAppliAttributeRow();
//            l_srvAppliAttributeParams.setInstitutionCode("0D");
//            l_srvAppliAttributeParams.setBranchCode("381");
//            l_srvAppliAttributeParams.setAccountCode("2512246");
//            l_srvAppliAttributeParams.setAppliAttribute("1");
//            TestDBUtility.insertWithDel(l_srvAppliAttributeParams);
//
//            SrvRegiSetupParams l_srvRegiSetupParams = new SrvRegiSetupParams();
//            l_processor.doDeleteAllQuery(SrvRegiSetupRow.TYPE);
//            l_srvRegiSetupParams = TestDBUtility.getSrvRegiSetupRow();
//            l_srvRegiSetupParams.setAppliDateFrom(null);
//            l_srvRegiSetupParams.setLotDiv("3");
//            TestDBUtility.insertWithDel(l_srvRegiSetupParams);
//
//            OrderAcceptStatusParams l_orderAcceptStatusParams = new OrderAcceptStatusParams();
//            l_processor.doDeleteAllQuery(OrderAcceptStatusRow.TYPE);
//            l_orderAcceptStatusParams = TestDBUtility.getOrderAcceptStatusRow();
//            l_orderAcceptStatusParams.setOrderAccTransaction("07");
//            l_orderAcceptStatusParams.setOrderAcceptStatus("7");
//            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);
//
//            Calendar l_calendar =  Calendar.getInstance();
//            l_calendar.set(2007, 6-1, 14);
//            Timestamp l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
//            ThreadLocalSystemAttributesRegistry.setAttribute(
//                "xblocks.gtl.attributes.systemtimestamp",
//                l_tsAppliyDate);
//
//            ThreadLocalSystemAttributesRegistry.setAttribute(
//                "web3.attributes.basetimestampfororderbizdate",
//                null);
//
//            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
//            l_clendarContext.setInstitutionCode("0D");
//            l_clendarContext.setMarketCode("SP");
//            l_clendarContext.setBranchCode("381");
//            l_clendarContext.setTradingTimeType("3");
//            l_clendarContext.setProductCode("0");
//            l_clendarContext.setOrderAcceptProduct("01");
//            l_clendarContext.setOrderAcceptTransaction("07");
//            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);
//
//            TradingTimeParams l_tradingTimeParams = new TradingTimeParams();
//            l_processor.doDeleteAllQuery(TradingTimeRow.TYPE);
//            l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
//            l_tradingTimeParams.setTradingTimeType("3");
//            l_tradingTimeParams.setBizdateCalcParameter("0");
//            TestDBUtility.insertWithDel(l_tradingTimeParams);
//
//            CalendarParams l_calendarParams = new CalendarParams();
//            l_processor.doDeleteAllQuery(CalendarRow.TYPE);
//            l_calendar.set(2007, 6-1, 14);
//            l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
//            l_calendarParams.setHoliday(l_tsAppliyDate);
//            l_calendarParams.setBizDateType("0");
//            l_calendarParams.setCreatedTimestamp(Calendar.getInstance().getTime());
//            l_calendarParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
//            TestDBUtility.insertWithDel(l_calendarParams);
//
//            WEB3SrvRegiApplyConfirmRequest l_request = new WEB3SrvRegiApplyConfirmRequestForTest();
//            l_request.chargeId = null;
//            l_request.bidAmt = null;
//            l_request.serviceDiv = "1234";
//            l_request.freeAttributeApplyDiv = null;
//            l_request.applyKindDiv = "2";
//            impl.validateUseAppli(l_request);
//            fail();
//        }
//        catch (WEB3BusinessLayerException l_ex)
//        {
//            log.debug(STR_METHOD_NAME, l_ex);
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02807, l_ex.getErrorInfo());
//        }
//        catch (Exception l_ex)
//        {
//            log.debug(STR_METHOD_NAME,l_ex);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }

    public void testValidateUseAppli04()
    {
        final String STR_METHOD_NAME = "testValidateUseAppli04()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.gentrade.WEB3GentradeOrderValidator",
            "validateSubAccountForTrading",
            new Class[] {SubAccount.class},
            new OrderValidationResult(ProcessingResult.newSuccessResultInstance()));
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getAccountId",
            new Class[] {},
            new Long(333812512246L));
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
            "getServiceRegist",
            new Class[] {String.class, String.class, String.class, String.class, String.class, String.class,
                boolean.class},
            null);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl",
            "getInitializeAppliDiv",
            new Class[] {String.class, String.class, String.class, String.class},
            "0");
        try
        {
            MainAccountParams l_mainAccountParams = new MainAccountParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
            l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountParams = new SubAccountParams();
            l_processor.doDeleteAllQuery(SubAccountRow.TYPE);
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            InstitutionParams l_institutionParams = new InstitutionParams();
            l_processor.doDeleteAllQuery(InstitutionRow.TYPE);
            l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams = new BranchParams();
            l_processor.doDeleteAllQuery(BranchRow.TYPE);
            l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            SrvRegiMasterParams l_srvRegiMasterParams = new SrvRegiMasterParams();
            l_processor.doDeleteAllQuery(SrvRegiMasterRow.TYPE);
            l_srvRegiMasterParams = TestDBUtility.getSrvRegiMasterRow();
            l_srvRegiMasterParams.setSrvStatus("2");
            TestDBUtility.insertWithDel(l_srvRegiMasterParams);

            SrvAppliAttributeParams l_srvAppliAttributeParams = new SrvAppliAttributeParams();
            l_processor.doDeleteAllQuery(SrvAppliAttributeRow.TYPE);
            l_srvAppliAttributeParams = TestDBUtility.getSrvAppliAttributeRow();
            l_srvAppliAttributeParams.setInstitutionCode("0D");
            l_srvAppliAttributeParams.setBranchCode("381");
            l_srvAppliAttributeParams.setAccountCode("2512246");
            l_srvAppliAttributeParams.setAppliAttribute("1");
            TestDBUtility.insertWithDel(l_srvAppliAttributeParams);

            SrvRegiSetupParams l_srvRegiSetupParams = new SrvRegiSetupParams();
            l_processor.doDeleteAllQuery(SrvRegiSetupRow.TYPE);
            l_srvRegiSetupParams = TestDBUtility.getSrvRegiSetupRow();
            l_srvRegiSetupParams.setAppliDateFrom(null);
            l_srvRegiSetupParams.setLotDiv("3");
            l_srvRegiSetupParams.setFreeCoverageLength(1);
            TestDBUtility.insertWithDel(l_srvRegiSetupParams);

            OrderAcceptStatusParams l_orderAcceptStatusParams = new OrderAcceptStatusParams();
            l_processor.doDeleteAllQuery(OrderAcceptStatusRow.TYPE);
            l_orderAcceptStatusParams = TestDBUtility.getOrderAcceptStatusRow();
            l_orderAcceptStatusParams.setOrderAccTransaction("07");
            l_orderAcceptStatusParams.setOrderAcceptStatus("7");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007, 6-1, 14);
            Timestamp l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsAppliyDate);

            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                null);

            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setMarketCode("SP");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setTradingTimeType("3");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setOrderAcceptProduct("01");
            l_clendarContext.setOrderAcceptTransaction("07");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            TradingTimeParams l_tradingTimeParams = new TradingTimeParams();
            l_processor.doDeleteAllQuery(TradingTimeRow.TYPE);
            l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("3");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            CalendarParams l_calendarParams = new CalendarParams();
            l_processor.doDeleteAllQuery(CalendarRow.TYPE);
            l_calendar.set(2007, 6-1, 14);
            l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
            l_calendarParams.setHoliday(l_tsAppliyDate);
            l_calendarParams.setBizDateType("0");
            l_calendarParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_calendarParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_calendarParams);

            l_processor.doDeleteAllQuery(SrvRegiLotInfoRow.TYPE);

            WEB3SrvRegiApplyConfirmRequest l_request = new WEB3SrvRegiApplyConfirmRequestForTest();
            l_request.chargeId = null;
            l_request.bidAmt = null;
            l_request.serviceDiv = "1234";
            l_request.freeAttributeApplyDiv = "1";
            l_request.applyKindDiv = "2";
            WEB3SrvRegiApplyConfirmResponse l_response = (WEB3SrvRegiApplyConfirmResponse)impl.
                validateUseAppli(l_request);

            assertEquals(l_response.trialStartDate, null);
            assertEquals(l_response.trialEndDate, null);
            assertEquals(WEB3DateUtility.compareToDay(l_response.checkDate, WEB3DateUtility.getDate("20070614", "yyyyMMdd")), 0);
            assertEquals(l_response.taxBidAmt, null);
            assertEquals(l_response.applyAttributeDiv, "1");
            assertEquals(l_response.freeTargetPeriod, "1");
            assertEquals(l_response.freeAttributeApplyDiv, "1");
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    class WEB3SrvRegiApplyConfirmRequestForTest extends WEB3SrvRegiApplyConfirmRequest
    {
        public void validate()
        {
            return;
        }
    }
}
@
