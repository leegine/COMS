head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.45.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionSettleContractOrderServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3OptionSettleContractOrderServiceImplTest.javaテスト
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/15 金傑 (中訊) 新規作成 仕様変更モデルNo.655
*/
package webbroker3.ifo.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingCalendarDetails;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FutureOpAccountDef;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarDetailsImpl;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoSettleContractOrderSpec;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.message.WEB3FuturesOptionsCloseMarginContractUnit;
import webbroker3.ifo.message.WEB3OptionsCloseMarginCompleteRequest;
import webbroker3.ifo.message.WEB3OptionsCloseMarginCompleteResponse;
import webbroker3.ifo.message.WEB3OptionsCloseMarginConfirmRequest;
import webbroker3.ifo.message.WEB3OptionsCloseMarginConfirmResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.mock.util.WEB3MockObjectRuntimeException;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * （株価指数オプション返済注文サービス実装クラステスト）<BR>
 * 
 * @@author 金傑
 * @@version 1.0
 */
public class WEB3OptionSettleContractOrderServiceImplTest extends TestBaseForMock
{
    private WEB3OptionSettleContractOrderServiceImpl l_serviceImpl = null;

    private WEB3OptionsCloseMarginConfirmRequest l_request = null;

    private WEB3OptionsCloseMarginConfirmResponse l_response = null;
    
    private WEB3OptionsCloseMarginCompleteRequest l_optionsRequest = null;
    
    private WEB3OptionsCloseMarginCompleteResponse l_optionsResponse = null;

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3OptionSettleContractOrderServiceImplTest.class);

    public WEB3OptionSettleContractOrderServiceImplTest(String name)
    {
        super(name);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.l_serviceImpl = new WEB3OptionSettleContractOrderServiceImplForTest();
        this.l_request = new WEB3OptionsCloseMarginConfirmRequestForTest();
        this.l_optionsRequest = new WEB3OptionsCloseMarginCompleteRequestForTest();
        this.initData();
        this.setMockMethod();
    }

    protected void tearDown() throws Exception
    {
        this.l_serviceImpl = null;
        this.l_request = null;
//        super.checkMethodValue();
        super.tearDown();
    }

    /**
     * 返済注文内容のget夕場前繰越対象フラグを取得する
     *
     */
