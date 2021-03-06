head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.18.15;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3ToSuccFuturesCancelOrderServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : ()åa¤ Ø\[VVXeæñ
 File Name        : iA±jæ¨æÁ¶T[rXImpl(WEB3ToSuccFuturesCancelOrderServiceImplTest.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/03/25 Ð±ì(u) VKì¬f268
 */
package webbroker3.triggerorder.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Calendar;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.BranchIndexDealtCondParams;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.quoteadaptor.AskPriceTitle;
import webbroker3.quoteadaptor.BidPriceTitle;
import webbroker3.quoteadaptor.CurrentPriceFlag;
import webbroker3.quoteadaptor.DataType;
import webbroker3.quoteadaptor.RealType;
import webbroker3.quoteadaptor.prototype.data.Web3QuoteProtoParams;
import webbroker3.quoteadaptor.stdimpls.WEB3IfoQuoteDataImplForMock;
import webbroker3.triggerorder.WEB3ToSuccIfoOrderUnitImpl;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitParams;
import webbroker3.triggerorder.message.WEB3SuccFuturesCancelCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesCancelCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccFuturesCancelConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesCancelConfirmResponse;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseConfirmRequest;
import webbroker3.triggerorder.util.WEB3ToSuccOrderManagerUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3ToSuccFuturesCancelOrderServiceImplTest extends TestBaseForMock
{
    /**
     * (OoÍ[eBeBB)<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3ToSuccFuturesCancelOrderServiceImplTest.class);

    WEB3ToSuccFuturesCancelOrderServiceImpl l_service = null;

    public WEB3ToSuccFuturesCancelOrderServiceImplTest(String name)
    {
        super(name);
    }

    protected void setUp() throws Exception
    {
        l_service = new WEB3ToSuccFuturesCancelOrderServiceImpl();
        TestDBUtility.deleteAll(SubAccountParams.TYPE);
        TestDBUtility.deleteAll(MainAccountParams.TYPE);
        TestDBUtility.deleteAll(RsvIfoOrderUnitParams.TYPE);
        TestDBUtility.deleteAll(BranchParams.TYPE);
        TestDBUtility.deleteAll(ProductParams.TYPE);
        TestDBUtility.deleteAll(IfoProductParams.TYPE);
        TestDBUtility.deleteAll(TradedProductParams.TYPE);
        TestDBUtility.deleteAll(IfoTradedProductParams.TYPE);
        TestDBUtility.deleteAll(MarketParams.TYPE);
        TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
        
        TestDBUtility.deleteAll(TradingTimeParams.TYPE);
        TestDBUtility.deleteAll(InstitutionParams.TYPE);
        TestDBUtility.deleteAll(BranchIndexDealtCondParams.TYPE);
        TestDBUtility.deleteAll(Web3QuoteProtoParams.TYPE);
        
        WEB3ToSuccOrderManagerUtility.changeOrderManager();
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        l_service = null;
        super.tearDown();
    }

    /**
     * iA±j¿wæ¨æÁ¶mFNGXgÌê this.validate¶
     */
    public void test_execute_0001()
    {
        final String STR_METHOD_NAME = "test_execute_0001()";
        log.entering(STR_METHOD_NAME);

        WEB3SuccFuturesCancelConfirmRequest l_request = new WEB3SuccFuturesCancelConfirmRequest();

        try
        {
            l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00080, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * iA±j¿wæ¨æÁ¶®¹NGXgÌê this.submit¶
     */
    public void test_execute_0002()
    {
        final String STR_METHOD_NAME = "test_execute_0002()";
        log.entering(STR_METHOD_NAME);

        WEB3SuccFuturesCancelConfirmRequest l_request = new WEB3SuccFuturesCancelConfirmRequest();

        try
        {
            l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00080, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * NGXg = null
     */
    public void test_execute_0003()
    {
        final String STR_METHOD_NAME = "test_execute_0003()";
        log.entering(STR_METHOD_NAME);

        WEB3SuccFuturesCancelConfirmRequest l_request = null;

        try
        {
            l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 
     */
    public void test_execute_0004()
    {
        final String STR_METHOD_NAME = "test_execute_0004()";
        log.entering(STR_METHOD_NAME);

        WEB3SuccFuturesCloseConfirmRequest l_request = new WEB3SuccFuturesCloseConfirmRequest();

        try
        {
            l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * iiA±jæ¨æÁT[rXjvalidate¶
     * AæªF20ªfBC
     */
    public void test_validateOrder_0001()
    {
        final String STR_METHOD_NAME = "test_validateOrder_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3SuccFuturesCancelConfirmRequest l_request = new WEB3SuccFuturesCancelConfirmRequest();
        l_request.id = "21";
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                    new Class[] {}, new Long("333812512246"));
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setCloseWorngSysFuture(1);
            TestDBUtility.insertWithDel(l_branchParams);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            BranchIndexDealtCondParams l_branchIndexDealtCondParams = TestDBUtility.getBranchIndexDealtCondRow();
            l_branchIndexDealtCondParams.setInstitutionCode("0D");
            l_branchIndexDealtCondParams.setBranchCode("381");
            l_branchIndexDealtCondParams.setFutureOptionDiv("1");
            l_branchIndexDealtCondParams.setEnableOrder("1");
            TestDBUtility.insertWithDel(l_branchIndexDealtCondParams);

            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = new RsvIfoOrderUnitParams();
            l_rsvIfoOrderUnitParams.setAccountId(11);
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
            l_rsvIfoOrderUnitParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_rsvIfoOrderUnitParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            
            //¶úÀæª
            l_rsvIfoOrderUnitParams.setExpirationDateType("2");
            //¶LøúÀ
            l_rsvIfoOrderUnitParams.setExpirationDate(WEB3DateUtility.getDate("20080707", "yyyyMMdd"));
            //§ïæª
            l_rsvIfoOrderUnitParams.setSessionType("0");
            //Ï
            l_rsvIfoOrderUnitParams.setClosingOrder("3");
            //TZónãà
            l_rsvIfoOrderUnitParams.setEstimatedPrice(65);
            //wl
            l_rsvIfoOrderUnitParams.setLimitPrice(56);
            //¶íÊ
            l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setProductId(3304148080000L);
            l_ifoOrderUnitParams.setOrderId(56);
            l_ifoOrderUnitParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);

            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(3304148080000L);
            //´YÁ¿R[h
            l_ifoProductParams.setUnderlyingProductCode("0019");
            //À
            l_ifoProductParams.setMonthOfDelivery("200808");
            TestDBUtility.insertWithDel(l_ifoProductParams);

            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setProductId(3304148080000L);
            l_tradedProductParams.setMarketId(3303L);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setTradedProductId(1006160060005L);
            l_ifoTradedProductParams.setMarketId(3303L);
            l_ifoTradedProductParams.setProductId(3304148080000L);
            l_ifoTradedProductParams.setValidForBizDate(null);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(WEB3DateUtility.getDate("20080808", "yyyyMMdd"));
            
            String[] l_strValues = {"0005"};
            WEB3GentradeTradingTimeManagementForMock.mockGetTradeCloseSuspension(l_strValues, "1");
            
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setProductCode("0005");
            
            TestDBUtility.insertWithDel(l_tradingTimeParams);
//            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
//            l_tradingTimeParams1.setInstitutionCode("0D");
//            l_tradingTimeParams1.setBranchCode("123");
//            l_tradingTimeParams1.setTradingTimeType("01");
//            l_tradingTimeParams1.setBizDateType("2");
//            l_tradingTimeParams1.setMarketCode("N1");
//            l_tradingTimeParams1.setProductCode("0");
//            
//            TestDBUtility.insertWithDel(l_tradingTimeParams1);
//            TradingTimeParams l_tradingTimeParams2 = TestDBUtility.getTradingTimeRow();
//            l_tradingTimeParams2.setInstitutionCode("0D");
//            l_tradingTimeParams2.setBranchCode("123");
//            l_tradingTimeParams2.setTradingTimeType("01");
//            l_tradingTimeParams2.setBizDateType("3");
//            l_tradingTimeParams2.setMarketCode("N1");
//            l_tradingTimeParams2.setProductCode("0");
//            
//            TestDBUtility.insertWithDel(l_tradingTimeParams2);
//            TradingTimeParams l_tradingTimeParams3 = TestDBUtility.getTradingTimeRow();
//            l_tradingTimeParams3.setInstitutionCode("0D");
//            l_tradingTimeParams3.setBranchCode("123");
//            l_tradingTimeParams3.setTradingTimeType("01");
//            l_tradingTimeParams3.setBizDateType("4");
//            l_tradingTimeParams3.setMarketCode("N1");
//            l_tradingTimeParams3.setProductCode("0");
//            
//            TestDBUtility.insertWithDel(l_tradingTimeParams3);
//            TradingTimeParams l_tradingTimeParams4 = TestDBUtility.getTradingTimeRow();
//            l_tradingTimeParams4.setInstitutionCode("0D");
//            l_tradingTimeParams4.setBranchCode("123");
//            l_tradingTimeParams4.setTradingTimeType("01");
//            l_tradingTimeParams4.setBizDateType("5");
//            l_tradingTimeParams4.setMarketCode("N1");
//            l_tradingTimeParams4.setProductCode("0");
//            
//            TestDBUtility.insertWithDel(l_tradingTimeParams4);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            //sêR[hiSONARj
            l_marketParams.setSonarMarketCode("4");
            l_marketParams.setMarketId(3303L);
            
            TestDBUtility.insertWithDel(l_marketParams);
            
            Web3QuoteProtoParams l_web3QuoteProtoParams = new Web3QuoteProtoParams();
            l_web3QuoteProtoParams.setQuoteDataId(1);
            l_web3QuoteProtoParams.setQuoteDate(WEB3DateUtility.formatDate(Calendar.getInstance().getTime(), "yyyyMMdd"));
            l_web3QuoteProtoParams.setRealType(RealType.REAL);
            l_web3QuoteProtoParams.setDataType(DataType.INDEX_FUTURE);
            l_web3QuoteProtoParams.setMarketCode("SP");
            l_web3QuoteProtoParams.setProductCode("0005");
            l_web3QuoteProtoParams.setContractMonth("200503");
            l_web3QuoteProtoParams.setCurrentPriceFlag(CurrentPriceFlag.NORMAL);
            l_web3QuoteProtoParams.setAskPriceTitle(AskPriceTitle.UNDEFINED);
            l_web3QuoteProtoParams.setBidPriceTitle(BidPriceTitle.UNDEFINED);
            l_web3QuoteProtoParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_web3QuoteProtoParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            
            //»Ýl
            l_web3QuoteProtoParams.setCurrentPrice(76);
            //Oúä
            l_web3QuoteProtoParams.setChange(2);
            //æøÔ
            l_web3QuoteProtoParams.setCurrentPriceTime("2008");
            
            TestDBUtility.insertWithDel(l_web3QuoteProtoParams);
            
            Web3QuoteProtoParams l_web3QuoteProtoParams1 = new Web3QuoteProtoParams();
            l_web3QuoteProtoParams1.setQuoteDataId(1001);
            l_web3QuoteProtoParams1.setQuoteDate(WEB3DateUtility.formatDate(Calendar.getInstance().getTime(), "yyyyMMdd"));
            l_web3QuoteProtoParams1.setRealType(RealType.DELAY);
            l_web3QuoteProtoParams1.setDataType(DataType.INDEX_FUTURE);
            l_web3QuoteProtoParams1.setMarketCode("SP");
            l_web3QuoteProtoParams1.setProductCode("0005");
            l_web3QuoteProtoParams1.setContractMonth("200503");
            l_web3QuoteProtoParams1.setCurrentPriceFlag(CurrentPriceFlag.NORMAL);
            l_web3QuoteProtoParams1.setAskPriceTitle(AskPriceTitle.UNDEFINED);
            l_web3QuoteProtoParams1.setBidPriceTitle(BidPriceTitle.UNDEFINED);
            l_web3QuoteProtoParams1.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_web3QuoteProtoParams1.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            //»Ýl
            l_web3QuoteProtoParams1.setCurrentPrice(34);
            //Oúä
            l_web3QuoteProtoParams1.setChange(3);
            //æøÔ
            l_web3QuoteProtoParams1.setCurrentPriceTime("2002");
            TestDBUtility.insertWithDel(l_web3QuoteProtoParams1);
            
            
            
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateIfoCancelOrder",
                    new Class[]{WEB3GentradeSubAccount.class, WEB3ToSuccIfoOrderUnitImpl.class},
                    null);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl", "isReversingTrade", new Class[]
                    {String.class, OrderUnit.class},
                    Boolean.TRUE);
            
            WEB3IfoQuoteDataImplForMock l_quoteData = new WEB3IfoQuoteDataImplForMock();
            l_quoteData.setCurrentPrice(12.3D);
            l_quoteData.setChange(56.3D);
            l_quoteData.setCurrentPriceTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.quoteadaptor.prototype.WEB3ProtoQuoteDataSupplierService",
                    "getQuote",
                    new Class[]{ TradedProduct.class, RealType.class },
                    l_quoteData);

            WEB3SuccFuturesCancelConfirmResponse l_response = l_service.validateOrder(l_request);
            
            WEB3MockObjectParamsValue l_paramsValue =
                TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateIfoCancelOrder",
                    new Class[]{WEB3GentradeSubAccount.class, WEB3ToSuccIfoOrderUnitImpl.class});

           assertEquals("333812512246", "" + ((WEB3GentradeSubAccount)l_paramsValue.getCalled(0)[0]).getAccountId());
           assertEquals("23.0", "" + ((WEB3ToSuccIfoOrderUnitImpl)l_paramsValue.getCalled(0)[1]).getQuantity());
           
           WEB3MockObjectParamsValue l_paramsValue1 =
               TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                   "webbroker3.quoteadaptor.prototype.WEB3ProtoQuoteDataSupplierService",
                   "getQuote",
                   new Class[]{ TradedProduct.class, RealType.class });

          assertEquals("8.0", "" + ((WEB3IfoTradedProductImpl)l_paramsValue1.getCalled(0)[0]).getLastClosingPrice());
          assertEquals("2", "" + ((RealType)l_paramsValue1.getCalled(0)[1]).intValue());
           
           //æøæª
           assertEquals("3", l_response.tradingType);
           //æøsê
           assertEquals("4", l_response.marketCode);
           //wíÊ
           assertEquals("0019", l_response.targetProductCode);
           //À
           assertEquals("200808", l_response.delivaryMonth);
           //¶Ê
           assertEquals("23", l_response.futOrderQuantity);
           //àñèÊ
           assertNull(l_response.partExecQuantity);
           //¶P¿æª
           assertEquals("1", l_response.orderPriceDiv);
           //¶P¿
           assertEquals("56", l_response.limitPrice);
           //·sð
           assertEquals("1", l_response.execCondType);
           //¶úÀæª
           assertEquals("2", l_response.expirationDateType);
           //¶LøúÀ
           assertEquals("20080707", WEB3DateUtility.formatDate(l_response.expirationDate, "yyyyMMdd"));
           //­ðæª
           assertEquals("0", l_response.orderCondType);
           //twlp­ðP¿
           assertNull(l_response.stopOrderCondPrice);
           //twlp­ðZq
           assertNull(l_response.stopOrderCondOperator);
           //vwlp­ðP¿
           assertNull(l_response.wlimitOrderCondPrice);
           //vwlp­ðZq
           assertNull(l_response.wlimitOrderCondOperator);
           //vwlp¶P¿æª
           assertNull(l_response.wLimitOrderPriceDiv);
           //vwlp¶P¿
           assertNull(l_response.wLimitPrice);
           //vwlp·sð
           assertNull(l_response.wlimitExecCondType);
           //vwlpLøóÔæª
           assertNull(l_response.wlimitEnableStatusDiv);
           //vwlpØÖO¶P¿
           assertNull(l_response.wlimitBefChgLimitPrice);
           //vwlpØÖO·sð
           assertNull(l_response.wlimitBefChgExecCondType);
           //³­ðæª
           assertNull(l_response.orgOrderCondType);
           //³­ðP¿
           assertNull(l_response.orgOrderCondPrice);
           //³­ðZq
           assertNull(l_response.orgCondOperator);
           //³vwlp¶P¿æª
           assertNull(l_response.orgWLimitOrderPriceDiv);
           //³vwlp¶P¿
           assertNull(l_response.orgWLimitPrice);
           //³vwlp·sð
           assertNull(l_response.orgWlimitExecCondType);
           //TZãà(Ï¹v)
           assertEquals("65", "" + l_response.estimatedContractPrice);
           //æøI¹x¶¾
           assertEquals("0005", l_response.messageSuspension[0]);
           
           //mF­ú
           assertEquals("20080808", "" + WEB3DateUtility.formatDate(l_response.checkDate, "yyyyMMdd"));
           //Ï
           assertEquals("3", l_response.closingOrder);
           //Ê¾×
           assertEquals("100", "" + l_response.contractUnits[0].contractQuantity);
           //»Ýl
           assertEquals("12.3", "" + l_response.currentPrice);
           //Oúä
           assertEquals("56.3", "" + l_response.comparedPreviousDay);
           //æøÔ
           assertEquals("0", "" + WEB3DateUtility.compareToDay(l_response.currentPriceTime, Calendar.getInstance().getTime()));
           //§ïæª
           assertEquals("0", l_response.sessionType);
           //P¿²®lîñ
           assertNull(l_response.priceAdjustmentValueInfo);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            e.printStackTrace();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    /**
     * iiA±jæ¨æÁT[rXjvalidate¶
     * AæªFA
     */
    public void test_validateOrder_0002()
    {
        final String STR_METHOD_NAME = "test_validateOrder_0002()";
        log.entering(STR_METHOD_NAME);
        WEB3SuccFuturesCancelConfirmRequest l_request = new WEB3SuccFuturesCancelConfirmRequest();
        l_request.id = "21";
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                    new Class[] {}, new Long("333812512246"));
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setQuotoType("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setCloseWorngSysFuture(1);
            TestDBUtility.insertWithDel(l_branchParams);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            BranchIndexDealtCondParams l_branchIndexDealtCondParams = TestDBUtility.getBranchIndexDealtCondRow();
            l_branchIndexDealtCondParams.setInstitutionCode("0D");
            l_branchIndexDealtCondParams.setBranchCode("381");
            l_branchIndexDealtCondParams.setFutureOptionDiv("1");
            l_branchIndexDealtCondParams.setEnableOrder("1");
            TestDBUtility.insertWithDel(l_branchIndexDealtCondParams);

            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = new RsvIfoOrderUnitParams();
            l_rsvIfoOrderUnitParams.setAccountId(11);
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
            l_rsvIfoOrderUnitParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_rsvIfoOrderUnitParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            
            //¶úÀæª
            l_rsvIfoOrderUnitParams.setExpirationDateType("2");
            //¶LøúÀ
            l_rsvIfoOrderUnitParams.setExpirationDate(WEB3DateUtility.getDate("20080707", "yyyyMMdd"));
            //§ïæª
            l_rsvIfoOrderUnitParams.setSessionType("0");
            //Ï
            l_rsvIfoOrderUnitParams.setClosingOrder("3");
            //TZónãà
            l_rsvIfoOrderUnitParams.setEstimatedPrice(65);
            //wl
            l_rsvIfoOrderUnitParams.setLimitPrice(56);
            //¶íÊ
            l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setProductId(3304148080000L);
            l_ifoOrderUnitParams.setOrderId(56);
            l_ifoOrderUnitParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);

            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(3304148080000L);
            //´YÁ¿R[h
            l_ifoProductParams.setUnderlyingProductCode("0019");
            //À
            l_ifoProductParams.setMonthOfDelivery("200808");
            TestDBUtility.insertWithDel(l_ifoProductParams);

            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setProductId(3304148080000L);
            l_tradedProductParams.setMarketId(3303L);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setTradedProductId(1006160060005L);
            l_ifoTradedProductParams.setMarketId(3303L);
            l_ifoTradedProductParams.setProductId(3304148080000L);
            l_ifoTradedProductParams.setValidForBizDate(null);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(WEB3DateUtility.getDate("20080808", "yyyyMMdd"));
            
            String[] l_strValues = {"0005"};
            WEB3GentradeTradingTimeManagementForMock.mockGetTradeCloseSuspension(l_strValues, "1");
            
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setProductCode("0005");
            
            TestDBUtility.insertWithDel(l_tradingTimeParams);
//            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
//            l_tradingTimeParams1.setInstitutionCode("0D");
//            l_tradingTimeParams1.setBranchCode("123");
//            l_tradingTimeParams1.setTradingTimeType("01");
//            l_tradingTimeParams1.setBizDateType("2");
//            l_tradingTimeParams1.setMarketCode("N1");
//            l_tradingTimeParams1.setProductCode("0");
//            
//            TestDBUtility.insertWithDel(l_tradingTimeParams1);
//            TradingTimeParams l_tradingTimeParams2 = TestDBUtility.getTradingTimeRow();
//            l_tradingTimeParams2.setInstitutionCode("0D");
//            l_tradingTimeParams2.setBranchCode("123");
//            l_tradingTimeParams2.setTradingTimeType("01");
//            l_tradingTimeParams2.setBizDateType("3");
//            l_tradingTimeParams2.setMarketCode("N1");
//            l_tradingTimeParams2.setProductCode("0");
//            
//            TestDBUtility.insertWithDel(l_tradingTimeParams2);
//            TradingTimeParams l_tradingTimeParams3 = TestDBUtility.getTradingTimeRow();
//            l_tradingTimeParams3.setInstitutionCode("0D");
//            l_tradingTimeParams3.setBranchCode("123");
//            l_tradingTimeParams3.setTradingTimeType("01");
//            l_tradingTimeParams3.setBizDateType("4");
//            l_tradingTimeParams3.setMarketCode("N1");
//            l_tradingTimeParams3.setProductCode("0");
//            
//            TestDBUtility.insertWithDel(l_tradingTimeParams3);
//            TradingTimeParams l_tradingTimeParams4 = TestDBUtility.getTradingTimeRow();
//            l_tradingTimeParams4.setInstitutionCode("0D");
//            l_tradingTimeParams4.setBranchCode("123");
//            l_tradingTimeParams4.setTradingTimeType("01");
//            l_tradingTimeParams4.setBizDateType("5");
//            l_tradingTimeParams4.setMarketCode("N1");
//            l_tradingTimeParams4.setProductCode("0");
//            
//            TestDBUtility.insertWithDel(l_tradingTimeParams4);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            //sêR[hiSONARj
            l_marketParams.setSonarMarketCode("4");
            l_marketParams.setMarketId(3303L);
            
            TestDBUtility.insertWithDel(l_marketParams);
            
            Web3QuoteProtoParams l_web3QuoteProtoParams = new Web3QuoteProtoParams();
            l_web3QuoteProtoParams.setQuoteDataId(1);
            l_web3QuoteProtoParams.setQuoteDate(WEB3DateUtility.formatDate(Calendar.getInstance().getTime(), "yyyyMMdd"));
            l_web3QuoteProtoParams.setRealType(RealType.REAL);
            l_web3QuoteProtoParams.setDataType(DataType.INDEX_FUTURE);
            l_web3QuoteProtoParams.setMarketCode("SP");
            l_web3QuoteProtoParams.setProductCode("0005");
            l_web3QuoteProtoParams.setContractMonth("200503");
            l_web3QuoteProtoParams.setCurrentPriceFlag(CurrentPriceFlag.NORMAL);
            l_web3QuoteProtoParams.setAskPriceTitle(AskPriceTitle.UNDEFINED);
            l_web3QuoteProtoParams.setBidPriceTitle(BidPriceTitle.UNDEFINED);
            l_web3QuoteProtoParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_web3QuoteProtoParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            
            //»Ýl
            l_web3QuoteProtoParams.setCurrentPrice(76);
            //Oúä
            l_web3QuoteProtoParams.setChange(2);
            //æøÔ
            l_web3QuoteProtoParams.setCurrentPriceTime("2008");
            
            TestDBUtility.insertWithDel(l_web3QuoteProtoParams);
            
            Web3QuoteProtoParams l_web3QuoteProtoParams1 = new Web3QuoteProtoParams();
            l_web3QuoteProtoParams1.setQuoteDataId(1001);
            l_web3QuoteProtoParams1.setQuoteDate(WEB3DateUtility.formatDate(Calendar.getInstance().getTime(), "yyyyMMdd"));
            l_web3QuoteProtoParams1.setRealType(RealType.DELAY);
            l_web3QuoteProtoParams1.setDataType(DataType.INDEX_FUTURE);
            l_web3QuoteProtoParams1.setMarketCode("SP");
            l_web3QuoteProtoParams1.setProductCode("0005");
            l_web3QuoteProtoParams1.setContractMonth("200503");
            l_web3QuoteProtoParams1.setCurrentPriceFlag(CurrentPriceFlag.NORMAL);
            l_web3QuoteProtoParams1.setAskPriceTitle(AskPriceTitle.UNDEFINED);
            l_web3QuoteProtoParams1.setBidPriceTitle(BidPriceTitle.UNDEFINED);
            l_web3QuoteProtoParams1.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_web3QuoteProtoParams1.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            //»Ýl
            l_web3QuoteProtoParams1.setCurrentPrice(34);
            //Oúä
            l_web3QuoteProtoParams1.setChange(3);
            //æøÔ
            l_web3QuoteProtoParams1.setCurrentPriceTime("2002");
            TestDBUtility.insertWithDel(l_web3QuoteProtoParams1);
            
            
            
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateIfoCancelOrder",
                    new Class[]{WEB3GentradeSubAccount.class, WEB3ToSuccIfoOrderUnitImpl.class},
                    null);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl", "isReversingTrade", new Class[]
                    {String.class, OrderUnit.class},
                    Boolean.TRUE);
            
            WEB3IfoQuoteDataImplForMock l_quoteData = new WEB3IfoQuoteDataImplForMock();
            l_quoteData.setCurrentPrice(12.3D);
            l_quoteData.setChange(56.3D);
            l_quoteData.setCurrentPriceTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.quoteadaptor.prototype.WEB3ProtoQuoteDataSupplierService",
                    "getQuote",
                    new Class[]{ TradedProduct.class, RealType.class },
                    l_quoteData);

            WEB3SuccFuturesCancelConfirmResponse l_response = l_service.validateOrder(l_request);
            
            WEB3MockObjectParamsValue l_paramsValue =
                TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateIfoCancelOrder",
                    new Class[]{WEB3GentradeSubAccount.class, WEB3ToSuccIfoOrderUnitImpl.class});

           assertEquals("333812512246", "" + ((WEB3GentradeSubAccount)l_paramsValue.getCalled(0)[0]).getAccountId());
           assertEquals("23.0", "" + ((WEB3ToSuccIfoOrderUnitImpl)l_paramsValue.getCalled(0)[1]).getQuantity());
           
           WEB3MockObjectParamsValue l_paramsValue1 =
               TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                   "webbroker3.quoteadaptor.prototype.WEB3ProtoQuoteDataSupplierService",
                   "getQuote",
                   new Class[]{ TradedProduct.class, RealType.class });

          assertEquals("8.0", "" + ((WEB3IfoTradedProductImpl)l_paramsValue1.getCalled(0)[0]).getLastClosingPrice());
          assertEquals("1", "" + ((RealType)l_paramsValue1.getCalled(0)[1]).intValue());
           
           //æøæª
           assertEquals("3", l_response.tradingType);
           //æøsê
           assertEquals("4", l_response.marketCode);
           //wíÊ
           assertEquals("0019", l_response.targetProductCode);
           //À
           assertEquals("200808", l_response.delivaryMonth);
           //¶Ê
           assertEquals("23", l_response.futOrderQuantity);
           //àñèÊ
           assertNull(l_response.partExecQuantity);
           //¶P¿æª
           assertEquals("1", l_response.orderPriceDiv);
           //¶P¿
           assertEquals("56", l_response.limitPrice);
           //·sð
           assertEquals("1", l_response.execCondType);
           //¶úÀæª
           assertEquals("2", l_response.expirationDateType);
           //¶LøúÀ
           assertEquals("20080707", WEB3DateUtility.formatDate(l_response.expirationDate, "yyyyMMdd"));
           //­ðæª
           assertEquals("0", l_response.orderCondType);
           //twlp­ðP¿
           assertNull(l_response.stopOrderCondPrice);
           //twlp­ðZq
           assertNull(l_response.stopOrderCondOperator);
           //vwlp­ðP¿
           assertNull(l_response.wlimitOrderCondPrice);
           //vwlp­ðZq
           assertNull(l_response.wlimitOrderCondOperator);
           //vwlp¶P¿æª
           assertNull(l_response.wLimitOrderPriceDiv);
           //vwlp¶P¿
           assertNull(l_response.wLimitPrice);
           //vwlp·sð
           assertNull(l_response.wlimitExecCondType);
           //vwlpLøóÔæª
           assertNull(l_response.wlimitEnableStatusDiv);
           //vwlpØÖO¶P¿
           assertNull(l_response.wlimitBefChgLimitPrice);
           //vwlpØÖO·sð
           assertNull(l_response.wlimitBefChgExecCondType);
           //³­ðæª
           assertNull(l_response.orgOrderCondType);
           //³­ðP¿
           assertNull(l_response.orgOrderCondPrice);
           //³­ðZq
           assertNull(l_response.orgCondOperator);
           //³vwlp¶P¿æª
           assertNull(l_response.orgWLimitOrderPriceDiv);
           //³vwlp¶P¿
           assertNull(l_response.orgWLimitPrice);
           //³vwlp·sð
           assertNull(l_response.orgWlimitExecCondType);
           //TZãà(Ï¹v)
           assertEquals("65", "" + l_response.estimatedContractPrice);
           //æøI¹x¶¾
           assertEquals("0005", l_response.messageSuspension[0]);
           
           //mF­ú
           assertEquals("20080808", "" + WEB3DateUtility.formatDate(l_response.checkDate, "yyyyMMdd"));
           //Ï
           assertEquals("3", l_response.closingOrder);
           //Ê¾×
           assertEquals("100", "" + l_response.contractUnits[0].contractQuantity);
           //»Ýl
           assertEquals("12.3", "" + l_response.currentPrice);
           //Oúä
           assertEquals("56.3", "" + l_response.comparedPreviousDay);
           //æøÔ
           assertEquals("0", "" + WEB3DateUtility.compareToDay(l_response.currentPriceTime, Calendar.getInstance().getTime()));
           //§ïæª
           assertEquals("0", l_response.sessionType);
           //P¿²®lîñ
           assertNull(l_response.priceAdjustmentValueInfo);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    /**
     * iiA±jæ¨æÁT[rXjvalidate¶
     * validate
     */
    public void test_validateOrder_0003()
    {
        final String STR_METHOD_NAME = "test_validateOrder_0003()";
        log.entering(STR_METHOD_NAME);
        WEB3SuccFuturesCancelConfirmRequest l_request = new WEB3SuccFuturesCancelConfirmRequest();
        try
        {
            l_service.validateOrder(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00080, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * submit¶
     * validate()
     */
    public void test_submitOrder_0001()
    {
        final String STR_METHOD_NAME = "test_submitOrder_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3SuccFuturesCancelCompleteRequest l_request = new WEB3SuccFuturesCancelCompleteRequest();
        try
        {
            l_service.submitOrder(l_request);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00080, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * submit¶
     * 
     */
    public void test_submitOrder_0002()
    {
        final String STR_METHOD_NAME = "test_submitOrder_0002()";
        log.entering(STR_METHOD_NAME);
        WEB3SuccFuturesCancelCompleteRequest l_request = new WEB3SuccFuturesCancelCompleteRequest();
        l_request.id = "21";
        l_request.checkDate = WEB3DateUtility.getDate("20080808", "yyyyMMdd");
        l_request.password = "123456";
        
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(WEB3DateUtility.getDate("20080808", "yyyyMMdd"));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                    new Class[] {}, new Long("333812512246"));
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setQuotoType("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setCloseWorngSysFuture(1);
            TestDBUtility.insertWithDel(l_branchParams);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = new RsvIfoOrderUnitParams();
            l_rsvIfoOrderUnitParams.setAccountId(11);
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
            l_rsvIfoOrderUnitParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_rsvIfoOrderUnitParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            
            //¶úÀæª
            l_rsvIfoOrderUnitParams.setExpirationDateType("2");
            //¶LøúÀ
            l_rsvIfoOrderUnitParams.setExpirationDate(WEB3DateUtility.getDate("20080707", "yyyyMMdd"));
            //§ïæª
            l_rsvIfoOrderUnitParams.setSessionType("0");
            //Ï
            l_rsvIfoOrderUnitParams.setClosingOrder("3");
            //TZónãà
            l_rsvIfoOrderUnitParams.setEstimatedPrice(65);
            //wl
            l_rsvIfoOrderUnitParams.setLimitPrice(56);
            //¶íÊ
            l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateIfoCancelOrder",
                    new Class[]{WEB3GentradeSubAccount.class, WEB3ToSuccIfoOrderUnitImpl.class},
                    null);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "submitIfoCancelOrder", new Class[]
                    {SubAccount.class, WEB3ToSuccIfoOrderUnitImpl.class, String.class},
                    null);
            
            WEB3SuccFuturesCancelCompleteResponse l_response = l_service.submitOrder(l_request);
            
            WEB3MockObjectParamsValue l_paramsValue =
                TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateIfoCancelOrder",
                    new Class[]{WEB3GentradeSubAccount.class, WEB3ToSuccIfoOrderUnitImpl.class});

           assertEquals("333812512246", "" + ((WEB3GentradeSubAccount)l_paramsValue.getCalled(0)[0]).getAccountId());
           assertEquals("23.0", "" + ((WEB3ToSuccIfoOrderUnitImpl)l_paramsValue.getCalled(0)[1]).getQuantity());
           
           WEB3MockObjectParamsValue l_paramsValue1 =
               TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                   "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                   "submitIfoCancelOrder",
                   new Class[]{SubAccount.class, WEB3ToSuccIfoOrderUnitImpl.class, String.class});

          assertEquals("333812512246", "" + ((WEB3GentradeSubAccount)l_paramsValue1.getCalled(0)[0]).getAccountId());
          assertEquals("23.0", "" + ((WEB3ToSuccIfoOrderUnitImpl)l_paramsValue1.getCalled(0)[1]).getQuantity());
          assertEquals("123456", "" + ((String)l_paramsValue1.getCalled(0)[2]));
          
          assertEquals("0", "" + WEB3DateUtility.compareToDay(l_response.lastUpdatedTimestamp, Calendar.getInstance().getTime()));
          assertEquals("21", l_response.orderActionId);
          assertFalse(l_response.succSettingFlag);
          
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            e.printStackTrace();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
