head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.13.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityAllTests.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3EuityAllTest.java
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/01/22　@金傑(中訊) 新規作成
 */
package webbroker3.equity;

import junit.framework.Test;
import junit.framework.TestSuite;

import webbroker3.equity.handler.WEB3EquityAssetInquiryHandlerTest;
import webbroker3.equity.handler.WEB3EquityBalanceReferenceHandlerTest;
import webbroker3.equity.handler.WEB3EquityCancelOrderHandlerTest;
import webbroker3.equity.handler.WEB3EquityChangeOrderHandlerTest;
import webbroker3.equity.handler.WEB3EquityChangeOrderInputHandlerTest;
import webbroker3.equity.handler.WEB3EquityMarginExecuteReferenceHandlerTest;
import webbroker3.equity.handler.WEB3EquityOrderHandlerTest;
import webbroker3.equity.handler.WEB3EquityPTSExecEndNotifyHandlerTest;
import webbroker3.equity.marketadaptor.WEB3EquityMarketRequestSenderServiceImplTest;
import webbroker3.equity.message.WEB3EquityBalanceReferenceRequestTest;
import webbroker3.equity.message.WEB3EquityBuyCompleteRequestTest;
import webbroker3.equity.message.WEB3EquityBuyConfirmRequestTest;
import webbroker3.equity.message.WEB3EquityBuyInputRequestTest;
import webbroker3.equity.message.WEB3EquityChangeCancelAcceptRequestTest;
import webbroker3.equity.message.WEB3EquityChangeCompleteRequestTest;
import webbroker3.equity.message.WEB3EquityChangeConfirmRequestTest;
import webbroker3.equity.message.WEB3EquityExecEndNotifyRequestTest;
import webbroker3.equity.message.WEB3EquityExecuteReferenceRequestTest;
import webbroker3.equity.message.WEB3EquityMarginExecuteReferenceRequestTest;
import webbroker3.equity.message.WEB3EquityMarginSortKeyTest;
import webbroker3.equity.message.WEB3EquityOffFloorProductListRequestTest;
import webbroker3.equity.message.WEB3EquityPTSExecEndNotifyRequestTest;
import webbroker3.equity.message.WEB3EquityProductInformationRequestTest;
import webbroker3.equity.message.WEB3EquitySellCompleteRequestTest;
import webbroker3.equity.message.WEB3EquitySellConfirmRequestTest;
import webbroker3.equity.message.WEB3EquitySellInputRequestTest;
import webbroker3.equity.message.WEB3EquitySellListRequestTest;
import webbroker3.equity.message.WEB3MarginCloseMarginChangeCompleteRequestTest;
import webbroker3.equity.message.WEB3MarginCloseMarginCompleteRequestTest;
import webbroker3.equity.message.WEB3MarginCloseMarginConfirmRequestTest;
import webbroker3.equity.message.WEB3MarginExecuteReferenceRequestTest;
import webbroker3.equity.message.WEB3MstkBookPriceRegistRequestTest;
import webbroker3.equity.message.WEB3MstkCancelCompleteRequestTest;
import webbroker3.equity.message.WEB3MstkSellConfirmRequestTest;
import webbroker3.equity.service.delegate.WEB3EquityChangeOrderRequestAdapterTest;
import webbroker3.equity.service.delegate.WEB3EquityOrderRequestAdapterTest;
import webbroker3.equity.service.delegate.WEB3EquityPTSChangeOrderRequestAdapterTest;
import webbroker3.equity.service.delegate.WEB3MarginChangeCloseMarginRequestAdapterTest;
import webbroker3.equity.service.delegate.WEB3MarginChangeOpenMarginRequestAdapterTest;
import webbroker3.equity.service.delegate.WEB3MarginCloseMarginRequestAdapterTest;
import webbroker3.equity.service.delegate.WEB3MarginOpenMarginRequestAdapterTest070619;
import webbroker3.equity.service.delegate.stdimpls.WEB3AsynEquityChangeCancelNotifyMainServiceImplTest;
import webbroker3.equity.service.delegate.stdimpls.WEB3AsynEquityOrderExecNotifyMainServiceImplTest;
import webbroker3.equity.service.delegate.stdimpls.WEB3AsynEquityReceiveCloseOrderServiceImplTest;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityBookValuePriceRegistServiceImplTest;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityChangeOrderServiceImplTest;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityExecuteReferenceServiceImplTest;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityFrontOrderServiceImplTest;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityMarginExecuteReferenceServiceImplTest;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityMarginExecuteReferenceServiceImplTest_GetContractPrice;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityMarginExecuteReferenceServiceImplTest_GetOrderBizDateList;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityMarginExecuteReferenceServiceImplTest_createExecuteUnits;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityMarginExecuteReferenceServiceImplTest_cyp;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityMarginExecuteReferenceServiceImplTest_execute;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityMarginExecuteReferenceServiceImplTest_getOpenDate;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityMarginExecuteReferenceServiceImplTest_isDesignateExecutedStatus;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityMarginExecuteReferenceServiceImpl_createExecuteGroupTest;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityMarginExecuteReferenceServiceImpl_createExecuteGroupTest070426;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityMarginExecuteReferenceServiceImpl_createQueryStringTest;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityOrderAcceptUnitServiceImplTest;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityOrderBuyInputServiceImplTest;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityOrderCarryOverServiceImplTest;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityOrderCarryOverSkipUnitServiceImplTest;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityOrderCarryOverUnitServiceImplTest;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityOrderExecEndNotifyServiceImplTest;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityOrderExecNotifyTransactionCallbackTest;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityOrderNotifyServiceImplTest;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityOrderNotifyUnitServiceImplTest;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityOrderServiceImplTest;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityPTSCancelOrderServiceImplTest;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityPTSChangeOrderInputServiceImplTest;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityPTSChangeOrderServiceImplTest;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityPTSExecEndNotifyServiceImplTest;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityPTSOrderServiceImplTest;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityProductInformationServiceImplTest;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityReceiveChangeEventServiceImplTest;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityReceiveCloseOrderServiceImplTest;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityReceiveCloseOrderUnitServiceImplTest;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquitySellOrderInputServiceImplTest;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginCancelServiceImplTest;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginChangeCancelNotifyChangeUnitServiceImplTest;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginChangeCloseMarginInputServiceImplTest;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginChangeCloseMarginServiceImplTest;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginChangeOpenMarginServiceImplTest;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginCloseMarginServiceImplTest;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginExecuteReferenceServiceImplTest;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginOpenMarginServiceImplTest070619;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginOrderNotifyServiceImplTest;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginOrderNotifyUnitServiceImplTest;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginSwapMarginAcceptServiceImplTest;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginSwapMarginServiceImplTest;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginSwapOrderNotifyServiceImplTest;
import webbroker3.equity.service.delegate.stdimpls.WEB3MstkCancelServiceImplTest;