//    public void testValidateOrder_C0001()
//    {
//        final String STR_METHOD_NAME = "testValidateOrder_C0001()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
//                    "validateSettleContractOrder", 
//                    new Class[]{ WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class },
//                    new WEB3MockObjectRuntimeException(STR_METHOD_NAME));
//                    
//            this.l_request.wlimitExecCondType = "1";
//            this.l_request.expirationDateType = "2";
//            this.l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[1];
//            this.l_request.closeMarginContractUnits[0] = new WEB3FuturesOptionsCloseMarginContractUnit();
//            this.l_request.closeMarginContractUnits[0].id = "555";
//            this.l_response = this.l_serviceImpl.validateOrder(this.l_request);
//            fail();
//        }
//        catch(WEB3MockObjectRuntimeException l_ex)
//        {
//            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
//                    "webbroker3.gentrade.WEB3GentradeAccountManager", 
//                    "getMainAccount", 
//                    new Class[] { long.class });
//
//           assertEquals(123L,((Long)l_paramsValue1.getFirstCalled()[0]).longValue());
//           
//           WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
//                   "webbroker3.gentrade.WEB3GentradeAccountManager", 
//                   "getSubAccount", 
//                   new Class[] { long.class,SubAccountTypeEnum.class });
//           
//           assertEquals(123L,((Long)l_paramsValue2.getFirstCalled()[0]).longValue());
//           assertEquals(SubAccountTypeEnum.class,l_paramsValue2.getFirstCalled()[1].getClass());
//           
//           WEB3MockObjectParamsValue l_paramsValue3 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
//                   "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
//                   "createSettleContractEntry", 
//                   new Class[]{ long.class, double.class, WEB3FuturesOptionsCloseMarginContractUnit[].class });
//           assertEquals(0L,((Long)l_paramsValue3.getFirstCalled()[0]).longValue());
//           assertEquals(0,((Double)l_paramsValue3.getFirstCalled()[1]).doubleValue(),0);
//           assertEquals("555",((WEB3FuturesOptionsCloseMarginContractUnit[])l_paramsValue3.getFirstCalled()[2])[0].id);
//           
//           WEB3MockObjectParamsValue l_paramsValue4 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
//                   "webbroker3.gentrade.WEB3GentradeAccountManager", 
//                   "getBranch", 
//                   new Class[]{long.class});
//           assertEquals(33382L,((Long)l_paramsValue4.getFirstCalled()[0]).longValue());
//           
//           WEB3MockObjectParamsValue l_paramsValue5 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
//                   "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
//                   "validateSettleContractOrder", 
//                   new Class[]{WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class});
//           assertEquals(false,((WEB3IfoSettleContractOrderSpec)l_paramsValue5.getFirstCalled()[1]).getEveningSessionCarryoverFlag());
//        }
//        catch (Exception l_ex)
//        {
//            log.debug(l_ex.getMessage(), l_ex);
//            log.error("", l_ex);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
    
    public void testValidateOrder_C0002()
    {
        final String STR_METHOD_NAME = "testValidateOrder_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(IfoContractParams.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setContractId(555L);
            l_ifoContractParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderId(1005);
            l_ifoOrderUnitParams.setClosingOrder("0");
            l_ifoOrderUnitParams.setProductId(l_ifoContractParams.getProductId());
            l_ifoOrderUnitParams.setMarketId(l_ifoContractParams.getMarketId());
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
            SettleContractEntry[] l_eqOrderEntry = new SettleContractEntry[1];
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_ifoContractParams.getProductId());
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(1234L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1234L);
            l_mainAccountParams.setIfoAccOpenDivTokyo(WEB3FutureOpAccountDef.OP_ACCOUNT_ESTABLISH);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(l_ifoContractParams.getProductId());
            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
            l_ifoProductParams.setTargetMarketId(l_ifoOrderUnitParams.getMarketId());
            l_ifoProductParams.setUnderlyingProductCode("0");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(l_ifoContractParams.getMarketId());
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(l_ifoContractParams.getProductId());
            l_tradedProductParams.setMarketId(l_ifoContractParams.getMarketId());
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductParams.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setProductId(l_ifoContractParams.getProductId());
            l_ifoTradedProductParams.setMarketId(l_ifoContractParams.getMarketId());
            l_ifoTradedProductParams.setTradedProductId(l_tradedProductParams.getTradedProductId());
//            l_ifoTradedProductParams.setValidForBizDate("20071123");
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            TestDBUtility.commit();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "validateSettleContractOrder", 
                    new Class[]{ WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class },
                    new WEB3MockObjectRuntimeException(STR_METHOD_NAME));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "createSettleContractEntry", 
                    new Class[]{ long.class, double.class, WEB3FuturesOptionsCloseMarginContractUnit[].class },
                    l_eqOrderEntry);
            SettleContractEntry l_entry = new SettleContractEntry(l_ifoContractParams.getContractId(), 10D);
            l_eqOrderEntry[0] = l_entry;
            
            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            NewOrderValidationResult l_result = new NewOrderValidationResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "validateSettleContractOrder", new Class[]
                    { WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class },
                    l_result);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getSessionProperty",
                    new Class[] {String.class},
                    "1");
            
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_sadresult =
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            l_sadresult.setCalcUnitPrice(10);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "calcEstimateDeliveryAmount",
                    new Class[]
                    { WEB3GentradeCommission.class, double.class, SubAccount.class, WEB3IfoTradedProductImpl.class,
                    double.class, SideEnum.class, boolean.class, boolean.class },
                    l_sadresult);

            TradingCalendarDetails tradingCalendarDetails =
                new WEB3GentradeTradingClendarDetailsImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeTradingCalendarModelImpl",
                    "getTradingCalendarDetails",
                    new Class[] {long.class},
                    tradingCalendarDetails);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeTradingCalendarModelImpl",
                    "getCurrentBizDate",
                    new Class[] {long.class},
                    WEB3DateUtility.getDate("20070619","yyyyMMdd"));

            this.l_request.wlimitExecCondType = "1";
            this.l_request.expirationDateType = "2";
            this.l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[1];
            this.l_request.closeMarginContractUnits[0] = new WEB3FuturesOptionsCloseMarginContractUnit();
            this.l_request.closeMarginContractUnits[0].id = "555";
            this.l_response = this.l_serviceImpl.validateOrder(this.l_request);
            assertEquals(WEB3DateUtility.formatDate(l_response.expirationDate, "yyyyMMdd"), WEB3DateUtility.formatDate(new Date(),"yyyyMMdd"));
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 返済注文内容のget夕場前繰越対象フラグを取得する
     *
     */
