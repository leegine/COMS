head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EqtypeAdminAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : Eqtypeadmin AppPlugin(WEB3EqtypeadminAppPlugin.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/05/30�@@����(���u) �d�l�ύX ���f��105
Revesion History : 2007/01/17  �Ӑ� (���u) �d�l�ύX���f��No.115-118
Revesion History : 2007/04/27  ��іQ (���u) �d�l�ύX���f��No.128,129,131,132
Revesion History : 2008/01/29  �юu�� (���u) PTS2���Ή�
Revesion History : 2008/02/19  �����F (���u) �d�l�ύX���f��No.170,171,179,180
Revesion History : 2008/11/20  ������ (���u) �d�l�ύX���f��No.213
Revesion History : 2008/12/26  ���� (SRA) �d�l�ύXDB���C�A�E�gNo.025
Revesion History : 2009/01/07  �И��� (���u) �d�l�ύX���f��No.216,No.219
*/
package webbroker3.eqtypeadmin;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;

import webbroker3.common.WEB3LogSysTimeInterceptor;
import webbroker3.eqtypeadmin.data.WEB3EqtypeadminMasterDatabaseExtensions;
import webbroker3.eqtypeadmin.data.WEB3EqtypeadminSessionDatabaseExtensions;
import webbroker3.eqtypeadmin.handler.WEB3AdminEquityAccProductTradeStopChangeHandler;
import webbroker3.eqtypeadmin.handler.WEB3AdminEquityAccProductTradeStopDeleteHandler;
import webbroker3.eqtypeadmin.handler.WEB3AdminEquityAccProductTradeStopListHandler;
import webbroker3.eqtypeadmin.handler.WEB3AdminEquityAccProductTradeStopRegistHandler;
import webbroker3.eqtypeadmin.handler.WEB3AdminEquityAttentionInfoNotifyHandler;
import webbroker3.eqtypeadmin.handler.WEB3AdminEquityAttentionInfoReferenceHandler;
import webbroker3.eqtypeadmin.handler.WEB3AdminEquityForcedSettleOrderApproveHandler;
import webbroker3.eqtypeadmin.handler.WEB3AdminEquityForcedSettleOrderApproveMainHandler;
import webbroker3.eqtypeadmin.handler.WEB3AdminEquityForcedSettleOrderDLHandler;
import webbroker3.eqtypeadmin.handler.WEB3AdminEquityForcedSettleReferenceHandler;
import webbroker3.eqtypeadmin.handler.WEB3AdminEquityForcedSettleTempOrderCreateHandler;
import webbroker3.eqtypeadmin.handler.WEB3AdminEquityManualExpireHandler;
import webbroker3.eqtypeadmin.handler.WEB3AdminEquityManualExpireMainHandler;
import webbroker3.eqtypeadmin.handler.WEB3AdminEquityPTSCancelExecHandler;
import webbroker3.eqtypeadmin.handler.WEB3AdminEquityPTSInputExecHandler;
import webbroker3.eqtypeadmin.handler.WEB3AdminEquityProductCondReferenceHandler;
import webbroker3.eqtypeadmin.handler.WEB3AdminEquityProductCondScheduleHandler;
import webbroker3.eqtypeadmin.handler.WEB3AdminEquityProductCondSettingHandler;
import webbroker3.eqtypeadmin.handler.WEB3AdminFrontNoticeHistoryHandler;
import webbroker3.eqtypeadmin.handler.WEB3AdminOffFloorChangeHandler;
import webbroker3.eqtypeadmin.handler.WEB3AdminOffFloorDeleteHandler;
import webbroker3.eqtypeadmin.handler.WEB3AdminOffFloorProductListHandler;
import webbroker3.eqtypeadmin.handler.WEB3AdminOffFloorRegistHandler;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityForcedSettleOrderApproveMainRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityForcedSettleOrderApproveMainResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityForcedSettleTempOrderCreateRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityForcedSettleTempOrderCreateResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseConfirmResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseInputResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseMainRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseMainResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseRunRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseRunResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseStatusRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseStatusResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSCancelExecCompleteRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSCancelExecCompleteResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSCancelExecConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSCancelExecConfirmResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSCancelExecInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSCancelExecInputResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSInputCancelExecCommonRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSInputCancelExecCommonResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSInputExecCompleteRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSInputExecCompleteResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSInputExecConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSInputExecConfirmResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSInputExecInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSInputExecInputResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleApproveConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleApproveConfirmResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleApproveRunRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleApproveRunResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleApproveStatusRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleApproveStatusResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleDownloadInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleDownloadInputResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleDownloadRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleDownloadResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleRefInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleRefInputResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleReferenceRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleReferenceResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminFrontNoticeHistoryInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminFrontNoticeHistoryInputResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminFrontNoticeHistoryReferenceRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminFrontNoticeHistoryReferenceResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorChangeCompleteRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorChangeCompleteResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorChangeConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorChangeConfirmResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorChangeInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorChangeInputResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorDeleteCompleteRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorDeleteCompleteResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorDeleteConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorDeleteConfirmResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorProductListRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorProductListResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorRegistCompleteRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorRegistCompleteResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorRegistConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorRegistConfirmResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorRegistInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorRegistInputResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopDeleteCompleteRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopDeleteCompleteResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopDeleteConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopDeleteConfirmResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopListRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopListResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopModifyCompleteRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopModifyCompleteResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopModifyConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopModifyConfirmResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopModifyInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopModifyInputResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopRegistCompleteRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopRegistCompleteResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopRegistConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopRegistConfirmResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopRegistInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopRegistInputResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondConfCompleteRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondConfCompleteResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondConfConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondConfConfirmResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondConfInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondConfInputResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondListInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondListInputResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondListRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondListResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondRefInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondRefInputResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondRefReferenceRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondRefReferenceResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEqAttentionInfoRefInpRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEqAttentionInfoRefInpResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEqAttentionInfoRefRefRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEqAttentionInfoRefRefResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityAttentionInfoNotifyRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityAttentionInfoNotifyResponse;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityAccProductTradeStopChangeService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityAccProductTradeStopDeleteService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityAccProductTradeStopListService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityAccProductTradeStopRegistService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityAttentionInfoNotifyService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityAttentionInfoNotifyUnitService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityAttentionInfoReferenceService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityForcedSettleBelowMarginRateBefOnlineTempOrderCreateUnitService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityForcedSettleBelowMarginRateIntermissionTempOrderCreateUnitService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityForcedSettleCloseDateTempOrderCreateUnitService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityForcedSettleOrderApproveMainService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityForcedSettleOrderApproveService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityForcedSettleOrderApproveUnitService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityForcedSettleOrderDLService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityForcedSettleReferenceService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityForcedSettleTempOrderCreateService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityManualExpireMainService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityManualExpireService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityPTSCancelExecService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityPTSInputExecService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityProductCondReferenceService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityProductCondScheduleService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityProductCondSettingService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminFrontNoticeHistoryService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminFrontOrderCommonService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminOffFloorChangeService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminOffFloorDeleteService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminOffFloorProductListService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminOffFloorRegistService;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityAccProductTradeStopChangeServiceImpl;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityAccProductTradeStopDeleteServiceImpl;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityAccProductTradeStopListServiceImpl;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityAccProductTradeStopRegistServiceImpl;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityAttentionInfoNotifyServiceImpl;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityAttentionInfoNotifyUnitServiceImpl;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityAttentionInfoReferenceServiceImpl;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityForcedSettleBelowMarginRateBefOnlineTempOrderCreateUnitServiceImpl;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityForcedSettleBelowMarginRateIntermissionTempOrderCreateUnitServiceImpl;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityForcedSettleCloseDateTempOrderCreateUnitServiceImpl;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityForcedSettleOrderApproveMainServiceImpl;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityForcedSettleOrderApproveServiceImpl;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityForcedSettleOrderApproveUnitServiceImpl;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityForcedSettleOrderDLServiceImpl;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityForcedSettleReferenceServiceImpl;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityForcedSettleTempOrderCreateServiceImpl;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityManualExpireMainServiceImpl;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityManualExpireServiceImpl;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityPTSCancelExecServiceImpl;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityPTSInputExecServiceImpl;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityProductCondReferenceServiceImpl;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityProductCondScheduleServiceImpl;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityProductCondSettingServiceImpl;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminFrontNoticeHistoryServiceImpl;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminFrontOrderCommonServiceImpl;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminOffFloorChangeServiceImpl;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminOffFloorDeleteServiceImpl;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminOffFloorProductListServiceImpl;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminOffFloorRegistServiceImpl;
import webbroker3.mqgateway.WEB3MQGatewayInterceptor;
import webbroker3.util.WEB3LogUtility;

/**
 * Webbroker3-Eqtypeadmin<BR>
 * WEB3EqtypeadminAppPlugin
 * @@author SRAI
 * @@version 1.0
 */
public final class WEB3EqtypeAdminAppPlugin extends Plugin
{
    /**
     * ���O���[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3EqtypeAdminAppPlugin.class);

    /**
     * �f�t�H���g�R���X�g���N�^
     */
    public WEB3EqtypeAdminAppPlugin()
    {
    }

    /**
     * plug method
     * @@throws Exception exception
     */
    public static void plug() throws Exception
    {
        final String STR_METHOD_NAME = "plug()";
        log.entering(STR_METHOD_NAME);
        plug(WEB3EqtypeAdminAppPlugin.class);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * onPlug method
     * @@throws Exception exception
     */
    public static void onPlug() throws Exception
    {
        final String METHOD_NAME = "onPlug()";
        log.entering(METHOD_NAME);

        // ���̃v���O�C������ɓǂݍ��ޕK�v�̂���v���O�C���̎w��B
        KernelPlugin.plug();

        // DatabaseExtensions �̃v���O�C������
        WEB3EqtypeadminSessionDatabaseExtensions.plug();
        WEB3EqtypeadminMasterDatabaseExtensions.plug();

        // Service �̓o�^����
        // �Ǘ��Ҍڋq�����ʎ����~�ύX�T�[�r�X
        Services.registerService(WEB3AdminEquityAccProductTradeStopChangeService.class,
            new WEB3AdminEquityAccProductTradeStopChangeServiceImpl());

        // �Ǘ��Ҍڋq�����ʎ����~�o�^�T�[�r�X
        Services.registerService(WEB3AdminEquityAccProductTradeStopRegistService.class,
            new WEB3AdminEquityAccProductTradeStopRegistServiceImpl());

        // �Ǘ��Ҍڋq�����ʎ����~�ꗗ�T�[�r�X
        Services.registerService(WEB3AdminEquityAccProductTradeStopListService.class,
            new WEB3AdminEquityAccProductTradeStopListServiceImpl());

        // �Ǘ��Ҋ������������\��ꗗ�T�[�r�X
        Services.registerService(WEB3AdminEquityProductCondScheduleService.class,
            new WEB3AdminEquityProductCondScheduleServiceImpl());

        // �Ǘ��Ҍڋq�����ʎ����~�폜�T�[�r�X
        Services.registerService(WEB3AdminEquityAccProductTradeStopDeleteService.class,
            new WEB3AdminEquityAccProductTradeStopDeleteServiceImpl());

        // �Ǘ��Ҋ������������Ɖ�T�[�r�X
        Services.registerService(WEB3AdminEquityProductCondReferenceService.class,
            new WEB3AdminEquityProductCondReferenceServiceImpl());

        // �Ǘ��җ���O���������ꗗ�T�[�r�X
        Services.registerService(WEB3AdminOffFloorProductListService.class,
            new  WEB3AdminOffFloorProductListServiceImpl());

        // �Ǘ��җ���O���������X�V�T�[�r�X
        Services.registerService(WEB3AdminOffFloorChangeService.class,
            new WEB3AdminOffFloorChangeServiceImpl());

        // �Ǘ��җ���O���������폜�T�[�r�X
        Services.registerService(WEB3AdminOffFloorDeleteService.class,
            new WEB3AdminOffFloorDeleteServiceImpl());

        // �Ǘ��җ���O���������V�K�o�^�T�[�r�X
        Services.registerService(WEB3AdminOffFloorRegistService.class,
            new WEB3AdminOffFloorRegistServiceImpl());

        // �Ǘ��Ҋ������������ݒ�T�[�r�X
        Services.registerService(WEB3AdminEquityProductCondSettingService.class,
            new WEB3AdminEquityProductCondSettingServiceImpl());

        // �Ǘ��҃t�����g�������ʃT�[�r�X
        Services.registerService(WEB3AdminFrontOrderCommonService.class,
            new WEB3AdminFrontOrderCommonServiceImpl());

       // �Ǘ��Ғʒm�����Q�ƃT�[�r�X
        Services.registerService(WEB3AdminFrontNoticeHistoryService.class,
            new WEB3AdminFrontNoticeHistoryServiceImpl());
        
        //�Ǘ��ҁE�����蓮�����T�[�r�X
        Services.registerService(WEB3AdminEquityManualExpireService.class,
            new WEB3AdminEquityManualExpireServiceImpl());
        
        //�Ǘ��ҁE�����蓮�������C���T�[�r�X
        Services.registerService(WEB3AdminEquityManualExpireMainService.class,
            new WEB3AdminEquityManualExpireMainServiceImpl());

        //�Ǘ��ҁE�������ω��������F�^�񏳔F�T�[�r�X
        Services.registerService(WEB3AdminEquityForcedSettleOrderApproveService.class,
            new WEB3AdminEquityForcedSettleOrderApproveServiceImpl());

        //�Ǘ��ҁE�������ω��������F�^�񏳔F���C���T�[�r�X
        Services.registerService(WEB3AdminEquityForcedSettleOrderApproveMainService.class,
            new WEB3AdminEquityForcedSettleOrderApproveMainServiceImpl());

        //�Ǘ��ҁE�������ω��������F�^�񏳔F�ꌏ�T�[�r�X
        Services.registerService(WEB3AdminEquityForcedSettleOrderApproveUnitService.class,
            new WEB3AdminEquityForcedSettleOrderApproveUnitServiceImpl());

        //�Ǘ��ҁE�������ϒ����Ɖ�T�[�r�X
        Services.registerService(WEB3AdminEquityForcedSettleReferenceService.class,
            new WEB3AdminEquityForcedSettleReferenceServiceImpl());

        //�������ω������쐬�T�[�r�X
        Services.registerService(WEB3AdminEquityForcedSettleTempOrderCreateService.class,
            new WEB3AdminEquityForcedSettleTempOrderCreateServiceImpl());

        //�������ϊ����������������쐬�ꌏ�T�[�r�X
        Services.registerService(WEB3AdminEquityForcedSettleCloseDateTempOrderCreateUnitService.class,
            new WEB3AdminEquityForcedSettleCloseDateTempOrderCreateUnitServiceImpl());

        //�������ϕۏ؋��ێ�������i�I�����C���J�n�O�j�������쐬�ꌏ�T�[�r�X
        Services.registerService(WEB3AdminEquityForcedSettleBelowMarginRateBefOnlineTempOrderCreateUnitService.class,
            new WEB3AdminEquityForcedSettleBelowMarginRateBefOnlineTempOrderCreateUnitServiceImpl());

        //�Ǘ��ҁE�����iPTS�j�o�����̓T�[�r�X
        Services.registerService(WEB3AdminEquityPTSInputExecService.class,
            new WEB3AdminEquityPTSInputExecServiceImpl());

        //�Ǘ��ҁE�����iPTS�j�o������T�[�r�X
        Services.registerService(WEB3AdminEquityPTSCancelExecService.class,
            new WEB3AdminEquityPTSCancelExecServiceImpl());

        //�������ϕۏ؋��ێ�������i��ԁj�������쐬�ꌏ�T�[�r�X
        Services.registerService(WEB3AdminEquityForcedSettleBelowMarginRateIntermissionTempOrderCreateUnitService.class,
            new WEB3AdminEquityForcedSettleBelowMarginRateIntermissionTempOrderCreateUnitServiceImpl());

        //�Ǘ��ҁE�������ϒ���DL�T�[�r�X
        Services.registerService(WEB3AdminEquityForcedSettleOrderDLService.class,
            new WEB3AdminEquityForcedSettleOrderDLServiceImpl());

        //�Ǘ��Ғ��ӏ��Ɖ�T�[�r�X
        Services.registerService(WEB3AdminEquityAttentionInfoReferenceService.class,
            new WEB3AdminEquityAttentionInfoReferenceServiceImpl());

        //���ӏ��ʒm�T�[�r�X
        Services.registerService(WEB3AdminEquityAttentionInfoNotifyService.class,
            new WEB3AdminEquityAttentionInfoNotifyServiceImpl());

        //���ӏ��ʒm�ꌏ�T�[�r�X
        Services.registerService(WEB3AdminEquityAttentionInfoNotifyUnitService.class,
            new WEB3AdminEquityAttentionInfoNotifyUnitServiceImpl());

        // �T�[�r�X�C���^�Z�v�^�̐ݒ�
        // �Ǘ��Ҍڋq�����ʎ����~�o�^�T�[�r�X
        Services.addInterceptor(WEB3AdminEquityAccProductTradeStopRegistService.class,
            new WEB3AdminPMEquityServiceInterceptor());


        // �Ǘ��Ҍڋq�����ʎ����~�ύX�T�[�r�X
        Services.addInterceptor(WEB3AdminEquityAccProductTradeStopChangeService.class,
            new WEB3AdminPMEquityServiceInterceptor());

        // �Ǘ��Ҍڋq�����ʎ����~�ꗗ�T�[�r�X
        Services.addInterceptor(WEB3AdminEquityAccProductTradeStopListService.class,
            new WEB3AdminPMEquityServiceInterceptor());

        // �Ǘ��Ҋ������������\��ꗗ�T�[�r�X
        Services.addInterceptor(WEB3AdminEquityProductCondScheduleService.class,
            new WEB3AdminPMEquityServiceInterceptor());

        // �Ǘ��Ҍڋq�����ʎ����~�폜�T�[�r�X
        Services.addInterceptor(WEB3AdminEquityAccProductTradeStopDeleteService.class,
            new WEB3AdminPMEquityServiceInterceptor());

        // �Ǘ��Ҋ������������Ɖ�T�[�r�X
        Services.addInterceptor(WEB3AdminEquityProductCondReferenceService.class,
            new WEB3AdminPMEquityServiceInterceptor());

        // �Ǘ��җ���O���������ꗗ�T�[�r�X
        Services.addInterceptor(WEB3AdminOffFloorProductListService.class,
            new WEB3AdminOffFloorServiceInterceptor());

        // �Ǘ��җ���O���������X�V�T�[�r�X
        Services.addInterceptor(WEB3AdminOffFloorChangeService.class,
            new WEB3AdminOffFloorServiceInterceptor());

        // �Ǘ��җ���O���������폜�T�[�r�X
        Services.addInterceptor(WEB3AdminOffFloorDeleteService.class,
            new WEB3AdminOffFloorServiceInterceptor());

        // �Ǘ��җ���O���������V�K�o�^�T�[�r�X
        Services.addInterceptor(WEB3AdminOffFloorRegistService.class,
            new WEB3AdminOffFloorServiceInterceptor());

        // �Ǘ��Ҋ������������ݒ�T�[�r�X
       Services.addInterceptor(WEB3AdminEquityProductCondSettingService.class,
            new WEB3AdminPMEquityServiceInterceptor());

       //�Ǘ��ҁE�����蓮�����T�[�r�X
       Services.addInterceptor(WEB3AdminEquityManualExpireService.class,
           new WEB3AdminEquityManualExpireServiceInterceptor());

        //�Ǘ��ҁE�������ϒ����Ɖ�T�[�r�X
        Services.addInterceptor(WEB3AdminEquityForcedSettleReferenceService.class,
            new WEB3AdminPMEquityServiceInterceptor());

        //�Ǘ��ҁE�������ω��������F�^�񏳔F�T�[�r�X
        Services.addInterceptor(WEB3AdminEquityForcedSettleOrderApproveService.class,
            new WEB3AdminPMEquityServiceInterceptor());

        //�������ϊ����������������쐬�ꌏ�T�[�r�X
        Services.addInterceptor(WEB3AdminEquityForcedSettleCloseDateTempOrderCreateUnitService.class,
            new WEB3AdminEquityForcedSettleTempOrderCreateUnitServiceInterceptor());

        //�������ϕۏ؋��ێ�������i�I�����C���J�n�O�j�������쐬�ꌏ�T�[�r�X
        Services.addInterceptor(WEB3AdminEquityForcedSettleBelowMarginRateBefOnlineTempOrderCreateUnitService.class,
            new WEB3AdminEquityForcedSettleTempOrderCreateUnitServiceInterceptor());

        //�Ǘ��ҁE�����iPTS�j�o�����̓T�[�r�X
        Services.addInterceptor(WEB3AdminEquityPTSInputExecService.class,
            new WEB3AdminEquityPTSInputExecServiceInterceptor());

        //�Ǘ��ҁE�����iPTS�j�o������T�[�r�X
        Services.addInterceptor(WEB3AdminEquityPTSCancelExecService.class,
            new WEB3AdminEquityPTSCancelExecServiceInterceptor());

        //�������ϕۏ؋��ێ�������i��ԁj�������쐬�ꌏ�T�[�r�X
        Services.addInterceptor(WEB3AdminEquityForcedSettleBelowMarginRateIntermissionTempOrderCreateUnitService.class,
            new WEB3AdminEquityForcedSettleTempOrderCreateUnitServiceInterceptor());

        //�Ǘ��ҁE�������ω��������F�^�񏳔F�ꌏ�T�[�r�X
        Services.addInterceptor(WEB3AdminEquityForcedSettleOrderApproveUnitService.class,
            new WEB3AdminEquityForcedSettleOrderApproveUnitServiceInterceptor());
        
        //�Ǘ��Ғ��ӏ��Ɖ�T�[�r�X
        Services.addInterceptor(WEB3AdminEquityAttentionInfoReferenceService.class,
            new WEB3AdminPMEquityServiceInterceptor());

        //���ӏ��ʒm�ꌏ�T�[�r�X
        Services.addInterceptor(WEB3AdminEquityAttentionInfoNotifyUnitService.class,
            new WEB3AdminEquityAttentionInfoNotifyUnitServiceInterceptor());

        // �T�[�r�X�Ăяo���O���
        // �����J�n�����Ə����I�����������O�o�͂���悤�ɐݒ肷��
        // �Ǘ��Ҍڋq�����ʎ����~�o�^�T�[�r�X
        Services.addInterceptor(WEB3AdminEquityAccProductTradeStopRegistService.class,
            new WEB3LogSysTimeInterceptor());

        // �Ǘ��Ҍڋq�����ʎ����~�ύX�T�[�r�X
        Services.addInterceptor(WEB3AdminEquityAccProductTradeStopChangeService.class,
            new WEB3LogSysTimeInterceptor());

        // �Ǘ��Ҍڋq�����ʎ����~�ꗗ�T�[�r�X
        Services.addInterceptor(WEB3AdminEquityAccProductTradeStopListService.class,
            new WEB3LogSysTimeInterceptor());

        // �Ǘ��Ҋ������������\��ꗗ�T�[�r�X
        Services.addInterceptor(WEB3AdminEquityProductCondScheduleService.class,
            new WEB3LogSysTimeInterceptor());

        // �Ǘ��Ҍڋq�����ʎ����~�폜�T�[�r�X
        Services.addInterceptor(WEB3AdminEquityAccProductTradeStopDeleteService.class,
            new WEB3LogSysTimeInterceptor());

        // �Ǘ��Ҋ������������Ɖ�T�[�r�X
        Services.addInterceptor(WEB3AdminEquityProductCondReferenceService.class,
            new WEB3LogSysTimeInterceptor());

        // �Ǘ��җ���O���������ꗗ�T�[�r�X
        Services.addInterceptor(WEB3AdminOffFloorProductListService.class,
            new WEB3LogSysTimeInterceptor());

        // �Ǘ��җ���O���������X�V�T�[�r�X
        Services.addInterceptor(WEB3AdminOffFloorChangeService.class,
            new WEB3LogSysTimeInterceptor());

        // �Ǘ��җ���O���������폜�T�[�r�X
        Services.addInterceptor(WEB3AdminOffFloorDeleteService.class,
            new WEB3LogSysTimeInterceptor());

        // �Ǘ��җ���O���������o�^�T�[�r�X
        Services.addInterceptor(WEB3AdminOffFloorRegistService.class,
            new WEB3LogSysTimeInterceptor());

        // �Ǘ��Ҋ������������ݒ�T�[�r�X
        Services.addInterceptor(WEB3AdminEquityProductCondSettingService.class,
            new WEB3LogSysTimeInterceptor());
            
        // �Ǘ��҃t�����g�������ʃT�[�r�X
        Services.addInterceptor(WEB3AdminFrontOrderCommonService.class,
            new WEB3LogSysTimeInterceptor());

        // �Ǘ��Ғʒm�����Q�ƃT�[�r�X
        Services.addInterceptor(WEB3AdminFrontNoticeHistoryService.class,
            new WEB3LogSysTimeInterceptor());
        
        //�Ǘ��ҁE�����蓮�����T�[�r�X
        Services.addInterceptor(WEB3AdminEquityManualExpireService.class,
            new WEB3LogSysTimeInterceptor());  
        
        //�Ǘ��ҁE�����蓮�������C���T�[�r�X
        Services.addInterceptor(WEB3AdminEquityManualExpireMainService.class,
            new WEB3LogSysTimeInterceptor());  

        //�Ǘ��ҁE�������ω��������F�^�񏳔F�T�[�r�X
        Services.addInterceptor(WEB3AdminEquityForcedSettleOrderApproveService.class,
            new WEB3LogSysTimeInterceptor());

        //�Ǘ��ҁE�������ω��������F�^�񏳔F���C���T�[�r�X
        Services.addInterceptor(WEB3AdminEquityForcedSettleOrderApproveMainService.class,
            new WEB3LogSysTimeInterceptor());

        //�Ǘ��ҁE�������ω��������F�^�񏳔F�ꌏ�T�[�r�X
        Services.addInterceptor(WEB3AdminEquityForcedSettleOrderApproveUnitService.class,
            new WEB3LogSysTimeInterceptor());

        //�Ǘ��ҁE�������ϒ����Ɖ�T�[�r�X
        Services.addInterceptor(WEB3AdminEquityForcedSettleReferenceService.class,
            new WEB3LogSysTimeInterceptor());

        //�������ω������쐬�T�[�r�X
        Services.addInterceptor(WEB3AdminEquityForcedSettleTempOrderCreateService.class,
            new WEB3LogSysTimeInterceptor());

        //�������ϊ����������������쐬�ꌏ�T�[�r�X
        Services.addInterceptor(WEB3AdminEquityForcedSettleCloseDateTempOrderCreateUnitService.class,
            new WEB3LogSysTimeInterceptor());

        //�������ϕۏ؋��ێ�������i�I�����C���J�n�O�j�������쐬�ꌏ�T�[�r�X
        Services.addInterceptor(WEB3AdminEquityForcedSettleBelowMarginRateBefOnlineTempOrderCreateUnitService.class,
            new WEB3LogSysTimeInterceptor());

        //�Ǘ��ҁE�����iPTS�j�o�����̓T�[�r�X
        Services.addInterceptor(WEB3AdminEquityPTSInputExecService.class,
            new WEB3LogSysTimeInterceptor());

        //�Ǘ��ҁE�����iPTS�j�o������T�[�r�X
        Services.addInterceptor(WEB3AdminEquityPTSCancelExecService.class,
            new WEB3LogSysTimeInterceptor());

        //�������ϕۏ؋��ێ�������i��ԁj�������쐬�ꌏ�T�[�r�X
        Services.addInterceptor(WEB3AdminEquityForcedSettleBelowMarginRateIntermissionTempOrderCreateUnitService.class,
            new WEB3LogSysTimeInterceptor());

        //�Ǘ��ҁE�������ϒ���DL�T�[�r�X
        Services.addInterceptor(WEB3AdminEquityForcedSettleOrderDLService.class,
            new WEB3LogSysTimeInterceptor());

        //�Ǘ��Ғ��ӏ��Ɖ�T�[�r�X
        Services.addInterceptor(WEB3AdminEquityAttentionInfoReferenceService.class,
            new WEB3LogSysTimeInterceptor());

        //���ӏ��ʒm�T�[�r�X
        Services.addInterceptor(WEB3AdminEquityAttentionInfoNotifyService.class,
            new WEB3LogSysTimeInterceptor());

        //���ӏ��ʒm�ꌏ�T�[�r�X
        Services.addInterceptor(WEB3AdminEquityAttentionInfoNotifyUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // �����g�����U�N�V�����ݒ�
        // �Ǘ��Ҍڋq�����ʎ����~�o�^�T�[�r�X
        Services.addInterceptor(WEB3AdminEquityAccProductTradeStopRegistService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));
            
        // �Ǘ��Ҍڋq�����ʎ����~�ύX�T�[�r�X
        Services.addInterceptor(WEB3AdminEquityAccProductTradeStopChangeService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        // �Ǘ��Ҍڋq�����ʎ����~�ꗗ�T�[�r�X
        Services.addInterceptor(WEB3AdminEquityAccProductTradeStopListService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        // �Ǘ��Ҋ������������\��ꗗ�T�[�r�X
        Services.addInterceptor(
        WEB3AdminEquityProductCondScheduleService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        // �Ǘ��Ҍڋq�����ʎ����~�폜�T�[�r�X
        Services.addInterceptor(WEB3AdminEquityAccProductTradeStopDeleteService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        // �Ǘ��Ҋ������������Ɖ�T�[�r�X
        Services.addInterceptor(WEB3AdminEquityProductCondReferenceService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        // �Ǘ��җ���O���������ꗗ�T�[�r�X
        Services.addInterceptor(WEB3AdminOffFloorProductListService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        // �Ǘ��җ���O���������X�V�T�[�r�X
        Services.addInterceptor(WEB3AdminOffFloorChangeService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        // �Ǘ��җ���O���������폜�T�[�r�X
        Services.addInterceptor(WEB3AdminOffFloorDeleteService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        // �Ǘ��җ���O���������o�^�T�[�r�X
        Services.addInterceptor(WEB3AdminOffFloorRegistService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        // �Ǘ��Ҋ������������ݒ�T�[�r�X
        Services.addInterceptor(WEB3AdminEquityProductCondSettingService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));
            
        // �Ǘ��҃t�����g�������ʃT�[�r�X
        Services.addInterceptor(WEB3AdminFrontOrderCommonService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));            

        // �Ǘ��Ғʒm�����Q�ƃT�[�r�X
        Services.addInterceptor(WEB3AdminFrontNoticeHistoryService.class,
        new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));
        
        //�Ǘ��ҁE�����蓮�����T�[�r�X
        Services.addInterceptor(WEB3AdminEquityManualExpireService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //�Ǘ��ҁE�����蓮�������C���T�[�r�X
        Services.addInterceptor(WEB3AdminEquityManualExpireMainService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //�Ǘ��ҁE�������ω��������F�^�񏳔F�T�[�r�X
        Services.addInterceptor(WEB3AdminEquityForcedSettleOrderApproveService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�Ǘ��ҁE�������ω��������F�^�񏳔F���C���T�[�r�X
        Services.addInterceptor(WEB3AdminEquityForcedSettleOrderApproveMainService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //�Ǘ��ҁE�������ω��������F�^�񏳔F�ꌏ�T�[�r�X
        Services.addInterceptor(WEB3AdminEquityForcedSettleOrderApproveUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //�Ǘ��ҁE�������ϒ����Ɖ�T�[�r�X
        Services.addInterceptor(WEB3AdminEquityForcedSettleReferenceService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�������ω������쐬�T�[�r�X
        Services.addInterceptor(WEB3AdminEquityForcedSettleTempOrderCreateService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //�������ϊ����������������쐬�ꌏ�T�[�r�X
        Services.addInterceptor(WEB3AdminEquityForcedSettleCloseDateTempOrderCreateUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //�������ϕۏ؋��ێ�������i�I�����C���J�n�O�j�������쐬�ꌏ�T�[�r�X
        Services.addInterceptor(WEB3AdminEquityForcedSettleBelowMarginRateBefOnlineTempOrderCreateUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //�Ǘ��ҁE�����iPTS�j�o�����̓T�[�r�X
        Services.addInterceptor(WEB3AdminEquityPTSInputExecService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //�Ǘ��ҁE�����iPTS�j�o������T�[�r�X
        Services.addInterceptor(WEB3AdminEquityPTSCancelExecService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //�������ϕۏ؋��ێ�������i��ԁj�������쐬�ꌏ�T�[�r�X
        Services.addInterceptor(WEB3AdminEquityForcedSettleBelowMarginRateIntermissionTempOrderCreateUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //�Ǘ��ҁE�������ϒ���DL�T�[�r�X
        Services.addInterceptor(WEB3AdminEquityForcedSettleOrderDLService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�Ǘ��Ғ��ӏ��Ɖ�T�[�r�X
        Services.addInterceptor(WEB3AdminEquityAttentionInfoReferenceService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //���ӏ��ʒm�T�[�r�X
        Services.addInterceptor(WEB3AdminEquityAttentionInfoNotifyService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //���ӏ��ʒm�ꌏ�T�[�r�X
        Services.addInterceptor(WEB3AdminEquityAttentionInfoNotifyUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        // MQ-Gateway�C���^�Z�v�^�̐ݒ�
        //�Ǘ��ҁE�������ω��������F�^�񏳔F�ꌏ�T�[�r�X
        Services.addInterceptor(WEB3AdminEquityForcedSettleOrderApproveUnitService.class,
            new WEB3MQGatewayInterceptor());

        // Message �̓o�^
        // �Ǘ��ҁE�ڋq�����ʎ����~�o�^���̓��N�G�X�g�E���X�|���X
        regClass(WEB3AdminPMAccProductTradeStopRegistInputRequest.class);
        regClass(WEB3AdminPMAccProductTradeStopRegistInputResponse.class);

        // �Ǘ��ҁE�ڋq�����ʎ����~�o�^�m�F���N�G�X�g�E���X�|���X
        regClass(WEB3AdminPMAccProductTradeStopRegistConfirmRequest.class);
        regClass(WEB3AdminPMAccProductTradeStopRegistConfirmResponse.class);

        // �Ǘ��ҁE�ڋq�����ʎ����~�o�^�������N�G�X�g�E���X�|���X
        regClass(WEB3AdminPMAccProductTradeStopRegistCompleteRequest.class);
        regClass(WEB3AdminPMAccProductTradeStopRegistCompleteResponse.class);

        // �Ǘ��ҁE�ڋq�����ʎ����~�ύX���̓��N�G�X�g�E���X�|���X
        regClass(WEB3AdminPMAccProductTradeStopModifyInputRequest.class);
        regClass(WEB3AdminPMAccProductTradeStopModifyInputResponse.class);

        // �Ǘ��ҁE�ڋq�����ʎ����~�ύX�m�F���N�G�X�g�E���X�|���X
        regClass(WEB3AdminPMAccProductTradeStopModifyConfirmRequest.class);
        regClass(WEB3AdminPMAccProductTradeStopModifyConfirmResponse.class);

        // �Ǘ��ҁE�ڋq�����ʎ����~�ύX�������N�G�X�g�E���X�|���X
        regClass(WEB3AdminPMAccProductTradeStopModifyCompleteRequest.class);
        regClass(WEB3AdminPMAccProductTradeStopModifyCompleteResponse.class);

        // �Ǘ��ҁE�ڋq�����ʎ����~�ꗗ���N�G�X�g�E���X�|���X
        regClass(WEB3AdminPMAccProductTradeStopListRequest.class);
        regClass(WEB3AdminPMAccProductTradeStopListResponse.class);

        // �Ǘ��ҁE�������������\��ꗗ���̓��N�G�X�g�E���X�|���X
        regClass(WEB3AdminPMProductCondListInputRequest.class);
        regClass(WEB3AdminPMProductCondListInputResponse.class);
        
        // �Ǘ��ҁE�������������\��ꗗ���N�G�X�g�E���X�|���X
        regClass(WEB3AdminPMProductCondListRequest.class);
        regClass(WEB3AdminPMProductCondListResponse.class);

        // �Ǘ��ҁE�ڋq�����ʎ����~�폜�m�F���N�G�X�g�E���X�|���X
        regClass(WEB3AdminPMAccProductTradeStopDeleteConfirmRequest.class);
        regClass(WEB3AdminPMAccProductTradeStopDeleteConfirmResponse.class);

        // �Ǘ��ҁE�ڋq�����ʎ����~�폜�������N�G�X�g�E���X�|���X
        regClass(WEB3AdminPMAccProductTradeStopDeleteCompleteRequest.class);
        regClass(WEB3AdminPMAccProductTradeStopDeleteCompleteResponse.class);

        // �Ǘ��ҁE�������������Ɖ�������̓��N�G�X�g�E���X�|���X
        regClass(WEB3AdminPMProductCondRefInputRequest.class);
        regClass(WEB3AdminPMProductCondRefInputResponse.class);

        // �Ǘ��ҁE�������������Ɖ�N�G�X�g�E���X�|���X
        regClass(WEB3AdminPMProductCondRefReferenceRequest.class);
        regClass(WEB3AdminPMProductCondRefReferenceResponse.class);

        // �Ǘ��ҁE����O���������ꗗ���N�G�X�g�E���X�|���X
        regClass(WEB3AdminOffFloorProductListRequest.class);
        regClass(WEB3AdminOffFloorProductListResponse.class);

        // �Ǘ��ҁE����O���������X�V���̓��N�G�X�g�E���X�|���X
        regClass(WEB3AdminOffFloorChangeInputRequest.class);
        regClass(WEB3AdminOffFloorChangeInputResponse.class);

        // �Ǘ��ҁE����O���������X�V�m�F���N�G�X�g�E���X�|���X
        regClass(WEB3AdminOffFloorChangeConfirmRequest.class);
        regClass(WEB3AdminOffFloorChangeConfirmResponse.class);

        // �Ǘ��ҁE����O���������X�V�������N�G�X�g�E���X�|���X
        regClass(WEB3AdminOffFloorChangeCompleteRequest.class);
        regClass(WEB3AdminOffFloorChangeCompleteResponse.class);

        // �Ǘ��ҁE����O���������폜�m�F���N�G�X�g�E���X�|���X
        regClass(WEB3AdminOffFloorDeleteConfirmRequest.class);
        regClass(WEB3AdminOffFloorDeleteConfirmResponse.class);

        // �Ǘ��ҁE����O���������폜�������N�G�X�g�E���X�|���X
        regClass(WEB3AdminOffFloorDeleteCompleteRequest.class);
        regClass(WEB3AdminOffFloorDeleteCompleteResponse.class);

        // �Ǘ��ҁE����O���������V�K�o�^���̓��N�G�X�g�E���X�|���X
        regClass(WEB3AdminOffFloorRegistInputRequest.class);
        regClass(WEB3AdminOffFloorRegistInputResponse.class);

        // �Ǘ��ҁE����O���������V�K�o�^�������N�G�X�g�E���X�|���X
        regClass(WEB3AdminOffFloorRegistCompleteRequest.class);
        regClass(WEB3AdminOffFloorRegistCompleteResponse.class);

        // �Ǘ��ҁE����O���������V�K�o�^�m�F���N�G�X�g�E���X�|���X
        regClass(WEB3AdminOffFloorRegistConfirmRequest.class);
        regClass(WEB3AdminOffFloorRegistConfirmResponse.class);

        // �Ǘ��ҁE�������������ݒ���̓��N�G�X�g�E���X�|���X
        regClass(WEB3AdminPMProductCondConfInputRequest.class);
        regClass(WEB3AdminPMProductCondConfInputResponse.class);

        // �Ǘ��ҁE�������������ݒ芮�����N�G�X�g�E���X�|���X
        regClass(WEB3AdminPMProductCondConfCompleteRequest.class);
        regClass(WEB3AdminPMProductCondConfCompleteResponse.class);

        // �Ǘ��ҁE�������������ݒ�m�F���N�G�X�g�E���X�|���X
        regClass(WEB3AdminPMProductCondConfConfirmRequest.class);
        regClass(WEB3AdminPMProductCondConfConfirmResponse.class);

        // �Ǘ��ҁE�ʒm�����Q�ƃ��N�G�X�g�E���X�|���X
        regClass(WEB3AdminFrontNoticeHistoryReferenceRequest.class);
        regClass(WEB3AdminFrontNoticeHistoryReferenceResponse.class);
        
        // �Ǘ��ҁE�ʒm������̓��N�G�X�g�E���X�|���X
        regClass(WEB3AdminFrontNoticeHistoryInputRequest.class);
        regClass(WEB3AdminFrontNoticeHistoryInputResponse.class);
        
        //�Ǘ��ҁE�����蓮�������̓��N�G�X�g�E���X�|���X 
        regClass(WEB3AdminEquityManualLapseInputRequest.class);
        regClass(WEB3AdminEquityManualLapseInputResponse.class);
        
        //�Ǘ��ҁE�����蓮�����m�F���N�G�X�g�E���X�|���X 
        regClass(WEB3AdminEquityManualLapseConfirmRequest.class);
        regClass(WEB3AdminEquityManualLapseConfirmResponse.class);
        
        //�Ǘ��ҁE�����蓮���������N�����N�G�X�g�E���X�|���X 
        regClass(WEB3AdminEquityManualLapseRunRequest.class);
        regClass(WEB3AdminEquityManualLapseRunResponse.class);
        
        //�Ǘ��ҁE�����蓮���������X�e�[�^�X�m�F���N�G�X�g�E���X�|���X 
        regClass(WEB3AdminEquityManualLapseStatusRequest.class);
        regClass(WEB3AdminEquityManualLapseStatusResponse.class);
        
        //�Ǘ��ҁE�����蓮�������C�����N�G�X�g�E���X�|���X 
        regClass(WEB3AdminEquityManualLapseMainRequest.class);
        regClass(WEB3AdminEquityManualLapseMainResponse.class);

        //�Ǘ��ҁE�������ω��������F�^�񏳔F���C�����N�G�X�g�E���X�|���X
        regClass(WEB3AdminEquityForcedSettleOrderApproveMainRequest.class);
        regClass(WEB3AdminEquityForcedSettleOrderApproveMainResponse.class);

        //�Ǘ��ҁE�������ω��������F�^�񏳔F�m�F���N�G�X�g�E���X�|���X
        regClass(WEB3AdminForcedSettleApproveConfirmRequest.class);
        regClass(WEB3AdminForcedSettleApproveConfirmResponse.class);

        //�Ǘ��ҁE�������ω��������F�^�񏳔F�����N�����N�G�X�g�E���X�|���X
        regClass(WEB3AdminForcedSettleApproveRunRequest.class);
        regClass(WEB3AdminForcedSettleApproveRunResponse.class);

        //�Ǘ��ҁE�������ω��������F�^�񏳔F�����X�e�[�^�X�m�F���N�G�X�g�E���X�|���X
        regClass(WEB3AdminForcedSettleApproveStatusRequest.class);
        regClass(WEB3AdminForcedSettleApproveStatusResponse.class);

        //�Ǘ��ҁE�������ϒ����Ɖ���̓��N�G�X�g�E���X�|���X
        regClass(WEB3AdminForcedSettleRefInputRequest.class);
        regClass(WEB3AdminForcedSettleRefInputResponse.class);

        //�Ǘ��ҁE�������ϒ����Ɖ�N�G�X�g�E���X�|���X
        regClass(WEB3AdminForcedSettleReferenceRequest.class);
        regClass(WEB3AdminForcedSettleReferenceResponse.class);

        //�������ω������쐬���N�G�X�g�E���X�|���X
        regClass(WEB3AdminEquityForcedSettleTempOrderCreateRequest.class);
        regClass(WEB3AdminEquityForcedSettleTempOrderCreateResponse.class);

        //�Ǘ��ҁE����(PTS)�o�����͎�����ʃ��N�G�X�g
        regClass(WEB3AdminEquityPTSInputCancelExecCommonRequest.class);
        regClass(WEB3AdminEquityPTSInputCancelExecCommonResponse.class);

        //�Ǘ��ҁE����(PTS)�o�����̓��N�G�X�g�E���X�|���X
        regClass(WEB3AdminEquityPTSInputExecInputRequest.class);
        regClass(WEB3AdminEquityPTSInputExecInputResponse.class);

        //�Ǘ��ҁE����(PTS)�o�����͊m�F���N�G�X�g�E���X�|���X
        regClass(WEB3AdminEquityPTSInputExecConfirmRequest.class);
        regClass(WEB3AdminEquityPTSInputExecConfirmResponse.class);

        //�Ǘ��ҁE�����iPTS�j�o�����͊������N�G�X�g�E���X�|���X
        regClass(WEB3AdminEquityPTSInputExecCompleteRequest.class);
        regClass(WEB3AdminEquityPTSInputExecCompleteResponse.class);

        //�Ǘ��ҁE�����iPTS�j�o��������̓��N�G�X�g�E���X�|���X
        regClass(WEB3AdminEquityPTSCancelExecInputRequest.class);
        regClass(WEB3AdminEquityPTSCancelExecInputResponse.class);
        
        //�Ǘ��ҁE�����iPTS�j�o������m�F���N�G�X�g�E���X�|���X
        regClass(WEB3AdminEquityPTSCancelExecConfirmRequest.class);
        regClass(WEB3AdminEquityPTSCancelExecConfirmResponse.class);

        //�Ǘ��ҁE�����iPTS�j�o������������N�G�X�g�E���X�|���X
        regClass(WEB3AdminEquityPTSCancelExecCompleteRequest.class);
        regClass(WEB3AdminEquityPTSCancelExecCompleteResponse.class);

        //�Ǘ��ҁE�������ϒ����_�E�����[�h���̓��N�G�X�g�E���X�|���X
        regClass(WEB3AdminForcedSettleDownloadInputRequest.class);
        regClass(WEB3AdminForcedSettleDownloadInputResponse.class);

        //�Ǘ��ҁE�������ϒ����_�E�����[�h���N�G�X�g�E���X�|���X
        regClass(WEB3AdminForcedSettleDownloadRequest.class);
        regClass(WEB3AdminForcedSettleDownloadResponse.class);
 
        //�Ǘ��ҁE���ӏ��Ɖ���̓��N�G�X�g�E���X�|���X
        regClass(WEB3AdminEqAttentionInfoRefInpRequest.class);
        regClass(WEB3AdminEqAttentionInfoRefInpResponse.class);

        //�Ǘ��ҁE���ӏ��Ɖ�N�G�X�g�E���X�|���X
        regClass(WEB3AdminEqAttentionInfoRefRefRequest.class);
        regClass(WEB3AdminEqAttentionInfoRefRefResponse.class);

        //���ӏ��ʒm���N�G�X�g�E���X�|���X
        regClass(WEB3AdminEquityAttentionInfoNotifyRequest.class);
        regClass(WEB3AdminEquityAttentionInfoNotifyResponse.class);

        // �n���h���[�̓o�^
        // �Ǘ��Ҍڋq�����ʎ����~�o�^�n���h��
        regHandler(WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminPMAccProductTradeStopRegistInputRequest.class,
            WEB3AdminEquityAccProductTradeStopRegistHandler.class,
            "getInputScreen");
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminPMAccProductTradeStopRegistConfirmRequest.class,
            WEB3AdminEquityAccProductTradeStopRegistHandler.class,
            "confirmRegist");
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminPMAccProductTradeStopRegistCompleteRequest.class,
            WEB3AdminEquityAccProductTradeStopRegistHandler.class,
            "completeRegist");

        // �Ǘ��Ҍڋq�����ʎ����~�ύX�n���h��
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminPMAccProductTradeStopModifyInputRequest.class,
            WEB3AdminEquityAccProductTradeStopChangeHandler.class,
            "getInputScreen");
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminPMAccProductTradeStopModifyConfirmRequest.class,
            WEB3AdminEquityAccProductTradeStopChangeHandler.class,
            "confirmChange");
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminPMAccProductTradeStopModifyCompleteRequest.class,
            WEB3AdminEquityAccProductTradeStopChangeHandler.class,
            "completeChange");

        // �Ǘ��Ҍڋq�����ʎ����~�ꗗ�n���h��
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminPMAccProductTradeStopListRequest.class,
            WEB3AdminEquityAccProductTradeStopListHandler.class,
            "getListScreen");

        // �Ǘ��Ҋ������������\��ꗗ�n���h��
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminPMProductCondListInputRequest.class,
            WEB3AdminEquityProductCondScheduleHandler.class,
            "getInputScreen");

        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminPMProductCondListRequest.class,
            WEB3AdminEquityProductCondScheduleHandler.class,
            "getSchedule");

        // �Ǘ��Ҍڋq�����ʎ����~�폜�n���h��
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminPMAccProductTradeStopDeleteConfirmRequest.class,
            WEB3AdminEquityAccProductTradeStopDeleteHandler.class,
            "confirmDelete");

        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminPMAccProductTradeStopDeleteCompleteRequest.class,
            WEB3AdminEquityAccProductTradeStopDeleteHandler.class,
            "completeDelete");

        // �Ǘ��Ҋ������������Ɖ�n���h��
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminPMProductCondRefInputRequest.class,
            WEB3AdminEquityProductCondReferenceHandler.class,
            "getProductInputScreen");

        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminPMProductCondRefReferenceRequest.class,
            WEB3AdminEquityProductCondReferenceHandler.class,
            "getReferenceScreen");

        // �Ǘ��җ���O���������ꗗ�n���h��
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminOffFloorProductListRequest.class,
            WEB3AdminOffFloorProductListHandler.class,
            "getProductList");

        // �Ǘ��җ���O���������X�V�n���h��
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminOffFloorChangeInputRequest.class,
            WEB3AdminOffFloorChangeHandler.class,
            "getInputScreen");

        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminOffFloorChangeConfirmRequest.class,
            WEB3AdminOffFloorChangeHandler.class,
            "validateChange");
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminOffFloorChangeCompleteRequest.class,
            WEB3AdminOffFloorChangeHandler.class,
            "submitChange");

        // �Ǘ��җ���O���������폜�n���h��
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminOffFloorDeleteConfirmRequest.class,
            WEB3AdminOffFloorDeleteHandler.class,
            "validateDelete");
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminOffFloorDeleteCompleteRequest.class,
            WEB3AdminOffFloorDeleteHandler.class,
            "submitDelete");

        // �Ǘ��җ���O���������V�K�o�^�n���h��
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminOffFloorRegistInputRequest.class,
            WEB3AdminOffFloorRegistHandler.class,
            "getInputScreen");
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminOffFloorRegistCompleteRequest.class,
            WEB3AdminOffFloorRegistHandler.class,
            "submitRegist");
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminOffFloorRegistConfirmRequest.class,
            WEB3AdminOffFloorRegistHandler.class,
            "validateRegist");

        // �Ǘ��Ҋ������������ݒ�n���h��
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminPMProductCondConfInputRequest.class,
            WEB3AdminEquityProductCondSettingHandler.class,
            "getConditionSettingInputScreen");

        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminPMProductCondConfConfirmRequest.class,
            WEB3AdminEquityProductCondSettingHandler.class,
            "confirmConditionSetting");

        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminPMProductCondConfCompleteRequest.class,
            WEB3AdminEquityProductCondSettingHandler.class,
            "completeConditionSetting");

        // �Ǘ��Ғʒm�����Q�ƃn���h��
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminFrontNoticeHistoryInputRequest.class,
            WEB3AdminFrontNoticeHistoryHandler.class,
            "getInputScreen");
            
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminFrontNoticeHistoryReferenceRequest.class,
            WEB3AdminFrontNoticeHistoryHandler.class,
            "getReferenceScreen");
        
        //�Ǘ��ҁE�����蓮�����n���h��
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminEquityManualLapseInputRequest.class,
            WEB3AdminEquityManualExpireHandler.class,
            "getInputScreen");
        
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminEquityManualLapseConfirmRequest.class,
            WEB3AdminEquityManualExpireHandler.class,
            "confirmManualExpire");
        
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminEquityManualLapseRunRequest.class,
            WEB3AdminEquityManualExpireHandler.class,
            "runManualExpire");
        
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminEquityManualLapseStatusRequest.class,
            WEB3AdminEquityManualExpireHandler.class,
            "confirmStatus");
        
        //�Ǘ��ҁE�����蓮�������C���n���h��
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminEquityManualLapseMainRequest.class,
            WEB3AdminEquityManualExpireMainHandler.class,
            "manualExpireRequest");

        //�Ǘ��ҁE�������ω��������F�^�񏳔F�n���h��
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminForcedSettleApproveConfirmRequest.class,
            WEB3AdminEquityForcedSettleOrderApproveHandler.class,
            "confirmApprove");

        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminForcedSettleApproveRunRequest.class,
            WEB3AdminEquityForcedSettleOrderApproveHandler.class,
            "runApprove");

        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminForcedSettleApproveStatusRequest.class,
            WEB3AdminEquityForcedSettleOrderApproveHandler.class,
            "confirmStatus");

        //�Ǘ��ҁE�������ω��������F�^�񏳔F���C���n���h��
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminEquityForcedSettleOrderApproveMainRequest.class,
            WEB3AdminEquityForcedSettleOrderApproveMainHandler.class,
            "approveRequest");

        //�������ω������쐬�n���h��
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminEquityForcedSettleTempOrderCreateRequest.class,
            WEB3AdminEquityForcedSettleTempOrderCreateHandler.class,
            "completeForcedSettleOrderCreate");

        //�Ǘ��ҁE�������ϒ����Ɖ�n���h��
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminForcedSettleRefInputRequest.class,
            WEB3AdminEquityForcedSettleReferenceHandler.class,
            "getInputScreen");

        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminForcedSettleReferenceRequest.class,
            WEB3AdminEquityForcedSettleReferenceHandler.class,
            "getReferenceScreen");

        //�Ǘ��ҁE�����iPTS�j�o�����̓n���h��
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminEquityPTSInputExecInputRequest.class,
            WEB3AdminEquityPTSInputExecHandler.class,
            "getInputScreen");

        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminEquityPTSInputExecConfirmRequest.class,
            WEB3AdminEquityPTSInputExecHandler.class,
            "validateInputExec");

        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminEquityPTSInputExecCompleteRequest.class,
            WEB3AdminEquityPTSInputExecHandler.class,
            "submitInputExec");

        //�Ǘ��ҁE�����iPTS�j�o������n���h��
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminEquityPTSCancelExecInputRequest.class,
            WEB3AdminEquityPTSCancelExecHandler.class,
            "getInputScreen");

        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminEquityPTSCancelExecConfirmRequest.class,
            WEB3AdminEquityPTSCancelExecHandler.class,
            "validateCancelExec");

        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminEquityPTSCancelExecCompleteRequest.class,
            WEB3AdminEquityPTSCancelExecHandler.class,
            "submitCancelExec");
   
        //�Ǘ��ҁE�������ϒ���DL�n���h��
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminForcedSettleDownloadInputRequest.class,
            WEB3AdminEquityForcedSettleOrderDLHandler.class,
            "getInputScreen");

        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminForcedSettleDownloadRequest.class,
            WEB3AdminEquityForcedSettleOrderDLHandler.class,
            "getDownloadFile");

        //�Ǘ��Ғ��ӏ��Ɖ�n���h��
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminEqAttentionInfoRefInpRequest.class,
            WEB3AdminEquityAttentionInfoReferenceHandler.class,
            "getInputScreen");

        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminEqAttentionInfoRefRefRequest.class,
            WEB3AdminEquityAttentionInfoReferenceHandler.class,
            "getReferenceScreen");

        //���ӏ��ʒm�n���h��
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminEquityAttentionInfoNotifyRequest.class,
            WEB3AdminEquityAttentionInfoNotifyHandler.class,
            "attentionInfoNotifyRequest");

        //RAC-CTX�C���^�Z�v�^�̐ݒ�
        //�Ǘ��ҁE�������ω��������F�^�񏳔F�ꌏ�T�[�r�X
        Services.addInterceptor(WEB3AdminEquityForcedSettleOrderApproveUnitService.class,
            new WEB3AdminEquityForcedSettleOrderApproveRacCtxInterceptor());

        //RAC-CTX�C���^�Z�v�^�̐ݒ�
        //�Ǘ��ҁE�������ϊ����������������쐬�ꌏ�T�[�r�X
        Services.addInterceptor(WEB3AdminEquityForcedSettleCloseDateTempOrderCreateUnitService.class,
            new WEB3AdminEquityForcedSettleOrderRacCtxInterceptor());
        //�Ǘ��ҁE�������ϕۏ؋��ێ�������i�I�����C���J�n�O�j�������쐬�ꌏ�T�[�r�X
        Services.addInterceptor(WEB3AdminEquityForcedSettleBelowMarginRateBefOnlineTempOrderCreateUnitService.class,
            new WEB3AdminEquityForcedSettleOrderRacCtxInterceptor());
        //�Ǘ��ҁE�������ϕۏ؋��ێ�������i��ԁj�������쐬�ꌏ�T�[�r�X
        Services.addInterceptor(WEB3AdminEquityForcedSettleBelowMarginRateIntermissionTempOrderCreateUnitService.class,
            new WEB3AdminEquityForcedSettleOrderRacCtxInterceptor());

        log.exiting(METHOD_NAME);
    }
}@
