// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   QueryProcessorFactoryCachingImpl.java

package com.fitechlabs.xtrade.kernel.data;

import com.fitechlabs.dbind.*;
import com.fitechlabs.dbind.impl.CachingDatabase;
import java.util.Properties;
import javax.sql.DataSource;

// Referenced classes of package com.fitechlabs.xtrade.kernel.data:
//            QueryProcessorFactoryStdImpl, QueryProcessor, QueryProcessorStdImpl, DataNetworkException

public class QueryProcessorFactoryCachingImpl extends QueryProcessorFactoryStdImpl
{
    private class QueryProcessorLockingImpl extends QueryProcessorStdImpl
    {

        protected Boolean doLockPartititionQueryImpl(boolean waitOnBusy)
            throws ConnectionException, SelectException
        {
            return cachingDatabase.lockPartition(waitOnBusy) ? Boolean.TRUE : Boolean.FALSE;
        }

        public void forceReadonlyMode(RowType rowType, boolean readonly)
            throws UnsupportedOperationException, DataNetworkException
        {
            Table table = database.getTable(rowType.getTableName());
            table.setReadonlyMode(readonly);
        }

        private QueryProcessorLockingImpl(CachingDatabase cachingDatabase)
        {
            super(cachingDatabase);
        }

    }


    public QueryProcessorFactoryCachingImpl(Properties properties, String namespace)
        throws Exception
    {
        super(properties, namespace);
    }

    protected Database createDatabase(DataSource datasource)
    {
        try
        {
            cachingDatabase = new CachingDatabase(datasource, true);
        }
        catch(DbException e)
        {
            throw new RuntimeException("failed to instantiate caching database: " + e);
        }
        return cachingDatabase;
    }

    protected QueryProcessor createQueryProcessor(Database database)
    {
        return new QueryProcessorLockingImpl(cachingDatabase);
    }

    private CachingDatabase cachingDatabase;

}
