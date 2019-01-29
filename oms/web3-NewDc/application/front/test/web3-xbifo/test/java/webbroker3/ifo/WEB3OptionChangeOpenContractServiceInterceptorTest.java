head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.01.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionChangeOpenContractServiceInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (WEB3OptionChangeOpenContractServiceInterceptorTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/06　@何文敏(中訊)
*/
package webbroker3.ifo;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;

import test.util.TestDBUtility;
import test.util.TestSpecialClassUtility;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.ifo.message.WEB3FuturesCloseMarginChangeCompleteRequest;
import webbroker3.ifo.message.WEB3OptionsOpenMarginChangeCompleteRequest;
import webbroker3.ifo.message.WEB3OptionsOpenMarginChangeConfirmRequest;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;
  
/**
 * @@author 何文敏(中訊)
 * @@version 1.0
 */
public class WEB3OptionChangeOpenContractServiceInterceptorTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(
                 WEB3OptionChangeOpenContractServiceInterceptorTest.class);

    WEB3OptionChangeOpenContractServiceInterceptor l_equityMarginInterceptor = 
        new WEB3OptionChangeOpenContractServiceInterceptor();
    


    public WEB3OptionChangeOpenContractServiceInterceptorTest(String arg0)
    {
        super(arg0);
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
    
    public void testOnCall_001()
    {
        final String STR_METHOD_NAME = "testOnCall_001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3FuturesCloseMarginChangeCompleteRequest[] l_serviceParams = new WEB3FuturesCloseMarginChangeCompleteRequest[1];
            l_serviceParams[0] = new WEB3FuturesCloseMarginChangeCompleteRequest();
            l_equityMarginInterceptor.onCall(null, l_serviceParams);
        }
        catch(WEB3BaseRuntimeException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /*
     * Test method for 'webbroker3.ifo.WEB3OptionChangeOpenContractServiceInterceptor.onCall(Method, Object[])'
     */
    public void testOnCall_002()
    {
        final String STR_METHOD_NAME = "testOnCall_002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                new Class[] {}, new Long(333812512246L));
        
        try
        {
            // テーブルへデータをインサート
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setTradingTimeType("12");
            l_tradingTimeParams.setBizDateType("2");
            l_tradingTimeParams.setProductCode("0005");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderId(33381L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            IfoProductParams l_ifoproductParams = TestDBUtility.getIfoProductRow();
            l_ifoproductParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_ifoproductParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_productParams);
            
            WEB3OptionsOpenMarginChangeConfirmRequest[] l_serviceParams = new WEB3OptionsOpenMarginChangeConfirmRequest[1];
            l_serviceParams[0] = new WEB3OptionsOpenMarginChangeConfirmRequest();
            l_serviceParams[0].id = "33381";
    
            // 実際メソッドをコール
            l_equityMarginInterceptor.onCall(null, l_serviceParams);
    
            // 予想結果と実際結果の比較
            WEB3GentradeTradingClendarContext l_context =
                (WEB3GentradeTradingClendarContext)
                ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
            assertEquals("0D", l_context.getInstitutionCode());
            assertEquals("381", l_context.getBranchCode());
            assertEquals(WEB3MarketCodeDef.DEFAULT, l_context.getMarketCode());
            assertEquals(WEB3TradingTimeTypeDef.INDEX_FUTURE_OP_CHANGE_CANCEL, l_context.getTradingTimeType());
            assertEquals("0005", l_context.getProductCode());
            assertEquals(WEB3OrderAccProductDef.OPTION, l_context.getOrderAcceptProduct());
            assertEquals(WEB3OrderAccTransactionDef.CHANGE, l_context.getOrderAcceptTransaction());
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /*
     * Test method for 'webbroker3.ifo.WEB3OptionChangeOpenContractServiceInterceptor.onCall(Method, Object[])'
     */
    public void testOnCall_003()
    {
        final String STR_METHOD_NAME = "testOnCall_003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                new Class[] {}, new Long(333812512246L));
        
        try
        {
            // テーブルへデータをインサート
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setTradingTimeType("12");
            l_tradingTimeParams.setBizDateType("2");
            l_tradingTimeParams.setProductCode("0005");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderId(33381L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            IfoProductParams l_ifoproductParams = TestDBUtility.getIfoProductRow();
            l_ifoproductParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_ifoproductParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_productParams);
            
            WEB3OptionsOpenMarginChangeCompleteRequest[] l_serviceParams = new WEB3OptionsOpenMarginChangeCompleteRequest[1];
            l_serviceParams[0] = new WEB3OptionsOpenMarginChangeCompleteRequest();
            l_serviceParams[0].id = "33381";
    
            // 実際メソッドをコール
            l_equityMarginInterceptor.onCall(null, l_serviceParams);
    
            // 予想結果と実際結果の比較
            WEB3GentradeTradingClendarContext l_context =
                (WEB3GentradeTradingClendarContext)
                ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
            assertEquals("0D", l_context.getInstitutionCode());
            assertEquals("381", l_context.getBranchCode());
            assertEquals(WEB3MarketCodeDef.DEFAULT, l_context.getMarketCode());
            assertEquals(WEB3TradingTimeTypeDef.INDEX_FUTURE_OP_CHANGE_CANCEL, l_context.getTradingTimeType());
            assertEquals("0005", l_context.getProductCode());
            assertEquals(WEB3OrderAccProductDef.OPTION, l_context.getOrderAcceptProduct());
            assertEquals(WEB3OrderAccTransactionDef.CHANGE, l_context.getOrderAcceptTransaction());
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /*
     * Test method for 'webbroker3.ifo.WEB3OptionChangeOpenContractServiceInterceptor.onCall(Method, Object[])'
     */
    public void testOnCall_004()
    {
        final String STR_METHOD_NAME = "testOnCall_004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                new Class[] {}, new Long(333812512246L));

        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(true);
            // テーブルへデータをインサート
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setTradingTimeType("12");
            l_tradingTimeParams.setBizDateType("2");
            l_tradingTimeParams.setProductCode("0005");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderId(33381L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            IfoProductParams l_ifoproductParams = TestDBUtility.getIfoProductRow();
            l_ifoproductParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_ifoproductParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_productParams);
            
            WEB3OptionsOpenMarginChangeCompleteRequest[] l_serviceParams = new WEB3OptionsOpenMarginChangeCompleteRequest[1];
            l_serviceParams[0] = new WEB3OptionsOpenMarginChangeCompleteRequest();
            l_serviceParams[0].id = "33381";
            
            // 実際メソッドをコール
            l_equityMarginInterceptor.onCall(null, l_serviceParams);
    
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /*
     * Test method for 'webbroker3.ifo.WEB3OptionChangeOpenContractServiceInterceptor.onCall(Method, Object[])'
     */
    public void testOnCall_005()
    {
        final String STR_METHOD_NAME = "testOnCall_005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                new Class[] {}, new Long(333812512246L));

        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(false);
            // テーブルへデータをインサート
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setTradingTimeType("12");
            l_tradingTimeParams.setBizDateType("2");
            l_tradingTimeParams.setProductCode("0005");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderId(33381L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            IfoProductParams l_ifoproductParams = TestDBUtility.getIfoProductRow();
            l_ifoproductParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_ifoproductParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_productParams);
            
            WEB3OptionsOpenMarginChangeCompleteRequest[] l_serviceParams = new WEB3OptionsOpenMarginChangeCompleteRequest[1];
            l_serviceParams[0] = new WEB3OptionsOpenMarginChangeCompleteRequest();
            l_serviceParams[0].id = "33381";
            
            // 実際メソッドをコール
            l_equityMarginInterceptor.onCall(null, l_serviceParams);
    
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * Test method for 'webbroker3.ifo.WEB3OptionChangeOpenContractServiceInterceptor.onReturn(Object, Object)'
     */
    public void testOnReturn()
    {
        final String STR_METHOD_NAME = "testOnReturn()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {      
            TestSpecialClassUtility.testServiceInterceptor(l_equityMarginInterceptor);
            assertEquals(ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG), null);
            assertEquals(ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.OFFSET_TAG), null);
            assertEquals(ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH), null);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.ifo.WEB3OptionChangeOpenContractServiceInterceptor.onThrowable(Object, Throwable)'
     */
    public void testOnThrowable()
    {
        final String STR_METHOD_NAME = "testOnThrowable()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        // 実際メソッドをコール
        TestSpecialClassUtility.testServiceInterceptor(l_equityMarginInterceptor);
        assertEquals(ThreadLocalSystemAttributesRegistry.
            getAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG), null);
        assertEquals(ThreadLocalSystemAttributesRegistry.
            getAttribute(WEB3GentradeTradingTimeManagement.OFFSET_TAG), null);
        assertEquals(ThreadLocalSystemAttributesRegistry.
            getAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH), null);
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

}
@
