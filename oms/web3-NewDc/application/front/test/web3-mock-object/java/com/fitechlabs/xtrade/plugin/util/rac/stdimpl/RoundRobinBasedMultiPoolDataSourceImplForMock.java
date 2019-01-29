head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.39.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	RoundRobinBasedMultiPoolDataSourceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package com.fitechlabs.xtrade.plugin.util.rac.stdimpl;

import javax.sql.DataSource;

import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class RoundRobinBasedMultiPoolDataSourceImplForMock extends RoundRobinBasedMultiPoolDataSourceImpl
{

    public RoundRobinBasedMultiPoolDataSourceImplForMock(DataSource arg0)
    {
        super(arg0);
    }

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(RoundRobinBasedMultiPoolDataSourceImplForMock.class);

    public String getJndiName()
    {
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "com.fitechlabs.xtrade.plugin.util.rac.stdimpl.RoundRobinBasedMultiPoolDataSourceImpl",
            "getJndiName",
            new Class[] {}))
        {

            return (String)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.util.rac.stdimpl.RoundRobinBasedMultiPoolDataSourceImpl",
                "getJndiName",
                new Class[] {}).asObject();
        }
        return super.getJndiName();
    }
}
@
