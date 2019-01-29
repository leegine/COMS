head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.00.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionOpenContractInputServiceInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3OptionOpenContractInputServiceInterceptorTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/08/28 金傑（中訊）新規作成
*/
package webbroker3.ifo;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccountForMock;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.ifo.message.WEB3OptionsOpenMarginInputRequest;
import webbroker3.ifo.message.WEB3OptionsOrderNotifyRequest;
import webbroker3.ifo.message.WEB3OptionsProductSelectRequest;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionOpenContractInputServiceImpl;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3OptionOpenContractInputServiceInterceptorTest extends TestBaseForMock
{

    private WEB3OptionOpenContractInputServiceInterceptor l_interceptor = null;

    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3OptionOpenContractInputServiceInterceptorTest.class);
    
    public WEB3OptionOpenContractInputServiceInterceptorTest(String name)
    {
        super(name);
    }
    
    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.l_interceptor = new WEB3OptionOpenContractInputServiceInterceptor();

    }

    protected void tearDown() throws Exception
    {
        this.l_interceptor = null;
        super.tearDown();
    }
    
    /**
     * パラメータタイプ不正
     * 抛出異常信息：WEB3ErrorCatalog.SYSTEM_ERROR_80018
     */
    public void testOncall_C0001()
    {
        String STR_METHOD_NAME = "testOnCall_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3OptionsOrderNotifyRequest l_request = new WEB3OptionsOrderNotifyRequest();
            Object[] l_serviceParam = new Object[]{l_request};
            Method l_method = WEB3OptionOpenContractInputServiceImpl.class.getMethod("execute",new Class[]{WEB3GenRequest.class});
            this.l_interceptor.onCall(l_method,l_serviceParam);
            fail();
        }
        catch(WEB3BaseRuntimeException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_ex.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            fail();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * データ不整合エラー
     * 抛出異常信息：WEB3ErrorCatalog.BUSINESS_ERROR_00301
     */
    public void testOncall_C0002()
    {
        String STR_METHOD_NAME = "testOnCall_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(12345));
            
            WEB3GentradeMainAccountForMock l_mainAccountForMock = new WEB3GentradeMainAccountForMock(TestDBUtility.getMainAccountRow());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "getMainAccount",
                    new Class[] {long.class},
                    l_mainAccountForMock);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());
            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution(33);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeMainAccount", 
                    "getInstitution", 
                    new Class[]{},
                    l_institution);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(33381);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeMainAccount", 
                    "getBranch", 
                    new Class[] {},
                    l_branch);
            
            WEB3OptionsOpenMarginInputRequest l_request = new WEB3OptionsOpenMarginInputRequest();
            l_request.contractType = "1";
            l_request.opProductCode = "3256";
            
            Object[] l_serviceParam = new Object[]{l_request};
            Method l_method = WEB3OptionOpenContractInputServiceImpl.class.getMethod("execute",new Class[]{WEB3GenRequest.class});
            Object l_obj = this.l_interceptor.onCall(l_method,l_serviceParam);
            assertEquals("0D",((WEB3GentradeTradingClendarContext)l_obj).getInstitutionCode());
            assertEquals("01",((WEB3GentradeTradingClendarContext)l_obj).getOrderAcceptTransaction());
            assertEquals("381",((WEB3GentradeTradingClendarContext)l_obj).getBranchCode());
        }
        catch(WEB3BaseRuntimeException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00301, l_ex.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 株価指数オプション新規建注文入力画面リクエストの場合
     * 且つ買建の場合
     */
    public void testOncall_C0003()
    {
        String STR_METHOD_NAME = "testOnCall_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("11");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setProductCode("0008");
            
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(12345));
            
            WEB3GentradeMainAccountForMock l_mainAccountForMock = new WEB3GentradeMainAccountForMock(TestDBUtility.getMainAccountRow());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "getMainAccount",
                    new Class[] {long.class},
                    l_mainAccountForMock);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());
            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution(33);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeMainAccount", 
                    "getInstitution", 
                    new Class[]{},
                    l_institution);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(33381);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeMainAccount", 
                    "getBranch", 
                    new Class[] {},
                    l_branch);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060005L);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.getIfoTradedProductRow();
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006160060005L);
            l_ifoProductParams.setInstitutionCode("1D");
            l_ifoProductParams.setProductCode("3256");
            l_ifoProductParams.setUnderlyingProductCode("0008");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            WEB3IfoProductImpl l_product = new WEB3IfoProductImplForMock(l_ifoProductParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl", 
                    "getIfoProduct",
                    new Class[] { Institution.class, String.class },
                    l_product);


            WEB3OptionsOpenMarginInputRequest l_request = new WEB3OptionsOpenMarginInputRequest();
            l_request.contractType = "1";
            l_request.opProductCode = "3256";
            
            Object[] l_serviceParam = new Object[]{l_request};
            Method l_method = WEB3OptionOpenContractInputServiceImpl.class.getMethod("execute",new Class[]{WEB3GenRequest.class});
            this.l_interceptor.onCall(l_method,l_serviceParam);
            Object l_obj = ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
            
            assertEquals("0D",((WEB3GentradeTradingClendarContext)l_obj).getInstitutionCode());
            assertEquals("01",((WEB3GentradeTradingClendarContext)l_obj).getOrderAcceptTransaction());
            assertEquals("381",((WEB3GentradeTradingClendarContext)l_obj).getBranchCode());
            assertEquals("0008",((WEB3GentradeTradingClendarContext)l_obj).getProductCode());
        }
        catch(WEB3BaseRuntimeException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 株価指数オプション新規建注文入力画面リクエストの場合
     * 且つ売建の場合
     */
    public void testOncall_C0004()
    {
        String STR_METHOD_NAME = "testOnCall_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("11");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setProductCode("0");
            
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(12345));
            
            WEB3GentradeMainAccountForMock l_mainAccountForMock = new WEB3GentradeMainAccountForMock(TestDBUtility.getMainAccountRow());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "getMainAccount",
                    new Class[] {long.class},
                    l_mainAccountForMock);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());
            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution(33);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeMainAccount", 
                    "getInstitution", 
                    new Class[]{},
                    l_institution);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(33381);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeMainAccount", 
                    "getBranch", 
                    new Class[] {},
                    l_branch);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060005L);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.getIfoTradedProductRow();
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006160060005L);
            l_ifoProductParams.setInstitutionCode("1D");
            l_ifoProductParams.setProductCode("3256");
            l_ifoProductParams.setUnderlyingProductCode("0008");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            WEB3IfoProductImpl l_product = new WEB3IfoProductImplForMock(l_ifoProductParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl", 
                    "getIfoProduct",
                    new Class[] { Institution.class, String.class },
                    l_product);


            WEB3OptionsOpenMarginInputRequest l_request = new WEB3OptionsOpenMarginInputRequest();
            l_request.contractType = "2";
            
            Object[] l_serviceParam = new Object[]{l_request};
            Method l_method = WEB3OptionOpenContractInputServiceImpl.class.getMethod("execute",new Class[]{WEB3GenRequest.class});
            this.l_interceptor.onCall(l_method,l_serviceParam);
            Object l_obj = ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
            
            assertEquals("0D",((WEB3GentradeTradingClendarContext)l_obj).getInstitutionCode());
            assertEquals("02",((WEB3GentradeTradingClendarContext)l_obj).getOrderAcceptTransaction());
            assertEquals("381",((WEB3GentradeTradingClendarContext)l_obj).getBranchCode());
            assertEquals("0",((WEB3GentradeTradingClendarContext)l_obj).getProductCode());
        }
        catch(WEB3BaseRuntimeException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 株価指数オプション新規建注文銘柄選択画面リクエストの場合
     * 且つ買建の場合
     */
    public void testOncall_C0005()
    {
        String STR_METHOD_NAME = "testOnCall_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("11");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setProductCode("0");
            
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(12345));
            
            WEB3GentradeMainAccountForMock l_mainAccountForMock = new WEB3GentradeMainAccountForMock(TestDBUtility.getMainAccountRow());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "getMainAccount",
                    new Class[] {long.class},
                    l_mainAccountForMock);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());
            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution(33);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeMainAccount", 
                    "getInstitution", 
                    new Class[]{},
                    l_institution);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(33381);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeMainAccount", 
                    "getBranch", 
                    new Class[] {},
                    l_branch);

            WEB3OptionsProductSelectRequest l_request = new WEB3OptionsProductSelectRequest();
            l_request.contractType = "1";
            
            Object[] l_serviceParam = new Object[]{l_request};
            Method l_method = WEB3OptionOpenContractInputServiceImpl.class.getMethod("execute",new Class[]{WEB3GenRequest.class});
            this.l_interceptor.onCall(l_method,l_serviceParam);
            Object l_obj = ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
            
            assertEquals("0D",((WEB3GentradeTradingClendarContext)l_obj).getInstitutionCode());
            assertEquals("01",((WEB3GentradeTradingClendarContext)l_obj).getOrderAcceptTransaction());
            assertEquals("381",((WEB3GentradeTradingClendarContext)l_obj).getBranchCode());
            assertEquals("0",((WEB3GentradeTradingClendarContext)l_obj).getProductCode());
        }
        catch(WEB3BaseRuntimeException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 株価指数オプション新規建注文銘柄選択画面リクエストの場合
     * 且つ買建の場合
     */
    public void testOncall_C0006()
    {
        String STR_METHOD_NAME = "testOnCall_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("11");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setProductCode("0");
            
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(12345));
            
            WEB3GentradeMainAccountForMock l_mainAccountForMock = new WEB3GentradeMainAccountForMock(TestDBUtility.getMainAccountRow());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "getMainAccount",
                    new Class[] {long.class},
                    l_mainAccountForMock);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());
            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution(33);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeMainAccount", 
                    "getInstitution", 
                    new Class[]{},
                    l_institution);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(33381);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeMainAccount", 
                    "getBranch", 
                    new Class[] {},
                    l_branch);

            WEB3OptionsProductSelectRequest l_request = new WEB3OptionsProductSelectRequest();
            l_request.contractType = "2";
            
            Object[] l_serviceParam = new Object[]{l_request};
            Method l_method = WEB3OptionOpenContractInputServiceImpl.class.getMethod("execute",new Class[]{WEB3GenRequest.class});
            this.l_interceptor.onCall(l_method,l_serviceParam);
            Object l_obj = ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
            
            assertEquals("0D",((WEB3GentradeTradingClendarContext)l_obj).getInstitutionCode());
            assertEquals("02",((WEB3GentradeTradingClendarContext)l_obj).getOrderAcceptTransaction());
            assertEquals("381",((WEB3GentradeTradingClendarContext)l_obj).getBranchCode());
            assertEquals("0",((WEB3GentradeTradingClendarContext)l_obj).getProductCode());
        }
        catch(WEB3BaseRuntimeException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * テーブルに該当するデータがありませんの場合
     * 
     */
    public void testOncall_C0007()
    {
        String STR_METHOD_NAME = "testOnCall_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("20");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setProductCode("0");
            
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(12345));
            
            WEB3GentradeMainAccountForMock l_mainAccountForMock = new WEB3GentradeMainAccountForMock(TestDBUtility.getMainAccountRow());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "getMainAccount",
                    new Class[] {long.class},
                    l_mainAccountForMock);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());
            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution(33);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeMainAccount", 
                    "getInstitution", 
                    new Class[]{},
                    l_institution);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(33381);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeMainAccount", 
                    "getBranch", 
                    new Class[] {},
                    l_branch);

            WEB3OptionsProductSelectRequest l_request = new WEB3OptionsProductSelectRequest();
            l_request.contractType = "2";
            
            Object[] l_serviceParam = new Object[]{l_request};
            Method l_method = WEB3OptionOpenContractInputServiceImpl.class.getMethod("execute",new Class[]{WEB3GenRequest.class});
            this.l_interceptor.onCall(l_method,l_serviceParam);
            fail();
        }
        catch(WEB3BaseRuntimeException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005,l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
}
@
