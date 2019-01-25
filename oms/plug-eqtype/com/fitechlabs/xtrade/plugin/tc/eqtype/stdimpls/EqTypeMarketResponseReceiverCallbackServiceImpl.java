// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeMarketResponseReceiverCallbackServiceImpl.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.*;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitDao;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.*;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls:
//            BaseEqTypeMarketResponseReceiverCallbackServiceImpl, EquityProductTypeOrderSubmitterPersistenceManager, Utils, EqTypeOrderSubmitterPersistenceManager

public class EqTypeMarketResponseReceiverCallbackServiceImpl extends BaseEqTypeMarketResponseReceiverCallbackServiceImpl
{

    public EqTypeMarketResponseReceiverCallbackServiceImpl()
    {
    }

    protected EqTypeOrderSubmitterPersistenceManager getThisOrderSubmitterPersistenceManager()
    {
        return EquityProductTypeOrderSubmitterPersistenceManager.getInstance();
    }

    protected TradingModule getThisTradingModule()
    {
        return GtlUtils.getTradingModule("eqtype");
    }

    public ProcessingResult process(OrderFillMarketResponseMessage msg)
    {
        FillOrderUnitSpec fillUnits[] = msg.getFillOrderUnitSpecs();
        Map orderUnitRowsMap = new HashMap();
        try
        {
            for(int i = 0; i < fillUnits.length; i++)
            {
                FillOrderUnitSpec unit = fillUnits[i];
                long orderUnitId = unit.getOrderUnitId();
                double fillPrice = unit.getFillPrice();
                double fillQty = unit.getFillQuantity();
                if(DBG)
                    m_log.debug("fill qty : " + fillQty + ", fill price : " + fillPrice);
                EqtypeOrderUnitRow orderUnitRow = EqtypeOrderUnitDao.findRowByPk(orderUnitId);
                orderUnitRowsMap.put(new Long(orderUnitId), orderUnitRow);
                if(!OrderCategEnum.SWAP_MARGIN.equals(orderUnitRow.getOrderCateg()) && (fillPrice <= 0.0D || com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils.Double.isZero(fillPrice)))
                    throw new IllegalArgumentException("OrderFillMarketResponseMessage contains zero for fill price.");
                if(fillQty <= 0.0D || com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils.Double.isZero(fillQty))
                    throw new IllegalArgumentException("OrderFillMarketResponseMessage contains zero for fill quantity.");
            }

        }
        catch(DataException de)
        {
            String errorMsg = "DB exception while processing fill message from market.";
            m_log.error("DB exception while processing fill message from market.", de);
            throw new RuntimeSystemException("DB exception while processing fill message from market.", de);
        }
        long orderId = msg.getOrderId();
        Map orderUnitsExecQtyTable = new HashMap();
        for(int i = 0; i < fillUnits.length; i++)
        {
            FillOrderUnitSpec fillUnit = fillUnits[i];
            long orderUnitId = fillUnit.getOrderUnitId();
            EqtypeOrderUnitRow orderUnitRow = (EqtypeOrderUnitRow)orderUnitRowsMap.get(new Long(orderUnitId));
            OrderCategEnum orderCateg = orderUnitRow.getOrderCateg();
            if(OrderCategEnum.CLOSE_MARGIN.equals(orderCateg) || OrderCategEnum.SWAP_MARGIN.equals(orderCateg))
            {
                double fillQty = fillUnit.getFillQuantity();
                com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry entries[] = EquityProductTypeOrderSubmitterPersistenceManager.getInstance().getExecQtyAssignedClosingContractEntries(orderUnitRow, fillQty);
                orderUnitsExecQtyTable.put(new Long(orderUnitId), entries);
            }
        }

        com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution orderExecBizObjects[] = EquityProductTypeOrderSubmitterPersistenceManager.getInstance().handleOrderFillMarketUpdates(orderId, msg.getFillOrderUnitSpecs());
        EqTypePositionManager posMgr = Utils.getPositionManager();
        for(int i = 0; i < orderExecBizObjects.length; i++)
        {
            EqTypeOrderExecution orderExec = (EqTypeOrderExecution)orderExecBizObjects[i];
            if(DBG)
                m_log.debug("Processing order execution for exec id : " + orderExec.getOrderExecutionId());
            switch(orderExec.getOrderType().intValue())
            {
            case 1: // '\001'
            case 2: // '\002'
            case 101: // 'e'
            case 102: // 'f'
                posMgr.processAssetOrderExecution(orderExec);
                break;

            case 3: // '\003'
            case 4: // '\004'
                posMgr.processContractOrderExecution(orderExec);
                break;

            case 5: // '\005'
            case 6: // '\006'
            case 7: // '\007'
            case 8: // '\b'
                com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry entries[] = (com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry[])orderUnitsExecQtyTable.get(new Long(orderExec.getOrderUnitId()));
                posMgr.processContractOrderExecution(orderExec, entries);
                break;

            default:
                String m = "logic error. order type seem to contain invalid/unexpected value";
                m_log.error("logic error. order type seem to contain invalid/unexpected value");
                throw new RuntimeSystemException("logic error. order type seem to contain invalid/unexpected value");
            }
        }

        return ProcessingResult.SUCCESS_RESULT;
    }

