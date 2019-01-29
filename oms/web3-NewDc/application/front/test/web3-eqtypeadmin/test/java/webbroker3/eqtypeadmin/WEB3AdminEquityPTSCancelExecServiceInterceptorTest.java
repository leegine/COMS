head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.27.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminEquityPTSCancelExecServiceInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 
Author Name      : Daiwa Institute of Research
Revesion History : 
*/
package webbroker3.eqtypeadmin;

import test.util.TestSpecialClassUtility;

import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminEquityPTSCancelExecServiceInterceptorTest extends TestBaseForMock
{
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3AdminEquityPTSCancelExecServiceInterceptorTest.class);
    
    public WEB3AdminEquityPTSCancelExecServiceInterceptorTest(String name)
    {
        super(name);
    }

    /**
     * onReturn
     * onThrowable
     */
    public void test_onReturnOronThrowable_0001()
    {
        final String STR_METHOD_NAME = "test_onReturnOronThrowable_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        TestSpecialClassUtility.testServiceInterceptor(new WEB3AdminEquityPTSCancelExecServiceInterceptor());
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
