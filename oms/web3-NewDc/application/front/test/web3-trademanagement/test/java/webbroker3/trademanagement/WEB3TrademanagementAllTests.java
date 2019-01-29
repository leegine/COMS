head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.04.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3TrademanagementAllTests.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3TrademanagementAllTests.java
Author Name      : Daiwa Institute of Research
Revesion History : 2007/12/28 金傑 新規作成
*/
package webbroker3.trademanagement;

import junit.framework.Test;
import junit.framework.TestSuite;

import webbroker3.trademanagement.handler.WEB3AdminTMLoginFrequencyListHandlerTest;
import webbroker3.trademanagement.handler.WEB3AdminTMLoginProcessingListHandlerTest;
import webbroker3.trademanagement.handler.WEB3AdminTMLoginRejectIPManagementHandlerTest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlListRequestTest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlRegistCommonRequestTest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlSortKeyTest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlUpdateCommonRequestTest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlUpdateCompleteRequestTest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlUpdateConfirmRequestTest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginCountListRequestTest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginHistoryListRequestTest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginHistorySortKeyTest;
import webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginFrequencyListServiceImplTest;
import webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginProcessingListServiceImplTest;
import webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginRejectIPManagementServiceImplTest;
import webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMMarketStopStartChangeServiceImplTest;

/**
 * @@author 金傑(中訊)
 * @@version 1.0
 */
public class WEB3TrademanagementAllTests
{

    /**
     *
     */
    public static Test suite()
    {
        TestSuite suite = new TestSuite("Test for webbroker3.trademanagement");
        //$JUnit-BEGIN$

        suite.addTestSuite(WEB3AdminTMMarketStopStartChangeServiceImplTest.class);
        suite.addTestSuite(WEB3AdminTMLoginRejectIPManagementServiceImplTest.class);
        suite.addTestSuite(WEB3AdminTMLoginRejectIPManagementHandlerTest.class);
        suite.addTestSuite(WEB3AdminTMLoginProcessingListServiceImplTest.class);
        suite.addTestSuite(WEB3AdminTMLoginProcessingListHandlerTest.class);
        suite.addTestSuite(WEB3AdminTMLoginFrequencyListServiceImplTest.class);
        suite.addTestSuite(WEB3AdminTMLoginFrequencyListHandlerTest.class);
        
        suite.addTestSuite(WEB3TradeManagementLoginTableDataManagerTest.class);
        suite.addTestSuite(WEB3AdminTraderAdminIPControlUpdateCompleteRequestTest.class);
        suite.addTestSuite(WEB3AdminTraderAdminLoginHistorySortKeyTest.class);
        suite.addTestSuite(WEB3AdminTraderAdminIPControlUpdateCommonRequestTest.class);
        suite.addTestSuite(WEB3AdminTraderAdminLoginCountListRequestTest.class);
        suite.addTestSuite(WEB3AdminTraderAdminIPControlSortKeyTest.class);
        suite.addTestSuite(WEB3AdminTraderAdminLoginHistoryListRequestTest.class);
        suite.addTestSuite(WEB3AdminTraderAdminIPControlUpdateConfirmRequestTest.class);
        suite.addTestSuite(WEB3AdminTraderAdminIPControlListRequestTest.class);
        suite.addTestSuite(WEB3AdminTraderAdminIPControlRegistCommonRequestTest.class);
        //$JUnit-END$
        return suite;
    }

}
@
