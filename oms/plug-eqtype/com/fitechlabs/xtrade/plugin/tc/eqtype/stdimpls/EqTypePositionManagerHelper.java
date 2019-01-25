// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypePositionManagerHelper.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeFinTransactionManager;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderExecution;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypePositionManagerPersistenceEventInterceptor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeClosingContractSpecParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractDao;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeLockedContractDetailsRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderExecutionParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderExecutionRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitDao;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BizLogicProvider;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionCateg;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GeneralizedFinTransaction;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GeneralizedFinTransactionManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GlobalBizLogicProvider;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetUnitDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetUnitParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetUnitSalesDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetUnitSalesParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetUnitSalesRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.GenFinTransactionDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.GenFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls:
//            EqTypeOrderExecutionImpl

public abstract class EqTypePositionManagerHelper
{
    public static class PersistentDataManager
    {

        public QueryProcessor getPosmngrProcessor()
            throws DataQueryException, DataNetworkException
        {
            return Processors.getDefaultProcessor();
        }

        public AssetParams getAssetParamsByPk(long assetId)
            throws DataException, RuntimeSystemException
        {
            AssetRow arow = AssetDao.findRowByPk(assetId);
            if(arow == null)
            {
                String msg = "getAssetByPk: asset not found for assetId(" + assetId + ")";
                EqTypePositionManagerHelper.m_log.error(msg);
                throw new RuntimeSystemException(msg);
            } else
            {
                return new AssetParams(arow);
            }
        }

        public AssetParams getAsset(long accountId, long subAccountId, long productId)
            throws DataQueryException, DataNetworkException
        {
            List assets = getPosmngrProcessor().doFindAllQuery(AssetRow.TYPE, "account_id = ? and sub_account_id = ? and product_id = ?", new Object[] {
                new Long(accountId), new Long(subAccountId), new Long(productId)
            });
            int size = assets.size();
            if(size == 0)
                return null;
            if(size == 1)
            {
                return new AssetParams((AssetRow)assets.get(0));
            } else
            {
                String msg = "EqTypeAsset too many rows for sub_account_id(" + subAccountId + "), product_id(" + productId + ")";
                EqTypePositionManagerHelper.m_log.error(msg);
                throw new DataFindException(msg);
            }
        }

        public List getAssets(long accountId, long subAccountId, ProductTypeEnum pType)
            throws DataQueryException, DataNetworkException
        {
            return getPosmngrProcessor().doFindAllQuery(AssetRow.TYPE, "account_id = ? and sub_account_id = ? and product_type = ? and quantity > 0", new Object[] {
                new Long(accountId), new Long(subAccountId), pType
            });
        }

        public List getAssets(long accountId, long subAccountId)
            throws DataQueryException, DataNetworkException
        {
            return getPosmngrProcessor().doFindAllQuery(AssetRow.TYPE, "account_id = ? and sub_account_id = ? and quantity > 0", new Object[] {
                new Long(accountId), new Long(subAccountId)
            });
        }

        public AssetParams getAsset(EqtypeFinTransactionParams tparams)
            throws DataQueryException, DataNetworkException
        {
            return getAsset(tparams.getAccountId(), tparams.getSubAccountId(), tparams.getProductId());
        }

        public AssetParams getAsset(long assetId)
            throws DataException, RuntimeSystemException
        {
            AssetRow arow;
            arow = AssetDao.findRowByPk(assetId);
            if(arow == null)
            {
                String msg = "getAsset: not asset for assetId(" + assetId + ")";
                EqTypePositionManagerHelper.m_log.error(msg);
                throw new RuntimeSystemException(msg);
            }
            return new AssetParams(arow);
            DataFindException e;
            e;
            return null;
        }

        public List getAssetFinTransOnADay(long accountId, long subAccountId, Date dt, ProductTypeEnum pType)
            throws DataException
        {
            List ftrans;
            if(pType != null)
                ftrans = getPosmngrProcessor().doFindAllQuery(EqtypeFinTransactionRow.TYPE, "account_id = ? and sub_account_id = ? and fin_transaction_categ = ? and  product_type = ? and  delivery_date = ?", new Object[] {
                    new Long(accountId), new Long(subAccountId), FinTransactionCateg.EQTYPE_ASSET, pType, dt
                });
            else
                ftrans = getPosmngrProcessor().doFindAllQuery(EqtypeFinTransactionRow.TYPE, "account_id = ? and sub_account_id = ? and fin_transaction_categ = ? and delivery_date = ?", new Object[] {
                    new Long(accountId), new Long(subAccountId), FinTransactionCateg.EQTYPE_ASSET, dt
                });
            return ftrans;
        }

        public List getAssetUnits(long accountId, long subAccountId, long productId)
            throws DataException
        {
            List aunits = getPosmngrProcessor().doFindAllQuery(AssetUnitRow.TYPE, "account_id = ? and sub_account_id = ? and product_id = ? and quantity > 0", "delivery_date asc, acquired_price asc", null, new Object[] {
                new Long(accountId), new Long(subAccountId), new Long(productId)
            });
            int len = aunits.size();
            List l = new ArrayList();
            if(len > 0)
            {
                for(int i = 0; i < len; i++)
                    l.add(i, new AssetUnitParams((AssetUnitRow)aunits.get(i)));

            }
            return l;
        }

        public List getAssetUnits(EqtypeFinTransactionParams tparams)
            throws DataException
        {
            return getAssetUnits(tparams.getAccountId(), tparams.getSubAccountId(), tparams.getProductId());
        }

        public AssetUnitParams getAssetUnit(long accountId, long subAccountId, long productId, double price, Date day)
            throws DataQueryException, DataNetworkException
        {
            List assets = getPosmngrProcessor().doFindAllQuery(AssetUnitRow.TYPE, "account_id = ? and sub_account_id = ? and product_id = ? and acquired_price = ? and trunc(delivery_date) = trunc(?)", new Object[] {
                new Long(accountId), new Long(subAccountId), new Long(productId), new Double(price), new java.sql.Date(day.getTime())
            });
            int size = assets.size();
            if(size == 0)
                return null;
            if(size == 1)
            {
                return new AssetUnitParams((AssetUnitRow)assets.get(0));
            } else
            {
                String msg = "EqTypeAssetUnit too many rows for sub_account_id(" + subAccountId + "), product_id(" + productId + "), price(" + price + "), delivery_date(" + day.toString() + ")";
                EqTypePositionManagerHelper.m_log.error(msg);
                throw new DataFindException(msg);
            }
        }

        public AssetUnitParams getAssetUnit(EqtypeFinTransactionParams tparams)
            throws DataQueryException, DataNetworkException
        {
            return getAssetUnit(tparams.getAccountId(), tparams.getSubAccountId(), tparams.getProductId(), tparams.getPrice(), ((Date) (tparams.getDeliveryDate())));
        }

        public AssetUnitParams getAssetUnitByPk(long assetUnitId)
            throws DataException
        {
            AssetUnitRow arow;
            arow = AssetUnitDao.findRowByPk(assetUnitId);
            if(arow == null)
            {
                String msg = "getAsset: not asset for assetId(" + assetUnitId + ")";
                EqTypePositionManagerHelper.m_log.error(msg);
                throw new RuntimeSystemException(msg);
            }
            return new AssetUnitParams(arow);
            DataFindException e;
            e;
            return null;
        }

        public void saveNewAssetUnitSales(AssetUnitSalesParams sales)
            throws DataQueryException, DataNetworkException
        {
            Timestamp now = GtlUtils.getSystemTimestamp();
            if(sales.getCreatedTimestamp() == null)
                sales.setCreatedTimestamp(now);
            if(sales.getLastUpdatedTimestamp() == null)
                sales.setLastUpdatedTimestamp(now);
            sales.setAssetUnitSalesId(AssetUnitSalesDao.newPkValue());
            EqTypePositionManagerPersistenceEventInterceptor interceptor = (EqTypePositionManagerPersistenceEventInterceptor)ThreadLocalSystemAttributesRegistry.getAttribute("EqTypePositionManagerPersistenceEventInterceptor");
            if(interceptor != null)
                sales = interceptor.mutateBeforeInsert(sales);
            getPosmngrProcessor().doInsertQuery(sales);
        }

        public void updateAssetUnitSales(AssetUnitSalesParams params, Map vals)
            throws DataNetworkException, DataQueryException
        {
            getPosmngrProcessor().doUpdateQuery(params.getPrimaryKey(), vals);
        }

        public void updateAssetUnitSales(AssetUnitSalesParams params)
            throws DataNetworkException, DataQueryException
        {
            Map vals = new HashMap(15);
            vals.put("quantity", new Double(params.getQuantity()));
            EqTypePositionManagerPersistenceEventInterceptor interceptor = (EqTypePositionManagerPersistenceEventInterceptor)ThreadLocalSystemAttributesRegistry.getAttribute("EqTypePositionManagerPersistenceEventInterceptor");
            if(interceptor != null)
                vals = interceptor.mutateBeforeUpdate(params, vals);
            updateAssetUnitSales(params, vals);
        }

        public List getAssetUnitSales(long accountId, long subAccountId, long transId)
            throws DataException
        {
            List sales = getPosmngrProcessor().doFindAllQuery(AssetUnitSalesRow.TYPE, "account_id = ? and sub_account_id = ? and fin_transaction_id\t= ?", new Object[] {
                new Long(accountId), new Long(subAccountId), new Long(transId)
            });
            int len = sales.size();
            List l = new ArrayList();
            if(len > 0)
            {
                for(int i = 0; i < len; i++)
                    l.add(i, new AssetUnitSalesParams((AssetUnitSalesRow)sales.get(i)));

            }
            return l;
        }

        public List getAssetUnitSales(EqtypeFinTransactionParams tparams)
            throws DataException
        {
            return getAssetUnitSales(tparams.getAccountId(), tparams.getSubAccountId(), tparams.getFinTransactionId());
        }

        public EqtypeContractParams getContract(long contractId)
            throws DataFindException, DataException
        {
            EqtypeContractRow contract = EqtypeContractDao.findRowByPk(contractId);
            return new EqtypeContractParams(contract);
            DataFindException e;
            e;
            return null;
        }

        public List getContracts(long accountId, long subAccountId, ProductTypeEnum pType)
            throws DataFindException, DataException
        {
            return getPosmngrProcessor().doFindAllQuery(EqtypeContractRow.TYPE, "account_id = ? and sub_account_id = ? and product_type = ?", new Object[] {
                new Long(accountId), new Long(subAccountId), pType
            });
        }

        public List getContracts(long accountId, long subAccountId)
            throws DataFindException, DataException
        {
            return getPosmngrProcessor().doFindAllQuery(EqtypeContractRow.TYPE, "account_id = ? and sub_account_id = ? ", new Object[] {
                new Long(accountId), new Long(subAccountId)
            });
        }

        public List getContracts(long accountId, long subAccountId, ContractTypeEnum cType, long productId)
            throws DataFindException, DataException
        {
            return getPosmngrProcessor().doFindAllQuery(EqtypeContractRow.TYPE, "account_id = ? and sub_account_id = ? and contract_type = ? and product_id = ?", "open_date asc", null, new Object[] {
                new Long(accountId), new Long(subAccountId), cType, new Long(productId)
            });
        }

