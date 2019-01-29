head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.04.08.05.38.47;	author zhang-tengyu;	state Exp;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	666;
commitid	64c4d9e9f6717d7;
filename	WEB3FXTransToFXServiceImplTest.java;

1.1
date	2011.04.07.01.37.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FXTransToFXServiceImplTest.java;


desc
@@


1.2
log
@*** empty log message ***
@
text
@package webbroker3.aio.service.delegate.stdimpls;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrder;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderManagerPersistenceEventInterceptor;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;
import com.gftforex.soap.api.ResultInfoBase;
import com.gftforex.soap.api.SendSyncRequestResponse;

import test.util.TestDBUtility;

import webbroker3.aio.WEB3FXTelegramProcessService;
import webbroker3.aio.WEB3FXTelegramProcessServiceImpl;
import webbroker3.aio.WEB3FXTelegramProcessServiceImplForMock;
import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.data.CompFxConditionRow;
import webbroker3.aio.data.FxAccountParams;
import webbroker3.aio.data.FxAccountRow;
import webbroker3.aio.data.FxTransferMasterParams;
import webbroker3.aio.data.FxTransferMasterRow;
import webbroker3.aio.data.GftTransferStatusParams;
import webbroker3.aio.data.GftTransferStatusRow;
import webbroker3.aio.define.WEB3AioAcceptResultCodeDef;
import webbroker3.aio.message.WEB3FXAccInformationUnit;
import webbroker3.aio.message.WEB3FXGftAskingTelegramUnit;
import webbroker3.aio.message.WEB3FXGftResultNoticeTelegramUnit;
import webbroker3.aio.message.WEB3FXTransToFXAskingRequest;
import webbroker3.aio.message.WEB3FXTransToFXAskingResponse;
import webbroker3.aio.message.WEB3FXTransToFXCompleteRequest;
import webbroker3.aio.message.WEB3FXTransToFXCompleteResponse;
import webbroker3.aio.message.WEB3FXTransToFXCompleteSoapRequest;
import webbroker3.aio.message.WEB3FXTransToFXCompleteSoapResponse;
import webbroker3.aio.message.WEB3FXTransToFXConfirmRequest;
import webbroker3.aio.message.WEB3FXTransToFXConfirmResponse;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ConnectDivDef;
import webbroker3.common.define.WEB3GftTransStatusCourseDivDef;
import webbroker3.common.define.WEB3SendRcvDivDef;
import webbroker3.common.define.WEB3SoapResultCodeDef;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.InstitutionPreferencesParams;
import webbroker3.gentrade.data.SoapConnectPrefRpcParams;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3FXTransToFXServiceImplTest extends TestBaseForMock {

    WEB3FXTransToFXServiceImpl l_impl;
    public WEB3FXTransToFXServiceImplTest(String arg0) {
        super(arg0);
    }

    protected void setUp() throws Exception {
        super.setUp();
        l_impl = new WEB3FXTransToFXServiceImpl();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        //TraderParams
        TestDBUtility.deleteAll(TraderParams.TYPE);
        TraderParams l_traderParams =
            TestDBUtility.getTraderRow();
        l_traderParams.setTraderId(123456);
        TestDBUtility.insertWithDel(l_traderParams);
        
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3FXTransToFXServiceImplTest.class);

    class WEB3FXTransToFXServiceImplTest1 extends WEB3FXTransToFXServiceImpl
    {
        protected String[] createParamList(String l_strConnectDiv,
            WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit) throws WEB3BaseException
        {
            return new String[]{"1","2","3"};
        }

        protected WEB3FXTransToFXAskingResponse startOrder(
            WEB3FXTransToFXAskingRequest l_request)
                throws WEB3BaseException
        {
            WEB3FXTransToFXAskingResponse l_response = new WEB3FXTransToFXAskingResponse();
            WEB3FXGftAskingTelegramUnit l_unit = new WEB3FXGftAskingTelegramUnit();
            l_unit.institutionCode = "0D";
            l_unit.branchCode = "381";
            l_unit.requestNumber = "14";
            l_response.fxGftAskingTelegramUnit = l_unit;
            return l_response;
        }

        protected WEB3FXTransToFXCompleteResponse submitOrder(
            WEB3FXTransToFXCompleteRequest l_request)
                throws WEB3BaseException
        {
            WEB3FXTransToFXCompleteResponse l_response = new WEB3FXTransToFXCompleteResponse();
            assertEquals(l_request.password,"12345678");
            l_response.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp();
            l_response.orderActionId = "1";
            l_response.deliveryDate = GtlUtils.getSystemTimestamp();
            return l_response;
        }
        
        protected WEB3FXTransToFXCompleteSoapResponse submitOrder(
                WEB3FXTransToFXCompleteSoapRequest l_request)
                    throws WEB3BaseException
        {
            WEB3FXTransToFXCompleteSoapResponse l_response = new WEB3FXTransToFXCompleteSoapResponse();
            assertEquals(l_request.password,"12345678");
            l_response.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp();
            l_response.orderActionId = "1";
            l_response.deliveryDate = GtlUtils.getSystemTimestamp();
            return l_response;
        }
    }

    public void testSubmitOrderCase0002()
    {
        final String STR_METHOD_NAME = "testSubmitOrderCase0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3FXTransToFXServiceImpl l_impl = new WEB3FXTransToFXServiceImpl();
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(1001));

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(1001);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAllAndCommit(GftTransferStatusParams.TYPE);
            GftTransferStatusParams l_gftTransferStatusParams = TestDBUtility.getGftTransferStatusRow();
            l_gftTransferStatusParams.setInstitutionCode("0D");
            l_gftTransferStatusParams.setBranchCode("381");
            l_gftTransferStatusParams.setOrderRequestNumber("14");
            l_gftTransferStatusParams.setSendRcvDiv(WEB3SendRcvDivDef.SEND_COMPLETE);
            TestDBUtility.insertWithDelAndCommit(l_gftTransferStatusParams);
            
            TestDBUtility.deleteAll(CompFxConditionParams.TYPE);
            CompFxConditionParams l_CompFxConditionParams = TestDBUtility.getCompFxConditionRow();
            l_CompFxConditionParams.setInstitutionCode("0D");
            l_CompFxConditionParams.setBranchCode("381");
            l_CompFxConditionParams.setGetTransferableAmtDiv("1");
            l_CompFxConditionParams.setMultiCfdAccDiv("0");
            TestDBUtility.insertWithDel(l_CompFxConditionParams);
            
            TestDBUtility.deleteAll(FxTransferMasterParams.TYPE);
            FxTransferMasterParams l_FxTransferMasterParams = TestDBUtility.getFxTransferMasterParams();
            l_FxTransferMasterParams.setFxSystemId(6241001);
            l_FxTransferMasterParams.setTransferDiv("1");
            TestDBUtility.insertWithDel(l_FxTransferMasterParams);

            WEB3FXTransToFXCompleteRequest l_request = new WEB3FXTransToFXCompleteRequest();
            l_request.orderId = "123";
            l_request.checkDate = GtlUtils.getSystemTimestamp();
            l_request.password = "12345678";
            WEB3FXGftResultNoticeTelegramUnit l_resultNoticeTelegramUnit =
                new WEB3FXGftResultNoticeTelegramUnit();
            l_resultNoticeTelegramUnit.institutionCode = "0D";
            l_resultNoticeTelegramUnit.branchCode = "381";
            l_resultNoticeTelegramUnit.requestNumber = "14";
            l_resultNoticeTelegramUnit.accountCode = "1234567";
            l_resultNoticeTelegramUnit.resultCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000990;
            l_request.fxGftResultNoticeTelegramUnit = l_resultNoticeTelegramUnit;
            l_impl.submitOrder(l_request);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_01800);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitOrderCase0003()
    {
        final String STR_METHOD_NAME = "testSubmitOrderCase0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3FXTransToFXServiceImpl l_impl = new WEB3FXTransToFXServiceImpl();

            
            Services.overrideService(WEB3FXTelegramProcessService.class, 
                    new WEB3FXTelegramProcessServiceImplForMock());
            
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(1001));
            
            Boolean l_falg = Boolean.TRUE;
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                    "isGFTTelegramSet", 
                    new Class[]{ WEB3FXGftResultNoticeTelegramUnit.class },
                    l_falg);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                    "isGFTTelegramLengthPropSame", 
                    new Class[]{ WEB3FXGftResultNoticeTelegramUnit.class },
                    l_falg);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                    "isGFTTelegramSendAndReceiveValueSame", 
                    new Class[]{WEB3FXGftResultNoticeTelegramUnit.class },
                    l_falg);

            
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001);
            l_mainAccountParams.setBranchCode("381");
            
            
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(1001);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAllAndCommit(GftTransferStatusParams.TYPE);
            GftTransferStatusParams l_gftTransferStatusParams = TestDBUtility.getGftTransferStatusRow();
            l_gftTransferStatusParams.setInstitutionCode("0D");
            l_gftTransferStatusParams.setBranchCode("381");
            l_gftTransferStatusParams.setOrderRequestNumber("14");
            l_gftTransferStatusParams.setSendRcvDiv(WEB3SendRcvDivDef.SEND_COMPLETE);
            l_gftTransferStatusParams.setFxSystemCode("01");
            TestDBUtility.insertWithDelAndCommit(l_gftTransferStatusParams);


            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            l_compFxConditionParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setFxSystemCode("01");
            l_compFxConditionParams.setFxSystemDiv("2");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);

            
            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setInstitutionCode("0D");
            l_tradingTimeParams1.setBranchCode("381");
            l_tradingTimeParams1.setMarketCode("N1");
            l_tradingTimeParams1.setTradingTimeType("11");
            l_tradingTimeParams1.setProductCode("0");
            l_tradingTimeParams1.setBizDateType("1");
            l_tradingTimeParams1.setStartTime("000000");
            l_tradingTimeParams1.setEndTime("235959");
            l_tradingTimeParams1.setSubmitMarketTrigger("0");
            l_tradingTimeParams1.setEnableOrder("0");
            l_tradingTimeParams1.setBizdateCalcParameter("1");
            l_tradingTimeParams1.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_tradingTimeParams1.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode(l_tradingTimeParams1.getInstitutionCode());
            l_clendarContext.setBranchCode(l_tradingTimeParams1.getBranchCode());
            l_clendarContext.setMarketCode(l_tradingTimeParams1.getMarketCode());
            l_clendarContext.setTradingTimeType(l_tradingTimeParams1.getTradingTimeType());
            l_clendarContext.setProductCode(l_tradingTimeParams1.getProductCode());
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext",l_clendarContext);

            //TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);

            TestDBUtility.deleteAll(SoapConnectPrefRpcParams.TYPE);
            SoapConnectPrefRpcParams l_soapConnectPrefRpcParams = new SoapConnectPrefRpcParams();
            l_soapConnectPrefRpcParams.setBranchId(33381);
            l_soapConnectPrefRpcParams.setConnectDiv("01");
            l_soapConnectPrefRpcParams.setEndpointName("1");
            l_soapConnectPrefRpcParams.setTargetNamespaceName("1");
            l_soapConnectPrefRpcParams.setServiceName("1");
            l_soapConnectPrefRpcParams.setPortTypeName("1");
            l_soapConnectPrefRpcParams.setOperationName("1");
            l_soapConnectPrefRpcParams.setResponseParamType("1");
            l_soapConnectPrefRpcParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_soapConnectPrefRpcParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_soapConnectPrefRpcParams);

            WEB3FXTransToFXCompleteRequest l_request = new WEB3FXTransToFXCompleteRequest();
            l_request.orderId = "123";
            l_request.checkDate = GtlUtils.getSystemTimestamp();
            l_request.password = "12345678";
            WEB3FXGftResultNoticeTelegramUnit l_resultNoticeTelegramUnit =
                new WEB3FXGftResultNoticeTelegramUnit();
            l_resultNoticeTelegramUnit.institutionCode = "0D";
            l_resultNoticeTelegramUnit.branchCode = "381";
            l_resultNoticeTelegramUnit.requestNumber = "14";
            l_resultNoticeTelegramUnit.accountCode = "1234567";
            l_resultNoticeTelegramUnit.resultCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000000;
            l_resultNoticeTelegramUnit.dirSendTime= "20080808125959";
            l_resultNoticeTelegramUnit.gftOperationDiv= "0";
            l_resultNoticeTelegramUnit.fxFirstLoginId= "0";
            l_resultNoticeTelegramUnit.groupName= "0";
            l_resultNoticeTelegramUnit.wolfSession= "0";
            l_resultNoticeTelegramUnit.wolfAid= "0";
            l_resultNoticeTelegramUnit.regetServiceId= "0";
            l_resultNoticeTelegramUnit.wolfSsid= "0";
            l_resultNoticeTelegramUnit.gftSendTime= "20080808125901";
            l_resultNoticeTelegramUnit.hashValue= "0";
            l_request.fxGftResultNoticeTelegramUnit = l_resultNoticeTelegramUnit;
            
            
            AioOrderUnitParams l_AioOrderUnitParmas = TestDBUtility.getAioOrderUnitRow();
            TestDBUtility.deleteAll(AioOrderUnitParams.TYPE);
            l_AioOrderUnitParmas.setAccountId(l_mainAccountParams.getAccountId());
            l_AioOrderUnitParmas.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_AioOrderUnitParmas.setBranchId(l_branchParams.getBranchId());
            l_AioOrderUnitParmas.setProductType(ProductTypeEnum.CASH);
            l_AioOrderUnitParmas.setOrderRequestNumber(l_gftTransferStatusParams.getOrderRequestNumber());
            TestDBUtility.insertWithDel(l_AioOrderUnitParmas);
            
            AioOrderParams l_AioOrderParams = TestDBUtility.getAioOrderRow();
            TestDBUtility.deleteAll(AioOrderParams.TYPE);
            l_AioOrderParams.setOrderId(l_AioOrderUnitParmas.getOrderId());
            TestDBUtility.insertWithDel(l_AioOrderParams);

            WEB3FXTransToFXCompleteResponse l_response =
            l_impl.submitOrder(l_request);

            assertEquals(l_response.orderActionId,String.valueOf(l_AioOrderUnitParmas.getOrderId()));
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitOrderCase0004()
    {
        final String STR_METHOD_NAME = "testSubmitOrderCase0004()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            WEB3FXTransToFXCompleteRequest l_request = new WEB3FXTransToFXCompleteRequest();
            l_request.orderId = "123";
            l_request.checkDate = GtlUtils.getSystemTimestamp();
            l_request.password = "12345678";
            l_request.fxSystemCode = "1";
            WEB3FXGftResultNoticeTelegramUnit l_resultNoticeTelegramUnit =
                new WEB3FXGftResultNoticeTelegramUnit();
            l_resultNoticeTelegramUnit.institutionCode = l_mainAccountParams.getInstitutionCode();
            l_resultNoticeTelegramUnit.branchCode = l_mainAccountParams.getBranchCode();
            l_resultNoticeTelegramUnit.requestNumber = "14";
            l_resultNoticeTelegramUnit.accountCode = l_mainAccountParams.getAccountCode();
            l_resultNoticeTelegramUnit.resultCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000000;
            l_resultNoticeTelegramUnit.dirSendTime= "20080808125959";
            l_resultNoticeTelegramUnit.gftOperationDiv= "0";
            l_resultNoticeTelegramUnit.fxFirstLoginId= "0";
            l_resultNoticeTelegramUnit.groupName= "0";
            l_resultNoticeTelegramUnit.wolfSession= "0";
            l_resultNoticeTelegramUnit.wolfAid= "0";
            l_resultNoticeTelegramUnit.regetServiceId= "0";
            l_resultNoticeTelegramUnit.wolfSsid= "0";
            l_resultNoticeTelegramUnit.gftSendTime= "20080808125901";
            l_resultNoticeTelegramUnit.hashValue= "0";
            l_request.fxGftResultNoticeTelegramUnit = l_resultNoticeTelegramUnit;
            
            TestDBUtility.deleteAllAndCommit(GftTransferStatusParams.TYPE);
            GftTransferStatusParams l_transferStatusParams =
                TestDBUtility.getGftTransferStatusRow();
            l_transferStatusParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_transferStatusParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_transferStatusParams.setOrderRequestNumber("14");
            TestDBUtility.insertWithDel(l_transferStatusParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            l_compFxConditionParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_compFxConditionParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_compFxConditionParams.setFxSystemCode(l_request.fxSystemCode);
            l_compFxConditionParams.setFxSystemDiv("2");
            l_compFxConditionParams.setSoapConnectDiv("0");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode(l_mainAccountParams.getBranchCode());
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "validateOrder",
                new Class[] {SubAccount.class},
                null);

            TestDBUtility.deleteAll(FxTransferMasterRow.TYPE);
            l_impl.submitOrder(l_request);
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitOrderCase0005()
    {
        final String STR_METHOD_NAME = "testSubmitOrderCase0005()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            WEB3FXTransToFXCompleteRequest l_request = new WEB3FXTransToFXCompleteRequest();
            l_request.orderId = "123";
            l_request.checkDate = GtlUtils.getSystemTimestamp();
            l_request.password = "12345678";
            l_request.fxSystemCode = "1";
            WEB3FXGftResultNoticeTelegramUnit l_resultNoticeTelegramUnit =
                new WEB3FXGftResultNoticeTelegramUnit();
            l_resultNoticeTelegramUnit.institutionCode = l_mainAccountParams.getInstitutionCode();
            l_resultNoticeTelegramUnit.branchCode = l_mainAccountParams.getBranchCode();
            l_resultNoticeTelegramUnit.requestNumber = "14";
            l_resultNoticeTelegramUnit.accountCode = l_mainAccountParams.getAccountCode();
            l_resultNoticeTelegramUnit.resultCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000000;
            l_resultNoticeTelegramUnit.dirSendTime= "20080808125959";
            l_resultNoticeTelegramUnit.gftOperationDiv= "0";
            l_resultNoticeTelegramUnit.fxFirstLoginId= "0";
            l_resultNoticeTelegramUnit.groupName= "0";
            l_resultNoticeTelegramUnit.wolfSession= "0";
            l_resultNoticeTelegramUnit.wolfAid= "0";
            l_resultNoticeTelegramUnit.regetServiceId= "0";
            l_resultNoticeTelegramUnit.wolfSsid= "0";
            l_resultNoticeTelegramUnit.gftSendTime= "20080808125901";
            l_resultNoticeTelegramUnit.hashValue= "0";
            l_request.fxGftResultNoticeTelegramUnit = l_resultNoticeTelegramUnit;
            
            TestDBUtility.deleteAllAndCommit(GftTransferStatusParams.TYPE);
            GftTransferStatusParams l_transferStatusParams =
                TestDBUtility.getGftTransferStatusRow();
            l_transferStatusParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_transferStatusParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_transferStatusParams.setOrderRequestNumber("14");
            TestDBUtility.insertWithDel(l_transferStatusParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            l_compFxConditionParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_compFxConditionParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_compFxConditionParams.setFxSystemCode(l_request.fxSystemCode);
            l_compFxConditionParams.setFxSystemDiv("2");
            l_compFxConditionParams.setSoapConnectDiv("0");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);
            
            TestDBUtility.deleteAll(FxTransferMasterParams.TYPE);
            FxTransferMasterParams l_fxTransferMasterParams =
                TestDBUtility.getFxTransferMasterParams();
            l_fxTransferMasterParams.setFxSystemId(l_compFxConditionParams.getFxSystemId());
            l_fxTransferMasterParams.setTransferDiv("1");
            TestDBUtility.insertWithDel(l_fxTransferMasterParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode(l_mainAccountParams.getBranchCode());
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "validateOrder",
                new Class[] {SubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSendAndReceiveValueSame",
                new Class[] {WEB3FXGftResultNoticeTelegramUnit.class},
                new Boolean(false));

            l_impl.submitOrder(l_request);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01800, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitOrderCase0006()
    {
        final String STR_METHOD_NAME = "testSubmitOrderCase0006()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            WEB3FXTransToFXCompleteRequest l_request = new WEB3FXTransToFXCompleteRequest();
            l_request.orderId = "123";
            l_request.checkDate = GtlUtils.getSystemTimestamp();
            l_request.password = "12345678";
            l_request.fxSystemCode = "1";
            WEB3FXGftResultNoticeTelegramUnit l_resultNoticeTelegramUnit =
                new WEB3FXGftResultNoticeTelegramUnit();
            l_resultNoticeTelegramUnit.institutionCode = l_mainAccountParams.getInstitutionCode();
            l_resultNoticeTelegramUnit.branchCode = l_mainAccountParams.getBranchCode();
            l_resultNoticeTelegramUnit.requestNumber = "14";
            l_resultNoticeTelegramUnit.accountCode = l_mainAccountParams.getAccountCode();
            l_resultNoticeTelegramUnit.resultCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000000;
            l_resultNoticeTelegramUnit.dirSendTime= "20080808125959";
            l_resultNoticeTelegramUnit.gftOperationDiv= "0";
            l_resultNoticeTelegramUnit.fxFirstLoginId= "0";
            l_resultNoticeTelegramUnit.groupName= "0";
            l_resultNoticeTelegramUnit.wolfSession= "0";
            l_resultNoticeTelegramUnit.wolfAid= null;
            l_resultNoticeTelegramUnit.regetServiceId= "0";
            l_resultNoticeTelegramUnit.wolfSsid= "0";
//            l_resultNoticeTelegramUnit.gftSendTime= "20080808125901";
            l_resultNoticeTelegramUnit.gftSendTime= "2008080812590w";
            l_resultNoticeTelegramUnit.hashValue= "0";
            l_request.fxGftResultNoticeTelegramUnit = l_resultNoticeTelegramUnit;
            
            TestDBUtility.deleteAllAndCommit(GftTransferStatusParams.TYPE);
            GftTransferStatusParams l_transferStatusParams =
                TestDBUtility.getGftTransferStatusRow();
            l_transferStatusParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_transferStatusParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_transferStatusParams.setOrderRequestNumber("14");
            TestDBUtility.insertWithDel(l_transferStatusParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            l_compFxConditionParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_compFxConditionParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_compFxConditionParams.setFxSystemCode(l_request.fxSystemCode);
            l_compFxConditionParams.setFxSystemDiv("2");
            l_compFxConditionParams.setSoapConnectDiv("0");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);
            
            TestDBUtility.deleteAll(FxTransferMasterParams.TYPE);
            FxTransferMasterParams l_fxTransferMasterParams =
                TestDBUtility.getFxTransferMasterParams();
            l_fxTransferMasterParams.setFxSystemId(l_compFxConditionParams.getFxSystemId());
            l_fxTransferMasterParams.setTransferDiv("1");
            TestDBUtility.insertWithDel(l_fxTransferMasterParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode(l_mainAccountParams.getBranchCode());
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "validateOrder",
                new Class[] {SubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSendAndReceiveValueSame",
                new Class[] {WEB3FXGftResultNoticeTelegramUnit.class},
                new Boolean(false));

            l_impl.submitOrder(l_request);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01800, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitOrderCase0007()
    {
        final String STR_METHOD_NAME = "testSubmitOrderCase0007()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            WEB3FXTransToFXCompleteRequest l_request = new WEB3FXTransToFXCompleteRequest();
            l_request.orderId = "123";
            l_request.checkDate = GtlUtils.getSystemTimestamp();
            l_request.password = "12345678";
            l_request.fxSystemCode = "1";
            WEB3FXGftResultNoticeTelegramUnit l_resultNoticeTelegramUnit =
                new WEB3FXGftResultNoticeTelegramUnit();
            l_resultNoticeTelegramUnit.institutionCode = l_mainAccountParams.getInstitutionCode();
            l_resultNoticeTelegramUnit.branchCode = l_mainAccountParams.getBranchCode();
            l_resultNoticeTelegramUnit.requestNumber = "14";
            l_resultNoticeTelegramUnit.accountCode = l_mainAccountParams.getAccountCode();
            l_resultNoticeTelegramUnit.resultCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000000;
            l_resultNoticeTelegramUnit.dirSendTime= "20080808125959";
            l_resultNoticeTelegramUnit.gftOperationDiv= "0";
            l_resultNoticeTelegramUnit.fxFirstLoginId= "0";
            l_resultNoticeTelegramUnit.groupName= "0";
            l_resultNoticeTelegramUnit.wolfSession= "0";
            l_resultNoticeTelegramUnit.wolfAid= "0";
            l_resultNoticeTelegramUnit.regetServiceId= "0";
            l_resultNoticeTelegramUnit.wolfSsid= "0";
            l_resultNoticeTelegramUnit.gftSendTime= "20080808125901";
            l_resultNoticeTelegramUnit.hashValue= "0";
            l_resultNoticeTelegramUnit.cashinoutAmt = "100";
            l_request.fxGftResultNoticeTelegramUnit = l_resultNoticeTelegramUnit;
            
            TestDBUtility.deleteAllAndCommit(GftTransferStatusParams.TYPE);
            GftTransferStatusParams l_transferStatusParams =
                TestDBUtility.getGftTransferStatusRow();
            l_transferStatusParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_transferStatusParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_transferStatusParams.setOrderRequestNumber("14");
            TestDBUtility.insertWithDel(l_transferStatusParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            l_compFxConditionParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_compFxConditionParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_compFxConditionParams.setFxSystemCode(l_request.fxSystemCode);
            l_compFxConditionParams.setFxSystemDiv("2");
            l_compFxConditionParams.setSoapConnectDiv("0");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);
            
            TestDBUtility.deleteAll(FxTransferMasterParams.TYPE);
            FxTransferMasterParams l_fxTransferMasterParams =
                TestDBUtility.getFxTransferMasterParams();
            l_fxTransferMasterParams.setFxSystemId(l_compFxConditionParams.getFxSystemId());
            l_fxTransferMasterParams.setTransferDiv("1");
            l_fxTransferMasterParams.setDeliveryDateDiv("1");
            TestDBUtility.insertWithDel(l_fxTransferMasterParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode(l_mainAccountParams.getBranchCode());
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "validateOrder",
                new Class[] {SubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSendAndReceiveValueSame",
                new Class[] {WEB3FXGftResultNoticeTelegramUnit.class},
                new Boolean(true));

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20090318", "yyyyMMdd"));
            
            //AioOrderUnitParams
            TestDBUtility.deleteAll(AioOrderUnitParams.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams =
                TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(l_mainAccountParams.getAccountId());
            l_aioOrderUnitParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_aioOrderUnitParams.setProductType(ProductTypeEnum.CASH);
            l_aioOrderUnitParams.setOrderRequestNumber("14");
            l_aioOrderUnitParams.setBranchId(l_branchParams.getBranchId());
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            
            //AioOrderParams
            TestDBUtility.deleteAll(AioOrderParams.TYPE);
            AioOrderParams l_aioOrderParams =
                TestDBUtility.getAioOrderRow();
            l_aioOrderParams.setOrderId(l_aioOrderUnitParams.getOrderId());
            TestDBUtility.insertWithDel(l_aioOrderParams);

            l_impl.submitOrder(l_request);
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisResult = l_queryProcessor.doFindAllQuery(AioOrderUnitParams.TYPE);
            assertEquals(l_lisResult.size(), 1);
            
            AioOrderUnitRow l_aioOrderUnitRow =
                (AioOrderUnitRow)l_lisResult.get(0);

            assertFalse(l_aioOrderUnitParams.getBizDate().equals(l_aioOrderUnitRow.getBizDate()));
            assertFalse(l_aioOrderUnitParams.getDeliveryDate().equals(l_aioOrderUnitRow.getDeliveryDate()));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitOrderCase0008()
    {
        final String STR_METHOD_NAME = "testSubmitOrderCase0008()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            WEB3FXTransToFXCompleteRequest l_request = new WEB3FXTransToFXCompleteRequest();
            l_request.orderId = "123";
            l_request.checkDate = WEB3DateUtility.getDate("20090318", "yyyyMMdd");
            l_request.password = "12345678";
            l_request.fxSystemCode = "1";
            WEB3FXGftResultNoticeTelegramUnit l_resultNoticeTelegramUnit =
                new WEB3FXGftResultNoticeTelegramUnit();
            l_resultNoticeTelegramUnit.institutionCode = l_mainAccountParams.getInstitutionCode();
            l_resultNoticeTelegramUnit.branchCode = l_mainAccountParams.getBranchCode();
            l_resultNoticeTelegramUnit.requestNumber = "14";
            l_resultNoticeTelegramUnit.accountCode = l_mainAccountParams.getAccountCode();
            l_resultNoticeTelegramUnit.resultCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000000;
            l_resultNoticeTelegramUnit.dirSendTime= "20080808125959";
            l_resultNoticeTelegramUnit.gftOperationDiv= "0";
            l_resultNoticeTelegramUnit.fxFirstLoginId= "0";
            l_resultNoticeTelegramUnit.groupName= "0";
            l_resultNoticeTelegramUnit.wolfSession= "0";
            l_resultNoticeTelegramUnit.wolfAid= "0";
            l_resultNoticeTelegramUnit.regetServiceId= "0";
            l_resultNoticeTelegramUnit.wolfSsid= "0";
            l_resultNoticeTelegramUnit.gftSendTime= "20080808125901";
            l_resultNoticeTelegramUnit.hashValue= "0";
            l_resultNoticeTelegramUnit.cashinoutAmt = "100";
            l_request.fxGftResultNoticeTelegramUnit = l_resultNoticeTelegramUnit;
            
            TestDBUtility.deleteAllAndCommit(GftTransferStatusParams.TYPE);
            GftTransferStatusParams l_transferStatusParams =
                TestDBUtility.getGftTransferStatusRow();
            l_transferStatusParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_transferStatusParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_transferStatusParams.setOrderRequestNumber("14");
            TestDBUtility.insertWithDel(l_transferStatusParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            l_compFxConditionParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_compFxConditionParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_compFxConditionParams.setFxSystemCode(l_request.fxSystemCode);
            l_compFxConditionParams.setFxSystemDiv("2");
            l_compFxConditionParams.setSoapConnectDiv("0");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);
            
            TestDBUtility.deleteAll(FxTransferMasterParams.TYPE);
            FxTransferMasterParams l_fxTransferMasterParams =
                TestDBUtility.getFxTransferMasterParams();
            l_fxTransferMasterParams.setFxSystemId(l_compFxConditionParams.getFxSystemId());
            l_fxTransferMasterParams.setTransferDiv("1");
            l_fxTransferMasterParams.setDeliveryDateDiv("1");
            TestDBUtility.insertWithDel(l_fxTransferMasterParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode(l_mainAccountParams.getBranchCode());
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "validateOrder",
                new Class[] {SubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSendAndReceiveValueSame",
                new Class[] {WEB3FXGftResultNoticeTelegramUnit.class},
                new Boolean(true));

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20090318", "yyyyMMdd"));
            
            //AioOrderUnitParams
            TestDBUtility.deleteAll(AioOrderUnitParams.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams =
                TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(l_mainAccountParams.getAccountId());
            l_aioOrderUnitParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_aioOrderUnitParams.setProductType(ProductTypeEnum.CASH);
            l_aioOrderUnitParams.setOrderRequestNumber("14");
            l_aioOrderUnitParams.setBranchId(l_branchParams.getBranchId());
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            
            //AioOrderParams
            TestDBUtility.deleteAll(AioOrderParams.TYPE);
            AioOrderParams l_aioOrderParams =
                TestDBUtility.getAioOrderRow();
            l_aioOrderParams.setOrderId(l_aioOrderUnitParams.getOrderId());
            TestDBUtility.insertWithDel(l_aioOrderParams);

            l_impl.submitOrder(l_request);
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisResult = l_queryProcessor.doFindAllQuery(AioOrderUnitParams.TYPE);
            assertEquals(l_lisResult.size(), 1);
            
            AioOrderUnitRow l_aioOrderUnitRow =
                (AioOrderUnitRow)l_lisResult.get(0);

            assertTrue(l_aioOrderUnitParams.getBizDate().equals(l_aioOrderUnitRow.getBizDate()));
            assertTrue(l_aioOrderUnitParams.getDeliveryDate().equals(l_aioOrderUnitRow.getDeliveryDate()));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitOrderCase0009()
    {
        final String STR_METHOD_NAME = "testSubmitOrderCase0009()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            WEB3FXTransToFXCompleteRequest l_request = new WEB3FXTransToFXCompleteRequest();
            l_request.orderId = "123";
            l_request.checkDate = WEB3DateUtility.getDate("20090318", "yyyyMMdd");
            l_request.password = "12345678";
            l_request.fxSystemCode = "1";
            WEB3FXGftResultNoticeTelegramUnit l_resultNoticeTelegramUnit =
                new WEB3FXGftResultNoticeTelegramUnit();
            l_resultNoticeTelegramUnit.institutionCode = l_mainAccountParams.getInstitutionCode();
            l_resultNoticeTelegramUnit.branchCode = l_mainAccountParams.getBranchCode();
            l_resultNoticeTelegramUnit.requestNumber = "14";
            l_resultNoticeTelegramUnit.accountCode = l_mainAccountParams.getAccountCode();
            l_resultNoticeTelegramUnit.resultCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000000;
            l_resultNoticeTelegramUnit.dirSendTime= "20080808125959";
            l_resultNoticeTelegramUnit.gftOperationDiv= "0";
            l_resultNoticeTelegramUnit.fxFirstLoginId= "0";
            l_resultNoticeTelegramUnit.groupName= "0";
            l_resultNoticeTelegramUnit.wolfSession= "0";
            l_resultNoticeTelegramUnit.wolfAid= "0";
            l_resultNoticeTelegramUnit.regetServiceId= "0";
            l_resultNoticeTelegramUnit.wolfSsid= "0";
            l_resultNoticeTelegramUnit.gftSendTime= "20080808125901";
            l_resultNoticeTelegramUnit.hashValue= "0";
            l_resultNoticeTelegramUnit.cashinoutAmt2 = "100";
            l_request.fxGftResultNoticeTelegramUnit = l_resultNoticeTelegramUnit;
            
            TestDBUtility.deleteAllAndCommit(GftTransferStatusParams.TYPE);
            GftTransferStatusParams l_transferStatusParams =
                TestDBUtility.getGftTransferStatusRow();
            l_transferStatusParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_transferStatusParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_transferStatusParams.setOrderRequestNumber("14");
            TestDBUtility.insertWithDel(l_transferStatusParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            l_compFxConditionParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_compFxConditionParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_compFxConditionParams.setFxSystemCode(l_request.fxSystemCode);
            l_compFxConditionParams.setFxSystemDiv("2");
            l_compFxConditionParams.setSoapConnectDiv("0");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);
            
            TestDBUtility.deleteAll(FxTransferMasterParams.TYPE);
            FxTransferMasterParams l_fxTransferMasterParams =
                TestDBUtility.getFxTransferMasterParams();
            l_fxTransferMasterParams.setFxSystemId(l_compFxConditionParams.getFxSystemId());
            l_fxTransferMasterParams.setTransferDiv("1");
            l_fxTransferMasterParams.setDeliveryDateDiv("1");
            TestDBUtility.insertWithDel(l_fxTransferMasterParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode(l_mainAccountParams.getBranchCode());
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "validateOrder",
                new Class[] {SubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSendAndReceiveValueSame",
                new Class[] {WEB3FXGftResultNoticeTelegramUnit.class},
                new Boolean(true));

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20090318", "yyyyMMdd"));
            
            //AioOrderUnitParams
            TestDBUtility.deleteAll(AioOrderUnitParams.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams =
                TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(l_mainAccountParams.getAccountId());
            l_aioOrderUnitParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_aioOrderUnitParams.setProductType(ProductTypeEnum.CASH);
            l_aioOrderUnitParams.setOrderRequestNumber("14");
            l_aioOrderUnitParams.setBranchId(l_branchParams.getBranchId());
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            
            //AioOrderParams
            TestDBUtility.deleteAll(AioOrderParams.TYPE);
            AioOrderParams l_aioOrderParams =
                TestDBUtility.getAioOrderRow();
            l_aioOrderParams.setOrderId(l_aioOrderUnitParams.getOrderId());
            TestDBUtility.insertWithDel(l_aioOrderParams);

            l_impl.submitOrder(l_request);
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisResult = l_queryProcessor.doFindAllQuery(AioOrderUnitParams.TYPE);
            assertEquals(l_lisResult.size(), 1);
            
            AioOrderUnitRow l_aioOrderUnitRow =
                (AioOrderUnitRow)l_lisResult.get(0);

            assertEquals(100, l_aioOrderUnitRow.getQuantity(), 0);
            assertEquals("71", l_aioOrderUnitRow.getRemarkCode());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitOrderCase0010()
    {
        final String STR_METHOD_NAME = "testSubmitOrderCase00010()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            WEB3FXTransToFXCompleteRequest l_request = new WEB3FXTransToFXCompleteRequest();
            l_request.orderId = "123";
            l_request.checkDate = WEB3DateUtility.getDate("20090318", "yyyyMMdd");
            l_request.password = "12345678";
            l_request.fxSystemCode = "1";
            WEB3FXGftResultNoticeTelegramUnit l_resultNoticeTelegramUnit =
                new WEB3FXGftResultNoticeTelegramUnit();
            l_resultNoticeTelegramUnit.institutionCode = l_mainAccountParams.getInstitutionCode();
            l_resultNoticeTelegramUnit.branchCode = l_mainAccountParams.getBranchCode();
            l_resultNoticeTelegramUnit.requestNumber = "14";
            l_resultNoticeTelegramUnit.accountCode = l_mainAccountParams.getAccountCode();
            l_resultNoticeTelegramUnit.resultCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000000;
            l_resultNoticeTelegramUnit.dirSendTime= "20080808125959";
            l_resultNoticeTelegramUnit.gftOperationDiv= "0";
            l_resultNoticeTelegramUnit.fxFirstLoginId= "0";
            l_resultNoticeTelegramUnit.groupName= "0";
            l_resultNoticeTelegramUnit.wolfSession= "0";
            l_resultNoticeTelegramUnit.wolfAid= "0";
            l_resultNoticeTelegramUnit.regetServiceId= "0";
            l_resultNoticeTelegramUnit.wolfSsid= "0";
            l_resultNoticeTelegramUnit.gftSendTime= "20080808125901";
            l_resultNoticeTelegramUnit.hashValue= "0";
            l_resultNoticeTelegramUnit.cashinoutAmt2 = "200";
            l_resultNoticeTelegramUnit.cashinoutAmt = "100";
            l_request.fxGftResultNoticeTelegramUnit = l_resultNoticeTelegramUnit;
            
            TestDBUtility.deleteAllAndCommit(GftTransferStatusParams.TYPE);
            GftTransferStatusParams l_transferStatusParams =
                TestDBUtility.getGftTransferStatusRow();
            l_transferStatusParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_transferStatusParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_transferStatusParams.setOrderRequestNumber("14");
            TestDBUtility.insertWithDel(l_transferStatusParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            l_compFxConditionParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_compFxConditionParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_compFxConditionParams.setFxSystemCode(l_request.fxSystemCode);
            l_compFxConditionParams.setFxSystemDiv("2");
            l_compFxConditionParams.setSoapConnectDiv("0");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);
            
            TestDBUtility.deleteAll(FxTransferMasterParams.TYPE);
            FxTransferMasterParams l_fxTransferMasterParams =
                TestDBUtility.getFxTransferMasterParams();
            l_fxTransferMasterParams.setFxSystemId(l_compFxConditionParams.getFxSystemId());
            l_fxTransferMasterParams.setTransferDiv("1");
            l_fxTransferMasterParams.setDeliveryDateDiv("1");
            TestDBUtility.insertWithDel(l_fxTransferMasterParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode(l_mainAccountParams.getBranchCode());
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_ProductParams =
                TestDBUtility.getProductRow();
            l_ProductParams.setProductId(123456666);
            l_ProductParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            l_ProductParams.setProductType(ProductTypeEnum.CASH);
            TestDBUtility.insertWithDel(l_ProductParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "validateOrder",
                new Class[] {SubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSendAndReceiveValueSame",
                new Class[] {WEB3FXGftResultNoticeTelegramUnit.class},
                new Boolean(true));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());
                
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(123456));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "submitTransferOrder",
                new Class[]{
                    SubAccount.class, ProductTypeEnum.class, OrderTypeEnum.class, NewOrderSpec.class,
                    AioOrderManagerPersistenceEventInterceptor.class, long.class, String.class},
                null);

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20090318", "yyyyMMdd"));
            
            //AioOrderUnitParams
            TestDBUtility.deleteAll(AioOrderUnitParams.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams =
                TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(l_mainAccountParams.getAccountId());
            l_aioOrderUnitParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_aioOrderUnitParams.setProductType(ProductTypeEnum.CASH);
            l_aioOrderUnitParams.setOrderRequestNumber("14");
            l_aioOrderUnitParams.setBranchId(l_branchParams.getBranchId());
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            
            //AioOrderParams
            TestDBUtility.deleteAll(AioOrderParams.TYPE);
            AioOrderParams l_aioOrderParams =
                TestDBUtility.getAioOrderRow();
            l_aioOrderParams.setOrderId(l_aioOrderUnitParams.getOrderId());
            TestDBUtility.insertWithDel(l_aioOrderParams);

            l_impl.submitOrder(l_request);
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisResult = l_queryProcessor.doFindAllQuery(AioOrderUnitParams.TYPE);
            assertEquals(l_lisResult.size(), 1);
            
            AioOrderUnitRow l_aioOrderUnitRow =
                (AioOrderUnitRow)l_lisResult.get(0);

            assertEquals(100, l_aioOrderUnitRow.getQuantity(), 0);
            assertEquals("72", l_aioOrderUnitRow.getRemarkCode());
            assertTrue(l_aioOrderUnitParams.getBizDate().equals(l_aioOrderUnitRow.getBizDate()));
            assertTrue(l_aioOrderUnitParams.getDeliveryDate().equals(l_aioOrderUnitRow.getDeliveryDate()));
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitOrderCase0011()
    {
        final String STR_METHOD_NAME = "testSubmitOrderCase00011()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            WEB3FXTransToFXCompleteRequest l_request = new WEB3FXTransToFXCompleteRequest();
            l_request.orderId = "123";
            l_request.checkDate = WEB3DateUtility.getDate("20090317", "yyyyMMdd");
            l_request.password = "12345678";
            l_request.fxSystemCode = "1";
            WEB3FXGftResultNoticeTelegramUnit l_resultNoticeTelegramUnit =
                new WEB3FXGftResultNoticeTelegramUnit();
            l_resultNoticeTelegramUnit.institutionCode = l_mainAccountParams.getInstitutionCode();
            l_resultNoticeTelegramUnit.branchCode = l_mainAccountParams.getBranchCode();
            l_resultNoticeTelegramUnit.requestNumber = "14";
            l_resultNoticeTelegramUnit.accountCode = l_mainAccountParams.getAccountCode();
            l_resultNoticeTelegramUnit.resultCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000000;
            l_resultNoticeTelegramUnit.dirSendTime= "20080808125959";
            l_resultNoticeTelegramUnit.gftOperationDiv= "0";
            l_resultNoticeTelegramUnit.fxFirstLoginId= "0";
            l_resultNoticeTelegramUnit.groupName= "0";
            l_resultNoticeTelegramUnit.wolfSession= "0";
            l_resultNoticeTelegramUnit.wolfAid= "0";
            l_resultNoticeTelegramUnit.regetServiceId= "0";
            l_resultNoticeTelegramUnit.wolfSsid= "0";
            l_resultNoticeTelegramUnit.gftSendTime= "20080808125901";
            l_resultNoticeTelegramUnit.hashValue= "0";
            l_resultNoticeTelegramUnit.cashinoutAmt2 = "200";
            l_resultNoticeTelegramUnit.cashinoutAmt = "100";
            l_request.fxGftResultNoticeTelegramUnit = l_resultNoticeTelegramUnit;
            
            TestDBUtility.deleteAllAndCommit(GftTransferStatusParams.TYPE);
            GftTransferStatusParams l_transferStatusParams =
                TestDBUtility.getGftTransferStatusRow();
            l_transferStatusParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_transferStatusParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_transferStatusParams.setOrderRequestNumber("14");
            TestDBUtility.insertWithDel(l_transferStatusParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            l_compFxConditionParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_compFxConditionParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_compFxConditionParams.setFxSystemCode(l_request.fxSystemCode);
            l_compFxConditionParams.setFxSystemDiv("2");
            l_compFxConditionParams.setSoapConnectDiv("0");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);
            
            TestDBUtility.deleteAll(FxTransferMasterParams.TYPE);
            FxTransferMasterParams l_fxTransferMasterParams =
                TestDBUtility.getFxTransferMasterParams();
            l_fxTransferMasterParams.setFxSystemId(l_compFxConditionParams.getFxSystemId());
            l_fxTransferMasterParams.setTransferDiv("1");
            l_fxTransferMasterParams.setDeliveryDateDiv("1");
            TestDBUtility.insertWithDel(l_fxTransferMasterParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode(l_mainAccountParams.getBranchCode());
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_ProductParams =
                TestDBUtility.getProductRow();
            l_ProductParams.setProductId(123456666);
            l_ProductParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            l_ProductParams.setProductType(ProductTypeEnum.CASH);
            TestDBUtility.insertWithDel(l_ProductParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "validateOrder",
                new Class[] {SubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSendAndReceiveValueSame",
                new Class[] {WEB3FXGftResultNoticeTelegramUnit.class},
                new Boolean(true));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());
                
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(123456));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "submitTransferOrder",
                new Class[]{
                    SubAccount.class, ProductTypeEnum.class, OrderTypeEnum.class, NewOrderSpec.class,
                    AioOrderManagerPersistenceEventInterceptor.class, long.class, String.class},
                null);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20090318", "yyyyMMdd"));
            
            //AioOrderUnitParams
            TestDBUtility.deleteAll(AioOrderUnitParams.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams =
                TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(l_mainAccountParams.getAccountId());
            l_aioOrderUnitParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_aioOrderUnitParams.setProductType(ProductTypeEnum.CASH);
            l_aioOrderUnitParams.setOrderRequestNumber("14");
            l_aioOrderUnitParams.setBranchId(l_branchParams.getBranchId());
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            
            //AioOrderParams
            TestDBUtility.deleteAll(AioOrderParams.TYPE);
            AioOrderParams l_aioOrderParams =
                TestDBUtility.getAioOrderRow();
            l_aioOrderParams.setOrderId(l_aioOrderUnitParams.getOrderId());
            TestDBUtility.insertWithDel(l_aioOrderParams);

            l_impl.submitOrder(l_request);
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisResult = l_queryProcessor.doFindAllQuery(AioOrderUnitParams.TYPE);
            assertEquals(l_lisResult.size(), 1);
            
            AioOrderUnitRow l_aioOrderUnitRow =
                (AioOrderUnitRow)l_lisResult.get(0);

            assertEquals(100, l_aioOrderUnitRow.getQuantity(), 0);
            assertEquals("72", l_aioOrderUnitRow.getRemarkCode());
            assertFalse(l_aioOrderUnitParams.getBizDate().equals(l_aioOrderUnitRow.getBizDate()));
            assertFalse(l_aioOrderUnitParams.getDeliveryDate().equals(l_aioOrderUnitRow.getDeliveryDate()));
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitOrderCase0012()
    {
        final String STR_METHOD_NAME = "testSubmitOrderCase00012()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            WEB3FXTransToFXCompleteRequest l_request = new WEB3FXTransToFXCompleteRequest();
            l_request.orderId = "123";
            l_request.checkDate = WEB3DateUtility.getDate("20090317", "yyyyMMdd");
            l_request.password = "12345678";
            l_request.fxSystemCode = "1";
            WEB3FXGftResultNoticeTelegramUnit l_resultNoticeTelegramUnit =
                new WEB3FXGftResultNoticeTelegramUnit();
            l_resultNoticeTelegramUnit.institutionCode = l_mainAccountParams.getInstitutionCode();
            l_resultNoticeTelegramUnit.branchCode = l_mainAccountParams.getBranchCode();
            l_resultNoticeTelegramUnit.requestNumber = "14";
            l_resultNoticeTelegramUnit.accountCode = l_mainAccountParams.getAccountCode();
            l_resultNoticeTelegramUnit.resultCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000000;
            l_resultNoticeTelegramUnit.dirSendTime= "20080808125959";
            l_resultNoticeTelegramUnit.gftOperationDiv= "0";
            l_resultNoticeTelegramUnit.fxFirstLoginId= "0";
            l_resultNoticeTelegramUnit.groupName= "0";
            l_resultNoticeTelegramUnit.wolfSession= "0";
            l_resultNoticeTelegramUnit.wolfAid= null;
            l_resultNoticeTelegramUnit.regetServiceId= "0";
            l_resultNoticeTelegramUnit.wolfSsid= "0";
            l_resultNoticeTelegramUnit.gftSendTime= "2008080812590s";
            l_resultNoticeTelegramUnit.hashValue= "0";
            l_resultNoticeTelegramUnit.cashinoutAmt2 = "200";
            l_resultNoticeTelegramUnit.cashinoutAmt = "100";
            l_request.fxGftResultNoticeTelegramUnit = l_resultNoticeTelegramUnit;
            
            TestDBUtility.deleteAllAndCommit(GftTransferStatusParams.TYPE);
            GftTransferStatusParams l_transferStatusParams =
                TestDBUtility.getGftTransferStatusRow();
            l_transferStatusParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_transferStatusParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_transferStatusParams.setOrderRequestNumber("14");
            l_transferStatusParams.setAccountCode(l_mainAccountParams.getAccountCode());
            l_transferStatusParams.setMrgTrnOrderRequestNumber("14");
            TestDBUtility.insertWithDel(l_transferStatusParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            l_compFxConditionParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_compFxConditionParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_compFxConditionParams.setFxSystemCode(l_request.fxSystemCode);
            l_compFxConditionParams.setFxSystemDiv("2");
            l_compFxConditionParams.setSoapConnectDiv("1");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);
            
            TestDBUtility.deleteAll(FxTransferMasterParams.TYPE);
            FxTransferMasterParams l_fxTransferMasterParams =
                TestDBUtility.getFxTransferMasterParams();
            l_fxTransferMasterParams.setFxSystemId(l_compFxConditionParams.getFxSystemId());
            l_fxTransferMasterParams.setTransferDiv("1");
            l_fxTransferMasterParams.setDeliveryDateDiv("1");
            TestDBUtility.insertWithDel(l_fxTransferMasterParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode(l_mainAccountParams.getBranchCode());
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "validateOrder",
                new Class[] {SubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSendAndReceiveValueSame",
                new Class[] {WEB3FXGftResultNoticeTelegramUnit.class},
                new Boolean(false));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());
                
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(123456));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "submitTransferOrder",
                new Class[]{
                    SubAccount.class, ProductTypeEnum.class, OrderTypeEnum.class, NewOrderSpec.class,
                    AioOrderManagerPersistenceEventInterceptor.class, long.class, String.class},
                null);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20090318", "yyyyMMdd"));
            
            //AioOrderUnitParams
            TestDBUtility.deleteAll(AioOrderUnitParams.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams =
                TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(l_mainAccountParams.getAccountId());
            l_aioOrderUnitParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_aioOrderUnitParams.setProductType(ProductTypeEnum.CASH);
            l_aioOrderUnitParams.setOrderRequestNumber("14");
            l_aioOrderUnitParams.setOrderCateg(OrderCategEnum.FX);
            l_aioOrderUnitParams.setBranchId(l_branchParams.getBranchId());
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            
            //AioOrderParams
            TestDBUtility.deleteAll(AioOrderParams.TYPE);
            AioOrderParams l_aioOrderParams =
                TestDBUtility.getAioOrderRow();
            l_aioOrderParams.setOrderId(l_aioOrderUnitParams.getOrderId());
            TestDBUtility.insertWithDel(l_aioOrderParams);

            l_impl.submitOrder(l_request);
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisResult = l_queryProcessor.doFindAllQuery(AioOrderUnitParams.TYPE);
            assertEquals(l_lisResult.size(), 1);
            
            AioOrderUnitRow l_aioOrderUnitRow =
                (AioOrderUnitRow)l_lisResult.get(0);

            assertFalse(l_aioOrderUnitParams.getBizDate().equals(l_aioOrderUnitRow.getBizDate()));
            assertFalse(l_aioOrderUnitParams.getDeliveryDate().equals(l_aioOrderUnitRow.getDeliveryDate()));
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitOrderCase0001()
    {
        final String STR_METHOD_NAME = "testSubmitOrderCase0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            WEB3FXTransToFXCompleteRequest l_request = new WEB3FXTransToFXCompleteRequest();
            l_request.orderId = "123";
            l_request.checkDate = WEB3DateUtility.getDate("20090318", "yyyyMMdd");
            l_request.password = "12345678";
            l_request.fxSystemCode = "1";
            WEB3FXGftResultNoticeTelegramUnit l_resultNoticeTelegramUnit =
                new WEB3FXGftResultNoticeTelegramUnit();
            l_resultNoticeTelegramUnit.institutionCode = l_mainAccountParams.getInstitutionCode();
            l_resultNoticeTelegramUnit.branchCode = l_mainAccountParams.getBranchCode();
            l_resultNoticeTelegramUnit.requestNumber = "14";
            l_resultNoticeTelegramUnit.accountCode = l_mainAccountParams.getAccountCode();
            l_resultNoticeTelegramUnit.resultCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000000;
            l_resultNoticeTelegramUnit.dirSendTime= "20080808125959";
            l_resultNoticeTelegramUnit.gftOperationDiv= "0";
            l_resultNoticeTelegramUnit.fxFirstLoginId= "0";
            l_resultNoticeTelegramUnit.groupName= "0";
            l_resultNoticeTelegramUnit.wolfSession= "0";
            l_resultNoticeTelegramUnit.wolfAid= null;
            l_resultNoticeTelegramUnit.regetServiceId= "0";
            l_resultNoticeTelegramUnit.wolfSsid= "0";
            l_resultNoticeTelegramUnit.gftSendTime= "2008080812590s";
            l_resultNoticeTelegramUnit.hashValue= "0";
            l_resultNoticeTelegramUnit.cashinoutAmt2 = "200";
            l_resultNoticeTelegramUnit.cashinoutAmt = "100";
            l_request.fxGftResultNoticeTelegramUnit = l_resultNoticeTelegramUnit;
            
            TestDBUtility.deleteAllAndCommit(GftTransferStatusParams.TYPE);
            GftTransferStatusParams l_transferStatusParams =
                TestDBUtility.getGftTransferStatusRow();
            l_transferStatusParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_transferStatusParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_transferStatusParams.setOrderRequestNumber("14");
            l_transferStatusParams.setAccountCode(l_mainAccountParams.getAccountCode());
            l_transferStatusParams.setMrgTrnOrderRequestNumber("14");
            TestDBUtility.insertWithDel(l_transferStatusParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            l_compFxConditionParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_compFxConditionParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_compFxConditionParams.setFxSystemCode(l_request.fxSystemCode);
            l_compFxConditionParams.setFxSystemDiv("2");
            l_compFxConditionParams.setSoapConnectDiv("1");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);
            
            TestDBUtility.deleteAll(FxTransferMasterParams.TYPE);
            FxTransferMasterParams l_fxTransferMasterParams =
                TestDBUtility.getFxTransferMasterParams();
            l_fxTransferMasterParams.setFxSystemId(l_compFxConditionParams.getFxSystemId());
            l_fxTransferMasterParams.setTransferDiv("1");
            l_fxTransferMasterParams.setDeliveryDateDiv("1");
            TestDBUtility.insertWithDel(l_fxTransferMasterParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode(l_mainAccountParams.getBranchCode());
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "validateOrder",
                new Class[] {SubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSendAndReceiveValueSame",
                new Class[] {WEB3FXGftResultNoticeTelegramUnit.class},
                new Boolean(false));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());
                
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(123456));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "submitTransferOrder",
                new Class[]{
                    SubAccount.class, ProductTypeEnum.class, OrderTypeEnum.class, NewOrderSpec.class,
                    AioOrderManagerPersistenceEventInterceptor.class, long.class, String.class},
                null);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20090318", "yyyyMMdd"));
            
            //AioOrderUnitParams
            TestDBUtility.deleteAll(AioOrderUnitParams.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams =
                TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(l_mainAccountParams.getAccountId());
            l_aioOrderUnitParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_aioOrderUnitParams.setProductType(ProductTypeEnum.CASH);
            l_aioOrderUnitParams.setOrderRequestNumber("14");
            l_aioOrderUnitParams.setBranchId(l_branchParams.getBranchId());
            l_aioOrderUnitParams.setOrderCateg(OrderCategEnum.FX);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            
            //AioOrderParams
            TestDBUtility.deleteAll(AioOrderParams.TYPE);
            AioOrderParams l_aioOrderParams =
                TestDBUtility.getAioOrderRow();
            l_aioOrderParams.setOrderId(l_aioOrderUnitParams.getOrderId());
            TestDBUtility.insertWithDel(l_aioOrderParams);

            l_impl.submitOrder(l_request);
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisResult = l_queryProcessor.doFindAllQuery(AioOrderUnitParams.TYPE);
            assertEquals(l_lisResult.size(), 1);
            
            AioOrderUnitRow l_aioOrderUnitRow =
                (AioOrderUnitRow)l_lisResult.get(0);

            assertTrue(l_aioOrderUnitParams.getBizDate().equals(l_aioOrderUnitRow.getBizDate()));
            assertTrue(l_aioOrderUnitParams.getDeliveryDate().equals(l_aioOrderUnitRow.getDeliveryDate()));
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitOrderCase_SoapT01()
    {
        final String STR_METHOD_NAME = "testSubmitOrderCase_SoapT01()";
        log.entering(STR_METHOD_NAME);

        WEB3FXTransToFXServiceImpl l_impl = new WEB3FXTransToFXServiceImpl();
        
        WEB3FXTransToFXCompleteSoapRequest l_request = new WEB3FXTransToFXCompleteSoapRequest();
        
        l_request.fxSystemCode= "05";

        try
        { 
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            l_compFxConditionParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            l_compFxConditionParams.setBranchCode(l_branchParams.getBranchCode());
            l_compFxConditionParams.setFxSystemCode("05");
            l_compFxConditionParams.setFxSystemDiv("2");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);

            
            TestDBUtility.deleteAllAndCommit(GftTransferStatusParams.TYPE);
            TestDBUtility.deleteAll(SoapConnectPrefRpcParams.TYPE);
   
            l_impl.submitOrder(l_request);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_03075);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitOrderCase_SoapT02()
    {
        final String STR_METHOD_NAME = "testSubmitOrderCase_SoapT02()";
        log.entering(STR_METHOD_NAME);

        WEB3FXTransToFXServiceImpl l_FXTransToFXServiceImpl = new WEB3FXTransToFXServiceImplForTest();
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));         

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("381");
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);
    
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            l_mainAccountParams.setCfdAccOpenDiv("1");
            l_mainAccountParams.setTransferCount(3);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            
            SoapConnectPrefRpcParams l_soapConnectPrefRpcParams = new SoapConnectPrefRpcParams();
            TestDBUtility.deleteAll(SoapConnectPrefRpcParams.TYPE);
            l_soapConnectPrefRpcParams.setBranchId(l_mainAccountParams.getBranchId());
            l_soapConnectPrefRpcParams.setConnectDiv(WEB3ConnectDivDef.GFT);
            l_soapConnectPrefRpcParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_soapConnectPrefRpcParams.setEndpointName("aaa");
            l_soapConnectPrefRpcParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_soapConnectPrefRpcParams.setLastUpdater("sss");
            l_soapConnectPrefRpcParams.setOperationName("operationname");
            l_soapConnectPrefRpcParams.parameter_list = "";
            l_soapConnectPrefRpcParams.parameter_type_list = "";
            l_soapConnectPrefRpcParams.setPortTypeName("porttypename");
            l_soapConnectPrefRpcParams.setResponseParamType("responseparamstype");
            l_soapConnectPrefRpcParams.setResponseTimeout("5");
            l_soapConnectPrefRpcParams.setServiceName("servisename");
            l_soapConnectPrefRpcParams.setTargetNamespaceName("TargetNamespaceName");
            TestDBUtility.insertWithDel(l_soapConnectPrefRpcParams);

            
            GftTransferStatusParams l_gftTransferStatusParams = TestDBUtility.getGftTransferStatusRow();
            TestDBUtility.deleteAllAndCommit(GftTransferStatusRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_gftTransferStatusParams);
            

            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            l_compFxConditionParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setFxSystemCode("01");
            l_compFxConditionParams.setFxSystemDiv("2");
            l_compFxConditionParams.setExtConnectSystemCode("01");//<<<<<<<<<<<<<<<<<<<<<<<<<<<<
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);
  
           
            Services.unregisterService(WEB3FXTelegramProcessService.class);
            Services.registerService(
                    WEB3FXTelegramProcessService.class,
                    new WEB3FXTelegramProcessServiceImplForTest());
            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.aio.WEB3FXDataControlServiceImpl", 
//                    "validateConnect",
//                    new Class[] {SoapConnectPrefRpcParams.class},
//                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3FXDataControlServiceImpl", 
                    "validateSetup",
                    new Class[] {SoapConnectPrefRpcParams.class},
                    null);
            
            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.aio.WEB3FXDataControlServiceImpl", 
//                    "getHashValue",
//                    new Class[] {String.class, String.class, String.class},
//                    "111");
            
            String l_requestResponse = "";
//            com.gftforex.soap.api.ResultInfoBase resin = new ResultInfoBase();
//            resin.setMajorStatusCode(1);
//            resin.setMinorStatusCode(2);
//            l_requestResponse.setSendSyncRequestResult(resin);
            
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3FXDataControlServiceImpl", 
                    "sendSoapMessage",
                    new Class[] {
                            WEB3FXGftAskingTelegramUnit.class,
                            SoapConnectPrefRpcParams.class},
                    l_requestResponse);
 
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3FXDataControlServiceImpl",
                    "updateGFTTransferStatus", 
                    new Class[]{ String.class, String.class, String.class, String.class},
                    null);

            WEB3FXGftResultNoticeTelegramUnit l_unit = new WEB3FXGftResultNoticeTelegramUnit();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3FXTransConnectionImpl",
                    "doTransfer", 
                    new Class[]{  CompFxConditionParams.class, WEB3FXGftAskingTelegramUnit.class},
                    l_unit);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "lockAccount",
                    new Class[] {String.class,String.class,String.class},
                    null);

            WEB3FXTransToFXCompleteSoapRequest l_request = new WEB3FXTransToFXCompleteSoapRequest();
            l_request.fxSystemCode ="01";
            WEB3FXTransToFXCompleteSoapResponse l_response = 
                l_FXTransToFXServiceImpl.submitOrder(l_request);
            
            
            assertEquals(l_response.lastUpdatedTimestamp,WEB3DateUtility.getDate("20081010", "yyyyMMdd"));
            assertEquals(l_response.orderActionId,"102");
            assertEquals(l_response.deliveryDate,WEB3DateUtility.getDate("20081011", "yyyyMMdd"));
        
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitOrderCase_SoapT03()
    {
        final String STR_METHOD_NAME = "testSubmitOrderCase_SoapT03()";
        log.entering(STR_METHOD_NAME);

        WEB3FXTransToFXServiceImpl l_FXTransToFXServiceImpl = new WEB3FXTransToFXServiceImplForTest();
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));         

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("381");
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);
    
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            l_mainAccountParams.setCfdAccOpenDiv("1");
            l_mainAccountParams.setTransferCount(3);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            
            SoapConnectPrefRpcParams l_soapConnectPrefRpcParams = new SoapConnectPrefRpcParams();
            TestDBUtility.deleteAll(SoapConnectPrefRpcParams.TYPE);
            l_soapConnectPrefRpcParams.setBranchId(l_mainAccountParams.getBranchId());
            l_soapConnectPrefRpcParams.setConnectDiv(WEB3ConnectDivDef.GFT);
            l_soapConnectPrefRpcParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_soapConnectPrefRpcParams.setEndpointName("aaa");
            l_soapConnectPrefRpcParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_soapConnectPrefRpcParams.setLastUpdater("sss");
            l_soapConnectPrefRpcParams.setOperationName("operationname");
            l_soapConnectPrefRpcParams.parameter_list = "";
            l_soapConnectPrefRpcParams.parameter_type_list = "";
            l_soapConnectPrefRpcParams.setPortTypeName("porttypename");
            l_soapConnectPrefRpcParams.setResponseParamType("responseparamstype");
            l_soapConnectPrefRpcParams.setResponseTimeout("5");
            l_soapConnectPrefRpcParams.setServiceName("servisename");
            l_soapConnectPrefRpcParams.setTargetNamespaceName("TargetNamespaceName");
            TestDBUtility.insertWithDel(l_soapConnectPrefRpcParams);

            
            GftTransferStatusParams l_gftTransferStatusParams = TestDBUtility.getGftTransferStatusRow();
            l_gftTransferStatusParams.setInstitutionCode("0D");
            l_gftTransferStatusParams.setBranchCode("381");
            l_gftTransferStatusParams.setOrderRequestNumber("14");
            TestDBUtility.deleteAllAndCommit(GftTransferStatusRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_gftTransferStatusParams);
            

            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            l_compFxConditionParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setFxSystemCode("01");
            l_compFxConditionParams.setFxSystemDiv("2");
            l_compFxConditionParams.setExtConnectSystemCode("02");//<<<<<<<<<<<<<<<<<<<<<<<<<<<<
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);
  
           
            Services.unregisterService(WEB3FXTelegramProcessService.class);
            Services.registerService(
                    WEB3FXTelegramProcessService.class,
                    new WEB3FXTelegramProcessServiceImplForTest());
            
           
            
            String l_requestResponse = "";
//            com.gftforex.soap.api.ResultInfoBase resin = new ResultInfoBase();
//            resin.setMajorStatusCode(1);
//            resin.setMinorStatusCode(2);
//            l_requestResponse.setSendSyncRequestResult(resin);
            
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3FXDataControlServiceImpl", 
                    "sendSoapMessage",
                    new Class[] {
                            WEB3FXGftAskingTelegramUnit.class,
                            SoapConnectPrefRpcParams.class},
                    l_requestResponse);
 
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3FXDataControlServiceImpl", 
                    "sendSoapMessage", new Class[]
                    { WEB3FXGftAskingTelegramUnit.class, SoapConnectPrefRpcParams.class },
                    WEB3SoapResultCodeDef.NORMAL);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3FXDataControlServiceImpl",
                    "updateGFTTransferStatus", 
                    new Class[]{ String.class, String.class, String.class, String.class},
                    null);
 
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "lockAccount",
                    new Class[] {String.class,String.class,String.class},
                    null);

//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.aio.WEB3FXDataControlServiceImpl", 
//                    "validateConnect",
//                    new Class[] {SoapConnectPrefRpcParams.class},
//                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3FXDataControlServiceImpl", 
                    "validateSetup",
                    new Class[] {SoapConnectPrefRpcParams.class},
                    null);
            
            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.aio.WEB3FXDataControlServiceImpl", 
