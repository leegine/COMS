// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MainAccountImpl.java

package com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls:
//            SimplePreferencesImpl, ParticipantImpl

public class MainAccountImpl
    implements MainAccount
{

    public MainAccountImpl(long id)
        throws DataQueryException, DataNetworkException
    {
        this(MainAccountDao.findRowByPk(id));
    }

    public MainAccountImpl(MainAccountRow row)
    {
        m_branch = null;
        m_participants = null;
        m_subAccounts = null;
        m_prefs = null;
        m_row = row;
    }

    public MainAccountImpl(long instId, long branchId, String accountCode)
        throws DataQueryException, DataNetworkException
    {
        this(getMainAccountRow(instId, branchId, accountCode));
    }

    public MainAccountImpl(long instId, String branchCode, String accountCode)
        throws DataQueryException, DataNetworkException
    {
        this(getMainAccountRow(instId, branchCode, accountCode));
    }

    private static MainAccountRow getMainAccountRow(long instId, long branchId, String accountCode)
        throws DataQueryException, DataNetworkException
    {
        MainAccountRow row = MainAccountDao.findRowByInstitutionIdBranchIdAccountCode(instId, branchId, accountCode);
        DataException de;
        if(row != null)
            return row;
        else
            throw new DataFindException("No main_account row found for instId,branchId,accountCode=" + instId + ',' + branchId + "," + accountCode);
        de;
        String msg = "Exception while getting account for instId,branchId,accountCode=" + instId + ',' + branchId + "," + accountCode;
        m_log.error(msg, de);
        throw new RuntimeSystemException(msg, de);
    }

    private static MainAccountRow getMainAccountRow(long instId, String branchCode, String accountCode)
        throws DataQueryException, DataNetworkException
    {
        MainAccountRow row = MainAccountDao.findRowByInstitutionIdBranchCodeAccountCode(instId, branchCode, accountCode);
        DataException de;
        if(row != null)
            return row;
        else
            throw new DataFindException("No main_account row found for instId,branchCode,accountCode=" + instId + ',' + branchCode + "," + accountCode);
        de;
        String msg = "Exception while getting account for instId,branchCode,accountCode=" + instId + ',' + branchCode + "," + accountCode;
        m_log.error(msg, de);
        throw new RuntimeSystemException(msg, de);
    }

    public long getAccountId()
    {
        return m_row.getAccountId();
    }

    public String getAccountCode()
    {
        return m_row.getAccountCode();
    }

    public PersonNameDetails getNameDetails()
    {
        PersonNameDetails name = new PersonNameDetails(m_row.getFamilyName(), m_row.getGivenName(), m_row.getMiddleName());
        return name;
    }

    public PersonNameDetails getAlt1NameDetails()
    {
        PersonNameDetails name = new PersonNameDetails(m_row.getFamilyNameAlt1(), m_row.getGivenNameAlt1(), m_row.getMiddleNameAlt1());
        return name;
    }

    public PersonNameDetails getAlt2NameDetails()
    {
        PersonNameDetails name = new PersonNameDetails(m_row.getFamilyNameAlt2(), m_row.getGivenNameAlt2(), m_row.getMiddleNameAlt2());
        return name;
    }

    public Branch getBranch()
    {
        long branchId;
        if(m_branch != null)
            break MISSING_BLOCK_LABEL_88;
        branchId = m_row.getBranchId();
        m_branch = GtlUtils.getAccountManager().getBranch(branchId);
        return m_branch;
        NotFoundException nfe;
        nfe;
        String s = "Could not obtain branch  for id : " + branchId + ", for account  with id " + getAccountId();
        m_log.error(s, nfe);
        throw new RuntimeException(s);
        return m_branch;
    }

    public ContactInfoDetails getContactInfoDetails()
    {
        ContactInfoDetails contact = new ContactInfoDetails(m_row.getAddressLine1(), m_row.getAddressLine2(), m_row.getZipCode(), m_row.getSubZipCode(), m_row.getTelephone(), m_row.getEmailAddress(), m_row.getEmailAddressAlt1(), m_row.getFax());
        return contact;
    }

    public String getTradingPassword()
    {
        return m_row.getTradingPassword();
    }

    public SubAccount[] getSubAccounts()
    {
        if(m_subAccounts == null)
            try
            {
                long accountId = getAccountId();
                QueryProcessor qp = Processors.getDefaultProcessor();
                String where = "account_id=?";
                Object bvs[] = {
                    new Long(accountId)
                };
                String orderBy = "sub_account_id";
                List rows = qp.doFindAllQuery(SubAccountRow.TYPE, "account_id=?", "sub_account_id", null, bvs);
                int size = rows.size();
                SubAccount subAccounts[] = new SubAccount[size];
                AccountManager mgr = GtlUtils.getAccountManager();
                for(int i = 0; i < size; i++)
                {
                    SubAccountRow row = (SubAccountRow)rows.get(i);
                    try
                    {
                        subAccounts[i] = mgr.getSubAccount(accountId, row.getSubAccountId());
                    }
                    catch(NotFoundException nfe)
                    {
                        String msg = "No SubAccount object could be obtained for accountId,subAccountId=" + accountId + "," + row.getSubAccountId();
                        m_log.error(msg, nfe);
                        throw new RuntimeSystemException(msg, nfe);
                    }
                }

                m_subAccounts = subAccounts;
            }
            catch(DataException de)
            {
                String msg = "Exception while getting SubAccounts for account_id = " + getAccountId();
                m_log.error(msg, de);
                throw new RuntimeSystemException(msg, de);
            }
        return m_subAccounts;
    }

    public Participant[] getParticipants()
    {
        if(m_participants == null)
            try
            {
                m_participants = ParticipantImpl.getParticipants(this);
            }
            catch(DataException de)
            {
                m_log.warn("Exception while getting Participant rows from participant table for account id :" + getAccountId());
                return new Participant[0];
            }
        return m_participants;
    }

    public Preferences getPreferences()
    {
        if(m_prefs != null)
            return m_prefs;
        String where = "account_id = ?";
        String orderBy = "name, name_serial_no";
        Object bindVars[] = {
            new Long(getAccountId())
        };
        List rows = Processors.getDefaultProcessor().doFindAllQuery(AccountPreferencesRow.TYPE, "account_id = ?", "name, name_serial_no", null, bindVars);
        int size = rows.size();
        String prevPrefName = null;
        ArrayList groupedPrefsByName = new ArrayList();
        SimplePreferencesImpl simplePrefs = new SimplePreferencesImpl();
        for(int i = 0; i < size; i++)
        {
            AccountPreferencesRow r = (AccountPreferencesRow)rows.get(i);
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
        m_log.error("Exception while fetching account_preferences table.", de);
        return new SimplePreferencesImpl();
    }

    public Object getDataSourceObject()
    {
        return m_row;
    }

    public SubAccount getSubAccount(SubAccountTypeEnum subAccountType)
        throws NotFoundException
    {
        return GtlUtils.getAccountManager().getSubAccount(getAccountId(), subAccountType);
    }

    private void serializeOperations(boolean wait)
        throws ResourceBusyException
    {
        boolean isException = false;
        Throwable cause = null;
        try
        {
            QueryProcessor qp = Processors.getDefaultProcessor();
            com.fitechlabs.dbind.PrimaryKey pk = new MainAccountPK(getAccountId());
            qp.doFindByPrimaryKeyQuery(pk, "FOR UPDATE " + (wait ? "" : "NOWAIT"));
            return;
        }
        catch(DataFindException dfe) { }
        catch(DataQueryException dqe)
        {
            if(wait)
            {
                isException = true;
                cause = dqe;
            }
        }
        catch(DataNetworkException dne)
        {
            isException = true;
            cause = dne;
        }
        if(isException)
        {
            m_log.error(cause.getMessage(), cause);
            throw new RuntimeSystemException("Error while locking account id :" + getAccountId(), cause);
        } else
        {
            throw new ResourceBusyException("MainAccount with mainAccountId = " + getAccountId() + " could not be locked. Resource may be busy");
        }
    }

    public void serializeOperationsWithNoWait()
        throws ResourceBusyException
    {
        serializeOperations(false);
    }

    public void serializeOperationsWithWait()
    {
        try
        {
            serializeOperations(true);
        }
        catch(ResourceBusyException rbe)
        {
            m_log.error("Unexpected exception.", rbe);
            throw new RuntimeSystemException("Unexpected exception: " + rbe.getMessage(), rbe);
        }
    }

    public MainAccountStatusEnum getAccountStatus()
    {
        return m_row.getAccountStatus();
    }

    public Institution getInstitution()
    {
        long institutionId = m_row.getInstitutionId();
        return GtlUtils.getAccountManager().getInstitution(institutionId);
        NotFoundException nfe;
        nfe;
        String msg = "No Institution found with id : " + institutionId + ", for acccountId in main_account :" + getAccountId();
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
    private final MainAccountRow m_row;
    private Branch m_branch;
    private Participant m_participants[];
    private SubAccount m_subAccounts[];
    private Preferences m_prefs;

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.MainAccountImpl.class);
        DBG = m_log.ison();
    }
}
