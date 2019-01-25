// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypePositionManagerHelperUse.java

package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls:
//            EqTypePositionManagerHelper

public class EqTypePositionManagerHelperUse extends EqTypePositionManagerHelper
{

    public EqTypePositionManagerHelperUse(ProductTypeEnum tradingModuleType)
    {
        super(tradingModuleType);
    }

    /**
     * @deprecated Method EqTypePositionManagerHelperUse is deprecated
     */

    public EqTypePositionManagerHelperUse()
    {
    }

    public boolean isAssetOrderExecution(EqTypeOrderExecution exec)
    {
        return true;
    }
}
