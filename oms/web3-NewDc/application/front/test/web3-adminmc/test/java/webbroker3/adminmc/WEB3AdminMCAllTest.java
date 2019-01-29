head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.39.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminMCAllTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.adminmc;

import junit.framework.Test;
import junit.framework.TestSuite;

import webbroker3.adminmc.service.delegate.stdimpls.WEB3AdminMCAdminDeleteServiceImplTest;
import webbroker3.adminmc.service.delegate.stdimpls.WEB3AdminMCAdminRegistServiceImplTest;

public class WEB3AdminMCAllTest
{
    public static Test suite()
    {
        TestSuite suite = new TestSuite();

        suite.addTestSuite(WEB3AdminMCAdminDeleteServiceImplTest.class);
        suite.addTestSuite(WEB3AdminMCAdminRegistServiceImplTest.class);
        return suite;
    }
}
@
