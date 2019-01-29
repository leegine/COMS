head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.02.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3XbbdAllTests.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.bd;

import junit.framework.Test;
import junit.framework.TestSuite;

import webbroker3.bd.handler.WEB3AdminBondDomesticProductListHandlerTest;
import webbroker3.bd.handler.WEB3AdminBondDomesticProductRegistHandlerTest;
import webbroker3.bd.handler.WEB3AdminBondDomesticRecruitLimitManageHandlerTest;
import webbroker3.bd.handler.WEB3AdminBondOrderReceiveHistoryHandlerTest;
import webbroker3.bd.handler.WEB3BondBalanceReferenceHandlerTest;
import webbroker3.bd.handler.WEB3BondDomesticApplyHandlerTest;
import webbroker3.bd.handler.WEB3BondDomesticApplyInputHandlerTest;
import webbroker3.bd.handler.WEB3BondDomesticApplyProductListHandlerTest;
import webbroker3.bd.message.WEB3AdminBondDomesticProductListDisplayRequestTest;
import webbroker3.bd.message.WEB3AdminBondDomesticProductListSearchConditionUnitTest;
import webbroker3.bd.message.WEB3AdminBondDomesticProductRegistCommonRequestTest;
import webbroker3.bd.message.WEB3AdminBondDomesticProductRegistCompleteRequestTest;
import webbroker3.bd.message.WEB3AdminBondDomesticProductRegistInputRequestTest;
import webbroker3.bd.message.WEB3AdminBondDomesticRecruitLimitManageCommonRequestTest;
import webbroker3.bd.message.WEB3AdminBondDomesticRecruitLimitManageCompleteRequestTest;
import webbroker3.bd.message.WEB3AdminBondDomesticRecruitLimitManageInputRequestTest;
import webbroker3.bd.message.WEB3AdminBondOrderExecInfoTest;
import webbroker3.bd.message.WEB3AdminBondOrderReceiveHistoryRequestTest;
import webbroker3.bd.message.WEB3BondApplyBuyCommonRequestTest;
import webbroker3.bd.message.WEB3BondApplyBuyProductListRequestTest;
import webbroker3.bd.message.WEB3BondBalanceReferenceCommonRequestTest;
import webbroker3.bd.message.WEB3BondBalanceReferenceRequestTest;
import webbroker3.bd.message.WEB3BondDomesticApplyCommonRequestTest;
import webbroker3.bd.message.WEB3BondDomesticApplyCompleteRequestTest;
import webbroker3.bd.message.WEB3BondDomesticApplyInputRequestTest;
import webbroker3.bd.message.WEB3BondDomesticApplyProductListRequestTest;
import webbroker3.bd.message.WEB3BondDomesticProductUpdateInfoTest;
import webbroker3.bd.message.WEB3BondExecuteReferenceRequestTest;
import webbroker3.bd.message.WEB3BondSellCommonRequestTest;
import webbroker3.bd.message.WEB3BondSellCompleteRequestTest;
import webbroker3.bd.service.delegate.stdimpls.WEB3AdminBondDomesticProductListServiceImplTest;
import webbroker3.bd.service.delegate.stdimpls.WEB3AdminBondDomesticProductRegistServiceImplTest;
import webbroker3.bd.service.delegate.stdimpls.WEB3AdminBondDomesticRecruitLimitManageServiceImplTest;
import webbroker3.bd.service.delegate.stdimpls.WEB3AdminBondExecuteCancelServiceImplTest;
import webbroker3.bd.service.delegate.stdimpls.WEB3AdminBondHelperServiceImplTest;
import webbroker3.bd.service.delegate.stdimpls.WEB3AdminBondHelperServiceImplTest_070510;
import webbroker3.bd.service.delegate.stdimpls.WEB3AdminBondOrderAndExecuteServiceImplTest_070510;
import webbroker3.bd.service.delegate.stdimpls.WEB3AdminBondOrderReceiveHistoryServiceImplTest;
import webbroker3.bd.service.delegate.stdimpls.WEB3AdminBondProductListServiceImplTest_070509;
import webbroker3.bd.service.delegate.stdimpls.WEB3AdminBondProductRegisterServiceImplTest_070509;
import webbroker3.bd.service.delegate.stdimpls.WEB3BondBalanceReferenceServiceImplTest;
import webbroker3.bd.service.delegate.stdimpls.WEB3BondCancelServiceImplTest;
import webbroker3.bd.service.delegate.stdimpls.WEB3BondDomesticApplyInputServiceImplTest;
import webbroker3.bd.service.delegate.stdimpls.WEB3BondDomesticApplyProductListServiceImplTest;
import webbroker3.bd.service.delegate.stdimpls.WEB3BondDomesticApplyServiceImplTest;
import webbroker3.bd.service.delegate.stdimpls.WEB3BondExecuteReferenceServiceImplTest;
import webbroker3.bd.service.delegate.stdimpls.WEB3BondExecuteReferenceServiceImplTest_20070514;
import webbroker3.bd.service.delegate.stdimpls.WEB3BondRecruitBuyInputServiceImplTest;
import webbroker3.bd.service.delegate.stdimpls.WEB3BondRecruitBuyProductListServiceImplTest;
import webbroker3.bd.service.delegate.stdimpls.WEB3BondRecruitBuyServiceImplTest;
import webbroker3.bd.service.delegate.stdimpls.WEB3BondSellInputServiceImplTest;
import webbroker3.bd.service.delegate.stdimpls.WEB3BondSellServiceImplTest;