    public ProcessingResult process(MarketResponseMessage msg)
    {
        EqTypeOrderManager eqtypeom;
        TradingModule tm;
        eqtypeom = Utils.getGlobalOrderManager();
        Order order = null;
        try
        {
            order = eqtypeom.getOrder(msg.getOrderId());
        }
        catch(NotFoundException nfe)
        {
            String errorMsg = "Invalid orderId in the MarketResponseMsg:" + msg.getClass() + ", orderId :" + msg.getOrderId();
            m_log.error(errorMsg, nfe);
            throw new RuntimeSystemException(errorMsg, nfe);
        }
        tm = GtlUtils.getTradingModule(order.getProductType());
        ProcessingResult processingresult;
        if(!isEqtypeTradingModule(tm))
            break MISSING_BLOCK_LABEL_120;
        processingresult = dispatchToEqTypeTradingModule(msg);
        eqtypeom.setThreadLocalPersistenceEventInterceptor(null);
        return processingresult;
        processingresult = tm.getMarketAdapter().getMarketResponseReceiverCallbackService().process(msg);
        eqtypeom.setThreadLocalPersistenceEventInterceptor(null);
        return processingresult;
        Exception exception;
        exception;
        eqtypeom.setThreadLocalPersistenceEventInterceptor(null);
        throw exception;
    }

    private ProcessingResult dispatchToEqTypeTradingModule(MarketResponseMessage msg)
    {
        if(msg instanceof OrderFillMarketResponseMessage)
            return process((OrderFillMarketResponseMessage)msg);
        if(msg instanceof NewOrderRejectedMarketResponseMessage)
            return super.process((NewOrderRejectedMarketResponseMessage)msg);
        if(msg instanceof NewOrderAcceptedMarketResponseMessage)
            return super.process((NewOrderAcceptedMarketResponseMessage)msg);
        if(msg instanceof ChangeOrderRejectedMarketResponseMessage)
            return super.process((ChangeOrderRejectedMarketResponseMessage)msg);
        if(msg instanceof ChangeOrderAcceptedMarketResponseMessage)
            return super.process((ChangeOrderAcceptedMarketResponseMessage)msg);
        if(msg instanceof CancelOrderAcceptedMarketResponseMessage)
            return super.process((CancelOrderAcceptedMarketResponseMessage)msg);
        if(msg instanceof CancelOrderRejectedMarketResponseMessage)
            return super.process((CancelOrderRejectedMarketResponseMessage)msg);
        if(msg instanceof NewOrderSentMarketResponseMessage)
            return super.process((NewOrderSentMarketResponseMessage)msg);
        if(msg instanceof CancelOrderSentMarketResponseMessage)
            return super.process((CancelOrderSentMarketResponseMessage)msg);
        if(msg instanceof ChangeOrderSentMarketResponseMessage)
            return super.process((ChangeOrderSentMarketResponseMessage)msg);
        if(msg instanceof OrderInvalidatedMarketResponseMessage)
            return super.process((OrderInvalidatedMarketResponseMessage)msg);
        if(msg instanceof UndoOrderInvalidatedMarketResponseMessage)
            return super.process((UndoOrderInvalidatedMarketResponseMessage)msg);
        if(msg instanceof UndoOrderFillMarketResponseMessage)
            return process((UndoOrderFillMarketResponseMessage)msg);
        else
            throw new IllegalArgumentException("Can not handle response message of type : " + msg.getClass().getName());
    }

    public ProcessingResult process(UndoOrderFillMarketResponseMessage msg)
    {
        getThisOrderSubmitterPersistenceManager().handleUndoOrderFillUpdates(msg.getOrderId(), msg.getOrderExecutionId());
        Utils.getPositionManager().undoExecution(msg.getOrderExecutionId());
        return ProcessingResult.SUCCESS_RESULT;
    }

    private static boolean isEqtypeTradingModule(TradingModule tm)
    {
        return "eqtype".equals(tm.getTradingModuleName());
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

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeMarketResponseReceiverCallbackServiceImpl.class);
        DBG = m_log.ison();
    }
}
