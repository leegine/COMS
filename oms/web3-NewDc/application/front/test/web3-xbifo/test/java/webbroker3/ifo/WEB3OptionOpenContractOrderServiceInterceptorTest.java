head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.58.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionOpenContractOrderServiceInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : OP新規建注文サービスインタセプタ(WEB3OptionOpenContractOrderServiceInterceptorTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/11 孫洪江（中訊）新規作成
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
import webbroker3.ifo.message.WEB3OptionsOpenMarginCompleteRequest;
import webbroker3.ifo.message.WEB3OptionsOpenMarginConfirmRequest;
import webbroker3.ifo.message.WEB3OptionsOrderNotifyRequest;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionOpenContractOrderServiceImpl;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3OptionOpenContractOrderServiceInterceptorTest extends TestBaseForMock
{
    private WEB3OptionOpenContractOrderServiceInterceptor l_interceptor = null;
    
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3OptionOpenContractOrderServiceInterceptorTest.class);

    public WEB3OptionOpenContractOrderServiceInterceptorTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.l_interceptor = new WEB3OptionOpenContractOrderServiceInterceptor();

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
            Method l_method = WEB3OptionOpenContractOrderServiceImpl.class.getMethod("execute",new Class[]{WEB3GenRequest.class});
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
     * リクエストデータ= new 株価指数オプション新規建注文確認リクエスト
     * リクエストデータ.建区分 == ”買建”の場合、”01：買付（新規建買）”
     * 
     */
    public void testOncall_C0002()
    {
        String STR_METHOD_NAME = "testOnCall_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("387");
            l_tradingTimeParams.setTradingTimeType("11");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setProductCode("0008");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setInstitutionCode("0D");
            l_tradingTimeParams1.setBranchCode("381");
            l_tradingTimeParams1.setTradingTimeType("11");
            l_tradingTimeParams1.setBizDateType("1");
            l_tradingTimeParams1.setMarketCode("0");
            l_tradingTimeParams1.setProductCode("0008");
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            
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


            WEB3OptionsOpenMarginConfirmRequest l_request = new WEB3OptionsOpenMarginConfirmRequest();
            l_request.contractType = "1";
            l_request.opProductCode = "3256";
            
            Object[] l_serviceParam = new Object[]{l_request};
            Method l_method = WEB3OptionOpenContractOrderServiceImpl.class.getMethod("execute",new Class[]{WEB3GenRequest.class});
            this.l_interceptor.onCall(l_method,l_serviceParam);
            Object l_obj = ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
            
            assertEquals("0D",((WEB3GentradeTradingClendarContext)l_obj).getInstitutionCode());
            assertEquals("01",((WEB3GentradeTradingClendarContext)l_obj).getOrderAcceptTransaction());
            assertEquals("381",((WEB3GentradeTradingClendarContext)l_obj).getBranchCode());
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
     * リクエストデータ= new 株価指数オプション新規建注文確認リクエスト
     * リクエストデータ.建区分 == ”売建”の場合、”02：売付（新規建売）”）
     * 
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
            l_tradingTimeParams.setBranchCode("387");
            l_tradingTimeParams.setTradingTimeType("11");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setProductCode("0008");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setInstitutionCode("0D");
            l_tradingTimeParams1.setBranchCode("381");
            l_tradingTimeParams1.setTradingTimeType("11");
            l_tradingTimeParams1.setBizDateType("1");
            l_tradingTimeParams1.setMarketCode("0");
            l_tradingTimeParams1.setProductCode("0008");
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            
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


            WEB3OptionsOpenMarginConfirmRequest l_request = new WEB3OptionsOpenMarginConfirmRequest();
            l_request.contractType = "2";
            l_request.opProductCode = "3256";
            
            Object[] l_serviceParam = new Object[]{l_request};
            Method l_method = WEB3OptionOpenContractOrderServiceImpl.class.getMethod("execute",new Class[]{WEB3GenRequest.class});
            this.l_interceptor.onCall(l_method,l_serviceParam);
            Object l_obj = ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
            
            assertEquals("0D",((WEB3GentradeTradingClendarContext)l_obj).getInstitutionCode());
            assertEquals("02",((WEB3GentradeTradingClendarContext)l_obj).getOrderAcceptTransaction());
            assertEquals("381",((WEB3GentradeTradingClendarContext)l_obj).getBranchCode());
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
     * リクエストデータ= new 株価指数オプション新規建注文完了リクエスト
     * リクエストデータ.建区分 == ”買建”の場合、”01：買付（新規建買）”
     * 
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
            l_tradingTimeParams.setBranchCode("387");
            l_tradingTimeParams.setTradingTimeType("11");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setProductCode("0008");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setInstitutionCode("0D");
            l_tradingTimeParams1.setBranchCode("381");
            l_tradingTimeParams1.setTradingTimeType("11");
            l_tradingTimeParams1.setBizDateType("1");
            l_tradingTimeParams1.setMarketCode("0");
            l_tradingTimeParams1.setProductCode("0008");
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            
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

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "lockAccount",
                new Class[] {String.class,String.class,String.class},
                null);

            WEB3OptionsOpenMarginCompleteRequest l_request = new WEB3OptionsOpenMarginCompleteRequest();
            l_request.contractType = "1";
            l_request.opProductCode = "3256";
            
            Object[] l_serviceParam = new Object[]{l_request};
            Method l_method = WEB3OptionOpenContractOrderServiceImpl.class.getMethod("execute",new Class[]{WEB3GenRequest.class});
            this.l_interceptor.onCall(l_method,l_serviceParam);
            Object l_obj = ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
            
            assertEquals("0D",((WEB3GentradeTradingClendarContext)l_obj).getInstitutionCode());
            assertEquals("01",((WEB3GentradeTradingClendarContext)l_obj).getOrderAcceptTransaction());
            assertEquals("381",((WEB3GentradeTradingClendarContext)l_obj).getBranchCode());
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
     * リクエストデータ= new 株価指数オプション新規建注文完了リクエスト
     * リクエストデータ.建区分 == ”売建”の場合、”02：売付（新規建売）”）
     * 
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
            l_tradingTimeParams.setBranchCode("387");
            l_tradingTimeParams.setTradingTimeType("11");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setProductCode("0008");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setInstitutionCode("0D");
            l_tradingTimeParams1.setBranchCode("381");
            l_tradingTimeParams1.setTradingTimeType("11");
            l_tradingTimeParams1.setBizDateType("1");
            l_tradingTimeParams1.setMarketCode("0");
            l_tradingTimeParams1.setProductCode("0008");
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            
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

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeAccountManager",
                "lockAccount",
                new Class[] {String.class,String.class,String.class},
                null);

            WEB3OptionsOpenMarginCompleteRequest l_request = new WEB3OptionsOpenMarginCompleteRequest();
            l_request.contractType = "2";
            l_request.opProductCode = "3256";
            
            Object[] l_serviceParam = new Object[]{l_request};
            Method l_method = WEB3OptionOpenContractOrderServiceImpl.class.getMethod("execute",new Class[]{WEB3GenRequest.class});
            this.l_interceptor.onCall(l_method,l_serviceParam);
            Object l_obj = ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
            
            assertEquals("0D",((WEB3GentradeTradingClendarContext)l_obj).getInstitutionCode());
            assertEquals("02",((WEB3GentradeTradingClendarContext)l_obj).getOrderAcceptTransaction());
            assertEquals("381",((WEB3GentradeTradingClendarContext)l_obj).getBranchCode());
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
}
@
