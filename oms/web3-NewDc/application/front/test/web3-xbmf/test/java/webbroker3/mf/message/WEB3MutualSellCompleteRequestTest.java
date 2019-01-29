head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.57.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualSellCompleteRequestTest.java;


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


public class WEB3MutualSellCompleteRequestTest extends TestSpecialClassUtility
{
    /**
     * Logger
     */
   private static WEB3LogUtility log =
       WEB3LogUtility.getInstance(WEB3MutualSellCompleteRequestTest.class);

    public WEB3MutualSellCompleteRequestTest(String arg0)
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
     * Test method for 'webbroker3.mf.message.WEB3MutualSellCompleteRequest.validate()'
     */
    public void testValidate()
    {
        final String STR_METHOD_NAME = "testValidate()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        this.testRequestValidate(WEB3MutualSellCompleteRequest.class,
        "application/front/test/web3-xbmf/test/java");
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

}
@
