// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EquityProductTypeOrderManagerReusableValidations.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls;

import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.*;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeClosingContractSpecRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.GenericOrderManagerReusableValidations;
import java.util.Arrays;
import java.util.List;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls:
//            PersistenceManagerImpl, EqTypeServerConfigConstants, Utils

public class EquityProductTypeOrderManagerReusableValidations extends GenericOrderManagerReusableValidations
{

    protected EquityProductTypeOrderManagerReusableValidations()
    {
    }

    public static EquityProductTypeOrderManagerReusableValidations getInstance()
    {
        return THIS_INSTANCE;
    }

    protected static void setInstance(EquityProductTypeOrderManagerReusableValidations newInstance)
    {
        m_log.info("Replacing default " + (com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EquityProductTypeOrderManagerReusableValidations.class) + " default impl with new implementation : " + newInstance.getClass());
        THIS_INSTANCE = newInstance;
    }

    public EqTypeProduct validateProductCode(Institution inst, String productCode)
        throws OrderValidationException
    {
        TradingModule tm;
        Product p;
        if(productCode == null)
            break MISSING_BLOCK_LABEL_103;
        tm = getThisTradingModule();
        p = ((EqTypeProductManager)tm.getProductManager()).getProduct(inst, productCode);
        if(tm.canHandle(p.getProductType()))
            return (EqTypeProduct)p;
        try
        {
            m_log.warn("Invalid trading module is called. ProductType is : " + p.getProductType() + ", but trading module:" + tm.getTradingModuleName() + " cant seem to handle such product type.");
        }
        catch(NotFoundException nfe) { }
        throw new OrderValidationException(OrderManagerErrorCatalog.INVALID_PRODUCT_CODE);
    }

    public TradedProduct validateTradedProduct(Product product, Market market)
        throws OrderValidationException
    {
        EqTypeTradedProduct tp = (EqTypeTradedProduct)super.validateTradedProduct(product, market);
        if(!tp.isListedCurrently())
            throw new OrderValidationException(OrderManagerErrorCatalog.TRADED_PRODUCT_NOT_LISTED);
        else
            return tp;
    }

    public EqTypeTradedProduct validateTradedProductForMarginTrading(EqTypeProduct product, Market market, boolean isLong)
        throws OrderValidationException
    {
        EqTypeTradedProduct tradedProduct = (EqTypeTradedProduct)validateTradedProduct(product, market);
        if(isLong && !tradedProduct.isMarginable())
            throw new OrderValidationException(OrderManagerErrorCatalog.TRADED_PRODUCT_NOT_LONG_MARGINABLE);
        if(!isLong && !tradedProduct.isShortable())
            throw new OrderValidationException(OrderManagerErrorCatalog.TRADED_PRODUCT_NOT_SHORT_MARGINABLE);
        else
            return tradedProduct;
    }

    public void validatePrice(TradedProduct tradedProduct, double limitPrice, boolean isMarketOrder)
        throws OrderValidationException
    {
        super.validatePrice(tradedProduct, limitPrice, isMarketOrder);
        if(!isMarketOrder)
        {
            EqTypeTradedProduct eqtypeTradedProduct = (EqTypeTradedProduct)tradedProduct;
            if(limitPrice > eqtypeTradedProduct.getDailyStopHigh() || limitPrice < eqtypeTradedProduct.getDailyStopLow())
                throw new OrderValidationException(OrderManagerErrorCatalog.OUT_OF_RANGE_LIMIT_PRICE);
            if(!eqtypeTradedProduct.isValidPriceAsPerTickValue(limitPrice))
                throw new OrderValidationException(OrderManagerErrorCatalog.INVALID_LIMIT_PRICE_NOT_AS_PER_TICK_VALUE);
        }
    }

