head	1.4;
access;
symbols;
locks; strict;
comment	@// @;


1.4
date	2011.04.08.05.24.32;	author zhang-tengyu;	state Exp;
branches;
next	1.3;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7204d9e9c100cef;
filename	WEB3GFTConnectionSystemTest.java;

1.3
date	2011.04.08.02.18.32;	author zhang-tengyu;	state Exp;
branches;
next	1.2;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6184d9e706b7e68;
filename	WEB3GFTConnectionSystemTest.java;

1.2
date	2011.04.08.00.53.54;	author che-jin;	state Exp;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	666;
commitid	1404d9e5c8b3d81;
filename	WEB3GFTConnectionSystemTest.java;

1.1
date	2011.04.07.01.33.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3GFTConnectionSystemTest.java;


desc
@@


1.4
log
@*** empty log message ***
@
text
@package webbroker3.aio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.xml.ws.Holder;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.gftforex.soap_api.LookupAccountInfo;
import com.gftforex.soap_api.LookupUserInfo;
import com.gftforex.soap_api.RejectedCommand;
import com.gftforex.soap_api.ResultInfoAddAccount;
import com.gftforex.soap_api.ResultInfoBase;
import com.gftforex.soap_api.ResultInfoCreateUser;
import com.gftforex.soap_api.ResultInfoLookupUser;
import com.gftforex.soap_api.SendSyncRequestResponse;

import test.util.TestDBUtility;

