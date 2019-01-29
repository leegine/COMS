head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.25.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3TradeManagementAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : Webbroker3-trademanagement �v���O�C���N���X(WEB3TradeManagementAppPlugin.java)
Author Name      : Daiwa Institute of Research
                   2006/10/11 ���G��(���u) �Ǘ��҈ב֓o�^ �Ή�
Revision History : 2008/09/26 �����F(���u) �s���A�^�b�N�h�~�Ή�
*/
package webbroker3.trademanagement;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;

import webbroker3.common.WEB3LogSysTimeInterceptor;
import webbroker3.trademanagement.handler.WEB3AdminTMExchangeRegistHandler;
import webbroker3.trademanagement.handler.WEB3AdminTMLoginFrequencyListHandler;
import webbroker3.trademanagement.handler.WEB3AdminTMLoginProcessingListHandler;
import webbroker3.trademanagement.handler.WEB3AdminTMLoginRejectIPManagementHandler;
import webbroker3.trademanagement.handler.WEB3AdminTMLoginStopStartChangeHandler;
import webbroker3.trademanagement.handler.WEB3AdminTMMarketStopStartChangeServiceHandler;
import webbroker3.trademanagement.handler.WEB3AdminTMProductStopStartChangeServiceHandler;
import webbroker3.trademanagement.handler.WEB3AdminTMProductStopStartReferenceHandler;
import webbroker3.trademanagement.message.WEB3AdminTMExchangeRegistCompleteRequest;
import webbroker3.trademanagement.message.WEB3AdminTMExchangeRegistCompleteResponse;
import webbroker3.trademanagement.message.WEB3AdminTMExchangeRegistConfirmRequest;
import webbroker3.trademanagement.message.WEB3AdminTMExchangeRegistConfirmResponse;
import webbroker3.trademanagement.message.WEB3AdminTMExchangeRegistInputRequest;
import webbroker3.trademanagement.message.WEB3AdminTMExchangeRegistInputResponse;
import webbroker3.trademanagement.message.WEB3AdminTMLStopStartChgCompleteRequest;
import webbroker3.trademanagement.message.WEB3AdminTMLStopStartChgCompleteResponse;
import webbroker3.trademanagement.message.WEB3AdminTMLStopStartChgConfirmRequest;
import webbroker3.trademanagement.message.WEB3AdminTMLStopStartChgConfirmResponse;
import webbroker3.trademanagement.message.WEB3AdminTMLStopStartChgInputRequest;
import webbroker3.trademanagement.message.WEB3AdminTMLStopStartChgInputResponse;
import webbroker3.trademanagement.message.WEB3AdminTMMStopStartChgCompleteRequest;
import webbroker3.trademanagement.message.WEB3AdminTMMStopStartChgCompleteResponse;
import webbroker3.trademanagement.message.WEB3AdminTMMStopStartChgConfirmRequest;
import webbroker3.trademanagement.message.WEB3AdminTMMStopStartChgConfirmResponse;
import webbroker3.trademanagement.message.WEB3AdminTMMStopStartChgInputRequest;
import webbroker3.trademanagement.message.WEB3AdminTMMStopStartChgInputResponse;
import webbroker3.trademanagement.message.WEB3AdminTMPStopStartCompleteRequest;
import webbroker3.trademanagement.message.WEB3AdminTMPStopStartCompleteResponse;
import webbroker3.trademanagement.message.WEB3AdminTMPStopStartConfirmRequest;
import webbroker3.trademanagement.message.WEB3AdminTMPStopStartConfirmResponse;
import webbroker3.trademanagement.message.WEB3AdminTMPStopStartReferenceRequest;
import webbroker3.trademanagement.message.WEB3AdminTMPStopStartReferenceResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlDeleteCompleteRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlDeleteCompleteResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlDeleteConfirmRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlDeleteConfirmResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlListRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlListResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlRegistCompleteRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlRegistCompleteResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlRegistConfirmRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlRegistConfirmResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlRegistInputRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlRegistInputResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlUpdateCompleteRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlUpdateCompleteResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlUpdateConfirmRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlUpdateConfirmResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlUpdateInputRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminIPControlUpdateInputResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginCountInputRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginCountInputResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginCountListRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginCountListResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginHistoryInputRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginHistoryInputResponse;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginHistoryListRequest;
import webbroker3.trademanagement.message.WEB3AdminTraderAdminLoginHistoryListResponse;
import webbroker3.trademanagement.service.delegate.WEB3AdminTMExchangeRegistService;
import webbroker3.trademanagement.service.delegate.WEB3AdminTMLoginFrequencyListService;
import webbroker3.trademanagement.service.delegate.WEB3AdminTMLoginProcessingListService;
import webbroker3.trademanagement.service.delegate.WEB3AdminTMLoginRejectIPManagementService;
import webbroker3.trademanagement.service.delegate.WEB3AdminTMLoginStopStartChangeService;
import webbroker3.trademanagement.service.delegate.WEB3AdminTMMarketStopStartChangeService;
import webbroker3.trademanagement.service.delegate.WEB3AdminTMProductStopStartChangeService;
import webbroker3.trademanagement.service.delegate.WEB3AdminTMProductStopStartReferenceService;
import webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMExchangeRegistServiceImpl;
import webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginFrequencyListServiceImpl;
import webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginProcessingListServiceImpl;
import webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginRejectIPManagementServiceImpl;
import webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginStopStartChangeServiceImpl;
import webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMMarketStopStartChangeServiceImpl;
import webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMProductStopStartChangeServiceImpl;
import webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMProductStopStartReferenceServiceImpl;
import webbroker3.util.WEB3LogUtility;

