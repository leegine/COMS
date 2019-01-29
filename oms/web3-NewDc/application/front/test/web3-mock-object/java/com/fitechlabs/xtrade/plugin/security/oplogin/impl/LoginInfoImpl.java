head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.04.19.05.37.50;	author zhang-tengyu;	state Exp;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	666;
commitid	72c4dad1fad7290;
filename	LoginInfoImpl.java;

1.1
date	2011.04.07.02.37.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	LoginInfoImpl.java;


desc
@@


1.2
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

import java.util.Date;
import java.util.Map;
import java.util.Set;

import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginTypeInfo;

public class LoginInfoImpl implements LoginInfo
{

    public LoginTypeInfo getLoginTypeInfo()
    {
        // TODO Auto-generated method stub
        return null;
    }

    public long getLoginId()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    public long getLoginTypeId()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    public String getUsername()
    {
        // TODO Auto-generated method stub
        return null;
    }

    public String getInitialPassword()
    {
        // TODO Auto-generated method stub
        return null;
    }

    public Set getSubordinateLoginGroups()
    {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean isDisabled()
    {
        // TODO Auto-generated method stub
        return false;
    }

    public Set getReachableAccountIds()
    {
        // TODO Auto-generated method stub
        return null;
    }

    public Set getReachableLoginIds()
    {
        // TODO Auto-generated method stub
        return null;
    }

    public Set getReachableLogins()
    {
        // TODO Auto-generated method stub
        return null;
    }

    public Map getAttributes()
    {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean isAccountReachable(long arg0)
    {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean hasFailedLogin()
    {
        // TODO Auto-generated method stub
        return false;
    }

    public int getFailureCount()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    public Date getLastFailureTimestamp()
    {
        // TODO Auto-generated method stub
        return null;
    }

    public Map getLoginAttributes()
    {
        // TODO Auto-generated method stub
        return null;
    }

    public Map getLoginTypeAttributes()
    {
        // TODO Auto-generated method stub
        return null;
    }

    public Long getDefaultAccountId()
    {
        // TODO Auto-generated method stub
        return null;
    }

}
@


1.1
log
@*** empty log message ***
@
text
@d109 18
@

