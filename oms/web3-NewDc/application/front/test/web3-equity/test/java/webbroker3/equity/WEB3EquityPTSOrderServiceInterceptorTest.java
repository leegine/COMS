head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.10.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityPTSOrderServiceInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (PTS)現物株式注文サービスインタセプタ
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/18 孟亞南 (中訊) 新規作成モデルNo.1215
*/
package webbroker3.equity;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;

import test.util.TestDBUtility;
import test.util.TestSpecialClassUtility;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.message.WEB3EquityBuyCompleteRequest;
import webbroker3.equity.message.WEB3EquityBuyConfirmRequest;
import webbroker3.equity.message.WEB3EquityCancelCompleteRequest;
import webbroker3.equity.message.WEB3EquitySellCompleteRequest;
import webbroker3.equity.message.WEB3EquitySellConfirmRequest;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityPTSOrderServiceInterceptorTest extends TestBaseForMock
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3EquityPTSOrderServiceInterceptorTest.class);

    WEB3EquityPTSOrderServiceInterceptor l_equityPTSOrderServiceInterceptor =
        new WEB3EquityPTSOrderServiceInterceptor();
    
    public WEB3EquityPTSOrderServiceInterceptorTest(String name)
    {
        super(name);
    }

    /**
     * onCall()
     * {現物株式注文取消完了リクエスト}
     * WEB3ErrorCatalog.SYSTEM_ERROR_80018
     */
    public void test_onCall_0001()
    {
        final String STR_METHOD_NAME = "test_onCall_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
             new Class[] {}, new Long(333812512246L));

        WEB3EquityCancelCompleteRequest l_cancelCompleteRequest = new WEB3EquityCancelCompleteRequest();
        Object[] l_serviceParams = {l_cancelCompleteRequest};
        try
        {
            // テーブルへデータをインサート
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            l_equityPTSOrderServiceInterceptor.onCall(null, l_serviceParams);
            fail();
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * onCall()
     * {現物株式買付注文確認リクエスト}
     * PTS取引時間管理.setTimestamp()をコールする。
     * WEB3ErrorCatalog.SYSTEM_ERROR_80005
     */
    public void test_onCall_0002()
    {
        final String STR_METHOD_NAME = "test_onCall_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
             new Class[] {}, new Long(333812512246L));

        WEB3EquityBuyConfirmRequest l_buyConfirmRequest = new WEB3EquityBuyConfirmRequest();
        l_buyConfirmRequest.marketCode = "SP";
        Object[] l_serviceParams = {l_buyConfirmRequest};
        try
        {
            // テーブルへデータをインサート
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            l_equityPTSOrderServiceInterceptor.onCall(null, l_serviceParams);
            fail();
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * onCall()
     * {現物株式買付注文確認リクエスト}
     * 正しい
     */
    public void test_onCall_0003()
    {
        final String STR_METHOD_NAME = "test_onCall_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
             new Class[] {}, new Long(333812512246L));

        WEB3EquityBuyConfirmRequest l_buyConfirmRequest = new WEB3EquityBuyConfirmRequest();
        l_buyConfirmRequest.marketCode = "SP";
        Object[] l_serviceParams = {l_buyConfirmRequest};
        try
        {
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
                    "web3.attributes.basetimestampfororderbizdate",
                    null);
                    
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "xblocks.gtl.attributes.systemtimestamp",
                    st);
            // テーブルへデータをインサート
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode("SP");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            l_equityPTSOrderServiceInterceptor.onCall(null, l_serviceParams);
            WEB3GentradeTradingClendarContext l_context =
                (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3EquityPTSTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);

            Timestamp l_processTime =
                (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(
                    "xblocks.gtl.attributes.systemtimestamp");
            assertEquals("20070612", "" + WEB3DateUtility.formatDate(l_processTime, "yyyyMMdd"));
            assertEquals("0D", l_context.getInstitutionCode());
            assertEquals("381", l_context.getBranchCode());
            assertEquals("SP", l_context.getMarketCode());
            assertEquals("01", l_context.getTradingTimeType());
            assertEquals("01", l_context.getOrderAcceptProduct());
            assertEquals("01", l_context.getOrderAcceptTransaction());
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * onCall()
     * {現物株式買付注文完了リクエスト}
     * 正しい
     */
    public void test_onCall_0004()
    {
        final String STR_METHOD_NAME = "test_onCall_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
             new Class[] {}, new Long(333812512246L));

        WEB3EquityBuyCompleteRequest l_buyCompleteRequest = new WEB3EquityBuyCompleteRequest();
        l_buyCompleteRequest.marketCode = "SP";
        Object[] l_serviceParams = {l_buyCompleteRequest};
        try
        {
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
                    "web3.attributes.basetimestampfororderbizdate",
                    null);
                    
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "xblocks.gtl.attributes.systemtimestamp",
                    st);
            
            // テーブルへデータをインサート
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode("SP");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            l_equityPTSOrderServiceInterceptor.onCall(null, l_serviceParams);
            WEB3GentradeTradingClendarContext l_context =
                (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3EquityPTSTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);

            Timestamp l_processTime =
                (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(
                    "xblocks.gtl.attributes.systemtimestamp");
            assertEquals("20070612", "" + WEB3DateUtility.formatDate(l_processTime, "yyyyMMdd"));
            assertEquals("0D", l_context.getInstitutionCode());
            assertEquals("381", l_context.getBranchCode());
            assertEquals("SP", l_context.getMarketCode());
            assertEquals("01", l_context.getTradingTimeType());
            assertEquals("01", l_context.getOrderAcceptProduct());
            assertEquals("01", l_context.getOrderAcceptTransaction());
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * onCall()
     * {現物株式売付注文完了リクエスト}
     * 正しい
     */
    public void test_onCall_0005()
    {
        final String STR_METHOD_NAME = "test_onCall_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
             new Class[] {}, new Long(333812512246L));

        WEB3EquitySellCompleteRequest l_sellCompleteRequest = new WEB3EquitySellCompleteRequest();
        l_sellCompleteRequest.marketCode = "SP";
        Object[] l_serviceParams = {l_sellCompleteRequest};
        try
        {
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
                    "web3.attributes.basetimestampfororderbizdate",
                    null);
                    
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "xblocks.gtl.attributes.systemtimestamp",
                    st);
            
            // テーブルへデータをインサート
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode("SP");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            l_equityPTSOrderServiceInterceptor.onCall(null, l_serviceParams);
            WEB3GentradeTradingClendarContext l_context =
                (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3EquityPTSTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);

            Timestamp l_processTime =
                (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(
                    "xblocks.gtl.attributes.systemtimestamp");
            assertEquals("20070612", "" + WEB3DateUtility.formatDate(l_processTime, "yyyyMMdd"));
            assertEquals("0D", l_context.getInstitutionCode());
            assertEquals("381", l_context.getBranchCode());
            assertEquals("SP", l_context.getMarketCode());
            assertEquals("01", l_context.getTradingTimeType());
            assertEquals("01", l_context.getOrderAcceptProduct());
            assertEquals("02", l_context.getOrderAcceptTransaction());
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * onCall()
     * {現物株式売付注文確認リクエスト}
     * 正しい
     */
    public void test_onCall_0006()
    {
        final String STR_METHOD_NAME = "test_onCall_0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
             new Class[] {}, new Long(333812512246L));

        WEB3EquitySellConfirmRequest l_sellConfirmRequest = new WEB3EquitySellConfirmRequest();
        l_sellConfirmRequest.marketCode = "SP";
        Object[] l_serviceParams = {l_sellConfirmRequest};
        try
        {
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
                    "web3.attributes.basetimestampfororderbizdate",
                    null);
                    
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "xblocks.gtl.attributes.systemtimestamp",
                    st);
            
            // テーブルへデータをインサート
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode("SP");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            l_equityPTSOrderServiceInterceptor.onCall(null, l_serviceParams);
            WEB3GentradeTradingClendarContext l_context =
                (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3EquityPTSTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);

            Timestamp l_processTime =
                (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(
                    "xblocks.gtl.attributes.systemtimestamp");
            assertEquals("20070612", "" + WEB3DateUtility.formatDate(l_processTime, "yyyyMMdd"));
            assertEquals("0D", l_context.getInstitutionCode());
            assertEquals("381", l_context.getBranchCode());
            assertEquals("SP", l_context.getMarketCode());
            assertEquals("01", l_context.getTradingTimeType());
            assertEquals("01", l_context.getOrderAcceptProduct());
            assertEquals("02", l_context.getOrderAcceptTransaction());
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    

    /**
     * onCall()
     * {現物株式買付注文完了リクエスト}
     * lockAccount
     * WEB3ErrorCatalog.SYSTEM_ERROR_80005
     */
    public void test_onCall_0007()
    {
        final String STR_METHOD_NAME = "test_onCall_0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
             new Class[] {}, new Long(333812512246L));

        WEB3EquityBuyCompleteRequest l_buyCompleteRequest = new WEB3EquityBuyCompleteRequest();
        l_buyCompleteRequest.marketCode = "SP";
        Object[] l_serviceParams = {l_buyCompleteRequest};
        try
        {
            // テーブルへデータをインサート
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("380");
            TestDBUtility.insertWithDel(l_branchParams);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode("SP");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("5");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            l_equityPTSOrderServiceInterceptor.onCall(null, l_serviceParams);
            fail();
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * onCall()
     * {現物株式売付注文完了リクエスト}
     * lockAccount
     * WEB3ErrorCatalog.SYSTEM_ERROR_80005
     */
    public void test_onCall_0008()
    {
        final String STR_METHOD_NAME = "test_onCall_0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
             new Class[] {}, new Long(333812512246L));

        WEB3EquitySellCompleteRequest l_sellCompleteRequest = new WEB3EquitySellCompleteRequest();
        l_sellCompleteRequest.marketCode = "SP";
        Object[] l_serviceParams = {l_sellCompleteRequest};
        try
        {
            // テーブルへデータをインサート
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("380");
            TestDBUtility.insertWithDel(l_branchParams);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode("SP");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("5");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            l_equityPTSOrderServiceInterceptor.onCall(null, l_serviceParams);
            fail();
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * onReturn
     * onThrowable
     */
    public void test_onReturnOronThrowable_0001()
    {
        final String STR_METHOD_NAME = "test_onReturnOronThrowable_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestSpecialClassUtility.testServiceInterceptor(l_equityPTSOrderServiceInterceptor);

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
