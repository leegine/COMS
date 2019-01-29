head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.12.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �]�͊Ǘ��҃v���O�C���̃v���O�C���N���X(WEB3AdminTPPlugin.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/01/06 �x�� �a��(FLJ) �V�K�쐬
Revision History : 2007/07/28 ��іQ(���u) ���f��No.002,003
Revision History : 2007/10/15 ������(���u) ���������Ǘ����f��No.027
Revision History : 2007/10/15 ������(���u) ���������Ǘ����f��No.040
*/
package webbroker3.tradingpoweradmin;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;

import webbroker3.common.WEB3LogSysTimeInterceptor;
import webbroker3.tradingpoweradmin.data.WEB3AdminTPAccountDatabaseExtensions;
import webbroker3.tradingpoweradmin.data.WEB3AdminTPSessionDatabaseExtensions;
import webbroker3.tradingpoweradmin.handler.WEB3AdminTPPaymentRequisitionCustomerSearchHandler;
import webbroker3.tradingpoweradmin.handler.WEB3AdminTPSearchAdvanceCustomerHandler;
import webbroker3.tradingpoweradmin.handler.WEB3AdminTPReCalcHandler;
import webbroker3.tradingpoweradmin.handler.WEB3AdminTPChangeAssetEvalDivHandler;
import webbroker3.tradingpoweradmin.handler.WEB3AdminTPChangeCalcControlHandler;
import webbroker3.tradingpoweradmin.handler.WEB3AdminTPFindPaymentRequisitionHandler;
import webbroker3.tradingpoweradmin.handler.WEB3AdminTPStopReleaseDepositAutoTransferHandler;
import webbroker3.tradingpoweradmin.handler.WEB3AdminTPPaymentRequisitionManageHandler;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPAdvanceCustomerDetailRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPAdvanceCustomerDetailResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPAdvanceCustomerSearchInputRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPAdvanceCustomerSearchInputResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPAdvanceCustomerSearchListRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPAdvanceCustomerSearchListResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionDetailRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionDetailResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionDownLoadRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionDownLoadResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionInputRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionInputResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionListRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionListResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPReCalcInputRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPReCalcInputResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPReCalcRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPReCalcResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeAssetEvalDivCompleteRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeAssetEvalDivCompleteResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeAssetEvalDivConfirmRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeAssetEvalDivConfirmResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeAssetEvalDivInputRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeAssetEvalDivInputResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeCalcControlCompleteRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeCalcControlCompleteResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeCalcControlConfirmRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeCalcControlConfirmResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeCalcControlInputRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPChangeCalcControlInputResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPFindCalcControlRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPFindCalcControlResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPFindDepositAutoTransferStopRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPFindDepositAutoTransferStopResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPFindPaymentRequisitionRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPFindPaymentRequisitionResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPReleaseDepositAutoTransferCompleteRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPReleaseDepositAutoTransferCompleteResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPReleaseDepositAutoTransferConfirmRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPReleaseDepositAutoTransferConfirmResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPStopDepositAutoTransferCompleteRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPStopDepositAutoTransferCompleteResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPStopDepositAutoTransferConfirmRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPStopDepositAutoTransferConfirmResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPStopDepositAutoTransferInputRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPStopDepositAutoTransferInputResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionManageSearchRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionManageSearchResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionManageHistoryRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionManageHistoryResponse;
import webbroker3.tradingpoweradmin.service.WEB3AdminTPServiceInterceptor;
import webbroker3.tradingpoweradmin.service.delegate.WEB3AdminTPPaymentRequisitionCustomerSearchService;
import webbroker3.tradingpoweradmin.service.delegate.WEB3AdminTPSearchAdvanceCustomerService;
import webbroker3.tradingpoweradmin.service.delegate.WEB3AdminTPReCalcService;
import webbroker3.tradingpoweradmin.service.delegate.WEB3AdminTPChangeAssetEvalDivService;
import webbroker3.tradingpoweradmin.service.delegate.WEB3AdminTPChangeCalcControlService;
import webbroker3.tradingpoweradmin.service.delegate.WEB3AdminTPFindPaymentRequisitionService;
import webbroker3.tradingpoweradmin.service.delegate.WEB3AdminTPStopReleaseDepositAutoTransferService;
import webbroker3.tradingpoweradmin.service.delegate.WEB3AdminTPPaymentRequisitionManageService;
import webbroker3.tradingpoweradmin.service.delegate.stdimpls.WEB3AdminTPPaymentRequisitionCustomerSearchServiceImpl;
import webbroker3.tradingpoweradmin.service.delegate.stdimpls.WEB3AdminTPSearchAdvanceCustomerServiceImpl;
import webbroker3.tradingpoweradmin.service.delegate.stdimpls.WEB3AdminTPReCalcServiceImpl;
import webbroker3.tradingpoweradmin.service.delegate.stdimpls.WEB3AdminTPChangeAssetEvalDivServiceImpl;
import webbroker3.tradingpoweradmin.service.delegate.stdimpls.WEB3AdminTPChangeCalcControlServiceImpl;
import webbroker3.tradingpoweradmin.service.delegate.stdimpls.WEB3AdminTPFindPaymentRequisitionServiceImpl;
import webbroker3.tradingpoweradmin.service.delegate.stdimpls.WEB3AdminTPStopReleaseDepositAutoTransferServiceImpl;
import webbroker3.tradingpoweradmin.service.delegate.stdimpls.WEB3AdminTPPaymentRequisitionManageServiceImpl;
import webbroker3.util.WEB3LogUtility;

/**
 * �]�͊Ǘ��҃v���O�C���̃v���O�C���N���X<br>
 *
 * @@author kazumi HORINO
 * @@version 1.0
 */
public class WEB3AdminTPPlugin extends Plugin
{

    /**
     * ���O
     */
    private static final WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTPPlugin.class);

    /**
     * �R���X�g���N�^
     */
    public WEB3AdminTPPlugin()
    {
    }

    /**
     * ���̃v���O�C�����v���O�C������Ƃ��Ɏg�p���郁�\�b�h
     */
    public static void plug() throws Exception
    {
        plug(WEB3AdminTPPlugin.class);
    }

    /**
     * ���̃v���O�C�����v���O�C������Ƃ��ɏ�������郁�\�b�h
     */
    public static void onPlug() throws Exception
    {

        // Kernel�v���O�C�����v���O
        KernelPlugin.plug();

        // �e�[�u������o�^
        WEB3AdminTPAccountDatabaseExtensions.plug();
        WEB3AdminTPSessionDatabaseExtensions.plug();
        
        // ---------------------------------------------------------------------

        // �]�͌v�Z���@@�ύX�T�[�r�X��o�^
        Services.registerService(
            WEB3AdminTPChangeAssetEvalDivService.class,
            new WEB3AdminTPChangeAssetEvalDivServiceImpl());

        log.debug("WEB3AdminTPChangeAssetEvalDivService registered.");

        // �]�͊Ǘ��҃T�[�r�X�C���^�[�Z�v�^��o�^
        Services.addInterceptor(
        		WEB3AdminTPChangeAssetEvalDivService.class,
            new WEB3AdminTPServiceInterceptor());

        log.debug("WEB3AdminTPServiceInterceptor added to WEB3AdminTPChangeAssetEvalDivService");

        // TransactionalInterceptor��o�^
        Services.addInterceptor(
        		WEB3AdminTPChangeAssetEvalDivService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        log.debug("TransactionalInterceptor added to WEB3AdminTPChangeAssetEvalDivService");

        // ---------------------------------------------------------------------

        // �]�͌v�Z���@@�ύX���͉�ʃ��b�Z�[�W��o�^
        regClass(WEB3AdminTPChangeAssetEvalDivInputRequest.class);
        log.debug("WEB3AdminTPChangeAssetEvalDivInputRequest registered.");
        regClass(WEB3AdminTPChangeAssetEvalDivInputResponse.class);
        log.debug("WEB3AdminTPChangeAssetEvalDivInputResponse registered.");


        // �]�͌v�Z���@@�ύX���͉�ʃn���h��
        regHandler(
            WEB3AdminTPPlugin.class,
            WEB3AdminTPChangeAssetEvalDivInputRequest.class,
            WEB3AdminTPChangeAssetEvalDivHandler.class,
            "changeAssetEvalDivInputRequest");

        log.debug("WEB3AdminTPChangeAssetEvalDivHandler.changeAssetEvalDivInputRequest registered.");

        // ---------------------------------------------------------------------

        // �]�͌v�Z���@@�ύX�m�F���b�Z�[�W��o�^
        regClass(WEB3AdminTPChangeAssetEvalDivConfirmRequest.class);
        log.debug("WEB3AdminTPChangeAssetEvalDivConfirmRequest registered.");
        regClass(WEB3AdminTPChangeAssetEvalDivConfirmResponse.class);
        log.debug("WEB3AdminTPChangeAssetEvalDivConfirmResponse registered.");


        // �]�͌v�Z���@@�ύX�m�F�n���h��
        regHandler(
            WEB3AdminTPPlugin.class,
            WEB3AdminTPChangeAssetEvalDivConfirmRequest.class,
            WEB3AdminTPChangeAssetEvalDivHandler.class,
            "changeAssetEvalDivConfirmRequest");

        log.debug("WEB3AdminTPChangeAssetEvalDivHandler.changeAssetEvalDivConfirmRequest registered.");

        // ---------------------------------------------------------------------

        // �]�͌v�Z���@@�ύX�������b�Z�[�W��o�^
        regClass(WEB3AdminTPChangeAssetEvalDivCompleteRequest.class);
        log.debug("WEB3AdminTPChangeAssetEvalDivCompleteRequest registered.");
        regClass(WEB3AdminTPChangeAssetEvalDivCompleteResponse.class);
        log.debug("WEB3AdminTPChangeAssetEvalDivCompleteResponse registered.");


        // �]�͌v�Z���@@�ύX�����n���h��
        regHandler(
            WEB3AdminTPPlugin.class,
            WEB3AdminTPChangeAssetEvalDivCompleteRequest.class,
            WEB3AdminTPChangeAssetEvalDivHandler.class,
            "changeAssetEvalDivCompleteRequest");

        log.debug("WEB3AdminTPChangeAssetEvalDivHandler.changeAssetEvalDivCompleteRequest registered.");

        // ---------------------------------------------------------------------


        // ---------------------------------------------------------------------

        // �]�͐���@@�\�ύX�T�[�r�X��o�^
        Services.registerService(
            WEB3AdminTPChangeCalcControlService.class,
            new WEB3AdminTPChangeCalcControlServiceImpl());

        log.debug("WEB3AdminTPChangeCalcControlService registered.");

        // �]�͊Ǘ��҃T�[�r�X�C���^�[�Z�v�^��o�^
        Services.addInterceptor(
        		WEB3AdminTPChangeCalcControlService.class,
            new WEB3AdminTPServiceInterceptor());

        log.debug("WEB3AdminTPServiceInterceptor added to WEB3AdminTPChangeCalcControlService");

        // TransactionalInterceptor��o�^
        Services.addInterceptor(
        		WEB3AdminTPChangeCalcControlService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        log.debug("TransactionalInterceptor added to WEB3AdminTPChangeCalcControlService");

        // ---------------------------------------------------------------------

        // �]�͐���@@�\�������b�Z�[�W��o�^
        regClass(WEB3AdminTPFindCalcControlRequest.class);
        log.debug("WEB3AdminTPFindCalcControlRequest registered.");
        regClass(WEB3AdminTPFindCalcControlResponse.class);
        log.debug("WEB3AdminTPFindCalcControlResponse registered.");


        // �]�͐���@@�\�ύX���͉�ʃn���h��
        regHandler(
            WEB3AdminTPPlugin.class,
            WEB3AdminTPFindCalcControlRequest.class,
            WEB3AdminTPChangeCalcControlHandler.class,
            "findCalcControlRequest");

        log.debug("WEB3AdminTPChangeCalcControlHandler.findCalcControlRequest registered.");

        // ---------------------------------------------------------------------

        // �]�͐���@@�\�ύX���͉�ʃ��b�Z�[�W��o�^
        regClass(WEB3AdminTPChangeCalcControlInputRequest.class);
        log.debug("WEB3AdminTPChangeCalcControlInputRequest registered.");
        regClass(WEB3AdminTPChangeCalcControlInputResponse.class);
        log.debug("WEB3AdminTPChangeCalcControlInputResponse registered.");


        // �]�͐���@@�\�ύX���͉�ʃn���h��
        regHandler(
            WEB3AdminTPPlugin.class,
            WEB3AdminTPChangeCalcControlInputRequest.class,
            WEB3AdminTPChangeCalcControlHandler.class,
            "changeCalcControlInputRequest");

        log.debug("WEB3AdminTPChangeCalcControlHandler.changeCalcControlInputRequest registered.");

        // ---------------------------------------------------------------------

        // �]�͐���@@�\�ύX�m�F���b�Z�[�W��o�^
        regClass(WEB3AdminTPChangeCalcControlConfirmRequest.class);
        log.debug("WEB3AdminTPChangeCalcControlConfirmRequest registered.");
        regClass(WEB3AdminTPChangeCalcControlConfirmResponse.class);
        log.debug("WEB3AdminTPChangeCalcControlConfirmResponse registered.");


        // �]�͐���@@�\�ύX�m�F�n���h��
        regHandler(
            WEB3AdminTPPlugin.class,
            WEB3AdminTPChangeCalcControlConfirmRequest.class,
            WEB3AdminTPChangeCalcControlHandler.class,
            "changeCalcControlConfirmRequest");

        log.debug("WEB3AdminTPChangeCalcControlHandler.changeCalcControlConfirmRequest registered.");

        // ---------------------------------------------------------------------

        // �]�͐���@@�\�ύX�������b�Z�[�W��o�^
        regClass(WEB3AdminTPChangeCalcControlCompleteRequest.class);
        log.debug("WEB3AdminTPChangeCalcControlCompleteRequest registered.");
        regClass(WEB3AdminTPChangeCalcControlCompleteResponse.class);
        log.debug("WEB3AdminTPChangeCalcControlCompleteResponse registered.");


        // �]�͐���@@�\�ύX�����n���h��
        regHandler(
            WEB3AdminTPPlugin.class,
            WEB3AdminTPChangeCalcControlCompleteRequest.class,
            WEB3AdminTPChangeCalcControlHandler.class,
            "changeCalcControlCompleteRequest");

        log.debug("WEB3AdminTPChangeCalcControlHandler.changeCalcControlCompleteRequest registered.");

        // ---------------------------------------------------------------------

        // ---------------------------------------------------------------------

        // �������������T�[�r�X��o�^
        Services.registerService(
            WEB3AdminTPFindPaymentRequisitionService.class,
            new WEB3AdminTPFindPaymentRequisitionServiceImpl());

        log.debug("WEB3AdminTPFindPaymentRequisitionService registered.");

        // �]�͊Ǘ��҃T�[�r�X�C���^�[�Z�v�^��o�^
        Services.addInterceptor(
        		WEB3AdminTPFindPaymentRequisitionService.class,
            new WEB3AdminTPServiceInterceptor());

        log.debug("WEB3AdminTPServiceInterceptor added to WEB3AdminTPFindPaymentRequisitionService");

        // TransactionalInterceptor��o�^
        Services.addInterceptor(
        		WEB3AdminTPFindPaymentRequisitionService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        log.debug("TransactionalInterceptor added to WEB3AdminTPFindPaymentRequisitionService");

        // ---------------------------------------------------------------------

        // ���������������b�Z�[�W��o�^
        regClass(WEB3AdminTPFindPaymentRequisitionRequest.class);
        log.debug("WEB3AdminTPFindPaymentRequisitionRequest registered.");
        regClass(WEB3AdminTPFindPaymentRequisitionResponse.class);
        log.debug("WEB3AdminTPFindPaymentRequisitionResponse registered.");


        // �������������n���h��
        regHandler(
            WEB3AdminTPPlugin.class,
            WEB3AdminTPFindPaymentRequisitionRequest.class,
            WEB3AdminTPFindPaymentRequisitionHandler.class,
            "findPaymentRequisitionRequest");

        log.debug("WEB3AdminTPFindPaymentRequisitionHandler.findPaymentRequisitionRequest registered.");

        // ---------------------------------------------------------------------

        // ---------------------------------------------------------------------

        // �ۏ؋������U�֒�~/�����T�[�r�X��o�^
        Services.registerService(
            WEB3AdminTPStopReleaseDepositAutoTransferService.class,
            new WEB3AdminTPStopReleaseDepositAutoTransferServiceImpl());

        log.debug("WEB3AdminTPStopReleaseDepositAutoTransferService registered.");

        // �]�͊Ǘ��҃T�[�r�X�C���^�[�Z�v�^��o�^
        Services.addInterceptor(
                WEB3AdminTPStopReleaseDepositAutoTransferService.class,
            new WEB3AdminTPServiceInterceptor());

        log.debug("WEB3AdminTPServiceInterceptor added to WEB3AdminTPStopReleaseDepositAutoTransferService");

        // TransactionalInterceptor��o�^
        Services.addInterceptor(
                WEB3AdminTPStopReleaseDepositAutoTransferService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        log.debug("TransactionalInterceptor added to WEB3AdminTPStopReleaseDepositAutoTransferService");

        // ---------------------------------------------------------------------

        // �ۏ؋������U�֒�~���͉�ʃ��b�Z�[�W��o�^
        regClass(WEB3AdminTPStopDepositAutoTransferInputRequest.class);
        log.debug("WEB3AdminTPStopDepositAutoTransferInputRequest registered.");
        regClass(WEB3AdminTPStopDepositAutoTransferInputResponse.class);
        log.debug("WEB3AdminTPStopDepositAutoTransferInputResponse registered.");

        // �ۏ؋������U�֒�~���͉�ʃn���h��
        regHandler(
            WEB3AdminTPPlugin.class,
            WEB3AdminTPStopDepositAutoTransferInputRequest.class,
            WEB3AdminTPStopReleaseDepositAutoTransferHandler.class,
            "stopDepositAutoTransferInputRequest");

        log.debug("WEB3AdminTPStopReleaseDepositAutoTransferHandler.stopDepositAutoTransferInputRequest registered.");

        // ---------------------------------------------------------------------

        // �ۏ؋������U�֒�~�m�F���b�Z�[�W��o�^
        regClass(WEB3AdminTPStopDepositAutoTransferConfirmRequest.class);
        log.debug("WEB3AdminTPStopDepositAutoTransferConfirmRequest registered.");
        regClass(WEB3AdminTPStopDepositAutoTransferConfirmResponse.class);
        log.debug("WEB3AdminTPStopDepositAutoTransferConfirmResponse registered.");

        // �ۏ؋������U�֒�~�m�F�n���h��
        regHandler(
            WEB3AdminTPPlugin.class,
            WEB3AdminTPStopDepositAutoTransferConfirmRequest.class,
            WEB3AdminTPStopReleaseDepositAutoTransferHandler.class,
            "stopDepositAutoTransferConfirmRequest");

        log.debug("WEB3AdminTPStopReleaseDepositAutoTransferHandler.stopDepositAutoTransferConfirmRequest registered.");

        // ---------------------------------------------------------------------

        // �ۏ؋������U�֒�~�������b�Z�[�W��o�^
        regClass(WEB3AdminTPStopDepositAutoTransferCompleteRequest.class);
        log.debug("WEB3AdminTPStopDepositAutoTransferCompleteRequest registered.");
        regClass(WEB3AdminTPStopDepositAutoTransferCompleteResponse.class);
        log.debug("WEB3AdminTPStopDepositAutoTransferCompleteResponse registered.");


        // �ۏ؋������U�֒�~�����n���h��
        regHandler(
                WEB3AdminTPPlugin.class,
                WEB3AdminTPStopDepositAutoTransferCompleteRequest.class,
                WEB3AdminTPStopReleaseDepositAutoTransferHandler.class,
                "stopDepositAutoTransferCompleteRequest");

        log.debug("WEB3AdminTPStopReleaseDepositAutoTransferHandler.stopDepositAutoTransferCompleteRequest registered.");

        // ---------------------------------------------------------------------

        // �ۏ؋������U�֒�~�ڋq�������b�Z�[�W��o�^
        regClass(WEB3AdminTPFindDepositAutoTransferStopRequest.class);
        log.debug("WEB3AdminTPFindDepositAutoTransferStopRequest registered.");
        regClass(WEB3AdminTPFindDepositAutoTransferStopResponse.class);
        log.debug("WEB3AdminTPFindDepositAutoTransferStopResponse registered.");

        // �ۏ؋������U�֒�~�ڋq�����n���h��
        regHandler(
            WEB3AdminTPPlugin.class,
            WEB3AdminTPFindDepositAutoTransferStopRequest.class,
            WEB3AdminTPStopReleaseDepositAutoTransferHandler.class,
            "findDepositAutoTransferStopRequest");

        log.debug("WEB3AdminTPStopReleaseDepositAutoTransferHandler.findDepositAutoTransferStopRequest registered.");

        // ---------------------------------------------------------------------

        // �ۏ؋������U�։����m�F���b�Z�[�W��o�^
        regClass(WEB3AdminTPReleaseDepositAutoTransferConfirmRequest.class);
        log.debug("WEB3AdminTPReleaseDepositAutoTransferConfirmRequest registered.");
        regClass(WEB3AdminTPReleaseDepositAutoTransferConfirmResponse.class);
        log.debug("WEB3AdminTPReleaseDepositAutoTransferConfirmResponse registered.");

        // �ۏ؋������U�։����m�F�n���h��
        regHandler(
            WEB3AdminTPPlugin.class,
            WEB3AdminTPReleaseDepositAutoTransferConfirmRequest.class,
            WEB3AdminTPStopReleaseDepositAutoTransferHandler.class,
            "releaseDepositAutoTransferConfirmRequest");

        log.debug("WEB3AdminTPStopReleaseDepositAutoTransferHandler.releaseDepositAutoTransferConfirmRequest registered.");

        // ---------------------------------------------------------------------

        // �ۏ؋������U�։����������b�Z�[�W��o�^
        regClass(WEB3AdminTPReleaseDepositAutoTransferCompleteRequest.class);
        log.debug("WEB3AdminTPReleaseDepositAutoTransferCompleteRequest registered.");
        regClass(WEB3AdminTPReleaseDepositAutoTransferCompleteResponse.class);
        log.debug("WEB3AdminTPReleaseDepositAutoTransferCompleteResponse registered.");


        // �ۏ؋������U�։��������n���h��
        regHandler(
                WEB3AdminTPPlugin.class,
                WEB3AdminTPReleaseDepositAutoTransferCompleteRequest.class,
                WEB3AdminTPStopReleaseDepositAutoTransferHandler.class,
                "releaseDepositAutoTransferCompleteRequest");

        log.debug("WEB3AdminTPStopReleaseDepositAutoTransferHandler.releaseDepositAutoTransferCompleteRequest registered.");


        // ---------------------------------------------------------------------

//�ǉ�

        // ---------------------------------------------------------------------

        // �]�͍Čv�Z�T�[�r�X��o�^
        Services.registerService(
            WEB3AdminTPReCalcService.class,
                new WEB3AdminTPReCalcServiceImpl());

        log.debug("WEB3AdminTPReCalcService registered.");

        // �]�͊Ǘ��҃T�[�r�X�C���^�[�Z�v�^��o�^
        Services.addInterceptor(
            WEB3AdminTPReCalcService.class,
                new WEB3AdminTPServiceInterceptor());

        log.debug("WEB3AdminTpServiceInterceptor added to WEB3AdminTPReCalcService");

        // TransactionalInterceptor��o�^
        Services.addInterceptor(
            WEB3AdminTPReCalcService.class,
                new TransactionalInterceptor(
                    TransactionalInterceptor.TX_JOIN_EXISTING));

        log.debug("TransactionalInterceptor added to WEB3AdminTPReCalcService");

        // �]�͍Čv�Z���s���b�Z�[�W��o�^
        regClass(WEB3AdminTPReCalcRequest.class);
        log.debug("WEB3AdminTPReCalcRequest registered.");
        regClass(WEB3AdminTPReCalcResponse.class);
        log.debug("WEB3AdminTPReCalcResponse registered.");

        // �]�͍Čv�Z�n���h��
        regHandler(
            WEB3AdminTPPlugin.class,
            WEB3AdminTPReCalcRequest.class,
            WEB3AdminTPReCalcHandler.class,
            "reCalcRequest");

        log.debug("WEB3AdminTPReCalcHandler.reCalcRequest registered.");


        // �]�͍Čv�Z���̓��b�Z�[�W��o�^
        regClass(WEB3AdminTPReCalcInputRequest.class);
        log.debug("WEB3AdminTPReCalcInputRequest registered.");
        regClass(WEB3AdminTPReCalcInputResponse.class);
        log.debug("WEB3AdminTPReCalcInputResponse registered.");

        // �]�͍Čv�Z�n���h��
        regHandler(
            WEB3AdminTPPlugin.class,
            WEB3AdminTPReCalcInputRequest.class,
            WEB3AdminTPReCalcHandler.class,
            "reCalcInputRequest");

        log.debug("WEB3AdminTPReCalcHandler.reCalcInputRequest registered.");


  // ---------------------------------------------------------------------

//�ǉ�

//�ǉ�2

        // ---------------------------------------------------------------------

        // �ۏ؋��ێ�������/���֋������ڋq�����T�[�r�X��o�^
        Services.registerService(
                WEB3AdminTPSearchAdvanceCustomerService.class,new WEB3AdminTPSearchAdvanceCustomerServiceImpl());

        log.debug("WEB3AdminTPAssetRepeatCalculationService registered.");

        // �ۏ؋��ێ�������/���֋������ڋq�����T�[�r�X�C���^�[�Z�v�^��o�^
        Services.addInterceptor(
                WEB3AdminTPSearchAdvanceCustomerService.class,
                new WEB3AdminTPServiceInterceptor());

        log.debug("WEB3AdminTPServiceInterceptor added to WEB3AdminTPAssetRepeatCalculationService");

        // TransactionalInterceptor��o�^
        Services.addInterceptor(
                WEB3AdminTPSearchAdvanceCustomerService.class,
                new TransactionalInterceptor(
                    TransactionalInterceptor.TX_JOIN_EXISTING));

        log.debug("TransactionalInterceptor added to WEB3AdminTPAssetRepeatCalculationService");

        // �ۏ؋��ێ�������/���֋������ڋq�������̓��b�Z�[�W��o�^
        regClass(WEB3AdminTPAdvanceCustomerSearchInputRequest.class);
        log.debug("WEB3AdminTPAssetRepeatCalculationRequest registered.");
        regClass(WEB3AdminTPAdvanceCustomerSearchInputResponse.class);
        log.debug("WEB3AdminTPAssetRepeatCalculationResponse registered.");

        // �ۏ؋��ێ�������/���֋������ڋq�������̓n���h��
        regHandler(
            WEB3AdminTPPlugin.class,
            WEB3AdminTPAdvanceCustomerSearchInputRequest.class,
            WEB3AdminTPSearchAdvanceCustomerHandler.class,
            "getAdvanceCustomerInput");
        log.debug("WEB3AdminTPAssetRepeatCalculationHandler.assetRepeatCalculationRequest registered.");

        // �ۏ؋��ێ�������/���֋������ڋq�����ꗗ���b�Z�[�W��o�^
        regClass(WEB3AdminTPAdvanceCustomerSearchListRequest.class);
        log.debug("WEB3AdminTPAssetRepeatCalculationRequest registered.");
        regClass(WEB3AdminTPAdvanceCustomerSearchListResponse.class);
        log.debug("WEB3AdminTPAssetRepeatCalculationResponse registered.");

        // �ۏ؋��ێ�������/���֋������ڋq�����ꗗ�n���h��
        regHandler(
            WEB3AdminTPPlugin.class,
            WEB3AdminTPAdvanceCustomerSearchListRequest.class,
            WEB3AdminTPSearchAdvanceCustomerHandler.class,
            "getAdvanceCustomerList");
        log.debug("WEB3AdminTPAssetRepeatCalculationHandler.assetRepeatCalculationRequest registered.");

        // �ۏ؋��ێ�������/���֋������ڋq�ڍ׃��b�Z�[�W��o�^
        regClass(WEB3AdminTPAdvanceCustomerDetailRequest.class);
        log.debug("WEB3AdminTPAssetRepeatCalculationRequest registered.");
        regClass(WEB3AdminTPAdvanceCustomerDetailResponse.class);
        log.debug("WEB3AdminTPAssetRepeatCalculationResponse registered.");

        // �ۏ؋��ێ�������/���֋������ڋq�ڍד��̓n���h��
        regHandler(
            WEB3AdminTPPlugin.class,
            WEB3AdminTPAdvanceCustomerDetailRequest.class,
            WEB3AdminTPSearchAdvanceCustomerHandler.class,
            "getMarginMaintenanceRateDebitAmountCustomerDetail");
        log.debug("WEB3AdminTPAssetRepeatCalculationHandler.assetRepeatCalculationRequest registered.");

  // ---------------------------------------------------------------------

//�ǉ�2


//�ǉ�3

      // ---------------------------------------------------------------------

      // ���������Ǘ��T�[�r�X��o�^
      Services.registerService(
    		  WEB3AdminTPPaymentRequisitionManageService.class,new WEB3AdminTPPaymentRequisitionManageServiceImpl());

      log.debug("WEB3AdminTPPaymentRequisitionManageService registered.");

      // ���������Ǘ��T�[�r�X�C���^�[�Z�v�^��o�^
      Services.addInterceptor(
    		  WEB3AdminTPPaymentRequisitionManageService.class,
              new WEB3AdminTPServiceInterceptor());

      log.debug("WEB3AdminTPServiceInterceptor added to WEB3AdminTPPaymentRequisitionManageService");

      // TransactionalInterceptor��o�^
      Services.addInterceptor(
    		  WEB3AdminTPPaymentRequisitionManageService.class,
              new TransactionalInterceptor(
                  TransactionalInterceptor.TX_JOIN_EXISTING));

      log.debug("TransactionalInterceptor added to WEB3AdminTPPaymentRequisitionManageService");

      // ���������Ǘ��ꗗ���b�Z�[�W��o�^
      regClass(WEB3AdminTPPaymentRequisitionManageSearchRequest.class);
      log.debug("WEB3AdminTPPaymentRequisitionManageSearchRequest registered.");
      regClass(WEB3AdminTPPaymentRequisitionManageSearchResponse.class);
      log.debug("WEB3AdminTPPaymentRequisitionManageSearchResponse registered.");

      // ���������Ǘ��ꗗ�n���h��
      regHandler(
          WEB3AdminTPPlugin.class,
          WEB3AdminTPPaymentRequisitionManageSearchRequest.class,
          WEB3AdminTPPaymentRequisitionManageHandler.class,
          "WEB3AdminTPPaymentRequisitionManageSearchRequest");
      log.debug("WEB3AdminTPPaymentRequisitionManageHandler.WEB3AdminTPPaymentRequisitionManageSearchRequest registered.");

      // ���������Ǘ��������b�Z�[�W��o�^
      regClass(WEB3AdminTPPaymentRequisitionManageHistoryRequest.class);
      log.debug("WEB3AdminTPPaymentRequisitionManageHistoryRequest registered.");
      regClass(WEB3AdminTPPaymentRequisitionManageHistoryResponse.class);
      log.debug("WEB3AdminTPPaymentRequisitionManageHistoryResponse registered.");

      // ���������Ǘ������n���h��
      regHandler(
          WEB3AdminTPPlugin.class,
          WEB3AdminTPPaymentRequisitionManageHistoryRequest.class,
          WEB3AdminTPPaymentRequisitionManageHandler.class,
          "WEB3AdminTPPaymentRequisitionManageHistoryRequest");
      log.debug("WEB3AdminTPPaymentRequisitionManageHandler.WEB3AdminTPPaymentRequisitionManageHistoryRequest registered.");

// ---------------------------------------------------------------------

      //���������ڋq�����T�[�r�X
      Services.registerService(WEB3AdminTPPaymentRequisitionCustomerSearchService.class,
              new WEB3AdminTPPaymentRequisitionCustomerSearchServiceImpl());

      // �T�[�r�X�Ăяo���O���
      // �����J�n�����Ə����I�����������O�o�͂���悤�ɐݒ肷��

      //���������ڋq�����T�[�r�X
      Services.addInterceptor(
          WEB3AdminTPPaymentRequisitionCustomerSearchService.class,
          new WEB3LogSysTimeInterceptor());

      //���������ڋq�����T�[�r�X
      Services.addInterceptor(
          WEB3AdminTPPaymentRequisitionCustomerSearchService.class,
          new TransactionalInterceptor(
          TransactionalInterceptor.TX_JOIN_EXISTING));

      //���������ڋq�������̓��N�G�X�g�E���X�|���X
      regClass(WEB3AdminTPPaymentRequisitionInputRequest.class);
      regClass(WEB3AdminTPPaymentRequisitionInputResponse.class);

      //���������ڋq�����ꗗ���N�G�X�g�E���X�|���X
      regClass(WEB3AdminTPPaymentRequisitionListRequest.class);
      regClass(WEB3AdminTPPaymentRequisitionListResponse.class);

      //���������ڋq�����ڍ׃��N�G�X�g�E���X�|���X
      regClass(WEB3AdminTPPaymentRequisitionDetailRequest.class);
      regClass(WEB3AdminTPPaymentRequisitionDetailResponse.class);

      //���������ڋq�����_�E�����[�h���N�G�X�g�E���X�|���X
      regClass(WEB3AdminTPPaymentRequisitionDownLoadRequest.class);
      regClass(WEB3AdminTPPaymentRequisitionDownLoadResponse.class);
      
      //���������ڋq�����n���h��(
      regHandler(
          WEB3AdminTPPlugin.class,
          WEB3AdminTPPaymentRequisitionInputRequest.class,
          WEB3AdminTPPaymentRequisitionCustomerSearchHandler.class,
          "getPaymentRequisitionCustomerSearchInput");

      regHandler(
          WEB3AdminTPPlugin.class,
          WEB3AdminTPPaymentRequisitionListRequest.class,
          WEB3AdminTPPaymentRequisitionCustomerSearchHandler.class,
          "getPaymentRequisitionCustomerSearchList");

      regHandler(
          WEB3AdminTPPlugin.class,
          WEB3AdminTPPaymentRequisitionDetailRequest.class,
          WEB3AdminTPPaymentRequisitionCustomerSearchHandler.class,
          "getPaymentRequisitionCustomerSearchDetail");

      regHandler(
          WEB3AdminTPPlugin.class,
          WEB3AdminTPPaymentRequisitionDownLoadRequest.class,
          WEB3AdminTPPaymentRequisitionCustomerSearchHandler.class,
          "getPaymentRequisitionCustomerSearchDownLoad");
//�ǉ�3
        log.info("WEB3AdminTPPlugin bootstrap succeeded.");

    }

}
@
