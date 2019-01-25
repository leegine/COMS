// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   InstitutionImpl.java

package com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls:
//            BranchImpl

public class InstitutionImpl
    implements Institution
{

    public InstitutionImpl(long instId)
        throws DataFindException, DataQueryException, DataNetworkException
    {
        this(InstitutionDao.findRowByPk(instId));
    }

    public InstitutionImpl(InstitutionRow row)
        throws DataFindException, DataQueryException, DataNetworkException
    {
        m_branches = null;
        if(row == null)
        {
            throw new DataFindException("No institution row found.");
        } else
        {
            m_instRow = row;
            return;
        }
    }

    public InstitutionImpl(String code)
        throws DataFindException, DataQueryException, DataNetworkException
    {
        this(InstitutionDao.findRowByInstitutionCode(code));
    }

    public long getInstitutionId()
    {
        return m_instRow.getInstitutionId();
    }

    public String getInstitutionCode()
    {
        return m_instRow.getInstitutionCode();
    }

    public String getInstitutionName()
    {
        return m_instRow.getInstitutionName();
    }

    public Branch[] getBranches()
    {
        if(m_branches == null)
            try
            {
                m_branches = BranchImpl.getBranches(this);
            }
            catch(DataException de)
            {
                m_log.warn("Exception while getting Branch rows from branch table for institution id:" + getInstitutionId());
                return new Branch[0];
            }
        return m_branches;
    }

    public Object getDataSourceObject()
    {
        return m_instRow;
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
    private final InstitutionRow m_instRow;
    private Branch m_branches[];

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.InstitutionImpl.class);
        DBG = m_log.ison();
    }
}
