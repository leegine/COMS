// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PrimaryKey.java

package com.fitechlabs.dbind;

import java.io.Serializable;

// Referenced classes of package com.fitechlabs.dbind:
//            RowType

public interface PrimaryKey
    extends Serializable
{

    public abstract RowType getRowType();
}
