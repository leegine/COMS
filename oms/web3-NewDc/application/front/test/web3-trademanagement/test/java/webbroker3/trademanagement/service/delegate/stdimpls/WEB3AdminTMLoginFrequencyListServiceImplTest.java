head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.49.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminTMLoginFrequencyListServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IP別ログイン回数一覧サービスImplTest(WEB3AdminTMLoginFrequencyListServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/22 劉剣(中訊) 新規作成
*/

package webbroker3.trademanagement.service.delegate.stdimpls;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginTypeInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdminPermissionRow;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.gentrade.data.LoginHistoryParams;
import webbroker3.gentrade.data.LoginHistoryPastParams;
import webbroker3.gentrade.data.LoginHistoryPastRow;
import webbroker3.gentrade.data.LoginHistoryRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlListRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginCountInputRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginCountInputResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginCountListRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginCountListResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginCountReferenceUnit;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminTMLoginFrequencyListServiceImplTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AdminTMLoginFrequencyListServiceImplTest.class);
    
    private WEB3AdminTMLoginFrequencyListServiceImpl l_impl = null;
    
    public WEB3AdminTMLoginFrequencyListServiceImplTest(String arg0)
    {
        super(arg0);
    }
    
    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.l_impl = new WEB3AdminTMLoginFrequencyListServiceImpl();
    }
    
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    /*
     * パラメータ値不正。
     * WEB3ErrorCatalog.SYSTEM_ERROR_80017
     */
    public void testExecute_C0001()
    {
        String STR_METHOD_NAME = "testExecute_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3GenRequest l_request = null;
            
            l_impl.execute(l_request);
            
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /*
     * ○管理者・IP別ログイン回数一覧検索入力リクエストの場合
     */
    public void testExecute_C0002()
    {
        String STR_METHOD_NAME = "testExecute_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3AdminTraderAdminLoginCountInputRequest l_request = new WEB3AdminTraderAdminLoginCountInputRequest();
            
            WEB3AdminTMLoginFrequencyListServiceImpl l_implForTest =
                new WEB3AdminTMLoginFrequencyListServiceImplForTest();
            WEB3AdminTraderAdminLoginCountInputResponse l_response =
                (WEB3AdminTraderAdminLoginCountInputResponse)l_implForTest.execute(l_request);
            
            assertEquals("101", l_response.errorMessage);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /*
     * ○管理者・IP別ログイン回数一覧検索結果リクエストの場合
     */
    public void testExecute_C0003()
    {
        String STR_METHOD_NAME = "testExecute_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3AdminTraderAdminLoginCountListRequest l_request = new WEB3AdminTraderAdminLoginCountListRequest();
            
            WEB3AdminTMLoginFrequencyListServiceImpl l_implForTest =
                new WEB3AdminTMLoginFrequencyListServiceImplForTest();
            WEB3AdminTraderAdminLoginCountListResponse l_response =
                (WEB3AdminTraderAdminLoginCountListResponse)l_implForTest.execute(l_request);
            
            assertEquals("202", l_response.pageIndex);
            assertEquals("303", l_response.totalPages);
            assertEquals("404", l_response.totalRecords);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /*
     * パラメータタイプ不正
     * SYSTEM_ERROR_80018
     */
    public void testExecute_C0004()
    {
        String STR_METHOD_NAME = "testExecute_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3AdminTraderAdminIPControlListRequest l_request = new WEB3AdminTraderAdminIPControlListRequest();
            
            l_impl.execute(l_request);
            
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /*
     * validate権限失敗
     * WEB3ErrorCatalog.BUSINESS_ERROR_01056
     */
    public void testGetSearchInputScreen_C0001()
    {
        String STR_METHOD_NAME = "testGetSearchInputScreen_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        LoginInfo l_loginInfo = new LoginInfoForTest();
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getLoginInfo",
            new Class[] {},
            l_loginInfo);
        
        try
        {
            WEB3AdminTraderAdminLoginCountInputRequest l_request = new WEB3AdminTraderAdminLoginCountInputRequest();
            
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(1001);
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setPermissionLevel("331");
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            
            l_impl.getSearchInputScreen(l_request);
            
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01056, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /*
     * normal case
     */
    public void testGetSearchInputScreen_C0002()
    {
        String STR_METHOD_NAME = "testGetSearchInputScreen_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        LoginInfo l_loginInfo = new LoginInfoForTest();
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getLoginInfo",
            new Class[] {},
            l_loginInfo);
        
        try
        {
            WEB3AdminTraderAdminLoginCountInputRequest l_request = new WEB3AdminTraderAdminLoginCountInputRequest();
            
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(1001);
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setPermissionLevel("331");
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C1301");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.FALSE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            l_impl.getSearchInputScreen(l_request);
            
            assertTrue(true);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /*
     * ※検索結果が0件の場合、エラーを返却する。
     * WEB3ErrorCatalog.BUSINESS_ERROR_01037
     */
    public void testGetObjectIPAddressProcess_C0001()
    {
        String STR_METHOD_NAME = "testGetObjectIPAddressProcess_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            String l_strInstitutionCode = "10";
            String l_strSearchDate = "20080808";
            String l_strStartTime = "1000";
            String l_strEndTime = "2000";
            
            TestDBUtility.deleteAll(LoginHistoryPastRow.TYPE);
            TestDBUtility.deleteAll(LoginHistoryRow.TYPE);
            
            Method l_method = WEB3AdminTMLoginFrequencyListServiceImpl.class.getDeclaredMethod(
                "getObjectIPAddressProcess",
                new Class[]{String.class, String.class, String.class, String.class});
            l_method.setAccessible(true);
            Object[] l_obj = {l_strInstitutionCode, l_strSearchDate, l_strStartTime, l_strEndTime};
            l_method.invoke(l_impl, l_obj);
            
            fail();
        }
        catch (InvocationTargetException l_exITE)
        {
            log.debug(STR_METHOD_NAME, l_exITE);
            WEB3BusinessLayerException l_targetException = (WEB3BusinessLayerException)l_exITE.getTargetException();
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01037, l_targetException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /*
     * ログイン過去履歴テーブルRow的場合
     */
    public void testGetObjectIPAddressProcess_C0002()
    {
        String STR_METHOD_NAME = "testGetObjectIPAddressProcess_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        Date l_datExpect = WEB3DateUtility.getDate("20080807","yyyyMMdd");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
            "getSystemTimestamp",
            new Class[] {},
            new Timestamp(l_datExpect.getTime()));
        
        try
        {
            String l_strInstitutionCode = "10";
            String l_strSearchDate = "20080808";
            String l_strStartTime = "1000";
            String l_strEndTime = "2000";
            
            TestDBUtility.deleteAll(LoginHistoryPastRow.TYPE);
            LoginHistoryPastParams l_loginHistoryPastParams = new LoginHistoryPastParams();
            l_loginHistoryPastParams.setLoginHistoryId(1001);
            l_loginHistoryPastParams.setInstitutionCode("10");
            l_loginHistoryPastParams.setBranchCode("20");
            l_loginHistoryPastParams.setAccountCode("30");
            l_loginHistoryPastParams.setAccountId(2001);
            l_loginHistoryPastParams.setOrderRootDiv("1");
            l_loginHistoryPastParams.setOrderChannel("0");
            l_loginHistoryPastParams.setLoginTimestamp(WEB3DateUtility.getDate("20080808120000","yyyyMMddHHmmss"));
            l_loginHistoryPastParams.setLoginFailure("0");
            l_loginHistoryPastParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_loginHistoryPastParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_loginHistoryPastParams.setIpAddress("192.168.1.1");
            TestDBUtility.insertWithDel(l_loginHistoryPastParams);
            
            Method l_method = WEB3AdminTMLoginFrequencyListServiceImpl.class.getDeclaredMethod(
                "getObjectIPAddressProcess",
                new Class[]{String.class, String.class, String.class, String.class});
            l_method.setAccessible(true);
            Object[] l_obj = {l_strInstitutionCode, l_strSearchDate, l_strStartTime, l_strEndTime};
            HashSet l_hsReturns = (HashSet)l_method.invoke(l_impl, l_obj);
            
            String l_strIt = null;
            Iterator l_it = l_hsReturns.iterator();
            while (l_it.hasNext())
            {
                l_strIt = l_it.next().toString();
            }
            
            assertEquals("192.168.1.1", l_strIt);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /*
     * ログイン履歴テーブルRow的場合
     */
    public void testGetObjectIPAddressProcess_C0003()
    {
        String STR_METHOD_NAME = "testGetObjectIPAddressProcess_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        Date l_datExpect = WEB3DateUtility.getDate("20080808","yyyyMMdd");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
            "getSystemTimestamp",
            new Class[] {},
            new Timestamp(l_datExpect.getTime()));
        
        try
        {
            String l_strInstitutionCode = "10";
            String l_strSearchDate = "20080808";
            String l_strStartTime = "0100";
            String l_strEndTime = "2000";
            
            TestDBUtility.deleteAll(LoginHistoryRow.TYPE);
            LoginHistoryParams l_loginHistoryParams = new LoginHistoryParams();
            l_loginHistoryParams.setLoginHistoryId(1001);
            l_loginHistoryParams.setInstitutionCode("10");
            l_loginHistoryParams.setBranchCode("20");
            l_loginHistoryParams.setAccountCode("30");
            l_loginHistoryParams.setAccountId(2001);
            l_loginHistoryParams.setOrderRootDiv("1");
            l_loginHistoryParams.setOrderChannel("0");
            l_loginHistoryParams.setLoginTimestamp(WEB3DateUtility.getDate("20080808120000","yyyyMMddHHmmss"));
            l_loginHistoryParams.setLoginFailure("0");
            l_loginHistoryParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_loginHistoryParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_loginHistoryParams.setIpAddress("192.168.1.2");
            TestDBUtility.insertWithDel(l_loginHistoryParams);
            
            Method l_method = WEB3AdminTMLoginFrequencyListServiceImpl.class.getDeclaredMethod(
                "getObjectIPAddressProcess",
                new Class[]{String.class, String.class, String.class, String.class});
            l_method.setAccessible(true);
            Object[] l_obj = {l_strInstitutionCode, l_strSearchDate, l_strStartTime, l_strEndTime};
            HashSet l_hsReturns = (HashSet)l_method.invoke(l_impl, l_obj);
            
            String l_strIt = null;
            Iterator l_it = l_hsReturns.iterator();
            while (l_it.hasNext())
            {
                l_strIt = l_it.next().toString();
            }
            
            assertEquals("192.168.1.2", l_strIt);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /*
     * normal case
     */
    public void testDeclarationFieldVariable_C0001()
    {
        String STR_METHOD_NAME = "testDeclarationFieldVariable_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            HashSet l_hsIPAddressList = new HashSet();
            l_hsIPAddressList.add("192.168.1.1");
            l_hsIPAddressList.add("192.168.1.2");
            l_hsIPAddressList.add("192.168.1.3");
            
            ArrayList loginProcessingFrequencyList = new ArrayList();
            Field l_field =
                WEB3AdminTMLoginFrequencyListServiceImpl.class.getDeclaredField("loginProcessingFrequencyList");
            l_field.setAccessible(true);
            l_field.set(l_impl, loginProcessingFrequencyList);
            
            HashMap ipAddressLoginProcessingFrequency = new HashMap();
            Field l_field1 =
                WEB3AdminTMLoginFrequencyListServiceImpl.class.getDeclaredField("ipAddressLoginProcessingFrequency");
            l_field1.setAccessible(true);
            l_field1.set(l_impl, ipAddressLoginProcessingFrequency);
            
            double duplicateCounter = 0.000001;
            Field l_field2 =
                WEB3AdminTMLoginFrequencyListServiceImpl.class.getDeclaredField("duplicateCounter");
            l_field2.setAccessible(true);
            l_field2.set(l_impl, new Double(duplicateCounter));
            
            Method l_method = WEB3AdminTMLoginFrequencyListServiceImpl.class.getDeclaredMethod(
                "declarationFieldVariable",
                new Class[]{HashSet.class});
            l_method.setAccessible(true);
            Object[] l_obj = {l_hsIPAddressList};
            l_method.invoke(l_impl, l_obj);
            
            ArrayList l_alChecks = (ArrayList)l_field.get(l_impl);
            HashMap l_hmChecks= (HashMap)l_field1.get(l_impl);
            Double l_dbChecks = (Double)l_field2.get(l_impl);
            
            assertEquals(0, l_alChecks.size());
            assertEquals(0, l_hmChecks.size());
            assertEquals(new Double("0.000001"), l_dbChecks);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /*
     * ログイン過去履歴テーブルRow的場合
     */
    public void testCreateIPLoginProcessingFrequency_C0001()
    {
        String STR_METHOD_NAME = "testCreateIPLoginProcessingFrequency_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        Date l_datExpect = WEB3DateUtility.getDate("20080807","yyyyMMdd");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
            "getSystemTimestamp",
            new Class[] {},
            new Timestamp(l_datExpect.getTime()));
        
        try
        {
            String l_strInstitutionCode = "10";
            String l_strSearchDate = "20080808";
            String l_strStartTime = "1000";
            String l_strEndTime = "2000";
            HashSet l_hsIPAddressList = new HashSet();
            l_hsIPAddressList.add("192.168.1.1");
            l_hsIPAddressList.add("192.168.1.2");
            l_hsIPAddressList.add("192.168.1.3");
            
            TestDBUtility.deleteAll(LoginHistoryPastRow.TYPE);
            LoginHistoryPastParams l_loginHistoryPastParams = new LoginHistoryPastParams();
            l_loginHistoryPastParams.setLoginHistoryId(1001);
            l_loginHistoryPastParams.setInstitutionCode("10");
            l_loginHistoryPastParams.setBranchCode("20");
            l_loginHistoryPastParams.setAccountCode("30");
            l_loginHistoryPastParams.setAccountId(2001);
            l_loginHistoryPastParams.setOrderRootDiv("1");
            l_loginHistoryPastParams.setOrderChannel("0");
            l_loginHistoryPastParams.setLoginTimestamp(WEB3DateUtility.getDate("20080808120000","yyyyMMddHHmmss"));
            l_loginHistoryPastParams.setLoginFailure("0");
            l_loginHistoryPastParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_loginHistoryPastParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_loginHistoryPastParams.setIpAddress("192.168.1.1");
            TestDBUtility.insertWithDel(l_loginHistoryPastParams);
            
            LoginHistoryPastParams l_loginHistoryPastParams1 = new LoginHistoryPastParams();
            l_loginHistoryPastParams1.setLoginHistoryId(1002);
            l_loginHistoryPastParams1.setInstitutionCode("10");
            l_loginHistoryPastParams1.setBranchCode("20");
            l_loginHistoryPastParams1.setAccountCode("30");
            l_loginHistoryPastParams1.setAccountId(2001);
            l_loginHistoryPastParams1.setOrderRootDiv("1");
            l_loginHistoryPastParams1.setOrderChannel("0");
            l_loginHistoryPastParams1.setLoginTimestamp(WEB3DateUtility.getDate("20080808120000","yyyyMMddHHmmss"));
            l_loginHistoryPastParams1.setLoginFailure("0");
            l_loginHistoryPastParams1.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_loginHistoryPastParams1.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_loginHistoryPastParams1.setIpAddress("192.168.1.2");
            TestDBUtility.insertWithDel(l_loginHistoryPastParams1);
            
            LoginHistoryPastParams l_loginHistoryPastParams2 = new LoginHistoryPastParams();
            l_loginHistoryPastParams2.setLoginHistoryId(1003);
            l_loginHistoryPastParams2.setInstitutionCode("10");
            l_loginHistoryPastParams2.setBranchCode("20");
            l_loginHistoryPastParams2.setAccountCode("30");
            l_loginHistoryPastParams2.setAccountId(2001);
            l_loginHistoryPastParams2.setOrderRootDiv("1");
            l_loginHistoryPastParams2.setOrderChannel("0");
            l_loginHistoryPastParams2.setLoginTimestamp(WEB3DateUtility.getDate("20080808120000","yyyyMMddHHmmss"));
            l_loginHistoryPastParams2.setLoginFailure("1");
            l_loginHistoryPastParams2.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_loginHistoryPastParams2.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_loginHistoryPastParams2.setIpAddress("192.168.1.2");
            TestDBUtility.insertWithDel(l_loginHistoryPastParams2);
            
            LoginHistoryPastParams l_loginHistoryPastParams3 = new LoginHistoryPastParams();
            l_loginHistoryPastParams3.setLoginHistoryId(1004);
            l_loginHistoryPastParams3.setInstitutionCode("10");
            l_loginHistoryPastParams3.setBranchCode("20");
            l_loginHistoryPastParams3.setAccountCode("30");
            l_loginHistoryPastParams3.setAccountId(2001);
            l_loginHistoryPastParams3.setOrderRootDiv("1");
            l_loginHistoryPastParams3.setOrderChannel("0");
            l_loginHistoryPastParams3.setLoginTimestamp(WEB3DateUtility.getDate("20080808120000","yyyyMMddHHmmss"));
            l_loginHistoryPastParams3.setLoginFailure("0");
            l_loginHistoryPastParams3.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_loginHistoryPastParams3.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_loginHistoryPastParams3.setIpAddress("192.168.1.3");
            TestDBUtility.insertWithDel(l_loginHistoryPastParams3);
            
            LoginHistoryPastParams l_loginHistoryPastParams4 = new LoginHistoryPastParams();
            l_loginHistoryPastParams4.setLoginHistoryId(1005);
            l_loginHistoryPastParams4.setInstitutionCode("10");
            l_loginHistoryPastParams4.setBranchCode("20");
            l_loginHistoryPastParams4.setAccountCode("30");
            l_loginHistoryPastParams4.setAccountId(2001);
            l_loginHistoryPastParams4.setOrderRootDiv("1");
            l_loginHistoryPastParams4.setOrderChannel("0");
            l_loginHistoryPastParams4.setLoginTimestamp(WEB3DateUtility.getDate("20080808120000","yyyyMMddHHmmss"));
            l_loginHistoryPastParams4.setLoginFailure("1");
            l_loginHistoryPastParams4.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_loginHistoryPastParams4.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_loginHistoryPastParams4.setIpAddress("192.168.1.3");
            TestDBUtility.insertWithDel(l_loginHistoryPastParams4);
            
            ArrayList loginProcessingFrequencyList = new ArrayList();
            Field l_field =
                WEB3AdminTMLoginFrequencyListServiceImpl.class.getDeclaredField("loginProcessingFrequencyList");
            l_field.setAccessible(true);
            l_field.set(l_impl, loginProcessingFrequencyList);
            
            HashMap ipAddressLoginProcessingFrequency = new HashMap();
            Field l_field1 =
                WEB3AdminTMLoginFrequencyListServiceImpl.class.getDeclaredField("ipAddressLoginProcessingFrequency");
            l_field1.setAccessible(true);
            l_field1.set(l_impl, ipAddressLoginProcessingFrequency);
            
            double duplicateCounter = 0.000001;
            Field l_field2 =
                WEB3AdminTMLoginFrequencyListServiceImpl.class.getDeclaredField("duplicateCounter");
            l_field2.setAccessible(true);
            l_field2.set(l_impl, new Double(duplicateCounter));
            
            Method l_method = WEB3AdminTMLoginFrequencyListServiceImpl.class.getDeclaredMethod(
                "createIPLoginProcessingFrequency",
                new Class[]{String.class, String.class, String.class, String.class, HashSet.class});
            l_method.setAccessible(true);
            Object[] l_obj = {l_strInstitutionCode, l_strSearchDate, l_strStartTime, l_strEndTime, l_hsIPAddressList};
            l_method.invoke(l_impl, l_obj);
            
            HashMap l_hmChecks = (HashMap)l_field1.get(l_impl);
            ArrayList l_alChecks = (ArrayList)l_field.get(l_impl);
            
            assertEquals("192.168.1.2", l_hmChecks.get(new Double(2.0)));
            assertEquals("192.168.1.3", l_hmChecks.get(new Double(2.000001)));
            assertEquals("192.168.1.1", l_hmChecks.get(new Double(1.0)));
            assertEquals(3, l_hmChecks.size());
            assertEquals("2", l_alChecks.get(0));
            assertEquals("2.000001", l_alChecks.get(1));
            assertEquals("1", l_alChecks.get(2));
            assertEquals(3, l_alChecks.size());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /*
     * ログイン履歴テーブルRow的場合
     */
    public void testCreateIPLoginProcessingFrequency_C0002()
    {
        String STR_METHOD_NAME = "testCreateIPLoginProcessingFrequency_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        Date l_datExpect = WEB3DateUtility.getDate("20080808","yyyyMMdd");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
            "getSystemTimestamp",
            new Class[] {},
            new Timestamp(l_datExpect.getTime()));
        
        try
        {
            String l_strInstitutionCode = "10";
            String l_strSearchDate = "20080808";
            String l_strStartTime = "1000";
            String l_strEndTime = "2000";
            HashSet l_hsIPAddressList = new HashSet();
            l_hsIPAddressList.add("192.168.1.1");
            l_hsIPAddressList.add("192.168.1.2");
            l_hsIPAddressList.add("192.168.1.3");
            
            TestDBUtility.deleteAll(LoginHistoryRow.TYPE);
            LoginHistoryParams l_loginHistoryParams = new LoginHistoryParams();
            l_loginHistoryParams.setLoginHistoryId(1001);
            l_loginHistoryParams.setInstitutionCode("10");
            l_loginHistoryParams.setBranchCode("20");
            l_loginHistoryParams.setAccountCode("30");
            l_loginHistoryParams.setAccountId(2001);
            l_loginHistoryParams.setOrderRootDiv("1");
            l_loginHistoryParams.setOrderChannel("0");
            l_loginHistoryParams.setLoginTimestamp(WEB3DateUtility.getDate("20080808120000","yyyyMMddHHmmss"));
            l_loginHistoryParams.setLoginFailure("0");
            l_loginHistoryParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_loginHistoryParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_loginHistoryParams.setIpAddress("192.168.1.1");
            TestDBUtility.insertWithDel(l_loginHistoryParams);
            
            LoginHistoryParams l_loginHistoryParams1 = new LoginHistoryParams();
            l_loginHistoryParams1.setLoginHistoryId(1002);
            l_loginHistoryParams1.setInstitutionCode("10");
            l_loginHistoryParams1.setBranchCode("20");
            l_loginHistoryParams1.setAccountCode("30");
            l_loginHistoryParams1.setAccountId(2001);
            l_loginHistoryParams1.setOrderRootDiv("1");
            l_loginHistoryParams1.setOrderChannel("0");
            l_loginHistoryParams1.setLoginTimestamp(WEB3DateUtility.getDate("20080808120000","yyyyMMddHHmmss"));
            l_loginHistoryParams1.setLoginFailure("0");
            l_loginHistoryParams1.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_loginHistoryParams1.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_loginHistoryParams1.setIpAddress("192.168.1.2");
            TestDBUtility.insertWithDel(l_loginHistoryParams1);
            
            LoginHistoryParams l_loginHistoryParams2 = new LoginHistoryParams();
            l_loginHistoryParams2.setLoginHistoryId(1003);
            l_loginHistoryParams2.setInstitutionCode("10");
            l_loginHistoryParams2.setBranchCode("20");
            l_loginHistoryParams2.setAccountCode("30");
            l_loginHistoryParams2.setAccountId(2001);
            l_loginHistoryParams2.setOrderRootDiv("1");
            l_loginHistoryParams2.setOrderChannel("0");
            l_loginHistoryParams2.setLoginTimestamp(WEB3DateUtility.getDate("20080808120000","yyyyMMddHHmmss"));
            l_loginHistoryParams2.setLoginFailure("1");
            l_loginHistoryParams2.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_loginHistoryParams2.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_loginHistoryParams2.setIpAddress("192.168.1.2");
            TestDBUtility.insertWithDel(l_loginHistoryParams2);
            
            LoginHistoryParams l_loginHistoryParams3 = new LoginHistoryParams();
            l_loginHistoryParams3.setLoginHistoryId(1004);
            l_loginHistoryParams3.setInstitutionCode("10");
            l_loginHistoryParams3.setBranchCode("20");
            l_loginHistoryParams3.setAccountCode("30");
            l_loginHistoryParams3.setAccountId(2001);
            l_loginHistoryParams3.setOrderRootDiv("1");
            l_loginHistoryParams3.setOrderChannel("0");
            l_loginHistoryParams3.setLoginTimestamp(WEB3DateUtility.getDate("20080808120000","yyyyMMddHHmmss"));
            l_loginHistoryParams3.setLoginFailure("0");
            l_loginHistoryParams3.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_loginHistoryParams3.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_loginHistoryParams3.setIpAddress("192.168.1.3");
            TestDBUtility.insertWithDel(l_loginHistoryParams3);
            
            LoginHistoryParams l_loginHistoryParams4 = new LoginHistoryParams();
            l_loginHistoryParams4.setLoginHistoryId(1005);
            l_loginHistoryParams4.setInstitutionCode("10");
            l_loginHistoryParams4.setBranchCode("20");
            l_loginHistoryParams4.setAccountCode("30");
            l_loginHistoryParams4.setAccountId(2001);
            l_loginHistoryParams4.setOrderRootDiv("1");
            l_loginHistoryParams4.setOrderChannel("0");
            l_loginHistoryParams4.setLoginTimestamp(WEB3DateUtility.getDate("20080808120000","yyyyMMddHHmmss"));
            l_loginHistoryParams4.setLoginFailure("1");
            l_loginHistoryParams4.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_loginHistoryParams4.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            l_loginHistoryParams4.setIpAddress("192.168.1.3");
            TestDBUtility.insertWithDel(l_loginHistoryParams4);
            
            ArrayList loginProcessingFrequencyList = new ArrayList();
            Field l_field =
                WEB3AdminTMLoginFrequencyListServiceImpl.class.getDeclaredField("loginProcessingFrequencyList");
            l_field.setAccessible(true);
            l_field.set(l_impl, loginProcessingFrequencyList);
            
            HashMap ipAddressLoginProcessingFrequency = new HashMap();
            Field l_field1 =
                WEB3AdminTMLoginFrequencyListServiceImpl.class.getDeclaredField("ipAddressLoginProcessingFrequency");
            l_field1.setAccessible(true);
            l_field1.set(l_impl, ipAddressLoginProcessingFrequency);
            
            double duplicateCounter = 0.000001;
            Field l_field2 =
                WEB3AdminTMLoginFrequencyListServiceImpl.class.getDeclaredField("duplicateCounter");
            l_field2.setAccessible(true);
            l_field2.set(l_impl, new Double(duplicateCounter));
            
            Method l_method = WEB3AdminTMLoginFrequencyListServiceImpl.class.getDeclaredMethod(
                "createIPLoginProcessingFrequency",
                new Class[]{String.class, String.class, String.class, String.class, HashSet.class});
            l_method.setAccessible(true);
            Object[] l_obj = {l_strInstitutionCode, l_strSearchDate, l_strStartTime, l_strEndTime, l_hsIPAddressList};
            l_method.invoke(l_impl, l_obj);
            
            HashMap l_hmChecks = (HashMap)l_field1.get(l_impl);
            ArrayList l_alChecks = (ArrayList)l_field.get(l_impl);
            
            assertEquals("192.168.1.2", l_hmChecks.get(new Double(2.0)));
            assertEquals("192.168.1.3", l_hmChecks.get(new Double(2.000001)));
            assertEquals("192.168.1.1", l_hmChecks.get(new Double(1.0)));
            assertEquals(3, l_hmChecks.size());
            assertEquals("2", l_alChecks.get(0));
            assertEquals("2.000001", l_alChecks.get(1));
            assertEquals("1", l_alChecks.get(2));
            assertEquals(3, l_alChecks.size());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /*
     * 「ログイン処理回数一覧」ArrayListのsize > 0
     * 重複フラグ = true(重複あり)の場合
     */
    public void testSetIPLoginProcessingFrequency_C0001()
    {
        String STR_METHOD_NAME = "testSetIPLoginProcessingFrequency_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            ArrayList loginProcessingFrequencyList = new ArrayList();
            loginProcessingFrequencyList.add("5");
            loginProcessingFrequencyList.add("6");
            loginProcessingFrequencyList.add("7");
            Field l_field =
                WEB3AdminTMLoginFrequencyListServiceImpl.class.getDeclaredField("loginProcessingFrequencyList");
            l_field.setAccessible(true);
            l_field.set(l_impl, loginProcessingFrequencyList);
            
            HashMap ipAddressLoginProcessingFrequency = new HashMap();
            Field l_field1 =
                WEB3AdminTMLoginFrequencyListServiceImpl.class.getDeclaredField("ipAddressLoginProcessingFrequency");
            l_field1.setAccessible(true);
            l_field1.set(l_impl, ipAddressLoginProcessingFrequency);
            
            double duplicateCounter = 0.000001;
            Field l_field2 =
                WEB3AdminTMLoginFrequencyListServiceImpl.class.getDeclaredField("duplicateCounter");
            l_field2.setAccessible(true);
            l_field2.set(l_impl, new Double(duplicateCounter));
            
            int l_intIPAddressEveryLoginProcessingFrequency = 5;
            String l_strIPAddress = "192.168.1.4";
            
            Method l_method = WEB3AdminTMLoginFrequencyListServiceImpl.class.getDeclaredMethod(
                "setIPLoginProcessingFrequency",
                new Class[]{int.class, String.class});
            l_method.setAccessible(true);
            Object[] l_obj = {new Integer(l_intIPAddressEveryLoginProcessingFrequency), l_strIPAddress};
            l_method.invoke(l_impl, l_obj);
            
            HashMap l_hmChecks = (HashMap)l_field1.get(l_impl);
            ArrayList l_alChecks = (ArrayList)l_field.get(l_impl);
            
            assertEquals("192.168.1.4", l_hmChecks.get(new Double(5.000001)));
            assertEquals(1, l_hmChecks.size());
            assertEquals("5.000001", l_alChecks.get(3));
            assertEquals(4, l_alChecks.size());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /*
     * 「ログイン処理回数一覧」ArrayListのsize > 0
     * 重複フラグ = false(重複なし)の場合
     */
    public void testSetIPLoginProcessingFrequency_C0002()
    {
        String STR_METHOD_NAME = "testSetIPLoginProcessingFrequency_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            ArrayList loginProcessingFrequencyList = new ArrayList();
            loginProcessingFrequencyList.add("5");
            loginProcessingFrequencyList.add("6");
            loginProcessingFrequencyList.add("7");
            Field l_field =
                WEB3AdminTMLoginFrequencyListServiceImpl.class.getDeclaredField("loginProcessingFrequencyList");
            l_field.setAccessible(true);
            l_field.set(l_impl, loginProcessingFrequencyList);
            
            HashMap ipAddressLoginProcessingFrequency = new HashMap();
            Field l_field1 =
                WEB3AdminTMLoginFrequencyListServiceImpl.class.getDeclaredField("ipAddressLoginProcessingFrequency");
            l_field1.setAccessible(true);
            l_field1.set(l_impl, ipAddressLoginProcessingFrequency);
            
            int l_intIPAddressEveryLoginProcessingFrequency = 8;
            String l_strIPAddress = "192.168.1.4";
            
            Method l_method = WEB3AdminTMLoginFrequencyListServiceImpl.class.getDeclaredMethod(
                "setIPLoginProcessingFrequency",
                new Class[]{int.class, String.class});
            l_method.setAccessible(true);
            Object[] l_obj = {new Integer(l_intIPAddressEveryLoginProcessingFrequency), l_strIPAddress};
            l_method.invoke(l_impl, l_obj);
            
            HashMap l_hmChecks = (HashMap)l_field1.get(l_impl);
            ArrayList l_alChecks = (ArrayList)l_field.get(l_impl);
            
            assertEquals("192.168.1.4", l_hmChecks.get(new Double(8.0)));
            assertEquals(1, l_hmChecks.size());
            assertEquals("8", l_alChecks.get(3));
            assertEquals(4, l_alChecks.size());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /*
     * 「ログイン処理回数一覧」ArrayListのsize = 0
     */
    public void testSetIPLoginProcessingFrequency_C0003()
    {
        String STR_METHOD_NAME = "testSetIPLoginProcessingFrequency_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            ArrayList loginProcessingFrequencyList = new ArrayList();
            Field l_field =
                WEB3AdminTMLoginFrequencyListServiceImpl.class.getDeclaredField("loginProcessingFrequencyList");
            l_field.setAccessible(true);
            l_field.set(l_impl, loginProcessingFrequencyList);
            
            HashMap ipAddressLoginProcessingFrequency = new HashMap();
            Field l_field1 =
                WEB3AdminTMLoginFrequencyListServiceImpl.class.getDeclaredField("ipAddressLoginProcessingFrequency");
            l_field1.setAccessible(true);
            l_field1.set(l_impl, ipAddressLoginProcessingFrequency);
            
            int l_intIPAddressEveryLoginProcessingFrequency = 8;
            String l_strIPAddress = "192.168.1.4";
            
            Method l_method = WEB3AdminTMLoginFrequencyListServiceImpl.class.getDeclaredMethod(
                "setIPLoginProcessingFrequency",
                new Class[]{int.class, String.class});
            l_method.setAccessible(true);
            Object[] l_obj = {new Integer(l_intIPAddressEveryLoginProcessingFrequency), l_strIPAddress};
            l_method.invoke(l_impl, l_obj);
            
            HashMap l_hmChecks = (HashMap)l_field1.get(l_impl);
            ArrayList l_alChecks = (ArrayList)l_field.get(l_impl);
            
            assertEquals("192.168.1.4", l_hmChecks.get(new Double(8.0)));
            assertEquals(1, l_hmChecks.size());
            assertEquals("8", l_alChecks.get(0));
            assertEquals(1, l_alChecks.size());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /*
     * normal case
     */
    public void testGetIPLoginProcessingFrequency_C0001()
    {
        String STR_METHOD_NAME = "testGetIPLoginProcessingFrequency_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            HashMap ipAddressLoginProcessingFrequency = new HashMap();
            ipAddressLoginProcessingFrequency.put(new Double(5.0), "192.168.1.1");
            ipAddressLoginProcessingFrequency.put(new Double(6.0), "192.168.1.2");
            ipAddressLoginProcessingFrequency.put(new Double(6.000001), "192.168.1.3");
            Field l_field =
                WEB3AdminTMLoginFrequencyListServiceImpl.class.getDeclaredField("ipAddressLoginProcessingFrequency");
            l_field.setAccessible(true);
            l_field.set(l_impl, ipAddressLoginProcessingFrequency);
            
            Method l_method = WEB3AdminTMLoginFrequencyListServiceImpl.class.getDeclaredMethod(
                "getIPLoginProcessingFrequency",
                new Class[]{});
            l_method.setAccessible(true);
            Object[] l_obj = {};
            HashMap l_hsReturns = (HashMap)l_method.invoke(l_impl, l_obj);
            
            assertEquals("192.168.1.1", l_hsReturns.get(new Double(5.0)));
            assertEquals("192.168.1.2", l_hsReturns.get(new Double(6.0)));
            assertEquals("192.168.1.3", l_hsReturns.get(new Double(6.000001)));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /*
     * normal case
     */
    public void testSortLoginProcessingFrequency_C0001()
    {
        String STR_METHOD_NAME = "testSortLoginProcessingFrequency_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            HashMap l_hmIPLoginProcessingFrequencys = new HashMap();
            l_hmIPLoginProcessingFrequencys.put(new Double(5.0), "192.168.1.1");
            l_hmIPLoginProcessingFrequencys.put(new Double(6.0), "192.168.1.2");
            l_hmIPLoginProcessingFrequencys.put(new Double(6.000001), "192.168.1.3");
            Set l_ipAddressLoginProcessingFrequencyCollection = l_hmIPLoginProcessingFrequencys.keySet();
            
            Method l_method = WEB3AdminTMLoginFrequencyListServiceImpl.class.getDeclaredMethod(
                "sortLoginProcessingFrequency",
                new Class[]{Set.class});
            l_method.setAccessible(true);
            Object[] l_obj = {l_ipAddressLoginProcessingFrequencyCollection};
            double[] l_dblPriAryDbls = (double[])l_method.invoke(l_impl, l_obj);
            
            //升序排列
            assertEquals("5.0", String.valueOf(l_dblPriAryDbls[0]));
            assertEquals("6.0", String.valueOf(l_dblPriAryDbls[1]));
            assertEquals("6.000001", String.valueOf(l_dblPriAryDbls[2]));
            assertEquals(3, l_dblPriAryDbls.length);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /*
     * 顯示七條,第八條捨去
     */
    public void testGetIPLoginFrequencyUnitList_C0001()
    {
        String STR_METHOD_NAME = "testGetIPLoginFrequencyUnitList_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            double[] l_dblAscIPLoginProcessingFrequencys = new double[8];
            l_dblAscIPLoginProcessingFrequencys[0] = 1.0;
            l_dblAscIPLoginProcessingFrequencys[1] = 3.0;
            l_dblAscIPLoginProcessingFrequencys[2] = 5.0;
            l_dblAscIPLoginProcessingFrequencys[3] = 6.0;
            l_dblAscIPLoginProcessingFrequencys[4] = 6.000001;
            l_dblAscIPLoginProcessingFrequencys[5] = 8.0;
            l_dblAscIPLoginProcessingFrequencys[6] = 8.000002;
            l_dblAscIPLoginProcessingFrequencys[7] = 8.000003;
            String l_strRank = "7";
            
            HashMap ipAddressLoginProcessingFrequency = new HashMap();
            ipAddressLoginProcessingFrequency.put(new Double(1.0), "192.168.1.1");
            ipAddressLoginProcessingFrequency.put(new Double(3.0), "192.168.1.2");
            ipAddressLoginProcessingFrequency.put(new Double(5.0), "192.168.1.3");
            ipAddressLoginProcessingFrequency.put(new Double(6.0), "192.168.1.4");
            ipAddressLoginProcessingFrequency.put(new Double(6.000001), "192.168.1.5");
            ipAddressLoginProcessingFrequency.put(new Double(8.0), "192.168.1.6");
            ipAddressLoginProcessingFrequency.put(new Double(8.000002), "192.168.1.7");
            ipAddressLoginProcessingFrequency.put(new Double(8.000003), "192.168.1.8");
            Field l_field =
                WEB3AdminTMLoginFrequencyListServiceImpl.class.getDeclaredField("ipAddressLoginProcessingFrequency");
            l_field.setAccessible(true);
            l_field.set(l_impl, ipAddressLoginProcessingFrequency);
            
            Method l_method = WEB3AdminTMLoginFrequencyListServiceImpl.class.getDeclaredMethod(
                "getIPLoginFrequencyUnitList",
                new Class[]{double[].class, String.class});
            l_method.setAccessible(true);
            Object[] l_obj = {l_dblAscIPLoginProcessingFrequencys, l_strRank};
            List l_lisReturns = (List)l_method.invoke(l_impl, l_obj);
            
            //三個並列第一
            assertEquals("192.168.1.8", ((WEB3AdminTraderAdminLoginCountReferenceUnit)l_lisReturns.get(0)).ipAddress);
            assertEquals("1", ((WEB3AdminTraderAdminLoginCountReferenceUnit)l_lisReturns.get(0)).rank);
            assertEquals("8", ((WEB3AdminTraderAdminLoginCountReferenceUnit)l_lisReturns.get(0)).loginCount);
            
            assertEquals("192.168.1.7", ((WEB3AdminTraderAdminLoginCountReferenceUnit)l_lisReturns.get(1)).ipAddress);
            assertEquals("1", ((WEB3AdminTraderAdminLoginCountReferenceUnit)l_lisReturns.get(1)).rank);
            assertEquals("8", ((WEB3AdminTraderAdminLoginCountReferenceUnit)l_lisReturns.get(1)).loginCount);
            
            assertEquals("192.168.1.6", ((WEB3AdminTraderAdminLoginCountReferenceUnit)l_lisReturns.get(2)).ipAddress);
            assertEquals("1", ((WEB3AdminTraderAdminLoginCountReferenceUnit)l_lisReturns.get(2)).rank);
            assertEquals("8", ((WEB3AdminTraderAdminLoginCountReferenceUnit)l_lisReturns.get(2)).loginCount);
            
            //2个并列第四
            assertEquals("192.168.1.5", ((WEB3AdminTraderAdminLoginCountReferenceUnit)l_lisReturns.get(3)).ipAddress);
            assertEquals("4", ((WEB3AdminTraderAdminLoginCountReferenceUnit)l_lisReturns.get(3)).rank);
            assertEquals("6", ((WEB3AdminTraderAdminLoginCountReferenceUnit)l_lisReturns.get(3)).loginCount);
            
            assertEquals("192.168.1.4", ((WEB3AdminTraderAdminLoginCountReferenceUnit)l_lisReturns.get(4)).ipAddress);
            assertEquals("4", ((WEB3AdminTraderAdminLoginCountReferenceUnit)l_lisReturns.get(4)).rank);
            assertEquals("6", ((WEB3AdminTraderAdminLoginCountReferenceUnit)l_lisReturns.get(4)).loginCount);
            
            //一个第六
            assertEquals("192.168.1.3", ((WEB3AdminTraderAdminLoginCountReferenceUnit)l_lisReturns.get(5)).ipAddress);
            assertEquals("6", ((WEB3AdminTraderAdminLoginCountReferenceUnit)l_lisReturns.get(5)).rank);
            assertEquals("5", ((WEB3AdminTraderAdminLoginCountReferenceUnit)l_lisReturns.get(5)).loginCount);
            
            //一个第七
            assertEquals("192.168.1.2", ((WEB3AdminTraderAdminLoginCountReferenceUnit)l_lisReturns.get(6)).ipAddress);
            assertEquals("7", ((WEB3AdminTraderAdminLoginCountReferenceUnit)l_lisReturns.get(6)).rank);
            assertEquals("3", ((WEB3AdminTraderAdminLoginCountReferenceUnit)l_lisReturns.get(6)).loginCount);
            
            //一个舍弃
            assertEquals(7, l_lisReturns.size());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /*
     * 顯示七條,第八條保留
     */
    public void testGetIPLoginFrequencyUnitList_C0002()
    {
        String STR_METHOD_NAME = "testGetIPLoginFrequencyUnitList_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            double[] l_dblAscIPLoginProcessingFrequencys = new double[9];
            l_dblAscIPLoginProcessingFrequencys[0] = 1.0;
            l_dblAscIPLoginProcessingFrequencys[1] = 3.0;
            l_dblAscIPLoginProcessingFrequencys[2] = 3.000001;
            l_dblAscIPLoginProcessingFrequencys[3] = 5.0;
            l_dblAscIPLoginProcessingFrequencys[4] = 6.0;
            l_dblAscIPLoginProcessingFrequencys[5] = 6.000002;
            l_dblAscIPLoginProcessingFrequencys[6] = 8.0;
            l_dblAscIPLoginProcessingFrequencys[7] = 8.000003;
            l_dblAscIPLoginProcessingFrequencys[8] = 8.000004;
            String l_strRank = "7";
            
            HashMap ipAddressLoginProcessingFrequency = new HashMap();
            ipAddressLoginProcessingFrequency.put(new Double(1.0), "192.168.1.1");
            ipAddressLoginProcessingFrequency.put(new Double(3.0), "192.168.1.2");
            ipAddressLoginProcessingFrequency.put(new Double(3.000001), "192.168.1.9");
            ipAddressLoginProcessingFrequency.put(new Double(5.0), "192.168.1.3");
            ipAddressLoginProcessingFrequency.put(new Double(6.0), "192.168.1.4");
            ipAddressLoginProcessingFrequency.put(new Double(6.000002), "192.168.1.5");
            ipAddressLoginProcessingFrequency.put(new Double(8.0), "192.168.1.6");
            ipAddressLoginProcessingFrequency.put(new Double(8.000003), "192.168.1.7");
            ipAddressLoginProcessingFrequency.put(new Double(8.000004), "192.168.1.8");
            Field l_field =
                WEB3AdminTMLoginFrequencyListServiceImpl.class.getDeclaredField("ipAddressLoginProcessingFrequency");
            l_field.setAccessible(true);
            l_field.set(l_impl, ipAddressLoginProcessingFrequency);
            
            Method l_method = WEB3AdminTMLoginFrequencyListServiceImpl.class.getDeclaredMethod(
                "getIPLoginFrequencyUnitList",
                new Class[]{double[].class, String.class});
            l_method.setAccessible(true);
            Object[] l_obj = {l_dblAscIPLoginProcessingFrequencys, l_strRank};
            List l_lisReturns = (List)l_method.invoke(l_impl, l_obj);
            
            //三個並列第一
            assertEquals("192.168.1.8", ((WEB3AdminTraderAdminLoginCountReferenceUnit)l_lisReturns.get(0)).ipAddress);
            assertEquals("1", ((WEB3AdminTraderAdminLoginCountReferenceUnit)l_lisReturns.get(0)).rank);
            assertEquals("8", ((WEB3AdminTraderAdminLoginCountReferenceUnit)l_lisReturns.get(0)).loginCount);
            
            assertEquals("192.168.1.7", ((WEB3AdminTraderAdminLoginCountReferenceUnit)l_lisReturns.get(1)).ipAddress);
            assertEquals("1", ((WEB3AdminTraderAdminLoginCountReferenceUnit)l_lisReturns.get(1)).rank);
            assertEquals("8", ((WEB3AdminTraderAdminLoginCountReferenceUnit)l_lisReturns.get(1)).loginCount);
            
            assertEquals("192.168.1.6", ((WEB3AdminTraderAdminLoginCountReferenceUnit)l_lisReturns.get(2)).ipAddress);
            assertEquals("1", ((WEB3AdminTraderAdminLoginCountReferenceUnit)l_lisReturns.get(2)).rank);
            assertEquals("8", ((WEB3AdminTraderAdminLoginCountReferenceUnit)l_lisReturns.get(2)).loginCount);
            
            //2个并列第四
            assertEquals("192.168.1.5", ((WEB3AdminTraderAdminLoginCountReferenceUnit)l_lisReturns.get(3)).ipAddress);
            assertEquals("4", ((WEB3AdminTraderAdminLoginCountReferenceUnit)l_lisReturns.get(3)).rank);
            assertEquals("6", ((WEB3AdminTraderAdminLoginCountReferenceUnit)l_lisReturns.get(3)).loginCount);
            
            assertEquals("192.168.1.4", ((WEB3AdminTraderAdminLoginCountReferenceUnit)l_lisReturns.get(4)).ipAddress);
            assertEquals("4", ((WEB3AdminTraderAdminLoginCountReferenceUnit)l_lisReturns.get(4)).rank);
            assertEquals("6", ((WEB3AdminTraderAdminLoginCountReferenceUnit)l_lisReturns.get(4)).loginCount);
            
            //一个第六
            assertEquals("192.168.1.3", ((WEB3AdminTraderAdminLoginCountReferenceUnit)l_lisReturns.get(5)).ipAddress);
            assertEquals("6", ((WEB3AdminTraderAdminLoginCountReferenceUnit)l_lisReturns.get(5)).rank);
            assertEquals("5", ((WEB3AdminTraderAdminLoginCountReferenceUnit)l_lisReturns.get(5)).loginCount);
            
            //2个第七
            assertEquals("192.168.1.9", ((WEB3AdminTraderAdminLoginCountReferenceUnit)l_lisReturns.get(6)).ipAddress);
            assertEquals("7", ((WEB3AdminTraderAdminLoginCountReferenceUnit)l_lisReturns.get(6)).rank);
            assertEquals("3", ((WEB3AdminTraderAdminLoginCountReferenceUnit)l_lisReturns.get(6)).loginCount);
            
            assertEquals("192.168.1.2", ((WEB3AdminTraderAdminLoginCountReferenceUnit)l_lisReturns.get(7)).ipAddress);
            assertEquals("7", ((WEB3AdminTraderAdminLoginCountReferenceUnit)l_lisReturns.get(7)).rank);
            assertEquals("3", ((WEB3AdminTraderAdminLoginCountReferenceUnit)l_lisReturns.get(7)).loginCount);
            
            //一个舍弃
            assertEquals(8, l_lisReturns.size());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * LoginInfoForTest
     */
    private class LoginInfoForTest implements LoginInfo
    {

        public LoginTypeInfo getLoginTypeInfo()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public long getLoginId()
        {
            // TODO Auto-generated method stub
            return 1001;
        }

        public long getLoginTypeId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public String getUsername()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public String getInitialPassword()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Set getSubordinateLoginGroups()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public boolean isDisabled()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public Set getReachableAccountIds()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Set getReachableLoginIds()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Set getReachableLogins()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Map getAttributes()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public boolean isAccountReachable(long arg0)
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean hasFailedLogin()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public int getFailureCount()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public Date getLastFailureTimestamp()
        {
            // TODO Auto-generated method stub
            return null;
        }
        
    }
    
    /**
     * WEB3AdminTMLoginFrequencyListServiceImplForTest
     */
    private class WEB3AdminTMLoginFrequencyListServiceImplForTest extends WEB3AdminTMLoginFrequencyListServiceImpl
    {
        protected WEB3AdminTraderAdminLoginCountInputResponse getSearchInputScreen(
            WEB3AdminTraderAdminLoginCountInputRequest l_request) throws WEB3BaseException
        {
            WEB3AdminTraderAdminLoginCountInputResponse l_response = new WEB3AdminTraderAdminLoginCountInputResponse();
            l_response.errorMessage = "101";
            
            return l_response;
        }
        
        protected WEB3AdminTraderAdminLoginCountListResponse getSearchResultScreen(
                WEB3AdminTraderAdminLoginCountListRequest l_request) throws WEB3BaseException
        {
            WEB3AdminTraderAdminLoginCountListResponse l_response = new WEB3AdminTraderAdminLoginCountListResponse();
            l_response.pageIndex = "202";
            l_response.totalPages = "303";
            l_response.totalRecords = "404";
            
            return l_response;
        }
    }
    
    /**
     *get検索結果画面
     */
    public void test_getSearchResultScreen_0001()
    {
        final String STR_METHOD_NAME = "test_getSearchResultScreen_0001()";
        log.entering(STR_METHOD_NAME);
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        Date l_date = WEB3DateUtility.getDate("20080924123000", "yyyyMMddHHmmss");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
            "getSystemTimestamp",
            new Class[] {},
            new Timestamp(l_date.getTime()));
        WEB3AdminTMLoginFrequencyListServiceImpl l_serviceImpl = new WEB3AdminTMLoginFrequencyListServiceImpl();
        //管理者・IP別ログイン回数一覧検索結果リクエスト
        WEB3AdminTraderAdminLoginCountListRequest l_request = new WEB3AdminTraderAdminLoginCountListRequest();
        //日付
        l_request.searchDate = "20080926";
        //時間(自)
        l_request.startTime = "1230";
        //時間(至)
        l_request.endTime = "1330";
        //ランク
        l_request.rank = "3";
        //要求ページ番号
        l_request.pageIndex = "1";
        //ページ内表示行数
        l_request.pageSize = "5";
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long("33381330003"));
        
        try
        {
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setAdministratorId(33381330001L);
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setLoginId(33381330003L);
            l_administratorParams.setPermissionLevel("770");
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("770");
            l_adminPermissionParams.setTransactionCategory("C1301");
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            //ログイン過去履歴
            this.getInitLoginHistoryPast();
            
            //ログイン履歴
            TestDBUtility.deleteAll(LoginHistoryParams.TYPE);
            LoginHistoryParams l_loginHistoryParams = new LoginHistoryParams();
            l_loginHistoryParams.setLoginHistoryId(1234567L);
            l_loginHistoryParams.setInstitutionCode("0D");
            l_loginHistoryParams.setBranchCode("1");
            l_loginHistoryParams.setAccountCode("235");
            l_loginHistoryParams.setAccountId(14444);
            l_loginHistoryParams.setIpAddress("192.168.1.200");//TODO
            l_loginHistoryParams.setOrderRootDiv("2");
            l_loginHistoryParams.setOrderChannel("2");
            //ログイン日時
            l_loginHistoryParams.setLoginTimestamp(WEB3DateUtility.getDate("20080924124512", "yyyyMMddHHmmss"));
            l_loginHistoryParams.setLoginFailure("0");
            l_loginHistoryParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_loginHistoryParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_loginHistoryParams);
            
            WEB3AdminTraderAdminLoginCountListResponse l_response = l_serviceImpl.getSearchResultScreen(l_request);
            
            WEB3AdminTraderAdminLoginCountReferenceUnit[] l_units = l_response.loginCountList;
            //表示ページ番号
            assertEquals("1", l_response.pageIndex);
            //総ページ数
            assertEquals("1", l_response.totalPages);
            //総レコード数
            assertEquals("3", l_response.totalRecords);
            
            assertEquals("10.253.111.2", l_units[0].ipAddress);
            assertEquals("1", l_units[0].rank);
            assertEquals("5", l_units[0].loginCount);
            
            assertEquals("10.253.111.1", l_units[1].ipAddress);
            assertEquals("1", l_units[1].rank);
            assertEquals("5", l_units[1].loginCount);
            
            assertEquals("10.253.111.3", l_units[2].ipAddress);
            assertEquals("3", l_units[2].rank);
            assertEquals("4", l_units[2].loginCount);
            
        } 
        catch (Exception e)
        {
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     *get検索結果画面
     */
    public void test_getSearchResultScreen_0002()
    {
        final String STR_METHOD_NAME = "test_getSearchResultScreen_0001()";
        log.entering(STR_METHOD_NAME);
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        Date l_date = WEB3DateUtility.getDate("20080924123000", "yyyyMMddHHmmss");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
            "getSystemTimestamp",
            new Class[] {},
            new Timestamp(l_date.getTime()));
        WEB3AdminTMLoginFrequencyListServiceImpl l_serviceImpl = new WEB3AdminTMLoginFrequencyListServiceImpl();
        //管理者・IP別ログイン回数一覧検索結果リクエスト
        WEB3AdminTraderAdminLoginCountListRequest l_request = new WEB3AdminTraderAdminLoginCountListRequest();
        //日付
        l_request.searchDate = "20080924";
        //時間(自)
        l_request.startTime = "1230";
        //時間(至)
        l_request.endTime = "1330";
        //ランク
        l_request.rank = "3";
        //要求ページ番号
        l_request.pageIndex = "1";
        //ページ内表示行数
        l_request.pageSize = "5";
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long("33381330003"));
        
        try
        {
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setAdministratorId(33381330001L);
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setLoginId(33381330003L);
            l_administratorParams.setPermissionLevel("770");
            TestDBUtility.insertWithDel(l_administratorParams);
            
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("770");
            l_adminPermissionParams.setTransactionCategory("C1301");
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            //ログイン過去履歴
//            this.getInitLoginHistoryPast();
            
            //ログイン履歴
            this.getInitLoginHistory();
                      
            WEB3AdminTraderAdminLoginCountListResponse l_response = l_serviceImpl.getSearchResultScreen(l_request);
            
            WEB3AdminTraderAdminLoginCountReferenceUnit[] l_units = l_response.loginCountList;
            //表示ページ番号
            assertEquals("1", l_response.pageIndex);
            //総ページ数
            assertEquals("1", l_response.totalPages);
            //総レコード数
            assertEquals("3", l_response.totalRecords);
            
            assertEquals("10.253.111.2", l_units[0].ipAddress);
            assertEquals("1", l_units[0].rank);
            assertEquals("5", l_units[0].loginCount);
            
            assertEquals("10.253.111.1", l_units[1].ipAddress);
            assertEquals("1", l_units[1].rank);
            assertEquals("5", l_units[1].loginCount);
            
            assertEquals("10.253.111.3", l_units[2].ipAddress);
            assertEquals("3", l_units[2].rank);
            assertEquals("4", l_units[2].loginCount);
            
        } 
        catch (Exception e)
        {
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    private void getInitLoginHistoryPast() throws WEB3BaseException
    {
        //ログイン過去履歴
        TestDBUtility.deleteAll(LoginHistoryPastParams.TYPE);
        for (int i = 0; i < 5; i++)
        {
            LoginHistoryPastParams l_loginHistoryPastParams = new LoginHistoryPastParams();
            l_loginHistoryPastParams.setLoginHistoryId(100001L + i);
            l_loginHistoryPastParams.setInstitutionCode("0D");
            l_loginHistoryPastParams.setBranchCode("1");
            l_loginHistoryPastParams.setAccountCode("235");
            l_loginHistoryPastParams.setAccountId(14444);
            l_loginHistoryPastParams.setIpAddress("10.253.111.1");//TODO
            l_loginHistoryPastParams.setOrderRootDiv("2");
            l_loginHistoryPastParams.setOrderChannel("2");
            //ログイン日時
            l_loginHistoryPastParams.setLoginTimestamp(WEB3DateUtility.getDate("20080926124512", "yyyyMMddHHmmss"));
            l_loginHistoryPastParams.setLoginFailure("0");
            l_loginHistoryPastParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_loginHistoryPastParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_loginHistoryPastParams);
        }
        
        for (int i = 0; i < 5; i++)
        {
            LoginHistoryPastParams l_loginHistoryPastParams1 = new LoginHistoryPastParams();
            l_loginHistoryPastParams1.setLoginHistoryId(200001L + i);
            l_loginHistoryPastParams1.setInstitutionCode("0D");
            l_loginHistoryPastParams1.setBranchCode("1");
            l_loginHistoryPastParams1.setAccountCode("235");
            l_loginHistoryPastParams1.setAccountId(14444);
            l_loginHistoryPastParams1.setIpAddress("10.253.111.2");//TODO
            l_loginHistoryPastParams1.setOrderRootDiv("2");
            l_loginHistoryPastParams1.setOrderChannel("2");
            //ログイン日時
            l_loginHistoryPastParams1.setLoginTimestamp(WEB3DateUtility.getDate("20080926124512", "yyyyMMddHHmmss"));
            l_loginHistoryPastParams1.setLoginFailure("0");
            l_loginHistoryPastParams1.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_loginHistoryPastParams1.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_loginHistoryPastParams1);
        }
        
        for (int i = 0; i < 4; i++)
        {
            LoginHistoryPastParams l_loginHistoryPastParams1 = new LoginHistoryPastParams();
            l_loginHistoryPastParams1.setLoginHistoryId(300001L + i);
            l_loginHistoryPastParams1.setInstitutionCode("0D");
            l_loginHistoryPastParams1.setBranchCode("1");
            l_loginHistoryPastParams1.setAccountCode("235");
            l_loginHistoryPastParams1.setAccountId(14444);
            l_loginHistoryPastParams1.setIpAddress("10.253.111.3");//TODO
            l_loginHistoryPastParams1.setOrderRootDiv("2");
            l_loginHistoryPastParams1.setOrderChannel("2");
            //ログイン日時
            l_loginHistoryPastParams1.setLoginTimestamp(WEB3DateUtility.getDate("20080926124512", "yyyyMMddHHmmss"));
            l_loginHistoryPastParams1.setLoginFailure("0");
            l_loginHistoryPastParams1.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_loginHistoryPastParams1.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_loginHistoryPastParams1);
        }
        
        for (int i = 0; i < 2; i++)
        {
            LoginHistoryPastParams l_loginHistoryPastParams1 = new LoginHistoryPastParams();
            l_loginHistoryPastParams1.setLoginHistoryId(400001L + i);
            l_loginHistoryPastParams1.setInstitutionCode("0D");
            l_loginHistoryPastParams1.setBranchCode("1");
            l_loginHistoryPastParams1.setAccountCode("235");
            l_loginHistoryPastParams1.setAccountId(14444);
            l_loginHistoryPastParams1.setIpAddress("10.253.111.4");//TODO
            l_loginHistoryPastParams1.setOrderRootDiv("2");
            l_loginHistoryPastParams1.setOrderChannel("2");
            //ログイン日時
            l_loginHistoryPastParams1.setLoginTimestamp(WEB3DateUtility.getDate("20080926124512", "yyyyMMddHHmmss"));
            l_loginHistoryPastParams1.setLoginFailure("0");
            l_loginHistoryPastParams1.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_loginHistoryPastParams1.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_loginHistoryPastParams1);
        }

        for (int i = 0; i < 1; i++)
        {
            LoginHistoryPastParams l_loginHistoryPastParams1 = new LoginHistoryPastParams();
            l_loginHistoryPastParams1.setLoginHistoryId(500001L + i);
            l_loginHistoryPastParams1.setInstitutionCode("0D");
            l_loginHistoryPastParams1.setBranchCode("1");
            l_loginHistoryPastParams1.setAccountCode("235");
            l_loginHistoryPastParams1.setAccountId(14444);
            l_loginHistoryPastParams1.setIpAddress("10.253.111.5");//TODO
            l_loginHistoryPastParams1.setOrderRootDiv("2");
            l_loginHistoryPastParams1.setOrderChannel("2");
            //ログイン日時
            l_loginHistoryPastParams1.setLoginTimestamp(WEB3DateUtility.getDate("20080926124512", "yyyyMMddHHmmss"));
            l_loginHistoryPastParams1.setLoginFailure("0");
            l_loginHistoryPastParams1.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_loginHistoryPastParams1.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_loginHistoryPastParams1);
        }
        
        for (int i = 0; i < 1; i++)
        {
            LoginHistoryPastParams l_loginHistoryPastParams1 = new LoginHistoryPastParams();
            l_loginHistoryPastParams1.setLoginHistoryId(600001L + i);
            l_loginHistoryPastParams1.setInstitutionCode("0D");
            l_loginHistoryPastParams1.setBranchCode("1");
            l_loginHistoryPastParams1.setAccountCode("235");
            l_loginHistoryPastParams1.setAccountId(14444);
            l_loginHistoryPastParams1.setIpAddress("10.253.111.6");//TODO
            l_loginHistoryPastParams1.setOrderRootDiv("2");
            l_loginHistoryPastParams1.setOrderChannel("2");
            //ログイン日時
            l_loginHistoryPastParams1.setLoginTimestamp(WEB3DateUtility.getDate("20080926124512", "yyyyMMddHHmmss"));
            l_loginHistoryPastParams1.setLoginFailure("0");
            l_loginHistoryPastParams1.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_loginHistoryPastParams1.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_loginHistoryPastParams1);
        }
        
        for (int i = 0; i < 1; i++)
        {
            LoginHistoryPastParams l_loginHistoryPastParams1 = new LoginHistoryPastParams();
            l_loginHistoryPastParams1.setLoginHistoryId(700001L + i);
            l_loginHistoryPastParams1.setInstitutionCode("0D");
            l_loginHistoryPastParams1.setBranchCode("1");
            l_loginHistoryPastParams1.setAccountCode("235");
            l_loginHistoryPastParams1.setAccountId(14444);
            l_loginHistoryPastParams1.setIpAddress("10.253.111.7");//TODO
            l_loginHistoryPastParams1.setOrderRootDiv("2");
            l_loginHistoryPastParams1.setOrderChannel("2");
            //ログイン日時
            l_loginHistoryPastParams1.setLoginTimestamp(WEB3DateUtility.getDate("20080926124512", "yyyyMMddHHmmss"));
            l_loginHistoryPastParams1.setLoginFailure("0");
            l_loginHistoryPastParams1.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_loginHistoryPastParams1.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_loginHistoryPastParams1);
        }
    }
    
    private void getInitLoginHistory() throws WEB3BaseException
    {
        //ログイン過去履歴
        TestDBUtility.deleteAll(LoginHistoryParams.TYPE);
        for (int i = 0; i < 5; i++)
        {
            LoginHistoryParams l_LoginHistoryParams = new LoginHistoryParams();
            l_LoginHistoryParams.setLoginHistoryId(100001L + i);
            l_LoginHistoryParams.setInstitutionCode("0D");
            l_LoginHistoryParams.setBranchCode("1");
            l_LoginHistoryParams.setAccountCode("235");
            l_LoginHistoryParams.setAccountId(14444);
            l_LoginHistoryParams.setIpAddress("10.253.111.1");//TODO
            l_LoginHistoryParams.setOrderRootDiv("2");
            l_LoginHistoryParams.setOrderChannel("2");
            //ログイン日時
            l_LoginHistoryParams.setLoginTimestamp(WEB3DateUtility.getDate("20080924124512", "yyyyMMddHHmmss"));
            l_LoginHistoryParams.setLoginFailure("0");
            l_LoginHistoryParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_LoginHistoryParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_LoginHistoryParams);
        }
        
        for (int i = 0; i < 5; i++)
        {
            LoginHistoryParams l_LoginHistoryParams1 = new LoginHistoryParams();
            l_LoginHistoryParams1.setLoginHistoryId(200001L + i);
            l_LoginHistoryParams1.setInstitutionCode("0D");
            l_LoginHistoryParams1.setBranchCode("1");
            l_LoginHistoryParams1.setAccountCode("235");
            l_LoginHistoryParams1.setAccountId(14444);
            l_LoginHistoryParams1.setIpAddress("10.253.111.2");//TODO
            l_LoginHistoryParams1.setOrderRootDiv("2");
            l_LoginHistoryParams1.setOrderChannel("2");
            //ログイン日時
            l_LoginHistoryParams1.setLoginTimestamp(WEB3DateUtility.getDate("20080924124512", "yyyyMMddHHmmss"));
            l_LoginHistoryParams1.setLoginFailure("0");
            l_LoginHistoryParams1.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_LoginHistoryParams1.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_LoginHistoryParams1);
        }
        
        for (int i = 0; i < 4; i++)
        {
            LoginHistoryParams l_LoginHistoryParams1 = new LoginHistoryParams();
            l_LoginHistoryParams1.setLoginHistoryId(300001L + i);
            l_LoginHistoryParams1.setInstitutionCode("0D");
            l_LoginHistoryParams1.setBranchCode("1");
            l_LoginHistoryParams1.setAccountCode("235");
            l_LoginHistoryParams1.setAccountId(14444);
            l_LoginHistoryParams1.setIpAddress("10.253.111.3");//TODO
            l_LoginHistoryParams1.setOrderRootDiv("2");
            l_LoginHistoryParams1.setOrderChannel("2");
            //ログイン日時
            l_LoginHistoryParams1.setLoginTimestamp(WEB3DateUtility.getDate("20080924124512", "yyyyMMddHHmmss"));
            l_LoginHistoryParams1.setLoginFailure("0");
            l_LoginHistoryParams1.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_LoginHistoryParams1.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_LoginHistoryParams1);
        }
        
        for (int i = 0; i < 2; i++)
        {
            LoginHistoryParams l_LoginHistoryParams1 = new LoginHistoryParams();
            l_LoginHistoryParams1.setLoginHistoryId(400001L + i);
            l_LoginHistoryParams1.setInstitutionCode("0D");
            l_LoginHistoryParams1.setBranchCode("1");
            l_LoginHistoryParams1.setAccountCode("235");
            l_LoginHistoryParams1.setAccountId(14444);
            l_LoginHistoryParams1.setIpAddress("10.253.111.4");//TODO
            l_LoginHistoryParams1.setOrderRootDiv("2");
            l_LoginHistoryParams1.setOrderChannel("2");
            //ログイン日時
            l_LoginHistoryParams1.setLoginTimestamp(WEB3DateUtility.getDate("20080924124512", "yyyyMMddHHmmss"));
            l_LoginHistoryParams1.setLoginFailure("0");
            l_LoginHistoryParams1.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_LoginHistoryParams1.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_LoginHistoryParams1);
        }

        for (int i = 0; i < 1; i++)
        {
            LoginHistoryParams l_LoginHistoryParams1 = new LoginHistoryParams();
            l_LoginHistoryParams1.setLoginHistoryId(500001L + i);
            l_LoginHistoryParams1.setInstitutionCode("0D");
            l_LoginHistoryParams1.setBranchCode("1");
            l_LoginHistoryParams1.setAccountCode("235");
            l_LoginHistoryParams1.setAccountId(14444);
            l_LoginHistoryParams1.setIpAddress("10.253.111.5");//TODO
            l_LoginHistoryParams1.setOrderRootDiv("2");
            l_LoginHistoryParams1.setOrderChannel("2");
            //ログイン日時
            l_LoginHistoryParams1.setLoginTimestamp(WEB3DateUtility.getDate("20080926124512", "yyyyMMddHHmmss"));
            l_LoginHistoryParams1.setLoginFailure("0");
            l_LoginHistoryParams1.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_LoginHistoryParams1.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_LoginHistoryParams1);
        }
        
        for (int i = 0; i < 1; i++)
        {
            LoginHistoryParams l_LoginHistoryParams1 = new LoginHistoryParams();
            l_LoginHistoryParams1.setLoginHistoryId(600001L + i);
            l_LoginHistoryParams1.setInstitutionCode("0D");
            l_LoginHistoryParams1.setBranchCode("1");
            l_LoginHistoryParams1.setAccountCode("235");
            l_LoginHistoryParams1.setAccountId(14444);
            l_LoginHistoryParams1.setIpAddress("10.253.111.6");//TODO
            l_LoginHistoryParams1.setOrderRootDiv("2");
            l_LoginHistoryParams1.setOrderChannel("2");
            //ログイン日時
            l_LoginHistoryParams1.setLoginTimestamp(WEB3DateUtility.getDate("20080924124512", "yyyyMMddHHmmss"));
            l_LoginHistoryParams1.setLoginFailure("0");
            l_LoginHistoryParams1.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_LoginHistoryParams1.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_LoginHistoryParams1);
        }
        
        for (int i = 0; i < 1; i++)
        {
            LoginHistoryParams l_LoginHistoryParams1 = new LoginHistoryParams();
            l_LoginHistoryParams1.setLoginHistoryId(700001L + i);
            l_LoginHistoryParams1.setInstitutionCode("0D");
            l_LoginHistoryParams1.setBranchCode("1");
            l_LoginHistoryParams1.setAccountCode("235");
            l_LoginHistoryParams1.setAccountId(14444);
            l_LoginHistoryParams1.setIpAddress("10.253.111.7");//TODO
            l_LoginHistoryParams1.setOrderRootDiv("2");
            l_LoginHistoryParams1.setOrderChannel("2");
            //ログイン日時
            l_LoginHistoryParams1.setLoginTimestamp(WEB3DateUtility.getDate("20080924124512", "yyyyMMddHHmmss"));
            l_LoginHistoryParams1.setLoginFailure("0");
            l_LoginHistoryParams1.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            l_LoginHistoryParams1.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDel(l_LoginHistoryParams1);
        }
    }
    
    /**
     *get検索結果画面
     */
    public void test_getSearchResultScreen_0003()
    {
        final String STR_METHOD_NAME = "test_getSearchResultScreen_0003()";
        log.entering(STR_METHOD_NAME);
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        Date l_date = WEB3DateUtility.getDate("20080924123000", "yyyyMMddHHmmss");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
            "getSystemTimestamp",
            new Class[] {},
            new Timestamp(l_date.getTime()));
        WEB3AdminTMLoginFrequencyListServiceImpl l_serviceImpl = new WEB3AdminTMLoginFrequencyListServiceImpl();
        //管理者・IP別ログイン回数一覧検索結果リクエスト
        WEB3AdminTraderAdminLoginCountListRequest l_request = new WEB3AdminTraderAdminLoginCountListRequest();
        //日付
        l_request.searchDate = null;
         
        try
        {
            l_serviceImpl.getSearchResultScreen(l_request);
            fail();
        } 
        catch (WEB3BaseException e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01272, e.getErrorInfo());
        }
        catch (Exception e)
        {
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    
    
    

}
@
