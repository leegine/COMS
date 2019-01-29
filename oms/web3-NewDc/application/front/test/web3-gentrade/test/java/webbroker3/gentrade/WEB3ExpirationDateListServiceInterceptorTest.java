head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.23.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3ExpirationDateListServiceInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3ExpirationDateListServiceInterceptorTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2008/02/19 于瀟（中訊）新規作成
*/
package webbroker3.gentrade;

import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import test.util.TestDBUtility;
import test.util.TestSpecialClassUtility;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3CommodityDivDef;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.gentrade.message.WEB3ExpirationDateListRequest;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3ExpirationDateListServiceInterceptorTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ExpirationDateListServiceInterceptorTest.class);


    public WEB3ExpirationDateListServiceInterceptorTest(String arg0)
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

    /**
     * 正常終了
     * 
     * 當l_requests[0].commodityType = WEB3CommodityDivDef.EQUITY時
     */
    public void testOnCall_C0001()
    {
        final String STR_METHOD_NAME = "testOnCall_C0001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3ExpirationDateListServiceInterceptor l_interceptor = 
            new WEB3ExpirationDateListServiceInterceptor();
        
        WEB3ExpirationDateListRequest[] l_requests =
            new WEB3ExpirationDateListRequest[1];
        l_requests[0] = new WEB3ExpirationDateListRequest();
        l_requests[0].commodityType = WEB3CommodityDivDef.EQUITY;
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            InstitutionParams institutionParams  = TestDBUtility.getInstitutionRow();
            institutionParams.setInstitutionId(l_mainAccountParams.getInstitutionId());
            TestDBUtility.insertWithDel(institutionParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setProductCode("0");
            
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            WEB3GentradeTradingClendarContext l_context =
                (WEB3GentradeTradingClendarContext)l_interceptor.onCall(null, l_requests);
        
            assertEquals("0D", l_context.getInstitutionCode());
            assertEquals("381", l_context.getBranchCode());
            assertNull(l_context.getMarketCode());
            assertEquals("0", l_context.getProductCode());
            assertEquals("01", l_context.getTradingTimeType());
            assertEquals("01", l_context.getOrderAcceptProduct());
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * 正常終了
     * 
     * 當l_requests[0].commodityType = WEB3CommodityDivDef.FUTURE時
     */
    public void testOnCall_C0002()
    {
        final String STR_METHOD_NAME = "testOnCall_C0002()";
        log.entering(STR_METHOD_NAME);
        
        WEB3ExpirationDateListServiceInterceptor l_interceptor = 
            new WEB3ExpirationDateListServiceInterceptor();
        
        WEB3ExpirationDateListRequest[] l_requests =
            new WEB3ExpirationDateListRequest[1];
        l_requests[0] = new WEB3ExpirationDateListRequest();
        l_requests[0].commodityType = WEB3CommodityDivDef.FUTURE;
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            InstitutionParams institutionParams  = TestDBUtility.getInstitutionRow();
            institutionParams.setInstitutionId(l_mainAccountParams.getInstitutionId());
            TestDBUtility.insertWithDel(institutionParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("11");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setProductCode("0");
            
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            WEB3GentradeTradingClendarContext l_context =
                (WEB3GentradeTradingClendarContext)l_interceptor.onCall(null, l_requests);
        
            assertEquals("0D", l_context.getInstitutionCode());
            assertEquals("381", l_context.getBranchCode());
            assertEquals("0", l_context.getMarketCode());
            assertEquals("0", l_context.getProductCode());
            assertEquals("11", l_context.getTradingTimeType());
            assertEquals("05", l_context.getOrderAcceptProduct());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * 正常終了
     * 
     * 當l_requests[0].commodityType = WEB3CommodityDivDef.MARGIN時
     */
    public void testOnCall_C0003()
    {
        final String STR_METHOD_NAME = "testOnCall_C0003()";
        log.entering(STR_METHOD_NAME);
        
        WEB3ExpirationDateListServiceInterceptor l_interceptor = 
            new WEB3ExpirationDateListServiceInterceptor();
        
        WEB3ExpirationDateListRequest[] l_requests =
            new WEB3ExpirationDateListRequest[1];
        l_requests[0] = new WEB3ExpirationDateListRequest();
        l_requests[0].commodityType = WEB3CommodityDivDef.MARGIN;
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            InstitutionParams institutionParams  = TestDBUtility.getInstitutionRow();
            institutionParams.setInstitutionId(l_mainAccountParams.getInstitutionId());
            TestDBUtility.insertWithDel(institutionParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setProductCode("0");
            
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            WEB3GentradeTradingClendarContext l_context =
                (WEB3GentradeTradingClendarContext)l_interceptor.onCall(null, l_requests);
        
            assertEquals("0D", l_context.getInstitutionCode());
            assertEquals("381", l_context.getBranchCode());
            assertNull(l_context.getMarketCode());
            assertEquals("0", l_context.getProductCode());
            assertEquals("01", l_context.getTradingTimeType());
            assertEquals("03", l_context.getOrderAcceptProduct());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * 正常終了
     * 
     * 當l_requests[0].commodityType = WEB3CommodityDivDef.OPTION時
     */
    public void testOnCall_C0004()
    {
        final String STR_METHOD_NAME = "testOnCall_C0004()";
        log.entering(STR_METHOD_NAME);
        
        WEB3ExpirationDateListServiceInterceptor l_interceptor = 
            new WEB3ExpirationDateListServiceInterceptor();
        
        WEB3ExpirationDateListRequest[] l_requests =
            new WEB3ExpirationDateListRequest[1];
        l_requests[0] = new WEB3ExpirationDateListRequest();
        l_requests[0].commodityType = WEB3CommodityDivDef.OPTION;
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            InstitutionParams institutionParams  = TestDBUtility.getInstitutionRow();
            institutionParams.setInstitutionId(l_mainAccountParams.getInstitutionId());
            TestDBUtility.insertWithDel(institutionParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("11");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setProductCode("0");
            
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            WEB3GentradeTradingClendarContext l_context =
                (WEB3GentradeTradingClendarContext)l_interceptor.onCall(null, l_requests);
        
            assertEquals("0D", l_context.getInstitutionCode());
            assertEquals("381", l_context.getBranchCode());
            assertEquals("0", l_context.getMarketCode());
            assertEquals("0", l_context.getProductCode());
            assertEquals("11", l_context.getTradingTimeType());
            assertEquals("06", l_context.getOrderAcceptProduct());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * ?出異常信息:SYSTEM_ERROR_80005
     *
     */
    public void testOnCall_C0005()
    {
        final String STR_METHOD_NAME = "testOnCall_C0005()";
        log.entering(STR_METHOD_NAME);
        
        WEB3ExpirationDateListServiceInterceptor l_interceptor = 
            new WEB3ExpirationDateListServiceInterceptor();
        
        WEB3ExpirationDateListRequest[] l_requests =
            new WEB3ExpirationDateListRequest[1];
        l_requests[0] = new WEB3ExpirationDateListRequest();
        l_requests[0].commodityType = WEB3CommodityDivDef.OPTION;
        l_requests[0].targetProductCode = "";
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            InstitutionParams institutionParams  = TestDBUtility.getInstitutionRow();
            institutionParams.setInstitutionId(l_mainAccountParams.getInstitutionId());
            TestDBUtility.insertWithDel(institutionParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("11");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            l_interceptor.onCall(null, l_requests);
        
        }
        catch(WEB3BaseRuntimeException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * 正常終了
     * 
     * onReturn
     * 
     * onThrowable
     * 
     */
    public void testOnReturnOnThrowable_C0001()
    {
        final String STR_METHOD_NAME = "testOnReturnOnThrowable_C0001()";
        log.entering(STR_METHOD_NAME);

        TestSpecialClassUtility.testServiceInterceptor(new WEB3ExpirationDateListServiceInterceptor());

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
}
@
