head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.11.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityAppPluginTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3EquityAppPluginTest.java
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/22  金傑　@(中訊) 新規作成
*/
package webbroker3.equity;


import test.util.TestSpecialClassUtility;

import webbroker3.equity.message.WEB3EquityMarginExecuteReferenceRequest;
import webbroker3.equity.service.delegate.WEB3EquityMarginExecuteReferenceService;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityAppPluginTest extends TestBaseForMock
{

    /**
     * Log<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityAppPluginTest.class);
    
    
    public WEB3EquityAppPluginTest(String name)
	{
		super(name);
	}
   
    public void testonPlug0001()
    {
    	final String STR_METHOD_NAME = "testonPlug0001()";
    	log.entering(TEST_START + STR_METHOD_NAME);
        
        Class l_requestExceptedValue[] = {WEB3EquityMarginExecuteReferenceRequest.class};
        Class l_serviceExceptedValue[] = {WEB3EquityMarginExecuteReferenceService.class};
        TestSpecialClassUtility.testAppPlugIn(l_requestExceptedValue,l_serviceExceptedValue,3);
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
