head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.41.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminMCAdminDeleteServiceImplTest.java;


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
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderRow;

import test.util.TestDBUtility;

import webbroker3.adminmc.message.WEB3AdminMCAdminDeleteCompleteRequest;
import webbroker3.common.define.WEB3LoginTypeAttributeKeyDef;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdministratorDao;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.gentrade.data.AdministratorTypeParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminMCAdminDeleteServiceImplTest extends TestBaseForMock
{

    /**
     * ログ出力ユーティリティ。
     */
    private  static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMCAdminRegistServiceImplTest.class);
    WEB3AdminMCAdminDeleteServiceImpl l_serviceImpl = null;
    
    public WEB3AdminMCAdminDeleteServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        l_serviceImpl = new WEB3AdminMCAdminDeleteServiceImpl();
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }


//    public void testValidateAdministrator_T01()
//    {
//        final String STR_METHOD_NAME = "testValidateAdministrator_T01()";
//        log.entering(STR_METHOD_NAME);
//        try
//        {
//            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//            WEB3AdminMCAdminDeleteConfirmRequest l_confirmRequest =
//                new WEB3AdminMCAdminDeleteConfirmRequest();
//            l_confirmRequest.branchCode = "601";
//            l_confirmRequest.administratorCode = "330001";
//            
//            // AdministratorParams
//            TestDBUtility.deleteAll(AdministratorParams.TYPE);
//            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
//            l_administratorParams.setAdministratorId(33381330003L);
//            l_administratorParams.setAdministratorCode("330003");
//            l_administratorParams.setLoginId(33381330003L);
//            TestDBUtility.insertWithDel(l_administratorParams);
//            
//            try
//            {
//                QueryProcessor l_processor = Processors.getDefaultProcessor();
//                l_administratorParams.setAdministratorCode("330001");
//                l_administratorParams.setAdministratorId(33381330001L);
//                l_administratorParams.setLoginId(33381330001L);
//                l_processor.doInsertQuery(l_administratorParams);
//            }
//            catch(Exception l_exc)
//            {
//                l_exc.printStackTrace();
//                fail();
//            }
//            
//            l_administratorParams.setAdministratorId(33381330003L);
//            l_administratorParams.setAdministratorCode("330003");
//            l_administratorParams.setLoginId(33381330003L);
//
//            //AdminPermissionParams
//            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
//            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
//            l_adminPermissionParams.setTransactionCategory("D0101");
//            l_adminPermissionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
//            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
//            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
//            TestDBUtility.insertWithDel(l_adminPermissionParams);
//            
//            //OpLoginSecurityService
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
//                "getLoginInfo",
//                new Class[] {},
//                new LoginInfoImplForMock());
//
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
//                "getLoginId",
//                new Class[] {},
//                new Long(l_administratorParams.getLoginId()));
//            
//            //InstitutionParams
//            TestDBUtility.deleteAll(InstitutionParams.TYPE);
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            l_institutionParams.setInstitutionId(l_administratorParams.getInstitutionId());
//            l_institutionParams.setAdminCodeCheckMode("1");
//            l_institutionParams.setAdminCodeMax(6);
//            l_institutionParams.setAdminCodeMin(4);
//            l_institutionParams.setTrdCodeMax(6);
//            l_institutionParams.setTrdCodeMin(4);
//            l_institutionParams.setTrdCodeCheckMode("1");
//            TestDBUtility.insertWithDel(l_institutionParams);
//            
//            //AdministratorTypeParams
//            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
//            AdministratorTypeParams l_adminTypeParams = TestDBUtility.getAdministratorTypeRow();
//            l_adminTypeParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
//            l_adminTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
//            l_adminTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
//            l_adminTypeParams.setDirAdminFlag(1);
//            TestDBUtility.insertWithDel(l_adminTypeParams);
//            
//            l_serviceImpl.validateAdministrator(l_confirmRequest);
//            log.debug(STR_METHOD_NAME + "------------------>ok");
//        }
//        catch(Exception l_exc)
//        {
//            log.exiting(STR_METHOD_NAME);
//            l_exc.printStackTrace();
//            fail();
//        }
//        log.exiting(STR_METHOD_NAME);
//    }
//
//    public void testValidateAdministrator_T02()
//    {
//        final String STR_METHOD_NAME = "testValidateAdministrator_T02()";
//        log.entering(STR_METHOD_NAME);
//        try
//        {
//            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//            WEB3AdminMCAdminDeleteConfirmRequest l_confirmRequest =
//                new WEB3AdminMCAdminDeleteConfirmRequest();
//            l_confirmRequest.branchCode = "601";
//            l_confirmRequest.administratorCode = "33001";
//            
//            // AdministratorParams
//            TestDBUtility.deleteAll(AdministratorParams.TYPE);
//            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
//            l_administratorParams.setAdministratorId(33381330003L);
//            l_administratorParams.setAdministratorCode("330003");
//            l_administratorParams.setLoginId(33381330003L);
//            TestDBUtility.insertWithDel(l_administratorParams);
//            
//            //AdminPermissionParams
//            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
//            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
//            l_adminPermissionParams.setTransactionCategory("D0101");
//            l_adminPermissionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
//            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
//            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
//            TestDBUtility.insertWithDel(l_adminPermissionParams);
//            
//            try
//            {
//                QueryProcessor l_processor = Processors.getDefaultProcessor();
//                l_administratorParams.setAdministratorCode("33001");
//                l_administratorParams.setAdministratorId(33381330001L);
//                l_administratorParams.setLoginId(33381330001L);
//                l_processor.doInsertQuery(l_administratorParams);
//                
//                l_adminPermissionParams.setTransactionCategory("D0201");
//                l_processor.doInsertQuery(l_adminPermissionParams);
//            }
//            catch(Exception l_exc)
//            {
//                l_exc.printStackTrace();
//                fail();
//            }
//            
//            l_administratorParams.setAdministratorId(33381330003L);
//            l_administratorParams.setAdministratorCode("330003");
//            l_administratorParams.setLoginId(33381330003L);
//            
//            //OpLoginSecurityService
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
//                "getLoginInfo",
//                new Class[] {},
//                new LoginInfoImplForMock());
//
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
//                "getLoginId",
//                new Class[] {},
//                new Long(l_administratorParams.getLoginId()));
//            
//            //InstitutionParams
//            TestDBUtility.deleteAll(InstitutionParams.TYPE);
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            l_institutionParams.setInstitutionId(l_administratorParams.getInstitutionId());
//            l_institutionParams.setAdminCodeCheckMode("1");
//            l_institutionParams.setAdminCodeMax(6);
//            l_institutionParams.setAdminCodeMin(4);
//            l_institutionParams.setTrdCodeMax(6);
//            l_institutionParams.setTrdCodeMin(4);
//            l_institutionParams.setTrdCodeCheckMode("1");
//            TestDBUtility.insertWithDel(l_institutionParams);
//
//            //AdministratorTypeParams
//            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
//            AdministratorTypeParams l_adminTypeParams = TestDBUtility.getAdministratorTypeRow();
//            l_adminTypeParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
//            l_adminTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
//            l_adminTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
//            l_adminTypeParams.setDirAdminFlag(1);
//            TestDBUtility.insertWithDel(l_adminTypeParams);
//
//            //TraderParams
//            TestDBUtility.deleteAll(TraderParams.TYPE);
//            TraderParams l_traderParams = TestDBUtility.getTraderRow();
//            l_traderParams.setLoginId(l_administratorParams.getLoginId());
//            l_traderParams.setBranchCode("601");
//            l_traderParams.setTraderCode("33001");
//            TestDBUtility.insertWithDel(l_traderParams);
//
//            l_serviceImpl.validateAdministrator(l_confirmRequest);
//            log.debug(STR_METHOD_NAME + "------------------>ok");
//        }
//        catch(Exception l_exc)
//        {
//            log.exiting(STR_METHOD_NAME);
//            l_exc.printStackTrace();
//            fail();
//        }
//        log.exiting(STR_METHOD_NAME);
//    }
//
//    public void testSubmitAdministrator_T01()
//    {
//        final String STR_METHOD_NAME = "testSubmitAdministrator_T01()";
//        log.entering(STR_METHOD_NAME);
//        try
//        {
//            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//            WEB3AdminMCAdminDeleteCompleteRequest l_completeRequest =
//                new WEB3AdminMCAdminDeleteCompleteRequest();
//            l_completeRequest.branchCode = "601";
//            l_completeRequest.administratorCode = "33001";
//            l_completeRequest.password = "123456";
//
//            // AdministratorParams
//            TestDBUtility.deleteAll(AdministratorParams.TYPE);
//            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
//            l_administratorParams.setAdministratorId(33381330003L);
//            l_administratorParams.setAdministratorCode("330003");
//            l_administratorParams.setLoginId(33381330003L);
//            TestDBUtility.insertWithDel(l_administratorParams);
//            
//            //AdminPermissionParams
//            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
//            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
//            l_adminPermissionParams.setTransactionCategory("D0101");
//            l_adminPermissionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
//            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
//            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
//            TestDBUtility.insertWithDel(l_adminPermissionParams);
//            
//            try
//            {
//                QueryProcessor l_processor = Processors.getDefaultProcessor();
//                l_administratorParams.setAdministratorCode("33001");
//                l_administratorParams.setAdministratorId(33381330001L);
//                l_administratorParams.setLoginId(33381330001L);
//                l_processor.doInsertQuery(l_administratorParams);
//                
//                l_adminPermissionParams.setTransactionCategory("D0201");
//                l_processor.doInsertQuery(l_adminPermissionParams);
//            }
//            catch(Exception l_exc)
//            {
//                l_exc.printStackTrace();
//                fail();
//            }
//            
//            l_administratorParams.setAdministratorId(33381330003L);
//            l_administratorParams.setAdministratorCode("330003");
//            l_administratorParams.setLoginId(33381330003L);
//            
//            //OpLoginSecurityService
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
//                "checkPassword",
//                new Class[] {String.class},
//                Boolean.TRUE);
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
//                "getLoginInfo",
//                new Class[] {},
//                new LoginInfoImplForMock());
//
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
//                "getLoginId",
//                new Class[] {},
//                new Long(l_administratorParams.getLoginId()));
//            
//            //OpLoginAdminServiceImpl
//            HashMap l_resultMap2 = new HashMap();
//            l_resultMap2.put(WEB3LoginTypeAttributeKeyDef.PASSWORD_MIN_LENGTH, "4");
//            l_resultMap2.put(WEB3LoginTypeAttributeKeyDef.PASSWORD_MAX_LENGTH, "6");
//            l_resultMap2.put(WEB3LoginTypeAttributeKeyDef.PASSWORD_CHECK_MODE, "1");
//            l_resultMap2.put(WEB3LoginTypeAttributeKeyDef.TRADING_PWD_ENV, "0");
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl", 
//                "getLoginTypeAttributes",
//                new Class[] {long.class},
//                l_resultMap2);
//
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl", 
//                "setLoginAttributes",
//                new Class[] {long.class, Map.class},
//                null);
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl", 
//                "addLoginGroupManager",
//                new Class[] {long.class, long.class},
//                null);
//            
//            //InstitutionParams
//            TestDBUtility.deleteAll(InstitutionParams.TYPE);
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            l_institutionParams.setInstitutionId(l_administratorParams.getInstitutionId());
//            l_institutionParams.setAdminCodeCheckMode("1");
//            l_institutionParams.setAdminCodeMax(6);
//            l_institutionParams.setAdminCodeMin(4);
//            l_institutionParams.setTrdCodeMax(6);
//            l_institutionParams.setTrdCodeMin(4);
//            l_institutionParams.setTrdCodeCheckMode("1");
//            TestDBUtility.insertWithDel(l_institutionParams);
//            
//            //AdministratorTypeParams
//            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
//            AdministratorTypeParams l_adminTypeParams = TestDBUtility.getAdministratorTypeRow();
//            l_adminTypeParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
//            l_adminTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
//            l_adminTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
//            l_adminTypeParams.setDirAdminFlag(1);
//            TestDBUtility.insertWithDel(l_adminTypeParams);
//
//            //TraderParams
//            TestDBUtility.deleteAll(TraderParams.TYPE);
//            TraderParams l_traderParams = TestDBUtility.getTraderRow();
//            l_traderParams.setLoginId(l_administratorParams.getLoginId());
//            l_traderParams.setBranchCode("601");
//            l_traderParams.setTraderCode("33001");
//            TestDBUtility.insertWithDel(l_traderParams);
//            AdministratorRow l_administratorRow1 = AdministratorDao.findRowByLoginId(33381330001L);
//            AdministratorRow l_administratorRow2 = AdministratorDao.findRowByLoginId(33381330003L);
//
//            assertNotNull(l_administratorRow1);
//            assertNotNull(l_administratorRow2);
//            l_serviceImpl.submitAdministrator(l_completeRequest);
//            AdministratorRow l_administratorRow3 = AdministratorDao.findRowByLoginId(33381330001L);
//            assertNull(l_administratorRow3);
//            TraderRow l_traderRow = TraderDao.findRowByLoginId(l_administratorParams.getLoginId());
//            assertNull(l_traderRow);
//            
//            log.debug(STR_METHOD_NAME + "------------------>ok");
//        }
//        catch(Exception l_exc)
//        {
//            log.exiting(STR_METHOD_NAME);
//            l_exc.printStackTrace();
//            fail();
//        }
//        log.exiting(STR_METHOD_NAME);
//    }
    

    public void testSubmitAdministrator_T02()
    {
        final String STR_METHOD_NAME = "testSubmitAdministrator_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            WEB3AdminMCAdminDeleteCompleteRequest l_completeRequest =
                new WEB3AdminMCAdminDeleteCompleteRequest();
            l_completeRequest.branchCode = "601";
            l_completeRequest.administratorCode = "33001";
            l_completeRequest.password = "123456";

            // AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setAdministratorId(33381330003L);
            l_administratorParams.setAdministratorCode("330003");
            l_administratorParams.setLoginId(33381330003L);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            //AdminPermissionParams
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setTransactionCategory("D0101");
            l_adminPermissionParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminPermissionParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            try
            {
                QueryProcessor l_processor = Processors.getDefaultProcessor();
                l_administratorParams.setAdministratorCode("33001");
                l_administratorParams.setAdministratorId(33381330001L);
                l_administratorParams.setLoginId(33381330001L);
                l_processor.doInsertQuery(l_administratorParams);
                
                l_adminPermissionParams.setTransactionCategory("D0201");
                l_processor.doInsertQuery(l_adminPermissionParams);
            }
            catch(Exception l_exc)
            {
                l_exc.printStackTrace();
                fail();
            }
            
            l_administratorParams.setAdministratorId(33381330003L);
            l_administratorParams.setAdministratorCode("330003");
            l_administratorParams.setLoginId(33381330003L);
            
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
            
            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(l_administratorParams.getInstitutionId());
            l_institutionParams.setAdminCodeCheckMode("1");
            l_institutionParams.setAdminCodeMax(6);
            l_institutionParams.setAdminCodeMin(4);
            l_institutionParams.setTrdCodeMax(6);
            l_institutionParams.setTrdCodeMin(4);
            l_institutionParams.setTrdCodeCheckMode("1");
            TestDBUtility.insertWithDel(l_institutionParams);
            
            //AdministratorTypeParams
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            AdministratorTypeParams l_adminTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_adminTypeParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_adminTypeParams.setPermissionLevel(l_administratorParams.getPermissionLevel());
            l_adminTypeParams.setAllBranchPermissionFlag(BooleanEnum.TRUE);
            l_adminTypeParams.setDirAdminFlag(1);
            TestDBUtility.insertWithDel(l_adminTypeParams);

            //TraderParams
            TestDBUtility.deleteAll(TraderParams.TYPE);
//            TraderParams l_traderParams = TestDBUtility.getTraderRow();
//            l_traderParams.setLoginId(l_administratorParams.getLoginId());
//            l_traderParams.setBranchCode("601");
//            l_traderParams.setTraderCode("33001");
//            TestDBUtility.insertWithDel(l_traderParams);
            
            AdministratorRow l_administratorRow1 = AdministratorDao.findRowByLoginId(33381330001L);
            AdministratorRow l_administratorRow2 = AdministratorDao.findRowByLoginId(33381330003L);

            assertNotNull(l_administratorRow1);
            assertNotNull(l_administratorRow2);
            l_serviceImpl.submitAdministrator(l_completeRequest);
            AdministratorRow l_administratorRow3 = AdministratorDao.findRowByLoginId(33381330001L);
            assertNull(l_administratorRow3);
            TraderRow l_traderRow = TraderDao.findRowByLoginId(l_administratorParams.getLoginId());
            assertNull(l_traderRow);
            
            log.debug(STR_METHOD_NAME + "------------------>ok");
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
