// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   InvRouter.java

package com.fitechlabs.dbind.impl;

import com.fitechlabs.xtrade.kernel.util.log.Logit;
import java.util.*;

public class InvRouter
{
    public static abstract class InvListener
    {

        protected abstract void onInvReceived(String s, String s1, String s2);

        protected void setId(Long id)
        {
            if(id == null && this.id == null)
                return;
            if(id != null && id.equals(this.id))
                return;
            if(list != null)
            {
                synchronized(list)
                {
                    list.remove(this);
                }
                list = null;
            }
            synchronized(InvRouter.map)
            {
                list = (List)InvRouter.map.get(id);
                if(list == null)
                {
                    list = new ArrayList(1);
                    InvRouter.map.put(id, list);
                }
            }
            synchronized(list)
            {
                list.add(this);
            }
            this.id = id;
        }

        private Long id;
        private List list;

        public InvListener()
        {
        }
    }


    private InvRouter()
    {
    }

    public static void onInvMessage(String message)
    {
        if(DBG)
            log.debug(">>> " + message + " <<<");
        StringTokenizer st = new StringTokenizer(message, ":");
        String name = st.nextToken();
        String op = st.nextToken();
        String acct = st.nextToken();
        String pk = st.nextToken();
        String beginInvalidationTimestampStr = null;
        try
        {
            beginInvalidationTimestampStr = st.nextToken();
        }
        catch(NoSuchElementException ignore)
        {
            log.warn("Invalidation Begin Timestamp is missing from the message, hence invalidation stats may be incorrect. Message received:" + message);
        }
        onInvMessage(new String[] {
            name, op, acct, pk, beginInvalidationTimestampStr
        });
    }

    public static void onInvMessage(String tokens[])
    {
        String name;
        String op;
        String pk;
        String beginInvalidationTimestampStr;
        List list;
        name = tokens[0];
        op = tokens[1];
        String acct = tokens[2];
        pk = tokens[3];
        beginInvalidationTimestampStr = tokens[4];
        Long partitionId = Long.valueOf(acct);
        list = null;
        synchronized(map)
        {
            list = (List)map.get(partitionId);
        }
        if(list == null)
            return;
        dbServerSideInvBeginTimestampData.set(beginInvalidationTimestampStr);
        synchronized(list)
        {
            InvListener listener;
            for(Iterator it = list.iterator(); it.hasNext(); listener.onInvReceived(name, op, pk))
                listener = (InvListener)it.next();

        }
        dbServerSideInvBeginTimestampData.set(null);
        break MISSING_BLOCK_LABEL_166;
        Exception exception2;
        exception2;
        dbServerSideInvBeginTimestampData.set(null);
        throw exception2;
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
    private static Map map = new HashMap();
    public static ThreadLocal dbServerSideInvBeginTimestampData = new ThreadLocal();

    static 
    {
        log = Logit.getInstance(com.fitechlabs.dbind.impl.InvRouter.class);
        DBG = log.ison();
    }

}