//                    "getHashValue",
//                    new Class[] {String.class, String.class, String.class},
//                    "111");
            
            WEB3FXTransToFXCompleteSoapRequest l_request = new WEB3FXTransToFXCompleteSoapRequest();
            l_request.fxSystemCode ="01";
            WEB3FXTransToFXCompleteSoapResponse l_response = 
                l_FXTransToFXServiceImpl.submitOrder(l_request);

            assertEquals(l_response.lastUpdatedTimestamp,WEB3DateUtility.getDate("20081010", "yyyyMMdd"));
            assertEquals(l_response.orderActionId,"102");
            assertEquals(l_response.deliveryDate,WEB3DateUtility.getDate("20081011", "yyyyMMdd"));

        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitOrderCase_SoapT04()
    {
        final String STR_METHOD_NAME = "testSubmitOrderCase_SoapT04()";
        log.entering(STR_METHOD_NAME);

        WEB3FXTransToFXServiceImpl l_FXTransToFXServiceImpl = new WEB3FXTransToFXServiceImpl();
        try
        {


            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));         
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImplForMock());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl", 
                    "getNewNumber",
                    new Class[] {String.class, String.class, ProductTypeEnum.class },
                    new String("831214"));
                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(123456));
                
                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                        "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                        "getSessionProperty",
                        new Class[] {String.class},
                        "1111");
                
                WEB3TPTradingPowerResult l_result = new WEB3TPTradingPowerResult();
                l_result.setResultFlg(true);
                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                        "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                        "validateTradingPower",
                        new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                        l_result);
                
                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                        "webbroker3.aio.WEB3AioOrderManager",
                        "submitTransferOrder",
                        new Class[] {SubAccount.class, ProductTypeEnum.class, OrderTypeEnum.class, NewOrderSpec.class,
                                AioOrderManagerPersistenceEventInterceptor.class, long.class, String.class},
                       null);
            
              TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "reCalcTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class},
                    null);
          
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("381");
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            l_mainAccountParams.setCfdAccOpenDiv("1");
            l_mainAccountParams.setTransferCount(3);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.commit();
            
            SoapConnectPrefRpcParams l_soapConnectPrefRpcParams = new SoapConnectPrefRpcParams();
            TestDBUtility.deleteAll(SoapConnectPrefRpcParams.TYPE);
            l_soapConnectPrefRpcParams.setBranchId(l_mainAccountParams.getBranchId());
            l_soapConnectPrefRpcParams.setConnectDiv(WEB3ConnectDivDef.GFT);
            l_soapConnectPrefRpcParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_soapConnectPrefRpcParams.setEndpointName("aaa");
            l_soapConnectPrefRpcParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_soapConnectPrefRpcParams.setLastUpdater("sss");
            l_soapConnectPrefRpcParams.setOperationName("operationname");
            l_soapConnectPrefRpcParams.parameter_list = "";
            l_soapConnectPrefRpcParams.parameter_type_list = "";
            l_soapConnectPrefRpcParams.setPortTypeName("porttypename");
            l_soapConnectPrefRpcParams.setResponseParamType("responseparamstype");
            l_soapConnectPrefRpcParams.setResponseTimeout("5");
            l_soapConnectPrefRpcParams.setServiceName("servisename");
            l_soapConnectPrefRpcParams.setTargetNamespaceName("TargetNamespaceName");
            TestDBUtility.insertWithDel(l_soapConnectPrefRpcParams);

            
            GftTransferStatusParams l_gftTransferStatusParams = TestDBUtility.getGftTransferStatusRow();
            l_gftTransferStatusParams.setInstitutionCode("0D");
            l_gftTransferStatusParams.setBranchCode("381");
            l_gftTransferStatusParams.setOrderRequestNumber("14");
            TestDBUtility.deleteAllAndCommit(GftTransferStatusRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_gftTransferStatusParams);
            

            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            l_compFxConditionParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setFxSystemCode("01");
            l_compFxConditionParams.setFxSystemDiv("2");
            l_compFxConditionParams.setExtConnectSystemCode("03");//<<<<<<<<<<<<<<<<<<<<<<<<<<<<
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);
            TestDBUtility.deleteAll(FxAccountParams.TYPE);
            FxAccountParams l_fxAccountParams =
                TestDBUtility.getFxAccountRow();
            l_fxAccountParams.setInstitutionCode("0D");
            l_fxAccountParams.setBranchCode("381");
            l_fxAccountParams.setFxSystemCode("01");
            l_fxAccountParams.setAccountCode("2512246");
            TestDBUtility.insertWithDel(l_fxAccountParams);
            Services.unregisterService(WEB3FXTelegramProcessService.class);
            Services.registerService(
                    WEB3FXTelegramProcessService.class,
                    new WEB3FXTelegramProcessServiceImplForTest());
            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.aio.WEB3FXDataControlServiceImpl", 
