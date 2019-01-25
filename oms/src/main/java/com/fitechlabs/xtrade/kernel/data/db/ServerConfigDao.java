// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ServerConfigDao.java

package com.fitechlabs.xtrade.kernel.data.db;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.data.*;

// Referenced classes of package com.fitechlabs.xtrade.kernel.data.db:
//            ServerConfigPK, ServerConfigRow

public class ServerConfigDao extends DataAccessObject
{

    protected ServerConfigDao(ServerConfigRow row)
    {
        super(row);
        this.row = row;
    }

    public ServerConfigRow getServerConfigRow()
    {
        return row;
    }

    public static ServerConfigDao forRow(ServerConfigRow row)
        throws IllegalArgumentException
    {
        return (ServerConfigDao)DataAccessObject.forRow(row);
    }

    public static long newPkValue()
        throws DataNetworkException, DataQueryException
    {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery(ServerConfigRow.TYPE);
    }

    public static ServerConfigPK newPkObject()
        throws DataNetworkException, DataQueryException
    {
        throw new UnsupportedOperationException("auto-generation of primary keys with multiple components not supported.");
    }

    public static ServerConfigRow findRowByPk(String p_configTitle, String p_configCateg, String p_configName)
        throws DataFindException, DataQueryException, DataNetworkException
    {
        ServerConfigPK pk = new ServerConfigPK(p_configTitle, p_configCateg, p_configName);
        return findRowByPk(pk);
    }

    public static ServerConfigRow findRowByPk(ServerConfigPK pk)
        throws DataFindException, DataQueryException, DataNetworkException
    {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (ServerConfigRow)qp.doFindByPrimaryKeyQuery(pk, null);
    }

    /**
     * @deprecated Method findDaoByPk is deprecated
     */

    public static ServerConfigDao findDaoByPk(String p_configTitle, String p_configCateg, String p_configName)
        throws DataFindException, DataQueryException, DataNetworkException
    {
        ServerConfigPK pk = new ServerConfigPK(p_configTitle, p_configCateg, p_configName);
        ServerConfigRow row = findRowByPk(pk);
        return forRow(row);
    }

    /**
     * @deprecated Method findDaoByPk is deprecated
     */

    public static ServerConfigDao findDaoByPk(ServerConfigPK pk)
        throws DataFindException, DataQueryException, DataNetworkException
    {
        ServerConfigRow row = findRowByPk(pk);
        return forRow(row);
    }

    private ServerConfigRow row;
    public static final com.fitechlabs.xtrade.kernel.data.DataAccessObject.Factory FACTORY = new com.fitechlabs.xtrade.kernel.data.DataAccessObject.Factory() {

        public DataAccessObject newInstance(Row row)
        {
            if(row instanceof ServerConfigRow)
                return new ServerConfigDao((ServerConfigRow)row);
            else
                throw new IllegalArgumentException("Not a ServerConfigRow or subclass: " + row.getClass());
        }

    }
;

}
