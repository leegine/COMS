head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.16.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3LoginAllTests.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (WEB3LoginAllTests.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/14 栄イ (中訊) 新規作成
*/

package webbroker3.login;

import junit.framework.Test;
import junit.framework.TestSuite;

//import webbroker3.login.handler.WEB3CCOperatorAccountListHandlerTest;
import webbroker3.login.handler.WEB3CCOperatorAccountListHandlerTest;
import webbroker3.login.message.WEB3CCOperatorAccountListRequestTest;
import webbroker3.login.message.WEB3TraderAccountInfosSortKeyTest;
import webbroker3.login.service.delegate.stdimpls.WEB3AcceptLoginServiceImplTest;
import webbroker3.login.service.delegate.stdimpls.WEB3AcceptLoginServiceImplTest070614_validateDiscriminationCode;
import webbroker3.login.service.delegate.stdimpls.WEB3AcceptLoginServiceImplTest_insertLoginHistory;
import webbroker3.login.service.delegate.stdimpls.WEB3AcceptLoginServiceImplTest_xhw;
import webbroker3.login.service.delegate.stdimpls.WEB3AcceptLoginServiceImplTest_xiexuan;
import webbroker3.login.service.delegate.stdimpls.WEB3CCOperatorAccountListServiceImplTest;
import webbroker3.login.service.delegate.stdimpls.WEB3LoginServiceBaseImplTest;

/**
 * テストクラス
 *
 * @@author 栄イ (中訊)
 * @@version 1.0
 */
public class WEB3LoginAllTests
{
    public static Test suite()
    {
        TestSuite suite = new TestSuite("Test for webbroker3.login");
        //$JUnit-BEGIN$
        suite.addTestSuite(WEB3CCOperatorAccountListHandlerTest.class);
        suite.addTestSuite(WEB3CCOperatorAccountListRequestTest.class);
        suite.addTestSuite(WEB3TraderAccountInfosSortKeyTest.class);
        suite.addTestSuite(WEB3AcceptLoginServiceImplTest_insertLoginHistory.class);
        suite.addTestSuite(WEB3AcceptLoginServiceImplTest_xhw.class);
        suite.addTestSuite(WEB3AcceptLoginServiceImplTest_xiexuan.class);
        suite.addTestSuite(WEB3AcceptLoginServiceImplTest.class);
        suite.addTestSuite(WEB3AcceptLoginServiceImplTest070614_validateDiscriminationCode.class);
        suite.addTestSuite(WEB3CCOperatorAccountListServiceImplTest.class);
        suite.addTestSuite(WEB3LoginServiceBaseImplTest.class);
        //$JUnit-END$
        return suite;

    }
}
@
