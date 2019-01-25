// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PersistenceManagerImpl.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.*;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.GtlQueryUtils;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls:
//            EqTypeOrderImpl, EqTypeCashBasedOrderUnitImpl, EqTypeContractOpenOrderUnitImpl, EqTypeContractSettleOrderUnitImpl, 
//            EqTypeContractSwapOrderUnitImpl, EqTypeOrderUnitImpl, EqTypeOrderActionImpl, EqTypeOrderExecutionImpl, 
//            EqTypeClosingContractSpecImpl, EqTypeCashBasedOrderTransactionImpl, EqTypeContractOpenTransactionImpl, EqTypeContractSettleTransactionImpl, 
//            EqTypeContractSwapTransactionImpl, EqTypeFinTransactionImpl

public final class PersistenceManagerImpl extends PersistenceManager
{

    public PersistenceManagerImpl()
    {
    }

    public EqTypeOrder findEqTypeOrder(long orderId)
        throws NotFoundException
    {
        EqTypeOrder obj = new EqTypeOrderImpl(orderId);
        return obj;
        DataFindException e;
        e;
        throw new NotFoundException("EqTypeOrder " + orderId + " not found.");
        DataException de;
        de;
        String msg = "DataException while getting order with orderId : " + orderId;
        m_log.error(msg, de);
        throw new RuntimeSystemException(msg, de);
    }

    EqTypeOrder getEqTypeOrder(EqtypeOrderRow row)
    {
        return new EqTypeOrderImpl(row);
    }

    EqTypeOrder getEqTypeOrder(EqTypeOrderUnit orderUnit)
    {
        return new EqTypeOrderImpl(orderUnit);
    }

    public List findEqTypeOrders(String where, String orderBy, String conditions, Object bindVars[], int pageSize, int pageNumber)
    {
        List objs;
        List rows = GtlQueryUtils.executeQuery(EqtypeOrderRow.TYPE, where, orderBy, conditions, bindVars, pageSize, pageNumber);
        int size = rows.size();
        objs = new ArrayList(size);
        for(int i = 0; i < size; i++)
        {
            EqtypeOrderRow row = (EqtypeOrderRow)rows.get(i);
            objs.add(getEqTypeOrder(row));
        }

        return objs;
        DataException de;
        de;
        String msg = "DataException while getting orders with where : " + where + ", orderBy : " + orderBy + ", conditions : " + conditions;
        m_log.error(msg, de);
        throw new RuntimeSystemException(msg, de);
    }

    public EqTypeOrderUnit toOrderUnit(EqtypeOrderUnitRow row)
    {
        return getTypedEqTypeOrderUnit(row);
    }

    public EqTypeOrderAction toOrderAction(EqtypeOrderActionRow row)
    {
        return getEqTypeOrderAction(row);
    }

    public EqTypeOrderExecution toOrderExecution(EqtypeOrderExecutionRow row)
    {
        return getEqTypeOrderExecution(row);
    }

    public EqTypeOrder toOrder(EqtypeOrderRow row)
    {
        return getEqTypeOrder(row);
    }

    EqTypeOrderUnit getTypedEqTypeOrderUnit(EqtypeOrderUnitRow row)
    {
        if(row == null)
            return null;
        switch(row.getOrderCateg().intValue())
        {
        case 1: // '\001'
            return new EqTypeCashBasedOrderUnitImpl(this, row);

        case 3: // '\003'
            return new EqTypeContractOpenOrderUnitImpl(this, row);

        case 5: // '\005'
            return new EqTypeContractSettleOrderUnitImpl(this, row);

        case 7: // '\007'
            return new EqTypeContractSwapOrderUnitImpl(this, row);

        case 9: // '\t'
        case 11: // '\013'
            return new EqTypeOrderUnitImpl(this, row);

        case 2: // '\002'
        case 4: // '\004'
        case 6: // '\006'
        case 8: // '\b'
        case 10: // '\n'
        default:
            return new EqTypeOrderUnitImpl(this, row);
        }
    }

