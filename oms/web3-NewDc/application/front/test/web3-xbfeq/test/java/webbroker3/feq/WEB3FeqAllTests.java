head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.55.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FeqAllTests.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (WEB3FeqAllTests.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/09 丁昭奎 (中訊) 新規作成
*/
package webbroker3.feq;

import junit.framework.Test;
import junit.framework.TestSuite;

import webbroker3.feq.handler.WEB3FeqBookValuePriceRegistHandlerTest;
import webbroker3.feq.message.WEB3AdminFeqForeignCostRegistCommonRequestTest;
import webbroker3.feq.message.WEB3AdminFeqForeignCostRegistInputRequestTest;
import webbroker3.feq.message.WEB3AdminFeqSendQueueReferenceRequestTest;
import webbroker3.feq.message.WEB3FeqBookPriceConfirmRequestTest;
import webbroker3.feq.message.WEB3FeqBookPriceRegistRequestTest;
import webbroker3.feq.message.WEB3FeqExecuteReferenceRequestTest;
import webbroker3.feq.service.delegate.stdimpls.WEB3AdminFeqCancelExecutionServiceImplTest;
import webbroker3.feq.service.delegate.stdimpls.WEB3AdminFeqExchangeRegistServiceImplTest;
import webbroker3.feq.service.delegate.stdimpls.WEB3AdminFeqExecuteResultUploadServiceImplTest;
import webbroker3.feq.service.delegate.stdimpls.WEB3AdminFeqExecutionEndServiceImplTest;
import webbroker3.feq.service.delegate.stdimpls.WEB3AdminFeqOpenAtOrderDLServiceImplTest;
import webbroker3.feq.service.delegate.stdimpls.WEB3AdminFeqOrderAcceptServiceImplTest;
import webbroker3.feq.service.delegate.stdimpls.WEB3AdminFeqOrderAndExecutionServiceImplTest;
import webbroker3.feq.service.delegate.stdimpls.WEB3AdminFeqOrderAndExecutionServiceImplTest_wubo;
import webbroker3.feq.service.delegate.stdimpls.WEB3AdminFeqOrderVoucherListServiceImplTest;
import webbroker3.feq.service.delegate.stdimpls.WEB3AdminFeqOrderVoucherListServiceImplTestZj;
import webbroker3.feq.service.delegate.stdimpls.WEB3AdminFeqRcvdQueueReferenceServiceImplTest;
import webbroker3.feq.service.delegate.stdimpls.WEB3AdminFeqSendQueueReferenceServiceImplTest;
import webbroker3.feq.service.delegate.stdimpls.WEB3AsynFeqOrderAcceptExecutionNotifyServiceImplTest;
import webbroker3.feq.service.delegate.stdimpls.WEB3FeqBalanceReferenceServiceImplTest;
import webbroker3.feq.service.delegate.stdimpls.WEB3FeqBookValuePriceRegistServiceImplTest;
import webbroker3.feq.service.delegate.stdimpls.WEB3FeqCancelServiceImplTest;
import webbroker3.feq.service.delegate.stdimpls.WEB3FeqChangeServiceImplTest;
import webbroker3.feq.service.delegate.stdimpls.WEB3FeqExecuteReferenceServiceImplTest;
import webbroker3.feq.service.delegate.stdimpls.WEB3FeqExecutionNotifyUnitServiceImplTest;
import webbroker3.feq.service.delegate.stdimpls.WEB3FeqOrderAcceptUnitServiceImplTest;
import webbroker3.feq.service.delegate.stdimpls.WEB3FeqOrderAndExecutionUpdateServiceImplTest;
import webbroker3.feq.service.delegate.stdimpls.WEB3FeqOrderCarryOverServiceImplTest;
import webbroker3.feq.service.delegate.stdimpls.WEB3FeqSellServiceImplTest;

/**
 *  * @@author 丁昭奎
 * @@version 1.0
 */
public class WEB3FeqAllTests
{
    public static Test suite()
    {
        TestSuite suite = new TestSuite("Test for webbroker3.feq");
        //$JUnit-BEGIN$
        suite.addTestSuite(WEB3FeqBizLogicProviderTest.class);
        suite.addTestSuite(WEB3FeqFinTransactionManagerTest.class);
        suite.addTestSuite(WEB3FeqOrderAcceptExecutionNotifyUnitTransactionCallbackTest.class);
        suite.addTestSuite(WEB3FeqOrderManagerTest.class);
        suite.addTestSuite(WEB3FeqOrderUnitTest.class);
        suite.addTestSuite(WEB3FeqOrderVoucherCSVTest.class);
        suite.addTestSuite(WEB3FeqPositionManagerHelperTest.class);
        suite.addTestSuite(WEB3FeqProductManagerTest.class);
        suite.addTestSuite(WEB3FeqProductTest.class);
        suite.addTestSuite(WEB3FeqTypeOrderManagerReusableValidationsTest.class);
        suite.addTestSuite(WEB3FeqBookValuePriceRegistHandlerTest.class);
        suite.addTestSuite(WEB3AdminFeqSendQueueReferenceRequestTest.class);
        suite.addTestSuite(WEB3FeqBookPriceConfirmRequestTest.class);
        suite.addTestSuite(WEB3FeqBookPriceRegistRequestTest.class);
        suite.addTestSuite(WEB3AdminFeqExecuteResultUploadServiceImplTest.class);
        suite.addTestSuite(WEB3AdminFeqExecutionEndServiceImplTest.class);
        suite.addTestSuite(WEB3AdminFeqOpenAtOrderDLServiceImplTest.class);
        suite.addTestSuite(WEB3AdminFeqOrderAcceptServiceImplTest.class);
        suite.addTestSuite(WEB3AdminFeqOrderAndExecutionServiceImplTest_wubo.class);
        suite.addTestSuite(WEB3AdminFeqOrderAndExecutionServiceImplTest.class);
        suite.addTestSuite(WEB3AdminFeqOrderVoucherListServiceImplTest.class);
        suite.addTestSuite(WEB3AdminFeqOrderVoucherListServiceImplTestZj.class);
        suite.addTestSuite(WEB3AdminFeqSendQueueReferenceServiceImplTest.class);
        suite.addTestSuite(WEB3AsynFeqOrderAcceptExecutionNotifyServiceImplTest.class);
        suite.addTestSuite(WEB3FeqBalanceReferenceServiceImplTest.class);
        suite.addTestSuite(WEB3FeqBookValuePriceRegistServiceImplTest.class);
        suite.addTestSuite(WEB3FeqCancelServiceImplTest.class);
        suite.addTestSuite(WEB3FeqChangeServiceImplTest.class);
        suite.addTestSuite(WEB3FeqExecuteReferenceServiceImplTest.class);
        suite.addTestSuite(WEB3FeqExecutionNotifyUnitServiceImplTest.class);
        suite.addTestSuite(WEB3FeqOrderAcceptUnitServiceImplTest.class);
        suite.addTestSuite(WEB3FeqOrderAndExecutionUpdateServiceImplTest.class);
        suite.addTestSuite(WEB3FeqOrderCarryOverServiceImplTest.class);
        suite.addTestSuite(WEB3FeqSellServiceImplTest.class);
        suite.addTestSuite(WEB3FeqChangeServiceInterceptorTest.class);
        suite.addTestSuite(WEB3FeqCancelServiceInterceptorTest.class);
        suite.addTestSuite(WEB3FeqMarketRequestSenderServiceTest.class);
        suite.addTestSuite(WEB3FeqOrderActionTest.class);
        suite.addTestSuite(WEB3FeqExecuteReferenceRequestTest.class);
        suite.addTestSuite(WEB3AdminFeqCancelExecutionServiceImplTest.class);
        suite.addTestSuite(WEB3AdminFeqRcvdQueueReferenceServiceImplTest.class);
        suite.addTestSuite(WEB3FeqOrderAcceptUnitServiceInterceptorTest.class);
        suite.addTestSuite(WEB3AdminFeqForeignCostRegistCommonRequestTest.class);
        suite.addTestSuite(WEB3AdminFeqForeignCostRegistInputRequestTest.class);
        suite.addTestSuite(WEB3AdminFeqExchangeRegistServiceImplTest.class);
        //$JUnit-END$
        return suite;
    }
}
@
