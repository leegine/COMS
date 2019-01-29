head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.06.08.32.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	1e84d9c24e17fc0;
filename	WEB3AccInfoElecDeliveryRegisterChangeServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研ビジネス・イノベーション
File Name        : 電子交付サービス登録・変更サービスImpl(WEB3AccInfoElecDeliveryRegisterChangeServiceImpl.java)
Author Name      : Daiwa Institute of Research Business Innovation
Revision History : 2010/11/17 張騰宇(中訊) 新規作成 仕様変更モデルNo.278
*/
package webbroker3.accountinfo.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderRow;

import test.util.TestDBUtility;

import webbroker3.accountinfo.message.WEB3AccInfoEleDeliveryInfo;
import webbroker3.accountinfo.message.WEB3AccInfoElecDeliveryApyReferenceRequest;
import webbroker3.accountinfo.message.WEB3AccInfoElecDeliveryApyReferenceResponse;
import webbroker3.accountinfo.message.WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest;
import webbroker3.accountinfo.message.WEB3AccInfoElecDeliveryRegisterChangeInputRequest;
import webbroker3.accountinfo.message.WEB3AccInfoElecDeliveryRegisterChangeInputResponse;
import webbroker3.accountinfo.message.WEB3AccInfoEquityCommissionCourseChangeCancelCompleteRequest;
import webbroker3.accountopen.data.HostConditionRegVoucherParams;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.EleDeliveryManagementDao;
import webbroker3.gentrade.data.EleDeliveryManagementParams;
import webbroker3.gentrade.data.EleDeliveryManagementRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AccInfoElecDeliveryRegisterChangeServiceImplTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoElecDeliveryRegisterChangeServiceImplTest.class);

    public WEB3AccInfoElecDeliveryRegisterChangeServiceImplTest(String arg0)
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

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.WEB3AccInfoElecDeliveryRegisterChangeServiceImpl.execute(WEB3GenRequest)'
     */
    public void testExecuteCase1()
    {
        final String STR_METHOD_NAME = "testExecuteCase1()";
        log.entering(STR_METHOD_NAME);

        try
        {
            
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            WEB3AccInfoElecDeliveryRegisterChangeInputRequest l_request =
                new WEB3AccInfoElecDeliveryRegisterChangeInputRequest();
            
            l_impl.execute(l_request);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03221,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExecuteCase2()
    {
        final String STR_METHOD_NAME = "testExecuteCase2()";
        log.entering(STR_METHOD_NAME);

        try
        {
            
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest l_request =
                new WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest();
            l_request.tradingReportDiv = "2";
            l_impl.execute(l_request);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03211,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExecuteCase3()
    {
        final String STR_METHOD_NAME = "testExecuteCase3()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));
            LoginInfoImpl l_loginInfo = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfo);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginId",
                new Class[] {},
                new Long(111111));
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            WEB3AccInfoElecDeliveryApyReferenceRequest l_request =
                new WEB3AccInfoElecDeliveryApyReferenceRequest();

            WEB3AccInfoElecDeliveryApyReferenceResponse l_response =
                (WEB3AccInfoElecDeliveryApyReferenceResponse)l_impl.execute(l_request);
            assertNull(l_response.eleDeliveryInfo);
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExecuteCase4()
    {
        final String STR_METHOD_NAME = "testExecuteCase3()";
        log.entering(STR_METHOD_NAME);

        try
        {
            
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            WEB3AccInfoEquityCommissionCourseChangeCancelCompleteRequest l_request =
                new WEB3AccInfoEquityCommissionCourseChangeCancelCompleteRequest();

            l_impl.execute(l_request);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.WEB3AccInfoElecDeliveryRegisterChangeServiceImpl.getInpurScreen(WEB3AccInfoElecDeliveryRegisterChangeInputRequest)'
     */
    public void testGetInputScreenCase1()
    {
        final String STR_METHOD_NAME = "testGetInputScreenCase1()";
        log.entering(STR_METHOD_NAME);

        try
        {
            
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            WEB3AccInfoElecDeliveryRegisterChangeInputRequest l_request =
                new WEB3AccInfoElecDeliveryRegisterChangeInputRequest();
            
            l_impl.getInputScreen(l_request);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03221,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetInputScreenCase2()
    {
        final String STR_METHOD_NAME = "testGetInputScreenCase2()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));
            
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            WEB3AccInfoElecDeliveryRegisterChangeInputRequest l_request =
                new WEB3AccInfoElecDeliveryRegisterChangeInputRequest();
            
            l_request.eleDeliveryFlag = "0";
            
            l_impl.getInputScreen(l_request);
            
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetInputScreenCase3()
    {
        final String STR_METHOD_NAME = "testGetInputScreenCase3()";
        log.entering(STR_METHOD_NAME);

        try
        {
//            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));
            LoginInfoImpl l_loginInfo = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfo);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginId",
                new Class[] {},
                new Long(111111));
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            WEB3AccInfoElecDeliveryRegisterChangeInputRequest l_request =
                new WEB3AccInfoElecDeliveryRegisterChangeInputRequest();
            TestDBUtility.deleteAll(EleDeliveryManagementParams.TYPE);
            EleDeliveryManagementParams l_eleDeliveryManagementParams = TestDBUtility.getEleDeliveryManagementRow();
            l_eleDeliveryManagementParams.setEleDelRegiFlag(0);           
            TestDBUtility.insertWithDel(l_eleDeliveryManagementParams);
            l_request.eleDeliveryFlag = "0";
            
            WEB3AccInfoElecDeliveryRegisterChangeInputResponse l_response =
                l_impl.getInputScreen(l_request);
            
            assertNull(l_response.eleDeliveryInfo);
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetInputScreenCase4()
    {
        final String STR_METHOD_NAME = "testGetInputScreenCase4()";
        log.entering(STR_METHOD_NAME);

        try
        {
//            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));
            LoginInfoImpl l_loginInfo = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {},
                    l_loginInfo);
                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(111111));
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            WEB3AccInfoElecDeliveryRegisterChangeInputRequest l_request =
                new WEB3AccInfoElecDeliveryRegisterChangeInputRequest();
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_MainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_MainAccountParams);

            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setLoginId(0);
            TestDBUtility.insertWithDel(l_traderParams);
            
            TestDBUtility.deleteAll(EleDeliveryManagementParams.TYPE);
            EleDeliveryManagementParams l_eleDeliveryManagementParams = TestDBUtility.getEleDeliveryManagementRow();
            l_eleDeliveryManagementParams.setEleDelRegiFlag(0);     
            l_eleDeliveryManagementParams.setTradingReportDiv("1");
            TestDBUtility.insertWithDel(l_eleDeliveryManagementParams);
            
            l_request.eleDeliveryFlag = "1";
            
            WEB3AccInfoElecDeliveryRegisterChangeInputResponse l_response =
                l_impl.getInputScreen(l_request);
            
            assertEquals("1", l_response.eleDeliveryInfo.tradingReportDiv);
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.WEB3AccInfoElecDeliveryRegisterChangeServiceImpl.submitChangeScreen(WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest)'
     */
    public void testSubmitChangeScreenCase1()
    {
        final String STR_METHOD_NAME = "testSubmitChangeScreenCase1()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));
            
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest l_request =
                new WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest();
            l_request.tradingReportDiv = "2";
            
            l_impl.submitChangeScreen(l_request);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03211,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitChangeScreenCase2()
    {
        final String STR_METHOD_NAME = "testSubmitChangeScreenCase2()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest l_request =
                new WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest();
            l_request.tradingReportDiv = "1";
            
            l_impl.submitChangeScreen(l_request);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitChangeScreenCase3()
    {
        final String STR_METHOD_NAME = "testSubmitChangeScreenCase3()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));
            LoginInfoImpl l_loginInfo = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {},
                    l_loginInfo);
                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(111111));
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

            TestDBUtility.deleteAll(HostConditionRegVoucherParams.TYPE);
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_MainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_MainAccountParams);

            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setLoginId(0);
            TestDBUtility.insertWithDel(l_traderParams);
            
            TestDBUtility.deleteAll(EleDeliveryManagementParams.TYPE);
            EleDeliveryManagementParams l_eleDeliveryManagementParams = TestDBUtility.getEleDeliveryManagementRow();
            l_eleDeliveryManagementParams.setEleDelRegiFlag(0);     
            l_eleDeliveryManagementParams.setTradingReportDiv("0");
            TestDBUtility.insertWithDel(l_eleDeliveryManagementParams);
            
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest l_request =
                new WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest();
            l_request.tradingReportDiv = "1";
            
            l_impl.submitChangeScreen(l_request);

        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitChangeScreenCase4()
    {
        final String STR_METHOD_NAME = "testSubmitChangeScreenCase4()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));
            LoginInfoImpl l_loginInfo = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {},
                    l_loginInfo);
                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(111111));
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            TestDBUtility.deleteAll(HostConditionRegVoucherParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_MainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_MainAccountParams);

            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setLoginId(0);
            TestDBUtility.insertWithDel(l_traderParams);
            
            TestDBUtility.deleteAll(EleDeliveryManagementParams.TYPE);
            EleDeliveryManagementParams l_eleDeliveryManagementParams = TestDBUtility.getEleDeliveryManagementRow();
            l_eleDeliveryManagementParams.setEleDelRegiFlag(0);     
            l_eleDeliveryManagementParams.setTradingReportDiv("0");
            TestDBUtility.insertWithDel(l_eleDeliveryManagementParams);
            
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest l_request =
                new WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest();
            l_request.ordRulReportDiv = "1";
            
            l_impl.submitChangeScreen(l_request);

        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.WEB3AccInfoElecDeliveryRegisterChangeServiceImpl.updateElecDeliveryManagement(WEB3GentradeMainAccount, WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest, String)'
     */
    public void testUpdateElecDeliveryManagementCase1()
    {
        final String STR_METHOD_NAME = "testUpdateElecDeliveryManagementCase1()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_MainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_MainAccountParams);

            
            TestDBUtility.deleteAll(EleDeliveryManagementParams.TYPE);
//            EleDeliveryManagementParams l_eleDeliveryManagementParams = TestDBUtility.getEleDeliveryManagementRow();
//            TestDBUtility.insertWithDel(l_eleDeliveryManagementParams);
            
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_MainAccountParams);
            
            WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest l_request =
                new WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest();
            
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            
            l_impl.updateElecDeliveryManagement(l_mainAccount, l_request, "");
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testUpdateElecDeliveryManagementCase2()
    {
        final String STR_METHOD_NAME = "testUpdateElecDeliveryManagementCase2()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_MainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_MainAccountParams);

            
            TestDBUtility.deleteAll(EleDeliveryManagementParams.TYPE);
            EleDeliveryManagementParams l_eleDeliveryManagementParams = TestDBUtility.getEleDeliveryManagementRow();
            l_eleDeliveryManagementParams.setEleDelRegiFlag(0);           
            TestDBUtility.insertWithDel(l_eleDeliveryManagementParams);
            
            
            assertEquals(0, l_eleDeliveryManagementParams.getEleDelRegiFlag());
            
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_MainAccountParams);
            
            
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            
            l_impl.updateElecDeliveryManagement(l_mainAccount, null, null);
            
            EleDeliveryManagementRow l_eleDeliveryManagementRow = null;
            l_eleDeliveryManagementRow = EleDeliveryManagementDao.findRowByInstitutionCodeBranchCodeAccountCode(
                "0D",
                "381",
                "2512246");
            assertEquals(1, l_eleDeliveryManagementRow.getEleDelRegiFlag());
            assertEquals("2512246", l_eleDeliveryManagementRow.getLastUpdater());
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testUpdateElecDeliveryManagementCase3()
    {
        final String STR_METHOD_NAME = "testUpdateElecDeliveryManagementCase3()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_MainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_MainAccountParams);

            
            TestDBUtility.deleteAll(EleDeliveryManagementParams.TYPE);
            EleDeliveryManagementParams l_eleDeliveryManagementParams = TestDBUtility.getEleDeliveryManagementRow();
            l_eleDeliveryManagementParams.setEleDelRegiFlag(1);
            TestDBUtility.insertWithDel(l_eleDeliveryManagementParams);
            
            
            assertEquals(1, l_eleDeliveryManagementParams.getEleDelRegiFlag());
            
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_MainAccountParams);
            
//            WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest l_request =
//                new WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest();
            
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            
            l_impl.updateElecDeliveryManagement(l_mainAccount, null, "12345");
            
            EleDeliveryManagementRow l_eleDeliveryManagementRow = null;
            l_eleDeliveryManagementRow = EleDeliveryManagementDao.findRowByInstitutionCodeBranchCodeAccountCode(
                "0D",
                "381",
                "2512246");
            assertEquals(1, l_eleDeliveryManagementRow.getEleDelRegiFlag());
            assertEquals("12345", l_eleDeliveryManagementRow.getLastUpdater());
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testUpdateElecDeliveryManagementCase4()
    {
        final String STR_METHOD_NAME = "testUpdateElecDeliveryManagementCase4()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_MainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_MainAccountParams);

            
            TestDBUtility.deleteAll(EleDeliveryManagementParams.TYPE);
            EleDeliveryManagementParams l_eleDeliveryManagementParams = TestDBUtility.getEleDeliveryManagementRow();
            l_eleDeliveryManagementParams.setEleDelRegiFlag(1);
            l_eleDeliveryManagementParams.setLastUpdater("111");
            l_eleDeliveryManagementParams.setTradingReportDiv("0");
            l_eleDeliveryManagementParams.setPositionReportDiv("1");
            l_eleDeliveryManagementParams.setOpeReportDiv("0");
            l_eleDeliveryManagementParams.setOrdRulReportDiv("0");
            l_eleDeliveryManagementParams.setReportDiv1("1");
            l_eleDeliveryManagementParams.setReportDiv2("1");
            l_eleDeliveryManagementParams.setReportDiv3("1");
            l_eleDeliveryManagementParams.setReportDiv4("1");
            l_eleDeliveryManagementParams.setReportDiv5("1");
            TestDBUtility.insertWithDel(l_eleDeliveryManagementParams);
            
            
            assertEquals(1, l_eleDeliveryManagementParams.getEleDelRegiFlag());
            
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_MainAccountParams);
            
            WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest l_request =
                new WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest();
            l_request.tradingReportDiv="1";
            l_request.positionReportDiv="0";
            l_request.opeReportDiv = "1";
            l_request.ordRulReportDiv="1";
            l_request.report_div1="0";
            l_request.report_div2="0";
            l_request.report_div3="0";
            l_request.report_div4="0";
            l_request.report_div5="0";
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            
            l_impl.updateElecDeliveryManagement(l_mainAccount, l_request, null);
            
            EleDeliveryManagementRow l_eleDeliveryManagementRow = null;
            l_eleDeliveryManagementRow = EleDeliveryManagementDao.findRowByInstitutionCodeBranchCodeAccountCode(
                "0D",
                "381",
                "2512246");
//            assertEquals(GtlUtils.getSystemTimestamp(), l_eleDeliveryManagementRow.getLastUpdatedTimestamp());
            assertEquals("2512246", l_eleDeliveryManagementRow.getLastUpdater());
            
            assertEquals("1", l_eleDeliveryManagementRow.getTradingReportDiv());
            assertEquals("0", l_eleDeliveryManagementRow.getTradingReportRegDiv());
//            assertEquals(GtlUtils.getSystemTimestamp(), l_eleDeliveryManagementRow.getTradingReportDivUpdDate());
            
            assertEquals("0", l_eleDeliveryManagementRow.getPositionReportDiv());
            assertEquals("0", l_eleDeliveryManagementRow.getPositionReportRegDiv());
//            assertEquals(GtlUtils.getSystemTimestamp(), l_eleDeliveryManagementRow.getPositionReportDivUpdDate());
            

            assertEquals("1", l_eleDeliveryManagementRow.getOpeReportDiv());
            assertEquals("0", l_eleDeliveryManagementRow.getOpeReportRegDiv());
//            assertEquals(GtlUtils.getSystemTimestamp(), l_eleDeliveryManagementRow.getOpeReportDivUpdDate());
            
            assertEquals("1", l_eleDeliveryManagementRow.getOrdRulReportDiv());
            assertEquals("0", l_eleDeliveryManagementRow.getOrdRulRepRegDiv());
//            assertEquals(GtlUtils.getSystemTimestamp(), l_eleDeliveryManagementRow.getOrdRulReportDivUpdDate());
            
            assertEquals("0", l_eleDeliveryManagementRow.getReportDiv1());
            assertEquals("0", l_eleDeliveryManagementRow.getReportRegDiv1());
//            assertEquals(GtlUtils.getSystemTimestamp(), l_eleDeliveryManagementRow.getReportDivUpdDate1());
            
            assertEquals("0", l_eleDeliveryManagementRow.getReportDiv2());
            assertEquals("0", l_eleDeliveryManagementRow.getReportRegDiv2());
//            assertEquals(GtlUtils.getSystemTimestamp(), l_eleDeliveryManagementRow.getReportDivUpdDate2());
            
            assertEquals("0", l_eleDeliveryManagementRow.getReportDiv3());
            assertEquals("0", l_eleDeliveryManagementRow.getReportRegDiv3());
//            assertEquals(GtlUtils.getSystemTimestamp(), l_eleDeliveryManagementRow.getReportDivUpdDate3());
            
            assertEquals("0", l_eleDeliveryManagementRow.getReportDiv4());
            assertEquals("0", l_eleDeliveryManagementRow.getReportRegDiv4());
//            assertEquals(GtlUtils.getSystemTimestamp(), l_eleDeliveryManagementRow.getReportDivUpdDate4());
            
            assertEquals("0", l_eleDeliveryManagementRow.getReportDiv5());
            assertEquals("0", l_eleDeliveryManagementRow.getReportRegDiv5());
//            assertEquals(GtlUtils.getSystemTimestamp(), l_eleDeliveryManagementRow.getReportDivUpdDate5());
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testUpdateElecDeliveryManagementCase5()
    {
        final String STR_METHOD_NAME = "testUpdateElecDeliveryManagementCase5()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_MainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_MainAccountParams);

            
            TestDBUtility.deleteAll(EleDeliveryManagementParams.TYPE);
            EleDeliveryManagementParams l_eleDeliveryManagementParams = TestDBUtility.getEleDeliveryManagementRow();
            l_eleDeliveryManagementParams.setEleDelRegiFlag(1);
            l_eleDeliveryManagementParams.setLastUpdater("111");
            l_eleDeliveryManagementParams.setTradingReportDiv("0");
            l_eleDeliveryManagementParams.setPositionReportDiv("1");
            l_eleDeliveryManagementParams.setOpeReportDiv("0");
            l_eleDeliveryManagementParams.setOrdRulReportDiv("0");
            l_eleDeliveryManagementParams.setReportDiv1("1");
            l_eleDeliveryManagementParams.setReportDiv2("1");
            l_eleDeliveryManagementParams.setReportDiv3("1");
            l_eleDeliveryManagementParams.setReportDiv4("1");
            l_eleDeliveryManagementParams.setReportDiv5("1");
            TestDBUtility.insertWithDel(l_eleDeliveryManagementParams);
            
            
            assertEquals(1, l_eleDeliveryManagementParams.getEleDelRegiFlag());
            
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_MainAccountParams);
            
            WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest l_request =
                new WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest();
            l_request.tradingReportDiv="1";
            l_request.positionReportDiv="1";
            l_request.opeReportDiv = "1";
            l_request.ordRulReportDiv="1";
            l_request.report_div1="0";
            l_request.report_div2="0";
            l_request.report_div3="0";
            l_request.report_div4="0";
            l_request.report_div5="0";
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            
            l_impl.updateElecDeliveryManagement(l_mainAccount, l_request, "12345");
            
            EleDeliveryManagementRow l_eleDeliveryManagementRow = null;
            l_eleDeliveryManagementRow = EleDeliveryManagementDao.findRowByInstitutionCodeBranchCodeAccountCode(
                "0D",
                "381",
                "2512246");
//            assertEquals(GtlUtils.getSystemTimestamp(), l_eleDeliveryManagementRow.getLastUpdatedTimestamp());
            assertEquals("12345", l_eleDeliveryManagementRow.getLastUpdater());
            
            assertEquals("1", l_eleDeliveryManagementRow.getTradingReportDiv());
            assertEquals("0", l_eleDeliveryManagementRow.getTradingReportRegDiv());
//            assertEquals(GtlUtils.getSystemTimestamp(), l_eleDeliveryManagementRow.getTradingReportDivUpdDate());
            
            assertEquals("9", l_eleDeliveryManagementRow.getPositionReportDiv());
            assertEquals("0", l_eleDeliveryManagementRow.getPositionReportRegDiv());
//            assertEquals(GtlUtils.getSystemTimestamp(), l_eleDeliveryManagementRow.getPositionReportDivUpdDate());
            

            assertEquals("1", l_eleDeliveryManagementRow.getOpeReportDiv());
            assertEquals("0", l_eleDeliveryManagementRow.getOpeReportRegDiv());
//            assertEquals(GtlUtils.getSystemTimestamp(), l_eleDeliveryManagementRow.getOpeReportDivUpdDate());
            
            assertEquals("1", l_eleDeliveryManagementRow.getOrdRulReportDiv());
            assertEquals("0", l_eleDeliveryManagementRow.getOrdRulRepRegDiv());
//            assertEquals(GtlUtils.getSystemTimestamp(), l_eleDeliveryManagementRow.getOrdRulReportDivUpdDate());
            
            assertEquals("0", l_eleDeliveryManagementRow.getReportDiv1());
            assertEquals("0", l_eleDeliveryManagementRow.getReportRegDiv1());
//            assertEquals(GtlUtils.getSystemTimestamp(), l_eleDeliveryManagementRow.getReportDivUpdDate1());
            
            assertEquals("0", l_eleDeliveryManagementRow.getReportDiv2());
            assertEquals("0", l_eleDeliveryManagementRow.getReportRegDiv2());
//            assertEquals(GtlUtils.getSystemTimestamp(), l_eleDeliveryManagementRow.getReportDivUpdDate2());
            
            assertEquals("0", l_eleDeliveryManagementRow.getReportDiv3());
            assertEquals("0", l_eleDeliveryManagementRow.getReportRegDiv3());
//            assertEquals(GtlUtils.getSystemTimestamp(), l_eleDeliveryManagementRow.getReportDivUpdDate3());
            
            assertEquals("0", l_eleDeliveryManagementRow.getReportDiv4());
            assertEquals("0", l_eleDeliveryManagementRow.getReportRegDiv4());
//            assertEquals(GtlUtils.getSystemTimestamp(), l_eleDeliveryManagementRow.getReportDivUpdDate4());
            
            assertEquals("0", l_eleDeliveryManagementRow.getReportDiv5());
            assertEquals("0", l_eleDeliveryManagementRow.getReportRegDiv5());
//            assertEquals(GtlUtils.getSystemTimestamp(), l_eleDeliveryManagementRow.getReportDivUpdDate5());
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testUpdateElecDeliveryManagementCase6()
    {
        final String STR_METHOD_NAME = "testUpdateElecDeliveryManagementCase6()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_MainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_MainAccountParams);

            
            TestDBUtility.deleteAll(EleDeliveryManagementParams.TYPE);
            EleDeliveryManagementParams l_eleDeliveryManagementParams = TestDBUtility.getEleDeliveryManagementRow();
            l_eleDeliveryManagementParams.setEleDelRegiFlag(1);
            l_eleDeliveryManagementParams.setLastUpdater("111");
            l_eleDeliveryManagementParams.setTradingReportDiv("0");
            TestDBUtility.insertWithDel(l_eleDeliveryManagementParams);
            
            
            assertEquals(1, l_eleDeliveryManagementParams.getEleDelRegiFlag());
            
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_MainAccountParams);
            
            WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest l_request =
                new WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest();
            
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            
            l_impl.updateElecDeliveryManagement(l_mainAccount, l_request, "12345");
            
            EleDeliveryManagementRow l_eleDeliveryManagementRow = null;
            l_eleDeliveryManagementRow = EleDeliveryManagementDao.findRowByInstitutionCodeBranchCodeAccountCode(
                "0D",
                "381",
                "2512246");
//            assertEquals(GtlUtils.getSystemTimestamp(), l_eleDeliveryManagementRow.getLastUpdatedTimestamp());
            assertEquals("12345", l_eleDeliveryManagementRow.getLastUpdater());
            assertEquals("0", l_eleDeliveryManagementRow.getTradingReportDiv());
            
            assertNull(l_eleDeliveryManagementRow.getTradingReportRegDiv());
            assertNull(l_eleDeliveryManagementRow.getTradingReportDivUpdDate());
            
            assertNull(l_eleDeliveryManagementRow.getPositionReportDiv());
            assertNull(l_eleDeliveryManagementRow.getPositionReportRegDiv());
            assertNull(l_eleDeliveryManagementRow.getPositionReportDivUpdDate());
            

            assertNull(l_eleDeliveryManagementRow.getOpeReportDiv());
            assertNull(l_eleDeliveryManagementRow.getOpeReportRegDiv());
            assertNull(l_eleDeliveryManagementRow.getOpeReportDivUpdDate());
            
            assertNull(l_eleDeliveryManagementRow.getOrdRulReportDiv());
            assertNull(l_eleDeliveryManagementRow.getOrdRulRepRegDiv());
            assertNull(l_eleDeliveryManagementRow.getOrdRulReportDivUpdDate());
            
            assertNull(l_eleDeliveryManagementRow.getReportDiv1());
            assertNull(l_eleDeliveryManagementRow.getReportRegDiv1());
            assertNull(l_eleDeliveryManagementRow.getReportDivUpdDate1());
            
            assertNull(l_eleDeliveryManagementRow.getReportDiv2());
            assertNull(l_eleDeliveryManagementRow.getReportRegDiv2());
            assertNull(l_eleDeliveryManagementRow.getReportDivUpdDate2());
            
            assertNull(l_eleDeliveryManagementRow.getReportDiv3());
            assertNull(l_eleDeliveryManagementRow.getReportRegDiv3());
            assertNull(l_eleDeliveryManagementRow.getReportDivUpdDate3());
            
            assertNull(l_eleDeliveryManagementRow.getReportDiv4());
            assertNull(l_eleDeliveryManagementRow.getReportRegDiv4());
            assertNull(l_eleDeliveryManagementRow.getReportDivUpdDate4());
            
            assertNull( l_eleDeliveryManagementRow.getReportDiv5());
            assertNull( l_eleDeliveryManagementRow.getReportRegDiv5());
            assertNull( l_eleDeliveryManagementRow.getReportDivUpdDate5());
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.WEB3AccInfoElecDeliveryRegisterChangeServiceImpl.validateChangeItem(EleDeliveryManagementParams, WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest, boolean)'
     */
    public void testValidateChangeItemCase1()
    {
        final String STR_METHOD_NAME = "testValidateChangeItemCase1()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest l_request =
                new WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest();
            //取引報告書交付区分
            l_request.tradingReportDiv = "0";
            EleDeliveryManagementParams l_eleDeliveryManagementParams = TestDBUtility.getEleDeliveryManagementRow();
            l_eleDeliveryManagementParams.setTradingReportDiv("0");
            l_eleDeliveryManagementParams.setTradingReportRegDiv("1");

            l_impl.validateChangeItem(l_eleDeliveryManagementParams, l_request,true);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03210,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateChangeItemCase2()
    {
        final String STR_METHOD_NAME = "testValidateChangeItemCase2()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest l_request =
                new WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest();
            //取引報告書交付区分
            l_request.tradingReportDiv = "0";
            EleDeliveryManagementParams l_eleDeliveryManagementParams = TestDBUtility.getEleDeliveryManagementRow();
            l_eleDeliveryManagementParams.setTradingReportDiv("1");
            l_eleDeliveryManagementParams.setTradingReportRegDiv("0");

            l_impl.validateChangeItem(l_eleDeliveryManagementParams, l_request,true);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03210,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateChangeItemCase3()
    {
        final String STR_METHOD_NAME = "testValidateChangeItemCase3()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest l_request =
                new WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest();
            //取引報告書交付区分
            l_request.tradingReportDiv = "0";
            EleDeliveryManagementParams l_eleDeliveryManagementParams = TestDBUtility.getEleDeliveryManagementRow();
            l_eleDeliveryManagementParams.setTradingReportDiv("1");
            l_eleDeliveryManagementParams.setTradingReportRegDiv("9");

            l_impl.validateChangeItem(l_eleDeliveryManagementParams, l_request,true);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03210,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateChangeItemCase4()
    {
        final String STR_METHOD_NAME = "testValidateChangeItemCase4()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest l_request =
                new WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest();
            //取引報告書交付区分
            l_request.tradingReportDiv = "0";
            EleDeliveryManagementParams l_eleDeliveryManagementParams = TestDBUtility.getEleDeliveryManagementRow();
            l_eleDeliveryManagementParams.setTradingReportDiv("1");
            l_eleDeliveryManagementParams.setTradingReportRegDiv("1");

            l_impl.validateChangeItem(l_eleDeliveryManagementParams, l_request,false);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03210,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateChangeItemCase5()
    {
        final String STR_METHOD_NAME = "testValidateChangeItemCase5()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest l_request =
                new WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest();
            //取引報告書交付区分
            l_request.tradingReportDiv = "1";
            ///取引報告書交付区分
            l_request.positionReportDiv = "0";
            EleDeliveryManagementParams l_eleDeliveryManagementParams = TestDBUtility.getEleDeliveryManagementRow();
            l_eleDeliveryManagementParams.setTradingReportDiv("0");
            l_eleDeliveryManagementParams.setTradingReportRegDiv("1");

            l_eleDeliveryManagementParams.setPositionReportDiv("0");
            l_eleDeliveryManagementParams.setPositionReportRegDiv("1");
            l_impl.validateChangeItem(l_eleDeliveryManagementParams, l_request,true);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03210,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateChangeItemCase6()
    {
        final String STR_METHOD_NAME = "testValidateChangeItemCase6()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest l_request =
                new WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest();
            //取引報告書交付区分
            l_request.tradingReportDiv = "1";
            ///取引報告書交付区分
            l_request.positionReportDiv = "0";
            EleDeliveryManagementParams l_eleDeliveryManagementParams = TestDBUtility.getEleDeliveryManagementRow();
            l_eleDeliveryManagementParams.setTradingReportDiv("0");
            l_eleDeliveryManagementParams.setTradingReportRegDiv("1");

            l_eleDeliveryManagementParams.setPositionReportDiv("1");
            l_eleDeliveryManagementParams.setPositionReportRegDiv("1");
            l_impl.validateChangeItem(l_eleDeliveryManagementParams, l_request,true);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03210,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateChangeItemCase7()
    {
        final String STR_METHOD_NAME = "testValidateChangeItemCase7()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest l_request =
                new WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest();
            //取引報告書交付区分
            l_request.tradingReportDiv = "1";
            ///取引報告書交付区分
            l_request.positionReportDiv = "1";
            EleDeliveryManagementParams l_eleDeliveryManagementParams = TestDBUtility.getEleDeliveryManagementRow();
            l_eleDeliveryManagementParams.setTradingReportDiv("0");
            l_eleDeliveryManagementParams.setTradingReportRegDiv("1");

            l_eleDeliveryManagementParams.setPositionReportDiv("9");
            l_eleDeliveryManagementParams.setPositionReportRegDiv("1");
            l_impl.validateChangeItem(l_eleDeliveryManagementParams, l_request,true);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03210,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateChangeItemCase8()
    {
        final String STR_METHOD_NAME = "testValidateChangeItemCase8()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest l_request =
                new WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest();
            //取引報告書交付区分
            l_request.tradingReportDiv = "1";
            ///取引報告書交付区分
            l_request.positionReportDiv = "0";
            EleDeliveryManagementParams l_eleDeliveryManagementParams = TestDBUtility.getEleDeliveryManagementRow();
            l_eleDeliveryManagementParams.setTradingReportDiv("0");
            l_eleDeliveryManagementParams.setTradingReportRegDiv("1");

            l_eleDeliveryManagementParams.setPositionReportDiv("9");
            l_eleDeliveryManagementParams.setPositionReportRegDiv("0");
            l_impl.validateChangeItem(l_eleDeliveryManagementParams, l_request,true);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03210,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateChangeItemCase9()
    {
        final String STR_METHOD_NAME = "testValidateChangeItemCase9()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest l_request =
                new WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest();
            //取引報告書交付区分
            l_request.tradingReportDiv = "1";
            ///取引報告書交付区分
            l_request.positionReportDiv = "0";
            EleDeliveryManagementParams l_eleDeliveryManagementParams = TestDBUtility.getEleDeliveryManagementRow();
            l_eleDeliveryManagementParams.setTradingReportDiv("0");
            l_eleDeliveryManagementParams.setTradingReportRegDiv("1");

            l_eleDeliveryManagementParams.setPositionReportDiv("9");
            l_eleDeliveryManagementParams.setPositionReportRegDiv("9");
            l_impl.validateChangeItem(l_eleDeliveryManagementParams, l_request,true);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03210,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateChangeItemCase10()
    {
        final String STR_METHOD_NAME = "testValidateChangeItemCase10()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest l_request =
                new WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest();
            //取引報告書交付区分
            l_request.tradingReportDiv = "1";
            ///取引報告書交付区分
            l_request.positionReportDiv = "0";
            EleDeliveryManagementParams l_eleDeliveryManagementParams = TestDBUtility.getEleDeliveryManagementRow();
            l_eleDeliveryManagementParams.setTradingReportDiv("0");
            l_eleDeliveryManagementParams.setTradingReportRegDiv("1");

            l_eleDeliveryManagementParams.setPositionReportDiv("9");
            l_eleDeliveryManagementParams.setPositionReportRegDiv("1");
            l_impl.validateChangeItem(l_eleDeliveryManagementParams, l_request,false);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03210,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateChangeItemCase11()
    {
        final String STR_METHOD_NAME = "testValidateChangeItemCase11 ()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest l_request =
                new WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest();
            //取引報告書交付区分
            l_request.tradingReportDiv = "1";
            ///取引報告書交付区分
            l_request.positionReportDiv = "1";
            //運用報告書交付区分
            l_request.opeReportDiv = "0";
            EleDeliveryManagementParams l_eleDeliveryManagementParams = TestDBUtility.getEleDeliveryManagementRow();
            l_eleDeliveryManagementParams.setTradingReportDiv("0");
            l_eleDeliveryManagementParams.setTradingReportRegDiv("1");

            l_eleDeliveryManagementParams.setPositionReportDiv("0");
            l_eleDeliveryManagementParams.setPositionReportRegDiv("1");
            
            l_eleDeliveryManagementParams.setOpeReportDiv("0");
            l_eleDeliveryManagementParams.setOpeReportRegDiv("1");
            l_impl.validateChangeItem(l_eleDeliveryManagementParams, l_request,true);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03210,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testValidateChangeItemCase12()
    {
        final String STR_METHOD_NAME = "testValidateChangeItemCase12 ()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest l_request =
                new WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest();
            //取引報告書交付区分
            l_request.tradingReportDiv = "1";
            ///取引報告書交付区分
            l_request.positionReportDiv = "1";
            //運用報告書交付区分
            l_request.opeReportDiv = "1";
            EleDeliveryManagementParams l_eleDeliveryManagementParams = TestDBUtility.getEleDeliveryManagementRow();
            l_eleDeliveryManagementParams.setTradingReportDiv("0");
            l_eleDeliveryManagementParams.setTradingReportRegDiv("1");

            l_eleDeliveryManagementParams.setPositionReportDiv("0");
            l_eleDeliveryManagementParams.setPositionReportRegDiv("1");
            
            l_eleDeliveryManagementParams.setOpeReportDiv("0");
            l_eleDeliveryManagementParams.setOpeReportRegDiv("0");
            l_impl.validateChangeItem(l_eleDeliveryManagementParams, l_request,true);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03210,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateChangeItemCase13()
    {
        final String STR_METHOD_NAME = "testValidateChangeItemCase13 ()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest l_request =
                new WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest();
            //取引報告書交付区分
            l_request.tradingReportDiv = "1";
            ///取引報告書交付区分
            l_request.positionReportDiv = "1";
            //運用報告書交付区分
            l_request.opeReportDiv = "0";
            EleDeliveryManagementParams l_eleDeliveryManagementParams = TestDBUtility.getEleDeliveryManagementRow();
            l_eleDeliveryManagementParams.setTradingReportDiv("0");
            l_eleDeliveryManagementParams.setTradingReportRegDiv("1");

            l_eleDeliveryManagementParams.setPositionReportDiv("0");
            l_eleDeliveryManagementParams.setPositionReportRegDiv("1");
            
            l_eleDeliveryManagementParams.setOpeReportDiv("1");
            l_eleDeliveryManagementParams.setOpeReportRegDiv("9");
            l_impl.validateChangeItem(l_eleDeliveryManagementParams, l_request,true);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03210,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateChangeItemCase14()
    {
        final String STR_METHOD_NAME = "testValidateChangeItemCase14 ()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest l_request =
                new WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest();
            //取引報告書交付区分
            l_request.tradingReportDiv = "1";
            ///取引報告書交付区分
            l_request.positionReportDiv = "1";
            //運用報告書交付区分
            l_request.opeReportDiv = "0";
            EleDeliveryManagementParams l_eleDeliveryManagementParams = TestDBUtility.getEleDeliveryManagementRow();
            l_eleDeliveryManagementParams.setTradingReportDiv("0");
            l_eleDeliveryManagementParams.setTradingReportRegDiv("1");

            l_eleDeliveryManagementParams.setPositionReportDiv("0");
            l_eleDeliveryManagementParams.setPositionReportRegDiv("1");
            
            l_eleDeliveryManagementParams.setOpeReportDiv("1");
            l_eleDeliveryManagementParams.setOpeReportRegDiv("1");
            l_impl.validateChangeItem(l_eleDeliveryManagementParams, l_request,false);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03210,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateChangeItemCase15()
    {
        final String STR_METHOD_NAME = "testValidateChangeItemCase15 ()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest l_request =
                new WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest();
            //取引報告書交付区分
            l_request.tradingReportDiv = "1";
            ///取引報告書交付区分
            l_request.positionReportDiv = "1";
            //運用報告書交付区分
            l_request.opeReportDiv = "1";
            //約款・規定集報告書交付区分
            l_request.ordRulReportDiv = "0";
            EleDeliveryManagementParams l_eleDeliveryManagementParams = TestDBUtility.getEleDeliveryManagementRow();
            l_eleDeliveryManagementParams.setTradingReportDiv("0");
            l_eleDeliveryManagementParams.setTradingReportRegDiv("1");

            l_eleDeliveryManagementParams.setPositionReportDiv("0");
            l_eleDeliveryManagementParams.setPositionReportRegDiv("1");
            
            l_eleDeliveryManagementParams.setOpeReportDiv("0");
            l_eleDeliveryManagementParams.setOpeReportRegDiv("1");
            
            l_eleDeliveryManagementParams.setOrdRulReportDiv("0");
            l_eleDeliveryManagementParams.setOrdRulRepRegDiv("1");
            l_impl.validateChangeItem(l_eleDeliveryManagementParams, l_request,true);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03210,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateChangeItemCase16()
    {
        final String STR_METHOD_NAME = "testValidateChangeItemCase16 ()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest l_request =
                new WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest();
            //取引報告書交付区分
            l_request.tradingReportDiv = "1";
            ///取引報告書交付区分
            l_request.positionReportDiv = "1";
            //運用報告書交付区分
            l_request.opeReportDiv = "1";
            //約款・規定集報告書交付区分
            l_request.ordRulReportDiv = "0";
            EleDeliveryManagementParams l_eleDeliveryManagementParams = TestDBUtility.getEleDeliveryManagementRow();
            l_eleDeliveryManagementParams.setTradingReportDiv("0");
            l_eleDeliveryManagementParams.setTradingReportRegDiv("1");

            l_eleDeliveryManagementParams.setPositionReportDiv("0");
            l_eleDeliveryManagementParams.setPositionReportRegDiv("1");
            
            l_eleDeliveryManagementParams.setOpeReportDiv("0");
            l_eleDeliveryManagementParams.setOpeReportRegDiv("1");
            
            l_eleDeliveryManagementParams.setOrdRulReportDiv("1");
            l_eleDeliveryManagementParams.setOrdRulRepRegDiv("0");
            l_impl.validateChangeItem(l_eleDeliveryManagementParams, l_request,true);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03210,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateChangeItemCase17()
    {
        final String STR_METHOD_NAME = "testValidateChangeItemCase17 ()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest l_request =
                new WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest();
            //取引報告書交付区分
            l_request.tradingReportDiv = "1";
            ///取引報告書交付区分
            l_request.positionReportDiv = "1";
            //運用報告書交付区分
            l_request.opeReportDiv = "1";
            //約款・規定集報告書交付区分
            l_request.ordRulReportDiv = "0";
            EleDeliveryManagementParams l_eleDeliveryManagementParams = TestDBUtility.getEleDeliveryManagementRow();
            l_eleDeliveryManagementParams.setTradingReportDiv("0");
            l_eleDeliveryManagementParams.setTradingReportRegDiv("1");

            l_eleDeliveryManagementParams.setPositionReportDiv("0");
            l_eleDeliveryManagementParams.setPositionReportRegDiv("1");
            
            l_eleDeliveryManagementParams.setOpeReportDiv("0");
            l_eleDeliveryManagementParams.setOpeReportRegDiv("1");
            
            l_eleDeliveryManagementParams.setOrdRulReportDiv("1");
            l_eleDeliveryManagementParams.setOrdRulRepRegDiv("1");
            l_impl.validateChangeItem(l_eleDeliveryManagementParams, l_request,false);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03210,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateChangeItemCase18()
    {
        final String STR_METHOD_NAME = "testValidateChangeItemCase18 ()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest l_request =
                new WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest();
            //取引報告書交付区分
            l_request.tradingReportDiv = "1";
            ///取引報告書交付区分
            l_request.positionReportDiv = "1";
            //運用報告書交付区分
            l_request.opeReportDiv = "1";
            //約款・規定集報告書交付区分
            l_request.ordRulReportDiv = "1";
            l_request.report_div1 = "0";
            EleDeliveryManagementParams l_eleDeliveryManagementParams = TestDBUtility.getEleDeliveryManagementRow();
            l_eleDeliveryManagementParams.setTradingReportDiv("0");
            l_eleDeliveryManagementParams.setTradingReportRegDiv("1");

            l_eleDeliveryManagementParams.setPositionReportDiv("0");
            l_eleDeliveryManagementParams.setPositionReportRegDiv("1");
            
            l_eleDeliveryManagementParams.setOpeReportDiv("0");
            l_eleDeliveryManagementParams.setOpeReportRegDiv("1");
            
            l_eleDeliveryManagementParams.setOrdRulReportDiv("0");
            l_eleDeliveryManagementParams.setOrdRulRepRegDiv("1");
            
            l_eleDeliveryManagementParams.setReportDiv1("0");
            l_eleDeliveryManagementParams.setReportRegDiv1("1");
            l_impl.validateChangeItem(l_eleDeliveryManagementParams, l_request,true);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03210,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
   
    public void testValidateChangeItemCase19()
    {
        final String STR_METHOD_NAME = "testValidateChangeItemCase19 ()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest l_request =
                new WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest();
            //取引報告書交付区分
            l_request.tradingReportDiv = "1";
            ///取引報告書交付区分
            l_request.positionReportDiv = "1";
            //運用報告書交付区分
            l_request.opeReportDiv = "1";
            //約款・規定集報告書交付区分
            l_request.ordRulReportDiv = "1";
            l_request.report_div1 = "1";
            EleDeliveryManagementParams l_eleDeliveryManagementParams = TestDBUtility.getEleDeliveryManagementRow();
            l_eleDeliveryManagementParams.setTradingReportDiv("0");
            l_eleDeliveryManagementParams.setTradingReportRegDiv("1");

            l_eleDeliveryManagementParams.setPositionReportDiv("0");
            l_eleDeliveryManagementParams.setPositionReportRegDiv("1");
            
            l_eleDeliveryManagementParams.setOpeReportDiv("0");
            l_eleDeliveryManagementParams.setOpeReportRegDiv("1");
            
            l_eleDeliveryManagementParams.setOrdRulReportDiv("0");
            l_eleDeliveryManagementParams.setOrdRulRepRegDiv("1");
            
            l_eleDeliveryManagementParams.setReportDiv1("0");
            l_eleDeliveryManagementParams.setReportRegDiv1("0");
            l_impl.validateChangeItem(l_eleDeliveryManagementParams, l_request,true);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03210,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateChangeItemCase20()
    {
        final String STR_METHOD_NAME = "testValidateChangeItemCase20 ()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest l_request =
                new WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest();
            //取引報告書交付区分
            l_request.tradingReportDiv = "1";
            ///取引報告書交付区分
            l_request.positionReportDiv = "1";
            //運用報告書交付区分
            l_request.opeReportDiv = "1";
            //約款・規定集報告書交付区分
            l_request.ordRulReportDiv = "1";
            l_request.report_div1 = "0";
            EleDeliveryManagementParams l_eleDeliveryManagementParams = TestDBUtility.getEleDeliveryManagementRow();
            l_eleDeliveryManagementParams.setTradingReportDiv("0");
            l_eleDeliveryManagementParams.setTradingReportRegDiv("1");

            l_eleDeliveryManagementParams.setPositionReportDiv("0");
            l_eleDeliveryManagementParams.setPositionReportRegDiv("1");
            
            l_eleDeliveryManagementParams.setOpeReportDiv("0");
            l_eleDeliveryManagementParams.setOpeReportRegDiv("1");
            
            l_eleDeliveryManagementParams.setOrdRulReportDiv("0");
            l_eleDeliveryManagementParams.setOrdRulRepRegDiv("1");
            
            l_eleDeliveryManagementParams.setReportDiv1("1");
            l_eleDeliveryManagementParams.setReportRegDiv1("1");
            l_impl.validateChangeItem(l_eleDeliveryManagementParams, l_request,false);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03210,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateChangeItemCase21()
    {
        final String STR_METHOD_NAME = "testValidateChangeItemCase21 ()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest l_request =
                new WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest();
            //取引報告書交付区分
            l_request.tradingReportDiv = "1";
            ///取引報告書交付区分
            l_request.positionReportDiv = "1";
            //運用報告書交付区分
            l_request.opeReportDiv = "1";
            //約款・規定集報告書交付区分
            l_request.ordRulReportDiv = "1";
            l_request.report_div1 = "1";
            l_request.report_div2 = "0";
            EleDeliveryManagementParams l_eleDeliveryManagementParams = TestDBUtility.getEleDeliveryManagementRow();
            l_eleDeliveryManagementParams.setTradingReportDiv("0");
            l_eleDeliveryManagementParams.setTradingReportRegDiv("1");

            l_eleDeliveryManagementParams.setPositionReportDiv("0");
            l_eleDeliveryManagementParams.setPositionReportRegDiv("1");
            
            l_eleDeliveryManagementParams.setOpeReportDiv("0");
            l_eleDeliveryManagementParams.setOpeReportRegDiv("1");
            
            l_eleDeliveryManagementParams.setOrdRulReportDiv("0");
            l_eleDeliveryManagementParams.setOrdRulRepRegDiv("1");
            
            l_eleDeliveryManagementParams.setReportDiv1("0");
            l_eleDeliveryManagementParams.setReportRegDiv1("1");
            
            l_eleDeliveryManagementParams.setReportDiv2("0");
            l_eleDeliveryManagementParams.setReportRegDiv2("1");
            l_impl.validateChangeItem(l_eleDeliveryManagementParams, l_request,true);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03210,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateChangeItemCase22()
    {
        final String STR_METHOD_NAME = "testValidateChangeItemCase22 ()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest l_request =
                new WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest();
            //取引報告書交付区分
            l_request.tradingReportDiv = "1";
            ///取引報告書交付区分
            l_request.positionReportDiv = "1";
            //運用報告書交付区分
            l_request.opeReportDiv = "1";
            //約款・規定集報告書交付区分
            l_request.ordRulReportDiv = "1";
            l_request.report_div1 = "1";
            l_request.report_div2 = "1";
            EleDeliveryManagementParams l_eleDeliveryManagementParams = TestDBUtility.getEleDeliveryManagementRow();
            l_eleDeliveryManagementParams.setTradingReportDiv("0");
            l_eleDeliveryManagementParams.setTradingReportRegDiv("1");

            l_eleDeliveryManagementParams.setPositionReportDiv("0");
            l_eleDeliveryManagementParams.setPositionReportRegDiv("1");
            
            l_eleDeliveryManagementParams.setOpeReportDiv("0");
            l_eleDeliveryManagementParams.setOpeReportRegDiv("1");
            
            l_eleDeliveryManagementParams.setOrdRulReportDiv("0");
            l_eleDeliveryManagementParams.setOrdRulRepRegDiv("1");
            
            l_eleDeliveryManagementParams.setReportDiv1("0");
            l_eleDeliveryManagementParams.setReportRegDiv1("1");
            
            l_eleDeliveryManagementParams.setReportDiv2("0");
            l_eleDeliveryManagementParams.setReportRegDiv2("0");
            l_impl.validateChangeItem(l_eleDeliveryManagementParams, l_request,true);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03210,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateChangeItemCase23()
    {
        final String STR_METHOD_NAME = "testValidateChangeItemCase23 ()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest l_request =
                new WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest();
            //取引報告書交付区分
            l_request.tradingReportDiv = "1";
            ///取引報告書交付区分
            l_request.positionReportDiv = "1";
            //運用報告書交付区分
            l_request.opeReportDiv = "1";
            //約款・規定集報告書交付区分
            l_request.ordRulReportDiv = "1";
            l_request.report_div1 = "1";
            l_request.report_div2 = "0";
            EleDeliveryManagementParams l_eleDeliveryManagementParams = TestDBUtility.getEleDeliveryManagementRow();
            l_eleDeliveryManagementParams.setTradingReportDiv("0");
            l_eleDeliveryManagementParams.setTradingReportRegDiv("1");

            l_eleDeliveryManagementParams.setPositionReportDiv("0");
            l_eleDeliveryManagementParams.setPositionReportRegDiv("1");
            
            l_eleDeliveryManagementParams.setOpeReportDiv("0");
            l_eleDeliveryManagementParams.setOpeReportRegDiv("1");
            
            l_eleDeliveryManagementParams.setOrdRulReportDiv("0");
            l_eleDeliveryManagementParams.setOrdRulRepRegDiv("1");
            
            l_eleDeliveryManagementParams.setReportDiv1("0");
            l_eleDeliveryManagementParams.setReportRegDiv1("1");
            
            l_eleDeliveryManagementParams.setReportDiv2("1");
            l_eleDeliveryManagementParams.setReportRegDiv2("1");
            l_impl.validateChangeItem(l_eleDeliveryManagementParams, l_request,false);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03210,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateChangeItemCase24()
    {
        final String STR_METHOD_NAME = "testValidateChangeItemCase24 ()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest l_request =
                new WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest();
            //取引報告書交付区分
            l_request.tradingReportDiv = "1";
            ///取引報告書交付区分
            l_request.positionReportDiv = "1";
            //運用報告書交付区分
            l_request.opeReportDiv = "1";
            //約款・規定集報告書交付区分
            l_request.ordRulReportDiv = "1";
            l_request.report_div1 = "1";
            l_request.report_div2 = "1";
            l_request.report_div3 = "0";
            EleDeliveryManagementParams l_eleDeliveryManagementParams = TestDBUtility.getEleDeliveryManagementRow();
            l_eleDeliveryManagementParams.setTradingReportDiv("0");
            l_eleDeliveryManagementParams.setTradingReportRegDiv("1");

            l_eleDeliveryManagementParams.setPositionReportDiv("0");
            l_eleDeliveryManagementParams.setPositionReportRegDiv("1");
            
            l_eleDeliveryManagementParams.setOpeReportDiv("0");
            l_eleDeliveryManagementParams.setOpeReportRegDiv("1");
            
            l_eleDeliveryManagementParams.setOrdRulReportDiv("0");
            l_eleDeliveryManagementParams.setOrdRulRepRegDiv("1");
            
            l_eleDeliveryManagementParams.setReportDiv1("0");
            l_eleDeliveryManagementParams.setReportRegDiv1("1");
            
            l_eleDeliveryManagementParams.setReportDiv2("0");
            l_eleDeliveryManagementParams.setReportRegDiv2("1");
            
            l_eleDeliveryManagementParams.setReportDiv3("0");
            l_eleDeliveryManagementParams.setReportRegDiv3("1");
            l_impl.validateChangeItem(l_eleDeliveryManagementParams, l_request,true);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03210,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateChangeItemCase25()
    {
        final String STR_METHOD_NAME = "testValidateChangeItemCase25 ()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest l_request =
                new WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest();
            //取引報告書交付区分
            l_request.tradingReportDiv = "1";
            ///取引報告書交付区分
            l_request.positionReportDiv = "1";
            //運用報告書交付区分
            l_request.opeReportDiv = "1";
            //約款・規定集報告書交付区分
            l_request.ordRulReportDiv = "1";
            l_request.report_div1 = "1";
            l_request.report_div2 = "1";
            l_request.report_div3 = "1";
            EleDeliveryManagementParams l_eleDeliveryManagementParams = TestDBUtility.getEleDeliveryManagementRow();
            l_eleDeliveryManagementParams.setTradingReportDiv("0");
            l_eleDeliveryManagementParams.setTradingReportRegDiv("1");

            l_eleDeliveryManagementParams.setPositionReportDiv("0");
            l_eleDeliveryManagementParams.setPositionReportRegDiv("1");
            
            l_eleDeliveryManagementParams.setOpeReportDiv("0");
            l_eleDeliveryManagementParams.setOpeReportRegDiv("1");
            
            l_eleDeliveryManagementParams.setOrdRulReportDiv("0");
            l_eleDeliveryManagementParams.setOrdRulRepRegDiv("1");
            
            l_eleDeliveryManagementParams.setReportDiv1("0");
            l_eleDeliveryManagementParams.setReportRegDiv1("1");
            
            l_eleDeliveryManagementParams.setReportDiv2("0");
            l_eleDeliveryManagementParams.setReportRegDiv2("1");
            
            l_eleDeliveryManagementParams.setReportDiv3("0");
            l_eleDeliveryManagementParams.setReportRegDiv3("0");
            l_impl.validateChangeItem(l_eleDeliveryManagementParams, l_request,true);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03210,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateChangeItemCase26()
    {
        final String STR_METHOD_NAME = "testValidateChangeItemCase26 ()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest l_request =
                new WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest();
            //取引報告書交付区分
            l_request.tradingReportDiv = "1";
            ///取引報告書交付区分
            l_request.positionReportDiv = "1";
            //運用報告書交付区分
            l_request.opeReportDiv = "1";
            //約款・規定集報告書交付区分
            l_request.ordRulReportDiv = "1";
            l_request.report_div1 = "1";
            l_request.report_div2 = "1";
            l_request.report_div3 = "0";
            EleDeliveryManagementParams l_eleDeliveryManagementParams = TestDBUtility.getEleDeliveryManagementRow();
            l_eleDeliveryManagementParams.setTradingReportDiv("0");
            l_eleDeliveryManagementParams.setTradingReportRegDiv("1");

            l_eleDeliveryManagementParams.setPositionReportDiv("0");
            l_eleDeliveryManagementParams.setPositionReportRegDiv("1");
            
            l_eleDeliveryManagementParams.setOpeReportDiv("0");
            l_eleDeliveryManagementParams.setOpeReportRegDiv("1");
            
            l_eleDeliveryManagementParams.setOrdRulReportDiv("0");
            l_eleDeliveryManagementParams.setOrdRulRepRegDiv("1");
            
            l_eleDeliveryManagementParams.setReportDiv1("0");
            l_eleDeliveryManagementParams.setReportRegDiv1("1");
            
            l_eleDeliveryManagementParams.setReportDiv2("0");
            l_eleDeliveryManagementParams.setReportRegDiv2("1");
            
            l_eleDeliveryManagementParams.setReportDiv3("1");
            l_eleDeliveryManagementParams.setReportRegDiv3("1");
            l_impl.validateChangeItem(l_eleDeliveryManagementParams, l_request,false);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03210,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateChangeItemCase27()
    {
        final String STR_METHOD_NAME = "testValidateChangeItemCase27 ()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest l_request =
                new WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest();
            //取引報告書交付区分
            l_request.tradingReportDiv = "1";
            ///取引報告書交付区分
            l_request.positionReportDiv = "1";
            //運用報告書交付区分
            l_request.opeReportDiv = "1";
            //約款・規定集報告書交付区分
            l_request.ordRulReportDiv = "1";
            l_request.report_div1 = "1";
            l_request.report_div2 = "1";
            l_request.report_div3 = "1";
            l_request.report_div4 = "1";
            EleDeliveryManagementParams l_eleDeliveryManagementParams = TestDBUtility.getEleDeliveryManagementRow();
            l_eleDeliveryManagementParams.setTradingReportDiv("0");
            l_eleDeliveryManagementParams.setTradingReportRegDiv("1");

            l_eleDeliveryManagementParams.setPositionReportDiv("0");
            l_eleDeliveryManagementParams.setPositionReportRegDiv("1");
            
            l_eleDeliveryManagementParams.setOpeReportDiv("0");
            l_eleDeliveryManagementParams.setOpeReportRegDiv("1");
            
            l_eleDeliveryManagementParams.setOrdRulReportDiv("0");
            l_eleDeliveryManagementParams.setOrdRulRepRegDiv("1");
            
            l_eleDeliveryManagementParams.setReportDiv1("0");
            l_eleDeliveryManagementParams.setReportRegDiv1("1");
            
            l_eleDeliveryManagementParams.setReportDiv2("0");
            l_eleDeliveryManagementParams.setReportRegDiv2("1");
            
            l_eleDeliveryManagementParams.setReportDiv3("0");
            l_eleDeliveryManagementParams.setReportRegDiv3("1");
            
            l_eleDeliveryManagementParams.setReportDiv4("1");
            l_eleDeliveryManagementParams.setReportRegDiv4("1");
            l_impl.validateChangeItem(l_eleDeliveryManagementParams, l_request,true);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03210,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateChangeItemCase28()
    {
        final String STR_METHOD_NAME = "testValidateChangeItemCase28 ()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest l_request =
                new WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest();
            //取引報告書交付区分
            l_request.tradingReportDiv = "1";
            ///取引報告書交付区分
            l_request.positionReportDiv = "1";
            //運用報告書交付区分
            l_request.opeReportDiv = "1";
            //約款・規定集報告書交付区分
            l_request.ordRulReportDiv = "1";
            l_request.report_div1 = "1";
            l_request.report_div2 = "1";
            l_request.report_div3 = "1";
            l_request.report_div4 = "1";
            EleDeliveryManagementParams l_eleDeliveryManagementParams = TestDBUtility.getEleDeliveryManagementRow();
            l_eleDeliveryManagementParams.setTradingReportDiv("0");
            l_eleDeliveryManagementParams.setTradingReportRegDiv("1");

            l_eleDeliveryManagementParams.setPositionReportDiv("0");
            l_eleDeliveryManagementParams.setPositionReportRegDiv("1");
            
            l_eleDeliveryManagementParams.setOpeReportDiv("0");
            l_eleDeliveryManagementParams.setOpeReportRegDiv("1");
            
            l_eleDeliveryManagementParams.setOrdRulReportDiv("0");
            l_eleDeliveryManagementParams.setOrdRulRepRegDiv("1");
            
            l_eleDeliveryManagementParams.setReportDiv1("0");
            l_eleDeliveryManagementParams.setReportRegDiv1("1");
            
            l_eleDeliveryManagementParams.setReportDiv2("0");
            l_eleDeliveryManagementParams.setReportRegDiv2("1");
            
            l_eleDeliveryManagementParams.setReportDiv3("0");
            l_eleDeliveryManagementParams.setReportRegDiv3("1");
            
            l_eleDeliveryManagementParams.setReportDiv4("0");
            l_eleDeliveryManagementParams.setReportRegDiv4("0");
            l_impl.validateChangeItem(l_eleDeliveryManagementParams, l_request,true);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03210,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateChangeItemCase29()
    {
        final String STR_METHOD_NAME = "testValidateChangeItemCase29 ()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest l_request =
                new WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest();
            //取引報告書交付区分
            l_request.tradingReportDiv = "1";
            ///取引報告書交付区分
            l_request.positionReportDiv = "1";
            //運用報告書交付区分
            l_request.opeReportDiv = "1";
            //約款・規定集報告書交付区分
            l_request.ordRulReportDiv = "1";
            l_request.report_div1 = "1";
            l_request.report_div2 = "1";
            l_request.report_div3 = "1";
            l_request.report_div4 = "0";
            EleDeliveryManagementParams l_eleDeliveryManagementParams = TestDBUtility.getEleDeliveryManagementRow();
            l_eleDeliveryManagementParams.setTradingReportDiv("0");
            l_eleDeliveryManagementParams.setTradingReportRegDiv("1");

            l_eleDeliveryManagementParams.setPositionReportDiv("0");
            l_eleDeliveryManagementParams.setPositionReportRegDiv("1");
            
            l_eleDeliveryManagementParams.setOpeReportDiv("0");
            l_eleDeliveryManagementParams.setOpeReportRegDiv("1");
            
            l_eleDeliveryManagementParams.setOrdRulReportDiv("0");
            l_eleDeliveryManagementParams.setOrdRulRepRegDiv("1");
            
            l_eleDeliveryManagementParams.setReportDiv1("0");
            l_eleDeliveryManagementParams.setReportRegDiv1("1");
            
            l_eleDeliveryManagementParams.setReportDiv2("0");
            l_eleDeliveryManagementParams.setReportRegDiv2("1");
            
            l_eleDeliveryManagementParams.setReportDiv3("0");
            l_eleDeliveryManagementParams.setReportRegDiv3("1");
            
            l_eleDeliveryManagementParams.setReportDiv4("1");
            l_eleDeliveryManagementParams.setReportRegDiv4("1");
            l_impl.validateChangeItem(l_eleDeliveryManagementParams, l_request,false);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03210,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateChangeItemCase30()
    {
        final String STR_METHOD_NAME = "testValidateChangeItemCase30 ()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest l_request =
                new WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest();
            //取引報告書交付区分
            l_request.tradingReportDiv = "1";
            ///取引報告書交付区分
            l_request.positionReportDiv = "1";
            //運用報告書交付区分
            l_request.opeReportDiv = "1";
            //約款・規定集報告書交付区分
            l_request.ordRulReportDiv = "1";
            l_request.report_div1 = "1";
            l_request.report_div2 = "1";
            l_request.report_div3 = "1";
            l_request.report_div4 = "1";
            l_request.report_div5 = "1";
            EleDeliveryManagementParams l_eleDeliveryManagementParams = TestDBUtility.getEleDeliveryManagementRow();
            l_eleDeliveryManagementParams.setTradingReportDiv("0");
            l_eleDeliveryManagementParams.setTradingReportRegDiv("1");

            l_eleDeliveryManagementParams.setPositionReportDiv("0");
            l_eleDeliveryManagementParams.setPositionReportRegDiv("1");
            
            l_eleDeliveryManagementParams.setOpeReportDiv("0");
            l_eleDeliveryManagementParams.setOpeReportRegDiv("1");
            
            l_eleDeliveryManagementParams.setOrdRulReportDiv("0");
            l_eleDeliveryManagementParams.setOrdRulRepRegDiv("1");
            
            l_eleDeliveryManagementParams.setReportDiv1("0");
            l_eleDeliveryManagementParams.setReportRegDiv1("1");
            
            l_eleDeliveryManagementParams.setReportDiv2("0");
            l_eleDeliveryManagementParams.setReportRegDiv2("1");
            
            l_eleDeliveryManagementParams.setReportDiv3("0");
            l_eleDeliveryManagementParams.setReportRegDiv3("1");
            
            l_eleDeliveryManagementParams.setReportDiv4("0");
            l_eleDeliveryManagementParams.setReportRegDiv4("1");
            
            l_eleDeliveryManagementParams.setReportDiv5("1");
            l_eleDeliveryManagementParams.setReportRegDiv5("1");
            l_impl.validateChangeItem(l_eleDeliveryManagementParams, l_request,true);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03210,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateChangeItemCase31()
    {
        final String STR_METHOD_NAME = "testValidateChangeItemCase31 ()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest l_request =
                new WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest();
            //取引報告書交付区分
            l_request.tradingReportDiv = "1";
            ///取引報告書交付区分
            l_request.positionReportDiv = "1";
            //運用報告書交付区分
            l_request.opeReportDiv = "1";
            //約款・規定集報告書交付区分
            l_request.ordRulReportDiv = "1";
            l_request.report_div1 = "1";
            l_request.report_div2 = "1";
            l_request.report_div3 = "1";
            l_request.report_div4 = "1";
            l_request.report_div5 = "1";
            EleDeliveryManagementParams l_eleDeliveryManagementParams = TestDBUtility.getEleDeliveryManagementRow();
            l_eleDeliveryManagementParams.setTradingReportDiv("0");
            l_eleDeliveryManagementParams.setTradingReportRegDiv("1");

            l_eleDeliveryManagementParams.setPositionReportDiv("0");
            l_eleDeliveryManagementParams.setPositionReportRegDiv("1");
            
            l_eleDeliveryManagementParams.setOpeReportDiv("0");
            l_eleDeliveryManagementParams.setOpeReportRegDiv("1");
            
            l_eleDeliveryManagementParams.setOrdRulReportDiv("0");
            l_eleDeliveryManagementParams.setOrdRulRepRegDiv("1");
            
            l_eleDeliveryManagementParams.setReportDiv1("0");
            l_eleDeliveryManagementParams.setReportRegDiv1("1");
            
            l_eleDeliveryManagementParams.setReportDiv2("0");
            l_eleDeliveryManagementParams.setReportRegDiv2("1");
            
            l_eleDeliveryManagementParams.setReportDiv3("0");
            l_eleDeliveryManagementParams.setReportRegDiv3("1");
            
            l_eleDeliveryManagementParams.setReportDiv4("0");
            l_eleDeliveryManagementParams.setReportRegDiv4("1");
            
            l_eleDeliveryManagementParams.setReportDiv5("0");
            l_eleDeliveryManagementParams.setReportRegDiv5("0");
            l_impl.validateChangeItem(l_eleDeliveryManagementParams, l_request,true);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03210,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateChangeItemCase32()
    {
        final String STR_METHOD_NAME = "testValidateChangeItemCase32 ()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest l_request =
                new WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest();
            //取引報告書交付区分
            l_request.tradingReportDiv = "1";
            ///取引報告書交付区分
            l_request.positionReportDiv = "1";
            //運用報告書交付区分
            l_request.opeReportDiv = "1";
            //約款・規定集報告書交付区分
            l_request.ordRulReportDiv = "1";
            l_request.report_div1 = "1";
            l_request.report_div2 = "1";
            l_request.report_div3 = "1";
            l_request.report_div4 = "1";
            l_request.report_div5 = "0";
            EleDeliveryManagementParams l_eleDeliveryManagementParams = TestDBUtility.getEleDeliveryManagementRow();
            l_eleDeliveryManagementParams.setTradingReportDiv("0");
            l_eleDeliveryManagementParams.setTradingReportRegDiv("1");

            l_eleDeliveryManagementParams.setPositionReportDiv("0");
            l_eleDeliveryManagementParams.setPositionReportRegDiv("1");
            
            l_eleDeliveryManagementParams.setOpeReportDiv("0");
            l_eleDeliveryManagementParams.setOpeReportRegDiv("1");
            
            l_eleDeliveryManagementParams.setOrdRulReportDiv("0");
            l_eleDeliveryManagementParams.setOrdRulRepRegDiv("1");
            
            l_eleDeliveryManagementParams.setReportDiv1("0");
            l_eleDeliveryManagementParams.setReportRegDiv1("1");
            
            l_eleDeliveryManagementParams.setReportDiv2("0");
            l_eleDeliveryManagementParams.setReportRegDiv2("1");
            
            l_eleDeliveryManagementParams.setReportDiv3("0");
            l_eleDeliveryManagementParams.setReportRegDiv3("1");
            
            l_eleDeliveryManagementParams.setReportDiv4("0");
            l_eleDeliveryManagementParams.setReportRegDiv4("1");
            
            l_eleDeliveryManagementParams.setReportDiv5("1");
            l_eleDeliveryManagementParams.setReportRegDiv5("1");
            l_impl.validateChangeItem(l_eleDeliveryManagementParams, l_request,false);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03210,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateChangeItemCase33()
    {
        final String STR_METHOD_NAME = "testValidateChangeItemCase33 ()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest l_request =
                new WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest();
            //取引報告書交付区分
            l_request.tradingReportDiv = "1";
            ///取引報告書交付区分
            l_request.positionReportDiv = "1";
            //運用報告書交付区分
            l_request.opeReportDiv = "1";
            //約款・規定集報告書交付区分
            l_request.ordRulReportDiv = "1";
            l_request.report_div1 = "1";
            l_request.report_div2 = "1";
            l_request.report_div3 = "1";
            l_request.report_div4 = "1";
            l_request.report_div5 = "1";
            EleDeliveryManagementParams l_eleDeliveryManagementParams = TestDBUtility.getEleDeliveryManagementRow();
            l_eleDeliveryManagementParams.setTradingReportDiv("0");
            l_eleDeliveryManagementParams.setTradingReportRegDiv("1");

            l_eleDeliveryManagementParams.setPositionReportDiv("0");
            l_eleDeliveryManagementParams.setPositionReportRegDiv("1");
            
            l_eleDeliveryManagementParams.setOpeReportDiv("0");
            l_eleDeliveryManagementParams.setOpeReportRegDiv("1");
            
            l_eleDeliveryManagementParams.setOrdRulReportDiv("0");
            l_eleDeliveryManagementParams.setOrdRulRepRegDiv("1");
            
            l_eleDeliveryManagementParams.setReportDiv1("0");
            l_eleDeliveryManagementParams.setReportRegDiv1("1");
            
            l_eleDeliveryManagementParams.setReportDiv2("0");
            l_eleDeliveryManagementParams.setReportRegDiv2("1");
            
            l_eleDeliveryManagementParams.setReportDiv3("0");
            l_eleDeliveryManagementParams.setReportRegDiv3("1");
            
            l_eleDeliveryManagementParams.setReportDiv4("0");
            l_eleDeliveryManagementParams.setReportRegDiv4("1");
            
            l_eleDeliveryManagementParams.setReportDiv5("0");
            l_eleDeliveryManagementParams.setReportRegDiv5("1");
            l_impl.validateChangeItem(l_eleDeliveryManagementParams, l_request,true);
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateChangeItemCase34()
    {
        final String STR_METHOD_NAME = "testValidateChangeItemCase34 ()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest l_request =
                new WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest();
            
            EleDeliveryManagementParams l_eleDeliveryManagementParams = TestDBUtility.getEleDeliveryManagementRow();
            l_eleDeliveryManagementParams.setTradingReportDiv("0");
            l_eleDeliveryManagementParams.setTradingReportRegDiv("1");

            l_eleDeliveryManagementParams.setPositionReportDiv("0");
            l_eleDeliveryManagementParams.setPositionReportRegDiv("1");
            
            l_eleDeliveryManagementParams.setOpeReportDiv("0");
            l_eleDeliveryManagementParams.setOpeReportRegDiv("1");
            
            l_eleDeliveryManagementParams.setOrdRulReportDiv("0");
            l_eleDeliveryManagementParams.setOrdRulRepRegDiv("1");
            
            l_eleDeliveryManagementParams.setReportDiv1("0");
            l_eleDeliveryManagementParams.setReportRegDiv1("1");
            
            l_eleDeliveryManagementParams.setReportDiv2("0");
            l_eleDeliveryManagementParams.setReportRegDiv2("1");
            
            l_eleDeliveryManagementParams.setReportDiv3("0");
            l_eleDeliveryManagementParams.setReportRegDiv3("1");
            
            l_eleDeliveryManagementParams.setReportDiv4("0");
            l_eleDeliveryManagementParams.setReportRegDiv4("1");
            
            l_eleDeliveryManagementParams.setReportDiv5("0");
            l_eleDeliveryManagementParams.setReportRegDiv5("1");
            l_impl.validateChangeItem(l_eleDeliveryManagementParams, l_request,true);
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.WEB3AccInfoElecDeliveryRegisterChangeServiceImpl.isEleDeliveryInfoChangeSonar(WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest)'
     */
    public void testIsEleDeliveryInfoChangeSonarCase1()
    {
        final String STR_METHOD_NAME = "testIsEleDeliveryInfoChangeSonarCase1()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest l_request =
                new WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest();
            //取引報告書交付区分
            l_request.tradingReportDiv = "0";
            //取引残高報告書交付区分
            l_request.positionReportDiv = null;
            //運用報告書交付区分
            l_request.opeReportDiv = null;
            boolean l_blnIsEleDeliveryInfoChangeSonar = l_impl.isEleDeliveryInfoChangeSonar(l_request);
            assertTrue(l_blnIsEleDeliveryInfoChangeSonar);
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testIsEleDeliveryInfoChangeSonarCase2()
    {
        final String STR_METHOD_NAME = "testIsEleDeliveryInfoChangeSonarCase2()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest l_request =
                new WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest();
            //取引報告書交付区分
            l_request.tradingReportDiv = null;
            //取引残高報告書交付区分
            l_request.positionReportDiv = "0";
            //運用報告書交付区分
            l_request.opeReportDiv = null;
            boolean l_blnIsEleDeliveryInfoChangeSonar = l_impl.isEleDeliveryInfoChangeSonar(l_request);
            assertTrue(l_blnIsEleDeliveryInfoChangeSonar);
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testIsEleDeliveryInfoChangeSonarCase3()
    {
        final String STR_METHOD_NAME = "testIsEleDeliveryInfoChangeSonarCase3()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest l_request =
                new WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest();
            //取引報告書交付区分
            l_request.tradingReportDiv = null;
            //取引残高報告書交付区分
            l_request.positionReportDiv = null;
            //運用報告書交付区分
            l_request.opeReportDiv = "0";
            boolean l_blnIsEleDeliveryInfoChangeSonar = l_impl.isEleDeliveryInfoChangeSonar(l_request);
            assertTrue(l_blnIsEleDeliveryInfoChangeSonar);
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testIsEleDeliveryInfoChangeSonarCase4()
    {
        final String STR_METHOD_NAME = "testIsEleDeliveryInfoChangeSonarCase4()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest l_request =
                new WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest();
            //取引報告書交付区分
            l_request.tradingReportDiv = null;
            //取引残高報告書交付区分
            l_request.positionReportDiv = null;
            //運用報告書交付区分
            l_request.opeReportDiv = null;
            boolean l_blnIsEleDeliveryInfoChangeSonar = l_impl.isEleDeliveryInfoChangeSonar(l_request);
            assertFalse(l_blnIsEleDeliveryInfoChangeSonar);
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.WEB3AccInfoElecDeliveryRegisterChangeServiceImpl.createHostConditionRegVoucher(EleDeliveryManagementParams, WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest, String)'
     */
    public void testCreateHostConditionRegVoucherCase1()
    {
        final String STR_METHOD_NAME = "testCreateHostConditionRegVoucherCase1()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest l_request =
                new WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest();

            EleDeliveryManagementParams l_eleDeliveryManagementParams = TestDBUtility.getEleDeliveryManagementRow();
            l_eleDeliveryManagementParams.setOpeReportDiv("1");
            HostConditionRegVoucherParams l_hostConditionRegVoucherParams =
                l_impl.createHostConditionRegVoucher(l_eleDeliveryManagementParams, l_request, "1011");
            assertEquals("0D", l_hostConditionRegVoucherParams.getInstitutionCode());
            assertEquals("381", l_hostConditionRegVoucherParams.getBranchCode());
            assertEquals("2512246", l_hostConditionRegVoucherParams.getAccountCode());
            assertEquals("1011", l_hostConditionRegVoucherParams.getTraderCode());
            
            assertNull(l_hostConditionRegVoucherParams.getPosReportCycleDiv());
            assertNull(l_hostConditionRegVoucherParams.getTradingEReportDiv());
            assertNull(l_hostConditionRegVoucherParams.getInvEReportDiv());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateHostConditionRegVoucherCase2()
    {
        final String STR_METHOD_NAME = "testCreateHostConditionRegVoucherCase2()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest l_request =
                new WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest();
            //取引残高報告書交付区分
            l_request.positionReportDiv = "0";
            //取引報告書交付区分
            l_request.tradingReportDiv = "0";
            //運用報告書交付区分
            l_request.opeReportDiv = "0";
            EleDeliveryManagementParams l_eleDeliveryManagementParams = TestDBUtility.getEleDeliveryManagementRow();
            l_eleDeliveryManagementParams.setOpeReportDiv("1");
            HostConditionRegVoucherParams l_hostConditionRegVoucherParams =
                l_impl.createHostConditionRegVoucher(l_eleDeliveryManagementParams, l_request, "1011");
            assertEquals("0D", l_hostConditionRegVoucherParams.getInstitutionCode());
            assertEquals("381", l_hostConditionRegVoucherParams.getBranchCode());
            assertEquals("2512246", l_hostConditionRegVoucherParams.getAccountCode());
            assertEquals("1011", l_hostConditionRegVoucherParams.getTraderCode());
            
            assertEquals("0", l_hostConditionRegVoucherParams.getPosReportCycleDiv());
            assertEquals("0", l_hostConditionRegVoucherParams.getTradingEReportDiv());
            assertEquals("0", l_hostConditionRegVoucherParams.getInvEReportDiv());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateHostConditionRegVoucherCase3()
    {
        final String STR_METHOD_NAME = "testCreateHostConditionRegVoucherCase3()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest l_request =
                new WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest();
            //取引残高報告書交付区分
            l_request.positionReportDiv = "1";
            //取引報告書交付区分
            l_request.tradingReportDiv = "1";
            //運用報告書交付区分
            l_request.opeReportDiv = "1";
            EleDeliveryManagementParams l_eleDeliveryManagementParams = TestDBUtility.getEleDeliveryManagementRow();
            l_eleDeliveryManagementParams.setOpeReportDiv("1");
            HostConditionRegVoucherParams l_hostConditionRegVoucherParams =
                l_impl.createHostConditionRegVoucher(l_eleDeliveryManagementParams, l_request, "1011");
            assertEquals("0D", l_hostConditionRegVoucherParams.getInstitutionCode());
            assertEquals("381", l_hostConditionRegVoucherParams.getBranchCode());
            assertEquals("2512246", l_hostConditionRegVoucherParams.getAccountCode());
            assertEquals("1011", l_hostConditionRegVoucherParams.getTraderCode());
            
            assertEquals("9", l_hostConditionRegVoucherParams.getPosReportCycleDiv());
            assertEquals("1", l_hostConditionRegVoucherParams.getTradingEReportDiv());
            assertEquals("1", l_hostConditionRegVoucherParams.getInvEReportDiv());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.WEB3AccInfoElecDeliveryRegisterChangeServiceImpl.createEleDeliveryInfo(WEB3GentradeMainAccount)'
     */
    public void testCreateEleDeliveryInfoCase1()
    {
        final String STR_METHOD_NAME = "testCreateEleDeliveryInfoCase1()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_MainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_MainAccountParams);

            
            TestDBUtility.deleteAll(EleDeliveryManagementParams.TYPE);
//            EleDeliveryManagementParams l_eleDeliveryManagementParams = TestDBUtility.getEleDeliveryManagementRow();
//            TestDBUtility.insertWithDel(l_eleDeliveryManagementParams);
            
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_MainAccountParams);
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            
            WEB3AccInfoEleDeliveryInfo l_eleDeliveryInfo = l_impl.createEleDeliveryInfo(l_mainAccount);
            assertNull(l_eleDeliveryInfo);
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testCreateEleDeliveryInfoCase2()
    {
        final String STR_METHOD_NAME = "testCreateEleDeliveryInfoCase2()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_MainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_MainAccountParams);

            
            TestDBUtility.deleteAll(EleDeliveryManagementParams.TYPE);
            EleDeliveryManagementParams l_eleDeliveryManagementParams = TestDBUtility.getEleDeliveryManagementRow();
            l_eleDeliveryManagementParams.setTradingReportRegDiv("0");
            l_eleDeliveryManagementParams.setPositionReportDiv("0");
            l_eleDeliveryManagementParams.setPositionReportRegDiv("0");
            l_eleDeliveryManagementParams.setOpeReportRegDiv("0");
            TestDBUtility.insertWithDel(l_eleDeliveryManagementParams);
            
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_MainAccountParams);
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            
            WEB3AccInfoEleDeliveryInfo l_eleDeliveryInfo = l_impl.createEleDeliveryInfo(l_mainAccount);
            assertEquals("0", l_eleDeliveryInfo.tradingReportRegDiv);
            assertEquals("0", l_eleDeliveryInfo.positionReportDiv);
            assertEquals("0", l_eleDeliveryInfo.positionReportRegDiv);
            assertEquals("0", l_eleDeliveryInfo.opeReportRegDiv);
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateEleDeliveryInfoCase3()
    {
        final String STR_METHOD_NAME = "testCreateEleDeliveryInfoCase3()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_MainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_MainAccountParams);

            
            TestDBUtility.deleteAll(EleDeliveryManagementParams.TYPE);
            EleDeliveryManagementParams l_eleDeliveryManagementParams = TestDBUtility.getEleDeliveryManagementRow();
            l_eleDeliveryManagementParams.setTradingReportRegDiv("9");
            l_eleDeliveryManagementParams.setPositionReportDiv("1");
            l_eleDeliveryManagementParams.setPositionReportRegDiv("9");
            l_eleDeliveryManagementParams.setOpeReportRegDiv("9");
            TestDBUtility.insertWithDel(l_eleDeliveryManagementParams);
            
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_MainAccountParams);
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            
            WEB3AccInfoEleDeliveryInfo l_eleDeliveryInfo = l_impl.createEleDeliveryInfo(l_mainAccount);
            assertEquals("0", l_eleDeliveryInfo.tradingReportRegDiv);
            assertEquals("0", l_eleDeliveryInfo.positionReportDiv);
            assertEquals("0", l_eleDeliveryInfo.positionReportRegDiv);
            assertEquals("0", l_eleDeliveryInfo.opeReportRegDiv);
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateEleDeliveryInfoCase4()
    {
        final String STR_METHOD_NAME = "testCreateEleDeliveryInfoCase4()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_MainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_MainAccountParams);

            
            TestDBUtility.deleteAll(EleDeliveryManagementParams.TYPE);
            EleDeliveryManagementParams l_eleDeliveryManagementParams = TestDBUtility.getEleDeliveryManagementRow();
            l_eleDeliveryManagementParams.setTradingReportRegDiv("1");
            l_eleDeliveryManagementParams.setPositionReportDiv("9");
            l_eleDeliveryManagementParams.setPositionReportRegDiv("1");
            l_eleDeliveryManagementParams.setOpeReportRegDiv("1");
            TestDBUtility.insertWithDel(l_eleDeliveryManagementParams);
            
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_MainAccountParams);
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            
            WEB3AccInfoEleDeliveryInfo l_eleDeliveryInfo = l_impl.createEleDeliveryInfo(l_mainAccount);
            assertEquals("1", l_eleDeliveryInfo.tradingReportRegDiv);
            assertEquals("1", l_eleDeliveryInfo.positionReportDiv);
            assertEquals("1", l_eleDeliveryInfo.positionReportRegDiv);
            assertEquals("1", l_eleDeliveryInfo.opeReportRegDiv);
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateEleDeliveryInfoCase5()
    {
        final String STR_METHOD_NAME = "testCreateEleDeliveryInfoCase5()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_MainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_MainAccountParams);

            
            TestDBUtility.deleteAll(EleDeliveryManagementParams.TYPE);
            EleDeliveryManagementParams l_eleDeliveryManagementParams = TestDBUtility.getEleDeliveryManagementRow();
            l_eleDeliveryManagementParams.setTradingReportRegDiv("2");
            l_eleDeliveryManagementParams.setPositionReportDiv("9");
            l_eleDeliveryManagementParams.setPositionReportRegDiv("2");
            l_eleDeliveryManagementParams.setOpeReportRegDiv("2");
            
            l_eleDeliveryManagementParams.setTradingReportDiv("1");
            l_eleDeliveryManagementParams.setTradingReportDivUpdDate(WEB3DateUtility.getDate("20101118", "yyyyMMdd"));
            l_eleDeliveryManagementParams.setPositionReportDivUpdDate(WEB3DateUtility.getDate("20101118", "yyyyMMdd"));
            l_eleDeliveryManagementParams.setOpeReportDiv("1");
            l_eleDeliveryManagementParams.setOpeReportDivUpdDate(WEB3DateUtility.getDate("20101119", "yyyyMMdd"));
            l_eleDeliveryManagementParams.setOrdRulReportDiv("0");
            l_eleDeliveryManagementParams.setOrdRulRepRegDiv("0");
            l_eleDeliveryManagementParams.setOrdRulReportDivUpdDate(WEB3DateUtility.getDate("20101119", "yyyyMMdd"));
            l_eleDeliveryManagementParams.setReportDiv1("1");
            l_eleDeliveryManagementParams.setReportRegDiv1("0");
            l_eleDeliveryManagementParams.setReportDivUpdDate1(WEB3DateUtility.getDate("20101120", "yyyyMMdd"));
            l_eleDeliveryManagementParams.setReportDiv2("1");
            l_eleDeliveryManagementParams.setReportRegDiv2("0");
            l_eleDeliveryManagementParams.setReportDivUpdDate2(WEB3DateUtility.getDate("20101121", "yyyyMMdd"));
            l_eleDeliveryManagementParams.setReportDiv3("1");
            l_eleDeliveryManagementParams.setReportRegDiv3("0");
            l_eleDeliveryManagementParams.setReportDivUpdDate3(WEB3DateUtility.getDate("20101122", "yyyyMMdd"));
            l_eleDeliveryManagementParams.setReportDiv4("1");
            l_eleDeliveryManagementParams.setReportRegDiv4("0");
            l_eleDeliveryManagementParams.setReportDivUpdDate4(WEB3DateUtility.getDate("20101123", "yyyyMMdd"));
            l_eleDeliveryManagementParams.setReportDiv5("1");
            l_eleDeliveryManagementParams.setReportRegDiv5("0");
            l_eleDeliveryManagementParams.setReportDivUpdDate5(WEB3DateUtility.getDate("20101124", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eleDeliveryManagementParams);
            
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_MainAccountParams);
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            
            WEB3AccInfoEleDeliveryInfo l_eleDeliveryInfo = l_impl.createEleDeliveryInfo(l_mainAccount);
            assertEquals("1", l_eleDeliveryInfo.tradingReportRegDiv);
            assertEquals("1", l_eleDeliveryInfo.positionReportDiv);
            assertEquals("1", l_eleDeliveryInfo.positionReportRegDiv);
            assertEquals("1", l_eleDeliveryInfo.opeReportRegDiv);
            
            assertEquals("1", l_eleDeliveryInfo.tradingReportDiv);
            assertEquals(WEB3DateUtility.getDate("20101118", "yyyyMMdd"), l_eleDeliveryInfo.tradingReportDivUpdateDate);
            assertEquals(WEB3DateUtility.getDate("20101118", "yyyyMMdd"), l_eleDeliveryInfo.positionReportDivUpdateDate);
            assertEquals("1", l_eleDeliveryInfo.opeReportDiv);
            assertEquals(WEB3DateUtility.getDate("20101119", "yyyyMMdd"), l_eleDeliveryInfo.opeReportDivUpdateDate);
            assertEquals("0", l_eleDeliveryInfo.ordRulReportDiv);
            assertEquals("0", l_eleDeliveryInfo.ordRulRepRegDiv);
            assertEquals(WEB3DateUtility.getDate("20101119", "yyyyMMdd"), l_eleDeliveryInfo.ordRulReportDivUpdateDate);
            assertEquals("1", l_eleDeliveryInfo.reportDiv1);
            assertEquals("0", l_eleDeliveryInfo.reportRegDiv1);
            assertEquals(WEB3DateUtility.getDate("20101120", "yyyyMMdd"), l_eleDeliveryInfo.reportDivUpdateDate1);
            assertEquals("1", l_eleDeliveryInfo.reportDiv2);
            assertEquals("0", l_eleDeliveryInfo.reportRegDiv2);
            assertEquals(WEB3DateUtility.getDate("20101121", "yyyyMMdd"), l_eleDeliveryInfo.reportDivUpdateDate2);
            assertEquals("1", l_eleDeliveryInfo.reportDiv3);
            assertEquals("0", l_eleDeliveryInfo.reportRegDiv3);
            assertEquals(WEB3DateUtility.getDate("20101122", "yyyyMMdd"), l_eleDeliveryInfo.reportDivUpdateDate3);
            assertEquals("1", l_eleDeliveryInfo.reportDiv4);
            assertEquals("0", l_eleDeliveryInfo.reportRegDiv4);
            assertEquals(WEB3DateUtility.getDate("20101123", "yyyyMMdd"), l_eleDeliveryInfo.reportDivUpdateDate4);
            assertEquals("1", l_eleDeliveryInfo.reportDiv5);
            assertEquals("0", l_eleDeliveryInfo.reportRegDiv5);
            assertEquals(WEB3DateUtility.getDate("20101124", "yyyyMMdd"), l_eleDeliveryInfo.reportDivUpdateDate5);
       
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.WEB3AccInfoElecDeliveryRegisterChangeServiceImpl.getEleDeliveryInfoList(WEB3AccInfoElecDeliveryApyReferenceRequest)'
     */
    public void testGetEleDeliveryInfoListCase1()
    {
        final String STR_METHOD_NAME = "testGetEleDeliveryInfoListCase1()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            WEB3AccInfoElecDeliveryApyReferenceRequest l_request = new WEB3AccInfoElecDeliveryApyReferenceRequest();
            l_impl.getEleDeliveryInfoList(l_request);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetEleDeliveryInfoListCase2()
    {
        final String STR_METHOD_NAME = "testGetEleDeliveryInfoListCase2()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_MainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_MainAccountParams);

            
            TestDBUtility.deleteAll(EleDeliveryManagementParams.TYPE);
            EleDeliveryManagementParams l_eleDeliveryManagementParams = TestDBUtility.getEleDeliveryManagementRow();
            l_eleDeliveryManagementParams.setOpeReportDiv("1");
            TestDBUtility.insertWithDel(l_eleDeliveryManagementParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            WEB3AccInfoElecDeliveryApyReferenceRequest l_request = new WEB3AccInfoElecDeliveryApyReferenceRequest();
            WEB3AccInfoElecDeliveryApyReferenceResponse l_response =
                l_impl.getEleDeliveryInfoList(l_request);
            assertEquals("1",l_response.eleDeliveryInfo.opeReportDiv);
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.accountinfo.service.delegate.stdimpls.WEB3AccInfoElecDeliveryRegisterChangeServiceImpl.getEleDeliveryManagement(WEB3GentradeMainAccount)'
     */
    public void testGetEleDeliveryManagementCase1()
    {
        final String STR_METHOD_NAME = "testGetEleDeliveryManagementCase1()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_MainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_MainAccountParams);

            
            TestDBUtility.deleteAll(EleDeliveryManagementParams.TYPE);
            EleDeliveryManagementParams l_eleDeliveryManagementParams = TestDBUtility.getEleDeliveryManagementRow();
            TestDBUtility.insertWithDel(l_eleDeliveryManagementParams);
            
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_MainAccountParams);
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            
            EleDeliveryManagementParams l_params = l_impl.getEleDeliveryManagement(l_mainAccount);
            
            assertEquals(0,l_params.getEleDelRegiFlag());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetEleDeliveryManagementCase2()
    {
        final String STR_METHOD_NAME = "testGetEleDeliveryManagementCase2()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_MainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_MainAccountParams);

            
            TestDBUtility.deleteAll(EleDeliveryManagementParams.TYPE);
            EleDeliveryManagementParams l_eleDeliveryManagementParams = TestDBUtility.getEleDeliveryManagementRow();
            l_eleDeliveryManagementParams.setAccountCode("1111111");
            TestDBUtility.insertWithDel(l_eleDeliveryManagementParams);
            
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_MainAccountParams);
            WEB3AccInfoElecDeliveryRegisterChangeServiceImpl l_impl =
                new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl();
            
            l_impl.getEleDeliveryManagement(l_mainAccount);
            
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

}
@
