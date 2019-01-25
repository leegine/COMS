// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeOrderManager.java

package com.fitechlabs.xtrade.plugin.tc.eqtype;

import com.fitechlabs.dbind.ListPage;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeCancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeChangeOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeChangeSettleContractOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeChangeSwapContractOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeNewCashBasedOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeNewMiniStockOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeNewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOpenContractOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSwapContractOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype:
//            EqTypeOrderManagerPersistenceEventInterceptor

public interface EqTypeOrderManager
    extends OrderManager
{

    public abstract long createNewOrderId();

    public abstract long createNewOrderUnitId();

    public abstract ProcessingResult expireOrders();

    public abstract ProcessingResult expireOrders(Institution institution);

    public abstract ProcessingResult expireOrder(long l);

    public abstract void setThreadLocalPersistenceEventInterceptor(EqTypeOrderManagerPersistenceEventInterceptor eqtypeordermanagerpersistenceeventinterceptor);

    public abstract EqTypeNewOrderValidationResult validateNewCashBasedOrder(SubAccount subaccount, EqTypeNewCashBasedOrderSpec eqtypenewcashbasedorderspec);

    public abstract EqTypeNewOrderValidationResult validateNewMiniStockOrder(SubAccount subaccount, EqTypeNewMiniStockOrderSpec eqtypenewministockorderspec);

    public abstract EqTypeNewOrderValidationResult validateOpenContractOrder(SubAccount subaccount, EqTypeOpenContractOrderSpec eqtypeopencontractorderspec);

    public abstract EqTypeNewOrderValidationResult validateSettleContractOrder(SubAccount subaccount, EqTypeSettleContractOrderSpec eqtypesettlecontractorderspec);

    public abstract EqTypeNewOrderValidationResult validateSwapContractOrder(SubAccount subaccount, EqTypeSwapContractOrderSpec eqtypeswapcontractorderspec);

    public abstract EqTypeOrderSubmissionResult submitNewCashBasedOrder(SubAccount subaccount, EqTypeNewCashBasedOrderSpec eqtypenewcashbasedorderspec, long l, String s, boolean flag);

    public abstract EqTypeOrderSubmissionResult submitNewMiniStockOrder(SubAccount subaccount, EqTypeNewMiniStockOrderSpec eqtypenewministockorderspec, long l, String s, boolean flag);

    public abstract EqTypeOrderSubmissionResult submitOpenContractOrder(SubAccount subaccount, EqTypeOpenContractOrderSpec eqtypeopencontractorderspec, long l, String s, boolean flag);

    public abstract EqTypeOrderSubmissionResult submitSettleContractOrder(SubAccount subaccount, EqTypeSettleContractOrderSpec eqtypesettlecontractorderspec, long l, String s, boolean flag);

    public abstract EqTypeOrderSubmissionResult submitSwapContractOrder(SubAccount subaccount, EqTypeSwapContractOrderSpec eqtypeswapcontractorderspec, long l, String s, boolean flag);

    public abstract EqTypeOrderValidationResult validateChangeOrder(SubAccount subaccount, EqTypeChangeOrderSpec eqtypechangeorderspec);

    public abstract EqTypeOrderValidationResult validateChangeSettleContractOrder(SubAccount subaccount, EqTypeChangeSettleContractOrderSpec eqtypechangesettlecontractorderspec);

    public abstract EqTypeOrderValidationResult validateChangeSwapContractOrder(SubAccount subaccount, EqTypeChangeSwapContractOrderSpec eqtypechangeswapcontractorderspec);

    public abstract EqTypeOrderValidationResult validateCancelOrder(SubAccount subaccount, EqTypeCancelOrderSpec eqtypecancelorderspec);

    public abstract EqTypeOrderSubmissionResult submitChangeOrder(SubAccount subaccount, EqTypeChangeOrderSpec eqtypechangeorderspec, String s, boolean flag);

    public abstract EqTypeOrderSubmissionResult submitChangeSettleContractOrder(SubAccount subaccount, EqTypeChangeSettleContractOrderSpec eqtypechangesettlecontractorderspec, String s, boolean flag);

    public abstract EqTypeOrderSubmissionResult submitChangeSwapContractOrder(SubAccount subaccount, EqTypeChangeSwapContractOrderSpec eqtypechangeswapcontractorderspec, String s, boolean flag);

    public abstract EqTypeOrderSubmissionResult submitCancelOrder(SubAccount subaccount, EqTypeCancelOrderSpec eqtypecancelorderspec, String s, boolean flag);

    public abstract ListPage getOrders(SubAccount subaccount, ProductTypeEnum producttypeenum, DateRangeQueryParamsSpec daterangequeryparamsspec, PaginationQueryParamsSpec paginationqueryparamsspec, SortKeySpec sortkeyspec);

    public abstract ListPage getOpenOrders(SubAccount subaccount, ProductTypeEnum producttypeenum, DateRangeQueryParamsSpec daterangequeryparamsspec, PaginationQueryParamsSpec paginationqueryparamsspec, SortKeySpec sortkeyspec);

    public abstract ListPage getOrderUnits(SubAccount subaccount, ProductTypeEnum producttypeenum, DateRangeQueryParamsSpec daterangequeryparamsspec, PaginationQueryParamsSpec paginationqueryparamsspec, SortKeySpec sortkeyspec);

    public abstract ListPage getOpenOrderUnits(SubAccount subaccount, ProductTypeEnum producttypeenum, DateRangeQueryParamsSpec daterangequeryparamsspec, PaginationQueryParamsSpec paginationqueryparamsspec, SortKeySpec sortkeyspec);

    public abstract ListPage getOrderUnits(SubAccount subaccount, FilterQueryParamsSpec filterqueryparamsspec, PaginationQueryParamsSpec paginationqueryparamsspec, SortKeySpec sortkeyspec);

    public abstract ListPage getOrderActions(SubAccount subaccount, ProductTypeEnum producttypeenum, DateRangeQueryParamsSpec daterangequeryparamsspec, PaginationQueryParamsSpec paginationqueryparamsspec, SortKeySpec sortkeyspec);

    public abstract ListPage getOrderActions(SubAccount subaccount, FilterQueryParamsSpec filterqueryparamsspec, PaginationQueryParamsSpec paginationqueryparamsspec, SortKeySpec sortkeyspec);

    public abstract ListPage getOrderExecutions(SubAccount subaccount, ProductTypeEnum producttypeenum, DateRangeQueryParamsSpec daterangequeryparamsspec, PaginationQueryParamsSpec paginationqueryparamsspec, SortKeySpec sortkeyspec);

    public abstract ListPage getOrderExecutions(SubAccount subaccount, FilterQueryParamsSpec filterqueryparamsspec, PaginationQueryParamsSpec paginationqueryparamsspec, SortKeySpec sortkeyspec);

    public abstract OrderUnit[] getOrderUnits(long l);
}
