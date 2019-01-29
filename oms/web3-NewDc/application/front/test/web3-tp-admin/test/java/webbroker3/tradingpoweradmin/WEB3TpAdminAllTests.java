head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.36.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3TpAdminAllTests.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (Š”)‘å˜a‘Œ¤ ØŒ”ƒ\ƒŠƒ…[ƒVƒ‡ƒ“ƒVƒXƒeƒ€‘æ“ñ•”
File Name        : (WEB3TpAdminAllTests.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/08/07 æâ—Ñ–Q(’†u) V‹Kì¬
*/

package webbroker3.tradingpoweradmin;

import junit.framework.Test;
import junit.framework.TestSuite;

import webbroker3.tradingpoweradmin.handler.WEB3AdminTPPaymentRequisitionCustomerSearchHandlerTest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeCalcControlCommonRequestTest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionDetailRequestTest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionDownLoadRequestTest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionListRequestTest;
import webbroker3.tradingpoweradmin.service.delegate.stdimpls.WEB3AdminTPChangeCalcControlServiceImplTest;
import webbroker3.tradingpoweradmin.service.delegate.stdimpls.WEB3AdminTPPaymentRequisitionCustomerSearchServiceImplTest;
import webbroker3.tradingpoweradmin.service.delegate.stdimpls.WEB3AdminTPPaymentRequisitionManageServiceImplTest;
import webbroker3.tradingpoweradmin.service.delegate.stdimpls.WEB3AdminTPSearchAdvanceCustomerServiceImplTest;

/**
 * WEB3TpAdminAllTests//TODO
 *
 * @@author æâ—Ñ–Q(’†u)
 * @@version 1.0
 */

public class WEB3TpAdminAllTests
{

    public static Test suite()
    {
        TestSuite suite = new TestSuite();

        suite.addTestSuite(WEB3AdminTPPaymentRequisitionCustomerSearchCSVTest.class);
        suite.addTestSuite(WEB3AdminTPPaymentRequisitionCustomerSearchHandlerTest.class);           
        suite.addTestSuite(WEB3AdminTPChangeCalcControlCommonRequestTest.class);           
        suite.addTestSuite(WEB3AdminTPPaymentRequisitionDetailRequestTest.class);          
        suite.addTestSuite(WEB3AdminTPPaymentRequisitionDownLoadRequestTest.class);        
        suite.addTestSuite(WEB3AdminTPPaymentRequisitionListRequestTest.class);            
        suite.addTestSuite(WEB3AdminTPChangeCalcControlServiceImplTest.class);               
        suite.addTestSuite(WEB3AdminTPPaymentRequisitionCustomerSearchServiceImplTest.class);
        suite.addTestSuite(WEB3AdminTPPaymentRequisitionManageServiceImplTest.class);        
        suite.addTestSuite(WEB3AdminTPSearchAdvanceCustomerServiceImplTest.class);           

        return suite;
    }

}
@
