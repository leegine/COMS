head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.58.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualSellConfirmRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf.message;

import test.util.TestSpecialClassUtility;

import webbroker3.util.WEB3LogUtility;


public class WEB3MutualSellConfirmRequestTest extends TestSpecialClassUtility
{
    /**
     * Logger
     */
   private static WEB3LogUtility log =
       WEB3LogUtility.getInstance(WEB3MutualSellConfirmRequestTest.class);

    public WEB3MutualSellConfirmRequestTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /*
     * Test method for 'webbroker3.mf.message.WEB3MutualSellConfirmRequest.validate()'
     */
    public void testValidate()
    {
        final String STR_METHOD_NAME = "testValidate()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        this.testRequestValidate(WEB3MutualSellConfirmRequest.class,
        "application/front/test/web3-xbmf/test/java");
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

}
@
