// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Row.java

package com.fitechlabs.dbind;

import java.io.Serializable;
import java.util.Map;

// Referenced classes of package com.fitechlabs.dbind:
//            PrimaryKey, RowType

public interface Row
    extends Serializable
{

    public abstract PrimaryKey getPrimaryKey();

    public abstract Object getColumn(String s);

    public abstract RowType getRowType();

    public abstract Map toInsertMap();

    public abstract Map toUpdateMap();

    /**
     * @deprecated Method toMap is deprecated
     */

    public abstract Map toMap();

    public static final String TAGNAME = "row";
}
