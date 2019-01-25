// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MessageHandlersParamsContext.java

package com.fitechlabs.xtrade.kernel.handler;

import java.util.LinkedList;
import java.util.List;

public class MessageHandlersParamsContext
{

    public MessageHandlersParamsContext()
    {
    }

    public static Object[] getMessageHandlerArgs()
    {
        List handleArgsList = (List)paramsContext.get();
        if(handleArgsList.size() == 0)
            return null;
        else
            return (Object[])handleArgsList.get(0);
    }

    public static void setMessageHandlerArgs(Object args[])
    {
        List handleArgsList = (List)paramsContext.get();
        if(args == null)
            handleArgsList.remove(0);
        else
            handleArgsList.add(0, ((Object) (args)));
    }

    private static ThreadLocal paramsContext = new ThreadLocal() {

        protected Object initialValue()
        {
            return new LinkedList();
        }

    }
;

}
