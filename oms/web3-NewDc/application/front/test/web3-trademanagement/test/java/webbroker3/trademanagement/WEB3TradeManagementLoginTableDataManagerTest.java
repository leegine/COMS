head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.04.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3TradeManagementLoginTableDataManagerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.trademanagement;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.dbind.RowType;

import webbroker3.gentrade.data.LoginHistoryPastRow;
import webbroker3.gentrade.data.LoginHistoryRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3TradeManagementLoginTableDataManagerTest extends TestBaseForMock
{

    public WEB3TradeManagementLoginTableDataManagerTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        loginTableDataManager = new WEB3TradeManagementLoginTableDataManager();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3TradeManagementLoginTableDataManagerTest.class);
    
    private WEB3TradeManagementLoginTableDataManager loginTableDataManager;
    
    /*
     * Test method for 'webbroker3.trademanagement.WEB3TradeManagementLoginTableDataManager.getRowType(String)'
     */
    public void testGetRowType_case1()
    {
        String STR_METHOD_NAME = "testGetRowType_case1()";
        log.entering(STR_METHOD_NAME);
        try
        {   
            Date l_date = WEB3DateUtility.getDate("20080924022000", "yyyyMMddhhmmss");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(l_date.getTime()));
            String l_strDate = "20080924";
            String l_strStartTime = "0200";
            RowType l_ret = loginTableDataManager.getRowType(l_strDate, l_strStartTime);
            assertEquals(LoginHistoryRow.TYPE, l_ret);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage());
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    /*
     * Test method for 'webbroker3.trademanagement.WEB3TradeManagementLoginTableDataManager.getRowType(String)'
     */
    public void testGetRowType_case2()
    {
        String STR_METHOD_NAME = "testGetRowType_case2()";
        log.entering(STR_METHOD_NAME);
        try
        {   
            Date l_date = WEB3DateUtility.getDate("20080924000000", "yyyyMMddhhmmss");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(l_date.getTime()));
            String l_strDate = "20080924";
            String l_strStartTime = "0130";
            RowType l_ret = loginTableDataManager.getRowType(l_strDate, l_strStartTime);
            assertEquals(LoginHistoryRow.TYPE, l_ret);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    /*
     * Test method for 'webbroker3.trademanagement.WEB3TradeManagementLoginTableDataManager.getRowType(String)'
     */
    public void testGetRowType_case3()
    {
        String STR_METHOD_NAME = "testGetRowType_case3()";
        log.entering(STR_METHOD_NAME);
        try
        {   
            Date l_date = WEB3DateUtility.getDate("20080924033000", "yyyyMMddhhmmss");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(l_date.getTime()));
            String l_strDate = "20080924";
            String l_strStartTime = "0900";
            RowType l_ret = loginTableDataManager.getRowType(l_strDate, l_strStartTime);
            assertEquals(LoginHistoryRow.TYPE, l_ret);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    /*
     * Test method for 'webbroker3.trademanagement.WEB3TradeManagementLoginTableDataManager.getRowType(String)'
     */
    public void testGetRowType_case4()
    {
        String STR_METHOD_NAME = "testGetRowType_case4()";
        log.entering(STR_METHOD_NAME);
        try
        {   
            Date l_date = WEB3DateUtility.getDate("20080924022000", "yyyyMMddhhmmss");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(l_date.getTime()));
            String l_strDate = "20080712";
            String l_strStartTime = "0900";
            RowType l_ret = loginTableDataManager.getRowType(l_strDate, l_strStartTime);
            assertEquals(LoginHistoryPastRow.TYPE, l_ret);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    /*
     * Test method for 'webbroker3.trademanagement.WEB3TradeManagementLoginTableDataManager.getRowType(String)'
     */
    public void testGetRowType_case5()
    {
        String STR_METHOD_NAME = "testGetRowType_case5()";
        log.entering(STR_METHOD_NAME);
        try
        {   
            Date l_date = WEB3DateUtility.getDate("20080924022000", "yyyyMMddhhmmss");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(l_date.getTime()));
            String l_strDate = "20080925";
            String l_strStartTime = "0900";
            RowType l_ret = loginTableDataManager.getRowType(l_strDate, l_strStartTime);
            assertEquals(LoginHistoryPastRow.TYPE, l_ret);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    /*
     * Test method for 'webbroker3.trademanagement.WEB3TradeManagementLoginTableDataManager.getRowType(String)'
     */
    public void testGetRowType_case6()
    {
        String STR_METHOD_NAME = "testGetRowType_case6()";
        log.entering(STR_METHOD_NAME);
        try
        {   
            Date l_date = WEB3DateUtility.getDate("20080924044000", "yyyyMMddhhmmss");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(l_date.getTime()));
            String l_strDate = "20080924";
            String l_strStartTime = "0900";
            RowType l_ret = loginTableDataManager.getRowType(l_strDate, l_strStartTime);
            assertEquals(LoginHistoryRow.TYPE, l_ret);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    /*
     * Test method for 'webbroker3.trademanagement.WEB3TradeManagementLoginTableDataManager.getRowType(String)'
     */
    public void testGetRowType_case7()
    {
        String STR_METHOD_NAME = "testGetRowType_case7()";
        log.entering(STR_METHOD_NAME);
        try
        {   
            Date l_date = WEB3DateUtility.getDate("20080924044000", "yyyyMMddhhmmss");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(l_date.getTime()));
            String l_strDate = "20080925";
            String l_strStartTime = "0900";
            RowType l_ret = loginTableDataManager.getRowType(l_strDate, l_strStartTime);
            assertEquals(LoginHistoryPastRow.TYPE, l_ret);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }
    
    /*
     * Test method for 'webbroker3.trademanagement.WEB3TradeManagementLoginTableDataManager.getRowType(String)'
     */
    public void testGetRowType_case8()
    {
        String STR_METHOD_NAME = "testGetRowType_case8()";
        log.entering(STR_METHOD_NAME);
        try
        {   
            Date l_date = WEB3DateUtility.getDate("20080924044000", "yyyyMMddhhmmss");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(l_date.getTime()));
            String l_strDate = "20080923";
            String l_strStartTime = "0900";
            RowType l_ret = loginTableDataManager.getRowType(l_strDate, l_strStartTime);
            assertEquals(LoginHistoryPastRow.TYPE, l_ret);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    /*
     * Test method for 'webbroker3.trademanagement.WEB3TradeManagementLoginTableDataManager.getRowType(String)'
     */
    public void testGetRowType_case9()
    {
        String STR_METHOD_NAME = "testGetRowType_case9()";
        log.entering(STR_METHOD_NAME);
        try
        {   
            Date l_date = WEB3DateUtility.getDate("20080924235959", "yyyyMMddHHmmss");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(l_date.getTime()));
            String l_strDate = "20080924";
            String l_strStartTime = "0900";
            RowType l_ret = loginTableDataManager.getRowType(l_strDate, l_strStartTime);
            assertEquals(LoginHistoryRow.TYPE, l_ret);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    /*
     * Test method for 'webbroker3.trademanagement.WEB3TradeManagementLoginTableDataManager.getRowType(String)'
     */
    public void testGetRowType_case10()
    {
        String STR_METHOD_NAME = "testGetRowType_case10()";
        log.entering(STR_METHOD_NAME);
        try
        {   
            Date l_date = WEB3DateUtility.getDate("20080924032959", "yyyyMMddHHmmss");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(l_date.getTime()));
            String l_strDate = "20080924";
            String l_strStartTime = "0230";
            RowType l_ret = loginTableDataManager.getRowType(l_strDate, l_strStartTime);
            assertEquals(LoginHistoryRow.TYPE, l_ret);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

}
@
