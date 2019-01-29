head	1.4;
access;
symbols;
locks; strict;
comment	@// @;


1.4
date	2011.04.08.07.44.01;	author zhang-tengyu;	state Exp;
branches;
next	1.3;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6184d9ebcc177b0;
filename	WEB3FXTransferAbleAmtDisplayServiceImplTest.java;

1.3
date	2011.04.08.02.18.41;	author zhang-tengyu;	state Exp;
branches;
next	1.2;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6184d9e706b7e68;
filename	WEB3FXTransferAbleAmtDisplayServiceImplTest.java;

1.2
date	2011.04.08.00.54.03;	author che-jin;	state Exp;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	666;
commitid	1404d9e5c8b3d81;
filename	WEB3FXTransferAbleAmtDisplayServiceImplTest.java;

1.1
date	2011.04.07.01.34.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FXTransferAbleAmtDisplayServiceImplTest.java;


desc
@@


1.4
log
@*** empty log message ***
@
text
@package webbroker3.aio;

import java.util.Calendar;
import java.util.HashMap;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.gftforex.soap_api.LookupAccountInfo;
import com.gftforex.soap_api.LookupUserInfo;
import com.gftforex.soap_api.RejectedCommand;
import com.gftforex.soap_api.ResultInfoLookupUser;

import test.util.TestDBUtility;

