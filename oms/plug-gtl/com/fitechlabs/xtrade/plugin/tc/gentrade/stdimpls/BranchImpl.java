// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BranchImpl.java

package com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import java.util.List;

public class BranchImpl
    implements Branch
{

    public BranchImpl(long id)
        throws DataFindException, DataQueryException, DataNetworkException
    {
        m_inst = null;
        m_mainAccounts = null;
        BranchPK pk = new BranchPK(id);
        m_branchRow = (BranchRow)Processors.getDefaultProcessor().doFindByPrimaryKeyQuery(pk, null);
    }

    public BranchImpl(Institution inst, String code)
        throws DataFindException, DataQueryException, DataNetworkException
    {
        m_inst = null;
        m_mainAccounts = null;
        String where = "institution_code = ? and BRANCH_CODE = ?";
        Object bindVars[] = {
            inst.getInstitutionCode(), code
        };
        List rows = Processors.getDefaultProcessor().doFindAllQuery(BranchRow.TYPE, "institution_code = ? and BRANCH_CODE = ?", null, bindVars);
        if(rows.size() > 0)
            m_branchRow = (BranchRow)rows.get(0);
        else
            throw new DataFindException("No branch row found with code = " + code + ", for institution:" + inst.getInstitutionCode());
    }

    public BranchImpl(BranchRow row)
    {
        m_inst = null;
        m_mainAccounts = null;
        m_branchRow = row;
    }

    public long getBranchId()
    {
        return m_branchRow.getBranchId();
    }

    public String getBranchCode()
    {
        return m_branchRow.getBranchCode();
    }

    public String getBranchName()
    {
        return m_branchRow.getBranchName();
    }

    public String getBranchNameAlt1()
    {
        return m_branchRow.getBranchNameAlt1();
    }

    public BranchTypeEnum getBranchType()
    {
        return m_branchRow.getBranchType();
    }

    public Institution getInstitution()
    {
        long instId;
        if(m_inst != null)
            break MISSING_BLOCK_LABEL_88;
        instId = m_branchRow.getInstitutionId();
        m_inst = GtlUtils.getAccountManager().getInstitution(instId);
        return m_inst;
        NotFoundException nfe;
        nfe;
        String s = "Could not obtain Institution for id : " + instId + ", in branch with id " + getBranchId();
        m_log.error(s, nfe);
        throw new RuntimeException(s);
        return m_inst;
    }

    public MainAccount[] getMainAccounts()
    {
        if(m_mainAccounts == null)
            try
            {
                QueryProcessor qp = Processors.getDefaultProcessor();
                String where = "branch_id=?";
                Object bvs[] = {
                    new Long(getBranchId())
                };
                String orderBy = "account_id";
                List rows = qp.doFindAllQuery(MainAccountRow.TYPE, "branch_id=?", "account_id", null, bvs);
                int size = rows.size();
                MainAccount mainAccounts[] = new MainAccount[size];
                AccountManager mgr = GtlUtils.getAccountManager();
                for(int i = 0; i < size; i++)
                {
                    MainAccountRow row = (MainAccountRow)rows.get(i);
                    try
                    {
                        mainAccounts[i] = mgr.getMainAccount(row.getAccountId());
                    }
                    catch(NotFoundException nfe)
                    {
                        String msg = "No MainAccount object found for account id:" + row.getAccountId();
                        m_log.error(msg, nfe);
                        throw new RuntimeSystemException(msg, nfe);
                    }
                }

                m_mainAccounts = mainAccounts;
            }
            catch(DataException de)
            {
                String msg = "Exception while getting main accounts for branch id : " + getBranchId();
                m_log.error(msg, de);
                throw new RuntimeSystemException(msg, de);
            }
        return m_mainAccounts;
    }

    public static Branch[] getBranches(Institution inst)
        throws DataQueryException, DataNetworkException
    {
        QueryProcessor qp = Processors.getDefaultProcessor();
        String where = "institution_id=?";
        Object bvs[] = {
            new Long(inst.getInstitutionId())
        };
        String orderBy = "branch_id";
        List rows = qp.doFindAllQuery(BranchRow.TYPE, "institution_id=?", "branch_id", null, bvs);
        int size = rows.size();
        Branch branches[] = new Branch[size];
        AccountManager mgr = GtlUtils.getAccountManager();
        for(int i = 0; i < size; i++)
        {
            BranchRow branchRow = (BranchRow)rows.get(i);
            try
            {
                branches[i] = mgr.getBranch(branchRow.getBranchId());
            }
            catch(NotFoundException nfe)
            {
                String msg = "Error while getting Branch object for branchId:" + branchRow.getBranchId();
                m_log.error(msg, nfe);
                throw new RuntimeSystemException(msg, nfe);
            }
        }

        return branches;
    }

    public Object getDataSourceObject()
    {
        return m_branchRow;
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
    private final BranchRow m_branchRow;
    private Institution m_inst;
    private MainAccount m_mainAccounts[];

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.BranchImpl.class);
        DBG = m_log.ison();
    }
}