        public EqtypeContractParams getContract(long accountId, long subAccountId, long productId, long marketId, ContractTypeEnum type, double price, Date openDate, Date closeDate)
            throws DataFindException, DataException
        {
            List contracts = getPosmngrProcessor().doFindAllQuery(EqtypeContractRow.TYPE, "account_id = ? and sub_account_id = ? and open_date = ? and contract_type = ? and product_id = ? and market_id = ? and contract_price = ? and close_date = ?", new Object[] {
                new Long(accountId), new Long(subAccountId), openDate, type, new Long(productId), new Long(marketId), new Double(price), closeDate
            });
            int size = contracts.size();
            if(size == 0)
                return null;
            if(size == 1)
            {
                return new EqtypeContractParams((EqtypeContractRow)contracts.get(0));
            } else
            {
                String msg = "EqTypeContract too many rows for sub_account_id(" + subAccountId + "), ContractType(" + type.toString() + "), product_id(" + productId + "), price(" + price + "), market_id(" + marketId + "), open_date(" + openDate.toString() + "), close_date(" + closeDate + ")";
                EqTypePositionManagerHelper.m_log.error(msg);
                throw new DataFindException(msg);
            }
        }

        public EqtypeLockedContractDetailsRow getLockedContractDetails(long contractId)
        {
            List rlt;
            try
            {
                rlt = getPosmngrProcessor().doFindAllQuery(EqtypeLockedContractDetailsRow.TYPE, "contract_id = ?", new Object[] {
                    new Long(contractId)
                });
            }
            catch(DataException e)
            {
                return null;
            }
            if(rlt == null || rlt.size() == 0)
                return null;
            else
                return (EqtypeLockedContractDetailsRow)rlt.get(0);
        }

        public List getFinTransactionForExec(long execId)
            throws DataException, RuntimeSystemException
        {
            List trans = getPosmngrProcessor().doFindAllQuery(EqtypeFinTransactionRow.TYPE, "order_execution_id = ?", new Object[] {
                new Long(execId)
            });
            int len = trans.size();
            List l = new ArrayList();
            if(len == 0)
            {
                String msg = "getFinTransactionForExec: cannot find transaction for exec(" + execId + ")";
                EqTypePositionManagerHelper.m_log.error(msg);
                throw new RuntimeSystemException(msg);
            }
            for(int i = 0; i < len; i++)
                l.add(i, new EqtypeFinTransactionParams((EqtypeFinTransactionRow)trans.get(i)));

            return l;
        }

        public List getFinTransactionByDeliveryDate(long accountId, long subAccountId, Date deliveryDate)
            throws DataException, RuntimeSystemException
        {
            List finTrans = getPosmngrProcessor().doFindAllQuery(EqtypeFinTransactionRow.TYPE, "account_id = ? and sub_account_id = ? and delivery_date > ? and delete_flag <> 1", "delivery_date desc, fin_transaction_timestamp desc", null, new Object[] {
                new Long(accountId), new Long(subAccountId), deliveryDate
            });
            return finTrans;
        }

        public List getGenTransactionByDeliveryDate(long accountId, long subAccountId, Date deliveryDate)
            throws DataException, RuntimeSystemException
        {
            List genTrans = getPosmngrProcessor().doFindAllQuery(GenFinTransactionRow.TYPE, "account_id = ? and sub_account_id = ? and delivery_date > ? and delete_flag <> 1", "delivery_date desc, transaction_date desc", null, new Object[] {
                new Long(accountId), new Long(subAccountId), deliveryDate
            });
            return genTrans;
        }

        public List getFinTransactionByDeliveryDate(long accountId, long subAccountId, FinTransactionType fType, Date deliveryDate)
            throws DataException, RuntimeSystemException
        {
            return getPosmngrProcessor().doFindAllQuery(EqtypeFinTransactionRow.TYPE, "account_id = ? and sub_account_id = ? and fin_transaction_type = ? and delivery_date >= ?", "delivery_date asc", null, new Object[] {
                new Long(accountId), new Long(subAccountId), fType, deliveryDate
            });
        }

        public List getFinTransactionByTransDate(long accountId, long subAccountId, FinTransactionType fType, Date transDate)
            throws DataException, RuntimeSystemException
        {
            return getPosmngrProcessor().doFindAllQuery(EqtypeFinTransactionRow.TYPE, "account_id = ? and sub_account_id = ? and fin_transaction_type = ? and fin_transaction_timestamp >= ?", "delivery_date asc", null, new Object[] {
                new Long(accountId), new Long(subAccountId), fType, transDate
            });
        }

        public void saveNewFinTransaction(EqtypeFinTransactionParams params)
            throws DataException, RuntimeSystemException
        {
            params.setFinTransactionId(GenFinTransactionDao.newPkValue());
            EqTypePositionManagerPersistenceEventInterceptor interceptor = (EqTypePositionManagerPersistenceEventInterceptor)ThreadLocalSystemAttributesRegistry.getAttribute("EqTypePositionManagerPersistenceEventInterceptor");
            if(interceptor != null)
                params = interceptor.mutateBeforeInsert(params);
            getPosmngrProcessor().doInsertQuery(params);
        }

        public void updateFinTransaction(EqtypeFinTransactionParams params, Map vals)
            throws DataNetworkException, DataQueryException
        {
            EqTypePositionManagerPersistenceEventInterceptor interceptor = (EqTypePositionManagerPersistenceEventInterceptor)ThreadLocalSystemAttributesRegistry.getAttribute("EqTypePositionManagerPersistenceEventInterceptor");
            if(interceptor != null)
                vals = interceptor.mutateBeforeUpdate(params, vals);
            getPosmngrProcessor().doUpdateQuery(params.getPrimaryKey(), vals);
        }

        protected void setUpdateTransAttributes(EqtypeFinTransactionParams params, Map vals)
        {
            vals.put("last_updated_timestamp", params.getLastUpdatedTimestamp());
            vals.put("delete_flag", params.getDeleteFlag());
        }

        public void updateFinTransaction(EqtypeFinTransactionParams params)
            throws DataException
        {
            HashMap vals = new HashMap(30);
            setUpdateTransAttributes(params, vals);
            updateFinTransaction(params, ((Map) (vals)));
        }

        public void saveNewAsset(AssetParams params)
            throws DataNetworkException, DataQueryException
        {
            if(params.getLastUpdatedTimestamp() == null)
                params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            if(params.getCreatedTimestamp() == null)
                params.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            params.setAssetId(AssetDao.newPkValue());
            EqTypePositionManagerPersistenceEventInterceptor interceptor = (EqTypePositionManagerPersistenceEventInterceptor)ThreadLocalSystemAttributesRegistry.getAttribute("EqTypePositionManagerPersistenceEventInterceptor");
            if(interceptor != null)
                params = interceptor.mutateBeforeInsert(params);
            getPosmngrProcessor().doInsertQuery(params);
        }

        public void updateAsset(AssetParams params, Map vals)
            throws DataNetworkException, DataQueryException
        {
            EqTypePositionManagerPersistenceEventInterceptor interceptor = (EqTypePositionManagerPersistenceEventInterceptor)ThreadLocalSystemAttributesRegistry.getAttribute("EqTypePositionManagerPersistenceEventInterceptor");
            if(interceptor != null)
                vals = interceptor.mutateBeforeUpdate(params, vals);
            getPosmngrProcessor().doUpdateQuery(params.getPrimaryKey(), vals);
        }

        public void saveNewAssetUnit(AssetUnitParams params)
            throws DataNetworkException, DataQueryException
        {
            if(params.getLastUpdatedTimestamp() == null)
                params.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            if(params.getCreatedTimestamp() == null)
                params.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            if(params.getAcquiredTimestamp() == null)
                params.setAcquiredTimestamp(GtlUtils.getSystemTimestamp());
            params.setAssetUnitId(AssetUnitDao.newPkValue());
            EqTypePositionManagerPersistenceEventInterceptor interceptor = (EqTypePositionManagerPersistenceEventInterceptor)ThreadLocalSystemAttributesRegistry.getAttribute("EqTypePositionManagerPersistenceEventInterceptor");
            if(interceptor != null)
                params = interceptor.mutateBeforeInsert(params);
            getPosmngrProcessor().doInsertQuery(params);
        }

        public void updateAssetUnit(AssetUnitParams params, Map vals)
            throws DataNetworkException, DataQueryException
        {
            EqTypePositionManagerPersistenceEventInterceptor interceptor = (EqTypePositionManagerPersistenceEventInterceptor)ThreadLocalSystemAttributesRegistry.getAttribute("EqTypePositionManagerPersistenceEventInterceptor");
            if(interceptor != null)
                vals = interceptor.mutateBeforeUpdate(params, vals);
            getPosmngrProcessor().doUpdateQuery(params.getPrimaryKey(), vals);
        }

        public void saveNewContract(EqtypeContractParams params)
            throws DataNetworkException, DataQueryException
        {
            params.setContractId(EqtypeContractDao.newPkValue());
            EqTypePositionManagerPersistenceEventInterceptor interceptor = (EqTypePositionManagerPersistenceEventInterceptor)ThreadLocalSystemAttributesRegistry.getAttribute("EqTypePositionManagerPersistenceEventInterceptor");
            if(interceptor != null)
                params = interceptor.mutateBeforeInsert(params);
            getPosmngrProcessor().doInsertQuery(params);
        }

        public void updateContract(EqtypeContractParams params, Map vals)
            throws DataNetworkException, DataQueryException
        {
            EqTypePositionManagerPersistenceEventInterceptor interceptor = (EqTypePositionManagerPersistenceEventInterceptor)ThreadLocalSystemAttributesRegistry.getAttribute("EqTypePositionManagerPersistenceEventInterceptor");
            if(interceptor != null)
                vals = interceptor.mutateBeforeUpdate(params, vals);
            getPosmngrProcessor().doUpdateQuery(params.getPrimaryKey(), vals);
        }

        public void setUpdateAssetAttributes(AssetParams aparams, Map values)
        {
            values.put("quantity", new Double(aparams.getQuantity()));
            values.put("book_value", new Double(aparams.getBookValue()));
            values.put("setup_fee", new Double(aparams.getSetupFee()));
            values.put("setup_fee_tax", new Double(aparams.getSetupFeeTax()));
            values.put("management_fee", new Double(aparams.getManagementFee()));
            values.put("management_fee_tax", new Double(aparams.getManagementFeeTax()));
            values.put("last_updated_timestamp", aparams.getLastUpdatedTimestamp());
        }

        public void updateAssetByTrans(AssetParams aparams)
            throws DataNetworkException, DataQueryException
        {
            Map values = new HashMap(20);
            setUpdateAssetAttributes(aparams, values);
            updateAsset(aparams, values);
        }

        public void setUpdateAssetUnitAttributes(AssetUnitParams aparams, Map vals)
        {
            vals.put("quantity", new Double(aparams.getQuantity()));
            vals.put("original_quantity", new Double(aparams.getOriginalQuantity()));
            vals.put("last_updated_timestamp", aparams.getLastUpdatedTimestamp());
        }

        public void updateAssetUnitByTrans(AssetUnitParams aparams)
            throws DataException
        {
            Map vals = new HashMap(10);
            setUpdateAssetUnitAttributes(aparams, vals);
            updateAssetUnit(aparams, vals);
        }

        public void setUpdateContractAttributes(EqtypeContractParams cparams, Map vals)
        {
            vals.put("original_quantity", new Double(cparams.getOriginalQuantity()));
            vals.put("quantity", new Double(cparams.getQuantity()));
            vals.put("setup_fee", new Double(cparams.getSetupFee()));
            vals.put("setup_fee_tax", new Double(cparams.getSetupFeeTax()));
            vals.put("management_fee", new Double(cparams.getManagementFee()));
            vals.put("management_fee_tax", new Double(cparams.getManagementFeeTax()));
            vals.put("interest_fee", new Double(cparams.getInterestFee()));
            vals.put("interest_fee_tax", new Double(cparams.getInterestFeeTax()));
            vals.put("last_updated_timestamp", cparams.getLastUpdatedTimestamp());
        }