public class WEB3EquityAllTests
{
	
	public static Test suite()
	{
		TestSuite suite = new TestSuite();
        suite.addTestSuite(WEB3EquityAppPluginTest.class);
        suite.addTestSuite(WEB3EquityBalanceReferenceServiceInterceptorTest.class);
        suite.addTestSuite(WEB3EquityChangeOrderSpecTest.class);
        suite.addTestSuite(WEB3EquityContractTest.class);
        suite.addTestSuite(WEB3EquityDataAdapterTest.class);
        suite.addTestSuite(WEB3EquityMarginExecuteReferenceServiceInterceptorTest.class);
        suite.addTestSuite(WEB3EquityNewCashBasedOrderSpecTest.class);
        suite.addTestSuite(WEB3EquityOrderBuyInputServiceInterceptorTest.class);
        suite.addTestSuite(WEB3EquityOrderManagerChangeOrderEventInterceptorTest.class);
        suite.addTestSuite(WEB3EquityOrderManagerTest_hewenmin20070608.class);
        suite.addTestSuite(WEB3EquityOrderManagerTest_T05151EQ.class);
        suite.addTestSuite(WEB3EquityOrderManagerTest.class);
        suite.addTestSuite(WEB3EquityOrderManagerTest070619.class);
        suite.addTestSuite(WEB3EquityPositionManagerTest.class);
        suite.addTestSuite(WEB3EquityProductManagerTest.class);
        suite.addTestSuite(WEB3EquityProductTest070619.class);
        suite.addTestSuite(WEB3EquityPTSCancelOrderServiceInterceptorTest.class);
        suite.addTestSuite(WEB3EquityPTSChangeOrderInputServiceInterceptorTest.class);
        suite.addTestSuite(WEB3EquityPTSChangeOrderServiceInterceptorTest.class);
        suite.addTestSuite(WEB3EquityPTSOrderManagerReusableValidationsTest.class);
        suite.addTestSuite(WEB3EquityPTSOrderManagerTest.class);
        suite.addTestSuite(WEB3EquityPTSOrderServiceInterceptorTest.class);
        suite.addTestSuite(WEB3EquityPTSTradingTimeManagementTest.class);
        suite.addTestSuite(WEB3EquitySellOrderInputServiceInterceptorTest.class);
        suite.addTestSuite(WEB3EquityTradedProductTest.class);
        suite.addTestSuite(WEB3EquityTypeOrderManagerReusableValidationsTest.class);
        suite.addTestSuite(WEB3EquityTypeOrderManagerReusableValidationsTest070619.class);
        suite.addTestSuite(WEB3MarginBalanceReferenceServiceInterceptorTest.class);
        suite.addTestSuite(WEB3MarginCancelServiceInterceptorTest.class);
        suite.addTestSuite(WEB3MarginChangeCloseMarginInputServiceInterceptorTest.class);
        suite.addTestSuite(WEB3MarginChangeCloseMarginServiceInterceptorTest.class);
        suite.addTestSuite(WEB3MarginCloseMarginInputServiceInterceptorTest.class);
        suite.addTestSuite(WEB3MarginCloseMarginListServiceInterceptorTest.class);
        suite.addTestSuite(WEB3MarginCloseMarginServiceInterceptorTest.class);
        suite.addTestSuite(WEB3MarginCloseMarginUpdateInterceptorTest.class);
        suite.addTestSuite(WEB3MiniStockOrderUpdateInterceptorTest.class);
        suite.addTestSuite(WEB3EquityCancelOrderHandlerTest.class);
        suite.addTestSuite(WEB3EquityChangeOrderHandlerTest.class);
        suite.addTestSuite(WEB3EquityChangeOrderInputHandlerTest.class);
        suite.addTestSuite(WEB3EquityMarginExecuteReferenceHandlerTest.class);
        suite.addTestSuite(WEB3EquityOrderHandlerTest.class);
        suite.addTestSuite(WEB3EquityPTSExecEndNotifyHandlerTest.class);
        suite.addTestSuite(WEB3EquityMarketRequestSenderServiceImplTest.class);
        suite.addTestSuite(WEB3EquityBalanceReferenceRequestTest.class);
        suite.addTestSuite(WEB3EquityBuyCompleteRequestTest.class);
        suite.addTestSuite(WEB3EquityBuyConfirmRequestTest.class);
        suite.addTestSuite(WEB3EquityBuyInputRequestTest.class);
        suite.addTestSuite(WEB3EquityExecuteReferenceRequestTest.class);
        suite.addTestSuite(WEB3EquityMarginExecuteReferenceRequestTest.class);
        suite.addTestSuite(WEB3EquityMarginSortKeyTest.class);
        suite.addTestSuite(WEB3EquityProductInformationRequestTest.class);
        suite.addTestSuite(WEB3EquityPTSExecEndNotifyRequestTest.class);
        suite.addTestSuite(WEB3EquitySellCompleteRequestTest.class);
        suite.addTestSuite(WEB3EquitySellConfirmRequestTest.class);
        suite.addTestSuite(WEB3EquitySellInputRequestTest.class);
        suite.addTestSuite(WEB3MarginCloseMarginCompleteRequestTest.class);
        suite.addTestSuite(WEB3MarginCloseMarginConfirmRequestTest.class);
        suite.addTestSuite(WEB3MarginExecuteReferenceRequestTest.class);
        suite.addTestSuite(WEB3EquityChangeOrderRequestAdapterTest.class);
        suite.addTestSuite(WEB3EquityOrderRequestAdapterTest.class);
        suite.addTestSuite(WEB3EquityPTSChangeOrderRequestAdapterTest.class);
        suite.addTestSuite(WEB3MarginChangeCloseMarginRequestAdapterTest.class);
        suite.addTestSuite(WEB3MarginChangeOpenMarginRequestAdapterTest.class);
        suite.addTestSuite(WEB3MarginCloseMarginRequestAdapterTest.class);
        suite.addTestSuite(WEB3MarginOpenMarginRequestAdapterTest070619.class);
        suite.addTestSuite(WEB3AsynEquityChangeCancelNotifyMainServiceImplTest.class);
        suite.addTestSuite(WEB3AsynEquityOrderExecNotifyMainServiceImplTest.class);
        suite.addTestSuite(WEB3AsynEquityReceiveCloseOrderServiceImplTest.class);
        suite.addTestSuite(WEB3EquityChangeOrderServiceImplTest.class);
        suite.addTestSuite(WEB3EquityExecuteReferenceServiceImplTest.class);
        suite.addTestSuite(WEB3EquityFrontOrderServiceImplTest.class);
        suite.addTestSuite(WEB3EquityMarginExecuteReferenceServiceImpl_createExecuteGroupTest.class);
        suite.addTestSuite(WEB3EquityMarginExecuteReferenceServiceImpl_createExecuteGroupTest070426.class);
        suite.addTestSuite(WEB3EquityMarginExecuteReferenceServiceImpl_createQueryStringTest.class);
        suite.addTestSuite(WEB3EquityMarginExecuteReferenceServiceImplTest_createExecuteUnits.class);
        suite.addTestSuite(WEB3EquityMarginExecuteReferenceServiceImplTest_cyp.class);
        suite.addTestSuite(WEB3EquityMarginExecuteReferenceServiceImplTest_execute.class);
        suite.addTestSuite(WEB3EquityMarginExecuteReferenceServiceImplTest_GetContractPrice.class);
        suite.addTestSuite(WEB3EquityMarginExecuteReferenceServiceImplTest_getOpenDate.class);
        suite.addTestSuite(WEB3EquityMarginExecuteReferenceServiceImplTest_GetOrderBizDateList.class);
        suite.addTestSuite(WEB3EquityMarginExecuteReferenceServiceImplTest_isDesignateExecutedStatus.class);
        suite.addTestSuite(WEB3EquityMarginExecuteReferenceServiceImplTest.class);
        suite.addTestSuite(WEB3EquityOrderBuyInputServiceImplTest.class);
        suite.addTestSuite(WEB3EquityOrderCarryOverServiceImplTest.class);
        suite.addTestSuite(WEB3EquityOrderCarryOverUnitServiceImplTest.class);
        suite.addTestSuite(WEB3EquityOrderExecEndNotifyServiceImplTest.class);
        suite.addTestSuite(WEB3EquityOrderNotifyServiceImplTest.class);
        suite.addTestSuite(WEB3EquityOrderServiceImplTest.class);
        suite.addTestSuite(WEB3EquityProductInformationServiceImplTest.class);
        suite.addTestSuite(WEB3EquityPTSCancelOrderServiceImplTest.class);
        suite.addTestSuite(WEB3EquityPTSChangeOrderInputServiceImplTest.class);
        suite.addTestSuite(WEB3EquityPTSChangeOrderServiceImplTest.class);
        suite.addTestSuite(WEB3EquityPTSExecEndNotifyServiceImplTest.class);
        suite.addTestSuite(WEB3EquityPTSOrderServiceImplTest.class);
        suite.addTestSuite(WEB3EquityReceiveCloseOrderUnitServiceImplTest.class);
        suite.addTestSuite(WEB3EquitySellOrderInputServiceImplTest.class);
        suite.addTestSuite(WEB3MarginCancelServiceImplTest.class);
        suite.addTestSuite(WEB3MarginChangeCloseMarginInputServiceImplTest.class);
        suite.addTestSuite(WEB3MarginChangeCloseMarginServiceImplTest.class);
        suite.addTestSuite(WEB3MarginChangeOpenMarginServiceImplTest.class);
        suite.addTestSuite(WEB3MarginCloseMarginServiceImplTest.class);
        suite.addTestSuite(WEB3MarginExecuteReferenceServiceImplTest.class);
        suite.addTestSuite(WEB3MarginOpenMarginServiceImplTest070619.class);
        suite.addTestSuite(WEB3MarginOrderNotifyServiceImplTest.class);
        suite.addTestSuite(WEB3MarginSwapMarginServiceImplTest.class);
        suite.addTestSuite(WEB3MarginSwapOrderNotifyServiceImplTest.class);
        suite.addTestSuite(WEB3EquityChangeCancelAcceptRequestTest.class);
        suite.addTestSuite(WEB3EquityChangeCompleteRequestTest.class);
        suite.addTestSuite(WEB3EquityChangeConfirmRequestTest.class);
        suite.addTestSuite(WEB3EquityExecEndNotifyRequestTest.class);
        suite.addTestSuite(WEB3EquityOffFloorProductListRequestTest.class);
        suite.addTestSuite(WEB3EquitySellListRequestTest.class);
        suite.addTestSuite(WEB3MarginCloseMarginChangeCompleteRequestTest.class);
        suite.addTestSuite(WEB3MstkBookPriceRegistRequestTest.class);
        suite.addTestSuite(WEB3MstkCancelCompleteRequestTest.class);
        suite.addTestSuite(WEB3MstkSellConfirmRequestTest.class);
        suite.addTestSuite(WEB3EquityAssetInquiryServiceInterceptorTest.class);
        suite.addTestSuite(WEB3EquityBookValuePriceRegistServiceInterceptorTest.class);
        suite.addTestSuite(WEB3MarginSwapMarginUpdateInterceptorTest.class);
        suite.addTestSuite(WEB3MiniStockCancelUpdateInterceptorTest.class);
        suite.addTestSuite(WEB3EquityAssetInquiryHandlerTest.class);
        suite.addTestSuite(WEB3EquityBalanceReferenceHandlerTest.class);
        suite.addTestSuite(WEB3MstkSellServiceInterceptorTest.class);
        suite.addTestSuite(WEB3EquityBookValuePriceRegistServiceImplTest.class);
        suite.addTestSuite(WEB3EquityOrderAcceptUnitServiceImplTest.class);
        suite.addTestSuite(WEB3EquityOrderExecNotifyTransactionCallbackTest.class);
        suite.addTestSuite(WEB3EquityOrderNotifyUnitServiceImplTest.class);
        suite.addTestSuite(WEB3EquityReceiveChangeEventServiceImplTest.class);
        suite.addTestSuite(WEB3EquityReceiveCloseOrderServiceImplTest.class);
        suite.addTestSuite(WEB3MarginChangeCancelNotifyChangeUnitServiceImplTest.class);
        suite.addTestSuite(WEB3MarginOrderNotifyUnitServiceImplTest.class);
        suite.addTestSuite(WEB3MarginSwapMarginAcceptServiceImplTest.class);
        suite.addTestSuite(WEB3MstkCancelServiceImplTest.class);
        suite.addTestSuite(WEB3EquityOrderCarryOverSkipUnitServiceImplTest.class);
        return suite;
	}
}
@
