head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.54.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityOrderHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : WEB3EquityOrderHandlerTest.java
Author Name      : Daiwa Institute of Research
Revesion History : 2007/12/29@@à(u) VKì¬
*/
package webbroker3.equity.handler;

import java.sql.Timestamp;

import test.util.TestDBUtility;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.message.WEB3EquityBuyCompleteRequest;
import webbroker3.equity.message.WEB3EquityBuyCompleteResponse;
import webbroker3.equity.message.WEB3EquityBuyConfirmRequest;
import webbroker3.equity.message.WEB3EquityBuyConfirmResponse;
import webbroker3.equity.message.WEB3EquitySellCompleteRequest;
import webbroker3.equity.message.WEB3EquitySellCompleteResponse;
import webbroker3.equity.message.WEB3EquitySellConfirmRequest;
import webbroker3.equity.message.WEB3EquitySellConfirmResponse;
import webbroker3.equity.service.delegate.WEB3EquityPTSOrderService;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesParams;

/**
 * ®E®¶nhNXÌeXg<BR>
 * @@author æâÑQ(u)
 * @@version 1.0
 */
public class WEB3EquityOrderHandlerTest extends TestBaseForMock
{
    private WEB3EquityPTSOrderService l_service = null;

    /**
     * OoÍ[eBeBB<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3EquityOrderHandlerTest.class);

    public WEB3EquityOrderHandlerTest(String name)
    {
        super(name);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    
    /**
     * (®¶tmFNGXg)<BR>
     * PTSsêÌê(sê.isPTSsê()==true<BR>
     * test_equityBuyOrderConfirm_0001()<BR>
     */
    public void test_equityBuyOrderConfirm_0001() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "test_equityBuyOrderConfirm_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[]{},
                new Long(333812512246L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(WEB3DateUtility.getDate("20080107", "yyyyMMdd").getTime()));

        WEB3EquityOrderHandler l_handler = new WEB3EquityOrderHandler();

        WEB3EquityBuyConfirmRequest l_request = new WEB3EquityBuyConfirmRequest();

        //Á¿R[hproductCode
        l_request.productCode = "1234";
        //ûÀæªtaxType
        l_request.taxType = "0";
        //æøæªtradingType
        l_request.tradingType = "1";
        //sêR[h
        l_request.marketCode = "11";

        try
        {
            WEB3EquityBuyConfirmResponse l_resultResponse = new WEB3EquityBuyConfirmResponse();

            //æøÔRowðì¬
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("5");
            l_tradingTimeParams.setMarketCode("11");
            TestDBUtility.deleteAllAndCommit(l_tradingTimeParams.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_tradingTimeParams);

            //sêRowðì¬
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("11");
            TestDBUtility.insertWithDelAndCommit(l_marketParams);

            //Úq}X^[Rowðì¬
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);
            
            //sêpvt@@X
            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("equity.pts.market.div");
            l_marketPreferencesParams.setNameSerialNo(1);
            l_marketPreferencesParams.setValue("1");
            l_marketPreferencesParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            l_marketPreferencesParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            TestDBUtility.deleteAllAndCommit(l_marketPreferencesParams.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_marketPreferencesParams);

            l_resultResponse = l_handler.equityBuyOrderConfirm(l_request);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00126, l_resultResponse.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * (®¶tmFNGXg)<BR>
     * unregisterService<BR>
     * test_equityBuyOrderConfirm_0002()<BR>
     */
    public void test_equityBuyOrderConfirm_0002()
    {
        final String STR_METHOD_NAME = "test_equityBuyOrderConfirm_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[]{},
                new Long(333812512246L));

        WEB3EquityOrderHandler l_handler = new WEB3EquityOrderHandler();

        WEB3EquityBuyConfirmRequest l_request = new WEB3EquityBuyConfirmRequest();

        //Á¿R[hproductCode
        l_request.productCode = "1234";
        //ûÀæªtaxType
        l_request.taxType = "0";
        //æøæªtradingType
        l_request.tradingType = "1";
        //sêR[h
        l_request.marketCode = "11";
        try
        {
            WEB3EquityBuyConfirmResponse l_resultResponse = new WEB3EquityBuyConfirmResponse();

            //æøÔRowðì¬
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("5");
            l_tradingTimeParams.setMarketCode("11");
            TestDBUtility.insertWithDelAndCommit(l_tradingTimeParams);
            
            //sêRowðì¬
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("11");
            TestDBUtility.insertWithDelAndCommit(l_marketParams);

            //Úq}X^[Rowðì¬
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);

            //sêpvt@@X
            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("equity.pts.market.div");
            l_marketPreferencesParams.setNameSerialNo(1);
            l_marketPreferencesParams.setValue("1");
            l_marketPreferencesParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            l_marketPreferencesParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            TestDBUtility.insertWithDelAndCommit(l_marketPreferencesParams);

            l_service = (WEB3EquityPTSOrderService) Services.getService(WEB3EquityPTSOrderService.class);
            Services.unregisterService(WEB3EquityPTSOrderService.class);

