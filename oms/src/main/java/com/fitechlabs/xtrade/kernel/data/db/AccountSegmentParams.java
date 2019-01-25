// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AccountSegmentParams.java

package com.fitechlabs.xtrade.kernel.data.db;

import com.fitechlabs.dbind.*;
import java.util.Map;

// Referenced classes of package com.fitechlabs.xtrade.kernel.data.db:
//            AccountSegmentRow, AccountSegmentPK

public class AccountSegmentParams extends Params
    implements AccountSegmentRow
{

    public RowType getRowType()
    {
        return AccountSegmentRow.TYPE;
    }

    public String toString()
    {
        return "{account_id=" + account_id + "," + "segment_id=" + segment_id + "}";
    }

    public AccountSegmentParams()
    {
    }

    public AccountSegmentParams(AccountSegmentRow p_row)
    {
        account_id = p_row.getAccountId();
        account_id_is_set = p_row.getAccountIdIsSet();
        segment_id = p_row.getSegmentId();
        segment_id_is_set = p_row.getSegmentIdIsSet();
    }

    public void markAllValuesAsSet()
    {
        super.markAllValuesAsSet();
        segment_id_is_set = true;
    }

    public boolean equals(Object other)
    {
        if(!(other instanceof AccountSegmentRow))
            return false;
        else
            return fieldsEqual((AccountSegmentRow)other);
    }

    public boolean fieldsEqual(AccountSegmentRow row)
    {
        if(account_id != row.getAccountId())
            return false;
        return segment_id == row.getSegmentId();
    }

    public int hashCode()
    {
        return (int)account_id + (int)segment_id;
    }

    protected void assertValidForInsert()
        throws IllegalArgumentException
    {
        super.assertValidForInsert();
        if(!segment_id_is_set)
            throw new IllegalArgumentException("non-nullable field 'segment_id' must be set before inserting.");
        else
            return;
    }

    public Map toInsertMap()
    {
        assertValidForInsert();
        Map map = super.toInsertMap();
        map.put("account_id", new Long(account_id));
        map.put("segment_id", new Long(segment_id));
        map.remove("rowid");
        return map;
    }

    public Map toUpdateMap()
    {
        Map map = super.toUpdateMap();
        if(segment_id_is_set)
            map.put("segment_id", new Long(segment_id));
        return map;
    }

    public final long getAccountId()
    {
        return account_id;
    }

    public final boolean getAccountIdIsSet()
    {
        return account_id_is_set;
    }

    public final long getSegmentId()
    {
        return segment_id;
    }

    public final boolean getSegmentIdIsSet()
    {
        return segment_id_is_set;
    }

    public PrimaryKey getPrimaryKey()
    {
        return new AccountSegmentPK(account_id);
    }

    public final void setAccountId(long p_accountId)
    {
        if(!mutable())
        {
            throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
        } else
        {
            account_id = p_accountId;
            account_id_is_set = true;
            return;
        }
    }

    public final void setSegmentId(long p_segmentId)
    {
        if(!mutable())
        {
            throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
        } else
        {
            segment_id = p_segmentId;
            segment_id_is_set = true;
            return;
        }
    }

    public Object getColumn(String name)
    {
        if(name == null || name.length() <= 0)
            throw new IllegalArgumentException("name cannot be null.");
        switch(name.charAt(0))
        {
        default:
            break;

        case 97: // 'a'
            if(name.equals("account_id"))
                return new Long(account_id);
            break;

        case 115: // 's'
            if(name.equals("segment_id"))
                return new Long(segment_id);
            break;
        }
        throw new IllegalArgumentException("field '" + name + "' not found.");
    }

    public void setColumn(String name, Object value)
    {
        if(!mutable())
            throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
        if(name == null || name.length() <= 0)
            throw new IllegalArgumentException("name cannot be null.");
        switch(name.charAt(0))
        {
        default:
            break;

        case 97: // 'a'
            if(name.equals("account_id"))
                if(!(value instanceof Long))
                {
                    throw new IllegalArgumentException("value for 'account_id' must be Long: '" + value + "' is not.");
                } else
                {
                    account_id = ((Long)value).longValue();
                    account_id_is_set = true;
                    return;
                }
            break;

        case 115: // 's'
            if(!name.equals("segment_id"))
                break;
            if(!(value instanceof Long))
            {
                throw new IllegalArgumentException("value for 'segment_id' must be Long: '" + value + "' is not.");
            } else
            {
                segment_id = ((Long)value).longValue();
                segment_id_is_set = true;
                return;
            }
        }
        throw new IllegalArgumentException("field '" + name + "' not found.");
    }

    public static final String TAGNAME = "row";
    public static final String PTYPE = "account_segment";
    public static final RowType TYPE;
    public long account_id;
    public long segment_id;
    private boolean account_id_is_set;
    private boolean segment_id_is_set;

    static 
    {
        TYPE = AccountSegmentRow.TYPE;
    }
}
