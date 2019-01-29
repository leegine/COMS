head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.36.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AioSLAccountOpenUnitServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : SL口座開設UnitServiceImplのテストクラス(WEB3AioSLAccountOpenUnitServiceImplTest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/09/23 周墨洋 (中訊) 新規作成 仕様変更・モデルNo.763
Revision History    : 2007/11/13 周墨洋 (中訊) 仕様変更・モデルNo.823
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import test.util.TestDBUtility;
import webbroker3.aio.message.WEB3FXAccOpenAskingRequest;
import webbroker3.aio.message.WEB3SLAccountBaseInfoUnit;
import webbroker3.aio.message.WEB3SLAccountOpenApplyRequest;
import webbroker3.aio.message.WEB3SLAccountOpenApplyResponse;
import webbroker3.aio.message.WEB3SLAccountOpenRequest;
import webbroker3.aio.message.WEB3SLAccountOpenResponse;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AccountOpenStatusDef;
import webbroker3.common.define.WEB3BranchLockDef;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3EnforcementDef;
import webbroker3.common.define.WEB3MarginAccountOpenDivDef;
import webbroker3.common.define.WEB3MngLockDef;
import webbroker3.common.define.WEB3OrderPermissionDef;
import webbroker3.common.define.WEB3SexDef;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.BranchPreferencesParams;
import webbroker3.gentrade.data.CommSerialNumbersParams;
import webbroker3.gentrade.data.StockSecuredLoanParams;
import webbroker3.gentrade.data.TradingpowerCalcConditionParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (SL口座開設UnitServiceImpl)<BR>
 * SL口座開設UnitServiceImplのテストクラス<BR>
 *
 * @@author 周墨洋
 * @@version 1.0
 */
public class WEB3AioSLAccountOpenUnitServiceImplTest extends TestBaseForMock
{

    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AioSLAccountOpenUnitServiceImplTest.class);

    /**
     * SL口座開設UnitServiceImpl
     */
    private WEB3AioSLAccountOpenUnitServiceImpl l_aioSLAccountOpenUnitServiceImpl = null;

    /**
     *
     * @@param arg0
     */
    public WEB3AioSLAccountOpenUnitServiceImplTest(String arg0)
    {
        super(arg0);
    }

    /**
     *
     */
    protected void setUp() throws Exception
    {
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
     *
     */
    public void testValidateAccOpenPossible_case0001()
    {
        String STR_METHOD_NAME = " testValidateAccOpenPossible_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_aioSLAccountOpenUnitServiceImpl =
            new WEB3AioSLAccountOpenUnitServiceImpl();

        try
        {
            boolean l_blnIsMarginAccountOpenDiv = true;
            boolean l_blnIsFuturesAccountOpenDiv = true;
            boolean l_blnIsOptionAccountOpenDiv = true;

            Method l_method =
                WEB3AioSLAccountOpenUnitServiceImpl.class.getDeclaredMethod(
                    "validateAccOpenPossible",
                    new Class[] {boolean.class, boolean.class, boolean.class});
            l_method.setAccessible(true);
            l_method.invoke(
                l_aioSLAccountOpenUnitServiceImpl,
                new Object[] {
                    new Boolean(l_blnIsMarginAccountOpenDiv),
                    new Boolean(l_blnIsFuturesAccountOpenDiv),
                    new Boolean(l_blnIsOptionAccountOpenDiv)});

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (NoSuchMethodException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (InvocationTargetException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3BusinessLayerException.class,
                l_ex.getTargetException().getClass());
            WEB3BusinessLayerException l_actualBusinessLayerException =
                (WEB3BusinessLayerException)l_ex.getTargetException();
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02938,
                l_actualBusinessLayerException.getErrorInfo());
        }
        catch (IllegalAccessException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     *
     */
    public void testValidateAccOpenPossible_case0002()
    {
        String STR_METHOD_NAME = " testValidateAccOpenPossible_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_aioSLAccountOpenUnitServiceImpl =
            new WEB3AioSLAccountOpenUnitServiceImpl();

        try
        {
            boolean l_blnIsMarginAccountOpenDiv = true;
            boolean l_blnIsFuturesAccountOpenDiv = true;
            boolean l_blnIsOptionAccountOpenDiv = false;

            Method l_method =
                WEB3AioSLAccountOpenUnitServiceImpl.class.getDeclaredMethod(
                    "validateAccOpenPossible",
                    new Class[] {boolean.class, boolean.class, boolean.class});
            l_method.setAccessible(true);
            l_method.invoke(
                l_aioSLAccountOpenUnitServiceImpl,
                new Object[] {
                    new Boolean(l_blnIsMarginAccountOpenDiv),
                    new Boolean(l_blnIsFuturesAccountOpenDiv),
                    new Boolean(l_blnIsOptionAccountOpenDiv)});

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (NoSuchMethodException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (InvocationTargetException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3BusinessLayerException.class,
                l_ex.getTargetException().getClass());
            WEB3BusinessLayerException l_actualBusinessLayerException =
                (WEB3BusinessLayerException)l_ex.getTargetException();
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02938,
                l_actualBusinessLayerException.getErrorInfo());
        }
        catch (IllegalAccessException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     *
     */
    public void testValidateAccOpenPossible_case0003()
    {
        String STR_METHOD_NAME = " testValidateAccOpenPossible_case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_aioSLAccountOpenUnitServiceImpl =
            new WEB3AioSLAccountOpenUnitServiceImpl();

        try
        {
            boolean l_blnIsMarginAccountOpenDiv = true;
            boolean l_blnIsFuturesAccountOpenDiv = false;
            boolean l_blnIsOptionAccountOpenDiv = true;

            Method l_method =
                WEB3AioSLAccountOpenUnitServiceImpl.class.getDeclaredMethod(
                    "validateAccOpenPossible",
                    new Class[] {boolean.class, boolean.class, boolean.class});
            l_method.setAccessible(true);
            l_method.invoke(
                l_aioSLAccountOpenUnitServiceImpl,
                new Object[] {
                    new Boolean(l_blnIsMarginAccountOpenDiv),
                    new Boolean(l_blnIsFuturesAccountOpenDiv),
                    new Boolean(l_blnIsOptionAccountOpenDiv)});

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (NoSuchMethodException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (InvocationTargetException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3BusinessLayerException.class,
                l_ex.getTargetException().getClass());
            WEB3BusinessLayerException l_actualBusinessLayerException =
                (WEB3BusinessLayerException)l_ex.getTargetException();
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02938,
                l_actualBusinessLayerException.getErrorInfo());
        }
        catch (IllegalAccessException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     *
     */
    public void testValidateAccOpenPossible_case0004()
    {
        String STR_METHOD_NAME = " testValidateAccOpenPossible_case0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_aioSLAccountOpenUnitServiceImpl =
            new WEB3AioSLAccountOpenUnitServiceImpl();

        try
        {
            boolean l_blnIsMarginAccountOpenDiv = true;
            boolean l_blnIsFuturesAccountOpenDiv = false;
            boolean l_blnIsOptionAccountOpenDiv = false;

            Method l_method =
                WEB3AioSLAccountOpenUnitServiceImpl.class.getDeclaredMethod(
                    "validateAccOpenPossible",
                    new Class[] {boolean.class, boolean.class, boolean.class});
            l_method.setAccessible(true);
            l_method.invoke(
                l_aioSLAccountOpenUnitServiceImpl,
                new Object[] {
                    new Boolean(l_blnIsMarginAccountOpenDiv),
                    new Boolean(l_blnIsFuturesAccountOpenDiv),
                    new Boolean(l_blnIsOptionAccountOpenDiv)});

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (NoSuchMethodException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (InvocationTargetException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3BusinessLayerException.class,
                l_ex.getTargetException().getClass());
            WEB3BusinessLayerException l_actualBusinessLayerException =
                (WEB3BusinessLayerException)l_ex.getTargetException();
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02936,
                l_actualBusinessLayerException.getErrorInfo());
        }
        catch (IllegalAccessException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     *
     */
    public void testValidateAccOpenPossible_case0005()
    {
        String STR_METHOD_NAME = " testValidateAccOpenPossible_case0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_aioSLAccountOpenUnitServiceImpl =
            new WEB3AioSLAccountOpenUnitServiceImpl();

        try
        {
            boolean l_blnIsMarginAccountOpenDiv = false;
            boolean l_blnIsFuturesAccountOpenDiv = true;
            boolean l_blnIsOptionAccountOpenDiv = true;

            Method l_method =
                WEB3AioSLAccountOpenUnitServiceImpl.class.getDeclaredMethod(
                    "validateAccOpenPossible",
                    new Class[] {boolean.class, boolean.class, boolean.class});
            l_method.setAccessible(true);
            l_method.invoke(
                l_aioSLAccountOpenUnitServiceImpl,
                new Object[] {
                    new Boolean(l_blnIsMarginAccountOpenDiv),
                    new Boolean(l_blnIsFuturesAccountOpenDiv),
                    new Boolean(l_blnIsOptionAccountOpenDiv)});

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (NoSuchMethodException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (InvocationTargetException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3BusinessLayerException.class,
                l_ex.getTargetException().getClass());
            WEB3BusinessLayerException l_actualBusinessLayerException =
                (WEB3BusinessLayerException)l_ex.getTargetException();
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02937,
                l_actualBusinessLayerException.getErrorInfo());
        }
        catch (IllegalAccessException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     *
     */
    public void testValidateAccOpenPossible_case0006()
    {
        String STR_METHOD_NAME = " testValidateAccOpenPossible_case0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_aioSLAccountOpenUnitServiceImpl =
            new WEB3AioSLAccountOpenUnitServiceImpl();

        try
        {
            boolean l_blnIsMarginAccountOpenDiv = false;
            boolean l_blnIsFuturesAccountOpenDiv = true;
            boolean l_blnIsOptionAccountOpenDiv = false;

            Method l_method =
                WEB3AioSLAccountOpenUnitServiceImpl.class.getDeclaredMethod(
                    "validateAccOpenPossible",
                    new Class[] {boolean.class, boolean.class, boolean.class});
            l_method.setAccessible(true);
            l_method.invoke(
                l_aioSLAccountOpenUnitServiceImpl,
                new Object[] {
                    new Boolean(l_blnIsMarginAccountOpenDiv),
                    new Boolean(l_blnIsFuturesAccountOpenDiv),
                    new Boolean(l_blnIsOptionAccountOpenDiv)});

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (NoSuchMethodException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (InvocationTargetException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3BusinessLayerException.class,
                l_ex.getTargetException().getClass());
            WEB3BusinessLayerException l_actualBusinessLayerException =
                (WEB3BusinessLayerException)l_ex.getTargetException();
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02937,
                l_actualBusinessLayerException.getErrorInfo());
        }
        catch (IllegalAccessException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     *
     */
    public void testValidateAccOpenPossible_case0007()
    {
        String STR_METHOD_NAME = " testValidateAccOpenPossible_case0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_aioSLAccountOpenUnitServiceImpl =
            new WEB3AioSLAccountOpenUnitServiceImpl();

        try
        {
            boolean l_blnIsMarginAccountOpenDiv = false;
            boolean l_blnIsFuturesAccountOpenDiv = false;
            boolean l_blnIsOptionAccountOpenDiv = true;

            Method l_method =
                WEB3AioSLAccountOpenUnitServiceImpl.class.getDeclaredMethod(
                    "validateAccOpenPossible",
                    new Class[] {boolean.class, boolean.class, boolean.class});
            l_method.setAccessible(true);
            l_method.invoke(
                l_aioSLAccountOpenUnitServiceImpl,
                new Object[] {
                    new Boolean(l_blnIsMarginAccountOpenDiv),
                    new Boolean(l_blnIsFuturesAccountOpenDiv),
                    new Boolean(l_blnIsOptionAccountOpenDiv)});

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (NoSuchMethodException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (InvocationTargetException l_ex)
        {
            assertEquals(WEB3BusinessLayerException.class,
                l_ex.getTargetException().getClass());
            WEB3BusinessLayerException l_actualBusinessLayerException =
                (WEB3BusinessLayerException)l_ex.getTargetException();
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02937,
                l_actualBusinessLayerException.getErrorInfo());
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (IllegalAccessException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     *
     */
    public void testValidateAccOpenPossible_case0008()
    {
        String STR_METHOD_NAME = " testValidateAccOpenPossible_case0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_aioSLAccountOpenUnitServiceImpl =
            new WEB3AioSLAccountOpenUnitServiceImpl();

        try
        {
            boolean l_blnIsMarginAccountOpenDiv = false;
            boolean l_blnIsFuturesAccountOpenDiv = false;
            boolean l_blnIsOptionAccountOpenDiv = false;

            Method l_method =
                WEB3AioSLAccountOpenUnitServiceImpl.class.getDeclaredMethod(
                    "validateAccOpenPossible",
                    new Class[] {boolean.class, boolean.class, boolean.class});
            l_method.setAccessible(true);
            l_method.invoke(
                l_aioSLAccountOpenUnitServiceImpl,
                new Object[] {
                    new Boolean(l_blnIsMarginAccountOpenDiv),
                    new Boolean(l_blnIsFuturesAccountOpenDiv),
                    new Boolean(l_blnIsOptionAccountOpenDiv)});

            assertTrue(true);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (NoSuchMethodException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (InvocationTargetException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (IllegalAccessException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     *
     */
    public void testGetPreference_case0001()
    {
        String STR_METHOD_NAME = " testGetPreference_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_aioSLAccountOpenUnitServiceImpl =
            new WEB3AioSLAccountOpenUnitServiceImpl();

        SystemPreferencesParams l_systemPreferencesParams =
            TestDBUtility.getSystemPreferencesRow();
        l_systemPreferencesParams.setName("46_CREDIT_URL");
        l_systemPreferencesParams.setValue("http://www.daiwa.com");

        try
        {
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);

            TestDBUtility.insertWithDel(l_systemPreferencesParams);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        try
        {
            String l_strName = "46_CREDIT_URL";

            Method l_method =
                WEB3AioSLAccountOpenUnitServiceImpl.class.getDeclaredMethod(
                    "getPreference",
                    new Class[] {String.class});
            l_method.setAccessible(true);
            String l_strPreferencetNo =
                (String)l_method.invoke(
                    l_aioSLAccountOpenUnitServiceImpl,
                    new Object[] {l_strName});

            assertEquals("http://www.daiwa.com", l_strPreferencetNo);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
        }
    }

    /**
     *
     */
    public void testGetPreference_case0002()
    {
        String STR_METHOD_NAME = " testGetPreference_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_aioSLAccountOpenUnitServiceImpl =
            new WEB3AioSLAccountOpenUnitServiceImpl();

        try
        {
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        try
        {
            String l_strName = "46_CREDIT_URL";

            Method l_method =
                WEB3AioSLAccountOpenUnitServiceImpl.class.getDeclaredMethod(
                    "getPreference",
                    new Class[] {String.class});
            l_method.setAccessible(true);
            String l_strPreferencetNo =
                (String)l_method.invoke(
                    l_aioSLAccountOpenUnitServiceImpl,
                    new Object[] {l_strName});

            assertEquals(null, l_strPreferencetNo);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
        }
    }

    /**
     *
     */
    public void testGetSerialNumber_case0001()
    {
        String STR_METHOD_NAME = " testGetSerialNumber_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_aioSLAccountOpenUnitServiceImpl =
            new WEB3AioSLAccountOpenUnitServiceImpl();

        CommSerialNumbersParams l_commSerialNumbersParams =
            new CommSerialNumbersParams();
        l_commSerialNumbersParams.setInstitutionCode("0D");
        l_commSerialNumbersParams.setSerialNumberName("stock_secured_loan");
        l_commSerialNumbersParams.setSerialNumber("070926122");
        l_commSerialNumbersParams.setCreatedTimestamp(
            WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp()));
        l_commSerialNumbersParams.setLastUpdatedTimestamp(
            WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp()));

        try
        {
            TestDBUtility.deleteAll(CommSerialNumbersParams.TYPE);

            TestDBUtility.insertWithDel(l_commSerialNumbersParams);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        try
        {
            String l_strInstitutionCode = "0D";
            String l_strSerialNumberName = "stock_secured_loan";

            Method l_method =
                WEB3AioSLAccountOpenUnitServiceImpl.class.getDeclaredMethod(
                    "getSerialNumber",
                    new Class[] {String.class, String.class});
            l_method.setAccessible(true);
            String l_strSerialNumber =
                (String)l_method.invoke(
                    l_aioSLAccountOpenUnitServiceImpl,
                    new Object[] {l_strInstitutionCode, l_strSerialNumberName});

            assertEquals("070926122", l_strSerialNumber);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(CommSerialNumbersParams.TYPE);
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
        }
    }

    /**
     *
     */
    public void testGetSerialNumber_case0002()
    {
        String STR_METHOD_NAME = " testGetSerialNumber_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_aioSLAccountOpenUnitServiceImpl =
            new WEB3AioSLAccountOpenUnitServiceImpl();

        try
        {
            TestDBUtility.deleteAll(CommSerialNumbersParams.TYPE);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        try
        {
            String l_strInstitutionCode = "0D";
            String l_strSerialNumberName = "stock_secured_loan";

            Method l_method =
                WEB3AioSLAccountOpenUnitServiceImpl.class.getDeclaredMethod(
                    "getSerialNumber",
                    new Class[] {String.class, String.class});
            l_method.setAccessible(true);
            l_method.invoke(
                l_aioSLAccountOpenUnitServiceImpl,
                new Object[] {l_strInstitutionCode, l_strSerialNumberName});

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (NoSuchMethodException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (InvocationTargetException l_ex)
        {
            assertEquals(WEB3BusinessLayerException.class,
                l_ex.getTargetException().getClass());
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                ((WEB3BaseException)l_ex.getTargetException()).getErrorInfo());
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (IllegalAccessException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(CommSerialNumbersParams.TYPE);
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
        }
    }

    /**
     *
     */
    public void testCreateStockLoanAccountNo_case0001()
    {
        String STR_METHOD_NAME = " testCreateStockLoanAccountNo_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_aioSLAccountOpenUnitServiceImpl =
            new WEB3AioSLAccountOpenUnitServiceImpl();

        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            Calendar ca =  Calendar.getInstance();
            ca.set(2007,9-1,24);
            Date date = ca.getTime();
            Timestamp st = new Timestamp(date.getTime());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc."
                + "gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[]{},
                st);

            String l_strSerialNumber = "070925123";

            Method l_method =
                WEB3AioSLAccountOpenUnitServiceImpl.class.getDeclaredMethod(
                    "createStockLoanAccountNo",
                    new Class[] {String.class});
            l_method.setAccessible(true);
            String l_strStockLoanAccount =
                (String)l_method.invoke(
                    l_aioSLAccountOpenUnitServiceImpl,
                    new Object[] {l_strSerialNumber});

            assertEquals("0709251240", l_strStockLoanAccount);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (NoSuchMethodException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (InvocationTargetException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (IllegalAccessException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        }
    }

    /**
     *
     */
    public void testCreateStockLoanAccountNo_case0002()
    {
        String STR_METHOD_NAME = " testCreateStockLoanAccountNo_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_aioSLAccountOpenUnitServiceImpl =
            new WEB3AioSLAccountOpenUnitServiceImpl();

        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            Calendar ca =  Calendar.getInstance();
            ca.set(2007,12-1,25);
            Date date = ca.getTime();
            Timestamp st = new Timestamp(date.getTime());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc."
                + "gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[]{},
                st);

            String l_strSerialNumber = "120925122";

            Method l_method =
                WEB3AioSLAccountOpenUnitServiceImpl.class.getDeclaredMethod(
                    "createStockLoanAccountNo",
                    new Class[] {String.class});
            l_method.setAccessible(true);
            String l_strStockLoanAccount =
                (String)l_method.invoke(
                    l_aioSLAccountOpenUnitServiceImpl,
                    new Object[] {l_strSerialNumber});

            assertEquals("1209251235", l_strStockLoanAccount);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (NoSuchMethodException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (InvocationTargetException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (IllegalAccessException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        }
    }

    /**
     *
     */
    public void testCreateStockLoanAccountNo_case0003()
    {
        String STR_METHOD_NAME = " testCreateStockLoanAccountNo_case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_aioSLAccountOpenUnitServiceImpl =
            new WEB3AioSLAccountOpenUnitServiceImpl();

        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            Calendar ca =  Calendar.getInstance();
            ca.set(2007,9-1,26);
            Date date = ca.getTime();
            Timestamp st = new Timestamp(date.getTime());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc."
                + "gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[]{},
                st);

            String l_strSerialNumber = "070925122";

            Method l_method =
                WEB3AioSLAccountOpenUnitServiceImpl.class.getDeclaredMethod(
                    "createStockLoanAccountNo",
                    new Class[] {String.class});
            l_method.setAccessible(true);
            String l_strStockLoanAccount =
                (String)l_method.invoke(
                    l_aioSLAccountOpenUnitServiceImpl,
                    new Object[] {l_strSerialNumber});

            assertEquals("0709260015", l_strStockLoanAccount);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (NoSuchMethodException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (InvocationTargetException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (IllegalAccessException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        }
    }

    /**
     *
     */
    public void testValidateSLAccountOpen_case0001()
    {
        String STR_METHOD_NAME = " testValidateSLAccountOpen_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_aioSLAccountOpenUnitServiceImpl =
            new WEB3AioSLAccountOpenUnitServiceImpl();

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams =
            TestDBUtility.getTradingpowerCalcConditionRow();
        l_tradingpowerCalcConditionParams.setAccountId(
            l_mainAccountParams.getAccountId());
        l_tradingpowerCalcConditionParams.setAssetEvaluationDiv(
            WEB3EnforcementDef.ENFORCEMENT);

        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            TestDBUtility.deleteAll(TradingpowerCalcConditionParams.TYPE);

            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        try
        {
            WEB3SLAccountOpenApplyRequest l_slAccountOpenApplyRequest =
                new WEB3SLAccountOpenApplyRequest();

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl."
                + "OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            WEB3SLAccountOpenApplyResponse l_slAccountOpenApplyResponse =
                l_aioSLAccountOpenUnitServiceImpl.validateSLAccountOpen(
                    l_slAccountOpenApplyRequest);

            assertNotNull(l_slAccountOpenApplyResponse);

            WEB3SLAccountBaseInfoUnit l_actualSLAccountBaseInfoUnit =
                l_slAccountOpenApplyResponse.accountBaseInfo;

            assertNotNull(l_actualSLAccountBaseInfoUnit);

            assertEquals(l_mainAccountParams.getFamilyNameAlt1(),
                l_actualSLAccountBaseInfoUnit.accountNameKana);
            assertEquals(l_mainAccountParams.getFamilyName(),
                l_actualSLAccountBaseInfoUnit.accountName);
            assertEquals(l_mainAccountParams.getEraBorn(),
                l_actualSLAccountBaseInfoUnit.eraBorn);
            assertEquals(l_mainAccountParams.getBornDate(),
                l_actualSLAccountBaseInfoUnit.bornDate);
            assertEquals(l_mainAccountParams.getSex(),
                l_actualSLAccountBaseInfoUnit.sex);
            assertEquals(l_mainAccountParams.getZipCode(),
                l_actualSLAccountBaseInfoUnit.zipCode);
            assertEquals(l_mainAccountParams.getAddressLine1(),
                l_actualSLAccountBaseInfoUnit.address1);
            assertEquals(l_mainAccountParams.getAddressLine2(),
                l_actualSLAccountBaseInfoUnit.address2);
            assertEquals(l_mainAccountParams.getAddressLine3(),
                l_actualSLAccountBaseInfoUnit.address3);
            assertEquals(l_mainAccountParams.getEmailAddress(),
                l_actualSLAccountBaseInfoUnit.mailAddress);

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(MainAccountParams.TYPE);
                TestDBUtility.deleteAll(SubAccountParams.TYPE);
                TestDBUtility.deleteAll(InstitutionParams.TYPE);
                TestDBUtility.deleteAll(BranchParams.TYPE);
                TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
                TestDBUtility.deleteAll(TradingpowerCalcConditionParams.TYPE);
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
            finally
            {
                TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
            }
        }
    }

    /**
     *
     */
    public void testValidateSLAccountOpen_case0002()
    {
        String STR_METHOD_NAME = " testValidateSLAccountOpen_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_aioSLAccountOpenUnitServiceImpl =
            new WEB3AioSLAccountOpenUnitServiceImpl();

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setSex(WEB3SexDef.CORPORATE);
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        BranchParams l_branchParams = TestDBUtility.getBranchRow();

        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);

            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_branchParams);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        try
        {
            WEB3SLAccountOpenApplyRequest l_slAccountOpenApplyRequest =
                new WEB3SLAccountOpenApplyRequest();

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl."
                + "OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            l_aioSLAccountOpenUnitServiceImpl.validateSLAccountOpen(
                l_slAccountOpenApplyRequest);

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02934, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(MainAccountParams.TYPE);
                TestDBUtility.deleteAll(SubAccountParams.TYPE);
                TestDBUtility.deleteAll(InstitutionParams.TYPE);
                TestDBUtility.deleteAll(BranchParams.TYPE);
                TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
            finally
            {
                TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
            }
        }
    }

    /**
     *
     */
    public void testValidateSLAccountOpen_case0003()
    {
        String STR_METHOD_NAME = " testValidateSLAccountOpen_case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_aioSLAccountOpenUnitServiceImpl =
            new WEB3AioSLAccountOpenUnitServiceImpl();

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        l_institutionParams.setAssetEvaluation(WEB3EnforcementDef.ENFORCEMENT);
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams =
            TestDBUtility.getTradingpowerCalcConditionRow();
        l_tradingpowerCalcConditionParams.setAccountId(
            l_mainAccountParams.getAccountId());
        l_tradingpowerCalcConditionParams.setAssetEvaluationDiv(
            WEB3EnforcementDef.ENFORCEMENT);

        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            TestDBUtility.deleteAll(TradingpowerCalcConditionParams.TYPE);

            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        try
        {
            WEB3SLAccountOpenApplyRequest l_slAccountOpenApplyRequest =
                new WEB3SLAccountOpenApplyRequest();

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl."
                + "OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            l_aioSLAccountOpenUnitServiceImpl.validateSLAccountOpen(
                l_slAccountOpenApplyRequest);

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02935, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(MainAccountParams.TYPE);
                TestDBUtility.deleteAll(SubAccountParams.TYPE);
                TestDBUtility.deleteAll(InstitutionParams.TYPE);
                TestDBUtility.deleteAll(BranchParams.TYPE);
                TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
            finally
            {
                TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
            }
        }
    }

    /**
     *
     */
    public void testValidateSLAccountOpen_case0004()
    {
        String STR_METHOD_NAME = " testValidateSLAccountOpen_case0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_aioSLAccountOpenUnitServiceImpl =
            new WEB3AioSLAccountOpenUnitServiceImpl();

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setMarginGenAccOpenDiv(
            WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN);
        l_mainAccountParams.setMarginSysAccOpenDiv(
            WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN);
        l_mainAccountParams.setIfoAccOpenDivTokyo(null);
        l_mainAccountParams.setIfoAccOpenDivOsaka(null);
        l_mainAccountParams.setIfoAccOpenDivNagoya(null);
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        BranchParams l_branchParams = TestDBUtility.getBranchRow();

        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);

            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_branchParams);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        try
        {
            WEB3SLAccountOpenApplyRequest l_slAccountOpenApplyRequest =
                new WEB3SLAccountOpenApplyRequest();

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl."
                + "OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            l_aioSLAccountOpenUnitServiceImpl.validateSLAccountOpen(
                l_slAccountOpenApplyRequest);

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02936, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(MainAccountParams.TYPE);
                TestDBUtility.deleteAll(SubAccountParams.TYPE);
                TestDBUtility.deleteAll(InstitutionParams.TYPE);
                TestDBUtility.deleteAll(BranchParams.TYPE);
                TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
            finally
            {
                TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
            }
        }
    }

    /**
     *
     */
    public void testValidateSLAccountOpen_case0005()
    {
        String STR_METHOD_NAME = " testValidateSLAccountOpen_case0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_aioSLAccountOpenUnitServiceImpl =
            new WEB3AioSLAccountOpenUnitServiceImpl();

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setMngLockOffStartDate(null);
        l_mainAccountParams.setBranchLock(WEB3BranchLockDef.BRANCH_LOCK);
        l_mainAccountParams.setOrderPermission(WEB3OrderPermissionDef.NO_PERMISSION);
        l_mainAccountParams.setMngLockFlag(WEB3MngLockDef.LOCK);

        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams =
            TestDBUtility.getTradingpowerCalcConditionRow();
        l_tradingpowerCalcConditionParams.setAccountId(
            l_mainAccountParams.getAccountId());
        l_tradingpowerCalcConditionParams.setAssetEvaluationDiv(
            WEB3EnforcementDef.ENFORCEMENT);

        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            TestDBUtility.deleteAll(TradingpowerCalcConditionParams.TYPE);

            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        try
        {
            WEB3SLAccountOpenApplyRequest l_slAccountOpenApplyRequest =
                new WEB3SLAccountOpenApplyRequest();

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl."
                + "OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            ProcessingResult processingResult = ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.BUSINESS_ERROR_00275);
            OrderValidationResult l_result = new OrderValidationResult(processingResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator", "validateAccountForTrading",
                    new Class[] { WEB3GentradeMainAccount.class, Timestamp.class, String.class },
                    l_result);
            
            l_aioSLAccountOpenUnitServiceImpl.validateSLAccountOpen(
                l_slAccountOpenApplyRequest);

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00275, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(MainAccountParams.TYPE);
                TestDBUtility.deleteAll(SubAccountParams.TYPE);
                TestDBUtility.deleteAll(InstitutionParams.TYPE);
                TestDBUtility.deleteAll(BranchParams.TYPE);
                TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
                TestDBUtility.deleteAll(TradingpowerCalcConditionParams.TYPE);
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
            finally
            {
                TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
            }
        }
    }

    /**
     *
     */
    public void testValidateSLAccountOpen_case0006()
    {
        String STR_METHOD_NAME = " testValidateSLAccountOpen_case0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_aioSLAccountOpenUnitServiceImpl =
            new WEB3AioSLAccountOpenUnitServiceImpl();

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setEraBorn("4");
        l_mainAccountParams.setBornDate("101010");

        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();

        BranchParams l_branchParams = TestDBUtility.getBranchRow();

        BranchPreferencesParams l_branchPreferencesParams =
            TestDBUtility.getBranchPreferencesRow();
        l_branchPreferencesParams.setBranchId(l_mainAccountParams.getBranchId());
        l_branchPreferencesParams.setName(
            WEB3BranchPreferencesNameDef.SL_LOWLIMIT_AGE_CHECK);
        l_branchPreferencesParams.setNameSerialNo(1);
        l_branchPreferencesParams.setValue("255");

        TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams =
            TestDBUtility.getTradingpowerCalcConditionRow();
        l_tradingpowerCalcConditionParams.setAccountId(
            l_mainAccountParams.getAccountId());
        l_tradingpowerCalcConditionParams.setAssetEvaluationDiv(
            WEB3EnforcementDef.ENFORCEMENT);

        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            TestDBUtility.deleteAll(TradingpowerCalcConditionParams.TYPE);

            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        try
        {
            WEB3SLAccountOpenApplyRequest l_slAccountOpenApplyRequest =
                new WEB3SLAccountOpenApplyRequest();

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl."
                + "OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            l_aioSLAccountOpenUnitServiceImpl.validateSLAccountOpen(
                l_slAccountOpenApplyRequest);

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02955, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(MainAccountParams.TYPE);
                TestDBUtility.deleteAll(SubAccountParams.TYPE);
                TestDBUtility.deleteAll(InstitutionParams.TYPE);
                TestDBUtility.deleteAll(BranchParams.TYPE);
                TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
                TestDBUtility.deleteAll(TradingpowerCalcConditionParams.TYPE);
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
            finally
            {
                TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
            }
        }
    }

    /**
     *
     */
    public void testValidateSLAccountOpen_case0007()
    {
        String STR_METHOD_NAME = " testValidateSLAccountOpen_case0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_aioSLAccountOpenUnitServiceImpl =
            new WEB3AioSLAccountOpenUnitServiceImpl();

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setEraBorn("4");
        l_mainAccountParams.setBornDate("101010");

        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();

        BranchParams l_branchParams = TestDBUtility.getBranchRow();

        BranchPreferencesParams l_branchPreferencesParams =
            TestDBUtility.getBranchPreferencesRow();

        TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams =
            TestDBUtility.getTradingpowerCalcConditionRow();
        l_tradingpowerCalcConditionParams.setAccountId(
            l_mainAccountParams.getAccountId());
        l_tradingpowerCalcConditionParams.setAssetEvaluationDiv(
            WEB3EnforcementDef.ENFORCEMENT);

        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            TestDBUtility.deleteAll(TradingpowerCalcConditionParams.TYPE);

            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_branchParams);

            l_branchPreferencesParams.setBranchId(l_mainAccountParams.getBranchId());
            l_branchPreferencesParams.setName(
                WEB3BranchPreferencesNameDef.SL_LOWLIMIT_AGE_CHECK);
            l_branchPreferencesParams.setNameSerialNo(1);
            l_branchPreferencesParams.setValue("0");
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            l_branchPreferencesParams.setBranchId(l_mainAccountParams.getBranchId());
            l_branchPreferencesParams.setName(
                WEB3BranchPreferencesNameDef.SL_HIGHLIMIT_AGE_CHECK);
            l_branchPreferencesParams.setNameSerialNo(1);
            l_branchPreferencesParams.setValue("0");
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        try
        {
            WEB3SLAccountOpenApplyRequest l_slAccountOpenApplyRequest =
                new WEB3SLAccountOpenApplyRequest();

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl."
                + "OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            l_aioSLAccountOpenUnitServiceImpl.validateSLAccountOpen(
                l_slAccountOpenApplyRequest);

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02955, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(MainAccountParams.TYPE);
                TestDBUtility.deleteAll(SubAccountParams.TYPE);
                TestDBUtility.deleteAll(InstitutionParams.TYPE);
                TestDBUtility.deleteAll(BranchParams.TYPE);
                TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
                TestDBUtility.deleteAll(TradingpowerCalcConditionParams.TYPE);
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
            finally
            {
                TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
            }
        }
    }

    /**
     *
     */
    public void testSubmitSLAccountOpen_case0001()
    {
        String STR_METHOD_NAME = " testSubmitSLAccountOpen_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_aioSLAccountOpenUnitServiceImpl =
            new WEB3AioSLAccountOpenUnitServiceImpl();

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setSex(WEB3SexDef.CORPORATE);
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        BranchParams l_branchParams = TestDBUtility.getBranchRow();

        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);

            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_branchParams);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        try
        {
            WEB3SLAccountOpenRequest l_slAccountOpenRequest =
                new WEB3SLAccountOpenRequest();

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl."
                + "OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            l_aioSLAccountOpenUnitServiceImpl.submitSLAccountOpen(
                l_slAccountOpenRequest);

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02934, l_ex.getErrorInfo());
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(MainAccountParams.TYPE);
                TestDBUtility.deleteAll(SubAccountParams.TYPE);
                TestDBUtility.deleteAll(InstitutionParams.TYPE);
                TestDBUtility.deleteAll(BranchParams.TYPE);
                TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
            finally
            {
                TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
            }
        }
    }

    /**
     *
     */
    public void testSubmitSLAccountOpen_case0002()
    {
        String STR_METHOD_NAME = " testSubmitSLAccountOpen_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_aioSLAccountOpenUnitServiceImpl =
            new WEB3AioSLAccountOpenUnitServiceImpl();

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        l_institutionParams.setAssetEvaluation(WEB3EnforcementDef.ENFORCEMENT);
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams =
            TestDBUtility.getTradingpowerCalcConditionRow();
        l_tradingpowerCalcConditionParams.setAccountId(
            l_mainAccountParams.getAccountId());
        l_tradingpowerCalcConditionParams.setAssetEvaluationDiv(
            WEB3EnforcementDef.ENFORCEMENT);

        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            TestDBUtility.deleteAll(TradingpowerCalcConditionParams.TYPE);

            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        try
        {
            WEB3SLAccountOpenRequest l_slAccountOpenRequest =
                new WEB3SLAccountOpenRequest();

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl."
                + "OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            l_aioSLAccountOpenUnitServiceImpl.submitSLAccountOpen(
                l_slAccountOpenRequest);

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02935, l_ex.getErrorInfo());
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(MainAccountParams.TYPE);
                TestDBUtility.deleteAll(SubAccountParams.TYPE);
                TestDBUtility.deleteAll(InstitutionParams.TYPE);
                TestDBUtility.deleteAll(BranchParams.TYPE);
                TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
            finally
            {
                TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
            }
        }
    }

    /**
     *
     */
    public void testSubmitSLAccountOpen_case0003()
    {
        String STR_METHOD_NAME = " testSubmitSLAccountOpen_case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_aioSLAccountOpenUnitServiceImpl =
            new WEB3AioSLAccountOpenUnitServiceImpl();

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setMarginGenAccOpenDiv(
            WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN);
        l_mainAccountParams.setMarginSysAccOpenDiv(
            WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN);
        l_mainAccountParams.setIfoAccOpenDivTokyo(null);
        l_mainAccountParams.setIfoAccOpenDivOsaka(null);
        l_mainAccountParams.setIfoAccOpenDivNagoya(null);
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        BranchParams l_branchParams = TestDBUtility.getBranchRow();

        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);

            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_branchParams);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        try
        {
            WEB3SLAccountOpenRequest l_slAccountOpenRequest =
                new WEB3SLAccountOpenRequest();

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl."
                + "OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            l_aioSLAccountOpenUnitServiceImpl.submitSLAccountOpen(
                l_slAccountOpenRequest);

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02936, l_ex.getErrorInfo());
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(MainAccountParams.TYPE);
                TestDBUtility.deleteAll(SubAccountParams.TYPE);
                TestDBUtility.deleteAll(InstitutionParams.TYPE);
                TestDBUtility.deleteAll(BranchParams.TYPE);
                TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
            finally
            {
                TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
            }
        }
    }

    /**
     *
     */
    public void testSubmitSLAccountOpen_case0004()
    {
        String STR_METHOD_NAME = " testSubmitSLAccountOpen_case0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_aioSLAccountOpenUnitServiceImpl =
            new WEB3AioSLAccountOpenUnitServiceImpl();

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

        Date l_datCurrentDay = WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp());
        Timestamp l_tsCurrentDay = new Timestamp(l_datCurrentDay.getTime());

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        BranchParams l_branchParams = TestDBUtility.getBranchRow();

        StockSecuredLoanParams l_stockSecuredLoanParams =
            TestDBUtility.getStockSecuredLoanRow();
        l_stockSecuredLoanParams.setAccountId(l_mainAccountParams.getAccountId());
        l_stockSecuredLoanParams.setAppliDate(l_tsCurrentDay);
        l_stockSecuredLoanParams.setStockLoanAccountCode("123");

        SystemPreferencesParams l_systemPreferencesParams =
            TestDBUtility.getSystemPreferencesRow();
        l_systemPreferencesParams.setName("46_CREDIT_URL");
        l_systemPreferencesParams.setValue("http://www.daiwa.com");

        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            TestDBUtility.deleteAll(StockSecuredLoanParams.TYPE);
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);

            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_stockSecuredLoanParams);
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        try
        {
            WEB3SLAccountOpenRequest l_slAccountOpenRequest =
                new WEB3SLAccountOpenRequest();

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl."
                + "OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            WEB3SLAccountOpenResponse l_slAccountOpenResponse =
                l_aioSLAccountOpenUnitServiceImpl.submitSLAccountOpen(
                    l_slAccountOpenRequest);

            assertNotNull(l_slAccountOpenResponse);

            WEB3SLAccountBaseInfoUnit l_actualSLAccountBaseInfoUnit =
                l_slAccountOpenResponse.accountBaseInfo;

            assertNotNull(l_actualSLAccountBaseInfoUnit);

            assertEquals(l_mainAccountParams.getFamilyNameAlt1(),
                l_actualSLAccountBaseInfoUnit.accountNameKana);
            assertEquals(l_mainAccountParams.getFamilyName(),
                l_actualSLAccountBaseInfoUnit.accountName);
            assertEquals(l_mainAccountParams.getEraBorn(),
                l_actualSLAccountBaseInfoUnit.eraBorn);
            assertEquals(l_mainAccountParams.getBornDate(),
                l_actualSLAccountBaseInfoUnit.bornDate);
            assertEquals(l_mainAccountParams.getSex(),
                l_actualSLAccountBaseInfoUnit.sex);
            assertEquals(l_mainAccountParams.getZipCode(),
                l_actualSLAccountBaseInfoUnit.zipCode);
            assertEquals(l_mainAccountParams.getAddressLine1(),
                l_actualSLAccountBaseInfoUnit.address1);
            assertEquals(l_mainAccountParams.getAddressLine2(),
                l_actualSLAccountBaseInfoUnit.address2);
            assertEquals(l_mainAccountParams.getAddressLine3(),
                l_actualSLAccountBaseInfoUnit.address3);
            assertEquals(l_mainAccountParams.getEmailAddress(),
                l_actualSLAccountBaseInfoUnit.mailAddress);

            assertEquals("123", l_slAccountOpenResponse.stockLoanAccount);
            assertEquals("http://www.daiwa.com", l_slAccountOpenResponse.url);

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(MainAccountParams.TYPE);
                TestDBUtility.deleteAll(SubAccountParams.TYPE);
                TestDBUtility.deleteAll(InstitutionParams.TYPE);
                TestDBUtility.deleteAll(BranchParams.TYPE);
                TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
                TestDBUtility.deleteAll(StockSecuredLoanParams.TYPE);
                TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
            finally
            {
                TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
            }
        }
    }

    /**
     *
     */
    public void testSubmitSLAccountOpen_case0005()
    {
        String STR_METHOD_NAME = " testSubmitSLAccountOpen_case0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_aioSLAccountOpenUnitServiceImpl =
            new WEB3AioSLAccountOpenUnitServiceImpl();

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        Calendar ca =  Calendar.getInstance();
        ca.set(2007,9-1,27);
        Date date = ca.getTime();
        Timestamp st = new Timestamp(date.getTime());
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.tc."
            + "gentrade.stdimpls.TradingSystemImpl",
            "getSystemTimestamp",
            new Class[]{},
            st);

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        BranchParams l_branchParams = TestDBUtility.getBranchRow();

        CommSerialNumbersParams l_commSerialNumbersParams =
            new CommSerialNumbersParams();
        l_commSerialNumbersParams.setInstitutionCode(
            l_mainAccountParams.getInstitutionCode());
        l_commSerialNumbersParams.setSerialNumberName("stock_secured_loan");
        l_commSerialNumbersParams.setSerialNumber("0102031224");
        l_commSerialNumbersParams.setCreatedTimestamp(
            WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp()));
        l_commSerialNumbersParams.setLastUpdatedTimestamp(
            WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp()));

        SystemPreferencesParams l_systemPreferencesParams =
            TestDBUtility.getSystemPreferencesRow();
        l_systemPreferencesParams.setName("46_CREDIT_URL");
        l_systemPreferencesParams.setValue("http://www.daiwa.com");

        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            TestDBUtility.deleteAll(StockSecuredLoanParams.TYPE);
            TestDBUtility.deleteAll(CommSerialNumbersParams.TYPE);
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);

            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_commSerialNumbersParams);
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        try
        {
            WEB3SLAccountOpenRequest l_slAccountOpenRequest =
                new WEB3SLAccountOpenRequest();

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl."
                + "OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImpl());

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            QueryProcessor l_processor = Processors.getDefaultProcessor();
            List l_lisResultList =
                l_processor.doFindAllQuery(StockSecuredLoanParams.TYPE);
            assertEquals(0, l_lisResultList.size());

            l_lisResultList =
                l_processor.doFindAllQuery(CommSerialNumbersParams.TYPE);
            assertEquals(1, l_lisResultList.size());

            WEB3SLAccountOpenResponse l_slAccountOpenResponse =
                l_aioSLAccountOpenUnitServiceImpl.submitSLAccountOpen(
                    l_slAccountOpenRequest);

            assertNotNull(l_slAccountOpenResponse);

            WEB3SLAccountBaseInfoUnit l_actualSLAccountBaseInfoUnit =
                l_slAccountOpenResponse.accountBaseInfo;

            assertNotNull(l_actualSLAccountBaseInfoUnit);

            assertEquals(l_mainAccountParams.getFamilyNameAlt1(),
                l_actualSLAccountBaseInfoUnit.accountNameKana);
            assertEquals(l_mainAccountParams.getFamilyName(),
                l_actualSLAccountBaseInfoUnit.accountName);
            assertEquals(l_mainAccountParams.getEraBorn(),
                l_actualSLAccountBaseInfoUnit.eraBorn);
            assertEquals(l_mainAccountParams.getBornDate(),
                l_actualSLAccountBaseInfoUnit.bornDate);
            assertEquals(l_mainAccountParams.getSex(),
                l_actualSLAccountBaseInfoUnit.sex);
            assertEquals(l_mainAccountParams.getZipCode(),
                l_actualSLAccountBaseInfoUnit.zipCode);
            assertEquals(l_mainAccountParams.getAddressLine1(),
                l_actualSLAccountBaseInfoUnit.address1);
            assertEquals(l_mainAccountParams.getAddressLine2(),
                l_actualSLAccountBaseInfoUnit.address2);
            assertEquals(l_mainAccountParams.getAddressLine3(),
                l_actualSLAccountBaseInfoUnit.address3);
            assertEquals(l_mainAccountParams.getEmailAddress(),
                l_actualSLAccountBaseInfoUnit.mailAddress);

            assertEquals("0709270014", l_slAccountOpenResponse.stockLoanAccount);
            assertEquals("http://www.daiwa.com", l_slAccountOpenResponse.url);

            l_lisResultList =
                l_processor.doFindAllQuery(StockSecuredLoanParams.TYPE);
            assertEquals(1, l_lisResultList.size());

            StockSecuredLoanParams l_resultStockSecuredLoanParams =
                (StockSecuredLoanParams)l_lisResultList.get(0);
            assertEquals("0709270014",
                l_resultStockSecuredLoanParams.getStockLoanAccountCode());
            assertEquals(l_mainAccountParams.getAccountId(),
                l_resultStockSecuredLoanParams.getAccountId());
            assertEquals(l_mainAccountParams.getInstitutionCode(),
                l_resultStockSecuredLoanParams.getInstitutionCode());
            assertEquals(l_mainAccountParams.getBranchCode(),
                l_resultStockSecuredLoanParams.getBranchCode());
            assertEquals(l_mainAccountParams.getAccountCode(),
                l_resultStockSecuredLoanParams.getAccountCode());
            assertEquals(WEB3AccountOpenStatusDef.REQUEST,
                l_resultStockSecuredLoanParams.getAccountOpenStatus());
            assertEquals(l_mainAccountParams.getYellowCustomer(),
                l_resultStockSecuredLoanParams.getYCustomerData());
            assertEquals(l_mainAccountParams.getExaminLockFlag(),
                l_resultStockSecuredLoanParams.getExaminLockFlag());
            assertEquals(l_mainAccountParams.getBranchLock(),
                l_resultStockSecuredLoanParams.getBranchLock());
            assertEquals(l_mainAccountParams.getMngLockFlag(),
                l_resultStockSecuredLoanParams.getMngLockFlag());
            assertEquals(l_mainAccountParams.getMngLockFlagAdvance(),
                l_resultStockSecuredLoanParams.getMngLockFlagAdvance());
            assertEquals(l_mainAccountParams.getMngLockFlagUnpayMargin(),
                l_resultStockSecuredLoanParams.getMngLockFlagUnpayMargin());
            assertEquals(l_mainAccountParams.getMngLockFlagShortSecurity(),
                l_resultStockSecuredLoanParams.getMngLockFlagShortSecurity());
            assertEquals(l_mainAccountParams.getMngLockFlagUnsubstitDepo(),
                l_resultStockSecuredLoanParams.getMngLockFlagUnsubstitDepo());
            assertEquals("0", l_resultStockSecuredLoanParams.getLastUpdater());

            l_lisResultList =
                l_processor.doFindAllQuery(CommSerialNumbersParams.TYPE);
            assertEquals(1, l_lisResultList.size());

            CommSerialNumbersParams l_resultCommSerialNumbersParams =
                (CommSerialNumbersParams)l_lisResultList.get(0);
            assertEquals("0D",
                l_resultCommSerialNumbersParams.getInstitutionCode());
            assertEquals("stock_secured_loan",
                l_resultCommSerialNumbersParams.getSerialNumberName());
            assertEquals("070927001",
                l_resultCommSerialNumbersParams.getSerialNumber());

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(MainAccountParams.TYPE);
                TestDBUtility.deleteAll(SubAccountParams.TYPE);
                TestDBUtility.deleteAll(InstitutionParams.TYPE);
                TestDBUtility.deleteAll(BranchParams.TYPE);
                TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
                TestDBUtility.deleteAll(StockSecuredLoanParams.TYPE);
                TestDBUtility.deleteAll(CommSerialNumbersParams.TYPE);
                TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
            finally
            {
                TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
            }
        }
    }

    /**
     *
     */
    public void testSubmitSLAccountOpen_case0006()
    {
        String STR_METHOD_NAME = " testSubmitSLAccountOpen_case0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_aioSLAccountOpenUnitServiceImpl =
            new WEB3AioSLAccountOpenUnitServiceImpl();

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

        Date l_datCurrentDay = WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp());
        Timestamp l_tsCurrentDay = new Timestamp(l_datCurrentDay.getTime());

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setMngLockOffStartDate(null);
        l_mainAccountParams.setBranchLock(WEB3BranchLockDef.BRANCH_LOCK);
        l_mainAccountParams.setOrderPermission(WEB3OrderPermissionDef.NO_PERMISSION);
        l_mainAccountParams.setMngLockFlag(WEB3MngLockDef.LOCK);

        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();

        BranchParams l_branchParams = TestDBUtility.getBranchRow();

        StockSecuredLoanParams l_stockSecuredLoanParams =
            TestDBUtility.getStockSecuredLoanRow();
        l_stockSecuredLoanParams.setAccountId(l_mainAccountParams.getAccountId());
        l_stockSecuredLoanParams.setAppliDate(l_tsCurrentDay);
        l_stockSecuredLoanParams.setStockLoanAccountCode("123");

        SystemPreferencesParams l_systemPreferencesParams =
            TestDBUtility.getSystemPreferencesRow();
        l_systemPreferencesParams.setName("46_CREDIT_URL");
        l_systemPreferencesParams.setValue("http://www.daiwa.com");

        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            TestDBUtility.deleteAll(StockSecuredLoanParams.TYPE);
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);

            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_stockSecuredLoanParams);
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        try
        {
            WEB3SLAccountOpenRequest l_slAccountOpenRequest =
                new WEB3SLAccountOpenRequest();

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl."
                + "OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            l_aioSLAccountOpenUnitServiceImpl.submitSLAccountOpen(
                l_slAccountOpenRequest);

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02957, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(MainAccountParams.TYPE);
                TestDBUtility.deleteAll(SubAccountParams.TYPE);
                TestDBUtility.deleteAll(InstitutionParams.TYPE);
                TestDBUtility.deleteAll(BranchParams.TYPE);
                TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
                TestDBUtility.deleteAll(StockSecuredLoanParams.TYPE);
                TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
            finally
            {
                TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
            }
        }
    }

    /**
     *
     */
    public void testSubmitSLAccountOpen_case0007()
    {
        String STR_METHOD_NAME = " testSubmitSLAccountOpen_case0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_aioSLAccountOpenUnitServiceImpl =
            new WEB3AioSLAccountOpenUnitServiceImpl();

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

        Date l_datCurrentDay = WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp());
        Timestamp l_tsCurrentDay = new Timestamp(l_datCurrentDay.getTime());

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setEraBorn("4");
        l_mainAccountParams.setBornDate("101010");

        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();

        BranchParams l_branchParams = TestDBUtility.getBranchRow();

        BranchPreferencesParams l_branchPreferencesParams =
            TestDBUtility.getBranchPreferencesRow();
        l_branchPreferencesParams.setBranchId(l_mainAccountParams.getBranchId());
        l_branchPreferencesParams.setName(
            WEB3BranchPreferencesNameDef.SL_LOWLIMIT_AGE_CHECK);
        l_branchPreferencesParams.setNameSerialNo(1);
        l_branchPreferencesParams.setValue("255");

        StockSecuredLoanParams l_stockSecuredLoanParams =
            TestDBUtility.getStockSecuredLoanRow();
        l_stockSecuredLoanParams.setAccountId(l_mainAccountParams.getAccountId());
        l_stockSecuredLoanParams.setAppliDate(l_tsCurrentDay);
        l_stockSecuredLoanParams.setStockLoanAccountCode("123");

        SystemPreferencesParams l_systemPreferencesParams =
            TestDBUtility.getSystemPreferencesRow();
        l_systemPreferencesParams.setName("46_CREDIT_URL");
        l_systemPreferencesParams.setValue("http://www.daiwa.com");

        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            TestDBUtility.deleteAll(StockSecuredLoanParams.TYPE);
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);

            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            TestDBUtility.insertWithDel(l_stockSecuredLoanParams);
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        try
        {
            WEB3SLAccountOpenRequest l_slAccountOpenRequest =
                new WEB3SLAccountOpenRequest();

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl."
                + "OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            l_aioSLAccountOpenUnitServiceImpl.submitSLAccountOpen(
                l_slAccountOpenRequest);

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02955, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(MainAccountParams.TYPE);
                TestDBUtility.deleteAll(SubAccountParams.TYPE);
                TestDBUtility.deleteAll(InstitutionParams.TYPE);
                TestDBUtility.deleteAll(BranchParams.TYPE);
                TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
                TestDBUtility.deleteAll(StockSecuredLoanParams.TYPE);
                TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
            finally
            {
                TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
            }
        }
    }

    /**
     *
     */
    public void testSubmitSLAccountOpen_case0008()
    {
        String STR_METHOD_NAME = " testSubmitSLAccountOpen_case0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_aioSLAccountOpenUnitServiceImpl =
            new WEB3AioSLAccountOpenUnitServiceImpl();

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

        Date l_datCurrentDay = WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp());
        Timestamp l_tsCurrentDay = new Timestamp(l_datCurrentDay.getTime());

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setEraBorn("4");
        l_mainAccountParams.setBornDate("101010");

        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();

        BranchParams l_branchParams = TestDBUtility.getBranchRow();

        BranchPreferencesParams l_branchPreferencesParams =
            TestDBUtility.getBranchPreferencesRow();

        StockSecuredLoanParams l_stockSecuredLoanParams =
            TestDBUtility.getStockSecuredLoanRow();
        l_stockSecuredLoanParams.setAccountId(l_mainAccountParams.getAccountId());
        l_stockSecuredLoanParams.setAppliDate(l_tsCurrentDay);
        l_stockSecuredLoanParams.setStockLoanAccountCode("123");

        SystemPreferencesParams l_systemPreferencesParams =
            TestDBUtility.getSystemPreferencesRow();
        l_systemPreferencesParams.setName("46_CREDIT_URL");
        l_systemPreferencesParams.setValue("http://www.daiwa.com");

        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
            TestDBUtility.deleteAll(StockSecuredLoanParams.TYPE);
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);

            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_branchParams);

            l_branchPreferencesParams.setBranchId(l_mainAccountParams.getBranchId());
            l_branchPreferencesParams.setName(
                WEB3BranchPreferencesNameDef.SL_LOWLIMIT_AGE_CHECK);
            l_branchPreferencesParams.setNameSerialNo(1);
            l_branchPreferencesParams.setValue("0");
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            l_branchPreferencesParams.setBranchId(l_mainAccountParams.getBranchId());
            l_branchPreferencesParams.setName(
                WEB3BranchPreferencesNameDef.SL_HIGHLIMIT_AGE_CHECK);
            l_branchPreferencesParams.setNameSerialNo(1);
            l_branchPreferencesParams.setValue("0");
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            TestDBUtility.insertWithDel(l_stockSecuredLoanParams);
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        try
        {
            WEB3SLAccountOpenRequest l_slAccountOpenRequest =
                new WEB3SLAccountOpenRequest();

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl."
                + "OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            l_aioSLAccountOpenUnitServiceImpl.submitSLAccountOpen(
                l_slAccountOpenRequest);

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02955, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(MainAccountParams.TYPE);
                TestDBUtility.deleteAll(SubAccountParams.TYPE);
                TestDBUtility.deleteAll(InstitutionParams.TYPE);
                TestDBUtility.deleteAll(BranchParams.TYPE);
                TestDBUtility.deleteAll(BranchPreferencesParams.TYPE);
                TestDBUtility.deleteAll(StockSecuredLoanParams.TYPE);
                TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
            finally
            {
                TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
            }
        }
    }

    /**
     *
     */
    public void testExecute_case0001()
    {
        String STR_METHOD_NAME = " testExecute_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_aioSLAccountOpenUnitServiceImpl =
            new WEB3AioSLAccountOpenUnitServiceImpl();

        WEB3FXAccOpenAskingRequest l_request = null;

        try
        {
            l_aioSLAccountOpenUnitServiceImpl.execute(l_request);

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     *
     */
    public void testExecute_case0002()
    {
        String STR_METHOD_NAME = " testExecute_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_aioSLAccountOpenUnitServiceImpl =
            new WEB3AioSLAccountOpenUnitServiceImpl();

        WEB3FXAccOpenAskingRequest l_fxAccOpenAskingRequest =
            new WEB3FXAccOpenAskingRequest();

        try
        {
            l_aioSLAccountOpenUnitServiceImpl.execute(l_fxAccOpenAskingRequest);

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_ex.getErrorInfo());
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     *
     */
    public void testExecute_case0003()
    {
        String STR_METHOD_NAME = " testExecute_case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_aioSLAccountOpenUnitServiceImpl =
            new WEB3AioSLAccountOpenUnitServiceImpl();

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        BranchParams l_branchParams = TestDBUtility.getBranchRow();

        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);

            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_branchParams);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        try
        {
            WEB3SLAccountOpenApplyRequest l_slAccountOpenApplyRequest =
                new WEB3SLAccountOpenApplyRequest();

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl."
                + "OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            l_aioSLAccountOpenUnitServiceImpl.execute(l_slAccountOpenApplyRequest);

            assertTrue(true);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(MainAccountParams.TYPE);
                TestDBUtility.deleteAll(SubAccountParams.TYPE);
                TestDBUtility.deleteAll(InstitutionParams.TYPE);
                TestDBUtility.deleteAll(BranchParams.TYPE);
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
            finally
            {
                TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
            }
        }
    }

    /**
     *
     */
    public void testExecute_case0004()
    {
        String STR_METHOD_NAME = " testExecute_case0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_aioSLAccountOpenUnitServiceImpl =
            new WEB3AioSLAccountOpenUnitServiceImpl();

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

        Date l_datCurrentDay = WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp());
        Timestamp l_tsCurrentDay = new Timestamp(l_datCurrentDay.getTime());

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        BranchParams l_branchParams = TestDBUtility.getBranchRow();

        StockSecuredLoanParams l_stockSecuredLoanParams =
            TestDBUtility.getStockSecuredLoanRow();
        l_stockSecuredLoanParams.setAccountId(l_mainAccountParams.getAccountId());
        l_stockSecuredLoanParams.setAppliDate(l_tsCurrentDay);
        l_stockSecuredLoanParams.setStockLoanAccountCode("123");

        SystemPreferencesParams l_systemPreferencesParams =
            TestDBUtility.getSystemPreferencesRow();
        l_systemPreferencesParams.setName("46_CREDIT_URL");
        l_systemPreferencesParams.setValue("http://www.daiwa.com");

        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(StockSecuredLoanParams.TYPE);
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);

            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_stockSecuredLoanParams);
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        try
        {

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl."
                + "OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            WEB3SLAccountOpenRequest l_slAccountOpenRequest =
                new WEB3SLAccountOpenRequest();

            WEB3SLAccountOpenResponse l_slAccountOpenResponse =
                (WEB3SLAccountOpenResponse)l_aioSLAccountOpenUnitServiceImpl.execute(
                    l_slAccountOpenRequest);

            assertNotNull(l_slAccountOpenResponse);

            WEB3SLAccountBaseInfoUnit l_actualSLAccountBaseInfoUnit =
                l_slAccountOpenResponse.accountBaseInfo;

            assertNotNull(l_actualSLAccountBaseInfoUnit);

            assertEquals(l_mainAccountParams.getFamilyNameAlt1(),
                l_actualSLAccountBaseInfoUnit.accountNameKana);
            assertEquals(l_mainAccountParams.getFamilyName(),
                l_actualSLAccountBaseInfoUnit.accountName);
            assertEquals(l_mainAccountParams.getEraBorn(),
                l_actualSLAccountBaseInfoUnit.eraBorn);
            assertEquals(l_mainAccountParams.getBornDate(),
                l_actualSLAccountBaseInfoUnit.bornDate);
            assertEquals(l_mainAccountParams.getSex(),
                l_actualSLAccountBaseInfoUnit.sex);
            assertEquals(l_mainAccountParams.getZipCode(),
                l_actualSLAccountBaseInfoUnit.zipCode);
            assertEquals(l_mainAccountParams.getAddressLine1(),
                l_actualSLAccountBaseInfoUnit.address1);
            assertEquals(l_mainAccountParams.getAddressLine2(),
                l_actualSLAccountBaseInfoUnit.address2);
            assertEquals(l_mainAccountParams.getAddressLine3(),
                l_actualSLAccountBaseInfoUnit.address3);
            assertEquals(l_mainAccountParams.getEmailAddress(),
                l_actualSLAccountBaseInfoUnit.mailAddress);

            assertEquals("123", l_slAccountOpenResponse.stockLoanAccount);
            assertEquals("http://www.daiwa.com", l_slAccountOpenResponse.url);

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        }
    }

}
@
