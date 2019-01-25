// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GenericOrderManagerReusableValidations.java

package com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls;

import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import java.util.Date;

public abstract class GenericOrderManagerReusableValidations
{

    protected GenericOrderManagerReusableValidations()
    {
    }

    public void commonFirstValidationsForAllOperations(SubAccount subAccount)
        throws OrderValidationException
    {
        TradingSystem tradSys = GtlUtils.getTradingSystem();
        if(!tradSys.isSystemAcceptingOrders())
            throw new OrderValidationException(OrderManagerErrorCatalog.SYSTEM_NOT_ACCEPTING_ORDERS);
        ProcessingResult result = GtlUtils.getCommonOrderValidator().validateSubAccountForTrading(subAccount).getProcessingResult();
        if(result.isFailedResult())
            throw new OrderValidationException(result.getErrorInfo());
        result = getThisTradingModule().getOrderManager().getOrderValidator().validateSubAccountForTrading(subAccount).getProcessingResult();
        if(result.isFailedResult())
            throw new OrderValidationException(result.getErrorInfo());
        else
            return;
    }

    public void validateSubAccountForTradingPower(SubAccount subAccount, long orderId)
        throws OrderValidationException
    {
        if(DBG)
            m_log.debug("Checking trading power/buying power for subaccount : " + subAccount.getSubAccountId());
        GlobalBizLogicProvider bizLogic = GtlUtils.getGlobalBizLogicProvider();
        OrderSpec orderSpec = new OrderSpec(orderId);
        OrderValidationResult result = bizLogic.checkTradingPower(subAccount, orderSpec);
        if(result.getProcessingResult().isSuccessfulResult())
        {
            if(DBG)
                m_log.debug("Trading power check passed !");
            return;
        }
        if(DBG)
            m_log.debug("Not enough buyingpower. error details : " + result.getProcessingResult().getErrorInfo());
        throw new OrderValidationException(result.getProcessingResult().getErrorInfo());
    }

    public Market validateMarket(Institution inst, String marketCode)
        throws OrderValidationException
    {
        Market market = null;
        try
        {
            if(marketCode != null)
                market = GtlUtils.getFinObjectManager().getMarket(inst, marketCode);
        }
        catch(NotFoundException nfe) { }
        if(market == null)
            throw new OrderValidationException(OrderManagerErrorCatalog.INVALID_MARKET_CODE);
        else
            return market;
    }

    public void validateMarket(Order order)
        throws OrderValidationException
    {
        OrderUnit orderUnits[] = order.getOrderUnits();
        for(int i = 0; i < orderUnits.length; i++)
        {
            OrderUnit ou = orderUnits[i];
            Market market = ou.getTradedProduct().getMarket();
            validateMarket(ou.getOrderType(), market);
        }

    }

    public void validateMarket(OrderTypeEnum orderType, Market market)
        throws OrderValidationException
    {
        OrderValidationResult result = getThisTradingModule().getOrderManager().getOrderValidator().validateMarket(orderType, market);
        if(result.getProcessingResult().isFailedResult())
            throw new OrderValidationException(OrderManagerErrorCatalog.NOT_ACCEPTING_ORDERS_FOR_MARKET);
        else
            return;
    }

    public void checkLotSize(double lotSize, double qty)
        throws OrderValidationException
    {
        long flatLotSize = (long)(lotSize * 100D);
        if((long)(qty * 100D) % flatLotSize != 0L)
            throw new OrderValidationException(OrderManagerErrorCatalog.INVALID_QTY_LOT_SIZE_ERROR);
        else
            return;
    }

    public void validateQuantity(double qty)
        throws OrderValidationException
    {
        if(qty <= 0.0D || com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils.Double.isZero(qty))
            throw new OrderValidationException(OrderManagerErrorCatalog.INVALID_QTY);
        else
            return;
    }

    public void validateQuantity(Product product, double qty)
        throws OrderValidationException
    {
        validateQuantity(qty);
        checkLotSize(product.getLotSize(), qty);
    }

    public void validateExpirationDate(TradedProduct tradedProduct, Date orderExpDate)
        throws OrderValidationException
    {
        if(orderExpDate != null)
        {
            Date today = GtlUtils.clearTimeFields(GtlUtils.getSystemTimestamp());
            Date inExpDate = GtlUtils.clearTimeFields(orderExpDate);
            if(inExpDate.before(today))
                throw new OrderValidationException(OrderManagerErrorCatalog.INVALID_ORDER_EXP_DATE);
        }
    }

    public void validateOrderIdForDuplicate(long orderId)
        throws OrderValidationException
    {
        OrderManager om = getThisTradingModule().getOrderManager();
        try
        {
            om.getOrder(orderId);
            throw new OrderValidationException(OrderManagerErrorCatalog.DUPLICATE_ORDER_SUBMISSION);
        }
        catch(NotFoundException ignore)
        {
            return;
        }
    }

    public Order validateOrderIdForExistence(long orderId)
        throws OrderValidationException
    {
        OrderManager om = getThisTradingModule().getOrderManager();
        return om.getOrder(orderId);
        NotFoundException ignore;
        ignore;
        throw new OrderValidationException(OrderManagerErrorCatalog.INVALID_ORDER);
    }