        public void updateContractByTrans(EqtypeContractParams cparams)
            throws DataException
        {
            Map vals = new HashMap(20);
            setUpdateContractAttributes(cparams, vals);
            updateContract(cparams, vals);
        }

        public static final String THREAD_LOCAL_PERSISTENT_INTERCEPTOR_NAME = "EqTypePositionManagerPersistenceEventInterceptor";

        public PersistentDataManager()
        {
        }
    }


    public EqTypePositionManagerHelper(ProductTypeEnum tradingModuleType)
    {
        m_TradingModule = null;
        m_tradingModuleType = tradingModuleType;
    }

    /**
     * @deprecated Method EqTypePositionManagerHelper is deprecated
     */

    public EqTypePositionManagerHelper()
    {
        m_TradingModule = null;
        m_tradingModuleType = ProductTypeEnum.EQUITY;
    }

    /**
     * @deprecated Method setTradingModuleType is deprecated
     */

    public void setTradingModuleType(ProductTypeEnum type)
    {
        m_tradingModuleType = type;
    }

    protected void notifyGtl(EqtypeFinTransactionParams tparams)
        throws RuntimeSystemException
    {
        try
        {
            SubAccount subAccount = m_finApp.getAccountManager().getSubAccount(tparams.getAccountId(), tparams.getSubAccountId());
            GeneralizedFinTransaction generalized_fin_transaction = new GeneralizedFinTransaction(tparams.getFinTransactionId(), subAccount, tparams.getDeliveryDate(), tparams.getFinTransactionType(), tparams.getNetAmount(), "EqtypeFinTransaction productId(" + tparams.getProductId() + "), marketId(" + tparams.getMarketId() + "), quantity(" + tparams.getQuantity() + "), price(" + tparams.getPrice() + ")", getTradingModule().getTradingModuleName(), tparams.getCreatedTimestamp());
            ProcessingResult ps = m_finApp.getGeneralizedFinTransactionManager().addTransaction(generalized_fin_transaction);
            if(!ps.isSuccessfulResult())
                throw new RuntimeSystemException("Method addTransaction failed:" + ps.getErrorInfo());
        }
        catch(NotFoundException nfe)
        {
            String msg = "SubAccount not found for accountId, SubAccountId:" + tparams.getAccountId() + "," + tparams.getSubAccountId();
            m_log.error(msg, nfe);
            throw new RuntimeSystemException(msg, nfe);
        }
    }

    public PersistentDataManager getPersistenceManager()
    {
        return m_PDM;
    }

    protected TradingModule getTradingModule()
    {
        if(m_TradingModule == null)
            synchronized(this)
            {
                if(m_TradingModule == null)
                    m_TradingModule = GtlUtils.getTradingModule(m_tradingModuleType);
            }
        return m_TradingModule;
    }

    protected EqTypeFinTransactionManager getFinTransactionManager()
    {
        return (EqTypeFinTransactionManager)getTradingModule().getFinTransactionManager();
    }

    protected BizLogicProvider getBusinessLogicProvider()
    {
        return getTradingModule().getBizLogicProvider();
    }

    public Date getContractCloseDate(Date openDate)
    {
        long advance = 1L;
        advance = advance * 1000L * 3600L * 24L * 182L;
        Date newDt = new Date(openDate.getTime());
        newDt.setTime(newDt.getTime() + advance);
        return newDt;
    }

    protected void setMarketOrderedTransDefaultValues(EqtypeFinTransactionParams tparams)
    {
        tparams.setCapitalGain(0.0D);
        tparams.setCapitalGainTax(0.0D);
        tparams.setFinTransactionTimestamp(GtlUtils.getSystemTimestamp());
        tparams.setCommissionFee(0.0D);
        tparams.setCommissionFeeTax(0.0D);
        tparams.setImportedSetupFee(0.0D);
        tparams.setImportedSetupFeeTax(0.0D);
        tparams.setImportedInterestFee(0.0D);
        tparams.setImportedInterestFeeTax(0.0D);
        tparams.setImportedManagementFee(0.0D);
        tparams.setImportedManagementFeeTax(0.0D);
        tparams.setTransferedAssetSetupFee(0.0D);
        tparams.setTransferedAssetSetupFeeTax(0.0D);
        tparams.setTransferedAssetMngFee(0.0D);
        tparams.setTransferedAssetMngFeeTax(0.0D);
        tparams.setTransferedAssetBookValue(0.0D);
        tparams.setDeleteFlag(BooleanEnum.FALSE);
        if(tparams.getCreatedTimestamp() == null)
            tparams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        if(tparams.getLastUpdatedTimestamp() == null)
            tparams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
    }

    protected void setAssetDefaultValues(AssetParams aparams)
    {
        aparams.setBookValue(0.0D);
        aparams.setQuantity(0.0D);
        aparams.setSetupFee(0.0D);
        aparams.setSetupFeeTax(0.0D);
        aparams.setManagementFee(0.0D);
        aparams.setManagementFeeTax(0.0D);
    }

    protected void setAssetUnitDefaultValues(AssetUnitParams aparams)
    {
        aparams.setOriginalQuantity(0.0D);
        aparams.setQuantity(0.0D);
        aparams.setAcquiredPrice(0.0D);
        aparams.setAcquiredTimestamp(GtlUtils.getSystemTimestamp());
    }

    protected void setContractDefaultValues(EqtypeContractParams cparams)
    {
        cparams.setOriginalQuantity(0.0D);
        cparams.setQuantity(0.0D);
        cparams.setSetupFee(0.0D);
        cparams.setSetupFeeTax(0.0D);
        cparams.setManagementFee(0.0D);
        cparams.setManagementFeeTax(0.0D);
        cparams.setInterestFee(0.0D);
        cparams.setInterestFeeTax(0.0D);
        cparams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        cparams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
    }

    protected int cashFlowDir(int transType)
    {
        switch(transType)
        {
        case 70: // 'F'
            return -1;

        case 80: // 'P'
            return 1;

        case 201: 
            return -1;

        case 202: 
            return 1;

        case 301: 
            return -1;

        case 302: 
            return 1;

        case 401: 
            return -1;

        case 402: 
            return 1;

        case 90: // 'Z'
            return -1;

        case 100: // 'd'
            return 1;

        case 110: // 'n'
            return 1;

        case 120: // 'x'
            return -1;

        case 130: 
            return -1;

        case 140: 
            return 1;

        case 20: // '\024'
            return -1;

        case 10: // '\n'
            return 1;

        case 30: // '\036'
            return -1;

        case 40: // '('
            return 0;

        case 160: 
            return -1;

        case 170: 
            return 1;

        case 60: // '<'
            return -1;

        case 1004: 
            return -1;

        case 1003: 
            return 1;
        }
        return 0;
    }

    public SideEnum getSide(FinTransactionType typ)
    {
        if(cashFlowDir(typ.intValue()) < 0)
            return SideEnum.BUY;
        if(cashFlowDir(typ.intValue()) > 0)
            return SideEnum.SELL;
        else
            return SideEnum.UNDEFINED;
    }

    protected void addCostToAssetFromTrans(AssetParams aparams, EqtypeFinTransactionParams trans)
    {
        int cflag;
        if(FinTransactionType.EQTYPE_BOND_BUY.equals(trans.getFinTransactionType()))
            cflag = 1;
        else
        if(FinTransactionType.EQTYPE_BOND_SELL.equals(trans.getFinTransactionType()))
            cflag = -1;
        else
            cflag = 0;
        aparams.setSetupFee(aparams.getSetupFee() + trans.getCommissionFee() + trans.getImportedSetupFee() + trans.getImportedManagementFee() + trans.getImportedInterestFee() + (double)cflag * trans.getAccruedInterest());
        aparams.setSetupFeeTax(aparams.getSetupFeeTax() + trans.getCommissionFeeTax() + trans.getImportedSetupFeeTax() + trans.getImportedManagementFeeTax() + trans.getImportedInterestFeeTax());
    }

    protected void giveBackCostFromAssetByTrans(AssetParams aparams, EqtypeFinTransactionParams trans)
    {
        aparams.setSetupFee(aparams.getSetupFee() - trans.getCommissionFee() - trans.getImportedSetupFee() - trans.getImportedManagementFee() - trans.getImportedInterestFee() - trans.getAccruedInterest());
        aparams.setSetupFeeTax(aparams.getSetupFeeTax() - trans.getCommissionFeeTax() - trans.getImportedSetupFeeTax() - trans.getImportedManagementFeeTax() - trans.getImportedInterestFeeTax());
    }

    protected void takeInCostFromAssetToTrans(double factor, AssetParams aparams, EqtypeFinTransactionParams trans)
    {
        double val = Math.floor(factor * aparams.getBookValue());
        trans.setTransferedAssetBookValue(val);
        aparams.setBookValue(aparams.getBookValue() - val);
        val = Math.floor(factor * aparams.getSetupFee());
        trans.setTransferedAssetSetupFee(val);
        aparams.setSetupFee(aparams.getSetupFee() - val);
        val = Math.floor(factor * aparams.getSetupFeeTax());
        trans.setTransferedAssetSetupFeeTax(val);
        aparams.setSetupFeeTax(aparams.getSetupFeeTax() - val);
        val = Math.floor(factor * aparams.getManagementFee());
        trans.setTransferedAssetMngFee(val);
        aparams.setManagementFee(aparams.getManagementFee() - val);
        val = Math.floor(factor * aparams.getManagementFeeTax());
        trans.setTransferedAssetMngFeeTax(val);
        aparams.setManagementFeeTax(aparams.getManagementFeeTax() - val);
    }

    protected void giveBackCostToAssetFromTrans(AssetParams aparams, EqtypeFinTransactionParams trans)
    {
        aparams.setBookValue(aparams.getBookValue() + trans.getTransferedAssetBookValue());
        aparams.setSetupFee(aparams.getSetupFee() + trans.getTransferedAssetSetupFee());
        aparams.setSetupFeeTax(aparams.getSetupFeeTax() + trans.getTransferedAssetSetupFeeTax());
        aparams.setManagementFee(aparams.getManagementFee() + trans.getTransferedAssetMngFee());
        aparams.setManagementFeeTax(aparams.getManagementFeeTax() + trans.getTransferedAssetMngFeeTax());
    }

    protected void takeInCostFromContractToTrans(double factor, EqtypeContractParams cparams, EqtypeFinTransactionParams tparams)
    {
        tparams.setImportedPaidValue(tparams.getImportedPaidValue() + calcAmount(cparams.getContractPrice(), tparams.getQuantity(), tparams.getProductType(), tparams.getProductId()));
        double val = Math.floor(factor * cparams.getSetupFee());
        tparams.setImportedSetupFee(tparams.getImportedSetupFee() + val);
        cparams.setSetupFee(cparams.getSetupFee() - val);
        val = Math.floor(factor * cparams.getSetupFeeTax());
        tparams.setImportedSetupFeeTax(tparams.getImportedSetupFeeTax() + val);
        cparams.setSetupFeeTax(cparams.getSetupFeeTax() - val);
        val = Math.floor(factor * cparams.getManagementFee());
        tparams.setImportedManagementFee(tparams.getImportedManagementFee() + val);
        cparams.setManagementFee(cparams.getManagementFee() - val);
        val = Math.floor(factor * cparams.getManagementFeeTax());
        tparams.setImportedManagementFeeTax(tparams.getImportedManagementFeeTax() + val);
        cparams.setManagementFeeTax(cparams.getManagementFeeTax() - val);
        val = Math.floor(factor * cparams.getInterestFee());
        tparams.setImportedInterestFee(tparams.getImportedInterestFee() + val);
        cparams.setInterestFee(cparams.getInterestFee() - val);
        val = Math.floor(factor * cparams.getInterestFeeTax());
        tparams.setImportedInterestFeeTax(tparams.getImportedInterestFeeTax() + val);
        cparams.setInterestFeeTax(cparams.getInterestFeeTax() - val);
    }

