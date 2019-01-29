head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.49.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FaqInputServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.faq.service.delegate.stdimpls;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;

import test.util.TestDBUtility;
import webbroker3.faq.message.WEB3FaqInputRequest;
import webbroker3.faq.message.WEB3FaqInputResponse;
import webbroker3.faq.service.delegate.stdimpls.WEB3FaqInputServiceImpl;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3FaqInputServiceImplTest extends TestBaseForMock
{

    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FaqInputServiceImplTest.class);

    /**
     * 問合せ管理お問合せ入力サービスImpl
     */
    WEB3FaqInputServiceImpl l_faqInputServiceImpl = null;

    /**
     * @@param arg0
     */
    public WEB3FaqInputServiceImplTest(String arg0)
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
    public void testGetInputScreen_case0001()
    {
        String STR_METHOD_NAME = " testGetInputScreen_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_faqInputServiceImpl = new WEB3FaqInputServiceImpl();

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setEmailAddress("v1@@dir.co.jp");
        l_mainAccountParams.setEmailAddressAlt1("sv2@@dir.co.jp");
        l_mainAccountParams.setEmailAddressAlt2("mpd3@@dir.co.jp");

        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setBranchCode("123");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setMarketCode("N1");

        try
        {
            TestDBUtility.deleteAllAndCommit(MainAccountParams.TYPE);
            TestDBUtility.deleteAllAndCommit(TradingTimeParams.TYPE);

            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);
            TestDBUtility.insertWithDelAndCommit(l_tradingTimeParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        try
        {
            WEB3FaqInputRequest l_faqInputRequest = new WEB3FaqInputRequest();

            WEB3FaqInputResponse l_faqInputResponse;

            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "isAccountIdSet",
                new Class[] {},
                new Boolean(true));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            Method l_method =
                WEB3FaqInputServiceImpl.class.getDeclaredMethod("getInputScreen",
                    new Class[] {WEB3FaqInputRequest.class});
            l_method.setAccessible(true);
            l_faqInputResponse =
                (WEB3FaqInputResponse)l_method.invoke(
                    l_faqInputServiceImpl,
                    new Object[] {l_faqInputRequest});

            assertNotNull(l_faqInputResponse);
            assertEquals("v1@@dir.co.jp", l_faqInputResponse.mailAddress);
            assertEquals("sv2@@dir.co.jp", l_faqInputResponse.mailAddress2);
            assertEquals("mpd3@@dir.co.jp", l_faqInputResponse.mailAddress3);

        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAllAndCommit(MainAccountParams.TYPE);
                TestDBUtility.deleteAllAndCommit(TradingTimeParams.TYPE);
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }

            log.exiting(TEST_END + STR_METHOD_NAME);
        }

    }

}
@
