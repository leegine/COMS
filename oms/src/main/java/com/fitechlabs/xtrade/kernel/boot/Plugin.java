// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Plugin.java

package com.fitechlabs.xtrade.kernel.boot;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.xtrade.kernel.data.config.ServerConfig;
import com.fitechlabs.xtrade.kernel.handler.*;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import java.lang.reflect.*;
import java.util.*;

// Referenced classes of package com.fitechlabs.xtrade.kernel.boot:
//            MessageClassRegistry

public abstract class Plugin
{

    public Plugin()
    {
    }

    public static void plug()
        throws Exception
    {
        throw new Exception("Plugin: plug() must be implemented by the Plugin subclass.");
    }

    public static void onPlug()
        throws Exception
    {
        throw new Exception("Plugin: onPlug() must be implemented by the Plugin subclass.");
    }

    public static void plug(Class pluginClasses[])
        throws Exception
    {
        for(int i = 0; i < pluginClasses.length; i++)
            plug(pluginClasses[i]);

    }

    public static void unplugAll()
    {
        for(Iterator it = plugged.iterator(); it.hasNext();)
        {
            Class pluginClass = (Class)it.next();
            try
            {
                Method onPlug = pluginClass.getMethod("onUnplug", voidTypes);
                onPlug.invoke(pluginClass, voidValues);
            }
            catch(NoSuchMethodException nsme) { }
            catch(InvocationTargetException ite)
            {
                log.warn("unplugging " + pluginClass + ": " + ite.getTargetException());
            }
            catch(IllegalAccessException iae)
            {
                log.warn("unplugging " + pluginClass + ": " + iae);
            }
        }

    }

    public static void plug(Class pluginClass)
        throws Exception
    {
label0:
        {
            synchronized(plugged)
            {
                if(!plugged.contains(pluginClass))
                    break label0;
            }
            return;
        }
        plugged.add(pluginClass);
        set;
        JVM INSTR monitorexit ;
          goto _L1
        exception;
        throw exception;
_L1:
        if(!(com.fitechlabs.xtrade.kernel.boot.Plugin.class).isAssignableFrom(pluginClass))
            throw new Exception("Plugin: " + pluginClass + " is not a subclass of Plugin");
        try
        {
            Method onPlug = pluginClass.getMethod("onPlug", voidTypes);
            onPlug.invoke(pluginClass, voidValues);
        }
        catch(NoSuchMethodException nsme)
        {
            throw new Exception("Plugin: onPlug() could not find method 'onPlug()' for class " + pluginClass);
        }
        catch(InvocationTargetException ite)
        {
            ite.getTargetException().printStackTrace();
            throw new Exception("Plugin: onPlug(" + pluginClass + ") threw " + ite.getTargetException());
        }
        return;
    }

    public static Class[] getInstalledPluginClasses()
    {
        return (Class[])plugged.toArray(new Class[plugged.size()]);
    }

    protected static void regClass(Class javaClass)
        throws Exception
    {
        MessageClassRegistry.register(javaClass);
    }

    /**
     * @deprecated Method regClass is deprecated
     */

    protected static void regClass(Class javaClass, boolean reverse)
        throws Exception
    {
        MessageClassRegistry.register(javaClass);
    }

    protected static void regBaseClass(Class javaClass)
        throws Exception
    {
        MessageClassRegistry.registerBaseClass(javaClass);
    }

    /**
     * @deprecated Method regHandler is deprecated
     */

    protected static void regHandler(Class requestClass, Class handlerClass, String methodName)
        throws Exception
    {
        MessageHandlerDispatcher.registerHandler(requestClass, handlerClass, methodName, null);
    }

    /**
     * @deprecated Method regHandler is deprecated
     */

    protected static void regHandler(Class requestClass, Class handlerClass, String methodName, HandlerEventNotificationCallback callback)
        throws Exception
    {
        MessageHandlerDispatcher.registerHandler(requestClass, handlerClass, methodName, callback == null ? null : (new HandlerEventNotificationCallback[] {
            callback
        }));
    }

    /**
     * @deprecated Method regHandler is deprecated
     */

    protected static void regHandler(Class requestClass, Class handlerClass, String methodName, HandlerEventNotificationCallback callbacks[])
        throws Exception
    {
        MessageHandlerDispatcher.registerHandler(requestClass, handlerClass, methodName, callbacks == null || callbacks.length <= 0 ? null : callbacks);
    }

    protected static void unRegHandler(Class requestClass)
        throws MessageHandlerException
    {
        MessageHandlerDispatcher.unRegisterHandler(requestClass);
    }

    protected static void regHandler(Class pluginClass, Class requestClass, Class handlerClass, String methodName)
        throws Exception
    {
        HandlerEventNotificationCallback mergedCallbacks[] = getDefaultHandlerEventNotificationCallbacks(pluginClass);
        MessageHandlerDispatcher.registerHandler(requestClass, handlerClass, methodName, mergedCallbacks);
    }

