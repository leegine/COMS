// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   QPFStdImpl.java

package com.fitechlabs.xtrade.kernel.data.impl;

import com.fitechlabs.dbind.Database;
import com.fitechlabs.dbind.impl.NonCachingDatabase;
import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import java.sql.SQLException;
import java.util.*;
import javax.sql.DataSource;

// Referenced classes of package com.fitechlabs.xtrade.kernel.data.impl:
//            QPStdImpl

public class QPFStdImpl
    implements QueryProcessorFactory
{

    public QPFStdImpl(Properties properties, String namespace)
        throws SQLException
    {
        tableInfos = new HashMap();
        DataSource dataSource = getInitDataSource(properties);
        log.info("QueryProcessor " + namespace + " use dataSource=" + dataSource);
        this.namespace = namespace;
        database = constructDatabase(dataSource);
        processor = constructQueryProcessor(database);
    }

    protected DataSource getInitDataSource(Properties properties)
        throws SQLException
    {
        return DataSources.getDataSource(properties);
    }

    protected Database constructDatabase(DataSource dataSource)
    {
        return new NonCachingDatabase(dataSource);
    }

    protected QueryProcessor constructQueryProcessor(Database database)
    {
        return new QPStdImpl(database);
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
    protected final Database database;
    protected final QueryProcessor processor;
    protected final String namespace;

    static 
    {
        log = Logit.getInstance(com.fitechlabs.xtrade.kernel.data.impl.QPFStdImpl.class);
        DBG = log.ison();
    }
}
