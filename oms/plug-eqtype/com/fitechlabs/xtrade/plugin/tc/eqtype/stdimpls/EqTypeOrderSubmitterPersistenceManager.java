// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeOrderSubmitterPersistenceManager.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.*;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.FillOrderUnitSpec;
import java.text.SimpleDateFormat;
import java.util.*;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls:
//            Utils, EqTypeOrderManagerImpl

public abstract class EqTypeOrderSubmitterPersistenceManager
{

    protected EqTypeOrderSubmitterPersistenceManager()
    {
    }

    public List getEqTypeClosingContractSpecRows(long orderUnitId, String orderBy)
    {
        Object bv[];
        QueryProcessor qp;
        String where = "order_unit_id = ?";
        bv = (new Object[] {
            new Long(orderUnitId)
        });
        qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(EqtypeClosingContractSpecRow.TYPE, "order_unit_id = ?", orderBy, null, bv);
        DataException de;
        de;
        String msg = "Exception while getting eqtype_closing_contract_spec for order_unit_id : " + orderUnitId;
        m_log.error(msg, de);
        throw new RuntimeSystemException(msg, de);
    }

    protected EqtypeClosingContractSpecRow getEqTypeClosingContractSpecRow(long orderUnitId, long contractId)
    {
        List rows;
        QueryProcessor qp = Processors.getDefaultProcessor();
        String where = "order_unit_id = ? and contract_id = ?";
        Object bv[] = {
            new Long(orderUnitId), new Long(contractId)
        };
        rows = qp.doFindAllQuery(EqtypeClosingContractSpecRow.TYPE, "order_unit_id = ? and contract_id = ?", bv);
        rows.size();
        JVM INSTR lookupswitch 2: default 102
    //                   0: 88
    //                   1: 90;
           goto _L1 _L2 _L3
_L2:
        return null;
_L3:
        return (EqtypeClosingContractSpecRow)rows.get(0);
_L1:
        try
        {
            String msg = "More than one row found in eqtype_closing_contract_spec table with order_unit_id,contract_id = " + orderUnitId + "," + contractId;
            m_log.error(msg);
            throw new RuntimeSystemException(msg);
        }
        catch(DataException de)
        {
            String msg = "Exception while getting eqtype_closing_contract_spec for order_unit_id,contract_id = " + orderUnitId + "," + contractId;
            m_log.error(msg, de);
            throw new RuntimeSystemException(msg, de);
        }
    }

    protected List moveClosingContractSpecsQuantity2ConfirmedQuantity(long orderUnitId)
    {
        return moveClosingContractSpecsQuantity(orderUnitId, true);
    }

    protected List moveClosingContractSpecsConfirmedQuantity2Quantity(long orderUnitId)
    {
        return moveClosingContractSpecsQuantity(orderUnitId, false);
    }

    private List moveClosingContractSpecsQuantity(long orderUnitId, boolean qty2ConfirmedQty)
    {
        List closingContractSpecRows;
        QueryProcessor qp = Processors.getDefaultProcessor();
        java.sql.Timestamp sysTime = GtlUtils.getTradingSystem().getSystemTimestamp();
        closingContractSpecRows = getEqTypeClosingContractSpecRows(orderUnitId, "closing_serial_no");
        int sz = closingContractSpecRows.size();
        BatchedQuery bqs[] = new BatchedQuery[sz];
        for(int k = 0; k < sz; k++)
        {
            EqtypeClosingContractSpecRow eqtypeClosingContractSpecRow = (EqtypeClosingContractSpecRow)closingContractSpecRows.get(k);
            Map changes = new HashMap();
            if(qty2ConfirmedQty)
                changes.put("confirmed_quantity", new Double(eqtypeClosingContractSpecRow.getQuantity()));
            else
                changes.put("quantity", new Double(eqtypeClosingContractSpecRow.getConfirmedQuantity()));
            changes.put("last_updated_timestamp", sysTime);
            bqs[k] = BatchedQuery.createUpdateQuery(eqtypeClosingContractSpecRow.getPrimaryKey(), changes);
        }

        qp.doQueries(1, bqs);
        return closingContractSpecRows;
        DataException de;
        de;
        String msg = "Exception while moving quantity -> confirmed_quantity in closing_contract_spec table for order_unit_id :" + orderUnitId;
        m_log.error(msg, de);
        throw new RuntimeSystemException(msg, de);
    }

    protected void adjustLockedQuantityForNewAssetOrder(EqtypeOrderUnitRow orderUnitRow)
    {
        adjustLockedQuantity(orderUnitRow, ZERO_DOUBLE_VALUE, null, null, 0.0D);
    }

    protected void adjustLockedQuantity(EqtypeOrderUnitRow orderUnitRow, Double beforeModifyQty, Boolean rollbackUsingConfirmedQtyForClosingContracts, List beforeChangeQtyClosingContracts, double fillQty)
    {
        OrderTypeEnum orderType = orderUnitRow.getOrderType();
        boolean isAssetType = false;
        switch(orderType.intValue())
        {
        case 2: // '\002'
        case 102: // 'f'
            isAssetType = true;
            break;

        case 5: // '\005'
        case 6: // '\006'
        case 7: // '\007'
        case 8: // '\b'
            isAssetType = false;
            break;

        default:
            return;
        }
        if(isAssetType)
            adjustLockedQuantityForEquitySell(orderUnitRow, beforeModifyQty.doubleValue(), fillQty);
        else
        if(rollbackUsingConfirmedQtyForClosingContracts != null || !com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils.Double.isZero(fillQty))
        {
            List currentClosingContractSpecRows = getEqTypeClosingContractSpecRows(orderUnitRow.getOrderUnitId(), "closing_serial_no");
            adjustLockedQuantityForMarginContractCloseOrSwap(orderUnitRow, rollbackUsingConfirmedQtyForClosingContracts, beforeChangeQtyClosingContracts, fillQty, currentClosingContractSpecRows);
        }
    }

    protected void adjustLockedQuantityForMarginContractCloseOrSwap(EqtypeOrderUnitRow orderUnitRow, Boolean rollbackUsingConfirmedQtyForClosingContracts, List beforeChangeQtyClosingContracts, double fillQty, List closingContractSpecs)
    {
        OrderTypeEnum orderType = orderUnitRow.getOrderType();
        OrderStatusEnum orderStatus = orderUnitRow.getOrderStatus();
        Map beforeChangeQtyClosingContractsMap = null;
        if(beforeChangeQtyClosingContracts != null)
        {
            beforeChangeQtyClosingContractsMap = new HashMap();
            int size = beforeChangeQtyClosingContracts.size();
            for(int i = 0; i < size; i++)
            {
                EqtypeClosingContractSpecRow r = (EqtypeClosingContractSpecRow)beforeChangeQtyClosingContracts.get(i);
                beforeChangeQtyClosingContractsMap.put(r.getPrimaryKey(), r);
            }

        }
        if(OrderTypeEnum.SWAP_MARGIN_SHORT.equals(orderUnitRow.getOrderType()))
        {
            double beforeModifyQtyForAsset = 0.0D;
            if(beforeChangeQtyClosingContracts != null)
            {
                int size = beforeChangeQtyClosingContracts.size();
                for(int i = 0; i < size; i++)
                {
                    EqtypeClosingContractSpecRow eqtypeClosingContractSpecRow = (EqtypeClosingContractSpecRow)beforeChangeQtyClosingContracts.get(i);
                    beforeModifyQtyForAsset += eqtypeClosingContractSpecRow.getConfirmedQuantityIsNull() ? eqtypeClosingContractSpecRow.getQuantity() : eqtypeClosingContractSpecRow.getConfirmedQuantity();
                }

            }
            adjustLockedQuantityForEquitySell(orderUnitRow, beforeModifyQtyForAsset, fillQty);
        }
        double lockedQtyTobeAdjustedDataTable[][] = new double[closingContractSpecs.size()][2];
        for(int i = 0; i < lockedQtyTobeAdjustedDataTable.length; i++)
            lockedQtyTobeAdjustedDataTable[i][0] = (0.0D / 0.0D);

        double remainingFillQtyToBeAdjusted = fillQty;
        boolean isProcessingFill = fillQty > 0.0D;
        int size = closingContractSpecs.size();
        for(int i = 0; i < size; i++)
        {
            EqtypeClosingContractSpecRow r = (EqtypeClosingContractSpecRow)closingContractSpecs.get(i);
            lockedQtyTobeAdjustedDataTable[i][0] = r.getContractId();
            lockedQtyTobeAdjustedDataTable[i][1] = 0.0D;
            double maxExecutableQty = r.getConfirmedQuantity() - r.getExecutedQuantity();
            if(isProcessingFill)
            {
                if(com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils.Double.isZero(remainingFillQtyToBeAdjusted))
                    break;
                if(maxExecutableQty > 0.0D && !com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils.Double.isZero(maxExecutableQty))
                {
                    lockedQtyTobeAdjustedDataTable[i][1] = Math.min(maxExecutableQty, remainingFillQtyToBeAdjusted) * -1D;
                    remainingFillQtyToBeAdjusted -= Math.abs(lockedQtyTobeAdjustedDataTable[i][1]);
                }
                continue;
            }
            if(OrderExpirationStatusEnum.EXPIRED.equals(orderUnitRow.getExpirationStatus()) || OrderExpirationStatusEnum.INVALIDATED_BY_MKT.equals(orderUnitRow.getExpirationStatus()))
            {
                lockedQtyTobeAdjustedDataTable[i][1] = (r.getConfirmedQuantityIsNull() ? r.getQuantity() : maxExecutableQty) * -1D;
                continue;
            }
            switch(orderStatus.intValue())
            {
            case 1: // '\001'
            {
                if(beforeChangeQtyClosingContractsMap != null)
                {
                    EqtypeClosingContractSpecRow beforeChangeRow = (EqtypeClosingContractSpecRow)beforeChangeQtyClosingContractsMap.get(r.getPrimaryKey());
                    double rollbackQty = beforeChangeRow.getQuantity();
                    lockedQtyTobeAdjustedDataTable[i][1] = -1D * rollbackQty + r.getQuantity();
                } else
                {
                    lockedQtyTobeAdjustedDataTable[i][1] = r.getQuantity();
                }
                break;
            }

            case 7: // '\007'
            {
                EqtypeClosingContractSpecRow beforeChangeRow = (EqtypeClosingContractSpecRow)beforeChangeQtyClosingContractsMap.get(r.getPrimaryKey());
                double adjustQty = Math.max(r.getQuantity(), r.getConfirmedQuantity()) - beforeChangeRow.getQuantity();
                lockedQtyTobeAdjustedDataTable[i][1] = adjustQty;
                break;
            }

            case 11: // '\013'
            {
                EqtypeClosingContractSpecRow beforeChangeRow = (EqtypeClosingContractSpecRow)beforeChangeQtyClosingContractsMap.get(r.getPrimaryKey());
                double adjustQty = r.getQuantity() - Math.max(beforeChangeRow.getQuantity(), beforeChangeRow.getConfirmedQuantity());
                lockedQtyTobeAdjustedDataTable[i][1] = adjustQty;
                break;
            }

            case 14: // '\016'
            {
                lockedQtyTobeAdjustedDataTable[i][1] = (r.getConfirmedQuantityIsNull() ? r.getQuantity() : maxExecutableQty) * -1D;
                break;
            }

            case 6: // '\006'
            {
                lockedQtyTobeAdjustedDataTable[i][1] = (r.getConfirmedQuantityIsNull() ? r.getQuantity() : maxExecutableQty) * -1D;
                break;
            }

            case 2: // '\002'
            case 3: // '\003'
            case 4: // '\004'
            case 5: // '\005'
            case 8: // '\b'
            case 9: // '\t'
            case 10: // '\n'
            case 12: // '\f'
            case 13: // '\r'
            default:
            {
                if(!OrderStatusEnum.MODIFIED.equals(orderStatus) || beforeChangeQtyClosingContractsMap == null)
                    break;
                EqtypeClosingContractSpecRow beforeChangeRow = (EqtypeClosingContractSpecRow)beforeChangeQtyClosingContractsMap.get(r.getPrimaryKey());
                if(!beforeChangeRow.getConfirmedQuantityIsNull())
                {
                    double adjustQty = r.getQuantity() - Math.max(beforeChangeRow.getQuantity(), beforeChangeRow.getConfirmedQuantity());
                    lockedQtyTobeAdjustedDataTable[i][1] = adjustQty;
                } else
                {
                    lockedQtyTobeAdjustedDataTable[i][1] = r.getQuantity() - beforeChangeRow.getQuantity();
                }
                break;
            }
            }
        }

        EqTypePositionManager eqtypePosMgr = Utils.getPositionManager();
        for(int i = 0; i < lockedQtyTobeAdjustedDataTable.length; i++)
        {
            if(java.lang.Double.isNaN(lockedQtyTobeAdjustedDataTable[i][0]))
                continue;
            long contractId = (long)lockedQtyTobeAdjustedDataTable[i][0];
            double lockedQtyTobeAdjusted = lockedQtyTobeAdjustedDataTable[i][1];
            if(com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils.Double.isZero(lockedQtyTobeAdjusted))
                continue;
            try
            {
                Contract contract = eqtypePosMgr.getContract(contractId);
                eqtypePosMgr.updateLockedQuantity(orderUnitRow.getOrderUnitId(), contract, lockedQtyTobeAdjusted);
                continue;
            }
            catch(NotFoundException nfe)
            {
                String msg = "Exception while updating the locked quantity of contract. Contract not found with id:" + contractId;
                m_log.error(msg, nfe);
                throw new RuntimeSystemException(msg, nfe);
            }
        }

    }

