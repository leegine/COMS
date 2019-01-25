// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EqTypeProductManager.java

package com.fitechlabs.xtrade.plugin.tc.eqtype;

import com.fitechlabs.xtrade.plugin.tc.gentrade.*;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.eqtype:
//            EqTypeProduct, EqTypeTradedProduct

public interface EqTypeProductManager
    extends ProductManager
{

    public abstract EqTypeProduct getProduct(Institution institution, String s)
        throws NotFoundException;

    public abstract EqTypeTradedProduct getTradedProduct(Institution institution, String s, String s1)
        throws NotFoundException;
}