//                    "validateConnect",
//                    new Class[] {SoapConnectPrefRpcParams.class},
//                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3FXDataControlServiceImpl", 
                    "validateSetup",
                    new Class[] {SoapConnectPrefRpcParams.class},
                    null);
            
            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.aio.WEB3FXDataControlServiceImpl", 
//                    "getHashValue",
//                    new Class[] {String.class, String.class, String.class},
//                    "111");
            
            String l_requestResponse = "";
//            com.gftforex.soap.api.ResultInfoBase resin = new ResultInfoBase();
//            resin.setMajorStatusCode(1);
//            resin.setMinorStatusCode(2);
//            l_requestResponse.setSendSyncRequestResult(resin);
            
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3FXDataControlServiceImpl", 
                    "sendSoapMessage",
                    new Class[] {
                            WEB3FXGftAskingTelegramUnit.class,
                            SoapConnectPrefRpcParams.class},
                    l_requestResponse);
            GftTransferStatusParams l_GftTransferStatusParams = TestDBUtility.getGftTransferStatusRow();
            l_GftTransferStatusParams.setInstitutionCode("0D");
            l_GftTransferStatusParams.setBranchCode("381");
            l_GftTransferStatusParams.setOrderRequestNumber("14");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3FXDataControlServiceImpl",
                    "updateGFTTransferStatus", 
                    new Class[]{ String.class, String.class, String.class, String.class},
                    l_GftTransferStatusParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3FXDataControlServiceImpl",
                    "validateChangePoss", 
                    new Class[]{ SubAccount.class, CompFxConditionParams.class},
                    null);
 
            WEB3FXAccInformationUnit fxAccInformationUnit = new WEB3FXAccInformationUnit();
            fxAccInformationUnit.fxAccountCode = "12";
            fxAccInformationUnit.fxCourseDiv = "1";
            WEB3FXTransToFXCompleteSoapRequest l_request = new WEB3FXTransToFXCompleteSoapRequest();
            l_request.fxSystemCode ="01";
            l_request.wolfSession = "sad";
            l_request.wolfAid = "111";
            l_request.regetServiceId = "222";
            l_request.wolfSsid = "333";
            l_request.transferAmount = "100";
            l_request.password = "123456";
            l_request.fxAccInformationUnit = fxAccInformationUnit;
            WEB3FXTransToFXCompleteSoapResponse l_response = 
                l_FXTransToFXServiceImpl.submitOrder(l_request);

        
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_01309);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitOrderCase_SoapT05()
    {

        final String STR_METHOD_NAME = "testSubmitOrderCase_SoapT05()";
        log.entering(STR_METHOD_NAME);

        WEB3FXTransToFXServiceImpl l_impl = new WEB3FXTransToFXServiceImpl();
        
        WEB3FXTransToFXCompleteSoapRequest l_request = new WEB3FXTransToFXCompleteSoapRequest();
        
        l_request.fxSystemCode= "01";

        try
        { 
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            SoapConnectPrefRpcParams l_soapConnectPrefRpcParams = new SoapConnectPrefRpcParams();
            TestDBUtility.deleteAll(SoapConnectPrefRpcParams.TYPE);
            l_soapConnectPrefRpcParams.setBranchId(l_mainAccountParams.getBranchId());
            l_soapConnectPrefRpcParams.setConnectDiv(WEB3ConnectDivDef.GFT);
            l_soapConnectPrefRpcParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_soapConnectPrefRpcParams.setEndpointName("aaa");
            l_soapConnectPrefRpcParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_soapConnectPrefRpcParams.setLastUpdater("sss");
            l_soapConnectPrefRpcParams.setOperationName("operationname");
            l_soapConnectPrefRpcParams.parameter_list = "";
            l_soapConnectPrefRpcParams.parameter_type_list = "";
            l_soapConnectPrefRpcParams.setPortTypeName("porttypename");
            l_soapConnectPrefRpcParams.setResponseParamType("responseparamstype");
            l_soapConnectPrefRpcParams.setResponseTimeout("5");
            l_soapConnectPrefRpcParams.setServiceName("servisename");
            l_soapConnectPrefRpcParams.setTargetNamespaceName("TargetNamespaceName");
            TestDBUtility.insertWithDel(l_soapConnectPrefRpcParams);
            
            TestDBUtility.deleteAll(CompFxConditionParams.TYPE);

            l_impl.submitOrder(l_request);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80005);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    
    }

    public void testStartOrder_Case008()
    {
        final String STR_METHOD_NAME = "testStartOrder_Case008()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FXTransToFXAskingRequest l_request =
                new WEB3FXTransToFXAskingRequest();
            l_request.wolfSession = "session";
            l_request.regetServiceId = "123";
            l_request.wolfAid = "456";
            l_request.wolfSsid = "789";
            
            WEB3FXAccInformationUnit fxAccInformationUnit =
                new WEB3FXAccInformationUnit();
            fxAccInformationUnit.fxCourseDiv = "0";
            fxAccInformationUnit.fxAccountCode = "123456";
            l_request.fxAccInformationUnit = fxAccInformationUnit;
            l_request.transferAmount = "100";
            l_request.fxSystemCode = "07";
            
            QueryProcessor l_processors = Processors.getDefaultProcessor();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getAccountId",
                new Class[] {},
                new Long(123456));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl", 
                    "getNewNumber",
                    new Class[] {String.class, String.class, ProductTypeEnum.class },
                    new String("831214"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "validateOrder",
                new Class[] {SubAccount.class},
                null);
            
            //MainAccountParams
            l_processors.doDeleteAllQuery(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123456);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setInstitutionId(33);
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setBranchId(123);
            l_mainAccountParams.setAccountType(MainAccountTypeEnum.CORPORATE_ACCOUNT);
            l_mainAccountParams.setTransferCount(4);
            l_mainAccountParams.setCfdAccOpenDiv("1");
            l_mainAccountParams.setIfoAccOpenDivTokyo("2");
            
            l_processors.doInsertQuery(l_mainAccountParams);

            //SubAccountParams
            l_processors.doDeleteAllQuery(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(123456);
            l_subAccountParams.setInstitutionCode("0D");
            l_subAccountParams.setInstitutionId(33);
            l_subAccountParams.setBranchId(123);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_processors.doInsertQuery(l_subAccountParams);
            
            //CompFxConditionRow
            l_processors.doDeleteAllQuery(CompFxConditionRow.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("624");
            l_compFxConditionParams.setFxSystemCode("07");
            l_compFxConditionParams.setFxSystemDiv("1");//<<<<<<<<<<<<<<<<<<<<
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            l_processors.doInsertQuery(l_compFxConditionParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20070215", "yyyyMMdd"));
            
            //InstitutionParams
            l_processors.doDeleteAllQuery(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33);
            l_processors.doInsertQuery(l_institutionParams);
            
            //BranchParams
            l_processors.doDeleteAllQuery(BranchParams.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(123);
            l_processors.doInsertQuery(l_branchParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());
                
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(123456));
            
            //TraderParams
            l_processors.doDeleteAllQuery(TraderParams.TYPE);
            TraderParams l_traderParams =
                TestDBUtility.getTraderRow();
            l_traderParams.setTraderId(123456);
            l_processors.doInsertQuery(l_traderParams);
            
            //ProductParams
            l_processors.doDeleteAllQuery(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123456789);
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductType(ProductTypeEnum.CASH);
            l_processors.doInsertQuery(l_productParams);
            
            //
            WEB3TPTradingPowerResult l_powerResult = new WEB3TPTradingPowerResult();
            l_powerResult.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {
                    WEB3GentradeSubAccount.class,
                    Object[].class,
                    Object[].class,
                    OrderTypeEnum.class,
                    boolean.class},
                l_powerResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "reCalcTradingPower",
                new Class[] {WEB3GentradeSubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "submitTransferOrder",
                new Class[]{
                    SubAccount.class, ProductTypeEnum.class, OrderTypeEnum.class, NewOrderSpec.class,
                    AioOrderManagerPersistenceEventInterceptor.class, long.class, String.class},
                null);
            
            //FxAccountParams
            l_processors.doDeleteAllQuery(FxAccountParams.TYPE);
            FxAccountParams l_fxAccountParams =
                TestDBUtility.getFxAccountRow();
            l_fxAccountParams.setInstitutionCode("0D");
            l_fxAccountParams.setBranchCode("624");
            l_fxAccountParams.setFxSystemCode("07");
            l_fxAccountParams.setAccountCode(l_mainAccountParams.getAccountCode());
            l_processors.doInsertQuery(l_fxAccountParams);

            //GftTransferStatusParams
            l_processors.doDeleteAllQuery(GftTransferStatusParams.TYPE);
            
            WEB3FXTransToFXAskingResponse r =   l_impl.startOrder(l_request);
            List l_lisResult = l_processors.doFindAllQuery(GftTransferStatusParams.TYPE);
            assertEquals(1, l_lisResult.size());
            assertEquals("07", ((GftTransferStatusRow)l_lisResult.get(0)).getFxSystemCode());

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

		/**
		 *  【WEB3】【入出金】単体テスト仕様兼報告書 NO:1222
		 * 証券会社プリファ@レンス檢索到數據的情況
		 *testStartOrder_Case009
		 */
    public void testStartOrder_Case009()
    {
        final String STR_METHOD_NAME = "testStartOrder_Case009()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FXTransToFXAskingRequest l_request =
                new WEB3FXTransToFXAskingRequest();
            l_request.wolfSession = "session";
            l_request.regetServiceId = "123";
            l_request.wolfAid = "456";
            l_request.wolfSsid = "789";
            
            WEB3FXAccInformationUnit fxAccInformationUnit =
                new WEB3FXAccInformationUnit();
            fxAccInformationUnit.fxCourseDiv = "0";
            fxAccInformationUnit.fxAccountCode = "123456";
            l_request.fxAccInformationUnit = fxAccInformationUnit;
            l_request.transferAmount = "100";
            l_request.fxSystemCode = "07";
            
            QueryProcessor l_processors = Processors.getDefaultProcessor();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getAccountId",
                new Class[] {},
                new Long(123456));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl", 
                    "getNewNumber",
                    new Class[] {String.class, String.class, ProductTypeEnum.class },
                    new String("831214"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "validateOrder",
                new Class[] {SubAccount.class},
                null);
            
            //MainAccountParams
            l_processors.doDeleteAllQuery(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123456);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setInstitutionId(33);
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setBranchId(123);
            l_mainAccountParams.setAccountType(MainAccountTypeEnum.CORPORATE_ACCOUNT);
            l_mainAccountParams.setTransferCount(4);
            l_mainAccountParams.setCfdAccOpenDiv("1");
            l_mainAccountParams.setIfoAccOpenDivTokyo("2");
            
            l_processors.doInsertQuery(l_mainAccountParams);

            //SubAccountParams
            l_processors.doDeleteAllQuery(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(123456);
            l_subAccountParams.setInstitutionCode("0D");
            l_subAccountParams.setInstitutionId(33);
            l_subAccountParams.setBranchId(123);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_processors.doInsertQuery(l_subAccountParams);
            
            //CompFxConditionRow
            l_processors.doDeleteAllQuery(CompFxConditionRow.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("624");
            l_compFxConditionParams.setFxSystemCode("07");
            l_compFxConditionParams.setFxSystemDiv("1");//<<<<<<<<<<<<<<<<<<<<
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            l_processors.doInsertQuery(l_compFxConditionParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20070215", "yyyyMMdd"));
            
            //InstitutionParams
            l_processors.doDeleteAllQuery(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33);
            l_processors.doInsertQuery(l_institutionParams);
            
            //BranchParams
            l_processors.doDeleteAllQuery(BranchParams.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(123);
            l_processors.doInsertQuery(l_branchParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());
                
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(123456));
            
            //TraderParams
            l_processors.doDeleteAllQuery(TraderParams.TYPE);
            TraderParams l_traderParams =
                TestDBUtility.getTraderRow();
            l_traderParams.setTraderId(123456);
            l_processors.doInsertQuery(l_traderParams);
            
            //ProductParams
            l_processors.doDeleteAllQuery(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123456789);
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductType(ProductTypeEnum.CASH);
            l_processors.doInsertQuery(l_productParams);
            
            //
            WEB3TPTradingPowerResult l_powerResult = new WEB3TPTradingPowerResult();
            l_powerResult.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {
                    WEB3GentradeSubAccount.class,
                    Object[].class,
                    Object[].class,
                    OrderTypeEnum.class,
                    boolean.class},
                l_powerResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "reCalcTradingPower",
                new Class[] {WEB3GentradeSubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "submitTransferOrder",
                new Class[]{
                    SubAccount.class, ProductTypeEnum.class, OrderTypeEnum.class, NewOrderSpec.class,
                    AioOrderManagerPersistenceEventInterceptor.class, long.class, String.class},
                null);
            
            //FxAccountParams
            l_processors.doDeleteAllQuery(FxAccountParams.TYPE);
            FxAccountParams l_fxAccountParams =
                TestDBUtility.getFxAccountRow();
            l_fxAccountParams.setInstitutionCode("0D");
            l_fxAccountParams.setBranchCode("624");
            l_fxAccountParams.setFxSystemCode("07");
            l_fxAccountParams.setFxLoginId(123456789l);
            l_fxAccountParams.setAccountCode(l_mainAccountParams.getAccountCode());
            l_processors.doInsertQuery(l_fxAccountParams);

            //GftTransferStatusParams
            l_processors.doDeleteAllQuery(GftTransferStatusParams.TYPE);
            
        	TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
        	InstitutionPreferencesParams l_institutionPreferencesParams =
                TestDBUtility.getInstitutionPreferencesRow();
        	l_institutionPreferencesParams.setName("fx.fxloginid.change.div");
        	l_institutionPreferencesParams.setValue("07");
        	 l_institutionPreferencesParams.setNameSerialNo(1);
        	 TestDBUtility.insertWithDel(l_institutionPreferencesParams);
            WEB3FXTransToFXAskingResponse r =   l_impl.startOrder(l_request);

            //get変換FXログイン
            //[引数] 
            // 証券会社ID：　@補助口座.getInstitution.getInstitutionId() 
            // FXシステムコード：　@会社別FXシステム条件Params.FXシステムコード 
            // FXログインID頭文字：　@会社別FXシステム条件Params.FXログインID頭文字 
            // FXログインID：　@FX顧客Params.FXログインID
            //補助口座.getInstitution.getInstitutionId()=33l
            //会社別FXシステム条件Params.FXシステムコード ="07"
            //会社別FXシステム条件Params.FXログインID頭文字 ="159"
            //FX顧客Params.FXログインID=123456789l
            
            assertEquals("159123456", r.fxGftAskingTelegramUnit.fxFirstLoginId);


        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testStartOrder_Case003()
    {
        final String STR_METOD_NAME = "testStartOrder_Case003()";
        log.entering(STR_METOD_NAME);
        try
        {
            WEB3FXTransToFXAskingRequest l_request =
                new WEB3FXTransToFXAskingRequest();
            l_request.wolfSession = "session";
            l_request.regetServiceId = "123";
            l_request.wolfAid = "456";
            l_request.wolfSsid = "789";
            
            WEB3FXAccInformationUnit fxAccInformationUnit =
                new WEB3FXAccInformationUnit();
            fxAccInformationUnit.fxCourseDiv = "0";
            fxAccInformationUnit.fxAccountCode = "123456";
            l_request.fxAccInformationUnit = fxAccInformationUnit;
            l_request.transferAmount = "100";
            l_request.fxSystemCode = "07";
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            l_compFxConditionParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_compFxConditionParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_compFxConditionParams.setFxSystemCode(l_request.fxSystemCode);
            l_compFxConditionParams.setFxSystemDiv("2");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode(l_mainAccountParams.getBranchCode());
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "validateOrder",
                new Class[] {SubAccount.class},
                null);

            TestDBUtility.deleteAll(FxTransferMasterRow.TYPE);
            l_impl.startOrder(l_request);
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METOD_NAME);
    }
    
    public void testStartOrder_Case004()
    {
        final String STR_METOD_NAME = "testStartOrder_Case004()";
        log.entering(STR_METOD_NAME);
        try
        {
            WEB3FXTransToFXAskingRequest l_request =
                new WEB3FXTransToFXAskingRequest();
            l_request.wolfSession = "session";
            l_request.regetServiceId = "123";
            l_request.wolfAid = "456";
            l_request.wolfSsid = "789";
            
            WEB3FXAccInformationUnit fxAccInformationUnit =
                new WEB3FXAccInformationUnit();
            fxAccInformationUnit.fxCourseDiv = "0";
            fxAccInformationUnit.fxAccountCode = "123456";
            l_request.fxAccInformationUnit = fxAccInformationUnit;
            l_request.transferAmount = "100";
            l_request.fxSystemCode = "07";
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("1");
            l_mainAccountParams.setIfoAccOpenDivOsaka("0");
            l_mainAccountParams.setIfoAccOpenDivNagoya("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_compFxConditionParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_compFxConditionParams.setFxSystemCode(l_request.fxSystemCode);
            l_compFxConditionParams.setFxSystemDiv("1");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);

            TestDBUtility.deleteAll(FxTransferMasterParams.TYPE);
            FxTransferMasterParams l_fxTransferMasterParams =
                TestDBUtility.getFxTransferMasterParams();
            l_fxTransferMasterParams.setFxSystemId(l_compFxConditionParams.getFxSystemId());
            l_fxTransferMasterParams.setTransferDiv("1");
            TestDBUtility.insertWithDel(l_fxTransferMasterParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode(l_mainAccountParams.getBranchCode());
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "validateOrder",
                new Class[] {SubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "validateOtherSystemAcceptPossible",
                new Class[] {String.class},
                null);

            l_impl.startOrder(l_request);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00284);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METOD_NAME);
    }
    
    public void testStartOrder_Case005()
    {
        final String STR_METOD_NAME = "testStartOrder_Case005()";
        log.entering(STR_METOD_NAME);
        try
        {
            WEB3FXTransToFXAskingRequest l_request =
                new WEB3FXTransToFXAskingRequest();
            l_request.wolfSession = "session";
            l_request.regetServiceId = "123";
            l_request.wolfAid = "456";
            l_request.wolfSsid = "789";
            
            WEB3FXAccInformationUnit fxAccInformationUnit =
                new WEB3FXAccInformationUnit();
            fxAccInformationUnit.fxCourseDiv = "0";
            fxAccInformationUnit.fxAccountCode = "123456";
            l_request.fxAccInformationUnit = fxAccInformationUnit;
            l_request.transferAmount = "100";
            l_request.fxSystemCode = "07";
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("2");
            l_mainAccountParams.setIfoAccOpenDivOsaka("2");
            l_mainAccountParams.setIfoAccOpenDivNagoya("2");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_compFxConditionParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_compFxConditionParams.setFxSystemCode(l_request.fxSystemCode);
            l_compFxConditionParams.setFxSystemDiv("1");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);

            TestDBUtility.deleteAll(FxTransferMasterParams.TYPE);
            FxTransferMasterParams l_fxTransferMasterParams =
                TestDBUtility.getFxTransferMasterParams();
            l_fxTransferMasterParams.setFxSystemId(l_compFxConditionParams.getFxSystemId());
            l_fxTransferMasterParams.setTransferDiv("1");
            l_fxTransferMasterParams.setDeliveryDateDiv("B");
            TestDBUtility.insertWithDel(l_fxTransferMasterParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode(l_mainAccountParams.getBranchCode());
            TestDBUtility.insertWithDel(l_branchParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20090318", "yyyyMMdd"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "validateOrder",
                new Class[] {SubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "validateOtherSystemAcceptPossible",
                new Class[] {String.class},
                null);

            l_impl.startOrder(l_request);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02865, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METOD_NAME);
    }
    
    public void testStartOrder_Case001()
    {
        final String STR_METOD_NAME = "testStartOrder_Case001()";
        log.entering(STR_METOD_NAME);
        try
        {
            WEB3FXTransToFXAskingRequest l_request =
                new WEB3FXTransToFXAskingRequest();
            l_request.wolfSession = "session";
            l_request.regetServiceId = "123";
            l_request.wolfAid = "456";
            l_request.wolfSsid = "789";
            
            WEB3FXAccInformationUnit fxAccInformationUnit =
                new WEB3FXAccInformationUnit();
            fxAccInformationUnit.fxCourseDiv = "0";
            fxAccInformationUnit.fxAccountCode = "123456";
            l_request.fxAccInformationUnit = fxAccInformationUnit;
            l_request.transferAmount = "100";
            l_request.fxSystemCode = "07";
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("2");
            l_mainAccountParams.setIfoAccOpenDivOsaka("2");
            l_mainAccountParams.setIfoAccOpenDivNagoya("2");
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_compFxConditionParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_compFxConditionParams.setFxSystemCode(l_request.fxSystemCode);
            l_compFxConditionParams.setFxSystemDiv("1");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);

            TestDBUtility.deleteAll(FxTransferMasterParams.TYPE);
            FxTransferMasterParams l_fxTransferMasterParams =
                TestDBUtility.getFxTransferMasterParams();
            l_fxTransferMasterParams.setFxSystemId(l_compFxConditionParams.getFxSystemId());
            l_fxTransferMasterParams.setTransferDiv("1");
            l_fxTransferMasterParams.setDeliveryDateDiv("A");
            l_fxTransferMasterParams.setIoListTradeDiv("1");
            TestDBUtility.insertWithDel(l_fxTransferMasterParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode(l_mainAccountParams.getBranchCode());
            TestDBUtility.insertWithDel(l_branchParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20090318", "yyyyMMdd"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "validateOrder",
                new Class[] {SubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "submitTransferOrder",
                new Class[] { SubAccount.class, ProductTypeEnum.class, OrderTypeEnum.class, NewOrderSpec.class,
                        AioOrderManagerPersistenceEventInterceptor.class, long.class, String.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "validateOtherSystemAcceptPossible",
                new Class[] {String.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "validateTransferPossibleCount",
                new Class[] {SubAccount.class, Date.class, OrderCategEnum.class},
                new Integer(1));
            
            QueryProcessor l_processors = Processors.getDefaultProcessor();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            l_subAccountParams.setSubAccountId(789456123);
            l_processors.doInsertQuery(l_subAccountParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());
                
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(123456));
            
            WEB3TPTradingPowerResult l_powerResult =  new WEB3TPTradingPowerResult();
            
            l_powerResult.setResultFlg(true);
            l_powerResult.setTradingPower(123);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "validateTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                l_powerResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "getTransferAmountToEquitySubAcount",
                new Class[] {WEB3GentradeSubAccount.class, double.class, Date.class},
                new Double(100));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "reCalcTradingPower",
                new Class[] {WEB3GentradeSubAccount.class},
                null);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl", 
                    "getNewNumber",
                    new Class[] {String.class, String.class, ProductTypeEnum.class },
                    new String("831214"));
            //TraderParams
            l_processors.doDeleteAllQuery(TraderParams.TYPE);
            TraderParams l_traderParams =
                TestDBUtility.getTraderRow();
            l_traderParams.setTraderId(123456);
            l_processors.doInsertQuery(l_traderParams);
            
            //ProductParams
            l_processors.doDeleteAllQuery(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123456789);
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductType(ProductTypeEnum.CASH);
            l_processors.doInsertQuery(l_productParams);

            //FxAccountParams
            l_processors.doDeleteAllQuery(FxAccountParams.TYPE);
            FxAccountParams l_fxAccountParams =
                TestDBUtility.getFxAccountRow();
            l_fxAccountParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            l_fxAccountParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_fxAccountParams.setFxSystemCode(l_compFxConditionParams.getFxSystemCode());
            l_fxAccountParams.setAccountCode(l_mainAccountParams.getAccountCode());
            l_processors.doInsertQuery(l_fxAccountParams);
            
            l_processors.doDeleteAllQuery(GftTransferStatusRow.TYPE);
            WEB3FXTransToFXAskingResponse l_response =
                l_impl.startOrder(l_request);
            
            WEB3FXGftAskingTelegramUnit l_telegramUnit =
                l_response.fxGftAskingTelegramUnit;
            assertEquals(l_telegramUnit.deliveryDate, "20090318");
            List l_lisResult = l_processors.doFindAllQuery(GftTransferStatusRow.TYPE);
            assertEquals(1, l_lisResult.size());
            GftTransferStatusRow l_gftTransferStatusRow =
                (GftTransferStatusRow)l_lisResult.get(0);
            assertNull(l_gftTransferStatusRow.getMrgTrnOrderRequestNumber());
            assertEquals(l_gftTransferStatusRow.getIoListTradeDiv(), "1");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METOD_NAME);
    }
    
    public void testStartOrder_Case002()
    {
        final String STR_METOD_NAME = "testStartOrder_Case002()";
        log.entering(STR_METOD_NAME);
        try
        {
            WEB3FXTransToFXAskingRequest l_request =
                new WEB3FXTransToFXAskingRequest();
            l_request.wolfSession = "session";
            l_request.regetServiceId = "123";
            l_request.wolfAid = "456";
            l_request.wolfSsid = "789";
            
            WEB3FXAccInformationUnit fxAccInformationUnit =
                new WEB3FXAccInformationUnit();
            fxAccInformationUnit.fxCourseDiv = "0";
            fxAccInformationUnit.fxAccountCode = "123456";
            l_request.fxAccInformationUnit = fxAccInformationUnit;
            l_request.transferAmount = "100";
            l_request.fxSystemCode = "07";
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("2");
            l_mainAccountParams.setIfoAccOpenDivOsaka("2");
            l_mainAccountParams.setIfoAccOpenDivNagoya("2");
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_compFxConditionParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_compFxConditionParams.setFxSystemCode(l_request.fxSystemCode);
            l_compFxConditionParams.setFxSystemDiv("1");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);

            TestDBUtility.deleteAll(FxTransferMasterParams.TYPE);
            FxTransferMasterParams l_fxTransferMasterParams =
                TestDBUtility.getFxTransferMasterParams();
            l_fxTransferMasterParams.setFxSystemId(l_compFxConditionParams.getFxSystemId());
            l_fxTransferMasterParams.setTransferDiv("1");
            l_fxTransferMasterParams.setDeliveryDateDiv("1");
            l_fxTransferMasterParams.setIoListTradeDiv("1");
            TestDBUtility.insertWithDel(l_fxTransferMasterParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode(l_mainAccountParams.getBranchCode());
            TestDBUtility.insertWithDel(l_branchParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20090318", "yyyyMMdd"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "validateOrder",
                new Class[] {SubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "submitTransferOrder",
                new Class[] { SubAccount.class, ProductTypeEnum.class, OrderTypeEnum.class, NewOrderSpec.class,
                        AioOrderManagerPersistenceEventInterceptor.class, long.class, String.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "validateOtherSystemAcceptPossible",
                new Class[] {String.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "validateTransferPossibleCount",
                new Class[] {SubAccount.class, Date.class, OrderCategEnum.class},
                new Integer(1));
            
            QueryProcessor l_processors = Processors.getDefaultProcessor();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            l_subAccountParams.setSubAccountId(789456123);
            l_processors.doInsertQuery(l_subAccountParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());
                
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(123456));
            
            WEB3TPTradingPowerResult l_powerResult =  new WEB3TPTradingPowerResult();
            
            l_powerResult.setResultFlg(true);
            l_powerResult.setTradingPower(123);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "validateTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                l_powerResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "getTransferAmountToEquitySubAcount",
                new Class[] {WEB3GentradeSubAccount.class, double.class, Date.class},
                new Double(100));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "reCalcTradingPower",
                new Class[] {WEB3GentradeSubAccount.class},
                null);
           
            //TraderParams
            l_processors.doDeleteAllQuery(TraderParams.TYPE);
            TraderParams l_traderParams =
                TestDBUtility.getTraderRow();
            l_traderParams.setTraderId(123456);
            l_processors.doInsertQuery(l_traderParams);
            
            //ProductParams
            l_processors.doDeleteAllQuery(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123456789);
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductType(ProductTypeEnum.CASH);
            l_processors.doInsertQuery(l_productParams);

            //FxAccountParams
            l_processors.doDeleteAllQuery(FxAccountParams.TYPE);
            FxAccountParams l_fxAccountParams =
                TestDBUtility.getFxAccountRow();
            l_fxAccountParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            l_fxAccountParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_fxAccountParams.setFxSystemCode(l_compFxConditionParams.getFxSystemCode());
            l_fxAccountParams.setAccountCode(l_mainAccountParams.getAccountCode());
            l_processors.doInsertQuery(l_fxAccountParams);
            
            l_processors.doDeleteAllQuery(GftTransferStatusRow.TYPE);
            WEB3FXTransToFXAskingResponse l_response =
                l_impl.startOrder(l_request);
            
            WEB3FXGftAskingTelegramUnit l_telegramUnit =
                l_response.fxGftAskingTelegramUnit;
            assertEquals(l_telegramUnit.deliveryDate, "20090319");
            List l_lisResult = l_processors.doFindAllQuery(GftTransferStatusRow.TYPE);
            assertEquals(1, l_lisResult.size());
            GftTransferStatusRow l_gftTransferStatusRow =
                (GftTransferStatusRow)l_lisResult.get(0);
            assertNull(l_gftTransferStatusRow.getMrgTrnOrderRequestNumber());
            assertEquals(l_gftTransferStatusRow.getIoListTradeDiv(), "1");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METOD_NAME);
    }
    
    public void testValidateOrder_Case001()
    {
        final String STR_METHOD_NAME = "testValidateOrder_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FXTransToFXServiceImpl l_impl = new WEB3FXTransToFXServiceImpl();
            WEB3FXTransToFXConfirmRequest l_request = new WEB3FXTransToFXConfirmRequest();
            l_request.transferAmount = "101";
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            l_compFxConditionParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_compFxConditionParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_compFxConditionParams.setFxSystemCode("01");
            l_compFxConditionParams.setFxSystemDiv("2");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode(l_mainAccountParams.getBranchCode());
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.WEB3AioOrderManager",
                "validateOrder",
                new Class[] {SubAccount.class},
                null);

            TestDBUtility.deleteAll(FxTransferMasterRow.TYPE);
            l_impl.validateOrder(l_request);
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    public void testValidateOrder_Case002()
    {
        final String STR_METHOD_NAME = "testValidateOrder_Case002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FXTransToFXServiceImpl l_impl = new WEB3FXTransToFXServiceImpl();
            WEB3FXTransToFXConfirmRequest l_request = new WEB3FXTransToFXConfirmRequest();
            l_request.transferAmount = "101";
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("1");
            l_mainAccountParams.setIfoAccOpenDivOsaka("0");
            l_mainAccountParams.setIfoAccOpenDivNagoya("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_compFxConditionParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_compFxConditionParams.setFxSystemCode("01");
            l_compFxConditionParams.setFxSystemDiv("1");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);

            TestDBUtility.deleteAll(FxTransferMasterParams.TYPE);
            FxTransferMasterParams l_fxTransferMasterParams =
                TestDBUtility.getFxTransferMasterParams();
            l_fxTransferMasterParams.setFxSystemId(l_compFxConditionParams.getFxSystemId());
            l_fxTransferMasterParams.setTransferDiv("1");
            TestDBUtility.insertWithDel(l_fxTransferMasterParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode(l_mainAccountParams.getBranchCode());
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "validateOrder",
                new Class[] {SubAccount.class},
                null);

            l_impl.validateOrder(l_request);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00284);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }

    public void testValidateOrder_Case003()
    {
        final String STR_METHOD_NAME = "testValidateOrder_Case003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FXTransToFXServiceImpl l_impl = new WEB3FXTransToFXServiceImpl();
            WEB3FXTransToFXConfirmRequest l_request = new WEB3FXTransToFXConfirmRequest();
            l_request.transferAmount = "101";
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("2");
            l_mainAccountParams.setIfoAccOpenDivOsaka("2");
            l_mainAccountParams.setIfoAccOpenDivNagoya("2");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_compFxConditionParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_compFxConditionParams.setFxSystemCode("01");
            l_compFxConditionParams.setFxSystemDiv("1");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);

            TestDBUtility.deleteAll(FxTransferMasterParams.TYPE);
            FxTransferMasterParams l_fxTransferMasterParams =
                TestDBUtility.getFxTransferMasterParams();
            l_fxTransferMasterParams.setFxSystemId(l_compFxConditionParams.getFxSystemId());
            l_fxTransferMasterParams.setTransferDiv("1");
            l_fxTransferMasterParams.setDeliveryDateDiv("B");
            TestDBUtility.insertWithDel(l_fxTransferMasterParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode(l_mainAccountParams.getBranchCode());
            TestDBUtility.insertWithDel(l_branchParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20090318", "yyyyMMdd"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "validateOrder",
                new Class[] {SubAccount.class},
                null);

            l_impl.validateOrder(l_request);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02865, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }

    public void testValidateOrder_Case004()
    {
        final String STR_METHOD_NAME = "testValidateOrder_Case004()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FXTransToFXServiceImpl l_impl = new WEB3FXTransToFXServiceImpl();
            WEB3FXTransToFXConfirmRequest l_request = new WEB3FXTransToFXConfirmRequest();
            l_request.transferAmount = "101";
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("2");
            l_mainAccountParams.setIfoAccOpenDivOsaka("2");
            l_mainAccountParams.setIfoAccOpenDivNagoya("2");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_compFxConditionParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_compFxConditionParams.setFxSystemCode("01");
            l_compFxConditionParams.setFxSystemDiv("1");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);

            TestDBUtility.deleteAll(FxTransferMasterParams.TYPE);
            FxTransferMasterParams l_fxTransferMasterParams =
                TestDBUtility.getFxTransferMasterParams();
            l_fxTransferMasterParams.setFxSystemId(l_compFxConditionParams.getFxSystemId());
            l_fxTransferMasterParams.setTransferDiv("1");
            l_fxTransferMasterParams.setDeliveryDateDiv("1");
            TestDBUtility.insertWithDel(l_fxTransferMasterParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode(l_mainAccountParams.getBranchCode());
            TestDBUtility.insertWithDel(l_branchParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20090318", "yyyyMMdd"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "validateOrder",
                new Class[] {SubAccount.class},
                null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "validateTransferPossibleCount",
                new Class[] {SubAccount.class, Date.class, OrderCategEnum.class},
                new Integer(1));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "getProductId",
                new Class[] {Institution.class},
                new Long(123456789));

            WEB3TPTradingPowerResult l_powerResult =  new WEB3TPTradingPowerResult();
            
            l_powerResult.setResultFlg(true);
            l_powerResult.setTradingPower(123);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "validateTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                l_powerResult);
            WEB3FXTransToFXConfirmResponse l_response =
                l_impl.validateOrder(l_request);
            assertEquals(
                WEB3DateUtility.compareToDay(l_response.deliveryDate,
                WEB3DateUtility.getDate("20090319", "yyyyMMdd")), 0);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }

    public void testSubmitOrderCase_SoapT06()
    {
        final String STR_METHOD_NAME = "testSubmitOrderCase_SoapT06()";
        log.entering(STR_METHOD_NAME);

        WEB3FXTransToFXServiceImpl l_impl = new WEB3FXTransToFXServiceImpl();
        
        WEB3FXTransToFXCompleteSoapRequest l_request = new WEB3FXTransToFXCompleteSoapRequest();
        
        l_request.fxSystemCode= "05";

        try
        { 
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            l_compFxConditionParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            l_compFxConditionParams.setBranchCode(l_branchParams.getBranchCode());
            l_compFxConditionParams.setFxSystemCode("05");
            l_compFxConditionParams.setFxSystemDiv("2");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);

            
            TestDBUtility.deleteAllAndCommit(GftTransferStatusParams.TYPE);
            TestDBUtility.deleteAll(SoapConnectPrefRpcParams.TYPE);
   
            l_impl.submitOrder(l_request);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_03075);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitOrderCase_SoapT07()
    {
        final String STR_METHOD_NAME = "testSubmitOrderCase_SoapT07()";
        log.entering(STR_METHOD_NAME);

        WEB3FXTransToFXServiceImpl l_FXTransToFXServiceImpl = new WEB3FXTransToFXServiceImplForTest();
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));         

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("381");
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);
    
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            l_mainAccountParams.setCfdAccOpenDiv("1");
            l_mainAccountParams.setTransferCount(3);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            
            SoapConnectPrefRpcParams l_soapConnectPrefRpcParams = new SoapConnectPrefRpcParams();
            TestDBUtility.deleteAll(SoapConnectPrefRpcParams.TYPE);
            l_soapConnectPrefRpcParams.setBranchId(l_mainAccountParams.getBranchId());
            l_soapConnectPrefRpcParams.setConnectDiv(WEB3ConnectDivDef.GFT);
            l_soapConnectPrefRpcParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_soapConnectPrefRpcParams.setEndpointName("aaa");
            l_soapConnectPrefRpcParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_soapConnectPrefRpcParams.setLastUpdater("sss");
            l_soapConnectPrefRpcParams.setOperationName("operationname");
            l_soapConnectPrefRpcParams.parameter_list = "";
            l_soapConnectPrefRpcParams.parameter_type_list = "";
            l_soapConnectPrefRpcParams.setPortTypeName("porttypename");
            l_soapConnectPrefRpcParams.setResponseParamType("responseparamstype");
            l_soapConnectPrefRpcParams.setResponseTimeout("5");
            l_soapConnectPrefRpcParams.setServiceName("servisename");
            l_soapConnectPrefRpcParams.setTargetNamespaceName("TargetNamespaceName");
            TestDBUtility.insertWithDel(l_soapConnectPrefRpcParams);

            
            GftTransferStatusParams l_gftTransferStatusParams = TestDBUtility.getGftTransferStatusRow();
            TestDBUtility.deleteAllAndCommit(GftTransferStatusRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_gftTransferStatusParams);
            

            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            l_compFxConditionParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setFxSystemCode("01");
            l_compFxConditionParams.setFxSystemDiv("2");
            l_compFxConditionParams.setExtConnectSystemCode("01");//<<<<<<<<<<<<<<<<<<<<<<<<<<<<
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);
  
           
            Services.unregisterService(WEB3FXTelegramProcessService.class);
            Services.registerService(
                    WEB3FXTelegramProcessService.class,
                    new WEB3FXTelegramProcessServiceImplForTest());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3FXDataControlServiceImpl", 
                    "validateSetup",
                    new Class[] {SoapConnectPrefRpcParams.class},
                    null);

            String l_requestResponse = "";