/**
 * Webbroker3-trademanagement �v���O�C���N���X�B<BR>
 * @@author Amarnath
 * @@version 1.0
 */
public final class WEB3TradeManagementAppPlugin extends Plugin
{
    /**
     * ���O���[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3TradeManagementAppPlugin.class);

    /**
     * �f�t�H���g�R���X�g���N�^�B
     */
    public WEB3TradeManagementAppPlugin()
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
        plug(WEB3TradeManagementAppPlugin.class);
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

        // Service �̓o�^����
        // �Ǘ��ҏ��i�ʎ戵��~�ĊJ�Ɖ�T�[�r�X
        Services.registerService(WEB3AdminTMProductStopStartReferenceService.class,
            new WEB3AdminTMProductStopStartReferenceServiceImpl());

        // �Ǘ��ҏ��i�ʎ戵��~�ĊJ�ύX�T�[�r�X
        Services.registerService(WEB3AdminTMProductStopStartChangeService.class,
            new WEB3AdminTMProductStopStartChangeServiceImpl());

        // �Ǘ��҃��O�C����~�ĊJ�ύX�T�[�r�X
        Services.registerService(WEB3AdminTMLoginStopStartChangeService.class,
            new WEB3AdminTMLoginStopStartChangeServiceImpl());

        // �Ǘ��Ҏs��ʎ����~�ĊJ�ύX�T�[�r�X
        Services.registerService(WEB3AdminTMMarketStopStartChangeService.class,
            new WEB3AdminTMMarketStopStartChangeServiceImpl());

        //�Ǘ��҈ב֓o�^�T�[�r�X
        Services.registerService(WEB3AdminTMExchangeRegistService.class,
            new WEB3AdminTMExchangeRegistServiceImpl());

        //�Ǘ��҃��O�C�������ꗗ�T�[�r�X
        Services.registerService(WEB3AdminTMLoginProcessingListService.class,
            new WEB3AdminTMLoginProcessingListServiceImpl());

        //IP�ʃ��O�C���񐔈ꗗ�T�[�r�X
        Services.registerService(WEB3AdminTMLoginFrequencyListService.class,
            new WEB3AdminTMLoginFrequencyListServiceImpl());

        //�Ǘ��҃��O�C������IP�Ǘ��T�[�r�X
        Services.registerService(WEB3AdminTMLoginRejectIPManagementService.class,
            new WEB3AdminTMLoginRejectIPManagementServiceImpl());