import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.data.FxAccountCodeParams;
import webbroker3.aio.data.FxAccountCodeRow;
import webbroker3.aio.data.FxAccountParams;
import webbroker3.aio.data.FxAccountRow;
import webbroker3.aio.message.WEB3FXGftAskingTelegramUnit;
import webbroker3.aio.message.WEB3FXTransferAbleAmtUnit;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.data.InstitutionPreferencesParams;
import webbroker3.gentrade.data.SoapConnectPrefRpcParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3FXTransferAbleAmtDisplayServiceImplTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXTransferAbleAmtDisplayServiceImplTest.class);

    public WEB3FXTransferAbleAmtDisplayServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    public void testGetTransferAbleAmtCase1()
    {
        final String STR_METHOD_NAME = "testGetTransferAbleAmtCase1()";
        log.entering (STR_METHOD_NAME );   
        
        try
        {
            WEB3FXTransferAbleAmtDisplayServiceImpl l_impl = new WEB3FXTransferAbleAmtDisplayServiceImpl();

            WEB3GFTConnectionSystem l_GFTConnectionSystem =  new WEB3GFTConnectionSystem();
            HashMap l_hmAmount = new HashMap();
            l_hmAmount.put("101", "1000");
            l_hmAmount.put("102", "2000");
            l_hmAmount.put("103", "3000");
            l_GFTConnectionSystem.connectionResultDetails.put("amount", l_hmAmount);
            WEB3FXTransferAbleAmtUnit[] l_transferAbleAmtUnits =
                new WEB3FXTransferAbleAmtUnit[3];
            l_transferAbleAmtUnits[0] = new WEB3FXTransferAbleAmtUnit();
            l_transferAbleAmtUnits[0].fxAccountCode = "101";
            l_transferAbleAmtUnits[0].fxCourseDiv = "1";
            l_transferAbleAmtUnits[0].transferableAmt = null;
            l_transferAbleAmtUnits[1] = new WEB3FXTransferAbleAmtUnit();
            l_transferAbleAmtUnits[1].fxAccountCode = "102";
            l_transferAbleAmtUnits[1].fxCourseDiv = "2";
            l_transferAbleAmtUnits[1].transferableAmt = null;
            l_transferAbleAmtUnits[2] = new WEB3FXTransferAbleAmtUnit();
            l_transferAbleAmtUnits[2].fxAccountCode = "103";
            l_transferAbleAmtUnits[2].fxCourseDiv = "3";
            l_transferAbleAmtUnits[2].transferableAmt = null;
            
            WEB3FXTransferAbleAmtUnit[] l_transferAbleAmtUnitsNew = l_impl.getTransferAbleAmt(l_GFTConnectionSystem, l_transferAbleAmtUnits);
            assertEquals("1000", l_transferAbleAmtUnitsNew[0].transferableAmt);
            assertEquals("2000", l_transferAbleAmtUnitsNew[1].transferableAmt);
            assertEquals("3000", l_transferAbleAmtUnitsNew[2].transferableAmt);
        }
        catch (Exception l_ex)
        {
            log.error("" + l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetTransferAbleAmtCase2()
    {
        final String STR_METHOD_NAME = "testGetTransferAbleAmtCase2()";
        log.entering (STR_METHOD_NAME );   
        
        try
        {
            WEB3FXTransferAbleAmtDisplayServiceImpl l_impl = new WEB3FXTransferAbleAmtDisplayServiceImpl();

            WEB3GFTConnectionSystem l_GFTConnectionSystem =  new WEB3GFTConnectionSystem();
            HashMap l_hmAmount = new HashMap();
            l_hmAmount.put("101", "1000");
            l_hmAmount.put("102", "2000");
            l_hmAmount.put("103", "3000");
            l_GFTConnectionSystem.connectionResultDetails.put("amount", l_hmAmount);
            WEB3FXTransferAbleAmtUnit[] l_transferAbleAmtUnits =
                new WEB3FXTransferAbleAmtUnit[3];
            l_transferAbleAmtUnits[0] = new WEB3FXTransferAbleAmtUnit();
            l_transferAbleAmtUnits[0].fxAccountCode = "101";
            l_transferAbleAmtUnits[0].fxCourseDiv = "1";
            l_transferAbleAmtUnits[0].transferableAmt = null;
            l_transferAbleAmtUnits[1] = new WEB3FXTransferAbleAmtUnit();
            l_transferAbleAmtUnits[1].fxAccountCode = "105";
            l_transferAbleAmtUnits[1].fxCourseDiv = "0";
            l_transferAbleAmtUnits[1].transferableAmt = null;
            l_transferAbleAmtUnits[2] = new WEB3FXTransferAbleAmtUnit();
            l_transferAbleAmtUnits[2].fxAccountCode = "103";
            l_transferAbleAmtUnits[2].fxCourseDiv = "3";
            l_transferAbleAmtUnits[2].transferableAmt = null;
            
            WEB3FXTransferAbleAmtUnit[] l_transferAbleAmtUnitsNew = l_impl.getTransferAbleAmt(l_GFTConnectionSystem, l_transferAbleAmtUnits);
            assertEquals("1000", l_transferAbleAmtUnitsNew[0].transferableAmt);
            assertNull(l_transferAbleAmtUnitsNew[1].transferableAmt);
            assertEquals("3000", l_transferAbleAmtUnitsNew[2].transferableAmt);
        }
        catch (Exception l_ex)
        {
            log.error("" + l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    
    public void testCreateTransferAvleAmtAskingTelegramUnitCase1()
    {
        final String STR_METHOD_NAME = "testCreateTransferAvleAmtAskingTelegramUnitCase1()";
        log.entering (STR_METHOD_NAME );   
        
        try
        {
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_SubAccountParams = TestDBUtility.getSubAccountRow();
            l_SubAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_SubAccountParams);
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_MainAccountParams = TestDBUtility.getMainAccountRow();
            l_MainAccountParams.setAccountCode("2512246");
            TestDBUtility.insertWithDel(l_MainAccountParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_InstitutionParams);
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(FxAccountParams.TYPE);
            FxAccountParams l_FxAccountParams = TestDBUtility.getFxAccountRow();
            l_FxAccountParams.setBranchCode("381");
            l_FxAccountParams.setFxLoginId(60);
            TestDBUtility.insertWithDel(l_FxAccountParams);
            
            TestDBUtility.deleteAll(InstitutionPreferencesParams.TYPE);
            InstitutionPreferencesParams l_InstitutionPreferencesParams = TestDBUtility.getInstitutionPreferencesRow();
            l_InstitutionPreferencesParams.setInstitutionId(33);
            l_InstitutionPreferencesParams.setName("fx.fxloginid.change.div");
            l_InstitutionPreferencesParams.setNameSerialNo(1);
            l_InstitutionPreferencesParams.setValue("02");
            TestDBUtility.insertWithDel(l_InstitutionPreferencesParams);
            
            WEB3FXTransferAbleAmtDisplayServiceImpl l_impl = new WEB3FXTransferAbleAmtDisplayServiceImpl();

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            SubAccount l_subAccount = l_accManager.getSubAccount(333812512246L, 33381251220301L);
            
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            
            WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit =
                (WEB3FXGftAskingTelegramUnit)l_impl.createTransferAbleAmtAskingTelegramUnit(l_subAccount, l_compFxConditionParams);
     
            assertEquals("20110408150001", l_fxGftAskingTelegramUnit.dirSendTime);
            assertEquals("07", l_fxGftAskingTelegramUnit.gftOperationDiv);
            assertEquals("15960", l_fxGftAskingTelegramUnit.fxFirstLoginId);
            assertEquals("0D", l_fxGftAskingTelegramUnit.institutionCode);
//            assertEquals("", l_fxGftAskingTelegramUnit.requestNumber);
        
        }
        catch (Exception l_ex)
        {
            log.error("" + l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /*
     * Test method for 'webbroker3.aio.WEB3FXTransferAbleAmtDisplayServiceImpl.getFXTransferAvleAmtNoCheck(SubAccount, CompFxConditionParams)'
     */
    public void testGetFXTransferAvleAmtNoCheckCase1()
    {
        final String STR_METHOD_NAME = "testGetFXTransferAvleAmtNoCheckCase1()";
        log.entering (STR_METHOD_NAME );   
        
        try
        {
            WEB3FXTransferAbleAmtDisplayServiceImpl l_impl = new WEB3FXTransferAbleAmtDisplayServiceImpl();
            
            this.initData();
            
            SubAccount l_subAccount = null;
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            AccountManager l_accManager = l_finApp.getAccountManager();
            l_subAccount = l_accManager.getSubAccount(333812512246L, 33381251220301L);
            
            CompFxConditionParams l_compFxConditionParams = new CompFxConditionParams();
            l_compFxConditionParams.setExtConnectSystemCode("01");
            l_compFxConditionParams.setInstitutionCode("16");
            l_compFxConditionParams.setBranchCode("100");
            l_compFxConditionParams.setFxSystemCode("05");
            
            FxAccountCodeParams l_fxAccountCodeParams = TestDBUtility.getFxAccountCodeRow();
            l_fxAccountCodeParams.setInstitutionCode("16");
            l_fxAccountCodeParams.setBranchCode("100");
            l_fxAccountCodeParams.setAccountCode("1610000");
            l_fxAccountCodeParams.setFxSystemCode("05");
            l_fxAccountCodeParams.setFxAccountCode("100402");
            l_fxAccountCodeParams.setFxCourseDiv("2");
            TestDBUtility.insertWithDel(l_fxAccountCodeParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("16");
            l_branchParams.setBranchCode("100");
            l_branchParams.setBranchId(16100);
            TestDBUtility.insertWithDel(l_branchParams);
            
            SoapConnectPrefRpcParams l_SoapConnectPrefRpcParams = TestDBUtility.getSoapConnectPrefRpcRow();
            l_SoapConnectPrefRpcParams.setBranchId(16100);
            l_SoapConnectPrefRpcParams.setConnectDiv("05");
            TestDBUtility.insertWithDel(l_SoapConnectPrefRpcParams);
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl", 
                "getNewNumber", 
                new Class[] { String.class, String.class, ProductTypeEnum.class },
                "111");

//           this.getSendSyncRequestResponse(2, true,0,0,200,200);
            WEB3ExtConnection l_extConnection = new WEB3GFTConnectionSystemFortest();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXConnCommonServiceImpl",
                "sendExtConnAskingMessage", new Class[]
                {CompFxConditionParams.class, WEB3FXGftAskingTelegramUnit.class  },
                l_extConnection);
            
            WEB3FXTransferAbleAmtUnit[] l_returnUnits =
                l_impl.getFXTransferAbleAmtNoCheck(l_subAccount, l_compFxConditionParams);
            assertEquals("100401", l_returnUnits[0].fxAccountCode);
            assertEquals("1", l_returnUnits[0].fxCourseDiv);
            assertNull(l_returnUnits[0].transferableAmt);
            assertEquals("100402", l_returnUnits[1].fxAccountCode);
            assertEquals("2", l_returnUnits[1].fxCourseDiv);
            assertNull(l_returnUnits[1].transferableAmt);
        }
        catch (Exception l_ex)
        {
            log.error("" + l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    public class WEB3GFTConnectionSystemFortest extends WEB3GFTConnectionSystem
    {
        public Object getResult(String l_strName)
        {
	         String l_result = "00000001";

            return l_result;
        }	
    }
    
    public void testGetFXTransferAvleAmtNoCheckCase2()
    {
        final String STR_METHOD_NAME = "testGetFXTransferAvleAmtNoCheckCase2()";
        log.entering (STR_METHOD_NAME );   
        
        try
        {
            WEB3FXTransferAbleAmtDisplayServiceImpl l_impl = new WEB3FXTransferAbleAmtDisplayServiceImpl();
            
            this.initData();
            
            SubAccount l_subAccount = null;
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            AccountManager l_accManager = l_finApp.getAccountManager();
            l_subAccount = l_accManager.getSubAccount(333812512246L, 33381251220301L);
            
            CompFxConditionParams l_compFxConditionParams = new CompFxConditionParams();
            l_compFxConditionParams.setExtConnectSystemCode("01");
            l_compFxConditionParams.setInstitutionCode("16");
            l_compFxConditionParams.setBranchCode("100");
            l_compFxConditionParams.setFxSystemCode("05");
            
            FxAccountCodeParams l_fxAccountCodeParams = TestDBUtility.getFxAccountCodeRow();
            l_fxAccountCodeParams.setInstitutionCode("16");
            l_fxAccountCodeParams.setBranchCode("100");
            l_fxAccountCodeParams.setAccountCode("1610000");
            l_fxAccountCodeParams.setFxSystemCode("05");
            l_fxAccountCodeParams.setFxAccountCode("100402");
            l_fxAccountCodeParams.setFxCourseDiv("2");
            TestDBUtility.insertWithDel(l_fxAccountCodeParams);
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl", 
                "getNewNumber", 
                new Class[] { String.class, String.class, ProductTypeEnum.class },
                "111");

            WEB3ExtConnection l_extConnection = new WEB3GFTConnectionSystemFortest();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXConnCommonServiceImpl",
                "sendExtConnAskingMessage", new Class[]
                {CompFxConditionParams.class, WEB3FXGftAskingTelegramUnit.class  },
                l_extConnection);
            
            WEB3FXTransferAbleAmtUnit[] l_returnUnits =
                l_impl.getFXTransferAbleAmtNoCheck(l_subAccount, l_compFxConditionParams);
            assertEquals("100401", l_returnUnits[0].fxAccountCode);
            assertEquals("1", l_returnUnits[0].fxCourseDiv);
            assertNull(l_returnUnits[0].transferableAmt);
            assertEquals("100402", l_returnUnits[1].fxAccountCode);
            assertEquals("2", l_returnUnits[1].fxCourseDiv);
            assertNull(l_returnUnits[1].transferableAmt);
        }
        catch (Exception l_ex)
        {
            log.error("" + l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    /*
     * Test method for 'webbroker3.aio.WEB3FXTransferAbleAmtDisplayServiceImpl.getFXTransferAvleAmtCheck(SubAccount, CompFxConditionParams, String, String)'
     */
    public void testGetFXTransferAvleAmtCheckCase1()
    {
        final String STR_METHOD_NAME = "testGetFXTransferAvleAmtCheckCase1()";
        log.entering (STR_METHOD_NAME );   
        
        try
        {
            WEB3FXTransferAbleAmtDisplayServiceImpl l_impl = new WEB3FXTransferAbleAmtDisplayServiceImpl();
            
            this.initData();
            SubAccount l_subAccount = null;
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            AccountManager l_accManager = l_finApp.getAccountManager();
            l_subAccount = l_accManager.getSubAccount(333812512246L, 33381251220301L);

            CompFxConditionParams l_compFxConditionParams = new CompFxConditionParams();
            l_compFxConditionParams.setExtConnectSystemCode("01");
            l_compFxConditionParams.setInstitutionCode("16");
            l_compFxConditionParams.setBranchCode("100");
            l_compFxConditionParams.setFxSystemCode("05");
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl", 
                "getNewNumber", 
                new Class[] { String.class, String.class, ProductTypeEnum.class },
                "111");
            
            TestDBUtility.deleteAll(FxAccountCodeRow.TYPE);
            
            WEB3ExtConnection l_extConnection = new WEB3GFTConnectionSystemFortest();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXConnCommonServiceImpl",
                "sendExtConnAskingMessage", new Class[]
                {CompFxConditionParams.class, WEB3FXGftAskingTelegramUnit.class  },
                l_extConnection);

            l_impl.getFXTransferAbleAmtCheck(l_subAccount, l_compFxConditionParams, "", "");
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("" + l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("" + l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetFXTransferAvleAmtCheckCase2()
    {
        final String STR_METHOD_NAME = "testGetFXTransferAvleAmtCheckCase2()";
        log.entering (STR_METHOD_NAME );   
        
        try
        {
            WEB3FXTransferAbleAmtDisplayServiceImpl l_impl = new WEB3FXTransferAbleAmtDisplayServiceImpl();
            
            this.initData();
            
            SubAccount l_subAccount = null;
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            AccountManager l_accManager = l_finApp.getAccountManager();
            l_subAccount = l_accManager.getSubAccount(333812512246L, 33381251220301L);
            
            CompFxConditionParams l_compFxConditionParams = new CompFxConditionParams();
            l_compFxConditionParams.setExtConnectSystemCode("01");
            l_compFxConditionParams.setInstitutionCode("16");
            l_compFxConditionParams.setBranchCode("100");
            l_compFxConditionParams.setFxSystemCode("05");
            
            FxAccountCodeParams l_fxAccountCodeParams = TestDBUtility.getFxAccountCodeRow();
            l_fxAccountCodeParams.setInstitutionCode("16");
            l_fxAccountCodeParams.setBranchCode("100");
            l_fxAccountCodeParams.setAccountCode("1610000");
            l_fxAccountCodeParams.setFxSystemCode("05");
            l_fxAccountCodeParams.setFxAccountCode("100402");
            l_fxAccountCodeParams.setFxCourseDiv("2");
            TestDBUtility.insertWithDel(l_fxAccountCodeParams);
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl", 
                "getNewNumber", 
                new Class[] { String.class, String.class, ProductTypeEnum.class },
                "111");
            WEB3ExtConnection l_extConnection = new WEB3GFTConnectionSystemFortest();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXConnCommonServiceImpl",
                "sendExtConnAskingMessage", new Class[]
                {CompFxConditionParams.class, WEB3FXGftAskingTelegramUnit.class  },
                l_extConnection);

            l_impl.getFXTransferAbleAmtCheck(l_subAccount, l_compFxConditionParams, "100", "3");
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("" + l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03162, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("" + l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetFXTransferAvleAmtCheckCase3()
    {
        final String STR_METHOD_NAME = "testGetFXTransferAvleAmtCheckCase3()";
        log.entering (STR_METHOD_NAME );   
        
        try
        {
            WEB3FXTransferAbleAmtDisplayServiceImpl l_impl = new WEB3FXTransferAbleAmtDisplayServiceImplFortest();
            
            this.initData();
            
            SubAccount l_subAccount = null;
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            AccountManager l_accManager = l_finApp.getAccountManager();
            l_subAccount = l_accManager.getSubAccount(333812512246L, 33381251220301L);
            
            CompFxConditionParams l_compFxConditionParams = new CompFxConditionParams();
            l_compFxConditionParams.setExtConnectSystemCode("01");
            l_compFxConditionParams.setInstitutionCode("16");
            l_compFxConditionParams.setBranchCode("100");
            l_compFxConditionParams.setFxSystemCode("05");
            TestDBUtility.deleteAll(FxAccountCodeParams.TYPE);
            FxAccountCodeParams l_fxAccountCodeParams = TestDBUtility.getFxAccountCodeRow();
            l_fxAccountCodeParams.setInstitutionCode("16");
            l_fxAccountCodeParams.setBranchCode("100");
            l_fxAccountCodeParams.setAccountCode("1610000");
            l_fxAccountCodeParams.setFxSystemCode("05");
            l_fxAccountCodeParams.setFxAccountCode("100402");
            l_fxAccountCodeParams.setFxCourseDiv("2");
            TestDBUtility.insertWithDel(l_fxAccountCodeParams);
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl", 
                "getNewNumber", 
                new Class[] { String.class, String.class, ProductTypeEnum.class },
                "111");
            WEB3ExtConnection l_extConnection = new WEB3GFTConnectionSystemFortest();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXConnCommonServiceImpl",
                "sendExtConnAskingMessage", new Class[]
                {CompFxConditionParams.class, WEB3FXGftAskingTelegramUnit.class  },
                l_extConnection);

            l_impl.getFXTransferAbleAmtCheck(l_subAccount, l_compFxConditionParams, "300", "1");
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("" + l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00761, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("" + l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    public class WEB3FXTransferAbleAmtDisplayServiceImplFortest extends WEB3FXTransferAbleAmtDisplayServiceImpl
    {
        public WEB3FXTransferAbleAmtUnit[] getFXTransferAbleAmtNoCheck(
                SubAccount l_subAccount, CompFxConditionParams l_compFxConditionParams)
                throws WEB3BaseException
        {
        	WEB3FXTransferAbleAmtUnit[] l_transferAbleAmtUnit= new WEB3FXTransferAbleAmtUnit[2];
        	
        	WEB3FXTransferAbleAmtUnit l_unit = new WEB3FXTransferAbleAmtUnit();
        	 l_unit.fxCourseDiv = "1";
        	 l_unit.transferableAmt = "100";
        	l_transferAbleAmtUnit[0] = l_unit;
        	return l_transferAbleAmtUnit;
        }
    }
    public void testGetFXTransferAvleAmtCheckCase4()
    {
        final String STR_METHOD_NAME = "testGetFXTransferAvleAmtCheckCase4()";
        log.entering (STR_METHOD_NAME );   
        
        try
        {
            WEB3FXTransferAbleAmtDisplayServiceImpl l_impl = new WEB3FXTransferAbleAmtDisplayServiceImpl();
            
            this.initData();
            
            SubAccount l_subAccount = null;
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            AccountManager l_accManager = l_finApp.getAccountManager();
            l_subAccount = l_accManager.getSubAccount(333812512246L, 33381251220301L);
            
            CompFxConditionParams l_compFxConditionParams = new CompFxConditionParams();
            l_compFxConditionParams.setExtConnectSystemCode("01");
            l_compFxConditionParams.setInstitutionCode("16");
            l_compFxConditionParams.setBranchCode("100");
            l_compFxConditionParams.setFxSystemCode("05");
            
            FxAccountCodeParams l_fxAccountCodeParams = TestDBUtility.getFxAccountCodeRow();
            l_fxAccountCodeParams.setInstitutionCode("16");
            l_fxAccountCodeParams.setBranchCode("100");
            l_fxAccountCodeParams.setAccountCode("1610000");
            l_fxAccountCodeParams.setFxSystemCode("05");
            l_fxAccountCodeParams.setFxAccountCode("100402");
            l_fxAccountCodeParams.setFxCourseDiv("2");
            TestDBUtility.insertWithDel(l_fxAccountCodeParams);
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl", 
                "getNewNumber", 
                new Class[] { String.class, String.class, ProductTypeEnum.class },
                "111");

            WEB3ExtConnection l_extConnection = new WEB3GFTConnectionSystemFortest();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3FXConnCommonServiceImpl",
                "sendExtConnAskingMessage", new Class[]
                {CompFxConditionParams.class, WEB3FXGftAskingTelegramUnit.class  },
                l_extConnection);

            WEB3FXTransferAbleAmtUnit l_returnUnit = l_impl.getFXTransferAbleAmtCheck(l_subAccount, l_compFxConditionParams, "100", "2");
            assertEquals("100402", l_returnUnit.fxAccountCode);
            assertEquals("2", l_returnUnit.fxCourseDiv);
            assertNull( l_returnUnit.transferableAmt);
        }
        catch (Exception l_ex)
        {
            log.error("" + l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    private void initData()
    {
        try
        {
        TestDBUtility.deleteAll(MainAccountRow.TYPE);
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setAccountId(333812512246L);
        l_mainAccountParams.setAccountCode("1610000");
        l_mainAccountParams.setBranchCode("100");
        l_mainAccountParams.setBranchId(16100);
        l_mainAccountParams.setInstitutionCode("16");
        l_mainAccountParams.setInstitutionId(33);
        TestDBUtility.insertWithDel(l_mainAccountParams);
        
        TestDBUtility.deleteAll(SubAccountRow.TYPE);
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        l_subAccountParams.setAccountId(333812512246L);
        l_subAccountParams.setSubAccountId(33381251220301L);
        l_subAccountParams.setInstitutionCode("16");
        l_subAccountParams.setBranchId(16100);
        l_subAccountParams.setInstitutionId(33);
        TestDBUtility.insertWithDel(l_subAccountParams);
        
        TestDBUtility.deleteAll(BranchParams.TYPE);
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        l_branchParams.setBranchId(16100);
        l_branchParams.setBranchCode("100");
        TestDBUtility.insertWithDel(l_branchParams);
        
        TestDBUtility.deleteAll(InstitutionRow.TYPE);
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        l_institutionParams.setInstitutionCode("16");
        TestDBUtility.insertWithDel(l_institutionParams);
        
        TestDBUtility.deleteAll(FxAccountRow.TYPE);
        FxAccountParams l_fxAccountParams = TestDBUtility.getFxAccountRow();
        l_fxAccountParams.setInstitutionCode("16");
        l_fxAccountParams.setBranchCode("100");
        l_fxAccountParams.setAccountCode("1610000");
        l_fxAccountParams.setFxSystemCode("05");
        TestDBUtility.insertWithDel(l_fxAccountParams);
        
        TestDBUtility.deleteAll(FxAccountCodeRow.TYPE);
        FxAccountCodeParams l_fxAccountCodeParams = TestDBUtility.getFxAccountCodeRow();
        l_fxAccountCodeParams.setInstitutionCode("16");
        l_fxAccountCodeParams.setBranchCode("100");
        l_fxAccountCodeParams.setAccountCode("1610000");
        l_fxAccountCodeParams.setFxSystemCode("05");
        l_fxAccountCodeParams.setFxAccountCode("100401");
        l_fxAccountCodeParams.setFxCourseDiv("1");
        TestDBUtility.insertWithDel(l_fxAccountCodeParams);
        
        TestDBUtility.deleteAll(SoapConnectPrefRpcParams.TYPE);
        SoapConnectPrefRpcParams l_SoapConnectPrefRpcParams = new SoapConnectPrefRpcParams();
        l_SoapConnectPrefRpcParams.setBranchId(16100);
        l_SoapConnectPrefRpcParams.setConnectDiv("05");
        l_SoapConnectPrefRpcParams.setEndpointName("172.17.30.41;8080");
        l_SoapConnectPrefRpcParams.setTargetNamespaceName("#");
        l_SoapConnectPrefRpcParams.setServiceName("RegistratorServiceSoap_test");
        l_SoapConnectPrefRpcParams.setPortTypeName("ORIX");
        l_SoapConnectPrefRpcParams.setOperationName("NaQ7yGV8FwK7eCHGc5svrkVA9HRWjVr254wUbzQBmNEEdypJnuZ4uEDCWGCBcV24GZTkSJLFl3GIL71qntzCVA==");
        l_SoapConnectPrefRpcParams.setResponseParamType("#");
        l_SoapConnectPrefRpcParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_SoapConnectPrefRpcParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        TestDBUtility.insertWithDel(l_SoapConnectPrefRpcParams);
        }
        catch (Exception l_ex)
        {
            log.error("" + l_ex);
            fail();
        }
    }
       

}
@


1.3
log
@*** empty log message ***
@
text
@d18 4
d202 1
a202 1
            assertEquals("20090923150001", l_fxGftAskingTelegramUnit.dirSendTime);
d252 11
d270 2
a271 1
           this.getSendSyncRequestResponse(2, true,0,0,200,200);
d274 4
a277 4
                "webbroker3.aio.WEB3FXDataControlServiceImpl",
                "sendSoapMessage", new Class[]
                { WEB3FXGftAskingTelegramUnit.class, SoapConnectPrefRpcParams.class, String.class, String.class },
                l_sendSyncRequestResponse);
d296 5
d302 4
d344 1
a344 2
            SendSyncRequestResponse l_sendSyncRequestResponse =
                this.getSendSyncRequestResponse(2, true,0,0,100,100);
d347 4
a350 4
                "webbroker3.aio.WEB3FXDataControlServiceImpl",
                "sendSoapMessage", new Class[]
                { WEB3FXGftAskingTelegramUnit.class, SoapConnectPrefRpcParams.class, String.class, String.class },
                l_sendSyncRequestResponse);
d356 1
a356 1
            assertEquals("100", l_returnUnits[0].transferableAmt);
d359 1
a359 1
            assertEquals("200", l_returnUnits[1].transferableAmt);
d402 2
a403 3
            SendSyncRequestResponse l_sendSyncRequestResponse =
                this.getSendSyncRequestResponse(2, true,0,0,0,0);
            
d405 4
a408 4
                "webbroker3.aio.WEB3FXDataControlServiceImpl",
                "sendSoapMessage", new Class[]
                { WEB3FXGftAskingTelegramUnit.class, SoapConnectPrefRpcParams.class, String.class, String.class },
                l_sendSyncRequestResponse);
d465 1
a465 3

            SendSyncRequestResponse l_sendSyncRequestResponse =
                this.getSendSyncRequestResponse(2, true,0,0,100,100);
d468 4
a471 4
                "webbroker3.aio.WEB3FXDataControlServiceImpl",
                "sendSoapMessage", new Class[]
                { WEB3FXGftAskingTelegramUnit.class, SoapConnectPrefRpcParams.class, String.class, String.class },
                l_sendSyncRequestResponse);
d497 1
a497 1
            WEB3FXTransferAbleAmtDisplayServiceImpl l_impl = new WEB3FXTransferAbleAmtDisplayServiceImpl();
d511 1
a511 1
            
d527 1
a527 3

            SendSyncRequestResponse l_sendSyncRequestResponse =
                this.getSendSyncRequestResponse(2, true,0,0,100,100);
d530 4
a533 4
                "webbroker3.aio.WEB3FXDataControlServiceImpl",
                "sendSoapMessage", new Class[]
                { WEB3FXGftAskingTelegramUnit.class, SoapConnectPrefRpcParams.class, String.class, String.class },
                l_sendSyncRequestResponse);
d551 15
a565 1
    
d604 1
a604 2
            SendSyncRequestResponse l_sendSyncRequestResponse =
                this.getSendSyncRequestResponse(2, true,0,0,100,100);
d607 4
a610 4
                "webbroker3.aio.WEB3FXDataControlServiceImpl",
                "sendSoapMessage", new Class[]
                { WEB3FXGftAskingTelegramUnit.class, SoapConnectPrefRpcParams.class, String.class, String.class },
                l_sendSyncRequestResponse);
d615 1
a615 1
            assertEquals("200", l_returnUnit.transferableAmt);
d698 1
a698 116
        
    private  SendSyncRequestResponse getSendSyncRequestResponse(
            int count, boolean l_isNullRejectedCommand,
            int majorStatusCode, int minorStatusCode,
            int majorErrorCode, int minorErrorCode)
        {
            SendSyncRequestResponse l_sendSyncRequestResponse =
                new SendSyncRequestResponse();
            ResultInfoLookupUser l_lookupUser = new ResultInfoLookupUser();

            //LookupAccountInfo
            LookupAccountInfo[] l_accountInfos = null;
            if (count == 2)
            {
                l_accountInfos = new LookupAccountInfo[2];
                l_accountInfos[0] = new LookupAccountInfo();
                l_accountInfos[0].setWithdrawableAmount(100);
                l_accountInfos[0].setAccountId(100401);
                
                l_accountInfos[1] = new LookupAccountInfo();
                l_accountInfos[1].setWithdrawableAmount(200);
                l_accountInfos[1].setAccountId(100402);
            }
            else if (count == 1)
            {
                l_accountInfos = new LookupAccountInfo[1];
                l_accountInfos[0] = new LookupAccountInfo();
                l_accountInfos[0].setWithdrawableAmount(100);
                l_accountInfos[0].setAccountId(100401);
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
    
    private  SendSyncRequestResponse getSendSyncRequestResponse1(
            int count, boolean l_isNullRejectedCommand,
            int majorStatusCode, int minorStatusCode,
            int majorErrorCode, int minorErrorCode)
        {
            SendSyncRequestResponse l_sendSyncRequestResponse =
                new SendSyncRequestResponse();
            ResultInfoLookupUser l_lookupUser = new ResultInfoLookupUser();

            //LookupAccountInfo
            LookupAccountInfo[] l_accountInfos = null;
            if (count == 2)
            {
                l_accountInfos = new LookupAccountInfo[2];
                l_accountInfos[0] = new LookupAccountInfo();
//                l_accountInfos[0].setWithdrawableAmount(100);
                l_accountInfos[0].setAccountId(100401);
                
                l_accountInfos[1] = new LookupAccountInfo();
//                l_accountInfos[1].setWithdrawableAmount(200);
                l_accountInfos[1].setAccountId(100402);
            }
            else if (count == 1)
            {
                l_accountInfos = new LookupAccountInfo[1];
                l_accountInfos[0] = new LookupAccountInfo();
                l_accountInfos[0].setWithdrawableAmount(100);
                l_accountInfos[0].setAccountId(100401);
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
@


1.2
log
@*** empty log message ***
@
text
@d255 1
a255 2
            SendSyncRequestResponse l_sendSyncRequestResponse =
                this.getSendSyncRequestResponse(2, true,0,0,200,200);
@


1.1
log
@*** empty log message ***
@
text
@a17 5
import com.gftforex.soap.api.LookupAccountInfo;
import com.gftforex.soap.api.LookupUserInfo;
import com.gftforex.soap.api.RejectedCommand;
import com.gftforex.soap.api.ResultInfoLookupUser;
import com.gftforex.soap.api.SendSyncRequestResponse;
@