    protected void adjustLockedQuantityForEquitySell(EqtypeOrderUnitRow orderUnitRow, double beforeModifyQty, double fillQty)
    {
        OrderTypeEnum orderType = orderUnitRow.getOrderType();
        OrderStatusEnum orderStatus = orderUnitRow.getOrderStatus();
        double lockedQtyTobeAdjusted = 0.0D;
        if(OrderExpirationStatusEnum.EXPIRED.equals(orderUnitRow.getExpirationStatus()) || OrderExpirationStatusEnum.INVALIDATED_BY_MKT.equals(orderUnitRow.getExpirationStatus()))
        {
            if(orderUnitRow.getConfirmedQuantityIsNull())
                lockedQtyTobeAdjusted = (orderUnitRow.getQuantity() - orderUnitRow.getExecutedQuantity()) * -1D;
            else
                lockedQtyTobeAdjusted = (orderUnitRow.getConfirmedQuantity() - orderUnitRow.getExecutedQuantity()) * -1D;
        } else
        {
            switch(orderStatus.intValue())
            {
            case 1: // '\001'
                if(com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils.Double.isZero(beforeModifyQty))
                    lockedQtyTobeAdjusted = orderUnitRow.getQuantity();
                else
                    lockedQtyTobeAdjusted = -1D * beforeModifyQty + orderUnitRow.getQuantity();
                break;

            case 14: // '\016'
                lockedQtyTobeAdjusted = (orderUnitRow.getQuantity() - orderUnitRow.getExecutedQuantity()) * -1D;
                break;

            case 6: // '\006'
                lockedQtyTobeAdjusted = orderUnitRow.getQuantity() * -1D;
                break;

            default:
                if(OrderStatusEnum.MODIFIED.equals(orderStatus) && !com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils.Double.isZero(beforeModifyQty))
                {
                    lockedQtyTobeAdjusted = -1D * beforeModifyQty + orderUnitRow.getQuantity();
                    break;
                }
                if(!com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils.Double.isZero(fillQty) && fillQty > 0.0D)
                    lockedQtyTobeAdjusted = fillQty * -1D;
                break;
            }
        }
        dispatchUpdateLockedQuantity(orderUnitRow.getAccountId(), orderUnitRow.getSubAccountId(), orderUnitRow.getOrderUnitId(), orderUnitRow.getProductId(), lockedQtyTobeAdjusted);
    }

    protected void updateExecQtyForContractClose(EqtypeOrderUnitRow orderUnitRow, double fillQty)
    {
        try
        {
            QueryProcessor qp = Processors.getDefaultProcessor();
            EqTypeSettleContractOrderEntry entries[] = getExecQtyAssignedClosingContractEntries(orderUnitRow, fillQty);
            java.sql.Timestamp sysTime = GtlUtils.getSystemTimestamp();
            List bqs = new ArrayList();
            int size = entries.length;
            for(int i = 0; i < size; i++)
            {
                String where = "order_unit_id  = ? and contract_id = ?";
                Object bv[] = {
                    new Long(orderUnitRow.getOrderUnitId()), new Long(entries[i].getContractId())
                };
                List rows = qp.doFindAllQuery(EqtypeClosingContractSpecRow.TYPE, "order_unit_id  = ? and contract_id = ?", bv);
                if(rows.size() != 1)
                    throw new DataFindException("More than one row or no row found in eqtype_closing_contract_spec table withorder_unit_id, contract_id:" + orderUnitRow.getOrderUnitId() + "," + entries[i].getContractId());
                EqtypeClosingContractSpecRow r = (EqtypeClosingContractSpecRow)rows.get(0);
                Map changes = new HashMap();
                changes.put("last_updated_timestamp", sysTime);
                changes.put("executed_quantity", new Double(entries[i].getQuantity() + r.getExecutedQuantity()));
                bqs.add(BatchedQuery.createUpdateQuery(r.getPrimaryKey(), changes));
            }

            if(bqs.size() > 0)
                qp.doQueries(1, (BatchedQuery[])bqs.toArray(new BatchedQuery[0]));
        }
        catch(DataException de)
        {
            String msg = "Exception while updating the executed_quantity of closing contract spec.";
            m_log.error("Exception while updating the executed_quantity of closing contract spec.", de);
            throw new RuntimeSystemException("Exception while updating the executed_quantity of closing contract spec.", de);
        }
    }

    protected EqTypeSettleContractOrderEntry[] getExecQtyAssignedClosingContractEntries(long orderUnitId, double fillQty)
    {
        EqtypeOrderUnitRow orderUnitRow = EqtypeOrderUnitDao.findRowByPk(orderUnitId);
        return getExecQtyAssignedClosingContractEntries(orderUnitRow, fillQty);
        DataException de;
        de;
        String msg = "Exception while assiging execd qty to each closing contract spec.";
        m_log.error("Exception while assiging execd qty to each closing contract spec.", de);
        throw new RuntimeSystemException("Exception while assiging execd qty to each closing contract spec.", de);
    }

    protected EqTypeSettleContractOrderEntry[] getExecQtyAssignedClosingContractEntries(EqtypeOrderUnitRow orderUnitRow, double fillQty)
    {
        if(!OrderCategEnum.CLOSE_MARGIN.equals(orderUnitRow.getOrderCateg()) && !OrderCategEnum.SWAP_MARGIN.equals(orderUnitRow.getOrderCateg()))
            throw new IllegalArgumentException("Invalid call. order unit is not of close/swap contract.");
        List entries;
        QueryProcessor qp = Processors.getDefaultProcessor();
        double remainingFillQty = fillQty;
        String where = "order_unit_id = ?";
        Object bv[] = {
            new Long(orderUnitRow.getOrderUnitId())
        };
        String orderBy = "closing_serial_no";
        List rows = qp.doFindAllQuery(EqtypeClosingContractSpecRow.TYPE, "order_unit_id = ?", "closing_serial_no", null, bv);
        entries = new ArrayList();
        int size = rows.size();
        for(int i = 0; i < size && remainingFillQty > 0.0D; i++)
        {
            EqtypeClosingContractSpecRow r = (EqtypeClosingContractSpecRow)rows.get(i);
            double maxExecutableQty = r.getQuantity() - r.getExecutedQuantity();
            if(maxExecutableQty > 0.0D && !com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils.Double.isZero(maxExecutableQty))
            {
                double thisExecQty = remainingFillQty < maxExecutableQty ? remainingFillQty : maxExecutableQty;
                remainingFillQty -= thisExecQty;
                EqTypeSettleContractOrderEntry e = new EqTypeSettleContractOrderEntry(r.getContractId(), thisExecQty);
                entries.add(e);
            }
        }

        return (EqTypeSettleContractOrderEntry[])entries.toArray(new EqTypeSettleContractOrderEntry[0]);
        DataException de;
        de;
        String msg = "Exception while updating the executed_quantity of closing contract spec.";
        m_log.error("Exception while updating the executed_quantity of closing contract spec.", de);
        throw new RuntimeSystemException("Exception while updating the executed_quantity of closing contract spec.", de);
    }