    public EqTypeOrderUnit findEqTypeOrderUnit(long orderUnitId)
        throws NotFoundException
    {
        EqtypeOrderUnitRow row = EqtypeOrderUnitDao.findRowByPk(orderUnitId);
        return getTypedEqTypeOrderUnit(row);
        DataFindException e;
        e;
        throw new NotFoundException("EqTypeOrderUnit " + orderUnitId + " not found.");
        DataException de;
        de;
        String msg = "DataException while getting orderUnit with orderUnitId : " + orderUnitId;
        m_log.error(msg, de);
        throw new RuntimeSystemException(msg, de);
    }

    EqTypeOrderUnit getEqTypeOrderUnit(EqtypeOrderUnitRow row)
    {
        return getTypedEqTypeOrderUnit(row);
    }

    public List findEqTypeOrderUnits(String where, String orderBy, String conditions, Object bindVars[], int pageSize, int pageNumber)
    {
        List objs;
        List rows = GtlQueryUtils.executeQuery(EqtypeOrderUnitRow.TYPE, where, orderBy, conditions, bindVars, pageSize, pageNumber);
        int size = rows.size();
        objs = new ArrayList(size);
        for(int i = 0; i < size; i++)
        {
            EqtypeOrderUnitRow row = (EqtypeOrderUnitRow)rows.get(i);
            objs.add(getEqTypeOrderUnit(row));
        }

        return objs;
        DataException de;
        de;
        String msg = "DataException while searching order units with where: " + where + ", orderBy :" + orderBy + ", conditions:" + conditions;
        m_log.error(msg, de);
        throw new RuntimeSystemException(msg, de);
    }

    public EqTypeOrderAction findEqTypeOrderAction(long order_action_id)
        throws NotFoundException
    {
        EqTypeOrderAction obj = new EqTypeOrderActionImpl(this, order_action_id);
        return obj;
        DataFindException e;
        e;
        throw new NotFoundException("EqTypeOrderAction " + order_action_id + " not found.");
        DataException de;
        de;
        String msg = "DataException while getting EqTypeOrderAction with orderActionId : " + order_action_id;
        m_log.error(msg, de);
        throw new RuntimeSystemException(msg, de);
    }

    EqTypeOrderAction getEqTypeOrderAction(EqtypeOrderActionRow row)
    {
        return new EqTypeOrderActionImpl(this, row);
    }

    public List findEqTypeOrderActions(String where, String orderBy, String conditions, Object bindVars[], int pageSize, int pageNumber)
    {
        List objs;
        List rows = GtlQueryUtils.executeQuery(EqtypeOrderActionRow.TYPE, where, orderBy, conditions, bindVars, pageSize, pageNumber);
        int size = rows.size();
        objs = new ArrayList(size);
        for(int i = 0; i < size; i++)
        {
            EqtypeOrderActionRow row = (EqtypeOrderActionRow)rows.get(i);
            objs.add(getEqTypeOrderAction(row));
        }

        return objs;
        DataException de;
        de;
        String msg = "DataException while getting orderActions with where  : " + where + ", orderBy:" + orderBy + ",conditions:" + conditions;
        m_log.error(msg, de);
        throw new RuntimeSystemException(msg, de);
    }

    public EqTypeOrderExecution findEqTypeOrderExecution(long order_execution_id)
        throws NotFoundException
    {
        EqTypeOrderExecution obj = new EqTypeOrderExecutionImpl(this, order_execution_id);
        return obj;
        DataFindException e;
        e;
        throw new NotFoundException("EqTypeOrderExecution " + order_execution_id + " not found.");
        DataException de;
        de;
        String msg = "DataException while getting orderExecution with order_execution_id : " + order_execution_id;
        m_log.error(msg, de);
        throw new RuntimeSystemException(msg, de);
    }

