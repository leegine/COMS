// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeServerConfigConstants.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.config.ServerConfig;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException;

public class EqTypeServerConfigConstants
{

    private EqTypeServerConfigConstants()
    {
    }

    public static double getMaxPerBuyOrderQuantity()
    {
        String configName = "MAX_PER_BUY_ORDER_QTY";
        String val;
        val = ServerConfig.getConfigValue("tc.eqtype", "MAX_PER_BUY_ORDER_QTY");
        if(val == null)
            break MISSING_BLOCK_LABEL_29;
        return Double.parseDouble(val);
        NumberFormatException nfe;
        nfe;
        m_log.warn("server_config contains invalid value for config_categ, config_name = tc.eqtype,MAX_PER_BUY_ORDER_QTY");
        return 1.7976931348623157E+308D;
        DataException de;
        de;
        m_log.error("Exception while getting value from server_config :" + de.getMessage(), de);
        throw new RuntimeSystemException("Exception while getting value from server_config", de);
    }

    public static double getMaxPerBuyOrderAmount()
    {
        String configName = "MAX_PER_BUY_ORDER_AMOUNT";
        String val;
        val = ServerConfig.getConfigValue("tc.eqtype", "MAX_PER_BUY_ORDER_AMOUNT");
        if(val == null)
            break MISSING_BLOCK_LABEL_29;
        return Double.parseDouble(val);
        NumberFormatException nfe;
        nfe;
        m_log.warn("server_config contains invalid value for config_categ, config_name = tc.eqtype,MAX_PER_BUY_ORDER_AMOUNT");
        return 1.7976931348623157E+308D;
        DataException de;
        de;
        m_log.error("Exception while getting value from server_config :" + de.getMessage(), de);
        throw new RuntimeSystemException("Exception while getting value from server_config", de);
    }

    public static int getPerRoundMaxOrderInExpireProcess()
    {
        String configName = "PER_ROUND_MAX_ORDERS_IN_EXPIRE_PROCESS";
        String val;
        val = ServerConfig.getConfigValue("tc.eqtype", "PER_ROUND_MAX_ORDERS_IN_EXPIRE_PROCESS");
        if(val == null)
            break MISSING_BLOCK_LABEL_29;
        return Integer.parseInt(val);
        NumberFormatException nfe;
        nfe;
        m_log.warn("server_config contains invalid value for config_categ, config_name = tc.eqtype,PER_ROUND_MAX_ORDERS_IN_EXPIRE_PROCESS");
        return 100;
        DataException de;
        de;
        m_log.error("Exception while getting value from server_config :" + de.getMessage(), de);
        throw new RuntimeSystemException("Exception while getting value from server_config", de);
    }

    public static int getDeliverDateShiftDays()
    {
        String configName = "DELIVERY_DATE_SHIFT_DAYS";
        String val;
        val = ServerConfig.getConfigValue("tc.eqtype", "DELIVERY_DATE_SHIFT_DAYS");
        if(val == null)
            break MISSING_BLOCK_LABEL_29;
        return Integer.parseInt(val);
        NumberFormatException nfe;
        nfe;
        m_log.warn("server_config contains invalid value for config_categ, config_name = tc.eqtype,DELIVERY_DATE_SHIFT_DAYS");
        return 3;
        DataException de;
        de;
        m_log.error("Exception while getting value from server_config :" + de.getMessage(), de);
        throw new RuntimeSystemException("Exception while getting value from server_config", de);
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
    public static final String CATEGORY = "tc.eqtype";
    private static final String MAX_PER_BUY_ORDER_QTY_CONFIG_NAME = "MAX_PER_BUY_ORDER_QTY";
    private static final String MAX_PER_BUY_ORDER_AMOUNT_CONFIG_NAME = "MAX_PER_BUY_ORDER_AMOUNT";
    private static final String VALIDATE_ORDERS = "VALIDATE_ORDERS";
    private static final String PER_ROUND_MAX_ORDERS_IN_EXPIRE_PROCESS = "PER_ROUND_MAX_ORDERS_IN_EXPIRE_PROCESS";
    private static final String DELIVERY_DATE_SHIFT_DAYS_NAME = "DELIVERY_DATE_SHIFT_DAYS";

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeServerConfigConstants.class);
        DBG = m_log.ison();
    }
}
