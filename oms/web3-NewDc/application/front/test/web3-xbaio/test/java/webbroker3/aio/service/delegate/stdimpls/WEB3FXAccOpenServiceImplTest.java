head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.04.08.05.40.56;	author zhang-tengyu;	state Exp;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	666;
commitid	2384d9e9fe8197c;
filename	WEB3FXAccOpenServiceImplTest.java;

1.1
date	2011.04.07.01.38.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FXAccOpenServiceImplTest.java;


desc
@@


1.2
log
@*** empty log message ***
@
text
@package webbroker3.aio.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import webbroker3.common.define.WEB3ConnectDivDef;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.SubAccountImpl;
import test.util.TestDBUtility;

import webbroker3.aio.WEB3FXDataControlService;
import webbroker3.aio.WEB3FXDataControlServiceImpl;
import webbroker3.aio.WEB3FXDataControlServiceImplForMock;
import webbroker3.aio.WEB3FXTelegramProcessService;
import webbroker3.aio.WEB3FXTelegramProcessServiceImpl;
import webbroker3.aio.WEB3FXTelegramProcessServiceImplForMock;
import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.data.CompFxConditionRow;
import webbroker3.aio.data.FxAccountCodeParams;
import webbroker3.aio.data.FxAccountParams;
import webbroker3.aio.data.FxAccountRow;
import webbroker3.aio.data.GftAccountOpenStatusParams;
import webbroker3.aio.data.GftAccountOpenStatusRow;
import webbroker3.aio.define.WEB3AioAcceptResultCodeDef;
import webbroker3.aio.message.WEB3FXAccInformationUnit;
import webbroker3.aio.message.WEB3FXAccOpenAskingRequest;
import webbroker3.aio.message.WEB3FXAccOpenAskingResponse;
import webbroker3.aio.message.WEB3FXAccOpenCompleteRequest;
import webbroker3.aio.message.WEB3FXAccOpenCompleteResponse;
import webbroker3.aio.message.WEB3FXAccOpenCompleteSoapRequest;
import webbroker3.aio.message.WEB3FXAccOpenCompleteSoapResponse;
import webbroker3.aio.message.WEB3FXAccOpenConfirmRequest;
import webbroker3.aio.message.WEB3FXGftAskingTelegramUnit;
import webbroker3.aio.message.WEB3FXGftResultNoticeTelegramUnit;
import webbroker3.aio.message.WEB3FXTradeAgreementUnit;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3GftMessageOperationDef;
import webbroker3.gentrade.data.AccOpenDivRow;
import webbroker3.gentrade.data.BranchPreferencesParams;
import webbroker3.gentrade.data.InstitutionPreferencesParams;
import webbroker3.gentrade.data.QuestionAnswerParams;
import webbroker3.gentrade.data.QuestionAnswerRow;
import webbroker3.gentrade.data.SoapConnectPrefRpcParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3FXAccOpenServiceImplTest extends TestBaseForMock {

    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXAccOpenServiceImplTest.class);

    WEB3FXAccOpenServiceImpl l_impl;
    public WEB3FXAccOpenServiceImplTest(String arg0) {
        super(arg0);
    }

    protected void setUp() throws Exception {
        super.setUp();
        l_impl = new WEB3FXAccOpenServiceImpl();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        Services.overrideService(WEB3FXDataControlService.class,
            new WEB3FXDataControlServiceImplForMock());
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testSubmitAccountOpen_Case001()
    {
        final String STR_METHOD_NAME = "testSubmitAccountOpen_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FXAccOpenCompleteRequest l_request =
                new WEB3FXAccOpenCompleteRequest();
            WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit =
                new WEB3FXGftResultNoticeTelegramUnit();
            l_fxGftResultNoticeTelegramUnit.dirSendTime = "20081010";
            l_fxGftResultNoticeTelegramUnit.gftOperationDiv = "1";
            l_fxGftResultNoticeTelegramUnit.fxAccountCode = "111111";
            l_fxGftResultNoticeTelegramUnit.fxMailAddress = "1@@163.com";
            l_fxGftResultNoticeTelegramUnit.fxFirstLoginId = "1";
            l_fxGftResultNoticeTelegramUnit.fxFirstPassword = "111111";
            l_fxGftResultNoticeTelegramUnit.groupName = "1";
            l_fxGftResultNoticeTelegramUnit.cashinoutAmt = "1";
            l_fxGftResultNoticeTelegramUnit.wolfSession = "1";
            l_fxGftResultNoticeTelegramUnit.wolfAid = "1";
            l_fxGftResultNoticeTelegramUnit.regetServiceId = "1";
            l_fxGftResultNoticeTelegramUnit.wolfSsid = "1";
            l_fxGftResultNoticeTelegramUnit.institutionCode = "0D";
            l_fxGftResultNoticeTelegramUnit.branchCode = "624";
            l_fxGftResultNoticeTelegramUnit.accountCode = "1111111";
            l_fxGftResultNoticeTelegramUnit.requestNumber = "1";
            l_fxGftResultNoticeTelegramUnit.resultCode = "00000000";
            l_fxGftResultNoticeTelegramUnit.gftSendTime = "1";
            l_fxGftResultNoticeTelegramUnit.gftAcc1 = "1";
            l_fxGftResultNoticeTelegramUnit.gftAcc2 = "1";
            l_fxGftResultNoticeTelegramUnit.fxLastName = "1";
            l_fxGftResultNoticeTelegramUnit.fxFirstName = "1";
            l_fxGftResultNoticeTelegramUnit.hashValue = "1";
            l_fxGftResultNoticeTelegramUnit.deliveryDate = "1";
            l_fxGftResultNoticeTelegramUnit.cashinoutAmt2 = "1";
            l_request.fxGftResultNoticeTelegramUnit = l_fxGftResultNoticeTelegramUnit;
            WEB3FXAccOpenServiceImpl l_impl = new WEB3FXAccOpenServiceImpl();

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(123456);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(TraderParams.TYPE);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123456);
            l_mainAccountParams.setBranchId(1001);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("1111111");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(GftAccountOpenStatusParams.TYPE);
            GftAccountOpenStatusParams l_gftAccountOpenStatusParams =
                TestDBUtility.getGftAccountOpenStatusRow();
            l_gftAccountOpenStatusParams.setAccountCode("1111111");
            l_gftAccountOpenStatusParams.setInstitutionCode("0D");
            l_gftAccountOpenStatusParams.setBranchCode("624");
            l_gftAccountOpenStatusParams.setOrderRequestNumber("1");
            l_gftAccountOpenStatusParams.setResultCodeSoap(null);
            l_gftAccountOpenStatusParams.setSendRcvDiv("1");
            l_gftAccountOpenStatusParams.setFxSystemCode("07");
            TestDBUtility.insertWithDel(l_gftAccountOpenStatusParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1001);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams =
                TestDBUtility.getInstitutionPreferencesRow();
            l_institutionPreferencesParams.setInstitutionId(33);
            l_institutionPreferencesParams.setName("gft.accountopen.fxsystemcode");
            l_institutionPreferencesParams.setValue("01");
            TestDBUtility.insertWithDel(l_institutionPreferencesParams);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(CompFxConditionParams.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("624");
            l_compFxConditionParams.setFxSystemCode("07");
            
            TestDBUtility.insertWithDel(l_compFxConditionParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSendAndReceiveValueSame", new Class[]
                { WEB3FXGftResultNoticeTelegramUnit.class },
                Boolean.TRUE);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSet", new Class[]
                { WEB3FXGftResultNoticeTelegramUnit.class },
                Boolean.TRUE);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramLengthPropSame", new Class[]
                { WEB3FXGftResultNoticeTelegramUnit.class },
                Boolean.TRUE);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123456));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());

            TestDBUtility.deleteAll(FxAccountParams.TYPE);
            TestDBUtility.deleteAll(FxAccountCodeParams.TYPE);
            TestDBUtility.deleteAll(AccOpenDivRow.TYPE);
            l_impl.submitAccountOpen(l_request);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitAccountOpen_Case002()
    {
        final String STR_METHOD_NAME = "testSubmitAccountOpen_Case002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FXAccOpenCompleteRequest l_request =
                new WEB3FXAccOpenCompleteRequest();
            WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit =
                new WEB3FXGftResultNoticeTelegramUnit();
            l_fxGftResultNoticeTelegramUnit.dirSendTime = "20081010";
            l_fxGftResultNoticeTelegramUnit.gftOperationDiv = "1";
            l_fxGftResultNoticeTelegramUnit.fxAccountCode = "111111";
            l_fxGftResultNoticeTelegramUnit.fxMailAddress = "1@@163.com";
            l_fxGftResultNoticeTelegramUnit.fxFirstLoginId = "1";
            l_fxGftResultNoticeTelegramUnit.fxFirstPassword = "111111";
            l_fxGftResultNoticeTelegramUnit.groupName = "1";
            l_fxGftResultNoticeTelegramUnit.cashinoutAmt = "1";
            l_fxGftResultNoticeTelegramUnit.wolfSession = "1";
            l_fxGftResultNoticeTelegramUnit.wolfAid = "1";
            l_fxGftResultNoticeTelegramUnit.regetServiceId = "1";
            l_fxGftResultNoticeTelegramUnit.wolfSsid = "1";
            l_fxGftResultNoticeTelegramUnit.institutionCode = "0D";
            l_fxGftResultNoticeTelegramUnit.branchCode = "624";
            l_fxGftResultNoticeTelegramUnit.accountCode = "1111111";
            l_fxGftResultNoticeTelegramUnit.requestNumber = "1";
            l_fxGftResultNoticeTelegramUnit.resultCode = "00000000";
            l_fxGftResultNoticeTelegramUnit.gftSendTime = "1";
            l_fxGftResultNoticeTelegramUnit.gftAcc1 = "1";
            l_fxGftResultNoticeTelegramUnit.gftAcc2 = "1";
            l_fxGftResultNoticeTelegramUnit.fxLastName = "1";
            l_fxGftResultNoticeTelegramUnit.fxFirstName = "1";
            l_fxGftResultNoticeTelegramUnit.hashValue = "1";
            l_fxGftResultNoticeTelegramUnit.deliveryDate = "1";
            l_fxGftResultNoticeTelegramUnit.cashinoutAmt2 = "1";
            l_request.fxGftResultNoticeTelegramUnit = l_fxGftResultNoticeTelegramUnit;
            WEB3FXAccOpenServiceImpl l_impl = new WEB3FXAccOpenServiceImpl();

            TestDBUtility.deleteAll(TraderParams.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setLoginId(0);
            TestDBUtility.insertWithDel(l_traderParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123456);
            l_mainAccountParams.setBranchId(1001);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("1111111");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(GftAccountOpenStatusParams.TYPE);
            GftAccountOpenStatusParams l_gftAccountOpenStatusParams =
                TestDBUtility.getGftAccountOpenStatusRow();
            l_gftAccountOpenStatusParams.setAccountCode("1111111");
            l_gftAccountOpenStatusParams.setInstitutionCode("0D");
            l_gftAccountOpenStatusParams.setBranchCode("624");
            l_gftAccountOpenStatusParams.setOrderRequestNumber("1");
            l_gftAccountOpenStatusParams.setResultCodeSoap(null);
            l_gftAccountOpenStatusParams.setSendRcvDiv("1");
            l_gftAccountOpenStatusParams.setFxSystemCode("07");
            TestDBUtility.insertWithDel(l_gftAccountOpenStatusParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1001);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams =
                TestDBUtility.getInstitutionPreferencesRow();
            l_institutionPreferencesParams.setInstitutionId(33);
            l_institutionPreferencesParams.setName("gft.accountopen.fxsystemcode");
            l_institutionPreferencesParams.setValue("01");
            TestDBUtility.insertWithDel(l_institutionPreferencesParams);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(CompFxConditionParams.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("624");
            l_compFxConditionParams.setFxSystemCode("07");
            TestDBUtility.insertWithDel(l_compFxConditionParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSendAndReceiveValueSame", new Class[]
                { WEB3FXGftResultNoticeTelegramUnit.class },
                Boolean.TRUE);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSet", new Class[]
                { WEB3FXGftResultNoticeTelegramUnit.class },
                Boolean.TRUE);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramLengthPropSame", new Class[]
                { WEB3FXGftResultNoticeTelegramUnit.class },
                Boolean.TRUE);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123456));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());

            TestDBUtility.deleteAll(FxAccountParams.TYPE);
            TestDBUtility.deleteAll(FxAccountCodeParams.TYPE);
            TestDBUtility.deleteAll(AccOpenDivRow.TYPE);
            l_impl.submitAccountOpen(l_request);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitAccountOpen_Case003()
    {
        final String STR_METHOD_NAME = "testSubmitAccountOpen_Case003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FXAccOpenCompleteRequest l_request =
                new WEB3FXAccOpenCompleteRequest();
            WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit =
                new WEB3FXGftResultNoticeTelegramUnit();
            l_fxGftResultNoticeTelegramUnit.dirSendTime = "20081010";
            l_fxGftResultNoticeTelegramUnit.gftOperationDiv = "1";
            l_fxGftResultNoticeTelegramUnit.fxAccountCode = "111111";
            l_fxGftResultNoticeTelegramUnit.fxMailAddress = "1@@163.com";
            l_fxGftResultNoticeTelegramUnit.fxFirstLoginId = "1";
            l_fxGftResultNoticeTelegramUnit.fxFirstPassword = "111111";
            l_fxGftResultNoticeTelegramUnit.groupName = "1";
            l_fxGftResultNoticeTelegramUnit.cashinoutAmt = "1";
            l_fxGftResultNoticeTelegramUnit.wolfSession = "1";
            l_fxGftResultNoticeTelegramUnit.wolfAid = "1";
            l_fxGftResultNoticeTelegramUnit.regetServiceId = "1";
            l_fxGftResultNoticeTelegramUnit.wolfSsid = "1";
            l_fxGftResultNoticeTelegramUnit.institutionCode = "0D";
            l_fxGftResultNoticeTelegramUnit.branchCode = "624";
            l_fxGftResultNoticeTelegramUnit.accountCode = "1111111";
            l_fxGftResultNoticeTelegramUnit.requestNumber = "1";
            l_fxGftResultNoticeTelegramUnit.resultCode = "00000000";
            l_fxGftResultNoticeTelegramUnit.gftSendTime = "1";
            l_fxGftResultNoticeTelegramUnit.gftAcc1 = "1";
            l_fxGftResultNoticeTelegramUnit.gftAcc2 = "1";
            l_fxGftResultNoticeTelegramUnit.fxLastName = "1";
            l_fxGftResultNoticeTelegramUnit.fxFirstName = "1";
            l_fxGftResultNoticeTelegramUnit.hashValue = "1";
            l_fxGftResultNoticeTelegramUnit.deliveryDate = "1";
            l_fxGftResultNoticeTelegramUnit.cashinoutAmt2 = "1";
            l_request.fxGftResultNoticeTelegramUnit = l_fxGftResultNoticeTelegramUnit;
            WEB3FXAccOpenServiceImpl l_impl = new WEB3FXAccOpenServiceImpl();

            TestDBUtility.deleteAll(TraderParams.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setLoginId(0);
            TestDBUtility.insertWithDel(l_traderParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123456);
            l_mainAccountParams.setBranchId(1001);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("1111111");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(GftAccountOpenStatusParams.TYPE);
            GftAccountOpenStatusParams l_gftAccountOpenStatusParams =
                TestDBUtility.getGftAccountOpenStatusRow();
            l_gftAccountOpenStatusParams.setAccountCode("1111111");
            l_gftAccountOpenStatusParams.setInstitutionCode("0D");
            l_gftAccountOpenStatusParams.setBranchCode("624");
            l_gftAccountOpenStatusParams.setOrderRequestNumber("1");
            l_gftAccountOpenStatusParams.setResultCodeSoap(null);
            l_gftAccountOpenStatusParams.setSendRcvDiv("1");
            l_gftAccountOpenStatusParams.setFxSystemCode("07");
            TestDBUtility.insertWithDel(l_gftAccountOpenStatusParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1001);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams =
                TestDBUtility.getInstitutionPreferencesRow();
            l_institutionPreferencesParams.setInstitutionId(33);
            l_institutionPreferencesParams.setName("fx.cfd.accountopendiv.update");
            l_institutionPreferencesParams.setValue("01");
            l_institutionPreferencesParams.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_institutionPreferencesParams);

            l_institutionPreferencesParams.setInstitutionId(33);
            l_institutionPreferencesParams.setName("gft.accountopen.fxsystemcode");
            l_institutionPreferencesParams.setValue("01");
            TestDBUtility.insertWithDel(l_institutionPreferencesParams);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(CompFxConditionParams.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("624");
            l_compFxConditionParams.setFxSystemCode("07");
            TestDBUtility.insertWithDel(l_compFxConditionParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSendAndReceiveValueSame", new Class[]
                { WEB3FXGftResultNoticeTelegramUnit.class },
                Boolean.TRUE);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSet", new Class[]
                { WEB3FXGftResultNoticeTelegramUnit.class },
                Boolean.TRUE);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramLengthPropSame", new Class[]
                { WEB3FXGftResultNoticeTelegramUnit.class },
                Boolean.TRUE);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123456));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());

            TestDBUtility.deleteAll(FxAccountParams.TYPE);
            TestDBUtility.deleteAll(FxAccountCodeParams.TYPE);
            TestDBUtility.deleteAll(AccOpenDivRow.TYPE);
            l_impl.submitAccountOpen(l_request);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitAccountOpen_Case004()
    {
        final String STR_METHOD_NAME = "testSubmitAccountOpen_Case004()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FXAccOpenCompleteRequest l_request =
                new WEB3FXAccOpenCompleteRequest();
            WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit =
                new WEB3FXGftResultNoticeTelegramUnit();
            l_fxGftResultNoticeTelegramUnit.dirSendTime = "20081010";
            l_fxGftResultNoticeTelegramUnit.gftOperationDiv = "1";
            l_fxGftResultNoticeTelegramUnit.fxAccountCode = "111111";
            l_fxGftResultNoticeTelegramUnit.fxMailAddress = "1@@163.com";
            l_fxGftResultNoticeTelegramUnit.fxFirstLoginId = "1";
            l_fxGftResultNoticeTelegramUnit.fxFirstPassword = "111111";
            l_fxGftResultNoticeTelegramUnit.groupName = "1";
            l_fxGftResultNoticeTelegramUnit.cashinoutAmt = "1";
            l_fxGftResultNoticeTelegramUnit.wolfSession = "1";
            l_fxGftResultNoticeTelegramUnit.wolfAid = "1";
            l_fxGftResultNoticeTelegramUnit.regetServiceId = "1";
            l_fxGftResultNoticeTelegramUnit.wolfSsid = "1";
            l_fxGftResultNoticeTelegramUnit.institutionCode = "0D";
            l_fxGftResultNoticeTelegramUnit.branchCode = "624";
            l_fxGftResultNoticeTelegramUnit.accountCode = "1111111";
            l_fxGftResultNoticeTelegramUnit.requestNumber = "1";
            l_fxGftResultNoticeTelegramUnit.resultCode = "00000000";
            l_fxGftResultNoticeTelegramUnit.gftSendTime = "1";
            l_fxGftResultNoticeTelegramUnit.gftAcc1 = "1";
            l_fxGftResultNoticeTelegramUnit.gftAcc2 = "1";
            l_fxGftResultNoticeTelegramUnit.fxLastName = "1";
            l_fxGftResultNoticeTelegramUnit.fxFirstName = "1";
            l_fxGftResultNoticeTelegramUnit.hashValue = "1";
            l_fxGftResultNoticeTelegramUnit.deliveryDate = "1";
            l_fxGftResultNoticeTelegramUnit.cashinoutAmt2 = "1";
            l_request.fxGftResultNoticeTelegramUnit = l_fxGftResultNoticeTelegramUnit;
            WEB3FXAccOpenServiceImpl l_impl = new WEB3FXAccOpenServiceImpl();

            TestDBUtility.deleteAll(TraderParams.TYPE);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123456);
            l_mainAccountParams.setBranchId(1001);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("1111111");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(GftAccountOpenStatusParams.TYPE);
            GftAccountOpenStatusParams l_gftAccountOpenStatusParams =
                TestDBUtility.getGftAccountOpenStatusRow();
            l_gftAccountOpenStatusParams.setAccountCode("1111111");
            l_gftAccountOpenStatusParams.setInstitutionCode("0D");
            l_gftAccountOpenStatusParams.setBranchCode("624");
            l_gftAccountOpenStatusParams.setOrderRequestNumber("1");
            l_gftAccountOpenStatusParams.setResultCodeSoap(null);
            l_gftAccountOpenStatusParams.setSendRcvDiv("1");
            l_gftAccountOpenStatusParams.setFxSystemCode("07");
            TestDBUtility.insertWithDel(l_gftAccountOpenStatusParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1001);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams =
                TestDBUtility.getInstitutionPreferencesRow();
            l_institutionPreferencesParams.setInstitutionId(33);
            l_institutionPreferencesParams.setName("fx.cfd.accountopendiv.update");
            l_institutionPreferencesParams.setValue("01");
            l_institutionPreferencesParams.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_institutionPreferencesParams);

            l_institutionPreferencesParams.setInstitutionId(33);
            l_institutionPreferencesParams.setName("gft.accountopen.fxsystemcode");
            l_institutionPreferencesParams.setValue("01");
            TestDBUtility.insertWithDel(l_institutionPreferencesParams);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(CompFxConditionParams.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("624");
            l_compFxConditionParams.setFxSystemCode("07");
            TestDBUtility.insertWithDel(l_compFxConditionParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSendAndReceiveValueSame", new Class[]
                { WEB3FXGftResultNoticeTelegramUnit.class },
                Boolean.TRUE);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSet", new Class[]
                { WEB3FXGftResultNoticeTelegramUnit.class },
                Boolean.TRUE);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramLengthPropSame", new Class[]
                { WEB3FXGftResultNoticeTelegramUnit.class },
                Boolean.TRUE);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123456));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());

            TestDBUtility.deleteAll(FxAccountParams.TYPE);
            TestDBUtility.deleteAll(FxAccountCodeParams.TYPE);
            TestDBUtility.deleteAll(AccOpenDivRow.TYPE);
            l_impl.submitAccountOpen(l_request);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitAccountOpen_Case005()
    {
        final String STR_METHOD_NAME = "testSubmitAccountOpen_Case005()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FXAccOpenCompleteRequest l_request =
                new WEB3FXAccOpenCompleteRequest();
            WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit =
                new WEB3FXGftResultNoticeTelegramUnit();
            l_fxGftResultNoticeTelegramUnit.dirSendTime = "20081010";
            l_fxGftResultNoticeTelegramUnit.gftOperationDiv = "1";
            l_fxGftResultNoticeTelegramUnit.fxAccountCode = "111111";
            l_fxGftResultNoticeTelegramUnit.fxMailAddress = "1@@163.com";
            l_fxGftResultNoticeTelegramUnit.fxFirstLoginId = "1";
            l_fxGftResultNoticeTelegramUnit.fxFirstPassword = "111111";
            l_fxGftResultNoticeTelegramUnit.groupName = "1";
            l_fxGftResultNoticeTelegramUnit.cashinoutAmt = "1";
            l_fxGftResultNoticeTelegramUnit.wolfSession = "1";
            l_fxGftResultNoticeTelegramUnit.wolfAid = "1";
            l_fxGftResultNoticeTelegramUnit.regetServiceId = "1";
            l_fxGftResultNoticeTelegramUnit.wolfSsid = "1";
            l_fxGftResultNoticeTelegramUnit.institutionCode = "0D";
            l_fxGftResultNoticeTelegramUnit.branchCode = "624";
            l_fxGftResultNoticeTelegramUnit.accountCode = "1111111";
            l_fxGftResultNoticeTelegramUnit.requestNumber = "1";
            l_fxGftResultNoticeTelegramUnit.resultCode = "00000000";
            l_fxGftResultNoticeTelegramUnit.gftSendTime = "1";
            l_fxGftResultNoticeTelegramUnit.gftAcc1 = "1";
            l_fxGftResultNoticeTelegramUnit.gftAcc2 = "1";
            l_fxGftResultNoticeTelegramUnit.fxLastName = "1";
            l_fxGftResultNoticeTelegramUnit.fxFirstName = "1";
            l_fxGftResultNoticeTelegramUnit.hashValue = "1";
            l_fxGftResultNoticeTelegramUnit.deliveryDate = "1";
            l_fxGftResultNoticeTelegramUnit.cashinoutAmt2 = "1";
            l_request.fxGftResultNoticeTelegramUnit = l_fxGftResultNoticeTelegramUnit;
            WEB3FXAccOpenServiceImpl l_impl = new WEB3FXAccOpenServiceImpl();

            TestDBUtility.deleteAll(TraderParams.TYPE);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123456);
            l_mainAccountParams.setBranchId(1001);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("1111111");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(GftAccountOpenStatusParams.TYPE);
            GftAccountOpenStatusParams l_gftAccountOpenStatusParams =
                TestDBUtility.getGftAccountOpenStatusRow();
            l_gftAccountOpenStatusParams.setAccountCode("1111111");
            l_gftAccountOpenStatusParams.setInstitutionCode("0D");
            l_gftAccountOpenStatusParams.setBranchCode("624");
            l_gftAccountOpenStatusParams.setOrderRequestNumber("1");
            l_gftAccountOpenStatusParams.setResultCodeSoap(null);
            l_gftAccountOpenStatusParams.setSendRcvDiv("1");
            l_gftAccountOpenStatusParams.setFxSystemCode("07");
            TestDBUtility.insertWithDel(l_gftAccountOpenStatusParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1001);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
