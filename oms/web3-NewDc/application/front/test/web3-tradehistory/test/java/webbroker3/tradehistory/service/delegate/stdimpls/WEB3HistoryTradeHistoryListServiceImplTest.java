head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.39.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3HistoryTradeHistoryListServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.tradehistory.service.delegate.stdimpls;

import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import test.util.TestDBUtility;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradehistory.data.TradeHistoryParams;
import webbroker3.tradehistory.message.WEB3HistorySortKeyUnit;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 *
 * @@author zhou-moyang
 *
 */
public class WEB3HistoryTradeHistoryListServiceImplTest extends TestBaseForMock
{

    /**
     * WEB3HistoryTradeHistoryListServiceImplTest
     * @@param arg0
     */
    public WEB3HistoryTradeHistoryListServiceImplTest(String arg0)
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
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
                WEB3HistoryTradeHistoryListServiceImplTest.class);

    /**
     *
     */
    public void testCreateQueryString_0001()
    {
        String STR_METHOD_NAME = " testCreateQueryString_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        Date l_datListStartDate = null;
        Date l_datListEndDate = null;
        String l_strProductCode = null;
        String l_strProductType = "H";

        WEB3HistoryTradeHistoryListServiceImpl l_historyTradeHistoryListServiceImpl =
            new WEB3HistoryTradeHistoryListServiceImpl();

        String l_strActual = null;
        l_strActual = l_historyTradeHistoryListServiceImpl.createQueryString(l_datListStartDate,
            l_datListEndDate,
            l_strProductCode,
            l_strProductType);

        StringBuffer l_sbExpected = new StringBuffer();
        l_sbExpected.append("institution_code = ? ");
        l_sbExpected.append("and branch_code = ? ");
        l_sbExpected.append("and account_code = ? ");
        l_sbExpected.append("and (commodity_code IN (?, ?, ?, ?) ");
        l_sbExpected.append("or (commodity_code =? and remark_code in (?, ?) and trade_code= ?)) ");

        String l_strExpected = l_sbExpected.toString();

        assertEquals(l_strExpected, l_strActual);

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     *
     */
    public void testCreateQueryDataContainer_0001()
    {
        String STR_METHOD_NAME = " testCreateQueryDataContainer_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAllAndCommit(MainAccountRow.TYPE);
            TestDBUtility.deleteAllAndCommit(InstitutionRow.TYPE);
            TestDBUtility.deleteAllAndCommit(BranchRow.TYPE);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        try
        {
            TestDBUtility.insertWithDel(TestDBUtility.getMainAccountRow());
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        try
        {
            long l_lngInstId = 33L;
            String l_strBranchCode = "381";
            String l_strAccountCode = "2512246";
            WEB3GentradeMainAccount l_mainAccount;

            l_mainAccount = new WEB3GentradeMainAccount(l_lngInstId, l_strBranchCode, l_strAccountCode);

            WEB3HistoryTradeHistoryListServiceImpl l_historyTradeHistoryListServiceImpl =
                new WEB3HistoryTradeHistoryListServiceImpl();

            Date l_datListStartDate = null;
            Date l_datListEndDate = null;
            String l_strProductCode = null;
            String l_strProductType = "H";

            String[] l_strActualList = null;
            l_strActualList = l_historyTradeHistoryListServiceImpl.createQueryDataContainer(l_mainAccount,
                l_datListStartDate,
                l_datListEndDate,
                l_strProductCode,
                l_strProductType);

            String[] l_strExpected = {"0D", "381", "251224", "20", "21", "22", "23", "00", "D102", "D108", "A3"};

            assertNotNull(l_strActualList);
            assertEquals(l_strExpected.length, l_strActualList.length);
            for (int i = 0; i < l_strActualList.length; i++)
            {
                assertEquals(l_strExpected[i], l_strActualList[i]);
            }

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(MainAccountRow.TYPE);
                TestDBUtility.deleteAll(InstitutionRow.TYPE);
                TestDBUtility.deleteAll(BranchRow.TYPE);
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
    public void testCreateQueryDataContainer_0002()
    {
        String STR_METHOD_NAME = " testCreateQueryDataContainer_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAllAndCommit(MainAccountRow.TYPE);
            TestDBUtility.deleteAllAndCommit(InstitutionRow.TYPE);
            TestDBUtility.deleteAllAndCommit(BranchRow.TYPE);
            TestDBUtility.deleteAllAndCommit(TradeHistoryParams.TYPE);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        try
        {
            TestDBUtility.insertWithDelAndCommit(TestDBUtility.getMainAccountRow());
            TestDBUtility.insertWithDelAndCommit(TestDBUtility.getInstitutionRow());
            TestDBUtility.insertWithDelAndCommit(TestDBUtility.getBranchRow());

            java.util.Date l_date = new java.util.Date();

            l_date = WEB3DateUtility.getDate("20070612", "yyyyMMdd");
            l_date = WEB3DateUtility.addHour(l_date, 10);
            l_date = WEB3DateUtility.addMinute(l_date, 5);
            l_date = WEB3DateUtility.addSecond(l_date, 8);

            TradeHistoryParams l_tradeHistoryParams = new TradeHistoryParams();

            l_tradeHistoryParams.setTradeHistoryId(1L);
            l_tradeHistoryParams.setInstitutionCode("0D");
            l_tradeHistoryParams.setBranchCode("381");
            l_tradeHistoryParams.setAccountCode("251224");
            l_tradeHistoryParams.setDeliveryDate(l_date);
            l_tradeHistoryParams.setCommodityCode("20");
            l_tradeHistoryParams.setRemarkCode("");
            l_tradeHistoryParams.setTradeCode("");
            l_tradeHistoryParams.setCreatedTimestamp(l_date);
            l_tradeHistoryParams.setLastUpdatedTimestamp(l_date);


            l_date = WEB3DateUtility.getDate("20070613", "yyyyMMdd");
            l_date = WEB3DateUtility.addHour(l_date, 10);
            l_date = WEB3DateUtility.addMinute(l_date, 5);
            l_date = WEB3DateUtility.addSecond(l_date, 8);

            TestDBUtility.insertWithDelAndCommit(l_tradeHistoryParams);

            l_tradeHistoryParams.setTradeHistoryId(2L);
            l_tradeHistoryParams.setInstitutionCode("0D");
            l_tradeHistoryParams.setBranchCode("381");
            l_tradeHistoryParams.setAccountCode("251224");
            l_tradeHistoryParams.setDeliveryDate(l_date);
            l_tradeHistoryParams.setCommodityCode("21");
            l_tradeHistoryParams.setRemarkCode("");
            l_tradeHistoryParams.setTradeCode("");
            l_tradeHistoryParams.setCreatedTimestamp(l_date);
            l_tradeHistoryParams.setLastUpdatedTimestamp(l_date);

            l_date = WEB3DateUtility.getDate("20070614", "yyyyMMdd");
            l_date = WEB3DateUtility.addHour(l_date, 10);
            l_date = WEB3DateUtility.addMinute(l_date, 5);
            l_date = WEB3DateUtility.addSecond(l_date, 8);

            TestDBUtility.insertWithDelAndCommit(l_tradeHistoryParams);

            l_tradeHistoryParams.setTradeHistoryId(3L);
            l_tradeHistoryParams.setInstitutionCode("0D");
            l_tradeHistoryParams.setBranchCode("381");
            l_tradeHistoryParams.setAccountCode("251224");
            l_tradeHistoryParams.setDeliveryDate(l_date);
            l_tradeHistoryParams.setCommodityCode("22");
            l_tradeHistoryParams.setRemarkCode("");
            l_tradeHistoryParams.setTradeCode("");
            l_tradeHistoryParams.setCreatedTimestamp(l_date);
            l_tradeHistoryParams.setLastUpdatedTimestamp(l_date);

            l_date = WEB3DateUtility.getDate("20070615", "yyyyMMdd");
            l_date = WEB3DateUtility.addHour(l_date, 10);
            l_date = WEB3DateUtility.addMinute(l_date, 5);
            l_date = WEB3DateUtility.addSecond(l_date, 8);

            TestDBUtility.insertWithDelAndCommit(l_tradeHistoryParams);

            l_tradeHistoryParams.setTradeHistoryId(4L);
            l_tradeHistoryParams.setInstitutionCode("0D");
            l_tradeHistoryParams.setBranchCode("381");
            l_tradeHistoryParams.setAccountCode("251224");
            l_tradeHistoryParams.setDeliveryDate(l_date);
            l_tradeHistoryParams.setCommodityCode("23");
            l_tradeHistoryParams.setRemarkCode("");
            l_tradeHistoryParams.setTradeCode("");
            l_tradeHistoryParams.setCreatedTimestamp(l_date);
            l_tradeHistoryParams.setLastUpdatedTimestamp(l_date);

            l_date = WEB3DateUtility.getDate("20070616", "yyyyMMdd");
            l_date = WEB3DateUtility.addHour(l_date, 10);
            l_date = WEB3DateUtility.addMinute(l_date, 5);
            l_date = WEB3DateUtility.addSecond(l_date, 8);

            TestDBUtility.insertWithDelAndCommit(l_tradeHistoryParams);

            l_tradeHistoryParams.setTradeHistoryId(5L);
            l_tradeHistoryParams.setInstitutionCode("0D");
            l_tradeHistoryParams.setBranchCode("381");
            l_tradeHistoryParams.setAccountCode("251224");
            l_tradeHistoryParams.setDeliveryDate(l_date);
            l_tradeHistoryParams.setCommodityCode("00");
            l_tradeHistoryParams.setRemarkCode("D102");
            l_tradeHistoryParams.setTradeCode("A3");
            l_tradeHistoryParams.setCreatedTimestamp(l_date);
            l_tradeHistoryParams.setLastUpdatedTimestamp(l_date);

            l_date = WEB3DateUtility.getDate("20070617", "yyyyMMdd");
            l_date = WEB3DateUtility.addHour(l_date, 10);
            l_date = WEB3DateUtility.addMinute(l_date, 5);
            l_date = WEB3DateUtility.addSecond(l_date, 8);

            TestDBUtility.insertWithDelAndCommit(l_tradeHistoryParams);

            l_tradeHistoryParams.setTradeHistoryId(6L);
            l_tradeHistoryParams.setInstitutionCode("0D");
            l_tradeHistoryParams.setBranchCode("381");
            l_tradeHistoryParams.setAccountCode("251224");
            l_tradeHistoryParams.setDeliveryDate(l_date);
            l_tradeHistoryParams.setCommodityCode("00");
            l_tradeHistoryParams.setRemarkCode("D108");
            l_tradeHistoryParams.setTradeCode("A3");
            l_tradeHistoryParams.setCreatedTimestamp(l_date);
            l_tradeHistoryParams.setLastUpdatedTimestamp(l_date);

            TestDBUtility.insertWithDelAndCommit(l_tradeHistoryParams);

        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        try
        {
            WEB3GentradeMainAccount l_mainAccount;

            l_mainAccount = new WEB3GentradeMainAccount(TestDBUtility.getMainAccountRow());

            WEB3HistoryTradeHistoryListServiceImpl l_historyTradeHistoryListServiceImpl =
                new WEB3HistoryTradeHistoryListServiceImpl();

            Date l_datListStartDate = null;
            Date l_datListEndDate = null;
            String l_strProductCode = null;
            String l_strProductType = "H";

            String l_strQueryString = null;
            l_strQueryString = l_historyTradeHistoryListServiceImpl.createQueryString(l_datListStartDate,
                l_datListEndDate,
                l_strProductCode,
                l_strProductType);

            String[] l_strQueryDataContainers = null;
            l_strQueryDataContainers = l_historyTradeHistoryListServiceImpl.createQueryDataContainer(l_mainAccount,
                l_datListStartDate,
                l_datListEndDate,
                l_strProductCode,
                l_strProductType);

            WEB3HistorySortKeyUnit l_sortKey1 = new WEB3HistorySortKeyUnit();
            l_sortKey1.keyItem = "deliveryDate";
            l_sortKey1.ascDesc = "A";

            WEB3HistorySortKeyUnit l_sortKey2 = new WEB3HistorySortKeyUnit();
            l_sortKey2.keyItem = "execDate";
            l_sortKey2.ascDesc = "A";

            WEB3HistorySortKeyUnit[] l_sortKeys = {l_sortKey1, l_sortKey2};

            String l_strSortCond = null;
            l_strSortCond = l_historyTradeHistoryListServiceImpl.createSortCond(l_sortKeys);

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            List l_lisTradeHistoryParams = null;
            l_lisTradeHistoryParams = l_queryProcessor.doFindAllQuery(TradeHistoryParams.TYPE,
                l_strQueryString,
                l_strSortCond,
                null,
                l_strQueryDataContainers);

            assertEquals(6, l_lisTradeHistoryParams.size());

            TradeHistoryParams l_expectedTradeHistoryParams = new TradeHistoryParams();

            java.util.Date l_date = new java.util.Date();

            l_date = WEB3DateUtility.getDate("20070616", "yyyyMMdd");
            l_date = WEB3DateUtility.addHour(l_date, 10);
            l_date = WEB3DateUtility.addMinute(l_date, 5);
            l_date = WEB3DateUtility.addSecond(l_date, 8);

            l_expectedTradeHistoryParams = (TradeHistoryParams)l_lisTradeHistoryParams.get(4);

            assertEquals(5, l_expectedTradeHistoryParams.getTradeHistoryId());
            assertEquals("0D", l_expectedTradeHistoryParams.getInstitutionCode());
            assertEquals("381", l_expectedTradeHistoryParams.getBranchCode());
            assertEquals("251224", l_expectedTradeHistoryParams.getAccountCode());
            assertEquals(l_date, l_expectedTradeHistoryParams.getDeliveryDate());
            assertEquals("00", l_expectedTradeHistoryParams.getCommodityCode());
            assertEquals("D102", l_expectedTradeHistoryParams.getRemarkCode());
            assertEquals("A3", l_expectedTradeHistoryParams.getTradeCode());
            assertEquals(l_date, l_expectedTradeHistoryParams.getCreatedTimestamp());
            assertEquals(l_date, l_expectedTradeHistoryParams.getLastUpdatedTimestamp());

            l_date = WEB3DateUtility.getDate("20070617", "yyyyMMdd");
            l_date = WEB3DateUtility.addHour(l_date, 10);
            l_date = WEB3DateUtility.addMinute(l_date, 5);
            l_date = WEB3DateUtility.addSecond(l_date, 8);

            l_expectedTradeHistoryParams = (TradeHistoryParams)l_lisTradeHistoryParams.get(5);

            assertEquals(6, l_expectedTradeHistoryParams.getTradeHistoryId());
            assertEquals("0D", l_expectedTradeHistoryParams.getInstitutionCode());
            assertEquals("381", l_expectedTradeHistoryParams.getBranchCode());
            assertEquals("251224", l_expectedTradeHistoryParams.getAccountCode());
            assertEquals(l_date, l_expectedTradeHistoryParams.getDeliveryDate());
            assertEquals("00", l_expectedTradeHistoryParams.getCommodityCode());
            assertEquals("D108", l_expectedTradeHistoryParams.getRemarkCode());
            assertEquals("A3", l_expectedTradeHistoryParams.getTradeCode());
            assertEquals(l_date, l_expectedTradeHistoryParams.getCreatedTimestamp());
            assertEquals(l_date, l_expectedTradeHistoryParams.getLastUpdatedTimestamp());

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(MainAccountRow.TYPE);
                TestDBUtility.deleteAll(InstitutionRow.TYPE);
                TestDBUtility.deleteAll(BranchRow.TYPE);
                TestDBUtility.deleteAllAndCommit(TradeHistoryParams.TYPE);
            }
            catch (Exception l_ex)
            {
                log.error(TEST_END + STR_METHOD_NAME);
                log.exiting(TEST_END + STR_METHOD_NAME);

                fail();
            }
        }
    }
    
    public void testCreateQueryString_0002()
    {
        String STR_METHOD_NAME = " testCreateQueryString_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        Date l_datListStartDate = null;
        Date l_datListEndDate = null;
        String l_strProductCode = null;
        
        //[”M:配当（現物）”の場合]
        String l_strProductType = "M";

        WEB3HistoryTradeHistoryListServiceImpl l_historyTradeHistoryListServiceImpl =
            new WEB3HistoryTradeHistoryListServiceImpl();

        String l_strActual = null;
        l_strActual = l_historyTradeHistoryListServiceImpl.createQueryString(l_datListStartDate,
            l_datListEndDate,
            l_strProductCode,
            l_strProductType);

        StringBuffer l_sbExpected = new StringBuffer();
        l_sbExpected.append("institution_code = ? and branch_code = ? and account_code = ? ");
        l_sbExpected.append("and remark_code = ? ");

        String l_strExpected = l_sbExpected.toString();

        assertEquals(l_strExpected, l_strActual);

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCreateQueryString_0003()
    {
        String STR_METHOD_NAME = " testCreateQueryString_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        Date l_datListStartDate = null;
        Date l_datListEndDate = null;
        String l_strProductCode = null;
        
        //[”N:配当（信用）”の場合]
        String l_strProductType = "N";

        WEB3HistoryTradeHistoryListServiceImpl l_historyTradeHistoryListServiceImpl =
            new WEB3HistoryTradeHistoryListServiceImpl();

        String l_strActual = null;
        l_strActual = l_historyTradeHistoryListServiceImpl.createQueryString(l_datListStartDate,
            l_datListEndDate,
            l_strProductCode,
            l_strProductType);

        StringBuffer l_sbExpected = new StringBuffer();
        l_sbExpected.append("institution_code = ? and branch_code = ? and account_code = ? ");
        l_sbExpected.append("and remark_code IN (?, ?) ");

        String l_strExpected = l_sbExpected.toString();

        assertEquals(l_strExpected, l_strActual);

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCreateQueryString_0004()
    {
        String STR_METHOD_NAME = " testCreateQueryString_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        Date l_datListStartDate = null;
        Date l_datListEndDate = null;
        String l_strProductCode = null;
        
        //[”O:配当（信用買）”の場合]
        String l_strProductType = "O";

        WEB3HistoryTradeHistoryListServiceImpl l_historyTradeHistoryListServiceImpl =
            new WEB3HistoryTradeHistoryListServiceImpl();

        String l_strActual = null;
        l_strActual = l_historyTradeHistoryListServiceImpl.createQueryString(l_datListStartDate,
            l_datListEndDate,
            l_strProductCode,
            l_strProductType);

        StringBuffer l_sbExpected = new StringBuffer();
        l_sbExpected.append("institution_code = ? and branch_code = ? and account_code = ? ");
        l_sbExpected.append("and remark_code = ? ");

        String l_strExpected = l_sbExpected.toString();

        assertEquals(l_strExpected, l_strActual);

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCreateQueryString_0005()
    {
        String STR_METHOD_NAME = " testCreateQueryString_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        Date l_datListStartDate = null;
        Date l_datListEndDate = null;
        String l_strProductCode = null;
        
        //[”P:配当（信用売）”の場合]
        String l_strProductType = "P";

        WEB3HistoryTradeHistoryListServiceImpl l_historyTradeHistoryListServiceImpl =
            new WEB3HistoryTradeHistoryListServiceImpl();

        String l_strActual = null;
        l_strActual = l_historyTradeHistoryListServiceImpl.createQueryString(l_datListStartDate,
            l_datListEndDate,
            l_strProductCode,
            l_strProductType);

        StringBuffer l_sbExpected = new StringBuffer();
        l_sbExpected.append("institution_code = ? and branch_code = ? and account_code = ? ");
        l_sbExpected.append("and remark_code = ? ");

        String l_strExpected = l_sbExpected.toString();

        assertEquals(l_strExpected, l_strActual);

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCreateQueryDataContainer_0003()
    {
        String STR_METHOD_NAME = " testCreateQueryDataContainer_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAllAndCommit(MainAccountRow.TYPE);
            TestDBUtility.deleteAllAndCommit(InstitutionRow.TYPE);
            TestDBUtility.deleteAllAndCommit(BranchRow.TYPE);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        try
        {
            TestDBUtility.insertWithDel(TestDBUtility.getMainAccountRow());
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        try
        {
            long l_lngInstId = 33L;
            String l_strBranchCode = "381";
            String l_strAccountCode = "2512246";
            WEB3GentradeMainAccount l_mainAccount;

            l_mainAccount = new WEB3GentradeMainAccount(l_lngInstId, l_strBranchCode, l_strAccountCode);

            WEB3HistoryTradeHistoryListServiceImpl l_historyTradeHistoryListServiceImpl =
                new WEB3HistoryTradeHistoryListServiceImpl();

            Date l_datListStartDate = null;
            Date l_datListEndDate = null;
            String l_strProductCode = null;
            
            //[”M:配当（現物）”の場合]
            String l_strProductType = "M";

            String[] l_strActualList = null;
            l_strActualList = l_historyTradeHistoryListServiceImpl.createQueryDataContainer(l_mainAccount,
                l_datListStartDate,
                l_datListEndDate,
                l_strProductCode,
                l_strProductType);

            String[] l_strExpected = {"0D", "381", "251224", "E114"};

            assertNotNull(l_strActualList);
            assertEquals(l_strExpected.length, l_strActualList.length);
            for (int i = 0; i < l_strActualList.length; i++)
            {
                assertEquals(l_strExpected[i], l_strActualList[i]);
            }

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(MainAccountRow.TYPE);
                TestDBUtility.deleteAll(InstitutionRow.TYPE);
                TestDBUtility.deleteAll(BranchRow.TYPE);
            }
            catch (Exception l_ex)
            {
                log.error(TEST_END + STR_METHOD_NAME);
                log.exiting(TEST_END + STR_METHOD_NAME);

                fail();
            }
        }
    }
    
    public void testCreateQueryDataContainer_0004()
    {
        String STR_METHOD_NAME = " testCreateQueryDataContainer_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAllAndCommit(MainAccountRow.TYPE);
            TestDBUtility.deleteAllAndCommit(InstitutionRow.TYPE);
            TestDBUtility.deleteAllAndCommit(BranchRow.TYPE);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        try
        {
            TestDBUtility.insertWithDel(TestDBUtility.getMainAccountRow());
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        try
        {
            long l_lngInstId = 33L;
            String l_strBranchCode = "381";
            String l_strAccountCode = "2512246";
            WEB3GentradeMainAccount l_mainAccount;

            l_mainAccount = new WEB3GentradeMainAccount(l_lngInstId, l_strBranchCode, l_strAccountCode);

            WEB3HistoryTradeHistoryListServiceImpl l_historyTradeHistoryListServiceImpl =
                new WEB3HistoryTradeHistoryListServiceImpl();

            Date l_datListStartDate = null;
            Date l_datListEndDate = null;
            String l_strProductCode = null;
            
            //[”N:配当（信用）”の場合]
            String l_strProductType = "N";

            String[] l_strActualList = null;
            l_strActualList = l_historyTradeHistoryListServiceImpl.createQueryDataContainer(l_mainAccount,
                l_datListStartDate,
                l_datListEndDate,
                l_strProductCode,
                l_strProductType);

            String[] l_strExpected = {"0D", "381", "251224", "A203", "A211"};

            assertNotNull(l_strActualList);
            assertEquals(l_strExpected.length, l_strActualList.length);
            for (int i = 0; i < l_strActualList.length; i++)
            {
                assertEquals(l_strExpected[i], l_strActualList[i]);
            }

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(MainAccountRow.TYPE);
                TestDBUtility.deleteAll(InstitutionRow.TYPE);
                TestDBUtility.deleteAll(BranchRow.TYPE);
            }
            catch (Exception l_ex)
            {
                log.error(TEST_END + STR_METHOD_NAME);
                log.exiting(TEST_END + STR_METHOD_NAME);

                fail();
            }
        }
    }
    
    public void testCreateQueryDataContainer_0005()
    {
        String STR_METHOD_NAME = " testCreateQueryDataContainer_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAllAndCommit(MainAccountRow.TYPE);
            TestDBUtility.deleteAllAndCommit(InstitutionRow.TYPE);
            TestDBUtility.deleteAllAndCommit(BranchRow.TYPE);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        try
        {
            TestDBUtility.insertWithDel(TestDBUtility.getMainAccountRow());
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        try
        {
            long l_lngInstId = 33L;
            String l_strBranchCode = "381";
            String l_strAccountCode = "2512246";
            WEB3GentradeMainAccount l_mainAccount;

            l_mainAccount = new WEB3GentradeMainAccount(l_lngInstId, l_strBranchCode, l_strAccountCode);

            WEB3HistoryTradeHistoryListServiceImpl l_historyTradeHistoryListServiceImpl =
                new WEB3HistoryTradeHistoryListServiceImpl();

            Date l_datListStartDate = null;
            Date l_datListEndDate = null;
            String l_strProductCode = null;
            
            //[”O:配当（信用買）”の場合]
            String l_strProductType = "O";

            String[] l_strActualList = null;
            l_strActualList = l_historyTradeHistoryListServiceImpl.createQueryDataContainer(l_mainAccount,
                l_datListStartDate,
                l_datListEndDate,
                l_strProductCode,
                l_strProductType);

            String[] l_strExpected = {"0D", "381", "251224", "A211"};

            assertNotNull(l_strActualList);
            assertEquals(l_strExpected.length, l_strActualList.length);
            for (int i = 0; i < l_strActualList.length; i++)
            {
                assertEquals(l_strExpected[i], l_strActualList[i]);
            }

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(MainAccountRow.TYPE);
                TestDBUtility.deleteAll(InstitutionRow.TYPE);
                TestDBUtility.deleteAll(BranchRow.TYPE);
            }
            catch (Exception l_ex)
            {
                log.error(TEST_END + STR_METHOD_NAME);
                log.exiting(TEST_END + STR_METHOD_NAME);

                fail();
            }
        }
    }
    
    public void testCreateQueryDataContainer_0006()
    {
        String STR_METHOD_NAME = " testCreateQueryDataContainer_0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAllAndCommit(MainAccountRow.TYPE);
            TestDBUtility.deleteAllAndCommit(InstitutionRow.TYPE);
            TestDBUtility.deleteAllAndCommit(BranchRow.TYPE);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        try
        {
            TestDBUtility.insertWithDel(TestDBUtility.getMainAccountRow());
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        try
        {
            long l_lngInstId = 33L;
            String l_strBranchCode = "381";
            String l_strAccountCode = "2512246";
            WEB3GentradeMainAccount l_mainAccount;

            l_mainAccount = new WEB3GentradeMainAccount(l_lngInstId, l_strBranchCode, l_strAccountCode);

            WEB3HistoryTradeHistoryListServiceImpl l_historyTradeHistoryListServiceImpl =
                new WEB3HistoryTradeHistoryListServiceImpl();

            Date l_datListStartDate = null;
            Date l_datListEndDate = null;
            String l_strProductCode = null;
            
            //[”P:配当（信用売）”の場合]
            String l_strProductType = "P";

            String[] l_strActualList = null;
            l_strActualList = l_historyTradeHistoryListServiceImpl.createQueryDataContainer(l_mainAccount,
                l_datListStartDate,
                l_datListEndDate,
                l_strProductCode,
                l_strProductType);

            String[] l_strExpected = {"0D", "381", "251224", "A203"};

            assertNotNull(l_strActualList);
            assertEquals(l_strExpected.length, l_strActualList.length);
            for (int i = 0; i < l_strActualList.length; i++)
            {
                assertEquals(l_strExpected[i], l_strActualList[i]);
            }

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(MainAccountRow.TYPE);
                TestDBUtility.deleteAll(InstitutionRow.TYPE);
                TestDBUtility.deleteAll(BranchRow.TYPE);
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
