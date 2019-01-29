head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.42.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	FaqAllTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.faq;

import webbroker3.faq.service.delegate.stdimpls.WEB3FaqInputServiceImplTest;

import junit.framework.Test;
import junit.framework.TestSuite;


public class FaqAllTest
{
    public static Test suite()
    {
        TestSuite suite = new TestSuite();
        
        suite.addTestSuite(WEB3FaqInputServiceImplTest.class);
        
        return suite;
        
    }
}
@
