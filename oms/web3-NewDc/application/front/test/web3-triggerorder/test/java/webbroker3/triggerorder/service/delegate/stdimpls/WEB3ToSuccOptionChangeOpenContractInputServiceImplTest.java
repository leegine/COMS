head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.21.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3ToSuccOptionChangeOpenContractInputServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3ToSuccOptionChangeOpenContractInputServiceImplTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/22 于瀟（中訊）新規作成
*/
package webbroker3.triggerorder.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Calendar;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ChannelDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.EnableOrderConditionParams;
import webbroker3.gentrade.data.EnableOrderConditionRow;
import webbroker3.gentrade.data.OrderexecutionEndParams;
import webbroker3.gentrade.data.OrderexecutionEndRow;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.message.WEB3OptionsOpenMarginChangeInputRequest;
import webbroker3.mock.TestBaseForMock;
import webbroker3.triggerorder.WEB3ToSuccIfoOrderUnitImpl;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitParams;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitRow;
import webbroker3.triggerorder.message.WEB3SuccFuturesOpenChangeConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenChangeInputRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenChangeInputResponse;
import webbroker3.triggerorder.util.WEB3ToSuccOrderManagerUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3ToSuccOptionChangeOpenContractInputServiceImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3ToSuccOptionChangeOpenContractInputServiceImplTest.class);

    public WEB3ToSuccOptionChangeOpenContractInputServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        WEB3ToSuccOrderManagerUtility.changeOrderManager();
        ThreadLocalSystemAttributesRegistry.setAttribute(
            "xblocks.gtl.attributes.systemtimestamp",
            new Timestamp(WEB3DateUtility.getDate("20080520", "yyyyMMdd").getTime()));
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
        TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
        TestDBUtility.deleteAll(InstitutionRow.TYPE);
        TestDBUtility.deleteAll(BranchRow.TYPE);
        TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
        TestDBUtility.deleteAll(MainAccountRow.TYPE);
        TestDBUtility.deleteAll(SubAccountRow.TYPE);
    }

    /**
     * SYSTEM_ERROR_80017
     * 
     * l_request == null
     */
    public void testExecute_C0001()
    {
        final String STR_METHOD_NAME = "testExecute_C0001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3ToSuccOptionChangeOpenContractInputServiceImpl l_impl =
            new WEB3ToSuccOptionChangeOpenContractInputServiceImpl();
        
        try
        {
            l_impl.execute(null);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * 正常終了
     * 
     * l_request instanseof WEB3SuccOptionsOpenChangeInputRequest的場合
     */
    public void testExecute_C0002()
    {
        final String STR_METHOD_NAME = "testExecute_C0002()";
        log.entering(STR_METHOD_NAME);
        
        WEB3SubExecuteToSuccOptionChangeOpenContractInputServiceImpl l_subImpl =
            new WEB3SubExecuteToSuccOptionChangeOpenContractInputServiceImpl();
        
        WEB3SuccOptionsOpenChangeInputRequest l_request =
            new WEB3SuccOptionsOpenChangeInputRequest();
        
        WEB3GenResponse l_response = null;
        try
        {
            l_response = l_subImpl.execute(l_request);
            assertEquals("12", ((WEB3SuccOptionsOpenChangeInputResponse)l_response).currentPrice);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    class WEB3SubExecuteToSuccOptionChangeOpenContractInputServiceImpl extends WEB3ToSuccOptionChangeOpenContractInputServiceImpl
    {
        protected WEB3SuccOptionsOpenChangeInputResponse createInputScreen(
                WEB3SuccOptionsOpenChangeInputRequest l_request) throws WEB3BaseException
        {
            WEB3SuccOptionsOpenChangeInputResponse l_response =
                new WEB3SuccOptionsOpenChangeInputResponse();
            l_response.currentPrice = "12";
            return l_response;
        }
    }
    
    /**
     * SYSTEM_ERROR_80018
     * 
     * l_request instanceof WEB3GenRequest的場合
     */
    public void testExecute_C0003()
    {
        final String STR_METHOD_NAME = "testExecute_C0003()";
        log.entering(STR_METHOD_NAME);
        
        WEB3ToSuccOptionChangeOpenContractInputServiceImpl l_impl =
            new WEB3ToSuccOptionChangeOpenContractInputServiceImpl();
        
        WEB3SuccFuturesOpenChangeConfirmRequest l_request =
            new WEB3SuccFuturesOpenChangeConfirmRequest();
        
        try
        {
            l_impl.execute(l_request);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * 正常終了
     */
    public void testGetChangeOrderUnit_C0001()
    {
        final String STR_METHOD_NAME = "testGetChangeOrderUnit_C0001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3ToSuccOptionChangeOpenContractInputServiceImpl l_impl =
            new WEB3ToSuccOptionChangeOpenContractInputServiceImpl();
        
        WEB3OptionsOpenMarginChangeInputRequest l_request =
            new WEB3OptionsOpenMarginChangeInputRequest();
        l_request.id = "1001";
        try
        {
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl =
                new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl", "createIfoOrderUnit", new Class[]
                {WEB3ToSuccIfoOrderUnitImpl.class},
                l_toSuccIfoOrderUnitImpl);
                    
            IfoOrderUnit l_ifoOrderUnit = l_impl.getChangeOrderUnit(l_request);
            assertEquals(33381L, l_ifoOrderUnit.getBranchId());
            
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * SYSTEM_ERROR_80017
     * 
     * l_request == null
     */
    public void testGetChangeOrderUnit_C0002()
    {
        final String STR_METHOD_NAME = "testGetChangeOrderUnit_C0002()";
        log.entering(STR_METHOD_NAME);
        
        WEB3ToSuccOptionChangeOpenContractInputServiceImpl l_impl =
            new WEB3ToSuccOptionChangeOpenContractInputServiceImpl();
        
        try
        {
            l_impl.getChangeOrderUnit(null);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * SYSTEM_ERROR_80017
     * 
     * l_orderUnit == null
     */
    public void testValidateOrderForChangeability_C0001()
    {
        final String STR_METHOD_NAME = "testValidateOrderForChangeability_C0001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3ToSuccOptionChangeOpenContractInputServiceImpl l_impl =
            new WEB3ToSuccOptionChangeOpenContractInputServiceImpl();
        
        try
        {
            l_impl.validateOrderForChangeability(null);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * 正常終了
     */
    public void testValidateOrderForChangeability_C0002()
    {
        final String STR_METHOD_NAME = "testValidateOrderForChangeability_C0002()";
        log.entering(STR_METHOD_NAME);
        
        WEB3ToSuccOptionChangeOpenContractInputServiceImpl l_impl =
            new WEB3ToSuccOptionChangeOpenContractInputServiceImpl();
        
        try
        {
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setBizDate("20080808");
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            //pass validateOrderForChangeability() 
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateTradeCloseChangeOrCancel(true);
//            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
//            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderId(1001L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);//
            
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(new Timestamp(WEB3DateUtility.getDate("20080520","yyyyMMdd").getTime()), "1");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(WEB3DateUtility.getDate("20080520","yyyyMMdd"));
            
            WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl =
                new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);
            
            l_impl.validateOrderForChangeability(l_toSuccIfoOrderUnitImpl);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * BUSINESS_ERROR_00080
     * 
     * リクエストデータの整合性をチェックする。
     */
    public void testCreateInputScreen_C0001()
    {
        final String STR_METHOD_NAME = "testCreateInputScreen_C0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3ToSuccOptionChangeOpenContractInputServiceImpl l_impl =
                new WEB3ToSuccOptionChangeOpenContractInputServiceImpl();
            WEB3SuccOptionsOpenChangeInputRequest l_request =
                new WEB3SuccOptionsOpenChangeInputRequest();
            l_impl.createInputScreen(l_request);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00080, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * SYSTEM_ERROR_80005
     * 
     * validate連続注文(補助口座, ProductTypeEnum, String, String, OrderUnit)抛異常
     */
    public void testCreateInputScreen_C0002()
    {
        final String STR_METHOD_NAME = "testCreateInputScreen_C0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            WEB3SubCreateInputScreenToSuccOptionChangeOpenContractInputServiceImpl l_impl =
                new WEB3SubCreateInputScreenToSuccOptionChangeOpenContractInputServiceImpl();
            WEB3SuccOptionsOpenChangeInputRequest l_request =
                new WEB3SuccOptionsOpenChangeInputRequest();
            l_request.id = "1001";
            
            l_impl.createInputScreen(l_request);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseRuntimeException l_ex)
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
     */
    public void testCreateInputScreen_C0003()
    {
        final String STR_METHOD_NAME = "testCreateInputScreenCase3()";
        log.entering(STR_METHOD_NAME);
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateSuccOrder", 
                    new Class[]{WEB3GentradeSubAccount.class, ProductTypeEnum.class,
                            String.class, String.class, OrderUnit.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "validateOrder", 
                    new Class[]
                    { SubAccount.class, String.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateOrderForChangeability",
                    new Class[] {Order.class, boolean.class},
                    null);
            
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//            TradingTimeParams l_tradingTimeParams =
//                TestDBUtility.getTradingTimeRow();
//            l_tradingTimeParams.setInstitutionCode("0D");
//            l_tradingTimeParams.setBranchCode("123");
//            l_tradingTimeParams.setMarketCode("N1");
//            l_tradingTimeParams.setTradingTimeType("01");
//            l_tradingTimeParams.setProductCode("0");
//            l_tradingTimeParams.setBizDateType("2");
//            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(EnableOrderConditionRow.TYPE);
            EnableOrderConditionParams l_enableOrderConditionParams =
                TestDBUtility.getEnableOrderConditionRow();
            l_enableOrderConditionParams.setInstitutionCode("0D");
            l_enableOrderConditionParams.setFutureOptionDiv("2");
            l_enableOrderConditionParams.setMarginTradingDiv("0");
            l_enableOrderConditionParams.setMarketCode("0");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);
            
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_RsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_RsvIfoOrderUnitParams.setOrderUnitId(1111);
            l_RsvIfoOrderUnitParams.setOrderId(1001);
            l_RsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
            l_RsvIfoOrderUnitParams.setProductId(1006169090018L);
            l_RsvIfoOrderUnitParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_RsvIfoOrderUnitParams);
           
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setAccountId(101001010010L);
            l_ifoOrderUnitParams.setSubAccountId(10100101001007L);
            l_ifoOrderUnitParams.setBranchId(33381);
            l_ifoOrderUnitParams.setTraderId(null);
            l_ifoOrderUnitParams.setOrderId(1001);
            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
            l_ifoOrderUnitParams.setLastExecutionSerialNo(0);
            l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderUnitParams.setFutureOptionDiv("1");
            l_ifoOrderUnitParams.setMarketId(1002);
            l_ifoOrderUnitParams.setQuantity(100);
            l_ifoOrderUnitParams.setPrice(200);
            l_ifoOrderUnitParams.setBizDate("20040101");
            l_ifoOrderUnitParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderManager =
               (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
            IfoOrderUnit l_ifoOrderUnit =(IfoOrderUnit)(l_orderManager.getOrderUnit(1001));
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "createIfoOrderUnit", new Class[]
                    {WEB3ToSuccIfoOrderUnitImpl.class},
                    l_ifoOrderUnit);

            Calendar ca1 =  Calendar.getInstance();
            ca1.set(2007,6-1,19);
            
//            Date date1 = ca1.getTime();
//            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(date1);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("0");
            l_marketParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006169090018L);
            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(330304148080000L);
            l_tradedProductParams.setProductId(1006169090018L);
            l_tradedProductParams.setMarketId(1002);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductParams.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setTradedProductId(330304148080000L);
            l_ifoTradedProductParams.setProductId(1006169090018L);
            l_ifoTradedProductParams.setUnitSize(10000L);
            l_ifoTradedProductParams.setMarketId(1002);
            l_ifoTradedProductParams.setLastTradingDate(WEB3DateUtility.getDate("20090820","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(OrderexecutionEndRow.TYPE);
            OrderexecutionEndParams l_orderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);
            
            TestDBUtility.deleteAll(IfoTradedProductUpdqParams.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setProductId(1006169090018L);
            l_ifoTradedProductUpdqParams.setInstitutionCode("0D");
            l_ifoTradedProductUpdqParams.setMarketId(1002);
            l_ifoTradedProductUpdqParams.setTradedProductId(330304148080000L);
            l_ifoTradedProductUpdqParams.setLastTradingDate(WEB3DateUtility.getDate("20080820","yyyyMMdd"));
//            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
//            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
//            Date l_datpreBizDate = new WEB3GentradeBizDate(
//                new Timestamp(l_datBizDate.getTime())).roll(1);
//            String l_strCreateDate = l_format.format(WEB3DateUtility.getDate("20080520","yyyyMMdd"));
            l_ifoTradedProductUpdqParams.setValidForBizDate("20080520");  
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockValidateTradeCloseChangeOrCancel(true);
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(new Timestamp(WEB3DateUtility.getDate("20080520","yyyyMMdd").getTime()), "1");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(WEB3DateUtility.getDate("20080520","yyyyMMdd"));
            
            WEB3IfoTradedProductImpl l_tradedProductImpl = new WEB3IfoTradedProductImpl(330304148080000L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateTradedProduct",
                    new Class[] {WEB3IfoProductImpl.class,
                            WEB3GentradeMarket.class,
                            boolean.class,
                            boolean.class},
                    l_tradedProductImpl);
            
            String[] l_strHandlingPossibleExecConds =
                new String[1];
            l_strHandlingPossibleExecConds[0] = "gun";
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "getHandlingPossibleExecConds", new Class[]
                    {String[].class, String[].class},
                    l_strHandlingPossibleExecConds);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateSuccOrder", new Class[]
                    {WEB3GentradeSubAccount.class, ProductTypeEnum.class, String.class, String.class, OrderUnit.class}, null);
            
            WEB3SuccOptionsOpenChangeInputRequest l_request =
                new WEB3SuccOptionsOpenChangeInputRequest();
            l_request.id = "1001";
            WEB3SubCreateInputScreenToSuccOptionChangeOpenContractInputServiceImpl l_impl =
                new WEB3SubCreateInputScreenToSuccOptionChangeOpenContractInputServiceImpl();
            WEB3SuccOptionsOpenChangeInputResponse l_response =
                l_impl.createInputScreen(l_request);
            
            //連続注文共通情報：　@先物OP予約注文単位.create連続注文共通情報()をセット。
            assertEquals("1001", l_response.succCommonInfo.parentOrderId);
            //単価調整値情報：　@　@先物OP予約注文単位.create単価調整値情報()をセット
            assertNull(l_response.priceAdjustmentValueInfo);
            //執行条件一覧：　@"無条件"のみをセット。
            assertEquals("1", l_response.execCondList[0]);
            //発注条件区分一覧：　@"指定なし"のみをセット。
            assertEquals("0", l_response.orderCondTypeList[0]);
            //Ｗ指値用執行条件一覧：　@nullをセット。
            assertNull(l_response.wlimitExecCondList);
            //有効期限開始日：　@(*1)取扱可能注文条件.get出来るまで注文開始日<取引最終日考慮>()の戻り値をセット
            assertEquals("20080520", WEB3DateUtility.formatDate(l_response.expirationStartDate, "yyyyMMdd"));
            //有効期限最終日：　@(*1)取扱可能注文条件.get出来るまで注文最終日<取引最終日考慮>()の戻り値をセット。
            assertEquals("20080520", WEB3DateUtility.formatDate(l_response.expirationEndDate, "yyyyMMdd"));
            //有効期限内祝日一覧：　@(*1)取扱可能注文条件.get注文期限内祝日一覧()の戻り値をセット。
            assertNull(l_response.holidayList);
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);

    }
    
    private class WEB3SubCreateInputScreenToSuccOptionChangeOpenContractInputServiceImpl extends WEB3ToSuccOptionChangeOpenContractInputServiceImpl
    {
        public SubAccount getSubAccount() throws WEB3BaseException 
        {
            final String STR_METHOD_NAME = "getSubAccount()";
            log.entering(STR_METHOD_NAME);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            l_mainAccountParams.setBranchId(33381);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512203L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            
            try
            {
                TestDBUtility.deleteAll(MainAccountRow.TYPE);
                TestDBUtility.insertWithDel(l_mainAccountParams);
                
                TestDBUtility.deleteAll(SubAccountRow.TYPE);
                TestDBUtility.insertWithDel(l_subAccountParams);
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeSubAccount l_subAccount = null;
            try
            {
                //取得補助口座
                l_subAccount =
                    (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(
                        333812512203L,
                        33381251220301L);
            }
            catch (NotFoundException l_nfe)
            {
                log.error("データ不整合エラー。", l_nfe);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe); 
            }
            
            log.exiting(STR_METHOD_NAME);
            return l_subAccount;
        }
        
        public Trader getTrader() throws WEB3SystemLayerException
        {
            return null;
        }
        
        public String getLoginChannel()
        {
            return WEB3ChannelDef.BRANCH;
        }
        
        protected void validateOrderForChangeability(IfoOrderUnit l_orderUnit)
            throws WEB3BaseException
        {
            
        }
    }
}
@
