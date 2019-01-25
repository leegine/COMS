// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PositionManager.java

package com.fitechlabs.xtrade.plugin.tc.gentrade;

import com.fitechlabs.dbind.Row;
import java.util.List;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade:
//            NotFoundException, MainAccount, SortKeySpec, ProductTypeEnum, 
//            FilterQueryParamsSpec, SubAccount, Asset, Product, 
//            Contract, PositionManagerPersistenceEventInterceptor

public interface PositionManager
{

    public abstract List getAssets(MainAccount mainaccount, SortKeySpec sortkeyspec, ProductTypeEnum producttypeenum);

    public abstract List getAssets(MainAccount mainaccount, FilterQueryParamsSpec filterqueryparamsspec, SortKeySpec sortkeyspec);

    public abstract List getAssets(SubAccount subaccount, SortKeySpec sortkeyspec, ProductTypeEnum producttypeenum);

    public abstract List getAssets(SubAccount subaccount, FilterQueryParamsSpec filterqueryparamsspec, SortKeySpec sortkeyspec);

    public abstract List getContracts(MainAccount mainaccount, SortKeySpec sortkeyspec, ProductTypeEnum producttypeenum);

    public abstract List getContracts(MainAccount mainaccount, FilterQueryParamsSpec filterqueryparamsspec, SortKeySpec sortkeyspec);

    public abstract List getContracts(SubAccount subaccount, FilterQueryParamsSpec filterqueryparamsspec, SortKeySpec sortkeyspec);

    public abstract List getContracts(SubAccount subaccount, SortKeySpec sortkeyspec, ProductTypeEnum producttypeenum);

    public abstract Asset getAsset(long l)
        throws NotFoundException;

    public abstract Asset getAsset(SubAccount subaccount, Product product)
        throws NotFoundException;

    public abstract Asset getAsset(long l, long l1, long l2)
        throws NotFoundException;

    public abstract Contract getContract(long l)
        throws NotFoundException;

    public abstract Asset toAsset(Row row);

    public abstract Contract toContract(Row row);

    /**
     * @deprecated Method getAssetPosition is deprecated
     */

    public abstract Asset getAssetPosition(long l)
        throws NotFoundException;

    /**
     * @deprecated Method getContractPosition is deprecated
     */

    public abstract Contract getContractPosition(long l)
        throws NotFoundException;

    public abstract void setThreadLocalPersistenceEventInterceptor(PositionManagerPersistenceEventInterceptor positionmanagerpersistenceeventinterceptor);
}
