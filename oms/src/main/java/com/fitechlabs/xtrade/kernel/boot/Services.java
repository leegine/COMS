// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Services.java

package com.fitechlabs.xtrade.kernel.boot;

import com.fitechlabs.dbind.Enum;
import com.fitechlabs.xtrade.kernel.interceptor.*;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.fitechlabs.xtrade.kernel.boot:
//            Service

public final class Services
{

    public Services()
    {
    }

    public static void registerService(Class serviceInterface, Service service)
    {
        if(serviceInterface == null)
            throw new IllegalArgumentException("Interface cannot be null.");
        if(service == null)
            throw new IllegalArgumentException("Implemenation cannot be null.");
        if(!(com.fitechlabs.xtrade.kernel.boot.Service.class).isAssignableFrom(serviceInterface))
            throw new IllegalArgumentException("Not a Service interface: " + serviceInterface);
        if(!serviceInterface.isAssignableFrom(service.getClass()))
            throw new IllegalArgumentException("Implementation " + service + " does not extend service interface " + serviceInterface);
        synchronized(service2Impl)
        {
            if(service2Impl.containsKey(serviceInterface))
                throw new IllegalStateException("Service " + serviceInterface.getName() + " already implemented by " + service);
            wrapAndSetService(serviceInterface, service);
        }
    }

    private static void wrapAndSetService(Class serviceInterface, Service service)
    {
        if(!(com.fitechlabs.xtrade.kernel.boot.Service.class).isAssignableFrom(service.getClass()))
        {
            throw new IllegalArgumentException("Not a Service: " + serviceInterface);
        } else
        {
            Service wrappedService = (Service)InterceptableBuilder.buildInterceptable(serviceInterface, service);
            service2Impl.put(serviceInterface, wrappedService);
            return;
        }
    }

    public static void addInterceptor(Class serviceInterface, Interceptor interceptor)
    {
        Service service = getService(serviceInterface);
        Interceptable interceptable = (Interceptable)service;
        interceptable.addInterceptor(interceptor);
    }

    public static Service overrideService(Class serviceInterface, Service service)
    {
        if(serviceInterface == null || service == null || !serviceInterface.isAssignableFrom(service.getClass()))
            throw new IllegalArgumentException("Implementation " + service + " does not extend service interface " + serviceInterface);
        Map map = service2Impl;
        JVM INSTR monitorenter ;
        Object prior;
        if(!service2Impl.containsKey(serviceInterface))
            throw new IllegalStateException("Service " + serviceInterface.getName() + " is not registered, so cannot be overriden.");
        prior = service2Impl.get(serviceInterface);
        Service wrappedService = (Service)InterceptableBuilder.buildInterceptable(serviceInterface, service);
        service2Impl.put(serviceInterface, wrappedService);
        return (Service)prior;
        Exception exception;
        exception;
        throw exception;
    }

    public static boolean unregisterService(Class serviceInterface)
    {
        if(serviceInterface == null)
            throw new IllegalArgumentException("service interface cannot be null.");
        Map map = service2Impl;
        JVM INSTR monitorenter ;
        Object impl = service2Impl.remove(serviceInterface);
        return impl != null;
        Exception exception;
        exception;
        throw exception;
    }

    public static Service getService(Class serviceInterface)
    {
        Object impl = service2Impl.get(serviceInterface);
        if(impl == null)
            throw new IllegalArgumentException("Service not found: " + serviceInterface.getName());
        else
            return (Service)impl;
    }

    /**
     * @deprecated Method registerEnum is deprecated
     */

    public static void registerEnum(Enum type, Class serviceInterface)
    {
        synchronized(enum2Service)
        {
            enum2Service.put(type, serviceInterface);
        }
    }

    /**
     * @deprecated Method getServiceClass is deprecated
     */

    public static Class getServiceClass(Enum type)
    {
        Map map = enum2Service;
        JVM INSTR monitorenter ;
        return (Class)enum2Service.get(type);
        Exception exception;
        exception;
        throw exception;
    }

    private static Map service2Impl = new HashMap();
    private static Map enum2Service = new HashMap();

}
