// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypePositionManagerPersistenceEventInterceptor.java

package com.fitechlabs.xtrade.plugin.tc.eqtype;

import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.PositionManagerPersistenceEventInterceptor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;
import java.util.Map;

public interface EqTypePositionManagerPersistenceEventInterceptor
    extends PositionManagerPersistenceEventInterceptor
{

    public abstract AssetParams mutateBeforeInsert(AssetParams assetparams);

    public abstract EqtypeContractParams mutateBeforeInsert(EqtypeContractParams eqtypecontractparams);

    public abstract EqtypeFinTransactionParams mutateBeforeInsert(EqtypeFinTransactionParams eqtypefintransactionparams);

    public abstract AssetUnitParams mutateBeforeInsert(AssetUnitParams assetunitparams);

    public abstract AssetUnitSalesParams mutateBeforeInsert(AssetUnitSalesParams assetunitsalesparams);

    public abstract Map mutateBeforeUpdate(AssetParams assetparams, Map map);

    public abstract Map mutateBeforeUpdate(EqtypeContractParams eqtypecontractparams, Map map);

    public abstract Map mutateBeforeUpdate(EqtypeFinTransactionParams eqtypefintransactionparams, Map map);

    public abstract Map mutateBeforeUpdate(AssetUnitParams assetunitparams, Map map);

    public abstract Map mutateBeforeUpdate(AssetUnitSalesParams assetunitsalesparams, Map map);
}