import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.data.FxAccountCodeParams;
import webbroker3.aio.define.WEB3AdminAioGftOperationDivDef;
import webbroker3.aio.message.WEB3FXGftAskingTelegramUnit;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.define.WEB3InstitutionPreferencesNameDef;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.InstitutionPreferencesParams;
import webbroker3.gentrade.data.SoapConnectPrefRpcParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3GFTConnectionSystemTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GFTConnectionSystemTest.class);

    WEB3GFTConnectionSystem l_connectionSystem =
        new WEB3GFTConnectionSystem();

    public WEB3GFTConnectionSystemTest(String name)
    {
        super(name);
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

    public void testSendSOAPMessage_Case001()
    {
        final String STR_METHOD_NAME = "testSendSOAPMessage_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit =
                new WEB3FXGftAskingTelegramUnit();
            l_fxGftAskingTelegramUnit.fxAccountCode = "123456";
            l_fxGftAskingTelegramUnit.institutionCode = "0D";
            l_fxGftAskingTelegramUnit.requestNumber = "123";


            TestDBUtility.deleteAll(SoapConnectPrefRpcParams.TYPE);
            SoapConnectPrefRpcParams l_soapConnectPrefRpcParams = new SoapConnectPrefRpcParams();
            l_soapConnectPrefRpcParams.setBranchId(33381);
            l_soapConnectPrefRpcParams.setConnectDiv("01");
            l_soapConnectPrefRpcParams.setEndpointName("http://10.253.111.62:8080/axis2/services/AdministrativeAPI");
            l_soapConnectPrefRpcParams.setTargetNamespaceName("mm");
            l_soapConnectPrefRpcParams.setServiceName("TEST_URL_HTTP");
            l_soapConnectPrefRpcParams.setPortTypeName("ORIX");
            l_soapConnectPrefRpcParams.setOperationName("wP9Gl713r6+2EKVPv10G0i+Uqxmp8keBjKrOjm37pbyukvLerx4DJHeNtDZbEgUU103fD9objXdePRl3Ur7CLw==");
            l_soapConnectPrefRpcParams.setResponseParamType("#");
            l_soapConnectPrefRpcParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_soapConnectPrefRpcParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_soapConnectPrefRpcParams.setResponseTimeout("50");
            TestDBUtility.insertWithDel(l_soapConnectPrefRpcParams);

            WEB3FXGftAskingTelegramUnit l_fXGftAskingTelegramUnit =
                new WEB3FXGftAskingTelegramUnit();
            l_fXGftAskingTelegramUnit.gftOperationDiv = "04";

            String l_strGMT = "20090702124512111";
            String l_strHashValue = "dfds12345few3f15";
            Holder<RejectedCommand> l_rejectedCommand = new Holder<RejectedCommand>();
            Holder<ResultInfoBase> l_sendSyncRequestResult = new Holder<ResultInfoBase>();
            l_connectionSystem.sendSoapMessage(
                    l_fxGftAskingTelegramUnit,
                    l_soapConnectPrefRpcParams,
                    l_strGMT,
                    l_strHashValue,
                    l_rejectedCommand,
                    l_sendSyncRequestResult);
            ResultInfoLookupUser l_lookupuser =
                (ResultInfoLookupUser)l_sendSyncRequestResult.value;
            LookupUserInfo l_lookupUserInfo = l_lookupuser.getUserInfo();
            List<LookupAccountInfo> l_accountInfos =
                l_lookupUserInfo.getAccounts();
            assertEquals(2, l_accountInfos.size());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
//            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
//
//    public void testGetSOAPGFTResultCode_Case001()
//    {
//        final String STR_METHOD_NAME = "testGetSOAPGFTResultCode_Case001()";
//        log.entering(STR_METHOD_NAME);
//        try
//        {
//            SendSyncRequestResponse l_sendSyncRequestResponse =
//                this.getSendSyncRequestResponse(
//                    2, true, 0, 0, 201, 2014);
//            String l_result =
//                l_connectionSystem.getSOAPGFTResultCode(l_sendSyncRequestResponse);
//            assertEquals("00000204", l_result);
//        }
//        catch (Exception l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    public void testGetSOAPGFTResultCode_Case002()
//    {
//        final String STR_METHOD_NAME = "testGetSOAPGFTResultCode_Case002()";
//        log.entering(STR_METHOD_NAME);
//        try
//        {
//            SendSyncRequestResponse l_sendSyncRequestResponse =
//                this.getSendSyncRequestResponse(
//                    2, false, 201, 2014, 0, 0);
//            String l_result =
//                l_connectionSystem.getSOAPGFTResultCode(l_sendSyncRequestResponse);
//            assertEquals("00000204", l_result);
//        }
//        catch (Exception l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.exiting(STR_METHOD_NAME);
//    }

//    public void testGetSOAPGFTResultCode_Case003()
//    {
//        final String STR_METHOD_NAME = "testGetSOAPGFTResultCode_Case003()";
//        log.entering(STR_METHOD_NAME);
//        try
//        {
//            SendSyncRequestResponse l_sendSyncRequestResponse =
//                this.getSendSyncRequestResponse(
//                    2, false, 100, 0, 0, 0);
//            String l_result =
//                l_connectionSystem.getSOAPGFTResultCode(l_sendSyncRequestResponse);
//            assertEquals("00000000", l_result);
//        }
//        catch (Exception l_ex)
//        {
//            log.exiting(STR_METHOD_NAME);
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.exiting(STR_METHOD_NAME);
//    }

    public void testGetGMTtime()
    {
        final String STR_METHOD_NAME = "testGetGMTtime()";
        log.entering(STR_METHOD_NAME);
        try
        {
            String l_strReturn = l_connectionSystem.getGMTtime();
            log.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$the result = " + l_strReturn);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSendMessage_Case001()
    {
        final String STR_METHOD_NAME = "testSendMessage_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit =
                new WEB3FXGftAskingTelegramUnit();
            l_fxGftAskingTelegramUnit.fxAccountCode = "123456";

            TestDBUtility.deleteAll(SoapConnectPrefRpcParams.TYPE);
            SoapConnectPrefRpcParams l_soapConnectPrefRpcParams = new SoapConnectPrefRpcParams();
            l_soapConnectPrefRpcParams.setBranchId(33381);
            l_soapConnectPrefRpcParams.setConnectDiv("01");
            l_soapConnectPrefRpcParams.setEndpointName("http://10.253.111.62:8080/axis2/services/AdministrativeAPI");
            l_soapConnectPrefRpcParams.setTargetNamespaceName("mm");
            l_soapConnectPrefRpcParams.setServiceName("TEST_URL_HTTP");
            l_soapConnectPrefRpcParams.setPortTypeName("ORIX");
            l_soapConnectPrefRpcParams.setOperationName("wP9Gl713r6+2EKVPv10G0i+Uqxmp8keBjKrOjm37pbyukvLerx4DJHeNtDZbEgUU103fD9objXdePRl3Ur7CLw==");
            l_soapConnectPrefRpcParams.setResponseParamType("#");
            l_soapConnectPrefRpcParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_soapConnectPrefRpcParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_soapConnectPrefRpcParams.setResponseTimeout("50");
            TestDBUtility.insertWithDel(l_soapConnectPrefRpcParams);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);

            l_connectionSystem.sendMessage(
                l_fxGftAskingTelegramUnit,
                l_soapConnectPrefRpcParams);

            assertEquals("00000204", (String)l_connectionSystem.getResult("resultCode"));
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
//            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSendMessage_Case002()
    {
        final String STR_METHOD_NAME = "testSendMessage_Case002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit =
                new WEB3FXGftAskingTelegramUnit();
            l_fxGftAskingTelegramUnit.fxAccountCode = "123456";

            TestDBUtility.deleteAll(SoapConnectPrefRpcParams.TYPE);
            SoapConnectPrefRpcParams l_soapConnectPrefRpcParams = new SoapConnectPrefRpcParams();
            l_soapConnectPrefRpcParams.setBranchId(33381);
            l_soapConnectPrefRpcParams.setConnectDiv("01");
            l_soapConnectPrefRpcParams.setEndpointName("http://10.253.111.62:8080/axis2/services/AdministrativeAPI");
            l_soapConnectPrefRpcParams.setTargetNamespaceName("mm");
            l_soapConnectPrefRpcParams.setServiceName("TEST_URL_HTTP");
            l_soapConnectPrefRpcParams.setPortTypeName("ORIX");
            l_soapConnectPrefRpcParams.setOperationName("wP9Gl713r6+2EKVPv10G0i+Uqxmp8keBjKrOjm37pbyukvLerx4DJHeNtDZbEgUU103fD9objXdePRl3Ur7CLw==");
            l_soapConnectPrefRpcParams.setResponseParamType("#");
            l_soapConnectPrefRpcParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_soapConnectPrefRpcParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_soapConnectPrefRpcParams.setResponseTimeout("50");
            TestDBUtility.insertWithDel(l_soapConnectPrefRpcParams);

            //SubAccountRow
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);

            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_subAccountParams.getAccountId());
            TestDBUtility.insertWithDel(l_mainAccountParams);

            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);
            
            //BranchParams
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode(l_mainAccountParams.getBranchCode());
            TestDBUtility.insertWithDel(l_branchParams);
            
            SubAccount l_subAccount =
                new WEB3GentradeSubAccount(l_subAccountParams);

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doDeleteAllQuery(FxAccountCodeParams.TYPE);
            FxAccountCodeParams l_fxAccountCodeParams =
                TestDBUtility.getFxAccountCodeRow();
            l_fxAccountCodeParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            l_fxAccountCodeParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_fxAccountCodeParams.setAccountCode(l_mainAccountParams.getAccountCode());
            l_fxAccountCodeParams.setFxSystemCode(l_soapConnectPrefRpcParams.getConnectDiv());
            l_fxAccountCodeParams.setFxAccountCode("1111");
            l_fxAccountCodeParams.setFxCourseDiv("1");
            l_queryProcessor.doInsertQuery(l_fxAccountCodeParams);

            l_connectionSystem.sendMessage(
                l_fxGftAskingTelegramUnit,
                l_soapConnectPrefRpcParams);
            Object l_result = l_connectionSystem.getResult("Amount");
            //resultCode
            HashMap l_hmAmount = (HashMap)l_result;
            assertEquals("100", (String)l_hmAmount.get("1"));
            assertEquals("00000000", (String)l_connectionSystem.getResult("resultCode"));
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
//            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSendMessage_Case003()
    {
        final String STR_METHOD_NAME = "testSendMessage_Case003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit =
                new WEB3FXGftAskingTelegramUnit();
            l_fxGftAskingTelegramUnit.fxAccountCode = "123456";

            TestDBUtility.deleteAll(SoapConnectPrefRpcParams.TYPE);
            SoapConnectPrefRpcParams l_soapConnectPrefRpcParams = new SoapConnectPrefRpcParams();
            l_soapConnectPrefRpcParams.setBranchId(33381);
            l_soapConnectPrefRpcParams.setConnectDiv("01");
            l_soapConnectPrefRpcParams.setEndpointName("http://10.253.111.62:8080/axis2/services/AdministrativeAPI");
            l_soapConnectPrefRpcParams.setTargetNamespaceName("mm");
            l_soapConnectPrefRpcParams.setServiceName("TEST_URL_HTTP");
            l_soapConnectPrefRpcParams.setPortTypeName("ORIX");
            l_soapConnectPrefRpcParams.setOperationName("wP9Gl713r6+2EKVPv10G0i+Uqxmp8keBjKrOjm37pbyukvLerx4DJHeNtDZbEgUU103fD9objXdePRl3Ur7CLw==");
            l_soapConnectPrefRpcParams.setResponseParamType("#");
            l_soapConnectPrefRpcParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_soapConnectPrefRpcParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_soapConnectPrefRpcParams.setResponseTimeout("50");
            TestDBUtility.insertWithDel(l_soapConnectPrefRpcParams);

            //SubAccountRow
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);

            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_subAccountParams.getAccountId());
            TestDBUtility.insertWithDel(l_mainAccountParams);

            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);
            
            //BranchParams
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode(l_mainAccountParams.getBranchCode());
            TestDBUtility.insertWithDel(l_branchParams);
            
            SubAccount l_subAccount =
                new WEB3GentradeSubAccount(l_subAccountParams);

            //FxAccountCodeParams
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doDeleteAllQuery(FxAccountCodeParams.TYPE);
            FxAccountCodeParams l_fxAccountCodeParams =
                TestDBUtility.getFxAccountCodeRow();
            l_fxAccountCodeParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            l_fxAccountCodeParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_fxAccountCodeParams.setAccountCode(l_mainAccountParams.getAccountCode());
            l_fxAccountCodeParams.setFxSystemCode(l_soapConnectPrefRpcParams.getConnectDiv());
            l_fxAccountCodeParams.setFxAccountCode("1111");
            l_fxAccountCodeParams.setFxCourseDiv("1");
            l_queryProcessor.doInsertQuery(l_fxAccountCodeParams);

            l_fxAccountCodeParams.setFxCourseDiv("2");
            l_fxAccountCodeParams.setFxAccountCode("2222");
            l_queryProcessor.doInsertQuery(l_fxAccountCodeParams);
            
            l_connectionSystem.sendMessage(
                l_fxGftAskingTelegramUnit,
                l_soapConnectPrefRpcParams);
            Object l_result = l_connectionSystem.getResult("Amount");
            //resultCode
            HashMap l_hmAmount = (HashMap)l_result;
            assertEquals("100", (String)l_hmAmount.get("1"));
            assertEquals("200", (String)l_hmAmount.get("2"));
            assertEquals("00000000", (String)l_connectionSystem.getResult("resultCode"));
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
//            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSendMessage_Case004()
    {
        final String STR_METHOD_NAME = "testSendMessage_Case004()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit =
                new WEB3FXGftAskingTelegramUnit();
            l_fxGftAskingTelegramUnit.fxAccountCode = "123456";

            TestDBUtility.deleteAll(SoapConnectPrefRpcParams.TYPE);
            SoapConnectPrefRpcParams l_soapConnectPrefRpcParams = new SoapConnectPrefRpcParams();
            l_soapConnectPrefRpcParams.setBranchId(33381);
            l_soapConnectPrefRpcParams.setConnectDiv("01");
            l_soapConnectPrefRpcParams.setEndpointName("http://10.253.111.62:8080/axis2/services/AdministrativeAPI");
            l_soapConnectPrefRpcParams.setTargetNamespaceName("mm");
            l_soapConnectPrefRpcParams.setServiceName("TEST_URL_HTTP");
            l_soapConnectPrefRpcParams.setPortTypeName("ORIX");
            l_soapConnectPrefRpcParams.setOperationName("wP9Gl713r6+2EKVPv10G0i+Uqxmp8keBjKrOjm37pbyukvLerx4DJHeNtDZbEgUU103fD9objXdePRl3Ur7CLw==");
            l_soapConnectPrefRpcParams.setResponseParamType("#");
            l_soapConnectPrefRpcParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_soapConnectPrefRpcParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_soapConnectPrefRpcParams.setResponseTimeout("50");
            TestDBUtility.insertWithDel(l_soapConnectPrefRpcParams);
            //SubAccountRow
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);

            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_subAccountParams.getAccountId());
            TestDBUtility.insertWithDel(l_mainAccountParams);

            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);
            
            //BranchParams
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode(l_mainAccountParams.getBranchCode());
            TestDBUtility.insertWithDel(l_branchParams);
            
            SubAccount l_subAccount =
                new WEB3GentradeSubAccount(l_subAccountParams);

            //FxAccountCodeParams
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doDeleteAllQuery(FxAccountCodeParams.TYPE);
            FxAccountCodeParams l_fxAccountCodeParams =
                TestDBUtility.getFxAccountCodeRow();
            l_fxAccountCodeParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            l_fxAccountCodeParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_fxAccountCodeParams.setAccountCode(l_mainAccountParams.getAccountCode());
            l_fxAccountCodeParams.setFxSystemCode(l_soapConnectPrefRpcParams.getConnectDiv());
            l_fxAccountCodeParams.setFxAccountCode("1111");
            l_fxAccountCodeParams.setFxCourseDiv("1");
            l_queryProcessor.doInsertQuery(l_fxAccountCodeParams);

            l_fxAccountCodeParams.setFxCourseDiv("2");
            l_fxAccountCodeParams.setFxAccountCode("2222");
            l_queryProcessor.doInsertQuery(l_fxAccountCodeParams);
            
            l_connectionSystem.sendMessage(
                l_fxGftAskingTelegramUnit,
                l_soapConnectPrefRpcParams);
            Object l_result = l_connectionSystem.getResult("Amount");
            assertNull(l_result);
            assertNull(l_result);
            assertEquals("00000000", (String)l_connectionSystem.getResult("resultCode"));
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
//            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testSendMessage_Case005()
    {
        final String STR_METHOD_NAME = "testSendMessage_Case005()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit =
                new WEB3FXGftAskingTelegramUnit();
            l_fxGftAskingTelegramUnit.fxAccountCode = "123456";

            TestDBUtility.deleteAll(SoapConnectPrefRpcParams.TYPE);
            SoapConnectPrefRpcParams l_soapConnectPrefRpcParams = new SoapConnectPrefRpcParams();
            l_soapConnectPrefRpcParams.setBranchId(33381);
            l_soapConnectPrefRpcParams.setConnectDiv("01");
            l_soapConnectPrefRpcParams.setEndpointName("http://10.253.111.62:8080/axis2/services/AdministrativeAPI");
            l_soapConnectPrefRpcParams.setTargetNamespaceName("mm");
            l_soapConnectPrefRpcParams.setServiceName("TEST_URL_HTTP");
            l_soapConnectPrefRpcParams.setPortTypeName("ORIX");
            l_soapConnectPrefRpcParams.setOperationName("wP9Gl713r6+2EKVPv10G0i+Uqxmp8keBjKrOjm37pbyukvLerx4DJHeNtDZbEgUU103fD9objXdePRl3Ur7CLw==");
            l_soapConnectPrefRpcParams.setResponseParamType("#");
            l_soapConnectPrefRpcParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_soapConnectPrefRpcParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_soapConnectPrefRpcParams.setResponseTimeout("50");
            TestDBUtility.insertWithDel(l_soapConnectPrefRpcParams);
            //SubAccountRow
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);

            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_subAccountParams.getAccountId());
            TestDBUtility.insertWithDel(l_mainAccountParams);

            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);
            
            //BranchParams
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode(l_mainAccountParams.getBranchCode());
            TestDBUtility.insertWithDel(l_branchParams);
            
            //FxAccountCodeParams
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doDeleteAllQuery(FxAccountCodeParams.TYPE);
            FxAccountCodeParams l_fxAccountCodeParams =
                TestDBUtility.getFxAccountCodeRow();
            l_fxAccountCodeParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            l_fxAccountCodeParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_fxAccountCodeParams.setAccountCode(l_mainAccountParams.getAccountCode());
            l_fxAccountCodeParams.setFxSystemCode(l_soapConnectPrefRpcParams.getConnectDiv());
            l_fxAccountCodeParams.setFxAccountCode("2222");
            l_fxAccountCodeParams.setFxCourseDiv("2");
            l_queryProcessor.doInsertQuery(l_fxAccountCodeParams);
            
            SubAccount l_subAccount =
                new WEB3GentradeSubAccount(l_subAccountParams);

            l_connectionSystem.sendMessage(
                l_fxGftAskingTelegramUnit,
                l_soapConnectPrefRpcParams);
            Object l_result = l_connectionSystem.getResult("Amount");
            HashMap l_hmAmount = (HashMap)l_result;
            assertEquals("200", (String)l_hmAmount.get("2"));
            assertEquals("00000000", (String)l_connectionSystem.getResult("resultCode"));
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
//            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSendMessage_Case006()
    {
        final String STR_METHOD_NAME = "testSendMessage_Case006()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit =
                new WEB3FXGftAskingTelegramUnit();
            l_fxGftAskingTelegramUnit.fxAccountCode = "123456";
            TestDBUtility.deleteAll(SoapConnectPrefRpcParams.TYPE);
            SoapConnectPrefRpcParams l_soapConnectPrefRpcParams = new SoapConnectPrefRpcParams();
            l_soapConnectPrefRpcParams.setBranchId(33381);
            l_soapConnectPrefRpcParams.setConnectDiv("01");
            l_soapConnectPrefRpcParams.setEndpointName("http://10.253.111.62:8080/axis2/services/AdministrativeAPI");
            l_soapConnectPrefRpcParams.setTargetNamespaceName("mm");
            l_soapConnectPrefRpcParams.setServiceName("TEST_URL_HTTP");
            l_soapConnectPrefRpcParams.setPortTypeName("ORIX");
            l_soapConnectPrefRpcParams.setOperationName("wP9Gl713r6+2EKVPv10G0i+Uqxmp8keBjKrOjm37pbyukvLerx4DJHeNtDZbEgUU103fD9objXdePRl3Ur7CLw==");
            l_soapConnectPrefRpcParams.setResponseParamType("#");
            l_soapConnectPrefRpcParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_soapConnectPrefRpcParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_soapConnectPrefRpcParams.setResponseTimeout("50");
            TestDBUtility.insertWithDel(l_soapConnectPrefRpcParams);
            //SubAccountRow
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);

            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_subAccountParams.getAccountId());
            TestDBUtility.insertWithDel(l_mainAccountParams);

            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);
            
            //BranchParams
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode(l_mainAccountParams.getBranchCode());
            TestDBUtility.insertWithDel(l_branchParams);
            
            SubAccount l_subAccount =
                new WEB3GentradeSubAccount(l_subAccountParams);

            //FxAccountCodeParams
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doDeleteAllQuery(FxAccountCodeParams.TYPE);
            FxAccountCodeParams l_fxAccountCodeParams =
                TestDBUtility.getFxAccountCodeRow();
            l_fxAccountCodeParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            l_fxAccountCodeParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_fxAccountCodeParams.setAccountCode(l_mainAccountParams.getAccountCode());
            l_fxAccountCodeParams.setFxSystemCode(l_soapConnectPrefRpcParams.getConnectDiv());
            l_fxAccountCodeParams.setFxAccountCode("1111");
            l_fxAccountCodeParams.setFxCourseDiv("1");
            l_queryProcessor.doInsertQuery(l_fxAccountCodeParams);

            l_fxAccountCodeParams.setFxCourseDiv("2");
            l_queryProcessor.doInsertQuery(l_fxAccountCodeParams);
            
            l_fxAccountCodeParams.setFxAccountCode("2222");
            l_fxAccountCodeParams.setFxCourseDiv("3");
            l_queryProcessor.doInsertQuery(l_fxAccountCodeParams);

            l_connectionSystem.sendMessage(
                l_fxGftAskingTelegramUnit,
                l_soapConnectPrefRpcParams);

            Object l_result = l_connectionSystem.getResult("Amount");
            HashMap l_hmAmount = (HashMap)l_result;
            assertEquals("200", (String)l_hmAmount.get("3"));
            assertEquals("00000000", (String)l_connectionSystem.getResult("resultCode"));
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
//            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSendMessage_Case007()
    {
        final String STR_METHOD_NAME = "testSendMessage_Case007()";
        log.entering(STR_METHOD_NAME);
        try
        {         
            TestDBUtility.deleteAll(SoapConnectPrefRpcParams.TYPE);
            SoapConnectPrefRpcParams l_soapConnectPrefRpcParams = new SoapConnectPrefRpcParams();
            l_soapConnectPrefRpcParams.setBranchId(15100);
            l_soapConnectPrefRpcParams.setConnectDiv("01");
            l_soapConnectPrefRpcParams.setEndpointName("http://10.253.111.62:8080/axis2/services/AdministrativeAPI");
            l_soapConnectPrefRpcParams.setTargetNamespaceName("#");
            l_soapConnectPrefRpcParams.setServiceName("TEST_URL_HTTP");
            l_soapConnectPrefRpcParams.setPortTypeName("ORIX");
            l_soapConnectPrefRpcParams.setOperationName("wP9Gl713r6+2EKVPv10G0i+Uqxmp8keBjKrOjm37pbyukvLerx4DJHeNtDZbEgUU103fD9objXdePRl3Ur7CLw==");
            l_soapConnectPrefRpcParams.setResponseParamType("#");
            l_soapConnectPrefRpcParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_soapConnectPrefRpcParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_soapConnectPrefRpcParams.setResponseTimeout("50");
            l_soapConnectPrefRpcParams.setParameterList("orix_ut");
            l_soapConnectPrefRpcParams.setParameterTypeList("orix_secondary_at:orix_primary_at");
            TestDBUtility.insertWithDel(l_soapConnectPrefRpcParams);
            //SubAccountRow
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);

            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_subAccountParams.getAccountId());
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);
            
            //BranchParams
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode(l_mainAccountParams.getBranchCode());
            TestDBUtility.insertWithDel(l_branchParams);

            WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit =
                new WEB3FXGftAskingTelegramUnit();
            l_fxGftAskingTelegramUnit.accountCode = "1611020";
            l_fxGftAskingTelegramUnit.institutionCode = l_subAccountParams.getInstitutionCode();
            l_fxGftAskingTelegramUnit.requestNumber = "1000001";
            l_fxGftAskingTelegramUnit.gftOperationDiv = WEB3AdminAioGftOperationDivDef.ACCOUNT_OPEN;
            l_fxGftAskingTelegramUnit.branchCode = "110";
            l_fxGftAskingTelegramUnit.wolfSession = "161102011111";
            l_fxGftAskingTelegramUnit.wolfAid = "161102022222";
            l_fxGftAskingTelegramUnit.regetServiceId = "161102033333";
            l_fxGftAskingTelegramUnit.wolfSsid = "000";
            l_fxGftAskingTelegramUnit.fxMailAddress = "t12@@dir.co.jp";
            l_fxGftAskingTelegramUnit.fxFirstLoginId = "307161102";
            l_fxGftAskingTelegramUnit.fxFirstPassword = "aaaaaa";
            l_fxGftAskingTelegramUnit.fxAccountCode = "111111";
            
            //CompFxConditionParams
            TestDBUtility.deleteAll(CompFxConditionParams.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("110");
            l_compFxConditionParams.setFxSystemCode("01");
//            l_compFxConditionParams.setMultiCfdAccDiv("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);
            
            //InstitutionPreferencesParams
            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams =
                TestDBUtility.getInstitutionPreferencesRow();
            l_institutionPreferencesParams.setInstitutionId(l_institutionParams.getInstitutionId());
            l_institutionPreferencesParams.setName(WEB3InstitutionPreferencesNameDef.GFT_ACCOUNTOPEN_FXSYSTEMCODE);
            TestDBUtility.insertWithDel(l_institutionPreferencesParams);
            
            //FxAccountCodeParams
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doDeleteAllQuery(FxAccountCodeParams.TYPE);
            FxAccountCodeParams l_fxAccountCodeParams =
                TestDBUtility.getFxAccountCodeRow();
            l_fxAccountCodeParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            l_fxAccountCodeParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_fxAccountCodeParams.setAccountCode(l_mainAccountParams.getAccountCode());
            l_fxAccountCodeParams.setFxSystemCode(l_soapConnectPrefRpcParams.getConnectDiv());
            l_fxAccountCodeParams.setFxAccountCode("1111");
            l_fxAccountCodeParams.setFxCourseDiv("1");
            l_queryProcessor.doInsertQuery(l_fxAccountCodeParams);

            l_fxAccountCodeParams.setFxCourseDiv("2");
            l_queryProcessor.doInsertQuery(l_fxAccountCodeParams);
            
            l_fxAccountCodeParams.setFxAccountCode("2222");
            l_fxAccountCodeParams.setFxCourseDiv("3");
            l_queryProcessor.doInsertQuery(l_fxAccountCodeParams);

            WEB3GFTConnectionSystemForTest l_connectionSystemForTest =
                new WEB3GFTConnectionSystemForTest();
            
            l_connectionSystemForTest.sendMessage(
                l_fxGftAskingTelegramUnit,
                l_soapConnectPrefRpcParams);

            assertEquals("111111", l_connectionSystemForTest.getResult(WEB3ExtConnection.FX_ACC_01).toString());
            assertEquals("222222", l_connectionSystemForTest.getResult(WEB3ExtConnection.FX_ACC_10).toString());
            assertEquals("333333", l_connectionSystemForTest.getResult(WEB3ExtConnection.CFD_ACC).toString());
//            assertEquals("20002", l_connectionSystemForTest.getResult(WEB3ExtConnection.CFD_ACC2).toString());
//            assertEquals("20003", l_connectionSystemForTest.getResult(WEB3ExtConnection.CFD_ACC3).toString());
//            assertEquals("20004", l_connectionSystemForTest.getResult(WEB3ExtConnection.CFD_ACC4).toString());
//            assertEquals("20005", l_connectionSystemForTest.getResult(WEB3ExtConnection.CFD_ACC5).toString());
//            assertEquals("20006", l_connectionSystemForTest.getResult(WEB3ExtConnection.CFD_ACC6).toString());
//            assertEquals("20007", l_connectionSystemForTest.getResult(WEB3ExtConnection.CFD_ACC7).toString());
//            assertEquals("20008", l_connectionSystemForTest.getResult(WEB3ExtConnection.CFD_ACC8).toString());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSendMessage_Case008()
    {
        final String STR_METHOD_NAME = "testSendMessage_Case008()";
        log.entering(STR_METHOD_NAME);
        try
        {         
            TestDBUtility.deleteAll(SoapConnectPrefRpcParams.TYPE);
            SoapConnectPrefRpcParams l_soapConnectPrefRpcParams = new SoapConnectPrefRpcParams();
            l_soapConnectPrefRpcParams.setBranchId(15100);
            l_soapConnectPrefRpcParams.setConnectDiv("01");
            l_soapConnectPrefRpcParams.setEndpointName("http://10.253.111.62:8080/axis2/services/AdministrativeAPI");
            l_soapConnectPrefRpcParams.setTargetNamespaceName("#");
            l_soapConnectPrefRpcParams.setServiceName("TEST_URL_HTTP");
            l_soapConnectPrefRpcParams.setPortTypeName("ORIX");
            l_soapConnectPrefRpcParams.setOperationName("wP9Gl713r6+2EKVPv10G0i+Uqxmp8keBjKrOjm37pbyukvLerx4DJHeNtDZbEgUU103fD9objXdePRl3Ur7CLw==");
            l_soapConnectPrefRpcParams.setResponseParamType("#");
            l_soapConnectPrefRpcParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_soapConnectPrefRpcParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_soapConnectPrefRpcParams.setResponseTimeout("50");
            l_soapConnectPrefRpcParams.setParameterList("orix_ut");
            l_soapConnectPrefRpcParams.setParameterTypeList("orix_secondary_at:orix_primary_at");
            TestDBUtility.insertWithDel(l_soapConnectPrefRpcParams);
            //SubAccountRow
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);

            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_subAccountParams.getAccountId());
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);
            
            //BranchParams
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode(l_mainAccountParams.getBranchCode());
            TestDBUtility.insertWithDel(l_branchParams);

            WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit =
                new WEB3FXGftAskingTelegramUnit();
            l_fxGftAskingTelegramUnit.accountCode = "1611020";
            l_fxGftAskingTelegramUnit.institutionCode = l_subAccountParams.getInstitutionCode();
            l_fxGftAskingTelegramUnit.requestNumber = "1000001";
            l_fxGftAskingTelegramUnit.gftOperationDiv = WEB3AdminAioGftOperationDivDef.ACCOUNT_OPEN;
            l_fxGftAskingTelegramUnit.branchCode = "110";
            l_fxGftAskingTelegramUnit.wolfSession = "161102011111";
            l_fxGftAskingTelegramUnit.wolfAid = "161102022222";
            l_fxGftAskingTelegramUnit.regetServiceId = "161102033333";
            l_fxGftAskingTelegramUnit.wolfSsid = "000";
            l_fxGftAskingTelegramUnit.fxMailAddress = "t12@@dir.co.jp";
            l_fxGftAskingTelegramUnit.fxFirstLoginId = "307161102";
            l_fxGftAskingTelegramUnit.fxFirstPassword = "aaaaaa";
            l_fxGftAskingTelegramUnit.fxAccountCode = "111111";
            
            //CompFxConditionParams
            TestDBUtility.deleteAll(CompFxConditionParams.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("110");
            l_compFxConditionParams.setFxSystemCode("01");
//            l_compFxConditionParams.setMultiCfdAccDiv("1");
            l_compFxConditionParams.setFxSystemDiv("2");
            TestDBUtility.insertWithDel(l_compFxConditionParams);
            
            //InstitutionPreferencesParams
            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams =
                TestDBUtility.getInstitutionPreferencesRow();
            TestDBUtility.insertWithDel(l_institutionPreferencesParams);
            
            //FxAccountCodeParams
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doDeleteAllQuery(FxAccountCodeParams.TYPE);
            FxAccountCodeParams l_fxAccountCodeParams =
                TestDBUtility.getFxAccountCodeRow();
            l_fxAccountCodeParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            l_fxAccountCodeParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_fxAccountCodeParams.setAccountCode(l_mainAccountParams.getAccountCode());
            l_fxAccountCodeParams.setFxSystemCode(l_soapConnectPrefRpcParams.getConnectDiv());
            l_fxAccountCodeParams.setFxAccountCode("1111");
            l_fxAccountCodeParams.setFxCourseDiv("1");
            l_queryProcessor.doInsertQuery(l_fxAccountCodeParams);

            l_fxAccountCodeParams.setFxCourseDiv("2");
            l_queryProcessor.doInsertQuery(l_fxAccountCodeParams);
            
            l_fxAccountCodeParams.setFxAccountCode("2222");
            l_fxAccountCodeParams.setFxCourseDiv("3");
            l_queryProcessor.doInsertQuery(l_fxAccountCodeParams);

            WEB3GFTConnectionSystemForTest l_connectionSystemForTest =
                new WEB3GFTConnectionSystemForTest();
            
            l_connectionSystemForTest.sendMessage(
                l_fxGftAskingTelegramUnit,
                l_soapConnectPrefRpcParams);

            assertEquals("111111", l_connectionSystemForTest.getResult(WEB3ExtConnection.CFD_ACC).toString());
//            assertEquals("20002", l_connectionSystemForTest.getResult(WEB3ExtConnection.CFD_ACC2).toString());
//            assertEquals("20003", l_connectionSystemForTest.getResult(WEB3ExtConnection.CFD_ACC3).toString());
//            assertEquals("20004", l_connectionSystemForTest.getResult(WEB3ExtConnection.CFD_ACC4).toString());
//            assertEquals("20005", l_connectionSystemForTest.getResult(WEB3ExtConnection.CFD_ACC5).toString());
//            assertEquals("20006", l_connectionSystemForTest.getResult(WEB3ExtConnection.CFD_ACC6).toString());
//            assertEquals("20007", l_connectionSystemForTest.getResult(WEB3ExtConnection.CFD_ACC7).toString());
//            assertEquals("20008", l_connectionSystemForTest.getResult(WEB3ExtConnection.CFD_ACC8).toString());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSendMessage_Case009()
    {
        final String STR_METHOD_NAME = "testSendMessage_Case009()";
        log.entering(STR_METHOD_NAME);
        try
        {         
            TestDBUtility.deleteAll(SoapConnectPrefRpcParams.TYPE);
            SoapConnectPrefRpcParams l_soapConnectPrefRpcParams = new SoapConnectPrefRpcParams();
            l_soapConnectPrefRpcParams.setBranchId(15100);
            l_soapConnectPrefRpcParams.setConnectDiv("01");
            l_soapConnectPrefRpcParams.setEndpointName("http://10.253.111.62:8080/axis2/services/AdministrativeAPI");
            l_soapConnectPrefRpcParams.setTargetNamespaceName("#");
            l_soapConnectPrefRpcParams.setServiceName("TEST_URL_HTTP");
            l_soapConnectPrefRpcParams.setPortTypeName("ORIX");
            l_soapConnectPrefRpcParams.setOperationName("wP9Gl713r6+2EKVPv10G0i+Uqxmp8keBjKrOjm37pbyukvLerx4DJHeNtDZbEgUU103fD9objXdePRl3Ur7CLw==");
            l_soapConnectPrefRpcParams.setResponseParamType("#");
            l_soapConnectPrefRpcParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_soapConnectPrefRpcParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_soapConnectPrefRpcParams.setResponseTimeout("50");
            l_soapConnectPrefRpcParams.setParameterList("orix_ut");
            l_soapConnectPrefRpcParams.setParameterTypeList("orix_secondary_at:orix_primary_at");
            TestDBUtility.insertWithDel(l_soapConnectPrefRpcParams);
            //SubAccountRow
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);

            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_subAccountParams.getAccountId());
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_institutionParams);
            
            //BranchParams
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode(l_mainAccountParams.getBranchCode());
            TestDBUtility.insertWithDel(l_branchParams);

            WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit =
                new WEB3FXGftAskingTelegramUnit();
            l_fxGftAskingTelegramUnit.accountCode = "1611020";
            l_fxGftAskingTelegramUnit.institutionCode = l_subAccountParams.getInstitutionCode();
            l_fxGftAskingTelegramUnit.requestNumber = "1000001";
            l_fxGftAskingTelegramUnit.gftOperationDiv = WEB3AdminAioGftOperationDivDef.ADD_ACCOUNT;
            l_fxGftAskingTelegramUnit.branchCode = "110";
            l_fxGftAskingTelegramUnit.wolfSession = "161102011111";
            l_fxGftAskingTelegramUnit.wolfAid = "161102022222";
            l_fxGftAskingTelegramUnit.regetServiceId = "161102033333";
            l_fxGftAskingTelegramUnit.wolfSsid = "000";
            l_fxGftAskingTelegramUnit.fxMailAddress = "t12@@dir.co.jp";
            l_fxGftAskingTelegramUnit.fxFirstLoginId = "307161102";
            l_fxGftAskingTelegramUnit.fxFirstPassword = "aaaaaa";
            l_fxGftAskingTelegramUnit.fxAccountCode = "111111";
            
            //CompFxConditionParams
            TestDBUtility.deleteAll(CompFxConditionParams.TYPE);
            CompFxConditionParams l_compFxConditionParams =
                TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode("0D");
            l_compFxConditionParams.setBranchCode("110");
            l_compFxConditionParams.setFxSystemCode("01");
