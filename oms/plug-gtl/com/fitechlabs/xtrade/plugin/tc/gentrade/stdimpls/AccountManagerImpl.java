// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AccountManagerImpl.java

package com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountDao;
import java.util.List;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls:
//            MainAccountImpl, SubAccountImpl, InstitutionImpl, BranchImpl

public class AccountManagerImpl
    implements AccountManager
{

    public AccountManagerImpl()
    {
    }

    static AccountManager getInstance()
    {
        return m_thisInstance;
    }

    public MainAccount getMainAccount(long accountId)
        throws NotFoundException
    {
        return new MainAccountImpl(accountId);
        DataFindException dfe;
        dfe;
        throw new NotFoundException("No MainAccount could be found with id : " + accountId);
        DataException de;
        de;
        String msg = "Exception while getting MainAccount for Id:" + accountId;
        m_log.error(msg, de);
        throw new RuntimeSystemException(msg, de);
    }

    public MainAccount getMainAccount(long institutionId, long branchId, String accountCode)
        throws NotFoundException
    {
        return new MainAccountImpl(institutionId, branchId, accountCode);
        DataFindException dfe;
        dfe;
        throw new NotFoundException("No MainAccount could be found with instId,branchId,accountCode: " + institutionId + "," + branchId + "," + accountCode);
        DataException de;
        de;
        String msg = "Exception while getting MainAccount for inst id:" + institutionId + ", branchId: " + branchId + ", accountCode :" + accountCode;
        m_log.error(msg, de);
        throw new RuntimeSystemException(msg, de);
    }

    public MainAccount getMainAccount(Institution inst, Branch branch, String accountCode)
        throws NotFoundException
    {
        return getMainAccount(inst.getInstitutionId(), branch.getBranchId(), accountCode);
    }

    public MainAccount getMainAccount(long institutionId, String branchCode, String accountCode)
        throws NotFoundException
    {
        return new MainAccountImpl(institutionId, branchCode, accountCode);
        DataFindException dfe;
        dfe;
        throw new NotFoundException("No MainAccount could be found with instId,branchCode,accountCode: " + institutionId + "," + branchCode + "," + accountCode);
        DataException de;
        de;
        String msg = "Exception while getting MainAccount for inst id:" + institutionId + ", branchCode: " + branchCode + ", accountCode :" + accountCode;
        m_log.error(msg, de);
        throw new RuntimeSystemException(msg, de);
    }

    public SubAccount getSubAccount(long accountId, long subAccountId)
        throws NotFoundException
    {
        return new SubAccountImpl(accountId, subAccountId);
        DataFindException dfe;
        dfe;
        throw new NotFoundException("SubAccount not found for accountId= " + accountId + ", SubAccount Id : " + subAccountId);
        DataException de;
        de;
        String msg = "Exception while getting SubAccount for accountId:" + accountId + ", subAccountId:" + subAccountId;
        m_log.error(msg, de);
        throw new RuntimeSystemException(msg, de);
    }

    public SubAccount getSubAccount(long accountId, SubAccountTypeEnum subAccountType)
        throws NotFoundException
    {
        com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow row = SubAccountDao.findRowByAccountIdSubAccountType(accountId, subAccountType);
        if(row != null)
            return new SubAccountImpl(row);
        try
        {
            throw new NotFoundException("SubAccount not found for accountId= " + accountId + ", SubAccountType : " + subAccountType);
        }
        catch(DataException de)
        {
            String msg = "Exception while getting SubAccount for accountId:" + accountId + ", SubAccountType :" + subAccountType;
            m_log.error(msg, de);
            throw new RuntimeSystemException(msg, de);
        }
    }

    public SubAccount[] getSubAccounts(long accountId)
    {
        AccountManager acctMgr = GtlUtils.getAccountManager();
        MainAccount ma = acctMgr.getMainAccount(accountId);
        return ma.getSubAccounts();
        NotFoundException nfe;
        nfe;
        String msg = "No MainAccount found. Invalid AccountId: " + accountId;
        m_log.warn(msg, nfe);
        return new SubAccount[0];
    }

    public Institution getInstitution(long instId)
        throws NotFoundException
    {
        return new InstitutionImpl(instId);
        DataFindException dfe;
        dfe;
        throw new NotFoundException("No institution   object found with the  id : " + instId);
        DataException de;
        de;
        String msg = "Error while getting institution  Object for  id : " + instId;
        m_log.error(msg, de);
        throw new RuntimeSystemException(msg, de);
    }

    public Institution getInstitution(String code)
        throws NotFoundException
    {
        return new InstitutionImpl(code);
        DataFindException dfe;
        dfe;
        throw new NotFoundException("No Institution   object found with the  code : " + code);
        DataException de;
        de;
        String msg = "Error while getting Institution  Object for  code : " + code;
        m_log.error(msg, de);
        throw new RuntimeSystemException(msg, de);
    }

    public Institution[] getAllInstitutions()
    {
        Institution insts[];
        QueryProcessor qp = Processors.getDefaultProcessor();
        List rows = qp.doFindAllQuery(InstitutionRow.TYPE, null, "institution_id", null, null);
        int size = rows.size();
        insts = new Institution[size];
        AccountManager mgr = GtlUtils.getAccountManager();
        for(int i = 0; i < size; i++)
        {
            InstitutionRow r = (InstitutionRow)rows.get(i);
            try
            {
                insts[i] = mgr.getInstitution(r.getInstitutionId());
            }
            catch(NotFoundException nfe)
            {
                String msg = "no institution found for id:" + r.getInstitutionId();
                m_log.error(msg, nfe);
                throw new RuntimeSystemException(msg, nfe);
            }
        }

        return insts;
        DataException de;
        de;
        String msg = "Exception while getting all institutions.";
        m_log.error("Exception while getting all institutions.", de);
        throw new RuntimeSystemException("Exception while getting all institutions.", de);
    }

    public Branch getBranch(long branchId)
        throws NotFoundException
    {
        return new BranchImpl(branchId);
        DataFindException dfe;
        dfe;
        throw new NotFoundException("No branch   object found with the  id : " + branchId);
        DataException de;
        de;
        String msg = "Error while getting Branch  Object for  id : " + branchId;
        m_log.error(msg, de);
        throw new RuntimeSystemException(msg, de);
    }

    public Branch getBranch(Institution inst, String branchCode)
        throws NotFoundException
    {
        return new BranchImpl(inst, branchCode);
        DataFindException dfe;
        dfe;
        throw new NotFoundException("No branch   object found with the  code : " + branchCode);
        DataException de;
        de;
        String msg = "Error while getting Branch  Object for  code : " + branchCode;
        m_log.error(msg, de);
        throw new RuntimeSystemException(msg, de);
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
    private static AccountManager m_thisInstance = new AccountManagerImpl();

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.AccountManagerImpl.class);
        DBG = m_log.ison();
    }
}
