head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.42.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3TriggerOrderAllTests.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : TriggerOrderAllTest.java
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/07 金傑 新規作成
*/
package webbroker3.triggerorder;

import junit.framework.Test;
import junit.framework.TestSuite;

import test.util.DeleteAllTable;
import webbroker3.triggerorder.handler.WEB3ToSuccFuturesCancelOrderHandlerTest;
import webbroker3.triggerorder.handler.WEB3ToSuccFuturesChangeClosingContractHandlerTest;
import webbroker3.triggerorder.handler.WEB3ToSuccFuturesChangeClosingContractInputHandlerTest;
import webbroker3.triggerorder.handler.WEB3ToSuccFuturesChangeOpenContractHandlerTest;
import webbroker3.triggerorder.handler.WEB3ToSuccFuturesChangeOpenContractInputHandlerTest;
import webbroker3.triggerorder.handler.WEB3ToSuccFuturesOpenContractHandlerTest;
import webbroker3.triggerorder.handler.WEB3ToSuccFuturesOpenContractInputHandlerTest;
import webbroker3.triggerorder.handler.WEB3ToSuccFuturesSettleContractInputHandlerTest;
import webbroker3.triggerorder.handler.WEB3ToSuccFuturesSettleContractOrderHandlerTest;
import webbroker3.triggerorder.handler.WEB3ToSuccOptionCancelOrderHandlerTest;
import webbroker3.triggerorder.handler.WEB3ToSuccOptionChangeClosingContractHandlerTest;
import webbroker3.triggerorder.handler.WEB3ToSuccOptionChangeClosingContractInputHandlerTest;
import webbroker3.triggerorder.handler.WEB3ToSuccOptionChangeOpenContractHandlerTest;
import webbroker3.triggerorder.handler.WEB3ToSuccOptionChangeOpenContractInputHandlerTest;
import webbroker3.triggerorder.handler.WEB3ToSuccOptionOpenContractInputHandlerTest;
import webbroker3.triggerorder.handler.WEB3ToSuccOptionOpenContractOrderHandlerTest;
import webbroker3.triggerorder.handler.WEB3ToSuccOptionSettleContractHandlerTest;
import webbroker3.triggerorder.handler.WEB3ToSuccOptionSettleContractInputServiceHandlerTest;
import webbroker3.triggerorder.message.WEB3IfoOrderCarryOverMainRequestTest;
import webbroker3.triggerorder.message.WEB3ManualCommonRequestTest;
import webbroker3.triggerorder.message.WEB3SuccEquityBuyInputRequestTest;
import webbroker3.triggerorder.message.WEB3SuccEquityChangeCompleteRequestTest;
import webbroker3.triggerorder.message.WEB3SuccEquityChangeConfirmRequestTest;
import webbroker3.triggerorder.message.WEB3SuccEquitySellInputRequestTest;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseChangeCompleteRequestTest;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseChangeConfirmRequestTest;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseCompleteRequestTest;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseConfirmRequestTest;
import webbroker3.triggerorder.message.WEB3SuccFuturesCloseInputRequestTest;
import webbroker3.triggerorder.message.WEB3SuccFuturesOpenChangeCompleteRequestTest;
import webbroker3.triggerorder.message.WEB3SuccFuturesOpenChangeConfirmRequestTest;
import webbroker3.triggerorder.message.WEB3SuccFuturesOpenCompleteRequestTest;
import webbroker3.triggerorder.message.WEB3SuccFuturesOpenConfirmRequestTest;
import webbroker3.triggerorder.message.WEB3SuccFuturesOpenInputRequestTest;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseChangeCompleteRequestTest;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseChangeConfirmRequestTest;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseCompleteRequestTest;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseConfirmRequestTest;
import webbroker3.triggerorder.message.WEB3SuccOptionsCloseInputRequestTest;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenChangeCompleteRequestTest;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenChangeConfirmRequestTest;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenCompleteRequestTest;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenConfirmRequestTest;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenInputRequestTest;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesOpenContractRequestAdapterTest;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesSettleContractOrderRequestAdapterTest;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionOpenContractOrderRequestAdapterTest;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionSettleContractOrderRequestAdapterTest;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3AsynAdminToManualExpireServiceImplTest;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3FuturesOrderCarryOverServiceImplTest;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3FuturesOrderCarryOverUnitServiceImplTest;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3IfoOrderCarryOverMainServiceImplTest;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3OptionOrderCarryOverServiceImplTest;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3OptionOrderCarryOverUnitServiceImplTest;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToIfoManualOrderUnitServiceImplTest;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToStopIfoOrderUnitServiceImplTest;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToStopIfoOrderUnitServiceImplTest_createOpenContractChangeSpec;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToStopIfoOrderUnitServiceImplTest_submitFuturesOpenContractStopOrder;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToStopIfoOrderUnitServiceImplTest_submitFuturesSettleContractStopOrder;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToStopIfoOrderUnitServiceImplTest_submitOptionOpenContractStopOrder;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToStopIfoOrderUnitServiceImplTest_submitOptionSettleContractStopOrder;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccDataGettingServiceImplTest;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccEquityChangeOrderServiceImplTest;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesCancelOrderServiceImplTest;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesChangeClosingContractInputServiceImplTest;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesChangeClosingContractServiceImplTest;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesChangeOpenContractInputServiceImplTest;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesChangeOpenContractServiceImplTest;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesOpenContractInputServiceImplTest;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesOpenContractServiceImplTest;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesSettleContractInputServiceImplTest;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesSettleContractOrderServiceImplTest;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccIfoOrderCarryOverServiceImplTest;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccIfoOrderUnitServiceImplTest;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccMarginChangeCloseMarginServiceImplTest;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionCancelOrderServiceImplTest;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionChangeClosingContractInputServiceImplTest;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionChangeClosingContractServiceImplTest;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionChangeOpenContractInputServiceImplTest;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionChangeOpenContractServiceImplTest;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionOpenContractInputServiceImplTest;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionSettleContractInputServiceImplTest;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionSettleContractOrderServiceImplTest;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOrderServiceImplTest;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccSettingListServiceImplTest;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToWLimitIfoSwitchUnitServiceImplTest;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToWLimitIfoSwitchUnitServiceImplTestEvening_submitOptionSettleContractWLimitOrder;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToWLimitIfoSwitchUnitServiceImplTest_submitFuturesOpenContractWLimitOrder;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToWLimitIfoSwitchUnitServiceImplTest_submitFuturesSettleContractWLimitOrder;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToWLimitIfoSwitchUnitServiceImplTest_submitOptionOpenContractWLimitOrder;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToWLimitIfoSwitchUnitServiceImplTest_submitOptionSettleContractWLimitOrder;

