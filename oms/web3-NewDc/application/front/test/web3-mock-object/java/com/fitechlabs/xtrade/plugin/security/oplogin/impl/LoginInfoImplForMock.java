head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.37.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	LoginInfoImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 
Author Name      : Daiwa Institute of Research
Revesion History : 
*/
package com.fitechlabs.xtrade.plugin.security.oplogin.impl;

import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class LoginInfoImplForMock extends LoginInfoImpl
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            LoginInfoImplForMock.class);
    
    public long getLoginId()
    {
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {}))
            {
            log.debug("com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl.getLoginId");
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {}).asLong();
            }
        return super.getLoginId();
    }

    public long getLoginTypeId()
    {
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginTypeId",
                new Class[] {}))
        {
            log.debug("com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl.getLoginTypeId");
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginTypeId",
                new Class[] {}).asLong();
        }

        return super.getLoginTypeId();
    }
}
@
