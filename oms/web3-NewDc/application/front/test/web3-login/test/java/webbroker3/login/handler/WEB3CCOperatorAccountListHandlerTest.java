head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.19.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3CCOperatorAccountListHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : CCオペレータ対象顧客一覧ハンドラのテストクラス(WEB3CCOperatorAccountListHandlerTest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/07/23 周墨洋 (中訊) 新規作成 モデルNo.039
*/

package webbroker3.login.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;

import test.util.TestDBUtility;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.data.TraderAccountInfoParams;
import webbroker3.login.message.WEB3CCOperatorAccountListRequest;
import webbroker3.login.message.WEB3CCOperatorAccountListResponse;
import webbroker3.login.message.WEB3TraderAccountInfo;
import webbroker3.login.message.WEB3TraderAccountInfosSortKey;
import webbroker3.login.service.delegate.WEB3CCOperatorAccountListService;
import webbroker3.login.service.delegate.stdimpls.WEB3CCOperatorAccountListServiceImpl;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (CCオペレータ対象顧客一覧ハンドラ)<BR>
 * CCオペレータ対象顧客一覧ハンドラのテストクラス<BR>
 *
 * @@author 周墨洋
 * @@version 1.0
 */
public class WEB3CCOperatorAccountListHandlerTest extends TestBaseForMock
{

    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
                WEB3CCOperatorAccountListHandlerTest.class);

    /**
     * CCオペレータ対象顧客一覧ハンドラ
     */
    private WEB3CCOperatorAccountListHandler l_handler = null;

    /**
     * @@param arg0
     */
    public WEB3CCOperatorAccountListHandlerTest(String arg0)
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
    public void testCCOperatorAccountListRequest_case0001()
    {
        final String STR_METHOD_NAME = " testCCOperatorAccountListRequest_case0001()";
        log.entering(STR_METHOD_NAME);

        l_handler = new WEB3CCOperatorAccountListHandler();

        try
        {
            WEB3CCOperatorAccountListRequest l_request =
                new WEB3CCOperatorAccountListRequest();
            Services.unregisterService(WEB3CCOperatorAccountListService.class);

            WEB3CCOperatorAccountListResponse l_response =
                (WEB3CCOperatorAccountListResponse)l_handler.ccOperatorAccountListRequest(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_response.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3CCOperatorAccountListService.class,
                new WEB3CCOperatorAccountListServiceImpl());
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     *
     */
    public void testCCOperatorAccountListRequest_case0002()
    {
        final String STR_METHOD_NAME = " testCCOperatorAccountListRequest_case0002()";
        log.entering(STR_METHOD_NAME);

        l_handler = new WEB3CCOperatorAccountListHandler();

        try
        {

            WEB3CCOperatorAccountListRequest l_request =
                new WEB3CCOperatorAccountListRequest();
            l_request.sortKeys = null;

            WEB3CCOperatorAccountListResponse l_response =
                (WEB3CCOperatorAccountListResponse)l_handler.ccOperatorAccountListRequest(l_request);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00231, l_response.errorInfo);
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
    public void testCCOperatorAccountListRequest_case0003()
    {
        final String STR_METHOD_NAME = " testCCOperatorAccountListRequest_case0002()";
        log.entering(STR_METHOD_NAME);

        l_handler = new WEB3CCOperatorAccountListHandler();

        try
        {
            TestDBUtility.deleteAll(TraderAccountInfoParams.TYPE);

            TraderAccountInfoParams l_traderAccountInfoParams =
                new TraderAccountInfoParams();
            l_traderAccountInfoParams.setTraderId(1112345L);
            l_traderAccountInfoParams.setAccountId(2221234567L);
            l_traderAccountInfoParams.setInstitutionCode("0D");
            l_traderAccountInfoParams.setBranchCode("381");
            l_traderAccountInfoParams.setTraderCode("12345");
            l_traderAccountInfoParams.setDepartmentCode("12");
            l_traderAccountInfoParams.setAccountCode("123456");
            l_traderAccountInfoParams.setFamilyName("何毅来家居");
            l_traderAccountInfoParams.setFamilyNameAlt1("かきくけこ");
            l_traderAccountInfoParams.setLastUpdater("phantom");
            l_traderAccountInfoParams.setCreatedTimestamp(
                WEB3DateUtility.getDate("20070724", "yyyyMMdd"));
            l_traderAccountInfoParams.setLastUpdatedTimestamp(
                WEB3DateUtility.getDate("20070724", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_traderAccountInfoParams);

            l_traderAccountInfoParams = new TraderAccountInfoParams();
            l_traderAccountInfoParams.setTraderId(1112345L);
            l_traderAccountInfoParams.setAccountId(2221234567L);
            l_traderAccountInfoParams.setInstitutionCode("0D");
            l_traderAccountInfoParams.setBranchCode("381");
            l_traderAccountInfoParams.setTraderCode("12345");
            l_traderAccountInfoParams.setDepartmentCode("12");
            l_traderAccountInfoParams.setAccountCode("123456");
            l_traderAccountInfoParams.setFamilyName("亜衣于江緒");
            l_traderAccountInfoParams.setFamilyNameAlt1("あいうえお");
            l_traderAccountInfoParams.setLastUpdater("phantom");
            l_traderAccountInfoParams.setCreatedTimestamp(
                WEB3DateUtility.getDate("20070724", "yyyyMMdd"));
            l_traderAccountInfoParams.setLastUpdatedTimestamp(
                WEB3DateUtility.getDate("20070724", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_traderAccountInfoParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        WEB3CCOperatorAccountListRequest l_request =
            new WEB3CCOperatorAccountListRequest();

        l_request.acceptCode = "123456";
        l_request.nameKanji = "亜衣于江緒";
        l_request.nameKana = "あいうえお";
        l_request.pageIndex = "1";
        l_request.pageSize = "12";

        WEB3TraderAccountInfosSortKey[] l_sortKeys =
            new WEB3TraderAccountInfosSortKey[2];
        WEB3TraderAccountInfosSortKey l_sortKey =
            new WEB3TraderAccountInfosSortKey();
        l_sortKey.keyItem = "acceptCode";
        l_sortKey.ascDesc = "A";
        l_sortKeys[0] = l_sortKey;
        l_sortKey = new WEB3TraderAccountInfosSortKey();
        l_sortKey.keyItem = "nameKana";
        l_sortKey.ascDesc = "D";
        l_sortKeys[1] = l_sortKey;
        l_request.sortKeys = l_sortKeys;

        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                "1112345");

            WEB3CCOperatorAccountListResponse l_response = null;

            l_response =
                (WEB3CCOperatorAccountListResponse)l_handler.ccOperatorAccountListRequest(l_request);

            assertNotNull(l_response);

            WEB3TraderAccountInfo[] l_expectedTraderAccountInfos = new WEB3TraderAccountInfo[1];
            WEB3TraderAccountInfo l_expectedTraderAccountInfo = new WEB3TraderAccountInfo();

            l_expectedTraderAccountInfo.branchCode = "381";
            l_expectedTraderAccountInfo.acceptCode = "123456";
            l_expectedTraderAccountInfo.nameKanji = "亜衣于江緒";
            l_expectedTraderAccountInfo.nameKana = "あいうえお";
            l_expectedTraderAccountInfo.accountID = 2221234567L;
            l_expectedTraderAccountInfos[0] = l_expectedTraderAccountInfo;

            WEB3TraderAccountInfo[] l_actualTraderAccountInfos = l_response.traderAccoutInfos;

            assertEquals("1", l_response.totalPages);
            assertEquals("1", l_response.totalRecords);
            assertEquals("1", l_response.pageIndex);
            assertEquals(l_expectedTraderAccountInfos.length, l_actualTraderAccountInfos.length);
            for (int i = 0; i < l_actualTraderAccountInfos.length; i++)
            {
                assertEquals(
                    l_expectedTraderAccountInfos[i].branchCode,
                    l_actualTraderAccountInfos[i].branchCode);
                assertEquals(
                    l_expectedTraderAccountInfos[i].acceptCode,
                    l_actualTraderAccountInfos[i].acceptCode);
                assertEquals(
                    l_expectedTraderAccountInfos[i].nameKanji,
                    l_actualTraderAccountInfos[i].nameKanji);
                assertEquals(
                    l_expectedTraderAccountInfos[i].nameKana,
                    l_actualTraderAccountInfos[i].nameKana);
                assertEquals(
                    l_expectedTraderAccountInfos[i].accountID,
                    l_actualTraderAccountInfos[i].accountID);
            }

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(TraderAccountInfoParams.TYPE);
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
        }
    }

}
@
