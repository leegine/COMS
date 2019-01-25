// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ClientAccessorRegistry.java

package com.fitechlabs.xtrade.kernel.handler.client;

import java.util.*;

// Referenced classes of package com.fitechlabs.xtrade.kernel.handler.client:
//            ServerAccessorFactory

public final class ClientAccessorRegistry
{

    private ClientAccessorRegistry()
    {
    }

    public static void registerClientAccessor(String name, ServerAccessorFactory factory)
    {
        synchronized(factories)
        {
            if(factories.containsKey(name))
                throw new RuntimeException("ClientAccessorRegistry name " + name + " already registered using " + factories.get(name));
            factories.put(name, factory);
        }
    }

    static ServerAccessorFactory getAccessorFactory(String name)
    {
        Map map = factories;
        JVM INSTR monitorenter ;
        return (ServerAccessorFactory)factories.get(name);
        Exception exception;
        exception;
        throw exception;
    }

    private static Map factories = Collections.synchronizedMap(new HashMap());

}