    protected void giveBackCostToContractByTrans(EqtypeContractParams cparams, EqtypeFinTransactionParams tparams)
    {
        cparams.setSetupFee(cparams.getSetupFee() + tparams.getImportedSetupFee());
        cparams.setSetupFeeTax(cparams.getSetupFeeTax() + tparams.getImportedSetupFeeTax());
        cparams.setManagementFee(cparams.getManagementFee() + tparams.getImportedManagementFee());
        cparams.setManagementFeeTax(cparams.getManagementFeeTax() + tparams.getImportedManagementFeeTax());
        cparams.setInterestFee(cparams.getInterestFee() + tparams.getImportedInterestFee());
        cparams.setInterestFeeTax(cparams.getInterestFeeTax() + tparams.getImportedInterestFeeTax());
    }

    protected void reverseAssetUnitSales(List sales)
        throws DataException
    {
        AssetUnitSalesParams rlt = new AssetUnitSalesParams();
        int len = sales.size();
        for(int i = 0; i < len; i++)
        {
            AssetUnitSalesParams sale = (AssetUnitSalesParams)sales.get(i);
            AssetUnitParams aunit = getPersistenceManager().getAssetUnitByPk(sale.getAssetUnitId());
            aunit.setQuantity(aunit.getQuantity() + sale.getQuantity());
            getPersistenceManager().updateAssetUnitByTrans(aunit);
        }

    }

    protected void setNewAssetParamsFromMarketTradedTrans(AssetParams aparams, EqtypeFinTransactionParams trans)
    {
        aparams.setAccountId(trans.getAccountId());
        aparams.setSubAccountId(trans.getSubAccountId());
        aparams.setBookValue(calcAmount(trans.getPrice(), trans.getQuantity(), trans.getProductType(), trans.getProductId()));
        aparams.setProductId(trans.getProductId());
        aparams.setProductType(trans.getProductType());
        aparams.setQuantity(trans.getQuantity());
        aparams.setTaxType(trans.getTaxType());
        addCostToAssetFromTrans(aparams, trans);
    }

    protected void updateAssetParamsFromMarketTradedTrans(AssetParams aparams, EqtypeFinTransactionParams trans)
        throws RuntimeSystemException
    {
        if(getSide(trans.getFinTransactionType()) == SideEnum.BUY)
        {
            aparams.setBookValue(aparams.getBookValue() + calcAmount(trans.getPrice(), trans.getQuantity(), trans.getProductType(), trans.getProductId()));
            aparams.setQuantity(aparams.getQuantity() + trans.getQuantity());
            addCostToAssetFromTrans(aparams, trans);
        } else
        if(getSide(trans.getFinTransactionType()) == SideEnum.SELL)
        {
            if(trans.getQuantity() > aparams.getQuantity())
            {
                String msg = "Asset-selling execution has sold too many shares: acct(" + aparams.getAccountId() + "), subAccount(" + aparams.getSubAccountId() + "), product(" + aparams.getProductId() + "), trans(" + trans.getFinTransactionType() + "), asset.shares(" + aparams.getQuantity() + "), trans.shares(" + trans.getQuantity() + ")";
                m_log.error(msg);
                throw new RuntimeSystemException(msg);
            }
            double factor;
            if(aparams.getQuantity() == trans.getQuantity())
                factor = 1.0D;
            else
                factor = trans.getQuantity() / aparams.getQuantity();
            takeInCostFromAssetToTrans(factor, aparams, trans);
            aparams.setQuantity(aparams.getQuantity() - trans.getQuantity());
        }
        aparams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
    }

    protected void reverseAssetParamsByMarketTradedTrans(AssetParams aparams, EqtypeFinTransactionParams trans)
        throws DataException, RuntimeSystemException
    {
        if(getSide(trans.getFinTransactionType()) == SideEnum.BUY)
        {
            if(trans.getQuantity() > aparams.getQuantity())
            {
                String msg = "revertAssetParamsByMarketTradedTrans: Too little aset for acct(" + aparams.getAccountId() + "), subAccount(" + aparams.getSubAccountId() + "), product(" + aparams.getProductId() + "), trans(" + trans.getFinTransactionType() + ")" + "asset.shares(" + aparams.getQuantity() + ") < trans.shares(" + trans.getQuantity() + ")";
                m_log.error(msg);
                throw new RuntimeSystemException(msg);
            }
            aparams.setBookValue(aparams.getBookValue() - calcAmount(trans.getPrice(), trans.getQuantity(), trans.getProductType(), trans.getProductId()));
            aparams.setQuantity(aparams.getQuantity() - trans.getQuantity());
            giveBackCostFromAssetByTrans(aparams, trans);
        } else
        if(getSide(trans.getFinTransactionType()) == SideEnum.SELL)
        {
            giveBackCostToAssetFromTrans(aparams, trans);
            aparams.setQuantity(aparams.getQuantity() + trans.getQuantity());
        } else
        {
            String msg = "revertAssetParamsByMarketTradedTrans: unsupported side(" + getSide(trans.getFinTransactionType()) + ")";
            m_log.error(msg);
            throw new RuntimeSystemException(msg);
        }
        aparams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
    }

    protected void setNewAssetUnitParamsFromMarketTradedTrans(AssetUnitParams aparams, long assetId, EqtypeFinTransactionParams trans)
    {
        aparams.setAssetId(assetId);
        aparams.setAccountId(trans.getAccountId());
        aparams.setSubAccountId(trans.getSubAccountId());
        aparams.setAcquiredPrice(trans.getPrice());
        aparams.setProductId(trans.getProductId());
        aparams.setProductType(trans.getProductType());
        aparams.setOriginalQuantity(trans.getQuantity());
        aparams.setQuantity(trans.getQuantity());
        aparams.setMarketId(trans.getMarketId());
        aparams.setAcquiredTimestamp(trans.getCreatedTimestamp());
        aparams.setDeliveryDate(trans.getDeliveryDate());
        aparams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        aparams.setLastUpdatedTimestamp(aparams.getCreatedTimestamp());
    }

    protected double updateAssetUnitParamsFromMarketTradedTrans(AssetUnitParams aparams, double quant, SideEnum side)
        throws DataException, RuntimeSystemException
    {
        if(side == SideEnum.BUY)
        {
            aparams.setOriginalQuantity(aparams.getOriginalQuantity() + quant);
            aparams.setQuantity(aparams.getQuantity() + quant);
            aparams.setAcquiredTimestamp(GtlUtils.getSystemTimestamp());
            aparams.setLastUpdatedTimestamp(aparams.getAcquiredTimestamp());
            return 0.0D;
        }
        if(side == SideEnum.SELL)
        {
            double aunit_quant = aparams.getQuantity();
            if(aunit_quant < 0.0D)
            {
                String msg = "Asset Unit does not have positive shares: acct(" + aparams.getAccountId() + "), subAccount(" + aparams.getSubAccountId() + "), product(" + aparams.getProductId() + "), shares(" + aparams.getQuantity() + "), delivery_date(" + aparams.getDeliveryDate() + ")";
                m_log.error(msg);
                throw new RuntimeSystemException(msg);
            }
            if(aunit_quant <= quant)
            {
                aparams.setQuantity(0.0D);
                return quant - aunit_quant;
            } else
            {
                aparams.setQuantity(aparams.getQuantity() - quant);
                return 0.0D;
            }
        } else
        {
            return quant;
        }
    }

    protected void reverseAssetUnitParamsFromMarketTradedTrans(AssetUnitParams aparams, double quant, SideEnum side)
        throws DataException, RuntimeSystemException
    {
        if(side == SideEnum.BUY)
        {
            double aunit_quant = aparams.getQuantity();
            if(aunit_quant < quant)
            {
                String msg = "reverseAssetUnitParamsFromMarketTradedTrans: not enough shares: acct(" + aparams.getAccountId() + "), subAccount(" + aparams.getSubAccountId() + "), product(" + aparams.getProductId() + "), delivery_date(" + aparams.getDeliveryDate() + "), aunit_quant(" + aparams.getQuantity() + ") < quant(" + quant + ")";
                m_log.error(msg);
                throw new RuntimeSystemException(msg);
            }
            aparams.setOriginalQuantity(aparams.getOriginalQuantity() - quant);
            aparams.setQuantity(aparams.getQuantity() - quant);
        } else
        if(side == SideEnum.SELL)
        {
            aparams.setQuantity(aparams.getQuantity() + quant);
        } else
        {
            String msg = "reverseAssetUnitParamsFromMarketTradedTrans: incorrect side(" + side + ")";
            m_log.error(msg);
            throw new RuntimeSystemException(msg);
        }
    }

    protected List updateAssetUnitListFromMarketTradedTrans(List aunits, double quant, long transId, SideEnum side)
        throws DataException
    {
        int cnt = aunits.size();
        List assetUnitSales = new ArrayList(cnt);
        for(int i = 0; i < cnt && quant > 0.0D; i++)
        {
            AssetUnitParams wrk_aunit = (AssetUnitParams)aunits.get(i);
            double prevQuant = quant;
            quant = updateAssetUnitParamsFromMarketTradedTrans(wrk_aunit, quant, side);
            getPersistenceManager().updateAssetUnitByTrans(wrk_aunit);
            AssetUnitSalesParams sale = new AssetUnitSalesParams();
            sale.setAccountId(wrk_aunit.getAccountId());
            sale.setSubAccountId(wrk_aunit.getSubAccountId());
            sale.setAssetUnitId(wrk_aunit.getAssetUnitId());
            sale.setFinTransactionId(transId);
            sale.setQuantity(prevQuant - quant);
            assetUnitSales.add(sale);
        }

        return assetUnitSales;
    }

    protected void updateContractOpenFromMarketOrderedTrans(EqtypeContractParams cparams, EqtypeFinTransactionParams tparams)
        throws RuntimeSystemException
    {
        if(cparams.getOpenDate() == null)
        {
            cparams.setAccountId(tparams.getAccountId());
            cparams.setSubAccountId(tparams.getSubAccountId());
            cparams.setContractType(ContractTypeEnum.getContractType(tparams.getFinTransactionType()));
            cparams.setMarketId(tparams.getMarketId());
            cparams.setProductId(tparams.getProductId());
            cparams.setProductType(tparams.getProductType());
            cparams.setContractPrice(tparams.getPrice());
            cparams.setOriginalContractPrice(tparams.getPrice());
            cparams.setOpenDate(tparams.getDeliveryDate());
            cparams.setCloseDate(getContractCloseDate(cparams.getOpenDate()));
        }
        cparams.setOriginalQuantity(cparams.getOriginalQuantity() + tparams.getQuantity());
        cparams.setQuantity(cparams.getQuantity() + tparams.getQuantity());
        cparams.setSetupFee(cparams.getSetupFee() + tparams.getCommissionFee());
        cparams.setSetupFeeTax(cparams.getSetupFeeTax() + tparams.getCommissionFeeTax());
        cparams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
    }

