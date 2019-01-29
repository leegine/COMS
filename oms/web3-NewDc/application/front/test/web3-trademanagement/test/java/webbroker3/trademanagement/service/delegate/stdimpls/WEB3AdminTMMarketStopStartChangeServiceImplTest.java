head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.49.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminTMMarketStopStartChangeServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.trademanagement.service.delegate.stdimpls;

import test.util.TestDBUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.data.BranchMarketDealtCondParams;
import webbroker3.gentrade.data.BranchMarketPtsDealtCondParams;
import webbroker3.gentrade.data.FeqBranchMarketDealtCondParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesParams;

/**
 * 管理者市場別取引停止再開変更サービスImplのテスト<BR>
 * @@author 趙林鵬(中訊)
 * @@version 1.0
 */
public class WEB3AdminTMMarketStopStartChangeServiceImplTest extends TestBaseForMock
{

    /**
     * WEB3AdminTMMarketStopStartChangeServiceImplTest
     * @@param arg0
     */
    public WEB3AdminTMMarketStopStartChangeServiceImplTest(String arg0)
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
                WEB3AdminTMMarketStopStartChangeServiceImplTest.class);

    WEB3AdminTMMarketStopStartChangeServiceImpl l_adminTMMarketStopStartChangeServiceImpl =
        new WEB3AdminTMMarketStopStartChangeServiceImpl();

    /**
     *(is取扱可能)<BR>
     * <BR>
     * test_isHandlingPossibleMarket_0001()<BR>
     */
    public void test_isHandlingPossibleMarket_0001() throws WEB3BaseException
    {
        String STR_METHOD_NAME = "test_getInputScreen_0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
        	//MarketテーブルRowを作成 MarketParams
        	MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);
        	WEB3GentradeMarket l_market = new WEB3GentradeMarket(l_marketParams.market_id);

            //（部店市場別.外株）取扱条件Rowを作成 FeqBranchMarketDealtCondParams
            FeqBranchMarketDealtCondParams l_feqBranchMarketDealtCondParams = TestDBUtility.getFeqBranchMarketDealtCondRow();
            TestDBUtility.insertWithDel(l_feqBranchMarketDealtCondParams);

        	boolean l_result = l_adminTMMarketStopStartChangeServiceImpl.isHandlingPossibleMarket(l_market);

        	assertEquals(true, l_result);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    /**
     *(is取扱可能)<BR>
     * <BR>
     * test_isHandlingPossibleMarket_0002()<BR>
     */
    public void test_isHandlingPossibleMarket_0002() throws WEB3BaseException
    {
        String STR_METHOD_NAME = "test_getInputScreen_0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
        	//MarketテーブルRowを作成 MarketParams
        	MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("11");
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);
            WEB3GentradeMarket l_market = new WEB3GentradeMarket(3303L);

            //市場用プリファ@レンス(market_preferences)
            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setValue("1");
            l_marketPreferencesParams.setName("equity.pts.market.div");
            TestDBUtility.deleteAll(l_marketPreferencesParams.TYPE);
            TestDBUtility.insertWithDel(l_marketPreferencesParams);

        	//（部店市場別PTS）取扱条件Rowを作成 BranchMarketDealtCondRow
            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams1 = new BranchMarketPtsDealtCondParams();
            l_branchMarketPtsDealtCondParams1.setInstitutionCode("0D");
            l_branchMarketPtsDealtCondParams1.setBranchCode("381");
            l_branchMarketPtsDealtCondParams1.setMarketCode("11");
            l_branchMarketPtsDealtCondParams1.setMartCanDealtEquity("1");
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams1);
        	boolean l_result = l_adminTMMarketStopStartChangeServiceImpl.isHandlingPossibleMarket(l_market);

        	assertEquals(true, l_result);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

    /**
     *(is取扱可能)<BR>
     * <BR>
     * test_isHandlingPossibleMarket_0003()<BR>
     */
    public void test_isHandlingPossibleMarket_0003() throws WEB3BaseException
    {
        String STR_METHOD_NAME = "test_getInputScreen_0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
        	//MarketテーブルRowを作成 MarketParams
        	MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("11");
            TestDBUtility.insertWithDel(l_marketParams);
            WEB3GentradeMarket l_market = new WEB3GentradeMarket(3303L);

            //市場用プリファ@レンス(market_preferences)
            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setValue("0");
            TestDBUtility.insertWithDel(l_marketPreferencesParams);

        	//（部店市場別）取扱条件Rowを作成 BranchMarketDealtCondRow
        	BranchMarketDealtCondParams l_branchMarketDealCondParams1 = new BranchMarketDealtCondParams();
            l_branchMarketDealCondParams1.setInstitutionCode("0D");
            l_branchMarketDealCondParams1.setBranchCode("381");
            l_branchMarketDealCondParams1.setMarketCode("11");
            l_branchMarketDealCondParams1.setMartCanDealtEquity("1");
            TestDBUtility.insertWithDel(l_branchMarketDealCondParams1);

        	boolean l_result = l_adminTMMarketStopStartChangeServiceImpl.isHandlingPossibleMarket(l_market);

        	assertEquals(true, l_result);
        }
        catch (Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
    }

}
@