    public void validateChangeOrderPriceAndQty(EqTypeOrder order, EqTypeChangeOrderSpec spec)
        throws OrderValidationException
    {
        OrderUnit origOrderUnits[] = order.getOrderUnits();
        EqTypeChangeOrderUnitEntry changeOrderEntries[] = spec.getChangeOrderUnitEntries();
        boolean changeDetected = false;
        for(int i = 0; i < changeOrderEntries.length; i++)
        {
            EqTypeChangeOrderUnitEntry entry = changeOrderEntries[i];
            long orderUnitId = entry.getOrderUnitId();
            OrderUnit origOrderUnit = lookupOrderUnit(orderUnitId, origOrderUnits);
            if(origOrderUnit == null)
                throw new OrderValidationException(OrderManagerErrorCatalog.INVALID_ORDER_UNIT_ID_IN_CHANGE_ORDER);
            double beforeChangePrice = java.lang.Double.isNaN(origOrderUnit.getConfirmedPrice()) ? origOrderUnit.isMarketOrder() ? 0.0D : origOrderUnit.getLimitPrice() : origOrderUnit.isConfirmedPriceMarketOrder() ? 0.0D : origOrderUnit.getConfirmedPrice();
            double beforeChangeQty = java.lang.Double.isNaN(origOrderUnit.getConfirmedQuantity()) ? origOrderUnit.getQuantity() : origOrderUnit.getConfirmedQuantity();
            double afterChangePrice = entry.isAfterChangePriceMarket() ? 0.0D : entry.getAfterChangePrice();
            double afterChangeQty = entry.getAfterChangeOriginalQuantity();
            if(beforeChangePrice != afterChangePrice)
            {
                validatePrice(origOrderUnit.getTradedProduct(), afterChangePrice, entry.isAfterChangePriceMarket());
                changeDetected = true;
            }
            if(beforeChangeQty == afterChangeQty)
                continue;
            if(afterChangeQty > beforeChangeQty)
                throw new OrderValidationException(OrderManagerErrorCatalog.AFTER_CHANGE_QTY_EXCEEDS_ORIG_QTY);
            if(afterChangeQty < origOrderUnit.getExecutedQuantity())
                throw new OrderValidationException(OrderManagerErrorCatalog.AFTER_CHANGE_QTY_BELOW_EXECD_QTY);
            validateQuantity(origOrderUnit.getTradedProduct().getProduct(), afterChangeQty);
            changeDetected = true;
        }

        if(!changeDetected)
            throw new OrderValidationException(OrderManagerErrorCatalog.NO_CHANGES_FOUND);
        else
            return;
    }

