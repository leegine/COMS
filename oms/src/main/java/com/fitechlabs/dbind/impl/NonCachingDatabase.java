// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NonCachingDatabase.java

package com.fitechlabs.dbind.impl;

import javax.sql.DataSource;

// Referenced classes of package com.fitechlabs.dbind.impl:
//            AbstractDatabase, SubclassDataStore

public class NonCachingDatabase extends AbstractDatabase
{

    public NonCachingDatabase(DataSource dataSource)
    {
        super(new SubclassDataStore(dataSource));
    }
}