//            l_compFxConditionParams.setMultiCfdAccDiv("1");
            l_compFxConditionParams.setFxSystemDiv("2");
            TestDBUtility.insertWithDel(l_compFxConditionParams);
            
            //InstitutionPreferencesParams
            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
            InstitutionPreferencesParams l_institutionPreferencesParams =
                TestDBUtility.getInstitutionPreferencesRow();
            TestDBUtility.insertWithDel(l_institutionPreferencesParams);
            
            //FxAccountCodeParams
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doDeleteAllQuery(FxAccountCodeParams.TYPE);
            FxAccountCodeParams l_fxAccountCodeParams =
                TestDBUtility.getFxAccountCodeRow();
            l_fxAccountCodeParams.setInstitutionCode(l_subAccountParams.getInstitutionCode());
            l_fxAccountCodeParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_fxAccountCodeParams.setAccountCode(l_mainAccountParams.getAccountCode());
            l_fxAccountCodeParams.setFxSystemCode(l_soapConnectPrefRpcParams.getConnectDiv());
            l_fxAccountCodeParams.setFxAccountCode("1111");
            l_fxAccountCodeParams.setFxCourseDiv("1");
            l_queryProcessor.doInsertQuery(l_fxAccountCodeParams);

            l_fxAccountCodeParams.setFxCourseDiv("2");
            l_queryProcessor.doInsertQuery(l_fxAccountCodeParams);
            
            l_fxAccountCodeParams.setFxAccountCode("2222");
            l_fxAccountCodeParams.setFxCourseDiv("3");
            l_queryProcessor.doInsertQuery(l_fxAccountCodeParams);

            WEB3GFTConnectionSystemForTest l_connectionSystemForTest =
                new WEB3GFTConnectionSystemForTest();
            
            l_connectionSystemForTest.sendMessage(
                l_fxGftAskingTelegramUnit,
                l_soapConnectPrefRpcParams);

            assertEquals("100000", l_connectionSystemForTest.getResult(WEB3ExtConnection.CFD_ACC).toString());