    public void validateChangeOrderPriceAndQty(EqTypeOrder order, EqTypeChangeSettleContractOrderSpec spec)
        throws OrderValidationException
    {
        OrderUnit origOrderUnits[] = order.getOrderUnits();
        EqTypeContractSettleChangeOrderUnitEntry changeOrderEntries[] = spec.getChangeOrderUnitEntries();
        boolean changeDetected = false;
        for(int i = 0; i < changeOrderEntries.length; i++)
        {
            EqTypeContractSettleChangeOrderUnitEntry entry = changeOrderEntries[i];
            long orderUnitId = entry.getOrderUnitId();
            OrderUnit origOrderUnit = lookupOrderUnit(orderUnitId, origOrderUnits);
            if(origOrderUnit == null)
                throw new OrderValidationException(OrderManagerErrorCatalog.INVALID_ORDER_UNIT_ID_IN_CHANGE_ORDER);
            double beforeChangePrice = java.lang.Double.isNaN(origOrderUnit.getConfirmedPrice()) ? origOrderUnit.isMarketOrder() ? 0.0D : origOrderUnit.getLimitPrice() : origOrderUnit.isConfirmedPriceMarketOrder() ? 0.0D : origOrderUnit.getConfirmedPrice();
            double beforeChangeQty = java.lang.Double.isNaN(origOrderUnit.getConfirmedQuantity()) ? origOrderUnit.getQuantity() : origOrderUnit.getConfirmedQuantity();
            double afterChangePrice = entry.isAfterChangePriceMarket() ? 0.0D : entry.getAfterChangePrice();
            double afterChangeQty = entry.getAfterChangeTotalQuantity();
            if(beforeChangePrice != afterChangePrice)
            {
                validatePrice((EqTypeTradedProduct)origOrderUnit.getTradedProduct(), afterChangePrice, entry.isAfterChangePriceMarket());
                changeDetected = true;
            }
            if(beforeChangeQty != afterChangeQty)
                changeDetected = true;
            if(afterChangeQty > beforeChangeQty)
                throw new OrderValidationException(OrderManagerErrorCatalog.AFTER_CHANGE_QTY_EXCEEDS_ORIG_QTY);
            if(afterChangeQty < origOrderUnit.getExecutedQuantity())
                throw new OrderValidationException(OrderManagerErrorCatalog.AFTER_CHANGE_QTY_BELOW_EXECD_QTY);
            EqTypeSettleContractOrderEntry afterChangeSettleContracts[] = (EqTypeSettleContractOrderEntry[])entry.getAfterChangeSettleContractOrderEntries().clone();
            Arrays.sort(afterChangeSettleContracts);
            List eqtypeContractClosingSpecRows = PersistenceManagerImpl.getEqTypeClosingContractSpecRows(origOrderUnit.getOrderUnitId(), "contract_id");
            if(eqtypeContractClosingSpecRows.size() != afterChangeSettleContracts.length)
                throw new OrderValidationException(OrderManagerErrorCatalog.INVALID_SETTLE_CONTRACT_CHANGE_SPEC);
            for(int k = 0; k < afterChangeSettleContracts.length; k++)
            {
                EqTypeSettleContractOrderEntry e = afterChangeSettleContracts[k];
                EqtypeClosingContractSpecRow r = (EqtypeClosingContractSpecRow)eqtypeContractClosingSpecRows.get(k);
                if(r.getContractId() != e.getContractId())
                    throw new OrderValidationException(OrderManagerErrorCatalog.INVALID_SETTLE_CONTRACT_CHANGE_SPEC);
                if(e.getQuantity() < r.getExecutedQuantity())
                    throw new OrderValidationException(OrderManagerErrorCatalog.AFTER_CHANGE_QTY_BELOW_EXECD_QTY);
                if(e.getQuantity() != r.getQuantity())
                    changeDetected = true;
                if(!com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils.Double.isZero(e.getQuantity()))
                    validateQuantity(e.getQuantity());
            }

            validateQuantity(origOrderUnit.getTradedProduct().getProduct(), afterChangeQty);
        }

        if(!changeDetected)
            throw new OrderValidationException(OrderManagerErrorCatalog.NO_CHANGES_FOUND);
        else
            return;
    }

    public void validateChangeOrderQty(EqTypeOrder order, EqTypeChangeSwapContractOrderSpec spec)
        throws OrderValidationException
    {
        OrderUnit origOrderUnits[] = order.getOrderUnits();
        EqTypeContractSwapChangeOrderUnitEntry changeOrderEntries[] = spec.getChangeOrderUnitEntries();
        boolean changeDetected = false;
        for(int i = 0; i < changeOrderEntries.length; i++)
        {
            EqTypeContractSwapChangeOrderUnitEntry entry = changeOrderEntries[i];
            long orderUnitId = entry.getOrderUnitId();
            OrderUnit origOrderUnit = lookupOrderUnit(orderUnitId, origOrderUnits);
            if(origOrderUnit == null)
                throw new OrderValidationException(OrderManagerErrorCatalog.INVALID_ORDER_UNIT_ID_IN_CHANGE_ORDER);
            double beforeChangeQty = java.lang.Double.isNaN(origOrderUnit.getConfirmedQuantity()) ? origOrderUnit.getQuantity() : origOrderUnit.getConfirmedQuantity();
            double afterChangeQty = entry.getAfterChangeTotalQuantity();
            if(beforeChangeQty != afterChangeQty)
                changeDetected = true;
            if(afterChangeQty > beforeChangeQty)
                throw new OrderValidationException(OrderManagerErrorCatalog.AFTER_CHANGE_QTY_EXCEEDS_ORIG_QTY);
            if(afterChangeQty < origOrderUnit.getExecutedQuantity())
                throw new OrderValidationException(OrderManagerErrorCatalog.AFTER_CHANGE_QTY_BELOW_EXECD_QTY);
            EqTypeSettleContractOrderEntry afterChangeSettleContracts[] = (EqTypeSettleContractOrderEntry[])entry.getAfterChangeSettleContractOrderEntries().clone();
            Arrays.sort(afterChangeSettleContracts);
            List eqtypeContractClosingSpecRows = PersistenceManagerImpl.getEqTypeClosingContractSpecRows(origOrderUnit.getOrderUnitId(), "contract_id");
            if(eqtypeContractClosingSpecRows.size() != afterChangeSettleContracts.length)
                throw new OrderValidationException(OrderManagerErrorCatalog.INVALID_SETTLE_CONTRACT_CHANGE_SPEC);
            for(int k = 0; k < afterChangeSettleContracts.length; k++)
            {
                EqTypeSettleContractOrderEntry e = afterChangeSettleContracts[k];
                EqtypeClosingContractSpecRow r = (EqtypeClosingContractSpecRow)eqtypeContractClosingSpecRows.get(k);
                if(r.getContractId() != e.getContractId())
                    throw new OrderValidationException(OrderManagerErrorCatalog.INVALID_SETTLE_CONTRACT_CHANGE_SPEC);
                if(e.getQuantity() < r.getExecutedQuantity())
                    throw new OrderValidationException(OrderManagerErrorCatalog.AFTER_CHANGE_QTY_BELOW_EXECD_QTY);
                if(e.getQuantity() != r.getQuantity())
                    changeDetected = true;
                if(!com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils.Double.isZero(e.getQuantity()))
                    validateQuantity(e.getQuantity());
            }

            validateQuantity(origOrderUnit.getTradedProduct().getProduct(), afterChangeQty);
        }

        if(!changeDetected)
            throw new OrderValidationException(OrderManagerErrorCatalog.NO_CHANGES_FOUND);
        else
            return;
    }

