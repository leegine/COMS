head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.37.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	OpLoginAdminServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : //TODO(OpLoginAdminServiceImplForMock.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/03 ���G�� (���u) �V�K�쐬
*/
package com.fitechlabs.xtrade.plugin.security.oplogin.impl;

import java.util.Map;

import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;

import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * XXXXXX�N���X//TODO
 *
 * @@author ���G��(���u)
 * @@version 1.0
 */
public class OpLoginAdminServiceImplForMock extends OpLoginAdminServiceImpl
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        OpLoginAdminServiceImplForMock.class);

    public Map getLoginTypeAttributes(long loginTypeId)
    {
        final String STR_METHOD_NAME = "getLoginTypeAttributes(long) -- ForMock";
        log.entering(STR_METHOD_NAME);
        //1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
            "getLoginTypeAttributes",
            new Class[] {long.class},
            new Object[]{new Long(loginTypeId)});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
                "getLoginTypeAttributes",
                new Class[] {long.class}))
            {
                //3)MockFor --�r asVoid
                log.exiting(STR_METHOD_NAME);
                return (Map)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
                    "getLoginTypeAttributes",
                    new Class[] {long.class}).asObject();            
            }

            log.exiting(STR_METHOD_NAME);
            return super.getLoginTypeAttributes(loginTypeId);
    }
    
    public LoginInfo getLoginInfo(long loginTypeId)
    {
        final String STR_METHOD_NAME = "getLoginTypeAttributes(long) -- ForMock";
        log.entering(STR_METHOD_NAME);
        //1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
            "getLoginInfo",
            new Class[] {long.class},
            new Object[]{new Long(loginTypeId)});
 
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
                "getLoginInfo",
                new Class[] {long.class}))
            {
        	log.debug("com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImplForMock.getLoginInfo(long)");
                return (LoginInfo)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
                    "getLoginInfo",
                    new Class[] {long.class}).asObject();            
            }
 
            log.exiting(STR_METHOD_NAME);
            return super.getLoginInfo(loginTypeId);
    }
    
    public LoginInfo getLoginInfo(String username)
    {
        final String STR_METHOD_NAME = "getLoginTypeAttributes(long) -- ForMock";
        log.entering(STR_METHOD_NAME);
        //1�j�ҝ���
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
            "getLoginInfo",
            new Class[] {String.class},
            new Object[]{username});
 
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
                "getLoginInfo",
                new Class[] {String.class}))
            {
            log.debug("com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImplForMock.getLoginInfo(long)");
                return (LoginInfo)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
                    "getLoginInfo",
                    new Class[] {String.class}).asObject();            
            }
 
            log.exiting(STR_METHOD_NAME);
            return super.getLoginInfo(username);
    }
    
    public Map getLoginAttributes(long loginTypeId)
    {
        final String STR_METHOD_NAME = "getLoginAttributes(long) -- ForMock";
        log.entering(STR_METHOD_NAME);
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
            "getLoginAttributes",
            new Class[] {long.class},
            new Object[]{new Long(loginTypeId)});
 
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
                "getLoginAttributes",
                new Class[] {long.class}))
            {
        	log.debug("com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImplForMock.getLoginAttributes(long)");
                return (Map)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
                    "getLoginAttributes",
                    new Class[] {long.class}).asObject();            
            }
 
            log.exiting(STR_METHOD_NAME);
            return super.getLoginTypeAttributes(loginTypeId);
    }

    public LoginInfo createLogin(long loginTypeId, String username, String password)
    {
        final String STR_METHOD_NAME = "createLogin(long, String, String) -- ForMock";
        log.entering(STR_METHOD_NAME);
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
            "createLogin",
            new Class[] {long.class, String.class, String.class},
            new Object[]{new Long(loginTypeId), username, password});
 
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
                "createLogin",
                new Class[] {long.class, String.class, String.class}))
            {
            log.debug("com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImplForMock.createLogin()");
                return (LoginInfo)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
                    "createLogin",
                    new Class[] {long.class, String.class, String.class}).asObject();            
            }
 
            log.exiting(STR_METHOD_NAME);
            return super.createLogin(loginTypeId, username, password);
    }

    public void setLoginAttributes(final long loginId, Map attributes)
    {
        final String STR_METHOD_NAME = "setLoginAttributes(final long loginId, Map attributes) -- ForMock";
        log.entering(STR_METHOD_NAME);
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
            "setLoginAttributes",
            new Class[] {long.class, Map.class},
            new Object[]{new Long(loginId), attributes});
 
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
            "setLoginAttributes",
            new Class[] {long.class, Map.class}))
            {
            log.debug("com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImplForMock.setLoginAttributes()");
                 TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
                    "setLoginAttributes",
                    new Class[] {long.class, Map.class}).asVoid();

                 return;
            }

            log.exiting(STR_METHOD_NAME);
            super.setLoginAttributes(loginId, attributes);
    }

    public void addLoginGroupManager(long groupId, long loginId)
    {
        final String STR_METHOD_NAME = "addLoginGroupManager(long loginId, long loginId) -- ForMock";
        log.entering(STR_METHOD_NAME);
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
            "addLoginGroupManager",
            new Class[] {long.class, long.class},
            new Object[]{new Long(groupId), new Long(loginId)});
 
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
            "addLoginGroupManager",
            new Class[] {long.class, long.class}))
            {
            log.debug("com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImplForMock.addLoginGroupManager()");
                 TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
                    "addLoginGroupManager",
                    new Class[] {long.class, long.class}).asVoid();

                 return;
            }

            log.exiting(STR_METHOD_NAME);
            super.addLoginGroupManager(groupId, loginId);
    }
}
@
