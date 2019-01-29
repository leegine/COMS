head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.26.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	TestSpecialClassUtilityTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package test.util;

import webbroker3.util.WEB3LogUtility;

import junit.framework.AssertionFailedError;


public class TestSpecialClassUtilityTest extends TestSpecialClassUtility
{
    
    /**
     * Logger
     */
   private static WEB3LogUtility log =
       WEB3LogUtility.getInstance(TestSpecialClassUtilityTest.class);

    public TestSpecialClassUtilityTest(String arg0)
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

    /**
     * 外国株式買付完了リクエスト<BR>
     * (テスト対象)<BR>
     * 外国株式買付完了リクエストのvalidate（）<BR>
     */
    public void testValidate() 
    {
        final String STR_METHOD_NAME = "testValidate()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try 
        {
            this.testRequestValidate(WEB3FeqBuyCompleteRequestForTest.class,
                                            "application/front/test/web3-mock-object-test/java");
        } 
        catch(Throwable l_ex)
        {
            assertEquals(AssertionFailedError.class,l_ex.getClass());
        }
        log.exiting(TEST_END + STR_METHOD_NAME);

        //assertLogs("application/front/test/web3-mock-object-test/java",TestSpecialClassUtilityTest.class,STR_METHOD_NAME);
    }
}
@
