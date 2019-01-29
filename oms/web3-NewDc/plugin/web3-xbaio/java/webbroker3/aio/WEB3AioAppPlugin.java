head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.28.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : Webbroker3-xbaio プラグインクラス(WEB3AioAppPlugin.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/20 王蘭芬(中訊)新規作成
Revesion History : 2004/12/15 王蘭芬(中訊)残対応
Revesion History : 2005/01/27 王蘭芬(中訊)為替保証金対応
Revesion History : 2005/02/22 王蘭芬(中訊)特定口座振替強制追加対応
Revesion History : 2005/04/01 王蘭芬(中訊)外国株式振替連携追加対応
Revesion History : 2005/07/12 韋念瓊(中訊)その他件数照会追加対応
Revesion History : 2006/01/12 黄建(中訊)入金連携追加対応
Revesion History : 2006/01/22 黄建(中訊)入金連携確認再処理追加対応
Revesion History : 2006/02/10 譚漢江(中訊)為替保証金追加対応
Revesion History : 2006/02/10 黄浩澎(中訊)管理者・FX口座開設ＵＬ追加対応
Revesion History : 2006/03/02 鄭徳懇(中訊)管理者・FX振替注文ＵＬ追加対応
Revesion History : 2006/04/19 相馬　@和明(SCS) 障害管理 U02823対応
Revesion History : 2006/05/11 黄建(中訊) バーチャル口座入金UL対応
Revesion History : 2006/07/31 黄建(中訊) 出金請求データ出力対応
Revesion History : 2006/09/01 徐宏偉(中訊) SONAR入出金（外貨）対応
Revesion History : 2006/09/05 徐宏偉(中訊) 外貨入出金受付
Revesion History : 2006/09/25 唐性峰(中訊) 証券担保ローン対応
Revesion History : 2006/10/19 徐宏偉(中訊) 債券出金連携
Revesion History : 2006/11/06 何文敏(中訊) モデルNo.677
Revesion History : 2007/02/09 徐宏偉(中訊) 管理者入出金一覧サービス
Revesion History : 2007/04/10 徐宏偉(中訊) 出金注文トリガー発行
Revesion History : 2007/07/13 金傑(中訊) 保証金への振替サービス追加対応
Revesion History : 2007/09/19 金傑(中訊) 担保ローン出金拘束金解除サービ追加対応
Revesion History : 2007/09/23 柴双紅(中訊) 証券担保ローン対応
Revesion History : 2007/09/26 趙林鵬(中訊) 担保銘柄登録対応
Revesion History : 2008/04/08 馮海濤 (中訊) 仕様変更・モデル832　@モデル833　@モデル840
                 : 2009/02/13 三島 (SCS)仕様変更・モデル1097
Revesion History : 2009/07/01 張騰宇(中訊) 【為替保証金】岩井振替可能額表示対応
Revesion History : 2009/09/21 張騰宇(中訊) 【為替保証金】岩井大証ＦＸ対応
*/
package webbroker3.aio;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AlreadyInstalledException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotInstalledException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.aio.data.WEB3AioAccountDatabaseExtensions;
import webbroker3.aio.data.WEB3AioMasterDatabaseExtensions;
import webbroker3.aio.data.WEB3AioSessionDatabaseExtensions;
import webbroker3.aio.handler.WEB3AccTransChangeFromIfoDepositHandler;
import webbroker3.aio.handler.WEB3AccTransChangeFromIfoDepositInputHandler;
import webbroker3.aio.handler.WEB3AccTransChangeRequestAcceptHandler;
import webbroker3.aio.handler.WEB3AccTransChangeRequestNotifyHandler;
import webbroker3.aio.handler.WEB3AccTransChangeToIfoDepositHandler;
import webbroker3.aio.handler.WEB3AccTransChangeToIfoDepositInputHandler;
import webbroker3.aio.handler.WEB3AdminAioCashinConfirmInputHandler;
import webbroker3.aio.handler.WEB3AdminAioCashinNoticeConfirmHandler;
import webbroker3.aio.handler.WEB3AdminAioCashinNoticeConfirmReTreatmentHandler;
import webbroker3.aio.handler.WEB3AdminAioCashoutInqHandler;
import webbroker3.aio.handler.WEB3AdminAioCashoutInqInputHandler;
import webbroker3.aio.handler.WEB3AdminAioListHandler;
import webbroker3.aio.handler.WEB3AdminAioOtherCountReferenceHandler;
import webbroker3.aio.handler.WEB3AdminAioSLCashOutStopReleaseHandler;
import webbroker3.aio.handler.WEB3AdminAioSLProductCancelHandler;
import webbroker3.aio.handler.WEB3AdminAioSLProductChangeHandler;
import webbroker3.aio.handler.WEB3AdminAioSLProductRegistHandler;
import webbroker3.aio.handler.WEB3AdminAioSLRegistProductReferenceHandler;
import webbroker3.aio.handler.WEB3AdminAioSettleReportHandler;
import webbroker3.aio.handler.WEB3AdminAioSettleReportInputHandler;
import webbroker3.aio.handler.WEB3AdminAioVirtualAccCashinULHandler;
import webbroker3.aio.handler.WEB3AdminFEqConAccountMngHandler;
import webbroker3.aio.handler.WEB3AdminFEqConAccountOpenMngHandler;
import webbroker3.aio.handler.WEB3AdminFEqConTransferMngHandler;
import webbroker3.aio.handler.WEB3AdminFXAccManagementHandler;
import webbroker3.aio.handler.WEB3AdminFXAccOpenManagementHandler;
import webbroker3.aio.handler.WEB3AdminFXAccOpenUploadHandler;
import webbroker3.aio.handler.WEB3AdminFXTransferManagementHandler;
import webbroker3.aio.handler.WEB3AdminFXTransferOrderUploadHandler;
import webbroker3.aio.handler.WEB3AioBondOnPaymentCooperationHandler;
import webbroker3.aio.handler.WEB3AioCashTransferListHandler;
import webbroker3.aio.handler.WEB3AioCashinAcceptHandler;
import webbroker3.aio.handler.WEB3AioCashinCooperationServiceHandler;
import webbroker3.aio.handler.WEB3AioCashinInputHandler;
import webbroker3.aio.handler.WEB3AioCashinNoticeHandler;
import webbroker3.aio.handler.WEB3AioCashinNoticeInputHandler;
import webbroker3.aio.handler.WEB3AioCashinSelectHandler;
import webbroker3.aio.handler.WEB3AioCashinSettleCancelHandler;
import webbroker3.aio.handler.WEB3AioCashinSettleCompleteHandler;
import webbroker3.aio.handler.WEB3AioCashinSettleErrorHandler;
import webbroker3.aio.handler.WEB3AioCashinSettlementHandler;
import webbroker3.aio.handler.WEB3AioCashoutAcceptHandler;
import webbroker3.aio.handler.WEB3AioCashoutCancelHandler;
import webbroker3.aio.handler.WEB3AioCashoutCancelListHandler;
import webbroker3.aio.handler.WEB3AioCashoutHandler;
import webbroker3.aio.handler.WEB3AioCashoutInputHandler;
import webbroker3.aio.handler.WEB3AioCashoutTradingPowerHandler;
import webbroker3.aio.handler.WEB3AioFinanceAmountRepayHandler;
import webbroker3.aio.handler.WEB3AioForeignCashTransAcceptHandler;
import webbroker3.aio.handler.WEB3AioInputOutputHistoryHandler;
import webbroker3.aio.handler.WEB3AioOnPaymentCooperationHandler;
import webbroker3.aio.handler.WEB3AioOutputNotifyHandler;
import webbroker3.aio.handler.WEB3AioSLAccountOpenHandler;
import webbroker3.aio.handler.WEB3AioSLRepayApplyHandler;
import webbroker3.aio.handler.WEB3AioSLRepayApplyInputHandler;
import webbroker3.aio.handler.WEB3AioSLRepayCancelHandler;
import webbroker3.aio.handler.WEB3AioSLRepayListHandler;
import webbroker3.aio.handler.WEB3AioSecuredLoanOfferStateListHandler;
import webbroker3.aio.handler.WEB3AioSecurityTransferForceHandler;
import webbroker3.aio.handler.WEB3AioSecurityTransferHandler;
import webbroker3.aio.handler.WEB3AioSecurityTransferInputHandler;
import webbroker3.aio.handler.WEB3AioSecurityTransferNotifyHandler;
import webbroker3.aio.handler.WEB3AioSonarCashTransForeignHandler;
import webbroker3.aio.handler.WEB3AioSonarCashTransHandler;
import webbroker3.aio.handler.WEB3AioSpsecTransferForceHandler;
import webbroker3.aio.handler.WEB3FEqConAccountOpenHandler;
import webbroker3.aio.handler.WEB3FEqConTransferCancelHandler;
import webbroker3.aio.handler.WEB3FEqConTransferHandler;
import webbroker3.aio.handler.WEB3FXAccOpenHandler;
import webbroker3.aio.handler.WEB3FXAccOpenInputHandler;
import webbroker3.aio.handler.WEB3FXSingleSignOnHandler;
import webbroker3.aio.handler.WEB3FXTransFromFXHandler;
import webbroker3.aio.handler.WEB3FXTransFromFXInputHandler;
import webbroker3.aio.handler.WEB3FXTransToFXHandler;
import webbroker3.aio.handler.WEB3FXTransToFXInputHandler;
import webbroker3.aio.marketadaptor.WEB3AioMarketAdaptorAppPlugin;
import webbroker3.aio.message.*;
import webbroker3.aio.service.delegate.WEB3AdminAioSLProductChangeService;
import webbroker3.aio.service.delegate.WEB3AccTransChangeAcceptUnitService;
import webbroker3.aio.service.delegate.WEB3AccTransChangeCompleteUnitService;
import webbroker3.aio.service.delegate.WEB3AccTransChangeFromIfoDepositInputService;
import webbroker3.aio.service.delegate.WEB3AccTransChangeFromIfoDepositService;
import webbroker3.aio.service.delegate.WEB3AccTransChangeRequestAcceptService;
import webbroker3.aio.service.delegate.WEB3AccTransChangeRequestNotifyService;
import webbroker3.aio.service.delegate.WEB3AccTransChangeRequestNotifyUnitService;
import webbroker3.aio.service.delegate.WEB3AccTransChangeToIfoDepositInputService;
import webbroker3.aio.service.delegate.WEB3AccTransChangeToIfoDepositService;
import webbroker3.aio.service.delegate.WEB3AdminAioCashinConfirmInputService;
import webbroker3.aio.service.delegate.WEB3AdminAioCashinNoticeConfirmReTreatmentService;
import webbroker3.aio.service.delegate.WEB3AdminAioCashinNoticeConfirmService;
import webbroker3.aio.service.delegate.WEB3AdminAioCashoutInqInputService;
import webbroker3.aio.service.delegate.WEB3AdminAioCashoutInqService;
import webbroker3.aio.service.delegate.WEB3AdminAioListService;
import webbroker3.aio.service.delegate.WEB3AdminAioOtherCountReferenceService;
import webbroker3.aio.service.delegate.WEB3AdminAioSLCashOutStopReleaseService;
import webbroker3.aio.service.delegate.WEB3AdminAioSLProductCancelService;
import webbroker3.aio.service.delegate.WEB3AdminAioSLProductRegistService;
import webbroker3.aio.service.delegate.WEB3AdminAioSLRegistProductReferenceService;
import webbroker3.aio.service.delegate.WEB3AdminAioSettleReportInputService;
import webbroker3.aio.service.delegate.WEB3AdminAioSettleReportService;
import webbroker3.aio.service.delegate.WEB3AdminAioVirtualAccCashinULService;
import webbroker3.aio.service.delegate.WEB3AdminFEqConAccountMngService;
import webbroker3.aio.service.delegate.WEB3AdminFEqConAccountOpenMngService;
import webbroker3.aio.service.delegate.WEB3AdminFEqConTransferMngService;
import webbroker3.aio.service.delegate.WEB3AdminFXAccManagementService;
import webbroker3.aio.service.delegate.WEB3AdminFXAccOpenManagementService;
import webbroker3.aio.service.delegate.WEB3AdminFXAccOpenUploadService;
import webbroker3.aio.service.delegate.WEB3AdminFXTransferManagementService;
import webbroker3.aio.service.delegate.WEB3AdminFXTransferManagementUnitService;
import webbroker3.aio.service.delegate.WEB3AdminFXTransferOrderUnitService;
import webbroker3.aio.service.delegate.WEB3AdminFXTransferOrderUploadService;
import webbroker3.aio.service.delegate.WEB3AioBondOnPaymentCooperationService;
import webbroker3.aio.service.delegate.WEB3AioBondOnPaymentCooperationUnitService;
import webbroker3.aio.service.delegate.WEB3AioCashOutOrderTriggerIssueUnitService;
import webbroker3.aio.service.delegate.WEB3AioCashTransferAcceptUnitService;
import webbroker3.aio.service.delegate.WEB3AioCashTransferCompleteUnitService;
import webbroker3.aio.service.delegate.WEB3AioCashTransferListService;
import webbroker3.aio.service.delegate.WEB3AioCashinAcceptService;
import webbroker3.aio.service.delegate.WEB3AioCashinCooperationNotifyUnitService;
import webbroker3.aio.service.delegate.WEB3AioCashinCooperationService;
import webbroker3.aio.service.delegate.WEB3AioCashinInputService;
import webbroker3.aio.service.delegate.WEB3AioCashinNoticeInputService;
import webbroker3.aio.service.delegate.WEB3AioCashinNoticeMailSendService;
import webbroker3.aio.service.delegate.WEB3AioCashinNoticeService;
import webbroker3.aio.service.delegate.WEB3AioCashinSelectService;
import webbroker3.aio.service.delegate.WEB3AioCashinSettleCancelService;
import webbroker3.aio.service.delegate.WEB3AioCashinSettleCompleteService;
import webbroker3.aio.service.delegate.WEB3AioCashinSettleErrorService;
import webbroker3.aio.service.delegate.WEB3AioCashinSettlementService;
import webbroker3.aio.service.delegate.WEB3AioCashoutAcceptService;
import webbroker3.aio.service.delegate.WEB3AioCashoutCancelListService;
import webbroker3.aio.service.delegate.WEB3AioCashoutCancelService;
import webbroker3.aio.service.delegate.WEB3AioCashoutCancelUnitService;
import webbroker3.aio.service.delegate.WEB3AioCashoutInputService;
import webbroker3.aio.service.delegate.WEB3AioCashoutService;
import webbroker3.aio.service.delegate.WEB3AioCashoutTradingPowerService;
import webbroker3.aio.service.delegate.WEB3AioCashoutTradingPowerUnitService;
import webbroker3.aio.service.delegate.WEB3AioFinanceAmountRepayService;
import webbroker3.aio.service.delegate.WEB3AioFinanceAmountRepayUnitService;
import webbroker3.aio.service.delegate.WEB3AioForeignCashTransAcceptService;
import webbroker3.aio.service.delegate.WEB3AioInputOutputHistoryService;
import webbroker3.aio.service.delegate.WEB3AioMultiBankTelegramProcessService;
import webbroker3.aio.service.delegate.WEB3AioOnPaymentCooperationService;
import webbroker3.aio.service.delegate.WEB3AioOnPaymentCooperationUnitService;
import webbroker3.aio.service.delegate.WEB3AioOutputNotifyService;
import webbroker3.aio.service.delegate.WEB3AioOutputNotifyUnitService;
import webbroker3.aio.service.delegate.WEB3AioSLAccountOpenUnitService;
import webbroker3.aio.service.delegate.WEB3AioSLRepayApplyInputService;
import webbroker3.aio.service.delegate.WEB3AioSLRepayApplyService;
import webbroker3.aio.service.delegate.WEB3AioSLRepayCancelService;
import webbroker3.aio.service.delegate.WEB3AioSLRepayListService;
import webbroker3.aio.service.delegate.WEB3AioSecuredLoanDataControlService;
import webbroker3.aio.service.delegate.WEB3AioSecuredLoanOfferStateListService;
import webbroker3.aio.service.delegate.WEB3AioSecurityTransferForceService;
import webbroker3.aio.service.delegate.WEB3AioSecurityTransferForceUnitService;
import webbroker3.aio.service.delegate.WEB3AioSecurityTransferInputService;
import webbroker3.aio.service.delegate.WEB3AioSecurityTransferNotifyService;
import webbroker3.aio.service.delegate.WEB3AioSecurityTransferNotifyUnitService;
import webbroker3.aio.service.delegate.WEB3AioSecurityTransferService;
import webbroker3.aio.service.delegate.WEB3AioSonarCashTransForeignService;
import webbroker3.aio.service.delegate.WEB3AioSonarCashTransForeignUnitService;
import webbroker3.aio.service.delegate.WEB3AioSonarCashTransService;
import webbroker3.aio.service.delegate.WEB3AioSonarCashTransUnitService;
import webbroker3.aio.service.delegate.WEB3AioSpsecTransferForceService;
import webbroker3.aio.service.delegate.WEB3AioSpsecTransferForceUnitService;
import webbroker3.aio.service.delegate.WEB3FEqConAccountOpenService;
import webbroker3.aio.service.delegate.WEB3FEqConTransferCancelService;
import webbroker3.aio.service.delegate.WEB3FEqConTransferService;
import webbroker3.aio.service.delegate.WEB3FXAccOpenInputService;
import webbroker3.aio.service.delegate.WEB3FXAccOpenService;
import webbroker3.aio.service.delegate.WEB3FXSingleSignOnService;
import webbroker3.aio.service.delegate.WEB3FXTransFromFXInputService;
import webbroker3.aio.service.delegate.WEB3FXTransFromFXService;
import webbroker3.aio.service.delegate.WEB3FXTransToFXInputService;
import webbroker3.aio.service.delegate.WEB3FXTransToFXService;
import webbroker3.aio.service.delegate.WEB3MarginTransferService;
import webbroker3.aio.service.delegate.stdimpls.WEB3AccTransChangeAcceptUnitServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AccTransChangeCompleteUnitServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AccTransChangeFromIfoDepositInputServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AccTransChangeFromIfoDepositServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AccTransChangeRequestAcceptServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AccTransChangeRequestNotifyServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AccTransChangeRequestNotifyUnitServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AccTransChangeToIfoDepositInputServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AccTransChangeToIfoDepositServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioSLProductChangeServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioCashinConfirmInputServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioCashinNoticeConfirmReTreatmentServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioCashinNoticeConfirmServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioCashoutInqInputServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioCashoutInqServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioListServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioOtherCountReferenceServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioSLCashOutStopReleaseServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioSLProductCancelServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioSLProductRegistServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioSLRegistProductReferenceServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioSettleReportInputServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioSettleReportServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioVirtualAccCashinULServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AdminFEqConAccountMngServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AdminFEqConAccountOpenMngServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AdminFEqConTransferMngServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AdminFXAccManagementServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AdminFXAccOpenManagementServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AdminFXAccOpenUploadServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AdminFXTransferManagementServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AdminFXTransferManagementUnitServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AdminFXTransferOrderUnitServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AdminFXTransferOrderUploadServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioBondOnPaymentCooperationServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioBondOnPaymentCooperationUnitServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioCashOutOrderTriggerIssueUnitServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioCashTransferAcceptUnitServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioCashTransferCompleteUnitServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioCashTransferListServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioCashinAcceptServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioCashinCooperationNotifyUnitServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioCashinCooperationServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioCashinInputServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioCashinNoticeInputServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioCashinNoticeMailSendServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioCashinNoticeServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioCashinSelectServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioCashinSettleCancelServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioCashinSettleCompleteServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioCashinSettleErrorServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioCashinSettlementServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioCashoutAcceptServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioCashoutCancelListServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioCashoutCancelServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioCashoutCancelUnitServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioCashoutInputServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioCashoutServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioCashoutTradingPowerServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioCashoutTradingPowerUnitServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioFinanceAmountRepayServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioFinanceAmountRepayUnitServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioForeignCashTransAcceptServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioInputOutputHistoryServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioMultiBankTelegramProcessServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioOnPaymentCooperationServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioOnPaymentCooperationUnitServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioOutputNotifyServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioOutputNotifyUnitServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioSLAccountOpenUnitServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioSLRepayApplyInputServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioSLRepayApplyServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioSLRepayCancelServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioSLRepayListServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioSecuredLoanDataControlServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioSecuredLoanOfferStateListServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioSecurityTransferForceServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioSecurityTransferForceUnitServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioSecurityTransferInputServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioSecurityTransferNotifyServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioSecurityTransferNotifyUnitServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioSecurityTransferServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioSonarCashTransForeignServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioSonarCashTransForeignUnitServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioSonarCashTransServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioSonarCashTransUnitServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioSpsecTransferForceServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioSpsecTransferForceUnitServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3FEqConAccountOpenServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3FEqConTransferCancelServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3FEqConTransferServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3FXAccOpenInputServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3FXAccOpenServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3FXSingleSignOnServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3FXTransFromFXInputServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3FXTransFromFXServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3FXTransToFXInputServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3FXTransToFXServiceImpl;
import webbroker3.aio.service.delegate.stdimpls.WEB3MarginTransferServiceImpl;
import webbroker3.common.WEB3LogSysTimeInterceptor;
import webbroker3.mqgateway.WEB3MQGatewayInterceptor;
import webbroker3.servlet.WEB3HttpServiceMappings;
import webbroker3.util.WEB3LogUtility;


/**
 * Webbroker3-Aio プラグインクラス。
 *
 * @@author 王蘭芬 (中訊)
 * @@version 1.0
 */
public final class WEB3AioAppPlugin extends Plugin
{
    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioAppPlugin.class);

    /**
     * コンストラクタ。
     */
    public WEB3AioAppPlugin()
    {
    }

    /**
     * プラグインエントリーポイント。
     */
    public static void plug() throws Exception
    {
        String METHOD_NAME = "plug()";
        log.entering(METHOD_NAME);

        plug(WEB3AioAppPlugin.class);

        log.exiting(METHOD_NAME);
    }

    /**
     * プラグイン処理。
     */
    public static void onPlug() throws Exception
    {
        String METHOD_NAME = "onPlug()";
        log.entering(METHOD_NAME);

        // このプラグインより先に読み込む必要のあるプラグインの指定。
        // install the system plugins that we need
        KernelPlugin.plug();

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        // 拡張トランザクション・マネージャーは
        // オーバーライドメソッドが無いため拡張取引モジュールクラスを作成し
        // 拡張取引モジュールクラス内で設定

        try
        {
            l_finApp.uninstallTradingModule("xb-aio-pdbt");
        }
        catch (NotInstalledException l_ex)
        {
            log.info(l_ex.getMessage());
        }

        try
        {
            log.info("Installing TradingModule : web3-aio");
            l_finApp.installTradingModule(new WEB3AioTradingModule());
            log.info("Installed TradingModule : web3-aio");
        }
        catch (AlreadyInstalledException l_ex)
        {
            log.info(l_ex.getMessage());
        }

        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.AIO);

        // 計算サービスクラス
        l_tradingModule.overrideBizLogicProvider(new WEB3AioBizLogicProvider());

        // 拡張注文マネージャ
        l_tradingModule.overrideOrderManager(new WEB3AioOrderManager());

        // AIOプロダクトマネージャ
        l_tradingModule.overrideProductManager(new WEB3AioProductManager());

        // ポジションマネージャ
        l_tradingModule.overridePositionManager(new WEB3AioPositionManager());

        // 入出金発注審査個別チェック
        WEB3AioProductTypeOrderManagerReusableValidations.register();

        // Webbroker3-Aio-MarketAdaptor プラグイン
        WEB3AioMarketAdaptorAppPlugin.plug();


        // DatabaseExtensions のプラグイン処理 ----------------------
        WEB3AioMasterDatabaseExtensions.plug();
        WEB3AioSessionDatabaseExtensions.plug();
        WEB3AioAccountDatabaseExtensions.plug();

        // Service の登録処理 ----------------------
        plugServices();

        // Service.execute()呼び出し前後に  ----------------------
        // 処理開始時刻と処理終了時刻をログ出力するように設定する
        plugLogSysTimeInterceptors();

        //Service に ServiceInterceptor を設定する ----------------------
        plugServiceInterceptors();

        // Service の Interceptor 設定処理 ----------------------
        // 自動トランザクション設定
        plugTransactionalInterceptors();

        //MQ-Gateway の Interceptor 設定処理 ----------------------
        plugMQGatewayInterceptors();

        // Message の登録処理 ----------------------
        plugMessages();

        // Handler の登録処理
        plugHandlers();

        //RAC-CTXインタセプタを設定する
        plugRacCtxInterceptors();

        log.exiting(METHOD_NAME);
    }
    /**
     * Service の登録処理
     * @@throws Exception
     */
    private static void plugServices() throws Exception
    {
        // ------（為替保証金）為替保証金共通サービス------

        // ------FX電文処理サービス-----------
        Services.registerService(
            WEB3FXTelegramProcessService.class,
            new WEB3FXTelegramProcessServiceImpl());

        // ------FXデータ制御サービス-----------
        Services.registerService(
            WEB3FXDataControlService.class,
            new WEB3FXDataControlServiceImpl());

        //接続共通インタフェース
        Services.registerService(
            WEB3FXConnCommonService.class,
            new WEB3FXConnCommonServiceImpl());

        //FX振替可能額表示サービスインターフェイス
        Services.registerService(
            WEB3FXTransferAbleAmtDisplayService.class,
            new WEB3FXTransferAbleAmtDisplayServiceImpl());

        //口座開設接続インタフェース
        Services.registerService(
            WEB3FXAccOpenConnection.class,
            new WEB3FXAccOpenConnectionImpl());
        
        //振替接続インタフェース
        Services.registerService(
            WEB3FXTransConnection.class,
            new WEB3FXTransConnectionImpl());

        // ------（入出金）マルチバンク決済エンティティ．マルチバンク決済制御サービス------
        Services.registerService(
                WEB3AioMultiBankSettleControlService.class,
                new WEB3AioMultiBankSettleControlServiceImpl());

        //-------------担保銘柄登録制御サービス ---------------
        Services.registerService(
            WEB3AdminAioSLProductRegistControlService.class,
            new WEB3AdminAioSLProductRegistControlServiceImpl());

        // 入出金サービス--------------------------Begin------
        // マルチバンク電文処理サービスインタフェース
        WEB3HttpServiceMappings l_mapping =
            (WEB3HttpServiceMappings)Services.getService(WEB3HttpServiceMappings.class);

        Services.registerService(
            WEB3AioMultiBankTelegramProcessService.class,
            new WEB3AioMultiBankTelegramProcessServiceImpl());
        l_mapping.addService("web3-aio", WEB3AioMultiBankTelegramProcessService.class);

        // 決済エラーサービスインターフェイス
        Services.registerService(
            WEB3AioCashinSettleErrorService.class,
            new WEB3AioCashinSettleErrorServiceImpl());

        // 決済依頼サービスインターフェイス
        Services.registerService(
                WEB3AioCashinSettlementService.class,
            new WEB3AioCashinSettlementServiceImpl());

        // 決済完了サービスインタフェース
        Services.registerService(
                WEB3AioCashinSettleCompleteService.class,
            new WEB3AioCashinSettleCompleteServiceImpl());

        // 注文要求中止サービスインターフェイス
        Services.registerService(
                WEB3AioCashinSettleCancelService.class,
            new WEB3AioCashinSettleCancelServiceImpl());

        // SONAR入出金サービスインターフェイス
        Services.registerService(
                WEB3AioSonarCashTransService.class,
            new WEB3AioSonarCashTransServiceImpl());

        // SONAR入出金UnitServiceインタフェース
        Services.registerService(
                WEB3AioSonarCashTransUnitService.class,
            new WEB3AioSonarCashTransUnitServiceImpl());

        // オンライン入金選択サービスインターフェイス
        Services.registerService(
                WEB3AioCashinSelectService.class,
            new WEB3AioCashinSelectServiceImpl());

        // オンライン入金入力サービスインタフェース
        Services.registerService(
                WEB3AioCashinInputService.class,
            new WEB3AioCashinInputServiceImpl());

        // 出金取消サービスインターフェイス
        Services.registerService(
                WEB3AioCashoutCancelService.class,
            new WEB3AioCashoutCancelServiceImpl());

        // 出金取消UnitService
        Services.registerService(
            WEB3AioCashoutCancelUnitService.class,
            new WEB3AioCashoutCancelUnitServiceImpl());

        // 出金取消一覧サービスインタフェース
        Services.registerService(
                WEB3AioCashoutCancelListService.class,
            new WEB3AioCashoutCancelListServiceImpl());

        // 出金申込サービスインターフェイス
        Services.registerService(
                WEB3AioCashoutService.class,
            new WEB3AioCashoutServiceImpl());

        // 出金申込入力サービスインタフェース
        Services.registerService(
                WEB3AioCashoutInputService.class,
            new WEB3AioCashoutInputServiceImpl());

        // 出金余力チェックサービスインターフェイスクラス
        Services.registerService(
                WEB3AioCashoutTradingPowerService.class,
            new WEB3AioCashoutTradingPowerServiceImpl());

        // 出金余力チェックUnitServiceインターフェイス
        Services.registerService(
                WEB3AioCashoutTradingPowerUnitService.class,
            new WEB3AioCashoutTradingPowerUnitServiceImpl());

        // 出金連携サービスインターフェイス
        Services.registerService(
            WEB3AioOnPaymentCooperationService.class,
            new WEB3AioOnPaymentCooperationServiceImpl());

        // 出金連携UnitServiceインターフェイス
        Services.registerService(
            WEB3AioOnPaymentCooperationUnitService.class,
            new WEB3AioOnPaymentCooperationUnitServiceImpl());

        // 入金連携サービスインターフェイス
        Services.registerService(
            WEB3AioCashinCooperationService.class,
            new WEB3AioCashinCooperationServiceImpl());

        // 入金連携通知一件サービスインターフェイス
        Services.registerService(
            WEB3AioCashinCooperationNotifyUnitService.class,
            new WEB3AioCashinCooperationNotifyUnitServiceImpl());

        // 入金連絡サービスインターフェイス
        Services.registerService(
                WEB3AioCashinNoticeService.class,
            new WEB3AioCashinNoticeServiceImpl());

        // 入金連絡メール送信サービスインタフェース
        Services.registerService(
                WEB3AioCashinNoticeMailSendService.class,
            new WEB3AioCashinNoticeMailSendServiceImpl());


        // 入金連絡入力サービスインターフェイス
        Services.registerService(
                WEB3AioCashinNoticeInputService.class,
            new WEB3AioCashinNoticeInputServiceImpl());

        // 入出金一覧サービスインタフェース
        Services.registerService(
                WEB3AioCashTransferListService.class,
            new WEB3AioCashTransferListServiceImpl());

        // 出金受付サービスインタフェース
        Services.registerService(
                WEB3AioCashoutAcceptService.class,
            new WEB3AioCashoutAcceptServiceImpl());

        // 入金受付サービスインタフェース
        Services.registerService(
                WEB3AioCashinAcceptService.class,
            new WEB3AioCashinAcceptServiceImpl());

        // 入出金完了UnitServiceインタフェース
        Services.registerService(
                WEB3AioCashTransferCompleteUnitService.class,
            new WEB3AioCashTransferCompleteUnitServiceImpl());

        // 入出金受付UnitServiceインタフェース
        Services.registerService(
                WEB3AioCashTransferAcceptUnitService.class,
            new WEB3AioCashTransferAcceptUnitServiceImpl());

        // SONAR入出金（外貨）サービスインタフェース
        Services.registerService(
                WEB3AioSonarCashTransForeignService.class,
            new WEB3AioSonarCashTransForeignServiceImpl());

        // SONAR入出金（外貨）UnitServiceインタフェース
        Services.registerService(
                WEB3AioSonarCashTransForeignUnitService.class,
            new WEB3AioSonarCashTransForeignUnitServiceImpl());

        //外貨入出金受付インタフェース
        Services.registerService(
                WEB3AioForeignCashTransAcceptService.class,
            new WEB3AioForeignCashTransAcceptServiceImpl());

        //融資額返済UnitServiceインタフェース
        Services.registerService(
                WEB3AioFinanceAmountRepayUnitService.class,
            new WEB3AioFinanceAmountRepayUnitServiceImpl());

        //融資額返済サービス インタフェース
        Services.registerService(
                WEB3AioFinanceAmountRepayService.class,
            new WEB3AioFinanceAmountRepayServiceImpl());

        // 債券出金連携サービスインタフェース
        Services.registerService(
                WEB3AioBondOnPaymentCooperationService.class,
            new WEB3AioBondOnPaymentCooperationServiceImpl());

        // 債券出金連携UnitServiceインタフェース
        Services.registerService(
                WEB3AioBondOnPaymentCooperationUnitService.class,
            new WEB3AioBondOnPaymentCooperationUnitServiceImpl());

        //出金注文トリガー発行UnitServiceインタフェース
        Services.registerService(
                WEB3AioCashOutOrderTriggerIssueUnitService.class,
            new WEB3AioCashOutOrderTriggerIssueUnitServiceImpl());

        // 入出金サービス--------------------------End------

        //入出庫サービスモデル --------------------------Begin------

        // 出庫通知サービス
        Services.registerService(
            WEB3AioOutputNotifyService.class,
            new WEB3AioOutputNotifyServiceImpl());

        // 出庫通知UnitService
        Services.registerService(
            WEB3AioOutputNotifyUnitService.class,
            new WEB3AioOutputNotifyUnitServiceImpl());
        
        // 保証金への振替サービス
        Services.registerService(
            WEB3MarginTransferService.class,
            new WEB3MarginTransferServiceImpl());

        //入出庫サービスモデル --------------------------End--------

        // 入出金サービス（管理者）--------------------------Begin------

        // その他件数照会サービスインタフェース
        Services.registerService(
                WEB3AdminAioOtherCountReferenceService.class,
            new WEB3AdminAioOtherCountReferenceServiceImpl());

        // 決済連携レポートサービスインタフェース
        Services.registerService(
                WEB3AdminAioSettleReportService.class,
            new WEB3AdminAioSettleReportServiceImpl());

        // 決済連携レポート入力サービスインタフェース
        Services.registerService(
                WEB3AdminAioSettleReportInputService.class,
            new WEB3AdminAioSettleReportInputServiceImpl());

        // 出金申込問合せサービスインタフェース
        Services.registerService(
                WEB3AdminAioCashoutInqService.class,
            new WEB3AdminAioCashoutInqServiceImpl());

        // 出金申込問合せ入力サービスインタフェース
        Services.registerService(
                WEB3AdminAioCashoutInqInputService.class,
            new WEB3AdminAioCashoutInqInputServiceImpl());

        // 入金連絡確認サービスインタフェース
        Services.registerService(
                WEB3AdminAioCashinNoticeConfirmService.class,
            new WEB3AdminAioCashinNoticeConfirmServiceImpl());

        // 入金連絡確認入力サービスインタフェース
        Services.registerService(
                WEB3AdminAioCashinConfirmInputService.class,
            new WEB3AdminAioCashinConfirmInputServiceImpl());

        // 入金通知確認再処理サービス
        Services.registerService(
            WEB3AdminAioCashinNoticeConfirmReTreatmentService.class,
            new WEB3AdminAioCashinNoticeConfirmReTreatmentServiceImpl());

        // バーチャル口座入金ULサービス
        Services.registerService(
            WEB3AdminAioVirtualAccCashinULService.class,
            new WEB3AdminAioVirtualAccCashinULServiceImpl());

        //管理者入出金一覧サービス
        Services.registerService(
            WEB3AdminAioListService.class,
            new WEB3AdminAioListServiceImpl());

        // 入出金サービス（管理者）--------------------------End------

        // 証拠金振替サービス--------------------------Begin------

        // 証拠金から振替サービスインタフェース
        Services.registerService(
                WEB3AccTransChangeFromIfoDepositService.class,
            new WEB3AccTransChangeFromIfoDepositServiceImpl());

        // 証拠金から振替入力サービスインタフェース
        Services.registerService(
                WEB3AccTransChangeFromIfoDepositInputService.class,
            new WEB3AccTransChangeFromIfoDepositInputServiceImpl());

        // 証拠金への振替サービスインタフェース
        Services.registerService(
                WEB3AccTransChangeToIfoDepositService.class,
            new WEB3AccTransChangeToIfoDepositServiceImpl());

        // 証拠金への振替入力サービスインタフェース
        Services.registerService(
                WEB3AccTransChangeToIfoDepositInputService.class,
            new WEB3AccTransChangeToIfoDepositInputServiceImpl());

        // 振替請求受付サービスインタフェース
        Services.registerService(
                WEB3AccTransChangeRequestAcceptService.class,
            new WEB3AccTransChangeRequestAcceptServiceImpl());

        // 振替受付UnitServiceインタフェース
        Services.registerService(
                WEB3AccTransChangeAcceptUnitService.class,
            new WEB3AccTransChangeAcceptUnitServiceImpl());

        // 振替完了UnitServiceインタフェース
        Services.registerService(
                WEB3AccTransChangeCompleteUnitService.class,
            new WEB3AccTransChangeCompleteUnitServiceImpl());

        // 振替請求通知サービスインタフェース
        Services.registerService(
                WEB3AccTransChangeRequestNotifyService.class,
            new WEB3AccTransChangeRequestNotifyServiceImpl());

        // 振替請求通知UnitServiceインタフェース
        Services.registerService(
                WEB3AccTransChangeRequestNotifyUnitService.class,
            new WEB3AccTransChangeRequestNotifyUnitServiceImpl());
        // 証拠金振替サービス--------------------------End------

        // 証券振替サービス--------------------------Begin------

        // 証券振替サービス インタフェース
        Services.registerService(
            WEB3AioSecurityTransferService.class,
            new WEB3AioSecurityTransferServiceImpl());

        // 証券振替強制サービス  インタフェース
        Services.registerService(
            WEB3AioSecurityTransferForceService.class,
            new WEB3AioSecurityTransferForceServiceImpl());

        // 証券振替強制UnitService  インタフェース
        Services.registerService(
            WEB3AioSecurityTransferForceUnitService.class,
            new WEB3AioSecurityTransferForceUnitServiceImpl());

        // 証券振替通知サービス   インタフェース
        Services.registerService(
            WEB3AioSecurityTransferNotifyService.class,
            new WEB3AioSecurityTransferNotifyServiceImpl());

        // 証券振替通知UnitService  インタフェース
        Services.registerService(
            WEB3AioSecurityTransferNotifyUnitService.class,
            new WEB3AioSecurityTransferNotifyUnitServiceImpl());

        //証券振替入力サービス インタフェース
        Services.registerService(
            WEB3AioSecurityTransferInputService.class,
            new WEB3AioSecurityTransferInputServiceImpl());

        //特定口座振替強制サービス インタフェース
        Services.registerService(
            WEB3AioSpsecTransferForceService.class,
            new WEB3AioSpsecTransferForceServiceImpl());

        //特定口座振替強制UnitService  インタフェース
        Services.registerService(
            WEB3AioSpsecTransferForceUnitService.class,
            new WEB3AioSpsecTransferForceUnitServiceImpl());

        // 証券振替サービス--------------------------End------

        // 為替保証金サービス--------------------------Begin------

        //FX口座開設サービス インタフェース
        Services.registerService(
            WEB3FXAccOpenService.class,
            new WEB3FXAccOpenServiceImpl());

        // FX口座開設入力サービス インタフェース
        Services.registerService(
            WEB3FXAccOpenInputService.class,
            new WEB3FXAccOpenInputServiceImpl());

        // FXから振替サービス インタフェース
        Services.registerService(
            WEB3FXTransFromFXService.class,
            new WEB3FXTransFromFXServiceImpl());

        // FXから振替入力サービス インタフェース
        Services.registerService(
            WEB3FXTransFromFXInputService.class,
            new WEB3FXTransFromFXInputServiceImpl());

        // FXへの振替サービス インタフェース
        Services.registerService(
            WEB3FXTransToFXService.class,
            new WEB3FXTransToFXServiceImpl());

        // FXへの振替入力サービス インタフェース
        Services.registerService(
            WEB3FXTransToFXInputService.class,
            new WEB3FXTransToFXInputServiceImpl());

        // シングルサインオンサービス インタフェース
        Services.registerService(
            WEB3FXSingleSignOnService.class,
            new WEB3FXSingleSignOnServiceImpl());

        // 管理者FX口座開設アップロードサービスインタフェース
        Services.registerService(
            WEB3AdminFXAccOpenUploadService.class,
            new WEB3AdminFXAccOpenUploadServiceImpl());
        // 為替保証金サービス--------------------------End------

        // 為替保証金サービス（管理者）--------------------------Begin------

        // FX振替管理サービス インタフェース
        Services.registerService(
            WEB3AdminFXTransferManagementService.class,
            new WEB3AdminFXTransferManagementServiceImpl());

        // FX振替管理UnitService インタフェース
        Services.registerService(
            WEB3AdminFXTransferManagementUnitService.class,
            new WEB3AdminFXTransferManagementUnitServiceImpl());

        // 管理者FX口座開設管理サービス インタフェース
        Services.registerService(
            WEB3AdminFXAccOpenManagementService.class,
            new WEB3AdminFXAccOpenManagementServiceImpl());

        // 管理者FX口座管理サービス インタフェース
        Services.registerService(
            WEB3AdminFXAccManagementService.class,
            new WEB3AdminFXAccManagementServiceImpl());

        // FX振替注文アップロードサービス インタフェース
        Services.registerService(
            WEB3AdminFXTransferOrderUploadService.class,
            new WEB3AdminFXTransferOrderUploadServiceImpl());

        // FX振替注文UnitService インタフェース
        Services.registerService(
            WEB3AdminFXTransferOrderUnitService.class,
            new WEB3AdminFXTransferOrderUnitServiceImpl());

        // 為替保証金サービス（管理者）--------------------------End------

        //入出庫履歴サービス
        Services.registerService(
            WEB3AioInputOutputHistoryService.class,
            new WEB3AioInputOutputHistoryServiceImpl());

        // 外国株式振替連携サービス -----------Begin-------

        // 外株振替連携データ制御サービス
        Services.registerService(
            WEB3FEqConTransferDataControlService.class,
            new WEB3FEqConTransferDataControlServiceImpl());

        // 外株口座への振替サービス
        Services.registerService(
            WEB3FEqConTransferService.class,
            new WEB3FEqConTransferServiceImpl());

        // 外株口座への振替取消サービス
        Services.registerService(
            WEB3FEqConTransferCancelService.class,
            new WEB3FEqConTransferCancelServiceImpl());

        // 外株口座開設サービス
        Services.registerService(
            WEB3FEqConAccountOpenService.class,
            new WEB3FEqConAccountOpenServiceImpl());

        // 外株口座開設管理サービス
        Services.registerService(
            WEB3AdminFEqConAccountOpenMngService.class,
            new WEB3AdminFEqConAccountOpenMngServiceImpl());

        // 外株口座管理サービス
        Services.registerService(
            WEB3AdminFEqConAccountMngService.class,
            new WEB3AdminFEqConAccountMngServiceImpl());

        // 外株振替管理サービス
        Services.registerService(
            WEB3AdminFEqConTransferMngService.class,
            new WEB3AdminFEqConTransferMngServiceImpl());

        // 外国株式振替連携サービス -----------End-------

        // 担保ローン出金拘束金解除サービ -----------Begin-------
        Services.registerService(
            WEB3AdminAioSLCashOutStopReleaseService.class,
            new WEB3AdminAioSLCashOutStopReleaseServiceImpl());

        // 担保ローン出金拘束金解除サービ -----------End-------

        //証券担保ローン対応
        //証券担保ローン返済申込サービス
        Services.registerService(
            WEB3AioSLRepayApplyService.class,
            new WEB3AioSLRepayApplyServiceImpl());

        //証券担保ローン返済申込入力サービス
        Services.registerService(
            WEB3AioSLRepayApplyInputService.class,
            new WEB3AioSLRepayApplyInputServiceImpl());

        //証券担保ローンデータ制御サービス
        Services.registerService(
            WEB3AioSecuredLoanDataControlService.class,
            new WEB3AioSecuredLoanDataControlServiceImpl());

        //証券担保ローン返済取消サービス
        Services.registerService(
            WEB3AioSLRepayCancelService.class,
            new WEB3AioSLRepayCancelServiceImpl());

        //証券担保ローン返済一覧サービス
        Services.registerService(
            WEB3AioSLRepayListService.class,
            new WEB3AioSLRepayListServiceImpl());

        //SL口座開設UnitService
        Services.registerService(
            WEB3AioSLAccountOpenUnitService.class,
            new WEB3AioSLAccountOpenUnitServiceImpl());

        //担保ローン申込状況一覧サービス
        Services.registerService(
            WEB3AioSecuredLoanOfferStateListService.class,
            new WEB3AioSecuredLoanOfferStateListServiceImpl());

        //担保銘柄取消サービス
        Services.registerService(
            WEB3AdminAioSLProductCancelService.class,
            new WEB3AdminAioSLProductCancelServiceImpl());
        
        // 担保銘柄登録サービス
        Services.registerService(
            WEB3AdminAioSLProductRegistService.class,
            new WEB3AdminAioSLProductRegistServiceImpl());

        //担保登録銘柄照会サービス
        Services.registerService(
            WEB3AdminAioSLRegistProductReferenceService.class,
            new WEB3AdminAioSLRegistProductReferenceServiceImpl());

        //担保銘柄変更サービス
        Services.registerService(
            WEB3AdminAioSLProductChangeService.class,
            new WEB3AdminAioSLProductChangeServiceImpl());
    }
    /**
     * Service の Interceptor 設定処理<BR>
     * 処理開始時刻と処理終了時刻をログ出力するように設定する
     * @@throws Exception
     */
    private static void plugLogSysTimeInterceptors() throws Exception
    {
        // 入出金サービス--------------------------Begin------
        // マルチバンク電文処理サービスインタフェース
        Services.addInterceptor(
            WEB3AioMultiBankTelegramProcessService.class,
            new WEB3LogSysTimeInterceptor());

        // 決済エラーサービスインターフェイス
        Services.addInterceptor(
            WEB3AioCashinSettleErrorService.class,
            new WEB3LogSysTimeInterceptor());

        // 決済依頼サービスインターフェイス
        Services.addInterceptor(
                WEB3AioCashinSettlementService.class,
            new WEB3LogSysTimeInterceptor());

        // 決済完了サービスインタフェース
        Services.addInterceptor(
                WEB3AioCashinSettleCompleteService.class,
            new WEB3LogSysTimeInterceptor());

        // 注文要求中止サービスインターフェイス
        Services.addInterceptor(
                WEB3AioCashinSettleCancelService.class,
            new WEB3LogSysTimeInterceptor());

        // SONAR入出金サービスインターフェイス
        Services.addInterceptor(
                WEB3AioSonarCashTransService.class,
            new WEB3LogSysTimeInterceptor());

        // SONAR入出金UnitServiceインタフェース
        Services.addInterceptor(
                WEB3AioSonarCashTransUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // オンライン入金選択サービスインターフェイス
        Services.addInterceptor(
                WEB3AioCashinSelectService.class,
            new WEB3LogSysTimeInterceptor());

        // オンライン入金入力サービスインタフェース
        Services.addInterceptor(
                WEB3AioCashinInputService.class,
            new WEB3LogSysTimeInterceptor());

        // 出金取消サービスインターフェイス
        Services.addInterceptor(
                WEB3AioCashoutCancelService.class,
            new WEB3LogSysTimeInterceptor());

        // 出金取消UnitService
        Services.addInterceptor(
            WEB3AioCashoutCancelUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // 出金取消一覧サービスインタフェース
        Services.addInterceptor(
                WEB3AioCashoutCancelListService.class,
            new WEB3LogSysTimeInterceptor());

        // 出金申込サービスインターフェイス
        Services.addInterceptor(
                WEB3AioCashoutService.class,
            new WEB3LogSysTimeInterceptor());

        // 出金申込入力サービスインタフェース
        Services.addInterceptor(
                WEB3AioCashoutInputService.class,
            new WEB3LogSysTimeInterceptor());

        // 出金余力チェックサービスインターフェイスクラス
        Services.addInterceptor(
                WEB3AioCashoutTradingPowerService.class,
            new WEB3LogSysTimeInterceptor());

        // 出金余力チェックUnitServiceインターフェイス
        Services.addInterceptor(
                WEB3AioCashoutTradingPowerUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // 出金連携サービスインタセプタ
        Services.addInterceptor(
            WEB3AioOnPaymentCooperationService.class,
            new WEB3LogSysTimeInterceptor());

        // 出金連携UnitServiceインタセプタ
        Services.addInterceptor(
            WEB3AioOnPaymentCooperationUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // 入金連携サービスインタセプタ
        Services.addInterceptor(
            WEB3AioCashinCooperationService.class,
            new WEB3LogSysTimeInterceptor());

        // 入金連携通知一件サービスインタセプタ
        Services.addInterceptor(
            WEB3AioCashinCooperationNotifyUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // 入金連絡サービスインターフェイス
        Services.addInterceptor(
                WEB3AioCashinNoticeService.class,
            new WEB3LogSysTimeInterceptor());

        // 入金連絡メール送信サービスインタフェース
        Services.addInterceptor(
                WEB3AioCashinNoticeMailSendService.class,
            new WEB3LogSysTimeInterceptor());


        // 入金連絡入力サービスインターフェイス
        Services.addInterceptor(
                WEB3AioCashinNoticeInputService.class,
            new WEB3LogSysTimeInterceptor());

        // 入出金一覧サービスインタフェース
        Services.addInterceptor(
                WEB3AioCashTransferListService.class,
            new WEB3LogSysTimeInterceptor());

        // 出金受付サービスインタフェース
        Services.addInterceptor(
                WEB3AioCashoutAcceptService.class,
            new WEB3LogSysTimeInterceptor());

        // 入金受付サービスインタフェース
        Services.addInterceptor(
                WEB3AioCashinAcceptService.class,
            new WEB3LogSysTimeInterceptor());

        // 入出金完了UnitServiceインタフェース
        Services.addInterceptor(
                WEB3AioCashTransferCompleteUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // 入出金受付UnitServiceインタフェース
        Services.addInterceptor(
                WEB3AioCashTransferAcceptUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // SONAR入出金（外貨）サービスインタフェース
        Services.addInterceptor(
                WEB3AioSonarCashTransForeignService.class,
            new WEB3LogSysTimeInterceptor());

        // SONAR入出金（外貨）UnitServiceインタフェース
        Services.addInterceptor(
                WEB3AioSonarCashTransForeignUnitService.class,
            new WEB3LogSysTimeInterceptor());

        //融資額返済UnitServiceインタフェース
        Services.addInterceptor(
                WEB3AioFinanceAmountRepayUnitService.class,
            new WEB3LogSysTimeInterceptor());

        //融資額返済サービス インタフェース
        Services.addInterceptor(
                WEB3AioFinanceAmountRepayService.class,
            new WEB3LogSysTimeInterceptor());

        // 債券出金連携サービスインタフェース
        Services.addInterceptor(
                WEB3AioBondOnPaymentCooperationService.class,
            new WEB3LogSysTimeInterceptor());

        // 債券出金連携UnitServiceインタフェース
        Services.addInterceptor(
                WEB3AioBondOnPaymentCooperationUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // 出金注文トリガー発行UnitServiceインタフェース
        Services.addInterceptor(
                WEB3AioCashOutOrderTriggerIssueUnitService.class,
            new WEB3LogSysTimeInterceptor());
        
        // 入出金サービス--------------------------End------

        //入出庫サービスモデル --------------------------Begin------

        // 出庫通知サービス
        Services.addInterceptor(
            WEB3AioOutputNotifyService.class,
            new WEB3LogSysTimeInterceptor());

        // 出庫通知UnitService
        Services.addInterceptor(
            WEB3AioOutputNotifyUnitService.class,
            new WEB3LogSysTimeInterceptor());
        // 保証金への振替サービス
        Services.addInterceptor(
            WEB3MarginTransferService.class,
            new WEB3LogSysTimeInterceptor());

        //入出庫サービスモデル --------------------------End-------

        // 入出金サービス（管理者）--------------------------Begin------

        // 決済連携レポートサービスインタフェース
        Services.addInterceptor(
                WEB3AdminAioSettleReportService.class,
            new WEB3LogSysTimeInterceptor());

        // 決済連携レポート入力サービスインタフェース
        Services.addInterceptor(
                WEB3AdminAioSettleReportInputService.class,
            new WEB3LogSysTimeInterceptor());

        // 出金申込問合せサービスインタフェース
        Services.addInterceptor(
                WEB3AdminAioCashoutInqService.class,
            new WEB3LogSysTimeInterceptor());

        // 出金申込問合せ入力サービスインタフェース
        Services.addInterceptor(
                WEB3AdminAioCashoutInqInputService.class,
            new WEB3LogSysTimeInterceptor());

        // 入金連絡確認サービスインタフェース
        Services.addInterceptor(
                WEB3AdminAioCashinNoticeConfirmService.class,
            new WEB3LogSysTimeInterceptor());

        // 入金連絡確認入力サービスインタフェース
        Services.addInterceptor(
                WEB3AdminAioCashinConfirmInputService.class,
            new WEB3LogSysTimeInterceptor());

        // その他件数照会サービスインタフェース
        Services.addInterceptor(
                WEB3AdminAioOtherCountReferenceService.class,
            new WEB3LogSysTimeInterceptor());

        // 入金通知確認再処理サービスインタフェース
        Services.addInterceptor(
            WEB3AdminAioCashinNoticeConfirmReTreatmentService.class,
            new WEB3LogSysTimeInterceptor());

        // バーチャル口座入金ULサービスインタフェース
        Services.addInterceptor(
            WEB3AdminAioVirtualAccCashinULService.class,
            new WEB3LogSysTimeInterceptor());

        //管理者入出金一覧サービス
        Services.addInterceptor(
            WEB3AdminAioListService.class,
            new WEB3LogSysTimeInterceptor());
        // 入出金サービス（管理者）--------------------------End------

        // 証拠金振替サービス--------------------------Begin------

        // 証拠金から振替サービスインタフェース
        Services.addInterceptor(
                WEB3AccTransChangeFromIfoDepositService.class,
            new WEB3LogSysTimeInterceptor());

        // 証拠金から振替入力サービスインタフェース
        Services.addInterceptor(
                WEB3AccTransChangeFromIfoDepositInputService.class,
            new WEB3LogSysTimeInterceptor());

        // 証拠金への振替サービスインタフェース
        Services.addInterceptor(
                WEB3AccTransChangeToIfoDepositService.class,
            new WEB3LogSysTimeInterceptor());

        // 証拠金への振替入力サービスインタフェース
        Services.addInterceptor(
                WEB3AccTransChangeToIfoDepositInputService.class,
            new WEB3LogSysTimeInterceptor());

        // 振替請求受付サービスインタフェース
        Services.addInterceptor(
                WEB3AccTransChangeRequestAcceptService.class,
            new WEB3LogSysTimeInterceptor());

        // 振替受付UnitServiceインタフェース
        Services.addInterceptor(
                WEB3AccTransChangeAcceptUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // 振替完了UnitServiceインタフェース
        Services.addInterceptor(
                WEB3AccTransChangeCompleteUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // 振替請求通知サービスインタフェース
        Services.addInterceptor(
                WEB3AccTransChangeRequestNotifyService.class,
            new WEB3LogSysTimeInterceptor());

        // 振替請求通知UnitServiceインタフェース
        Services.addInterceptor(
                WEB3AccTransChangeRequestNotifyUnitService.class,
            new WEB3LogSysTimeInterceptor());
        // 証拠金振替サービス--------------------------End------

        // 証券振替サービス--------------------------Start------

        // 証券振替サービス インタフェース
        Services.addInterceptor(
            WEB3AioSecurityTransferService.class,
            new WEB3LogSysTimeInterceptor());

        // 証券振替強制サービス  インタフェース
        Services.addInterceptor(
            WEB3AioSecurityTransferForceService.class,
            new WEB3LogSysTimeInterceptor());

        // 証券振替強制UnitService  インタフェース
        Services.addInterceptor(
            WEB3AioSecurityTransferForceUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // 証券振替通知サービス   インタフェース
        Services.addInterceptor(
            WEB3AioSecurityTransferNotifyService.class,
            new WEB3LogSysTimeInterceptor());

        // 証券振替通知UnitService  インタフェース
        Services.addInterceptor(
            WEB3AioSecurityTransferNotifyUnitService.class,
            new WEB3LogSysTimeInterceptor());

        //証券振替入力サービス インタフェース
        Services.addInterceptor(
            WEB3AioSecurityTransferInputService.class,
            new WEB3LogSysTimeInterceptor());

        //特定口座振替強制サービス  インタフェース
        Services.addInterceptor(
            WEB3AioSpsecTransferForceService.class,
            new WEB3LogSysTimeInterceptor());

        //特定口座振替強制UnitService  インタフェース
        Services.addInterceptor(
            WEB3AioSpsecTransferForceUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // 証券振替サービス--------------------------End------


        // 為替保証金サービス--------------------------Begin------

        //FX口座開設サービス インタフェース
        Services.addInterceptor(
            WEB3FXAccOpenService.class,
            new WEB3LogSysTimeInterceptor());

        // FX口座開設入力サービス インタフェース
        Services.addInterceptor(
            WEB3FXAccOpenInputService.class,
            new WEB3LogSysTimeInterceptor());

        // FXから振替サービス インタフェース
        Services.addInterceptor(
            WEB3FXTransFromFXService.class,
            new WEB3LogSysTimeInterceptor());

        // FXから振替入力サービス インタフェース
        Services.addInterceptor(
            WEB3FXTransFromFXInputService.class,
            new WEB3LogSysTimeInterceptor());

        // FXへの振替サービス インタフェース
        Services.addInterceptor(
            WEB3FXTransToFXService.class,
            new WEB3LogSysTimeInterceptor());

        // FXへの振替入力サービス インタフェース
        Services.addInterceptor(
            WEB3FXTransToFXInputService.class,
            new WEB3LogSysTimeInterceptor());

        // シングルサインオンサービス インタフェース
        Services.addInterceptor(
            WEB3FXSingleSignOnService.class,
            new WEB3FXSingleSignOnServiceInterceptor());

        // 管理者FX口座開設アップロードサービスインタフェース
        Services.addInterceptor(
            WEB3AdminFXAccOpenUploadService.class,
            new WEB3LogSysTimeInterceptor());

        //接続共通インタフェース
        Services.addInterceptor(
            WEB3FXConnCommonService.class,
            new WEB3LogSysTimeInterceptor());

        //FX振替可能額表示サービスインターフェイス
        Services.addInterceptor(
            WEB3FXTransferAbleAmtDisplayService.class,
            new WEB3LogSysTimeInterceptor());
        
        //口座開設接続インタフェース
        Services.addInterceptor(
            WEB3FXAccOpenConnection.class,
            new WEB3LogSysTimeInterceptor());

        //振替接続インタフェース
        Services.addInterceptor(
            WEB3FXTransConnection.class,
            new WEB3LogSysTimeInterceptor());
        
        // 為替保証金サービス--------------------------End------

        // 為替保証金サービス（管理者）--------------------------Begin------

        // FX振替管理サービス インタフェース
        Services.addInterceptor(
            WEB3AdminFXTransferManagementService.class,
            new WEB3LogSysTimeInterceptor());

        // FX振替管理UnitService インタフェース
        Services.addInterceptor(
            WEB3AdminFXTransferManagementUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // 管理者FX口座開設管理サービス インタフェース
        Services.addInterceptor(
            WEB3AdminFXAccOpenManagementService.class,
            new WEB3LogSysTimeInterceptor());

        // 管理者FX口座管理サービス インタフェース
        Services.addInterceptor(
            WEB3AdminFXAccManagementService.class,
            new WEB3LogSysTimeInterceptor());

        // FX振替注文アップロードサービス インタフェース
        Services.addInterceptor(
            WEB3AdminFXTransferOrderUploadService.class,
            new WEB3LogSysTimeInterceptor());

        // FX振替注文UnitService インタフェース
        Services.addInterceptor(
            WEB3AdminFXTransferOrderUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // 為替保証金サービス（管理者）--------------------------End------

        //入出庫履歴サービス
        Services.addInterceptor(
            WEB3AioInputOutputHistoryService.class,
            new WEB3LogSysTimeInterceptor());

        // 外国株式振替連携サービス -----------Begin-------

        // 外株振替連携データ制御サービス
        Services.addInterceptor(
            WEB3FEqConTransferDataControlService.class,
            new WEB3LogSysTimeInterceptor());

        // 外株口座への振替サービス
        Services.addInterceptor(
            WEB3FEqConTransferService.class,
            new WEB3LogSysTimeInterceptor());

        // 外株口座への振替取消サービス
        Services.addInterceptor(
            WEB3FEqConTransferCancelService.class,
            new WEB3LogSysTimeInterceptor());

        // 外株口座開設サービス
        Services.addInterceptor(
            WEB3FEqConAccountOpenService.class,
            new WEB3LogSysTimeInterceptor());

        // 外株口座開設管理サービス
        Services.addInterceptor(
            WEB3AdminFEqConAccountOpenMngService.class,
            new WEB3LogSysTimeInterceptor());

        // 外株口座管理サービス
        Services.addInterceptor(
            WEB3AdminFEqConAccountMngService.class,
            new WEB3LogSysTimeInterceptor());

        // 外株振替管理サービス
        Services.addInterceptor(
            WEB3AdminFEqConTransferMngService.class,
            new WEB3LogSysTimeInterceptor());

        // 外国株式振替連携サービス -----------End-------

        // 担保ローン出金拘束金解除サービ -----------Begin-------
        Services.addInterceptor(
            WEB3AdminAioSLCashOutStopReleaseService.class,
            new WEB3LogSysTimeInterceptor());

        // 担保ローン出金拘束金解除サービ -----------End-------
        //証券担保ローン対応
        //証券担保ローン返済申込サービス
        Services.addInterceptor(
            WEB3AioSLRepayApplyService.class,
            new WEB3LogSysTimeInterceptor());

        //証券担保ローン返済申込入力サービス
        Services.addInterceptor(
            WEB3AioSLRepayApplyInputService.class,
            new WEB3LogSysTimeInterceptor());

        //証券担保ローンデータ制御サービス
        Services.addInterceptor(
            WEB3AioSecuredLoanDataControlService.class,
            new WEB3LogSysTimeInterceptor());

        //証券担保ローン返済取消サービス
        Services.addInterceptor(
            WEB3AioSLRepayCancelService.class,
            new WEB3LogSysTimeInterceptor());

        //証券担保ローン返済一覧サービス
        Services.addInterceptor(
            WEB3AioSLRepayListService.class,
            new WEB3LogSysTimeInterceptor());

        //SL口座開設UnitService
        Services.addInterceptor(
            WEB3AioSLAccountOpenUnitService.class,
            new WEB3LogSysTimeInterceptor());

        //担保ローン申込状況一覧サービス
        Services.addInterceptor(
            WEB3AioSecuredLoanOfferStateListService.class,
            new WEB3LogSysTimeInterceptor());
        
        //担保銘柄取消サービス
        Services.addInterceptor(
            WEB3AdminAioSLProductCancelService.class,
            new WEB3LogSysTimeInterceptor());
        
        //担保銘柄登録サービス
        Services.addInterceptor(
            WEB3AdminAioSLProductRegistService.class,
            new WEB3LogSysTimeInterceptor());

        //担保登録銘柄照会サービス
        Services.addInterceptor(
            WEB3AdminAioSLRegistProductReferenceService.class,
            new WEB3LogSysTimeInterceptor());
        
        //担保銘柄変更サービス
        Services.addInterceptor(
            WEB3AdminAioSLProductChangeService.class,
            new WEB3LogSysTimeInterceptor());
        
        //担保銘柄登録制御サービス 
        Services.addInterceptor(
            WEB3AdminAioSLProductRegistControlService.class,
            new WEB3LogSysTimeInterceptor());
        
    }
    /**
     * Service の Interceptor 設定処理<BR>
     * ServiceInterceptor設定
     * @@throws Exception
     */
    private static void plugServiceInterceptors() throws Exception
    {
        // 入出金サービス--------------------------Begin------
        // マルチバンク電文処理サービスインタフェース
        Services.addInterceptor(
            WEB3AioMultiBankTelegramProcessService.class,
            new WEB3AioMultiBankTelegramProcessServiceInterceptor());

        // 決済エラーサービスインターフェイス
        Services.addInterceptor(
            WEB3AioCashinSettleErrorService.class,
            new WEB3AioCashinSettleErrorServiceInterceptor());

        // 決済依頼サービスインターフェイス
        Services.addInterceptor(
                WEB3AioCashinSettlementService.class,
            new WEB3AioCashinSettlementServiceInterceptor());

        // 決済完了サービスインタフェース
        Services.addInterceptor(
                WEB3AioCashinSettleCompleteService.class,
            new WEB3AioCashinSettleCompleteServiceInterceptor());

        // 注文要求中止サービスインターフェイス
        Services.addInterceptor(
                WEB3AioCashinSettleCancelService.class,
            new WEB3AioCashinSettleCancelServiceInterceptor());

        // SONAR入出金UnitServiceインタフェース
        Services.addInterceptor(
                WEB3AioSonarCashTransUnitService.class,
            new WEB3AioSonarCashTransUnitServiceInterceptor());

        // オンライン入金選択サービスインターフェイス
        Services.addInterceptor(
                WEB3AioCashinSelectService.class,
            new WEB3AioCashinSelectServiceInterceptor());

        // オンライン入金入力サービスインタフェース
        Services.addInterceptor(
                WEB3AioCashinInputService.class,
            new WEB3AioCashinInputServiceInterceptor());

        // 出金取消サービスインターフェイス
        Services.addInterceptor(
                WEB3AioCashoutCancelService.class,
            new WEB3AioCashoutCancelServiceInterceptor());

        // 出金取消一覧サービスインタフェース
        Services.addInterceptor(
                WEB3AioCashoutCancelListService.class,
            new WEB3AioCashoutCancelListServiceInterceptor());

        // 出金申込サービスインターフェイス
        Services.addInterceptor(
                WEB3AioCashoutService.class,
            new WEB3AioCashoutServiceInterceptor());

        // 出金申込入力サービスインタフェース
        Services.addInterceptor(
                WEB3AioCashoutInputService.class,
            new WEB3AioCashoutInputServiceInterceptor());

        // 出金余力チェックUnitServiceインターフェイス
        Services.addInterceptor(
                WEB3AioCashoutTradingPowerUnitService.class,
            new WEB3AioCashoutTradingPowerUnitServiceInterceptor());

        // 出金連携サービスインターフェイス
        Services.addInterceptor(
                WEB3AioOnPaymentCooperationService.class,
            new WEB3AioOnPaymentCooperationServiceInterceptor());

        // 出金連携UnitService インターフェイス
        Services.addInterceptor(
                WEB3AioOnPaymentCooperationUnitService.class,
            new WEB3AioOnPaymentCooperationUnitServiceInterceptor());

        // 入金連携通知一件サービスインターフェイス
        Services.addInterceptor(
                WEB3AioCashinCooperationNotifyUnitService.class,
            new WEB3AioCashinCooperationNotifyUnitServiceInterceptor());

        // 入金連絡サービスインターフェイス
        Services.addInterceptor(
                WEB3AioCashinNoticeService.class,
            new WEB3AioCashinNoticeServiceInterceptor());

        // 入金連絡入力サービスインターフェイス
        Services.addInterceptor(
                WEB3AioCashinNoticeInputService.class,
            new WEB3AioCashinNoticeInputServiceInterceptor());

        // 入出金一覧サービスインタフェース
        Services.addInterceptor(
                WEB3AioCashTransferListService.class,
            new WEB3AioCashTransferListServiceInterceptor());

        // 入出金完了UnitServiceインタフェース
        Services.addInterceptor(
                WEB3AioCashTransferCompleteUnitService.class,
            new WEB3AioCashTransferCompleteUnitServiceInterceptor());

        // 入出金受付UnitServiceインタフェース
        Services.addInterceptor(
                WEB3AioCashTransferAcceptUnitService.class,
            new WEB3AioCashTransferAcceptUnitServiceInterceptor());

        // SONAR入出金（外貨）UnitServiceインタフェース
        Services.addInterceptor(
            WEB3AioSonarCashTransForeignUnitService.class,
            new WEB3AioSonarCashTransForeignUnitServiceInterceptor());

        //融資額返済UnitServiceインタフェース
        Services.addInterceptor(
                WEB3AioFinanceAmountRepayUnitService.class,
                new WEB3AioFinanceAmountRepayUnitServiceInterceptor());

        // 債券出金連携UnitServiceインタフェース
        Services.addInterceptor(
            WEB3AioBondOnPaymentCooperationUnitService.class,
            new WEB3AioBondOnPaymentCooperationUnitServiceInterceptor());
        // 入出金サービス--------------------------End------

        // 入出金サービス（管理者）--------------------------Begin------

        // 入金連絡確認サービスインタフェース
        Services.addInterceptor(
                WEB3AdminAioCashinNoticeConfirmService.class,
            new WEB3AdminAioCashinNoticeConfirmServiceInterceptor());

        // 入金連絡確認入力サービスインタフェース
        Services.addInterceptor(
                WEB3AdminAioCashinConfirmInputService.class,
            new WEB3AdminAioCashinConfirmInputServiceInterceptor());

        // 入金通知確認再処理サービスインタフェース
        Services.addInterceptor(
            WEB3AdminAioCashinNoticeConfirmReTreatmentService.class,
            new WEB3AdminAioCashinNoticeConfirmReTreatmentServiceInterceptor());

        // バーチャル口座入金ULサービスインタフェース
        Services.addInterceptor(
            WEB3AdminAioVirtualAccCashinULService.class,
            new WEB3AdminAioVirtualAccCashinULServiceInterceptor());

        // 入出金サービス（管理者）--------------------------End------

        // 証拠金振替サービス--------------------------Begin------

        // 証拠金から振替サービスインタフェース
        Services.addInterceptor(
                WEB3AccTransChangeFromIfoDepositService.class,
            new WEB3AccTransChangeFromIfoDepositServiceInterceptor());

        // 証拠金から振替入力サービスインタフェース
        Services.addInterceptor(
                WEB3AccTransChangeFromIfoDepositInputService.class,
            new WEB3AccTransChangeFromIfoDepositInputServiceInterceptor());

        // 証拠金への振替サービスインタフェース
        Services.addInterceptor(
                WEB3AccTransChangeToIfoDepositService.class,
            new WEB3AccTransChangeToIfoDepositServiceInterceptor());

        // 証拠金への振替入力サービスインタフェース
        Services.addInterceptor(
                WEB3AccTransChangeToIfoDepositInputService.class,
            new WEB3AccTransChangeToIfoDepositInputServiceInterceptor());

        // 振替受付UnitServiceインタフェース
        Services.addInterceptor(
                WEB3AccTransChangeAcceptUnitService.class,
            new WEB3AccTransChangeAcceptUnitServiceInterceptor());

        // 振替完了UnitServiceインタフェース
        Services.addInterceptor(
                WEB3AccTransChangeCompleteUnitService.class,
            new WEB3AccTransChangeCompleteUnitServiceInterceptor());

        // 振替請求通知UnitServiceインタフェース
        Services.addInterceptor(
                WEB3AccTransChangeRequestNotifyUnitService.class,
            new WEB3AccTransChangeRequestNotifyUnitServiceInterceptor());
        // 証拠金振替サービス--------------------------End------

        // 証券振替サービス--------------------------Start------

        // 証券振替サービス インタフェース
        Services.addInterceptor(
            WEB3AioSecurityTransferService.class,
            new WEB3AioSecurityTransferServiceInterceptor());

        // 証券振替強制UnitService  インタフェース
        Services.addInterceptor(
            WEB3AioSecurityTransferForceUnitService.class,
            new WEB3AioSecurityTransferForceUnitServiceInterceptor());

        // 証券振替通知UnitService  インタフェース
        Services.addInterceptor(
            WEB3AioSecurityTransferNotifyUnitService.class,
            new WEB3AioSecurityTransferNotifyUnitServiceInterceptor());

        //証券振替入力サービス インタフェース
        Services.addInterceptor(
            WEB3AioSecurityTransferInputService.class,
            new WEB3AioSecurityTransferInputServiceInterceptor());

        //特定口座振替強制UnitService  インタフェース
        Services.addInterceptor(
            WEB3AioSpsecTransferForceUnitService.class,
            new WEB3AioSpsecTransferForceUnitServiceInterceptor());

        // 証券振替サービス--------------------------End------

        // 為替保証金サービス--------------------------Begin------

        //FX口座開設サービス インタフェース
        Services.addInterceptor(
            WEB3FXAccOpenService.class,
            new WEB3FXAccOpenServiceInterceptor());

        // FX口座開設入力サービス インタフェース
        Services.addInterceptor(
            WEB3FXAccOpenInputService.class,
            new WEB3FXAccOpenInputServiceInterceptor());

        // FXから振替サービス インタフェース
        Services.addInterceptor(
            WEB3FXTransFromFXService.class,
            new WEB3FXTransFromFXServiceInterceptor());

        // FXから振替入力サービス インタフェース
        Services.addInterceptor(
            WEB3FXTransFromFXInputService.class,
            new WEB3FXTransFromFXInputServiceInterceptor());

        // FXへの振替サービス インタフェース
        Services.addInterceptor(
            WEB3FXTransToFXService.class,
            new WEB3FXTransToFXServiceInterceptor());

        // FXへの振替入力サービス インタフェース
        Services.addInterceptor(
            WEB3FXTransToFXInputService.class,
            new WEB3FXTransToFXInputServiceInterceptor());

        // シングルサインオンサービス インタフェース
        Services.addInterceptor(
            WEB3FXSingleSignOnService.class,
            new WEB3FXSingleSignOnServiceInterceptor());

        // 管理者FX口座開設アップロードサービスインタフェース
        Services.addInterceptor(
            WEB3AdminFXAccOpenUploadService.class,
            new WEB3AdminFXAccOpenUploadServiceInterceptor());
        // 為替保証金サービス--------------------------End------

        // 為替保証金サービス（管理者）--------------------------Begin------

        // FX振替管理UnitService インタフェース
        Services.addInterceptor(
            WEB3AdminFXTransferManagementUnitService.class,
            new WEB3AdminFXTransferManagementUnitServiceInterceptor());

        // 管理者FX口座開設管理サービス インタフェース
        Services.addInterceptor(
            WEB3AdminFXAccOpenManagementService.class,
            new WEB3AdminFXAccOpenManagementServiceInterceptor());

        // 管理者FX口座管理サービス インタフェース
        Services.addInterceptor(
            WEB3AdminFXAccManagementService.class,
            new WEB3AdminFXAccManagementServiceInterceptor());

        // FX振替注文アップロードサービス インタフェース
        Services.addInterceptor(
            WEB3AdminFXTransferOrderUploadService.class,
            new WEB3AdminFXTransferOrderServiceInterceptor());

        // 為替保証金サービス（管理者）--------------------------End------

        //入出庫サービスモデル --------------------------Begin------

        // 出庫通知UnitService
        Services.addInterceptor(
            WEB3AioOutputNotifyUnitService.class,
            new WEB3AioOutputNotifyUnitServiceInterceptor());

        //入出庫履歴サービス
        Services.addInterceptor(
            WEB3AioInputOutputHistoryService.class,
            new WEB3AioInputOutputHistoryServiceInterceptor());

        //入出庫サービスモデル --------------------------End------

        // 外国株式振替連携サービス -----------Begin-------

        // 外株口座への振替サービス
        Services.addInterceptor(
            WEB3FEqConTransferService.class,
            new WEB3FEqConTransferServiceInterceptor());

        // 外株口座への振替取消サービス
        Services.addInterceptor(
            WEB3FEqConTransferCancelService.class,
            new WEB3FEqConTransferCancelServiceInterceptor());

        // 外株口座開設サービス
        Services.addInterceptor(
            WEB3FEqConAccountOpenService.class,
            new WEB3FEqConAccountOpenServiceInterceptor());

        // 外株口座開設管理サービス
        Services.addInterceptor(
            WEB3AdminFEqConAccountOpenMngService.class,
            new WEB3AdminFEqConAccountOpenMngServiceInterceptor());

        // 外株口座管理サービス
        Services.addInterceptor(
            WEB3AdminFEqConAccountMngService.class,
            new WEB3LogSysTimeInterceptor());

        // 外株振替管理サービス
        Services.addInterceptor(
            WEB3AdminFEqConTransferMngService.class,
            new WEB3AdminFEqConAccountMngServiceInterceptor());

        // 外国株式振替連携サービス -----------End-------
        
        //担保ローン出金拘束金解除サービ--------------------------Begin------
        Services.addInterceptor(
            WEB3AdminAioSLCashOutStopReleaseService.class,
            new WEB3AdminAioSLCashOutStopReleaseServiceInterceptor());
        
        //担保ローン出金拘束金解除サービ--------------------------End------

        //証券担保ローン対応
        //証券担保ローン返済申込サービス
        Services.addInterceptor(
            WEB3AioSLRepayApplyService.class,
            new WEB3AioSLRepayApplyServiceInterceptor());

        //証券担保ローン返済申込入力サービス
        Services.addInterceptor(
            WEB3AioSLRepayApplyInputService.class,
            new WEB3AioSLRepayApplyInputServiceInterceptor());

        //証券担保ローン返済取消サービス
        Services.addInterceptor(
            WEB3AioSLRepayCancelService.class,
            new WEB3AioSLRepayCancelServiceInterceptor());

        //証券担保ローン返済一覧サービス
        Services.addInterceptor(
            WEB3AioSLRepayListService.class,
            new WEB3AioSLRepayListServiceInterceptor());

        //SL口座開設UnitService
        Services.addInterceptor(
            WEB3AioSLAccountOpenUnitService.class,
            new WEB3AioSLAccountOpenServiceInterceptor());
        
        //担保銘柄取消サービス
        Services.addInterceptor(
            WEB3AdminAioSLProductCancelService.class,
            new WEB3AdminAioSLProductRegistServiceInterceptor());
        
        //担保銘柄登録サービス
        Services.addInterceptor(
            WEB3AdminAioSLProductRegistService.class,
            new WEB3AdminAioSLProductRegistServiceInterceptor());

        //担保登録銘柄照会サービス
        Services.addInterceptor(
            WEB3AdminAioSLRegistProductReferenceService.class,
            new WEB3AdminAioSLProductRegistServiceInterceptor());
        
        //担保銘柄変更サービス
        Services.addInterceptor(
            WEB3AdminAioSLProductChangeService.class,
            new WEB3AdminAioSLProductRegistServiceInterceptor());
    }
    /**
     * Service の Interceptor 設定処理<BR>
     * 自動トランザクション設定
     * @@throws Exception
     */
    private static void plugTransactionalInterceptors() throws Exception
    {
        // 入出金サービス--------------------------Begin------
        // マルチバンク電文処理サービスインタフェース
        Services.addInterceptor(
            WEB3AioMultiBankTelegramProcessService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 決済エラーサービスインターフェイス
        Services.addInterceptor(
            WEB3AioCashinSettleErrorService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 決済依頼サービスインターフェイス
        Services.addInterceptor(
                WEB3AioCashinSettlementService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 決済完了サービスインタフェース
        Services.addInterceptor(
                WEB3AioCashinSettleCompleteService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 注文要求中止サービスインターフェイス
        Services.addInterceptor(
                WEB3AioCashinSettleCancelService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // SONAR入出金サービスインターフェイス
        Services.addInterceptor(
                WEB3AioSonarCashTransService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // SONAR入出金UnitServiceインタフェース
        Services.addInterceptor(
                WEB3AioSonarCashTransUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // オンライン入金選択サービスインターフェイス
        Services.addInterceptor(
                WEB3AioCashinSelectService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // オンライン入金入力サービスインタフェース
        Services.addInterceptor(
                WEB3AioCashinInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 出金取消サービスインターフェイス
        Services.addInterceptor(
                WEB3AioCashoutCancelService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 出金取消UnitService
        Services.addInterceptor(
            WEB3AioCashoutCancelUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 出金取消一覧サービスインタフェース
        Services.addInterceptor(
                WEB3AioCashoutCancelListService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 出金申込サービスインターフェイス
        Services.addInterceptor(
                WEB3AioCashoutService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 出金申込入力サービスインタフェース
        Services.addInterceptor(
                WEB3AioCashoutInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 出金余力チェックサービスインターフェイスクラス
        Services.addInterceptor(
                WEB3AioCashoutTradingPowerService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 出金余力チェックUnitServiceインターフェイス
        Services.addInterceptor(
                WEB3AioCashoutTradingPowerUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        // 出金連携サービスインターフェイスクラス
        Services.addInterceptor(
                WEB3AioOnPaymentCooperationService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 出金連携UnitServiceインターフェイス
        Services.addInterceptor(
                WEB3AioOnPaymentCooperationUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        // 入金連携サービス
        Services.addInterceptor(
                WEB3AioCashinCooperationService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 入金連携通知一件サービス
        Services.addInterceptor(
                WEB3AioCashinCooperationNotifyUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));


        // 入金連絡サービスインターフェイス
        Services.addInterceptor(
                WEB3AioCashinNoticeService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 入金連絡メール送信サービスインタフェース
        Services.addInterceptor(
                WEB3AioCashinNoticeMailSendService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));


        // 入金連絡入力サービスインターフェイス
        Services.addInterceptor(
                WEB3AioCashinNoticeInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 入出金一覧サービスインタフェース
        Services.addInterceptor(
                WEB3AioCashTransferListService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 出金受付サービスインタフェース
        Services.addInterceptor(
                WEB3AioCashoutAcceptService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 入金受付サービスインタフェース
        Services.addInterceptor(
                WEB3AioCashinAcceptService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 入出金完了UnitServiceインタフェース
        Services.addInterceptor(
                WEB3AioCashTransferCompleteUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 入出金受付UnitServiceインタフェース
        Services.addInterceptor(
                WEB3AioCashTransferAcceptUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // SONAR入出金（外貨）サービスインタフェース
        Services.addInterceptor(
                WEB3AioSonarCashTransForeignService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // SONAR入出金（外貨）UnitServiceインタフェース
        Services.addInterceptor(
                WEB3AioSonarCashTransForeignUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //外貨入出金受付インタフェース
        Services.addInterceptor(
                WEB3AioForeignCashTransAcceptService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //融資額返済UnitServiceインタフェース
        Services.addInterceptor(
                WEB3AioFinanceAmountRepayUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //融資額返済サービス インタフェース
        Services.addInterceptor(
                WEB3AioFinanceAmountRepayService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //債券出金連携サービスインタフェース
        Services.addInterceptor(
                WEB3AioBondOnPaymentCooperationService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 債券出金連携UnitServiceインタフェース
        Services.addInterceptor(
                WEB3AioBondOnPaymentCooperationUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //出金注文トリガー発行UnitServiceインタフェース
        Services.addInterceptor(
                WEB3AioCashOutOrderTriggerIssueUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));
        // 保証金への振替サービス
        Services.addInterceptor(
                WEB3MarginTransferService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        // 入出金サービス--------------------------End------

        // 入出金サービス（管理者）--------------------------Begin------

        // 決済連携レポートサービスインタフェース
        Services.addInterceptor(
                WEB3AdminAioSettleReportService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 決済連携レポート入力サービスインタフェース
        Services.addInterceptor(
                WEB3AdminAioSettleReportInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 出金申込問合せサービスインタフェース
        Services.addInterceptor(
                WEB3AdminAioCashoutInqService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 出金申込問合せ入力サービスインタフェース
        Services.addInterceptor(
                WEB3AdminAioCashoutInqInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 入金連絡確認サービスインタフェース
        Services.addInterceptor(
                WEB3AdminAioCashinNoticeConfirmService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 入金連絡確認入力サービスインタフェース
        Services.addInterceptor(
                WEB3AdminAioCashinConfirmInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // その他件数照会サービスインタフェース
        Services.addInterceptor(
                WEB3AdminAioOtherCountReferenceService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 入金通知確認再処理サービスインタフェース
        Services.addInterceptor(
            WEB3AdminAioCashinNoticeConfirmReTreatmentService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // バーチャル口座入金ULサービスインタフェース
        Services.addInterceptor(
            WEB3AdminAioVirtualAccCashinULService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //管理者入出金一覧サービス
        Services.addInterceptor(
            WEB3AdminAioListService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        // 入出金サービス（管理者）--------------------------End------

        //入出庫サービスモデル --------------------------Begin------

        // 出庫通知サービス
        Services.addInterceptor(
            WEB3AioOutputNotifyService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 出庫通知UnitService
        Services.addInterceptor(
            WEB3AioOutputNotifyUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //入出庫サービスモデル --------------------------End--------

        // 証拠金振替サービス--------------------------Begin------

        // 証拠金から振替サービスインタフェース
        Services.addInterceptor(
                WEB3AccTransChangeFromIfoDepositService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 証拠金から振替入力サービスインタフェース
        Services.addInterceptor(
                WEB3AccTransChangeFromIfoDepositInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 証拠金への振替サービスインタフェース
        Services.addInterceptor(
                WEB3AccTransChangeToIfoDepositService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 証拠金への振替入力サービスインタフェース
        Services.addInterceptor(
                WEB3AccTransChangeToIfoDepositInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 振替請求受付サービスインタフェース
        Services.addInterceptor(
                WEB3AccTransChangeRequestAcceptService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 振替受付UnitServiceインタフェース
        Services.addInterceptor(
                WEB3AccTransChangeAcceptUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 振替完了UnitServiceインタフェース
        Services.addInterceptor(
                WEB3AccTransChangeCompleteUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 振替請求通知サービスインタフェース
        Services.addInterceptor(
                WEB3AccTransChangeRequestNotifyService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 振替請求通知UnitServiceインタフェース
        Services.addInterceptor(
                WEB3AccTransChangeRequestNotifyUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        // 証拠金振替サービス--------------------------End------

        // 証券振替サービス--------------------------Start------

        // 証券振替サービス インタフェース
        Services.addInterceptor(
            WEB3AioSecurityTransferService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 証券振替強制サービス  インタフェース
        Services.addInterceptor(
            WEB3AioSecurityTransferForceService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 証券振替強制UnitService  インタフェース
        Services.addInterceptor(
            WEB3AioSecurityTransferForceUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 証券振替通知サービス   インタフェース
        Services.addInterceptor(
            WEB3AioSecurityTransferNotifyService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 証券振替通知UnitService  インタフェース
        Services.addInterceptor(
            WEB3AioSecurityTransferNotifyUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //証券振替入力サービス インタフェース
        Services.addInterceptor(
            WEB3AioSecurityTransferInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //特定口座振替強制サービス インタフェース
        Services.addInterceptor(
            WEB3AioSpsecTransferForceService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //特定口座振替強制UnitService  インタフェース
        Services.addInterceptor(
            WEB3AioSpsecTransferForceUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 証券振替サービス--------------------------End------

        // 為替保証金サービス--------------------------Begin------

        // FX口座開設入力サービス インタフェース
        Services.addInterceptor(
            WEB3FXAccOpenInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // FXから振替サービス インタフェース
        Services.addInterceptor(
            WEB3FXTransFromFXService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // FXから振替入力サービス インタフェース
        Services.addInterceptor(
            WEB3FXTransFromFXInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // FXへの振替サービス インタフェース
        Services.addInterceptor(
            WEB3FXTransToFXService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // FXへの振替入力サービス インタフェース
        Services.addInterceptor(
            WEB3FXTransToFXInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // FXデータ制御サービス インタフェース
        Services.addInterceptor(
            WEB3FXDataControlService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        // シングルサインオンサービス インタフェース
        Services.addInterceptor(
            WEB3FXSingleSignOnService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        // 管理者FX口座開設アップロードサービスインタフェース
        Services.addInterceptor(
            WEB3AdminFXAccOpenUploadService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //接続共通インタフェース
        Services.addInterceptor(
            WEB3FXConnCommonService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //FX振替可能額表示サービスインターフェイス
        Services.addInterceptor(
            WEB3FXTransferAbleAmtDisplayService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //口座開設接続インタフェース
        Services.addInterceptor(
            WEB3FXAccOpenConnection.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //振替接続インタフェース
        Services.addInterceptor(
            WEB3FXTransConnection.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 為替保証金サービス--------------------------End------

        // 為替保証金サービス（管理者）--------------------------Begin------

        // FX振替管理UnitService インタフェース
        Services.addInterceptor(
            WEB3AdminFXTransferManagementUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        // 管理者FX口座開設管理サービス インタフェース
        Services.addInterceptor(
            WEB3AdminFXAccOpenManagementService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 管理者FX口座管理サービス インタフェース
        Services.addInterceptor(
            WEB3AdminFXAccManagementService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // FX振替注文アップロードサービス インタフェース
        Services.addInterceptor(
            WEB3AdminFXTransferOrderUploadService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // FX振替注文UnitService インタフェース
        Services.addInterceptor(
            WEB3AdminFXTransferOrderUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));
        // 為替保証金サービス（管理者）--------------------------End------

        //入出庫履歴サービス
        Services.addInterceptor(
            WEB3AioInputOutputHistoryService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        // 外国株式振替連携サービス -----------Begin-------

        // 外株振替連携データ制御サービス
        Services.addInterceptor(
            WEB3FEqConTransferDataControlService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        // 外株口座への振替サービス
        Services.addInterceptor(
            WEB3FEqConTransferService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        // 外株口座への振替取消サービス
        Services.addInterceptor(
            WEB3FEqConTransferCancelService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        // 外株口座開設サービス
        Services.addInterceptor(
            WEB3FEqConAccountOpenService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        // 外株口座開設管理サービス
        Services.addInterceptor(
            WEB3AdminFEqConAccountOpenMngService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        // 外株口座管理サービス
        Services.addInterceptor(
            WEB3AdminFEqConAccountMngService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        // 外株振替管理サービス
        Services.addInterceptor(
            WEB3AdminFEqConTransferMngService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        // 外国株式振替連携サービス -----------End-------
        
        // 担保ローン出金拘束金解除サービ -----------Begin-------
        Services.addInterceptor(
            WEB3AdminAioSLCashOutStopReleaseService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));
        
        // 担保ローン出金拘束金解除サービ -----------End-------

        //証券担保ローン対応
        //証券担保ローン返済申込サービス
        Services.addInterceptor(
            WEB3AioSLRepayApplyService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        //証券担保ローン返済申込入力サービス
        Services.addInterceptor(
            WEB3AioSLRepayApplyInputService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        //証券担保ローンデータ制御サービス
        Services.addInterceptor(
            WEB3AioSecuredLoanDataControlService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        //証券担保ローン返済取消サービス
        Services.addInterceptor(
            WEB3AioSLRepayCancelService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        //証券担保ローン返済一覧サービス
        Services.addInterceptor(
            WEB3AioSLRepayListService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        //SL口座開設UnitService
        Services.addInterceptor(
            WEB3AioSLAccountOpenUnitService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        //担保ローン申込状況一覧サービス
        Services.addInterceptor(
            WEB3AioSecuredLoanOfferStateListService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        //担保銘柄取消サービス
        Services.addInterceptor(
            WEB3AdminAioSLProductCancelService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //担保銘柄登録サービス
        Services.addInterceptor(
            WEB3AdminAioSLProductRegistService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        //担保登録銘柄照会サービス
        Services.addInterceptor(
            WEB3AdminAioSLRegistProductReferenceService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        //担保銘柄変更サービス
        Services.addInterceptor(
            WEB3AdminAioSLProductChangeService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        //担保銘柄登録制御サービス
        Services.addInterceptor(
            WEB3AdminAioSLProductRegistControlService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));
    }
    /**
     * MQ-Gateway の Interceptor 設定処理
     * @@throws Exception
     */
    private static void plugMQGatewayInterceptors() throws Exception
    {

        // 入出金サービス--------------------------Begin------

        //入金連携一件処理サービス
        Services.addInterceptor(
                WEB3AioCashinCooperationNotifyUnitService.class,
            new WEB3MQGatewayInterceptor());

        //出金申込サービス
        Services.addInterceptor(
            WEB3AioCashoutService.class,
            new WEB3MQGatewayInterceptor());

        //出金連携サービス
        Services.addInterceptor(
            WEB3AioOnPaymentCooperationService.class,
            new WEB3MQGatewayInterceptor());

        //出金取消サービス
        Services.addInterceptor(
            WEB3AioCashoutCancelService.class,
            new WEB3MQGatewayInterceptor());

        //マルチバンク電文処理サービスインターフェイス
        Services.addInterceptor(
            WEB3AioMultiBankTelegramProcessService.class,
            new WEB3MQGatewayInterceptor());

        //出金余力チェックサービスインターフェイス
        Services.addInterceptor(
            WEB3AioCashoutTradingPowerService.class,
            new WEB3MQGatewayInterceptor());
        // 入出金サービス--------------------------End------

        // 入出金サービス（管理者）--------------------------Begin------

        //出金申込問合せサービスインターフェイス
        Services.addInterceptor(
            WEB3AdminAioCashoutInqService.class,
            new WEB3MQGatewayInterceptor());
        // 入出金サービス（管理者）--------------------------End------

        // 証拠金振替サービス--------------------------Begin------

        // 証拠金から振替サービスインタフェース
        Services.addInterceptor(
            WEB3AccTransChangeFromIfoDepositService.class,
            new WEB3MQGatewayInterceptor());

        // 証拠金への振替サービスインタフェース
        Services.addInterceptor(
            WEB3AccTransChangeToIfoDepositService.class,
            new WEB3MQGatewayInterceptor());
        // 証拠金振替サービス--------------------------End------

        // 証券振替サービス--------------------------Start------

        //証券振替サービス
        Services.addInterceptor(
            WEB3AioSecurityTransferService.class,
            new WEB3MQGatewayInterceptor());
        // 証券振替サービス--------------------------End------

        // 為替保証金サービス--------------------------Start------

        // FXから振替サービス
        Services.addInterceptor(
            WEB3FXTransFromFXService.class,
            new WEB3MQGatewayInterceptor());

        // FXへの振替サービス
        Services.addInterceptor(
            WEB3FXTransToFXService.class,
            new WEB3MQGatewayInterceptor());
        // 為替保証金サービス--------------------------End------

        // 為替保証金サービス（管理者）--------------------------Begin------

        // FX振替注文アップロードサービス インタフェース
        Services.addInterceptor(
            WEB3AdminFXTransferOrderUploadService.class,
            new WEB3MQGatewayInterceptor());

        // 為替保証金サービス（管理者）--------------------------End------

        // 外国株式振替連携サービス--------------------------Start------

        // 外株口座への振替サービス
        Services.addInterceptor(
            WEB3FEqConTransferService.class,
            new WEB3MQGatewayInterceptor());

        // 外国株式振替連携サービス--------------------------End------
        
        //債券出金連携サービス--------------------------Start------
        Services.addInterceptor(
            WEB3AioBondOnPaymentCooperationUnitService.class,
            new WEB3MQGatewayInterceptor());
        //債券出金連携サービス--------------------------End------

        //出金注文トリガー発行UnitService--------------------------Start------
        Services.addInterceptor(
            WEB3AioCashOutOrderTriggerIssueUnitService.class,
            new WEB3MQGatewayInterceptor());
        //出金注文トリガー発行UnitService--------------------------End------

        //証券担保ローン返済取消
        Services.addInterceptor(
            WEB3AioSLRepayCancelService.class,
            new WEB3MQGatewayInterceptor());

        //証券担保ローン返済申込
        Services.addInterceptor(
            WEB3AioSLRepayApplyService.class,
            new WEB3MQGatewayInterceptor());
    }
    /**
     * Message の登録処理
     * @@throws Exception
     */
    private static void plugMessages() throws Exception
    {
        //FXへの振替SOAPリクエスト
        regClass(WEB3FXTransToFXCompleteSoapRequest.class);
        //FXへの振替SOAPレスポンス
        regClass(WEB3FXTransToFXCompleteSoapResponse.class);
        //管理者・FX口座開設申込ダウンロードリクエスト
        regClass(WEB3AdminFXAccOpenApplyDownloadRequest.class);
        //管理者・FX口座開設申込ダウンロードレスポンス
        regClass(WEB3AdminFXAccOpenApplyDownloadResponse.class);

        //管理者・FX口座開設アップロード中止リクエスト
        regClass(WEB3AdminFXAccOpenUploadCancelRequest.class);
        //管理者・FX口座開設アップロード中止レスポンス
        regClass(WEB3AdminFXAccOpenUploadCancelResponse.class);
        //管理者・FX口座開設アップロード完了レスポンス
        regClass(WEB3AdminFXAccOpenUploadCompleteRequest.class);
        //管理者・FX口座開設アップロード完了レスポンス
        regClass(WEB3AdminFXAccOpenUploadCompleteResponse.class);
        //管理者・FX口座開設アップロード確認リクエスト
        regClass(WEB3AdminFXAccOpenUploadConfirmRequest.class);
        //管理者・FX口座開設アップロード確認レスポンス
        regClass(WEB3AdminFXAccOpenUploadConfirmResponse.class);
        //管理者・FX口座開設アップロード入力リクエスト
        regClass(WEB3AdminFXAccOpenUploadInputRequest.class);
        //管理者・FX口座開設アップロード入力レスポンス
        regClass(WEB3AdminFXAccOpenUploadInputResponse.class);
        //管理者・FX振替注文アップロード中止リクエスト
        regClass(WEB3AdminFXTransferUploadCancelRequest.class);
        //管理者・FX振替注文アップロード中止レスポンス
        regClass(WEB3AdminFXTransferUploadCancelResponse.class);
        //管理者・FX振替注文アップロード完了リクエスト
        regClass(WEB3AdminFXTransferUploadCompleteRequest.class);
        //管理者・FX振替注文アップロード完了レスポンス
        regClass(WEB3AdminFXTransferUploadCompleteResponse.class);
        //管理者・FX振替注文アップロード確認リクエスト
        regClass(WEB3AdminFXTransferUploadConfirmRequest.class);
        //管理者・FX振替注文アップロード確認レスポンス
        regClass(WEB3AdminFXTransferUploadConfirmResponse.class);
        //管理者・FX振替注文アップロード入力リクエスト
        regClass(WEB3AdminFXTransferUploadInputRequest.class);
        //管理者・FX振替注文アップロード入力レスポンス
        regClass(WEB3AdminFXTransferUploadInputResponse.class);
        // SONAR入出金リクエスト
        regClass(WEB3AioSonarCashTransRequest.class);
        // SONAR入出金レスポンス
        regClass(WEB3AioSonarCashTransResponse.class);
        // オンライン入金確認リクエスト
        regClass(WEB3AioCashinConfirmRequest.class);
        // オンライン入金確認レスポンス
        regClass(WEB3AioCashinConfirmResponse.class);
        // オンライン入金選択画面リクエスト
        regClass(WEB3AioCashinSelectRequest.class);
        // オンライン入金選択画面レスポンス
        regClass(WEB3AioCashinSelectResponse.class);
        // オンライン入金入力リクエスト
        regClass(WEB3AioCashinInputRequest.class);
        // オンライン入金入力レスポンス
        regClass(WEB3AioCashinInputResponse.class);
        // 決済エラーリクエスト
        regClass(WEB3AioCashinSettleErrorRequest.class);
        // 決済エラーレスポンス
        regClass(WEB3AioCashinSettleErrorResponse.class);
        // 決済依頼リクエスト
        regClass(WEB3AioCashinSettlementRequest.class);
        // 決済依頼レスポンス
        regClass(WEB3AioCashinSettlementResponse.class);
        // 決済完了リクエスト
        regClass(WEB3AioCashinSettleCompleteRequest.class);
        // 決済完了レスポンス
        regClass(WEB3AioCashinSettleCompleteResponse.class);
        // 出金取消一覧リクエスト
        regClass(WEB3AioCashoutCancelListRequest.class);
        // 出金取消一覧レスポンス
        regClass(WEB3AioCashoutCancelListResponse.class);
        // 出金取消確認リクエスト
        regClass(WEB3AioCashoutCancelConfirmRequest.class);
        // 出金取消確認レスポンス
        regClass(WEB3AioCashoutCancelConfirmResponse.class);
        // 出金取消完了リクエスト
        regClass(WEB3AioCashoutCancelCompleteRequest.class);
        // 出金取消完了レスポンス
        regClass(WEB3AioCashoutCancelCompleteResponse.class);
        // 出金受付リクエスト
        regClass(WEB3AioCashoutAcceptRequest.class);
        // 出金受付レスポンス
        regClass(WEB3AioCashoutAcceptResponse.class);
        // 出金申込確認リクエスト
        regClass(WEB3AioCashoutConfirmRequest.class);
        // 出金申込確認レスポンス
        regClass(WEB3AioCashoutConfirmResponse.class);
        // 出金申込完了リクエスト
        regClass(WEB3AioCashoutCompleteRequest.class);
        // 出金申込完了レスポンス
        regClass(WEB3AioCashoutCompleteResponse.class);
        // 出金申込入力リクエスト
        regClass(WEB3AioCashoutInputRequest.class);
        // 出金申込入力レスポンス
        regClass(WEB3AioCashoutInputResponse.class);
        // 出金余力チェックリクエスト
        regClass(WEB3AioCashoutTradingPowerCheckRequest.class);
        // 出金余力チェックレスポンス
        regClass(WEB3AioCashoutTradingPowerCheckResponse.class);
        // 注文要求中止リクエスト
        regClass(WEB3AioCashinSettleCancelRequest.class);
        // 注文要求中止レスポンス
        regClass(WEB3AioCashinSettleCancelResponse.class);
        // 入金受付リクエスト
        regClass(WEB3AioCashinAcceptRequest.class);
        // 入金受付レスポンス
        regClass(WEB3AioCashinAcceptResponse.class);
        // 出金連携リクエスト
        regClass(WEB3AioOnPaymentCooperationRequest.class);
        // 出金連携レスポンス
        regClass(WEB3AioOnPaymentCooperationResponse.class);
        // 入金連携通知リクエスト
        regClass(WEB3AioCashinCooperationNotifyRequest.class);
        // 入金連携通知レスポンス
        regClass(WEB3AioCashinCooperationNotifyResponse.class);
        // 入金連絡確認リクエスト
        regClass(WEB3AioCashinNoticeConfirmRequest.class);
        // 入金連絡確認レスポンス
        regClass(WEB3AioCashinNoticeConfirmResponse.class);
        // 入金連絡完了リクエスト
        regClass(WEB3AioCashinNoticeCompleteRequest.class);
        // 入金連絡完了レスポンス
        regClass(WEB3AioCashinNoticeCompleteResponse.class);
        // 入金連絡入力リクエスト
        regClass(WEB3AioCashinNoticeInputRequest.class);
        // 入金連絡入力レスポンス
        regClass(WEB3AioCashinNoticeInputResponse.class);
        // 入出金一覧リクエスト
        regClass(WEB3AioCashTransferListRequest.class);
        // 入出金一覧レスポンス
        regClass(WEB3AioCashTransferListResponse.class);
        // 証拠金から振替確認リクエスト
        regClass(WEB3AccTransChangeFromIfoDepositConfirmRequest.class);
        // 証拠金から振替確認レスポンス
        regClass(WEB3AccTransChangeFromIfoDepositConfirmResponse.class);
        // 証拠金から振替完了リクエスト
        regClass(WEB3AccTransChangeFromIfoDepositCompleteRequest.class);
        // 証拠金から振替完了レスポンス
        regClass(WEB3AccTransChangeFromIfoDepositCompleteResponse.class);
        // 証拠金から振替入力リクエスト
        regClass(WEB3AccTransChangeFromIfoDepositInputRequest.class);
        // 証拠金から振替入力レスポンス
        regClass(WEB3AccTransChangeFromIfoDepositInputResponse.class);
        // 証拠金への振替確認リクエスト
        regClass(WEB3AccTransChangeToIfoDepositConfirmRequest.class);
        // 証拠金への振替確認レスポンス
        regClass(WEB3AccTransChangeToIfoDepositConfirmResponse.class);
        // 証拠金への振替完了リクエスト
        regClass(WEB3AccTransChangeToIfoDepositCompleteRequest.class);
        // 証拠金への振替完了レスポンス
        regClass(WEB3AccTransChangeToIfoDepositCompleteResponse.class);
        // 証拠金への振替入力リクエスト
        regClass(WEB3AccTransChangeToIfoDepositInputRequest.class);
        // 証拠金への振替入力レスポンス
        regClass(WEB3AccTransChangeToIfoDepositInputResponse.class);
        // 振替請求受付リクエスト
        regClass(WEB3AccTransChangeRequestAcceptRequest.class);
        // 振替請求受付レスポンス
        regClass(WEB3AccTransChangeRequestAcceptResponse.class);
        // 振替請求通知リクエスト
        regClass(WEB3AccTransChangeRequestNotifyRequest.class);
        // 振替請求通知レスポンス
        regClass(WEB3AccTransChangeRequestNotifyResponse.class);
        // その他件数照会入力リクエスト
        regClass(WEB3AdminAioOtherCountReferenceInputRequest.class);
        // その他件数照会入力レスポンス
        regClass(WEB3AdminAioOtherCountReferenceInputResponse.class);
        // その他件数照会リクエスト
        regClass(WEB3AdminAioOtherCountReferenceRequest.class);
        // その他件数照会レスポンス
        regClass(WEB3AdminAioOtherCountReferenceResponse.class);
        // 決済連携レポート一覧リクエスト
        regClass(WEB3AdminAioSettleReportListRequest.class);
        // 決済連携レポート一覧レスポンス
        regClass(WEB3AdminAioSettleReportListResponse.class);
        // 決済連携レポート入力リクエスト
        regClass(WEB3AdminAioSettleReportInputRequest.class);
        // 決済連携レポート入力レスポンス
        regClass(WEB3AdminAioSettleReportInputResponse.class);
        // 出金申込問合せ一覧リクエスト
        regClass(WEB3AdminAioCashoutInqListRequest.class);
        // 出金申込問合せ一覧レスポンス
        regClass(WEB3AdminAioCashoutInqListResponse.class);
        // 出金申込問合せ取消確認リクエスト
        regClass(WEB3AdminAioCashoutInqCancelConfirmRequest.class);
        // 出金申込問合せ取消確認レスポンス
        regClass(WEB3AdminAioCashoutInqCancelConfirmResponse.class);
        // 出金申込問合せ取消完了リクエスト
        regClass(WEB3AdminAioCashoutInqCancelCompleteRequest.class);
        // 出金申込問合せ取消完了レスポンス
        regClass(WEB3AdminAioCashoutInqCancelCompleteResponse.class);
        // 出金申込問合せ出金確認リクエスト
        regClass(WEB3AdminAioCashoutInqConfirmRequest.class);
        // 出金申込問合せ出金確認レスポンス
        regClass(WEB3AdminAioCashoutInqConfirmResponse.class);
        // 出金申込問合せ出金完了リクエスト
        regClass(WEB3AdminAioCashoutInqCompleteRequest.class);
        // 出金申込問合せ出金完了レスポンス
        regClass(WEB3AdminAioCashoutInqCompleteResponse.class);
        // 出金申込問合せ入力リクエスト
        regClass(WEB3AdminAioCashoutInqInputRequest.class);
        // 出金申込問合せ入力レスポンス
        regClass(WEB3AdminAioCashoutInqInputResponse.class);
        // 入金連絡確認一覧リクエスト
        regClass(WEB3AdminAioCashinConfirmListRequest.class);
        // 入金連絡確認一覧レスポンス
        regClass(WEB3AdminAioCashinConfirmListResponse.class);
        // 入金連絡確認入力リクエスト
        regClass(WEB3AdminAioCashinConfirmInputRequest.class);
        // 入金連絡確認入力レスポンス
        regClass(WEB3AdminAioCashinConfirmInputResponse.class);
        // SONAR入出金（外貨）リクエスト
        regClass(WEB3AioSonarCashTransForeignRequest.class);
        // SONAR入出金（外貨）レスポンス
        regClass(WEB3AioSonarCashTransForeignResponse.class);
        //外貨入出金受付リクエスト
        regClass(WEB3AioForeignCashTransAcceptRequest.class);
        //外貨入出金受付レスポンス
        regClass(WEB3AioForeignCashTransAcceptResponse.class);
        //融資額返済リクエスト
        regClass(WEB3AioFinanceAmountRepayRequest.class);
        //融資額返済レスポンス
        regClass(WEB3AioFinanceAmountRepayResponse.class);
        // 債券出金連携リクエスト
        regClass(WEB3AioBondOnPaymentCooperationRequest.class);
        //債券出金連携レスポンス
        regClass(WEB3AioBondOnPaymentCooperationResponse.class);

        // 証券振替一覧リクエスト
        regClass(WEB3AioSecurityTransferListRequest.class);
        // 証券振替一覧レスポンス
        regClass(WEB3AioSecurityTransferListResponse.class);
        // 証券振替確認リクエスト
        regClass(WEB3AioSecurityTransferConfirmRequest.class);
        // 証券振替確認レスポンス
        regClass(WEB3AioSecurityTransferConfirmResponse.class);
        // 証券振替完了リクエスト
        regClass(WEB3AioSecurityTransferCompleteRequest.class);
        // 証券振替完了レスポンス
        regClass(WEB3AioSecurityTransferCompleteResponse.class);
        // 証券振替強制リクエスト
        regClass(WEB3AioSecurityTransferForceRequest.class);
        // 証券振替強制レスポンス
        regClass(WEB3AioSecurityTransferForceResponse.class);
        // 証券振替通知リクエスト
        regClass(WEB3AioSecurityTransferNotifyRequest.class);
        // 証券振替通知レスポンス
        regClass(WEB3AioSecurityTransferNotifyResponse.class);
        // 証券振替入力リクエスト
        regClass(WEB3AioSecurityTransferInputRequest.class);
        // 証券振替入力レスポンス
        regClass(WEB3AioSecurityTransferInputResponse.class);

        //特定口座振替強制リクエスト
        regClass(WEB3AioSpsecTransferForceRequest.class);

        //特定口座振替強制レスポンス
        regClass(WEB3AioSpsecTransferForceResponse.class);

        // FX口座開設確認リクエスト
        regClass(WEB3FXAccOpenConfirmRequest.class);
        // FX口座開設確認レスポンス
        regClass(WEB3FXAccOpenConfirmResponse.class);
        // FX口座開設完了リクエスト
        regClass(WEB3FXAccOpenCompleteRequest.class);
        // FX口座開設完了レスポンス
        regClass(WEB3FXAccOpenCompleteResponse.class);
        // FX口座開設入力リクエスト
        regClass(WEB3FXAccOpenInputRequest.class);
        // FX口座開設入力レスポンス
        regClass(WEB3FXAccOpenInputResponse.class);
        // FX口座開設依頼リクエスト
        regClass(WEB3FXAccOpenAskingRequest.class);
        // FX口座開設依頼レスポンス
        regClass(WEB3FXAccOpenAskingResponse.class);
        // FX口座開設取引同意リクエスト
        regClass(WEB3FXAccOpenTradeAgreementRequest.class);
        // FX口座開設取引同意レスポンス
        regClass(WEB3FXAccOpenTradeAgreementResponse.class);
        // FXから振替確認リクエスト
        regClass(WEB3FXTransFromFXConfirmRequest.class);
        // FXから振替確認レスポンス
        regClass(WEB3FXTransFromFXConfirmResponse.class);
        // FXから振替完了リクエスト
        regClass(WEB3FXTransFromFXCompleteRequest.class);
        // FXから振替完了レスポンス
        regClass(WEB3FXTransFromFXCompleteResponse.class);
        // FXから振替入力リクエスト
        regClass(WEB3FXTransFromFXInputRequest.class);
        // FXから振替入力レスポンス
        regClass(WEB3FXTransFromFXInputResponse.class);
        // FXから振替依頼リクエスト
        regClass(WEB3FXTransFromFXAskingRequest.class);
        // FXから振替依頼レスポンス
        regClass(WEB3FXTransFromFXAskingResponse.class);
        // FXへの振替確認リクエスト
        regClass(WEB3FXTransToFXConfirmRequest.class);
        // FXへの振替確認レスポンス
        regClass(WEB3FXTransToFXConfirmResponse.class);
        // FXへの振替完了リクエスト
        regClass(WEB3FXTransToFXCompleteRequest.class);
        // FXへの振替完了レスポンス
        regClass(WEB3FXTransToFXCompleteResponse.class);
        // FXへの振替入力リクエスト
        regClass(WEB3FXTransToFXInputRequest.class);
        // FXへの振替入力レスポンス
        regClass(WEB3FXTransToFXInputResponse.class);
        // FXへの振替依頼リクエスト
        regClass(WEB3FXTransToFXAskingRequest.class);
        // FXへの振替依頼レスポンス
        regClass(WEB3FXTransToFXAskingResponse.class);
        // シングルサインオンリクエスト
        regClass(WEB3FXSingleSignOnRequest.class);
        // シングルサインオンレスポンス
        regClass(WEB3FXSingleSignOnResponse.class);
        // FX取引同意リクエスト
        regClass(WEB3FXTradeAgreementRequest.class);
        // FX取引同意レスポンス
        regClass(WEB3FXTradeAgreementResponse.class);
        // 管理者・FX振替一覧リクエスト
        regClass(WEB3AdminFXTransferListRequest.class);
        // 管理者・FX振替一覧レスポンス
        regClass(WEB3AdminFXTransferListResponse.class);
        // 管理者・FX振替一覧条件入力リクエスト
        regClass(WEB3AdminFXTransferListConditionRequest.class);
        // 管理者・FX振替一覧条件入力レスポンス
        regClass(WEB3AdminFXTransferListConditionResponse.class);
        // 管理者・FX振替取消確認リクエスト
        regClass(WEB3AdminFXTransferCancelConfirmRequest.class);
        // 管理者・FX振替取消確認レスポンス
        regClass(WEB3AdminFXTransferCancelConfirmResponse.class);
        // 管理者・FX振替取消完了リクエスト
        regClass(WEB3AdminFXTransferCancelCompleteRequest.class);
        // 管理者・FX振替取消完了レスポンス
        regClass(WEB3AdminFXTransferCancelCompleteResponse.class);
        // 管理者・FX口座開設ステータス更新確認リクエスト
        regClass(WEB3AdminFXAccOpenStatusUpdConfirmRequest.class);
        // 管理者・FX口座開設ステータス更新確認レスポンス
        regClass(WEB3AdminFXAccOpenStatusUpdConfirmResponse.class);
        // 管理者・FX口座開設ステータス更新完了リクエスト
        regClass(WEB3AdminFXAccOpenStatusUpdCompleteRequest.class);
        // 管理者・FX口座開設ステータス更新完了レスポンス
        regClass(WEB3AdminFXAccOpenStatusUpdCompleteResponse.class);
        // 管理者・FX口座開設ステータス更新入力リクエスト
        regClass(WEB3AdminFXAccOpenStatusUpdInputRequest.class);
        // 管理者・FX口座開設ステータス更新入力レスポンス
        regClass(WEB3AdminFXAccOpenStatusUpdInputResponse.class);
        // 管理者・FX口座開設申込一覧リクエスト
        regClass(WEB3AdminFXAccOpenApplyListRequest.class);
        // 管理者・FX口座開設申込一覧レスポンス
        regClass(WEB3AdminFXAccOpenApplyListResponse.class);
        // 管理者・FX口座開設申込一覧条件入力リクエスト
        regClass(WEB3AdminFXAccOpenApplyListConditionRequest.class);
        // 管理者・FX口座開設申込一覧条件入力レスポンス
        regClass(WEB3AdminFXAccOpenApplyListConditionResponse.class);
        // 管理者・FX口座情報変更確認リクエスト
        regClass(WEB3AdminFXAccInfoChangeConfirmRequest.class);
        // 管理者・FX口座情報変更確認レスポンス
        regClass(WEB3AdminFXAccInfoChangeConfirmResponse.class);
        // 管理者・FX口座情報変更完了リクエスト
        regClass(WEB3AdminFXAccInfoChangeCompleteRequest.class);
        // 管理者・FX口座情報変更完了レスポンス
        regClass(WEB3AdminFXAccInfoChangeCompleteResponse.class);
        // 管理者・FX口座情報変更入力リクエスト
        regClass(WEB3AdminFXAccInfoChangeInputRequest.class);
        // 管理者・FX口座情報変更入力レスポンス
        regClass(WEB3AdminFXAccInfoChangeInputResponse.class);
        // 管理者・FX口座情報検索リクエスト
        regClass(WEB3AdminFXAccInfoSearchRequest.class);
        // 管理者・FX口座情報検索レスポンス
        regClass(WEB3AdminFXAccInfoSearchResponse.class);
        // 管理者・FX口座情報検索条件入力リクエスト
        regClass(WEB3AdminFXAccInfoSearchConditionRequest.class);
        // 管理者・FX口座情報検索条件入力レスポンス
        regClass(WEB3AdminFXAccInfoSearchConditionResponse.class);

        //入出庫履歴リクエスト
        regClass(WEB3AioInputOutputHistoryListRequest.class);
        //入出庫履歴レスポンス
        regClass(WEB3AioInputOutputHistoryListResponse.class);


        //  外株口座への振替確認リクエスト
        regClass(WEB3FEqConTransferConfirmRequest.class);
        //  外株口座への振替確認レスポンス
        regClass(WEB3FEqConTransferConfirmResponse.class);
        //  外株口座への振替完了リクエスト
        regClass(WEB3FEqConTransferCompleteRequest.class);
        //  外株口座への振替完了レスポンス
        regClass(WEB3FEqConTransferCompleteResponse.class);
        //  外株口座への振替取消確認リクエスト
        regClass(WEB3FEqConTransferCancelConfirmRequest.class);
        //  外株口座への振替取消確認レスポンス
        regClass(WEB3FEqConTransferCancelConfirmResponse.class);
        //  外株口座への振替取消完了リクエスト
        regClass(WEB3FEqConTransferCancelCompleteRequest.class);
        //  外株口座への振替取消完了レスポンス
        regClass(WEB3FEqConTransferCancelCompleteResponse.class);
        //  外株口座への振替取消選択リクエスト
        regClass(WEB3FEqConTransferCancelSelectRequest.class);
        //  外株口座への振替取消選択レスポンス
        regClass(WEB3FEqConTransferCancelSelectResponse.class);
        //  外株口座への振替入力リクエスト
        regClass(WEB3FEqConTransferInputRequest.class);
        //  外株口座への振替入力レスポンス
        regClass(WEB3FEqConTransferInputResponse.class);
        //  外株口座開設確認リクエスト
        regClass(WEB3FEqConAccountOpenConfirmRequest.class);
        //  外株口座開設確認レスポンス
        regClass(WEB3FEqConAccountOpenConfirmResponse.class);
        //  外株口座開設完了リクエスト
        regClass(WEB3FEqConAccountOpenCompleteRequest.class);
        //  外株口座開設完了レスポンス
        regClass(WEB3FEqConAccountOpenCompleteResponse.class);
        //  外株口座開設管理ステータス更新確認リクエスト
        regClass(WEB3AdminFEqConAccountOpenMngStatusUpdConfirmRequest.class);
        //  外株口座開設管理ステータス更新確認レスポンス
        regClass(WEB3AdminFEqConAccountOpenMngStatusUpdConfirmResponse.class);
        //  外株口座開設管理ステータス更新完了リクエスト
        regClass(WEB3AdminFEqConAccountOpenMngStatusUpdCompleteRequest.class);
        //  外株口座開設管理ステータス更新完了レスポンス
        regClass(WEB3AdminFEqConAccountOpenMngStatusUpdCompleteResponse.class);
        //  外株口座開設管理ステータス更新入力リクエスト
        regClass(WEB3AdminFEqConAccountOpenMngStatusUpdInputRequest.class);
        //  外株口座開設管理ステータス更新入力レスポンス
        regClass(WEB3AdminFEqConAccountOpenMngStatusUpdInputResponse.class);
        //  外株口座開設管理一覧ダウンロードリクエスト
        regClass(WEB3AdminFEqConAccountOpenMngListDownloadRequest.class);
        //  外株口座開設管理一覧ダウンロードレスポンス
        regClass(WEB3AdminFEqConAccountOpenMngListDownloadResponse.class);
        //  外株口座開設管理一覧リクエスト
        regClass(WEB3AdminFEqConAccountOpenMngListRequest.class);
        //  外株口座開設管理一覧レスポンス
        regClass(WEB3AdminFEqConAccountOpenMngListResponse.class);
        //  外株口座開設管理条件入力リクエスト
        regClass(WEB3AdminFEqConAccountOpenMngSrcInputRequest.class);
        //  外株口座開設管理条件入力レスポンス
        regClass(WEB3AdminFEqConAccountOpenMngSrcInputResponse.class);
        //  外株口座開設取引同意リクエスト
        regClass(WEB3FEqConAccountOpenAgreementRequest.class);
        //  外株口座開設取引同意レスポンス
        regClass(WEB3FEqConAccountOpenAgreementResponse.class);
        //  外株口座開設状況変更確認リクエスト
        regClass(WEB3AdminFEqConAccountStateChangeConfirmRequest.class);
        //  外株口座開設状況変更確認レスポンス
        regClass(WEB3AdminFEqConAccountStateChangeConfirmResponse.class);
        //  外株口座開設状況変更完了リクエスト
        regClass(WEB3AdminFEqConAccountStateChangeCompleteRequest.class);
        //  外株口座開設状況変更完了レスポンス
        regClass(WEB3AdminFEqConAccountStateChangeCompleteResponse.class);
        //  外株口座開設入力リクエスト
        regClass(WEB3FEqConAccountOpenInputRequest.class);
        //  外株口座開設入力レスポンス
        regClass(WEB3FEqConAccountOpenInputResponse.class);
        //  外株口座情報検索リクエスト
        regClass(WEB3AdminFEqConAccountInfoSearchRequest.class);
        //  外株口座情報検索レスポンス
        regClass(WEB3AdminFEqConAccountInfoSearchResponse.class);
        //  外株口座情報検索条件入力リクエスト
        regClass(WEB3AdminFEqConAccountInfoSearchInputRequest.class);
        //  外株口座情報検索条件入力レスポンス
        regClass(WEB3AdminFEqConAccountInfoSearchInputResponse.class);
        //  外株振替一覧リクエスト
        regClass(WEB3AdminFEqConTransferListRequest.class);
        //  外株振替一覧レスポンス
        regClass(WEB3AdminFEqConTransferListResponse.class);
        //  外株振替一覧条件入力リクエスト
        regClass(WEB3AdminFEqConTransferListInputRequest.class);
        //  外株振替一覧条件入力レスポンス
        regClass(WEB3AdminFEqConTransferListInputResponse.class);
        //  出庫通知リクエスト
        regClass(WEB3AioOutputNotifyRequest.class);
        //  出庫通知レスポンス
        regClass(WEB3AioOutputNotifyResponse.class);

        //入金通知検索リクエスト
        regClass(WEB3AdminAioCashinNoticeSearchRequest.class);
        //入金通知検索レスポンス
        regClass(WEB3AdminAioCashinNoticeSearchResponse.class);
        //入金通知訂正確認リクエスト
        regClass(WEB3AdminAioCashinNoticeChangeConfirmRequest.class);
        //入金通知訂正確認レスポンス
        regClass(WEB3AdminAioCashinNoticeChangeConfirmResponse.class);
        //入金通知訂正完了リクエスト
        regClass(WEB3AdminAioCashinNoticeChangeCompleteRequest.class);
        //入金通知訂正完了レスポンス
        regClass(WEB3AdminAioCashinNoticeChangeCompleteResponse.class);
        //入金通知訂正共通リクエスト
        regClass(WEB3AdminAioCashinChangeCommonRequest.class);
        // 入金通知訂正入力画面リクエスト
        regClass(WEB3AdminAioCashinNoticeChangeInputRequest.class);
        //入金通知訂正入力画面レスポンス
        regClass(WEB3AdminAioCashinNoticeChangeInputResponse.class);

        //バーチャル口座入金UL入力リクエスト
        regClass(WEB3AdminAioVirtualAccCashinULInputRequest.class);
        //バーチャル口座入金UL入力レスポンス
        regClass(WEB3AdminAioVirtualAccCashinULInputResponse.class);
        //バーチャル口座入金UL確認リクエスト
        regClass(WEB3AdminAioVirtualAccCashinULConfirmRequest.class);
        //バーチャル口座入金UL確認レスポンス
        regClass(WEB3AdminAioVirtualAccCashinULConfirmResponse.class);
        //バーチャル口座入金UL完了リクエスト
        regClass(WEB3AdminAioVirtualAccCashinULCompleteRequest.class);
        //バーチャル口座入金UL完了レスポンス
        regClass(WEB3AdminAioVirtualAccCashinULCompleteResponse.class);
        //バーチャル口座入金UL中止リクエスト
        regClass(WEB3AdminAioVirtualAccCashinULCancelRequest.class);
        //バーチャル口座入金UL中止レスポンス
        regClass(WEB3AdminAioVirtualAccCashinULCancelResponse.class);

        //出金申込問合せダウンロードリクエスト
        regClass(WEB3AdminAioCashoutInqDownloadRequest.class);
        //出金申込問合せダウンロードレスポンス
        regClass(WEB3AdminAioCashoutInqDownloadResponse.class);

        //入出金一覧ダウンロードリクエスト
        regClass(WEB3AdminAioCashTransferListDownloadRequest.class);
        //入出金一覧ダウンロードレスポンス
        regClass(WEB3AdminAioCashTransferListDownloadResponse.class);
        //入出金一覧入力リクエスト
        regClass(WEB3AdminAioCashTransferListInputRequest.class);
        //入出金一覧入力レスポンス
        regClass(WEB3AdminAioCashTransferListInputResponse.class);
        //入出金一覧結果リクエスト
        regClass(WEB3AdminAioCashTransferListRequest.class);
        //入出金一覧結果レスポンス
        regClass(WEB3AdminAioCashTransferListResponse.class);
        //入出金一覧結果リクエスト
        regClass(WEB3AdminAioCashTransferListRequest.class);
        
        // 証券担保ローン出金拘束金一覧リクエスト
        regClass(WEB3AdminSLRestraintMoneyListRequest.class);
        // 証券担保ローン出金拘束金一覧レスポンス
        regClass(WEB3AdminSLRestraintMoneyListResponse.class);
        // 証券担保ローン出金停止解除確認リクエスト
        regClass(WEB3AdminSLCashOutStopReleaseConfirmRequest.class);
        // 証券担保ローン出金停止解除確認レスポンス
        regClass(WEB3AdminSLCashOutStopReleaseConfirmResponse.class);
        // 証券担保ローン出金停止解除完了リクエスト
        regClass(WEB3AdminSLCashOutStopReleaseCompleteRequest.class);
        // 証券担保ローン出金停止解除完了レスポンス
        regClass(WEB3AdminSLCashOutStopReleaseCompleteResponse.class);

        //証券担保ローン対応
        //管理者証券担保ローン申込顧客一覧リクエスト
        regClass(WEB3AdminSLAccountOpenApplyListRequest.class);
        //管理者証券担保ローン申込顧客一覧レスポンス
        regClass(WEB3AdminSLAccountOpenApplyListResponse.class);

        //SL口座開設申込リクエスト
        regClass(WEB3SLAccountOpenApplyRequest.class);
        //SL口座開設申込レスポンス
        regClass(WEB3SLAccountOpenApplyResponse.class);

        //SL口座開設リクエスト
        regClass(WEB3SLAccountOpenRequest.class);
        //SL口座開設レスポンス
        regClass(WEB3SLAccountOpenResponse.class);

        //証券担保ローン返済申込完了リクエスト
        regClass(WEB3SLRepayApplyCompleteRequest.class);
        //証券担保ローン返済申込完了レスポンス
        regClass(WEB3SLRepayApplyCompleteResponse.class);

        //証券担保ローン返済申込確認リクエスト
        regClass(WEB3SLRepayApplyConfirmRequest.class);
        //証券担保ローン返済申込確認レスポンス
        regClass(WEB3SLRepayApplyConfirmResponse.class);

        //証券担保ローン返済申込入力リクエスト
        regClass(WEB3SLRepayApplyInputRequest.class);
        //証券担保ローン返済申込入力レスポンス
        regClass(WEB3SLRepayApplyInputResponse.class);

        //証券担保ローン返済取消共通リクエスト
        regClass(WEB3SLRepayCancelCommonRequest.class);
        //証券担保ローン返済取消共通レスポンス
        regClass(WEB3SLRepayCancelCommonResponse.class);

        //証券担保ローン返済取消完了リクエスト
        regClass(WEB3SLRepayCancelCompleteRequest.class);
        //証券担保ローン返済取消完了レスポンス
        regClass(WEB3SLRepayCancelCompleteResponse.class);

        //証券担保ローン返済取消確認リクエスト
        regClass(WEB3SLRepayCancelConfirmRequest.class);
        //証券担保ローン返済取消確認レスポンス
        regClass(WEB3SLRepayCancelConfirmResponse.class);

        //証券担保ローン返済一覧リクエスト
        regClass(WEB3SLRepayCancelListRequest.class);
        //証券担保ローン返済一覧レスポンス
        regClass(WEB3SLRepayCancelListResponse.class);

        //証券担保ローン返済申込共通リクエスト
        regClass(WEB3SLRepayApplyCommonRequest.class);
        //証券担保ローン返済申込共通レスポンス
        regClass(WEB3SLRepayApplyCommonResponse.class);
        
        //担保銘柄登録取消確認リクエスト
        regClass(WEB3AdminSLProductCancelConfirmRequest.class);
        //担保銘柄登録取消確認レスポンス
        regClass(WEB3AdminSLProductCancelConfirmResponse.class);

        //担保銘柄登録取消完了リクエスト
        regClass(WEB3AdminSLProductCancelCompleteRequest.class);
        //担保銘柄登録取消完了レスポンス
        regClass(WEB3AdminSLProductCancelCompleteResponse.class);

        //担保銘柄登録入力リクエスト
        regClass(WEB3AdminSLProductRegistInputRequest.class);
        //担保銘柄登録入力レスポンス
        regClass(WEB3AdminSLProductRegistInputResponse.class);
        //担保銘柄登録確認リクエスト
        regClass(WEB3AdminSLProductRegistConfirmRequest.class);
        //担保銘柄登録確認レスポンス
        regClass(WEB3AdminSLProductRegistConfirmResponse.class);
        //担保銘柄登録完了リクエスト
        regClass(WEB3AdminSLProductRegistCompleteRequest.class);
        //担保銘柄登録完了レスポンス
        regClass(WEB3AdminSLProductRegistCompleteResponse.class);
        
        //担保銘柄登録変更入力リクエスト
        regClass(WEB3AdminSLProductChangeInputRequest.class);
        //担保銘柄登録変更入力レスポンス
        regClass(WEB3AdminSLProductChangeInputResponse.class);
        //担保銘柄登録変更確認リクエスト
        regClass(WEB3AdminSLProductChangeConfirmRequest.class);
        //担保銘柄登録変更確認レスポンス
        regClass(WEB3AdminSLProductChangeConfirmResponse.class);
        //担保銘柄登録変更完了リクエスト
        regClass(WEB3AdminSLProductChangeCompleteRequest.class);
        //担保銘柄登録変更完了レスポンス
        regClass(WEB3AdminSLProductChangeCompleteResponse.class);
        
        //担保銘柄登録一覧リクエスト
        regClass(WEB3AdminSLProductRegiListRequest.class);
        //担保銘柄登録一覧レスポンス
        regClass(WEB3AdminSLProductRegiListResponse.class);

        //FXから振替完了リクエスト（SOAP接続）
        regClass(WEB3FXTransFromFXCompleteSoapRequest.class);
        //FXから振替完了レスポンス（SOAP接続）
        regClass(WEB3FXTransFromFXCompleteSoapResponse.class);

        //FX口座開設完了リクエスト（SOAP接続）
        regClass(WEB3FXAccOpenCompleteSoapRequest.class);
        //FX口座開設完了レスポンス（SOAP接続）
        regClass(WEB3FXAccOpenCompleteSoapResponse.class);
    }

    /**
     * Handler の登録処理
     * @@throws Exception
     */
    private static void plugHandlers() throws Exception
    {
        //Handler の登録処理 ----------------------

        //管理者FX口座開設管理ハンドラ
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AdminFXAccOpenApplyDownloadRequest.class,
            WEB3AdminFXAccOpenManagementHandler.class,
        "getDownloadFile");

        //管理者FX口座開設アップロードハンドラ
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AdminFXAccOpenUploadInputRequest.class,
            WEB3AdminFXAccOpenUploadHandler.class,
        "getUploadScreen");

        //管理者FX口座開設アップロードハンドラ
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AdminFXAccOpenUploadConfirmRequest.class,
            WEB3AdminFXAccOpenUploadHandler.class,
        "validateUploadFile");

        //管理者FX口座開設アップロードハンドラ
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AdminFXAccOpenUploadCompleteRequest.class,
            WEB3AdminFXAccOpenUploadHandler.class,
        "submitUpload");

        //管理者FX口座開設アップロードハンドラ
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AdminFXAccOpenUploadCancelRequest.class,
            WEB3AdminFXAccOpenUploadHandler.class,
        "undoUpload");

        //FX振替注文アップロードハンドラ
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AdminFXTransferUploadInputRequest.class,
            WEB3AdminFXTransferOrderUploadHandler.class,
            "getUploadScreen");

        //FX振替注文アップロードハンドラ
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AdminFXTransferUploadConfirmRequest.class,
            WEB3AdminFXTransferOrderUploadHandler.class,
            "validateUploadFile");

        //FX振替注文アップロードハンドラ
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AdminFXTransferUploadCompleteRequest.class,
            WEB3AdminFXTransferOrderUploadHandler.class,
            "fxTransferOrderUpload");

        //FX振替注文アップロードハンドラ
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AdminFXTransferUploadCancelRequest.class,
            WEB3AdminFXTransferOrderUploadHandler.class,
            "undoUploadStop");

        // 証拠金から振替ハンドラ
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AccTransChangeFromIfoDepositConfirmRequest.class,
                WEB3AccTransChangeFromIfoDepositHandler.class,
            "handleConfirmOrder");
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AccTransChangeFromIfoDepositCompleteRequest.class,
                WEB3AccTransChangeFromIfoDepositHandler.class,
            "handleCompleteOrder");
        // 証拠金から振替入力ハンドラ
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AccTransChangeFromIfoDepositInputRequest.class,
                WEB3AccTransChangeFromIfoDepositInputHandler.class,
            "handleAccTransChangeFromIfoDepositInputRequest");
        // 振替請求受付ハンドラ
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AccTransChangeRequestAcceptRequest.class,
                WEB3AccTransChangeRequestAcceptHandler.class,
            "handleAccTransChangeRequestAcceptRequest");
        // 振替請求通知ハンドラ
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AccTransChangeRequestNotifyRequest.class,
                WEB3AccTransChangeRequestNotifyHandler.class,
            "handleAccTransChangeRequestNotifyRequest");
        // 証拠金への振替ハンドラ
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AccTransChangeToIfoDepositConfirmRequest.class,
                WEB3AccTransChangeToIfoDepositHandler.class,
            "confirmOrder");
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AccTransChangeToIfoDepositCompleteRequest.class,
                WEB3AccTransChangeToIfoDepositHandler.class,
            "completeOrder");
        // 証拠金への振替入力ハンドラ
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AccTransChangeToIfoDepositInputRequest.class,
                WEB3AccTransChangeToIfoDepositInputHandler.class,
            "handleAccTransChangeToIfoDepositInputRequest");
        // 出金連携ハンドラ
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AioOnPaymentCooperationRequest.class,
                WEB3AioOnPaymentCooperationHandler.class,
            "onPaymentCooperationRequest");

        // 入金連携ハンドラ
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AioCashinCooperationNotifyRequest.class,
            WEB3AioCashinCooperationServiceHandler.class,
            "cashinCooperationRequest");

        // 入金連絡確認入力ハンドラ
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AdminAioCashinConfirmInputRequest.class,
                WEB3AdminAioCashinConfirmInputHandler.class,
                "handleCashinConfirmInputRequest");
        //  入金連絡確認ハンドラ
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AdminAioCashinConfirmListRequest.class,
                WEB3AdminAioCashinNoticeConfirmHandler.class,
                "handleCashinConfirmListRequest");
        //  出金申込問合せハンドラ
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AdminAioCashoutInqListRequest.class,
                WEB3AdminAioCashoutInqHandler.class,
            "handlecashoutInqRequest");
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AdminAioCashoutInqConfirmRequest.class,
                WEB3AdminAioCashoutInqHandler.class,
            "confirmCashout");
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AdminAioCashoutInqCompleteRequest.class,
                WEB3AdminAioCashoutInqHandler.class,
            "completeCashout");
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AdminAioCashoutInqCancelConfirmRequest.class,
                WEB3AdminAioCashoutInqHandler.class,
            "confirmCancel");
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AdminAioCashoutInqCancelCompleteRequest.class,
                WEB3AdminAioCashoutInqHandler.class,
            "completeCancel");
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AdminAioCashoutInqDownloadRequest.class,
            WEB3AdminAioCashoutInqHandler.class,
        	"getDownloadFile");
        //  出金申込問合せ入力ハンドラ
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AdminAioCashoutInqInputRequest.class,
                WEB3AdminAioCashoutInqInputHandler.class,
            "hadleCashoutInqRequest");
        // 決済連携レポートハンドラ
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AdminAioSettleReportListRequest.class,
                WEB3AdminAioSettleReportHandler.class,
            "handleReportIndicationRequest");
        // 決済連携レポート入力ハンドラ
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AdminAioSettleReportInputRequest.class,
                WEB3AdminAioSettleReportInputHandler.class,
            "handleReportInputRequest");
        // その他件数照会ハンドラ
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AdminAioOtherCountReferenceInputRequest.class,
                WEB3AdminAioOtherCountReferenceHandler.class,
            "getInputScreen");
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AdminAioOtherCountReferenceRequest.class,
                WEB3AdminAioOtherCountReferenceHandler.class,
            "getCountReferenceScreen");
        // 入金受付ハンドラ
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AioCashinAcceptRequest.class,
                WEB3AioCashinAcceptHandler.class,
            "handleAioCashinAcceptRequest");
        //  オンライン入金入力ハンドラ
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AioCashinInputRequest.class,
                WEB3AioCashinInputHandler.class,
            "handleCashinInputRequest");
        // 入金連絡ハンドラ
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AioCashinNoticeConfirmRequest.class,
                WEB3AioCashinNoticeHandler.class,
            "handleConfirmNoticeRequest");
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AioCashinNoticeCompleteRequest.class,
                WEB3AioCashinNoticeHandler.class,
            "handleCompleteNoticeRequest");
        // 入金連絡入力ハンドラ
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AioCashinNoticeInputRequest.class,
                WEB3AioCashinNoticeInputHandler.class,
            "handleCashinNoticeInputRequest");
        // オンライン入金選択ハンドラ
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AioCashinSelectRequest.class,
                WEB3AioCashinSelectHandler.class,
            "handleCashinSelectRequest");
        // 注文要求中止ハンドラ
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AioCashinSettleCancelRequest.class,
                WEB3AioCashinSettleCancelHandler.class,
            "handleCashinSettleCancelRequest");
        // 決済完了ハンドラ
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AioCashinSettleCompleteRequest.class,
                WEB3AioCashinSettleCompleteHandler.class,
            "handleCashinSettleCompleteRequest");
        // 決済エラーハンドラ
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AioCashinSettleErrorRequest.class,
                WEB3AioCashinSettleErrorHandler.class,
            "handleCashinSettleErrorRequest");
        //  決済依頼ハンドラ
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AioCashinConfirmRequest.class,
                WEB3AioCashinSettlementHandler.class,
            "handleConfirmSettlement");
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AioCashinSettlementRequest.class,
                WEB3AioCashinSettlementHandler.class,
            "handleStartSettlement");
        // 出金受付ハンドラ
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AioCashoutAcceptRequest.class,
                WEB3AioCashoutAcceptHandler.class,
            "handleCashoutAcceptRequest");
        // 出金取消ハンドラ
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AioCashoutCancelConfirmRequest.class,
                WEB3AioCashoutCancelHandler.class,
            "handleConfirmCancelRequest");
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AioCashoutCancelCompleteRequest.class,
                WEB3AioCashoutCancelHandler.class,
            "handleCompleteCancelRequest");
        // 出金取消一覧ハンドラ
       regHandler(
               WEB3AioAppPlugin.class,
               WEB3AioCashoutCancelListRequest.class,
               WEB3AioCashoutCancelListHandler.class,
            "handleAioCashoutCancelListRequest");
        // 出金申込ハンドラ
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AioCashoutConfirmRequest.class,
                WEB3AioCashoutHandler.class,
            "handleConfirmOrderRequest");
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AioCashoutCompleteRequest.class,
                WEB3AioCashoutHandler.class,
            "handleCompleteOrderRequest");
        // 出金申込入力ハンドラ
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AioCashoutInputRequest.class,
                WEB3AioCashoutInputHandler.class,
            "handleCashoutInputHandler");
        // 出金余力チェックハンドラ
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AioCashoutTradingPowerCheckRequest.class,
                WEB3AioCashoutTradingPowerHandler.class,
            "handleCashoutRemainingPowerCheckRequest");
        // 入出金一覧ハンドラ
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AioCashTransferListRequest.class,
                WEB3AioCashTransferListHandler.class,
            "handleCashTransferListRequest");
        // SONAR入出金ハンドラ
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AioSonarCashTransRequest.class,
                WEB3AioSonarCashTransHandler.class,
            "handleAioCashTransRequest");

        //融資額返済ハンドラ
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AioFinanceAmountRepayRequest.class,
                WEB3AioFinanceAmountRepayHandler.class,
            "financeAmountRepayRequest");

        // 証券振替ハンドラ
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AioSecurityTransferConfirmRequest.class,
            WEB3AioSecurityTransferHandler.class,
            "handleSecurityTransferConfirmRequest");
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AioSecurityTransferCompleteRequest.class,
                WEB3AioSecurityTransferHandler.class,
                "handleSecurityTransferCompleteRequest");

        //証券振替強制ハンドラ
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AioSecurityTransferForceRequest.class,
                WEB3AioSecurityTransferForceHandler.class,
                "handleSecurityTransferForceRequest");

        // 証券振替通知ハンドラ
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AioSecurityTransferNotifyRequest.class,
                WEB3AioSecurityTransferNotifyHandler.class,
                "handleSecurityTransferNotifyRequest");

        // 証券振替入力ハンドラ
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AioSecurityTransferListRequest.class,
            WEB3AioSecurityTransferInputHandler.class,
            "handleSecurityTransferListRequest");
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AioSecurityTransferInputRequest.class,
            WEB3AioSecurityTransferInputHandler.class,
            "handleSecurityTransferInputRequest");

        //特定口座振替強制ハンドラ
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AioSpsecTransferForceRequest.class,
            WEB3AioSpsecTransferForceHandler.class,
            "handleSpsecTransferForceRequest");

        // SONAR入出金（外貨）ハンドラ
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AioSonarCashTransForeignRequest.class,
            WEB3AioSonarCashTransForeignHandler.class,
            "sonarCashTransForeignRequest");

        //外貨入出金受付ハンドラ
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AioForeignCashTransAcceptRequest.class,
            WEB3AioForeignCashTransAcceptHandler.class,
            "foreignCashTransAcceptRequest");

        // 債券出金連携ハンドラ
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AioBondOnPaymentCooperationRequest.class,
            WEB3AioBondOnPaymentCooperationHandler.class,
            "bondOnPaymentCooperationRequest");
        // ---為替保証金サービス---------

        // FX口座開設確認リクエスト
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3FXAccOpenConfirmRequest.class,
            WEB3FXAccOpenHandler.class,
            "accountOpenConfirm");

        regHandler(
            WEB3AioAppPlugin.class,
            WEB3FXAccOpenCompleteSoapRequest.class,
            WEB3FXAccOpenHandler.class,
            "accountOpenCompleteSoap");
        // FX口座開設完了リクエスト
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3FXAccOpenCompleteRequest.class,
                WEB3FXAccOpenHandler.class,
            "accountOpenComplete");
        // FX口座開設入力リクエスト
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3FXAccOpenInputRequest.class,
                WEB3FXAccOpenInputHandler.class,
            "displayInputScreen");
        // FX口座開設依頼リクエスト
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3FXAccOpenAskingRequest.class,
                WEB3FXAccOpenHandler.class,
            "accountOpenAsking");
        // FX口座開設取引同意リクエスト
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3FXAccOpenTradeAgreementRequest.class,
                WEB3FXAccOpenInputHandler.class,
            "displayTradeAgreementScreen");
        // FXから振替確認リクエスト
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3FXTransFromFXConfirmRequest.class,
                WEB3FXTransFromFXHandler.class,
            "orderConfirm");
        //注文完了（SOAP接続）
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3FXTransFromFXCompleteSoapRequest.class,
            WEB3FXTransFromFXHandler.class,
            "orderCompleteSoap");
        // FXから振替完了リクエスト
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3FXTransFromFXCompleteRequest.class,
                WEB3FXTransFromFXHandler.class,
            "orderComplete");
        // FXから振替入力リクエスト
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3FXTransFromFXInputRequest.class,
                WEB3FXTransFromFXInputHandler.class,
            "displayInputScreen");
        // シングルサインオンリクエスト
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3FXSingleSignOnRequest.class,
                WEB3FXSingleSignOnHandler.class,
            "displayExterFxSystem");
        // FX取引同意リクエスト
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3FXTradeAgreementRequest.class,
                WEB3FXSingleSignOnHandler.class,
            "displayTradeAgreementScreen");
        // FXから振替依頼リクエスト
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3FXTransFromFXAskingRequest.class,
                WEB3FXTransFromFXHandler.class,
            "orderAsking");
        // FXへの振替確認リクエスト
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3FXTransToFXConfirmRequest.class,
                WEB3FXTransToFXHandler.class,
            "orderConfirm");
        // FXへの振替完了リクエスト
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3FXTransToFXCompleteRequest.class,
                WEB3FXTransToFXHandler.class,
            "orderComplete");
        // FXへの振替完了リクエスト（SOAP接続）
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3FXTransToFXCompleteSoapRequest.class,
                WEB3FXTransToFXHandler.class,
            "orderComplete");
        // FXへの振替入力リクエスト
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3FXTransToFXInputRequest.class,
                WEB3FXTransToFXInputHandler.class,
            "displayInputScreen");
        // FXへの振替依頼リクエスト
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3FXTransToFXAskingRequest.class,
                WEB3FXTransToFXHandler.class,
            "orderAsking");
        // 管理者・FX振替一覧リクエスト
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AdminFXTransferListRequest.class,
                WEB3AdminFXTransferManagementHandler.class,
            "listScreenDisplay");
        // 管理者・FX振替一覧条件入力リクエスト
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AdminFXTransferListConditionRequest.class,
                WEB3AdminFXTransferManagementHandler.class,
            "condInputScreenDisplay");
        // 管理者・FX振替取消確認リクエスト
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AdminFXTransferCancelConfirmRequest.class,
                WEB3AdminFXTransferManagementHandler.class,
            "cancelConfirm");
        // 管理者・FX振替取消完了リクエスト
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AdminFXTransferCancelCompleteRequest.class,
                WEB3AdminFXTransferManagementHandler.class,
            "cancelComplete");
        // 管理者・FX口座開設ステータス更新確認リクエスト
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AdminFXAccOpenStatusUpdConfirmRequest.class,
                WEB3AdminFXAccOpenManagementHandler.class,
            "confirmStatusUpd");
        // 管理者・FX口座開設ステータス更新完了リクエスト
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AdminFXAccOpenStatusUpdCompleteRequest.class,
                WEB3AdminFXAccOpenManagementHandler.class,
            "completeStatusUpd");
        // 管理者・FX口座開設ステータス更新入力リクエスト
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AdminFXAccOpenStatusUpdInputRequest.class,
                WEB3AdminFXAccOpenManagementHandler.class,
            "getStatusUpdInputScreen");
        // 管理者・FX口座開設申込一覧リクエスト
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AdminFXAccOpenApplyListRequest.class,
                WEB3AdminFXAccOpenManagementHandler.class,
            "getListScreen");
        // 管理者・FX口座開設申込一覧条件入力リクエスト
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AdminFXAccOpenApplyListConditionRequest.class,
                WEB3AdminFXAccOpenManagementHandler.class,
            "getCondInputScreen");
        // 管理者・FX口座情報変更確認リクエスト
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AdminFXAccInfoChangeConfirmRequest.class,
                WEB3AdminFXAccManagementHandler.class,
            "confirmChanger");
        // 管理者・FX口座情報変更完了リクエスト
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AdminFXAccInfoChangeCompleteRequest.class,
                WEB3AdminFXAccManagementHandler.class,
            "completeChange");
        // 管理者・FX口座情報変更入力リクエスト
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AdminFXAccInfoChangeInputRequest.class,
                WEB3AdminFXAccManagementHandler.class,
            "getUpdInputScreen");
        // 管理者・FX口座情報検索リクエスト
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AdminFXAccInfoSearchRequest.class,
                WEB3AdminFXAccManagementHandler.class,
            "getQueryResult");
        // 管理者・FX口座情報検索条件入力リクエスト
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AdminFXAccInfoSearchConditionRequest.class,
            WEB3AdminFXAccManagementHandler.class,
            "getCondInputScreen");

        //入出庫履歴
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AioInputOutputHistoryListRequest.class,
            WEB3AioInputOutputHistoryHandler.class,
            "handleHistoryRequest");

        // 外株口座への振替ハンドラ
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3FEqConTransferInputRequest.class,
            WEB3FEqConTransferHandler.class,
            "displayInputScreen");
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3FEqConTransferConfirmRequest.class,
            WEB3FEqConTransferHandler.class,
            "transferConfirm");
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3FEqConTransferCompleteRequest.class,
            WEB3FEqConTransferHandler.class,
            "transferComplete");
        // 外株口座への振替取消ハンドラ
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3FEqConTransferCancelSelectRequest.class,
            WEB3FEqConTransferCancelHandler.class,
            "displaySelectScreen");
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3FEqConTransferCancelConfirmRequest.class,
            WEB3FEqConTransferCancelHandler.class,
            "cancelConfirm");
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3FEqConTransferCancelCompleteRequest.class,
            WEB3FEqConTransferCancelHandler.class,
            "cancelCompelte");
        // 外株口座開設ハンドラ
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3FEqConAccountOpenAgreementRequest.class,
            WEB3FEqConAccountOpenHandler.class,
            "displayAgreementScreen");
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3FEqConAccountOpenInputRequest.class,
            WEB3FEqConAccountOpenHandler.class,
            "displayInputScreen");
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3FEqConAccountOpenConfirmRequest.class,
            WEB3FEqConAccountOpenHandler.class,
            "applyConfirm");
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3FEqConAccountOpenCompleteRequest.class,
            WEB3FEqConAccountOpenHandler.class,
            "applyComplete");
        // 外株口座開設管理ハンドラ
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AdminFEqConAccountOpenMngSrcInputRequest.class,
            WEB3AdminFEqConAccountOpenMngHandler.class,
            "displayInputScreen");
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AdminFEqConAccountOpenMngListRequest.class,
            WEB3AdminFEqConAccountOpenMngHandler.class,
            "displayListScreen");
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AdminFEqConAccountOpenMngStatusUpdInputRequest.class,
            WEB3AdminFEqConAccountOpenMngHandler.class,
            "displayStatusUpdScreen");
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AdminFEqConAccountOpenMngStatusUpdConfirmRequest.class,
            WEB3AdminFEqConAccountOpenMngHandler.class,
            "statusUpdConfirm");
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AdminFEqConAccountOpenMngStatusUpdCompleteRequest.class,
            WEB3AdminFEqConAccountOpenMngHandler.class,
            "statusUpdComplete");
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AdminFEqConAccountOpenMngListDownloadRequest.class,
            WEB3AdminFEqConAccountOpenMngHandler.class,
            "getDownloadFile");

        // 外株口座管理ハンドラ
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AdminFEqConAccountInfoSearchInputRequest.class,
            WEB3AdminFEqConAccountMngHandler.class,
            "displayInputScreen");
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AdminFEqConAccountInfoSearchRequest.class,
            WEB3AdminFEqConAccountMngHandler.class,
            "displaySearchScreen");
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AdminFEqConAccountStateChangeConfirmRequest.class,
            WEB3AdminFEqConAccountMngHandler.class,
            "accountStateChangeConfirm");
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AdminFEqConAccountStateChangeCompleteRequest.class,
            WEB3AdminFEqConAccountMngHandler.class,
            "accountStateChangeComplete");

        // 外株振替管理ハンドラ
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AdminFEqConTransferListInputRequest.class,
            WEB3AdminFEqConTransferMngHandler.class,
            "displayInputScreen");
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AdminFEqConTransferListRequest.class,
            WEB3AdminFEqConTransferMngHandler.class,
            "displayListScreen");

        // 出庫通知ハンドラ
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AioOutputNotifyRequest.class,
            WEB3AioOutputNotifyHandler.class,
            "outputNotifyRequest");

        // 入金通知確認再処理ハンドラ
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AdminAioCashinNoticeSearchRequest.class,
            WEB3AdminAioCashinNoticeConfirmReTreatmentHandler.class,
            "cashinNoticeSearchRequest");
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AdminAioCashinNoticeChangeInputRequest.class,
            WEB3AdminAioCashinNoticeConfirmReTreatmentHandler.class,
            "cashinNoticeChangeInputScreenRequest");
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AdminAioCashinNoticeChangeConfirmRequest.class,
            WEB3AdminAioCashinNoticeConfirmReTreatmentHandler.class,
            "cashinNoticeChangeConfirmRequest");
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AdminAioCashinNoticeChangeCompleteRequest.class,
            WEB3AdminAioCashinNoticeConfirmReTreatmentHandler.class,
            "cashinNoticeChangeCompleteRequest");

        //バーチャル口座入金ULハンドラ
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AdminAioVirtualAccCashinULInputRequest.class,
            WEB3AdminAioVirtualAccCashinULHandler.class,
            "uploadScreenDisplay");
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AdminAioVirtualAccCashinULConfirmRequest.class,
            WEB3AdminAioVirtualAccCashinULHandler.class,
            "uploadFileConfirm");
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AdminAioVirtualAccCashinULCompleteRequest.class,
            WEB3AdminAioVirtualAccCashinULHandler.class,
            "virtualAccCashinUpload");
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AdminAioVirtualAccCashinULCancelRequest.class,
            WEB3AdminAioVirtualAccCashinULHandler.class,
            "uploadCancel");

        //管理者入出金一覧ハンドラ
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AdminAioCashTransferListInputRequest.class,
            WEB3AdminAioListHandler.class,
            "getInputScreen");

        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AdminAioCashTransferListRequest.class,
            WEB3AdminAioListHandler.class,
            "getListScreen");

        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AdminAioCashTransferListDownloadRequest.class,
            WEB3AdminAioListHandler.class,
            "getDownLoadFile");

        // 証券担保ローン出金停止解除ハンドラ
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AdminSLCashOutStopReleaseConfirmRequest.class,
            WEB3AdminAioSLCashOutStopReleaseHandler.class,
            "validateCashOutStopReleaseConfirmScreen");
        
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AdminSLCashOutStopReleaseCompleteRequest.class,
            WEB3AdminAioSLCashOutStopReleaseHandler.class,
            "submitCashOutStopReleaseCompleteScreen");
        
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AdminSLRestraintMoneyListRequest.class,
            WEB3AdminAioSLCashOutStopReleaseHandler.class,
            "getSLRestraintMoneyListScreen");

        //証券担保ローン対応
        //証券担保ローン返済申込ハンドラ
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3SLRepayApplyConfirmRequest.class,
            WEB3AioSLRepayApplyHandler.class,
            "confirmOrder");

        regHandler(
            WEB3AioAppPlugin.class,
            WEB3SLRepayApplyCompleteRequest.class,
            WEB3AioSLRepayApplyHandler.class,
            "completeOrder");

        //証券担保ローン返済申込入力ハンドラ
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3SLRepayApplyInputRequest.class,
            WEB3AioSLRepayApplyInputHandler.class,
            "slRepayInputRequest");

        //証券担保ローン返済取消ハンドラ
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3SLRepayCancelConfirmRequest.class,
            WEB3AioSLRepayCancelHandler.class,
            "confirmOrder");

        regHandler(
            WEB3AioAppPlugin.class,
            WEB3SLRepayCancelCompleteRequest.class,
            WEB3AioSLRepayCancelHandler.class,
            "completeOrder");

        //証券担保ローン返済一覧ハンドラ
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3SLRepayCancelListRequest.class,
            WEB3AioSLRepayListHandler.class,
            "repayCancelListRequest");

        //SL口座開設ハンドラ
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3SLAccountOpenApplyRequest.class,
            WEB3AioSLAccountOpenHandler.class,
            "slAccountOpenConfirm");

        regHandler(
            WEB3AioAppPlugin.class,
            WEB3SLAccountOpenRequest.class,
            WEB3AioSLAccountOpenHandler.class,
            "slAccountOpen");

        //担保ローン申込状況一覧ハンドラ
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AdminSLAccountOpenApplyListRequest.class,
            WEB3AioSecuredLoanOfferStateListHandler.class,
            "getListScreen");

        //担保銘柄取消ハンドラ
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AdminSLProductCancelConfirmRequest.class,
            WEB3AdminAioSLProductCancelHandler.class,
            "validateSLProductCancel");
        
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AdminSLProductCancelCompleteRequest.class,
            WEB3AdminAioSLProductCancelHandler.class,
            "submitSLProductCancel");
        
        //担保銘柄登録ハンドラ
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AdminSLProductRegistInputRequest.class,
            WEB3AdminAioSLProductRegistHandler.class,
            "getSLProductInput");
        
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AdminSLProductRegistConfirmRequest.class,
            WEB3AdminAioSLProductRegistHandler.class,
            "validateSLProductRegist");
        
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AdminSLProductRegistCompleteRequest.class,
            WEB3AdminAioSLProductRegistHandler.class,
            "submitSLProductRegist");

        //担保銘柄変更ハンドラ
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AdminSLProductChangeInputRequest.class,
            WEB3AdminAioSLProductChangeHandler.class,
            "getSLProductChangeInput");

        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AdminSLProductChangeConfirmRequest.class,
            WEB3AdminAioSLProductChangeHandler.class,
            "validateSLProductChange");

        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AdminSLProductChangeCompleteRequest.class,
            WEB3AdminAioSLProductChangeHandler.class,
            "submitSLProductChange");

        //担保登録銘柄照会ハンドラ
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AdminSLProductRegiListRequest.class,
            WEB3AdminAioSLRegistProductReferenceHandler.class,
            "getSLRegiProductList");
    }

    /**
     * RAC-CTXインタセプタを設定する
     * @@throws Exception
     */
    private static void plugRacCtxInterceptors() throws Exception
    {
        //------------------------------------
        // RAC-CTXインタセプタを設定する
        //------------------------------------

        // 証券振替通知一件
        Services.addInterceptor(
            WEB3AioSecurityTransferNotifyUnitService.class,
            new WEB3AioDescendRacCtxInterceptor());

        // 証券振替強制一件
        Services.addInterceptor(
            WEB3AioSecurityTransferForceUnitService.class,
            new WEB3AioDescendRacCtxInterceptor());

        // 特定口座振替強制一件
        Services.addInterceptor(
            WEB3AioSpsecTransferForceUnitService.class,
            new WEB3AioDescendRacCtxInterceptor());
        // 李志強 U01362の暫定対応 end

        // 李志強 U01504の暫定対応 start
        // 振替請求通知一件
        Services.addInterceptor(
            WEB3AccTransChangeRequestNotifyUnitService.class,
            new WEB3AioDescendRacCtxInterceptor());

        // 振替受付一件
        Services.addInterceptor(
            WEB3AccTransChangeAcceptUnitService.class,
            new WEB3AioDescendRacCtxInterceptor());

        // 振替完了一件
        Services.addInterceptor(
            WEB3AccTransChangeCompleteUnitService.class,
            new WEB3AioDescendRacCtxInterceptor());

        // 入出金受付一件
        Services.addInterceptor(
            WEB3AioCashTransferAcceptUnitService.class,
            new WEB3AioDescendRacCtxInterceptor());

        // 入出金完了一件
        Services.addInterceptor(
            WEB3AioCashTransferCompleteUnitService.class,
            new WEB3AioDescendRacCtxInterceptor());

        // SONAR入出金一件
        Services.addInterceptor(
            WEB3AioSonarCashTransUnitService.class,
            new WEB3AioDescendRacCtxInterceptor());

        // 出金余力チェック一件
        Services.addInterceptor(
            WEB3AioCashoutTradingPowerService.class,
            new WEB3AioDescendRacCtxInterceptor());

        // 出庫通知一件
        Services.addInterceptor(
            WEB3AioOutputNotifyUnitService.class,
            new WEB3AioDescendRacCtxInterceptor());

        // SONAR入出金（外貨）
        Services.addInterceptor(
            WEB3AioSonarCashTransForeignUnitService.class,
            new WEB3AioDescendRacCtxInterceptor());

        // 融資額返済一件
        Services.addInterceptor(
                WEB3AioFinanceAmountRepayUnitService.class,
            new WEB3AioDescendRacCtxInterceptor());

        // 外貨入出金受付
        Services.addInterceptor(
            WEB3AioForeignCashTransAcceptService.class,
            new WEB3AioDescendRacCtxInterceptor());

        // SONAR入出金（外貨）
        Services.addInterceptor(
            WEB3AioBondOnPaymentCooperationUnitService.class,
            new WEB3AioDescendRacCtxInterceptor());

    }
}
@
