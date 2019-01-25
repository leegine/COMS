// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeFinTransactionManagerImpl.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.*;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeFinTransactionPK;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.CalendarUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.GtlQueryUtils;
import java.util.*;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls:
//            EqTypeCashBasedOrderTransactionImpl, EqTypeContractOpenTransactionImpl, EqTypeContractSettleTransactionImpl, EqTypeContractSwapTransactionImpl, 
//            AssetTransferFinTransactionImpl, CashTransferFinTransactionImpl, EqTypeFinTransactionImpl

public class EqTypeFinTransactionManagerImpl
    implements EqTypeFinTransactionManager
{

    public EqTypeFinTransactionManagerImpl()
    {
        m_moduleName = "eqtype";
    }

    public ProcessingResult addTransaction(FinTransaction finTran)
    {
        if(finTran instanceof EqTypeFinTransaction)
            return addTransaction((EqTypeFinTransaction)finTran);
        else
            throw new IllegalArgumentException("addTransaction called with invalid FinTransaction object when expecting an object of type EqTypeFinTransaction.");
    }

    public ProcessingResult addTransaction(EqTypeFinTransaction fin_transaction)
    {
        PersistenceManager.getInstance().newEqTypeFinTransaction(fin_transaction);
        GeneralizedFinTransaction generalized_fin_transaction = new GeneralizedFinTransaction(fin_transaction.getId(), fin_transaction.getSubAccount(), fin_transaction.getDeliveryDate(), fin_transaction.getFinTransactionType(), fin_transaction.getNetAmount(), composeDescription(fin_transaction), m_moduleName, fin_transaction.getTransactionTimestamp());
        FinApp finApp = (FinApp)Services.getService(com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp.class);
        ProcessingResult ps = finApp.getGeneralizedFinTransactionManager().addTransaction(generalized_fin_transaction);
        if(ps.isSuccessfulResult())
            return ProcessingResult.newSuccessResultInstance();
        else
            throw new RuntimeSystemException("Method addTransaction failed:" + ps.getErrorInfo());
    }

    public String composeDescription(EqTypeFinTransaction fin_transaction)
    {
        return fin_transaction.getFinTransactionType().stringValue();
    }

    public ProcessingResult undoTransaction(long fin_transaction_id)
        throws NotFoundException
    {
        Map map;
        map = new HashMap();
        map.put("delete_flag", BooleanEnum.TRUE);
        String msg;
        ErrorInfo errInfo;
        int deleted = Processors.getDefaultProcessor().doUpdateQuery(new EqtypeFinTransactionPK(fin_transaction_id), map);
        if(deleted != 0)
            break MISSING_BLOCK_LABEL_138;
        msg = "No EqTypeFinTransaction record is deleted for id=" + fin_transaction_id;
        m_log.warn(msg);
        errInfo = new ErrorInfo();
        return ProcessingResult.newFailedResultInstance(errInfo.addText(msg));
        DataException e;
        e;
        throw new RuntimeSystemException("Method undoTransaction failed for id=" + fin_transaction_id + " cause:" + e.getMessage());
        FinApp finApp = (FinApp)Services.getService(com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp.class);
        ProcessingResult ps = finApp.getGeneralizedFinTransactionManager().undoTransaction(fin_transaction_id);
        return ps;
    }

    public FinTransaction getTransaction(long transactionId)
        throws NotFoundException
    {
        Row row;
        QueryProcessor qp = Processors.getDefaultProcessor();
        com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.GtlQueryUtils.Where query = GtlQueryUtils.addNotMarkedDeleted0(null);
        row = qp.doFindByPrimaryKeyQuery(new EqtypeFinTransactionPK(transactionId), query.getWhere(), query.getBindVarArray());
        return toFinTransaction(row);
        DataFindException de;
        de;
        throw new NotFoundException("EqTypeFinTransaction " + transactionId + " not found.");
        de;
        String msg = "Exception while querying eqtype_fin_transaction rows given an transaction id:" + transactionId;
        m_log.error(msg, de);
        throw new RuntimeSystemException(msg, de);
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
        String where;
        Object bvs[];
        if(DBG)
            m_log.debug("::: Starting getTransactions on SubAccount");
        java.util.Date from = CalendarUtils.clearTimeFields(dateRange.getFromDate());
        java.util.Date to = CalendarUtils.clearTimeFields(CalendarUtils.getNextCalendarDay(dateRange.getToDate()));
        TradingModule thisTm = GtlUtils.getTradingModule(m_moduleName);
        StringBuffer whereSb = new StringBuffer();
        ProductTypeEnum productTypes[] = thisTm.getProductTypes();
        for(int i = 0; i < productTypes.length; i++)
        {
            ProductTypeEnum type = productTypes[i];
            whereSb.append(type.intValue());
            if(i < productTypes.length - 1)
                whereSb.append(",");
        }

        whereSb.append(")");
        where = "account_id = ? and sub_account_id = ? and fin_transaction_timestamp  >= ? and  fin_transaction_timestamp < ?  and (delete_flag is null or delete_flag = ?) and product_type in ( " + whereSb.toString();
        bvs = (new Object[] {
            new Long(subAccount.getAccountId()), new Long(subAccount.getSubAccountId()), from, to, BooleanEnum.FALSE
        });
        Map retMap;
        QueryProcessor qp = Processors.getDefaultProcessor();
        List rows = qp.doFindAllQuery(EqtypeFinTransactionRow.TYPE, where, bvs);
        int size = rows.size();
        retMap = new HashMap(size);
        for(int i = 0; i < size; i++)
        {
            EqtypeFinTransactionRow r = (EqtypeFinTransactionRow)rows.get(i);
            retMap.put(new Long(r.getFinTransactionId()), toFinTransaction(r));
        }

        if(DBG)
            m_log.debug("::: Returning from getTransactions on SubAccount");
        return retMap;
        DataException de;
        de;
        String msg = "Exception while querying eqtype_fin_transaction rows given an array of transaction ids.";
        m_log.error("Exception while querying eqtype_fin_transaction rows given an array of transaction ids.", de);
        throw new RuntimeSystemException("Exception while querying eqtype_fin_transaction rows given an array of transaction ids.", de);
    }

    protected FinTransaction toFinTransaction(Row r)
    {
        EqtypeFinTransactionRow row = (EqtypeFinTransactionRow)r;
        switch(row.getFinTransactionType().intValue())
        {
        case 70: // 'F'
        case 80: // 'P'
        case 201: 
        case 202: 
            return new EqTypeCashBasedOrderTransactionImpl(row);

        case 90: // 'Z'
        case 100: // 'd'
            return new EqTypeContractOpenTransactionImpl(row);

        case 110: // 'n'
        case 120: // 'x'
            return new EqTypeContractSettleTransactionImpl(row);

        case 130: 
        case 140: 
            return new EqTypeContractSwapTransactionImpl(row);

        case 1003: 
        case 1004: 
            return new AssetTransferFinTransactionImpl(row);

        case 10: // '\n'
        case 20: // '\024'
            return new CashTransferFinTransactionImpl(row);
        }
        return new EqTypeFinTransactionImpl(row);
    }

    private static final Logit m_log;
    private static final boolean DBG;
    protected String m_moduleName;

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeFinTransactionManagerImpl.class);
        DBG = m_log.ison();
    }
}
