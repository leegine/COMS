head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.23.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FuturesOpenMarginCompleteRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : (WEB3FuturesOpenMarginCompleteRequestTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/06/10 ���z(���u) �V�K�쐬
*/
package webbroker3.ifo.message;

import test.util.TestSpecialClassUtility;

public class WEB3FuturesOpenMarginCompleteRequestTest extends TestSpecialClassUtility
{

    public WEB3FuturesOpenMarginCompleteRequestTest(String arg0)
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
     * Test method for 'webbroker3.ifo.message.WEB3FuturesOpenMarginCompleteRequest.validate()'
     */
    public void testValidate()
    {
        this.testRequestValidate(
            WEB3FuturesOpenMarginCompleteRequest.class,
            "application/front/test/web3-xbifo/test/java");
    }

}
@
