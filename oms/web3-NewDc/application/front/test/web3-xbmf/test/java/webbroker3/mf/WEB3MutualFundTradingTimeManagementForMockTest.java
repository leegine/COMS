head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.14.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualFundTradingTimeManagementForMockTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf;

import java.util.Date;

import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3MutualFundTradingTimeManagementForMockTest extends TestBaseForMock
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFundTradedProductForMock.class);
    
    public WEB3MutualFundTradingTimeManagementForMockTest(String arg0)
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

    public void testMockGetMutualOrderBizDate()
    {
        final String STR_METHOD_NAME = "testMockGetMutualOrderBizDate()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3MutualFundTradingTimeManagementForMock.mockGetMutualOrderBizDate(
                    WEB3DateUtility.getDate("20060102", "yyyyMMdd"));
            Date l_date = WEB3MutualFundTradingTimeManagement.getMutualOrderBizDate();
            String l_strDate = WEB3DateUtility.formatDate(l_date , "yyyyMMdd");
            assertEquals("20060102", l_strDate);
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_exc);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testMockGetOrderCloseTime()
    {
        final String STR_METHOD_NAME = "testMockGetOrderCloseTime()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3MutualFundTradingTimeManagementForMock.mockGetOrderCloseTime("150000");
            String l_strCloseTime = WEB3MutualFundTradingTimeManagement.getOrderCloseTime();
            assertEquals("150000", l_strCloseTime);
            
            WEB3MutualFundTradingTimeManagementForMock.mockGetOrderCloseTime("101010");
            l_strCloseTime = WEB3MutualFundTradingTimeManagement.getOrderCloseTime();
            assertEquals("101010", l_strCloseTime);
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME, l_exc);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
