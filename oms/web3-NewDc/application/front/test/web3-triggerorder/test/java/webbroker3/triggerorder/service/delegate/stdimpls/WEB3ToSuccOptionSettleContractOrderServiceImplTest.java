head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.18.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3ToSuccOptionSettleContractOrderServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （WEB3ToSuccOptionSettleContractOrderServiceImplTest.java）
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/22 トウ鋒鋼 (中訊) 新規作成
*/
package webbroker3.triggerorder.service.delegate.stdimpls;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.ordersubmitter.io.IfoSettleContractOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoContractSettleOrderUnitImpl;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.data.EnableOrderConditionParams;
import webbroker3.gentrade.data.EnableOrderConditionRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.ifo.WEB3IfoContractImpl;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoSettleContractOrderSpec;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.message.WEB3FuturesOptionsCloseMarginContractUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;
import webbroker3.ifo.service.delegate.WEB3FuturesSettleContractOrderRequestAdapter;
import webbroker3.ifo.service.delegate.WEB3OptionSettleContractOrderRequestAdapter;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitParams;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitRow;
import webbroker3.triggerorder.message.WEB3SuccCommonInfo;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseConfirmResponse;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseInputRequest;
import webbroker3.triggerorder.message.WEB3SuccPriceAdjustmentValueInfo;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionSettleContractOrderRequestAdapter;
import webbroker3.triggerorder.util.WEB3ToSuccOrderManagerUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3ToSuccOptionSettleContractOrderServiceImplTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccOptionSettleContractOrderServiceImplTest.class);

    public WEB3ToSuccOptionSettleContractOrderServiceImplTest(String arg0)
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
                new Timestamp((WEB3DateUtility.getDate("20080422", "yyyyMMdd")).getTime()));
        ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate", null);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * リクエスト = null の場合
     *
     */
    public void testExecute_0001()
    {
        final String STR_METHOD_NAME = "testExecute_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3ToSuccOptionSettleContractOrderServiceImpl l_serviceImpl =
                new WEB3ToSuccOptionSettleContractOrderServiceImpl();
            
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
     * （連続）株価指数先物返済注文確認リクエストの場合
     *
     */
    public void testExecute_0002()
    {
        final String STR_METHOD_NAME = "testExecute_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3ToSuccOptionSettleContractOrderServiceImpl l_serviceImpl =
                new WEB3ToSuccOptionSettleContractOrderServiceImplForTest();
            
            WEB3SuccOptionsCloseConfirmRequest l_request = new WEB3SuccOptionsCloseConfirmRequest();
            WEB3SuccOptionsCloseConfirmResponse l_response =
                (WEB3SuccOptionsCloseConfirmResponse)l_serviceImpl.execute(l_request);
            assertEquals("15", l_response.afterAdjustmentPrice);
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
     * （連続）株価指数先物返済注文完了リクエストの場合
     *
     */
    public void testExecute_0003()
    {
        final String STR_METHOD_NAME = "testExecute_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3ToSuccOptionSettleContractOrderServiceImpl l_serviceImpl =
                new WEB3ToSuccOptionSettleContractOrderServiceImplForTest();
            
            WEB3SuccOptionsCloseCompleteRequest l_request = new WEB3SuccOptionsCloseCompleteRequest();
            WEB3SuccOptionsCloseCompleteResponse l_response =
                (WEB3SuccOptionsCloseCompleteResponse)l_serviceImpl.execute(l_request);
            assertEquals(WEB3DateUtility.getDate("20080331", "yyyyMMdd"), l_response.lastUpdatedTimestamp);
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
     * リクエストが以外の場合
     *
     */
    public void testExecute_0004()
    {
        final String STR_METHOD_NAME = "testExecute_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3ToSuccOptionSettleContractOrderServiceImpl l_serviceImpl =
                new WEB3ToSuccOptionSettleContractOrderServiceImpl();
            WEB3SuccOptionsCloseInputRequest l_request = new WEB3SuccOptionsCloseInputRequest();
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

    /**
     * 正常完了
     *
     */
    public void testCreateRequestAdapter()
    {
        final String STR_METHOD_NAME = "testCreateRequestAdapter()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccOptionSettleContractOrderServiceImpl l_serviceImpl =
            new WEB3ToSuccOptionSettleContractOrderServiceImpl();
        
        try
        {
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setAccountId(333812512246L);
            l_ifoOrderUnitParams.setConfirmedPrice(22.3);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            WEB3SuccOptionsCloseConfirmRequest l_request = new WEB3SuccOptionsCloseConfirmRequest();
            l_request.succCommonInfo = new WEB3SuccCommonInfo();
            l_request.succCommonInfo.parentOrderId = "1001";
            l_request.closingOrder = "0";
            
            WEB3ToSuccOptionSettleContractOrderRequestAdapter l_adapter =
                (WEB3ToSuccOptionSettleContractOrderRequestAdapter)l_serviceImpl.createRequestAdapter(l_request);
            assertEquals("0", ((WEB3SuccOptionsCloseConfirmRequest)l_adapter.request).closingOrder);
            assertEquals("22.3", "" + l_adapter.parentOrderUnit.getConfirmedPrice());
            assertEquals("0.0", "" + l_adapter.price);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionSettleContractOrderServiceImpl.createSettleContractEntry(WEB3FuturesSettleContractOrderRequestAdapter, WEB3FuturesOptionsCloseMarginContractUnit[])'
     */
    public void testCreateSettleContractEntry_0001()
    {
        final String STR_METHOD_NAME = "testCreateSettleContractEntry_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccOptionSettleContractOrderServiceImpl l_serviceImpl =
            new WEB3ToSuccOptionSettleContractOrderServiceImpl();

        try
        {
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setAccountId(333812512246L);
            l_ifoOrderUnitParams.setConfirmedPrice(22.3);
            l_ifoOrderUnitParams.setQuantity(18);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            WEB3ToSuccOptionSettleContractOrderRequestAdapter l_requestAdapter =
                new WEB3ToSuccOptionSettleContractOrderRequestAdapter();

            WEB3SuccOptionsCloseConfirmRequest l_request = new WEB3SuccOptionsCloseConfirmRequest();
            l_request.succCommonInfo = new WEB3SuccCommonInfo();
            l_request.succCommonInfo.succTradingType = "17";
            l_request.opOrderQuantity = "15";
            l_requestAdapter.request = l_request;
            WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[1];
            l_requestAdapter.parentOrderUnit =
                new IfoContractSettleOrderUnitImpl(l_ifoOrderUnitParams.getOrderUnitId());
            l_closeMarginContractUnits[0] = new WEB3FuturesOptionsCloseMarginContractUnit();
            
            SettleContractEntry[] l_settleContractEntries =
                l_serviceImpl.createSettleContractEntry(l_requestAdapter, l_closeMarginContractUnits);
            
            assertEquals("15.0", "" + l_settleContractEntries[0].getQuantity());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCreateSettleContractEntry_0002()
    {
        final String STR_METHOD_NAME = "testCreateSettleContractEntry_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccOptionSettleContractOrderServiceImpl l_serviceImpl =
            new WEB3ToSuccOptionSettleContractOrderServiceImpl();

        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "createSettleContractEntry", new Class[]
                    { long.class, double.class, WEB3FuturesOptionsCloseMarginContractUnit[].class },
                    new SettleContractEntry[]{new SettleContractEntry(0, 11)});
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setAccountId(333812512246L);
            l_ifoOrderUnitParams.setConfirmedPrice(22.3);
            l_ifoOrderUnitParams.setQuantity(18);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            WEB3ToSuccOptionSettleContractOrderRequestAdapter l_requestAdapter =
                new WEB3ToSuccOptionSettleContractOrderRequestAdapter();

            WEB3SuccOptionsCloseConfirmRequest l_request = new WEB3SuccOptionsCloseConfirmRequest();
            l_request.succCommonInfo = new WEB3SuccCommonInfo();
            l_request.succCommonInfo.succTradingType = "12";
            l_request.opOrderQuantity = "15";
            l_requestAdapter.request = l_request;
            WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[1];
            l_requestAdapter.parentOrderUnit =
                new IfoContractSettleOrderUnitImpl(l_ifoOrderUnitParams.getOrderUnitId());
            l_closeMarginContractUnits[0] = new WEB3FuturesOptionsCloseMarginContractUnit();
            
            SettleContractEntry[] l_settleContractEntries =
                l_serviceImpl.createSettleContractEntry(l_requestAdapter, l_closeMarginContractUnits);
            
            assertEquals("11.0", "" + l_settleContractEntries[0].getQuantity());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionSettleContractOrderServiceImpl.validateFuturesSettleContractOrder(SubAccount, IfoSettleContractOrderSpec, WEB3FuturesSettleContractOrderRequestAdapter)'
     */
    public void testValidateOptionsSettleContractOrder_0001()
    {
        final String STR_METHOD_NAME = "testValidateOptionsSettleContractOrder_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccOptionSettleContractOrderServiceImpl l_serviceImpl =
            new WEB3ToSuccOptionSettleContractOrderServiceImpl();
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.INDEX_FUTURES_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("2");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setInstitutionCode("0D");
            l_tradingTimeParams1.setBranchCode("123");
            l_tradingTimeParams1.setMarketCode("N1");
            l_tradingTimeParams1.setTradingTimeType("01");
            l_tradingTimeParams1.setProductCode("0");
            l_tradingTimeParams1.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            
            TradingTimeParams l_tradingTimeParams2 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams2.setInstitutionCode("0D");
            l_tradingTimeParams2.setBranchCode("123");
            l_tradingTimeParams2.setMarketCode("N1");
            l_tradingTimeParams2.setTradingTimeType("01");
            l_tradingTimeParams2.setProductCode("0");
            l_tradingTimeParams2.setBizDateType("3");
            TestDBUtility.insertWithDel(l_tradingTimeParams2);
            
            TradingTimeParams l_tradingTimeParams3 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams3.setInstitutionCode("0D");
            l_tradingTimeParams3.setBranchCode("123");
            l_tradingTimeParams3.setMarketCode("N1");
            l_tradingTimeParams3.setTradingTimeType("01");
            l_tradingTimeParams3.setProductCode("0");
            l_tradingTimeParams3.setBizDateType("4");
            TestDBUtility.insertWithDel(l_tradingTimeParams3);
            
            
            SubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);

            IfoSettleContractOrderSpec l_changeSettleContractOrderSpec =
                WEB3IfoSettleContractOrderSpec.createSettleContractOrderSpec(
                    "0D", null, 0, null, null, null, null, 0, 0, null, null, null, true);
            
            WEB3ToSuccOptionSettleContractOrderRequestAdapter l_requestAdapter =
                new WEB3ToSuccOptionSettleContractOrderRequestAdapterForTest();
            
            WEB3SuccOptionsCloseConfirmRequest l_request = new WEB3SuccOptionsCloseConfirmRequest();
            l_request.succCommonInfo = new WEB3SuccCommonInfo();
            l_request.succCommonInfo.succTradingType = "17";
            l_requestAdapter.request = l_request;
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "validateSettleContractOrder", new Class[]
                    {WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class, WEB3IfoContractImpl.class},
                    new NewOrderValidationResult(ProcessingResult.newSuccessResultInstance()));
            
            NewOrderValidationResult l_result = l_serviceImpl.validateOptionsSettleContractOrder(
                l_subAccount, (WEB3IfoSettleContractOrderSpec)l_changeSettleContractOrderSpec, (WEB3OptionSettleContractOrderRequestAdapter)l_requestAdapter);
            
            assertEquals(ProcessingResult.SUCCESS_RESULT, l_result.getProcessingResult());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidateOptionsSettleContractOrder_0002()
    {
        final String STR_METHOD_NAME = "testValidateOptionsSettleContractOrder_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccOptionSettleContractOrderServiceImpl l_serviceImpl =
            new WEB3ToSuccOptionSettleContractOrderServiceImpl();
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.INDEX_FUTURES_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("2");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setInstitutionCode("0D");
            l_tradingTimeParams1.setBranchCode("123");
            l_tradingTimeParams1.setMarketCode("N1");
            l_tradingTimeParams1.setTradingTimeType("01");
            l_tradingTimeParams1.setProductCode("0");
            l_tradingTimeParams1.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            
            TradingTimeParams l_tradingTimeParams2 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams2.setInstitutionCode("0D");
            l_tradingTimeParams2.setBranchCode("123");
            l_tradingTimeParams2.setMarketCode("N1");
            l_tradingTimeParams2.setTradingTimeType("01");
            l_tradingTimeParams2.setProductCode("0");
            l_tradingTimeParams2.setBizDateType("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams2);
            
            TradingTimeParams l_tradingTimeParams3 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams3.setInstitutionCode("0D");
            l_tradingTimeParams3.setBranchCode("123");
            l_tradingTimeParams3.setMarketCode("N1");
            l_tradingTimeParams3.setTradingTimeType("01");
            l_tradingTimeParams3.setProductCode("0");
            l_tradingTimeParams3.setBizDateType("3");
            TestDBUtility.insertWithDel(l_tradingTimeParams3);
            
            TradingTimeParams l_tradingTimeParams4 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams4.setInstitutionCode("0D");
            l_tradingTimeParams4.setBranchCode("123");
            l_tradingTimeParams4.setMarketCode("N1");
            l_tradingTimeParams4.setTradingTimeType("01");
            l_tradingTimeParams4.setProductCode("0");
            l_tradingTimeParams4.setBizDateType("4");
            TestDBUtility.insertWithDel(l_tradingTimeParams4);
            
            SubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);

            IfoSettleContractOrderSpec l_changeSettleContractOrderSpec =
                WEB3IfoSettleContractOrderSpec.createSettleContractOrderSpec(
                    "0D", null, 0, null, null, null, null, 0, 0, null, null, null, true);
            
            WEB3ToSuccOptionSettleContractOrderRequestAdapter l_requestAdapter =
                new WEB3ToSuccOptionSettleContractOrderRequestAdapterForTest();
            
            WEB3SuccOptionsCloseConfirmRequest l_request = new WEB3SuccOptionsCloseConfirmRequest();
            l_request.succCommonInfo = new WEB3SuccCommonInfo();
            l_request.succCommonInfo.succTradingType = "12";
            l_requestAdapter.request = l_request;
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "validateSettleContractOrder", new Class[]
                    {WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class},
                    new NewOrderValidationResult(ProcessingResult.newSuccessResultInstance()));
            
            NewOrderValidationResult l_result = l_serviceImpl.validateOptionsSettleContractOrder(
                l_subAccount, (WEB3IfoSettleContractOrderSpec)l_changeSettleContractOrderSpec, (WEB3OptionSettleContractOrderRequestAdapter)l_requestAdapter);
            
            assertEquals(ProcessingResult.SUCCESS_RESULT, l_result.getProcessingResult());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionSettleContractOrderServiceImpl.submitSettleContractOrder(WEB3FuturesSettleContractOrderRequestAdapter, SubAccount, IfoSettleContractOrderSpec, long, WEB3GentradeCommission, WEB3IfoEstimateDeliveryAmountCalcResult)'
     */
    public void testSubmitSettleContractOrder_0001()
    {
        final String STR_METHOD_NAME = "testSubmitSettleContractOrder_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccOptionSettleContractOrderServiceImpl l_serviceImpl =
            new WEB3ToSuccOptionSettleContractOrderServiceImpl();
        try
        {
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setAccountId(333812512246L);
            l_ifoOrderUnitParams.setConfirmedPrice(22.3);
            l_ifoOrderUnitParams.setQuantity(18);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            WEB3ToSuccOptionSettleContractOrderRequestAdapterForTest l_requestAdapter =
                new WEB3ToSuccOptionSettleContractOrderRequestAdapterForTest();
            
            WEB3SuccOptionsCloseCompleteRequest l_request =
                new WEB3SuccOptionsCloseCompleteRequest();
            l_request.succCommonInfo = new WEB3SuccCommonInfo();
            l_request.succCommonInfo.succTradingType = "17";
            l_request.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            l_request.priceAdjustmentValueInfo.priceAdjustmentValue = "10";
            l_request.priceAdjustmentValueInfo.sign = "+";
            l_request.closingOrder = "0";
            l_request.password = "1234";
            
            l_requestAdapter.request = l_request;

            l_requestAdapter.parentOrderUnit = new IfoContractSettleOrderUnitImpl(l_ifoOrderUnitParams.getOrderUnitId());
            l_requestAdapter.getContract();
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            SubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);

            IfoSettleContractOrderSpec l_changeSettleContractOrderSpec =
                WEB3IfoSettleContractOrderSpec.createSettleContractOrderSpec(
                    "0D", null, 0, null, null, null, null, 0, 0, null, null, null, true);
            
            long l_lngOrderID = 0L;
            WEB3GentradeCommission l_genCommission = new WEB3GentradeCommission();
            WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmountCalcResult =
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            
            l_estimateDeliveryAmountCalcResult.setCommission(20);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "submitIfoCloseContractNewOrder",
                    new Class[]
                    {SubAccount.class, WEB3IfoSettleContractOrderSpec.class, long.class, String.class, String.class,
                            Double.class, IfoOrderUnit.class, WEB3IfoEstimateDeliveryAmountCalcResult.class,
                            WEB3IfoContractImpl.class, String.class},
                            null);
            
            l_serviceImpl.submitSettleContractOrder(
                l_requestAdapter,
                l_subAccount,
                (WEB3IfoSettleContractOrderSpec)l_changeSettleContractOrderSpec,
                l_lngOrderID,
                l_genCommission,
                l_estimateDeliveryAmountCalcResult);
            
            // 參數驗證
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "submitIfoCloseContractNewOrder",
                    new Class[]{SubAccount.class, WEB3IfoSettleContractOrderSpec.class, long.class, String.class, String.class,
                            Double.class, IfoOrderUnit.class, WEB3IfoEstimateDeliveryAmountCalcResult.class,
                            WEB3IfoContractImpl.class, String.class});
            
            Object[] l_paramsValues = l_paramsValue.getFirstCalled();
            assertEquals("333812512246", "" + ((WEB3GentradeSubAccount)l_paramsValues[0]).getAccountId());
            assertEquals("0D", "" + ((WEB3IfoSettleContractOrderSpec)l_paramsValues[1]).getInstitutionCode());
            assertEquals("0", "" + ((Long)l_paramsValues[2]).longValue());
            assertEquals("1234", (String)l_paramsValues[3]);
            assertEquals("17", (String)l_paramsValues[4]);
            assertEquals("10.0", "" + ((Double)l_paramsValues[5]).doubleValue());
            assertEquals("333812512246", "" + ((IfoOrderUnit)l_paramsValues[6]).getAccountId());
            assertEquals("20.0", "" + ((WEB3IfoEstimateDeliveryAmountCalcResult)l_paramsValues[7]).getCommission());
            assertEquals("1001", "" + ((WEB3IfoContractImpl)l_paramsValues[8]).getContractId());
            assertEquals("0", (String)l_paramsValues[9]);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    

    /*
     * Test method for 'webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionSettleContractOrderServiceImpl.setPrice(WEB3FuturesSettleContractOrderRequestAdapter, WEB3GenResponse)'
     */
    public void testSetPrice_0001()
    {
        final String STR_METHOD_NAME = "testSetPrice_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccOptionSettleContractOrderServiceImpl l_serviceImpl =
            new WEB3ToSuccOptionSettleContractOrderServiceImpl();
        
        WEB3ToSuccOptionSettleContractOrderRequestAdapter l_adapter =
            new WEB3ToSuccOptionSettleContractOrderRequestAdapter();
        
        l_adapter.price = 10;
        
        WEB3SuccOptionsCloseConfirmRequest l_request = new WEB3SuccOptionsCloseConfirmRequest();
        l_request.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
        l_request.priceAdjustmentValueInfo.priceAdjustmentValue = "12";
        l_request.priceAdjustmentValueInfo.sign = "+";
        
        l_adapter.request = l_request;
        
        WEB3SuccOptionsCloseConfirmResponse l_response = new WEB3SuccOptionsCloseConfirmResponse();
        l_response.afterAdjustmentPrice = "20";
        try
        {
            l_serviceImpl.setPrice(l_adapter, l_response);
            assertEquals("10", l_response.afterAdjustmentPrice);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSetPrice_0002()
    {
        final String STR_METHOD_NAME = "testSetPrice_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccOptionSettleContractOrderServiceImpl l_serviceImpl =
            new WEB3ToSuccOptionSettleContractOrderServiceImpl();
        
        WEB3ToSuccOptionSettleContractOrderRequestAdapter l_adapter =
            new WEB3ToSuccOptionSettleContractOrderRequestAdapter();
        
        WEB3SuccOptionsCloseConfirmRequest l_request = new WEB3SuccOptionsCloseConfirmRequest();
        l_adapter.request = l_request;
        
        WEB3SuccOptionsCloseConfirmResponse l_response = new WEB3SuccOptionsCloseConfirmResponse();
        l_response.afterAdjustmentPrice = "20";
        try
        {
            l_serviceImpl.setPrice(l_adapter, l_response);
            assertEquals("20", l_response.afterAdjustmentPrice);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionSettleContractOrderServiceImpl.validateOrder(WEB3SuccOptionsCloseConfirmRequest)'
     */
    public void testValidateOrder_0001()
    {
        final String STR_METHOD_NAME = "testValidateOrder_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccOptionSettleContractOrderServiceImpl l_serviceImpl =
            new WEB3ToSuccOptionSettleContractOrderServiceImpl();
        
        try
        {
            WEB3SuccOptionsCloseConfirmRequest l_request = new WEB3SuccOptionsCloseConfirmRequest();
            
            l_serviceImpl.validateOrder(l_request);
            fail();
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
    
    public void testValidateOrder_0002()
    {
        final String STR_METHOD_NAME = "testValidateOrder_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccOptionSettleContractOrderServiceImpl l_serviceImpl =
            new WEB3ToSuccOptionSettleContractOrderServiceImpl();
        
        try
        {
            WEB3SuccOptionsCloseConfirmRequest l_request = new WEB3SuccOptionsCloseConfirmRequest();
            l_request.succCommonInfo = new WEB3SuccCommonInfo();
            l_request.succCommonInfo.parentOrderId = "1001";
            l_request.succCommonInfo.succTradingType = "17";
            l_request.orderPriceDiv = "0";
            l_request.limitPrice = null;
            l_request.execCondType = "1";
            l_request.expirationDateType = "1";
            l_request.expirationDate = null;
            l_request.orderCondType = "0";
            l_request.stopOrderCondPrice = null;
            l_request.stopOrderCondOperator = null;
            l_request.wlimitOrderCondPrice = null;
            l_request.wlimitOrderCondOperator = null;
            l_request.wLimitOrderPriceDiv = null;
            l_request.wLimitPrice = null;
            l_request.wlimitExecCondType = null;
            l_request.wlimitEnableStatusDiv = null;
            l_request.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            l_request.closingOrder ="1";
            l_request.opOrderQuantity = "1000";
            l_request.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            l_request.priceAdjustmentValueInfo.sign = "+";
            l_request.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("3");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(3304148080000L);
            l_ifoProductParams.setUnderlyingProductCode("000005");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(EnableOrderConditionRow.TYPE);
            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionRow();
            l_enableOrderConditionParams.setInstitutionCode("0D");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.IFO);
            l_enableOrderConditionParams.setFutureOptionDiv("2");
            l_enableOrderConditionParams.setMarginTradingDiv("0");
            l_enableOrderConditionParams.setMarketCode("0");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);
            
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setAccountId(333812512246L);
            l_ifoOrderUnitParams.setConfirmedPrice(22.3);
            l_ifoOrderUnitParams.setQuantity(18);
            l_ifoOrderUnitParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            l_serviceImpl.validateOrder(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidateOrder_0003()
    {
        final String STR_METHOD_NAME = "testValidateOrder_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccOptionSettleContractOrderServiceImpl l_serviceImpl =
            new WEB3ToSuccOptionSettleContractOrderServiceImpl();
        
        try
        {
            WEB3SuccOptionsCloseConfirmRequest l_request = new WEB3SuccOptionsCloseConfirmRequest();
            l_request.succCommonInfo = new WEB3SuccCommonInfo();
            l_request.succCommonInfo.parentOrderId = "1001";
            l_request.succCommonInfo.succTradingType = "17";
            l_request.orderPriceDiv = "0";
            l_request.limitPrice = null;
            l_request.execCondType = "1";
            l_request.expirationDateType = "1";
            l_request.expirationDate = null;
            l_request.orderCondType = "0";
            l_request.stopOrderCondPrice = null;
            l_request.stopOrderCondOperator = null;
            l_request.wlimitOrderCondPrice = null;
            l_request.wlimitOrderCondOperator = null;
            l_request.wLimitOrderPriceDiv = null;
            l_request.wLimitPrice = null;
            l_request.wlimitExecCondType = null;
            l_request.wlimitEnableStatusDiv = null;
            l_request.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            l_request.closingOrder ="1";
            l_request.opOrderQuantity = "1000";
            l_request.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            l_request.priceAdjustmentValueInfo.sign = "+";
            l_request.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("3");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(3304148080000L);
            l_ifoProductParams.setUnderlyingProductCode("000005");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateSuccOrder",
                    new Class[]
                    {WEB3GentradeSubAccount.class, ProductTypeEnum.class, String.class, String.class, OrderUnit.class},
                    null);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setAccountId(333812512246L);
            l_ifoOrderUnitParams.setConfirmedPrice(22.3);
            l_ifoOrderUnitParams.setQuantity(18);
            l_ifoOrderUnitParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            l_serviceImpl.validateOrder(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidateOrder_0004()
    {
        final String STR_METHOD_NAME = "testValidateOrder_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccOptionSettleContractOrderServiceImpl l_serviceImpl =
            new WEB3ToSuccOptionSettleContractOrderServiceImplForTest1();
        
        try
        {
            WEB3SuccOptionsCloseConfirmRequest l_request = new WEB3SuccOptionsCloseConfirmRequest();
            l_request.succCommonInfo = new WEB3SuccCommonInfo();
            l_request.succCommonInfo.parentOrderId = "1001";
            l_request.succCommonInfo.succTradingType = "17";
            l_request.orderPriceDiv = "0";
            l_request.limitPrice = null;
            l_request.execCondType = "1";
            l_request.expirationDateType = "1";
            l_request.expirationDate = null;
            l_request.orderCondType = "0";
            l_request.stopOrderCondPrice = null;
            l_request.stopOrderCondOperator = null;
            l_request.wlimitOrderCondPrice = null;
            l_request.wlimitOrderCondOperator = null;
            l_request.wLimitOrderPriceDiv = null;
            l_request.wLimitPrice = null;
            l_request.wlimitExecCondType = null;
            l_request.wlimitEnableStatusDiv = null;
            l_request.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit()};
            l_request.closeMarginContractUnits[0].id = "1001";
            l_request.closingOrder ="1";
            l_request.opOrderQuantity = "12";
            l_request.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            l_request.priceAdjustmentValueInfo.sign = "+";
            l_request.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("3");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(3304148080000L);
            l_ifoProductParams.setTargetMarketId(1002);
            l_ifoProductParams.setUnderlyingProductCode("000005");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateSuccOrder",
                    new Class[]
                    {WEB3GentradeSubAccount.class, ProductTypeEnum.class, String.class, String.class, OrderUnit.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateSuccOrderMaxQuantity",
                    new Class[]{OrderUnit.class}, null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {}, new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getSessionProperty",
                    new Class[] {String.class},
                    "1");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "getDayTradeType",
                    new Class[]{ SettleContractEntry[].class },
                    "5");
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "createIfoContract",
                    new Class[]{IfoOrderUnit.class},
                    new WEB3IfoContractImpl(l_ifoContractParams.getContractId()));
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParam = TestDBUtility.getTraderRow();
            l_traderParam.setTraderId(3338111123L);
            l_traderParam.setLoginId(256L);
            TestDBUtility.insertWithDel(l_traderParam);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(3338111123L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "validateSettleContractOrder", new Class[]
                    {WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class, WEB3IfoContractImpl.class},
                    new NewOrderValidationResult(ProcessingResult.newSuccessResultInstance()));
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_calcResult =
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            l_calcResult.setCommission(20);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "calcEstimateDeliveryAmount", new Class[]
                    { WEB3GentradeCommission.class, double.class, SubAccount.class, WEB3IfoTradedProductImpl.class,
                            double.class, SideEnum.class, boolean.class, boolean.class },
                     l_calcResult);
            
            
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "xblocks.gtl.attributes.systemtimestamp",
                    new Timestamp((WEB3DateUtility.getDate("20080422", "yyyyMMdd")).getTime()));
                
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "web3.attributes.basetimestampfororderbizdate", null);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setAccountId(333812512246L);
            l_ifoOrderUnitParams.setConfirmedPrice(22.3);
            l_ifoOrderUnitParams.setQuantity(18);
            l_ifoOrderUnitParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setValidForBizDate(null);
            l_ifoTradedProductParams.setTradedProductId(1006160060005L);
            l_ifoTradedProductParams.setProductId(3304148080000L);
            l_ifoTradedProductParams.setLastTradingDate(WEB3DateUtility.getDate("20080529", "yyyyMMdd"));
            l_ifoTradedProductParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080000L);
            l_tradedProductParams.setMarketId(1002);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("000005");
            l_tradingTimeParams.setBizDateType("2");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setInstitutionCode("0D");
            l_tradingTimeParams1.setBranchCode("123");
            l_tradingTimeParams1.setMarketCode("N1");
            l_tradingTimeParams1.setTradingTimeType("01");
            l_tradingTimeParams1.setProductCode("000005");
            l_tradingTimeParams1.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            
            TradingTimeParams l_tradingTimeParams2 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams2.setInstitutionCode("0D");
            l_tradingTimeParams2.setBranchCode("123");
            l_tradingTimeParams2.setMarketCode("N1");
            l_tradingTimeParams2.setTradingTimeType("01");
            l_tradingTimeParams2.setProductCode("000005");
            l_tradingTimeParams2.setBizDateType("3");
            TestDBUtility.insertWithDel(l_tradingTimeParams2);
            
            TradingTimeParams l_tradingTimeParams3 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams3.setInstitutionCode("0D");
            l_tradingTimeParams3.setBranchCode("123");
            l_tradingTimeParams3.setMarketCode("N1");
            l_tradingTimeParams3.setTradingTimeType("01");
            l_tradingTimeParams3.setProductCode("000005");
            l_tradingTimeParams3.setBizDateType("4");
            TestDBUtility.insertWithDel(l_tradingTimeParams3);
            
            TradingTimeParams l_tradingTimeParams4 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams4.setInstitutionCode("0D");
            l_tradingTimeParams4.setBranchCode("123");
            l_tradingTimeParams4.setMarketCode("N1");
            l_tradingTimeParams4.setTradingTimeType("01");
            l_tradingTimeParams4.setProductCode("000005");
            l_tradingTimeParams4.setBizDateType("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams4);
            
            WEB3SuccOptionsCloseConfirmResponse l_response =
                l_serviceImpl.validateOrder(l_request);
            
            assertEquals("20", l_response.commission);
            
            WEB3GentradeTradingClendarContext l_clendarContext =
                (WEB3GentradeTradingClendarContext)
                    ThreadLocalSystemAttributesRegistry.getAttribute("web3.tradingcalendarcontext");
            
            assertEquals("000005", l_clendarContext.getProductCode());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidateOrder_0005()
    {
        final String STR_METHOD_NAME = "testValidateOrder_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccOptionSettleContractOrderServiceImpl l_serviceImpl =
            new WEB3ToSuccOptionSettleContractOrderServiceImplForTest1();
        
        try
        {
            WEB3SuccOptionsCloseConfirmRequest l_request = new WEB3SuccOptionsCloseConfirmRequest();
            l_request.succCommonInfo = new WEB3SuccCommonInfo();
            l_request.succCommonInfo.parentOrderId = "1001";
            l_request.succCommonInfo.succTradingType = "18";
            l_request.orderPriceDiv = "0";
            l_request.limitPrice = null;
            l_request.execCondType = "1";
            l_request.expirationDateType = "1";
            l_request.expirationDate = null;
            l_request.orderCondType = "0";
            l_request.stopOrderCondPrice = null;
            l_request.stopOrderCondOperator = null;
            l_request.wlimitOrderCondPrice = null;
            l_request.wlimitOrderCondOperator = null;
            l_request.wLimitOrderPriceDiv = null;
            l_request.wLimitPrice = null;
            l_request.wlimitExecCondType = null;
            l_request.wlimitEnableStatusDiv = null;
            l_request.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit()};
            l_request.closeMarginContractUnits[0].id = "1001";
            l_request.closingOrder ="1";
            l_request.opOrderQuantity = "12";
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("3");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(3304148080000L);
            l_ifoProductParams.setTargetMarketId(1002);
            l_ifoProductParams.setUnderlyingProductCode("000005");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateSuccOrder",
                    new Class[]
                    {WEB3GentradeSubAccount.class, ProductTypeEnum.class, String.class, String.class, OrderUnit.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateSuccOrderMaxQuantity",
                    new Class[]{OrderUnit.class}, null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {}, new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getSessionProperty",
                    new Class[] {String.class},
                    "1");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "getDayTradeType",
                    new Class[]{ SettleContractEntry[].class },
                    "5");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "createSettleContractEntry", new Class[]
                    { long.class, double.class, WEB3FuturesOptionsCloseMarginContractUnit[].class },
                    new SettleContractEntry[]{new SettleContractEntry(1001, 11)});
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "createIfoContract",
                    new Class[]{IfoOrderUnit.class},
                    new WEB3IfoContractImpl(l_ifoContractParams.getContractId()));
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParam = TestDBUtility.getTraderRow();
            l_traderParam.setTraderId(3338111123L);
            l_traderParam.setLoginId(256L);
            TestDBUtility.insertWithDel(l_traderParam);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(3338111123L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "validateSettleContractOrder", new Class[]
                    {WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class, WEB3IfoContractImpl.class},
                    new NewOrderValidationResult(ProcessingResult.newSuccessResultInstance()));
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "xblocks.gtl.attributes.systemtimestamp",
                    new Timestamp((WEB3DateUtility.getDate("20080422", "yyyyMMdd")).getTime()));
                
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "web3.attributes.basetimestampfororderbizdate", null);
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_calcResult =
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            l_calcResult.setCommission(20);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "calcEstimateDeliveryAmount", new Class[]
                    { WEB3GentradeCommission.class, double.class, SubAccount.class, WEB3IfoTradedProductImpl.class,
                            double.class, SideEnum.class, boolean.class, boolean.class },
                     l_calcResult);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setAccountId(333812512246L);
            l_ifoOrderUnitParams.setConfirmedPrice(22.3);
            l_ifoOrderUnitParams.setQuantity(18);
            l_ifoOrderUnitParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setValidForBizDate(null);
            l_ifoTradedProductParams.setTradedProductId(1006160060005L);
            l_ifoTradedProductParams.setProductId(3304148080000L);
            l_ifoTradedProductParams.setLastTradingDate(WEB3DateUtility.getDate("20080529", "yyyyMMdd"));
            l_ifoTradedProductParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080000L);
            l_tradedProductParams.setMarketId(1002);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("000005");
            l_tradingTimeParams.setBizDateType("2");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setInstitutionCode("0D");
            l_tradingTimeParams1.setBranchCode("123");
            l_tradingTimeParams1.setMarketCode("N1");
            l_tradingTimeParams1.setTradingTimeType("01");
            l_tradingTimeParams1.setProductCode("000005");
            l_tradingTimeParams1.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            
            TradingTimeParams l_tradingTimeParams2 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams2.setInstitutionCode("0D");
            l_tradingTimeParams2.setBranchCode("123");
            l_tradingTimeParams2.setMarketCode("N1");
            l_tradingTimeParams2.setTradingTimeType("01");
            l_tradingTimeParams2.setProductCode("000005");
            l_tradingTimeParams2.setBizDateType("3");
            TestDBUtility.insertWithDel(l_tradingTimeParams2);
            
            TradingTimeParams l_tradingTimeParams3 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams3.setInstitutionCode("0D");
            l_tradingTimeParams3.setBranchCode("123");
            l_tradingTimeParams3.setMarketCode("N1");
            l_tradingTimeParams3.setTradingTimeType("01");
            l_tradingTimeParams3.setProductCode("000005");
            l_tradingTimeParams3.setBizDateType("4");
            TestDBUtility.insertWithDel(l_tradingTimeParams3);
            
            TradingTimeParams l_tradingTimeParams4 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams4.setInstitutionCode("0D");
            l_tradingTimeParams4.setBranchCode("123");
            l_tradingTimeParams4.setMarketCode("N1");
            l_tradingTimeParams4.setTradingTimeType("01");
            l_tradingTimeParams4.setProductCode("000005");
            l_tradingTimeParams4.setBizDateType("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams4);
            
            WEB3SuccOptionsCloseConfirmResponse l_response =
                l_serviceImpl.validateOrder(l_request);
            
            assertEquals("20", l_response.commission);
            
            WEB3GentradeTradingClendarContext l_clendarContext =
                (WEB3GentradeTradingClendarContext)
                    ThreadLocalSystemAttributesRegistry.getAttribute("web3.tradingcalendarcontext");
            
            assertEquals("000005", l_clendarContext.getProductCode());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }


    /*
     * Test method for 'webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionSettleContractOrderServiceImpl.submitOrder(WEB3SuccOptionsCloseCompleteRequest)'
     */
    public void testSubmitOrder_0001()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccOptionSettleContractOrderServiceImpl l_serviceImpl =
            new WEB3ToSuccOptionSettleContractOrderServiceImpl();
        
        try
        {
            WEB3SuccOptionsCloseCompleteRequest l_request = new WEB3SuccOptionsCloseCompleteRequest();
            l_serviceImpl.submitOrder(l_request);
            fail();
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
    
    public void testSubmitOrder_0002()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccOptionSettleContractOrderServiceImpl l_serviceImpl =
            new WEB3ToSuccOptionSettleContractOrderServiceImpl();
        
        try
        {
            WEB3SuccOptionsCloseCompleteRequest l_request = new WEB3SuccOptionsCloseCompleteRequest();
            l_request.succCommonInfo = new WEB3SuccCommonInfo();
            l_request.succCommonInfo.parentOrderId = "1001";
            l_request.succCommonInfo.succTradingType = "17";
            l_request.orderPriceDiv = "0";
            l_request.limitPrice = null;
            l_request.execCondType = "1";
            l_request.expirationDateType = "1";
            l_request.expirationDate = null;
            l_request.orderCondType = "0";
            l_request.stopOrderCondPrice = null;
            l_request.stopOrderCondOperator = null;
            l_request.wlimitOrderCondPrice = null;
            l_request.wlimitOrderCondOperator = null;
            l_request.wLimitOrderPriceDiv = null;
            l_request.wLimitPrice = null;
            l_request.wlimitExecCondType = null;
            l_request.wlimitEnableStatusDiv = null;
            l_request.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            l_request.closingOrder ="1";
            l_request.opOrderQuantity = "1000";
            l_request.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            l_request.priceAdjustmentValueInfo.sign = "+";
            l_request.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            l_request.orderId = "1001";
            l_request.checkPrice = "20";
            l_request.checkDate = WEB3DateUtility.getDate("20080402", "yyyyMMdd");
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("3");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(3304148080000L);
            l_ifoProductParams.setUnderlyingProductCode("000005");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setAccountId(333812512246L);
            l_ifoOrderUnitParams.setConfirmedPrice(22.3);
            l_ifoOrderUnitParams.setQuantity(18);
            l_ifoOrderUnitParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            l_serviceImpl.submitOrder(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02245, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSubmitOrder_0003()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccOptionSettleContractOrderServiceImpl l_serviceImpl =
            new WEB3ToSuccOptionSettleContractOrderServiceImpl();
        
        try
        {
            WEB3SuccOptionsCloseCompleteRequest l_request = new WEB3SuccOptionsCloseCompleteRequest();
            l_request.succCommonInfo = new WEB3SuccCommonInfo();
            l_request.succCommonInfo.parentOrderId = "1001";
            l_request.succCommonInfo.succTradingType = "17";
            l_request.orderPriceDiv = "0";
            l_request.limitPrice = null;
            l_request.execCondType = "1";
            l_request.expirationDateType = "1";
            l_request.expirationDate = null;
            l_request.orderCondType = "0";
            l_request.stopOrderCondPrice = null;
            l_request.stopOrderCondOperator = null;
            l_request.wlimitOrderCondPrice = null;
            l_request.wlimitOrderCondOperator = null;
            l_request.wLimitOrderPriceDiv = null;
            l_request.wLimitPrice = null;
            l_request.wlimitExecCondType = null;
            l_request.wlimitEnableStatusDiv = null;
            l_request.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            l_request.closingOrder ="1";
            l_request.opOrderQuantity = "1000";
            l_request.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            l_request.priceAdjustmentValueInfo.sign = "+";
            l_request.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            l_request.orderId = "1001";
            l_request.checkPrice = "20";
            l_request.checkDate = WEB3DateUtility.getDate("20080402", "yyyyMMdd");
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("3");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(3304148080000L);
            l_ifoProductParams.setUnderlyingProductCode("000005");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateSuccOrder",
                    new Class[]
                    {WEB3GentradeSubAccount.class, ProductTypeEnum.class, String.class, String.class, OrderUnit.class},
                    null);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setAccountId(333812512246L);
            l_ifoOrderUnitParams.setConfirmedPrice(22.3);
            l_ifoOrderUnitParams.setQuantity(18);
            l_ifoOrderUnitParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            l_serviceImpl.submitOrder(l_request);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSubmitOrder_0004()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccOptionSettleContractOrderServiceImpl l_serviceImpl =
            new WEB3ToSuccOptionSettleContractOrderServiceImplForTest1();
        
        try
        {
            WEB3SuccOptionsCloseCompleteRequest l_request = new WEB3SuccOptionsCloseCompleteRequest();
            l_request.succCommonInfo = new WEB3SuccCommonInfo();
            l_request.succCommonInfo.parentOrderId = "1001";
            l_request.succCommonInfo.succTradingType = "17";
            l_request.orderPriceDiv = "0";
            l_request.execCondType = "1";
            l_request.expirationDateType = "1";
            l_request.expirationDate = null;
            l_request.orderCondType = "0";
            l_request.stopOrderCondPrice = null;
            l_request.stopOrderCondOperator = null;
            l_request.wlimitOrderCondPrice = null;
            l_request.wlimitOrderCondOperator = null;
            l_request.wLimitOrderPriceDiv = null;
            l_request.wLimitPrice = null;
            l_request.wlimitExecCondType = null;
            l_request.wlimitEnableStatusDiv = null;
            l_request.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit(),
                    new WEB3FuturesOptionsCloseMarginContractUnit()};
            l_request.closingOrder ="1";
            l_request.opOrderQuantity = "12";
            l_request.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            l_request.priceAdjustmentValueInfo.sign = "+";
            l_request.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            l_request.orderId = "1001";
            l_request.checkPrice = "20";
            l_request.checkDate = WEB3DateUtility.getDate("20080422", "yyyyMMdd");
            l_request.orderPriceDiv = "0";
            l_request.limitPrice = null;
            l_request.afterAdjustmentPrice = "15";
            
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = new RsvIfoOrderUnitParams();
            l_rsvIfoOrderUnitParams.setAccountId(333812512246L);
            l_rsvIfoOrderUnitParams.setMarketId(3303L);
            l_rsvIfoOrderUnitParams.setSubAccountId(123);
            l_rsvIfoOrderUnitParams.setBranchId(1234);
            l_rsvIfoOrderUnitParams.setOrderId(1001);
            l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.UNDEFINED);
            l_rsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.UNDEFINED);
            l_rsvIfoOrderUnitParams.setLastOrderActionSerialNo(0);
            l_rsvIfoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_rsvIfoOrderUnitParams.setQuantity(23);
            l_rsvIfoOrderUnitParams.setOrderStatus(OrderStatusEnum.UNDEFINED);
            l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.UNDEFINED);
            l_rsvIfoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.UNDEFINED);
            l_rsvIfoOrderUnitParams.setTaxType(TaxTypeEnum.UNDEFINED);
            l_rsvIfoOrderUnitParams.setBizDate("20070117");
            l_rsvIfoOrderUnitParams.setProductId(3304148080000L);
            l_rsvIfoOrderUnitParams.setParentOrderId(56);
            l_rsvIfoOrderUnitParams.setSerialNoInParent(68);
            l_rsvIfoOrderUnitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20080328", "yyyyMMdd"));
            l_rsvIfoOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20080328", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("3");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(3304148080000L);
            l_ifoProductParams.setTargetMarketId(1002);
            l_ifoProductParams.setUnderlyingProductCode("000005");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateSuccOrder",
                    new Class[]
                    {WEB3GentradeSubAccount.class, ProductTypeEnum.class, String.class, String.class, OrderUnit.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateSuccOrderMaxQuantity",
                    new Class[]{OrderUnit.class}, null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {}, new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getSessionProperty",
                    new Class[] {String.class},
                    "1");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "getDayTradeType",
                    new Class[]{ SettleContractEntry[].class },
                    "5");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl", "notifyRLS",
                    new Class[]
                    {IfoOrderUnit.class, OrderManagerPersistenceContext.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "submitIfoCloseContractNewOrder",
                    new Class[]
                    {SubAccount.class, WEB3IfoSettleContractOrderSpec.class, long.class, String.class, String.class,
                            Double.class, IfoOrderUnit.class, WEB3IfoEstimateDeliveryAmountCalcResult.class,
                            WEB3IfoContractImpl.class, String.class}, null);
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_calcResult =
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            l_calcResult.setCommission(20);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "calcEstimateDeliveryAmount", new Class[]
                    { WEB3GentradeCommission.class, double.class, SubAccount.class, WEB3IfoTradedProductImpl.class,
                            double.class, SideEnum.class, boolean.class, boolean.class },
                     l_calcResult);

            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "xblocks.gtl.attributes.systemtimestamp",
                    new Timestamp((WEB3DateUtility.getDate("20080422", "yyyyMMdd")).getTime()));
                
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "web3.attributes.basetimestampfororderbizdate", null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "validateSettleContractOrder", new Class[]
                    {WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class, WEB3IfoContractImpl.class},
                    new NewOrderValidationResult(ProcessingResult.newSuccessResultInstance()));
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "createIfoContract",
                    new Class[]{IfoOrderUnit.class},
                    new WEB3IfoContractImpl(l_ifoContractParams.getContractId()));
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParam = TestDBUtility.getTraderRow();
            l_traderParam.setTraderId(3338111123L);
            l_traderParam.setLoginId(256L);
            TestDBUtility.insertWithDel(l_traderParam);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(3338111123L));
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setAccountId(333812512246L);
            l_ifoOrderUnitParams.setConfirmedPrice(22.3);
            l_ifoOrderUnitParams.setQuantity(18);
            l_ifoOrderUnitParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setValidForBizDate(null);
            l_ifoTradedProductParams.setTradedProductId(1006160060005L);
            l_ifoTradedProductParams.setProductId(3304148080000L);
            l_ifoTradedProductParams.setLastTradingDate(WEB3DateUtility.getDate("20080529", "yyyyMMdd"));
            l_ifoTradedProductParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080000L);
            l_tradedProductParams.setMarketId(1002);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("000005");
            l_tradingTimeParams.setBizDateType("2");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setInstitutionCode("0D");
            l_tradingTimeParams1.setBranchCode("123");
            l_tradingTimeParams1.setMarketCode("N1");
            l_tradingTimeParams1.setTradingTimeType("01");
            l_tradingTimeParams1.setProductCode("000005");
            l_tradingTimeParams1.setBizDateType("1");
            l_tradingTimeParams1.setBizdateCalcParameter("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            
            TradingTimeParams l_tradingTimeParams2 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams2.setInstitutionCode("0D");
            l_tradingTimeParams2.setBranchCode("123");
            l_tradingTimeParams2.setMarketCode("N1");
            l_tradingTimeParams2.setTradingTimeType("01");
            l_tradingTimeParams2.setProductCode("000005");
            l_tradingTimeParams2.setBizDateType("3");
            l_tradingTimeParams2.setBizdateCalcParameter("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams2);
            
            TradingTimeParams l_tradingTimeParams3 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams3.setInstitutionCode("0D");
            l_tradingTimeParams3.setBranchCode("123");
            l_tradingTimeParams3.setMarketCode("N1");
            l_tradingTimeParams3.setTradingTimeType("01");
            l_tradingTimeParams3.setProductCode("000005");
            l_tradingTimeParams3.setBizDateType("4");
            l_tradingTimeParams3.setBizdateCalcParameter("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams3);
            
            TradingTimeParams l_tradingTimeParams4 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams4.setInstitutionCode("0D");
            l_tradingTimeParams4.setBranchCode("123");
            l_tradingTimeParams4.setMarketCode("N1");
            l_tradingTimeParams4.setTradingTimeType("01");
            l_tradingTimeParams4.setProductCode("000005");
            l_tradingTimeParams4.setBizDateType("0");
            l_tradingTimeParams4.setBizdateCalcParameter("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams4);
            
            WEB3SuccOptionsCloseCompleteResponse l_response =
                l_serviceImpl.submitOrder(l_request);
            
            assertEquals("1001", l_response.orderActionId);
            
            WEB3GentradeTradingClendarContext l_clendarContext =
                (WEB3GentradeTradingClendarContext)
                    ThreadLocalSystemAttributesRegistry.getAttribute("web3.tradingcalendarcontext");
            
            assertEquals("000005", l_clendarContext.getProductCode());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSubmitOrder_0005()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccOptionSettleContractOrderServiceImpl l_serviceImpl =
            new WEB3ToSuccOptionSettleContractOrderServiceImplForTest1();
        
        try
        {
            WEB3SuccOptionsCloseCompleteRequest l_request = new WEB3SuccOptionsCloseCompleteRequest();
            l_request.succCommonInfo = new WEB3SuccCommonInfo();
            l_request.succCommonInfo.parentOrderId = "1001";
            l_request.succCommonInfo.succTradingType = "18";
            l_request.orderPriceDiv = "0";
            l_request.execCondType = "1";
            l_request.expirationDateType = "1";
            l_request.expirationDate = null;
            l_request.orderCondType = "0";
            l_request.stopOrderCondPrice = null;
            l_request.stopOrderCondOperator = null;
            l_request.wlimitOrderCondPrice = null;
            l_request.wlimitOrderCondOperator = null;
            l_request.wLimitOrderPriceDiv = null;
            l_request.wLimitPrice = null;
            l_request.wlimitExecCondType = null;
            l_request.wlimitEnableStatusDiv = null;
            l_request.closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[]{new WEB3FuturesOptionsCloseMarginContractUnit()};
            l_request.closeMarginContractUnits[0].id = "1001";
            l_request.closingOrder ="1";
            l_request.opOrderQuantity = "12";
            l_request.orderId = "1001";
            l_request.checkPrice = "20";
            l_request.checkDate = WEB3DateUtility.getDate("20080422", "yyyyMMdd");
            l_request.orderPriceDiv = "0";
            l_request.limitPrice = null;
            l_request.afterAdjustmentPrice = "15";
            
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = new RsvIfoOrderUnitParams();
            l_rsvIfoOrderUnitParams.setAccountId(333812512246L);
            l_rsvIfoOrderUnitParams.setMarketId(3303L);
            l_rsvIfoOrderUnitParams.setSubAccountId(123);
            l_rsvIfoOrderUnitParams.setBranchId(1234);
            l_rsvIfoOrderUnitParams.setOrderId(1001);
            l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.UNDEFINED);
            l_rsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.UNDEFINED);
            l_rsvIfoOrderUnitParams.setLastOrderActionSerialNo(0);
            l_rsvIfoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_rsvIfoOrderUnitParams.setQuantity(23);
            l_rsvIfoOrderUnitParams.setOrderStatus(OrderStatusEnum.UNDEFINED);
            l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.UNDEFINED);
            l_rsvIfoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.UNDEFINED);
            l_rsvIfoOrderUnitParams.setTaxType(TaxTypeEnum.UNDEFINED);
            l_rsvIfoOrderUnitParams.setBizDate("20070117");
            l_rsvIfoOrderUnitParams.setProductId(3304148080000L);
            l_rsvIfoOrderUnitParams.setParentOrderId(56);
            l_rsvIfoOrderUnitParams.setSerialNoInParent(68);
            l_rsvIfoOrderUnitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20080328", "yyyyMMdd"));
            l_rsvIfoOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20080328", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("3");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(3304148080000L);
            l_ifoProductParams.setTargetMarketId(1002);
            l_ifoProductParams.setUnderlyingProductCode("000005");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateSuccOrder",
                    new Class[]
                    {WEB3GentradeSubAccount.class, ProductTypeEnum.class, String.class, String.class, OrderUnit.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateSuccOrderMaxQuantity",
                    new Class[]{OrderUnit.class}, null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {}, new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getSessionProperty",
                    new Class[] {String.class},
                    "1");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "getDayTradeType",
                    new Class[]{ SettleContractEntry[].class },
                    "5");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl", "notifyRLS",
                    new Class[]
                    {IfoOrderUnit.class, OrderManagerPersistenceContext.class},
                    null);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "createIfoContract",
                    new Class[]{IfoOrderUnit.class},
                    new WEB3IfoContractImpl(l_ifoContractParams.getContractId()));
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParam = TestDBUtility.getTraderRow();
            l_traderParam.setTraderId(3338111123L);
            l_traderParam.setLoginId(256L);
            TestDBUtility.insertWithDel(l_traderParam);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(3338111123L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "createSettleContractEntry", new Class[]
                    { long.class, double.class, WEB3FuturesOptionsCloseMarginContractUnit[].class },
                    new SettleContractEntry[]{new SettleContractEntry(1001, 11)});
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "validateSettleContractOrder", new Class[]
                    {WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class, WEB3IfoContractImpl.class},
                    new NewOrderValidationResult(ProcessingResult.newSuccessResultInstance()));
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_calcResult =
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            l_calcResult.setCommission(20);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "calcEstimateDeliveryAmount", new Class[]
                    { WEB3GentradeCommission.class, double.class, SubAccount.class, WEB3IfoTradedProductImpl.class,
                            double.class, SideEnum.class, boolean.class, boolean.class },
                     l_calcResult);

            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "xblocks.gtl.attributes.systemtimestamp",
                    new Timestamp((WEB3DateUtility.getDate("20080422", "yyyyMMdd")).getTime()));
                
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "web3.attributes.basetimestampfororderbizdate", null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "submitIfoCloseContractNewOrder",
                    new Class[]
                    {SubAccount.class, WEB3IfoSettleContractOrderSpec.class, long.class, String.class, String.class,
                            Double.class, IfoOrderUnit.class, WEB3IfoEstimateDeliveryAmountCalcResult.class,
                            WEB3IfoContractImpl.class, String.class}, null);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setAccountId(333812512246L);
            l_ifoOrderUnitParams.setConfirmedPrice(22.3);
            l_ifoOrderUnitParams.setQuantity(18);
            l_ifoOrderUnitParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setValidForBizDate(null);
            l_ifoTradedProductParams.setTradedProductId(1006160060005L);
            l_ifoTradedProductParams.setProductId(3304148080000L);
            l_ifoTradedProductParams.setLastTradingDate(WEB3DateUtility.getDate("20080529", "yyyyMMdd"));
            l_ifoTradedProductParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080000L);
            l_tradedProductParams.setMarketId(1002);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("000005");
            l_tradingTimeParams.setBizDateType("2");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setInstitutionCode("0D");
            l_tradingTimeParams1.setBranchCode("123");
            l_tradingTimeParams1.setMarketCode("N1");
            l_tradingTimeParams1.setTradingTimeType("01");
            l_tradingTimeParams1.setProductCode("000005");
            l_tradingTimeParams1.setBizDateType("1");
            l_tradingTimeParams1.setBizdateCalcParameter("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            
            TradingTimeParams l_tradingTimeParams2 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams2.setInstitutionCode("0D");
            l_tradingTimeParams2.setBranchCode("123");
            l_tradingTimeParams2.setMarketCode("N1");
            l_tradingTimeParams2.setTradingTimeType("01");
            l_tradingTimeParams2.setProductCode("000005");
            l_tradingTimeParams2.setBizDateType("3");
            l_tradingTimeParams2.setBizdateCalcParameter("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams2);
            
            TradingTimeParams l_tradingTimeParams3 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams3.setInstitutionCode("0D");
            l_tradingTimeParams3.setBranchCode("123");
            l_tradingTimeParams3.setMarketCode("N1");
            l_tradingTimeParams3.setTradingTimeType("01");
            l_tradingTimeParams3.setProductCode("000005");
            l_tradingTimeParams3.setBizDateType("4");
            l_tradingTimeParams3.setBizdateCalcParameter("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams3);
            
            TradingTimeParams l_tradingTimeParams4 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams4.setInstitutionCode("0D");
            l_tradingTimeParams4.setBranchCode("123");
            l_tradingTimeParams4.setMarketCode("N1");
            l_tradingTimeParams4.setTradingTimeType("01");
            l_tradingTimeParams4.setProductCode("000005");
            l_tradingTimeParams4.setBizDateType("0");
            l_tradingTimeParams4.setBizdateCalcParameter("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams4);
            
            WEB3SuccOptionsCloseCompleteResponse l_response =
                l_serviceImpl.submitOrder(l_request);
            
            assertEquals("1001", l_response.orderActionId);
            
            WEB3GentradeTradingClendarContext l_clendarContext =
                (WEB3GentradeTradingClendarContext)
                    ThreadLocalSystemAttributesRegistry.getAttribute("web3.tradingcalendarcontext");
            
            assertEquals("000005", l_clendarContext.getProductCode());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionSettleContractOrderServiceImpl.validateSuccOrderMaxQuantity(OrderUnit)'
     */
    public void testValidateSuccOrderMaxQuantity_0001()
    {
        final String STR_METHOD_NAME = "testValidateSuccOrderMaxQuantity_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccOptionSettleContractOrderServiceImpl l_serviceImpl =
            new WEB3ToSuccOptionSettleContractOrderServiceImpl();
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateSuccOrderMaxQuantity",
                    new Class[]{OrderUnit.class},
                    new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02247,
                        "連続注文最大設定数を超過です。"));
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setAccountId(333812512246L);
            l_ifoOrderUnitParams.setConfirmedPrice(22.3);
            l_ifoOrderUnitParams.setQuantity(18);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            IfoOrderUnit l_ifoOrderUnit = new IfoContractSettleOrderUnitImpl(l_ifoOrderUnitParams.getOrderUnitId());
            
            l_serviceImpl.validateSuccOrderMaxQuantity(l_ifoOrderUnit);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02247, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidateSuccOrderMaxQuantity_0002()
    {
        final String STR_METHOD_NAME = "testValidateSuccOrderMaxQuantity_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccOptionSettleContractOrderServiceImpl l_serviceImpl =
            new WEB3ToSuccOptionSettleContractOrderServiceImpl();
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateSuccOrderMaxQuantity",
                    new Class[]{OrderUnit.class},
                    null);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setAccountId(333812512246L);
            l_ifoOrderUnitParams.setConfirmedPrice(22.3);
            l_ifoOrderUnitParams.setQuantity(18);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            IfoOrderUnit l_ifoOrderUnit = new IfoContractSettleOrderUnitImpl(l_ifoOrderUnitParams.getOrderUnitId());
            
            l_serviceImpl.validateSuccOrderMaxQuantity(l_ifoOrderUnit);
            assertTrue(true);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    
    /*
     * Test method for 'webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionSettleContractOrderServiceImpl.notifyRsvOrderRegister(long)'
     */
    public void testNotifyRsvOrderRegister()
    {
        final String STR_METHOD_NAME = "testNotifyRsvOrderRegister()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl", "notifyRLS",
                    new Class[]
                    {IfoOrderUnit.class, OrderManagerPersistenceContext.class},
                    null);
            
            WEB3ToSuccOptionSettleContractOrderServiceImpl l_serviceImpl =
                new WEB3ToSuccOptionSettleContractOrderServiceImpl();
            
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = new RsvIfoOrderUnitParams();
            l_rsvIfoOrderUnitParams.setAccountId(333812512246L);
            l_rsvIfoOrderUnitParams.setMarketId(3303L);
            l_rsvIfoOrderUnitParams.setSubAccountId(123);
            l_rsvIfoOrderUnitParams.setBranchId(1234);
            l_rsvIfoOrderUnitParams.setOrderId(21);
            l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.UNDEFINED);
            l_rsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.UNDEFINED);
            l_rsvIfoOrderUnitParams.setLastOrderActionSerialNo(0);
            l_rsvIfoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_rsvIfoOrderUnitParams.setQuantity(23);
            l_rsvIfoOrderUnitParams.setOrderStatus(OrderStatusEnum.UNDEFINED);
            l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.UNDEFINED);
            l_rsvIfoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.UNDEFINED);
            l_rsvIfoOrderUnitParams.setTaxType(TaxTypeEnum.UNDEFINED);
            l_rsvIfoOrderUnitParams.setBizDate("20070117");
            l_rsvIfoOrderUnitParams.setProductId(3304148080000L);
            l_rsvIfoOrderUnitParams.setParentOrderId(56);
            l_rsvIfoOrderUnitParams.setSerialNoInParent(68);
            l_rsvIfoOrderUnitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20080328", "yyyyMMdd"));
            l_rsvIfoOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20080328", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            l_serviceImpl.notifyRsvOrderRegister(21);
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
     * WEB3SuccOptionsCloseConfirmRequest
     * 取得した決済順序≠"ランダム"の場合は
     * リクエストアダプタ.リクエストデータ.注文数量 を使用。
     * １）で求めた注文数量 < リクエストアダプタ.親注文の注文単位.注文数量の場合
     * １）で求めた注文数量を返却する
     */
    public void testGetOrderQuantity_0001()
    {
        final String STR_METHOD_NAME = " testGetOrderQuantity_0001()";
        log.entering(STR_METHOD_NAME);
            try
            {
                TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
                IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
                TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
                
                WEB3ToSuccOptionSettleContractOrderServiceImpl l_impl =
                    new WEB3ToSuccOptionSettleContractOrderServiceImpl();
                WEB3SuccOptionsCloseConfirmRequest l_request = new WEB3SuccOptionsCloseConfirmRequest();
                WEB3SuccCommonInfo l_CommonInfo = new WEB3SuccCommonInfo();
                l_CommonInfo.parentOrderId = "1001";
                l_request.succCommonInfo = l_CommonInfo;
                l_request.opOrderQuantity = "11.0";
                Method l_method =
                    WEB3ToSuccOptionSettleContractOrderServiceImpl.class.getDeclaredMethod(
                        "getOrderQuantity", 
                        new Class[]{WEB3ToSuccOptionSettleContractOrderRequestAdapter.class});
                l_method.setAccessible(true);
                WEB3ToSuccOptionSettleContractOrderRequestAdapter l_adapter =
                    (WEB3ToSuccOptionSettleContractOrderRequestAdapter)WEB3ToSuccOptionSettleContractOrderRequestAdapter.create(l_request);
            
                Double l_dblOrderQuantity = (Double)l_method.invoke(l_impl, new Object[]{l_adapter});
                assertEquals(l_dblOrderQuantity + "", "11.0");
            }
            catch (Exception l_ex)
            {
                log.error("", l_ex);
                fail();   
            }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * WEB3SuccOptionsCloseConfirmRequest
     * 取得した決済順序=="ランダム"の場合は
     * リクエストアダプタ.リクエストデータ.返済建玉の全要素の注文数量のSUM値を使用
     * １）で求めた注文数量 < リクエストアダプタ.親注文の注文単位.注文数量の場合
     * １）で求めた注文数量を返却する
     */
    public void testGetOrderQuantity_0002()
    {
        final String STR_METHOD_NAME = " testGetOrderQuantity_0002()";
        log.entering(STR_METHOD_NAME);
            try
            {
                TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
                IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
                TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
                
                WEB3ToSuccOptionSettleContractOrderServiceImpl l_impl =
                    new WEB3ToSuccOptionSettleContractOrderServiceImpl();
                WEB3SuccOptionsCloseConfirmRequest l_request = new WEB3SuccOptionsCloseConfirmRequest();
                WEB3SuccCommonInfo l_CommonInfo = new WEB3SuccCommonInfo();
                l_CommonInfo.parentOrderId = "1001";
                l_request.succCommonInfo = l_CommonInfo;
                l_request.opOrderQuantity = "11.0";
                l_request.closingOrder = "0";
                WEB3FuturesOptionsCloseMarginContractUnit[] closeMarginContractUnits =
                    new WEB3FuturesOptionsCloseMarginContractUnit[2];
                WEB3FuturesOptionsCloseMarginContractUnit l_unit1 = new WEB3FuturesOptionsCloseMarginContractUnit();
                l_unit1.contractOrderQuantity = "22.0";
                WEB3FuturesOptionsCloseMarginContractUnit l_unit2 = new WEB3FuturesOptionsCloseMarginContractUnit();
                l_unit2.contractOrderQuantity = "11.0";
                closeMarginContractUnits[0] = l_unit1;
                closeMarginContractUnits[1] = l_unit2;
                l_request.closeMarginContractUnits = closeMarginContractUnits;
                Method l_method =
                    WEB3ToSuccOptionSettleContractOrderServiceImpl.class.getDeclaredMethod(
                        "getOrderQuantity", 
                        new Class[]{WEB3ToSuccOptionSettleContractOrderRequestAdapter.class});
                l_method.setAccessible(true);
                WEB3ToSuccOptionSettleContractOrderRequestAdapter l_adapter =
                    (WEB3ToSuccOptionSettleContractOrderRequestAdapter)WEB3ToSuccOptionSettleContractOrderRequestAdapter.create(l_request);
            
                Double l_dblOrderQuantity = (Double)l_method.invoke(l_impl, new Object[]{l_adapter});
                assertEquals(l_dblOrderQuantity + "", "33.0");
            }
            catch (Exception l_ex)
            {
                log.error("", l_ex);
                fail();   
            }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * WEB3SuccOptionsCloseConfirmRequest
     * １）で求めた注文数量 > リクエストアダプタ.親注文の注文単位.注文数量の場合
     * 「注文数量が親注文の注文数量を超過」の例外をスローする。
     * BUSINESS_ERROR_03065
     */
    public void testGetOrderQuantity_0003()
    {
        final String STR_METHOD_NAME = " testGetOrderQuantity_0003()";
        log.entering(STR_METHOD_NAME);
            try
            {
                TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
                IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
                l_ifoOrderUnitParams.setQuantity(10.0);
                TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
                
                WEB3ToSuccOptionSettleContractOrderServiceImpl l_impl =
                    new WEB3ToSuccOptionSettleContractOrderServiceImpl();
                WEB3SuccOptionsCloseConfirmRequest l_request = new WEB3SuccOptionsCloseConfirmRequest();
                WEB3SuccCommonInfo l_CommonInfo = new WEB3SuccCommonInfo();
                l_CommonInfo.parentOrderId = "1001";
                l_request.succCommonInfo = l_CommonInfo;
                l_request.opOrderQuantity = "11.0";
                l_request.closingOrder = "0";
                WEB3FuturesOptionsCloseMarginContractUnit[] closeMarginContractUnits =
                    new WEB3FuturesOptionsCloseMarginContractUnit[2];
                WEB3FuturesOptionsCloseMarginContractUnit l_unit1 = new WEB3FuturesOptionsCloseMarginContractUnit();
                l_unit1.contractOrderQuantity = "22.0";
                WEB3FuturesOptionsCloseMarginContractUnit l_unit2 = new WEB3FuturesOptionsCloseMarginContractUnit();
                l_unit2.contractOrderQuantity = "11.0";
                closeMarginContractUnits[0] = l_unit1;
                closeMarginContractUnits[1] = l_unit2;
                l_request.closeMarginContractUnits = closeMarginContractUnits;
                Method l_method =
                    WEB3ToSuccOptionSettleContractOrderServiceImpl.class.getDeclaredMethod(
                        "getOrderQuantity", 
                        new Class[]{WEB3ToSuccOptionSettleContractOrderRequestAdapter.class});
                l_method.setAccessible(true);
                WEB3ToSuccOptionSettleContractOrderRequestAdapter l_adapter =
                    (WEB3ToSuccOptionSettleContractOrderRequestAdapter)WEB3ToSuccOptionSettleContractOrderRequestAdapter.create(l_request);
            
                l_method.invoke(l_impl, new Object[]{l_adapter});
                fail();
            }
            catch (InvocationTargetException l_ex)
            {
                assertEquals(WEB3BusinessLayerException.class,
                        l_ex.getTargetException().getClass());
                    WEB3BusinessLayerException l_businessLayerException =
                        (WEB3BusinessLayerException)l_ex.getTargetException();
                assertEquals(l_businessLayerException.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_03065);
            }
            catch (Exception l_ex)
            {
                log.error("", l_ex);
                fail();   
            }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * WEB3SuccOptionsCloseCompleteRequest
     * 取得した決済順序≠"ランダム"の場合は
     * リクエストアダプタ.リクエストデータ.注文数量 を使用。
     * １）で求めた注文数量 < リクエストアダプタ.親注文の注文単位.注文数量の場合
     * １）で求めた注文数量を返却する
     */
    public void testGetOrderQuantity_0004()
    {
        final String STR_METHOD_NAME = " testGetOrderQuantity_0004()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            WEB3ToSuccOptionSettleContractOrderServiceImpl l_impl =
                new WEB3ToSuccOptionSettleContractOrderServiceImpl();
            WEB3SuccOptionsCloseCompleteRequest l_request = new WEB3SuccOptionsCloseCompleteRequest();
            WEB3SuccCommonInfo l_CommonInfo = new WEB3SuccCommonInfo();
            l_CommonInfo.parentOrderId = "1001";
            l_request.succCommonInfo = l_CommonInfo;
            l_request.opOrderQuantity = "11.0";
            Method l_method =
                WEB3ToSuccOptionSettleContractOrderServiceImpl.class.getDeclaredMethod(
                    "getOrderQuantity", 
                    new Class[]{WEB3ToSuccOptionSettleContractOrderRequestAdapter.class});
            l_method.setAccessible(true);
            WEB3ToSuccOptionSettleContractOrderRequestAdapter l_adapter =
                (WEB3ToSuccOptionSettleContractOrderRequestAdapter)WEB3ToSuccOptionSettleContractOrderRequestAdapter.create(l_request);
        
            Double l_dblOrderQuantity = (Double)l_method.invoke(l_impl, new Object[]{l_adapter});
            assertEquals(l_dblOrderQuantity + "", "11.0");
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();   
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * WEB3SuccOptionsCloseCompleteRequest
     * 取得した決済順序=="ランダム"の場合は
     * リクエストアダプタ.リクエストデータ.返済建玉の全要素の注文数量のSUM値を使用
     * １）で求めた注文数量 < リクエストアダプタ.親注文の注文単位.注文数量の場合
     * １）で求めた注文数量を返却する
     */
    public void testGetOrderQuantity_0005()
    {
        final String STR_METHOD_NAME = " testGetOrderQuantity_0005()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            WEB3ToSuccOptionSettleContractOrderServiceImpl l_impl =
                new WEB3ToSuccOptionSettleContractOrderServiceImpl();
            WEB3SuccOptionsCloseCompleteRequest l_request = new WEB3SuccOptionsCloseCompleteRequest();
            WEB3SuccCommonInfo l_CommonInfo = new WEB3SuccCommonInfo();
            l_CommonInfo.parentOrderId = "1001";
            l_request.succCommonInfo = l_CommonInfo;
            l_request.opOrderQuantity = "11.0";
            l_request.closingOrder = "0";
            WEB3FuturesOptionsCloseMarginContractUnit[] closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[2];
            WEB3FuturesOptionsCloseMarginContractUnit l_unit1 = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_unit1.contractOrderQuantity = "22.0";
            WEB3FuturesOptionsCloseMarginContractUnit l_unit2 = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_unit2.contractOrderQuantity = "11.0";
            closeMarginContractUnits[0] = l_unit1;
            closeMarginContractUnits[1] = l_unit2;
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            Method l_method =
                WEB3ToSuccOptionSettleContractOrderServiceImpl.class.getDeclaredMethod(
                    "getOrderQuantity", 
                    new Class[]{WEB3ToSuccOptionSettleContractOrderRequestAdapter.class});
            l_method.setAccessible(true);
            WEB3ToSuccOptionSettleContractOrderRequestAdapter l_adapter =
                (WEB3ToSuccOptionSettleContractOrderRequestAdapter)WEB3ToSuccOptionSettleContractOrderRequestAdapter.create(l_request);
        
            Double l_dblOrderQuantity = (Double)l_method.invoke(l_impl, new Object[]{l_adapter});
            assertEquals(l_dblOrderQuantity + "", "33.0");
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();   
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * WEB3SuccOptionsCloseCompleteRequest
     * １）で求めた注文数量 > リクエストアダプタ.親注文の注文単位.注文数量の場合
     * 「注文数量が親注文の注文数量を超過」の例外をスローする。
     * BUSINESS_ERROR_03065
     */
    public void testGetOrderQuantity_0006()
    {
        final String STR_METHOD_NAME = " testGetOrderQuantity_0006()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setQuantity(10.0);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            WEB3ToSuccOptionSettleContractOrderServiceImpl l_impl =
                new WEB3ToSuccOptionSettleContractOrderServiceImpl();
            WEB3SuccOptionsCloseCompleteRequest l_request = new WEB3SuccOptionsCloseCompleteRequest();
            WEB3SuccCommonInfo l_CommonInfo = new WEB3SuccCommonInfo();
            l_CommonInfo.parentOrderId = "1001";
            l_request.succCommonInfo = l_CommonInfo;
            l_request.opOrderQuantity = "11.0";
            l_request.closingOrder = "0";
            WEB3FuturesOptionsCloseMarginContractUnit[] closeMarginContractUnits =
                new WEB3FuturesOptionsCloseMarginContractUnit[2];
            WEB3FuturesOptionsCloseMarginContractUnit l_unit1 = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_unit1.contractOrderQuantity = "22.0";
            WEB3FuturesOptionsCloseMarginContractUnit l_unit2 = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_unit2.contractOrderQuantity = "11.0";
            closeMarginContractUnits[0] = l_unit1;
            closeMarginContractUnits[1] = l_unit2;
            l_request.closeMarginContractUnits = closeMarginContractUnits;
            Method l_method =
                WEB3ToSuccOptionSettleContractOrderServiceImpl.class.getDeclaredMethod(
                    "getOrderQuantity", 
                    new Class[]{WEB3ToSuccOptionSettleContractOrderRequestAdapter.class});
            l_method.setAccessible(true);
            WEB3ToSuccOptionSettleContractOrderRequestAdapter l_adapter =
                (WEB3ToSuccOptionSettleContractOrderRequestAdapter)WEB3ToSuccOptionSettleContractOrderRequestAdapter.create(l_request);
        
            l_method.invoke(l_impl, new Object[]{l_adapter});
            fail();
        }
        catch (InvocationTargetException l_ex)
        {
            assertEquals(WEB3BusinessLayerException.class,
                    l_ex.getTargetException().getClass());
                WEB3BusinessLayerException l_businessLayerException =
                    (WEB3BusinessLayerException)l_ex.getTargetException();
            assertEquals(l_businessLayerException.getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_03065);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetDayTradeType_0001()
    {
        final String STR_METHOD_NAME = "testGetDayTradeType_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3ToSuccOptionSettleContractOrderServiceImpl l_impl =
                new WEB3ToSuccOptionSettleContractOrderServiceImpl();
            
            SettleContractEntry[] l_settleContractEntries =
                new SettleContractEntry[]{new SettleContractEntry(1000, 50)};
            
            WEB3SuccOptionsCloseConfirmRequest l_confirmRequest = new WEB3SuccOptionsCloseConfirmRequest();
            l_confirmRequest.succCommonInfo = new WEB3SuccCommonInfo();
            l_confirmRequest.succCommonInfo.succTradingType = "17";
            WEB3ToSuccOptionSettleContractOrderRequestAdapter l_adapter =
                new WEB3ToSuccOptionSettleContractOrderRequestAdapter();
            l_adapter.request = l_confirmRequest;
            
            assertEquals("5", l_impl.getDayTradeType(l_settleContractEntries, l_adapter));
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetDayTradeType_0002()
    {
        final String STR_METHOD_NAME = "testGetDayTradeType_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3ToSuccOptionSettleContractOrderServiceImpl l_impl =
                new WEB3ToSuccOptionSettleContractOrderServiceImpl();
            
            SettleContractEntry[] l_settleContractEntries =
                new SettleContractEntry[]{new SettleContractEntry(1000, 50)};
            
            WEB3SuccOptionsCloseConfirmRequest l_confirmRequest = new WEB3SuccOptionsCloseConfirmRequest();
            l_confirmRequest.succCommonInfo = new WEB3SuccCommonInfo();
            l_confirmRequest.succCommonInfo.succTradingType = "12";
            WEB3ToSuccOptionSettleContractOrderRequestAdapter l_adapter =
                new WEB3ToSuccOptionSettleContractOrderRequestAdapter();
            l_adapter.request = l_confirmRequest;
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "getDayTradeType", new Class[]
                    { SettleContractEntry[].class }, "6");
            
            assertEquals("6", l_impl.getDayTradeType(l_settleContractEntries, l_adapter));
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /*
     * Test method for 'webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionSettleContractOrderServiceImpl.createContractUnit(SettleContractEntry[], WEB3FuturesSettleContractOrderRequestAdapter, WEB3IfoTradedProductImpl)'
     */
    public void testCreateContractUnit_0001()
    {
        final String STR_METHOD_NAME = "testCreateContractUnit_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccOptionSettleContractOrderServiceImpl l_serviceImpl =
            new WEB3ToSuccOptionSettleContractOrderServiceImpl();
        try
        {
            SettleContractEntry[] l_settleContractEntrys =
                new SettleContractEntry[]{new SettleContractEntry(0, 20)};
            WEB3ToSuccOptionSettleContractOrderRequestAdapter l_requestAdapter =
                new WEB3ToSuccOptionSettleContractOrderRequestAdapter();
            
            WEB3SuccOptionsCloseConfirmRequest l_request = new WEB3SuccOptionsCloseConfirmRequest();
            l_request.succCommonInfo = new WEB3SuccCommonInfo();
            l_request.succCommonInfo.succTradingType = "17";
            l_requestAdapter.request = l_request;
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setAccountId(333812512246L);
            l_ifoOrderUnitParams.setConfirmedPrice(22.3);
            l_ifoOrderUnitParams.setQuantity(18);
            l_ifoOrderUnitParams.setBizDate("20080401");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            l_requestAdapter.parentOrderUnit = new IfoContractSettleOrderUnitImpl(l_ifoOrderUnitParams.getOrderUnitId());
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setValidForBizDate(null);
            l_ifoTradedProductParams.setTradedProductId(1006160060005L);
            l_ifoTradedProductParams.setProductId(3304148080000L);
            l_ifoTradedProductParams.setLastTradingDate(WEB3DateUtility.getDate("20080529", "yyyyMMdd"));
            l_ifoTradedProductParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080000L);
            l_tradedProductParams.setMarketId(1002);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(3304148080000L);
            l_ifoProductParams.setUnderlyingProductCode("000005");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            WEB3IfoTradedProductImpl l_tradedProduct = new WEB3IfoTradedProductImpl(l_tradedProductParams);
            
            WEB3FuturesOptionsContractUnit[] l_contractUnits =
                l_serviceImpl.createContractUnit(l_settleContractEntrys, l_requestAdapter, l_tradedProduct);
            
            assertEquals("18", l_contractUnits[0].contractQuantity);
            assertEquals(WEB3DateUtility.getDate("20080401", "yyyyMMdd"), l_contractUnits[0].openDate);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCreateContractUnit_0002()
    {
        final String STR_METHOD_NAME = "testCreateContractUnit_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccOptionSettleContractOrderServiceImpl l_serviceImpl =
            new WEB3ToSuccOptionSettleContractOrderServiceImpl();
        try
        {
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_marketParams);
            
            SettleContractEntry[] l_settleContractEntrys =
                new SettleContractEntry[]{new SettleContractEntry(1001, 20)};
            WEB3ToSuccOptionSettleContractOrderRequestAdapter l_requestAdapter =
                new WEB3ToSuccOptionSettleContractOrderRequestAdapter();
            
            WEB3SuccOptionsCloseConfirmRequest l_request = new WEB3SuccOptionsCloseConfirmRequest();
            l_request.succCommonInfo = new WEB3SuccCommonInfo();
            l_request.succCommonInfo.succTradingType = "12";
            l_requestAdapter.request = l_request;
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setAccountId(333812512246L);
            l_ifoOrderUnitParams.setConfirmedPrice(22.3);
            l_ifoOrderUnitParams.setQuantity(18);
            l_ifoOrderUnitParams.setBizDate("20080401");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            l_requestAdapter.parentOrderUnit = new IfoContractSettleOrderUnitImpl(l_ifoOrderUnitParams.getOrderUnitId());
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(3304148080000L);
            l_ifoProductParams.setUnderlyingProductCode("000005");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setValidForBizDate(null);
            l_ifoTradedProductParams.setTradedProductId(1006160060005L);
            l_ifoTradedProductParams.setProductId(3304148080000L);
            l_ifoTradedProductParams.setLastTradingDate(WEB3DateUtility.getDate("20080529", "yyyyMMdd"));
            l_ifoTradedProductParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080000L);
            l_tradedProductParams.setMarketId(1002);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.commit();
            WEB3IfoTradedProductImpl l_tradedProduct = new WEB3IfoTradedProductImpl(l_tradedProductParams);
            
            WEB3FuturesOptionsContractUnit[] l_contractUnits =
                l_serviceImpl.createContractUnit(l_settleContractEntrys, l_requestAdapter, l_tradedProduct);
            
            assertEquals("20", l_contractUnits[0].contractOrderQuantity);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    private class WEB3ToSuccOptionSettleContractOrderServiceImplForTest
        extends WEB3ToSuccOptionSettleContractOrderServiceImpl
    {
        protected WEB3SuccOptionsCloseConfirmResponse validateOrder(WEB3SuccOptionsCloseConfirmRequest l_request)
            throws WEB3BaseException
        {
            WEB3SuccOptionsCloseConfirmResponse l_response = new WEB3SuccOptionsCloseConfirmResponse();
            l_response.afterAdjustmentPrice = "15";
            return l_response;
        }
        
        protected WEB3SuccOptionsCloseCompleteResponse submitOrder(WEB3SuccOptionsCloseCompleteRequest l_request)
        throws WEB3BaseException
        {
            WEB3SuccOptionsCloseCompleteResponse l_response = new WEB3SuccOptionsCloseCompleteResponse();
            l_response.lastUpdatedTimestamp = WEB3DateUtility.getDate("20080331", "yyyyMMdd");
            return l_response;
        }
    }
    
    private class WEB3ToSuccOptionSettleContractOrderRequestAdapterForTest
        extends WEB3ToSuccOptionSettleContractOrderRequestAdapter
    {
        public WEB3IfoContractImpl getContract() throws WEB3BaseException
        {
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            TestDBUtility.insertWithDel(l_ifoContractParams);
            return new WEB3IfoContractImpl(l_ifoContractParams);
        }
    }

    private class WEB3ToSuccOptionSettleContractOrderServiceImplForTest1
        extends WEB3ToSuccOptionSettleContractOrderServiceImpl
    {
        protected NewOrderValidationResult validateFuturesSettleContractOrder(
            SubAccount l_subAccount,
            IfoSettleContractOrderSpec l_settleContractOrderSpec,
            WEB3FuturesSettleContractOrderRequestAdapter l_requestAdapter)
            throws WEB3BaseException
        {
            return new NewOrderValidationResult(ProcessingResult.SUCCESS_RESULT);
        }
        
        protected WEB3IfoEstimateDeliveryAmountCalcResult getEstimateSettlementIncome(
            WEB3GentradeCommission l_genCommission,
            double l_dblLimitPrice,
            SubAccount l_subAccount,
            WEB3IfoTradedProductImpl l_tradedProduct,
            SettleContractEntry[] l_settleContractEntrys,
            double l_dblQuantity,
            WEB3FuturesSettleContractOrderRequestAdapter l_requestAdapter)
            throws WEB3BaseException
        {
            WEB3IfoEstimateDeliveryAmountCalcResult l_result = new WEB3IfoEstimateDeliveryAmountCalcResult();
            l_result.setCommission(20);
            return l_result;
        }
        
        protected WEB3FuturesOptionsContractUnit[] createContractUnit(
            SettleContractEntry[] l_settleContractEntrys,
            WEB3FuturesSettleContractOrderRequestAdapter l_requestAdapter,
            WEB3IfoTradedProductImpl l_tradedProduct)
            throws WEB3BaseException
        {
            WEB3FuturesOptionsContractUnit[] l_contractUnits = new WEB3FuturesOptionsContractUnit[1];
            l_contractUnits[0] = new WEB3FuturesOptionsContractUnit();
            l_contractUnits[0].contractQuantity = "300";
            return l_contractUnits;
        }
        
        protected void submitSettleContractOrder(
            WEB3FuturesSettleContractOrderRequestAdapter l_requestAdapter,
            SubAccount l_subAccount,
            IfoSettleContractOrderSpec l_changeSettleContractOrderSpec,
            long l_lngOrderID,
            WEB3GentradeCommission l_genCommission,
            WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmountCalcResult)
            throws WEB3BaseException
        {
        
        }
    }

}
@
