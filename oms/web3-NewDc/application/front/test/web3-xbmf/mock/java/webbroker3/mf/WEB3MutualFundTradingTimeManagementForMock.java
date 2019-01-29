head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.23.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualFundTradingTimeManagementForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundTradedProductParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeTradingClendarDetailsImplForMock;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.mf.data.MutualFundFrgncalRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * 投信取引時間管理ForMock
 *
 * @@author 柴双紅(中訊)
 * @@version 1.0
 */
public class WEB3MutualFundTradingTimeManagementForMock extends WEB3MutualFundTradingTimeManagement
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFundTradedProductForMock.class);
    
    /**
     * get投信発注日
     * @@param l_expectDate 期待?
     * @@throws WEB3BaseException
     */
    public static void mockGetMutualOrderBizDate(Date l_expectDate) throws WEB3BaseException
    {
        TestDBUtility.deleteAll(MutualFundFrgncalRow.TYPE);
        WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_expectDate);
    }
    
    /**
     * 注文受付締切時間を取得する。
     * @@param l_strCloseTime
     */
    public static void mockGetOrderCloseTime(String l_strCloseTime) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "mockGetOrderCloseTime(String)";
        log.entering(STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        TestDBUtility.insertWithDel(l_institutionParams);
        WEB3GentradeInstitution l_institution = null;
        try
        {
            l_institution = new WEB3GentradeInstitution(l_institutionParams);
        }
        catch(Exception l_exc)
        {
            log.error(STR_METHOD_NAME, l_exc);
        }
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager", 
                "getInstitution",
                new Class[] {long.class},
                l_institution);
        
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketId(3303);
        TestDBUtility.insertWithDel(l_marketParams);
        
        ProductParams l_productParams = TestDBUtility.getProductRow();
        TestDBUtility.insertWithDel(l_productParams);
        TradedProductParams l_tradeProductParams = TestDBUtility.getTradedProductRow();
        l_tradeProductParams.setTradedProductId(400003000100000L);
        TestDBUtility.insertWithDel(l_tradeProductParams);
        
        MutualFundTradedProductParams l_fundTradedProductParams =
            TestDBUtility.getMutualFundTradedProductRow();
        l_fundTradedProductParams.setTradedProductId(400003000100000L);
        WEB3MutualFundTradedProduct l_fundTradedProduct = null;
        
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setEndTime(l_strCloseTime);
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("123");
        l_tradingTimeParams.setMarketCode("N1");
        l_tradingTimeParams.setTradingTimeType("01");  
        l_tradingTimeParams.setProductCode("0");
        l_tradingTimeParams.setBizDateType("1");  
        l_tradingTimeParams.setBizdateCalcParameter("0");
        
        TestDBUtility.insertWithDel(l_tradingTimeParams);
        try
        {
            l_fundTradedProduct =
                new WEB3MutualFundTradedProduct(l_fundTradedProductParams);
        }
        catch(Exception l_exc)
        {
            log.error(STR_METHOD_NAME, l_exc);
        }
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mf.WEB3MutualFundProductManager", 
            "getMutualFundTradedProduct",
            new Class[] {Institution.class, String.class},
            l_fundTradedProduct);
        
        WEB3GentradeTradingClendarDetailsImplForMock l_implForMock =
            new WEB3GentradeTradingClendarDetailsImplForMock();
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.gentrade.WEB3GentradeTradingCalendarModelImpl", 
            "getTradingCalendarDetails",
            new Class[] {long.class},
            l_implForMock);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.gentrade.WEB3GentradeTradingClendarDetailsImpl", 
            "getTradeCloseTime",
            new Class[] {},
            l_strCloseTime);
    }
}
@
