// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Processors.java

package com.fitechlabs.xtrade.kernel.data;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.util.PropertiesFinder;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

// Referenced classes of package com.fitechlabs.xtrade.kernel.data:
//            QueryProcessorFactory, DataNetworkException, DataException, DataFindException, 
//            DataQueryException, QueryProcessor, BatchedQuery

public class Processors
{
    private static class PkAllocator
    {

        private synchronized long alloc()
            throws DataNetworkException, DataQueryException
        {
            if(next >= realloc)
            {
                int allocCount = 1000;
                next = getSpan(allocCount);
                realloc = next + (long)allocCount;
            }
            return next++;
        }

        private long getSpan(long allocCount)
            throws DataNetworkException, DataQueryException
        {
            QueryProcessor qp = Processors.getProcessor("config");
            BatchedQuery query = BatchedQuery.createProcedureCallQuery("exec.serial_values", new Object[] {
                nameSpace, new Long(allocCount), null
            }, new Object[] {
                null, null, new Integer(-5)
            }, false);
            Object results[] = (Object[])qp.doQuery(2, query);
            Number d = (Number)results[2];
            return d.longValue();
        }

        private String nameSpace;
        private long next;
        private long realloc;


        private PkAllocator(String nameSpace)
        {
            this.nameSpace = nameSpace;
        }

    }


    public Processors()
    {
    }

    public static void setProcessorFactory(String processorName, String factoryClassName, String propertyFileName)
        throws DataException
    {
        setProcessorFactory(processorName, factoryClassName, PropertiesFinder.getProperties(propertyFileName, null));
    }

    public static void setProcessorFactory(String processorName, String factoryClassName, Properties properties)
        throws DataException
    {
        try
        {
            Class c = Class.forName(factoryClassName);
            Constructor m = c.getConstructor(new Class[] {
                java.util.Properties.class, java.lang.String.class
            });
            Object o = m.newInstance(new Object[] {
                properties, processorName
            });
            QueryProcessorFactory f = (QueryProcessorFactory)o;
            setProcessorFactory(processorName, f);
        }
        catch(InvocationTargetException ite)
        {
            throw new DataNetworkException("Adding processor '" + processorName + "' threw " + ite.getTargetException());
        }
        catch(Exception e)
        {
            throw new DataNetworkException("Adding processor '" + processorName + "' threw " + e);
        }
    }

    public static void setProcessorFactory(String processorName, QueryProcessorFactory factoryInstance)
        throws IllegalStateException
    {
        if(processorFactories.containsKey(processorName))
        {
            throw new IllegalStateException("Processor with name '" + processorName + "' has already been registered.");
        } else
        {
            log.info("'" + processorName + "' factory set to " + factoryInstance.getClass().getName());
            processorFactories.put(processorName, factoryInstance);
            accountFactory = null;
            sessionFactory = null;
            defaultFactory = null;
            masterFactory = null;
            return;
        }
    }

    public static QueryProcessorFactory replaceProcessorFactory(String processorName, QueryProcessorFactory newFactoryInstance)
        throws IllegalStateException
    {
        QueryProcessorFactory oldInstance = (QueryProcessorFactory)processorFactories.get(processorName);
        if(oldInstance == null)
        {
            throw new IllegalStateException("Processor with name '" + processorName + "' has not been registered previously.");
        } else
        {
            log.info("'" + processorName + "' factory replaced with " + newFactoryInstance.getClass().getName());
            processorFactories.put(processorName, newFactoryInstance);
            accountFactory = null;
            sessionFactory = null;
            defaultFactory = null;
            masterFactory = null;
            return oldInstance;
        }
    }

    public static QueryProcessorFactory getQueryProcessorFactory(String processorName)
    {
        return (QueryProcessorFactory)processorFactories.get(processorName);
    }

    /**
     * @deprecated Method getProcessor is deprecated
     */

    public static QueryProcessor getProcessor(String processor_name)
        throws DataNetworkException, DataFindException
    {
        QueryProcessorFactory f = getQueryProcessorFactory(processor_name);
        if(f == null)
            return null;
        else
            return f.getQueryProcessor(null);
    }

    /**
     * @deprecated Method getProcessor is deprecated
     */

    public static QueryProcessor getProcessor(String processor_name, long id)
        throws DataNetworkException, DataFindException
    {
        QueryProcessorFactory f = getQueryProcessorFactory(processor_name);
        if(f == null)
            return null;
        else
            return f.getQueryProcessor(new Long(id));
    }

