// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AccountSegmentDao.java

package com.fitechlabs.xtrade.kernel.data.db;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.data.*;
import java.util.List;

// Referenced classes of package com.fitechlabs.xtrade.kernel.data.db:
//            AccountSegmentPK, AccountSegmentRow, SegmentUrlPK, SegmentUrlRow, 
//            SegmentUrlDao

public class AccountSegmentDao extends DataAccessObject
{

    protected AccountSegmentDao(AccountSegmentRow row)
    {
        super(row);
        this.row = row;
    }

    public AccountSegmentRow getAccountSegmentRow()
    {
        return row;
    }

    public static AccountSegmentDao forRow(AccountSegmentRow row)
        throws IllegalArgumentException
    {
        return (AccountSegmentDao)DataAccessObject.forRow(row);
    }

    public static long newPkValue()
        throws DataNetworkException, DataQueryException
    {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery(AccountSegmentRow.TYPE);
    }

    public static AccountSegmentPK newPkObject()
        throws DataNetworkException, DataQueryException
    {
        long id = newPkValue();
        return new AccountSegmentPK(id);
    }

    public static AccountSegmentRow findRowByPk(long p_accountId)
        throws DataFindException, DataQueryException, DataNetworkException
    {
        AccountSegmentPK pk = new AccountSegmentPK(p_accountId);
        return findRowByPk(pk);
    }

    public static AccountSegmentRow findRowByPk(AccountSegmentPK pk)
        throws DataFindException, DataQueryException, DataNetworkException
    {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (AccountSegmentRow)qp.doFindByPrimaryKeyQuery(pk, null);
    }

    /**
     * @deprecated Method findDaoByPk is deprecated
     */

    public static AccountSegmentDao findDaoByPk(long p_accountId)
        throws DataFindException, DataQueryException, DataNetworkException
    {
        AccountSegmentPK pk = new AccountSegmentPK(p_accountId);
        AccountSegmentRow row = findRowByPk(pk);
        return forRow(row);
    }

    /**
     * @deprecated Method findDaoByPk is deprecated
     */

    public static AccountSegmentDao findDaoByPk(AccountSegmentPK pk)
        throws DataFindException, DataQueryException, DataNetworkException
    {
        AccountSegmentRow row = findRowByPk(pk);
        return forRow(row);
    }

    public SegmentUrlRow fetchSegmentUrlRowViaSegmentId()
        throws DataNetworkException, DataFindException, DataQueryException
    {
        SegmentUrlPK pk = new SegmentUrlPK(this.row.getSegmentId());
        Row row = SegmentUrlDao.findRowByPk(pk);
        if(row != null && !(row instanceof SegmentUrlRow))
            throw new IllegalStateException("not a subclass: " + row.getClass());
        else
            return (SegmentUrlRow)row;
    }

    /**
     * @deprecated Method fetchSegmentUrlDaoViaSegmentId is deprecated
     */

    public SegmentUrlDao fetchSegmentUrlDaoViaSegmentId()
        throws DataNetworkException, DataFindException, DataQueryException
    {
        SegmentUrlPK pk = new SegmentUrlPK(row.getSegmentId());
        DataAccessObject dao = SegmentUrlDao.findDaoByPk(pk);
        if(dao != null && !(dao instanceof SegmentUrlDao))
            throw new IllegalStateException("not a subclass: " + dao.getClass());
        else
            return (SegmentUrlDao)dao;
    }

    /**
     * @deprecated Method findRowsBySegmentId is deprecated
     */

    public static List findRowsBySegmentId(SegmentUrlDao dao)
        throws DataNetworkException, DataQueryException
    {
        return findRowsBySegmentId(dao.getSegmentUrlRow());
    }

    public static List findRowsBySegmentId(SegmentUrlRow row)
        throws DataNetworkException, DataQueryException
    {
        return findRowsBySegmentId(row.getSegmentId());
    }

    public static List findRowsBySegmentId(SegmentUrlPK pk)
        throws DataNetworkException, DataQueryException
    {
        return findRowsBySegmentId(pk.segment_id);
    }

    public static List findRowsBySegmentId(long p_segmentId)
        throws DataNetworkException, DataQueryException
    {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(AccountSegmentRow.TYPE, "segment_id=?", null, new Object[] {
            new Long(p_segmentId)
        });
    }

    /**
     * @deprecated Method findDaosBySegmentId is deprecated
     */

    public static List findDaosBySegmentId(SegmentUrlDao actor)
        throws DataNetworkException, DataQueryException
    {
        return DataAccessObject.forRows(findRowsBySegmentId(actor));
    }

    /**
     * @deprecated Method findDaosBySegmentId is deprecated
     */

    public static List findDaosBySegmentId(SegmentUrlRow row)
        throws DataNetworkException, DataQueryException
    {
        return DataAccessObject.forRows(findRowsBySegmentId(row));
    }

    /**
     * @deprecated Method findDaosBySegmentId is deprecated
     */

    public static List findDaosBySegmentId(SegmentUrlPK pk)
        throws DataNetworkException, DataQueryException
    {
        return DataAccessObject.forRows(findRowsBySegmentId(pk.segment_id));
    }

    /**
     * @deprecated Method findDaosBySegmentId is deprecated
     */

    public static List findDaosBySegmentId(long p_segmentId)
        throws DataNetworkException, DataQueryException
    {
        return DataAccessObject.forRows(findRowsBySegmentId(p_segmentId));
    }

    private AccountSegmentRow row;
    public static final com.fitechlabs.xtrade.kernel.data.DataAccessObject.Factory FACTORY = new com.fitechlabs.xtrade.kernel.data.DataAccessObject.Factory() {

        public DataAccessObject newInstance(Row row)
        {
            if(row instanceof AccountSegmentRow)
                return new AccountSegmentDao((AccountSegmentRow)row);
            else
                throw new IllegalArgumentException("Not a AccountSegmentRow or subclass: " + row.getClass());
        }

    }
;

}