    protected static void regHandler(Class pluginClass, Class requestClass, Class handlerClass, String methodName, HandlerEventNotificationCallback callback)
        throws Exception
    {
        HandlerEventNotificationCallback mergedCallbacks[] = merge(getDefaultHandlerEventNotificationCallbacks(pluginClass), callback == null ? null : (new HandlerEventNotificationCallback[] {
            callback
        }));
        MessageHandlerDispatcher.registerHandler(requestClass, handlerClass, methodName, mergedCallbacks);
    }

    private static HandlerEventNotificationCallback[] merge(HandlerEventNotificationCallback a1[], HandlerEventNotificationCallback a2[])
    {
        if(a1 == null || a1.length == 0)
            return a2;
        if(a2 == null || a2.length == 0)
        {
            return a1;
        } else
        {
            HandlerEventNotificationCallback mergedCallbacks[] = new HandlerEventNotificationCallback[a1.length + a2.length];
            System.arraycopy(a1, 0, mergedCallbacks, 0, a1.length);
            System.arraycopy(a2, 0, mergedCallbacks, a1.length, a2.length);
            return mergedCallbacks;
        }
    }

    protected static void regHandler(Class pluginClass, Class requestClass, Class handlerClass, String methodName, HandlerEventNotificationCallback callbacks[])
        throws Exception
    {
        HandlerEventNotificationCallback mergedCallbacks[] = merge(getDefaultHandlerEventNotificationCallbacks(pluginClass), callbacks);
        MessageHandlerDispatcher.registerHandler(requestClass, handlerClass, methodName, mergedCallbacks);
    }

    public static HandlerEventNotificationCallback[] defaultHandlerEventNotificationCallbacks()
    {
        return null;
    }

    private static HandlerEventNotificationCallback[] getDefaultHandlerEventNotificationCallbacks(Class pluginClass)
    {
        if(!(com.fitechlabs.xtrade.kernel.boot.Plugin.class).isAssignableFrom(pluginClass))
            throw new IllegalArgumentException(pluginClass.getName() + "is not a sub class of com.fitechlabs.xtrade.kernel.boot.Plugin.");
        Object callbacksHolder[] = (Object[])defaultCallbacksMap.get(pluginClass);
        if(callbacksHolder != null)
            return (HandlerEventNotificationCallback[])callbacksHolder[0];
        HandlerEventNotificationCallback callbacks[];
        Method method = pluginClass.getDeclaredMethod("defaultHandlerEventNotificationCallbacks", new Class[0]);
        if(!Modifier.isStatic(method.getModifiers()))
            throw new IllegalStateException("Method defaultHandlerEventNotificationCallbacks() is not static method.");
        if(!Modifier.isPublic(method.getModifiers()))
            throw new IllegalStateException("Method defaultHandlerEventNotificationCallbacks() is not public method.");
        callbacks = (HandlerEventNotificationCallback[])method.invoke(null, new Object[0]);
        defaultCallbacksMap.put(pluginClass, ((Object) (new Object[] {
            callbacks
        })));
        return callbacks;
        NoSuchMethodException e;
        e;
        return null;
        e;
        throw new IllegalStateException("Exception thrown when trying to get common HandlerEventNotificationCallbacks for " + pluginClass.getName() + ":" + e);
    }

    protected static void regProcessor(String processorName, Class processorClass, String propertiesFileName)
        throws Exception
    {
        Processors.setProcessorFactory(processorName, processorClass.getName(), propertiesFileName);
    }

    protected static void regProcessor(String processorName, Class defaultClass)
        throws Exception
    {
        Properties props = ServerConfig.getConfigCategory(processorName + ".properties");
        regProcessor(processorName, defaultClass, props);
    }

    protected static void regProcessor(String processorName, Class processorClass, Properties properties)
        throws Exception
    {
        String className = ServerConfig.getConfigValue(processorName + ".processor", "factory.class", processorClass.getName());
        Processors.setProcessorFactory(processorName, className, properties);
    }

    protected static void regDbExtension(String processorName, String tableName, Class pkClass, Class rowClass)
        throws Exception
    {
        Processors.getQueryProcessorFactory(processorName).extendInstance(tableName, pkClass, rowClass, null, null);
    }

    protected static void regDbExtension(String processorName, String tableName, Class pkClass, Class rowClass, String superTable, String subclassField)
        throws Exception
    {
        Processors.getQueryProcessorFactory(processorName).extendInstance(tableName, pkClass, rowClass, superTable, subclassField);
    }

    protected static void regDao(Class rowClass, com.fitechlabs.xtrade.kernel.data.DataAccessObject.Factory factory)
        throws Exception
    {
        DataAccessObject.registerClass(rowClass, factory);
    }

    private static final Logit log;
    private static final Set plugged = new HashSet();
    private static final Class voidTypes[] = new Class[0];
    private static final Object voidValues[] = new Object[0];
    private static Map defaultCallbacksMap = new HashMap();

    static 
    {
        log = Logit.getInstance(com.fitechlabs.xtrade.kernel.boot.Plugin.class);
    }
}