    /**
     * @deprecated Method getSessionProcessor is deprecated
     */

    public static QueryProcessor getSessionProcessor()
        throws DataNetworkException, DataFindException
    {
        QueryProcessorFactory f = sessionFactory;
        if(f == null)
        {
            sessionFactory = f = (QueryProcessorFactory)processorFactories.get("session");
            if(f == null)
                return null;
        }
        return f.getQueryProcessor(null);
    }

    /**
     * @deprecated Method getMasterProcessor is deprecated
     */

    public static QueryProcessor getMasterProcessor()
        throws DataNetworkException, DataFindException
    {
        QueryProcessorFactory f = masterFactory;
        if(f == null)
        {
            masterFactory = f = (QueryProcessorFactory)processorFactories.get("master");
            if(f == null)
                return null;
        }
        return f.getQueryProcessor(null);
    }

    public static QueryProcessor getAccountProcessor()
        throws DataNetworkException, DataFindException
    {
        QueryProcessorFactory f = accountFactory;
        if(f == null)
        {
            accountFactory = f = (QueryProcessorFactory)processorFactories.get("account");
            if(f == null)
                return null;
        }
        return f.getQueryProcessor(null);
    }

    /**
     * @deprecated Method getAccountProcessor is deprecated
     */

    public static QueryProcessor getAccountProcessor(long account_id)
        throws DataNetworkException, DataFindException
    {
        QueryProcessorFactory f = accountFactory;
        if(f == null)
        {
            accountFactory = f = (QueryProcessorFactory)processorFactories.get("account");
            if(f == null)
                return null;
        }
        return f.getQueryProcessor(new Long(account_id));
    }

    public static QueryProcessor getDefaultProcessor()
        throws DataNetworkException, DataFindException
    {
        QueryProcessorFactory f = defaultFactory;
        if(f == null)
        {
            defaultFactory = f = (QueryProcessorFactory)processorFactories.get("default");
            if(f == null)
                return null;
        }
        return f.getQueryProcessor(null);
    }

    public static long getNewPkValue()
        throws DataNetworkException, DataQueryException
    {
        return getNewPkValue("unnamed");
    }

    public static long getNewPkValue(String nameSpace)
        throws DataNetworkException, DataQueryException
    {
        PkAllocator pka = getOrCreateAllocator(nameSpace);
        return pka.alloc();
    }

    private static PkAllocator getOrCreateAllocator(String nameSpace)
    {
        PkAllocator pka = (PkAllocator)pkAllocators.get(nameSpace);
        if(pka == null)
            synchronized(com.fitechlabs.xtrade.kernel.data.Processors.class)
            {
                pka = (PkAllocator)pkAllocators.get(nameSpace);
                if(pka == null)
                    pkAllocators.put(nameSpace, pka = new PkAllocator(nameSpace));
            }
        return pka;
    }

    static final void main(String args[])
        throws Exception
    {
        System.setProperty("DEFAULT_DRIVER", "oracle.jdbc.driver.OracleDriver");
        System.setProperty("DEFAULT_URL", "jdbc:oracle:thin:ebankJim/ebankJim@localhost:1521:fiveutf8");
        KernelPlugin.plug();
        for(int i = 0; i < 10; i++)
            System.out.println("next pk value: " + getNewPkValue());

        speedtest();
        speedtest();
        speedtest();
        speedtest();
    }

    private static void speedtest()
        throws Exception
    {
        long end = System.currentTimeMillis() + 1000L;
        int count;
        for(count = 0; System.currentTimeMillis() < end; count++)
            getNewPkValue();

        System.out.println("speed: " + count + " selects per second");
    }

    private static final Logit log;
    private static final String DEFAULT_PK_NAME_SPACE = "unnamed";
    private static final int DEFAULT_PK_ALLOC_COUNT = 1000;
    private static Map processorFactories = new HashMap();
    private static QueryProcessorFactory accountFactory;
    private static QueryProcessorFactory sessionFactory;
    private static QueryProcessorFactory masterFactory;
    private static QueryProcessorFactory defaultFactory;
    private static Map pkAllocators = Collections.synchronizedMap(new HashMap());

    static 
    {
        log = Logit.getInstance(com.fitechlabs.xtrade.kernel.data.Processors.class);
    }
}
