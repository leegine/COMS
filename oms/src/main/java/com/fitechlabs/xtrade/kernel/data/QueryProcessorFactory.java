// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   QueryProcessorFactory.java

package com.fitechlabs.xtrade.kernel.data;


// Referenced classes of package com.fitechlabs.xtrade.kernel.data:
//            DataNetworkException, DataFindException, QueryProcessor

public interface QueryProcessorFactory
{

    public abstract void extendInstance(String s, Class class1, Class class2, String s1, String s2);

    public abstract QueryProcessor getQueryProcessor(Long long1)
        throws DataNetworkException, DataFindException;

    public abstract QueryProcessor getFinderProcessor()
        throws DataNetworkException;

    public abstract QueryProcessor getMutatorProcessor();
}
