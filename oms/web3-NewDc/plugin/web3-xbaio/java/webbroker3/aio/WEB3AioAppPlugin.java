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
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : Webbroker3-xbaio �v���O�C���N���X(WEB3AioAppPlugin.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/20 �����(���u)�V�K�쐬
Revesion History : 2004/12/15 �����(���u)�c�Ή�
Revesion History : 2005/01/27 �����(���u)�ב֕ۏ؋��Ή�
Revesion History : 2005/02/22 �����(���u)��������U�֋����ǉ��Ή�
Revesion History : 2005/04/01 �����(���u)�O�������U�֘A�g�ǉ��Ή�
Revesion History : 2005/07/12 ��O��(���u)���̑������Ɖ�ǉ��Ή�
Revesion History : 2006/01/12 ����(���u)�����A�g�ǉ��Ή�
Revesion History : 2006/01/22 ����(���u)�����A�g�m�F�ď����ǉ��Ή�
Revesion History : 2006/02/10 杊��](���u)�ב֕ۏ؋��ǉ��Ή�
Revesion History : 2006/02/10 ���_�O(���u)�Ǘ��ҁEFX�����J�݂t�k�ǉ��Ή�
Revesion History : 2006/03/02 �A����(���u)�Ǘ��ҁEFX�U�֒����t�k�ǉ��Ή�
Revesion History : 2006/04/19 ���n�@@�a��(SCS) ��Q�Ǘ� U02823�Ή�
Revesion History : 2006/05/11 ����(���u) �o�[�`������������UL�Ή�
Revesion History : 2006/07/31 ����(���u) �o�������f�[�^�o�͑Ή�
Revesion History : 2006/09/01 ���G��(���u) SONAR���o���i�O�݁j�Ή�
Revesion History : 2006/09/05 ���G��(���u) �O�ݓ��o����t
Revesion History : 2006/09/25 ������(���u) �،��S�ۃ��[���Ή�
Revesion History : 2006/10/19 ���G��(���u) ���o���A�g
Revesion History : 2006/11/06 �����q(���u) ���f��No.677
Revesion History : 2007/02/09 ���G��(���u) �Ǘ��ғ��o���ꗗ�T�[�r�X
Revesion History : 2007/04/10 ���G��(���u) �o�������g���K�[���s
Revesion History : 2007/07/13 ����(���u) �ۏ؋��ւ̐U�փT�[�r�X�ǉ��Ή�
Revesion History : 2007/09/19 ����(���u) �S�ۃ��[���o���S���������T�[�r�ǉ��Ή�
Revesion History : 2007/09/23 �đo�g(���u) �،��S�ۃ��[���Ή�
Revesion History : 2007/09/26 ��іQ(���u) �S�ۖ����o�^�Ή�
Revesion History : 2008/04/08 �g�C�� (���u) �d�l�ύX�E���f��832�@@���f��833�@@���f��840
                 : 2009/02/13 �O�� (SCS)�d�l�ύX�E���f��1097
Revesion History : 2009/07/01 �����F(���u) �y�ב֕ۏ؋��z���U�։\�z�\���Ή�
Revesion History : 2009/09/21 �����F(���u) �y�ב֕ۏ؋��z����؂e�w�Ή�
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
 * Webbroker3-Aio �v���O�C���N���X�B
 *
 * @@author ����� (���u)
 * @@version 1.0
 */
public final class WEB3AioAppPlugin extends Plugin
{
    /**
     * ���O���[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioAppPlugin.class);

    /**
     * �R���X�g���N�^�B
     */
    public WEB3AioAppPlugin()
    {
    }

    /**
     * �v���O�C���G���g���[�|�C���g�B
     */
    public static void plug() throws Exception
    {
        String METHOD_NAME = "plug()";
        log.entering(METHOD_NAME);

        plug(WEB3AioAppPlugin.class);

        log.exiting(METHOD_NAME);
    }

    /**
     * �v���O�C�������B
     */
    public static void onPlug() throws Exception
    {
        String METHOD_NAME = "onPlug()";
        log.entering(METHOD_NAME);

        // ���̃v���O�C������ɓǂݍ��ޕK�v�̂���v���O�C���̎w��B
        // install the system plugins that we need
        KernelPlugin.plug();

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        // �g���g�����U�N�V�����E�}�l�[�W���[��
        // �I�[�o�[���C�h���\�b�h���������ߊg��������W���[���N���X���쐬��
        // �g��������W���[���N���X���Őݒ�

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

        // �v�Z�T�[�r�X�N���X
        l_tradingModule.overrideBizLogicProvider(new WEB3AioBizLogicProvider());

        // �g�������}�l�[�W��
        l_tradingModule.overrideOrderManager(new WEB3AioOrderManager());

        // AIO�v���_�N�g�}�l�[�W��
        l_tradingModule.overrideProductManager(new WEB3AioProductManager());

        // �|�W�V�����}�l�[�W��
        l_tradingModule.overridePositionManager(new WEB3AioPositionManager());

        // ���o�������R���ʃ`�F�b�N
        WEB3AioProductTypeOrderManagerReusableValidations.register();

        // Webbroker3-Aio-MarketAdaptor �v���O�C��
        WEB3AioMarketAdaptorAppPlugin.plug();


        // DatabaseExtensions �̃v���O�C������ ----------------------
        WEB3AioMasterDatabaseExtensions.plug();
        WEB3AioSessionDatabaseExtensions.plug();
        WEB3AioAccountDatabaseExtensions.plug();

        // Service �̓o�^���� ----------------------
        plugServices();

        // Service.execute()�Ăяo���O���  ----------------------
        // �����J�n�����Ə����I�����������O�o�͂���悤�ɐݒ肷��
        plugLogSysTimeInterceptors();

        //Service �� ServiceInterceptor ��ݒ肷�� ----------------------
        plugServiceInterceptors();

        // Service �� Interceptor �ݒ菈�� ----------------------
        // �����g�����U�N�V�����ݒ�
        plugTransactionalInterceptors();

        //MQ-Gateway �� Interceptor �ݒ菈�� ----------------------
        plugMQGatewayInterceptors();

        // Message �̓o�^���� ----------------------
        plugMessages();

        // Handler �̓o�^����
        plugHandlers();

        //RAC-CTX�C���^�Z�v�^��ݒ肷��
        plugRacCtxInterceptors();

        log.exiting(METHOD_NAME);
    }
    /**
     * Service �̓o�^����
     * @@throws Exception
     */
    private static void plugServices() throws Exception
    {
        // ------�i�ב֕ۏ؋��j�ב֕ۏ؋����ʃT�[�r�X------

        // ------FX�d�������T�[�r�X-----------
        Services.registerService(
            WEB3FXTelegramProcessService.class,
            new WEB3FXTelegramProcessServiceImpl());

        // ------FX�f�[�^����T�[�r�X-----------
        Services.registerService(
            WEB3FXDataControlService.class,
            new WEB3FXDataControlServiceImpl());

        //�ڑ����ʃC���^�t�F�[�X
        Services.registerService(
            WEB3FXConnCommonService.class,
            new WEB3FXConnCommonServiceImpl());

        //FX�U�։\�z�\���T�[�r�X�C���^�[�t�F�C�X
        Services.registerService(
            WEB3FXTransferAbleAmtDisplayService.class,
            new WEB3FXTransferAbleAmtDisplayServiceImpl());

        //�����J�ݐڑ��C���^�t�F�[�X
        Services.registerService(
            WEB3FXAccOpenConnection.class,
            new WEB3FXAccOpenConnectionImpl());
        
        //�U�֐ڑ��C���^�t�F�[�X
        Services.registerService(
            WEB3FXTransConnection.class,
            new WEB3FXTransConnectionImpl());

        // ------�i���o���j�}���`�o���N���σG���e�B�e�B�D�}���`�o���N���ϐ���T�[�r�X------
        Services.registerService(
                WEB3AioMultiBankSettleControlService.class,
                new WEB3AioMultiBankSettleControlServiceImpl());

        //-------------�S�ۖ����o�^����T�[�r�X ---------------
        Services.registerService(
            WEB3AdminAioSLProductRegistControlService.class,
            new WEB3AdminAioSLProductRegistControlServiceImpl());

        // ���o���T�[�r�X--------------------------Begin------
        // �}���`�o���N�d�������T�[�r�X�C���^�t�F�[�X
        WEB3HttpServiceMappings l_mapping =
            (WEB3HttpServiceMappings)Services.getService(WEB3HttpServiceMappings.class);

        Services.registerService(
            WEB3AioMultiBankTelegramProcessService.class,
            new WEB3AioMultiBankTelegramProcessServiceImpl());
        l_mapping.addService("web3-aio", WEB3AioMultiBankTelegramProcessService.class);

        // ���σG���[�T�[�r�X�C���^�[�t�F�C�X
        Services.registerService(
            WEB3AioCashinSettleErrorService.class,
            new WEB3AioCashinSettleErrorServiceImpl());

        // ���ψ˗��T�[�r�X�C���^�[�t�F�C�X
        Services.registerService(
                WEB3AioCashinSettlementService.class,
            new WEB3AioCashinSettlementServiceImpl());

        // ���ϊ����T�[�r�X�C���^�t�F�[�X
        Services.registerService(
                WEB3AioCashinSettleCompleteService.class,
            new WEB3AioCashinSettleCompleteServiceImpl());

        // �����v�����~�T�[�r�X�C���^�[�t�F�C�X
        Services.registerService(
                WEB3AioCashinSettleCancelService.class,
            new WEB3AioCashinSettleCancelServiceImpl());

        // SONAR���o���T�[�r�X�C���^�[�t�F�C�X
        Services.registerService(
                WEB3AioSonarCashTransService.class,
            new WEB3AioSonarCashTransServiceImpl());

        // SONAR���o��UnitService�C���^�t�F�[�X
        Services.registerService(
                WEB3AioSonarCashTransUnitService.class,
            new WEB3AioSonarCashTransUnitServiceImpl());

        // �I�����C�������I���T�[�r�X�C���^�[�t�F�C�X
        Services.registerService(
                WEB3AioCashinSelectService.class,
            new WEB3AioCashinSelectServiceImpl());

        // �I�����C���������̓T�[�r�X�C���^�t�F�[�X
        Services.registerService(
                WEB3AioCashinInputService.class,
            new WEB3AioCashinInputServiceImpl());

        // �o������T�[�r�X�C���^�[�t�F�C�X
        Services.registerService(
                WEB3AioCashoutCancelService.class,
            new WEB3AioCashoutCancelServiceImpl());

        // �o�����UnitService
        Services.registerService(
            WEB3AioCashoutCancelUnitService.class,
            new WEB3AioCashoutCancelUnitServiceImpl());

        // �o������ꗗ�T�[�r�X�C���^�t�F�[�X
        Services.registerService(
                WEB3AioCashoutCancelListService.class,
            new WEB3AioCashoutCancelListServiceImpl());

        // �o���\���T�[�r�X�C���^�[�t�F�C�X
        Services.registerService(
                WEB3AioCashoutService.class,
            new WEB3AioCashoutServiceImpl());

        // �o���\�����̓T�[�r�X�C���^�t�F�[�X
        Services.registerService(
                WEB3AioCashoutInputService.class,
            new WEB3AioCashoutInputServiceImpl());

        // �o���]�̓`�F�b�N�T�[�r�X�C���^�[�t�F�C�X�N���X
        Services.registerService(
                WEB3AioCashoutTradingPowerService.class,
            new WEB3AioCashoutTradingPowerServiceImpl());

        // �o���]�̓`�F�b�NUnitService�C���^�[�t�F�C�X
        Services.registerService(
                WEB3AioCashoutTradingPowerUnitService.class,
            new WEB3AioCashoutTradingPowerUnitServiceImpl());

        // �o���A�g�T�[�r�X�C���^�[�t�F�C�X
        Services.registerService(
            WEB3AioOnPaymentCooperationService.class,
            new WEB3AioOnPaymentCooperationServiceImpl());

        // �o���A�gUnitService�C���^�[�t�F�C�X
        Services.registerService(
            WEB3AioOnPaymentCooperationUnitService.class,
            new WEB3AioOnPaymentCooperationUnitServiceImpl());

        // �����A�g�T�[�r�X�C���^�[�t�F�C�X
        Services.registerService(
            WEB3AioCashinCooperationService.class,
            new WEB3AioCashinCooperationServiceImpl());

        // �����A�g�ʒm�ꌏ�T�[�r�X�C���^�[�t�F�C�X
        Services.registerService(
            WEB3AioCashinCooperationNotifyUnitService.class,
            new WEB3AioCashinCooperationNotifyUnitServiceImpl());

        // �����A���T�[�r�X�C���^�[�t�F�C�X
        Services.registerService(
                WEB3AioCashinNoticeService.class,
            new WEB3AioCashinNoticeServiceImpl());

        // �����A�����[�����M�T�[�r�X�C���^�t�F�[�X
        Services.registerService(
                WEB3AioCashinNoticeMailSendService.class,
            new WEB3AioCashinNoticeMailSendServiceImpl());


        // �����A�����̓T�[�r�X�C���^�[�t�F�C�X
        Services.registerService(
                WEB3AioCashinNoticeInputService.class,
            new WEB3AioCashinNoticeInputServiceImpl());

        // ���o���ꗗ�T�[�r�X�C���^�t�F�[�X
        Services.registerService(
                WEB3AioCashTransferListService.class,
            new WEB3AioCashTransferListServiceImpl());

        // �o����t�T�[�r�X�C���^�t�F�[�X
        Services.registerService(
                WEB3AioCashoutAcceptService.class,
            new WEB3AioCashoutAcceptServiceImpl());

        // ������t�T�[�r�X�C���^�t�F�[�X
        Services.registerService(
                WEB3AioCashinAcceptService.class,
            new WEB3AioCashinAcceptServiceImpl());

        // ���o������UnitService�C���^�t�F�[�X
        Services.registerService(
                WEB3AioCashTransferCompleteUnitService.class,
            new WEB3AioCashTransferCompleteUnitServiceImpl());

        // ���o����tUnitService�C���^�t�F�[�X
        Services.registerService(
                WEB3AioCashTransferAcceptUnitService.class,
            new WEB3AioCashTransferAcceptUnitServiceImpl());

        // SONAR���o���i�O�݁j�T�[�r�X�C���^�t�F�[�X
        Services.registerService(
                WEB3AioSonarCashTransForeignService.class,
            new WEB3AioSonarCashTransForeignServiceImpl());

        // SONAR���o���i�O�݁jUnitService�C���^�t�F�[�X
        Services.registerService(
                WEB3AioSonarCashTransForeignUnitService.class,
            new WEB3AioSonarCashTransForeignUnitServiceImpl());

        //�O�ݓ��o����t�C���^�t�F�[�X
        Services.registerService(
                WEB3AioForeignCashTransAcceptService.class,
            new WEB3AioForeignCashTransAcceptServiceImpl());

        //�Z���z�ԍ�UnitService�C���^�t�F�[�X
        Services.registerService(
                WEB3AioFinanceAmountRepayUnitService.class,
            new WEB3AioFinanceAmountRepayUnitServiceImpl());

        //�Z���z�ԍσT�[�r�X �C���^�t�F�[�X
        Services.registerService(
                WEB3AioFinanceAmountRepayService.class,
            new WEB3AioFinanceAmountRepayServiceImpl());

        // ���o���A�g�T�[�r�X�C���^�t�F�[�X
        Services.registerService(
                WEB3AioBondOnPaymentCooperationService.class,
            new WEB3AioBondOnPaymentCooperationServiceImpl());

        // ���o���A�gUnitService�C���^�t�F�[�X
        Services.registerService(
                WEB3AioBondOnPaymentCooperationUnitService.class,
            new WEB3AioBondOnPaymentCooperationUnitServiceImpl());

        //�o�������g���K�[���sUnitService�C���^�t�F�[�X
        Services.registerService(
                WEB3AioCashOutOrderTriggerIssueUnitService.class,
            new WEB3AioCashOutOrderTriggerIssueUnitServiceImpl());

        // ���o���T�[�r�X--------------------------End------

        //���o�ɃT�[�r�X���f�� --------------------------Begin------

        // �o�ɒʒm�T�[�r�X
        Services.registerService(
            WEB3AioOutputNotifyService.class,
            new WEB3AioOutputNotifyServiceImpl());

        // �o�ɒʒmUnitService
        Services.registerService(
            WEB3AioOutputNotifyUnitService.class,
            new WEB3AioOutputNotifyUnitServiceImpl());
        
        // �ۏ؋��ւ̐U�փT�[�r�X
        Services.registerService(
            WEB3MarginTransferService.class,
            new WEB3MarginTransferServiceImpl());

        //���o�ɃT�[�r�X���f�� --------------------------End--------

        // ���o���T�[�r�X�i�Ǘ��ҁj--------------------------Begin------

        // ���̑������Ɖ�T�[�r�X�C���^�t�F�[�X
        Services.registerService(
                WEB3AdminAioOtherCountReferenceService.class,
            new WEB3AdminAioOtherCountReferenceServiceImpl());

        // ���ϘA�g���|�[�g�T�[�r�X�C���^�t�F�[�X
        Services.registerService(
                WEB3AdminAioSettleReportService.class,
            new WEB3AdminAioSettleReportServiceImpl());

        // ���ϘA�g���|�[�g���̓T�[�r�X�C���^�t�F�[�X
        Services.registerService(
                WEB3AdminAioSettleReportInputService.class,
            new WEB3AdminAioSettleReportInputServiceImpl());

        // �o���\���⍇���T�[�r�X�C���^�t�F�[�X
        Services.registerService(
                WEB3AdminAioCashoutInqService.class,
            new WEB3AdminAioCashoutInqServiceImpl());

        // �o���\���⍇�����̓T�[�r�X�C���^�t�F�[�X
        Services.registerService(
                WEB3AdminAioCashoutInqInputService.class,
            new WEB3AdminAioCashoutInqInputServiceImpl());

        // �����A���m�F�T�[�r�X�C���^�t�F�[�X
        Services.registerService(
                WEB3AdminAioCashinNoticeConfirmService.class,
            new WEB3AdminAioCashinNoticeConfirmServiceImpl());

        // �����A���m�F���̓T�[�r�X�C���^�t�F�[�X
        Services.registerService(
                WEB3AdminAioCashinConfirmInputService.class,
            new WEB3AdminAioCashinConfirmInputServiceImpl());

        // �����ʒm�m�F�ď����T�[�r�X
        Services.registerService(
            WEB3AdminAioCashinNoticeConfirmReTreatmentService.class,
            new WEB3AdminAioCashinNoticeConfirmReTreatmentServiceImpl());

        // �o�[�`������������UL�T�[�r�X
        Services.registerService(
            WEB3AdminAioVirtualAccCashinULService.class,
            new WEB3AdminAioVirtualAccCashinULServiceImpl());

        //�Ǘ��ғ��o���ꗗ�T�[�r�X
        Services.registerService(
            WEB3AdminAioListService.class,
            new WEB3AdminAioListServiceImpl());

        // ���o���T�[�r�X�i�Ǘ��ҁj--------------------------End------

        // �؋����U�փT�[�r�X--------------------------Begin------

        // �؋�������U�փT�[�r�X�C���^�t�F�[�X
        Services.registerService(
                WEB3AccTransChangeFromIfoDepositService.class,
            new WEB3AccTransChangeFromIfoDepositServiceImpl());

        // �؋�������U�֓��̓T�[�r�X�C���^�t�F�[�X
        Services.registerService(
                WEB3AccTransChangeFromIfoDepositInputService.class,
            new WEB3AccTransChangeFromIfoDepositInputServiceImpl());

        // �؋����ւ̐U�փT�[�r�X�C���^�t�F�[�X
        Services.registerService(
                WEB3AccTransChangeToIfoDepositService.class,
            new WEB3AccTransChangeToIfoDepositServiceImpl());

        // �؋����ւ̐U�֓��̓T�[�r�X�C���^�t�F�[�X
        Services.registerService(
                WEB3AccTransChangeToIfoDepositInputService.class,
            new WEB3AccTransChangeToIfoDepositInputServiceImpl());

        // �U�֐�����t�T�[�r�X�C���^�t�F�[�X
        Services.registerService(
                WEB3AccTransChangeRequestAcceptService.class,
            new WEB3AccTransChangeRequestAcceptServiceImpl());

        // �U�֎�tUnitService�C���^�t�F�[�X
        Services.registerService(
                WEB3AccTransChangeAcceptUnitService.class,
            new WEB3AccTransChangeAcceptUnitServiceImpl());

        // �U�֊���UnitService�C���^�t�F�[�X
        Services.registerService(
                WEB3AccTransChangeCompleteUnitService.class,
            new WEB3AccTransChangeCompleteUnitServiceImpl());

        // �U�֐����ʒm�T�[�r�X�C���^�t�F�[�X
        Services.registerService(
                WEB3AccTransChangeRequestNotifyService.class,
            new WEB3AccTransChangeRequestNotifyServiceImpl());

        // �U�֐����ʒmUnitService�C���^�t�F�[�X
        Services.registerService(
                WEB3AccTransChangeRequestNotifyUnitService.class,
            new WEB3AccTransChangeRequestNotifyUnitServiceImpl());
        // �؋����U�փT�[�r�X--------------------------End------

        // �،��U�փT�[�r�X--------------------------Begin------

        // �،��U�փT�[�r�X �C���^�t�F�[�X
        Services.registerService(
            WEB3AioSecurityTransferService.class,
            new WEB3AioSecurityTransferServiceImpl());

        // �،��U�֋����T�[�r�X  �C���^�t�F�[�X
        Services.registerService(
            WEB3AioSecurityTransferForceService.class,
            new WEB3AioSecurityTransferForceServiceImpl());

        // �،��U�֋���UnitService  �C���^�t�F�[�X
        Services.registerService(
            WEB3AioSecurityTransferForceUnitService.class,
            new WEB3AioSecurityTransferForceUnitServiceImpl());

        // �،��U�֒ʒm�T�[�r�X   �C���^�t�F�[�X
        Services.registerService(
            WEB3AioSecurityTransferNotifyService.class,
            new WEB3AioSecurityTransferNotifyServiceImpl());

        // �،��U�֒ʒmUnitService  �C���^�t�F�[�X
        Services.registerService(
            WEB3AioSecurityTransferNotifyUnitService.class,
            new WEB3AioSecurityTransferNotifyUnitServiceImpl());

        //�،��U�֓��̓T�[�r�X �C���^�t�F�[�X
        Services.registerService(
            WEB3AioSecurityTransferInputService.class,
            new WEB3AioSecurityTransferInputServiceImpl());

        //��������U�֋����T�[�r�X �C���^�t�F�[�X
        Services.registerService(
            WEB3AioSpsecTransferForceService.class,
            new WEB3AioSpsecTransferForceServiceImpl());

        //��������U�֋���UnitService  �C���^�t�F�[�X
        Services.registerService(
            WEB3AioSpsecTransferForceUnitService.class,
            new WEB3AioSpsecTransferForceUnitServiceImpl());

        // �،��U�փT�[�r�X--------------------------End------

        // �ב֕ۏ؋��T�[�r�X--------------------------Begin------

        //FX�����J�݃T�[�r�X �C���^�t�F�[�X
        Services.registerService(
            WEB3FXAccOpenService.class,
            new WEB3FXAccOpenServiceImpl());

        // FX�����J�ݓ��̓T�[�r�X �C���^�t�F�[�X
        Services.registerService(
            WEB3FXAccOpenInputService.class,
            new WEB3FXAccOpenInputServiceImpl());

        // FX����U�փT�[�r�X �C���^�t�F�[�X
        Services.registerService(
            WEB3FXTransFromFXService.class,
            new WEB3FXTransFromFXServiceImpl());

        // FX����U�֓��̓T�[�r�X �C���^�t�F�[�X
        Services.registerService(
            WEB3FXTransFromFXInputService.class,
            new WEB3FXTransFromFXInputServiceImpl());

        // FX�ւ̐U�փT�[�r�X �C���^�t�F�[�X
        Services.registerService(
            WEB3FXTransToFXService.class,
            new WEB3FXTransToFXServiceImpl());

        // FX�ւ̐U�֓��̓T�[�r�X �C���^�t�F�[�X
        Services.registerService(
            WEB3FXTransToFXInputService.class,
            new WEB3FXTransToFXInputServiceImpl());

        // �V���O���T�C���I���T�[�r�X �C���^�t�F�[�X
        Services.registerService(
            WEB3FXSingleSignOnService.class,
            new WEB3FXSingleSignOnServiceImpl());

        // �Ǘ���FX�����J�݃A�b�v���[�h�T�[�r�X�C���^�t�F�[�X
        Services.registerService(
            WEB3AdminFXAccOpenUploadService.class,
            new WEB3AdminFXAccOpenUploadServiceImpl());
        // �ב֕ۏ؋��T�[�r�X--------------------------End------

        // �ב֕ۏ؋��T�[�r�X�i�Ǘ��ҁj--------------------------Begin------

        // FX�U�֊Ǘ��T�[�r�X �C���^�t�F�[�X
        Services.registerService(
            WEB3AdminFXTransferManagementService.class,
            new WEB3AdminFXTransferManagementServiceImpl());

        // FX�U�֊Ǘ�UnitService �C���^�t�F�[�X
        Services.registerService(
            WEB3AdminFXTransferManagementUnitService.class,
            new WEB3AdminFXTransferManagementUnitServiceImpl());

        // �Ǘ���FX�����J�݊Ǘ��T�[�r�X �C���^�t�F�[�X
        Services.registerService(
            WEB3AdminFXAccOpenManagementService.class,
            new WEB3AdminFXAccOpenManagementServiceImpl());

        // �Ǘ���FX�����Ǘ��T�[�r�X �C���^�t�F�[�X
        Services.registerService(
            WEB3AdminFXAccManagementService.class,
            new WEB3AdminFXAccManagementServiceImpl());

        // FX�U�֒����A�b�v���[�h�T�[�r�X �C���^�t�F�[�X
        Services.registerService(
            WEB3AdminFXTransferOrderUploadService.class,
            new WEB3AdminFXTransferOrderUploadServiceImpl());

        // FX�U�֒���UnitService �C���^�t�F�[�X
        Services.registerService(
            WEB3AdminFXTransferOrderUnitService.class,
            new WEB3AdminFXTransferOrderUnitServiceImpl());

        // �ב֕ۏ؋��T�[�r�X�i�Ǘ��ҁj--------------------------End------

        //���o�ɗ����T�[�r�X
        Services.registerService(
            WEB3AioInputOutputHistoryService.class,
            new WEB3AioInputOutputHistoryServiceImpl());

        // �O�������U�֘A�g�T�[�r�X -----------Begin-------

        // �O���U�֘A�g�f�[�^����T�[�r�X
        Services.registerService(
            WEB3FEqConTransferDataControlService.class,
            new WEB3FEqConTransferDataControlServiceImpl());

        // �O�������ւ̐U�փT�[�r�X
        Services.registerService(
            WEB3FEqConTransferService.class,
            new WEB3FEqConTransferServiceImpl());

        // �O�������ւ̐U�֎���T�[�r�X
        Services.registerService(
            WEB3FEqConTransferCancelService.class,
            new WEB3FEqConTransferCancelServiceImpl());

        // �O�������J�݃T�[�r�X
        Services.registerService(
            WEB3FEqConAccountOpenService.class,
            new WEB3FEqConAccountOpenServiceImpl());

        // �O�������J�݊Ǘ��T�[�r�X
        Services.registerService(
            WEB3AdminFEqConAccountOpenMngService.class,
            new WEB3AdminFEqConAccountOpenMngServiceImpl());

        // �O�������Ǘ��T�[�r�X
        Services.registerService(
            WEB3AdminFEqConAccountMngService.class,
            new WEB3AdminFEqConAccountMngServiceImpl());

        // �O���U�֊Ǘ��T�[�r�X
        Services.registerService(
            WEB3AdminFEqConTransferMngService.class,
            new WEB3AdminFEqConTransferMngServiceImpl());

        // �O�������U�֘A�g�T�[�r�X -----------End-------

        // �S�ۃ��[���o���S���������T�[�r -----------Begin-------
        Services.registerService(
            WEB3AdminAioSLCashOutStopReleaseService.class,
            new WEB3AdminAioSLCashOutStopReleaseServiceImpl());

        // �S�ۃ��[���o���S���������T�[�r -----------End-------

        //�،��S�ۃ��[���Ή�
        //�،��S�ۃ��[���ԍϐ\���T�[�r�X
        Services.registerService(
            WEB3AioSLRepayApplyService.class,
            new WEB3AioSLRepayApplyServiceImpl());

        //�،��S�ۃ��[���ԍϐ\�����̓T�[�r�X
        Services.registerService(
            WEB3AioSLRepayApplyInputService.class,
            new WEB3AioSLRepayApplyInputServiceImpl());

        //�،��S�ۃ��[���f�[�^����T�[�r�X
        Services.registerService(
            WEB3AioSecuredLoanDataControlService.class,
            new WEB3AioSecuredLoanDataControlServiceImpl());

        //�،��S�ۃ��[���ԍώ���T�[�r�X
        Services.registerService(
            WEB3AioSLRepayCancelService.class,
            new WEB3AioSLRepayCancelServiceImpl());

        //�،��S�ۃ��[���ԍψꗗ�T�[�r�X
        Services.registerService(
            WEB3AioSLRepayListService.class,
            new WEB3AioSLRepayListServiceImpl());

        //SL�����J��UnitService
        Services.registerService(
            WEB3AioSLAccountOpenUnitService.class,
            new WEB3AioSLAccountOpenUnitServiceImpl());

        //�S�ۃ��[���\���󋵈ꗗ�T�[�r�X
        Services.registerService(
            WEB3AioSecuredLoanOfferStateListService.class,
            new WEB3AioSecuredLoanOfferStateListServiceImpl());

        //�S�ۖ�������T�[�r�X
        Services.registerService(
            WEB3AdminAioSLProductCancelService.class,
            new WEB3AdminAioSLProductCancelServiceImpl());
        
        // �S�ۖ����o�^�T�[�r�X
        Services.registerService(
            WEB3AdminAioSLProductRegistService.class,
            new WEB3AdminAioSLProductRegistServiceImpl());

        //�S�ۓo�^�����Ɖ�T�[�r�X
        Services.registerService(
            WEB3AdminAioSLRegistProductReferenceService.class,
            new WEB3AdminAioSLRegistProductReferenceServiceImpl());

        //�S�ۖ����ύX�T�[�r�X
        Services.registerService(
            WEB3AdminAioSLProductChangeService.class,
            new WEB3AdminAioSLProductChangeServiceImpl());
    }
    /**
     * Service �� Interceptor �ݒ菈��<BR>
     * �����J�n�����Ə����I�����������O�o�͂���悤�ɐݒ肷��
     * @@throws Exception
     */
    private static void plugLogSysTimeInterceptors() throws Exception
    {
        // ���o���T�[�r�X--------------------------Begin------
        // �}���`�o���N�d�������T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
            WEB3AioMultiBankTelegramProcessService.class,
            new WEB3LogSysTimeInterceptor());

        // ���σG���[�T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
            WEB3AioCashinSettleErrorService.class,
            new WEB3LogSysTimeInterceptor());

        // ���ψ˗��T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
                WEB3AioCashinSettlementService.class,
            new WEB3LogSysTimeInterceptor());

        // ���ϊ����T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AioCashinSettleCompleteService.class,
            new WEB3LogSysTimeInterceptor());

        // �����v�����~�T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
                WEB3AioCashinSettleCancelService.class,
            new WEB3LogSysTimeInterceptor());

        // SONAR���o���T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
                WEB3AioSonarCashTransService.class,
            new WEB3LogSysTimeInterceptor());

        // SONAR���o��UnitService�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AioSonarCashTransUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // �I�����C�������I���T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
                WEB3AioCashinSelectService.class,
            new WEB3LogSysTimeInterceptor());

        // �I�����C���������̓T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AioCashinInputService.class,
            new WEB3LogSysTimeInterceptor());

        // �o������T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
                WEB3AioCashoutCancelService.class,
            new WEB3LogSysTimeInterceptor());

        // �o�����UnitService
        Services.addInterceptor(
            WEB3AioCashoutCancelUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // �o������ꗗ�T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AioCashoutCancelListService.class,
            new WEB3LogSysTimeInterceptor());

        // �o���\���T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
                WEB3AioCashoutService.class,
            new WEB3LogSysTimeInterceptor());

        // �o���\�����̓T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AioCashoutInputService.class,
            new WEB3LogSysTimeInterceptor());

        // �o���]�̓`�F�b�N�T�[�r�X�C���^�[�t�F�C�X�N���X
        Services.addInterceptor(
                WEB3AioCashoutTradingPowerService.class,
            new WEB3LogSysTimeInterceptor());

        // �o���]�̓`�F�b�NUnitService�C���^�[�t�F�C�X
        Services.addInterceptor(
                WEB3AioCashoutTradingPowerUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // �o���A�g�T�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
            WEB3AioOnPaymentCooperationService.class,
            new WEB3LogSysTimeInterceptor());

        // �o���A�gUnitService�C���^�Z�v�^
        Services.addInterceptor(
            WEB3AioOnPaymentCooperationUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // �����A�g�T�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
            WEB3AioCashinCooperationService.class,
            new WEB3LogSysTimeInterceptor());

        // �����A�g�ʒm�ꌏ�T�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
            WEB3AioCashinCooperationNotifyUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // �����A���T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
                WEB3AioCashinNoticeService.class,
            new WEB3LogSysTimeInterceptor());

        // �����A�����[�����M�T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AioCashinNoticeMailSendService.class,
            new WEB3LogSysTimeInterceptor());


        // �����A�����̓T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
                WEB3AioCashinNoticeInputService.class,
            new WEB3LogSysTimeInterceptor());

        // ���o���ꗗ�T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AioCashTransferListService.class,
            new WEB3LogSysTimeInterceptor());

        // �o����t�T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AioCashoutAcceptService.class,
            new WEB3LogSysTimeInterceptor());

        // ������t�T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AioCashinAcceptService.class,
            new WEB3LogSysTimeInterceptor());

        // ���o������UnitService�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AioCashTransferCompleteUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // ���o����tUnitService�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AioCashTransferAcceptUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // SONAR���o���i�O�݁j�T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AioSonarCashTransForeignService.class,
            new WEB3LogSysTimeInterceptor());

        // SONAR���o���i�O�݁jUnitService�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AioSonarCashTransForeignUnitService.class,
            new WEB3LogSysTimeInterceptor());

        //�Z���z�ԍ�UnitService�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AioFinanceAmountRepayUnitService.class,
            new WEB3LogSysTimeInterceptor());

        //�Z���z�ԍσT�[�r�X �C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AioFinanceAmountRepayService.class,
            new WEB3LogSysTimeInterceptor());

        // ���o���A�g�T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AioBondOnPaymentCooperationService.class,
            new WEB3LogSysTimeInterceptor());

        // ���o���A�gUnitService�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AioBondOnPaymentCooperationUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // �o�������g���K�[���sUnitService�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AioCashOutOrderTriggerIssueUnitService.class,
            new WEB3LogSysTimeInterceptor());
        
        // ���o���T�[�r�X--------------------------End------

        //���o�ɃT�[�r�X���f�� --------------------------Begin------

        // �o�ɒʒm�T�[�r�X
        Services.addInterceptor(
            WEB3AioOutputNotifyService.class,
            new WEB3LogSysTimeInterceptor());

        // �o�ɒʒmUnitService
        Services.addInterceptor(
            WEB3AioOutputNotifyUnitService.class,
            new WEB3LogSysTimeInterceptor());
        // �ۏ؋��ւ̐U�փT�[�r�X
        Services.addInterceptor(
            WEB3MarginTransferService.class,
            new WEB3LogSysTimeInterceptor());

        //���o�ɃT�[�r�X���f�� --------------------------End-------

        // ���o���T�[�r�X�i�Ǘ��ҁj--------------------------Begin------

        // ���ϘA�g���|�[�g�T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AdminAioSettleReportService.class,
            new WEB3LogSysTimeInterceptor());

        // ���ϘA�g���|�[�g���̓T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AdminAioSettleReportInputService.class,
            new WEB3LogSysTimeInterceptor());

        // �o���\���⍇���T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AdminAioCashoutInqService.class,
            new WEB3LogSysTimeInterceptor());

        // �o���\���⍇�����̓T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AdminAioCashoutInqInputService.class,
            new WEB3LogSysTimeInterceptor());

        // �����A���m�F�T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AdminAioCashinNoticeConfirmService.class,
            new WEB3LogSysTimeInterceptor());

        // �����A���m�F���̓T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AdminAioCashinConfirmInputService.class,
            new WEB3LogSysTimeInterceptor());

        // ���̑������Ɖ�T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AdminAioOtherCountReferenceService.class,
            new WEB3LogSysTimeInterceptor());

        // �����ʒm�m�F�ď����T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
            WEB3AdminAioCashinNoticeConfirmReTreatmentService.class,
            new WEB3LogSysTimeInterceptor());

        // �o�[�`������������UL�T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
            WEB3AdminAioVirtualAccCashinULService.class,
            new WEB3LogSysTimeInterceptor());

        //�Ǘ��ғ��o���ꗗ�T�[�r�X
        Services.addInterceptor(
            WEB3AdminAioListService.class,
            new WEB3LogSysTimeInterceptor());
        // ���o���T�[�r�X�i�Ǘ��ҁj--------------------------End------

        // �؋����U�փT�[�r�X--------------------------Begin------

        // �؋�������U�փT�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AccTransChangeFromIfoDepositService.class,
            new WEB3LogSysTimeInterceptor());

        // �؋�������U�֓��̓T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AccTransChangeFromIfoDepositInputService.class,
            new WEB3LogSysTimeInterceptor());

        // �؋����ւ̐U�փT�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AccTransChangeToIfoDepositService.class,
            new WEB3LogSysTimeInterceptor());

        // �؋����ւ̐U�֓��̓T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AccTransChangeToIfoDepositInputService.class,
            new WEB3LogSysTimeInterceptor());

        // �U�֐�����t�T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AccTransChangeRequestAcceptService.class,
            new WEB3LogSysTimeInterceptor());

        // �U�֎�tUnitService�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AccTransChangeAcceptUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // �U�֊���UnitService�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AccTransChangeCompleteUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // �U�֐����ʒm�T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AccTransChangeRequestNotifyService.class,
            new WEB3LogSysTimeInterceptor());

        // �U�֐����ʒmUnitService�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AccTransChangeRequestNotifyUnitService.class,
            new WEB3LogSysTimeInterceptor());
        // �؋����U�փT�[�r�X--------------------------End------

        // �،��U�փT�[�r�X--------------------------Start------

        // �،��U�փT�[�r�X �C���^�t�F�[�X
        Services.addInterceptor(
            WEB3AioSecurityTransferService.class,
            new WEB3LogSysTimeInterceptor());

        // �،��U�֋����T�[�r�X  �C���^�t�F�[�X
        Services.addInterceptor(
            WEB3AioSecurityTransferForceService.class,
            new WEB3LogSysTimeInterceptor());

        // �،��U�֋���UnitService  �C���^�t�F�[�X
        Services.addInterceptor(
            WEB3AioSecurityTransferForceUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // �،��U�֒ʒm�T�[�r�X   �C���^�t�F�[�X
        Services.addInterceptor(
            WEB3AioSecurityTransferNotifyService.class,
            new WEB3LogSysTimeInterceptor());

        // �،��U�֒ʒmUnitService  �C���^�t�F�[�X
        Services.addInterceptor(
            WEB3AioSecurityTransferNotifyUnitService.class,
            new WEB3LogSysTimeInterceptor());

        //�،��U�֓��̓T�[�r�X �C���^�t�F�[�X
        Services.addInterceptor(
            WEB3AioSecurityTransferInputService.class,
            new WEB3LogSysTimeInterceptor());

        //��������U�֋����T�[�r�X  �C���^�t�F�[�X
        Services.addInterceptor(
            WEB3AioSpsecTransferForceService.class,
            new WEB3LogSysTimeInterceptor());

        //��������U�֋���UnitService  �C���^�t�F�[�X
        Services.addInterceptor(
            WEB3AioSpsecTransferForceUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // �،��U�փT�[�r�X--------------------------End------


        // �ב֕ۏ؋��T�[�r�X--------------------------Begin------

        //FX�����J�݃T�[�r�X �C���^�t�F�[�X
        Services.addInterceptor(
            WEB3FXAccOpenService.class,
            new WEB3LogSysTimeInterceptor());

        // FX�����J�ݓ��̓T�[�r�X �C���^�t�F�[�X
        Services.addInterceptor(
            WEB3FXAccOpenInputService.class,
            new WEB3LogSysTimeInterceptor());

        // FX����U�փT�[�r�X �C���^�t�F�[�X
        Services.addInterceptor(
            WEB3FXTransFromFXService.class,
            new WEB3LogSysTimeInterceptor());

        // FX����U�֓��̓T�[�r�X �C���^�t�F�[�X
        Services.addInterceptor(
            WEB3FXTransFromFXInputService.class,
            new WEB3LogSysTimeInterceptor());

        // FX�ւ̐U�փT�[�r�X �C���^�t�F�[�X
        Services.addInterceptor(
            WEB3FXTransToFXService.class,
            new WEB3LogSysTimeInterceptor());

        // FX�ւ̐U�֓��̓T�[�r�X �C���^�t�F�[�X
        Services.addInterceptor(
            WEB3FXTransToFXInputService.class,
            new WEB3LogSysTimeInterceptor());

        // �V���O���T�C���I���T�[�r�X �C���^�t�F�[�X
        Services.addInterceptor(
            WEB3FXSingleSignOnService.class,
            new WEB3FXSingleSignOnServiceInterceptor());

        // �Ǘ���FX�����J�݃A�b�v���[�h�T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
            WEB3AdminFXAccOpenUploadService.class,
            new WEB3LogSysTimeInterceptor());

        //�ڑ����ʃC���^�t�F�[�X
        Services.addInterceptor(
            WEB3FXConnCommonService.class,
            new WEB3LogSysTimeInterceptor());

        //FX�U�։\�z�\���T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
            WEB3FXTransferAbleAmtDisplayService.class,
            new WEB3LogSysTimeInterceptor());
        
        //�����J�ݐڑ��C���^�t�F�[�X
        Services.addInterceptor(
            WEB3FXAccOpenConnection.class,
            new WEB3LogSysTimeInterceptor());

        //�U�֐ڑ��C���^�t�F�[�X
        Services.addInterceptor(
            WEB3FXTransConnection.class,
            new WEB3LogSysTimeInterceptor());
        
        // �ב֕ۏ؋��T�[�r�X--------------------------End------

        // �ב֕ۏ؋��T�[�r�X�i�Ǘ��ҁj--------------------------Begin------

        // FX�U�֊Ǘ��T�[�r�X �C���^�t�F�[�X
        Services.addInterceptor(
            WEB3AdminFXTransferManagementService.class,
            new WEB3LogSysTimeInterceptor());

        // FX�U�֊Ǘ�UnitService �C���^�t�F�[�X
        Services.addInterceptor(
            WEB3AdminFXTransferManagementUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // �Ǘ���FX�����J�݊Ǘ��T�[�r�X �C���^�t�F�[�X
        Services.addInterceptor(
            WEB3AdminFXAccOpenManagementService.class,
            new WEB3LogSysTimeInterceptor());

        // �Ǘ���FX�����Ǘ��T�[�r�X �C���^�t�F�[�X
        Services.addInterceptor(
            WEB3AdminFXAccManagementService.class,
            new WEB3LogSysTimeInterceptor());

        // FX�U�֒����A�b�v���[�h�T�[�r�X �C���^�t�F�[�X
        Services.addInterceptor(
            WEB3AdminFXTransferOrderUploadService.class,
            new WEB3LogSysTimeInterceptor());

        // FX�U�֒���UnitService �C���^�t�F�[�X
        Services.addInterceptor(
            WEB3AdminFXTransferOrderUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // �ב֕ۏ؋��T�[�r�X�i�Ǘ��ҁj--------------------------End------

        //���o�ɗ����T�[�r�X
        Services.addInterceptor(
            WEB3AioInputOutputHistoryService.class,
            new WEB3LogSysTimeInterceptor());

        // �O�������U�֘A�g�T�[�r�X -----------Begin-------

        // �O���U�֘A�g�f�[�^����T�[�r�X
        Services.addInterceptor(
            WEB3FEqConTransferDataControlService.class,
            new WEB3LogSysTimeInterceptor());

        // �O�������ւ̐U�փT�[�r�X
        Services.addInterceptor(
            WEB3FEqConTransferService.class,
            new WEB3LogSysTimeInterceptor());

        // �O�������ւ̐U�֎���T�[�r�X
        Services.addInterceptor(
            WEB3FEqConTransferCancelService.class,
            new WEB3LogSysTimeInterceptor());

        // �O�������J�݃T�[�r�X
        Services.addInterceptor(
            WEB3FEqConAccountOpenService.class,
            new WEB3LogSysTimeInterceptor());

        // �O�������J�݊Ǘ��T�[�r�X
        Services.addInterceptor(
            WEB3AdminFEqConAccountOpenMngService.class,
            new WEB3LogSysTimeInterceptor());

        // �O�������Ǘ��T�[�r�X
        Services.addInterceptor(
            WEB3AdminFEqConAccountMngService.class,
            new WEB3LogSysTimeInterceptor());

        // �O���U�֊Ǘ��T�[�r�X
        Services.addInterceptor(
            WEB3AdminFEqConTransferMngService.class,
            new WEB3LogSysTimeInterceptor());

        // �O�������U�֘A�g�T�[�r�X -----------End-------

        // �S�ۃ��[���o���S���������T�[�r -----------Begin-------
        Services.addInterceptor(
            WEB3AdminAioSLCashOutStopReleaseService.class,
            new WEB3LogSysTimeInterceptor());

        // �S�ۃ��[���o���S���������T�[�r -----------End-------
        //�،��S�ۃ��[���Ή�
        //�،��S�ۃ��[���ԍϐ\���T�[�r�X
        Services.addInterceptor(
            WEB3AioSLRepayApplyService.class,
            new WEB3LogSysTimeInterceptor());

        //�،��S�ۃ��[���ԍϐ\�����̓T�[�r�X
        Services.addInterceptor(
            WEB3AioSLRepayApplyInputService.class,
            new WEB3LogSysTimeInterceptor());

        //�،��S�ۃ��[���f�[�^����T�[�r�X
        Services.addInterceptor(
            WEB3AioSecuredLoanDataControlService.class,
            new WEB3LogSysTimeInterceptor());

        //�،��S�ۃ��[���ԍώ���T�[�r�X
        Services.addInterceptor(
            WEB3AioSLRepayCancelService.class,
            new WEB3LogSysTimeInterceptor());

        //�،��S�ۃ��[���ԍψꗗ�T�[�r�X
        Services.addInterceptor(
            WEB3AioSLRepayListService.class,
            new WEB3LogSysTimeInterceptor());

        //SL�����J��UnitService
        Services.addInterceptor(
            WEB3AioSLAccountOpenUnitService.class,
            new WEB3LogSysTimeInterceptor());

        //�S�ۃ��[���\���󋵈ꗗ�T�[�r�X
        Services.addInterceptor(
            WEB3AioSecuredLoanOfferStateListService.class,
            new WEB3LogSysTimeInterceptor());
        
        //�S�ۖ�������T�[�r�X
        Services.addInterceptor(
            WEB3AdminAioSLProductCancelService.class,
            new WEB3LogSysTimeInterceptor());
        
        //�S�ۖ����o�^�T�[�r�X
        Services.addInterceptor(
            WEB3AdminAioSLProductRegistService.class,
            new WEB3LogSysTimeInterceptor());

        //�S�ۓo�^�����Ɖ�T�[�r�X
        Services.addInterceptor(
            WEB3AdminAioSLRegistProductReferenceService.class,
            new WEB3LogSysTimeInterceptor());
        
        //�S�ۖ����ύX�T�[�r�X
        Services.addInterceptor(
            WEB3AdminAioSLProductChangeService.class,
            new WEB3LogSysTimeInterceptor());
        
        //�S�ۖ����o�^����T�[�r�X 
        Services.addInterceptor(
            WEB3AdminAioSLProductRegistControlService.class,
            new WEB3LogSysTimeInterceptor());
        
    }
    /**
     * Service �� Interceptor �ݒ菈��<BR>
     * ServiceInterceptor�ݒ�
     * @@throws Exception
     */
    private static void plugServiceInterceptors() throws Exception
    {
        // ���o���T�[�r�X--------------------------Begin------
        // �}���`�o���N�d�������T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
            WEB3AioMultiBankTelegramProcessService.class,
            new WEB3AioMultiBankTelegramProcessServiceInterceptor());

        // ���σG���[�T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
            WEB3AioCashinSettleErrorService.class,
            new WEB3AioCashinSettleErrorServiceInterceptor());

        // ���ψ˗��T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
                WEB3AioCashinSettlementService.class,
            new WEB3AioCashinSettlementServiceInterceptor());

        // ���ϊ����T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AioCashinSettleCompleteService.class,
            new WEB3AioCashinSettleCompleteServiceInterceptor());

        // �����v�����~�T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
                WEB3AioCashinSettleCancelService.class,
            new WEB3AioCashinSettleCancelServiceInterceptor());

        // SONAR���o��UnitService�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AioSonarCashTransUnitService.class,
            new WEB3AioSonarCashTransUnitServiceInterceptor());

        // �I�����C�������I���T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
                WEB3AioCashinSelectService.class,
            new WEB3AioCashinSelectServiceInterceptor());

        // �I�����C���������̓T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AioCashinInputService.class,
            new WEB3AioCashinInputServiceInterceptor());

        // �o������T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
                WEB3AioCashoutCancelService.class,
            new WEB3AioCashoutCancelServiceInterceptor());

        // �o������ꗗ�T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AioCashoutCancelListService.class,
            new WEB3AioCashoutCancelListServiceInterceptor());

        // �o���\���T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
                WEB3AioCashoutService.class,
            new WEB3AioCashoutServiceInterceptor());

        // �o���\�����̓T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AioCashoutInputService.class,
            new WEB3AioCashoutInputServiceInterceptor());

        // �o���]�̓`�F�b�NUnitService�C���^�[�t�F�C�X
        Services.addInterceptor(
                WEB3AioCashoutTradingPowerUnitService.class,
            new WEB3AioCashoutTradingPowerUnitServiceInterceptor());

        // �o���A�g�T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
                WEB3AioOnPaymentCooperationService.class,
            new WEB3AioOnPaymentCooperationServiceInterceptor());

        // �o���A�gUnitService �C���^�[�t�F�C�X
        Services.addInterceptor(
                WEB3AioOnPaymentCooperationUnitService.class,
            new WEB3AioOnPaymentCooperationUnitServiceInterceptor());

        // �����A�g�ʒm�ꌏ�T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
                WEB3AioCashinCooperationNotifyUnitService.class,
            new WEB3AioCashinCooperationNotifyUnitServiceInterceptor());

        // �����A���T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
                WEB3AioCashinNoticeService.class,
            new WEB3AioCashinNoticeServiceInterceptor());

        // �����A�����̓T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
                WEB3AioCashinNoticeInputService.class,
            new WEB3AioCashinNoticeInputServiceInterceptor());

        // ���o���ꗗ�T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AioCashTransferListService.class,
            new WEB3AioCashTransferListServiceInterceptor());

        // ���o������UnitService�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AioCashTransferCompleteUnitService.class,
            new WEB3AioCashTransferCompleteUnitServiceInterceptor());

        // ���o����tUnitService�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AioCashTransferAcceptUnitService.class,
            new WEB3AioCashTransferAcceptUnitServiceInterceptor());

        // SONAR���o���i�O�݁jUnitService�C���^�t�F�[�X
        Services.addInterceptor(
            WEB3AioSonarCashTransForeignUnitService.class,
            new WEB3AioSonarCashTransForeignUnitServiceInterceptor());

        //�Z���z�ԍ�UnitService�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AioFinanceAmountRepayUnitService.class,
                new WEB3AioFinanceAmountRepayUnitServiceInterceptor());

        // ���o���A�gUnitService�C���^�t�F�[�X
        Services.addInterceptor(
            WEB3AioBondOnPaymentCooperationUnitService.class,
            new WEB3AioBondOnPaymentCooperationUnitServiceInterceptor());
        // ���o���T�[�r�X--------------------------End------

        // ���o���T�[�r�X�i�Ǘ��ҁj--------------------------Begin------

        // �����A���m�F�T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AdminAioCashinNoticeConfirmService.class,
            new WEB3AdminAioCashinNoticeConfirmServiceInterceptor());

        // �����A���m�F���̓T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AdminAioCashinConfirmInputService.class,
            new WEB3AdminAioCashinConfirmInputServiceInterceptor());

        // �����ʒm�m�F�ď����T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
            WEB3AdminAioCashinNoticeConfirmReTreatmentService.class,
            new WEB3AdminAioCashinNoticeConfirmReTreatmentServiceInterceptor());

        // �o�[�`������������UL�T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
            WEB3AdminAioVirtualAccCashinULService.class,
            new WEB3AdminAioVirtualAccCashinULServiceInterceptor());

        // ���o���T�[�r�X�i�Ǘ��ҁj--------------------------End------

        // �؋����U�փT�[�r�X--------------------------Begin------

        // �؋�������U�փT�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AccTransChangeFromIfoDepositService.class,
            new WEB3AccTransChangeFromIfoDepositServiceInterceptor());

        // �؋�������U�֓��̓T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AccTransChangeFromIfoDepositInputService.class,
            new WEB3AccTransChangeFromIfoDepositInputServiceInterceptor());

        // �؋����ւ̐U�փT�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AccTransChangeToIfoDepositService.class,
            new WEB3AccTransChangeToIfoDepositServiceInterceptor());

        // �؋����ւ̐U�֓��̓T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AccTransChangeToIfoDepositInputService.class,
            new WEB3AccTransChangeToIfoDepositInputServiceInterceptor());

        // �U�֎�tUnitService�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AccTransChangeAcceptUnitService.class,
            new WEB3AccTransChangeAcceptUnitServiceInterceptor());

        // �U�֊���UnitService�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AccTransChangeCompleteUnitService.class,
            new WEB3AccTransChangeCompleteUnitServiceInterceptor());

        // �U�֐����ʒmUnitService�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AccTransChangeRequestNotifyUnitService.class,
            new WEB3AccTransChangeRequestNotifyUnitServiceInterceptor());
        // �؋����U�փT�[�r�X--------------------------End------

        // �،��U�փT�[�r�X--------------------------Start------

        // �،��U�փT�[�r�X �C���^�t�F�[�X
        Services.addInterceptor(
            WEB3AioSecurityTransferService.class,
            new WEB3AioSecurityTransferServiceInterceptor());

        // �،��U�֋���UnitService  �C���^�t�F�[�X
        Services.addInterceptor(
            WEB3AioSecurityTransferForceUnitService.class,
            new WEB3AioSecurityTransferForceUnitServiceInterceptor());

        // �،��U�֒ʒmUnitService  �C���^�t�F�[�X
        Services.addInterceptor(
            WEB3AioSecurityTransferNotifyUnitService.class,
            new WEB3AioSecurityTransferNotifyUnitServiceInterceptor());

        //�،��U�֓��̓T�[�r�X �C���^�t�F�[�X
        Services.addInterceptor(
            WEB3AioSecurityTransferInputService.class,
            new WEB3AioSecurityTransferInputServiceInterceptor());

        //��������U�֋���UnitService  �C���^�t�F�[�X
        Services.addInterceptor(
            WEB3AioSpsecTransferForceUnitService.class,
            new WEB3AioSpsecTransferForceUnitServiceInterceptor());

        // �،��U�փT�[�r�X--------------------------End------

        // �ב֕ۏ؋��T�[�r�X--------------------------Begin------

        //FX�����J�݃T�[�r�X �C���^�t�F�[�X
        Services.addInterceptor(
            WEB3FXAccOpenService.class,
            new WEB3FXAccOpenServiceInterceptor());

        // FX�����J�ݓ��̓T�[�r�X �C���^�t�F�[�X
        Services.addInterceptor(
            WEB3FXAccOpenInputService.class,
            new WEB3FXAccOpenInputServiceInterceptor());

        // FX����U�փT�[�r�X �C���^�t�F�[�X
        Services.addInterceptor(
            WEB3FXTransFromFXService.class,
            new WEB3FXTransFromFXServiceInterceptor());

        // FX����U�֓��̓T�[�r�X �C���^�t�F�[�X
        Services.addInterceptor(
            WEB3FXTransFromFXInputService.class,
            new WEB3FXTransFromFXInputServiceInterceptor());

        // FX�ւ̐U�փT�[�r�X �C���^�t�F�[�X
        Services.addInterceptor(
            WEB3FXTransToFXService.class,
            new WEB3FXTransToFXServiceInterceptor());

        // FX�ւ̐U�֓��̓T�[�r�X �C���^�t�F�[�X
        Services.addInterceptor(
            WEB3FXTransToFXInputService.class,
            new WEB3FXTransToFXInputServiceInterceptor());

        // �V���O���T�C���I���T�[�r�X �C���^�t�F�[�X
        Services.addInterceptor(
            WEB3FXSingleSignOnService.class,
            new WEB3FXSingleSignOnServiceInterceptor());

        // �Ǘ���FX�����J�݃A�b�v���[�h�T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
            WEB3AdminFXAccOpenUploadService.class,
            new WEB3AdminFXAccOpenUploadServiceInterceptor());
        // �ב֕ۏ؋��T�[�r�X--------------------------End------

        // �ב֕ۏ؋��T�[�r�X�i�Ǘ��ҁj--------------------------Begin------

        // FX�U�֊Ǘ�UnitService �C���^�t�F�[�X
        Services.addInterceptor(
            WEB3AdminFXTransferManagementUnitService.class,
            new WEB3AdminFXTransferManagementUnitServiceInterceptor());

        // �Ǘ���FX�����J�݊Ǘ��T�[�r�X �C���^�t�F�[�X
        Services.addInterceptor(
            WEB3AdminFXAccOpenManagementService.class,
            new WEB3AdminFXAccOpenManagementServiceInterceptor());

        // �Ǘ���FX�����Ǘ��T�[�r�X �C���^�t�F�[�X
        Services.addInterceptor(
            WEB3AdminFXAccManagementService.class,
            new WEB3AdminFXAccManagementServiceInterceptor());

        // FX�U�֒����A�b�v���[�h�T�[�r�X �C���^�t�F�[�X
        Services.addInterceptor(
            WEB3AdminFXTransferOrderUploadService.class,
            new WEB3AdminFXTransferOrderServiceInterceptor());

        // �ב֕ۏ؋��T�[�r�X�i�Ǘ��ҁj--------------------------End------

        //���o�ɃT�[�r�X���f�� --------------------------Begin------

        // �o�ɒʒmUnitService
        Services.addInterceptor(
            WEB3AioOutputNotifyUnitService.class,
            new WEB3AioOutputNotifyUnitServiceInterceptor());

        //���o�ɗ����T�[�r�X
        Services.addInterceptor(
            WEB3AioInputOutputHistoryService.class,
            new WEB3AioInputOutputHistoryServiceInterceptor());

        //���o�ɃT�[�r�X���f�� --------------------------End------

        // �O�������U�֘A�g�T�[�r�X -----------Begin-------

        // �O�������ւ̐U�փT�[�r�X
        Services.addInterceptor(
            WEB3FEqConTransferService.class,
            new WEB3FEqConTransferServiceInterceptor());

        // �O�������ւ̐U�֎���T�[�r�X
        Services.addInterceptor(
            WEB3FEqConTransferCancelService.class,
            new WEB3FEqConTransferCancelServiceInterceptor());

        // �O�������J�݃T�[�r�X
        Services.addInterceptor(
            WEB3FEqConAccountOpenService.class,
            new WEB3FEqConAccountOpenServiceInterceptor());

        // �O�������J�݊Ǘ��T�[�r�X
        Services.addInterceptor(
            WEB3AdminFEqConAccountOpenMngService.class,
            new WEB3AdminFEqConAccountOpenMngServiceInterceptor());

        // �O�������Ǘ��T�[�r�X
        Services.addInterceptor(
            WEB3AdminFEqConAccountMngService.class,
            new WEB3LogSysTimeInterceptor());

        // �O���U�֊Ǘ��T�[�r�X
        Services.addInterceptor(
            WEB3AdminFEqConTransferMngService.class,
            new WEB3AdminFEqConAccountMngServiceInterceptor());

        // �O�������U�֘A�g�T�[�r�X -----------End-------
        
        //�S�ۃ��[���o���S���������T�[�r--------------------------Begin------
        Services.addInterceptor(
            WEB3AdminAioSLCashOutStopReleaseService.class,
            new WEB3AdminAioSLCashOutStopReleaseServiceInterceptor());
        
        //�S�ۃ��[���o���S���������T�[�r--------------------------End------

        //�،��S�ۃ��[���Ή�
        //�،��S�ۃ��[���ԍϐ\���T�[�r�X
        Services.addInterceptor(
            WEB3AioSLRepayApplyService.class,
            new WEB3AioSLRepayApplyServiceInterceptor());

        //�،��S�ۃ��[���ԍϐ\�����̓T�[�r�X
        Services.addInterceptor(
            WEB3AioSLRepayApplyInputService.class,
            new WEB3AioSLRepayApplyInputServiceInterceptor());

        //�،��S�ۃ��[���ԍώ���T�[�r�X
        Services.addInterceptor(
            WEB3AioSLRepayCancelService.class,
            new WEB3AioSLRepayCancelServiceInterceptor());

        //�،��S�ۃ��[���ԍψꗗ�T�[�r�X
        Services.addInterceptor(
            WEB3AioSLRepayListService.class,
            new WEB3AioSLRepayListServiceInterceptor());

        //SL�����J��UnitService
        Services.addInterceptor(
            WEB3AioSLAccountOpenUnitService.class,
            new WEB3AioSLAccountOpenServiceInterceptor());
        
        //�S�ۖ�������T�[�r�X
        Services.addInterceptor(
            WEB3AdminAioSLProductCancelService.class,
            new WEB3AdminAioSLProductRegistServiceInterceptor());
        
        //�S�ۖ����o�^�T�[�r�X
        Services.addInterceptor(
            WEB3AdminAioSLProductRegistService.class,
            new WEB3AdminAioSLProductRegistServiceInterceptor());

        //�S�ۓo�^�����Ɖ�T�[�r�X
        Services.addInterceptor(
            WEB3AdminAioSLRegistProductReferenceService.class,
            new WEB3AdminAioSLProductRegistServiceInterceptor());
        
        //�S�ۖ����ύX�T�[�r�X
        Services.addInterceptor(
            WEB3AdminAioSLProductChangeService.class,
            new WEB3AdminAioSLProductRegistServiceInterceptor());
    }
    /**
     * Service �� Interceptor �ݒ菈��<BR>
     * �����g�����U�N�V�����ݒ�
     * @@throws Exception
     */
    private static void plugTransactionalInterceptors() throws Exception
    {
        // ���o���T�[�r�X--------------------------Begin------
        // �}���`�o���N�d�������T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
            WEB3AioMultiBankTelegramProcessService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // ���σG���[�T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
            WEB3AioCashinSettleErrorService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // ���ψ˗��T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
                WEB3AioCashinSettlementService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // ���ϊ����T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AioCashinSettleCompleteService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // �����v�����~�T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
                WEB3AioCashinSettleCancelService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // SONAR���o���T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
                WEB3AioSonarCashTransService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // SONAR���o��UnitService�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AioSonarCashTransUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // �I�����C�������I���T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
                WEB3AioCashinSelectService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // �I�����C���������̓T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AioCashinInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // �o������T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
                WEB3AioCashoutCancelService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // �o�����UnitService
        Services.addInterceptor(
            WEB3AioCashoutCancelUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // �o������ꗗ�T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AioCashoutCancelListService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // �o���\���T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
                WEB3AioCashoutService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // �o���\�����̓T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AioCashoutInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // �o���]�̓`�F�b�N�T�[�r�X�C���^�[�t�F�C�X�N���X
        Services.addInterceptor(
                WEB3AioCashoutTradingPowerService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // �o���]�̓`�F�b�NUnitService�C���^�[�t�F�C�X
        Services.addInterceptor(
                WEB3AioCashoutTradingPowerUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        // �o���A�g�T�[�r�X�C���^�[�t�F�C�X�N���X
        Services.addInterceptor(
                WEB3AioOnPaymentCooperationService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // �o���A�gUnitService�C���^�[�t�F�C�X
        Services.addInterceptor(
                WEB3AioOnPaymentCooperationUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        // �����A�g�T�[�r�X
        Services.addInterceptor(
                WEB3AioCashinCooperationService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // �����A�g�ʒm�ꌏ�T�[�r�X
        Services.addInterceptor(
                WEB3AioCashinCooperationNotifyUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));


        // �����A���T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
                WEB3AioCashinNoticeService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // �����A�����[�����M�T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AioCashinNoticeMailSendService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));


        // �����A�����̓T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
                WEB3AioCashinNoticeInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // ���o���ꗗ�T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AioCashTransferListService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // �o����t�T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AioCashoutAcceptService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // ������t�T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AioCashinAcceptService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // ���o������UnitService�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AioCashTransferCompleteUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // ���o����tUnitService�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AioCashTransferAcceptUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // SONAR���o���i�O�݁j�T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AioSonarCashTransForeignService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // SONAR���o���i�O�݁jUnitService�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AioSonarCashTransForeignUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�O�ݓ��o����t�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AioForeignCashTransAcceptService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�Z���z�ԍ�UnitService�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AioFinanceAmountRepayUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //�Z���z�ԍσT�[�r�X �C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AioFinanceAmountRepayService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //���o���A�g�T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AioBondOnPaymentCooperationService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // ���o���A�gUnitService�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AioBondOnPaymentCooperationUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //�o�������g���K�[���sUnitService�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AioCashOutOrderTriggerIssueUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));
        // �ۏ؋��ւ̐U�փT�[�r�X
        Services.addInterceptor(
                WEB3MarginTransferService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        // ���o���T�[�r�X--------------------------End------

        // ���o���T�[�r�X�i�Ǘ��ҁj--------------------------Begin------

        // ���ϘA�g���|�[�g�T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AdminAioSettleReportService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // ���ϘA�g���|�[�g���̓T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AdminAioSettleReportInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // �o���\���⍇���T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AdminAioCashoutInqService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // �o���\���⍇�����̓T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AdminAioCashoutInqInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // �����A���m�F�T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AdminAioCashinNoticeConfirmService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // �����A���m�F���̓T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AdminAioCashinConfirmInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // ���̑������Ɖ�T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AdminAioOtherCountReferenceService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // �����ʒm�m�F�ď����T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
            WEB3AdminAioCashinNoticeConfirmReTreatmentService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // �o�[�`������������UL�T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
            WEB3AdminAioVirtualAccCashinULService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�Ǘ��ғ��o���ꗗ�T�[�r�X
        Services.addInterceptor(
            WEB3AdminAioListService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        // ���o���T�[�r�X�i�Ǘ��ҁj--------------------------End------

        //���o�ɃT�[�r�X���f�� --------------------------Begin------

        // �o�ɒʒm�T�[�r�X
        Services.addInterceptor(
            WEB3AioOutputNotifyService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // �o�ɒʒmUnitService
        Services.addInterceptor(
            WEB3AioOutputNotifyUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //���o�ɃT�[�r�X���f�� --------------------------End--------

        // �؋����U�փT�[�r�X--------------------------Begin------

        // �؋�������U�փT�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AccTransChangeFromIfoDepositService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // �؋�������U�֓��̓T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AccTransChangeFromIfoDepositInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // �؋����ւ̐U�փT�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AccTransChangeToIfoDepositService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // �؋����ւ̐U�֓��̓T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AccTransChangeToIfoDepositInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // �U�֐�����t�T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AccTransChangeRequestAcceptService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // �U�֎�tUnitService�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AccTransChangeAcceptUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // �U�֊���UnitService�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AccTransChangeCompleteUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // �U�֐����ʒm�T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AccTransChangeRequestNotifyService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // �U�֐����ʒmUnitService�C���^�t�F�[�X
        Services.addInterceptor(
                WEB3AccTransChangeRequestNotifyUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        // �؋����U�փT�[�r�X--------------------------End------

        // �،��U�փT�[�r�X--------------------------Start------

        // �،��U�փT�[�r�X �C���^�t�F�[�X
        Services.addInterceptor(
            WEB3AioSecurityTransferService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // �،��U�֋����T�[�r�X  �C���^�t�F�[�X
        Services.addInterceptor(
            WEB3AioSecurityTransferForceService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // �،��U�֋���UnitService  �C���^�t�F�[�X
        Services.addInterceptor(
            WEB3AioSecurityTransferForceUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // �،��U�֒ʒm�T�[�r�X   �C���^�t�F�[�X
        Services.addInterceptor(
            WEB3AioSecurityTransferNotifyService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // �،��U�֒ʒmUnitService  �C���^�t�F�[�X
        Services.addInterceptor(
            WEB3AioSecurityTransferNotifyUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�،��U�֓��̓T�[�r�X �C���^�t�F�[�X
        Services.addInterceptor(
            WEB3AioSecurityTransferInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //��������U�֋����T�[�r�X �C���^�t�F�[�X
        Services.addInterceptor(
            WEB3AioSpsecTransferForceService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //��������U�֋���UnitService  �C���^�t�F�[�X
        Services.addInterceptor(
            WEB3AioSpsecTransferForceUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // �،��U�փT�[�r�X--------------------------End------

        // �ב֕ۏ؋��T�[�r�X--------------------------Begin------

        // FX�����J�ݓ��̓T�[�r�X �C���^�t�F�[�X
        Services.addInterceptor(
            WEB3FXAccOpenInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // FX����U�փT�[�r�X �C���^�t�F�[�X
        Services.addInterceptor(
            WEB3FXTransFromFXService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // FX����U�֓��̓T�[�r�X �C���^�t�F�[�X
        Services.addInterceptor(
            WEB3FXTransFromFXInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // FX�ւ̐U�փT�[�r�X �C���^�t�F�[�X
        Services.addInterceptor(
            WEB3FXTransToFXService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // FX�ւ̐U�֓��̓T�[�r�X �C���^�t�F�[�X
        Services.addInterceptor(
            WEB3FXTransToFXInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // FX�f�[�^����T�[�r�X �C���^�t�F�[�X
        Services.addInterceptor(
            WEB3FXDataControlService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        // �V���O���T�C���I���T�[�r�X �C���^�t�F�[�X
        Services.addInterceptor(
            WEB3FXSingleSignOnService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        // �Ǘ���FX�����J�݃A�b�v���[�h�T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
            WEB3AdminFXAccOpenUploadService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�ڑ����ʃC���^�t�F�[�X
        Services.addInterceptor(
            WEB3FXConnCommonService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //FX�U�։\�z�\���T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
            WEB3FXTransferAbleAmtDisplayService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //�����J�ݐڑ��C���^�t�F�[�X
        Services.addInterceptor(
            WEB3FXAccOpenConnection.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //�U�֐ڑ��C���^�t�F�[�X
        Services.addInterceptor(
            WEB3FXTransConnection.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // �ב֕ۏ؋��T�[�r�X--------------------------End------

        // �ב֕ۏ؋��T�[�r�X�i�Ǘ��ҁj--------------------------Begin------

        // FX�U�֊Ǘ�UnitService �C���^�t�F�[�X
        Services.addInterceptor(
            WEB3AdminFXTransferManagementUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        // �Ǘ���FX�����J�݊Ǘ��T�[�r�X �C���^�t�F�[�X
        Services.addInterceptor(
            WEB3AdminFXAccOpenManagementService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // �Ǘ���FX�����Ǘ��T�[�r�X �C���^�t�F�[�X
        Services.addInterceptor(
            WEB3AdminFXAccManagementService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // FX�U�֒����A�b�v���[�h�T�[�r�X �C���^�t�F�[�X
        Services.addInterceptor(
            WEB3AdminFXTransferOrderUploadService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // FX�U�֒���UnitService �C���^�t�F�[�X
        Services.addInterceptor(
            WEB3AdminFXTransferOrderUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));
        // �ב֕ۏ؋��T�[�r�X�i�Ǘ��ҁj--------------------------End------

        //���o�ɗ����T�[�r�X
        Services.addInterceptor(
            WEB3AioInputOutputHistoryService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        // �O�������U�֘A�g�T�[�r�X -----------Begin-------

        // �O���U�֘A�g�f�[�^����T�[�r�X
        Services.addInterceptor(
            WEB3FEqConTransferDataControlService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        // �O�������ւ̐U�փT�[�r�X
        Services.addInterceptor(
            WEB3FEqConTransferService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        // �O�������ւ̐U�֎���T�[�r�X
        Services.addInterceptor(
            WEB3FEqConTransferCancelService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        // �O�������J�݃T�[�r�X
        Services.addInterceptor(
            WEB3FEqConAccountOpenService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        // �O�������J�݊Ǘ��T�[�r�X
        Services.addInterceptor(
            WEB3AdminFEqConAccountOpenMngService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        // �O�������Ǘ��T�[�r�X
        Services.addInterceptor(
            WEB3AdminFEqConAccountMngService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        // �O���U�֊Ǘ��T�[�r�X
        Services.addInterceptor(
            WEB3AdminFEqConTransferMngService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        // �O�������U�֘A�g�T�[�r�X -----------End-------
        
        // �S�ۃ��[���o���S���������T�[�r -----------Begin-------
        Services.addInterceptor(
            WEB3AdminAioSLCashOutStopReleaseService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));
        
        // �S�ۃ��[���o���S���������T�[�r -----------End-------

        //�،��S�ۃ��[���Ή�
        //�،��S�ۃ��[���ԍϐ\���T�[�r�X
        Services.addInterceptor(
            WEB3AioSLRepayApplyService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        //�،��S�ۃ��[���ԍϐ\�����̓T�[�r�X
        Services.addInterceptor(
            WEB3AioSLRepayApplyInputService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        //�،��S�ۃ��[���f�[�^����T�[�r�X
        Services.addInterceptor(
            WEB3AioSecuredLoanDataControlService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        //�،��S�ۃ��[���ԍώ���T�[�r�X
        Services.addInterceptor(
            WEB3AioSLRepayCancelService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        //�،��S�ۃ��[���ԍψꗗ�T�[�r�X
        Services.addInterceptor(
            WEB3AioSLRepayListService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        //SL�����J��UnitService
        Services.addInterceptor(
            WEB3AioSLAccountOpenUnitService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        //�S�ۃ��[���\���󋵈ꗗ�T�[�r�X
        Services.addInterceptor(
            WEB3AioSecuredLoanOfferStateListService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        //�S�ۖ�������T�[�r�X
        Services.addInterceptor(
            WEB3AdminAioSLProductCancelService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //�S�ۖ����o�^�T�[�r�X
        Services.addInterceptor(
            WEB3AdminAioSLProductRegistService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        //�S�ۓo�^�����Ɖ�T�[�r�X
        Services.addInterceptor(
            WEB3AdminAioSLRegistProductReferenceService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        //�S�ۖ����ύX�T�[�r�X
        Services.addInterceptor(
            WEB3AdminAioSLProductChangeService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        //�S�ۖ����o�^����T�[�r�X
        Services.addInterceptor(
            WEB3AdminAioSLProductRegistControlService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));
    }
    /**
     * MQ-Gateway �� Interceptor �ݒ菈��
     * @@throws Exception
     */
    private static void plugMQGatewayInterceptors() throws Exception
    {

        // ���o���T�[�r�X--------------------------Begin------

        //�����A�g�ꌏ�����T�[�r�X
        Services.addInterceptor(
                WEB3AioCashinCooperationNotifyUnitService.class,
            new WEB3MQGatewayInterceptor());

        //�o���\���T�[�r�X
        Services.addInterceptor(
            WEB3AioCashoutService.class,
            new WEB3MQGatewayInterceptor());

        //�o���A�g�T�[�r�X
        Services.addInterceptor(
            WEB3AioOnPaymentCooperationService.class,
            new WEB3MQGatewayInterceptor());

        //�o������T�[�r�X
        Services.addInterceptor(
            WEB3AioCashoutCancelService.class,
            new WEB3MQGatewayInterceptor());

        //�}���`�o���N�d�������T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
            WEB3AioMultiBankTelegramProcessService.class,
            new WEB3MQGatewayInterceptor());

        //�o���]�̓`�F�b�N�T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
            WEB3AioCashoutTradingPowerService.class,
            new WEB3MQGatewayInterceptor());
        // ���o���T�[�r�X--------------------------End------

        // ���o���T�[�r�X�i�Ǘ��ҁj--------------------------Begin------

        //�o���\���⍇���T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
            WEB3AdminAioCashoutInqService.class,
            new WEB3MQGatewayInterceptor());
        // ���o���T�[�r�X�i�Ǘ��ҁj--------------------------End------

        // �؋����U�փT�[�r�X--------------------------Begin------

        // �؋�������U�փT�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
            WEB3AccTransChangeFromIfoDepositService.class,
            new WEB3MQGatewayInterceptor());

        // �؋����ւ̐U�փT�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
            WEB3AccTransChangeToIfoDepositService.class,
            new WEB3MQGatewayInterceptor());
        // �؋����U�փT�[�r�X--------------------------End------

        // �،��U�փT�[�r�X--------------------------Start------

        //�،��U�փT�[�r�X
        Services.addInterceptor(
            WEB3AioSecurityTransferService.class,
            new WEB3MQGatewayInterceptor());
        // �،��U�փT�[�r�X--------------------------End------

        // �ב֕ۏ؋��T�[�r�X--------------------------Start------

        // FX����U�փT�[�r�X
        Services.addInterceptor(
            WEB3FXTransFromFXService.class,
            new WEB3MQGatewayInterceptor());

        // FX�ւ̐U�փT�[�r�X
        Services.addInterceptor(
            WEB3FXTransToFXService.class,
            new WEB3MQGatewayInterceptor());
        // �ב֕ۏ؋��T�[�r�X--------------------------End------

        // �ב֕ۏ؋��T�[�r�X�i�Ǘ��ҁj--------------------------Begin------

        // FX�U�֒����A�b�v���[�h�T�[�r�X �C���^�t�F�[�X
        Services.addInterceptor(
            WEB3AdminFXTransferOrderUploadService.class,
            new WEB3MQGatewayInterceptor());

        // �ב֕ۏ؋��T�[�r�X�i�Ǘ��ҁj--------------------------End------

        // �O�������U�֘A�g�T�[�r�X--------------------------Start------

        // �O�������ւ̐U�փT�[�r�X
        Services.addInterceptor(
            WEB3FEqConTransferService.class,
            new WEB3MQGatewayInterceptor());

        // �O�������U�֘A�g�T�[�r�X--------------------------End------
        
        //���o���A�g�T�[�r�X--------------------------Start------
        Services.addInterceptor(
            WEB3AioBondOnPaymentCooperationUnitService.class,
            new WEB3MQGatewayInterceptor());
        //���o���A�g�T�[�r�X--------------------------End------

        //�o�������g���K�[���sUnitService--------------------------Start------
        Services.addInterceptor(
            WEB3AioCashOutOrderTriggerIssueUnitService.class,
            new WEB3MQGatewayInterceptor());
        //�o�������g���K�[���sUnitService--------------------------End------

        //�،��S�ۃ��[���ԍώ��
        Services.addInterceptor(
            WEB3AioSLRepayCancelService.class,
            new WEB3MQGatewayInterceptor());

        //�،��S�ۃ��[���ԍϐ\��
        Services.addInterceptor(
            WEB3AioSLRepayApplyService.class,
            new WEB3MQGatewayInterceptor());
    }
    /**
     * Message �̓o�^����
     * @@throws Exception
     */
    private static void plugMessages() throws Exception
    {
        //FX�ւ̐U��SOAP���N�G�X�g
        regClass(WEB3FXTransToFXCompleteSoapRequest.class);
        //FX�ւ̐U��SOAP���X�|���X
        regClass(WEB3FXTransToFXCompleteSoapResponse.class);
        //�Ǘ��ҁEFX�����J�ݐ\���_�E�����[�h���N�G�X�g
        regClass(WEB3AdminFXAccOpenApplyDownloadRequest.class);
        //�Ǘ��ҁEFX�����J�ݐ\���_�E�����[�h���X�|���X
        regClass(WEB3AdminFXAccOpenApplyDownloadResponse.class);

        //�Ǘ��ҁEFX�����J�݃A�b�v���[�h���~���N�G�X�g
        regClass(WEB3AdminFXAccOpenUploadCancelRequest.class);
        //�Ǘ��ҁEFX�����J�݃A�b�v���[�h���~���X�|���X
        regClass(WEB3AdminFXAccOpenUploadCancelResponse.class);
        //�Ǘ��ҁEFX�����J�݃A�b�v���[�h�������X�|���X
        regClass(WEB3AdminFXAccOpenUploadCompleteRequest.class);
        //�Ǘ��ҁEFX�����J�݃A�b�v���[�h�������X�|���X
        regClass(WEB3AdminFXAccOpenUploadCompleteResponse.class);
        //�Ǘ��ҁEFX�����J�݃A�b�v���[�h�m�F���N�G�X�g
        regClass(WEB3AdminFXAccOpenUploadConfirmRequest.class);
        //�Ǘ��ҁEFX�����J�݃A�b�v���[�h�m�F���X�|���X
        regClass(WEB3AdminFXAccOpenUploadConfirmResponse.class);
        //�Ǘ��ҁEFX�����J�݃A�b�v���[�h���̓��N�G�X�g
        regClass(WEB3AdminFXAccOpenUploadInputRequest.class);
        //�Ǘ��ҁEFX�����J�݃A�b�v���[�h���̓��X�|���X
        regClass(WEB3AdminFXAccOpenUploadInputResponse.class);
        //�Ǘ��ҁEFX�U�֒����A�b�v���[�h���~���N�G�X�g
        regClass(WEB3AdminFXTransferUploadCancelRequest.class);
        //�Ǘ��ҁEFX�U�֒����A�b�v���[�h���~���X�|���X
        regClass(WEB3AdminFXTransferUploadCancelResponse.class);
        //�Ǘ��ҁEFX�U�֒����A�b�v���[�h�������N�G�X�g
        regClass(WEB3AdminFXTransferUploadCompleteRequest.class);
        //�Ǘ��ҁEFX�U�֒����A�b�v���[�h�������X�|���X
        regClass(WEB3AdminFXTransferUploadCompleteResponse.class);
        //�Ǘ��ҁEFX�U�֒����A�b�v���[�h�m�F���N�G�X�g
        regClass(WEB3AdminFXTransferUploadConfirmRequest.class);
        //�Ǘ��ҁEFX�U�֒����A�b�v���[�h�m�F���X�|���X
        regClass(WEB3AdminFXTransferUploadConfirmResponse.class);
        //�Ǘ��ҁEFX�U�֒����A�b�v���[�h���̓��N�G�X�g
        regClass(WEB3AdminFXTransferUploadInputRequest.class);
        //�Ǘ��ҁEFX�U�֒����A�b�v���[�h���̓��X�|���X
        regClass(WEB3AdminFXTransferUploadInputResponse.class);
        // SONAR���o�����N�G�X�g
        regClass(WEB3AioSonarCashTransRequest.class);
        // SONAR���o�����X�|���X
        regClass(WEB3AioSonarCashTransResponse.class);
        // �I�����C�������m�F���N�G�X�g
        regClass(WEB3AioCashinConfirmRequest.class);
        // �I�����C�������m�F���X�|���X
        regClass(WEB3AioCashinConfirmResponse.class);
        // �I�����C�������I����ʃ��N�G�X�g
        regClass(WEB3AioCashinSelectRequest.class);
        // �I�����C�������I����ʃ��X�|���X
        regClass(WEB3AioCashinSelectResponse.class);
        // �I�����C���������̓��N�G�X�g
        regClass(WEB3AioCashinInputRequest.class);
        // �I�����C���������̓��X�|���X
        regClass(WEB3AioCashinInputResponse.class);
        // ���σG���[���N�G�X�g
        regClass(WEB3AioCashinSettleErrorRequest.class);
        // ���σG���[���X�|���X
        regClass(WEB3AioCashinSettleErrorResponse.class);
        // ���ψ˗����N�G�X�g
        regClass(WEB3AioCashinSettlementRequest.class);
        // ���ψ˗����X�|���X
        regClass(WEB3AioCashinSettlementResponse.class);
        // ���ϊ������N�G�X�g
        regClass(WEB3AioCashinSettleCompleteRequest.class);
        // ���ϊ������X�|���X
        regClass(WEB3AioCashinSettleCompleteResponse.class);
        // �o������ꗗ���N�G�X�g
        regClass(WEB3AioCashoutCancelListRequest.class);
        // �o������ꗗ���X�|���X
        regClass(WEB3AioCashoutCancelListResponse.class);
        // �o������m�F���N�G�X�g
        regClass(WEB3AioCashoutCancelConfirmRequest.class);
        // �o������m�F���X�|���X
        regClass(WEB3AioCashoutCancelConfirmResponse.class);
        // �o������������N�G�X�g
        regClass(WEB3AioCashoutCancelCompleteRequest.class);
        // �o������������X�|���X
        regClass(WEB3AioCashoutCancelCompleteResponse.class);
        // �o����t���N�G�X�g
        regClass(WEB3AioCashoutAcceptRequest.class);
        // �o����t���X�|���X
        regClass(WEB3AioCashoutAcceptResponse.class);
        // �o���\���m�F���N�G�X�g
        regClass(WEB3AioCashoutConfirmRequest.class);
        // �o���\���m�F���X�|���X
        regClass(WEB3AioCashoutConfirmResponse.class);
        // �o���\���������N�G�X�g
        regClass(WEB3AioCashoutCompleteRequest.class);
        // �o���\���������X�|���X
        regClass(WEB3AioCashoutCompleteResponse.class);
        // �o���\�����̓��N�G�X�g
        regClass(WEB3AioCashoutInputRequest.class);
        // �o���\�����̓��X�|���X
        regClass(WEB3AioCashoutInputResponse.class);
        // �o���]�̓`�F�b�N���N�G�X�g
        regClass(WEB3AioCashoutTradingPowerCheckRequest.class);
        // �o���]�̓`�F�b�N���X�|���X
        regClass(WEB3AioCashoutTradingPowerCheckResponse.class);
        // �����v�����~���N�G�X�g
        regClass(WEB3AioCashinSettleCancelRequest.class);
        // �����v�����~���X�|���X
        regClass(WEB3AioCashinSettleCancelResponse.class);
        // ������t���N�G�X�g
        regClass(WEB3AioCashinAcceptRequest.class);
        // ������t���X�|���X
        regClass(WEB3AioCashinAcceptResponse.class);
        // �o���A�g���N�G�X�g
        regClass(WEB3AioOnPaymentCooperationRequest.class);
        // �o���A�g���X�|���X
        regClass(WEB3AioOnPaymentCooperationResponse.class);
        // �����A�g�ʒm���N�G�X�g
        regClass(WEB3AioCashinCooperationNotifyRequest.class);
        // �����A�g�ʒm���X�|���X
        regClass(WEB3AioCashinCooperationNotifyResponse.class);
        // �����A���m�F���N�G�X�g
        regClass(WEB3AioCashinNoticeConfirmRequest.class);
        // �����A���m�F���X�|���X
        regClass(WEB3AioCashinNoticeConfirmResponse.class);
        // �����A���������N�G�X�g
        regClass(WEB3AioCashinNoticeCompleteRequest.class);
        // �����A���������X�|���X
        regClass(WEB3AioCashinNoticeCompleteResponse.class);
        // �����A�����̓��N�G�X�g
        regClass(WEB3AioCashinNoticeInputRequest.class);
        // �����A�����̓��X�|���X
        regClass(WEB3AioCashinNoticeInputResponse.class);
        // ���o���ꗗ���N�G�X�g
        regClass(WEB3AioCashTransferListRequest.class);
        // ���o���ꗗ���X�|���X
        regClass(WEB3AioCashTransferListResponse.class);
        // �؋�������U�֊m�F���N�G�X�g
        regClass(WEB3AccTransChangeFromIfoDepositConfirmRequest.class);
        // �؋�������U�֊m�F���X�|���X
        regClass(WEB3AccTransChangeFromIfoDepositConfirmResponse.class);
        // �؋�������U�֊������N�G�X�g
        regClass(WEB3AccTransChangeFromIfoDepositCompleteRequest.class);
        // �؋�������U�֊������X�|���X
        regClass(WEB3AccTransChangeFromIfoDepositCompleteResponse.class);
        // �؋�������U�֓��̓��N�G�X�g
        regClass(WEB3AccTransChangeFromIfoDepositInputRequest.class);
        // �؋�������U�֓��̓��X�|���X
        regClass(WEB3AccTransChangeFromIfoDepositInputResponse.class);
        // �؋����ւ̐U�֊m�F���N�G�X�g
        regClass(WEB3AccTransChangeToIfoDepositConfirmRequest.class);
        // �؋����ւ̐U�֊m�F���X�|���X
        regClass(WEB3AccTransChangeToIfoDepositConfirmResponse.class);
        // �؋����ւ̐U�֊������N�G�X�g
        regClass(WEB3AccTransChangeToIfoDepositCompleteRequest.class);
        // �؋����ւ̐U�֊������X�|���X
        regClass(WEB3AccTransChangeToIfoDepositCompleteResponse.class);
        // �؋����ւ̐U�֓��̓��N�G�X�g
        regClass(WEB3AccTransChangeToIfoDepositInputRequest.class);
        // �؋����ւ̐U�֓��̓��X�|���X
        regClass(WEB3AccTransChangeToIfoDepositInputResponse.class);
        // �U�֐�����t���N�G�X�g
        regClass(WEB3AccTransChangeRequestAcceptRequest.class);
        // �U�֐�����t���X�|���X
        regClass(WEB3AccTransChangeRequestAcceptResponse.class);
        // �U�֐����ʒm���N�G�X�g
        regClass(WEB3AccTransChangeRequestNotifyRequest.class);
        // �U�֐����ʒm���X�|���X
        regClass(WEB3AccTransChangeRequestNotifyResponse.class);
        // ���̑������Ɖ���̓��N�G�X�g
        regClass(WEB3AdminAioOtherCountReferenceInputRequest.class);
        // ���̑������Ɖ���̓��X�|���X
        regClass(WEB3AdminAioOtherCountReferenceInputResponse.class);
        // ���̑������Ɖ�N�G�X�g
        regClass(WEB3AdminAioOtherCountReferenceRequest.class);
        // ���̑������Ɖ�X�|���X
        regClass(WEB3AdminAioOtherCountReferenceResponse.class);
        // ���ϘA�g���|�[�g�ꗗ���N�G�X�g
        regClass(WEB3AdminAioSettleReportListRequest.class);
        // ���ϘA�g���|�[�g�ꗗ���X�|���X
        regClass(WEB3AdminAioSettleReportListResponse.class);
        // ���ϘA�g���|�[�g���̓��N�G�X�g
        regClass(WEB3AdminAioSettleReportInputRequest.class);
        // ���ϘA�g���|�[�g���̓��X�|���X
        regClass(WEB3AdminAioSettleReportInputResponse.class);
        // �o���\���⍇���ꗗ���N�G�X�g
        regClass(WEB3AdminAioCashoutInqListRequest.class);
        // �o���\���⍇���ꗗ���X�|���X
        regClass(WEB3AdminAioCashoutInqListResponse.class);
        // �o���\���⍇������m�F���N�G�X�g
        regClass(WEB3AdminAioCashoutInqCancelConfirmRequest.class);
        // �o���\���⍇������m�F���X�|���X
        regClass(WEB3AdminAioCashoutInqCancelConfirmResponse.class);
        // �o���\���⍇������������N�G�X�g
        regClass(WEB3AdminAioCashoutInqCancelCompleteRequest.class);
        // �o���\���⍇������������X�|���X
        regClass(WEB3AdminAioCashoutInqCancelCompleteResponse.class);
        // �o���\���⍇���o���m�F���N�G�X�g
        regClass(WEB3AdminAioCashoutInqConfirmRequest.class);
        // �o���\���⍇���o���m�F���X�|���X
        regClass(WEB3AdminAioCashoutInqConfirmResponse.class);
        // �o���\���⍇���o���������N�G�X�g
        regClass(WEB3AdminAioCashoutInqCompleteRequest.class);
        // �o���\���⍇���o���������X�|���X
        regClass(WEB3AdminAioCashoutInqCompleteResponse.class);
        // �o���\���⍇�����̓��N�G�X�g
        regClass(WEB3AdminAioCashoutInqInputRequest.class);
        // �o���\���⍇�����̓��X�|���X
        regClass(WEB3AdminAioCashoutInqInputResponse.class);
        // �����A���m�F�ꗗ���N�G�X�g
        regClass(WEB3AdminAioCashinConfirmListRequest.class);
        // �����A���m�F�ꗗ���X�|���X
        regClass(WEB3AdminAioCashinConfirmListResponse.class);
        // �����A���m�F���̓��N�G�X�g
        regClass(WEB3AdminAioCashinConfirmInputRequest.class);
        // �����A���m�F���̓��X�|���X
        regClass(WEB3AdminAioCashinConfirmInputResponse.class);
        // SONAR���o���i�O�݁j���N�G�X�g
        regClass(WEB3AioSonarCashTransForeignRequest.class);
        // SONAR���o���i�O�݁j���X�|���X
        regClass(WEB3AioSonarCashTransForeignResponse.class);
        //�O�ݓ��o����t���N�G�X�g
        regClass(WEB3AioForeignCashTransAcceptRequest.class);
        //�O�ݓ��o����t���X�|���X
        regClass(WEB3AioForeignCashTransAcceptResponse.class);
        //�Z���z�ԍσ��N�G�X�g
        regClass(WEB3AioFinanceAmountRepayRequest.class);
        //�Z���z�ԍσ��X�|���X
        regClass(WEB3AioFinanceAmountRepayResponse.class);
        // ���o���A�g���N�G�X�g
        regClass(WEB3AioBondOnPaymentCooperationRequest.class);
        //���o���A�g���X�|���X
        regClass(WEB3AioBondOnPaymentCooperationResponse.class);

        // �،��U�ֈꗗ���N�G�X�g
        regClass(WEB3AioSecurityTransferListRequest.class);
        // �،��U�ֈꗗ���X�|���X
        regClass(WEB3AioSecurityTransferListResponse.class);
        // �،��U�֊m�F���N�G�X�g
        regClass(WEB3AioSecurityTransferConfirmRequest.class);
        // �،��U�֊m�F���X�|���X
        regClass(WEB3AioSecurityTransferConfirmResponse.class);
        // �،��U�֊������N�G�X�g
        regClass(WEB3AioSecurityTransferCompleteRequest.class);
        // �،��U�֊������X�|���X
        regClass(WEB3AioSecurityTransferCompleteResponse.class);
        // �،��U�֋������N�G�X�g
        regClass(WEB3AioSecurityTransferForceRequest.class);
        // �،��U�֋������X�|���X
        regClass(WEB3AioSecurityTransferForceResponse.class);
        // �،��U�֒ʒm���N�G�X�g
        regClass(WEB3AioSecurityTransferNotifyRequest.class);
        // �،��U�֒ʒm���X�|���X
        regClass(WEB3AioSecurityTransferNotifyResponse.class);
        // �،��U�֓��̓��N�G�X�g
        regClass(WEB3AioSecurityTransferInputRequest.class);
        // �،��U�֓��̓��X�|���X
        regClass(WEB3AioSecurityTransferInputResponse.class);

        //��������U�֋������N�G�X�g
        regClass(WEB3AioSpsecTransferForceRequest.class);

        //��������U�֋������X�|���X
        regClass(WEB3AioSpsecTransferForceResponse.class);

        // FX�����J�݊m�F���N�G�X�g
        regClass(WEB3FXAccOpenConfirmRequest.class);
        // FX�����J�݊m�F���X�|���X
        regClass(WEB3FXAccOpenConfirmResponse.class);
        // FX�����J�݊������N�G�X�g
        regClass(WEB3FXAccOpenCompleteRequest.class);
        // FX�����J�݊������X�|���X
        regClass(WEB3FXAccOpenCompleteResponse.class);
        // FX�����J�ݓ��̓��N�G�X�g
        regClass(WEB3FXAccOpenInputRequest.class);
        // FX�����J�ݓ��̓��X�|���X
        regClass(WEB3FXAccOpenInputResponse.class);
        // FX�����J�݈˗����N�G�X�g
        regClass(WEB3FXAccOpenAskingRequest.class);
        // FX�����J�݈˗����X�|���X
        regClass(WEB3FXAccOpenAskingResponse.class);
        // FX�����J�ݎ�����Ӄ��N�G�X�g
        regClass(WEB3FXAccOpenTradeAgreementRequest.class);
        // FX�����J�ݎ�����Ӄ��X�|���X
        regClass(WEB3FXAccOpenTradeAgreementResponse.class);
        // FX����U�֊m�F���N�G�X�g
        regClass(WEB3FXTransFromFXConfirmRequest.class);
        // FX����U�֊m�F���X�|���X
        regClass(WEB3FXTransFromFXConfirmResponse.class);
        // FX����U�֊������N�G�X�g
        regClass(WEB3FXTransFromFXCompleteRequest.class);
        // FX����U�֊������X�|���X
        regClass(WEB3FXTransFromFXCompleteResponse.class);
        // FX����U�֓��̓��N�G�X�g
        regClass(WEB3FXTransFromFXInputRequest.class);
        // FX����U�֓��̓��X�|���X
        regClass(WEB3FXTransFromFXInputResponse.class);
        // FX����U�ֈ˗����N�G�X�g
        regClass(WEB3FXTransFromFXAskingRequest.class);
        // FX����U�ֈ˗����X�|���X
        regClass(WEB3FXTransFromFXAskingResponse.class);
        // FX�ւ̐U�֊m�F���N�G�X�g
        regClass(WEB3FXTransToFXConfirmRequest.class);
        // FX�ւ̐U�֊m�F���X�|���X
        regClass(WEB3FXTransToFXConfirmResponse.class);
        // FX�ւ̐U�֊������N�G�X�g
        regClass(WEB3FXTransToFXCompleteRequest.class);
        // FX�ւ̐U�֊������X�|���X
        regClass(WEB3FXTransToFXCompleteResponse.class);
        // FX�ւ̐U�֓��̓��N�G�X�g
        regClass(WEB3FXTransToFXInputRequest.class);
        // FX�ւ̐U�֓��̓��X�|���X
        regClass(WEB3FXTransToFXInputResponse.class);
        // FX�ւ̐U�ֈ˗����N�G�X�g
        regClass(WEB3FXTransToFXAskingRequest.class);
        // FX�ւ̐U�ֈ˗����X�|���X
        regClass(WEB3FXTransToFXAskingResponse.class);
        // �V���O���T�C���I�����N�G�X�g
        regClass(WEB3FXSingleSignOnRequest.class);
        // �V���O���T�C���I�����X�|���X
        regClass(WEB3FXSingleSignOnResponse.class);
        // FX������Ӄ��N�G�X�g
        regClass(WEB3FXTradeAgreementRequest.class);
        // FX������Ӄ��X�|���X
        regClass(WEB3FXTradeAgreementResponse.class);
        // �Ǘ��ҁEFX�U�ֈꗗ���N�G�X�g
        regClass(WEB3AdminFXTransferListRequest.class);
        // �Ǘ��ҁEFX�U�ֈꗗ���X�|���X
        regClass(WEB3AdminFXTransferListResponse.class);
        // �Ǘ��ҁEFX�U�ֈꗗ�������̓��N�G�X�g
        regClass(WEB3AdminFXTransferListConditionRequest.class);
        // �Ǘ��ҁEFX�U�ֈꗗ�������̓��X�|���X
        regClass(WEB3AdminFXTransferListConditionResponse.class);
        // �Ǘ��ҁEFX�U�֎���m�F���N�G�X�g
        regClass(WEB3AdminFXTransferCancelConfirmRequest.class);
        // �Ǘ��ҁEFX�U�֎���m�F���X�|���X
        regClass(WEB3AdminFXTransferCancelConfirmResponse.class);
        // �Ǘ��ҁEFX�U�֎���������N�G�X�g
        regClass(WEB3AdminFXTransferCancelCompleteRequest.class);
        // �Ǘ��ҁEFX�U�֎���������X�|���X
        regClass(WEB3AdminFXTransferCancelCompleteResponse.class);
        // �Ǘ��ҁEFX�����J�݃X�e�[�^�X�X�V�m�F���N�G�X�g
        regClass(WEB3AdminFXAccOpenStatusUpdConfirmRequest.class);
        // �Ǘ��ҁEFX�����J�݃X�e�[�^�X�X�V�m�F���X�|���X
        regClass(WEB3AdminFXAccOpenStatusUpdConfirmResponse.class);
        // �Ǘ��ҁEFX�����J�݃X�e�[�^�X�X�V�������N�G�X�g
        regClass(WEB3AdminFXAccOpenStatusUpdCompleteRequest.class);
        // �Ǘ��ҁEFX�����J�݃X�e�[�^�X�X�V�������X�|���X
        regClass(WEB3AdminFXAccOpenStatusUpdCompleteResponse.class);
        // �Ǘ��ҁEFX�����J�݃X�e�[�^�X�X�V���̓��N�G�X�g
        regClass(WEB3AdminFXAccOpenStatusUpdInputRequest.class);
        // �Ǘ��ҁEFX�����J�݃X�e�[�^�X�X�V���̓��X�|���X
        regClass(WEB3AdminFXAccOpenStatusUpdInputResponse.class);
        // �Ǘ��ҁEFX�����J�ݐ\���ꗗ���N�G�X�g
        regClass(WEB3AdminFXAccOpenApplyListRequest.class);
        // �Ǘ��ҁEFX�����J�ݐ\���ꗗ���X�|���X
        regClass(WEB3AdminFXAccOpenApplyListResponse.class);
        // �Ǘ��ҁEFX�����J�ݐ\���ꗗ�������̓��N�G�X�g
        regClass(WEB3AdminFXAccOpenApplyListConditionRequest.class);
        // �Ǘ��ҁEFX�����J�ݐ\���ꗗ�������̓��X�|���X
        regClass(WEB3AdminFXAccOpenApplyListConditionResponse.class);
        // �Ǘ��ҁEFX�������ύX�m�F���N�G�X�g
        regClass(WEB3AdminFXAccInfoChangeConfirmRequest.class);
        // �Ǘ��ҁEFX�������ύX�m�F���X�|���X
        regClass(WEB3AdminFXAccInfoChangeConfirmResponse.class);
        // �Ǘ��ҁEFX�������ύX�������N�G�X�g
        regClass(WEB3AdminFXAccInfoChangeCompleteRequest.class);
        // �Ǘ��ҁEFX�������ύX�������X�|���X
        regClass(WEB3AdminFXAccInfoChangeCompleteResponse.class);
        // �Ǘ��ҁEFX�������ύX���̓��N�G�X�g
        regClass(WEB3AdminFXAccInfoChangeInputRequest.class);
        // �Ǘ��ҁEFX�������ύX���̓��X�|���X
        regClass(WEB3AdminFXAccInfoChangeInputResponse.class);
        // �Ǘ��ҁEFX������񌟍����N�G�X�g
        regClass(WEB3AdminFXAccInfoSearchRequest.class);
        // �Ǘ��ҁEFX������񌟍����X�|���X
        regClass(WEB3AdminFXAccInfoSearchResponse.class);
        // �Ǘ��ҁEFX������񌟍��������̓��N�G�X�g
        regClass(WEB3AdminFXAccInfoSearchConditionRequest.class);
        // �Ǘ��ҁEFX������񌟍��������̓��X�|���X
        regClass(WEB3AdminFXAccInfoSearchConditionResponse.class);

        //���o�ɗ������N�G�X�g
        regClass(WEB3AioInputOutputHistoryListRequest.class);
        //���o�ɗ������X�|���X
        regClass(WEB3AioInputOutputHistoryListResponse.class);


        //  �O�������ւ̐U�֊m�F���N�G�X�g
        regClass(WEB3FEqConTransferConfirmRequest.class);
        //  �O�������ւ̐U�֊m�F���X�|���X
        regClass(WEB3FEqConTransferConfirmResponse.class);
        //  �O�������ւ̐U�֊������N�G�X�g
        regClass(WEB3FEqConTransferCompleteRequest.class);
        //  �O�������ւ̐U�֊������X�|���X
        regClass(WEB3FEqConTransferCompleteResponse.class);
        //  �O�������ւ̐U�֎���m�F���N�G�X�g
        regClass(WEB3FEqConTransferCancelConfirmRequest.class);
        //  �O�������ւ̐U�֎���m�F���X�|���X
        regClass(WEB3FEqConTransferCancelConfirmResponse.class);
        //  �O�������ւ̐U�֎���������N�G�X�g
        regClass(WEB3FEqConTransferCancelCompleteRequest.class);
        //  �O�������ւ̐U�֎���������X�|���X
        regClass(WEB3FEqConTransferCancelCompleteResponse.class);
        //  �O�������ւ̐U�֎���I�����N�G�X�g
        regClass(WEB3FEqConTransferCancelSelectRequest.class);
        //  �O�������ւ̐U�֎���I�����X�|���X
        regClass(WEB3FEqConTransferCancelSelectResponse.class);
        //  �O�������ւ̐U�֓��̓��N�G�X�g
        regClass(WEB3FEqConTransferInputRequest.class);
        //  �O�������ւ̐U�֓��̓��X�|���X
        regClass(WEB3FEqConTransferInputResponse.class);
        //  �O�������J�݊m�F���N�G�X�g
        regClass(WEB3FEqConAccountOpenConfirmRequest.class);
        //  �O�������J�݊m�F���X�|���X
        regClass(WEB3FEqConAccountOpenConfirmResponse.class);
        //  �O�������J�݊������N�G�X�g
        regClass(WEB3FEqConAccountOpenCompleteRequest.class);
        //  �O�������J�݊������X�|���X
        regClass(WEB3FEqConAccountOpenCompleteResponse.class);
        //  �O�������J�݊Ǘ��X�e�[�^�X�X�V�m�F���N�G�X�g
        regClass(WEB3AdminFEqConAccountOpenMngStatusUpdConfirmRequest.class);
        //  �O�������J�݊Ǘ��X�e�[�^�X�X�V�m�F���X�|���X
        regClass(WEB3AdminFEqConAccountOpenMngStatusUpdConfirmResponse.class);
        //  �O�������J�݊Ǘ��X�e�[�^�X�X�V�������N�G�X�g
        regClass(WEB3AdminFEqConAccountOpenMngStatusUpdCompleteRequest.class);
        //  �O�������J�݊Ǘ��X�e�[�^�X�X�V�������X�|���X
        regClass(WEB3AdminFEqConAccountOpenMngStatusUpdCompleteResponse.class);
        //  �O�������J�݊Ǘ��X�e�[�^�X�X�V���̓��N�G�X�g
        regClass(WEB3AdminFEqConAccountOpenMngStatusUpdInputRequest.class);
        //  �O�������J�݊Ǘ��X�e�[�^�X�X�V���̓��X�|���X
        regClass(WEB3AdminFEqConAccountOpenMngStatusUpdInputResponse.class);
        //  �O�������J�݊Ǘ��ꗗ�_�E�����[�h���N�G�X�g
        regClass(WEB3AdminFEqConAccountOpenMngListDownloadRequest.class);
        //  �O�������J�݊Ǘ��ꗗ�_�E�����[�h���X�|���X
        regClass(WEB3AdminFEqConAccountOpenMngListDownloadResponse.class);
        //  �O�������J�݊Ǘ��ꗗ���N�G�X�g
        regClass(WEB3AdminFEqConAccountOpenMngListRequest.class);
        //  �O�������J�݊Ǘ��ꗗ���X�|���X
        regClass(WEB3AdminFEqConAccountOpenMngListResponse.class);
        //  �O�������J�݊Ǘ��������̓��N�G�X�g
        regClass(WEB3AdminFEqConAccountOpenMngSrcInputRequest.class);
        //  �O�������J�݊Ǘ��������̓��X�|���X
        regClass(WEB3AdminFEqConAccountOpenMngSrcInputResponse.class);
        //  �O�������J�ݎ�����Ӄ��N�G�X�g
        regClass(WEB3FEqConAccountOpenAgreementRequest.class);
        //  �O�������J�ݎ�����Ӄ��X�|���X
        regClass(WEB3FEqConAccountOpenAgreementResponse.class);
        //  �O�������J�ݏ󋵕ύX�m�F���N�G�X�g
        regClass(WEB3AdminFEqConAccountStateChangeConfirmRequest.class);
        //  �O�������J�ݏ󋵕ύX�m�F���X�|���X
        regClass(WEB3AdminFEqConAccountStateChangeConfirmResponse.class);
        //  �O�������J�ݏ󋵕ύX�������N�G�X�g
        regClass(WEB3AdminFEqConAccountStateChangeCompleteRequest.class);
        //  �O�������J�ݏ󋵕ύX�������X�|���X
        regClass(WEB3AdminFEqConAccountStateChangeCompleteResponse.class);
        //  �O�������J�ݓ��̓��N�G�X�g
        regClass(WEB3FEqConAccountOpenInputRequest.class);
        //  �O�������J�ݓ��̓��X�|���X
        regClass(WEB3FEqConAccountOpenInputResponse.class);
        //  �O��������񌟍����N�G�X�g
        regClass(WEB3AdminFEqConAccountInfoSearchRequest.class);
        //  �O��������񌟍����X�|���X
        regClass(WEB3AdminFEqConAccountInfoSearchResponse.class);
        //  �O��������񌟍��������̓��N�G�X�g
        regClass(WEB3AdminFEqConAccountInfoSearchInputRequest.class);
        //  �O��������񌟍��������̓��X�|���X
        regClass(WEB3AdminFEqConAccountInfoSearchInputResponse.class);
        //  �O���U�ֈꗗ���N�G�X�g
        regClass(WEB3AdminFEqConTransferListRequest.class);
        //  �O���U�ֈꗗ���X�|���X
        regClass(WEB3AdminFEqConTransferListResponse.class);
        //  �O���U�ֈꗗ�������̓��N�G�X�g
        regClass(WEB3AdminFEqConTransferListInputRequest.class);
        //  �O���U�ֈꗗ�������̓��X�|���X
        regClass(WEB3AdminFEqConTransferListInputResponse.class);
        //  �o�ɒʒm���N�G�X�g
        regClass(WEB3AioOutputNotifyRequest.class);
        //  �o�ɒʒm���X�|���X
        regClass(WEB3AioOutputNotifyResponse.class);

        //�����ʒm�������N�G�X�g
        regClass(WEB3AdminAioCashinNoticeSearchRequest.class);
        //�����ʒm�������X�|���X
        regClass(WEB3AdminAioCashinNoticeSearchResponse.class);
        //�����ʒm�����m�F���N�G�X�g
        regClass(WEB3AdminAioCashinNoticeChangeConfirmRequest.class);
        //�����ʒm�����m�F���X�|���X
        regClass(WEB3AdminAioCashinNoticeChangeConfirmResponse.class);
        //�����ʒm�����������N�G�X�g
        regClass(WEB3AdminAioCashinNoticeChangeCompleteRequest.class);
        //�����ʒm�����������X�|���X
        regClass(WEB3AdminAioCashinNoticeChangeCompleteResponse.class);
        //�����ʒm�������ʃ��N�G�X�g
        regClass(WEB3AdminAioCashinChangeCommonRequest.class);
        // �����ʒm�������͉�ʃ��N�G�X�g
        regClass(WEB3AdminAioCashinNoticeChangeInputRequest.class);
        //�����ʒm�������͉�ʃ��X�|���X
        regClass(WEB3AdminAioCashinNoticeChangeInputResponse.class);

        //�o�[�`������������UL���̓��N�G�X�g
        regClass(WEB3AdminAioVirtualAccCashinULInputRequest.class);
        //�o�[�`������������UL���̓��X�|���X
        regClass(WEB3AdminAioVirtualAccCashinULInputResponse.class);
        //�o�[�`������������UL�m�F���N�G�X�g
        regClass(WEB3AdminAioVirtualAccCashinULConfirmRequest.class);
        //�o�[�`������������UL�m�F���X�|���X
        regClass(WEB3AdminAioVirtualAccCashinULConfirmResponse.class);
        //�o�[�`������������UL�������N�G�X�g
        regClass(WEB3AdminAioVirtualAccCashinULCompleteRequest.class);
        //�o�[�`������������UL�������X�|���X
        regClass(WEB3AdminAioVirtualAccCashinULCompleteResponse.class);
        //�o�[�`������������UL���~���N�G�X�g
        regClass(WEB3AdminAioVirtualAccCashinULCancelRequest.class);
        //�o�[�`������������UL���~���X�|���X
        regClass(WEB3AdminAioVirtualAccCashinULCancelResponse.class);

        //�o���\���⍇���_�E�����[�h���N�G�X�g
        regClass(WEB3AdminAioCashoutInqDownloadRequest.class);
        //�o���\���⍇���_�E�����[�h���X�|���X
        regClass(WEB3AdminAioCashoutInqDownloadResponse.class);

        //���o���ꗗ�_�E�����[�h���N�G�X�g
        regClass(WEB3AdminAioCashTransferListDownloadRequest.class);
        //���o���ꗗ�_�E�����[�h���X�|���X
        regClass(WEB3AdminAioCashTransferListDownloadResponse.class);
        //���o���ꗗ���̓��N�G�X�g
        regClass(WEB3AdminAioCashTransferListInputRequest.class);
        //���o���ꗗ���̓��X�|���X
        regClass(WEB3AdminAioCashTransferListInputResponse.class);
        //���o���ꗗ���ʃ��N�G�X�g
        regClass(WEB3AdminAioCashTransferListRequest.class);
        //���o���ꗗ���ʃ��X�|���X
        regClass(WEB3AdminAioCashTransferListResponse.class);
        //���o���ꗗ���ʃ��N�G�X�g
        regClass(WEB3AdminAioCashTransferListRequest.class);
        
        // �،��S�ۃ��[���o���S�����ꗗ���N�G�X�g
        regClass(WEB3AdminSLRestraintMoneyListRequest.class);
        // �،��S�ۃ��[���o���S�����ꗗ���X�|���X
        regClass(WEB3AdminSLRestraintMoneyListResponse.class);
        // �،��S�ۃ��[���o����~�����m�F���N�G�X�g
        regClass(WEB3AdminSLCashOutStopReleaseConfirmRequest.class);
        // �،��S�ۃ��[���o����~�����m�F���X�|���X
        regClass(WEB3AdminSLCashOutStopReleaseConfirmResponse.class);
        // �،��S�ۃ��[���o����~�����������N�G�X�g
        regClass(WEB3AdminSLCashOutStopReleaseCompleteRequest.class);
        // �،��S�ۃ��[���o����~�����������X�|���X
        regClass(WEB3AdminSLCashOutStopReleaseCompleteResponse.class);

        //�،��S�ۃ��[���Ή�
        //�Ǘ��ҏ،��S�ۃ��[���\���ڋq�ꗗ���N�G�X�g
        regClass(WEB3AdminSLAccountOpenApplyListRequest.class);
        //�Ǘ��ҏ،��S�ۃ��[���\���ڋq�ꗗ���X�|���X
        regClass(WEB3AdminSLAccountOpenApplyListResponse.class);

        //SL�����J�ݐ\�����N�G�X�g
        regClass(WEB3SLAccountOpenApplyRequest.class);
        //SL�����J�ݐ\�����X�|���X
        regClass(WEB3SLAccountOpenApplyResponse.class);

        //SL�����J�݃��N�G�X�g
        regClass(WEB3SLAccountOpenRequest.class);
        //SL�����J�݃��X�|���X
        regClass(WEB3SLAccountOpenResponse.class);

        //�،��S�ۃ��[���ԍϐ\���������N�G�X�g
        regClass(WEB3SLRepayApplyCompleteRequest.class);
        //�،��S�ۃ��[���ԍϐ\���������X�|���X
        regClass(WEB3SLRepayApplyCompleteResponse.class);

        //�،��S�ۃ��[���ԍϐ\���m�F���N�G�X�g
        regClass(WEB3SLRepayApplyConfirmRequest.class);
        //�،��S�ۃ��[���ԍϐ\���m�F���X�|���X
        regClass(WEB3SLRepayApplyConfirmResponse.class);

        //�،��S�ۃ��[���ԍϐ\�����̓��N�G�X�g
        regClass(WEB3SLRepayApplyInputRequest.class);
        //�،��S�ۃ��[���ԍϐ\�����̓��X�|���X
        regClass(WEB3SLRepayApplyInputResponse.class);

        //�،��S�ۃ��[���ԍώ�����ʃ��N�G�X�g
        regClass(WEB3SLRepayCancelCommonRequest.class);
        //�،��S�ۃ��[���ԍώ�����ʃ��X�|���X
        regClass(WEB3SLRepayCancelCommonResponse.class);

        //�،��S�ۃ��[���ԍώ���������N�G�X�g
        regClass(WEB3SLRepayCancelCompleteRequest.class);
        //�،��S�ۃ��[���ԍώ���������X�|���X
        regClass(WEB3SLRepayCancelCompleteResponse.class);

        //�،��S�ۃ��[���ԍώ���m�F���N�G�X�g
        regClass(WEB3SLRepayCancelConfirmRequest.class);
        //�،��S�ۃ��[���ԍώ���m�F���X�|���X
        regClass(WEB3SLRepayCancelConfirmResponse.class);

        //�،��S�ۃ��[���ԍψꗗ���N�G�X�g
        regClass(WEB3SLRepayCancelListRequest.class);
        //�،��S�ۃ��[���ԍψꗗ���X�|���X
        regClass(WEB3SLRepayCancelListResponse.class);

        //�،��S�ۃ��[���ԍϐ\�����ʃ��N�G�X�g
        regClass(WEB3SLRepayApplyCommonRequest.class);
        //�،��S�ۃ��[���ԍϐ\�����ʃ��X�|���X
        regClass(WEB3SLRepayApplyCommonResponse.class);
        
        //�S�ۖ����o�^����m�F���N�G�X�g
        regClass(WEB3AdminSLProductCancelConfirmRequest.class);
        //�S�ۖ����o�^����m�F���X�|���X
        regClass(WEB3AdminSLProductCancelConfirmResponse.class);

        //�S�ۖ����o�^����������N�G�X�g
        regClass(WEB3AdminSLProductCancelCompleteRequest.class);
        //�S�ۖ����o�^����������X�|���X
        regClass(WEB3AdminSLProductCancelCompleteResponse.class);

        //�S�ۖ����o�^���̓��N�G�X�g
        regClass(WEB3AdminSLProductRegistInputRequest.class);
        //�S�ۖ����o�^���̓��X�|���X
        regClass(WEB3AdminSLProductRegistInputResponse.class);
        //�S�ۖ����o�^�m�F���N�G�X�g
        regClass(WEB3AdminSLProductRegistConfirmRequest.class);
        //�S�ۖ����o�^�m�F���X�|���X
        regClass(WEB3AdminSLProductRegistConfirmResponse.class);
        //�S�ۖ����o�^�������N�G�X�g
        regClass(WEB3AdminSLProductRegistCompleteRequest.class);
        //�S�ۖ����o�^�������X�|���X
        regClass(WEB3AdminSLProductRegistCompleteResponse.class);
        
        //�S�ۖ����o�^�ύX���̓��N�G�X�g
        regClass(WEB3AdminSLProductChangeInputRequest.class);
        //�S�ۖ����o�^�ύX���̓��X�|���X
        regClass(WEB3AdminSLProductChangeInputResponse.class);
        //�S�ۖ����o�^�ύX�m�F���N�G�X�g
        regClass(WEB3AdminSLProductChangeConfirmRequest.class);
        //�S�ۖ����o�^�ύX�m�F���X�|���X
        regClass(WEB3AdminSLProductChangeConfirmResponse.class);
        //�S�ۖ����o�^�ύX�������N�G�X�g
        regClass(WEB3AdminSLProductChangeCompleteRequest.class);
        //�S�ۖ����o�^�ύX�������X�|���X
        regClass(WEB3AdminSLProductChangeCompleteResponse.class);
        
        //�S�ۖ����o�^�ꗗ���N�G�X�g
        regClass(WEB3AdminSLProductRegiListRequest.class);
        //�S�ۖ����o�^�ꗗ���X�|���X
        regClass(WEB3AdminSLProductRegiListResponse.class);

        //FX����U�֊������N�G�X�g�iSOAP�ڑ��j
        regClass(WEB3FXTransFromFXCompleteSoapRequest.class);
        //FX����U�֊������X�|���X�iSOAP�ڑ��j
        regClass(WEB3FXTransFromFXCompleteSoapResponse.class);

        //FX�����J�݊������N�G�X�g�iSOAP�ڑ��j
        regClass(WEB3FXAccOpenCompleteSoapRequest.class);
        //FX�����J�݊������X�|���X�iSOAP�ڑ��j
        regClass(WEB3FXAccOpenCompleteSoapResponse.class);
    }

    /**
     * Handler �̓o�^����
     * @@throws Exception
     */
    private static void plugHandlers() throws Exception
    {
        //Handler �̓o�^���� ----------------------

        //�Ǘ���FX�����J�݊Ǘ��n���h��
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AdminFXAccOpenApplyDownloadRequest.class,
            WEB3AdminFXAccOpenManagementHandler.class,
        "getDownloadFile");

        //�Ǘ���FX�����J�݃A�b�v���[�h�n���h��
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AdminFXAccOpenUploadInputRequest.class,
            WEB3AdminFXAccOpenUploadHandler.class,
        "getUploadScreen");

        //�Ǘ���FX�����J�݃A�b�v���[�h�n���h��
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AdminFXAccOpenUploadConfirmRequest.class,
            WEB3AdminFXAccOpenUploadHandler.class,
        "validateUploadFile");

        //�Ǘ���FX�����J�݃A�b�v���[�h�n���h��
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AdminFXAccOpenUploadCompleteRequest.class,
            WEB3AdminFXAccOpenUploadHandler.class,
        "submitUpload");

        //�Ǘ���FX�����J�݃A�b�v���[�h�n���h��
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AdminFXAccOpenUploadCancelRequest.class,
            WEB3AdminFXAccOpenUploadHandler.class,
        "undoUpload");

        //FX�U�֒����A�b�v���[�h�n���h��
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AdminFXTransferUploadInputRequest.class,
            WEB3AdminFXTransferOrderUploadHandler.class,
            "getUploadScreen");

        //FX�U�֒����A�b�v���[�h�n���h��
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AdminFXTransferUploadConfirmRequest.class,
            WEB3AdminFXTransferOrderUploadHandler.class,
            "validateUploadFile");

        //FX�U�֒����A�b�v���[�h�n���h��
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AdminFXTransferUploadCompleteRequest.class,
            WEB3AdminFXTransferOrderUploadHandler.class,
            "fxTransferOrderUpload");

        //FX�U�֒����A�b�v���[�h�n���h��
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AdminFXTransferUploadCancelRequest.class,
            WEB3AdminFXTransferOrderUploadHandler.class,
            "undoUploadStop");

        // �؋�������U�փn���h��
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
        // �؋�������U�֓��̓n���h��
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AccTransChangeFromIfoDepositInputRequest.class,
                WEB3AccTransChangeFromIfoDepositInputHandler.class,
            "handleAccTransChangeFromIfoDepositInputRequest");
        // �U�֐�����t�n���h��
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AccTransChangeRequestAcceptRequest.class,
                WEB3AccTransChangeRequestAcceptHandler.class,
            "handleAccTransChangeRequestAcceptRequest");
        // �U�֐����ʒm�n���h��
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AccTransChangeRequestNotifyRequest.class,
                WEB3AccTransChangeRequestNotifyHandler.class,
            "handleAccTransChangeRequestNotifyRequest");
        // �؋����ւ̐U�փn���h��
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
        // �؋����ւ̐U�֓��̓n���h��
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AccTransChangeToIfoDepositInputRequest.class,
                WEB3AccTransChangeToIfoDepositInputHandler.class,
            "handleAccTransChangeToIfoDepositInputRequest");
        // �o���A�g�n���h��
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AioOnPaymentCooperationRequest.class,
                WEB3AioOnPaymentCooperationHandler.class,
            "onPaymentCooperationRequest");

        // �����A�g�n���h��
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AioCashinCooperationNotifyRequest.class,
            WEB3AioCashinCooperationServiceHandler.class,
            "cashinCooperationRequest");

        // �����A���m�F���̓n���h��
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AdminAioCashinConfirmInputRequest.class,
                WEB3AdminAioCashinConfirmInputHandler.class,
                "handleCashinConfirmInputRequest");
        //  �����A���m�F�n���h��
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AdminAioCashinConfirmListRequest.class,
                WEB3AdminAioCashinNoticeConfirmHandler.class,
                "handleCashinConfirmListRequest");
        //  �o���\���⍇���n���h��
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
        //  �o���\���⍇�����̓n���h��
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AdminAioCashoutInqInputRequest.class,
                WEB3AdminAioCashoutInqInputHandler.class,
            "hadleCashoutInqRequest");
        // ���ϘA�g���|�[�g�n���h��
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AdminAioSettleReportListRequest.class,
                WEB3AdminAioSettleReportHandler.class,
            "handleReportIndicationRequest");
        // ���ϘA�g���|�[�g���̓n���h��
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AdminAioSettleReportInputRequest.class,
                WEB3AdminAioSettleReportInputHandler.class,
            "handleReportInputRequest");
        // ���̑������Ɖ�n���h��
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
        // ������t�n���h��
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AioCashinAcceptRequest.class,
                WEB3AioCashinAcceptHandler.class,
            "handleAioCashinAcceptRequest");
        //  �I�����C���������̓n���h��
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AioCashinInputRequest.class,
                WEB3AioCashinInputHandler.class,
            "handleCashinInputRequest");
        // �����A���n���h��
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
        // �����A�����̓n���h��
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AioCashinNoticeInputRequest.class,
                WEB3AioCashinNoticeInputHandler.class,
            "handleCashinNoticeInputRequest");
        // �I�����C�������I���n���h��
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AioCashinSelectRequest.class,
                WEB3AioCashinSelectHandler.class,
            "handleCashinSelectRequest");
        // �����v�����~�n���h��
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AioCashinSettleCancelRequest.class,
                WEB3AioCashinSettleCancelHandler.class,
            "handleCashinSettleCancelRequest");
        // ���ϊ����n���h��
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AioCashinSettleCompleteRequest.class,
                WEB3AioCashinSettleCompleteHandler.class,
            "handleCashinSettleCompleteRequest");
        // ���σG���[�n���h��
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AioCashinSettleErrorRequest.class,
                WEB3AioCashinSettleErrorHandler.class,
            "handleCashinSettleErrorRequest");
        //  ���ψ˗��n���h��
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
        // �o����t�n���h��
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AioCashoutAcceptRequest.class,
                WEB3AioCashoutAcceptHandler.class,
            "handleCashoutAcceptRequest");
        // �o������n���h��
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
        // �o������ꗗ�n���h��
       regHandler(
               WEB3AioAppPlugin.class,
               WEB3AioCashoutCancelListRequest.class,
               WEB3AioCashoutCancelListHandler.class,
            "handleAioCashoutCancelListRequest");
        // �o���\���n���h��
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
        // �o���\�����̓n���h��
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AioCashoutInputRequest.class,
                WEB3AioCashoutInputHandler.class,
            "handleCashoutInputHandler");
        // �o���]�̓`�F�b�N�n���h��
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AioCashoutTradingPowerCheckRequest.class,
                WEB3AioCashoutTradingPowerHandler.class,
            "handleCashoutRemainingPowerCheckRequest");
        // ���o���ꗗ�n���h��
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AioCashTransferListRequest.class,
                WEB3AioCashTransferListHandler.class,
            "handleCashTransferListRequest");
        // SONAR���o���n���h��
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AioSonarCashTransRequest.class,
                WEB3AioSonarCashTransHandler.class,
            "handleAioCashTransRequest");

        //�Z���z�ԍσn���h��
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AioFinanceAmountRepayRequest.class,
                WEB3AioFinanceAmountRepayHandler.class,
            "financeAmountRepayRequest");

        // �،��U�փn���h��
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

        //�،��U�֋����n���h��
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AioSecurityTransferForceRequest.class,
                WEB3AioSecurityTransferForceHandler.class,
                "handleSecurityTransferForceRequest");

        // �،��U�֒ʒm�n���h��
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AioSecurityTransferNotifyRequest.class,
                WEB3AioSecurityTransferNotifyHandler.class,
                "handleSecurityTransferNotifyRequest");

        // �،��U�֓��̓n���h��
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

        //��������U�֋����n���h��
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AioSpsecTransferForceRequest.class,
            WEB3AioSpsecTransferForceHandler.class,
            "handleSpsecTransferForceRequest");

        // SONAR���o���i�O�݁j�n���h��
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AioSonarCashTransForeignRequest.class,
            WEB3AioSonarCashTransForeignHandler.class,
            "sonarCashTransForeignRequest");

        //�O�ݓ��o����t�n���h��
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AioForeignCashTransAcceptRequest.class,
            WEB3AioForeignCashTransAcceptHandler.class,
            "foreignCashTransAcceptRequest");

        // ���o���A�g�n���h��
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AioBondOnPaymentCooperationRequest.class,
            WEB3AioBondOnPaymentCooperationHandler.class,
            "bondOnPaymentCooperationRequest");
        // ---�ב֕ۏ؋��T�[�r�X---------

        // FX�����J�݊m�F���N�G�X�g
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
        // FX�����J�݊������N�G�X�g
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3FXAccOpenCompleteRequest.class,
                WEB3FXAccOpenHandler.class,
            "accountOpenComplete");
        // FX�����J�ݓ��̓��N�G�X�g
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3FXAccOpenInputRequest.class,
                WEB3FXAccOpenInputHandler.class,
            "displayInputScreen");
        // FX�����J�݈˗����N�G�X�g
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3FXAccOpenAskingRequest.class,
                WEB3FXAccOpenHandler.class,
            "accountOpenAsking");
        // FX�����J�ݎ�����Ӄ��N�G�X�g
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3FXAccOpenTradeAgreementRequest.class,
                WEB3FXAccOpenInputHandler.class,
            "displayTradeAgreementScreen");
        // FX����U�֊m�F���N�G�X�g
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3FXTransFromFXConfirmRequest.class,
                WEB3FXTransFromFXHandler.class,
            "orderConfirm");
        //���������iSOAP�ڑ��j
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3FXTransFromFXCompleteSoapRequest.class,
            WEB3FXTransFromFXHandler.class,
            "orderCompleteSoap");
        // FX����U�֊������N�G�X�g
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3FXTransFromFXCompleteRequest.class,
                WEB3FXTransFromFXHandler.class,
            "orderComplete");
        // FX����U�֓��̓��N�G�X�g
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3FXTransFromFXInputRequest.class,
                WEB3FXTransFromFXInputHandler.class,
            "displayInputScreen");
        // �V���O���T�C���I�����N�G�X�g
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3FXSingleSignOnRequest.class,
                WEB3FXSingleSignOnHandler.class,
            "displayExterFxSystem");
        // FX������Ӄ��N�G�X�g
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3FXTradeAgreementRequest.class,
                WEB3FXSingleSignOnHandler.class,
            "displayTradeAgreementScreen");
        // FX����U�ֈ˗����N�G�X�g
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3FXTransFromFXAskingRequest.class,
                WEB3FXTransFromFXHandler.class,
            "orderAsking");
        // FX�ւ̐U�֊m�F���N�G�X�g
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3FXTransToFXConfirmRequest.class,
                WEB3FXTransToFXHandler.class,
            "orderConfirm");
        // FX�ւ̐U�֊������N�G�X�g
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3FXTransToFXCompleteRequest.class,
                WEB3FXTransToFXHandler.class,
            "orderComplete");
        // FX�ւ̐U�֊������N�G�X�g�iSOAP�ڑ��j
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3FXTransToFXCompleteSoapRequest.class,
                WEB3FXTransToFXHandler.class,
            "orderComplete");
        // FX�ւ̐U�֓��̓��N�G�X�g
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3FXTransToFXInputRequest.class,
                WEB3FXTransToFXInputHandler.class,
            "displayInputScreen");
        // FX�ւ̐U�ֈ˗����N�G�X�g
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3FXTransToFXAskingRequest.class,
                WEB3FXTransToFXHandler.class,
            "orderAsking");
        // �Ǘ��ҁEFX�U�ֈꗗ���N�G�X�g
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AdminFXTransferListRequest.class,
                WEB3AdminFXTransferManagementHandler.class,
            "listScreenDisplay");
        // �Ǘ��ҁEFX�U�ֈꗗ�������̓��N�G�X�g
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AdminFXTransferListConditionRequest.class,
                WEB3AdminFXTransferManagementHandler.class,
            "condInputScreenDisplay");
        // �Ǘ��ҁEFX�U�֎���m�F���N�G�X�g
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AdminFXTransferCancelConfirmRequest.class,
                WEB3AdminFXTransferManagementHandler.class,
            "cancelConfirm");
        // �Ǘ��ҁEFX�U�֎���������N�G�X�g
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AdminFXTransferCancelCompleteRequest.class,
                WEB3AdminFXTransferManagementHandler.class,
            "cancelComplete");
        // �Ǘ��ҁEFX�����J�݃X�e�[�^�X�X�V�m�F���N�G�X�g
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AdminFXAccOpenStatusUpdConfirmRequest.class,
                WEB3AdminFXAccOpenManagementHandler.class,
            "confirmStatusUpd");
        // �Ǘ��ҁEFX�����J�݃X�e�[�^�X�X�V�������N�G�X�g
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AdminFXAccOpenStatusUpdCompleteRequest.class,
                WEB3AdminFXAccOpenManagementHandler.class,
            "completeStatusUpd");
        // �Ǘ��ҁEFX�����J�݃X�e�[�^�X�X�V���̓��N�G�X�g
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AdminFXAccOpenStatusUpdInputRequest.class,
                WEB3AdminFXAccOpenManagementHandler.class,
            "getStatusUpdInputScreen");
        // �Ǘ��ҁEFX�����J�ݐ\���ꗗ���N�G�X�g
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AdminFXAccOpenApplyListRequest.class,
                WEB3AdminFXAccOpenManagementHandler.class,
            "getListScreen");
        // �Ǘ��ҁEFX�����J�ݐ\���ꗗ�������̓��N�G�X�g
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AdminFXAccOpenApplyListConditionRequest.class,
                WEB3AdminFXAccOpenManagementHandler.class,
            "getCondInputScreen");
        // �Ǘ��ҁEFX�������ύX�m�F���N�G�X�g
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AdminFXAccInfoChangeConfirmRequest.class,
                WEB3AdminFXAccManagementHandler.class,
            "confirmChanger");
        // �Ǘ��ҁEFX�������ύX�������N�G�X�g
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AdminFXAccInfoChangeCompleteRequest.class,
                WEB3AdminFXAccManagementHandler.class,
            "completeChange");
        // �Ǘ��ҁEFX�������ύX���̓��N�G�X�g
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AdminFXAccInfoChangeInputRequest.class,
                WEB3AdminFXAccManagementHandler.class,
            "getUpdInputScreen");
        // �Ǘ��ҁEFX������񌟍����N�G�X�g
        regHandler(
                WEB3AioAppPlugin.class,
                WEB3AdminFXAccInfoSearchRequest.class,
                WEB3AdminFXAccManagementHandler.class,
            "getQueryResult");
        // �Ǘ��ҁEFX������񌟍��������̓��N�G�X�g
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AdminFXAccInfoSearchConditionRequest.class,
            WEB3AdminFXAccManagementHandler.class,
            "getCondInputScreen");

        //���o�ɗ���
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AioInputOutputHistoryListRequest.class,
            WEB3AioInputOutputHistoryHandler.class,
            "handleHistoryRequest");

        // �O�������ւ̐U�փn���h��
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
        // �O�������ւ̐U�֎���n���h��
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
        // �O�������J�݃n���h��
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
        // �O�������J�݊Ǘ��n���h��
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

        // �O�������Ǘ��n���h��
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

        // �O���U�֊Ǘ��n���h��
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

        // �o�ɒʒm�n���h��
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AioOutputNotifyRequest.class,
            WEB3AioOutputNotifyHandler.class,
            "outputNotifyRequest");

        // �����ʒm�m�F�ď����n���h��
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

        //�o�[�`������������UL�n���h��
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

        //�Ǘ��ғ��o���ꗗ�n���h��
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

        // �،��S�ۃ��[���o����~�����n���h��
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

        //�،��S�ۃ��[���Ή�
        //�،��S�ۃ��[���ԍϐ\���n���h��
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

        //�،��S�ۃ��[���ԍϐ\�����̓n���h��
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3SLRepayApplyInputRequest.class,
            WEB3AioSLRepayApplyInputHandler.class,
            "slRepayInputRequest");

        //�،��S�ۃ��[���ԍώ���n���h��
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

        //�،��S�ۃ��[���ԍψꗗ�n���h��
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3SLRepayCancelListRequest.class,
            WEB3AioSLRepayListHandler.class,
            "repayCancelListRequest");

        //SL�����J�݃n���h��
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

        //�S�ۃ��[���\���󋵈ꗗ�n���h��
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AdminSLAccountOpenApplyListRequest.class,
            WEB3AioSecuredLoanOfferStateListHandler.class,
            "getListScreen");

        //�S�ۖ�������n���h��
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
        
        //�S�ۖ����o�^�n���h��
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

        //�S�ۖ����ύX�n���h��
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

        //�S�ۓo�^�����Ɖ�n���h��
        regHandler(
            WEB3AioAppPlugin.class,
            WEB3AdminSLProductRegiListRequest.class,
            WEB3AdminAioSLRegistProductReferenceHandler.class,
            "getSLRegiProductList");
    }

    /**
     * RAC-CTX�C���^�Z�v�^��ݒ肷��
     * @@throws Exception
     */
    private static void plugRacCtxInterceptors() throws Exception
    {
        //------------------------------------
        // RAC-CTX�C���^�Z�v�^��ݒ肷��
        //------------------------------------

        // �،��U�֒ʒm�ꌏ
        Services.addInterceptor(
            WEB3AioSecurityTransferNotifyUnitService.class,
            new WEB3AioDescendRacCtxInterceptor());

        // �،��U�֋����ꌏ
        Services.addInterceptor(
            WEB3AioSecurityTransferForceUnitService.class,
            new WEB3AioDescendRacCtxInterceptor());

        // ��������U�֋����ꌏ
        Services.addInterceptor(
            WEB3AioSpsecTransferForceUnitService.class,
            new WEB3AioDescendRacCtxInterceptor());
        // ���u�� U01362�̎b��Ή� end

        // ���u�� U01504�̎b��Ή� start
        // �U�֐����ʒm�ꌏ
        Services.addInterceptor(
            WEB3AccTransChangeRequestNotifyUnitService.class,
            new WEB3AioDescendRacCtxInterceptor());

        // �U�֎�t�ꌏ
        Services.addInterceptor(
            WEB3AccTransChangeAcceptUnitService.class,
            new WEB3AioDescendRacCtxInterceptor());

        // �U�֊����ꌏ
        Services.addInterceptor(
            WEB3AccTransChangeCompleteUnitService.class,
            new WEB3AioDescendRacCtxInterceptor());

        // ���o����t�ꌏ
        Services.addInterceptor(
            WEB3AioCashTransferAcceptUnitService.class,
            new WEB3AioDescendRacCtxInterceptor());

        // ���o�������ꌏ
        Services.addInterceptor(
            WEB3AioCashTransferCompleteUnitService.class,
            new WEB3AioDescendRacCtxInterceptor());

        // SONAR���o���ꌏ
        Services.addInterceptor(
            WEB3AioSonarCashTransUnitService.class,
            new WEB3AioDescendRacCtxInterceptor());

        // �o���]�̓`�F�b�N�ꌏ
        Services.addInterceptor(
            WEB3AioCashoutTradingPowerService.class,
            new WEB3AioDescendRacCtxInterceptor());

        // �o�ɒʒm�ꌏ
        Services.addInterceptor(
            WEB3AioOutputNotifyUnitService.class,
            new WEB3AioDescendRacCtxInterceptor());

        // SONAR���o���i�O�݁j
        Services.addInterceptor(
            WEB3AioSonarCashTransForeignUnitService.class,
            new WEB3AioDescendRacCtxInterceptor());

        // �Z���z�ԍψꌏ
        Services.addInterceptor(
                WEB3AioFinanceAmountRepayUnitService.class,
            new WEB3AioDescendRacCtxInterceptor());

        // �O�ݓ��o����t
        Services.addInterceptor(
            WEB3AioForeignCashTransAcceptService.class,
            new WEB3AioDescendRacCtxInterceptor());

        // SONAR���o���i�O�݁j
        Services.addInterceptor(
            WEB3AioBondOnPaymentCooperationUnitService.class,
            new WEB3AioDescendRacCtxInterceptor());

    }
}
@