//            com.gftforex.soap.api.ResultInfoBase resin = new ResultInfoBase();
//            resin.setMajorStatusCode(1);
//            resin.setMinorStatusCode(2);
//            l_requestResponse.setSendSyncRequestResult(resin);
            
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3FXDataControlServiceImpl", 
                    "sendSoapMessage",
                    new Class[] {
                            WEB3FXGftAskingTelegramUnit.class,
                            SoapConnectPrefRpcParams.class},
                    l_requestResponse);
 
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3FXDataControlServiceImpl",
                    "updateGFTTransferStatus", 
                    new Class[]{ String.class, String.class, String.class, String.class},
                    null);

            WEB3FXGftResultNoticeTelegramUnit l_unit = new WEB3FXGftResultNoticeTelegramUnit();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3FXTransConnectionImpl",
                    "doTransfer", 
                    new Class[]{  CompFxConditionParams.class, WEB3FXGftAskingTelegramUnit.class},
                    l_unit);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "lockAccount",
                    new Class[] {String.class,String.class,String.class},
                    null);

            WEB3FXTransToFXCompleteSoapRequest l_request = new WEB3FXTransToFXCompleteSoapRequest();
            l_request.fxSystemCode ="01";
            WEB3FXTransToFXCompleteSoapResponse l_response = 
                l_FXTransToFXServiceImpl.submitOrder(l_request);
            
            
            assertEquals(l_response.lastUpdatedTimestamp,WEB3DateUtility.getDate("20081010", "yyyyMMdd"));
            assertEquals(l_response.orderActionId,"102");
            assertEquals(l_response.deliveryDate,WEB3DateUtility.getDate("20081011", "yyyyMMdd"));
        
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitOrderCase_SoapT08()
    {
        final String STR_METHOD_NAME = "testSubmitOrderCase_SoapT08()";
        log.entering(STR_METHOD_NAME);

        WEB3FXTransToFXServiceImpl l_FXTransToFXServiceImpl = new WEB3FXTransToFXServiceImplForTest();
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));         

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("381");
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);
    
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            l_mainAccountParams.setCfdAccOpenDiv("1");
            l_mainAccountParams.setTransferCount(3);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            
            SoapConnectPrefRpcParams l_soapConnectPrefRpcParams = new SoapConnectPrefRpcParams();
            TestDBUtility.deleteAll(SoapConnectPrefRpcParams.TYPE);
            l_soapConnectPrefRpcParams.setBranchId(l_mainAccountParams.getBranchId());
            l_soapConnectPrefRpcParams.setConnectDiv(WEB3ConnectDivDef.GFT);
            l_soapConnectPrefRpcParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_soapConnectPrefRpcParams.setEndpointName("aaa");
            l_soapConnectPrefRpcParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_soapConnectPrefRpcParams.setLastUpdater("sss");
            l_soapConnectPrefRpcParams.setOperationName("operationname");
            l_soapConnectPrefRpcParams.parameter_list = "";
            l_soapConnectPrefRpcParams.parameter_type_list = "";
            l_soapConnectPrefRpcParams.setPortTypeName("porttypename");
            l_soapConnectPrefRpcParams.setResponseParamType("responseparamstype");
            l_soapConnectPrefRpcParams.setResponseTimeout("5");
            l_soapConnectPrefRpcParams.setServiceName("servisename");
            l_soapConnectPrefRpcParams.setTargetNamespaceName("TargetNamespaceName");
            TestDBUtility.insertWithDel(l_soapConnectPrefRpcParams);

            
            GftTransferStatusParams l_gftTransferStatusParams = TestDBUtility.getGftTransferStatusRow();
            l_gftTransferStatusParams.setInstitutionCode("0D");
            l_gftTransferStatusParams.setBranchCode("381");
            l_gftTransferStatusParams.setOrderRequestNumber("14");
            TestDBUtility.deleteAllAndCommit(GftTransferStatusRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_gftTransferStatusParams);
            

            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            l_compFxConditionParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setFxSystemCode("01");
            l_compFxConditionParams.setFxSystemDiv("2");
            l_compFxConditionParams.setExtConnectSystemCode("02");//<<<<<<<<<<<<<<<<<<<<<<<<<<<<
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);
  
           
            Services.unregisterService(WEB3FXTelegramProcessService.class);
            Services.registerService(
                    WEB3FXTelegramProcessService.class,
                    new WEB3FXTelegramProcessServiceImplForTest());
            
           
            
            String l_requestResponse = "";
//            com.gftforex.soap.api.ResultInfoBase resin = new ResultInfoBase();
//            resin.setMajorStatusCode(1);
//            resin.setMinorStatusCode(2);
//            l_requestResponse.setSendSyncRequestResult(resin);
            
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3FXDataControlServiceImpl", 
                    "sendSoapMessage",
                    new Class[] {
                            WEB3FXGftAskingTelegramUnit.class,
                            SoapConnectPrefRpcParams.class},
                    l_requestResponse);
 
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3FXDataControlServiceImpl", 
                    "sendSoapMessage", new Class[]
                    { WEB3FXGftAskingTelegramUnit.class, SoapConnectPrefRpcParams.class },
                    WEB3SoapResultCodeDef.NORMAL);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3FXDataControlServiceImpl",
                    "updateGFTTransferStatus", 
                    new Class[]{ String.class, String.class, String.class, String.class},
                    null);
 
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "lockAccount",
                    new Class[] {String.class,String.class,String.class},
                    null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3FXDataControlServiceImpl", 
                    "validateSetup",
                    new Class[] {SoapConnectPrefRpcParams.class},
                    null);

            WEB3FXTransToFXCompleteSoapRequest l_request = new WEB3FXTransToFXCompleteSoapRequest();
            l_request.fxSystemCode ="01";
            WEB3FXTransToFXCompleteSoapResponse l_response = 
                l_FXTransToFXServiceImpl.submitOrder(l_request);

            assertEquals(l_response.lastUpdatedTimestamp,WEB3DateUtility.getDate("20081010", "yyyyMMdd"));
            assertEquals(l_response.orderActionId,"102");
            assertEquals(l_response.deliveryDate,WEB3DateUtility.getDate("20081011", "yyyyMMdd"));

        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitOrderCase_SoapT09()
    {
        final String STR_METHOD_NAME = "testSubmitOrderCase_SoapT09()";
        log.entering(STR_METHOD_NAME);

        WEB3FXTransToFXServiceImpl l_FXTransToFXServiceImpl = new WEB3FXTransToFXServiceImplForTest();
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getAccountId",
                    new Class[] {},
                    new Long(266242512246L));        
            


            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("381");
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(266242512246L);
            l_mainAccountParams.setBranchId(33381);
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            l_mainAccountParams.setFxAccOpenDiv("1");
            l_mainAccountParams.setTransferCount(3);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            WEB3GentradeMainAccount l_mainaccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "getMainAccount",
                    new Class[] {long.class},
                    l_mainaccount);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(266242512246L);
            l_subAccountParams.setSubAccountId(26624251220301L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);

            SoapConnectPrefRpcParams l_soapConnectPrefRpcParams = new SoapConnectPrefRpcParams();
            TestDBUtility.deleteAll(SoapConnectPrefRpcParams.TYPE);
            l_soapConnectPrefRpcParams.setBranchId(l_mainAccountParams.getBranchId());
            l_soapConnectPrefRpcParams.setConnectDiv("06");
            l_soapConnectPrefRpcParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_soapConnectPrefRpcParams.setEndpointName("aaa");
            l_soapConnectPrefRpcParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_soapConnectPrefRpcParams.setLastUpdater("sss");
            l_soapConnectPrefRpcParams.setOperationName("fx_retela");
            l_soapConnectPrefRpcParams.parameter_list = "";
            l_soapConnectPrefRpcParams.parameter_type_list = "";
            l_soapConnectPrefRpcParams.setPortTypeName("porttypename");
            l_soapConnectPrefRpcParams.setResponseParamType("responseparamstype");
            l_soapConnectPrefRpcParams.setResponseTimeout("5");
            l_soapConnectPrefRpcParams.setServiceName("servisename");
            l_soapConnectPrefRpcParams.setTargetNamespaceName("TargetNamespaceName");
            TestDBUtility.insertWithDel(l_soapConnectPrefRpcParams);

            
            GftTransferStatusParams l_gftTransferStatusParams = TestDBUtility.getGftTransferStatusRow();
            TestDBUtility.deleteAllAndCommit(GftTransferStatusRow.TYPE);
            l_gftTransferStatusParams.setInstitutionCode("0D");
            l_gftTransferStatusParams.setBranchCode("381");
            l_gftTransferStatusParams.setAccountCode("2512246");
            l_gftTransferStatusParams.setOrderRequestNumber("1980003");
            l_gftTransferStatusParams.setOperationDiv("01");
            l_gftTransferStatusParams.setCourseDiv("0");
            l_gftTransferStatusParams.setFxAccountCode("100401");
            l_gftTransferStatusParams.setTransferStatusDiv("1");
            l_gftTransferStatusParams.setSendRcvDiv("1");
            TestDBUtility.insertWithDelAndCommit(l_gftTransferStatusParams);
            

            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            l_compFxConditionParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setFxSystemCode("06");
            l_compFxConditionParams.fx_system_div = "";
            l_compFxConditionParams.setSoapConnectDiv("1");
            l_compFxConditionParams.setExtConnectSystemCode("06");//<<<<<<<<<<<<<<<<<<<<<<<<<<<<
            l_compFxConditionParams.setGetTransferableAmtDiv("0");
            TestDBUtility.insertWithDel(l_compFxConditionParams);

            Services.unregisterService(WEB3FXTelegramProcessService.class);
            Services.registerService(
                    WEB3FXTelegramProcessService.class,
                    new WEB3FXTelegramProcessServiceImplForTest());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3FXDataControlServiceImpl", 
                    "validateSetup",
                    new Class[] {SoapConnectPrefRpcParams.class},
                    null);

            String l_requestResponse = "";
//            com.gftforex.soap.api.ResultInfoBase resin = new ResultInfoBase();
//            resin.setMajorStatusCode(1);
//            resin.setMinorStatusCode(2);
//            l_requestResponse.setSendSyncRequestResult(resin);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3FXDataControlServiceImpl", 
                    "sendSoapMessage",
                    new Class[] {
                            WEB3FXGftAskingTelegramUnit.class,
                            SoapConnectPrefRpcParams.class},
                    l_requestResponse);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3FXDataControlServiceImpl",
                    "updateGFTTransferStatus", 
                    new Class[]{ String.class, String.class, String.class, String.class},
                    null);

            WEB3FXGftResultNoticeTelegramUnit l_unit = new WEB3FXGftResultNoticeTelegramUnit();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3FXTransConnectionImpl",
                    "doTransfer", 
                    new Class[]{  CompFxConditionParams.class, WEB3FXGftAskingTelegramUnit.class},
                    l_unit);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "lockAccount",
                    new Class[] {String.class,String.class,String.class},
                    null);

            WEB3FXTransToFXCompleteSoapRequest l_request = new WEB3FXTransToFXCompleteSoapRequest();
            l_request.fxSystemCode ="06";
            WEB3FXTransToFXCompleteSoapResponse l_response = 
                l_FXTransToFXServiceImpl.submitOrder(l_request);

            assertEquals(l_response.lastUpdatedTimestamp,WEB3DateUtility.getDate("20081010", "yyyyMMdd"));
            assertEquals(l_response.orderActionId,"102");
            assertEquals(l_response.deliveryDate,WEB3DateUtility.getDate("20081011", "yyyyMMdd"));

        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitOrderCase_SoapT10()
    {
        final String STR_METHOD_NAME = "testSubmitOrderCase_SoapT10()";
        log.entering(STR_METHOD_NAME);

        WEB3FXTransToFXServiceImpl l_FXTransToFXServiceImpl = new WEB3FXTransToFXServiceImpl();
        try
        {


            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));         
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImplForMock());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl", 
                    "getNewNumber",
                    new Class[] {String.class, String.class, ProductTypeEnum.class },
                    new String("831214"));
                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(123456));
                
                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                        "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                        "getSessionProperty",
                        new Class[] {String.class},
                        "1111");
                
                WEB3TPTradingPowerResult l_result = new WEB3TPTradingPowerResult();
                l_result.setResultFlg(true);
                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                        "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                        "validateTradingPower",
                        new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                        l_result);

                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                        "webbroker3.aio.WEB3AioOrderManager",
                        "submitTransferOrder",
                        new Class[] {SubAccount.class, ProductTypeEnum.class, OrderTypeEnum.class, NewOrderSpec.class,
                                AioOrderManagerPersistenceEventInterceptor.class, long.class, String.class},
                       null);

              TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "reCalcTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class},
                    null);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("381");
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            l_mainAccountParams.setCfdAccOpenDiv("1");
            l_mainAccountParams.setTransferCount(3);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.commit();

            SoapConnectPrefRpcParams l_soapConnectPrefRpcParams = new SoapConnectPrefRpcParams();
            TestDBUtility.deleteAll(SoapConnectPrefRpcParams.TYPE);
            l_soapConnectPrefRpcParams.setBranchId(l_mainAccountParams.getBranchId());
            l_soapConnectPrefRpcParams.setConnectDiv(WEB3ConnectDivDef.GFT);
            l_soapConnectPrefRpcParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_soapConnectPrefRpcParams.setEndpointName("aaa");
            l_soapConnectPrefRpcParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_soapConnectPrefRpcParams.setLastUpdater("sss");
            l_soapConnectPrefRpcParams.setOperationName("operationname");
            l_soapConnectPrefRpcParams.parameter_list = "";
            l_soapConnectPrefRpcParams.parameter_type_list = "";
            l_soapConnectPrefRpcParams.setPortTypeName("porttypename");
            l_soapConnectPrefRpcParams.setResponseParamType("responseparamstype");
            l_soapConnectPrefRpcParams.setResponseTimeout("5");
            l_soapConnectPrefRpcParams.setServiceName("servisename");
            l_soapConnectPrefRpcParams.setTargetNamespaceName("TargetNamespaceName");
            TestDBUtility.insertWithDel(l_soapConnectPrefRpcParams);

            GftTransferStatusParams l_gftTransferStatusParams = TestDBUtility.getGftTransferStatusRow();
            l_gftTransferStatusParams.setInstitutionCode("0D");
            l_gftTransferStatusParams.setBranchCode("381");
            l_gftTransferStatusParams.setOrderRequestNumber("14");
            TestDBUtility.deleteAllAndCommit(GftTransferStatusRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_gftTransferStatusParams);

            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            l_compFxConditionParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setFxSystemCode("01");
            l_compFxConditionParams.setFxSystemDiv("2");
            l_compFxConditionParams.setExtConnectSystemCode("03");//<<<<<<<<<<<<<<<<<<<<<<<<<<<<
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);
            TestDBUtility.deleteAll(FxAccountParams.TYPE);
            FxAccountParams l_fxAccountParams =
                TestDBUtility.getFxAccountRow();
            l_fxAccountParams.setInstitutionCode("0D");
            l_fxAccountParams.setBranchCode("381");
            l_fxAccountParams.setFxSystemCode("01");
            l_fxAccountParams.setAccountCode("2512246");
            TestDBUtility.insertWithDel(l_fxAccountParams);
            Services.unregisterService(WEB3FXTelegramProcessService.class);
            Services.registerService(
                    WEB3FXTelegramProcessService.class,
                    new WEB3FXTelegramProcessServiceImplForTest());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3FXDataControlServiceImpl", 
                    "validateSetup",
                    new Class[] {SoapConnectPrefRpcParams.class},
                    null);

            String l_requestResponse = "";