    public void validateExecutionConditionType(EqTypeTradedProduct eqtypetradedproduct, EqTypeExecutionConditionType eqtypeexecutionconditiontype)
        throws OrderValidationException
    {
    }

    public void validatePerNewCashBasedOrderLimits(EqTypeTradedProduct tradedProduct, double qty, double limitPrice, boolean isMarketOrder, boolean isBuySide)
        throws OrderValidationException
    {
        if(!isBuySide)
            return;
        if(qty > EqTypeServerConfigConstants.getMaxPerBuyOrderQuantity())
            throw new OrderValidationException(OrderManagerErrorCatalog.QTY_EXCEEDED_PER_ORDER_LIMIT);
        else
            return;
    }

    public void validatePerOpenMarginContractOrderLimits(EqTypeTradedProduct tradedProduct, double qty, double limitPrice, boolean isMarketOrder, boolean isLong)
        throws OrderValidationException
    {
        if(qty > EqTypeServerConfigConstants.getMaxPerBuyOrderQuantity())
            throw new OrderValidationException(OrderManagerErrorCatalog.QTY_EXCEEDED_PER_ORDER_LIMIT);
        else
            return;
    }

    public void validatePerOrderMaxLots(EqTypeTradedProduct tproduct, double qty)
        throws OrderValidationException
    {
        Product product = tproduct.getProduct();
        double maxLots = tproduct.getMarket().getMaxNumberOfLotsPerOrder(product.getProductType());
        double specifiedLots = qty / product.getLotSize();
        if(specifiedLots > maxLots)
            throw new OrderValidationException(OrderManagerErrorCatalog.INVALID_QTY_EXCEEDS_PER_ORDER_MAX_LOTS);
        else
            return;
    }

