// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GeneralizedFinTransactionManagerImpl.java

package com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls;

import com.fitechlabs.dbind.ArrayListPage;
import com.fitechlabs.dbind.ListPage;
import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import java.text.SimpleDateFormat;
import java.util.*;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls:
//            CalendarUtils

public class GeneralizedFinTransactionManagerImpl
    implements GeneralizedFinTransactionManager
{

    public GeneralizedFinTransactionManagerImpl()
    {
    }

    public long createTransactionId()
    {
        return GenFinTransactionDao.newPkValue();
        DataException e;
        e;
        throw new RuntimeSystemException("Exception thrown when trying to allocate Id for new FinTransaction", e);
    }

    public ProcessingResult addTransaction(FinTransaction finTran)
    {
        if(finTran instanceof GeneralizedFinTransaction)
            return addTransaction((GeneralizedFinTransaction)finTran);
        else
            throw new IllegalArgumentException("Arguement is not of type GeneralizedFinTransaction");
    }

    public ProcessingResult addTransaction(GeneralizedFinTransaction tran)
    {
        long accountId;
        long subAccountId;
        double netAmount;
        GenFinTransactionParams params;
        accountId = tran.getSubAccount().getMainAccount().getAccountId();
        subAccountId = tran.getSubAccount().getSubAccountId();
        netAmount = tran.getNetAmount();
        params = new GenFinTransactionParams();
        params.setDeleteFlag(BooleanEnum.FALSE);
        params.setTransactionId(tran.getId());
        params.setTransactionDate(tran.getTransactionTimestamp());
        params.setTransactionType(tran.getFinTransactionType());
        params.setTransactionDescription(tran.getGenericDescription());
        params.setTradeModuleName(tran.getTradingModuleName());
        params.setDeliveryDate(tran.getDeliveryDate());
        params.setNetAmount(netAmount);
        params.setAccountId(accountId);
        params.setSubAccountId(subAccountId);
        java.util.Date bizDate = GtlUtils.getTradingSystem().getBizDate();
        params.setBizDate(GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(bizDate));
        java.sql.Timestamp now = GtlUtils.getSystemTimestamp();
        params.setCreatedTimestamp(now);
        params.setLastUpdatedTimestamp(now);
        QueryProcessor qp = Processors.getDefaultProcessor();
        qp.doInsertQuery(params);
        AccountManager am = GtlUtils.getAccountManager();
        SubAccount subAccount = am.getSubAccount(accountId, subAccountId);
        subAccount.adjustCashBalance(netAmount);
        return ProcessingResult.newSuccessResultInstance();
        DataException ex;
        ex;
        String msg = "failed to add transaction: " + tran.getId() + tran.getGenericDescription();
        m_log.error(msg, ex);
        throw new RuntimeSystemException(msg, ex);
        NotFoundException nfe;
        nfe;
        String msg = "Failed to get SubAccount object for accountId-subAccountId" + accountId + "-" + subAccountId;
        m_log.error(msg, nfe);
        throw new RuntimeSystemException(msg, nfe);
    }

    public ProcessingResult notifyTransaction(GeneralizedFinTransaction tran)
    {
        SubAccount subAccount;
        subAccount = tran.getSubAccount();
        long accountId = subAccount.getMainAccount().getAccountId();
        long subAccountId = subAccount.getSubAccountId();
        QueryProcessor qp;
        GenFinTransactionRow row;
        qp = Processors.getDefaultProcessor();
        row = null;
        try
        {
            row = GenFinTransactionDao.findRowByPk(tran.getId());
        }
        catch(DataFindException ignore) { }
        if(row == null)
            return addTransaction(tran);
        ProcessingResult pr;
        double beforeUpdateTranNetAmount = row.getNetAmount();
        double newNetAmount = tran.getNetAmount();
        GenFinTransactionParams params = new GenFinTransactionParams(row);
        params.setTransactionDate(tran.getTransactionTimestamp());
        params.setTransactionType(tran.getFinTransactionType());
        params.setTransactionDescription(tran.getGenericDescription());
        params.setTradeModuleName(tran.getTradingModuleName());
        params.setDeliveryDate(tran.getDeliveryDate());
        params.setNetAmount(newNetAmount);
        java.sql.Timestamp now = GtlUtils.getSystemTimestamp();
        params.setLastUpdatedTimestamp(now);
        qp.doUpdateQuery(params);
        double cashBalaneAdjustAmount = newNetAmount - beforeUpdateTranNetAmount;
        pr = subAccount.adjustCashBalance(cashBalaneAdjustAmount);
        return pr;
        DataException ex;
        ex;
        String msg = "failed to process notifyTransaction : " + tran.getId() + tran.getGenericDescription();
        m_log.error(msg, ex);
        throw new RuntimeSystemException(msg, ex);
    }

    public ProcessingResult undoTransaction(long tranId)
        throws NotFoundException
    {
        GenFinTransactionPK tranKey = new GenFinTransactionPK(tranId);
        ProcessingResult pr;
        FinTransaction finTran = getGeneralizedTransaction(tranId);
        Hashtable map = new Hashtable();
        map.put("delete_flag", BooleanEnum.TRUE);
        map.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());
        Processors.getDefaultProcessor().doUpdateQuery(tranKey, map);
        double cashBalanceRollbackAmount = finTran.getNetAmount() * -1D;
        pr = finTran.getSubAccount().adjustCashBalance(cashBalanceRollbackAmount);
        return pr;
        DataException ex;
        ex;
        String msg = "failed to remove transaction: " + tranId;
        m_log.error(msg, ex);
        throw new RuntimeSystemException(msg, ex);
    }

    public ListPage searchTransactions(SubAccount subAccount, DateRangePaginationQueryParamsSpec querySpec)
    {
        return searchTransactions(subAccount, NULL_FIN_TRAN_TYPE_ARRAY, querySpec);
    }

    public ListPage searchTransactions(SubAccount subAccount, FinTransactionType transactionType, DateRangePaginationQueryParamsSpec querySpec)
    {
        return searchTransactions(subAccount, new FinTransactionType[] {
            transactionType
        }, querySpec);
    }

    public ListPage searchTransactions(SubAccount subAccount, FinTransactionType transactionTypes[], DateRangePaginationQueryParamsSpec querySpec)
    {
        java.util.Date from;
        java.util.Date to;
        int pageSize;
        int pageNumber;
        String where;
        Object bindVars[];
        from = CalendarUtils.clearTimeFields(querySpec.getDateRangeQueryParamsSpec().getFromDate());
        to = CalendarUtils.clearTimeFields(CalendarUtils.getNextCalendarDay(querySpec.getDateRangeQueryParamsSpec().getToDate()));
        PaginationQueryParamsSpec pagination = querySpec.getPaginationQueryParamsSpec();
        pageSize = pagination != null && !PaginationQueryParamsSpec.ALL_PAGES.equals(pagination) ? pagination.getPageSize() : -1;
        pageNumber = pagination != null && !PaginationQueryParamsSpec.ALL_PAGES.equals(pagination) ? pagination.getPageNumber() : -1;
        if(transactionTypes.length == 0)
        {
            where = "account_id = ? and sub_account_id = ? and transaction_date  >= ? and  transaction_date  < ?  and (delete_flag is null or delete_flag = ?)";
            bindVars = (new Object[] {
                new Long(subAccount.getMainAccount().getAccountId()), new Long(subAccount.getSubAccountId()), from, to, BooleanEnum.FALSE
            });
        } else
        if(transactionTypes.length == 1)
        {
            where = "account_id = ? and sub_account_id =? and transaction_type = ? AND transaction_date >= ? and  transaction_date < ?  and (delete_flag is null or delete_flag = ?)";
            bindVars = (new Object[] {
                new Long(subAccount.getMainAccount().getAccountId()), new Long(subAccount.getSubAccountId()), new Long(transactionTypes[0].intValue()), from, to, BooleanEnum.FALSE
            });
        } else
        {
            StringBuffer tranTypeWhereBuf = new StringBuffer();
            for(int i = 0; i < transactionTypes.length; i++)
            {
                tranTypeWhereBuf.append(transactionTypes[i].intValue());
                if(i + 1 != transactionTypes.length)
                    tranTypeWhereBuf.append(",");
            }

            if(DBG)
                m_log.debug("transaction type where : " + tranTypeWhereBuf);
            where = "account_id = ? and sub_account_id =? and transaction_type in ( " + tranTypeWhereBuf.toString() + " ) AND " + "transaction_date >= ? and  transaction_date < ?" + " and (delete_flag is null or delete_flag = ?)";
            bindVars = (new Object[] {
                new Long(subAccount.getMainAccount().getAccountId()), new Long(subAccount.getSubAccountId()), from, to, BooleanEnum.FALSE
            });
        }
        String orderBy = "transaction_date, created_timestamp";
        ListPage rows;
        QueryProcessor qp = Processors.getDefaultProcessor();
        if(pageSize <= 0 || pageNumber < 0)
        {
            List tmpResults = qp.doFindAllQuery(GenFinTransactionRow.TYPE, where, "transaction_date, created_timestamp", null, bindVars);
            int tmpResultsSize = tmpResults.size();
            rows = new ArrayListPage(tmpResults, tmpResultsSize != 0 ? tmpResultsSize : 1, 0, tmpResultsSize);
        } else
        {
            rows = qp.doFindAllQuery(GenFinTransactionRow.TYPE, where, "transaction_date, created_timestamp", null, bindVars, pageSize, pageNumber);
        }
        return toPolymorphicFinTranList(subAccount, rows, querySpec.getDateRangeQueryParamsSpec());
        DataException de;
        de;
        String msg = "failed to query transaction, account_id:" + subAccount.getMainAccount().getAccountId() + ", subaccount id : " + subAccount.getSubAccountId() + ", from date: " + from + ", to date: " + to;
        m_log.error(msg, de);
        throw new RuntimeSystemException(msg, de);
    }

    public ListPage searchTransactions(SubAccount subAccount, FilterQueryParamsSpec filterSpec, PaginationQueryParamsSpec paginationSpec, SortKeySpec sortKeySpec)
    {
        int pageSize;
        int pageNumber;
        String where;
        Object bindVars[];
        String orderBy;
        pageSize = paginationSpec != null && !paginationSpec.equals(PaginationQueryParamsSpec.ALL_PAGES) ? paginationSpec.getPageSize() : -1;
        pageNumber = paginationSpec != null && !paginationSpec.equals(PaginationQueryParamsSpec.ALL_PAGES) ? paginationSpec.getPageNumber() : -1;
        if(filterSpec == null)
        {
            where = "account_id = ? and sub_account_id = ?  and (delete_flag is null or delete_flag = ?) ";
            bindVars = (new Object[] {
                new Long(subAccount.getMainAccount().getAccountId()), new Long(subAccount.getSubAccountId()), BooleanEnum.FALSE
            });
        } else
        {
            where = "account_id = ? and sub_account_id =?   and (delete_flag is null or delete_flag = ?) and " + filterSpec.getWhereCondition();
            Object filterSpecBindVars[] = filterSpec.getBindVars() != null ? filterSpec.getBindVars() : new Object[0];
            bindVars = new Object[filterSpecBindVars.length + 3];
            bindVars[0] = new Long(subAccount.getMainAccount().getAccountId());
            bindVars[1] = new Long(subAccount.getSubAccountId());
            bindVars[2] = BooleanEnum.FALSE;
            for(int i = 0; i < filterSpecBindVars.length; i++)
                bindVars[i + 3] = filterSpecBindVars[i];

        }
        orderBy = sortKeySpec != null && !sortKeySpec.isSortKeyNull() ? sortKeySpec.getSortKeySpec() : "transaction_date, created_timestamp";
        ListPage rows;
        QueryProcessor qp = Processors.getDefaultProcessor();
        if(pageSize <= 0 || pageNumber < 0)
        {
            List tmpResults = qp.doFindAllQuery(GenFinTransactionRow.TYPE, where, orderBy, null, bindVars);
            int tmpResultsSize = tmpResults.size();
            rows = new ArrayListPage(tmpResults, tmpResultsSize != 0 ? tmpResultsSize : 1, 0, tmpResultsSize);
        } else
        {
            rows = qp.doFindAllQuery(GenFinTransactionRow.TYPE, where, orderBy, null, bindVars, pageSize, pageNumber);
        }
        return toPolymorphicFinTranList(subAccount, rows);
        DataException de;
        de;
        String msg = "failed to query transaction, account_id:" + subAccount.getMainAccount().getAccountId() + ", subaccount id : " + subAccount.getSubAccountId() + ", FilterQueryParamsSpec: " + filterSpec + ", SortKeySpec:" + sortKeySpec;
        m_log.error(msg, de);
        throw new RuntimeSystemException(msg, de);
    }

    private ListPage toPolymorphicFinTranList(final SubAccount subAccount, final ListPage list, final DateRangeQueryParamsSpec dateRange)
    {
        ListPage transList;
        QueryProcessor qp = Processors.getDefaultProcessor();
        transList = (ListPage)qp.doTransaction(new TransactionCallback() {

            public Object process()
                throws DataNetworkException, DataQueryException, DataCallbackException
            {
                Map perModuleFinTransMap = new HashMap();
                List genFinTransList = new ArrayList(list.size());
                int size = list.size();
                for(int i = 0; i < size; i++)
                {
                    GenFinTransactionRow row = (GenFinTransactionRow)list.get(i);
                    String tradingModuleName = row.getTradeModuleName();
                    FinTransaction finTx = null;
                    if(tradingModuleName == null)
                    {
                        finTx = GeneralizedFinTransactionManagerImpl.toGeneralizedFinTransaction(subAccount, row);
                    } else
                    {
                        Map finTransMap = (Map)perModuleFinTransMap.get(tradingModuleName);
                        if(finTransMap == null)
                        {
                            finTransMap = GtlUtils.getTradingModule(tradingModuleName).getFinTransactionManager().getTransactions(subAccount, dateRange);
                            perModuleFinTransMap.put(tradingModuleName, finTransMap);
                        }
                        finTx = (FinTransaction)finTransMap.get(new Long(row.getTransactionId()));
                    }
                    genFinTransList.add(finTx);
                }

                return new ArrayListPage(genFinTransList, list.pageSize(), list.pageNumber(), list.totalSize());
            }

        }
);
        return transList;
        DataException de;
        de;
        String msg = "Exception while searching transactions.";
        m_log.error("Exception while searching transactions.", de);
        throw new RuntimeSystemException("Exception while searching transactions.", de);
    }

    private ListPage toPolymorphicFinTranList(final SubAccount subAccount, final ListPage list)
    {
        ListPage transList;
        QueryProcessor qp = Processors.getDefaultProcessor();
        transList = (ListPage)qp.doTransaction(new TransactionCallback() {

            public Object process()
                throws DataNetworkException, DataQueryException, DataCallbackException
            {
                Map perModuleFinTransMap = new HashMap();
                List genFinTransList = new ArrayList(list.size());
                int size = list.size();
                for(int i = 0; i < size; i++)
                {
                    GenFinTransactionRow row = (GenFinTransactionRow)list.get(i);
                    String tradingModuleName = row.getTradeModuleName();
                    FinTransaction finTx = null;
                    if(tradingModuleName == null)
                    {
                        finTx = GeneralizedFinTransactionManagerImpl.toGeneralizedFinTransaction(subAccount, row);
                    } else
                    {
                        Map finTransMap = (Map)perModuleFinTransMap.get(tradingModuleName);
                        if(finTransMap == null)
                        {
                            finTransMap = GtlUtils.getTradingModule(tradingModuleName).getFinTransactionManager().getTransactions(subAccount, DateRangeQueryParamsSpec.ALL_DATE_RANGES);
                            perModuleFinTransMap.put(tradingModuleName, finTransMap);
                        }
                        finTx = (FinTransaction)finTransMap.get(new Long(row.getTransactionId()));
                    }
                    genFinTransList.add(finTx);
                }

                return new ArrayListPage(genFinTransList, list.pageSize(), list.pageNumber(), list.totalSize());
            }

        }
);
        return transList;
        DataException de;
        de;
        String msg = "Exception while searching transactions.";
        m_log.error("Exception while searching transactions.", de);
        throw new RuntimeSystemException("Exception while searching transactions.", de);
    }

    private static GeneralizedFinTransaction toGeneralizedFinTransaction(SubAccount subAccount, GenFinTransactionRow row)
    {
        GeneralizedFinTransaction gft = subAccount != null ? new GeneralizedFinTransaction(row.getTransactionId(), subAccount, row.getDeliveryDate(), row.getTransactionType(), row.getNetAmount(), row.getTransactionDescription(), row.getTradeModuleName(), row.getCreatedTimestamp(), row) : new GeneralizedFinTransaction(row.getTransactionId(), row.getAccountId(), row.getSubAccountId(), row.getDeliveryDate(), row.getTransactionType(), row.getNetAmount(), row.getTransactionDescription(), row.getTradeModuleName(), row.getCreatedTimestamp(), row);
        return gft;
    }

    public FinTransaction getGeneralizedTransaction(long transactionId)
        throws NotFoundException
    {
        GenFinTransactionRow row = GenFinTransactionDao.findRowByPk(transactionId);
        if(!BooleanEnum.TRUE.equals(row.getDeleteFlag()))
            return toGeneralizedFinTransaction(null, row);
        break MISSING_BLOCK_LABEL_73;
        DataFindException ignore;
        ignore;
        break MISSING_BLOCK_LABEL_73;
        DataException de;
        de;
        m_log.error(de.getMessage(), de);
        throw new RuntimeSystemException("Exception while getting Generalized Transaction for id :" + transactionId, de);
        throw new NotFoundException("No transaction is found with id : " + transactionId);
    }

    public FinTransaction getTransaction(long transactionId)
        throws NotFoundException
    {
        GenFinTransactionRow row;
        String tradingModuleName;
        row = GenFinTransactionDao.findDaoByPk(transactionId).getGenFinTransactionRow();
        if(BooleanEnum.TRUE.equals(row.getDeleteFlag()))
            throw new DataFindException("delete_flag is set to ON");
        tradingModuleName = row.getTradeModuleName();
        if(tradingModuleName == null)
            return toGeneralizedFinTransaction(null, row);
        TradingModule tm = GtlUtils.getTradingModule(tradingModuleName);
        return tm.getFinTransactionManager().getTransaction(transactionId);
        DataFindException dfe;
        dfe;
        throw new NotFoundException("No transaction is found with id : " + transactionId);
        DataException de;
        de;
        m_log.error(de.getMessage(), de);
        throw new RuntimeSystemException("Exception while getting Generalized Transaction for id :" + transactionId, de);
    }

    public Map getTransactions(SubAccount subAccount, long transactionIds[])
    {
        Map allFinTransMap = getTransactions(subAccount, DateRangeQueryParamsSpec.ALL_DATE_RANGES);
        Map retMap = new HashMap();
        for(int i = 0; i < transactionIds.length; i++)
        {
            Long id = new Long(transactionIds[i]);
            Object val = allFinTransMap.get(id);
            if(val != null)
                retMap.put(id, val);
        }

        return retMap;
    }

    public Map getTransactions(SubAccount subAccount, DateRangeQueryParamsSpec dateRange)
    {
        Object bvs[];
        java.util.Date from = CalendarUtils.clearTimeFields(dateRange.getFromDate());
        java.util.Date to = CalendarUtils.clearTimeFields(CalendarUtils.getNextCalendarDay(dateRange.getToDate()));
        String where = "account_id = ? and sub_account_id = ? and transaction_date  >= ? and  transaction_date  < ?  and (delete_flag is null or delete_flag = ?)";
        bvs = (new Object[] {
            new Long(subAccount.getAccountId()), new Long(subAccount.getSubAccountId()), from, to, BooleanEnum.FALSE
        });
        Map retMap;
        QueryProcessor qp = Processors.getDefaultProcessor();
        List rows = qp.doFindAllQuery(GenFinTransactionRow.TYPE, "account_id = ? and sub_account_id = ? and transaction_date  >= ? and  transaction_date  < ?  and (delete_flag is null or delete_flag = ?)", bvs);
        int size = rows.size();
        retMap = new HashMap(size);
        Map perModuleFinTransMap = new HashMap();
        for(int i = 0; i < size; i++)
        {
            GenFinTransactionRow r = (GenFinTransactionRow)rows.get(i);
            Long transactionId = new Long(r.getTransactionId());
            String tradingModuleName = r.getTradeModuleName();
            if(tradingModuleName == null)
            {
                retMap.put(transactionId, toGeneralizedFinTransaction(null, r));
                continue;
            }
            Map moduleFinTransMap = (Map)perModuleFinTransMap.get(tradingModuleName);
            if(moduleFinTransMap == null)
            {
                moduleFinTransMap = GtlUtils.getTradingModule(tradingModuleName).getFinTransactionManager().getTransactions(subAccount, dateRange);
                perModuleFinTransMap.put(tradingModuleName, moduleFinTransMap);
            }
            retMap.put(transactionId, moduleFinTransMap.get(transactionId));
        }

        return retMap;
        DataException de;
        de;
        String msg = "Exception while querying gen_fin_transaction rows given an array of transaction ids.";
        m_log.error("Exception while querying gen_fin_transaction rows given an array of transaction ids.", de);
        throw new RuntimeSystemException("Exception while querying gen_fin_transaction rows given an array of transaction ids.", de);
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
    private static final FinTransactionType NULL_FIN_TRAN_TYPE_ARRAY[] = new FinTransactionType[0];

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.GeneralizedFinTransactionManagerImpl.class);
        DBG = m_log.ison();
    }

}
