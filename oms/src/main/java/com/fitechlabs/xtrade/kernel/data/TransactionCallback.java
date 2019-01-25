// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TransactionCallback.java

package com.fitechlabs.xtrade.kernel.data;

import java.io.Serializable;

// Referenced classes of package com.fitechlabs.xtrade.kernel.data:
//            DataNetworkException, DataQueryException, DataCallbackException

public interface TransactionCallback
    extends Serializable
{

    public abstract Object process()
        throws DataNetworkException, DataQueryException, DataCallbackException;
}
