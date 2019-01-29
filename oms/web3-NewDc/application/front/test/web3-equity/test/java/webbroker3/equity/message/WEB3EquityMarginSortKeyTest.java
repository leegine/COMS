head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.51.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityMarginSortKeyTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3EquityMarginExecuteReferenceRequest.java
Author Name      : Daiwa Institute of Research
Revesion History : Revesion History : 2007/01/17　@関博(中訊) 新規作成
*/
package webbroker3.equity.message;

import test.util.TestSpecialClassUtility;

import webbroker3.util.WEB3LogUtility;

public class WEB3EquityMarginSortKeyTest extends TestSpecialClassUtility
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3EquityMarginSortKeyTest.class);

    public WEB3EquityMarginSortKeyTest(String arg0)
    {
        super(arg0);
    }
    public static void main(String[] args)
    {

    }
    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }
    public void test()
    {
        final String STR_METHOD_NAME = "testWEB3EquityMarginSortKeyValidate()";
        log.debug(TEST_START + STR_METHOD_NAME);

        this.testRequestValidate(WEB3EquityMarginSortKey.class,
                "application/front/test/web3-equity/test/java");

        log.debug(TEST_END + STR_METHOD_NAME);
    }
}
@