    protected void revertContractOpenFromMarketOrderedTrans(EqtypeContractParams cparams, EqtypeFinTransactionParams tparams)
        throws RuntimeSystemException
    {
        if(cparams.getOriginalQuantity() < tparams.getQuantity())
        {
            String msg = "revertContractOpenFromMarketOrderedTrans: too little contractOriginalQuantity(" + cparams.getOriginalQuantity() + ") < tradeQuantity(" + tparams.getQuantity() + ")";
            m_log.error(msg);
            throw new RuntimeSystemException(msg);
        }
        if(cparams.getQuantity() < tparams.getQuantity())
        {
            String msg = "revertContractOpenFromMarketOrderedTrans: too little contractQuantity(" + cparams.getQuantity() + ") < tradeQuantity(" + tparams.getQuantity() + ")";
            m_log.error(msg);
            throw new RuntimeSystemException(msg);
        } else
        {
            cparams.setOriginalQuantity(cparams.getOriginalQuantity() - tparams.getQuantity());
            cparams.setQuantity(cparams.getQuantity() - tparams.getQuantity());
            cparams.setSetupFee(cparams.getSetupFee() - tparams.getCommissionFee());
            cparams.setSetupFeeTax(cparams.getSetupFeeTax() - tparams.getCommissionFeeTax());
            cparams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            return;
        }
    }

    protected void updateContractCloseFromMarketOrderedTransTooLessShares(EqtypeContractParams cparams, EqtypeFinTransactionParams tparams)
        throws RuntimeSystemException
    {
        String msg = "updateContractCloseFromMarketOrderedTrans: too little shares(" + tparams.getQuantity() + ") to settle.";
        m_log.error(msg);
        throw new RuntimeSystemException(msg);
    }

    protected void updateContractCloseFromMarketOrderedTrans(EqtypeContractParams cparams, EqtypeFinTransactionParams tparams)
        throws RuntimeSystemException
    {
        double factor = 0.0D;
        if(cparams.getQuantity() <= 0.0D)
            updateContractCloseFromMarketOrderedTransTooLessShares(cparams, tparams);
        else
        if(cparams.getQuantity() == tparams.getQuantity())
            factor = 1.0D;
        else
        if(cparams.getQuantity() > tparams.getQuantity())
        {
            factor = tparams.getQuantity() / cparams.getQuantity();
        } else
        {
            String msg = "updateContractCloseFromMarketOrderedTrans: too much to settle(" + tparams.getQuantity() + ") for (" + cparams.getQuantity() + ")";
            m_log.error(msg);
            throw new RuntimeSystemException(msg);
        }
        takeInCostFromContractToTrans(factor, cparams, tparams);
        cparams.setQuantity(cparams.getQuantity() - tparams.getQuantity());
        cparams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
    }

    protected void revertContractCloseFromMarketOrderedTrans(EqtypeContractParams cparams, EqtypeFinTransactionParams tparams)
        throws RuntimeSystemException
    {
        giveBackCostToContractByTrans(cparams, tparams);
        cparams.setQuantity(cparams.getQuantity() + tparams.getQuantity());
        cparams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
    }

    protected void updateContractSwapFromMarketOrderedTransTooLessShares(EqtypeContractParams cparams, EqtypeFinTransactionParams tparams)
        throws RuntimeSystemException
    {
        String msg = "updateContractSwapFromMarketOrderedTrans: too little shares(" + tparams.getQuantity() + ") to settle.";
        m_log.error(msg);
        throw new RuntimeSystemException(msg);
    }

    protected List updateContractSwapFromMarketOrderedTrans(EqtypeContractParams cparams, EqtypeFinTransactionParams tparams)
        throws RuntimeSystemException, DataException
    {
        double factor = 0.0D;
        if(cparams.getQuantity() <= 0.0D)
            updateContractSwapFromMarketOrderedTransTooLessShares(cparams, tparams);
        else
        if(cparams.getQuantity() == tparams.getQuantity())
            factor = 1.0D;
        else
        if(cparams.getQuantity() > tparams.getQuantity())
        {
            factor = tparams.getQuantity() / cparams.getQuantity();
        } else
        {
            String msg = "updateContractSwapFromMarketOrderedTrans: too much to settle(" + tparams.getQuantity() + ") for (" + cparams.getQuantity() + ")";
            m_log.error(msg);
            throw new RuntimeSystemException(msg);
        }
        takeInCostFromContractToTrans(factor, cparams, tparams);
        FinTransactionType typ = tparams.getFinTransactionType();
        if(typ == FinTransactionType.EQTYPE_SWAP_MARGIN_LONG)
            tparams.setFinTransactionType(FinTransactionType.EQTYPE_EQUITY_BUY);
        else
            tparams.setFinTransactionType(FinTransactionType.EQTYPE_EQUITY_SELL);
        List assetSales = applyToAssetPosition(tparams);
        tparams.setFinTransactionType(typ);
        cparams.setQuantity(cparams.getQuantity() - tparams.getQuantity());
        cparams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        return assetSales;
    }

    protected void revertContractSwapFromMarketOrderedTrans(EqtypeContractParams cparams, EqtypeFinTransactionParams tparams)
        throws RuntimeSystemException, DataException
    {
        giveBackCostToContractByTrans(cparams, tparams);
        FinTransactionType typ = tparams.getFinTransactionType();
        SideEnum side;
        if(typ == FinTransactionType.EQTYPE_SWAP_MARGIN_LONG)
            side = SideEnum.BUY;
        else
            side = SideEnum.SELL;
        reverseAssetPositionByTrans(tparams, side);
        cparams.setQuantity(cparams.getQuantity() + tparams.getQuantity());
        cparams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
    }

    protected void setExecutionInfoToMarketOrderedTrans(EqtypeFinTransactionParams tparams, EqTypeOrderExecution exec)
        throws DataQueryException, DataNetworkException, RuntimeSystemException
    {
        EqtypeOrderUnitRow ounit = EqtypeOrderUnitDao.findRowByPk(exec.getOrderUnitId());
        if(ounit == null)
        {
            String msg = "setExecutionInfoToMarketOrderedTrans: No corresponding order unit for execId(" + exec.getOrderExecutionId() + "),acct(" + exec.getAccountId() + "),subAcct(" + exec.getSubAccountId() + "),product(" + exec.getProductId() + "),market(" + exec.getMarketId() + ")";
            m_log.error(msg);
            throw new RuntimeSystemException(msg);
        } else
        {
            setExecutionInfoToMarketOrderedTrans(tparams, exec, ounit);
            return;
        }
    }

    protected void setExecutionInfoToMarketOrderedTrans(EqtypeFinTransactionParams tparams, EqTypeOrderExecution exec, EqtypeOrderUnitRow ounit)
        throws DataQueryException, DataNetworkException, RuntimeSystemException
    {
        tparams.setAccountId(exec.getAccountId());
        tparams.setSubAccountId(exec.getSubAccountId());
        tparams.setCommissionFee(getBusinessLogicProvider().calcCommission(exec));
        tparams.setCommissionFeeTax(GtlUtils.getGlobalBizLogicProvider().calcSalesTax(tparams.getCommissionFee()));
        tparams.setMarketId(exec.getMarketId());
        tparams.setOrderExecutionId(exec.getOrderExecutionId());
        tparams.setOrderUnitId(exec.getOrderUnitId());
        tparams.setPrice(exec.getExecutionPrice());
        tparams.setProductId(exec.getProductId());
        tparams.setProductType(exec.getProductType());
        tparams.setQuantity(exec.getExecutionQuantity());
        tparams.setDeliveryDate(GtlUtils.truncateDate(exec.getDeliveryDate()));
        tparams.setOrderId(ounit.getOrderId());
        tparams.setFinTransactionType(ounit.getOrderType().getFinTransactionType());
        tparams.setFinTransactionCateg(ounit.getOrderType().getFinTransactionType().getCateg());
        tparams.setTaxType(ounit.getTaxType());
    }

    protected List applyToAssetUnitPosition(EqtypeFinTransactionParams trans, AssetParams asset)
        throws DataException, RuntimeSystemException
    {
        List assetUnitSales = null;
        if(getSide(trans.getFinTransactionType()) == SideEnum.BUY)
        {
            AssetUnitParams aunit = getPersistenceManager().getAssetUnit(trans);
            if(aunit == null)
            {
                AssetUnitParams aparams = new AssetUnitParams();
                setAssetUnitDefaultValues(aparams);
                setNewAssetUnitParamsFromMarketTradedTrans(aparams, asset.getAssetId(), trans);
                getPersistenceManager().saveNewAssetUnit(aparams);
            } else
            {
                updateAssetUnitParamsFromMarketTradedTrans(aunit, trans.getQuantity(), getSide(trans.getFinTransactionType()));
                getPersistenceManager().updateAssetUnitByTrans(aunit);
            }
        } else
        if(getSide(trans.getFinTransactionType()) == SideEnum.SELL)
        {
            List aunits = getPersistenceManager().getAssetUnits(trans);
            if(aunits.size() == 0)
            {
                String msg = "No Asset Unit for acct(" + trans.getAccountId() + "), subacct(" + trans.getSubAccountId() + "), product(" + trans.getProductId() + "), aquire_date(" + trans.getDeliveryDate().toString() + ")";
                m_log.error(msg);
                throw new RuntimeSystemException(msg);
            }
            assetUnitSales = updateAssetUnitListFromMarketTradedTrans(aunits, trans.getQuantity(), trans.getFinTransactionId(), getSide(trans.getFinTransactionType()));
        }
        return assetUnitSales;
    }

    protected List applyToAssetPosition(EqtypeFinTransactionParams trans)
        throws DataException, RuntimeSystemException
    {
        List assetUnitSales = null;
        AssetParams asset = getPersistenceManager().getAsset(trans);
        if(getSide(trans.getFinTransactionType()) == SideEnum.BUY)
        {
            if(asset == null)
            {
                AssetParams aparams = new AssetParams();
                setAssetDefaultValues(aparams);
                setNewAssetParamsFromMarketTradedTrans(aparams, trans);
                getPersistenceManager().saveNewAsset(aparams);
                asset = aparams;
            } else
            {
                updateAssetParamsFromMarketTradedTrans(asset, trans);
                getPersistenceManager().updateAssetByTrans(asset);
            }
        } else
        {
            if(asset == null)
            {
                String msg = "applyToAssetPosition: No Asset for acct(" + trans.getAccountId() + "), subacct(" + trans.getSubAccountId() + "), product(" + trans.getProductId() + ")";
                m_log.error(msg);
                throw new RuntimeSystemException(msg);
            }
            if(asset.getQuantity() < trans.getQuantity())
            {
                String msg = "applyToAssetPosition: Too little Asset for acct(" + trans.getAccountId() + "), subacct(" + trans.getSubAccountId() + "), product(" + trans.getProductId() + "), asset:" + asset.getQuantity() + " < trans:" + trans.getQuantity();
                m_log.error(msg);
                throw new RuntimeSystemException(msg);
            }
            updateAssetParamsFromMarketTradedTrans(asset, trans);
            getPersistenceManager().updateAssetByTrans(asset);
        }
        assetUnitSales = applyToAssetUnitPosition(trans, asset);
        trans.setAssetId(asset.getAssetId());
        return assetUnitSales;
    }

