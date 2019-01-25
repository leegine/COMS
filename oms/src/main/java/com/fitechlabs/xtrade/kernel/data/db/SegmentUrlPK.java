// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SegmentUrlPK.java

package com.fitechlabs.xtrade.kernel.data.db;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

// Referenced classes of package com.fitechlabs.xtrade.kernel.data.db:
//            SegmentUrlRow

public class SegmentUrlPK
    implements PrimaryKey
{

    public RowType getRowType()
    {
        return SegmentUrlRow.TYPE;
    }

    public SegmentUrlPK()
    {
        m_id = null;
    }

    public SegmentUrlPK(long p_segmentId)
    {
        m_id = null;
        segment_id = p_segmentId;
    }

    public static SegmentUrlPK fromString(String pkValueString)
        throws NumberFormatException
    {
        SegmentUrlPK pk = new SegmentUrlPK();
        pk.segment_id = Long.valueOf(pkValueString).longValue();
        pk.m_id = pkValueString;
        return pk;
    }

    public String toString()
    {
        if(m_id == null)
            m_id = String.valueOf(segment_id);
        return m_id;
    }

    public boolean equals(Object other)
    {
        if(other == null || !(other instanceof SegmentUrlPK))
            return false;
        SegmentUrlPK o = (SegmentUrlPK)other;
        return segment_id == o.segment_id;
    }

    public int hashCode()
    {
        return (int)segment_id;
    }

    public static final String TAGNAME = "pk";
    public static final String PTYPE = "segment_url";
    public long segment_id;
    private String m_id;
}
