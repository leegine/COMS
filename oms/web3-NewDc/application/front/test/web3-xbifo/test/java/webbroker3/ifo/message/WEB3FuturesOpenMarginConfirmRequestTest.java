head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.26.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FuturesOpenMarginConfirmRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (WEB3FuturesOpenMarginConfirmRequestTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/06/05 安陽(中訊) 新規作成
*/
package webbroker3.ifo.message;

import test.util.TestSpecialClassUtility;

public class WEB3FuturesOpenMarginConfirmRequestTest extends TestSpecialClassUtility
{

    public WEB3FuturesOpenMarginConfirmRequestTest(String arg0)
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
     * Test method for 'webbroker3.ifo.message.WEB3FuturesOpenMarginConfirmRequest.validate()'
     */
    public void testValidate()
    {
        this.testRequestValidate(
            WEB3FuturesOpenMarginConfirmRequest.class,
            "application/front/test/web3-xbifo/test/java");
    }

}
@
