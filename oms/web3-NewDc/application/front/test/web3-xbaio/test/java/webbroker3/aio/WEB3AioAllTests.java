head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.35.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AioAllTests.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (WEB3AioAllTests.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/08 周捷 (中訊) 新規作成
*/
package webbroker3.aio;

import junit.framework.Test;
import junit.framework.TestSuite;
import test.util.DeleteAllTable;

import webbroker3.aio.handler.WEB3AdminAioListHandlerTest;
import webbroker3.aio.handler.WEB3AdminAioSLCashOutStopReleaseHandlerTest;
import webbroker3.aio.handler.WEB3AdminAioSLProductCancelHandlerTest;
import webbroker3.aio.handler.WEB3AdminAioSLProductChangeHandlerTest;
import webbroker3.aio.handler.WEB3AdminAioSLProductRegistHandlerTest;
import webbroker3.aio.handler.WEB3AdminAioSLRegistProductReferenceHandlerTest;
import webbroker3.aio.handler.WEB3AioSLAccountOpenHandlerTest;
import webbroker3.aio.handler.WEB3AioSLRepayApplyHandlerTest;
import webbroker3.aio.handler.WEB3AioSLRepayApplyInputHandlerTest;
import webbroker3.aio.handler.WEB3AioSLRepayCancelHandlerTest;
import webbroker3.aio.handler.WEB3AioSLRepayListHandlerTest;
import webbroker3.aio.handler.WEB3AioSecuredLoanOfferStateListHandlerTest;
import webbroker3.aio.handler.WEB3FXAccOpenHandlerTest;
import webbroker3.aio.handler.WEB3FXTransFromFXHandlerTest;
import webbroker3.aio.marketadaptor.WEB3AioMarketRequestSenderServiceImplTest;
import webbroker3.aio.marketadaptor.WEB3AioMarketRequestSenderServiceImplTest20070320;
import webbroker3.aio.marketadaptor.WEB3AioMarketRequestSenderServiceImplTest_20070314;
import webbroker3.aio.message.WEB3AdminAioCashTransferListDownloadRequestTest;
import webbroker3.aio.message.WEB3AdminAioCashTransferListRequestTest;
import webbroker3.aio.message.WEB3AdminSLAccountOpenApplyListRequestTest;
import webbroker3.aio.message.WEB3AdminSLProductCancelCompleteRequestTest;
import webbroker3.aio.message.WEB3AdminSLProductCancelConfirmRequestTest;
import webbroker3.aio.message.WEB3AdminSLProductChangeCompleteRequestTest;
import webbroker3.aio.message.WEB3AdminSLProductChangeConfirmRequestTest;
import webbroker3.aio.message.WEB3AdminSLProductChangeInputRequestTest;
import webbroker3.aio.message.WEB3AdminSLProductRegiListRequestTest;
import webbroker3.aio.message.WEB3AdminSLProductRegistCommonRequestTest;
import webbroker3.aio.message.WEB3AdminSLRestraintMoneyListRequestTest;
import webbroker3.aio.message.WEB3SLRepayCancelCommonRequestTest;
import webbroker3.aio.message.WEB3SLRepayCommonRequestTest;
import webbroker3.aio.service.delegate.stdimpls.WEB3AccTransChangeAcceptUnitServiceImplTest;
import webbroker3.aio.service.delegate.stdimpls.WEB3AccTransChangeCompleteUnitServiceImplTest;
import webbroker3.aio.service.delegate.stdimpls.WEB3AccTransChangeFromIfoDepositServiceImplTest;
import webbroker3.aio.service.delegate.stdimpls.WEB3AccTransChangeRequestAcceptNormalTransactionCallbackTest;
import webbroker3.aio.service.delegate.stdimpls.WEB3AccTransChangeRequestAcceptOrderUnitTransactionCallbackTest;
import webbroker3.aio.service.delegate.stdimpls.WEB3AccTransChangeRequestAcceptServiceImplTest;
import webbroker3.aio.service.delegate.stdimpls.WEB3AccTransChangeRequestNotifyNormalTransactionCallbackTest;
import webbroker3.aio.service.delegate.stdimpls.WEB3AccTransChangeRequestNotifyServiceImplTest;
import webbroker3.aio.service.delegate.stdimpls.WEB3AccTransChangeRequestNotifyUnitServiceImplTest;
import webbroker3.aio.service.delegate.stdimpls.WEB3AccTransChangeToIfoDepositInputServiceImplTest;
import webbroker3.aio.service.delegate.stdimpls.WEB3AccTransChangeToIfoDepositServiceImplTest;
import webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioCashinNoticeConfirmReTreatmentServiceImplTest;
import webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioCashoutInqServiceImplTest;
import webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioListServiceImplTest;
import webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioSLCashOutStopReleaseServiceImplTest;
import webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioSLProductCancelServiceImplTest;
import webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioSLProductChangeServiceImplTest;
import webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioSLProductRegistServiceImplTest;
import webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioSLRegistProductReferenceServiceImplTest;
import webbroker3.aio.service.delegate.stdimpls.WEB3AdminFXAccManagementServiceImplTest;
import webbroker3.aio.service.delegate.stdimpls.WEB3AdminFXAccOpenManagementServiceImplTest;
import webbroker3.aio.service.delegate.stdimpls.WEB3AdminFXTransferManagementServiceImplTest;
import webbroker3.aio.service.delegate.stdimpls.WEB3AdminFXTransferOrderUnitServiceImplTest;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioBondOnPaymentCooperationUnitServiceImplTest;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioCashTransferListServiceImplTest;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioCashinCooperationNotifyUnitServiceImplTest;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioCashoutInputServiceImplTest;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioCashoutServiceImplTest;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioCashoutTradingPowerServiceImplTest;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioMultiBankTelegramProcessServiceImplTest;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioSLAccountOpenUnitServiceImplTest;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioSLRepayApplyInputServiceImplTest;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioSLRepayApplyServiceImplTest;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioSLRepayCancelServiceImplTest;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioSLRepayListServiceImplTest;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioSecuredLoanDataControlServiceImplTest;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioSecuredLoanOfferStateListServiceImplTest;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioSecurityTransferForceUnitServiceImplTest;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioSecurityTransferServiceImplTest;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioSonarCashTransNormalTransactionCallbackTest;
import webbroker3.aio.service.delegate.stdimpls.WEB3FXAccOpenInputServiceImplTest;
import webbroker3.aio.service.delegate.stdimpls.WEB3FXAccOpenServiceImplTest;
import webbroker3.aio.service.delegate.stdimpls.WEB3FXSingleSignOnServiceImplTest;
import webbroker3.aio.service.delegate.stdimpls.WEB3FXTransFromFXServiceImplTest;
import webbroker3.aio.service.delegate.stdimpls.WEB3FXTransToFXServiceImplTest;
import webbroker3.aio.service.delegate.stdimpls.WEB3MarginTransferServiceImplTest;

/**
 * XXXXXXクラス
 *
 * @@author 周捷(中訊)
 * @@version 1.0
 */  
public class WEB3AioAllTests
{   
    public static Test suite()
    {
        TestSuite suite = new TestSuite("Test for webbroker3.aio");
        //$JUnit-BEGIN$
        suite.addTestSuite(DeleteAllTable.class);
        suite.addTestSuite(WEB3FXAccOpenServiceImplTest.class);
        suite.addTestSuite(WEB3FXTransFromFXServiceImplTest.class);
        suite.addTestSuite(WEB3AccTransChangeFromIfoDepositServiceImplTest.class);
        suite.addTestSuite(WEB3AdminFXTransferOrderUnitServiceImplTest.class);
        suite.addTestSuite(WEB3AdminAioSLProductRegistServiceImplTest.class);
        suite.addTestSuite(WEB3AdminFXAccManagementServiceImplTest.class);
        suite.addTestSuite(WEB3FXTransToFXServiceImplTest.class);
        suite.addTestSuite(WEB3AdminAioListDownloadCSVTest.class);
        suite.addTestSuite(WEB3AdminAioSLCashOutStopReleaseServiceInterceptorTest.class);
        suite.addTestSuite(WEB3AdminAioSLProductRegistControlServiceImplTest.class);
        suite.addTestSuite(WEB3AdminAioSLProductRegistServiceInterceptorTest.class);
        suite.addTestSuite(WEB3AioCashTransUpdateInterceptorTest.class);
        suite.addTestSuite(WEB3AioMultiBankSettleControlServiceImplTest.class);
        suite.addTestSuite(WEB3AioMultiBankTelegramProcessServiceInterceptorTest.class);
        suite.addTestSuite(WEB3AioOrderManagerTest.class);
        suite.addTestSuite(WEB3AioSLAccountOpenServiceInterceptorTest.class);
        suite.addTestSuite(WEB3AioSLRepayApplyInputServiceInterceptorTest.class);
        suite.addTestSuite(WEB3AioSLRepayApplyServiceInterceptorTest.class);
        suite.addTestSuite(WEB3AioSLRepayCancelServiceInterceptorTest.class);
        suite.addTestSuite(WEB3AioSLRepayCancelUpdateInterceptorTest.class);
        suite.addTestSuite(WEB3AioSLRepayListServiceInterceptorTest.class);
        suite.addTestSuite(WEB3AioSLRepayUpdateInterceptorTest.class);
        suite.addTestSuite(WEB3AioTransferOrderUpdateInterceptorTest.class);
        suite.addTestSuite(WEB3FXDataControlServiceImplTest.class);
        suite.addTestSuite(WEB3AdminAioListHandlerTest.class);
        suite.addTestSuite(WEB3AdminAioSLCashOutStopReleaseHandlerTest.class);
        suite.addTestSuite(WEB3AdminAioSLProductCancelHandlerTest.class);
        suite.addTestSuite(WEB3AdminAioSLProductChangeHandlerTest.class);
        suite.addTestSuite(WEB3AdminAioSLProductRegistHandlerTest.class);
        suite.addTestSuite(WEB3AdminAioSLRegistProductReferenceHandlerTest.class);
        suite.addTestSuite(WEB3AioSecuredLoanOfferStateListHandlerTest.class);
        suite.addTestSuite(WEB3AioSLAccountOpenHandlerTest.class);
        suite.addTestSuite(WEB3AioSLRepayApplyHandlerTest.class);
        suite.addTestSuite(WEB3AioSLRepayApplyInputHandlerTest.class);
        suite.addTestSuite(WEB3AioSLRepayCancelHandlerTest.class);
        suite.addTestSuite(WEB3AioSLRepayListHandlerTest.class);
        suite.addTestSuite(WEB3FXAccOpenHandlerTest.class);
        suite.addTestSuite(WEB3FXTransFromFXHandlerTest.class);

        suite.addTestSuite(WEB3AioMarketRequestSenderServiceImplTest_20070314.class);
        suite.addTestSuite(WEB3AioMarketRequestSenderServiceImplTest.class);
        suite.addTestSuite(WEB3AioMarketRequestSenderServiceImplTest20070320.class);
        suite.addTestSuite(WEB3AdminAioCashTransferListDownloadRequestTest.class);
        suite.addTestSuite(WEB3AdminAioCashTransferListRequestTest.class);
        suite.addTestSuite(WEB3AdminSLAccountOpenApplyListRequestTest.class);
        suite.addTestSuite(WEB3AdminSLProductCancelCompleteRequestTest.class);
        suite.addTestSuite(WEB3AdminSLProductCancelConfirmRequestTest.class);
        suite.addTestSuite(WEB3AdminSLProductChangeCompleteRequestTest.class);
        suite.addTestSuite(WEB3AdminSLProductChangeConfirmRequestTest.class);
        suite.addTestSuite(WEB3AdminSLProductChangeInputRequestTest.class);
        suite.addTestSuite(WEB3AdminSLProductRegiListRequestTest.class);
        suite.addTestSuite(WEB3AdminSLProductRegistCommonRequestTest.class);
        suite.addTestSuite(WEB3AdminSLRestraintMoneyListRequestTest.class);
        suite.addTestSuite(WEB3SLRepayCancelCommonRequestTest.class);
        suite.addTestSuite(WEB3SLRepayCommonRequestTest.class);
        suite.addTestSuite(WEB3AccTransChangeRequestNotifyServiceImplTest.class);
        suite.addTestSuite(WEB3AdminAioCashinNoticeConfirmReTreatmentServiceImplTest.class);
        suite.addTestSuite(WEB3AdminAioCashoutInqServiceImplTest.class);
        suite.addTestSuite(WEB3AdminAioListServiceImplTest.class);
        suite.addTestSuite(WEB3AdminAioSLCashOutStopReleaseServiceImplTest.class);
        suite.addTestSuite(WEB3AdminAioSLProductCancelServiceImplTest.class);
        suite.addTestSuite(WEB3AdminAioSLProductChangeServiceImplTest.class);

        suite.addTestSuite(WEB3AdminAioSLRegistProductReferenceServiceImplTest.class);

        suite.addTestSuite(WEB3AdminFXAccOpenManagementServiceImplTest.class);
        suite.addTestSuite(WEB3AdminFXTransferManagementServiceImplTest.class);

        suite.addTestSuite(WEB3AioBondOnPaymentCooperationUnitServiceImplTest.class);
        suite.addTestSuite(WEB3AioCashinCooperationNotifyUnitServiceImplTest.class);
        suite.addTestSuite(WEB3AioCashoutInputServiceImplTest.class);
        suite.addTestSuite(WEB3AioCashoutServiceImplTest.class);
        suite.addTestSuite(WEB3AioCashoutTradingPowerServiceImplTest.class);
        suite.addTestSuite(WEB3AioCashTransferListServiceImplTest.class);
        suite.addTestSuite(WEB3AioMultiBankTelegramProcessServiceImplTest.class);
        suite.addTestSuite(WEB3AioSecuredLoanDataControlServiceImplTest.class);
        suite.addTestSuite(WEB3AioSecuredLoanOfferStateListServiceImplTest.class);
        suite.addTestSuite(WEB3AioSecurityTransferForceUnitServiceImplTest.class);
        suite.addTestSuite(WEB3AioSecurityTransferServiceImplTest.class);
        suite.addTestSuite(WEB3AioSLAccountOpenUnitServiceImplTest.class);
        suite.addTestSuite(WEB3AioSLRepayApplyInputServiceImplTest.class);
        suite.addTestSuite(WEB3AioSLRepayApplyServiceImplTest.class);
        suite.addTestSuite(WEB3AioSLRepayCancelServiceImplTest.class);
        suite.addTestSuite(WEB3AioSLRepayListServiceImplTest.class);
        suite.addTestSuite(WEB3AioSonarCashTransNormalTransactionCallbackTest.class);
        suite.addTestSuite(WEB3FXAccOpenInputServiceImplTest.class);

        suite.addTestSuite(WEB3FXSingleSignOnServiceImplTest.class);
        suite.addTestSuite(WEB3AdminFXAccOpenApplyDownloadCsvTest.class);
        suite.addTestSuite(WEB3AdminFXAccOpenUploadCsvTest.class);
        suite.addTestSuite(WEB3AdminFXAccOpenApplyDownloadCsvTest.class);
        suite.addTestSuite(WEB3AdminFXTransferOrderUploadCsvTest.class);

        suite.addTestSuite(WEB3MarginTransferServiceImplTest.class);

        suite.addTestSuite(WEB3FXTransFromFXInputServiceInterceptorTest.class);
        suite.addTestSuite(WEB3FXTransFromFXServiceInterceptorTest.class);
        suite.addTestSuite(WEB3FXTransToFXInputServiceInterceptorTest.class);
        suite.addTestSuite(WEB3FXTransToFXServiceInterceptorTest.class);
        suite.addTestSuite(WEB3FXAccOpenInputServiceInterceptorTest.class);
        suite.addTestSuite(WEB3FXAccOpenServiceInterceptorTest.class);
        suite.addTestSuite(WEB3AioProductTypeOrderManagerReusableValidationsTest.class);

        suite.addTestSuite(WEB3AccTransChangeRequestAcceptOrderUnitTransactionCallbackTest.class);
        suite.addTestSuite(WEB3AccTransChangeAcceptUnitServiceImplTest.class);
        suite.addTestSuite(WEB3AccTransChangeCompleteUnitServiceImplTest.class);
        suite.addTestSuite(WEB3AccTransChangeRequestAcceptNormalTransactionCallbackTest.class);

        suite.addTestSuite(WEB3AccTransChangeRequestAcceptServiceImplTest.class);
        suite.addTestSuite(WEB3AccTransChangeRequestNotifyUnitServiceImplTest.class);
        
        suite.addTestSuite(WEB3AccTransChangeRequestNotifyNormalTransactionCallbackTest.class);
        
        suite.addTestSuite(WEB3AccTransChangeToIfoDepositInputServiceImplTest.class);

        suite.addTestSuite(WEB3AccTransChangeToIfoDepositServiceImplTest.class);
        suite.addTestSuite(WEB3AdminAioVirtualAccCashinULCsvTest.class);
        
        //$JUnit-END$
        return suite;
    }
}
@
