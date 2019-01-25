// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   QPFBeanImpl.java

package com.fitechlabs.xtrade.kernel.data.impl;

import com.fitechlabs.dbind.*;
import com.fitechlabs.dbind.impl.*;
import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.xtrade.kernel.handler.MessageHandlerDispatcher;
import com.fitechlabs.xtrade.kernel.handler.MessageHandlingContext;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import java.sql.SQLException;
import java.util.*;

// Referenced classes of package com.fitechlabs.xtrade.kernel.data.impl:
//            QPFStdImpl, QPStdImpl, QPBeanImpl

public class QPFBeanImpl extends QPFStdImpl
{
    private static class BeanImpl extends QPStdImpl
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
            cdb.setId(nid);
            id = cdb.getId();
            break MISSING_BLOCK_LABEL_67;
            Exception exception;
            exception;
            id = cdb.getId();
            throw exception;
            break MISSING_BLOCK_LABEL_79;
            Exception exception1;
            exception1;
            throw exception1;
        }

        protected boolean doLockPartititionQueryImpl(boolean waitOnBusy)
            throws ConnectionException, SelectException
        {
            return cdb.lockPartition(waitOnBusy);
        }

        private Long id;
        private Long nextId;
        private int users;
        private CachingDatabase cdb;






        public BeanImpl(Database mutatorDatabase)
            throws DbException
        {
            super(new CachingDatabase(mutatorDatabase));
            cdb = (CachingDatabase)super.database;
        }
    }

    private class BeanManager
    {

        private BeanImpl reserveFinder(Long id)
            throws DataNetworkException
        {
            boolean stats = CacheStatistics.isCollecting();
            long start = stats ? System.currentTimeMillis() : 0L;
            String info = null;
            boolean hit = false;
            BeanImpl b;
            try
            {
                synchronized(this)
                {
                    b = (BeanImpl)idmap.get(id);
                    if(b != null)
                    {
                        hit = true;
                        if(b.users++ == 0)
                            freelist.remove(b);
                        else
                            info = "in use";
                    } else
                    {
                        do
                        {
                            if(!freelist.isEmpty())
                                break;
                            try
                            {
                                wait();
                            }
                            catch(InterruptedException e)
                            {
                                info = "interrupted";
                                throw new DataNetworkException("interrupted while waiting for bean: " + e);
                            }
                        } while(true);
                        b = (BeanImpl)freelist.removeOldest();
                        idmap.remove(b.id);
                        b.users = 0;
                        b.setId(id);
                        b.users = 1;
                        idmap.put(id, b);
                        info = "from freelist";
                    }
                }
                try
                {
                    b.establishId();
                }
                catch(DbException e)
                {
                    throw new DataNetworkException("can't establish id: " + e.getMessage());
                }
            }
            finally
            {
                if(stats)
                {
                    if(hit)
                        CacheStatistics.addHit(namespace, "beans", "(beans)", System.currentTimeMillis() - start, info);
                    else
                        CacheStatistics.addMiss(namespace, "beans", "(beans)", System.currentTimeMillis() - start, info);
                    CacheStatistics.addSizeChange(namespace, "beans", idmap.size(), beanCount);
                }
            }
            return b;
        }

        protected void releaseFinder(BeanImpl b)
        {
label0:
            {
                synchronized(this)
                {
                    if(--b.users <= 0)
                        break label0;
                }
                return;
            }
            freelist.add(b);
            notify();
            beanmanager;
            JVM INSTR monitorexit ;
              goto _L1
            exception;
            throw exception;
_L1:
        }

        private final int beanCount;
        private final LRUSet freelist = new LRUSet();
        private final Map idmap = new HashMap();


        BeanManager(int beanCount)
            throws DbException
        {
            this.beanCount = beanCount;
            for(int i = 0; i < beanCount; i++)
            {
                BeanImpl impl = new BeanImpl(database);
                freelist.add(impl);
            }

        }
    }

    private class BeanHandle extends QPBeanImpl
    {

        protected QueryProcessor reserveFinder()
            throws DataNetworkException
        {
            return manager.reserveFinder(id);
        }

        protected void releaseFinder(QueryProcessor finder)
        {
            if(!(finder instanceof BeanImpl))
            {
                throw new IllegalArgumentException("not a bean impl: " + finder);
            } else
            {
                manager.releaseFinder((BeanImpl)finder);
                return;
            }
        }

        public Long getId()
        {
            return id;
        }

        private Long id;

        private BeanHandle(Database database, Long id)
        {
            super(database);
            this.id = id;
        }

    }


    public QPFBeanImpl(Properties properties, String namespace)
        throws SQLException, DbException
    {
        super(properties, namespace);
        init();
    }

    private void init()
        throws DbException
    {
        int beanCount = CacheSizes.getActualBeanCount(super.namespace);
        manager = new BeanManager(beanCount);
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

    public QueryProcessor getQueryProcessor(Long argument)
        throws DataNetworkException, DataFindException
    {
        if(argument == null || argument.longValue() == 0L)
            return super.processor;
        else
            return new BeanHandle(super.database, argument);
    }

    static Class _mthclass$(String x0)
    {
        return Class.forName(x0);
        ClassNotFoundException x1;
        x1;
        throw new NoClassDefFoundError(x1.getMessage());
    }

    private static Logit log;
    private BeanManager manager;

    static 
    {
        log = Logit.getInstance(com.fitechlabs.xtrade.kernel.data.impl.QPFBeanImpl.class);
    }

}
