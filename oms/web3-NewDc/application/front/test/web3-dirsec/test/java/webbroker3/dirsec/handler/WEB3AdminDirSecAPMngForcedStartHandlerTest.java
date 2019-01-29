head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.33.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminDirSecAPMngForcedStartHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3AdminDirSecAPMngForcedStartHandlerTest.java
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/07/23 楊夫志(中訊) 新規作成 モデル 132
*/
package webbroker3.dirsec.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngForcedStartCmpRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngForcedStartCmpResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngForcedStartCnfRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngForcedStartCnfResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngForcedStartInpRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngForcedStartInpResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngForcedStartSortKey;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngListRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngListResponse;
import webbroker3.dirsec.service.delegate.WEB3AdminDirSecAPMngForcedStartService;
import webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecAPMngForcedStartServiceImpl;
import webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecAioOrderUnitTableUpdateServiceImplTest.LoginInfoImplTest;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminDirSecAPMngForcedStartHandlerTest extends TestBaseForMock
{

    WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3AdminDirSecAPMngForcedStartHandlerTest.class);

    WEB3AdminDirSecAPMngForcedStartHandler l_handler = null;
    
    public WEB3AdminDirSecAPMngForcedStartHandlerTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        l_handler = new WEB3AdminDirSecAPMngForcedStartHandler();
        MOCK_MANAGER.setIsMockUsed(true);
        LoginInfoImplTest l_loginInfoImpl = new LoginInfoImplTest();
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getLoginInfo",
            new Class[] {},
            l_loginInfoImpl);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
            "getLoginId",
            new Class[] {},
            new Long(1001));
        TestDBUtility.deleteAll(AdministratorParams.TYPE);
        AdministratorParams l_administratorParams =
            TestDBUtility.getAdministratorRow();
        TestDBUtility.insertWithDel(l_administratorParams);

        WEB3Administrator l_administrator =
            new WEB3Administrator(l_administratorParams);
        WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    //管理者下り処理一覧画面表示処理に失敗しました。
    //WEB3BaseException
    public void testGetAPMngList_C0001()
    {
        final String STR_METHOD_NAME = "testGetAPMngList_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {       
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecAPMngForcedStartServiceImpl",
                    "execute",
                    new Class[] {WEB3GenRequest.class},
                    new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, "", STR_METHOD_NAME));
            
            WEB3AdminDirSecAPMngListRequest l_request = new WEB3AdminDirSecAPMngListRequest();
            l_request.pageIndex = "1111";
            l_request.pageSize = "1111";
            l_request.sortKeys = new WEB3AdminDirSecAPMngForcedStartSortKey[]
               {new WEB3AdminDirSecAPMngForcedStartSortKey()};
            l_request.sortKeys[0].keyItem = "1111";
            l_request.sortKeys[0].ascDesc = WEB3AscDescDef.ASC;
            WEB3AdminDirSecAPMngListResponse l_response = new WEB3AdminDirSecAPMngListResponse();
            l_response = l_handler.getAPMngList(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //管理者下り処理一覧画面表示処理に失敗しました。
    //WEB3BaseRuntimeException
    public void testGetAPMngList_C0002()
    {
        final String STR_METHOD_NAME = "testGetAPMngList_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {       
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecAPMngForcedStartServiceImpl",
                    "execute",
                    new Class[] {WEB3GenRequest.class},
                    new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, ""));
            
            WEB3AdminDirSecAPMngListRequest l_request = new WEB3AdminDirSecAPMngListRequest();
            l_request.pageIndex = "1111";
            l_request.pageSize = "1111";
            l_request.sortKeys = new WEB3AdminDirSecAPMngForcedStartSortKey[]
               {new WEB3AdminDirSecAPMngForcedStartSortKey()};
            l_request.sortKeys[0].keyItem = "1111";
            l_request.sortKeys[0].ascDesc = WEB3AscDescDef.ASC;
            WEB3AdminDirSecAPMngListResponse l_response = new WEB3AdminDirSecAPMngListResponse();
            l_response = l_handler.getAPMngList(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //normalcase
    public void testGetAPMngList_C0003()
    {
        final String STR_METHOD_NAME = "testGetAPMngList_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminDirSecAPMngListResponse  genResponse = new WEB3AdminDirSecAPMngListResponse();
            genResponse.pageIndex = "1111";
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecAPMngForcedStartServiceImpl",
                    "execute",
                    new Class[] {WEB3GenRequest.class},
                    genResponse);
            
            WEB3AdminDirSecAPMngListRequest l_request = new WEB3AdminDirSecAPMngListRequest();
            l_request.pageIndex = "1111";
            l_request.pageSize = "1111";
            l_request.sortKeys = new WEB3AdminDirSecAPMngForcedStartSortKey[]
               {new WEB3AdminDirSecAPMngForcedStartSortKey()};
            l_request.sortKeys[0].keyItem = "1111";
            l_request.sortKeys[0].ascDesc = WEB3AscDescDef.ASC;
            WEB3AdminDirSecAPMngListResponse l_response = new WEB3AdminDirSecAPMngListResponse();
            l_response = l_handler.getAPMngList(l_request);
            assertEquals("1111", l_response.pageIndex);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //下り処理強制起動入力画面表示に失敗しました。
    //WEB3BaseException
    public void testGetAPMngForcedStartInp_C0001()
    {
        final String STR_METHOD_NAME = "testGetAPMngForcedStartInp_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {       
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecAPMngForcedStartServiceImpl",
                    "execute",
                    new Class[] {WEB3GenRequest.class},
                    new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, "", STR_METHOD_NAME));
            
            WEB3AdminDirSecAPMngForcedStartInpRequest l_request = new WEB3AdminDirSecAPMngForcedStartInpRequest();
            l_request.pType = "1111";
            WEB3AdminDirSecAPMngForcedStartInpResponse l_response = new WEB3AdminDirSecAPMngForcedStartInpResponse();
            l_response = l_handler.getAPMngForcedStartInp(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //下り処理強制起動入力画面表示に失敗しました。
    //WEB3BaseRuntimeException
    public void testGetAPMngForcedStartInp_C0002()
    {
        final String STR_METHOD_NAME = "testGetAPMngForcedStartInp_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {       
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecAPMngForcedStartServiceImpl",
                    "execute",
                    new Class[] {WEB3GenRequest.class},
                    new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, "", STR_METHOD_NAME));
            
            WEB3AdminDirSecAPMngForcedStartInpRequest l_request = new WEB3AdminDirSecAPMngForcedStartInpRequest();
            l_request.pType = "1111";
            WEB3AdminDirSecAPMngForcedStartInpResponse l_response = new WEB3AdminDirSecAPMngForcedStartInpResponse();
            l_response = l_handler.getAPMngForcedStartInp(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //normalcase
    public void testGetAPMngForcedStartInp_C0003()
    {
        final String STR_METHOD_NAME = "testGetAPMngForcedStartInp_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {   
            WEB3AdminDirSecAPMngForcedStartInpResponse inpResponse = new WEB3AdminDirSecAPMngForcedStartInpResponse();
            inpResponse.errorMessage = "1111";
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecAPMngForcedStartServiceImpl",
                    "execute",
                    new Class[] {WEB3GenRequest.class},
                    inpResponse);
            
            WEB3AdminDirSecAPMngForcedStartInpRequest l_request = new WEB3AdminDirSecAPMngForcedStartInpRequest();
            l_request.pType = "1111";
            WEB3AdminDirSecAPMngForcedStartInpResponse l_response = new WEB3AdminDirSecAPMngForcedStartInpResponse();
            l_response = l_handler.getAPMngForcedStartInp(l_request);
            assertEquals("1111", l_response.errorMessage);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //下り処理強制起動確認画面表示に失敗しました。
    //WEB3BaseException
    public void testValidateAPMngForcedStartCnf_C0001()
    {
        final String STR_METHOD_NAME = "testValidateAPMngForcedStartCnf_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {       
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecAPMngForcedStartServiceImpl",
                    "execute",
                    new Class[] {WEB3GenRequest.class},
                    new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, "", STR_METHOD_NAME));
            
            WEB3AdminDirSecAPMngForcedStartCnfRequest l_request = new WEB3AdminDirSecAPMngForcedStartCnfRequest();
            l_request.pType = "1111";
            WEB3AdminDirSecAPMngForcedStartCnfResponse l_response = new WEB3AdminDirSecAPMngForcedStartCnfResponse();
            l_response = l_handler.validateAPMngForcedStartCnf(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //下り処理強制起動確認画面表示に失敗しました。
    //WEB3BaseRuntimeException
    public void testValidateAPMngForcedStartCnf_C0002()
    {
        final String STR_METHOD_NAME = "testValidateAPMngForcedStartCnf_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {       
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecAPMngForcedStartServiceImpl",
                    "execute",
                    new Class[] {WEB3GenRequest.class},
                    new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, "", STR_METHOD_NAME));
            
            WEB3AdminDirSecAPMngForcedStartCnfRequest l_request = new WEB3AdminDirSecAPMngForcedStartCnfRequest();
            l_request.pType = "1111";
            WEB3AdminDirSecAPMngForcedStartCnfResponse l_response = new WEB3AdminDirSecAPMngForcedStartCnfResponse();
            l_response = l_handler.validateAPMngForcedStartCnf(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //normalcase
    public void testValidateAPMngForcedStartCnf_C0003()
    {
        final String STR_METHOD_NAME = "testValidateAPMngForcedStartCnf_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AdminDirSecAPMngForcedStartCnfResponse cnfResponse = new WEB3AdminDirSecAPMngForcedStartCnfResponse();
            cnfResponse.errorMessage = "1111";
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecAPMngForcedStartServiceImpl",
                    "execute",
                    new Class[] {WEB3GenRequest.class},
                    cnfResponse);
            
            WEB3AdminDirSecAPMngForcedStartCnfRequest l_request = new WEB3AdminDirSecAPMngForcedStartCnfRequest();
            l_request.pType = "1111";
            WEB3AdminDirSecAPMngForcedStartCnfResponse l_response = new WEB3AdminDirSecAPMngForcedStartCnfResponse();
            l_response = l_handler.validateAPMngForcedStartCnf(l_request);
            assertEquals("1111", l_response.errorMessage);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //管理者下り処理強制起動完了画面表示処理に失敗しました。
    //WEB3BaseException
    public void testSubmitAPMngForcedStartCmp_C0001()
    {
        final String STR_METHOD_NAME = "testSubmitAPMngForcedStartCmp_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {       
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecAPMngForcedStartServiceImpl",
                    "execute",
                    new Class[] {WEB3GenRequest.class},
                    new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, "", STR_METHOD_NAME));
            
            WEB3AdminDirSecAPMngForcedStartCmpRequest l_request = new WEB3AdminDirSecAPMngForcedStartCmpRequest();
            l_request.pType = "1111";
            WEB3AdminDirSecAPMngForcedStartCmpResponse l_response = new WEB3AdminDirSecAPMngForcedStartCmpResponse();
            l_response = l_handler.submitAPMngForcedStartCmp(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //管理者下り処理強制起動完了画面表示処理に失敗しました。
    //WEB3BaseRuntimeException
    public void testSubmitAPMngForcedStartCmp_C0002()
    {
        final String STR_METHOD_NAME = "testSubmitAPMngForcedStartCmp_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {       
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecAPMngForcedStartServiceImpl",
                    "execute",
                    new Class[] {WEB3GenRequest.class},
                    new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, "", STR_METHOD_NAME));
            
            WEB3AdminDirSecAPMngForcedStartCmpRequest l_request = new WEB3AdminDirSecAPMngForcedStartCmpRequest();
            l_request.pType = "1111";
            WEB3AdminDirSecAPMngForcedStartCmpResponse l_response = new WEB3AdminDirSecAPMngForcedStartCmpResponse();
            l_response = l_handler.submitAPMngForcedStartCmp(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //normalcase
    public void testSubmitAPMngForcedStartCmp_C0003()
    {
        final String STR_METHOD_NAME = "testSubmitAPMngForcedStartCmp_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {   
            WEB3AdminDirSecAPMngForcedStartCmpResponse cmpResponse = new WEB3AdminDirSecAPMngForcedStartCmpResponse();
            cmpResponse.errorMessage = "1111";
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecAPMngForcedStartServiceImpl",
                    "execute",
                    new Class[] {WEB3GenRequest.class},
                    cmpResponse);
            
            WEB3AdminDirSecAPMngForcedStartCmpRequest l_request = new WEB3AdminDirSecAPMngForcedStartCmpRequest();
            l_request.pType = "1111";
            WEB3AdminDirSecAPMngForcedStartCmpResponse l_response = new WEB3AdminDirSecAPMngForcedStartCmpResponse();
            l_response = l_handler.submitAPMngForcedStartCmp(l_request);
            assertEquals("1111", l_response.errorMessage);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //管理者下り処理強制起動サービスImplを取得に失敗しました。
    public void testGetAPMngList_C0004()
    {
        final String STR_METHOD_NAME = "testGetAPMngList_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            Services.unregisterService(WEB3AdminDirSecAPMngForcedStartService.class);
            
            WEB3AdminDirSecAPMngListRequest l_request = new WEB3AdminDirSecAPMngListRequest();
            WEB3AdminDirSecAPMngListResponse l_response = new WEB3AdminDirSecAPMngListResponse();
            l_response = l_handler.getAPMngList(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminDirSecAPMngForcedStartService.class,
                    new WEB3AdminDirSecAPMngForcedStartServiceImpl());
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //管理者下り処理強制起動サービスImplを取得に失敗しました。"
    public void testGetAPMngForcedStartInp_C0004()
    {
        final String STR_METHOD_NAME = "testGetAPMngForcedStartInp_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            Services.unregisterService(WEB3AdminDirSecAPMngForcedStartService.class);
            
            WEB3AdminDirSecAPMngForcedStartInpRequest l_request = new WEB3AdminDirSecAPMngForcedStartInpRequest();
            WEB3AdminDirSecAPMngForcedStartInpResponse l_response = new WEB3AdminDirSecAPMngForcedStartInpResponse();
            l_response = l_handler.getAPMngForcedStartInp(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminDirSecAPMngForcedStartService.class,
                    new WEB3AdminDirSecAPMngForcedStartServiceImpl());
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //管理者下り処理強制起動サービスImplを取得に失敗しました。"
    public void testValidateAPMngForcedStartCnf_C0004()
    {
        final String STR_METHOD_NAME = "testValidateAPMngForcedStartCnf_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            Services.unregisterService(WEB3AdminDirSecAPMngForcedStartService.class);
            
            WEB3AdminDirSecAPMngForcedStartCnfRequest l_request = new WEB3AdminDirSecAPMngForcedStartCnfRequest();
            WEB3AdminDirSecAPMngForcedStartCnfResponse l_response = new WEB3AdminDirSecAPMngForcedStartCnfResponse();
            l_response = l_handler.validateAPMngForcedStartCnf(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminDirSecAPMngForcedStartService.class,
                    new WEB3AdminDirSecAPMngForcedStartServiceImpl());
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //管理者下り処理強制起動サービスImplを取得に失敗しました。"
    public void testSubmitAPMngForcedStartCmp_C0004()
    {
        final String STR_METHOD_NAME = "testSubmitAPMngForcedStartCmp_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            Services.unregisterService(WEB3AdminDirSecAPMngForcedStartService.class);
            
            WEB3AdminDirSecAPMngForcedStartCmpRequest l_request = new WEB3AdminDirSecAPMngForcedStartCmpRequest();
            WEB3AdminDirSecAPMngForcedStartCmpResponse l_response = new WEB3AdminDirSecAPMngForcedStartCmpResponse();
            l_response = l_handler.submitAPMngForcedStartCmp(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminDirSecAPMngForcedStartService.class,
                    new WEB3AdminDirSecAPMngForcedStartServiceImpl());
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    public class LoginInfoImplTest extends LoginInfoImpl
    {
        public long getLoginId()
        {
            // TODO Auto-generated method stub
            return 1001L;
        }
    }

}
@