    protected EqtypeOrderUnitParams getInitializedOrderUnitParamsForAnyNewOrder(SubAccount subAccount, long orderId, TradedProduct tradedProduct, OrderCategEnum orderCateg, OrderTypeEnum orderType, double limitPrice, double quantity, EqTypeExecutionConditionType execType, Date orderExpDate, TaxTypeEnum taxType, Long traderId)
    {
        long orderUnitId = Utils.getGlobalOrderManager().createNewOrderUnitId();
        java.sql.Timestamp sysTime = GtlUtils.getTradingSystem().getSystemTimestamp();
        MainAccount mainAccount = subAccount.getMainAccount();
        long accountId = mainAccount.getAccountId();
        long subAccountId = subAccount.getSubAccountId();
        long branchId = mainAccount.getBranch().getBranchId();
        EqtypeOrderUnitParams orderUnitParams = new EqtypeOrderUnitParams();
        orderUnitParams.setAccountId(accountId);
        orderUnitParams.setBranchId(branchId);
        orderUnitParams.setCreatedTimestamp(sysTime);
        orderUnitParams.setDeliveryDate(tradedProduct.getDailyDeliveryDate());
        orderUnitParams.setExecutionConditionType(execType);
        orderUnitParams.setLastExecutionSerialNo(0);
        orderUnitParams.setExpirationDate(orderExpDate);
        orderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.OPEN);
        orderUnitParams.setLastUpdatedTimestamp(sysTime);
        orderUnitParams.setMarketId(tradedProduct.getMarket().getMarketId());
        orderUnitParams.setLastOrderActionSerialNo(1);
        orderUnitParams.setOrderCateg(orderCateg);
        orderUnitParams.setOrderId(orderId);
        orderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
        orderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
        orderUnitParams.setOrderType(orderType);
        orderUnitParams.setOrderUnitId(orderUnitId);
        if(!java.lang.Double.isNaN(limitPrice))
            orderUnitParams.setLimitPrice(limitPrice);
        Product product = tradedProduct.getProduct();
        orderUnitParams.setProductId(product.getProductId());
        orderUnitParams.setProductType(product.getProductType());
        orderUnitParams.setQuantity(quantity);
        orderUnitParams.setQuantityType(QuantityTypeEnum.QUANTITY);
        orderUnitParams.setSubAccountId(subAccountId);
        orderUnitParams.setTaxType(taxType);
        orderUnitParams.setTraderId(traderId);
        Date bizDate = GtlUtils.getFinObjectManager().getTradingCalendar(tradedProduct.getTradedProductId()).getCurrentBizDate();
        orderUnitParams.setBizDate(GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(bizDate));
        return orderUnitParams;
    }

    protected EqtypeOrderParams getInitializedEqtypeOrderParamsForAnyNewOrder(SubAccount subAccount, ProductTypeEnum productType, long orderId)
    {
        java.sql.Timestamp sysTime = GtlUtils.getTradingSystem().getSystemTimestamp();
        MainAccount mainAccount = subAccount.getMainAccount();
        long accountId = mainAccount.getAccountId();
        long subAccountId = subAccount.getSubAccountId();
        EqtypeOrderParams orderParams = new EqtypeOrderParams();
        orderParams.setAccountId(accountId);
        orderParams.setCreatedTimestamp(sysTime);
        orderParams.setLastUpdatedTimestamp(sysTime);
        orderParams.setOrderId(orderId);
        orderParams.setSubAccountId(subAccountId);
        orderParams.setProductType(productType);
        if(DBG)
            m_log.debug("created eqtype_order row with values :  :" + orderParams.toString());
        return orderParams;
    }

    public EqtypeOrderUnitParams persistNewCashBasedOrder(SubAccount subAccount, long orderId, EqTypeNewCashBasedOrderSpec spec, EqTypeTradedProduct tradedProduct, boolean isMiniStockOrder)
    {
        EqtypeOrderUnitParams orderUnitParams;
        QueryProcessor qp = Processors.getDefaultProcessor();
        EqtypeOrderParams orderParams = getInitializedEqtypeOrderParamsForAnyNewOrder(subAccount, tradedProduct.getProduct().getProductType(), orderId);
        qp.doInsertQuery(orderParams);
        if(DBG)
            m_log.debug("created eqtype_order row with values :" + orderParams.toString());
        orderUnitParams = getInitializedOrderUnitParamsForAnyNewOrder(subAccount, orderId, tradedProduct, OrderCategEnum.ASSET, spec.isBuyOrder() ? isMiniStockOrder ? OrderTypeEnum.MINI_STOCK_BUY : OrderTypeEnum.EQUITY_BUY : isMiniStockOrder ? OrderTypeEnum.MINI_STOCK_SELL : OrderTypeEnum.EQUITY_SELL, spec.isMarketOrder() ? 0.0D : spec.getLimitPrice(), spec.getQuantity(), spec.getExecConditionType(), spec.getOrderExpDate(), spec.getTaxType(), spec.getTraderIdAsObject());
        EqTypeOrderManagerPersistenceEventInterceptor persistenceInterceptor = EqTypeOrderManagerImpl.getThreadLocalPersistenceEventInterceptor();
        BatchedQuery bq = null;
        if(persistenceInterceptor != null)
        {
            if(DBG)
                m_log.debug(">>>>>>> Persistence interceptor registered. calling for new cash based order.");
            orderUnitParams = persistenceInterceptor.mutate(OrderManagerPersistenceType.INSERT, OrderManagerPersistenceContext.NEW_CASH_BASED_ORDER, orderUnitParams);
            bq = persistenceInterceptor.getQueryToExecute(OrderManagerPersistenceType.INSERT, com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow.class);
        }
        if(bq != null)
        {
            if(DBG)
                m_log.debug("Using the query returned by EqTypeOrderManagerPersistenceEventInterceptor.getQueryToExecute()");
            qp.doQuery(bq);
        } else
        {
            qp.doInsertQuery(orderUnitParams);
        }
        if(DBG)
            m_log.debug("created eqtype_order_unit row with values  :" + orderUnitParams.toString());
        adjustLockedQuantityForNewAssetOrder(orderUnitParams);
        EqtypeOrderActionParams orderActionParams = getInitializedEqTypeOrderActionParams(orderUnitParams, OrderEventTypeEnum.NEW, (0.0D / 0.0D), (0.0D / 0.0D));
        persistOrderAction(OrderManagerPersistenceContext.NEW_CASH_BASED_ORDER, orderActionParams);
        return orderUnitParams;
        DataException de;
        de;
        String msg = "Exception while persisting a new order to DB. :  " + de.getMessage();
        m_log.error(msg, de);
        throw new RuntimeSystemException(msg, de);
    }

    public EqtypeOrderUnitParams persistOpenContractOrder(SubAccount subAccount, long orderId, EqTypeOpenContractOrderSpec spec, EqTypeTradedProduct tradedProduct)
    {
        EqtypeOrderUnitParams orderUnitParams;
        QueryProcessor qp = Processors.getDefaultProcessor();
        EqtypeOrderParams orderParams = getInitializedEqtypeOrderParamsForAnyNewOrder(subAccount, tradedProduct.getProduct().getProductType(), orderId);
        qp.doInsertQuery(orderParams);
        if(DBG)
            m_log.debug("created eqtype_order row with values :" + orderParams.toString());
        orderUnitParams = getInitializedOrderUnitParamsForAnyNewOrder(subAccount, orderId, tradedProduct, OrderCategEnum.OPEN_MARGIN, spec.isLongOrder() ? OrderTypeEnum.MARGIN_LONG : OrderTypeEnum.MARGIN_SHORT, spec.isMarketOrder() ? 0.0D : spec.getLimitPrice(), spec.getQuantity(), spec.getExecConditionType(), spec.getOrderExpDate(), spec.getTaxType(), spec.getTraderIdAsObject());
        EqTypeOrderManagerPersistenceEventInterceptor persistenceInterceptor = EqTypeOrderManagerImpl.getThreadLocalPersistenceEventInterceptor();
        BatchedQuery bq = null;
        if(persistenceInterceptor != null)
        {
            if(DBG)
                m_log.debug(">>>>>>> Persistence interceptor registered. calling for OPEN CONTRACT  order.");
            orderUnitParams = persistenceInterceptor.mutate(OrderManagerPersistenceType.INSERT, OrderManagerPersistenceContext.NEW_OPEN_CONTRACT_ORDER, orderUnitParams);
            bq = persistenceInterceptor.getQueryToExecute(OrderManagerPersistenceType.INSERT, com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow.class);
        }
        if(bq != null)
        {
            if(DBG)
                m_log.debug("Using the query returned by EqTypeOrderManagerPersistenceEventInterceptor.getQueryToExecute()");
            qp.doQuery(bq);
        } else
        {
            qp.doInsertQuery(orderUnitParams);
        }
        if(DBG)
            m_log.debug("created eqtype_order_unit row with values  :" + orderUnitParams.toString());
        EqtypeOrderActionParams orderActionParams = getInitializedEqTypeOrderActionParams(orderUnitParams, OrderEventTypeEnum.NEW, (0.0D / 0.0D), (0.0D / 0.0D));
        persistOrderAction(OrderManagerPersistenceContext.NEW_OPEN_CONTRACT_ORDER, orderActionParams);
        return orderUnitParams;
        DataException de;
        de;
        String msg = "Exception while persisting a new order to DB. :  " + de.getMessage();
        m_log.error(msg, de);
        throw new RuntimeSystemException(msg, de);
    }

    protected EqtypeClosingContractSpecParams[] createEqtypeClosingContractSpec(SubAccount subAccount, long orderId, long orderUnitId, EqTypeSettleContractOrderEntry entries[])
    {
        EqtypeClosingContractSpecParams paramsArray[];
        QueryProcessor qp = Processors.getDefaultProcessor();
        long accountId = subAccount.getMainAccount().getAccountId();
        long subAccountId = subAccount.getSubAccountId();
        java.sql.Timestamp sysTime = GtlUtils.getTradingSystem().getSystemTimestamp();
        BatchedQuery bq[] = new BatchedQuery[entries.length];
        paramsArray = new EqtypeClosingContractSpecParams[entries.length];
        for(int i = 0; i < entries.length; i++)
        {
            EqTypeSettleContractOrderEntry e = entries[i];
            EqtypeClosingContractSpecParams params = new EqtypeClosingContractSpecParams();
            params.setAccountId(accountId);
            params.setSubAccountId(subAccountId);
            params.setClosingContractSpecId(EqtypeClosingContractSpecDao.newPkValue());
            params.setClosingSerialNo(i + 1);
            params.setContractId(e.getContractId());
            params.setCreatedTimestamp(sysTime);
            params.setExecutedQuantity(0.0D);
            params.setLastUpdatedTimestamp(sysTime);
            params.setOrderId(orderId);
            params.setOrderUnitId(orderUnitId);
            params.setQuantity(e.getQuantity());
            bq[i] = BatchedQuery.createInsertQuery(params);
            paramsArray[i] = params;
        }

        qp.doQueries(1, bq);
        return paramsArray;
        DataException de;
        de;
        String msg = "Exception while persisting a new order to DB. :  " + de.getMessage();
        m_log.error(msg, de);
        throw new RuntimeSystemException(msg, de);
    }

    public EqtypeOrderUnitParams persistSwapContractOrder(SubAccount subAccount, long orderId, EqTypeSwapContractOrderSpec spec, EqTypeTradedProduct tradedProduct)
    {
        EqtypeOrderUnitParams orderUnitParams;
        QueryProcessor qp = Processors.getDefaultProcessor();
        EqtypeOrderParams orderParams = getInitializedEqtypeOrderParamsForAnyNewOrder(subAccount, tradedProduct.getProduct().getProductType(), orderId);
        qp.doInsertQuery(orderParams);
        if(DBG)
            m_log.debug("created eqtype_order row with values :" + orderParams.toString());
        EqTypePositionManager eqposMgr = Utils.getPositionManager();
        Contract contract = eqposMgr.getContract(spec.getSettleContractOrderEntries()[0].getContractId());
        OrderTypeEnum orderType = contract.isLong() ? OrderTypeEnum.SWAP_MARGIN_LONG : OrderTypeEnum.SWAP_MARGIN_SHORT;
        orderUnitParams = getInitializedOrderUnitParamsForAnyNewOrder(subAccount, orderId, tradedProduct, OrderCategEnum.SWAP_MARGIN, orderType, (0.0D / 0.0D), spec.getTotalQuantity(), EqTypeExecutionConditionType.NONE, EqTypeSwapContractOrderSpec.MAX_ORDER_EXP_DATE, spec.getTaxType(), spec.getTraderIdAsObject());
        EqTypeOrderManagerPersistenceEventInterceptor persistenceInterceptor = EqTypeOrderManagerImpl.getThreadLocalPersistenceEventInterceptor();
        BatchedQuery bq = null;
        if(persistenceInterceptor != null)
        {
            if(DBG)
                m_log.debug(">>>>>>> Persistence interceptor registered. calling for SWAP CONTRACT  order.");
            orderUnitParams = persistenceInterceptor.mutate(OrderManagerPersistenceType.INSERT, OrderManagerPersistenceContext.NEW_SWAP_CONTRACT_ORDER, orderUnitParams);
            bq = persistenceInterceptor.getQueryToExecute(OrderManagerPersistenceType.INSERT, com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow.class);
        }
        if(bq != null)
        {
            if(DBG)
                m_log.debug("Using the query returned by EqTypeOrderManagerPersistenceEventInterceptor.getQueryToExecute()");
            qp.doQuery(bq);
        } else
        {
            qp.doInsertQuery(orderUnitParams);
        }
        long orderUnitId = orderUnitParams.getOrderUnitId();
        if(DBG)
            m_log.debug("created eqtype_order_unit row with values  :" + orderUnitParams.toString());
        EqtypeOrderActionParams orderActionParams = getInitializedEqTypeOrderActionParams(orderUnitParams, OrderEventTypeEnum.NEW, (0.0D / 0.0D), (0.0D / 0.0D));
        persistOrderAction(OrderManagerPersistenceContext.NEW_SWAP_CONTRACT_ORDER, orderActionParams);
        EqtypeClosingContractSpecParams closingContractSpecs[] = createEqtypeClosingContractSpec(subAccount, orderId, orderUnitId, spec.getSettleContractOrderEntries());
        adjustLockedQuantityForMarginContractCloseOrSwap(orderUnitParams, null, null, 0.0D, Arrays.asList(closingContractSpecs));
        return orderUnitParams;
        DataException de;
        de;
        String msg = "Exception while persisting a new order to DB. :  " + de.getMessage();
        m_log.error(msg, de);
        throw new RuntimeSystemException(msg, de);
        NotFoundException nfe;
        nfe;
        String msg = "contract not found. contract id : " + spec.getSettleContractOrderEntries()[0].getContractId();
        m_log.error(msg, nfe);
        throw new RuntimeSystemException(msg, nfe);
    }

    public EqtypeOrderUnitParams persistSettleContractOrder(SubAccount subAccount, long orderId, EqTypeSettleContractOrderSpec spec, EqTypeTradedProduct tradedProduct)
    {
        EqtypeOrderUnitParams orderUnitParams;
        QueryProcessor qp = Processors.getDefaultProcessor();
        EqtypeOrderParams orderParams = getInitializedEqtypeOrderParamsForAnyNewOrder(subAccount, tradedProduct.getProduct().getProductType(), orderId);
        qp.doInsertQuery(orderParams);
        if(DBG)
            m_log.debug("created eqtype_order row with values :" + orderParams.toString());
        EqTypePositionManager eqposMgr = Utils.getPositionManager();
        Contract contract = eqposMgr.getContract(spec.getSettleContractOrderEntries()[0].getContractId());
        OrderTypeEnum orderType = contract.isLong() ? OrderTypeEnum.CLOSE_MARGIN_LONG : OrderTypeEnum.CLOSE_MARGIN_SHORT;
        orderUnitParams = getInitializedOrderUnitParamsForAnyNewOrder(subAccount, orderId, tradedProduct, OrderCategEnum.CLOSE_MARGIN, orderType, spec.isMarketOrder() ? 0.0D : spec.getLimitPrice(), spec.getTotalQuantity(), spec.getExecConditionType(), spec.getOrderExpDate(), spec.getTaxType(), spec.getTraderIdAsObject());
        EqTypeOrderManagerPersistenceEventInterceptor persistenceInterceptor = EqTypeOrderManagerImpl.getThreadLocalPersistenceEventInterceptor();
        BatchedQuery bq = null;
        if(persistenceInterceptor != null)
        {
            if(DBG)
                m_log.debug(">>>>>>> Persistence interceptor registered. calling for SETTLE CONTRACT  order.");
            orderUnitParams = persistenceInterceptor.mutate(OrderManagerPersistenceType.INSERT, OrderManagerPersistenceContext.NEW_SETTLE_CONTRACT_ORDER, orderUnitParams);
            bq = persistenceInterceptor.getQueryToExecute(OrderManagerPersistenceType.INSERT, com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow.class);
        }
        if(bq != null)
        {
            if(DBG)
                m_log.debug("Using the query returned by EqTypeOrderManagerPersistenceEventInterceptor.getQueryToExecute()");
            qp.doQuery(bq);
        } else
        {
            qp.doInsertQuery(orderUnitParams);
        }
        long orderUnitId = orderUnitParams.getOrderUnitId();
        if(DBG)
            m_log.debug("created eqtype_order_unit row with values  :" + orderUnitParams.toString());
        EqtypeOrderActionParams orderActionParams = getInitializedEqTypeOrderActionParams(orderUnitParams, OrderEventTypeEnum.NEW, (0.0D / 0.0D), (0.0D / 0.0D));
        persistOrderAction(OrderManagerPersistenceContext.NEW_SETTLE_CONTRACT_ORDER, orderActionParams);
        EqtypeClosingContractSpecParams closingContractSpecs[] = createEqtypeClosingContractSpec(subAccount, orderId, orderUnitId, spec.getSettleContractOrderEntries());
        adjustLockedQuantityForMarginContractCloseOrSwap(orderUnitParams, null, null, 0.0D, Arrays.asList(closingContractSpecs));
        return orderUnitParams;
        DataException de;
        de;
        String msg = "Exception while persisting a new order to DB. :  " + de.getMessage();
        m_log.error(msg, de);
        throw new RuntimeSystemException(msg, de);
        NotFoundException nfe;
        nfe;
        String msg = "contract not found. contract id : " + spec.getSettleContractOrderEntries()[0].getContractId();
        m_log.error(msg, nfe);
        throw new RuntimeSystemException(msg, nfe);
    }

    public void handleCancelAcceptedOrderUpdates(long orderId)
    {
        applyNewOrderStatus(OrderManagerPersistenceContext.CANCEL_ORDER_ACCEPTED, orderId, OrderStatusEnum.CANCEL_ACCEPTED, OrderEventTypeEnum.CANCEL);
    }

    public void handleCancelRejectedOrderUpdates(long orderId)
    {
        applyNewOrderStatus(OrderManagerPersistenceContext.CANCEL_ORDER_REJECTED_BY_MKT, orderId, OrderStatusEnum.NOT_CANCELLED, OrderEventTypeEnum.REJECTED_BY_MKT);
    }

    public void handleCancellingOrderUpdates(long orderId)
    {
        applyNewOrderStatus(OrderManagerPersistenceContext.CANCEL_ORDER_SENT_TO_MARKET, orderId, OrderStatusEnum.CANCELLING, OrderEventTypeEnum.CANCEL);
    }

    public void handleCancelledOrderUpdates(long orderId)
    {
        applyNewOrderStatus(OrderManagerPersistenceContext.CANCEL_ORDER_CONFIRMED_BY_MKT, orderId, OrderStatusEnum.CANCELLED, OrderEventTypeEnum.CANCEL);
    }

    public void handleOrderInvalidatedUpdates(long orderId)
    {
        applyNewOrderStatus(OrderManagerPersistenceContext.ORDER_INVALIDATED_BY_MKT, orderId, OrderStatusEnum.UNDEFINED, OrderEventTypeEnum.EXPIRE);
    }

    public void handleUndoOrderInvalidationUpdates(long orderId)
    {
        try
        {
            QueryProcessor qp = Processors.getDefaultProcessor();
            EqtypeOrderRow orderRow = EqtypeOrderDao.findRowByPk(orderId);
            List orderUnits = EqtypeOrderUnitDao.findRowsByOrderId(orderRow);
            java.sql.Timestamp sysTime = GtlUtils.getSystemTimestamp();
            Map changes = new HashMap();
            changes.put("last_updated_timestamp", sysTime);
            qp.doUpdateQuery(orderRow.getPrimaryKey(), changes);
            int size = orderUnits.size();
            for(int i = 0; i < size; i++)
            {
                EqtypeOrderUnitRow orderUnitRow = (EqtypeOrderUnitRow)orderUnits.get(i);
                EqtypeOrderUnitRow orderUnitRowBeforeChangeImage = orderUnitRow;
                if(!OrderOpenStatusEnum.CLOSED.equals(orderUnitRowBeforeChangeImage.getOrderOpenStatus()))
                {
                    String msg = "Invalid undoOrderInvalidation. OrderUnit with id : " + orderUnitRow.getOrderUnitId() + " , is not in CLOSED state. Ignoring the undoOrderInvalidation message. ";
                    m_log.warn(msg);
                    throw new RuntimeSystemException(msg);
                }
                if(!OrderExpirationStatusEnum.INVALIDATED_BY_MKT.equals(orderUnitRowBeforeChangeImage.getExpirationStatus()))
                {
                    String msg = "Invalid undoOrderInvalidation. OrderUnit with id : " + orderUnitRow.getOrderUnitId() + " , is not invalidated by market, hence can't be undone. Ignoring the undoOrderInvalidation message. ";
                    m_log.warn(msg);
                    throw new RuntimeSystemException(msg);
                }
                EqtypeOrderUnitParams orderUnitParams = new EqtypeOrderUnitParams(orderUnitRow);
                orderUnitParams.setLastUpdatedTimestamp(sysTime);
                int lastOrderActionSerNo = orderUnitParams.getLastOrderActionSerialNo();
                orderUnitParams.setLastOrderActionSerialNo(lastOrderActionSerNo + 1);
                orderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
                orderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.OPEN);
                BatchedQuery bq = null;
                EqTypeOrderManagerPersistenceEventInterceptor persistenceInterceptor = EqTypeOrderManagerImpl.getThreadLocalPersistenceEventInterceptor();
                if(persistenceInterceptor != null)
                {
                    orderUnitParams = persistenceInterceptor.mutate(OrderManagerPersistenceType.UPDATE, OrderManagerPersistenceContext.UNDO_INVALIDATION_BY_MKT, orderUnitParams);
                    bq = persistenceInterceptor.getQueryToExecute(OrderManagerPersistenceType.UPDATE, com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow.class);
                }
                if(bq != null)
                    qp.doQuery(bq);
                else
                    qp.doUpdateQuery(orderUnitParams);
                if(DBG)
                    m_log.debug("Updated order unit row. new  values : " + orderUnitParams.toString());
                if(OrderCategEnum.ASSET.equals(orderUnitRowBeforeChangeImage.getOrderCateg()) && SideEnum.SELL.equals(SideEnum.getSide(orderUnitRowBeforeChangeImage.getOrderType())) || OrderTypeEnum.SWAP_MARGIN_SHORT.equals(orderUnitRowBeforeChangeImage.getOrderType()))
                {
                    double lockedQtyTobeAdjusted;
                    if(com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils.Double.isZero(orderUnitRowBeforeChangeImage.getConfirmedQuantity()))
                        lockedQtyTobeAdjusted = orderUnitRowBeforeChangeImage.getQuantity();
                    else
                        lockedQtyTobeAdjusted = orderUnitRowBeforeChangeImage.getConfirmedQuantity() - orderUnitRowBeforeChangeImage.getExecutedQuantity();
                    dispatchUpdateLockedQuantity(orderUnitRowBeforeChangeImage.getAccountId(), orderUnitRowBeforeChangeImage.getSubAccountId(), orderUnitRowBeforeChangeImage.getOrderUnitId(), orderUnitRowBeforeChangeImage.getProductId(), lockedQtyTobeAdjusted);
                }
                if(OrderCategEnum.CLOSE_MARGIN.equals(orderUnitRowBeforeChangeImage.getOrderCateg()) || OrderCategEnum.SWAP_MARGIN.equals(orderUnitRowBeforeChangeImage.getOrderCateg()))
                {
                    List currentClosingContractSpecRows = getEqTypeClosingContractSpecRows(orderUnitRow.getOrderUnitId(), "closing_serial_no");
                    EqTypePositionManager eqtypePosMgr = Utils.getPositionManager();
                    for(int k = 0; k < currentClosingContractSpecRows.size(); k++)
                    {
                        EqtypeClosingContractSpecRow closingContractRow = (EqtypeClosingContractSpecRow)currentClosingContractSpecRows.get(k);
                        long contractId = closingContractRow.getContractId();
                        try
                        {
                            Contract contract = eqtypePosMgr.getContract(contractId);
                            double lockedQtyTobeAdjusted;
                            if(closingContractRow.getConfirmedQuantityIsNull())
                                lockedQtyTobeAdjusted = closingContractRow.getQuantity();
                            else
                                lockedQtyTobeAdjusted = closingContractRow.getConfirmedQuantity() - closingContractRow.getExecutedQuantity();
                            if(!com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils.Double.isZero(lockedQtyTobeAdjusted))
                                eqtypePosMgr.updateLockedQuantity(orderUnitRow.getOrderUnitId(), contract, lockedQtyTobeAdjusted);
                            continue;
                        }
                        catch(NotFoundException nfe)
                        {
                            String msg = "Exception while updating the locked quantity of contract. Contract not found with id:" + contractId;
                            m_log.error(msg, nfe);
                            throw new RuntimeSystemException(msg, nfe);
                        }
                    }

                }
                EqtypeOrderActionParams orderActionParams = getInitializedEqTypeOrderActionParams(orderUnitParams, OrderEventTypeEnum.UNDO_INVALIDATION_BY_MKT, (0.0D / 0.0D), (0.0D / 0.0D));
                persistOrderAction(OrderManagerPersistenceContext.UNDO_INVALIDATION_BY_MKT, orderActionParams);
            }

            return;
        }
        catch(DataException de)
        {
            String msg = "Exception while handling order related updates  :  " + de.getMessage();
            m_log.error(msg, de);
            throw new RuntimeSystemException(msg, de);
        }
    }

    public void handleUndoOrderFillUpdates(long orderId, long execId)
    {
        try
        {
            QueryProcessor qp = Processors.getDefaultProcessor();
            EqtypeOrderRow orderRow = EqtypeOrderDao.findRowByPk(orderId);
            List orderUnits = EqtypeOrderUnitDao.findRowsByOrderId(orderRow);
            java.sql.Timestamp sysTime = GtlUtils.getSystemTimestamp();
            Map changes = new HashMap();
            changes.put("last_updated_timestamp", sysTime);
            qp.doUpdateQuery(orderRow.getPrimaryKey(), changes);
            int size = orderUnits.size();
            for(int i = 0; i < size; i++)
            {
                EqtypeOrderUnitRow orderUnitRow = (EqtypeOrderUnitRow)orderUnits.get(i);
                EqtypeOrderUnitRow ouRowBeforeChangeImage = orderUnitRow;
                if(com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils.Double.isZero(ouRowBeforeChangeImage.getExecutedQuantity()))
                {
                    String msg = "Invalid undoOrderFill. Order ID : " + orderId + ", orderExecutionId:" + execId + " , EqTypeOrderUnitRow indicates no executions. ";
                    m_log.warn(msg);
                    throw new RuntimeSystemException(msg);
                }
                EqtypeOrderExecutionRow orderExecRow = EqtypeOrderExecutionDao.findRowByOrderExecutionId(execId);
                if(orderExecRow == null)
                {
                    String msg = "OrderExecutionId is invalid. no eqtype_order_execution_row found with orderExecutionId:" + execId;
                    m_log.warn(msg);
                    throw new RuntimeSystemException(msg);
                }
                if(BooleanEnum.TRUE.equals(orderExecRow.getDeleteFlag()))
                {
                    String msg = "eqtype_order_execution row corresponding to execId:" + execId + " contains deleted_flag=TRUE already, hence undoFill can't be done.";
                    m_log.warn(msg);
                    throw new RuntimeSystemException(msg);
                }
                if(orderExecRow.getExecQuantity() > ouRowBeforeChangeImage.getExecutedQuantity())
                {
                    String msg = "Invalid eqtype_order_unit_row.getExecutedQuantity.order_unit_row.getExecutedQuantity seems to be less than eqtype_order_execution row's getExecQuantity. with orderExecId:" + execId;
                    m_log.warn(msg);
                    throw new RuntimeSystemException(msg);
                }
                double undoExecQty = orderExecRow.getExecQuantity();
                double undoExecPrice = orderExecRow.getExecPrice();
                double undoExecAmount = getFillAmount(ouRowBeforeChangeImage, orderExecRow.getExecPrice(), undoExecQty);
                EqtypeOrderUnitParams orderUnitParams = new EqtypeOrderUnitParams(ouRowBeforeChangeImage);
                orderUnitParams.setLastUpdatedTimestamp(sysTime);
                int lastOrderActionSerNo = orderUnitParams.getLastOrderActionSerialNo();
                orderUnitParams.setLastOrderActionSerialNo(lastOrderActionSerNo + 1);
                boolean isBeforeChangeImageFullyExecuted = com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils.Double.isEqual(ouRowBeforeChangeImage.getQuantity(), ouRowBeforeChangeImage.getExecutedQuantity());
                boolean isBeforeChangeImageClosed = OrderOpenStatusEnum.CLOSED.equals(ouRowBeforeChangeImage.getOrderOpenStatus());
                boolean reopenOrder = isBeforeChangeImageClosed && isBeforeChangeImageFullyExecuted;
                if(reopenOrder)
                    orderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
                boolean adjustLockedQty = isBeforeChangeImageFullyExecuted || !isBeforeChangeImageClosed;
                if(!OrderCategEnum.SWAP_MARGIN.equals(ouRowBeforeChangeImage.getOrderCateg()))
                    orderUnitParams.setExecutedAmount(ouRowBeforeChangeImage.getExecutedAmount() - undoExecAmount);
                double afterUndoExecQty = ouRowBeforeChangeImage.getExecutedQuantity() - undoExecQty;
                if(com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils.Double.isZero(afterUndoExecQty))
                    orderUnitParams.setExecutedQuantity(null);
                else
                    orderUnitParams.setExecutedQuantity(afterUndoExecQty);
                BatchedQuery bq = null;
                EqTypeOrderManagerPersistenceEventInterceptor persistenceInterceptor = EqTypeOrderManagerImpl.getThreadLocalPersistenceEventInterceptor();
                if(persistenceInterceptor != null)
                {
                    orderUnitParams = persistenceInterceptor.mutate(OrderManagerPersistenceType.UPDATE, OrderManagerPersistenceContext.UNDO_EXECUTION, orderUnitParams);
                    bq = persistenceInterceptor.getQueryToExecute(OrderManagerPersistenceType.UPDATE, com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow.class);
                }
                if(bq != null)
                    qp.doQuery(bq);
                else
                    qp.doUpdateQuery(orderUnitParams);
                if(DBG)
                    m_log.debug("Updated order unit row. new  values : " + orderUnitParams.toString());
                if((OrderCategEnum.ASSET.equals(ouRowBeforeChangeImage.getOrderCateg()) && SideEnum.SELL.equals(SideEnum.getSide(ouRowBeforeChangeImage.getOrderType())) || OrderTypeEnum.SWAP_MARGIN_SHORT.equals(ouRowBeforeChangeImage.getOrderType())) && adjustLockedQty)
                {
                    double lockedQtyTobeAdjusted = undoExecQty;
                    dispatchUpdateLockedQuantity(ouRowBeforeChangeImage.getAccountId(), ouRowBeforeChangeImage.getSubAccountId(), ouRowBeforeChangeImage.getOrderUnitId(), ouRowBeforeChangeImage.getProductId(), lockedQtyTobeAdjusted);
                }
                if(OrderCategEnum.CLOSE_MARGIN.equals(ouRowBeforeChangeImage.getOrderCateg()) || OrderCategEnum.SWAP_MARGIN.equals(ouRowBeforeChangeImage.getOrderCateg()))
                {
                    List eqtypeFinTranRows = EqtypeFinTransactionDao.findRowsByOrderExecutionId(execId);
                    Map eqtypeFinTransMap = new HashMap();
                    double totalSettledQty = 0.0D;
                    for(int k = 0; k < eqtypeFinTranRows.size(); k++)
                    {
                        EqtypeFinTransactionRow finTranRow = (EqtypeFinTransactionRow)eqtypeFinTranRows.get(k);
                        totalSettledQty += finTranRow.getQuantity();
                        eqtypeFinTransMap.put(new Long(finTranRow.getContractId()), finTranRow);
                    }

                    if(!com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils.Double.isEqual(totalSettledQty, undoExecQty))
                    {
                        String msg = "the sum of quantity of eqtype_fin_transaction rows with order_execution_id:" + execId + " doesn't match undo fill qty";
                        m_log.warn(msg);
                        throw new RuntimeSystemException(msg);
                    }
                    List currentClosingContractSpecRows = getEqTypeClosingContractSpecRows(orderUnitRow.getOrderUnitId(), "closing_serial_no");
                    EqTypePositionManager eqtypePosMgr = Utils.getPositionManager();
                    for(int k = 0; k < currentClosingContractSpecRows.size(); k++)
                    {
                        EqtypeClosingContractSpecRow closingContractRow = (EqtypeClosingContractSpecRow)currentClosingContractSpecRows.get(k);
                        long contractId = closingContractRow.getContractId();
                        EqtypeFinTransactionRow finTranRow = (EqtypeFinTransactionRow)eqtypeFinTransMap.get(new Long(contractId));
                        if(finTranRow == null)
                            continue;
                        double undoExecQtyForThisContract = finTranRow.getQuantity();
                        boolean isNonZeroUndoExecQty = !com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils.Double.isZero(undoExecQtyForThisContract);
                        try
                        {
                            Contract contract = eqtypePosMgr.getContract(contractId);
                            if(adjustLockedQty && isNonZeroUndoExecQty)
                                eqtypePosMgr.updateLockedQuantity(orderUnitRow.getOrderUnitId(), contract, undoExecQtyForThisContract);
                            double newExecQty = closingContractRow.getExecutedQuantity() - undoExecQtyForThisContract;
                            Map closingContractChanges = new HashMap();
                            closingContractChanges.put("last_updated_timestamp", sysTime);
                            closingContractChanges.put("executed_quantity", new Double(newExecQty));
                            qp.doUpdateQuery(closingContractRow.getPrimaryKey(), closingContractChanges);
                            continue;
                        }
                        catch(NotFoundException nfe)
                        {
                            String msg = "Exception while updating the locked quantity of contract. Contract not found with id:" + contractId;
                            m_log.error(msg, nfe);
                            throw new RuntimeSystemException(msg, nfe);
                        }
                    }

                }
                EqtypeOrderActionParams orderActionParams = getInitializedEqTypeOrderActionParams(orderUnitParams, OrderEventTypeEnum.UNDO_EXECUTION, undoExecQty, OrderCategEnum.SWAP_MARGIN.equals(orderUnitParams.getOrderCateg()) ? (0.0D / 0.0D) : undoExecPrice);
                persistOrderAction(OrderManagerPersistenceContext.UNDO_EXECUTION, orderActionParams);
                EqtypeOrderExecutionParams orderExecParams = new EqtypeOrderExecutionParams(orderExecRow);
                orderExecParams.setLastUpdatedTimestamp(sysTime);
                orderExecParams.setDeleteFlag(BooleanEnum.TRUE);
                BatchedQuery bq4OrderExec = null;
                if(persistenceInterceptor != null)
                {
                    orderExecParams = persistenceInterceptor.mutate(OrderManagerPersistenceType.UPDATE, OrderManagerPersistenceContext.UNDO_EXECUTION, orderExecParams);
                    bq4OrderExec = persistenceInterceptor.getQueryToExecute(OrderManagerPersistenceType.UPDATE, com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderExecutionRow.class);
                }
                if(bq4OrderExec != null)
                    qp.doQuery(bq4OrderExec);
                else
                    qp.doUpdateQuery(orderExecParams);
            }

            return;
        }
        catch(DataException de)
        {
            String msg = "Exception while handling order related updates  :  " + de.getMessage();
            m_log.error(msg, de);
            throw new RuntimeSystemException(msg, de);
        }
    }

    public void handleOrderExpiredUpdates(long orderId)
    {
        applyNewOrderStatus(OrderManagerPersistenceContext.ORDER_EXPIRED, orderId, OrderStatusEnum.UNDEFINED, OrderEventTypeEnum.EXPIRE);
    }

    public void handleChangeAcceptedOrderUpdates(long orderId, EqTypeChangeOrderUnitEntry changeOrderUnitEntries[])
    {
        applyNewOrderStatus(OrderManagerPersistenceContext.MODIFY_ORDER_ACCEPTED, orderId, OrderStatusEnum.MODIFY_ACCEPTED, OrderEventTypeEnum.CHANGE, changeOrderUnitEntries, null, null);
    }

    public void handleChangeAcceptedOrderUpdates(long orderId, EqTypeContractSettleChangeOrderUnitEntry changeOrderUnitEntries[])
    {
        applyNewOrderStatus(OrderManagerPersistenceContext.MODIFY_ORDER_ACCEPTED, orderId, OrderStatusEnum.MODIFY_ACCEPTED, OrderEventTypeEnum.CHANGE, null, changeOrderUnitEntries, null);
    }

    public void handleChangeAcceptedOrderUpdates(long orderId, EqTypeContractSwapChangeOrderUnitEntry changeOrderUnitEntries[])
    {
        applyNewOrderStatus(OrderManagerPersistenceContext.MODIFY_ORDER_ACCEPTED, orderId, OrderStatusEnum.MODIFY_ACCEPTED, OrderEventTypeEnum.CHANGE, null, null, changeOrderUnitEntries);
    }

    public void handleChangeRejectedOrderUpdates(long orderId)
    {
        applyNewOrderStatus(OrderManagerPersistenceContext.MODIFY_ORDER_REJECTED_BY_MKT, orderId, OrderStatusEnum.NOT_MODIFIED, OrderEventTypeEnum.REJECTED_BY_MKT);
    }

    public void handleChangingOrderUpdates(long orderId)
    {
        applyNewOrderStatus(OrderManagerPersistenceContext.CHANGE_ORDER_SENT_TO_MARKET, orderId, OrderStatusEnum.MODIFYING, OrderEventTypeEnum.CHANGE);
    }

    public void handleChangedOrderUpdates(long orderId, EqTypeChangeOrderUnitEntry changeOrderUnitEntries[])
    {
        applyNewOrderStatus(OrderManagerPersistenceContext.MODIFY_ORDER_CONFIRMED_BY_MKT, orderId, OrderStatusEnum.MODIFIED, OrderEventTypeEnum.CHANGE, changeOrderUnitEntries, null, null);
    }

    public void handleChangedOrderUpdates(long orderId, EqTypeContractSettleChangeOrderUnitEntry changeOrderUnitEntries[])
    {
        applyNewOrderStatus(OrderManagerPersistenceContext.MODIFY_ORDER_CONFIRMED_BY_MKT, orderId, OrderStatusEnum.MODIFIED, OrderEventTypeEnum.CHANGE, null, changeOrderUnitEntries, null);
    }

    public void handleChangedOrderUpdates(long orderId, EqTypeContractSwapChangeOrderUnitEntry changeOrderUnitEntries[])
    {
        applyNewOrderStatus(OrderManagerPersistenceContext.MODIFY_ORDER_CONFIRMED_BY_MKT, orderId, OrderStatusEnum.MODIFIED, OrderEventTypeEnum.CHANGE, null, null, changeOrderUnitEntries);
    }

    public void handleChangedOrderUpdates(long orderId)
    {
        applyNewOrderStatus(OrderManagerPersistenceContext.MODIFY_ORDER_CONFIRMED_BY_MKT, orderId, OrderStatusEnum.MODIFIED, OrderEventTypeEnum.CHANGE);
    }

    public void handleOrderSentToMarketUpdates(long orderId)
    {
        applyNewOrderStatus(OrderManagerPersistenceContext.NEW_ORDER_SENT_TO_MARKET, orderId, OrderStatusEnum.ORDERING, OrderEventTypeEnum.SEND_TO_MKT);
    }

    public void handleOrderAcceptedByMarketUpdates(long orderId)
    {
        applyNewOrderStatus(OrderManagerPersistenceContext.ORDER_CONFIRMED_BY_MKT, orderId, OrderStatusEnum.ORDERED, OrderEventTypeEnum.CONFIRMED_BY_MKT);
    }

    public void handleOrderRejectedByMarketUpdates(long orderId)
    {
        applyNewOrderStatus(OrderManagerPersistenceContext.ORDER_REJECTED_BY_MKT, orderId, OrderStatusEnum.NOT_ORDERED, OrderEventTypeEnum.REJECTED_BY_MKT);
    }

    public OrderExecution[] handleOrderFillMarketUpdates(long orderId, FillOrderUnitSpec fillOrderUnits[])
    {
        QueryProcessor qp;
        java.sql.Timestamp sysTime;
        int size;
        OrderExecution orderExecBizObjects[];
        int i;
        qp = Processors.getDefaultProcessor();
        EqtypeOrderRow orderRow = EqtypeOrderDao.findRowByPk(orderId);
        sysTime = GtlUtils.getSystemTimestamp();
        EqtypeOrderParams orderParams = new EqtypeOrderParams(orderRow);
        orderParams.setLastUpdatedTimestamp(sysTime);
        qp.doUpdateQuery(orderParams);
        if(DBG)
            m_log.debug("update eqtype_order row with values :  :" + orderParams.toString());
        size = fillOrderUnits.length;
        orderExecBizObjects = new OrderExecution[size];
        i = 0;
_L7:
        if(i >= size) goto _L2; else goto _L1
_L1:
        EqtypeOrderUnitRow orderUnitRow = EqtypeOrderUnitDao.findRowByPk(fillOrderUnits[i].getOrderUnitId());
        if(!orderUnitRow.getConfirmedQuantityIsNull()) goto _L4; else goto _L3
_L3:
        m_log.error("Order unit is not in valid state. Confirmed_quantity is null. Ignoring the fill message. order unit row values : " + orderUnitRow.toString());
        return NULL_EQTYPE_ORDER_EXECUTION;
_L4:
        if(orderUnitRow.getExecutedQuantity() != orderUnitRow.getConfirmedQuantity())
            break MISSING_BLOCK_LABEL_211;
        m_log.error("Order unit is already filled. ignoring the fill message. order unit row values : " + orderUnitRow.toString());
        return NULL_EQTYPE_ORDER_EXECUTION;
        if(orderUnitRow.getOrderOpenStatus().equals(OrderOpenStatusEnum.OPEN))
            break MISSING_BLOCK_LABEL_261;
        m_log.error("Order unit is not in open status. ignoring the fill messages. order unit row values:" + orderUnitRow.toString());
        return NULL_EQTYPE_ORDER_EXECUTION;
        orderUnitRow.getOrderStatus().intValue();
        JVM INSTR tableswitch 3 15: default 339
    //                   3 336
    //                   4 339
    //                   5 339
    //                   6 339
    //                   7 336
    //                   8 336
    //                   9 339
    //                   10 336
    //                   11 336
    //                   12 336
    //                   13 336
    //                   14 339
    //                   15 336;
           goto _L5 _L6 _L5 _L5 _L5 _L6 _L6 _L5 _L6 _L6 _L6 _L6 _L5 _L6
_L5:
        m_log.error("order status is not in the correct state for applying  FILL related updates. order unit row values: " + orderUnitRow.toString());
        return NULL_EQTYPE_ORDER_EXECUTION;
_L6:
        EqtypeOrderUnitParams orderUnitParams = new EqtypeOrderUnitParams(orderUnitRow);
        OrderCategEnum orderCateg = orderUnitRow.getOrderCateg();
        orderUnitParams.setLastUpdatedTimestamp(sysTime);
        int lastOrderActionSerNo = orderUnitParams.getLastOrderActionSerialNo();
        orderUnitParams.setLastOrderActionSerialNo(lastOrderActionSerNo + 1);
        double fillQty = fillOrderUnits[i].getFillQuantity();
        double fillPrice = fillOrderUnits[i].getFillPrice();
        double fillAmount = getFillAmount(orderUnitRow, fillPrice, fillQty);
        double totalFilledQtySofar = fillQty + orderUnitRow.getExecutedQuantity();
        if(totalFilledQtySofar > orderUnitRow.getConfirmedQuantity())
        {
            String msg = "Fill quantity is greater than confirmed quantity for order unit id : " + fillOrderUnits[i].getOrderUnitId();
            m_log.error(msg);
            throw new RuntimeSystemException(msg);
        }
        if(!OrderCategEnum.SWAP_MARGIN.equals(orderCateg))
            orderUnitParams.setExecutedAmount(orderUnitRow.getExecutedAmount() + fillAmount);
        orderUnitParams.setExecutedQuantity(totalFilledQtySofar);
        boolean isFullyExecuted = orderUnitRow.getConfirmedQuantity() == totalFilledQtySofar;
        if(isFullyExecuted)
            orderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
        int lastExecSerialNo = orderUnitParams.getLastExecutionSerialNo() + 1;
        orderUnitParams.setLastExecutionSerialNo(lastExecSerialNo);
        EqTypeOrderManagerPersistenceEventInterceptor persistenceInterceptor = EqTypeOrderManagerImpl.getThreadLocalPersistenceEventInterceptor();
        BatchedQuery bq = null;
        if(persistenceInterceptor != null)
        {
            orderUnitParams = persistenceInterceptor.mutate(OrderManagerPersistenceType.UPDATE, OrderManagerPersistenceContext.FILL_ORDER, orderUnitParams);
            bq = persistenceInterceptor.getQueryToExecute(OrderManagerPersistenceType.UPDATE, com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow.class);
        }
        if(bq != null)
        {
            if(DBG)
                m_log.debug("Using the query returned by EqTypeOrderManagerPersistenceEventInterceptor.getQueryToExecute()");
            qp.doQuery(bq);
        } else
        {
            qp.doUpdateQuery(orderUnitParams);
        }
        if(DBG)
            m_log.debug("Updated order unit row. new  values : " + orderUnitParams.toString());
        adjustLockedQuantity(orderUnitParams, ZERO_DOUBLE_VALUE, null, null, fillQty);
        if(OrderCategEnum.CLOSE_MARGIN.equals(orderCateg) || OrderCategEnum.SWAP_MARGIN.equals(orderCateg))
            updateExecQtyForContractClose(orderUnitRow, fillQty);
        EqtypeOrderActionParams orderActionParams = getInitializedEqTypeOrderActionParams(orderUnitParams, OrderEventTypeEnum.EXECUTE, fillQty, fillPrice);
        persistOrderAction(OrderManagerPersistenceContext.FILL_ORDER, orderActionParams);
        EqtypeOrderExecutionParams orderExecParams = getInitializedEqTypeOrderExecutionParams(orderUnitParams, fillQty, fillPrice);
        BatchedQuery bq4OrderExec = null;
        if(persistenceInterceptor != null)
        {
            orderExecParams = persistenceInterceptor.mutate(OrderManagerPersistenceType.INSERT, OrderManagerPersistenceContext.FILL_ORDER, orderExecParams);
            bq4OrderExec = persistenceInterceptor.getQueryToExecute(OrderManagerPersistenceType.INSERT, com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderExecutionRow.class);
        }
        if(bq4OrderExec != null)
        {
            if(DBG)
                m_log.debug("Using the query returned by EqTypeOrderManagerPersistenceEventInterceptor.getQueryToExecute()");
            qp.doQuery(bq4OrderExec);
        } else
        {
            qp.doInsertQuery(orderExecParams);
        }
        orderExecBizObjects[i] = GtlUtils.getTradingModule(orderExecParams.getProductType()).getOrderManager().toOrderExecution(orderExecParams);
        i++;
          goto _L7
_L2:
        return orderExecBizObjects;
        DataException de;
        de;
        String msg = "Exception while handling updates related to FILL ORDER  message  :  " + de.getMessage();
        m_log.error(msg, de);
        throw new RuntimeSystemException(msg, de);
    }

    protected EqTypeChangeOrderUnitEntry lookup(long orderUnitId, EqTypeChangeOrderUnitEntry changeOrderUnitEntries[])
    {
        for(int i = 0; i < changeOrderUnitEntries.length; i++)
        {
            EqTypeChangeOrderUnitEntry entry = changeOrderUnitEntries[i];
            if(entry.getOrderUnitId() == orderUnitId)
                return entry;
        }

        m_log.error("Logic error! couldn't find give orderUnitId in EqTypeChangeOrderUnitEntry[] !!!!!");
        return null;
    }

    public EqTypeContractSettleChangeOrderUnitEntry lookup(long orderUnitId, EqTypeContractSettleChangeOrderUnitEntry changeOrderUnitEntries[])
    {
        for(int i = 0; i < changeOrderUnitEntries.length; i++)
        {
            EqTypeContractSettleChangeOrderUnitEntry entry = changeOrderUnitEntries[i];
            if(entry.getOrderUnitId() == orderUnitId)
                return entry;
        }

        m_log.error("Logic error! couldn't find give orderUnitId in EqTypeContractSettleChangeOrderUnitEntry[] !!!!!");
        return null;
    }

    protected EqTypeContractSwapChangeOrderUnitEntry lookup(long orderUnitId, EqTypeContractSwapChangeOrderUnitEntry changeOrderUnitEntries[])
    {
        for(int i = 0; i < changeOrderUnitEntries.length; i++)
        {
            EqTypeContractSwapChangeOrderUnitEntry entry = changeOrderUnitEntries[i];
            if(entry.getOrderUnitId() == orderUnitId)
                return entry;
        }

        m_log.error("Logic error! couldn't find give orderUnitId in EqTypeContractSwapChangeOrderUnitEntry[] !!!!!");
        return null;
    }

    protected void applyNewOrderStatus(OrderManagerPersistenceContext persistenceContext, long orderId, OrderStatusEnum newOrderStatus, OrderEventTypeEnum orderEventType)
    {
        applyNewOrderStatus(persistenceContext, orderId, newOrderStatus, orderEventType, null, null, null);
    }

    protected void applyNewOrderStatus(OrderManagerPersistenceContext persistenceContext, long orderId, OrderStatusEnum desiredNewOrderStatus, OrderEventTypeEnum desiredOrderEventType, EqTypeChangeOrderUnitEntry changeOrderUnits[], EqTypeContractSettleChangeOrderUnitEntry changeSettleOrderUnits[], 
            EqTypeContractSwapChangeOrderUnitEntry changeSwapOrderUnits[])
    {
        QueryProcessor qp;
        List orderUnits;
        java.sql.Timestamp sysTime;
        int size;
        int i;
        qp = Processors.getDefaultProcessor();
        EqtypeOrderRow orderRow = EqtypeOrderDao.findRowByPk(orderId);
        orderUnits = EqtypeOrderUnitDao.findRowsByOrderId(orderRow);
        sysTime = GtlUtils.getSystemTimestamp();
        EqtypeOrderParams orderParams = new EqtypeOrderParams(orderRow);
        orderParams.setLastUpdatedTimestamp(sysTime);
        qp.doUpdateQuery(orderParams);
        size = orderUnits.size();
        i = 0;
_L2:
        EqtypeOrderUnitRow orderUnitRowBeforeChangeImage;
        EqtypeOrderUnitParams orderUnitParams;
        List beforeChangeQtyClosingContracts;
        OrderStatusEnum newOrderStatus;
        OrderEventTypeEnum orderEventType;
        double beforeModifyQty;
        Boolean rollbackUsingConfirmedQtyForClosingContracts;
        if(i >= size)
            break MISSING_BLOCK_LABEL_1426;
        EqtypeOrderUnitRow orderUnitRow = (EqtypeOrderUnitRow)orderUnits.get(i);
        orderUnitRowBeforeChangeImage = orderUnitRow;
        orderUnitParams = new EqtypeOrderUnitParams(orderUnitRow);
        orderUnitParams.setLastUpdatedTimestamp(sysTime);
        int lastOrderActionSerNo = orderUnitParams.getLastOrderActionSerialNo();
        orderUnitParams.setLastOrderActionSerialNo(lastOrderActionSerNo + 1);
        if(changeOrderUnits != null)
        {
            EqTypeChangeOrderUnitEntry changeOrderUnitEntry = lookup(orderUnitRow.getOrderUnitId(), changeOrderUnits);
            orderUnitParams.setLimitPrice(changeOrderUnitEntry.isAfterChangePriceMarket() ? 0.0D : changeOrderUnitEntry.getAfterChangePrice());
            orderUnitParams.setQuantity(changeOrderUnitEntry.getAfterChangeOriginalQuantity());
        }
        EqTypeSettleContractOrderEntry afterChangeSettleContractEntries[] = null;
        if(changeSettleOrderUnits != null)
        {
            EqTypeContractSettleChangeOrderUnitEntry changeOrderUnitEntry = lookup(orderUnitRow.getOrderUnitId(), changeSettleOrderUnits);
            orderUnitParams.setLimitPrice(changeOrderUnitEntry.isAfterChangePriceMarket() ? 0.0D : changeOrderUnitEntry.getAfterChangePrice());
            orderUnitParams.setQuantity(changeOrderUnitEntry.getAfterChangeTotalQuantity());
            afterChangeSettleContractEntries = changeOrderUnitEntry.getAfterChangeSettleContractOrderEntries();
        }
        if(changeSwapOrderUnits != null)
        {
            EqTypeContractSwapChangeOrderUnitEntry changeOrderUnitEntry = lookup(orderUnitRow.getOrderUnitId(), changeSwapOrderUnits);
            orderUnitParams.setQuantity(changeOrderUnitEntry.getAfterChangeTotalQuantity());
            afterChangeSettleContractEntries = changeOrderUnitEntry.getAfterChangeSettleContractOrderEntries();
        }
        beforeChangeQtyClosingContracts = null;
        boolean isSettleOrSwapContractOrderUnit = OrderCategEnum.CLOSE_MARGIN.equals(orderUnitRow.getOrderCateg()) || OrderCategEnum.SWAP_MARGIN.equals(orderUnitRow.getOrderCateg());
        if(isSettleOrSwapContractOrderUnit)
            beforeChangeQtyClosingContracts = getEqTypeClosingContractSpecRows(orderUnitRow.getOrderUnitId(), "closing_serial_no");
        if(afterChangeSettleContractEntries != null)
        {
            for(int k = 0; k < afterChangeSettleContractEntries.length; k++)
            {
                EqTypeSettleContractOrderEntry eqtypeSettleContractOrderEntry = afterChangeSettleContractEntries[k];
                EqtypeClosingContractSpecRow closingContractSpecRow = getEqTypeClosingContractSpecRow(orderUnitRow.getOrderUnitId(), eqtypeSettleContractOrderEntry.getContractId());
                if(closingContractSpecRow == null)
                    throw new IllegalArgumentException("eqtype_closing_contract_spec row is not found for order_unit_id, contract_id:" + orderUnitRow.getOrderUnitId() + "," + eqtypeSettleContractOrderEntry.getContractId());
                double newQty = eqtypeSettleContractOrderEntry.getQuantity();
                double oldQty = closingContractSpecRow.getQuantity();
                if(oldQty != newQty)
                {
                    Map changes = new HashMap();
                    changes.put("quantity", new Double(newQty));
                    changes.put("last_updated_timestamp", sysTime);
                    qp.doUpdateQuery(closingContractSpecRow.getPrimaryKey(), changes);
                }
            }

        }
        newOrderStatus = desiredNewOrderStatus;
        orderEventType = desiredOrderEventType;
        beforeModifyQty = 0.0D;
        rollbackUsingConfirmedQtyForClosingContracts = null;
        if(OrderStatusEnum.ORDERED.equals(newOrderStatus))
        {
            if(!OrderCategEnum.SWAP_MARGIN.equals(orderUnitRow.getOrderCateg()))
                orderUnitParams.setConfirmedPrice(orderUnitParams.getLimitPrice());
            orderUnitParams.setConfirmedQuantity(orderUnitParams.getQuantity());
            if(isSettleOrSwapContractOrderUnit)
                moveClosingContractSpecsQuantity2ConfirmedQuantity(orderUnitRow.getOrderUnitId());
        } else
        if(OrderStatusEnum.MODIFIED.equals(newOrderStatus))
        {
            if(orderUnitParams.getConfirmedQuantityIsNull())
            {
                newOrderStatus = orderUnitParams.getOrderStatus();
                orderEventType = OrderEventTypeEnum.NEW;
                beforeModifyQty = orderUnitRow.getQuantity();
                if(isSettleOrSwapContractOrderUnit)
                    rollbackUsingConfirmedQtyForClosingContracts = Boolean.FALSE;
            } else
            {
                orderUnitParams.setConfirmedPrice(orderUnitParams.getLimitPrice());
                orderUnitParams.setConfirmedQuantity(orderUnitParams.getQuantity());
                beforeModifyQty = orderUnitRow.getConfirmedQuantity();
                if(isSettleOrSwapContractOrderUnit)
                {
                    moveClosingContractSpecsQuantity2ConfirmedQuantity(orderUnitRow.getOrderUnitId());
                    rollbackUsingConfirmedQtyForClosingContracts = Boolean.TRUE;
                }
            }
        } else
        if(OrderStatusEnum.NOT_MODIFIED.equals(newOrderStatus))
        {
            orderUnitParams.setLimitPrice(orderUnitParams.getConfirmedPrice());
            orderUnitParams.setQuantity(orderUnitParams.getConfirmedQuantity());
            moveClosingContractSpecsConfirmedQuantity2Quantity(orderUnitRow.getOrderUnitId());
            rollbackUsingConfirmedQtyForClosingContracts = Boolean.TRUE;
        } else
        if(isSettleOrSwapContractOrderUnit)
        {
            if(OrderStatusEnum.CANCELLED.equals(newOrderStatus) || OrderStatusEnum.NOT_ORDERED.equals(newOrderStatus) || OrderEventTypeEnum.EXPIRE.equals(orderEventType))
                if(orderUnitRow.getConfirmedQuantityIsNull())
                    rollbackUsingConfirmedQtyForClosingContracts = Boolean.FALSE;
                else
                    rollbackUsingConfirmedQtyForClosingContracts = Boolean.TRUE;
            if(OrderStatusEnum.CANCELLED.equals(newOrderStatus))
                rollbackUsingConfirmedQtyForClosingContracts = Boolean.TRUE;
            else
            if(OrderStatusEnum.NOT_ORDERED.equals(newOrderStatus))
                rollbackUsingConfirmedQtyForClosingContracts = Boolean.FALSE;
            if(OrderStatusEnum.MODIFY_ACCEPTED.equals(newOrderStatus) && afterChangeSettleContractEntries != null)
                rollbackUsingConfirmedQtyForClosingContracts = Boolean.TRUE;
        }
        if(!OrderEventTypeEnum.EXPIRE.equals(orderEventType))
            orderUnitParams.setOrderStatus(newOrderStatus);
        boolean shallClose = OrderOpenStatusEnum.CLOSED.equals(orderUnitRow.getOrderOpenStatus());
        if(!shallClose)
            switch(newOrderStatus.intValue())
            {
            case 6: // '\006'
            case 14: // '\016'
                orderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
                break;

            case 10: // '\n'
                if(!orderUnitParams.getConfirmedQuantityIsNull() && !com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils.Double.isZero(orderUnitParams.getConfirmedQuantity()))
                {
                    double unexecQty = orderUnitParams.getConfirmedQuantity() - orderUnitParams.getExecutedQuantity();
                    if(com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils.Double.isZero(unexecQty))
                        orderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
                }
                break;

            default:
                if(OrderEventTypeEnum.EXPIRE.equals(orderEventType))
                {
                    orderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
                    if(OrderManagerPersistenceContext.ORDER_INVALIDATED_BY_MKT.equals(persistenceContext))
                        orderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.INVALIDATED_BY_MKT);
                    else
                        orderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.EXPIRED);
                }
                break;
            }
        else
        if(OrderEventTypeEnum.EXPIRE.equals(orderEventType))
        {
            String orderEventMsg = OrderManagerPersistenceContext.ORDER_INVALIDATED_BY_MKT.equals(persistenceContext) ? "Order Invalidation By Market" : "Order Expired";
            m_log.warn("OrderUnit with id : " + orderUnitRow.getOrderUnitId() + " , is already closed. Ignoring : " + orderEventMsg);
            return;
        }
        BatchedQuery bq = null;
        if(persistenceContext != null)
        {
            EqTypeOrderManagerPersistenceEventInterceptor persistenceInterceptor = EqTypeOrderManagerImpl.getThreadLocalPersistenceEventInterceptor();
            if(persistenceInterceptor != null)
            {
                orderUnitParams = persistenceInterceptor.mutate(OrderManagerPersistenceType.UPDATE, persistenceContext, orderUnitParams);
                bq = persistenceInterceptor.getQueryToExecute(OrderManagerPersistenceType.UPDATE, com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow.class);
            }
        }
        if(bq != null)
        {
            if(DBG)
                m_log.debug("Using the query returned by EqTypeOrderManagerPersistenceEventInterceptor.getQueryToExecute()");
            qp.doQuery(bq);
        } else
        {
            qp.doUpdateQuery(orderUnitParams);
        }
        if(DBG)
            m_log.debug("Updated order unit row. new  values : " + orderUnitParams.toString());
        if(beforeChangeQtyClosingContracts == null)
            adjustLockedQuantity(orderUnitParams, new Double(beforeModifyQty), null, null, 0.0D);
        else
            adjustLockedQuantity(orderUnitParams, null, rollbackUsingConfirmedQtyForClosingContracts, rollbackUsingConfirmedQtyForClosingContracts != null ? beforeChangeQtyClosingContracts : null, 0.0D);
        EqtypeOrderActionParams orderActionParams = getInitializedEqTypeOrderActionParams(orderUnitParams, orderEventType, (0.0D / 0.0D), (0.0D / 0.0D));
        if(OrderStatusEnum.NOT_MODIFIED.equals(newOrderStatus))
        {
            orderActionParams.setPrice(orderUnitRowBeforeChangeImage.getLimitPrice());
            orderActionParams.setConfirmedPrice(orderUnitRowBeforeChangeImage.getConfirmedPrice());
            orderActionParams.setQuantity(orderUnitRowBeforeChangeImage.getQuantity());
            orderActionParams.setConfirmedQuantity(orderUnitRowBeforeChangeImage.getConfirmedQuantity());
        }
        persistOrderAction(persistenceContext, orderActionParams);
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        return;
        DataException de;
        de;
        String msg = "Exception while handling order related updates  :  " + de.getMessage();
        m_log.error(msg, de);
        throw new RuntimeSystemException(msg, de);
    }

    protected EqtypeOrderActionParams getInitializedEqTypeOrderActionParams(EqtypeOrderUnitRow orderUnitRow, OrderEventTypeEnum eventType, double execQty, double execPrice)
        throws DataQueryException, DataNetworkException
    {
        long orderActionId = EqtypeOrderActionDao.newPkValue();
        java.sql.Timestamp sysTime = GtlUtils.getSystemTimestamp();
        EqtypeOrderActionParams orderActionParams = new EqtypeOrderActionParams();
        orderActionParams.setAccountId(orderUnitRow.getAccountId());
        if(!orderUnitRow.getConfirmedPriceIsNull())
            orderActionParams.setConfirmedPrice(orderUnitRow.getConfirmedPrice());
        if(!orderUnitRow.getConfirmedQuantityIsNull())
            orderActionParams.setConfirmedQuantity(orderUnitRow.getConfirmedQuantity());
        orderActionParams.setCreatedTimestamp(sysTime);
        orderActionParams.setExecutionConditionType(orderUnitRow.getExecutionConditionType());
        if(java.lang.Double.isNaN(execQty))
            orderActionParams.setExecutedQuantity(null);
        else
            orderActionParams.setExecutedQuantity(execQty);
        if(java.lang.Double.isNaN(execPrice))
            orderActionParams.setExecutedPrice(null);
        else
            orderActionParams.setExecutedPrice(execPrice);
        orderActionParams.setExpirationStatus(orderUnitRow.getExpirationStatus());
        orderActionParams.setLastUpdatedTimestamp(sysTime);
        orderActionParams.setMarketId(orderUnitRow.getMarketId());
        orderActionParams.setOrderActionId(orderActionId);
        orderActionParams.setOrderActionSerialNo(orderUnitRow.getLastOrderActionSerialNo());
        orderActionParams.setOrderEventType(eventType);
        orderActionParams.setOrderId(orderUnitRow.getOrderId());
        orderActionParams.setOrderStatus(orderUnitRow.getOrderStatus());
        orderActionParams.setOrderType(orderUnitRow.getOrderType());
        orderActionParams.setOrderUnitId(orderUnitRow.getOrderUnitId());
        if(!orderUnitRow.getLimitPriceIsNull())
            orderActionParams.setPrice(orderUnitRow.getLimitPrice());
        orderActionParams.setProductId(orderUnitRow.getProductId());
        orderActionParams.setQuantity(orderUnitRow.getQuantity());
        orderActionParams.setQuantityType(orderUnitRow.getQuantityType());
        orderActionParams.setSubAccountId(orderUnitRow.getSubAccountId());
        orderActionParams.setProductType(orderUnitRow.getProductType());
        return orderActionParams;
    }

    protected EqtypeOrderExecutionParams getInitializedEqTypeOrderExecutionParams(EqtypeOrderUnitRow orderUnitRow, double execQty, double execPrice)
        throws DataQueryException, DataNetworkException
    {
        EqtypeOrderExecutionParams orderExecParams;
        long orderExecId = EqtypeOrderExecutionDao.newPkValue();
        java.sql.Timestamp sysTime = GtlUtils.getSystemTimestamp();
        orderExecParams = new EqtypeOrderExecutionParams();
        orderExecParams.setAccountId(orderUnitRow.getAccountId());
        orderExecParams.setBranchId(orderUnitRow.getBranchId());
        orderExecParams.setCreatedTimestamp(sysTime);
        long productId = orderUnitRow.getProductId();
        long marketId = orderUnitRow.getMarketId();
        TradedProduct tradedProduct = Utils.getProductManager().getTradedProduct(productId, marketId);
        orderExecParams.setDeleteFlag(BooleanEnum.FALSE);
        orderExecParams.setDeliveryDate(tradedProduct.getDailyDeliveryDate());
        if(!OrderCategEnum.SWAP_MARGIN.equals(orderUnitRow.getOrderCateg()))
            orderExecParams.setExecPrice(execPrice);
        orderExecParams.setExecQuantity(execQty);
        orderExecParams.setExecSerialNo(orderUnitRow.getLastExecutionSerialNo());
        orderExecParams.setExecTimestamp(sysTime);
        orderExecParams.setLastUpdatedTimestamp(sysTime);
        orderExecParams.setMarketId(orderUnitRow.getMarketId());
        orderExecParams.setOrderExecutionId(orderExecId);
        orderExecParams.setOrderId(orderUnitRow.getOrderId());
        orderExecParams.setOrderType(orderUnitRow.getOrderType());
        orderExecParams.setOrderUnitId(orderUnitRow.getOrderUnitId());
        orderExecParams.setProductId(orderUnitRow.getProductId());
        orderExecParams.setProductType(orderUnitRow.getProductType());
        orderExecParams.setQuantityType(orderUnitRow.getQuantityType());
        orderExecParams.setSubAccountId(orderUnitRow.getSubAccountId());
        Date bizDate = GtlUtils.getFinObjectManager().getTradingCalendar(tradedProduct.getTradedProductId()).getCurrentBizDate();
        orderExecParams.setBizDate(GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(bizDate));
        if(!orderUnitRow.getTraderIdIsNull())
            orderExecParams.setTraderId(orderUnitRow.getTraderId());
        return orderExecParams;
        NotFoundException nfe;
        nfe;
        String msg = "TradedProduct not found for product id, market id in order unit row : " + orderUnitRow.toString();
        m_log.error(msg, nfe);
        throw new RuntimeSystemException(msg, nfe);
    }

    protected void persistOrderAction(OrderManagerPersistenceContext persistenceContext, EqtypeOrderActionParams orderActionParams)
        throws DataException
    {
        QueryProcessor qp = Processors.getDefaultProcessor();
        BatchedQuery bq1 = null;
        if(persistenceContext != null)
        {
            EqTypeOrderManagerPersistenceEventInterceptor persistenceInterceptor = EqTypeOrderManagerImpl.getThreadLocalPersistenceEventInterceptor();
            if(persistenceInterceptor != null)
            {
                orderActionParams = persistenceInterceptor.mutate(OrderManagerPersistenceType.INSERT, persistenceContext, orderActionParams);
                bq1 = persistenceInterceptor.getQueryToExecute(OrderManagerPersistenceType.INSERT, com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderActionRow.class);
            }
        }
        if(bq1 != null)
        {
            if(DBG)
                m_log.debug("Using the query returned by EqTypeOrderManagerPersistenceEventInterceptor.getQueryToExecute()");
            qp.doQuery(bq1);
        } else
        {
            qp.doInsertQuery(orderActionParams);
        }
    }

    protected double getFillAmount(EqtypeOrderUnitRow orderUnitRow, double fillPrice, double fillQty)
    {
        return Math.floor(fillPrice * fillQty);
    }

    protected EqTypeOrderManagerPersistenceEventInterceptor getThreadLocalPersistenceEventInterceptor()
    {
        return EqTypeOrderManagerImpl.getThreadLocalPersistenceEventInterceptor();
    }

    protected abstract void dispatchUpdateLockedQuantity(long l, long l1, long l2, long l3, double d);

    protected abstract TradingModule getThisTradingModule();

    private static final Logit m_log;
    private static final boolean DBG;
    protected static final EqTypeOrderExecution NULL_EQTYPE_ORDER_EXECUTION[] = new EqTypeOrderExecution[0];
    protected static final Double ZERO_DOUBLE_VALUE = new Double(0.0D);

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeOrderSubmitterPersistenceManager.class);
        DBG = m_log.ison();
    }
}
