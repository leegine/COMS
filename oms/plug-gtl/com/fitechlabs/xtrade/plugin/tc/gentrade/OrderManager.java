// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OrderManager.java

package com.fitechlabs.xtrade.plugin.tc.gentrade;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.CancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.ChangeOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade:
//            NotFoundException, Order, OrderUnit, OrderAction, 
//            OrderExecution, SubAccount, ProductTypeEnum, OrderValidator

public interface OrderManager
{

    public abstract Order getOrder(long l)
        throws NotFoundException;

    public abstract Order toOrder(Row row);

    public abstract OrderUnit getOrderUnit(long l)
        throws NotFoundException;

    public abstract OrderUnit toOrderUnit(Row row);

    public abstract OrderAction getOrderAction(long l)
        throws NotFoundException;

    public abstract OrderAction toOrderAction(Row row);

    public abstract OrderExecution getOrderExecution(long l)
        throws NotFoundException;

    public abstract OrderExecution toOrderExecution(Row row);

    public abstract NewOrderValidationResult validateNewOrder(SubAccount subaccount, ProductTypeEnum producttypeenum, NewOrderSpec neworderspec);

    public abstract OrderSubmissionResult submitNewOrder(SubAccount subaccount, ProductTypeEnum producttypeenum, NewOrderSpec neworderspec, long l, String s, boolean flag);

    public abstract OrderValidationResult validateChangeOrder(SubAccount subaccount, ChangeOrderSpec changeorderspec);

    public abstract OrderSubmissionResult submitChangeOrder(SubAccount subaccount, ChangeOrderSpec changeorderspec, String s, boolean flag);

    public abstract OrderValidationResult validateCancelOrder(SubAccount subaccount, CancelOrderSpec cancelorderspec);

    public abstract OrderSubmissionResult submitCancelOrder(SubAccount subaccount, CancelOrderSpec cancelorderspec, String s, boolean flag);

    public abstract OrderValidator getOrderValidator();

    public abstract void overrideOrderValidator(OrderValidator ordervalidator);
}
