head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.26.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AcceptLoginServiceImplTest_insertLoginHistory.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 顧客ログインサービス(WEB3AcceptLoginServiceImplTest_insertLoginHistory.java)
Author Name         : Daiwa Institute of Research
Revision History    :
*/

package webbroker3.login.service.delegate.stdimpls;

import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.data.BranchPreferencesParams;
import webbroker3.gentrade.data.LoginHistoryParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 *
 */
public class WEB3AcceptLoginServiceImplTest_insertLoginHistory extends TestBaseForMock
{

    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
                WEB3AcceptLoginServiceImplTest_insertLoginHistory.class);

    /**
     * @@param arg0
     */
    public WEB3AcceptLoginServiceImplTest_insertLoginHistory(String arg0)
    {
        super(arg0);
    }

    /**
     *
     */
    protected void setUp() throws Exception
    {
        super.checkMethodValue();
        super.setUp();
    }

    /**
     *
     */
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * レコードない
     */
    public void testInsertLoginHistory_0001()
    {
        final String STR_METHOD_NAME = " testInsertLoginHistory_0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAllAndCommit(BranchPreferencesParams.TYPE);
            TestDBUtility.deleteAllAndCommit(LoginHistoryParams.TYPE);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        WEB3AcceptLoginServiceImpl l_acceptLoginServiceImpl =
            new WEB3AcceptLoginServiceImpl();

        long l_lngBranchID = 381L;
        String l_strInstitutionCode = "0D";
        String l_strBranchCode = "381";
        String l_strAccountCode = null;
        String l_strOrderActionId = null;
        String l_lngAccountID = "0";
        String l_strIpAddress = null;
        String l_strOrderRootDiv = null;
        String l_strOrderChannel = null;
        Date l_datMachineTime = null;
        ErrorInfo l_errorInfo = null;


        try
        {
            l_acceptLoginServiceImpl.insertLoginHistory(
                l_lngBranchID,
                l_strInstitutionCode,
                l_strBranchCode,
                l_strAccountCode,
                l_strOrderActionId,
                l_lngAccountID,
                l_strIpAddress,
                l_strOrderRootDiv,
                l_strOrderChannel,
                l_datMachineTime,
                l_errorInfo);

            log.error(TEST_END + STR_METHOD_NAME);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertTrue(true);
        }
        catch (WEB3BaseException l_exBE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exBE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAllAndCommit(BranchPreferencesParams.TYPE);
                TestDBUtility.deleteAllAndCommit(LoginHistoryParams.TYPE);
            }
            catch (Exception l_ex)
            {
                log.error(TEST_END + STR_METHOD_NAME);
                log.exiting(TEST_END + STR_METHOD_NAME);

                fail();
            }
        }
    }

    /**
     * レコードの値が「実施しない」の場合は当メソッド終了
     */
    public void testInsertLoginHistory_0002()
    {
        final String STR_METHOD_NAME = " testInsertLoginHistory_0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAllAndCommit(BranchPreferencesParams.TYPE);
            TestDBUtility.deleteAllAndCommit(LoginHistoryParams.TYPE);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        try
        {
            BranchPreferencesParams l_branchPreferencesParams = new BranchPreferencesParams();
            l_branchPreferencesParams.setBranchId(33381L);
            l_branchPreferencesParams.setName("login.log.record");
            l_branchPreferencesParams.setNameSerialNo(1);
            // 実施しない
            l_branchPreferencesParams.setValue("0");

            java.util.Date l_date = new java.util.Date();

            l_date = WEB3DateUtility.getDate("20070615", "yyyyMMdd");
            l_date = WEB3DateUtility.addHour(l_date, 10);
            l_date = WEB3DateUtility.addMinute(l_date, 20);
            l_date = WEB3DateUtility.addSecond(l_date, 30);

            l_branchPreferencesParams.setCreatedTimestamp(l_date);
            l_branchPreferencesParams.setLastUpdatedTimestamp(l_date);

            TestDBUtility.insertWithDelAndCommit(l_branchPreferencesParams);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        WEB3AcceptLoginServiceImpl l_acceptLoginServiceImpl =
            new WEB3AcceptLoginServiceImpl();

        long l_lngBranchID = 33381L;
        String l_strInstitutionCode = "0D";
        String l_strBranchCode = "381";
        String l_strAccountCode = null;
        String l_strOrderActionId = null;
        String l_lngAccountID = "0";
        String l_strIpAddress = null;
        String l_strOrderRootDiv = null;
        String l_strOrderChannel = null;
        Date l_datMachineTime = null;
        ErrorInfo l_errorInfo = null;


        try
        {
            l_acceptLoginServiceImpl.insertLoginHistory(
                l_lngBranchID,
                l_strInstitutionCode,
                l_strBranchCode,
                l_strAccountCode,
                l_strOrderActionId,
                l_lngAccountID,
                l_strIpAddress,
                l_strOrderRootDiv,
                l_strOrderChannel,
                l_datMachineTime,
                l_errorInfo);

            log.error(TEST_END + STR_METHOD_NAME);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertTrue(true);
        }
        catch (WEB3BaseException l_exBE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exBE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAllAndCommit(BranchPreferencesParams.TYPE);
                TestDBUtility.deleteAllAndCommit(LoginHistoryParams.TYPE);
            }
            catch (Exception l_ex)
            {
                log.error(TEST_END + STR_METHOD_NAME);
                log.exiting(TEST_END + STR_METHOD_NAME);

                fail();
            }
        }
    }

    /**
     *
     */
    public void testInsertLoginHistory_0003()
    {
        final String STR_METHOD_NAME = " testInsertLoginHistory_0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAllAndCommit(BranchPreferencesParams.TYPE);
            TestDBUtility.deleteAllAndCommit(LoginHistoryParams.TYPE);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        try
        {
            BranchPreferencesParams l_branchPreferencesParams = new BranchPreferencesParams();
            l_branchPreferencesParams.setBranchId(33381L);
            l_branchPreferencesParams.setName("login.log.record.1");
            l_branchPreferencesParams.setNameSerialNo(1);
            // 実施
            l_branchPreferencesParams.setValue("1");

            java.util.Date l_date = new java.util.Date();

            l_date = WEB3DateUtility.getDate("20070615", "yyyyMMdd");
            l_date = WEB3DateUtility.addHour(l_date, 10);
            l_date = WEB3DateUtility.addMinute(l_date, 20);
            l_date = WEB3DateUtility.addSecond(l_date, 30);

            l_branchPreferencesParams.setCreatedTimestamp(l_date);
            l_branchPreferencesParams.setLastUpdatedTimestamp(l_date);

            TestDBUtility.insertWithDelAndCommit(l_branchPreferencesParams);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        WEB3AcceptLoginServiceImpl l_acceptLoginServiceImpl =
            new WEB3AcceptLoginServiceImpl();

        java.util.Date l_date = new java.util.Date();

        l_date = WEB3DateUtility.getDate("20070618", "yyyyMMdd");
        l_date = WEB3DateUtility.addHour(l_date, 10);
        l_date = WEB3DateUtility.addMinute(l_date, 20);
        l_date = WEB3DateUtility.addSecond(l_date, 30);

        long l_lngBranchID = 33381L;
        String l_strInstitutionCode = "0D";
        String l_strBranchCode = "381";
        String l_strAccountCode = "2512246";
        String l_strOrderActionId = null;
        String l_lngAccountID  = "333812512246";
        String l_strIpAddress = null;
        String l_strOrderRootDiv = "1";
        String l_strOrderChannel = "2";
        Date l_datMachineTime = l_date;
        ErrorInfo l_errorInfo = null;

        try
        {
            l_acceptLoginServiceImpl.insertLoginHistory(
                l_lngBranchID,
                l_strInstitutionCode,
                l_strBranchCode,
                l_strAccountCode,
                l_strOrderActionId,
                l_lngAccountID,
                l_strIpAddress,
                l_strOrderRootDiv,
                l_strOrderChannel,
                l_datMachineTime,
                l_errorInfo);
        }
        catch (WEB3BaseException l_exBE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exBE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            List l_lisHostPostalTransVoucher =
                l_queryProcessor.doFindAllQuery(LoginHistoryParams.TYPE);

            if (!l_lisHostPostalTransVoucher.isEmpty())
            {
                LoginHistoryParams l_loginHistoryParams =
                    (LoginHistoryParams)l_lisHostPostalTransVoucher.get(0);

                assertEquals("0D", l_loginHistoryParams.institution_code);
                assertEquals("381", l_loginHistoryParams.branch_code);
                assertEquals("2512246", l_loginHistoryParams.account_code);
                assertNull(l_loginHistoryParams.discrimination_code);
                assertEquals(333812512246L, l_loginHistoryParams.account_id);
                assertNull(l_loginHistoryParams.ip_address);
                assertEquals("1", l_loginHistoryParams.order_root_div);
                assertEquals("2", l_loginHistoryParams.order_channel);

                java.util.Date l_expectedDate = new java.util.Date();

                l_expectedDate = WEB3DateUtility.getDate("20070618", "yyyyMMdd");
                l_expectedDate = WEB3DateUtility.addHour(l_expectedDate, 10);
                l_expectedDate = WEB3DateUtility.addMinute(l_expectedDate, 20);
                l_expectedDate = WEB3DateUtility.addSecond(l_expectedDate, 30);

                assertEquals(l_expectedDate, l_loginHistoryParams.login_timestamp);
                assertEquals("0", l_loginHistoryParams.login_failure);
                assertNull(l_loginHistoryParams.login_error_info);
                assertNotNull(l_loginHistoryParams.created_timestamp);
                assertNotNull(l_loginHistoryParams.last_updated_timestamp);

                log.exiting(TEST_END + STR_METHOD_NAME);
            }
            else
            {
                fail();
            }

        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAllAndCommit(BranchPreferencesParams.TYPE);
                TestDBUtility.deleteAllAndCommit(LoginHistoryParams.TYPE);
            }
            catch (Exception l_ex)
            {
                log.error(TEST_END + STR_METHOD_NAME);
                log.exiting(TEST_END + STR_METHOD_NAME);

                fail();
            }
        }

    }

    /**
     *
     */
    public void testInsertLoginHistory_0004()
    {
        final String STR_METHOD_NAME = " testInsertLoginHistory_0004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAllAndCommit(BranchPreferencesParams.TYPE);
            TestDBUtility.deleteAllAndCommit(LoginHistoryParams.TYPE);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        try
        {
            BranchPreferencesParams l_branchPreferencesParams = new BranchPreferencesParams();
            l_branchPreferencesParams.setBranchId(33381L);
            l_branchPreferencesParams.setName("login.log.record.1");
            l_branchPreferencesParams.setNameSerialNo(1);
            // 実施
            l_branchPreferencesParams.setValue("1");

            java.util.Date l_date = new java.util.Date();

            l_date = WEB3DateUtility.getDate("20070615", "yyyyMMdd");
            l_date = WEB3DateUtility.addHour(l_date, 10);
            l_date = WEB3DateUtility.addMinute(l_date, 20);
            l_date = WEB3DateUtility.addSecond(l_date, 30);

            l_branchPreferencesParams.setCreatedTimestamp(l_date);
            l_branchPreferencesParams.setLastUpdatedTimestamp(l_date);

            TestDBUtility.insertWithDelAndCommit(l_branchPreferencesParams);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        WEB3AcceptLoginServiceImpl l_acceptLoginServiceImpl =
            new WEB3AcceptLoginServiceImpl();

        java.util.Date l_date = new java.util.Date();

        l_date = WEB3DateUtility.getDate("20070618", "yyyyMMdd");
        l_date = WEB3DateUtility.addHour(l_date, 10);
        l_date = WEB3DateUtility.addMinute(l_date, 20);
        l_date = WEB3DateUtility.addSecond(l_date, 30);

        long l_lngBranchID = 33381L;
        String l_strInstitutionCode = "0D";
        String l_strBranchCode = "381";
        String l_strAccountCode = "2512246";
        String l_strOrderActionId = "999";
        String l_lngAccountID = "333812512246";
        String l_strIpAddress = "255.255.255.255";
        String l_strOrderRootDiv = "1";
        String l_strOrderChannel = "2";
        Date l_datMachineTime = l_date;
        ErrorInfo l_errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80003;

        try
        {
            l_acceptLoginServiceImpl.insertLoginHistory(
                l_lngBranchID,
                l_strInstitutionCode,
                l_strBranchCode,
                l_strAccountCode,
                l_strOrderActionId,
                l_lngAccountID,
                l_strIpAddress,
                l_strOrderRootDiv,
                l_strOrderChannel,
                l_datMachineTime,
                l_errorInfo);
        }
        catch (WEB3BaseException l_exBE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exBE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            List l_lisHostPostalTransVoucher =
                l_queryProcessor.doFindAllQuery(LoginHistoryParams.TYPE);

            if (!l_lisHostPostalTransVoucher.isEmpty())
            {
                LoginHistoryParams l_loginHistoryParams =
                    (LoginHistoryParams)l_lisHostPostalTransVoucher.get(0);

                assertEquals("0D", l_loginHistoryParams.institution_code);
                assertEquals("381", l_loginHistoryParams.branch_code);
                assertEquals("2512246", l_loginHistoryParams.account_code);
                assertEquals("999", l_loginHistoryParams.discrimination_code);
                assertEquals(333812512246L, l_loginHistoryParams.account_id);
                assertEquals("255.255.255.255", l_loginHistoryParams.ip_address);
                assertEquals("1", l_loginHistoryParams.order_root_div);
                assertEquals("2", l_loginHistoryParams.order_channel);

                java.util.Date l_expectedDate = new java.util.Date();

                l_expectedDate = WEB3DateUtility.getDate("20070618", "yyyyMMdd");
                l_expectedDate = WEB3DateUtility.addHour(l_expectedDate, 10);
                l_expectedDate = WEB3DateUtility.addMinute(l_expectedDate, 20);
                l_expectedDate = WEB3DateUtility.addSecond(l_expectedDate, 30);

                assertEquals(l_expectedDate, l_loginHistoryParams.login_timestamp);
                assertEquals("1", l_loginHistoryParams.login_failure);
                String strExpectedLoginErrorInfo =
                    "ErrorInfo(80003,webbroker3.common.WEB3ErrorCatalog.SYSTEM_ERROR_80003," +
                    "DBへのアクセスに失敗しました。,null)";
                assertEquals(strExpectedLoginErrorInfo, l_loginHistoryParams.login_error_info);
                assertNotNull(l_loginHistoryParams.created_timestamp);
                assertNotNull(l_loginHistoryParams.last_updated_timestamp);

                log.exiting(TEST_END + STR_METHOD_NAME);
            }
            else
            {
                fail();
            }

        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAllAndCommit(BranchPreferencesParams.TYPE);
                TestDBUtility.deleteAllAndCommit(LoginHistoryParams.TYPE);
            }
            catch (Exception l_ex)
            {
                log.error(TEST_END + STR_METHOD_NAME);
                log.exiting(TEST_END + STR_METHOD_NAME);

                fail();
            }
        }

    }

}
@
