head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.37.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3TradeHistoryAllTests.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.tradehistory;

import webbroker3.tradehistory.service.delegate.stdimpls.WEB3HistoryTradeHistoryListServiceImplTest;
import junit.framework.Test;
import junit.framework.TestSuite;

/**
 *
 */
public class WEB3TradeHistoryAllTests
{

    /**
     *
     */
    public static Test suite()
    {
        TestSuite suite = new TestSuite("Test for webbroker3.tradehistory");
        //$JUnit-BEGIN$

        suite.addTestSuite(WEB3HistoryTradeHistoryListServiceImplTest.class);
        //$JUnit-END$
        return suite;
    }

}
@
