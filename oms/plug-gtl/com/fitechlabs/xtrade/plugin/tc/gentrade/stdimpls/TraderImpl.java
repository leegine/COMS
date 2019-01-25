// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TraderImpl.java

package com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderRow;
import java.util.List;

public class TraderImpl
    implements Trader
{

    public TraderImpl(long id, boolean isLoginId)
        throws DataQueryException, DataNetworkException
    {
        if(isLoginId)
        {
            QueryProcessor qp = Processors.getDefaultProcessor();
            String where = "login_id = ?";
            Object bindVars[] = {
                new Long(id)
            };
            List rows = qp.doFindAllQuery(TraderRow.TYPE, "login_id = ?", bindVars);
            if(rows.size() == 0)
                throw new DataFindException("trader row not found with login_id = " + id);
            m_row = (TraderRow)rows.get(0);
        } else
        {
            m_row = TraderDao.findRowByPk(id);
        }
    }

    public TraderImpl(Institution inst, String traderCode, String branchCode)
        throws DataQueryException, DataNetworkException
    {
        QueryProcessor qp = Processors.getDefaultProcessor();
        String where = "institution_code = ? and trader_code = ? and branch_code = ?";
        Object bindVars[] = {
            inst.getInstitutionCode(), traderCode, branchCode
        };
        List rows = qp.doFindAllQuery(TraderRow.TYPE, "institution_code = ? and trader_code = ? and branch_code = ?", bindVars);
        if(rows.size() == 0)
        {
            throw new DataFindException("trader row not found with institution_code,trader_code, branch_code : " + inst.getInstitutionCode() + "," + traderCode + "," + branchCode);
        } else
        {
            m_row = (TraderRow)rows.get(0);
            return;
        }
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

    public long getTraderId()
    {
        return m_row.getTraderId();
    }

    public String getTraderCode()
    {
        return m_row.getTraderCode();
    }

    public long getLoginId()
    {
        return m_row.getLoginId();
    }

    public TraderTypeEnum getTraderType()
    {
        return m_row.getTraderType();
    }

    public Branch getBranch()
    {
        if(m_branch == null)
            try
            {
                FinApp finApp = (FinApp)Services.getService(com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp.class);
                m_branch = finApp.getAccountManager().getBranch(getInstitution(), m_row.getBranchCode());
            }
            catch(NotFoundException nfe)
            {
                String msg = "Exception while getting branch for id : " + m_row.getBranchCode() + ", for trader id: " + getTraderId();
                m_log.error(nfe.getMessage(), nfe);
                throw new RuntimeSystemException(msg, nfe);
            }
        return m_branch;
    }

    public Institution getInstitution()
    {
        if(m_inst == null)
            try
            {
                String instCode = m_row.getInstitutionCode();
                m_inst = GtlUtils.getAccountManager().getInstitution(instCode);
            }
            catch(NotFoundException nfe)
            {
                String msg = "Exception while getting Institution  for institutionCode : " + m_row.getInstitutionCode() + ", for trader id: " + getTraderId();
                m_log.error(nfe.getMessage(), nfe);
                throw new RuntimeSystemException(msg, nfe);
            }
        return m_inst;
    }

    public int hashCode()
    {
        return (int)getTraderId();
    }

    public boolean equals(Object obj)
    {
        if(obj == this)
            return true;
        if(obj instanceof Trader)
            return ((Trader)obj).getTraderId() == getTraderId();
        else
            return false;
    }

    public Object getDataSourceObject()
    {
        return m_row;
    }

    private static final Logit m_log;
    private static final boolean DBG;
    private final TraderRow m_row;
    private Branch m_branch;
    private Institution m_inst;

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.ParticipantImpl.class);
        DBG = m_log.ison();
    }
}