//            assertEquals("20002", l_connectionSystemForTest.getResult(WEB3ExtConnection.CFD_ACC2).toString());
//            assertEquals("20003", l_connectionSystemForTest.getResult(WEB3ExtConnection.CFD_ACC3).toString());
//            assertEquals("20004", l_connectionSystemForTest.getResult(WEB3ExtConnection.CFD_ACC4).toString());
//            assertEquals("20005", l_connectionSystemForTest.getResult(WEB3ExtConnection.CFD_ACC5).toString());
//            assertEquals("20006", l_connectionSystemForTest.getResult(WEB3ExtConnection.CFD_ACC6).toString());
//            assertEquals("20007", l_connectionSystemForTest.getResult(WEB3ExtConnection.CFD_ACC7).toString());
//            assertEquals("20008", l_connectionSystemForTest.getResult(WEB3ExtConnection.CFD_ACC8).toString());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public static SendSyncRequestResponse getSendSyncRequestResponse(
        int count, boolean l_isNullRejectedCommand,
        int majorStatusCode, int minorStatusCode,
        int majorErrorCode, int minorErrorCode)
    {
        SendSyncRequestResponse l_sendSyncRequestResponse =
            new SendSyncRequestResponse();
        ResultInfoLookupUser l_lookupUser = new ResultInfoLookupUser();

        //LookupAccountInfo
        List<LookupAccountInfo> l_accountInfos = new ArrayList<LookupAccountInfo>();

        if (count == 2)
        {
        	LookupAccountInfo l_LookupAccountInfo = new LookupAccountInfo();
        	l_LookupAccountInfo.setAccountId(1111);
        	l_LookupAccountInfo.setWithdrawableAmount(100);
            
            l_accountInfos.add(l_LookupAccountInfo);
            
            LookupAccountInfo l_LookupAccountInfo1 = new LookupAccountInfo();
        	l_LookupAccountInfo1.setAccountId(2222);
        	l_LookupAccountInfo1.setWithdrawableAmount(200);
        	
            l_accountInfos.add(l_LookupAccountInfo1);
        }
        else if (count == 1)
        {
        	LookupAccountInfo l_LookupAccountInfo = new LookupAccountInfo();
        	l_LookupAccountInfo.setAccountId(1111);
        	l_LookupAccountInfo.setWithdrawableAmount(100);
            
            l_accountInfos.add(l_LookupAccountInfo);
        }

        if (l_isNullRejectedCommand)
        {
            RejectedCommand l_rejectedCommand =
                new RejectedCommand();
            l_rejectedCommand.setMajorErrorCode(majorErrorCode);
            l_rejectedCommand.setMajorErrorMessage("");
            l_rejectedCommand.setMinorErrorCode(minorErrorCode);
            l_rejectedCommand.setMinorErrorMessage("");
            l_sendSyncRequestResponse.setRejectedCommand(l_rejectedCommand);
        }

        LookupUserInfo l_lookupUserInfo = new LookupUserInfo();
        
        if (count != 0)
        {
            l_lookupUserInfo.setAccounts(l_accountInfos);
        }

        l_lookupUser.setUserInfo(l_lookupUserInfo);
        l_lookupUser.setStatusMessage("Normal");
        l_lookupUser.setMajorStatusCode(majorStatusCode);
        l_lookupUser.setMinorStatusCode(minorStatusCode);

        l_sendSyncRequestResponse.setSendSyncRequestResult(l_lookupUser);
        return l_sendSyncRequestResponse;
    }
    
    public static SendSyncRequestResponse getSendSyncRequestResponseForTest(
            int count, boolean l_isNullRejectedCommand,
            int majorStatusCode, int minorStatusCode,
            int majorErrorCode, int minorErrorCode)
        {
            SendSyncRequestResponse l_sendSyncRequestResponse =
                new SendSyncRequestResponse();

//            long[] l_lngAccountIds_10 = {
//                10001,
//                10010,
//                20001,
//                20002,
//                20003,
//                20004,
//                20005,
//                20006,
//                20007,
//                20008};
//            
//            long[] l_lngAccountIds_8 = {
//                    20001,
//                    20002,
//                    20003,
//                    20004,
//                    20005,
//                    20006,
//                    20007,
//                    20008};
            List<Long> l_lngAccountIds_10 = new ArrayList<Long>();
            l_lngAccountIds_10.add(new Long(10001));
            l_lngAccountIds_10.add(new Long(10010));
            l_lngAccountIds_10.add(new Long(20001));
            l_lngAccountIds_10.add(new Long(20002));
            l_lngAccountIds_10.add(new Long(20003));
            l_lngAccountIds_10.add(new Long(20004));
            l_lngAccountIds_10.add(new Long(20005));
            l_lngAccountIds_10.add(new Long(20006));
            l_lngAccountIds_10.add(new Long(20007));
            l_lngAccountIds_10.add(new Long(20008));
            List<Long> l_lngAccountIds_8= new ArrayList<Long>();
            l_lngAccountIds_8.add(new Long(20001));
            l_lngAccountIds_8.add(new Long(20002));
            l_lngAccountIds_8.add(new Long(20003));
            l_lngAccountIds_8.add(new Long(20004));
            l_lngAccountIds_8.add(new Long(20005));
            l_lngAccountIds_8.add(new Long(20006));
            l_lngAccountIds_8.add(new Long(20007));
            l_lngAccountIds_8.add(new Long(20008));
            //ResultInfoCreateUser
            ResultInfoCreateUser l_resultInfoCreateUser = new ResultInfoCreateUser();
            if (count == 10)
            {                
                l_resultInfoCreateUser.setAccountIds(l_lngAccountIds_10);
            }
            else if (count == 8)
            {                
                l_resultInfoCreateUser.setAccountIds(l_lngAccountIds_8);
            }

            if (l_isNullRejectedCommand)
            {
                RejectedCommand l_rejectedCommand =
                    new RejectedCommand();
                l_rejectedCommand.setMajorErrorCode(majorErrorCode);
                l_rejectedCommand.setMajorErrorMessage("");
                l_rejectedCommand.setMinorErrorCode(minorErrorCode);
                l_rejectedCommand.setMinorErrorMessage("");
                l_sendSyncRequestResponse.setRejectedCommand(l_rejectedCommand);
            }

            l_resultInfoCreateUser.setStatusMessage("Normal");
            l_resultInfoCreateUser.setMajorStatusCode(majorStatusCode);
            l_resultInfoCreateUser.setMinorStatusCode(minorStatusCode);

            l_sendSyncRequestResponse.setSendSyncRequestResult(l_resultInfoCreateUser);
            return l_sendSyncRequestResponse;
        }
    
    public static SendSyncRequestResponse getSendSyncRequestResponseForTest1(
            int count, boolean l_isNullRejectedCommand,
            int majorStatusCode, int minorStatusCode,
            int majorErrorCode, int minorErrorCode)
        {
            SendSyncRequestResponse l_sendSyncRequestResponse =
                new SendSyncRequestResponse();

//            long[] l_lngAccountIds_10 = {
//                10001,
//                10010,
//                20001,
//                20002,
//                20003,
//                20004,
//                20005,
//                20006,
//                20007,
//                20008};
//            
//            long[] l_lngAccountIds_8 = {
//                    20001,
//                    20002,
//                    20003,
//                    20004,
//                    20005,
//                    20006,
//                    20007,
//                    20008};
            List<Long> l_lngAccountIds_10 = new ArrayList<Long>();
            l_lngAccountIds_10.add(new Long(10001));
            l_lngAccountIds_10.add(new Long(10010));
            l_lngAccountIds_10.add(new Long(20001));
            l_lngAccountIds_10.add(new Long(20002));
            l_lngAccountIds_10.add(new Long(20003));
            l_lngAccountIds_10.add(new Long(20004));
            l_lngAccountIds_10.add(new Long(20005));
            l_lngAccountIds_10.add(new Long(20006));
            l_lngAccountIds_10.add(new Long(20007));
            l_lngAccountIds_10.add(new Long(20008));
            List<Long> l_lngAccountIds_8= new ArrayList<Long>();
            l_lngAccountIds_8.add(new Long(20001));
            l_lngAccountIds_8.add(new Long(20002));
            l_lngAccountIds_8.add(new Long(20003));
            l_lngAccountIds_8.add(new Long(20004));
            l_lngAccountIds_8.add(new Long(20005));
            l_lngAccountIds_8.add(new Long(20006));
            l_lngAccountIds_8.add(new Long(20007));
            l_lngAccountIds_8.add(new Long(20008));
;            //ResultInfoCreateUser
            ResultInfoAddAccount l_resultInfoAddAccount = new ResultInfoAddAccount();
            if (count == 10)
            {                
                l_resultInfoAddAccount.setAccountId(l_lngAccountIds_10);
            }
            else if (count == 8)
            {                
                l_resultInfoAddAccount.setAccountId(l_lngAccountIds_8);
            }

            if (l_isNullRejectedCommand)
            {
                RejectedCommand l_rejectedCommand =
                    new RejectedCommand();
                l_rejectedCommand.setMajorErrorCode(majorErrorCode);
                l_rejectedCommand.setMajorErrorMessage("");
                l_rejectedCommand.setMinorErrorCode(minorErrorCode);
                l_rejectedCommand.setMinorErrorMessage("");
                l_sendSyncRequestResponse.setRejectedCommand(l_rejectedCommand);
            }

            l_resultInfoAddAccount.setStatusMessage("Normal");
            l_resultInfoAddAccount.setMajorStatusCode(majorStatusCode);
            l_resultInfoAddAccount.setMinorStatusCode(minorStatusCode);

            l_sendSyncRequestResponse.setSendSyncRequestResult(l_resultInfoAddAccount);
            return l_sendSyncRequestResponse;
        }
    
    protected class WEB3GFTConnectionSystemForTest extends WEB3GFTConnectionSystem
    {
        protected SendSyncRequestResponse sendSoapMessage(
                WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit,
                SoapConnectPrefRpcParams l_rpcParams,
                String l_worldTime,
                String l_hashValue) throws WEB3BaseException
        {
            String STR_METHOD_NAME = 
                "sendSoapMessage(WEB3FXGftAskingTelegramUnit, " +
                "SoapConnectPrefRpcParams, String, String) For Test";
            log.entering(STR_METHOD_NAME);
            
          SendSyncRequestResponse l_sendSyncRequestResponse =
              getSendSyncRequestResponseForTest1(
                  8, false, 100, 0, 0, 0);
            
            log.exiting(STR_METHOD_NAME);
            return l_sendSyncRequestResponse; 
            
        }
        
        protected void validateConnect(SoapConnectPrefRpcParams l_rpcParams) 
        {
            String STR_METHOD_NAME = "validateConnect(SoapConnectPrefRpcParams) For Test";
            log.entering(STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
        }
    }
}
@