    public void validateOrderForCancellation(Order order)
        throws OrderValidationException
    {
        OrderUnit orderUnits[] = order.getOrderUnits();
        int i = 0;
        do
        {
            if(i >= orderUnits.length)
                break;
            OrderUnit ou = orderUnits[i];
            if(!OrderOpenStatusEnum.OPEN.equals(ou.getOrderOpenStatus()))
                throw new OrderValidationException(OrderManagerErrorCatalog.CANCEL_FAILED_ORDER_ALREADY_CLOSED);
            switch(ou.getOrderStatus().intValue())
            {
            case 2: // '\002'
            case 7: // '\007'
            case 8: // '\b'
            case 12: // '\f'
            case 13: // '\r'
                throw new OrderValidationException(OrderManagerErrorCatalog.CANCEL_FAILED_ORDER_NOT_CANCELLABLE_NOW);

            case 3: // '\003'
            case 4: // '\004'
            case 5: // '\005'
            case 6: // '\006'
            case 9: // '\t'
            case 10: // '\n'
            case 11: // '\013'
            default:
                i++;
                break;
            }
        } while(true);
    }

    public void validateOrderForChangeability(Order order)
        throws OrderValidationException
    {
        OrderUnit orderUnits[] = order.getOrderUnits();
        int i = 0;
        do
        {
            if(i >= orderUnits.length)
                break;
            OrderUnit ou = orderUnits[i];
            if(!OrderOpenStatusEnum.OPEN.equals(ou.getOrderOpenStatus()))
                throw new OrderValidationException(OrderManagerErrorCatalog.CHANGE_FAILED_ORDER_ALREADY_CLOSED);
            switch(ou.getOrderStatus().intValue())
            {
            case 2: // '\002'
            case 7: // '\007'
            case 8: // '\b'
            case 12: // '\f'
            case 13: // '\r'
                throw new OrderValidationException(OrderManagerErrorCatalog.CHANGE_FAILED_ORDER_NOT_CHANGEABLE_NOW);

            case 3: // '\003'
            case 4: // '\004'
            case 5: // '\005'
            case 6: // '\006'
            case 9: // '\t'
            case 10: // '\n'
            case 11: // '\013'
            default:
                i++;
                break;
            }
        } while(true);
    }

    public void validateSellableAssetQuantity(SubAccount subAccount, TradedProduct tradedProduct, double quantityOrdered)
        throws OrderValidationException
    {
        PositionManager posMgr = getThisTradingModule().getPositionManager();
        try
        {
            Asset asset = posMgr.getAsset(subAccount.getAccountId(), subAccount.getSubAccountId(), tradedProduct.getProduct().getProductId());
            double qty = asset.getQuantity();
            double lockedQty = asset.getLockedQuantity();
            if(quantityOrdered > qty - lockedQty)
                throw new OrderValidationException(OrderManagerErrorCatalog.INSUFFICIENT_ASSET_QUANTITY_FOR_SELL);
            else
                return;
        }
        catch(NotFoundException nfe)
        {
            throw new OrderValidationException(OrderManagerErrorCatalog.ASSET_NOT_AVAILABLE_FOR_SELL);
        }
    }

    public void runOrderValidatorChecks(SubAccount subAccount, Order order)
        throws OrderValidationException
    {
        OrderUnit orderUnits[] = order.getOrderUnits();
        for(int i = 0; i < orderUnits.length; i++)
        {
            OrderUnit ou = orderUnits[i];
            runOrderValidatorChecks(subAccount, ou.getOrderType(), ou.getTradedProduct());
        }

    }

    public void runOrderValidatorChecks(SubAccount subAccount, OrderTypeEnum orderType, TradedProduct tradedProduct)
        throws OrderValidationException
    {
        OrderValidator validator = getThisTradingModule().getOrderManager().getOrderValidator();
        OrderValidationResult result = validator.validateTradedProductForTrading(subAccount, orderType, tradedProduct);
        if(result.getProcessingResult().isFailedResult())
            throw new OrderValidationException(result.getProcessingResult().getErrorInfo());
        result = validator.validateMarket(orderType, tradedProduct.getMarket());
        if(result.getProcessingResult().isFailedResult())
            throw new OrderValidationException(result.getProcessingResult().getErrorInfo());
        else
            return;
    }

    public TradedProduct validateTradedProduct(Product product, Market market)
        throws OrderValidationException
    {
        return getThisTradingModule().getProductManager().getTradedProduct(product, market);
        NotFoundException nfe;
        nfe;
        throw new OrderValidationException(OrderManagerErrorCatalog.INVALID_TRADED_PRODUCT);
    }

    protected OrderUnit lookupOrderUnit(long orderUnitId, OrderUnit orderUnits[])
    {
        for(int i = 0; i < orderUnits.length; i++)
        {
            OrderUnit ou = orderUnits[i];
            if(ou.getOrderUnitId() == orderUnitId)
                return ou;
        }

        return null;
    }

    public void validatePrice(TradedProduct tradedProduct, double limitPrice, boolean isMarketOrder)
        throws OrderValidationException
    {
        if(limitPrice < 0.0D)
            throw new OrderValidationException(OrderManagerErrorCatalog.INVALID_LIMIT_PRICE);
        Market market = tradedProduct.getMarket();
        if(isMarketOrder && !market.acceptsMarketOrder())
            throw new OrderValidationException(OrderManagerErrorCatalog.NO_MARKET_ORDERS);
        if(!isMarketOrder && !market.acceptsLimitOrder())
            throw new OrderValidationException(OrderManagerErrorCatalog.NO_LIMIT_ORDERS);
        else
            return;
    }

    protected abstract TradingModule getThisTradingModule();

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
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.GenericOrderManagerReusableValidations.class);
        DBG = m_log.ison();
    }
}
