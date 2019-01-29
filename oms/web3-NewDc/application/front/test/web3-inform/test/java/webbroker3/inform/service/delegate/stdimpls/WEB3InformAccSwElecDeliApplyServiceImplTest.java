head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.13.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3InformAccSwElecDeliApplyServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3InformAccSwElecDeliApplyServiceImplTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/30 金傑（中訊）新規作成
*/
package webbroker3.inform.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.PersonNameDetails;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TraderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;

import test.util.TestDBUtility;

import webbroker3.accountopen.data.HostConditionRegVoucherParams;
import webbroker3.accountopen.data.HostConditionRegVoucherRow;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.CalendarParams;
import webbroker3.gentrade.data.CalendarRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.inform.WEB3InformConditionRegVoucher;
import webbroker3.inform.data.VariousInformDao;
import webbroker3.inform.data.VariousInformParams;
import webbroker3.inform.data.VariousInformRow;
import webbroker3.inform.message.WEB3AdminInformAccSwitchElecDeliAppDtInfo;
import webbroker3.inform.message.WEB3AdminInformAccSwitchElecDeliApplyInfo;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherChgCmpRequest;
import webbroker3.inform.message.WEB3InformAccSwElecDeliApplyCmpRequest;
import webbroker3.inform.message.WEB3InformAccSwElecDeliApplyCmpResponse;
import webbroker3.inform.message.WEB3InformAccSwElecDeliApplyConfRequest;
import webbroker3.inform.message.WEB3InformAccSwElecDeliApplyConfResponse;
import webbroker3.inform.message.WEB3InformAccSwElecDeliApplyInpRequest;
import webbroker3.inform.message.WEB3InformAccSwElecDeliApplyInpResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3InformAccSwElecDeliApplyServiceImplTest extends TestBaseForMock
{
    private WEB3InformAccSwElecDeliApplyServiceImpl l_serviceImpl = null;
    
    private boolean l_blnIsSetTrader = false;
    
    private List l_lisSearchResultForMainAccount = null;
    
    private List l_lisSearchResultForVariousInform = null;
    
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
                WEB3InformAccSwElecDeliApplyServiceImplTest.class);

    public WEB3InformAccSwElecDeliApplyServiceImplTest(String l_strName)
    {
        super(l_strName);
    }
    
    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        this.l_serviceImpl = new WEB3InformAccSwElecDeliApplyServiceImpl();
        TestDBUtility.deleteAll(VariousInformRow.TYPE);
    }

    protected void tearDown() throws Exception
    {
//        super.checkMethodValue();
        this.l_serviceImpl = null;
        this.l_blnIsSetTrader = false;
        this.l_lisSearchResultForMainAccount = null;
        this.l_lisSearchResultForVariousInform = null;
        TestDBUtility.deleteAll(MainAccountRow.TYPE);
        super.tearDown();
    }
    
    /**
     * 抛出異常信息：SYSTEM_ERROR_80017
     *
     */
    public void testExecute_C0001()
    {
        String STR_METHOD_NAME = "testExecute_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            l_serviceImpl.execute(null);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3SystemLayerException.class,l_ex.getClass());
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017,l_ex.getErrorInfo());
            log.error(""+l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            fail();
            log.error(""+l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 抛出異常信息：SYSTEM_ERROR_80018
     *
     */
    public void testExecute_C0002()
    {
        String STR_METHOD_NAME = "testExecute_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            l_serviceImpl.execute(new WEB3AdminInformProfDistVoucherChgCmpRequest());
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3SystemLayerException.class,l_ex.getClass());
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018,l_ex.getErrorInfo());
            log.error(""+l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            fail();
            log.error(""+l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 抛出異常信息：BUSINESS_ERROR_01035:該当する顧客が存在しません。
     *
     */
    public void testExecuteForGetInputScreen_C0001()
    {
        String STR_METHOD_NAME = "testExecuteForGetInputScreen_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(123456L));
                    
            WEB3InformAccSwElecDeliApplyInpRequest l_request = new WEB3InformAccSwElecDeliApplyInpRequest();
            l_request.informType = "1";
            
            WEB3InformAccSwElecDeliApplyInpResponse l_response =
                (WEB3InformAccSwElecDeliApplyInpResponse)l_serviceImpl.execute(l_request);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3BusinessLayerException.class,l_ex.getClass());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01035,l_ex.getErrorInfo());
            log.error(""+l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            fail();
            log.error(""+l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 正常返回
     *
     */
    public void testExecuteForGetInputScreen_C0002()
    {
        String STR_METHOD_NAME = "testExecuteForGetInputScreen_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.initData();
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));
            
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(333812512246L);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "getMainAccount",
                    new Class[] {long.class},
                    l_mainAccount);
                    
            WEB3InformAccSwElecDeliApplyInpRequest l_request = new WEB3InformAccSwElecDeliApplyInpRequest();
            l_request.informType = "1";
            
            WEB3InformAccSwElecDeliApplyInpResponse l_response =
                (WEB3InformAccSwElecDeliApplyInpResponse)l_serviceImpl.execute(l_request);
            
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager", 
                    "getMainAccount", 
                    new Class[] {long.class});
            assertEquals(333812512246L,((Long)l_paramsValue.getFirstCalled()[0]).longValue());
            
            assertEquals("381",l_response.branchCode);
            assertEquals("7654321",l_response.accountCode);
            assertEquals("内藤　@四郎",l_response.accountName);
            assertEquals(WEB3AdminInformAccSwitchElecDeliApplyInfo.class,(l_response.beforeInfo).getClass());
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(""+l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error(""+l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * get各種連絡一覧の戻り値() != null の場合、以下の処理を行う。
     *
     */
    public void testExecuteForGetInputScreen_C0003()
    {
        String STR_METHOD_NAME = "testExecuteForGetInputScreen_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.initData();

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));

            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(333812512246L);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "getMainAccount",
                    new Class[] {long.class},
                    l_mainAccount);
                    
            WEB3InformAccSwElecDeliApplyInpRequest l_request = new WEB3InformAccSwElecDeliApplyInpRequest();
            l_request.informType = "1";
            
            WEB3InformAccSwElecDeliApplyInpResponse l_response =
                (WEB3InformAccSwElecDeliApplyInpResponse)l_serviceImpl.execute(l_request);
            
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager", 
                    "getMainAccount", 
                    new Class[] {long.class});
            assertEquals(333812512246L,((Long)l_paramsValue.getFirstCalled()[0]).longValue());
            
            assertEquals("381",l_response.branchCode);
            assertEquals("7654321",l_response.accountCode);
            assertEquals("内藤　@四郎",l_response.accountName);
            assertEquals(WEB3AdminInformAccSwitchElecDeliApplyInfo.class,(l_response.beforeInfo).getClass());
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(""+l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error(""+l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 抛出異常信息：BUSINESS_ERROR_01035:該当する顧客が存在しません。
     *
     */
    public void testExecuteForValidateApply_C0001()
    {
        String STR_METHOD_NAME = "testExecuteForValidateApply_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(123456L));
                    
            WEB3InformAccSwElecDeliApplyConfRequest l_request = new WEB3InformAccSwElecDeliApplyConfRequestForTest();
            WEB3InformAccSwElecDeliApplyConfResponse l_response =
                (WEB3InformAccSwElecDeliApplyConfResponse)l_serviceImpl.execute(l_request);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3BusinessLayerException.class,l_ex.getClass());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01035,l_ex.getErrorInfo());
            log.error(""+l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            fail();
            log.error(""+l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
//    /**
//     * 抛出異常信息：BUSINESS_ERROR_02680
//     *
//     */
//    public void testExecuteForValidateApply_C0002()
//    {
//        String STR_METHOD_NAME = "testExecuteForValidateApply_C0002()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            this.initData();
//            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//                    "getAccountId",
//                    new Class[] {},
//                    new Long(333812512246L));
//            
//            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(333812512246L);
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.gentrade.WEB3GentradeAccountManager",
//                    "getMainAccount",
//                    new Class[] {long.class},
//                    l_mainAccount);
//                    
//            WEB3InformAccSwElecDeliApplyConfRequest l_request = new WEB3InformAccSwElecDeliApplyConfRequestForTest();
//            l_request.changedInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
//            l_request.changedInfo.mobileAccoutDiv = "0";
//            
//            WEB3InformAccSwElecDeliApplyConfResponse l_response =
//                (WEB3InformAccSwElecDeliApplyConfResponse)l_serviceImpl.execute(l_request);
//            
//            fail();
//        }
//        catch(WEB3BaseException l_ex)
//        {
//            assertEquals(WEB3BusinessLayerException.class,l_ex.getClass());
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02680,l_ex.getErrorInfo());
//            
//            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
//                    "webbroker3.gentrade.WEB3GentradeAccountManager", 
//                    "getMainAccount", 
//                    new Class[] {long.class});
//            assertEquals(333812512246L,((Long)l_paramsValue.getFirstCalled()[0]).longValue());
//            
//            log.error(""+l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//        }
//        catch(Exception l_ex)
//        {
//            fail();
//            log.error(""+l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }

    /**
     * 正常返回
     *
     */
    public void testExecuteForValidateApply_C0003()
    {
        String STR_METHOD_NAME = "testExecuteForValidateApply_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.initData();
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));

            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(333812512246L);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "getMainAccount",
                    new Class[] {long.class},
                    l_mainAccount);

            WEB3InformAccSwElecDeliApplyConfRequest l_request = new WEB3InformAccSwElecDeliApplyConfRequestForTest();
            l_request.changedInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
            l_request.changedInfo.mobileAccoutDiv = "1";
            l_request.informType = "1";

            WEB3InformAccSwElecDeliApplyConfResponse l_response =
                (WEB3InformAccSwElecDeliApplyConfResponse)l_serviceImpl.execute(l_request);

            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "getMainAccount",
                    new Class[] {long.class});
            assertEquals(333812512246L,((Long)l_paramsValue.getFirstCalled()[0]).longValue());

            assertTrue(true);
            assertEquals(WEB3InformAccSwElecDeliApplyConfResponse.class,l_response.getClass());
        }
        catch(WEB3BaseException l_ex)
        {           
            log.error(""+l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error(""+l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     *  抛出異常信息：BUSINESS_ERROR_01035:該当する顧客が存在しません。
     *
     */
    public void testExecuteForSubmitApply_C0001()
    {
        String STR_METHOD_NAME = "testExecuteForSubmitApply_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(123456L));

                    
            WEB3InformAccSwElecDeliApplyCmpRequest l_request = new WEB3InformAccSwElecDeliApplyCmpRequestForTest();
            l_request.changedInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
            l_request.changedInfo.mobileAccoutDiv = "1";
            
            WEB3InformAccSwElecDeliApplyCmpResponse l_response =
                (WEB3InformAccSwElecDeliApplyCmpResponse)l_serviceImpl.execute(l_request);
            
            fail();
        }
        catch(WEB3BaseException l_ex)
        {           
            assertEquals(WEB3BusinessLayerException.class,l_ex.getClass());
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01035,l_ex.getErrorInfo());
            log.error(""+l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(""+l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     *  validate取引パスワード = false的場合
     *
     */
    public void testExecuteForSubmitApply_C0002()
    {
        String STR_METHOD_NAME = "testExecuteForSubmitApply_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512249L);
            l_mainAccountParams.setAccountCode("7654321");
            l_mainAccountParams.setOnlyMobileOpenDiv("0");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));

                    
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(333812512249L);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "getMainAccount",
                    new Class[] {long.class},
                    l_mainAccount);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(1234));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getSessionProperty",
                    new Class[] {String.class},
                    "0");
            
            WEB3InformAccSwElecDeliApplyCmpRequest l_request = new WEB3InformAccSwElecDeliApplyCmpRequestForTest();
            l_request.changedInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
            l_request.changedInfo.mobileAccoutDiv = "0";
            
            this.l_serviceImpl = new WEB3InformAccSwElecDeliApplyServiceImplForTest();
            
            WEB3InformAccSwElecDeliApplyCmpResponse l_response =
                (WEB3InformAccSwElecDeliApplyCmpResponse)l_serviceImpl.execute(l_request);
            
            fail();
        }
        catch(WEB3BaseException l_ex)
        {           

            assertEquals(WEB3BaseException.class,l_ex.getClass());
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80003,l_ex.getErrorInfo());
            
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager", 
                    "getMainAccount", 
                    new Class[] {long.class});
            assertEquals(333812512246L,((Long)l_paramsValue1.getFirstCalled()[0]).longValue());
            
            WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getSessionProperty", 
                    new Class[] {String.class});
            assertEquals("orderRootDiv",(String)l_paramsValue2.getFirstCalled()[0]);
            
            log.error(""+l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(""+l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
//    /**
//     *  抛出異常信息：BUSINESS_ERROR_02680
//     *
//     */
//    public void testExecuteForSubmitApply_C0003()
//    {
//        String STR_METHOD_NAME = "testExecuteForSubmitApply_C0003()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            this.initData();
//            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//                    "getAccountId",
//                    new Class[] {},
//                    new Long(333812512246L));
//
//                    
//            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(333812512246L);
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.gentrade.WEB3GentradeAccountManager",
//                    "getMainAccount",
//                    new Class[] {long.class},
//                    l_mainAccount);
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//                    "getLoginId",
//                    new Class[] {},
//                    new Long(1234));
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
//                    "getSessionProperty",
//                    new Class[] {String.class},
//                    "0");
//            
//            WEB3InformAccSwElecDeliApplyCmpRequest l_request = new WEB3InformAccSwElecDeliApplyCmpRequestForTest();
//            l_request.changedInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
//            l_request.changedInfo.mobileAccoutDiv = "0";
//            
//            this.l_serviceImpl = new WEB3InformAccSwElecDeliApplyServiceImplForTest();
//            this.l_blnIsSetTrader = true;
//            
//            WEB3InformAccSwElecDeliApplyCmpResponse l_response =
//                (WEB3InformAccSwElecDeliApplyCmpResponse)l_serviceImpl.execute(l_request);
//            
//            fail();
//        }
//        catch(WEB3BaseException l_ex)
//        {           
//
//            assertEquals(WEB3BusinessLayerException.class,l_ex.getClass());
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02680,l_ex.getErrorInfo());
//            
//            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
//                    "webbroker3.gentrade.WEB3GentradeAccountManager", 
//                    "getMainAccount", 
//                    new Class[] {long.class});
//            assertEquals(333812512246L,((Long)l_paramsValue1.getFirstCalled()[0]).longValue());
//            
//            WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
//                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
//                    "getSessionProperty", 
//                    new Class[] {String.class});
//            assertEquals("orderRootDiv",(String)l_paramsValue2.getFirstCalled()[0]);
//            
//            log.error(""+l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//        }
//        catch(Exception l_ex)
//        {
//            log.error(""+l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }

    
    
//    /**
//     *  モバイル専用口座開設区分に変更がある場合
//     *  更新「顧客マスタテーブル」
//     *
//     */
//    public void testExecuteForSubmitApply_C0004()
//    {
//        String STR_METHOD_NAME = "testExecuteForSubmitApply_C0004()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            this.initData();
//            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//                    "getAccountId",
//                    new Class[] {},
//                    new Long(333812512246L));
//
//                    
//            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(333812512246L);
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.gentrade.WEB3GentradeAccountManager",
//                    "getMainAccount",
//                    new Class[] {long.class},
//                    l_mainAccount);
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//                    "getLoginId",
//                    new Class[] {},
//                    new Long(1234));
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
//                    "getSessionProperty",
//                    new Class[] {String.class},
//                    "0");
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.inform.service.delegate.stdimpls.WEB3InformHostReqOrderNumberManageServiceImpl",
//                    "getNewOrderRequestCode", 
//                    new Class[]{ String.class, String.class },
//                    "888888");
//            
//            WEB3InformAccSwElecDeliApplyCmpRequest l_request = new WEB3InformAccSwElecDeliApplyCmpRequestForTest();
//            l_request.changedInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
//            l_request.changedInfo.mobileAccoutDiv = "1";
//            l_request.informType = "1";
//            
//            this.l_serviceImpl = new WEB3InformAccSwElecDeliApplyServiceImplForTest();
//            this.l_blnIsSetTrader = true;
//            
//            WEB3InformAccSwElecDeliApplyCmpResponse l_response =
//                (WEB3InformAccSwElecDeliApplyCmpResponse)l_serviceImpl.execute(l_request);
//            
//            assertEquals(WEB3InformAccSwElecDeliApplyCmpResponse.class,l_response.getClass());
//            assertNotNull(l_response);
//            assertEquals(WEB3AdminInformAccSwitchElecDeliAppDtInfo.class,l_response.dateInfo.getClass());
//            
//            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
//                    "webbroker3.gentrade.WEB3GentradeAccountManager", 
//                    "getMainAccount", 
//                    new Class[] {long.class});
//            assertEquals(333812512246L,((Long)l_paramsValue1.getFirstCalled()[0]).longValue());
//            
//            WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
//                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
//                    "getSessionProperty", 
//                    new Class[] {String.class});
//            assertEquals("orderRootDiv",(String)l_paramsValue2.getFirstCalled()[0]);
//            
//            WEB3MockObjectParamsValue l_paramsValue3 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
//                    "webbroker3.inform.service.delegate.stdimpls.WEB3InformHostReqOrderNumberManageServiceImpl", 
//                    "getNewOrderRequestCode", 
//                    new Class[] { String.class, String.class });
//            assertEquals("0D",(String)l_paramsValue3.getFirstCalled()[0]);
//            assertEquals("1",(String)l_paramsValue3.getFirstCalled()[1]);
//
//            this.getSearchResult();
//            assertNotNull(this.l_lisSearchResultForMainAccount);
//            assertEquals(1,this.l_lisSearchResultForMainAccount.size());
//
//            // 更新「顧客マスタテーブル」
//            assertEquals("1",((MainAccountRow)this.l_lisSearchResultForMainAccount.get(0)).getOnlyMobileOpenDiv());
//            assertEquals("7654321",((MainAccountRow)this.l_lisSearchResultForMainAccount.get(0)).getAccountCode());
//            
//            Timestamp l_tp = ((MainAccountRow)this.l_lisSearchResultForMainAccount.get(0)).getOnlyMblOpnDivTimestamp();
//            assertEquals(0,WEB3DateUtility.compareToDay(new Date(),l_tp));
//            
//            assertNotNull(this.l_lisSearchResultForVariousInform);
//            assertEquals(1,this.l_lisSearchResultForVariousInform.size());
//            
//            // 受付完了
//            assertEquals("3",((VariousInformRow)this.l_lisSearchResultForVariousInform.get(0)).getStatus());
//            // 更新者コード
//            assertEquals("123456",((VariousInformRow)this.l_lisSearchResultForVariousInform.get(0)).getLastUpdater());
//            // 識別コード
//            assertEquals("888888",((VariousInformRow)this.l_lisSearchResultForVariousInform.get(0)).getRequestNumber());
//        }
//        catch(WEB3BaseException l_ex)
//        {                       
//            log.error(""+l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        catch(Exception l_ex)
//        {
//            log.error(""+l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//
//    /**
//     *  不是「モバイル専用口座開設区分に変更がある場合」
//     *  不更新「顧客マスタテーブル」
//     *
//     */
//    public void testExecuteForSubmitApply_C0005()
//    {
//        String STR_METHOD_NAME = "testExecuteForSubmitApply_C0005()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            this.initData();
//            
//            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//                    "getAccountId",
//                    new Class[] {},
//                    new Long(333812512246L));
//
//                    
//            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(333812512246L);
//
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.gentrade.WEB3GentradeAccountManager",
//                    "getMainAccount",
//                    new Class[] {long.class},
//                    l_mainAccount);
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//                    "getLoginId",
//                    new Class[] {},
//                    new Long(1234));
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
//                    "getSessionProperty",
//                    new Class[] {String.class},
//                    "0");
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.inform.service.delegate.stdimpls.WEB3InformHostReqOrderNumberManageServiceImpl",
//                    "getNewOrderRequestCode", 
//                    new Class[]{ String.class, String.class },
//                    "12345");
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
//                    "getLoginInfo",
//                    new Class[] {},
//                    new LoginInfoImplForMock());
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
//                    "getLoginTypeId",
//                    new Class[] {},
//                    new Long(12345));
//            
//            Map l_map = new HashMap();
//            l_map.put("TRADING_PWD_ENV","0");
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
//                    "getLoginTypeAttributes",
//                    new Class[] {long.class},
//                    l_map);
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
//                    "checkPassword",
//                    new Class[] {String.class},
//                    new Boolean(true));
//            
//            WEB3InformAccSwElecDeliApplyCmpRequest l_request = new WEB3InformAccSwElecDeliApplyCmpRequestForTest();
//            l_request.changedInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
//            l_request.changedInfo.mobileAccoutDiv = "0";
//            l_request.changedInfo.tradingReportDiv = "0";
//            l_request.changedInfo.tradingReportDiv = null;
//            l_request.changedInfo.positionReportDiv = null;
//            l_request.changedInfo.positionReportCycleDiv = null;
//            l_request.changedInfo.certificateDepositDiv = null;
//            l_request.changedInfo.accountStatementDiv = null;
//            l_request.informType = "0";
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
//                "validateAccSwitchElecDeliApplyInfo",
//                new Class[]{ WEB3AdminInformAccSwitchElecDeliApplyInfo.class, WEB3AdminInformAccSwitchElecDeliApplyInfo.class },
//                null);
//
//            this.l_serviceImpl = new WEB3InformAccSwElecDeliApplyServiceImplForTest();
//            this.l_blnIsSetTrader = false;
//            
//            WEB3InformAccSwElecDeliApplyCmpResponse l_response =
//                (WEB3InformAccSwElecDeliApplyCmpResponse)l_serviceImpl.execute(l_request);
//            
//            assertEquals(WEB3InformAccSwElecDeliApplyCmpResponse.class,l_response.getClass());
//            assertNotNull(l_response);
//            assertEquals(WEB3AdminInformAccSwitchElecDeliAppDtInfo.class,l_response.dateInfo.getClass());
//            
//            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
//                    "webbroker3.gentrade.WEB3GentradeAccountManager", 
//                    "getMainAccount", 
//                    new Class[] {long.class});
//            assertEquals(333812512246L,((Long)l_paramsValue1.getFirstCalled()[0]).longValue());
//            
//            WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
//                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
//                    "getSessionProperty", 
//                    new Class[] {String.class});
//            assertEquals("orderRootDiv",(String)l_paramsValue2.getFirstCalled()[0]);
//            
//            WEB3MockObjectParamsValue l_paramsValue3 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
//                    "webbroker3.inform.service.delegate.stdimpls.WEB3InformHostReqOrderNumberManageServiceImpl", 
//                    "getNewOrderRequestCode", 
//                    new Class[] { String.class, String.class });
//            assertEquals("0D",(String)l_paramsValue3.getFirstCalled()[0]);
//            assertEquals("0",(String)l_paramsValue3.getFirstCalled()[1]);
//            
//            WEB3MockObjectParamsValue l_paramsValue4 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
//                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl", 
//                    "getLoginTypeAttributes", 
//                    new Class[] {long.class});
//            assertEquals(12345,((Long)l_paramsValue4.getFirstCalled()[0]).longValue());
//            
//            WEB3MockObjectParamsValue l_paramsValue5 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
//                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
//                    "checkPassword", 
//                    new Class[] {String.class});
//            assertNull((String)l_paramsValue5.getFirstCalled()[0]);
//            
//            WEB3MockObjectParamsValue l_paramsValue6 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
//                    "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
//                    "validateAccSwitchElecDeliApplyInfo",
//                    new Class[]
//                    { WEB3AdminInformAccSwitchElecDeliApplyInfo.class,
//                            WEB3AdminInformAccSwitchElecDeliApplyInfo.class });
//            
//            assertEquals(WEB3AdminInformAccSwitchElecDeliApplyInfo.class,
//                    ((WEB3AdminInformAccSwitchElecDeliApplyInfo)l_paramsValue6.getFirstCalled()[0]).getClass());
//            assertEquals(WEB3AdminInformAccSwitchElecDeliApplyInfo.class,
//                    ((WEB3AdminInformAccSwitchElecDeliApplyInfo)l_paramsValue6.getFirstCalled()[1]).getClass());
//            
//            this.getSearchResult();
//            
//            assertNotNull(this.l_lisSearchResultForVariousInform);
//            assertEquals(1,this.l_lisSearchResultForVariousInform.size());
//            
//            // 受付完了
//            assertEquals("3",((VariousInformRow)this.l_lisSearchResultForVariousInform.get(0)).getStatus());
//            // 更新者コード
//            //assertNull(((VariousInformRow)this.l_lisSearchResultForVariousInform.get(0)).getLastUpdater());
//            assertEquals("123456",((VariousInformRow)this.l_lisSearchResultForVariousInform.get(0)).getLastUpdater());
//            // 識別コード
//            assertEquals("888888",((VariousInformRow)this.l_lisSearchResultForVariousInform.get(0)).getRequestNumber());
//        }
//        catch(WEB3BaseException l_ex)
//        {                       
//            log.error(""+l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        catch(Exception l_ex)
//        {
//            log.error(""+l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    /**
//     *  isトリガ発行 == true の場合、以下の処理を実行
//     *  実行 "トリガ発行(String, String)" 方法@
//     */
//    public void testExecuteForSubmitApply_C0006()
//    {
//        String STR_METHOD_NAME = "testExecuteForSubmitApply_C0006()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            this.initData();
//            
//            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
//
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//                    "getAccountId",
//                    new Class[] {},
//                    new Long(333812512246L));
//            
//            //TradingTimeRow
//            TradingTimeParams l_tradingTimeParams = new TradingTimeParams();
//            l_tradingTimeParams.setInstitutionCode("0D");
//            l_tradingTimeParams.setBranchCode("381");
//            l_tradingTimeParams.setMarketCode("SP");
//            l_tradingTimeParams.setTradingTimeType("26");
//            l_tradingTimeParams.setProductCode("0");
//            l_tradingTimeParams.setBizDateType("0");
//            l_tradingTimeParams.setStartTime("000000");
//            l_tradingTimeParams.setEndTime("235959");
//            l_tradingTimeParams.setSubmitMarketTrigger("1");
//            l_tradingTimeParams.setEnableOrder("0");
//            l_tradingTimeParams.setBizdateCalcParameter("1");
//            l_tradingTimeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
//            l_tradingTimeParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//            TestDBUtility.insertWithDel(l_tradingTimeParams); 
//            //TradingTimeRow
//            TradingTimeParams l_tradingTimeParams1 = new TradingTimeParams();
//            l_tradingTimeParams1.setInstitutionCode("0D");
//            l_tradingTimeParams1.setBranchCode("123");
//            l_tradingTimeParams1.setMarketCode("N1");
//            l_tradingTimeParams1.setTradingTimeType("01");
//            l_tradingTimeParams1.setProductCode("0");
//            l_tradingTimeParams1.setBizDateType("1");
//            l_tradingTimeParams1.setStartTime("000000");
//            l_tradingTimeParams1.setEndTime("235959");
//            l_tradingTimeParams1.setSubmitMarketTrigger("1");//WEB3SubmitMarketTriggerDef.TRIGGER
//            l_tradingTimeParams1.setEnableOrder("0");
//            l_tradingTimeParams1.setBizdateCalcParameter("1");
//            l_tradingTimeParams1.setCreatedTimestamp(Calendar.getInstance().getTime());
//            l_tradingTimeParams1.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
//            TestDBUtility.insertWithDel(l_tradingTimeParams1);
//                    
//            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(333812512246L);
//
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.gentrade.WEB3GentradeAccountManager",
//                    "getMainAccount",
//                    new Class[] {long.class},
//                    l_mainAccount);
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//                    "getLoginId",
//                    new Class[] {},
//                    new Long(1234));
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
//                    "getSessionProperty",
//                    new Class[] {String.class},
//                    "0");
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.inform.service.delegate.stdimpls.WEB3InformHostReqOrderNumberManageServiceImpl",
//                    "getNewOrderRequestCode", 
//                    new Class[]{ String.class, String.class },
//                    "12345");
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
//                    "getLoginInfo",
//                    new Class[] {},
//                    new LoginInfoImplForMock());
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
//                    "getLoginTypeId",
//                    new Class[] {},
//                    new Long(12345));
//            
//            Map l_map = new HashMap();
//            l_map.put("TRADING_PWD_ENV","0");
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
//                    "getLoginTypeAttributes",
//                    new Class[] {long.class},
//                    l_map);
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
//                    "checkPassword",
//                    new Class[] {String.class},
//                    new Boolean(true));
//            
//            WEB3InformAccSwElecDeliApplyCmpRequest l_request = new WEB3InformAccSwElecDeliApplyCmpRequestForTest();
//            l_request.changedInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
//            l_request.changedInfo.mobileAccoutDiv = "0";
//            l_request.changedInfo.tradingReportDiv = "0";
//            l_request.changedInfo.tradingReportDiv = null;
//            l_request.changedInfo.positionReportDiv = null;
//            l_request.changedInfo.positionReportCycleDiv = null;
//            l_request.changedInfo.certificateDepositDiv = null;
//            l_request.changedInfo.accountStatementDiv = null;
//            l_request.informType = "0";
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
//                "validateAccSwitchElecDeliApplyInfo",
//                new Class[]{ WEB3AdminInformAccSwitchElecDeliApplyInfo.class, WEB3AdminInformAccSwitchElecDeliApplyInfo.class },
//                null);
//
//            this.l_serviceImpl = new WEB3InformAccSwElecDeliApplyServiceImplForTest();
//            this.l_blnIsSetTrader = false;
//    
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
//                    "submitMarketTrigger", new Class[]
//                    { String.class, String.class },
//                    null);
//            
//            WEB3InformAccSwElecDeliApplyCmpResponse l_response =
//                (WEB3InformAccSwElecDeliApplyCmpResponse)l_serviceImpl.execute(l_request);
//            
//            assertEquals(WEB3InformAccSwElecDeliApplyCmpResponse.class,l_response.getClass());
//            assertNotNull(l_response);
//            assertEquals(WEB3AdminInformAccSwitchElecDeliAppDtInfo.class,l_response.dateInfo.getClass());
//            
//            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
//                    "webbroker3.gentrade.WEB3GentradeAccountManager", 
//                    "getMainAccount", 
//                    new Class[] {long.class});
//            assertEquals(333812512246L,((Long)l_paramsValue1.getFirstCalled()[0]).longValue());
//            
//            WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
//                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
//                    "getSessionProperty", 
//                    new Class[] {String.class});
//            assertEquals("orderRootDiv",(String)l_paramsValue2.getFirstCalled()[0]);
//            
//            WEB3MockObjectParamsValue l_paramsValue3 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
//                    "webbroker3.inform.service.delegate.stdimpls.WEB3InformHostReqOrderNumberManageServiceImpl", 
//                    "getNewOrderRequestCode", 
//                    new Class[] { String.class, String.class });
//            assertEquals("0D",(String)l_paramsValue3.getFirstCalled()[0]);
//            assertEquals("0",(String)l_paramsValue3.getFirstCalled()[1]);
//            
//            WEB3MockObjectParamsValue l_paramsValue4 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
//                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl", 
//                    "getLoginTypeAttributes", 
//                    new Class[] {long.class});
//            assertEquals(12345,((Long)l_paramsValue4.getFirstCalled()[0]).longValue());
//            
//            WEB3MockObjectParamsValue l_paramsValue5 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
//                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
//                    "checkPassword", 
//                    new Class[] {String.class});
//            assertNull((String)l_paramsValue5.getFirstCalled()[0]);
//            
//            WEB3MockObjectParamsValue l_paramsValue6 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
//                    "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
//                    "validateAccSwitchElecDeliApplyInfo",
//                    new Class[]
//                    { WEB3AdminInformAccSwitchElecDeliApplyInfo.class,
//                            WEB3AdminInformAccSwitchElecDeliApplyInfo.class });
//            
//            assertEquals(WEB3AdminInformAccSwitchElecDeliApplyInfo.class,
//                    ((WEB3AdminInformAccSwitchElecDeliApplyInfo)l_paramsValue6.getFirstCalled()[0]).getClass());
//            assertEquals(WEB3AdminInformAccSwitchElecDeliApplyInfo.class,
//                    ((WEB3AdminInformAccSwitchElecDeliApplyInfo)l_paramsValue6.getFirstCalled()[1]).getClass());
//            
//            this.getSearchResult();
//            
//            assertNotNull(this.l_lisSearchResultForVariousInform);
//            assertEquals(1,this.l_lisSearchResultForVariousInform.size());
//            
//            // 受付完了
//            assertEquals("3",((VariousInformRow)this.l_lisSearchResultForVariousInform.get(0)).getStatus());
//            // 更新者コード
//            //assertNull(((VariousInformRow)this.l_lisSearchResultForVariousInform.get(0)).getLastUpdater());
//            assertEquals("123456",((VariousInformRow)this.l_lisSearchResultForVariousInform.get(0)).getLastUpdater());
//            // 識別コード
//            assertEquals("888888",((VariousInformRow)this.l_lisSearchResultForVariousInform.get(0)).getRequestNumber());
//        
//            WEB3MockObjectParamsValue l_paramsValue7 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
//                    "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
//                    "submitMarketTrigger", new Class[]
//                    { String.class, String.class });
//            assertEquals("0D",(String)l_paramsValue7.getFirstCalled()[0]);
//            assertEquals("GI843",(String)l_paramsValue7.getFirstCalled()[1]);
//        
//        }
//        catch(WEB3BaseException l_ex)
//        {                       
//            log.error(""+l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        catch(Exception l_ex)
//        {
//            log.error(""+l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
    
    
//    //submit申込
//    //is伝票作成()の戻り値がtrueの場合
//    //各種連絡行 更新後的直 データコード： “GI843”
//    //           伝票作成状況： 1：作成済
//    public void testSubmitApplyCase0001()
//    {
//        final String STR_METHOD_NAME = "testSubmitApplyCase0001()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        try
//        {
//            this.initData();
//            
//            TestDBUtility.deleteAll(HostConditionRegVoucherRow.TYPE);
//            
//            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//                    "getAccountId",
//                    new Class[] {},
//                    new Long(333812512246L));
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
//                "validateAccSwitchElecDeliApplyInfo",
//                new Class[]{ WEB3AdminInformAccSwitchElecDeliApplyInfo.class, WEB3AdminInformAccSwitchElecDeliApplyInfo.class },
//                null);
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "webbroker3.inform.service.delegate.stdimpls.WEB3InformHostReqOrderNumberManageServiceImpl",
//                "getNewOrderRequestCode", 
//                new Class[]{ String.class, String.class },
//                "888888");
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl",
//                    "getNewNumber", 
//                    new Class[]{ String.class, String.class, ProductTypeEnum.class },
//                    "8");
//            
//            WEB3InformAccSwElecDeliApplyServiceImplForTest l_impl = new WEB3InformAccSwElecDeliApplyServiceImplForTest();
//            WEB3InformAccSwElecDeliApplyCmpRequest l_request = new WEB3InformAccSwElecDeliApplyCmpRequest();
//            WEB3AdminInformAccSwitchElecDeliApplyInfo changedInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
//            changedInfo.taxType = "0";
//            changedInfo.marginTaxType = "1";
//            l_request.informType = "1";
//            l_request.changedInfo = changedInfo;
//            l_impl.submitApply(l_request);
//            
//            VariousInformRow l_row = VariousInformDao.findRowByPk("0D","1","888888","381");
//            assertEquals(l_row.getRequestCode(), "GI843");
//            assertEquals(l_row.getStatus(), "1");
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    //submit申込
//    //is伝票作成()の戻り値がfalseの場合
//    //各種連絡行 更新後的直 データコード： null
//    //           伝票作成状況：  3：受付完了
//    public void testSubmitApplyCase0002()
//    {
//        final String STR_METHOD_NAME = "testSubmitApplyCase0002()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        try
//        {
//            this.initData();
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            l_mainAccountParams.setOnlyMobileOpenDiv("0");
//            l_mainAccountParams.setAccountId(333812512246L);
//            l_mainAccountParams.setInstitutionCode("0D");
//            l_mainAccountParams.setInstitutionId(33L);
//            l_mainAccountParams.setAccountCode("7654321");
//            l_mainAccountParams.setBranchId(33381L);
//            l_mainAccountParams.setBranchCode("381");
//            l_mainAccountParams.setSonarTraderCode("11124");
//            l_mainAccountParams.setAccountType(MainAccountTypeEnum.INDIVIDUAL_ACCOUNT);
//            l_mainAccountParams.setFamilyName("内藤　@四郎");
//            l_mainAccountParams.setFamilyNameAlt1("ﾅｲﾄｳ ｼﾛｳ");
//            l_mainAccountParams.setGivenNameAlt1("Siro");
//            l_mainAccountParams.setZipCode("1001238");
//            l_mainAccountParams.setSubZipCode("1001238");
//            l_mainAccountParams.setAddressLine1("東京都");
//            l_mainAccountParams.setAddressLine2("江東区");
//            l_mainAccountParams.setAddressLine3("深川５");
//            l_mainAccountParams.setTelephone("38201115");
//            l_mainAccountParams.setMobile("901115");
//            l_mainAccountParams.setFax("38202226");
//            l_mainAccountParams.setEquityOrderExeMailFlag(BooleanEnum.TRUE);
//            l_mainAccountParams.setEquityOrderUnexecMailFlag(BooleanEnum.TRUE);
//            l_mainAccountParams.setIfoOrderExecMailFlag(BooleanEnum.TRUE);
//            l_mainAccountParams.setIfoOrderUnexecMailFlag(BooleanEnum.TRUE);
//            l_mainAccountParams.setInformationMailFlag(BooleanEnum.TRUE);
//            l_mainAccountParams.setResident("0");
//            l_mainAccountParams.setNewAccountDiv("0");
//            l_mainAccountParams.setViaTrustBankDiv("0");
//            l_mainAccountParams.setEmailAddress("t4@@dir.co.jp");
//            l_mainAccountParams.setTradingPassword("&:,<#!+=!.,#:##&");
//            l_mainAccountParams.setAccountOpenDate(WEB3DateUtility.getDate("20040202","yyyyMMdd"));
//            l_mainAccountParams.setAccountCloseDate(WEB3DateUtility.getDate("99991231","yyyyMMdd"));
//            l_mainAccountParams.setPersonIdentify("1");
//            l_mainAccountParams.setEraBorn("3");
//            l_mainAccountParams.setBornDate("101013");
//            l_mainAccountParams.setSex("1");
//            l_mainAccountParams.setYellowCustomer("0");
//            l_mainAccountParams.setHtSettlementWay("0");
//            l_mainAccountParams.setBankAccountRegi("0");
//            l_mainAccountParams.setAccountStatus(MainAccountStatusEnum.ACTIVE);
//            l_mainAccountParams.setExaminLockFlag("0");
//            l_mainAccountParams.setMngLockFlag("0");
//            l_mainAccountParams.setMngLockFlagAdvance(BooleanEnum.FALSE);
//            l_mainAccountParams.setMngLockFlagUnpayMargin(BooleanEnum.FALSE);
//            l_mainAccountParams.setMngLockFlagShortSecurity(BooleanEnum.FALSE);
//            l_mainAccountParams.setMngLockFlagUnsubstitDepo(BooleanEnum.FALSE);
//            l_mainAccountParams.setBranchLock("0");
//            l_mainAccountParams.setOrderPermission("0");
//            l_mainAccountParams.setTaxType(TaxTypeEnum.SPECIAL);
//            l_mainAccountParams.setTaxTypeNext(TaxTypeEnum.SPECIAL_WITHHOLD);
//            l_mainAccountParams.setMarginTaxType(TaxTypeEnum.SPECIAL);
//            l_mainAccountParams.setMarginTaxTypeNext(TaxTypeEnum.SPECIAL_WITHHOLD);
//            l_mainAccountParams.setQualifiedInstInvestorDiv("0");
//            l_mainAccountParams.setMarginSysAccOpenDiv("0");
//            l_mainAccountParams.setMarginGenAccOpenDiv("0");
//            l_mainAccountParams.setEquitySpAccOpenDate(WEB3DateUtility.getDate("20040202","yyyyMMdd"));
//            l_mainAccountParams.setForeignSecAccOpenDiv("1");
//            l_mainAccountParams.setIfoAccOpenDivTokyo("0");
//            l_mainAccountParams.setIfoAccOpenDivOsaka("0");
//            l_mainAccountParams.setIfoAccOpenDivNagoya("0");
//            l_mainAccountParams.setRuitoAccOpenDiv("0");
//            l_mainAccountParams.setMrfAccOpenDiv("0");
//            l_mainAccountParams.setFxAccOpenDiv("0");
//            l_mainAccountParams.setFeqConAccOpenDiv("0");
//            l_mainAccountParams.setTopPageId("0");
//            l_mainAccountParams.setQuotoType("0");
//            l_mainAccountParams.setTradingReportDiv("1");
//            l_mainAccountParams.setPositionReportDiv("1");
//            l_mainAccountParams.setPositionReportCycleDiv("1");//取引残高報告書作成周期区分 == (引数)各種連絡行.区分４
//            l_mainAccountParams.setCertificateDepositFlag(BooleanEnum.TRUE);
//            l_mainAccountParams.setAccountStatementFlag(BooleanEnum.TRUE);
//            l_mainAccountParams.setEmailLastUpdater("2512246");
//            l_mainAccountParams.setEmailLastUpdatedTimestamp(Calendar.getInstance().getTime());
//            l_mainAccountParams.setTradingPasswordUpdater("2512246");
//            l_mainAccountParams.setTrPwdLastUpdateTimestamp(Calendar.getInstance().getTime());
//            l_mainAccountParams.setMbOffLastUpdater("2512246");
//            l_mainAccountParams.setMbOffLastUpdatedTimestamp(Calendar.getInstance().getTime());
//            l_mainAccountParams.setEnableOrderLastUpdater("2512246");
//            l_mainAccountParams.setEnableOrderUpdatedTimestamp(Calendar.getInstance().getTime());
//            l_mainAccountParams.setFxAccOpenDivLastUpdater("2512246");
//            l_mainAccountParams.setFxAccOpenUpdatedTimestamp(Calendar.getInstance().getTime());
//            l_mainAccountParams.setFeqConAccOpenDivUpdater("2512246");
//            l_mainAccountParams.setFeqConAccOpenTimestamp(Calendar.getInstance().getTime());
//            l_mainAccountParams.setMrfFundCode("1");
//            l_mainAccountParams.setCreatedTimestamp(Calendar.getInstance().getTime());
//            l_mainAccountParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
//            l_mainAccountParams.setSpMngAccOpenDiv(null);
//            l_mainAccountParams.setOnlyMblOpnDivTimestamp(Calendar.getInstance().getTime());
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            
//            TestDBUtility.deleteAll(HostConditionRegVoucherRow.TYPE);
//            
//            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//                    "getAccountId",
//                    new Class[] {},
//                    new Long(333812512246L));
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
//                "validateAccSwitchElecDeliApplyInfo",
//                new Class[]{ WEB3AdminInformAccSwitchElecDeliApplyInfo.class, WEB3AdminInformAccSwitchElecDeliApplyInfo.class },
//                null);
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "webbroker3.inform.service.delegate.stdimpls.WEB3InformHostReqOrderNumberManageServiceImpl",
//                "getNewOrderRequestCode", 
//                new Class[]{ String.class, String.class },
//                "888888");
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl",
//                    "getNewNumber", 
//                    new Class[]{ String.class, String.class, ProductTypeEnum.class },
//                    "8");
//            
//            WEB3InformAccSwElecDeliApplyServiceImplForTest l_impl = new WEB3InformAccSwElecDeliApplyServiceImplForTest();
//            WEB3InformAccSwElecDeliApplyCmpRequest l_request = new WEB3InformAccSwElecDeliApplyCmpRequest();
//            WEB3AdminInformAccSwitchElecDeliApplyInfo changedInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
//            changedInfo.taxType = "0";
//            changedInfo.marginTaxType = "1";
//            changedInfo.positionReportCycleDiv = "1";//取引残高報告書作成周期区分 == (引数)各種連絡行.区分４
//            changedInfo.positionReportDiv = "1";
//            changedInfo.certificateDepositDiv = "1";
//            changedInfo.accountStatementDiv = "1";
//            changedInfo.tradingReportDiv = "1";
//            changedInfo.taxType = "2";
//            changedInfo.taxTypeNext = "3";
//            changedInfo.marginTaxType = "2";
//            changedInfo.marginTaxTypeNext = "3";
//            
//            
//            l_request.informType = "1";
//            l_request.changedInfo = changedInfo;
//            l_impl.submitApply(l_request);
//            
//            VariousInformRow l_row = VariousInformDao.findRowByPk("0D","1","888888","381");
//            assertNull(l_row.getRequestCode());
//            assertEquals(l_row.getStatus(), "3");
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.exiting(STR_METHOD_NAME);
//    }
//
//    private List getSearchResult()
//    {
//        final String STR_METHOD_NAME = "getSearchResult()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        List lisSearchResult = null;
//        try
//        {
//
//            StringBuffer l_sbWhereForMainAccount = new StringBuffer();
//            l_sbWhereForMainAccount.append(" account_id = ? ");
//
//            Object[] l_objWheresForMainAccount = {new Long(333812512246L)};
//
//            QueryProcessor l_queryProcesser = Processors.getDefaultProcessor();
//            this.l_lisSearchResultForMainAccount = l_queryProcesser.doFindAllQuery(
//                MainAccountRow.TYPE,
//                l_sbWhereForMainAccount.toString(),
//                l_objWheresForMainAccount);
//
//            StringBuffer l_sbWhereForVariousInformParams = new StringBuffer();
//            l_sbWhereForVariousInformParams.append("institution_code = ? and ");
//            l_sbWhereForVariousInformParams.append("inform_div = ? and ");
//            l_sbWhereForVariousInformParams.append("request_number = ? and ");
//            l_sbWhereForVariousInformParams.append("branch_code = ? ");
//
//            Object[] l_objWheresForVariousInformParams = {"0D", "0", "888888", "381"};
//
//            l_queryProcesser = Processors.getDefaultProcessor();
//            this.l_lisSearchResultForVariousInform = l_queryProcesser.doFindAllQuery(
//                VariousInformRow.TYPE,
//                l_sbWhereForVariousInformParams.toString(),
//                l_objWheresForVariousInformParams);
//
//        }
//        catch(Exception l_ex)
//        {
//            log.error("", l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//        return lisSearchResult;
//    }

    private void initData()
    {
        final String STR_METHOD_NAME = "initData()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setOnlyMobileOpenDiv("0");
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setInstitutionId(33L);
            l_mainAccountParams.setAccountCode("7654321");
            l_mainAccountParams.setBranchId(33381L);
            l_mainAccountParams.setBranchCode("381");
            l_mainAccountParams.setSonarTraderCode("11124");
            l_mainAccountParams.setAccountType(MainAccountTypeEnum.INDIVIDUAL_ACCOUNT);
            l_mainAccountParams.setFamilyName("内藤　@四郎");
            l_mainAccountParams.setFamilyNameAlt1("ﾅｲﾄｳ ｼﾛｳ");
            l_mainAccountParams.setGivenNameAlt1("Siro");
            l_mainAccountParams.setZipCode("1001238");
            l_mainAccountParams.setSubZipCode("1001238");
            l_mainAccountParams.setAddressLine1("東京都");
            l_mainAccountParams.setAddressLine2("江東区");
            l_mainAccountParams.setAddressLine3("深川５");
            l_mainAccountParams.setTelephone("38201115");
            l_mainAccountParams.setMobile("901115");
            l_mainAccountParams.setFax("38202226");
            l_mainAccountParams.setEquityOrderExeMailFlag(BooleanEnum.TRUE);
            l_mainAccountParams.setEquityOrderUnexecMailFlag(BooleanEnum.TRUE);
            l_mainAccountParams.setIfoOrderExecMailFlag(BooleanEnum.TRUE);
            l_mainAccountParams.setIfoOrderUnexecMailFlag(BooleanEnum.TRUE);
            l_mainAccountParams.setInformationMailFlag(BooleanEnum.TRUE);
            l_mainAccountParams.setResident("0");
            l_mainAccountParams.setNewAccountDiv("0");
            l_mainAccountParams.setViaTrustBankDiv("0");
            l_mainAccountParams.setEmailAddress("t4@@dir.co.jp");
            l_mainAccountParams.setTradingPassword("&:,<#!+=!.,#:##&");
            l_mainAccountParams.setAccountOpenDate(WEB3DateUtility.getDate("20040202","yyyyMMdd"));
            l_mainAccountParams.setAccountCloseDate(WEB3DateUtility.getDate("99991231","yyyyMMdd"));
            l_mainAccountParams.setPersonIdentify("1");
            l_mainAccountParams.setEraBorn("3");
            l_mainAccountParams.setBornDate("101013");
            l_mainAccountParams.setSex("1");
            l_mainAccountParams.setYellowCustomer("0");
            l_mainAccountParams.setHtSettlementWay("0");
            l_mainAccountParams.setBankAccountRegi("0");
            l_mainAccountParams.setAccountStatus(MainAccountStatusEnum.ACTIVE);
            l_mainAccountParams.setExaminLockFlag("0");
            l_mainAccountParams.setMngLockFlag("0");
            l_mainAccountParams.setMngLockFlagAdvance(BooleanEnum.FALSE);
            l_mainAccountParams.setMngLockFlagUnpayMargin(BooleanEnum.FALSE);
            l_mainAccountParams.setMngLockFlagShortSecurity(BooleanEnum.FALSE);
            l_mainAccountParams.setMngLockFlagUnsubstitDepo(BooleanEnum.FALSE);
            l_mainAccountParams.setBranchLock("0");
            l_mainAccountParams.setOrderPermission("0");
            l_mainAccountParams.setTaxType(TaxTypeEnum.SPECIAL);
            l_mainAccountParams.setTaxTypeNext(TaxTypeEnum.SPECIAL_WITHHOLD);
            l_mainAccountParams.setMarginTaxType(TaxTypeEnum.UNDEFINED);
            l_mainAccountParams.setMarginTaxTypeNext(TaxTypeEnum.UNDEFINED);
            l_mainAccountParams.setQualifiedInstInvestorDiv("0");
            l_mainAccountParams.setMarginSysAccOpenDiv("0");
            l_mainAccountParams.setMarginGenAccOpenDiv("0");
            l_mainAccountParams.setEquitySpAccOpenDate(WEB3DateUtility.getDate("20040202","yyyyMMdd"));
            l_mainAccountParams.setForeignSecAccOpenDiv("1");
            l_mainAccountParams.setIfoAccOpenDivTokyo("0");
            l_mainAccountParams.setIfoAccOpenDivOsaka("0");
            l_mainAccountParams.setIfoAccOpenDivNagoya("0");
            l_mainAccountParams.setRuitoAccOpenDiv("0");
            l_mainAccountParams.setMrfAccOpenDiv("0");
            l_mainAccountParams.setFxAccOpenDiv("0");
            l_mainAccountParams.setFeqConAccOpenDiv("0");
            l_mainAccountParams.setTopPageId("0");
            l_mainAccountParams.setQuotoType("0");
            l_mainAccountParams.setTradingReportDiv("1");
            l_mainAccountParams.setPositionReportDiv("9");
            l_mainAccountParams.setPositionReportCycleDiv("1");
            l_mainAccountParams.setCertificateDepositFlag(BooleanEnum.TRUE);
            l_mainAccountParams.setAccountStatementFlag(BooleanEnum.TRUE);
            l_mainAccountParams.setEmailLastUpdater("2512246");
            l_mainAccountParams.setEmailLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams.setTradingPasswordUpdater("2512246");
            l_mainAccountParams.setTrPwdLastUpdateTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams.setMbOffLastUpdater("2512246");
            l_mainAccountParams.setMbOffLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams.setEnableOrderLastUpdater("2512246");
            l_mainAccountParams.setEnableOrderUpdatedTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams.setFxAccOpenDivLastUpdater("2512246");
            l_mainAccountParams.setFxAccOpenUpdatedTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams.setFeqConAccOpenDivUpdater("2512246");
            l_mainAccountParams.setFeqConAccOpenTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams.setMrfFundCode("1");
            l_mainAccountParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_mainAccountParams.setSpMngAccOpenDiv("0");
            l_mainAccountParams.setOnlyMblOpnDivTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_mainAccountParams);

            //VariousInformRow
            TestDBUtility.deleteAll(VariousInformRow.TYPE);
            VariousInformParams variousInformParams1 = new VariousInformParams();
            variousInformParams1.setBranchCode("381");
            variousInformParams1.setInstitutionCode("0D");
            variousInformParams1.setInformDiv("1");
            variousInformParams1.setRequestNumber("123");
            variousInformParams1.setAccountCode("7654321");
            variousInformParams1.setStatus("0");
            variousInformParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070907","yyyyMMdd"));
            variousInformParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070907","yyyyMMdd"));
            TestDBUtility.insertWithDel(variousInformParams1);
            VariousInformParams variousInformParams2 = new VariousInformParams();
            variousInformParams2.setBranchCode("381");
            variousInformParams2.setInstitutionCode("0D");
            variousInformParams2.setInformDiv("0");
            variousInformParams2.setRequestNumber("888888");
            variousInformParams2.setAccountCode("7654321");
            variousInformParams2.setStatus("3");
            variousInformParams2.setLastUpdater("123456");
            variousInformParams2.setCreatedTimestamp(WEB3DateUtility.getDate("20070907","yyyyMMdd"));
            variousInformParams2.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070907","yyyyMMdd"));
            TestDBUtility.insertWithDel(variousInformParams2);    
            
            //TradingTimeRow
            TradingTimeParams l_tradingTimeParams = new TradingTimeParams();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode("SP");
            l_tradingTimeParams.setTradingTimeType("26");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("0");
            l_tradingTimeParams.setStartTime("000000");
            l_tradingTimeParams.setEndTime("235959");
            l_tradingTimeParams.setSubmitMarketTrigger("1");
            l_tradingTimeParams.setEnableOrder("0");
            l_tradingTimeParams.setBizdateCalcParameter("1");
            l_tradingTimeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_tradingTimeParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.insertWithDel(l_tradingTimeParams); 
            
            //CalendarRow
            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(Calendar.getInstance().getTime());
            l_calendarParams.setBizDateType("1");
            l_calendarParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_calendarParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.deleteAll(CalendarRow.TYPE);
            TestDBUtility.insertWithDel(l_calendarParams);

        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    private class WEB3InformAccSwElecDeliApplyConfRequestForTest extends WEB3InformAccSwElecDeliApplyConfRequest
    {
        public void validate() throws WEB3BaseException
        {

        }
    }

    private class WEB3InformAccSwElecDeliApplyCmpRequestForTest extends WEB3InformAccSwElecDeliApplyCmpRequest
    {
        public void validate() throws WEB3BaseException
        {

        }
    }

    private class MyTradeImplForTest implements Trader
    {

        public long getTraderId()
        {
            return 0;
        }

        public String getTraderCode()
        {
            return "123456";
        }

        public long getLoginId()
        {
            return 0;
        }

        public TraderTypeEnum getTraderType()
        {
            return null;
        }

        public PersonNameDetails getNameDetails()
        {
            return null;
        }

        public PersonNameDetails getAlt1NameDetails()
        {
            return null;
        }

        public PersonNameDetails getAlt2NameDetails()
        {
            return null;
        }

        public Branch getBranch()
        {
            return null;
        }

        public Institution getInstitution()
        {
            return null;
        }

        public Object getDataSourceObject()
        {
            return null;
        }

    }

    private class WEB3InformAccSwElecDeliApplyServiceImplForTest extends WEB3InformAccSwElecDeliApplyServiceImpl
    {

        public WEB3GentradeSubAccount getSubAccount() throws WEB3BaseException
        {
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            return new WEB3GentradeSubAccount(l_subAccountParams);
        }

        public Trader getTrader() throws WEB3SystemLayerException
        {
            if(l_blnIsSetTrader)
            {
                return new MyTradeImplForTest();
            }
            return null;
        }

    }
}
@
