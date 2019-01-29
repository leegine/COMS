head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.39.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FXTransToFXInputServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio.service.delegate.stdimpls;

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

import webbroker3.aio.WEB3AioOrderManagerForMock;
import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.data.CompFxConditionRow;
import webbroker3.aio.data.FxAccountCodeParams;
import webbroker3.aio.data.FxAccountParams;
import webbroker3.aio.data.FxAccountRow;
import webbroker3.aio.data.FxTransferMasterParams;
import webbroker3.aio.data.FxTransferMasterRow;
import webbroker3.aio.data.GftTransferStatusParams;
import webbroker3.aio.data.GftTransferStatusRow;
import webbroker3.aio.message.WEB3FXTransFromFXInputResponse;
import webbroker3.aio.message.WEB3FXTransToFXInputRequest;
import webbroker3.aio.message.WEB3FXTransToFXInputResponse;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.define.WEB3CommonTransferPageDivDef;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.AccOpenDivParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (FXへの振替入力サービスImplTest) <BR>
 * FXへの振替入力サービス実装クラス <BR>
 */
public class WEB3FXTransToFXInputServiceImplTest extends TestBaseForMock {

    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3FXTransToFXInputServiceImplTest.class);
    
    public WEB3FXTransToFXInputServiceImplTest(String arg0) {
        super(arg0);
    }

    protected void setUp() throws Exception {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    WEB3FXTransToFXInputServiceImpl l_impl  = new WEB3FXTransToFXInputServiceImpl();
    
    /*
     * Test method for 'webbroker3.aio.service.delegate.stdimpls.WEB3FXTransToFXInputServiceImpl.execute(WEB3GenRequest)'
     */
    public void testExecute() 
    {

        final String STR_METHOD_NAME  = "testExecute";
        log.entering(STR_METHOD_NAME);
        
        WEB3FXTransToFXInputRequest l_request = new WEB3FXTransToFXInputRequest();
        l_request.fxSystemCode = "01";
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));

           TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", 
                "getPaymentTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Date.class},
                new Double(1001));
 
           TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
               "webbroker3.aio.WEB3AioOrderManager", 
               "validateOrder",
               new Class[] {SubAccount.class},
               null);

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
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);
            
            AioOrderParams l_aioOrderParams = TestDBUtility.getAioOrderRow();
            TestDBUtility.deleteAll(AioOrderRow.TYPE);
            l_aioOrderParams.setAccountId(l_subAccountParams.getAccountId());
            l_aioOrderParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_aioOrderParams.setOrderId(1111);
            TestDBUtility.insertWithDel(l_aioOrderParams);
            
            GftTransferStatusParams l_gftTransferStatusParams = TestDBUtility.getGftTransferStatusRow();
            TestDBUtility.deleteAllAndCommit(GftTransferStatusRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_gftTransferStatusParams);
            
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            l_compFxConditionParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setFxSystemCode("01");
            l_compFxConditionParams.setFxSystemDiv("2");
            TestDBUtility.insertWithDel(l_compFxConditionParams);

            TestDBUtility.deleteAll(FxAccountRow.TYPE);
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
            
            TestDBUtility.deleteAll(FxAccountCodeParams.TYPE);
            TestDBUtility.insertWithDel(l_FxAccountCodeParams);

            TestDBUtility.deleteAll(FxTransferMasterRow.TYPE);
            FxTransferMasterParams l_fxTransferMasterParams =
                TestDBUtility.getFxTransferMasterParams();
            l_fxTransferMasterParams.setFxSystemId(6241001);
            l_fxTransferMasterParams.setTransferDiv("1");
            l_fxTransferMasterParams.setDeliveryDateDiv("2");
            TestDBUtility.insertWithDel(l_fxTransferMasterParams);

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                    WEB3DateUtility.getDate("20090320", "yyyyMMdd"));

            WEB3FXTransToFXInputResponse l_FXTransToFXInputResponse = 
                (WEB3FXTransToFXInputResponse)l_impl.execute(l_request);

            assertEquals(l_FXTransToFXInputResponse.transferableAmt,"1001");

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testExecuteCase0001() 
    {

        final String STR_METHOD_NAME  = "testExecuteCase0001";
        log.entering(STR_METHOD_NAME);
        
        WEB3FXTransToFXInputRequest l_request = new WEB3FXTransToFXInputRequest();
        l_request.fxSystemCode = "01";
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));

           TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", 
                "getPaymentTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Date.class},
                new Double(1001));
 
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
                   "validateOtherSystemAcceptPossible",
                   new Class[] {String.class},
                   null);
           
           TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                   "webbroker3.aio.WEB3AioOrderManager", 
                   "validateTransferPossibleCount",
                   new Class[] {SubAccount.class, Date.class, OrderCategEnum.class},
                   new Integer(8));
           
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
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);
            
            AioOrderParams l_aioOrderParams = TestDBUtility.getAioOrderRow();
            TestDBUtility.deleteAll(AioOrderRow.TYPE);
            l_aioOrderParams.setAccountId(l_subAccountParams.getAccountId());
            l_aioOrderParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_aioOrderParams.setOrderId(1111);
            TestDBUtility.insertWithDel(l_aioOrderParams);
            
            GftTransferStatusParams l_gftTransferStatusParams = TestDBUtility.getGftTransferStatusRow();
            TestDBUtility.deleteAllAndCommit(GftTransferStatusRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_gftTransferStatusParams);
            
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            l_compFxConditionParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setFxSystemCode("01");
            l_compFxConditionParams.setFxSystemDiv("2");
            l_compFxConditionParams.setMultiCfdAccDiv("0");
            l_compFxConditionParams.setCommonTransferPageDiv(WEB3CommonTransferPageDivDef.NOT_EXECUTE);
            TestDBUtility.insertWithDel(l_compFxConditionParams);

            TestDBUtility.deleteAll(FxAccountRow.TYPE);
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
            
            TestDBUtility.deleteAll(FxAccountCodeParams.TYPE);
            TestDBUtility.insertWithDel(l_FxAccountCodeParams);

            TestDBUtility.deleteAll(FxTransferMasterRow.TYPE);
            FxTransferMasterParams l_fxTransferMasterParams =
                TestDBUtility.getFxTransferMasterParams();
            l_fxTransferMasterParams.setFxSystemId(6241001);
            l_fxTransferMasterParams.setTransferDiv("1");
            l_fxTransferMasterParams.setDeliveryDateDiv("2");
            TestDBUtility.insertWithDel(l_fxTransferMasterParams);

            TestDBUtility.deleteAll(AccOpenDivParams.TYPE);
            AccOpenDivParams l_accOpenDivParams =
                TestDBUtility.getAccOpenDivRow();
            l_accOpenDivParams.setAccountId(l_subAccountParams.getAccountId());
            l_accOpenDivParams.setAccType(l_compFxConditionParams.getAccType());
            l_accOpenDivParams.setAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_accOpenDivParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                    WEB3DateUtility.getDate("20090320", "yyyyMMdd"));

            WEB3FXTransToFXInputResponse l_FXTransToFXInputResponse = 
                (WEB3FXTransToFXInputResponse)l_impl.execute(l_request);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExecuteCase0002() 
    {

        final String STR_METHOD_NAME  = "testExecuteCase0002";
        log.entering(STR_METHOD_NAME);
        
        WEB3FXTransToFXInputRequest l_request = new WEB3FXTransToFXInputRequest();
        l_request.fxSystemCode = "01";
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));

           TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", 
                "getPaymentTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Date.class},
                new Double(1001));
 
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
                   "validateOtherSystemAcceptPossible",
                   new Class[] {String.class},
                   null);
           
           TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                   "webbroker3.aio.WEB3AioOrderManager", 
                   "validateTransferPossibleCount",
                   new Class[] {SubAccount.class, Date.class, OrderCategEnum.class},
                   new Integer(8));
           
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
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);
            
            AioOrderParams l_aioOrderParams = TestDBUtility.getAioOrderRow();
            TestDBUtility.deleteAll(AioOrderRow.TYPE);
            l_aioOrderParams.setAccountId(l_subAccountParams.getAccountId());
            l_aioOrderParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_aioOrderParams.setOrderId(1111);
            TestDBUtility.insertWithDel(l_aioOrderParams);
            
            GftTransferStatusParams l_gftTransferStatusParams = TestDBUtility.getGftTransferStatusRow();
            TestDBUtility.deleteAllAndCommit(GftTransferStatusRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_gftTransferStatusParams);
            
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            TestDBUtility.deleteAll(CompFxConditionRow.TYPE);
            l_compFxConditionParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            l_compFxConditionParams.setBranchCode("381");
            l_compFxConditionParams.setFxSystemCode("01");
            l_compFxConditionParams.setFxSystemDiv("2");
            l_compFxConditionParams.setMultiCfdAccDiv("0");
            l_compFxConditionParams.setCommonTransferPageDiv(WEB3CommonTransferPageDivDef.EXECUTE);
            TestDBUtility.insertWithDel(l_compFxConditionParams);

            TestDBUtility.deleteAll(FxAccountRow.TYPE);
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
            
            TestDBUtility.deleteAll(FxAccountCodeParams.TYPE);
            TestDBUtility.insertWithDel(l_FxAccountCodeParams);

            TestDBUtility.deleteAll(FxTransferMasterRow.TYPE);
            FxTransferMasterParams l_fxTransferMasterParams =
                TestDBUtility.getFxTransferMasterParams();
            l_fxTransferMasterParams.setFxSystemId(6241001);
            l_fxTransferMasterParams.setTransferDiv("1");
            l_fxTransferMasterParams.setDeliveryDateDiv("2");
            TestDBUtility.insertWithDel(l_fxTransferMasterParams);

            TestDBUtility.deleteAll(AccOpenDivParams.TYPE);
            AccOpenDivParams l_accOpenDivParams =
                TestDBUtility.getAccOpenDivRow();
            l_accOpenDivParams.setAccountId(l_subAccountParams.getAccountId());
            l_accOpenDivParams.setAccType(l_compFxConditionParams.getAccType());
            l_accOpenDivParams.setAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_accOpenDivParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                    WEB3DateUtility.getDate("20090320", "yyyyMMdd"));

            WEB3FXTransToFXInputResponse l_FXTransToFXInputResponse = 
                (WEB3FXTransToFXInputResponse)l_impl.execute(l_request);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
