head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.50.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MstkCancelCompleteRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3MstkCancelCompleteRequestTest.java
 Author Name      : Daiwa Institute of Research
 Revesion History : 2008/07/10 楊夫志 (中訊) 新規作成
*/
package webbroker3.equity.message;

import test.util.TestSpecialClassUtility;

public class WEB3MstkCancelCompleteRequestTest extends TestSpecialClassUtility {

	public WEB3MstkCancelCompleteRequestTest(String arg0) {
		super(arg0);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/*
	 * Test method for 'webbroker3.equity.message.WEB3MstkCancelCompleteRequest.validate()'
	 */
	public void testValidate()
	{
		this.testRequestValidate(WEB3MstkCancelCompleteRequest.class,
            "application/front/test/web3-equity/test/java");
	}

}
@
