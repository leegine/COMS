head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.13.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminInformSwElecDeliApplyServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者口座切替・電子交付申込サービス実装クラス(WEB3AdminInformSwElecDeliApplyServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/31 孫洪江（中訊）新規作成 モデルNo.099
*/

package webbroker3.inform.service.delegate.stdimpls;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import test.util.TestDBUtility;

import webbroker3.accountopen.data.HostConditionRegVoucherParams;
import webbroker3.accountopen.data.HostConditionRegVoucherRow;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdminPermissionRow;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.gentrade.data.AdministratorTypeParams;
import webbroker3.gentrade.data.AdministratorTypeRow;
import webbroker3.gentrade.data.CalendarParams;
import webbroker3.gentrade.data.CalendarRow;
import webbroker3.gentrade.data.FeqCalendarParams;
import webbroker3.gentrade.data.FeqCalendarRow;
import webbroker3.gentrade.data.OrderAcceptStatusParams;
import webbroker3.gentrade.data.OrderAcceptStatusRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.inform.data.VariousInformDao;
import webbroker3.inform.data.VariousInformParams;
import webbroker3.inform.data.VariousInformRow;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyCmpRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyCmpResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyConfRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyConfResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyInpRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyInpResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyRefRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyRefResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplySrcRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplySrcResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliChangeCmpRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliChangeCmpResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliChangeConfRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliChangeConfResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliDeleteCmpRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliDeleteCmpResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliDeleteConfRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliDeleteConfResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwitchElecDeliAppDtInfo;
import webbroker3.inform.message.WEB3AdminInformAccSwitchElecDeliApplyInfo;
import webbroker3.inform.message.WEB3InformAccSwElecDeliApplyCmpRequest;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminInformSwElecDeliApplyServiceImplTest extends TestBaseForMock
{
    private WEB3AdminInformSwElecDeliApplyServiceImpl l_service; 
    private WEB3GenResponse l_reponse;
    private WEB3GenRequest l_request;
    private WEB3AdminInformAccSwElecDeliApplyInpRequest l_inpRequest;
    private WEB3Administrator l_administrator;
    private AdministratorParams l_administratorParams;
    private WEB3AdminInformAccSwElecDeliApplyCmpRequest l_cmpRequest;
    private WEB3AdminInformAccSwElecDeliApplyConfRequest l_confRequest;
    
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminInformSwElecDeliApplyServiceImplTest.class);

    public WEB3AdminInformSwElecDeliApplyServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        this.l_service = new WEB3AdminInformSwElecDeliApplyServiceImplForTest();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testExcute_C0001()
    {
        final String STR_METHOD_NAME = "testExcute_C0001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminInformSwElecDeliApplyServiceImpl l_serviceImpl = new WEB3AdminInformSwElecDeliApplyServiceImplForExcute();
        try
        {
            l_serviceImpl.execute(null);
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExcute_C0002()
    {
        final String STR_METHOD_NAME = "testExcute_C0002()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminInformSwElecDeliApplyServiceImpl l_serviceImpl = new WEB3AdminInformSwElecDeliApplyServiceImplForExcute();
        try
        {
            WEB3AdminInformAccSwElecDeliApplySrcRequest l_request = new WEB3AdminInformAccSwElecDeliApplySrcRequest(); 
            WEB3GenResponse l_response = l_serviceImpl.execute(l_request);
            
            assertEquals(WEB3AdminInformAccSwElecDeliApplySrcResponse.class, l_response.getClass());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExcute_C0003()
    {
        final String STR_METHOD_NAME = "testExcute_C0003()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminInformSwElecDeliApplyServiceImpl l_serviceImpl = new WEB3AdminInformSwElecDeliApplyServiceImplForExcute();
        try
        {
            WEB3AdminInformAccSwElecDeliApplyInpRequest l_request = new WEB3AdminInformAccSwElecDeliApplyInpRequest(); 
            WEB3GenResponse l_response = l_serviceImpl.execute(l_request);
            
            assertEquals(WEB3AdminInformAccSwElecDeliApplyInpResponse.class, l_response.getClass());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExcute_C0004()
    {
        final String STR_METHOD_NAME = "testExcute_C0004()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminInformSwElecDeliApplyServiceImpl l_serviceImpl = new WEB3AdminInformSwElecDeliApplyServiceImplForExcute();
        try
        {
            WEB3AdminInformAccSwElecDeliApplyConfRequest l_request = new WEB3AdminInformAccSwElecDeliApplyConfRequest(); 
            WEB3GenResponse l_response = l_serviceImpl.execute(l_request);
            
            assertEquals(WEB3AdminInformAccSwElecDeliApplyConfResponse.class, l_response.getClass());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExcute_C0005()
    {
        final String STR_METHOD_NAME = "testExcute_C0005()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminInformSwElecDeliApplyServiceImpl l_serviceImpl = new WEB3AdminInformSwElecDeliApplyServiceImplForExcute();
        try
        {
            WEB3AdminInformAccSwElecDeliApplyCmpRequest l_request = new WEB3AdminInformAccSwElecDeliApplyCmpRequest(); 
            WEB3GenResponse l_response = l_serviceImpl.execute(l_request);
            
            assertEquals(WEB3AdminInformAccSwElecDeliApplyCmpResponse.class, l_response.getClass());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExcute_C0006()
    {
        final String STR_METHOD_NAME = "testExcute_C0006()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminInformSwElecDeliApplyServiceImpl l_serviceImpl = new WEB3AdminInformSwElecDeliApplyServiceImplForExcute();
        try
        {
            WEB3AdminInformAccSwElecDeliApplyRefRequest l_request = new WEB3AdminInformAccSwElecDeliApplyRefRequest(); 
            WEB3GenResponse l_response = l_serviceImpl.execute(l_request);
            
            assertEquals(WEB3AdminInformAccSwElecDeliApplyRefResponse.class, l_response.getClass());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExcute_C0007()
    {
        final String STR_METHOD_NAME = "testExcute_C0007()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminInformSwElecDeliApplyServiceImpl l_serviceImpl = new WEB3AdminInformSwElecDeliApplyServiceImplForExcute();
        try
        {
            WEB3AdminInformAccSwElecDeliChangeConfRequest l_request = new WEB3AdminInformAccSwElecDeliChangeConfRequest(); 
            WEB3GenResponse l_response = l_serviceImpl.execute(l_request);
            
            assertEquals(WEB3AdminInformAccSwElecDeliChangeConfResponse.class, l_response.getClass());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExcute_C0008()
    {
        final String STR_METHOD_NAME = "testExcute_C0008()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminInformSwElecDeliApplyServiceImpl l_serviceImpl = new WEB3AdminInformSwElecDeliApplyServiceImplForExcute();
        try
        {
            WEB3AdminInformAccSwElecDeliChangeCmpRequest l_request = new WEB3AdminInformAccSwElecDeliChangeCmpRequest(); 
            WEB3GenResponse l_response = l_serviceImpl.execute(l_request);
            
            assertEquals(WEB3AdminInformAccSwElecDeliChangeCmpResponse.class, l_response.getClass());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExcute_C0009()
    {
        final String STR_METHOD_NAME = "testExcute_C0009()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminInformSwElecDeliApplyServiceImpl l_serviceImpl = new WEB3AdminInformSwElecDeliApplyServiceImplForExcute();
        try
        {
            WEB3AdminInformAccSwElecDeliDeleteConfRequest l_request = new WEB3AdminInformAccSwElecDeliDeleteConfRequest(); 
            WEB3GenResponse l_response = l_serviceImpl.execute(l_request);
            
            assertEquals(WEB3AdminInformAccSwElecDeliDeleteConfResponse.class, l_response.getClass());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExcute_C00010()
    {
        final String STR_METHOD_NAME = "testExcute_C0010()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminInformSwElecDeliApplyServiceImpl l_serviceImpl = new WEB3AdminInformSwElecDeliApplyServiceImplForExcute();
        try
        {
            WEB3AdminInformAccSwElecDeliDeleteCmpRequest l_request = new WEB3AdminInformAccSwElecDeliDeleteCmpRequest(); 
            WEB3GenResponse l_response = l_serviceImpl.execute(l_request);
            
            assertEquals(WEB3AdminInformAccSwElecDeliDeleteCmpResponse.class, l_response.getClass());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExcute_C00011()
    {
        final String STR_METHOD_NAME = "testExcute_C0011()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminInformSwElecDeliApplyServiceImpl l_serviceImpl = new WEB3AdminInformSwElecDeliApplyServiceImplForExcute();
        try
        {
            WEB3InformAccSwElecDeliApplyCmpRequest l_request = new WEB3InformAccSwElecDeliApplyCmpRequest(); 
            l_serviceImpl.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * getInputScreen      WEB3ErrorCatalog.BUSINESS_ERROR_01035
     * 
     */
    public void testGetInputScreen_C0001()
    {
        String STR_METHOD_NAME = "testGetInputScreen_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.initData();
        this.l_inpRequest = new WEB3AdminInformAccSwElecDeliApplyInpRequestForTest();
        this.l_administrator = new WEB3AdministratorForTest(this.l_administratorParams);

        try
        {
            LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                new Class[] {},
                l_loginInfoImplForMock);

            long l_lngLoginId = 33381330003L;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(l_lngLoginId));

           this.l_inpRequest.accountCode = "123456";
           WEB3AdministratorForMock.mockValidateBranchPermission(
               this.l_administrator,
               "123",
               true
           );
           
            this.l_reponse = this.l_service.execute(this.l_inpRequest);

            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01035, l_ex.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();   
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * getInputScreen      WEB3ErrorCatalog.BUSINESS_ERROR_01037
     * 
     */
    public void testGetInputScreen_C0002()
    {
        String STR_METHOD_NAME = "testGetInputScreen_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.initData();
        this.l_inpRequest = new WEB3AdminInformAccSwElecDeliApplyInpRequestForTest();
        this.l_administrator = new WEB3AdministratorForTest(this.l_administratorParams);

        try
        {
            LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                new Class[] {},
                l_loginInfoImplForMock);

            long l_lngLoginId = 33381330003L;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(l_lngLoginId));

           this.l_inpRequest.branchCode = "000";
           this.l_inpRequest.requestNumber = "0";
           this.l_inpRequest.informType = "9";
           WEB3AdministratorForMock.mockValidateBranchPermission(
               this.l_administrator,
               "123",
               true
           );

           WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(333812512246L);
           TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
               "webbroker3.gentrade.WEB3GentradeAccountManager",
               "getMainAccount",
               new Class[] {String.class, String.class, String.class},
               l_mainAccount);
           
            this.l_reponse = this.l_service.execute(this.l_inpRequest);
            
            
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            //ParamsCheck
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "getMainAccount",
                new Class[] {String.class, String.class, String.class});
            assertEquals("0D", (String)l_paramsValue1.getFirstCalled()[0]);
            assertEquals("000",(String)l_paramsValue1.getFirstCalled()[1]);
            assertNull(l_paramsValue1.getFirstCalled()[2]);
            assertTrue(true);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01037, l_ex.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();   
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 
     * validateApply    WEB3ErrorCatalog.BUSINESS_ERROR_01035
     *
     */
    public void testValidateApply_0001()
    {
        String STR_METHOD_NAME = "testValidateApply_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.initData();
        this.l_confRequest = new WEB3AdminInformAccSwElecDeliApplyConfRequestForTest();
        this.l_administrator = new WEB3AdministratorForTest(this.l_administratorParams);
        
        try
        {
            this.l_confRequest.changedInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
            LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                new Class[] {},
                l_loginInfoImplForMock);
            
            long l_lngLoginId = 33381330003L;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(l_lngLoginId));
            
            WEB3AdministratorForMock.mockValidateBranchPermission(
                this.l_administrator,
                "123",
                true
            );
            this.l_confRequest.accountCode = "123456";
//            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(333812512246L);
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "webbroker3.gentrade.WEB3GentradeAccountManager",
//                "getMainAccount",
//                new Class[] {String.class, String.class, String.class},
//                l_mainAccount);
            
//            boolean l_blnIsChangedInfo = true;
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
//                    "isChangedInfo", 
//                    new Class[]{ WEB3AdminInformAccSwitchElecDeliApplyInfo.class, MainAccount.class },
//                    new Boolean(l_blnIsChangedInfo));
            
            this.l_reponse = this.l_service.execute(this.l_confRequest);
            
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            //ParamsCheck
//            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
//                "webbroker3.gentrade.WEB3GentradeAccountManager",
//                "getMainAccount",
//                new Class[] {String.class, String.class, String.class});
//            assertEquals("0D", (String)l_paramsValue1.getFirstCalled()[0]);
//            assertNull((String)l_paramsValue1.getFirstCalled()[1]);
//            assertNull(l_paramsValue1.getFirstCalled()[2]);
            
//            WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
//                    "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
//                    "isChangedInfo", 
//                    new Class[]{ WEB3AdminInformAccSwitchElecDeliApplyInfo.class, MainAccount.class });
//            assertEquals(WEB3AdminInformAccSwitchElecDeliApplyInfo.class, ((WEB3AdminInformAccSwitchElecDeliApplyInfo)l_paramsValue2.getFirstCalled()[0]).getClass());
//            assertEquals(WEB3GentradeMainAccountForMock.class, ((MainAccount)l_paramsValue2.getFirstCalled()[1]).getClass());
//
//            assertTrue(true);
            
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01035, l_ex.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();   
        }
        
        
        
    
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    
    public void testValidateApply_0002()
    {
        String STR_METHOD_NAME = "testValidateApply_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        this.initData();
        WEB3AdminInformAccSwElecDeliApplyConfRequestForTest l_request =
            new WEB3AdminInformAccSwElecDeliApplyConfRequestForTest();

        l_request.changedInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
        l_request.changedInfo.mobileAccoutDiv = "0";

        try
        {
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            WEB3AdministratorForMock.mockValidateBranchPermission(l_administrator, l_request.branchCode, true);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "getMainAccount",
                new Class[] {String.class, String.class, String.class},
                l_mainAccount);
           
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
                    "validateAccSwitchElecDeliApplyInfo", 
                    new Class[]{ WEB3AdminInformAccSwitchElecDeliApplyInfo.class, WEB3AdminInformAccSwitchElecDeliApplyInfo.class },
                    null);
            
            l_service.validateApply(l_request);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 
     * submitApply   WEB3ErrorCatalog.BUSINESS_ERROR_01035
     *
     */
    public void testSubmitApply_0001()
    {
        String STR_METHOD_NAME = "testSubmitApply_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        this.initData();

        this.l_cmpRequest = new WEB3AdminInformAccSwElecDeliApplyCmpRequestForTest();
        this.l_administrator = new WEB3AdministratorForTest(this.l_administratorParams);

        try
        {
            LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                new Class[] {},
                l_loginInfoImplForMock);
            
            long l_lngLoginId = 33381330003L;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(l_lngLoginId));
            
            WEB3AdministratorForMock.mockValidateBranchPermission(
                this.l_administrator,
                "123",
                true);
            
            WEB3AdministratorForMock.mockValidateTradingPassword(
                "123",
                true);
            
//            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(333812512246L);
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "webbroker3.gentrade.WEB3GentradeAccountManager",
//                "getMainAccount",
//                new Class[] {String.class, String.class, String.class},
//                l_mainAccount);

//            boolean l_blnIsChangedInfo = true;
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
//                    "isChangedInfo", 
//                    new Class[]{ WEB3AdminInformAccSwitchElecDeliApplyInfo.class, MainAccount.class },
//                    new Boolean(l_blnIsChangedInfo));
            
//            WEB3AdminInformAccSwitchElecDeliAppDtInfo l_elecDeliAppDtInfo = new WEB3AdminInformAccSwitchElecDeliAppDtInfo();
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
//                "createAccSwitchElecDeliAppDtInfo", 
//                new Class[]{ String.class, String.class },
//                l_elecDeliAppDtInfo);
            
            WEB3AdminInformAccSwitchElecDeliApplyInfo changedInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
            this.l_cmpRequest.changedInfo = changedInfo;
            this.l_cmpRequest.changedInfo.taxType = "1";
            this.l_cmpRequest.changedInfo.marginTaxType = "1";
            this.l_cmpRequest.informType = "1";
            this.l_cmpRequest.accountCode= "123456";
            
//            String l_strRequestNumber = "1";
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "webbroker3.inform.service.delegate.stdimpls.WEB3InformHostReqOrderNumberManageServiceImpl",
//                "getNewOrderRequestCode", 
//                new Class[]{ String.class, String.class },
//                l_strRequestNumber);

            //aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
            this.l_reponse = this.l_service.execute(this.l_cmpRequest);
           
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            //ParamsCheck
//            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
//                "webbroker3.gentrade.WEB3GentradeAccountManager",
//                "getMainAccount",
//                new Class[] {String.class, String.class, String.class});
//            assertEquals("0D", (String)l_paramsValue1.getFirstCalled()[0]);
//            assertNull((String)l_paramsValue1.getFirstCalled()[1]);
//            assertNull(l_paramsValue1.getFirstCalled()[2]);
//
//            WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
//                    "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
//                    "isChangedInfo", 
//                    new Class[]{ WEB3AdminInformAccSwitchElecDeliApplyInfo.class, MainAccount.class });
//            assertEquals(WEB3AdminInformAccSwitchElecDeliApplyInfo.class, ((WEB3AdminInformAccSwitchElecDeliApplyInfo)l_paramsValue2.getFirstCalled()[0]).getClass());
//            assertEquals(WEB3GentradeMainAccountForMock.class, ((MainAccount)l_paramsValue2.getFirstCalled()[1]).getClass());
//            
//            WEB3MockObjectParamsValue l_paramsValue3 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
//                    "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
//                    "createAccSwitchElecDeliAppDtInfo", 
//                    new Class[]{ String.class, String.class });
//            assertEquals("1", (String)l_paramsValue3.getFirstCalled()[0]);
//            assertEquals("1", (String)l_paramsValue3.getFirstCalled()[1]);
//
//            WEB3MockObjectParamsValue l_paramsValue4 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
//                "webbroker3.inform.service.delegate.stdimpls.WEB3InformHostReqOrderNumberManageServiceImpl",
//                "getNewOrderRequestCode", 
//                new Class[]{ String.class, String.class });
//            assertEquals("0D", (String)l_paramsValue4.getFirstCalled()[0]);
//            assertEquals("1", (String)l_paramsValue4.getFirstCalled()[1]);
//
//            assertTrue(true);
            
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01035, l_ex.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();   
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 
     * 正常通過
     *
     */
//    public void testSubmitApply_0002()
//    {
//        String STR_METHOD_NAME = "testSubmitApply_0002()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        this.initData();
//        this.l_cmpRequest = new WEB3AdminInformAccSwElecDeliApplyCmpRequestForTest();
//        this.l_administrator = new WEB3AdministratorForTest(this.l_administratorParams);
//
//        try
//        {
//            LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
//                new Class[] {},
//                l_loginInfoImplForMock);
//            
//            long l_lngLoginId = 33381330003L;
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
//                    "getLoginId",
//                    new Class[] {},
//                    new Long(l_lngLoginId));
//            
//            WEB3AdministratorForMock.mockValidateBranchPermission(
//                this.l_administrator,
//                "123",
//                true);
//            
//            WEB3AdministratorForMock.mockValidateTradingPassword(
//                "123",
//                true);
//            
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            
//            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "webbroker3.gentrade.WEB3GentradeAccountManager",
//                "getMainAccount",
//                new Class[] {String.class, String.class, String.class},
//                l_mainAccount);
//
//            boolean l_blnIsChangedInfo = false;
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
//                    "isChangedInfo", 
//                    new Class[]{ WEB3AdminInformAccSwitchElecDeliApplyInfo.class, MainAccount.class },
//                    new Boolean(l_blnIsChangedInfo));
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
//                    "validateAccSwitchElecDeliApplyInfo", new Class[]
//                    { WEB3AdminInformAccSwitchElecDeliApplyInfo.class, MainAccount.class },
//                    null);
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
//                    "validateAccSwitchElecDeliApplyInfo",
//                    new Class[]
//                    { WEB3AdminInformAccSwitchElecDeliApplyInfo.class,
//                            WEB3AdminInformAccSwitchElecDeliApplyInfo.class }, null);
//            
//            WEB3AdminInformAccSwitchElecDeliApplyInfo changedInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
//            this.l_cmpRequest.changedInfo = changedInfo;
//            this.l_cmpRequest.changedInfo.taxType = "1";
//            this.l_cmpRequest.changedInfo.marginTaxType = "1";
//            this.l_cmpRequest.informType = "1";
//
//            this.l_reponse = this.l_service.submitApply(this.l_cmpRequest);
//        }
//        catch(Exception l_ex)
//        {
//            log.error("", l_ex);
//            fail();   
//        }
//
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }

    /**
     * get各種連絡一覧の戻り値() != null の場合
     * throw WEB3ErrorCatalog.SYSTEM_ERROR_80017
     */
    public void testSubmitApply_0003()
    {
        final String STR_METHOD_NAME = "testSubmitApply_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        this.initData();
        this.l_cmpRequest = new WEB3AdminInformAccSwElecDeliApplyCmpRequestForTest();
        this.l_administrator = new WEB3AdministratorForTest(this.l_administratorParams);
        l_cmpRequest.branchCode = "123";
        l_cmpRequest.informType = "1";
        l_cmpRequest.accountCode = "1235643";
       
        try
        {
            LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                new Class[] {},
                l_loginInfoImplForMock);
            
            long l_lngLoginId = 33381330003L;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(l_lngLoginId));
            
            WEB3AdministratorForMock.mockValidateBranchPermission(
                this.l_administrator,
                "123",
                true);
            
            WEB3AdministratorForMock.mockValidateTradingPassword(
                "123",
                true);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(VariousInformRow.TYPE);
            VariousInformParams l_variousInformParams = TestDBUtility.getVariousInformRow();
            l_variousInformParams.setBranchCode("123");
            l_variousInformParams.setInstitutionCode("0D");
            l_variousInformParams.setInformDiv("1");
            l_variousInformParams.setAccountCode("1235643");
            TestDBUtility.insertWithDel(l_variousInformParams);
            
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "getMainAccount",
                new Class[] {String.class, String.class, String.class},
                l_mainAccount);

            boolean l_blnIsChangedInfo = false;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
                    "isChangedInfo", 
                    new Class[]{ WEB3AdminInformAccSwitchElecDeliApplyInfo.class, MainAccount.class },
                    new Boolean(l_blnIsChangedInfo));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
                    "validateAccSwitchElecDeliApplyInfo", new Class[]
                    { WEB3AdminInformAccSwitchElecDeliApplyInfo.class, MainAccount.class },
                    null);
            
            
            WEB3AdminInformAccSwitchElecDeliApplyInfo changedInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
            this.l_cmpRequest.changedInfo = changedInfo;
            this.l_cmpRequest.changedInfo.taxType = "1";
            this.l_cmpRequest.changedInfo.marginTaxType = "1";
            this.l_cmpRequest.informType = "1";

            this.l_reponse = this.l_service.submitApply(this.l_cmpRequest);
        }

        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();   
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
//    /**
//     * validate口座切替・電子交付申込情報(口座切替・電子交付申込情報, 
//     * 口座切替・電子交付申込情報)異常
//     * WEB3ErrorCatalog.BUSINESS_ERROR_02873
//     */
//    public void testSubmitApply_0004()
//    {
//        final String STR_METHOD_NAME = "testSubmitApply_0004()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        this.initData();
//        this.l_cmpRequest = new WEB3AdminInformAccSwElecDeliApplyCmpRequestForTest();
//        this.l_administrator = new WEB3AdministratorForTest(this.l_administratorParams);
//        l_cmpRequest.branchCode = "123";
//        l_cmpRequest.informType = "1";
//        l_cmpRequest.accountCode = "1235643";
//        try
//        {
//            LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
//                new Class[] {},
//                l_loginInfoImplForMock);
//            
//            long l_lngLoginId = 33381330003L;
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
//                    "getLoginId",
//                    new Class[] {},
//                    new Long(l_lngLoginId));
//            
//            WEB3AdministratorForMock.mockValidateBranchPermission(
//                this.l_administrator,
//                "123",
//                true);
//            
//            WEB3AdministratorForMock.mockValidateTradingPassword(
//                "123",
//                true);
//            
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            
//            TestDBUtility.deleteAll(VariousInformRow.TYPE);
//            VariousInformParams l_variousInformParams = TestDBUtility.getVariousInformRow();
//            l_variousInformParams.setBranchCode("123");
//            l_variousInformParams.setInstitutionCode("0D");
//            l_variousInformParams.setInformDiv("1");
//            l_variousInformParams.setAccountCode("1235643");
//            l_variousInformParams.setStatus("2");
//            l_variousInformParams.setExtText2("20070303");
//            TestDBUtility.insertWithDel(l_variousInformParams);
//            
//            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "webbroker3.gentrade.WEB3GentradeAccountManager",
//                "getMainAccount",
//                new Class[] {String.class, String.class, String.class},
//                l_mainAccount);
//
//            boolean l_blnIsChangedInfo = false;
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
//                    "isChangedInfo", 
//                    new Class[]{ WEB3AdminInformAccSwitchElecDeliApplyInfo.class, MainAccount.class },
//                    new Boolean(l_blnIsChangedInfo));
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
//                    "validateAccSwitchElecDeliApplyInfo", new Class[]
//                    { WEB3AdminInformAccSwitchElecDeliApplyInfo.class, MainAccount.class },
//                    null);
//            
//            WEB3AdminInformAccSwitchElecDeliApplyInfo changedInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
//            this.l_cmpRequest.changedInfo = changedInfo;
//            this.l_cmpRequest.changedInfo.taxType = "1";
//            this.l_cmpRequest.changedInfo.marginTaxType = "1";
//            this.l_cmpRequest.informType = "1";
//            this.l_reponse = this.l_service.submitApply(this.l_cmpRequest);
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02873, l_ex.getErrorInfo());
//        }
//        catch(Exception l_ex)
//        {
//            log.error("", l_ex);
//            fail();   
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    /**
//     * 
//     *
//     */
//    public void testSubmitApply_0005()
//    {
//        final String STR_METHOD_NAME = "testSubmitApply_0005()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        this.initData();
//        this.l_cmpRequest = new WEB3AdminInformAccSwElecDeliApplyCmpRequestForTest();
//        this.l_administrator = new WEB3AdministratorForTest(this.l_administratorParams);
//        l_cmpRequest.branchCode = "123";
//        l_cmpRequest.informType = "1";
//        l_cmpRequest.accountCode = "1235643";
//        try
//        {
//            LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
//                new Class[] {},
//                l_loginInfoImplForMock);
//            
//            long l_lngLoginId = 33381330003L;
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
//                    "getLoginId",
//                    new Class[] {},
//                    new Long(l_lngLoginId));
//            
//            WEB3AdministratorForMock.mockValidateBranchPermission(
//                this.l_administrator,
//                "123",
//                true);
//            
//            WEB3AdministratorForMock.mockValidateTradingPassword(
//                "123",
//                true);
//            
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            
//            TestDBUtility.deleteAll(VariousInformRow.TYPE);
//            
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
//            l_tradingTimeParams.setInstitutionCode("0D");
//            l_tradingTimeParams.setBranchCode("123");
//            l_tradingTimeParams.setMarketCode("N1");
//            l_tradingTimeParams.setTradingTimeType("01");
//            l_tradingTimeParams.setBizDateType("1");
//            l_tradingTimeParams.setProductCode("0");
//            l_tradingTimeParams.setSubmitMarketTrigger("1");
//            TestDBUtility.insertWithDel(l_tradingTimeParams);
//            
//            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "webbroker3.gentrade.WEB3GentradeAccountManager",
//                "getMainAccount",
//                new Class[] {String.class, String.class, String.class},
//                l_mainAccount);
//
//            boolean l_blnIsChangedInfo = false;
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
//                    "isChangedInfo", 
//                    new Class[]{ WEB3AdminInformAccSwitchElecDeliApplyInfo.class, MainAccount.class },
//                    new Boolean(l_blnIsChangedInfo));
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
//                    "validateAccSwitchElecDeliApplyInfo", new Class[]
//                    { WEB3AdminInformAccSwitchElecDeliApplyInfo.class, MainAccount.class },
//                    null);
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
//                    "validateAccSwitchElecDeliApplyInfo",
//                    new Class[]
//                    { WEB3AdminInformAccSwitchElecDeliApplyInfo.class,
//                            WEB3AdminInformAccSwitchElecDeliApplyInfo.class }, null);
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
//                    "submitMarketTrigger", new Class[]
//                    { String.class, String.class }, null);
//            
//            
//            WEB3AdminInformAccSwitchElecDeliApplyInfo changedInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
//            this.l_cmpRequest.changedInfo = changedInfo;
//            this.l_cmpRequest.changedInfo.taxType = "1";
//            this.l_cmpRequest.changedInfo.marginTaxType = "1";
//            this.l_cmpRequest.informType = "1";
//
//            this.l_reponse = this.l_service.submitApply(this.l_cmpRequest);
//            assertTrue(true);
//        }
//        catch(Exception l_ex)
//        {
//            log.error("", l_ex);
//            fail();   
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    public void testSubmitApply_0006()
//    {
//        final String STR_METHOD_NAME = "testSubmitApply_0006()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        this.initData();
//        this.l_cmpRequest = new WEB3AdminInformAccSwElecDeliApplyCmpRequestForTest();
//        this.l_administrator = new WEB3AdministratorForTest(this.l_administratorParams);
//        l_cmpRequest.branchCode = "123";
//        l_cmpRequest.informType = "1";
//        l_cmpRequest.accountCode = "1235643";
//
//        try
//        {
//            LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
//                new Class[] {},
//                l_loginInfoImplForMock);
//            
//            long l_lngLoginId = 33381330003L;
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
//                    "getLoginId",
//                    new Class[] {},
//                    new Long(l_lngLoginId));
//            
//            WEB3AdministratorForMock.mockValidateBranchPermission(
//                this.l_administrator,
//                "123",
//                true);
//            
//            WEB3AdministratorForMock.mockValidateTradingPassword(
//                "123",
//                true);
//            
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            
//            TestDBUtility.deleteAll(VariousInformRow.TYPE);
//            
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
//            l_tradingTimeParams.setInstitutionCode("0D");
//            l_tradingTimeParams.setBranchCode("123");
//            l_tradingTimeParams.setMarketCode("N1");
//            l_tradingTimeParams.setTradingTimeType("01");
//            l_tradingTimeParams.setBizDateType("1");
//            l_tradingTimeParams.setProductCode("0");
//            l_tradingTimeParams.setSubmitMarketTrigger("1");
//            TestDBUtility.insertWithDel(l_tradingTimeParams);
//            
//            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "webbroker3.gentrade.WEB3GentradeAccountManager",
//                "getMainAccount",
//                new Class[] {String.class, String.class, String.class},
//                l_mainAccount);
//
//            boolean l_blnIsChangedInfo = false;
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
//                    "isChangedInfo", 
//                    new Class[]{ WEB3AdminInformAccSwitchElecDeliApplyInfo.class, MainAccount.class },
//                    new Boolean(l_blnIsChangedInfo));
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
//                    "validateAccSwitchElecDeliApplyInfo", new Class[]
//                    { WEB3AdminInformAccSwitchElecDeliApplyInfo.class, MainAccount.class },
//                    null);
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
//                    "validateAccSwitchElecDeliApplyInfo",
//                    new Class[]
//                    { WEB3AdminInformAccSwitchElecDeliApplyInfo.class,
//                            WEB3AdminInformAccSwitchElecDeliApplyInfo.class }, null);
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
//                    "submitMarketTrigger", new Class[]
//                    { String.class, String.class }, null);
//            
//            
//            WEB3AdminInformAccSwitchElecDeliApplyInfo changedInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
//            this.l_cmpRequest.changedInfo = changedInfo;
//            this.l_cmpRequest.changedInfo.taxType = "1";
//            this.l_cmpRequest.changedInfo.mobileAccoutDiv = "0";
//            this.l_cmpRequest.changedInfo.marginTaxType = "1";
//            this.l_cmpRequest.informType = "1";
//
//            this.l_reponse = this.l_service.submitApply(this.l_cmpRequest);
//            
//            assertTrue(true);
//        }
//        catch(Exception l_ex)
//        {
//            log.error("", l_ex);
//            fail();   
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
   
    /**
     * validate注文受付可能( ) 異常
     * 抛出：WEB3ErrorCatalog.SYSTEM_ERROR_80006
     */
    public void testGetReferenceScreen_case0001()
    {
        final String STR_METHOD_NAME = "testGetReferenceScreen_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminInformSwElecDeliApplyServiceImpl l_serviceImpl = new WEB3AdminInformSwElecDeliApplyServiceImpl();

        WEB3AdminInformAccSwElecDeliApplyRefRequest l_request =
            new WEB3AdminInformAccSwElecDeliApplyRefRequest();
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(false);
            l_serviceImpl.getReferenceScreen(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 管理者・口座切替・電子交付申込参照リクエスト.validate( ) 異常
     * 抛出：WEB3ErrorCatalog.BUSINESS_ERROR_00833
     */
    public void testGetReferenceScreen_case0002()
    {
        final String STR_METHOD_NAME = "testGetReferenceScreen_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminInformSwElecDeliApplyServiceImpl l_serviceImpl = new WEB3AdminInformSwElecDeliApplyServiceImpl();

        WEB3AdminInformAccSwElecDeliApplyRefRequest l_request =
            new WEB3AdminInformAccSwElecDeliApplyRefRequest();
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            l_serviceImpl.getReferenceScreen(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00833, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * validate権限(機@能カテゴリコード : String, is更新 : boolean) 異常
     * 抛出：WEB3ErrorCatalog.BUSINESS_ERROR_01056
     */
    public void testGetReferenceScreen_case0003()
    {
        final String STR_METHOD_NAME = "testGetReferenceScreen_case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminInformSwElecDeliApplyServiceImpl l_serviceImpl = new WEB3AdminInformSwElecDeliApplyServiceImpl();

        WEB3AdminInformAccSwElecDeliApplyRefRequest l_request =
            new WEB3AdminInformAccSwElecDeliApplyRefRequestForTest();
        try
        {

            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                new Class[] {},
                l_loginInfoImplForMock);

            long l_lngLoginId = 33381330003L;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(l_lngLoginId));

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            WEB3AdministratorForMock.mockValidateAuthority(l_administrator, "A0106", true, false);
            l_serviceImpl.getReferenceScreen(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * validate部店権限(部店コード : String) 異常
     * 抛出：WEB3ErrorCatalog.SYSTEM_ERROR_80003
     */
    public void testGetReferenceScreen_case0004()
    {
        final String STR_METHOD_NAME = "testGetReferenceScreen_case0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminInformSwElecDeliApplyServiceImpl l_serviceImpl = new WEB3AdminInformSwElecDeliApplyServiceImpl();

        WEB3AdminInformAccSwElecDeliApplyRefRequest l_request =
            new WEB3AdminInformAccSwElecDeliApplyRefRequestForTest();
        try
        {
            TestDBUtility.deleteAll(AdministratorTypeRow.TYPE);

            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("012");
            TestDBUtility.insertWithDel(l_administratorParams);

            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);

            LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                new Class[] {},
                l_loginInfoImplForMock);

            long l_lngLoginId = 33381330003L;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(l_lngLoginId));

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            WEB3AdministratorForMock.mockValidateAuthority(l_administrator, "A0106", true, true);
            l_serviceImpl.getReferenceScreen(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80003, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * get顧客()にて該当データが取得できない場合
     * 抛出：WEB3ErrorCatalog.BUSINESS_ERROR_01035
     */
    public void testGetReferenceScreen_case0005()
    {
        final String STR_METHOD_NAME = "testGetReferenceScreen_case0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminInformSwElecDeliApplyServiceImpl l_serviceImpl = new WEB3AdminInformSwElecDeliApplyServiceImpl();

        WEB3AdminInformAccSwElecDeliApplyRefRequest l_request =
            new WEB3AdminInformAccSwElecDeliApplyRefRequestForTest();

        l_request.branchCode = "663";
        l_request.accountCode = "123523";
        try
        {

            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_institutionParams);

            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);

            LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                new Class[] {},
                l_loginInfoImplForMock);

            long l_lngLoginId = 33381330003L;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(l_lngLoginId));

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            WEB3AdministratorForMock.mockValidateAuthority(l_administrator, "A0106", true, true);
            WEB3AdministratorForMock.mockValidateBranchPermission(l_administrator, "663", true);

            l_serviceImpl.getReferenceScreen(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01035, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 各種連絡行が取得できない場合
     * 抛出：WEB3ErrorCatalog.BUSINESS_ERROR_01037
     */
    public void testGetReferenceScreen_case0006()
    {
        final String STR_METHOD_NAME = "testGetReferenceScreen_case0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminInformSwElecDeliApplyServiceImpl l_serviceImpl = new WEB3AdminInformSwElecDeliApplyServiceImpl();

        WEB3AdminInformAccSwElecDeliApplyRefRequest l_request =
            new WEB3AdminInformAccSwElecDeliApplyRefRequestForTest();

        l_request.branchCode = "663";
        l_request.accountCode = "251224";
        try
        {

            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setBranchCode("663");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);

            LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                new Class[] {},
                l_loginInfoImplForMock);

            long l_lngLoginId = 33381330003L;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(l_lngLoginId));

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            WEB3AdministratorForMock.mockValidateAuthority(l_administrator, "A0106", true, true);
            WEB3AdministratorForMock.mockValidateBranchPermission(l_administrator, "663", true);

            l_serviceImpl.getReferenceScreen(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01037, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 正常通過の場合
     * 
     */
    public void testGetReferenceScreen_case0007()
    {
        final String STR_METHOD_NAME = "testGetReferenceScreen_case0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminInformSwElecDeliApplyServiceImpl l_serviceImpl = new WEB3AdminInformSwElecDeliApplyServiceImpl();

        WEB3AdminInformAccSwElecDeliApplyRefRequest l_request =
            new WEB3AdminInformAccSwElecDeliApplyRefRequestForTest();

        l_request.branchCode = "663";
        l_request.accountCode = "251224";
        l_request.informType = "1";
        l_request.requestNumber = "23";
        try
        {
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setBranchCode("663");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(VariousInformRow.TYPE);
            VariousInformParams l_variousInformParams = TestDBUtility.getVariousInformRow();
            l_variousInformParams.setBranchCode("663");
            l_variousInformParams.setInstitutionCode("0D");
            l_variousInformParams.setInformDiv("1");
            l_variousInformParams.setRequestNumber("23");
            l_variousInformParams.setAccountCode("2512246");
            l_variousInformParams.setAccountName("ANDY");
            TestDBUtility.insertWithDel(l_variousInformParams);

            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);

            LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                new Class[] {},
                l_loginInfoImplForMock);

            long l_lngLoginId = 33381330003L;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(l_lngLoginId));

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            WEB3AdministratorForMock.mockValidateAuthority(l_administrator, "A0106", true, true);
            WEB3AdministratorForMock.mockValidateBranchPermission(l_administrator, "663", true);

            WEB3AdminInformAccSwElecDeliApplyRefResponse l_expectedResponse =
                l_serviceImpl.getReferenceScreen(l_request);
            
            assertEquals("663", l_expectedResponse.branchCode);
            assertEquals("2512246", l_expectedResponse.accountCode);
            assertEquals("ANDY", l_expectedResponse.accountName);
            
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidateChange_case0001()
    {
        final String STR_METHOD_NAME = "testValidateChange_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminInformSwElecDeliApplyServiceImpl l_serviceImpl = new WEB3AdminInformSwElecDeliApplyServiceImpl();
        WEB3AdminInformAccSwElecDeliChangeConfRequest l_request = new WEB3AdminInformAccSwElecDeliChangeConfRequest();
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(false);
            l_serviceImpl.validateChange(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidateChange_case0002()
    {
        final String STR_METHOD_NAME = "testValidateChange_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminInformSwElecDeliApplyServiceImpl l_serviceImpl = new WEB3AdminInformSwElecDeliApplyServiceImpl();
        WEB3AdminInformAccSwElecDeliChangeConfRequest l_request = new WEB3AdminInformAccSwElecDeliChangeConfRequest();
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            l_serviceImpl.validateChange(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00833, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidateChange_case0003()
    {
        final String STR_METHOD_NAME = "testValidateChange_case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminInformSwElecDeliApplyServiceImpl l_serviceImpl = new WEB3AdminInformSwElecDeliApplyServiceImpl();
        WEB3AdminInformAccSwElecDeliChangeConfRequest l_request =
            new WEB3AdminInformAccSwElecDeliChangeConfRequestForTest();
        try
        {
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                new Class[] {},
                l_loginInfoImplForMock);

            long l_lngLoginId = 33381330003L;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(l_lngLoginId));
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            WEB3AdministratorForMock.mockValidateAuthority(l_administrator, "A0106", true, false);
            l_serviceImpl.validateChange(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidateChange_case0004()
    {
        final String STR_METHOD_NAME = "testValidateChange_case0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminInformSwElecDeliApplyServiceImpl l_serviceImpl = new WEB3AdminInformSwElecDeliApplyServiceImpl();
        WEB3AdminInformAccSwElecDeliChangeConfRequest l_request =
            new WEB3AdminInformAccSwElecDeliChangeConfRequestForTest();
        
        try
        {
            TestDBUtility.deleteAll(AdministratorTypeRow.TYPE);
            
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("235");
            TestDBUtility.insertWithDel(l_administratorParams);
            
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);

            LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                new Class[] {},
                l_loginInfoImplForMock);

            long l_lngLoginId = 33381330003L;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(l_lngLoginId));
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            WEB3AdministratorForMock.mockValidateAuthority(l_administrator, "A0106", true, true);
            l_serviceImpl.validateChange(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80003, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * get顧客()にて該当データが取得できない場合
     * 抛出：WEB3ErrorCatalog.BUSINESS_ERROR_01035
     */
    public void testValidateChange_case0005()
    {
        final String STR_METHOD_NAME = "testValidateChange_case0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminInformSwElecDeliApplyServiceImpl l_serviceImpl = new WEB3AdminInformSwElecDeliApplyServiceImpl();

        WEB3AdminInformAccSwElecDeliChangeConfRequest l_request =
            new WEB3AdminInformAccSwElecDeliChangeConfRequestForTest();

        l_request.branchCode = "663";
        l_request.accountCode = "123523";
        try
        {

            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_institutionParams);

            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);

            LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                new Class[] {},
                l_loginInfoImplForMock);

            long l_lngLoginId = 33381330003L;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(l_lngLoginId));

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            WEB3AdministratorForMock.mockValidateAuthority(l_administrator, "A0106", true, true);
            WEB3AdministratorForMock.mockValidateBranchPermission(l_administrator, "663", true);

            l_serviceImpl.validateChange(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01035, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 各種連絡行が取得できない場合
     * 抛出：WEB3ErrorCatalog.BUSINESS_ERROR_01037
     */
    public void testValidateChange_case0006()
    {
        final String STR_METHOD_NAME = "testValidateChange_case0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminInformSwElecDeliApplyServiceImpl l_serviceImpl = new WEB3AdminInformSwElecDeliApplyServiceImpl();

        WEB3AdminInformAccSwElecDeliChangeConfRequest l_request =
            new WEB3AdminInformAccSwElecDeliChangeConfRequestForTest();

        l_request.branchCode = "663";
        l_request.accountCode = "251224";
        try
        {

            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setBranchCode("663");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);

            LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                new Class[] {},
                l_loginInfoImplForMock);

            long l_lngLoginId = 33381330003L;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(l_lngLoginId));

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            WEB3AdministratorForMock.mockValidateAuthority(l_administrator, "A0106", true, true);
            WEB3AdministratorForMock.mockValidateBranchPermission(l_administrator, "663", true);

            l_serviceImpl.validateChange(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01037, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * validate伝票変更 異常
     * 抛出：WEB3ErrorCatalog.BUSINESS_ERROR_02787
     */
    public void testValidateChange_case0007()
    {
        final String STR_METHOD_NAME = "testValidateChange_case0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminInformSwElecDeliApplyServiceImpl l_serviceImpl = new WEB3AdminInformSwElecDeliApplyServiceImpl();

        WEB3AdminInformAccSwElecDeliChangeConfRequest l_request =
            new WEB3AdminInformAccSwElecDeliChangeConfRequestForTest();

        l_request.branchCode = "663";
        l_request.accountCode = "251224";
        l_request.informType = "1";
        l_request.requestNumber = "23";
        try
        {

            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setBranchCode("663");
            l_mainAccountParams.setAccountCode("2512246");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(VariousInformRow.TYPE);
            VariousInformParams l_variousInformParams = TestDBUtility.getVariousInformRow();
            l_variousInformParams.setBranchCode("663");
            l_variousInformParams.setInstitutionCode("0D");
            l_variousInformParams.setInformDiv("1");
            l_variousInformParams.setRequestNumber("23");
            l_variousInformParams.setAccountCode("2512246");
            TestDBUtility.insertWithDel(l_variousInformParams);

            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);

            LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                new Class[] {},
                l_loginInfoImplForMock);

            long l_lngLoginId = 33381330003L;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(l_lngLoginId));

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            WEB3AdministratorForMock.mockValidateAuthority(l_administrator, "A0106", true, true);
            WEB3AdministratorForMock.mockValidateBranchPermission(l_administrator, "663", true);

            l_serviceImpl.validateChange(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02787, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * validate口座切替・電子交付申込情報(口座切替・電子交付申込情報, 口座切替・電子交付申込情報) 異常
     * 抛出：WEB3ErrorCatalog.BUSINESS_ERROR_02688
     */
    public void testValidateChange_case0008()
    {
        final String STR_METHOD_NAME = "testValidateChange_case0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminInformSwElecDeliApplyServiceImpl l_serviceImpl = new WEB3AdminInformSwElecDeliApplyServiceImpl();

        WEB3AdminInformAccSwElecDeliChangeConfRequest l_request =
            new WEB3AdminInformAccSwElecDeliChangeConfRequestForTest();

        l_request.branchCode = "663";
        l_request.accountCode = "251224";
        l_request.informType = "1";
        l_request.requestNumber = "23";
        try
        {

            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setBranchCode("663");
            l_mainAccountParams.setAccountCode("2512246");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(VariousInformRow.TYPE);
            VariousInformParams l_variousInformParams = TestDBUtility.getVariousInformRow();
            l_variousInformParams.setBranchCode("663");
            l_variousInformParams.setInstitutionCode("0D");
            l_variousInformParams.setInformDiv("1");
            l_variousInformParams.setRequestNumber("23");
            l_variousInformParams.setAccountCode("2512246");
            l_variousInformParams.setStatus("4");
            TestDBUtility.insertWithDel(l_variousInformParams);

            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);

            LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                new Class[] {},
                l_loginInfoImplForMock);

            long l_lngLoginId = 33381330003L;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(l_lngLoginId));

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            WEB3AdministratorForMock.mockValidateAuthority(l_administrator, "A0106", true, true);
            WEB3AdministratorForMock.mockValidateBranchPermission(l_administrator, "663", true);

            l_serviceImpl.validateChange(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02688, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidateChange_case0009()
    {
        final String STR_METHOD_NAME = "testValidateChange_case0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminInformSwElecDeliApplyServiceImpl l_serviceImpl = new WEB3AdminInformSwElecDeliApplyServiceImpl();

        WEB3AdminInformAccSwElecDeliChangeConfRequest l_request =
            new WEB3AdminInformAccSwElecDeliChangeConfRequestForTest();

        l_request.branchCode = "663";
        l_request.accountCode = "251224";
        l_request.informType = "1";
        l_request.requestNumber = "23";

        try
        {

            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setBranchCode("663");
            l_mainAccountParams.setAccountCode("2512246");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(VariousInformRow.TYPE);
            VariousInformParams l_variousInformParams = TestDBUtility.getVariousInformRow();
            l_variousInformParams.setBranchCode("663");
            l_variousInformParams.setInstitutionCode("0D");
            l_variousInformParams.setInformDiv("1");
            l_variousInformParams.setRequestNumber("23");
            l_variousInformParams.setAccountCode("2512246");
            l_variousInformParams.setStatus("4");
            TestDBUtility.insertWithDel(l_variousInformParams);
            WEB3AdminInformAccSwitchElecDeliApplyInfo l_changedInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
            l_changedInfo.accountStatementDiv = "1231";
            l_changedInfo.mobileAccoutDiv = "0";
            l_changedInfo.tradingReportDiv = "0";
            l_changedInfo.positionReportDiv = "1";
            l_changedInfo.positionReportCycleDiv = "1";
            l_changedInfo.certificateDepositDiv = "1";
            l_changedInfo.accountStatementDiv = "1";

            l_request.changedInfo = l_changedInfo;

            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);

            LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                new Class[] {},
                l_loginInfoImplForMock);

            long l_lngLoginId = 33381330003L;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(l_lngLoginId));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
                    "validateAccSwitchElecDeliApplyInfo",
                    new Class[]
                    { WEB3AdminInformAccSwitchElecDeliApplyInfo.class,
                            WEB3AdminInformAccSwitchElecDeliApplyInfo.class }, null);

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            WEB3AdministratorForMock.mockValidateAuthority(l_administrator, "A0106", true, true);
            WEB3AdministratorForMock.mockValidateBranchPermission(l_administrator, "663", true);

            WEB3AdminInformAccSwElecDeliChangeConfResponse l_response =
                l_serviceImpl.validateChange(l_request);
            assertTrue(true);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

//    public void testUpdateVariousInformChangeInfo_C0001()
//    {
//        String STR_METHOD_NAME = "testUpdateVariousInformChangeInfo_C0001()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
//            "getLoginInfo",
//            new Class[] {},
//            new LoginInfoImplForMock());
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl", 
//            "getLoginId",
//            new Class[] {},
//            new Long(33381330003L));
//
//        try
//        {
//            //データベースへデータをインサート
//            QueryProcessor l_processor = Processors.getDefaultProcessor();
//            l_processor.doDeleteAllQuery(AdministratorRow.TYPE);
//            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
//            TestDBUtility.insertWithDel(l_administratorParams);
//
//            l_processor.doDeleteAllQuery(VariousInformRow.TYPE);
//            VariousInformParams l_variousInformParams = TestDBUtility.getVariousInformRow();
//            l_variousInformParams.setExtDiv1("v1");
//            l_variousInformParams.setExtDiv11("11");
//            l_variousInformParams.setExtDiv13("13");
//            l_variousInformParams.setExtText5("exttext5");
//            TestDBUtility.insertWithDel(l_variousInformParams);
//
//            //実際メソッドをコール
//            l_variousInformParams.setInstitutionCode("123");
//            l_variousInformParams.setEmailAddress("12");
//            l_variousInformParams.setExtDiv1(null);
//            l_variousInformParams.setExtDiv11(null);
//            l_variousInformParams.setExtDiv13(null);
//            l_variousInformParams.setExtText4("exttext4");
//            l_variousInformParams.setExtText5(null);
//
//            Method l_method = WEB3AdminInformSwElecDeliApplyServiceImpl.class.getDeclaredMethod(
//                "updateVariousInformChangeInfo", new Class[]{VariousInformParams.class, String.class, String.class, String.class});
//            l_method.setAccessible(true);
//            l_method.invoke(l_service, new Object[]{l_variousInformParams, "123", "number", "code"});
//
//            //比較
//            List l_lisResults = l_processor.doFindAllQuery(VariousInformRow.TYPE);
//            VariousInformRow l_variousInformRow1 = (VariousInformRow)l_lisResults.get(0);
//            assertEquals("123", l_variousInformRow1.getInstitutionCode());
//            assertEquals("12", l_variousInformRow1.getInformDiv());
//            assertEquals("123", l_variousInformRow1.getRequestNumber());
//            assertEquals("000", l_variousInformRow1.getBranchCode());
//            assertNull(l_variousInformRow1.getAccountCode());
//            assertNull(l_variousInformRow1.getTraderCode());
//            assertNull(l_variousInformRow1.getAccountName());
//            assertNull(l_variousInformRow1.getEmailAddress());
//            assertNull(l_variousInformRow1.getExtDiv1());
//            assertNull(l_variousInformRow1.getExtDiv11());
//            assertNull(l_variousInformRow1.getExtDiv12());
//            assertNull(l_variousInformRow1.getExtDiv13());
//            assertEquals("exttext4", l_variousInformRow1.getExtText4());
//            assertEquals("exttext5", l_variousInformRow1.getExtText5());
//            assertEquals("330001", l_variousInformRow1.getLastUpdater());
//            assertEquals("1", l_variousInformRow1.getStatus());
//            assertNull(l_variousInformRow1.getErrorReasonCode());
//            assertEquals("number", l_variousInformRow1.getOrderRequestNumber());
//            assertEquals("code", l_variousInformRow1.getRequestCode());
//            assertNull(l_variousInformRow1.getSendTimestamp());
//
//            l_processor.doDeleteAllQuery(VariousInformRow.TYPE);
//        }
//        catch (Exception l_ex)
//        {
//            log.debug(STR_METHOD_NAME,l_ex);
//            fail();
//        }
//
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }

    public void testSubmitCancel_C0001()
    {
        String STR_METHOD_NAME = "testSubmitCancel_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //スタティックメソッドの準備
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("123");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext",
                l_clendarContext);

            //実際メソッドをコール
            WEB3AdminInformAccSwElecDeliDeleteCmpRequest l_request = new WEB3AdminInformAccSwElecDeliDeleteCmpRequest();
            l_service.submitCancel(l_request);
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSubmitCancel_C0002()
    {
        String STR_METHOD_NAME = "testSubmitCancel_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            //スタティックメソッドの準備
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setTradingTimeType("123");
            l_clendarContext.setOrderAcceptProduct("01");
            l_clendarContext.setOrderAcceptTransaction("07");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext",
                l_clendarContext);

            //データベースへデータをインサート
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(OrderAcceptStatusRow.TYPE);
            OrderAcceptStatusParams l_orderAcceptStatusParams = TestDBUtility.getOrderAcceptStatusRow();
            l_orderAcceptStatusParams.setOrderAccTransaction("07");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            //実際メソッドをコール
            WEB3AdminInformAccSwElecDeliDeleteCmpRequest l_request = new WEB3AdminInformAccSwElecDeliDeleteCmpRequest();
            l_service.submitCancel(l_request);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00833, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSubmitCancel_C0003()
    {
        String STR_METHOD_NAME = "testSubmitCancel_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
            "getLoginInfo",
            new Class[] {},
            new LoginInfoImplForMock());
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl", 
            "getLoginId",
            new Class[] {},
            new Long(33381330003L));
        try
        {
            //スタティックメソッドの準備
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setTradingTimeType("123");
            l_clendarContext.setOrderAcceptProduct("01");
            l_clendarContext.setOrderAcceptTransaction("07");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext",
                l_clendarContext);

            //データベースへデータをインサート
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(OrderAcceptStatusRow.TYPE);
            OrderAcceptStatusParams l_orderAcceptStatusParams = TestDBUtility.getOrderAcceptStatusRow();
            l_orderAcceptStatusParams.setOrderAccTransaction("07");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            l_processor.doDeleteAllQuery(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            l_processor.doDeleteAllQuery(AdminPermissionRow.TYPE);

            //実際メソッドをコール
            WEB3AdminInformAccSwElecDeliDeleteCmpRequest l_request = new WEB3AdminInformAccSwElecDeliDeleteCmpRequest();
            l_request.branchCode = "123";
            l_request.accountCode = "123456";
            l_request.informType = "0";
            l_request.requestNumber = "0";
            l_service.submitCancel(l_request);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSubmitCancel_C0004()
    {
        String STR_METHOD_NAME = "testSubmitCancel_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
            "getLoginInfo",
            new Class[] {},
            new LoginInfoImplForMock());
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl", 
            "getLoginId",
            new Class[] {},
            new Long(33381330003L));
        try
        {
            //スタティックメソッドの準備
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setTradingTimeType("123");
            l_clendarContext.setOrderAcceptProduct("01");
            l_clendarContext.setOrderAcceptTransaction("07");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext",
                l_clendarContext);

            //データベースへデータをインサート
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(OrderAcceptStatusRow.TYPE);
            OrderAcceptStatusParams l_orderAcceptStatusParams = TestDBUtility.getOrderAcceptStatusRow();
            l_orderAcceptStatusParams.setOrderAccTransaction("07");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            l_processor.doDeleteAllQuery(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("321");
            TestDBUtility.insertWithDel(l_administratorParams);

            l_processor.doDeleteAllQuery(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("A0106");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            l_processor.doDeleteAllQuery(AdministratorTypeRow.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            //実際メソッドをコール
            WEB3AdminInformAccSwElecDeliDeleteCmpRequest l_request = new WEB3AdminInformAccSwElecDeliDeleteCmpRequest();
            l_request.branchCode = "123";
            l_request.accountCode = "123456";
            l_request.informType = "0";
            l_request.requestNumber = "0";
            l_service.submitCancel(l_request);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01074, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSubmitCancel_C0005()
    {
        String STR_METHOD_NAME = "testSubmitCancel_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
            "getLoginInfo",
            new Class[] {},
            new LoginInfoImplForMock());
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl", 
            "getLoginId",
            new Class[] {},
            new Long(33381330003L));
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl", 
            "getLoginTypeId",
            new Class[] {},
            new Long(0L));
        HashMap l_map = new HashMap();
        l_map.put("TRADING_PWD_ENV", "0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl", 
            "getLoginTypeAttributes",
            new Class[] {long.class},
            l_map);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
            "checkPassword",
            new Class[] {String.class},
            Boolean.FALSE);
        try
        {
            //スタティックメソッドの準備
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setTradingTimeType("123");
            l_clendarContext.setOrderAcceptProduct("01");
            l_clendarContext.setOrderAcceptTransaction("07");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext",
                l_clendarContext);

            //データベースへデータをインサート
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(OrderAcceptStatusRow.TYPE);
            OrderAcceptStatusParams l_orderAcceptStatusParams = TestDBUtility.getOrderAcceptStatusRow();
            l_orderAcceptStatusParams.setOrderAccTransaction("07");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            l_processor.doDeleteAllQuery(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("321");
            TestDBUtility.insertWithDel(l_administratorParams);

            l_processor.doDeleteAllQuery(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("A0106");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            l_processor.doDeleteAllQuery(AdministratorTypeRow.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            //実際メソッドをコール
            WEB3AdminInformAccSwElecDeliDeleteCmpRequest l_request = new WEB3AdminInformAccSwElecDeliDeleteCmpRequest();
            l_request.branchCode = "321";
            l_request.accountCode = "123456";
            l_request.informType = "0";
            l_request.requestNumber = "0";
            l_service.submitCancel(l_request);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00009, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSubmitCancel_C0006()
    {
        String STR_METHOD_NAME = "testSubmitCancel_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
            "getLoginInfo",
            new Class[] {},
            new LoginInfoImplForMock());
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl", 
            "getLoginId",
            new Class[] {},
            new Long(33381330003L));
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl", 
            "getLoginTypeId",
            new Class[] {},
            new Long(0L));
        HashMap l_map = new HashMap();
        l_map.put("TRADING_PWD_ENV", "0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl", 
            "getLoginTypeAttributes",
            new Class[] {long.class},
            l_map);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
            "checkPassword",
            new Class[] {String.class},
            Boolean.TRUE);
        try
        {
            //スタティックメソッドの準備
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setTradingTimeType("123");
            l_clendarContext.setOrderAcceptProduct("01");
            l_clendarContext.setOrderAcceptTransaction("07");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext",
                l_clendarContext);

            //データベースへデータをインサート
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(OrderAcceptStatusRow.TYPE);
            OrderAcceptStatusParams l_orderAcceptStatusParams = TestDBUtility.getOrderAcceptStatusRow();
            l_orderAcceptStatusParams.setOrderAccTransaction("07");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            l_processor.doDeleteAllQuery(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("321");
            TestDBUtility.insertWithDel(l_administratorParams);

            l_processor.doDeleteAllQuery(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("A0106");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            l_processor.doDeleteAllQuery(AdministratorTypeRow.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);

            //実際メソッドをコール
            WEB3AdminInformAccSwElecDeliDeleteCmpRequest l_request = new WEB3AdminInformAccSwElecDeliDeleteCmpRequest();
            l_request.branchCode = "321";
            l_request.accountCode = "123456";
            l_request.informType = "0";
            l_request.requestNumber = "0";
            l_service.submitCancel(l_request);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01035, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSubmitCancel_C0007()
    {
        String STR_METHOD_NAME = "testSubmitCancel_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
            "getLoginInfo",
            new Class[] {},
            new LoginInfoImplForMock());
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl", 
            "getLoginId",
            new Class[] {},
            new Long(33381330003L));
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl", 
            "getLoginTypeId",
            new Class[] {},
            new Long(0L));
        HashMap l_map = new HashMap();
        l_map.put("TRADING_PWD_ENV", "0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl", 
            "getLoginTypeAttributes",
            new Class[] {long.class},
            l_map);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
            "checkPassword",
            new Class[] {String.class},
            Boolean.TRUE);
        try
        {
            //スタティックメソッドの準備
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setTradingTimeType("123");
            l_clendarContext.setOrderAcceptProduct("01");
            l_clendarContext.setOrderAcceptTransaction("07");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext",
                l_clendarContext);

            //データベースへデータをインサート
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(OrderAcceptStatusRow.TYPE);
            OrderAcceptStatusParams l_orderAcceptStatusParams = TestDBUtility.getOrderAcceptStatusRow();
            l_orderAcceptStatusParams.setOrderAccTransaction("07");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            l_processor.doDeleteAllQuery(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("321");
            TestDBUtility.insertWithDel(l_administratorParams);

            l_processor.doDeleteAllQuery(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("A0106");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            l_processor.doDeleteAllQuery(AdministratorTypeRow.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setBranchCode("321");
            l_mainAccountParams.setAccountCode("123456");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            l_processor.doDeleteAllQuery(VariousInformRow.TYPE);
            VariousInformParams l_variousInformParams = TestDBUtility.getVariousInformRow();
            l_variousInformParams.setInstitutionCode("0D");
            l_variousInformParams.setBranchCode("321");
            l_variousInformParams.setInformDiv("0");
            l_variousInformParams.setRequestNumber("1");
            TestDBUtility.insertWithDel(l_variousInformParams);

            //実際メソッドをコール
            WEB3AdminInformAccSwElecDeliDeleteCmpRequest l_request = new WEB3AdminInformAccSwElecDeliDeleteCmpRequest();
            l_request.branchCode = "321";
            l_request.accountCode = "123456";
            l_request.informType = "0";
            l_request.requestNumber = "0";
            l_service.submitCancel(l_request);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01037, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSubmitCancel_C0008()
    {
        String STR_METHOD_NAME = "testSubmitCancel_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
            "getLoginInfo",
            new Class[] {},
            new LoginInfoImplForMock());
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl", 
            "getLoginId",
            new Class[] {},
            new Long(33381330003L));
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl", 
            "getLoginTypeId",
            new Class[] {},
            new Long(0L));
        HashMap l_map = new HashMap();
        l_map.put("TRADING_PWD_ENV", "0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl", 
            "getLoginTypeAttributes",
            new Class[] {long.class},
            l_map);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
            "checkPassword",
            new Class[] {String.class},
            Boolean.TRUE);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
            "validateVoucherCancel",
            new Class[] {String.class, boolean.class},
            new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_02798, ""));
        try
        {
            //スタティックメソッドの準備
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setTradingTimeType("10");
            l_clendarContext.setOrderAcceptProduct("01");
            l_clendarContext.setOrderAcceptTransaction("07");
            l_clendarContext.setMarketCode("ti");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext",
                l_clendarContext);

            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007, 9-1, 20);
            Timestamp l_tsOrderAcceptDate = new Timestamp(l_calendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp", l_tsOrderAcceptDate);

            //データベースへデータをインサート
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(OrderAcceptStatusRow.TYPE);
            OrderAcceptStatusParams l_orderAcceptStatusParams = TestDBUtility.getOrderAcceptStatusRow();
            l_orderAcceptStatusParams.setOrderAccTransaction("07");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            l_processor.doDeleteAllQuery(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("321");
            TestDBUtility.insertWithDel(l_administratorParams);

            l_processor.doDeleteAllQuery(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("A0106");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            l_processor.doDeleteAllQuery(AdministratorTypeRow.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setBranchCode("321");
            l_mainAccountParams.setAccountCode("123456");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            l_processor.doDeleteAllQuery(VariousInformRow.TYPE);
            VariousInformParams l_variousInformParams = TestDBUtility.getVariousInformRow();
            l_variousInformParams.setInstitutionCode("0D");
            l_variousInformParams.setBranchCode("321");
            l_variousInformParams.setInformDiv("0");
            l_variousInformParams.setRequestNumber("0");
            TestDBUtility.insertWithDel(l_variousInformParams);

            l_processor.doDeleteAllQuery(CalendarRow.TYPE);
            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(WEB3DateUtility.getDate("20070920", "yyyyMMdd"));
            l_calendarParams.setBizDateType("1");
            l_calendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070920", "yyyyMMdd"));
            l_calendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070920", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);

            l_processor.doDeleteAllQuery(FeqCalendarRow.TYPE);
            FeqCalendarParams l_feqCalendarParams = new FeqCalendarParams();
            l_feqCalendarParams.setInstitutionCode("0D");
            l_feqCalendarParams.setMarketCode("ti");
            l_feqCalendarParams.setBizDate(WEB3DateUtility.getDate("20070920", "yyyyMMdd"));
            l_feqCalendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070920", "yyyyMMdd"));
            l_feqCalendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070920", "yyyyMMdd"));
            l_feqCalendarParams.setBizDateType("0");
            TestDBUtility.insertWithDel(l_feqCalendarParams);

            //実際メソッドをコール
            WEB3AdminInformAccSwElecDeliDeleteCmpRequest l_request = new WEB3AdminInformAccSwElecDeliDeleteCmpRequest();
            l_request.branchCode = "321";
            l_request.accountCode = "123456";
            l_request.informType = "0";
            l_request.requestNumber = "0";
            l_service.submitCancel(l_request);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02798, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testSubmitCancel_C0009()
    {
        String STR_METHOD_NAME = "testSubmitCancel_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
            "getLoginInfo",
            new Class[] {},
            new LoginInfoImplForMock());
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl", 
            "getLoginId",
            new Class[] {},
            new Long(33381330003L));
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl", 
            "getLoginTypeId",
            new Class[] {},
            new Long(0L));
        HashMap l_map = new HashMap();
        l_map.put("TRADING_PWD_ENV", "0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl", 
            "getLoginTypeAttributes",
            new Class[] {long.class},
            l_map);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
            "checkPassword",
            new Class[] {String.class},
            Boolean.TRUE);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
            "validateVoucherCancel",
            new Class[] {String.class, boolean.class},
            null);
        try
        {
            //スタティックメソッドの準備
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setTradingTimeType("10");
            l_clendarContext.setOrderAcceptProduct("01");
            l_clendarContext.setOrderAcceptTransaction("07");
            l_clendarContext.setMarketCode("ti");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext",
                l_clendarContext);

            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007, 9-1, 20);
            Timestamp l_tsOrderAcceptDate = new Timestamp(l_calendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp", l_tsOrderAcceptDate);

            //データベースへデータをインサート
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(OrderAcceptStatusRow.TYPE);
            OrderAcceptStatusParams l_orderAcceptStatusParams = TestDBUtility.getOrderAcceptStatusRow();
            l_orderAcceptStatusParams.setOrderAccTransaction("07");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            l_processor.doDeleteAllQuery(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("321");
            TestDBUtility.insertWithDel(l_administratorParams);

            l_processor.doDeleteAllQuery(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("A0106");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            l_processor.doDeleteAllQuery(AdministratorTypeRow.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setBranchCode("321");
            l_mainAccountParams.setAccountCode("123456");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            l_processor.doDeleteAllQuery(VariousInformRow.TYPE);
            VariousInformParams l_variousInformParams = TestDBUtility.getVariousInformRow();
            l_variousInformParams.setInstitutionCode("0D");
            l_variousInformParams.setBranchCode("321");
            l_variousInformParams.setInformDiv("0");
            l_variousInformParams.setRequestNumber("0");
            l_variousInformParams.setAccountCode("ac");
            l_variousInformParams.setExtText5("exttext5");
            l_variousInformParams.setExtText6("20070920000000");
            l_variousInformParams.setStatus("1");
            TestDBUtility.insertWithDel(l_variousInformParams);

            l_processor.doDeleteAllQuery(CalendarRow.TYPE);
            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(WEB3DateUtility.getDate("20070920", "yyyyMMdd"));
            l_calendarParams.setBizDateType("1");
            l_calendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070920", "yyyyMMdd"));
            l_calendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070920", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);

            l_processor.doDeleteAllQuery(FeqCalendarRow.TYPE);
            FeqCalendarParams l_feqCalendarParams = new FeqCalendarParams();
            l_feqCalendarParams.setInstitutionCode("0D");
            l_feqCalendarParams.setMarketCode("ti");
            l_feqCalendarParams.setBizDate(WEB3DateUtility.getDate("20070920", "yyyyMMdd"));
            l_feqCalendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070920", "yyyyMMdd"));
            l_feqCalendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070920", "yyyyMMdd"));
            l_feqCalendarParams.setBizDateType("0");
            TestDBUtility.insertWithDel(l_feqCalendarParams);

            l_processor.doDeleteAllQuery(HostConditionRegVoucherRow.TYPE);
            HostConditionRegVoucherParams l_hostConditionRegVoucherParams = TestDBUtility.getHostConditionRegVoucherRow();
            l_hostConditionRegVoucherParams.setOrderRequestNumber("0");
            l_hostConditionRegVoucherParams.setBranchCode("321");
            l_hostConditionRegVoucherParams.setAccountCode("ac");
            TestDBUtility.insertWithDel(l_hostConditionRegVoucherParams);

            //実際メソッドをコール
            WEB3AdminInformAccSwElecDeliDeleteCmpRequest l_request = new WEB3AdminInformAccSwElecDeliDeleteCmpRequest();
            l_request.branchCode = "321";
            l_request.accountCode = "123456";
            l_request.informType = "0";
            l_request.requestNumber = "0";
            l_service.submitCancel(l_request);

            //比較
            List l_lisResults = l_processor.doFindAllQuery(VariousInformRow.TYPE);
            VariousInformRow l_variousInformRow1 = (VariousInformRow)l_lisResults.get(0);
            assertEquals("330001", l_variousInformRow1.getLastUpdater());
            assertEquals("0", l_variousInformRow1.getStatus());

            List l_lisResults1 = l_processor.doFindAllQuery(MainAccountRow.TYPE);
            MainAccountRow l_mainAccountRow = (MainAccountRow)l_lisResults1.get(0);
            assertNull(l_mainAccountRow.getOnlyMobileOpenDiv());
            assertEquals("exttext5", l_mainAccountRow.getOnlyMblOpnDivLastUpdater());
            assertEquals(0, WEB3DateUtility.compareToDay(WEB3DateUtility.getDate("20070920", "yyyyMMdd"),
                l_mainAccountRow.getOnlyMblOpnDivTimestamp()));
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testGetInputScreen_C0003()
    {
        String STR_METHOD_NAME = "testGetInputScreen_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
            "getLoginInfo",
            new Class[] {},
            new LoginInfoImplForMock());
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl", 
            "getLoginId",
            new Class[] {},
            new Long(33381330003L));
        VariousInformParams l_variousInformParams = new VariousInformParams();
        l_variousInformParams.setStatus("abc");
        List l_lisInforms = new ArrayList();
        l_lisInforms.add(l_variousInformParams);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
            "getVariousInformList",
            new Class[] {String.class, String.class, String.class, String.class},
            l_lisInforms);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
            "validateVoucherMake",
            new Class[] {String.class, boolean.class},
            new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_02786, ""));
        try
        {
            //スタティックメソッドの準備
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setTradingTimeType("10");
            l_clendarContext.setOrderAcceptProduct("01");
            l_clendarContext.setOrderAcceptTransaction("07");
            l_clendarContext.setMarketCode("ti");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext",
                l_clendarContext);

            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007, 9-1, 20);
            Timestamp l_tsOrderAcceptDate = new Timestamp(l_calendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp", l_tsOrderAcceptDate);

            //データベースへデータをインサート
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(OrderAcceptStatusRow.TYPE);
            OrderAcceptStatusParams l_orderAcceptStatusParams = TestDBUtility.getOrderAcceptStatusRow();
            l_orderAcceptStatusParams.setOrderAccTransaction("07");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            l_processor.doDeleteAllQuery(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            l_processor.doDeleteAllQuery(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("A0106");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            l_processor.doDeleteAllQuery(AdministratorTypeRow.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setBranchCode("321");
            l_mainAccountParams.setAccountCode("123456");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            l_processor.doDeleteAllQuery(CalendarRow.TYPE);
            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(WEB3DateUtility.getDate("20070920", "yyyyMMdd"));
            l_calendarParams.setBizDateType("1");
            l_calendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070920", "yyyyMMdd"));
            l_calendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070920", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);

            l_processor.doDeleteAllQuery(FeqCalendarRow.TYPE);
            FeqCalendarParams l_feqCalendarParams = new FeqCalendarParams();
            l_feqCalendarParams.setInstitutionCode("0D");
            l_feqCalendarParams.setMarketCode("ti");
            l_feqCalendarParams.setBizDate(WEB3DateUtility.getDate("20070920", "yyyyMMdd"));
            l_feqCalendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070920", "yyyyMMdd"));
            l_feqCalendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070920", "yyyyMMdd"));
            l_feqCalendarParams.setBizDateType("0");
            TestDBUtility.insertWithDel(l_feqCalendarParams);

            //実際メソッドをコール
            WEB3AdminInformAccSwElecDeliApplyInpRequest l_request = new WEB3AdminInformAccSwElecDeliApplyInpRequest();
            l_request.branchCode = "321";
            l_request.accountCode = "123456";
            l_request.informType = "0";
            l_service.getInputScreen(l_request);
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02786, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testGetInputScreen_C0004()
    {
        String STR_METHOD_NAME = "testGetInputScreen_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
            "getLoginInfo",
            new Class[] {},
            new LoginInfoImplForMock());
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl", 
            "getLoginId",
            new Class[] {},
            new Long(33381330003L));
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
            "getVariousInformList",
            new Class[] {String.class, String.class, String.class, String.class},
            null);
        WEB3AdminInformAccSwitchElecDeliApplyInfo l_adminInformAccSwitchElecDeliApplyInfo =
            new WEB3AdminInformAccSwitchElecDeliApplyInfo();
        l_adminInformAccSwitchElecDeliApplyInfo.accountStatementDiv = "1";
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
            "createAccSwitchElecDeliApplyInfo",
            new Class[] {MainAccount.class},
            l_adminInformAccSwitchElecDeliApplyInfo);
        try
        {
            //スタティックメソッドの準備
            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setTradingTimeType("10");
            l_clendarContext.setOrderAcceptProduct("01");
            l_clendarContext.setOrderAcceptTransaction("07");
            l_clendarContext.setMarketCode("ti");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext",
                l_clendarContext);

            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007, 9-1, 20);
            Timestamp l_tsOrderAcceptDate = new Timestamp(l_calendar.getTimeInMillis());
            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp", l_tsOrderAcceptDate);

            //データベースへデータをインサート
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(OrderAcceptStatusRow.TYPE);
            OrderAcceptStatusParams l_orderAcceptStatusParams = TestDBUtility.getOrderAcceptStatusRow();
            l_orderAcceptStatusParams.setOrderAccTransaction("07");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

            l_processor.doDeleteAllQuery(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            l_processor.doDeleteAllQuery(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("A0106");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            l_processor.doDeleteAllQuery(AdministratorTypeRow.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);

            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setBranchCode("321");
            l_mainAccountParams.setAccountCode("123456");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            l_processor.doDeleteAllQuery(CalendarRow.TYPE);
            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(WEB3DateUtility.getDate("20070920", "yyyyMMdd"));
            l_calendarParams.setBizDateType("1");
            l_calendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070920", "yyyyMMdd"));
            l_calendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070920", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);

            l_processor.doDeleteAllQuery(FeqCalendarRow.TYPE);
            FeqCalendarParams l_feqCalendarParams = new FeqCalendarParams();
            l_feqCalendarParams.setInstitutionCode("0D");
            l_feqCalendarParams.setMarketCode("ti");
            l_feqCalendarParams.setBizDate(WEB3DateUtility.getDate("20070920", "yyyyMMdd"));
            l_feqCalendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070920", "yyyyMMdd"));
            l_feqCalendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070920", "yyyyMMdd"));
            l_feqCalendarParams.setBizDateType("0");
            TestDBUtility.insertWithDel(l_feqCalendarParams);

            //実際メソッドをコール
            WEB3AdminInformAccSwElecDeliApplyInpRequest l_request = new WEB3AdminInformAccSwElecDeliApplyInpRequest();
            l_request.branchCode = "321";
            l_request.accountCode = "123456";
            l_request.informType = "0";
//            l_request.requestNumber = "0";
            WEB3AdminInformAccSwElecDeliApplyInpResponse l_response = l_service.getInputScreen(l_request);

            //比較
            assertEquals("321", l_response.branchCode);
            assertEquals("123456", l_response.accountCode);
            assertEquals("内藤　@四郎", l_response.accountName);
            assertEquals("1", l_response.beforeInfo.accountStatementDiv);
            assertEquals("1", l_response.changedInfo.accountStatementDiv);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }


    /**
     * validate申込
     * get各種連絡一覧の戻り値() == null 
     */
    public void testValidateApplyCase0001()
    {
        final String STR_METHOD_NAME = "testValidateApplyCase0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminInformAccSwElecDeliApplyConfRequest l_request = new WEB3AdminInformAccSwElecDeliApplyConfRequest();
        l_request.branchCode = "123";
        l_request.accountCode = "251224";
        l_request.requestNumber = "1";
        l_request.informType = "2";
        l_request.changedInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
        l_request.changedInfo.tradingReportDiv = "0";
        l_request.changedInfo.positionReportDiv = "1";
        l_request.changedInfo.positionReportCycleDiv = "1";
        l_request.changedInfo.certificateDepositDiv = "1";
        l_request.changedInfo.accountStatementDiv = "1";
        
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
                    "validateAccSwitchElecDeliApplyInfo", 
                    new Class[]{ WEB3AdminInformAccSwitchElecDeliApplyInfo.class, WEB3AdminInformAccSwitchElecDeliApplyInfo.class },
                    null);
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            WEB3AdministratorForMock.mockValidateBranchPermission(l_administrator, l_request.branchCode, true);
            WEB3AdministratorForMock.mockValidateTradingPassword("123", true);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("123");
            l_mainAccountParams.setAccountCode("251224");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(VariousInformRow.TYPE);
            l_service.validateApply(l_request);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
//    /**
//     * validate申込
//     * get各種連絡一覧の戻り値() != null 
//     */
//    public void testValidateApplyCase0002()
//    {
//        final String STR_METHOD_NAME = "testValidateApplyCase0002()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        WEB3AdminInformAccSwElecDeliApplyConfRequest l_request = new WEB3AdminInformAccSwElecDeliApplyConfRequest();
//        l_request.branchCode = "123";
//        l_request.accountCode = "251224";
//        l_request.requestNumber = "1";
//        l_request.informType = "2";
//        
//        MOCK_MANAGER.setIsMockUsed(true);
//        try
//        {
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
//                    "validateAccSwitchElecDeliApplyInfo", 
//                    new Class[]{ WEB3AdminInformAccSwitchElecDeliApplyInfo.class, WEB3AdminInformAccSwitchElecDeliApplyInfo.class },
//                    null);
//            
//            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
//            
//            TestDBUtility.deleteAll(AdministratorRow.TYPE);
//            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
//            TestDBUtility.insertWithDel(l_administratorParams);
//            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
//            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
//            WEB3AdministratorForMock.mockValidateBranchPermission(l_administrator, l_request.branchCode, true);
//            WEB3AdministratorForMock.mockValidateTradingPassword("123", true);
//            
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            l_mainAccountParams.setInstitutionCode("0D");
//            l_mainAccountParams.setBranchCode("123");
//            l_mainAccountParams.setAccountCode("251224");
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//
//            TestDBUtility.deleteAll(VariousInformRow.TYPE);
//            VariousInformParams l_variousInformParams =
//                TestDBUtility.getVariousInformRow();
//            l_variousInformParams.setRequestNumber("1");
//            l_variousInformParams.setInformDiv("2");
//            l_variousInformParams.setInstitutionCode("0D");
//            l_variousInformParams.setBranchCode("123");
//            l_variousInformParams.setStatus("0");
//            l_variousInformParams.setAccountCode("251224");
//            l_variousInformParams.setAccountName("abc");
//            l_variousInformParams.setExtDiv1("0");
//            l_variousInformParams.setExtDiv2("99");
//            l_variousInformParams.setExtText2("20070925");
//            TestDBUtility.insertWithDel(l_variousInformParams);
//            l_service.validateApply(l_request);
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
    /**
     * validate申込
     * get各種連絡一覧の戻り値() != null
     * validate伝票作成抛異常。
     */
    public void testValidateApplyCase0003()
    {
        final String STR_METHOD_NAME = "testValidateApplyCase0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminInformAccSwElecDeliApplyConfRequest l_request = new WEB3AdminInformAccSwElecDeliApplyConfRequest();
        l_request.branchCode = "123";
        l_request.accountCode = "251224";
        l_request.requestNumber = "1";
        l_request.informType = "2";
        
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
                    "validateAccSwitchElecDeliApplyInfo", 
                    new Class[]{ WEB3AdminInformAccSwitchElecDeliApplyInfo.class, WEB3AdminInformAccSwitchElecDeliApplyInfo.class },
                    null);
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            WEB3AdministratorForMock.mockValidateBranchPermission(l_administrator, l_request.branchCode, true);
            WEB3AdministratorForMock.mockValidateTradingPassword("123", true);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("123");
            l_mainAccountParams.setAccountCode("251224");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(VariousInformRow.TYPE);
            VariousInformParams l_variousInformParams =
                TestDBUtility.getVariousInformRow();
            l_variousInformParams.setRequestNumber("1");
            l_variousInformParams.setInformDiv("2");
            l_variousInformParams.setInstitutionCode("0D");
            l_variousInformParams.setBranchCode("123");
            l_variousInformParams.setStatus("0");
            l_variousInformParams.setAccountCode("251224");
            l_variousInformParams.setAccountName("abc");
            l_variousInformParams.setExtDiv1("0");
            l_variousInformParams.setExtDiv2("99");
            TestDBUtility.insertWithDel(l_variousInformParams);
            l_service.validateApply(l_request);
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.SYSTEM_ERROR_80017);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
    }

    /**
     * validate取消
     * get顧客()にて該当データが取得できない場合、例外をthrowする。
     * WEB3ErrorCatalog.BUSINESS_ERROR_01035
     */
    public void testValidateCancelCase0001()
    {
        final String STR_METHOD_NAME = "testValidateCancelCase0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminInformAccSwElecDeliDeleteConfRequest l_request = new WEB3AdminInformAccSwElecDeliDeleteConfRequest();
        l_request.branchCode = "123";
        l_request.accountCode = "251224";
        l_request.requestNumber = "1";
        l_request.informType = "2";
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            WEB3AdministratorForMock.mockValidateBranchPermission(l_administrator, l_request.branchCode, true);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            l_service.validateCancel(l_request);

        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_01035);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
    }
    
    /**
     * validate取消
     * 各種連絡行が取得できない場合（戻り値 == null）、例外をスローする。
     * WEB3ErrorCatalog.BUSINESS_ERROR_01037
     */
    public void testValidateCancelCase0002()
    {
        final String STR_METHOD_NAME = "testValidateCancelCase0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminInformAccSwElecDeliDeleteConfRequest l_request = new WEB3AdminInformAccSwElecDeliDeleteConfRequest();
        l_request.branchCode = "123";
        l_request.accountCode = "251224";
        l_request.requestNumber = "1";
        l_request.informType = "2";
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            WEB3AdministratorForMock.mockValidateBranchPermission(l_administrator, l_request.branchCode, true);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("123");
            l_mainAccountParams.setAccountCode("251224");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(VariousInformRow.TYPE);
            l_service.validateCancel(l_request);

        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_01037);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
    }
    
    /**
     * validate取消
     * 正常通過，返回正確。
     */
    public void testValidateCancelCase0003()
    {
        final String STR_METHOD_NAME = "testValidateCancelCase0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminInformAccSwElecDeliDeleteConfRequest l_request = new WEB3AdminInformAccSwElecDeliDeleteConfRequest();
        l_request.branchCode = "123";
        l_request.accountCode = "251224";
        l_request.requestNumber = "1";
        l_request.informType = "2";
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            WEB3AdministratorForMock.mockValidateBranchPermission(l_administrator, l_request.branchCode, true);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("123");
            l_mainAccountParams.setAccountCode("251224");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(VariousInformRow.TYPE);
            VariousInformParams l_variousInformParams =
                TestDBUtility.getVariousInformRow();
            l_variousInformParams.setRequestNumber("1");
            l_variousInformParams.setInformDiv("2");
            l_variousInformParams.setInstitutionCode("0D");
            l_variousInformParams.setBranchCode("123");
            l_variousInformParams.setStatus("1");
            l_variousInformParams.setAccountCode("251224");
            l_variousInformParams.setAccountName("abc");
            l_variousInformParams.setExtDiv1("33");
            l_variousInformParams.setExtDiv2("99");
            TestDBUtility.insertWithDel(l_variousInformParams);

            WEB3AdminInformAccSwElecDeliDeleteConfResponse l_response = l_service.validateCancel(l_request);
            assertEquals("123", l_response.branchCode);
            assertEquals("251224", l_response.accountCode);
            assertEquals("abc", l_response.accountName);
            WEB3AdminInformAccSwitchElecDeliApplyInfo l_seliApplyInfo =
                l_response.changedInfo;
            assertEquals("33", l_seliApplyInfo.mobileAccoutDiv);
            assertEquals("99", l_seliApplyInfo.tradingReportDiv);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * submit変更
     * get顧客()にて該当データが取得できない場合、例外をthrowする。
     * WEB3ErrorCatalog.BUSINESS_ERROR_01035
     */
    public void testSubmitChangeCase0001()
    {
        final String STR_METHOD_NAME = "testSubmitChangeCase0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminInformAccSwElecDeliChangeCmpRequest l_request = new WEB3AdminInformAccSwElecDeliChangeCmpRequest();
        l_request.branchCode = "123";
        l_request.accountCode = "251224";
        l_request.requestNumber = "1";
        l_request.informType = "2";
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            WEB3AdministratorForMock.mockValidateBranchPermission(l_administrator, l_request.branchCode, true);
            WEB3AdministratorForMock.mockValidateTradingPassword("123", true);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);

            l_service.submitChange(l_request);
            
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_01035);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
    }
    
    /**
     * submit変更
     * 各種連絡行が取得できない場合（戻り値 == null）、例外をスローする。
     * WEB3ErrorCatalog.BUSINESS_ERROR_01037
     */
    public void testSubmitChangeCase0002()
    {
        final String STR_METHOD_NAME = "testSubmitChangeCase0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminInformAccSwElecDeliChangeCmpRequest l_request = new WEB3AdminInformAccSwElecDeliChangeCmpRequest();
        l_request.branchCode = "123";
        l_request.accountCode = "251224";
        l_request.requestNumber = "1";
        l_request.informType = "2";
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            WEB3AdministratorForMock.mockValidateBranchPermission(l_administrator, l_request.branchCode, true);
            WEB3AdministratorForMock.mockValidateTradingPassword("123", true);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("123");
            l_mainAccountParams.setAccountCode("251224");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(VariousInformRow.TYPE);

            l_service.submitChange(l_request);
            
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_01037);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
    }
    
//    /**
//     * submit変更
//     * isトリガ発行 == true 
//     * モバイル専用口座開設区分に変更がある
//     * 正常通過，返回正確。
//     */
//    public void testSubmitChangeCase0003()
//    {
//        final String STR_METHOD_NAME = "testSubmitChangeCase0003()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        WEB3AdminInformAccSwElecDeliChangeCmpRequest l_request = new WEB3AdminInformAccSwElecDeliChangeCmpRequest();
//        l_request.branchCode = "123";
//        l_request.accountCode = "251224";
//        l_request.requestNumber = "1";
//        l_request.informType = "2";
//        WEB3AdminInformAccSwitchElecDeliApplyInfo l_info = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
//        l_info.mobileAccoutDiv = "1";
//        l_info.taxType = "1";
//        l_info.marginTaxType = "2";
//        l_request.changedInfo = l_info;
//        
//        MOCK_MANAGER.setIsMockUsed(true);
//        try
//        {
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
//                "validateAccSwitchElecDeliApplyInfo", 
//                new Class[]{ WEB3AdminInformAccSwitchElecDeliApplyInfo.class, WEB3AdminInformAccSwitchElecDeliApplyInfo.class },
//                null);
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
//                "submitMarketTrigger", 
//                new Class[]{ String.class, String.class },
//                null);
//            
//            Date l_datExpect = WEB3DateUtility.getDate("20060924","yyyyMMdd");
//            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);
//            
//            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
//            
//            TestDBUtility.deleteAll(AdministratorRow.TYPE);
//            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
//            TestDBUtility.insertWithDel(l_administratorParams);
//            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
//            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
//            WEB3AdministratorForMock.mockValidateBranchPermission(l_administrator, l_request.branchCode, true);
//            WEB3AdministratorForMock.mockValidateTradingPassword("123", true);
//            
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            l_mainAccountParams.setInstitutionCode("0D");
//            l_mainAccountParams.setBranchCode("123");
//            l_mainAccountParams.setAccountCode("251224");
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//
//            TestDBUtility.deleteAll(VariousInformRow.TYPE);
//            VariousInformParams l_variousInformParams =
//                TestDBUtility.getVariousInformRow();
//            l_variousInformParams.setRequestNumber("1");
//            l_variousInformParams.setInformDiv("2");
//            l_variousInformParams.setInstitutionCode("0D");
//            l_variousInformParams.setBranchCode("123");
//            l_variousInformParams.setStatus("0");
//            l_variousInformParams.setAccountCode("251224");
//            l_variousInformParams.setAccountName("abc");
//            l_variousInformParams.setExtDiv1("0");
//            l_variousInformParams.setExtDiv2("99");
//            TestDBUtility.insertWithDel(l_variousInformParams);
//            
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
//            l_tradingTimeParams.setInstitutionCode("0D");
//            l_tradingTimeParams.setBranchCode("123");
//            l_tradingTimeParams.setMarketCode("N1");
//            l_tradingTimeParams.setTradingTimeType("01");
//            l_tradingTimeParams.setProductCode("0");
//            l_tradingTimeParams.setBizDateType("1");
//            l_tradingTimeParams.setStartTime("000000");
//            l_tradingTimeParams.setEndTime("235959");
//            l_tradingTimeParams.setSubmitMarketTrigger("1");
//            TestDBUtility.insertWithDel(l_tradingTimeParams);
//            
//            WEB3AdminInformAccSwElecDeliChangeCmpResponse l_response = l_service.submitChange(l_request);
//            WEB3AdminInformAccSwitchElecDeliAppDtInfo l_accSwitchElecDeliAppDtInfo = l_response.dateInfo;
//            assertEquals(WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(),"yyyyMMdd"), 
//                WEB3DateUtility.formatDate(l_accSwitchElecDeliAppDtInfo.applyDate, "yyyyMMdd"));
//            assertEquals("180925", l_accSwitchElecDeliAppDtInfo.marginTaxTypeOpenDate);
//            assertNull(l_accSwitchElecDeliAppDtInfo.taxTypeOpenDate);
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    /**
//     * submit変更
//     * isトリガ発行 == false 
//     * モバイル専用口座開設区分に変更がある以外
//     * 正常通過，返回正確。
//     */
//    public void testSubmitChangeCase0004()
//    {
//        final String STR_METHOD_NAME = "testSubmitChangeCase0004()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        WEB3AdminInformAccSwElecDeliChangeCmpRequest l_request = new WEB3AdminInformAccSwElecDeliChangeCmpRequest();
//        l_request.branchCode = "123";
//        l_request.accountCode = "251224";
//        l_request.requestNumber = "1";
//        l_request.informType = "2";
//        WEB3AdminInformAccSwitchElecDeliApplyInfo l_info = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
//        l_info.mobileAccoutDiv = "1";
//        l_info.taxType = "1";
//        l_info.marginTaxType = "2";
//        l_request.changedInfo = l_info;
//        
//        MOCK_MANAGER.setIsMockUsed(true);
//        try
//        {
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
//                "validateAccSwitchElecDeliApplyInfo", 
//                new Class[]{ WEB3AdminInformAccSwitchElecDeliApplyInfo.class, WEB3AdminInformAccSwitchElecDeliApplyInfo.class },
//                null);
//            
//            Date l_datExpect = WEB3DateUtility.getDate("20060924","yyyyMMdd");
//            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);
//            
//            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
//            
//            TestDBUtility.deleteAll(AdministratorRow.TYPE);
//            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
//            TestDBUtility.insertWithDel(l_administratorParams);
//            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
//            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
//            WEB3AdministratorForMock.mockValidateBranchPermission(l_administrator, l_request.branchCode, true);
//            WEB3AdministratorForMock.mockValidateTradingPassword("123", true);
//            
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            l_mainAccountParams.setInstitutionCode("0D");
//            l_mainAccountParams.setBranchCode("123");
//            l_mainAccountParams.setAccountCode("251224");
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//
//            TestDBUtility.deleteAll(VariousInformRow.TYPE);
//            VariousInformParams l_variousInformParams =
//                TestDBUtility.getVariousInformRow();
//            l_variousInformParams.setRequestNumber("1");
//            l_variousInformParams.setInformDiv("2");
//            l_variousInformParams.setInstitutionCode("0D");
//            l_variousInformParams.setBranchCode("123");
//            l_variousInformParams.setStatus("0");
//            l_variousInformParams.setAccountCode("251224");
//            l_variousInformParams.setAccountName("abc");
//            l_variousInformParams.setExtDiv1("1");
//            l_variousInformParams.setExtDiv2("99");
//            TestDBUtility.insertWithDel(l_variousInformParams);
//
//            WEB3AdminInformAccSwElecDeliChangeCmpResponse l_response = l_service.submitChange(l_request);
//            WEB3AdminInformAccSwitchElecDeliAppDtInfo l_accSwitchElecDeliAppDtInfo = l_response.dateInfo;
//            assertEquals(WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(),"yyyyMMdd"), 
//                WEB3DateUtility.formatDate(l_accSwitchElecDeliAppDtInfo.applyDate, "yyyyMMdd"));
//            assertEquals("180925", l_accSwitchElecDeliAppDtInfo.marginTaxTypeOpenDate);
//            assertNull(l_accSwitchElecDeliAppDtInfo.taxTypeOpenDate);
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    /**
//     * submit変更
//     * isトリガ発行 == false 
//     * モバイル専用口座開設区分に変更がある
//     * 顧客マスタテーブルを更新する正確。
//     */
//    public void testSubmitChangeCase0005()
//    {
//        final String STR_METHOD_NAME = "testSubmitChangeCase0005()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        WEB3AdminInformAccSwElecDeliChangeCmpRequest l_request = new WEB3AdminInformAccSwElecDeliChangeCmpRequest();
//        l_request.branchCode = "123";
//        l_request.accountCode = "251224";
//        l_request.requestNumber = "1";
//        l_request.informType = "2";
//        WEB3AdminInformAccSwitchElecDeliApplyInfo l_info = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
//        l_info.mobileAccoutDiv = "1";
//        l_info.taxType = "1";
//        l_info.marginTaxType = "2";
//        l_request.changedInfo = l_info;
//        
//        MOCK_MANAGER.setIsMockUsed(true);
//        try
//        {
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
//                "validateAccSwitchElecDeliApplyInfo", 
//                new Class[]{ WEB3AdminInformAccSwitchElecDeliApplyInfo.class, WEB3AdminInformAccSwitchElecDeliApplyInfo.class },
//                null);
//            
//            Date l_datExpect = WEB3DateUtility.getDate("20060924","yyyyMMdd");
//            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);
//            
//            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
//            
//            TestDBUtility.deleteAll(AdministratorRow.TYPE);
//            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
//            TestDBUtility.insertWithDel(l_administratorParams);
//            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
//            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
//            WEB3AdministratorForMock.mockValidateBranchPermission(l_administrator, l_request.branchCode, true);
//            WEB3AdministratorForMock.mockValidateTradingPassword("123", true);
//            
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            l_mainAccountParams.setInstitutionCode("0D");
//            l_mainAccountParams.setBranchCode("123");
//            l_mainAccountParams.setAccountCode("251224");
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//
//            TestDBUtility.deleteAll(VariousInformRow.TYPE);
//            VariousInformParams l_variousInformParams =
//                TestDBUtility.getVariousInformRow();
//            l_variousInformParams.setRequestNumber("1");
//            l_variousInformParams.setInformDiv("2");
//            l_variousInformParams.setInstitutionCode("0D");
//            l_variousInformParams.setBranchCode("123");
//            l_variousInformParams.setStatus("0");
//            l_variousInformParams.setAccountCode("251224");
//            l_variousInformParams.setAccountName("abc");
//            l_variousInformParams.setExtDiv1("0");
//            l_variousInformParams.setExtDiv2("99");
//            TestDBUtility.insertWithDel(l_variousInformParams);
//
//           l_service.submitChange(l_request);
//           
//           MainAccountRow l_row = MainAccountDao.findRowByAccountId(l_mainAccountParams.getAccountId());
//           assertEquals("1", l_row.getOnlyMobileOpenDiv());
//           assertEquals(l_administratorParams.getAdministratorCode(), l_row.getOnlyMblOpnDivLastUpdater());
//           assertEquals(WEB3DateUtility.formatDate(GtlUtils.getTradingSystem().getSystemTimestamp(),"yyyyMMdd"), 
//                   WEB3DateUtility.formatDate(l_row.getOnlyMblOpnDivTimestamp(),"yyyyMMdd"));
//            
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
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
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            l_mainAccountParams.setOnlyMobileOpenDiv("0");
//            l_mainAccountParams.setAccountId(333812512246L);
//            l_mainAccountParams.setInstitutionCode("0D");
//            l_mainAccountParams.setInstitutionId(33L);
//            l_mainAccountParams.setAccountCode("654321");
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
//            TestDBUtility.deleteAll(AdministratorRow.TYPE);
//            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
//            TestDBUtility.insertWithDel(l_administratorParams);
//            WEB3AdministratorForTest l_administrator = new WEB3AdministratorForTest(l_administratorParams);
//            
//            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
//            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
//            WEB3AdministratorForMock.mockValidateBranchPermission(l_administrator, "381", true);
//            WEB3AdministratorForMock.mockValidateTradingPassword("381", true);
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
//            WEB3AdminInformSwElecDeliApplyServiceImplForTest l_impl = new WEB3AdminInformSwElecDeliApplyServiceImplForTest();
//            WEB3AdminInformAccSwElecDeliApplyCmpRequest l_request = new WEB3AdminInformAccSwElecDeliApplyCmpRequest();
//            WEB3AdminInformAccSwitchElecDeliApplyInfo changedInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
//            changedInfo.taxType = "0";
//            changedInfo.taxTypeNext = "0";
//            changedInfo.marginTaxType = "1";
//            changedInfo.marginTaxTypeNext = "1";
//            l_request.branchCode = "381";
//            l_request.accountCode = "654321";
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
//            l_mainAccountParams.setAccountCode("654321");
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
//            TestDBUtility.deleteAll(AdministratorRow.TYPE);
//            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
//            TestDBUtility.insertWithDel(l_administratorParams);
//            WEB3AdministratorForTest l_administrator = new WEB3AdministratorForTest(l_administratorParams);
//            
//            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
//            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
//            WEB3AdministratorForMock.mockValidateBranchPermission(l_administrator, "381", true);
//            WEB3AdministratorForMock.mockValidateTradingPassword("381", true);
//            
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
//            WEB3AdminInformSwElecDeliApplyServiceImplForTest l_impl = new WEB3AdminInformSwElecDeliApplyServiceImplForTest();
//            WEB3AdminInformAccSwElecDeliApplyCmpRequest l_request = new WEB3AdminInformAccSwElecDeliApplyCmpRequest();
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
//            l_request.branchCode = "381";
//            l_request.accountCode = "654321";
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

    //20071024
    //submit変更
    //is伝票作成()の戻り値がtrueの場合
    //各種連絡行 更新後的直 データコード： “GI843”
    //           伝票作成状況： 1：作成済
    public void testSubmitChangeCase1()
    {
        final String STR_METHOD_NAME = "testSubmitChangeCase1()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            this.initData();
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setOnlyMobileOpenDiv("0");
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setInstitutionId(33L);
            l_mainAccountParams.setAccountCode("654321");
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
            l_mainAccountParams.setMarginTaxType(TaxTypeEnum.SPECIAL);
            l_mainAccountParams.setMarginTaxTypeNext(TaxTypeEnum.SPECIAL_WITHHOLD);
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
            l_mainAccountParams.setPositionReportDiv("1");
            l_mainAccountParams.setPositionReportCycleDiv("1");//取引残高報告書作成周期区分 == (引数)各種連絡行.区分４
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
            l_mainAccountParams.setSpMngAccOpenDiv(null);
            l_mainAccountParams.setOnlyMblOpnDivTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(HostConditionRegVoucherRow.TYPE);
            TestDBUtility.deleteAll(VariousInformRow.TYPE);
            VariousInformParams variousInformParams = TestDBUtility.getVariousInformRow();
            variousInformParams.setBranchCode("381");
            variousInformParams.setInstitutionCode("0D");
            variousInformParams.setInformDiv("1");
            variousInformParams.setRequestNumber("1");
            variousInformParams.setStatus("1");
            TestDBUtility.insertWithDel(variousInformParams);
            
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3AdministratorForTest l_administrator = new WEB3AdministratorForTest(l_administratorParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            WEB3AdministratorForMock.mockValidateBranchPermission(l_administrator, "381", true);
            WEB3AdministratorForMock.mockValidateTradingPassword("381", true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
                "validateAccSwitchElecDeliApplyInfo",
                new Class[]{ WEB3AdminInformAccSwitchElecDeliApplyInfo.class, WEB3AdminInformAccSwitchElecDeliApplyInfo.class },
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
                    "validateVoucherChange",
                    new Class[]{String.class, boolean.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.inform.service.delegate.stdimpls.WEB3InformHostReqOrderNumberManageServiceImpl",
                "getNewOrderRequestCode", 
                new Class[]{ String.class, String.class },
                "888888");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl",
                    "getNewNumber", 
                    new Class[]{ String.class, String.class, ProductTypeEnum.class },
                    "8");
            
            WEB3AdminInformSwElecDeliApplyServiceImplForTest l_impl = new WEB3AdminInformSwElecDeliApplyServiceImplForTest();
            WEB3AdminInformAccSwElecDeliChangeCmpRequest l_request = new WEB3AdminInformAccSwElecDeliChangeCmpRequest();
            WEB3AdminInformAccSwitchElecDeliApplyInfo changedInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
            changedInfo.taxType = "0";
            changedInfo.taxTypeNext = "0";
            changedInfo.marginTaxType = "1";
            changedInfo.marginTaxTypeNext = "1";
            changedInfo.tradingReportDiv = "0";
            changedInfo.positionReportDiv = "1";
            changedInfo.positionReportCycleDiv = "1";
            changedInfo.certificateDepositDiv = "1";
            changedInfo.accountStatementDiv = "1";
            l_request.branchCode = "381";
            l_request.accountCode = "654321";
            l_request.informType = "1";
            l_request.requestNumber = "1";
            l_request.changedInfo = changedInfo;
            l_impl.submitChange(l_request);
            
            VariousInformRow l_row = VariousInformDao.findRowByPk("0D","1","1","381");
            assertEquals(l_row.getRequestCode(), "GI843");
            assertEquals(l_row.getStatus(), "1");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
//    //submit変更
//    //is伝票作成()の戻り値がfalseの場合
//    //各種連絡行 更新後的直 データコード： null
//    //           伝票作成状況：  3：受付完了
//    public void testSubmitChangeCase2()
//    {
//        final String STR_METHOD_NAME = "testSubmitChangeCase2()";
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
//            l_mainAccountParams.setAccountCode("654321");
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
//            TestDBUtility.deleteAll(VariousInformRow.TYPE);
//            VariousInformParams variousInformParams = TestDBUtility.getVariousInformRow();
//            variousInformParams.setBranchCode("381");
//            variousInformParams.setInstitutionCode("0D");
//            variousInformParams.setInformDiv("1");
//            variousInformParams.setRequestNumber("1");
//            variousInformParams.setStatus("1");
//            TestDBUtility.insertWithDel(variousInformParams);
//            
//            
//            TestDBUtility.deleteAll(AdministratorRow.TYPE);
//            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
//            TestDBUtility.insertWithDel(l_administratorParams);
//            WEB3AdministratorForTest l_administrator = new WEB3AdministratorForTest(l_administratorParams);
//            
//            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
//            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
//            WEB3AdministratorForMock.mockValidateBranchPermission(l_administrator, "381", true);
//            WEB3AdministratorForMock.mockValidateTradingPassword("381", true);
//            
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
//                    "webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl",
//                    "validateVoucherChange",
//                    new Class[]{String.class, boolean.class},
//                    null);
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
//            WEB3AdminInformSwElecDeliApplyServiceImplForTest l_impl = new WEB3AdminInformSwElecDeliApplyServiceImplForTest();
//            WEB3AdminInformAccSwElecDeliChangeCmpRequest l_request = new WEB3AdminInformAccSwElecDeliChangeCmpRequest();
//            WEB3AdminInformAccSwitchElecDeliApplyInfo changedInfo = new WEB3AdminInformAccSwitchElecDeliApplyInfo();
//            changedInfo.taxType = "0";
//            changedInfo.taxTypeNext = "0";
//            changedInfo.marginTaxType = "1";
//            changedInfo.marginTaxTypeNext= "1";
//            changedInfo.positionReportCycleDiv = "1";//取引残高報告書作成周期区分 == (引数)各種連絡行.区分４
//            changedInfo.positionReportDiv = "1";
//            changedInfo.certificateDepositDiv = "1";
//            changedInfo.accountStatementDiv = "1";
//            changedInfo.tradingReportDiv = "1";
//            changedInfo.taxType = "2";
//            changedInfo.taxTypeNext = "3";
//            changedInfo.marginTaxType = "2";
//            changedInfo.marginTaxTypeNext = "3";
//            changedInfo.tradingReportDiv = "0";
//            changedInfo.positionReportDiv = "1";
//            changedInfo.positionReportCycleDiv = "1";
//            changedInfo.certificateDepositDiv = "1";
//            changedInfo.accountStatementDiv = "1";
//            
//            
//            l_request.informType = "1";
//            l_request.branchCode = "381";
//            l_request.accountCode = "654321";
//            l_request.changedInfo = changedInfo;
//            l_request.requestNumber = "1";
//            l_impl.submitChange(l_request);
//            
//            VariousInformRow l_row = VariousInformDao.findRowByPk("0D","1","1","381");
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

    private class WEB3AdminInformForTest extends WEB3GenRequest
    {
        public WEB3GenResponse createResponse()
        {
            return null;
        }
    }
    
    private class WEB3AdminInformSwElecDeliApplyServiceImplForTest extends WEB3AdminInformSwElecDeliApplyServiceImpl
    {
        
        
    }

    private void initData()
    {
        String STR_METHOD_NAME = "initData()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            //AdministratorRow
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            this.l_administratorParams = TestDBUtility.getAdministratorRow();
            this.l_administratorParams.setAdministratorId(33381330001L);
            this.l_administratorParams.setBranchCode("123");
            this.l_administratorParams.setAdministratorCode("330001");
            this.l_administratorParams.setInstitutionId(33L);
            this.l_administratorParams.setInstitutionCode("0D");
            this.l_administratorParams.setLoginId(33381330003L);
            this.l_administratorParams.setPermissionLevel("331");  
            TestDBUtility.insertWithDel(this.l_administratorParams);
            AdministratorParams l_administratorParams1 = TestDBUtility.getAdministratorRow();
            l_administratorParams1.setAdministratorId(33381330001L);
            l_administratorParams.setBranchCode("381");
            l_administratorParams1.setAdministratorCode("330001");
            l_administratorParams1.setInstitutionId(33L);
            l_administratorParams1.setInstitutionCode("0D");
            l_administratorParams1.setLoginId(33381330003L);
            l_administratorParams1.setPermissionLevel("331");
            TestDBUtility.insertWithDel(l_administratorParams1);
            
            //AdminPermissionRow
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams = new AdminPermissionParams();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("A0106");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            l_adminPermissionParams.setLastUpdater("sra501");
            l_adminPermissionParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040210","yyyyMMdd"));
            l_adminPermissionParams.setUpdateTimestamp(WEB3DateUtility.getDate("20040210","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            //TradingTimeRow
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setInstitutionCode("0D");
            l_tradingTimeParams1.setBranchCode("381");
            l_tradingTimeParams1.setMarketCode("SP");
            l_tradingTimeParams1.setTradingTimeType("26");
            l_tradingTimeParams1.setProductCode("0");
            l_tradingTimeParams1.setBizDateType("0");
            l_tradingTimeParams1.setStartTime("000000");
            l_tradingTimeParams1.setEndTime("235959");
            l_tradingTimeParams1.setSubmitMarketTrigger("0");
            l_tradingTimeParams1.setEnableOrder("0");
            l_tradingTimeParams1.setBizdateCalcParameter("1");
            l_tradingTimeParams1.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_tradingTimeParams1.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            TradingTimeParams l_tradingTimeParams2 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams2.setInstitutionCode("0D");
            l_tradingTimeParams2.setBranchCode("123");
            l_tradingTimeParams2.setMarketCode("N1");
            l_tradingTimeParams2.setTradingTimeType("01");
            l_tradingTimeParams2.setProductCode("0");
            l_tradingTimeParams2.setBizDateType("1");
            l_tradingTimeParams2.setStartTime("000000");
            l_tradingTimeParams2.setEndTime("235959");
            l_tradingTimeParams2.setSubmitMarketTrigger("0");
            l_tradingTimeParams2.setEnableOrder("0");
            l_tradingTimeParams2.setBizdateCalcParameter("1");
            l_tradingTimeParams2.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_tradingTimeParams2.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.insertWithDel(l_tradingTimeParams2);
            
            //AdministratorTypeRow
            TestDBUtility.deleteAll(AdministratorTypeRow.TYPE);
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel("331");
            l_administratorTypeParams.setLastUpdater("");
            l_administratorTypeParams.setDirAdminFlag(0);
            l_administratorTypeParams.setAllBranchPermissionFlag(BooleanEnum.FALSE);
            l_administratorTypeParams.setPermissionLevelName("更新可能管理者");
            l_administratorTypeParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            l_administratorTypeParams.setUpdateTimestamp(WEB3DateUtility.getDate("20040130","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            
            //MainAccountRow
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setInstitutionId(33L);
            l_mainAccountParams.setAccountCode("2512246");
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
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //VariousInformRow
            TestDBUtility.deleteAll(VariousInformRow.TYPE);
            VariousInformParams variousInformParams = TestDBUtility.getVariousInformRow();
            variousInformParams.setBranchCode("000");
            variousInformParams.setInstitutionCode("0D");
            variousInformParams.setInformDiv("0");
            variousInformParams.setRequestNumber("1");
            variousInformParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            variousInformParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070607","yyyyMMdd"));
            TestDBUtility.insertWithDel(variousInformParams);            
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public class WEB3AdministratorForTest extends WEB3Administrator
    {

        public WEB3AdministratorForTest(AdministratorParams l_administratorParams)
        {
            super(l_administratorParams);
        }
        
        public void validateAuthority(String l_strTransactionCategory,boolean l_isUpdate)
        throws WEB3BaseException
        {
                
        }
        
        public void validateTradingPassword(String l_strPassword)
        throws WEB3BaseException
        {
                
        }
        
        public String getInstitutionCode()
        {
            return "12";
        }
        
        public void validateBranchPermission(String l_strBranchCode)
        throws WEB3BaseException
        {
                
        }
        
        public String getAdministratorCode()
        {
            return "12";
        }
        
        public String getBranchCode()
        {
            return "381";
        }
    }
    
    private class WEB3AdminInformAccSwElecDeliApplyInpRequestForTest extends WEB3AdminInformAccSwElecDeliApplyInpRequest
    {           
        public void validate() throws WEB3BaseException
        {
            
        }
    }
    
    private class WEB3AdminInformAccSwElecDeliApplyConfRequestForTest extends WEB3AdminInformAccSwElecDeliApplyConfRequest
    {
        public void validate() throws WEB3BaseException
        {
            
        }
    }
    
    private class WEB3AdminInformAccSwElecDeliApplyCmpRequestForTest extends WEB3AdminInformAccSwElecDeliApplyCmpRequest
    {
        public void validate() throws WEB3BaseException
        {
            
        }
    }
    
    private class WEB3AdminInformAccSwElecDeliApplyRefRequestForTest extends WEB3AdminInformAccSwElecDeliApplyRefRequest
    {
        public void validate() throws WEB3BaseException
        {
            
        }
    }
    
    private class WEB3AdminInformAccSwElecDeliChangeConfRequestForTest extends WEB3AdminInformAccSwElecDeliChangeConfRequest
    {
        public void validate() throws WEB3BaseException
        {
            
        }
    }
    
    private class WEB3AdminInformSwElecDeliApplyServiceImplForExcute extends WEB3AdminInformSwElecDeliApplyServiceImpl
    {
        protected WEB3AdminInformAccSwElecDeliApplySrcResponse getSearchScreen(
            WEB3AdminInformAccSwElecDeliApplySrcRequest l_request) throws WEB3BaseException
        {
            return new WEB3AdminInformAccSwElecDeliApplySrcResponse();
        }
        
        protected WEB3AdminInformAccSwElecDeliApplyInpResponse getInputScreen(
            WEB3AdminInformAccSwElecDeliApplyInpRequest l_request) throws WEB3BaseException
        {
            return new WEB3AdminInformAccSwElecDeliApplyInpResponse();
        }
        
        protected WEB3AdminInformAccSwElecDeliApplyConfResponse validateApply(
            WEB3AdminInformAccSwElecDeliApplyConfRequest l_request) throws WEB3BaseException
        {
            return new WEB3AdminInformAccSwElecDeliApplyConfResponse();
        }
        
        protected WEB3AdminInformAccSwElecDeliApplyCmpResponse submitApply(
            WEB3AdminInformAccSwElecDeliApplyCmpRequest l_request) throws WEB3BaseException
        {
            return new WEB3AdminInformAccSwElecDeliApplyCmpResponse();
        }
        protected WEB3AdminInformAccSwElecDeliApplyRefResponse getReferenceScreen(
            WEB3AdminInformAccSwElecDeliApplyRefRequest l_request) throws WEB3BaseException
        {
            return new WEB3AdminInformAccSwElecDeliApplyRefResponse();
        }
        protected WEB3AdminInformAccSwElecDeliChangeConfResponse validateChange(
            WEB3AdminInformAccSwElecDeliChangeConfRequest l_request) throws WEB3BaseException
        {
            return new WEB3AdminInformAccSwElecDeliChangeConfResponse();
        }
        
        protected WEB3AdminInformAccSwElecDeliChangeCmpResponse submitChange(
            WEB3AdminInformAccSwElecDeliChangeCmpRequest l_request) throws WEB3BaseException
        {
            return new WEB3AdminInformAccSwElecDeliChangeCmpResponse();
        }
        protected WEB3AdminInformAccSwElecDeliDeleteConfResponse validateCancel(
            WEB3AdminInformAccSwElecDeliDeleteConfRequest l_request)
            throws WEB3BaseException
        {
            return new WEB3AdminInformAccSwElecDeliDeleteConfResponse();
        }
            
        protected WEB3AdminInformAccSwElecDeliDeleteCmpResponse submitCancel(
            WEB3AdminInformAccSwElecDeliDeleteCmpRequest l_request)
            throws WEB3BaseException
        {
            return new WEB3AdminInformAccSwElecDeliDeleteCmpResponse();
        }
    }
}
@
