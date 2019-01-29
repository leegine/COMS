head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.53.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : Webbroker3-Inform �v���O�C���N���X(WEB3InformAppPlugin.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/25 ������ (���u) �V�K�쐬
Revesion History : 2007/05/29 �Ӑ� (���u) ���f��No.045,046
Revesion History : 2007/06/06 ���G�� (���u) �����E���z���o�^�`�[�쐬�Ή�
Revesion History : 2007/07/23 ���� (���u) ���f��No.099
Revesion History : 2007/07/23 �����F (���u) ���f��No.098
Revesion History : 2007/07/30 ���� (���u) ���f��No.100
Revesion History : 2007/09/21 ��іQ (���u) ���f��No.110,112
Revesion History : 2008/02/25 �Ӑ� (���u) ���f��No.122,123,124,125,127
Revesion History : 2008/03/05 �đo�g (���u) ���f��No.128,129,130
Revesion History : 2009/06/29 �đo�g (���u) DB���C�A�E�gNo.013
*/

package webbroker3.inform;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;

import webbroker3.common.WEB3LogSysTimeInterceptor;
import webbroker3.inform.data.WEB3InformMasterDatabaseExtensions;
import webbroker3.inform.data.WEB3InformSessionDatabaseExtensions;
import webbroker3.inform.handler.WEB3AdminInformPTSAccOpenStateChangeHandler;
import webbroker3.inform.handler.WEB3AdminInformPTSAccountListHandler;
import webbroker3.inform.handler.WEB3AdminInformProfDistRegistVoucherMakeHandler;
import webbroker3.inform.handler.WEB3AdminInformProfDistSellTransSrcListHandler;
import webbroker3.inform.handler.WEB3AdminInformReferenceHandler;
import webbroker3.inform.handler.WEB3AdminInformSwElecDeliApplyHandler;
import webbroker3.inform.handler.WEB3InformAccSwElecDeliApplyHandler;
import webbroker3.inform.handler.WEB3InformInputHandler;
import webbroker3.inform.handler.WEB3InformPTSAccOpenApplyHandler;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyCmpRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyCmpResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyConfRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyConfResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyInpRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyInpResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyRefRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyRefResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplySrcRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplySrcResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliChangeCmpRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliChangeCmpResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliChangeConfRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliChangeConfResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliDeleteCmpRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliDeleteCmpResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliDeleteConfRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliDeleteConfResponse;
import webbroker3.inform.message.WEB3AdminInformDetailRequest;
import webbroker3.inform.message.WEB3AdminInformDetailResponse;
import webbroker3.inform.message.WEB3AdminInformDownLoadRequest;
import webbroker3.inform.message.WEB3AdminInformDownLoadResponse;
import webbroker3.inform.message.WEB3AdminInformInputRequest;
import webbroker3.inform.message.WEB3AdminInformInputResponse;
import webbroker3.inform.message.WEB3AdminInformListRequest;
import webbroker3.inform.message.WEB3AdminInformListResponse;
import webbroker3.inform.message.WEB3AdminInformPTSAccOpenStateChangeCmpRequest;
import webbroker3.inform.message.WEB3AdminInformPTSAccOpenStateChangeCmpResponse;
import webbroker3.inform.message.WEB3AdminInformPTSAccOpenStateChangeCnfRequest;
import webbroker3.inform.message.WEB3AdminInformPTSAccOpenStateChangeCnfResponse;
import webbroker3.inform.message.WEB3AdminInformPTSAccOpenStateChangeInpRequest;
import webbroker3.inform.message.WEB3AdminInformPTSAccOpenStateChangeInpResponse;
import webbroker3.inform.message.WEB3AdminInformPTSAccOpenStateChangeSrcRequest;
import webbroker3.inform.message.WEB3AdminInformPTSAccOpenStateChangeSrcResponse;
import webbroker3.inform.message.WEB3AdminInformPTSAccountListInquiryRequest;
import webbroker3.inform.message.WEB3AdminInformPTSAccountListInquiryResponse;
import webbroker3.inform.message.WEB3AdminInformPTSAccountListResultRequest;
import webbroker3.inform.message.WEB3AdminInformPTSAccountListResultResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistSellTransSrcInpRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistSellTransSrcInpResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistSellTransSrcListRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistSellTransSrcListResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistStatusSearchInputRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistStatusSearchInputResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherCancCmpRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherCancCmpResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherCancCnfRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherCancCnfResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherChgCmpRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherChgCmpResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherChgCnfRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherChgCnfResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherInfoRefRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherInfoRefResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherMakeCmpRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherMakeCmpResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherMakeCnfRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherMakeCnfResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherMakeInpRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherMakeInpResponse;
import webbroker3.inform.message.WEB3InformAccSwElecDeliApplyCmpRequest;
import webbroker3.inform.message.WEB3InformAccSwElecDeliApplyCmpResponse;
import webbroker3.inform.message.WEB3InformAccSwElecDeliApplyConfRequest;
import webbroker3.inform.message.WEB3InformAccSwElecDeliApplyConfResponse;
import webbroker3.inform.message.WEB3InformAccSwElecDeliApplyInpRequest;
import webbroker3.inform.message.WEB3InformAccSwElecDeliApplyInpResponse;
import webbroker3.inform.message.WEB3InformCompleteRequest;
import webbroker3.inform.message.WEB3InformCompleteResponse;
import webbroker3.inform.message.WEB3InformConfirmRequest;
import webbroker3.inform.message.WEB3InformConfirmResponse;
import webbroker3.inform.message.WEB3InformInputRequest;
import webbroker3.inform.message.WEB3InformInputResponse;
import webbroker3.inform.message.WEB3InformPTSAccOpenApplyCmpRequest;
import webbroker3.inform.message.WEB3InformPTSAccOpenApplyCmpResponse;
import webbroker3.inform.message.WEB3InformPTSAccOpenApplyCnfRequest;
import webbroker3.inform.message.WEB3InformPTSAccOpenApplyCnfResponse;
import webbroker3.inform.message.WEB3InformPTSAccOpenApplyInpRequest;
import webbroker3.inform.message.WEB3InformPTSAccOpenApplyInpResponse;
import webbroker3.inform.service.delegate.WEB3AdminInformPTSAccOpenStateChangeService;
import webbroker3.inform.service.delegate.WEB3AdminInformPTSAccountListService;
import webbroker3.inform.service.delegate.WEB3AdminInformProfDistRegistVoucherMakeService;
import webbroker3.inform.service.delegate.WEB3AdminInformProfDistSellTransSrcListService;
import webbroker3.inform.service.delegate.WEB3AdminInformReferenceService;
import webbroker3.inform.service.delegate.WEB3AdminInformSwElecDeliApplyService;
import webbroker3.inform.service.delegate.WEB3InformAccSwElecDeliApplyCommonService;
import webbroker3.inform.service.delegate.WEB3InformAccSwElecDeliApplyService;
import webbroker3.inform.service.delegate.WEB3InformHostReqOrderNumberManageService;
import webbroker3.inform.service.delegate.WEB3InformInputService;
import webbroker3.inform.service.delegate.WEB3InformPTSAccOpenApplyService;
import webbroker3.inform.service.delegate.stdimpls.WEB3AdminInformPTSAccOpenStateChangeServiceImpl;
import webbroker3.inform.service.delegate.stdimpls.WEB3AdminInformPTSAccountListServiceImpl;
import webbroker3.inform.service.delegate.stdimpls.WEB3AdminInformProfDistRegistVoucherMakeServiceImpl;
import webbroker3.inform.service.delegate.stdimpls.WEB3AdminInformProfDistSellTransSrcListServiceImpl;
import webbroker3.inform.service.delegate.stdimpls.WEB3AdminInformReferenceServiceImpl;
import webbroker3.inform.service.delegate.stdimpls.WEB3AdminInformSwElecDeliApplyServiceImpl;
import webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl;
import webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyServiceImpl;
import webbroker3.inform.service.delegate.stdimpls.WEB3InformHostReqOrderNumberManageServiceImpl;
import webbroker3.inform.service.delegate.stdimpls.WEB3InformInputServiceImpl;
import webbroker3.inform.service.delegate.stdimpls.WEB3InformPTSAccOpenApplyServiceImpl;
import webbroker3.mqgateway.WEB3MQGatewayInterceptor;
import webbroker3.util.WEB3LogUtility;

/**
 * Webbroker3-Inform �v���O�C���N���X�B
 * @@author ������
 * @@version 1.0
 */
public final class WEB3InformAppPlugin extends Plugin
{
    /**
     * ���O���[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3InformAppPlugin.class);

    /**
     * �R���X�g���N�^�B
     */
    public WEB3InformAppPlugin()
    {

    }

    /**
     * �v���O�C���G���g���[�|�C���g�B
     */
    public static void plug() throws Exception
    {
        String METHOD_NAME = "plug()";
        log.entering(METHOD_NAME);

        plug(WEB3InformAppPlugin.class);

        log.exiting(METHOD_NAME);
    }

    /**
     * �v���O�C�������B
     */
    public static void onPlug() throws Exception
    {
        String METHOD_NAME = "onPlug()";
        log.entering(METHOD_NAME);
        
        WEB3InformMasterDatabaseExtensions.plug();
        WEB3InformSessionDatabaseExtensions.plug();

        KernelPlugin.plug();

        // ---------------------- 1 Service �̓o�^���� ----------------------
        //�A�����̓T�[�r�X
        Services.registerService(
            WEB3InformInputService.class,
            new WEB3InformInputServiceImpl());

        //�A����񌟍��T�[�r�X
        Services.registerService(
            WEB3AdminInformReferenceService.class,
            new WEB3AdminInformReferenceServiceImpl());

        //�A���Ǘ����ʃR�[�h�̔ԃT�[�r�X
        Services.registerService(
            WEB3InformHostReqOrderNumberManageService.class,
            new WEB3InformHostReqOrderNumberManageServiceImpl());

        //�����E���z���E���p����U����ꗗ�T�[�r�X�C���^�[�t�F�C�X
        Services.registerService(WEB3AdminInformProfDistSellTransSrcListService.class,
            new WEB3AdminInformProfDistSellTransSrcListServiceImpl());

        //�����E���z���o�^�`�[�쐬�T�[�r�X�C���^�[�t�F�C�X
        Services.registerService(
            WEB3AdminInformProfDistRegistVoucherMakeService.class,
            new WEB3AdminInformProfDistRegistVoucherMakeServiceImpl());

        // �����ؑցE�d�q��t�\�����ʃT�[�r�X�C���^�[�t�F�C�X
        Services.registerService(
            WEB3InformAccSwElecDeliApplyCommonService.class,
            new WEB3InformAccSwElecDeliApplyCommonServiceImpl());

        //�Ǘ��Ҍ����ؑցE�d�q��t�\���T�[�r�X
        Services.registerService(
            WEB3AdminInformSwElecDeliApplyService.class,
            new WEB3AdminInformSwElecDeliApplyServiceImpl());

        //�����ؑցE�d�q��t�\���T�[�r�X
        Services.registerService(
            WEB3InformAccSwElecDeliApplyService.class,
            new WEB3InformAccSwElecDeliApplyServiceImpl());

        //PTS�����J�ݐ\���T�[�r�X
        Services.registerService(
            WEB3InformPTSAccOpenApplyService.class,
            new WEB3InformPTSAccOpenApplyServiceImpl());

        //�Ǘ���PTS�����J�ݏ󋵕ύX�T�[�r�X
        Services.registerService(
            WEB3AdminInformPTSAccOpenStateChangeService.class,
            new WEB3AdminInformPTSAccOpenStateChangeServiceImpl());

        //�Ǘ���PTS�\���q�ꗗ�⍇���T�[�r�X
        Services.registerService(
            WEB3AdminInformPTSAccountListService.class,
            new WEB3AdminInformPTSAccountListServiceImpl());


        // ---------------------- 2 Service.execute()�Ăяo���O���  ----------------------
        // �����J�n�����Ə����I�����������O�o�͂���悤�ɐݒ肷��
        //�A�����̓T�[�r�X
        Services.addInterceptor(
            WEB3InformInputService.class,
            new WEB3LogSysTimeInterceptor());

        //�A����񌟍��T�[�r�X
        Services.addInterceptor(
            WEB3AdminInformReferenceService.class,
            new WEB3LogSysTimeInterceptor());

        //�����E���z���E���p����U����ꗗ�T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(WEB3AdminInformProfDistSellTransSrcListService.class,
            new WEB3LogSysTimeInterceptor());

        //�����E���z���o�^�`�[�쐬�T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
            WEB3AdminInformProfDistRegistVoucherMakeService.class,
            new WEB3LogSysTimeInterceptor());

        //�Ǘ��Ҍ����ؑցE�d�q��t�\���T�[�r�X
        Services.addInterceptor(
            WEB3AdminInformSwElecDeliApplyService.class,
            new WEB3LogSysTimeInterceptor());

        //�����ؑցE�d�q��t�\���T�[�r�X
        Services.addInterceptor(
            WEB3InformAccSwElecDeliApplyService.class,
            new WEB3LogSysTimeInterceptor());

        //PTS�����J�ݐ\���T�[�r�X
        Services.addInterceptor(
            WEB3InformPTSAccOpenApplyService.class,
            new WEB3LogSysTimeInterceptor());

        //�Ǘ���PTS�����J�ݏ󋵕ύX�T�[�r�X
        Services.addInterceptor(
            WEB3AdminInformPTSAccOpenStateChangeService.class,
            new WEB3LogSysTimeInterceptor());

        //�Ǘ���PTS�\���q�ꗗ�⍇���T�[�r�X
        Services.addInterceptor(
            WEB3AdminInformPTSAccountListService.class,
            new WEB3LogSysTimeInterceptor());

        // ---------------------- 3 Service �� ServiceInterceptor ��ݒ肷�� ----------------------
        //�A�����̓T�[�r�X
        Services.addInterceptor(
            WEB3InformInputService.class,
            new WEB3InformInputServiceInterceptor());

        //�A����񌟍��T�[�r�X
        Services.addInterceptor(
            WEB3AdminInformReferenceService.class,
            new WEB3AdminInformReferenceServiceInterceptor());

        //�����E���z���E���p����U����ꗗ�T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(WEB3AdminInformProfDistSellTransSrcListService.class,
            new WEB3AdminInformProfDistSellTransSrcListServiceInterceptor());

        //�����E���z���o�^�`�[�쐬�T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
            WEB3AdminInformProfDistRegistVoucherMakeService.class,
            new WEB3AdminInformProfDistRegistVoucherMakeInterceptor());

        //�Ǘ��Ҍ����ؑցE�d�q��t�\���T�[�r�X
        Services.addInterceptor(
            WEB3AdminInformSwElecDeliApplyService.class,
            new WEB3AdminInformSwElecDeliApplyServiceInterceptor());

        //�����ؑցE�d�q��t�\���T�[�r�X
        Services.addInterceptor(
            WEB3InformAccSwElecDeliApplyService.class,
            new WEB3InformAccSwElecDeliApplyServiceInterceptor());

        //PTS�����J�ݐ\���T�[�r�X
        Services.addInterceptor(
            WEB3InformPTSAccOpenApplyService.class,
            new WEB3InformPTSAccOpenApplyServiceInterceptor());

        //�Ǘ���PTS�����J�ݏ󋵕ύX�T�[�r�X
        Services.addInterceptor(
            WEB3AdminInformPTSAccOpenStateChangeService.class,
            new WEB3AdminInformPTSAccOpenStateChangeServiceInterceptor());

        //�Ǘ���PTS�\���q�ꗗ�⍇���T�[�r�X
        Services.addInterceptor(
            WEB3AdminInformPTSAccountListService.class,
            new WEB3AdminInformPTSAccountListServiceInterceptor());

        // ---------------------- 4 Service �� Interceptor �ݒ菈�� ----------------------
        // �����g�����U�N�V�����ݒ�
        //�A�����̓T�[�r�X
        Services.addInterceptor(
            WEB3InformInputService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        //�A����񌟍��T�[�r�X
        Services.addInterceptor(
            WEB3AdminInformReferenceService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        //�A���Ǘ����ʃR�[�h�̔ԃT�[�r�X
        Services.addInterceptor(
            WEB3InformHostReqOrderNumberManageService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        //�����E���z���E���p����U����ꗗ�T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(WEB3AdminInformProfDistSellTransSrcListService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        //�����E���z���o�^�`�[�쐬�T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
            WEB3AdminInformProfDistRegistVoucherMakeService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        // �����ؑցE�d�q��t�\�����ʃT�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
            WEB3InformAccSwElecDeliApplyCommonService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        //�Ǘ��Ҍ����ؑցE�d�q��t�\���T�[�r�X
        Services.addInterceptor(
            WEB3AdminInformSwElecDeliApplyService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        //�����ؑցE�d�q��t�\���T�[�r�X
        Services.addInterceptor(
            WEB3InformAccSwElecDeliApplyService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        //PTS�����J�ݐ\���T�[�r�X
        Services.addInterceptor(
            WEB3InformPTSAccOpenApplyService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        //�Ǘ���PTS�����J�ݏ󋵕ύX�T�[�r�X
        Services.addInterceptor(
            WEB3AdminInformPTSAccOpenStateChangeService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        //�Ǘ���PTS�\���q�ꗗ�⍇���T�[�r�X
        Services.addInterceptor(
            WEB3AdminInformPTSAccountListService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        // ---------------------- 5 Message �̓o�^���� ----------------------
        //�A�����͗v�����N�G�X�g
        regClass(WEB3InformInputRequest.class);
        //�A�����͉������X�|���X
        regClass(WEB3InformInputResponse.class);

        //�A���m�F�v�����N�G�X�g
        regClass(WEB3InformConfirmRequest.class);
        //�A���m�F�������X�|���X
        regClass(WEB3InformConfirmResponse.class);

        //�A�������v�����N�G�X�g
        regClass(WEB3InformCompleteRequest.class);
        //�A�������������X�|���X
        regClass(WEB3InformCompleteResponse.class);

        //�A����񌟍����͗v�����N�G�X�g
        regClass(WEB3AdminInformInputRequest.class);
        //�A����񌟍����͉������X�|���X
        regClass(WEB3AdminInformInputResponse.class);

        //�A����񌟍��ڍחv�����N�G�X�g
        regClass(WEB3AdminInformDetailRequest.class);
        //�A����񌟍��ڍ׉������X�|���X
        regClass(WEB3AdminInformDetailResponse.class);

        //�A����񌟍��ꗗ�v�����N�G�X�g
        regClass(WEB3AdminInformListRequest.class);
        //�A����񌟍��ꗗ�������X�|���X
        regClass(WEB3AdminInformListResponse.class);

        //�A����񌟍��_�E�����[�h�v�����N�G�X�g
        regClass(WEB3AdminInformDownLoadRequest.class);
        //�A����񌟍��_�E�����[�h�������X�|���X
        regClass(WEB3AdminInformDownLoadResponse.class);
        
        //�����E���z���E���p����U���挟�����̓��N�G�X�g�N���X
        regClass(WEB3AdminInformProfDistSellTransSrcInpRequest.class);
        //�����E���z���E���p����U���挟�����̓��X�|���X�N���X
        regClass(WEB3AdminInformProfDistSellTransSrcInpResponse.class);

        //�����E���z���E���p����U����ꗗ���N�G�X�g�N���X
        regClass(WEB3AdminInformProfDistSellTransSrcListRequest.class);
        //�����E���z���E���p����U����ꗗ���X�|���X�N���X
        regClass(WEB3AdminInformProfDistSellTransSrcListResponse.class);

        //�Ǘ��ҁE�����E���z���o�^�󋵖⍇�����̓��N�G�X�g�N���X
        regClass(WEB3AdminInformProfDistStatusSearchInputRequest.class);
        //�Ǘ��ҁE�����E���z���o�^�󋵖⍇�����̓��X�|���X�N���X
        regClass(WEB3AdminInformProfDistStatusSearchInputResponse.class);
        //�Ǘ��ҁE�����`�[�쐬���̓��N�G�X�g�N���X
        regClass(WEB3AdminInformProfDistVoucherMakeInpRequest.class);
        //�Ǘ��ҁE�����`�[�쐬���̓��X�|���X�N���X
        regClass(WEB3AdminInformProfDistVoucherMakeInpResponse.class);
        //�Ǘ��ҁE�����`�[�쐬�m�F���N�G�X�g�N���X
        regClass(WEB3AdminInformProfDistVoucherMakeCnfRequest.class);
        //�Ǘ��ҁE�����`�[�쐬�m�F���X�|���X�N���X
        regClass(WEB3AdminInformProfDistVoucherMakeCnfResponse.class);
        //�Ǘ��ҁE�����`�[�쐬�������N�G�X�g
        regClass(WEB3AdminInformProfDistVoucherMakeCmpRequest.class);
        //�Ǘ��ҁE�����`�[�쐬�������X�|���X�N���X
        regClass(WEB3AdminInformProfDistVoucherMakeCmpResponse.class);
        //�Ǘ��ҁE�`�[���Q�ƃ��N�G�X�g�N���X
        regClass(WEB3AdminInformProfDistVoucherInfoRefRequest.class);
        //�Ǘ��ҁE�`�[���Q�ƃ��X�|���X�N���X
        regClass(WEB3AdminInformProfDistVoucherInfoRefResponse.class);
        //�Ǘ��ҁE�����`�[�ύX�m�F���N�G�X�g�N���X
        regClass(WEB3AdminInformProfDistVoucherChgCnfRequest.class);
        //�Ǘ��ҁE�����`�[�ύX�m�F���X�|���X�N���X
        regClass(WEB3AdminInformProfDistVoucherChgCnfResponse.class);
        //�Ǘ��ҁE�����`�[�ύX�������N�G�X�g�N���X
        regClass(WEB3AdminInformProfDistVoucherChgCmpRequest.class);
        //�Ǘ��ҁE�����`�[�ύX�������X�|���X�N���X
        regClass(WEB3AdminInformProfDistVoucherChgCmpResponse.class);
        //�Ǘ��ҁE�����`�[����m�F���N�G�X�g�N���X
        regClass(WEB3AdminInformProfDistVoucherCancCnfRequest.class);
        //�Ǘ��ҁE�����`�[����m�F���X�|���X�N���X
        regClass(WEB3AdminInformProfDistVoucherCancCnfResponse.class);
        //�Ǘ��ҁE�����`�[����������N�G�X�g�N���X
        regClass(WEB3AdminInformProfDistVoucherCancCmpRequest.class);
        //�Ǘ��ҁE�����`�[����������X�|���X�N���X
        regClass(WEB3AdminInformProfDistVoucherCancCmpResponse.class);

        //�����ؑցE�d�q��t�\�����̓��N�G�X�g�N���X
        regClass(WEB3InformAccSwElecDeliApplyInpRequest.class);
        //�����ؑցE�d�q��t�\�����̓��X�|���X�N���X
        regClass(WEB3InformAccSwElecDeliApplyInpResponse.class);
        //�����ؑցE�d�q��t�\���m�F���N�G�X�g�N���X
        regClass(WEB3InformAccSwElecDeliApplyConfRequest.class);
        //�����ؑցE�d�q��t�\���m�F���X�|���X�N���X
        regClass(WEB3InformAccSwElecDeliApplyConfResponse.class);
        //�����ؑցE�d�q��t�\���������N�G�X�g�N���X
        regClass(WEB3InformAccSwElecDeliApplyCmpRequest.class);
        //�����ؑցE�d�q��t�\���������X�|���X�N���X
        regClass(WEB3InformAccSwElecDeliApplyCmpResponse.class);
        //�Ǘ��ҁE�����ؑցE�d�q��t�\���������N�G�X�g�N���X
        regClass(WEB3AdminInformAccSwElecDeliApplySrcRequest.class);
        //�Ǘ��ҁE�����ؑցE�d�q��t�\���������X�|���X�N���X
        regClass(WEB3AdminInformAccSwElecDeliApplySrcResponse.class);
        //�Ǘ��ҁE�����ؑցE�d�q��t�\�����̓��N�G�X�g�N���X
        regClass(WEB3AdminInformAccSwElecDeliApplyInpRequest.class);
        //�Ǘ��ҁE�����ؑցE�d�q��t�\�����̓��X�|���X�N���X
        regClass(WEB3AdminInformAccSwElecDeliApplyInpResponse.class);
        //�Ǘ��ҁE�����ؑցE�d�q��t�\���m�F���N�G�X�g�N���X
        regClass(WEB3AdminInformAccSwElecDeliApplyConfRequest.class);
        //�Ǘ��ҁE�����ؑցE�d�q��t�\���m�F���X�|���X�N���X
        regClass(WEB3AdminInformAccSwElecDeliApplyConfResponse.class);
        //�Ǘ��ҁE�����ؑցE�d�q��t�\���������N�G�X�g�N���X
        regClass(WEB3AdminInformAccSwElecDeliApplyCmpRequest.class);
        //�Ǘ��ҁE�����ؑցE�d�q��t�\���������X�|���X�N���X
        regClass(WEB3AdminInformAccSwElecDeliApplyCmpResponse.class);

        //�Ǘ��ҁE�����ؑցE�d�q��t�\���ύX�m�F���N�G�X�g
        regClass(WEB3AdminInformAccSwElecDeliChangeConfRequest.class);

        //�Ǘ��ҁE�����ؑցE�d�q��t�\���ύX�m�F���X�|���X
        regClass(WEB3AdminInformAccSwElecDeliChangeConfResponse.class);

        //�Ǘ��ҁE�����ؑցE�d�q��t�\���ύX�������N�G�X�g
        regClass(WEB3AdminInformAccSwElecDeliChangeCmpRequest.class);

        //�Ǘ��ҁE�����ؑցE�d�q��t�\���ύX�������X�|���X
        regClass(WEB3AdminInformAccSwElecDeliChangeCmpResponse.class);

        //�Ǘ��ҁE�����ؑցE�d�q��t�\������m�F���N�G�X�g
        regClass(WEB3AdminInformAccSwElecDeliDeleteConfRequest.class);

        //�Ǘ��ҁE�����ؑցE�d�q��t�\������m�F���X�|���X
        regClass(WEB3AdminInformAccSwElecDeliDeleteConfResponse.class);

        //�Ǘ��ҁE�����ؑցE�d�q��t�\������������N�G�X�g
        regClass(WEB3AdminInformAccSwElecDeliDeleteCmpRequest.class);

        //�Ǘ��ҁE�����ؑցE�d�q��t�\������������X�|���X
        regClass(WEB3AdminInformAccSwElecDeliDeleteCmpResponse.class);

        //�Ǘ��ҁE�����ؑցE�d�q��t�\���Q�ƃ��N�G�X�g
        regClass(WEB3AdminInformAccSwElecDeliApplyRefRequest.class);

        //�Ǘ��ҁE�����ؑցE�d�q��t�\���Q�ƃ��X�|���X
        regClass(WEB3AdminInformAccSwElecDeliApplyRefResponse.class);

        //PTS�����J�ݐ\���������N�G�X�g
        regClass(WEB3InformPTSAccOpenApplyCmpRequest.class);
        //PTS�����J�ݐ\���������X�|���X
        regClass(WEB3InformPTSAccOpenApplyCmpResponse.class);
        //PTS�����J�ݐ\���m�F���N�G�X�g
        regClass(WEB3InformPTSAccOpenApplyCnfRequest.class);
        //PTS�����J�ݐ\���m�F���X�|���X
        regClass(WEB3InformPTSAccOpenApplyCnfResponse.class);
        //PTS�����J�ݐ\�����̓��N�G�X�g
        regClass(WEB3InformPTSAccOpenApplyInpRequest.class);
        //PTS�����J�ݐ\�����̓��X�|���X
        regClass(WEB3InformPTSAccOpenApplyInpResponse.class);

        //�Ǘ��ҁEPTS�����J�ݏ󋵕ύX
        //�Ǘ��ҁEPTS�����J�ݏ󋵕ύX�������N�G�X�g
        regClass(WEB3AdminInformPTSAccOpenStateChangeSrcRequest.class);
        //�Ǘ��ҁEPTS�����J�ݏ󋵕ύX�������X�|���X
        regClass(WEB3AdminInformPTSAccOpenStateChangeSrcResponse.class);
        //�Ǘ��ҁEPTS�����J�ݏ󋵕ύX���̓��N�G�X�g
        regClass(WEB3AdminInformPTSAccOpenStateChangeInpRequest.class);
        //�Ǘ��ҁEPTS�����J�ݏ󋵕ύX���̓��X�|���X
        regClass(WEB3AdminInformPTSAccOpenStateChangeInpResponse.class);
        //�Ǘ��ҁEPTS�����J�ݏ󋵕ύX�m�F���N�G�X�g
        regClass(WEB3AdminInformPTSAccOpenStateChangeCnfRequest.class);
        //�Ǘ��ҁEPTS�����J�ݏ󋵕ύX�m�F���X�|���X
        regClass(WEB3AdminInformPTSAccOpenStateChangeCnfResponse.class);
        //�Ǘ��ҁEPTS�����J�ݏ󋵕ύX�������N�G�X�g
        regClass(WEB3AdminInformPTSAccOpenStateChangeCmpRequest.class);
        //�Ǘ��ҁEPTS�����J�ݏ󋵕ύX�������X�|���X
        regClass(WEB3AdminInformPTSAccOpenStateChangeCmpResponse.class);

        //�Ǘ��ҁEPTS�\���q�ꗗ�⍇��
        //�Ǘ��ҁEPTS�\���q�ꗗ�⍇���������N�G�X�g
        regClass(WEB3AdminInformPTSAccountListInquiryRequest.class);
        //�Ǘ��ҁEPTS�\���q�ꗗ�⍇���������X�|���X
        regClass(WEB3AdminInformPTSAccountListInquiryResponse.class);
        //�Ǘ��ҁEPTS�\���q�ꗗ�⍇���������ʃ��N�G�X�g
        regClass(WEB3AdminInformPTSAccountListResultRequest.class);
        //�Ǘ��ҁEPTS�\���q�ꗗ�⍇���������ʃ��X�|���X
        regClass(WEB3AdminInformPTSAccountListResultResponse.class);

        // ---------------------- 6 Handler �̓o�^���� ----------------------
        //�A������
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3InformInputRequest.class,
            WEB3InformInputHandler.class,
            "informInputDisplay");

        regHandler(
            WEB3InformAppPlugin.class,
            WEB3InformConfirmRequest.class,
            WEB3InformInputHandler.class,
            "informConfirm");

        regHandler(
            WEB3InformAppPlugin.class,
            WEB3InformCompleteRequest.class,
            WEB3InformInputHandler.class,
            "informComplete");

        //�A����񌟍�
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3AdminInformInputRequest.class,
            WEB3AdminInformReferenceHandler.class,
            "informInputDisplay");

        regHandler(
            WEB3InformAppPlugin.class,
            WEB3AdminInformListRequest.class,
            WEB3AdminInformReferenceHandler.class,
            "informListDisplay");

        regHandler(
            WEB3InformAppPlugin.class,
            WEB3AdminInformDetailRequest.class,
            WEB3AdminInformReferenceHandler.class,
            "informDetailDisplay");

        regHandler(
            WEB3InformAppPlugin.class,
            WEB3AdminInformDownLoadRequest.class,
            WEB3AdminInformReferenceHandler.class,
            "allInformDownload");

        //�����E���z���E���p����U����ꗗ
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3AdminInformProfDistSellTransSrcInpRequest.class,
            WEB3AdminInformProfDistSellTransSrcListHandler.class,
            "displayInputScreen");

        regHandler(
            WEB3InformAppPlugin.class,
            WEB3AdminInformProfDistSellTransSrcListRequest.class,
            WEB3AdminInformProfDistSellTransSrcListHandler.class,
            "displayListScreen");

        //----------�����E���z���o�^�`�[�쐬start----------------->
        //�o�^�󋵌���
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3AdminInformProfDistStatusSearchInputRequest.class,
            WEB3AdminInformProfDistRegistVoucherMakeHandler.class,
            "registStatusSearch");

        //�`�[�쐬����
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3AdminInformProfDistVoucherMakeInpRequest.class,
            WEB3AdminInformProfDistRegistVoucherMakeHandler.class,
            "voucherMakeInp");

        //�`�[�쐬�m�F
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3AdminInformProfDistVoucherMakeCnfRequest.class,
            WEB3AdminInformProfDistRegistVoucherMakeHandler.class,
            "voucherMakeCnf");

        //�`�[�쐬����
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3AdminInformProfDistVoucherMakeCmpRequest.class,
            WEB3AdminInformProfDistRegistVoucherMakeHandler.class,
            "voucherMakeCmp");

        //�o�^�����Q��
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3AdminInformProfDistVoucherInfoRefRequest.class,
            WEB3AdminInformProfDistRegistVoucherMakeHandler.class,
            "registAccountRef");

        //�o�^�����ύX�m�F
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3AdminInformProfDistVoucherChgCnfRequest.class,
            WEB3AdminInformProfDistRegistVoucherMakeHandler.class,
            "registAccountChgeCnf");

        //�o�^�����ύX����
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3AdminInformProfDistVoucherChgCmpRequest.class,
            WEB3AdminInformProfDistRegistVoucherMakeHandler.class,
            "registAccountChgeCmp");

        //�o�^��������m�F
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3AdminInformProfDistVoucherCancCnfRequest.class,
            WEB3AdminInformProfDistRegistVoucherMakeHandler.class,
            "registAccountCancCnf");

        //�o�^�����������
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3AdminInformProfDistVoucherCancCmpRequest.class,
            WEB3AdminInformProfDistRegistVoucherMakeHandler.class,
            "registAccountCancCmp");
        //----------�����E���z���o�^�`�[�쐬end----------------->

        //----------�Ǘ��Ҍ����ؑցE�d�q��t�\��start----------------->

        // ������ʕ\��
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3AdminInformAccSwElecDeliApplySrcRequest.class,
            WEB3AdminInformSwElecDeliApplyHandler.class,
            "searchScreenDisplay");

        // ���͉�ʕ\��
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3AdminInformAccSwElecDeliApplyInpRequest.class,
            WEB3AdminInformSwElecDeliApplyHandler.class,
            "displayInputScreen");

        // �\���m�F
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3AdminInformAccSwElecDeliApplyConfRequest.class,
            WEB3AdminInformSwElecDeliApplyHandler.class,
            "applyConfirm");

        // �\������
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3AdminInformAccSwElecDeliApplyCmpRequest.class,
            WEB3AdminInformSwElecDeliApplyHandler.class,
            "applyComplete");

        //get�Ɖ���
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3AdminInformAccSwElecDeliApplyRefRequest.class,
            WEB3AdminInformSwElecDeliApplyHandler.class,
            "displayReferenceScreen");

        //validate�ύX
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3AdminInformAccSwElecDeliChangeConfRequest.class,
            WEB3AdminInformSwElecDeliApplyHandler.class,
            "changeConfirm");

        //submit�ύX
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3AdminInformAccSwElecDeliChangeCmpRequest.class,
            WEB3AdminInformSwElecDeliApplyHandler.class,
            "changeComplete");

        //validate���
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3AdminInformAccSwElecDeliDeleteConfRequest.class,
            WEB3AdminInformSwElecDeliApplyHandler.class,
            "deleteConfirm");

        //submit���
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3AdminInformAccSwElecDeliDeleteCmpRequest.class,
            WEB3AdminInformSwElecDeliApplyHandler.class,
            "deleteComplete");

        //----------�Ǘ��Ҍ����ؑցE�d�q��t�\��end----------------->

        //----------�����ؑցE�d�q��t�\��start----------------->
        //���͉�ʕ\��
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3InformAccSwElecDeliApplyInpRequest.class,
            WEB3InformAccSwElecDeliApplyHandler.class,
            "displayInputScreen");

        //�\���m�F
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3InformAccSwElecDeliApplyConfRequest.class,
            WEB3InformAccSwElecDeliApplyHandler.class,
            "applyConfirm");

        //�\������
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3InformAccSwElecDeliApplyCmpRequest.class,
            WEB3InformAccSwElecDeliApplyHandler.class,
            "applyComplete");

        //----------�����ؑցE�d�q��t�\��end----------------->

        //----------PTS�����J�ݐ\��start----------------->
        //���͉�ʕ\��
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3InformPTSAccOpenApplyInpRequest.class,
            WEB3InformPTSAccOpenApplyHandler.class,
            "displayInputScreen");

        //�\���m�F
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3InformPTSAccOpenApplyCnfRequest.class,
            WEB3InformPTSAccOpenApplyHandler.class,
            "applyConfirm");

        //�\������
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3InformPTSAccOpenApplyCmpRequest.class,
            WEB3InformPTSAccOpenApplyHandler.class,
            "applyComplete");
        //----------PTS�����J�ݐ\��end----------------->

        //�Ǘ��ҁEPTS�����J�ݏ󋵕ύX
        //������ʕ\��
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3AdminInformPTSAccOpenStateChangeSrcRequest.class,
            WEB3AdminInformPTSAccOpenStateChangeHandler.class,
            "displaySearchScrean");

        //���͉�ʕ\��
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3AdminInformPTSAccOpenStateChangeInpRequest.class,
            WEB3AdminInformPTSAccOpenStateChangeHandler.class,
            "displayInputScrean");

        //�ύX�m�F
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3AdminInformPTSAccOpenStateChangeCnfRequest.class,
            WEB3AdminInformPTSAccOpenStateChangeHandler.class,
            "changeConfirm");

        //�ύX����
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3AdminInformPTSAccOpenStateChangeCmpRequest.class,
            WEB3AdminInformPTSAccOpenStateChangeHandler.class,
            "changeComplete");

        //�Ǘ��ҁEPTS�\���q�ꗗ�⍇��
        //������ʕ\��
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3AdminInformPTSAccountListInquiryRequest.class,
            WEB3AdminInformPTSAccountListHandler.class,
            "displaySearchScreen");

        //�������ʕ\��
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3AdminInformPTSAccountListResultRequest.class,
            WEB3AdminInformPTSAccountListHandler.class,
            "displaySearchResultScreen");


        //* MQ-Gateway �� Interceptor �ݒ菈��
        Services.addInterceptor(
            WEB3AdminInformProfDistRegistVoucherMakeService.class,
            new WEB3MQGatewayInterceptor());

        //�Ǘ��Ҍ����ؑցE�d�q��t�\���T�[�r�X
        Services.addInterceptor(
            WEB3AdminInformSwElecDeliApplyService.class,
            new WEB3MQGatewayInterceptor());

        //�����ؑցE�d�q��t�\���T�[�r�X
        Services.addInterceptor(
            WEB3InformAccSwElecDeliApplyService.class,
            new WEB3MQGatewayInterceptor());

        log.exiting(METHOD_NAME);
    }
}
@
