head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.36.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3UtilAllTests.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (WEB3UtilAllTests.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/08 栄イ (中訊) 新規作成
*/

package webbroker3.util;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * テストクラス
 *
 * @@author 栄イ (中訊)
 * @@version 1.0
 */  
public class WEB3UtilAllTests
{
    private static boolean needRunWEB3DateUtilityTest = true;

    public static Test suite()
    {
        TestSuite suite = new TestSuite("Test for webbroker3.util");
        //$JUnit-BEGIN$
            
        if (needRunWEB3DateUtilityTest) 
        {
            suite.addTestSuite(WEB3DateUtilityTest.class);  
        }
        
        suite.addTestSuite(WEB3StringTypeUtilityTest.class);  
        //$JUnit-END$
        return suite;
        
    }
}
@
