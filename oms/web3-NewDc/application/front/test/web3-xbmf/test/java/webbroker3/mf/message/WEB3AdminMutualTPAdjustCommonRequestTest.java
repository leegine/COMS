head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.58.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminMutualTPAdjustCommonRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf.message;


import test.util.TestSpecialClassUtility;

public class WEB3AdminMutualTPAdjustCommonRequestTest extends TestSpecialClassUtility
{

    public WEB3AdminMutualTPAdjustCommonRequestTest(String arg)
    {
        super(arg);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testValidate()
    {
        this.testRequestValidate(WEB3AdminMutualTPAdjustCommonRequest.class,
            "application/front/test/web3-xbmf/test/java");
    }

}
@
