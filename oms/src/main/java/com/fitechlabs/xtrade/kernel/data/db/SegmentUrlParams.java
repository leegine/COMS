// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SegmentUrlParams.java

package com.fitechlabs.xtrade.kernel.data.db;

import com.fitechlabs.dbind.*;
import java.util.Map;

// Referenced classes of package com.fitechlabs.xtrade.kernel.data.db:
//            SegmentUrlRow, SegmentUrlPK

public class SegmentUrlParams extends Params
    implements SegmentUrlRow
{

    public RowType getRowType()
    {
        return SegmentUrlRow.TYPE;
    }

    public String toString()
    {
        return "{segment_id=" + segment_id + "," + "data_source_url=" + data_source_url + "}";
    }

    public SegmentUrlParams()
    {
    }

    public SegmentUrlParams(SegmentUrlRow p_row)
    {
        segment_id = p_row.getSegmentId();
        segment_id_is_set = p_row.getSegmentIdIsSet();
        data_source_url = p_row.getDataSourceUrl();
        data_source_url_is_set = p_row.getDataSourceUrlIsSet();
    }

    public void markAllValuesAsSet()
    {
        super.markAllValuesAsSet();
        data_source_url_is_set = true;
    }

    public boolean equals(Object other)
    {
        if(!(other instanceof SegmentUrlRow))
            return false;
        else
            return fieldsEqual((SegmentUrlRow)other);
    }

    public boolean fieldsEqual(SegmentUrlRow row)
    {
        if(segment_id != row.getSegmentId())
            return false;
        if(data_source_url == null)
        {
            if(row.getDataSourceUrl() != null)
                return false;
        } else
        if(!data_source_url.equals(row.getDataSourceUrl()))
            return false;
        return true;
    }

    public int hashCode()
    {
        return (int)segment_id + (data_source_url == null ? 0 : data_source_url.hashCode());
    }

    protected void assertValidForInsert()
        throws IllegalArgumentException
    {
        super.assertValidForInsert();
        if(!data_source_url_is_set)
            throw new IllegalArgumentException("non-nullable field 'data_source_url' must be set before inserting.");
        else
            return;
    }

    public Map toInsertMap()
    {
        assertValidForInsert();
        Map map = super.toInsertMap();
        map.put("segment_id", new Long(segment_id));
        map.put("data_source_url", data_source_url);
        map.remove("rowid");
        return map;
    }

    public Map toUpdateMap()
    {
        Map map = super.toUpdateMap();
        if(data_source_url_is_set)
            map.put("data_source_url", data_source_url);
        return map;
    }

    public final long getSegmentId()
    {
        return segment_id;
    }

    public final boolean getSegmentIdIsSet()
    {
        return segment_id_is_set;
    }

    public final String getDataSourceUrl()
    {
        return data_source_url;
    }

    public final boolean getDataSourceUrlIsSet()
    {
        return data_source_url_is_set;
    }

    public PrimaryKey getPrimaryKey()
    {
        return new SegmentUrlPK(segment_id);
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

    public final void setDataSourceUrl(String p_dataSourceUrl)
    {
        if(!mutable())
        {
            throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
        } else
        {
            data_source_url = p_dataSourceUrl;
            data_source_url_is_set = true;
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

        case 100: // 'd'
            if(name.equals("data_source_url"))
                return data_source_url;
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

        case 100: // 'd'
            if(name.equals("data_source_url"))
                if(!(value instanceof String))
                {
                    throw new IllegalArgumentException("value for 'data_source_url' must be String: '" + value + "' is not.");
                } else
                {
                    data_source_url = (String)value;
                    data_source_url_is_set = true;
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
    public static final String PTYPE = "segment_url";
    public static final RowType TYPE;
    public long segment_id;
    public String data_source_url;
    private boolean segment_id_is_set;
    private boolean data_source_url_is_set;

    static 
    {
        TYPE = SegmentUrlRow.TYPE;
    }
}