public class WEB3XbbdAllTests
{
    public static Test suite()
    {
        TestSuite suite = new TestSuite("Test for webbroker3.xbbd");

        //$JUnit-BEGIN$
        suite.addTestSuite(WEB3BondExecuteReferenceServiceImplTest.class);
        suite.addTestSuite(WEB3BondBalanceReferenceRequestTest.class);
        suite.addTestSuite(WEB3BondBalanceReferenceServiceImplTest.class);
        suite.addTestSuite(WEB3AdminBondOrderCancelUpdateInterceptorTest.class);
        suite.addTestSuite(WEB3BondProductTest.class);
        suite.addTestSuite(WEB3AdminBondOrderExecInfoTest.class);
        suite.addTestSuite(WEB3AdminBondHelperServiceImplTest.class);

        //<-----------------200705---------------------------------->
        suite.addTestSuite(WEB3BondApplyBuyCommonRequestTest.class);
        suite.addTestSuite(WEB3BondApplyBuyProductListRequestTest.class);
        suite.addTestSuite(WEB3BondSellCommonRequestTest.class);
        suite.addTestSuite(WEB3BondSellCompleteRequestTest.class);

        suite.addTestSuite(WEB3AdminBondProductListServiceImplTest_070509.class);
        suite.addTestSuite(WEB3AdminBondProductRegisterServiceImplTest_070509.class);
        suite.addTestSuite(WEB3BondRecruitBuyInputServiceImplTest.class);
        suite.addTestSuite(WEB3BondOrderManagerTest.class);
        suite.addTestSuite(WEB3BondProductTest_070509.class);
        suite.addTestSuite(WEB3BondRecruitBuyProductListServiceImplTest.class);
        suite.addTestSuite(WEB3BondSellInputServiceImplTest.class);
        suite.addTestSuite(WEB3BondCancelServiceImplTest.class);

        suite.addTestSuite(WEB3AdminBondOrderAndExecuteServiceImplTest_070510.class);
        suite.addTestSuite(WEB3AdminBondHelperServiceImplTest_070510.class);
        suite.addTestSuite(WEB3BondSellServiceImplTest.class);
        suite.addTestSuite(WEB3AdminBondDomesticProductRegistServiceImplTest.class);
        suite.addTestSuite(WEB3AdminBondDomesticProductRegistHandlerTest.class);
        suite.addTestSuite(WEB3AdminBondDomesticProductRegistServiceInterceptorTest.class);
        suite.addTestSuite(WEB3AdminBondDomesticProductListHandlerTest.class);
        suite.addTestSuite(WEB3AdminBondDomesticProductListDisplayRequestTest.class);
        suite.addTestSuite(WEB3AdminBondDomesticProductRegistCompleteRequestTest.class);
        suite.addTestSuite(WEB3AdminBondDomesticProductRegistInputRequestTest.class);
        suite.addTestSuite(WEB3BondExecuteReferenceRequestTest.class);
        suite.addTestSuite(WEB3BondProductManagerTest.class);
        suite.addTestSuite(WEB3BondTradingTimeManagementTest.class);
        suite.addTestSuite(WEB3AdminBondDomesticProductListSearchConditionUnitTest.class);
        suite.addTestSuite(WEB3AdminBondDomesticProductRegistCommonRequestTest.class);
        suite.addTestSuite(WEB3AdminBondDomesticProductListServiceImplTest.class);
        suite.addTestSuite(WEB3BondExecuteReferenceServiceImplTest_20070514.class);
        suite.addTestSuite(WEB3BondRecruitBuyServiceImplTest.class);
        suite.addTestSuite(WEB3BondOrderTypeJudgeTest.class);
        suite.addTestSuite(WEB3BondDomesticProductUpdateInfoTest.class);
        suite.addTestSuite(WEB3BondBalanceReferenceCommonRequestTest.class);

        suite.addTestSuite(WEB3BondBalanceReferenceHandlerTest.class);

        //wubo
        suite.addTestSuite(WEB3AdminBondExecuteCancelServiceInterceptorTest.class);
        suite.addTestSuite(WEB3BondDomesticApplyServiceInterceptorTest.class);
        suite.addTestSuite(WEB3BondDomesticOrderUpdateInterceptorTest.class);
        suite.addTestSuite(WEB3BondOrderManagerReusableValidationsCheckTest_wubo.class);
        suite.addTestSuite(WEB3BondDomesticApplyHandlerTest.class);
        suite.addTestSuite(WEB3AdminBondExecuteCancelServiceImplTest.class);
        suite.addTestSuite(WEB3BondDomesticApplyServiceImplTest.class);
//        suite.addTestSuite(WEB3XbbdAppPluginTest.class);
        //$JUnit-END$

        suite.addTestSuite(WEB3AdminBondDomesticRecruitLimitManageServiceImplTest.class);
        suite.addTestSuite(WEB3AdminBondDomesticRecruitLimitManageServiceInterceptorTest.class);
        suite.addTestSuite(WEB3AdminBondDomesticRecruitLimitManageHandlerTest.class);
        suite.addTestSuite(WEB3BondDomesticApplyInputServiceInterceptorTest.class);
        suite.addTestSuite(WEB3BondDomesticApplyInputHandlerTest.class);
        suite.addTestSuite(WEB3AdminBondOrderReceiveHistoryHandlerTest.class);

        suite.addTestSuite(WEB3AdminBondOrderReceiveHistoryServiceImplTest.class);
        suite.addTestSuite(WEB3BondDomesticApplyInputServiceImplTest.class);

        suite.addTestSuite(WEB3BondBizLogicProviderTest.class);
        suite.addTestSuite(WEB3BondBranchConditionTest.class);
        suite.addTestSuite(WEB3BondDomesticApplyProductListServiceImplTest.class);

        suite.addTestSuite(WEB3AdminBondDomesticRecruitLimitManageCommonRequestTest.class);
        suite.addTestSuite(WEB3AdminBondDomesticRecruitLimitManageCompleteRequestTest.class);
        suite.addTestSuite(WEB3AdminBondDomesticRecruitLimitManageInputRequestTest.class);
        suite.addTestSuite(WEB3AdminBondOrderReceiveHistoryRequestTest.class);
        suite.addTestSuite(WEB3BondDomesticApplyCommonRequestTest.class);
        suite.addTestSuite(WEB3BondDomesticApplyCompleteRequestTest.class);
        suite.addTestSuite(WEB3BondDomesticApplyInputRequestTest.class);
        suite.addTestSuite(WEB3BondDomesticApplyProductListRequestTest.class);

        suite.addTestSuite(WEB3BondNewOrderSpecTest.class);
        suite.addTestSuite(WEB3AdminBondDomesticOrderCancelUpdateInterceptorTest.class);
        suite.addTestSuite(WEB3BondDomesticApplyProductListServiceInterceptorTest.class);
        suite.addTestSuite(WEB3BondDomesticApplyProductListHandlerTest.class);
        suite.addTestSuite(WEB3BondCancelServiceInterceptorTest.class);

        return suite;
    }
}
@
