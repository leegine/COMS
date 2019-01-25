// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ParticipantImpl.java

package com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ParticipantDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ParticipantRow;
import java.util.List;

public class ParticipantImpl
    implements Participant
{

    public ParticipantImpl(MainAccount acct, long participantId)
        throws DataQueryException, DataNetworkException
    {
        m_mainAccount = acct;
        m_row = ParticipantDao.findDaoByPk(acct.getAccountId(), participantId).getParticipantRow();
    }

    public ParticipantImpl(MainAccount acct, ParticipantRow participantRow)
        throws DataQueryException, DataNetworkException
    {
        m_mainAccount = acct;
        m_row = participantRow;
    }

    public long getParticipantId()
    {
        return m_row.getParticipantId();
    }

    public String getParticipantCode()
    {
        return m_row.getParticipantCode();
    }

    public MainAccount getMainAccount()
    {
        return m_mainAccount;
    }

    public ParticipantTypeEnum getParticipantType()
    {
        return m_row.getParticipantType();
    }

    public PersonNameDetails getNameDetails()
    {
        PersonNameDetails name = new PersonNameDetails(m_row.getParticipantFamilyName(), m_row.getParticipantGivenName(), m_row.getParticipantMiddleName());
        return name;
    }

    public PersonNameDetails getAlt1NameDetails()
    {
        PersonNameDetails name = new PersonNameDetails(m_row.getParticipantFamilyNameAlt1(), m_row.getParticipantGivenNameAlt1(), m_row.getParticipantMiddleNameAlt1());
        return name;
    }

    static Participant[] getParticipants(MainAccount acct)
        throws DataQueryException, DataNetworkException
    {
        QueryProcessor qp = Processors.getDefaultProcessor();
        String where = "account_id = ?";
        Object bvs[] = {
            new Long(acct.getAccountId())
        };
        String orderBy = "participant_id";
        List rows = qp.doFindAllQuery(ParticipantRow.TYPE, "account_id = ?", "participant_id", null, bvs);
        int size = rows.size();
        Participant participants[] = new Participant[size];
        for(int i = 0; i < size; i++)
        {
            ParticipantRow r = (ParticipantRow)rows.get(i);
            participants[i] = new ParticipantImpl(acct, r);
        }

        return participants;
    }

    public Object getDataSourceObject()
    {
        return m_row;
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
    private final ParticipantRow m_row;
    private final MainAccount m_mainAccount;

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.ParticipantImpl.class);
        DBG = m_log.ison();
    }
}
