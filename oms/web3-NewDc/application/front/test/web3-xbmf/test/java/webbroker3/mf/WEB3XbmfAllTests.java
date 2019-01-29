head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.15.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3XbmfAllTests.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (äî)ëÂòaëçå§ èÿåîÉ\ÉäÉÖÅ[ÉVÉáÉìÉVÉXÉeÉÄëÊìÒïî
 File Name        : WEB3XbmfAllTests.java
 Author Name      : Daiwa Institute of Research
 Revesion History : 2007/02/25Å@@éƒëoçg(íÜêu) êVãKçÏê¨
 */
package webbroker3.mf;



import junit.framework.Test;
import junit.framework.TestSuite;

import webbroker3.mf.handler.WEB3AdminMutualConditionsHandlerTest;
import webbroker3.mf.handler.WEB3AdminMutualTPACancelHandlerTest;
import webbroker3.mf.handler.WEB3MutualFixedBuyConditionHandlerTest;
import webbroker3.mf.marketadaptor.WEB3MutualFundMarketRequestServiceTest;
import webbroker3.mf.message.WEB3AdminMutualCategoryRegistChangeRequestTest;
import webbroker3.mf.message.WEB3AdminMutualConditionsReferenceRequestTest;
import webbroker3.mf.message.WEB3AdminMutualDisplayOrderCompleteRequestTest;
import webbroker3.mf.message.WEB3AdminMutualDisplayOrderInputRequestTest;
import webbroker3.mf.message.WEB3AdminMutualFrgncalCompleteRequestTest;
import webbroker3.mf.message.WEB3AdminMutualFrgncalReferenceRequestTest;
import webbroker3.mf.message.WEB3AdminMutualTPAdjustCommonRequestTest;
import webbroker3.mf.message.WEB3MutualBuyCompleteRequestTest;
import webbroker3.mf.message.WEB3MutualBuyConfirmRequestTest;
import webbroker3.mf.message.WEB3MutualBuyListRequestTest;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionCompleteRequestTest;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionConfirmRequestTest;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionInputRequestTest;
import webbroker3.mf.message.WEB3MutualOrderReferenceRequestTest;
import webbroker3.mf.message.WEB3MutualProductConditionsCommonRequestTest;
import webbroker3.mf.message.WEB3MutualProductConditionsCommonRequestTestByReflect;
import webbroker3.mf.message.WEB3MutualSellCompleteRequestTest;
import webbroker3.mf.message.WEB3MutualSellConfirmRequestTest;
import webbroker3.mf.message.WEB3MutualSwitchingCompleteRequestTest;
import webbroker3.mf.service.delegate.stdimpls.WEB3AdminMutualCategoryRegistServiceImplTest;
import webbroker3.mf.service.delegate.stdimpls.WEB3AdminMutualConditionsReferenceServiceImplTest;
import webbroker3.mf.service.delegate.stdimpls.WEB3AdminMutualConditionsServiceImplTest;
import webbroker3.mf.service.delegate.stdimpls.WEB3AdminMutualDisplayOrderServiceImplTest;
import webbroker3.mf.service.delegate.stdimpls.WEB3AdminMutualFrgncalServiceImplTest;
import webbroker3.mf.service.delegate.stdimpls.WEB3AdminMutualTPACancelServiceImplTest;
import webbroker3.mf.service.delegate.stdimpls.WEB3AdminMutualTPAdjustServiceImplTest;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualBalanceReferenceServiceImplTest;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualBuyInputServiceImplTest;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualBuyListServiceImplTest;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualBuyServiceImplTest;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualCancelAcceptServiceImplTest;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualCancelServiceImplTest;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualFixedBuyApplyServiceImplTest;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualFixedBuyConditionListServiceImplTest;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualFrgnMmfOrderAcceptNormalTransactionCallbackTest;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualFrgnMmfOrderAcceptServiceImplTest;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualOrderReferenceServiceImplTest;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualRecruitOrderInputServiceImplTest;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualRecruitOrderServiceImplTest;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualSellInputServiceImplTest;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualSellServiceImplTest;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualSwitchingInputServiceImplTest;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualSwitchingServiceImplTest;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualSwtProductListServiceImplTest;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualTradeOrderNotifyUnitServiceImplTest;

public class WEB3XbmfAllTests
{
    public static Test suite()
    {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(WEB3MutualFixedBuyCommonServiceImplTest.class);
        suite.addTestSuite(WEB3MutualBalanceReferenceServiceImplTest.class);
        suite.addTestSuite(WEB3MutualFundBizLogicProviderTest.class);
        suite.addTestSuite(WEB3MutualFundNewOrderConfirmInterceptorTest.class);
        suite.addTestSuite(WEB3MutualFundOrderManagerReusableValidationsCheckTest.class);
        suite.addTestSuite(WEB3MutualFundOrderManagerTest.class);
        suite.addTestSuite(WEB3MutualFundPositionManagerTest.class);
        suite.addTestSuite(WEB3MutualFundProductCondSpecTest.class);
        suite.addTestSuite(WEB3MutualFundProductManagerTest.class);
        suite.addTestSuite(WEB3MutualFundProductTest.class);
        suite.addTestSuite(WEB3MutualFundTradingTimeManagementForMockTest.class);
        suite.addTestSuite(WEB3MutualFundTradingTimeManagementTest.class);
        suite.addTestSuite(WEB3MutualProductCategoryTest.class);
        suite.addTestSuite(WEB3MutualFundMarketRequestServiceTest.class);
        suite.addTestSuite(WEB3MutualBuyCompleteRequestTest.class);
        suite.addTestSuite(WEB3MutualBuyConfirmRequestTest.class);
        suite.addTestSuite(WEB3MutualProductConditionsCommonRequestTest.class);
        suite.addTestSuite(WEB3MutualProductConditionsCommonRequestTestByReflect.class);
        suite.addTestSuite(WEB3MutualSellCompleteRequestTest.class);
        suite.addTestSuite(WEB3MutualSellConfirmRequestTest.class);
        suite.addTestSuite(WEB3AdminMutualCategoryRegistServiceImplTest.class);
        suite.addTestSuite(WEB3AdminMutualConditionsReferenceServiceImplTest.class);
        suite.addTestSuite(WEB3AdminMutualConditionsServiceImplTest.class);
        suite.addTestSuite(WEB3AdminMutualDisplayOrderServiceImplTest.class);
        suite.addTestSuite(WEB3MutualBuyInputServiceImplTest.class);
        suite.addTestSuite(WEB3MutualBuyListServiceImplTest.class);
        suite.addTestSuite(WEB3MutualBuyServiceImplTest.class);
        suite.addTestSuite(WEB3MutualCancelServiceImplTest.class);
        suite.addTestSuite(WEB3MutualFixedBuyApplyServiceImplTest.class);
        suite.addTestSuite(WEB3MutualFixedBuyConditionListServiceImplTest.class);
        suite.addTestSuite(WEB3MutualFrgnMmfOrderAcceptNormalTransactionCallbackTest.class);
        suite.addTestSuite(WEB3MutualFrgnMmfOrderAcceptServiceImplTest.class);
        suite.addTestSuite(WEB3MutualOrderReferenceServiceImplTest.class);
        suite.addTestSuite(WEB3MutualRecruitOrderInputServiceImplTest.class);
        suite.addTestSuite(WEB3MutualRecruitOrderServiceImplTest.class);
        suite.addTestSuite(WEB3MutualSellInputServiceImplTest.class);
        suite.addTestSuite(WEB3MutualSellServiceImplTest.class);
        suite.addTestSuite(WEB3MutualSwitchingInputServiceImplTest.class);
        suite.addTestSuite(WEB3MutualSwitchingServiceImplTest.class);
        suite.addTestSuite(WEB3MutualSwtProductListServiceImplTest.class);
        suite.addTestSuite(WEB3MutualTradeOrderNotifyUnitServiceImplTest.class);
        suite.addTestSuite(WEB3MutualFixedBuyConditionInputRequestTest.class);
        suite.addTestSuite(WEB3MutualFixedBuyConditionConfirmRequestTest.class);
        suite.addTestSuite(WEB3MutualFixedBuyConditionCompleteRequestTest.class);
        suite.addTestSuite(WEB3MutualFixedBuyConditionServiceInterceptorTest.class);
        suite.addTestSuite(WEB3MutualFixedBuyConditionHandlerTest.class);
        suite.addTestSuite(WEB3AdminMutualConditionsHandlerTest.class);
        suite.addTestSuite(WEB3AdminMutualTPACancelHandlerTest.class);
        suite.addTestSuite(WEB3AdminMutualCategoryRegistChangeRequestTest.class);
        suite.addTestSuite(WEB3AdminMutualConditionsReferenceRequestTest.class);
        suite.addTestSuite(WEB3MutualOrderReferenceRequestTest.class);
        suite.addTestSuite(WEB3AdminMutualDisplayOrderCompleteRequestTest.class);
        suite.addTestSuite(WEB3AdminMutualDisplayOrderInputRequestTest.class);
        suite.addTestSuite(WEB3AdminMutualFrgncalCompleteRequestTest.class);
        suite.addTestSuite(WEB3AdminMutualFrgncalReferenceRequestTest.class);
        suite.addTestSuite(WEB3MutualBuyListRequestTest.class);
        suite.addTestSuite(WEB3MutualSwitchingCompleteRequestTest.class);
        suite.addTestSuite(WEB3MutualFixedBuyApplyServiceInterceptorTest.class);
        suite.addTestSuite(WEB3MutualCancelAcceptUnitServiceInterceptorTest.class);
        suite.addTestSuite(WEB3MutualFundNewOrderApplyConfirmInterceptorTest.class);
        suite.addTestSuite(WEB3MutualFundTradeOrderNotifyConfirmInterceptorTest.class);
        suite.addTestSuite(WEB3MutualFundNewOrderSwtConfirmInterceptorTest.class);
        suite.addTestSuite(WEB3AdminMutualFrgncalServiceImplTest.class);
        suite.addTestSuite(WEB3AdminMutualTPACancelServiceImplTest.class);
        suite.addTestSuite(WEB3AdminMutualTPAdjustServiceImplTest.class);
        suite.addTestSuite(WEB3MutualFixedBuyCloseDateDrawDateCalcTest.class);
        suite.addTestSuite(WEB3AdminMutualTPAdjustCommonRequestTest.class);
        suite.addTestSuite(WEB3MutualCancelAcceptServiceImplTest.class);
        return suite;
    }
}@
