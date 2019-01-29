head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.26.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3CCOperatorAccountListServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : CCオペレータ対象顧客一覧サービスImplのテストクラス(WEB3CCOperatorAccountListServiceImplTest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/07/23 周墨洋 (中訊) 新規作成 モデルNo.039
*/

package webbroker3.login.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import test.util.TestDBUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.data.TraderAccountInfoParams;
import webbroker3.gentrade.data.TraderAccountInfoRow;
import webbroker3.login.message.WEB3CCOperatorAccountListRequest;
import webbroker3.login.message.WEB3CCOperatorAccountListResponse;
import webbroker3.login.message.WEB3CCOperatorLoginRequest;
import webbroker3.login.message.WEB3TraderAccountInfo;
import webbroker3.login.message.WEB3TraderAccountInfosSortKey;
import webbroker3.login.service.delegate.WEB3CCOperatorAccountListService;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;

/**
 * (CCオペレータ対象顧客一覧サービスImpl)<BR>
 * CCオペレータ対象顧客一覧サービスImplのテストクラス<BR>
 *
 * @@author 周墨洋
 * @@version 1.0
 */
public class WEB3CCOperatorAccountListServiceImplTest extends TestBaseForMock
{

    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3CCOperatorAccountListServiceImplTest.class);

    /**
     * CCオペレータ対象顧客一覧サービスImpl
     */
    private WEB3CCOperatorAccountListServiceImpl l_serviceImpl = null;

    /**
     * CCオペレータ対象顧客一覧サービス
     */
    private WEB3CCOperatorAccountListService l_service = null;

    /**
     * @@param arg0
     */
    public WEB3CCOperatorAccountListServiceImplTest(String arg0)
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
    public void testCreateQueryDataContainer_case0001()
    {
        String STR_METHOD_NAME = " testCreateQueryDataContainer_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_serviceImpl = new WEB3CCOperatorAccountListServiceImpl();

        long l_lngTradeId = 987654321L;
        String l_strAccountCode = null;
        String l_strFamilyName = null;
        String l_strFamilyNameAlt1 = null;

        Object[] l_lisExpected = {new Long(l_lngTradeId)};
        Object[] l_lisActual =
            l_serviceImpl.createQueryDataContainer(
                 l_lngTradeId,
                 l_strAccountCode,
                 l_strFamilyName,
                 l_strFamilyNameAlt1);

        assertEquals(l_lisExpected.length, l_lisActual.length);
        for (int i = 0; i<l_lisActual.length; i++)
        {
            assertEquals(l_lisExpected[i], l_lisActual[i]);
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     *
     */
    public void testCreateQueryDataContainer_case0002()
    {
        String STR_METHOD_NAME = " testCreateQueryDataContainer_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_serviceImpl = new WEB3CCOperatorAccountListServiceImpl();

        long l_lngTradeId = 987654321L;
        String l_strAccountCode = "7654321";
        String l_strFamilyName = "漢字";
        String l_strFamilyNameAlt1 = "カナ";

        Object[] l_lisExpected = {
            new Long(l_lngTradeId),
            l_strAccountCode,
            "%" + l_strFamilyName + "%",
            "%" + l_strFamilyNameAlt1 + "%"};
        Object[] l_lisActual =
            l_serviceImpl.createQueryDataContainer(
                 l_lngTradeId,
                 l_strAccountCode,
                 l_strFamilyName,
                 l_strFamilyNameAlt1);

        assertEquals(l_lisExpected.length, l_lisActual.length);
        for (int i = 0; i<l_lisActual.length; i++)
        {
            assertEquals(l_lisExpected[i], l_lisActual[i]);
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     *
     */
    public void testCreateSortCond_case0001()
    {
        String STR_METHOD_NAME = " testCreateSortCond_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_serviceImpl = new WEB3CCOperatorAccountListServiceImpl();

        WEB3TraderAccountInfosSortKey[] l_sortKeys = null;

        try
        {
            l_serviceImpl.createSortCond(l_sortKeys);
            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            log.debug(l_exBE.getMessage(), l_exBE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_exBE.getErrorInfo());
        }
        catch (Exception l_exE)
        {
            log.debug(l_exE.getMessage(), l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     *
     */
    public void testCreateSortCond_case0002()
    {
        String STR_METHOD_NAME = " testCreateSortCond_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_serviceImpl = new WEB3CCOperatorAccountListServiceImpl();

        WEB3TraderAccountInfosSortKey[] l_sortKeys = new WEB3TraderAccountInfosSortKey[1];
        WEB3TraderAccountInfosSortKey l_sortKey = new WEB3TraderAccountInfosSortKey();

        l_sortKey.keyItem = "acceptCode";
        l_sortKey.ascDesc = "A";
        l_sortKeys[0] = l_sortKey;

        String l_strExpected = "account_code ASC ";
        String l_strActual;
        try
        {
            l_strActual = l_serviceImpl.createSortCond(l_sortKeys);
            assertEquals(l_strExpected, l_strActual);
        }
        catch (WEB3BaseException l_exBE)
        {
            log.debug(l_exBE.getMessage(), l_exBE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_exE)
        {
            log.debug(l_exE.getMessage(), l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     *
     */
    public void testCreateSortCond_case0003()
    {
        String STR_METHOD_NAME = " testCreateSortCond_case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_serviceImpl = new WEB3CCOperatorAccountListServiceImpl();

        WEB3TraderAccountInfosSortKey[] l_sortKeys = new WEB3TraderAccountInfosSortKey[1];
        WEB3TraderAccountInfosSortKey l_sortKey = new WEB3TraderAccountInfosSortKey();

        l_sortKey.keyItem = "nameKana";
        l_sortKey.ascDesc = "D";
        l_sortKeys[0] = l_sortKey;

        String l_strExpected = "family_name_alt1 DESC ";
        String l_strActual;
        try
        {
            l_strActual = l_serviceImpl.createSortCond(l_sortKeys);
            assertEquals(l_strExpected, l_strActual);
        }
        catch (WEB3BaseException l_exBE)
        {
            log.debug(l_exBE.getMessage(), l_exBE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_exE)
        {
            log.debug(l_exE.getMessage(), l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     *
     */
    public void testCreateSortCond_case0004()
    {
        String STR_METHOD_NAME = " testCreateSortCond_case0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_serviceImpl = new WEB3CCOperatorAccountListServiceImpl();

        WEB3TraderAccountInfosSortKey[] l_sortKeys = new WEB3TraderAccountInfosSortKey[2];

        WEB3TraderAccountInfosSortKey l_sortKey = new WEB3TraderAccountInfosSortKey();
        l_sortKey.keyItem = "acceptCode";
        l_sortKey.ascDesc = "A";
        l_sortKeys[0] = l_sortKey;

        l_sortKey = new WEB3TraderAccountInfosSortKey();
        l_sortKey.keyItem = "nameKana";
        l_sortKey.ascDesc = "D";
        l_sortKeys[1] = l_sortKey;

        String l_strExpected = "account_code ASC , family_name_alt1 DESC ";
        String l_strActual;
        try
        {
            l_strActual = l_serviceImpl.createSortCond(l_sortKeys);
            assertEquals(l_strExpected, l_strActual);
        }
        catch (WEB3BaseException l_exBE)
        {
            log.debug(l_exBE.getMessage(), l_exBE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_exE)
        {
            log.debug(l_exE.getMessage(), l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     *
     */
    public void testCreateSortCond_case0005()
    {
        String STR_METHOD_NAME = " testCreateSortCond_case0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_serviceImpl = new WEB3CCOperatorAccountListServiceImpl();

        WEB3TraderAccountInfosSortKey[] l_sortKeys = new WEB3TraderAccountInfosSortKey[2];

        WEB3TraderAccountInfosSortKey l_sortKey = new WEB3TraderAccountInfosSortKey();
        l_sortKey.keyItem = "nameKana";
        l_sortKey.ascDesc = "D";
        l_sortKeys[0] = l_sortKey;

        l_sortKey = new WEB3TraderAccountInfosSortKey();
        l_sortKey.keyItem = "acceptCode";
        l_sortKey.ascDesc = "A";
        l_sortKeys[1] = l_sortKey;

        String l_strExpected = "family_name_alt1 DESC , account_code ASC ";
        String l_strActual;
        try
        {
            l_strActual = l_serviceImpl.createSortCond(l_sortKeys);
            assertEquals(l_strExpected, l_strActual);
        }
        catch (WEB3BaseException l_exBE)
        {
            log.debug(l_exBE.getMessage(), l_exBE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_exE)
        {
            log.debug(l_exE.getMessage(), l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     *
     */
    public void testCreateQueryString_case0001()
    {
        String STR_METHOD_NAME = " testCreateQueryString_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_serviceImpl = new WEB3CCOperatorAccountListServiceImpl();

        String l_strAccountCode = null;
        String l_strFamilyName = null;
        String l_strFamilyNameAlt1 = null;

        String l_strExpected = "trader_id = ? ";
        String l_strActual;
        l_strActual =
            l_serviceImpl.createQueryString(
                 l_strAccountCode,
                 l_strFamilyName,
                 l_strFamilyNameAlt1);

        assertEquals(l_strExpected, l_strActual);

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     *
     */
    public void testCreateQueryString_case0002()
    {
        String STR_METHOD_NAME = " testCreateQueryString_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_serviceImpl = new WEB3CCOperatorAccountListServiceImpl();

        String l_strAccountCode = "7654321";
        String l_strFamilyName = "漢字";
        String l_strFamilyNameAlt1 = "カナ";

        String l_strExpected =
            "trader_id = ? and account_code = ? and family_name like ? and family_name_alt1 like ? ";
        String l_strActual =
            l_serviceImpl.createQueryString(
                 l_strAccountCode,
                 l_strFamilyName,
                 l_strFamilyNameAlt1);

        assertEquals(l_strExpected, l_strActual);

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     *
     */
    public void testCreateQueryString_case0003()
    {
        String STR_METHOD_NAME = " testCreateQueryString_case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_serviceImpl = new WEB3CCOperatorAccountListServiceImpl();

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
            l_traderAccountInfoParams.setAccountId(2221234568L);
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

        try
        {
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

            String l_strTradeId = "1112345";

            String l_strCreateQueryString = l_serviceImpl.createQueryString(
                l_request.acceptCode,
                l_request.nameKanji,
                l_request.nameKana);

            Object[] l_createQueryDataContainers = l_serviceImpl.createQueryDataContainer(
                Long.parseLong(l_strTradeId),
                l_request.acceptCode,
                l_request.nameKanji,
                l_request.nameKana);

            String l_strCreateSortCond = l_serviceImpl.createSortCond(l_request.sortKeys);

            List l_lisResults = new ArrayList();

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisResults = l_queryProcessor.doFindAllQuery(
                TraderAccountInfoRow.TYPE,
                l_strCreateQueryString,
                l_strCreateSortCond,
                null,
                l_createQueryDataContainers);

            assertEquals(1, l_lisResults.size());

            TraderAccountInfoParams l_actualTraderAccountInfoParams =
                (TraderAccountInfoParams)l_lisResults.get(0);
            assertEquals(1112345L, l_actualTraderAccountInfoParams.getTraderId());
            assertEquals(2221234568L, l_actualTraderAccountInfoParams.getAccountId());
            assertEquals("0D", l_actualTraderAccountInfoParams.getInstitutionCode());
            assertEquals("381", l_actualTraderAccountInfoParams.getBranchCode());
            assertEquals("12345", l_actualTraderAccountInfoParams.getTraderCode());
            assertEquals("12", l_actualTraderAccountInfoParams.getDepartmentCode());
            assertEquals("123456", l_actualTraderAccountInfoParams.getAccountCode());
            assertEquals("亜衣于江緒", l_actualTraderAccountInfoParams.getFamilyName());
            assertEquals("あいうえお", l_actualTraderAccountInfoParams.getFamilyNameAlt1());
            assertEquals("phantom", l_actualTraderAccountInfoParams.getLastUpdater());
            assertEquals(
                WEB3DateUtility.getDate("20070724", "yyyyMMdd"),
                l_actualTraderAccountInfoParams.getCreatedTimestamp());
            assertEquals(
                WEB3DateUtility.getDate("20070724", "yyyyMMdd"),
                l_actualTraderAccountInfoParams.getLastUpdatedTimestamp());

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
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

    /**
     *
     */
    public void testCreateQueryString_case0004()
    {
        String STR_METHOD_NAME = " testCreateQueryString_case0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_serviceImpl = new WEB3CCOperatorAccountListServiceImpl();

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
            l_traderAccountInfoParams.setAccountId(2221234568L);
            l_traderAccountInfoParams.setInstitutionCode("0D");
            l_traderAccountInfoParams.setBranchCode("381");
            l_traderAccountInfoParams.setTraderCode("12345");
            l_traderAccountInfoParams.setDepartmentCode("12");
            l_traderAccountInfoParams.setAccountCode("123456");
            l_traderAccountInfoParams.setFamilyName("于");
            l_traderAccountInfoParams.setFamilyNameAlt1("は");
            l_traderAccountInfoParams.setLastUpdater("phantom");
            l_traderAccountInfoParams.setCreatedTimestamp(
                WEB3DateUtility.getDate("20070724", "yyyyMMdd"));
            l_traderAccountInfoParams.setLastUpdatedTimestamp(
                WEB3DateUtility.getDate("20070724", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_traderAccountInfoParams);

            l_traderAccountInfoParams = new TraderAccountInfoParams();
            l_traderAccountInfoParams.setTraderId(1112345L);
            l_traderAccountInfoParams.setAccountId(2221234569L);
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

            l_traderAccountInfoParams = new TraderAccountInfoParams();
            l_traderAccountInfoParams.setTraderId(1112345L);
            l_traderAccountInfoParams.setAccountId(2221234570L);
            l_traderAccountInfoParams.setInstitutionCode("0D");
            l_traderAccountInfoParams.setBranchCode("381");
            l_traderAccountInfoParams.setTraderCode("12345");
            l_traderAccountInfoParams.setDepartmentCode("12");
            l_traderAccountInfoParams.setAccountCode("123456");
            l_traderAccountInfoParams.setFamilyName("于江");
            l_traderAccountInfoParams.setFamilyNameAlt1("う");
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

        try
        {
            WEB3CCOperatorAccountListRequest l_request =
                new WEB3CCOperatorAccountListRequest();
            l_request.acceptCode = "123456";
            l_request.nameKanji = "于";
            l_request.nameKana = "う";
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

            String l_strTradeId = "1112345";

            String l_strCreateQueryString = l_serviceImpl.createQueryString(
                l_request.acceptCode,
                l_request.nameKanji,
                l_request.nameKana);

            Object[] l_createQueryDataContainers = l_serviceImpl.createQueryDataContainer(
                Long.parseLong(l_strTradeId),
                l_request.acceptCode,
                l_request.nameKanji,
                l_request.nameKana);

            String l_strCreateSortCond = l_serviceImpl.createSortCond(l_request.sortKeys);

            List l_lisResults = new ArrayList();

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisResults = l_queryProcessor.doFindAllQuery(
                TraderAccountInfoRow.TYPE,
                l_strCreateQueryString,
                l_strCreateSortCond,
                null,
                l_createQueryDataContainers);

            assertEquals(2, l_lisResults.size());

            TraderAccountInfoParams l_actualTraderAccountInfoParams =
                (TraderAccountInfoParams)l_lisResults.get(0);
            assertEquals(1112345L, l_actualTraderAccountInfoParams.getTraderId());
            assertEquals(2221234570L, l_actualTraderAccountInfoParams.getAccountId());
            assertEquals("0D", l_actualTraderAccountInfoParams.getInstitutionCode());
            assertEquals("381", l_actualTraderAccountInfoParams.getBranchCode());
            assertEquals("12345", l_actualTraderAccountInfoParams.getTraderCode());
            assertEquals("12", l_actualTraderAccountInfoParams.getDepartmentCode());
            assertEquals("123456", l_actualTraderAccountInfoParams.getAccountCode());
            assertEquals("于江", l_actualTraderAccountInfoParams.getFamilyName());
            assertEquals("う", l_actualTraderAccountInfoParams.getFamilyNameAlt1());
            assertEquals("phantom", l_actualTraderAccountInfoParams.getLastUpdater());
            assertEquals(
                WEB3DateUtility.getDate("20070724", "yyyyMMdd"),
                l_actualTraderAccountInfoParams.getCreatedTimestamp());
            assertEquals(
                WEB3DateUtility.getDate("20070724", "yyyyMMdd"),
                l_actualTraderAccountInfoParams.getLastUpdatedTimestamp());

            l_actualTraderAccountInfoParams = (TraderAccountInfoParams)l_lisResults.get(1);
            assertEquals(1112345L, l_actualTraderAccountInfoParams.getTraderId());
            assertEquals(2221234569L, l_actualTraderAccountInfoParams.getAccountId());
            assertEquals("0D", l_actualTraderAccountInfoParams.getInstitutionCode());
            assertEquals("381", l_actualTraderAccountInfoParams.getBranchCode());
            assertEquals("12345", l_actualTraderAccountInfoParams.getTraderCode());
            assertEquals("12", l_actualTraderAccountInfoParams.getDepartmentCode());
            assertEquals("123456", l_actualTraderAccountInfoParams.getAccountCode());
            assertEquals("亜衣于江緒", l_actualTraderAccountInfoParams.getFamilyName());
            assertEquals("あいうえお", l_actualTraderAccountInfoParams.getFamilyNameAlt1());
            assertEquals("phantom", l_actualTraderAccountInfoParams.getLastUpdater());
            assertEquals(
                WEB3DateUtility.getDate("20070724", "yyyyMMdd"),
                l_actualTraderAccountInfoParams.getCreatedTimestamp());
            assertEquals(
                WEB3DateUtility.getDate("20070724", "yyyyMMdd"),
                l_actualTraderAccountInfoParams.getLastUpdatedTimestamp());

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
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

    /**
     *
     */
    public void testCreateQueryString_case0005()
    {
        String STR_METHOD_NAME = " testCreateQueryString_case0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_serviceImpl = new WEB3CCOperatorAccountListServiceImpl();

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
            l_traderAccountInfoParams.setAccountCode("666456");
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
            l_traderAccountInfoParams.setAccountId(2221234568L);
            l_traderAccountInfoParams.setInstitutionCode("0D");
            l_traderAccountInfoParams.setBranchCode("381");
            l_traderAccountInfoParams.setTraderCode("12345");
            l_traderAccountInfoParams.setDepartmentCode("12");
            l_traderAccountInfoParams.setAccountCode("123456");
            l_traderAccountInfoParams.setFamilyName("于");
            l_traderAccountInfoParams.setFamilyNameAlt1("は");
            l_traderAccountInfoParams.setLastUpdater("phantom");
            l_traderAccountInfoParams.setCreatedTimestamp(
                WEB3DateUtility.getDate("20070724", "yyyyMMdd"));
            l_traderAccountInfoParams.setLastUpdatedTimestamp(
                WEB3DateUtility.getDate("20070724", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_traderAccountInfoParams);

            l_traderAccountInfoParams = new TraderAccountInfoParams();
            l_traderAccountInfoParams.setTraderId(1112345L);
            l_traderAccountInfoParams.setAccountId(2221234569L);
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

            l_traderAccountInfoParams = new TraderAccountInfoParams();
            l_traderAccountInfoParams.setTraderId(1112345L);
            l_traderAccountInfoParams.setAccountId(2221234590L);
            l_traderAccountInfoParams.setInstitutionCode("0D");
            l_traderAccountInfoParams.setBranchCode("381");
            l_traderAccountInfoParams.setTraderCode("12345");
            l_traderAccountInfoParams.setDepartmentCode("12");
            l_traderAccountInfoParams.setAccountCode("123456");
            l_traderAccountInfoParams.setFamilyName("于江");
            l_traderAccountInfoParams.setFamilyNameAlt1("う");
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

        try
        {
            WEB3CCOperatorAccountListRequest l_request =
                new WEB3CCOperatorAccountListRequest();
            l_request.acceptCode = null;
            l_request.nameKanji = null;
            l_request.nameKana = null;
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

            String l_strTradeId = "1112345";

            String l_strCreateQueryString = l_serviceImpl.createQueryString(
                l_request.acceptCode,
                l_request.nameKanji,
                l_request.nameKana);

            Object[] l_createQueryDataContainers = l_serviceImpl.createQueryDataContainer(
                Long.parseLong(l_strTradeId),
                l_request.acceptCode,
                l_request.nameKanji,
                l_request.nameKana);

            String l_strCreateSortCond = l_serviceImpl.createSortCond(l_request.sortKeys);

            List l_lisResults = new ArrayList();

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisResults = l_queryProcessor.doFindAllQuery(
                TraderAccountInfoRow.TYPE,
                l_strCreateQueryString,
                l_strCreateSortCond,
                null,
                l_createQueryDataContainers);

            assertEquals(4, l_lisResults.size());

            TraderAccountInfoParams l_actualTraderAccountInfoParams =
                (TraderAccountInfoParams)l_lisResults.get(0);
            assertEquals(1112345L, l_actualTraderAccountInfoParams.getTraderId());
            assertEquals(2221234568L, l_actualTraderAccountInfoParams.getAccountId());
            assertEquals("0D", l_actualTraderAccountInfoParams.getInstitutionCode());
            assertEquals("381", l_actualTraderAccountInfoParams.getBranchCode());
            assertEquals("12345", l_actualTraderAccountInfoParams.getTraderCode());
            assertEquals("12", l_actualTraderAccountInfoParams.getDepartmentCode());
            assertEquals("123456", l_actualTraderAccountInfoParams.getAccountCode());
            assertEquals("于", l_actualTraderAccountInfoParams.getFamilyName());
            assertEquals("は", l_actualTraderAccountInfoParams.getFamilyNameAlt1());
            assertEquals("phantom", l_actualTraderAccountInfoParams.getLastUpdater());
            assertEquals(
                WEB3DateUtility.getDate("20070724", "yyyyMMdd"),
                l_actualTraderAccountInfoParams.getCreatedTimestamp());
            assertEquals(
                WEB3DateUtility.getDate("20070724", "yyyyMMdd"),
                l_actualTraderAccountInfoParams.getLastUpdatedTimestamp());

            l_actualTraderAccountInfoParams =
                (TraderAccountInfoParams)l_lisResults.get(1);
            assertEquals(1112345L, l_actualTraderAccountInfoParams.getTraderId());
            assertEquals(2221234590L, l_actualTraderAccountInfoParams.getAccountId());
            assertEquals("0D", l_actualTraderAccountInfoParams.getInstitutionCode());
            assertEquals("381", l_actualTraderAccountInfoParams.getBranchCode());
            assertEquals("12345", l_actualTraderAccountInfoParams.getTraderCode());
            assertEquals("12", l_actualTraderAccountInfoParams.getDepartmentCode());
            assertEquals("123456", l_actualTraderAccountInfoParams.getAccountCode());
            assertEquals("于江", l_actualTraderAccountInfoParams.getFamilyName());
            assertEquals("う", l_actualTraderAccountInfoParams.getFamilyNameAlt1());
            assertEquals("phantom", l_actualTraderAccountInfoParams.getLastUpdater());
            assertEquals(
                WEB3DateUtility.getDate("20070724", "yyyyMMdd"),
                l_actualTraderAccountInfoParams.getCreatedTimestamp());
            assertEquals(
                WEB3DateUtility.getDate("20070724", "yyyyMMdd"),
                l_actualTraderAccountInfoParams.getLastUpdatedTimestamp());

            l_actualTraderAccountInfoParams = (TraderAccountInfoParams)l_lisResults.get(2);
            assertEquals(1112345L, l_actualTraderAccountInfoParams.getTraderId());
            assertEquals(2221234569L, l_actualTraderAccountInfoParams.getAccountId());
            assertEquals("0D", l_actualTraderAccountInfoParams.getInstitutionCode());
            assertEquals("381", l_actualTraderAccountInfoParams.getBranchCode());
            assertEquals("12345", l_actualTraderAccountInfoParams.getTraderCode());
            assertEquals("12", l_actualTraderAccountInfoParams.getDepartmentCode());
            assertEquals("123456", l_actualTraderAccountInfoParams.getAccountCode());
            assertEquals("亜衣于江緒", l_actualTraderAccountInfoParams.getFamilyName());
            assertEquals("あいうえお", l_actualTraderAccountInfoParams.getFamilyNameAlt1());
            assertEquals("phantom", l_actualTraderAccountInfoParams.getLastUpdater());
            assertEquals(
                WEB3DateUtility.getDate("20070724", "yyyyMMdd"),
                l_actualTraderAccountInfoParams.getCreatedTimestamp());
            assertEquals(
                WEB3DateUtility.getDate("20070724", "yyyyMMdd"),
                l_actualTraderAccountInfoParams.getLastUpdatedTimestamp());

            l_actualTraderAccountInfoParams = (TraderAccountInfoParams)l_lisResults.get(3);
            assertEquals(1112345L, l_actualTraderAccountInfoParams.getTraderId());
            assertEquals(2221234567L, l_actualTraderAccountInfoParams.getAccountId());
            assertEquals("0D", l_actualTraderAccountInfoParams.getInstitutionCode());
            assertEquals("381", l_actualTraderAccountInfoParams.getBranchCode());
            assertEquals("12345", l_actualTraderAccountInfoParams.getTraderCode());
            assertEquals("12", l_actualTraderAccountInfoParams.getDepartmentCode());
            assertEquals("666456", l_actualTraderAccountInfoParams.getAccountCode());
            assertEquals("何毅来家居", l_actualTraderAccountInfoParams.getFamilyName());
            assertEquals("かきくけこ", l_actualTraderAccountInfoParams.getFamilyNameAlt1());
            assertEquals("phantom", l_actualTraderAccountInfoParams.getLastUpdater());
            assertEquals(
                WEB3DateUtility.getDate("20070724", "yyyyMMdd"),
                l_actualTraderAccountInfoParams.getCreatedTimestamp());
            assertEquals(
                WEB3DateUtility.getDate("20070724", "yyyyMMdd"),
                l_actualTraderAccountInfoParams.getLastUpdatedTimestamp());

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
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

    /**
     *
     */
    public void testGetAccountList_case0001()
    {
        String STR_METHOD_NAME = " testGetAccountList_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_serviceImpl = new WEB3CCOperatorAccountListServiceImpl();

        WEB3CCOperatorAccountListRequest l_request =
            new WEB3CCOperatorAccountListRequest();

        l_request.acceptCode = null;
        l_request.nameKanji = null;
        l_request.nameKana = null;
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
                null);

            l_serviceImpl.getAccountList(l_request);

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     *
     */
    public void testGetAccountList_case0002()
    {
        String STR_METHOD_NAME = " testGetAccountList_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_serviceImpl = new WEB3CCOperatorAccountListServiceImpl();

        WEB3CCOperatorAccountListRequest l_request =
            new WEB3CCOperatorAccountListRequest();

        l_request.acceptCode = null;
        l_request.nameKanji = null;
        l_request.nameKana = null;
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
                "123");

            WEB3CCOperatorAccountListResponse l_response;
            l_response = l_serviceImpl.getAccountList(l_request);

            assertNull(l_response.traderAccoutInfos);
            assertEquals("0", l_response.totalPages);
            assertEquals("0", l_response.totalRecords);
            assertEquals("0", l_response.pageIndex);

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     *
     */
    public void testGetAccountList_case0003()
    {
        String STR_METHOD_NAME = " testGetAccountList_case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

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

        l_serviceImpl = new WEB3CCOperatorAccountListServiceImpl();

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

            WEB3CCOperatorAccountListResponse l_response;
            l_response = l_serviceImpl.getAccountList(l_request);

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
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
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

    /**
     *
     */
    public void testGetAccountList_case0004()
    {
        String STR_METHOD_NAME = " testGetAccountList_case0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

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
            l_traderAccountInfoParams.setAccountId(2221234568L);
            l_traderAccountInfoParams.setInstitutionCode("0D");
            l_traderAccountInfoParams.setBranchCode("381");
            l_traderAccountInfoParams.setTraderCode("12345");
            l_traderAccountInfoParams.setDepartmentCode("12");
            l_traderAccountInfoParams.setAccountCode("123456");
            l_traderAccountInfoParams.setFamilyName("于");
            l_traderAccountInfoParams.setFamilyNameAlt1("は");
            l_traderAccountInfoParams.setLastUpdater("phantom");
            l_traderAccountInfoParams.setCreatedTimestamp(
                WEB3DateUtility.getDate("20070724", "yyyyMMdd"));
            l_traderAccountInfoParams.setLastUpdatedTimestamp(
                WEB3DateUtility.getDate("20070724", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_traderAccountInfoParams);

            l_traderAccountInfoParams = new TraderAccountInfoParams();
            l_traderAccountInfoParams.setTraderId(1112345L);
            l_traderAccountInfoParams.setAccountId(2221234569L);
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

            l_traderAccountInfoParams = new TraderAccountInfoParams();
            l_traderAccountInfoParams.setTraderId(1112345L);
            l_traderAccountInfoParams.setAccountId(2221234570L);
            l_traderAccountInfoParams.setInstitutionCode("0D");
            l_traderAccountInfoParams.setBranchCode("381");
            l_traderAccountInfoParams.setTraderCode("12345");
            l_traderAccountInfoParams.setDepartmentCode("12");
            l_traderAccountInfoParams.setAccountCode("123456");
            l_traderAccountInfoParams.setFamilyName("于江");
            l_traderAccountInfoParams.setFamilyNameAlt1("う");
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

        l_serviceImpl = new WEB3CCOperatorAccountListServiceImpl();

        WEB3CCOperatorAccountListRequest l_request =
            new WEB3CCOperatorAccountListRequest();

        l_request.acceptCode = "123456";
        l_request.nameKanji = "于";
        l_request.nameKana = "う";
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

            WEB3CCOperatorAccountListResponse l_response;
            l_response = l_serviceImpl.getAccountList(l_request);

            WEB3TraderAccountInfo[] l_expectedTraderAccountInfos = new WEB3TraderAccountInfo[2];

            WEB3TraderAccountInfo l_expectedTraderAccountInfo = new WEB3TraderAccountInfo();
            l_expectedTraderAccountInfo.branchCode = "381";
            l_expectedTraderAccountInfo.acceptCode = "123456";
            l_expectedTraderAccountInfo.nameKanji = "于江";
            l_expectedTraderAccountInfo.nameKana = "う";
            l_expectedTraderAccountInfo.accountID = 2221234570L;
            l_expectedTraderAccountInfos[0] = l_expectedTraderAccountInfo;

            l_expectedTraderAccountInfo = new WEB3TraderAccountInfo();
            l_expectedTraderAccountInfo.branchCode = "381";
            l_expectedTraderAccountInfo.acceptCode = "123456";
            l_expectedTraderAccountInfo.nameKanji = "亜衣于江緒";
            l_expectedTraderAccountInfo.nameKana = "あいうえお";
            l_expectedTraderAccountInfo.accountID = 2221234569L;
            l_expectedTraderAccountInfos[1] = l_expectedTraderAccountInfo;

            WEB3TraderAccountInfo[] l_actualTraderAccountInfos = l_response.traderAccoutInfos;

            assertEquals("1", l_response.totalPages);
            assertEquals("2", l_response.totalRecords);
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
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
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

    /**
     *
     */
    public void testGetAccountList_case0005()
    {
        String STR_METHOD_NAME = " testGetAccountList_case0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

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
            l_traderAccountInfoParams.setAccountId(2221234568L);
            l_traderAccountInfoParams.setInstitutionCode("0D");
            l_traderAccountInfoParams.setBranchCode("381");
            l_traderAccountInfoParams.setTraderCode("12345");
            l_traderAccountInfoParams.setDepartmentCode("12");
            l_traderAccountInfoParams.setAccountCode("123456");
            l_traderAccountInfoParams.setFamilyName("于");
            l_traderAccountInfoParams.setFamilyNameAlt1("は");
            l_traderAccountInfoParams.setLastUpdater("phantom");
            l_traderAccountInfoParams.setCreatedTimestamp(
                WEB3DateUtility.getDate("20070724", "yyyyMMdd"));
            l_traderAccountInfoParams.setLastUpdatedTimestamp(
                WEB3DateUtility.getDate("20070724", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_traderAccountInfoParams);

            l_traderAccountInfoParams = new TraderAccountInfoParams();
            l_traderAccountInfoParams.setTraderId(1112345L);
            l_traderAccountInfoParams.setAccountId(2221234569L);
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

            l_traderAccountInfoParams = new TraderAccountInfoParams();
            l_traderAccountInfoParams.setTraderId(1112345L);
            l_traderAccountInfoParams.setAccountId(2221234570L);
            l_traderAccountInfoParams.setInstitutionCode("0D");
            l_traderAccountInfoParams.setBranchCode("381");
            l_traderAccountInfoParams.setTraderCode("12345");
            l_traderAccountInfoParams.setDepartmentCode("12");
            l_traderAccountInfoParams.setAccountCode("123456");
            l_traderAccountInfoParams.setFamilyName("于江");
            l_traderAccountInfoParams.setFamilyNameAlt1("う");
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

        l_serviceImpl = new WEB3CCOperatorAccountListServiceImpl();

        WEB3CCOperatorAccountListRequest l_request =
            new WEB3CCOperatorAccountListRequest();

        l_request.acceptCode = null;
        l_request.nameKanji = null;
        l_request.nameKana = null;
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

            WEB3CCOperatorAccountListResponse l_response;
            l_response = l_serviceImpl.getAccountList(l_request);

            WEB3TraderAccountInfo[] l_expectedTraderAccountInfos = new WEB3TraderAccountInfo[4];

            WEB3TraderAccountInfo l_expectedTraderAccountInfo = new WEB3TraderAccountInfo();
            l_expectedTraderAccountInfo.branchCode = "381";
            l_expectedTraderAccountInfo.acceptCode = "123456";
            l_expectedTraderAccountInfo.nameKanji = "于";
            l_expectedTraderAccountInfo.nameKana = "は";
            l_expectedTraderAccountInfo.accountID = 2221234568L;
            l_expectedTraderAccountInfos[0] = l_expectedTraderAccountInfo;

            l_expectedTraderAccountInfo = new WEB3TraderAccountInfo();
            l_expectedTraderAccountInfo.branchCode = "381";
            l_expectedTraderAccountInfo.acceptCode = "123456";
            l_expectedTraderAccountInfo.nameKanji = "何毅来家居";
            l_expectedTraderAccountInfo.nameKana = "かきくけこ";
            l_expectedTraderAccountInfo.accountID = 2221234567L;
            l_expectedTraderAccountInfos[1] = l_expectedTraderAccountInfo;

            l_expectedTraderAccountInfo = new WEB3TraderAccountInfo();
            l_expectedTraderAccountInfo.branchCode = "381";
            l_expectedTraderAccountInfo.acceptCode = "123456";
            l_expectedTraderAccountInfo.nameKanji = "于江";
            l_expectedTraderAccountInfo.nameKana = "う";
            l_expectedTraderAccountInfo.accountID = 2221234570L;
            l_expectedTraderAccountInfos[2] = l_expectedTraderAccountInfo;

            l_expectedTraderAccountInfo = new WEB3TraderAccountInfo();
            l_expectedTraderAccountInfo.branchCode = "381";
            l_expectedTraderAccountInfo.acceptCode = "123456";
            l_expectedTraderAccountInfo.nameKanji = "亜衣于江緒";
            l_expectedTraderAccountInfo.nameKana = "あいうえお";
            l_expectedTraderAccountInfo.accountID = 2221234569L;
            l_expectedTraderAccountInfos[3] = l_expectedTraderAccountInfo;

            WEB3TraderAccountInfo[] l_actualTraderAccountInfos = l_response.traderAccoutInfos;

            assertEquals("1", l_response.totalPages);
            assertEquals("4", l_response.totalRecords);
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
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
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

    /**
     *
     */
    public void testExecute_case0001()
    {
        String STR_METHOD_NAME = " testExecute_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);  

        l_service = (WEB3CCOperatorAccountListService)Services.getService(
            WEB3CCOperatorAccountListService.class);

        WEB3CCOperatorAccountListRequest l_request = null;

        try
        {
            l_service.execute(l_request);

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
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

        l_service = (WEB3CCOperatorAccountListService)Services.getService(
            WEB3CCOperatorAccountListService.class);

        WEB3CCOperatorLoginRequest l_ccOperatorLoginRequest =
            new WEB3CCOperatorLoginRequest();

        try
        {
            l_service.execute(l_ccOperatorLoginRequest);

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
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

        l_service = (WEB3CCOperatorAccountListService)Services.getService(
            WEB3CCOperatorAccountListService.class);

        WEB3CCOperatorAccountListRequest l_request =
            new WEB3CCOperatorAccountListRequest();

        l_request.sortKeys = null;

        try
        {
            l_service.execute(l_request);

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00231, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     *
     */
    public void testExecute_case0004()
    {
        String STR_METHOD_NAME = " testExecute_case0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_service = (WEB3CCOperatorAccountListService)Services.getService(
            WEB3CCOperatorAccountListService.class);

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

            l_response = (WEB3CCOperatorAccountListResponse)l_service.execute(l_request);

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
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
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