1.3
log
@*** empty log message ***
@
text
@d3 1
d6 3
d18 8
d88 1
a88 1
            l_soapConnectPrefRpcParams.setEndpointName("http://10.253.111.85:8080/axis2/services/AdministrativeAPI");
d105 2
a106 1

d111 3
a113 1
                    l_strHashValue);
d115 1
a115 1
                (ResultInfoLookupUser)l_return.getSendSyncRequestResult();
d117 1
a117 1
            LookupAccountInfo[] l_accountInfos =
d119 1
a119 1
            assertEquals(2, l_accountInfos.length);
d228 1
a228 1
            l_soapConnectPrefRpcParams.setEndpointName("http://10.253.111.85:8080/axis2/services/AdministrativeAPI");
d272 1
a272 1
            l_soapConnectPrefRpcParams.setEndpointName("http://10.253.111.85:8080/axis2/services/AdministrativeAPI");
d357 1
a357 1
            l_soapConnectPrefRpcParams.setEndpointName("http://10.253.111.85:8080/axis2/services/AdministrativeAPI");
d448 1
a448 1
            l_soapConnectPrefRpcParams.setEndpointName("http://10.253.111.85:8080/axis2/services/AdministrativeAPI");
d536 1
a536 1
            l_soapConnectPrefRpcParams.setEndpointName("http://10.253.111.85:8080/axis2/services/AdministrativeAPI");
