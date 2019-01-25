// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EquityPositionManagerHelper.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls:
//            EqTypePositionManagerHelper

public class EquityPositionManagerHelper extends EqTypePositionManagerHelper
{

    public EquityPositionManagerHelper(ProductTypeEnum tradingModuleType)
    {
        super(tradingModuleType);
    }

    /**
     * @deprecated Method EquityPositionManagerHelper is deprecated
     */

    public EquityPositionManagerHelper()
    {
    }

    public boolean isAssetOrderExecution(EqTypeOrderExecution exec)
    {
        switch(exec.getOrderType().intValue())
        {
        case 1: // '\001'
        case 2: // '\002'
        case 101: // 'e'
        case 102: // 'f'
            return true;
        }
        return false;
    }
}