    protected void reverseAssetUnitPositionByTrans(EqtypeFinTransactionParams trans, SideEnum side, AssetParams asset)
        throws DataException, RuntimeSystemException
    {
        if(side == SideEnum.BUY)
        {
            AssetUnitParams aunit = getPersistenceManager().getAssetUnit(trans);
            if(aunit == null)
            {
                String msg = "reverseAssetUnitPositionByTrans: acct(" + trans.getAccountId() + "), subAcct(" + trans.getSubAccountId() + "), tradeType(" + trans.getFinTransactionType() + "), product(" + trans.getProductId() + "), quantity(" + trans.getQuantity() + ")";
                m_log.error(msg);
                throw new RuntimeSystemException(msg);
            }
            reverseAssetUnitParamsFromMarketTradedTrans(aunit, trans.getQuantity(), SideEnum.BUY);
            getPersistenceManager().updateAssetUnitByTrans(aunit);
        } else
        if(side == SideEnum.SELL)
        {
            List aunitSales = getPersistenceManager().getAssetUnitSales(trans);
            if(aunitSales == null || aunitSales.size() == 0)
            {
                String msg = "reverseAssetUnitPositionByTrans: not AssetUnitSales for acct(" + trans.getAccountId() + "), subAcct(" + trans.getSubAccountId() + "), transId(" + trans.getFinTransactionId() + ")";
                m_log.debug(msg);
                throw new RuntimeSystemException(msg);
            }
            int len = aunitSales.size();
            for(int i = 0; i < len; i++)
            {
                AssetUnitSalesParams sale = (AssetUnitSalesParams)aunitSales.get(i);
                AssetUnitParams aunit = getPersistenceManager().getAssetUnitByPk(sale.getAssetUnitId());
                reverseAssetUnitParamsFromMarketTradedTrans(aunit, sale.getQuantity(), SideEnum.SELL);
                getPersistenceManager().updateAssetUnitByTrans(aunit);
            }

        } else
        {
            String msg = "reverseAssetUnitPositionByTrans: unsupported side(" + side + ") for acct(" + trans.getAccountId() + "), subAcct(" + trans.getSubAccountId() + "), transId(" + trans.getFinTransactionId() + ")";
            m_log.error(msg);
            throw new RuntimeSystemException(msg);
        }
    }

    protected void reverseAssetPositionByTrans(EqtypeFinTransactionParams trans, SideEnum side)
        throws DataException, RuntimeSystemException
    {
        AssetParams asset = getPersistenceManager().getAsset(trans.getAssetId());
        if(side == SideEnum.BUY)
        {
            if(asset == null)
            {
                String msg = "reverseAssetPositionByTrans: acct(" + trans.getAccountId() + "), subAcct(" + trans.getSubAccountId() + "), transType(" + trans.getFinTransactionType() + "), product(" + trans.getProductId() + "), price(" + trans.getPrice() + "), quantity(" + trans.getQuantity() + ")";
                m_log.error(msg);
                throw new RuntimeSystemException(msg);
            }
            if(asset.getQuantity() < trans.getQuantity())
            {
                String msg = "reverseAssetPositionByTrans: Too little Asset for acct(" + trans.getAccountId() + "), subacct(" + trans.getSubAccountId() + "), product(" + trans.getProductId() + "), asset(" + asset.getQuantity() + ") < trans(" + trans.getQuantity() + ")";
                m_log.error(msg);
                throw new RuntimeSystemException(msg);
            }
            reverseAssetParamsByMarketTradedTrans(asset, trans);
            getPersistenceManager().updateAssetByTrans(asset);
        } else
        if(side == SideEnum.SELL)
        {
            if(asset == null)
            {
                String msg = "reverseAssetPositionByTrans: No Asset for acct(" + trans.getAccountId() + "), transType(" + trans.getFinTransactionType() + "), subacct(" + trans.getSubAccountId() + "), product(" + trans.getProductId() + ")";
                m_log.error(msg);
                throw new RuntimeSystemException(msg);
            }
            reverseAssetParamsByMarketTradedTrans(asset, trans);
            getPersistenceManager().updateAssetByTrans(asset);
        }
        reverseAssetUnitPositionByTrans(trans, side, asset);
    }

    protected void setAssetNetAmount(EqtypeFinTransactionParams tparams)
        throws DataException
    {
        double execAmt = calcAmount(tparams.getPrice(), tparams.getQuantity(), tparams.getProductType(), tparams.getProductId());
        int cflag = getSide(tparams.getFinTransactionType()).cashFlowDir();
        double capitalGainTax = 0.0D;
        if(getSide(tparams.getFinTransactionType()) == SideEnum.SELL)
        {
            double grossGain = (double)cflag * (execAmt - tparams.getTransferedAssetBookValue()) - tparams.getTransferedAssetSetupFee() - tparams.getTransferedAssetSetupFeeTax() - tparams.getCommissionFee() - tparams.getCommissionFeeTax() - tparams.getTransferedAssetMngFee() - tparams.getTransferedAssetMngFeeTax();
            SubAccount subAccount = getSubAccount(tparams.getAccountId(), tparams.getSubAccountId());
            capitalGainTax = getBusinessLogicProvider().calcCapitalGainTax(subAccount, tparams.getTaxType(), grossGain);
            tparams.setCapitalGain(grossGain - capitalGainTax);
            tparams.setCapitalGainTax(capitalGainTax);
        }
        double netAmount = (double)cflag * execAmt - tparams.getCommissionFee() - tparams.getCommissionFeeTax() - capitalGainTax;
        tparams.setNetAmount(netAmount);
    }

    protected void processCashBasedOrderExecution(EqTypeOrderExecution exec)
        throws DataException
    {
        EqtypeFinTransactionParams tparams = new EqtypeFinTransactionParams();
        List assetUnitSales = null;
        setMarketOrderedTransDefaultValues(tparams);
        setExecutionInfoToMarketOrderedTrans(tparams, exec);
        assetUnitSales = applyToAssetPosition(tparams);
        setAssetNetAmount(tparams);
        getPersistenceManager().saveNewFinTransaction(tparams);
        notifyGtl(tparams);
        if(assetUnitSales != null)
        {
            int len = assetUnitSales.size();
            for(int i = 0; i < len; i++)
            {
                AssetUnitSalesParams sale = (AssetUnitSalesParams)assetUnitSales.get(i);
                sale.setFinTransactionId(tparams.getFinTransactionId());
                getPersistenceManager().saveNewAssetUnitSales(sale);
            }

        }
    }

    public void processAssetOrderExecution(EqTypeOrderExecution exec)
        throws RuntimeSystemException
    {
        try
        {
            if(isAssetOrderExecution(exec))
            {
                processCashBasedOrderExecution(exec);
            } else
            {
                String msg = "Processing Asset Order; unsupported OrderType(" + exec.getOrderType() + "), Execution(" + exec.toString() + ")";
                m_log.error(msg);
                throw new RuntimeSystemException(msg);
            }
        }
        catch(DataException e)
        {
            String msg = "Error while updating postions. msg : " + e.getMessage();
            m_log.error(msg, e);
            throw new RuntimeSystemException(msg, e);
        }
    }

    public abstract boolean isAssetOrderExecution(EqTypeOrderExecution eqtypeorderexecution);

    protected void setMarginNetAmount(EqtypeFinTransactionParams tparams)
        throws DataException
    {
        FinTransactionType ttype = tparams.getFinTransactionType();
        double execAmt = calcAmount(tparams.getPrice(), tparams.getQuantity(), tparams.getProductType(), tparams.getProductId());
        if(ttype.getCateg() == FinTransactionCateg.EQTYPE_OPEN_MARGIN)
        {
            tparams.setNetAmount(0.0D);
        } else
        {
            int interestCostFlag = 0;
            if(FinTransactionType.EQTYPE_CLOSE_MARGIN_LONG.equals(ttype) || FinTransactionType.EQTYPE_SWAP_MARGIN_LONG.equals(ttype))
                interestCostFlag = 1;
            else
            if(FinTransactionType.EQTYPE_CLOSE_MARGIN_SHORT.equals(ttype) || FinTransactionType.EQTYPE_SWAP_MARGIN_SHORT.equals(ttype))
                interestCostFlag = -1;
            double transCost = tparams.getCommissionFee() + tparams.getCommissionFeeTax() + tparams.getImportedSetupFee() + tparams.getImportedSetupFeeTax() + tparams.getImportedManagementFee() + tparams.getImportedManagementFeeTax() + (double)interestCostFlag * tparams.getImportedInterestFee() + (double)interestCostFlag * tparams.getImportedInterestFeeTax();
            if(ttype.getCateg() == FinTransactionCateg.EQTYPE_CLOSE_MARGIN)
            {
                int cflag = getSide(tparams.getFinTransactionType()).cashFlowDir();
                double grossGain = (double)cflag * (execAmt - tparams.getImportedPaidValue()) - transCost;
                SubAccount subAccount = getSubAccount(tparams.getAccountId(), tparams.getSubAccountId());
                double capitalGainTax = getBusinessLogicProvider().calcCapitalGainTax(subAccount, tparams.getTaxType(), grossGain);
                tparams.setCapitalGainTax(capitalGainTax);
                tparams.setCapitalGain(grossGain - capitalGainTax);
                tparams.setNetAmount(tparams.getCapitalGain());
            } else
            if(ttype == FinTransactionType.EQTYPE_SWAP_MARGIN_SHORT)
            {
                double grossGain = tparams.getImportedPaidValue() - transCost - tparams.getTransferedAssetBookValue() - tparams.getTransferedAssetSetupFee() - tparams.getTransferedAssetSetupFeeTax() - tparams.getTransferedAssetMngFee() - tparams.getTransferedAssetMngFeeTax();
                SubAccount subAccount = getSubAccount(tparams.getAccountId(), tparams.getSubAccountId());
                double capitalGainTax = getBusinessLogicProvider().calcCapitalGainTax(subAccount, tparams.getTaxType(), grossGain);
                tparams.setCapitalGainTax(capitalGainTax);
                tparams.setCapitalGain(grossGain - capitalGainTax);
                tparams.setNetAmount(tparams.getImportedPaidValue() - transCost - capitalGainTax);
            } else
            if(ttype == FinTransactionType.EQTYPE_SWAP_MARGIN_LONG)
                tparams.setNetAmount(-tparams.getImportedPaidValue() - transCost);
        }
    }

    protected SubAccount getSubAccount(long account_id, long sub_account_id)
        throws DataException
    {
        SubAccount subAccount = GtlUtils.getAccountManager().getSubAccount(account_id, sub_account_id);
        return subAccount;
        NotFoundException e;
        e;
        throw new DataFindException("SubAccount not found (account_id/sub_account_id):" + account_id + "/" + sub_account_id);
    }

    protected EqtypeContractParams getContract(EqtypeFinTransactionParams tparams)
        throws DataException, RuntimeSystemException
    {
        return getPersistenceManager().getContract(tparams.getAccountId(), tparams.getSubAccountId(), tparams.getProductId(), tparams.getMarketId(), ContractTypeEnum.getContractType(tparams.getFinTransactionType()), tparams.getPrice(), tparams.getDeliveryDate(), getContractCloseDate(tparams.getDeliveryDate()));
    }

    protected void processNewMarginOrderExecution(EqTypeOrderExecution exec)
        throws DataException, RuntimeSystemException
    {
        EqtypeFinTransactionParams tparams = new EqtypeFinTransactionParams();
        setMarketOrderedTransDefaultValues(tparams);
        setExecutionInfoToMarketOrderedTrans(tparams, exec);
        EqtypeContractParams cparams = getContract(tparams);
        if(cparams == null)
        {
            cparams = new EqtypeContractParams();
            setContractDefaultValues(cparams);
            updateContractOpenFromMarketOrderedTrans(cparams, tparams);
            getPersistenceManager().saveNewContract(cparams);
        } else
        {
            updateContractOpenFromMarketOrderedTrans(cparams, tparams);
            getPersistenceManager().updateContractByTrans(cparams);
        }
        setMarginNetAmount(tparams);
        tparams.setContractId(cparams.getContractId());
        getPersistenceManager().saveNewFinTransaction(tparams);
        notifyGtl(tparams);
    }