//    public void testSubmitOrder_C0001()
//    {
//        final String STR_METHOD_NAME = "testSubmitOrder_C0001()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
//                    "validateSettleContractOrder", 
//                    new Class[]{ WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class },
//                    new WEB3MockObjectRuntimeException(STR_METHOD_NAME));
//                    
//            this.l_optionsRequest.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[1];
//            this.l_optionsRequest.closeMarginContractUnits[0] = new WEB3FuturesOptionsCloseMarginContractUnit();
//            this.l_optionsRequest.closeMarginContractUnits[0].id = "666";
//            this.l_optionsRequest.orderPriceDiv = "0";
//            this.l_optionsResponse = this.l_serviceImpl.submitOrder(this.l_optionsRequest);
//            fail();
//        }
//        catch(WEB3MockObjectRuntimeException l_ex)
//        {
//            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
//                    "webbroker3.gentrade.WEB3GentradeAccountManager", 
//                    "getMainAccount", 
//                    new Class[] { long.class });
//
//           assertEquals(123L,((Long)l_paramsValue1.getFirstCalled()[0]).longValue());
//           
//           WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
//                   "webbroker3.gentrade.WEB3GentradeAccountManager", 
//                   "getSubAccount", 
//                   new Class[] { long.class,SubAccountTypeEnum.class });
//           
//           assertEquals(123L,((Long)l_paramsValue2.getFirstCalled()[0]).longValue());
//           assertEquals(SubAccountTypeEnum.class,l_paramsValue2.getFirstCalled()[1].getClass());
//           
//           WEB3MockObjectParamsValue l_paramsValue3 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
//                   "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
//                   "createSettleContractEntry", 
//                   new Class[]{ long.class, double.class, WEB3FuturesOptionsCloseMarginContractUnit[].class });
//           assertEquals(0L,((Long)l_paramsValue3.getFirstCalled()[0]).longValue());
//           assertEquals(0,((Double)l_paramsValue3.getFirstCalled()[1]).doubleValue(),0);
//           assertEquals("666",((WEB3FuturesOptionsCloseMarginContractUnit[])l_paramsValue3.getFirstCalled()[2])[0].id);
//           
//           WEB3MockObjectParamsValue l_paramsValue4 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
//                   "webbroker3.gentrade.WEB3GentradeAccountManager", 
//                   "getBranch", 
//                   new Class[]{long.class});
//           assertEquals(33382L,((Long)l_paramsValue4.getFirstCalled()[0]).longValue());
//           
//           WEB3MockObjectParamsValue l_paramsValue5 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
//                   "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
//                   "validateSettleContractOrder", 
//                   new Class[]{WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class});
//           assertEquals(false,((WEB3IfoSettleContractOrderSpec)l_paramsValue5.getFirstCalled()[1]).getEveningSessionCarryoverFlag());
//        }
//        catch (Exception l_ex)
//        {
//            log.error("", l_ex);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
    
    public void testSubmitOrder_C0002()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(IfoContractParams.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setContractId(666L);
            l_ifoContractParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderId(1005);
            l_ifoOrderUnitParams.setClosingOrder("0");
            l_ifoOrderUnitParams.setProductId(l_ifoContractParams.getProductId());
            l_ifoOrderUnitParams.setMarketId(l_ifoContractParams.getMarketId());
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
            SettleContractEntry[] l_eqOrderEntry = new SettleContractEntry[1];
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_ifoContractParams.getProductId());
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(1234L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(1234L);
            l_mainAccountParams.setIfoAccOpenDivTokyo(WEB3FutureOpAccountDef.OP_ACCOUNT_ESTABLISH);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(l_ifoContractParams.getProductId());
            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
            l_ifoProductParams.setTargetMarketId(l_ifoOrderUnitParams.getMarketId());
            l_ifoProductParams.setUnderlyingProductCode("0");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(l_ifoContractParams.getMarketId());
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(l_ifoContractParams.getProductId());
            l_tradedProductParams.setMarketId(l_ifoContractParams.getMarketId());
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductParams.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setProductId(l_ifoContractParams.getProductId());
            l_ifoTradedProductParams.setMarketId(l_ifoContractParams.getMarketId());
            l_ifoTradedProductParams.setTradedProductId(l_tradedProductParams.getTradedProductId());
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "validateSettleContractOrder", 
                    new Class[]{ WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class },
                    new WEB3MockObjectRuntimeException(STR_METHOD_NAME));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "createSettleContractEntry", 
                    new Class[]{ long.class, double.class, WEB3FuturesOptionsCloseMarginContractUnit[].class },
                    l_eqOrderEntry);
            SettleContractEntry l_entry = new SettleContractEntry(l_ifoContractParams.getContractId(), 10D);
            l_eqOrderEntry[0] = l_entry;

            TradingCalendarDetails tradingCalendarDetails =
                new WEB3GentradeTradingClendarDetailsImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeTradingCalendarModelImpl",
                    "getTradingCalendarDetails",
                    new Class[] {long.class},
                    tradingCalendarDetails);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeTradingCalendarModelImpl",
                    "getCurrentBizDate",
                    new Class[] {long.class},
                    WEB3DateUtility.getDate("20070619","yyyyMMdd"));


            this.l_optionsRequest.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[1];
            this.l_optionsRequest.closeMarginContractUnits[0] = new WEB3FuturesOptionsCloseMarginContractUnit();
            this.l_optionsRequest.closeMarginContractUnits[0].id = "666";
            this.l_optionsRequest.orderPriceDiv = "0";
            this.l_optionsResponse = this.l_serviceImpl.submitOrder(this.l_optionsRequest);
            fail();
        }
        catch(WEB3MockObjectRuntimeException l_ex)
        {
            WEB3MockObjectParamsValue l_paramsValue1 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager", 
                    "getMainAccount", 
                    new Class[] { long.class });

           assertEquals(123L,((Long)l_paramsValue1.getFirstCalled()[0]).longValue());
           
           WEB3MockObjectParamsValue l_paramsValue2 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                   "webbroker3.gentrade.WEB3GentradeAccountManager", 
                   "getSubAccount", 
                   new Class[] { long.class,SubAccountTypeEnum.class });
           
           assertEquals(123L,((Long)l_paramsValue2.getFirstCalled()[0]).longValue());
           assertEquals(SubAccountTypeEnum.class,l_paramsValue2.getFirstCalled()[1].getClass());
           
           WEB3MockObjectParamsValue l_paramsValue3 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                   "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                   "createSettleContractEntry", 
                   new Class[]{ long.class, double.class, WEB3FuturesOptionsCloseMarginContractUnit[].class });
           assertEquals(0L,((Long)l_paramsValue3.getFirstCalled()[0]).longValue());
           assertEquals(0,((Double)l_paramsValue3.getFirstCalled()[1]).doubleValue(),0);
           assertEquals("666",((WEB3FuturesOptionsCloseMarginContractUnit[])l_paramsValue3.getFirstCalled()[2])[0].id);
           
           WEB3MockObjectParamsValue l_paramsValue4 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                   "webbroker3.gentrade.WEB3GentradeAccountManager", 
                   "getBranch", 
                   new Class[]{long.class});
           assertEquals(33382L,((Long)l_paramsValue4.getFirstCalled()[0]).longValue());
           
           WEB3MockObjectParamsValue l_paramsValue5 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                   "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                   "validateSettleContractOrder", 
                   new Class[]{WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class});

           
           assertEquals(false,((WEB3IfoSettleContractOrderSpec)l_paramsValue5.getFirstCalled()[1]).getEveningSessionCarryoverFlag());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    private void initData()
    {
        final String STR_METHOD_NAME = "initData()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            //口座ＩＤ]
            l_subAccountParams.setAccountId(333812512266L);
            //補助口座ＩＤ
            l_subAccountParams.setSubAccountId(33381251220366L);
            l_subAccountParams.setInstitutionCode("1D");
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512266L);
            l_mainAccountParams.setInstitutionCode("1D");
            l_mainAccountParams.setIfoAccOpenDivTokyo("3");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33382);
            l_branchParams.setBranchCode("387");
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("1D");
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date());
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setContractId(1005);
            l_ifoContractParams.setProductId(1006160060007L);
            l_ifoContractParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060007L);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006160060007L);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_IfoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_IfoTradedProductParams.setTradedProductId(1006160060007L);
            l_IfoTradedProductParams.setMarketId(1002L);
            l_IfoTradedProductParams.setProductId(1006160060007L);
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060007L);
            l_tradedProductParams.setMarketId(1002L);
            l_tradedProductParams.setProductId(1006160060007L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002L);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setTradedProductId(1006160060007L);
            l_ifoTradedProductUpdqParams.setValidForBizDate("20070619");
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }
    
    private void setMockMethod()
    {
        final String STR_METHOD_NAME = "setMockMethod()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(123L));
            
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(333812512266L);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "getMainAccount",
                    new Class[] {long.class},
                    l_mainAccount);
            
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(333812512266L,33381251220366L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager", 
                    "getSubAccount",
                    new Class[] { long.class, SubAccountTypeEnum.class },
                    l_subAccount);
            
            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution("1D");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeSubAccount", 
                    "getInstitution", 
                    new Class[]{},
                    l_institution);
            
            LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getLoginInfo",
                    new Class[] {},
                    l_loginInfoImplForMock);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(123456L));
            
            SettleContractEntry [] l_settleContractOrderEntries = new SettleContractEntry[1];
            l_settleContractOrderEntries[0] = new  SettleContractEntry(1005,500);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "createSettleContractEntry", 
                    new Class[]{ long.class, double.class, WEB3FuturesOptionsCloseMarginContractUnit[].class },
                    l_settleContractOrderEntries);
            
            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(33382L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "getBranch",
                    new Class[] {long.class},
                    l_branch);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeSubAccount",
                    "getWeb3GenBranch",
                    new Class[] {},
                    l_branch);
            
            NewOrderValidationResult l_result = new NewOrderValidationResult(ProcessingResult.SUCCESS_RESULT,123);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "validateSettleContractOrder", 
                    new Class[]{ WEB3GentradeSubAccount.class, WEB3IfoSettleContractOrderSpec.class },
                    l_result);
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_START + STR_METHOD_NAME);
    }

    private class WEB3OptionsCloseMarginConfirmRequestForTest extends WEB3OptionsCloseMarginConfirmRequest
    {
        public void validate() throws WEB3BaseException
        {
            log.debug("WEB3OptionsCloseMarginConfirmRequestForTest.validate()");
        }
    }
    
    private class WEB3OptionsCloseMarginCompleteRequestForTest extends WEB3OptionsCloseMarginCompleteRequest
    {
        public void validate() throws WEB3BaseException
        {
            log.debug("WEB3OptionsCloseMarginCompleteRequestForTest.validate()");
        }
    }

    private class WEB3OptionSettleContractOrderServiceImplForTest extends WEB3OptionSettleContractOrderServiceImpl
    {
        public Trader getTrader() throws WEB3SystemLayerException
        {
            return null;
        }
    }
}
@