//            com.gftforex.soap.api.ResultInfoBase resin = new ResultInfoBase();
//            resin.setMajorStatusCode(1);
//            resin.setMinorStatusCode(2);
//            l_requestResponse.setSendSyncRequestResult(resin);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3FXDataControlServiceImpl", 
                    "sendSoapMessage",
                    new Class[] {
                            WEB3FXGftAskingTelegramUnit.class,
                            SoapConnectPrefRpcParams.class},
                    l_requestResponse);
            GftTransferStatusParams l_GftTransferStatusParams = TestDBUtility.getGftTransferStatusRow();
            l_GftTransferStatusParams.setInstitutionCode("0D");
            l_GftTransferStatusParams.setBranchCode("381");
            l_GftTransferStatusParams.setOrderRequestNumber("14");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3FXDataControlServiceImpl",
                    "updateGFTTransferStatus", 
                    new Class[]{ String.class, String.class, String.class, String.class},
                    l_GftTransferStatusParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3FXDataControlServiceImpl",
                    "validateChangePoss", 
                    new Class[]{ SubAccount.class, CompFxConditionParams.class},
                    null);

            WEB3FXAccInformationUnit fxAccInformationUnit = new WEB3FXAccInformationUnit();
            fxAccInformationUnit.fxAccountCode = "12";
            fxAccInformationUnit.fxCourseDiv = "1";
            WEB3FXTransToFXCompleteSoapRequest l_request = new WEB3FXTransToFXCompleteSoapRequest();
            l_request.fxSystemCode ="01";
            l_request.wolfSession = "sad";
            l_request.wolfAid = "111";
            l_request.regetServiceId = "222";
            l_request.wolfSsid = "333";
            l_request.transferAmount = "100";
            l_request.password = "123456";
            l_request.fxAccInformationUnit = fxAccInformationUnit;
            WEB3FXTransToFXCompleteSoapResponse l_response = 
                l_FXTransToFXServiceImpl.submitOrder(l_request);

        }
        catch(WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_01309);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitOrderCase_SoapT11()
    {

        final String STR_METHOD_NAME = "testSubmitOrderCase_SoapT11()";
        log.entering(STR_METHOD_NAME);

        WEB3FXTransToFXServiceImpl l_impl = new WEB3FXTransToFXServiceImpl();
        
        WEB3FXTransToFXCompleteSoapRequest l_request = new WEB3FXTransToFXCompleteSoapRequest();
        
        l_request.fxSystemCode= "01";

        try
        { 
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            SoapConnectPrefRpcParams l_soapConnectPrefRpcParams = new SoapConnectPrefRpcParams();
            TestDBUtility.deleteAll(SoapConnectPrefRpcParams.TYPE);
            l_soapConnectPrefRpcParams.setBranchId(l_mainAccountParams.getBranchId());
            l_soapConnectPrefRpcParams.setConnectDiv(WEB3ConnectDivDef.GFT);
            l_soapConnectPrefRpcParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_soapConnectPrefRpcParams.setEndpointName("aaa");
            l_soapConnectPrefRpcParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_soapConnectPrefRpcParams.setLastUpdater("sss");
            l_soapConnectPrefRpcParams.setOperationName("operationname");
            l_soapConnectPrefRpcParams.parameter_list = "";
            l_soapConnectPrefRpcParams.parameter_type_list = "";
            l_soapConnectPrefRpcParams.setPortTypeName("porttypename");
            l_soapConnectPrefRpcParams.setResponseParamType("responseparamstype");
            l_soapConnectPrefRpcParams.setResponseTimeout("5");
            l_soapConnectPrefRpcParams.setServiceName("servisename");
            l_soapConnectPrefRpcParams.setTargetNamespaceName("TargetNamespaceName");
            TestDBUtility.insertWithDel(l_soapConnectPrefRpcParams);

            TestDBUtility.deleteAll(CompFxConditionParams.TYPE);

            l_impl.submitOrder(l_request);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80005);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    
    }

    public void testStartOrderCase0001()
    {
        final String STR_METHOD_NAME = "testStartOrderCase0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FXTransToFXAskingRequest l_request =
                new WEB3FXTransToFXAskingRequest();
            l_request.wolfSession = "session";
            l_request.regetServiceId = "123";
            l_request.wolfAid = "456";
            l_request.wolfSsid = "789";
            
            WEB3FXAccInformationUnit fxAccInformationUnit =
                new WEB3FXAccInformationUnit();
            fxAccInformationUnit.fxCourseDiv =
                WEB3GftTransStatusCourseDivDef.CFD_COURSE;
            fxAccInformationUnit.fxAccountCode = "123456";
            l_request.fxAccInformationUnit = fxAccInformationUnit;
            l_request.transferAmount = "100";
            l_request.fxSystemCode = "07";
            
            QueryProcessor l_processors = Processors.getDefaultProcessor();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getAccountId",
                new Class[] {},
                new Long(123456));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl", 
                    "getNewNumber",
                    new Class[] {String.class, String.class, ProductTypeEnum.class },
                    new String("831214"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "validateOrder",
                new Class[] {SubAccount.class},
                null);
            
            //MainAccountParams
            l_processors.doDeleteAllQuery(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123456);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setInstitutionId(33);
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setBranchId(123);
            l_mainAccountParams.setAccountType(MainAccountTypeEnum.CORPORATE_ACCOUNT);
            l_mainAccountParams.setTransferCount(4);
            l_mainAccountParams.setCfdAccOpenDiv("1");
            l_mainAccountParams.setIfoAccOpenDivTokyo("2");
            
            l_processors.doInsertQuery(l_mainAccountParams);

            //SubAccountParams
            l_processors.doDeleteAllQuery(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(123456);
            l_subAccountParams.setInstitutionCode("0D");
            l_subAccountParams.setInstitutionId(33);
            l_subAccountParams.setBranchId(123);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_processors.doInsertQuery(l_subAccountParams);
            
            //CompFxConditionRow
            l_processors.doDeleteAllQuery(CompFxConditionRow.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("624");
            l_compFxConditionParams.setFxSystemCode("07");
            l_compFxConditionParams.setFxSystemDiv("1");//<<<<<<<<<<<<<<<<<<<<
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            l_compFxConditionParams.setMultiCfdAccDiv("1");
            l_compFxConditionParams.setFxSystemId(6241001);
            l_processors.doInsertQuery(l_compFxConditionParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20070215", "yyyyMMdd"));
            
            //InstitutionParams
            l_processors.doDeleteAllQuery(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33);
            l_processors.doInsertQuery(l_institutionParams);
            
            //BranchParams
            l_processors.doDeleteAllQuery(BranchParams.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(123);
            l_processors.doInsertQuery(l_branchParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());
                
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(123456));
            
            //TraderParams
            l_processors.doDeleteAllQuery(TraderParams.TYPE);
            TraderParams l_traderParams =
                TestDBUtility.getTraderRow();
            l_traderParams.setTraderId(123456);
            l_processors.doInsertQuery(l_traderParams);
            
            //ProductParams
            l_processors.doDeleteAllQuery(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123456789);
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductType(ProductTypeEnum.CASH);
            l_processors.doInsertQuery(l_productParams);
            
            //
            WEB3TPTradingPowerResult l_powerResult = new WEB3TPTradingPowerResult();
            l_powerResult.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {
                    WEB3GentradeSubAccount.class,
                    Object[].class,
                    Object[].class,
                    OrderTypeEnum.class,
                    boolean.class},
                l_powerResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "reCalcTradingPower",
                new Class[] {WEB3GentradeSubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "submitTransferOrder",
                new Class[]{
                    SubAccount.class, ProductTypeEnum.class, OrderTypeEnum.class, NewOrderSpec.class,
                    AioOrderManagerPersistenceEventInterceptor.class, long.class, String.class},
                null);
            
            //FxAccountParams
            l_processors.doDeleteAllQuery(FxAccountParams.TYPE);
            FxAccountParams l_fxAccountParams =
                TestDBUtility.getFxAccountRow();
            l_fxAccountParams.setInstitutionCode("0D");
            l_fxAccountParams.setBranchCode("624");
            l_fxAccountParams.setFxSystemCode("07");
            l_fxAccountParams.setFxLoginId(123456789l);
            l_fxAccountParams.setAccountCode(l_mainAccountParams.getAccountCode());
            l_processors.doInsertQuery(l_fxAccountParams);

            //GftTransferStatusParams
            l_processors.doDeleteAllQuery(GftTransferStatusParams.TYPE);
            
            //FxTransferMasterParams
            l_processors.doDeleteAllQuery(FxTransferMasterParams.TYPE);
            FxTransferMasterParams l_fxTransferMasterParams =
                TestDBUtility.getFxTransferMasterParams();
            l_fxTransferMasterParams.setRemarkName("CFD");
            l_fxTransferMasterParams.setRemarkName2("CFD2");
            l_fxTransferMasterParams.setRemarkName3("CFD3");
            l_fxTransferMasterParams.setRemarkName4("CFD4");
            l_fxTransferMasterParams.setRemarkName5("CFD5");
            l_fxTransferMasterParams.setRemarkName6("CFD6");
            l_fxTransferMasterParams.setRemarkName7("CFD7");
            l_fxTransferMasterParams.setRemarkName8("CFD8");
            
            l_fxTransferMasterParams.setFxSystemId(6241001);
            l_fxTransferMasterParams.setTransferDiv("1");
            l_processors.doInsertQuery(l_fxTransferMasterParams);
            
            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams =
                TestDBUtility.getInstitutionPreferencesRow();
            l_institutionPreferencesParams.setName("fx.fxloginid.change.div");
            l_institutionPreferencesParams.setValue("07");
             l_institutionPreferencesParams.setNameSerialNo(1);
             TestDBUtility.insertWithDel(l_institutionPreferencesParams);
            WEB3FXTransToFXAskingResponse r =   l_impl.startOrder(l_request);

            //get変換FXログイン
            //[引数] 
            // 証券会社ID：　@補助口座.getInstitution.getInstitutionId() 
            // FXシステムコード：　@会社別FXシステム条件Params.FXシステムコード 
            // FXログインID頭文字：　@会社別FXシステム条件Params.FXログインID頭文字 
            // FXログインID：　@FX顧客Params.FXログインID
            //補助口座.getInstitution.getInstitutionId()=33l
            //会社別FXシステム条件Params.FXシステムコード ="07"
            //会社別FXシステム条件Params.FXログインID頭文字 ="159"
            //FX顧客Params.FXログインID=123456789l
            
            assertEquals("159123456", r.fxGftAskingTelegramUnit.fxFirstLoginId);


        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testStartOrderCase0002()
    {
        final String STR_METHOD_NAME = "testStartOrderCase0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FXTransToFXAskingRequest l_request =
                new WEB3FXTransToFXAskingRequest();
            l_request.wolfSession = "session";
            l_request.regetServiceId = "123";
            l_request.wolfAid = "456";
            l_request.wolfSsid = "789";
            
            WEB3FXAccInformationUnit fxAccInformationUnit =
                new WEB3FXAccInformationUnit();
            fxAccInformationUnit.fxCourseDiv =
                WEB3GftTransStatusCourseDivDef.CFD_COURSE_2;
            fxAccInformationUnit.fxAccountCode = "123456";
            l_request.fxAccInformationUnit = fxAccInformationUnit;
            l_request.transferAmount = "100";
            l_request.fxSystemCode = "07";
            
            QueryProcessor l_processors = Processors.getDefaultProcessor();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getAccountId",
                new Class[] {},
                new Long(123456));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl", 
                    "getNewNumber",
                    new Class[] {String.class, String.class, ProductTypeEnum.class },
                    new String("831214"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "validateOrder",
                new Class[] {SubAccount.class},
                null);
            
            //MainAccountParams
            l_processors.doDeleteAllQuery(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123456);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setInstitutionId(33);
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setBranchId(123);
            l_mainAccountParams.setAccountType(MainAccountTypeEnum.CORPORATE_ACCOUNT);
            l_mainAccountParams.setTransferCount(4);
            l_mainAccountParams.setCfdAccOpenDiv("1");
            l_mainAccountParams.setIfoAccOpenDivTokyo("2");
            
            l_processors.doInsertQuery(l_mainAccountParams);

            //SubAccountParams
            l_processors.doDeleteAllQuery(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(123456);
            l_subAccountParams.setInstitutionCode("0D");
            l_subAccountParams.setInstitutionId(33);
            l_subAccountParams.setBranchId(123);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_processors.doInsertQuery(l_subAccountParams);
            
            //CompFxConditionRow
            l_processors.doDeleteAllQuery(CompFxConditionRow.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("624");
            l_compFxConditionParams.setFxSystemCode("07");
            l_compFxConditionParams.setFxSystemDiv("1");//<<<<<<<<<<<<<<<<<<<<
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            l_compFxConditionParams.setMultiCfdAccDiv("1");
            l_compFxConditionParams.setFxSystemId(6241001);
            l_processors.doInsertQuery(l_compFxConditionParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20070215", "yyyyMMdd"));
            
            //InstitutionParams
            l_processors.doDeleteAllQuery(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33);
            l_processors.doInsertQuery(l_institutionParams);
            
            //BranchParams
            l_processors.doDeleteAllQuery(BranchParams.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(123);
            l_processors.doInsertQuery(l_branchParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());
                
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(123456));
            
            //TraderParams
            l_processors.doDeleteAllQuery(TraderParams.TYPE);
            TraderParams l_traderParams =
                TestDBUtility.getTraderRow();
            l_traderParams.setTraderId(123456);
            l_processors.doInsertQuery(l_traderParams);
            
            //ProductParams
            l_processors.doDeleteAllQuery(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123456789);
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductType(ProductTypeEnum.CASH);
            l_processors.doInsertQuery(l_productParams);
            
            //
            WEB3TPTradingPowerResult l_powerResult = new WEB3TPTradingPowerResult();
            l_powerResult.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {
                    WEB3GentradeSubAccount.class,
                    Object[].class,
                    Object[].class,
                    OrderTypeEnum.class,
                    boolean.class},
                l_powerResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "reCalcTradingPower",
                new Class[] {WEB3GentradeSubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "submitTransferOrder",
                new Class[]{
                    SubAccount.class, ProductTypeEnum.class, OrderTypeEnum.class, NewOrderSpec.class,
                    AioOrderManagerPersistenceEventInterceptor.class, long.class, String.class},
                null);
            
            //FxAccountParams
            l_processors.doDeleteAllQuery(FxAccountParams.TYPE);
            FxAccountParams l_fxAccountParams =
                TestDBUtility.getFxAccountRow();
            l_fxAccountParams.setInstitutionCode("0D");
            l_fxAccountParams.setBranchCode("624");
            l_fxAccountParams.setFxSystemCode("07");
            l_fxAccountParams.setFxLoginId(123456789l);
            l_fxAccountParams.setAccountCode(l_mainAccountParams.getAccountCode());
            l_processors.doInsertQuery(l_fxAccountParams);

            //GftTransferStatusParams
            l_processors.doDeleteAllQuery(GftTransferStatusParams.TYPE);
            
            //FxTransferMasterParams
            l_processors.doDeleteAllQuery(FxTransferMasterParams.TYPE);
            FxTransferMasterParams l_fxTransferMasterParams =
                TestDBUtility.getFxTransferMasterParams();
            l_fxTransferMasterParams.setRemarkName("CFD");
            l_fxTransferMasterParams.setRemarkName2("CFD2");
            l_fxTransferMasterParams.setRemarkName3("CFD3");
            l_fxTransferMasterParams.setRemarkName4("CFD4");
            l_fxTransferMasterParams.setRemarkName5("CFD5");
            l_fxTransferMasterParams.setRemarkName6("CFD6");
            l_fxTransferMasterParams.setRemarkName7("CFD7");
            l_fxTransferMasterParams.setRemarkName8("CFD8");
            
            l_fxTransferMasterParams.setFxSystemId(6241001);
            l_fxTransferMasterParams.setTransferDiv("1");
            l_processors.doInsertQuery(l_fxTransferMasterParams);
            
            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams =
                TestDBUtility.getInstitutionPreferencesRow();
            l_institutionPreferencesParams.setName("fx.fxloginid.change.div");
            l_institutionPreferencesParams.setValue("07");
             l_institutionPreferencesParams.setNameSerialNo(1);
             TestDBUtility.insertWithDel(l_institutionPreferencesParams);
            WEB3FXTransToFXAskingResponse r =   l_impl.startOrder(l_request);

            //get変換FXログイン
            //[引数] 
            // 証券会社ID：　@補助口座.getInstitution.getInstitutionId() 
            // FXシステムコード：　@会社別FXシステム条件Params.FXシステムコード 
            // FXログインID頭文字：　@会社別FXシステム条件Params.FXログインID頭文字 
            // FXログインID：　@FX顧客Params.FXログインID
            //補助口座.getInstitution.getInstitutionId()=33l
            //会社別FXシステム条件Params.FXシステムコード ="07"
            //会社別FXシステム条件Params.FXログインID頭文字 ="159"
            //FX顧客Params.FXログインID=123456789l
            
            assertEquals("159123456", r.fxGftAskingTelegramUnit.fxFirstLoginId);


        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testStartOrderCase0003()
    {
        final String STR_METHOD_NAME = "testStartOrderCase0003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FXTransToFXAskingRequest l_request =
                new WEB3FXTransToFXAskingRequest();
            l_request.wolfSession = "session";
            l_request.regetServiceId = "123";
            l_request.wolfAid = "456";
            l_request.wolfSsid = "789";
            
            WEB3FXAccInformationUnit fxAccInformationUnit =
                new WEB3FXAccInformationUnit();
            fxAccInformationUnit.fxCourseDiv =
                WEB3GftTransStatusCourseDivDef.CFD_COURSE_3;
            fxAccInformationUnit.fxAccountCode = "123456";
            l_request.fxAccInformationUnit = fxAccInformationUnit;
            l_request.transferAmount = "100";
            l_request.fxSystemCode = "07";
            
            QueryProcessor l_processors = Processors.getDefaultProcessor();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getAccountId",
                new Class[] {},
                new Long(123456));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl", 
                    "getNewNumber",
                    new Class[] {String.class, String.class, ProductTypeEnum.class },
                    new String("831214"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "validateOrder",
                new Class[] {SubAccount.class},
                null);
            
            //MainAccountParams
            l_processors.doDeleteAllQuery(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123456);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setInstitutionId(33);
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setBranchId(123);
            l_mainAccountParams.setAccountType(MainAccountTypeEnum.CORPORATE_ACCOUNT);
            l_mainAccountParams.setTransferCount(4);
            l_mainAccountParams.setCfdAccOpenDiv("1");
            l_mainAccountParams.setIfoAccOpenDivTokyo("2");
            
            l_processors.doInsertQuery(l_mainAccountParams);

            //SubAccountParams
            l_processors.doDeleteAllQuery(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(123456);
            l_subAccountParams.setInstitutionCode("0D");
            l_subAccountParams.setInstitutionId(33);
            l_subAccountParams.setBranchId(123);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_processors.doInsertQuery(l_subAccountParams);
            
            //CompFxConditionRow
            l_processors.doDeleteAllQuery(CompFxConditionRow.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("624");
            l_compFxConditionParams.setFxSystemCode("07");
            l_compFxConditionParams.setFxSystemDiv("1");//<<<<<<<<<<<<<<<<<<<<
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            l_compFxConditionParams.setMultiCfdAccDiv("1");
            l_compFxConditionParams.setFxSystemId(6241001);
            l_processors.doInsertQuery(l_compFxConditionParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20070215", "yyyyMMdd"));
            
            //InstitutionParams
            l_processors.doDeleteAllQuery(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33);
            l_processors.doInsertQuery(l_institutionParams);
            
            //BranchParams
            l_processors.doDeleteAllQuery(BranchParams.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(123);
            l_processors.doInsertQuery(l_branchParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());
                
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(123456));
            
            //TraderParams
            l_processors.doDeleteAllQuery(TraderParams.TYPE);
            TraderParams l_traderParams =
                TestDBUtility.getTraderRow();
            l_traderParams.setTraderId(123456);
            l_processors.doInsertQuery(l_traderParams);
            
            //ProductParams
            l_processors.doDeleteAllQuery(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123456789);
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductType(ProductTypeEnum.CASH);
            l_processors.doInsertQuery(l_productParams);
            
            //
            WEB3TPTradingPowerResult l_powerResult = new WEB3TPTradingPowerResult();
            l_powerResult.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {
                    WEB3GentradeSubAccount.class,
                    Object[].class,
                    Object[].class,
                    OrderTypeEnum.class,
                    boolean.class},
                l_powerResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "reCalcTradingPower",
                new Class[] {WEB3GentradeSubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "submitTransferOrder",
                new Class[]{
                    SubAccount.class, ProductTypeEnum.class, OrderTypeEnum.class, NewOrderSpec.class,
                    AioOrderManagerPersistenceEventInterceptor.class, long.class, String.class},
                null);
            
            //FxAccountParams
            l_processors.doDeleteAllQuery(FxAccountParams.TYPE);
            FxAccountParams l_fxAccountParams =
                TestDBUtility.getFxAccountRow();
            l_fxAccountParams.setInstitutionCode("0D");
            l_fxAccountParams.setBranchCode("624");
            l_fxAccountParams.setFxSystemCode("07");
            l_fxAccountParams.setFxLoginId(123456789l);
            l_fxAccountParams.setAccountCode(l_mainAccountParams.getAccountCode());
            l_processors.doInsertQuery(l_fxAccountParams);

            //GftTransferStatusParams
            l_processors.doDeleteAllQuery(GftTransferStatusParams.TYPE);
            
            //FxTransferMasterParams
            l_processors.doDeleteAllQuery(FxTransferMasterParams.TYPE);
            FxTransferMasterParams l_fxTransferMasterParams =
                TestDBUtility.getFxTransferMasterParams();
            l_fxTransferMasterParams.setRemarkName("CFD");
            l_fxTransferMasterParams.setRemarkName2("CFD2");
            l_fxTransferMasterParams.setRemarkName3("CFD3");
            l_fxTransferMasterParams.setRemarkName4("CFD4");
            l_fxTransferMasterParams.setRemarkName5("CFD5");
            l_fxTransferMasterParams.setRemarkName6("CFD6");
            l_fxTransferMasterParams.setRemarkName7("CFD7");
            l_fxTransferMasterParams.setRemarkName8("CFD8");
            
            l_fxTransferMasterParams.setFxSystemId(6241001);
            l_fxTransferMasterParams.setTransferDiv("1");
            l_processors.doInsertQuery(l_fxTransferMasterParams);
            
            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams =
                TestDBUtility.getInstitutionPreferencesRow();
            l_institutionPreferencesParams.setName("fx.fxloginid.change.div");
            l_institutionPreferencesParams.setValue("07");
             l_institutionPreferencesParams.setNameSerialNo(1);
             TestDBUtility.insertWithDel(l_institutionPreferencesParams);
            WEB3FXTransToFXAskingResponse r =   l_impl.startOrder(l_request);

            //get変換FXログイン
            //[引数] 
            // 証券会社ID：　@補助口座.getInstitution.getInstitutionId() 
            // FXシステムコード：　@会社別FXシステム条件Params.FXシステムコード 
            // FXログインID頭文字：　@会社別FXシステム条件Params.FXログインID頭文字 
            // FXログインID：　@FX顧客Params.FXログインID
            //補助口座.getInstitution.getInstitutionId()=33l
            //会社別FXシステム条件Params.FXシステムコード ="07"
            //会社別FXシステム条件Params.FXログインID頭文字 ="159"
            //FX顧客Params.FXログインID=123456789l
            
            assertEquals("159123456", r.fxGftAskingTelegramUnit.fxFirstLoginId);


        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testStartOrderCase0004()
    {
        final String STR_METHOD_NAME = "testStartOrderCase0004()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FXTransToFXAskingRequest l_request =
                new WEB3FXTransToFXAskingRequest();
            l_request.wolfSession = "session";
            l_request.regetServiceId = "123";
            l_request.wolfAid = "456";
            l_request.wolfSsid = "789";
            
            WEB3FXAccInformationUnit fxAccInformationUnit =
                new WEB3FXAccInformationUnit();
            fxAccInformationUnit.fxCourseDiv =
                WEB3GftTransStatusCourseDivDef.CFD_COURSE_4;
            fxAccInformationUnit.fxAccountCode = "123456";
            l_request.fxAccInformationUnit = fxAccInformationUnit;
            l_request.transferAmount = "100";
            l_request.fxSystemCode = "07";
            
            QueryProcessor l_processors = Processors.getDefaultProcessor();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getAccountId",
                new Class[] {},
                new Long(123456));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl", 
                    "getNewNumber",
                    new Class[] {String.class, String.class, ProductTypeEnum.class },
                    new String("831214"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "validateOrder",
                new Class[] {SubAccount.class},
                null);
            
            //MainAccountParams
            l_processors.doDeleteAllQuery(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123456);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setInstitutionId(33);
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setBranchId(123);
            l_mainAccountParams.setAccountType(MainAccountTypeEnum.CORPORATE_ACCOUNT);
            l_mainAccountParams.setTransferCount(4);
            l_mainAccountParams.setCfdAccOpenDiv("1");
            l_mainAccountParams.setIfoAccOpenDivTokyo("2");
            
            l_processors.doInsertQuery(l_mainAccountParams);

            //SubAccountParams
            l_processors.doDeleteAllQuery(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(123456);
            l_subAccountParams.setInstitutionCode("0D");
            l_subAccountParams.setInstitutionId(33);
            l_subAccountParams.setBranchId(123);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_processors.doInsertQuery(l_subAccountParams);
            
            //CompFxConditionRow
            l_processors.doDeleteAllQuery(CompFxConditionRow.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("624");
            l_compFxConditionParams.setFxSystemCode("07");
            l_compFxConditionParams.setFxSystemDiv("1");//<<<<<<<<<<<<<<<<<<<<
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            l_compFxConditionParams.setMultiCfdAccDiv("1");
            l_compFxConditionParams.setFxSystemId(6241001);
            l_processors.doInsertQuery(l_compFxConditionParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20070215", "yyyyMMdd"));
            
            //InstitutionParams
            l_processors.doDeleteAllQuery(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33);
            l_processors.doInsertQuery(l_institutionParams);
            
            //BranchParams
            l_processors.doDeleteAllQuery(BranchParams.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(123);
            l_processors.doInsertQuery(l_branchParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());
                
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(123456));
            
            //TraderParams
            l_processors.doDeleteAllQuery(TraderParams.TYPE);
            TraderParams l_traderParams =
                TestDBUtility.getTraderRow();
            l_traderParams.setTraderId(123456);
            l_processors.doInsertQuery(l_traderParams);
            
            //ProductParams
            l_processors.doDeleteAllQuery(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123456789);
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductType(ProductTypeEnum.CASH);
            l_processors.doInsertQuery(l_productParams);
            
            //
            WEB3TPTradingPowerResult l_powerResult = new WEB3TPTradingPowerResult();
            l_powerResult.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {
                    WEB3GentradeSubAccount.class,
                    Object[].class,
                    Object[].class,
                    OrderTypeEnum.class,
                    boolean.class},
                l_powerResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "reCalcTradingPower",
                new Class[] {WEB3GentradeSubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "submitTransferOrder",
                new Class[]{
                    SubAccount.class, ProductTypeEnum.class, OrderTypeEnum.class, NewOrderSpec.class,
                    AioOrderManagerPersistenceEventInterceptor.class, long.class, String.class},
                null);
            
            //FxAccountParams
            l_processors.doDeleteAllQuery(FxAccountParams.TYPE);
            FxAccountParams l_fxAccountParams =
                TestDBUtility.getFxAccountRow();
            l_fxAccountParams.setInstitutionCode("0D");
            l_fxAccountParams.setBranchCode("624");
            l_fxAccountParams.setFxSystemCode("07");
            l_fxAccountParams.setFxLoginId(123456789l);
            l_fxAccountParams.setAccountCode(l_mainAccountParams.getAccountCode());
            l_processors.doInsertQuery(l_fxAccountParams);

            //GftTransferStatusParams
            l_processors.doDeleteAllQuery(GftTransferStatusParams.TYPE);
            
            //FxTransferMasterParams
            l_processors.doDeleteAllQuery(FxTransferMasterParams.TYPE);
            FxTransferMasterParams l_fxTransferMasterParams =
                TestDBUtility.getFxTransferMasterParams();
            l_fxTransferMasterParams.setRemarkName("CFD");
            l_fxTransferMasterParams.setRemarkName2("CFD2");
            l_fxTransferMasterParams.setRemarkName3("CFD3");
            l_fxTransferMasterParams.setRemarkName4("CFD4");
            l_fxTransferMasterParams.setRemarkName5("CFD5");
            l_fxTransferMasterParams.setRemarkName6("CFD6");
            l_fxTransferMasterParams.setRemarkName7("CFD7");
            l_fxTransferMasterParams.setRemarkName8("CFD8");
            
            l_fxTransferMasterParams.setFxSystemId(6241001);
            l_fxTransferMasterParams.setTransferDiv("1");
            l_processors.doInsertQuery(l_fxTransferMasterParams);
            
            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams =
                TestDBUtility.getInstitutionPreferencesRow();
            l_institutionPreferencesParams.setName("fx.fxloginid.change.div");
            l_institutionPreferencesParams.setValue("07");
             l_institutionPreferencesParams.setNameSerialNo(1);
             TestDBUtility.insertWithDel(l_institutionPreferencesParams);
            WEB3FXTransToFXAskingResponse r =   l_impl.startOrder(l_request);

            //get変換FXログイン
            //[引数] 
            // 証券会社ID：　@補助口座.getInstitution.getInstitutionId() 
            // FXシステムコード：　@会社別FXシステム条件Params.FXシステムコード 
            // FXログインID頭文字：　@会社別FXシステム条件Params.FXログインID頭文字 
            // FXログインID：　@FX顧客Params.FXログインID
            //補助口座.getInstitution.getInstitutionId()=33l
            //会社別FXシステム条件Params.FXシステムコード ="07"
            //会社別FXシステム条件Params.FXログインID頭文字 ="159"
            //FX顧客Params.FXログインID=123456789l
            
            assertEquals("159123456", r.fxGftAskingTelegramUnit.fxFirstLoginId);


        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testStartOrderCase0005()
    {
        final String STR_METHOD_NAME = "testStartOrderCase0005()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FXTransToFXAskingRequest l_request =
                new WEB3FXTransToFXAskingRequest();
            l_request.wolfSession = "session";
            l_request.regetServiceId = "123";
            l_request.wolfAid = "456";
            l_request.wolfSsid = "789";
            
            WEB3FXAccInformationUnit fxAccInformationUnit =
                new WEB3FXAccInformationUnit();
            fxAccInformationUnit.fxCourseDiv =
                WEB3GftTransStatusCourseDivDef.CFD_COURSE_5;
            fxAccInformationUnit.fxAccountCode = "123456";
            l_request.fxAccInformationUnit = fxAccInformationUnit;
            l_request.transferAmount = "100";
            l_request.fxSystemCode = "07";
            
            QueryProcessor l_processors = Processors.getDefaultProcessor();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getAccountId",
                new Class[] {},
                new Long(123456));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl", 
                    "getNewNumber",
                    new Class[] {String.class, String.class, ProductTypeEnum.class },
                    new String("831214"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "validateOrder",
                new Class[] {SubAccount.class},
                null);
            
            //MainAccountParams
            l_processors.doDeleteAllQuery(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123456);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setInstitutionId(33);
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setBranchId(123);
            l_mainAccountParams.setAccountType(MainAccountTypeEnum.CORPORATE_ACCOUNT);
            l_mainAccountParams.setTransferCount(4);
            l_mainAccountParams.setCfdAccOpenDiv("1");
            l_mainAccountParams.setIfoAccOpenDivTokyo("2");
            
            l_processors.doInsertQuery(l_mainAccountParams);

            //SubAccountParams
            l_processors.doDeleteAllQuery(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(123456);
            l_subAccountParams.setInstitutionCode("0D");
            l_subAccountParams.setInstitutionId(33);
            l_subAccountParams.setBranchId(123);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_processors.doInsertQuery(l_subAccountParams);
            
            //CompFxConditionRow
            l_processors.doDeleteAllQuery(CompFxConditionRow.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("624");
            l_compFxConditionParams.setFxSystemCode("07");
            l_compFxConditionParams.setFxSystemDiv("1");//<<<<<<<<<<<<<<<<<<<<
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            l_compFxConditionParams.setMultiCfdAccDiv("1");
            l_compFxConditionParams.setFxSystemId(6241001);
            l_processors.doInsertQuery(l_compFxConditionParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20070215", "yyyyMMdd"));
            
            //InstitutionParams
            l_processors.doDeleteAllQuery(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33);
            l_processors.doInsertQuery(l_institutionParams);
            
            //BranchParams
            l_processors.doDeleteAllQuery(BranchParams.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(123);
            l_processors.doInsertQuery(l_branchParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());
                
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(123456));
            
            //TraderParams
            l_processors.doDeleteAllQuery(TraderParams.TYPE);
            TraderParams l_traderParams =
                TestDBUtility.getTraderRow();
            l_traderParams.setTraderId(123456);
            l_processors.doInsertQuery(l_traderParams);
            
            //ProductParams
            l_processors.doDeleteAllQuery(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123456789);
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductType(ProductTypeEnum.CASH);
            l_processors.doInsertQuery(l_productParams);
            
            //
            WEB3TPTradingPowerResult l_powerResult = new WEB3TPTradingPowerResult();
            l_powerResult.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {
                    WEB3GentradeSubAccount.class,
                    Object[].class,
                    Object[].class,
                    OrderTypeEnum.class,
                    boolean.class},
                l_powerResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "reCalcTradingPower",
                new Class[] {WEB3GentradeSubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "submitTransferOrder",
                new Class[]{
                    SubAccount.class, ProductTypeEnum.class, OrderTypeEnum.class, NewOrderSpec.class,
                    AioOrderManagerPersistenceEventInterceptor.class, long.class, String.class},
                null);
            
            //FxAccountParams
            l_processors.doDeleteAllQuery(FxAccountParams.TYPE);
            FxAccountParams l_fxAccountParams =
                TestDBUtility.getFxAccountRow();
            l_fxAccountParams.setInstitutionCode("0D");
            l_fxAccountParams.setBranchCode("624");
            l_fxAccountParams.setFxSystemCode("07");
            l_fxAccountParams.setFxLoginId(123456789l);
            l_fxAccountParams.setAccountCode(l_mainAccountParams.getAccountCode());
            l_processors.doInsertQuery(l_fxAccountParams);

            //GftTransferStatusParams
            l_processors.doDeleteAllQuery(GftTransferStatusParams.TYPE);
            
            //FxTransferMasterParams
            l_processors.doDeleteAllQuery(FxTransferMasterParams.TYPE);
            FxTransferMasterParams l_fxTransferMasterParams =
                TestDBUtility.getFxTransferMasterParams();
            l_fxTransferMasterParams.setRemarkName("CFD");
            l_fxTransferMasterParams.setRemarkName2("CFD2");
            l_fxTransferMasterParams.setRemarkName3("CFD3");
            l_fxTransferMasterParams.setRemarkName4("CFD4");
            l_fxTransferMasterParams.setRemarkName5("CFD5");
            l_fxTransferMasterParams.setRemarkName6("CFD6");
            l_fxTransferMasterParams.setRemarkName7("CFD7");
            l_fxTransferMasterParams.setRemarkName8("CFD8");
            
            l_fxTransferMasterParams.setFxSystemId(6241001);
            l_fxTransferMasterParams.setTransferDiv("1");
            l_processors.doInsertQuery(l_fxTransferMasterParams);
            
            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams =
                TestDBUtility.getInstitutionPreferencesRow();
            l_institutionPreferencesParams.setName("fx.fxloginid.change.div");
            l_institutionPreferencesParams.setValue("07");
             l_institutionPreferencesParams.setNameSerialNo(1);
             TestDBUtility.insertWithDel(l_institutionPreferencesParams);
            WEB3FXTransToFXAskingResponse r =   l_impl.startOrder(l_request);

            //get変換FXログイン
            //[引数] 
            // 証券会社ID：　@補助口座.getInstitution.getInstitutionId() 
            // FXシステムコード：　@会社別FXシステム条件Params.FXシステムコード 
            // FXログインID頭文字：　@会社別FXシステム条件Params.FXログインID頭文字 
            // FXログインID：　@FX顧客Params.FXログインID
            //補助口座.getInstitution.getInstitutionId()=33l
            //会社別FXシステム条件Params.FXシステムコード ="07"
            //会社別FXシステム条件Params.FXログインID頭文字 ="159"
            //FX顧客Params.FXログインID=123456789l
            
            assertEquals("159123456", r.fxGftAskingTelegramUnit.fxFirstLoginId);


        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testStartOrderCase0006()
    {
        final String STR_METHOD_NAME = "testStartOrderCase0006()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FXTransToFXAskingRequest l_request =
                new WEB3FXTransToFXAskingRequest();
            l_request.wolfSession = "session";
            l_request.regetServiceId = "123";
            l_request.wolfAid = "456";
            l_request.wolfSsid = "789";
            
            WEB3FXAccInformationUnit fxAccInformationUnit =
                new WEB3FXAccInformationUnit();
            fxAccInformationUnit.fxCourseDiv =
                WEB3GftTransStatusCourseDivDef.CFD_COURSE_6;
            fxAccInformationUnit.fxAccountCode = "123456";
            l_request.fxAccInformationUnit = fxAccInformationUnit;
            l_request.transferAmount = "100";
            l_request.fxSystemCode = "07";
            
            QueryProcessor l_processors = Processors.getDefaultProcessor();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getAccountId",
                new Class[] {},
                new Long(123456));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl", 
                    "getNewNumber",
                    new Class[] {String.class, String.class, ProductTypeEnum.class },
                    new String("831214"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "validateOrder",
                new Class[] {SubAccount.class},
                null);
            
            //MainAccountParams
            l_processors.doDeleteAllQuery(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123456);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setInstitutionId(33);
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setBranchId(123);
            l_mainAccountParams.setAccountType(MainAccountTypeEnum.CORPORATE_ACCOUNT);
            l_mainAccountParams.setTransferCount(4);
            l_mainAccountParams.setCfdAccOpenDiv("1");
            l_mainAccountParams.setIfoAccOpenDivTokyo("2");
            
            l_processors.doInsertQuery(l_mainAccountParams);

            //SubAccountParams
            l_processors.doDeleteAllQuery(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(123456);
            l_subAccountParams.setInstitutionCode("0D");
            l_subAccountParams.setInstitutionId(33);
            l_subAccountParams.setBranchId(123);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_processors.doInsertQuery(l_subAccountParams);
            
            //CompFxConditionRow
            l_processors.doDeleteAllQuery(CompFxConditionRow.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("624");
            l_compFxConditionParams.setFxSystemCode("07");
            l_compFxConditionParams.setFxSystemDiv("1");//<<<<<<<<<<<<<<<<<<<<
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            l_compFxConditionParams.setMultiCfdAccDiv("1");
            l_compFxConditionParams.setFxSystemId(6241001);
            l_processors.doInsertQuery(l_compFxConditionParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20070215", "yyyyMMdd"));
            
            //InstitutionParams
            l_processors.doDeleteAllQuery(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33);
            l_processors.doInsertQuery(l_institutionParams);
            
            //BranchParams
            l_processors.doDeleteAllQuery(BranchParams.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(123);
            l_processors.doInsertQuery(l_branchParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());
                
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(123456));
            
            //TraderParams
            l_processors.doDeleteAllQuery(TraderParams.TYPE);
            TraderParams l_traderParams =
                TestDBUtility.getTraderRow();
            l_traderParams.setTraderId(123456);
            l_processors.doInsertQuery(l_traderParams);
            
            //ProductParams
            l_processors.doDeleteAllQuery(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123456789);
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductType(ProductTypeEnum.CASH);
            l_processors.doInsertQuery(l_productParams);
            
            //
            WEB3TPTradingPowerResult l_powerResult = new WEB3TPTradingPowerResult();
            l_powerResult.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {
                    WEB3GentradeSubAccount.class,
                    Object[].class,
                    Object[].class,
                    OrderTypeEnum.class,
                    boolean.class},
                l_powerResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "reCalcTradingPower",
                new Class[] {WEB3GentradeSubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "submitTransferOrder",
                new Class[]{
                    SubAccount.class, ProductTypeEnum.class, OrderTypeEnum.class, NewOrderSpec.class,
                    AioOrderManagerPersistenceEventInterceptor.class, long.class, String.class},
                null);
            
            //FxAccountParams
            l_processors.doDeleteAllQuery(FxAccountParams.TYPE);
            FxAccountParams l_fxAccountParams =
                TestDBUtility.getFxAccountRow();
            l_fxAccountParams.setInstitutionCode("0D");
            l_fxAccountParams.setBranchCode("624");
            l_fxAccountParams.setFxSystemCode("07");
            l_fxAccountParams.setFxLoginId(123456789l);
            l_fxAccountParams.setAccountCode(l_mainAccountParams.getAccountCode());
            l_processors.doInsertQuery(l_fxAccountParams);

            //GftTransferStatusParams
            l_processors.doDeleteAllQuery(GftTransferStatusParams.TYPE);
            
            //FxTransferMasterParams
            l_processors.doDeleteAllQuery(FxTransferMasterParams.TYPE);
            FxTransferMasterParams l_fxTransferMasterParams =
                TestDBUtility.getFxTransferMasterParams();
            l_fxTransferMasterParams.setRemarkName("CFD");
            l_fxTransferMasterParams.setRemarkName2("CFD2");
            l_fxTransferMasterParams.setRemarkName3("CFD3");
            l_fxTransferMasterParams.setRemarkName4("CFD4");
            l_fxTransferMasterParams.setRemarkName5("CFD5");
            l_fxTransferMasterParams.setRemarkName6("CFD6");
            l_fxTransferMasterParams.setRemarkName7("CFD7");
            l_fxTransferMasterParams.setRemarkName8("CFD8");
            
            l_fxTransferMasterParams.setFxSystemId(6241001);
            l_fxTransferMasterParams.setTransferDiv("1");
            l_processors.doInsertQuery(l_fxTransferMasterParams);
            
            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams =
                TestDBUtility.getInstitutionPreferencesRow();
            l_institutionPreferencesParams.setName("fx.fxloginid.change.div");
            l_institutionPreferencesParams.setValue("07");
             l_institutionPreferencesParams.setNameSerialNo(1);
             TestDBUtility.insertWithDel(l_institutionPreferencesParams);
            WEB3FXTransToFXAskingResponse r =   l_impl.startOrder(l_request);

            //get変換FXログイン
            //[引数] 
            // 証券会社ID：　@補助口座.getInstitution.getInstitutionId() 
            // FXシステムコード：　@会社別FXシステム条件Params.FXシステムコード 
            // FXログインID頭文字：　@会社別FXシステム条件Params.FXログインID頭文字 
            // FXログインID：　@FX顧客Params.FXログインID
            //補助口座.getInstitution.getInstitutionId()=33l
            //会社別FXシステム条件Params.FXシステムコード ="07"
            //会社別FXシステム条件Params.FXログインID頭文字 ="159"
            //FX顧客Params.FXログインID=123456789l
            
            assertEquals("159123456", r.fxGftAskingTelegramUnit.fxFirstLoginId);


        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testStartOrderCase0007()
    {
        final String STR_METHOD_NAME = "testStartOrderCase0007()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FXTransToFXAskingRequest l_request =
                new WEB3FXTransToFXAskingRequest();
            l_request.wolfSession = "session";
            l_request.regetServiceId = "123";
            l_request.wolfAid = "456";
            l_request.wolfSsid = "789";
            
            WEB3FXAccInformationUnit fxAccInformationUnit =
                new WEB3FXAccInformationUnit();
            fxAccInformationUnit.fxCourseDiv =
                WEB3GftTransStatusCourseDivDef.CFD_COURSE_7;
            fxAccInformationUnit.fxAccountCode = "123456";
            l_request.fxAccInformationUnit = fxAccInformationUnit;
            l_request.transferAmount = "100";
            l_request.fxSystemCode = "07";
            
            QueryProcessor l_processors = Processors.getDefaultProcessor();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getAccountId",
                new Class[] {},
                new Long(123456));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl", 
                    "getNewNumber",
                    new Class[] {String.class, String.class, ProductTypeEnum.class },
                    new String("831214"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "validateOrder",
                new Class[] {SubAccount.class},
                null);
            
            //MainAccountParams
            l_processors.doDeleteAllQuery(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123456);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setInstitutionId(33);
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setBranchId(123);
            l_mainAccountParams.setAccountType(MainAccountTypeEnum.CORPORATE_ACCOUNT);
            l_mainAccountParams.setTransferCount(4);
            l_mainAccountParams.setCfdAccOpenDiv("1");
            l_mainAccountParams.setIfoAccOpenDivTokyo("2");
            
            l_processors.doInsertQuery(l_mainAccountParams);

            //SubAccountParams
            l_processors.doDeleteAllQuery(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(123456);
            l_subAccountParams.setInstitutionCode("0D");
            l_subAccountParams.setInstitutionId(33);
            l_subAccountParams.setBranchId(123);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_processors.doInsertQuery(l_subAccountParams);
            
            //CompFxConditionRow
            l_processors.doDeleteAllQuery(CompFxConditionRow.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("624");
            l_compFxConditionParams.setFxSystemCode("07");
            l_compFxConditionParams.setFxSystemDiv("1");//<<<<<<<<<<<<<<<<<<<<
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            l_compFxConditionParams.setMultiCfdAccDiv("1");
            l_compFxConditionParams.setFxSystemId(6241001);
            l_processors.doInsertQuery(l_compFxConditionParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20070215", "yyyyMMdd"));
            
            //InstitutionParams
            l_processors.doDeleteAllQuery(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33);
            l_processors.doInsertQuery(l_institutionParams);
            
            //BranchParams
            l_processors.doDeleteAllQuery(BranchParams.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(123);
            l_processors.doInsertQuery(l_branchParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());
                
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(123456));
            
            //TraderParams
            l_processors.doDeleteAllQuery(TraderParams.TYPE);
            TraderParams l_traderParams =
                TestDBUtility.getTraderRow();
            l_traderParams.setTraderId(123456);
            l_processors.doInsertQuery(l_traderParams);
            
            //ProductParams
            l_processors.doDeleteAllQuery(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123456789);
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductType(ProductTypeEnum.CASH);
            l_processors.doInsertQuery(l_productParams);
            
            //
            WEB3TPTradingPowerResult l_powerResult = new WEB3TPTradingPowerResult();
            l_powerResult.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {
                    WEB3GentradeSubAccount.class,
                    Object[].class,
                    Object[].class,
                    OrderTypeEnum.class,
                    boolean.class},
                l_powerResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "reCalcTradingPower",
                new Class[] {WEB3GentradeSubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "submitTransferOrder",
                new Class[]{
                    SubAccount.class, ProductTypeEnum.class, OrderTypeEnum.class, NewOrderSpec.class,
                    AioOrderManagerPersistenceEventInterceptor.class, long.class, String.class},
                null);
            
            //FxAccountParams
            l_processors.doDeleteAllQuery(FxAccountParams.TYPE);
            FxAccountParams l_fxAccountParams =
                TestDBUtility.getFxAccountRow();
            l_fxAccountParams.setInstitutionCode("0D");
            l_fxAccountParams.setBranchCode("624");
            l_fxAccountParams.setFxSystemCode("07");
            l_fxAccountParams.setFxLoginId(123456789l);
            l_fxAccountParams.setAccountCode(l_mainAccountParams.getAccountCode());
            l_processors.doInsertQuery(l_fxAccountParams);

            //GftTransferStatusParams
            l_processors.doDeleteAllQuery(GftTransferStatusParams.TYPE);
            
            //FxTransferMasterParams
            l_processors.doDeleteAllQuery(FxTransferMasterParams.TYPE);
            FxTransferMasterParams l_fxTransferMasterParams =
                TestDBUtility.getFxTransferMasterParams();
            l_fxTransferMasterParams.setRemarkName("CFD");
            l_fxTransferMasterParams.setRemarkName2("CFD2");
            l_fxTransferMasterParams.setRemarkName3("CFD3");
            l_fxTransferMasterParams.setRemarkName4("CFD4");
            l_fxTransferMasterParams.setRemarkName5("CFD5");
            l_fxTransferMasterParams.setRemarkName6("CFD6");
            l_fxTransferMasterParams.setRemarkName7("CFD7");
            l_fxTransferMasterParams.setRemarkName8("CFD8");
            
            l_fxTransferMasterParams.setFxSystemId(6241001);
            l_fxTransferMasterParams.setTransferDiv("1");
            l_processors.doInsertQuery(l_fxTransferMasterParams);
            
            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams =
                TestDBUtility.getInstitutionPreferencesRow();
            l_institutionPreferencesParams.setName("fx.fxloginid.change.div");
            l_institutionPreferencesParams.setValue("07");
             l_institutionPreferencesParams.setNameSerialNo(1);
             TestDBUtility.insertWithDel(l_institutionPreferencesParams);
            WEB3FXTransToFXAskingResponse r =   l_impl.startOrder(l_request);

            //get変換FXログイン
            //[引数] 
            // 証券会社ID：　@補助口座.getInstitution.getInstitutionId() 
            // FXシステムコード：　@会社別FXシステム条件Params.FXシステムコード 
            // FXログインID頭文字：　@会社別FXシステム条件Params.FXログインID頭文字 
            // FXログインID：　@FX顧客Params.FXログインID
            //補助口座.getInstitution.getInstitutionId()=33l
            //会社別FXシステム条件Params.FXシステムコード ="07"
            //会社別FXシステム条件Params.FXログインID頭文字 ="159"
            //FX顧客Params.FXログインID=123456789l
            
            assertEquals("159123456", r.fxGftAskingTelegramUnit.fxFirstLoginId);


        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testStartOrderCase0008()
    {
        final String STR_METHOD_NAME = "testStartOrderCase0008()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FXTransToFXAskingRequest l_request =
                new WEB3FXTransToFXAskingRequest();
            l_request.wolfSession = "session";
            l_request.regetServiceId = "123";
            l_request.wolfAid = "456";
            l_request.wolfSsid = "789";
            
            WEB3FXAccInformationUnit fxAccInformationUnit =
                new WEB3FXAccInformationUnit();
            fxAccInformationUnit.fxCourseDiv =
                WEB3GftTransStatusCourseDivDef.CFD_COURSE_8;
            fxAccInformationUnit.fxAccountCode = "123456";
            l_request.fxAccInformationUnit = fxAccInformationUnit;
            l_request.transferAmount = "100";
            l_request.fxSystemCode = "07";
            
            QueryProcessor l_processors = Processors.getDefaultProcessor();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getAccountId",
                new Class[] {},
                new Long(123456));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl", 
                    "getNewNumber",
                    new Class[] {String.class, String.class, ProductTypeEnum.class },
                    new String("831214"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "validateOrder",
                new Class[] {SubAccount.class},
                null);
            
            //MainAccountParams
            l_processors.doDeleteAllQuery(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123456);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setInstitutionId(33);
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setBranchId(123);
            l_mainAccountParams.setAccountType(MainAccountTypeEnum.CORPORATE_ACCOUNT);
            l_mainAccountParams.setTransferCount(4);
            l_mainAccountParams.setCfdAccOpenDiv("1");
            l_mainAccountParams.setIfoAccOpenDivTokyo("2");
            
            l_processors.doInsertQuery(l_mainAccountParams);

            //SubAccountParams
            l_processors.doDeleteAllQuery(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(123456);
            l_subAccountParams.setInstitutionCode("0D");
            l_subAccountParams.setInstitutionId(33);
            l_subAccountParams.setBranchId(123);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_processors.doInsertQuery(l_subAccountParams);
            
            //CompFxConditionRow
            l_processors.doDeleteAllQuery(CompFxConditionRow.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("624");
            l_compFxConditionParams.setFxSystemCode("07");
            l_compFxConditionParams.setFxSystemDiv("1");//<<<<<<<<<<<<<<<<<<<<
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            l_compFxConditionParams.setMultiCfdAccDiv("1");
            l_compFxConditionParams.setFxSystemId(6241001);
            l_processors.doInsertQuery(l_compFxConditionParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20070215", "yyyyMMdd"));
            
            //InstitutionParams
            l_processors.doDeleteAllQuery(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33);
            l_processors.doInsertQuery(l_institutionParams);
            
            //BranchParams
            l_processors.doDeleteAllQuery(BranchParams.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(123);
            l_processors.doInsertQuery(l_branchParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());
                
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(123456));
            
            //TraderParams
            l_processors.doDeleteAllQuery(TraderParams.TYPE);
            TraderParams l_traderParams =
                TestDBUtility.getTraderRow();
            l_traderParams.setTraderId(123456);
            l_processors.doInsertQuery(l_traderParams);
            
            //ProductParams
            l_processors.doDeleteAllQuery(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123456789);
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductType(ProductTypeEnum.CASH);
            l_processors.doInsertQuery(l_productParams);
            
            //
            WEB3TPTradingPowerResult l_powerResult = new WEB3TPTradingPowerResult();
            l_powerResult.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {
                    WEB3GentradeSubAccount.class,
                    Object[].class,
                    Object[].class,
                    OrderTypeEnum.class,
                    boolean.class},
                l_powerResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "reCalcTradingPower",
                new Class[] {WEB3GentradeSubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "submitTransferOrder",
                new Class[]{
                    SubAccount.class, ProductTypeEnum.class, OrderTypeEnum.class, NewOrderSpec.class,
                    AioOrderManagerPersistenceEventInterceptor.class, long.class, String.class},
                null);
            
            //FxAccountParams
            l_processors.doDeleteAllQuery(FxAccountParams.TYPE);
            FxAccountParams l_fxAccountParams =
                TestDBUtility.getFxAccountRow();
            l_fxAccountParams.setInstitutionCode("0D");
            l_fxAccountParams.setBranchCode("624");
            l_fxAccountParams.setFxSystemCode("07");
            l_fxAccountParams.setFxLoginId(123456789l);
            l_fxAccountParams.setAccountCode(l_mainAccountParams.getAccountCode());
            l_processors.doInsertQuery(l_fxAccountParams);

            //GftTransferStatusParams
            l_processors.doDeleteAllQuery(GftTransferStatusParams.TYPE);
            
            //FxTransferMasterParams
            l_processors.doDeleteAllQuery(FxTransferMasterParams.TYPE);
            FxTransferMasterParams l_fxTransferMasterParams =
                TestDBUtility.getFxTransferMasterParams();
            l_fxTransferMasterParams.setRemarkName("CFD");
            l_fxTransferMasterParams.setRemarkName2("CFD2");
            l_fxTransferMasterParams.setRemarkName3("CFD3");
            l_fxTransferMasterParams.setRemarkName4("CFD4");
            l_fxTransferMasterParams.setRemarkName5("CFD5");
            l_fxTransferMasterParams.setRemarkName6("CFD6");
            l_fxTransferMasterParams.setRemarkName7("CFD7");
            l_fxTransferMasterParams.setRemarkName8("CFD8");
            
            l_fxTransferMasterParams.setFxSystemId(6241001);
            l_fxTransferMasterParams.setTransferDiv("1");
            l_processors.doInsertQuery(l_fxTransferMasterParams);
            
            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams =
                TestDBUtility.getInstitutionPreferencesRow();
            l_institutionPreferencesParams.setName("fx.fxloginid.change.div");
            l_institutionPreferencesParams.setValue("07");
             l_institutionPreferencesParams.setNameSerialNo(1);
             TestDBUtility.insertWithDel(l_institutionPreferencesParams);
            WEB3FXTransToFXAskingResponse r =   l_impl.startOrder(l_request);

            //get変換FXログイン
            //[引数] 
            // 証券会社ID：　@補助口座.getInstitution.getInstitutionId() 
            // FXシステムコード：　@会社別FXシステム条件Params.FXシステムコード 
            // FXログインID頭文字：　@会社別FXシステム条件Params.FXログインID頭文字 
            // FXログインID：　@FX顧客Params.FXログインID
            //補助口座.getInstitution.getInstitutionId()=33l
            //会社別FXシステム条件Params.FXシステムコード ="07"
            //会社別FXシステム条件Params.FXログインID頭文字 ="159"
            //FX顧客Params.FXログインID=123456789l
            
            assertEquals("159123456", r.fxGftAskingTelegramUnit.fxFirstLoginId);


        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testStartOrderCase0009()
    {
        final String STR_METHOD_NAME = "testStartOrderCase0009()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FXTransToFXAskingRequest l_request =
                new WEB3FXTransToFXAskingRequest();
            l_request.wolfSession = "session";
            l_request.regetServiceId = "123";
            l_request.wolfAid = "456";
            l_request.wolfSsid = "789";
            
            WEB3FXAccInformationUnit fxAccInformationUnit =
                new WEB3FXAccInformationUnit();
            fxAccInformationUnit.fxCourseDiv =
                WEB3GftTransStatusCourseDivDef.CFD_COURSE_8;
            fxAccInformationUnit.fxAccountCode = "123456";
            l_request.fxAccInformationUnit = fxAccInformationUnit;
            l_request.transferAmount = "100";
            l_request.fxSystemCode = "07";
            
            QueryProcessor l_processors = Processors.getDefaultProcessor();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getAccountId",
                new Class[] {},
                new Long(123456));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl", 
                    "getNewNumber",
                    new Class[] {String.class, String.class, ProductTypeEnum.class },
                    new String("831214"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "validateOrder",
                new Class[] {SubAccount.class},
                null);
            
            //MainAccountParams
            l_processors.doDeleteAllQuery(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123456);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setInstitutionId(33);
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setBranchId(123);
            l_mainAccountParams.setAccountType(MainAccountTypeEnum.CORPORATE_ACCOUNT);
            l_mainAccountParams.setTransferCount(4);
            l_mainAccountParams.setCfdAccOpenDiv("1");
            l_mainAccountParams.setIfoAccOpenDivTokyo("2");
            
            l_processors.doInsertQuery(l_mainAccountParams);

            //SubAccountParams
            l_processors.doDeleteAllQuery(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(123456);
            l_subAccountParams.setInstitutionCode("0D");
            l_subAccountParams.setInstitutionId(33);
            l_subAccountParams.setBranchId(123);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_processors.doInsertQuery(l_subAccountParams);
            
            //CompFxConditionRow
            l_processors.doDeleteAllQuery(CompFxConditionRow.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("624");
            l_compFxConditionParams.setFxSystemCode("07");
            l_compFxConditionParams.setFxSystemDiv("1");//<<<<<<<<<<<<<<<<<<<<
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            l_compFxConditionParams.setMultiCfdAccDiv("0");
            l_compFxConditionParams.setFxSystemId(6241001);
            l_processors.doInsertQuery(l_compFxConditionParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20070215", "yyyyMMdd"));
            
            //InstitutionParams
            l_processors.doDeleteAllQuery(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33);
            l_processors.doInsertQuery(l_institutionParams);
            
            //BranchParams
            l_processors.doDeleteAllQuery(BranchParams.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(123);
            l_processors.doInsertQuery(l_branchParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());
                
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(123456));
            
            //TraderParams
            l_processors.doDeleteAllQuery(TraderParams.TYPE);
            TraderParams l_traderParams =
                TestDBUtility.getTraderRow();
            l_traderParams.setTraderId(123456);
            l_processors.doInsertQuery(l_traderParams);
            
            //ProductParams
            l_processors.doDeleteAllQuery(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123456789);
            l_productParams.setInstitutionCode("0D");
            l_productParams.setProductType(ProductTypeEnum.CASH);
            l_processors.doInsertQuery(l_productParams);
            
            //
            WEB3TPTradingPowerResult l_powerResult = new WEB3TPTradingPowerResult();
            l_powerResult.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "validateTradingPower",
                new Class[] {
                    WEB3GentradeSubAccount.class,
                    Object[].class,
                    Object[].class,
                    OrderTypeEnum.class,
                    boolean.class},
                l_powerResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                "reCalcTradingPower",
                new Class[] {WEB3GentradeSubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "submitTransferOrder",
                new Class[]{
                    SubAccount.class, ProductTypeEnum.class, OrderTypeEnum.class, NewOrderSpec.class,
                    AioOrderManagerPersistenceEventInterceptor.class, long.class, String.class},
                null);
            
            //FxAccountParams
            l_processors.doDeleteAllQuery(FxAccountParams.TYPE);
            FxAccountParams l_fxAccountParams =
                TestDBUtility.getFxAccountRow();
            l_fxAccountParams.setInstitutionCode("0D");
            l_fxAccountParams.setBranchCode("624");
            l_fxAccountParams.setFxSystemCode("07");
            l_fxAccountParams.setFxLoginId(123456789l);
            l_fxAccountParams.setAccountCode(l_mainAccountParams.getAccountCode());
            l_processors.doInsertQuery(l_fxAccountParams);

            //GftTransferStatusParams
            l_processors.doDeleteAllQuery(GftTransferStatusParams.TYPE);
            
            //FxTransferMasterParams
            l_processors.doDeleteAllQuery(FxTransferMasterParams.TYPE);
            FxTransferMasterParams l_fxTransferMasterParams =
                TestDBUtility.getFxTransferMasterParams();
            l_fxTransferMasterParams.setRemarkName("CFD");
            l_fxTransferMasterParams.setRemarkName2("CFD2");
            l_fxTransferMasterParams.setRemarkName3("CFD3");
            l_fxTransferMasterParams.setRemarkName4("CFD4");
            l_fxTransferMasterParams.setRemarkName5("CFD5");
            l_fxTransferMasterParams.setRemarkName6("CFD6");
            l_fxTransferMasterParams.setRemarkName7("CFD7");
            l_fxTransferMasterParams.setRemarkName8("CFD8");
            
            l_fxTransferMasterParams.setFxSystemId(6241001);
            l_fxTransferMasterParams.setTransferDiv("1");
            l_processors.doInsertQuery(l_fxTransferMasterParams);
            
            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams =
                TestDBUtility.getInstitutionPreferencesRow();
            l_institutionPreferencesParams.setName("fx.fxloginid.change.div");
            l_institutionPreferencesParams.setValue("07");
             l_institutionPreferencesParams.setNameSerialNo(1);
             TestDBUtility.insertWithDel(l_institutionPreferencesParams);
            WEB3FXTransToFXAskingResponse r =   l_impl.startOrder(l_request);

            //get変換FXログイン
            //[引数] 
            // 証券会社ID：　@補助口座.getInstitution.getInstitutionId() 
            // FXシステムコード：　@会社別FXシステム条件Params.FXシステムコード 
            // FXログインID頭文字：　@会社別FXシステム条件Params.FXログインID頭文字 
            // FXログインID：　@FX顧客Params.FXログインID
            //補助口座.getInstitution.getInstitutionId()=33l
            //会社別FXシステム条件Params.FXシステムコード ="07"
            //会社別FXシステム条件Params.FXログインID頭文字 ="159"
            //FX顧客Params.FXログインID=123456789l
            
            assertEquals("159123456", r.fxGftAskingTelegramUnit.fxFirstLoginId);


        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitOrderCase0013()
    {
        final String STR_METHOD_NAME = "testSubmitOrderCase00013()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            WEB3FXTransToFXCompleteRequest l_request = new WEB3FXTransToFXCompleteRequest();
            l_request.orderId = "123";
            l_request.checkDate = WEB3DateUtility.getDate("20090317", "yyyyMMdd");
            l_request.password = "12345678";
            l_request.fxSystemCode = "1";
            WEB3FXGftResultNoticeTelegramUnit l_resultNoticeTelegramUnit =
                new WEB3FXGftResultNoticeTelegramUnit();
            l_resultNoticeTelegramUnit.institutionCode = l_mainAccountParams.getInstitutionCode();
            l_resultNoticeTelegramUnit.branchCode = l_mainAccountParams.getBranchCode();
            l_resultNoticeTelegramUnit.requestNumber = "14";
            l_resultNoticeTelegramUnit.accountCode = l_mainAccountParams.getAccountCode();
            l_resultNoticeTelegramUnit.resultCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000000;
            l_resultNoticeTelegramUnit.dirSendTime= "20080808125959";
            l_resultNoticeTelegramUnit.gftOperationDiv= "0";
            l_resultNoticeTelegramUnit.fxFirstLoginId= "0";
            l_resultNoticeTelegramUnit.groupName= "0";
            l_resultNoticeTelegramUnit.wolfSession= "0";
            l_resultNoticeTelegramUnit.wolfAid= null;
            l_resultNoticeTelegramUnit.regetServiceId= "0";
            l_resultNoticeTelegramUnit.wolfSsid= "0";
            l_resultNoticeTelegramUnit.gftSendTime= "2008080812590s";
            l_resultNoticeTelegramUnit.hashValue= "0";
            l_resultNoticeTelegramUnit.cashinoutAmt2 = "200";
            l_resultNoticeTelegramUnit.cashinoutAmt = "100";
            l_request.fxGftResultNoticeTelegramUnit = l_resultNoticeTelegramUnit;
            
            TestDBUtility.deleteAllAndCommit(GftTransferStatusParams.TYPE);
            GftTransferStatusParams l_transferStatusParams =
                TestDBUtility.getGftTransferStatusRow();
            l_transferStatusParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_transferStatusParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_transferStatusParams.setOrderRequestNumber("14");
            l_transferStatusParams.setAccountCode(l_mainAccountParams.getAccountCode());
            l_transferStatusParams.setMrgTrnOrderRequestNumber("14");
            l_transferStatusParams.setCourseDiv(
                WEB3GftTransStatusCourseDivDef.CFD_COURSE);
            TestDBUtility.insertWithDel(l_transferStatusParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            l_compFxConditionParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_compFxConditionParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_compFxConditionParams.setFxSystemCode(l_request.fxSystemCode);
            l_compFxConditionParams.setFxSystemDiv("2");
            l_compFxConditionParams.setSoapConnectDiv("1");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            l_compFxConditionParams.setMultiCfdAccDiv("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);
            
            TestDBUtility.deleteAll(FxTransferMasterParams.TYPE);
            FxTransferMasterParams l_fxTransferMasterParams =
                TestDBUtility.getFxTransferMasterParams();
            l_fxTransferMasterParams.setFxSystemId(l_compFxConditionParams.getFxSystemId());
            l_fxTransferMasterParams.setTransferDiv("1");
            l_fxTransferMasterParams.setDeliveryDateDiv("1");
            
            l_fxTransferMasterParams.setTransferDiv(
                    WEB3GftTransStatusCourseDivDef.CFD_COURSE);
            l_fxTransferMasterParams.setRemarkName("CFD");
            l_fxTransferMasterParams.setRemarkName2("CFD2");
            l_fxTransferMasterParams.setRemarkName3("CFD3");
            l_fxTransferMasterParams.setRemarkName4("CFD4");
            l_fxTransferMasterParams.setRemarkName5("CFD5");
            l_fxTransferMasterParams.setRemarkName6("CFD6");
            l_fxTransferMasterParams.setRemarkName7("CFD7");
            l_fxTransferMasterParams.setRemarkName8("CFD8");
            
            l_fxTransferMasterParams.setFxSystemId(6241001);
            l_fxTransferMasterParams.setTransferDiv("1");
            TestDBUtility.insertWithDel(l_fxTransferMasterParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode(l_mainAccountParams.getBranchCode());
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "validateOrder",
                new Class[] {SubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSendAndReceiveValueSame",
                new Class[] {WEB3FXGftResultNoticeTelegramUnit.class},
                new Boolean(false));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());
                
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(123456));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "submitTransferOrder",
                new Class[]{
                    SubAccount.class, ProductTypeEnum.class, OrderTypeEnum.class, NewOrderSpec.class,
                    AioOrderManagerPersistenceEventInterceptor.class, long.class, String.class},
                null);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20090318", "yyyyMMdd"));
            
            //AioOrderUnitParams
            TestDBUtility.deleteAll(AioOrderUnitParams.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams =
                TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(l_mainAccountParams.getAccountId());
            l_aioOrderUnitParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_aioOrderUnitParams.setProductType(ProductTypeEnum.CASH);
            l_aioOrderUnitParams.setOrderRequestNumber("14");
            l_aioOrderUnitParams.setOrderCateg(OrderCategEnum.FX);
            l_aioOrderUnitParams.setBranchId(l_branchParams.getBranchId());
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            
            //AioOrderParams
            TestDBUtility.deleteAll(AioOrderParams.TYPE);
            AioOrderParams l_aioOrderParams =
                TestDBUtility.getAioOrderRow();
            l_aioOrderParams.setOrderId(l_aioOrderUnitParams.getOrderId());
            TestDBUtility.insertWithDel(l_aioOrderParams);

            l_impl.submitOrder(l_request);
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisResult = l_queryProcessor.doFindAllQuery(AioOrderUnitParams.TYPE);
            assertEquals(l_lisResult.size(), 1);
            
            AioOrderUnitRow l_aioOrderUnitRow =
                (AioOrderUnitRow)l_lisResult.get(0);

            assertFalse(l_aioOrderUnitParams.getBizDate().equals(l_aioOrderUnitRow.getBizDate()));
            assertFalse(l_aioOrderUnitParams.getDeliveryDate().equals(l_aioOrderUnitRow.getDeliveryDate()));
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitOrderCase0014()
    {
        final String STR_METHOD_NAME = "testSubmitOrderCase00014()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            WEB3FXTransToFXCompleteRequest l_request = new WEB3FXTransToFXCompleteRequest();
            l_request.orderId = "123";
            l_request.checkDate = WEB3DateUtility.getDate("20090317", "yyyyMMdd");
            l_request.password = "12345678";
            l_request.fxSystemCode = "1";
            WEB3FXGftResultNoticeTelegramUnit l_resultNoticeTelegramUnit =
                new WEB3FXGftResultNoticeTelegramUnit();
            l_resultNoticeTelegramUnit.institutionCode = l_mainAccountParams.getInstitutionCode();
            l_resultNoticeTelegramUnit.branchCode = l_mainAccountParams.getBranchCode();
            l_resultNoticeTelegramUnit.requestNumber = "14";
            l_resultNoticeTelegramUnit.accountCode = l_mainAccountParams.getAccountCode();
            l_resultNoticeTelegramUnit.resultCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000000;
            l_resultNoticeTelegramUnit.dirSendTime= "20080808125959";
            l_resultNoticeTelegramUnit.gftOperationDiv= "0";
            l_resultNoticeTelegramUnit.fxFirstLoginId= "0";
            l_resultNoticeTelegramUnit.groupName= "0";
            l_resultNoticeTelegramUnit.wolfSession= "0";
            l_resultNoticeTelegramUnit.wolfAid= null;
            l_resultNoticeTelegramUnit.regetServiceId= "0";
            l_resultNoticeTelegramUnit.wolfSsid= "0";
            l_resultNoticeTelegramUnit.gftSendTime= "2008080812590s";
            l_resultNoticeTelegramUnit.hashValue= "0";
            l_resultNoticeTelegramUnit.cashinoutAmt2 = "200";
            l_resultNoticeTelegramUnit.cashinoutAmt = "100";
            l_request.fxGftResultNoticeTelegramUnit = l_resultNoticeTelegramUnit;
            
            TestDBUtility.deleteAllAndCommit(GftTransferStatusParams.TYPE);
            GftTransferStatusParams l_transferStatusParams =
                TestDBUtility.getGftTransferStatusRow();
            l_transferStatusParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_transferStatusParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_transferStatusParams.setOrderRequestNumber("14");
            l_transferStatusParams.setAccountCode(l_mainAccountParams.getAccountCode());
            l_transferStatusParams.setMrgTrnOrderRequestNumber("14");
            l_transferStatusParams.setCourseDiv(
                WEB3GftTransStatusCourseDivDef.CFD_COURSE_2);
            TestDBUtility.insertWithDel(l_transferStatusParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            l_compFxConditionParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_compFxConditionParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_compFxConditionParams.setFxSystemCode(l_request.fxSystemCode);
            l_compFxConditionParams.setFxSystemDiv("2");
            l_compFxConditionParams.setSoapConnectDiv("1");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            l_compFxConditionParams.setMultiCfdAccDiv("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);
            
            TestDBUtility.deleteAll(FxTransferMasterParams.TYPE);
            FxTransferMasterParams l_fxTransferMasterParams =
                TestDBUtility.getFxTransferMasterParams();
            l_fxTransferMasterParams.setFxSystemId(l_compFxConditionParams.getFxSystemId());
            l_fxTransferMasterParams.setTransferDiv("1");
            l_fxTransferMasterParams.setDeliveryDateDiv("1");
            
            l_fxTransferMasterParams.setTransferDiv(
                    WEB3GftTransStatusCourseDivDef.CFD_COURSE);
            l_fxTransferMasterParams.setRemarkName("CFD");
            l_fxTransferMasterParams.setRemarkName2("CFD2");
            l_fxTransferMasterParams.setRemarkName3("CFD3");
            l_fxTransferMasterParams.setRemarkName4("CFD4");
            l_fxTransferMasterParams.setRemarkName5("CFD5");
            l_fxTransferMasterParams.setRemarkName6("CFD6");
            l_fxTransferMasterParams.setRemarkName7("CFD7");
            l_fxTransferMasterParams.setRemarkName8("CFD8");
            
            l_fxTransferMasterParams.setFxSystemId(6241001);
            l_fxTransferMasterParams.setTransferDiv("1");
            TestDBUtility.insertWithDel(l_fxTransferMasterParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode(l_mainAccountParams.getBranchCode());
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "validateOrder",
                new Class[] {SubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSendAndReceiveValueSame",
                new Class[] {WEB3FXGftResultNoticeTelegramUnit.class},
                new Boolean(false));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());
                
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(123456));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "submitTransferOrder",
                new Class[]{
                    SubAccount.class, ProductTypeEnum.class, OrderTypeEnum.class, NewOrderSpec.class,
                    AioOrderManagerPersistenceEventInterceptor.class, long.class, String.class},
                null);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20090318", "yyyyMMdd"));
            
            //AioOrderUnitParams
            TestDBUtility.deleteAll(AioOrderUnitParams.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams =
                TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(l_mainAccountParams.getAccountId());
            l_aioOrderUnitParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_aioOrderUnitParams.setProductType(ProductTypeEnum.CASH);
            l_aioOrderUnitParams.setOrderRequestNumber("14");
            l_aioOrderUnitParams.setOrderCateg(OrderCategEnum.FX);
            l_aioOrderUnitParams.setBranchId(l_branchParams.getBranchId());
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            
            //AioOrderParams
            TestDBUtility.deleteAll(AioOrderParams.TYPE);
            AioOrderParams l_aioOrderParams =
                TestDBUtility.getAioOrderRow();
            l_aioOrderParams.setOrderId(l_aioOrderUnitParams.getOrderId());
            TestDBUtility.insertWithDel(l_aioOrderParams);

            l_impl.submitOrder(l_request);
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisResult = l_queryProcessor.doFindAllQuery(AioOrderUnitParams.TYPE);
            assertEquals(l_lisResult.size(), 1);
            
            AioOrderUnitRow l_aioOrderUnitRow =
                (AioOrderUnitRow)l_lisResult.get(0);

            assertFalse(l_aioOrderUnitParams.getBizDate().equals(l_aioOrderUnitRow.getBizDate()));
            assertFalse(l_aioOrderUnitParams.getDeliveryDate().equals(l_aioOrderUnitRow.getDeliveryDate()));
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitOrderCase0015()
    {
        final String STR_METHOD_NAME = "testSubmitOrderCase00015()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            WEB3FXTransToFXCompleteRequest l_request = new WEB3FXTransToFXCompleteRequest();
            l_request.orderId = "123";
            l_request.checkDate = WEB3DateUtility.getDate("20090317", "yyyyMMdd");
            l_request.password = "12345678";
            l_request.fxSystemCode = "1";
            WEB3FXGftResultNoticeTelegramUnit l_resultNoticeTelegramUnit =
                new WEB3FXGftResultNoticeTelegramUnit();
            l_resultNoticeTelegramUnit.institutionCode = l_mainAccountParams.getInstitutionCode();
            l_resultNoticeTelegramUnit.branchCode = l_mainAccountParams.getBranchCode();
            l_resultNoticeTelegramUnit.requestNumber = "14";
            l_resultNoticeTelegramUnit.accountCode = l_mainAccountParams.getAccountCode();
            l_resultNoticeTelegramUnit.resultCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000000;
            l_resultNoticeTelegramUnit.dirSendTime= "20080808125959";
            l_resultNoticeTelegramUnit.gftOperationDiv= "0";
            l_resultNoticeTelegramUnit.fxFirstLoginId= "0";
            l_resultNoticeTelegramUnit.groupName= "0";
            l_resultNoticeTelegramUnit.wolfSession= "0";
            l_resultNoticeTelegramUnit.wolfAid= null;
            l_resultNoticeTelegramUnit.regetServiceId= "0";
            l_resultNoticeTelegramUnit.wolfSsid= "0";
            l_resultNoticeTelegramUnit.gftSendTime= "2008080812590s";
            l_resultNoticeTelegramUnit.hashValue= "0";
            l_resultNoticeTelegramUnit.cashinoutAmt2 = "200";
            l_resultNoticeTelegramUnit.cashinoutAmt = "100";
            l_request.fxGftResultNoticeTelegramUnit = l_resultNoticeTelegramUnit;
            
            TestDBUtility.deleteAllAndCommit(GftTransferStatusParams.TYPE);
            GftTransferStatusParams l_transferStatusParams =
                TestDBUtility.getGftTransferStatusRow();
            l_transferStatusParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_transferStatusParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_transferStatusParams.setOrderRequestNumber("14");
            l_transferStatusParams.setAccountCode(l_mainAccountParams.getAccountCode());
            l_transferStatusParams.setMrgTrnOrderRequestNumber("14");
            l_transferStatusParams.setCourseDiv(
                WEB3GftTransStatusCourseDivDef.CFD_COURSE_3);
            TestDBUtility.insertWithDel(l_transferStatusParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            l_compFxConditionParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_compFxConditionParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_compFxConditionParams.setFxSystemCode(l_request.fxSystemCode);
            l_compFxConditionParams.setFxSystemDiv("2");
            l_compFxConditionParams.setSoapConnectDiv("1");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            l_compFxConditionParams.setMultiCfdAccDiv("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);
            
            TestDBUtility.deleteAll(FxTransferMasterParams.TYPE);
            FxTransferMasterParams l_fxTransferMasterParams =
                TestDBUtility.getFxTransferMasterParams();
            l_fxTransferMasterParams.setFxSystemId(l_compFxConditionParams.getFxSystemId());
            l_fxTransferMasterParams.setTransferDiv("1");
            l_fxTransferMasterParams.setDeliveryDateDiv("1");
            
            l_fxTransferMasterParams.setTransferDiv(
                    WEB3GftTransStatusCourseDivDef.CFD_COURSE);
            l_fxTransferMasterParams.setRemarkName("CFD");
            l_fxTransferMasterParams.setRemarkName2("CFD2");
            l_fxTransferMasterParams.setRemarkName3("CFD3");
            l_fxTransferMasterParams.setRemarkName4("CFD4");
            l_fxTransferMasterParams.setRemarkName5("CFD5");
            l_fxTransferMasterParams.setRemarkName6("CFD6");
            l_fxTransferMasterParams.setRemarkName7("CFD7");
            l_fxTransferMasterParams.setRemarkName8("CFD8");
            
            l_fxTransferMasterParams.setFxSystemId(6241001);
            l_fxTransferMasterParams.setTransferDiv("1");
            TestDBUtility.insertWithDel(l_fxTransferMasterParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode(l_mainAccountParams.getBranchCode());
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "validateOrder",
                new Class[] {SubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSendAndReceiveValueSame",
                new Class[] {WEB3FXGftResultNoticeTelegramUnit.class},
                new Boolean(false));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());
                
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(123456));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "submitTransferOrder",
                new Class[]{
                    SubAccount.class, ProductTypeEnum.class, OrderTypeEnum.class, NewOrderSpec.class,
                    AioOrderManagerPersistenceEventInterceptor.class, long.class, String.class},
                null);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20090318", "yyyyMMdd"));
            
            //AioOrderUnitParams
            TestDBUtility.deleteAll(AioOrderUnitParams.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams =
                TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(l_mainAccountParams.getAccountId());
            l_aioOrderUnitParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_aioOrderUnitParams.setProductType(ProductTypeEnum.CASH);
            l_aioOrderUnitParams.setOrderRequestNumber("14");
            l_aioOrderUnitParams.setOrderCateg(OrderCategEnum.FX);
            l_aioOrderUnitParams.setBranchId(l_branchParams.getBranchId());
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            
            //AioOrderParams
            TestDBUtility.deleteAll(AioOrderParams.TYPE);
            AioOrderParams l_aioOrderParams =
                TestDBUtility.getAioOrderRow();
            l_aioOrderParams.setOrderId(l_aioOrderUnitParams.getOrderId());
            TestDBUtility.insertWithDel(l_aioOrderParams);

            l_impl.submitOrder(l_request);
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisResult = l_queryProcessor.doFindAllQuery(AioOrderUnitParams.TYPE);
            assertEquals(l_lisResult.size(), 1);
            
            AioOrderUnitRow l_aioOrderUnitRow =
                (AioOrderUnitRow)l_lisResult.get(0);

            assertFalse(l_aioOrderUnitParams.getBizDate().equals(l_aioOrderUnitRow.getBizDate()));
            assertFalse(l_aioOrderUnitParams.getDeliveryDate().equals(l_aioOrderUnitRow.getDeliveryDate()));
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitOrderCase0016()
    {
        final String STR_METHOD_NAME = "testSubmitOrderCase00016()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            WEB3FXTransToFXCompleteRequest l_request = new WEB3FXTransToFXCompleteRequest();
            l_request.orderId = "123";
            l_request.checkDate = WEB3DateUtility.getDate("20090317", "yyyyMMdd");
            l_request.password = "12345678";
            l_request.fxSystemCode = "1";
            WEB3FXGftResultNoticeTelegramUnit l_resultNoticeTelegramUnit =
                new WEB3FXGftResultNoticeTelegramUnit();
            l_resultNoticeTelegramUnit.institutionCode = l_mainAccountParams.getInstitutionCode();
            l_resultNoticeTelegramUnit.branchCode = l_mainAccountParams.getBranchCode();
            l_resultNoticeTelegramUnit.requestNumber = "14";
            l_resultNoticeTelegramUnit.accountCode = l_mainAccountParams.getAccountCode();
            l_resultNoticeTelegramUnit.resultCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000000;
            l_resultNoticeTelegramUnit.dirSendTime= "20080808125959";
            l_resultNoticeTelegramUnit.gftOperationDiv= "0";
            l_resultNoticeTelegramUnit.fxFirstLoginId= "0";
            l_resultNoticeTelegramUnit.groupName= "0";
            l_resultNoticeTelegramUnit.wolfSession= "0";
            l_resultNoticeTelegramUnit.wolfAid= null;
            l_resultNoticeTelegramUnit.regetServiceId= "0";
            l_resultNoticeTelegramUnit.wolfSsid= "0";
            l_resultNoticeTelegramUnit.gftSendTime= "2008080812590s";
            l_resultNoticeTelegramUnit.hashValue= "0";
            l_resultNoticeTelegramUnit.cashinoutAmt2 = "200";
            l_resultNoticeTelegramUnit.cashinoutAmt = "100";
            l_request.fxGftResultNoticeTelegramUnit = l_resultNoticeTelegramUnit;
            
            TestDBUtility.deleteAllAndCommit(GftTransferStatusParams.TYPE);
            GftTransferStatusParams l_transferStatusParams =
                TestDBUtility.getGftTransferStatusRow();
            l_transferStatusParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_transferStatusParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_transferStatusParams.setOrderRequestNumber("14");
            l_transferStatusParams.setAccountCode(l_mainAccountParams.getAccountCode());
            l_transferStatusParams.setMrgTrnOrderRequestNumber("14");
            l_transferStatusParams.setCourseDiv(
                WEB3GftTransStatusCourseDivDef.CFD_COURSE_4);
            TestDBUtility.insertWithDel(l_transferStatusParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            l_compFxConditionParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_compFxConditionParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_compFxConditionParams.setFxSystemCode(l_request.fxSystemCode);
            l_compFxConditionParams.setFxSystemDiv("2");
            l_compFxConditionParams.setSoapConnectDiv("1");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            l_compFxConditionParams.setMultiCfdAccDiv("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);
            
            TestDBUtility.deleteAll(FxTransferMasterParams.TYPE);
            FxTransferMasterParams l_fxTransferMasterParams =
                TestDBUtility.getFxTransferMasterParams();
            l_fxTransferMasterParams.setFxSystemId(l_compFxConditionParams.getFxSystemId());
            l_fxTransferMasterParams.setTransferDiv("1");
            l_fxTransferMasterParams.setDeliveryDateDiv("1");
            
            l_fxTransferMasterParams.setTransferDiv(
                    WEB3GftTransStatusCourseDivDef.CFD_COURSE);
            l_fxTransferMasterParams.setRemarkName("CFD");
            l_fxTransferMasterParams.setRemarkName2("CFD2");
            l_fxTransferMasterParams.setRemarkName3("CFD3");
            l_fxTransferMasterParams.setRemarkName4("CFD4");
            l_fxTransferMasterParams.setRemarkName5("CFD5");
            l_fxTransferMasterParams.setRemarkName6("CFD6");
            l_fxTransferMasterParams.setRemarkName7("CFD7");
            l_fxTransferMasterParams.setRemarkName8("CFD8");
            
            l_fxTransferMasterParams.setFxSystemId(6241001);
            l_fxTransferMasterParams.setTransferDiv("1");
            TestDBUtility.insertWithDel(l_fxTransferMasterParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode(l_mainAccountParams.getBranchCode());
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "validateOrder",
                new Class[] {SubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSendAndReceiveValueSame",
                new Class[] {WEB3FXGftResultNoticeTelegramUnit.class},
                new Boolean(false));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());
                
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(123456));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "submitTransferOrder",
                new Class[]{
                    SubAccount.class, ProductTypeEnum.class, OrderTypeEnum.class, NewOrderSpec.class,
                    AioOrderManagerPersistenceEventInterceptor.class, long.class, String.class},
                null);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20090318", "yyyyMMdd"));
            
            //AioOrderUnitParams
            TestDBUtility.deleteAll(AioOrderUnitParams.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams =
                TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(l_mainAccountParams.getAccountId());
            l_aioOrderUnitParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_aioOrderUnitParams.setProductType(ProductTypeEnum.CASH);
            l_aioOrderUnitParams.setOrderRequestNumber("14");
            l_aioOrderUnitParams.setOrderCateg(OrderCategEnum.FX);
            l_aioOrderUnitParams.setBranchId(l_branchParams.getBranchId());
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            
            //AioOrderParams
            TestDBUtility.deleteAll(AioOrderParams.TYPE);
            AioOrderParams l_aioOrderParams =
                TestDBUtility.getAioOrderRow();
            l_aioOrderParams.setOrderId(l_aioOrderUnitParams.getOrderId());
            TestDBUtility.insertWithDel(l_aioOrderParams);

            l_impl.submitOrder(l_request);
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisResult = l_queryProcessor.doFindAllQuery(AioOrderUnitParams.TYPE);
            assertEquals(l_lisResult.size(), 1);
            
            AioOrderUnitRow l_aioOrderUnitRow =
                (AioOrderUnitRow)l_lisResult.get(0);

            assertFalse(l_aioOrderUnitParams.getBizDate().equals(l_aioOrderUnitRow.getBizDate()));
            assertFalse(l_aioOrderUnitParams.getDeliveryDate().equals(l_aioOrderUnitRow.getDeliveryDate()));
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitOrderCase0017()
    {
        final String STR_METHOD_NAME = "testSubmitOrderCase00017()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            WEB3FXTransToFXCompleteRequest l_request = new WEB3FXTransToFXCompleteRequest();
            l_request.orderId = "123";
            l_request.checkDate = WEB3DateUtility.getDate("20090317", "yyyyMMdd");
            l_request.password = "12345678";
            l_request.fxSystemCode = "1";
            WEB3FXGftResultNoticeTelegramUnit l_resultNoticeTelegramUnit =
                new WEB3FXGftResultNoticeTelegramUnit();
            l_resultNoticeTelegramUnit.institutionCode = l_mainAccountParams.getInstitutionCode();
            l_resultNoticeTelegramUnit.branchCode = l_mainAccountParams.getBranchCode();
            l_resultNoticeTelegramUnit.requestNumber = "14";
            l_resultNoticeTelegramUnit.accountCode = l_mainAccountParams.getAccountCode();
            l_resultNoticeTelegramUnit.resultCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000000;
            l_resultNoticeTelegramUnit.dirSendTime= "20080808125959";
            l_resultNoticeTelegramUnit.gftOperationDiv= "0";
            l_resultNoticeTelegramUnit.fxFirstLoginId= "0";
            l_resultNoticeTelegramUnit.groupName= "0";
            l_resultNoticeTelegramUnit.wolfSession= "0";
            l_resultNoticeTelegramUnit.wolfAid= null;
            l_resultNoticeTelegramUnit.regetServiceId= "0";
            l_resultNoticeTelegramUnit.wolfSsid= "0";
            l_resultNoticeTelegramUnit.gftSendTime= "2008080812590s";
            l_resultNoticeTelegramUnit.hashValue= "0";
            l_resultNoticeTelegramUnit.cashinoutAmt2 = "200";
            l_resultNoticeTelegramUnit.cashinoutAmt = "100";
            l_request.fxGftResultNoticeTelegramUnit = l_resultNoticeTelegramUnit;
            
            TestDBUtility.deleteAllAndCommit(GftTransferStatusParams.TYPE);
            GftTransferStatusParams l_transferStatusParams =
                TestDBUtility.getGftTransferStatusRow();
            l_transferStatusParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_transferStatusParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_transferStatusParams.setOrderRequestNumber("14");
            l_transferStatusParams.setAccountCode(l_mainAccountParams.getAccountCode());
            l_transferStatusParams.setMrgTrnOrderRequestNumber("14");
            l_transferStatusParams.setCourseDiv(
                WEB3GftTransStatusCourseDivDef.CFD_COURSE_5);
            TestDBUtility.insertWithDel(l_transferStatusParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            l_compFxConditionParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_compFxConditionParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_compFxConditionParams.setFxSystemCode(l_request.fxSystemCode);
            l_compFxConditionParams.setFxSystemDiv("2");
            l_compFxConditionParams.setSoapConnectDiv("1");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            l_compFxConditionParams.setMultiCfdAccDiv("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);
            
            TestDBUtility.deleteAll(FxTransferMasterParams.TYPE);
            FxTransferMasterParams l_fxTransferMasterParams =
                TestDBUtility.getFxTransferMasterParams();
            l_fxTransferMasterParams.setFxSystemId(l_compFxConditionParams.getFxSystemId());
            l_fxTransferMasterParams.setTransferDiv("1");
            l_fxTransferMasterParams.setDeliveryDateDiv("1");
            
            l_fxTransferMasterParams.setTransferDiv(
                    WEB3GftTransStatusCourseDivDef.CFD_COURSE);
            l_fxTransferMasterParams.setRemarkName("CFD");
            l_fxTransferMasterParams.setRemarkName2("CFD2");
            l_fxTransferMasterParams.setRemarkName3("CFD3");
            l_fxTransferMasterParams.setRemarkName4("CFD4");
            l_fxTransferMasterParams.setRemarkName5("CFD5");
            l_fxTransferMasterParams.setRemarkName6("CFD6");
            l_fxTransferMasterParams.setRemarkName7("CFD7");
            l_fxTransferMasterParams.setRemarkName8("CFD8");
            
            l_fxTransferMasterParams.setFxSystemId(6241001);
            l_fxTransferMasterParams.setTransferDiv("1");
            TestDBUtility.insertWithDel(l_fxTransferMasterParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode(l_mainAccountParams.getBranchCode());
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "validateOrder",
                new Class[] {SubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSendAndReceiveValueSame",
                new Class[] {WEB3FXGftResultNoticeTelegramUnit.class},
                new Boolean(false));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());
                
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(123456));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "submitTransferOrder",
                new Class[]{
                    SubAccount.class, ProductTypeEnum.class, OrderTypeEnum.class, NewOrderSpec.class,
                    AioOrderManagerPersistenceEventInterceptor.class, long.class, String.class},
                null);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20090318", "yyyyMMdd"));
            
            //AioOrderUnitParams
            TestDBUtility.deleteAll(AioOrderUnitParams.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams =
                TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(l_mainAccountParams.getAccountId());
            l_aioOrderUnitParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_aioOrderUnitParams.setProductType(ProductTypeEnum.CASH);
            l_aioOrderUnitParams.setOrderRequestNumber("14");
            l_aioOrderUnitParams.setOrderCateg(OrderCategEnum.FX);
            l_aioOrderUnitParams.setBranchId(l_branchParams.getBranchId());
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            
            //AioOrderParams
            TestDBUtility.deleteAll(AioOrderParams.TYPE);
            AioOrderParams l_aioOrderParams =
                TestDBUtility.getAioOrderRow();
            l_aioOrderParams.setOrderId(l_aioOrderUnitParams.getOrderId());
            TestDBUtility.insertWithDel(l_aioOrderParams);

            l_impl.submitOrder(l_request);
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisResult = l_queryProcessor.doFindAllQuery(AioOrderUnitParams.TYPE);
            assertEquals(l_lisResult.size(), 1);
            
            AioOrderUnitRow l_aioOrderUnitRow =
                (AioOrderUnitRow)l_lisResult.get(0);

            assertFalse(l_aioOrderUnitParams.getBizDate().equals(l_aioOrderUnitRow.getBizDate()));
            assertFalse(l_aioOrderUnitParams.getDeliveryDate().equals(l_aioOrderUnitRow.getDeliveryDate()));
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitOrderCase0018()
    {
        final String STR_METHOD_NAME = "testSubmitOrderCase00018()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            WEB3FXTransToFXCompleteRequest l_request = new WEB3FXTransToFXCompleteRequest();
            l_request.orderId = "123";
            l_request.checkDate = WEB3DateUtility.getDate("20090317", "yyyyMMdd");
            l_request.password = "12345678";
            l_request.fxSystemCode = "1";
            WEB3FXGftResultNoticeTelegramUnit l_resultNoticeTelegramUnit =
                new WEB3FXGftResultNoticeTelegramUnit();
            l_resultNoticeTelegramUnit.institutionCode = l_mainAccountParams.getInstitutionCode();
            l_resultNoticeTelegramUnit.branchCode = l_mainAccountParams.getBranchCode();
            l_resultNoticeTelegramUnit.requestNumber = "14";
            l_resultNoticeTelegramUnit.accountCode = l_mainAccountParams.getAccountCode();
            l_resultNoticeTelegramUnit.resultCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000000;
            l_resultNoticeTelegramUnit.dirSendTime= "20080808125959";
            l_resultNoticeTelegramUnit.gftOperationDiv= "0";
            l_resultNoticeTelegramUnit.fxFirstLoginId= "0";
            l_resultNoticeTelegramUnit.groupName= "0";
            l_resultNoticeTelegramUnit.wolfSession= "0";
            l_resultNoticeTelegramUnit.wolfAid= null;
            l_resultNoticeTelegramUnit.regetServiceId= "0";
            l_resultNoticeTelegramUnit.wolfSsid= "0";
            l_resultNoticeTelegramUnit.gftSendTime= "2008080812590s";
            l_resultNoticeTelegramUnit.hashValue= "0";
            l_resultNoticeTelegramUnit.cashinoutAmt2 = "200";
            l_resultNoticeTelegramUnit.cashinoutAmt = "100";
            l_request.fxGftResultNoticeTelegramUnit = l_resultNoticeTelegramUnit;
            
            TestDBUtility.deleteAllAndCommit(GftTransferStatusParams.TYPE);
            GftTransferStatusParams l_transferStatusParams =
                TestDBUtility.getGftTransferStatusRow();
            l_transferStatusParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_transferStatusParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_transferStatusParams.setOrderRequestNumber("14");
            l_transferStatusParams.setAccountCode(l_mainAccountParams.getAccountCode());
            l_transferStatusParams.setMrgTrnOrderRequestNumber("14");
            l_transferStatusParams.setCourseDiv(
                WEB3GftTransStatusCourseDivDef.CFD_COURSE_6);
            TestDBUtility.insertWithDel(l_transferStatusParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            l_compFxConditionParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_compFxConditionParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_compFxConditionParams.setFxSystemCode(l_request.fxSystemCode);
            l_compFxConditionParams.setFxSystemDiv("2");
            l_compFxConditionParams.setSoapConnectDiv("1");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            l_compFxConditionParams.setMultiCfdAccDiv("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);
            
            TestDBUtility.deleteAll(FxTransferMasterParams.TYPE);
            FxTransferMasterParams l_fxTransferMasterParams =
                TestDBUtility.getFxTransferMasterParams();
            l_fxTransferMasterParams.setFxSystemId(l_compFxConditionParams.getFxSystemId());
            l_fxTransferMasterParams.setTransferDiv("1");
            l_fxTransferMasterParams.setDeliveryDateDiv("1");
            
            l_fxTransferMasterParams.setTransferDiv(
                    WEB3GftTransStatusCourseDivDef.CFD_COURSE);
            l_fxTransferMasterParams.setRemarkName("CFD");
            l_fxTransferMasterParams.setRemarkName2("CFD2");
            l_fxTransferMasterParams.setRemarkName3("CFD3");
            l_fxTransferMasterParams.setRemarkName4("CFD4");
            l_fxTransferMasterParams.setRemarkName5("CFD5");
            l_fxTransferMasterParams.setRemarkName6("CFD6");
            l_fxTransferMasterParams.setRemarkName7("CFD7");
            l_fxTransferMasterParams.setRemarkName8("CFD8");
            
            l_fxTransferMasterParams.setFxSystemId(6241001);
            l_fxTransferMasterParams.setTransferDiv("1");
            TestDBUtility.insertWithDel(l_fxTransferMasterParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode(l_mainAccountParams.getBranchCode());
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "validateOrder",
                new Class[] {SubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSendAndReceiveValueSame",
                new Class[] {WEB3FXGftResultNoticeTelegramUnit.class},
                new Boolean(false));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());
                
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(123456));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "submitTransferOrder",
                new Class[]{
                    SubAccount.class, ProductTypeEnum.class, OrderTypeEnum.class, NewOrderSpec.class,
                    AioOrderManagerPersistenceEventInterceptor.class, long.class, String.class},
                null);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20090318", "yyyyMMdd"));
            
            //AioOrderUnitParams
            TestDBUtility.deleteAll(AioOrderUnitParams.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams =
                TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(l_mainAccountParams.getAccountId());
            l_aioOrderUnitParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_aioOrderUnitParams.setProductType(ProductTypeEnum.CASH);
            l_aioOrderUnitParams.setOrderRequestNumber("14");
            l_aioOrderUnitParams.setOrderCateg(OrderCategEnum.FX);
            l_aioOrderUnitParams.setBranchId(l_branchParams.getBranchId());
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            
            //AioOrderParams
            TestDBUtility.deleteAll(AioOrderParams.TYPE);
            AioOrderParams l_aioOrderParams =
                TestDBUtility.getAioOrderRow();
            l_aioOrderParams.setOrderId(l_aioOrderUnitParams.getOrderId());
            TestDBUtility.insertWithDel(l_aioOrderParams);

            l_impl.submitOrder(l_request);
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisResult = l_queryProcessor.doFindAllQuery(AioOrderUnitParams.TYPE);
            assertEquals(l_lisResult.size(), 1);
            
            AioOrderUnitRow l_aioOrderUnitRow =
                (AioOrderUnitRow)l_lisResult.get(0);

            assertFalse(l_aioOrderUnitParams.getBizDate().equals(l_aioOrderUnitRow.getBizDate()));
            assertFalse(l_aioOrderUnitParams.getDeliveryDate().equals(l_aioOrderUnitRow.getDeliveryDate()));
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitOrderCase0019()
    {
        final String STR_METHOD_NAME = "testSubmitOrderCase00019()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            WEB3FXTransToFXCompleteRequest l_request = new WEB3FXTransToFXCompleteRequest();
            l_request.orderId = "123";
            l_request.checkDate = WEB3DateUtility.getDate("20090317", "yyyyMMdd");
            l_request.password = "12345678";
            l_request.fxSystemCode = "1";
            WEB3FXGftResultNoticeTelegramUnit l_resultNoticeTelegramUnit =
                new WEB3FXGftResultNoticeTelegramUnit();
            l_resultNoticeTelegramUnit.institutionCode = l_mainAccountParams.getInstitutionCode();
            l_resultNoticeTelegramUnit.branchCode = l_mainAccountParams.getBranchCode();
            l_resultNoticeTelegramUnit.requestNumber = "14";
            l_resultNoticeTelegramUnit.accountCode = l_mainAccountParams.getAccountCode();
            l_resultNoticeTelegramUnit.resultCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000000;
            l_resultNoticeTelegramUnit.dirSendTime= "20080808125959";
            l_resultNoticeTelegramUnit.gftOperationDiv= "0";
            l_resultNoticeTelegramUnit.fxFirstLoginId= "0";
            l_resultNoticeTelegramUnit.groupName= "0";
            l_resultNoticeTelegramUnit.wolfSession= "0";
            l_resultNoticeTelegramUnit.wolfAid= null;
            l_resultNoticeTelegramUnit.regetServiceId= "0";
            l_resultNoticeTelegramUnit.wolfSsid= "0";
            l_resultNoticeTelegramUnit.gftSendTime= "2008080812590s";
            l_resultNoticeTelegramUnit.hashValue= "0";
            l_resultNoticeTelegramUnit.cashinoutAmt2 = "200";
            l_resultNoticeTelegramUnit.cashinoutAmt = "100";
            l_request.fxGftResultNoticeTelegramUnit = l_resultNoticeTelegramUnit;
            
            TestDBUtility.deleteAllAndCommit(GftTransferStatusParams.TYPE);
            GftTransferStatusParams l_transferStatusParams =
                TestDBUtility.getGftTransferStatusRow();
            l_transferStatusParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_transferStatusParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_transferStatusParams.setOrderRequestNumber("14");
            l_transferStatusParams.setAccountCode(l_mainAccountParams.getAccountCode());
            l_transferStatusParams.setMrgTrnOrderRequestNumber("14");
            l_transferStatusParams.setCourseDiv(
                WEB3GftTransStatusCourseDivDef.CFD_COURSE_7);
            TestDBUtility.insertWithDel(l_transferStatusParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            l_compFxConditionParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_compFxConditionParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_compFxConditionParams.setFxSystemCode(l_request.fxSystemCode);
            l_compFxConditionParams.setFxSystemDiv("2");
            l_compFxConditionParams.setSoapConnectDiv("1");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            l_compFxConditionParams.setMultiCfdAccDiv("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);
            
            TestDBUtility.deleteAll(FxTransferMasterParams.TYPE);
            FxTransferMasterParams l_fxTransferMasterParams =
                TestDBUtility.getFxTransferMasterParams();
            l_fxTransferMasterParams.setFxSystemId(l_compFxConditionParams.getFxSystemId());
            l_fxTransferMasterParams.setTransferDiv("1");
            l_fxTransferMasterParams.setDeliveryDateDiv("1");
            
            l_fxTransferMasterParams.setTransferDiv(
                    WEB3GftTransStatusCourseDivDef.CFD_COURSE);
            l_fxTransferMasterParams.setRemarkName("CFD");
            l_fxTransferMasterParams.setRemarkName2("CFD2");
            l_fxTransferMasterParams.setRemarkName3("CFD3");
            l_fxTransferMasterParams.setRemarkName4("CFD4");
            l_fxTransferMasterParams.setRemarkName5("CFD5");
            l_fxTransferMasterParams.setRemarkName6("CFD6");
            l_fxTransferMasterParams.setRemarkName7("CFD7");
            l_fxTransferMasterParams.setRemarkName8("CFD8");
            
            l_fxTransferMasterParams.setFxSystemId(6241001);
            l_fxTransferMasterParams.setTransferDiv("1");
            TestDBUtility.insertWithDel(l_fxTransferMasterParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode(l_mainAccountParams.getBranchCode());
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "validateOrder",
                new Class[] {SubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSendAndReceiveValueSame",
                new Class[] {WEB3FXGftResultNoticeTelegramUnit.class},
                new Boolean(false));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());
                
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(123456));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "submitTransferOrder",
                new Class[]{
                    SubAccount.class, ProductTypeEnum.class, OrderTypeEnum.class, NewOrderSpec.class,
                    AioOrderManagerPersistenceEventInterceptor.class, long.class, String.class},
                null);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20090318", "yyyyMMdd"));
            
            //AioOrderUnitParams
            TestDBUtility.deleteAll(AioOrderUnitParams.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams =
                TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(l_mainAccountParams.getAccountId());
            l_aioOrderUnitParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_aioOrderUnitParams.setProductType(ProductTypeEnum.CASH);
            l_aioOrderUnitParams.setOrderRequestNumber("14");
            l_aioOrderUnitParams.setOrderCateg(OrderCategEnum.FX);
            l_aioOrderUnitParams.setBranchId(l_branchParams.getBranchId());
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            
            //AioOrderParams
            TestDBUtility.deleteAll(AioOrderParams.TYPE);
            AioOrderParams l_aioOrderParams =
                TestDBUtility.getAioOrderRow();
            l_aioOrderParams.setOrderId(l_aioOrderUnitParams.getOrderId());
            TestDBUtility.insertWithDel(l_aioOrderParams);

            l_impl.submitOrder(l_request);
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisResult = l_queryProcessor.doFindAllQuery(AioOrderUnitParams.TYPE);
            assertEquals(l_lisResult.size(), 1);
            
            AioOrderUnitRow l_aioOrderUnitRow =
                (AioOrderUnitRow)l_lisResult.get(0);

            assertFalse(l_aioOrderUnitParams.getBizDate().equals(l_aioOrderUnitRow.getBizDate()));
            assertFalse(l_aioOrderUnitParams.getDeliveryDate().equals(l_aioOrderUnitRow.getDeliveryDate()));
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitOrderCase0020()
    {
        final String STR_METHOD_NAME = "testSubmitOrderCase00020()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            WEB3FXTransToFXCompleteRequest l_request = new WEB3FXTransToFXCompleteRequest();
            l_request.orderId = "123";
            l_request.checkDate = WEB3DateUtility.getDate("20090317", "yyyyMMdd");
            l_request.password = "12345678";
            l_request.fxSystemCode = "1";
            WEB3FXGftResultNoticeTelegramUnit l_resultNoticeTelegramUnit =
                new WEB3FXGftResultNoticeTelegramUnit();
            l_resultNoticeTelegramUnit.institutionCode = l_mainAccountParams.getInstitutionCode();
            l_resultNoticeTelegramUnit.branchCode = l_mainAccountParams.getBranchCode();
            l_resultNoticeTelegramUnit.requestNumber = "14";
            l_resultNoticeTelegramUnit.accountCode = l_mainAccountParams.getAccountCode();
            l_resultNoticeTelegramUnit.resultCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000000;
            l_resultNoticeTelegramUnit.dirSendTime= "20080808125959";
            l_resultNoticeTelegramUnit.gftOperationDiv= "0";
            l_resultNoticeTelegramUnit.fxFirstLoginId= "0";
            l_resultNoticeTelegramUnit.groupName= "0";
            l_resultNoticeTelegramUnit.wolfSession= "0";
            l_resultNoticeTelegramUnit.wolfAid= null;
            l_resultNoticeTelegramUnit.regetServiceId= "0";
            l_resultNoticeTelegramUnit.wolfSsid= "0";
            l_resultNoticeTelegramUnit.gftSendTime= "2008080812590s";
            l_resultNoticeTelegramUnit.hashValue= "0";
            l_resultNoticeTelegramUnit.cashinoutAmt2 = "200";
            l_resultNoticeTelegramUnit.cashinoutAmt = "100";
            l_request.fxGftResultNoticeTelegramUnit = l_resultNoticeTelegramUnit;
            
            TestDBUtility.deleteAllAndCommit(GftTransferStatusParams.TYPE);
            GftTransferStatusParams l_transferStatusParams =
                TestDBUtility.getGftTransferStatusRow();
            l_transferStatusParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_transferStatusParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_transferStatusParams.setOrderRequestNumber("14");
            l_transferStatusParams.setAccountCode(l_mainAccountParams.getAccountCode());
            l_transferStatusParams.setMrgTrnOrderRequestNumber("14");
            l_transferStatusParams.setCourseDiv(
                WEB3GftTransStatusCourseDivDef.CFD_COURSE_8);
            TestDBUtility.insertWithDel(l_transferStatusParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            l_compFxConditionParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_compFxConditionParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_compFxConditionParams.setFxSystemCode(l_request.fxSystemCode);
            l_compFxConditionParams.setFxSystemDiv("2");
            l_compFxConditionParams.setSoapConnectDiv("1");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            l_compFxConditionParams.setMultiCfdAccDiv("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);
            
            TestDBUtility.deleteAll(FxTransferMasterParams.TYPE);
            FxTransferMasterParams l_fxTransferMasterParams =
                TestDBUtility.getFxTransferMasterParams();
            l_fxTransferMasterParams.setFxSystemId(l_compFxConditionParams.getFxSystemId());
            l_fxTransferMasterParams.setTransferDiv("1");
            l_fxTransferMasterParams.setDeliveryDateDiv("1");
            
            l_fxTransferMasterParams.setTransferDiv(
                    WEB3GftTransStatusCourseDivDef.CFD_COURSE);
            l_fxTransferMasterParams.setRemarkName("CFD");
            l_fxTransferMasterParams.setRemarkName2("CFD2");
            l_fxTransferMasterParams.setRemarkName3("CFD3");
            l_fxTransferMasterParams.setRemarkName4("CFD4");
            l_fxTransferMasterParams.setRemarkName5("CFD5");
            l_fxTransferMasterParams.setRemarkName6("CFD6");
            l_fxTransferMasterParams.setRemarkName7("CFD7");
            l_fxTransferMasterParams.setRemarkName8("CFD8");
            
            l_fxTransferMasterParams.setFxSystemId(6241001);
            l_fxTransferMasterParams.setTransferDiv("1");
            TestDBUtility.insertWithDel(l_fxTransferMasterParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode(l_mainAccountParams.getBranchCode());
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "validateOrder",
                new Class[] {SubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSendAndReceiveValueSame",
                new Class[] {WEB3FXGftResultNoticeTelegramUnit.class},
                new Boolean(false));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());
                
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(123456));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "submitTransferOrder",
                new Class[]{
                    SubAccount.class, ProductTypeEnum.class, OrderTypeEnum.class, NewOrderSpec.class,
                    AioOrderManagerPersistenceEventInterceptor.class, long.class, String.class},
                null);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20090318", "yyyyMMdd"));
            
            //AioOrderUnitParams
            TestDBUtility.deleteAll(AioOrderUnitParams.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams =
                TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(l_mainAccountParams.getAccountId());
            l_aioOrderUnitParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_aioOrderUnitParams.setProductType(ProductTypeEnum.CASH);
            l_aioOrderUnitParams.setOrderRequestNumber("14");
            l_aioOrderUnitParams.setOrderCateg(OrderCategEnum.FX);
            l_aioOrderUnitParams.setBranchId(l_branchParams.getBranchId());
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            
            //AioOrderParams
            TestDBUtility.deleteAll(AioOrderParams.TYPE);
            AioOrderParams l_aioOrderParams =
                TestDBUtility.getAioOrderRow();
            l_aioOrderParams.setOrderId(l_aioOrderUnitParams.getOrderId());
            TestDBUtility.insertWithDel(l_aioOrderParams);

            l_impl.submitOrder(l_request);
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisResult = l_queryProcessor.doFindAllQuery(AioOrderUnitParams.TYPE);
            assertEquals(l_lisResult.size(), 1);
            
            AioOrderUnitRow l_aioOrderUnitRow =
                (AioOrderUnitRow)l_lisResult.get(0);

            assertFalse(l_aioOrderUnitParams.getBizDate().equals(l_aioOrderUnitRow.getBizDate()));
            assertFalse(l_aioOrderUnitParams.getDeliveryDate().equals(l_aioOrderUnitRow.getDeliveryDate()));
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitOrderCase0021()
    {
        final String STR_METHOD_NAME = "testSubmitOrderCase00021()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            WEB3FXTransToFXCompleteRequest l_request = new WEB3FXTransToFXCompleteRequest();
            l_request.orderId = "123";
            l_request.checkDate = WEB3DateUtility.getDate("20090317", "yyyyMMdd");
            l_request.password = "12345678";
            l_request.fxSystemCode = "1";
            WEB3FXGftResultNoticeTelegramUnit l_resultNoticeTelegramUnit =
                new WEB3FXGftResultNoticeTelegramUnit();
            l_resultNoticeTelegramUnit.institutionCode = l_mainAccountParams.getInstitutionCode();
            l_resultNoticeTelegramUnit.branchCode = l_mainAccountParams.getBranchCode();
            l_resultNoticeTelegramUnit.requestNumber = "14";
            l_resultNoticeTelegramUnit.accountCode = l_mainAccountParams.getAccountCode();
            l_resultNoticeTelegramUnit.resultCode = WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000000;
            l_resultNoticeTelegramUnit.dirSendTime= "20080808125959";
            l_resultNoticeTelegramUnit.gftOperationDiv= "0";
            l_resultNoticeTelegramUnit.fxFirstLoginId= "0";
            l_resultNoticeTelegramUnit.groupName= "0";
            l_resultNoticeTelegramUnit.wolfSession= "0";
            l_resultNoticeTelegramUnit.wolfAid= null;
            l_resultNoticeTelegramUnit.regetServiceId= "0";
            l_resultNoticeTelegramUnit.wolfSsid= "0";
            l_resultNoticeTelegramUnit.gftSendTime= "2008080812590s";
            l_resultNoticeTelegramUnit.hashValue= "0";
            l_resultNoticeTelegramUnit.cashinoutAmt2 = "200";
            l_resultNoticeTelegramUnit.cashinoutAmt = "100";
            l_request.fxGftResultNoticeTelegramUnit = l_resultNoticeTelegramUnit;
            
            TestDBUtility.deleteAllAndCommit(GftTransferStatusParams.TYPE);
            GftTransferStatusParams l_transferStatusParams =
                TestDBUtility.getGftTransferStatusRow();
            l_transferStatusParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_transferStatusParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_transferStatusParams.setOrderRequestNumber("14");
            l_transferStatusParams.setAccountCode(l_mainAccountParams.getAccountCode());
            l_transferStatusParams.setMrgTrnOrderRequestNumber("14");
            l_transferStatusParams.setCourseDiv(
                WEB3GftTransStatusCourseDivDef.CFD_COURSE_8);
            TestDBUtility.insertWithDel(l_transferStatusParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            l_compFxConditionParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_compFxConditionParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_compFxConditionParams.setFxSystemCode(l_request.fxSystemCode);
            l_compFxConditionParams.setFxSystemDiv("2");
            l_compFxConditionParams.setSoapConnectDiv("1");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            l_compFxConditionParams.setMultiCfdAccDiv("0");
            TestDBUtility.insertWithDel(l_compFxConditionParams);
            
            TestDBUtility.deleteAll(FxTransferMasterParams.TYPE);
            FxTransferMasterParams l_fxTransferMasterParams =
                TestDBUtility.getFxTransferMasterParams();
            l_fxTransferMasterParams.setFxSystemId(l_compFxConditionParams.getFxSystemId());
            l_fxTransferMasterParams.setTransferDiv("1");
            l_fxTransferMasterParams.setDeliveryDateDiv("1");
            
            l_fxTransferMasterParams.setTransferDiv(
                    WEB3GftTransStatusCourseDivDef.CFD_COURSE);
            l_fxTransferMasterParams.setRemarkName("CFD");
            l_fxTransferMasterParams.setRemarkName2("CFD2");
            l_fxTransferMasterParams.setRemarkName3("CFD3");
            l_fxTransferMasterParams.setRemarkName4("CFD4");
            l_fxTransferMasterParams.setRemarkName5("CFD5");
            l_fxTransferMasterParams.setRemarkName6("CFD6");
            l_fxTransferMasterParams.setRemarkName7("CFD7");
            l_fxTransferMasterParams.setRemarkName8("CFD8");
            
            l_fxTransferMasterParams.setFxSystemId(6241001);
            l_fxTransferMasterParams.setTransferDiv("1");
            TestDBUtility.insertWithDel(l_fxTransferMasterParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode(l_mainAccountParams.getBranchCode());
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "validateOrder",
                new Class[] {SubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSendAndReceiveValueSame",
                new Class[] {WEB3FXGftResultNoticeTelegramUnit.class},
                new Boolean(false));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());
                
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(123456));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "submitTransferOrder",
                new Class[]{
                    SubAccount.class, ProductTypeEnum.class, OrderTypeEnum.class, NewOrderSpec.class,
                    AioOrderManagerPersistenceEventInterceptor.class, long.class, String.class},
                null);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20090318", "yyyyMMdd"));
            
            //AioOrderUnitParams
            TestDBUtility.deleteAll(AioOrderUnitParams.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams =
                TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(l_mainAccountParams.getAccountId());
            l_aioOrderUnitParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_aioOrderUnitParams.setProductType(ProductTypeEnum.CASH);
            l_aioOrderUnitParams.setOrderRequestNumber("14");
            l_aioOrderUnitParams.setOrderCateg(OrderCategEnum.FX);
            l_aioOrderUnitParams.setBranchId(l_branchParams.getBranchId());
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            
            //AioOrderParams
            TestDBUtility.deleteAll(AioOrderParams.TYPE);
            AioOrderParams l_aioOrderParams =
                TestDBUtility.getAioOrderRow();
            l_aioOrderParams.setOrderId(l_aioOrderUnitParams.getOrderId());
            TestDBUtility.insertWithDel(l_aioOrderParams);

            l_impl.submitOrder(l_request);
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisResult = l_queryProcessor.doFindAllQuery(AioOrderUnitParams.TYPE);
            assertEquals(l_lisResult.size(), 1);
            
            AioOrderUnitRow l_aioOrderUnitRow =
                (AioOrderUnitRow)l_lisResult.get(0);

            assertFalse(l_aioOrderUnitParams.getBizDate().equals(l_aioOrderUnitRow.getBizDate()));
            assertFalse(l_aioOrderUnitParams.getDeliveryDate().equals(l_aioOrderUnitRow.getDeliveryDate()));
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    class WEB3FXTransToFXServiceImplForTest extends WEB3FXTransToFXServiceImpl
    {
        protected WEB3FXTransToFXAskingResponse startOrder(
                WEB3FXTransToFXAskingRequest l_request)
                    throws WEB3BaseException
        {

            WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit =
                new WEB3FXGftAskingTelegramUnit();
            l_fxGftAskingTelegramUnit.institutionCode = "0D";
            l_fxGftAskingTelegramUnit.branchCode = "381";
            l_fxGftAskingTelegramUnit.requestNumber = "14";

            WEB3FXTransToFXAskingResponse l_response = 
                new WEB3FXTransToFXAskingResponse();
            l_response.fxGftAskingTelegramUnit = l_fxGftAskingTelegramUnit;
            return l_response;
        }

        protected WEB3FXTransToFXCompleteResponse submitOrder(
                WEB3FXTransToFXCompleteRequest l_request)
                    throws WEB3BaseException
            {

                String l_strResultCode = l_request.fxGftResultNoticeTelegramUnit.resultCode;
                //assertEquals(l_strResultCode, "10002");

                WEB3FXTransToFXCompleteResponse l_response =
                    new WEB3FXTransToFXCompleteResponse();
                l_response.lastUpdatedTimestamp = WEB3DateUtility.getDate("20081010", "yyyyMMdd");
                l_response.orderActionId = "102";
                l_response.deliveryDate = WEB3DateUtility.getDate("20081011", "yyyyMMdd");
                return l_response;
            }
    }

    class WEB3FXTransToFXServiceImplForTest1 extends WEB3FXTransToFXServiceImpl
    {
        protected WEB3FXTransToFXAskingResponse startOrder(
                WEB3FXTransToFXAskingRequest l_request)
                    throws WEB3BaseException
        {

            WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit =
                new WEB3FXGftAskingTelegramUnit();
            l_fxGftAskingTelegramUnit.institutionCode = "0D";
            l_fxGftAskingTelegramUnit.branchCode = "381";
            l_fxGftAskingTelegramUnit.requestNumber = "14";

            WEB3FXTransToFXAskingResponse l_response = 
                new WEB3FXTransToFXAskingResponse();
            l_response.fxGftAskingTelegramUnit = l_fxGftAskingTelegramUnit;
            return l_response;
        }

        protected WEB3FXTransToFXCompleteResponse submitOrder(
            WEB3FXTransToFXCompleteRequest l_request)
                throws WEB3BaseException
        {

            String l_strResultCode = l_request.fxGftResultNoticeTelegramUnit.resultCode;
            assertEquals(l_strResultCode, "00000901");

            WEB3FXTransToFXCompleteResponse l_response =
                new WEB3FXTransToFXCompleteResponse();
            l_response.lastUpdatedTimestamp = WEB3DateUtility.getDate("20081010", "yyyyMMdd");
            l_response.orderActionId = "102";
            l_response.deliveryDate = WEB3DateUtility.getDate("20081011", "yyyyMMdd");
            return l_response;
        }
    }

    class WEB3FXTransToFXServiceImplForTest2 extends WEB3FXTransToFXServiceImpl
    {
        protected WEB3FXTransToFXAskingResponse startOrder(
                WEB3FXTransToFXAskingRequest l_request)
                    throws WEB3BaseException
        {

            WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit =
                new WEB3FXGftAskingTelegramUnit();
            l_fxGftAskingTelegramUnit.institutionCode = "0D";
            l_fxGftAskingTelegramUnit.branchCode = "381";
            l_fxGftAskingTelegramUnit.requestNumber = "14";

            WEB3FXTransToFXAskingResponse l_response = 
                new WEB3FXTransToFXAskingResponse();
            l_response.fxGftAskingTelegramUnit = l_fxGftAskingTelegramUnit;
            return l_response;
        }

        protected WEB3FXTransToFXCompleteResponse submitOrder(
            WEB3FXTransToFXCompleteRequest l_request)
                throws WEB3BaseException
        {

            String l_strResultCode = l_request.fxGftResultNoticeTelegramUnit.resultCode;
            assertEquals(l_strResultCode, "00000199");

            WEB3FXTransToFXCompleteResponse l_response =
                new WEB3FXTransToFXCompleteResponse();
            l_response.lastUpdatedTimestamp = WEB3DateUtility.getDate("20081010", "yyyyMMdd");
            l_response.orderActionId = "102";
            l_response.deliveryDate = WEB3DateUtility.getDate("20081011", "yyyyMMdd");
            return l_response;
        }
    }
    
     class WEB3FXTelegramProcessServiceImplForTest extends WEB3FXTelegramProcessServiceImpl
    {
        
        
            public String createGFTTelegramHashValue(WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit)
            throws WEB3BaseException
        {
    
            return "aaa";
            
        }
        
    }
}
@


1.1
log
@*** empty log message ***
@
text
@d2011 5
a2015 5
            SendSyncRequestResponse l_requestResponse = new SendSyncRequestResponse();
            com.gftforex.soap.api.ResultInfoBase resin = new ResultInfoBase();
            resin.setMajorStatusCode(1);
            resin.setMinorStatusCode(2);
            l_requestResponse.setSendSyncRequestResult(resin);
d2155 5
a2159 5
            SendSyncRequestResponse l_requestResponse = new SendSyncRequestResponse();
            com.gftforex.soap.api.ResultInfoBase resin = new ResultInfoBase();
            resin.setMajorStatusCode(1);
            resin.setMinorStatusCode(2);
            l_requestResponse.setSendSyncRequestResult(resin);
d2382 5
a2386 5
            SendSyncRequestResponse l_requestResponse = new SendSyncRequestResponse();
            com.gftforex.soap.api.ResultInfoBase resin = new ResultInfoBase();
            resin.setMajorStatusCode(1);
            resin.setMinorStatusCode(2);
            l_requestResponse.setSendSyncRequestResult(resin);
d4046 5
a4050 5
            SendSyncRequestResponse l_requestResponse = new SendSyncRequestResponse();
            com.gftforex.soap.api.ResultInfoBase resin = new ResultInfoBase();
            resin.setMajorStatusCode(1);
            resin.setMinorStatusCode(2);
            l_requestResponse.setSendSyncRequestResult(resin);
d4190 5
a4194 5
            SendSyncRequestResponse l_requestResponse = new SendSyncRequestResponse();
            com.gftforex.soap.api.ResultInfoBase resin = new ResultInfoBase();
            resin.setMajorStatusCode(1);
            resin.setMinorStatusCode(2);
            l_requestResponse.setSendSyncRequestResult(resin);
d4359 5
a4363 5
            SendSyncRequestResponse l_requestResponse = new SendSyncRequestResponse();
            com.gftforex.soap.api.ResultInfoBase resin = new ResultInfoBase();
            resin.setMajorStatusCode(1);
            resin.setMinorStatusCode(2);
            l_requestResponse.setSendSyncRequestResult(resin);
d4551 5
a4555 5
            SendSyncRequestResponse l_requestResponse = new SendSyncRequestResponse();
            com.gftforex.soap.api.ResultInfoBase resin = new ResultInfoBase();
            resin.setMajorStatusCode(1);
            resin.setMinorStatusCode(2);
            l_requestResponse.setSendSyncRequestResult(resin);
@

