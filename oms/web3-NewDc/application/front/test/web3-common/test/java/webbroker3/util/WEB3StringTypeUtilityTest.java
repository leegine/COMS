head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.36.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3StringTypeUtilityTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 文字列の文字種を判断する機@能Test(WEB3StringTypeUtilityTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/08/20 趙林鵬(中訊) 新規作成
*/

package webbroker3.util;

import webbroker3.mock.TestBaseForMock;

public class WEB3StringTypeUtilityTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3StringTypeUtilityTest.class);

    public WEB3StringTypeUtilityTest(String arg0)
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
     * isMultiメソッドを使用して1文字ずつ全角か半角かを判断し、<BR>
     * 算出したバイト数を返却する。<BR>
     */
    public void testGetFixedByteLengthCase0001()
    {
        final String STR_METHOD_NAME = "testGetFixedByteLengthCase0001()";
        log.entering(STR_METHOD_NAME);

        String l_str = "ABCDＥＦＧＨ〜‖";

        try
        {
            int l_intFixedByteLength = WEB3StringTypeUtility.getFixedByteLength(l_str);
         
            assertEquals(16, l_intFixedByteLength);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
