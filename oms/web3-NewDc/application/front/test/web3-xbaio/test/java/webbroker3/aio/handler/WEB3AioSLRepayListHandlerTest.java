head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.24.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AioSLRepayListHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 証券担保ローン返済一覧ハンドラのテストクラス(WEB3AioSLRepayListHandlerTest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/09/25 周墨洋 (中訊) 新規作成 仕様変更・モデルNo.757
*/

package webbroker3.aio.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;

import test.util.TestDBUtility;
import webbroker3.aio.message.WEB3SLRepayCancelListRequest;
import webbroker3.aio.message.WEB3SLRepayCancelListResponse;
import webbroker3.aio.message.WEB3SLRepayUnit;
import webbroker3.aio.service.delegate.WEB3AioSLRepayListService;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioSLRepayListServiceImpl;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AccountOpenStatusDef;
import webbroker3.common.define.WEB3SecuredLoanSecAccOpenDivDef;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.StockSecuredLoanParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * (証券担保ローン返済一覧ハンドラ)<BR>
 * 証券担保ローン返済一覧ハンドラのテストクラス<BR>
 *
 * @@author 周墨洋
 * @@version 1.0
 */
public class WEB3AioSLRepayListHandlerTest extends TestBaseForMock
{

    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AioSLRepayListHandlerTest.class);

    /**
     * 証券担保ローン返済一覧ハンドラ
     */
    private WEB3AioSLRepayListHandler l_handler = null;

    /**
     * @@param arg0
     */
    public WEB3AioSLRepayListHandlerTest(String arg0)
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
    public void testRrepayCancelListRequest_case0001()
    {
        final String STR_METHOD_NAME = " testRrepayCancelListRequest_case0001()";
        log.entering(STR_METHOD_NAME);

        l_handler = new WEB3AioSLRepayListHandler();

        try
        {
            WEB3SLRepayCancelListRequest l_request =
                new WEB3SLRepayCancelListRequest();
            Services.unregisterService(WEB3AioSLRepayListService.class);

            WEB3SLRepayCancelListResponse l_response =
                (WEB3SLRepayCancelListResponse)l_handler.repayCancelListRequest(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            Services.registerService(
                WEB3AioSLRepayListService.class,
                new WEB3AioSLRepayListServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     *
     */
    public void testRrepayCancelListRequest_case0002()
    {
        final String STR_METHOD_NAME = " testRrepayCancelListRequest_case0002()";
        log.entering(STR_METHOD_NAME);

        l_handler = new WEB3AioSLRepayListHandler();

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(StockSecuredLoanParams.TYPE);

            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
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

            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl."
                + "OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            WEB3SLRepayCancelListRequest l_request =
                new WEB3SLRepayCancelListRequest();

            WEB3SLRepayCancelListResponse l_response =
                (WEB3SLRepayCancelListResponse)l_handler.repayCancelListRequest(l_request);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02914, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     *
     */
    public void testRrepayCancelListRequest_case0003()
    {
        final String STR_METHOD_NAME = " testRrepayCancelListRequest_case0003()";
        log.entering(STR_METHOD_NAME);

        l_handler = new WEB3AioSLRepayListHandler();

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setSecuredLoanSecAccOpenDiv(
            WEB3SecuredLoanSecAccOpenDivDef.OPEN);
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        StockSecuredLoanParams l_stockSecuredLoanParams =
            TestDBUtility.getStockSecuredLoanRow();
        l_stockSecuredLoanParams.setAccountId(l_mainAccountParams.getAccountId());
        l_stockSecuredLoanParams.setAccountOpenStatus(WEB3AccountOpenStatusDef.PROMISE);

        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(StockSecuredLoanParams.TYPE);
            TestDBUtility.deleteAll(AioOrderUnitParams.TYPE);

            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_stockSecuredLoanParams);
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

            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl."
                + "OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            WEB3SLRepayCancelListRequest l_request =
                new WEB3SLRepayCancelListRequest();

            WEB3SLRepayCancelListResponse l_response =
                (WEB3SLRepayCancelListResponse)l_handler.repayCancelListRequest(l_request);

            assertNotNull(l_response);
            assertEquals(WEB3SLRepayCancelListResponse.class, l_response.getClass());
            assertNotNull(l_response.stockLoanRepayUnits);
            WEB3SLRepayUnit[] l_actualSLRepayUnits =
                l_response.stockLoanRepayUnits;
            assertEquals(0, l_actualSLRepayUnits.length);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

}
@