//            InstitutionPreferencesParams l_institutionPreferencesParams =
//                TestDBUtility.getInstitutionPreferencesRow();
//            l_institutionPreferencesParams.setInstitutionId(33);
//            l_institutionPreferencesParams.setName("gft.accountopen.fxsystemcode");
//            TestDBUtility.insertWithDel(l_institutionPreferencesParams);

            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            BranchPreferencesParams l_branchPreferencesParams =
                TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(1001);
            l_branchPreferencesParams.setName("fx.accountopendiv.update");
            l_branchPreferencesParams.setNameSerialNo(1);
            l_branchPreferencesParams.setValue("5");
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(CompFxConditionParams.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("624");
            l_compFxConditionParams.setFxSystemCode("07");
            l_compFxConditionParams.setFxSystemDiv(null);
            TestDBUtility.insertWithDel(l_compFxConditionParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSendAndReceiveValueSame", new Class[]
                { WEB3FXGftResultNoticeTelegramUnit.class },
                Boolean.TRUE);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSet", new Class[]
                { WEB3FXGftResultNoticeTelegramUnit.class },
                Boolean.TRUE);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramLengthPropSame", new Class[]
                { WEB3FXGftResultNoticeTelegramUnit.class },
                Boolean.TRUE);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123456));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());

            TestDBUtility.deleteAll(FxAccountParams.TYPE);
            TestDBUtility.deleteAll(FxAccountCodeParams.TYPE);
            TestDBUtility.deleteAll(AccOpenDivRow.TYPE);
            l_impl.submitAccountOpen(l_request);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitAccountOpen_Case006()
    {
        final String STR_METHOD_NAME = "testSubmitAccountOpen_Case006()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FXAccOpenCompleteRequest l_request =
                new WEB3FXAccOpenCompleteRequest();
            WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit =
                new WEB3FXGftResultNoticeTelegramUnit();
            l_fxGftResultNoticeTelegramUnit.dirSendTime = "20081010";
            l_fxGftResultNoticeTelegramUnit.gftOperationDiv = "1";
            l_fxGftResultNoticeTelegramUnit.fxAccountCode = "111111";
            l_fxGftResultNoticeTelegramUnit.fxMailAddress = "1@@163.com";
            l_fxGftResultNoticeTelegramUnit.fxFirstLoginId = "1";
            l_fxGftResultNoticeTelegramUnit.fxFirstPassword = "111111";
            l_fxGftResultNoticeTelegramUnit.groupName = "1";
            l_fxGftResultNoticeTelegramUnit.cashinoutAmt = "1";
            l_fxGftResultNoticeTelegramUnit.wolfSession = "1";
            l_fxGftResultNoticeTelegramUnit.wolfAid = "1";
            l_fxGftResultNoticeTelegramUnit.regetServiceId = "1";
            l_fxGftResultNoticeTelegramUnit.wolfSsid = "1";
            l_fxGftResultNoticeTelegramUnit.institutionCode = "0D";
            l_fxGftResultNoticeTelegramUnit.branchCode = "624";
            l_fxGftResultNoticeTelegramUnit.accountCode = "1111111";
            l_fxGftResultNoticeTelegramUnit.requestNumber = "1";
            l_fxGftResultNoticeTelegramUnit.resultCode = "00000000";
            l_fxGftResultNoticeTelegramUnit.gftSendTime = "1";
            l_fxGftResultNoticeTelegramUnit.gftAcc1 = "1";
            l_fxGftResultNoticeTelegramUnit.gftAcc2 = "1";
            l_fxGftResultNoticeTelegramUnit.fxLastName = "1";
            l_fxGftResultNoticeTelegramUnit.fxFirstName = "1";
            l_fxGftResultNoticeTelegramUnit.hashValue = "1";
            l_fxGftResultNoticeTelegramUnit.deliveryDate = "1";
            l_fxGftResultNoticeTelegramUnit.cashinoutAmt2 = "1";
            l_request.fxGftResultNoticeTelegramUnit = l_fxGftResultNoticeTelegramUnit;
            WEB3FXAccOpenServiceImpl l_impl = new WEB3FXAccOpenServiceImpl();

            TestDBUtility.deleteAll(TraderParams.TYPE);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123456);
            l_mainAccountParams.setBranchId(1001);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("1111111");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(GftAccountOpenStatusParams.TYPE);
            GftAccountOpenStatusParams l_gftAccountOpenStatusParams =
                TestDBUtility.getGftAccountOpenStatusRow();
            l_gftAccountOpenStatusParams.setAccountCode("1111111");
            l_gftAccountOpenStatusParams.setInstitutionCode("0D");
            l_gftAccountOpenStatusParams.setBranchCode("624");
            l_gftAccountOpenStatusParams.setOrderRequestNumber("1");
            l_gftAccountOpenStatusParams.setResultCodeSoap(null);
            l_gftAccountOpenStatusParams.setSendRcvDiv("1");
            l_gftAccountOpenStatusParams.setFxSystemCode("07");
            TestDBUtility.insertWithDel(l_gftAccountOpenStatusParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1001);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
