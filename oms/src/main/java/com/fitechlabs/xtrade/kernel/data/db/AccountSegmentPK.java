// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AccountSegmentPK.java

package com.fitechlabs.xtrade.kernel.data.db;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

// Referenced classes of package com.fitechlabs.xtrade.kernel.data.db:
//            AccountSegmentRow

public class AccountSegmentPK
    implements PrimaryKey
{

    public RowType getRowType()
    {
        return AccountSegmentRow.TYPE;
    }

    public AccountSegmentPK()
    {
        m_id = null;
    }

    public AccountSegmentPK(long p_accountId)
    {
        m_id = null;
        account_id = p_accountId;
    }

    public static AccountSegmentPK fromString(String pkValueString)
        throws NumberFormatException
    {
        AccountSegmentPK pk = new AccountSegmentPK();
        pk.account_id = Long.valueOf(pkValueString).longValue();
        pk.m_id = pkValueString;
        return pk;
    }

    public String toString()
    {
        if(m_id == null)
            m_id = String.valueOf(account_id);
        return m_id;
    }

    public boolean equals(Object other)
    {
        if(other == null || !(other instanceof AccountSegmentPK))
            return false;
        AccountSegmentPK o = (AccountSegmentPK)other;
        return account_id == o.account_id;
    }

    public int hashCode()
    {
        return (int)account_id;
    }

    public static final String TAGNAME = "pk";
    public static final String PTYPE = "account_segment";
    public long account_id;
    private String m_id;
}
