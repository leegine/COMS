head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.41.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminMCAdminRegistServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.adminmc.service.delegate.stdimpls;

import java.util.HashMap;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginParams;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginTypeParams;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginUsernameDao;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginUsernamePK;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginUsernameParams;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginUsernameRow;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;

import test.util.TestDBUtility;

import webbroker3.adminmc.message.WEB3AdminMCAdminRegistCompleteRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminRegistConfirmRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminRegistUnit;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.define.WEB3LoginTypeAttributeKeyDef;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdministratorDao;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.gentrade.data.AdministratorTypeParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminMCAdminRegistServiceImplTest extends TestBaseForMock
{

    /**
     * ログ出力ユーティリティ。
     */
    private  static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMCAdminRegistServiceImplTest.class);

    WEB3AdminMCAdminRegistServiceImpl l_impl = null;

    public WEB3AdminMCAdminRegistServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        l_impl = new WEB3AdminMCAdminRegistServiceImpl();
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testValidateAdministrator_T01()
    {
        final String STR_METHOD_NAME = "testValidateAdministrator_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            WEB3AdminMCAdminRegistConfirmRequest l_confirmRequest =
                new WEB3AdminMCAdminRegistConfirmRequest();
            WEB3AdminMCAdminRegistUnit l_registUnit = new WEB3AdminMCAdminRegistUnit();
            l_registUnit.branchCode = "601";
            l_registUnit.administratorCode = "440001";
            l_registUnit.administratorName = "jiddk";
            l_registUnit.permissionLevel = "101";
            l_registUnit.password1 = "123456";
            l_registUnit.password2 = "123456";
            l_registUnit.mailAddress = "jiddk@@126.com";
//            l_registUnit.administratorOperatorDiv = "1";
//            l_registUnit.departmentCode = "12";
//            l_registUnit.accountOrderDiv = "0";
            
            l_confirmRequest.adminRegistUnit = l_registUnit;

            // AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            //AdminPermissionParams
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setTransactionCategory("D0101");
            l_adminPermissionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            l_adminPermissionParams.setTransactionCategory("");
            try
            {
                QueryProcessor l_processor = Processors.getDefaultProcessor();
                l_adminPermissionParams.setTransactionCategory("D0201");
                l_processor.doInsertQuery(l_adminPermissionParams);
            }
            catch(Exception l_exc)
            {
                l_exc.printStackTrace();
                fail();
            }

            //AdministratorTypeParams
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_adminTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_adminTypeParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            l_adminTypeParams.setDirAdminFlag(1);
            TestDBUtility.insertWithDel(l_adminTypeParams);

            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(l_administratorParams.getInstitutionId());
            l_institutionParams.setAdminCodeCheckMode("1");
            l_institutionParams.setAdminCodeMax(7);
            l_institutionParams.setAdminCodeMin(5);
            l_institutionParams.setTrdCodeMax(7);
            l_institutionParams.setTrdCodeMin(5);
            l_institutionParams.setTrdCodeCheckMode("1");
            TestDBUtility.insertWithDel(l_institutionParams);

            //BranchParams
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode(l_confirmRequest.adminRegistUnit.branchCode);
            l_branchParams.setInstitutionId(l_institutionParams.getInstitutionId());
            l_branchParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_branchParams);

            //OpLoginSecurityService
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(l_administratorParams.getLoginId()));
            
            //OpLoginAdminServiceImpl
            HashMap l_resultMap = new HashMap();
            l_resultMap.put(WEB3LoginTypeAttributeKeyDef.PASSWORD_MIN_LENGTH, "5");
            l_resultMap.put(WEB3LoginTypeAttributeKeyDef.PASSWORD_MAX_LENGTH, "7");
            l_resultMap.put(WEB3LoginTypeAttributeKeyDef.PASSWORD_CHECK_MODE, "1");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl", 
                "getLoginTypeAttributes",
                new Class[] {long.class},
                l_resultMap);

            l_impl.validateAdministrator(l_confirmRequest);
            log.info(STR_METHOD_NAME + "--------------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateAdministrator_T02()
    {
        final String STR_METHOD_NAME = "testValidateAdministrator_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            WEB3AdminMCAdminRegistConfirmRequest l_confirmRequest =
                new WEB3AdminMCAdminRegistConfirmRequest();
            WEB3AdminMCAdminRegistUnit l_registUnit = new WEB3AdminMCAdminRegistUnit();
            l_registUnit.branchCode = "601";
            l_registUnit.administratorCode = "440001";
            l_registUnit.administratorName = "jiddk";
            l_registUnit.permissionLevel = "101";
            l_registUnit.password1 = "123456";
            l_registUnit.password2 = "123456";
            l_registUnit.mailAddress = "jiddk@@126.com";
//            l_registUnit.administratorOperatorDiv = "0";
//            l_registUnit.departmentCode = "12";
//            l_registUnit.accountOrderDiv = "0";
//            
            l_confirmRequest.adminRegistUnit = l_registUnit;

            // AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            //AdminPermissionParams
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setTransactionCategory("D0101");
            l_adminPermissionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            l_adminPermissionParams.setTransactionCategory("");
            try
            {
                QueryProcessor l_processor = Processors.getDefaultProcessor();
                l_adminPermissionParams.setTransactionCategory("D0201");
                l_processor.doInsertQuery(l_adminPermissionParams);
            }
            catch(Exception l_exc)
            {
                l_exc.printStackTrace();
                fail();
            }

            //AdministratorTypeParams
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_adminTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_adminTypeParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            l_adminTypeParams.setDirAdminFlag(1);
            TestDBUtility.insertWithDel(l_adminTypeParams);

            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(l_administratorParams.getInstitutionId());
            l_institutionParams.setAdminCodeCheckMode("1");
            l_institutionParams.setAdminCodeMax(7);
            l_institutionParams.setAdminCodeMin(5);
            l_institutionParams.setTrdCodeMax(7);
            l_institutionParams.setTrdCodeMin(5);
            l_institutionParams.setTrdCodeCheckMode("1");
            TestDBUtility.insertWithDel(l_institutionParams);

            //BranchParams
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode(l_confirmRequest.adminRegistUnit.branchCode);
            l_branchParams.setInstitutionId(l_institutionParams.getInstitutionId());
            l_branchParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            TestDBUtility.insertWithDel(l_branchParams);

            //OpLoginSecurityService
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(l_administratorParams.getLoginId()));
            
            //OpLoginAdminServiceImpl
            HashMap l_resultMap = new HashMap();
            l_resultMap.put(WEB3LoginTypeAttributeKeyDef.PASSWORD_MIN_LENGTH, "5");
            l_resultMap.put(WEB3LoginTypeAttributeKeyDef.PASSWORD_MAX_LENGTH, "7");
            l_resultMap.put(WEB3LoginTypeAttributeKeyDef.PASSWORD_CHECK_MODE, "1");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl", 
                "getLoginTypeAttributes",
                new Class[] {long.class},
                l_resultMap);

            l_impl.validateAdministrator(l_confirmRequest);
            log.info(STR_METHOD_NAME + "--------------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitAdministrator_T01()
    {
        final String STR_METHOD_NAME = "testSubmitAdministrator_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            WEB3AdminMCAdminRegistCompleteRequest l_completeRequest =
                new WEB3AdminMCAdminRegistCompleteRequest();
            WEB3AdminMCAdminRegistUnit l_registUnit =
                new WEB3AdminMCAdminRegistUnit();
            
            l_registUnit.branchCode = "601";
            l_registUnit.administratorCode = "44001";
            l_registUnit.administratorName = "jiddk";
            l_registUnit.permissionLevel = "101";
            l_registUnit.password1 = "123456";
            l_registUnit.password2 = "123456";
            l_registUnit.mailAddress = "jiddk@@126.com";
//            l_registUnit.administratorOperatorDiv = "1";
//            l_registUnit.departmentCode = "12";
//            l_registUnit.accountOrderDiv = "0";
            
            l_completeRequest.adminRegistUnit = l_registUnit;
            l_completeRequest.password = "123456";
            
            // AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            //AdminPermissionParams
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setTransactionCategory("D0101");
            l_adminPermissionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            l_adminPermissionParams.setTransactionCategory("");
            try
            {
                QueryProcessor l_processor = Processors.getDefaultProcessor();
                l_adminPermissionParams.setTransactionCategory("D0201");
                l_processor.doInsertQuery(l_adminPermissionParams);
            }
            catch(Exception l_exc)
            {
                l_exc.printStackTrace();
                fail();
            }

            //AdministratorTypeParams
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_adminTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_adminTypeParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            l_adminTypeParams.setDirAdminFlag(1);
            TestDBUtility.insertWithDel(l_adminTypeParams);

            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(l_administratorParams.getInstitutionId());
            l_institutionParams.setAdminCodeCheckMode("1");
            l_institutionParams.setAdminCodeMax(7);
            l_institutionParams.setAdminCodeMin(5);
            l_institutionParams.setTrdCodeMax(7);
            l_institutionParams.setTrdCodeMin(5);
            l_institutionParams.setTrdCodeCheckMode("1");
            TestDBUtility.insertWithDel(l_institutionParams);

            //BranchParams
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode(l_completeRequest.adminRegistUnit.branchCode);
            l_branchParams.setInstitutionId(l_institutionParams.getInstitutionId());
            l_branchParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            l_branchParams.setTraderTypeId(1001);
            TestDBUtility.insertWithDel(l_branchParams);

            //LoginTypeParams
            TestDBUtility.deleteAll(LoginTypeParams.TYPE);
            LoginTypeParams l_loginTypeParams = new LoginTypeParams();
            l_loginTypeParams.setTypeId(1001);
            l_loginTypeParams.setTypeName("jiddk");
            TestDBUtility.insertWithDel(l_loginTypeParams);

            //LoginParams
            TestDBUtility.deleteAll(LoginParams.TYPE);
            LoginParams l_loginParams = new LoginParams();
            l_loginParams.setLoginId(33381330001L);
            l_loginParams.setTypeId(l_loginTypeParams.getTypeId());
            l_loginParams.setInitialPassword("123456");
            l_loginParams.setDisabled(1);
            TestDBUtility.insertWithDel(l_loginParams);
            
            //OpLoginSecurityService
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "checkPassword",
                new Class[] {String.class},
                Boolean.TRUE);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(l_administratorParams.getLoginId()));
            
            //OpLoginAdminServiceImpl
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl", 
//                "createLogin",
//                new Class[] {long.class, String.class, String.class},
//                new LoginInfoImplForMock());

            HashMap l_resultMap2 = new HashMap();
            l_resultMap2.put(WEB3LoginTypeAttributeKeyDef.PASSWORD_MIN_LENGTH, "4");
            l_resultMap2.put(WEB3LoginTypeAttributeKeyDef.PASSWORD_MAX_LENGTH, "6");
            l_resultMap2.put(WEB3LoginTypeAttributeKeyDef.PASSWORD_CHECK_MODE, "1");
            l_resultMap2.put(WEB3LoginTypeAttributeKeyDef.TRADING_PWD_ENV, "0");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl", 
                "getLoginTypeAttributes",
                new Class[] {long.class},
                l_resultMap2);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl", 
                "setLoginAttributes",
                new Class[] {long.class, Map.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl", 
                "addLoginGroupManager",
                new Class[] {long.class, long.class},
                null);

            //TraderParams
            TestDBUtility.deleteAll(TraderParams.TYPE);

            //LoginUsernameParams
            TestDBUtility.deleteAll(LoginUsernameParams.TYPE);
            
            l_impl.submitAdministrator(l_completeRequest);
            
            LoginUsernameRow l_loginUserNameRow = null;
            AdministratorRow l_administratorRow = null;
            try
            {
                l_loginUserNameRow = LoginUsernameDao.findRowByPk(new LoginUsernamePK("30D44001"));
                long l_lngLoginId = l_loginUserNameRow.getLoginId();
                l_administratorRow = AdministratorDao.findRowByLoginId(l_lngLoginId);
                
            }
            catch(Exception l_exc)
            {
                l_exc.printStackTrace();
            }

            assertNotNull(l_loginUserNameRow);
            assertEquals(l_registUnit.administratorCode,  l_administratorRow.getAdministratorCode());
            assertEquals(l_institutionParams.institution_id, l_administratorRow.getInstitutionId());
            assertEquals(l_institutionParams.institution_code, l_administratorRow.getInstitutionCode());
            assertEquals(l_registUnit.branchCode, l_administratorRow.getBranchCode());

            assertEquals(l_registUnit.administratorName, l_administratorRow.getName());
            assertEquals(l_registUnit.mailAddress, l_administratorRow.getEmailAddress());
            assertNull(l_administratorRow.getTradingPassword());
            assertEquals(l_registUnit.permissionLevel, l_administratorRow.getPermissionLevel());
            assertEquals(l_administratorParams.getAdministratorCode(), l_administratorRow.getLastUpdater());

            log.info(STR_METHOD_NAME + "--------------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSubmitAdministrator_T02()
    {
        final String STR_METHOD_NAME = "testSubmitAdministrator_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            WEB3AdminMCAdminRegistCompleteRequest l_completeRequest =
                new WEB3AdminMCAdminRegistCompleteRequest();
            WEB3AdminMCAdminRegistUnit l_registUnit =
                new WEB3AdminMCAdminRegistUnit();
            
            l_registUnit.branchCode = "601";
            l_registUnit.administratorCode = "44001";
            l_registUnit.administratorName = "jiddk";
            l_registUnit.permissionLevel = "101";
            l_registUnit.password1 = "123456";
            l_registUnit.password2 = "123456";
            l_registUnit.mailAddress = "jiddk@@126.com";
//            l_registUnit.administratorOperatorDiv = "1";
//            l_registUnit.departmentCode = "12";
//            l_registUnit.accountOrderDiv = "0";
            
            l_completeRequest.adminRegistUnit = l_registUnit;
            l_completeRequest.password = "123456";
            
            // AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setTradingPassword("123456");
            TestDBUtility.insertWithDel(l_administratorParams);

            //AdminPermissionParams
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setTransactionCategory("D0101");
            l_adminPermissionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            l_adminPermissionParams.setTransactionCategory("");
            try
            {
                QueryProcessor l_processor = Processors.getDefaultProcessor();
                l_adminPermissionParams.setTransactionCategory("D0201");
                l_processor.doInsertQuery(l_adminPermissionParams);
            }
            catch(Exception l_exc)
            {
                l_exc.printStackTrace();
                fail();
            }

            //AdministratorTypeParams
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_adminTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_adminTypeParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            l_adminTypeParams.setDirAdminFlag(1);
            TestDBUtility.insertWithDel(l_adminTypeParams);

            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(l_administratorParams.getInstitutionId());
            l_institutionParams.setAdminCodeCheckMode("1");
            l_institutionParams.setAdminCodeMax(7);
            l_institutionParams.setAdminCodeMin(5);
            l_institutionParams.setTrdCodeMax(7);
            l_institutionParams.setTrdCodeMin(5);
            l_institutionParams.setTrdCodeCheckMode("1");
            TestDBUtility.insertWithDel(l_institutionParams);

            //BranchParams
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode(l_completeRequest.adminRegistUnit.branchCode);
            l_branchParams.setInstitutionId(l_institutionParams.getInstitutionId());
            l_branchParams.setInstitutionCode(l_institutionParams.getInstitutionCode());
            l_branchParams.setTraderTypeId(1001);
            TestDBUtility.insertWithDel(l_branchParams);

            //LoginTypeParams
            TestDBUtility.deleteAll(LoginTypeParams.TYPE);
            LoginTypeParams l_loginTypeParams = new LoginTypeParams();
            l_loginTypeParams.setTypeId(1001);
            l_loginTypeParams.setTypeName("jiddk");
            TestDBUtility.insertWithDel(l_loginTypeParams);

            //LoginParams
            TestDBUtility.deleteAll(LoginParams.TYPE);
            LoginParams l_loginParams = new LoginParams();
            l_loginParams.setLoginId(33381330001L);
            l_loginParams.setTypeId(l_loginTypeParams.getTypeId());
            l_loginParams.setInitialPassword("123456");
            l_loginParams.setDisabled(1);
            TestDBUtility.insertWithDel(l_loginParams);
            
            //OpLoginSecurityService
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "checkPassword",
                new Class[] {String.class},
                Boolean.TRUE);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImplForMock());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(l_administratorParams.getLoginId()));
            
            //OpLoginAdminServiceImpl
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl", 
//                "createLogin",
//                new Class[] {long.class, String.class, String.class},
//                new LoginInfoImplForMock());

            HashMap l_resultMap2 = new HashMap();
            l_resultMap2.put(WEB3LoginTypeAttributeKeyDef.PASSWORD_MIN_LENGTH, "4");
            l_resultMap2.put(WEB3LoginTypeAttributeKeyDef.PASSWORD_MAX_LENGTH, "6");
            l_resultMap2.put(WEB3LoginTypeAttributeKeyDef.PASSWORD_CHECK_MODE, "1");
            l_resultMap2.put(WEB3LoginTypeAttributeKeyDef.TRADING_PWD_ENV, "1");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl", 
                "getLoginTypeAttributes",
                new Class[] {long.class},
                l_resultMap2);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl", 
                "setLoginAttributes",
                new Class[] {long.class, Map.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl", 
                "addLoginGroupManager",
                new Class[] {long.class, long.class},
                null);

            //TraderParams
            TestDBUtility.deleteAll(TraderParams.TYPE);

            //LoginUsernameParams
            TestDBUtility.deleteAll(LoginUsernameParams.TYPE);
            
            l_impl.submitAdministrator(l_completeRequest);
            
            LoginUsernameRow l_loginUserNameRow = null;
            AdministratorRow l_administratorRow = null;
            try
            {
                l_loginUserNameRow = LoginUsernameDao.findRowByPk(new LoginUsernamePK("30D44001"));
                long l_lngLoginId = l_loginUserNameRow.getLoginId();
                l_administratorRow = AdministratorDao.findRowByLoginId(l_lngLoginId);
                
            }
            catch(Exception l_exc)
            {
                l_exc.printStackTrace();
            }

            WEB3Crypt l_crypt = new WEB3Crypt();
            
            assertNotNull(l_loginUserNameRow);
            assertEquals(l_registUnit.administratorCode,  l_administratorRow.getAdministratorCode());
            assertEquals(l_institutionParams.institution_id, l_administratorRow.getInstitutionId());
            assertEquals(l_institutionParams.institution_code, l_administratorRow.getInstitutionCode());
            assertEquals(l_registUnit.branchCode, l_administratorRow.getBranchCode());

            assertEquals(l_registUnit.administratorName, l_administratorRow.getName());
            assertEquals(l_registUnit.mailAddress, l_administratorRow.getEmailAddress());
            assertEquals(l_crypt.encrypt("123456"), l_administratorRow.getTradingPassword());
            assertEquals(l_registUnit.permissionLevel, l_administratorRow.getPermissionLevel());
            assertEquals(l_administratorParams.getAdministratorCode(), l_administratorRow.getLastUpdater());

            log.info(STR_METHOD_NAME + "--------------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

}
@
