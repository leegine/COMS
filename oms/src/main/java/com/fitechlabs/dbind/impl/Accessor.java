// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Accessor.java

package com.fitechlabs.dbind.impl;

import com.fitechlabs.dbind.*;
import java.util.List;

// Referenced classes of package com.fitechlabs.dbind.impl:
//            BindSql, ResultFiller

public interface Accessor
{

    public abstract int mutateInsert(String s, BindSql bindsql)
        throws ConnectionException, InsertException;

    public abstract int mutateUpdate(String s, BindSql bindsql, BindSql bindsql1, BindSql bindsql2)
        throws ConnectionException, UpdateException;

    public abstract int mutateDelete(String s, String s1, BindSql bindsql, BindSql bindsql1)
        throws ConnectionException, DeleteException;

    public abstract Object accessUnique(String s, BindSql bindsql, BindSql bindsql1, BindSql bindsql2, String s1, ResultFiller resultfiller)
        throws ConnectionException, SelectException;

    public abstract List accessList(String s, BindSql bindsql, BindSql bindsql1, String s1, String s2, ResultFiller resultfiller)
        throws ConnectionException, SelectException;
}
