head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.39.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FXTransFromFXInputServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio.service.delegate.stdimpls;

import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
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
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderRow;

import test.util.TestDBUtility;

import webbroker3.aio.WEB3GFTConnectionSystemTest;
import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.data.CompFxConditionRow;
import webbroker3.aio.data.FxAccountCodeParams;
import webbroker3.aio.data.FxAccountParams;
import webbroker3.aio.data.FxAccountRow;
import webbroker3.aio.data.GftTransferStatusParams;
import webbroker3.aio.data.GftTransferStatusRow;
import webbroker3.aio.message.WEB3FXGftAskingTelegramUnit;
import webbroker3.aio.message.WEB3FXTransFromFXInputRequest;
import webbroker3.aio.message.WEB3FXTransFromFXInputResponse;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.define.WEB3CommonTransferPageDivDef;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.SoapConnectPrefRpcParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (FXから振替入力サービスImplTest) <BR>
 * 
 * 
 */
public class WEB3FXTransFromFXInputServiceImplTest extends TestBaseForMock {

    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3FXTransFromFXInputServiceImplTest.class);
    
    public WEB3FXTransFromFXInputServiceImplTest(String arg0) {
        super(arg0);
    }

    protected void setUp() throws Exception {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    WEB3FXTransFromFXInputServiceImpl l_impl = new WEB3FXTransFromFXInputServiceImpl();
    
    /*
     * Test method for 'webbroker3.aio.service.delegate.stdimpls.WEB3FXTransFromFXInputServiceImpl.execute(WEB3GenRequest)'
     */
    public void testExecute() 
    {
        final String STR_METHOD_NAME  = "testExecute";
        log.entering(STR_METHOD_NAME);
        
        WEB3FXTransFromFXInputRequest l_request = new WEB3FXTransFromFXInputRequest();
        l_request.fxSystemCode = "01";
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "validateOrder",
                new Class[] {SubAccount.class},
                null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXDataControlServiceImpl",
                "validateChangePoss",
                new Class[]{ SubAccount.class, CompFxConditionParams.class},
                null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXDataControlServiceImpl", "getHashValue", new Class[]
                { String.class, String.class, String.class },
                "1");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXDataControlServiceImpl", "sendSoapMessage", new Class[]
                 {WEB3FXGftAskingTelegramUnit.class, SoapConnectPrefRpcParams.class, String.class, String.class },
                 WEB3GFTConnectionSystemTest.getSendSyncRequestResponse(1, true,0,0,0,0));
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(AioOrderRow.TYPE);
            TestDBUtility.deleteAllAndCommit(GftTransferStatusRow.TYPE);
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            TestDBUtility.deleteAll(FxAccountRow.TYPE);
            TestDBUtility.deleteAll(FxAccountCodeParams.TYPE);
            TestDBUtility.deleteAll(SoapConnectPrefRpcParams.TYPE);

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
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            AioOrderParams l_aioOrderParams = TestDBUtility.getAioOrderRow();
            l_aioOrderParams.setAccountId(l_subAccountParams.getAccountId());
            l_aioOrderParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_aioOrderParams.setOrderId(1111);
            TestDBUtility.insertWithDel(l_aioOrderParams);

            GftTransferStatusParams l_gftTransferStatusParams = TestDBUtility.getGftTransferStatusRow();
            TestDBUtility.insertWithDelAndCommit(l_gftTransferStatusParams);

            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setFxSystemCode("01");
            l_compFxConditionParams.setFxSystemDiv("2");
            l_compFxConditionParams.setGetTransferableAmtDiv("1");
            l_compFxConditionParams.setExtConnectSystemCode("01");
            TestDBUtility.insertWithDel(l_compFxConditionParams);


            FxAccountParams l_fxAccountParams = TestDBUtility.getFxAccountRow();
            l_fxAccountParams.setInstitutionCode("0D");
            l_fxAccountParams.setBranchCode("381");
            l_fxAccountParams.setFxSystemCode("01");
            l_fxAccountParams.setAccountCode("2512246");
            TestDBUtility.insertWithDel(l_fxAccountParams);

            FxAccountCodeParams l_FxAccountCodeParams = TestDBUtility.getFxAccountCodeRow();
            l_FxAccountCodeParams.setBranchCode("381");
            l_FxAccountCodeParams.setAccountCode("2512246");
            l_FxAccountCodeParams.setFxSystemCode("01");
            l_FxAccountCodeParams.setFxAccountCode("11");
            l_FxAccountCodeParams.setFxCourseDiv("1");
            l_FxAccountCodeParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_FxAccountCodeParams);

            SoapConnectPrefRpcParams l_soapConnectPrefRpcParams = new SoapConnectPrefRpcParams();
            l_soapConnectPrefRpcParams.setBranchId(l_branchParams.getBranchId());
            l_soapConnectPrefRpcParams.setConnectDiv(l_compFxConditionParams.getFxSystemCode());
            l_soapConnectPrefRpcParams.setEndpointName("url");
            l_soapConnectPrefRpcParams.setTargetNamespaceName("0");
            l_soapConnectPrefRpcParams.setOperationName("0");
            l_soapConnectPrefRpcParams.setParameterList("1");
            l_soapConnectPrefRpcParams.setServiceName("1");
            l_soapConnectPrefRpcParams.setPortTypeName("1");
            l_soapConnectPrefRpcParams.setResponseParamType("1");
            l_soapConnectPrefRpcParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_soapConnectPrefRpcParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_soapConnectPrefRpcParams);

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20090321", "yyyyMMdd"));

            WEB3FXTransFromFXInputResponse l_fxTransFromFXInputResponse 
                = (WEB3FXTransFromFXInputResponse)l_impl.execute(l_request);

            assertEquals(l_fxTransFromFXInputResponse.transferCount,"0");
            assertEquals(l_fxTransFromFXInputResponse.transferCountUpper,"3");
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    /*
     * Test method for 'webbroker3.aio.service.delegate.stdimpls.WEB3FXTransFromFXInputServiceImpl.execute(WEB3GenRequest)'
     */
    public void testExecute001() 
    {
        final String STR_METHOD_NAME  = "testExecute001";
        log.entering(STR_METHOD_NAME);
        
        WEB3FXTransFromFXInputRequest l_request = new WEB3FXTransFromFXInputRequest();
        l_request.fxSystemCode = "01";
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "validateOrder",
                new Class[] {SubAccount.class},
                null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXDataControlServiceImpl",
                "validateChangePoss",
                new Class[]{ SubAccount.class, CompFxConditionParams.class},
                null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXDataControlServiceImpl", "getHashValue", new Class[]
                { String.class, String.class, String.class },
                "1");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXDataControlServiceImpl", "sendSoapMessage", new Class[]
                 {WEB3FXGftAskingTelegramUnit.class, SoapConnectPrefRpcParams.class, String.class, String.class },
                 WEB3GFTConnectionSystemTest.getSendSyncRequestResponse(1, true,0,0,0,0));
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(AioOrderRow.TYPE);
            TestDBUtility.deleteAllAndCommit(GftTransferStatusRow.TYPE);
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            TestDBUtility.deleteAll(FxAccountRow.TYPE);
            TestDBUtility.deleteAll(FxAccountCodeParams.TYPE);
            TestDBUtility.deleteAll(SoapConnectPrefRpcParams.TYPE);

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
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            AioOrderParams l_aioOrderParams = TestDBUtility.getAioOrderRow();
            l_aioOrderParams.setAccountId(l_subAccountParams.getAccountId());
            l_aioOrderParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_aioOrderParams.setOrderId(1111);
            TestDBUtility.insertWithDel(l_aioOrderParams);

            GftTransferStatusParams l_gftTransferStatusParams = TestDBUtility.getGftTransferStatusRow();
            TestDBUtility.insertWithDelAndCommit(l_gftTransferStatusParams);

            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setFxSystemCode("01");
            l_compFxConditionParams.setFxSystemDiv("2");
            l_compFxConditionParams.setGetTransferableAmtDiv("0");
            l_compFxConditionParams.setExtConnectSystemCode("01");
            TestDBUtility.insertWithDel(l_compFxConditionParams);


            FxAccountParams l_fxAccountParams = TestDBUtility.getFxAccountRow();
            l_fxAccountParams.setInstitutionCode("0D");
            l_fxAccountParams.setBranchCode("381");
            l_fxAccountParams.setFxSystemCode("01");
            l_fxAccountParams.setAccountCode("2512246");
            TestDBUtility.insertWithDel(l_fxAccountParams);

            FxAccountCodeParams l_FxAccountCodeParams = TestDBUtility.getFxAccountCodeRow();
            l_FxAccountCodeParams.setBranchCode("381");
            l_FxAccountCodeParams.setAccountCode("2512246");
            l_FxAccountCodeParams.setFxSystemCode("01");
            l_FxAccountCodeParams.setFxAccountCode("11");
            l_FxAccountCodeParams.setFxCourseDiv("1");
            l_FxAccountCodeParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_FxAccountCodeParams);

            SoapConnectPrefRpcParams l_soapConnectPrefRpcParams = new SoapConnectPrefRpcParams();
            l_soapConnectPrefRpcParams.setBranchId(l_branchParams.getBranchId());
            l_soapConnectPrefRpcParams.setConnectDiv(l_compFxConditionParams.getFxSystemCode());
            l_soapConnectPrefRpcParams.setEndpointName("url");
            l_soapConnectPrefRpcParams.setTargetNamespaceName("0");
            l_soapConnectPrefRpcParams.setOperationName("0");
            l_soapConnectPrefRpcParams.setParameterList("1");
            l_soapConnectPrefRpcParams.setServiceName("1");
            l_soapConnectPrefRpcParams.setPortTypeName("1");
            l_soapConnectPrefRpcParams.setResponseParamType("1");
            l_soapConnectPrefRpcParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_soapConnectPrefRpcParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_soapConnectPrefRpcParams);

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20090321", "yyyyMMdd"));

            WEB3FXTransFromFXInputResponse l_fxTransFromFXInputResponse 
                = (WEB3FXTransFromFXInputResponse)l_impl.execute(l_request);

            assertEquals(l_fxTransFromFXInputResponse.transferCount,"0");
            assertEquals(l_fxTransFromFXInputResponse.transferCountUpper,"3");
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExecute002() 
    {
        final String STR_METHOD_NAME  = "testExecute002";
        log.entering(STR_METHOD_NAME);
        
        WEB3FXTransFromFXInputRequest l_request = new WEB3FXTransFromFXInputRequest();
        l_request.fxSystemCode = "01";
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));

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
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3AioOrderManager", 
                    "validateTransferPossibleCount",
                    new Class[] {SubAccount.class, Date.class, OrderCategEnum.class},
                    new Integer(8));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXDataControlServiceImpl",
                "validateChangePoss",
                new Class[]{ SubAccount.class, CompFxConditionParams.class},
                null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXDataControlServiceImpl", "getHashValue", new Class[]
                { String.class, String.class, String.class },
                "1");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXDataControlServiceImpl", "sendSoapMessage", new Class[]
                 {WEB3FXGftAskingTelegramUnit.class, SoapConnectPrefRpcParams.class, String.class, String.class },
                 WEB3GFTConnectionSystemTest.getSendSyncRequestResponse(1, true,0,0,0,0));
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(AioOrderRow.TYPE);
            TestDBUtility.deleteAllAndCommit(GftTransferStatusRow.TYPE);
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            TestDBUtility.deleteAll(FxAccountRow.TYPE);
            TestDBUtility.deleteAll(FxAccountCodeParams.TYPE);
            TestDBUtility.deleteAll(SoapConnectPrefRpcParams.TYPE);

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
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            AioOrderParams l_aioOrderParams = TestDBUtility.getAioOrderRow();
            l_aioOrderParams.setAccountId(l_subAccountParams.getAccountId());
            l_aioOrderParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_aioOrderParams.setOrderId(1111);
            TestDBUtility.insertWithDel(l_aioOrderParams);

            GftTransferStatusParams l_gftTransferStatusParams = TestDBUtility.getGftTransferStatusRow();
            TestDBUtility.insertWithDelAndCommit(l_gftTransferStatusParams);

            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setFxSystemCode("01");
            l_compFxConditionParams.setFxSystemDiv("2");
            l_compFxConditionParams.setGetTransferableAmtDiv("0");
            l_compFxConditionParams.setExtConnectSystemCode("01");
            l_compFxConditionParams.setMultiCfdAccDiv("0");
            l_compFxConditionParams.setCommonTransferPageDiv(WEB3CommonTransferPageDivDef.NOT_EXECUTE);
            TestDBUtility.insertWithDel(l_compFxConditionParams);


            FxAccountParams l_fxAccountParams = TestDBUtility.getFxAccountRow();
            l_fxAccountParams.setInstitutionCode("0D");
            l_fxAccountParams.setBranchCode("381");
            l_fxAccountParams.setFxSystemCode("01");
            l_fxAccountParams.setAccountCode("2512246");
            TestDBUtility.insertWithDel(l_fxAccountParams);

            FxAccountCodeParams l_FxAccountCodeParams = TestDBUtility.getFxAccountCodeRow();
            l_FxAccountCodeParams.setBranchCode("381");
            l_FxAccountCodeParams.setAccountCode("2512246");
            l_FxAccountCodeParams.setFxSystemCode("01");
            l_FxAccountCodeParams.setFxAccountCode("11");
            l_FxAccountCodeParams.setFxCourseDiv("1");
            l_FxAccountCodeParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_FxAccountCodeParams);

            SoapConnectPrefRpcParams l_soapConnectPrefRpcParams = new SoapConnectPrefRpcParams();
            l_soapConnectPrefRpcParams.setBranchId(l_branchParams.getBranchId());
            l_soapConnectPrefRpcParams.setConnectDiv(l_compFxConditionParams.getFxSystemCode());
            l_soapConnectPrefRpcParams.setEndpointName("url");
            l_soapConnectPrefRpcParams.setTargetNamespaceName("0");
            l_soapConnectPrefRpcParams.setOperationName("0");
            l_soapConnectPrefRpcParams.setParameterList("1");
            l_soapConnectPrefRpcParams.setServiceName("1");
            l_soapConnectPrefRpcParams.setPortTypeName("1");
            l_soapConnectPrefRpcParams.setResponseParamType("1");
            l_soapConnectPrefRpcParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_soapConnectPrefRpcParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_soapConnectPrefRpcParams);

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20090321", "yyyyMMdd"));

            WEB3FXTransFromFXInputResponse l_fxTransFromFXInputResponse 
                = (WEB3FXTransFromFXInputResponse)l_impl.execute(l_request);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExecute003() 
    {
        final String STR_METHOD_NAME  = "testExecute003";
        log.entering(STR_METHOD_NAME);
        
        WEB3FXTransFromFXInputRequest l_request = new WEB3FXTransFromFXInputRequest();
        l_request.fxSystemCode = "01";
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));

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
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.aio.WEB3AioOrderManager", 
                    "validateTransferPossibleCount",
                    new Class[] {SubAccount.class, Date.class, OrderCategEnum.class},
                    new Integer(8));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXDataControlServiceImpl",
                "validateChangePoss",
                new Class[]{ SubAccount.class, CompFxConditionParams.class},
                null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXDataControlServiceImpl", "getHashValue", new Class[]
                { String.class, String.class, String.class },
                "1");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXDataControlServiceImpl", "sendSoapMessage", new Class[]
                 {WEB3FXGftAskingTelegramUnit.class, SoapConnectPrefRpcParams.class, String.class, String.class },
                 WEB3GFTConnectionSystemTest.getSendSyncRequestResponse(1, true,0,0,0,0));
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(AioOrderRow.TYPE);
            TestDBUtility.deleteAllAndCommit(GftTransferStatusRow.TYPE);
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            TestDBUtility.deleteAll(FxAccountRow.TYPE);
            TestDBUtility.deleteAll(FxAccountCodeParams.TYPE);
            TestDBUtility.deleteAll(SoapConnectPrefRpcParams.TYPE);

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
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            AioOrderParams l_aioOrderParams = TestDBUtility.getAioOrderRow();
            l_aioOrderParams.setAccountId(l_subAccountParams.getAccountId());
            l_aioOrderParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_aioOrderParams.setOrderId(1111);
            TestDBUtility.insertWithDel(l_aioOrderParams);

            GftTransferStatusParams l_gftTransferStatusParams = TestDBUtility.getGftTransferStatusRow();
            TestDBUtility.insertWithDelAndCommit(l_gftTransferStatusParams);

            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setFxSystemCode("01");
            l_compFxConditionParams.setFxSystemDiv("2");
            l_compFxConditionParams.setGetTransferableAmtDiv("0");
            l_compFxConditionParams.setExtConnectSystemCode("01");
            l_compFxConditionParams.setMultiCfdAccDiv("0");
            l_compFxConditionParams.setCommonTransferPageDiv(WEB3CommonTransferPageDivDef.EXECUTE);
            TestDBUtility.insertWithDel(l_compFxConditionParams);


            FxAccountParams l_fxAccountParams = TestDBUtility.getFxAccountRow();
            l_fxAccountParams.setInstitutionCode("0D");
            l_fxAccountParams.setBranchCode("381");
            l_fxAccountParams.setFxSystemCode("01");
            l_fxAccountParams.setAccountCode("2512246");
            TestDBUtility.insertWithDel(l_fxAccountParams);

            FxAccountCodeParams l_FxAccountCodeParams = TestDBUtility.getFxAccountCodeRow();
            l_FxAccountCodeParams.setBranchCode("381");
            l_FxAccountCodeParams.setAccountCode("2512246");
            l_FxAccountCodeParams.setFxSystemCode("01");
            l_FxAccountCodeParams.setFxAccountCode("11");
            l_FxAccountCodeParams.setFxCourseDiv("1");
            l_FxAccountCodeParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_FxAccountCodeParams);

            SoapConnectPrefRpcParams l_soapConnectPrefRpcParams = new SoapConnectPrefRpcParams();
            l_soapConnectPrefRpcParams.setBranchId(l_branchParams.getBranchId());
            l_soapConnectPrefRpcParams.setConnectDiv(l_compFxConditionParams.getFxSystemCode());
            l_soapConnectPrefRpcParams.setEndpointName("url");
            l_soapConnectPrefRpcParams.setTargetNamespaceName("0");
            l_soapConnectPrefRpcParams.setOperationName("0");
            l_soapConnectPrefRpcParams.setParameterList("1");
            l_soapConnectPrefRpcParams.setServiceName("1");
            l_soapConnectPrefRpcParams.setPortTypeName("1");
            l_soapConnectPrefRpcParams.setResponseParamType("1");
            l_soapConnectPrefRpcParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_soapConnectPrefRpcParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_soapConnectPrefRpcParams);

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20090321", "yyyyMMdd"));

            WEB3FXTransFromFXInputResponse l_fxTransFromFXInputResponse 
                = (WEB3FXTransFromFXInputResponse)l_impl.execute(l_request);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
