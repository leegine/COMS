// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ErrorMessagePK.java

package com.fitechlabs.xtrade.kernel.data.db;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

// Referenced classes of package com.fitechlabs.xtrade.kernel.data.db:
//            ErrorMessageRow

public class ErrorMessagePK
    implements PrimaryKey
{

    public RowType getRowType()
    {
        return ErrorMessageRow.TYPE;
    }

    public ErrorMessagePK()
    {
        m_id = null;
    }

    public ErrorMessagePK(long p_errorMessageId)
    {
        m_id = null;
        error_message_id = p_errorMessageId;
    }

    public static ErrorMessagePK fromString(String pkValueString)
        throws NumberFormatException
    {
        ErrorMessagePK pk = new ErrorMessagePK();
        pk.error_message_id = Long.valueOf(pkValueString).longValue();
        pk.m_id = pkValueString;
        return pk;
    }

    public String toString()
    {
        if(m_id == null)
            m_id = String.valueOf(error_message_id);
        return m_id;
    }

    public boolean equals(Object other)
    {
        if(other == null || !(other instanceof ErrorMessagePK))
            return false;
        ErrorMessagePK o = (ErrorMessagePK)other;
        return error_message_id == o.error_message_id;
    }

    public int hashCode()
    {
        return (int)error_message_id;
    }

    public static final String TAGNAME = "pk";
    public static final String PTYPE = "error_message";
    public long error_message_id;
    private String m_id;
}
