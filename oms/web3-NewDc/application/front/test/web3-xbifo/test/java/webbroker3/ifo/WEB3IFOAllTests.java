head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.01.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IFOAllTests.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : (WEB3AIOAllTests.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2007/02/05 周捷 (中訊) 新規作成
 */
package webbroker3.ifo;

import java.util.Date;

import junit.framework.Test;
import junit.framework.TestSuite;
import test.util.DeleteAllTable;

import webbroker3.ifo.handler.WEB3FuturesOpenContractInputHandlerTest;
import webbroker3.ifo.handler.WEB3FuturesSettleContractOrderHandlerTest;
import webbroker3.ifo.handler.WEB3OptionOpenContractInputHandlerTest;
import webbroker3.ifo.handler.WEB3OptionOpenContractOrderHandlerTest;
import webbroker3.ifo.marketadaptor.WEB3IfoMarketRequestSenderServiceImplTest;
import webbroker3.ifo.marketadaptor.WEB3IfoMarketRequestSenderServiceImplTest_ES_send;
import webbroker3.ifo.message.WEB3FuturesCloseMarginChangeCompleteRequestTest;
import webbroker3.ifo.message.WEB3FuturesCloseMarginChangeConfirmRequestTest;
import webbroker3.ifo.message.WEB3FuturesCloseMarginCompleteRequestTest;
import webbroker3.ifo.message.WEB3FuturesCloseMarginConfirmRequestTest;
import webbroker3.ifo.message.WEB3FuturesCloseMarginInputRequestTest;
import webbroker3.ifo.message.WEB3FuturesCloseMarginListRequestTest;
import webbroker3.ifo.message.WEB3FuturesCommonRequestTest;
import webbroker3.ifo.message.WEB3FuturesExecuteReferenceRequestTest;
import webbroker3.ifo.message.WEB3FuturesIndividualCloseMarginListRequestTest;
import webbroker3.ifo.message.WEB3FuturesOpenMarginChangeCompleteRequestTest;
import webbroker3.ifo.message.WEB3FuturesOpenMarginChangeConfirmRequestTest;
import webbroker3.ifo.message.WEB3FuturesOpenMarginCompleteRequestTest;
import webbroker3.ifo.message.WEB3FuturesOpenMarginConfirmRequestTest;
import webbroker3.ifo.message.WEB3FuturesOptionsBalanceReferenceRequestTest;
import webbroker3.ifo.message.WEB3FuturesSessionTypeComparatorTest;
import webbroker3.ifo.message.WEB3IfoExecEndNotifyRequestTest;
import webbroker3.ifo.message.WEB3OptionsCloseMarginChangeCompleteRequestTest;
import webbroker3.ifo.message.WEB3OptionsCloseMarginChangeCompleteRequestTest_OT;
import webbroker3.ifo.message.WEB3OptionsCloseMarginChangeConfirmRequestTest;
import webbroker3.ifo.message.WEB3OptionsCloseMarginChangeConfirmRequestTest_OT;
import webbroker3.ifo.message.WEB3OptionsCloseMarginCompleteRequestTest;
import webbroker3.ifo.message.WEB3OptionsCloseMarginCompleteRequestTest_OT;
import webbroker3.ifo.message.WEB3OptionsCloseMarginConfirmRequestTest;
import webbroker3.ifo.message.WEB3OptionsCloseMarginConfirmRequestTest_OT;
import webbroker3.ifo.message.WEB3OptionsCloseMarginContractListRequestTest;
import webbroker3.ifo.message.WEB3OptionsCloseMarginInputRequestTest;
import webbroker3.ifo.message.WEB3OptionsCloseMarginListRequestTest;
import webbroker3.ifo.message.WEB3OptionsCommonRequestTest;
import webbroker3.ifo.message.WEB3OptionsContractReferenceRequestTest;
import webbroker3.ifo.message.WEB3OptionsExecuteReferenceRequestTest;
import webbroker3.ifo.message.WEB3OptionsIndividualCloseMarginListRequestTest;
import webbroker3.ifo.message.WEB3OptionsOpenDateComparatorTest;
import webbroker3.ifo.message.WEB3OptionsOpenMarginChangeCompleteRequestTest_OT;
import webbroker3.ifo.message.WEB3OptionsOpenMarginChangeConfirmRequestTest_OT;
import webbroker3.ifo.message.WEB3OptionsOpenMarginCompleteRequestTest;
import webbroker3.ifo.message.WEB3OptionsOpenMarginConfirmRequestTest;
import webbroker3.ifo.message.WEB3OptionsOpenMarginInputRequestTest;
import webbroker3.ifo.message.WEB3OptionsProductSelectRequestTest;
import webbroker3.ifo.message.WEB3OptionsSessionTypeComparatorTest;
import webbroker3.ifo.service.delegate.WEB3FuturesOpenContractRequestAdapterTest;
import webbroker3.ifo.service.delegate.WEB3FuturesSettleContractOrderRequestAdapterTest;
import webbroker3.ifo.service.delegate.WEB3OptionOpenContractOrderRequestAdapterTest;
import webbroker3.ifo.service.delegate.WEB3OptionSettleContractOrderRequestAdapterTest;
import webbroker3.ifo.service.delegate.stdimpls.WEB3AsynFuturesChangeCancelNotifyServiceImplTest;
import webbroker3.ifo.service.delegate.stdimpls.WEB3AsynFuturesOrderExecNotifyServiceImplTest;
import webbroker3.ifo.service.delegate.stdimpls.WEB3AsynIfoCloseNotifyServiceImplTest;
import webbroker3.ifo.service.delegate.stdimpls.WEB3AsynOptionChangeCancelNotifyServiceImplTest;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesCancelOrderServiceImplTest;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesChangeCancelNotifyNotifyCancelTransactionCallbackTest;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesChangeCancelNotifyNotifyChangeTransactionCallbackTest;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesChangeCancelNotifyServiceImplTest;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesChangeCancelNotifyUnitServiceImplTest;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesChangeClosingContractInputServiceImplTest;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesChangeClosingContractServiceImplTest;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesChangeOpenContractInputServiceImplTest;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesChangeOpenContractServiceImplTest;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesContractInquiryServiceImplTest;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesExecuteReferenceServiceImplTest_ES;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesIndividualSettleContractListServiceImplTest;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesOpenContractServiceImplTest;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesOrderExecNotifyNormalTransactionCallbackTest;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesOrderExecNotifyServiceImplTest;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesOrderExecNotifyUnitServiceImplTest;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesOrderNotifyUnitServiceImplTest;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesSettleContractInputServiceImplTest;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesSettleContractOrderServiceImplTest;
import webbroker3.ifo.service.delegate.stdimpls.WEB3IfoBalanceReferenceServiceImplTest;
import webbroker3.ifo.service.delegate.stdimpls.WEB3IfoChangeCancelOrderAcceptNormalTransactionCallbackTest;
import webbroker3.ifo.service.delegate.stdimpls.WEB3IfoChangeCancelOrderAcceptUnitServiceImplTestZJ;
import webbroker3.ifo.service.delegate.stdimpls.WEB3IfoCloseNotifyTransactionCallbackTest;
import webbroker3.ifo.service.delegate.stdimpls.WEB3IfoCloseNotifyUnitServiceImplTest;
import webbroker3.ifo.service.delegate.stdimpls.WEB3IfoDesignateEmbeddedNotifyServiceImplTest;
import webbroker3.ifo.service.delegate.stdimpls.WEB3IfoExecuteEndNotifyServiceImplTest;
import webbroker3.ifo.service.delegate.stdimpls.WEB3IfoExecuteEndNotifyUnitServiceImplTest;
import webbroker3.ifo.service.delegate.stdimpls.WEB3IfoExecutedMailSendServiceImplTest;
import webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImplTest;
import webbroker3.ifo.service.delegate.stdimpls.WEB3IfoOrderAcceptNormalTransactionCallbackTest;
import webbroker3.ifo.service.delegate.stdimpls.WEB3IfoOrderAcceptUnitServiceImplTest;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionCancelOrderServiceImplTest;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionChangeCancelNotifyNotifyCancelTransactionCallbackTest;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionChangeCancelNotifyNotifyChangeTransactionCallbackTest;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionChangeCancelNotifyUnitServiceImplTest;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionChangeClosingContractInputServiceImplTest;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionChangeClosingContractServiceImplTest;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionChangeClosingContractServiceImplTest_OT;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionChangeOpenContractInputServiceImplTest;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionChangeOpenContractServiceImplTest;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionChangeOpenContractServiceImplTest_OT;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionIndividualSettleContractListServiceImplTest;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionOpenContractOrderServiceImplTest;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionOrderExecNotifyNormalTransactionCallbackTest;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionOrderExecNotifyUnitServiceImplTest;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionOrderExecutedInquiryServiceImplTest;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionOrderExecutedInquiryServiceImplTest_ES;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionOrderExecutedInquiryServiceImplTest_myn;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionSettleContractInputServiceImplTest;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionSettleContractOrderServiceImplTest;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionSettleContractOrderServiceImplTest_OT;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionsOrderNotifyUnitServiceImplTest;

/**
 * XXXXXXクラス
 * 
 * @@author 周捷(中訊)
 * @@version 1.0
 */
public class WEB3IFOAllTests
{

    public static Test suite()
    {
        TestSuite suite = new TestSuite((new Date()).toLocaleString() +": Test for webbroker3.ifo");
        // $JUnit-BEGIN$
        suite.addTestSuite(DeleteAllTable.class);
        suite.addTestSuite(WEB3IfoExecuteEndNotifyUnitServiceImplTest.class);
        suite.addTestSuite(WEB3OptionChangeClosingContractInputServiceImplTest.class);
        suite.addTestSuite(WEB3OptionOpenContractInputServiceInterceptorTest.class);
        suite.addTestSuite(WEB3FuturesCancelOrderServiceInterceptorTest.class);
        suite.addTestSuite(WEB3FuturesChangeClosingContractServiceInterceptorTest.class);
        suite.addTestSuite(WEB3FuturesChangeOpenContractServiceInterceptorTest.class);
        suite.addTestSuite(WEB3FuturesOpenContractInputServiceInterceptorTest_ES.class);
        suite.addTestSuite(WEB3FuturesOrderManagerImplTest.class);
        suite.addTestSuite(WEB3FuturesSettleContractOrderServiceInterceptorTest.class);
        suite.addTestSuite(WEB3IfoAcceptedUpdateInterceptorTest.class);
        suite.addTestSuite(WEB3IfoBizLogicProviderTest_ES.class);
        suite.addTestSuite(WEB3IfoCancelUpdateInterceptorTest.class);
        suite.addTestSuite(WEB3IfoChangeCancelNotifyUpdateInterceptorTest.class);
        suite.addTestSuite(WEB3IfoChangeSettleContractOrderSpecTest_ES.class);
        suite.addTestSuite(WEB3IfoContractImplTest.class);
        suite.addTestSuite(WEB3IfoDataAdapterTest.class);
        suite.addTestSuite(WEB3IfoExecuteNotifyUpdateInterceptorTest_ES.class);
        suite.addTestSuite(WEB3IfoOpenContractChangeSpecTest_ES.class);
        suite.addTestSuite(WEB3IfoOpenContractChangeUpdateInterceptorTest.class);
        suite.addTestSuite(WEB3IfoOpenContractOrderNotifyUpdateInterceptorTest.class);
        suite.addTestSuite(WEB3IfoOpenContractOrderNotifyUpdateInterceptorTest_ES.class);
        suite.addTestSuite(WEB3IfoOpenContractOrderSpecTest_ES.class);
        suite.addTestSuite(WEB3IfoOpenContractUpdateInterceptorTest.class);
        suite.addTestSuite(WEB3IfoOpenContractUpdateInterceptorTest_ES.class);
        suite.addTestSuite(WEB3IfoOrderManagerReusableValidationsTest.class);
        suite.addTestSuite(WEB3IfoOrderManagerReusableValidationsTest_calcStopLowPrice.class);
        suite.addTestSuite(WEB3IfoOrderManagerReusableValidationsTest_validateEveningSessionLastTradingDate.class);
        suite.addTestSuite(WEB3IfoPositionManagerHelperTest.class);
        suite.addTestSuite(WEB3IfoPositionManagerImplTest.class);
//        suite.addTestSuite(WEB3IfoProductManagerImplTest.class);
        suite.addTestSuite(WEB3IfoSettleContractChangeUpdateInterceptorTest.class);
        suite.addTestSuite(WEB3IfoSettleContractOrderNotifyUpdateInterceptorTest.class);
        suite.addTestSuite(WEB3IfoSettleContractOrderNotifyUpdateInterceptorTest_ES.class);
        suite.addTestSuite(WEB3IfoSettleContractOrderSpecTest.class);
        suite.addTestSuite(WEB3IfoSettleContractUpdateInterceptorTest.class);
        suite.addTestSuite(WEB3IfoSettleContractUpdateInterceptorTest_ES.class);
        suite.addTestSuite(WEB3IfoTradedProductImplTest.class);
        suite.addTestSuite(WEB3IfoUpdateInterceptorTest.class);
        suite.addTestSuite(WEB3OptionCancelOrderServiceInterceptorTest.class);
        suite.addTestSuite(WEB3OptionChangeClosingContractServiceInterceptorTest.class);
        suite.addTestSuite(WEB3OptionChangeOpenContractServiceInterceptorTest.class);        
        suite.addTestSuite(WEB3OptionOpenContractInputServiceInterceptorTest_ES.class);
        suite.addTestSuite(WEB3OptionOpenContractOrderServiceInterceptorTest.class);
        suite.addTestSuite(WEB3OptionOrderManagerImplTest.class);
        suite.addTestSuite(WEB3OptionSettleContractOrderServiceInterceptorTest.class);
        suite.addTestSuite(WEB3OptionOpenContractInputHandlerTest.class);
        suite.addTestSuite(WEB3OptionOpenContractOrderHandlerTest.class);
        suite.addTestSuite(WEB3IfoMarketRequestSenderServiceImplTest.class);
        suite.addTestSuite(WEB3IfoMarketRequestSenderServiceImplTest_ES_send.class);
        suite.addTestSuite(WEB3FuturesCloseMarginChangeCompleteRequestTest.class);
        suite.addTestSuite(WEB3FuturesCloseMarginChangeConfirmRequestTest.class);
        suite.addTestSuite(WEB3FuturesCloseMarginCompleteRequestTest.class);
        suite.addTestSuite(WEB3FuturesCloseMarginConfirmRequestTest.class);
        suite.addTestSuite(WEB3FuturesCommonRequestTest.class);
        suite.addTestSuite(WEB3FuturesExecuteReferenceRequestTest.class);
        suite.addTestSuite(WEB3FuturesSessionTypeComparatorTest.class);
        suite.addTestSuite(WEB3IfoExecEndNotifyRequestTest.class);
        suite.addTestSuite(WEB3OptionsCloseMarginChangeCompleteRequestTest.class);
        suite.addTestSuite(WEB3OptionsCloseMarginChangeConfirmRequestTest.class);
        suite.addTestSuite(WEB3OptionsCloseMarginCompleteRequestTest.class);
        suite.addTestSuite(WEB3OptionsCloseMarginConfirmRequestTest.class);
        suite.addTestSuite(WEB3OptionsCloseMarginContractListRequestTest.class);
        suite.addTestSuite(WEB3OptionsCommonRequestTest.class);
        suite.addTestSuite(WEB3OptionsExecuteReferenceRequestTest.class);
        suite.addTestSuite(WEB3OptionsOpenDateComparatorTest.class);
        suite.addTestSuite(WEB3OptionsOpenMarginCompleteRequestTest.class);
        suite.addTestSuite(WEB3OptionsOpenMarginConfirmRequestTest.class);
        suite.addTestSuite(WEB3OptionsOpenMarginInputRequestTest.class);
        suite.addTestSuite(WEB3OptionsProductSelectRequestTest.class);
        suite.addTestSuite(WEB3OptionsSessionTypeComparatorTest.class);
        suite.addTestSuite(WEB3FuturesOpenContractRequestAdapterTest.class);
        suite.addTestSuite(WEB3FuturesSettleContractOrderRequestAdapterTest.class);
        suite.addTestSuite(WEB3OptionOpenContractOrderRequestAdapterTest.class);
        suite.addTestSuite(WEB3OptionSettleContractOrderRequestAdapterTest.class);
        suite.addTestSuite(WEB3AsynFuturesChangeCancelNotifyServiceImplTest.class);
        suite.addTestSuite(WEB3AsynFuturesOrderExecNotifyServiceImplTest.class);
        suite.addTestSuite(WEB3AsynIfoCloseNotifyServiceImplTest.class);
        suite.addTestSuite(WEB3AsynOptionChangeCancelNotifyServiceImplTest.class);
        suite.addTestSuite(WEB3FuturesCancelOrderServiceImplTest.class);
        suite.addTestSuite(WEB3FuturesChangeCancelNotifyNotifyCancelTransactionCallbackTest.class);
        suite.addTestSuite(WEB3FuturesChangeCancelNotifyNotifyChangeTransactionCallbackTest.class);
        suite.addTestSuite(WEB3FuturesChangeCancelNotifyUnitServiceImplTest.class);
        suite.addTestSuite(WEB3FuturesChangeClosingContractInputServiceImplTest.class);
        suite.addTestSuite(WEB3FuturesChangeClosingContractServiceImplTest.class);
        suite.addTestSuite(WEB3FuturesChangeOpenContractInputServiceImplTest.class);
        suite.addTestSuite(WEB3FuturesChangeOpenContractServiceImplTest.class);
        suite.addTestSuite(WEB3FuturesExecuteReferenceServiceImplTest_ES.class);
        suite.addTestSuite(WEB3FuturesIndividualSettleContractListServiceImplTest.class);
//        suite.addTestSuite(WEB3FuturesOpenContractInputServiceImplTest.class);
        suite.addTestSuite(WEB3FuturesOpenContractServiceImplTest.class);
        suite.addTestSuite(WEB3FuturesOrderExecNotifyNormalTransactionCallbackTest.class);
        suite.addTestSuite(WEB3FuturesOrderExecNotifyUnitServiceImplTest.class);
        suite.addTestSuite(WEB3FuturesOrderNotifyUnitServiceImplTest.class);
        suite.addTestSuite(WEB3FuturesSettleContractInputServiceImplTest.class);
        suite.addTestSuite(WEB3FuturesSettleContractOrderServiceImplTest.class);
        suite.addTestSuite(WEB3IfoBalanceReferenceServiceImplTest.class);
        suite.addTestSuite(WEB3IfoChangeCancelOrderAcceptNormalTransactionCallbackTest.class);
        suite.addTestSuite(WEB3IfoChangeCancelOrderAcceptUnitServiceImplTestZJ.class);
        suite.addTestSuite(WEB3IfoCloseNotifyTransactionCallbackTest.class);
        suite.addTestSuite(WEB3IfoCloseNotifyUnitServiceImplTest.class);
        suite.addTestSuite(WEB3IfoExecuteEndNotifyServiceImplTest.class);
        suite.addTestSuite(WEB3IfoFrontOrderServiceImplTest.class);
        suite.addTestSuite(WEB3IfoOrderAcceptNormalTransactionCallbackTest.class);
        suite.addTestSuite(WEB3IfoOrderAcceptUnitServiceImplTest.class);
        suite.addTestSuite(WEB3OptionCancelOrderServiceImplTest.class);
        suite.addTestSuite(WEB3OptionChangeCancelNotifyNotifyCancelTransactionCallbackTest.class);
        suite.addTestSuite(WEB3OptionChangeCancelNotifyNotifyChangeTransactionCallbackTest.class);
        suite.addTestSuite(WEB3OptionChangeCancelNotifyUnitServiceImplTest.class);
        suite.addTestSuite(WEB3OptionChangeClosingContractServiceImplTest.class);
        suite.addTestSuite(WEB3OptionChangeOpenContractInputServiceImplTest.class);
        suite.addTestSuite(WEB3OptionChangeOpenContractServiceImplTest.class);
        suite.addTestSuite(WEB3OptionIndividualSettleContractListServiceImplTest.class);
//        suite.addTestSuite(WEB3OptionOpenContractInputServiceImplTest.class);
        suite.addTestSuite(WEB3OptionOpenContractOrderServiceImplTest.class);
        suite.addTestSuite(WEB3OptionOrderExecNotifyNormalTransactionCallbackTest.class);
        suite.addTestSuite(WEB3OptionOrderExecutedInquiryServiceImplTest.class);
        suite.addTestSuite(WEB3OptionOrderExecutedInquiryServiceImplTest_ES.class);
        suite.addTestSuite(WEB3OptionOrderExecutedInquiryServiceImplTest_myn.class);
        suite.addTestSuite(WEB3OptionSettleContractInputServiceImplTest.class);
        suite.addTestSuite(WEB3OptionSettleContractOrderServiceImplTest.class);
        suite.addTestSuite(WEB3OptionsOrderNotifyUnitServiceImplTest.class);
        suite.addTestSuite(WEB3FuturesCloseMarginInputRequestTest.class);
        suite.addTestSuite(WEB3FuturesCloseMarginListRequestTest.class);
        suite.addTestSuite(WEB3FuturesOpenMarginConfirmRequestTest.class);
        suite.addTestSuite(WEB3FuturesOpenMarginCompleteRequestTest.class);
        
        suite.addTestSuite(WEB3OptionsCloseMarginChangeConfirmRequestTest_OT.class);
        suite.addTestSuite(WEB3OptionsCloseMarginChangeCompleteRequestTest_OT.class);
        suite.addTestSuite(WEB3OptionsCloseMarginConfirmRequestTest_OT.class);
        suite.addTestSuite(WEB3OptionsCloseMarginCompleteRequestTest_OT.class);
        suite.addTestSuite(WEB3OptionsOpenMarginChangeConfirmRequestTest_OT.class);
        suite.addTestSuite(WEB3OptionsOpenMarginChangeCompleteRequestTest_OT.class);
        //impls
        suite.addTestSuite(WEB3OptionChangeClosingContractServiceImplTest_OT.class);
        suite.addTestSuite(WEB3OptionChangeOpenContractServiceImplTest_OT.class);
        suite.addTestSuite(WEB3OptionSettleContractOrderServiceImplTest_OT.class);
        
        suite.addTestSuite(WEB3FuturesOpenContractInputHandlerTest.class);
        suite.addTestSuite(WEB3FuturesSettleContractOrderHandlerTest.class);
        suite.addTestSuite(WEB3FuturesIndividualCloseMarginListRequestTest.class);
        suite.addTestSuite(WEB3FuturesOpenMarginChangeCompleteRequestTest.class);
        suite.addTestSuite(WEB3FuturesOpenMarginChangeConfirmRequestTest.class);
        suite.addTestSuite(WEB3FuturesOptionsBalanceReferenceRequestTest.class);
        suite.addTestSuite(WEB3OptionsCloseMarginInputRequestTest.class);
        suite.addTestSuite(WEB3OptionsCloseMarginListRequestTest.class);
        suite.addTestSuite(WEB3OptionsContractReferenceRequestTest.class);
        suite.addTestSuite(WEB3OptionsIndividualCloseMarginListRequestTest.class);
        suite.addTestSuite(WEB3FuturesChangeClosingContractInputServiceInterceptorTest.class);
        suite.addTestSuite(WEB3FuturesChangeOpenContractInputServiceInterceptorTest.class);
        suite.addTestSuite(WEB3IfoCancelConfirmUpdateInterceptorTest.class);
        suite.addTestSuite(WEB3IfoChangeConfirmUpdateInterceptorTest.class);
        suite.addTestSuite(WEB3IfoCloseNotifyUpdateInterceptorTest.class);
        suite.addTestSuite(WEB3FuturesChangeCancelNotifyServiceImplTest.class);
        suite.addTestSuite(WEB3FuturesOrderExecNotifyServiceImplTest.class);
        suite.addTestSuite(WEB3FuturesContractInquiryServiceImplTest.class);
        suite.addTestSuite(WEB3IfoDesignateEmbeddedNotifyServiceImplTest.class);
        suite.addTestSuite(WEB3IfoExecutedMailSendServiceImplTest.class);
        suite.addTestSuite(WEB3OptionOrderExecNotifyUnitServiceImplTest.class);
        suite.addTestSuite(WEB3IfoFinTransactionManagerImplTest.class);
        
        // $JUnit-END$
        return suite;
    }
}
@
