// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   QPFDefaultImpl.java

package com.fitechlabs.xtrade.kernel.data.impl;

import com.fitechlabs.dbind.Database;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import java.sql.SQLException;
import java.util.Properties;

// Referenced classes of package com.fitechlabs.xtrade.kernel.data.impl:
//            QPFStdImpl, QPDefaultImpl

public class QPFDefaultImpl extends QPFStdImpl
{

    public QPFDefaultImpl(Properties properties, String namespace)
        throws SQLException
    {
        super(properties, namespace);
    }

    protected QueryProcessor constructQueryProcessor(Database database)
    {
        return new QPDefaultImpl(database);
    }
}
