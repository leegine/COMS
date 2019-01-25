// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CommonOrderValidatorImpl.java

package com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls;

import com.fitechlabs.xtrade.kernel.util.log.Logit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

public class CommonOrderValidatorImpl
    implements CommonOrderValidator
{

    public CommonOrderValidatorImpl()
    {
    }

    public OrderValidationResult validateTradingPassword(Trader trader, SubAccount subAccount, String tradingPassword)
    {
        String actualTradingPassword = subAccount.getMainAccount().getTradingPassword();
        if(actualTradingPassword == null || !actualTradingPassword.equals(tradingPassword))
            return new OrderValidationResult(ProcessingResult.newFailedResultInstance(CommonOrderValidator.INVALID_TRADING_PASSWORD));
        else
            return OrderValidationResult.VALIDATION_OK_RESULT;
    }

    public OrderValidationResult validateSubAccountForTrading(SubAccount subAccount)
    {
        if(SubAccountStatusEnum.ACTIVE.equals(subAccount.getSubAccountStatus()))
        {
            if(MainAccountStatusEnum.ACTIVE.equals(subAccount.getMainAccount().getAccountStatus()))
                return OrderValidationResult.VALIDATION_OK_RESULT;
            else
                return new OrderValidationResult(ProcessingResult.newFailedResultInstance(CommonOrderValidator.MAIN_ACCOUNT_LOCKED));
        } else
        {
            return new OrderValidationResult(ProcessingResult.newFailedResultInstance(CommonOrderValidator.SUBACCOUNT_LOCKED));
        }
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
        m_log = Logit.getInstance(com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.CommonOrderValidatorImpl.class);
        DBG = m_log.ison();
    }
}
