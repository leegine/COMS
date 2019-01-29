head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.26.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AcceptLoginServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 顧客ログインサービステスト(WEB3AcceptLoginServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/14  栄イ (中訊) 新規作成
*/
package webbroker3.login.service.delegate.stdimpls;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3LoginAttributeKeyDef;
import webbroker3.common.define.WEB3LoginTypeAttributeKeyDef;
import webbroker3.gentrade.data.BranchPreferencesParams;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.gentrade.data.LoginHistoryRow;
import webbroker3.login.message.WEB3AcceptLoginRequest;
import webbroker3.login.message.WEB3SetSessionRequest;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * (顧客ログインサービステスト)<BR>
 * 
 * @@author 栄イ (中訊)
 * @@version 1.0
 */
public class WEB3AcceptLoginServiceImplTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3AcceptLoginServiceImplTest.class);

    WEB3AcceptLoginServiceImpl l_imple = null;

    public WEB3AcceptLoginServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        l_imple = new WEB3AcceptLoginServiceImplForTest();
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

//    /*
//     * testExecute_Case0001
//     * ログイン停止中の場合、ログイン停止中エラーをthrowする。
//     */
//    public void testExecute_Case0001()
//    {
//        final String STR_METHOD_NAME = " testExecute_Case0001()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
//        try
//        {
//            WEB3AcceptLoginRequest l_request = new WEB3AcceptLoginRequest();
//            l_request.institutionCode = "0D";
//            l_request.branchCode = "381";
//
//            TestDBUtility.deleteAll(InstitutionRow.TYPE);
//            TestDBUtility.deleteAll(BranchRow.TYPE);
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            TestDBUtility.insertWithDel(l_institutionParams);
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            l_branchParams.setLoginStopDiv("0");
//            TestDBUtility.insertWithDel(l_branchParams);
//            l_imple.execute(l_request);
//            assertEquals(false, true);
//        }
//        catch(WEB3BusinessLayerException l_ble)
//        {
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_90201, l_ble.getErrorInfo());
//            log.exiting(TEST_END + STR_METHOD_NAME);
//        }
//        catch (Exception e)
//        {
//            log.error("ERROR:", e);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//
//    /*
//     * testExecute_Case0002
//     * 注文チャネル: 不正な値の場合、その他認証エラーをthrowする。
//     */
//    public void testExecute_Case0002()
//    {
//        final String STR_METHOD_NAME = " testExecute_Case0002()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
//        try
//        {
//            WEB3AcceptLoginRequest l_request = new WEB3AcceptLoginRequest();
//            l_request.institutionCode = "0D";
//            l_request.branchCode = "381";
//            l_request.orderChannel = null;
//
//            TestDBUtility.deleteAll(InstitutionRow.TYPE);
//            TestDBUtility.deleteAll(BranchRow.TYPE);
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            TestDBUtility.insertWithDel(l_institutionParams);
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            l_branchParams.setLoginStopDiv("1");
//            TestDBUtility.insertWithDel(l_branchParams);
//            l_imple.execute(l_request);
//            assertEquals(false, true);
//        }
//        catch(WEB3BusinessLayerException l_ble)
//        {
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_90221, l_ble.getErrorInfo());
//            log.exiting(TEST_END + STR_METHOD_NAME);
//        }
//        catch (Exception e)
//        {
//            log.error("ERROR:", e);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//
//    /*
//     * testExecute_Case0003
//     * 顧客コード: コード値エラーとしてその他認証エラーをthrowする。
//     */
//    public void testExecute_Case0003()
//    {
//        final String STR_METHOD_NAME = " testExecute_Case0003()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
//        try
//        {
//            WEB3AcceptLoginRequest l_request = new WEB3AcceptLoginRequest();
//            l_request.institutionCode = "0D";
//            l_request.branchCode = "381";
//            l_request.orderChannel = "0";
//            l_request.acceptCode = "123";
//
//            TestDBUtility.deleteAll(InstitutionRow.TYPE);
//            TestDBUtility.deleteAll(BranchRow.TYPE);
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            TestDBUtility.insertWithDel(l_institutionParams);
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            l_branchParams.setLoginStopDiv("1");
//            TestDBUtility.insertWithDel(l_branchParams);
//            l_imple.execute(l_request);
//            assertEquals(false, true);
//        }
//        catch(WEB3BusinessLayerException l_ble)
//        {
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_90221, l_ble.getErrorInfo());
//            log.exiting(TEST_END + STR_METHOD_NAME);
//        }
//        catch (Exception e)
//        {
//            log.error("ERROR:", e);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//
//    /*
//     * testExecute_Case0004
//     * LoginInfo: 取得できなかった場合、NotFoundExceptionをthrowする。
//     */
//    public void testExecute_Case0004()
//    {
//        final String STR_METHOD_NAME = " testExecute_Case0004()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
//        try
//        {
//            WEB3AcceptLoginRequest l_request = new WEB3AcceptLoginRequest();
//            l_request.institutionCode = "0D";
//            l_request.branchCode = "381";
//            l_request.orderChannel = "0";
//            l_request.acceptCode = "12345678";
//            l_request.xTradeUsername = "username";
//
//            TestDBUtility.deleteAll(InstitutionRow.TYPE);
//            TestDBUtility.deleteAll(BranchRow.TYPE);
//            TestDBUtility.deleteAll(LoginRow.TYPE);
//            TestDBUtility.deleteAll(LoginUsernameRow.TYPE);
//
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            TestDBUtility.insertWithDel(l_institutionParams);
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            l_branchParams.setLoginStopDiv("1");
//            TestDBUtility.insertWithDel(l_branchParams);
//            LoginUsernameParams l_loginUsernameParams = new LoginUsernameParams();
//            l_loginUsernameParams.setUsername("username");
//            l_loginUsernameParams.setLoginId(100001);
//            TestDBUtility.insertWithDel(l_loginUsernameParams);
//            LoginParams l_loginParams = new LoginParams();
//            l_loginParams.setLoginId(100000);
//            l_loginParams.setTypeId(100);
//            l_loginParams.setInitialPassword("123456");
//            l_loginParams.setDisabled(0);
//            TestDBUtility.insertWithDel(l_loginParams);
//
//            l_imple.execute(l_request);
//            assertEquals(false, true);
//        }
//        catch(WEB3BusinessLayerException l_ble)
//        {
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_90221, l_ble.getErrorInfo());
//            log.exiting(TEST_END + STR_METHOD_NAME);
//        }
//        catch (Exception e)
//        {
//            log.error("ERROR:", e);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//
//    /*
//     * testExecute_Case0005
//     * ログインユーザ有効: 無効の場合、顧客ロック中エラーをthrowする。
//     */
//    public void testExecute_Case0005()
//    {
//        final String STR_METHOD_NAME = " testExecute_Case0005()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
//        try
//        {
//            WEB3AcceptLoginRequest l_request = new WEB3AcceptLoginRequest();
//            l_request.institutionCode = "0D";
//            l_request.branchCode = "381";
//            l_request.orderChannel = "0";
//            l_request.acceptCode = "12345678";
//            l_request.xTradeUsername = "username";
//
//            TestDBUtility.deleteAll(InstitutionRow.TYPE);
//            TestDBUtility.deleteAll(BranchRow.TYPE);
//            TestDBUtility.deleteAll(LoginRow.TYPE);
//            TestDBUtility.deleteAll(LoginUsernameRow.TYPE);
//
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            TestDBUtility.insertWithDel(l_institutionParams);
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            l_branchParams.setLoginStopDiv("1");
//            TestDBUtility.insertWithDel(l_branchParams);
//            LoginUsernameParams l_loginUsernameParams = new LoginUsernameParams();
//            l_loginUsernameParams.setUsername("username");
//            l_loginUsernameParams.setLoginId(100001);
//            TestDBUtility.insertWithDel(l_loginUsernameParams);
//            LoginParams l_loginParams = new LoginParams();
//            l_loginParams.setLoginId(100001);
//            l_loginParams.setTypeId(101);
//            l_loginParams.setInitialPassword("123456");
//            l_loginParams.setDisabled(1);
//            TestDBUtility.insertWithDel(l_loginParams);
//            LoginTypeAttributeParams l_loginTypeAttributeParams = new LoginTypeAttributeParams();
//            l_loginTypeAttributeParams.setTypeId(101);
//            l_loginTypeAttributeParams.setAttributeName("SECURITY_LEVEL");
//            l_loginTypeAttributeParams.setAttributeValue("10");
//            TestDBUtility.insertWithDel(l_loginTypeAttributeParams);
//
//            l_imple.execute(l_request);
//            assertEquals(false, true);
//        }
//        catch(WEB3BusinessLayerException l_ble)
//        {
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_90202, l_ble.getErrorInfo());
//            log.exiting(TEST_END + STR_METHOD_NAME);
//        }
//        catch (Exception e)
//        {
//            log.error("ERROR:", e);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//
//    /*
//     * testExecute_Case0006
//     * リクエスト.注文経路区分がIVRのとき パスワード変更が不要な場合
//     */
//    public void testExecute_Case0006()
//    {
//        final String STR_METHOD_NAME = " testExecute_Case0006()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//        try
//        {
//            WEB3AcceptLoginRequest l_request = new WEB3AcceptLoginRequest();
//            l_request.institutionCode = "0D";
//            l_request.branchCode = "381";
//            l_request.orderChannel = "0";
//            l_request.acceptCode = "12345678";
//            l_request.xTradeUsername = "username";
//            l_request.orderRootDiv = "F";
//            l_request.acceptPassword = "1234567";
//
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//                    "logIn",
//                    new Class[] {String.class, String.class},
//                    new String("100001:100001:0:uubvn7bl1o85ot3g6c2anaid1fnk"));
//
//            TestDBUtility.deleteAll(InstitutionRow.TYPE);
//            TestDBUtility.deleteAll(BranchRow.TYPE);
//            TestDBUtility.deleteAll(LoginRow.TYPE);
//            TestDBUtility.deleteAll(LoginUsernameRow.TYPE);
//            TestDBUtility.deleteAll(LoginTypeAttributeRow.TYPE);
//            TestDBUtility.deleteAll(LoginAttributeRow.TYPE);
//
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            TestDBUtility.insertWithDel(l_institutionParams);
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            l_branchParams.setLoginStopDiv("1");
//            TestDBUtility.insertWithDel(l_branchParams);
//            LoginUsernameParams l_loginUsernameParams = new LoginUsernameParams();
//            l_loginUsernameParams.setUsername("username");
//            l_loginUsernameParams.setLoginId(100001);
//            TestDBUtility.insertWithDel(l_loginUsernameParams);
//            LoginParams l_loginParams = new LoginParams();
//            l_loginParams.setLoginId(100001);
//            l_loginParams.setTypeId(101);
//            l_loginParams.setInitialPassword("123456");
//            l_loginParams.setDisabled(0);
//            TestDBUtility.insertWithDel(l_loginParams);
//            LoginTypeAttributeParams l_loginTypeAttributeParams = new LoginTypeAttributeParams();
//            l_loginTypeAttributeParams.setTypeId(101);
//            l_loginTypeAttributeParams.setAttributeName("SECURITY_LEVEL");
//            l_loginTypeAttributeParams.setAttributeValue("10");
//            TestDBUtility.insertWithDel(l_loginTypeAttributeParams);
//            l_loginTypeAttributeParams.setAttributeName("LOGIN_ERROR_MAX");
//            l_loginTypeAttributeParams.setAttributeValue("10");
//            TestDBUtility.insertWithDel(l_loginTypeAttributeParams);
//            l_loginTypeAttributeParams.setAttributeName("PASSWORD_MAX_LENGTH");
//            l_loginTypeAttributeParams.setAttributeValue("10");
//            TestDBUtility.insertWithDel(l_loginTypeAttributeParams);
//            l_loginTypeAttributeParams.setAttributeName("PASSWORD_MIN_LENGTH");
//            l_loginTypeAttributeParams.setAttributeValue("4");
//            TestDBUtility.insertWithDel(l_loginTypeAttributeParams);
//            l_loginTypeAttributeParams.setAttributeName("PASSWORD_CHECK_MODE");
//            l_loginTypeAttributeParams.setAttributeValue("2");
//            TestDBUtility.insertWithDel(l_loginTypeAttributeParams);
//            LoginAttributeParams l_loginAttributeParams = new LoginAttributeParams();
//            l_loginAttributeParams.setLoginId(100001);
//            l_loginAttributeParams.setAttributeName("WEB3_ENCRYPTED_INITIAL_PASSWORD");
//            l_loginAttributeParams.setAttributeValue("1234567");
//            TestDBUtility.insertWithDel(l_loginAttributeParams);
//            l_loginAttributeParams.setAttributeName("WEB3_ENCRYPTED_PASSWORD");
//            l_loginAttributeParams.setAttributeValue("3456789");
//            TestDBUtility.insertWithDel(l_loginAttributeParams);
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            l_mainAccountParams.setAccountId(0);
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = new TradingpowerCalcConditionParams();
//            l_tradingpowerCalcConditionParams.setCalcConditionId(1);
//            l_tradingpowerCalcConditionParams.setAccountId(0);
//            l_tradingpowerCalcConditionParams.setBranchId(33381);
//            l_tradingpowerCalcConditionParams.setAssetEvaluationDiv("0");
//            l_tradingpowerCalcConditionParams.setSpecialDebitAmount(0);
//            l_tradingpowerCalcConditionParams.setTradingStop("0");
//            l_tradingpowerCalcConditionParams.setMarginOpenPositionStop("0");
//            l_tradingpowerCalcConditionParams.setIfoOpenPositionStop("0");
//            l_tradingpowerCalcConditionParams.setPaymentStop("0");
//            l_tradingpowerCalcConditionParams.setOtherTradingStop("0");
//            l_tradingpowerCalcConditionParams.setCreatedTimestamp(Calendar.getInstance().getTime());
//            l_tradingpowerCalcConditionParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
//            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);
//
//            WEB3AcceptLoginResponse l_loginRes = (WEB3AcceptLoginResponse)l_imple.execute(l_request);
//            assertEquals("0", l_loginRes.passwordChangeFlag);
//            assertEquals(null, l_loginRes.passwordMaxLength);
//            assertEquals(null, l_loginRes.passwordMinLength);
//            assertEquals(null, l_loginRes.passwordCheckMethod);
//        }
//        catch (Exception e)
//        {
//            log.error("ERROR:", e);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//
//    /*
//     * testExecute_Case0007
//     * リクエスト.注文経路区分がIVRない パスワード変更が不要な場合
//     */
//    public void testExecute_Case0007()
//    {
//        final String STR_METHOD_NAME = " testExecute_Case0007()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//        try
//        {
//            WEB3AcceptLoginRequest l_request = new WEB3AcceptLoginRequest();
//            l_request.institutionCode = "0D";
//            l_request.branchCode = "381";
//            l_request.orderChannel = "0";
//            l_request.acceptCode = "12345678";
//            l_request.xTradeUsername = "username";
//            l_request.orderRootDiv = "P";
//            l_request.acceptPassword = "1234567";
//
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//                    "logIn",
//                    new Class[] {String.class, String.class},
//                    new String("100001:100001:0:uubvn7bl1o85ot3g6c2anaid1fnk"));
//
//            TestDBUtility.deleteAll(InstitutionRow.TYPE);
//            TestDBUtility.deleteAll(BranchRow.TYPE);
//            TestDBUtility.deleteAll(LoginRow.TYPE);
//            TestDBUtility.deleteAll(LoginUsernameRow.TYPE);
//            TestDBUtility.deleteAll(LoginTypeAttributeRow.TYPE);
//            TestDBUtility.deleteAll(LoginAttributeRow.TYPE);
//
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            TestDBUtility.insertWithDel(l_institutionParams);
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            l_branchParams.setLoginStopDiv("1");
//            TestDBUtility.insertWithDel(l_branchParams);
//            LoginUsernameParams l_loginUsernameParams = new LoginUsernameParams();
//            l_loginUsernameParams.setUsername("username");
//            l_loginUsernameParams.setLoginId(100001);
//            TestDBUtility.insertWithDel(l_loginUsernameParams);
//            LoginParams l_loginParams = new LoginParams();
//            l_loginParams.setLoginId(100001);
//            l_loginParams.setTypeId(101);
//            l_loginParams.setInitialPassword("123456");
//            l_loginParams.setDisabled(0);
//            TestDBUtility.insertWithDel(l_loginParams);
//            LoginTypeAttributeParams l_loginTypeAttributeParams = new LoginTypeAttributeParams();
//            l_loginTypeAttributeParams.setTypeId(101);
//            l_loginTypeAttributeParams.setAttributeName("SECURITY_LEVEL");
//            l_loginTypeAttributeParams.setAttributeValue("10");
//            TestDBUtility.insertWithDel(l_loginTypeAttributeParams);
//            l_loginTypeAttributeParams.setAttributeName("LOGIN_ERROR_MAX");
//            l_loginTypeAttributeParams.setAttributeValue("10");
//            TestDBUtility.insertWithDel(l_loginTypeAttributeParams);
//            l_loginTypeAttributeParams.setAttributeName("PASSWORD_MAX_LENGTH");
//            l_loginTypeAttributeParams.setAttributeValue("10");
//            TestDBUtility.insertWithDel(l_loginTypeAttributeParams);
//            l_loginTypeAttributeParams.setAttributeName("PASSWORD_MIN_LENGTH");
//            l_loginTypeAttributeParams.setAttributeValue("4");
//            TestDBUtility.insertWithDel(l_loginTypeAttributeParams);
//            l_loginTypeAttributeParams.setAttributeName("PASSWORD_CHECK_MODE");
//            l_loginTypeAttributeParams.setAttributeValue("2");
//            TestDBUtility.insertWithDel(l_loginTypeAttributeParams);
//            LoginAttributeParams l_loginAttributeParams = new LoginAttributeParams();
//            l_loginAttributeParams.setLoginId(100001);
//            l_loginAttributeParams.setAttributeName("WEB3_ENCRYPTED_INITIAL_PASSWORD");
//            l_loginAttributeParams.setAttributeValue("1234567");
//            TestDBUtility.insertWithDel(l_loginAttributeParams);
//            l_loginAttributeParams.setAttributeName("WEB3_ENCRYPTED_PASSWORD");
//            l_loginAttributeParams.setAttributeValue("3456789");
//            TestDBUtility.insertWithDel(l_loginAttributeParams);
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            l_mainAccountParams.setAccountId(0);
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = new TradingpowerCalcConditionParams();
//            l_tradingpowerCalcConditionParams.setCalcConditionId(1);
//            l_tradingpowerCalcConditionParams.setAccountId(0);
//            l_tradingpowerCalcConditionParams.setBranchId(33381);
//            l_tradingpowerCalcConditionParams.setAssetEvaluationDiv("0");
//            l_tradingpowerCalcConditionParams.setSpecialDebitAmount(0);
//            l_tradingpowerCalcConditionParams.setTradingStop("0");
//            l_tradingpowerCalcConditionParams.setMarginOpenPositionStop("0");
//            l_tradingpowerCalcConditionParams.setIfoOpenPositionStop("0");
//            l_tradingpowerCalcConditionParams.setPaymentStop("0");
//            l_tradingpowerCalcConditionParams.setOtherTradingStop("0");
//            l_tradingpowerCalcConditionParams.setCreatedTimestamp(Calendar.getInstance().getTime());
//            l_tradingpowerCalcConditionParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
//            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);
//
//            WEB3AcceptLoginResponse l_loginRes = (WEB3AcceptLoginResponse)l_imple.execute(l_request);
//            assertEquals("0", l_loginRes.passwordChangeFlag);
//            assertEquals(null, l_loginRes.passwordMaxLength);
//            assertEquals(null, l_loginRes.passwordMinLength);
//            assertEquals(null, l_loginRes.passwordCheckMethod);
//        }
//        catch (Exception e)
//        {
//            log.error("ERROR:", e);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//
//    /*
//     * testExecute_Case0008
//     * ログインエラーの場合、認証エラーとしてパスワードエラーをthrowする。
//     */
//    public void testExecute_Case0008()
//    {
//        final String STR_METHOD_NAME = " testExecute_Case0008()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//        try
//        {
//            WEB3AcceptLoginRequest l_request = new WEB3AcceptLoginRequest();
//            l_request.institutionCode = "0D";
//            l_request.branchCode = "381";
//            l_request.orderChannel = "0";
//            l_request.acceptCode = "12345678";
//            l_request.xTradeUsername = "username";
//            l_request.orderRootDiv = "P";
//            l_request.acceptPassword = "1234567";
//
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//                    "logIn",
//                    new Class[] {String.class, String.class},
//                    null);
//
//            TestDBUtility.deleteAll(InstitutionRow.TYPE);
//            TestDBUtility.deleteAll(BranchRow.TYPE);
//            TestDBUtility.deleteAll(LoginRow.TYPE);
//            TestDBUtility.deleteAll(LoginUsernameRow.TYPE);
//            TestDBUtility.deleteAll(LoginTypeAttributeRow.TYPE);
//            TestDBUtility.deleteAll(LoginAttributeRow.TYPE);
//
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            TestDBUtility.insertWithDel(l_institutionParams);
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            l_branchParams.setLoginStopDiv("1");
//            TestDBUtility.insertWithDel(l_branchParams);
//            LoginUsernameParams l_loginUsernameParams = new LoginUsernameParams();
//            l_loginUsernameParams.setUsername("username");
//            l_loginUsernameParams.setLoginId(100001);
//            TestDBUtility.insertWithDel(l_loginUsernameParams);
//            LoginParams l_loginParams = new LoginParams();
//            l_loginParams.setLoginId(100001);
//            l_loginParams.setTypeId(101);
//            l_loginParams.setInitialPassword("123456");
//            l_loginParams.setDisabled(0);
//            TestDBUtility.insertWithDel(l_loginParams);
//            LoginTypeAttributeParams l_loginTypeAttributeParams = new LoginTypeAttributeParams();
//            l_loginTypeAttributeParams.setTypeId(101);
//            l_loginTypeAttributeParams.setAttributeName("SECURITY_LEVEL");
//            l_loginTypeAttributeParams.setAttributeValue("10");
//            TestDBUtility.insertWithDel(l_loginTypeAttributeParams);
//            l_loginTypeAttributeParams.setAttributeName("LOGIN_ERROR_MAX");
//            l_loginTypeAttributeParams.setAttributeValue("10");
//            TestDBUtility.insertWithDel(l_loginTypeAttributeParams);
//            LoginAttributeParams l_loginAttributeParams = new LoginAttributeParams();
//            l_loginAttributeParams.setLoginId(100001);
//            l_loginAttributeParams.setAttributeName("WEB3_ENCRYPTED_INITIAL_PASSWORD");
//            l_loginAttributeParams.setAttributeValue("1234567");
//            TestDBUtility.insertWithDel(l_loginAttributeParams);
//            l_loginAttributeParams.setAttributeName("WEB3_ENCRYPTED_PASSWORD");
//            l_loginAttributeParams.setAttributeValue("3456789");
//            TestDBUtility.insertWithDel(l_loginAttributeParams);
//
//            WEB3AcceptLoginResponse l_loginRes = (WEB3AcceptLoginResponse)l_imple.execute(l_request);
//            assertEquals(true, false);
//        }
//        catch(WEB3BusinessLayerException l_ble)
//        {
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_90203, l_ble.getErrorInfo());
//            log.exiting(TEST_END + STR_METHOD_NAME);
//        }
//        catch (Exception e)
//        {
//            log.error("ERROR:", e);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//
//    /*
//     * testExecute_Case0009
//     * リクエスト.注文経路区分がIVRのとき パスワード変更が必要な場合
//     */
//    public void testExecute_Case0009()
//    {
//        final String STR_METHOD_NAME = " testExecute_Case0007()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//        try
//        {
//            WEB3AcceptLoginRequest l_request = new WEB3AcceptLoginRequest();
//            l_request.institutionCode = "0D";
//            l_request.branchCode = "381";
//            l_request.orderChannel = "0";
//            l_request.acceptCode = "12345678";
//            l_request.xTradeUsername = "username";
//            l_request.orderRootDiv = "F";
//            l_request.acceptPassword = "1234567";
//
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//                    "logIn",
//                    new Class[] {String.class, String.class},
//                    new String("100001:100001:0:uubvn7bl1o85ot3g6c2anaid1fnk"));
//
//            TestDBUtility.deleteAll(InstitutionRow.TYPE);
//            TestDBUtility.deleteAll(BranchRow.TYPE);
//            TestDBUtility.deleteAll(LoginRow.TYPE);
//            TestDBUtility.deleteAll(LoginUsernameRow.TYPE);
//            TestDBUtility.deleteAll(LoginTypeAttributeRow.TYPE);
//            TestDBUtility.deleteAll(LoginAttributeRow.TYPE);
//
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            TestDBUtility.insertWithDel(l_institutionParams);
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            l_branchParams.setLoginStopDiv("1");
//            TestDBUtility.insertWithDel(l_branchParams);
//            LoginUsernameParams l_loginUsernameParams = new LoginUsernameParams();
//            l_loginUsernameParams.setUsername("username");
//            l_loginUsernameParams.setLoginId(100001);
//            TestDBUtility.insertWithDel(l_loginUsernameParams);
//            LoginParams l_loginParams = new LoginParams();
//            l_loginParams.setLoginId(100001);
//            l_loginParams.setTypeId(101);
//            l_loginParams.setInitialPassword("123456");
//            l_loginParams.setDisabled(0);
//            TestDBUtility.insertWithDel(l_loginParams);
//            LoginTypeAttributeParams l_loginTypeAttributeParams = new LoginTypeAttributeParams();
//            l_loginTypeAttributeParams.setTypeId(101);
//            l_loginTypeAttributeParams.setAttributeName("SECURITY_LEVEL");
//            l_loginTypeAttributeParams.setAttributeValue("10");
//            TestDBUtility.insertWithDel(l_loginTypeAttributeParams);
//            l_loginTypeAttributeParams.setAttributeName("LOGIN_ERROR_MAX");
//            l_loginTypeAttributeParams.setAttributeValue("10");
//            TestDBUtility.insertWithDel(l_loginTypeAttributeParams);
//            l_loginTypeAttributeParams.setAttributeName("PWDCHANGE_FIRST_FLAG");
//            l_loginTypeAttributeParams.setAttributeValue("1");
//            TestDBUtility.insertWithDel(l_loginTypeAttributeParams);
//            l_loginTypeAttributeParams.setAttributeName("PWDCHANGE_INTERVAL_FLAG");
//            l_loginTypeAttributeParams.setAttributeValue("1");
//            TestDBUtility.insertWithDel(l_loginTypeAttributeParams);
//            l_loginTypeAttributeParams.setAttributeName("PASSWORD_MAX_LENGTH");
//            l_loginTypeAttributeParams.setAttributeValue("10");
//            TestDBUtility.insertWithDel(l_loginTypeAttributeParams);
//            l_loginTypeAttributeParams.setAttributeName("PASSWORD_MIN_LENGTH");
//            l_loginTypeAttributeParams.setAttributeValue("4");
//            TestDBUtility.insertWithDel(l_loginTypeAttributeParams);
//            l_loginTypeAttributeParams.setAttributeName("PASSWORD_CHECK_MODE");
//            l_loginTypeAttributeParams.setAttributeValue("2");
//            TestDBUtility.insertWithDel(l_loginTypeAttributeParams);
//            LoginAttributeParams l_loginAttributeParams = new LoginAttributeParams();
//            l_loginAttributeParams.setLoginId(100001);
//            l_loginAttributeParams.setAttributeName("WEB3_ENCRYPTED_INITIAL_PASSWORD");
//            l_loginAttributeParams.setAttributeValue("1234567");
//            TestDBUtility.insertWithDel(l_loginAttributeParams);
//            l_loginAttributeParams.setAttributeName("WEB3_ENCRYPTED_PASSWORD");
//            l_loginAttributeParams.setAttributeValue("3456789");
//            TestDBUtility.insertWithDel(l_loginAttributeParams);
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            l_mainAccountParams.setAccountId(0);
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = new TradingpowerCalcConditionParams();
//            l_tradingpowerCalcConditionParams.setCalcConditionId(1);
//            l_tradingpowerCalcConditionParams.setAccountId(0);
//            l_tradingpowerCalcConditionParams.setBranchId(33381);
//            l_tradingpowerCalcConditionParams.setAssetEvaluationDiv("0");
//            l_tradingpowerCalcConditionParams.setSpecialDebitAmount(0);
//            l_tradingpowerCalcConditionParams.setTradingStop("0");
//            l_tradingpowerCalcConditionParams.setMarginOpenPositionStop("0");
//            l_tradingpowerCalcConditionParams.setIfoOpenPositionStop("0");
//            l_tradingpowerCalcConditionParams.setPaymentStop("0");
//            l_tradingpowerCalcConditionParams.setOtherTradingStop("0");
//            l_tradingpowerCalcConditionParams.setCreatedTimestamp(Calendar.getInstance().getTime());
//            l_tradingpowerCalcConditionParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
//            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);
//
//            WEB3AcceptLoginResponse l_loginRes = (WEB3AcceptLoginResponse)l_imple.execute(l_request);
//            assertEquals("0", l_loginRes.passwordChangeFlag);
//            assertEquals(null, l_loginRes.passwordMaxLength);
//            assertEquals(null, l_loginRes.passwordMinLength);
//            assertEquals(null, l_loginRes.passwordCheckMethod);
//        }
//        catch (Exception e)
//        {
//            log.error("ERROR:", e);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//
//    /*
//     * testExecute_Case0010
//     * リクエスト.注文経路区分がIVRない パスワード変更が必要な場合
//     */
//    public void testExecute_Case0010()
//    {
//        final String STR_METHOD_NAME = " testExecute_Case0010()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//        try
//        {
//            WEB3AcceptLoginRequest l_request = new WEB3AcceptLoginRequest();
//            l_request.institutionCode = "0D";
//            l_request.branchCode = "381";
//            l_request.orderChannel = "0";
//            l_request.acceptCode = "12345678";
//            l_request.xTradeUsername = "username";
//            l_request.orderRootDiv = "P";
//            l_request.acceptPassword = "1234567";
//
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//                    "logIn",
//                    new Class[] {String.class, String.class},
//                    new String("100001:100001:0:uubvn7bl1o85ot3g6c2anaid1fnk"));
//
//            TestDBUtility.deleteAll(InstitutionRow.TYPE);
//            TestDBUtility.deleteAll(BranchRow.TYPE);
//            TestDBUtility.deleteAll(LoginRow.TYPE);
//            TestDBUtility.deleteAll(LoginUsernameRow.TYPE);
//            TestDBUtility.deleteAll(LoginTypeAttributeRow.TYPE);
//            TestDBUtility.deleteAll(LoginAttributeRow.TYPE);
//
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            TestDBUtility.insertWithDel(l_institutionParams);
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            l_branchParams.setLoginStopDiv("1");
//            TestDBUtility.insertWithDel(l_branchParams);
//            LoginUsernameParams l_loginUsernameParams = new LoginUsernameParams();
//            l_loginUsernameParams.setUsername("username");
//            l_loginUsernameParams.setLoginId(100001);
//            TestDBUtility.insertWithDel(l_loginUsernameParams);
//            LoginParams l_loginParams = new LoginParams();
//            l_loginParams.setLoginId(100001);
//            l_loginParams.setTypeId(101);
//            l_loginParams.setInitialPassword("123456");
//            l_loginParams.setDisabled(0);
//            TestDBUtility.insertWithDel(l_loginParams);
//            LoginTypeAttributeParams l_loginTypeAttributeParams = new LoginTypeAttributeParams();
//            l_loginTypeAttributeParams.setTypeId(101);
//            l_loginTypeAttributeParams.setAttributeName("SECURITY_LEVEL");
//            l_loginTypeAttributeParams.setAttributeValue("10");
//            TestDBUtility.insertWithDel(l_loginTypeAttributeParams);
//            l_loginTypeAttributeParams.setAttributeName("LOGIN_ERROR_MAX");
//            l_loginTypeAttributeParams.setAttributeValue("10");
//            TestDBUtility.insertWithDel(l_loginTypeAttributeParams);
//            l_loginTypeAttributeParams.setAttributeName("PWDCHANGE_FIRST_FLAG");
//            l_loginTypeAttributeParams.setAttributeValue("1");
//            TestDBUtility.insertWithDel(l_loginTypeAttributeParams);
//            l_loginTypeAttributeParams.setAttributeName("PWDCHANGE_INTERVAL_FLAG");
//            l_loginTypeAttributeParams.setAttributeValue("1");
//            TestDBUtility.insertWithDel(l_loginTypeAttributeParams);
//            l_loginTypeAttributeParams.setAttributeName("PASSWORD_INTERVAL");
//            l_loginTypeAttributeParams.setAttributeValue("-1");
//            TestDBUtility.insertWithDel(l_loginTypeAttributeParams);
//            l_loginTypeAttributeParams.setAttributeName("PASSWORD_MAX_LENGTH");
//            l_loginTypeAttributeParams.setAttributeValue("10");
//            TestDBUtility.insertWithDel(l_loginTypeAttributeParams);
//            l_loginTypeAttributeParams.setAttributeName("PASSWORD_MIN_LENGTH");
//            l_loginTypeAttributeParams.setAttributeValue("4");
//            TestDBUtility.insertWithDel(l_loginTypeAttributeParams);
//            l_loginTypeAttributeParams.setAttributeName("PASSWORD_CHECK_MODE");
//            l_loginTypeAttributeParams.setAttributeValue("2");
//            TestDBUtility.insertWithDel(l_loginTypeAttributeParams);
//            LoginAttributeParams l_loginAttributeParams = new LoginAttributeParams();
//            l_loginAttributeParams.setLoginId(100001);
//            l_loginAttributeParams.setAttributeName("WEB3_ENCRYPTED_INITIAL_PASSWORD");
//            l_loginAttributeParams.setAttributeValue("1234567");
//            TestDBUtility.insertWithDel(l_loginAttributeParams);
//            l_loginAttributeParams.setAttributeName("WEB3_ENCRYPTED_PASSWORD");
//            l_loginAttributeParams.setAttributeValue("3456789");
//            TestDBUtility.insertWithDel(l_loginAttributeParams);
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            l_mainAccountParams.setAccountId(0);
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = new TradingpowerCalcConditionParams();
//            l_tradingpowerCalcConditionParams.setCalcConditionId(1);
//            l_tradingpowerCalcConditionParams.setAccountId(0);
//            l_tradingpowerCalcConditionParams.setBranchId(33381);
//            l_tradingpowerCalcConditionParams.setAssetEvaluationDiv("0");
//            l_tradingpowerCalcConditionParams.setSpecialDebitAmount(0);
//            l_tradingpowerCalcConditionParams.setTradingStop("0");
//            l_tradingpowerCalcConditionParams.setMarginOpenPositionStop("0");
//            l_tradingpowerCalcConditionParams.setIfoOpenPositionStop("0");
//            l_tradingpowerCalcConditionParams.setPaymentStop("0");
//            l_tradingpowerCalcConditionParams.setOtherTradingStop("0");
//            l_tradingpowerCalcConditionParams.setCreatedTimestamp(Calendar.getInstance().getTime());
//            l_tradingpowerCalcConditionParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
//            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);
//
//            WEB3AcceptLoginResponse l_loginRes = (WEB3AcceptLoginResponse)l_imple.execute(l_request);
//            assertEquals("1", l_loginRes.passwordChangeFlag);
//            assertEquals("10", l_loginRes.passwordMaxLength);
//            assertEquals("4", l_loginRes.passwordMinLength);
//            assertEquals("2", l_loginRes.passwordCheckMethod);
//        }
//        catch (Exception e)
//        {
//            log.error("ERROR:", e);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }

    //引数.パスワードと復号化したパスワードが同じとき
    //復号化可能初期パスワードを取得
    //復号化可能パスワードを取得
    public void testGetPasswordForIVR0001() 
    {
        final String STR_METHOD_NAME = "testGetPasswordForIVR0001";
        log.entering(STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            String l_strPassword = "1234567";
            HashMap l_loginAttributes = new HashMap();
            l_loginAttributes.put("WEB3_ENCRYPTED_INITIAL_PASSWORD", "1234567");
            l_loginAttributes.put("WEB3_ENCRYPTED_PASSWORD","12345");
            String l_result = l_imple.getPasswordForIVR(l_strPassword, l_loginAttributes);
            assertEquals(l_result, "12345");
        }
        catch (Exception e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //引数.パスワードと復号化したパスワードが同じ以外のとき
    public void testGetPasswordForIVR0002() 
    {
        final String STR_METHOD_NAME = "testGetPasswordForIVR0002";
        log.entering(STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            String l_strPassword = "12345";
            HashMap l_loginAttributes = new HashMap();
            l_loginAttributes.put("WEB3_ENCRYPTED_INITIAL_PASSWORD", "1234567");
            l_loginAttributes.put("WEB3_ENCRYPTED_PASSWORD","12345");
            String l_result = l_imple.getPasswordForIVR(l_strPassword, l_loginAttributes);
            assertEquals(l_result, "    ");
        }
        catch (Exception e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //復号化可能初期パスワードを取得できないとき
    public void testGetPasswordForIVR0003() 
    {
        final String STR_METHOD_NAME = "testGetPasswordForIVR0003";
        log.entering(STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            String l_strPassword = "12345";
            HashMap l_loginAttributes = new HashMap();
//            l_loginAttributes.put("WEB3_ENCRYPTED_INITIAL_PASSWORD", "");
            l_loginAttributes.put("WEB3_ENCRYPTED_PASSWORD","12345");
            String l_result = l_imple.getPasswordForIVR(l_strPassword, l_loginAttributes);
            fail();
        }
        catch(NotFoundException l_exc)
        {
            assertEquals("復号化可能初期パスワードを取得できない。", l_exc.getMessage());
        }
        catch (Exception e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //復号化可能パスワードを取得できない
    public void testGetPasswordForIVR0004() 
    {
        final String STR_METHOD_NAME = "testGetPasswordForIVR0004";
        log.entering(STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            String l_strPassword = "1234567";
            HashMap l_loginAttributes = new HashMap();
            l_loginAttributes.put("WEB3_ENCRYPTED_INITIAL_PASSWORD", "1234567");
//            l_loginAttributes.put("WEB3_ENCRYPTED_PASSWORD","12345");
            String l_result = l_imple.getPasswordForIVR(l_strPassword, l_loginAttributes);
            fail();
        }
        catch(NotFoundException l_exc)
        {
            assertEquals("復号化可能パスワードを取得できない。", l_exc.getMessage());
        }
        catch (Exception e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    private class WEB3AcceptLoginServiceImplForTest extends WEB3AcceptLoginServiceImpl
    {
        protected void setSessionAttribute(
            String l_sessionID,
            WEB3SetSessionRequest l_request)
            throws WEB3BaseException
        {
            System.out.println("setSessionAttribute");
        }

        protected void updateLastLoginTime(final long l_loginID) throws WEB3BaseException
        {
            System.out.println("updateLastLoginTime");
        }
    }
    
    public void testExecute_Case00001() throws WEB3BaseException, DataNetworkException, DataQueryException
    {
        final String STR_METHOD_NAME = "testExecute_Case00007()";
        WEB3AcceptLoginRequest l_request = new WEB3AcceptLoginRequest();
        l_request.institutionCode = "0D";
        l_request.branchCode = "381";
        l_request.orderChannel = "0";
        l_request.acceptCode = "1234";
        l_request.orderRootDiv = "1";
        
        WEB3AcceptLoginServiceImplForTest2 l_implTest2 = new WEB3AcceptLoginServiceImplForTest2();
        try
        {
            //InstitutionRow
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            //BranchRow
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setLoginStopDiv("1");
            l_branchParams.setAccountCodeMin(2);
            l_branchParams.setAccountCodeMax(4);
            l_branchParams.setAccountCodeCheckMode("1");
            TestDBUtility.insertWithDel(l_branchParams);
            
            //BranchPreferencesDao
            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(l_branchParams.getBranchId());
            l_branchPreferencesParams.setName(
                    WEB3BranchPreferencesNameDef.LOGIN_LOG_RECORD + l_request.orderRootDiv);
            l_branchPreferencesParams.setNameSerialNo(1);
            l_branchPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
                "getLoginInfo",
                new Class[] {String.class},
                null);           
            
            TestDBUtility.deleteAll(LoginHistoryRow.TYPE);
            
            l_implTest2.execute(l_request);
            fail();
            
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_90221,l_ex.getErrorInfo());
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_result = l_queryProcessor.doFindAllQuery(LoginHistoryRow.TYPE);
            assertEquals(1, l_result.size());
            LoginHistoryRow l_loginHistoryRow = (LoginHistoryRow)l_result.get(0);
            
            assertEquals("0D",l_loginHistoryRow.getInstitutionCode());
            assertEquals("381",l_loginHistoryRow.getBranchCode());
            assertEquals("1234",l_loginHistoryRow.getAccountCode());
            assertEquals(0,l_loginHistoryRow.getAccountId());
            assertEquals("1",l_loginHistoryRow.getOrderRootDiv());
            assertEquals("0",l_loginHistoryRow.getOrderChannel());
            assertEquals("1",l_loginHistoryRow.getLoginFailure());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
    }
    
    public void testExecute_Case00002() throws WEB3BaseException, DataNetworkException, DataQueryException
    {
        final String STR_METHOD_NAME = "testExecute_Case00007()";
        WEB3AcceptLoginRequest l_request = new WEB3AcceptLoginRequest();
        l_request.institutionCode = "0D";
        l_request.branchCode = "381";
        l_request.orderChannel = "0";
        l_request.acceptCode = "1234";
        l_request.orderRootDiv = "1";
        l_request.xTradeUsername = "tingting";
        
        WEB3AcceptLoginServiceImplForTest2 l_implTest2 = new WEB3AcceptLoginServiceImplForTest2();
        try
        {
            //InstitutionRow
            TestDBUtility.deleteAllAndCommit(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDelAndCommit(l_institutionParams);
            
            //BranchRow
            TestDBUtility.deleteAllAndCommit(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setLoginStopDiv("1");
            l_branchParams.setAccountCodeMin(2);
            l_branchParams.setAccountCodeMax(4);
            l_branchParams.setAccountCodeCheckMode("1");
            TestDBUtility.insertWithDelAndCommit(l_branchParams);
            
            //BranchPreferencesDao
            TestDBUtility.deleteAllAndCommit(BranchPreferencesRow.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(l_branchParams.getBranchId());
            l_branchPreferencesParams.setName(
                    WEB3BranchPreferencesNameDef.LOGIN_LOG_RECORD + l_request.orderRootDiv);
            l_branchPreferencesParams.setNameSerialNo(1);
            l_branchPreferencesParams.setValue("1");
            TestDBUtility.insertWithDelAndCommit(l_branchPreferencesParams);
            
            LoginInfo l_loginInfo = new LoginInfoImpl2();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
                "getLoginInfo",
                new Class[] {String.class},
                l_loginInfo);           
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
                "getLoginInfo",
                new Class[] {long.class},
                l_loginInfo);
            TestDBUtility.deleteAllAndCommit(LoginHistoryRow.TYPE);
            
            Map l_map = new HashMap();
            l_map.put(WEB3LoginTypeAttributeKeyDef.SECURITY_LEVEL, "123");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
                "getLoginTypeAttributes",
                new Class[] {long.class},
                l_map);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                    "getPreference",
                    new Class[] {String.class},
                    "true");
            
            l_implTest2.execute(l_request);
            
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_90203,l_ex.getErrorInfo());
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_result = l_queryProcessor.doFindAllQuery(LoginHistoryRow.TYPE);
            assertEquals(1, l_result.size());
            LoginHistoryRow l_loginHistoryRow = (LoginHistoryRow)l_result.get(0);
            
            assertEquals("0D",l_loginHistoryRow.getInstitutionCode());
            assertEquals("381",l_loginHistoryRow.getBranchCode());
            assertEquals("1234",l_loginHistoryRow.getAccountCode());
            assertEquals(0,l_loginHistoryRow.getAccountId());
            assertEquals("1",l_loginHistoryRow.getOrderRootDiv());
            assertEquals("0",l_loginHistoryRow.getOrderChannel());
            assertEquals("1",l_loginHistoryRow.getLoginFailure());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(),l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
    }
    
    private class WEB3AcceptLoginServiceImplForTest2 extends WEB3AcceptLoginServiceImpl
    {
        protected boolean isRejectIp(
                long l_lngBranchId,
                String l_strInstitutionCode,
                String l_strOrderRootDiv,
                String l_strIpAddress) throws WEB3BaseException
            {
                return false;
            
            }
        
        protected ProcessingResult validateDiscriminationCode(
                String l_strDiscriminationCode,
                String l_strOrderRootDiv,
                Map l_mapLoginTypeAttributes,
                Map l_mapLoginAttributes) throws WEB3BaseException
            {
                return ProcessingResult.newSuccessResultInstance();
            }
        
        protected boolean isEnabledUser(LoginInfo l_loginInfo) throws WEB3BaseException
        {
            return true;
        }
    }
    private class  LoginInfoImpl2 extends LoginInfoImpl
    {
        public long getLoginId()
        {
            // TODO Auto-generated method stub
            return 12;
        }
        
        public String getUsername()
        {
            // TODO Auto-generated method stub
            return "tingting";
        }
        public long getLoginTypeId()
        {
            // TODO Auto-generated method stub
            return 1;
        }
        public Map getAttributes()
        {
            // TODO Auto-generated method stub
            Map l_map = new HashMap();
            l_map.put(WEB3LoginAttributeKeyDef.PASSWORD, null);
            return l_map;
        }
        public boolean isDisabled()
        {
            // TODO Auto-generated method stub
            return true;
        }
        
        
    }
}
@
