head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.58.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	DefaultOrderValidatorImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class DefaultOrderValidatorImplForMock extends DefaultOrderValidatorImpl
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(DefaultOrderValidatorImplForMock.class);

    public OrderValidationResult validateSubAccountForTrading(SubAccount l_subAccount)
    {
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.DefaultOrderValidatorImpl",
            "validateSubAccountForTrading",
            new Class[] {SubAccount.class}))
        {
            log.debug("com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.DefaultOrderValidatorImpl." +
                "validateSubAccountForTrading(SubAccount)");
            return (OrderValidationResult)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.DefaultOrderValidatorImpl",
                "validateSubAccountForTrading",
                new Class[] {SubAccount.class}).asObject();
        }
        return super.validateSubAccountForTrading(l_subAccount);
    }
}
@
