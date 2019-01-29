head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.33.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminIfoDepShortageReferenceHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3AdminIfoDepShortageReferenceHandlerTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2009/03/10 劉剣（中訊）新規作成
*/
package webbroker3.ifoadmin.handler;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginTypeInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdminPermissionRow;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.gentrade.data.IfoDepositParams;
import webbroker3.gentrade.data.IfoDepositRow;
import webbroker3.gentrade.data.ProcessManagementParams;
import webbroker3.gentrade.data.ProcessManagementRow;
import webbroker3.ifoadmin.message.WEB3AdminIfoDepShortageRefInputRequest;
import webbroker3.ifoadmin.message.WEB3AdminIfoDepShortageRefInputResponse;
import webbroker3.ifoadmin.message.WEB3AdminIfoDepShortageReferenceRequest;
import webbroker3.ifoadmin.message.WEB3AdminIfoDepShortageReferenceResponse;
import webbroker3.ifoadmin.message.WEB3IfoDepShortageSortKey;
import webbroker3.ifoadmin.service.delegate.WEB3AdminIfoDepShortageReferenceService;
import webbroker3.ifodeposit.WEB3IfoDepositCalc;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminIfoDepShortageReferenceHandlerTest extends TestBaseForMock
{
    public WEB3AdminIfoDepShortageReferenceHandlerTest(String name)
    {
        super(name);
        // TODO Auto-generated constructor stub
    }

    private WEB3AdminIfoDepShortageReferenceService l_service = null;
    private WEB3AdminIfoDepShortageReferenceHandler l_handler = null;

    protected void setUp() throws Exception
    {
        super.setUp();
        l_handler = new WEB3AdminIfoDepShortageReferenceHandler();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3AdminIfoDepShortageReferenceHandlerTest.class);

    /**
     * サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
     * WEB3ErrorCatalog.SYSTEM_ERROR_80002
     */
    public void testGetInputScreen_C0001()
    {
        final String STR_METHOD_NAME = " testGetInputScreen_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminIfoDepShortageRefInputRequest l_request = new WEB3AdminIfoDepShortageRefInputRequest();
            l_service =
                (WEB3AdminIfoDepShortageReferenceService)Services.getService(
                    WEB3AdminIfoDepShortageReferenceService.class);
            Services.unregisterService(WEB3AdminIfoDepShortageReferenceService.class);

            WEB3AdminIfoDepShortageRefInputResponse l_response = l_handler.getInputScreen(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminIfoDepShortageReferenceService.class, l_service);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 管理者証拠金不足状況照会入力画面表示処理が失敗しました。
     * WEB3ErrorCatalog.SYSTEM_ERROR_80005
     */
    public void testGetInputScreen_C0002()
    {
        final String STR_METHOD_NAME = " testGetInputScreen_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            LoginInfo l_loginInfo = new LoginInfoForTest();
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {},
                    l_loginInfo);

            TestDBUtility.deleteAll(AdministratorRow.TYPE);

            WEB3AdminIfoDepShortageRefInputRequest l_request = new WEB3AdminIfoDepShortageRefInputRequest();
            WEB3AdminIfoDepShortageRefInputResponse l_response = l_handler.getInputScreen(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * normal case
     */
    public void testGetInputScreen_C0003()
    {
        final String STR_METHOD_NAME = " testGetInputScreen_C0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            LoginInfo l_loginInfo = new LoginInfoForTest();
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {},
                    l_loginInfo);

            Date l_dat = WEB3DateUtility.getDate("20090309", "yyyyMMdd");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                    "getBizDate",
                    new Class[] {},
                    l_dat);

            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setPermissionLevel("331");
            l_administratorParams.setBranchCode("001");
            l_administratorParams.setLoginId(33381330003L);
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0305");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            WEB3AdminIfoDepShortageRefInputRequest l_request = new WEB3AdminIfoDepShortageRefInputRequest();
            WEB3AdminIfoDepShortageRefInputResponse l_response = l_handler.getInputScreen(l_request);

            assertTrue(true);
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
     * WEB3ErrorCatalog.SYSTEM_ERROR_80002
     */
    public void testGetReferenceScreen_C0001()
    {
        final String STR_METHOD_NAME = " testGetReferenceScreen_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminIfoDepShortageReferenceRequest l_request = new WEB3AdminIfoDepShortageReferenceRequest();
            l_service =
                (WEB3AdminIfoDepShortageReferenceService)Services.getService(
                    WEB3AdminIfoDepShortageReferenceService.class);
            Services.unregisterService(WEB3AdminIfoDepShortageReferenceService.class);

            WEB3AdminIfoDepShortageReferenceResponse l_response = l_handler.getReferenceScreen(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3AdminIfoDepShortageReferenceService.class, l_service);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 管理者証拠金不足状況照会処理が失敗しました。
     * WEB3ErrorCatalog.BUSINESS_ERROR_03154
     */
    public void testGetReferenceScreen_C0002()
    {
        final String STR_METHOD_NAME = " testGetReferenceScreen_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminIfoDepShortageReferenceRequest l_request = new WEB3AdminIfoDepShortageReferenceRequest();
            WEB3AdminIfoDepShortageReferenceResponse l_response = l_handler.getReferenceScreen(l_request);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03154, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * normal case
     */
    public void testGetReferenceScreen_C0003()
    {
        final String STR_METHOD_NAME = " testGetReferenceScreen_C0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            LoginInfo l_loginInfo = new LoginInfoForTest();
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {},
                    l_loginInfo);

            Date l_dat = WEB3DateUtility.getDate("20090309", "yyyyMMdd");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                    "getBizDate",
                    new Class[] {},
                    l_dat);

            WEB3IfoDepositCalc l_ifoDepCalc = new WEB3IfoDepositCalcForTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifodeposit.WEB3IfoDepositCalcServiceImpl",
                    "getIfoDepositCalc",
                    new Class[]{ WEB3GentradeSubAccount.class },
                    l_ifoDepCalc);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            l_institutionParams.setInstitutionId(33L);
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setBranchCode("001");
            l_mainAccountParams.setAccountCode("1234567");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setPermissionLevel("331");
            l_administratorParams.setBranchCode("001");
            l_administratorParams.setLoginId(33381330003L);
            TestDBUtility.insertWithDel(l_administratorParams);

            TestDBUtility.deleteAll(AdminPermissionRow.TYPE);
            AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
            l_adminPermissionParams.setInstitutionCode("0D");
            l_adminPermissionParams.setPermissionLevel("331");
            l_adminPermissionParams.setTransactionCategory("C0305");
            l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_adminPermissionParams);

            TestDBUtility.deleteAll(ProcessManagementRow.TYPE);
            ProcessManagementParams l_processManagementParams = new ProcessManagementParams();
            l_processManagementParams.setProcessId("0001");
            l_processManagementParams.setInstitutionCode("0D");
            l_processManagementParams.setBranchCode("001");
            TestDBUtility.insertWithDel(l_processManagementParams);

            TestDBUtility.deleteAll(IfoDepositRow.TYPE);
            IfoDepositParams l_ifoDepositParams = new IfoDepositParams();
            l_ifoDepositParams.setInstitutionCode("0D");
            l_ifoDepositParams.setBranchCode("001");
            l_ifoDepositParams.setAccountCode("1234567");
            l_ifoDepositParams.setCalcDate(WEB3DateUtility.getDate("20090309", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_ifoDepositParams);

            WEB3AdminIfoDepShortageReferenceRequest l_request =
                new WEB3AdminIfoDepShortageReferenceRequest();
            l_request.searchDate = WEB3DateUtility.getDate("20090309", "yyyyMMdd");
            l_request.branchCode = new String[1];
            l_request.branchCode[0] = "001";
            l_request.uncancelDiv = "0";
            l_request.sortKeys = new WEB3IfoDepShortageSortKey[1];
            l_request.sortKeys[0] = new WEB3IfoDepShortageSortKey();
            l_request.sortKeys[0].keyItem = "branchCode";
            l_request.sortKeys[0].ascDesc = "A";
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            WEB3AdminIfoDepShortageReferenceResponse l_response = l_handler.getReferenceScreen(l_request);

            assertTrue(true);
        }
        catch (Exception l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
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
            return 33381330003L;
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
     * WEB3IfoDepositCalcForTest
     */
    private class WEB3IfoDepositCalcForTest extends WEB3IfoDepositCalc
    {
        public double calcNextBizDateDemandAmount()
        {
            return 11;
        }
        public double getCurrentBizDateDemandAmount()
        {
            return 22;
        }
        public double calcNonPayAmount()
        {
            return 33;
        }
        public double calcIfoDepositRequiredAmount()
        {
            return 44;
        }
        public double calcBuyContractQty(int l_intReservedDate)
        {
            return 55;
        }
        public double calcSellContractQty(int l_intReservedDate)
        {
            return 66;
        }
        public double calcBuyOrderQty(int l_intReservedDate)
        {
            return 77;
        }
        public double calcSellOrderQty(int l_intReservedDate)
        {
            return 88;
        }
    }
}
@
