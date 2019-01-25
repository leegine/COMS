// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ServerLicenseDao.java

package com.fitechlabs.xtrade.kernel.data.db;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.data.*;

// Referenced classes of package com.fitechlabs.xtrade.kernel.data.db:
//            ServerLicensePK, ServerLicenseRow

public class ServerLicenseDao extends DataAccessObject
{

    protected ServerLicenseDao(ServerLicenseRow row)
    {
        super(row);
        this.row = row;
    }

    public ServerLicenseRow getServerLicenseRow()
    {
        return row;
    }

    public static ServerLicenseDao forRow(ServerLicenseRow row)
        throws IllegalArgumentException
    {
        return (ServerLicenseDao)DataAccessObject.forRow(row);
    }

    public static long newPkValue()
        throws DataNetworkException, DataQueryException
    {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery(ServerLicenseRow.TYPE);
    }

    public static ServerLicensePK newPkObject()
        throws DataNetworkException, DataQueryException
    {
        throw new UnsupportedOperationException("auto-generation of primary keys with non-long value components not supported.");
    }

    public static ServerLicenseRow findRowByPk(String p_licenseId)
        throws DataFindException, DataQueryException, DataNetworkException
    {
        ServerLicensePK pk = new ServerLicensePK(p_licenseId);
        return findRowByPk(pk);
    }

    public static ServerLicenseRow findRowByPk(ServerLicensePK pk)
        throws DataFindException, DataQueryException, DataNetworkException
    {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (ServerLicenseRow)qp.doFindByPrimaryKeyQuery(pk, null);
    }

    /**
     * @deprecated Method findDaoByPk is deprecated
     */

    public static ServerLicenseDao findDaoByPk(String p_licenseId)
        throws DataFindException, DataQueryException, DataNetworkException
    {
        ServerLicensePK pk = new ServerLicensePK(p_licenseId);
        ServerLicenseRow row = findRowByPk(pk);
        return forRow(row);
    }

    /**
     * @deprecated Method findDaoByPk is deprecated
     */

    public static ServerLicenseDao findDaoByPk(ServerLicensePK pk)
        throws DataFindException, DataQueryException, DataNetworkException
    {
        ServerLicenseRow row = findRowByPk(pk);
        return forRow(row);
    }

    private ServerLicenseRow row;
    public static final com.fitechlabs.xtrade.kernel.data.DataAccessObject.Factory FACTORY = new com.fitechlabs.xtrade.kernel.data.DataAccessObject.Factory() {

        public DataAccessObject newInstance(Row row)
        {
            if(row instanceof ServerLicenseRow)
                return new ServerLicenseDao((ServerLicenseRow)row);
            else
                throw new IllegalArgumentException("Not a ServerLicenseRow or subclass: " + row.getClass());
        }

    }
;

}
