// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DefaultEqTypePositionManagerPersistenceEventInterceptor.java

package com.fitechlabs.xtrade.plugin.tc.eqtype;

import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import java.util.Map;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype:
//            EqTypePositionManagerPersistenceEventInterceptor

public class DefaultEqTypePositionManagerPersistenceEventInterceptor
    implements EqTypePositionManagerPersistenceEventInterceptor
{

    public DefaultEqTypePositionManagerPersistenceEventInterceptor()
    {
    }

    public AssetParams mutateBeforeInsert(AssetParams beforeAssetParams)
    {
        return beforeAssetParams;
    }

    public AssetUnitParams mutateBeforeInsert(AssetUnitParams beforeAssetUnitParams)
    {
        return beforeAssetUnitParams;
    }

    public AssetUnitSalesParams mutateBeforeInsert(AssetUnitSalesParams beforeAssetUnitSalesParams)
    {
        return beforeAssetUnitSalesParams;
    }

    public EqtypeContractParams mutateBeforeInsert(EqtypeContractParams beforeContractParams)
    {
        return beforeContractParams;
    }

    public EqtypeFinTransactionParams mutateBeforeInsert(EqtypeFinTransactionParams beforeFinTransactionParams)
    {
        return beforeFinTransactionParams;
    }

    public Map mutateBeforeUpdate(AssetParams assetParams, Map beforeChanges)
    {
        return beforeChanges;
    }

    public Map mutateBeforeUpdate(AssetUnitParams assetUnitParams, Map beforeChanges)
    {
        return beforeChanges;
    }

    public Map mutateBeforeUpdate(AssetUnitSalesParams assetUnitSalesParams, Map beforeChanges)
    {
        return beforeChanges;
    }

    public Map mutateBeforeUpdate(EqtypeContractParams contractParams, Map beforeChanges)
    {
        return beforeChanges;
    }

    public Map mutateBeforeUpdate(EqtypeFinTransactionParams finTransactionParams, Map beforeChanges)
    {
        return beforeChanges;
    }
}
