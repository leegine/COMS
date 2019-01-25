// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CommonOrderValidator.java

package com.fitechlabs.xtrade.plugin.tc.gentrade;

import com.fitechlabs.xtrade.kernel.error.ErrorManager;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

// Referenced classes of package com.fitechlabs.xtrade.plugin.tc.gentrade:
//            Trader, SubAccount

public interface CommonOrderValidator
{

    public abstract OrderValidationResult validateTradingPassword(Trader trader, SubAccount subaccount, String s);

    public abstract OrderValidationResult validateSubAccountForTrading(SubAccount subaccount);

    public static final ErrorInfo INVALID_TRADING_PASSWORD = ErrorManager.getInstance(_cls1.class.com.fitechlabs.xtrade.plugin.tc.gentrade.CommonOrderValidator != null ? _cls1.class.com.fitechlabs.xtrade.plugin.tc.gentrade.CommonOrderValidator : (_cls1.class.com.fitechlabs.xtrade.plugin.tc.gentrade.CommonOrderValidator = _cls1.class.("com.fitechlabs.xtrade.plugin.tc.gentrade.CommonOrderValidator"))).defineErrorInfo("XB_INVALID_TRADING_PASSWORD", "Invalid trading password.");
    public static final ErrorInfo MAIN_ACCOUNT_LOCKED = ErrorManager.getInstance(_cls1.class.com.fitechlabs.xtrade.plugin.tc.gentrade.CommonOrderValidator != null ? _cls1.class.com.fitechlabs.xtrade.plugin.tc.gentrade.CommonOrderValidator : (_cls1.class.com.fitechlabs.xtrade.plugin.tc.gentrade.CommonOrderValidator = _cls1.class.("com.fitechlabs.xtrade.plugin.tc.gentrade.CommonOrderValidator"))).defineErrorInfo("XB_MAIN_ACCOUNT_LOCKED", "No trading is allowed using the  MainAccount  to which the specified SubAccount belongs to since it may have been closed or locked.");
    public static final ErrorInfo SUBACCOUNT_LOCKED = ErrorManager.getInstance(_cls1.class.com.fitechlabs.xtrade.plugin.tc.gentrade.CommonOrderValidator != null ? _cls1.class.com.fitechlabs.xtrade.plugin.tc.gentrade.CommonOrderValidator : (_cls1.class.com.fitechlabs.xtrade.plugin.tc.gentrade.CommonOrderValidator = _cls1.class.("com.fitechlabs.xtrade.plugin.tc.gentrade.CommonOrderValidator"))).defineErrorInfo("XB_SUBACCOUNT_LOCKED", "No trading is allowed using the speicified SubAccount since the SubAccount may have been closed or locked.");


    // Unreferenced inner class com/fitechlabs/xtrade/plugin/tc/gentrade/CommonOrderValidator$1

/* anonymous class */
    static class _cls1
    {

        static Class _mthclass$(String x0)
        {
            return Class.forName(x0);
            ClassNotFoundException x1;
            x1;
            throw new NoClassDefFoundError(x1.getMessage());
        }

        static Class class$com$fitechlabs$xtrade$plugin$tc$gentrade$CommonOrderValidator; /* synthetic field */
    }

}
