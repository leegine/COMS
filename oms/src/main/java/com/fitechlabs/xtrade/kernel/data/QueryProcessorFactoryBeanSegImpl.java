// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   QueryProcessorFactoryBeanSegImpl.java

package com.fitechlabs.xtrade.kernel.data;

import com.fitechlabs.dbind.*;
import com.fitechlabs.dbind.impl.*;
import com.fitechlabs.xtrade.kernel.data.db.AccountSegmentPK;
import com.fitechlabs.xtrade.kernel.data.db.AccountSegmentRow;
import com.fitechlabs.xtrade.kernel.data.db.SegmentUrlPK;
import com.fitechlabs.xtrade.kernel.data.db.SegmentUrlRow;
import java.util.*;
import javax.sql.DataSource;

// Referenced classes of package com.fitechlabs.xtrade.kernel.data:
//            QueryProcessorFactoryBeanImpl, DataNetworkException, DataFindException, QueryProcessor, 
//            DataSources

public class QueryProcessorFactoryBeanSegImpl extends QueryProcessorFactoryBeanImpl
{
    private class BeanSegImpl extends QueryProcessorFactoryBeanImpl.BeanImpl
    {

        protected void establishId()
            throws DbException
        {
            Long id = getId();
            currentId.set(id);
            super.establishId();
            id = getId();
            currentId.set(id);
        }

        private BeanSegImpl(Database database)
            throws Exception
        {
            super(database);
        }

    }

    private class BeanAwareTypedSegmentedDatabase extends NonCachingDatabase
    {

        public DataSource getDataSource()
        {
            Long id = (Long)currentId.get();
            if(id == null)
                return super.getDataSource();
            javax.transaction.Transaction t = suspendTransaction();
            SegmentUrlRow row;
            try
            {
                AccountSegmentPK aspk = new AccountSegmentPK(id.longValue());
                AccountSegmentRow asrow = (AccountSegmentRow)accountSegmentTable.select(aspk);
                SegmentUrlPK supk = new SegmentUrlPK(asrow.getSegmentId());
                row = (SegmentUrlRow)segmentUrlTable.select(supk);
            }
            catch(FindException dqe)
            {
                throw new IllegalStateException("missing or bad segment info for account_id=" + id);
            }
            resumeTransaction(t);
            break MISSING_BLOCK_LABEL_141;
            Exception exception;
            exception;
            resumeTransaction(t);
            throw exception;
            String url = row.getDataSourceUrl();
            if(sources.containsKey(url))
                return (DataSource)sources.get(url);
            DataSource ds;
            Properties props = DataSources.getDefaultProperties();
            props.put("database.url", url);
            ds = DataSources.getDataSource(props);
            sources.put(url, ds);
            return ds;
            Exception e;
            e;
            throw new RuntimeException("failed to select segment url's: " + e);
        }

        private Map sources;

        private BeanAwareTypedSegmentedDatabase(DataSource masterSource)
        {
            super(masterSource);
            sources = new HashMap();
        }

    }


    public QueryProcessorFactoryBeanSegImpl(Properties props, String namespace)
        throws Exception
    {
        super(props, namespace);
        currentId = new ThreadLocal();
    }

    public QueryProcessor getQueryProcessor(Long l)
        throws DataNetworkException, DataFindException
    {
        if(l == null || l.longValue() == 0L)
            throw new RuntimeException("Processor 0 cannot be used with segmented databases");
        else
            return super.getQueryProcessor(l);
    }

    protected Database createTypedDatabase(DataSource datasource)
    {
        CachingDatabase segUrlCachingDb = new CachingDatabase(datasource);
        segmentUrlTable = segUrlCachingDb.createTable(SegmentUrlRow.TYPE.getTableName(), com.fitechlabs.xtrade.kernel.data.db.SegmentUrlPK.class, com.fitechlabs.xtrade.kernel.data.db.SegmentUrlParams.class);
        accountSegmentTable = segUrlCachingDb.createTable(AccountSegmentRow.TYPE.getTableName(), com.fitechlabs.xtrade.kernel.data.db.AccountSegmentPK.class, com.fitechlabs.xtrade.kernel.data.db.AccountSegmentParams.class);
        beanAwareDatabase = new BeanAwareTypedSegmentedDatabase(datasource);
        return beanAwareDatabase;
        Exception e;
        e;
        throw new RuntimeException("failed to instantiate factory: " + e);
    }

    protected QueryProcessorFactoryBeanImpl.BeanImpl createBeanImpl()
        throws Exception
    {
        QueryProcessorFactoryBeanImpl.BeanImpl bean = new BeanSegImpl(beanAwareDatabase);
        return bean;
    }

    private Table segmentUrlTable;
    private Table accountSegmentTable;
    private BeanAwareTypedSegmentedDatabase beanAwareDatabase;
    private ThreadLocal currentId;



}
