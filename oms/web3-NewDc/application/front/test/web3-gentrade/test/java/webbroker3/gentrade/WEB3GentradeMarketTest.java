head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.22.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3GentradeMarketTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.gentrade;

import test.util.TestDBUtility;

import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;

public class WEB3GentradeMarketTest extends TestBaseForMock
{
    private WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeMarketTest.class);
    
    public WEB3GentradeMarketTest(String arg0)
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

    public void testIsPTSMarket_T01()
    {
        final String STR_METHOD_NAME = "testIsPTSMarket_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //MarketParams
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);

            //MarketPreferencesParams
            TestDBUtility.deleteAll(MarketPreferencesParams.TYPE);
            MarketPreferencesParams l_marketPreferencesParams =
                TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(l_marketParams.getMarketId());
            TestDBUtility.insertWithDel(l_marketPreferencesParams);

            WEB3GentradeMarket l_market = new WEB3GentradeMarket(l_marketParams.getMarketId());
            boolean l_isMarket = l_market.isPTSMarket();
            assertTrue(!l_isMarket);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testIsPTSMarket_T02()
    {
        final String STR_METHOD_NAME = "testIsPTSMarket_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //MarketParams
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);

            //MarketPreferencesParams
            TestDBUtility.deleteAll(MarketPreferencesParams.TYPE);
            MarketPreferencesParams l_marketPreferencesParams =
                TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(l_marketParams.getMarketId());
            l_marketPreferencesParams.setName("equity.pts.market.div");
            l_marketPreferencesParams.setNameSerialNo(1);
            l_marketPreferencesParams.setValue("0");
            TestDBUtility.insertWithDel(l_marketPreferencesParams);

            WEB3GentradeMarket l_market = new WEB3GentradeMarket(l_marketParams.getMarketId());
            boolean l_isMarket = l_market.isPTSMarket();
            assertTrue(!l_isMarket);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testIsPTSMarket_T03()
    {
        final String STR_METHOD_NAME = "testIsPTSMarket_T03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //MarketParams
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);

            //MarketPreferencesParams
            TestDBUtility.deleteAll(MarketPreferencesParams.TYPE);
            MarketPreferencesParams l_marketPreferencesParams =
                TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(l_marketParams.getMarketId());
            l_marketPreferencesParams.setName("equity.pts.market.div");
            l_marketPreferencesParams.setNameSerialNo(1);
            l_marketPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_marketPreferencesParams);

            WEB3GentradeMarket l_market = new WEB3GentradeMarket(l_marketParams.getMarketId());
            boolean l_isMarket = l_market.isPTSMarket();
            assertTrue(l_isMarket);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

}
@
