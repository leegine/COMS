// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SubAccountImpl.java

package com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import java.util.*;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls:
//            SimplePreferencesImpl

public class SubAccountImpl
    implements SubAccount
{

    public SubAccountImpl(MainAccount mainAcct, SubAccountRow subAcctRow)
    {
        m_mainAccount = null;
        m_prefs = null;
        m_mainAccount = mainAcct;
        m_subAcctRow = subAcctRow;
    }

    public SubAccountImpl(long accountId, long subAccountId)
        throws DataQueryException, DataNetworkException
    {
        this((MainAccount)null, SubAccountDao.findRowByPk(accountId, subAccountId));
    }

    public SubAccountImpl(SubAccountRow subAcctRow)
    {
        this((MainAccount)null, subAcctRow);
    }

    private long getMainAccountId()
    {
        return m_subAcctRow.getAccountId();
    }

    public long getSubAccountId()
    {
        return m_subAcctRow.getSubAccountId();
    }

    public long getAccountId()
    {
        return m_subAcctRow.getAccountId();
    }

    public MainAccount getMainAccount()
    {
        if(m_mainAccount == null)
            try
            {
                m_mainAccount = GtlUtils.getAccountManager().getMainAccount(getMainAccountId());
            }
            catch(NotFoundException nfe)
            {
                m_log.error("MainAccount could not be obtained for sub account id: " + getSubAccountId() + ", MainAccount ID:" + getMainAccountId(), nfe);
            }
        return m_mainAccount;
    }

    public SubAccountTypeEnum getSubAccountType()
    {
        return m_subAcctRow.getSubAccountType();
    }

    public SubAccountStatusEnum getSubAccountStatus()
    {
        return m_subAcctRow.getSubAccountStatus();
    }

    public double getCashBalance()
    {
        return m_subAcctRow.getCashBalance();
    }

    public Date getSubAccountOpenDate()
    {
        return m_subAcctRow.getOpenDate();
    }

    public Date getSubAccountClosedDate()
    {
        return m_subAcctRow.getCloseDate();
    }

    public Preferences getPreferences()
    {
        if(m_prefs != null)
            return m_prefs;
        String where = "account_id = ? AND sub_account_id = ?";
        String orderBy = "name, name_serial_no";
        Object bindVars[] = {
            new Long(m_mainAccount.getAccountId()), new Long(getSubAccountId())
        };
        List rows = Processors.getDefaultProcessor().doFindAllQuery(SubAccountPreferencesRow.TYPE, "account_id = ? AND sub_account_id = ?", "name, name_serial_no", null, bindVars);
        int size = rows.size();
        String prevPrefName = null;
        ArrayList groupedPrefsByName = new ArrayList();
        SimplePreferencesImpl simplePrefs = new SimplePreferencesImpl();
        for(int i = 0; i < size; i++)
        {
            SubAccountPreferencesRow r = (SubAccountPreferencesRow)rows.get(i);
            String prefName = r.getName();
            if(!prefName.equals(prevPrefName))
            {
                if(prevPrefName != null)
                    simplePrefs.add(prevPrefName, groupedPrefsByName);
                prevPrefName = prefName;
                groupedPrefsByName = new ArrayList();
            }
            groupedPrefsByName.add(r.getValue());
        }

        if(groupedPrefsByName.size() > 0)
            simplePrefs.add(prevPrefName, groupedPrefsByName);
        m_prefs = simplePrefs;
        return m_prefs;
        DataException de;
        de;
        m_log.error("Exception while fetching sub_account_preferences table.", de);
        return new SimplePreferencesImpl();
    }

    public ProcessingResult adjustCashBalance(double amount)
    {
        if(amount != 0.0D)
        {
            final double adjustAmount = amount;
            try
            {
                final QueryProcessor qp = Processors.getDefaultProcessor();
                qp.doTransaction(1, new TransactionCallback() {

                    public Object process()
                        throws DataNetworkException, DataQueryException, DataCallbackException
                    {
                        long accountId;
                        long subAccountId;
                        if(SubAccountImpl.DBG)
                            SubAccountImpl.m_log.debug("Updating cash balance. amount : " + adjustAmount);
                        accountId = getMainAccountId();
                        subAccountId = getSubAccountId();
                        serializeOperations(true);
                        SubAccountRow subAccountRow = SubAccountDao.findRowByPk(accountId, subAccountId);
                        double currentBalance = subAccountRow.getCashBalance();
                        double newBalance = Math.floor(currentBalance + adjustAmount);
                        Map changes = new HashMap();
                        changes.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());
                        changes.put("cash_balance", new Double(newBalance));
                        qp.doUpdateQuery(subAccountRow.getPrimaryKey(), changes);
                        return null;
                        ResourceBusyException rbe;
                        rbe;
                        String msg = "SubAccount could not be locked. account-subaccountid:" + accountId + "-" + subAccountId;
                        SubAccountImpl.m_log.error(rbe.getMessage(), rbe);
                        throw new RuntimeSystemException(msg, rbe);
                        DataFindException dfe;
                        dfe;
                        SubAccountImpl.m_log.error(dfe.getMessage(), dfe);
                        throw new RuntimeSystemException("SubAccount not found.", dfe);
                    }

                }
);
            }
            catch(DataException de)
            {
                String msg = "Exception adjusting cash balance for accountId-subAccountId : " + getMainAccountId() + "-" + getSubAccountId();
                m_log.error(de.getMessage(), de);
                throw new RuntimeSystemException(msg, de);
            }
        }
        return ProcessingResult.newSuccessResultInstance();
    }

    public void serializeOperations(boolean wait)
        throws ResourceBusyException
    {
        if(wait)
            serializeOperationsWithWait();
        else
            serializeOperationsWithNoWait();
    }

    public void serializeOperationsWithNoWait()
        throws ResourceBusyException
    {
        getMainAccount().serializeOperationsWithNoWait();
    }

    public void serializeOperationsWithWait()
    {
        getMainAccount().serializeOperationsWithWait();
    }

    public Object getDataSourceObject()
    {
        return m_subAcctRow;
    }

    public Institution getInstitution()
    {
        long institutionId = m_subAcctRow.getInstitutionId();
        return GtlUtils.getAccountManager().getInstitution(institutionId);
        NotFoundException nfe;
        nfe;
        String msg = "No Institution found with id : " + institutionId + ", for acccountId in sub_account :" + getAccountId() + ", subAccountId : " + getSubAccountId();
        m_log.error(msg, nfe);
        throw new IllegalStateException(msg);
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
    private final SubAccountRow m_subAcctRow;
    private MainAccount m_mainAccount;
    private Preferences m_prefs;

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.SubAccountImpl.class);
        DBG = m_log.ison();
    }



}