//            InstitutionPreferencesParams l_institutionPreferencesParams =
//                TestDBUtility.getInstitutionPreferencesRow();
//            l_institutionPreferencesParams.setInstitutionId(33);
//            l_institutionPreferencesParams.setName("gft.accountopen.fxsystemcode");
//            TestDBUtility.insertWithDel(l_institutionPreferencesParams);

            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            BranchPreferencesParams l_branchPreferencesParams =
                TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(1001);
            l_branchPreferencesParams.setName("fx.accountopendiv.update");
            l_branchPreferencesParams.setNameSerialNo(1);
            l_branchPreferencesParams.setValue("0");
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(CompFxConditionParams.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("624");
            l_compFxConditionParams.setFxSystemCode("07");
            l_compFxConditionParams.setFxSystemDiv(null);
            TestDBUtility.insertWithDel(l_compFxConditionParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSendAndReceiveValueSame", new Class[]
                { WEB3FXGftResultNoticeTelegramUnit.class },
                Boolean.TRUE);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSet", new Class[]
                { WEB3FXGftResultNoticeTelegramUnit.class },
                Boolean.TRUE);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramLengthPropSame", new Class[]
                { WEB3FXGftResultNoticeTelegramUnit.class },
                Boolean.TRUE);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123456));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());

            TestDBUtility.deleteAll(FxAccountParams.TYPE);
            TestDBUtility.deleteAll(FxAccountCodeParams.TYPE);
            TestDBUtility.deleteAll(AccOpenDivRow.TYPE);
            l_impl.submitAccountOpen(l_request);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitAccountOpen_Case007()
    {
        final String STR_METHOD_NAME = "testSubmitAccountOpen_Case007()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FXAccOpenCompleteRequest l_request =
                new WEB3FXAccOpenCompleteRequest();
            WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit =
                new WEB3FXGftResultNoticeTelegramUnit();
            l_fxGftResultNoticeTelegramUnit.dirSendTime = "20081010";
            l_fxGftResultNoticeTelegramUnit.gftOperationDiv = "1";
            l_fxGftResultNoticeTelegramUnit.fxAccountCode = "111111";
            l_fxGftResultNoticeTelegramUnit.fxMailAddress = "1@@163.com";
            l_fxGftResultNoticeTelegramUnit.fxFirstLoginId = "1";
            l_fxGftResultNoticeTelegramUnit.fxFirstPassword = "111111";
            l_fxGftResultNoticeTelegramUnit.groupName = "1";
            l_fxGftResultNoticeTelegramUnit.cashinoutAmt = "1";
            l_fxGftResultNoticeTelegramUnit.wolfSession = "1";
            l_fxGftResultNoticeTelegramUnit.wolfAid = "1";
            l_fxGftResultNoticeTelegramUnit.regetServiceId = "1";
            l_fxGftResultNoticeTelegramUnit.wolfSsid = "1";
            l_fxGftResultNoticeTelegramUnit.institutionCode = "0D";
            l_fxGftResultNoticeTelegramUnit.branchCode = "624";
            l_fxGftResultNoticeTelegramUnit.accountCode = "1111111";
            l_fxGftResultNoticeTelegramUnit.requestNumber = "1";
            l_fxGftResultNoticeTelegramUnit.resultCode = "00000000";
            l_fxGftResultNoticeTelegramUnit.gftSendTime = "1";
            l_fxGftResultNoticeTelegramUnit.gftAcc1 = "1";
            l_fxGftResultNoticeTelegramUnit.gftAcc2 = "1";
            l_fxGftResultNoticeTelegramUnit.fxLastName = "1";
            l_fxGftResultNoticeTelegramUnit.fxFirstName = "1";
            l_fxGftResultNoticeTelegramUnit.hashValue = "1";
            l_fxGftResultNoticeTelegramUnit.deliveryDate = "1";
            l_fxGftResultNoticeTelegramUnit.cashinoutAmt2 = "1";
            l_request.fxGftResultNoticeTelegramUnit = l_fxGftResultNoticeTelegramUnit;
            WEB3FXAccOpenServiceImpl l_impl = new WEB3FXAccOpenServiceImpl();

            TestDBUtility.deleteAll(TraderParams.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setLoginId(0);
            TestDBUtility.insertWithDel(l_traderParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123456);
            l_mainAccountParams.setBranchId(1001);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("1111111");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(GftAccountOpenStatusParams.TYPE);
            GftAccountOpenStatusParams l_gftAccountOpenStatusParams =
                TestDBUtility.getGftAccountOpenStatusRow();
            l_gftAccountOpenStatusParams.setAccountCode("1111111");
            l_gftAccountOpenStatusParams.setInstitutionCode("0D");
            l_gftAccountOpenStatusParams.setBranchCode("624");
            l_gftAccountOpenStatusParams.setOrderRequestNumber("1");
            l_gftAccountOpenStatusParams.setResultCodeSoap(null);
            l_gftAccountOpenStatusParams.setSendRcvDiv("1");
            l_gftAccountOpenStatusParams.setFxSystemCode("07");
            TestDBUtility.insertWithDel(l_gftAccountOpenStatusParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1001);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
//            InstitutionPreferencesParams l_institutionPreferencesParams =
//                TestDBUtility.getInstitutionPreferencesRow();
//            l_institutionPreferencesParams.setInstitutionId(33);
//            l_institutionPreferencesParams.setName("gft.accountopen.fxsystemcode");
//            TestDBUtility.insertWithDel(l_institutionPreferencesParams);

            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            BranchPreferencesParams l_branchPreferencesParams =
                TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(1001);
            l_branchPreferencesParams.setName("fx.accountopendiv.update");
            l_branchPreferencesParams.setNameSerialNo(1);
            l_branchPreferencesParams.setValue("5");
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(CompFxConditionParams.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("624");
            l_compFxConditionParams.setFxSystemCode("07");
            l_compFxConditionParams.setFxSystemDiv(null);
            TestDBUtility.insertWithDel(l_compFxConditionParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSendAndReceiveValueSame", new Class[]
                { WEB3FXGftResultNoticeTelegramUnit.class },
                Boolean.TRUE);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSet", new Class[]
                { WEB3FXGftResultNoticeTelegramUnit.class },
                Boolean.TRUE);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramLengthPropSame", new Class[]
                { WEB3FXGftResultNoticeTelegramUnit.class },
                Boolean.TRUE);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123456));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());

            TestDBUtility.deleteAll(FxAccountParams.TYPE);
            TestDBUtility.deleteAll(FxAccountCodeParams.TYPE);
            TestDBUtility.deleteAll(AccOpenDivRow.TYPE);
            l_impl.submitAccountOpen(l_request);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitAccountOpen_Case008()
    {
        final String STR_METHOD_NAME = "testSubmitAccountOpen_Case008()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FXAccOpenCompleteRequest l_request =
                new WEB3FXAccOpenCompleteRequest();
            WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit =
                new WEB3FXGftResultNoticeTelegramUnit();
            l_fxGftResultNoticeTelegramUnit.dirSendTime = "20081010";
            l_fxGftResultNoticeTelegramUnit.gftOperationDiv = "1";
            l_fxGftResultNoticeTelegramUnit.fxAccountCode = "111111";
            l_fxGftResultNoticeTelegramUnit.fxMailAddress = "1@@163.com";
            l_fxGftResultNoticeTelegramUnit.fxFirstLoginId = "1";
            l_fxGftResultNoticeTelegramUnit.fxFirstPassword = "111111";
            l_fxGftResultNoticeTelegramUnit.groupName = "1";
            l_fxGftResultNoticeTelegramUnit.cashinoutAmt = "1";
            l_fxGftResultNoticeTelegramUnit.wolfSession = "1";
            l_fxGftResultNoticeTelegramUnit.wolfAid = "1";
            l_fxGftResultNoticeTelegramUnit.regetServiceId = "1";
            l_fxGftResultNoticeTelegramUnit.wolfSsid = "1";
            l_fxGftResultNoticeTelegramUnit.institutionCode = "0D";
            l_fxGftResultNoticeTelegramUnit.branchCode = "624";
            l_fxGftResultNoticeTelegramUnit.accountCode = "1111111";
            l_fxGftResultNoticeTelegramUnit.requestNumber = "1";
            l_fxGftResultNoticeTelegramUnit.resultCode = "00000000";
            l_fxGftResultNoticeTelegramUnit.gftSendTime = "1";
            l_fxGftResultNoticeTelegramUnit.gftAcc1 = "1";
            l_fxGftResultNoticeTelegramUnit.gftAcc2 = "1";
            l_fxGftResultNoticeTelegramUnit.fxLastName = "1";
            l_fxGftResultNoticeTelegramUnit.fxFirstName = "1";
            l_fxGftResultNoticeTelegramUnit.hashValue = "1";
            l_fxGftResultNoticeTelegramUnit.deliveryDate = "1";
            l_fxGftResultNoticeTelegramUnit.cashinoutAmt2 = "1";
            l_request.fxGftResultNoticeTelegramUnit = l_fxGftResultNoticeTelegramUnit;
            WEB3FXAccOpenServiceImpl l_impl = new WEB3FXAccOpenServiceImpl();

            TestDBUtility.deleteAll(TraderParams.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setLoginId(0);
            TestDBUtility.insertWithDel(l_traderParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123456);
            l_mainAccountParams.setBranchId(1001);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("1111111");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(GftAccountOpenStatusParams.TYPE);
            GftAccountOpenStatusParams l_gftAccountOpenStatusParams =
                TestDBUtility.getGftAccountOpenStatusRow();
            l_gftAccountOpenStatusParams.setAccountCode("1111111");
            l_gftAccountOpenStatusParams.setInstitutionCode("0D");
            l_gftAccountOpenStatusParams.setBranchCode("624");
            l_gftAccountOpenStatusParams.setOrderRequestNumber("1");
            l_gftAccountOpenStatusParams.setResultCodeSoap(null);
            l_gftAccountOpenStatusParams.setSendRcvDiv("1");
            l_gftAccountOpenStatusParams.setFxSystemCode("07");
            TestDBUtility.insertWithDel(l_gftAccountOpenStatusParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1001);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
//            InstitutionPreferencesParams l_institutionPreferencesParams =
//                TestDBUtility.getInstitutionPreferencesRow();
//            l_institutionPreferencesParams.setInstitutionId(33);
//            l_institutionPreferencesParams.setName("gft.accountopen.fxsystemcode");
//            TestDBUtility.insertWithDel(l_institutionPreferencesParams);

            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            BranchPreferencesParams l_branchPreferencesParams =
                TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(1001);
            l_branchPreferencesParams.setName("fx.accountopendiv.update");
            l_branchPreferencesParams.setNameSerialNo(1);
            l_branchPreferencesParams.setValue("0");
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(CompFxConditionParams.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("624");
            l_compFxConditionParams.setFxSystemCode("07");
            l_compFxConditionParams.setFxSystemDiv(null);
            TestDBUtility.insertWithDel(l_compFxConditionParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSendAndReceiveValueSame", new Class[]
                { WEB3FXGftResultNoticeTelegramUnit.class },
                Boolean.TRUE);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSet", new Class[]
                { WEB3FXGftResultNoticeTelegramUnit.class },
                Boolean.TRUE);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramLengthPropSame", new Class[]
                { WEB3FXGftResultNoticeTelegramUnit.class },
                Boolean.TRUE);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123456));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());

            TestDBUtility.deleteAll(FxAccountParams.TYPE);
            TestDBUtility.deleteAll(FxAccountCodeParams.TYPE);
            TestDBUtility.deleteAll(AccOpenDivRow.TYPE);
            l_impl.submitAccountOpen(l_request);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitAccountOpen_C0009()
    {
        final String STR_METHOD_NAME = "testSubmitAccountOpen_C0009()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FXAccOpenCompleteRequest l_request =
                new WEB3FXAccOpenCompleteRequest();
            WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit =
                new WEB3FXGftResultNoticeTelegramUnit();
            l_fxGftResultNoticeTelegramUnit.dirSendTime = "20081010";
            l_fxGftResultNoticeTelegramUnit.gftOperationDiv = "1";
            l_fxGftResultNoticeTelegramUnit.fxAccountCode = "111111";
            l_fxGftResultNoticeTelegramUnit.fxMailAddress = "1@@163.com";
            l_fxGftResultNoticeTelegramUnit.fxFirstLoginId = "1";
            l_fxGftResultNoticeTelegramUnit.fxFirstPassword = "111111";
            l_fxGftResultNoticeTelegramUnit.groupName = "1";
            l_fxGftResultNoticeTelegramUnit.cashinoutAmt = "1";
            l_fxGftResultNoticeTelegramUnit.wolfSession = "1";
            l_fxGftResultNoticeTelegramUnit.wolfAid = "1";
            l_fxGftResultNoticeTelegramUnit.regetServiceId = "1";
            l_fxGftResultNoticeTelegramUnit.wolfSsid = "1";
            l_fxGftResultNoticeTelegramUnit.institutionCode = "0D";
            l_fxGftResultNoticeTelegramUnit.branchCode = "624";
            l_fxGftResultNoticeTelegramUnit.accountCode = "1111111";
            l_fxGftResultNoticeTelegramUnit.requestNumber = "1";
            l_fxGftResultNoticeTelegramUnit.resultCode = "00000000";
            l_fxGftResultNoticeTelegramUnit.gftSendTime = "1";
            l_fxGftResultNoticeTelegramUnit.gftAcc1 = "1";
            l_fxGftResultNoticeTelegramUnit.gftAcc2 = "1";
            l_fxGftResultNoticeTelegramUnit.fxLastName = "1";
            l_fxGftResultNoticeTelegramUnit.fxFirstName = "1";
            l_fxGftResultNoticeTelegramUnit.hashValue = "1";
            l_fxGftResultNoticeTelegramUnit.deliveryDate = "1";
            l_fxGftResultNoticeTelegramUnit.cashinoutAmt2 = "1";
            l_request.fxGftResultNoticeTelegramUnit = l_fxGftResultNoticeTelegramUnit;
            WEB3FXAccOpenServiceImpl l_impl = new WEB3FXAccOpenServiceImpl();

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(123456);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(TraderParams.TYPE);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123456);
            l_mainAccountParams.setBranchId(1001);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("1111111");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(GftAccountOpenStatusParams.TYPE);
            GftAccountOpenStatusParams l_gftAccountOpenStatusParams =
                TestDBUtility.getGftAccountOpenStatusRow();
            l_gftAccountOpenStatusParams.setAccountCode("1111111");
            l_gftAccountOpenStatusParams.setInstitutionCode("0D");
            l_gftAccountOpenStatusParams.setBranchCode("624");
            l_gftAccountOpenStatusParams.setOrderRequestNumber("1");
            l_gftAccountOpenStatusParams.setResultCodeSoap(null);
            l_gftAccountOpenStatusParams.setSendRcvDiv("1");
            l_gftAccountOpenStatusParams.setFxSystemCode("07");
            TestDBUtility.insertWithDel(l_gftAccountOpenStatusParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1001);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams =
                TestDBUtility.getInstitutionPreferencesRow();
            l_institutionPreferencesParams.setInstitutionId(33);
            l_institutionPreferencesParams.setName("gft.accountopen.fxsystemcode");
            l_institutionPreferencesParams.setValue("01");
            TestDBUtility.insertWithDel(l_institutionPreferencesParams);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(CompFxConditionParams.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setFxSystemCode("07");
            TestDBUtility.insertWithDel(l_compFxConditionParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSendAndReceiveValueSame", new Class[]
                { WEB3FXGftResultNoticeTelegramUnit.class },
                Boolean.TRUE);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSet", new Class[]
                { WEB3FXGftResultNoticeTelegramUnit.class },
                Boolean.TRUE);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramLengthPropSame", new Class[]
                { WEB3FXGftResultNoticeTelegramUnit.class },
                Boolean.TRUE);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123456));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());

            TestDBUtility.deleteAll(FxAccountParams.TYPE);
            TestDBUtility.deleteAll(FxAccountCodeParams.TYPE);
            TestDBUtility.deleteAll(AccOpenDivRow.TYPE);
            l_impl.submitAccountOpen(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitAccountOpen_C0010()
    {
        final String STR_METHOD_NAME = "testSubmitAccountOpen_C0010()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FXAccOpenCompleteRequest l_request =
                new WEB3FXAccOpenCompleteRequest();
            WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit =
                new WEB3FXGftResultNoticeTelegramUnit();
            l_fxGftResultNoticeTelegramUnit.dirSendTime = "20081010";
            l_fxGftResultNoticeTelegramUnit.gftOperationDiv = "1";
            l_fxGftResultNoticeTelegramUnit.fxAccountCode = "111111";
            l_fxGftResultNoticeTelegramUnit.fxMailAddress = "1@@163.com";
            l_fxGftResultNoticeTelegramUnit.fxFirstLoginId = "1";
            l_fxGftResultNoticeTelegramUnit.fxFirstPassword = "111111";
            l_fxGftResultNoticeTelegramUnit.groupName = "1";
            l_fxGftResultNoticeTelegramUnit.cashinoutAmt = "1";
            l_fxGftResultNoticeTelegramUnit.wolfSession = "1";
            l_fxGftResultNoticeTelegramUnit.wolfAid = "1";
            l_fxGftResultNoticeTelegramUnit.regetServiceId = "1";
            l_fxGftResultNoticeTelegramUnit.wolfSsid = "1";
            l_fxGftResultNoticeTelegramUnit.institutionCode = "0D";
            l_fxGftResultNoticeTelegramUnit.branchCode = "624";
            l_fxGftResultNoticeTelegramUnit.accountCode = "1111111";
            l_fxGftResultNoticeTelegramUnit.requestNumber = "1";
            l_fxGftResultNoticeTelegramUnit.resultCode = "00000000";
            l_fxGftResultNoticeTelegramUnit.gftSendTime = "1";
            l_fxGftResultNoticeTelegramUnit.gftAcc1 = "1";
            l_fxGftResultNoticeTelegramUnit.gftAcc2 = "1";
            l_fxGftResultNoticeTelegramUnit.fxLastName = "1";
            l_fxGftResultNoticeTelegramUnit.fxFirstName = "1";
            l_fxGftResultNoticeTelegramUnit.hashValue = "1";
            l_fxGftResultNoticeTelegramUnit.deliveryDate = "1";
            l_fxGftResultNoticeTelegramUnit.cashinoutAmt2 = "1";
            l_request.fxGftResultNoticeTelegramUnit = l_fxGftResultNoticeTelegramUnit;
            WEB3FXAccOpenServiceImpl l_impl = new WEB3FXAccOpenServiceImpl();

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(123456);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(TraderParams.TYPE);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123456);
            l_mainAccountParams.setBranchId(1001);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("1111111");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(GftAccountOpenStatusParams.TYPE);
            GftAccountOpenStatusParams l_gftAccountOpenStatusParams =
                TestDBUtility.getGftAccountOpenStatusRow();
            l_gftAccountOpenStatusParams.setAccountCode("1111111");
            l_gftAccountOpenStatusParams.setInstitutionCode("0D");
            l_gftAccountOpenStatusParams.setBranchCode("381");
            l_gftAccountOpenStatusParams.setOrderRequestNumber("1");
            l_gftAccountOpenStatusParams.setResultCodeSoap(null);
            l_gftAccountOpenStatusParams.setSendRcvDiv("1");
            l_gftAccountOpenStatusParams.setFxSystemCode("07");
            TestDBUtility.insertWithDel(l_gftAccountOpenStatusParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1001);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams =
                TestDBUtility.getInstitutionPreferencesRow();
            l_institutionPreferencesParams.setInstitutionId(33);
            l_institutionPreferencesParams.setName("gft.accountopen.fxsystemcode");
            l_institutionPreferencesParams.setValue("01");
            TestDBUtility.insertWithDel(l_institutionPreferencesParams);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(CompFxConditionParams.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("624");
            l_compFxConditionParams.setFxSystemCode("07");
            l_compFxConditionParams.setSoapConnectDiv("0");
            TestDBUtility.insertWithDel(l_compFxConditionParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSendAndReceiveValueSame", new Class[]
                { WEB3FXGftResultNoticeTelegramUnit.class },
                Boolean.TRUE);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSet", new Class[]
                { WEB3FXGftResultNoticeTelegramUnit.class },
                Boolean.TRUE);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramLengthPropSame", new Class[]
                { WEB3FXGftResultNoticeTelegramUnit.class },
                Boolean.TRUE);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123456));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());

            TestDBUtility.deleteAll(FxAccountParams.TYPE);
            TestDBUtility.deleteAll(FxAccountCodeParams.TYPE);
            TestDBUtility.deleteAll(AccOpenDivRow.TYPE);
            l_impl.submitAccountOpen(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitAccountOpen_C0011()
    {
        final String STR_METHOD_NAME = "testSubmitAccountOpen_C0011()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FXAccOpenCompleteRequest l_request =
                new WEB3FXAccOpenCompleteRequest();
            WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit =
                new WEB3FXGftResultNoticeTelegramUnit();
            l_fxGftResultNoticeTelegramUnit.dirSendTime = "20081010";
            l_fxGftResultNoticeTelegramUnit.gftOperationDiv = "1";
            l_fxGftResultNoticeTelegramUnit.fxAccountCode = "111111";
            l_fxGftResultNoticeTelegramUnit.fxMailAddress = "1@@163.com";
            l_fxGftResultNoticeTelegramUnit.fxFirstLoginId = "1";
            l_fxGftResultNoticeTelegramUnit.fxFirstPassword = "111111";
            l_fxGftResultNoticeTelegramUnit.groupName = "1";
            l_fxGftResultNoticeTelegramUnit.cashinoutAmt = "1";
            l_fxGftResultNoticeTelegramUnit.wolfSession = "1";
            l_fxGftResultNoticeTelegramUnit.wolfAid = "1";
            l_fxGftResultNoticeTelegramUnit.regetServiceId = "1";
            l_fxGftResultNoticeTelegramUnit.wolfSsid = "1";
            l_fxGftResultNoticeTelegramUnit.institutionCode = "0D";
            l_fxGftResultNoticeTelegramUnit.branchCode = "624";
            l_fxGftResultNoticeTelegramUnit.accountCode = "1111111";
            l_fxGftResultNoticeTelegramUnit.requestNumber = "1";
            l_fxGftResultNoticeTelegramUnit.resultCode = "00000000";
            l_fxGftResultNoticeTelegramUnit.gftSendTime = "1";
            l_fxGftResultNoticeTelegramUnit.gftAcc1 = "1";
            l_fxGftResultNoticeTelegramUnit.gftAcc2 = "1";
            l_fxGftResultNoticeTelegramUnit.fxLastName = "1";
            l_fxGftResultNoticeTelegramUnit.fxFirstName = "1";
            l_fxGftResultNoticeTelegramUnit.hashValue = "1";
            l_fxGftResultNoticeTelegramUnit.deliveryDate = "1";
            l_fxGftResultNoticeTelegramUnit.cashinoutAmt2 = "1";
            l_request.fxGftResultNoticeTelegramUnit = l_fxGftResultNoticeTelegramUnit;
            WEB3FXAccOpenServiceImpl l_impl = new WEB3FXAccOpenServiceImpl();

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(123456);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(TraderParams.TYPE);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123456);
            l_mainAccountParams.setBranchId(1001);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("1111111");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(GftAccountOpenStatusParams.TYPE);
            GftAccountOpenStatusParams l_gftAccountOpenStatusParams =
                TestDBUtility.getGftAccountOpenStatusRow();
            l_gftAccountOpenStatusParams.setAccountCode("1111111");
            l_gftAccountOpenStatusParams.setInstitutionCode("0D");
            l_gftAccountOpenStatusParams.setBranchCode("624");
            l_gftAccountOpenStatusParams.setOrderRequestNumber("1");
            l_gftAccountOpenStatusParams.setResultCodeSoap(null);
            l_gftAccountOpenStatusParams.setSendRcvDiv("1");
            l_gftAccountOpenStatusParams.setFxSystemCode("07");
            TestDBUtility.insertWithDel(l_gftAccountOpenStatusParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1001);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams =
                TestDBUtility.getInstitutionPreferencesRow();
            l_institutionPreferencesParams.setInstitutionId(33);
            l_institutionPreferencesParams.setName("gft.accountopen.fxsystemcode");
            l_institutionPreferencesParams.setValue("01");
            TestDBUtility.insertWithDel(l_institutionPreferencesParams);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(CompFxConditionParams.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("624");
            l_compFxConditionParams.setFxSystemCode("07");
            l_compFxConditionParams.setSoapConnectDiv("1");
            l_compFxConditionParams.setAccType("01");
            l_compFxConditionParams.setAccOpenRealUpdate("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);

            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setLoginId(1001);
            TestDBUtility.insertWithDel(l_traderParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(1001));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSendAndReceiveValueSame", new Class[]
                { WEB3FXGftResultNoticeTelegramUnit.class },
                Boolean.TRUE);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSet", new Class[]
                { WEB3FXGftResultNoticeTelegramUnit.class },
                Boolean.FALSE);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramLengthPropSame", new Class[]
                { WEB3FXGftResultNoticeTelegramUnit.class },
                Boolean.TRUE);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123456));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());

            TestDBUtility.deleteAll(FxAccountParams.TYPE);
            TestDBUtility.deleteAll(FxAccountCodeParams.TYPE);
            TestDBUtility.deleteAll(AccOpenDivRow.TYPE);
            l_impl.submitAccountOpen(l_request);

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisAccOpenDivRows = l_queryProcessor.doFindAllQuery(AccOpenDivRow.TYPE);

            List l_lisMainAccountRows = l_queryProcessor.doFindAllQuery(MainAccountRow.TYPE);

            assertEquals(1, l_lisMainAccountRows.size());
            assertEquals(2, l_lisAccOpenDivRows.size());

            MainAccountRow l_mainAccountRow = (MainAccountRow)l_lisMainAccountRows.get(0);
            assertEquals("11123", l_mainAccountRow.getFxAccOpenDivLastUpdater());

            AccOpenDivRow l_accOpenDivRow1 = (AccOpenDivRow)l_lisAccOpenDivRows.get(0);
            assertEquals("1", l_accOpenDivRow1.getAccOpenDiv());
            assertEquals(123456, l_accOpenDivRow1.getAccountId());
            assertEquals("01", l_accOpenDivRow1.getAccType());
            assertEquals("11123", l_accOpenDivRow1.getLastUpdater());


            AccOpenDivRow l_accOpenDivRow2 = (AccOpenDivRow)l_lisAccOpenDivRows.get(1);
            assertEquals("1", l_accOpenDivRow2.getAccOpenDiv());
            assertEquals(123456, l_accOpenDivRow2.getAccountId());
            assertEquals("02", l_accOpenDivRow2.getAccType());
            assertEquals("11123", l_accOpenDivRow2.getLastUpdater());
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitAccountOpen_C0012()
    {
        final String STR_METHOD_NAME = "testSubmitAccountOpen_C0012()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FXAccOpenCompleteRequest l_request =
                new WEB3FXAccOpenCompleteRequest();
            WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit =
                new WEB3FXGftResultNoticeTelegramUnit();
            l_fxGftResultNoticeTelegramUnit.dirSendTime = "20081010";
            l_fxGftResultNoticeTelegramUnit.gftOperationDiv = "1";
            l_fxGftResultNoticeTelegramUnit.fxAccountCode = "111111";
            l_fxGftResultNoticeTelegramUnit.fxMailAddress = "1@@163.com";
            l_fxGftResultNoticeTelegramUnit.fxFirstLoginId = "1";
            l_fxGftResultNoticeTelegramUnit.fxFirstPassword = "111111";
            l_fxGftResultNoticeTelegramUnit.groupName = "1";
            l_fxGftResultNoticeTelegramUnit.cashinoutAmt = "1";
            l_fxGftResultNoticeTelegramUnit.wolfSession = "1";
            l_fxGftResultNoticeTelegramUnit.wolfAid = "1";
            l_fxGftResultNoticeTelegramUnit.regetServiceId = "1";
            l_fxGftResultNoticeTelegramUnit.wolfSsid = "1";
            l_fxGftResultNoticeTelegramUnit.institutionCode = "0D";
            l_fxGftResultNoticeTelegramUnit.branchCode = "624";
            l_fxGftResultNoticeTelegramUnit.accountCode = "1111111";
            l_fxGftResultNoticeTelegramUnit.requestNumber = "1";
            l_fxGftResultNoticeTelegramUnit.resultCode = "00000000";
            l_fxGftResultNoticeTelegramUnit.gftSendTime = "1";
            l_fxGftResultNoticeTelegramUnit.gftAcc1 = "1";
            l_fxGftResultNoticeTelegramUnit.gftAcc2 = "1";
            l_fxGftResultNoticeTelegramUnit.fxLastName = "1";
            l_fxGftResultNoticeTelegramUnit.fxFirstName = "1";
            l_fxGftResultNoticeTelegramUnit.hashValue = "1";
            l_fxGftResultNoticeTelegramUnit.deliveryDate = "1";
            l_fxGftResultNoticeTelegramUnit.cashinoutAmt2 = "1";
            l_request.fxGftResultNoticeTelegramUnit = l_fxGftResultNoticeTelegramUnit;
            WEB3FXAccOpenServiceImpl l_impl = new WEB3FXAccOpenServiceImpl();

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(123456);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(TraderParams.TYPE);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123456);
            l_mainAccountParams.setBranchId(1001);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("1111111");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(GftAccountOpenStatusParams.TYPE);
            GftAccountOpenStatusParams l_gftAccountOpenStatusParams =
                TestDBUtility.getGftAccountOpenStatusRow();
            l_gftAccountOpenStatusParams.setAccountCode("1111111");
            l_gftAccountOpenStatusParams.setInstitutionCode("0D");
            l_gftAccountOpenStatusParams.setBranchCode("624");
            l_gftAccountOpenStatusParams.setOrderRequestNumber("1");
            l_gftAccountOpenStatusParams.setResultCodeSoap(null);
            l_gftAccountOpenStatusParams.setSendRcvDiv("1");
            l_gftAccountOpenStatusParams.setFxSystemCode("07");
            TestDBUtility.insertWithDel(l_gftAccountOpenStatusParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1001);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(CompFxConditionParams.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("624");
            l_compFxConditionParams.setFxSystemCode("07");
            l_compFxConditionParams.setSoapConnectDiv("1");
            l_compFxConditionParams.setAccType("01");
            l_compFxConditionParams.setAccOpenRealUpdate("0");
            TestDBUtility.insertWithDel(l_compFxConditionParams);

            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setLoginId(1005);
            TestDBUtility.insertWithDel(l_traderParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(1001));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSendAndReceiveValueSame", new Class[]
                { WEB3FXGftResultNoticeTelegramUnit.class },
                Boolean.TRUE);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSet", new Class[]
                { WEB3FXGftResultNoticeTelegramUnit.class },
                Boolean.FALSE);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramLengthPropSame", new Class[]
                { WEB3FXGftResultNoticeTelegramUnit.class },
                Boolean.TRUE);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123456));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());

            TestDBUtility.deleteAll(FxAccountParams.TYPE);
            TestDBUtility.deleteAll(FxAccountCodeParams.TYPE);
            TestDBUtility.deleteAll(AccOpenDivRow.TYPE);
            l_impl.submitAccountOpen(l_request);

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisAccOpenDivRows = l_queryProcessor.doFindAllQuery(AccOpenDivRow.TYPE);

            List l_lisMainAccountRows = l_queryProcessor.doFindAllQuery(MainAccountRow.TYPE);

            assertEquals(1, l_lisMainAccountRows.size());
            assertEquals(1, l_lisAccOpenDivRows.size());

            MainAccountRow l_mainAccountRow = (MainAccountRow)l_lisMainAccountRows.get(0);
            assertEquals("1111111", l_mainAccountRow.getFxAccOpenDivLastUpdater());

            AccOpenDivRow l_accOpenDivRow1 = (AccOpenDivRow)l_lisAccOpenDivRows.get(0);
            assertEquals("0", l_accOpenDivRow1.getAccOpenDiv());
            assertEquals(123456, l_accOpenDivRow1.getAccountId());
            assertEquals("01", l_accOpenDivRow1.getAccType());
            assertEquals("01", l_accOpenDivRow1.getAccType());
            assertEquals("1111111", l_accOpenDivRow1.getLastUpdater());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitAccountOpen_C0013()
    {
        final String STR_METHOD_NAME = "testSubmitAccountOpen_C0013()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FXAccOpenCompleteRequest l_request =
                new WEB3FXAccOpenCompleteRequest();
            WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit =
                new WEB3FXGftResultNoticeTelegramUnit();
            l_fxGftResultNoticeTelegramUnit.dirSendTime = "20081010";
            l_fxGftResultNoticeTelegramUnit.gftOperationDiv = "1";
            l_fxGftResultNoticeTelegramUnit.fxAccountCode = "111111";
            l_fxGftResultNoticeTelegramUnit.fxMailAddress = "1@@163.com";
            l_fxGftResultNoticeTelegramUnit.fxFirstLoginId = "1";
            l_fxGftResultNoticeTelegramUnit.fxFirstPassword = "111111";
            l_fxGftResultNoticeTelegramUnit.groupName = "1";
            l_fxGftResultNoticeTelegramUnit.cashinoutAmt = "1";
            l_fxGftResultNoticeTelegramUnit.wolfSession = "1";
            l_fxGftResultNoticeTelegramUnit.wolfAid = "1";
            l_fxGftResultNoticeTelegramUnit.regetServiceId = "1";
            l_fxGftResultNoticeTelegramUnit.wolfSsid = "1";
            l_fxGftResultNoticeTelegramUnit.institutionCode = "0D";
            l_fxGftResultNoticeTelegramUnit.branchCode = "624";
            l_fxGftResultNoticeTelegramUnit.accountCode = "1111111";
            l_fxGftResultNoticeTelegramUnit.requestNumber = "1";
            l_fxGftResultNoticeTelegramUnit.resultCode = "00000000";
            l_fxGftResultNoticeTelegramUnit.gftSendTime = "1";
            l_fxGftResultNoticeTelegramUnit.gftAcc1 = "1";
            l_fxGftResultNoticeTelegramUnit.gftAcc2 = "1";
            l_fxGftResultNoticeTelegramUnit.fxLastName = "1";
            l_fxGftResultNoticeTelegramUnit.fxFirstName = "1";
            l_fxGftResultNoticeTelegramUnit.hashValue = "1";
            l_fxGftResultNoticeTelegramUnit.deliveryDate = "1";
            l_fxGftResultNoticeTelegramUnit.cashinoutAmt2 = "1";
            l_request.fxGftResultNoticeTelegramUnit = l_fxGftResultNoticeTelegramUnit;
            WEB3FXAccOpenServiceImpl l_impl = new WEB3FXAccOpenServiceImpl();

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(123456);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(TraderParams.TYPE);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123456);
            l_mainAccountParams.setBranchId(1001);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("1111111");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(GftAccountOpenStatusParams.TYPE);
            GftAccountOpenStatusParams l_gftAccountOpenStatusParams =
                TestDBUtility.getGftAccountOpenStatusRow();
            l_gftAccountOpenStatusParams.setAccountCode("1111111");
            l_gftAccountOpenStatusParams.setInstitutionCode("0D");
            l_gftAccountOpenStatusParams.setBranchCode("624");
            l_gftAccountOpenStatusParams.setOrderRequestNumber("1");
            l_gftAccountOpenStatusParams.setResultCodeSoap(null);
            l_gftAccountOpenStatusParams.setSendRcvDiv("1");
            l_gftAccountOpenStatusParams.setFxSystemCode("07");
            TestDBUtility.insertWithDel(l_gftAccountOpenStatusParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1001);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams =
                TestDBUtility.getInstitutionPreferencesRow();
            l_institutionPreferencesParams.setInstitutionId(33);
            l_institutionPreferencesParams.setName("gft.accountopen.fxsystemcode");
            l_institutionPreferencesParams.setValue("01");
            TestDBUtility.insertWithDel(l_institutionPreferencesParams);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(CompFxConditionParams.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("624");
            l_compFxConditionParams.setFxSystemCode("07");
            l_compFxConditionParams.setSoapConnectDiv("0");
            l_compFxConditionParams.setAccType("00");
            l_compFxConditionParams.setAccOpenRealUpdate("0");
            TestDBUtility.insertWithDel(l_compFxConditionParams);

            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setLoginId(1001);
            TestDBUtility.insertWithDel(l_traderParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(1001));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSendAndReceiveValueSame", new Class[]
                { WEB3FXGftResultNoticeTelegramUnit.class },
                Boolean.TRUE);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSet", new Class[]
                { WEB3FXGftResultNoticeTelegramUnit.class },
                Boolean.FALSE);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramLengthPropSame", new Class[]
                { WEB3FXGftResultNoticeTelegramUnit.class },
                Boolean.FALSE);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123456));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());

            TestDBUtility.deleteAll(FxAccountParams.TYPE);
            TestDBUtility.deleteAll(FxAccountCodeParams.TYPE);
            TestDBUtility.deleteAll(AccOpenDivRow.TYPE);
            l_impl.submitAccountOpen(l_request);

        }
        catch (WEB3BaseException l_ex)
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

    public void testSubmitAccountOpen_C0014()
    {
        final String STR_METHOD_NAME = "testSubmitAccountOpen_C0014()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FXAccOpenCompleteRequest l_request =
                new WEB3FXAccOpenCompleteRequest();
            WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit =
                new WEB3FXGftResultNoticeTelegramUnit();
            l_fxGftResultNoticeTelegramUnit.dirSendTime = "20081010";
            l_fxGftResultNoticeTelegramUnit.gftOperationDiv = "1";
            l_fxGftResultNoticeTelegramUnit.fxAccountCode = "111111";
            l_fxGftResultNoticeTelegramUnit.fxMailAddress = "1@@163.com";
            l_fxGftResultNoticeTelegramUnit.fxFirstLoginId = "1";
            l_fxGftResultNoticeTelegramUnit.fxFirstPassword = "111111";
            l_fxGftResultNoticeTelegramUnit.groupName = "1";
            l_fxGftResultNoticeTelegramUnit.cashinoutAmt = "1";
            l_fxGftResultNoticeTelegramUnit.wolfSession = "1";
            l_fxGftResultNoticeTelegramUnit.wolfAid = "1";
            l_fxGftResultNoticeTelegramUnit.regetServiceId = "1";
            l_fxGftResultNoticeTelegramUnit.wolfSsid = "1";
            l_fxGftResultNoticeTelegramUnit.institutionCode = "0D";
            l_fxGftResultNoticeTelegramUnit.branchCode = "624";
            l_fxGftResultNoticeTelegramUnit.accountCode = "1111111";
            l_fxGftResultNoticeTelegramUnit.requestNumber = "1";
            l_fxGftResultNoticeTelegramUnit.resultCode = "00000000";
            l_fxGftResultNoticeTelegramUnit.gftSendTime = "1";
            l_fxGftResultNoticeTelegramUnit.gftAcc1 = "1";
            l_fxGftResultNoticeTelegramUnit.gftAcc2 = "1";
            l_fxGftResultNoticeTelegramUnit.fxLastName = "1";
            l_fxGftResultNoticeTelegramUnit.fxFirstName = "1";
            l_fxGftResultNoticeTelegramUnit.hashValue = "1";
            l_fxGftResultNoticeTelegramUnit.deliveryDate = "1";
            l_fxGftResultNoticeTelegramUnit.cashinoutAmt2 = "1";
            l_request.fxGftResultNoticeTelegramUnit = l_fxGftResultNoticeTelegramUnit;
            WEB3FXAccOpenServiceImpl l_impl = new WEB3FXAccOpenServiceImpl();

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(123456);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(TraderParams.TYPE);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123456);
            l_mainAccountParams.setBranchId(1001);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("1111111");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(GftAccountOpenStatusParams.TYPE);
            GftAccountOpenStatusParams l_gftAccountOpenStatusParams =
                TestDBUtility.getGftAccountOpenStatusRow();
            l_gftAccountOpenStatusParams.setAccountCode("1111111");
            l_gftAccountOpenStatusParams.setInstitutionCode("0D");
            l_gftAccountOpenStatusParams.setBranchCode("624");
            l_gftAccountOpenStatusParams.setOrderRequestNumber("1");
            l_gftAccountOpenStatusParams.setResultCodeSoap(null);
            l_gftAccountOpenStatusParams.setSendRcvDiv("1");
            l_gftAccountOpenStatusParams.setFxSystemCode("07");
            TestDBUtility.insertWithDel(l_gftAccountOpenStatusParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1001);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams =
                TestDBUtility.getInstitutionPreferencesRow();
            l_institutionPreferencesParams.setInstitutionId(33);
            l_institutionPreferencesParams.setName("gft.accountopen.fxsystemcode");
            l_institutionPreferencesParams.setValue("01");
            TestDBUtility.insertWithDel(l_institutionPreferencesParams);

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(CompFxConditionParams.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("624");
            l_compFxConditionParams.setFxSystemCode("07");
            l_compFxConditionParams.setSoapConnectDiv("0");
            l_compFxConditionParams.setAccType("00");
            l_compFxConditionParams.setAccOpenRealUpdate("0");
            TestDBUtility.insertWithDel(l_compFxConditionParams);

            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setLoginId(1001);
            TestDBUtility.insertWithDel(l_traderParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(1001));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSendAndReceiveValueSame", new Class[]
                { WEB3FXGftResultNoticeTelegramUnit.class },
                Boolean.TRUE);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramSet", new Class[]
                { WEB3FXGftResultNoticeTelegramUnit.class },
                Boolean.FALSE);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXTelegramProcessServiceImpl",
                "isGFTTelegramLengthPropSame", new Class[]
                { WEB3FXGftResultNoticeTelegramUnit.class },
                Boolean.TRUE);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123456));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());

            TestDBUtility.deleteAll(FxAccountParams.TYPE);
            TestDBUtility.deleteAll(FxAccountCodeParams.TYPE);
            TestDBUtility.deleteAll(AccOpenDivRow.TYPE);
            l_impl.submitAccountOpen(l_request);
        }
        catch (WEB3BaseException l_ex)
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

    public void testSubmitAccountOpenSoap1()
    {
        final String STR_METHOD_NAME = "testSubmitAccountOpenSoap1";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            WEB3FXAccOpenCompleteSoapRequest l_request =
                new WEB3FXAccOpenCompleteSoapRequest();
            l_request.fxSystemCode = "01";

            WEB3FXAccOpenServiceImpl l_fxTransFromFXServiceImpl =
                new WEB3FXAccOpenServiceImpl();
    
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123456));

            TestDBUtility.deleteAll(CompFxConditionParams.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setFxSystemCode("01");
            TestDBUtility.insertWithDel(l_compFxConditionParams);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123456);
            l_mainAccountParams.setBranchId(1001);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setAccountCode("jiddk");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1001);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(SoapConnectPrefRpcParams.TYPE);
            l_fxTransFromFXServiceImpl.submitAccountOpenSoap(l_request);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01309,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    
    /*
     * Test method for 'webbroker3.aio.service.delegate.stdimpls.WEB3FXAccOpenServiceImpl.submitAccountOpenSoap(WEB3FXAccOpenCompleteSoapRequest)'
     */
    public void testSubmitAccountOpenSoap() {

        final String STR_METHOD_NAME = "testSubmitAccountOpenSoap()";
        log.entering(STR_METHOD_NAME);
        
        WEB3FXAccOpenServiceImplForTest1 l_impl = new WEB3FXAccOpenServiceImplForTest1();
        WEB3FXAccOpenCompleteSoapRequest l_request = new WEB3FXAccOpenCompleteSoapRequest();
        

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512203L));

            SoapConnectPrefRpcParams l_soapConnectPrefRpcParams = new SoapConnectPrefRpcParams();
            TestDBUtility.deleteAll(SoapConnectPrefRpcParams.TYPE);
            l_soapConnectPrefRpcParams.setBranchId(222);
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
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("624");
            l_branchParams.setBranchId(222);
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);
    
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setAccountCode("321");
            l_mainAccountParams.setBranchId(222);
            l_mainAccountParams.setInstitutionId(123);
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
    
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setAccountCode("jiddk");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setInstitutionCode("0D");
            //口座ＩＤ]
            l_subAccountParams.setAccountId(333812512203L);
            //補助口座ＩＤ
            l_subAccountParams.setSubAccountId(33381251220301L);
            //補助口座タイプ
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            l_subAccountParams.setBranchId(222);
            l_subAccountParams.setInstitutionId(123);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            l_subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            Services.unregisterService(WEB3FXDataControlService.class);
            Services.unregisterService(WEB3FXTelegramProcessService.class);
            
            
            Services.registerService(
                    WEB3FXDataControlService.class,
                    new WEB3FXDataControlServiceImplForTest());
            
            Services.registerService(
                    WEB3FXTelegramProcessService.class,
                    new WEB3FXTelegramProcessServiceImplForTest());

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(123);
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);
            l_request.fxSystemCode = "01";

            TestDBUtility.deleteAll(CompFxConditionParams.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("624");
            l_compFxConditionParams.setFxSystemCode("01");
            TestDBUtility.insertWithDel(l_compFxConditionParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXDataControlServiceImpl",
                "getHashValue", new Class[]
                { String.class, String.class, String.class },
                "1");

            String l_sendSyncRequestResponse = "";
//            ResultInfoBase l_resultInfoBase = new ResultInfoBase();
//            l_resultInfoBase.setMajorStatusCode(1);
//            l_resultInfoBase.setMinorStatusCode(1);
//            l_sendSyncRequestResponse.setSendSyncRequestResult(l_resultInfoBase);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3FXDataControlServiceImpl",
                    "sendSoapMessage",
                    new Class[]{
                        WEB3FXGftAskingTelegramUnit.class,
                        SoapConnectPrefRpcParams.class, String.class, String.class },
                    l_sendSyncRequestResponse);

            WEB3FXAccOpenCompleteSoapResponse l_response =
                l_impl.submitAccountOpenSoap(l_request);
            
            assertEquals(l_response.fxLoginId,"bbb");
            assertEquals(l_response.fxAccInformationList[0].fxAccountCode,"123");
            assertEquals(l_response.fxAccInformationList[0].fxCourseDiv,"1");
            fail();
            
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03161, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
        
        
    }
    
    public void testValidateAccountOpen_Case001()
    {
        final String STR_METHOD_NAME = "testValidateAccountOpen_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_processors = Processors.getDefaultProcessor();
            WEB3FXAccOpenConfirmRequest l_request =
                new WEB3FXAccOpenConfirmRequest();
            l_request.fxMailAddress = "jiddk@@126.com";
            l_request.fxMailAddressCnf = "jiddk@@126.com";
            l_request.fxPassword = "123456";
            l_request.fxPasswordCnf = "123456";
            l_request.agreementDiv = "0";
            l_request.fxSystemCode = "07";
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getAccountId",
                new Class[] {},
                new Long(123456));
            
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
            l_processors.doInsertQuery(l_mainAccountParams);

            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setAccountCode("jiddk");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
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
                
            l_subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
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
                "webbroker3.aio.WEB3AioOrderManager", 
                "validateOrder",
                new Class[] {SubAccount.class},
                null);
            
            //CompFxConditionRow
            l_processors.doDeleteAllQuery(CompFxConditionRow.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("624");
            l_compFxConditionParams.setFxSystemCode("07");
            l_processors.doInsertQuery(l_compFxConditionParams);
            
            //FxAccountRow
            l_processors.doDeleteAllQuery(FxAccountRow.TYPE);
            
            l_impl.validateAccountOpen(l_request);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testStartAccountOpen_Case001()
    {
        final String STR_METHOD_NAME = "testStartAccountOpen_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_processors = Processors.getDefaultProcessor();
            WEB3FXAccOpenAskingRequest l_request =
                new WEB3FXAccOpenAskingRequest();
            l_request.wolfSession = "session";
            l_request.wolfAid = "123";
            l_request.regetServiceId = "456";
            l_request.fxMailAddress = "jiddk@@126.com";
            l_request.fxPassword = "123456";
            l_request.agreementDiv = "0";
            l_request.fxSystemCode = "07";
            l_request.wolfSsid = "789";
            
            WEB3FXTradeAgreementUnit[] fxTradeAgreementList =
                new WEB3FXTradeAgreementUnit[2];
            fxTradeAgreementList[0] = new WEB3FXTradeAgreementUnit();
            fxTradeAgreementList[0].questionAnswer = "1";
            fxTradeAgreementList[0].questionNumber = "101";
            fxTradeAgreementList[1] = new WEB3FXTradeAgreementUnit();
            fxTradeAgreementList[1].questionAnswer = "0";
            fxTradeAgreementList[1].questionNumber = "102";
            
            l_request.fxTradeAgreementList = fxTradeAgreementList;

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getAccountId",
                new Class[] {},
                new Long(123456));
            
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
            l_processors.doInsertQuery(l_mainAccountParams);

            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setAccountCode("jiddk");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(123);
            TestDBUtility.insertWithDel(l_branchParams);

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
            
            l_subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setFxSystemCode("07");
            l_compFxConditionParams.setQuestionCheckDiv("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);

            //BranchPreferencesRow
            l_processors.doDeleteAllQuery(InstitutionPreferencesParams.TYPE);
            
            l_impl.startAccountOpen(l_request);
            fail();
            
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals( WEB3ErrorCatalog.BUSINESS_ERROR_01796, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testStartAccountOpen_Case002()
    {
        final String STR_METHOD_NAME = "testStartAccountOpen_Case002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_processors = Processors.getDefaultProcessor();
            WEB3FXAccOpenAskingRequest l_request =
                new WEB3FXAccOpenAskingRequest();
            l_request.wolfSession = "session";
            l_request.wolfAid = "123";
            l_request.regetServiceId = "456";
            l_request.fxMailAddress = "jiddk@@126.com";
            l_request.fxPassword = "123456";
            l_request.agreementDiv = "0";
            l_request.fxSystemCode = "07";
            l_request.wolfSsid = "789";
            
            WEB3FXTradeAgreementUnit[] fxTradeAgreementList =
                new WEB3FXTradeAgreementUnit[1];
            fxTradeAgreementList[0] = new WEB3FXTradeAgreementUnit();
            fxTradeAgreementList[0].questionAnswer = "1";
            fxTradeAgreementList[0].questionNumber = "101";
            
            l_request.fxTradeAgreementList = fxTradeAgreementList;

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getAccountId",
                new Class[] {},
                new Long(123456));
            
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
            l_processors.doInsertQuery(l_mainAccountParams);

            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setAccountCode("jiddk");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
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
            
            l_subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            //BranchPreferencesRow
            l_processors.doDeleteAllQuery(InstitutionPreferencesParams.TYPE);
            InstitutionPreferencesParams l_branchPreferencesParams =
                this.getInstitutionPreferencesParams();
            l_branchPreferencesParams.setInstitutionId(33);
            l_branchPreferencesParams.setName("fx.agreement.question.check");
            l_branchPreferencesParams.setNameSerialNo(1);
            l_processors.doInsertQuery(l_branchPreferencesParams);
            
            //CompFxConditionRow
            l_processors.doDeleteAllQuery(CompFxConditionRow.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setFxSystemCode("07");
            l_processors.doInsertQuery(l_compFxConditionParams);
            
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
                "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl",
                "getNewNumber",
                new Class[] {String.class, String.class, ProductTypeEnum.class },
                "111");     
            
            //TraderParams
            l_processors.doDeleteAllQuery(TraderParams.TYPE);
            TraderParams l_traderParams =
                TestDBUtility.getTraderRow();
            l_traderParams.setTraderId(123456);
            l_processors.doInsertQuery(l_traderParams);
            
            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            OrderValidationResult l_result = new OrderValidationResult(processingResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeOrderValidator", "validateTradingPassword",
                new Class[] {Trader.class, SubAccount.class, String.class },
                l_result);
            //GftAccountOpenStatusParams
            l_processors.doDeleteAllQuery(GftAccountOpenStatusParams.TYPE);

            //QuestionAnswerParams
            l_processors.doDeleteAllQuery(QuestionAnswerParams.TYPE);
            
            l_impl.startAccountOpen(l_request);
            List l_lisResult1 =
                l_processors.doFindAllQuery(GftAccountOpenStatusParams.TYPE);
            assertEquals(1, l_lisResult1.size());
            assertEquals("07", ((GftAccountOpenStatusRow)l_lisResult1.get(0)).getFxSystemCode());
            
            List l_lisResult2 = l_processors.doFindAllQuery(QuestionAnswerParams.TYPE);
            assertEquals(1, l_lisResult2.size());
            assertEquals("07", ((QuestionAnswerRow)l_lisResult2.get(0)).getQuestionDiv());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testStartAccountOpen_Case003()
    {
        final String STR_METHOD_NAME = "testStartAccountOpen_Case003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_processors = Processors.getDefaultProcessor();
            WEB3FXAccOpenAskingRequest l_request =
                new WEB3FXAccOpenAskingRequest();
            l_request.wolfSession = "session";
            l_request.wolfAid = "123";
            l_request.regetServiceId = "456";
            l_request.fxMailAddress = "jiddk@@126.com";
            l_request.fxPassword = "123456";
            l_request.agreementDiv = "0";
            l_request.fxSystemCode = "07";
            l_request.wolfSsid = "789";
            
            WEB3FXTradeAgreementUnit[] fxTradeAgreementList =
                new WEB3FXTradeAgreementUnit[1];
            fxTradeAgreementList[0] = new WEB3FXTradeAgreementUnit();
            fxTradeAgreementList[0].questionAnswer = "1";
            fxTradeAgreementList[0].questionNumber = "101";
            
            l_request.fxTradeAgreementList = fxTradeAgreementList;

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getAccountId",
                new Class[] {},
                new Long(123456));
            
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
            l_mainAccountParams.setAddressLine1("東京都");
            l_mainAccountParams.setAddressLine2("江東区");
            l_mainAccountParams.setAddressLine3("深川５");
            l_mainAccountParams.setAccountType(MainAccountTypeEnum.CORPORATE_ACCOUNT);
            l_processors.doInsertQuery(l_mainAccountParams);

            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setAccountCode("jiddk");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
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
            
            l_subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(123);
            TestDBUtility.insertWithDel(l_branchParams);

            //BranchPreferencesRow
            l_processors.doDeleteAllQuery(InstitutionPreferencesParams.TYPE);
            InstitutionPreferencesParams l_branchPreferencesParams =
                this.getInstitutionPreferencesParams();
            l_branchPreferencesParams.setInstitutionId(33);
            l_branchPreferencesParams.setName("fx.agreement.question.check");
            l_branchPreferencesParams.setNameSerialNo(1);
            l_processors.doInsertQuery(l_branchPreferencesParams);
            
            //CompFxConditionRow
            l_processors.doDeleteAllQuery(CompFxConditionRow.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setFxSystemCode("07");
            l_compFxConditionParams.setOnlineAccOpen("1");
            l_processors.doInsertQuery(l_compFxConditionParams);
            
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
                "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl",
                "getNewNumber",
                new Class[] {String.class, String.class, ProductTypeEnum.class },
                "111");     
            
            //TraderParams
            l_processors.doDeleteAllQuery(TraderParams.TYPE);
            TraderParams l_traderParams =
                TestDBUtility.getTraderRow();
            l_traderParams.setTraderId(123456);
            l_processors.doInsertQuery(l_traderParams);
            
            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            OrderValidationResult l_result = new OrderValidationResult(processingResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeOrderValidator", "validateTradingPassword",
                new Class[] {Trader.class, SubAccount.class, String.class },
                l_result);
            //GftAccountOpenStatusParams
            TestDBUtility.deleteAll(GftAccountOpenStatusParams.TYPE);

            //QuestionAnswerParams
            TestDBUtility.deleteAll(QuestionAnswerParams.TYPE);

            TestDBUtility.deleteAll(FxAccountParams.TYPE);
            FxAccountParams l_fxAccountParams =
                TestDBUtility.getFxAccountRow();
            l_fxAccountParams.setAccountCode("2512246");
            l_fxAccountParams.setBranchCode("624");
            l_fxAccountParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_fxAccountParams);

            WEB3FXAccOpenAskingResponse l_response =
                l_impl.startAccountOpen(l_request);

            assertEquals("01", l_response.fxGftAskingTelegramUnit.gftOperationDiv);
            assertEquals("東京都", l_response.fxGftAskingTelegramUnit.address1);
            assertEquals("江東区", l_response.fxGftAskingTelegramUnit.address2);
            assertEquals("深川５", l_response.fxGftAskingTelegramUnit.address3);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testStartAccountOpen_Case004()
    {
        final String STR_METHOD_NAME = "testStartAccountOpen_Case004()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_processors = Processors.getDefaultProcessor();
            WEB3FXAccOpenAskingRequest l_request =
                new WEB3FXAccOpenAskingRequest();
            l_request.wolfSession = "session";
            l_request.wolfAid = "123";
            l_request.regetServiceId = "456";
            l_request.fxMailAddress = "jiddk@@126.com";
            l_request.fxPassword = "123456";
            l_request.agreementDiv = "0";
            l_request.fxSystemCode = "07";
            l_request.wolfSsid = "789";
            
            WEB3FXTradeAgreementUnit[] fxTradeAgreementList =
                new WEB3FXTradeAgreementUnit[1];
            fxTradeAgreementList[0] = new WEB3FXTradeAgreementUnit();
            fxTradeAgreementList[0].questionAnswer = "1";
            fxTradeAgreementList[0].questionNumber = "101";
            
            l_request.fxTradeAgreementList = fxTradeAgreementList;

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getAccountId",
                new Class[] {},
                new Long(123456));
            
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
            l_mainAccountParams.setAddressLine1(" 東京都");
            l_mainAccountParams.setAddressLine2("　@江東区");
            l_mainAccountParams.setAddressLine3("　@ 深川５");
            l_mainAccountParams.setAccountType(MainAccountTypeEnum.CORPORATE_ACCOUNT);
            l_processors.doInsertQuery(l_mainAccountParams);

            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setAccountCode("jiddk");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
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
            
            l_subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(123);
            TestDBUtility.insertWithDel(l_branchParams);

            //BranchPreferencesRow
            l_processors.doDeleteAllQuery(InstitutionPreferencesParams.TYPE);
            InstitutionPreferencesParams l_branchPreferencesParams =
                this.getInstitutionPreferencesParams();
            l_branchPreferencesParams.setInstitutionId(33);
            l_branchPreferencesParams.setName("fx.agreement.question.check");
            l_branchPreferencesParams.setNameSerialNo(1);
            l_processors.doInsertQuery(l_branchPreferencesParams);
            
            //CompFxConditionRow
            l_processors.doDeleteAllQuery(CompFxConditionRow.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setFxSystemCode("07");
            l_compFxConditionParams.setOnlineAccOpen("1");
            l_processors.doInsertQuery(l_compFxConditionParams);
            
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
                "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl",
                "getNewNumber",
                new Class[] {String.class, String.class, ProductTypeEnum.class },
                "111");     
            
            //TraderParams
            l_processors.doDeleteAllQuery(TraderParams.TYPE);
            TraderParams l_traderParams =
                TestDBUtility.getTraderRow();
            l_traderParams.setTraderId(123456);
            l_processors.doInsertQuery(l_traderParams);
            
            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            OrderValidationResult l_result = new OrderValidationResult(processingResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeOrderValidator", "validateTradingPassword",
                new Class[] {Trader.class, SubAccount.class, String.class },
                l_result);
            //GftAccountOpenStatusParams
            TestDBUtility.deleteAll(GftAccountOpenStatusParams.TYPE);

            //QuestionAnswerParams
            TestDBUtility.deleteAll(QuestionAnswerParams.TYPE);

            TestDBUtility.deleteAll(FxAccountParams.TYPE);
            FxAccountParams l_fxAccountParams =
                TestDBUtility.getFxAccountRow();
            l_fxAccountParams.setAccountCode("2512246");
            l_fxAccountParams.setBranchCode("624");
            l_fxAccountParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_fxAccountParams);

            WEB3FXAccOpenAskingResponse l_response =
                l_impl.startAccountOpen(l_request);

            assertEquals("01", l_response.fxGftAskingTelegramUnit.gftOperationDiv);
            assertEquals("東京都", l_response.fxGftAskingTelegramUnit.address1);
            assertEquals("江東区", l_response.fxGftAskingTelegramUnit.address2);
            assertEquals("深川５", l_response.fxGftAskingTelegramUnit.address3);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testStartAccountOpen_Case005()
    {
        final String STR_METHOD_NAME = "testStartAccountOpen_Case005()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_processors = Processors.getDefaultProcessor();
            WEB3FXAccOpenAskingRequest l_request =
                new WEB3FXAccOpenAskingRequest();
            l_request.wolfSession = "session";
            l_request.wolfAid = "123";
            l_request.regetServiceId = "456";
            l_request.fxMailAddress = "jiddk@@126.com";
            l_request.fxPassword = "123456";
            l_request.agreementDiv = "0";
            l_request.fxSystemCode = "07";
            l_request.wolfSsid = "789";
            
            WEB3FXTradeAgreementUnit[] fxTradeAgreementList =
                new WEB3FXTradeAgreementUnit[1];
            fxTradeAgreementList[0] = new WEB3FXTradeAgreementUnit();
            fxTradeAgreementList[0].questionAnswer = "1";
            fxTradeAgreementList[0].questionNumber = "101";
            
            l_request.fxTradeAgreementList = fxTradeAgreementList;

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getAccountId",
                new Class[] {},
                new Long(123456));
            
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
            l_mainAccountParams.setAddressLine1("東京都 ");
            l_mainAccountParams.setAddressLine2("江東区　@");
            l_mainAccountParams.setAddressLine3("深川５　@");
            l_mainAccountParams.setAccountType(MainAccountTypeEnum.CORPORATE_ACCOUNT);
            l_processors.doInsertQuery(l_mainAccountParams);

            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setAccountCode("jiddk");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
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
            
            l_subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(123);
            TestDBUtility.insertWithDel(l_branchParams);

            //BranchPreferencesRow
            l_processors.doDeleteAllQuery(InstitutionPreferencesParams.TYPE);
            InstitutionPreferencesParams l_branchPreferencesParams =
                this.getInstitutionPreferencesParams();
            l_branchPreferencesParams.setInstitutionId(33);
            l_branchPreferencesParams.setName("fx.agreement.question.check");
            l_branchPreferencesParams.setNameSerialNo(1);
            l_processors.doInsertQuery(l_branchPreferencesParams);
            
            //CompFxConditionRow
            l_processors.doDeleteAllQuery(CompFxConditionRow.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setFxSystemCode("07");
            l_compFxConditionParams.setOnlineAccOpen("1");
            l_processors.doInsertQuery(l_compFxConditionParams);
            
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
                "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl",
                "getNewNumber",
                new Class[] {String.class, String.class, ProductTypeEnum.class },
                "111");     
            
            //TraderParams
            l_processors.doDeleteAllQuery(TraderParams.TYPE);
            TraderParams l_traderParams =
                TestDBUtility.getTraderRow();
            l_traderParams.setTraderId(123456);
            l_processors.doInsertQuery(l_traderParams);
            
            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            OrderValidationResult l_result = new OrderValidationResult(processingResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeOrderValidator", "validateTradingPassword",
                new Class[] {Trader.class, SubAccount.class, String.class },
                l_result);
            //GftAccountOpenStatusParams
            TestDBUtility.deleteAll(GftAccountOpenStatusParams.TYPE);

            //QuestionAnswerParams
            TestDBUtility.deleteAll(QuestionAnswerParams.TYPE);

            TestDBUtility.deleteAll(FxAccountParams.TYPE);
            FxAccountParams l_fxAccountParams =
                TestDBUtility.getFxAccountRow();
            l_fxAccountParams.setAccountCode("2512246");
            l_fxAccountParams.setBranchCode("624");
            l_fxAccountParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_fxAccountParams);

            WEB3FXAccOpenAskingResponse l_response =
                l_impl.startAccountOpen(l_request);

            assertEquals("01", l_response.fxGftAskingTelegramUnit.gftOperationDiv);
            assertEquals("東京都", l_response.fxGftAskingTelegramUnit.address1);
            assertEquals("江東区", l_response.fxGftAskingTelegramUnit.address2);
            assertEquals("深川５", l_response.fxGftAskingTelegramUnit.address3);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testStartAccountOpen_Case006()
    {
        final String STR_METHOD_NAME = "testStartAccountOpen_Case006()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_processors = Processors.getDefaultProcessor();
            WEB3FXAccOpenAskingRequest l_request =
                new WEB3FXAccOpenAskingRequest();
            l_request.wolfSession = "session";
            l_request.wolfAid = "123";
            l_request.regetServiceId = "456";
            l_request.fxMailAddress = "jiddk@@126.com";
            l_request.fxPassword = "123456";
            l_request.agreementDiv = "0";
            l_request.fxSystemCode = "07";
            l_request.wolfSsid = "789";
            
            WEB3FXTradeAgreementUnit[] fxTradeAgreementList =
                new WEB3FXTradeAgreementUnit[1];
            fxTradeAgreementList[0] = new WEB3FXTradeAgreementUnit();
            fxTradeAgreementList[0].questionAnswer = "1";
            fxTradeAgreementList[0].questionNumber = "101";
            
            l_request.fxTradeAgreementList = fxTradeAgreementList;

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getAccountId",
                new Class[] {},
                new Long(123456));
            
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
            l_mainAccountParams.setAddressLine1("　@ 　@　@ 　@ ");
            l_mainAccountParams.setAddressLine2("　@　@　@　@　@");
            l_mainAccountParams.setAddressLine3("         ");
            l_mainAccountParams.setAccountType(MainAccountTypeEnum.CORPORATE_ACCOUNT);
            l_processors.doInsertQuery(l_mainAccountParams);

            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setAccountCode("jiddk");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
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
            
            l_subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(123);
            TestDBUtility.insertWithDel(l_branchParams);

            //BranchPreferencesRow
            l_processors.doDeleteAllQuery(InstitutionPreferencesParams.TYPE);
            InstitutionPreferencesParams l_branchPreferencesParams =
                this.getInstitutionPreferencesParams();
            l_branchPreferencesParams.setInstitutionId(33);
            l_branchPreferencesParams.setName("fx.agreement.question.check");
            l_branchPreferencesParams.setNameSerialNo(1);
            l_processors.doInsertQuery(l_branchPreferencesParams);
            
            //CompFxConditionRow
            l_processors.doDeleteAllQuery(CompFxConditionRow.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setFxSystemCode("07");
            l_compFxConditionParams.setOnlineAccOpen("1");
            l_processors.doInsertQuery(l_compFxConditionParams);
            
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
                "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl",
                "getNewNumber",
                new Class[] {String.class, String.class, ProductTypeEnum.class },
                "111");     
            
            //TraderParams
            l_processors.doDeleteAllQuery(TraderParams.TYPE);
            TraderParams l_traderParams =
                TestDBUtility.getTraderRow();
            l_traderParams.setTraderId(123456);
            l_processors.doInsertQuery(l_traderParams);
            
            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            OrderValidationResult l_result = new OrderValidationResult(processingResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeOrderValidator", "validateTradingPassword",
                new Class[] {Trader.class, SubAccount.class, String.class },
                l_result);
            //GftAccountOpenStatusParams
            TestDBUtility.deleteAll(GftAccountOpenStatusParams.TYPE);

            //QuestionAnswerParams
            TestDBUtility.deleteAll(QuestionAnswerParams.TYPE);

            TestDBUtility.deleteAll(FxAccountParams.TYPE);
            FxAccountParams l_fxAccountParams =
                TestDBUtility.getFxAccountRow();
            l_fxAccountParams.setAccountCode("2512246");
            l_fxAccountParams.setBranchCode("624");
            l_fxAccountParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_fxAccountParams);

            WEB3FXAccOpenAskingResponse l_response =
                l_impl.startAccountOpen(l_request);

            assertEquals("01", l_response.fxGftAskingTelegramUnit.gftOperationDiv);
            assertNull(l_response.fxGftAskingTelegramUnit.address1);
            assertNull(l_response.fxGftAskingTelegramUnit.address2);
            assertNull(l_response.fxGftAskingTelegramUnit.address3);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testStartAccountOpen_C0004()
    {
        final String STR_METHOD_NAME = "testStartAccountOpen_C0004()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_processors = Processors.getDefaultProcessor();
            WEB3FXAccOpenAskingRequest l_request =
                new WEB3FXAccOpenAskingRequest();
            l_request.wolfSession = "session";
            l_request.wolfAid = "123";
            l_request.regetServiceId = "456";
            l_request.fxMailAddress = "jiddk@@126.com";
            l_request.fxPassword = "123456";
            l_request.agreementDiv = "0";
            l_request.fxSystemCode = "07";
            l_request.wolfSsid = "789";

            WEB3FXTradeAgreementUnit[] fxTradeAgreementList =
                new WEB3FXTradeAgreementUnit[1];
            fxTradeAgreementList[0] = new WEB3FXTradeAgreementUnit();
            fxTradeAgreementList[0].questionAnswer = "0";
            fxTradeAgreementList[0].questionNumber = "101";

            l_request.fxTradeAgreementList = fxTradeAgreementList;

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getAccountId",
                new Class[] {},
                new Long(123456));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "validateOrder",
                new Class[] {SubAccount.class},
                null);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123456);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setInstitutionId(33);
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setBranchId(123);
            l_mainAccountParams.setAccountType(MainAccountTypeEnum.CORPORATE_ACCOUNT);
            l_processors.doInsertQuery(l_mainAccountParams);

            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setAccountCode("jiddk");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(123456);
            l_subAccountParams.setInstitutionCode("0D");
            l_subAccountParams.setInstitutionId(33);
            l_subAccountParams.setBranchId(123);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            l_subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
            InstitutionPreferencesParams l_branchPreferencesParams =
                this.getInstitutionPreferencesParams();
            l_branchPreferencesParams.setInstitutionId(33);
            l_branchPreferencesParams.setName("fx.agreement.question.check");
            l_branchPreferencesParams.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("624");
            l_compFxConditionParams.setFxSystemCode("06");
            TestDBUtility.insertWithDel(l_compFxConditionParams);

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
                "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl",
                "getNewNumber",
                new Class[] {String.class, String.class, ProductTypeEnum.class },
                "111");     

            TestDBUtility.deleteAll(TraderParams.TYPE);
            TraderParams l_traderParams =
                TestDBUtility.getTraderRow();
            l_traderParams.setTraderId(123456);
            TestDBUtility.insertWithDel(l_traderParams);
            
            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            OrderValidationResult l_result = new OrderValidationResult(processingResult);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeOrderValidator", "validateTradingPassword",
                new Class[] {Trader.class, SubAccount.class, String.class },
                l_result);

            TestDBUtility.deleteAll(GftAccountOpenStatusParams.TYPE);

            TestDBUtility.deleteAll(QuestionAnswerParams.TYPE);
            
            l_impl.startAccountOpen(l_request);

            fail();
        }
        catch (WEB3BaseException l_ex)
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

    public void testStartAccountOpen_C0005()
    {
        final String STR_METHOD_NAME = "testStartAccountOpen_C0005()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_processors = Processors.getDefaultProcessor();
            WEB3FXAccOpenAskingRequest l_request =
                new WEB3FXAccOpenAskingRequest();
            l_request.wolfSession = "session";
            l_request.wolfAid = "123";
            l_request.regetServiceId = "456";
            l_request.fxMailAddress = "jiddk@@126.com";
            l_request.fxPassword = "123456";
            l_request.agreementDiv = "0";
            l_request.fxSystemCode = "07";
            l_request.wolfSsid = "789";

            WEB3FXTradeAgreementUnit[] fxTradeAgreementList =
                new WEB3FXTradeAgreementUnit[1];
            fxTradeAgreementList[0] = new WEB3FXTradeAgreementUnit();
            fxTradeAgreementList[0].questionAnswer = "0";
            fxTradeAgreementList[0].questionNumber = "101";

            l_request.fxTradeAgreementList = fxTradeAgreementList;

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getAccountId",
                new Class[] {},
                new Long(123456));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "validateOrder",
                new Class[] {SubAccount.class},
                null);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123456);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setInstitutionId(33);
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setBranchId(123);
            l_mainAccountParams.setAccountType(MainAccountTypeEnum.CORPORATE_ACCOUNT);
            l_processors.doInsertQuery(l_mainAccountParams);

            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setAccountCode("jiddk");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(123456);
            l_subAccountParams.setInstitutionCode("0D");
            l_subAccountParams.setInstitutionId(33);
            l_subAccountParams.setBranchId(123);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            l_subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
            InstitutionPreferencesParams l_branchPreferencesParams =
                this.getInstitutionPreferencesParams();
            l_branchPreferencesParams.setInstitutionId(33);
            l_branchPreferencesParams.setName("fx.agreement.question.check");
            l_branchPreferencesParams.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setFxSystemCode("07");
            l_compFxConditionParams.setQuestionCheckDiv("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);

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
                "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl",
                "getNewNumber",
                new Class[] {String.class, String.class, ProductTypeEnum.class },
                "111");     

            TestDBUtility.deleteAll(TraderParams.TYPE);
            TraderParams l_traderParams =
                TestDBUtility.getTraderRow();
            l_traderParams.setTraderId(123456);
            TestDBUtility.insertWithDel(l_traderParams);
            
            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            OrderValidationResult l_result = new OrderValidationResult(processingResult);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeOrderValidator", "validateTradingPassword",
                new Class[] {Trader.class, SubAccount.class, String.class },
                l_result);

            TestDBUtility.deleteAll(GftAccountOpenStatusParams.TYPE);

            TestDBUtility.deleteAll(QuestionAnswerParams.TYPE);
            
            l_impl.startAccountOpen(l_request);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01796, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testStartAccountOpen_C0006()
    {
        final String STR_METHOD_NAME = "testStartAccountOpen_C0006()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_processors = Processors.getDefaultProcessor();
            WEB3FXAccOpenAskingRequest l_request =
                new WEB3FXAccOpenAskingRequest();
            l_request.wolfSession = "session";
            l_request.wolfAid = "123";
            l_request.regetServiceId = "456";
            l_request.fxMailAddress = "jiddk@@126.com";
            l_request.fxPassword = "123456";
            l_request.agreementDiv = "0";
            l_request.fxSystemCode = "07";
            l_request.wolfSsid = "789";

            WEB3FXTradeAgreementUnit[] fxTradeAgreementList =
                new WEB3FXTradeAgreementUnit[1];
            fxTradeAgreementList[0] = new WEB3FXTradeAgreementUnit();
            fxTradeAgreementList[0].questionAnswer = "0";
            fxTradeAgreementList[0].questionNumber = "101";

            l_request.fxTradeAgreementList = fxTradeAgreementList;

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getAccountId",
                new Class[] {},
                new Long(123456));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "validateOrder",
                new Class[] {SubAccount.class},
                null);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123456);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setInstitutionId(33);
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setBranchId(123);
            l_mainAccountParams.setAccountType(MainAccountTypeEnum.CORPORATE_ACCOUNT);
            l_processors.doInsertQuery(l_mainAccountParams);

            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setAccountCode("jiddk");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(123456);
            l_subAccountParams.setInstitutionCode("0D");
            l_subAccountParams.setInstitutionId(33);
            l_subAccountParams.setBranchId(123);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            l_subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(123);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(FxAccountRow.TYPE);
            FxAccountParams l_fxAccountParams = TestDBUtility.getFxAccountRow();
            l_fxAccountParams.setInstitutionCode("0D");
            l_fxAccountParams.setBranchCode("381");
            l_fxAccountParams.setFxSystemCode("07");
            l_fxAccountParams.setAccountCode("2512246");
            TestDBUtility.insertWithDel(l_fxAccountParams);

            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
            InstitutionPreferencesParams l_branchPreferencesParams =
                this.getInstitutionPreferencesParams();
            l_branchPreferencesParams.setInstitutionId(33);
            l_branchPreferencesParams.setName("fx.agreement.question.check");
            l_branchPreferencesParams.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setFxSystemCode("07");
            l_compFxConditionParams.setQuestionCheckDiv("2");
            l_compFxConditionParams.setFxSystemDiv("2");
            TestDBUtility.insertWithDel(l_compFxConditionParams);

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
                "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl",
                "getNewNumber",
                new Class[] {String.class, String.class, ProductTypeEnum.class },
                "111");     

            TestDBUtility.deleteAll(TraderParams.TYPE);
            TraderParams l_traderParams =
                TestDBUtility.getTraderRow();
            l_traderParams.setTraderId(123456);
            TestDBUtility.insertWithDel(l_traderParams);

            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            OrderValidationResult l_result = new OrderValidationResult(processingResult);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeOrderValidator", "validateTradingPassword",
                new Class[] {Trader.class, SubAccount.class, String.class },
                l_result);

            TestDBUtility.deleteAll(GftAccountOpenStatusParams.TYPE);

            TestDBUtility.deleteAll(QuestionAnswerParams.TYPE);
            
            l_impl.startAccountOpen(l_request);

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03133, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testStartAccountOpen_C0007()
    {
        final String STR_METHOD_NAME = "testStartAccountOpen_C0007()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_processors = Processors.getDefaultProcessor();
            WEB3FXAccOpenAskingRequest l_request =
                new WEB3FXAccOpenAskingRequest();
            l_request.wolfSession = "session";
            l_request.wolfAid = "123";
            l_request.regetServiceId = "456";
            l_request.fxMailAddress = "jiddk@@126.com";
            l_request.fxPassword = "123456";
            l_request.agreementDiv = "0";
            l_request.fxSystemCode = "07";
            l_request.wolfSsid = "789";

            WEB3FXTradeAgreementUnit[] fxTradeAgreementList =
                new WEB3FXTradeAgreementUnit[1];
            fxTradeAgreementList[0] = new WEB3FXTradeAgreementUnit();
            fxTradeAgreementList[0].questionAnswer = "1";
            fxTradeAgreementList[0].questionNumber = "101";

            l_request.fxTradeAgreementList = fxTradeAgreementList;

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getAccountId",
                new Class[] {},
                new Long(123456));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "validateOrder",
                new Class[] {SubAccount.class},
                null);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123456);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setInstitutionId(33);
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setBranchId(123);
            l_mainAccountParams.setAddressLine1("東京都");
            l_mainAccountParams.setAddressLine2(null);
            l_mainAccountParams.setAddressLine3(null);
            l_mainAccountParams.setAccountType(MainAccountTypeEnum.CORPORATE_ACCOUNT);
            l_processors.doInsertQuery(l_mainAccountParams);

            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setAccountCode("jiddk");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(123456);
            l_subAccountParams.setInstitutionCode("0D");
            l_subAccountParams.setInstitutionId(33);
            l_subAccountParams.setBranchId(123);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            l_subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(FxAccountRow.TYPE);
            FxAccountParams l_fxAccountParams = TestDBUtility.getFxAccountRow();
            l_fxAccountParams.setInstitutionCode("0D");
            l_fxAccountParams.setBranchCode("381");
            l_fxAccountParams.setFxSystemCode("07");
            l_fxAccountParams.setAccountCode("102");
            TestDBUtility.insertWithDel(l_fxAccountParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(123);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
            InstitutionPreferencesParams l_branchPreferencesParams =
                this.getInstitutionPreferencesParams();
            l_branchPreferencesParams.setInstitutionId(33);
            l_branchPreferencesParams.setName("fx.agreement.question.check");
            l_branchPreferencesParams.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setFxSystemCode("07");
            l_compFxConditionParams.setExtConnectSystemCode("01");
            l_compFxConditionParams.setOnlineAccOpen("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);

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
                "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl",
                "getNewNumber",
                new Class[] {String.class, String.class, ProductTypeEnum.class },
                "111");     

            TestDBUtility.deleteAll(TraderParams.TYPE);
            TraderParams l_traderParams =
                TestDBUtility.getTraderRow();
            l_traderParams.setTraderId(123456);
            TestDBUtility.insertWithDel(l_traderParams);
            
            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            OrderValidationResult l_result = new OrderValidationResult(processingResult);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeOrderValidator", "validateTradingPassword",
                new Class[] {Trader.class, SubAccount.class, String.class },
                l_result);

            TestDBUtility.deleteAll(GftAccountOpenStatusParams.TYPE);

            TestDBUtility.deleteAll(QuestionAnswerParams.TYPE);
            
            WEB3FXAccOpenAskingResponse l_response = l_impl.startAccountOpen(l_request);

            assertEquals("01", l_response.fxGftAskingTelegramUnit.gftOperationDiv);
            assertEquals("東京都", l_response.fxGftAskingTelegramUnit.address1);
            assertNull(l_response.fxGftAskingTelegramUnit.address2);
            assertNull(l_response.fxGftAskingTelegramUnit.address3);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testStartAccountOpen_C0008()
    {
        final String STR_METHOD_NAME = "testStartAccountOpen_C0008()";
        log.entering(STR_METHOD_NAME);
        try
        {
            QueryProcessor l_processors = Processors.getDefaultProcessor();
            WEB3FXAccOpenAskingRequest l_request =
                new WEB3FXAccOpenAskingRequest();
            l_request.wolfSession = "session";
            l_request.wolfAid = "123";
            l_request.regetServiceId = "456";
            l_request.fxMailAddress = "jiddk@@126.com";
            l_request.fxPassword = "123456";
            l_request.agreementDiv = "0";
            l_request.fxSystemCode = "07";
            l_request.wolfSsid = "789";

            WEB3FXTradeAgreementUnit[] fxTradeAgreementList =
                new WEB3FXTradeAgreementUnit[1];
            fxTradeAgreementList[0] = new WEB3FXTradeAgreementUnit();
            fxTradeAgreementList[0].questionAnswer = "1";
            fxTradeAgreementList[0].questionNumber = "101";

            l_request.fxTradeAgreementList = fxTradeAgreementList;

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getAccountId",
                new Class[] {},
                new Long(123456));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "validateOrder",
                new Class[] {SubAccount.class},
                null);

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123456);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setInstitutionId(33);
            l_mainAccountParams.setBranchCode("624");
            l_mainAccountParams.setBranchId(123);
            l_mainAccountParams.setAddressLine1("　@ 　@東京 　@ 都 　@ ");
            l_mainAccountParams.setAddressLine2("　@ 　@江 　@ 東　@ 区 　@ ");
            l_mainAccountParams.setAddressLine3(" 深　@ 　@川５　@ ");
            l_mainAccountParams.setAccountType(MainAccountTypeEnum.CORPORATE_ACCOUNT);
            l_processors.doInsertQuery(l_mainAccountParams);

            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setAccountCode("jiddk");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(123456);
            l_subAccountParams.setInstitutionCode("0D");
            l_subAccountParams.setInstitutionId(33);
            l_subAccountParams.setBranchId(123);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            l_subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(123);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(FxAccountRow.TYPE);
            FxAccountParams l_fxAccountParams = TestDBUtility.getFxAccountRow();
            l_fxAccountParams.setInstitutionCode("0D");
            l_fxAccountParams.setBranchCode("381");
            l_fxAccountParams.setFxSystemCode("07");
            l_fxAccountParams.setAccountCode("102");
            TestDBUtility.insertWithDel(l_fxAccountParams);

            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
            InstitutionPreferencesParams l_branchPreferencesParams =
                this.getInstitutionPreferencesParams();
            l_branchPreferencesParams.setInstitutionId(33);
            l_branchPreferencesParams.setName("fx.agreement.question.check");
            l_branchPreferencesParams.setNameSerialNo(1);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setFxSystemCode("07");
            l_compFxConditionParams.setExtConnectSystemCode("01");
            l_compFxConditionParams.setOnlineAccOpen("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);

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
                "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl",
                "getNewNumber",
                new Class[] {String.class, String.class, ProductTypeEnum.class },
                "111");     

            TestDBUtility.deleteAll(TraderParams.TYPE);
            TraderParams l_traderParams =
                TestDBUtility.getTraderRow();
            l_traderParams.setTraderId(123456);
            TestDBUtility.insertWithDel(l_traderParams);
            
            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            OrderValidationResult l_result = new OrderValidationResult(processingResult);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeOrderValidator", "validateTradingPassword",
                new Class[] {Trader.class, SubAccount.class, String.class },
                l_result);

            TestDBUtility.deleteAll(GftAccountOpenStatusParams.TYPE);

            TestDBUtility.deleteAll(QuestionAnswerParams.TYPE);
            
            WEB3FXAccOpenAskingResponse l_response = l_impl.startAccountOpen(l_request);

            assertEquals("01", l_response.fxGftAskingTelegramUnit.gftOperationDiv);
            assertEquals("東京 　@ 都", l_response.fxGftAskingTelegramUnit.address1);
            assertEquals("江 　@ 東　@ 区", l_response.fxGftAskingTelegramUnit.address2);
            assertEquals("深　@ 　@川５", l_response.fxGftAskingTelegramUnit.address3);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * isGFT口座開設()の戻り値 ==falseの場合
     * 新規開設：01を返却する。 
     */
    public void testGetGFTOperationDiv_C0001()
    {
        final String STR_METHOD_NAME = "testGetGFTOperationDiv_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setExtConnectSystemCode("01");
            l_compFxConditionParams.setFxSystemCode("01");
            TestDBUtility.insertWithDel(l_compFxConditionParams);
            l_compFxConditionParams.setFxSystemCode("02");
            TestDBUtility.insertWithDel(l_compFxConditionParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(102);
            l_subAccountParams.setInstitutionId(101);
            SubAccount l_subAccount = new SubAccountImpl(l_subAccountParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(102);
            l_mainAccountParams.setInstitutionId(101);
            l_mainAccountParams.setBranchId(103);
            l_mainAccountParams.setAccountCode("100");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(101);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(103);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(FxAccountRow.TYPE);
            FxAccountParams l_fxAccountParams = TestDBUtility.getFxAccountRow();
            l_fxAccountParams.setInstitutionCode("0D");
            l_fxAccountParams.setBranchCode("381");
            l_fxAccountParams.setAccountCode("100");
            l_fxAccountParams.setFxSystemCode("03");
            TestDBUtility.insertWithDel(l_fxAccountParams);

            String l_strGFTOperationDiv = l_impl.getGFTOperationDiv(l_subAccount);

            assertEquals("01", l_strGFTOperationDiv);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * GFT口座開設()の戻り値 ==trueの場合
     * 加開設：03を返却する。
     */
    public void testGetGFTOperationDiv_C0002()
    {
        final String STR_METHOD_NAME = "testGetGFTOperationDiv_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setExtConnectSystemCode("01");
            l_compFxConditionParams.setFxSystemCode("01");
            TestDBUtility.insertWithDel(l_compFxConditionParams);
            l_compFxConditionParams.setFxSystemCode("02");
            TestDBUtility.insertWithDel(l_compFxConditionParams);
            l_compFxConditionParams.setFxSystemCode("03");
            TestDBUtility.insertWithDel(l_compFxConditionParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(102);
            l_subAccountParams.setInstitutionId(101);
            SubAccount l_subAccount = new SubAccountImpl(l_subAccountParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(102);
            l_mainAccountParams.setInstitutionId(101);
            l_mainAccountParams.setBranchId(103);
            l_mainAccountParams.setAccountCode("100");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(101);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(103);
            TestDBUtility.insertWithDel(l_branchParams);

            TestDBUtility.deleteAll(FxAccountRow.TYPE);
            FxAccountParams l_fxAccountParams = TestDBUtility.getFxAccountRow();
            l_fxAccountParams.setInstitutionCode("0D");
            l_fxAccountParams.setBranchCode("381");
            l_fxAccountParams.setAccountCode("100");
            l_fxAccountParams.setFxSystemCode("03");
            TestDBUtility.insertWithDel(l_fxAccountParams);

            String l_strGFTOperationDiv = l_impl.getGFTOperationDiv(l_subAccount);

            assertEquals("03", l_strGFTOperationDiv);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public class WEB3FXAccOpenServiceImplForTest1 extends WEB3FXAccOpenServiceImpl{
        
        protected WEB3FXAccOpenAskingResponse startAccountOpen(
                WEB3FXAccOpenAskingRequest l_request) throws WEB3BaseException
            {
                final String STR_METHOD_NAME = "startAccountOpen(WEB3FXAccOpenAskingRequest l_request)";
                log.entering(STR_METHOD_NAME);
                
   
                //1.12) GFT依頼電文明細( )
                //アイテムの定義
                //GFT依頼電文明細オブジェクトを生成する。
                WEB3FXGftAskingTelegramUnit l_fXGftAskingTelegramUnit = new WEB3FXGftAskingTelegramUnit();
                
                //1.13) (*)プロパティセット
                //GFT依頼電文明細に必要なプロパティをセットする（下記以外のプロパティは設定しない）
                
                //DIR→GFT送信日時   ：現在時刻（システムタイムスタンプ）
                l_fXGftAskingTelegramUnit.dirSendTime = 
                    WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), "yyyyMMddHHmmss");
                
                //処理区分      ：01(口座開設）
                l_fXGftAskingTelegramUnit.gftOperationDiv = WEB3GftMessageOperationDef.ACCOUNT_OPEN;
                    
                //メールアドレス   ：リクエストデータ.FXメールアドレス
                l_fXGftAskingTelegramUnit.fxMailAddress = l_request.fxMailAddress;
                
                //初期ログインID  ：get新規FXログインID()の戻り値
                l_fXGftAskingTelegramUnit.fxFirstLoginId = "bbb";
                
                //初期パスワード   ：リクエストデータ.FX暗証番号
                l_fXGftAskingTelegramUnit.fxFirstPassword = l_request.fxPassword;
                
                //担当区分      ：会社別FXシステム条件Params.担当区分
                l_fXGftAskingTelegramUnit.groupName = "lll";
                
                //会社コード     ：補助口座.証券会社コード
                l_fXGftAskingTelegramUnit.institutionCode = "0D";
                
                //WOLFセッションキー   ：リクエストデータ.WOLFセッションキー
                l_fXGftAskingTelegramUnit.wolfSession = l_request.wolfSession;
                
                //アプリケーションID    ：リクエストデータ.アプリケーションID
                l_fXGftAskingTelegramUnit.wolfAid = l_request.wolfAid;
                
                //再生成サービスID ：リクエストデータ.再生成サービスID
                l_fXGftAskingTelegramUnit.regetServiceId = l_request.regetServiceId;
                
                //SSID      ：リクエストデータ.SSID
                l_fXGftAskingTelegramUnit.wolfSsid = l_request.wolfSsid;
                
                //部店コード     ：補助口座.get取引店().getBranchCode()
                l_fXGftAskingTelegramUnit.branchCode = "381";
                
                //顧客コード     ：補助口座.getMainAccount().getAccountCode()
                l_fXGftAskingTelegramUnit.accountCode = "123";
                
                //識別コード     ：get新規識別コード()の戻り値
                l_fXGftAskingTelegramUnit.requestNumber = "456";
                
                
                l_fXGftAskingTelegramUnit.fxLastName = "fengfht";
                
                //1.14) createGFT電文ハッシュ値(GFT依頼電文明細)
                //アイテムの定義
                //GFT電文のハッシュ値を取得する。
                //[引数の設定]
                //GFT依頼電文明細：　@プロパティセットを行ったGFT依頼電文明細
                WEB3FXTelegramProcessService l_fXTelegramProcessService =
                    (WEB3FXTelegramProcessService)Services.getService(WEB3FXTelegramProcessService.class);

                String l_strGFTTelegramHashValue = "ffff";
                    ///l_fXTelegramProcessService.createGFTTelegramHashValue(l_fXGftAskingTelegramUnit);

                //createGFT電文ハッシュ値()の戻り値のハッシュ値を
                //GFT依頼電文明細.ハッシュ値にセットする。
                l_fXGftAskingTelegramUnit.hashValue = l_strGFTTelegramHashValue;

                WEB3FXAccOpenAskingResponse l_response = new WEB3FXAccOpenAskingResponse();


                l_response.fxUrl = "asdasd";
                l_response.fxGftAskingTelegramUnit = l_fXGftAskingTelegramUnit;

                log.exiting(STR_METHOD_NAME);
                return l_response;
            }

        protected WEB3FXAccOpenCompleteResponse submitAccountOpen(
            WEB3FXAccOpenCompleteRequest l_request) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "submitAccountOpen(WEB3FXAccOpenCompleteRequest l_request)";
            log.entering(STR_METHOD_NAME);
            

            WEB3FXAccInformationUnit[] fxAccInformationList = new WEB3FXAccInformationUnit[1];
            
            fxAccInformationList[0]  = new WEB3FXAccInformationUnit();
            
            fxAccInformationList[0].fxAccountCode = "123";
            fxAccInformationList[0].fxCourseDiv = "1";
            
            //1.14) createResponse( )
            WEB3FXAccOpenCompleteResponse l_response = new WEB3FXAccOpenCompleteResponse();

            l_response.fxLoginId = l_request.fxGftResultNoticeTelegramUnit.fxFirstLoginId;
            
            //為替保証金口座情報一覧：　@createFX口座情報一覧()の戻り値
            l_response.fxAccInformationList = fxAccInformationList;
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

    }

    public class WEB3FXAccOpenServiceImplForTest extends WEB3FXAccOpenServiceImpl{
        
        protected WEB3FXAccOpenAskingResponse startAccountOpen(
                WEB3FXAccOpenAskingRequest l_request) throws WEB3BaseException
            {
                final String STR_METHOD_NAME = "startAccountOpen(WEB3FXAccOpenAskingRequest l_request)";
                log.entering(STR_METHOD_NAME);
                
   
                //1.12) GFT依頼電文明細( )
                //アイテムの定義
                //GFT依頼電文明細オブジェクトを生成する。
                WEB3FXGftAskingTelegramUnit l_fXGftAskingTelegramUnit = new WEB3FXGftAskingTelegramUnit();
                
                //1.13) (*)プロパティセット
                //GFT依頼電文明細に必要なプロパティをセットする（下記以外のプロパティは設定しない）
                
                //DIR→GFT送信日時   ：現在時刻（システムタイムスタンプ）
                l_fXGftAskingTelegramUnit.dirSendTime = 
                    WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), "yyyyMMddHHmmss");
                
                //処理区分      ：01(口座開設）
                l_fXGftAskingTelegramUnit.gftOperationDiv = WEB3GftMessageOperationDef.ACCOUNT_OPEN;
                    
                //メールアドレス   ：リクエストデータ.FXメールアドレス
                l_fXGftAskingTelegramUnit.fxMailAddress = l_request.fxMailAddress;
                
                //初期ログインID  ：get新規FXログインID()の戻り値
                l_fXGftAskingTelegramUnit.fxFirstLoginId = "bbb";
                
                //初期パスワード   ：リクエストデータ.FX暗証番号
                l_fXGftAskingTelegramUnit.fxFirstPassword = l_request.fxPassword;
                
                //担当区分      ：会社別FXシステム条件Params.担当区分
                l_fXGftAskingTelegramUnit.groupName = "lll";
                
                //会社コード     ：補助口座.証券会社コード
                l_fXGftAskingTelegramUnit.institutionCode = "0D";
                
                //WOLFセッションキー   ：リクエストデータ.WOLFセッションキー
                l_fXGftAskingTelegramUnit.wolfSession = l_request.wolfSession;
                
                //アプリケーションID    ：リクエストデータ.アプリケーションID
                l_fXGftAskingTelegramUnit.wolfAid = l_request.wolfAid;
                
                //再生成サービスID ：リクエストデータ.再生成サービスID
                l_fXGftAskingTelegramUnit.regetServiceId = l_request.regetServiceId;
                
                //SSID      ：リクエストデータ.SSID
                l_fXGftAskingTelegramUnit.wolfSsid = l_request.wolfSsid;
                
                //部店コード     ：補助口座.get取引店().getBranchCode()
                l_fXGftAskingTelegramUnit.branchCode = "381";
                
                //顧客コード     ：補助口座.getMainAccount().getAccountCode()
                l_fXGftAskingTelegramUnit.accountCode = "123";
                
                //識別コード     ：get新規識別コード()の戻り値
                l_fXGftAskingTelegramUnit.requestNumber = "456";
                
                
                l_fXGftAskingTelegramUnit.fxLastName = "fengfht";
                
                //1.14) createGFT電文ハッシュ値(GFT依頼電文明細)
                //アイテムの定義
                //GFT電文のハッシュ値を取得する。
                //[引数の設定]
                //GFT依頼電文明細：　@プロパティセットを行ったGFT依頼電文明細
                WEB3FXTelegramProcessService l_fXTelegramProcessService =
                    (WEB3FXTelegramProcessService)Services.getService(WEB3FXTelegramProcessService.class);

                String l_strGFTTelegramHashValue = "ffff";
                    ///l_fXTelegramProcessService.createGFTTelegramHashValue(l_fXGftAskingTelegramUnit);

                //createGFT電文ハッシュ値()の戻り値のハッシュ値を
                //GFT依頼電文明細.ハッシュ値にセットする。
                l_fXGftAskingTelegramUnit.hashValue = l_strGFTTelegramHashValue;

                WEB3FXAccOpenAskingResponse l_response = new WEB3FXAccOpenAskingResponse();


                l_response.fxUrl = "asdasd";
                l_response.fxGftAskingTelegramUnit = l_fXGftAskingTelegramUnit;

                log.exiting(STR_METHOD_NAME);
                return l_response;
            }

        protected WEB3FXAccOpenCompleteSoapResponse submitAccountOpenSoap(
            WEB3FXAccOpenCompleteSoapRequest l_request) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "WEB3FXAccOpenCompleteSoapRequest(WEB3FXAccOpenCompleteSoapRequest l_request)";
            log.entering(STR_METHOD_NAME);
            

            WEB3FXAccInformationUnit[] fxAccInformationList = new WEB3FXAccInformationUnit[1];
            
            fxAccInformationList[0]  = new WEB3FXAccInformationUnit();

            fxAccInformationList[0].fxAccountCode = "123";
            fxAccInformationList[0].fxCourseDiv = "1";
            
            //1.14) createResponse( )
            WEB3FXAccOpenCompleteSoapResponse l_response = new WEB3FXAccOpenCompleteSoapResponse();
                            
            //為替保証金口座情報一覧：　@createFX口座情報一覧()の戻り値
            l_response.fxLoginId = "bbb";
            l_response.fxAccInformationList = fxAccInformationList;
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        protected WEB3FXAccOpenCompleteResponse submitAccountOpen(
            WEB3FXAccOpenCompleteRequest l_request) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "submitAccountOpen(WEB3FXAccOpenCompleteRequest l_request)";
            log.entering(STR_METHOD_NAME);
            

            WEB3FXAccInformationUnit[] fxAccInformationList = new WEB3FXAccInformationUnit[1];
            
            fxAccInformationList[0]  = new WEB3FXAccInformationUnit();
            
            fxAccInformationList[0].fxAccountCode = "123";
            fxAccInformationList[0].fxCourseDiv = "1";
            
            //1.14) createResponse( )
            WEB3FXAccOpenCompleteResponse l_response = new WEB3FXAccOpenCompleteResponse();

            l_response.fxLoginId = l_request.fxGftResultNoticeTelegramUnit.fxFirstLoginId;
            
            //為替保証金口座情報一覧：　@createFX口座情報一覧()の戻り値
            l_response.fxAccInformationList = fxAccInformationList;
            
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

    }

    public class WEB3FXDataControlServiceImplForTest extends WEB3FXDataControlServiceImplForMock{
        
        public void updateGFTAccountOpenStatus(
                String l_strInstitutionCode,
                String l_strBranchCode,
                String l_strOrderRequestNumber,
                String l_strResultCode)
                    throws WEB3BaseException{
            
        }

        public String getSoapAcceptResultCode(String l_acceptResultCode,
            String l_acceptResultDetailCode)
        {
            return WEB3AioAcceptResultCodeDef.ACCEPT_RESULT_CODE_00000199;
        }
    }
    
    
    public class WEB3FXTelegramProcessServiceImplForTest extends WEB3FXTelegramProcessServiceImplForMock{
        
        
        public String createGFTTelegramHashValue(WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "createGFTTelegramHashValue(" +
            "WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit)";
        
        return "aaa";
        
    }
        
    }
    
    public InstitutionPreferencesParams getInstitutionPreferencesParams()
    {
        InstitutionPreferencesParams l_params = new InstitutionPreferencesParams();
        //証券会社ＩＤ    institution_id     NUMBER    18    NotNull
        l_params.setInstitutionId(33);
        //プリファ@レンス名    name     VARVHAR2    200    NotNull
        l_params.setName("jiddk");
        //プリファ@レンス名の連番    name_serial_no     NUMBER    6    NotNull
        l_params.setNameSerialNo(1);
        //プリファ@レンスの値    value     VARVHAR2    200    NotNull
        l_params.setValue("jidck");
        //作成日付    created_timestamp     DATE        NotNull
        l_params.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        //更新日付    last_updated_timestamp     DATE        NotNull
        l_params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        return l_params;
    }

}
@


1.1
log
@*** empty log message ***
@
text
@a27 3
import com.gftforex.soap.api.ResultInfoBase;
import com.gftforex.soap.api.SendSyncRequestResponse;

d2329 5
a2333 5
            SendSyncRequestResponse l_sendSyncRequestResponse = new SendSyncRequestResponse();
            ResultInfoBase l_resultInfoBase = new ResultInfoBase();
            l_resultInfoBase.setMajorStatusCode(1);
            l_resultInfoBase.setMinorStatusCode(1);
            l_sendSyncRequestResponse.setSendSyncRequestResult(l_resultInfoBase);
@