        // �T�[�r�X�C���^�Z�v�^�̐ݒ�
        // �Ǘ��ҏ��i�ʎ戵��~�ĊJ�Ɖ�T�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
        WEB3AdminTMProductStopStartReferenceService.class,
                new WEB3AdminTMProductStopStartReferenceServiceInterceptor());

        // �Ǘ��ҏ��i�ʎ戵��~�ĊJ�ύX�T�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
        WEB3AdminTMProductStopStartChangeService.class,
                new WEB3AdminTMProductStopStartChangeServiceInterceptor());

        // �Ǘ��҃��O�C����~�ĊJ�ύX�T�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
        WEB3AdminTMLoginStopStartChangeService.class,
                new WEB3AdminTMLoginStopStartChangeServiceInterceptor());

        // �Ǘ��Ҏs��ʎ����~�ĊJ�ύX�T�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
        WEB3AdminTMMarketStopStartChangeService.class,
                new WEB3AdminTMMarketStopStartChangeServiceInterceptor());

        // �T�[�r�X�Ăяo���O���
        // �����J�n�����Ə����I�����������O�o�͂���悤�ɐݒ肷��
        // �Ǘ��ҏ��i�ʎ戵��~�ĊJ�Ɖ�T�[�r�X
        Services.addInterceptor(
        WEB3AdminTMProductStopStartReferenceService.class,
                new TransactionalInterceptor(
                    TransactionalInterceptor.TX_CREATE_NEW));

        // �Ǘ��ҏ��i�ʎ戵��~�ĊJ�ύX�T�[�r�X
        Services.addInterceptor(
        WEB3AdminTMProductStopStartChangeService.class,
            new WEB3LogSysTimeInterceptor());

        // �Ǘ��҃��O�C����~�ĊJ�ύX�T�[�r�X
        Services.addInterceptor(
                WEB3AdminTMLoginStopStartChangeService.class,
                    new WEB3LogSysTimeInterceptor());

        // �Ǘ��Ҏs��ʎ����~�ĊJ�ύX�T�[�r�X
        Services.addInterceptor(
        WEB3AdminTMMarketStopStartChangeService.class,
            new WEB3LogSysTimeInterceptor());

        // �Ǘ��ҏ��i�ʎ戵��~�ĊJ�Ɖ�T�[�r�X
        Services.addInterceptor(
        WEB3AdminTMProductStopStartReferenceService.class,
            new WEB3LogSysTimeInterceptor());

        //�Ǘ��҈ב֓o�^�T�[�r�X
        Services.addInterceptor(
        WEB3AdminTMExchangeRegistService.class,
            new WEB3LogSysTimeInterceptor());
        
        //�Ǘ��҃��O�C�������ꗗ�T�[�r�X
        Services.addInterceptor(
            WEB3AdminTMLoginProcessingListService.class,
            new WEB3LogSysTimeInterceptor());

        //IP�ʃ��O�C���񐔈ꗗ�T�[�r�X
        Services.addInterceptor(
            WEB3AdminTMLoginFrequencyListService.class,
            new WEB3LogSysTimeInterceptor());

        //�Ǘ��҃��O�C������IP�Ǘ��T�[�r�X
        Services.addInterceptor(
            WEB3AdminTMLoginRejectIPManagementService.class,
            new WEB3LogSysTimeInterceptor());

        // �����g�����U�N�V�����ݒ�
        // �Ǘ��ҏ��i�ʎ戵��~�ĊJ�ύX�T�[�r�X
        Services.addInterceptor(
        WEB3AdminTMProductStopStartChangeService.class,
                new TransactionalInterceptor(
                    TransactionalInterceptor.TX_CREATE_NEW));
        // �Ǘ��҃��O�C����~�ĊJ�ύX�T�[�r�X
        Services.addInterceptor(
        WEB3AdminTMLoginStopStartChangeService.class,
                new TransactionalInterceptor(
                    TransactionalInterceptor.TX_CREATE_NEW));

        // �Ǘ��Ҏs��ʎ����~�ĊJ�ύX�T�[�r�X
        Services.addInterceptor(
        WEB3AdminTMMarketStopStartChangeService.class,
                new TransactionalInterceptor(
                    TransactionalInterceptor.TX_CREATE_NEW));

        // �Ǘ��҈ב֓o�^�T�[�r�X
        Services.addInterceptor(
                WEB3AdminTMExchangeRegistService.class,
                new TransactionalInterceptor(
                    TransactionalInterceptor.TX_CREATE_NEW));

        //�Ǘ��҃��O�C�������ꗗ�T�[�r�X
        Services.addInterceptor(
            WEB3AdminTMLoginProcessingListService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        //IP�ʃ��O�C���񐔈ꗗ�T�[�r�X
        Services.addInterceptor(
            WEB3AdminTMLoginFrequencyListService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        //�Ǘ��҃��O�C������IP�Ǘ��T�[�r�X
        Services.addInterceptor(
            WEB3AdminTMLoginRejectIPManagementService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        // Message �̓o�^
        // �Ǘ��ҏ��i�ʎ戵��~�ĊJ�Ɖ�N�G�X�g�E���X�|���X
        regClass(WEB3AdminTMPStopStartReferenceRequest.class);
        regClass(WEB3AdminTMPStopStartReferenceResponse.class);

        // �Ǘ��ҁE���i�ʎ戵��~�ĊJ�ύX�������N�G�X�g�E���X�|���X
        regClass(WEB3AdminTMPStopStartCompleteRequest.class);
        regClass(WEB3AdminTMPStopStartCompleteResponse.class);

        // �Ǘ��ҁE���i�ʎ戵��~�ĊJ�ύX�m�F���N�G�X�g�E���X�|���X
        regClass(WEB3AdminTMPStopStartConfirmRequest.class);
        regClass(WEB3AdminTMPStopStartConfirmResponse.class);

        // �Ǘ��ҁE���O�C����~�ĊJ�ύX���̓��N�G�X�g�E���X�|���X
        regClass(WEB3AdminTMLStopStartChgInputRequest.class);
        regClass(WEB3AdminTMLStopStartChgInputResponse.class);

        // �Ǘ��ҁE���O�C����~�ĊJ�ύX�m�F���N�G�X�g�E���X�|���X
        regClass(WEB3AdminTMLStopStartChgConfirmRequest.class);
        regClass(WEB3AdminTMLStopStartChgConfirmResponse.class);

        // �Ǘ��ҁE���O�C����~�ĊJ�ύX�������N�G�X�g�E���X�|���X
        regClass(WEB3AdminTMLStopStartChgCompleteRequest.class);
        regClass(WEB3AdminTMLStopStartChgCompleteResponse.class);

        // �Ǘ��ҁE�s��ʎ����~�ĊJ�ύX���̓��N�G�X�g�E���X�|���X
        regClass(WEB3AdminTMMStopStartChgInputRequest.class);
        regClass(WEB3AdminTMMStopStartChgInputResponse.class);

        // �Ǘ��ҁE�s��ʎ����~�ĊJ�ύX�m�F���N�G�X�g�E���X�|���X
        regClass(WEB3AdminTMMStopStartChgConfirmRequest.class);
        regClass(WEB3AdminTMMStopStartChgConfirmResponse.class);
        // �Ǘ��ҁE�s��ʎ����~�ĊJ�ύX�������N�G�X�g�E���X�|���X
        regClass(WEB3AdminTMMStopStartChgCompleteRequest.class);
        regClass(WEB3AdminTMMStopStartChgCompleteResponse.class);

        //�Ǘ��ҁE�ב֓o�^���̓��N�G�X�g�E���X�|���X
        regClass(WEB3AdminTMExchangeRegistInputRequest.class);
        regClass(WEB3AdminTMExchangeRegistInputResponse.class);
        //�Ǘ��ҁE�ב֓o�^�m�F���N�G�X�g�E���X�|���X
        regClass(WEB3AdminTMExchangeRegistConfirmRequest.class);
        regClass(WEB3AdminTMExchangeRegistConfirmResponse.class);

        //�Ǘ��ҁE�ב֓o�^�������N�G�X�g�E���X�|���X
        regClass(WEB3AdminTMExchangeRegistCompleteRequest.class);
        regClass(WEB3AdminTMExchangeRegistCompleteResponse.class);

        //�Ǘ��ҁE���O�C�������ꗗ�������̓��N�G�X�g.���X�|���X
        regClass(WEB3AdminTraderAdminLoginHistoryInputRequest.class);
        regClass(WEB3AdminTraderAdminLoginHistoryInputResponse.class);

        //�Ǘ��ҁE���O�C�������ꗗ�̌������ʃ��N�G�X�g.���X�|���X
        regClass(WEB3AdminTraderAdminLoginHistoryListRequest.class);
        regClass(WEB3AdminTraderAdminLoginHistoryListResponse.class);

        //�Ǘ��ҁE���O�C������IP�ꗗ���N�G�X�g.���X�|���X
        regClass(WEB3AdminTraderAdminIPControlListRequest.class);
        regClass(WEB3AdminTraderAdminIPControlListResponse.class);

        //�Ǘ��ҁE���O�C������IP�o�^���N�G�X�g.���X�|���X
        regClass(WEB3AdminTraderAdminIPControlRegistInputRequest.class);
        regClass(WEB3AdminTraderAdminIPControlRegistInputResponse.class);

        //�Ǘ��ҁE���O�C������IP�o�^���ύX���N�G�X�g.���X�|���X
        regClass(WEB3AdminTraderAdminIPControlUpdateInputRequest.class);
        regClass(WEB3AdminTraderAdminIPControlUpdateInputResponse.class);

        //�Ǘ��ҁE���O�C������IP�o�^���폜�m�F���N�G�X�g.���X�|���X
        regClass(WEB3AdminTraderAdminIPControlDeleteConfirmRequest.class);
        regClass(WEB3AdminTraderAdminIPControlDeleteConfirmResponse.class);

        //�Ǘ��ҁE���O�C������IP�o�^�m�F���N�G�X�g.���X�|���X
        regClass(WEB3AdminTraderAdminIPControlRegistConfirmRequest.class);
        regClass(WEB3AdminTraderAdminIPControlRegistConfirmResponse.class);

        //�Ǘ��ҁE���O�C������IP�o�^���ύX�m�F���N�G�X�g.���X�|���X
        regClass(WEB3AdminTraderAdminIPControlUpdateConfirmRequest.class);
        regClass(WEB3AdminTraderAdminIPControlUpdateConfirmResponse.class);

        //�Ǘ��ҁE���O�C������IP�o�^���폜�������N�G�X�g.���X�|���X
        regClass(WEB3AdminTraderAdminIPControlDeleteCompleteRequest.class);
        regClass(WEB3AdminTraderAdminIPControlDeleteCompleteResponse.class);

        //�Ǘ��ҁE���O�C������IP�o�^�������N�G�X�g.���X�|���X
        regClass(WEB3AdminTraderAdminIPControlRegistCompleteRequest.class);
        regClass(WEB3AdminTraderAdminIPControlRegistCompleteResponse.class);

        //�Ǘ��ҁE���O�C������IP�o�^���ύX�������N�G�X�g.���X�|���X
        regClass(WEB3AdminTraderAdminIPControlUpdateCompleteRequest.class);
        regClass(WEB3AdminTraderAdminIPControlUpdateCompleteResponse.class);

        //�Ǘ��ҁEIP�ʃ��O�C���񐔈ꗗ�������̓��N�G�X�g.���X�|���X
        regClass(WEB3AdminTraderAdminLoginCountInputRequest.class);
        regClass(WEB3AdminTraderAdminLoginCountInputResponse.class);
        
        //�Ǘ��ҁEIP�ʃ��O�C���񐔈ꗗ�������ʃ��N�G�X�g.���X�|���X
        regClass(WEB3AdminTraderAdminLoginCountListRequest.class);
        regClass(WEB3AdminTraderAdminLoginCountListResponse.class);
        

        // �n���h���[�̓o�^
        // �Ǘ��ҏ��i�ʎ戵��~�ĊJ�Ɖ�n���h��
        regHandler(
            WEB3TradeManagementAppPlugin.class,
            WEB3AdminTMPStopStartReferenceRequest.class,
            WEB3AdminTMProductStopStartReferenceHandler.class,
            "getProductHandlingStatusReference");

        // �Ǘ��ҏ��i�ʎ戵��~�ĊJ�ύX�n���h��
        regHandler(
        WEB3TradeManagementAppPlugin.class,
        WEB3AdminTMPStopStartConfirmRequest.class,
        WEB3AdminTMProductStopStartChangeServiceHandler.class,
            "confirmChange");
        regHandler(
        WEB3TradeManagementAppPlugin.class,
        WEB3AdminTMPStopStartCompleteRequest.class,
        WEB3AdminTMProductStopStartChangeServiceHandler.class,
            "completeChange");

        // �Ǘ��҃��O�C����~�ĊJ�ύX�n���h��
        regHandler(
        WEB3TradeManagementAppPlugin.class,
        WEB3AdminTMLStopStartChgInputRequest.class,
        WEB3AdminTMLoginStopStartChangeHandler.class,
            "getChangeInputScreen");
        regHandler(
        WEB3TradeManagementAppPlugin.class,
        WEB3AdminTMLStopStartChgConfirmRequest.class,
        WEB3AdminTMLoginStopStartChangeHandler.class,
            "confirmChange");
        regHandler(
        WEB3TradeManagementAppPlugin.class,
        WEB3AdminTMLStopStartChgCompleteRequest.class,
        WEB3AdminTMLoginStopStartChangeHandler.class,
            "completeChange");

        // �Ǘ��Ҏs��ʎ����~�ĊJ�ύX�n���h��
        regHandler(
        WEB3TradeManagementAppPlugin.class,
        WEB3AdminTMMStopStartChgInputRequest.class,
        WEB3AdminTMMarketStopStartChangeServiceHandler.class,
            "getChangeInputScreen");
        regHandler(
        WEB3TradeManagementAppPlugin.class,
        WEB3AdminTMMStopStartChgConfirmRequest.class,
        WEB3AdminTMMarketStopStartChangeServiceHandler.class,
            "confirmChange");
        regHandler(
        WEB3TradeManagementAppPlugin.class,
        WEB3AdminTMMStopStartChgCompleteRequest.class,
        WEB3AdminTMMarketStopStartChangeServiceHandler.class,
            "completeChange");

        //�Ǘ��҈ב֓o�^�n���h��
        regHandler(
        WEB3TradeManagementAppPlugin.class,
        WEB3AdminTMExchangeRegistInputRequest.class,
        WEB3AdminTMExchangeRegistHandler.class,
            "getInputScreen");
        regHandler(
        WEB3TradeManagementAppPlugin.class,
        WEB3AdminTMExchangeRegistConfirmRequest.class,
        WEB3AdminTMExchangeRegistHandler.class,
            "validateExchangeRegist");
        regHandler(
        WEB3TradeManagementAppPlugin.class,
        WEB3AdminTMExchangeRegistCompleteRequest.class,
        WEB3AdminTMExchangeRegistHandler.class,
            "submitExchangeRegist");

        //�Ǘ��҃��O�C�������ꗗ�n���h��
        regHandler(
            WEB3TradeManagementAppPlugin.class,
            WEB3AdminTraderAdminLoginHistoryInputRequest.class,
            WEB3AdminTMLoginProcessingListHandler.class,
            "getSearchInputScreen");
        regHandler(
            WEB3TradeManagementAppPlugin.class,
            WEB3AdminTraderAdminLoginHistoryListRequest.class,
            WEB3AdminTMLoginProcessingListHandler.class,
            "getSearchResultScreen");

        //�Ǘ��҃��O�C������IP�Ǘ��n���h��
        regHandler(
            WEB3TradeManagementAppPlugin.class,
            WEB3AdminTraderAdminIPControlListRequest.class,
            WEB3AdminTMLoginRejectIPManagementHandler.class,
            "getListScreen");
        regHandler(
            WEB3TradeManagementAppPlugin.class,
            WEB3AdminTraderAdminIPControlRegistInputRequest.class,
            WEB3AdminTMLoginRejectIPManagementHandler.class,
            "getRegistScreen");
        regHandler(
            WEB3TradeManagementAppPlugin.class,
            WEB3AdminTraderAdminIPControlRegistConfirmRequest.class,
            WEB3AdminTMLoginRejectIPManagementHandler.class,
            "validateRegist");
        regHandler(
            WEB3TradeManagementAppPlugin.class,
            WEB3AdminTraderAdminIPControlRegistCompleteRequest.class,
            WEB3AdminTMLoginRejectIPManagementHandler.class,
            "submitRegist");
        regHandler(
            WEB3TradeManagementAppPlugin.class,
            WEB3AdminTraderAdminIPControlUpdateInputRequest.class,
            WEB3AdminTMLoginRejectIPManagementHandler.class,
            "getChangedScreen");
        regHandler(
            WEB3TradeManagementAppPlugin.class,
            WEB3AdminTraderAdminIPControlUpdateConfirmRequest.class,
            WEB3AdminTMLoginRejectIPManagementHandler.class,
            "validateChange");
        regHandler(
            WEB3TradeManagementAppPlugin.class,
            WEB3AdminTraderAdminIPControlUpdateCompleteRequest.class,
            WEB3AdminTMLoginRejectIPManagementHandler.class,
            "submitChange");
        regHandler(
            WEB3TradeManagementAppPlugin.class,
            WEB3AdminTraderAdminIPControlDeleteConfirmRequest.class,
            WEB3AdminTMLoginRejectIPManagementHandler.class,
            "validateDelete");
        regHandler(
            WEB3TradeManagementAppPlugin.class,
            WEB3AdminTraderAdminIPControlDeleteCompleteRequest.class,
            WEB3AdminTMLoginRejectIPManagementHandler.class,
            "submitDelete");

        //IP�ʃ��O�C���񐔈ꗗ�n���h��
        regHandler(
            WEB3TradeManagementAppPlugin.class,
            WEB3AdminTraderAdminLoginCountInputRequest.class,
            WEB3AdminTMLoginFrequencyListHandler.class,
            "getSearchInputScreen");
        regHandler(
            WEB3TradeManagementAppPlugin.class,
            WEB3AdminTraderAdminLoginCountListRequest.class,
            WEB3AdminTMLoginFrequencyListHandler.class,
            "getSearchResultScreen");

        log.exiting(METHOD_NAME);
    }
}@
