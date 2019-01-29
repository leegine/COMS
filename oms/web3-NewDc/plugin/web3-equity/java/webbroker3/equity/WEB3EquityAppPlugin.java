head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : Webbroker3-Equity �v���O�C���N���X(WEB3EquityAppPlugin.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/19 �����@@�ǘa(SRA) �V�K�쐬
                   2004/04/01 羐� (���u) �ǉ�
                   2004/12/16 �������F(SRA) �c�Č��Ή��ɂ��C��
                   2004/12/29 �����a��(SRA) �R�����g�̏C��
                   2005/01/06 �����a��(SRA) JavaDoc�C��
Revesion History : 2007/12/24 �юu��(���u) PTS�d�l�ύX
Revesion History : 2008/01/29 ��іQ(���u) ���f��1285,1286
*/
package webbroker3.equity;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3LogSysTimeInterceptor;
import webbroker3.equity.data.WEB3EquityMarketSessionDatabaseExtensions;
import webbroker3.equity.data.WEB3EquityMasterDatabaseExtensions;
import webbroker3.equity.handler.WEB3EquityAssetInquiryHandler;
import webbroker3.equity.handler.WEB3EquityBalanceReferenceHandler;
import webbroker3.equity.handler.WEB3EquityBookValuePriceRegistHandler;
import webbroker3.equity.handler.WEB3EquityCancelOrderHandler;
import webbroker3.equity.handler.WEB3EquityChangeCancelAcceptHandler;
import webbroker3.equity.handler.WEB3EquityChangeCancelNotifyMainHandler;
import webbroker3.equity.handler.WEB3EquityChangeOrderHandler;
import webbroker3.equity.handler.WEB3EquityChangeOrderInputHandler;
import webbroker3.equity.handler.WEB3EquityExecuteReferenceHandler;
import webbroker3.equity.handler.WEB3EquityMarginExecuteReferenceHandler;
import webbroker3.equity.handler.WEB3EquityOffFloorProductListHandler;
import webbroker3.equity.handler.WEB3EquityOrderAcceptHandler;
import webbroker3.equity.handler.WEB3EquityOrderBuyInputHandler;
import webbroker3.equity.handler.WEB3EquityOrderCarryOverHandler;
import webbroker3.equity.handler.WEB3EquityOrderCarryOverSkipHandler;
import webbroker3.equity.handler.WEB3EquityOrderExecEndNotifyHandler;
import webbroker3.equity.handler.WEB3EquityOrderExecNotifyMainHandler;
import webbroker3.equity.handler.WEB3EquityOrderHandler;
import webbroker3.equity.handler.WEB3EquityOrderNotifyHandler;
import webbroker3.equity.handler.WEB3EquityPTSExecEndNotifyHandler;
import webbroker3.equity.handler.WEB3EquityProductInformationHandler;
import webbroker3.equity.handler.WEB3EquityReceiveCloseOrderHandler;
import webbroker3.equity.handler.WEB3EquitySellOrderInputHandler;
import webbroker3.equity.handler.WEB3MarginBalanceReferenceHandler;
import webbroker3.equity.handler.WEB3MarginCancelHandler;
import webbroker3.equity.handler.WEB3MarginChangeCloseMarginHandler;
import webbroker3.equity.handler.WEB3MarginChangeCloseMarginInputHandler;
import webbroker3.equity.handler.WEB3MarginChangeOpenMarginHandler;
import webbroker3.equity.handler.WEB3MarginChangeOpenMarginInputHandler;
import webbroker3.equity.handler.WEB3MarginCloseMarginHandler;
import webbroker3.equity.handler.WEB3MarginCloseMarginInputServiceHandler;
import webbroker3.equity.handler.WEB3MarginCloseMarginListHandler;
import webbroker3.equity.handler.WEB3MarginContractReferenceHandler;
import webbroker3.equity.handler.WEB3MarginExecuteReferenceHandler;
import webbroker3.equity.handler.WEB3MarginOpenMarginHandler;
import webbroker3.equity.handler.WEB3MarginOpenMarginInputHandler;
import webbroker3.equity.handler.WEB3MarginOrderNotifyHandler;
import webbroker3.equity.handler.WEB3MarginSwapMarginAcceptHandler;
import webbroker3.equity.handler.WEB3MarginSwapMarginHandler;
import webbroker3.equity.handler.WEB3MarginSwapMarginInputHandler;
import webbroker3.equity.handler.WEB3MarginSwapOrderNotifyHandler;
import webbroker3.equity.handler.WEB3MstkBalanceReferenceHandler;
import webbroker3.equity.handler.WEB3MstkBookValuePriceRegistHandler;
import webbroker3.equity.handler.WEB3MstkBuyHandler;
import webbroker3.equity.handler.WEB3MstkCancelHandler;
import webbroker3.equity.handler.WEB3MstkExecuteReferenceHandler;
import webbroker3.equity.handler.WEB3MstkSellHandler;
import webbroker3.equity.marketadaptor.WEB3EquityMarketRequestSenderServiceImpl;
import webbroker3.equity.message.*;
import webbroker3.equity.service.delegate.WEB3EquityAssetInquiryService;
import webbroker3.equity.service.delegate.WEB3EquityBalanceReferenceService;
import webbroker3.equity.service.delegate.WEB3EquityBookValuePriceRegistService;
import webbroker3.equity.service.delegate.WEB3EquityCancelOrderService;
import webbroker3.equity.service.delegate.WEB3EquityChangeCancelAcceptService;
import webbroker3.equity.service.delegate.WEB3EquityChangeCancelAcceptUnitService;
import webbroker3.equity.service.delegate.WEB3EquityChangeCancelNotifyMainService;
import webbroker3.equity.service.delegate.WEB3EquityChangeOrderInputService;
import webbroker3.equity.service.delegate.WEB3EquityChangeOrderService;
import webbroker3.equity.service.delegate.WEB3EquityExecuteReferenceService;
import webbroker3.equity.service.delegate.WEB3EquityExecutedMailSenderService;
import webbroker3.equity.service.delegate.WEB3EquityFrontOrderService;
import webbroker3.equity.service.delegate.WEB3EquityMarginExecuteReferenceService;
import webbroker3.equity.service.delegate.WEB3EquityOffFloorProductListService;
import webbroker3.equity.service.delegate.WEB3EquityOrderAcceptService;
import webbroker3.equity.service.delegate.WEB3EquityOrderAcceptUnitService;
import webbroker3.equity.service.delegate.WEB3EquityOrderBuyInputService;
import webbroker3.equity.service.delegate.WEB3EquityOrderCarryOverService;
import webbroker3.equity.service.delegate.WEB3EquityOrderCarryOverSkipObjectCheckService;
import webbroker3.equity.service.delegate.WEB3EquityOrderCarryOverSkipService;
import webbroker3.equity.service.delegate.WEB3EquityOrderCarryOverSkipUnitService;
import webbroker3.equity.service.delegate.WEB3EquityOrderCarryOverUnitService;
import webbroker3.equity.service.delegate.WEB3EquityOrderExecEndNotifyService;
import webbroker3.equity.service.delegate.WEB3EquityOrderExecNotifyMainService;
import webbroker3.equity.service.delegate.WEB3EquityOrderExecNotifyUnitService;
import webbroker3.equity.service.delegate.WEB3EquityOrderNotifyService;
import webbroker3.equity.service.delegate.WEB3EquityOrderNotifyUnitService;
import webbroker3.equity.service.delegate.WEB3EquityOrderService;
import webbroker3.equity.service.delegate.WEB3EquityPTSCancelOrderService;
import webbroker3.equity.service.delegate.WEB3EquityPTSChangeOrderInputService;
import webbroker3.equity.service.delegate.WEB3EquityPTSChangeOrderService;
import webbroker3.equity.service.delegate.WEB3EquityPTSExecEndNotifyService;
import webbroker3.equity.service.delegate.WEB3EquityPTSOrderService;
import webbroker3.equity.service.delegate.WEB3EquityProductInformationService;
import webbroker3.equity.service.delegate.WEB3EquityReceiveCancelEventService;
import webbroker3.equity.service.delegate.WEB3EquityReceiveChangeEventService;
import webbroker3.equity.service.delegate.WEB3EquityReceiveCloseOrderService;
import webbroker3.equity.service.delegate.WEB3EquityReceiveCloseOrderUnitService;
import webbroker3.equity.service.delegate.WEB3EquitySellOrderInputService;
import webbroker3.equity.service.delegate.WEB3MarginBalanceReferenceService;
import webbroker3.equity.service.delegate.WEB3MarginCancelService;
import webbroker3.equity.service.delegate.WEB3MarginChangeCancelNotifyCancelUnitService;
import webbroker3.equity.service.delegate.WEB3MarginChangeCancelNotifyChangeUnitService;
import webbroker3.equity.service.delegate.WEB3MarginChangeCloseMarginInputService;
import webbroker3.equity.service.delegate.WEB3MarginChangeCloseMarginService;
import webbroker3.equity.service.delegate.WEB3MarginChangeOpenMarginInputService;
import webbroker3.equity.service.delegate.WEB3MarginChangeOpenMarginService;
import webbroker3.equity.service.delegate.WEB3MarginCloseMarginInputService;
import webbroker3.equity.service.delegate.WEB3MarginCloseMarginListService;
import webbroker3.equity.service.delegate.WEB3MarginCloseMarginService;
import webbroker3.equity.service.delegate.WEB3MarginContractReferenceService;
import webbroker3.equity.service.delegate.WEB3MarginExecuteReferenceService;
import webbroker3.equity.service.delegate.WEB3MarginOpenMarginInputService;
import webbroker3.equity.service.delegate.WEB3MarginOpenMarginService;
import webbroker3.equity.service.delegate.WEB3MarginOrderExecNotifyUnitService;
import webbroker3.equity.service.delegate.WEB3MarginOrderNotifyService;
import webbroker3.equity.service.delegate.WEB3MarginOrderNotifyUnitService;
import webbroker3.equity.service.delegate.WEB3MarginSwapMarginAcceptService;
import webbroker3.equity.service.delegate.WEB3MarginSwapMarginAcceptUnitService;
import webbroker3.equity.service.delegate.WEB3MarginSwapMarginInputService;
import webbroker3.equity.service.delegate.WEB3MarginSwapMarginService;
import webbroker3.equity.service.delegate.WEB3MarginSwapOrderNotifyService;
import webbroker3.equity.service.delegate.WEB3MarginSwapOrderNotifyUnitService;
import webbroker3.equity.service.delegate.WEB3MstkBalanceReferenceService;
import webbroker3.equity.service.delegate.WEB3MstkBookValuePriceRegistService;
import webbroker3.equity.service.delegate.WEB3MstkBuyService;
import webbroker3.equity.service.delegate.WEB3MstkCancelService;
import webbroker3.equity.service.delegate.WEB3MstkExecuteReferenceService;
import webbroker3.equity.service.delegate.WEB3MstkSellService;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityAssetInquiryServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityBalanceReferenceServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityBookValuePriceRegistServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityCancelOrderServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityChangeCancelAcceptServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityChangeCancelAcceptUnitServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityChangeCancelNotifyMainServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityChangeOrderInputServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityChangeOrderServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityExecuteReferenceServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityExecutedMailSenderServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityFrontOrderServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityMarginExecuteReferenceServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityOffFloorProductListServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityOrderAcceptServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityOrderAcceptUnitServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityOrderBuyInputServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityOrderCarryOverServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityOrderCarryOverSkipObjectCheckServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityOrderCarryOverSkipServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityOrderCarryOverSkipUnitServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityOrderCarryOverUnitServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityOrderExecEndNotifyServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityOrderExecNotifyMainServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityOrderExecNotifyUnitServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityOrderNotifyServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityOrderNotifyUnitServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityOrderServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityPTSCancelOrderServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityPTSChangeOrderInputServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityPTSChangeOrderServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityPTSExecEndNotifyServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityPTSOrderServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityProductInformationServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityReceiveCancelEventServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityReceiveChangeEventServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityReceiveCloseOrderServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityReceiveCloseOrderUnitServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquitySellOrderInputServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginBalanceReferenceServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginCancelServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginChangeCancelNotifyCancelUnitServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginChangeCancelNotifyChangeUnitServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginChangeCloseMarginInputServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginChangeCloseMarginServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginChangeOpenMarginInputServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginChangeOpenMarginServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginCloseMarginInputServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginCloseMarginListServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginCloseMarginServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginContractReferenceServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginExecuteReferenceServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginOpenMarginInputServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginOpenMarginServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginOrderExecNotifyUnitServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginOrderNotifyServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginOrderNotifyUnitServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginSwapMarginAcceptServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginSwapMarginAcceptUnitServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginSwapMarginInputServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginSwapMarginServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginSwapOrderNotifyServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginSwapOrderNotifyUnitServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3MstkBalanceReferenceServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3MstkBookValuePriceRegistServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3MstkBuyServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3MstkCancelServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3MstkExecuteReferenceServiceImpl;
import webbroker3.equity.service.delegate.stdimpls.WEB3MstkSellServiceImpl;
import webbroker3.mqgateway.WEB3MQGatewayInterceptor;
import webbroker3.rlsgateway.WEB3RlsGatewayInterceptor;
import webbroker3.util.WEB3LogUtility;

/**
 * �iWebbroker3-Equity �v���O�C���N���X�j�B
 * @@author �����@@�ǘa(SRA)
 * @@version 1.0
 */
public final class WEB3EquityAppPlugin extends Plugin {
    /**
     * ���O���[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityAppPlugin.class);

    /**
     * �R���X�g���N�^�B
     */
    public WEB3EquityAppPlugin()
    {
    }

    /**
     * �v���O�C���G���g���[�|�C���g�B
     */
    public static void plug() throws Exception {
        String METHOD_NAME = "plug()";
        log.entering(METHOD_NAME);
        plug(WEB3EquityAppPlugin.class);
        log.exiting(METHOD_NAME);
    }

    /**
     * �v���O�C�������B
     */
    public static void onPlug() throws Exception {
        String METHOD_NAME = "onPlug()";
        log.entering(METHOD_NAME);

        // ���̃v���O�C������ɓǂݍ��ޕK�v�̂���v���O�C���̎w��B
        // install the system plugins that we need
        KernelPlugin.plug();

        // DatabaseExtensions �̃v���O�C������ ----------------------
        // WEB3EquityMasterDatabaseExtensions ���v���O�C��
        WEB3EquityMasterDatabaseExtensions.plug();
        // WEB3EquityMarketSessionDatabaseExtensions ���v���O�C��
        WEB3EquityMarketSessionDatabaseExtensions.plug();

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        // �g���g�����U�N�V�����E�}�l�[�W���[��
        // �I�[�o�[���C�h���\�b�h���������ߊg��������W���[���N���X���쐬��
        // �g��������W���[���N���X���Őݒ�
        l_finApp.uninstallTradingModule("eqtype");
        l_finApp.installTradingModule(new WEB3EquityTradingModule());
        TradingModule l_tradingModule = l_finApp.getTradingModule("eqtype");

        // Adapter�̓o�^ ----------------------------------------
        // �s�ꃊ�N�G�X�g���M�T�[�r�X
        l_tradingModule.getMarketAdapter().installMarketRequestSenderService(
            new WEB3EquityMarketRequestSenderServiceImpl());

        // �g���v���_�N�g�E�}�l�[�W���[
        l_tradingModule.overrideProductManager(new WEB3EquityProductManager());

        // �v�Z�T�[�r�X�N���X
        l_tradingModule.overrideBizLogicProvider(
            new WEB3EquityBizLogicProvider());

        // PTS�����}�l�[�W��
        l_tradingModule.overrideOrderManager(
            new WEB3EquityPTSOrderManager());

        // �|�W�V�����}�l�[�W��
        l_tradingModule.overridePositionManager(
            new WEB3EquityPositionManager());

        // PTS�����R���ʃ`�F�b�N
        new WEB3EquityPTSOrderManagerReusableValidations().register();


        /*
         * ��������
         */
        // Service �̓o�^
        // 1) ���t�ꗗ�T�[�r�X
        Services.registerService(
            WEB3EquityAssetInquiryService.class,
            new WEB3EquityAssetInquiryServiceImpl());

        // 2) ������������T�[�r�X
        Services.registerService(
            WEB3EquityCancelOrderService.class,
            new WEB3EquityCancelOrderServiceImpl());

        // 3) �������������t�T�[�r�X
        Services.registerService(
            WEB3EquityChangeCancelAcceptService.class,
            new WEB3EquityChangeCancelAcceptServiceImpl());

        // 4) ���������������̓T�[�r�X
        Services.registerService(
            WEB3EquityChangeOrderInputService.class,
            new WEB3EquityChangeOrderInputServiceImpl());

        // 5) �������������T�[�r�X
        Services.registerService(
            WEB3EquityChangeOrderService.class,
            new WEB3EquityChangeOrderServiceImpl());

        // 6) ���������������Ɖ�T�[�r�X
        Services.registerService(
            WEB3EquityExecuteReferenceService.class,
            new WEB3EquityExecuteReferenceServiceImpl());

        // 7) ����������t�T�[�r�X
        Services.registerService(
            WEB3EquityOrderAcceptService.class,
            new WEB3EquityOrderAcceptServiceImpl());

        // 8) �����������t���̓T�[�r�X
        Services.registerService(
            WEB3EquityOrderBuyInputService.class,
            new WEB3EquityOrderBuyInputServiceImpl());

        // 9) �����J�z�ꌏ�T�[�r�X
        Services.registerService(
            WEB3EquityOrderCarryOverUnitService.class,
            new WEB3EquityOrderCarryOverUnitServiceImpl());

        // 10) �����J�z�T�[�r�X
        Services.registerService(
            WEB3EquityOrderCarryOverService.class,
            new WEB3EquityOrderCarryOverServiceImpl());

        // 11) ���������J�z�X�L�b�v�����ʒm�J�z�Ώۃ`�F�b�N�T�[�r�X
        Services.registerService(
            WEB3EquityOrderCarryOverSkipObjectCheckService.class,
            new WEB3EquityOrderCarryOverSkipObjectCheckServiceImpl());

        // 12) ���������J�z�X�L�b�v�����ʒm�ꌏ�T�[�r�X
        Services.registerService(
            WEB3EquityOrderCarryOverSkipUnitService.class,
            new WEB3EquityOrderCarryOverSkipUnitServiceImpl());

        // 13) ���������J�z�X�L�b�v�����ʒm�T�[�r�X
        Services.registerService(
            WEB3EquityOrderCarryOverSkipService.class,
            new WEB3EquityOrderCarryOverSkipServiceImpl());

        // 14) �o���I���ʒm�T�[�r�X
        Services.registerService(
            WEB3EquityOrderExecEndNotifyService.class,
            new WEB3EquityOrderExecEndNotifyServiceImpl());

        // 15) ���������o���ʒm�ꌏ�T�[�r�X
        Services.registerService(
            WEB3EquityOrderExecNotifyUnitService.class,
            new WEB3EquityOrderExecNotifyUnitServiceImpl());

        // 16) �����o���ʒm���C���T�[�r�X
        Services.registerService(
            WEB3EquityOrderExecNotifyMainService.class,
            new WEB3EquityOrderExecNotifyMainServiceImpl());

        // 17) ���������ʒm�ꌏ�T�[�r�X
        Services.registerService(
            WEB3EquityOrderNotifyUnitService.class,
            new WEB3EquityOrderNotifyUnitServiceImpl());

        // 18) ���������ʒm�T�[�r�X
        Services.registerService(
            WEB3EquityOrderNotifyService.class,
            new WEB3EquityOrderNotifyServiceImpl());

        // 19) ���������T�[�r�X
        Services.registerService(
            WEB3EquityOrderService.class,
            new WEB3EquityOrderServiceImpl());

        // 20) ����������������ʒm����ꌏ�T�[�r�X
        Services.registerService(
            WEB3EquityReceiveCancelEventService.class,
            new WEB3EquityReceiveCancelEventServiceImpl());

        // 21) ������������ʒm���C���T�[�r�X
        Services.registerService(
            WEB3EquityChangeCancelNotifyMainService.class,
            new WEB3EquityChangeCancelNotifyMainServiceImpl());

        // 22) ����������������ʒm�����ꌏ�T�[�r�X
        Services.registerService(
            WEB3EquityReceiveChangeEventService.class,
            new WEB3EquityReceiveChangeEventServiceImpl());

        // 23) ���������ʒm�ꌏ�T�[�r�X
        Services.registerService(
            WEB3EquityReceiveCloseOrderUnitService.class,
            new WEB3EquityReceiveCloseOrderUnitServiceImpl());

        // 24) ���������ʒm�T�[�r�X
        Services.registerService(
            WEB3EquityReceiveCloseOrderService.class,
            new WEB3EquityReceiveCloseOrderServiceImpl());

        // 25) �����������t�������̓T�[�r�X
        Services.registerService(
            WEB3EquitySellOrderInputService.class,
            new WEB3EquitySellOrderInputServiceImpl());

        // 26) ��胁�[�����M�T�[�r�X
        Services.registerService(
            WEB3EquityExecutedMailSenderService.class,
            new WEB3EquityExecutedMailSenderServiceImpl());

        // 27) �����������\���T�[�r�X
        Services.registerService(
            WEB3EquityProductInformationService.class,
            new WEB3EquityProductInformationServiceImpl());

        // 28) ����O���������ꗗ�T�[�r�X
        Services.registerService(
            WEB3EquityOffFloorProductListService.class,
            new WEB3EquityOffFloorProductListServiceImpl());

        // 29) ���������c���Ɖ�T�[�r�X
        Services.registerService(
            WEB3EquityBalanceReferenceService.class,
            new WEB3EquityBalanceReferenceServiceImpl());

        // 30) ���������뉿�P���o�^�T�[�r�X
        Services.registerService(
            WEB3EquityBookValuePriceRegistService.class,
            new WEB3EquityBookValuePriceRegistServiceImpl());

        // 31) ����������t�ꌏ�T�[�r�X
        Services.registerService(
            WEB3EquityOrderAcceptUnitService.class,
            new WEB3EquityOrderAcceptUnitServiceImpl());

        // 32) �������������t�ꌏ�T�[�r�X
        Services.registerService(
            WEB3EquityChangeCancelAcceptUnitService.class,
            new WEB3EquityChangeCancelAcceptUnitServiceImpl());

        // 33) ���������T�[�r�X
        Services.registerService(
            WEB3EquityFrontOrderService.class,
            new WEB3EquityFrontOrderServiceImpl());

        // 34) �����E�M�p�������Ɖ�T�[�r�X
        Services.registerService(
        	WEB3EquityMarginExecuteReferenceService.class,
            new WEB3EquityMarginExecuteReferenceServiceImpl());

        // 35) (PTS)�������������T�[�r�X
        Services.registerService(
            WEB3EquityPTSOrderService.class,
            new WEB3EquityPTSOrderServiceImpl());

        // 36) (PTS)����������������T�[�r�X
        Services.registerService(
            WEB3EquityPTSCancelOrderService.class,
            new WEB3EquityPTSCancelOrderServiceImpl());

        // 37) (PTS)�����������������T�[�r�X
        Services.registerService(
            WEB3EquityPTSChangeOrderService.class,
            new WEB3EquityPTSChangeOrderServiceImpl());

        // 38) (PTS)�������������������̓T�[�r�X
        Services.registerService(
            WEB3EquityPTSChangeOrderInputService.class,
            new WEB3EquityPTSChangeOrderInputServiceImpl());

        // 39) (PTS)�����o���I���ʒm�T�[�r�X
        Services.registerService(
            WEB3EquityPTSExecEndNotifyService.class,
            new WEB3EquityPTSExecEndNotifyServiceImpl());

        // Service�C���^�Z�v�^�̐ݒ�
        // 1) ���t�ꗗ�T�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
            WEB3EquityAssetInquiryService.class,
            new WEB3EquityAssetInquiryServiceInterceptor());

        // 2) ������������T�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
            WEB3EquityCancelOrderService.class,
            new WEB3EquityCancelOrderServiceInterceptor());

        // 3 �������������������̓T�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
            WEB3EquityChangeOrderInputService.class,
            new WEB3EquityChangeOrderInputServiceInterceptor());

        // 4) �������������T�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
            WEB3EquityChangeOrderService.class,
            new WEB3EquityChangeOrderServiceInterceptor());

        // 5) ���������������Ɖ�T�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
            WEB3EquityExecuteReferenceService.class,
            new WEB3EquityExecuteReferenceServiceInterceptor());

        // 6) �����������t�������̓T�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
            WEB3EquityOrderBuyInputService.class,
            new WEB3EquityOrderBuyInputServiceInterceptor());

        // 7) �����J�z�ꌏ�T�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
            WEB3EquityOrderCarryOverUnitService.class,
            new WEB3EquityOrderCarryOverUnitServiceInterceptor());

        // 8) ���������J�z�X�L�b�v�����ʒm�J�z�Ώۃ`�F�b�N�T�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
            WEB3EquityOrderCarryOverSkipObjectCheckService.class,
            new WEB3EquityOrderCarryOverSkipObjectCheckServiceInterceptor());

        // 9) ���������o���ʒm�ꌏ�T�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
            WEB3EquityOrderExecNotifyUnitService.class,
            new WEB3EquityOrderExecNotifyUnitServiceInterceptor());

        // 10) ���������ʒm�ꌏ�T�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
            WEB3EquityOrderNotifyUnitService.class,
            new WEB3EquityOrderNotifyPartServiceInterceptor());

        // 11) ���������T�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
            WEB3EquityOrderService.class,
            new WEB3EquityOrderServiceInterceptor());

        // 12) ����������������ʒm����ꌏ�T�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
            WEB3EquityReceiveCancelEventService.class,
            new WEB3EquityReceiveCancelEventServiceInterceptor());

        // 13) ����������������ʒm�����ꌏ�T�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
            WEB3EquityReceiveChangeEventService.class,
            new WEB3EquityReceiveChangeEventServiceInterceptor());

        // 14) �����������t�������̓T�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
            WEB3EquitySellOrderInputService.class,
            new WEB3EquitySellOrderInputServiceInterceptor());

        // 15) ���������ʒm�ꌏ�T�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
            WEB3EquityReceiveCloseOrderUnitService.class,
            new WEB3EquityReceiveCloseOrderUnitServiceInterceptor());

        // 16) �����������\���T�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
            WEB3EquityProductInformationService.class,
            new WEB3EquityProductInformationServiceInterceptor());

        // 17) ����O���������ꗗ�T�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
            WEB3EquityOffFloorProductListService.class,
            new WEB3EquityOffFloorProductListServiceInterceptor());

        // 18) ���������c���Ɖ�T�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
            WEB3EquityBalanceReferenceService.class,
            new WEB3EquityBalanceReferenceServiceInterceptor());

        // 19) ���������뉿�P���o�^�T�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
            WEB3EquityBookValuePriceRegistService.class,
            new WEB3EquityBookValuePriceRegistServiceInterceptor());

        // 20) ����������t�ꌏ�T�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
            WEB3EquityOrderAcceptUnitService.class,
            new WEB3EquityOrderAcceptUnitServiceInterceptor());

        // 21) ���������t�ꌏ�T�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
            WEB3EquityChangeCancelAcceptUnitService.class,
            new WEB3EquityChangeCancelAcceptUnitServiceInterceptor());

        // 22) �����E�M�p�������Ɖ�T�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
        	WEB3EquityMarginExecuteReferenceService.class,
            new WEB3EquityMarginExecuteReferenceServiceInterceptor());

        // 23) (PTS)�������������T�[�r�X
        Services.addInterceptor(
            WEB3EquityPTSOrderService.class,
            new WEB3EquityPTSOrderServiceInterceptor());

        // 24) (PTS)����������������T�[�r�X
        Services.addInterceptor(
            WEB3EquityPTSCancelOrderService.class,
            new WEB3EquityPTSCancelOrderServiceInterceptor());

        // 25) (PTS)�����������������T�[�r�X
        Services.addInterceptor(
            WEB3EquityPTSChangeOrderService.class,
            new WEB3EquityPTSChangeOrderServiceInterceptor());

        // 26) (PTS)�������������������̓T�[�r�X
        Services.addInterceptor(
            WEB3EquityPTSChangeOrderInputService.class,
            new WEB3EquityPTSChangeOrderInputServiceInterceptor());

        // �����g�����U�N�V�����ݒ�
        // 1) �����J�z�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3EquityOrderCarryOverUnitService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        // 2) ���������J�z�X�L�b�v�����ʒm�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3EquityOrderCarryOverSkipUnitService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        // 3) ���������ʒm�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3EquityReceiveCloseOrderUnitService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        // 4) ���������o���ʒm�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3EquityOrderExecNotifyUnitService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        // 5) ���������ʒm�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3EquityOrderNotifyUnitService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // 6) ����������������ʒm����ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3EquityReceiveCancelEventService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        // 7) ����������������ʒm�����ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3EquityReceiveChangeEventService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        // 8) ���������T�[�r�X
        Services.addInterceptor(
            WEB3EquityOrderService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // 9) ����������t�T�[�r�X
        Services.addInterceptor(
            WEB3EquityOrderAcceptService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // 10) �������������T�[�r�X
        Services.addInterceptor(
            WEB3EquityChangeOrderService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // 11) ������������T�[�r�X
        Services.addInterceptor(
            WEB3EquityCancelOrderService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // 12) �������������t�T�[�r�X
        Services.addInterceptor(
            WEB3EquityChangeCancelAcceptService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // 13) ������������ʒm���C���T�[�r�X
        Services.addInterceptor(
            WEB3EquityChangeCancelNotifyMainService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // 14) ��胁�[�����M�T�[�r�X
        Services.addInterceptor(
            WEB3EquityExecutedMailSenderService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        // 15) �����J�z�T�[�r�X
        Services.addInterceptor(
            WEB3EquityOrderCarryOverService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // 16) ���������J�z�X�L�b�v�����ʒm�J�z�Ώۃ`�F�b�N�T�[�r�X
        Services.addInterceptor(
            WEB3EquityOrderCarryOverSkipObjectCheckService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // 17) ���������J�z�X�L�b�v�����ʒm�T�[�r�X
        Services.addInterceptor(
            WEB3EquityOrderCarryOverSkipService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // 18) �o���I���ʒm�T�[�r�X
        Services.addInterceptor(
            WEB3EquityOrderExecEndNotifyService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // 19) �����o���ʒm���C���T�[�r�X
        Services.addInterceptor(
            WEB3EquityOrderExecNotifyMainService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // 20) ���������ʒm�T�[�r�X
        Services.addInterceptor(
            WEB3EquityOrderNotifyService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // 21) ���������ʒm�T�[�r�X
        Services.addInterceptor(
            WEB3EquityReceiveCloseOrderService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // 22) ���������뉿�P���o�^�T�[�r�X
        Services.addInterceptor(
            WEB3EquityBookValuePriceRegistService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // 23) ����������t�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3EquityOrderAcceptUnitService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // 24) �������������t�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3EquityChangeCancelAcceptUnitService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // 25) �����E�M�p�������Ɖ�T�[�r�X
        Services.addInterceptor(
        		WEB3EquityMarginExecuteReferenceService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        // 23) (PTS)�������������T�[�r�X
        Services.addInterceptor(
            WEB3EquityPTSOrderService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // 24) (PTS)����������������T�[�r�X
        Services.addInterceptor(
            WEB3EquityPTSCancelOrderService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // 25) (PTS)�����������������T�[�r�X
        Services.addInterceptor(
            WEB3EquityPTSChangeOrderService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));
        
        // 26) (PTS)�����o���I���ʒm�T�[�r�X
        Services.addInterceptor(
            WEB3EquityPTSExecEndNotifyService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // ���[���G���W���T�[�o�E�C���^�Z�v�^�̐ݒ�
        // 1) ������������T�[�r�X
        Services.addInterceptor(
            WEB3EquityCancelOrderService.class,
            new WEB3RlsGatewayInterceptor());

        // 2) �������������T�[�r�X
        Services.addInterceptor(
            WEB3EquityChangeOrderService.class,
            new WEB3RlsGatewayInterceptor());

        // 3) �����J�z�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3EquityOrderCarryOverUnitService.class,
            new WEB3RlsGatewayInterceptor());

        // 4) ���������T�[�r�X
        Services.addInterceptor(
            WEB3EquityOrderService.class,
            new WEB3RlsGatewayInterceptor());

        // 5) �����o���ʒm�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3EquityOrderExecNotifyUnitService.class,
            new WEB3RlsGatewayInterceptor());

        // 6) ������������ʒm�����ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3EquityReceiveChangeEventService.class,
            new WEB3RlsGatewayInterceptor());

        // MQ-Gateway�C���^�Z�v�^�̐ݒ�
        // 1) ������������T�[�r�X
        Services.addInterceptor(
            WEB3EquityCancelOrderService.class,
            new WEB3MQGatewayInterceptor());

        // 2) �������������T�[�r�X
        Services.addInterceptor(
            WEB3EquityChangeOrderService.class,
            new WEB3MQGatewayInterceptor());

        // 3) �����J�z�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3EquityOrderCarryOverUnitService.class,
            new WEB3MQGatewayInterceptor());

        // 4) ���������T�[�r�X
        Services.addInterceptor(
            WEB3EquityOrderService.class,
            new WEB3MQGatewayInterceptor());

        // �������ԃ��O�o�̓C���^�Z�v�^�̐ݒ�
        // 1) ���t�ꗗ�T�[�r�X
        Services.addInterceptor(
            WEB3EquityAssetInquiryService.class,
            new WEB3LogSysTimeInterceptor());

        // 2) ������������T�[�r�X
        Services.addInterceptor(
            WEB3EquityCancelOrderService.class,
            new WEB3LogSysTimeInterceptor());

        // 3) �������������t�T�[�r�X
        Services.addInterceptor(
            WEB3EquityChangeCancelAcceptService.class,
            new WEB3LogSysTimeInterceptor());

        // 4) ���������������̓T�[�r�X
        Services.addInterceptor(
            WEB3EquityChangeOrderInputService.class,
            new WEB3LogSysTimeInterceptor());

        // 5) �������������T�[�r�X
        Services.addInterceptor(
            WEB3EquityChangeOrderService.class,
            new WEB3LogSysTimeInterceptor());

        // 6) ���������������Ɖ�T�[�r�X
        Services.addInterceptor(
            WEB3EquityExecuteReferenceService.class,
            new WEB3LogSysTimeInterceptor());

        // 7) ����������t�T�[�r�X
        Services.addInterceptor(
            WEB3EquityOrderAcceptService.class,
            new WEB3LogSysTimeInterceptor());

        // 8) �����������t���̓T�[�r�X
        Services.addInterceptor(
            WEB3EquityOrderBuyInputService.class,
            new WEB3LogSysTimeInterceptor());

        // 9) �����J�z�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3EquityOrderCarryOverUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // 10) �����J�z�T�[�r�X
        Services.addInterceptor(
            WEB3EquityOrderCarryOverService.class,
            new WEB3LogSysTimeInterceptor());

        // 11) ���������J�z�X�L�b�v�����ʒm�J�z�Ώۃ`�F�b�N�T�[�r�X
        Services.addInterceptor(
            WEB3EquityOrderCarryOverSkipObjectCheckService.class,
            new WEB3LogSysTimeInterceptor());

        // 12) ���������J�z�X�L�b�v�����ʒm�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3EquityOrderCarryOverSkipUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // 13) ���������J�z�X�L�b�v�����ʒm�T�[�r�X
        Services.addInterceptor(
            WEB3EquityOrderCarryOverSkipService.class,
            new WEB3LogSysTimeInterceptor());

        // 14) �o���I���ʒm�T�[�r�X
        Services.addInterceptor(
            WEB3EquityOrderExecEndNotifyService.class,
            new WEB3LogSysTimeInterceptor());

        // 15) ���������o���ʒm�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3EquityOrderExecNotifyUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // 16) �����o���ʒm���C���T�[�r�X
        Services.addInterceptor(
            WEB3EquityOrderExecNotifyMainService.class,
            new WEB3LogSysTimeInterceptor());

        // 17) ���������ʒm�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3EquityOrderNotifyUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // 18) ���������ʒm�T�[�r�X
        Services.addInterceptor(
            WEB3EquityOrderNotifyService.class,
            new WEB3LogSysTimeInterceptor());

        // 19) ���������T�[�r�X
        Services.addInterceptor(
            WEB3EquityOrderService.class,
            new WEB3LogSysTimeInterceptor());

        // 20) ����������������ʒm����ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3EquityReceiveCancelEventService.class,
            new WEB3LogSysTimeInterceptor());

        // 21) ������������ʒm���C���T�[�r�X
        Services.addInterceptor(
            WEB3EquityChangeCancelNotifyMainService.class,
            new WEB3LogSysTimeInterceptor());

        // 22) ����������������ʒm�����ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3EquityReceiveChangeEventService.class,
            new WEB3LogSysTimeInterceptor());

        // 23) ���������ʒm�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3EquityReceiveCloseOrderUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // 24) ���������ʒm�T�[�r�X
        Services.addInterceptor(
            WEB3EquityReceiveCloseOrderService.class,
            new WEB3LogSysTimeInterceptor());

        // 25) �����������t�������̓T�[�r�X
        Services.addInterceptor(
            WEB3EquitySellOrderInputService.class,
            new WEB3LogSysTimeInterceptor());

        // 26) �����������\���T�[�r�X
        Services.addInterceptor(
            WEB3EquityProductInformationService.class,
            new WEB3LogSysTimeInterceptor());

        // 27) ����O���������ꗗ�T�[�r�X
        Services.addInterceptor(
            WEB3EquityOffFloorProductListService.class,
            new WEB3LogSysTimeInterceptor());

        // 28) ���������c���Ɖ�T�[�r�X
        Services.addInterceptor(
            WEB3EquityBalanceReferenceService.class,
            new WEB3LogSysTimeInterceptor());

        // 29) ���������뉿�P���o�^�T�[�r�X
        Services.addInterceptor(
            WEB3EquityBookValuePriceRegistService.class,
            new WEB3LogSysTimeInterceptor());

        // 30) ����������t�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3EquityOrderAcceptUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // 31) �������������t�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3EquityChangeCancelAcceptUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // 32) �����E�M�p�������Ɖ�T�[�r
        Services.addInterceptor(
        		WEB3EquityMarginExecuteReferenceService.class,
            new WEB3LogSysTimeInterceptor());

        // 33) (PTS)����������������T�[�r�X
        Services.addInterceptor(
            WEB3EquityPTSCancelOrderService.class,
            new WEB3LogSysTimeInterceptor());

        // 34) (PTS)�����������������T�[�r�X
        Services.addInterceptor(
            WEB3EquityPTSChangeOrderService.class,
            new WEB3LogSysTimeInterceptor());

        // 35) (PTS)�������������������̓T�[�r�X
        Services.addInterceptor(
            WEB3EquityPTSChangeOrderInputService.class,
            new WEB3LogSysTimeInterceptor());

        // 36) (PTS)�����o���I���ʒm�T�[�r�X
        Services.addInterceptor(
            WEB3EquityPTSExecEndNotifyService.class,
            new WEB3LogSysTimeInterceptor());

        // Message �̓o�^
        // 1-1) �����������t�����������N�G�X�g
        regClass(WEB3EquityBuyCompleteRequest.class);
        // 1-2) �����������t�����������X�|���X
        regClass(WEB3EquityBuyCompleteResponse.class);

        // 2-1) �����������t�����m�F���N�G�X�g
        regClass(WEB3EquityBuyConfirmRequest.class);
        // 2-2) �����������t�����m�F���X�|���X
        regClass(WEB3EquityBuyConfirmResponse.class);

        // 3-1) �����������t�������̓��N�G�X�g
        regClass(WEB3EquityBuyInputRequest.class);
        // 3-2) �����������t�������̓��X�|���X
        regClass(WEB3EquityBuyInputResponse.class);

        // 4-1) ����������������������N�G�X�g
        regClass(WEB3EquityCancelCompleteRequest.class);
        // 4-2) ����������������������X�|���X
        regClass(WEB3EquityCancelCompleteResponse.class);

        // 5-1) ����������������m�F���N�G�X�g
        regClass(WEB3EquityCancelConfirmRequest.class);
        // 5-2) ����������������m�F���X�|���X
        regClass(WEB3EquityCancelConfirmResponse.class);

        // 6-1) �����J�z�X�L�b�v�����ʒm���N�G�X�g
        regClass(WEB3EquityCarryOverSkipRequest.class);
        // 6-2) �����J�z�X�L�b�v�����ʒm���X�|���X
        regClass(WEB3EquityCarryOverSkipResponse.class);

        // 7-1) �������������t���N�G�X�g
        regClass(WEB3EquityChangeCancelAcceptRequest.class);
        // 7-2) �������������t���X�|���X
        regClass(WEB3EquityChangeCancelAcceptResponse.class);

        // 8-1) �����������������������N�G�X�g
        regClass(WEB3EquityChangeCompleteRequest.class);
        // 8-2) �����������������������X�|���X
        regClass(WEB3EquityChangeCompleteResponse.class);

        // 9-1) �����������������m�F���N�G�X�g
        regClass(WEB3EquityChangeConfirmRequest.class);
        // 9-2) �����������������m�F���X�|���X
        regClass(WEB3EquityChangeConfirmResponse.class);

        // 10-1) �������������������̓��N�G�X�g
        regClass(WEB3EquityChangeInputRequest.class);
        // 10-2) �������������������̓��X�|���X
        regClass(WEB3EquityChangeInputResponse.class);

        // 11-1) �������N�G�X�g
        regClass(WEB3EquityCloseOrderRequest.class);
        // 11-2) �������X�|���X
        regClass(WEB3EquityCloseOrderResponse.class);

        // 12-1) �o���I���ʒm���N�G�X�g
        regClass(WEB3EquityExecEndNotifyRequest.class);
        // 12-2) �o���I���ʒm���X�|���X
        regClass(WEB3EquityExecEndNotifyResponse.class);

        // 13-1) �����o���ʒm���C�����N�G�X�g
        regClass(WEB3EquityExecNotifyMainRequest.class);
        // 13-2) �o���ʒm���X�|���X
        regClass(WEB3EquityExecNotifyMainResponse.class);

        // 14-1) ���������������ڍ׃��N�G�X�g
        regClass(WEB3EquityExecuteDetailsRequest.class);
        // 14-2) ���������������ڍ׃��X�|���X
        regClass(WEB3EquityExecuteDetailsResponse.class);

        // 15-1) ���������������Ɖ�N�G�X�g
        regClass(WEB3EquityExecuteReferenceRequest.class);
        // 15-2) ���������������Ɖ�X�|���X
        regClass(WEB3EquityExecuteReferenceResponse.class);

        // 16-1) ����������t���N�G�X�g
        regClass(WEB3EquityOrderAcceptRequest.class);
        // 16-2) ����������t���X�|���X
        regClass(WEB3EquityOrderAcceptResponse.class);

        // 17-1) �����J�z���N�G�X�g
        regClass(WEB3EquityOrderCarryOverRequest.class);
        // 17-2) �����J�z���X�|���X
        regClass(WEB3EquityOrderCarryOverResponse.class);

        // 18-1) ��������������藚�����N�G�X�g
        regClass(WEB3EquityOrderHistoryRequest.class);
        // 18-2) ��������������藚�����X�|���X
        regClass(WEB3EquityOrderHistoryResponse.class);

        // 19-1) �������������ʒm���N�G�X�g
        regClass(WEB3EquityOrderNotifyRequest.class);
        // 19-2) �������������ʒm���X�|���X
        regClass(WEB3EquityOrderNotifyResponse.class);

        // 20-1) ��������ʒm���C�����N�G�X�g
        regClass(WEB3EquityChangeCancelNotifyMainRequest.class);
        // 20-2) ��������ʒm���X�|���X
        regClass(WEB3EquityChangeCancelNotifyMainResponse.class);

        // 21-1) �����������t�����������N�G�X�g
        regClass(WEB3EquitySellCompleteRequest.class);
        // 21-2) �����������t�����������X�|���X
        regClass(WEB3EquitySellCompleteResponse.class);

        // 22-1) �����������t�����m�F���N�G�X�g
        regClass(WEB3EquitySellConfirmRequest.class);
        // 22-2) �����������t�����m�F���X�|���X
        regClass(WEB3EquitySellConfirmResponse.class);

        // 23-1) �����������t�������̓��N�G�X�g
        regClass(WEB3EquitySellInputRequest.class);
        // 23-2) �����������t�������̓��X�|���X
        regClass(WEB3EquitySellInputResponse.class);

        // 24-1) �����������t�ꗗ���N�G�X�g
        regClass(WEB3EquitySellListRequest.class);
        // 24-2) �����������t�ꗗ���X�|���X
        regClass(WEB3EquitySellListResponse.class);

        // 25-1) �����������\�����N�G�X�g
        regClass(WEB3EquityProductInformationRequest.class);
        // 25-2) �����������\�����X�|���X
        regClass(WEB3EquityProductInformationResponse.class);

        // 26-1) ����O���������ꗗ�\���T�[�r�X
        regClass(WEB3EquityOffFloorProductListRequest.class);
        // 26-2) ����O���������ꗗ�\�����X�|���X
        regClass(WEB3EquityOffFloorProductListResponse.class);

        // 27-1) �����������t���������I��\�����N�G�X�g
        regClass(WEB3EquityProductSelectRequest.class);
        // 27-2) �����������t���������I��\�����X�|���X
        regClass(WEB3EquityProductSelectResponse.class);

        // 28-1) ���������c���Ɖ�N�G�X�g
        regClass(WEB3EquityBalanceReferenceRequest.class);
        // 28-2) ���������c���Ɖ�X�|���X
        regClass(WEB3EquityBalanceReferenceResponse.class);

        // 29-1) ���������c�����v���N�G�X�g
        regClass(WEB3EquityBalanceReferenceTotalRequest.class);
        // 29-2) ���������c�����v���X�|���X
        regClass(WEB3EquityBalanceReferenceTotalResponse.class);

        // 30-1) ���������뉿�P���o�^���̓��N�G�X�g
        regClass(WEB3EquityBookPriceInputRequest.class);
        // 30-2) ���������뉿�P���o�^���̓��X�|���X
        regClass(WEB3EquityBookPriceInputResponse.class);

        // 31-1) ���������뉿�P���o�^���N�G�X�g
        regClass(WEB3EquityBookPriceRegistRequest.class);
        // 31-2) ���������뉿�P���o�^���X�|���X
        regClass(WEB3EquityBookPriceRegistResponse.class);

        // 32-1) �����E�M�p�������Ɖ�N�G�X�g
        regClass(WEB3EquityMarginExecuteReferenceRequest.class);
        // 32-2) �����E�M�p�������Ɖ�X�|���X
        regClass(WEB3EquityMarginExecuteReferenceResponse.class);

        // 33-1) (PTS)�����o���I���ʒm���N�G�X�g
        regClass(WEB3EquityPTSExecEndNotifyRequest.class);
        // 33-2) (PTS)�����o���I���ʒm���X�|���X
        regClass(WEB3EquityPTSExecEndNotifyResponse.class);

        // Handler �̓o�^
        // 1) �����������t�ꗗ���N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquitySellListRequest.class,
            WEB3EquityAssetInquiryHandler.class,
            "assetInquiryRequest");

        // 2) ����������������m�F���N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquityCancelConfirmRequest.class,
            WEB3EquityCancelOrderHandler.class,
            "equityCancelConfirmRequest");

        // 3) ����������������������N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquityCancelCompleteRequest.class,
            WEB3EquityCancelOrderHandler.class,
            "equityCancelCompleteRequest");

        // 4) �������������t���N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquityChangeCancelAcceptRequest.class,
            WEB3EquityChangeCancelAcceptHandler.class,
            "equityChangeCancelAcceptRequest");

        // 5) �����������������m�F���N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquityChangeConfirmRequest.class,
            WEB3EquityChangeOrderHandler.class,
            "equityChangeOrderConfirmRequest");

        // 6) �����������������������N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquityChangeCompleteRequest.class,
            WEB3EquityChangeOrderHandler.class,
            "equityChangeOrderCompleteRequest");

        // 7) �������������������̓��N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquityChangeInputRequest.class,
            WEB3EquityChangeOrderInputHandler.class,
            "equityChangeOrderInputRequest");

        // 8) ���������������Ɖ�N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquityExecuteReferenceRequest.class,
            WEB3EquityExecuteReferenceHandler.class,
            "searchExecuteReference");

        // 9) ���������������ڍ׃��N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquityExecuteDetailsRequest.class,
            WEB3EquityExecuteReferenceHandler.class,
            "searchExecuteDetails");

        // 10) ��������������藚�����N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquityOrderHistoryRequest.class,
            WEB3EquityExecuteReferenceHandler.class,
            "searchOrderHistory");

        // 11) ����������t���N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquityOrderAcceptRequest.class,
            WEB3EquityOrderAcceptHandler.class,
            "equityOrderAcceptRequest");

        // 12) �����������t�������̓��N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquityBuyInputRequest.class,
            WEB3EquityOrderBuyInputHandler.class,
            "equityOrderBuyInputRequest");

        // 13) ���������J�z���N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquityOrderCarryOverRequest.class,
            WEB3EquityOrderCarryOverHandler.class,
            "completeCarryOver");

        // 14) �����J�z�X�L�b�v�����ʒm���N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquityCarryOverSkipRequest.class,
            WEB3EquityOrderCarryOverSkipHandler.class,
            "completeNotify");

        // 15) �o���I���ʒm���N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquityExecEndNotifyRequest.class,
            WEB3EquityOrderExecEndNotifyHandler.class,
            "completeNotify");

        // 16) �����o���ʒm���C�����N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquityExecNotifyMainRequest.class,
            WEB3EquityOrderExecNotifyMainHandler.class,
            "equityOrderExecNotifyMainRequest");

        // 17) �����������t�����m�F���N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquityBuyConfirmRequest.class,
            WEB3EquityOrderHandler.class,
            "equityBuyOrderConfirm");

        // 18) �����������t�����������N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquityBuyCompleteRequest.class,
            WEB3EquityOrderHandler.class,
            "equityBuyOrderComplete");

        // 19) �����������t�����m�F���N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquitySellConfirmRequest.class,
            WEB3EquityOrderHandler.class,
            "equitySellOrderConfirm");

        // 20) �����������t�����������N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquitySellCompleteRequest.class,
            WEB3EquityOrderHandler.class,
            "equitySellOrderComplete");

        // 21) �������������ʒm���N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquityOrderNotifyRequest.class,
            WEB3EquityOrderNotifyHandler.class,
            "WEB3EquityOrderNotifyRequest");

        // 22) ������������ʒm���C�����N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquityChangeCancelNotifyMainRequest.class,
            WEB3EquityChangeCancelNotifyMainHandler.class,
            "equityChangeCancelNotifyMainRequest");

        // 23) ���������ʒm���N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquityCloseOrderRequest.class,
            WEB3EquityReceiveCloseOrderHandler.class,
            "receiveCloseOrder");

        // 24) �����������t�������̓��N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquitySellInputRequest.class,
            WEB3EquitySellOrderInputHandler.class,
            "equitySellOrderInputRequest");

        // 25) �����������\�����N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquityProductInformationRequest.class,
            WEB3EquityProductInformationHandler.class,
            "equityProductInformationRequest");

        // 26) ����O���������ꗗ���N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquityOffFloorProductListRequest.class,
            WEB3EquityOffFloorProductListHandler.class,
            "equityOffFloorProductListRequest");

        // 27) �����������t���������I�����N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquityProductSelectRequest.class,
            WEB3EquityOrderBuyInputHandler.class,
            "equityProductSelectRequest");

        // 28) �����������c���Ɖ�N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquityBalanceReferenceRequest.class,
            WEB3EquityBalanceReferenceHandler.class,
            "getBalanceReference");

        // 29) ���������c���Ɖ�c�����v���N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquityBalanceReferenceTotalRequest.class,
            WEB3EquityBalanceReferenceHandler.class,
            "getBalanceTotal");

        // 30) ���������뉿�P���o�^���̓��N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquityBookPriceInputRequest.class,
            WEB3EquityBookValuePriceRegistHandler.class,
            "getInputScreen");

        // 31) ���������뉿�P���o�^���N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquityBookPriceRegistRequest.class,
            WEB3EquityBookValuePriceRegistHandler.class,
            "completeBookValuePrice");

        // 32) �����E�M�p�������Ɖ�N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquityMarginExecuteReferenceRequest.class,
            WEB3EquityMarginExecuteReferenceHandler.class,
            "searchExecuteReference");

        // 33) (PTS)�����o���I���ʒm�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3EquityPTSExecEndNotifyRequest.class,
            WEB3EquityPTSExecEndNotifyHandler.class,
            "completeNotify");

        /*
         * �M�p���
         */
        // Service �̓o�^
        // 1) �M�p������ψꗗ�T�[�r�X
        Services.registerService(
            WEB3MarginCloseMarginListService.class,
            new WEB3MarginCloseMarginListServiceImpl());

        // 2) �M�p��������Ɖ�T�[�r�X
        Services.registerService(
            WEB3MarginContractReferenceService.class,
            new WEB3MarginContractReferenceServiceImpl());

        // 3) �M�p����������n�T�[�r�X
        Services.registerService(
            WEB3MarginSwapMarginService.class,
            new WEB3MarginSwapMarginServiceImpl());

        // 4) �M�p����������n��t�T�[�r�X
        Services.registerService(
            WEB3MarginSwapMarginAcceptService.class,
            new WEB3MarginSwapMarginAcceptServiceImpl());

        // 5) �M�p����������n��t�ꌏ�T�[�r�X
        Services.registerService(
            WEB3MarginSwapMarginAcceptUnitService.class,
            new WEB3MarginSwapMarginAcceptUnitServiceImpl());

        // 6) �M�p����������n���̓T�[�r�X
        Services.registerService(
            WEB3MarginSwapMarginInputService.class,
            new WEB3MarginSwapMarginInputServiceImpl());

        // 7) �M�p�������T�[�r�X
        Services.registerService(
            WEB3MarginCancelService.class,
            new WEB3MarginCancelServiceImpl());

        // 8) �M�p����o���ʒm�ꌏ�T�[�r�X
        Services.registerService(
            WEB3MarginOrderExecNotifyUnitService.class,
            new WEB3MarginOrderExecNotifyUnitServiceImpl());

        // 9) �M�p����V�K���T�[�r�X
        Services.registerService(
            WEB3MarginOpenMarginService.class,
            new WEB3MarginOpenMarginServiceImpl());

        // 10) �M�p����V�K�����̓T�[�r�X
        Services.registerService(
            WEB3MarginOpenMarginInputService.class,
            new WEB3MarginOpenMarginInputServiceImpl());

        // 11) �M�p��������ʒm�T�[�r�X
        Services.registerService(
            WEB3MarginOrderNotifyService.class,
            new WEB3MarginOrderNotifyServiceImpl());

        // 12) �M�p��������ʒm�ꌏ�T�[�r�X
        Services.registerService(
            WEB3MarginOrderNotifyUnitService.class,
            new WEB3MarginOrderNotifyUnitServiceImpl());

        // 13) �M�p����������Ɖ�T�[�r�X
        Services.registerService(
            WEB3MarginExecuteReferenceService.class,
            new WEB3MarginExecuteReferenceServiceImpl());

        // 14) �M�p�����������ʒm����ꌏ�T�[�r�X
        Services.registerService(
            WEB3MarginChangeCancelNotifyCancelUnitService.class,
            new WEB3MarginChangeCancelNotifyCancelUnitServiceImpl());

        // 15) �M�p�����������ʒm�����ꌏ�T�[�r�X
        Services.registerService(
            WEB3MarginChangeCancelNotifyChangeUnitService.class,
            new WEB3MarginChangeCancelNotifyChangeUnitServiceImpl());

        // 16) �M�p��������V�K���T�[�r�X
        Services.registerService(
            WEB3MarginChangeOpenMarginService.class,
            new WEB3MarginChangeOpenMarginServiceImpl());

        // 17) �M�p��������V�K�����̓T�[�r�X
        Services.registerService(
            WEB3MarginChangeOpenMarginInputService.class,
            new WEB3MarginChangeOpenMarginInputServiceImpl());

        // 18) �M�p��������ԍσT�[�r�X
        Services.registerService(
            WEB3MarginChangeCloseMarginService.class,
            new WEB3MarginChangeCloseMarginServiceImpl());

        // 19) �M�p��������ԍϓ��̓T�[�r�X
        Services.registerService(
            WEB3MarginChangeCloseMarginInputService.class,
            new WEB3MarginChangeCloseMarginInputServiceImpl());

        // 20) �M�p����ԍσT�[�r�X
        Services.registerService(
            WEB3MarginCloseMarginService.class,
            new WEB3MarginCloseMarginServiceImpl());

        // 21) �M�p����ԍϓ��̓T�[�r�X
        Services.registerService(
            WEB3MarginCloseMarginInputService.class,
            new WEB3MarginCloseMarginInputServiceImpl());

        // 22) �M�p����������n�����ʒm�T�[�r�X
        Services.registerService(
            WEB3MarginSwapOrderNotifyService.class,
            new WEB3MarginSwapOrderNotifyServiceImpl());

        // 23) �M�p����������n�����ʒm�ꌏ�T�[�r�X
        Services.registerService(
            WEB3MarginSwapOrderNotifyUnitService.class,
            new WEB3MarginSwapOrderNotifyUnitServiceImpl());

        // 24) �M�p����c���Ɖ�T�[�r�X
        Services.registerService(
            WEB3MarginBalanceReferenceService.class,
            new WEB3MarginBalanceReferenceServiceImpl());

        // Service�C���^�Z�v�^�̐ݒ�
        // 1) �M�p������ψꗗ�T�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
            WEB3MarginCloseMarginListService.class,
            new WEB3MarginCloseMarginListServiceInterceptor());

        // 2) �M�p��������Ɖ�T�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
            WEB3MarginContractReferenceService.class,
            new WEB3MarginContractReferenceServiceInterceptor());

        // 3) �M�p����������n�T�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
            WEB3MarginSwapMarginService.class,
            new WEB3MarginSwapMarginServiceInterceptor());

        // 4) �M�p����������n��t�ꌏ�T�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
            WEB3MarginSwapMarginAcceptUnitService.class,
            new WEB3MarginSwapMarginAcceptUnitServiceInterceptor());

        // 5) �M�p����������n���̓T�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
            WEB3MarginSwapMarginInputService.class,
            new WEB3MarginSwapMarginInputServiceInterceptor());

        // 6) �M�p�������T�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
            WEB3MarginCancelService.class,
            new WEB3MarginCancelServiceInterceptor());

        // 7) �M�p����o���ʒm�ꌏ�T�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
            WEB3MarginOrderExecNotifyUnitService.class,
            new WEB3MarginOrderExecNotifyUnitServiceInterceptor());

        // 8) �M�p����V�K���T�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
            WEB3MarginOpenMarginService.class,
            new WEB3MarginOpenMarginServiceInterceptor());

        // 9) �M�p����V�K�����̓T�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
            WEB3MarginOpenMarginInputService.class,
            new WEB3MarginOpenMarginInputServiceInterceptor());

        // 10) �M�p��������ʒm�ꌏ�T�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
            WEB3MarginOrderNotifyUnitService.class,
            new WEB3MarginOrderNotifyUnitServiceInterceptor());

        // 11) �M�p����������Ɖ�T�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
            WEB3MarginExecuteReferenceService.class,
            new WEB3MarginExecuteReferenceServiceInterceptor());

        // 12) �M�p�����������ʒm�T�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
            WEB3MarginChangeCancelNotifyChangeUnitService.class,
            new WEB3MarginChangeCancelNotifyChangeUnitServiceInterceptor());

        // 13) �M�p��������V�K���T�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
            WEB3MarginChangeOpenMarginService.class,
            new WEB3MarginChangeOpenMarginServiceInterceptor());

        // 14) �M�p��������V�K�����̓T�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
            WEB3MarginChangeOpenMarginInputService.class,
            new WEB3MarginChangeOpenMarginInputServiceInterceptor());

        // 15) �M�p��������ԍσT�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
            WEB3MarginChangeCloseMarginService.class,
            new WEB3MarginChangeCloseMarginServiceInterceptor());

        // 16) �M�p��������ԍϓ��̓T�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
            WEB3MarginChangeCloseMarginInputService.class,
            new WEB3MarginChangeCloseMarginInputServiceInterceptor());

        // 17) �M�p����ԍσT�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
            WEB3MarginCloseMarginService.class,
            new WEB3MarginCloseMarginServiceInterceptor());

        // 18) �M�p����ԍϓ��̓T�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
            WEB3MarginCloseMarginInputService.class,
            new WEB3MarginCloseMarginInputServiceInterceptor());

        // 19) �M�p�����������ʒm����ꌏ�T�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
            WEB3MarginChangeCancelNotifyCancelUnitService.class,
            new WEB3MarginChangeCancelNotifyCancelUnitServiceInterceptor());

        // 20) �M�p����������n�����ʒm�ꌏ�T�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
            WEB3MarginSwapOrderNotifyUnitService.class,
            new WEB3MarginSwapOrderNotifyUnitServiceInterceptor());

        // 21) �M�p��������c���Ɖ�T�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
            WEB3MarginBalanceReferenceService.class,
            new WEB3MarginBalanceReferenceServiceInterceptor());

        // �����g�����U�N�V�����ݒ�
        // 1) �M�p����������n�T�[�r�X
        Services.addInterceptor(
            WEB3MarginSwapMarginService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // 2) �M�p����������n��t�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3MarginSwapMarginAcceptUnitService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // 3) �M�p�������T�[�r�X
        Services.addInterceptor(
            WEB3MarginCancelService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // 4) �M�p����o���ʒm�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3MarginOrderExecNotifyUnitService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        // 5) �M�p����V�K���T�[�r�X
        Services.addInterceptor(
            WEB3MarginOpenMarginService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // 6) �M�p��������ʒm�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3MarginOrderNotifyUnitService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // 7) �M�p�����������ʒm�����ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3MarginChangeCancelNotifyChangeUnitService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        // 8) �M�p��������V�K���T�[�r�X
        Services.addInterceptor(
            WEB3MarginChangeOpenMarginService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // 9) �M�p��������ԍσT�[�r�X
        Services.addInterceptor(
            WEB3MarginChangeCloseMarginService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // 10) �M�p����ԍσT�[�r�X
        Services.addInterceptor(
            WEB3MarginCloseMarginService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // 11) �M�p�����������ʒm����ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3MarginChangeCancelNotifyCancelUnitService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        // 12) �M�p����������n�����ʒm�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3MarginSwapOrderNotifyUnitService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // ���[���G���W���T�[�o�E�C���^�Z�v�^�̐ݒ�
        // 1) �M�p����������n�T�[�r�X
        Services.addInterceptor(
            WEB3MarginSwapMarginService.class,
            new WEB3RlsGatewayInterceptor());

        // 2) �M�p�������T�[�r�X
        Services.addInterceptor(
            WEB3MarginCancelService.class,
            new WEB3RlsGatewayInterceptor());

        // 3) �M�p����V�K���T�[�r�X
        Services.addInterceptor(
            WEB3MarginOpenMarginService.class,
            new WEB3RlsGatewayInterceptor());

        // 4) �M�p��������V�K���T�[�r�X
        Services.addInterceptor(
            WEB3MarginChangeOpenMarginService.class,
            new WEB3RlsGatewayInterceptor());

        // 5) �M�p��������ԍσT�[�r�X
        Services.addInterceptor(
            WEB3MarginChangeCloseMarginService.class,
            new WEB3RlsGatewayInterceptor());

        // 6) �M�p����ԍσT�[�r�X
        Services.addInterceptor(
            WEB3MarginCloseMarginService.class,
            new WEB3RlsGatewayInterceptor());

        // 7) �M�p�����������ʒm�����ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3MarginChangeCancelNotifyChangeUnitService.class,
            new WEB3RlsGatewayInterceptor());

        // 8) �M�p����o���ʒm�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3MarginOrderExecNotifyUnitService.class,
            new WEB3RlsGatewayInterceptor());

        // MQ-Gateway�C���^�Z�v�^�̐ݒ�
        // 1) �M�p����������n�T�[�r�X
        Services.addInterceptor(
            WEB3MarginSwapMarginService.class,
            new WEB3MQGatewayInterceptor());

        // 2) �M�p�������T�[�r�X
        Services.addInterceptor(
            WEB3MarginCancelService.class,
            new WEB3MQGatewayInterceptor());

        // 3) �M�p����V�K���T�[�r�X
        Services.addInterceptor(
            WEB3MarginOpenMarginService.class,
            new WEB3MQGatewayInterceptor());

        // 4) �M�p��������V�K���T�[�r�X
        Services.addInterceptor(
            WEB3MarginChangeOpenMarginService.class,
            new WEB3MQGatewayInterceptor());

        // 5) �M�p��������ԍσT�[�r�X
        Services.addInterceptor(
            WEB3MarginChangeCloseMarginService.class,
            new WEB3MQGatewayInterceptor());

        // 6) �M�p����ԍσT�[�r�X
        Services.addInterceptor(
            WEB3MarginCloseMarginService.class,
            new WEB3MQGatewayInterceptor());

        // �������ԃ��O�o�̓C���^�Z�v�^�̐ݒ�
        // 1) �M�p������ψꗗ�T�[�r�X
        Services.addInterceptor(
            WEB3MarginCloseMarginListService.class,
            new WEB3LogSysTimeInterceptor());

        // 2) �M�p��������Ɖ�T�[�r�X
        Services.addInterceptor(
            WEB3MarginContractReferenceService.class,
            new WEB3LogSysTimeInterceptor());

        // 3) �M�p����������n�T�[�r�X
        Services.addInterceptor(
            WEB3MarginSwapMarginService.class,
            new WEB3LogSysTimeInterceptor());

        // 4) �M�p����������n��t�T�[�r�X
        Services.addInterceptor(
            WEB3MarginSwapMarginAcceptService.class,
            new WEB3LogSysTimeInterceptor());

        // 5) �M�p����������n��t�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3MarginSwapMarginAcceptUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // 6) �M�p����������n���̓T�[�r�X
        Services.addInterceptor(
            WEB3MarginSwapMarginInputService.class,
            new WEB3LogSysTimeInterceptor());

        // 7) �M�p�������T�[�r�X
        Services.addInterceptor(
            WEB3MarginCancelService.class,
            new WEB3LogSysTimeInterceptor());

        // 8) �M�p����o���ʒm�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3MarginOrderExecNotifyUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // 9) �M�p����V�K���T�[�r�X
        Services.addInterceptor(
            WEB3MarginOpenMarginService.class,
            new WEB3LogSysTimeInterceptor());

        // 10) �M�p����V�K�����̓T�[�r�X
        Services.addInterceptor(
            WEB3MarginOpenMarginInputService.class,
            new WEB3LogSysTimeInterceptor());

        // 11) �M�p��������ʒm�T�[�r�X
        Services.addInterceptor(
            WEB3MarginOrderNotifyService.class,
            new WEB3LogSysTimeInterceptor());

        // 12) �M�p��������ʒm�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3MarginOrderNotifyUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // 13) �M�p����������Ɖ�T�[�r�X
        Services.addInterceptor(
            WEB3MarginExecuteReferenceService.class,
            new WEB3LogSysTimeInterceptor());

        // 14) �M�p�����������ʒm����ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3MarginChangeCancelNotifyCancelUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // 15) �M�p�����������ʒm�����ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3MarginChangeCancelNotifyChangeUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // 16) �M�p��������V�K���T�[�r�X
        Services.addInterceptor(
            WEB3MarginChangeOpenMarginService.class,
            new WEB3LogSysTimeInterceptor());

        // 17) �M�p��������V�K�����̓T�[�r�X
        Services.addInterceptor(
            WEB3MarginChangeOpenMarginInputService.class,
            new WEB3LogSysTimeInterceptor());

        // 18) �M�p��������ԍσT�[�r�X
        Services.addInterceptor(
            WEB3MarginChangeCloseMarginService.class,
            new WEB3LogSysTimeInterceptor());

        // 19) �M�p��������ԍϓ��̓T�[�r�X
        Services.addInterceptor(
            WEB3MarginChangeCloseMarginInputService.class,
            new WEB3LogSysTimeInterceptor());

        // 20) �M�p����ԍσT�[�r�X
        Services.addInterceptor(
            WEB3MarginCloseMarginService.class,
            new WEB3LogSysTimeInterceptor());

        // 21) �M�p����ԍϓ��̓T�[�r�X
        Services.addInterceptor(
            WEB3MarginCloseMarginInputService.class,
            new WEB3LogSysTimeInterceptor());

        // 22) �M�p����������n�����ʒm�T�[�r�X
        Services.addInterceptor(
            WEB3MarginSwapOrderNotifyService.class,
            new WEB3LogSysTimeInterceptor());

        // 23) �M�p����������n�����ʒm�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3MarginSwapOrderNotifyUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // 24) �M�p����c���Ɖ�T�[�r�X
        Services.addInterceptor(
            WEB3MarginBalanceReferenceService.class,
            new WEB3LogSysTimeInterceptor());

        // Message �̓o�^
        // 1-1) �M�p������ψꗗ���N�G�X�g
        regClass(WEB3MarginCloseMarginListRequest.class);
        // 1-2) �M�p������ψꗗ���X�|���X
        regClass(WEB3MarginCloseMarginListResponse.class);

        // 2-1) �M�p����ʌ��ψꗗ���N�G�X�g
        regClass(WEB3MarginIndividualCloseMarginListRequest.class);
        // 2-2) �M�p����ʌ��ψꗗ���X�|���X
        regClass(WEB3MarginIndividualCloseMarginListResponse.class);

        // 3-1) �M�p��������Ɖ�N�G�X�g
        regClass(WEB3MarginContractReferenceRequest.class);
        // 3-2) �M�p��������Ɖ�X�|���X
        regClass(WEB3MarginContractReferenceResponse.class);

        // 4-1) �M�p����������n�����m�F���N�G�X�g
        regClass(WEB3MarginSwapMarginConfirmRequest.class);
        // 4-2) �M�p����������n�����m�F���X�|���X
        regClass(WEB3MarginSwapMarginConfirmResponse.class);

        // 5-1) �M�p����������n�����������N�G�X�g
        regClass(WEB3MarginSwapMarginCompleteRequest.class);
        // 5-2) �M�p����������n�����������X�|���X
        regClass(WEB3MarginSwapMarginCompleteResponse.class);

        // 6-1) �M�p����������n��t���N�G�X�g
        regClass(WEB3MarginSwapMarginAcceptRequest.class);
        // 6-2) �M�p����������n��t���X�|���X
        regClass(WEB3MarginSwapMarginAcceptResponse.class);

        // 7-1) �M�p����������n�������̓��N�G�X�g
        regClass(WEB3MarginSwapMarginInputRequest.class);
        // 7-2) �M�p����������n�������̓��X�|���X
        regClass(WEB3MarginSwapMarginInputResponse.class);

        // 8-1) �M�p�����������m�F���N�G�X�g
        regClass(WEB3MarginCancelConfirmRequest.class);
        // 8-2) �M�p�����������m�F���X�|���X
        regClass(WEB3MarginCancelConfirmResponse.class);

        // 9-1) �M�p�����������������N�G�X�g
        regClass(WEB3MarginCancelCompleteRequest.class);
        // 9-2) �M�p�����������������X�|���X
        regClass(WEB3MarginCancelCompleteResponse.class);

        // 10-1) �M�p����V�K�������m�F���N�G�X�g
        regClass(WEB3MarginOpenMarginConfirmRequest.class);
        // 10-2) �M�p����V�K�������m�F���X�|���X
        regClass(WEB3MarginOpenMarginConfirmResponse.class);

        // 11-1) �M�p����V�K�������������N�G�X�g
        regClass(WEB3MarginOpenMarginCompleteRequest.class);
        // 11-2) �M�p����V�K�������������X�|���X
        regClass(WEB3MarginOpenMarginCompleteResponse.class);

        // 12-1) �M�p����V�K�����������I�����N�G�X�g
        regClass(WEB3MarginProductSelectRequest.class);
        // 12-2) �M�p����V�K�����������I�����X�|���X
        regClass(WEB3MarginProductSelectResponse.class);

        // 13-1) �M�p����V�K���������̓��N�G�X�g
        regClass(WEB3MarginOpenMarginInputRequest.class);
        // 13-2) �M�p����V�K���������̓��X�|���X
        regClass(WEB3MarginOpenMarginInputResponse.class);

        // 14-1) �M�p��������ʒm���N�G�X�g
        regClass(WEB3MarginOrderNotifyRequest.class);
        // 14-2) �M�p��������ʒm���X�|���X
        regClass(WEB3MarginOrderNotifyResponse.class);

        // 15-1) �M�p����������Ɖ�N�G�X�g
        regClass(WEB3MarginExecuteReferenceRequest.class);
        // 15-2) �M�p����������Ɖ�X�|���X
        regClass(WEB3MarginExecuteReferenceResponse.class);

        // 16-1) �M�p����������ڍ׃��N�G�X�g
        regClass(WEB3MarginExecuteDetailsRequest.class);
        // 16-2) �M�p����������ڍ׃��X�|���X
        regClass(WEB3MarginExecuteDetailsResponse.class);

        // 17-1) �M�p������������Ɖ�N�G�X�g
        regClass(WEB3MarginOrderHistoryRequest.class);
        // 17-2) �M�p������������Ɖ�X�|���X
        regClass(WEB3MarginOrderHistoryResponse.class);

        // 18-1) �M�p������ό����ꗗ���N�G�X�g
        regClass(WEB3MarginCloseMarginContractListRequest.class);
        // 18-2) �M�p������ό����ꗗ���X�|���X
        regClass(WEB3MarginCloseMarginContractListResponse.class);

        // 19-1) �M�p�����������_�V�K���m�F���N�G�X�g
        regClass(WEB3MarginOpenMarginChangeConfirmRequest.class);
        // 19-2) �M�p�����������_�V�K���m�F���X�|���X
        regClass(WEB3MarginOpenMarginChangeConfirmResponse.class);

        // 20-1) �M�p�����������_�V�K���������N�G�X�g
        regClass(WEB3MarginOpenMarginChangeCompleteRequest.class);
        // 20-2) �M�p�����������_�V�K���������X�|���X
        regClass(WEB3MarginOpenMarginChangeCompleteResponse.class);

        // 21-1) �M�p��������V�K�����̓��N�G�X�g
        regClass(WEB3MarginOpenMarginChangeInputRequest.class);
        // 21-2) �M�p��������V�K�����̓��X�|���X
        regClass(WEB3MarginOpenMarginChangeInputResponse.class);

        // 22-1) �M�p�����������_�ԍϊm�F���N�G�X�g
        regClass(WEB3MarginCloseMarginChangeConfirmRequest.class);
        // 22-2) �M�p�����������_�ԍϊm�F���X�|���X
        regClass(WEB3MarginCloseMarginChangeConfirmResponse.class);

        // 23-1) �M�p�����������_�ԍϊ������N�G�X�g
        regClass(WEB3MarginCloseMarginChangeCompleteRequest.class);
        // 23-2) �M�p�����������_�ԍϊ������X�|���X
        regClass(WEB3MarginCloseMarginChangeCompleteResponse.class);

        // 24-1) �M�p��������ԍϓ��̓��N�G�X�g
        regClass(WEB3MarginCloseMarginChangeInputRequest.class);
        // 24-2) �M�p��������ԍϓ��̓��X�|���X
        regClass(WEB3MarginCloseMarginChangeInputResponse.class);

        // 25-1) �M�p����ԍϒ����m�F���N�G�X�g
        regClass(WEB3MarginCloseMarginConfirmRequest.class);
        // 25-2) �M�p����ԍϒ����m�F���X�|���X
        regClass(WEB3MarginCloseMarginConfirmResponse.class);

        // 26-1) �M�p����ԍϒ����������N�G�X�g
        regClass(WEB3MarginCloseMarginCompleteRequest.class);
        // 26-2) �M�p����ԍϒ����������X�|���X
        regClass(WEB3MarginCloseMarginCompleteResponse.class);

        // 27-1) �M�p����ԍϒ������̓��N�G�X�g
        regClass(WEB3MarginCloseMarginInputRequest.class);
        // 27-2) �M�p����ԍϒ������̓��X�|���X
        regClass(WEB3MarginCloseMarginInputResponse.class);

        // 28-1) �M�p����������n�����ʒm���N�G�X�g
        regClass(WEB3MarginSwapOrderNotifyRequest.class);
        // 28-2) �M�p����������n�����ʒm���X�|���X
        regClass(WEB3MarginSwapOrderNotifyResponse.class);

        // 29-1) �M�p����c���Ɖ�N�G�X�g
        regClass(WEB3MarginBalanceReferenceRequest.class);
        // 29-2) �M�p����c���Ɖ�X�|���X
        regClass(WEB3MarginBalanceReferenceResponse.class);

        // 30-1) �M�p����c�����v���N�G�X�g
        regClass(WEB3MarginBalanceReferenceTotalRequest.class);
        // 30-2) �M�p����c�����v���X�|���X
        regClass(WEB3MarginBalanceReferenceTotalResponse.class);

        // Handler �̓o�^
        // 1) �M�p������ψꗗ���N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MarginCloseMarginListRequest.class,
            WEB3MarginCloseMarginListHandler.class,
            "getCloseMarginList");

        // 2) �M�p����ʌ��ψꗗ���N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MarginIndividualCloseMarginListRequest.class,
            WEB3MarginCloseMarginListHandler.class,
            "getIndividualCloseMarginList");

        // 3) �M�p��������Ɖ�N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MarginContractReferenceRequest.class,
            WEB3MarginContractReferenceHandler.class,
            "getContractReference");

        // 4) �M�p����������n�����m�F���N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MarginSwapMarginConfirmRequest.class,
            WEB3MarginSwapMarginHandler.class,
            "confirmSwapMargin");

        // 5) �M�p����������n�����������N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MarginSwapMarginCompleteRequest.class,
            WEB3MarginSwapMarginHandler.class,
            "completeSwapMargin");

        // 6) �M�p����������n��t���N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MarginSwapMarginAcceptRequest.class,
            WEB3MarginSwapMarginAcceptHandler.class,
            "swapMarginAcceptRequest");

        // 7) �M�p����������n�������̓��N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MarginSwapMarginInputRequest.class,
            WEB3MarginSwapMarginInputHandler.class,
            "getSwapMarginInputScreen");

        // 8) �M�p�����������m�F���N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MarginCancelConfirmRequest.class,
            WEB3MarginCancelHandler.class,
            "confirmCancel");

        // 9) �M�p�����������������N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MarginCancelCompleteRequest.class,
            WEB3MarginCancelHandler.class,
            "completeCancel");

        // 10) �M�p����V�K�������m�F���N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MarginOpenMarginConfirmRequest.class,
            WEB3MarginOpenMarginHandler.class,
            "confirmOrder");

        // 11) �M�p����V�K�������������N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MarginOpenMarginCompleteRequest.class,
            WEB3MarginOpenMarginHandler.class,
            "completeOrder");

        // 12) �M�p����V�K�����������I�����N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MarginProductSelectRequest.class,
            WEB3MarginOpenMarginInputHandler.class,
            "getProductSelectScreen");

        // 13) �M�p����V�K���������̓��N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MarginOpenMarginInputRequest.class,
            WEB3MarginOpenMarginInputHandler.class,
            "getOpenMarginInputScreen");

        // 14) �M�p��������ʒm���N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MarginOrderNotifyRequest.class,
            WEB3MarginOrderNotifyHandler.class,
            "orderNotifyRequest");

        // 15) �M�p����������Ɖ�N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MarginExecuteReferenceRequest.class,
            WEB3MarginExecuteReferenceHandler.class,
            "searchOrderExecuteReference");

        // 16) �M�p����������ڍ׃��N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MarginExecuteDetailsRequest.class,
            WEB3MarginExecuteReferenceHandler.class,
            "searchOrderExecuteDetails");

        // 17) �M�p������������Ɖ�N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MarginOrderHistoryRequest.class,
            WEB3MarginExecuteReferenceHandler.class,
            "searchOrderHistoryInquiry");

        // 18) �M�p������ό����ꗗ���N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MarginCloseMarginContractListRequest.class,
            WEB3MarginExecuteReferenceHandler.class,
            "searchCloseMarginContractList");

        // 19) �M�p�����������_�V�K���m�F���N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MarginOpenMarginChangeConfirmRequest.class,
            WEB3MarginChangeOpenMarginHandler.class,
            "confirmOpenMarginChange");

        // 20) �M�p�����������_�V�K���������N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MarginOpenMarginChangeCompleteRequest.class,
            WEB3MarginChangeOpenMarginHandler.class,
            "completeOpenMarginChange");

        // 21) �M�p��������V�K�����̓��N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MarginOpenMarginChangeInputRequest.class,
            WEB3MarginChangeOpenMarginInputHandler.class,
            "getOpenMarginChangeInputScreen");

        // 22) �M�p�����������_�ԍϊm�F���N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MarginCloseMarginChangeConfirmRequest.class,
            WEB3MarginChangeCloseMarginHandler.class,
            "confirmCloseMarginChange");

        // 23) �M�p�����������_�ԍϊ������N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MarginCloseMarginChangeCompleteRequest.class,
            WEB3MarginChangeCloseMarginHandler.class,
            "completeCloseMarginChange");

        // 24) �M�p��������ԍϓ��̓��N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MarginCloseMarginChangeInputRequest.class,
            WEB3MarginChangeCloseMarginInputHandler.class,
            "getCloseMarginChangeInputScreen");

        // 25) �M�p����ԍϒ����m�F���N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MarginCloseMarginConfirmRequest.class,
            WEB3MarginCloseMarginHandler.class,
            "confirmCloseMargin");

        // 26) �M�p����ԍϒ����������N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MarginCloseMarginCompleteRequest.class,
            WEB3MarginCloseMarginHandler.class,
            "completeCloseMargin");

        // 27) �M�p����ԍϒ������̓��N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MarginCloseMarginInputRequest.class,
            WEB3MarginCloseMarginInputServiceHandler.class,
            "getCloseMarginInputScreen");

        // 28) �M�p����������n�����ʒm���N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MarginSwapOrderNotifyRequest.class,
            WEB3MarginSwapOrderNotifyHandler.class,
            "swapOrderNotify");

        // 29) �M�p����c���Ɖ�N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MarginBalanceReferenceRequest.class,
            WEB3MarginBalanceReferenceHandler.class,
            "getBalanceReference");

        // 30) �M�p����c���Ɖ�c�����v���N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MarginBalanceReferenceTotalRequest.class,
            WEB3MarginBalanceReferenceHandler.class,
            "getBalanceTotal");


        /**
         *  �����~�j����
         */
        // Service�̓o�^
        // 1) �����~�j������������T�[�r�X
        Services.registerService(
            WEB3MstkCancelService.class,
            new WEB3MstkCancelServiceImpl());

        // 2) �����~�j�������t�����T�[�r�X
        Services.registerService(
            WEB3MstkBuyService.class,
            new WEB3MstkBuyServiceImpl());

        // 3) �����~�j�����������Ɖ�T�[�r�X
        Services.registerService(
            WEB3MstkExecuteReferenceService.class,
            new WEB3MstkExecuteReferenceServiceImpl());

        // 4) �����~�j�������t�����T�[�r�X
        Services.registerService(
            WEB3MstkSellService.class,
            new WEB3MstkSellServiceImpl());

        // 5) �����~�j�����c���Ɖ�T�[�r�X
        Services.registerService(
            WEB3MstkBalanceReferenceService.class,
            new WEB3MstkBalanceReferenceServiceImpl());

        // 6) �����~�j�����뉿�P���o�^�T�[�r�X
        Services.registerService(
            WEB3MstkBookValuePriceRegistService.class,
            new WEB3MstkBookValuePriceRegistServiceImpl());

        // Service�C���^�Z�v�^�̐ݒ�
        // 1) �����~�j������������T�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
            WEB3MstkCancelService.class,
            new WEB3MstkCancelServiceInterceptor());

        // 2) �����~�j�������t�����T�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
            WEB3MstkBuyService.class,
            new WEB3MstkBuyServiceInterceptor());

        // 3) �����~�j�����������Ɖ�T�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
            WEB3MstkExecuteReferenceService.class,
            new WEB3MstkExecuteReferenceServiceInterceptor());

        // 4) �����~�j�������t�����T�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
            WEB3MstkSellService.class,
            new WEB3MstkSellServiceInterceptor());

        // 5) �����~�j�����c���Ɖ�T�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
            WEB3MstkBalanceReferenceService.class,
            new WEB3MstkBalanceReferenceServiceInterceptor());

        // 6) �����~�j�����뉿�P���o�^�T�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
            WEB3MstkBookValuePriceRegistService.class,
            new WEB3MstkBookValuePriceRegistServiceInterceptor());

        // �����g�����U�N�V�����̐ݒ�
        // 1) �����~�j������������T�[�r�X
        Services.addInterceptor(
            WEB3MstkCancelService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));
        
        // 2) �����~�j�������t�����T�[�r�X
        Services.addInterceptor(
            WEB3MstkBuyService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // 3) �����~�j�������t�����T�[�r�X
        Services.addInterceptor(
            WEB3MstkSellService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // 4) �����~�j�����뉿�P���o�^�T�[�r�X
        Services.addInterceptor(
            WEB3MstkBookValuePriceRegistService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        // MQ-Gateway�C���^�Z�v�^�̐ݒ�
        // 1) �����~�j������������T�[�r�X
        Services.addInterceptor(
            WEB3MstkCancelService.class,
            new WEB3MQGatewayInterceptor());

        // 2) �����~�j�������t�����T�[�r�X
        Services.addInterceptor(
           WEB3MstkBuyService.class,
           new WEB3MQGatewayInterceptor());

        // 3) �����~�j�������t�����T�[�r�X
        Services.addInterceptor(
            WEB3MstkSellService.class,
            new WEB3MQGatewayInterceptor());

        // �������ԃ��O�o�̓C���^�Z�v�^�̐ݒ�
        // 1) �����~�j�����������Ɖ�T�[�r�X
        Services.addInterceptor(
            WEB3MstkExecuteReferenceService.class,
            new WEB3LogSysTimeInterceptor());

        // 2) �����~�j�������t�����T�[�r�X
        Services.addInterceptor(
            WEB3MstkBuyService.class,
            new WEB3LogSysTimeInterceptor());

        // 3) �����~�j������������T�[�r�X
        Services.addInterceptor(
            WEB3MstkCancelService.class,
            new WEB3LogSysTimeInterceptor());

        // 4) �����~�j�������t�����T�[�r�X
        Services.addInterceptor(
            WEB3MstkSellService.class,
            new WEB3LogSysTimeInterceptor());

        // 5) �����~�j�����c���Ɖ�T�[�r�X
        Services.addInterceptor(
            WEB3MstkBalanceReferenceService.class,
            new WEB3LogSysTimeInterceptor());

        // 6) �����~�j�����뉿�P���o�^�T�[�r�X
        Services.addInterceptor(
            WEB3MstkBookValuePriceRegistService.class,
            new WEB3LogSysTimeInterceptor());
        
        // Message �̓o�^
        // 1-1) �����~�j������������m�F���N�G�X�g
        regClass(WEB3MstkCancelConfirmRequest.class);
        // 1-2) �����~�j������������m�F���X�|���X
        regClass(WEB3MstkCancelConfirmResponse.class);
        
        // 2-1) �����~�j������������������N�G�X�g
        regClass(WEB3MstkCancelCompleteRequest.class);
        // 2-2) �����~�j������������������X�|���X
        regClass(WEB3MstkCancelCompleteResponse.class);

        // 3-1) �����~�j�������t�����m�F���N�G�X�g
        regClass(WEB3MstkBuyConfirmRequest.class);
        // 3-2) �����~�j�������t�����m�F���X�|���X
        regClass(WEB3MstkBuyConfirmResponse.class);
        
        // 4-1) �����~�j�������t�����������N�G�X�g
        regClass(WEB3MstkBuyCompleteRequest.class);
        // 4-2) �����~�j�������t�����������X�|���X
        regClass(WEB3MstkBuyCompleteResponse.class);
        
        // 5-1) �����~�j�������t�������̓��N�G�X�g
        regClass(WEB3MstkBuyInputRequest.class);
        // 5-2) �����~�j�������t�������̓��X�|���X
        regClass(WEB3MstkBuyInputResponse.class);

        // 6-1) �����~�j�����������Ɖ�N�G�X�g
        regClass(WEB3MstkExecuteReferenceRequest.class);
        // 6-2) �����~�j�����������Ɖ�X�|���X
        regClass(WEB3MstkExecuteReferenceResponse.class);

        // 7-1) �����~�j�������t�����m�F���N�G�X�g
        regClass(WEB3MstkSellConfirmRequest.class);
        // 7-2) �����~�j�������t�����m�F���X�|���X
        regClass(WEB3MstkSellConfirmResponse.class);
        
        // 8-1) �����~�j�������t�����������N�G�X�g
        regClass(WEB3MstkSellCompleteRequest.class);
        // 8-2) �����~�j�������t�����������X�|���X
        regClass(WEB3MstkSellCompleteResponse.class);
        
        // 9-1) �����~�j�������t�������̓��N�G�X�g
        regClass(WEB3MstkSellInputRequest.class);
        // 9-2) �����~�j�������t�������̓��X�|���X
        regClass(WEB3MstkSellInputResponse.class);
        
        // 10-1) �����~�j�������t�ꗗ���N�G�X�g
        regClass(WEB3MstkSellListRequest.class);
        // 10-2) �����~�j�������t�ꗗ���X�|���X
        regClass(WEB3MstkSellListResponse.class);

        // 11-1) �����~�j�����c���Ɖ�N�G�X�g
        regClass(WEB3MstkBalanceReferenceRequest.class);
        // 11-2) �����~�j�����c���Ɖ�X�|���X
        regClass(WEB3MstkBalanceReferenceResponse.class);
        
        // 12-1) �����~�j�����c�����v���N�G�X�g
        regClass(WEB3MstkBalanceReferenceTotalRequest.class);
        // 12-2) �����~�j�����c�����v���X�|���X
        regClass(WEB3MstkBalanceReferenceTotalResponse.class);

        // 13-1) �����~�j�����뉿�P���o�^���̓��N�G�X�g
        regClass(WEB3MstkBookPriceInputRequest.class);
        // 13-2) �����~�j�����뉿�P���o�^���̓��X�|���X
        regClass(WEB3MstkBookPriceInputResponse.class);
        
        // 14-1) �����~�j�����뉿�P���o�^���N�G�X�g
        regClass(WEB3MstkBookPriceRegistRequest.class);
        // 14-2) �����~�j�����뉿�P���o�^���X�|���X
        regClass(WEB3MstkBookPriceRegistResponse.class);

        // Handler �̓o�^
        // 1) �����~�j������������m�F���N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MstkCancelConfirmRequest.class,
            WEB3MstkCancelHandler.class,
            "handleConfirmOrder");

        // 2) �����~�j������������������N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MstkCancelCompleteRequest.class,
            WEB3MstkCancelHandler.class,
            "handleCompleteOrder");

        // 3) �����~�j�������t�ꗗ���N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MstkSellListRequest.class,
            WEB3MstkSellHandler.class,
            "handleGetSellList");

        // 4) �����~�j�������t�������̓��N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MstkSellInputRequest.class,
            WEB3MstkSellHandler.class,
            "handleGetSellInputScreen");

        // 5) �����~�j�������t�����m�F���N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MstkSellConfirmRequest.class,
            WEB3MstkSellHandler.class,
            "handleConfirmOrder");

        // 6) �����~�j�������t�����������N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MstkSellCompleteRequest.class,
            WEB3MstkSellHandler.class,
            "handleCompleteOrder");

        // 7) �����~�j�����������Ɖ�N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MstkExecuteReferenceRequest.class,
            WEB3MstkExecuteReferenceHandler.class,
            "handleSearchOrderExecuteReference");

        // 8) �����~�j�������t�����m�F���N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MstkBuyConfirmRequest.class,
            WEB3MstkBuyHandler.class,
            "handleConfirmOrder");

        // 9) �����~�j�������t�����������N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MstkBuyCompleteRequest.class,
            WEB3MstkBuyHandler.class,
            "handleCompleteOrder");

        // 10) �����~�j�������t�������̓��N�G�X�g�p�n���h��
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MstkBuyInputRequest.class,
            WEB3MstkBuyHandler.class,
            "handleGetBuyInputScreen");

        // 11) �����~�j�����c���Ɖ�N�G�X�g�p�n���h���|
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MstkBalanceReferenceRequest.class,
            WEB3MstkBalanceReferenceHandler.class,
            "getBalanceReference");

        // 12) �����~�j�����c�����v���N�G�X�g�p�n���h���|
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MstkBalanceReferenceTotalRequest.class,
            WEB3MstkBalanceReferenceHandler.class,
            "getBalanceTotal");

        // 13) �����~�j�����뉿�P���o�^���͉�ʃ��N�G�X�g�p�n���h���|
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MstkBookPriceInputRequest.class,
            WEB3MstkBookValuePriceRegistHandler.class,
            "getInputScreen");

        // 14) �����~�j�����뉿�P���o�^���N�G�X�g�p�n���h���|
        regHandler(
            WEB3EquityAppPlugin.class,
            WEB3MstkBookPriceRegistRequest.class,
            WEB3MstkBookValuePriceRegistHandler.class,
            "completeBookValuePrice");


        // RAC-CTX�C���^�Z�v�^�̐ݒ�
        // 1�j���������o���ʒm�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3EquityOrderExecNotifyUnitService.class,
            new WEB3EquityDescendRacCtxInterceptor());
        // 2�j�M�p����o���ʒm�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3MarginOrderExecNotifyUnitService.class,
            new WEB3EquityDescendRacCtxInterceptor());
        // 3�j���������ʒm�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3EquityReceiveCloseOrderUnitService.class,
            new WEB3EquityDescendRacCtxInterceptor());
        // 4�j�������������t�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3EquityChangeCancelAcceptUnitService.class,
            new WEB3EquityDescendRacCtxInterceptor());
        // 5�j����������t�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3EquityOrderAcceptUnitService.class,
            new WEB3EquityDescendRacCtxInterceptor());
        // 6�j�M�p��������ʒm�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3MarginSwapOrderNotifyUnitService.class,
            new WEB3EquityDescendRacCtxInterceptor());
        // 7�j�M�p����������n��t�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3MarginSwapMarginAcceptUnitService.class,
            new WEB3EquityDescendRacCtxInterceptor());
        // 8�j���������ʒm�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3EquityOrderNotifyUnitService.class,
            new WEB3EquityDescendRacCtxInterceptor());
        // 9�j�M�p��������ʒm�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3MarginOrderNotifyUnitService.class,
            new WEB3EquityDescendRacCtxInterceptor());
        // 10�j�M�p�����������ʒm�����ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3MarginChangeCancelNotifyChangeUnitService.class,
            new WEB3EquityDescendRacCtxInterceptor());
        // 11�j�M�p�����������ʒm����ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3MarginChangeCancelNotifyCancelUnitService.class,
            new WEB3EquityDescendRacCtxInterceptor());
        // 12�j�����J�z�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3EquityOrderCarryOverUnitService.class,
            new WEB3EquityDescendRacCtxInterceptor());
        
        log.exiting(METHOD_NAME);
    }
}
@
