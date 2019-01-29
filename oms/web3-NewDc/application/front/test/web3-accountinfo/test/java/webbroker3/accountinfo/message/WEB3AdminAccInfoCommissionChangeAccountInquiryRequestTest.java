head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.06.08.38.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5404d9c264d0465;
filename	WEB3AdminAccInfoCommissionChangeAccountInquiryRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountinfo.message;

import test.util.TestSpecialClassUtility;

import webbroker3.mock.TestBaseForMock;

public class WEB3AdminAccInfoCommissionChangeAccountInquiryRequestTest extends
        TestSpecialClassUtility
{

    public WEB3AdminAccInfoCommissionChangeAccountInquiryRequestTest(String arg0)
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
    public void testValidate()
    {
        this.testRequestValidate(WEB3AdminAccInfoCommissionChangeAccountInquiryRequest.class,
                "application/front/test/web3-accountinfo/test/java");
    }

}
@