    public EqTypeOrderExecution findEqTypeOrderExecution(long order_execution_id, String where, Object bindVars[])
        throws NotFoundException
    {
        EqTypeOrderExecution obj;
        EqtypeOrderExecutionRow row = (EqtypeOrderExecutionRow)findByPk(new EqtypeOrderExecutionPK(order_execution_id), where, bindVars);
        obj = new EqTypeOrderExecutionImpl(this, row);
        return obj;
        DataFindException e;
        e;
        throw new NotFoundException("EqTypeOrderExecution " + order_execution_id + " not found.");
        DataException de;
        de;
        String msg = "DataException while getting orderExecution with orderExecutionId : " + order_execution_id + ", where : " + where;
        m_log.error(msg, de);
        throw new RuntimeSystemException(msg, de);
    }

    EqTypeOrderExecution getEqTypeOrderExecution(EqtypeOrderExecutionRow row)
    {
        return new EqTypeOrderExecutionImpl(this, row);
    }

    public List findEqTypeOrderExecutions(String where, String orderBy, String conditions, Object bindVars[], int pageSize, int pageNumber)
    {
        List objs;
        List rows = GtlQueryUtils.executeQuery(EqtypeOrderExecutionRow.TYPE, where, orderBy, conditions, bindVars, pageSize, pageNumber);
        int size = rows.size();
        objs = new ArrayList(size);
        for(int i = 0; i < size; i++)
        {
            EqtypeOrderExecutionRow row = (EqtypeOrderExecutionRow)rows.get(i);
            objs.add(getEqTypeOrderExecution(row));
        }

        return objs;
        DataException de;
        de;
        String msg = "DataException while getting orderExecutions with where: " + where + ", orderBy :" + orderBy + ", conditions:" + conditions;
        m_log.error(msg, de);
        throw new RuntimeSystemException(msg, de);
    }

    public EqTypeClosingContractSpec findEqTypeClosingContractSpec(long closing_contract_spec_id)
        throws NotFoundException
    {
        EqTypeClosingContractSpec obj = new EqTypeClosingContractSpecImpl(closing_contract_spec_id);
        return obj;
        DataFindException e;
        e;
        throw new NotFoundException("EqTypeClosingContractSpec " + closing_contract_spec_id + " not found.");
        DataException de;
        de;
        String msg = "DataException while closingContractSpec with closing_contract_spec_id:" + closing_contract_spec_id;
        m_log.error(msg, de);
        throw new RuntimeSystemException(msg, de);
    }

    static List getEqTypeClosingContractSpecRows(long orderUnitId, String orderBy)
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

    static EqtypeClosingContractSpecRow getEqTypeClosingContractSpecRow(long orderUnitId, long contractId)
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

    EqTypeClosingContractSpec getEqTypeClosingContractSpec(EqtypeClosingContractSpecRow row)
    {
        return new EqTypeClosingContractSpecImpl(row);
    }

    static EqTypeFinTransaction getTypedEqTypeFinTransaction(PersistenceManagerImpl pm, EqtypeFinTransactionRow row)
    {
        if(row == null)
            return null;
        switch(row.getFinTransactionType().intValue())
        {
        case 70: // 'F'
        case 80: // 'P'
        case 201: 
        case 202: 
            return new EqTypeCashBasedOrderTransactionImpl(pm, row);

        case 90: // 'Z'
        case 100: // 'd'
            return new EqTypeContractOpenTransactionImpl(pm, row);

        case 110: // 'n'
        case 120: // 'x'
            return new EqTypeContractSettleTransactionImpl(pm, row);

        case 130: 
        case 140: 
            return new EqTypeContractSwapTransactionImpl(pm, row);
        }
        return new EqTypeFinTransactionImpl(pm, row);
    }

    public EqTypeFinTransaction findEqTypeFinTransaction(long fin_transaction_id)
        throws NotFoundException
    {
        EqtypeFinTransactionRow row = EqtypeFinTransactionDao.findRowByPk(fin_transaction_id);
        return getTypedEqTypeFinTransaction(this, row);
        DataFindException e;
        e;
        throw new NotFoundException("EqTypeFinTransaction " + fin_transaction_id + " not found.");
        DataException de;
        de;
        throw new RuntimeSystemException("Error during DB operation. Root cause: " + de.getMessage(), de);
    }