public class WEB3TriggerOrderAllTests
{
	public static Test suite()
	{
		TestSuite suite = new TestSuite("Test for webbroker3.triggerorder");
        suite.addTestSuite(DeleteAllTable.class);
		suite.addTestSuite(WEB3ToSuccEqTypeOrderUnitImplTest.class);
		suite.addTestSuite(WEB3ToSuccFuturesCancelOrderServiceInterceptorTest.class);
		suite.addTestSuite(WEB3ToSuccFuturesChangeClosingContractInputServiceInterceptorTest.class);
		suite.addTestSuite(WEB3ToSuccFuturesChangeClosingContractServiceInterceptorTest.class);
		suite.addTestSuite(WEB3ToSuccFuturesChangeOpenContractInputServiceInterceptorTest.class);
		suite.addTestSuite(WEB3ToSuccFuturesChangeOpenContractServiceInterceptorTest.class);
		suite.addTestSuite(WEB3ToSuccIfoChangeOpenContractOrderSpecTest.class);
		suite.addTestSuite(WEB3ToSuccIfoChangeSettleContractOrderSpecTest.class);
		
		suite.addTestSuite(WEB3ToSuccOptionCancelOrderServiceInterceptorTest.class);
		suite.addTestSuite(WEB3ToSuccOptionChangeClosingContractInputServiceInterceptorTest.class);
		suite.addTestSuite(WEB3ToSuccOptionChangeClosingContractServiceInterceptorTest.class);
		suite.addTestSuite(WEB3ToSuccOptionChangeOpenContractInputServiceInterceptorTest.class);
		suite.addTestSuite(WEB3ToSuccOptionChangeOpenContractServiceInterceptorTest.class);
		suite.addTestSuite(WEB3ToSuccOrderManagerImplTest.class);
		suite.addTestSuite(WEB3ToWLimitIfoSwitchUnitServiceInterceptorTest.class);
		suite.addTestSuite(WEB3ToWLimitIfoSwitchUpdateInterceptorTest.class);
		suite.addTestSuite(WEB3ToSuccFuturesCancelOrderHandlerTest.class);
		suite.addTestSuite(WEB3ToSuccFuturesChangeClosingContractHandlerTest.class);
		suite.addTestSuite(WEB3ToSuccFuturesChangeClosingContractInputHandlerTest.class);
		suite.addTestSuite(WEB3ToSuccFuturesChangeOpenContractHandlerTest.class);
		suite.addTestSuite(WEB3ToSuccFuturesChangeOpenContractInputHandlerTest.class);
		suite.addTestSuite(WEB3ToSuccFuturesOpenContractHandlerTest.class);
		suite.addTestSuite(WEB3ToSuccFuturesOpenContractInputHandlerTest.class);
		suite.addTestSuite(WEB3ToSuccFuturesSettleContractInputHandlerTest.class);
		suite.addTestSuite(WEB3ToSuccFuturesSettleContractOrderHandlerTest.class);
		suite.addTestSuite(WEB3ToSuccOptionCancelOrderHandlerTest.class);
		suite.addTestSuite(WEB3ToSuccOptionChangeClosingContractHandlerTest.class);
		suite.addTestSuite(WEB3ToSuccOptionChangeClosingContractInputHandlerTest.class);
		suite.addTestSuite(WEB3ToSuccOptionChangeOpenContractHandlerTest.class);
		suite.addTestSuite(WEB3ToSuccOptionChangeOpenContractInputHandlerTest.class);
		suite.addTestSuite(WEB3ToSuccOptionOpenContractInputHandlerTest.class);
		suite.addTestSuite(WEB3ToSuccOptionOpenContractOrderHandlerTest.class);
		suite.addTestSuite(WEB3ToSuccOptionSettleContractHandlerTest.class);
		suite.addTestSuite(WEB3ToSuccOptionSettleContractInputServiceHandlerTest.class);
		suite.addTestSuite(WEB3SuccFuturesCloseChangeCompleteRequestTest.class);
		suite.addTestSuite(WEB3SuccFuturesCloseChangeConfirmRequestTest.class);
		suite.addTestSuite(WEB3SuccFuturesCloseCompleteRequestTest.class);
		suite.addTestSuite(WEB3SuccFuturesCloseConfirmRequestTest.class);
		suite.addTestSuite(WEB3SuccFuturesCloseInputRequestTest.class);
		suite.addTestSuite(WEB3SuccFuturesOpenChangeCompleteRequestTest.class);
		suite.addTestSuite(WEB3SuccFuturesOpenChangeConfirmRequestTest.class);
		suite.addTestSuite(WEB3SuccFuturesOpenCompleteRequestTest.class);
		suite.addTestSuite(WEB3SuccFuturesOpenConfirmRequestTest.class);
		suite.addTestSuite(WEB3SuccFuturesOpenInputRequestTest.class);
		suite.addTestSuite(WEB3SuccOptionsCloseChangeCompleteRequestTest.class);
		suite.addTestSuite(WEB3SuccOptionsCloseChangeConfirmRequestTest.class);
		suite.addTestSuite(WEB3SuccOptionsCloseCompleteRequestTest.class);
		suite.addTestSuite(WEB3SuccOptionsCloseConfirmRequestTest.class);
		suite.addTestSuite(WEB3SuccOptionsCloseInputRequestTest.class);
		suite.addTestSuite(WEB3SuccOptionsOpenChangeCompleteRequestTest.class);
		suite.addTestSuite(WEB3SuccOptionsOpenChangeConfirmRequestTest.class);
		suite.addTestSuite(WEB3SuccOptionsOpenCompleteRequestTest.class);
		suite.addTestSuite(WEB3SuccOptionsOpenConfirmRequestTest.class);
		suite.addTestSuite(WEB3SuccOptionsOpenInputRequestTest.class);
		suite.addTestSuite(WEB3ToSuccFuturesOpenContractRequestAdapterTest.class);
		suite.addTestSuite(WEB3ToSuccFuturesSettleContractOrderRequestAdapterTest.class);
		suite.addTestSuite(WEB3ToSuccOptionOpenContractOrderRequestAdapterTest.class);
		suite.addTestSuite(WEB3ToSuccOptionSettleContractOrderRequestAdapterTest.class);
		suite.addTestSuite(WEB3AsynAdminToManualExpireServiceImplTest.class);
		suite.addTestSuite(WEB3FuturesOrderCarryOverServiceImplTest.class);
		suite.addTestSuite(WEB3FuturesOrderCarryOverUnitServiceImplTest.class);
		suite.addTestSuite(WEB3IfoOrderCarryOverMainServiceImplTest.class);
		suite.addTestSuite(WEB3OptionOrderCarryOverServiceImplTest.class);
		suite.addTestSuite(WEB3OptionOrderCarryOverUnitServiceImplTest.class);
		suite.addTestSuite(WEB3ToIfoManualOrderUnitServiceImplTest.class);
		suite.addTestSuite(WEB3ToSuccIfoOrderUnitServiceImplTest.class);
		suite.addTestSuite(WEB3ToSuccIfoOrderUnitServiceInterceptorTest.class);
		suite.addTestSuite(WEB3ToStopIfoOrderUnitServiceImplTest.class);
		suite.addTestSuite(WEB3ToStopIfoOrderUnitServiceImplTest_createOpenContractChangeSpec.class);
		suite.addTestSuite(WEB3ToStopIfoOrderUnitServiceImplTest_submitFuturesOpenContractStopOrder.class);
		suite.addTestSuite(WEB3ToStopIfoOrderUnitServiceImplTest_submitFuturesSettleContractStopOrder.class);
		suite.addTestSuite(WEB3ToStopIfoOrderUnitServiceImplTest_submitOptionOpenContractStopOrder.class);
		suite.addTestSuite(WEB3ToStopIfoOrderUnitServiceImplTest_submitOptionSettleContractStopOrder.class);
		suite.addTestSuite(WEB3ToSuccDataGettingServiceImplTest.class);
		suite.addTestSuite(WEB3ToSuccEquityChangeOrderServiceImplTest.class);
		suite.addTestSuite(WEB3ToSuccFuturesCancelOrderServiceImplTest.class);
		suite.addTestSuite(WEB3ToSuccFuturesChangeClosingContractInputServiceImplTest.class);
		suite.addTestSuite(WEB3ToSuccFuturesChangeClosingContractServiceImplTest.class);
		suite.addTestSuite(WEB3ToSuccFuturesChangeOpenContractInputServiceImplTest.class);
		suite.addTestSuite(WEB3ToSuccFuturesChangeOpenContractServiceImplTest.class);
		
		
		suite.addTestSuite(WEB3ToSuccFuturesSettleContractOrderServiceImplTest.class);
		
		suite.addTestSuite(WEB3ToSuccMarginChangeCloseMarginServiceImplTest.class);
		
		suite.addTestSuite(WEB3ToSuccOptionChangeClosingContractInputServiceImplTest.class);
		suite.addTestSuite(WEB3ToSuccOptionChangeClosingContractServiceImplTest.class);
		suite.addTestSuite(WEB3ToSuccOptionChangeOpenContractInputServiceImplTest.class);
		suite.addTestSuite(WEB3ToSuccOptionChangeOpenContractServiceImplTest.class);
		suite.addTestSuite(WEB3ToSuccOptionOpenContractInputServiceImplTest.class);
		suite.addTestSuite(WEB3ToSuccOrderServiceImplTest.class);
		
		
		suite.addTestSuite(WEB3ToWLimitIfoSwitchUnitServiceImplTestEvening_submitOptionSettleContractWLimitOrder.class);
		suite.addTestSuite(WEB3ToWLimitIfoSwitchUnitServiceImplTest_submitFuturesOpenContractWLimitOrder.class);
		suite.addTestSuite(WEB3ToWLimitIfoSwitchUnitServiceImplTest_submitFuturesSettleContractWLimitOrder.class);
		suite.addTestSuite(WEB3ToWLimitIfoSwitchUnitServiceImplTest_submitOptionOpenContractWLimitOrder.class);
		suite.addTestSuite(WEB3ToWLimitIfoSwitchUnitServiceImplTest_submitOptionSettleContractWLimitOrder.class);
        
        suite.addTestSuite(WEB3ToSuccIfoOrderUnitImplTest.class);
        suite.addTestSuite(WEB3ToWLimitIfoSwitchUnitServiceImplTest.class);
        //
        suite.addTestSuite(WEB3ToSuccSettingListServiceImplTest.class);
        suite.addTestSuite(WEB3ToSuccOptionSettleContractOrderServiceImplTest.class);
        suite.addTestSuite(WEB3ToSuccOptionSettleContractInputServiceImplTest.class);
        suite.addTestSuite(WEB3ToSuccFuturesSettleContractInputServiceImplTest.class);
        suite.addTestSuite(WEB3ToSuccFuturesOpenContractInputServiceImplTest.class);
        suite.addTestSuite(WEB3ToSuccFuturesOpenContractServiceImplTest.class);
        suite.addTestSuite(WEB3ToSuccIfoOrderCarryOverServiceImplTest.class);

        suite.addTestSuite(WEB3ToSuccOptionCancelOrderServiceImplTest.class);
        suite.addTestSuite(WEB3IfoOrderCarryOverMainRequestTest.class);
        
        suite.addTestSuite(WEB3ManualCommonRequestTest.class);
        suite.addTestSuite(WEB3SuccEquitySellInputRequestTest.class);
        suite.addTestSuite(WEB3SuccEquityChangeConfirmRequestTest.class);
        suite.addTestSuite(WEB3SuccEquityChangeCompleteRequestTest.class);
        suite.addTestSuite(WEB3SuccEquityBuyInputRequestTest.class);
        suite.addTestSuite(WEB3ToStopIfoOrderUpdateInterceptorTest.class);
		return suite;
	}
}
@
