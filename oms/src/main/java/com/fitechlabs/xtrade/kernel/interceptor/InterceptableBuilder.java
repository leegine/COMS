// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   InterceptableBuilder.java

package com.fitechlabs.xtrade.kernel.interceptor;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.fitechlabs.xtrade.kernel.interceptor:
//            Interceptable, Interceptor

public class InterceptableBuilder
{
    private static class Manager
        implements Interceptable
    {

        public void addInterceptor(Interceptor i)
        {
            if(i == null)
                throw new IllegalArgumentException("Interceptors can't be null.");
            List list = getOrCreateList();
            synchronized(list)
            {
                list.add(i);
                array = null;
            }
        }

        private List getOrCreateList()
        {
            if(list == null)
                synchronized(this)
                {
                    if(list == null)
                        list = new ArrayList();
                }
            return list;
        }

        private Interceptor[] getOrCreateArray()
        {
            Interceptor a[] = array;
            if(null != a)
                return a;
            List list1 = list;
            JVM INSTR monitorenter ;
            if(null == array)
                array = (Interceptor[])list.toArray(new Interceptor[list.size()]);
            return array;
            Exception exception;
            exception;
            throw exception;
        }

        private Object invoke(Method method, Object args[])
            throws Throwable
        {
            Interceptor interceptors[];
            int n;
            Object contexts[];
            int i;
            if(list == null)
                return InterceptableBuilder.invokeOn(_flddelegate, method, args);
            interceptors = getOrCreateArray();
            n = interceptors.length;
            contexts = new Object[n];
            i = n;
            Object result;
            while(--i >= 0) 
                contexts[i] = interceptors[i].onCall(method, args);
            result = InterceptableBuilder.invokeOn(_flddelegate, method, args);
            while(++i < n) 
                interceptors[i].onReturn(contexts[i], result);
            return result;
            Throwable rootCause;
            rootCause;
            Throwable latest = rootCause;
            while(++i < n) 
                try
                {
                    interceptors[i].onThrowable(contexts[i], latest);
                }
                catch(Throwable otherProblem)
                {
                    latest = otherProblem;
                }
            throw latest;
        }

        private Object _flddelegate;
        private List list;
        private Interceptor array[];


        private Manager(Object delegate)
        {
            list = null;
            array = null;
            _flddelegate = delegate;
        }

    }

    private static class InvocationHandlerImpl
        implements InvocationHandler
    {

        public Object invoke(Object proxy, Method method, Object args[])
            throws Throwable
        {
            if(method.getDeclaringClass() == (InterceptableBuilder.class$com$fitechlabs$xtrade$kernel$interceptor$Interceptable != null ? InterceptableBuilder.class$com$fitechlabs$xtrade$kernel$interceptor$Interceptable : (InterceptableBuilder.class$com$fitechlabs$xtrade$kernel$interceptor$Interceptable = InterceptableBuilder._mthclass$("com.fitechlabs.xtrade.kernel.interceptor.Interceptable"))))
                return InterceptableBuilder.invokeOn(manager, method, args);
            else
                return manager.invoke(method, args);
        }

        private Manager manager;

        private InvocationHandlerImpl(Manager manager)
        {
            this.manager = manager;
        }

    }


    public InterceptableBuilder()
    {
    }

    public static Interceptable buildInterceptable(Class clientInterface, Object implementation)
    {
        if(clientInterface == null || implementation == null)
            throw new IllegalArgumentException("interface and implementation must not be null.");
        if(!clientInterface.isAssignableFrom(implementation.getClass()))
        {
            throw new IllegalArgumentException("supplied implementation does not implement " + clientInterface.getName());
        } else
        {
            Manager manager = new Manager(implementation);
            InvocationHandler handler = new InvocationHandlerImpl(manager);
            Object proxy = Proxy.newProxyInstance(clientInterface.getClassLoader(), new Class[] {
                clientInterface, com.fitechlabs.xtrade.kernel.interceptor.Interceptable.class
            }, handler);
            return (Interceptable)proxy;
        }
    }

    private static final Object invokeOn(Object object, Method method, Object args[])
        throws Throwable
    {
        return method.invoke(object, args);
        InvocationTargetException ite;
        ite;
        throw ite.getTargetException();
    }

}
