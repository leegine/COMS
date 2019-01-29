head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.26.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AcceptLoginServiceImplTest_xiexuan.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.login.service.delegate.stdimpls;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginTypeInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginParams;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginTypeParams;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.BranchImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.InstitutionImpl;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3LoginAttributeKeyDef;
import webbroker3.common.define.WEB3LoginTypeAttributeKeyDef;
import webbroker3.gentrade.WEB3ServiceImpState;
import webbroker3.gentrade.data.BranchPreferencesParams;
import webbroker3.gentrade.data.LoginHistoryRow;
import webbroker3.login.message.WEB3AcceptInfo;
import webbroker3.login.message.WEB3AcceptLoginRequest;
import webbroker3.login.message.WEB3BranchInfo;
import webbroker3.login.message.WEB3InstitutionInfo;
import webbroker3.login.message.WEB3SetSessionRequest;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AcceptLoginServiceImplTest_xiexuan extends TestBaseForMock
{
    public static boolean l_blnIsLoginStopFlag = true;
    public static boolean l_blnCheckOrderChannelFlag = true;
    public static boolean l_blnIsRejectIpFlag = true;
    public static boolean l_blnCheckAcceptCodeFlag = true;
    public static boolean l_blnProcessingResultFlag = true;
    public static boolean l_blnIsEnabledUserFlag = true;
    
    public static long l_expIsRejectIp_BranchIdValue;
    public static String l_expIsRejectIp_InstitutionCodeValue;
    public static String l_expIsRejectIp_OrderRootDivValue;
    public static String l_expIsRejectIp_IpAddressValue;
    public static boolean l_blnIsRejectIp_ParamCheck = false;
   
    public static String l_expValidateDiscriminationCode_DiscriminationCodeValue;
    public static String l_expValidateDiscriminationCode_OrderRootDivValue;
    public static Map l_expValidateDiscriminationCode_LoginTypeAttributesValue;
    public static Map l_expValidateDiscriminationCode_LoginAttributesValue;
    public static boolean l_blnValidateDiscriminationCode__ParamCheck = false;

    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AcceptLoginServiceImplTest_xiexuan.class);

    public WEB3AcceptLoginServiceImplTest_xiexuan(String arg0)
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
    
    public void testExcute_0001()
    {
        final String STR_METHOD_NAME = "testExcute_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(LoginHistoryRow.TYPE);
            
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("login.log.record.1");
            l_branchPreferencesParams.setNameSerialNo(1);
            TestDBUtility.deleteAll(l_branchPreferencesParams.getRowType());
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            InstitutionImpl l_institutionImpl = new InstitutionImpl(l_institutionParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "getInstitution",
                new Class[] {String.class},
                l_institutionImpl);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(l_branchParams.getRowType());
            TestDBUtility.insertWithDel(l_branchParams);
            BranchImpl l_branchImpl = new BranchImpl(l_branchParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "getBranch",
                new Class[] {Institution.class, String.class},
                l_branchImpl);
            
            l_blnIsLoginStopFlag = true;
            
            WEB3AcceptLoginServiceImplForTest l_acceptLoginServiceImplForTest =
                new WEB3AcceptLoginServiceImplForTest();
            
            WEB3AcceptLoginRequest l_request = new WEB3AcceptLoginRequest();
            l_request.xTradeUsername="123";
            l_request.acceptPassword="123";
            l_request.institutionCode = "0D";
            l_request.branchCode = "381";
            l_request.acceptCode = "23456";
            l_request.ipAddress = "127.0.0.1";
            l_request.discriminationCode = "1";
            l_request.account_id = "123";
            l_request.orderRootDiv = "1";
            l_request.orderChannel = "2";

            l_acceptLoginServiceImplForTest.execute(l_request);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_90201, l_ex.getErrorInfo());
            
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                String l_strQueryWhere = "institution_code = ? and branch_code = ? and account_code = ?";
                Object[] l_objWhereValue = {"0D", "381", "23456"};
                
                List l_list =
                    l_queryProcessor.doFindAllQuery(LoginHistoryRow.TYPE, l_strQueryWhere, l_objWhereValue);
                
                LoginHistoryRow l_loginHistoryRow = (LoginHistoryRow)l_list.get(0);
                
                assertEquals("0D", l_loginHistoryRow.getInstitutionCode());
                assertEquals("381", l_loginHistoryRow.getBranchCode());
                assertEquals("23456", l_loginHistoryRow.getAccountCode());
                assertEquals("127.0.0.1", l_loginHistoryRow.getIpAddress());
                assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_90201.toString(), l_loginHistoryRow.getLoginErrorInfo());
            }
            catch (Exception ex)
            {
                log.error(TEST_END + STR_METHOD_NAME, ex);
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testExcute_0002()
    {
        final String STR_METHOD_NAME = "testExcute_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(LoginHistoryRow.TYPE);
            
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("login.log.record.1");
            l_branchPreferencesParams.setNameSerialNo(1);
            TestDBUtility.deleteAll(l_branchPreferencesParams.getRowType());
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            InstitutionImpl l_institutionImpl = new InstitutionImpl(l_institutionParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "getInstitution",
                new Class[] {String.class},
                l_institutionImpl);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(l_branchParams.getRowType());
            TestDBUtility.insertWithDel(l_branchParams);
            BranchImpl l_branchImpl = new BranchImpl(l_branchParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "getBranch",
                new Class[] {Institution.class, String.class},
                l_branchImpl);
            
            l_blnIsLoginStopFlag = false;
            l_blnCheckOrderChannelFlag = false;
            
            WEB3AcceptLoginServiceImplForTest l_acceptLoginServiceImplForTest =
                new WEB3AcceptLoginServiceImplForTest();
            
            WEB3AcceptLoginRequest l_request = new WEB3AcceptLoginRequest();
            l_request.xTradeUsername="123";
            l_request.acceptPassword="123";
            l_request.institutionCode = "0D";
            l_request.branchCode = "381";
            l_request.acceptCode = "23456";
            l_request.ipAddress = "127.0.0.1";
            l_request.discriminationCode = "1";
            l_request.account_id = "123";
            l_request.orderRootDiv = "1";
            l_request.orderChannel = "2";

            l_acceptLoginServiceImplForTest.execute(l_request);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_90221, l_ex.getErrorInfo());
            
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                String l_strQueryWhere = "institution_code = ? and branch_code = ? and account_code = ?";
                Object[] l_objWhereValue = {"0D", "381", "23456"};
                
                List l_list =
                    l_queryProcessor.doFindAllQuery(LoginHistoryRow.TYPE, l_strQueryWhere, l_objWhereValue);
                
                LoginHistoryRow l_loginHistoryRow = (LoginHistoryRow)l_list.get(0);
                
                assertEquals("0D", l_loginHistoryRow.getInstitutionCode());
                assertEquals("381", l_loginHistoryRow.getBranchCode());
                assertEquals("23456", l_loginHistoryRow.getAccountCode());
                assertEquals("127.0.0.1", l_loginHistoryRow.getIpAddress());
                assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_90221.toString(), l_loginHistoryRow.getLoginErrorInfo());
            }
            catch (Exception ex)
            {
                log.error(TEST_END + STR_METHOD_NAME, ex);
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testExcute_0003()
    {
        final String STR_METHOD_NAME = "testExcute_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(LoginHistoryRow.TYPE);
            
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("login.log.record.1");
            l_branchPreferencesParams.setNameSerialNo(1);
            TestDBUtility.deleteAll(l_branchPreferencesParams.getRowType());
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            InstitutionImpl l_institutionImpl = new InstitutionImpl(l_institutionParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "getInstitution",
                new Class[] {String.class},
                l_institutionImpl);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(l_branchParams.getRowType());
            TestDBUtility.insertWithDel(l_branchParams);
            BranchImpl l_branchImpl = new BranchImpl(l_branchParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "getBranch",
                new Class[] {Institution.class, String.class},
                l_branchImpl);
            
            l_blnIsLoginStopFlag = false;
            l_blnCheckOrderChannelFlag = true;
            l_blnIsRejectIpFlag = true;
            
            WEB3AcceptLoginServiceImplForTest l_acceptLoginServiceImplForTest =
                new WEB3AcceptLoginServiceImplForTest();
            
            l_expIsRejectIp_BranchIdValue = 33381;
            l_expIsRejectIp_InstitutionCodeValue = "0D";
            l_expIsRejectIp_OrderRootDivValue = "1";
            l_expIsRejectIp_IpAddressValue = "127.0.0.1";
            
            WEB3AcceptLoginRequest l_request = new WEB3AcceptLoginRequest();
            l_request.xTradeUsername="123";
            l_request.acceptPassword="123";
            l_request.institutionCode = "0D";
            l_request.branchCode = "381";
            l_request.acceptCode = "23456";
            l_request.ipAddress = "127.0.0.1";
            l_request.discriminationCode = "1";
            l_request.account_id = "123";
            l_request.orderRootDiv = "1";
            l_request.orderChannel = "2";

            l_acceptLoginServiceImplForTest.execute(l_request);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            
            assertFalse(l_blnIsRejectIp_ParamCheck);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02821, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testExcute_0004()
    {
        final String STR_METHOD_NAME = "testExcute_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(LoginHistoryRow.TYPE);
            
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("login.log.record.1");
            l_branchPreferencesParams.setNameSerialNo(1);
            TestDBUtility.deleteAll(l_branchPreferencesParams.getRowType());
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            InstitutionImpl l_institutionImpl = new InstitutionImpl(l_institutionParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "getInstitution",
                new Class[] {String.class},
                l_institutionImpl);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(l_branchParams.getRowType());
            TestDBUtility.insertWithDel(l_branchParams);
            BranchImpl l_branchImpl = new BranchImpl(l_branchParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "getBranch",
                new Class[] {Institution.class, String.class},
                l_branchImpl);
            
            l_blnIsLoginStopFlag = false;
            l_blnCheckOrderChannelFlag = true;
            l_blnIsRejectIpFlag = false;
            l_blnCheckAcceptCodeFlag = false;
            
            WEB3AcceptLoginServiceImplForTest l_acceptLoginServiceImplForTest =
                new WEB3AcceptLoginServiceImplForTest();
            
            WEB3AcceptLoginRequest l_request = new WEB3AcceptLoginRequest();
            l_request.xTradeUsername="123";
            l_request.acceptPassword="123";
            l_request.institutionCode = "0D";
            l_request.branchCode = "381";
            l_request.acceptCode = "23456";
            l_request.ipAddress = "127.0.0.1";
            l_request.discriminationCode = "1";
            l_request.account_id = "123";
            l_request.orderRootDiv = "1";
            l_request.orderChannel = "2";

            l_acceptLoginServiceImplForTest.execute(l_request);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_90221, l_ex.getErrorInfo());
            
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                String l_strQueryWhere = "institution_code = ? and branch_code = ? and account_code = ?";
                Object[] l_objWhereValue = {"0D", "381", "23456"};
                
                List l_list =
                    l_queryProcessor.doFindAllQuery(LoginHistoryRow.TYPE, l_strQueryWhere, l_objWhereValue);
                
                LoginHistoryRow l_loginHistoryRow = (LoginHistoryRow)l_list.get(0);
                
                assertEquals("0D", l_loginHistoryRow.getInstitutionCode());
                assertEquals("381", l_loginHistoryRow.getBranchCode());
                assertEquals("23456", l_loginHistoryRow.getAccountCode());
                assertEquals("127.0.0.1", l_loginHistoryRow.getIpAddress());
                assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_90221.toString(), l_loginHistoryRow.getLoginErrorInfo());
            }
            catch (Exception ex)
            {
                log.error(TEST_END + STR_METHOD_NAME, ex);
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testExcute_0005()
    {
        final String STR_METHOD_NAME = "testExcute_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(LoginHistoryRow.TYPE);
            
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("login.log.record.1");
            l_branchPreferencesParams.setNameSerialNo(1);
            TestDBUtility.deleteAll(l_branchPreferencesParams.getRowType());
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            InstitutionImpl l_institutionImpl = new InstitutionImpl(l_institutionParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "getInstitution",
                new Class[] {String.class},
                l_institutionImpl);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(l_branchParams.getRowType());
            TestDBUtility.insertWithDel(l_branchParams);
            BranchImpl l_branchImpl = new BranchImpl(l_branchParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "getBranch",
                new Class[] {Institution.class, String.class},
                l_branchImpl);
            
            LoginInfoImpl l_LoginInfoImpl = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
                "getLoginInfo",
                new Class[] {String.class},
                l_LoginInfoImpl);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
                "getLoginInfo",
                new Class[] {long.class},
                l_LoginInfoImpl);
            
            Map l_map = new HashMap();
            l_map.put(WEB3LoginTypeAttributeKeyDef.SECURITY_LEVEL, "123");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
                "getLoginTypeAttributes",
                new Class[] {long.class},
                l_map);
            
            l_blnIsLoginStopFlag = false;
            l_blnCheckOrderChannelFlag = true;
            l_blnIsRejectIpFlag = false;
            l_blnCheckAcceptCodeFlag = true;
            l_blnProcessingResultFlag = false;
            
            WEB3AcceptLoginServiceImplForTest l_acceptLoginServiceImplForTest =
                new WEB3AcceptLoginServiceImplForTest();
            
            l_expValidateDiscriminationCode_DiscriminationCodeValue = "1";
            l_expValidateDiscriminationCode_OrderRootDivValue = "1";
            l_expValidateDiscriminationCode_LoginTypeAttributesValue = l_map;
            l_expValidateDiscriminationCode_LoginAttributesValue = null;
            
            WEB3AcceptLoginRequest l_request = new WEB3AcceptLoginRequest();
            l_request.xTradeUsername="123";
            l_request.acceptPassword="123";
            l_request.institutionCode = "0D";
            l_request.branchCode = "381";
            l_request.acceptCode = "23456";
            l_request.ipAddress = "127.0.0.1";
            l_request.discriminationCode = "1";
            l_request.account_id = "123";
            l_request.orderRootDiv = "1";
            l_request.orderChannel = "2";

            l_acceptLoginServiceImplForTest.execute(l_request);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            
            assertFalse(l_blnValidateDiscriminationCode__ParamCheck);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_90221, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testExcute_0006()
    {
        final String STR_METHOD_NAME = "testExcute_0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(LoginHistoryRow.TYPE);
            
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("login.log.record.1");
            l_branchPreferencesParams.setNameSerialNo(1);
            TestDBUtility.deleteAll(l_branchPreferencesParams.getRowType());
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            InstitutionImpl l_institutionImpl = new InstitutionImpl(l_institutionParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "getInstitution",
                new Class[] {String.class},
                l_institutionImpl);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(l_branchParams.getRowType());
            TestDBUtility.insertWithDel(l_branchParams);
            BranchImpl l_branchImpl = new BranchImpl(l_branchParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "getBranch",
                new Class[] {Institution.class, String.class},
                l_branchImpl);
            
            LoginInfoImpl l_LoginInfoImpl = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
                "getLoginInfo",
                new Class[] {String.class},
                l_LoginInfoImpl);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
                "getLoginInfo",
                new Class[] {long.class},
                l_LoginInfoImpl);
            
            Map l_map = new HashMap();
            l_map.put(WEB3LoginTypeAttributeKeyDef.SECURITY_LEVEL, "123");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
                "getLoginTypeAttributes",
                new Class[] {long.class},
                l_map);
            
            l_blnIsLoginStopFlag = false;
            l_blnCheckOrderChannelFlag = true;
            l_blnIsRejectIpFlag = false;
            l_blnCheckAcceptCodeFlag = true;
            l_blnProcessingResultFlag = true;
            l_blnIsEnabledUserFlag = false;
            
            WEB3AcceptLoginServiceImplForTest l_acceptLoginServiceImplForTest =
                new WEB3AcceptLoginServiceImplForTest();
            
            WEB3AcceptLoginRequest l_request = new WEB3AcceptLoginRequest();
            l_request.xTradeUsername="123";
            l_request.acceptPassword="123";
            l_request.institutionCode = "0D";
            l_request.branchCode = "381";
            l_request.acceptCode = "23456";
            l_request.ipAddress = "127.0.0.1";
            l_request.discriminationCode = "1";
            l_request.account_id = "123";
            l_request.orderRootDiv = "1";
            l_request.orderChannel = "2";

            l_acceptLoginServiceImplForTest.execute(l_request);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_90202, l_ex.getErrorInfo());
            
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                String l_strQueryWhere = "institution_code = ? and branch_code = ? and account_code = ?";
                Object[] l_objWhereValue = {"0D", "381", "23456"};
                
                List l_list =
                    l_queryProcessor.doFindAllQuery(LoginHistoryRow.TYPE, l_strQueryWhere, l_objWhereValue);
                
                LoginHistoryRow l_loginHistoryRow = (LoginHistoryRow)l_list.get(0);
                
                assertEquals("0D", l_loginHistoryRow.getInstitutionCode());
                assertEquals("381", l_loginHistoryRow.getBranchCode());
                assertEquals("23456", l_loginHistoryRow.getAccountCode());
                assertEquals("127.0.0.1", l_loginHistoryRow.getIpAddress());
                assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_90202.toString(), l_loginHistoryRow.getLoginErrorInfo());
            }
            catch (Exception ex)
            {
                log.error(TEST_END + STR_METHOD_NAME, ex);
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testExcute_0007()
    {
        final String STR_METHOD_NAME = "testExcute_0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(LoginHistoryRow.TYPE);
            
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("login.log.record.1");
            l_branchPreferencesParams.setNameSerialNo(1);
            TestDBUtility.deleteAll(l_branchPreferencesParams.getRowType());
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            InstitutionImpl l_institutionImpl = new InstitutionImpl(l_institutionParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "getInstitution",
                new Class[] {String.class},
                l_institutionImpl);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(l_branchParams.getRowType());
            TestDBUtility.insertWithDel(l_branchParams);
            BranchImpl l_branchImpl = new BranchImpl(l_branchParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "getBranch",
                new Class[] {Institution.class, String.class},
                l_branchImpl);
            
            LoginInfoImpl l_LoginInfoImpl = new LoginInfoImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
                "getLoginInfo",
                new Class[] {String.class},
                l_LoginInfoImpl);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
                "getLoginInfo",
                new Class[] {long.class},
                l_LoginInfoImpl);
            
            Map l_map = new HashMap();
            l_map.put(WEB3LoginTypeAttributeKeyDef.SECURITY_LEVEL, "123");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
                "getLoginTypeAttributes",
                new Class[] {long.class},
                l_map);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "logIn",
                new Class[] {String.class, String.class},
                null);
            
            l_blnIsLoginStopFlag = false;
            l_blnCheckOrderChannelFlag = true;
            l_blnIsRejectIpFlag = false;
            l_blnCheckAcceptCodeFlag = true;
            l_blnProcessingResultFlag = true;
            l_blnIsEnabledUserFlag = true;
            
            WEB3AcceptLoginServiceImplForTest l_acceptLoginServiceImplForTest =
                new WEB3AcceptLoginServiceImplForTest();
            
            WEB3AcceptLoginRequest l_request = new WEB3AcceptLoginRequest();
            l_request.xTradeUsername="123";
            l_request.acceptPassword="123";
            l_request.institutionCode = "0D";
            l_request.branchCode = "381";
            l_request.acceptCode = "23456";
            l_request.ipAddress = "127.0.0.1";
            l_request.discriminationCode = "1";
            l_request.account_id = "123";
            l_request.orderRootDiv = "1";
            l_request.orderChannel = "2";

            l_acceptLoginServiceImplForTest.execute(l_request);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_90203, l_ex.getErrorInfo());
            
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                String l_strQueryWhere = "institution_code = ? and branch_code = ? and account_code = ?";
                Object[] l_objWhereValue = {"0D", "381", "23456"};
                
                List l_list =
                    l_queryProcessor.doFindAllQuery(LoginHistoryRow.TYPE, l_strQueryWhere, l_objWhereValue);
                
                LoginHistoryRow l_loginHistoryRow = (LoginHistoryRow)l_list.get(0);
                
                assertEquals("0D", l_loginHistoryRow.getInstitutionCode());
                assertEquals("381", l_loginHistoryRow.getBranchCode());
                assertEquals("23456", l_loginHistoryRow.getAccountCode());
                assertEquals("127.0.0.1", l_loginHistoryRow.getIpAddress());
                assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_90203.toString(), l_loginHistoryRow.getLoginErrorInfo());
            }
            catch (Exception ex)
            {
                log.error(TEST_END + STR_METHOD_NAME, ex);
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testExcute_0008()
    {
        final String STR_METHOD_NAME = "testExcute_0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(LoginHistoryRow.TYPE);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.deleteAll(l_mainAccountParams.getRowType());
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            LoginTypeParams l_loginTypeParams = new LoginTypeParams();
            l_loginTypeParams.setTypeId(12);
            l_loginTypeParams.setTypeName("aaaaa");
            TestDBUtility.deleteAll(l_loginTypeParams.getRowType());
            TestDBUtility.insertWithDel(l_loginTypeParams);

            LoginParams l_loginRow = new LoginParams();
            l_loginRow.setLoginId(0);
            l_loginRow.setTypeId(12);
            l_loginRow.setAccountId(333812512246L);
            l_loginRow.setInitialPassword("123");
            l_loginRow.setHashedPassword("123");
            l_loginRow.setAffinityKey(1);
            l_loginRow.setDisabled(1);
            l_loginRow.setFailureCount(123);
            l_loginRow.setLastFailureTimestamp(new Date());
            TestDBUtility.deleteAll(l_loginRow.getRowType());
            TestDBUtility.insertWithDel(l_loginRow);
                        
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("login.log.record.F");
            l_branchPreferencesParams.setNameSerialNo(1);
            TestDBUtility.deleteAll(l_branchPreferencesParams.getRowType());
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            InstitutionImpl l_institutionImpl = new InstitutionImpl(l_institutionParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "getInstitution",
                new Class[] {String.class},
                l_institutionImpl);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(l_branchParams.getRowType());
            TestDBUtility.insertWithDel(l_branchParams);
            BranchImpl l_branchImpl = new BranchImpl(l_branchParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "getBranch",
                new Class[] {Institution.class, String.class},
                l_branchImpl);
            
            LoginInfoImplForMock l_LoginInfoImpl = new LoginInfoImplForMock();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
                "getLoginInfo",
                new Class[] {String.class},
                l_LoginInfoImpl);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
                "getLoginInfo",
                new Class[] {long.class},
                l_LoginInfoImpl);
            
            Map l_map = new HashMap();
            l_map.put(WEB3LoginTypeAttributeKeyDef.SECURITY_LEVEL, "123");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
                "getLoginTypeAttributes",
                new Class[] {long.class},
                l_map);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "logIn",
                new Class[] {String.class, String.class},
                "123:234:345");
            
            l_blnIsLoginStopFlag = false;
            l_blnCheckOrderChannelFlag = true;
            l_blnIsRejectIpFlag = false;
            l_blnCheckAcceptCodeFlag = true;
            l_blnProcessingResultFlag = true;
            l_blnIsEnabledUserFlag = true;
            
            WEB3AcceptLoginServiceImplForTest l_acceptLoginServiceImplForTest =
                new WEB3AcceptLoginServiceImplForTest();
            
            WEB3AcceptLoginRequest l_request = new WEB3AcceptLoginRequest();
            l_request.xTradeUsername="123";
            l_request.acceptPassword="123";
            l_request.institutionCode = "0D";
            l_request.branchCode = "381";
            l_request.acceptCode = "23456";
            l_request.ipAddress = "127.0.0.1";
            l_request.discriminationCode = "1";
            l_request.account_id = "123";
            l_request.orderRootDiv = "F";
            l_request.orderChannel = "2";
            
            
            l_expValidateDiscriminationCode_DiscriminationCodeValue = "1";
            l_expValidateDiscriminationCode_OrderRootDivValue = "1";
            l_expValidateDiscriminationCode_LoginTypeAttributesValue = l_map;
            l_expValidateDiscriminationCode_LoginAttributesValue = null;
            
            
            
            l_acceptLoginServiceImplForTest.execute(l_request);
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            String l_strQueryWhere = "institution_code = ? and branch_code = ? and account_code = ?";
            Object[] l_objWhereValue = {"0D", "381", "23456"};
            
            List l_list =
                l_queryProcessor.doFindAllQuery(LoginHistoryRow.TYPE, l_strQueryWhere, l_objWhereValue);
            
            LoginHistoryRow l_loginHistoryRow = (LoginHistoryRow)l_list.get(0);
            
            assertEquals("0D", l_loginHistoryRow.getInstitutionCode());
            assertEquals("381", l_loginHistoryRow.getBranchCode());
            assertEquals("23456", l_loginHistoryRow.getAccountCode());
            assertEquals("127.0.0.1", l_loginHistoryRow.getIpAddress());
            assertEquals(null, l_loginHistoryRow.getLoginErrorInfo());

        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    private class LoginInfoImplForMock implements LoginInfo
    {
        public LoginTypeInfo getLoginTypeInfo()
        {
            return null;
        }

        public long getLoginId()
        {
            return 0L;
        }

        public long getLoginTypeId()
        {
            return 0L;
        }

        public String getUsername()
        {
            return "";
        }

        public String getInitialPassword()
        {
            return null;
        }

        public Set getSubordinateLoginGroups()
        {
            return null;
        }

        public boolean isDisabled()
        {
            return true;
        }

        public Set getReachableAccountIds()
        {
            return null;
        }

        public Set getReachableLoginIds()
        {
            return null;
        }

        public Set getReachableLogins()
        {
            return null;
        }

        public Map getAttributes()
        {
            Map l_mapReturns = new HashMap();
            l_mapReturns.put(WEB3LoginAttributeKeyDef.LAST_LOGIN, "");
            return l_mapReturns;
        }

        public boolean isAccountReachable(long l)
        {
            return true;
        }

        public boolean hasFailedLogin()
        {
            return true;
        }

        public int getFailureCount()
        {
            return 0;
        }

        public Date getLastFailureTimestamp()
        {
            return null;
        }
    }
    public class WEB3AcceptLoginServiceImplForTest extends WEB3AcceptLoginServiceImpl
    {
        protected WEB3AcceptInfo getAcceptInfo(String l_acceptCode, MainAccountRow l_mainAccountRow, LoginInfo l_loginInfo, String l_orderRootDiv)
        throws WEB3BaseException
        {
            log.entering("getAcceptInfo(String)");
            WEB3AcceptInfo l_acceptInfo = new WEB3AcceptInfo();
            log.exiting("getAcceptInfo(String)");
            return l_acceptInfo;
        }
        
        public String getPasswordForIVR(String l_strPassword, Map l_loginAttributes) 
        throws NotFoundException
        {
            final String STR_METHOD_NAME = "getPasswordForIVR(String, Map)";
            log.entering(STR_METHOD_NAME);
            
            
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
//        protected void insertLoginHistory(
//            long l_lngBranchID,
//            String l_strInstitutionCode,
//            String l_strBranchCode,
//            String l_strAccountCode,
//            String l_strOrderActionId,
//            long l_lngAccountID,
//            String l_strIpAddress,
//            String l_strOrderRootDiv,
//            String l_strOrderChannel,
//            Date l_datMachineTime,
//            ErrorInfo l_errorInfo) throws WEB3BaseException
//        {
//            final String STR_METHOD_NAME = "insertLoginHistory(long, String, String, String, String," +
//                    "long, String, String, String, Date, ErrorInfo)";
//            log.entering(STR_METHOD_NAME);
//            
//            log.exiting(STR_METHOD_NAME);
//        }
        
        protected boolean isRejectIp(
            long l_lngBranchId,
            String l_strInstitutionCode,
            String l_strOrderRootDiv,
            String l_strIpAddress) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = " isRejectIp(long, String, String, String)";
            log.entering(STR_METHOD_NAME);

            if (WEB3AcceptLoginServiceImplTest_xiexuan.l_expIsRejectIp_BranchIdValue != l_lngBranchId
                || !WEB3AcceptLoginServiceImplTest_xiexuan.l_expIsRejectIp_InstitutionCodeValue.equals(l_strInstitutionCode)
                || !WEB3AcceptLoginServiceImplTest_xiexuan.l_expIsRejectIp_OrderRootDivValue.equals(l_strOrderRootDiv)
                || !WEB3AcceptLoginServiceImplTest_xiexuan.l_expIsRejectIp_IpAddressValue.equals(l_strIpAddress))
            {
                WEB3AcceptLoginServiceImplTest_xiexuan.l_blnIsRejectIp_ParamCheck = true;
            }
            
            if (WEB3AcceptLoginServiceImplTest_xiexuan.l_blnIsRejectIpFlag)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        protected ProcessingResult validateDiscriminationCode(
            String l_strDiscriminationCode,
            String l_strOrderRootDiv,
            Map l_mapLoginTypeAttributes,
            Map l_mapLoginAttributes) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = " validateDiscriminationCode(String, String, Map, Map)";
            log.entering(STR_METHOD_NAME);

            if (!WEB3AcceptLoginServiceImplTest_xiexuan.l_expValidateDiscriminationCode_DiscriminationCodeValue.equals(l_strDiscriminationCode)
                || !WEB3AcceptLoginServiceImplTest_xiexuan.l_expValidateDiscriminationCode_OrderRootDivValue.equals(l_strOrderRootDiv)
                || !WEB3AcceptLoginServiceImplTest_xiexuan.l_expValidateDiscriminationCode_LoginTypeAttributesValue.equals(l_mapLoginTypeAttributes)
                )
            {
                WEB3AcceptLoginServiceImplTest_xiexuan.l_blnValidateDiscriminationCode__ParamCheck = true;
            }
            
            if (WEB3AcceptLoginServiceImplTest_xiexuan.l_blnProcessingResultFlag)
            {
                log.exiting(STR_METHOD_NAME);
                return ProcessingResult.SUCCESS_RESULT;
            }
            ErrorInfo errorInfo = WEB3ErrorCatalog.BUSINESS_ERROR_90221;

            log.exiting(STR_METHOD_NAME);
            return ProcessingResult.newFailedResultInstance(errorInfo);
        }
        
        protected boolean isLoginStop(BranchRow l_branchRow) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "isLoginStop()";
            log.entering(STR_METHOD_NAME);
         
            if (WEB3AcceptLoginServiceImplTest_xiexuan.l_blnIsLoginStopFlag)
            {
                return true;
            }
            return false;
        }
        
        protected boolean checkOrderChannel(String l_orderChannel)
        throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "checkOrderChannel(String)";
            log.entering(STR_METHOD_NAME);
            
            log.exiting(STR_METHOD_NAME);

            if (WEB3AcceptLoginServiceImplTest_xiexuan.l_blnCheckOrderChannelFlag)
            {
                return true;
            }
            return false;
        }
        
        protected boolean checkAcceptCode(String l_inputCode, BranchRow l_branchRow)
        throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "checkAcceptCode(String)";
            log.entering(STR_METHOD_NAME);
            
            log.exiting(STR_METHOD_NAME);

            if (WEB3AcceptLoginServiceImplTest_xiexuan.l_blnCheckAcceptCodeFlag)
            {
                return true;
            }
            return false;
        }
        
        protected boolean isEnabledUser(LoginInfo l_loginInfo) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "isEnabledUser()";
            log.entering(STR_METHOD_NAME);
            
            log.exiting(STR_METHOD_NAME);

            if (WEB3AcceptLoginServiceImplTest_xiexuan.l_blnIsEnabledUserFlag)
            {
                return true;
            }
            return false;
        }
        
        protected void setSessionAttribute(
            String l_sessionID,
            WEB3SetSessionRequest l_request)
            throws WEB3BaseException
        {
            
        }
        
        protected WEB3AcceptInfo getAcceptInfo(
            String l_acceptCode,
            MainAccountRow l_mainAccountRow,
            LoginInfo l_loginInfo)
            throws WEB3BaseException
        {
            return null;
        }
        
        protected WEB3InstitutionInfo getInstitutionInfo(
            InstitutionRow l_institutionRow)
            throws WEB3BaseException
        {
            return null;
        }
        
        protected WEB3BranchInfo getBranchInfo(
            BranchRow l_branchRow)
            throws WEB3BaseException
        {
            return null;
        }
        
        protected WEB3ServiceImpState getServiceImpState(
            InstitutionRow l_institutionRow,
            BranchRow l_branchRow,
            MainAccountRow l_mainAccountRow)
            throws WEB3BaseException
        {
            return null;
        }
    }


}
@
