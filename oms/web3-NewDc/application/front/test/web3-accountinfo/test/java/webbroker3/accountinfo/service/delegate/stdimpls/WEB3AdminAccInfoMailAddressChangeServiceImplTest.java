head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.06.08.32.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	1e84d9c24e17fc0;
filename	WEB3AdminAccInfoMailAddressChangeServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountinfo.service.delegate.stdimpls;

import java.util.HashMap;
import java.util.Map;

import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;

import test.util.TestDBUtility;

import webbroker3.accountinfo.message.WEB3AccInfoMultiMailAddressInfo;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeCompleteRequest;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressChangeConfirmRequest;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3LoginTypeAttributeKeyDef;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorTypeParams;
import webbroker3.gentrade.data.BranchPreferencesParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminAccInfoMailAddressChangeServiceImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoMailAddressChangeServiceImplTest.class);

    public WEB3AdminAccInfoMailAddressChangeServiceImplTest(String arg0)
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

    public void testValidateChange_0001()
    {
        final String STR_METHOD_NAME = " testValidateChange_0001()";
        log.entering(STR_METHOD_NAME );

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
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

        WEB3GentradeMainAccount l_gentradeMainAccount =
            new WEB3GentradeMainAccount(TestDBUtility.getMainAccountRow());
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.gentrade.WEB3GentradeAccountManager",
            "getMainAccount",
            new Class[] {String.class, String.class, String.class},
            l_gentradeMainAccount);
        try
        {
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("338");
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("A0101");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel("331");
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            
            WEB3AdminAccInfoMailAddressChangeConfirmRequestForMock l_request =
                new WEB3AdminAccInfoMailAddressChangeConfirmRequestForMock();
            l_request.branchCode = "338";
            l_request.changedMailAddress = null;
            l_request.multiMailAddressInfo = new WEB3AccInfoMultiMailAddressInfo();
            l_request.multiMailAddressInfo.mailAddress2 = "xie@@sinacom.cn";
            l_request.multiMailAddressInfo.mailAddressDelFlag2 = true;

            WEB3AdminAccInfoMailAddressChangeServiceImpl l_impl = new WEB3AdminAccInfoMailAddressChangeServiceImpl();
            l_impl.validateChange(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02899, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitChange_0001()
    {
        final String STR_METHOD_NAME = " testSubmitChange_0001()";
        log.entering(STR_METHOD_NAME );

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
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
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "checkPassword",
            new Class[] {String.class},
            Boolean.TRUE);
        
        Map l_map = new HashMap();
        l_map.put(WEB3LoginTypeAttributeKeyDef.TRADING_PWD_ENV, "0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
            "getLoginTypeAttributes",
            new Class[] {long.class},
            l_map);

        WEB3GentradeMainAccount l_gentradeMainAccount =
            new WEB3GentradeMainAccount(TestDBUtility.getMainAccountRow());
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.gentrade.WEB3GentradeAccountManager",
            "getMainAccount",
            new Class[] {String.class, String.class, String.class},
            l_gentradeMainAccount);
        try
        {
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("338");
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("A0101");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel("331");
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            
            WEB3AdminAccInfoMailAddressChangeCompleteRequestForMock l_request =
                new WEB3AdminAccInfoMailAddressChangeCompleteRequestForMock();
            l_request.branchCode = "338";
            l_request.changedMailAddress = null;
            l_request.multiMailAddressInfo = new WEB3AccInfoMultiMailAddressInfo();
            l_request.multiMailAddressInfo.mailAddress2 = "xie@@sinacom.cn";
            l_request.multiMailAddressInfo.mailAddressDelFlag2 = true;

            WEB3AdminAccInfoMailAddressChangeServiceImpl l_impl = new WEB3AdminAccInfoMailAddressChangeServiceImpl();
            l_impl.submitChange(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02899, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitChange_0002()
    {
        final String STR_METHOD_NAME = " testSubmitChange_0002()";
        log.entering(STR_METHOD_NAME );

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
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
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "checkPassword",
            new Class[] {String.class},
            Boolean.TRUE);
        
        Map l_map = new HashMap();
        l_map.put(WEB3LoginTypeAttributeKeyDef.TRADING_PWD_ENV, "0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
            "getLoginTypeAttributes",
            new Class[] {long.class},
            l_map);

        WEB3GentradeMainAccount l_gentradeMainAccount =
            new WEB3GentradeMainAccount(TestDBUtility.getMainAccountRow());
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.gentrade.WEB3GentradeAccountManager",
            "getMainAccount",
            new Class[] {String.class, String.class, String.class},
            l_gentradeMainAccount);
        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);

            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setName(WEB3BranchPreferencesNameDef.PRE_NAME_DUPLO);
            l_branchPreferencesParams.setBranchId(33381L);
            l_branchPreferencesParams.setNameSerialNo(1);
            l_branchPreferencesParams.setValue("0");
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            l_branchParams.setBranchCode("338");
            TestDBUtility.insertWithDel(l_branchParams);
            
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            l_InstitutionParams.setInstitutionCode("0D");
            l_InstitutionParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_InstitutionParams);
            
            MainAccountParams l_MainAccountParams = TestDBUtility.getMainAccountRow();
            l_MainAccountParams.setInstitutionCode("0D");
            l_MainAccountParams.setInstitutionId(33L);
            l_MainAccountParams.setAccountId(333812512246L);
            
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("338");
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("A0101");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel("331");
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            
            WEB3AdminAccInfoMailAddressChangeCompleteRequestForMock l_request =
                new WEB3AdminAccInfoMailAddressChangeCompleteRequestForMock();
            l_request.branchCode = "338";
            l_request.changedMailAddress = null;
            l_request.multiMailAddressInfo = new WEB3AccInfoMultiMailAddressInfo();
            l_request.multiMailAddressInfo.mailAddress2 = "xie@@sinacom.cn";
            l_request.multiMailAddressInfo.mailAddressDelFlag2 = false;
            l_request.mailAddressDelFlag = true;
            l_request.multiMailAddressInfo.mailAddressDelFlag3 = true;

            WEB3AdminAccInfoMailAddressChangeServiceImpl l_impl = new WEB3AdminAccInfoMailAddressChangeServiceImpl();
            l_impl.submitChange(l_request);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testSubmitChange_0003()
    {
        final String STR_METHOD_NAME = " testSubmitChange_0003()";
        log.entering(STR_METHOD_NAME );

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
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
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "checkPassword",
            new Class[] {String.class},
            Boolean.TRUE);
        
        Map l_map = new HashMap();
        l_map.put(WEB3LoginTypeAttributeKeyDef.TRADING_PWD_ENV, "0");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
            "getLoginTypeAttributes",
            new Class[] {long.class},
            l_map);

        WEB3GentradeMainAccount l_gentradeMainAccount =
            new WEB3GentradeMainAccount(TestDBUtility.getMainAccountRow());
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.gentrade.WEB3GentradeAccountManager",
            "getMainAccount",
            new Class[] {String.class, String.class, String.class},
            l_gentradeMainAccount);
        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);

            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setName(WEB3BranchPreferencesNameDef.PRE_NAME_DUPLO);
            l_branchPreferencesParams.setBranchId(33381L);
            l_branchPreferencesParams.setNameSerialNo(1);
            l_branchPreferencesParams.setValue("0");
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            l_branchParams.setBranchCode("338");
            TestDBUtility.insertWithDel(l_branchParams);
            
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            l_InstitutionParams.setInstitutionCode("0D");
            l_InstitutionParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_InstitutionParams);
            
            MainAccountParams l_MainAccountParams = TestDBUtility.getMainAccountRow();
            l_MainAccountParams.setInstitutionCode("0D");
            l_MainAccountParams.setInstitutionId(33L);
            l_MainAccountParams.setAccountId(333812512246L);

            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setBranchCode("338");
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            TestDBUtility.insertWithDel(l_administratorParams);
            
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("A0101");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.deleteAll(AdminPermissionParams.TYPE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);
            
            AdministratorTypeParams l_administratorTypeParams = TestDBUtility.getAdministratorTypeRow();
            l_administratorTypeParams.setInstitutionCode("0D");
            l_administratorTypeParams.setPermissionLevel("331");
            TestDBUtility.deleteAll(AdministratorTypeParams.TYPE);
            TestDBUtility.insertWithDel(l_administratorTypeParams);
            
            WEB3AdminAccInfoMailAddressChangeCompleteRequestForMock l_request =
                new WEB3AdminAccInfoMailAddressChangeCompleteRequestForMock();
            l_request.branchCode = "338";
            l_request.changedMailAddress = "xie@@sinacom.cn";
            l_request.multiMailAddressInfo = new WEB3AccInfoMultiMailAddressInfo();
            l_request.multiMailAddressInfo.mailAddress3 = "xie@@sinacom.cn";
            l_request.multiMailAddressInfo.mailAddress2 = null;
            l_request.multiMailAddressInfo.mailAddressDelFlag2 = true;
            l_request.mailAddressDelFlag = false;
            l_request.multiMailAddressInfo.mailAddressDelFlag3 = false;

            WEB3AdminAccInfoMailAddressChangeServiceImpl l_impl = new WEB3AdminAccInfoMailAddressChangeServiceImpl();
            l_impl.submitChange(l_request);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    private class WEB3AdminAccInfoMailAddressChangeConfirmRequestForMock extends WEB3AdminAccInfoMailAddressChangeConfirmRequest
    {
        public void validate() throws WEB3BaseException
        {
            
        }
    }
    
    private class WEB3AdminAccInfoMailAddressChangeCompleteRequestForMock extends WEB3AdminAccInfoMailAddressChangeCompleteRequest
    {
        public void validate() throws WEB3BaseException
        {
            
        }
    }
}
@
