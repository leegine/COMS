head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.19.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3ToSuccOptionSettleContractInputServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （WEB3ToSuccOptionSettleContractInputServiceImplTest.java）
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/22 トウ鋒鋼 (中訊) 新規作成
*/
package webbroker3.triggerorder.service.delegate.stdimpls;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoContractSettleOrderUnitImpl;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ExecutionConditionDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3ReserveOrderTradingTypeDef;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.ifo.WEB3IfoContractImpl;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.message.WEB3FuturesCloseMarginInputRequest;
import webbroker3.ifo.message.WEB3FuturesContractReferenceUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsSortKey;
import webbroker3.ifo.message.WEB3OptionsContractReferenceUnit;
import webbroker3.mock.TestBaseForMock;
import webbroker3.triggerorder.message.WEB3SuccCommonInfo;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseInputRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseInputResponse;
import webbroker3.triggerorder.util.WEB3ToSuccOrderManagerUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3ToSuccOptionSettleContractInputServiceImplTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccOptionSettleContractInputServiceImplTest.class);

    public WEB3ToSuccOptionSettleContractInputServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        WEB3ToSuccOrderManagerUtility.changeOrderManager();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * execute
     * リクエストがnullの場合
     */
    public void testExecute_0001()
    {
        final String STR_METHOD_NAME = "testExecute_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccOptionSettleContractInputServiceImpl l_serviceImpl =
            new WEB3ToSuccOptionSettleContractInputServiceImplForTest();
        
        try
        {
            l_serviceImpl.execute(null);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * execute
     * リクエストデータの型が（連続）株価指数先物返済入力画面リクエストの場合
     */
    public void testExecute_0002()
    {
        final String STR_METHOD_NAME = "testExecute_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccOptionSettleContractInputServiceImpl l_serviceImpl =
            new WEB3ToSuccOptionSettleContractInputServiceImplForTest();
        
        try
        {
            WEB3SuccOptionsCloseInputRequest l_request = new WEB3SuccOptionsCloseInputRequest();
            WEB3SuccOptionsCloseInputResponse l_response =
                (WEB3SuccOptionsCloseInputResponse)l_serviceImpl.execute(l_request);
            
            assertEquals("1", l_response.execCondList[0]);
            assertEquals("0", l_response.orderCondTypeList[0]);
            assertNull(l_response.wlimitExecCondList);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * execute
     * リクエストデータの型が不正の場合
     */
    public void testExecute_0003()
    {
        final String STR_METHOD_NAME = "testExecute_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccOptionSettleContractInputServiceImpl l_serviceImpl =
            new WEB3ToSuccOptionSettleContractInputServiceImplForTest();
        
        try
        {
            WEB3SuccOptionsCloseConfirmRequest l_request = new WEB3SuccOptionsCloseConfirmRequest();
            l_serviceImpl.execute(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetSettleContractInputScreen_0001()
    {
        final String STR_METHOD_NAME = "testGetSettleContractInputScreen_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccOptionSettleContractInputServiceImpl l_serviceImpl =
            new WEB3ToSuccOptionSettleContractInputServiceImpl();
        
        WEB3SuccOptionsCloseInputRequest l_request = new WEB3SuccOptionsCloseInputRequest();
        try
        {
            l_serviceImpl.getSettleContractInputScreen(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02251, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetSettleContractInputScreen_0002()
    {
        final String STR_METHOD_NAME = "testGetSettleContractInputScreen_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccOptionSettleContractInputServiceImpl l_serviceImpl =
            new WEB3ToSuccOptionSettleContractInputServiceImpl();
        
        WEB3SuccOptionsCloseInputRequest l_request = new WEB3SuccOptionsCloseInputRequest();
        WEB3SuccCommonInfo l_succCommonInfo = new WEB3SuccCommonInfo();
        
        l_succCommonInfo.parentOrderId = "1001";
        l_succCommonInfo.succTradingType = WEB3ReserveOrderTradingTypeDef.SETTLE_OP_ASSUMPTION_ORDER;
        l_request.succCommonInfo = l_succCommonInfo;
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                "isReversingTrade",
                new Class[] {String.class, OrderUnit.class},
                Boolean.TRUE);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                "validateSuccOrder",
                new Class[] {WEB3GentradeSubAccount.class, 
                ProductTypeEnum.class, String.class, String.class, OrderUnit.class},
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    "パラメータ値不正。"));
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("3");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(1006169090018L);
            l_tradedProductParams.setMarketId(1002);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_tradedProductParams);
          
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setValidForBizDate(null);
            l_ifoTradedProductParams.setTradedProductId(1006160060005L);
            l_ifoTradedProductParams.setProductId(1006169090018L);
            l_ifoTradedProductParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006169090018L);
            l_ifoProductParams.setTargetMarketId(10);
            l_ifoProductParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_ifoProductParams);

            l_serviceImpl.getSettleContractInputScreen(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetSettleContractInputScreen_0003()
    {
        final String STR_METHOD_NAME = "testGetSettleContractInputScreen_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccOptionSettleContractInputServiceImpl l_serviceImpl =
            new WEB3ToSuccOptionSettleContractInputServiceImpl();
        
        WEB3SuccOptionsCloseInputRequest l_request = new WEB3SuccOptionsCloseInputRequest();
        WEB3SuccCommonInfo l_succCommonInfo = new WEB3SuccCommonInfo();
        
        l_succCommonInfo.parentOrderId = "1001";
        l_succCommonInfo.succTradingType = WEB3ReserveOrderTradingTypeDef.SETTLE_OP_ASSUMPTION_ORDER;
        l_request.succCommonInfo = l_succCommonInfo;
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                "isReversingTrade",
                new Class[] {String.class, OrderUnit.class},
                Boolean.TRUE);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                "validateSuccOrder",
                new Class[] {WEB3GentradeSubAccount.class, 
                ProductTypeEnum.class, String.class, String.class, OrderUnit.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateSuccOrderMaxQuantity",
                    new Class[]{OrderUnit.class},
                    new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                        "パラメータ値不正。"));
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("3");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            l_serviceImpl.getSettleContractInputScreen(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetSettleContractInputScreen_0004()
    {
        final String STR_METHOD_NAME = "testGetSettleContractInputScreen_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccOptionSettleContractInputServiceImpl l_serviceImpl =
            new WEB3ToSuccOptionSettleContractInputServiceImpl();
        
        WEB3SuccOptionsCloseInputRequest l_request = new WEB3SuccOptionsCloseInputRequest();
        WEB3SuccCommonInfo l_succCommonInfo = new WEB3SuccCommonInfo();
        
        l_succCommonInfo.parentOrderId = "1001";
        l_succCommonInfo.succTradingType = WEB3ReserveOrderTradingTypeDef.SETTLE_OP_ASSUMPTION_ORDER;
        l_request.succCommonInfo = l_succCommonInfo;
        l_request.id = new String[]{"1001"};
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                "isReversingTrade",
                new Class[] {String.class, OrderUnit.class},
                Boolean.TRUE);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                "validateSuccOrder",
                new Class[] {WEB3GentradeSubAccount.class, 
                ProductTypeEnum.class, String.class, String.class, OrderUnit.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateSuccOrderMaxQuantity",
                    new Class[]{OrderUnit.class},
                    null);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("3");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setProductId(1006169090018L);
            l_ifoContractParams.setContractPrice(20.2);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(1006169090018L);
            l_tradedProductParams.setMarketId(1002);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_tradedProductParams);
          
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setValidForBizDate(null);
            l_ifoTradedProductParams.setTradedProductId(1006160060005L);
            l_ifoTradedProductParams.setProductId(1006169090018L);
            l_ifoTradedProductParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006169090018L);
            l_ifoProductParams.setTargetMarketId(10);
            l_ifoProductParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "createIfoContract", new Class[]
                    {IfoOrderUnit.class},
                    new WEB3IfoContractImpl(l_ifoContractParams));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "validateOrder", new Class[]
                    { SubAccount.class, String.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateTradedProduct",
                    new Class[]
                    { WEB3IfoProductImpl.class, WEB3GentradeMarket.class, boolean.class, boolean.class },
                    new WEB3IfoTradedProductImpl(l_tradedProductParams));
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                  "xblocks.gtl.attributes.systemtimestamp",
                  new Timestamp((WEB3DateUtility.getDate("20080422", "yyyyMMdd")).getTime()));
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0005");
            l_tradingTimeParams.setBizDateType("2");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setInstitutionCode("0D");
            l_tradingTimeParams1.setBranchCode("123");
            l_tradingTimeParams1.setMarketCode("N1");
            l_tradingTimeParams1.setTradingTimeType("01");
            l_tradingTimeParams1.setProductCode("0005");
            l_tradingTimeParams1.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            TradingTimeParams l_tradingTimeParams2 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams2.setInstitutionCode("0D");
            l_tradingTimeParams2.setBranchCode("123");
            l_tradingTimeParams2.setMarketCode("N1");
            l_tradingTimeParams2.setTradingTimeType("01");
            l_tradingTimeParams2.setProductCode("0005");
            l_tradingTimeParams2.setBizDateType("3");
            TestDBUtility.insertWithDel(l_tradingTimeParams2);
            TradingTimeParams l_tradingTimeParams3 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams3.setInstitutionCode("0D");
            l_tradingTimeParams3.setBranchCode("123");
            l_tradingTimeParams3.setMarketCode("N1");
            l_tradingTimeParams3.setTradingTimeType("01");
            l_tradingTimeParams3.setProductCode("0005");
            l_tradingTimeParams3.setBizDateType("4");
            TestDBUtility.insertWithDel(l_tradingTimeParams3);
            TestDBUtility.commit();
            WEB3SuccOptionsCloseInputResponse l_response = l_serviceImpl.getSettleContractInputScreen(l_request);
            
            WEB3GentradeTradingClendarContext l_clendarContext =
                (WEB3GentradeTradingClendarContext)
                    ThreadLocalSystemAttributesRegistry.getAttribute("web3.tradingcalendarcontext");
            
            assertEquals("0005", l_clendarContext.getProductCode());
            assertEquals("0005", l_response.targetProductCode);
            assertEquals("1", l_response.execCondList[0]);
            assertEquals("0", l_response.orderCondTypeList[0]);
            assertNull(l_response.wlimitExecCondList);
        }
        catch (Exception l_ex)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            log.error("", l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetSettleContractInputScreen_0005()
    {
        final String STR_METHOD_NAME = "testGetSettleContractInputScreen_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccOptionSettleContractInputServiceImpl l_serviceImpl =
            new WEB3ToSuccOptionSettleContractInputServiceImplForTest1();
        
        WEB3SuccOptionsCloseInputRequest l_request = new WEB3SuccOptionsCloseInputRequest();
        WEB3SuccCommonInfo l_succCommonInfo = new WEB3SuccCommonInfo();
        
        l_succCommonInfo.parentOrderId = "1001";
        l_succCommonInfo.succTradingType = WEB3ReserveOrderTradingTypeDef.SETTLE_OP_ASSUMPTION_ORDER;
        l_request.succCommonInfo = l_succCommonInfo;
        l_request.id = new String[]{"1001"};
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                "isReversingTrade",
                new Class[] {String.class, OrderUnit.class},
                Boolean.FALSE);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                "validateSuccOrder",
                new Class[] {WEB3GentradeSubAccount.class, 
                ProductTypeEnum.class, String.class, String.class, OrderUnit.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateSuccOrderMaxQuantity",
                    new Class[]{OrderUnit.class},
                    null);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("3");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setProductId(1006169090018L);
            l_ifoContractParams.setContractPrice(20.2);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(1006169090018L);
            l_tradedProductParams.setMarketId(1002);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_tradedProductParams);
          
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setValidForBizDate(null);
            l_ifoTradedProductParams.setTradedProductId(1006160060005L);
            l_ifoTradedProductParams.setProductId(1006169090018L);
            l_ifoTradedProductParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006169090018L);
            l_ifoProductParams.setInstitutionCode("0D");
            l_ifoProductParams.setTargetMarketId(1002);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "createIfoContract", new Class[]
                    {IfoOrderUnit.class},
                    new WEB3IfoContractImpl(l_ifoContractParams));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "validateOrder", new Class[]
                    { SubAccount.class, String.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateTradedProduct",
                    new Class[]
                    { WEB3IfoProductImpl.class, WEB3GentradeMarket.class, boolean.class, boolean.class },
                    new WEB3IfoTradedProductImpl(l_tradedProductParams));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "getHandlingPossibleExecConds", new Class[]
                    { String[].class, String[].class },
                    new String[]{"1"});
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "xblocks.gtl.attributes.systemtimestamp",
                    new Timestamp((WEB3DateUtility.getDate("20080422", "yyyyMMdd")).getTime()));
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0005");
            l_tradingTimeParams.setBizDateType("2");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setInstitutionCode("0D");
            l_tradingTimeParams1.setBranchCode("123");
            l_tradingTimeParams1.setMarketCode("N1");
            l_tradingTimeParams1.setTradingTimeType("01");
            l_tradingTimeParams1.setProductCode("0005");
            l_tradingTimeParams1.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            TradingTimeParams l_tradingTimeParams2 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams2.setInstitutionCode("0D");
            l_tradingTimeParams2.setBranchCode("123");
            l_tradingTimeParams2.setMarketCode("N1");
            l_tradingTimeParams2.setTradingTimeType("01");
            l_tradingTimeParams2.setProductCode("0005");
            l_tradingTimeParams2.setBizDateType("3");
            TestDBUtility.insertWithDel(l_tradingTimeParams2);
            TradingTimeParams l_tradingTimeParams3 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams3.setInstitutionCode("0D");
            l_tradingTimeParams3.setBranchCode("123");
            l_tradingTimeParams3.setMarketCode("N1");
            l_tradingTimeParams3.setTradingTimeType("01");
            l_tradingTimeParams3.setProductCode("0005");
            l_tradingTimeParams3.setBizDateType("4");
            TestDBUtility.insertWithDel(l_tradingTimeParams3);
            WEB3SuccOptionsCloseInputResponse l_response = l_serviceImpl.getSettleContractInputScreen(l_request);
            
            WEB3GentradeTradingClendarContext l_clendarContext =
                (WEB3GentradeTradingClendarContext)
                    ThreadLocalSystemAttributesRegistry.getAttribute("web3.tradingcalendarcontext");
            
            assertEquals("0005", l_clendarContext.getProductCode());
            assertEquals("1", l_response.execCondList[0]);
            assertEquals("0", l_response.orderCondTypeList[0]);
            assertNull(l_response.wlimitExecCondList);
        }
        catch (Exception l_ex)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            log.error("", l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetContract_0001()
    {
        final String STR_METHOD_NAME = "testGetContract_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccOptionSettleContractInputServiceImpl l_serviceImpl =
            new WEB3ToSuccOptionSettleContractInputServiceImpl();
        WEB3SuccOptionsCloseInputRequest l_request = new WEB3SuccOptionsCloseInputRequest();
        try
        {
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setProductId(1006169090018L);
            l_ifoContractParams.setContractPrice(20.2);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "isReversingTrade",
                    new Class[] {String.class, OrderUnit.class},
                    Boolean.FALSE);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "createIfoContract", new Class[]
                    {IfoOrderUnit.class},
                    new WEB3IfoContractImpl(l_ifoContractParams));
            
            l_request.succCommonInfo = new WEB3SuccCommonInfo();
            l_request.succCommonInfo.parentOrderId = "1001";
            l_request.succCommonInfo.succTradingType = "1";
            l_request.id = new String[]{"1001"};
            WEB3IfoContractImpl l_ifoContractImpl = l_serviceImpl.getContract(l_request);
            
            assertEquals("20.2", "" + l_ifoContractImpl.getContractPrice());
        }
        catch (Exception l_ex)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            log.error("", l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetContract_0002()
    {
        final String STR_METHOD_NAME = "testGetContract_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccOptionSettleContractInputServiceImpl l_serviceImpl =
            new WEB3ToSuccOptionSettleContractInputServiceImpl();
        WEB3SuccOptionsCloseInputRequest l_request = new WEB3SuccOptionsCloseInputRequest();
        try
        {
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setProductId(1006169090018L);
            l_ifoContractParams.setContractPrice(20.2);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "isReversingTrade",
                    new Class[] {String.class, OrderUnit.class},
                    Boolean.FALSE);
            
            l_request.succCommonInfo = new WEB3SuccCommonInfo();
            l_request.succCommonInfo.parentOrderId = "1001";
            l_request.succCommonInfo.succTradingType = "1";
            l_request.id = new String[]{"1001"};
            
            WEB3IfoContractImpl l_ifoContractImpl = l_serviceImpl.getContract(l_request);
            
            assertEquals("20.2", "" + l_ifoContractImpl.getContractPrice());
        }
        catch (Exception l_ex)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            log.error("", l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCreateContractReferenceUnitList_0001()
    {
        final String STR_METHOD_NAME = "testCreateContractReferenceUnitList_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccOptionSettleContractInputServiceImpl l_serviceImpl =
            new WEB3ToSuccOptionSettleContractInputServiceImpl();
        WEB3SuccOptionsCloseInputRequest l_request = new WEB3SuccOptionsCloseInputRequest();
        l_request.succCommonInfo = new WEB3SuccCommonInfo();
        l_request.succCommonInfo.parentOrderId = "1001";
        l_request.succCommonInfo.succTradingType = "1";
        try
        {
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006169090018L);
            l_ifoProductParams.setInstitutionCode("0D");
            l_ifoProductParams.setUnderlyingProductCode("0005");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "isReversingTrade",
                    new Class[] {String.class, OrderUnit.class},
                    Boolean.TRUE);
            
            WEB3OptionsContractReferenceUnit[] l_referenceUnits =
                l_serviceImpl.createContractReferenceUnitList(l_request);
            
            assertEquals("0005", l_referenceUnits[0].targetProductCode);
        }
        catch (Exception l_ex)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            log.error("", l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCreateContractReferenceUnitList_0002()
    {
        final String STR_METHOD_NAME = "testCreateContractReferenceUnitList_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccOptionSettleContractInputServiceImpl l_serviceImpl =
            new WEB3ToSuccOptionSettleContractInputServiceImpl();
        WEB3SuccOptionsCloseInputRequest l_request = new WEB3SuccOptionsCloseInputRequest();
        l_request.succCommonInfo = new WEB3SuccCommonInfo();
        l_request.succCommonInfo.parentOrderId = "1001";
        l_request.succCommonInfo.succTradingType = "1";
        l_request.id = new String[]{"1001"};
        try
        {
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006169090018L);
            l_ifoProductParams.setInstitutionCode("0D");
            l_ifoProductParams.setUnderlyingProductCode("0005");
            l_ifoProductParams.setTargetMarketId(1002);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setProductId(1006169090018L);
            l_ifoContractParams.setContractPrice(20.2);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setValidForBizDate(null);
            l_ifoTradedProductParams.setTradedProductId(1006160060005L);
            l_ifoTradedProductParams.setProductId(1006169090018L);
            l_ifoTradedProductParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(1006169090018L);
            l_tradedProductParams.setMarketId(1002);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "isReversingTrade",
                    new Class[] {String.class, OrderUnit.class},
                    Boolean.FALSE);
            
            WEB3OptionsContractReferenceUnit[] l_referenceUnits =
                l_serviceImpl.createContractReferenceUnitList(l_request);
            
            assertEquals("20.2", l_referenceUnits[0].contractPrice);
        }
        catch (Exception l_ex)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            log.error("", l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSortContractUnitList_0001()
    {
        final String STR_METHOD_NAME = "testSortContractUnitList_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccOptionSettleContractInputServiceImpl l_serviceImpl =
            new WEB3ToSuccOptionSettleContractInputServiceImpl();
        WEB3SuccOptionsCloseInputRequest l_request = new WEB3SuccOptionsCloseInputRequest();
        l_request.succCommonInfo = new WEB3SuccCommonInfo();
        l_request.succCommonInfo.parentOrderId = "1001";
        l_request.futOpSortKeys = new WEB3FuturesOptionsSortKey[1];
        l_request.futOpSortKeys[0] = new WEB3FuturesOptionsSortKey();
        l_request.futOpSortKeys[0].keyItem = "contractPrice";
        l_request.futOpSortKeys[0].ascDesc = "A";
        
        
        WEB3FuturesOptionsContractUnit[] l_contractUnits =
            new WEB3FuturesOptionsContractUnit[2];
        l_contractUnits[0] = new WEB3FuturesOptionsContractUnit();
        l_contractUnits[0].contractPrice = "20";
        
        l_contractUnits[1] = new WEB3FuturesOptionsContractUnit();
        l_contractUnits[1].contractPrice = "15";
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "isReversingTrade",
                    new Class[] {String.class, OrderUnit.class},
                    Boolean.FALSE);
            
            l_serviceImpl.sortContractUnitList(l_contractUnits, l_request);
            
            assertEquals("15", l_contractUnits[0].contractPrice);
            assertEquals("20", l_contractUnits[1].contractPrice);
        }
        catch (Exception l_ex)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            log.error("", l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSortContractUnitList_0002()
    {
        final String STR_METHOD_NAME = "testSortContractUnitList_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccOptionSettleContractInputServiceImpl l_serviceImpl =
            new WEB3ToSuccOptionSettleContractInputServiceImpl();
        WEB3SuccOptionsCloseInputRequest l_request = new WEB3SuccOptionsCloseInputRequest();
        l_request.succCommonInfo = new WEB3SuccCommonInfo();
        l_request.succCommonInfo.parentOrderId = "1001";
        
        
        WEB3FuturesOptionsContractUnit[] l_contractUnits =
            new WEB3FuturesOptionsContractUnit[2];
        l_contractUnits[0] = new WEB3FuturesOptionsContractUnit();
        l_contractUnits[0].contractPrice = "20";
        
        l_contractUnits[1] = new WEB3FuturesOptionsContractUnit();
        l_contractUnits[1].contractPrice = "15";
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "isReversingTrade",
                    new Class[] {String.class, OrderUnit.class},
                    Boolean.TRUE);
            
            l_serviceImpl.sortContractUnitList(l_contractUnits, l_request);
            
            assertEquals("20", l_contractUnits[0].contractPrice);
            assertEquals("15", l_contractUnits[1].contractPrice);
        }
        catch (Exception l_ex)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            log.error("", l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCreateContractReferenceUnit_0001()
    {
        final String STR_METHOD_NAME = "testCreateContractReferenceUnit_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3ToSuccOptionSettleContractInputServiceImpl l_serviceImpl =
                new WEB3ToSuccOptionSettleContractInputServiceImpl();
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setAccountId(333812512246L);
            l_ifoOrderUnitParams.setSonarMarketCode("1");
            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE);
            l_ifoOrderUnitParams.setBizDate("20080425");
            l_ifoOrderUnitParams.setQuantity(20);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006169090018L);
            l_ifoProductParams.setInstitutionCode("0D");
            l_ifoProductParams.setUnderlyingProductCode("0005");
            l_ifoProductParams.setTargetMarketId(1002);
            l_ifoProductParams.setMonthOfDelivery("200804");
            l_ifoProductParams.setStrikePrice(20);
            l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.CALL_OPTIONS);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            IfoContractSettleOrderUnitImpl l_ifoContractSettleOrderUnitImpl =
                new IfoContractSettleOrderUnitImpl(l_ifoOrderUnitParams.getOrderUnitId());
            
            WEB3OptionsContractReferenceUnit l_referenceUnit =
                l_serviceImpl.createContractReferenceUnit(l_ifoContractSettleOrderUnitImpl);
            
            assertEquals("0005" ,l_referenceUnit.targetProductCode);
            assertEquals("200804" ,l_referenceUnit.delivaryMonth);
            assertEquals("C" ,l_referenceUnit.opProductType);
            assertEquals("20" ,l_referenceUnit.strikePrice);
            assertEquals("1" ,l_referenceUnit.marketCode);
            assertEquals("1" ,l_referenceUnit.contractType);
            assertEquals("0","" + WEB3DateUtility.compareToDay(WEB3DateUtility.getDate("20080425" ,"yyyyMMdd"),l_referenceUnit.openDate));
            assertEquals("20" ,l_referenceUnit.contractOrderQuantity);
        }
        catch (Exception l_ex)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            log.error("", l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCreateContractReferenceUnit_0002()
    {
        final String STR_METHOD_NAME = "testCreateContractReferenceUnit_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3ToSuccOptionSettleContractInputServiceImpl l_serviceImpl =
                new WEB3ToSuccOptionSettleContractInputServiceImpl();
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setAccountId(333812512246L);
            l_ifoOrderUnitParams.setSonarMarketCode("1");
            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_SELL_TO_CLOSE);
            l_ifoOrderUnitParams.setBizDate("20080425");
            l_ifoOrderUnitParams.setQuantity(20);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006169090018L);
            l_ifoProductParams.setInstitutionCode("0D");
            l_ifoProductParams.setUnderlyingProductCode("0005");
            l_ifoProductParams.setTargetMarketId(1002);
            l_ifoProductParams.setMonthOfDelivery("200804");
            l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
            l_ifoProductParams.setStrikePrice(20);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            IfoContractSettleOrderUnitImpl l_ifoContractSettleOrderUnitImpl =
                new IfoContractSettleOrderUnitImpl(l_ifoOrderUnitParams.getOrderUnitId());
            
            WEB3OptionsContractReferenceUnit l_referenceUnit =
                l_serviceImpl.createContractReferenceUnit(l_ifoContractSettleOrderUnitImpl);
            
            assertEquals("0005" ,l_referenceUnit.targetProductCode);
            assertEquals("200804" ,l_referenceUnit.delivaryMonth);
            assertEquals("P" ,l_referenceUnit.opProductType);
            assertEquals("20" ,l_referenceUnit.strikePrice);
            assertEquals("1" ,l_referenceUnit.marketCode);
            assertEquals("2" ,l_referenceUnit.contractType);
            assertEquals("0","" + WEB3DateUtility.compareToDay(WEB3DateUtility.getDate("20080425" ,"yyyyMMdd"),l_referenceUnit.openDate));
            assertEquals("20" ,l_referenceUnit.contractOrderQuantity);
        }
        catch (Exception l_ex)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            log.error("", l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    private class WEB3ToSuccOptionSettleContractInputServiceImplForTest
        extends WEB3ToSuccOptionSettleContractInputServiceImpl
    {
        protected WEB3SuccOptionsCloseInputResponse getSettleContractInputScreen(
            WEB3SuccOptionsCloseInputRequest l_request) throws WEB3BaseException
        {
            WEB3SuccOptionsCloseInputResponse l_response = new WEB3SuccOptionsCloseInputResponse();
            //執行条件一覧
            l_response.execCondList = new String[]{WEB3ExecutionConditionDef.NO_CONDITION};
            //発注条件区分一覧
            l_response.orderCondTypeList = new String[]{WEB3OrderingConditionDef.DEFAULT};
            //Ｗ指値用執行条件一覧
            l_response.wlimitExecCondList = null;
            return l_response;
        }
        
    }
    
    private class WEB3ToSuccOptionSettleContractInputServiceImplForTest1
    extends WEB3ToSuccOptionSettleContractInputServiceImpl
   {
        protected WEB3FuturesContractReferenceUnit[] createContractReferenceUnitList(
            WEB3FuturesCloseMarginInputRequest l_request) throws WEB3BaseException
        {
            WEB3FuturesContractReferenceUnit l_referenceUnit = new WEB3FuturesContractReferenceUnit();
            l_referenceUnit.contractOrderQuantity = "20";
            return new WEB3FuturesContractReferenceUnit[]{l_referenceUnit};
        }
    }
}
@
