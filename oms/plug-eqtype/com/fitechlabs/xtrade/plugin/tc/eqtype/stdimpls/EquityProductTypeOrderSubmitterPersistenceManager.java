// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EquityProductTypeOrderSubmitterPersistenceManager.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls;

import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypePositionManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls:
//            EqTypeOrderSubmitterPersistenceManager

public class EquityProductTypeOrderSubmitterPersistenceManager extends EqTypeOrderSubmitterPersistenceManager
{

    protected EquityProductTypeOrderSubmitterPersistenceManager()
    {
    }

    public static EquityProductTypeOrderSubmitterPersistenceManager getInstance()
    {
        return THIS_INSTANCE;
    }

    protected static void setInstance(EquityProductTypeOrderSubmitterPersistenceManager newInstance)
    {
        m_log.info("Replacing default " + (com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EquityProductTypeOrderSubmitterPersistenceManager.class) + " default impl with new implementation : " + newInstance.getClass());
        THIS_INSTANCE = newInstance;
    }

    protected ProductTypeEnum getProductType()
    {
        return ProductTypeEnum.EQUITY;
    }

    protected void dispatchUpdateLockedQuantity(long accountId, long subAccountId, long orderUnitId, long productId, double lockedQtyTobeAdjusted)
    {
        EqTypePositionManager eqtypePosMgr = (EqTypePositionManager)GtlUtils.getTradingModule("eqtype").getPositionManager();
        eqtypePosMgr.updateLockedQuantity(accountId, subAccountId, orderUnitId, productId, lockedQtyTobeAdjusted);
    }

    protected TradingModule getThisTradingModule()
    {
        return GtlUtils.getTradingModule("eqtype");
    }

    private static final Logit m_log;
    private static final boolean DBG;
    private static EquityProductTypeOrderSubmitterPersistenceManager THIS_INSTANCE = new EquityProductTypeOrderSubmitterPersistenceManager();

    static 
    {
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EquityProductTypeOrderSubmitterPersistenceManager.class);
        DBG = m_log.ison();
    }
}