    public Contract validateSettleContractEntries(EqTypeSettleContractOrderEntry entries[])
        throws OrderValidationException
    {
        if(entries.length == 0)
            throw new OrderValidationException(OrderManagerErrorCatalog.NO_SETTLE_CONTRACT_ENTRIES_SPECIFIED);
        EqTypePositionManager eqPosMgr = Utils.getPositionManager();
        EqTypeProductManager eqPrdtMgr = Utils.getProductManager();
        Contract firstContract;
        try
        {
            firstContract = eqPosMgr.getContract(entries[0].getContractId());
        }
        catch(NotFoundException nfe)
        {
            throw new OrderValidationException(OrderManagerErrorCatalog.SETTLE_CONTRACT_ORDER_INVALID_CONTRACT_ID);
        }
        boolean isLong = firstContract.isLong();
        EqTypeTradedProduct tproduct;
        try
        {
            tproduct = (EqTypeTradedProduct)eqPrdtMgr.getTradedProduct(firstContract.getProduct().getProductId(), firstContract.getMarketId());
        }
        catch(NotFoundException nfe)
        {
            String errMsg = "Could not find traded product specified in contract id :" + firstContract.getContractId();
            m_log.error(errMsg, nfe);
            throw new RuntimeSystemException(errMsg, nfe);
        }
        long firstProductId = tproduct.getProduct().getProductId();
        long firstMarketId = tproduct.getMarket().getMarketId();
        for(int i = 0; i < entries.length; i++)
        {
            EqTypeSettleContractOrderEntry e = entries[i];
            try
            {
                Contract contract = i != 0 ? eqPosMgr.getContract(e.getContractId()) : firstContract;
                validateQuantity(e.getQuantity());
                boolean isTypeSame = !(isLong ^ contract.isLong());
                if(!isTypeSame)
                    throw new OrderValidationException(OrderManagerErrorCatalog.MIXED_SETTLE_CONTRACT_ENTRIES);
                if(contract.getProduct().getProductId() != firstProductId || contract.getMarketId() != firstMarketId)
                    throw new OrderValidationException(OrderManagerErrorCatalog.MIXED_PRODUCTS_IN_SETTLE_CONTRACT_ENTRIES);
                double availableQty = contract.getQuantity() - contract.getLockedQuantity();
                if(e.getQuantity() > availableQty)
                    throw new OrderValidationException(OrderManagerErrorCatalog.SETTLE_CONTRACT_ORDER_INVALID_QUANTITY);
            }
            catch(NotFoundException nfe)
            {
                throw new OrderValidationException(OrderManagerErrorCatalog.SETTLE_CONTRACT_ORDER_INVALID_CONTRACT_ID);
            }
        }

        return firstContract;
    }

    public void validateSwappableAssetQuantity(SubAccount subAccount, TradedProduct tradedProduct, double quantityOrdered)
        throws OrderValidationException
    {
        EqTypePositionManager posMgr = Utils.getPositionManager();
        try
        {
            Asset asset = posMgr.getAsset(subAccount, (EqTypeProduct)tradedProduct.getProduct());
            double qty = asset.getQuantity();
            double lockedQty = asset.getLockedQuantity();
            if(quantityOrdered > qty - lockedQty)
                throw new OrderValidationException(OrderManagerErrorCatalog.INSUFFICIENT_ASSET_QUANTITY_FOR_SWAP);
            else
                return;
        }
        catch(NotFoundException nfe)
        {
            throw new OrderValidationException(OrderManagerErrorCatalog.ASSET_NOT_AVAILABLE_FOR_SWAP);
        }
    }

    public void validateMiniStockProduct(EqTypeTradedProduct tproduct)
        throws OrderValidationException
    {
        if(!tproduct.isMiniStockTradable())
            throw new OrderValidationException(OrderManagerErrorCatalog.XB_MINI_STOCK_TRADING_NOT_ALLOWED);
        else
            return;
    }

    public void validateMiniStockQuantity(EqTypeTradedProduct tproduct, double qty)
        throws OrderValidationException
    {
        validateQuantity(qty);
        EqTypeProduct eqproduct = (EqTypeProduct)tproduct.getProduct();
        if(qty >= eqproduct.getLotSize())
            throw new OrderValidationException(OrderManagerErrorCatalog.XB_MINI_STOCK_MAX_QTY_EXCEEDED);
        try
        {
            checkLotSize(eqproduct.getMiniStockLotSize(), qty);
        }
        catch(OrderValidationException ove)
        {
            throw new OrderValidationException(OrderManagerErrorCatalog.XB_MINI_STOCK_LOT_SIZE_ERROR);
        }
    }

    protected TradingModule getThisTradingModule()
    {
        return GtlUtils.getTradingModule("eqtype");
    }

    private static final Logit m_log;
    private static final boolean DBG;
    private static EquityProductTypeOrderManagerReusableValidations THIS_INSTANCE = new EquityProductTypeOrderManagerReusableValidations();

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EquityProductTypeOrderManagerReusableValidations.class);
        DBG = m_log.ison();
    }
}
