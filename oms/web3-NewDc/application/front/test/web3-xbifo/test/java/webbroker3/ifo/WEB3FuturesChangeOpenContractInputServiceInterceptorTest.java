head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.57.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FuturesChangeOpenContractInputServiceInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3ToSuccIfoOrderUnitServiceInterceptorTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2008/07/10 劉剣（中訊）新規作成
*/
package webbroker3.ifo;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;

import test.util.TestDBUtility;
import test.util.TestSpecialClassUtility;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.ifo.message.WEB3FuturesOpenMarginChangeInputRequest;
import webbroker3.ifo.message.WEB3OptionsOpenMarginChangeInputRequest;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3FuturesChangeOpenContractInputServiceInterceptorTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FuturesChangeOpenContractInputServiceInterceptorTest.class);
    
    WEB3FuturesChangeOpenContractInputServiceInterceptor l_interceptor = null;

    public WEB3FuturesChangeOpenContractInputServiceInterceptorTest(String arg0)
    {
        super(arg0);
        // TODO Auto-generated constructor stub
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.l_interceptor = new WEB3FuturesChangeOpenContractInputServiceInterceptor();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    /*
     * parameter is null type
     * 抛出：WEB3ErrorCatalog.SYSTEM_ERROR_80017
     */
    public void testOnCall_C0001()
    {
        final String STR_METHOD_NAME = "testOnCall_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3FuturesOpenMarginChangeInputRequest[] l_serviceParams = new WEB3FuturesOpenMarginChangeInputRequest[1];
            l_interceptor.onCall(null, l_serviceParams);
            fail();

        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * parameter is null type
     * 抛出：WEB3ErrorCatalog.SYSTEM_ERROR_80017
     */
    public void testOnCall_C0002()
    {
        final String STR_METHOD_NAME = "testOnCall_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            Object[] l_serviceParams = null;
            l_interceptor.onCall(null, l_serviceParams);
            fail();

        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * パラメータタイプ不正。
     * 抛出：WEB3ErrorCatalog.SYSTEM_ERROR_80018
     */
    public void testOnCall_C0003()
    {
        final String STR_METHOD_NAME = "testOnCall_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3OptionsOpenMarginChangeInputRequest[] l_serviceParams = new WEB3OptionsOpenMarginChangeInputRequest[1];
            l_serviceParams[0] = new WEB3OptionsOpenMarginChangeInputRequest();
            l_interceptor.onCall(null, l_serviceParams);
            fail();

        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * l_orderUnits.length==0
     * 抛出：WEB3ErrorCatalog.SYSTEM_ERROR_80005
     */
    public void testOnCall_C0004()
    {
        final String STR_METHOD_NAME = "testOnCall_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {

            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderId(1234);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            WEB3FuturesOpenMarginChangeInputRequest[] l_serviceParams = new WEB3FuturesOpenMarginChangeInputRequest[1];
            l_serviceParams[0] = new WEB3FuturesOpenMarginChangeInputRequest();
            l_serviceParams[0].id = "1001";
            l_interceptor.onCall(null, l_serviceParams);
            fail();

        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 取引時間テーブル検索： 件数 = 0
     * WEB3ErrorCatalog.SYSTEM_ERROR_80005
     */
    public void testOnCall_C0005()
    {
        final String STR_METHOD_NAME = "testOnCall_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));

        try
        {
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();;
            TestDBUtility.insertWithDel(l_institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_ifoProductParams);

            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            TestDBUtility.insertWithDel(l_tradingTimeParams);
    
            WEB3FuturesOpenMarginChangeInputRequest[] l_serviceParam = new WEB3FuturesOpenMarginChangeInputRequest[1];
            l_serviceParam[0] = new WEB3FuturesOpenMarginChangeInputRequest();
            l_serviceParam[0].id = "1001";
            l_interceptor.onCall(null, l_serviceParam);
            fail();

        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * normal case
     */
    public void testOnCall_C0006()
    {
        final String STR_METHOD_NAME = "testOnCall_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();;
            TestDBUtility.insertWithDel(l_institutionParams);
           
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_ifoProductParams);

            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("12");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setProductCode("0005");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            WEB3FuturesOpenMarginChangeInputRequest[] l_serviceParam = new WEB3FuturesOpenMarginChangeInputRequest[1];
            l_serviceParam[0] = new WEB3FuturesOpenMarginChangeInputRequest();
            l_serviceParam[0].id = "1001";
            l_interceptor.onCall(null, l_serviceParam);

            WEB3GentradeTradingClendarContext l_context =
                (WEB3GentradeTradingClendarContext)
                    ThreadLocalSystemAttributesRegistry.getAttribute(
                            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);

            assertEquals("0D",l_context.getInstitutionCode());
            assertEquals("381",l_context.getBranchCode());
            assertEquals("0",l_context.getMarketCode());
            assertEquals("12",l_context.getTradingTimeType());
            assertEquals("0005",l_context.getProductCode());
            assertEquals(WEB3OrderAccProductDef.FUTURE,l_context.getOrderAcceptProduct());
            assertEquals(WEB3OrderAccTransactionDef.CHANGE,l_context.getOrderAcceptTransaction());

        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
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
            TestSpecialClassUtility.testServiceInterceptor(l_interceptor);
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
        TestSpecialClassUtility.testServiceInterceptor(l_interceptor);
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
