// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeOrderManagerImpl.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls;

import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.xtrade.kernel.license.LicenseException;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.*;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls:
//            EqTypeOrderSubmitter, Utils, EqTypeServerConfigConstants, EquityProductTypeOrderManagerReusableValidations, 
//            EqTypeOrderManagerInterceptor, EquityProductTypeOrderSubmitterPersistenceManager

public class EqTypeOrderManagerImpl extends EqTypeOrderSubmitter
    implements EqTypeOrderManager
{

    protected EqTypeOrderManagerImpl()
    {
        m_PM = PersistenceManager.getInstance();
    }

    public Order getOrder(long orderId)
        throws NotFoundException
    {
        Order order = m_PM.findEqTypeOrder(orderId);
        EqtypeOrderRow row = (EqtypeOrderRow)order.getDataSourceObject();
        TradingModule tm = GtlUtils.getTradingModule(row.getProductType());
        if(isEqtypeTradingModule(tm))
            return order;
        else
            return tm.getOrderManager().toOrder(row);
    }

    public OrderUnit[] getOrderUnits(long orderId)
    {
        OrderUnit orderUnits[];
        List rows = GtlQueryUtils.executeQuery(EqtypeOrderUnitRow.TYPE, "order_id=?", null, null, new Object[] {
            new Long(orderId)
        }, -1, -1);
        OrderManager om = Utils.getGlobalOrderManager();
        int size = rows.size();
        orderUnits = new OrderUnit[size];
        for(int i = 0; i < size; i++)
        {
            EqtypeOrderUnitRow r = (EqtypeOrderUnitRow)rows.get(i);
            orderUnits[i] = om.toOrderUnit(r);
        }

        return orderUnits;
        DataException de;
        de;
        String msg = "Error while getting orderUnits for orderId :" + orderId;
        m_log.error(msg, de);
        throw new RuntimeSystemException(msg, de);
    }

    public OrderUnit getOrderUnit(long orderUnitId)
        throws NotFoundException
    {
        OrderUnit ou = m_PM.findEqTypeOrderUnit(orderUnitId);
        EqtypeOrderUnitRow row = (EqtypeOrderUnitRow)ou.getDataSourceObject();
        TradingModule tm = GtlUtils.getTradingModule(row.getProductType());
        if(isEqtypeTradingModule(tm))
            return ou;
        else
            return tm.getOrderManager().toOrderUnit(row);
    }

    public OrderAction getOrderAction(long orderActionId)
        throws NotFoundException
    {
        OrderAction oa = m_PM.findEqTypeOrderAction(orderActionId);
        EqtypeOrderActionRow row = (EqtypeOrderActionRow)oa.getDataSourceObject();
        TradingModule tm = GtlUtils.getTradingModule(row.getProductType());
        if(isEqtypeTradingModule(tm))
            return oa;
        else
            return tm.getOrderManager().toOrderAction(row);
    }

    public OrderExecution getOrderExecution(long orderExecutionId)
        throws NotFoundException
    {
        OrderExecution oe = m_PM.findEqTypeOrderExecution(orderExecutionId);
        EqtypeOrderExecutionRow row = (EqtypeOrderExecutionRow)oe.getDataSourceObject();
        TradingModule tm = GtlUtils.getTradingModule(row.getProductType());
        if(isEqtypeTradingModule(tm))
            return oe;
        else
            return tm.getOrderManager().toOrderExecution(row);
    }

    public Order toOrder(Row row)
    {
        if(!(row instanceof EqtypeOrderRow))
            throw new IllegalArgumentException("Row is not of EqtypeOrderRow type");
        EqtypeOrderRow ourow = (EqtypeOrderRow)row;
        TradingModule tm = GtlUtils.getTradingModule(ourow.getProductType());
        if(isEqtypeTradingModule(tm))
            return m_PM.toOrder(ourow);
        else
            return tm.getOrderManager().toOrder(row);
    }

    public OrderAction toOrderAction(Row row)
    {
        if(!(row instanceof EqtypeOrderActionRow))
            throw new IllegalArgumentException("Row is not of EqtypeOrderActionRow type");
        EqtypeOrderActionRow ourow = (EqtypeOrderActionRow)row;
        TradingModule tm = GtlUtils.getTradingModule(ourow.getProductType());
        if(isEqtypeTradingModule(tm))
            return m_PM.toOrderAction(ourow);
        else
            return tm.getOrderManager().toOrderAction(row);
    }

    public OrderExecution toOrderExecution(Row row)
    {
        if(!(row instanceof EqtypeOrderExecutionRow))
            throw new IllegalArgumentException("Row is not of EqtypeOrderExecutionRow type");
        EqtypeOrderExecutionRow ourow = (EqtypeOrderExecutionRow)row;
        TradingModule tm = GtlUtils.getTradingModule(ourow.getProductType());
        if(isEqtypeTradingModule(tm))
            return m_PM.toOrderExecution(ourow);
        else
            return tm.getOrderManager().toOrderExecution(row);
    }

    public OrderUnit toOrderUnit(Row row)
    {
        if(!(row instanceof EqtypeOrderUnitRow))
            throw new IllegalArgumentException("Row is not of EqtypeOrderUnitRow type");
        EqtypeOrderUnitRow ourow = (EqtypeOrderUnitRow)row;
        TradingModule tm = GtlUtils.getTradingModule(ourow.getProductType());
        if(isEqtypeTradingModule(tm))
            return m_PM.toOrderUnit(ourow);
        else
            return tm.getOrderManager().toOrderUnit(row);
    }

    public ProcessingResult expireOrder(final long orderId)
    {
        EqTypeOrderManager eqtypeom;
        AccountManager acctMgr = GtlUtils.getAccountManager();
        eqtypeom = (EqTypeOrderManager)GtlUtils.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        final Order o;
        QueryProcessor qp;
        o = eqtypeom.getOrder(orderId);
        qp = Processors.getDefaultProcessor();
        return (ProcessingResult)qp.doTransaction(1, new TransactionCallback() {

            public Object process()
                throws DataNetworkException, DataQueryException, DataCallbackException
            {
                SubAccount subAccount = o.getSubAccount();
                try
                {
                    subAccount.serializeOperationsWithNoWait();
                }
                catch(ResourceBusyException rbe)
                {
                    return ProcessingResult.newFailedResultInstance(OrderManagerErrorCatalog.XB_EXPIRE_SUB_ACCOUNT_BUSY);
                }
                OrderUnit ous[] = o.getOrderUnits();
                if(OrderOpenStatusEnum.OPEN.equals(ous[0].getOrderOpenStatus()))
                    EquityProductTypeOrderSubmitterPersistenceManager.getInstance().handleOrderExpiredUpdates(orderId);
                else
                    return ProcessingResult.newFailedResultInstance(OrderManagerErrorCatalog.XB_EXPIRE_ORDER_NOT_OPEN);
                return ProcessingResult.SUCCESS_RESULT;
            }

        }
);
        DataException de;
        de;
        m_log.error(de.getMessage(), de);
        throw new RuntimeSystemException(de.getMessage(), de);
        NotFoundException nfe;
        nfe;
        return ProcessingResult.newFailedResultInstance(OrderManagerErrorCatalog.INVALID_ORDER);
    }

    public ProcessingResult expireOrders(Institution inst)
    {
        String where = "order_open_status = ? and account_id in (select account_id from main_account where institution_id = ?)";
        Object bvs[] = {
            OrderOpenStatusEnum.OPEN, new Long(inst.getInstitutionId())
        };
        String orderBy = "account_id, sub_account_id";
        int pageSize = EqTypeServerConfigConstants.getPerRoundMaxOrderInExpireProcess();
        m_log.info("*** Expiring orders using the per round MAX orders to process size : " + pageSize);
        AccountManager acctMgr = GtlUtils.getAccountManager();
        SimpleDateFormat yyyymmddFormatter = GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat();
        int processedRowCount = 0;
        try
        {
            QueryProcessor noCacheAccountQp = Processors.getAccountProcessor(0L);
            boolean expiredAnyOrderUnitsAtAll = false;
            do
            {
                expiredAnyOrderUnitsAtAll = false;
                int pageNo = 0;
                do
                {
                    List rows = noCacheAccountQp.doFindAllQuery(EqtypeOrderUnitRow.TYPE, "order_open_status = ? and account_id in (select account_id from main_account where institution_id = ?)", "account_id, sub_account_id", null, bvs, pageSize, pageNo);
                    if(rows.size() == 0)
                        break;
                    int thisIterationProcessedRowCount = expireOrders(rows, acctMgr, yyyymmddFormatter);
                    if(thisIterationProcessedRowCount > 0)
                    {
                        processedRowCount += thisIterationProcessedRowCount;
                        m_log.info("expiredOrders count so far : " + processedRowCount);
                        expiredAnyOrderUnitsAtAll = true;
                    }
                    pageNo++;
                } while(true);
            } while(expiredAnyOrderUnitsAtAll);
        }
        catch(DataException de)
        {
            String msg = "Exception While expiring orders";
            m_log.error("Exception While expiring orders", de);
            throw new RuntimeSystemException("Exception While expiring orders", de);
        }
        m_log.info("*********** Total expired orderUnitRows count :  " + processedRowCount + ", for Institution Code:" + inst.getInstitutionCode());
        return ProcessingResult.SUCCESS_RESULT;
    }

    public ProcessingResult expireOrders()
    {
        String where = "order_open_status = ?";
        Object bvs[] = {
            OrderOpenStatusEnum.OPEN
        };
        String orderBy = "account_id, sub_account_id";
        int pageSize = EqTypeServerConfigConstants.getPerRoundMaxOrderInExpireProcess();
        m_log.info("*** Expiring orders using the per round MAX orders to process size : " + pageSize);
        AccountManager acctMgr = GtlUtils.getAccountManager();
        SimpleDateFormat yyyymmddFormatter = GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat();
        int processedRowCount = 0;
        try
        {
            QueryProcessor qp = Processors.getDefaultProcessor();
            boolean expiredAnyOrderUnitsAtAll = false;
            do
            {
                expiredAnyOrderUnitsAtAll = false;
                int pageNo = 0;
                do
                {
                    List rows = qp.doFindAllQuery(EqtypeOrderUnitRow.TYPE, "order_open_status = ?", "account_id, sub_account_id", null, bvs, pageSize, pageNo);
                    if(rows.size() == 0)
                        break;
                    int thisIterationProcessedRowCount = expireOrders(rows, acctMgr, yyyymmddFormatter);
                    if(thisIterationProcessedRowCount > 0)
                    {
                        processedRowCount += thisIterationProcessedRowCount;
                        m_log.info("expiredOrders count so far : " + processedRowCount);
                        expiredAnyOrderUnitsAtAll = true;
                    }
                    pageNo++;
                } while(true);
            } while(expiredAnyOrderUnitsAtAll);
        }
        catch(DataException de)
        {
            String msg = "Exception While expiring orders";
            m_log.error("Exception While expiring orders", de);
            throw new RuntimeSystemException("Exception While expiring orders", de);
        }
        m_log.info("*********** Total expired orderUnitRows count :  " + processedRowCount);
        return ProcessingResult.SUCCESS_RESULT;
    }

    private static int expireOrders(final List orderUnitRows, final AccountManager acctMgr, final SimpleDateFormat yyyymmddFormatter)
        throws DataException
    {
        final QueryProcessor qp = Processors.getDefaultProcessor();
        String where = "order_open_status = ?";
        final Object bvs[] = {
            OrderOpenStatusEnum.OPEN
        };
        Integer processedRowCountObj = (Integer)qp.doTransaction(1, new TransactionCallback() {

            public Object process()
                throws DataNetworkException, DataQueryException, DataCallbackException
            {
                int processedRowCount = 0;
                int size = orderUnitRows.size();
                for(int i = 0; i < size; i++)
                {
                    EqtypeOrderUnitRow orderUnitRow = (EqtypeOrderUnitRow)orderUnitRows.get(i);
                    String expireDate = yyyymmddFormatter.format(orderUnitRow.getExpirationDate());
                    String currentBizDate = null;
                    try
                    {
                        TradedProduct tproduct = Utils.getProductManager().getTradedProduct(orderUnitRow.getProductId(), orderUnitRow.getMarketId());
                        currentBizDate = yyyymmddFormatter.format(GtlUtils.getFinObjectManager().getTradingCalendar(tproduct.getTradedProductId()).getCurrentBizDate());
                    }
                    catch(NotFoundException nfe)
                    {
                        EqTypeOrderManagerImpl.m_log.warn("Error while processing orderUnitRow with orderUnitId:" + orderUnitRow.getOrderUnitId() + ", probably no Market found. Ignoring !!!!", nfe);
                        continue;
                    }
                    if(expireDate.compareTo(currentBizDate) >= 0)
                        continue;
                    try
                    {
                        SubAccount subAccount = acctMgr.getSubAccount(orderUnitRow.getAccountId(), orderUnitRow.getSubAccountId());
                        subAccount.serializeOperationsWithNoWait();
                        boolean processThisRow = false;
                        try
                        {
                            if(qp.doFindByPrimaryKeyQuery(orderUnitRow.getPrimaryKey(), "order_open_status = ?", bvs) != null)
                                processThisRow = true;
                        }
                        catch(DataFindException ignore) { }
                        if(!processThisRow)
                            continue;
                        EquityProductTypeOrderSubmitterPersistenceManager.getInstance().handleOrderExpiredUpdates(orderUnitRow.getOrderId());
                        if(EqTypeOrderManagerImpl.DBG)
                            EqTypeOrderManagerImpl.m_log.debug("Expired order with orderUnitId :" + orderUnitRow.getOrderUnitId());
                        processedRowCount++;
                    }
                    catch(NotFoundException nfe)
                    {
                        EqTypeOrderManagerImpl.m_log.error("Error while processing orderUnitRow with orderUnitId:" + orderUnitRow.getOrderUnitId() + ", no SubAccount found. Ignoring");
                    }
                    catch(ResourceBusyException ignore)
                    {
                        EqTypeOrderManagerImpl.m_log.warn("A serialization lock on SubAccount could not be acquired during the expireOrders() process.account_id:" + orderUnitRow.getAccountId() + ", sub_account_id:" + orderUnitRow.getSubAccountId());
                    }
                }

                return new Integer(processedRowCount);
            }

        }
);
        return processedRowCountObj.intValue();
    }

    public ListPage getOrders(SubAccount subAccount, ProductTypeEnum productType, DateRangeQueryParamsSpec dateRangeQuerySpec, PaginationQueryParamsSpec paginationQuerySpec, SortKeySpec sortKey)
    {
        return getOrders(subAccount, productType, dateRangeQuerySpec, paginationQuerySpec, sortKey, null, null);
    }

    public ListPage getOpenOrders(SubAccount subAccount, ProductTypeEnum productType, DateRangeQueryParamsSpec dateRangeQuerySpec, PaginationQueryParamsSpec paginationQuerySpec, SortKeySpec sortKey)
    {
        ListPage orderUnits = getOpenOrderUnits(subAccount, productType, dateRangeQuerySpec, paginationQuerySpec, sortKey);
        return getOrdersFromOrderUnits(orderUnits);
    }

    public ListPage getOrderUnits(SubAccount subAccount, ProductTypeEnum productType, DateRangeQueryParamsSpec dateRangeQuerySpec, PaginationQueryParamsSpec paginationQuerySpec, SortKeySpec sortKey)
    {
        return getOrderUnits(subAccount, productType, dateRangeQuerySpec, paginationQuerySpec, sortKey, null, null);
    }

    public ListPage getOpenOrderUnits(SubAccount subAccount, ProductTypeEnum productType, DateRangeQueryParamsSpec dateRangeQuerySpec, PaginationQueryParamsSpec paginationQuerySpec, SortKeySpec sortKey)
    {
        ListPage orderUnits = getOrderUnits(subAccount, productType, dateRangeQuerySpec, paginationQuerySpec, sortKey, "order_open_status=?", new Object[] {
            OrderOpenStatusEnum.OPEN
        });
        return orderUnits;
    }

    public ListPage getOrderUnits(SubAccount subAccount, FilterQueryParamsSpec filterQueryParamsSpec, PaginationQueryParamsSpec paginationQuerySpec, SortKeySpec sortKey)
    {
        ListPage orderUnits = getOrderUnits(subAccount, null, null, paginationQuerySpec, sortKey, filterQueryParamsSpec == null ? null : filterQueryParamsSpec.getWhereCondition(), filterQueryParamsSpec == null ? null : filterQueryParamsSpec.getBindVars());
        return orderUnits;
    }

    public ListPage getOrderActions(SubAccount subAccount, ProductTypeEnum productType, DateRangeQueryParamsSpec dateRangeQuerySpec, PaginationQueryParamsSpec paginationQuerySpec, SortKeySpec sortKey)
    {
        return getOrderActions(subAccount, productType, dateRangeQuerySpec, paginationQuerySpec, sortKey, null, null);
    }

    public ListPage getOrderActions(SubAccount subAccount, FilterQueryParamsSpec filterQueryParamsSpec, PaginationQueryParamsSpec paginationQuerySpec, SortKeySpec sortKey)
    {
        return getOrderActions(subAccount, null, null, paginationQuerySpec, sortKey, filterQueryParamsSpec == null ? null : filterQueryParamsSpec.getWhereCondition(), filterQueryParamsSpec == null ? null : filterQueryParamsSpec.getBindVars());
    }

    public ListPage getOrderExecutions(SubAccount subAccount, ProductTypeEnum productType, DateRangeQueryParamsSpec dateRangeQuerySpec, PaginationQueryParamsSpec paginationQuerySpec, SortKeySpec sortKey)
    {
        return getOrderExecutions(subAccount, productType, dateRangeQuerySpec, paginationQuerySpec, sortKey, null, null);
    }

    public ListPage getOrderExecutions(SubAccount subAccount, FilterQueryParamsSpec filterQueryParamsSpec, PaginationQueryParamsSpec paginationQuerySpec, SortKeySpec sortKey)
    {
        return getOrderExecutions(subAccount, null, null, paginationQuerySpec, sortKey, filterQueryParamsSpec == null ? null : filterQueryParamsSpec.getWhereCondition(), filterQueryParamsSpec == null ? null : filterQueryParamsSpec.getBindVars());
    }

    private ListPage getOrders(SubAccount subAccount, ProductTypeEnum productType, DateRangeQueryParamsSpec dateRangeQuerySpec, PaginationQueryParamsSpec paginationQuerySpec, SortKeySpec sortKey, String where, Object bindVars[])
    {
        com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.GtlQueryUtils.Where query;
        int pageSize;
        int pageNumber;
        String orderBy;
        query = null;
        query = GtlQueryUtils.addSubAccountQueryInfo0(query, subAccount);
        if(productType != null)
            query = GtlQueryUtils.addProductTypeInfo0(query, productType);
        query = GtlQueryUtils.addDateRangeQueryInfo0(query, dateRangeQuerySpec, "created_timestamp");
        query.append(where, bindVars);
        pageSize = -1;
        pageNumber = -1;
        if(paginationQuerySpec != null && !paginationQuerySpec.equals(PaginationQueryParamsSpec.ALL_PAGES))
        {
            pageSize = paginationQuerySpec.getPageSize();
            pageNumber = paginationQuerySpec.getPageNumber();
        }
        orderBy = sortKey != null && !sortKey.isSortKeyNull() ? sortKey.getSortKeySpec() : null;
        ListPage rows;
        List retList;
        rows = GtlQueryUtils.executeQuery(EqtypeOrderRow.TYPE, query.getWhere(), orderBy, null, query.getBindVarArray(), pageSize, pageNumber);
        int size = rows.size();
        retList = new ArrayList(size);
        for(int i = 0; i < size; i++)
        {
            EqtypeOrderRow row = (EqtypeOrderRow)rows.get(i);
            Order o = GtlUtils.getTradingModule(row.getProductType()).getOrderManager().toOrder(row);
            retList.add(o);
        }

        return new ArrayListPage(retList, rows.pageSize(), rows.pageNumber(), rows.totalSize());
        DataException de;
        de;
        String msg = "Error while fetching EQTYPE_ORDER table.";
        m_log.error("Error while fetching EQTYPE_ORDER table.", de);
        throw new RuntimeSystemException("Error while fetching EQTYPE_ORDER table.", de);
    }

    private static ListPage getOrdersFromOrderUnits(ListPage orderUnits)
    {
        int size = orderUnits.size();
        List objs = new ArrayList(size);
        for(int i = 0; i < size; i++)
        {
            OrderUnit orderUnit = (OrderUnit)orderUnits.get(i);
            objs.add(orderUnit.getOrder());
        }

        return new ArrayListPage(objs, orderUnits.pageSize(), orderUnits.pageNumber(), orderUnits.totalSize());
    }

    private ListPage getOrderUnits(SubAccount subAccount, ProductTypeEnum productType, DateRangeQueryParamsSpec dateRangeQuerySpec, PaginationQueryParamsSpec paginationQuerySpec, SortKeySpec sortKey, String where, Object bindVars[])
    {
        com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.GtlQueryUtils.Where query;
        int pageSize;
        int pageNumber;
        String orderBy;
        query = null;
        query = GtlQueryUtils.addSubAccountQueryInfo0(query, subAccount);
        if(productType != null)
            query = GtlQueryUtils.addProductTypeInfo0(query, productType);
        query = GtlQueryUtils.addDateRangeQueryInfo0(query, dateRangeQuerySpec, "created_timestamp");
        query.append(where, bindVars);
        pageSize = -1;
        pageNumber = -1;
        if(paginationQuerySpec != null && !paginationQuerySpec.equals(PaginationQueryParamsSpec.ALL_PAGES))
        {
            pageSize = paginationQuerySpec.getPageSize();
            pageNumber = paginationQuerySpec.getPageNumber();
        }
        orderBy = sortKey != null && !sortKey.isSortKeyNull() ? sortKey.getSortKeySpec() : null;
        ListPage rows;
        List retList;
        rows = GtlQueryUtils.executeQuery(EqtypeOrderUnitRow.TYPE, query.getWhere(), orderBy, null, query.getBindVarArray(), pageSize, pageNumber);
        int size = rows.size();
        retList = new ArrayList(size);
        for(int i = 0; i < size; i++)
        {
            EqtypeOrderUnitRow row = (EqtypeOrderUnitRow)rows.get(i);
            OrderUnit ou = GtlUtils.getTradingModule(row.getProductType()).getOrderManager().toOrderUnit(row);
            retList.add(ou);
        }

        return new ArrayListPage(retList, rows.pageSize(), rows.pageNumber(), rows.totalSize());
        DataException de;
        de;
        String msg = "Error while fetching EQTYPE_ORDER_UNIT table.";
        m_log.error("Error while fetching EQTYPE_ORDER_UNIT table.", de);
        throw new RuntimeSystemException("Error while fetching EQTYPE_ORDER_UNIT table.", de);
    }

    private ListPage getOrderActions(SubAccount subAccount, ProductTypeEnum productType, DateRangeQueryParamsSpec dateRangeQuerySpec, PaginationQueryParamsSpec paginationQuerySpec, SortKeySpec sortKey, String where, Object bindVars[])
    {
        com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.GtlQueryUtils.Where query;
        int pageSize;
        int pageNumber;
        String orderBy;
        query = null;
        query = GtlQueryUtils.addSubAccountQueryInfo0(query, subAccount);
        if(productType != null)
            query = GtlQueryUtils.addProductTypeInfo0(query, productType);
        query = GtlQueryUtils.addDateRangeQueryInfo0(query, dateRangeQuerySpec, "created_timestamp");
        query.append(where, bindVars);
        pageSize = -1;
        pageNumber = -1;
        if(paginationQuerySpec != null && !paginationQuerySpec.equals(PaginationQueryParamsSpec.ALL_PAGES))
        {
            pageSize = paginationQuerySpec.getPageSize();
            pageNumber = paginationQuerySpec.getPageNumber();
        }
        orderBy = sortKey != null && !sortKey.isSortKeyNull() ? sortKey.getSortKeySpec() : null;
        ListPage rows;
        List retList;
        rows = GtlQueryUtils.executeQuery(EqtypeOrderActionRow.TYPE, query.getWhere(), orderBy, null, query.getBindVarArray(), pageSize, pageNumber);
        int size = rows.size();
        retList = new ArrayList(size);
        for(int i = 0; i < size; i++)
        {
            EqtypeOrderActionRow row = (EqtypeOrderActionRow)rows.get(i);
            OrderAction oa = GtlUtils.getTradingModule(row.getProductType()).getOrderManager().toOrderAction(row);
            retList.add(oa);
        }

        return new ArrayListPage(retList, rows.pageSize(), rows.pageNumber(), rows.totalSize());
        DataException de;
        de;
        String msg = "Error while fetching EQTYPE_ORDER_ACTION table.";
        m_log.error("Error while fetching EQTYPE_ORDER_ACTION table.", de);
        throw new RuntimeSystemException("Error while fetching EQTYPE_ORDER_ACTION table.", de);
    }

    private ListPage getOrderExecutions(SubAccount subAccount, ProductTypeEnum productType, DateRangeQueryParamsSpec dateRangeQuerySpec, PaginationQueryParamsSpec paginationQuerySpec, SortKeySpec sortKey, String where, Object bindVars[])
    {
        com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.GtlQueryUtils.Where query;
        int pageSize;
        int pageNumber;
        String orderBy;
        query = null;
        query = GtlQueryUtils.addSubAccountQueryInfo0(query, subAccount);
        if(productType != null)
            query = GtlQueryUtils.addProductTypeInfo0(query, productType);
        query = GtlQueryUtils.addDateRangeQueryInfo0(query, dateRangeQuerySpec, "created_timestamp");
        query = GtlQueryUtils.addNotMarkedDeleted0(query);
        query.append(where, bindVars);
        pageSize = -1;
        pageNumber = -1;
        if(paginationQuerySpec != null && !paginationQuerySpec.equals(PaginationQueryParamsSpec.ALL_PAGES))
        {
            pageSize = paginationQuerySpec.getPageSize();
            pageNumber = paginationQuerySpec.getPageNumber();
        }
        orderBy = sortKey != null && !sortKey.isSortKeyNull() ? sortKey.getSortKeySpec() : null;
        ListPage rows;
        List retList;
        rows = GtlQueryUtils.executeQuery(EqtypeOrderExecutionRow.TYPE, query.getWhere().toString(), orderBy, null, query.getBindVarArray(), pageSize, pageNumber);
        int size = rows.size();
        retList = new ArrayList(size);
        for(int i = 0; i < size; i++)
        {
            EqtypeOrderExecutionRow row = (EqtypeOrderExecutionRow)rows.get(i);
            OrderExecution oe = GtlUtils.getTradingModule(row.getProductType()).getOrderManager().toOrderExecution(row);
            retList.add(oe);
        }

        return new ArrayListPage(retList, rows.pageSize(), rows.pageNumber(), rows.totalSize());
        DataException de;
        de;
        String msg = "Error while fetching EQTYPE_ORDER_EXECUTION table.";
        m_log.error("Error while fetching EQTYPE_ORDER_EXECUTION table.", de);
        throw new RuntimeSystemException("Error while fetching EQTYPE_ORDER_EXECUTION table.", de);
    }

    public void setThreadLocalPersistenceEventInterceptor(EqTypeOrderManagerPersistenceEventInterceptor interceptor)
    {
        m_persistenceInterceptors.set(interceptor);
    }

    static EqTypeOrderManagerPersistenceEventInterceptor getThreadLocalPersistenceEventInterceptor()
    {
        return (EqTypeOrderManagerPersistenceEventInterceptor)m_persistenceInterceptors.get();
    }

    public OrderValidationResult validateCancelOrder(SubAccount subAccount, CancelOrderSpec spec)
    {
        TradingModule tm;
        long orderId = spec.getOrderId();
        ProductTypeEnum productType = getProductTypeEnum(orderId);
        if(productType == null)
            return new OrderValidationResult(ProcessingResult.newFailedResultInstance(OrderManagerErrorCatalog.INVALID_ORDER));
        tm = GtlUtils.getTradingModule(productType);
        if(isEqtypeTradingModule(tm))
            if(spec instanceof EqTypeCancelOrderSpec)
            {
                return Utils.getGlobalOrderManager().validateCancelOrder(subAccount, (EqTypeCancelOrderSpec)spec);
            } else
            {
                EqTypeCancelOrderSpec eqtypeSpec = new EqTypeCancelOrderSpec(orderId);
                return Utils.getGlobalOrderManager().validateCancelOrder(subAccount, eqtypeSpec);
            }
        if(!OrderSubmitContextIndicator.isValidateCalledFromSubmit())
            OrderManagerThreadLocalOrderInputDataContextSetter.setCancelOrderSubAccountAndSpecParams(subAccount, spec);
        OrderValidationResult ordervalidationresult;
        EquityProductTypeOrderManagerReusableValidations.getInstance().commonFirstValidationsForAllOperations(subAccount);
        ordervalidationresult = tm.getOrderManager().validateCancelOrder(subAccount, spec);
        if(!OrderSubmitContextIndicator.isValidateCalledFromSubmit())
            OrderManagerThreadLocalOrderInputDataContextSetter.clearContext();
        return ordervalidationresult;
        OrderValidationException ve;
        ve;
        NewOrderValidationResult newordervalidationresult = new NewOrderValidationResult(ve.getValidationResult().getProcessingResult());
        if(!OrderSubmitContextIndicator.isValidateCalledFromSubmit())
            OrderManagerThreadLocalOrderInputDataContextSetter.clearContext();
        return newordervalidationresult;
        Exception exception;
        exception;
        if(!OrderSubmitContextIndicator.isValidateCalledFromSubmit())
            OrderManagerThreadLocalOrderInputDataContextSetter.clearContext();
        throw exception;
    }

    public OrderSubmissionResult submitCancelOrder(SubAccount subAccount, CancelOrderSpec spec, String tradingPassword, boolean skipOrderValidation)
    {
        TradingModule tm;
        long orderId = spec.getOrderId();
        ProductTypeEnum productType = getProductTypeEnum(orderId);
        tm = GtlUtils.getTradingModule(productType);
        if(isEqtypeTradingModule(tm))
        {
            EqTypeCancelOrderSpec eqspec = (spec instanceof EqTypeCancelOrderSpec) ? (EqTypeCancelOrderSpec)spec : new EqTypeCancelOrderSpec(orderId);
            return Utils.getGlobalOrderManager().submitCancelOrder(subAccount, eqspec, tradingPassword, skipOrderValidation);
        }
        OrderManagerThreadLocalOrderInputDataContextSetter.setCancelOrderSubAccountAndSpecParams(subAccount, spec);
        if(skipOrderValidation)
            break MISSING_BLOCK_LABEL_113;
        EquityProductTypeOrderManagerReusableValidations.getInstance().commonFirstValidationsForAllOperations(subAccount);
        break MISSING_BLOCK_LABEL_113;
        OrderValidationException ve;
        ve;
        OrderSubmissionResult ordersubmissionresult1 = new OrderSubmissionResult(ve.getValidationResult().getProcessingResult());
        return ordersubmissionresult1;
        try
        {
            checkTradingPassword(null, subAccount, tradingPassword);
            break MISSING_BLOCK_LABEL_148;
        }
        catch(OrderValidationException ex)
        {
            ordersubmissionresult1 = new OrderSubmissionResult(ex.getValidationResult().getProcessingResult());
        }
        return ordersubmissionresult1;
        OrderSubmissionResult ordersubmissionresult = tm.getOrderManager().submitCancelOrder(subAccount, spec, tradingPassword, skipOrderValidation);
        return ordersubmissionresult;
        local;
        Utils.getGlobalOrderManager().setThreadLocalPersistenceEventInterceptor(null);
        tm.getMarketAdapter().setThreadLocalMarketRequestSenderServiceProxy(null);
        OrderManagerThreadLocalOrderInputDataContextSetter.clearContext();
        JVM INSTR ret 12;
    }

    public OrderValidationResult validateChangeOrder(SubAccount subAccount, ChangeOrderSpec spec)
    {
        TradingModule tm;
        long orderId = spec.getOrderId();
        ProductTypeEnum productType = getProductTypeEnum(orderId);
        if(productType == null)
            return new OrderValidationResult(ProcessingResult.newFailedResultInstance(OrderManagerErrorCatalog.INVALID_ORDER));
        tm = GtlUtils.getTradingModule(productType);
        if(isEqtypeTradingModule(tm))
        {
            if(spec instanceof EqTypeChangeOrderSpec)
                return Utils.getGlobalOrderManager().validateChangeOrder(subAccount, (EqTypeChangeOrderSpec)spec);
            if(spec instanceof EqTypeChangeSettleContractOrderSpec)
                return Utils.getGlobalOrderManager().validateChangeSettleContractOrder(subAccount, (EqTypeChangeSettleContractOrderSpec)spec);
            if(spec instanceof EqTypeChangeSwapContractOrderSpec)
                return Utils.getGlobalOrderManager().validateChangeSwapContractOrder(subAccount, (EqTypeChangeSwapContractOrderSpec)spec);
            else
                throw new IllegalArgumentException("Invalid ChangeOrderSpec. eqtype trading module doesnt know how to handle validateChangeOrder with " + spec.getClass());
        }
        OrderValidationResult ordervalidationresult;
        if(!OrderSubmitContextIndicator.isValidateCalledFromSubmit())
            OrderManagerThreadLocalOrderInputDataContextSetter.setChangeOrderSubAccountAndSpecParams(subAccount, spec);
        EquityProductTypeOrderManagerReusableValidations.getInstance().commonFirstValidationsForAllOperations(subAccount);
        ordervalidationresult = tm.getOrderManager().validateChangeOrder(subAccount, spec);
        if(!OrderSubmitContextIndicator.isValidateCalledFromSubmit())
            OrderManagerThreadLocalOrderInputDataContextSetter.clearContext();
        return ordervalidationresult;
        OrderValidationException ve;
        ve;
        NewOrderValidationResult newordervalidationresult = new NewOrderValidationResult(ve.getValidationResult().getProcessingResult());
        if(!OrderSubmitContextIndicator.isValidateCalledFromSubmit())
            OrderManagerThreadLocalOrderInputDataContextSetter.clearContext();
        return newordervalidationresult;
        Exception exception;
        exception;
        if(!OrderSubmitContextIndicator.isValidateCalledFromSubmit())
            OrderManagerThreadLocalOrderInputDataContextSetter.clearContext();
        throw exception;
    }

    public OrderSubmissionResult submitChangeOrder(SubAccount subAccount, ChangeOrderSpec spec, String tradingPassword, boolean skipOrderValidation)
    {
        TradingModule tm;
        long orderId = spec.getOrderId();
        ProductTypeEnum productType = getProductTypeEnum(orderId);
        tm = GtlUtils.getTradingModule(productType);
        if(isEqtypeTradingModule(tm))
        {
            if(spec instanceof EqTypeChangeOrderSpec)
                return Utils.getGlobalOrderManager().submitChangeOrder(subAccount, (EqTypeChangeOrderSpec)spec, tradingPassword, skipOrderValidation);
            if(spec instanceof EqTypeChangeSettleContractOrderSpec)
                return Utils.getGlobalOrderManager().submitChangeSettleContractOrder(subAccount, (EqTypeChangeSettleContractOrderSpec)spec, tradingPassword, skipOrderValidation);
            if(spec instanceof EqTypeChangeSwapContractOrderSpec)
                return Utils.getGlobalOrderManager().submitChangeSwapContractOrder(subAccount, (EqTypeChangeSwapContractOrderSpec)spec, tradingPassword, skipOrderValidation);
            else
                throw new IllegalArgumentException("Invalid ChangeOrderSpec. eqtype trading module doesnt know how to handle validateChangeOrder with " + spec.getClass());
        }
        OrderManagerThreadLocalOrderInputDataContextSetter.setChangeOrderSubAccountAndSpecParams(subAccount, spec);
        if(skipOrderValidation)
            break MISSING_BLOCK_LABEL_175;
        EquityProductTypeOrderManagerReusableValidations.getInstance().commonFirstValidationsForAllOperations(subAccount);
        break MISSING_BLOCK_LABEL_175;
        OrderValidationException ve;
        ve;
        OrderSubmissionResult ordersubmissionresult1 = new OrderSubmissionResult(ve.getValidationResult().getProcessingResult());
        return ordersubmissionresult1;
        try
        {
            checkTradingPassword(null, subAccount, tradingPassword);
            break MISSING_BLOCK_LABEL_210;
        }
        catch(OrderValidationException ex)
        {
            ordersubmissionresult1 = new OrderSubmissionResult(ex.getValidationResult().getProcessingResult());
        }
        return ordersubmissionresult1;
        OrderSubmissionResult ordersubmissionresult = tm.getOrderManager().submitChangeOrder(subAccount, spec, tradingPassword, skipOrderValidation);
        return ordersubmissionresult;
        local;
        Utils.getGlobalOrderManager().setThreadLocalPersistenceEventInterceptor(null);
        tm.getMarketAdapter().setThreadLocalMarketRequestSenderServiceProxy(null);
        OrderManagerThreadLocalOrderInputDataContextSetter.clearContext();
        JVM INSTR ret 12;
    }

    public NewOrderValidationResult validateNewOrder(SubAccount subAccount, ProductTypeEnum productType, NewOrderSpec spec)
    {
        TradingModule tm;
        tm = GtlUtils.getTradingModule(productType);
        if(isEqtypeTradingModule(tm))
        {
            if(spec instanceof EqTypeNewCashBasedOrderSpec)
                return Utils.getGlobalOrderManager().validateNewCashBasedOrder(subAccount, (EqTypeNewCashBasedOrderSpec)spec);
            if(spec instanceof EqTypeOpenContractOrderSpec)
                return Utils.getGlobalOrderManager().validateOpenContractOrder(subAccount, (EqTypeOpenContractOrderSpec)spec);
            if(spec instanceof EqTypeSettleContractOrderSpec)
                return Utils.getGlobalOrderManager().validateSettleContractOrder(subAccount, (EqTypeSettleContractOrderSpec)spec);
            if(spec instanceof EqTypeSwapContractOrderSpec)
                return Utils.getGlobalOrderManager().validateSwapContractOrder(subAccount, (EqTypeSwapContractOrderSpec)spec);
            if(spec instanceof EqTypeNewMiniStockOrderSpec)
                return Utils.getGlobalOrderManager().validateNewMiniStockOrder(subAccount, (EqTypeNewMiniStockOrderSpec)spec);
            else
                throw new IllegalArgumentException("Invalid NewOrderSpec. eqtype trading module doesnt know how to handle validateNewOrder with " + spec.getClass());
        }
        NewOrderValidationResult newordervalidationresult;
        if(!OrderSubmitContextIndicator.isValidateCalledFromSubmit())
            OrderManagerThreadLocalOrderInputDataContextSetter.setNewOrderSubAccountAndSpecParams(subAccount, spec);
        EquityProductTypeOrderManagerReusableValidations.getInstance().commonFirstValidationsForAllOperations(subAccount);
        newordervalidationresult = tm.getOrderManager().validateNewOrder(subAccount, productType, spec);
        if(!OrderSubmitContextIndicator.isValidateCalledFromSubmit())
            OrderManagerThreadLocalOrderInputDataContextSetter.clearContext();
        return newordervalidationresult;
        OrderValidationException ve;
        ve;
        NewOrderValidationResult newordervalidationresult1 = new NewOrderValidationResult(ve.getValidationResult().getProcessingResult());
        if(!OrderSubmitContextIndicator.isValidateCalledFromSubmit())
            OrderManagerThreadLocalOrderInputDataContextSetter.clearContext();
        return newordervalidationresult1;
        Exception exception;
        exception;
        if(!OrderSubmitContextIndicator.isValidateCalledFromSubmit())
            OrderManagerThreadLocalOrderInputDataContextSetter.clearContext();
        throw exception;
    }

    public OrderSubmissionResult submitNewOrder(SubAccount subAccount, ProductTypeEnum productType, NewOrderSpec spec, long orderId, String tradingPassword, boolean skipOrderValidation)
    {
        TradingModule tm;
        tm = GtlUtils.getTradingModule(productType);
        if("eqtype".equals(tm.getTradingModuleName()))
        {
            if(spec instanceof EqTypeNewCashBasedOrderSpec)
                return Utils.getGlobalOrderManager().submitNewCashBasedOrder(subAccount, (EqTypeNewCashBasedOrderSpec)spec, orderId, tradingPassword, skipOrderValidation);
            if(spec instanceof EqTypeOpenContractOrderSpec)
                return Utils.getGlobalOrderManager().submitOpenContractOrder(subAccount, (EqTypeOpenContractOrderSpec)spec, orderId, tradingPassword, skipOrderValidation);
            if(spec instanceof EqTypeSettleContractOrderSpec)
                return Utils.getGlobalOrderManager().submitSettleContractOrder(subAccount, (EqTypeSettleContractOrderSpec)spec, orderId, tradingPassword, skipOrderValidation);
            if(spec instanceof EqTypeSwapContractOrderSpec)
                return Utils.getGlobalOrderManager().submitSwapContractOrder(subAccount, (EqTypeSwapContractOrderSpec)spec, orderId, tradingPassword, skipOrderValidation);
            if(spec instanceof EqTypeNewMiniStockOrderSpec)
                return Utils.getGlobalOrderManager().submitNewMiniStockOrder(subAccount, (EqTypeNewMiniStockOrderSpec)spec, orderId, tradingPassword, skipOrderValidation);
            else
                throw new IllegalArgumentException("Invalid NewOrderSpec. eqtype trading module doesnt know how to handle submitNewOrder with " + spec.getClass());
        }
        OrderManagerThreadLocalOrderInputDataContextSetter.setNewOrderSubAccountAndSpecParams(subAccount, spec);
        if(skipOrderValidation)
            break MISSING_BLOCK_LABEL_231;
        EquityProductTypeOrderManagerReusableValidations.getInstance().commonFirstValidationsForAllOperations(subAccount);
        break MISSING_BLOCK_LABEL_231;
        OrderValidationException ve;
        ve;
        OrderSubmissionResult ordersubmissionresult1 = new OrderSubmissionResult(ve.getValidationResult().getProcessingResult());
        return ordersubmissionresult1;
        try
        {
            checkTradingPassword(spec.getTrader(), subAccount, tradingPassword);
            break MISSING_BLOCK_LABEL_270;
        }
        catch(OrderValidationException ex)
        {
            ordersubmissionresult1 = new OrderSubmissionResult(ex.getValidationResult().getProcessingResult());
        }
        return ordersubmissionresult1;
        try
        {
            EquityProductTypeOrderManagerReusableValidations.getInstance().validateOrderIdForDuplicate(orderId);
            break MISSING_BLOCK_LABEL_306;
        }
        // Misplaced declaration of an exception variable
        catch(OrderValidationException ex)
        {
            ordersubmissionresult1 = new OrderSubmissionResult(ex.getValidationResult().getProcessingResult());
        }
        return ordersubmissionresult1;
        OrderSubmissionResult ordersubmissionresult = tm.getOrderManager().submitNewOrder(subAccount, productType, spec, orderId, tradingPassword, skipOrderValidation);
        return ordersubmissionresult;
        local;
        Utils.getGlobalOrderManager().setThreadLocalPersistenceEventInterceptor(null);
        tm.getMarketAdapter().setThreadLocalMarketRequestSenderServiceProxy(null);
        OrderManagerThreadLocalOrderInputDataContextSetter.clearContext();
        JVM INSTR ret 12;
    }

    private static ProductTypeEnum getProductTypeEnum(long orderId)
    {
        EqtypeOrderRow row = EqtypeOrderDao.findRowByOrderId(orderId);
        if(row != null)
            return row.getProductType();
        return null;
        DataException de;
        de;
        String msg = "Error while fetching EQTYPE_ORDER with orderId :" + orderId;
        m_log.error(msg, de);
        throw new RuntimeSystemException(msg, de);
    }

    public EqTypeNewOrderValidationResult validateNewCashBasedOrder(SubAccount subAccount, EqTypeNewCashBasedOrderSpec spec)
    {
        EqTypeNewOrderValidationResult eqtypenewordervalidationresult;
        if(!OrderSubmitContextIndicator.isValidateCalledFromSubmit())
            OrderManagerThreadLocalOrderInputDataContextSetter.setNewOrderSubAccountAndSpecParams(subAccount, spec);
        eqtypenewordervalidationresult = super.validateNewCashBasedOrder(subAccount, spec);
        if(!OrderSubmitContextIndicator.isValidateCalledFromSubmit())
            OrderManagerThreadLocalOrderInputDataContextSetter.clearContext();
        return eqtypenewordervalidationresult;
        Exception exception;
        exception;
        if(!OrderSubmitContextIndicator.isValidateCalledFromSubmit())
            OrderManagerThreadLocalOrderInputDataContextSetter.clearContext();
        throw exception;
    }

    public EqTypeNewOrderValidationResult validateNewMiniStockOrder(SubAccount subAccount, EqTypeNewMiniStockOrderSpec spec)
    {
        EqTypeNewOrderValidationResult eqtypenewordervalidationresult;
        checkMiniStockTradingLicense();
        if(!OrderSubmitContextIndicator.isValidateCalledFromSubmit())
            OrderManagerThreadLocalOrderInputDataContextSetter.setNewOrderSubAccountAndSpecParams(subAccount, spec);
        eqtypenewordervalidationresult = super.validateNewMiniStockOrder(subAccount, spec);
        if(!OrderSubmitContextIndicator.isValidateCalledFromSubmit())
            OrderManagerThreadLocalOrderInputDataContextSetter.clearContext();
        return eqtypenewordervalidationresult;
        Exception exception;
        exception;
        if(!OrderSubmitContextIndicator.isValidateCalledFromSubmit())
            OrderManagerThreadLocalOrderInputDataContextSetter.clearContext();
        throw exception;
    }

    public EqTypeNewOrderValidationResult validateOpenContractOrder(SubAccount subAccount, EqTypeOpenContractOrderSpec spec)
    {
        EqTypeNewOrderValidationResult eqtypenewordervalidationresult;
        checkMarginTradingLicense();
        if(!OrderSubmitContextIndicator.isValidateCalledFromSubmit())
            OrderManagerThreadLocalOrderInputDataContextSetter.setNewOrderSubAccountAndSpecParams(subAccount, spec);
        eqtypenewordervalidationresult = super.validateOpenContractOrder(subAccount, spec);
        if(!OrderSubmitContextIndicator.isValidateCalledFromSubmit())
            OrderManagerThreadLocalOrderInputDataContextSetter.clearContext();
        return eqtypenewordervalidationresult;
        Exception exception;
        exception;
        if(!OrderSubmitContextIndicator.isValidateCalledFromSubmit())
            OrderManagerThreadLocalOrderInputDataContextSetter.clearContext();
        throw exception;
    }

    public EqTypeNewOrderValidationResult validateSettleContractOrder(SubAccount subAccount, EqTypeSettleContractOrderSpec spec)
    {
        EqTypeNewOrderValidationResult eqtypenewordervalidationresult;
        checkMarginTradingLicense();
        if(!OrderSubmitContextIndicator.isValidateCalledFromSubmit())
            OrderManagerThreadLocalOrderInputDataContextSetter.setNewOrderSubAccountAndSpecParams(subAccount, spec);
        eqtypenewordervalidationresult = super.validateSettleContractOrder(subAccount, spec);
        if(!OrderSubmitContextIndicator.isValidateCalledFromSubmit())
            OrderManagerThreadLocalOrderInputDataContextSetter.clearContext();
        return eqtypenewordervalidationresult;
        Exception exception;
        exception;
        if(!OrderSubmitContextIndicator.isValidateCalledFromSubmit())
            OrderManagerThreadLocalOrderInputDataContextSetter.clearContext();
        throw exception;
    }

    public EqTypeNewOrderValidationResult validateSwapContractOrder(SubAccount subAccount, EqTypeSwapContractOrderSpec spec)
    {
        EqTypeNewOrderValidationResult eqtypenewordervalidationresult;
        if(!OrderSubmitContextIndicator.isValidateCalledFromSubmit())
            OrderManagerThreadLocalOrderInputDataContextSetter.setNewOrderSubAccountAndSpecParams(subAccount, spec);
        eqtypenewordervalidationresult = super.validateSwapContractOrder(subAccount, spec);
        if(!OrderSubmitContextIndicator.isValidateCalledFromSubmit())
            OrderManagerThreadLocalOrderInputDataContextSetter.clearContext();
        return eqtypenewordervalidationresult;
        Exception exception;
        exception;
        if(!OrderSubmitContextIndicator.isValidateCalledFromSubmit())
            OrderManagerThreadLocalOrderInputDataContextSetter.clearContext();
        throw exception;
    }

    public EqTypeOrderValidationResult validateChangeOrder(SubAccount subAccount, EqTypeChangeOrderSpec spec)
    {
        EqTypeOrderValidationResult eqtypeordervalidationresult;
        if(!OrderSubmitContextIndicator.isValidateCalledFromSubmit())
            OrderManagerThreadLocalOrderInputDataContextSetter.setChangeOrderSubAccountAndSpecParams(subAccount, spec);
        eqtypeordervalidationresult = super.validateChangeOrder(subAccount, spec);
        if(!OrderSubmitContextIndicator.isValidateCalledFromSubmit())
            OrderManagerThreadLocalOrderInputDataContextSetter.clearContext();
        return eqtypeordervalidationresult;
        Exception exception;
        exception;
        if(!OrderSubmitContextIndicator.isValidateCalledFromSubmit())
            OrderManagerThreadLocalOrderInputDataContextSetter.clearContext();
        throw exception;
    }

    public EqTypeOrderValidationResult validateChangeSettleContractOrder(SubAccount subAccount, EqTypeChangeSettleContractOrderSpec spec)
    {
        EqTypeOrderValidationResult eqtypeordervalidationresult;
        if(!OrderSubmitContextIndicator.isValidateCalledFromSubmit())
            OrderManagerThreadLocalOrderInputDataContextSetter.setChangeOrderSubAccountAndSpecParams(subAccount, spec);
        eqtypeordervalidationresult = super.validateChangeSettleContractOrder(subAccount, spec);
        if(!OrderSubmitContextIndicator.isValidateCalledFromSubmit())
            OrderManagerThreadLocalOrderInputDataContextSetter.clearContext();
        return eqtypeordervalidationresult;
        Exception exception;
        exception;
        if(!OrderSubmitContextIndicator.isValidateCalledFromSubmit())
            OrderManagerThreadLocalOrderInputDataContextSetter.clearContext();
        throw exception;
    }

    public EqTypeOrderValidationResult validateChangeSwapContractOrder(SubAccount subAccount, EqTypeChangeSwapContractOrderSpec spec)
    {
        EqTypeOrderValidationResult eqtypeordervalidationresult;
        if(!OrderSubmitContextIndicator.isValidateCalledFromSubmit())
            OrderManagerThreadLocalOrderInputDataContextSetter.setChangeOrderSubAccountAndSpecParams(subAccount, spec);
        eqtypeordervalidationresult = super.validateChangeSwapContractOrder(subAccount, spec);
        if(!OrderSubmitContextIndicator.isValidateCalledFromSubmit())
            OrderManagerThreadLocalOrderInputDataContextSetter.clearContext();
        return eqtypeordervalidationresult;
        Exception exception;
        exception;
        if(!OrderSubmitContextIndicator.isValidateCalledFromSubmit())
            OrderManagerThreadLocalOrderInputDataContextSetter.clearContext();
        throw exception;
    }

    public EqTypeOrderValidationResult validateCancelOrder(SubAccount subAccount, EqTypeCancelOrderSpec spec)
    {
        EqTypeOrderValidationResult eqtypeordervalidationresult;
        if(!OrderSubmitContextIndicator.isValidateCalledFromSubmit())
            OrderManagerThreadLocalOrderInputDataContextSetter.setCancelOrderSubAccountAndSpecParams(subAccount, spec);
        eqtypeordervalidationresult = super.validateCancelOrder(subAccount, spec);
        if(!OrderSubmitContextIndicator.isValidateCalledFromSubmit())
            OrderManagerThreadLocalOrderInputDataContextSetter.clearContext();
        return eqtypeordervalidationresult;
        Exception exception;
        exception;
        if(!OrderSubmitContextIndicator.isValidateCalledFromSubmit())
            OrderManagerThreadLocalOrderInputDataContextSetter.clearContext();
        throw exception;
    }

    public EqTypeOrderSubmissionResult submitNewCashBasedOrder(SubAccount subAccount, EqTypeNewCashBasedOrderSpec spec, long orderId, String tradingPassword, boolean skipOrderValidation)
    {
        EqTypeOrderSubmissionResult eqtypeordersubmissionresult;
        try
        {
            OrderManagerThreadLocalOrderInputDataContextSetter.setNewOrderSubAccountAndSpecParams(subAccount, spec);
            eqtypeordersubmissionresult = super.submitNewCashBasedOrder(subAccount, spec, orderId, tradingPassword, skipOrderValidation);
        }
        finally
        {
            Utils.getGlobalOrderManager().setThreadLocalPersistenceEventInterceptor(null);
            GtlUtils.getTradingModule("eqtype").getMarketAdapter().setThreadLocalMarketRequestSenderServiceProxy(null);
            OrderManagerThreadLocalOrderInputDataContextSetter.clearContext();
        }
        return eqtypeordersubmissionresult;
    }

    public EqTypeOrderSubmissionResult submitNewMiniStockOrder(SubAccount subAccount, EqTypeNewMiniStockOrderSpec spec, long orderId, String tradingPassword, boolean skipOrderValidation)
    {
        EqTypeOrderSubmissionResult eqtypeordersubmissionresult;
        try
        {
            checkMiniStockTradingLicense();
            OrderManagerThreadLocalOrderInputDataContextSetter.setNewOrderSubAccountAndSpecParams(subAccount, spec);
            eqtypeordersubmissionresult = super.submitNewMiniStockOrder(subAccount, spec, orderId, tradingPassword, skipOrderValidation);
        }
        finally
        {
            Utils.getGlobalOrderManager().setThreadLocalPersistenceEventInterceptor(null);
            GtlUtils.getTradingModule("eqtype").getMarketAdapter().setThreadLocalMarketRequestSenderServiceProxy(null);
            OrderManagerThreadLocalOrderInputDataContextSetter.clearContext();
        }
        return eqtypeordersubmissionresult;
    }

    public EqTypeOrderSubmissionResult submitOpenContractOrder(SubAccount subAccount, EqTypeOpenContractOrderSpec spec, long orderId, String tradingPassword, boolean skipOrderValidation)
    {
        EqTypeOrderSubmissionResult eqtypeordersubmissionresult;
        try
        {
            checkMarginTradingLicense();
            OrderManagerThreadLocalOrderInputDataContextSetter.setNewOrderSubAccountAndSpecParams(subAccount, spec);
            eqtypeordersubmissionresult = super.submitOpenContractOrder(subAccount, spec, orderId, tradingPassword, skipOrderValidation);
        }
        finally
        {
            Utils.getGlobalOrderManager().setThreadLocalPersistenceEventInterceptor(null);
            GtlUtils.getTradingModule("eqtype").getMarketAdapter().setThreadLocalMarketRequestSenderServiceProxy(null);
            OrderManagerThreadLocalOrderInputDataContextSetter.clearContext();
        }
        return eqtypeordersubmissionresult;
    }

    public EqTypeOrderSubmissionResult submitSettleContractOrder(SubAccount subAccount, EqTypeSettleContractOrderSpec spec, long orderId, String tradingPassword, boolean skipOrderValidation)
    {
        EqTypeOrderSubmissionResult eqtypeordersubmissionresult;
        try
        {
            checkMarginTradingLicense();
            OrderManagerThreadLocalOrderInputDataContextSetter.setNewOrderSubAccountAndSpecParams(subAccount, spec);
            eqtypeordersubmissionresult = super.submitSettleContractOrder(subAccount, spec, orderId, tradingPassword, skipOrderValidation);
        }
        finally
        {
            Utils.getGlobalOrderManager().setThreadLocalPersistenceEventInterceptor(null);
            GtlUtils.getTradingModule("eqtype").getMarketAdapter().setThreadLocalMarketRequestSenderServiceProxy(null);
            OrderManagerThreadLocalOrderInputDataContextSetter.clearContext();
        }
        return eqtypeordersubmissionresult;
    }

    public EqTypeOrderSubmissionResult submitSwapContractOrder(SubAccount subAccount, EqTypeSwapContractOrderSpec spec, long orderId, String tradingPassword, boolean skipOrderValidation)
    {
        EqTypeOrderSubmissionResult eqtypeordersubmissionresult;
        try
        {
            OrderManagerThreadLocalOrderInputDataContextSetter.setNewOrderSubAccountAndSpecParams(subAccount, spec);
            eqtypeordersubmissionresult = super.submitSwapContractOrder(subAccount, spec, orderId, tradingPassword, skipOrderValidation);
        }
        finally
        {
            Utils.getGlobalOrderManager().setThreadLocalPersistenceEventInterceptor(null);
            GtlUtils.getTradingModule("eqtype").getMarketAdapter().setThreadLocalMarketRequestSenderServiceProxy(null);
            OrderManagerThreadLocalOrderInputDataContextSetter.clearContext();
        }
        return eqtypeordersubmissionresult;
    }

    public EqTypeOrderSubmissionResult submitChangeOrder(SubAccount subAccount, EqTypeChangeOrderSpec spec, String tradingPassword, boolean skipOrderValidation)
    {
        EqTypeOrderSubmissionResult eqtypeordersubmissionresult;
        try
        {
            OrderManagerThreadLocalOrderInputDataContextSetter.setChangeOrderSubAccountAndSpecParams(subAccount, spec);
            eqtypeordersubmissionresult = super.submitChangeOrder(subAccount, spec, tradingPassword, skipOrderValidation);
        }
        finally
        {
            Utils.getGlobalOrderManager().setThreadLocalPersistenceEventInterceptor(null);
            GtlUtils.getTradingModule("eqtype").getMarketAdapter().setThreadLocalMarketRequestSenderServiceProxy(null);
            OrderManagerThreadLocalOrderInputDataContextSetter.clearContext();
        }
        return eqtypeordersubmissionresult;
    }

    public EqTypeOrderSubmissionResult submitChangeSettleContractOrder(SubAccount subAccount, EqTypeChangeSettleContractOrderSpec spec, String tradingPassword, boolean skipOrderValidation)
    {
        EqTypeOrderSubmissionResult eqtypeordersubmissionresult;
        try
        {
            OrderManagerThreadLocalOrderInputDataContextSetter.setChangeOrderSubAccountAndSpecParams(subAccount, spec);
            eqtypeordersubmissionresult = super.submitChangeSettleContractOrder(subAccount, spec, tradingPassword, skipOrderValidation);
        }
        finally
        {
            Utils.getGlobalOrderManager().setThreadLocalPersistenceEventInterceptor(null);
            GtlUtils.getTradingModule("eqtype").getMarketAdapter().setThreadLocalMarketRequestSenderServiceProxy(null);
            OrderManagerThreadLocalOrderInputDataContextSetter.clearContext();
        }
        return eqtypeordersubmissionresult;
    }

    public EqTypeOrderSubmissionResult submitChangeSwapContractOrder(SubAccount subAccount, EqTypeChangeSwapContractOrderSpec spec, String tradingPassword, boolean skipOrderValidation)
    {
        EqTypeOrderSubmissionResult eqtypeordersubmissionresult;
        try
        {
            OrderManagerThreadLocalOrderInputDataContextSetter.setChangeOrderSubAccountAndSpecParams(subAccount, spec);
            eqtypeordersubmissionresult = super.submitChangeSwapContractOrder(subAccount, spec, tradingPassword, skipOrderValidation);
        }
        finally
        {
            Utils.getGlobalOrderManager().setThreadLocalPersistenceEventInterceptor(null);
            GtlUtils.getTradingModule("eqtype").getMarketAdapter().setThreadLocalMarketRequestSenderServiceProxy(null);
            OrderManagerThreadLocalOrderInputDataContextSetter.clearContext();
        }
        return eqtypeordersubmissionresult;
    }

    public EqTypeOrderSubmissionResult submitCancelOrder(SubAccount subAccount, EqTypeCancelOrderSpec spec, String tradingPassword, boolean skipOrderValidation)
    {
        EqTypeOrderSubmissionResult eqtypeordersubmissionresult;
        try
        {
            OrderManagerThreadLocalOrderInputDataContextSetter.setCancelOrderSubAccountAndSpecParams(subAccount, spec);
            eqtypeordersubmissionresult = super.submitCancelOrder(subAccount, spec, tradingPassword, skipOrderValidation);
        }
        finally
        {
            Utils.getGlobalOrderManager().setThreadLocalPersistenceEventInterceptor(null);
            GtlUtils.getTradingModule("eqtype").getMarketAdapter().setThreadLocalMarketRequestSenderServiceProxy(null);
            OrderManagerThreadLocalOrderInputDataContextSetter.clearContext();
        }
        return eqtypeordersubmissionresult;
    }

    private static boolean isEqtypeTradingModule(TradingModule tm)
    {
        return "eqtype".equals(tm.getTradingModuleName());
    }

    private static MainAccount getMainAccount(long accountId)
    {
        return GtlUtils.getAccountManager().getMainAccount(accountId);
        NotFoundException nfe;
        nfe;
        String msg = "MainAccount not found accountId : " + accountId;
        m_log.error(msg, nfe);
        throw new RuntimeSystemException(msg, nfe);
    }

    private static void checkMarginTradingLicense()
        throws LicenseException
    {
        if(EqTypeOrderManagerInterceptor.m_isMarginAndMiniStockLicenseAvailable)
            return;
        if(EqTypeOrderManagerInterceptor.m_licenseChecker4Margin.isValidLicenseAvailable())
        {
            return;
        } else
        {
            m_log.error(EqTypeOrderManagerInterceptor.NO_VALID_EQTYPE_MARGIN_LICENSE.toString());
            throw new LicenseException(EqTypeOrderManagerInterceptor.NO_VALID_EQTYPE_MARGIN_LICENSE.toString());
        }
    }

    private static void checkMiniStockTradingLicense()
        throws LicenseException
    {
        if(EqTypeOrderManagerInterceptor.m_isMarginAndMiniStockLicenseAvailable)
            return;
        if(EqTypeOrderManagerInterceptor.m_licenseChecker4Mini.isValidLicenseAvailable())
        {
            return;
        } else
        {
            m_log.error(EqTypeOrderManagerInterceptor.NO_VALID_EQTYPE_MINI_LICENSE.toString());
            throw new LicenseException(EqTypeOrderManagerInterceptor.NO_VALID_EQTYPE_MINI_LICENSE.toString());
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
    protected PersistenceManager m_PM;
    private final int NOPAGE = -1;
    private static final ThreadLocal m_persistenceInterceptors = new ThreadLocal();

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeOrderManagerImpl.class);
        DBG = m_log.ison();
    }


}
