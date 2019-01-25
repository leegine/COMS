// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PackageSpec.java

package com.fitechlabs.dbind.gen;

import java.util.*;

// Referenced classes of package com.fitechlabs.dbind.gen:
//            ProcedureSpec

public class PackageSpec
{

    public PackageSpec(String name)
    {
        allProcedures = new ArrayList();
        this.name = name;
    }

    public void addProcedure(ProcedureSpec proc)
    {
        allProcedures.add(proc);
    }

    public Iterator getProcedureIterator()
    {
        return allProcedures.iterator();
    }

    List allProcedures;
    String name;
}