d619 1
a619 1
            l_soapConnectPrefRpcParams.setEndpointName("http://10.253.111.85:8080/axis2/services/AdministrativeAPI");
d707 1
a707 1
            l_soapConnectPrefRpcParams.setEndpointName("http://10.253.111.28:8080/axis2/services/AdministrativeAPI");
d807 10
a816 10
            assertEquals("10001", l_connectionSystemForTest.getResult(WEB3ExtConnection.FX_ACC_01).toString());
            assertEquals("10010", l_connectionSystemForTest.getResult(WEB3ExtConnection.FX_ACC_10).toString());
            assertEquals("20001", l_connectionSystemForTest.getResult(WEB3ExtConnection.CFD_ACC).toString());
            assertEquals("20002", l_connectionSystemForTest.getResult(WEB3ExtConnection.CFD_ACC2).toString());
            assertEquals("20003", l_connectionSystemForTest.getResult(WEB3ExtConnection.CFD_ACC3).toString());
            assertEquals("20004", l_connectionSystemForTest.getResult(WEB3ExtConnection.CFD_ACC4).toString());
            assertEquals("20005", l_connectionSystemForTest.getResult(WEB3ExtConnection.CFD_ACC5).toString());
            assertEquals("20006", l_connectionSystemForTest.getResult(WEB3ExtConnection.CFD_ACC6).toString());
            assertEquals("20007", l_connectionSystemForTest.getResult(WEB3ExtConnection.CFD_ACC7).toString());
            assertEquals("20008", l_connectionSystemForTest.getResult(WEB3ExtConnection.CFD_ACC8).toString());
