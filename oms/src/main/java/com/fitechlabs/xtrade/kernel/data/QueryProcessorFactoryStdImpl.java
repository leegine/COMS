// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   QueryProcessorFactoryStdImpl.java

package com.fitechlabs.xtrade.kernel.data;

import com.fitechlabs.dbind.Database;
import com.fitechlabs.dbind.impl.NonCachingDatabase;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import java.sql.SQLException;
import java.util.*;
import javax.sql.DataSource;

// Referenced classes of package com.fitechlabs.xtrade.kernel.data:
//            QueryProcessorStdImpl, QueryProcessorFactory, DataNetworkException, DataFindException, 
//            DataSources, QueryProcessor

public class QueryProcessorFactoryStdImpl
    implements QueryProcessorFactory
{

    public QueryProcessorFactoryStdImpl(Properties properties, String namespace)
        throws SQLException
    {
        tableInfos = new HashMap();
        DataSource datasource = DataSources.getDataSource(properties);
        log.info("QueryProcessor " + namespace + " use dataSource=" + datasource);
        this.namespace = namespace;
        database = createDatabase(datasource);
        processor = createQueryProcessor(database);
    }

    protected Database createDatabase(DataSource datasource)
    {
        return new NonCachingDatabase(datasource);
    }

    protected QueryProcessor createQueryProcessor(Database database)
    {
        return new QueryProcessorStdImpl(database);
    }

    public void extendInstance(String tableName, Class pkClass, Class rowClass, String superTable, String subclassField)
    {
        database.createTable(tableName, pkClass, rowClass, superTable, subclassField);
    }

    public QueryProcessor getQueryProcessor(Long argument)
        throws DataNetworkException, DataFindException
    {
        return processor;
    }

    public QueryProcessor getFinderProcessor()
        throws DataNetworkException
    {
        return processor;
    }

    public QueryProcessor getMutatorProcessor()
    {
        return processor;
    }

    static Class _mthclass$(String x0)
    {
        return Class.forName(x0);
        ClassNotFoundException x1;
        x1;
        throw new NoClassDefFoundError(x1.getMessage());
    }

    private static final Logit log;
    private static final boolean DBG;
    private Map tableInfos;
    protected Database database;
    protected QueryProcessor processor;
    protected final String namespace;

    static 
    {
        log = Logit.getInstance(com.fitechlabs.xtrade.kernel.data.QueryProcessorFactoryStdImpl.class);
        DBG = log.ison();
    }
}
