// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypePositionManagerImpl.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls:
//            AbstractEqTypePositionManager, EquityPositionManagerHelper, EqTypeAssetImpl, EqTypeContractImpl

public class EqTypePositionManagerImpl extends AbstractEqTypePositionManager
{

    public EqTypePositionManagerImpl()
    {
        m_tradingType = ProductTypeEnum.EQUITY;
        m_helper = new EquityPositionManagerHelper(m_tradingType);
    }

    public Asset toAssetPosition(Row row)
    {
        return toAsset(row);
    }

    public Contract toContractPosition(Row row)
    {
        return toContract(row);
    }

    public Asset toAsset(Row row)
    {
        return new EqTypeAssetImpl((AssetRow)row);
    }

    public Contract toContract(Row row)
    {
        return new EqTypeContractImpl((EqtypeContractRow)row);
    }

    static Class _mthclass$(String x0)
    {
        return Class.forName(x0);
        ClassNotFoundException x1;
        x1;
        throw new NoClassDefFoundError(x1.getMessage());
    }

    private static final Logit m_log;
    private static final boolean DBG;

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypePositionManagerImpl.class);
        DBG = m_log.ison();
    }
}
