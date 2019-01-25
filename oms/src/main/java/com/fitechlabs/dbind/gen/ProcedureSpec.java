// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ProcedureSpec.java

package com.fitechlabs.dbind.gen;

import java.util.*;

// Referenced classes of package com.fitechlabs.dbind.gen:
//            ArgumentSpec

public class ProcedureSpec
{

    public ProcedureSpec(String name, int sqlReturnType)
    {
        allArguments = new ArrayList();
        this.name = name;
        this.sqlReturnType = sqlReturnType;
    }

    public void addArgument(ArgumentSpec arg)
    {
        allArguments.add(arg);
    }

    public Iterator getArgumentIterator()
    {
        return allArguments.iterator();
    }

    public String getName()
    {
        return name;
    }

    List allArguments;
    String name;
    int sqlReturnType;
}
