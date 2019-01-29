head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.22.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3GentradeOrderValidatorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 注文チェッククラステスト(WEB3GentradeOrderValidatorTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/28  栄イ (中訊) 新規作成
*/
package webbroker3.gentrade;

/**
 * (注文チェッククラステスト)<BR>
 * 
 * @@author 栄イ (中訊)
 * @@version 1.0
 */
import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.MainAccountImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.SubAccountImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TraderImpl;

import test.util.TestDBUtility;

import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3GentradeOrderValidatorTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3GentradeMainAccountTest.class);

    public WEB3GentradeOrderValidatorTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestDBUtility.deleteAll(MainAccountRow.TYPE);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
        TestDBUtility.deleteAll(MainAccountRow.TYPE);
    }

    /*
     * ログイン情報が取得できない場合
     */
    public void testValidateTradingPasswordTraderSubAccountString_0001()
    {
        final String STR_METHOD_NAME = " testValidateTradingPasswordTraderSubAccountString_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            WEB3GentradeOrderValidator l_orderValidator = new WEB3GentradeOrderValidator();
            OrderValidationResult l_result = l_orderValidator.validateTradingPassword(null, null, null);
            assertEquals(OrderValidationResult.VALIDATION_OK_RESULT, l_result);
        }
        catch (Exception e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            log.error("error:", e);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * セッションより取得した注文経路区分==”IVR”の場合
     */
    public void testValidateTradingPasswordTraderSubAccountString_0002()
    {
        final String STR_METHOD_NAME = " testValidateTradingPasswordTraderSubAccountString_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                new String("F"));
        try
        {
            WEB3GentradeOrderValidator l_orderValidator = new WEB3GentradeOrderValidator();
            OrderValidationResult l_result = l_orderValidator.validateTradingPassword(null, null, null);
            assertEquals(OrderValidationResult.VALIDATION_OK_RESULT, l_result);
        }
        catch (Exception e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * 顧客入力の場合
     */
    public void testValidateTradingPasswordTraderSubAccountString_0003()
    {
        final String STR_METHOD_NAME = " testValidateTradingPasswordTraderSubAccountString_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                new String("D"));
        try
        {
            MainAccountRow l_mainAccountRow = TestDBUtility.getMainAccountRow();
            SubAccountRow l_subAcctRow = TestDBUtility.getSubAccountRow();
            MainAccount l_mainAccount = new MainAccountImpl(l_mainAccountRow);
            SubAccount l_subAccount = new SubAccountImpl(l_mainAccount, l_subAcctRow);
            WEB3GentradeOrderValidatorForTest l_orderValidator = new WEB3GentradeOrderValidatorForTest();
            OrderValidationResult l_result = l_orderValidator.validateTradingPassword(null, l_subAccount, "123456");
            assertEquals(OrderValidationResult.VALIDATION_OK_RESULT, l_result);
        }
        catch (Exception e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * 代理入力の場合
     */
    public void testValidateTradingPasswordTraderSubAccountString_0004()
    {
        final String STR_METHOD_NAME = " testValidateTradingPasswordTraderSubAccountString_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                new String("D"));
        try
        {
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            l_traderParams.setTraderId(1001);
            TestDBUtility.insertWithDel(l_traderParams);
            Trader l_proxyInputPerson = new TraderImpl(1001, false);
            WEB3GentradeOrderValidator l_orderValidator = new WEB3GentradeOrderValidator();
            OrderValidationResult l_result = l_orderValidator.validateTradingPassword(null, null, null);
            assertEquals(OrderValidationResult.VALIDATION_OK_RESULT, l_result);
        }
        catch (Exception e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testvalidateAccountForTradingWEB3GentradeMainAccountTimestampString_0001
     */
    public void testvalidateAccountForTradingWEB3GentradeMainAccountTimestampString_0001()
    {
        final String STR_METHOD_NAME = " testvalidateAccountForTradingWEB3GentradeMainAccountTimestampString_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setBranchLock("1");
            l_mainAccountParams.setOrderPermission("1");
            l_mainAccountParams.setMngLockFlag("1");
            l_mainAccountParams.setMngLockOffStartDate(WEB3DateUtility.getDate("20070829","yyyyMMdd"));
            l_mainAccountParams.setMngLockOffEndDate(WEB3DateUtility.getDate("20070629","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_mainAccountParams);
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            WEB3GentradeOrderValidator l_orderValidator = new WEB3GentradeOrderValidator();
            OrderValidationResult l_result
                = l_orderValidator.validateAccountForTrading(
                    l_mainAccount,
                    new Timestamp(WEB3DateUtility.getDate("20070729000000","yyyyMMddHHmmss").getTime()),
                    "1");
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02957, l_result.getProcessingResult().getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testvalidateAccountForTradingWEB3GentradeMainAccountTimestampString_0002
     */
    public void testvalidateAccountForTradingWEB3GentradeMainAccountTimestampString_0002()
    {
        final String STR_METHOD_NAME = " testvalidateAccountForTradingWEB3GentradeMainAccountTimestampString_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setBranchLock("0");
            l_mainAccountParams.setOrderPermission("1");
            l_mainAccountParams.setMngLockFlag("1");
            l_mainAccountParams.setMngLockOffStartDate(WEB3DateUtility.getDate("20070829","yyyyMMdd"));
            l_mainAccountParams.setMngLockOffEndDate(WEB3DateUtility.getDate("20070629","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_mainAccountParams);
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            WEB3GentradeOrderValidator l_orderValidator = new WEB3GentradeOrderValidator();
            OrderValidationResult l_result
                = l_orderValidator.validateAccountForTrading(
                    l_mainAccount,
                    new Timestamp(WEB3DateUtility.getDate("20070729000000","yyyyMMddHHmmss").getTime()),
                    "1");
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02958, l_result.getProcessingResult().getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testvalidateAccountForTradingWEB3GentradeMainAccountTimestampString_0003
     */
    public void testvalidateAccountForTradingWEB3GentradeMainAccountTimestampString_0003()
    {
        final String STR_METHOD_NAME = " testvalidateAccountForTradingWEB3GentradeMainAccountTimestampString_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setBranchLock("1");
            l_mainAccountParams.setOrderPermission("0");
            l_mainAccountParams.setMngLockFlag("1");
            l_mainAccountParams.setMngLockOffStartDate(WEB3DateUtility.getDate("20070829","yyyyMMdd"));
            l_mainAccountParams.setMngLockOffEndDate(WEB3DateUtility.getDate("20070629","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_mainAccountParams);
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            WEB3GentradeOrderValidator l_orderValidator = new WEB3GentradeOrderValidator();
            OrderValidationResult l_result
                = l_orderValidator.validateAccountForTrading(
                    l_mainAccount,
                    new Timestamp(WEB3DateUtility.getDate("20070729000000","yyyyMMddHHmmss").getTime()),
                    "1");
            assertEquals(OrderValidationResult.VALIDATION_OK_RESULT, l_result);
        }
        catch (Exception e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testvalidateAccountForTradingWEB3GentradeMainAccountTimestampString_0004
     */
    public void testvalidateAccountForTradingWEB3GentradeMainAccountTimestampString_0004()
    {
        final String STR_METHOD_NAME = " testvalidateAccountForTradingWEB3GentradeMainAccountTimestampString_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setBranchLock("1");
            l_mainAccountParams.setOrderPermission("1");
            l_mainAccountParams.setMngLockFlag("0");
            l_mainAccountParams.setMngLockOffStartDate(WEB3DateUtility.getDate("20070829","yyyyMMdd"));
            l_mainAccountParams.setMngLockOffEndDate(WEB3DateUtility.getDate("20070629","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_mainAccountParams);
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            WEB3GentradeOrderValidator l_orderValidator = new WEB3GentradeOrderValidator();
            OrderValidationResult l_result
                = l_orderValidator.validateAccountForTrading(
                    l_mainAccount,
                    new Timestamp(WEB3DateUtility.getDate("20070729000000","yyyyMMddHHmmss").getTime()),
                    "1");
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02957, l_result.getProcessingResult().getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testvalidateAccountForTradingWEB3GentradeMainAccountTimestampString_0005
     */
    public void testvalidateAccountForTradingWEB3GentradeMainAccountTimestampString_0005()
    {
        final String STR_METHOD_NAME = " testvalidateAccountForTradingWEB3GentradeMainAccountTimestampString_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setBranchLock("1");
            l_mainAccountParams.setOrderPermission("1");
            l_mainAccountParams.setMngLockFlag("1");
            l_mainAccountParams.setMngLockOffStartDate(WEB3DateUtility.getDate("20070629","yyyyMMdd"));
            l_mainAccountParams.setMngLockOffEndDate(WEB3DateUtility.getDate("20070629","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_mainAccountParams);
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            WEB3GentradeOrderValidator l_orderValidator = new WEB3GentradeOrderValidator();
            OrderValidationResult l_result
                = l_orderValidator.validateAccountForTrading(
                    l_mainAccount,
                    new Timestamp(WEB3DateUtility.getDate("20070729000000","yyyyMMddHHmmss").getTime()),
                    "1");
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02957, l_result.getProcessingResult().getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testvalidateAccountForTradingWEB3GentradeMainAccountTimestampString_0006
     */
    public void testvalidateAccountForTradingWEB3GentradeMainAccountTimestampString_0006()
    {
        final String STR_METHOD_NAME = " testvalidateAccountForTradingWEB3GentradeMainAccountTimestampString_0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setBranchLock("1");
            l_mainAccountParams.setOrderPermission("1");
            l_mainAccountParams.setMngLockFlag("1");
            l_mainAccountParams.setMngLockOffStartDate(WEB3DateUtility.getDate("20070829","yyyyMMdd"));
            l_mainAccountParams.setMngLockOffEndDate(WEB3DateUtility.getDate("20070829","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_mainAccountParams);
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            WEB3GentradeOrderValidator l_orderValidator = new WEB3GentradeOrderValidator();
            OrderValidationResult l_result
                = l_orderValidator.validateAccountForTrading(
                    l_mainAccount,
                    new Timestamp(WEB3DateUtility.getDate("20070729000000","yyyyMMddHHmmss").getTime()),
                    "1");
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02957, l_result.getProcessingResult().getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testvalidateAccountForTradingWEB3GentradeMainAccountTimestampString_0007
     */
    public void testvalidateAccountForTradingWEB3GentradeMainAccountTimestampString_0007()
    {
        final String STR_METHOD_NAME = " testvalidateAccountForTradingWEB3GentradeMainAccountTimestampString_0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setBranchLock("1");
            l_mainAccountParams.setOrderPermission("1");
            l_mainAccountParams.setMngLockFlag("1");
            l_mainAccountParams.setMngLockOffStartDate(WEB3DateUtility.getDate("20070629","yyyyMMdd"));
            l_mainAccountParams.setMngLockOffEndDate(WEB3DateUtility.getDate("20070829","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_mainAccountParams);
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            WEB3GentradeOrderValidator l_orderValidator = new WEB3GentradeOrderValidator();
            OrderValidationResult l_result
                = l_orderValidator.validateAccountForTrading(
                    l_mainAccount,
                    new Timestamp(WEB3DateUtility.getDate("20070729000000","yyyyMMddHHmmss").getTime()),
                    "1");
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02957, l_result.getProcessingResult().getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testvalidateAccountForTradingWEB3GentradeMainAccountTimestampString_0008
     */
    public void testvalidateAccountForTradingWEB3GentradeMainAccountTimestampString_0008()
    {
        final String STR_METHOD_NAME = " testvalidateAccountForTradingWEB3GentradeMainAccountTimestampString_0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setBranchLock("1");
            l_mainAccountParams.setOrderPermission("1");
            l_mainAccountParams.setMngLockFlag("1");
            l_mainAccountParams.setMngLockOffStartDate(WEB3DateUtility.getDate("20070729","yyyyMMdd"));
            l_mainAccountParams.setMngLockOffEndDate(WEB3DateUtility.getDate("20070729","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_mainAccountParams);
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            WEB3GentradeOrderValidator l_orderValidator = new WEB3GentradeOrderValidator();
            OrderValidationResult l_result
                = l_orderValidator.validateAccountForTrading(
                    l_mainAccount,
                    new Timestamp(WEB3DateUtility.getDate("20070729000000","yyyyMMddHHmmss").getTime()),
                    "1");
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02957, l_result.getProcessingResult().getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testvalidateAccountForTradingWEB3GentradeMainAccountTimestampString_0009
     */
    public void testvalidateAccountForTradingWEB3GentradeMainAccountTimestampString_0009()
    {
        final String STR_METHOD_NAME = " testvalidateAccountForTradingWEB3GentradeMainAccountTimestampString_0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setBranchLock("1");
            l_mainAccountParams.setOrderPermission("1");
            l_mainAccountParams.setMngLockFlag("1");
            l_mainAccountParams.setMngLockOffStartDate(WEB3DateUtility.getDate("20070829","yyyyMMdd"));
            l_mainAccountParams.setMngLockOffEndDate(WEB3DateUtility.getDate("20070629","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_mainAccountParams);
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            WEB3GentradeOrderValidator l_orderValidator = new WEB3GentradeOrderValidator();
            OrderValidationResult l_result
                = l_orderValidator.validateAccountForTrading(
                    l_mainAccount,
                    new Timestamp(WEB3DateUtility.getDate("20070729000000","yyyyMMddHHmmss").getTime()),
                    "2");
            assertEquals(OrderValidationResult.VALIDATION_OK_RESULT, l_result);
        }
        catch (Exception e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testvalidateAccountForTradingWEB3GentradeMainAccountTimestampString_0010
     */
    public void testvalidateAccountForTradingWEB3GentradeMainAccountTimestampString_0010()
    {
        final String STR_METHOD_NAME = " testvalidateAccountForTradingWEB3GentradeMainAccountTimestampString_0010()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setBranchLock("1");
            l_mainAccountParams.setOrderPermission("1");
            l_mainAccountParams.setMngLockFlag("1");
            l_mainAccountParams.setMngLockOffStartDate(null);
            l_mainAccountParams.setMngLockOffEndDate(null);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            WEB3GentradeOrderValidator l_orderValidator = new WEB3GentradeOrderValidator();
            OrderValidationResult l_result
                = l_orderValidator.validateAccountForTrading(
                    l_mainAccount,
                    new Timestamp(WEB3DateUtility.getDate("20070729000000","yyyyMMddHHmmss").getTime()),
                    "1");
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02957, l_result.getProcessingResult().getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testvalidateAccountForTradingWEB3GentradeMainAccountTimestampString_0011
     */
    public void testvalidateAccountForTradingWEB3GentradeMainAccountTimestampString_0011()
    {
        final String STR_METHOD_NAME = " testvalidateAccountForTradingWEB3GentradeMainAccountTimestampString_0011()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setBranchLock("1");
            l_mainAccountParams.setOrderPermission("1");
            l_mainAccountParams.setMngLockFlag("1");
            l_mainAccountParams.setMngLockOffStartDate(null);
            l_mainAccountParams.setMngLockOffEndDate(WEB3DateUtility.getDate("20070829","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_mainAccountParams);
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            WEB3GentradeOrderValidator l_orderValidator = new WEB3GentradeOrderValidator();
            OrderValidationResult l_result
                = l_orderValidator.validateAccountForTrading(
                    l_mainAccount,
                    new Timestamp(WEB3DateUtility.getDate("20070729000000","yyyyMMddHHmmss").getTime()),
                    "1");
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02957, l_result.getProcessingResult().getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testvalidateAccountForTradingWEB3GentradeMainAccount_0001
     */
    public void testvalidateAccountForTradingWEB3GentradeMainAccount_0001()
    {
        final String STR_METHOD_NAME = " testvalidateAccountForTradingWEB3GentradeMainAccount_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setYellowCustomer("0");
            l_mainAccountParams.setBranchLock("0");
            l_mainAccountParams.setExaminLockFlag("0");
            l_mainAccountParams.setOrderPermission("1");
            l_mainAccountParams.setMngLockFlag("1");
            l_mainAccountParams.setMngLockOffStartDate(null);
            l_mainAccountParams.setMngLockOffEndDate(WEB3DateUtility.getDate("20070829","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_mainAccountParams);
            Date l_datDate =
                WEB3DateUtility.getDate("20040716110000", "yyyyMMddHHmmss");
            Timestamp l_tsDate = new Timestamp(l_datDate.getTime());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsDate);
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setTradingTimeType("26");
            l_context.setMarketCode("SP");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext", l_context);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.forcedsettleordervalidationskip", BooleanEnum.TRUE);
            TradingTimeParams l_tradingTimeParams =
                TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode("SP");
            l_tradingTimeParams.setTradingTimeType("26");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setSessionType("1");
            l_tradingTimeParams.setStartTime("110000");
            l_tradingTimeParams.setEndTime("160000");
            l_tradingTimeParams.setBizdateCalcParameter("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            WEB3GentradeOrderValidator l_orderValidator = new WEB3GentradeOrderValidator();
            OrderValidationResult l_result
                = l_orderValidator.validateAccountForTrading(l_mainAccount);
            assertEquals(OrderValidationResult.VALIDATION_OK_RESULT, l_result);
        }
        catch (Exception e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testvalidateAccountForTradingWEB3GentradeMainAccount_0002
     */
    public void testvalidateAccountForTradingWEB3GentradeMainAccount_0002()
    {
        final String STR_METHOD_NAME = " testvalidateAccountForTradingWEB3GentradeMainAccount_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setYellowCustomer("0");
            l_mainAccountParams.setBranchLock("0");
            l_mainAccountParams.setExaminLockFlag("0");
            l_mainAccountParams.setOrderPermission("1");
            l_mainAccountParams.setMngLockFlag("1");
            l_mainAccountParams.setMngLockOffStartDate(null);
            l_mainAccountParams.setMngLockOffEndDate(WEB3DateUtility.getDate("20070829","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_mainAccountParams);
            Date l_datDate =
                WEB3DateUtility.getDate("20040716110000", "yyyyMMddHHmmss");
            Timestamp l_tsDate = new Timestamp(l_datDate.getTime());
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsDate);
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setTradingTimeType("26");
            l_context.setMarketCode("SP");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext", l_context);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.forcedsettleordervalidationskip", BooleanEnum.FALSE);
            TradingTimeParams l_tradingTimeParams =
                TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode("SP");
            l_tradingTimeParams.setTradingTimeType("26");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setSessionType("1");
            l_tradingTimeParams.setStartTime("110000");
            l_tradingTimeParams.setEndTime("160000");
            l_tradingTimeParams.setBizdateCalcParameter("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            WEB3GentradeOrderValidator l_orderValidator = new WEB3GentradeOrderValidator();
            OrderValidationResult l_result
                = l_orderValidator.validateAccountForTrading(l_mainAccount);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00276, l_result.getProcessingResult().getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    private class WEB3GentradeOrderValidatorForTest extends WEB3GentradeOrderValidator
    {
        public OrderValidationResult validateTradingPassword(
            WEB3GentradeMainAccount l_genMainAccount,
            String l_strTradingPassword)
        {
            System.out.println("setSessionAttribute");
            return OrderValidationResult.VALIDATION_OK_RESULT;
        }
    }
}
@
