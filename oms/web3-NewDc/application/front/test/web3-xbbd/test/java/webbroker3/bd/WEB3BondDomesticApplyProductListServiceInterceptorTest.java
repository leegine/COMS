head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.03.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3BondDomesticApplyProductListServiceInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 国内債券応募銘柄一覧サービスインタセプタのテストクラス(WEB3BondDomesticApplyProductListServiceInterceptorTest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/08/03 周墨洋 (中訊) 新規作成 新規作成 仕様変更・モデルNo.224
*/

package webbroker3.bd;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;

import test.util.TestDBUtility;
import test.util.TestSpecialClassUtility;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * (国内債券応募銘柄一覧サービスインタセプタ)<BR>
 * 国内債券応募銘柄一覧サービスインタセプタのテストクラス<BR>
 *
 * @@author 周墨洋
 * @@version 1.0
 */
public class WEB3BondDomesticApplyProductListServiceInterceptorTest
    extends TestBaseForMock
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3BondDomesticApplyProductListServiceInterceptorTest.class);

    /**
     * 国内債券応募銘柄一覧サービスインタセプタ
     */
    WEB3BondDomesticApplyProductListServiceInterceptor l_interceptor =null;

    /**
     * @@param arg0
     */
    public WEB3BondDomesticApplyProductListServiceInterceptorTest(String arg0)
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
    public void testOnCall_case0001()
    {
        final String STR_METHOD_NAME = " testOnCall_case0001()";
        log.entering(STR_METHOD_NAME);

        l_interceptor = new WEB3BondDomesticApplyProductListServiceInterceptor();

        try
        {
            l_interceptor.onCall(null, null);
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_exE)
        {
            log.debug(l_exE.getMessage(), l_exE);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     *
     */
    public void testOnCall_case0002()
    {
        final String STR_METHOD_NAME = " testOnCall_case0002()";
        log.entering(STR_METHOD_NAME);

        l_interceptor = new WEB3BondDomesticApplyProductListServiceInterceptor();

        try
        {
            Object[] l_serviceParams = new Object[]{};
            l_interceptor.onCall(null, l_serviceParams);
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_exE)
        {
            log.debug(l_exE.getMessage(), l_exE);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     *
     */
    public void testOnCall_case0003()
    {
        final String STR_METHOD_NAME = " testOnCall_case0003()";
        log.entering(STR_METHOD_NAME);

        l_interceptor = new WEB3BondDomesticApplyProductListServiceInterceptor();

        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setTradingTimeType("36");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setMarketCode("0");

        try
        {
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);

            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
        }
        catch (Exception l_exE)
        {
            log.debug(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        try
        {
            Object[] l_serviceParams = new Object[]{Object.class};

            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl."
                    + "OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(12345L));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl."
                    + "OpLoginSecurityServiceImpl",
                "isAccountIdSet",
                new Class[] {},
                Boolean.TRUE);

            l_interceptor.onCall(null, l_serviceParams);

            fail();
        }
        catch (WEB3BaseRuntimeException l_exBRE)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_exBRE.getErrorInfo());

            log.debug(l_exBRE.getMessage(), l_exBRE);
        }
        catch (Exception l_exE)
        {
            log.debug(l_exE.getMessage(), l_exE);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(InstitutionParams.TYPE);
                TestDBUtility.deleteAll(BranchParams.TYPE);
                TestDBUtility.deleteAll(MainAccountParams.TYPE);
                TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.debug(l_exE.getMessage(), l_exE);
                log.exiting(STR_METHOD_NAME);
                fail();
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

  /**
   *
   */
  public void testOnCall_case0004()
  {
      final String STR_METHOD_NAME = " testOnCall_case0004()";
      log.entering(STR_METHOD_NAME);

      l_interceptor = new WEB3BondDomesticApplyProductListServiceInterceptor();

      InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
      BranchParams l_branchParams = TestDBUtility.getBranchRow();
      MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
      TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
      l_tradingTimeParams.setTradingTimeType("36");
      l_tradingTimeParams.setBizDateType("1");
      l_tradingTimeParams.setMarketCode("0");

      try
      {
          TestDBUtility.deleteAll(InstitutionParams.TYPE);
          TestDBUtility.deleteAll(BranchParams.TYPE);
          TestDBUtility.deleteAll(MainAccountParams.TYPE);
          TestDBUtility.deleteAll(TradingTimeParams.TYPE);

          TestDBUtility.insertWithDel(l_institutionParams);
          TestDBUtility.insertWithDel(l_branchParams);
          TestDBUtility.insertWithDel(l_mainAccountParams);
          TestDBUtility.insertWithDel(l_tradingTimeParams);
      }
      catch (Exception l_exE)
      {
          log.debug(l_exE.getMessage(), l_exE);
          log.exiting(STR_METHOD_NAME);
          fail();
      }

      try
      {
          Object[] l_serviceParams = new Object[]{Object.class};

          TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
              "com.fitechlabs.xtrade.plugin.security.oplogin.impl."
                  + "OpLoginSecurityServiceImpl",
              "getAccountId",
              new Class[] {},
              new Long(l_mainAccountParams.getAccountId()));

          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
              "com.fitechlabs.xtrade.plugin.security.oplogin.impl."
                  + "OpLoginSecurityServiceImpl",
              "isAccountIdSet",
              new Class[] {},
              Boolean.FALSE);

          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
              "com.fitechlabs.xtrade.plugin.security.oplogin.impl."
                  + "OpLoginSecurityServiceImpl",
              "getLoginInfo",
              new Class[] {},
              new LoginInfoImpl());

          l_interceptor.onCall(null, l_serviceParams);

          fail();
      }
      catch (WEB3BaseRuntimeException l_exBRE)
      {
          assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_exBRE.getErrorInfo());
          log.debug(l_exBRE.getMessage(), l_exBRE);
      }
      catch (Exception l_exE)
      {
          log.debug(l_exE.getMessage(), l_exE);
          fail();
      }
      finally
      {
          try
          {
              TestDBUtility.deleteAll(InstitutionParams.TYPE);
              TestDBUtility.deleteAll(BranchParams.TYPE);
              TestDBUtility.deleteAll(MainAccountParams.TYPE);
              TestDBUtility.deleteAll(TradingTimeParams.TYPE);
          }
          catch (Exception l_exE)
          {
              log.debug(l_exE.getMessage(), l_exE);
              log.exiting(STR_METHOD_NAME);
              fail();
          }
      }

      log.exiting(STR_METHOD_NAME);
  }

    /**
     *
     */
    public void testOnCall_case0005()
    {
        final String STR_METHOD_NAME = " testOnCall_case0005()";
        log.entering(STR_METHOD_NAME);

        l_interceptor = new WEB3BondDomesticApplyProductListServiceInterceptor();

        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setTradingTimeType("36");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setMarketCode("0");

        try
        {
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);

            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
        }
        catch (Exception l_exE)
        {
            log.debug(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        try
        {
            Object[] l_serviceParams = new Object[]{Object.class};

            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl."
                    + "OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl."
                    + "OpLoginSecurityServiceImpl",
                "isAccountIdSet",
                new Class[] {},
                Boolean.TRUE);

            WEB3GentradeTradingClendarContext l_gentradeTradingClendarContext;
            l_gentradeTradingClendarContext =
                (WEB3GentradeTradingClendarContext)l_interceptor.onCall(
                    null,
                    l_serviceParams);

            assertEquals("0D", l_gentradeTradingClendarContext.getInstitutionCode());
            assertEquals("381", l_gentradeTradingClendarContext.getBranchCode());
            assertEquals("0", l_gentradeTradingClendarContext.getMarketCode());
            assertEquals("0", l_gentradeTradingClendarContext.getProductCode());
            assertEquals("36", l_gentradeTradingClendarContext.getTradingTimeType());
            assertEquals("28", l_gentradeTradingClendarContext.getOrderAcceptProduct());
            assertEquals("07", l_gentradeTradingClendarContext.getOrderAcceptTransaction());

            assertEquals(l_gentradeTradingClendarContext,
                ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH));
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (Exception l_exE)
        {
            log.debug(l_exE.getMessage(), l_exE);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(InstitutionParams.TYPE);
                TestDBUtility.deleteAll(BranchParams.TYPE);
                TestDBUtility.deleteAll(MainAccountParams.TYPE);
                TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.debug(l_exE.getMessage(), l_exE);
                log.exiting(STR_METHOD_NAME);
                fail();
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     *
     */
    public void testOnCall_case0006()
    {
        final String STR_METHOD_NAME = " testOnCall_case0006()";
        log.entering(STR_METHOD_NAME);

        l_interceptor = new WEB3BondDomesticApplyProductListServiceInterceptor();

        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setTradingTimeType("36");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setMarketCode("0");
        AdministratorParams l_administratorParams =
            TestDBUtility.getAdministratorRow();
        l_administratorParams.setLoginId(0L);
        l_administratorParams.setBranchCode("381");

        try
        {
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(AdministratorParams.TYPE);

            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_administratorParams);
        }
        catch (Exception l_exE)
        {
            log.debug(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        try
        {
            Object[] l_serviceParams = new Object[]{Object.class};

            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl."
                    + "OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl."
                    + "OpLoginSecurityServiceImpl",
                "isAccountIdSet",
                new Class[] {},
                Boolean.FALSE);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl."
                    + "OpLoginSecurityServiceImpl",
                "getLoginInfo",
                new Class[] {},
                new LoginInfoImpl());

            WEB3GentradeTradingClendarContext l_gentradeTradingClendarContext;
            l_gentradeTradingClendarContext =
                (WEB3GentradeTradingClendarContext)l_interceptor.onCall(
                    null,
                    l_serviceParams);

            assertEquals("0D", l_gentradeTradingClendarContext.getInstitutionCode());
            assertEquals("381", l_gentradeTradingClendarContext.getBranchCode());
            assertEquals("0", l_gentradeTradingClendarContext.getMarketCode());
            assertEquals("0", l_gentradeTradingClendarContext.getProductCode());
            assertEquals("36", l_gentradeTradingClendarContext.getTradingTimeType());
            assertEquals("28", l_gentradeTradingClendarContext.getOrderAcceptProduct());
            assertEquals("07", l_gentradeTradingClendarContext.getOrderAcceptTransaction());

            assertEquals(l_gentradeTradingClendarContext,
                ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH));
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (Exception l_exE)
        {
            log.debug(l_exE.getMessage(), l_exE);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(InstitutionParams.TYPE);
                TestDBUtility.deleteAll(BranchParams.TYPE);
                TestDBUtility.deleteAll(MainAccountParams.TYPE);
                TestDBUtility.deleteAll(TradingTimeParams.TYPE);
                TestDBUtility.deleteAll(AdministratorParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.debug(l_exE.getMessage(), l_exE);
                log.exiting(STR_METHOD_NAME);
                fail();
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     *
     */
    public void testOnReturn_case0001()
    {
        final String STR_METHOD_NAME = " testOnReturn_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_interceptor = new WEB3BondDomesticApplyProductListServiceInterceptor();

        try
        {
            Object l_context = new Object();
            Object l_returnValue = new Object();
            l_interceptor.onReturn(l_context, l_returnValue);

            TestSpecialClassUtility.testServiceInterceptor(l_interceptor);
            assertNull(ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG));
            assertNull(ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.OFFSET_TAG));
            assertNull(ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH));

            assertTrue(true);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     *
     */
    public void testOnThrowable_case0001()
    {
        final String STR_METHOD_NAME = " testOnThrowable_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_interceptor = new WEB3BondDomesticApplyProductListServiceInterceptor();

        Object l_context = new Object();
        Throwable l_throwable = new Throwable();
        l_interceptor.onThrowable(l_context, l_throwable);

        TestSpecialClassUtility.testServiceInterceptor(l_interceptor);
        assertNull(ThreadLocalSystemAttributesRegistry.getAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG));
        assertNull(ThreadLocalSystemAttributesRegistry.getAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG));
        assertNull(ThreadLocalSystemAttributesRegistry.getAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH));

        assertTrue(true);

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

}
@
