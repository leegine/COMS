// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypePositionManagerThreadLocalContext.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls;

import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import java.util.HashMap;
import java.util.Map;

public class EqTypePositionManagerThreadLocalContext
{

    protected static Map getContextHolderAsMap()
    {
        return (Map)m_dataContext.get();
    }

    private EqTypePositionManagerThreadLocalContext()
    {
    }

    public static boolean isUndoExecution()
    {
        return Boolean.TRUE.equals(getContextHolderAsMap().get("IS_UNDO_EXECUTION"));
    }

    static void setIsUndoExeution()
    {
        getContextHolderAsMap().put("IS_UNDO_EXECUTION", Boolean.TRUE);
    }

    public static long getUndoExecutionId()
    {
        return ((Long)getContextHolderAsMap().get("UNDO_EXECUTION_ID")).longValue();
    }

    static void setExecutionId(long executionId)
    {
        getContextHolderAsMap().put("UNDO_EXECUTION_ID", new Long(executionId));
    }

    public static boolean isProcessingAssetExecution()
    {
        return Boolean.TRUE.equals(getContextHolderAsMap().get("IS_PROCESSING_ASSET_EXECUTION"));
    }

    static void setIsProcessingAssetExecution()
    {
        getContextHolderAsMap().put("IS_PROCESSING_ASSET_EXECUTION", Boolean.TRUE);
    }

    public static boolean isProcessingContractOpenExecution()
    {
        return Boolean.TRUE.equals(getContextHolderAsMap().get("IS_PROCESSING_CONTRACT_OPEN_EXECUTION"));
    }

    static void setIsProcessingContractOpenExecution()
    {
        getContextHolderAsMap().put("IS_PROCESSING_CONTRACT_OPEN_EXECUTION", Boolean.TRUE);
    }

    public static boolean isProcessingContractSettleExecution()
    {
        return Boolean.TRUE.equals(getContextHolderAsMap().get("IS_PROCESSING_CONTRACT_SETTLE_EXECUTION"));
    }

    static void setIsProcessingContractSettleExecution()
    {
        getContextHolderAsMap().put("IS_PROCESSING_CONTRACT_SETTLE_EXECUTION", Boolean.TRUE);
    }

    public static boolean isProcessingContractSwapExecution()
    {
        return Boolean.TRUE.equals(getContextHolderAsMap().get("IS_PROCESSING_CONTRACT_SWAP_EXECUTION"));
    }

    static void setIsProcessingContractSwapExecution()
    {
        getContextHolderAsMap().put("IS_PROCESSING_CONTRACT_SWAP_EXECUTION", Boolean.TRUE);
    }

    public static EqTypeOrderExecution getOrderExecution()
    {
        return (EqTypeOrderExecution)getContextHolderAsMap().get("ORDER_EXECUTION");
    }

    static void setOrderExecution(EqTypeOrderExecution orderExecution)
    {
        getContextHolderAsMap().put("ORDER_EXECUTION", orderExecution);
    }

    public static SettleContractEntry[] getEqTypeSettleContractOrderEntries()
    {
        return (SettleContractEntry[])getContextHolderAsMap().get("CONTRACT_SETTLE_ENTRIES");
    }

    static void setEqTypeSettleContractOrderEntries(SettleContractEntry entries[])
    {
        getContextHolderAsMap().put("CONTRACT_SETTLE_ENTRIES", entries);
    }

    static void clearContext()
    {
        getContextHolderAsMap().clear();
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
    protected static final ThreadLocal m_dataContext = new ThreadLocal() {

        protected Object initialValue()
        {
            return new HashMap();
        }

    }
;
    private static final String IS_UNDO_EXECUTION = "IS_UNDO_EXECUTION";
    private static final String UNDO_EXECUTION_ID = "UNDO_EXECUTION_ID";
    private static final String IS_PROCESSING_ASSET_EXECUTION = "IS_PROCESSING_ASSET_EXECUTION";
    private static final String IS_PROCESSING_CONTRACT_OPEN_EXECUTION = "IS_PROCESSING_CONTRACT_OPEN_EXECUTION";
    private static final String IS_PROCESSING_CONTRACT_SETTLE_EXECUTION = "IS_PROCESSING_CONTRACT_SETTLE_EXECUTION";
    private static final String IS_PROCESSING_CONTRACT_SWAP_EXECUTION = "IS_PROCESSING_CONTRACT_SWAP_EXECUTION";
    private static final String ORDER_EXECUTION = "ORDER_EXECUTION";
    private static final String CONTRACT_SETTLE_ENTRIES = "CONTRACT_SETTLE_ENTRIES";

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypePositionManagerThreadLocalContext.class);
        DBG = m_log.ison();
    }
}
