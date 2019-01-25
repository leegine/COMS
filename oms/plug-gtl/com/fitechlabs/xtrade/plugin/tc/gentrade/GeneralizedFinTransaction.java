// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GeneralizedFinTransaction.java

package com.fitechlabs.xtrade.plugin.tc.gentrade;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import java.sql.Timestamp;
import java.util.Date;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade:
//            FinApp, NotFoundException, RuntimeSystemException, FinTransaction, 
//            SubAccount, MainAccount, AccountManager, FinTransactionType, 
//            FinTransactionCateg

public class GeneralizedFinTransaction
    implements FinTransaction
{

    public GeneralizedFinTransaction(long id, SubAccount subAccount, Date deliveryDate, FinTransactionType type, double netAmount, 
            String description, String tradingModuleName, Timestamp transTimestamp)
    {
        this(id, subAccount, deliveryDate, type, netAmount, description, tradingModuleName, transTimestamp, null);
    }

    public GeneralizedFinTransaction(long id, long accountId, long subAccountId, Date deliveryDate, 
            FinTransactionType type, double netAmount, String description, String tradingModuleName, Timestamp transTimestamp)
    {
        this(id, accountId, subAccountId, deliveryDate, type, netAmount, description, tradingModuleName, transTimestamp, null);
    }

    public GeneralizedFinTransaction(long id, SubAccount subAccount, Date deliveryDate, FinTransactionType type, double netAmount, 
            String description, String tradingModuleName, Timestamp transTimestamp, Object dataSourceObject)
    {
        m_id = id;
        m_subAccount = subAccount;
        m_deliveryDate = deliveryDate;
        m_type = type;
        m_netAmount = netAmount;
        m_txTimestamp = transTimestamp;
        m_desc = description;
        m_tradingModuleName = tradingModuleName;
        m_dataSourceObject = dataSourceObject;
        m_accountId = subAccount.getSubAccountId();
        m_subAccountId = subAccount.getMainAccount().getAccountId();
    }

    public GeneralizedFinTransaction(long id, long accountId, long subAccountId, Date deliveryDate, 
            FinTransactionType type, double netAmount, String description, String tradingModuleName, Timestamp transTimestamp, Object dataSourceObject)
    {
        m_id = id;
        m_deliveryDate = deliveryDate;
        m_type = type;
        m_netAmount = netAmount;
        m_txTimestamp = transTimestamp;
        m_desc = description;
        m_tradingModuleName = tradingModuleName;
        m_dataSourceObject = dataSourceObject;
        m_accountId = accountId;
        m_subAccountId = subAccountId;
    }

    public String getGenericDescription()
    {
        return m_desc;
    }

    public String getTradingModuleName()
    {
        return m_tradingModuleName;
    }

    public Date getDeliveryDate()
    {
        return m_deliveryDate;
    }

    public long getId()
    {
        return m_id;
    }

    public double getNetAmount()
    {
        return m_netAmount;
    }

    public SubAccount getSubAccount()
    {
        if(m_subAccount == null)
        {
            FinApp finApp = (FinApp)Services.getService(com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp.class);
            try
            {
                m_subAccount = finApp.getAccountManager().getSubAccount(m_accountId, m_subAccountId);
            }
            catch(NotFoundException nfe)
            {
                String msg = "Invalid accountId,subAccount id in transacion id :" + m_accountId + "," + m_subAccountId + "," + getId();
                m_log.error(msg, nfe);
                throw new RuntimeSystemException(msg, nfe);
            }
        }
        return m_subAccount;
    }

    public FinTransactionType getFinTransactionType()
    {
        return m_type;
    }

    public FinTransactionCateg getFinTransactionCateg()
    {
        return m_type.toFinTransactionCateg();
    }

    public boolean isGtlTransaction()
    {
        return getTradingModuleName() == null;
    }

    public Timestamp getTransactionTimestamp()
    {
        return m_txTimestamp;
    }

    public int hashCode()
    {
        return (int)getId();
    }

    public boolean equals(Object obj)
    {
        return (obj instanceof FinTransaction) && ((FinTransaction)obj).getId() == getId();
    }

    public Object getDataSourceObject()
    {
        return m_dataSourceObject;
    }

    private static final Logit m_log;
    private static final boolean DBG;
    private final long m_id;
    private final FinTransactionType m_type;
    private final double m_netAmount;
    private final Date m_deliveryDate;
    private final Timestamp m_txTimestamp;
    private final Object m_dataSourceObject;
    private final long m_accountId;
    private final long m_subAccountId;
    private SubAccount m_subAccount;
    private final String m_desc;
    private final String m_tradingModuleName;

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.gentrade.GeneralizedFinTransaction.class);
        DBG = m_log.ison();
    }
}
