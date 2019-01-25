// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   QueryProcessorFactoryBeanImpl.java

package com.fitechlabs.xtrade.kernel.data;

import com.fitechlabs.dbind.*;
import com.fitechlabs.dbind.impl.*;
import com.fitechlabs.xtrade.kernel.handler.MessageHandlerDispatcher;
import com.fitechlabs.xtrade.kernel.handler.MessageHandlingContext;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import java.util.*;

// Referenced classes of package com.fitechlabs.xtrade.kernel.data:
//            QueryProcessorFactoryStdImpl, DataNetworkException, DataFindException, QueryProcessor, 
//            QueryProcessorStdImpl, QueryProcessorBatchingImpl, DataBatchException, DataRollbackException, 
//            DataQueryException, DataCallbackException, BatchedQuery, TransactionCallback

public class QueryProcessorFactoryBeanImpl extends QueryProcessorFactoryStdImpl
{
    protected static class BeanImpl extends QueryProcessorStdImpl
    {

        private void setId(Long newId)
        {
            nextId = newId;
        }

        protected Long getId()
        {
            return id;
        }

        protected void establishId()
            throws DbException
        {
            Long nid;
            nid = nextId;
            if(nid.equals(id))
                break MISSING_BLOCK_LABEL_79;
            BeanImpl beanimpl = this;
            JVM INSTR monitorenter ;
            if(nid.equals(id))
                break MISSING_BLOCK_LABEL_67;
            cachingDatabase.setId(nid);
            id = cachingDatabase.getId();
            break MISSING_BLOCK_LABEL_67;
            Exception exception;
            exception;
            id = cachingDatabase.getId();
            throw exception;
            break MISSING_BLOCK_LABEL_79;
            Exception exception1;
            exception1;
            throw exception1;
        }

        protected Boolean doLockPartititionQueryImpl(boolean waitOnBusy)
            throws ConnectionException, SelectException
        {
            return cachingDatabase.lockPartition(waitOnBusy) ? Boolean.TRUE : Boolean.FALSE;
        }

        private Long id;
        private Long nextId;
        private int users;
        private CachingDatabase cachingDatabase;






        protected BeanImpl(Database nonCachingDatabase)
            throws Exception
        {
            this(new CachingDatabase(nonCachingDatabase, QueryProcessorFactoryBeanImpl.NULL_INITIAL_ID));
        }

        protected BeanImpl(CachingDatabase cachingDb)
            throws Exception
        {
            super(cachingDb);
            cachingDatabase = cachingDb;
        }
    }

    private class BeanHandle extends QueryProcessorBatchingImpl
    {

        public Object[] doQueries(int transactionSemantics, BatchedQuery queries[])
            throws DataNetworkException, DataBatchException, DataRollbackException
        {
            BeanImpl bean = manager.getBeanImpl(id);
            Object aobj[];
            bean.establishId();
            aobj = bean.doQueries(transactionSemantics, queries);
            manager.releaseBeanImpl(bean);
            return aobj;
            Exception exception;
            exception;
            manager.releaseBeanImpl(bean);
            throw exception;
            DbException dbe;
            dbe;
            throw new DataNetworkException(dbe.getMessage());
            InterruptedException ie;
            ie;
            throw new DataNetworkException(ie.getMessage());
        }

        public Object doQuery(int transactionSemantics, BatchedQuery query)
            throws DataNetworkException, DataQueryException, DataRollbackException
        {
            BeanImpl bean = manager.getBeanImpl(id);
            Object obj;
            bean.establishId();
            obj = bean.doQuery(transactionSemantics, query);
            manager.releaseBeanImpl(bean);
            return obj;
            Exception exception;
            exception;
            manager.releaseBeanImpl(bean);
            throw exception;
            DbException dbe;
            dbe;
            throw new DataNetworkException(dbe.getMessage());
            InterruptedException ie;
            ie;
            throw new DataNetworkException(ie.getMessage());
        }

