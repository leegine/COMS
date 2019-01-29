head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.37.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	OpLoginSecurityServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.security.oplogin.impl;

import com.fitechlabs.xtrade.plugin.security.oplogin.IllegalSessionStateException;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;

import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class OpLoginSecurityServiceImplForMock extends OpLoginSecurityServiceImpl
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        OpLoginSecurityServiceImplForMock.class);
    
    public OpLoginSecurityServiceImplForMock()
    {
        super();
    }

    public long getAccountId()
        throws IllegalSessionStateException
    {
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getAccountId",
            new Class[] {}))
        {
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {}).asLong();            
        }
        return super.getAccountId();
    }

    public boolean isAccountIdSet()
        throws IllegalSessionStateException
    {
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "isAccountIdSet",
            new Class[] {}))
        {
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "isAccountIdSet",
                new Class[] {}).asBoolean();            
        }
        return super.isAccountIdSet();
    }
    
    public LoginInfo getLoginInfo() throws IllegalSessionStateException
    {
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                new Class[] {}))
        {
            return (LoginInfo) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[] {}).asObject();
        }
        return super.getLoginInfo();

    }
    public String getSessionProperty(String name) throws IllegalSessionStateException
    {
        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getSessionProperty",
            new Class[] {String.class},
            new Object[]{name});
        
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getSessionProperty",
                new Class[] {String.class}))
        {
            return (String) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getSessionProperty",
                    new Class[] {String.class}).asObject();

        }
        return super.getSessionProperty(name);
    }
    
    public boolean checkPassword(String password) 
    {
        // 1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "checkPassword",
            new Class[] {String.class},
            new Object[]{password});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "checkPassword",
            new Class[] {String.class}))
        {
            return (boolean) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "checkPassword",
                new Class[] {String.class}).asBoolean();
        }

        return super.checkPassword(password);
    }

    public String logIn(String username, String password)
    {
        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "logIn",
            new Class[] {String.class, String.class},
            new Object[]{username, password});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "logIn",
            new Class[] {String.class, String.class}))
        {

            //2)MockFor --〉 asVoid
            return (String)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "logIn",
                new Class[] {String.class, String.class}).asObject();
        }

        return super.logIn(username, password);
    }

    public long getLoginId()
        throws IllegalSessionStateException
    {
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getLoginId",
            new Class[] {}))
        {

            //2)MockFor --〉 asVoid
            return TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getLoginId",
                new Class[] {}).asLong();
        }

        return super.getLoginId();
    }
}
@