            l_resultResponse = l_handler.equityBuyOrderConfirm(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_resultResponse.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3EquityPTSOrderService.class, l_service);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * (®¶tmFNGXg)<BR>
     * usêR[hªnullvÌáOðX[·éB<BR>
     * test_equityBuyOrderConfirm_0003()<BR>
     */
    public void test_equityBuyOrderConfirm_0003() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "test_equityBuyOrderConfirm_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3EquityOrderHandler l_handler = new WEB3EquityOrderHandler();

        WEB3EquityBuyConfirmRequest l_request = new WEB3EquityBuyConfirmRequest();

        //sêR[h
        l_request.marketCode = null;
        try
        {
            WEB3EquityBuyConfirmResponse l_resultResponse = new WEB3EquityBuyConfirmResponse();

            l_resultResponse = l_handler.equityBuyOrderConfirm(l_request);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00443, l_resultResponse.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * (®¶tmFNGXg)<BR>
     * usêR[hª¢è`ÌlvÌáOðX[·éB<BR>
     * test_equityBuyOrderConfirm_0004()<BR>
     */
    public void test_equityBuyOrderConfirm_0004() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "test_equityBuyOrderConfirm_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3EquityOrderHandler l_handler = new WEB3EquityOrderHandler();

        WEB3EquityBuyConfirmRequest l_request = new WEB3EquityBuyConfirmRequest();

        //sêR[h
        l_request.marketCode = "12";
        try
        {
            WEB3EquityBuyConfirmResponse l_resultResponse = new WEB3EquityBuyConfirmResponse();

            l_resultResponse = l_handler.equityBuyOrderConfirm(l_request);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00608, l_resultResponse.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * (®¶tmFNGXg)<BR>
     * PTSsêÌê(sê.isPTSsê()==false<BR>
     * PF
     * QFåã
     * RF¼Ã®
     * UFª
     * WFDy
     * XFNNM
     * POFJASDAQ
     * PPFJNX-PTS
     * XXFDæsê
     * test_equityBuyOrderConfirm_marketCode_0001()<BR>
     */
    public void test_equityBuyOrderConfirm_marketCode_0001() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "test_equityBuyOrderConfirm_marketCode_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[]{},
                new Long(333812512246L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(WEB3DateUtility.getDate("20080106", "yyyyMMdd").getTime()));

        WEB3EquityOrderHandler l_handler = new WEB3EquityOrderHandler();

        WEB3EquityBuyConfirmRequest l_request = new WEB3EquityBuyConfirmRequest();

        //Á¿R[hproductCode
        l_request.productCode = "1234";
        //ûÀæªtaxType
        l_request.taxType = "0";
        //æøæªtradingType
        l_request.tradingType = "1";
        //sêR[h
        l_request.marketCode = "1";

        try
        {
            WEB3EquityBuyConfirmResponse l_resultResponse = new WEB3EquityBuyConfirmResponse();

            //æøÔRowðì¬
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("0");
            l_tradingTimeParams.setMarketCode("1");
            TestDBUtility.insertWithDelAndCommit(l_tradingTimeParams);

            //sêRowðì¬
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDelAndCommit(l_marketParams);

            //Úq}X^[Rowðì¬
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);

            //sêpvt@@X
            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("equity.pts.market.div");
            l_marketPreferencesParams.setNameSerialNo(1);
            l_marketPreferencesParams.setValue("0");
            l_marketPreferencesParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            l_marketPreferencesParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            TestDBUtility.insertWithDelAndCommit(l_marketPreferencesParams);

            l_resultResponse = l_handler.equityBuyOrderConfirm(l_request);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00126, l_resultResponse.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * (®¶tmFNGXg)<BR>
     * PTSsêÌê(sê.isPTSsê()==false<BR>
     * PF
     * QFåã
     * RF¼Ã®
     * UFª
     * WFDy
     * XFNNM
     * POFJASDAQ
     * PPFJNX-PTS
     * XXFDæsê
     * test_equityBuyOrderConfirm_marketCode_0002()<BR>
     */
    public void test_equityBuyOrderConfirm_marketCode_0002() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "test_equityBuyOrderConfirm_marketCode_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[]{},
                new Long(333812512246L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(WEB3DateUtility.getDate("20080106", "yyyyMMdd").getTime()));

        WEB3EquityOrderHandler l_handler = new WEB3EquityOrderHandler();

        WEB3EquityBuyConfirmRequest l_request = new WEB3EquityBuyConfirmRequest();

        //Á¿R[hproductCode
        l_request.productCode = "1234";
        //ûÀæªtaxType
        l_request.taxType = "0";
        //æøæªtradingType
        l_request.tradingType = "1";
        //sêR[h
        l_request.marketCode = "2";

        try
        {
            WEB3EquityBuyConfirmResponse l_resultResponse = new WEB3EquityBuyConfirmResponse();

            //æøÔRowðì¬
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("0");
            l_tradingTimeParams.setMarketCode("2");
            TestDBUtility.insertWithDelAndCommit(l_tradingTimeParams);

            //sêRowðì¬
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("2");
            TestDBUtility.insertWithDelAndCommit(l_marketParams);

            //Úq}X^[Rowðì¬
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);

            //sêpvt@@X
            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("equity.pts.market.div");
            l_marketPreferencesParams.setNameSerialNo(1);
            l_marketPreferencesParams.setValue("0");
            l_marketPreferencesParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            l_marketPreferencesParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            TestDBUtility.insertWithDelAndCommit(l_marketPreferencesParams);

            l_resultResponse = l_handler.equityBuyOrderConfirm(l_request);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00126, l_resultResponse.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * (®¶tmFNGXg)<BR>
     * PTSsêÌê(sê.isPTSsê()==false<BR>
     * PF
     * QFåã
     * RF¼Ã®
     * UFª
     * WFDy
     * XFNNM
     * POFJASDAQ
     * PPFJNX-PTS
     * XXFDæsê
     * test_equityBuyOrderConfirm_marketCode_0003()<BR>
     */
    public void test_equityBuyOrderConfirm_marketCode_0003() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "test_equityBuyOrderConfirm_marketCode_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[]{},
                new Long(333812512246L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(WEB3DateUtility.getDate("20080106", "yyyyMMdd").getTime()));

        WEB3EquityOrderHandler l_handler = new WEB3EquityOrderHandler();

        WEB3EquityBuyConfirmRequest l_request = new WEB3EquityBuyConfirmRequest();

        //Á¿R[hproductCode
        l_request.productCode = "1234";
        //ûÀæªtaxType
        l_request.taxType = "0";
        //æøæªtradingType
        l_request.tradingType = "1";
        //sêR[h
        l_request.marketCode = "3";

        try
        {
            WEB3EquityBuyConfirmResponse l_resultResponse = new WEB3EquityBuyConfirmResponse();

            //æøÔRowðì¬
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("0");
            l_tradingTimeParams.setMarketCode("3");
            TestDBUtility.insertWithDelAndCommit(l_tradingTimeParams);

            //sêRowðì¬
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("3");
            TestDBUtility.insertWithDelAndCommit(l_marketParams);
            //Úq}X^[Rowðì¬
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();

            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);

            //sêpvt@@X
            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("equity.pts.market.div");
            l_marketPreferencesParams.setNameSerialNo(1);
            l_marketPreferencesParams.setValue("0");
            l_marketPreferencesParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            l_marketPreferencesParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            TestDBUtility.insertWithDelAndCommit(l_marketPreferencesParams);

            l_resultResponse = l_handler.equityBuyOrderConfirm(l_request);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00126, l_resultResponse.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * (®¶tmFNGXg)<BR>
     * PTSsêÌê(sê.isPTSsê()==false<BR>
     * PF
     * QFåã
     * RF¼Ã®
     * UFª
     * WFDy
     * XFNNM
     * POFJASDAQ
     * PPFJNX-PTS
     * XXFDæsê
     * test_equityBuyOrderConfirm_marketCode_0006()<BR>
     */
    public void test_equityBuyOrderConfirm_marketCode_0006() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "test_equityBuyOrderConfirm_marketCode_0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[]{},
                new Long(333812512246L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(WEB3DateUtility.getDate("20080106", "yyyyMMdd").getTime()));

        WEB3EquityOrderHandler l_handler = new WEB3EquityOrderHandler();

        WEB3EquityBuyConfirmRequest l_request = new WEB3EquityBuyConfirmRequest();

        //Á¿R[hproductCode
        l_request.productCode = "1234";
        //ûÀæªtaxType
        l_request.taxType = "0";
        //æøæªtradingType
        l_request.tradingType = "1";
        //sêR[h
        l_request.marketCode = "6";

        try
        {
            WEB3EquityBuyConfirmResponse l_resultResponse = new WEB3EquityBuyConfirmResponse();

            //æøÔRowðì¬
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setMarketCode("6");
            l_tradingTimeParams.setBizDateType("0");
            TestDBUtility.insertWithDelAndCommit(l_tradingTimeParams);

            //sêRowðì¬
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("6");
            TestDBUtility.insertWithDelAndCommit(l_marketParams);

            //Úq}X^[Rowðì¬
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();

            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);

            //sêpvt@@X
            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("equity.pts.market.div");
            l_marketPreferencesParams.setNameSerialNo(1);
            l_marketPreferencesParams.setValue("0");
            l_marketPreferencesParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            l_marketPreferencesParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            TestDBUtility.insertWithDelAndCommit(l_marketPreferencesParams);

            l_resultResponse = l_handler.equityBuyOrderConfirm(l_request);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00126, l_resultResponse.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * (®¶tmFNGXg)<BR>
     * PTSsêÌê(sê.isPTSsê()==false<BR>
     * PF
     * QFåã
     * RF¼Ã®
     * UFª
     * WFDy
     * XFNNM
     * POFJASDAQ
     * PPFJNX-PTS
     * XXFDæsê
     * test_equityBuyOrderConfirm_marketCode_0009()<BR>
     */
    public void test_equityBuyOrderConfirm_marketCode_0009() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "test_equityBuyOrderConfirm_marketCode_0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[]{},
                new Long(333812512246L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(WEB3DateUtility.getDate("20080106", "yyyyMMdd").getTime()));

        WEB3EquityOrderHandler l_handler = new WEB3EquityOrderHandler();

        WEB3EquityBuyConfirmRequest l_request = new WEB3EquityBuyConfirmRequest();

        //Á¿R[hproductCode
        l_request.productCode = "1234";
        //ûÀæªtaxType
        l_request.taxType = "0";
        //æøæªtradingType
        l_request.tradingType = "1";
        //sêR[h
        l_request.marketCode = "9";

        try
        {
            WEB3EquityBuyConfirmResponse l_resultResponse = new WEB3EquityBuyConfirmResponse();

            //æøÔRowðì¬
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("0");
            l_tradingTimeParams.setMarketCode("9");
            TestDBUtility.insertWithDelAndCommit(l_tradingTimeParams);

            //sêRowðì¬
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("9");

            TestDBUtility.insertWithDelAndCommit(l_marketParams);

            //Úq}X^[Rowðì¬
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();

            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);

            //sêpvt@@X
            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("equity.pts.market.div");
            l_marketPreferencesParams.setNameSerialNo(1);
            l_marketPreferencesParams.setValue("0");
            l_marketPreferencesParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            l_marketPreferencesParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            TestDBUtility.insertWithDelAndCommit(l_marketPreferencesParams);

            l_resultResponse = l_handler.equityBuyOrderConfirm(l_request);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00126, l_resultResponse.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * (®¶tmFNGXg)<BR>
     * PTSsêÌê(sê.isPTSsê()==false<BR>
     * PF
     * QFåã
     * RF¼Ã®
     * UFª
     * WFDy
     * XFNNM
     * POFJASDAQ
     * PPFJNX-PTS
     * XXFDæsê
     * test_equityBuyOrderConfirm_marketCode_0010()<BR>
     */
    public void test_equityBuyOrderConfirm_marketCode_0010() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "test_equityBuyOrderConfirm_marketCode_0010()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[]{},
                new Long(333812512246L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(WEB3DateUtility.getDate("20080106", "yyyyMMdd").getTime()));

        WEB3EquityOrderHandler l_handler = new WEB3EquityOrderHandler();

        WEB3EquityBuyConfirmRequest l_request = new WEB3EquityBuyConfirmRequest();

        //Á¿R[hproductCode
        l_request.productCode = "1234";
        //ûÀæªtaxType
        l_request.taxType = "0";
        //æøæªtradingType
        l_request.tradingType = "1";
        //sêR[h
        l_request.marketCode = "10";

        try
        {
            WEB3EquityBuyConfirmResponse l_resultResponse = new WEB3EquityBuyConfirmResponse();

            //æøÔRowðì¬
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("0");
            l_tradingTimeParams.setMarketCode("10");
            TestDBUtility.insertWithDelAndCommit(l_tradingTimeParams);

            //sêRowðì¬
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("10");
            TestDBUtility.insertWithDelAndCommit(l_marketParams);

            //Úq}X^[Rowðì¬
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();

            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);

            //sêpvt@@X
            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("equity.pts.market.div");
            l_marketPreferencesParams.setNameSerialNo(1);
            l_marketPreferencesParams.setValue("0");
            l_marketPreferencesParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            l_marketPreferencesParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            TestDBUtility.insertWithDelAndCommit(l_marketPreferencesParams);

            l_resultResponse = l_handler.equityBuyOrderConfirm(l_request);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00126, l_resultResponse.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * (®¶tmFNGXg)<BR>
     * PTSsêÌê(sê.isPTSsê()==false<BR>
     * PF
     * QFåã
     * RF¼Ã®
     * UFª
     * WFDy
     * XFNNM
     * POFJASDAQ
     * PPFJNX-PTS
     * XXFDæsê
     * test_equityBuyOrderConfirm_marketCode_0099()<BR>
     */
    public void test_equityBuyOrderConfirm_marketCode_0099() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "test_equityBuyOrderConfirm_marketCode_0099()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[]{},
                new Long(333812512246L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(WEB3DateUtility.getDate("20080106", "yyyyMMdd").getTime()));

        WEB3EquityOrderHandler l_handler = new WEB3EquityOrderHandler();

        WEB3EquityBuyConfirmRequest l_request = new WEB3EquityBuyConfirmRequest();

        //Á¿R[hproductCode
        l_request.productCode = "1234";
        //ûÀæªtaxType
        l_request.taxType = "0";
        //æøæªtradingType
        l_request.tradingType = "1";
        //sêR[h
        l_request.marketCode = "99";

        try
        {
            WEB3EquityBuyConfirmResponse l_resultResponse = new WEB3EquityBuyConfirmResponse();

            //æøÔRowðì¬
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("0");
            l_tradingTimeParams.setMarketCode("99");
            TestDBUtility.insertWithDelAndCommit(l_tradingTimeParams);

            //sêRowðì¬
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("99");
            TestDBUtility.insertWithDelAndCommit(l_marketParams);

            //Úq}X^[Rowðì¬
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();

            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);

            //sêpvt@@X
            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("equity.pts.market.div");
            l_marketPreferencesParams.setNameSerialNo(1);
            l_marketPreferencesParams.setValue("0");
            l_marketPreferencesParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            l_marketPreferencesParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            TestDBUtility.insertWithDelAndCommit(l_marketPreferencesParams);

            l_resultResponse = l_handler.equityBuyOrderConfirm(l_request);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00126, l_resultResponse.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * (®¶tmFNGXg)<BR>
     * PTSsêÌê(sê.isPTSsê()==true<BR>
     * test_equitySellOrderConfirm_0001()<BR>
     */
    public void test_equitySellOrderConfirm_0001() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "test_equitySellOrderConfirm_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[]{},
                new Long(333812512246L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(WEB3DateUtility.getDate("20080107", "yyyyMMdd").getTime()));

        WEB3EquityOrderHandler l_handler = new WEB3EquityOrderHandler();

        WEB3EquitySellConfirmRequest l_request = new WEB3EquitySellConfirmRequest();

        //id
        l_request.id = "1234";
        //sêR[h
        l_request.marketCode = "11";

        try
        {
            WEB3EquitySellConfirmResponse l_resultResponse = new WEB3EquitySellConfirmResponse();

            //æøÔRowðì¬
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("5");
            l_tradingTimeParams.setMarketCode("11");
            TestDBUtility.insertWithDelAndCommit(l_tradingTimeParams);

            //sêRowðì¬
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("11");

            TestDBUtility.insertWithDelAndCommit(l_marketParams);

            //Úq}X^[Rowðì¬
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();

            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);

            //sêpvt@@X
            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("equity.pts.market.div");
            l_marketPreferencesParams.setNameSerialNo(1);
            l_marketPreferencesParams.setValue("1");
            l_marketPreferencesParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            l_marketPreferencesParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            TestDBUtility.deleteAllAndCommit(l_marketPreferencesParams.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_marketPreferencesParams);

            l_resultResponse = l_handler.equitySellOrderConfirm(l_request);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00126, l_resultResponse.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * (®¶tmFNGXg)<BR>
     * unregisterService<BR>
     * test_equitySellOrderConfirm_0002()<BR>
     */
    public void test_equitySellOrderConfirm_0002()
    {
        final String STR_METHOD_NAME = "test_equitySellOrderConfirm_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[]{},
                new Long(333812512246L));

        WEB3EquityOrderHandler l_handler = new WEB3EquityOrderHandler();
        
        WEB3EquitySellConfirmRequest l_request = new WEB3EquitySellConfirmRequest();

        //id
        l_request.id = "1234";
        //sêR[h
        l_request.marketCode = "11";
        try
        {
            WEB3EquitySellConfirmResponse l_resultResponse = new WEB3EquitySellConfirmResponse();

            //æøÔRowðì¬
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("5");
            l_tradingTimeParams.setMarketCode("11");
            TestDBUtility.insertWithDelAndCommit(l_tradingTimeParams);
            
            //sêRowðì¬
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("11");
            TestDBUtility.insertWithDelAndCommit(l_marketParams);

            //Úq}X^[Rowðì¬
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();

            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);

            //sêpvt@@X
            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("equity.pts.market.div");
            l_marketPreferencesParams.setNameSerialNo(1);
            l_marketPreferencesParams.setValue("1");
            l_marketPreferencesParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            l_marketPreferencesParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            TestDBUtility.insertWithDelAndCommit(l_marketPreferencesParams);

            l_service = (WEB3EquityPTSOrderService) Services.getService(WEB3EquityPTSOrderService.class);
            Services.unregisterService(WEB3EquityPTSOrderService.class);

            l_resultResponse = l_handler.equitySellOrderConfirm(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_resultResponse.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3EquityPTSOrderService.class, l_service);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * (®¶tmFNGXg)<BR>
     * usêR[hªnullvÌáOðX[·éB<BR>
     * test_equitySellOrderConfirm_0003()<BR>
     */
    public void test_equitySellOrderConfirm_0003() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "test_equitySellOrderConfirm_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3EquityOrderHandler l_handler = new WEB3EquityOrderHandler();

        WEB3EquitySellConfirmRequest l_request = new WEB3EquitySellConfirmRequest();

        //sêR[h
        l_request.id = null;
        try
        {
            WEB3EquitySellConfirmResponse l_resultResponse = new WEB3EquitySellConfirmResponse();

            l_resultResponse = l_handler.equitySellOrderConfirm(l_request);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00443, l_resultResponse.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * (®¶tmFNGXg)<BR>
     * usêR[hª¢è`ÌlvÌáOðX[·éB<BR>
     * test_equitySellOrderConfirm_0004()<BR>
     */
    public void test_equitySellOrderConfirm_0004() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "test_equitySellOrderConfirm_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3EquityOrderHandler l_handler = new WEB3EquityOrderHandler();

        WEB3EquitySellConfirmRequest l_request = new WEB3EquitySellConfirmRequest();

        //sêR[h
        l_request.marketCode = "12";
        try
        {
            WEB3EquitySellConfirmResponse l_resultResponse = new WEB3EquitySellConfirmResponse();

            l_resultResponse = l_handler.equitySellOrderConfirm(l_request);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00608, l_resultResponse.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * (®¶tmFNGXg)<BR>
     * PTSsêÌê(sê.isPTSsê()==false<BR>
     * PF
     * QFåã
     * RF¼Ã®
     * UFª
     * WFDy
     * XFNNM
     * POFJASDAQ
     * PPFJNX-PTS
     * XXFDæsê
     * test_equitySellOrderConfirm_marketCode_0001()<BR>
     */
    public void test_equitySellOrderConfirm_marketCode_0001() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "test_equitySellOrderConfirm_marketCode_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[]{},
                new Long(333812512246L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(WEB3DateUtility.getDate("20080106", "yyyyMMdd").getTime()));

        WEB3EquityOrderHandler l_handler = new WEB3EquityOrderHandler();

        WEB3EquitySellConfirmRequest l_request = new WEB3EquitySellConfirmRequest();

        //id
        l_request.id = "1234";
        //sêR[h
        l_request.marketCode = "1";

        try
        {
            WEB3EquitySellConfirmResponse l_resultResponse = new WEB3EquitySellConfirmResponse();

            //æøÔRowðì¬
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setMarketCode("1");
            l_tradingTimeParams.setBizDateType("0");
            TestDBUtility.insertWithDelAndCommit(l_tradingTimeParams);

            //sêRowðì¬
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDelAndCommit(l_marketParams);

            //Úq}X^[Rowðì¬
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);
            //sêpvt@@X
            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("equity.pts.market.div");
            l_marketPreferencesParams.setNameSerialNo(1);
            l_marketPreferencesParams.setValue("0");
            l_marketPreferencesParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            l_marketPreferencesParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            TestDBUtility.insertWithDelAndCommit(l_marketPreferencesParams);

            l_resultResponse = l_handler.equitySellOrderConfirm(l_request);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00126, l_resultResponse.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * (®¶tmFNGXg)<BR>
     * PTSsêÌê(sê.isPTSsê()==false<BR>
     * PF
     * QFåã
     * RF¼Ã®
     * UFª
     * WFDy
     * XFNNM
     * POFJASDAQ
     * PPFJNX-PTS
     * XXFDæsê
     * test_equitySellOrderConfirm_marketCode_0002()<BR>
     */
    public void test_equitySellOrderConfirm_marketCode_0002() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "test_equitySellOrderConfirm_marketCode_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[]{},
                new Long(333812512246L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(WEB3DateUtility.getDate("20080106", "yyyyMMdd").getTime()));

        WEB3EquityOrderHandler l_handler = new WEB3EquityOrderHandler();

        WEB3EquitySellConfirmRequest l_request = new WEB3EquitySellConfirmRequest();

        //id
        l_request.id = "1234";
        //sêR[h
        l_request.marketCode = "2";        
        try
        {
            WEB3EquitySellConfirmResponse l_resultResponse = new WEB3EquitySellConfirmResponse();

            //æøÔRowðì¬
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("0");
            l_tradingTimeParams.setMarketCode("2");
            TestDBUtility.insertWithDelAndCommit(l_tradingTimeParams);

            //sêRowðì¬
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("2");

            TestDBUtility.insertWithDelAndCommit(l_marketParams);

            //Úq}X^[Rowðì¬
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();

            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);

            //sêpvt@@X
            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("equity.pts.market.div");
            l_marketPreferencesParams.setNameSerialNo(1);
            l_marketPreferencesParams.setValue("0");
            l_marketPreferencesParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            l_marketPreferencesParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            TestDBUtility.insertWithDelAndCommit(l_marketPreferencesParams);

            l_resultResponse = l_handler.equitySellOrderConfirm(l_request);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00126, l_resultResponse.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * (®¶tmFNGXg)<BR>
     * PTSsêÌê(sê.isPTSsê()==false<BR>
     * PF
     * QFåã
     * RF¼Ã®
     * UFª
     * WFDy
     * XFNNM
     * POFJASDAQ
     * PPFJNX-PTS
     * XXFDæsê
     * test_equitySellOrderConfirm_marketCode_0003()<BR>
     */
    public void test_equitySellOrderConfirm_marketCode_0003() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "test_equitySellOrderConfirm_marketCode_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[]{},
                new Long(333812512246L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(WEB3DateUtility.getDate("20080106", "yyyyMMdd").getTime()));

        WEB3EquityOrderHandler l_handler = new WEB3EquityOrderHandler();

        WEB3EquitySellConfirmRequest l_request = new WEB3EquitySellConfirmRequest();

        //id
        l_request.id = "1234";
        //sêR[h
        l_request.marketCode = "3";
        try
        {
            WEB3EquitySellConfirmResponse l_resultResponse = new WEB3EquitySellConfirmResponse();

            //æøÔRowðì¬
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("0");
            l_tradingTimeParams.setMarketCode("3");
            TestDBUtility.insertWithDelAndCommit(l_tradingTimeParams);

            //sêRowðì¬
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("3");

            TestDBUtility.insertWithDelAndCommit(l_marketParams);

            //Úq}X^[Rowðì¬
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();

            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);

            //sêpvt@@X
            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("equity.pts.market.div");
            l_marketPreferencesParams.setNameSerialNo(1);
            l_marketPreferencesParams.setValue("0");
            l_marketPreferencesParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            l_marketPreferencesParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            TestDBUtility.insertWithDelAndCommit(l_marketPreferencesParams);

            l_resultResponse = l_handler.equitySellOrderConfirm(l_request);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00126, l_resultResponse.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
   
    /**
     * (®¶tmFNGXg)<BR>
     * PTSsêÌê(sê.isPTSsê()==false<BR>
     * PF
     * QFåã
     * RF¼Ã®
     * UFª
     * WFDy
     * XFNNM
     * POFJASDAQ
     * PPFJNX-PTS
     * XXFDæsê
     * test_equitySellOrderConfirm_marketCode_0006()<BR>
     */
    public void test_equitySellOrderConfirm_marketCode_0006() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "test_equitySellOrderConfirm_marketCode_0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[]{},
                new Long(333812512246L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(WEB3DateUtility.getDate("20080106", "yyyyMMdd").getTime()));

        WEB3EquityOrderHandler l_handler = new WEB3EquityOrderHandler();

        WEB3EquitySellConfirmRequest l_request = new WEB3EquitySellConfirmRequest();

        //id
        l_request.id = "1234";
        //sêR[h
        l_request.marketCode = "6";

        try
        {
            WEB3EquitySellConfirmResponse l_resultResponse = new WEB3EquitySellConfirmResponse();

            //æøÔRowðì¬
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("0");
            l_tradingTimeParams.setMarketCode("6");
            TestDBUtility.insertWithDelAndCommit(l_tradingTimeParams);

            //sêRowðì¬
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("6");

            TestDBUtility.insertWithDelAndCommit(l_marketParams);

            //Úq}X^[Rowðì¬
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();

            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);

            //sêpvt@@X
            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("equity.pts.market.div");
            l_marketPreferencesParams.setNameSerialNo(1);
            l_marketPreferencesParams.setValue("0");
            l_marketPreferencesParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            l_marketPreferencesParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            TestDBUtility.insertWithDelAndCommit(l_marketPreferencesParams);

            l_resultResponse = l_handler.equitySellOrderConfirm(l_request);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00126, l_resultResponse.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * (®¶tmFNGXg)<BR>
     * PTSsêÌê(sê.isPTSsê()==false<BR>
     * PF
     * QFåã
     * RF¼Ã®
     * UFª
     * WFDy
     * XFNNM
     * POFJASDAQ
     * PPFJNX-PTS
     * XXFDæsê
     * test_equitySellOrderConfirm_marketCode_0009()<BR>
     */
    public void test_equitySellOrderConfirm_marketCode_0009() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "test_equitySellOrderConfirm_marketCode_0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[]{},
                new Long(333812512246L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(WEB3DateUtility.getDate("20080106", "yyyyMMdd").getTime()));

        WEB3EquityOrderHandler l_handler = new WEB3EquityOrderHandler();

        WEB3EquitySellConfirmRequest l_request = new WEB3EquitySellConfirmRequest();

        //id
        l_request.id = "1234";
        //sêR[h
        l_request.marketCode = "9";

        try
        {
            WEB3EquitySellConfirmResponse l_resultResponse = new WEB3EquitySellConfirmResponse();

            //æøÔRowðì¬
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("0");
            l_tradingTimeParams.setMarketCode("9");
            TestDBUtility.insertWithDelAndCommit(l_tradingTimeParams);

            //sêRowðì¬
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("9");

            TestDBUtility.insertWithDelAndCommit(l_marketParams);

            //Úq}X^[Rowðì¬
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();

            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);

            //sêpvt@@X
            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("equity.pts.market.div");
            l_marketPreferencesParams.setNameSerialNo(1);
            l_marketPreferencesParams.setValue("0");
            l_marketPreferencesParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            l_marketPreferencesParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            TestDBUtility.insertWithDelAndCommit(l_marketPreferencesParams);

            l_resultResponse = l_handler.equitySellOrderConfirm(l_request);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00126, l_resultResponse.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * (®¶tmFNGXg)<BR>
     * PTSsêÌê(sê.isPTSsê()==false<BR>
     * PF
     * QFåã
     * RF¼Ã®
     * UFª
     * WFDy
     * XFNNM
     * POFJASDAQ
     * PPFJNX-PTS
     * XXFDæsê
     * test_equitySellOrderConfirm_marketCode_0010()<BR>
     */
    public void test_equitySellOrderConfirm_marketCode_0010() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "test_equitySellOrderConfirm_marketCode_0010()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[]{},
                new Long(333812512246L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(WEB3DateUtility.getDate("20080106", "yyyyMMdd").getTime()));

        WEB3EquityOrderHandler l_handler = new WEB3EquityOrderHandler();

        WEB3EquitySellConfirmRequest l_request = new WEB3EquitySellConfirmRequest();

        //id
        l_request.id = "1234";
        //sêR[h
        l_request.marketCode = "10";

        try
        {
            WEB3EquitySellConfirmResponse l_resultResponse = new WEB3EquitySellConfirmResponse();

            //æøÔRowðì¬
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("0");
            l_tradingTimeParams.setMarketCode("10");
            TestDBUtility.insertWithDelAndCommit(l_tradingTimeParams);

            //sêRowðì¬
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("10");
           
            TestDBUtility.insertWithDelAndCommit(l_marketParams);

            //Úq}X^[Rowðì¬
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();

            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);

            //sêpvt@@X
            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("equity.pts.market.div");
            l_marketPreferencesParams.setNameSerialNo(1);
            l_marketPreferencesParams.setValue("0");
            l_marketPreferencesParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            l_marketPreferencesParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            TestDBUtility.insertWithDelAndCommit(l_marketPreferencesParams);

            l_resultResponse = l_handler.equitySellOrderConfirm(l_request);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00126, l_resultResponse.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * (®¶tmFNGXg)<BR>
     * PTSsêÌê(sê.isPTSsê()==false<BR>
     * PF
     * QFåã
     * RF¼Ã®
     * UFª
     * WFDy
     * XFNNM
     * POFJASDAQ
     * PPFJNX-PTS
     * XXFDæsê
     * test_equitySellOrderConfirm_marketCode_0099()<BR>
     */
    public void test_equitySellOrderConfirm_marketCode_0099() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "test_equitySellOrderConfirm_marketCode_0099()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[]{},
                new Long(333812512246L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(WEB3DateUtility.getDate("20080106", "yyyyMMdd").getTime()));

        WEB3EquityOrderHandler l_handler = new WEB3EquityOrderHandler();

        WEB3EquitySellConfirmRequest l_request = new WEB3EquitySellConfirmRequest();

        //id
        l_request.id = "1234";
        //sêR[h
        l_request.marketCode = "99";

        try
        {
            WEB3EquitySellConfirmResponse l_resultResponse = new WEB3EquitySellConfirmResponse();

            //æøÔRowðì¬
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("0");
            l_tradingTimeParams.setMarketCode("99");
            TestDBUtility.insertWithDelAndCommit(l_tradingTimeParams);

            //sêRowðì¬
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("99");
            
            TestDBUtility.insertWithDelAndCommit(l_marketParams);

            //Úq}X^[Rowðì¬
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);
            
            //sêpvt@@X
            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("equity.pts.market.div");
            l_marketPreferencesParams.setNameSerialNo(1);
            l_marketPreferencesParams.setValue("0");
            l_marketPreferencesParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            l_marketPreferencesParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            TestDBUtility.insertWithDelAndCommit(l_marketPreferencesParams);

            TestDBUtility.commit();
            l_resultResponse = l_handler.equitySellOrderConfirm(l_request);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00608, l_resultResponse.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * (®¶t®¹NGXg)<BR>
     * PTSsêÌê(sê.isPTSsê()==true<BR>
     * test_equityBuyOrderComplete_0001()<BR>
     */
    public void test_equityBuyOrderComplete_0001() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "test_equityBuyOrderComplete_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[]{},
                new Long(333812512246L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(WEB3DateUtility.getDate("20080107", "yyyyMMdd").getTime()));

        WEB3EquityOrderHandler l_handler = new WEB3EquityOrderHandler();

        WEB3EquityBuyCompleteRequest l_request = new WEB3EquityBuyCompleteRequest();

        //Á¿R[hproductCode
        l_request.productCode = "1234";
        //sêR[h
        l_request.marketCode = "11";
        //ûÀæªtaxType
        l_request.taxType = "0";
        //æøæªtradingType
        l_request.tradingType = "1";

        try
        {
            WEB3EquityBuyCompleteResponse l_resultResponse = new WEB3EquityBuyCompleteResponse();

            //æøÔRowðì¬
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("5");
            l_tradingTimeParams.setMarketCode("11");
            TestDBUtility.insertWithDelAndCommit(l_tradingTimeParams);

            //sêRowðì¬
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("11");

            TestDBUtility.insertWithDelAndCommit(l_marketParams);

            //Úq}X^[Rowðì¬
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();

            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);
            
            //sêpvt@@X
            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("equity.pts.market.div");
            l_marketPreferencesParams.setNameSerialNo(1);
            l_marketPreferencesParams.setValue("1");
            l_marketPreferencesParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            l_marketPreferencesParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            TestDBUtility.insertWithDelAndCommit(l_marketPreferencesParams);

            l_resultResponse = l_handler.equityBuyOrderComplete(l_request);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00126, l_resultResponse.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * (®¶t®¹NGXg)<BR>
     * unregisterService<BR>
     * test_equityBuyOrderComplete_0002()<BR>
     */
    public void test_equityBuyOrderComplete_0002()
    {
        final String STR_METHOD_NAME = "test_equityBuyOrderComplete_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[]{},
                new Long(333812512246L));

        WEB3EquityOrderHandler l_handler = new WEB3EquityOrderHandler();
        
        WEB3EquityBuyCompleteRequest l_request = new WEB3EquityBuyCompleteRequest();

        //Á¿R[hproductCode
        l_request.productCode = "1234";
        //sêR[h
        l_request.marketCode = "11";
        //ûÀæªtaxType
        l_request.taxType = "0";
        //æøæªtradingType
        l_request.tradingType = "1";
        
        try
        {
            WEB3EquityBuyCompleteResponse l_resultResponse = new WEB3EquityBuyCompleteResponse();

            //æøÔRowðì¬
//            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
//            l_tradingTimeParams.setTradingTimeType("01");
//            l_tradingTimeParams.setBizDateType("5");
//            l_tradingTimeParams.setMarketCode("11");
            
//            TestDBUtility.insertWithDelAndCommit(l_tradingTimeParams);
            
            //sêRowðì¬
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("11");
            
            TestDBUtility.insertWithDelAndCommit(l_marketParams);

            //Úq}X^[Rowðì¬
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();

            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);

            //sêpvt@@X
            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("equity.pts.market.div");
            l_marketPreferencesParams.setNameSerialNo(1);
            l_marketPreferencesParams.setValue("1");
            l_marketPreferencesParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            l_marketPreferencesParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            TestDBUtility.insertWithDelAndCommit(l_marketPreferencesParams);

            l_service = (WEB3EquityPTSOrderService) Services.getService(WEB3EquityPTSOrderService.class);
            Services.unregisterService(WEB3EquityPTSOrderService.class);

            l_resultResponse = l_handler.equityBuyOrderComplete(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_resultResponse.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3EquityPTSOrderService.class, l_service);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * (®¶t®¹NGXg)<BR>
     * usêR[hªnullvÌáOðX[·éB<BR>
     * test_equityBuyOrderComplete_0003()<BR>
     */
    public void test_equityBuyOrderComplete_0003() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "test_equityBuyOrderComplete_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3EquityOrderHandler l_handler = new WEB3EquityOrderHandler();

        WEB3EquityBuyCompleteRequest l_request = new WEB3EquityBuyCompleteRequest();

        //sêR[h
        l_request.productCode = null;
        try
        {
            WEB3EquityBuyCompleteResponse l_resultResponse = new WEB3EquityBuyCompleteResponse();

            l_resultResponse = l_handler.equityBuyOrderComplete(l_request);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00443, l_resultResponse.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * (®¶t®¹NGXg)<BR>
     * usêR[hª¢è`ÌlvÌáOðX[·éB<BR>
     * test_equityBuyOrderComplete_0004()<BR>
     */
    public void test_equityBuyOrderComplete_0004() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "test_equityBuyOrderComplete_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3EquityOrderHandler l_handler = new WEB3EquityOrderHandler();

        WEB3EquityBuyCompleteRequest l_request = new WEB3EquityBuyCompleteRequest();

        //sêR[h
        l_request.marketCode = "12";
        try
        {
            WEB3EquityBuyCompleteResponse l_resultResponse = new WEB3EquityBuyCompleteResponse();

            l_resultResponse = l_handler.equityBuyOrderComplete(l_request);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00608, l_resultResponse.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * (®¶t®¹NGXg)<BR>
     * PTSsêÌê(sê.isPTSsê()==false<BR>
     * PF
     * QFåã
     * RF¼Ã®
     * UFª
     * WFDy
     * XFNNM
     * POFJASDAQ
     * PPFJNX-PTS
     * XXFDæsê
     * test_equityBuyOrderComplete_marketCode_0001()<BR>
     */
    public void test_equityBuyOrderComplete_marketCode_0001() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "test_equityBuyOrderComplete_marketCode_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[]{},
                new Long(333812512246L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(WEB3DateUtility.getDate("20080106", "yyyyMMdd").getTime()));

        WEB3EquityOrderHandler l_handler = new WEB3EquityOrderHandler();

        WEB3EquityBuyCompleteRequest l_request = new WEB3EquityBuyCompleteRequest();

        //Á¿R[hproductCode
        l_request.productCode = "1234";
        //sêR[h
        l_request.marketCode = "1";
        //ûÀæªtaxType
        l_request.taxType = "0";
        //æøæªtradingType
        l_request.tradingType = "1";

        try
        {
            WEB3EquityBuyCompleteResponse l_resultResponse = new WEB3EquityBuyCompleteResponse();

            //æøÔRowðì¬
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("0");
            l_tradingTimeParams.setMarketCode("1");
            l_tradingTimeParams.setStartTime("000001");
            TestDBUtility.insertWithDelAndCommit(l_tradingTimeParams);

            //sêRowðì¬
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("1");

            TestDBUtility.insertWithDelAndCommit(l_marketParams);

            //Úq}X^[Rowðì¬
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();

            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);

            //sêpvt@@X
            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("equity.pts.market.div");
            l_marketPreferencesParams.setNameSerialNo(1);
            l_marketPreferencesParams.setValue("0");
            l_marketPreferencesParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            l_marketPreferencesParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            TestDBUtility.insertWithDelAndCommit(l_marketPreferencesParams);

            l_resultResponse = l_handler.equityBuyOrderComplete(l_request);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00126, l_resultResponse.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * (®¶t®¹NGXg)<BR>
     * PTSsêÌê(sê.isPTSsê()==false<BR>
     * PF
     * QFåã
     * RF¼Ã®
     * UFª
     * WFDy
     * XFNNM
     * POFJASDAQ
     * PPFJNX-PTS
     * XXFDæsê
     * test_equityBuyOrderComplete_marketCode_0002()<BR>
     */
    public void test_equityBuyOrderComplete_marketCode_0002() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "test_equityBuyOrderComplete_marketCode_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[]{},
                new Long(333812512246L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(WEB3DateUtility.getDate("20080106", "yyyyMMdd").getTime()));

        WEB3EquityOrderHandler l_handler = new WEB3EquityOrderHandler();

        WEB3EquityBuyCompleteRequest l_request = new WEB3EquityBuyCompleteRequest();

        //Á¿R[hproductCode
        l_request.productCode = "1234";
        //sêR[h
        l_request.marketCode = "2";
        //ûÀæªtaxType
        l_request.taxType = "0";
        //æøæªtradingType
        l_request.tradingType = "1";

        try
        {
            WEB3EquityBuyCompleteResponse l_resultResponse = new WEB3EquityBuyCompleteResponse();

            //æøÔRowðì¬
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setMarketCode("2");
            l_tradingTimeParams.setBizDateType("0");
            TestDBUtility.insertWithDelAndCommit(l_tradingTimeParams);

            //sêRowðì¬
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("2");

            TestDBUtility.insertWithDelAndCommit(l_marketParams);

            //Úq}X^[Rowðì¬
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();

            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);

            //sêpvt@@X
            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("equity.pts.market.div");
            l_marketPreferencesParams.setNameSerialNo(1);
            l_marketPreferencesParams.setValue("0");
            l_marketPreferencesParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            l_marketPreferencesParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            TestDBUtility.insertWithDelAndCommit(l_marketPreferencesParams);

            l_resultResponse = l_handler.equityBuyOrderComplete(l_request);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00126, l_resultResponse.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * (®¶t®¹NGXg)<BR>
     * PTSsêÌê(sê.isPTSsê()==false<BR>
     * PF
     * QFåã
     * RF¼Ã®
     * UFª
     * WFDy
     * XFNNM
     * POFJASDAQ
     * PPFJNX-PTS
     * XXFDæsê
     * test_equityBuyOrderComplete_marketCode_0003()<BR>
     */
    public void test_equityBuyOrderComplete_marketCode_0003() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "test_equityBuyOrderComplete_marketCode_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[]{},
                new Long(333812512246L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(WEB3DateUtility.getDate("20080106", "yyyyMMdd").getTime()));

        WEB3EquityOrderHandler l_handler = new WEB3EquityOrderHandler();

        WEB3EquityBuyCompleteRequest l_request = new WEB3EquityBuyCompleteRequest();

        //Á¿R[hproductCode
        l_request.productCode = "1234";
        //sêR[h
        l_request.marketCode = "3";
        //ûÀæªtaxType
        l_request.taxType = "0";
        //æøæªtradingType
        l_request.tradingType = "1";

        try
        {
            WEB3EquityBuyCompleteResponse l_resultResponse = new WEB3EquityBuyCompleteResponse();

            //æøÔRowðì¬
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("0");
            l_tradingTimeParams.setMarketCode("3");
            TestDBUtility.insertWithDelAndCommit(l_tradingTimeParams);

            //sêRowðì¬
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("3");

            TestDBUtility.insertWithDelAndCommit(l_marketParams);

            //Úq}X^[Rowðì¬
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();

            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);

            //sêpvt@@X
            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("equity.pts.market.div");
            l_marketPreferencesParams.setNameSerialNo(1);
            l_marketPreferencesParams.setValue("0");
            l_marketPreferencesParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            l_marketPreferencesParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            TestDBUtility.insertWithDelAndCommit(l_marketPreferencesParams);

            l_resultResponse = l_handler.equityBuyOrderComplete(l_request);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00126, l_resultResponse.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * (®¶t®¹NGXg)<BR>
     * PTSsêÌê(sê.isPTSsê()==false<BR>
     * PF
     * QFåã
     * RF¼Ã®
     * UFª
     * WFDy
     * XFNNM
     * POFJASDAQ
     * PPFJNX-PTS
     * XXFDæsê
     * test_equityBuyOrderComplete_marketCode_0006()<BR>
     */
    public void test_equityBuyOrderComplete_marketCode_0006() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "test_equityBuyOrderComplete_marketCode_0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[]{},
                new Long(333812512246L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(WEB3DateUtility.getDate("20080106", "yyyyMMdd").getTime()));

        WEB3EquityOrderHandler l_handler = new WEB3EquityOrderHandler();

        WEB3EquityBuyCompleteRequest l_request = new WEB3EquityBuyCompleteRequest();

        //Á¿R[hproductCode
        l_request.productCode = "1234";
        //sêR[h
        l_request.marketCode = "6";
        //ûÀæªtaxType
        l_request.taxType = "0";
        //æøæªtradingType
        l_request.tradingType = "1";

        try
        {
            WEB3EquityBuyCompleteResponse l_resultResponse = new WEB3EquityBuyCompleteResponse();

            //æøÔRowðì¬
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("0");
            l_tradingTimeParams.setMarketCode("6");
            TestDBUtility.insertWithDelAndCommit(l_tradingTimeParams);

            //sêRowðì¬
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("6");

            TestDBUtility.insertWithDelAndCommit(l_marketParams);

            //Úq}X^[Rowðì¬
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();

            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);

            //sêpvt@@X
            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("equity.pts.market.div");
            l_marketPreferencesParams.setNameSerialNo(1);
            l_marketPreferencesParams.setValue("0");
            l_marketPreferencesParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            l_marketPreferencesParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            TestDBUtility.insertWithDelAndCommit(l_marketPreferencesParams);

            l_resultResponse = l_handler.equityBuyOrderComplete(l_request);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00126, l_resultResponse.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * (®¶t®¹NGXg)<BR>
     * PTSsêÌê(sê.isPTSsê()==false<BR>
     * PF
     * QFåã
     * RF¼Ã®
     * UFª
     * WFDy
     * XFNNM
     * POFJASDAQ
     * PPFJNX-PTS
     * XXFDæsê
     * test_equityBuyOrderComplete_marketCode_0009()<BR>
     */
    public void test_equityBuyOrderComplete_marketCode_0009() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "test_equityBuyOrderComplete_marketCode_0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[]{},
                new Long(333812512246L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(WEB3DateUtility.getDate("20080106", "yyyyMMdd").getTime()));

        WEB3EquityOrderHandler l_handler = new WEB3EquityOrderHandler();

        WEB3EquityBuyCompleteRequest l_request = new WEB3EquityBuyCompleteRequest();

        //Á¿R[hproductCode
        l_request.productCode = "1234";
        //sêR[h
        l_request.marketCode = "9";
        //ûÀæªtaxType
        l_request.taxType = "0";
        //æøæªtradingType
        l_request.tradingType = "1";

        try
        {
            WEB3EquityBuyCompleteResponse l_resultResponse = new WEB3EquityBuyCompleteResponse();

            //æøÔRowðì¬
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("0");
            l_tradingTimeParams.setMarketCode("9");

            TestDBUtility.insertWithDelAndCommit(l_tradingTimeParams);

            //sêRowðì¬
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("9");

            TestDBUtility.insertWithDelAndCommit(l_marketParams);

            //Úq}X^[Rowðì¬
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();

            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);

            //sêpvt@@X
            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("equity.pts.market.div");
            l_marketPreferencesParams.setNameSerialNo(1);
            l_marketPreferencesParams.setValue("0");
            l_marketPreferencesParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            l_marketPreferencesParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            TestDBUtility.insertWithDelAndCommit(l_marketPreferencesParams);

            l_resultResponse = l_handler.equityBuyOrderComplete(l_request);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00126, l_resultResponse.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * (®¶t®¹NGXg)<BR>
     * PTSsêÌê(sê.isPTSsê()==false<BR>
     * PF
     * QFåã
     * RF¼Ã®
     * UFª
     * WFDy
     * XFNNM
     * POFJASDAQ
     * PPFJNX-PTS
     * XXFDæsê
     * test_equityBuyOrderComplete_marketCode_0010()<BR>
     */
    public void test_equityBuyOrderComplete_marketCode_0010() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "test_equityBuyOrderComplete_marketCode_0010()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[]{},
                new Long(333812512246L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(WEB3DateUtility.getDate("20080106", "yyyyMMdd").getTime()));

        WEB3EquityOrderHandler l_handler = new WEB3EquityOrderHandler();

        WEB3EquityBuyCompleteRequest l_request = new WEB3EquityBuyCompleteRequest();

        //Á¿R[hproductCode
        l_request.productCode = "1234";
        //sêR[h
        l_request.marketCode = "10";
        //ûÀæªtaxType
        l_request.taxType = "0";
        //æøæªtradingType
        l_request.tradingType = "1";

        try
        {
            WEB3EquityBuyCompleteResponse l_resultResponse = new WEB3EquityBuyCompleteResponse();

            //æøÔRowðì¬
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("0");
            l_tradingTimeParams.setMarketCode("10");
            TestDBUtility.insertWithDelAndCommit(l_tradingTimeParams);

            //sêRowðì¬
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("10");

            TestDBUtility.insertWithDelAndCommit(l_marketParams);

            //Úq}X^[Rowðì¬
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();

            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);

            //sêpvt@@X
            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("equity.pts.market.div");
            l_marketPreferencesParams.setNameSerialNo(1);
            l_marketPreferencesParams.setValue("0");
            l_marketPreferencesParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            l_marketPreferencesParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            TestDBUtility.insertWithDelAndCommit(l_marketPreferencesParams);

            l_resultResponse = l_handler.equityBuyOrderComplete(l_request);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00126, l_resultResponse.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * (®¶t®¹NGXg)<BR>
     * PTSsêÌê(sê.isPTSsê()==false<BR>
     * PF
     * QFåã
     * RF¼Ã®
     * UFª
     * WFDy
     * XFNNM
     * POFJASDAQ
     * PPFJNX-PTS
     * XXFDæsê
     * test_equityBuyOrderComplete_marketCode_0099()<BR>
     */
    public void test_equityBuyOrderComplete_marketCode_0099() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "test_equityBuyOrderComplete_marketCode_0099()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[]{},
                new Long(333812512246L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(WEB3DateUtility.getDate("20080106", "yyyyMMdd").getTime()));

        WEB3EquityOrderHandler l_handler = new WEB3EquityOrderHandler();

        WEB3EquityBuyCompleteRequest l_request = new WEB3EquityBuyCompleteRequest();

        //Á¿R[hproductCode
        l_request.productCode = "1234";
        //sêR[h
        l_request.marketCode = "99";
        //ûÀæªtaxType
        l_request.taxType = "0";
        //æøæªtradingType
        l_request.tradingType = "1";

        try
        {
            WEB3EquityBuyCompleteResponse l_resultResponse = new WEB3EquityBuyCompleteResponse();

            //æøÔRowðì¬
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setMarketCode("99");
            l_tradingTimeParams.setBizDateType("0");
            TestDBUtility.insertWithDelAndCommit(l_tradingTimeParams);

            //sêRowðì¬
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("99");

            TestDBUtility.insertWithDelAndCommit(l_marketParams);

            //Úq}X^[Rowðì¬
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();

            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);

            //sêpvt@@X
            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("equity.pts.market.div");
            l_marketPreferencesParams.setNameSerialNo(1);
            l_marketPreferencesParams.setValue("0");
            l_marketPreferencesParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            l_marketPreferencesParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            TestDBUtility.insertWithDelAndCommit(l_marketPreferencesParams);

            l_resultResponse = l_handler.equityBuyOrderComplete(l_request);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00126, l_resultResponse.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * (®¶t®¹NGXg)<BR>
     * PTSsêÌê(sê.isPTSsê()==true<BR>
     * test_equitySellOrderComplete_0001()<BR>
     */
    public void test_equitySellOrderComplete_0001() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "test_equitySellOrderComplete_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[]{},
                new Long(333812512246L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(WEB3DateUtility.getDate("20080107", "yyyyMMdd").getTime()));

        WEB3EquityOrderHandler l_handler = new WEB3EquityOrderHandler();

        WEB3EquitySellCompleteRequest l_request = new WEB3EquitySellCompleteRequest();

        //id
        l_request.id = "1234";
        //sêR[h
        l_request.marketCode = "11";

        try
        {
            WEB3EquitySellCompleteResponse l_resultResponse = new WEB3EquitySellCompleteResponse();

            //æøÔRowðì¬
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("5");
            l_tradingTimeParams.setMarketCode("11");

            TestDBUtility.insertWithDelAndCommit(l_tradingTimeParams);

            //sêRowðì¬
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("11");

            TestDBUtility.insertWithDelAndCommit(l_marketParams);

            //Úq}X^[Rowðì¬
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();

            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);

            //sêpvt@@X
            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("equity.pts.market.div");
            l_marketPreferencesParams.setNameSerialNo(1);
            l_marketPreferencesParams.setValue("1");
            l_marketPreferencesParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            l_marketPreferencesParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            TestDBUtility.deleteAllAndCommit(l_marketPreferencesParams.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_marketPreferencesParams);

            l_resultResponse = l_handler.equitySellOrderComplete(l_request);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00126, l_resultResponse.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * (®¶t®¹NGXg)<BR>
     * unregisterService<BR>
     * test_equitySellOrderComplete_0002()<BR>
     */
    public void test_equitySellOrderComplete_0002()
    {
        final String STR_METHOD_NAME = "test_equitySellOrderComplete_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[]{},
                new Long(333812512246L));

        WEB3EquityOrderHandler l_handler = new WEB3EquityOrderHandler();

        WEB3EquitySellCompleteRequest l_request = new WEB3EquitySellCompleteRequest();

        //id
        l_request.id = "1234";
        //sêR[h
        l_request.marketCode = "11";

        try
        {
            WEB3EquitySellCompleteResponse l_resultResponse = new WEB3EquitySellCompleteResponse();

            //æøÔRowðì¬
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("5");
            l_tradingTimeParams.setMarketCode("11");

            TestDBUtility.insertWithDelAndCommit(l_tradingTimeParams);

            //sêRowðì¬
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("11");

            TestDBUtility.insertWithDelAndCommit(l_marketParams);

            //Úq}X^[Rowðì¬
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();

            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);

            //sêpvt@@X
            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("equity.pts.market.div");
            l_marketPreferencesParams.setNameSerialNo(1);
            l_marketPreferencesParams.setValue("1");
            l_marketPreferencesParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            l_marketPreferencesParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            TestDBUtility.insertWithDelAndCommit(l_marketPreferencesParams);

            l_service = (WEB3EquityPTSOrderService) Services.getService(WEB3EquityPTSOrderService.class);
            Services.unregisterService(WEB3EquityPTSOrderService.class);

            l_resultResponse = l_handler.equitySellOrderComplete(l_request);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002, l_resultResponse.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            Services.registerService(WEB3EquityPTSOrderService.class, l_service);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * (®¶t®¹NGXg)<BR>
     * usêR[hªnullvÌáOðX[·éB<BR>
     * test_equitySellOrderComplete_0003()<BR>
     */
    public void test_equitySellOrderComplete_0003() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "test_equitySellOrderComplete_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3EquityOrderHandler l_handler = new WEB3EquityOrderHandler();

        WEB3EquitySellCompleteRequest l_request = new WEB3EquitySellCompleteRequest();
        //id
        l_request.id = "1234";
        //sêR[h
        l_request.marketCode = null;
        try
        {
            WEB3EquitySellCompleteResponse l_resultResponse = new WEB3EquitySellCompleteResponse();

            l_resultResponse = l_handler.equitySellOrderComplete(l_request);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00443, l_resultResponse.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * (®¶t®¹NGXg)<BR>
     * usêR[hª¢è`ÌlvÌáOðX[·éB<BR>
     * test_equitySellOrderComplete_0004()<BR>
     */
    public void test_equitySellOrderComplete_0004() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "test_equitySellOrderComplete_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3EquityOrderHandler l_handler = new WEB3EquityOrderHandler();

        WEB3EquitySellCompleteRequest l_request = new WEB3EquitySellCompleteRequest();

        //sêR[h
        l_request.marketCode = "12";
        try
        {
            WEB3EquitySellCompleteResponse l_resultResponse = new WEB3EquitySellCompleteResponse();

            l_resultResponse = l_handler.equitySellOrderComplete(l_request);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00608, l_resultResponse.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * (®¶t®¹NGXg)<BR>
     * PTSsêÌê(sê.isPTSsê()==false<BR>
     * PF
     * QFåã
     * RF¼Ã®
     * UFª
     * WFDy
     * XFNNM
     * POFJASDAQ
     * PPFJNX-PTS
     * XXFDæsê
     * test_equitySellOrderComplete_marketCode_0001()<BR>
     */
    public void test_equitySellOrderComplete_marketCode_0001() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "test_equitySellOrderComplete_marketCode_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[]{},
                new Long(333812512246L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(WEB3DateUtility.getDate("20080106", "yyyyMMdd").getTime()));

        WEB3EquityOrderHandler l_handler = new WEB3EquityOrderHandler();

        WEB3EquitySellCompleteRequest l_request = new WEB3EquitySellCompleteRequest();

        //id
        l_request.id = "1234";
        //sêR[h
        l_request.marketCode = "1";

        try
        {
            WEB3EquitySellCompleteResponse l_resultResponse = new WEB3EquitySellCompleteResponse();

            //æøÔRowðì¬
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("0");
            l_tradingTimeParams.setMarketCode("1");
            TestDBUtility.insertWithDelAndCommit(l_tradingTimeParams);

            //sêRowðì¬
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("1");

            TestDBUtility.insertWithDelAndCommit(l_marketParams);

            //Úq}X^[Rowðì¬
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();

            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);

            //sêpvt@@X
            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("equity.pts.market.div");
            l_marketPreferencesParams.setNameSerialNo(1);
            l_marketPreferencesParams.setValue("0");
            l_marketPreferencesParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            l_marketPreferencesParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            TestDBUtility.insertWithDelAndCommit(l_marketPreferencesParams);

            l_resultResponse = l_handler.equitySellOrderComplete(l_request);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00126, l_resultResponse.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * (®¶t®¹NGXg)<BR>
     * PTSsêÌê(sê.isPTSsê()==false<BR>
     * PF
     * QFåã
     * RF¼Ã®
     * UFª
     * WFDy
     * XFNNM
     * POFJASDAQ
     * PPFJNX-PTS
     * XXFDæsê
     * test_equitySellOrderComplete_marketCode_0002()<BR>
     */
    public void test_equitySellOrderComplete_marketCode_0002() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "test_equitySellOrderComplete_marketCode_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[]{},
                new Long(333812512246L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(WEB3DateUtility.getDate("20080106", "yyyyMMdd").getTime()));

        WEB3EquityOrderHandler l_handler = new WEB3EquityOrderHandler();

        WEB3EquitySellCompleteRequest l_request = new WEB3EquitySellCompleteRequest();

        //id
        l_request.id = "1234";
        //sêR[h
        l_request.marketCode = "2";

        try
        {
            WEB3EquitySellCompleteResponse l_resultResponse = new WEB3EquitySellCompleteResponse();

            //æøÔRowðì¬
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("0");
            l_tradingTimeParams.setMarketCode("2");
            TestDBUtility.insertWithDelAndCommit(l_tradingTimeParams);

            //sêRowðì¬
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("2");

            TestDBUtility.insertWithDelAndCommit(l_marketParams);

            //Úq}X^[Rowðì¬
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();

            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);

            //sêpvt@@X
            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("equity.pts.market.div");
            l_marketPreferencesParams.setNameSerialNo(1);
            l_marketPreferencesParams.setValue("0");
            l_marketPreferencesParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            l_marketPreferencesParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            TestDBUtility.insertWithDelAndCommit(l_marketPreferencesParams);

            l_resultResponse = l_handler.equitySellOrderComplete(l_request);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00126, l_resultResponse.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * (®¶t®¹NGXg)<BR>
     * PTSsêÌê(sê.isPTSsê()==false<BR>
     * PF
     * QFåã
     * RF¼Ã®
     * UFª
     * WFDy
     * XFNNM
     * POFJASDAQ
     * PPFJNX-PTS
     * XXFDæsê
     * test_equitySellOrderComplete_marketCode_0003()<BR>
     */
    public void test_equitySellOrderComplete_marketCode_0003() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "test_equitySellOrderComplete_marketCode_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[]{},
                new Long(333812512246L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(WEB3DateUtility.getDate("20080106", "yyyyMMdd").getTime()));

        WEB3EquityOrderHandler l_handler = new WEB3EquityOrderHandler();

        WEB3EquitySellCompleteRequest l_request = new WEB3EquitySellCompleteRequest();

        //id
        l_request.id = "1234";
        //sêR[h
        l_request.marketCode = "3";

        try
        {
            WEB3EquitySellCompleteResponse l_resultResponse = new WEB3EquitySellCompleteResponse();

            //æøÔRowðì¬
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("0");
            l_tradingTimeParams.setMarketCode("3");
            TestDBUtility.insertWithDelAndCommit(l_tradingTimeParams);

            //sêRowðì¬
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("3");

            TestDBUtility.insertWithDelAndCommit(l_marketParams);

            //Úq}X^[Rowðì¬
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();

            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);

            //sêpvt@@X
            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("equity.pts.market.div");
            l_marketPreferencesParams.setNameSerialNo(1);
            l_marketPreferencesParams.setValue("0");
            l_marketPreferencesParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            l_marketPreferencesParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            TestDBUtility.insertWithDelAndCommit(l_marketPreferencesParams);

            l_resultResponse = l_handler.equitySellOrderComplete(l_request);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00126, l_resultResponse.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * (®¶t®¹NGXg)<BR>
     * PTSsêÌê(sê.isPTSsê()==false<BR>
     * PF
     * QFåã
     * RF¼Ã®
     * UFª
     * WFDy
     * XFNNM
     * POFJASDAQ
     * PPFJNX-PTS
     * XXFDæsê
     * test_equitySellOrderComplete_marketCode_0006()<BR>
     */
    public void test_equitySellOrderComplete_marketCode_0006() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "test_equitySellOrderComplete_marketCode_0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[]{},
                new Long(333812512246L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(WEB3DateUtility.getDate("20080106", "yyyyMMdd").getTime()));

        WEB3EquityOrderHandler l_handler = new WEB3EquityOrderHandler();

        WEB3EquitySellCompleteRequest l_request = new WEB3EquitySellCompleteRequest();

        //id
        l_request.id = "1234";
        //sêR[h
        l_request.marketCode = "6";

        try
        {
            WEB3EquitySellCompleteResponse l_resultResponse = new WEB3EquitySellCompleteResponse();

            //æøÔRowðì¬
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setMarketCode("6");
            l_tradingTimeParams.setBizDateType("0");
            TestDBUtility.insertWithDelAndCommit(l_tradingTimeParams);

            //sêRowðì¬
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("6");

            TestDBUtility.insertWithDelAndCommit(l_marketParams);

            //Úq}X^[Rowðì¬
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();

            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);

            //sêpvt@@X
            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("equity.pts.market.div");
            l_marketPreferencesParams.setNameSerialNo(1);
            l_marketPreferencesParams.setValue("0");
            l_marketPreferencesParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            l_marketPreferencesParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            TestDBUtility.insertWithDelAndCommit(l_marketPreferencesParams);

            l_resultResponse = l_handler.equitySellOrderComplete(l_request);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00126, l_resultResponse.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * (®¶t®¹NGXg)<BR>
     * PTSsêÌê(sê.isPTSsê()==false<BR>
     * PF
     * QFåã
     * RF¼Ã®
     * UFª
     * WFDy
     * XFNNM
     * POFJASDAQ
     * PPFJNX-PTS
     * XXFDæsê
     * test_equitySellOrderComplete_marketCode_0009()<BR>
     */
    public void test_equitySellOrderComplete_marketCode_0009() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "test_equitySellOrderComplete_marketCode_0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[]{},
                new Long(333812512246L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(WEB3DateUtility.getDate("20080106", "yyyyMMdd").getTime()));

        WEB3EquityOrderHandler l_handler = new WEB3EquityOrderHandler();

        WEB3EquitySellCompleteRequest l_request = new WEB3EquitySellCompleteRequest();

        //id
        l_request.id = "1234";
        //sêR[h
        l_request.marketCode = "9";

        try
        {
            WEB3EquitySellCompleteResponse l_resultResponse = new WEB3EquitySellCompleteResponse();

            //æøÔRowðì¬
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("0");
            l_tradingTimeParams.setMarketCode("9");

            TestDBUtility.insertWithDelAndCommit(l_tradingTimeParams);

            //sêRowðì¬
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("9");

            TestDBUtility.insertWithDelAndCommit(l_marketParams);

            //Úq}X^[Rowðì¬
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();

            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);

            //sêpvt@@X
            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("equity.pts.market.div");
            l_marketPreferencesParams.setNameSerialNo(1);
            l_marketPreferencesParams.setValue("0");
            l_marketPreferencesParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            l_marketPreferencesParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            TestDBUtility.insertWithDelAndCommit(l_marketPreferencesParams);

            l_resultResponse = l_handler.equitySellOrderComplete(l_request);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00126, l_resultResponse.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * (®¶t®¹NGXg)<BR>
     * PTSsêÌê(sê.isPTSsê()==false<BR>
     * PF
     * QFåã
     * RF¼Ã®
     * UFª
     * WFDy
     * XFNNM
     * POFJASDAQ
     * PPFJNX-PTS
     * XXFDæsê
     * test_equitySellOrderComplete_marketCode_0010()<BR>
     */
    public void test_equitySellOrderComplete_marketCode_0010() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "test_equitySellOrderComplete_marketCode_0010()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[]{},
                new Long(333812512246L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(WEB3DateUtility.getDate("20080106", "yyyyMMdd").getTime()));

        WEB3EquityOrderHandler l_handler = new WEB3EquityOrderHandler();

        WEB3EquitySellCompleteRequest l_request = new WEB3EquitySellCompleteRequest();

        //id
        l_request.id = "1234";
        //sêR[h
        l_request.marketCode = "10";

        try
        {
            WEB3EquitySellCompleteResponse l_resultResponse = new WEB3EquitySellCompleteResponse();

            //æøÔRowðì¬
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("0");
            l_tradingTimeParams.setMarketCode("10");
            TestDBUtility.insertWithDelAndCommit(l_tradingTimeParams);

            //sêRowðì¬
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("10");

            TestDBUtility.insertWithDelAndCommit(l_marketParams);

            //Úq}X^[Rowðì¬
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();

            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);

            //sêpvt@@X
            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("equity.pts.market.div");
            l_marketPreferencesParams.setNameSerialNo(1);
            l_marketPreferencesParams.setValue("0");
            l_marketPreferencesParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            l_marketPreferencesParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            TestDBUtility.insertWithDelAndCommit(l_marketPreferencesParams);

            l_resultResponse = l_handler.equitySellOrderComplete(l_request);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00126, l_resultResponse.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * (®¶t®¹NGXg)<BR>
     * PTSsêÌê(sê.isPTSsê()==false<BR>
     * PF
     * QFåã
     * RF¼Ã®
     * UFª
     * WFDy
     * XFNNM
     * POFJASDAQ
     * PPFJNX-PTS
     * XXFDæsê
     * test_equitySellOrderComplete_marketCode_0099()<BR>
     */
    public void test_equitySellOrderComplete_marketCode_0099() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "test_equitySellOrderComplete_marketCode_0099()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[]{},
                new Long(333812512246L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(WEB3DateUtility.getDate("20080106", "yyyyMMdd").getTime()));

        WEB3EquityOrderHandler l_handler = new WEB3EquityOrderHandler();

        WEB3EquitySellCompleteRequest l_request = new WEB3EquitySellCompleteRequest();

        //id
        l_request.id = "1234";
        //sêR[h
        l_request.marketCode = "99";

        try
        {
            WEB3EquitySellCompleteResponse l_resultResponse = new WEB3EquitySellCompleteResponse();

            //æøÔRowðì¬
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setMarketCode("99");
            l_tradingTimeParams.setBizDateType("0");
            TestDBUtility.insertWithDelAndCommit(l_tradingTimeParams);

            //sêRowðì¬
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("99");

            TestDBUtility.insertWithDelAndCommit(l_marketParams);

            //Úq}X^[Rowðì¬
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();

            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);

            //sêpvt@@X
            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(3303L);
            l_marketPreferencesParams.setName("equity.pts.market.div");
            l_marketPreferencesParams.setNameSerialNo(1);
            l_marketPreferencesParams.setValue("0");
            l_marketPreferencesParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            l_marketPreferencesParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070225","yyyyMMdd"));
            TestDBUtility.insertWithDelAndCommit(l_marketPreferencesParams);

            l_resultResponse = l_handler.equitySellOrderComplete(l_request);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00608, l_resultResponse.errorInfo);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

}
@
