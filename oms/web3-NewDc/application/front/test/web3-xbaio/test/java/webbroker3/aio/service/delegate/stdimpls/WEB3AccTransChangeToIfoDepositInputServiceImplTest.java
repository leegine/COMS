head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.36.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AccTransChangeToIfoDepositInputServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (äî)ëÂòaëçå§ èÿåîÉ\ÉäÉÖÅ[ÉVÉáÉìÉVÉXÉeÉÄëÊìÒïî
File Name        : ÅiWEB3AccTransChangeToIfoDepositInputServiceImplTest.javaÅj
Author Name      : Daiwa Institute of Research
Revision History : 2008/12/16 ïêîg (íÜêu) êVãKçÏê¨
*/
package webbroker3.aio.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;

import test.util.TestDBUtility;

import webbroker3.aio.message.WEB3AccTransChangeToIfoDepositInputRequest;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AccTransChangeToIfoDepositInputServiceImplTest extends TestBaseForMock
{

    public WEB3AccTransChangeToIfoDepositInputServiceImplTest(String arg0)
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

    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AccTransChangeToIfoDepositInputServiceImplTest.class);

    /**
     * testExecute_C0001<BR>
     * ÈÑÊöì¸å˚ô“ù…ê•î€à◊ãÛ<BR>
     * l_request = null<BR>
     */
    public void testExecute_C0001()
    {
        final String STR_METHOD_NAME = "testExecute_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccTransChangeToIfoDepositInputServiceImpl l_impl =
                new WEB3AccTransChangeToIfoDepositInputServiceImpl();
            l_impl.execute(null);
            fail();
        }
        catch(WEB3BaseRuntimeException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80017);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * testExecute_C0002<BR>
     * ÈÑÊöì¸å˚ô“ù…ê•î€à◊ãÛ<BR>
     * l_request != null<BR>
     */
    public void testExecute_C0002()
    {
        final String STR_METHOD_NAME = "testExecute_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccTransChangeToIfoDepositInputServiceImpl l_impl =
                new WEB3AccTransChangeToIfoDepositInputServiceImpl();
            WEB3AccTransChangeToIfoDepositInputRequest l_request =
                new WEB3AccTransChangeToIfoDepositInputRequest();

            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(1001));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "validateOrder",
                new Class[] {SubAccount.class},
                new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_00003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "èåèÇ…äYìñÇ∑ÇÈésèÍÇ™Ç›Ç¬Ç©ÇËÇ‹ÇπÇÒÇ≈ÇµÇΩÅB"));

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(1001);
            l_subAccountParams.setSubAccountType(
                SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            l_impl.execute(l_request);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00003);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * testExecute_C0002<BR>
     * ÈÑÊöì¸å˚ô“ù…ê•î€à◊ãÛ<BR>
     * l_request != null<BR>
     */
    public void testExecute_C0003()
    {
        final String STR_METHOD_NAME = "testExecute_C0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccTransChangeToIfoDepositInputServiceImpl l_impl =
                new WEB3AccTransChangeToIfoDepositInputServiceImpl();
            WEB3AccTransChangeToIfoDepositInputRequest l_request =
                new WEB3AccTransChangeToIfoDepositInputRequest();

            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(1001));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager",
                "validateOrder",
                new Class[] {SubAccount.class},
                null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.WEB3AioOrderManager", 
                "validateOpenFuturesTradedAccount",
                new Class[] {SubAccount.class},
                new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_00004,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "èåèÇ…äYìñÇ∑ÇÈésèÍÇ™Ç›Ç¬Ç©ÇËÇ‹ÇπÇÒÇ≈ÇµÇΩÅB"));
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1001);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(1001);
            l_subAccountParams.setSubAccountType(
                SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            l_impl.execute(l_request);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00004);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
//
//    /**
//     * testExecute_C0002<BR>
//     * ÈÑÊöì¸å˚ô“ù…ê•î€à◊ãÛ<BR>
//     * l_request != null<BR>
//     */
//    public void testExecute_C0004()
//    {
//        final String STR_METHOD_NAME = "testExecute_C0004()";
//        log.entering(STR_METHOD_NAME);
//
//        try
//        {
//            WEB3AccTransChangeToIfoDepositInputServiceImpl l_impl =
//                new WEB3AccTransChangeToIfoDepositInputServiceImpl();
//            WEB3AccTransChangeToIfoDepositInputRequest l_request =
//                new WEB3AccTransChangeToIfoDepositInputRequest();
//
//            MOCK_MANAGER.setIsMockUsed(true);
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//                "getAccountId",
//                new Class[] {},
//                new Long(1001));
//
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "webbroker3.aio.WEB3AioOrderManager",
//                "validateOrder",
//                new Class[] {SubAccount.class},
//                null);
//
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "webbroker3.aio.WEB3AioOrderManager", 
//                "validateOpenFuturesTradedAccount",
//                new Class[] {SubAccount.class},
//                null);
//
//            TestDBUtility.deleteAll(MainAccountParams.TYPE);
//            MainAccountParams l_mainAccountParams =
//                TestDBUtility.getMainAccountRow();
//            l_mainAccountParams.setAccountId(1001);
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//
//            TestDBUtility.deleteAll(SubAccountParams.TYPE);
//            SubAccountParams l_subAccountParams =
//                TestDBUtility.getSubAccountRow();
//            l_subAccountParams.setAccountId(1001);
//            l_subAccountParams.setSubAccountType(
//                SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
//            TestDBUtility.insertWithDel(l_subAccountParams);
//            l_impl.execute(l_request);
//            fail();
//        }
//        catch(WEB3BaseException l_ex)
//        {
//            log.error(STR_METHOD_NAME, l_ex);
//            log.exiting(STR_METHOD_NAME);
//            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00004);
//        }
//        catch(Exception l_ex)
//        {
//            log.error(STR_METHOD_NAME, l_ex);
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//
//        log.exiting(STR_METHOD_NAME);
//    }
}
@
