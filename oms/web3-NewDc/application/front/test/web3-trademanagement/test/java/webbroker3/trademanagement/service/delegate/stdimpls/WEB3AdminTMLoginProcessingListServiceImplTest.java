head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.49.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminTMLoginProcessingListServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3AdminTMLoginProcessingListServiceImplTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/22 張少傑 (中訊) 新規作成 モデルNo.005
*/
package webbroker3.trademanagement.service.delegate.stdimpls;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import test.util.TestDBUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdminPermissionRow;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.gentrade.data.LoginHistoryParams;
import webbroker3.gentrade.data.LoginHistoryPastParams;
import webbroker3.gentrade.data.LoginHistoryPastRow;
import webbroker3.gentrade.data.LoginHistoryRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.trademanagement.message.WEB3AdminTMExchangeRegistCompleteRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginHistoryInputRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginHistoryInputResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginHistoryListRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginHistoryListResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginHistoryReferenceUnit;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginHistorySortKey;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminTMLoginProcessingListServiceImplTest extends TestBaseForMock
{
    WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTMLoginProcessingListServiceImplTest.class);
    
    WEB3AdminTMLoginProcessingListServiceImpl l_impl = null;
    public WEB3AdminTMLoginProcessingListServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        l_impl = new WEB3AdminTMLoginProcessingListServiceImpl();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    //リクエストデータ = null
    public void testExcute_C001()
    {
        final String STR_METHOD_NAME = "testExcute_C001";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3GenRequest l_request = null;
            WEB3GenResponse l_response = l_impl.execute(l_request);
            fail();
        }
        catch(WEB3SystemLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            fail();
            log.error(l_ex.getMessage());
            log.exiting(STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }
    //正常返回
    public void testExcute_C002()
    {
        final String STR_METHOD_NAME = "testExcute_C002";
        log.entering(STR_METHOD_NAME);
        WEB3AdminTraderAdminLoginHistoryInputRequest l_request =
            new WEB3AdminTraderAdminLoginHistoryInputRequest();
        try
        {
            LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImplForMock);
            
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionRow = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionRow.setInstitutionCode("0D");
            l_adminPermissionRow.setTransactionCategory("C1301");
            l_adminPermissionRow.setPermissionLevel("331");
            TestDBUtility.insertWithDel(l_adminPermissionRow);
            
            WEB3GenResponse l_response = l_impl.execute(l_request);
        }
        catch(Exception l_ex)
        {
            fail();
            log.error(l_ex.getMessage());
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testExcute_C003()
    {
        final String STR_METHOD_NAME = "testExcute_C003";
        log.entering(STR_METHOD_NAME);
        WEB3AdminTraderAdminLoginHistoryListRequest l_request =
            new WEB3AdminTraderAdminLoginHistoryListRequest();
        WEB3AdminTMLoginProcessingListServiceImplForTest l_impTest =
            new WEB3AdminTMLoginProcessingListServiceImplForTest();
        try
        {
            WEB3AdminTraderAdminLoginHistoryListResponse l_response =
                (WEB3AdminTraderAdminLoginHistoryListResponse)l_impTest.execute(l_request);
            assertEquals("2", l_response.pageIndex);
            assertEquals("20", l_response.totalPages);
            assertEquals("100", l_response.totalRecords);
        }
        catch (WEB3BaseException e)
        {
            fail();
        }
    }
    //l_request ！==WEB3AdminTraderAdminLoginHistoryInputRequest かつ
    //WEB3AdminTraderAdminLoginHistoryListRequest
    public void testExcute_C004()
    {
        final String STR_METHOD_NAME = "testExcute_C004()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminTMExchangeRegistCompleteRequest l_request =
            new WEB3AdminTMExchangeRegistCompleteRequest();
        try
        { 
            WEB3GenResponse l_response = l_impl.execute(l_request);
            fail();
        }
        catch(WEB3SystemLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_ex.getErrorInfo()) ;
        }
        catch(Exception l_ex)
        {
            fail();
            log.error(l_ex.getMessage());
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //validate権限方法@抛出異常
    public void testGetSearchInputScreen_Case001()
    {
        final String STR_METHOD_NAME = "testGetSearchInputScreen_Case001";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminTraderAdminLoginHistoryInputRequest l_request =
            new WEB3AdminTraderAdminLoginHistoryInputRequest();
        WEB3AdminTraderAdminLoginHistoryInputResponse l_response =
            new WEB3AdminTraderAdminLoginHistoryInputResponse();
        try
        {
            LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImplForMock);
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            TestDBUtility.insertWithDel(l_administratorParams);
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            l_response = l_impl.getSearchInputScreen(l_request);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex.getErrorInfo());
        }
        catch (WEB3BaseException l_ex)
        {
            fail();
            log.error(l_ex.getErrorMessage());
        }
        log.exiting(STR_METHOD_NAME);
    }
    //正常結束
    public void testGetSearchInputScreen_Case002()
    {
        final String STR_METHOD_NAME = "testGetSearchInputScreen_Case002";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminTraderAdminLoginHistoryInputRequest l_request =
            new WEB3AdminTraderAdminLoginHistoryInputRequest();
        WEB3AdminTraderAdminLoginHistoryInputResponse l_response =
            new WEB3AdminTraderAdminLoginHistoryInputResponse();
        try
        {
            LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImplForMock);
            
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionRow = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionRow.setInstitutionCode("0D");
            l_adminPermissionRow.setTransactionCategory("C1301");
            l_adminPermissionRow.setPermissionLevel("331");
            TestDBUtility.insertWithDel(l_adminPermissionRow);
            l_response = l_impl.getSearchInputScreen(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            fail();
            log.error(l_ex.getErrorMessage());
        }
        log.exiting(STR_METHOD_NAME);
    }
    //validate方法@候抛出異常
    public void testGetSearchResultScreen_Case001()
    {
        final String STR_METHOD_NAME = "testGetSearchResultScreen_Case001";
        log.entering(STR_METHOD_NAME);        
        WEB3AdminTraderAdminLoginHistoryListResponse l_response = null;
        try
        {
            WEB3AdminTraderAdminLoginHistoryListRequest l_request =
                new WEB3AdminTraderAdminLoginHistoryListRequest();
            l_request.searchDate = null;
            l_response = l_impl.getSearchResultScreen(l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01272, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            fail();
            log.error(l_ex.getMessage());
        }
        log.exiting(STR_METHOD_NAME);
    }
    //validate方法@抛出異常
    public void testGetSearchResultScreen_Case002()
    {
        final String STR_METHOD_NAME = "testGetSearchResultScreen_Case002";
        log.entering(STR_METHOD_NAME);        
        WEB3AdminTraderAdminLoginHistoryListResponse l_response = null;
        try
        {
            LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImplForMock);
            
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionRow = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionRow.setInstitutionCode("0D");
            l_adminPermissionRow.setTransactionCategory("C13");
            l_adminPermissionRow.setPermissionLevel("331");
            TestDBUtility.insertWithDel(l_adminPermissionRow);
            
            WEB3AdminTraderAdminLoginHistoryListRequest l_request =
                new WEB3AdminTraderAdminLoginHistoryListRequest();
            WEB3AdminTraderAdminLoginHistorySortKey[] sortkey =
                new WEB3AdminTraderAdminLoginHistorySortKey[1];
            sortkey[0] = new WEB3AdminTraderAdminLoginHistorySortKey();
            sortkey[0].keyItem = "ipAddress";
            sortkey[0].ascDesc = "A";
            l_request.searchDate = "20080206";
            l_request.accountCode = "102050";
            l_request.branchCode  = "102";
            l_request.startTime = "0900";
            l_request.endTime = "1000";
            l_request.ipAddress = "192.168.255.21";
            l_request.sortKeys = sortkey;
            l_request.pageIndex = "1";
            l_request.pageSize = "5";
            l_response = l_impl.getSearchResultScreen(l_request);
            fail();
        }
        catch(WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            fail();
            log.error(l_ex.getMessage());
        }
        log.exiting(STR_METHOD_NAME);
    }
    //正常結束
    //檢索login_history表
    public void testGetSearchResultScreen_Case003()
    {
        final String STR_METHOD_NAME = "testGetSearchResultScreen_Case003";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            Date l_date = WEB3DateUtility.getDate("20080924123000", "yyyyMMddHHmmss");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(l_date.getTime()));
            
            WEB3AdminTraderAdminLoginHistorySortKey[] l_sortKeys =
                new WEB3AdminTraderAdminLoginHistorySortKey[3];
            l_sortKeys[0] = new WEB3AdminTraderAdminLoginHistorySortKey();
            l_sortKeys[0].keyItem = "loginDate";
            l_sortKeys[0].ascDesc = "A";
            
            l_sortKeys[1] = new WEB3AdminTraderAdminLoginHistorySortKey();
            l_sortKeys[1].keyItem = "ipAddress";
            l_sortKeys[1].ascDesc = "D";
            
            l_sortKeys[2] = new WEB3AdminTraderAdminLoginHistorySortKey();
            l_sortKeys[2].keyItem = "accountCode";
            l_sortKeys[2].ascDesc = "A";
            
            WEB3AdminTraderAdminLoginHistoryListRequest l_request =
                new WEB3AdminTraderAdminLoginHistoryListRequest();
            l_request.accountCode = "102050";
            l_request.branchCode = "102";
            l_request.ipAddress  = "192.168.255.21";
            l_request.searchDate = "20080924";
            l_request.startTime = "0900";
            l_request.endTime = "1000";
            l_request.sortKeys = l_sortKeys;
            l_request.pageIndex = "1";
            l_request.pageSize = "2";
            WEB3AdminTraderAdminLoginHistoryListResponse l_response =
                new WEB3AdminTraderAdminLoginHistoryListResponse();
            
            TestDBUtility.deleteAll(LoginHistoryPastRow.TYPE);
            TestDBUtility.deleteAll(LoginHistoryRow.TYPE);
            LoginHistoryParams l_loginHistoryParams = TestDBUtility.getLoginHistoryRow();
            l_loginHistoryParams.setLoginTimestamp(WEB3DateUtility.getDate("20080924093000","yyyyMMddHHmmss"));
            l_loginHistoryParams.setAccountCode("1020501");
            TestDBUtility.insertWithDel(l_loginHistoryParams);
            
            LoginHistoryParams l_loginHistoryParams1 = TestDBUtility.getLoginHistoryRow();
            l_loginHistoryParams1.setLoginTimestamp(WEB3DateUtility.getDate("20080924093000","yyyyMMddHHmmss"));
            l_loginHistoryParams1.setLoginHistoryId(1002);
            l_loginHistoryParams1.setAccountCode("1020502");
            l_loginHistoryParams1.setLoginFailure("0");
            l_loginHistoryParams1.setOrderRootDiv("1");
            TestDBUtility.insertWithDel(l_loginHistoryParams1);
            
            LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImplForMock);
            
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionRow = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionRow.setInstitutionCode("0D");
            l_adminPermissionRow.setTransactionCategory("C1301");
            l_adminPermissionRow.setPermissionLevel("331");
            TestDBUtility.insertWithDel(l_adminPermissionRow);
            
            l_response = l_impl.getSearchResultScreen(l_request);

            assertEquals("1", l_response.pageIndex);
            assertEquals("1", l_response.totalPages);
            assertEquals("2", l_response.totalRecords);
            
            assertEquals(2, l_response.loginHistoryList.length);
            assertEquals("1020501", l_response.loginHistoryList[0].accountCode);
            assertEquals("192.168.255.21", l_response.loginHistoryList[0].ipAddress);
            assertEquals("102", l_response.loginHistoryList[0].branchCode);
            assertEquals("2", l_response.loginHistoryList[0].orderRootDiv); 
            
            assertEquals("1020502", l_response.loginHistoryList[1].accountCode);
            assertEquals("192.168.255.21", l_response.loginHistoryList[1].ipAddress);
            assertEquals("102", l_response.loginHistoryList[1].branchCode);
            assertEquals("1", l_response.loginHistoryList[1].orderRootDiv); 
        }
        catch (WEB3BaseException l_ex)
        {
            fail();
            log.error(l_ex.getErrorMessage());
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //正常結束
    //檢索login_history_Past表
    public void testGetSearchResultScreen_Case004()
    {
        final String STR_METHOD_NAME = "testGetSearchResultScreen_Case004";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            Date l_date = WEB3DateUtility.getDate("20080925133000", "yyyyMMddHHmmss");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(l_date.getTime()));
            
            WEB3AdminTraderAdminLoginHistorySortKey[] l_sortKeys =
                new WEB3AdminTraderAdminLoginHistorySortKey[3];
            l_sortKeys[0] = new WEB3AdminTraderAdminLoginHistorySortKey();
            l_sortKeys[0].keyItem = "loginDate";
            l_sortKeys[0].ascDesc = "A";
            
            l_sortKeys[1] = new WEB3AdminTraderAdminLoginHistorySortKey();
            l_sortKeys[1].keyItem = "ipAddress";
            l_sortKeys[1].ascDesc = "D";
            
            l_sortKeys[2] = new WEB3AdminTraderAdminLoginHistorySortKey();
            l_sortKeys[2].keyItem = "accountCode";
            l_sortKeys[2].ascDesc = "A";
            
            WEB3AdminTraderAdminLoginHistoryListRequest l_request =
                new WEB3AdminTraderAdminLoginHistoryListRequest();
            l_request.accountCode = "102050";
            l_request.branchCode = "102";
            l_request.ipAddress  = "192.168.255.21";
            l_request.searchDate = "20080924";
            l_request.startTime = "0900";
            l_request.endTime = "1000";
            l_request.sortKeys = l_sortKeys;
            l_request.pageIndex = "1";
            l_request.pageSize = "2";
            WEB3AdminTraderAdminLoginHistoryListResponse l_response =
                new WEB3AdminTraderAdminLoginHistoryListResponse();
            
            TestDBUtility.deleteAll(LoginHistoryRow.TYPE);
            TestDBUtility.deleteAll(LoginHistoryPastRow.TYPE);
            LoginHistoryPastParams l_loginHistoryPastParams = TestDBUtility.getLoginHistoryPastRow();
            l_loginHistoryPastParams.setLoginTimestamp(WEB3DateUtility.getDate("20080924093000","yyyyMMddHHmmss"));
            l_loginHistoryPastParams.setAccountCode("1020501");
            TestDBUtility.insertWithDel(l_loginHistoryPastParams);
            
            LoginHistoryPastParams l_loginHistoryPastParams1 = TestDBUtility.getLoginHistoryPastRow();
            l_loginHistoryPastParams1.setLoginTimestamp(WEB3DateUtility.getDate("20080924093000","yyyyMMddHHmmss"));
            l_loginHistoryPastParams1.setLoginHistoryId(1002);
            l_loginHistoryPastParams1.setAccountCode("1020502");
            l_loginHistoryPastParams1.setLoginFailure("0");
            l_loginHistoryPastParams1.setOrderRootDiv("1");
            TestDBUtility.insertWithDel(l_loginHistoryPastParams1);
            
            LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImplForMock);
            
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionRow = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionRow.setInstitutionCode("0D");
            l_adminPermissionRow.setTransactionCategory("C1301");
            l_adminPermissionRow.setPermissionLevel("331");
            TestDBUtility.insertWithDel(l_adminPermissionRow);
            
            l_response = l_impl.getSearchResultScreen(l_request);

            assertEquals("1", l_response.pageIndex);
            assertEquals("1", l_response.totalPages);
            assertEquals("2", l_response.totalRecords);
            
            assertEquals(2, l_response.loginHistoryList.length);
            assertEquals("1020501", l_response.loginHistoryList[0].accountCode);
            assertEquals("192.168.255.21", l_response.loginHistoryList[0].ipAddress);
            assertEquals("102", l_response.loginHistoryList[0].branchCode);
            assertEquals("2", l_response.loginHistoryList[0].orderRootDiv); 
            
            assertEquals("1020502", l_response.loginHistoryList[1].accountCode);
            assertEquals("192.168.255.21", l_response.loginHistoryList[1].ipAddress);
            assertEquals("102", l_response.loginHistoryList[1].branchCode);
            assertEquals("1", l_response.loginHistoryList[1].orderRootDiv); 
        }
        catch (WEB3BaseException l_ex)
        {
            fail();
            log.error(l_ex.getErrorMessage());
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //正常結束
    //檢索login_history表
    public void testGetSearchResultScreen_Case005()
    {
        final String STR_METHOD_NAME = "testGetSearchResultScreen_Case005";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            Date l_date = WEB3DateUtility.getDate("20080924123000", "yyyyMMddHHmmss");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(l_date.getTime()));
            
            WEB3AdminTraderAdminLoginHistorySortKey[] l_sortKeys =
                new WEB3AdminTraderAdminLoginHistorySortKey[1];
            l_sortKeys[0] = new WEB3AdminTraderAdminLoginHistorySortKey();
            l_sortKeys[0].keyItem = "ipAddress";
            l_sortKeys[0].ascDesc = "A";
            
            WEB3AdminTraderAdminLoginHistoryListRequest l_request =
                new WEB3AdminTraderAdminLoginHistoryListRequest();
            l_request.accountCode = "102050";
            l_request.branchCode = null;
            l_request.ipAddress  = null;
            l_request.searchDate = "20080924";
            l_request.startTime = "0900";
            l_request.endTime = "1000";
            l_request.sortKeys = l_sortKeys;
            l_request.pageIndex = "1";
            l_request.pageSize = "4";
            WEB3AdminTraderAdminLoginHistoryListResponse l_response =
                new WEB3AdminTraderAdminLoginHistoryListResponse();
            
            TestDBUtility.deleteAll(LoginHistoryPastRow.TYPE);
            TestDBUtility.deleteAll(LoginHistoryRow.TYPE);
            LoginHistoryParams l_loginHistoryParams = TestDBUtility.getLoginHistoryRow();
            l_loginHistoryParams.setLoginTimestamp(WEB3DateUtility.getDate("20080924093000","yyyyMMddHHmmss"));
            l_loginHistoryParams.setAccountCode("1020501");
            l_loginHistoryParams.setIpAddress("10.168.255.10");
            TestDBUtility.insertWithDel(l_loginHistoryParams);
            
            LoginHistoryParams l_loginHistoryParams1 = TestDBUtility.getLoginHistoryRow();
            l_loginHistoryParams1.setLoginTimestamp(WEB3DateUtility.getDate("20080924093000","yyyyMMddHHmmss"));
            l_loginHistoryParams1.setLoginHistoryId(1002);
            l_loginHistoryParams1.setAccountCode("1020501");
            l_loginHistoryParams1.setLoginFailure("0");
            l_loginHistoryParams1.setOrderRootDiv("1");
            l_loginHistoryParams1.setIpAddress("10.168.255.9");
            TestDBUtility.insertWithDel(l_loginHistoryParams1);
            
            LoginHistoryParams l_loginHistoryParams2 = TestDBUtility.getLoginHistoryRow();
            l_loginHistoryParams2.setLoginTimestamp(WEB3DateUtility.getDate("20080924093000","yyyyMMddHHmmss"));
            l_loginHistoryParams2.setLoginHistoryId(1003);
            l_loginHistoryParams2.setAccountCode("1020502");
            l_loginHistoryParams2.setLoginFailure("0");
            l_loginHistoryParams2.setOrderRootDiv("1");
            l_loginHistoryParams2.setIpAddress("10.168.255.11");
            TestDBUtility.insertWithDel(l_loginHistoryParams2);
            
            LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImplForMock);
            
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionRow = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionRow.setInstitutionCode("0D");
            l_adminPermissionRow.setTransactionCategory("C1301");
            l_adminPermissionRow.setPermissionLevel("331");
            TestDBUtility.insertWithDel(l_adminPermissionRow);
            
            l_response = l_impl.getSearchResultScreen(l_request);

            assertEquals("1", l_response.pageIndex);
            assertEquals("1", l_response.totalPages);
            assertEquals("3", l_response.totalRecords);
            
            assertEquals(3, l_response.loginHistoryList.length);
            assertEquals("1020501", l_response.loginHistoryList[0].accountCode);
            assertEquals("10.168.255.9", l_response.loginHistoryList[0].ipAddress);
            assertEquals("102", l_response.loginHistoryList[0].branchCode);
            assertEquals("1", l_response.loginHistoryList[0].orderRootDiv); 
            
            assertEquals("1020501", l_response.loginHistoryList[1].accountCode);
            assertEquals("10.168.255.10", l_response.loginHistoryList[1].ipAddress);
            assertEquals("102", l_response.loginHistoryList[1].branchCode);
            assertEquals("2", l_response.loginHistoryList[1].orderRootDiv); 
            
            assertEquals("1020502", l_response.loginHistoryList[2].accountCode);
            assertEquals("10.168.255.11", l_response.loginHistoryList[2].ipAddress);
            assertEquals("102", l_response.loginHistoryList[2].branchCode);
            assertEquals("1", l_response.loginHistoryList[2].orderRootDiv); 
        }
        catch (WEB3BaseException l_ex)
        {
            fail();
            log.error(l_ex.getErrorMessage());
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //正常結束
    //檢索login_history表
    public void testGetSearchResultScreen_Case006()
    {
        final String STR_METHOD_NAME = "testGetSearchResultScreen_Case006";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            Date l_date = WEB3DateUtility.getDate("20080924123000", "yyyyMMddHHmmss");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(l_date.getTime()));
            
            WEB3AdminTraderAdminLoginHistorySortKey[] l_sortKeys =
                new WEB3AdminTraderAdminLoginHistorySortKey[1];
            l_sortKeys[0] = new WEB3AdminTraderAdminLoginHistorySortKey();
            l_sortKeys[0].keyItem = "ipAddress";
            l_sortKeys[0].ascDesc = "A";
            
            WEB3AdminTraderAdminLoginHistoryListRequest l_request =
                new WEB3AdminTraderAdminLoginHistoryListRequest();
            l_request.accountCode = "102050";
            l_request.branchCode = null;
            l_request.ipAddress  = null;
            l_request.searchDate = "20080924";
            l_request.startTime = "0900";
            l_request.endTime = "1000";
            l_request.sortKeys = l_sortKeys;
            l_request.pageIndex = "1";
            l_request.pageSize = "4";
            WEB3AdminTraderAdminLoginHistoryListResponse l_response =
                new WEB3AdminTraderAdminLoginHistoryListResponse();
            
            TestDBUtility.deleteAll(LoginHistoryPastRow.TYPE);
            TestDBUtility.deleteAll(LoginHistoryRow.TYPE);
            LoginHistoryParams l_loginHistoryParams = TestDBUtility.getLoginHistoryRow();
            l_loginHistoryParams.setLoginTimestamp(WEB3DateUtility.getDate("20080924093000","yyyyMMddHHmmss"));
            l_loginHistoryParams.setAccountCode("1020501");
            l_loginHistoryParams.setIpAddress("10.1.255.10");
            TestDBUtility.insertWithDel(l_loginHistoryParams);
            
            LoginHistoryParams l_loginHistoryParams1 = TestDBUtility.getLoginHistoryRow();
            l_loginHistoryParams1.setLoginTimestamp(WEB3DateUtility.getDate("20080924093000","yyyyMMddHHmmss"));
            l_loginHistoryParams1.setLoginHistoryId(1002);
            l_loginHistoryParams1.setAccountCode("1020501");
            l_loginHistoryParams1.setLoginFailure("0");
            l_loginHistoryParams1.setOrderRootDiv("1");
            l_loginHistoryParams1.setIpAddress("10.1.255.9");
            TestDBUtility.insertWithDel(l_loginHistoryParams1);
            
            LoginHistoryParams l_loginHistoryParams2 = TestDBUtility.getLoginHistoryRow();
            l_loginHistoryParams2.setLoginTimestamp(WEB3DateUtility.getDate("20080924093000","yyyyMMddHHmmss"));
            l_loginHistoryParams2.setLoginHistoryId(1003);
            l_loginHistoryParams2.setAccountCode("1020502");
            l_loginHistoryParams2.setLoginFailure("0");
            l_loginHistoryParams2.setOrderRootDiv("1");
            l_loginHistoryParams2.setIpAddress("10.1.255.11");
            TestDBUtility.insertWithDel(l_loginHistoryParams2);
            
            LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImplForMock);
            
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionRow = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionRow.setInstitutionCode("0D");
            l_adminPermissionRow.setTransactionCategory("C1301");
            l_adminPermissionRow.setPermissionLevel("331");
            TestDBUtility.insertWithDel(l_adminPermissionRow);
            
            l_response = l_impl.getSearchResultScreen(l_request);

            assertEquals("1", l_response.pageIndex);
            assertEquals("1", l_response.totalPages);
            assertEquals("3", l_response.totalRecords);
            
            assertEquals(3, l_response.loginHistoryList.length);
            assertEquals("1020501", l_response.loginHistoryList[0].accountCode);
            assertEquals("10.1.255.9", l_response.loginHistoryList[0].ipAddress);
            assertEquals("102", l_response.loginHistoryList[0].branchCode);
            assertEquals("1", l_response.loginHistoryList[0].orderRootDiv); 
            
            assertEquals("1020501", l_response.loginHistoryList[1].accountCode);
            assertEquals("10.1.255.10", l_response.loginHistoryList[1].ipAddress);
            assertEquals("102", l_response.loginHistoryList[1].branchCode);
            assertEquals("2", l_response.loginHistoryList[1].orderRootDiv); 
            
            assertEquals("1020502", l_response.loginHistoryList[2].accountCode);
            assertEquals("10.1.255.11", l_response.loginHistoryList[2].ipAddress);
            assertEquals("102", l_response.loginHistoryList[2].branchCode);
            assertEquals("1", l_response.loginHistoryList[2].orderRootDiv); 
        }
        catch (WEB3BaseException l_ex)
        {
            fail();
            log.error(l_ex.getErrorMessage());
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //正常結束
    //檢索login_history表
    public void testGetSearchResultScreen_Case007()
    {
        final String STR_METHOD_NAME = "testGetSearchResultScreen_Case007";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            Date l_date = WEB3DateUtility.getDate("20080924123000", "yyyyMMddHHmmss");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(l_date.getTime()));
            
            WEB3AdminTraderAdminLoginHistorySortKey[] l_sortKeys =
                new WEB3AdminTraderAdminLoginHistorySortKey[1];
            l_sortKeys[0] = new WEB3AdminTraderAdminLoginHistorySortKey();
            l_sortKeys[0].keyItem = "ipAddress";
            l_sortKeys[0].ascDesc = "A";
            
            WEB3AdminTraderAdminLoginHistoryListRequest l_request =
                new WEB3AdminTraderAdminLoginHistoryListRequest();
            l_request.accountCode = "102050";
            l_request.branchCode = null;
            l_request.ipAddress  = null;
            l_request.searchDate = "20080924";
            l_request.startTime = "0900";
            l_request.endTime = "1000";
            l_request.sortKeys = l_sortKeys;
            l_request.pageIndex = "1";
            l_request.pageSize = "4";
            WEB3AdminTraderAdminLoginHistoryListResponse l_response =
                new WEB3AdminTraderAdminLoginHistoryListResponse();
            
            TestDBUtility.deleteAll(LoginHistoryPastRow.TYPE);
            TestDBUtility.deleteAll(LoginHistoryRow.TYPE);
            LoginHistoryParams l_loginHistoryParams = TestDBUtility.getLoginHistoryRow();
            l_loginHistoryParams.setLoginTimestamp(WEB3DateUtility.getDate("20080924093000","yyyyMMddHHmmss"));
            l_loginHistoryParams.setAccountCode("1020501");
            l_loginHistoryParams.setIpAddress("10.1.1.10");
            TestDBUtility.insertWithDel(l_loginHistoryParams);
            
            LoginHistoryParams l_loginHistoryParams1 = TestDBUtility.getLoginHistoryRow();
            l_loginHistoryParams1.setLoginTimestamp(WEB3DateUtility.getDate("20080924093000","yyyyMMddHHmmss"));
            l_loginHistoryParams1.setLoginHistoryId(1002);
            l_loginHistoryParams1.setAccountCode("1020501");
            l_loginHistoryParams1.setLoginFailure("0");
            l_loginHistoryParams1.setOrderRootDiv("1");
            l_loginHistoryParams1.setIpAddress("10.1.1.9");
            TestDBUtility.insertWithDel(l_loginHistoryParams1);
            
            LoginHistoryParams l_loginHistoryParams2 = TestDBUtility.getLoginHistoryRow();
            l_loginHistoryParams2.setLoginTimestamp(WEB3DateUtility.getDate("20080924093000","yyyyMMddHHmmss"));
            l_loginHistoryParams2.setLoginHistoryId(1003);
            l_loginHistoryParams2.setAccountCode("1020502");
            l_loginHistoryParams2.setLoginFailure("0");
            l_loginHistoryParams2.setOrderRootDiv("1");
            l_loginHistoryParams2.setIpAddress("10.1.1.11");
            TestDBUtility.insertWithDel(l_loginHistoryParams2);
            
            LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImplForMock);
            
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionRow = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionRow.setInstitutionCode("0D");
            l_adminPermissionRow.setTransactionCategory("C1301");
            l_adminPermissionRow.setPermissionLevel("331");
            TestDBUtility.insertWithDel(l_adminPermissionRow);
            
            l_response = l_impl.getSearchResultScreen(l_request);

            assertEquals("1", l_response.pageIndex);
            assertEquals("1", l_response.totalPages);
            assertEquals("3", l_response.totalRecords);
            
            assertEquals(3, l_response.loginHistoryList.length);
            assertEquals("1020501", l_response.loginHistoryList[0].accountCode);
            assertEquals("10.1.1.9", l_response.loginHistoryList[0].ipAddress);
            assertEquals("102", l_response.loginHistoryList[0].branchCode);
            assertEquals("1", l_response.loginHistoryList[0].orderRootDiv); 
            
            assertEquals("1020501", l_response.loginHistoryList[1].accountCode);
            assertEquals("10.1.1.10", l_response.loginHistoryList[1].ipAddress);
            assertEquals("102", l_response.loginHistoryList[1].branchCode);
            assertEquals("2", l_response.loginHistoryList[1].orderRootDiv); 
            
            assertEquals("1020502", l_response.loginHistoryList[2].accountCode);
            assertEquals("10.1.1.11", l_response.loginHistoryList[2].ipAddress);
            assertEquals("102", l_response.loginHistoryList[2].branchCode);
            assertEquals("1", l_response.loginHistoryList[2].orderRootDiv); 
        }
        catch (WEB3BaseException l_ex)
        {
            fail();
            log.error(l_ex.getErrorMessage());
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //正常結束
    //檢索login_history表
    public void testGetSearchResultScreen_Case008()
    {
        final String STR_METHOD_NAME = "testGetSearchResultScreen_Case008";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            Date l_date = WEB3DateUtility.getDate("20080924123000", "yyyyMMddHHmmss");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(l_date.getTime()));
            
            WEB3AdminTraderAdminLoginHistorySortKey[] l_sortKeys =
                new WEB3AdminTraderAdminLoginHistorySortKey[1];
            l_sortKeys[0] = new WEB3AdminTraderAdminLoginHistorySortKey();
            l_sortKeys[0].keyItem = "ipAddress";
            l_sortKeys[0].ascDesc = "A";
            
            WEB3AdminTraderAdminLoginHistoryListRequest l_request =
                new WEB3AdminTraderAdminLoginHistoryListRequest();
            l_request.accountCode = "102050";
            l_request.branchCode = null;
            l_request.ipAddress  = null;
            l_request.searchDate = "20080924";
            l_request.startTime = "0900";
            l_request.endTime = "1000";
            l_request.sortKeys = l_sortKeys;
            l_request.pageIndex = "1";
            l_request.pageSize = "4";
            WEB3AdminTraderAdminLoginHistoryListResponse l_response =
                new WEB3AdminTraderAdminLoginHistoryListResponse();
            
            TestDBUtility.deleteAll(LoginHistoryPastRow.TYPE);
            TestDBUtility.deleteAll(LoginHistoryRow.TYPE);
            LoginHistoryParams l_loginHistoryParams = TestDBUtility.getLoginHistoryRow();
            l_loginHistoryParams.setLoginTimestamp(WEB3DateUtility.getDate("20080924093000","yyyyMMddHHmmss"));
            l_loginHistoryParams.setAccountCode("1020501");
            l_loginHistoryParams.setIpAddress("1.1.1.1");
            TestDBUtility.insertWithDel(l_loginHistoryParams);
            
            LoginHistoryParams l_loginHistoryParams1 = TestDBUtility.getLoginHistoryRow();
            l_loginHistoryParams1.setLoginTimestamp(WEB3DateUtility.getDate("20080924093000","yyyyMMddHHmmss"));
            l_loginHistoryParams1.setLoginHistoryId(1002);
            l_loginHistoryParams1.setAccountCode("1020501");
            l_loginHistoryParams1.setLoginFailure("0");
            l_loginHistoryParams1.setOrderRootDiv("1");
            l_loginHistoryParams1.setIpAddress("1.1.1.0");
            TestDBUtility.insertWithDel(l_loginHistoryParams1);
            
            LoginHistoryParams l_loginHistoryParams2 = TestDBUtility.getLoginHistoryRow();
            l_loginHistoryParams2.setLoginTimestamp(WEB3DateUtility.getDate("20080924093000","yyyyMMddHHmmss"));
            l_loginHistoryParams2.setLoginHistoryId(1003);
            l_loginHistoryParams2.setAccountCode("1020502");
            l_loginHistoryParams2.setLoginFailure("0");
            l_loginHistoryParams2.setOrderRootDiv("1");
            l_loginHistoryParams2.setIpAddress("1.1.1.10");
            TestDBUtility.insertWithDel(l_loginHistoryParams2);
            
            LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImplForMock);
            
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionRow = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionRow.setInstitutionCode("0D");
            l_adminPermissionRow.setTransactionCategory("C1301");
            l_adminPermissionRow.setPermissionLevel("331");
            TestDBUtility.insertWithDel(l_adminPermissionRow);
            
            l_response = l_impl.getSearchResultScreen(l_request);

            assertEquals("1", l_response.pageIndex);
            assertEquals("1", l_response.totalPages);
            assertEquals("3", l_response.totalRecords);
            
            assertEquals(3, l_response.loginHistoryList.length);
            assertEquals("1020501", l_response.loginHistoryList[0].accountCode);
            assertEquals("1.1.1.0", l_response.loginHistoryList[0].ipAddress);
            assertEquals("102", l_response.loginHistoryList[0].branchCode);
            assertEquals("1", l_response.loginHistoryList[0].orderRootDiv); 
            
            assertEquals("1020501", l_response.loginHistoryList[1].accountCode);
            assertEquals("1.1.1.1", l_response.loginHistoryList[1].ipAddress);
            assertEquals("102", l_response.loginHistoryList[1].branchCode);
            assertEquals("2", l_response.loginHistoryList[1].orderRootDiv); 
            
            assertEquals("1020502", l_response.loginHistoryList[2].accountCode);
            assertEquals("1.1.1.10", l_response.loginHistoryList[2].ipAddress);
            assertEquals("102", l_response.loginHistoryList[2].branchCode);
            assertEquals("1", l_response.loginHistoryList[2].orderRootDiv); 
        }
        catch (WEB3BaseException l_ex)
        {
            fail();
            log.error(l_ex.getErrorMessage());
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //正常結束
    //檢索login_history表
    public void testGetSearchResultScreen_Case009()
    {
        final String STR_METHOD_NAME = "testGetSearchResultScreen_Case009";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            Date l_date = WEB3DateUtility.getDate("20080924123000", "yyyyMMddHHmmss");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(l_date.getTime()));
            
            WEB3AdminTraderAdminLoginHistorySortKey[] l_sortKeys =
                new WEB3AdminTraderAdminLoginHistorySortKey[1];
            l_sortKeys[0] = new WEB3AdminTraderAdminLoginHistorySortKey();
            l_sortKeys[0].keyItem = "ipAddress";
            l_sortKeys[0].ascDesc = "D";
            
            WEB3AdminTraderAdminLoginHistoryListRequest l_request =
                new WEB3AdminTraderAdminLoginHistoryListRequest();
            l_request.accountCode = "102050";
            l_request.branchCode = null;
            l_request.ipAddress  = null;
            l_request.searchDate = "20080924";
            l_request.startTime = "0900";
            l_request.endTime = "1000";
            l_request.sortKeys = l_sortKeys;
            l_request.pageIndex = "1";
            l_request.pageSize = "4";
            WEB3AdminTraderAdminLoginHistoryListResponse l_response =
                new WEB3AdminTraderAdminLoginHistoryListResponse();
            
            TestDBUtility.deleteAll(LoginHistoryPastRow.TYPE);
            TestDBUtility.deleteAll(LoginHistoryRow.TYPE);
            LoginHistoryParams l_loginHistoryParams = TestDBUtility.getLoginHistoryRow();
            l_loginHistoryParams.setLoginTimestamp(WEB3DateUtility.getDate("20080924093000","yyyyMMddHHmmss"));
            l_loginHistoryParams.setAccountCode("1020501");
            l_loginHistoryParams.setIpAddress("1.1.1.1");
            TestDBUtility.insertWithDel(l_loginHistoryParams);
            
            LoginHistoryParams l_loginHistoryParams1 = TestDBUtility.getLoginHistoryRow();
            l_loginHistoryParams1.setLoginTimestamp(WEB3DateUtility.getDate("20080924093000","yyyyMMddHHmmss"));
            l_loginHistoryParams1.setLoginHistoryId(1002);
            l_loginHistoryParams1.setAccountCode("1020501");
            l_loginHistoryParams1.setLoginFailure("0");
            l_loginHistoryParams1.setOrderRootDiv("1");
            l_loginHistoryParams1.setIpAddress("1.1.1.0");
            TestDBUtility.insertWithDel(l_loginHistoryParams1);
            
            LoginHistoryParams l_loginHistoryParams2 = TestDBUtility.getLoginHistoryRow();
            l_loginHistoryParams2.setLoginTimestamp(WEB3DateUtility.getDate("20080924093000","yyyyMMddHHmmss"));
            l_loginHistoryParams2.setLoginHistoryId(1003);
            l_loginHistoryParams2.setAccountCode("1020502");
            l_loginHistoryParams2.setLoginFailure("0");
            l_loginHistoryParams2.setOrderRootDiv("1");
            l_loginHistoryParams2.setIpAddress("1.1.1.10");
            TestDBUtility.insertWithDel(l_loginHistoryParams2);
            
            LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImplForMock);
            
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionRow = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionRow.setInstitutionCode("0D");
            l_adminPermissionRow.setTransactionCategory("C1301");
            l_adminPermissionRow.setPermissionLevel("331");
            TestDBUtility.insertWithDel(l_adminPermissionRow);
            
            l_response = l_impl.getSearchResultScreen(l_request);

            assertEquals("1", l_response.pageIndex);
            assertEquals("1", l_response.totalPages);
            assertEquals("3", l_response.totalRecords);
            
            assertEquals("1020502", l_response.loginHistoryList[0].accountCode);
            assertEquals("1.1.1.10", l_response.loginHistoryList[0].ipAddress);
            assertEquals("102", l_response.loginHistoryList[0].branchCode);
            assertEquals("1", l_response.loginHistoryList[0].orderRootDiv); 
            
            assertEquals("1020501", l_response.loginHistoryList[1].accountCode);
            assertEquals("1.1.1.1", l_response.loginHistoryList[1].ipAddress);
            assertEquals("102", l_response.loginHistoryList[1].branchCode);
            assertEquals("2", l_response.loginHistoryList[1].orderRootDiv); 
            
            assertEquals(3, l_response.loginHistoryList.length);
            assertEquals("1020501", l_response.loginHistoryList[2].accountCode);
            assertEquals("1.1.1.0", l_response.loginHistoryList[2].ipAddress);
            assertEquals("102", l_response.loginHistoryList[2].branchCode);
            assertEquals("1", l_response.loginHistoryList[2].orderRootDiv); 

        }
        catch (WEB3BaseException l_ex)
        {
            fail();
            log.error(l_ex.getErrorMessage());
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //正常結束
    //檢索login_history表
    public void testGetSearchResultScreen_Case0010()
    {
        final String STR_METHOD_NAME = "testGetSearchResultScreen_Case0010";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            Date l_date = WEB3DateUtility.getDate("20080924123000", "yyyyMMddHHmmss");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(l_date.getTime()));
            
            WEB3AdminTraderAdminLoginHistorySortKey[] l_sortKeys =
                new WEB3AdminTraderAdminLoginHistorySortKey[1];
            l_sortKeys[0] = new WEB3AdminTraderAdminLoginHistorySortKey();
            l_sortKeys[0].keyItem = "ipAddress";
            l_sortKeys[0].ascDesc = "A";
            
            WEB3AdminTraderAdminLoginHistoryListRequest l_request =
                new WEB3AdminTraderAdminLoginHistoryListRequest();
            l_request.accountCode = "102050";
            l_request.branchCode = null;
            l_request.ipAddress  = null;
            l_request.searchDate = "20080924";
            l_request.startTime = "0900";
            l_request.endTime = "1000";
            l_request.sortKeys = l_sortKeys;
            l_request.pageIndex = "1";
            l_request.pageSize = "4";
            WEB3AdminTraderAdminLoginHistoryListResponse l_response =
                new WEB3AdminTraderAdminLoginHistoryListResponse();
            
            TestDBUtility.deleteAll(LoginHistoryPastRow.TYPE);
            TestDBUtility.deleteAll(LoginHistoryRow.TYPE);
            LoginHistoryParams l_loginHistoryParams = TestDBUtility.getLoginHistoryRow();
            l_loginHistoryParams.setLoginTimestamp(WEB3DateUtility.getDate("20080924093000","yyyyMMddHHmmss"));
            l_loginHistoryParams.setAccountCode("1020501");
            l_loginHistoryParams.setIpAddress("0.0.0.1");
            TestDBUtility.insertWithDel(l_loginHistoryParams);
            
            LoginHistoryParams l_loginHistoryParams1 = TestDBUtility.getLoginHistoryRow();
            l_loginHistoryParams1.setLoginTimestamp(WEB3DateUtility.getDate("20080924093000","yyyyMMddHHmmss"));
            l_loginHistoryParams1.setLoginHistoryId(1002);
            l_loginHistoryParams1.setAccountCode("1020501");
            l_loginHistoryParams1.setLoginFailure("0");
            l_loginHistoryParams1.setOrderRootDiv("1");
            l_loginHistoryParams1.setIpAddress("0.0.0.0");
            TestDBUtility.insertWithDel(l_loginHistoryParams1);
            
            LoginHistoryParams l_loginHistoryParams2 = TestDBUtility.getLoginHistoryRow();
            l_loginHistoryParams2.setLoginTimestamp(WEB3DateUtility.getDate("20080924093000","yyyyMMddHHmmss"));
            l_loginHistoryParams2.setLoginHistoryId(1003);
            l_loginHistoryParams2.setAccountCode("1020502");
            l_loginHistoryParams2.setLoginFailure("0");
            l_loginHistoryParams2.setOrderRootDiv("1");
            l_loginHistoryParams2.setIpAddress("0.0.0.10");
            TestDBUtility.insertWithDel(l_loginHistoryParams2);
            
            LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImplForMock);
            
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionRow = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionRow.setInstitutionCode("0D");
            l_adminPermissionRow.setTransactionCategory("C1301");
            l_adminPermissionRow.setPermissionLevel("331");
            TestDBUtility.insertWithDel(l_adminPermissionRow);
            
            l_response = l_impl.getSearchResultScreen(l_request);

            assertEquals("1", l_response.pageIndex);
            assertEquals("1", l_response.totalPages);
            assertEquals("3", l_response.totalRecords);
            
            assertEquals(3, l_response.loginHistoryList.length);
            assertEquals("1020501", l_response.loginHistoryList[0].accountCode);
            assertEquals("0.0.0.0", l_response.loginHistoryList[0].ipAddress);
            assertEquals("102", l_response.loginHistoryList[0].branchCode);
            assertEquals("1", l_response.loginHistoryList[0].orderRootDiv); 
            
            assertEquals("1020501", l_response.loginHistoryList[1].accountCode);
            assertEquals("0.0.0.1", l_response.loginHistoryList[1].ipAddress);
            assertEquals("102", l_response.loginHistoryList[1].branchCode);
            assertEquals("2", l_response.loginHistoryList[1].orderRootDiv); 
            
            assertEquals("1020502", l_response.loginHistoryList[2].accountCode);
            assertEquals("0.0.0.10", l_response.loginHistoryList[2].ipAddress);
            assertEquals("102", l_response.loginHistoryList[2].branchCode);
            assertEquals("1", l_response.loginHistoryList[2].orderRootDiv); 
        }
        catch (WEB3BaseException l_ex)
        {
            fail();
            log.error(l_ex.getErrorMessage());
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //正常結束
    //檢索login_history表
    public void testGetSearchResultScreen_Case0011()
    {
        final String STR_METHOD_NAME = "testGetSearchResultScreen_Case0011";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            Date l_date = WEB3DateUtility.getDate("20080924123000", "yyyyMMddHHmmss");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(l_date.getTime()));
            
            WEB3AdminTraderAdminLoginHistorySortKey[] l_sortKeys =
                new WEB3AdminTraderAdminLoginHistorySortKey[1];
            l_sortKeys[0] = new WEB3AdminTraderAdminLoginHistorySortKey();
            l_sortKeys[0].keyItem = "ipAddress";
            l_sortKeys[0].ascDesc = "D";
            
            WEB3AdminTraderAdminLoginHistoryListRequest l_request =
                new WEB3AdminTraderAdminLoginHistoryListRequest();
            l_request.accountCode = "102050";
            l_request.branchCode = null;
            l_request.ipAddress  = null;
            l_request.searchDate = "20080924";
            l_request.startTime = "0900";
            l_request.endTime = "1000";
            l_request.sortKeys = l_sortKeys;
            l_request.pageIndex = "1";
            l_request.pageSize = "4";
            WEB3AdminTraderAdminLoginHistoryListResponse l_response =
                new WEB3AdminTraderAdminLoginHistoryListResponse();
            
            TestDBUtility.deleteAll(LoginHistoryPastRow.TYPE);
            TestDBUtility.deleteAll(LoginHistoryRow.TYPE);
            LoginHistoryParams l_loginHistoryParams = TestDBUtility.getLoginHistoryRow();
            l_loginHistoryParams.setLoginTimestamp(WEB3DateUtility.getDate("20080924093000","yyyyMMddHHmmss"));
            l_loginHistoryParams.setAccountCode("1020501");
            l_loginHistoryParams.setIpAddress("0.0.0.1");
            TestDBUtility.insertWithDel(l_loginHistoryParams);
            
            LoginHistoryParams l_loginHistoryParams1 = TestDBUtility.getLoginHistoryRow();
            l_loginHistoryParams1.setLoginTimestamp(WEB3DateUtility.getDate("20080924093000","yyyyMMddHHmmss"));
            l_loginHistoryParams1.setLoginHistoryId(1002);
            l_loginHistoryParams1.setAccountCode("1020501");
            l_loginHistoryParams1.setLoginFailure("0");
            l_loginHistoryParams1.setOrderRootDiv("1");
            l_loginHistoryParams1.setIpAddress("0.0.0.0");
            TestDBUtility.insertWithDel(l_loginHistoryParams1);
            
            LoginHistoryParams l_loginHistoryParams2 = TestDBUtility.getLoginHistoryRow();
            l_loginHistoryParams2.setLoginTimestamp(WEB3DateUtility.getDate("20080924093000","yyyyMMddHHmmss"));
            l_loginHistoryParams2.setLoginHistoryId(1003);
            l_loginHistoryParams2.setAccountCode("1020502");
            l_loginHistoryParams2.setLoginFailure("0");
            l_loginHistoryParams2.setOrderRootDiv("1");
            l_loginHistoryParams2.setIpAddress("0.0.0.10");
            TestDBUtility.insertWithDel(l_loginHistoryParams2);
            
            LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                l_loginInfoImplForMock);
            
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(0);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionRow = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionRow.setInstitutionCode("0D");
            l_adminPermissionRow.setTransactionCategory("C1301");
            l_adminPermissionRow.setPermissionLevel("331");
            TestDBUtility.insertWithDel(l_adminPermissionRow);
            
            l_response = l_impl.getSearchResultScreen(l_request);

            assertEquals("1", l_response.pageIndex);
            assertEquals("1", l_response.totalPages);
            assertEquals("3", l_response.totalRecords);
            
            assertEquals("1020502", l_response.loginHistoryList[0].accountCode);
            assertEquals("0.0.0.10", l_response.loginHistoryList[0].ipAddress);
            assertEquals("102", l_response.loginHistoryList[0].branchCode);
            assertEquals("1", l_response.loginHistoryList[0].orderRootDiv); 
            
            assertEquals("1020501", l_response.loginHistoryList[1].accountCode);
            assertEquals("0.0.0.1", l_response.loginHistoryList[1].ipAddress);
            assertEquals("102", l_response.loginHistoryList[1].branchCode);
            assertEquals("2", l_response.loginHistoryList[1].orderRootDiv); 
            
            assertEquals(3, l_response.loginHistoryList.length);
            assertEquals("1020501", l_response.loginHistoryList[2].accountCode);
            assertEquals("0.0.0.0", l_response.loginHistoryList[2].ipAddress);
            assertEquals("102", l_response.loginHistoryList[2].branchCode);
            assertEquals("1", l_response.loginHistoryList[2].orderRootDiv); 

        }
        catch (WEB3BaseException l_ex)
        {
            fail();
            log.error(l_ex.getErrorMessage());
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    
//    引数.IPアドレス != null の場合 かつ
//    引数.部店 != null の場合 かつ
//    引数.顧客コード != null の場合 
//    返回正確的文字列
    public void testCreateQueryString_C001()
    {
        final String STR_METHOD_NAME = "testCreateQueryString_C001";
        log.entering(STR_METHOD_NAME);
        
        String l_strIpAddress = "192.168.255.21";
        String l_strAccountCode = "102050";
        String l_strBranchCode = "102";
        try
        {
            Method l_method = WEB3AdminTMLoginProcessingListServiceImpl.class.getDeclaredMethod(
                    "createQueryString",
                    new Class[]{String.class, String.class, String.class});
            l_method.setAccessible(true);
            Object[] l_obj = {l_strIpAddress, l_strAccountCode, l_strBranchCode};
            String l_strReturn = (String)l_method.invoke(l_impl, l_obj);
            assertEquals("institution_code = ? and " +
                    "login_timestamp >= to_date( ? || ? ||'00', 'yyyymmddhh24miss') and " +
                    "login_timestamp <= to_date( ? || ? ||'59', 'yyyymmddhh24miss') " +
                    " and ip_address = ?" +
                    " and branch_code like ? || '%'" +
                    " and account_code like ? || '%' ", l_strReturn);
        }
        catch (Exception  l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
//    引数.IPアドレス = null の場合 かつ
//    引数.部店 = null の場合 かつ
//    引数.顧客コード = null の場合 
//    返回正確的文字列
    public void testCreateQueryString_C002()
    {
        final String STR_METHOD_NAME = "testCreateQueryString_C002";
        log.entering(STR_METHOD_NAME);
        
        String l_strIpAddress = null;
        String l_strAccountCode = null;
        String l_strBranchCode = null;
        try
        {
            Method l_method = WEB3AdminTMLoginProcessingListServiceImpl.class.getDeclaredMethod(
                    "createQueryString",
                    new Class[]{String.class, String.class, String.class});
            l_method.setAccessible(true);
            Object[] l_obj = {l_strIpAddress, l_strAccountCode, l_strBranchCode};
            String l_strReturn = (String)l_method.invoke(l_impl, l_obj);
            assertEquals("institution_code = ? and " +
                    "login_timestamp >= to_date( ? || ? ||'00', 'yyyymmddhh24miss') and " +
                    "login_timestamp <= to_date( ? || ? ||'59', 'yyyymmddhh24miss') ", l_strReturn);
        }
        catch (Exception  l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
//    引数.IPアドレス ！= null の場合 かつ
//    引数.部店 ！= null の場合 かつ
//    引数.顧客コード = null の場合 
//    返回正確的文字列
    public void testCreateQueryString_C003()
    {
        final String STR_METHOD_NAME = "testCreateQueryString_C003";
        log.entering(STR_METHOD_NAME);
        
        String l_strIpAddress = "192.168.255.21";
        String l_strAccountCode = null;
        String l_strBranchCode = "102";
        try
        {
            Method l_method = WEB3AdminTMLoginProcessingListServiceImpl.class.getDeclaredMethod(
                    "createQueryString",
                    new Class[]{String.class, String.class, String.class});
            l_method.setAccessible(true);
            Object[] l_obj = {l_strIpAddress, l_strAccountCode, l_strBranchCode};
            String l_strReturn = (String)l_method.invoke(l_impl, l_obj);
            assertEquals("institution_code = ? and " +
                    "login_timestamp >= to_date( ? || ? ||'00', 'yyyymmddhh24miss') and " +
                    "login_timestamp <= to_date( ? || ? ||'59', 'yyyymmddhh24miss') " +
                    " and ip_address = ?" +
                    " and branch_code like ? || '%'", l_strReturn);
        }
        catch (Exception  l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
//    引数.IPアドレス != null の場合　@かつ
//    引数.部店 != null の場合　@かつ
//    引数.顧客コード != null の場合
    public void testCreateQueryDataContainer_C001()
    {
        final String STR_METHOD_NAME = "testCreateQueryDataContainer_C001";
        log.entering(STR_METHOD_NAME);
        
        String l_strInstitutionCode = "0D";
        String l_strSearchDate = "20080402";
        String l_strIpAddress = "192.168.255.21";
        String l_strBranchCode = "102";
        String l_strAccountCode = "102050";
        String l_strStartTime = "0900";
        String l_strEndTime = "1000";
        
        try
        {
            Method l_method = WEB3AdminTMLoginProcessingListServiceImpl.class.getDeclaredMethod(
                    "createQueryDataContainer",
                    new Class[]{String.class, String.class, String.class,
                                String.class, String.class, String.class, String.class});
            l_method.setAccessible(true);
            Object[] l_obj = {l_strInstitutionCode, l_strSearchDate, l_strIpAddress,
                l_strBranchCode, l_strAccountCode, l_strStartTime, l_strEndTime};
            Object[] l_strReturn = (Object[])l_method.invoke(l_impl, l_obj);
            assertEquals("0D", l_strReturn[0]);
            assertEquals("20080402", l_strReturn[1]);
            assertEquals("0900", l_strReturn[2]);
            assertEquals("20080402", l_strReturn[3]);
            assertEquals("1000", l_strReturn[4]);
            assertEquals("192.168.255.21", l_strReturn[5]);
            assertEquals("102", l_strReturn[6]);
            assertEquals("102050", l_strReturn[7]);
        }
        catch (Exception  l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
//    引数.IPアドレス = null の場合　@かつ
//    引数.部店 = null の場合　@かつ
//    引数.顧客コード = null の場合
    public void testCreateQueryDataContainer_C002()
    {
        final String STR_METHOD_NAME = "testCreateQueryDataContainer_C002";
        log.entering(STR_METHOD_NAME);
        
        String l_strInstitutionCode = "0D";
        String l_strSearchDate = "20080402";
        String l_strIpAddress = null;
        String l_strBranchCode = null;
        String l_strAccountCode = null;
        String l_strStartTime = "0900";
        String l_strEndTime = "1000";
        
        try
        {
            Method l_method = WEB3AdminTMLoginProcessingListServiceImpl.class.getDeclaredMethod(
                    "createQueryDataContainer",
                    new Class[]{String.class, String.class, String.class,
                                String.class, String.class, String.class, String.class});
            l_method.setAccessible(true);
            Object[] l_obj = {l_strInstitutionCode, l_strSearchDate, l_strIpAddress,
                l_strBranchCode, l_strAccountCode, l_strStartTime, l_strEndTime};
            Object[] l_strReturn = (Object[])l_method.invoke(l_impl, l_obj);
            assertEquals(5, l_strReturn.length);
            assertEquals("0D", l_strReturn[0]);
            assertEquals("20080402", l_strReturn[1]);
            assertEquals("0900", l_strReturn[2]);
            assertEquals("20080402", l_strReturn[3]);
            assertEquals("1000", l_strReturn[4]);   
        }
        catch (Exception  l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
//    引数.IPアドレス！ = null 　@かつ
//    引数.部店 = null　@かつ
//    引数.顧客コード ！= null 
    public void testCreateQueryDataContainer_C003()
    {
        final String STR_METHOD_NAME = "testCreateQueryDataContainer_C003";
        log.entering(STR_METHOD_NAME);
        
        String l_strInstitutionCode = "0D";
        String l_strSearchDate = "20080402";
        String l_strIpAddress = "192.168.255.21";
        String l_strBranchCode = null;
        String l_strAccountCode = "102050";
        String l_strStartTime = "0900";
        String l_strEndTime = "1000";
        
        try
        {
            Method l_method = WEB3AdminTMLoginProcessingListServiceImpl.class.getDeclaredMethod(
                    "createQueryDataContainer",
                    new Class[]{String.class, String.class, String.class,
                                String.class, String.class, String.class, String.class});
            l_method.setAccessible(true);
            Object[] l_obj = {l_strInstitutionCode, l_strSearchDate, l_strIpAddress,
                l_strBranchCode, l_strAccountCode, l_strStartTime, l_strEndTime};
            Object[] l_strReturn = (Object[])l_method.invoke(l_impl, l_obj);
            assertEquals(7, l_strReturn.length);
            assertEquals("0D", l_strReturn[0]);
            assertEquals("20080402", l_strReturn[1]);
            assertEquals("0900", l_strReturn[2]);
            assertEquals("20080402", l_strReturn[3]);
            assertEquals("1000", l_strReturn[4]);
            assertEquals("192.168.255.21", l_strReturn[5]);
            assertEquals("102050", l_strReturn[6]);
        }
        catch (Exception  l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
//    (引数)ソートキー.キー項目 = 日時 
//    昇順：asc 
    public void testCreateSortCond_C001()
    {
        final String STR_METHOD_NAME = "testCreateSortCond_C001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminTraderAdminLoginHistorySortKey[] l_sortKeys =
                new WEB3AdminTraderAdminLoginHistorySortKey[1];
            l_sortKeys[0] = new WEB3AdminTraderAdminLoginHistorySortKey();
            l_sortKeys[0].keyItem = "loginDate";
            l_sortKeys[0].ascDesc = "A";
            
            Method l_method = WEB3AdminTMLoginProcessingListServiceImpl.class.getDeclaredMethod(
                "createSortCond",
                new Class[]{WEB3AdminTraderAdminLoginHistorySortKey[].class});
            l_method.setAccessible(true);
            Object[] l_obj = {l_sortKeys};
            String l_strReturn = (String)l_method.invoke(l_impl, l_obj);
            assertEquals(" login_timestamp ASC", l_strReturn);
        }
        catch(Exception l_ex)
        {
            fail();
            log.error(l_ex.getMessage());
        }
        
        log.exiting(STR_METHOD_NAME);
    }
//    (引数)ソートキー.キー項目 = 顧客コード 
//    昇順：Desc 
    public void testCreateSortCond_C002()
    {
        final String STR_METHOD_NAME = "testCreateSortCond_C002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminTraderAdminLoginHistorySortKey[] l_sortKeys =
                new WEB3AdminTraderAdminLoginHistorySortKey[1];
            l_sortKeys[0] = new WEB3AdminTraderAdminLoginHistorySortKey();
            l_sortKeys[0].keyItem = "accountCode";
            l_sortKeys[0].ascDesc = "D";
            
            Method l_method = WEB3AdminTMLoginProcessingListServiceImpl.class.getDeclaredMethod(
                "createSortCond",
                new Class[]{WEB3AdminTraderAdminLoginHistorySortKey[].class});
            l_method.setAccessible(true);
            Object[] l_obj = {l_sortKeys};
            String l_strReturn = (String)l_method.invoke(l_impl, l_obj);
            assertEquals(" account_code DESC", l_strReturn);
        }
        catch(Exception l_ex)
        {
            fail();
            log.error(l_ex.getMessage());
        }
        
        log.exiting(STR_METHOD_NAME);
    }
//    (引数)ソートキー.キー項目 = 顧客コード の場合
//    昇順：asc 
//    引数)ソートキー.キー項目 = IPアドレス の
//    降順：desc
    public void testCreateSortCond_C003()
    {
        final String STR_METHOD_NAME = "testCreateSortCond_C003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminTraderAdminLoginHistorySortKey[] l_sortKeys =
                new WEB3AdminTraderAdminLoginHistorySortKey[2];
            l_sortKeys[0] = new WEB3AdminTraderAdminLoginHistorySortKey();
            l_sortKeys[0].keyItem = "ipAddress";
            l_sortKeys[0].ascDesc = "D";
            
            l_sortKeys[1] = new WEB3AdminTraderAdminLoginHistorySortKey();
            l_sortKeys[1].keyItem = "accountCode";
            l_sortKeys[1].ascDesc = "A";
            
            Method l_method = WEB3AdminTMLoginProcessingListServiceImpl.class.getDeclaredMethod(
                "createSortCond",
                new Class[]{WEB3AdminTraderAdminLoginHistorySortKey[].class});
            l_method.setAccessible(true);
            Object[] l_obj = {l_sortKeys};
            String l_strReturn = (String)l_method.invoke(l_impl, l_obj);
            assertEquals(" NVL( LPAD( SUBSTR( IP_ADDRESS, 1, INSTR( IP_ADDRESS," +
            " '.', 1, 1) -1), 3, '0'), '000') ||" +
            "NVL( LPAD( SUBSTR( IP_ADDRESS, INSTR( IP_ADDRESS, '.', 1, 1) +1," +
            " INSTR( IP_ADDRESS, '.', 1, 2) - INSTR" +
            "( IP_ADDRESS, '.', 1, 1)-1), 3, '0'), '000') || " +
            "NVL( LPAD( SUBSTR( IP_ADDRESS, INSTR( IP_ADDRESS, '.', 1, 2) +1," +
            " INSTR( IP_ADDRESS, '.', 1, 3) - INSTR" +
            "( IP_ADDRESS, '.', 1, 2)-1), 3, '0'), '000') || " +
            "NVL( LPAD( SUBSTR( IP_ADDRESS, INSTR( IP_ADDRESS, '.', 1, 3) +1), 3, '0'), '000') DESC,  account_code ASC", l_strReturn);
        }
        catch(Exception l_ex)
        {
            fail();
            log.error(l_ex.getMessage());
        }
        
        log.exiting(STR_METHOD_NAME);
    }
//    (引数)ソートキー.キー項目 = 日時
//    昇順：asc 
//    引数)ソートキー.キー項目 = IPアドレス 
//    降順：desc
//    (引数)ソートキー.キー項目 = 顧客コード 
//    昇順：asc     
    public void testCreateSortCond_C004()
    {
        final String STR_METHOD_NAME = "testCreateSortCond_C004()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminTraderAdminLoginHistorySortKey[] l_sortKeys =
                new WEB3AdminTraderAdminLoginHistorySortKey[3];
            l_sortKeys[0] = new WEB3AdminTraderAdminLoginHistorySortKey();
            l_sortKeys[0].keyItem = "loginDate";
            l_sortKeys[0].ascDesc = "A";
            
            l_sortKeys[1] = new WEB3AdminTraderAdminLoginHistorySortKey();
            l_sortKeys[1].keyItem = "ipAddress";
            l_sortKeys[1].ascDesc = "D";
            
            l_sortKeys[2] = new WEB3AdminTraderAdminLoginHistorySortKey();
            l_sortKeys[2].keyItem = "accountCode";
            l_sortKeys[2].ascDesc = "A";
            
            Method l_method = WEB3AdminTMLoginProcessingListServiceImpl.class.getDeclaredMethod(
                "createSortCond",
                new Class[]{WEB3AdminTraderAdminLoginHistorySortKey[].class});
            l_method.setAccessible(true);
            Object[] l_obj = {l_sortKeys};
            String l_strReturn = (String)l_method.invoke(l_impl, l_obj);
            assertEquals(" login_timestamp ASC,  NVL( LPAD( SUBSTR( IP_ADDRESS, 1, INSTR( IP_ADDRESS," +
                    " '.', 1, 1) -1), 3, '0'), '000') ||" +
                    "NVL( LPAD( SUBSTR( IP_ADDRESS, INSTR( IP_ADDRESS, '.', 1, 1) +1," +
                    " INSTR( IP_ADDRESS, '.', 1, 2) - INSTR" +
                    "( IP_ADDRESS, '.', 1, 1)-1), 3, '0'), '000') || " +
                    "NVL( LPAD( SUBSTR( IP_ADDRESS, INSTR( IP_ADDRESS, '.', 1, 2) +1," +
                    " INSTR( IP_ADDRESS, '.', 1, 3) - INSTR" +
                    "( IP_ADDRESS, '.', 1, 2)-1), 3, '0'), '000') || " +
                    "NVL( LPAD( SUBSTR( IP_ADDRESS, INSTR( IP_ADDRESS, '.', 1, 3) +1), 3, '0'), '000') DESC, "+
                    " account_code ASC", l_strReturn);
        }
        catch(Exception l_ex)
        {
            fail();
            log.error(l_ex.getMessage());
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    //検索結果が0件の場合、エラーを返却する。
    public void testGetLoginProessInfoList_C001()
    {
        final String STR_METHOD_NAME = "testGetLoginProessInfoList_C001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            Date l_date = WEB3DateUtility.getDate("20080924123000", "yyyyMMddHHmmss");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(l_date.getTime()));
            
            TestDBUtility.deleteAll(LoginHistoryRow.TYPE);
            String l_strSearchCondition = "institution_code = ? and " +
                    "login_timestamp >= to_date( ? || ? ||'00', 'yyyymmddhh24miss') and " +
                    "login_timestamp <= to_date( ? || ? ||'59', 'yyyymmddhh24miss') " +
                    " and ip_address = ?" +
                    " and branch_code like ?" +
                    " and account_code like ? || '%' ";
            String l_strSearchSortCondtion = " login_timestamp ASC,  ip_address DESC,  account_code ASC";
            
            String l_strInstitutionCode = "0D";
            String l_strSearchDate = "20080924";
            String l_strIpAddress = "192.168.255.21";
            String l_strBranchCode = "102";
            String l_strAccountCode = "102050";
            String l_strStartTime = "0900";
            String l_strEndTime = "1000";
            Object[] l_dataContainers = {
               l_strInstitutionCode,
               l_strSearchDate,
               l_strStartTime,
               l_strSearchDate,
               l_strEndTime,
               l_strIpAddress,
               l_strBranchCode,
               l_strAccountCode
            };
            
            Method l_method = WEB3AdminTMLoginProcessingListServiceImpl.class.getDeclaredMethod(
                "getLoginProessInfoList",
                new Class[]{String.class, String.class, Object[].class});
                l_method.setAccessible(true);
                Object[] l_obj = {l_strSearchCondition, l_strSearchSortCondtion, l_dataContainers};
                List l_strReturns = (ArrayList)l_method.invoke(l_impl, l_obj);
                fail();
        }
        catch(InvocationTargetException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3BusinessLayerException.class,
                l_ex.getTargetException().getClass());
            WEB3BusinessLayerException l_targetException =
                (WEB3BusinessLayerException)l_ex.getTargetException();
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                l_targetException.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //検索結果が1件の場合
    ////LoginHistoryRow.TYPE
    public void testGetLoginProessInfoList_C002()
    {
        final String STR_METHOD_NAME = "testGetLoginProessInfoList_C002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            Date l_date = WEB3DateUtility.getDate("20080924123000", "yyyyMMddHHmmss");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(l_date.getTime()));
            
            TestDBUtility.deleteAll(LoginHistoryRow.TYPE);
            String l_strSearchCondition = "institution_code = ? and " +
                    "login_timestamp >= to_date( ? || ? ||'00', 'yyyymmddhh24miss') and " +
                    "login_timestamp <= to_date( ? || ? ||'59', 'yyyymmddhh24miss') " +
                    " and ip_address = ?" +
                    " and branch_code like ?" +
                    " and account_code like ? || '%' ";
            String l_strSearchSortCondtion = " login_timestamp ASC,  ip_address DESC,  account_code ASC";
            
            String l_strInstitutionCode = "0D";
            String l_strSearchDate = "20080924";
            String l_strIpAddress = "192.168.255.21";
            String l_strBranchCode = "102";
            String l_strAccountCode = "102050";
            String l_strStartTime = "0900";
            String l_strEndTime = "1000";
            Object[] l_dataContainers = {
                    l_strInstitutionCode,
                    l_strSearchDate,
                    l_strStartTime,
                    l_strSearchDate,
                    l_strEndTime,
                    l_strIpAddress,
                    l_strBranchCode,
                    l_strAccountCode
                 };
            
            TestDBUtility.deleteAll(LoginHistoryRow.TYPE);
            LoginHistoryParams l_loginHistoryParams = TestDBUtility.getLoginHistoryRow();
            l_loginHistoryParams.setAccountCode("1020501");
            l_loginHistoryParams.setLoginTimestamp(WEB3DateUtility.getDate("20080924093000","yyyyMMddHHmmss"));
            l_loginHistoryParams.setBranchCode("102");
            TestDBUtility.insertWithDel(l_loginHistoryParams);
            
            Method l_method = WEB3AdminTMLoginProcessingListServiceImpl.class.getDeclaredMethod(
                "getLoginProessInfoList",
                new Class[]{String.class, String.class, Object[].class});
                l_method.setAccessible(true);
                Object[] l_obj = {l_strSearchCondition, l_strSearchSortCondtion, l_dataContainers};
                List l_strReturns = (ArrayList)l_method.invoke(l_impl, l_obj);
                LoginHistoryRow l_loginHistoryRow =
                    (LoginHistoryRow)l_strReturns.get(0);
                assertEquals("1020501", l_loginHistoryRow.getAccountCode());
                assertEquals("192.168.255.21", l_loginHistoryRow.getIpAddress());
                assertEquals("102", l_loginHistoryRow.getBranchCode());
                assertEquals("0D", l_loginHistoryRow.getInstitutionCode());
                assertEquals("2", l_loginHistoryRow.getOrderRootDiv());
        }
        catch(Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //検索結果が2件の場合
    //LoginHistoryRow.TYPE
    public void testGetLoginProessInfoList_C003()
    {
        final String STR_METHOD_NAME = "testGetLoginProessInfoList_C003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            Date l_date = WEB3DateUtility.getDate("20080924123000", "yyyyMMddHHmmss");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(l_date.getTime()));
            
            TestDBUtility.deleteAll(LoginHistoryRow.TYPE);
            String l_strSearchCondition = "institution_code = ? and " +
                    "login_timestamp >= to_date( ? || ? ||'00', 'yyyymmddhh24miss') and " +
                    "login_timestamp <= to_date( ? || ? ||'59', 'yyyymmddhh24miss') " +
                    " and ip_address = ?" +
                    " and branch_code like ?" +
                    " and account_code like ? || '%' ";
            String l_strSearchSortCondtion = " login_timestamp ASC,  ip_address DESC,  account_code ASC";
            
            String l_strInstitutionCode = "0D";
            String l_strSearchDate = "20080924";
            String l_strIpAddress = "192.168.255.21";
            String l_strBranchCode = "102";
            String l_strAccountCode = "102050";
            String l_strStartTime = "0900";
            String l_strEndTime = "1000";
            Object[] l_dataContainers = {
                    l_strInstitutionCode,
                    l_strSearchDate,
                    l_strStartTime,
                    l_strSearchDate,
                    l_strEndTime,
                    l_strIpAddress,
                    l_strBranchCode,
                    l_strAccountCode
                 };
            
            TestDBUtility.deleteAll(LoginHistoryRow.TYPE);
            LoginHistoryParams l_loginHistoryParams1 = TestDBUtility.getLoginHistoryRow();
            l_loginHistoryParams1.setLoginTimestamp(WEB3DateUtility.getDate("20080924093000","yyyyMMddHHmmss"));
            l_loginHistoryParams1.setAccountCode("1020501");
            TestDBUtility.insertWithDel(l_loginHistoryParams1);
            
            LoginHistoryParams l_loginHistoryParams2 = TestDBUtility.getLoginHistoryRow();
            l_loginHistoryParams2.setLoginTimestamp(WEB3DateUtility.getDate("20080924093000","yyyyMMddHHmmss"));
            l_loginHistoryParams2.setLoginHistoryId(1002);
            l_loginHistoryParams2.setLoginFailure("0");
            l_loginHistoryParams2.setAccountCode("102050");
            TestDBUtility.insertWithDel(l_loginHistoryParams2);
            
            Method l_method = WEB3AdminTMLoginProcessingListServiceImpl.class.getDeclaredMethod(
                "getLoginProessInfoList",
                new Class[]{String.class, String.class, Object[].class});
                l_method.setAccessible(true);
                Object[] l_obj = {l_strSearchCondition, l_strSearchSortCondtion, l_dataContainers};
                List l_strReturns = (ArrayList)l_method.invoke(l_impl, l_obj);
                LoginHistoryRow l_loginHistoryRow1 =
                    (LoginHistoryRow)l_strReturns.get(0);
                assertEquals(2, l_strReturns.size());
                
                assertEquals(1002, l_loginHistoryRow1.getLoginHistoryId());
                assertEquals("102050", l_loginHistoryRow1.getAccountCode());
                assertEquals("192.168.255.21", l_loginHistoryRow1.getIpAddress());
                assertEquals("102", l_loginHistoryRow1.getBranchCode());
                assertEquals("0D", l_loginHistoryRow1.getInstitutionCode());
                assertEquals("2", l_loginHistoryRow1.getOrderRootDiv());
                assertEquals("0", l_loginHistoryRow1.getLoginFailure());
                
                LoginHistoryRow l_loginHistoryRow2 =
                    (LoginHistoryRow)l_strReturns.get(1);
                assertEquals(1001, l_loginHistoryRow2.getLoginHistoryId());
                assertEquals("1020501", l_loginHistoryRow2.getAccountCode());
                assertEquals("192.168.255.21", l_loginHistoryRow2.getIpAddress());
                assertEquals("102", l_loginHistoryRow2.getBranchCode());
                assertEquals("0D", l_loginHistoryRow2.getInstitutionCode());
                assertEquals("2", l_loginHistoryRow2.getOrderRootDiv()); 
                assertEquals("1", l_loginHistoryRow2.getLoginFailure());
        }
        catch(Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //検索結果が1件の場合
    //LoginHistoryPastRow.TYPE
    public void testGetLoginProessInfoList_C004()
    {
        final String STR_METHOD_NAME = "testGetLoginProessInfoList_C004()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            Date l_date = WEB3DateUtility.getDate("20080925133000", "yyyyMMddHHmmss");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(l_date.getTime()));
            
            TestDBUtility.deleteAll(LoginHistoryRow.TYPE);
            String l_strSearchCondition = "institution_code = ? and " +
                    "login_timestamp >= to_date( ? || ? ||'00', 'yyyymmddhh24miss') and " +
                    "login_timestamp <= to_date( ? || ? ||'59', 'yyyymmddhh24miss') " +
                    " and ip_address = ?" +
                    " and branch_code like ?" +
                    " and account_code like ? || '%' ";
            String l_strSearchSortCondtion = " login_timestamp ASC,  ip_address DESC,  account_code ASC";
            
            String l_strInstitutionCode = "0D";
            String l_strSearchDate = "20080924";
            String l_strIpAddress = "192.168.255.21";
            String l_strBranchCode = "102";
            String l_strAccountCode = "102050";
            String l_strStartTime = "0900";
            String l_strEndTime = "1000";
            Object[] l_dataContainers = {
                    l_strInstitutionCode,
                    l_strSearchDate,
                    l_strStartTime,
                    l_strSearchDate,
                    l_strEndTime,
                    l_strIpAddress,
                    l_strBranchCode,
                    l_strAccountCode
                 };
            
            TestDBUtility.deleteAll(LoginHistoryPastRow.TYPE);
            LoginHistoryPastParams l_loginHistoryPastParams = TestDBUtility.getLoginHistoryPastRow();
            l_loginHistoryPastParams.setLoginTimestamp(WEB3DateUtility.getDate("20080924093000","yyyyMMddHHmmss"));
            l_loginHistoryPastParams.setAccountCode("1020501");
            TestDBUtility.insertWithDel(l_loginHistoryPastParams);
            
            Method l_method = WEB3AdminTMLoginProcessingListServiceImpl.class.getDeclaredMethod(
                "getLoginProessInfoList",
                new Class[]{String.class, String.class, Object[].class});
                l_method.setAccessible(true);
                Object[] l_obj = {l_strSearchCondition, l_strSearchSortCondtion, l_dataContainers};
                List l_strReturns = (ArrayList)l_method.invoke(l_impl, l_obj);
                LoginHistoryPastRow l_loginHistoryPastRow =
                    (LoginHistoryPastRow)l_strReturns.get(0);
                assertEquals("1020501", l_loginHistoryPastRow.getAccountCode());
                assertEquals("192.168.255.21", l_loginHistoryPastRow.getIpAddress());
                assertEquals("102", l_loginHistoryPastRow.getBranchCode());
                assertEquals("0D", l_loginHistoryPastRow.getInstitutionCode());
                assertEquals("2", l_loginHistoryPastRow.getOrderRootDiv());
        }
        catch(Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //検索結果が2件の場合
    //LoginHistoryPastRow.TYPE
    public void testGetLoginProessInfoList_C005()
    {
        final String STR_METHOD_NAME = "testGetLoginProessInfoList_C005()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            Date l_date = WEB3DateUtility.getDate("20080925133000", "yyyyMMddHHmmss");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(l_date.getTime()));
            
            TestDBUtility.deleteAll(LoginHistoryRow.TYPE);
            String l_strSearchCondition = "institution_code = ? and " +
                    "login_timestamp >= to_date( ? || ? ||'00', 'yyyymmddhh24miss') and " +
                    "login_timestamp <= to_date( ? || ? ||'59', 'yyyymmddhh24miss') " +
                    " and ip_address = ?" +
                    " and branch_code like ?" +
                    " and account_code like ? || '%' ";
            String l_strSearchSortCondtion = " login_timestamp ASC,  ip_address DESC,  account_code DESC";
            
            String l_strInstitutionCode = "0D";
            String l_strSearchDate = "20080924";
            String l_strIpAddress = "192.168.255.21";
            String l_strBranchCode = "102";
            String l_strAccountCode = "102050";
            String l_strStartTime = "0900";
            String l_strEndTime = "1000";
            Object[] l_dataContainers = {
                    l_strInstitutionCode,
                    l_strSearchDate,
                    l_strStartTime,
                    l_strSearchDate,
                    l_strEndTime,
                    l_strIpAddress,
                    l_strBranchCode,
                    l_strAccountCode
                 };
            
            TestDBUtility.deleteAll(LoginHistoryPastRow.TYPE);
            LoginHistoryPastParams l_loginHistoryPastParams1 = TestDBUtility.getLoginHistoryPastRow();
            l_loginHistoryPastParams1.setLoginTimestamp(WEB3DateUtility.getDate("20080924093000","yyyyMMddHHmmss"));
            l_loginHistoryPastParams1.setAccountCode("1020501");
            TestDBUtility.insertWithDel(l_loginHistoryPastParams1);
            
            LoginHistoryPastParams l_loginHistoryPastParams2 = TestDBUtility.getLoginHistoryPastRow();
            l_loginHistoryPastParams2.setLoginTimestamp(WEB3DateUtility.getDate("20080924093000","yyyyMMddHHmmss"));
            l_loginHistoryPastParams2.setLoginHistoryId(1002);
            l_loginHistoryPastParams2.setLoginFailure("0");
            l_loginHistoryPastParams2.setAccountCode("1020502");
            TestDBUtility.insertWithDel(l_loginHistoryPastParams2);
            
            Method l_method = WEB3AdminTMLoginProcessingListServiceImpl.class.getDeclaredMethod(
                "getLoginProessInfoList",
                new Class[]{String.class, String.class, Object[].class});
                l_method.setAccessible(true);
                Object[] l_obj = {l_strSearchCondition, l_strSearchSortCondtion, l_dataContainers};
                List l_strReturns = (ArrayList)l_method.invoke(l_impl, l_obj);
                LoginHistoryPastRow l_loginHistoryPastRow1 =
                    (LoginHistoryPastRow)l_strReturns.get(0);
                assertEquals(2, l_strReturns.size());
                assertEquals(1002, l_loginHistoryPastRow1.getLoginHistoryId());
                assertEquals("1020502", l_loginHistoryPastRow1.getAccountCode());
                assertEquals("192.168.255.21", l_loginHistoryPastRow1.getIpAddress());
                assertEquals("102", l_loginHistoryPastRow1.getBranchCode());
                assertEquals("0D", l_loginHistoryPastRow1.getInstitutionCode());
                assertEquals("2", l_loginHistoryPastRow1.getOrderRootDiv());
                assertEquals("0", l_loginHistoryPastRow1.getLoginFailure());
                
                LoginHistoryPastRow l_loginHistoryPastRow2 =
                    (LoginHistoryPastRow)l_strReturns.get(1);
                assertEquals(1001, l_loginHistoryPastRow2.getLoginHistoryId());
                assertEquals("1020501", l_loginHistoryPastRow2.getAccountCode());
                assertEquals("192.168.255.21", l_loginHistoryPastRow2.getIpAddress());
                assertEquals("102", l_loginHistoryPastRow2.getBranchCode());
                assertEquals("0D", l_loginHistoryPastRow2.getInstitutionCode());
                assertEquals("2", l_loginHistoryPastRow2.getOrderRootDiv()); 
                assertEquals("1", l_loginHistoryPastRow2.getLoginFailure());
        }
        catch(Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
//    List ページ毎ログイン処理情報一條
//    ログイン履歴テーブルRow
    public void testCreateLoginProessInfoList_C001()
    {
        final String STR_METHOD_NAME = "testCreateLoginProessInfoList_C001";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            List l_lisPagePrevLoginProcessInfos = new ArrayList();
            LoginHistoryParams l_loginHistoryParams = TestDBUtility.getLoginHistoryRow();
            l_loginHistoryParams.setAccountCode("1020501");
            LoginHistoryRow l_loginHistorRow = (LoginHistoryRow)l_loginHistoryParams;
            l_lisPagePrevLoginProcessInfos.add(l_loginHistorRow);
            
            Method l_method = WEB3AdminTMLoginProcessingListServiceImpl.class.getDeclaredMethod(
                "createLoginProessInfoList",
                 new Class[]{List.class});
                 l_method.setAccessible(true);
                 Object[] l_obj = {l_lisPagePrevLoginProcessInfos};
           WEB3AdminTraderAdminLoginHistoryReferenceUnit[] l_returns =
               (WEB3AdminTraderAdminLoginHistoryReferenceUnit[])l_method.invoke(l_impl, l_obj);

           assertEquals("1020501", l_returns[0].accountCode);
           assertEquals("102", l_returns[0].branchCode);
           assertEquals("192.168.255.21", l_returns[0].ipAddress); 
           assertEquals("1", l_returns[0].loginResult);
           assertEquals(WEB3DateUtility.getDate("20040716","yyyyMMdd"), l_returns[0].loginDate);
        }
        catch(Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
//  List ページ毎ログイン処理情報多條
//  ログイン履歴テーブルRow
  public void testCreateLoginProessInfoList_C002()
  {
      final String STR_METHOD_NAME = "testCreateLoginProessInfoList_C002";
      log.entering(STR_METHOD_NAME);
      
      try
      {
          List l_lisPagePrevLoginProcessInfos = new ArrayList();
          LoginHistoryParams l_loginHistoryParams = TestDBUtility.getLoginHistoryRow();
          l_loginHistoryParams.setAccountCode("1020501");
          LoginHistoryRow l_loginHistorRow = (LoginHistoryRow)l_loginHistoryParams;
          l_lisPagePrevLoginProcessInfos.add(l_loginHistorRow);
          
          LoginHistoryParams l_loginHistoryParams1 = TestDBUtility.getLoginHistoryRow();
          l_loginHistoryParams1.setAccountCode("1020502");
          l_loginHistoryParams1.setIpAddress("192.168.255.22");
          l_loginHistoryParams1.setLoginFailure("0");
          LoginHistoryRow l_loginHistorRow1 = (LoginHistoryRow)l_loginHistoryParams1;
          l_lisPagePrevLoginProcessInfos.add(l_loginHistorRow1);
          Method l_method = WEB3AdminTMLoginProcessingListServiceImpl.class.getDeclaredMethod(
              "createLoginProessInfoList",
               new Class[]{List.class});
               l_method.setAccessible(true);
               Object[] l_obj = {l_lisPagePrevLoginProcessInfos};
         WEB3AdminTraderAdminLoginHistoryReferenceUnit[] l_returns =
             (WEB3AdminTraderAdminLoginHistoryReferenceUnit[])l_method.invoke(l_impl, l_obj);

         assertEquals("1020501", l_returns[0].accountCode);
         assertEquals("102", l_returns[0].branchCode);
         assertEquals("192.168.255.21", l_returns[0].ipAddress); 
         assertEquals("1", l_returns[0].loginResult);
         assertEquals(WEB3DateUtility.getDate("20040716","yyyyMMdd"), l_returns[0].loginDate);
         
         assertEquals("1020502", l_returns[1].accountCode);
         assertEquals("102", l_returns[1].branchCode);
         assertEquals("192.168.255.22", l_returns[1].ipAddress); 
         assertEquals("0", l_returns[1].loginResult);
         assertEquals(WEB3DateUtility.getDate("20040716","yyyyMMdd"), l_returns[0].loginDate);
      }
      catch(Exception l_ex)
      {
          fail();
      }
      log.exiting(STR_METHOD_NAME);
  }
  
  //List ページ毎ログイン処理情報一條
  // ログイン(過去)履歴テーブルRow
  public void testCreateLoginProessInfoList_C003()
  {
      final String STR_METHOD_NAME = "testCreateLoginProessInfoList_C001";
      log.entering(STR_METHOD_NAME);
      
      try
      {
          List l_lisPagePrevLoginProcessInfos = new ArrayList();
          LoginHistoryPastParams l_loginHistoryPastParams = TestDBUtility.getLoginHistoryPastRow();
          l_loginHistoryPastParams.setAccountCode("1020501");
          LoginHistoryPastRow l_loginHistoryPastRow = (LoginHistoryPastRow)l_loginHistoryPastParams;
          l_lisPagePrevLoginProcessInfos.add(l_loginHistoryPastRow);
          
          Method l_method = WEB3AdminTMLoginProcessingListServiceImpl.class.getDeclaredMethod(
              "createLoginProessInfoList",
               new Class[]{List.class});
               l_method.setAccessible(true);
               Object[] l_obj = {l_lisPagePrevLoginProcessInfos};
         WEB3AdminTraderAdminLoginHistoryReferenceUnit[] l_returns =
             (WEB3AdminTraderAdminLoginHistoryReferenceUnit[])l_method.invoke(l_impl, l_obj);
  
         assertEquals("1020501", l_returns[0].accountCode);
         assertEquals("102", l_returns[0].branchCode);
         assertEquals("192.168.255.21", l_returns[0].ipAddress); 
         assertEquals("1", l_returns[0].loginResult);
         assertEquals(WEB3DateUtility.getDate("20040716","yyyyMMdd"), l_returns[0].loginDate);
      }
      catch(Exception l_ex)
      {
          fail();
      }
      log.exiting(STR_METHOD_NAME);
  }
  
  //List ページ毎ログイン処理情報多條
  // ログイン(過去)履歴テーブルRow
  public void testCreateLoginProessInfoList_C004()
  {
    final String STR_METHOD_NAME = "testCreateLoginProessInfoList_C004";
    log.entering(STR_METHOD_NAME);
    
    try
    {
        List l_lisPagePrevLoginProcessInfos = new ArrayList();
        LoginHistoryPastParams l_loginHistoryPastParams = TestDBUtility.getLoginHistoryPastRow();
        l_loginHistoryPastParams.setAccountCode("1020501");
        LoginHistoryPastRow l_loginHistorPastRow = (LoginHistoryPastRow)l_loginHistoryPastParams;
        l_lisPagePrevLoginProcessInfos.add(l_loginHistorPastRow);
        
        LoginHistoryPastParams l_loginHistoryPastParams1 = TestDBUtility.getLoginHistoryPastRow();
        l_loginHistoryPastParams1.setAccountCode("1020502");
        l_loginHistoryPastParams1.setIpAddress("192.168.255.22");
        l_loginHistoryPastParams1.setLoginFailure("0");
        LoginHistoryPastRow l_loginHistoryPastRow1 = (LoginHistoryPastRow)l_loginHistoryPastParams1;
        l_lisPagePrevLoginProcessInfos.add(l_loginHistoryPastRow1);
        Method l_method = WEB3AdminTMLoginProcessingListServiceImpl.class.getDeclaredMethod(
            "createLoginProessInfoList",
             new Class[]{List.class});
             l_method.setAccessible(true);
             Object[] l_obj = {l_lisPagePrevLoginProcessInfos};
       WEB3AdminTraderAdminLoginHistoryReferenceUnit[] l_returns =
           (WEB3AdminTraderAdminLoginHistoryReferenceUnit[])l_method.invoke(l_impl, l_obj);
  
       assertEquals("1020501", l_returns[0].accountCode);
       assertEquals("102", l_returns[0].branchCode);
       assertEquals("192.168.255.21", l_returns[0].ipAddress); 
       assertEquals("1", l_returns[0].loginResult);
       assertEquals(WEB3DateUtility.getDate("20040716","yyyyMMdd"), l_returns[0].loginDate);
       
       assertEquals("1020502", l_returns[1].accountCode);
       assertEquals("102", l_returns[1].branchCode);
       assertEquals("192.168.255.22", l_returns[1].ipAddress); 
       assertEquals("0", l_returns[1].loginResult);
       assertEquals(WEB3DateUtility.getDate("20040716","yyyyMMdd"), l_returns[0].loginDate);
    }
    catch(Exception l_ex)
    {
        fail();
    }
    log.exiting(STR_METHOD_NAME);
  }
  private class WEB3AdminTMLoginProcessingListServiceImplForTest extends
      WEB3AdminTMLoginProcessingListServiceImpl
  {
      protected WEB3AdminTraderAdminLoginHistoryListResponse getSearchResultScreen(
          WEB3AdminTraderAdminLoginHistoryListRequest l_request) throws WEB3BaseException
      {
          WEB3AdminTraderAdminLoginHistoryListResponse l_response =
              new WEB3AdminTraderAdminLoginHistoryListResponse();
          l_response.pageIndex = "2";
          l_response.totalPages = "20";
          l_response.totalRecords = "100";
          return l_response;
      }
 
  }
}
@
