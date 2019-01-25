// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SegmentUrlDao.java

package com.fitechlabs.xtrade.kernel.data.db;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.data.*;
import java.util.List;

// Referenced classes of package com.fitechlabs.xtrade.kernel.data.db:
//            SegmentUrlPK, SegmentUrlRow, AccountSegmentDao

public class SegmentUrlDao extends DataAccessObject
{

    protected SegmentUrlDao(SegmentUrlRow row)
    {
        super(row);
        this.row = row;
    }

    public SegmentUrlRow getSegmentUrlRow()
    {
        return row;
    }

    public static SegmentUrlDao forRow(SegmentUrlRow row)
        throws IllegalArgumentException
    {
        return (SegmentUrlDao)DataAccessObject.forRow(row);
    }

    public static long newPkValue()
        throws DataNetworkException, DataQueryException
    {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery(SegmentUrlRow.TYPE);
    }

    public static SegmentUrlPK newPkObject()
        throws DataNetworkException, DataQueryException
    {
        long id = newPkValue();
        return new SegmentUrlPK(id);
    }

    public static SegmentUrlRow findRowByPk(long p_segmentId)
        throws DataFindException, DataQueryException, DataNetworkException
    {
        SegmentUrlPK pk = new SegmentUrlPK(p_segmentId);
        return findRowByPk(pk);
    }

    public static SegmentUrlRow findRowByPk(SegmentUrlPK pk)
        throws DataFindException, DataQueryException, DataNetworkException
    {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (SegmentUrlRow)qp.doFindByPrimaryKeyQuery(pk, null);
    }

    /**
     * @deprecated Method findDaoByPk is deprecated
     */

    public static SegmentUrlDao findDaoByPk(long p_segmentId)
        throws DataFindException, DataQueryException, DataNetworkException
    {
        SegmentUrlPK pk = new SegmentUrlPK(p_segmentId);
        SegmentUrlRow row = findRowByPk(pk);
        return forRow(row);
    }

    /**
     * @deprecated Method findDaoByPk is deprecated
     */

    public static SegmentUrlDao findDaoByPk(SegmentUrlPK pk)
        throws DataFindException, DataQueryException, DataNetworkException
    {
        SegmentUrlRow row = findRowByPk(pk);
        return forRow(row);
    }

    public List fetchAccountSegmentRowsBySegmentId()
        throws DataNetworkException, DataQueryException
    {
        return AccountSegmentDao.findRowsBySegmentId(row);
    }

    /**
     * @deprecated Method fetchAccountSegmentDaosBySegmentId is deprecated
     */

    public List fetchAccountSegmentDaosBySegmentId()
        throws DataNetworkException, DataQueryException
    {
        return AccountSegmentDao.findDaosBySegmentId(row);
    }

    /**
     * @deprecated Method fetchAccountSegmentDaosSegmentId is deprecated
     */

    public List fetchAccountSegmentDaosSegmentId()
        throws DataNetworkException, DataQueryException
    {
        return fetchAccountSegmentDaosBySegmentId();
    }

    private SegmentUrlRow row;
    public static final com.fitechlabs.xtrade.kernel.data.DataAccessObject.Factory FACTORY = new com.fitechlabs.xtrade.kernel.data.DataAccessObject.Factory() {

        public DataAccessObject newInstance(Row row)
        {
            if(row instanceof SegmentUrlRow)
                return new SegmentUrlDao((SegmentUrlRow)row);
            else
                throw new IllegalArgumentException("Not a SegmentUrlRow or subclass: " + row.getClass());
        }

    }
;

}