        public Object doTransaction(int transactionSemantics, TransactionCallback callback)
            throws DataNetworkException, DataQueryException, DataCallbackException, DataRollbackException
        {
            BeanImpl bean = manager.getBeanImpl(id);
            Object obj;
            bean.establishId();
            obj = bean.doTransaction(transactionSemantics, callback);
            manager.releaseBeanImpl(bean);
            return obj;
            Exception exception;
            exception;
            manager.releaseBeanImpl(bean);
            throw exception;
            DbException dbe;
            dbe;
            throw new DataNetworkException(dbe.getMessage());
            InterruptedException ie;
            ie;
            throw new DataNetworkException(ie.getMessage());
        }

        private Long id;

        private BeanHandle(Long id)
        {
            super(database);
            this.id = id;
        }

    }

    private class BeanMgr
    {

        private synchronized BeanImpl getBeanImpl(Long id)
            throws InterruptedException
        {
            boolean stats = CacheStatistics.isCollecting();
            long start = stats ? System.currentTimeMillis() : 0L;
            BeanImpl b = (BeanImpl)idmap.get(id);
            if(b != null)
            {
                if(b.users++ == 0)
                {
                    freelist.remove(b);
                    if(stats)
                        CacheStatistics.addHit(namespace, "beans", "(beans)", System.currentTimeMillis() - start, null);
                } else
                if(stats)
                    CacheStatistics.addHit(namespace, "beans", "(beans)", System.currentTimeMillis() - start, "in use");
                return b;
            }
            for(; freelist.isEmpty(); wait());
            b = (BeanImpl)freelist.removeOldest();
            idmap.remove(b.id);
            b.users = 1;
            b.setId(id);
            idmap.put(id, b);
            if(stats)
            {
                CacheStatistics.addMiss(namespace, "beans", "(beans)", System.currentTimeMillis() - start, null);
                CacheStatistics.addSizeChange(namespace, "beans", idmap.size(), beanCount);
            }
            return b;
        }

        private synchronized void releaseBeanImpl(BeanImpl b)
        {
            if(--b.users > 0)
            {
                return;
            } else
            {
                freelist.add(b);
                notify();
                return;
            }
        }

        private final LRUSet freelist = new LRUSet();
        private final Map idmap = new HashMap();
        private final int beanCount;



        BeanMgr(int beanCount)
            throws Exception
        {
            this.beanCount = beanCount;
            for(int i = 0; i < beanCount; i++)
                freelist.add(createBeanImpl());

        }
    }


    public QueryProcessorFactoryBeanImpl(Properties properties, String namespace)
        throws Exception
    {
        this(properties, namespace, CacheSizes.getActualBeanCount(namespace));
    }

    private QueryProcessorFactoryBeanImpl(Properties properties, String namespace, int beanCount)
        throws Exception
    {
        super(properties, namespace);
        manager = new BeanMgr(beanCount);
    }

    public QueryProcessor getQueryProcessor(Long idObject)
        throws DataNetworkException, DataFindException
    {
        if(idObject == null || idObject.longValue() == 0L)
            return super.getQueryProcessor(null);
        else
            return new BeanHandle(idObject);
    }

    public QueryProcessor getFinderProcessor()
        throws DataNetworkException
    {
        long account_id = MessageHandlerDispatcher.getHandlingContext().getAccountId();
        return getQueryProcessor(new Long(account_id));
        Exception e;
        e;
        throw new DataNetworkException("failed to get processor via id, " + e);
    }

    protected BeanImpl createBeanImpl()
        throws Exception
    {
        return new BeanImpl(super.database);
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
    protected static final Long NULL_INITIAL_ID = null;
    private BeanMgr manager;

    static 
    {
        log = Logit.getInstance(com.fitechlabs.xtrade.kernel.data.QueryProcessorFactoryBeanImpl.class);
        DBG = log.ison();
    }

}
