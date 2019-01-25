// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ErrorMessageDao.java

package com.fitechlabs.xtrade.kernel.data.db;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.data.*;
import java.util.List;

// Referenced classes of package com.fitechlabs.xtrade.kernel.data.db:
//            ErrorMessagePK, ErrorMessageRow

public class ErrorMessageDao extends DataAccessObject
{

    protected ErrorMessageDao(ErrorMessageRow row)
    {
        super(row);
        this.row = row;
    }

    public ErrorMessageRow getErrorMessageRow()
    {
        return row;
    }

    public static ErrorMessageDao forRow(ErrorMessageRow row)
        throws IllegalArgumentException
    {
        return (ErrorMessageDao)DataAccessObject.forRow(row);
    }

    public static long newPkValue()
        throws DataNetworkException, DataQueryException
    {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery(ErrorMessageRow.TYPE);
    }

    public static ErrorMessagePK newPkObject()
        throws DataNetworkException, DataQueryException
    {
        long id = newPkValue();
        return new ErrorMessagePK(id);
    }

    public static ErrorMessageRow findRowByPk(long p_errorMessageId)
        throws DataFindException, DataQueryException, DataNetworkException
    {
        ErrorMessagePK pk = new ErrorMessagePK(p_errorMessageId);
        return findRowByPk(pk);
    }

    public static ErrorMessageRow findRowByPk(ErrorMessagePK pk)
        throws DataFindException, DataQueryException, DataNetworkException
    {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (ErrorMessageRow)qp.doFindByPrimaryKeyQuery(pk, null);
    }

    /**
     * @deprecated Method findDaoByPk is deprecated
     */

    public static ErrorMessageDao findDaoByPk(long p_errorMessageId)
        throws DataFindException, DataQueryException, DataNetworkException
    {
        ErrorMessagePK pk = new ErrorMessagePK(p_errorMessageId);
        ErrorMessageRow row = findRowByPk(pk);
        return forRow(row);
    }

    /**
     * @deprecated Method findDaoByPk is deprecated
     */

    public static ErrorMessageDao findDaoByPk(ErrorMessagePK pk)
        throws DataFindException, DataQueryException, DataNetworkException
    {
        ErrorMessageRow row = findRowByPk(pk);
        return forRow(row);
    }

    public static ErrorMessageRow findRowByErrorClassErrorCode(String p_errorClass, String p_errorCode)
        throws DataNetworkException, DataFindException, DataQueryException
    {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(ErrorMessageRow.TYPE, "error_class=? and error_code=?", null, new Object[] {
            p_errorClass, p_errorCode
        });
        switch(list.size())
        {
        case 0: // '\0'
            return null;

        case 1: // '\001'
            return (ErrorMessageRow)list.get(0);
        }
        throw new DataFindException("too many results in unique query ErrorMessageDao.findRowsByErrorClassErrorCode(): " + list.size());
    }

    /**
     * @deprecated Method findDaoByErrorClassErrorCode is deprecated
     */

    public static ErrorMessageDao findDaoByErrorClassErrorCode(String p_errorClass, String p_errorCode)
        throws DataNetworkException, DataFindException, DataQueryException
    {
        return forRow(findRowByErrorClassErrorCode(p_errorClass, p_errorCode));
    }

    public static ErrorMessageRow findRowByErrorClassErrorTag(String p_errorClass, String p_errorTag)
        throws DataNetworkException, DataFindException, DataQueryException
    {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(ErrorMessageRow.TYPE, "error_class=? and error_tag=?", null, new Object[] {
            p_errorClass, p_errorTag
        });
        switch(list.size())
        {
        case 0: // '\0'
            return null;

        case 1: // '\001'
            return (ErrorMessageRow)list.get(0);
        }
        throw new DataFindException("too many results in unique query ErrorMessageDao.findRowsByErrorClassErrorTag(): " + list.size());
    }

    /**
     * @deprecated Method findDaoByErrorClassErrorTag is deprecated
     */

    public static ErrorMessageDao findDaoByErrorClassErrorTag(String p_errorClass, String p_errorTag)
        throws DataNetworkException, DataFindException, DataQueryException
    {
        return forRow(findRowByErrorClassErrorTag(p_errorClass, p_errorTag));
    }

    public static List findRowsByErrorClassInitialText(String p_errorClass, String p_initialText)
        throws DataNetworkException, DataQueryException
    {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(ErrorMessageRow.TYPE, "error_class=? and initial_text=?", null, new Object[] {
            p_errorClass, p_initialText
        });
    }

    /**
     * @deprecated Method findDaosByErrorClassInitialText is deprecated
     */

    public static List findDaosByErrorClassInitialText(String p_errorClass, String p_initialText)
        throws DataNetworkException, DataQueryException
    {
        return DataAccessObject.forRows(findRowsByErrorClassInitialText(p_errorClass, p_initialText));
    }

    private ErrorMessageRow row;
    public static final com.fitechlabs.xtrade.kernel.data.DataAccessObject.Factory FACTORY = new com.fitechlabs.xtrade.kernel.data.DataAccessObject.Factory() {

        public DataAccessObject newInstance(Row row)
        {
            if(row instanceof ErrorMessageRow)
                return new ErrorMessageDao((ErrorMessageRow)row);
            else
                throw new IllegalArgumentException("Not a ErrorMessageRow or subclass: " + row.getClass());
        }

    }
;

}
