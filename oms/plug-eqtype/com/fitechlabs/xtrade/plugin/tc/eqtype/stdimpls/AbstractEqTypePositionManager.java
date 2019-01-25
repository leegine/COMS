// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AbstractEqTypePositionManager.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderExecution;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypePositionManager;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.GtlQueryUtils;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls:
//            EqTypeOrderExecutionImpl, EqTypePositionManagerThreadLocalContext, EqTypePositionManagerHelper

public abstract class AbstractEqTypePositionManager
    implements EqTypePositionManager
{

    public void setThreadLocalPersistenceEventInterceptor(PositionManagerPersistenceEventInterceptor interceptor)
    {
        ThreadLocalSystemAttributesRegistry.setAttribute("EqTypePositionManagerPersistenceEventInterceptor", interceptor);
    }

    public AbstractEqTypePositionManager()
    {
        m_helper = null;
        m_tradingType = null;
    }

    public Asset getAssetPosition(long assetId)
        throws NotFoundException
    {
        return getAsset(assetId);
    }

    public Contract getContractPosition(long contractId)
        throws NotFoundException
    {
        return getContract(contractId);
    }

    public Asset convertToPolymorphicAsset(AssetRow row)
    {
        PositionManager pm = GtlUtils.getTradingModule(row.getProductType()).getPositionManager();
        return pm.toAsset(row);
    }

    public Contract convertToPolymorphicContract(EqtypeContractRow row)
    {
        PositionManager pm = GtlUtils.getTradingModule(row.getProductType()).getPositionManager();
        return pm.toContract(row);
    }

    public List getAssets(MainAccount mainAccount, SortKeySpec sortKey, ProductTypeEnum productType)
    {
        return getAssetsByAccount(mainAccount, sortKey, productType);
        DataException de;
        de;
        throw new RuntimeSystemException("Error during DB operation. Root cause: " + de.getMessage(), de);
    }

    public List getAssets(SubAccount subAccount, SortKeySpec sortKey, ProductTypeEnum productType)
    {
        return getAssetsByAccount(subAccount, sortKey, productType);
        DataException de;
        de;
        throw new RuntimeSystemException("Error during DB operation. Root cause: " + de.getMessage(), de);
    }

    public List getAssets(MainAccount mainAccount, FilterQueryParamsSpec filterQueryParamsSpec, SortKeySpec sortKey)
    {
        return getAssetsByAccount(mainAccount, sortKey, null, filterQueryParamsSpec == null ? null : filterQueryParamsSpec.getWhereCondition(), filterQueryParamsSpec == null ? null : filterQueryParamsSpec.getBindVars(), false);
        DataException de;
        de;
        m_log.error("Error while getting Assets with account_id:" + mainAccount.getAccountId() + ", filterQueryParamsSpec:" + filterQueryParamsSpec + ",sortKeySpec:" + sortKey);
        throw new RuntimeSystemException("Error during DB operation. Root cause: " + de.getMessage(), de);
    }

    public List getAssets(SubAccount subAccount, FilterQueryParamsSpec filterQueryParamsSpec, SortKeySpec sortKey)
    {
        return getAssetsByAccount(subAccount, sortKey, null, filterQueryParamsSpec == null ? null : filterQueryParamsSpec.getWhereCondition(), filterQueryParamsSpec == null ? null : filterQueryParamsSpec.getBindVars(), false);
        DataException de;
        de;
        m_log.error("Error while getting Assets with account_id:" + subAccount.getAccountId() + ",sub_account_id:" + subAccount.getSubAccountId() + ", filterQueryParamsSpec:" + filterQueryParamsSpec + ",sortKeySpec:" + sortKey);
        throw new RuntimeSystemException("Error during DB operation. Root cause: " + de.getMessage(), de);
    }

    private List getAssetsByAccount(Object account, SortKeySpec sortKey, ProductTypeEnum productType)
        throws DataException
    {
        return getAssetsByAccount(account, sortKey, productType, null, null, true);
    }

    private List getAssetsByAccount(Object account, SortKeySpec sortKey, ProductTypeEnum productType, String where, Object bindVars[], boolean includeQuantityCheck)
        throws DataException
    {
        com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.GtlQueryUtils.Where query = null;
        if(account instanceof SubAccount)
            query = GtlQueryUtils.addSubAccountQueryInfo0(query, (SubAccount)account);
        else
            query = GtlQueryUtils.addMainAccountQueryInfo0(query, (MainAccount)account);
        if(productType != null)
            query = GtlQueryUtils.addProductTypeInfo0(query, productType);
        if(where != null)
            query.append(where, bindVars);
        if(includeQuantityCheck)
        {
            Object Zero[] = {
                new Double(0.0D)
            };
            query.append("quantity>?", Zero);
        }
        return selectAssets(query, sortKey, null);
    }

    public List getContracts(MainAccount mainAccount, FilterQueryParamsSpec filterQueryParamsSpec, SortKeySpec sortKey)
    {
        return getContractsByAccount(mainAccount, sortKey, null, filterQueryParamsSpec == null ? null : filterQueryParamsSpec.getWhereCondition(), filterQueryParamsSpec == null ? null : filterQueryParamsSpec.getBindVars(), false);
        DataException de;
        de;
        m_log.error("Error while getting Contract with account_id:" + mainAccount.getAccountId() + ", filterQueryParamsSpec:" + filterQueryParamsSpec + ",sortKeySpec:" + sortKey);
        throw new RuntimeSystemException("Error during DB operation. Root cause: " + de.getMessage(), de);
    }

    public List getContracts(SubAccount subAccount, FilterQueryParamsSpec filterQueryParamsSpec, SortKeySpec sortKey)
    {
        return getContractsByAccount(subAccount, sortKey, null, filterQueryParamsSpec == null ? null : filterQueryParamsSpec.getWhereCondition(), filterQueryParamsSpec == null ? null : filterQueryParamsSpec.getBindVars(), false);
        DataException de;
        de;
        m_log.error("Error while getting Contract with account_id:" + subAccount.getAccountId() + ", sub_account_id:" + subAccount.getSubAccountId() + ", filterQueryParamsSpec:" + filterQueryParamsSpec + ",sortKeySpec:" + sortKey);
        throw new RuntimeSystemException("Error during DB operation. Root cause: " + de.getMessage(), de);
    }

    public List getContracts(MainAccount mainAccount, SortKeySpec sortKey, ProductTypeEnum productType)
    {
        return getContractsByAccount(mainAccount, sortKey, productType);
        DataException de;
        de;
        throw new RuntimeSystemException("Error during DB operation. Root cause: " + de.getMessage(), de);
    }

    public List getContracts(SubAccount subAccount, SortKeySpec sortKey, ProductTypeEnum productType)
    {
        return getContractsByAccount(subAccount, sortKey, productType);
        DataException de;
        de;
        throw new RuntimeSystemException("Error during DB operation. Root cause: " + de.getMessage(), de);
    }

    private List getContractsByAccount(Object account, SortKeySpec sortKey, ProductTypeEnum productType)
        throws DataException
    {
        return getContractsByAccount(account, sortKey, productType, null, null, true);
    }

    private List getContractsByAccount(Object account, SortKeySpec sortKey, ProductTypeEnum productType, String where, Object bindVars[], boolean includeQuantityCheck)
        throws DataException
    {
        Object Zero[] = {
            new Double(0.0D)
        };
        com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.GtlQueryUtils.Where query = null;
        if(account instanceof SubAccount)
            query = GtlQueryUtils.addSubAccountQueryInfo0(query, (SubAccount)account);
        else
            query = GtlQueryUtils.addMainAccountQueryInfo0(query, (MainAccount)account);
        if(productType != null)
            query = GtlQueryUtils.addProductTypeInfo0(query, productType);
        if(includeQuantityCheck)
            query.append("quantity>?", Zero);
        if(where != null)
            query.append(where, bindVars);
        return selectContracts(query, sortKey, null);
    }

    private List selectAssets(com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.GtlQueryUtils.Where where, SortKeySpec sortKey, String condition)
        throws DataException
    {
        List rows = Processors.getDefaultProcessor().doFindAllQuery(AssetRow.TYPE, where == null ? null : where.getWhere(), sortKey != null && !sortKey.isSortKeyNull() ? sortKey.getSortKeySpec() : null, condition, where == null ? null : where.getBindVarArray());
        int size = rows.size();
        List objs = new ArrayList();
        for(int i = 0; i < size; i++)
        {
            AssetRow row = (AssetRow)rows.get(i);
            objs.add(convertToPolymorphicAsset(row));
        }

        return objs;
    }

    private List selectContracts(com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.GtlQueryUtils.Where where, SortKeySpec sortKey, String condition)
        throws DataException
    {
        List rows = Processors.getDefaultProcessor().doFindAllQuery(EqtypeContractRow.TYPE, where == null ? null : where.getWhere(), sortKey != null && !sortKey.isSortKeyNull() ? sortKey.getSortKeySpec() : null, condition, where == null ? null : where.getBindVarArray());
        int size = rows.size();
        List objs = new ArrayList();
        for(int i = 0; i < size; i++)
        {
            EqtypeContractRow row = (EqtypeContractRow)rows.get(i);
            objs.add(convertToPolymorphicContract(row));
        }

        return objs;
    }

    public Asset getAsset(long assetId)
        throws NotFoundException
    {
        Asset obj = convertToPolymorphicAsset(AssetDao.findRowByPk(assetId));
        return obj;
        DataFindException e;
        e;
        throw new NotFoundException("EqTypeAsset " + assetId + " not found.");
        DataException de;
        de;
        throw new RuntimeSystemException("Error during DB operation. Root cause: " + de.getMessage(), de);
    }

    public Asset getAsset(long accountId, long subAccountId, long productId)
        throws NotFoundException
    {
        List rows;
        QueryProcessor qp = Processors.getDefaultProcessor();
        rows = qp.doFindAllQuery(AssetRow.TYPE, "account_id=? and sub_account_id=? and product_id=?", null, new Object[] {
            new Long(accountId), new Long(subAccountId), new Long(productId)
        });
        rows.size();
        JVM INSTR lookupswitch 2: default 154
    //                   0: 92
    //                   1: 138;
           goto _L1 _L2 _L3
_L2:
        throw new NotFoundException("There is no asset record for account:" + accountId + " sub_account_id:" + subAccountId + " product:" + productId);
_L3:
        return convertToPolymorphicAsset((AssetRow)rows.get(0));
_L1:
        try
        {
            throw new IllegalStateException("There are more than one asset records for account:" + accountId + " sub_account_id:" + subAccountId + " product:" + productId);
        }
        catch(DataException de)
        {
            String msg = "Error while getting an Asset for accountId,subAccountId,productId:" + accountId + "," + subAccountId + "," + productId;
            m_log.error(msg, de);
            throw new RuntimeSystemException(msg);
        }
    }

    public Asset getAsset(SubAccount subAccount, Product product)
        throws NotFoundException
    {
        return getAsset(subAccount.getMainAccount().getAccountId(), subAccount.getSubAccountId(), product.getProductId());
    }

    public Contract getContract(long contractId)
        throws NotFoundException
    {
        Contract obj = convertToPolymorphicContract(EqtypeContractDao.findRowByPk(contractId));
        return obj;
        DataFindException e;
        e;
        throw new NotFoundException("EqTypeContract " + contractId + " not found.");
        DataException de;
        de;
        throw new RuntimeSystemException("Error during DB operation. Root cause: " + de.getMessage(), de);
    }

    public void processAssetOrderExecution(OrderExecution exec)
        throws RuntimeSystemException
    {
        EqTypeOrderExecution eqtypeExec = null;
        if(exec instanceof EqTypeOrderExecution)
            eqtypeExec = (EqTypeOrderExecution)exec;
        else
            eqtypeExec = new EqTypeOrderExecutionImpl((EqtypeOrderExecutionRow)exec.getDataSourceObject());
        EqTypePositionManagerThreadLocalContext.setIsProcessingAssetExecution();
        EqTypePositionManagerThreadLocalContext.setOrderExecution(eqtypeExec);
        m_helper.processAssetOrderExecution(eqtypeExec);
        EqTypePositionManagerThreadLocalContext.clearContext();
        setThreadLocalPersistenceEventInterceptor(null);
        break MISSING_BLOCK_LABEL_71;
        Exception exception;
        exception;
        EqTypePositionManagerThreadLocalContext.clearContext();
        setThreadLocalPersistenceEventInterceptor(null);
        throw exception;
    }

    public void processContractOrderExecution(OrderExecution obj)
        throws RuntimeSystemException
    {
        EqTypeOrderExecution exec = null;
        if(obj instanceof EqTypeOrderExecution)
        {
            exec = (EqTypeOrderExecution)obj;
        } else
        {
            String msg = "Invalid class type: " + obj.getClass().getName() + ". Expected: EqTypeOrderExecution";
            m_log.error(msg);
            throw new RuntimeSystemException(msg);
        }
        OrderTypeEnum orderType = exec.getOrderType();
        if(OrderTypeEnum.MARGIN_LONG.equals(orderType) || OrderTypeEnum.MARGIN_SHORT.equals(orderType))
        {
            EqTypePositionManagerThreadLocalContext.setIsProcessingContractOpenExecution();
            EqTypePositionManagerThreadLocalContext.setOrderExecution(exec);
            m_helper.processContractOrderExecution(exec, (SettleContractEntry)null);
        } else
        {
            String msg = "processContractOrderExecution: acct(" + exec.getAccountId() + "), subAcct(" + exec.getSubAccountId() + "), product(" + exec.getProductId() + "), exec_date(" + exec.getExecutionTimestamp() + "), order_type(" + exec.getOrderType() + "), get null for settles.";
            m_log.error(msg);
            throw new RuntimeSystemException(msg);
        }
        EqTypePositionManagerThreadLocalContext.clearContext();
        setThreadLocalPersistenceEventInterceptor(null);
        break MISSING_BLOCK_LABEL_242;
        Exception exception;
        exception;
        EqTypePositionManagerThreadLocalContext.clearContext();
        setThreadLocalPersistenceEventInterceptor(null);
        throw exception;
    }

    public void processContractOrderExecution(OrderExecution obj, SettleContractEntry settles[])
        throws RuntimeSystemException
    {
        EqTypeOrderExecution exec = null;
        if(obj instanceof EqTypeOrderExecution)
        {
            exec = (EqTypeOrderExecution)obj;
        } else
        {
            String msg = "Invalid class type: " + obj.getClass().getName() + ". Expected: EqTypeOrderExecution";
            m_log.error(msg);
            throw new RuntimeSystemException(msg);
        }
        OrderTypeEnum orderType = exec.getOrderType();
        switch(orderType.intValue())
        {
        case 3: // '\003'
        case 4: // '\004'
            EqTypePositionManagerThreadLocalContext.setIsProcessingContractOpenExecution();
            EqTypePositionManagerThreadLocalContext.setOrderExecution(exec);
            break;

        case 5: // '\005'
        case 6: // '\006'
            EqTypePositionManagerThreadLocalContext.setIsProcessingContractSettleExecution();
            EqTypePositionManagerThreadLocalContext.setOrderExecution(exec);
            EqTypePositionManagerThreadLocalContext.setEqTypeSettleContractOrderEntries(settles);
            break;

        case 7: // '\007'
        case 8: // '\b'
            EqTypePositionManagerThreadLocalContext.setIsProcessingContractSwapExecution();
            EqTypePositionManagerThreadLocalContext.setOrderExecution(exec);
            EqTypePositionManagerThreadLocalContext.setEqTypeSettleContractOrderEntries(settles);
            break;

        default:
            String msg = "processContractOrderExecution: acct(" + exec.getAccountId() + "), subAcct(" + exec.getSubAccountId() + "), product(" + exec.getProductId() + "), exec_date(" + exec.getExecutionTimestamp() + "), order_type(" + exec.getOrderType() + "), unsupported order type.";
            m_log.error(msg);
            throw new RuntimeSystemException(msg);
        }
        m_helper.processContractOrderExecution(exec, settles);
        EqTypePositionManagerThreadLocalContext.clearContext();
        setThreadLocalPersistenceEventInterceptor(null);
        break MISSING_BLOCK_LABEL_296;
        Exception exception;
        exception;
        EqTypePositionManagerThreadLocalContext.clearContext();
        setThreadLocalPersistenceEventInterceptor(null);
        throw exception;
    }

    public void updateLockedQuantity(long accountId, long subAccountId, long orderUnitId, long productId, double lockedQtyToBeAdjusted)
        throws RuntimeSystemException
    {
        Asset asset = null;
        if(com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils.Double.isZero(lockedQtyToBeAdjusted))
            return;
        try
        {
            asset = getAsset(accountId, subAccountId, productId);
        }
        catch(NotFoundException nfe)
        {
            throw new IllegalArgumentException("No asset found with accountId, SubAccountId, productId:" + accountId + "," + subAccountId + "," + productId);
        }
        long assetId = asset.getAssetId();
        LockedAssetDetailsParams lockedAssetParams = null;
        boolean useUpdateQuery = false;
        java.sql.Timestamp sysTime = GtlUtils.getSystemTimestamp();
        try
        {
            QueryProcessor qp = Processors.getDefaultProcessor();
            try
            {
                com.fitechlabs.xtrade.plugin.tc.gentrade.data.LockedAssetDetailsRow lockedAssetRow = LockedAssetDetailsDao.findRowByPk(assetId);
                lockedAssetParams = new LockedAssetDetailsParams(lockedAssetRow);
                useUpdateQuery = true;
            }
            catch(DataFindException ignore)
            {
                if(lockedQtyToBeAdjusted < 0.0D)
                {
                    String errMsg = "locked_asset_details row not found for asset_id : " + assetId;
                    m_log.error(errMsg);
                    throw new IllegalStateException(errMsg);
                }
                lockedAssetParams = new LockedAssetDetailsParams();
                lockedAssetParams.setAccountId(accountId);
                lockedAssetParams.setAssetId(assetId);
                lockedAssetParams.setCreatedTimestamp(sysTime);
                lockedAssetParams.setSubAccountId(subAccountId);
            }
            double currentLockedQty = lockedAssetParams.getLockedQuantity();
            double newLockedQty = currentLockedQty + lockedQtyToBeAdjusted;
            if(com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils.Double.isZero(newLockedQty))
                newLockedQty = 0.0D;
            lockedAssetParams.setLastUpdatedTimestamp(sysTime);
            lockedAssetParams.setLockedQuantity(newLockedQty);
            if(useUpdateQuery)
                qp.doUpdateQuery(lockedAssetParams);
            else
                qp.doInsertQuery(lockedAssetParams);
        }
        catch(DataException de)
        {
            String msg = "DataException while updating eqtype_locked_asset_details for assetId:" + assetId;
            m_log.error(msg, de);
            throw new RuntimeSystemException(msg, de);
        }
    }

    public void updateLockedQuantity(long orderUnitId, Contract contract, double lockedQtyToBeAdjusted)
        throws RuntimeSystemException
    {
        if(com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils.Double.isZero(lockedQtyToBeAdjusted))
            return;
        long contractId = contract.getContractId();
        EqtypeLockedContractDetailsParams lockedContractParams = null;
        boolean useUpdateQuery = false;
        java.sql.Timestamp sysTime = GtlUtils.getSystemTimestamp();
        try
        {
            QueryProcessor qp = Processors.getDefaultProcessor();
            try
            {
                com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeLockedContractDetailsRow lockedContractRow = EqtypeLockedContractDetailsDao.findRowByPk(contractId);
                lockedContractParams = new EqtypeLockedContractDetailsParams(lockedContractRow);
                useUpdateQuery = true;
            }
            catch(DataFindException ignore)
            {
                if(lockedQtyToBeAdjusted < 0.0D)
                {
                    String errMsg = "Eqtype_locked_contract_details row not found for contract_id : " + contractId;
                    m_log.error(errMsg);
                    throw new IllegalStateException(errMsg);
                }
                long accountId = contract.getSubAccount().getMainAccount().getAccountId();
                long subAccountId = contract.getSubAccount().getSubAccountId();
                lockedContractParams = new EqtypeLockedContractDetailsParams();
                lockedContractParams.setAccountId(accountId);
                lockedContractParams.setContractId(contractId);
                lockedContractParams.setCreatedTimestamp(sysTime);
                lockedContractParams.setSubAccountId(subAccountId);
            }
            double currentLockedQty = lockedContractParams.getLockedQuantity();
            double newLockedQty = currentLockedQty + lockedQtyToBeAdjusted;
            if(com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils.Double.isZero(newLockedQty))
                newLockedQty = 0.0D;
            lockedContractParams.setLastUpdatedTimestamp(sysTime);
            lockedContractParams.setLockedQuantity(newLockedQty);
            if(useUpdateQuery)
                qp.doUpdateQuery(lockedContractParams);
            else
                qp.doInsertQuery(lockedContractParams);
        }
        catch(DataException de)
        {
            String msg = "DataException while updating Eqtype_locked_contract_details for assetId:" + contractId;
            m_log.error(msg, de);
            throw new RuntimeSystemException(msg, de);
        }
    }

    public void undoExecution(long exeuctionId)
        throws RuntimeSystemException
    {
        try
        {
            EqTypePositionManagerThreadLocalContext.setIsUndoExeution();
            EqTypePositionManagerThreadLocalContext.setExecutionId(exeuctionId);
            m_helper.undoExecution(exeuctionId);
        }
        catch(DataException de)
        {
            String msg = "DataException while undoing order execution for exeuctionId:" + exeuctionId;
            m_log.error(msg, de);
            throw new RuntimeSystemException(msg, de);
        }
        EqTypePositionManagerThreadLocalContext.clearContext();
        setThreadLocalPersistenceEventInterceptor(null);
        break MISSING_BLOCK_LABEL_81;
        Exception exception;
        exception;
        EqTypePositionManagerThreadLocalContext.clearContext();
        setThreadLocalPersistenceEventInterceptor(null);
        throw exception;
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
    private static final ThreadLocal m_persistenceInterceptor = new ThreadLocal();
    protected EqTypePositionManagerHelper m_helper;
    protected ProductTypeEnum m_tradingType;

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.AbstractEqTypePositionManager.class);
        DBG = m_log.ison();
    }
}
