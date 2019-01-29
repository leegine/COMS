head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.59.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionSettleContractOrderServiceInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : OP返済注文サービスインタセプタTest(WEB3OptionSettleContractOrderServiceInterceptorTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/11 趙林鵬(中訊) 新規作成
*/

package webbroker3.ifo;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.ifo.message.WEB3FuturesOptionsCloseMarginContractUnit;
import webbroker3.ifo.message.WEB3OptionsCloseMarginCompleteRequest;
import webbroker3.ifo.message.WEB3OptionsCloseMarginConfirmRequest;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3OptionSettleContractOrderServiceInterceptorTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionSettleContractOrderServiceInterceptorTest.class);

    public WEB3OptionSettleContractOrderServiceInterceptorTest(String arg0)
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
     * サービスメソッド引数 = WEB3OptionsCloseMarginConfirmRequest，
     * 取引カレンダコンテキストに内容をセットする。
     */
    public void testonCallCase0001()
    {
        final String STR_METHOD_NAME = "testonCallCase0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setTradingTimeType("11");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setProductCode("0005");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            IfoProductParams l_ifoproductParams = TestDBUtility.getIfoProductRow();
            l_ifoproductParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_ifoproductParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_productParams);
            
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setContractId(1001);
            l_ifoContractParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            Method l_method = null;
            WEB3OptionsCloseMarginConfirmRequest l_request = new WEB3OptionsCloseMarginConfirmRequest();

            WEB3FuturesOptionsCloseMarginContractUnit[] l_units =
                new WEB3FuturesOptionsCloseMarginContractUnit[1];
            l_units[0] = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_units[0].id = "1001";
            
            l_request.closeMarginContractUnits = l_units;
            Object[] l_serviceParam = {l_request};
            
            WEB3OptionSettleContractOrderServiceInterceptor l_interceptor = 
                new WEB3OptionSettleContractOrderServiceInterceptor();
            
            l_interceptor.onCall(l_method, l_serviceParam);
            
            WEB3GentradeTradingClendarContext l_context =
                (WEB3GentradeTradingClendarContext)
                ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
            
            assertEquals("0D", l_context.getInstitutionCode());
            assertEquals("381", l_context.getBranchCode());
            assertEquals("0", l_context.getMarketCode());
            assertEquals("11", l_context.getTradingTimeType());
            assertNull(l_context.getProductCode());
            assertEquals("06", l_context.getOrderAcceptProduct());
            assertEquals("03", l_context.getOrderAcceptTransaction());

        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
    }
    
    /**
     * サービスメソッド引数 = WEB3OptionsCloseMarginCompleteRequest，
     * 取引カレンダコンテキストに内容をセットする。
     */
    public void testonCallCase0002()
    {
        final String STR_METHOD_NAME = "testonCallCase0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setTradingTimeType("11");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setProductCode("0005");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            IfoProductParams l_ifoproductParams = TestDBUtility.getIfoProductRow();
            l_ifoproductParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_ifoproductParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_productParams);
            
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setContractId(1001);
            l_ifoContractParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            Method l_method = null;
            WEB3OptionsCloseMarginCompleteRequest l_request = new WEB3OptionsCloseMarginCompleteRequest();

            WEB3FuturesOptionsCloseMarginContractUnit[] l_units =
                new WEB3FuturesOptionsCloseMarginContractUnit[1];
            l_units[0] = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_units[0].id = "1001";
            
            l_request.closeMarginContractUnits = l_units;
            Object[] l_serviceParam = {l_request};
            
            WEB3OptionSettleContractOrderServiceInterceptor l_interceptor = 
                new WEB3OptionSettleContractOrderServiceInterceptor();
            
            l_interceptor.onCall(l_method, l_serviceParam);
            
            WEB3GentradeTradingClendarContext l_context =
                (WEB3GentradeTradingClendarContext)
                ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
            
            assertEquals("0D", l_context.getInstitutionCode());
            assertEquals("381", l_context.getBranchCode());
            assertEquals("0", l_context.getMarketCode());
            assertEquals("11", l_context.getTradingTimeType());
            assertNull("0005", l_context.getProductCode());
            assertEquals("06", l_context.getOrderAcceptProduct());
            assertEquals("03", l_context.getOrderAcceptTransaction());

        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
    }

    /**
     * 取引時間管理.setTimestamp()をコールする抛出
     * WEB3ErrorCatalog.SYSTEM_ERROR_80005異常。
     */
    public void testonCallCase0003()
    {
        final String STR_METHOD_NAME = "testonCallCase0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            
            IfoProductParams l_ifoproductParams = TestDBUtility.getIfoProductRow();
            l_ifoproductParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_ifoproductParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_productParams);
            
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setContractId(1001);
            l_ifoContractParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            Method l_method = null;
            WEB3OptionsCloseMarginCompleteRequest l_request = new WEB3OptionsCloseMarginCompleteRequest();

            WEB3FuturesOptionsCloseMarginContractUnit[] l_units =
                new WEB3FuturesOptionsCloseMarginContractUnit[1];
            l_units[0] = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_units[0].id = "1001";
            
            l_request.closeMarginContractUnits = l_units;
            Object[] l_serviceParam = {l_request};
            
            WEB3OptionSettleContractOrderServiceInterceptor l_interceptor = 
                new WEB3OptionSettleContractOrderServiceInterceptor();
            
            l_interceptor.onCall(l_method, l_serviceParam);
            fail();
        }
        catch(WEB3BaseRuntimeException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.SYSTEM_ERROR_80005);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            fail();
            log.error(ERROR + l_ex.getMessage(), l_ex);
        }
    }  
}
@
