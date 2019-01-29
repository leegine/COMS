head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : Webbroker3-xbruito �v���O�C���N���X(WEB3RuitoAppPlugin.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/18 ����� (���u)  �V�K�쐬
                   2004/12/15 �����(���u)�c�Ή�
*/
package webbroker3.xbruito;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AlreadyInstalledException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotInstalledException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3LogSysTimeInterceptor;
import webbroker3.util.WEB3LogUtility;
import webbroker3.xbruito.data.WEB3RuitoSessionDatabaseExtensions;
import webbroker3.xbruito.handler.WEB3AdminRuitoTradeStopHandler;
import webbroker3.xbruito.handler.WEB3RuitoBuyOrderHandler;
import webbroker3.xbruito.handler.WEB3RuitoBuyOrderInputHandler;
import webbroker3.xbruito.handler.WEB3RuitoCancelAcceptedHandler;
import webbroker3.xbruito.handler.WEB3RuitoCancelHandler;
import webbroker3.xbruito.handler.WEB3RuitoMRFCancelAcceptHandler;
import webbroker3.xbruito.handler.WEB3RuitoMRFOrderAcceptHandler;
import webbroker3.xbruito.handler.WEB3RuitoOrderAcceptHandler;
import webbroker3.xbruito.handler.WEB3RuitoOrderReferenceHandler;
import webbroker3.xbruito.handler.WEB3RuitoSellHandler;
import webbroker3.xbruito.handler.WEB3RuitoSellInputHandler;
import webbroker3.xbruito.handler.WEB3RuitoSellPossibleListReferenceHandler;
import webbroker3.xbruito.handler.WEB3RuitoTradeOrderNotifyHandler;
import webbroker3.xbruito.marketadaptor.WEB3RuitoMarketAdaptorAppPlugin;
import webbroker3.xbruito.message.WEB3AdminRuitoTradeStopCompleteRequest;
import webbroker3.xbruito.message.WEB3AdminRuitoTradeStopCompleteResponse;
import webbroker3.xbruito.message.WEB3AdminRuitoTradeStopConfirmRequest;
import webbroker3.xbruito.message.WEB3AdminRuitoTradeStopConfirmResponse;
import webbroker3.xbruito.message.WEB3AdminRuitoTradeStopInputRequest;
import webbroker3.xbruito.message.WEB3AdminRuitoTradeStopInputResponse;
import webbroker3.xbruito.message.WEB3RuitoBuyCompleteRequest;
import webbroker3.xbruito.message.WEB3RuitoBuyCompleteResponse;
import webbroker3.xbruito.message.WEB3RuitoBuyConfirmRequest;
import webbroker3.xbruito.message.WEB3RuitoBuyConfirmResponse;
import webbroker3.xbruito.message.WEB3RuitoBuyInputRequest;
import webbroker3.xbruito.message.WEB3RuitoBuyInputResponse;
import webbroker3.xbruito.message.WEB3RuitoCancelAcceptRequest;
import webbroker3.xbruito.message.WEB3RuitoCancelAcceptResponse;
import webbroker3.xbruito.message.WEB3RuitoCancelCompleteRequest;
import webbroker3.xbruito.message.WEB3RuitoCancelCompleteResponse;
import webbroker3.xbruito.message.WEB3RuitoCancelConfirmRequest;
import webbroker3.xbruito.message.WEB3RuitoCancelConfirmResponse;
import webbroker3.xbruito.message.WEB3RuitoCommonRequest;
import webbroker3.xbruito.message.WEB3RuitoDealingOrderNotifyRequest;
import webbroker3.xbruito.message.WEB3RuitoDealingOrderNotifyResponse;
import webbroker3.xbruito.message.WEB3RuitoMRFCancelAcceptRequest;
import webbroker3.xbruito.message.WEB3RuitoMRFCancelAcceptResponse;
import webbroker3.xbruito.message.WEB3RuitoMRFOrderAcceptRequest;
import webbroker3.xbruito.message.WEB3RuitoMRFOrderAcceptResponse;
import webbroker3.xbruito.message.WEB3RuitoOrderAcceptRequest;
import webbroker3.xbruito.message.WEB3RuitoOrderAcceptResponse;
import webbroker3.xbruito.message.WEB3RuitoOrderReferenceRequest;
import webbroker3.xbruito.message.WEB3RuitoOrderReferenceResponse;
import webbroker3.xbruito.message.WEB3RuitoSellCompleteRequest;
import webbroker3.xbruito.message.WEB3RuitoSellCompleteResponse;
import webbroker3.xbruito.message.WEB3RuitoSellConfirmRequest;
import webbroker3.xbruito.message.WEB3RuitoSellConfirmResponse;
import webbroker3.xbruito.message.WEB3RuitoSellInputRequest;
import webbroker3.xbruito.message.WEB3RuitoSellInputResponse;
import webbroker3.xbruito.message.WEB3RuitoSellListRequest;
import webbroker3.xbruito.message.WEB3RuitoSellListResponse;
import webbroker3.xbruito.service.delegate.WEB3AdminRuitoTradeStopService;
import webbroker3.xbruito.service.delegate.WEB3RuitoBuyOrderInputService;
import webbroker3.xbruito.service.delegate.WEB3RuitoBuyOrderService;
import webbroker3.xbruito.service.delegate.WEB3RuitoCancelAcceptedService;
import webbroker3.xbruito.service.delegate.WEB3RuitoCancelAcceptedUnitService;
import webbroker3.xbruito.service.delegate.WEB3RuitoCancelService;
import webbroker3.xbruito.service.delegate.WEB3RuitoMRFCancelAcceptService;
import webbroker3.xbruito.service.delegate.WEB3RuitoMRFCancelAcceptUnitService;
import webbroker3.xbruito.service.delegate.WEB3RuitoMRFOrderAcceptService;
import webbroker3.xbruito.service.delegate.WEB3RuitoMRFOrderAcceptUnitService;
import webbroker3.xbruito.service.delegate.WEB3RuitoOrderAcceptService;
import webbroker3.xbruito.service.delegate.WEB3RuitoOrderAcceptUnitService;
import webbroker3.xbruito.service.delegate.WEB3RuitoOrderReferenceService;
import webbroker3.xbruito.service.delegate.WEB3RuitoSellInputService;
import webbroker3.xbruito.service.delegate.WEB3RuitoSellPossibleListReferenceService;
import webbroker3.xbruito.service.delegate.WEB3RuitoSellService;
import webbroker3.xbruito.service.delegate.WEB3RuitoTradeOrderNotifyService;
import webbroker3.xbruito.service.delegate.WEB3RuitoTradeOrderNotifyUnitService;
import webbroker3.xbruito.service.delegate.stdimpls.WEB3AdminRuitoTradeStopServiceImpl;
import webbroker3.xbruito.service.delegate.stdimpls.WEB3RuitoBuyOrderInputServiceImpl;
import webbroker3.xbruito.service.delegate.stdimpls.WEB3RuitoBuyOrderServiceImpl;
import webbroker3.xbruito.service.delegate.stdimpls.WEB3RuitoCancelAcceptedServiceImpl;
import webbroker3.xbruito.service.delegate.stdimpls.WEB3RuitoCancelAcceptedUnitServiceImpl;
import webbroker3.xbruito.service.delegate.stdimpls.WEB3RuitoCancelServiceImpl;
import webbroker3.xbruito.service.delegate.stdimpls.WEB3RuitoMRFCancelAcceptServiceImpl;
import webbroker3.xbruito.service.delegate.stdimpls.WEB3RuitoMRFCancelAcceptUnitServiceImpl;
import webbroker3.xbruito.service.delegate.stdimpls.WEB3RuitoMRFOrderAcceptServiceImpl;
import webbroker3.xbruito.service.delegate.stdimpls.WEB3RuitoMRFOrderAcceptUnitServiceImpl;
import webbroker3.xbruito.service.delegate.stdimpls.WEB3RuitoOrderAcceptServiceImpl;
import webbroker3.xbruito.service.delegate.stdimpls.WEB3RuitoOrderAcceptUnitServiceImpl;
import webbroker3.xbruito.service.delegate.stdimpls.WEB3RuitoOrderReferenceServiceImpl;
import webbroker3.xbruito.service.delegate.stdimpls.WEB3RuitoSellInputServiceImpl;
import webbroker3.xbruito.service.delegate.stdimpls.WEB3RuitoSellPossibleListReferenceServiceImpl;
import webbroker3.xbruito.service.delegate.stdimpls.WEB3RuitoSellServiceImpl;
import webbroker3.xbruito.service.delegate.stdimpls.WEB3RuitoTradeOrderNotifyServiceImpl;
import webbroker3.xbruito.service.delegate.stdimpls.WEB3RuitoTradeOrderNotifyUnitServiceImpl;
import webbroker3.mqgateway.WEB3MQGatewayInterceptor;

/**
 * Webbroker3-Ruito �v���O�C���N���X�B
 *
 * @@author ����� (���u)
 * @@version 1.0
 */
public final class WEB3RuitoAppPlugin extends Plugin
{
    /**
     * ���O���[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RuitoAppPlugin.class);

    /**
     * �R���X�g���N�^�B
     */
    public WEB3RuitoAppPlugin()
    {
        String METHOD_NAME = "WEB3RuitoAppPlugin()";
        log.entering(METHOD_NAME);

        log.exiting(METHOD_NAME);
    }

    /**
     * �v���O�C���G���g���[�|�C���g�B
     */
    public static void plug() throws Exception
    {
        String METHOD_NAME = "plug()";
        log.entering(METHOD_NAME);

        plug(WEB3RuitoAppPlugin.class);

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
            l_finApp.uninstallTradingModule("xb-ruito-pdbt");
        }
        catch (NotInstalledException l_ex)
        {
            log.debug(l_ex.getMessage());
        }

        try
        {
            log.debug("Installing TradingModule : web3-ruito");
            l_finApp.installTradingModule(new WEB3RuitoTradingModule());
            log.debug("Installed TradingModule : web3-ruito");
        }
        catch (AlreadyInstalledException l_ex)
        {
            log.debug(l_ex.getMessage());
        }

        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.RUITO);

        // �g���v���_�N�g�E�}�l�[�W���[
        l_tradingModule.overrideProductManager(new WEB3RuitoProductManager());

        // �g�������}�l�[�W��
        l_tradingModule.overrideOrderManager(new WEB3RuitoOrderManager());

        // �|�W�V�����}�l�[�W��
        l_tradingModule.overridePositionManager(new WEB3RuitoPositionManager());

        // �ݓ������R���ʃ`�F�b�N
        WEB3RuitoOrderManagerReusableValidationsCheck.register();

        // Webbroker3-Ruito-MarketAdaptor �v���O�C��
        WEB3RuitoMarketAdaptorAppPlugin.plug();


        // DatabaseExtensions �̃v���O�C������ ----------------------
        WEB3RuitoSessionDatabaseExtensions.plug();

        // Service �̓o�^���� ----------------------

        // �ݐϓ������t�������̓T�[�r�X�C���^�t�F�[�X
        Services.registerService(
            WEB3RuitoBuyOrderInputService.class,
            new WEB3RuitoBuyOrderInputServiceImpl());

        // �ݐϓ������t�����T�[�r�X�C���^�[�t�F�C�X
        Services.registerService(
            WEB3RuitoBuyOrderService.class,
            new WEB3RuitoBuyOrderServiceImpl());

        // �ݐϓ��������t�T�[�r�X�C���^�[�t�F�C�X
        Services.registerService(
            WEB3RuitoCancelAcceptedService.class,
            new WEB3RuitoCancelAcceptedServiceImpl());

        // �ݓ������t�P���T�[�r�X�C���^�t�F�[�X
        Services.registerService(
            WEB3RuitoCancelAcceptedUnitService.class,
            new WEB3RuitoCancelAcceptedUnitServiceImpl());

        // �ݐϓ�������T�[�r�X�C���^�[�t�F�C�X
        Services.registerService(
            WEB3RuitoCancelService.class,
            new WEB3RuitoCancelServiceImpl());

        // �ݐϓ���MRF�����t�T�[�r�X�C���^�[�t�F�C�X
        Services.registerService(
            WEB3RuitoMRFCancelAcceptService.class,
            new WEB3RuitoMRFCancelAcceptServiceImpl());

        // �ݓ�MRF�����t�P���T�[�r�X�C���^�t�F�[�X
        Services.registerService(
            WEB3RuitoMRFCancelAcceptUnitService.class,
            new WEB3RuitoMRFCancelAcceptUnitServiceImpl());

        // �ݐϓ���MRF������t�T�[�r�X�C���^�[�t�F�C�X
        Services.registerService(
            WEB3RuitoMRFOrderAcceptService.class,
            new WEB3RuitoMRFOrderAcceptServiceImpl());

        // �ݓ�MRF������t�P���T�[�r�X�C���^�t�F�[�X
        Services.registerService(
            WEB3RuitoMRFOrderAcceptUnitService.class,
            new WEB3RuitoMRFOrderAcceptUnitServiceImpl());

        // �ݐϓ���������t�T�[�r�X�C���^�[�t�F�C�X
        Services.registerService(
            WEB3RuitoOrderAcceptService.class,
            new WEB3RuitoOrderAcceptServiceImpl());

        // �ݓ�������t�P���T�[�r�X�C���^�t�F�[�X
        Services.registerService(
            WEB3RuitoOrderAcceptUnitService.class,
            new WEB3RuitoOrderAcceptUnitServiceImpl());

        // �ݐϓ��������Ɖ�T�[�r�X�C���^�[�t�F�C�X
        Services.registerService(
            WEB3RuitoOrderReferenceService.class,
            new WEB3RuitoOrderReferenceServiceImpl());

        // �ݓ������̓T�[�r�X�C���^�t�F�[�X
        Services.registerService(
            WEB3RuitoSellInputService.class,
            new WEB3RuitoSellInputServiceImpl());

        // �ݓ����\�ꗗ�Ɖ�T�[�r�X�C���^�[�t�F�C�X�N���X
        Services.registerService(
            WEB3RuitoSellPossibleListReferenceService.class,
            new WEB3RuitoSellPossibleListReferenceServiceImpl());

        // �ݐϓ������T�[�r�X�C���^�[�t�F�C�X
        Services.registerService(
            WEB3RuitoSellService.class,
            new WEB3RuitoSellServiceImpl());

        // �ݐϓ������������ʒm�T�[�r�X�C���^�[�t�F�C�X
        Services.registerService(
            WEB3RuitoTradeOrderNotifyService.class,
            new WEB3RuitoTradeOrderNotifyServiceImpl());

        // �ݓ����������ʒm�P���T�[�r�X�C���^�t�F�[�X
        Services.registerService(
            WEB3RuitoTradeOrderNotifyUnitService.class,
            new WEB3RuitoTradeOrderNotifyUnitServiceImpl());

        // �Ǘ��җݓ������ʔ�����~�T�[�r�X
        Services.registerService(
            WEB3AdminRuitoTradeStopService.class,
            new WEB3AdminRuitoTradeStopServiceImpl());

        // Service.execute()�Ăяo���O���  ----------------------
        // �����J�n�����Ə����I�����������O�o�͂���悤�ɐݒ肷��

        // �ݐϓ������t�������̓T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
            WEB3RuitoBuyOrderInputService.class,
            new WEB3LogSysTimeInterceptor());

        // �ݐϓ������t�����T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
            WEB3RuitoBuyOrderService.class,
            new WEB3LogSysTimeInterceptor());

        // �ݐϓ��������t�T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
            WEB3RuitoCancelAcceptedService.class,
            new WEB3LogSysTimeInterceptor());

        // �ݓ������t�P���T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
            WEB3RuitoCancelAcceptedUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // �ݐϓ�������T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
            WEB3RuitoCancelService.class,
            new WEB3LogSysTimeInterceptor());

        // �ݐϓ���MRF�����t�T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
            WEB3RuitoMRFCancelAcceptService.class,
            new WEB3LogSysTimeInterceptor());

        // �ݓ�MRF�����t�P���T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
            WEB3RuitoMRFCancelAcceptUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // �ݐϓ���MRF������t�T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
            WEB3RuitoMRFOrderAcceptService.class,
            new WEB3LogSysTimeInterceptor());

        // �ݓ�MRF������t�P���T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
            WEB3RuitoMRFOrderAcceptUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // �ݐϓ���������t�T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
            WEB3RuitoOrderAcceptService.class,
            new WEB3LogSysTimeInterceptor());

        // �ݓ�������t�P���T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
            WEB3RuitoOrderAcceptUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // �ݐϓ��������Ɖ�T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
            WEB3RuitoOrderReferenceService.class,
            new WEB3LogSysTimeInterceptor());

        // �ݓ������̓T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
            WEB3RuitoSellInputService.class,
            new WEB3LogSysTimeInterceptor());

        // �ݓ����\�ꗗ�Ɖ�T�[�r�X�C���^�[�t�F�C�X�N���X
        Services.addInterceptor(
            WEB3RuitoSellPossibleListReferenceService.class,
            new WEB3LogSysTimeInterceptor());

        // �ݐϓ������T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
            WEB3RuitoSellService.class,
            new WEB3LogSysTimeInterceptor());

        // �ݐϓ������������ʒm�T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
            WEB3RuitoTradeOrderNotifyService.class,
            new WEB3LogSysTimeInterceptor());

        // �ݓ����������ʒm�P���T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
            WEB3RuitoTradeOrderNotifyUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // �Ǘ��җݓ������ʔ�����~�T�[�r�X ���O�C���^�[�t�F�C�X
        Services.addInterceptor(
            WEB3AdminRuitoTradeStopService.class,
            new WEB3LogSysTimeInterceptor());

        //Service �� ServiceInterceptor ��ݒ肷�� ----------------------

        // �ݐϓ������t�������̓T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
            WEB3RuitoBuyOrderInputService.class,
            new WEB3RuitoBuyOrderInputInterceptor());

        // �ݐϓ������t�����T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
            WEB3RuitoBuyOrderService.class,
            new WEB3RuitoBuyOrderServiceInterceptor());

        // �ݓ������t�P���T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
            WEB3RuitoCancelAcceptedUnitService.class,
            new WEB3RuitoCancelAcceptedUnitServiceInterceptor());

        // �ݐϓ�������T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
            WEB3RuitoCancelService.class,
            new WEB3RuitoCancelServiceInterceptor());

        // �ݓ�MRF�����t�P���T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
            WEB3RuitoMRFCancelAcceptUnitService.class,
            new WEB3RuitoMRFCancelAcceptUnitServiceInterceptor());

        // �ݓ�MRF������t�P���T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
            WEB3RuitoMRFOrderAcceptUnitService.class,
            new WEB3RuitoMRFOrderAcceptUnitServiceInterceptor());

        // �ݓ�������t�P���T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
            WEB3RuitoOrderAcceptUnitService.class,
            new WEB3RuitoOrderAcceptUnitServiceInterceptor());

        // �ݐϓ��������Ɖ�T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
            WEB3RuitoOrderReferenceService.class,
            new WEB3RuitoOrderReferenceServiceInterceptor());

        // �ݓ������̓T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
            WEB3RuitoSellInputService.class,
            new WEB3RuitoSellInputInterceptor());

        // �ݓ����\�ꗗ�Ɖ�T�[�r�X�C���^�[�t�F�C�X�N���X
        Services.addInterceptor(
            WEB3RuitoSellPossibleListReferenceService.class,
            new WEB3RuitoSellPossibleListReferenceInterceptor());

        // �ݐϓ������T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
            WEB3RuitoSellService.class,
            new WEB3RuitoSellServiceInterceptor());

        // �ݓ����������ʒm�P���T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
            WEB3RuitoTradeOrderNotifyUnitService.class,
            new WEB3RuitoTradeOrderNotifyUnitServiceInterceptor());

        // �Ǘ��җݓ������ʔ�����~�T�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
            WEB3AdminRuitoTradeStopService.class,
            new WEB3AdminRuitoTradeStopInterceptor());

        // Service �� Interceptor �ݒ菈�� ----------------------
        // �����g�����U�N�V�����ݒ�

        // �ݐϓ������t�������̓T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
            WEB3RuitoBuyOrderInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // �ݐϓ������t�����T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
            WEB3RuitoBuyOrderService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // �ݓ������t�P���T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
            WEB3RuitoCancelAcceptedUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // �ݐϓ�������T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
            WEB3RuitoCancelService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // �ݓ�MRF�����t�P���T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
            WEB3RuitoMRFCancelAcceptUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // �ݓ�MRF������t�P���T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
            WEB3RuitoMRFOrderAcceptUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // �ݓ�������t�P���T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
            WEB3RuitoOrderAcceptUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // �ݐϓ��������Ɖ�T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
            WEB3RuitoOrderReferenceService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // �ݓ������̓T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
            WEB3RuitoSellInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // �ݓ����\�ꗗ�Ɖ�T�[�r�X�C���^�[�t�F�C�X�N���X
        Services.addInterceptor(
            WEB3RuitoSellPossibleListReferenceService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // �ݐϓ������T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
            WEB3RuitoSellService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // �ݓ����������ʒm�P���T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
            WEB3RuitoTradeOrderNotifyUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // �Ǘ��җݓ������ʔ�����~�T�[�r�X
        Services.addInterceptor(
            WEB3AdminRuitoTradeStopService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // MQ-Gateway�@@Interceptor �ݒ菈��-----------------------

        // �ݐϓ������t�T�[�r�X
        Services.addInterceptor(
            WEB3RuitoBuyOrderService.class,
            new WEB3MQGatewayInterceptor());

        // �ݐϓ������T�[�r�X
        Services.addInterceptor(
            WEB3RuitoSellService.class,
            new WEB3MQGatewayInterceptor());

        // �ݐϓ�������T�[�r�X
        Services.addInterceptor(
            WEB3RuitoCancelService.class,
            new WEB3MQGatewayInterceptor());

        // �ݐϓ���MRF������t�P���T�[�r�X
        Services.addInterceptor(
            WEB3RuitoMRFOrderAcceptUnitService.class,
            new WEB3MQGatewayInterceptor());

        // �ݐϓ���������t�P���T�[�r�X
        Services.addInterceptor(
            WEB3RuitoOrderAcceptUnitService.class,
            new WEB3MQGatewayInterceptor());

        // �ݐϓ��������t�P���T�[�r�X
        Services.addInterceptor(
            WEB3RuitoCancelAcceptedUnitService.class,
            new WEB3MQGatewayInterceptor());

        // Message �̓o�^���� ----------------------

        // �ݐϓ������t�����������N�G�X�g
        regClass(WEB3RuitoBuyCompleteRequest.class);
        // �ݓ����t�����������X�|���X
        regClass(WEB3RuitoBuyCompleteResponse.class);

        // �ݐϓ������t�����m�F���N�G�X�g
        regClass(WEB3RuitoBuyConfirmRequest.class);
        // �ݐϓ������t�����m�F���X�|���X�N���X
        regClass(WEB3RuitoBuyConfirmResponse.class);

        // �ݓ��������̓��N�G�X�g�N���X
        regClass(WEB3RuitoBuyInputRequest.class);
        // �ݓ��������̓��X�|���X�N���X
        regClass(WEB3RuitoBuyInputResponse.class);

        // �ݐϓ��������t���N�G�X�g
        regClass(WEB3RuitoCancelAcceptRequest.class);
        // �ݐϓ��������t���X�|���X
        regClass(WEB3RuitoCancelAcceptResponse.class);

        // �ݐϓ�������������N�G�X�g
        regClass(WEB3RuitoCancelCompleteRequest.class);
        // �ݐϓ�������������X�|���X
        regClass(WEB3RuitoCancelCompleteResponse.class);

        // �ݐϓ�������m�F���N�G�X�g
        regClass(WEB3RuitoCancelConfirmRequest.class);
        // �ݐϓ�������m�F���X�|���X
        regClass(WEB3RuitoCancelConfirmResponse.class);

        // �ݐϓ������ʃ��N�G�X�g
        regClass(WEB3RuitoCommonRequest.class);

        // �ݐϓ������������ʒm���N�G�X�g
        regClass(WEB3RuitoDealingOrderNotifyRequest.class);
        // �ݐϓ������������ʒm���X�|���X
        regClass(WEB3RuitoDealingOrderNotifyResponse.class);

        // �ݐϓ���MRF�����t���N�G�X�g
        regClass(WEB3RuitoMRFCancelAcceptRequest.class);
        // �ݐϓ���MRF�����t���X�|���X
        regClass(WEB3RuitoMRFCancelAcceptResponse.class);

        // �ݐϓ���MRF������t���N�G�X�g
        regClass(WEB3RuitoMRFOrderAcceptRequest.class);
        // �ݐϓ���MRF������t���X�|���X
        regClass(WEB3RuitoMRFOrderAcceptResponse.class);

        // �ݐϓ���������t���N�G�X�g
        regClass(WEB3RuitoOrderAcceptRequest.class);
        // �ݐϓ���������t���X�|���X
        regClass(WEB3RuitoOrderAcceptResponse.class);

        // �ݓ������Ɖ�N�G�X�g
        regClass(WEB3RuitoOrderReferenceRequest.class);
        // �ݓ������Ɖ�X�|���X
        regClass(WEB3RuitoOrderReferenceResponse.class);

        // �ݓ���񊮗����N�G�X�g
        regClass(WEB3RuitoSellCompleteRequest.class);
        // �ݓ���񊮗����X�|���X
        regClass(WEB3RuitoSellCompleteResponse.class);

        // �ݓ����m�F���N�G�X�g
        regClass(WEB3RuitoSellConfirmRequest.class);
        // �ݓ����m�F���X�|���X
        regClass(WEB3RuitoSellConfirmResponse.class);

        // �ݓ������̓��N�G�X�g
        regClass(WEB3RuitoSellInputRequest.class);
        // �ݓ������̓��X�|���X
        regClass(WEB3RuitoSellInputResponse.class);

        // �ݓ����ꗗ���N�G�X�g
        regClass(WEB3RuitoSellListRequest.class);
        // �ݓ����ꗗ���X�|���X
        regClass(WEB3RuitoSellListResponse.class);

        //�ݓ������ʔ�����~�m�F���N�G�X�g
        regClass(WEB3AdminRuitoTradeStopConfirmRequest.class);
        //�ݓ������ʔ�����~�m�F���X�|���X
        regClass(WEB3AdminRuitoTradeStopConfirmResponse.class);
        //�ݓ������ʔ�����~�������N�G�X�g
        regClass(WEB3AdminRuitoTradeStopCompleteRequest.class);
        //�ݓ������ʔ�����~�������X�|���X
        regClass(WEB3AdminRuitoTradeStopCompleteResponse.class);
        //�ݓ������ʔ�����~���͉�ʃ��N�G�X�g
        regClass(WEB3AdminRuitoTradeStopInputRequest.class);
        //�ݓ������ʔ�����~���͉�ʃ��X�|���X
        regClass(WEB3AdminRuitoTradeStopInputResponse.class);


        //Handler �̓o�^���� ----------------------

        // �ݐϓ������t�����n���h�̓o�^
        regHandler(
            WEB3RuitoAppPlugin.class,
            WEB3RuitoBuyConfirmRequest.class,
            WEB3RuitoBuyOrderHandler.class,
            "confirmBuyOrder");
        regHandler(
            WEB3RuitoAppPlugin.class,
            WEB3RuitoBuyCompleteRequest.class,
            WEB3RuitoBuyOrderHandler.class,
            "submitBuyOrder");

        // �ݐϓ������t�������̓n���h���̓o�^
        regHandler(
            WEB3RuitoAppPlugin.class,
            WEB3RuitoBuyInputRequest.class,
            WEB3RuitoBuyOrderInputHandler.class,
            "ruitoBuyInputRequest");

        // �ݐϓ��������t�n���h���[�̓o�^
        regHandler(
            WEB3RuitoAppPlugin.class,
            WEB3RuitoCancelAcceptRequest.class,
            WEB3RuitoCancelAcceptedHandler.class,
            "cancelAcceptedRequest");

        // �ݐϓ������ �p�n���h���[�̓o�^
        regHandler(
            WEB3RuitoAppPlugin.class,
            WEB3RuitoCancelConfirmRequest.class,
            WEB3RuitoCancelHandler.class,
            "confirmCancel");
        regHandler(
            WEB3RuitoAppPlugin.class,
            WEB3RuitoCancelCompleteRequest.class,
            WEB3RuitoCancelHandler.class,
            "completeCancel");

        // �ݐϓ���MRF�����t �p�n���h���[�̓o�^
        regHandler(
            WEB3RuitoAppPlugin.class,
            WEB3RuitoMRFCancelAcceptRequest.class,
            WEB3RuitoMRFCancelAcceptHandler.class,
            "mrfCancelAcceptRequest");

        // �ݐϓ���MRF������t �p�n���h���[�̓o�^
        regHandler(
            WEB3RuitoAppPlugin.class,
            WEB3RuitoMRFOrderAcceptRequest.class,
            WEB3RuitoMRFOrderAcceptHandler.class,
            "mrfOrderAcceptRequest");

        // �ݐϓ���������t �p�n���h���[�̓o�^
        regHandler(
            WEB3RuitoAppPlugin.class,
            WEB3RuitoOrderAcceptRequest.class,
            WEB3RuitoOrderAcceptHandler.class,
            "orderAcceptRequest");

        // �ݐϓ��������Ɖ� �p�n���h���[�̓o�^
        regHandler(
            WEB3RuitoAppPlugin.class,
            WEB3RuitoOrderReferenceRequest.class,
            WEB3RuitoOrderReferenceHandler.class,
            "ruitoOrderReferenceRequest");

        // �ݐϓ������ �p�n���h���[�̓o�^
        regHandler(
            WEB3RuitoAppPlugin.class,
            WEB3RuitoSellConfirmRequest.class,
            WEB3RuitoSellHandler.class,
            "confirmSell");
        regHandler(
            WEB3RuitoAppPlugin.class,
            WEB3RuitoSellCompleteRequest.class,
            WEB3RuitoSellHandler.class,
            "completeSell");

        // �ݓ������� �p�n���h���[�̓o�^
        regHandler(
            WEB3RuitoAppPlugin.class,
            WEB3RuitoSellInputRequest.class,
            WEB3RuitoSellInputHandler.class,
            "sellInputRequest");

        // �ݐϓ������ꗗ �p�n���h���[�̓o�^
        regHandler(
            WEB3RuitoAppPlugin.class,
            WEB3RuitoSellListRequest.class,
            WEB3RuitoSellPossibleListReferenceHandler.class,
            "searchRuitoSellPossibleListReference");

        // �ݐϓ������������ʒm �p�n���h���[�̓o�^
        regHandler(
            WEB3RuitoAppPlugin.class,
            WEB3RuitoDealingOrderNotifyRequest.class,
            WEB3RuitoTradeOrderNotifyHandler.class,
            "tradeOrderNotifyRequest");

        // �Ǘ��җݓ������ʔ�����~�n���h���̓o�^
        regHandler(
            WEB3RuitoAppPlugin.class,
            WEB3AdminRuitoTradeStopInputRequest.class,
            WEB3AdminRuitoTradeStopHandler.class,
            "handleGetInputScreen");

        // �Ǘ��җݓ������ʔ�����~�n���h���̓o�^
        regHandler(
            WEB3RuitoAppPlugin.class,
            WEB3AdminRuitoTradeStopConfirmRequest.class,
            WEB3AdminRuitoTradeStopHandler.class,
            "handleComfirmTradeStop");

        // �Ǘ��җݓ������ʔ�����~�n���h���̓o�^
        regHandler(
            WEB3RuitoAppPlugin.class,
            WEB3AdminRuitoTradeStopCompleteRequest.class,
            WEB3AdminRuitoTradeStopHandler.class,
            "handleCompleteTradeStop");

        // ���u�� U01306�̎b��Ή� start
        //------------------------------------
        // RAC-CTX�C���^�Z�v�^��ݒ肷��
        //------------------------------------
        // �ݐϓ���������t�ꌏ
        Services.addInterceptor(
            WEB3RuitoOrderAcceptUnitService.class,
            new WEB3RuitoDescendRacCtxInterceptor());

        // �ݐϓ��������t�ꌏ
        Services.addInterceptor(
            WEB3RuitoCancelAcceptedUnitService.class,
            new WEB3RuitoDescendRacCtxInterceptor());

        // �ݐϓ������������ʒm�ꌏ
        Services.addInterceptor(
            WEB3RuitoTradeOrderNotifyUnitService.class,
            new WEB3RuitoDescendRacCtxInterceptor());

        // �l�q�e������t�ꌏ
        Services.addInterceptor(
            WEB3RuitoMRFOrderAcceptUnitService.class,
            new WEB3RuitoDescendRacCtxInterceptor());

        // �l�q�e�����t�ꌏ
        Services.addInterceptor(
            WEB3RuitoMRFCancelAcceptUnitService.class,
            new WEB3RuitoDescendRacCtxInterceptor());

        log.exiting(METHOD_NAME);

    }
}

@