    protected void processCloseMarginOrderExecution(EqTypeOrderExecution exec, long contractId)
        throws DataException, RuntimeSystemException
    {
        EqtypeFinTransactionParams tparams = new EqtypeFinTransactionParams();
        setMarketOrderedTransDefaultValues(tparams);
        setExecutionInfoToMarketOrderedTrans(tparams, exec);
        EqtypeContractParams cparams = getPersistenceManager().getContract(contractId);
        if(cparams == null)
        {
            String msg = "processCloseMarginOrderExecution dosen't exist for acct(" + tparams.getAccountId() + "fType(" + tparams.getFinTransactionType() + "), subAcct(" + tparams.getSubAccountId() + "), product(" + tparams.getProductId() + "), price(" + tparams.getPrice() + "), openDate(" + tparams.getDeliveryDate() + ")";
            m_log.error(msg);
            throw new RuntimeSystemException(msg);
        } else
        {
            tparams.setContractId(cparams.getContractId());
            updateContractCloseFromMarketOrderedTrans(cparams, tparams);
            getPersistenceManager().updateContractByTrans(cparams);
            setMarginNetAmount(tparams);
            getPersistenceManager().saveNewFinTransaction(tparams);
            notifyGtl(tparams);
            return;
        }
    }

    protected void processSwapMarginOrderExecution(EqTypeOrderExecution exec, long contractId)
        throws DataException
    {
        EqtypeFinTransactionParams tparams = new EqtypeFinTransactionParams();
        List assetUnitSales = null;
        setMarketOrderedTransDefaultValues(tparams);
        setExecutionInfoToMarketOrderedTrans(tparams, exec);
        EqtypeContractParams cparams = getPersistenceManager().getContract(contractId);
        if(cparams == null)
        {
            String msg = "processSwapMarginOrderExecution dosen't exist for Acct(" + tparams.getAccountId() + "FinType(" + tparams.getFinTransactionType() + "), subAcct(" + tparams.getSubAccountId() + "), product(" + tparams.getProductId() + "), price(" + tparams.getPrice() + "), openDate(" + tparams.getDeliveryDate() + ")";
            m_log.error(msg);
            throw new RuntimeSystemException(msg);
        }
        tparams.setContractId(cparams.getContractId());
        assetUnitSales = updateContractSwapFromMarketOrderedTrans(cparams, tparams);
        getPersistenceManager().updateContractByTrans(cparams);
        setMarginNetAmount(tparams);
        getPersistenceManager().saveNewFinTransaction(tparams);
        notifyGtl(tparams);
        if(assetUnitSales != null)
        {
            int len = assetUnitSales.size();
            for(int i = 0; i < len; i++)
            {
                AssetUnitSalesParams sale = (AssetUnitSalesParams)assetUnitSales.get(i);
                sale.setFinTransactionId(tparams.getFinTransactionId());
                getPersistenceManager().saveNewAssetUnitSales(sale);
            }

        }
    }

    protected EqTypeOrderExecution getSettlementExecution(EqTypeOrderExecution exec, SettleContractEntry settle)
        throws DataException, RuntimeSystemException
    {
        if(settle == null)
        {
            String msg = "getSettlementExecution: settle should not be null for acct(" + exec.getAccountId() + "), subAccountId(" + exec.getSubAccountId() + "), orderType(" + exec.getOrderType() + "), productId(" + exec.getProductId() + "), marketId(" + exec.getMarketId() + "), execPrice(" + exec.getExecutionPrice() + "), execQuantity(" + exec.getExecutionQuantity() + ")";
            m_log.error(msg);
            throw new RuntimeSystemException(msg);
        }
        if(exec.getExecutionQuantity() < settle.getQuantity())
        {
            String msg = "getSettlementExecution: settlement num(" + settle.getQuantity() + ") > execution num(" + exec.getExecutionQuantity() + ")";
            m_log.error(msg);
            throw new RuntimeSystemException(msg);
        }
        EqtypeOrderUnitRow ounit = EqtypeOrderUnitDao.findRowByPk(exec.getOrderUnitId());
        if(ounit == null)
        {
            String msg = "getSettlementExecution: No corresponding order unit for execId(" + exec.getOrderExecutionId() + "acct(" + exec.getAccountId() + "),subAcct(" + exec.getSubAccountId() + "),product(" + exec.getProductId() + "),market(" + exec.getMarketId() + ")";
            m_log.error(msg);
            throw new RuntimeSystemException(msg);
        }
        EqtypeOrderExecutionRow eRow = (EqtypeOrderExecutionRow)exec.getDataSourceObject();
        if(eRow == null)
        {
            String msg = "getSettlementExecution: no data soruce object for execution acct(" + exec.getAccountId() + "), subAcct(" + exec.getSubAccountId() + "), execId(" + exec.getOrderExecutionId() + "), productId(" + exec.getProductId() + ")";
            m_log.error(msg);
            throw new RuntimeSystemException(msg);
        }
        EqtypeOrderExecutionParams eparams = new EqtypeOrderExecutionParams(eRow);
        eparams.setExecQuantity(settle.getQuantity());
        EqtypeContractParams cparams = getPersistenceManager().getContract(settle.getContractId());
        if(cparams == null)
        {
            String msg = "processSwapMarginOrderExecution dosen't exist for Acct(" + exec.getAccountId() + "OrderType(" + exec.getOrderType() + "), subAcct(" + exec.getSubAccountId() + "), product(" + exec.getProductId() + "), price(" + exec.getExecutionPrice() + "), openDate(" + exec.getDeliveryDate() + ")";
            m_log.error(msg);
            throw new RuntimeSystemException(msg);
        }
        if(exec.getOrderType() == OrderTypeEnum.SWAP_MARGIN_LONG || exec.getOrderType() == OrderTypeEnum.SWAP_MARGIN_SHORT)
            eparams.setExecPrice(cparams.getContractPrice());
        else
            eparams.setExecPrice(exec.getExecutionPrice());
        eparams.setAccountId(exec.getAccountId());
        eparams.setSubAccountId(exec.getSubAccountId());
        eparams.setBranchId(exec.getBranchId());
        eparams.setCreatedTimestamp(exec.getExecutionTimestamp());
        eparams.setDeleteFlag(BooleanEnum.FALSE);
        eparams.setDeliveryDate(exec.getDeliveryDate());
        eparams.setExecSerialNo(exec.getExecutionSerialNo());
        eparams.setExecTimestamp(exec.getExecutionTimestamp());
        eparams.setLastUpdatedTimestamp(exec.getExecutionTimestamp());
        eparams.setMarketId(exec.getMarketId());
        eparams.setOrderExecutionId(exec.getOrderExecutionId());
        eparams.setOrderId(ounit.getOrderId());
        eparams.setOrderUnitId(ounit.getOrderUnitId());
        eparams.setOrderType(exec.getOrderType());
        eparams.setProductId(exec.getProductId());
        eparams.setProductType(exec.getProductType());
        eparams.setTraderId(exec.getTraderId());
        EqtypeOrderExecutionRow erow = eparams;
        return new EqTypeOrderExecutionImpl(erow);
    }

    public void processContractOrderExecution(EqTypeOrderExecution exec)
        throws RuntimeSystemException
    {
        try
        {
            if(exec.getOrderType() == OrderTypeEnum.MARGIN_LONG || exec.getOrderType() == OrderTypeEnum.MARGIN_SHORT)
            {
                processNewMarginOrderExecution(exec);
            } else
            {
                String msg = "processContractOrderExecution: wrong order type(" + exec.getOrderType() + ") for " + "acct(" + exec.getAccountId() + "), subAcct(" + exec.getSubAccountId() + "), product(" + exec.getProductId() + "), exec_date(" + exec.getExecutionTimestamp() + ")";
                m_log.error(msg);
                throw new RuntimeSystemException(msg);
            }
        }
        catch(DataException e)
        {
            String msg = "processContractOrderExecution: got an error";
            throw new RuntimeSystemException(msg, e);
        }
    }

    public void processContractOrderExecution(EqTypeOrderExecution exec, SettleContractEntry settle)
        throws RuntimeSystemException
    {
        try
        {
            if(exec.getOrderType() == OrderTypeEnum.MARGIN_LONG || exec.getOrderType() == OrderTypeEnum.MARGIN_SHORT)
            {
                processNewMarginOrderExecution(exec);
            } else
            {
                if(settle == null)
                {
                    String msg = "processContractOrderExecution: acct(" + exec.getAccountId() + "), subAcct(" + exec.getSubAccountId() + "), product(" + exec.getProductId() + "), exec_date(" + exec.getExecutionTimestamp() + "), get null for settles.";
                    m_log.error(msg);
                    throw new RuntimeSystemException(msg);
                }
                if(exec.getOrderType().equals(OrderTypeEnum.CLOSE_MARGIN_LONG) || exec.getOrderType().equals(OrderTypeEnum.CLOSE_MARGIN_SHORT))
                {
                    exec = getSettlementExecution(exec, settle);
                    processCloseMarginOrderExecution(exec, settle.getContractId());
                } else
                if(exec.getOrderType().equals(OrderTypeEnum.SWAP_MARGIN_LONG) || exec.getOrderType().equals(OrderTypeEnum.SWAP_MARGIN_SHORT))
                {
                    exec = getSettlementExecution(exec, settle);
                    processSwapMarginOrderExecution(exec, settle.getContractId());
                } else
                {
                    String msg = "processContractOrderExecution: unsupported order type(" + exec.getOrderType() + ")";
                    m_log.error(msg);
                    throw new RuntimeSystemException(msg);
                }
            }
        }
        catch(DataException e)
        {
            String msg = "Error while processing Contract Order Execution:" + e.getMessage();
            m_log.error(msg, e);
            throw new RuntimeSystemException(msg, e);
        }
    }

    public void processContractOrderExecution(EqTypeOrderExecution exec, SettleContractEntry settles[])
        throws RuntimeSystemException
    {
        int len;
        double total;
        try
        {
            if(exec.getOrderType().equals(OrderTypeEnum.MARGIN_LONG) || exec.getOrderType().equals(OrderTypeEnum.MARGIN_SHORT))
            {
                processNewMarginOrderExecution(exec);
                return;
            }
        }
        catch(DataException e)
        {
            String msg = "Error while processing Contract Order Execution:" + e.getMessage();
            m_log.error(msg, e);
            throw new RuntimeSystemException(msg, e);
        }
        if(settles == null)
        {
            String msg = "processContractOrderExecution : acct(" + exec.getAccountId() + "), subAcct(" + exec.getSubAccountId() + "), product(" + exec.getProductId() + "), exec_date(" + exec.getExecutionTimestamp() + "): settles should not be null";
            m_log.error(msg);
            throw new RuntimeSystemException(msg);
        }
        len = settles.length;
        total = 0.0D;
        for(int i = 0; i < len; i++)
            total += settles[i].getQuantity();

        if(total > exec.getExecutionQuantity())
        {
            String msg = "processContractOrderExecution: settlement number(" + total + " exceeds the execution number(" + exec.getExecutionQuantity() + ")";
            m_log.error(msg);
            throw new RuntimeSystemException(msg);
        }
        for(int i = 0; i < len; i++)
            processContractOrderExecution(exec, settles[i]);

    }

    protected void undoAssetOrderExecutionTrans(EqtypeFinTransactionRow trow)
        throws DataException, RuntimeSystemException
    {
        EqtypeFinTransactionParams trans = new EqtypeFinTransactionParams(trow);
        reverseAssetPositionByTrans(trans, getSide(trans.getFinTransactionType()));
        trans.setDeleteFlag(BooleanEnum.TRUE);
        trans.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        getPersistenceManager().updateFinTransaction(trans);
    }

