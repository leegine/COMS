// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeOrderSubmitter.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls;

import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.*;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages.*;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.*;
import java.util.Date;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls:
//            EquityProductTypeOrderManagerReusableValidations, Utils, EquityProductTypeOrderSubmitterPersistenceManager

public abstract class EqTypeOrderSubmitter extends BaseOrderManagerImpl
    implements EqTypeOrderManager
{

    protected EqTypeOrderSubmitter()
    {
        super(new DefaultOrderValidatorImpl());
    }

    public EqTypeNewOrderValidationResult validateNewCashBasedOrder(SubAccount subAccount, EqTypeNewCashBasedOrderSpec spec)
    {
        long newOrderId;
        EquityProductTypeOrderManagerReusableValidations.getInstance().commonFirstValidationsForAllOperations(subAccount);
        com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct product = EquityProductTypeOrderManagerReusableValidations.getInstance().validateProductCode(subAccount.getInstitution(), spec.getProductCode());
        com.fitechlabs.xtrade.plugin.tc.gentrade.Market market = EquityProductTypeOrderManagerReusableValidations.getInstance().validateMarket(subAccount.getInstitution(), spec.getMarketCode());
        EqTypeTradedProduct tradedProduct = (EqTypeTradedProduct)EquityProductTypeOrderManagerReusableValidations.getInstance().validateTradedProduct(product, market);
        EquityProductTypeOrderManagerReusableValidations.getInstance().runOrderValidatorChecks(subAccount, spec.isBuyOrder() ? OrderTypeEnum.EQUITY_BUY : OrderTypeEnum.EQUITY_SELL, tradedProduct);
        EquityProductTypeOrderManagerReusableValidations.getInstance().validatePrice(tradedProduct, spec.getLimitPrice(), spec.isMarketOrder());
        EquityProductTypeOrderManagerReusableValidations.getInstance().validateQuantity(tradedProduct.getProduct(), spec.getQuantity());
        if(spec.isSellOrder())
            EquityProductTypeOrderManagerReusableValidations.getInstance().validateSellableAssetQuantity(subAccount, tradedProduct, spec.getQuantity());
        EquityProductTypeOrderManagerReusableValidations.getInstance().validatePerNewCashBasedOrderLimits(tradedProduct, spec.getQuantity(), spec.getLimitPrice(), spec.isMarketOrder(), spec.isBuyOrder());
        EquityProductTypeOrderManagerReusableValidations.getInstance().validatePerOrderMaxLots(tradedProduct, spec.getQuantity());
        EquityProductTypeOrderManagerReusableValidations.getInstance().validateExpirationDate(tradedProduct, spec.getOrderExpDate());
        EquityProductTypeOrderManagerReusableValidations.getInstance().validateExecutionConditionType(tradedProduct, spec.getExecConditionType());
        newOrderId = OrderSubmitContextIndicator.isValidateCalledFromSubmit() ? -1L : Utils.getGlobalOrderManager().createNewOrderId();
        if(DBG)
            m_log.debug("Order validation done. new order Id : " + newOrderId);
        return new EqTypeNewOrderValidationResult(ProcessingResult.SUCCESS_RESULT, newOrderId);
        OrderValidationException ve;
        ve;
        return new EqTypeNewOrderValidationResult(ve.getValidationResult().getProcessingResult());
    }

    public EqTypeNewOrderValidationResult validateNewMiniStockOrder(SubAccount subAccount, EqTypeNewMiniStockOrderSpec spec)
    {
        long newOrderId;
        EquityProductTypeOrderManagerReusableValidations.getInstance().commonFirstValidationsForAllOperations(subAccount);
        com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct product = EquityProductTypeOrderManagerReusableValidations.getInstance().validateProductCode(subAccount.getInstitution(), spec.getProductCode());
        com.fitechlabs.xtrade.plugin.tc.gentrade.Market market = EquityProductTypeOrderManagerReusableValidations.getInstance().validateMarket(subAccount.getInstitution(), spec.getMarketCode());
        EqTypeTradedProduct tradedProduct = (EqTypeTradedProduct)EquityProductTypeOrderManagerReusableValidations.getInstance().validateTradedProduct(product, market);
        EquityProductTypeOrderManagerReusableValidations.getInstance().validateMiniStockProduct(tradedProduct);
        EquityProductTypeOrderManagerReusableValidations.getInstance().runOrderValidatorChecks(subAccount, spec.isBuyOrder() ? OrderTypeEnum.MINI_STOCK_BUY : OrderTypeEnum.MINI_STOCK_SELL, tradedProduct);
        EquityProductTypeOrderManagerReusableValidations.getInstance().validateMiniStockQuantity(tradedProduct, spec.getQuantity());
        if(spec.isSellOrder())
            EquityProductTypeOrderManagerReusableValidations.getInstance().validateSellableAssetQuantity(subAccount, tradedProduct, spec.getQuantity());
        newOrderId = OrderSubmitContextIndicator.isValidateCalledFromSubmit() ? -1L : Utils.getGlobalOrderManager().createNewOrderId();
        if(DBG)
            m_log.debug("Order validation done. new order Id : " + newOrderId);
        return new EqTypeNewOrderValidationResult(ProcessingResult.SUCCESS_RESULT, newOrderId);
        OrderValidationException ve;
        ve;
        return new EqTypeNewOrderValidationResult(ve.getValidationResult().getProcessingResult());
    }

    public EqTypeNewOrderValidationResult validateOpenContractOrder(SubAccount subAccount, EqTypeOpenContractOrderSpec spec)
    {
        long newOrderId;
        EquityProductTypeOrderManagerReusableValidations.getInstance().commonFirstValidationsForAllOperations(subAccount);
        com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct product = EquityProductTypeOrderManagerReusableValidations.getInstance().validateProductCode(subAccount.getInstitution(), spec.getProductCode());
        com.fitechlabs.xtrade.plugin.tc.gentrade.Market market = EquityProductTypeOrderManagerReusableValidations.getInstance().validateMarket(subAccount.getInstitution(), spec.getMarketCode());
        EqTypeTradedProduct tradedProduct = EquityProductTypeOrderManagerReusableValidations.getInstance().validateTradedProductForMarginTrading(product, market, spec.isLongOrder());
        EquityProductTypeOrderManagerReusableValidations.getInstance().runOrderValidatorChecks(subAccount, spec.isLongOrder() ? OrderTypeEnum.MARGIN_LONG : OrderTypeEnum.MARGIN_SHORT, tradedProduct);
        EquityProductTypeOrderManagerReusableValidations.getInstance().validatePrice(tradedProduct, spec.getLimitPrice(), spec.isMarketOrder());
        EquityProductTypeOrderManagerReusableValidations.getInstance().validateQuantity(tradedProduct.getProduct(), spec.getQuantity());
        EquityProductTypeOrderManagerReusableValidations.getInstance().validatePerOpenMarginContractOrderLimits(tradedProduct, spec.getQuantity(), spec.getLimitPrice(), spec.isMarketOrder(), spec.isLongOrder());
        EquityProductTypeOrderManagerReusableValidations.getInstance().validatePerOrderMaxLots(tradedProduct, spec.getQuantity());
        EquityProductTypeOrderManagerReusableValidations.getInstance().validateExpirationDate(tradedProduct, spec.getOrderExpDate());
        EquityProductTypeOrderManagerReusableValidations.getInstance().validateExecutionConditionType(tradedProduct, spec.getExecConditionType());
        newOrderId = OrderSubmitContextIndicator.isValidateCalledFromSubmit() ? -1L : Utils.getGlobalOrderManager().createNewOrderId();
        if(DBG)
            m_log.debug("Order validation done. new order Id : " + newOrderId);
        return new EqTypeNewOrderValidationResult(ProcessingResult.SUCCESS_RESULT, newOrderId);
        OrderValidationException ve;
        ve;
        return new EqTypeNewOrderValidationResult(ve.getValidationResult().getProcessingResult());
    }

    public EqTypeNewOrderValidationResult validateSettleContractOrder(SubAccount subAccount, EqTypeSettleContractOrderSpec spec)
    {
        long newOrderId;
        EquityProductTypeOrderManagerReusableValidations.getInstance().commonFirstValidationsForAllOperations(subAccount);
        Contract firstContract = (EqTypeContract)EquityProductTypeOrderManagerReusableValidations.getInstance().validateSettleContractEntries(spec.getSettleContractOrderEntries());
        EqTypeTradedProduct tproduct = (EqTypeTradedProduct)firstContract.getTradedProduct();
        EquityProductTypeOrderManagerReusableValidations.getInstance().runOrderValidatorChecks(subAccount, firstContract.isLong() ? OrderTypeEnum.CLOSE_MARGIN_LONG : OrderTypeEnum.CLOSE_MARGIN_SHORT, tproduct);
        EquityProductTypeOrderManagerReusableValidations.getInstance().validatePrice(tproduct, spec.getLimitPrice(), spec.isMarketOrder());
        EquityProductTypeOrderManagerReusableValidations.getInstance().validateQuantity(tproduct.getProduct(), spec.getTotalQuantity());
        EquityProductTypeOrderManagerReusableValidations.getInstance().validatePerOrderMaxLots(tproduct, spec.getTotalQuantity());
        EquityProductTypeOrderManagerReusableValidations.getInstance().validateExpirationDate(tproduct, spec.getOrderExpDate());
        EquityProductTypeOrderManagerReusableValidations.getInstance().validateExecutionConditionType(tproduct, spec.getExecConditionType());
        newOrderId = OrderSubmitContextIndicator.isValidateCalledFromSubmit() ? -1L : Utils.getGlobalOrderManager().createNewOrderId();
        if(DBG)
            m_log.debug("Order validation done. new order Id : " + newOrderId);
        return new EqTypeNewOrderValidationResult(ProcessingResult.SUCCESS_RESULT, newOrderId);
        OrderValidationException ve;
        ve;
        return new EqTypeNewOrderValidationResult(ve.getValidationResult().getProcessingResult());
    }

    public EqTypeNewOrderValidationResult validateSwapContractOrder(SubAccount subAccount, EqTypeSwapContractOrderSpec spec)
    {
        long newOrderId;
        EquityProductTypeOrderManagerReusableValidations.getInstance().commonFirstValidationsForAllOperations(subAccount);
        Contract firstContract = EquityProductTypeOrderManagerReusableValidations.getInstance().validateSettleContractEntries(spec.getSettleContractOrderEntries());
        EqTypeTradedProduct tproduct = (EqTypeTradedProduct)firstContract.getTradedProduct();
        if(firstContract.isShort())
            EquityProductTypeOrderManagerReusableValidations.getInstance().validateSwappableAssetQuantity(subAccount, tproduct, spec.getTotalQuantity());
        EquityProductTypeOrderManagerReusableValidations.getInstance().runOrderValidatorChecks(subAccount, firstContract.isLong() ? OrderTypeEnum.SWAP_MARGIN_LONG : OrderTypeEnum.SWAP_MARGIN_SHORT, tproduct);
        EquityProductTypeOrderManagerReusableValidations.getInstance().validateQuantity(tproduct.getProduct(), spec.getTotalQuantity());
        newOrderId = OrderSubmitContextIndicator.isValidateCalledFromSubmit() ? -1L : Utils.getGlobalOrderManager().createNewOrderId();
        if(DBG)
            m_log.debug("Order validation done. new order Id : " + newOrderId);
        return new EqTypeNewOrderValidationResult(ProcessingResult.SUCCESS_RESULT, newOrderId);
        OrderValidationException ve;
        ve;
        return new EqTypeNewOrderValidationResult(ve.getValidationResult().getProcessingResult());
    }

    public EqTypeOrderSubmissionResult submitNewCashBasedOrder(SubAccount subAccount, EqTypeNewCashBasedOrderSpec spec, long orderId, String tradingPassword, boolean skipOrderValidation)
    {
        EqtypeOrderUnitParams orderUnitParams;
        OrderSubmitContextIndicator.setSubmitContext();
        performCommonSubmitStepPreValdations(true, spec.getTrader(), subAccount, tradingPassword, orderId);
        if(skipOrderValidation)
            break MISSING_BLOCK_LABEL_67;
        NewOrderValidationResult valResult = Utils.getGlobalOrderManager().validateNewOrder(subAccount, ProductTypeEnum.EQUITY, spec);
        if(!valResult.getProcessingResult().isFailedResult())
            break MISSING_BLOCK_LABEL_67;
        orderUnitParams = new EqTypeOrderSubmissionResult(valResult.getProcessingResult());
        OrderSubmitContextIndicator.clearSubmitContext();
        return orderUnitParams;
        EqTypeOrderSubmissionResult eqtypeordersubmissionresult;
        EqTypeTradedProduct tradedProduct = Utils.getProductManager().getTradedProduct(subAccount.getInstitution(), spec.getProductCode(), spec.getMarketCode());
        if(DBG)
            m_log.debug("Persisting order. order id : " + orderId);
        orderUnitParams = EquityProductTypeOrderSubmitterPersistenceManager.getInstance().persistNewCashBasedOrder(subAccount, orderId, spec, tradedProduct, false);
        if(!skipOrderValidation)
            EquityProductTypeOrderManagerReusableValidations.getInstance().validateSubAccountForTradingPower(subAccount, orderId);
        long orderUnitId = orderUnitParams.getOrderUnitId();
        MarketRequestSendResult sendResult = sendNewCashBasedOrderToMarket(subAccount, orderId, orderUnitId, spec.getQuantity(), spec.getLimitPrice(), tradedProduct, spec.isBuyOrder(), spec.getExecConditionType(), spec.getOrderExpDate(), orderUnitParams);
        if(!sendResult.getProcessingResult().isFailedResult())
            break MISSING_BLOCK_LABEL_270;
        if(DBG)
            m_log.debug("error response from market-gwy:" + sendResult.getProcessingResult().toString());
        eqtypeordersubmissionresult = new EqTypeOrderSubmissionResult(ProcessingResult.newFailedResultInstance(sendResult.getProcessingResult().getErrorInfo()));
        OrderSubmitContextIndicator.clearSubmitContext();
        return eqtypeordersubmissionresult;
        eqtypeordersubmissionresult = EqTypeOrderSubmissionResult.OK_RESULT;
        OrderSubmitContextIndicator.clearSubmitContext();
        return eqtypeordersubmissionresult;
        OrderValidationException ove;
        ove;
        orderUnitParams = new EqTypeOrderSubmissionResult(ove.getValidationResult().getProcessingResult());
        OrderSubmitContextIndicator.clearSubmitContext();
        return orderUnitParams;
        NotFoundException nfe;
        nfe;
        String msg = "Could not find market/product/traded product.";
        m_log.error(nfe.getMessage(), nfe);
        throw new RuntimeSystemException("Could not find market/product/traded product.", nfe);
        Exception exception;
        exception;
        OrderSubmitContextIndicator.clearSubmitContext();
        throw exception;
    }

    public EqTypeOrderSubmissionResult submitNewMiniStockOrder(SubAccount subAccount, EqTypeNewMiniStockOrderSpec spec, long orderId, String tradingPassword, boolean skipOrderValidation)
    {
        EqTypeNewCashBasedOrderSpec cashBasedOrderSpec;
        OrderSubmitContextIndicator.setSubmitContext();
        performCommonSubmitStepPreValdations(true, spec.getTrader(), subAccount, tradingPassword, orderId);
        if(skipOrderValidation)
            break MISSING_BLOCK_LABEL_67;
        NewOrderValidationResult valResult = Utils.getGlobalOrderManager().validateNewOrder(subAccount, ProductTypeEnum.EQUITY, spec);
        if(!valResult.getProcessingResult().isFailedResult())
            break MISSING_BLOCK_LABEL_67;
        cashBasedOrderSpec = new EqTypeOrderSubmissionResult(valResult.getProcessingResult());
        OrderSubmitContextIndicator.clearSubmitContext();
        return cashBasedOrderSpec;
        EqTypeOrderSubmissionResult eqtypeordersubmissionresult;
        EqTypeTradedProduct tradedProduct = Utils.getProductManager().getTradedProduct(subAccount.getInstitution(), spec.getProductCode(), spec.getMarketCode());
        if(DBG)
            m_log.debug("Persisting order. order id : " + orderId);
        cashBasedOrderSpec = EqTypeNewCashBasedOrderSpec.createMarketOrderSpec(spec.getTrader(), spec.isBuyOrder(), spec.getProductCode(), spec.getMarketCode(), spec.getQuantity(), spec.getExecConditionType(), spec.getOrderExpDate(), spec.getTaxType());
        EqtypeOrderUnitParams orderUnitParams = EquityProductTypeOrderSubmitterPersistenceManager.getInstance().persistNewCashBasedOrder(subAccount, orderId, cashBasedOrderSpec, tradedProduct, true);
        if(!skipOrderValidation)
            EquityProductTypeOrderManagerReusableValidations.getInstance().validateSubAccountForTradingPower(subAccount, orderId);
        long orderUnitId = orderUnitParams.getOrderUnitId();
        MarketRequestSendResult sendResult = sendNewCashBasedOrderToMarket(subAccount, orderId, orderUnitId, spec.getQuantity(), 0.0D, tradedProduct, spec.isBuyOrder(), null, spec.getOrderExpDate(), orderUnitParams);
        if(!sendResult.getProcessingResult().isFailedResult())
            break MISSING_BLOCK_LABEL_302;
        if(DBG)
            m_log.debug("error response from market-gwy:" + sendResult.getProcessingResult().toString());
        eqtypeordersubmissionresult = new EqTypeOrderSubmissionResult(ProcessingResult.newFailedResultInstance(sendResult.getProcessingResult().getErrorInfo()));
        OrderSubmitContextIndicator.clearSubmitContext();
        return eqtypeordersubmissionresult;
        eqtypeordersubmissionresult = EqTypeOrderSubmissionResult.OK_RESULT;
        OrderSubmitContextIndicator.clearSubmitContext();
        return eqtypeordersubmissionresult;
        OrderValidationException ove;
        ove;
        cashBasedOrderSpec = new EqTypeOrderSubmissionResult(ove.getValidationResult().getProcessingResult());
        OrderSubmitContextIndicator.clearSubmitContext();
        return cashBasedOrderSpec;
        NotFoundException nfe;
        nfe;
        String msg = "Could not find market/product/traded product.";
        m_log.error(nfe.getMessage(), nfe);
        throw new RuntimeSystemException("Could not find market/product/traded product.", nfe);
        Exception exception;
        exception;
        OrderSubmitContextIndicator.clearSubmitContext();
        throw exception;
    }

    private static MarketRequestSendResult sendNewCashBasedOrderToMarket(SubAccount subAccount, long orderId, long orderUnitId, double quantity, double limitPrice, EqTypeTradedProduct tradedProduct, boolean isBuyOrder, EqTypeExecutionConditionType execType, Date orderExpDate, EqtypeOrderUnitParams orderUnitRow)
    {
        if(DBG)
            m_log.debug("sending to market adapter.");
        EqTypeMarketRequestSenderService marketSenderSvc = Utils.getMarketSenderService();
        com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow dataSourceObj = orderUnitRow;
        com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages.EqTypeMarketRequestCashBasedOrderUnitSpec unitSpec = new DefaultEqTypeMarketRequestCashBasedOrderUnitSpec(orderUnitId, quantity, limitPrice, tradedProduct, isBuyOrder, execType, orderExpDate, dataSourceObj);
        com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages.EqTypeNewCashBasedOrderMarketRequestMessage mktReqMsg = new DefaultEqTypeNewCashBasedOrderMarketRequestMessage(subAccount, orderId, unitSpec);
        MarketRequestSendResult sendResult = marketSenderSvc.send(mktReqMsg);
        return sendResult;
    }

    private static MarketRequestSendResult sendOpenMarginContractOrderToMarket(SubAccount subAccount, long orderId, long orderUnitId, double quantity, double limitPrice, EqTypeTradedProduct tradedProduct, boolean isLongOrder, EqTypeExecutionConditionType execType, Date orderExpDate, EqtypeOrderUnitParams orderUnitRow)
    {
        if(DBG)
            m_log.debug("sending to market adapter.");
        EqTypeMarketRequestSenderService marketSenderSvc = Utils.getMarketSenderService();
        com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow dataSourceObj = orderUnitRow;
        com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages.EqTypeMarketRequestMarginOpenOrderUnitSpec unitSpec = new DefaultEqTypeMarketRequestMarginOpenOrderUnitSpec(orderUnitId, quantity, limitPrice, tradedProduct, isLongOrder, execType, orderExpDate, dataSourceObj);
        com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages.EqTypeOpenContractOrderMarketRequestMessage mktReqMsg = new DefaultEqTypeOpenContractOrderMarketRequestMessage(subAccount, orderId, unitSpec);
        MarketRequestSendResult sendResult = marketSenderSvc.send(mktReqMsg);
        return sendResult;
    }

    private static MarketRequestSendResult sendSettleMarginContractOrderToMarket(SubAccount subAccount, long orderId, long orderUnitId, double quantity, double limitPrice, EqTypeTradedProduct tradedProduct, boolean isSettlingLong, EqTypeExecutionConditionType execType, Date orderExpDate, EqtypeOrderUnitParams orderUnitRow)
    {
        if(DBG)
            m_log.debug("sending to market adapter.");
        EqTypeMarketRequestSenderService marketSenderSvc = Utils.getMarketSenderService();
        com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow dataSourceObj = orderUnitRow;
        com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages.EqTypeMarketRequestMarginSettleOrderUnitSpec unitSpec = new DefaultEqTypeMarketRequestMarginSettleOrderUnitSpec(orderUnitId, quantity, limitPrice, tradedProduct, isSettlingLong, execType, orderExpDate, dataSourceObj);
        com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages.EqTypeSettleContractOrderMarketRequestMessage mktReqMsg = new DefaultEqTypeSettleContractOrderMarketRequestMessage(subAccount, orderId, unitSpec);
        MarketRequestSendResult sendResult = marketSenderSvc.send(mktReqMsg);
        return sendResult;
    }

    private static MarketRequestSendResult sendSwapMarginContractOrderToMarket(SubAccount subAccount, long orderId, long orderUnitId, double quantity, EqTypeTradedProduct tradedProduct, 
            boolean isSwappingLong, EqtypeOrderUnitParams orderUnitRow)
    {
        if(DBG)
            m_log.debug("sending to market adapter.");
        EqTypeMarketRequestSenderService marketSenderSvc = Utils.getMarketSenderService();
        com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow dataSourceObj = orderUnitRow;
        com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages.EqTypeMarketRequestMarginSwapOrderUnitSpec unitSpec = new DefaultEqTypeMarketRequestMarginSwapOrderUnitSpec(orderUnitId, quantity, tradedProduct, isSwappingLong, dataSourceObj);
        com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages.EqTypeSwapContractOrderMarketRequestMessage mktReqMsg = new DefaultEqTypeSwapContractOrderMarketRequestMessage(subAccount, orderId, unitSpec);
        MarketRequestSendResult sendResult = marketSenderSvc.send(mktReqMsg);
        return sendResult;
    }

    public EqTypeOrderSubmissionResult submitOpenContractOrder(SubAccount subAccount, EqTypeOpenContractOrderSpec spec, long orderId, String tradingPassword, boolean skipOrderValidation)
    {
        EqtypeOrderUnitParams orderUnitParams;
        OrderSubmitContextIndicator.setSubmitContext();
        performCommonSubmitStepPreValdations(true, spec.getTrader(), subAccount, tradingPassword, orderId);
        if(skipOrderValidation)
            break MISSING_BLOCK_LABEL_67;
        NewOrderValidationResult valResult = Utils.getGlobalOrderManager().validateNewOrder(subAccount, ProductTypeEnum.EQUITY, spec);
        if(!valResult.getProcessingResult().isFailedResult())
            break MISSING_BLOCK_LABEL_67;
        orderUnitParams = new EqTypeOrderSubmissionResult(valResult.getProcessingResult());
        OrderSubmitContextIndicator.clearSubmitContext();
        return orderUnitParams;
        EqTypeOrderSubmissionResult eqtypeordersubmissionresult;
        EqTypeTradedProduct tradedProduct = Utils.getProductManager().getTradedProduct(subAccount.getInstitution(), spec.getProductCode(), spec.getMarketCode());
        if(DBG)
            m_log.debug("persisting order. order id : " + orderId);
        orderUnitParams = EquityProductTypeOrderSubmitterPersistenceManager.getInstance().persistOpenContractOrder(subAccount, orderId, spec, tradedProduct);
        if(!skipOrderValidation)
            EquityProductTypeOrderManagerReusableValidations.getInstance().validateSubAccountForTradingPower(subAccount, orderId);
        long orderUnitId = orderUnitParams.getOrderUnitId();
        MarketRequestSendResult sendResult = sendOpenMarginContractOrderToMarket(subAccount, orderId, orderUnitId, spec.getQuantity(), spec.getLimitPrice(), tradedProduct, spec.isLongOrder(), spec.getExecConditionType(), spec.getOrderExpDate(), orderUnitParams);
        if(!sendResult.getProcessingResult().isFailedResult())
            break MISSING_BLOCK_LABEL_269;
        if(DBG)
            m_log.debug("error response from market-gwy:" + sendResult.getProcessingResult().toString());
        eqtypeordersubmissionresult = new EqTypeOrderSubmissionResult(ProcessingResult.newFailedResultInstance(sendResult.getProcessingResult().getErrorInfo()));
        OrderSubmitContextIndicator.clearSubmitContext();
        return eqtypeordersubmissionresult;
        eqtypeordersubmissionresult = EqTypeOrderSubmissionResult.OK_RESULT;
        OrderSubmitContextIndicator.clearSubmitContext();
        return eqtypeordersubmissionresult;
        OrderValidationException ove;
        ove;
        orderUnitParams = new EqTypeOrderSubmissionResult(ove.getValidationResult().getProcessingResult());
        OrderSubmitContextIndicator.clearSubmitContext();
        return orderUnitParams;
        NotFoundException nfe;
        nfe;
        String msg = "Could not find market/product/traded product.";
        m_log.error(nfe.getMessage(), nfe);
        throw new RuntimeSystemException("Could not find market/product/traded product.", nfe);
        Exception exception;
        exception;
        OrderSubmitContextIndicator.clearSubmitContext();
        throw exception;
    }

    public EqTypeOrderSubmissionResult submitSettleContractOrder(SubAccount subAccount, EqTypeSettleContractOrderSpec spec, long orderId, String tradingPassword, boolean skipOrderValidation)
    {
        EqTypeTradedProduct tradedProduct;
        OrderSubmitContextIndicator.setSubmitContext();
        performCommonSubmitStepPreValdations(true, spec.getTrader(), subAccount, tradingPassword, orderId);
        if(skipOrderValidation)
            break MISSING_BLOCK_LABEL_67;
        NewOrderValidationResult valResult = Utils.getGlobalOrderManager().validateNewOrder(subAccount, ProductTypeEnum.EQUITY, spec);
        if(!valResult.getProcessingResult().isFailedResult())
            break MISSING_BLOCK_LABEL_67;
        tradedProduct = new EqTypeOrderSubmissionResult(valResult.getProcessingResult());
        OrderSubmitContextIndicator.clearSubmitContext();
        return tradedProduct;
        EqTypeOrderSubmissionResult eqtypeordersubmissionresult;
        Contract firstContract = Utils.getPositionManager().getContract(spec.getSettleContractOrderEntries()[0].getContractId());
        tradedProduct = (EqTypeTradedProduct)Utils.getProductManager().getTradedProduct(firstContract.getProduct().getProductId(), firstContract.getMarketId());
        if(DBG)
            m_log.debug("persisting order. order id : " + orderId);
        EqtypeOrderUnitParams orderUnitParams = EquityProductTypeOrderSubmitterPersistenceManager.getInstance().persistSettleContractOrder(subAccount, orderId, spec, tradedProduct);
        if(!skipOrderValidation)
            EquityProductTypeOrderManagerReusableValidations.getInstance().validateSubAccountForTradingPower(subAccount, orderId);
        long orderUnitId = orderUnitParams.getOrderUnitId();
        MarketRequestSendResult sendResult = sendSettleMarginContractOrderToMarket(subAccount, orderId, orderUnitId, spec.getTotalQuantity(), spec.getLimitPrice(), tradedProduct, firstContract.isLong(), spec.getExecConditionType(), spec.getOrderExpDate(), orderUnitParams);
        if(!sendResult.getProcessingResult().isFailedResult())
            break MISSING_BLOCK_LABEL_299;
        if(DBG)
            m_log.debug("error response from market-gwy:" + sendResult.getProcessingResult().toString());
        eqtypeordersubmissionresult = new EqTypeOrderSubmissionResult(ProcessingResult.newFailedResultInstance(sendResult.getProcessingResult().getErrorInfo()));
        OrderSubmitContextIndicator.clearSubmitContext();
        return eqtypeordersubmissionresult;
        eqtypeordersubmissionresult = EqTypeOrderSubmissionResult.OK_RESULT;
        OrderSubmitContextIndicator.clearSubmitContext();
        return eqtypeordersubmissionresult;
        OrderValidationException ove;
        ove;
        tradedProduct = new EqTypeOrderSubmissionResult(ove.getValidationResult().getProcessingResult());
        OrderSubmitContextIndicator.clearSubmitContext();
        return tradedProduct;
        NotFoundException nfe;
        nfe;
        String msg = "Could not find market/product/traded product.";
        m_log.error(nfe.getMessage(), nfe);
        throw new RuntimeSystemException("Could not find market/product/traded product.", nfe);
        Exception exception;
        exception;
        OrderSubmitContextIndicator.clearSubmitContext();
        throw exception;
    }

    public EqTypeOrderSubmissionResult submitSwapContractOrder(SubAccount subAccount, EqTypeSwapContractOrderSpec spec, long orderId, String tradingPassword, boolean skipOrderValidation)
    {
        EqTypeTradedProduct tradedProduct;
        OrderSubmitContextIndicator.setSubmitContext();
        performCommonSubmitStepPreValdations(true, spec.getTrader(), subAccount, tradingPassword, orderId);
        if(skipOrderValidation)
            break MISSING_BLOCK_LABEL_67;
        NewOrderValidationResult valResult = Utils.getGlobalOrderManager().validateNewOrder(subAccount, ProductTypeEnum.EQUITY, spec);
        if(!valResult.getProcessingResult().isFailedResult())
            break MISSING_BLOCK_LABEL_67;
        tradedProduct = new EqTypeOrderSubmissionResult(valResult.getProcessingResult());
        OrderSubmitContextIndicator.clearSubmitContext();
        return tradedProduct;
        EqTypeOrderSubmissionResult eqtypeordersubmissionresult;
        Contract firstContract = Utils.getPositionManager().getContract(spec.getSettleContractOrderEntries()[0].getContractId());
        tradedProduct = (EqTypeTradedProduct)firstContract.getTradedProduct();
        if(DBG)
            m_log.debug("persisting order. order id : " + orderId);
        EqtypeOrderUnitParams orderUnitParams = EquityProductTypeOrderSubmitterPersistenceManager.getInstance().persistSwapContractOrder(subAccount, orderId, spec, tradedProduct);
        if(!skipOrderValidation)
            EquityProductTypeOrderManagerReusableValidations.getInstance().validateSubAccountForTradingPower(subAccount, orderId);
        long orderUnitId = orderUnitParams.getOrderUnitId();
        MarketRequestSendResult sendResult = sendSwapMarginContractOrderToMarket(subAccount, orderId, orderUnitId, spec.getTotalQuantity(), tradedProduct, firstContract.isLong(), orderUnitParams);
        if(!sendResult.getProcessingResult().isFailedResult())
            break MISSING_BLOCK_LABEL_267;
        if(DBG)
            m_log.debug("error response from market-gwy:" + sendResult.getProcessingResult().toString());
        eqtypeordersubmissionresult = new EqTypeOrderSubmissionResult(ProcessingResult.newFailedResultInstance(sendResult.getProcessingResult().getErrorInfo()));
        OrderSubmitContextIndicator.clearSubmitContext();
        return eqtypeordersubmissionresult;
        eqtypeordersubmissionresult = EqTypeOrderSubmissionResult.OK_RESULT;
        OrderSubmitContextIndicator.clearSubmitContext();
        return eqtypeordersubmissionresult;
        OrderValidationException ove;
        ove;
        tradedProduct = new EqTypeOrderSubmissionResult(ove.getValidationResult().getProcessingResult());
        OrderSubmitContextIndicator.clearSubmitContext();
        return tradedProduct;
        NotFoundException nfe;
        nfe;
        String msg = "Could not find market/product/traded product.";
        m_log.error(nfe.getMessage(), nfe);
        throw new RuntimeSystemException("Could not find market/product/traded product.", nfe);
        Exception exception;
        exception;
        OrderSubmitContextIndicator.clearSubmitContext();
        throw exception;
    }

    public EqTypeOrderValidationResult validateChangeOrder(SubAccount subAccount, EqTypeChangeOrderSpec spec)
    {
        if(DBG)
            m_log.debug("Begin change order validation.");
        EquityProductTypeOrderManagerReusableValidations.getInstance().commonFirstValidationsForAllOperations(subAccount);
        long orderId = spec.getOrderId();
        Order order = EquityProductTypeOrderManagerReusableValidations.getInstance().validateOrderIdForExistence(orderId);
        OrderUnit orderUnits[] = order.getOrderUnits();
        OrderCategEnum orderCateg = orderUnits.length <= 0 ? null : orderUnits[0].getOrderCateg();
        if(!OrderCategEnum.ASSET.equals(orderCateg) && !OrderCategEnum.OPEN_MARGIN.equals(orderCateg))
            throw new IllegalArgumentException("Invalid call to change validateChangeOrder. Should be calling either validateChangeSettleContractOrder or validateChangeSwapContractOrder");
        OrderTypeEnum orderType = orderUnits.length <= 0 ? null : orderUnits[0].getOrderType();
        if(OrderTypeEnum.MINI_STOCK_BUY.equals(orderType) || OrderTypeEnum.MINI_STOCK_SELL.equals(orderType))
            throw new IllegalArgumentException("Invalid call to change validateChangeOrder. Mini stock change is not supported.");
        EquityProductTypeOrderManagerReusableValidations.getInstance().runOrderValidatorChecks(subAccount, (EqTypeOrder)order);
        EquityProductTypeOrderManagerReusableValidations.getInstance().validateOrderForChangeability((EqTypeOrder)order);
        EquityProductTypeOrderManagerReusableValidations.getInstance().validateChangeOrderPriceAndQty((EqTypeOrder)order, spec);
        if(DBG)
            m_log.debug("change order validation passed.");
        return EqTypeOrderValidationResult.OK_RESULT;
        OrderValidationException ve;
        ve;
        return new EqTypeOrderValidationResult(ve.getValidationResult().getProcessingResult());
    }

    public EqTypeOrderValidationResult validateChangeSettleContractOrder(SubAccount subAccount, EqTypeChangeSettleContractOrderSpec spec)
    {
        if(DBG)
            m_log.debug("Begin change Settle Contract order validation.");
        EquityProductTypeOrderManagerReusableValidations.getInstance().commonFirstValidationsForAllOperations(subAccount);
        long orderId = spec.getOrderId();
        Order order = EquityProductTypeOrderManagerReusableValidations.getInstance().validateOrderIdForExistence(orderId);
        OrderUnit orderUnits[] = order.getOrderUnits();
        if(orderUnits.length <= 0 || !orderUnits[0].getOrderCateg().equals(OrderCategEnum.CLOSE_MARGIN))
            throw new IllegalArgumentException("Invalid call to change validateChangeSettleContractOrder. Should be calling either validateChangeOrder or validateChangeSwapContractOrder");
        EquityProductTypeOrderManagerReusableValidations.getInstance().runOrderValidatorChecks(subAccount, (EqTypeOrder)order);
        EquityProductTypeOrderManagerReusableValidations.getInstance().validateOrderForChangeability((EqTypeOrder)order);
        EquityProductTypeOrderManagerReusableValidations.getInstance().validateChangeOrderPriceAndQty((EqTypeOrder)order, spec);
        if(DBG)
            m_log.debug("change settle contract order validation passed.");
        return EqTypeOrderValidationResult.OK_RESULT;
        OrderValidationException ve;
        ve;
        return new EqTypeOrderValidationResult(ve.getValidationResult().getProcessingResult());
    }

    public EqTypeOrderValidationResult validateChangeSwapContractOrder(SubAccount subAccount, EqTypeChangeSwapContractOrderSpec spec)
    {
        if(DBG)
            m_log.debug("Begin change Settle Contract order validation.");
        EquityProductTypeOrderManagerReusableValidations.getInstance().commonFirstValidationsForAllOperations(subAccount);
        long orderId = spec.getOrderId();
        Order order = EquityProductTypeOrderManagerReusableValidations.getInstance().validateOrderIdForExistence(orderId);
        OrderUnit orderUnits[] = order.getOrderUnits();
        if(orderUnits.length <= 0 || !orderUnits[0].getOrderCateg().equals(OrderCategEnum.SWAP_MARGIN))
            throw new IllegalArgumentException("Invalid call to change validateChangeSwapContractOrder. Should be calling either validateChangeOrder or validateChangeSettleContractOrder");
        EquityProductTypeOrderManagerReusableValidations.getInstance().runOrderValidatorChecks(subAccount, order);
        EquityProductTypeOrderManagerReusableValidations.getInstance().validateOrderForChangeability(order);
        EquityProductTypeOrderManagerReusableValidations.getInstance().validateChangeOrderQty((EqTypeOrder)order, spec);
        if(DBG)
            m_log.debug("change swap contract order validation passed.");
        return EqTypeOrderValidationResult.OK_RESULT;
        OrderValidationException ve;
        ve;
        return new EqTypeOrderValidationResult(ve.getValidationResult().getProcessingResult());
    }

    public EqTypeOrderSubmissionResult submitChangeOrder(SubAccount subAccount, EqTypeChangeOrderSpec spec, String tradingPassword, boolean skipOrderValidation)
    {
        EqTypeOrderSubmissionResult eqtypeordersubmissionresult;
        OrderSubmitContextIndicator.setSubmitContext();
        performCommonSubmitStepPreValdations(false, null, subAccount, tradingPassword, -1L);
        if(skipOrderValidation)
            break MISSING_BLOCK_LABEL_62;
        OrderValidationResult valResult = Utils.getGlobalOrderManager().validateChangeOrder(subAccount, spec);
        if(!valResult.getProcessingResult().isFailedResult())
            break MISSING_BLOCK_LABEL_62;
        eqtypeordersubmissionresult = new EqTypeOrderSubmissionResult(valResult.getProcessingResult());
        OrderSubmitContextIndicator.clearSubmitContext();
        return eqtypeordersubmissionresult;
        boolean localChange;
        EqTypeMarketRequestSenderService marketSenderSvc;
        com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages.EqTypeChangeOrderMarketRequestMessage mktReqMsg;
        long orderId = spec.getOrderId();
        Order order = Utils.getOrderManager().getOrder(orderId);
        OrderUnit orderUnits[] = order.getOrderUnits();
        localChange = Double.isNaN(orderUnits[0].getConfirmedQuantity());
        if(localChange)
            EquityProductTypeOrderSubmitterPersistenceManager.getInstance().handleChangedOrderUpdates(orderId, spec.getChangeOrderUnitEntries());
        else
            EquityProductTypeOrderSubmitterPersistenceManager.getInstance().handleChangeAcceptedOrderUpdates(orderId, spec.getChangeOrderUnitEntries());
        if(!skipOrderValidation)
            EquityProductTypeOrderManagerReusableValidations.getInstance().validateSubAccountForTradingPower(subAccount, orderId);
        marketSenderSvc = Utils.getMarketSenderService();
        mktReqMsg = new DefaultEqTypeChangeOrderMarketRequestMessage(subAccount, orderId, spec.getChangeOrderUnitEntries());
        EqTypeOrderSubmissionResult eqtypeordersubmissionresult1;
        if(DBG)
            m_log.debug("Sending change request to market: localChange : " + localChange);
        MarketRequestSendResult sendResult = marketSenderSvc.send(mktReqMsg, localChange);
        if(!sendResult.getProcessingResult().isFailedResult())
            break MISSING_BLOCK_LABEL_296;
        if(DBG)
            m_log.debug("error response from market-gwy:" + sendResult.getProcessingResult().toString());
        eqtypeordersubmissionresult1 = new EqTypeOrderSubmissionResult(ProcessingResult.newFailedResultInstance(sendResult.getProcessingResult().getErrorInfo()));
        OrderSubmitContextIndicator.clearSubmitContext();
        return eqtypeordersubmissionresult1;
        eqtypeordersubmissionresult1 = EqTypeOrderSubmissionResult.OK_RESULT;
        OrderSubmitContextIndicator.clearSubmitContext();
        return eqtypeordersubmissionresult1;
        TooLateException tle;
        tle;
        eqtypeordersubmissionresult1 = new EqTypeOrderSubmissionResult(ProcessingResult.newFailedResultInstance(OrderManagerErrorCatalog.CHANGE_FAILED_ORDER_NOT_CHANGEABLE_NOW));
        OrderSubmitContextIndicator.clearSubmitContext();
        return eqtypeordersubmissionresult1;
        OrderValidationException ove;
        ove;
        eqtypeordersubmissionresult = new EqTypeOrderSubmissionResult(ove.getValidationResult().getProcessingResult());
        OrderSubmitContextIndicator.clearSubmitContext();
        return eqtypeordersubmissionresult;
        NotFoundException nfe;
        nfe;
        m_log.error(nfe.getMessage(), nfe);
        throw new RuntimeSystemException("Exception while sumitting a change order.", nfe);
        Exception exception;
        exception;
        OrderSubmitContextIndicator.clearSubmitContext();
        throw exception;
    }

    public EqTypeOrderSubmissionResult submitChangeSettleContractOrder(SubAccount subAccount, EqTypeChangeSettleContractOrderSpec spec, String tradingPassword, boolean skipOrderValidation)
    {
        EqTypeOrderSubmissionResult eqtypeordersubmissionresult;
        OrderSubmitContextIndicator.setSubmitContext();
        performCommonSubmitStepPreValdations(false, null, subAccount, tradingPassword, -1L);
        if(skipOrderValidation)
            break MISSING_BLOCK_LABEL_62;
        OrderValidationResult valResult = Utils.getGlobalOrderManager().validateChangeOrder(subAccount, spec);
        if(!valResult.getProcessingResult().isFailedResult())
            break MISSING_BLOCK_LABEL_62;
        eqtypeordersubmissionresult = new EqTypeOrderSubmissionResult(valResult.getProcessingResult());
        OrderSubmitContextIndicator.clearSubmitContext();
        return eqtypeordersubmissionresult;
        boolean localChange;
        EqTypeMarketRequestSenderService marketSenderSvc;
        com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages.EqTypeChangeSettleContractOrderMarketRequestMessage mktReqMsg;
        long orderId = spec.getOrderId();
        EqTypeOrder order = (EqTypeOrder)Utils.getOrderManager().getOrder(orderId);
        OrderUnit orderUnits[] = order.getOrderUnits();
        localChange = Double.isNaN(((EqTypeMarketTradedOrderUnit)orderUnits[0]).getConfirmedQuantity());
        if(localChange)
            EquityProductTypeOrderSubmitterPersistenceManager.getInstance().handleChangedOrderUpdates(orderId, spec.getChangeOrderUnitEntries());
        else
            EquityProductTypeOrderSubmitterPersistenceManager.getInstance().handleChangeAcceptedOrderUpdates(orderId, spec.getChangeOrderUnitEntries());
        if(!skipOrderValidation)
            EquityProductTypeOrderManagerReusableValidations.getInstance().validateSubAccountForTradingPower(subAccount, orderId);
        marketSenderSvc = Utils.getMarketSenderService();
        mktReqMsg = new DefaultEqTypeChangeSettleContractOrderMarketRequestMessage(subAccount, orderId, spec.getChangeOrderUnitEntries());
        EqTypeOrderSubmissionResult eqtypeordersubmissionresult1;
        if(DBG)
            m_log.debug("Sending change request to market: localChange : " + localChange);
        MarketRequestSendResult sendResult = marketSenderSvc.send(mktReqMsg, localChange);
        if(!sendResult.getProcessingResult().isFailedResult())
            break MISSING_BLOCK_LABEL_302;
        if(DBG)
            m_log.debug("error response from market-gwy:" + sendResult.getProcessingResult().toString());
        eqtypeordersubmissionresult1 = new EqTypeOrderSubmissionResult(ProcessingResult.newFailedResultInstance(sendResult.getProcessingResult().getErrorInfo()));
        OrderSubmitContextIndicator.clearSubmitContext();
        return eqtypeordersubmissionresult1;
        eqtypeordersubmissionresult1 = EqTypeOrderSubmissionResult.OK_RESULT;
        OrderSubmitContextIndicator.clearSubmitContext();
        return eqtypeordersubmissionresult1;
        TooLateException tle;
        tle;
        eqtypeordersubmissionresult1 = new EqTypeOrderSubmissionResult(ProcessingResult.newFailedResultInstance(OrderManagerErrorCatalog.CHANGE_FAILED_ORDER_NOT_CHANGEABLE_NOW));
        OrderSubmitContextIndicator.clearSubmitContext();
        return eqtypeordersubmissionresult1;
        OrderValidationException ove;
        ove;
        eqtypeordersubmissionresult = new EqTypeOrderSubmissionResult(ove.getValidationResult().getProcessingResult());
        OrderSubmitContextIndicator.clearSubmitContext();
        return eqtypeordersubmissionresult;
        NotFoundException nfe;
        nfe;
        m_log.error(nfe.getMessage(), nfe);
        throw new RuntimeSystemException("Exception while sumitting a change settle contract order.", nfe);
        Exception exception;
        exception;
        OrderSubmitContextIndicator.clearSubmitContext();
        throw exception;
    }

    public EqTypeOrderSubmissionResult submitChangeSwapContractOrder(SubAccount subAccount, EqTypeChangeSwapContractOrderSpec spec, String tradingPassword, boolean skipOrderValidation)
    {
        EqTypeOrderSubmissionResult eqtypeordersubmissionresult;
        OrderSubmitContextIndicator.setSubmitContext();
        performCommonSubmitStepPreValdations(false, null, subAccount, tradingPassword, -1L);
        if(skipOrderValidation)
            break MISSING_BLOCK_LABEL_62;
        OrderValidationResult valResult = Utils.getGlobalOrderManager().validateChangeOrder(subAccount, spec);
        if(!valResult.getProcessingResult().isFailedResult())
            break MISSING_BLOCK_LABEL_62;
        eqtypeordersubmissionresult = new EqTypeOrderSubmissionResult(valResult.getProcessingResult());
        OrderSubmitContextIndicator.clearSubmitContext();
        return eqtypeordersubmissionresult;
        boolean localChange;
        EqTypeMarketRequestSenderService marketSenderSvc;
        com.fitechlabs.xtrade.plugin.tc.eqtype.market.messages.EqTypeChangeSwapContractOrderMarketRequestMessage mktReqMsg;
        long orderId = spec.getOrderId();
        EqTypeOrder order = (EqTypeOrder)Utils.getOrderManager().getOrder(orderId);
        OrderUnit orderUnits[] = order.getOrderUnits();
        localChange = Double.isNaN(((EqTypeMarketTradedOrderUnit)orderUnits[0]).getConfirmedQuantity());
        if(localChange)
            EquityProductTypeOrderSubmitterPersistenceManager.getInstance().handleChangedOrderUpdates(orderId, spec.getChangeOrderUnitEntries());
        else
            EquityProductTypeOrderSubmitterPersistenceManager.getInstance().handleChangeAcceptedOrderUpdates(orderId, spec.getChangeOrderUnitEntries());
        if(!skipOrderValidation)
            EquityProductTypeOrderManagerReusableValidations.getInstance().validateSubAccountForTradingPower(subAccount, orderId);
        marketSenderSvc = Utils.getMarketSenderService();
        mktReqMsg = new DefaultEqTypeChangeSwapContractOrderMarketRequestMessage(subAccount, orderId, spec.getChangeOrderUnitEntries());
        EqTypeOrderSubmissionResult eqtypeordersubmissionresult1;
        if(DBG)
            m_log.debug("Sending change request to market: localChange : " + localChange);
        MarketRequestSendResult sendResult = marketSenderSvc.send(mktReqMsg, localChange);
        if(!sendResult.getProcessingResult().isFailedResult())
            break MISSING_BLOCK_LABEL_302;
        if(DBG)
            m_log.debug("error response from market-gwy:" + sendResult.getProcessingResult().toString());
        eqtypeordersubmissionresult1 = new EqTypeOrderSubmissionResult(ProcessingResult.newFailedResultInstance(sendResult.getProcessingResult().getErrorInfo()));
        OrderSubmitContextIndicator.clearSubmitContext();
        return eqtypeordersubmissionresult1;
        eqtypeordersubmissionresult1 = EqTypeOrderSubmissionResult.OK_RESULT;
        OrderSubmitContextIndicator.clearSubmitContext();
        return eqtypeordersubmissionresult1;
        TooLateException tle;
        tle;
        eqtypeordersubmissionresult1 = new EqTypeOrderSubmissionResult(ProcessingResult.newFailedResultInstance(OrderManagerErrorCatalog.CHANGE_FAILED_ORDER_NOT_CHANGEABLE_NOW));
        OrderSubmitContextIndicator.clearSubmitContext();
        return eqtypeordersubmissionresult1;
        OrderValidationException ove;
        ove;
        eqtypeordersubmissionresult = new EqTypeOrderSubmissionResult(ove.getValidationResult().getProcessingResult());
        OrderSubmitContextIndicator.clearSubmitContext();
        return eqtypeordersubmissionresult;
        NotFoundException nfe;
        nfe;
        m_log.error(nfe.getMessage(), nfe);
        throw new RuntimeSystemException("Exception while sumitting a change settle contract order.", nfe);
        Exception exception;
        exception;
        OrderSubmitContextIndicator.clearSubmitContext();
        throw exception;
    }

    public EqTypeOrderValidationResult validateCancelOrder(SubAccount subAccount, EqTypeCancelOrderSpec spec)
    {
        EquityProductTypeOrderManagerReusableValidations.getInstance().commonFirstValidationsForAllOperations(subAccount);
        long orderId = spec.getOrderId();
        Order order = EquityProductTypeOrderManagerReusableValidations.getInstance().validateOrderIdForExistence(orderId);
        EquityProductTypeOrderManagerReusableValidations.getInstance().runOrderValidatorChecks(subAccount, (EqTypeOrder)order);
        EquityProductTypeOrderManagerReusableValidations.getInstance().validateOrderForCancellation((EqTypeOrder)order);
        return EqTypeOrderValidationResult.OK_RESULT;
        OrderValidationException ve;
        ve;
        return new EqTypeOrderValidationResult(ve.getValidationResult().getProcessingResult());
    }

    public EqTypeOrderSubmissionResult submitCancelOrder(SubAccount subAccount, EqTypeCancelOrderSpec spec, String tradingPassword, boolean skipOrderValidation)
    {
        long orderId;
        EqTypeOrderSubmissionResult eqtypeordersubmissionresult1;
        OrderSubmitContextIndicator.setSubmitContext();
        performCommonSubmitStepPreValdations(false, null, subAccount, tradingPassword, -1L);
        orderId = spec.getOrderId();
        if(skipOrderValidation)
            break MISSING_BLOCK_LABEL_68;
        OrderValidationResult valResult = Utils.getGlobalOrderManager().validateCancelOrder(subAccount, spec);
        if(!valResult.getProcessingResult().isFailedResult())
            break MISSING_BLOCK_LABEL_68;
        eqtypeordersubmissionresult1 = new EqTypeOrderSubmissionResult(valResult.getProcessingResult());
        OrderSubmitContextIndicator.clearSubmitContext();
        return eqtypeordersubmissionresult1;
        boolean localCancel;
        EqTypeMarketRequestSenderService marketSenderSvc;
        com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.CancelOrderMarketRequestMessage mktReqMsg;
        EqTypeOrder order = (EqTypeOrder)Utils.getOrderManager().getOrder(orderId);
        OrderUnit orderUnits[] = order.getOrderUnits();
        localCancel = Double.isNaN(((EqTypeMarketTradedOrderUnit)orderUnits[0]).getConfirmedQuantity());
        if(localCancel)
        {
            if(DBG)
                m_log.debug("order is not yet sent to market. trying a local cancel.");
            EquityProductTypeOrderSubmitterPersistenceManager.getInstance().handleCancelledOrderUpdates(orderId);
        } else
        {
            EquityProductTypeOrderSubmitterPersistenceManager.getInstance().handleCancelAcceptedOrderUpdates(orderId);
        }
        if(!skipOrderValidation)
            EquityProductTypeOrderManagerReusableValidations.getInstance().validateSubAccountForTradingPower(subAccount, orderId);
        marketSenderSvc = Utils.getMarketSenderService();
        mktReqMsg = new DefaultCancelOrderMarketRequestMessage(subAccount, orderId);
        EqTypeOrderSubmissionResult eqtypeordersubmissionresult2;
        if(DBG)
            m_log.debug("Sending cancel request to market: localCancel : " + localCancel);
        MarketRequestSendResult sendResult = marketSenderSvc.send(mktReqMsg, localCancel);
        if(!sendResult.getProcessingResult().isFailedResult())
            break MISSING_BLOCK_LABEL_304;
        if(DBG)
            m_log.debug("error response from market-gwy:" + sendResult.getProcessingResult().toString());
        eqtypeordersubmissionresult2 = new EqTypeOrderSubmissionResult(ProcessingResult.newFailedResultInstance(sendResult.getProcessingResult().getErrorInfo()));
        OrderSubmitContextIndicator.clearSubmitContext();
        return eqtypeordersubmissionresult2;
        eqtypeordersubmissionresult2 = EqTypeOrderSubmissionResult.OK_RESULT;
        OrderSubmitContextIndicator.clearSubmitContext();
        return eqtypeordersubmissionresult2;
        TooLateException tle;
        tle;
        eqtypeordersubmissionresult2 = new EqTypeOrderSubmissionResult(ProcessingResult.newFailedResultInstance(OrderManagerErrorCatalog.CANCEL_FAILED_ORDER_NOT_CANCELLABLE_NOW));
        OrderSubmitContextIndicator.clearSubmitContext();
        return eqtypeordersubmissionresult2;
        OrderValidationException ove;
        ove;
        EqTypeOrderSubmissionResult eqtypeordersubmissionresult = new EqTypeOrderSubmissionResult(ove.getValidationResult().getProcessingResult());
        OrderSubmitContextIndicator.clearSubmitContext();
        return eqtypeordersubmissionresult;
        NotFoundException nfe;
        nfe;
        m_log.error(nfe.getMessage(), nfe);
        throw new RuntimeSystemException("Exception while sumitting a cancel order.", nfe);
        Exception exception;
        exception;
        OrderSubmitContextIndicator.clearSubmitContext();
        throw exception;
    }

    public long createNewOrderId()
    {
        return PersistenceManager.getInstance().createNewOrderId();
    }

    public long createNewOrderUnitId()
    {
        return PersistenceManager.getInstance().createNewOrderUnitId();
    }

    protected void checkTradingPassword(Trader trader, SubAccount subAccount, String tradingPassword)
        throws OrderValidationException
    {
        ProcessingResult result = GtlUtils.getCommonOrderValidator().validateTradingPassword(trader, subAccount, tradingPassword).getProcessingResult();
        if(result.isFailedResult())
            throw new OrderValidationException(result.getErrorInfo());
        else
            return;
    }

    private void performCommonSubmitStepPreValdations(boolean isNewOrder, Trader trader, SubAccount subAccount, String tradingPassword, long orderId)
        throws OrderValidationException
    {
        checkTradingPassword(trader, subAccount, tradingPassword);
        if(isNewOrder)
            EquityProductTypeOrderManagerReusableValidations.getInstance().validateOrderIdForDuplicate(orderId);
    }

    protected TradingModule getThisTradingModule()
    {
        return GtlUtils.getTradingModule("eqtype");
    }

    static Class _mthclass$(String x0)
    {
        return Class.forName(x0);
        ClassNotFoundException x1;
        x1;
        throw new NoClassDefFoundError(x1.getMessage());
    }

    private static final Logit m_log;
    private static final boolean DBG;

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeOrderSubmitter.class);
        DBG = m_log.ison();
    }
}
