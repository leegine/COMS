// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   QPFCachingImpl.java

package com.fitechlabs.xtrade.kernel.data.impl;

import com.fitechlabs.dbind.*;
import com.fitechlabs.dbind.impl.CachingDatabase;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import java.sql.SQLException;
import java.util.Properties;
import javax.sql.DataSource;

// Referenced classes of package com.fitechlabs.xtrade.kernel.data.impl:
//            QPFStdImpl, QPStdImpl

public class QPFCachingImpl extends QPFStdImpl
{
    private static class QPLockingImpl extends QPStdImpl
    {

        protected boolean doLockPartititionQueryImpl(boolean waitOnBusy)
            throws ConnectionException, SelectException
        {
            CachingDatabase cdb = (CachingDatabase)super.database;
            return cdb.lockPartition(waitOnBusy);
        }

        public void forceReadonlyMode(RowType rowType, boolean readonly)
            throws UnsupportedOperationException, DataNetworkException
        {
            Table table = super.database.getTable(rowType.getTableName());
            table.setReadonlyMode(readonly);
        }

        private CachingDatabase cachingDatabase;

        private QPLockingImpl(CachingDatabase cachingDatabase)
        {
            super(cachingDatabase);
            this.cachingDatabase = cachingDatabase;
        }

    }


    public QPFCachingImpl(Properties properties, String namespace)
        throws SQLException
    {
        super(properties, namespace);
    }

    protected Database constructDatabase(DataSource dataSource)
    {
        return new CachingDatabase(dataSource, true);
        DbException e;
        e;
        throw new RuntimeException("unable to construct caching database around " + dataSource + ", excep=" + e);
    }

    protected QueryProcessor constructQueryProcessor(Database database)
    {
        if(!(database instanceof CachingDatabase))
            throw new IllegalArgumentException("not a CachingDatabase: " + database);
        else
            return new QPLockingImpl((CachingDatabase)database);
    }
}
