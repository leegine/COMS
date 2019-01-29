head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.06.08.32.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	1e84d9c24e17fc0;
filename	WEB3AccInfoMailAddressChangeServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountinfo.service.delegate.stdimpls;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import test.util.TestDBUtility;
import webbroker3.accountinfo.message.WEB3AccInfoMailAddressChangeCompleteRequest;
import webbroker3.accountinfo.message.WEB3AccInfoMailAddressChangeConfirmRequest;
import webbroker3.accountinfo.message.WEB3AccInfoMailAddressInfoUnit;
import webbroker3.accountinfo.message.WEB3AccInfoMailAddressTypeUnit;
import webbroker3.accountinfo.message.WEB3AccInfoMultiMailAddressInfo;
import webbroker3.accountinfo.message.WEB3AccInfoMultiMailAddressList;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.BranchPreferencesParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginTypeInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

public class WEB3AccInfoMailAddressChangeServiceImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AccInfoMailAddressChangeServiceImplTest.class);

    public WEB3AccInfoMailAddressChangeServiceImplTest(String arg0)
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

    public class LoginInfoForTest implements LoginInfo
    {

        public long getAccountId()
        {
            return 1;
        }

        public LoginTypeInfo getLoginTypeInfo()
        {
            return null;
        }

        public long getLoginId()
        {
            return 0;
        }

        public long getLoginTypeId()
        {
            return 0;
        }

        public String getUsername()
        {
            return null;
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
            return false;
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
            return null;
        }

        public boolean isAccountReachable(long arg0)
        {
            return false;
        }

        public boolean hasFailedLogin()
        {
            return false;
        }

        public int getFailureCount()
        {
            return 0;
        }

        public Date getLastFailureTimestamp()
        {
            return null;
        }

		public Map getLoginAttributes() {
			// TODO Auto-generated method stub
			return null;
		}

		public Map getLoginTypeAttributes() {
			// TODO Auto-generated method stub
			return null;
		}

		public Long getDefaultAccountId() {
			// TODO Auto-generated method stub
			return null;
		}
    }

    /**
     * 
     */
    public void test_validateChange_C0001()
    {

        final String STR_METHOD_NAME = " test_validateChange_C0001()";
        log.info(TEST_START + STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                    new Class[]
                    {}, new Long(1001L));
            LoginInfoForTest l_loginInfoForTest = new LoginInfoForTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[]
                    {}, l_loginInfoForTest);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setAccountCode("190600");
            l_mainAccountParams.setBranchCode("368");
            l_mainAccountParams.setBranchId(1001L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setName(WEB3BranchPreferencesNameDef.ACCOUNTINFO_MULTI_MAILADDRESS_FLAG);
            l_branchPreferencesParams.setBranchId(1001L);
            l_branchPreferencesParams.setNameSerialNo(1);
            l_branchPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            l_branchPreferencesParams.setName(WEB3BranchPreferencesNameDef.PRE_NAME_DUPLO);
            l_branchPreferencesParams.setBranchId(1001L);
            l_branchPreferencesParams.setNameSerialNo(1);
            l_branchPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1001L);
            l_branchParams.setBranchCode("368");
            TestDBUtility.insertWithDel(l_branchParams);

            WEB3AccInfoMailAddressChangeServiceImpl l_accInfoMailAddressChangeServiceImpl = new WEB3AccInfoMailAddressChangeServiceImpl();
            WEB3AccInfoMailAddressChangeConfirmRequest l_request = new WEB3AccInfoMailAddressChangeConfirmRequest();
            l_request.mailAddressDelFlag = false;
            l_request.changedMailAddress = "wu-bo@@sinocom.cn";
            WEB3AccInfoMultiMailAddressInfo l_accInfoMultiMailAddressInfo = new WEB3AccInfoMultiMailAddressInfo();
            l_accInfoMultiMailAddressInfo.mailAddress2 = "wu-bo@@sinocom.cn";
            l_accInfoMultiMailAddressInfo.mailAddressDelFlag2 = false;
            l_accInfoMultiMailAddressInfo.execMailFlag = "2";
            l_accInfoMultiMailAddressInfo.unExecMailFlag = "2";
            l_accInfoMultiMailAddressInfo.importantMailFlag = "2";
            l_accInfoMultiMailAddressInfo.guidanceMailFlag2 = "2";
            l_request.multiMailAddressInfo = l_accInfoMultiMailAddressInfo;
            l_accInfoMailAddressChangeServiceImpl.validateChange(l_request);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
        log.info(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 
     */
    public void test_validateChange_C0002()
    {

        final String STR_METHOD_NAME = " test_validateChange_C0002()";
        log.info(TEST_START + STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                    new Class[]
                    {}, new Long(1001L));
            LoginInfoForTest l_loginInfoForTest = new LoginInfoForTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[]
                    {}, l_loginInfoForTest);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setAccountCode("190600");
            l_mainAccountParams.setBranchCode("368");
            l_mainAccountParams.setBranchId(1001L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setName(WEB3BranchPreferencesNameDef.ACCOUNTINFO_MULTI_MAILADDRESS_FLAG);
            l_branchPreferencesParams.setBranchId(1001L);
            l_branchPreferencesParams.setNameSerialNo(1);
            l_branchPreferencesParams.setValue("2");
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            l_branchPreferencesParams.setName(WEB3BranchPreferencesNameDef.PRE_NAME_DUPLO);
            l_branchPreferencesParams.setBranchId(1001L);
            l_branchPreferencesParams.setNameSerialNo(1);
            l_branchPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1001L);
            l_branchParams.setBranchCode("368");
            TestDBUtility.insertWithDel(l_branchParams);

            WEB3AccInfoMailAddressChangeServiceImpl l_accInfoMailAddressChangeServiceImpl = new WEB3AccInfoMailAddressChangeServiceImpl();
            WEB3AccInfoMailAddressChangeConfirmRequest l_request = new WEB3AccInfoMailAddressChangeConfirmRequest();
            l_request.mailAddressDelFlag = false;
            l_request.changedMailAddress = "wu-bo@@sinocom.cn";
            WEB3AccInfoMultiMailAddressInfo l_accInfoMultiMailAddressInfo = new WEB3AccInfoMultiMailAddressInfo();
            l_accInfoMultiMailAddressInfo.mailAddress2 = "wu-bo@@sinocom.cn";
            l_accInfoMultiMailAddressInfo.mailAddressDelFlag2 = false;
            l_accInfoMultiMailAddressInfo.execMailFlag = "2";
            l_accInfoMultiMailAddressInfo.unExecMailFlag = "2";
            l_accInfoMultiMailAddressInfo.importantMailFlag = "2";
            l_accInfoMultiMailAddressInfo.guidanceMailFlag2 = "2";
            l_request.multiMailAddressInfo = l_accInfoMultiMailAddressInfo;
            WEB3AccInfoMultiMailAddressList l_multiMailAddressList = new WEB3AccInfoMultiMailAddressList();
            WEB3AccInfoMailAddressInfoUnit[] l_mailAddressInfoList = new WEB3AccInfoMailAddressInfoUnit[1];
            l_mailAddressInfoList[0] = new WEB3AccInfoMailAddressInfoUnit();
            l_mailAddressInfoList[0].mailAddress = "test@@sinocom.cn";
            l_mailAddressInfoList[0].mailAddressDiv = "1";
            l_mailAddressInfoList[0].mailAddressNo = "1";
            l_multiMailAddressList.mailAddressInfoList = l_mailAddressInfoList;
            l_request.multiMailAddressList = l_multiMailAddressList;

            l_accInfoMailAddressChangeServiceImpl.validateChange(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(l_ex.getErrorMessage(), "テーブルに該当するデータがありません。");
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80005);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
        log.info(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 
     */
    public void test_validateChange_C0003()
    {

        final String STR_METHOD_NAME = " test_validateChange_C0003()";
        log.info(TEST_START + STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                    new Class[]
                    {}, new Long(1001L));
            LoginInfoForTest l_loginInfoForTest = new LoginInfoForTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[]
                    {}, l_loginInfoForTest);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setAccountCode("190600");
            l_mainAccountParams.setBranchCode("368");
            l_mainAccountParams.setBranchId(1001L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setName(WEB3BranchPreferencesNameDef.ACCOUNTINFO_MULTI_MAILADDRESS_FLAG);
            l_branchPreferencesParams.setBranchId(1001L);
            l_branchPreferencesParams.setNameSerialNo(1);
            l_branchPreferencesParams.setValue("2");
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            l_branchPreferencesParams.setName(WEB3BranchPreferencesNameDef.PRE_NAME_DUPLO);
            l_branchPreferencesParams.setBranchId(1001L);
            l_branchPreferencesParams.setNameSerialNo(1);
            l_branchPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1001L);
            l_branchParams.setBranchCode("368");
            TestDBUtility.insertWithDel(l_branchParams);

            SystemPreferencesParams l_systemPreferencesParams = TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("mobile.mail.");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            WEB3AccInfoMailAddressChangeServiceImpl l_accInfoMailAddressChangeServiceImpl = new WEB3AccInfoMailAddressChangeServiceImpl();
            WEB3AccInfoMailAddressChangeConfirmRequest l_request = new WEB3AccInfoMailAddressChangeConfirmRequest();
            l_request.mailAddressDelFlag = false;
            l_request.changedMailAddress = "wu-bo@@sinocom.cn";
            WEB3AccInfoMultiMailAddressInfo l_accInfoMultiMailAddressInfo = new WEB3AccInfoMultiMailAddressInfo();
            l_accInfoMultiMailAddressInfo.mailAddress2 = "wu-bo@@sinocom.cn";
            l_accInfoMultiMailAddressInfo.mailAddressDelFlag2 = false;
            l_accInfoMultiMailAddressInfo.execMailFlag = "2";
            l_accInfoMultiMailAddressInfo.unExecMailFlag = "2";
            l_accInfoMultiMailAddressInfo.importantMailFlag = "2";
            l_accInfoMultiMailAddressInfo.guidanceMailFlag2 = "2";
            l_request.multiMailAddressInfo = l_accInfoMultiMailAddressInfo;
            WEB3AccInfoMultiMailAddressList l_multiMailAddressList = new WEB3AccInfoMultiMailAddressList();
            WEB3AccInfoMailAddressInfoUnit[] l_mailAddressInfoList = new WEB3AccInfoMailAddressInfoUnit[1];
            l_mailAddressInfoList[0] = new WEB3AccInfoMailAddressInfoUnit();
            l_mailAddressInfoList[0].mailAddress = "test@@sinocom.cn";
            l_mailAddressInfoList[0].mailAddressDiv = "1";
            l_mailAddressInfoList[0].mailAddressNo = "1";
            l_multiMailAddressList.mailAddressInfoList = l_mailAddressInfoList;
            l_request.multiMailAddressList = l_multiMailAddressList;

            l_accInfoMailAddressChangeServiceImpl.validateChange(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_02443);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
        log.info(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 
     */
    public void test_validateChange_C0004()
    {

        final String STR_METHOD_NAME = " test_validateChange_C0004()";
        log.info(TEST_START + STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                    new Class[]
                    {}, new Long(1001L));
            LoginInfoForTest l_loginInfoForTest = new LoginInfoForTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[]
                    {}, l_loginInfoForTest);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setAccountCode("190600");
            l_mainAccountParams.setBranchCode("368");
            l_mainAccountParams.setBranchId(1001L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setName(WEB3BranchPreferencesNameDef.ACCOUNTINFO_MULTI_MAILADDRESS_FLAG);
            l_branchPreferencesParams.setBranchId(1001L);
            l_branchPreferencesParams.setNameSerialNo(1);
            l_branchPreferencesParams.setValue("2");
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            l_branchPreferencesParams.setName(WEB3BranchPreferencesNameDef.PRE_NAME_DUPLO);
            l_branchPreferencesParams.setBranchId(1001L);
            l_branchPreferencesParams.setNameSerialNo(1);
            l_branchPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1001L);
            l_branchParams.setBranchCode("368");
            TestDBUtility.insertWithDel(l_branchParams);

            SystemPreferencesParams l_systemPreferencesParams = TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("mobile.mail.");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            WEB3AccInfoMailAddressChangeServiceImpl l_accInfoMailAddressChangeServiceImpl = new WEB3AccInfoMailAddressChangeServiceImpl();
            WEB3AccInfoMailAddressChangeConfirmRequest l_request = new WEB3AccInfoMailAddressChangeConfirmRequest();
            l_request.mailAddressDelFlag = false;
            l_request.changedMailAddress = "wu-bo@@sinocom.cn";
            WEB3AccInfoMultiMailAddressInfo l_accInfoMultiMailAddressInfo = new WEB3AccInfoMultiMailAddressInfo();
            l_accInfoMultiMailAddressInfo.mailAddress2 = "wu-bo@@sinocom.cn";
            l_accInfoMultiMailAddressInfo.mailAddressDelFlag2 = false;
            l_accInfoMultiMailAddressInfo.execMailFlag = "2";
            l_accInfoMultiMailAddressInfo.unExecMailFlag = "2";
            l_accInfoMultiMailAddressInfo.importantMailFlag = "2";
            l_accInfoMultiMailAddressInfo.guidanceMailFlag2 = "2";
            l_request.multiMailAddressInfo = l_accInfoMultiMailAddressInfo;
            WEB3AccInfoMultiMailAddressList l_multiMailAddressList = new WEB3AccInfoMultiMailAddressList();
            WEB3AccInfoMailAddressInfoUnit[] l_mailAddressInfoList = new WEB3AccInfoMailAddressInfoUnit[1];
            l_mailAddressInfoList[0] = new WEB3AccInfoMailAddressInfoUnit();
            l_mailAddressInfoList[0].mailAddress = "test@@sinocom.cn";
            l_mailAddressInfoList[0].mailAddressDiv = "2";
            l_mailAddressInfoList[0].mailAddressNo = "1";
            l_multiMailAddressList.mailAddressInfoList = l_mailAddressInfoList;
            l_request.multiMailAddressList = l_multiMailAddressList;

            l_accInfoMailAddressChangeServiceImpl.validateChange(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_03189);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
        log.info(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 
     */
    public void test_validateChange_C0005()
    {

        final String STR_METHOD_NAME = " test_validateChange_C0005()";
        log.info(TEST_START + STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                    new Class[]
                    {}, new Long(1001L));
            LoginInfoForTest l_loginInfoForTest = new LoginInfoForTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[]
                    {}, l_loginInfoForTest);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setAccountCode("190600");
            l_mainAccountParams.setBranchCode("368");
            l_mainAccountParams.setBranchId(1001L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setName(WEB3BranchPreferencesNameDef.ACCOUNTINFO_MULTI_MAILADDRESS_FLAG);
            l_branchPreferencesParams.setBranchId(1001L);
            l_branchPreferencesParams.setNameSerialNo(1);
            l_branchPreferencesParams.setValue("2");
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            l_branchPreferencesParams.setName(WEB3BranchPreferencesNameDef.PRE_NAME_DUPLO);
            l_branchPreferencesParams.setBranchId(1001L);
            l_branchPreferencesParams.setNameSerialNo(1);
            l_branchPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1001L);
            l_branchParams.setBranchCode("368");
            TestDBUtility.insertWithDel(l_branchParams);

            SystemPreferencesParams l_systemPreferencesParams = TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("mobile.mail.");
            l_systemPreferencesParams.setValue("sinocom.cn");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            WEB3AccInfoMailAddressChangeServiceImpl l_accInfoMailAddressChangeServiceImpl = new WEB3AccInfoMailAddressChangeServiceImpl();
            WEB3AccInfoMailAddressChangeConfirmRequest l_request = new WEB3AccInfoMailAddressChangeConfirmRequest();
            l_request.mailAddressDelFlag = false;
            l_request.changedMailAddress = "wu-bo@@sinocom.cn";
            WEB3AccInfoMultiMailAddressInfo l_accInfoMultiMailAddressInfo = new WEB3AccInfoMultiMailAddressInfo();
            l_accInfoMultiMailAddressInfo.mailAddress2 = "wu-bo@@sinocom.cn";
            l_accInfoMultiMailAddressInfo.mailAddressDelFlag2 = false;
            l_accInfoMultiMailAddressInfo.execMailFlag = "2";
            l_accInfoMultiMailAddressInfo.unExecMailFlag = "2";
            l_accInfoMultiMailAddressInfo.importantMailFlag = "2";
            l_accInfoMultiMailAddressInfo.guidanceMailFlag2 = "2";
            l_request.multiMailAddressInfo = l_accInfoMultiMailAddressInfo;
            WEB3AccInfoMultiMailAddressList l_multiMailAddressList = new WEB3AccInfoMultiMailAddressList();
            WEB3AccInfoMailAddressInfoUnit[] l_mailAddressInfoList = new WEB3AccInfoMailAddressInfoUnit[1];
            l_mailAddressInfoList[0] = new WEB3AccInfoMailAddressInfoUnit();
            l_mailAddressInfoList[0].mailAddress = "test@@sinocom.cn";
            l_mailAddressInfoList[0].mailAddressDiv = "2";
            l_mailAddressInfoList[0].mailAddressNo = "1";
            l_multiMailAddressList.mailAddressInfoList = l_mailAddressInfoList;
            l_request.multiMailAddressList = l_multiMailAddressList;

            l_accInfoMailAddressChangeServiceImpl.validateChange(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_02443);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
        log.info(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 
     */
    public void test_validateChange_C0006()
    {

        final String STR_METHOD_NAME = " test_validateChange_C0006()";
        log.info(TEST_START + STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                    new Class[]
                    {}, new Long(1001L));
            LoginInfoForTest l_loginInfoForTest = new LoginInfoForTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[]
                    {}, l_loginInfoForTest);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setAccountCode("190600");
            l_mainAccountParams.setBranchCode("368");
            l_mainAccountParams.setBranchId(1001L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setName(WEB3BranchPreferencesNameDef.ACCOUNTINFO_MULTI_MAILADDRESS_FLAG);
            l_branchPreferencesParams.setBranchId(1001L);
            l_branchPreferencesParams.setNameSerialNo(1);
            l_branchPreferencesParams.setValue("2");
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            l_branchPreferencesParams.setName(WEB3BranchPreferencesNameDef.PRE_NAME_DUPLO);
            l_branchPreferencesParams.setBranchId(1001L);
            l_branchPreferencesParams.setNameSerialNo(1);
            l_branchPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1001L);
            l_branchParams.setBranchCode("368");
            TestDBUtility.insertWithDel(l_branchParams);

            WEB3AccInfoMailAddressChangeServiceImpl l_accInfoMailAddressChangeServiceImpl = new WEB3AccInfoMailAddressChangeServiceImpl();
            WEB3AccInfoMailAddressChangeConfirmRequest l_request = new WEB3AccInfoMailAddressChangeConfirmRequest();
            l_request.mailAddressDelFlag = false;
            l_request.changedMailAddress = "wu-bo@@sinocom.cn";
            WEB3AccInfoMultiMailAddressInfo l_accInfoMultiMailAddressInfo = new WEB3AccInfoMultiMailAddressInfo();
            l_accInfoMultiMailAddressInfo.mailAddress2 = "wu-bo@@sinocom.cn";
            l_accInfoMultiMailAddressInfo.mailAddressDelFlag2 = false;
            l_accInfoMultiMailAddressInfo.execMailFlag = "2";
            l_accInfoMultiMailAddressInfo.unExecMailFlag = "2";
            l_accInfoMultiMailAddressInfo.importantMailFlag = "2";
            l_accInfoMultiMailAddressInfo.guidanceMailFlag2 = "2";
            l_request.multiMailAddressInfo = l_accInfoMultiMailAddressInfo;
            WEB3AccInfoMultiMailAddressList l_multiMailAddressList = new WEB3AccInfoMultiMailAddressList();
            WEB3AccInfoMailAddressInfoUnit[] l_mailAddressInfoList = new WEB3AccInfoMailAddressInfoUnit[1];
            l_mailAddressInfoList[0] = new WEB3AccInfoMailAddressInfoUnit();
            l_mailAddressInfoList[0].mailAddress = "test@@sinocom.cn";
            l_mailAddressInfoList[0].mailAddressDiv = "2";
            l_mailAddressInfoList[0].mailAddressNo = "1";
            l_multiMailAddressList.mailAddressInfoList = l_mailAddressInfoList;
            l_request.multiMailAddressList = l_multiMailAddressList;

            l_accInfoMailAddressChangeServiceImpl.validateChange(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80005);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
        log.info(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 
     */
    public void test_submitChange_C0001()
    {

        final String STR_METHOD_NAME = " test_submitChange_C0001()";
        log.info(TEST_START + STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                    new Class[]
                    {}, new Long(1001L));
            LoginInfoForTest l_loginInfoForTest = new LoginInfoForTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[]
                    {}, l_loginInfoForTest);

            OrderValidationResult l_result = new OrderValidationResult(ProcessingResult.SUCCESS_RESULT);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateTradingPassword", new Class[]
                    {Trader.class, SubAccount.class, String.class}, l_result);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(1001L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setAccountCode("190600");
            l_mainAccountParams.setBranchCode("368");
            l_mainAccountParams.setBranchId(1001L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setName(WEB3BranchPreferencesNameDef.ACCOUNTINFO_MULTI_MAILADDRESS_FLAG);
            l_branchPreferencesParams.setBranchId(1001L);
            l_branchPreferencesParams.setNameSerialNo(1);
            l_branchPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            l_branchPreferencesParams.setName(WEB3BranchPreferencesNameDef.PRE_NAME_DUPLO);
            l_branchPreferencesParams.setBranchId(1001L);
            l_branchPreferencesParams.setNameSerialNo(1);
            l_branchPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1001L);
            l_branchParams.setBranchCode("368");
            TestDBUtility.insertWithDel(l_branchParams);

            WEB3AccInfoMailAddressChangeServiceImpl l_accInfoMailAddressChangeServiceImpl = new WEB3AccInfoMailAddressChangeServiceImpl();
            WEB3AccInfoMailAddressChangeCompleteRequest l_request = new WEB3AccInfoMailAddressChangeCompleteRequest();
            l_request.mailAddressDelFlag = false;
            l_request.changedMailAddress = "wu-bo@@sinocom.cn";
            WEB3AccInfoMultiMailAddressInfo l_accInfoMultiMailAddressInfo = new WEB3AccInfoMultiMailAddressInfo();
            l_accInfoMultiMailAddressInfo.mailAddress2 = "wu-bo@@sinocom.cn";
            l_accInfoMultiMailAddressInfo.mailAddressDelFlag2 = false;
            l_accInfoMultiMailAddressInfo.execMailFlag = "2";
            l_accInfoMultiMailAddressInfo.unExecMailFlag = "2";
            l_accInfoMultiMailAddressInfo.importantMailFlag = "2";
            l_accInfoMultiMailAddressInfo.guidanceMailFlag2 = "2";
            l_accInfoMultiMailAddressInfo.mailAddressDelFlag3 = true;
            l_request.multiMailAddressInfo = l_accInfoMultiMailAddressInfo;
            l_accInfoMailAddressChangeServiceImpl.submitChange(l_request);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
        log.info(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 
     */
    public void test_submitChange_C0002()
    {

        final String STR_METHOD_NAME = " test_submitChange_C0002()";
        log.info(TEST_START + STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                    new Class[]
                    {}, new Long(1001L));
            LoginInfoForTest l_loginInfoForTest = new LoginInfoForTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[]
                    {}, l_loginInfoForTest);

            OrderValidationResult l_result = new OrderValidationResult(ProcessingResult.SUCCESS_RESULT);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateTradingPassword", new Class[]
                    {Trader.class, SubAccount.class, String.class}, l_result);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(1001L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setAccountCode("190600");
            l_mainAccountParams.setBranchCode("368");
            l_mainAccountParams.setBranchId(1001L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setName(WEB3BranchPreferencesNameDef.ACCOUNTINFO_MULTI_MAILADDRESS_FLAG);
            l_branchPreferencesParams.setBranchId(1001L);
            l_branchPreferencesParams.setNameSerialNo(1);
            l_branchPreferencesParams.setValue("0");
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            l_branchPreferencesParams.setName(WEB3BranchPreferencesNameDef.PRE_NAME_DUPLO);
            l_branchPreferencesParams.setBranchId(1001L);
            l_branchPreferencesParams.setNameSerialNo(1);
            l_branchPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1001L);
            l_branchParams.setBranchCode("368");
            TestDBUtility.insertWithDel(l_branchParams);

            WEB3AccInfoMailAddressChangeServiceImpl l_accInfoMailAddressChangeServiceImpl = new WEB3AccInfoMailAddressChangeServiceImpl();
            WEB3AccInfoMailAddressChangeCompleteRequest l_request = new WEB3AccInfoMailAddressChangeCompleteRequest();
            l_request.mailAddressDelFlag = true;
            WEB3AccInfoMultiMailAddressInfo l_accInfoMultiMailAddressInfo = new WEB3AccInfoMultiMailAddressInfo();
            l_accInfoMultiMailAddressInfo.mailAddress3 = "wu-bo@@sinocom.cn";
            l_accInfoMultiMailAddressInfo.mailAddressDelFlag2 = true;
            l_accInfoMultiMailAddressInfo.execMailFlag = "2";
            l_accInfoMultiMailAddressInfo.unExecMailFlag = "2";
            l_accInfoMultiMailAddressInfo.importantMailFlag = "2";
            l_accInfoMultiMailAddressInfo.guidanceMailFlag2 = "2";
            l_accInfoMultiMailAddressInfo.mailAddressDelFlag3 = false;
            l_request.multiMailAddressInfo = l_accInfoMultiMailAddressInfo;
            l_accInfoMailAddressChangeServiceImpl.submitChange(l_request);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
        log.info(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 
     */
    public void test_submitChange_C0003()
    {

        final String STR_METHOD_NAME = " test_submitChange_C0003()";
        log.info(TEST_START + STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                    new Class[]
                    {}, new Long(1001L));
            LoginInfoForTest l_loginInfoForTest = new LoginInfoForTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[]
                    {}, l_loginInfoForTest);

            OrderValidationResult l_result = new OrderValidationResult(ProcessingResult.SUCCESS_RESULT);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateTradingPassword", new Class[]
                    {Trader.class, SubAccount.class, String.class}, l_result);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(1001L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setAccountCode("190600");
            l_mainAccountParams.setBranchCode("368");
            l_mainAccountParams.setBranchId(1001L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setName(WEB3BranchPreferencesNameDef.ACCOUNTINFO_MULTI_MAILADDRESS_FLAG);
            l_branchPreferencesParams.setBranchId(1001L);
            l_branchPreferencesParams.setNameSerialNo(1);
            l_branchPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            l_branchPreferencesParams.setName(WEB3BranchPreferencesNameDef.PRE_NAME_DUPLO);
            l_branchPreferencesParams.setBranchId(1001L);
            l_branchPreferencesParams.setNameSerialNo(1);
            l_branchPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1001L);
            l_branchParams.setBranchCode("368");
            TestDBUtility.insertWithDel(l_branchParams);

            WEB3AccInfoMailAddressChangeServiceImpl l_accInfoMailAddressChangeServiceImpl = new WEB3AccInfoMailAddressChangeServiceImpl();
            WEB3AccInfoMailAddressChangeCompleteRequest l_request = new WEB3AccInfoMailAddressChangeCompleteRequest();
            l_request.mailAddressDelFlag = true;
            WEB3AccInfoMultiMailAddressInfo l_accInfoMultiMailAddressInfo = new WEB3AccInfoMultiMailAddressInfo();
            l_accInfoMultiMailAddressInfo.mailAddress2 = "wu-bo@@sinocom.cn";
            l_accInfoMultiMailAddressInfo.mailAddressDelFlag2 = false;
            l_accInfoMultiMailAddressInfo.execMailFlag = "2";
            l_accInfoMultiMailAddressInfo.unExecMailFlag = "2";
            l_accInfoMultiMailAddressInfo.importantMailFlag = "2";
            l_accInfoMultiMailAddressInfo.guidanceMailFlag2 = "2";
            l_accInfoMultiMailAddressInfo.mailAddressDelFlag3 = true;
            l_request.multiMailAddressInfo = l_accInfoMultiMailAddressInfo;
            l_accInfoMailAddressChangeServiceImpl.submitChange(l_request);
            fail();
        }
        catch (WEB3BusinessLayerException l_businessLayerException)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02892, l_businessLayerException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
        log.info(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 
     */
    public void test_submitChange_C0004()
    {

        final String STR_METHOD_NAME = " test_submitChange_C0004()";
        log.info(TEST_START + STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                    new Class[]
                    {}, new Long(1001L));
            LoginInfoForTest l_loginInfoForTest = new LoginInfoForTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[]
                    {}, l_loginInfoForTest);

            OrderValidationResult l_result = new OrderValidationResult(ProcessingResult.SUCCESS_RESULT);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateTradingPassword", new Class[]
                    {Trader.class, SubAccount.class, String.class}, l_result);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(1001L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setAccountCode("190600");
            l_mainAccountParams.setBranchCode("368");
            l_mainAccountParams.setBranchId(1001L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setName(WEB3BranchPreferencesNameDef.ACCOUNTINFO_MULTI_MAILADDRESS_FLAG);
            l_branchPreferencesParams.setBranchId(1001L);
            l_branchPreferencesParams.setNameSerialNo(1);
            l_branchPreferencesParams.setValue("2");
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            l_branchPreferencesParams.setName(WEB3BranchPreferencesNameDef.PRE_NAME_DUPLO);
            l_branchPreferencesParams.setBranchId(1001L);
            l_branchPreferencesParams.setNameSerialNo(1);
            l_branchPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1001L);
            l_branchParams.setBranchCode("368");
            TestDBUtility.insertWithDel(l_branchParams);

            WEB3AccInfoMailAddressChangeServiceImpl l_accInfoMailAddressChangeServiceImpl = new WEB3AccInfoMailAddressChangeServiceImpl();
            WEB3AccInfoMailAddressChangeCompleteRequest l_request = new WEB3AccInfoMailAddressChangeCompleteRequest();
            l_request.mailAddressDelFlag = true;
            WEB3AccInfoMultiMailAddressInfo l_accInfoMultiMailAddressInfo = new WEB3AccInfoMultiMailAddressInfo();
            l_accInfoMultiMailAddressInfo.mailAddress3 = "wu-bo@@sinocom.cn";
            l_accInfoMultiMailAddressInfo.mailAddressDelFlag2 = true;
            l_accInfoMultiMailAddressInfo.execMailFlag = "2";
            l_accInfoMultiMailAddressInfo.unExecMailFlag = "2";
            l_accInfoMultiMailAddressInfo.importantMailFlag = "2";
            l_accInfoMultiMailAddressInfo.guidanceMailFlag2 = "2";
            l_accInfoMultiMailAddressInfo.mailAddressDelFlag3 = false;
            l_request.multiMailAddressInfo = l_accInfoMultiMailAddressInfo;

            WEB3AccInfoMultiMailAddressList l_multiMailAddressList = new WEB3AccInfoMultiMailAddressList();
            WEB3AccInfoMailAddressInfoUnit[] l_mailAddressInfoList = new WEB3AccInfoMailAddressInfoUnit[1];
            l_mailAddressInfoList[0] = new WEB3AccInfoMailAddressInfoUnit();
            l_mailAddressInfoList[0].mailAddress = "test@@sinocom.cn";
            l_mailAddressInfoList[0].mailAddressDiv = "2";
            l_mailAddressInfoList[0].mailAddressNo = "1";
            l_multiMailAddressList.mailAddressInfoList = l_mailAddressInfoList;

            WEB3AccInfoMailAddressTypeUnit[] l_accInfoMailAddressTypeUnits =
                new WEB3AccInfoMailAddressTypeUnit[1];
            l_accInfoMailAddressTypeUnits[0] = new WEB3AccInfoMailAddressTypeUnit();
            l_accInfoMailAddressTypeUnits[0].mailAddressNo = "1";
            l_accInfoMailAddressTypeUnits[0].mailAddressTypeNo = "1";
            l_multiMailAddressList.mailTypeInfoList = l_accInfoMailAddressTypeUnits;
            l_request.multiMailAddressList = l_multiMailAddressList;
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            l_accInfoMailAddressChangeServiceImpl.submitChange(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80005);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
        log.info(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 
     */
    public void test_submitChange_C0005()
    {

        final String STR_METHOD_NAME = " test_submitChange_C0005()";
        log.info(TEST_START + STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                    new Class[]
                    {}, new Long(1001L));
            LoginInfoForTest l_loginInfoForTest = new LoginInfoForTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[]
                    {}, l_loginInfoForTest);

            OrderValidationResult l_result = new OrderValidationResult(ProcessingResult.SUCCESS_RESULT);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateTradingPassword", new Class[]
                    {Trader.class, SubAccount.class, String.class}, l_result);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_systemPreferencesParams = TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("mobile.mail.");
            l_systemPreferencesParams.setValue("sinocom.cn");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(1001L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setAccountCode("190600");
            l_mainAccountParams.setBranchCode("368");
            l_mainAccountParams.setBranchId(1001L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setName(WEB3BranchPreferencesNameDef.ACCOUNTINFO_MULTI_MAILADDRESS_FLAG);
            l_branchPreferencesParams.setBranchId(1001L);
            l_branchPreferencesParams.setNameSerialNo(1);
            l_branchPreferencesParams.setValue("2");
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            l_branchPreferencesParams.setName(WEB3BranchPreferencesNameDef.PRE_NAME_DUPLO);
            l_branchPreferencesParams.setBranchId(1001L);
            l_branchPreferencesParams.setNameSerialNo(1);
            l_branchPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1001L);
            l_branchParams.setBranchCode("368");
            TestDBUtility.insertWithDel(l_branchParams);

            WEB3AccInfoMailAddressChangeServiceImpl l_accInfoMailAddressChangeServiceImpl = new WEB3AccInfoMailAddressChangeServiceImpl();
            WEB3AccInfoMailAddressChangeCompleteRequest l_request = new WEB3AccInfoMailAddressChangeCompleteRequest();
            l_request.mailAddressDelFlag = true;
            WEB3AccInfoMultiMailAddressInfo l_accInfoMultiMailAddressInfo = new WEB3AccInfoMultiMailAddressInfo();
            l_accInfoMultiMailAddressInfo.mailAddress3 = "wu-bo@@sinocom.cn";
            l_accInfoMultiMailAddressInfo.mailAddressDelFlag2 = true;
            l_accInfoMultiMailAddressInfo.execMailFlag = "2";
            l_accInfoMultiMailAddressInfo.unExecMailFlag = "2";
            l_accInfoMultiMailAddressInfo.importantMailFlag = "2";
            l_accInfoMultiMailAddressInfo.guidanceMailFlag2 = "2";
            l_accInfoMultiMailAddressInfo.mailAddressDelFlag3 = false;
            l_request.multiMailAddressInfo = l_accInfoMultiMailAddressInfo;

            WEB3AccInfoMultiMailAddressList l_multiMailAddressList = new WEB3AccInfoMultiMailAddressList();
            WEB3AccInfoMailAddressInfoUnit[] l_mailAddressInfoList = new WEB3AccInfoMailAddressInfoUnit[1];
            l_mailAddressInfoList[0] = new WEB3AccInfoMailAddressInfoUnit();
            l_mailAddressInfoList[0].mailAddress = "test@@sinocom.cn";
            l_mailAddressInfoList[0].mailAddressDiv = "2";
            l_mailAddressInfoList[0].mailAddressNo = "1";
            l_multiMailAddressList.mailAddressInfoList = l_mailAddressInfoList;

            WEB3AccInfoMailAddressTypeUnit[] l_accInfoMailAddressTypeUnits =
                new WEB3AccInfoMailAddressTypeUnit[1];
            l_accInfoMailAddressTypeUnits[0] = new WEB3AccInfoMailAddressTypeUnit();
            l_accInfoMailAddressTypeUnits[0].mailAddressNo = "1";
            l_accInfoMailAddressTypeUnits[0].mailAddressTypeNo = "1";
            l_multiMailAddressList.mailTypeInfoList = l_accInfoMailAddressTypeUnits;
            l_request.multiMailAddressList = l_multiMailAddressList;
            l_accInfoMailAddressChangeServiceImpl.submitChange(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_02443);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
        log.info(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 
     */
    public void test_submitChange_C0006()
    {

        final String STR_METHOD_NAME = " test_submitChange_C0006()";
        log.info(TEST_START + STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                    new Class[]
                    {}, new Long(1001L));
            LoginInfoForTest l_loginInfoForTest = new LoginInfoForTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[]
                    {}, l_loginInfoForTest);

            OrderValidationResult l_result = new OrderValidationResult(ProcessingResult.SUCCESS_RESULT);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateTradingPassword", new Class[]
                    {Trader.class, SubAccount.class, String.class}, l_result);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_systemPreferencesParams = TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("mobile.mail.");
            l_systemPreferencesParams.setValue("sinocom.cn");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(1001L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setAccountCode("190600");
            l_mainAccountParams.setBranchCode("368");
            l_mainAccountParams.setBranchId(1001L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setName(WEB3BranchPreferencesNameDef.ACCOUNTINFO_MULTI_MAILADDRESS_FLAG);
            l_branchPreferencesParams.setBranchId(1001L);
            l_branchPreferencesParams.setNameSerialNo(1);
            l_branchPreferencesParams.setValue("2");
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            l_branchPreferencesParams.setName(WEB3BranchPreferencesNameDef.PRE_NAME_DUPLO);
            l_branchPreferencesParams.setBranchId(1001L);
            l_branchPreferencesParams.setNameSerialNo(1);
            l_branchPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1001L);
            l_branchParams.setBranchCode("368");
            TestDBUtility.insertWithDel(l_branchParams);

            WEB3AccInfoMailAddressChangeServiceImpl l_accInfoMailAddressChangeServiceImpl = new WEB3AccInfoMailAddressChangeServiceImpl();
            WEB3AccInfoMailAddressChangeCompleteRequest l_request = new WEB3AccInfoMailAddressChangeCompleteRequest();
            l_request.mailAddressDelFlag = true;
            WEB3AccInfoMultiMailAddressInfo l_accInfoMultiMailAddressInfo = new WEB3AccInfoMultiMailAddressInfo();
            l_accInfoMultiMailAddressInfo.mailAddress3 = "wu-bo@@sinocom.cn";
            l_accInfoMultiMailAddressInfo.mailAddressDelFlag2 = true;
            l_accInfoMultiMailAddressInfo.execMailFlag = "2";
            l_accInfoMultiMailAddressInfo.unExecMailFlag = "2";
            l_accInfoMultiMailAddressInfo.importantMailFlag = "2";
            l_accInfoMultiMailAddressInfo.guidanceMailFlag2 = "2";
            l_accInfoMultiMailAddressInfo.mailAddressDelFlag3 = false;
            l_request.multiMailAddressInfo = l_accInfoMultiMailAddressInfo;

            WEB3AccInfoMultiMailAddressList l_multiMailAddressList = new WEB3AccInfoMultiMailAddressList();
            WEB3AccInfoMailAddressInfoUnit[] l_mailAddressInfoList = new WEB3AccInfoMailAddressInfoUnit[1];
            l_mailAddressInfoList[0] = new WEB3AccInfoMailAddressInfoUnit();
            l_mailAddressInfoList[0].mailAddress = "test@@sinocom.cn";
            l_mailAddressInfoList[0].mailAddressDiv = "1";
            l_mailAddressInfoList[0].mailAddressNo = "1";
            l_multiMailAddressList.mailAddressInfoList = l_mailAddressInfoList;

            WEB3AccInfoMailAddressTypeUnit[] l_accInfoMailAddressTypeUnits =
                new WEB3AccInfoMailAddressTypeUnit[1];
            l_accInfoMailAddressTypeUnits[0] = new WEB3AccInfoMailAddressTypeUnit();
            l_accInfoMailAddressTypeUnits[0].mailAddressNo = "1";
            l_accInfoMailAddressTypeUnits[0].mailAddressTypeNo = "1";
            l_multiMailAddressList.mailTypeInfoList = l_accInfoMailAddressTypeUnits;
            l_request.multiMailAddressList = l_multiMailAddressList;
            l_accInfoMailAddressChangeServiceImpl.submitChange(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_03168);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
        log.info(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 
     */
    public void test_submitChange_C0007()
    {

        final String STR_METHOD_NAME = " test_submitChange_C0007()";
        log.info(TEST_START + STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                    new Class[]
                    {}, new Long(1001L));
            LoginInfoForTest l_loginInfoForTest = new LoginInfoForTest();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[]
                    {}, l_loginInfoForTest);

            OrderValidationResult l_result = new OrderValidationResult(ProcessingResult.SUCCESS_RESULT);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateTradingPassword", new Class[]
                    {Trader.class, SubAccount.class, String.class}, l_result);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_systemPreferencesParams = TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("mobile.mail.");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(1001L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setAccountCode("190600");
            l_mainAccountParams.setBranchCode("368");
            l_mainAccountParams.setBranchId(1001L);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setName(WEB3BranchPreferencesNameDef.ACCOUNTINFO_MULTI_MAILADDRESS_FLAG);
            l_branchPreferencesParams.setBranchId(1001L);
            l_branchPreferencesParams.setNameSerialNo(1);
            l_branchPreferencesParams.setValue("2");
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            l_branchPreferencesParams.setName(WEB3BranchPreferencesNameDef.PRE_NAME_DUPLO);
            l_branchPreferencesParams.setBranchId(1001L);
            l_branchPreferencesParams.setNameSerialNo(1);
            l_branchPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1001L);
            l_branchParams.setBranchCode("368");
            TestDBUtility.insertWithDel(l_branchParams);

            WEB3AccInfoMailAddressChangeServiceImpl l_accInfoMailAddressChangeServiceImpl = new WEB3AccInfoMailAddressChangeServiceImpl();
            WEB3AccInfoMailAddressChangeCompleteRequest l_request = new WEB3AccInfoMailAddressChangeCompleteRequest();
            l_request.mailAddressDelFlag = true;
            WEB3AccInfoMultiMailAddressInfo l_accInfoMultiMailAddressInfo = new WEB3AccInfoMultiMailAddressInfo();
            l_accInfoMultiMailAddressInfo.mailAddress3 = "wu-bo@@sinocom.cn";
            l_accInfoMultiMailAddressInfo.mailAddressDelFlag2 = true;
            l_accInfoMultiMailAddressInfo.execMailFlag = "2";
            l_accInfoMultiMailAddressInfo.unExecMailFlag = "2";
            l_accInfoMultiMailAddressInfo.importantMailFlag = "2";
            l_accInfoMultiMailAddressInfo.guidanceMailFlag2 = "2";
            l_accInfoMultiMailAddressInfo.mailAddressDelFlag3 = false;
            l_request.multiMailAddressInfo = l_accInfoMultiMailAddressInfo;

            WEB3AccInfoMultiMailAddressList l_multiMailAddressList = new WEB3AccInfoMultiMailAddressList();
            WEB3AccInfoMailAddressInfoUnit[] l_mailAddressInfoList = new WEB3AccInfoMailAddressInfoUnit[1];
            l_mailAddressInfoList[0] = new WEB3AccInfoMailAddressInfoUnit();
            l_mailAddressInfoList[0].mailAddress = "test@@sinocom.cn";
            l_mailAddressInfoList[0].mailAddressDiv = "1";
            l_mailAddressInfoList[0].mailAddressNo = "1";
            l_multiMailAddressList.mailAddressInfoList = l_mailAddressInfoList;

            WEB3AccInfoMailAddressTypeUnit[] l_accInfoMailAddressTypeUnits =
                new WEB3AccInfoMailAddressTypeUnit[1];
            l_accInfoMailAddressTypeUnits[0] = new WEB3AccInfoMailAddressTypeUnit();
            l_accInfoMailAddressTypeUnits[0].mailAddressNo = "1";
            l_accInfoMailAddressTypeUnits[0].mailAddressTypeNo = "1";
            l_multiMailAddressList.mailTypeInfoList = l_accInfoMailAddressTypeUnits;
            l_request.multiMailAddressList = l_multiMailAddressList;
            l_accInfoMailAddressChangeServiceImpl.submitChange(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_02443);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
        log.info(TEST_END + STR_METHOD_NAME);
    }

    public void test_updateAccountMailAddress0001()
    {
        final String STR_METHOD_NAME = " test_submitChange_C0007()";
        log.info(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3AccInfoMailAddressInfoUnit[] l_mailAddressInfoList = new WEB3AccInfoMailAddressInfoUnit[1];
            l_mailAddressInfoList[0] = new WEB3AccInfoMailAddressInfoUnit();
            l_mailAddressInfoList[0].mailAddress = "test@@sinocom.cn";
            l_mailAddressInfoList[0].mailAddressDiv = "1";
            l_mailAddressInfoList[0].mailAddressNo = "1";
            WEB3AccInfoMailAddressChangeServiceImpl l_accInfoMailAddressChangeServiceImpl =
                new WEB3AccInfoMailAddressChangeServiceImpl();
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001L);
            l_mainAccountParams.setInstitutionCode("0D");
            l_mainAccountParams.setAccountCode("190600");
            l_mainAccountParams.setBranchCode("368");
            l_mainAccountParams.setBranchId(1001L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            l_accInfoMailAddressChangeServiceImpl.updateAccountMailAddress(
                l_mainAccount,
                l_mailAddressInfoList[0]);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_ex);
            log.info(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    // public void test_getBranchPreferences_C0001()
    // {
    //
    // final String STR_METHOD_NAME = " test_submitChange_C0001()";
    // log.info(TEST_START + STR_METHOD_NAME);
    //
    // try
    // {
    // TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
    // BranchPreferencesParams l_branchPreferencesParams =
    // TestDBUtility.getBranchPreferencesRow();
    // l_branchPreferencesParams.setBranchId(1001L);
    // l_branchPreferencesParams.setName("1");
    // l_branchPreferencesParams.setNameSerialNo(1);
    // l_branchPreferencesParams.setValue("1");
    // TestDBUtility.insertWithDel(l_branchPreferencesParams);
    //
    // WEB3AccInfoMailAddressChangeServiceImpl
    // l_accInfoMailAddressChangeServiceImpl =
    // new WEB3AccInfoMailAddressChangeServiceImpl();
    // String l_strBranchPreferences =
    // l_accInfoMailAddressChangeServiceImpl.getBranchPreferences(1001L, "1",
    // 1);
    // assertEquals("1", l_strBranchPreferences);
    // }
    // catch (Exception l_ex)
    // {
    // log.exiting(STR_METHOD_NAME);
    // log.error(STR_METHOD_NAME, l_ex);
    // log.info(TEST_END + STR_METHOD_NAME);
    // fail();
    // }
    // log.exiting(STR_METHOD_NAME);
    // log.info(TEST_END + STR_METHOD_NAME);
    // }
    // /**
    // *
    // */
    // public void test_getBranchPreferences_C0002()
    // {
    //
    // final String STR_METHOD_NAME = " test_submitChange_C0001()";
    // log.info(TEST_START + STR_METHOD_NAME);
    //
    // try
    // {
    // TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
    //
    // WEB3AccInfoMailAddressChangeServiceImpl
    // l_accInfoMailAddressChangeServiceImpl =
    //             new WEB3AccInfoMailAddressChangeServiceImpl();
    //         String l_strBranchPreferences =
    //             l_accInfoMailAddressChangeServiceImpl.getBranchPreferences(1001L, "1", 1);
    //         assertEquals("0", l_strBranchPreferences);
    //     }
    //     catch (Exception l_ex)
    //     {
    //         log.exiting(STR_METHOD_NAME);
    //         log.error(STR_METHOD_NAME, l_ex);
    //         log.info(TEST_END + STR_METHOD_NAME);
    //         fail();
    //     }
    //     log.exiting(STR_METHOD_NAME);
    //     log.info(TEST_END + STR_METHOD_NAME);
    // }
}
@