    public EqTypeFinTransaction findEqTypeFinTransaction(long fin_transaction_id, String where, Object bindVars[])
        throws NotFoundException
    {
        EqtypeFinTransactionRow row = (EqtypeFinTransactionRow)findByPk(new EqtypeFinTransactionPK(fin_transaction_id), where, bindVars);
        return getTypedEqTypeFinTransaction(this, row);
        DataFindException e;
        e;
        throw new NotFoundException("EqTypeFinTransaction " + fin_transaction_id + " not found.");
        DataException de;
        de;
        throw new RuntimeSystemException("Error during DB operation. Root cause: " + de.getMessage(), de);
    }

    EqTypeFinTransaction getEqTypeFinTransaction(EqtypeFinTransactionRow row)
    {
        return getTypedEqTypeFinTransaction(this, row);
    }

    public List findEqTypeFinTransactions(String where, String orderBy, String conditions, Object bindVars[], int pageSize, int pageNumber)
    {
        List objs;
        List rows = GtlQueryUtils.executeQuery(EqtypeFinTransactionRow.TYPE, where, orderBy, conditions, bindVars, pageSize, pageNumber);
        int size = rows.size();
        objs = new ArrayList(size);
        for(int i = 0; i < size; i++)
        {
            EqtypeFinTransactionRow row = (EqtypeFinTransactionRow)rows.get(i);
            objs.add(getEqTypeFinTransaction(row));
        }

        return objs;
        DataException de;
        de;
        String msg = "DataException eqtype_fin_transactions with where : " + where + ", orderBy:" + orderBy + ", conditions:" + conditions;
        m_log.error(msg, de);
        throw new RuntimeSystemException(msg, de);
    }

    private Row findByPk(PrimaryKey pk, String where, Object bindVars[])
        throws DataNetworkException, DataQueryException
    {
        QueryProcessor qp = Processors.getDefaultProcessor();
        if(DBG)
        {
            m_log.debug("pk=" + pk);
            m_log.debug("where=" + where);
            if(bindVars != null)
            {
                for(int i = 0; i < bindVars.length; i++)
                    m_log.debug("bindVars[" + i + "]=" + bindVars[i]);

            } else
            {
                m_log.debug("bindVars=null");
            }
        }
        return qp.doFindByPrimaryKeyQuery(pk, where, bindVars);
    }

    public long createNewOrderId()
    {
        return EqtypeOrderDao.newPkValue();
        DataException de;
        de;
        m_log.error(de.getMessage(), de);
        throw new RuntimeSystemException("Exception while getting new PK for eqtype_order table.", de);
    }

    public long createNewOrderUnitId()
    {
        return EqtypeOrderUnitDao.newPkValue();
        DataException de;
        de;
        m_log.error(de.getMessage(), de);
        throw new RuntimeSystemException("Exception while getting new PK for eqtype_order_unit table.", de);
    }

    public int newEqTypeFinTransaction(EqTypeFinTransaction fin_transaction)
    {
        EqtypeFinTransactionPK pk;
        QueryProcessor qp = Processors.getDefaultProcessor();
        pk = (EqtypeFinTransactionPK)qp.doInsertQuery(((EqTypeFinTransactionImpl)fin_transaction).m_Row);
        return pk != null ? 1 : 0;
        DataException e;
        e;
        String msg = "Exception while persisting a new EqTypeFinTransaction to DB. :  " + e.getMessage();
        m_log.error(msg, e);
        throw new RuntimeSystemException(msg, e);
    }

    public int removeEqTypeFinTransaction(long fin_transaction_id)
    {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doDeleteQuery(new EqtypeFinTransactionPK(fin_transaction_id));
        DataException e;
        e;
        String msg = "Exception while deleting an EqTypeFinTransaction from DB. :  " + e.getMessage();
        m_log.error(msg, e);
        throw new RuntimeSystemException(msg, e);
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
    static final PersistenceManagerImpl INSTANCE = new PersistenceManagerImpl();

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.PersistenceManagerImpl.class);
        DBG = m_log.ison();
    }
}