d837 1
a837 1
            l_soapConnectPrefRpcParams.setEndpointName("http://10.253.111.28:8080/axis2/services/AdministrativeAPI");
d899 1
a899 1
            l_compFxConditionParams.setMultiCfdAccDiv("1");
d936 8
a943 8
            assertEquals("20001", l_connectionSystemForTest.getResult(WEB3ExtConnection.CFD_ACC).toString());
            assertEquals("20002", l_connectionSystemForTest.getResult(WEB3ExtConnection.CFD_ACC2).toString());
            assertEquals("20003", l_connectionSystemForTest.getResult(WEB3ExtConnection.CFD_ACC3).toString());
            assertEquals("20004", l_connectionSystemForTest.getResult(WEB3ExtConnection.CFD_ACC4).toString());
            assertEquals("20005", l_connectionSystemForTest.getResult(WEB3ExtConnection.CFD_ACC5).toString());
            assertEquals("20006", l_connectionSystemForTest.getResult(WEB3ExtConnection.CFD_ACC6).toString());
            assertEquals("20007", l_connectionSystemForTest.getResult(WEB3ExtConnection.CFD_ACC7).toString());
            assertEquals("20008", l_connectionSystemForTest.getResult(WEB3ExtConnection.CFD_ACC8).toString());
d964 1
a964 1
            l_soapConnectPrefRpcParams.setEndpointName("http://10.253.111.28:8080/axis2/services/AdministrativeAPI");
