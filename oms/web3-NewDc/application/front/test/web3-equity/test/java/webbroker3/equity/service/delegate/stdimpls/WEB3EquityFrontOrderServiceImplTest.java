head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.54.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityFrontOrderServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式発注サービスImplTest(WEB3EquityFrontOrderServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/23 張騰宇 モデル 1243 1265
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeOrderUnitImpl;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.equity.service.delegate.WEB3EquityFrontOrderService;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityFrontOrderServiceImplTest extends TestBaseForMock
{
    /**
     * (ログ出力ユーティリティ)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityFrontOrderServiceImplTest.class);

    public WEB3EquityFrontOrderServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        Calendar ca =  Calendar.getInstance();
        ca.set(2007,6-1,12);// 注意月?要減1
        Date date = ca.getTime();
        Timestamp st = new Timestamp(date.getTime());
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.tc." + 
                "gentrade.stdimpls.TradingSystemImpl", 
            "getSystemTimestamp",
            new Class[]{}, 
            st);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",st);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /*
     * Test method for 'webbroker3.equity.service.delegate.stdimpls.WEB3EquityFrontOrderServiceImpl.getPTSChangeOrderRev(EqTypeOrderUnit)'
     */
    public void testGetPTSChangeOrderRevCase0()
    {
        final String STR_METHOD_NAME = "testGetPTSChangeOrderRevCase0()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams1 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams1.setOrderUnitId(3304148080001L);
            l_eqtypeOrderUnitParams1.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams1.setRequestType(WEB3RequestTypeDef.DEFAULT);
            l_eqtypeOrderUnitParams1.setTraderId(3338111121L);
            l_eqtypeOrderUnitParams1.setOrderRev("00");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams1);
            
            Timestamp l_tsOrderAcceptTime = new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime());
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(l_tsOrderAcceptTime, "1");
            //WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_tsOrderAcceptTime);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParam = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParam.setInstitutionCode("0D");
            l_tradingTimeParam.setBranchCode("123");
            l_tradingTimeParam.setMarketCode("N1");
            l_tradingTimeParam.setTradingTimeType("01");
            l_tradingTimeParam.setProductCode("0");
            l_tradingTimeParam.setBizDateType("1");
            l_tradingTimeParam.setSubmitMarketTrigger("1");
            TestDBUtility.insertWithDel(l_tradingTimeParam);
            
            EqTypeOrderUnit l_orderUnit = new EqTypeOrderUnitImpl(null, 3304148080001L);
            WEB3EquityFrontOrderService l_service =
                (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
            String l_strPTSChangOrderRev = l_service.getPTSChangeOrderRev(l_orderUnit);
            assertEquals("01", l_strPTSChangOrderRev);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //PTS取引時間管理.is市場開局時間帯( ) == true
    //引数の注文単位.注文Rev.に＋１した文字列を返却する
    public void testGetPTSChangeOrderRevCase1()
    {
        final String STR_METHOD_NAME = "testGetPTSChangeOrderRevCase1()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams1 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams1.setOrderUnitId(3304148080001L);
            l_eqtypeOrderUnitParams1.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams1.setRequestType(WEB3RequestTypeDef.DEFAULT);
            l_eqtypeOrderUnitParams1.setTraderId(3338111121L);
            l_eqtypeOrderUnitParams1.setOrderRev("02");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams1);
            
            Timestamp l_tsOrderAcceptTime = new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime());
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(l_tsOrderAcceptTime, "1");
            //WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_tsOrderAcceptTime);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParam = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParam.setInstitutionCode("0D");
            l_tradingTimeParam.setBranchCode("123");
            l_tradingTimeParam.setMarketCode("N1");
            l_tradingTimeParam.setTradingTimeType("01");
            l_tradingTimeParam.setProductCode("0");
            l_tradingTimeParam.setBizDateType("1");
            l_tradingTimeParam.setSubmitMarketTrigger("1");
            TestDBUtility.insertWithDel(l_tradingTimeParam);
            
            EqTypeOrderUnit l_orderUnit = new EqTypeOrderUnitImpl(null, 3304148080001L);
            WEB3EquityFrontOrderService l_service =
                (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
            String l_strPTSChangOrderRev = l_service.getPTSChangeOrderRev(l_orderUnit);
            assertEquals("03", l_strPTSChangOrderRev);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //PTS取引時間管理.is市場開局時間帯( ) == true
    //桁数を超える値となった場
    //「注文Rev.の値が最大桁数を超過」の例外をthrowする
    public void testGetPTSChangeOrderRevCase2()
    {
        final String STR_METHOD_NAME = "testGetPTSChangeOrderRevCase2()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams1 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams1.setOrderUnitId(3304148080001L);
            l_eqtypeOrderUnitParams1.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams1.setRequestType(WEB3RequestTypeDef.DEFAULT);
            l_eqtypeOrderUnitParams1.setTraderId(3338111121L);
            l_eqtypeOrderUnitParams1.setOrderRev("99");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams1);
            
            EqTypeOrderUnit l_orderUnit = new EqTypeOrderUnitImpl(null, 3304148080001L);
            WEB3EquityFrontOrderService l_service =
                (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
            l_service.getPTSChangeOrderRev(l_orderUnit);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_02185);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    //PTS取引時間管理.is市場開局時間帯( ) == false
    //引数の注文単位.注文Rev.をそのまま返却する
    public void testGetPTSChangeOrderRevCase3()
    {
        final String STR_METHOD_NAME = "testGetPTSChangeOrderRevCase3()";
        log.entering(STR_METHOD_NAME);

        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(WEB3DateUtility.getDate("20071223","yyyyMMdd").getTime()));
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams1 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams1.setOrderUnitId(3304148080001L);
            l_eqtypeOrderUnitParams1.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams1.setRequestType(WEB3RequestTypeDef.DEFAULT);
            l_eqtypeOrderUnitParams1.setTraderId(3338111121L);
            l_eqtypeOrderUnitParams1.setOrderRev("02");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams1);

            EqTypeOrderUnit l_orderUnit = new EqTypeOrderUnitImpl(null, 3304148080001L);
            WEB3EquityFrontOrderService l_service =
                (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
            String l_strPTSChangOrderRev = l_service.getPTSChangeOrderRev(l_orderUnit);
            assertEquals("02", l_strPTSChangOrderRev);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //引数の注文単位 == null
    public void testGetPTSChangeOrderRevCase4()
    {
        final String STR_METHOD_NAME = "testGetPTSChangeOrderRevCase4()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3EquityFrontOrderService l_service =
                (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
            l_service.getPTSChangeOrderRev(null);
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80017);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //testgetFrontOrderSystemCode
    public void testGetFrontOrderSystemCode_C001()
    {
        final String STR_METHOD_NAME = "testGetFrontOrderSystemCode_C001";
        log.entering(STR_METHOD_NAME);
        
        WEB3EquityFrontOrderServiceImpl l_impl = new WEB3EquityFrontOrderServiceImpl();
        String l_strMarketCode = "1";
        String l_strOpenOtcDiv = "2";
        String l_strReurn =
            l_impl.getFrontOrderSystemCode(l_strMarketCode, l_strOpenOtcDiv);
        assertEquals("1", l_strReurn);
        log.exiting(STR_METHOD_NAME);
    }
    
    //testgetFrontOrderSystemCode
    public void testGetFrontOrderSystemCode_C002()
    {
        final String STR_METHOD_NAME = "testGetFrontOrderSystemCode_C002";
        log.entering(STR_METHOD_NAME);
        
        WEB3EquityFrontOrderServiceImpl l_impl = new WEB3EquityFrontOrderServiceImpl();
        String l_strMarketCode = "3";
        String l_strOpenOtcDiv = "2";
        String l_strReurn =
            l_impl.getFrontOrderSystemCode(l_strMarketCode, l_strOpenOtcDiv);
        assertEquals("1", l_strReurn);
        log.exiting(STR_METHOD_NAME);
    }
    
    //getフロント発注システム区分
    // １）　@引数の市場コード == （"東証" or "名証"）の場合は
    //　@　@"東証、名証"を返却する。
    //市場コード == 東証 1
    //返回1:東証（株式のみ)、名証（株式のみ)
    public void testGetFrontOrderSystemCode_Case1()
    {
        final String STR_METHOD_NAME = "testGetFrontOrderSystemCode_Case1()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3EquityFrontOrderService l_service =
                (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
            String l_strFrontOrderSystemCode = l_service.getFrontOrderSystemCode("1","");
            assertEquals("1", l_strFrontOrderSystemCode);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //getフロント発注システム区分
    // １）　@引数の市場コード == （"東証" or "名証"）の場合は
    //　@　@"東証、名証"を返却する。
    //市場コード == 名証 3
    //返回1:東証（株式のみ)、名証（株式のみ)
    public void testGetFrontOrderSystemCode_Case2()
    {
        final String STR_METHOD_NAME = "testGetFrontOrderSystemCode_Case2()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3EquityFrontOrderService l_service =
                (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
            String l_strFrontOrderSystemCode = l_service.getFrontOrderSystemCode("3","");
            assertEquals("1", l_strFrontOrderSystemCode);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    //getフロント発注システム区分
    // 引数の市場コード == "NNM"の場合は、"ヘラクレス"を返却する。
    //市場コード == NNM 9
    //返回4:ヘラクレス
    public void testGetFrontOrderSystemCode_Case3()
    {
        final String STR_METHOD_NAME = "testGetFrontOrderSystemCode_Case3()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3EquityFrontOrderService l_service =
                (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
            String l_strFrontOrderSystemCode = l_service.getFrontOrderSystemCode("9","");
            assertEquals("4", l_strFrontOrderSystemCode);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //getフロント発注システム区分
    //      ４）　@引数の市場コード == "JASDAQ"の場合、引数の店頭公開区分の値により
    // 　@　@以下の値を返却する。
    // 　@　@　@引数の店頭公開区分 != "マーケットメイク銘柄"であれば、"JASDAQ"。
    //市場コード == "JASDAQ"10
    //引数の店頭公開区分 != "マーケットメイク銘柄" 0
    //返回5：JASDAQ
    public void testGetFrontOrderSystemCode_Case4()
    {
        final String STR_METHOD_NAME = "testGetFrontOrderSystemCode_Case4()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3EquityFrontOrderService l_service =
                (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
            String l_strFrontOrderSystemCode = l_service.getFrontOrderSystemCode("10", "0");
            assertEquals("4", l_strFrontOrderSystemCode);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //getフロント発注システム区分
    //      ４）　@引数の市場コード == "JASDAQ"の場合、引数の店頭公開区分の値により
    // 　@　@以下の値を返却する。
    // 　@　@　@引数の店頭公開区分 == "マーケットメイク銘柄"であれば、"JASDAQ-MM"。
    //市場コード == "JASDAQ"10
    //引数の店頭公開区分 == "マーケットメイク銘柄" 3
    //返回3:JASDAQ-MM
    public void testGetFrontOrderSystemCode_Case5()
    {
        final String STR_METHOD_NAME = "testGetFrontOrderSystemCode_Case5()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3EquityFrontOrderService l_service =
                (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
            String l_strFrontOrderSystemCode = l_service.getFrontOrderSystemCode("9", "3");
            assertEquals("4", l_strFrontOrderSystemCode);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //getフロント発注取引所区分コード
    //      ４）　@引数の市場コード＝"9"（NNM）の場合
    // 　@　@"2"（大証）を返す。
    public void testGetFrontOrderExchangeCode_Case1()
    {
        final String STR_METHOD_NAME = "testGetFrontOrderExchangeCode_Case1()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3EquityFrontOrderService l_service =
                (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
            String l_strFrontOrderSystemCode = l_service.getFrontOrderExchangeCode("9");
            assertEquals("2", l_strFrontOrderSystemCode);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //getフロント発注取引所区分コード
    //      ４）　@引数の市場コード＝"10"（JASDAQ）の場合
    // 　@　@"2"（大証）を返す。
    public void testGetFrontOrderExchangeCode_Case2()
    {
        final String STR_METHOD_NAME = "testGetFrontOrderExchangeCode_Case2()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3EquityFrontOrderService l_service =
                (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
            String l_strFrontOrderSystemCode = l_service.getFrontOrderExchangeCode("10");
            assertEquals("2", l_strFrontOrderSystemCode);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //getフロント発注取引所区分コード
    //      ５）　@引数の市場コードが上記(1 2 3 9 10)以外の場合
    // 　@　@nullを返す。
    public void testGetFrontOrderExchangeCode_Case3()
    {
        final String STR_METHOD_NAME = "testGetFrontOrderExchangeCode_Case3()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3EquityFrontOrderService l_service =
                (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
            String l_strFrontOrderSystemCode = l_service.getFrontOrderExchangeCode("5");
            assertNull(l_strFrontOrderSystemCode);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //getフロント発注システム区分
    //引数の市場コード == "10"(JASDAQ)の場合は、
    //"4"(ヘラクレス)を返却する。
    public void testGetFrontOrderSystemCode_Case6()
    {
        final String STR_METHOD_NAME = "testGetFrontOrderSystemCode_Case6()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3EquityFrontOrderService l_service =
                (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
            String l_strFrontOrderSystemCode = l_service.getFrontOrderSystemCode("10","");
            assertEquals("4", l_strFrontOrderSystemCode);
        }
        catch (Exception l_ex)
		{
            log.error(l_ex.getMessage(), l_ex);
            fail();
		}
        log.exiting(STR_METHOD_NAME);
    }

    //getフロント発注システム区分
    // 引数の市場コード == "10"(JASDAQ)、引数の店頭公開区分の値に関わらず
    // "4"(ヘラクレス)を返却する。
    //引数の店頭公開区分 != "マーケットメイク銘柄" 0
    public void testGetFrontOrderSystemCode_Case7()
    {
        final String STR_METHOD_NAME = "testGetFrontOrderSystemCode_Case7";
        log.entering(STR_METHOD_NAME);
        
        try
		{
            WEB3EquityFrontOrderService l_service =
                (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
            String l_strFrontOrderSystemCode = l_service.getFrontOrderSystemCode("10", "0");
            assertEquals("4", l_strFrontOrderSystemCode);
		}
        catch (Exception l_ex)
		{
            log.error(l_ex.getMessage(), l_ex);
            fail();
		}
        log.exiting(STR_METHOD_NAME);
    }

    //getフロント発注システム区分
    // 引数の市場コード == "10"(JASDAQ)、引数の店頭公開区分の値に関わらず
    // "4"(ヘラクレス)を返却する。
    //引数の店頭公開区分 == "マーケットメイク銘柄" 3
    public void testGetFrontOrderSystemCode_Case8()
    {
        final String STR_METHOD_NAME = "testGetFrontOrderSystemCode_Case8";
        log.entering(STR_METHOD_NAME);
        
        try
		{
            WEB3EquityFrontOrderService l_service =
                (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
            String l_strFrontOrderSystemCode = l_service.getFrontOrderSystemCode("10", "3");
            assertEquals("4", l_strFrontOrderSystemCode);
		}
        catch (Exception l_ex)
		{
            log.error(l_ex.getMessage(), l_ex);
            fail();
		}
        log.exiting(STR_METHOD_NAME);
    }
}
@
