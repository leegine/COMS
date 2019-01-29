head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.56.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPLibPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �]�͌v�Z�v���O�C���N���X(WEB3TPLibPlugin.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2004/09/08 �� �V�K�쐬
 Revesion History : 2007/08/01 ����(���u) ���f��No.112
 Revesion History : 2008/05/27 �g�E�N�|(���u) �u���V���v���N�X�A�g�v�Ή�
 */
package webbroker3.tradingpower;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;

import webbroker3.common.WEB3LogSysTimeInterceptor;
import webbroker3.tradingpower.data.WEB3TPLibAccountDatabaseExtensions;
import webbroker3.tradingpower.data.WEB3TPLibMasterDatabaseExtensions;
import webbroker3.tradingpower.data.WEB3TPLibSessionDatabaseExtensions;
import webbroker3.tradingpower.handler.WEB3TPOrixTpCalcResultHandler;
import webbroker3.tradingpower.handler.WEB3TPReCalcNotifyHandler;
import webbroker3.tradingpower.message.WEB3TPOrixTpCalcResultRequest;
import webbroker3.tradingpower.message.WEB3TPOrixTpCalcResultResponse;
import webbroker3.tradingpower.message.WEB3TPReCalcNotifyRequest;
import webbroker3.tradingpower.message.WEB3TPReCalcNotifyResponse;
import webbroker3.tradingpower.service.delegate.WEB3TPBondSimplexCooperationService;
import webbroker3.tradingpower.service.delegate.WEB3TPOrixVantiveService;
import webbroker3.tradingpower.service.delegate.WEB3TPPaymentRequisitionManageService;
import webbroker3.tradingpower.service.delegate.WEB3TPReCalcNotifyService;
import webbroker3.tradingpower.service.delegate.WEB3TPReCalcNotifyUnitService;
import webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPBondSimplexCooperationServiceImpl;
import webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPOrixVantiveServiceImpl;
import webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPPaymentRequisitionManageServiceImpl;
import webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPReCalcNotifyServiceImpl;
import webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPReCalcNotifyUnitServiceImpl;
import webbroker3.util.WEB3LogUtility;

/**
 * �]�͌v�Z�v���O�C���N���X
 *
 * @@author ��
 * @@version 1.0
 */
public final class WEB3TPLibPlugin extends Plugin
{
    /**
     * ���O���[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3TPLibPlugin.class);

    /**
     * �R���X�g���N�^�B
     */
    public WEB3TPLibPlugin()
    {
    }

    /**
     * �v���O�C���G���g���[�|�C���g�B
     */
    public static void plug() throws Exception
    {
        String METHOD_NAME = "plug()";
        log.entering(METHOD_NAME);
        plug(WEB3TPLibPlugin.class);
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
        KernelPlugin.plug();

        // DatabaseExtensions �v���O�C��
        WEB3TPLibAccountDatabaseExtensions.plug();
        WEB3TPLibMasterDatabaseExtensions.plug();
        WEB3TPLibSessionDatabaseExtensions.plug();

        // Service �̓o�^���� ----------------------------------

        /*
         * ����]�̓T�[�r�X��o�^
         */
        Services.registerService(
            WEB3TPTradingPowerService.class,
            new WEB3TPTradingPowerServiceImpl());
        log.debug("WEB3TPTradingPowerService registered.");

        //�]�̓T�[�r�X�C���^�[�Z�v�^��o�^
        Services.addInterceptor(
            WEB3TPTradingPowerService.class,
            new WEB3TPTradingPowerServiceInterceptor());
        log.debug(
            "WEB3TPTradingPowerServiceInterceptor added to WEB3TPTradingPowerService");

        // TransactionalInterceptor��o�^
        Services.addInterceptor(
            WEB3TPTradingPowerService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        log.debug("TransactionalInterceptor added to WEB3TPTradingPowerService");


        /*
         * �]�͍Čv�Z�T�[�r�X��o�^
         */
        Services.registerService(
            WEB3TPTradingPowerReCalcService.class,
            new WEB3TPTradingPowerReCalcServiceImpl());
        log.debug("WEB3TPTradingPowerReCalcService registered.");

        //�]�̓T�[�r�X�C���^�[�Z�v�^��o�^
        Services.addInterceptor(
            WEB3TPTradingPowerReCalcService.class,
            new WEB3TPTradingPowerServiceInterceptor());
        log.debug(
            "WEB3TPTradingPowerServiceInterceptor added to WEB3TPTradingPowerReCalcService");

        // TransactionalInterceptor��o�^
        Services.addInterceptor(
            WEB3TPTradingPowerReCalcService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        log.debug("TransactionalInterceptor added to WEB3TPTradingPowerReCalcService");


        /*
         * �ԍό�]�̓T�[�r�X��o�^
         */
        Services.registerService(
            WEB3TPTradingPowerAfterRepayService.class,
            new WEB3TPTradingPowerAfterRepayServiceImpl());
        log.debug("WEB3TPTradingPowerAfterRepayService registered.");

        //�]�̓T�[�r�X�C���^�[�Z�v�^��o�^
        Services.addInterceptor(
            WEB3TPTradingPowerAfterRepayService.class,
            new WEB3TPTradingPowerServiceInterceptor());
        log.debug(
            "WEB3TPTradingPowerServiceInterceptor added to WEB3TPTradingPowerAfterRepayService");

        //TransactionalInterceptor��o�^
        Services.addInterceptor(
            WEB3TPTradingPowerAfterRepayService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        log.debug("TransactionalInterceptor added to WEB3TPTradingPowerAfterRepayService");


        /*
         * �������ώ���]�̓T�[�r�X��o�^
         */
        Services.registerService(
            WEB3TPTradingPowerSettlementService.class,
            new WEB3TPTradingPowerSettlementServiceImpl());
        log.debug("WEB3TPTradingPowerSettlementService registered.");

        //�]�̓T�[�r�X�C���^�[�Z�v�^��o�^
        Services.addInterceptor(
            WEB3TPTradingPowerSettlementService.class,
            new WEB3TPTradingPowerServiceInterceptor());
        log.debug(
            "WEB3TPTradingPowerServiceInterceptor added to WEB3TPTradingPowerSettlementService");

        //TransactionalInterceptor��o�^
        Services.addInterceptor(
            WEB3TPTradingPowerSettlementService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        log.debug("TransactionalInterceptor added to WEB3TPTradingPowerSettlementService");


        /*
         * �]�͌v�Z�ʒm�T�[�r�X
         */
        Services.registerService(
            WEB3TPReCalcNotifyService.class,
            new WEB3TPReCalcNotifyServiceImpl());
        log.debug("WEB3TPReCalcNotifyService registered.");

        //TransactionalInterceptor��o�^
        Services.addInterceptor(
            WEB3TPReCalcNotifyService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));
        log.debug("TransactionalInterceptor added to WEB3TPReCalcNotifyService");

        /*
         * �]�͌v�Z�ʒm�ꌏ�T�[�r�X
         */
        Services.registerService(
            WEB3TPReCalcNotifyUnitService.class,
            new WEB3TPReCalcNotifyUnitServiceImpl());
        log.debug("WEB3TPReCalcNotifyUnitService registered.");

        //�]�͌v�Z�ʒm�ꌏ�T�[�r�X�C���^�Z�v�^��o�^
        Services.addInterceptor(
            WEB3TPReCalcNotifyUnitService.class,
            new WEB3TPReCalcNotifyUnitServiceInterceptor());
        log.debug(
            "WEB3TPReCalcNotifyUnitServiceInterceptor added to WEB3TPReCalcNotifyUnitService");

        //TransactionalInterceptor��o�^
        Services.addInterceptor(
            WEB3TPReCalcNotifyUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));
        log.debug("TransactionalInterceptor added to WEB3TPReCalcNotifyUnitService");

        // RAC-CTX�C���^�Z�v�^��ݒ肷��
        Services.addInterceptor(
        WEB3TPReCalcNotifyUnitService.class,
            new WEB3TPDescendRacCtxInterceptor());
        log.debug("WEB3TPDescendRacCtxInterceptor added to WEB3TPReCalcNotifyUnitService");

        /*
         * Orix Vantive�A�g�T�[�r�X��o�^
         */
        Services.registerService(
            WEB3TPOrixVantiveService.class,
            new WEB3TPOrixVantiveServiceImpl());
        log.debug("WEB3TPOrixVantiveService registered.");

        // TransactionalInterceptor��o�^
        Services.addInterceptor(
            WEB3TPOrixVantiveService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));
        log.debug("TransactionalInterceptor added to WEB3TPOrixVantiveService");
        
        /*
         * ���������Ǘ��T�[�r�X��o�^
         */
         Services.registerService(
             WEB3TPPaymentRequisitionManageService.class,
             new WEB3TPPaymentRequisitionManageServiceImpl());
         log.debug("WEB3TPPaymentRequisitionManageService registered.");
         
         // WEB3TPTradingPowerServiceInterceptor
         Services.addInterceptor(
             WEB3TPPaymentRequisitionManageService.class,
             new WEB3TPTradingPowerServiceInterceptor());
         log.debug("WEB3TPTradingPowerServiceInterceptor added to WEB3TPPaymentRequisitionManageService");

         // WEB3LogSysTimeInterceptor
         Services.addInterceptor(
             WEB3TPPaymentRequisitionManageService.class,
             new WEB3LogSysTimeInterceptor());
         log.debug("WEB3LogSysTimeInterceptor added to WEB3TPPaymentRequisitionManageService");
         
         // TransactionalInterceptor��o�^
         Services.addInterceptor(
             WEB3TPPaymentRequisitionManageService.class,
             new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
         log.debug("TransactionalInterceptor added to WEB3TPPaymentRequisitionManageService");

        // ���V���v���N�X�A�g�T�[�r�X
        Services.registerService(
            WEB3TPBondSimplexCooperationService.class,
            new WEB3TPBondSimplexCooperationServiceImpl());

        Services.addInterceptor(
            WEB3TPBondSimplexCooperationService.class,
            new WEB3TPTradingPowerServiceInterceptor());

        Services.addInterceptor(
            WEB3TPBondSimplexCooperationService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // Message �̓o�^���� ----------------------------------

        // �]�͌v�Z�ʒm���N�G�X�g ��o�^
        regClass(WEB3TPReCalcNotifyRequest.class);
        // �]�͌v�Z�ʒm���X�|���X ��o�^
        regClass(WEB3TPReCalcNotifyResponse.class);
        // Orix�]�͌v�Z���ʃ��N�G�X�g ��o�^
        regClass(WEB3TPOrixTpCalcResultRequest.class);
        // Orix�]�͌v�Z���ʃ��X�|���X ��o�^
        regClass(WEB3TPOrixTpCalcResultResponse.class);

        // Handler �̓o�^���� ------------------------------

        // �]�͌v�Z�ʒm�n���h���̓o�^
        regHandler(
            WEB3TPLibPlugin.class,
            WEB3TPReCalcNotifyRequest.class,
            WEB3TPReCalcNotifyHandler.class,
            "reCalcNotifyRequest");
        // Orix�]�͌v�Z���ʃn���h���̓o�^
        regHandler(
            WEB3TPLibPlugin.class,
            WEB3TPOrixTpCalcResultRequest.class,
            WEB3TPOrixTpCalcResultHandler.class,
            "orixTpCalcResultRequest");

        log.exiting(METHOD_NAME);
    }
}
@