d1026 1
a1026 1
            l_compFxConditionParams.setMultiCfdAccDiv("1");
d1063 8
a1070 8
            assertEquals("20001", l_connectionSystemForTest.getResult(WEB3ExtConnection.CFD_ACC).toString());
            assertEquals("20002", l_connectionSystemForTest.getResult(WEB3ExtConnection.CFD_ACC2).toString());
            assertEquals("20003", l_connectionSystemForTest.getResult(WEB3ExtConnection.CFD_ACC3).toString());
            assertEquals("20004", l_connectionSystemForTest.getResult(WEB3ExtConnection.CFD_ACC4).toString());
            assertEquals("20005", l_connectionSystemForTest.getResult(WEB3ExtConnection.CFD_ACC5).toString());
            assertEquals("20006", l_connectionSystemForTest.getResult(WEB3ExtConnection.CFD_ACC6).toString());
            assertEquals("20007", l_connectionSystemForTest.getResult(WEB3ExtConnection.CFD_ACC7).toString());
            assertEquals("20008", l_connectionSystemForTest.getResult(WEB3ExtConnection.CFD_ACC8).toString());
d1091 2
a1092 1
        LookupAccountInfo[] l_accountInfos = null;
d1095 11
a1105 8
            l_accountInfos = new LookupAccountInfo[2];
            l_accountInfos[0] = new LookupAccountInfo();
            l_accountInfos[0].setAccountId(1111);
            l_accountInfos[0].setWithdrawableAmount(100);
            
            l_accountInfos[1] = new LookupAccountInfo();
            l_accountInfos[1].setAccountId(2222);
            l_accountInfos[1].setWithdrawableAmount(200);
d1109 5
a1113 4
            l_accountInfos = new LookupAccountInfo[1];
            l_accountInfos[0] = new LookupAccountInfo();
            l_accountInfos[0].setAccountId(1111);
            l_accountInfos[0].setWithdrawableAmount(100);
d1151 41
a1191 22
            long[] l_lngAccountIds_10 = {
                10001,
                10010,
                20001,
                20002,
                20003,
                20004,
                20005,
                20006,
                20007,
                20008};
            
            long[] l_lngAccountIds_8 = {
                    20001,
                    20002,
                    20003,
                    20004,
                    20005,
                    20006,
                    20007,
                    20008};
            
d1230 42
a1271 23
            long[] l_lngAccountIds_10 = {
                10001,
                10010,
                20001,
                20002,
                20003,
                20004,
                20005,
                20006,
                20007,
                20008};
            
            long[] l_lngAccountIds_8 = {
                    20001,
                    20002,
                    20003,
                    20004,
                    20005,
                    20006,
                    20007,
                    20008};
            
            //ResultInfoCreateUser
@


1.2
log
@*** empty log message ***
@
text
@d94 1
a94 2
            SendSyncRequestResponse l_return =
                l_connectionSystem.sendSoapMessage(
d754 1
a754 1
            l_compFxConditionParams.setMultiCfdAccDiv("1");
@


1.1
log
@*** empty log message ***
@
text
@a13 7
import com.gftforex.soap.api.LookupAccountInfo;
import com.gftforex.soap.api.LookupUserInfo;
import com.gftforex.soap.api.RejectedCommand;
import com.gftforex.soap.api.ResultInfoAddAccount;
import com.gftforex.soap.api.ResultInfoCreateUser;
import com.gftforex.soap.api.ResultInfoLookupUser;
import com.gftforex.soap.api.SendSyncRequestResponse;
@

