// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeFinTransactionImpl.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeFinTransaction;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeFinTransactionDao;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeFinTransactionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import java.sql.Timestamp;
import java.util.Date;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls:
//            PersistenceManagerImpl

public class EqTypeFinTransactionImpl
    implements EqTypeFinTransaction
{

    public EqTypeFinTransactionImpl(long fin_transaction_id)
        throws DataQueryException, DataNetworkException
    {
        this(EqtypeFinTransactionDao.findRowByPk(fin_transaction_id));
    }

    public EqTypeFinTransactionImpl(EqtypeFinTransactionRow row)
    {
        m_Row = row;
    }

    /**
     * @deprecated Method EqTypeFinTransactionImpl is deprecated
     */

    EqTypeFinTransactionImpl(PersistenceManagerImpl pm, long fin_transaction_id)
        throws DataQueryException, DataNetworkException
    {
        this(pm, EqtypeFinTransactionDao.findRowByPk(fin_transaction_id));
    }

    /**
     * @deprecated Method EqTypeFinTransactionImpl is deprecated
     */

    EqTypeFinTransactionImpl(PersistenceManagerImpl pm, EqtypeFinTransactionRow row)
    {
        m_Row = row;
    }

    public long getAccountId()
    {
        return m_Row.getAccountId();
    }

    public long getId()
    {
        return m_Row.getFinTransactionId();
    }

    public double getNetAmount()
    {
        return m_Row.getNetAmount();
    }

    public FinTransactionType getFinTransactionType()
    {
        return m_Row.getFinTransactionType();
    }

    public FinTransactionCateg getFinTransactionCateg()
    {
        return m_Row.getFinTransactionCateg();
    }

    public Timestamp getTransactionTimestamp()
    {
        return m_Row.getFinTransactionTimestamp();
    }

    public long getSubAccountId()
    {
        return m_Row.getSubAccountId();
    }

    public Date getDeliveryDate()
    {
        return m_Row.getDeliveryDate();
    }

    public SubAccount getSubAccount()
    {
        FinApp finApp = (FinApp)Services.getService(com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp.class);
        SubAccount subAccount = finApp.getAccountManager().getSubAccount(getAccountId(), getSubAccountId());
        return subAccount;
        NotFoundException nfe;
        nfe;
        m_log.error(nfe.getMessage(), nfe);
        throw new RuntimeSystemException("SubAccount object could not be obtained for account_id/sub_account_id : " + getAccountId() + "/" + getSubAccountId(), nfe);
    }

    public Object getDataSourceObject()
    {
        return m_Row;
    }

    private static final Logit m_log;
    private static final boolean DBG;
    protected final EqtypeFinTransactionRow m_Row;

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeFinTransactionImpl.class);
        DBG = m_log.ison();
    }
}