    protected void undoNewMarginTrans(EqtypeFinTransactionRow trow)
        throws DataException, RuntimeSystemException
    {
        EqtypeFinTransactionParams trans = new EqtypeFinTransactionParams(trow);
        EqtypeContractParams cparams = getPersistenceManager().getContract(trans.getContractId());
        if(cparams == null)
        {
            String msg = "undoNewMarginTrans: no contract for contractId(" + trans.getContractId() + "), transId(" + trans.getFinTransactionId() + "), acct(" + trans.getAccountId() + "), subAcct(" + trans.getSubAccountId() + "), tradeType(" + trans.getFinTransactionType() + "), finDate(" + trans.getFinTransactionTimestamp() + "), product(" + trans.getProductId() + ")";
            m_log.error(msg);
            throw new RuntimeSystemException(msg);
        } else
        {
            revertContractOpenFromMarketOrderedTrans(cparams, trans);
            getPersistenceManager().updateContractByTrans(cparams);
            trans.setDeleteFlag(BooleanEnum.TRUE);
            trans.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            getPersistenceManager().updateFinTransaction(trans);
            return;
        }
    }

    protected void undoCloseMarginTrans(EqtypeFinTransactionRow trow)
        throws DataException, RuntimeSystemException
    {
        EqtypeFinTransactionParams trans = new EqtypeFinTransactionParams(trow);
        EqtypeContractParams cparams = getPersistenceManager().getContract(trans.getContractId());
        if(cparams == null)
        {
            String msg = "undoCloseMarginTrans: no contract for contractId(" + trans.getContractId() + "), transId(" + trans.getFinTransactionId() + "), acct(" + trans.getAccountId() + "), subAcct(" + trans.getSubAccountId() + "), tradeType(" + trans.getFinTransactionType() + "), finDate(" + trans.getFinTransactionTimestamp() + "), product(" + trans.getProductId() + ")";
            m_log.error(msg);
            throw new RuntimeSystemException(msg);
        } else
        {
            revertContractCloseFromMarketOrderedTrans(cparams, trans);
            getPersistenceManager().updateContractByTrans(cparams);
            trans.setDeleteFlag(BooleanEnum.TRUE);
            trans.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            getPersistenceManager().updateFinTransaction(trans);
            return;
        }
    }

    protected void undoSwapMarginTrans(EqtypeFinTransactionRow trow)
        throws DataException, RuntimeSystemException
    {
        EqtypeFinTransactionParams trans = new EqtypeFinTransactionParams(trow);
        EqtypeContractParams cparams = getPersistenceManager().getContract(trans.getContractId());
        if(cparams == null)
        {
            String msg = "undoSwapMarginTrans: no contract for contractId(" + trans.getContractId() + "), transId(" + trans.getFinTransactionId() + "), acct(" + trans.getAccountId() + "), subAcct(" + trans.getSubAccountId() + "), tradeType(" + trans.getFinTransactionType() + "), finDate(" + trans.getFinTransactionTimestamp() + "), product(" + trans.getProductId() + ")";
            m_log.error(msg);
            throw new RuntimeSystemException(msg);
        } else
        {
            revertContractSwapFromMarketOrderedTrans(cparams, trans);
            getPersistenceManager().updateContractByTrans(cparams);
            trans.setDeleteFlag(BooleanEnum.TRUE);
            trans.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            getPersistenceManager().updateFinTransaction(trans);
            return;
        }
    }

    protected void undoFinTransaction(EqtypeFinTransactionRow trow)
        throws DataException, RuntimeSystemException
    {
        switch(trow.getFinTransactionCateg().intValue())
        {
        case 20: // '\024'
            undoAssetOrderExecutionTrans(trow);
            break;

        case 30: // '\036'
            undoNewMarginTrans(trow);
            break;

        case 40: // '('
            undoCloseMarginTrans(trow);
            break;

        case 60: // '<'
            undoSwapMarginTrans(trow);
            break;

        default:
            String msg = "undoFinTransaction: unsupported FinTransactionCateg(" + trow.getFinTransactionCateg() + ") for acct(" + trow.getAccountId() + "), subAcct(" + trow.getSubAccountId() + "), prouct(" + trow.getProductId() + "), tradeDate(" + trow.getFinTransactionTimestamp() + "), deliveryDate(" + trow.getDeliveryDate() + ")";
            m_log.error(msg);
            throw new RuntimeSystemException(msg);
        }
    }

    public void undoExecution(long execId)
        throws DataException, RuntimeSystemException
    {
        List transList = getPersistenceManager().getFinTransactionForExec(execId);
        int len = transList.size();
        for(int i = 0; i < len; i++)
        {
            EqtypeFinTransactionRow row = (EqtypeFinTransactionRow)transList.get(i);
            undoFinTransaction(row);
            try
            {
                m_finApp.getGeneralizedFinTransactionManager().undoTransaction(row.getFinTransactionId());
            }
            catch(NotFoundException nfe)
            {
                String msg = "undoFinTransaction: can not find generalized transanction for id : " + row.getFinTransactionId();
                m_log.error(msg);
                throw new RuntimeSystemException(msg);
            }
        }

    }

    public Date getOrderDate(EqtypeOrderUnitParams ounit)
    {
        String bizDate;
        SimpleDateFormat format;
        bizDate = ounit.getBizDate();
        format = new SimpleDateFormat("yyyyMMdd");
        return format.parse(bizDate);
        ParseException e;
        e;
        return null;
    }

    public void setFinTransactionNetAmount(EqtypeFinTransactionParams tparams)
        throws DataException
    {
        FinTransactionCateg cat = tparams.getFinTransactionCateg();
        if(cat.equals(FinTransactionCateg.EQTYPE_ASSET))
            setAssetNetAmount(tparams);
        else
        if(cat.equals(FinTransactionCateg.EQTYPE_OPEN_MARGIN) || cat.equals(FinTransactionCateg.EQTYPE_CLOSE_MARGIN) || cat.equals(FinTransactionCateg.EQTYPE_SWAP_MARGIN))
            setMarginNetAmount(tparams);
    }

    public List getContractCloseSpec(long accountId, long subAccountId, long productId, ContractTypeEnum cType, 
            long orderId, long orderUnitId, double settleQnt)
        throws DataException
    {
        List contracts = getPersistenceManager().getContracts(accountId, subAccountId, cType, productId);
        int len = contracts != null ? contracts.size() : 0;
        List rlt = new ArrayList(len);
        double remaining = settleQnt;
        int num = 0;
        for(int i = 0; remaining > 0.0D && i < len; i++)
        {
            EqtypeContractRow cRow = (EqtypeContractRow)contracts.get(i);
            EqtypeLockedContractDetailsRow lckContract = getPersistenceManager().getLockedContractDetails(cRow.getContractId());
            EqtypeClosingContractSpecParams close = new EqtypeClosingContractSpecParams();
            close.setAccountId(cRow.getAccountId());
            close.setSubAccountId(cRow.getSubAccountId());
            close.setContractId(cRow.getContractId());
            close.setOrderId(orderId);
            close.setOrderUnitId(orderUnitId);
            close.setExecutedQuantity(0.0D);
            double shares = cRow.getQuantity() - lckContract.getLockedQuantity();
            if(shares <= 0.0D)
                continue;
            double settle;
            if(remaining >= shares)
                settle = shares;
            else
                settle = remaining;
            remaining -= settle;
            close.setQuantity(settle);
            close.setConfirmedQuantity(settle);
            rlt.add(close);
        }

        return rlt;
    }

    public double calcAmount(double price, double quantity, ProductTypeEnum pType, long productId)
        throws RuntimeSystemException
    {
        return getBusinessLogicProvider().calcExecutionAmount(pType, productId, price, quantity, null);
    }

    public List getAllFinTransactionByDeliveryDate(long accountId, long subAccountId, Date deliveryDate)
        throws DataException, RuntimeSystemException
    {
        PersistentDataManager dmgr = getPersistenceManager();
        List finTrans = dmgr.getFinTransactionByDeliveryDate(accountId, subAccountId, deliveryDate);
        List genTrans = dmgr.getGenTransactionByDeliveryDate(accountId, subAccountId, deliveryDate);
        int finIdx = 0;
        int finLen = finTrans.size();
        int genIdx = 0;
        int genLen = genTrans.size();
        List trans = new ArrayList(finLen + genLen);
        if(DBG)
        {
            for(int i = 0; i < finLen; i++)
            {
                EqtypeFinTransactionRow fRow = (EqtypeFinTransactionRow)finTrans.get(i);
                m_log.debug("FIN:" + fRow.toString());
            }

            for(int i = 0; i < genLen; i++)
            {
                GenFinTransactionRow gRow = (GenFinTransactionRow)genTrans.get(i);
                m_log.debug("GEN:" + gRow.toString());
            }

        }
        if(finLen == 0)
        {
            for(int i = 0; i < genLen; i++)
                trans.add(createFinTransFromGen((GenFinTransactionRow)genTrans.get(i)));

            return trans;
        }
        if(genLen == 0)
        {
            for(int i = 0; i < finLen; i++)
            {
                EqtypeFinTransactionParams tparams = new EqtypeFinTransactionParams((EqtypeFinTransactionRow)finTrans.get(i));
                trans.add(tparams);
            }

            return trans;
        }
        for(genIdx = 0; genIdx < genLen; genIdx++)
        {
            GenFinTransactionRow gTran = (GenFinTransactionRow)genTrans.get(genIdx);
            EqtypeFinTransactionRow fTran = null;
            finIdx = 0;
            do
            {
                if(finIdx >= finLen)
                    break;
                EqtypeFinTransactionRow fRow = (EqtypeFinTransactionRow)finTrans.get(finIdx);
                if(fRow.getFinTransactionId() == gTran.getTransactionId())
                {
                    fTran = fRow;
                    break;
                }
                finIdx++;
            } while(true);
            if(fTran != null)
                trans.add(new EqtypeFinTransactionParams(fTran));
            else
                trans.add(createFinTransFromGen(gTran));
        }

        return trans;
    }

    public EqtypeFinTransactionParams createFinTransFromGen(GenFinTransactionRow gTran)
        throws RuntimeSystemException
    {
        EqtypeFinTransactionParams fTran = new EqtypeFinTransactionParams();
        fTran.setAccountId(gTran.getAccountId());
        fTran.setSubAccountId(gTran.getSubAccountId());
        fTran.setFinTransactionType(FinTransactionType.OTHER);
        fTran.setFinTransactionTimestamp(gTran.getTransactionDate());
        fTran.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        fTran.setLastUpdatedTimestamp(fTran.getCreatedTimestamp());
        fTran.setDeliveryDate(gTran.getDeliveryDate());
        fTran.setNetAmount(gTran.getNetAmount());
        fTran.setPrice(1.0D);
        fTran.setQuantity(fTran.getNetAmount());
        fTran.setFinTransactionCateg(FinTransactionCateg.OTHER);
        return fTran;
    }

    public double getOriginalContractPrice(EqtypeFinTransactionParams tparams)
        throws RuntimeSystemException
    {
        if(!tparams.getContractIdIsNull())
        {
            EqtypeContractRow cRow;
            try
            {
                cRow = getPersistenceManager().getContract(tparams.getContractId());
            }
            catch(DataException e)
            {
                cRow = null;
            }
            if(cRow != null)
                return cRow.getContractPrice();
            else
                return 0.0D;
        } else
        {
            return 0.0D;
        }
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
    protected static final FinApp m_finApp;
    private ProductTypeEnum m_tradingModuleType;
    protected static PersistentDataManager m_PDM = new PersistentDataManager();
    private TradingModule m_TradingModule;

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypePositionManagerHelper.class);
        DBG = m_log.ison();
        m_finApp = (FinApp)Services.getService(com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp.class);
    }

}
