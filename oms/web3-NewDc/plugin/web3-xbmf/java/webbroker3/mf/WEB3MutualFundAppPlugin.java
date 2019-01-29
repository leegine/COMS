head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFundAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : Webbroker3-xbmf �v���O�C���N���X(WEB3MutualFundAppPlugin.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/25 ����� (���u)  �V�K�쐬
Revesion History : 2004/12/15 �����(���u)�c�Ή�
Revesion History : 2006/06/27 �юu��(���u) �d�l�ύX ���f��412,417
Revesion History : 2006/07/25 ���G��(���u) �d�l�ύX ���f��460,410
Revesion History : 2007/02/07 �юu��(���u) �d�l�ύX ���f��534,536
Revesion History : 2008/07/14 ���z(���u) �d�l�ύX ���f��605,608
*/
package webbroker3.mf;

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
import webbroker3.mf.data.WEB3MfAccountDatabaseExtensions;
import webbroker3.mf.data.WEB3MfMasterDatabaseExtensions;
import webbroker3.mf.data.WEB3MfSessionDatabaseExtensions;
import webbroker3.mf.handler.WEB3AdminMutualCategoryRegistHandler;
import webbroker3.mf.handler.WEB3AdminMutualConditionsHandler;
import webbroker3.mf.handler.WEB3AdminMutualConditionsReferenceHandler;
import webbroker3.mf.handler.WEB3AdminMutualDisplayOrderHandler;
import webbroker3.mf.handler.WEB3AdminMutualTPACancelHandler;
import webbroker3.mf.handler.WEB3AdminMutualTPAdjustHandler;
import webbroker3.mf.handler.WEB3MutualBalanceReferenceHandler;
import webbroker3.mf.handler.WEB3MutualBuyHandler;
import webbroker3.mf.handler.WEB3MutualBuyInputHandler;
import webbroker3.mf.handler.WEB3MutualBuyListHandler;
import webbroker3.mf.handler.WEB3MutualCancelAcceptHandler;
import webbroker3.mf.handler.WEB3MutualCancelHandler;
import webbroker3.mf.handler.WEB3MutualFixedBuyApplyHandler;
import webbroker3.mf.handler.WEB3MutualFixedBuyConditionHandler;
import webbroker3.mf.handler.WEB3MutualFixedBuyConditionListHandler;
import webbroker3.mf.handler.WEB3MutualFrgnMmfOrderAcceptHandler;
import webbroker3.mf.handler.WEB3MutualFrgncalHandler;
import webbroker3.mf.handler.WEB3MutualOrderAcceptServiceHandler;
import webbroker3.mf.handler.WEB3MutualOrderReferenceHandler;
import webbroker3.mf.handler.WEB3MutualRecruitOrderHandler;
import webbroker3.mf.handler.WEB3MutualRecruitOrderInputHandler;
import webbroker3.mf.handler.WEB3MutualSellHandler;
import webbroker3.mf.handler.WEB3MutualSellInputHandler;
import webbroker3.mf.handler.WEB3MutualSellSwtListInquiryHandler;
import webbroker3.mf.handler.WEB3MutualSwitchingHandler;
import webbroker3.mf.handler.WEB3MutualSwitchingInputHandler;
import webbroker3.mf.handler.WEB3MutualSwtProductListHandler;
import webbroker3.mf.handler.WEB3MutualTradeOrderNotifyHandler;
import webbroker3.mf.marketadaptor.WEB3MutualFundMarketAdaptorAppPlugin;
import webbroker3.mf.message.WEB3AdminMutualCategoryRegistChangeRequest;
import webbroker3.mf.message.WEB3AdminMutualCategoryRegistChangeResponse;
import webbroker3.mf.message.WEB3AdminMutualCategoryRegistCompleteRequest;
import webbroker3.mf.message.WEB3AdminMutualCategoryRegistCompleteResponse;
import webbroker3.mf.message.WEB3AdminMutualCategoryRegistConfirmRequest;
import webbroker3.mf.message.WEB3AdminMutualCategoryRegistConfirmResponse;
import webbroker3.mf.message.WEB3AdminMutualCategoryRegistInputRequest;
import webbroker3.mf.message.WEB3AdminMutualCategoryRegistInputResponse;
import webbroker3.mf.message.WEB3AdminMutualConditionsCompleteRequest;
import webbroker3.mf.message.WEB3AdminMutualConditionsCompleteResponse;
import webbroker3.mf.message.WEB3AdminMutualConditionsConfirmRequest;
import webbroker3.mf.message.WEB3AdminMutualConditionsConfirmResponse;
import webbroker3.mf.message.WEB3AdminMutualConditionsInputRequest;
import webbroker3.mf.message.WEB3AdminMutualConditionsInputResponse;
import webbroker3.mf.message.WEB3AdminMutualConditionsRefInputRequest;
import webbroker3.mf.message.WEB3AdminMutualConditionsRefInputResponse;
import webbroker3.mf.message.WEB3AdminMutualConditionsReferenceRequest;
import webbroker3.mf.message.WEB3AdminMutualConditionsReferenceResponse;
import webbroker3.mf.message.WEB3AdminMutualDisplayOrderCompleteRequest;
import webbroker3.mf.message.WEB3AdminMutualDisplayOrderCompleteResponse;
import webbroker3.mf.message.WEB3AdminMutualDisplayOrderInputRequest;
import webbroker3.mf.message.WEB3AdminMutualDisplayOrderInputResponse;
import webbroker3.mf.message.WEB3AdminMutualFrgncalCompleteRequest;
import webbroker3.mf.message.WEB3AdminMutualFrgncalCompleteResponse;
import webbroker3.mf.message.WEB3AdminMutualFrgncalInputRequest;
import webbroker3.mf.message.WEB3AdminMutualFrgncalInputResponse;
import webbroker3.mf.message.WEB3AdminMutualFrgncalReferenceRequest;
import webbroker3.mf.message.WEB3AdminMutualFrgncalReferenceResponse;
import webbroker3.mf.message.WEB3AdminMutualTPACancelCompleteRequest;
import webbroker3.mf.message.WEB3AdminMutualTPACancelCompleteResponse;
import webbroker3.mf.message.WEB3AdminMutualTPACancelListRequest;
import webbroker3.mf.message.WEB3AdminMutualTPACancelListResponse;
import webbroker3.mf.message.WEB3AdminMutualTPAdjustCompleteRequest;
import webbroker3.mf.message.WEB3AdminMutualTPAdjustCompleteResponse;
import webbroker3.mf.message.WEB3AdminMutualTPAdjustConfirmRequest;
import webbroker3.mf.message.WEB3AdminMutualTPAdjustConfirmResponse;
import webbroker3.mf.message.WEB3MutualApplyCompleteRequest;
import webbroker3.mf.message.WEB3MutualApplyCompleteResponse;
import webbroker3.mf.message.WEB3MutualApplyConfirmRequest;
import webbroker3.mf.message.WEB3MutualApplyConfirmResponse;
import webbroker3.mf.message.WEB3MutualApplyInputRequest;
import webbroker3.mf.message.WEB3MutualApplyInputResponse;
import webbroker3.mf.message.WEB3MutualBalanceReferenceRequest;
import webbroker3.mf.message.WEB3MutualBalanceReferenceResponse;
import webbroker3.mf.message.WEB3MutualBalanceReferenceTotalRequest;
import webbroker3.mf.message.WEB3MutualBalanceReferenceTotalResponse;
import webbroker3.mf.message.WEB3MutualBuyCompleteRequest;
import webbroker3.mf.message.WEB3MutualBuyCompleteResponse;
import webbroker3.mf.message.WEB3MutualBuyConfirmRequest;
import webbroker3.mf.message.WEB3MutualBuyConfirmResponse;
import webbroker3.mf.message.WEB3MutualBuyInputRequest;
import webbroker3.mf.message.WEB3MutualBuyInputResponse;
import webbroker3.mf.message.WEB3MutualBuyListRequest;
import webbroker3.mf.message.WEB3MutualBuyListResponse;
import webbroker3.mf.message.WEB3MutualCancelAcceptRequest;
import webbroker3.mf.message.WEB3MutualCancelAcceptResponse;
import webbroker3.mf.message.WEB3MutualCancelCompleteRequest;
import webbroker3.mf.message.WEB3MutualCancelCompleteResponse;
import webbroker3.mf.message.WEB3MutualCancelConfirmRequest;
import webbroker3.mf.message.WEB3MutualCancelConfirmResponse;
import webbroker3.mf.message.WEB3MutualFixedBuyApplyConfirmRequest;
import webbroker3.mf.message.WEB3MutualFixedBuyApplyConfirmResponse;
import webbroker3.mf.message.WEB3MutualFixedBuyApplyInputRequest;
import webbroker3.mf.message.WEB3MutualFixedBuyApplyInputResponse;
import webbroker3.mf.message.WEB3MutualFixedBuyCommonRequest;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionCompleteRequest;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionCompleteResponse;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionConfirmRequest;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionConfirmResponse;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionInputRequest;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionInputResponse;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionListRequest;
import webbroker3.mf.message.WEB3MutualFixedBuyConditionListResponse;
import webbroker3.mf.message.WEB3MutualFrgnMmfOrderAcceptRequest;
import webbroker3.mf.message.WEB3MutualFrgnMmfOrderAcceptResponse;
import webbroker3.mf.message.WEB3MutualOrderAcceptRequest;
import webbroker3.mf.message.WEB3MutualOrderAcceptResponse;
import webbroker3.mf.message.WEB3MutualOrderReferenceRequest;
import webbroker3.mf.message.WEB3MutualOrderReferenceResponse;
import webbroker3.mf.message.WEB3MutualSellCompleteRequest;
import webbroker3.mf.message.WEB3MutualSellCompleteResponse;
import webbroker3.mf.message.WEB3MutualSellConfirmRequest;
import webbroker3.mf.message.WEB3MutualSellConfirmResponse;
import webbroker3.mf.message.WEB3MutualSellInputRequest;
import webbroker3.mf.message.WEB3MutualSellInputResponse;
import webbroker3.mf.message.WEB3MutualSellSwtListRequest;
import webbroker3.mf.message.WEB3MutualSellSwtListResponse;
import webbroker3.mf.message.WEB3MutualSwTargetListRequest;
import webbroker3.mf.message.WEB3MutualSwTargetListResponse;
import webbroker3.mf.message.WEB3MutualSwitchingCompleteRequest;
import webbroker3.mf.message.WEB3MutualSwitchingCompleteResponse;
import webbroker3.mf.message.WEB3MutualSwitchingConfirmRequest;
import webbroker3.mf.message.WEB3MutualSwitchingConfirmResponse;
import webbroker3.mf.message.WEB3MutualSwitchingInputRequest;
import webbroker3.mf.message.WEB3MutualSwitchingInputResponse;
import webbroker3.mf.message.WEB3MutualTradeOrderNotifyRequest;
import webbroker3.mf.message.WEB3MutualTradeOrderNotifyResponse;
import webbroker3.mf.service.delegate.WEB3AdminMutualCategoryRegistService;
import webbroker3.mf.service.delegate.WEB3AdminMutualConditionsReferenceService;
import webbroker3.mf.service.delegate.WEB3AdminMutualConditionsService;
import webbroker3.mf.service.delegate.WEB3AdminMutualDisplayOrderService;
import webbroker3.mf.service.delegate.WEB3AdminMutualFrgncalService;
import webbroker3.mf.service.delegate.WEB3AdminMutualTPACancelService;
import webbroker3.mf.service.delegate.WEB3AdminMutualTPAdjustService;
import webbroker3.mf.service.delegate.WEB3MutualBalanceReferenceService;
import webbroker3.mf.service.delegate.WEB3MutualBuyInputService;
import webbroker3.mf.service.delegate.WEB3MutualBuyListService;
import webbroker3.mf.service.delegate.WEB3MutualBuyService;
import webbroker3.mf.service.delegate.WEB3MutualCancelAcceptService;
import webbroker3.mf.service.delegate.WEB3MutualCancelAcceptUnitService;
import webbroker3.mf.service.delegate.WEB3MutualCancelService;
import webbroker3.mf.service.delegate.WEB3MutualFixedBuyApplyService;
import webbroker3.mf.service.delegate.WEB3MutualFixedBuyConditionListService;
import webbroker3.mf.service.delegate.WEB3MutualFixedBuyConditionService;
import webbroker3.mf.service.delegate.WEB3MutualFrgnMmfOrderAcceptService;
import webbroker3.mf.service.delegate.WEB3MutualOrderAcceptService;
import webbroker3.mf.service.delegate.WEB3MutualOrderAcceptUnitService;
import webbroker3.mf.service.delegate.WEB3MutualOrderReferenceService;
import webbroker3.mf.service.delegate.WEB3MutualRecruitOrderInputService;
import webbroker3.mf.service.delegate.WEB3MutualRecruitOrderService;
import webbroker3.mf.service.delegate.WEB3MutualSellInputService;
import webbroker3.mf.service.delegate.WEB3MutualSellService;
import webbroker3.mf.service.delegate.WEB3MutualSellSwtListInquiryService;
import webbroker3.mf.service.delegate.WEB3MutualSwitchingInputService;
import webbroker3.mf.service.delegate.WEB3MutualSwitchingService;
import webbroker3.mf.service.delegate.WEB3MutualSwtProductListService;
import webbroker3.mf.service.delegate.WEB3MutualTradeOrderNotifyService;
import webbroker3.mf.service.delegate.WEB3MutualTradeOrderNotifyUnitService;
import webbroker3.mf.service.delegate.stdimpls.WEB3AdminMutualCategoryRegistServiceImpl;
import webbroker3.mf.service.delegate.stdimpls.WEB3AdminMutualConditionsReferenceServiceImpl;
import webbroker3.mf.service.delegate.stdimpls.WEB3AdminMutualConditionsServiceImpl;
import webbroker3.mf.service.delegate.stdimpls.WEB3AdminMutualDisplayOrderServiceImpl;
import webbroker3.mf.service.delegate.stdimpls.WEB3AdminMutualFrgncalServiceImpl;
import webbroker3.mf.service.delegate.stdimpls.WEB3AdminMutualTPACancelServiceImpl;
import webbroker3.mf.service.delegate.stdimpls.WEB3AdminMutualTPAdjustServiceImpl;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualBalanceReferenceServiceImpl;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualBuyInputServiceImpl;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualBuyListServiceImpl;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualBuyServiceImpl;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualCancelAcceptServiceImpl;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualCancelAcceptUnitServiceImpl;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualCancelServiceImpl;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualFixedBuyApplyServiceImpl;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualFixedBuyConditionListServiceImpl;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualFixedBuyConditionServiceImpl;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualFrgnMmfOrderAcceptServiceImpl;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualOrderAcceptServiceImpl;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualOrderAcceptUnitServiceImpl;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualOrderReferenceServiceImpl;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualRecruitOrderInputServiceImpl;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualRecruitOrderServiceImpl;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualSellInputServiceImpl;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualSellServiceImpl;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualSellSwtListInquiryServiceImpl;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualSwitchingInputServiceImpl;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualSwitchingServiceImpl;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualSwtProductListServiceImpl;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualTradeOrderNotifyServiceImpl;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualTradeOrderNotifyUnitServiceImpl;
import webbroker3.mqgateway.WEB3MQGatewayInterceptor;
import webbroker3.util.WEB3LogUtility;


/**
 * Webbroker3-MF �v���O�C���N���X�B
 *
 * @@author ����� (���u)
 * @@version 1.0
 */
public final class WEB3MutualFundAppPlugin extends Plugin
{
    /**
     * ���O���[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFundAppPlugin.class);

    /**
     * �R���X�g���N�^�B
     */
    public WEB3MutualFundAppPlugin()
    {
    }

    /**
     * �v���O�C���G���g���[�|�C���g�B
     */
    public static void plug() throws Exception
    {
        String METHOD_NAME = "plug()";
        log.entering(METHOD_NAME);

        plug(WEB3MutualFundAppPlugin.class);

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
            l_finApp.uninstallTradingModule("xb-mf-pdbt");
        }
        catch (NotInstalledException l_ex)
        {
            log.debug(l_ex.getMessage());
        }

        try
        {
            log.debug("Installing TradingModule : web3-mf");
            l_finApp.installTradingModule(new WEB3MutualFundTradingModule());
            log.debug("Installed TradingModule : web3-mf");
        }
        catch (AlreadyInstalledException l_ex)
        {
            log.debug(l_ex.getMessage());
        }

        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);

        // �g���v���_�N�g�E�}�l�[�W���[
        l_tradingModule.overrideProductManager(new WEB3MutualFundProductManager());

        // �v�Z�T�[�r�X�N���X
        l_tradingModule.overrideBizLogicProvider(new WEB3MutualFundBizLogicProvider());

        // �g�������}�l�[�W��
        l_tradingModule.overrideOrderManager(new WEB3MutualFundOrderManager());

        // �|�W�V�����}�l�[�W��
        l_tradingModule.overridePositionManager(new WEB3MutualFundPositionManager());
        
        // �����M�������R���ʃ`�F�b�N
        WEB3MutualFundOrderManagerReusableValidationsCheck.register();
        
        // Webbroker3-MF-MarketAdaptor �v���O�C��
        WEB3MutualFundMarketAdaptorAppPlugin.plug();


        // DatabaseExtensions �̃v���O�C������ ----------------------
        WEB3MfMasterDatabaseExtensions.plug();
        WEB3MfAccountDatabaseExtensions.plug();
        WEB3MfSessionDatabaseExtensions.plug();

        // Service �̓o�^���� ----------------------

        // ���M�C�O�s��J�����_�[�o�^�T�[�r�X�C���^�t�F�[�X
        Services.registerService(
            WEB3AdminMutualFrgncalService.class,
            new WEB3AdminMutualFrgncalServiceImpl());

        // �Ǘ��ғ��M���������o�^�T�[�r�X�C���^�[�t�F�C�X
        Services.registerService(
            WEB3AdminMutualConditionsService.class,
            new WEB3AdminMutualConditionsServiceImpl());

        // �Ǘ��ғ��M���������o�^�Ɖ�T�[�r�X�C���^�[�t�F�C�X
        Services.registerService(
            WEB3AdminMutualConditionsReferenceService.class,
            new WEB3AdminMutualConditionsReferenceServiceImpl());

        // ���M���T�[�r�X�C���^�t�F�[�X
        Services.registerService(
            WEB3MutualSellService.class,
            new WEB3MutualSellServiceImpl());

        // ���M���抷�ꗗ�Ɖ�T�[�r�X �C���^�[�t�F�C�X
        Services.registerService(
            WEB3MutualSellSwtListInquiryService.class,
            new WEB3MutualSellSwtListInquiryServiceImpl());

        // ���M�����̓T�[�r�X�C���^�[�t�F�C�X
        Services.registerService(
            WEB3MutualSellInputService.class,
            new WEB3MutualSellInputServiceImpl());

        // ���M����T�[�r�X �C���^�t�F�[�X
        Services.registerService(
            WEB3MutualCancelService.class,
            new WEB3MutualCancelServiceImpl());

        // ���M�����t�T�[�r�X�C���^�[�t�F�C�X
        Services.registerService(
            WEB3MutualCancelAcceptService.class,
            new WEB3MutualCancelAcceptServiceImpl());

        // ���M�����t�P���T�[�r�X�C���^�t�F�[�X
        Services.registerService(
            WEB3MutualCancelAcceptUnitService.class,
            new WEB3MutualCancelAcceptUnitServiceImpl());

        // ���M�抷�T�[�r�X�C���^�[�t�F�C�X
        Services.registerService(
            WEB3MutualSwitchingService.class,
            new WEB3MutualSwitchingServiceImpl());

        // ���M�抷���̓T�[�r�X�C���^�t�F�[�X
        Services.registerService(
            WEB3MutualSwitchingInputService.class,
            new WEB3MutualSwitchingInputServiceImpl());

        // ���M������t�T�[�r�X�C���^�[�t�F�C�X
        Services.registerService(
            WEB3MutualOrderAcceptService.class,
            new WEB3MutualOrderAcceptServiceImpl());

        // ���M������t�P���T�[�r�X�C���^�t�F�[�X
        Services.registerService(
            WEB3MutualOrderAcceptUnitService.class,
            new WEB3MutualOrderAcceptUnitServiceImpl());

        // ���M�����Ɖ�T�[�r�X�C���^�[�t�F�C�X�N���X
        Services.registerService(
            WEB3MutualOrderReferenceService.class,
            new WEB3MutualOrderReferenceServiceImpl());

        // ���M���t�ꗗ�Ɖ�T�[�r�X�C���^�[�t�F�C�X
        Services.registerService(
            WEB3MutualBuyListService.class,
            new WEB3MutualBuyListServiceImpl());

        // ���M���t�����T�[�r�X �C���^�[�t�F�C�X
        Services.registerService(
            WEB3MutualBuyService.class,
            new WEB3MutualBuyServiceImpl());

        // ���M���t�������̓T�[�r�X�C���^�t�F�[�X
        Services.registerService(
            WEB3MutualBuyInputService.class,
            new WEB3MutualBuyInputServiceImpl());


        // ���M���������ʒm�T�[�r�X�C���^�[�t�F�C�X
        Services.registerService(
            WEB3MutualTradeOrderNotifyService.class,
            new WEB3MutualTradeOrderNotifyServiceImpl());

        // ���M���������ʒm�P���T�[�r�X�C���^�t�F�[�X
        Services.registerService(
            WEB3MutualTradeOrderNotifyUnitService.class,
            new WEB3MutualTradeOrderNotifyUnitServiceImpl());

        // �Ǘ��҃J�e�S���[�o�^�T�[�r�X 
        Services.registerService(
            WEB3AdminMutualCategoryRegistService.class,
            new WEB3AdminMutualCategoryRegistServiceImpl());

        // ���M�Ǘ��Җ����\�������o�^�T�[�r�X
        Services.registerService(
            WEB3AdminMutualDisplayOrderService.class,
            new WEB3AdminMutualDisplayOrderServiceImpl());
        
        // ���M�c���Ɖ�T�[�r�X�C���^�t�F�C�X
        Services.registerService(
            WEB3MutualBalanceReferenceService.class,
            new WEB3MutualBalanceReferenceServiceImpl());
        
        // ���M��W�������̓T�[�r�X
        Services.registerService(
            WEB3MutualRecruitOrderInputService.class,
            new WEB3MutualRecruitOrderInputServiceImpl());
        
        // ���M��W�����T�[�r�X
        Services.registerService(
            WEB3MutualRecruitOrderService.class,
            new WEB3MutualRecruitOrderServiceImpl());
        
        // ���M�抷������ꗗ�Ɖ�T�[�r�X 
        Services.registerService(
            WEB3MutualSwtProductListService.class,
            new WEB3MutualSwtProductListServiceImpl());
        
        // ���M�Ǘ��җ]�͒����T�[�r�X 
        Services.registerService(
            WEB3AdminMutualTPAdjustService.class,
            new WEB3AdminMutualTPAdjustServiceImpl());
        
        // ���M�Ǘ��җ]�͒�������T�[�r�X 
        Services.registerService(
            WEB3AdminMutualTPACancelService.class,
            new WEB3AdminMutualTPACancelServiceImpl());
        
        // ���M�莞��z���t�V�K�\���T�[�r�X
        Services.registerService(
            WEB3MutualFixedBuyApplyService.class,
            new WEB3MutualFixedBuyApplyServiceImpl());
        
        //���M�莞��z���t�����ꗗ�T�[�r�X 
        Services.registerService(
            WEB3MutualFixedBuyConditionListService.class,
            new WEB3MutualFixedBuyConditionListServiceImpl());
        
        // �莞��z���t���ʃT�[�r�X
        Services.registerService(
            WEB3MutualFixedBuyCommonService.class,
            new WEB3MutualFixedBuyCommonServiceImpl());
        
        //�O��MMF������t�T�[�r�X
        Services.registerService(
            WEB3MutualFrgnMmfOrderAcceptService.class,
            new WEB3MutualFrgnMmfOrderAcceptServiceImpl());

        //���M�莞��z���t���������o�^�T�[�r�X
        Services.registerService(
            WEB3MutualFixedBuyConditionService.class,
            new WEB3MutualFixedBuyConditionServiceImpl());

        // Service.execute()�Ăяo���O���  ----------------------
        // �����J�n�����Ə����I�����������O�o�͂���悤�ɐݒ肷��

        // ���M�C�O�s��J�����_�[�o�^�T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
            WEB3AdminMutualFrgncalService.class,
            new WEB3LogSysTimeInterceptor());

        // �Ǘ��ғ��M���������o�^�T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
            WEB3AdminMutualConditionsService.class,
            new WEB3LogSysTimeInterceptor());

        // �Ǘ��ғ��M���������o�^�Ɖ�T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
            WEB3AdminMutualConditionsReferenceService.class,
            new WEB3LogSysTimeInterceptor());

        // ���M���T�[�r�X �C���^�t�F�[�X
        Services.addInterceptor(
            WEB3MutualSellService.class,
            new WEB3LogSysTimeInterceptor());

        // ���M���抷�ꗗ�Ɖ�T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
            WEB3MutualSellSwtListInquiryService.class,
            new WEB3LogSysTimeInterceptor());

        // ���M�����̓T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
            WEB3MutualSellInputService.class,
            new WEB3LogSysTimeInterceptor());

        // ���M����T�[�r�X �C���^�t�F�[�X
        Services.addInterceptor(
            WEB3MutualCancelService.class,
            new WEB3LogSysTimeInterceptor());

        // ���M�����t�T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
            WEB3MutualCancelAcceptService.class,
            new WEB3LogSysTimeInterceptor());

        // ���M�����t�P���T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
            WEB3MutualCancelAcceptUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // ���M�抷�T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
            WEB3MutualSwitchingService.class,
            new WEB3LogSysTimeInterceptor());

        // ���M�抷���̓T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
            WEB3MutualSwitchingInputService.class,
            new WEB3LogSysTimeInterceptor());

        // ���M������t�T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
            WEB3MutualOrderAcceptService.class,
            new WEB3LogSysTimeInterceptor());

        // ���M������t�P���T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
            WEB3MutualOrderAcceptUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // ���M�����Ɖ�T�[�r�X�C���^�[�t�F�C�X�N���X
        Services.addInterceptor(
            WEB3MutualOrderReferenceService.class,
            new WEB3LogSysTimeInterceptor());

        // ���M���t�ꗗ�Ɖ�T�[�r�X �C���^�[�t�F�C�X
        Services.addInterceptor(
            WEB3MutualBuyListService.class,
            new WEB3LogSysTimeInterceptor());

        // ���M���t�����T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
            WEB3MutualBuyService.class,
            new WEB3LogSysTimeInterceptor());

        // ���M���t�������̓T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
            WEB3MutualBuyInputService.class,
            new WEB3LogSysTimeInterceptor());

        // ���M���������ʒm�T�[�r�X�C���^�[�t�F�C�X�N���X
        Services.addInterceptor(
            WEB3MutualTradeOrderNotifyService.class,
            new WEB3LogSysTimeInterceptor());

        // ���M���������ʒm�P���T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
            WEB3MutualTradeOrderNotifyUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // �Ǘ��҃J�e�S���[�o�^�T�[�r�X ���O
        Services.addInterceptor(
            WEB3AdminMutualCategoryRegistService.class,
            new WEB3LogSysTimeInterceptor());

        // ���M�Ǘ��Җ����\�������o�^�T�[�r�X
        Services.addInterceptor(
            WEB3AdminMutualDisplayOrderService.class,
            new WEB3LogSysTimeInterceptor());

        // ���M�c���Ɖ�T�[�r�X
        Services.addInterceptor(
            WEB3MutualBalanceReferenceService.class,
            new WEB3LogSysTimeInterceptor());
        
        // ���M��W�������̓T�[�r�X
        Services.addInterceptor(
            WEB3MutualRecruitOrderInputService.class,
            new WEB3LogSysTimeInterceptor());

        // ���M��W�����T�[�r�X
        Services.addInterceptor(
            WEB3MutualRecruitOrderService.class,
            new WEB3LogSysTimeInterceptor());
        
        // ���M�抷������ꗗ�Ɖ�T�[�r�X 
        Services.addInterceptor(
            WEB3MutualSwtProductListService.class,
            new WEB3LogSysTimeInterceptor());
        
        // ���M�Ǘ��җ]�͒����T�[�r�X 
        Services.addInterceptor(
            WEB3AdminMutualTPAdjustService.class,
            new WEB3LogSysTimeInterceptor());
        
        // ���M�Ǘ��җ]�͒�������T�[�r�X
        Services.addInterceptor(
            WEB3AdminMutualTPACancelService.class,
            new WEB3LogSysTimeInterceptor());
        
        //���M�莞��z���t�V�K�\���T�[�r�X
        Services.addInterceptor(
            WEB3MutualFixedBuyApplyService.class,
            new WEB3LogSysTimeInterceptor());
        
        //���M�莞��z���t�����ꗗ�T�[�r�X
        Services.addInterceptor(
            WEB3MutualFixedBuyConditionListService.class,
            new WEB3LogSysTimeInterceptor());

        //�O��MMF������t�T�[�r�X
        Services.addInterceptor(
            WEB3MutualFrgnMmfOrderAcceptService.class,
            new WEB3LogSysTimeInterceptor());

        //���M�莞��z���t���������o�^�T�[�r�X
        Services.addInterceptor(
            WEB3MutualFixedBuyConditionService.class,
            new WEB3LogSysTimeInterceptor());

        //Service �� ServiceInterceptor ��ݒ肷�� ----------------------
        
        // ���M�C�O�s��J�����_�[�o�^�T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
            WEB3AdminMutualConditionsService.class,
            new WEB3AdminMutualConditionsServiceInterceptor());
        
        // ���M���T�[�r�X �C���^�t�F�[�X
        Services.addInterceptor(
            WEB3MutualSellService.class,
            new WEB3MutualSellServiceInterceptor());

        // ���M���抷�ꗗ�Ɖ�T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
            WEB3MutualSellSwtListInquiryService.class,
            new WEB3MutualSellSwtListInquiryServiceInterceptor());

        // ���M�����̓T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
            WEB3MutualSellInputService.class,
            new WEB3MutualSellInputSeviceInterceptor());

        // ���M����T�[�r�X �C���^�t�F�[�X
        Services.addInterceptor(
            WEB3MutualCancelService.class,
            new WEB3MutualCancelServiceInterceptor());

        // ���M�����t�P���T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
            WEB3MutualCancelAcceptUnitService.class,
            new WEB3MutualCancelAcceptUnitServiceInterceptor());

        // ���M�抷�T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
            WEB3MutualSwitchingService.class,
            new WEB3MutualSwitchingServiceInterceptor());

        // ���M�抷���̓T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
            WEB3MutualSwitchingInputService.class,
            new WEB3MutualSwitchingInputServiceInterceptor());

        // ���M������t�P���T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
            WEB3MutualOrderAcceptUnitService.class,
            new WEB3MutualOrderAcceptUnitServiceInterceptor());

        // ���M�����Ɖ�T�[�r�X�C���^�[�t�F�C�X�N���X
        Services.addInterceptor(
            WEB3MutualOrderReferenceService.class,
            new WEB3MutualOrderReferenceServiceInterceptor());

        // ���M���t�ꗗ�Ɖ�T�[�r�X �C���^�[�t�F�C�X
        Services.addInterceptor(
            WEB3MutualBuyListService.class,
            new WEB3MutualBuyListServiceInterceptor());

        // ���M���t�����T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
            WEB3MutualBuyService.class,
            new WEB3MutualBuyServiceInterceptor());

        // ���M���t�������̓T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
            WEB3MutualBuyInputService.class,
            new WEB3MutualBuyInputServiceInterceptor());

        // ���M���������ʒm�P���T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
            WEB3MutualTradeOrderNotifyUnitService.class,
            new WEB3MutualTradeOrderNotifyUnitServiceIntercetor());
            
        // ���M�Ǘ��Җ����\�������o�^�T�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
            WEB3AdminMutualDisplayOrderService.class,
            new WEB3AdminMutualDisplayOrderServiceInterceptor());
         
        // ���M�c���Ɖ�T�[�r�X
        Services.addInterceptor(
            WEB3MutualBalanceReferenceService.class,
            new WEB3MutualBalanceReferenceServiceInterceptor());
        
        // ���M��W�������̓T�[�r�X
        Services.addInterceptor(
            WEB3MutualRecruitOrderInputService.class,
            new WEB3MutualRecruitOrderInputServiceInterceptor());

        // ���M��W�����T�[�r�X
        Services.addInterceptor(
            WEB3MutualRecruitOrderService.class,
            new WEB3MutualRecruitOrderServiceInterceptor());
        
        // ���M�抷������ꗗ�Ɖ�T�[�r�X 
        Services.addInterceptor(
            WEB3MutualSwtProductListService.class,
            new WEB3MutualSwtProductListInterceptor());
        
        // ���M�Ǘ��җ]�͒����T�[�r�X 
        Services.addInterceptor(
            WEB3AdminMutualTPAdjustService.class,
            new WEB3AdminMutualTPAdjustServiceInterceptor());
        
        // ���M�Ǘ��җ]�͒�������T�[�r�X
        Services.addInterceptor(
            WEB3AdminMutualTPACancelService.class,
            new WEB3AdminMutualTPACancelServiceInterceptor());
        
        //���M�莞��z���t�V�K�\���T�[�r�X
        Services.addInterceptor(
            WEB3MutualFixedBuyApplyService.class,
            new WEB3MutualFixedBuyApplyServiceInterceptor());
        
        //���M�莞��z���t�����ꗗ�T�[�r�X�C���^�Z�v�^    
        Services.addInterceptor(
            WEB3MutualFixedBuyConditionListService.class,
            new WEB3MutualFixedBuyConditionListServiceInterceptor());

        //���M�莞��z���t���������o�^�T�[�r�X�C���^�Z�v�^
        Services.addInterceptor(
            WEB3MutualFixedBuyConditionService.class,
            new WEB3MutualFixedBuyConditionServiceInterceptor());

        // Service �� Interceptor �ݒ菈�� ----------------------
        // �����g�����U�N�V�����ݒ�

        // ���M�C�O�s��J�����_�[�o�^�T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
            WEB3AdminMutualFrgncalService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // �Ǘ��ғ��M���������o�^�T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
            WEB3AdminMutualConditionsService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // �Ǘ��ғ��M���������o�^�Ɖ�T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
            WEB3AdminMutualConditionsReferenceService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // ���M���T�[�r�X �C���^�t�F�[�X
        Services.addInterceptor(
            WEB3MutualSellService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // ���M���抷�ꗗ�Ɖ�T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
            WEB3MutualSellSwtListInquiryService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // ���M�����̓T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
            WEB3MutualSellInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // ���M����T�[�r�X �C���^�t�F�[�X
        Services.addInterceptor(
            WEB3MutualCancelService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // ���M�����t�P���T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
            WEB3MutualCancelAcceptUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // ���M�抷�T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
            WEB3MutualSwitchingService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // ���M�抷���̓T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
            WEB3MutualSwitchingInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // ���M������t�P���T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
            WEB3MutualOrderAcceptUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // ���M�����Ɖ�T�[�r�X�C���^�[�t�F�C�X�N���X
        Services.addInterceptor(
            WEB3MutualOrderReferenceService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // ���M���t�ꗗ�Ɖ�T�[�r�X �C���^�[�t�F�C�X
        Services.addInterceptor(
            WEB3MutualBuyListService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // ���M���t�����T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
            WEB3MutualBuyService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // ���M���t�������̓T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
            WEB3MutualBuyInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // ���M���������ʒm�P���T�[�r�X�C���^�[�t�F�C�X
        Services.addInterceptor(
            WEB3MutualTradeOrderNotifyUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // �Ǘ��҃J�e�S���[�o�^�T�[�r�X 
        Services.addInterceptor(
            WEB3AdminMutualCategoryRegistService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // ���M�Ǘ��Җ����\�������o�^�T�[�r�X
        Services.addInterceptor(
            WEB3AdminMutualDisplayOrderService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // ���M�c���Ɖ�T�[�r�X
        Services.addInterceptor(
            WEB3MutualBalanceReferenceService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        // ���M��W�������̓T�[�r�X
        Services.addInterceptor(
            WEB3MutualRecruitOrderInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // ���M��W�����T�[�r�X
        Services.addInterceptor(
            WEB3MutualRecruitOrderService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        // ���M�抷������ꗗ�Ɖ�T�[�r�X 
        Services.addInterceptor(
            WEB3MutualSwtProductListService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        // ���M�Ǘ��җ]�͒����T�[�r�X 
        Services.addInterceptor(
            WEB3AdminMutualTPAdjustService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        // ���M�Ǘ��җ]�͒�������T�[�r�X
        Services.addInterceptor(
            WEB3AdminMutualTPACancelService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //���M�莞��z���t�V�K�\���T�[�r�X �C���^�t�F�[�X
        Services.addInterceptor(
            WEB3MutualFixedBuyApplyService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //���M�莞��z���t�����ꗗ�T�[�r�X 
        Services.addInterceptor(
            WEB3MutualFixedBuyConditionListService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�O��MMF������t�T�[�r�X
        Services.addInterceptor(
            WEB3MutualFrgnMmfOrderAcceptService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //���M�莞��z���t���������o�^�T�[�r�X
        Services.addInterceptor(
            WEB3MutualFixedBuyConditionService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // MQGatewayInterceptor�̐ݒ� ---------------
        
        // ���M���t�T�[�r�X
        Services.addInterceptor(
            WEB3MutualBuyService.class,
            new WEB3MQGatewayInterceptor());
        
        // ���M���T�[�r�X
        Services.addInterceptor(
            WEB3MutualSellService.class,
            new WEB3MQGatewayInterceptor());
        
        // ���M�抷�T�[�r�X
        Services.addInterceptor(
            WEB3MutualSwitchingService.class,
            new WEB3MQGatewayInterceptor());
        
        // ���M����T�[�r�X
        Services.addInterceptor(
            WEB3MutualCancelService.class,
            new WEB3MQGatewayInterceptor());
        
        // ���M��W�����T�[�r�X
        Services.addInterceptor(
            WEB3MutualRecruitOrderService.class,
            new WEB3MQGatewayInterceptor());

        // Message �̓o�^���� ----------------------

        // ���M���m�F���N�G�X�g 
        regClass(WEB3MutualSellConfirmRequest.class);
        // ���M���m�F���X�|���X 
        regClass(WEB3MutualSellConfirmResponse.class);
        // ���M��񊮗����N�G�X�g 
        regClass(WEB3MutualSellCompleteRequest.class);
        // ���M��񊮗����X�|���X 
        regClass(WEB3MutualSellCompleteResponse.class);
        // ���M���抷�ꗗ�Ɖ�N�G�X�g 
        regClass(WEB3MutualSellSwtListRequest.class);
        // ���M���抷�ꗗ�Ɖ�X�|���X 
        regClass(WEB3MutualSellSwtListResponse.class);
        // ���M�����̓��N�G�X�g 
        regClass(WEB3MutualSellInputRequest.class);
        // ���M�����̓��X�|���X 
        regClass(WEB3MutualSellInputResponse.class);
        // ���M�C�O�s��J�����_�[�o�^�������N�G�X�g 
        regClass(WEB3AdminMutualFrgncalCompleteRequest.class);
        // ���M�C�O�s��J�����_�[�o�^�������X�|���X 
        regClass(WEB3AdminMutualFrgncalCompleteResponse.class);
        // ���M�C�O�s��J�����_�[�o�^�Ɖ�N�G�X�g 
        regClass(WEB3AdminMutualFrgncalReferenceRequest.class);
        // ���M�C�O�s��J�����_�[�o�^�Ɖ�X�|���X 
        regClass(WEB3AdminMutualFrgncalReferenceResponse.class);
        // ���M�C�O�s��J�����_�[�o�^���̓��N�G�X�g 
        regClass(WEB3AdminMutualFrgncalInputRequest.class);
        // ���M�C�O�s��J�����_�[�o�^���̓��X�|���X 
        regClass(WEB3AdminMutualFrgncalInputResponse.class);
        // ���M����m�F���N�G�X�g 
        regClass(WEB3MutualCancelConfirmRequest.class);
        // ���M����m�F���X�|���X 
        regClass(WEB3MutualCancelConfirmResponse.class);
        // ���M����������N�G�X�g
        regClass(WEB3MutualCancelCompleteRequest.class);
        // ���M����������X�|���X 
        regClass(WEB3MutualCancelCompleteResponse.class);
        // ���M�����t���N�G�X�g 
        regClass(WEB3MutualCancelAcceptRequest.class);
        // ���M�����t���X�|���X 
        regClass(WEB3MutualCancelAcceptResponse.class);
        // ���M�抷�m�F���N�G�X�g 
        regClass(WEB3MutualSwitchingConfirmRequest.class);
        // ���M�抷�m�F���X�|���X 
        regClass(WEB3MutualSwitchingConfirmResponse.class);
        // ���M�抷�������N�G�X�g 
        regClass(WEB3MutualSwitchingCompleteRequest.class);
        // ���M�抷�������X�|���X 
        regClass(WEB3MutualSwitchingCompleteResponse.class);
        // ���M�抷���̓��N�G�X�g 
        regClass(WEB3MutualSwitchingInputRequest.class);
        // ���M�抷���̓��X�|���X 
        regClass(WEB3MutualSwitchingInputResponse.class);
        // ���M������t���N�G�X�g 
        regClass(WEB3MutualOrderAcceptRequest.class);
        // ���M������t���X�|���X 
        regClass(WEB3MutualOrderAcceptResponse.class);
        // ���M�����Ɖ�N�G�X�g 
        regClass(WEB3MutualOrderReferenceRequest.class);
        // ���M�����Ɖ�X�|���X 
        regClass(WEB3MutualOrderReferenceResponse.class);
        // ���M���t�ꗗ�Ɖ�N�G�X�g 
        regClass(WEB3MutualBuyListRequest.class);
        // ���M���t�ꗗ�Ɖ�X�|���X 
        regClass(WEB3MutualBuyListResponse.class);
        // ���M���t�����m�F���N�G�X�g 
        regClass(WEB3MutualBuyConfirmRequest.class);
        // ���M���t�����m�F���X�|���X 
        regClass(WEB3MutualBuyConfirmResponse.class);
        // ���M���t�����������N�G�X�g 
        regClass(WEB3MutualBuyCompleteRequest.class);
        // ���M���t�����������X�|���X 
        regClass(WEB3MutualBuyCompleteResponse.class);
        // ���M���t�������̓��N�G�X�g 
        regClass(WEB3MutualBuyInputRequest.class);
        // ���M���t�������̓��X�|���X 
        regClass(WEB3MutualBuyInputResponse.class);
        // ���M���������ʒm���N�G�X�g 
        regClass(WEB3MutualTradeOrderNotifyRequest.class);
        // ���M���������ʒm���X�|���X 
        regClass(WEB3MutualTradeOrderNotifyResponse.class);
        // ���M���������o�^�m�F���N�G�X�g 
        regClass(WEB3AdminMutualConditionsConfirmRequest.class);
        // ���M���������o�^�m�F���X�|���X 
        regClass(WEB3AdminMutualConditionsConfirmResponse.class);
        // ���M���������o�^�������N�G�X�g 
        regClass(WEB3AdminMutualConditionsCompleteRequest.class);
        // ���M���������o�^�������X�|���X 
        regClass(WEB3AdminMutualConditionsCompleteResponse.class);
        // ���M���������o�^�Ɖ�N�G�X�g 
        regClass(WEB3AdminMutualConditionsReferenceRequest.class);
        // ���M���������o�^�Ɖ�X�|���X 
        regClass(WEB3AdminMutualConditionsReferenceResponse.class);
        // ���M���������o�^�Ɖ���̓��N�G�X�g 
        regClass(WEB3AdminMutualConditionsRefInputRequest.class);
        // ���M���������o�^�Ɖ���̓��X�|���X 
        regClass(WEB3AdminMutualConditionsRefInputResponse.class);
        // ���M���������o�^���̓��N�G�X�g 
        regClass(WEB3AdminMutualConditionsInputRequest.class);
        // ���M���������o�^���̓��X�|���X 
        regClass(WEB3AdminMutualConditionsInputResponse.class);       
        //���M��W�������̓��N�G�X�g    
        regClass(WEB3MutualApplyInputRequest.class);
        //���M��W�������̓��X�|���X    
        regClass(WEB3MutualApplyInputResponse.class);
        //���M��W�����m�F���N�G�X�g    
        regClass(WEB3MutualApplyConfirmRequest.class);       
        //���M��W�����m�F���X�|���X    
        regClass(WEB3MutualApplyConfirmResponse.class);       
        //���M��W�����������N�G�X�g    
        regClass(WEB3MutualApplyCompleteRequest.class);       
        //���M��W�����������X�|���X    
        regClass(WEB3MutualApplyCompleteResponse.class);              
        //���M�抷������ꗗ���N�G�X�g
        regClass(WEB3MutualSwTargetListRequest.class);       
        //���M�抷������ꗗ���X�|���X
        regClass(WEB3MutualSwTargetListResponse.class);       
        //���M�莞��z���t���ʃ��N�G�X�g
        regClass(WEB3MutualFixedBuyCommonRequest.class);
        //���M�莞��z���t�V�K�\�����̓��N�G�X�g
        regClass(WEB3MutualFixedBuyApplyInputRequest.class);
        //���M�莞��z���t�V�K�\�����̓��X�|���X
        regClass(WEB3MutualFixedBuyApplyInputResponse.class);
        //���M�莞��z���t�V�K�\���m�F���N�G�X�g
        regClass(WEB3MutualFixedBuyApplyConfirmRequest.class);
        //���M�莞��z���t�V�K�\���m�F���X�|���X
        regClass(WEB3MutualFixedBuyApplyConfirmResponse.class);
        //���M�莞��z���t�����ꗗ���N�G�X�g    
        regClass(WEB3MutualFixedBuyConditionListRequest.class);
        //���M�莞��z���t�����ꗗ���X�|���X    
        regClass(WEB3MutualFixedBuyConditionListResponse.class);
        //�O��MMF������t���N�G�X�g
        regClass(WEB3MutualFrgnMmfOrderAcceptRequest.class);
        //�O��MMF������t���X�|���X
        regClass(WEB3MutualFrgnMmfOrderAcceptResponse.class);

        // ���M�Ǘ��҃J�e�S���[�o�^���͉�ʗv��  
        regClass(WEB3AdminMutualCategoryRegistInputRequest.class);
        // ���M�Ǘ��҃J�e�S���[�o�^���͉�ʉ����@@ 
        regClass(WEB3AdminMutualCategoryRegistInputResponse.class);
        // ���M�Ǘ��҃J�e�S���[�ύX���͉�ʗv���@@�@@    
        regClass(WEB3AdminMutualCategoryRegistChangeRequest.class);
        // ���M�Ǘ��҃J�e�S���[�ύX���͉�ʉ���  
        regClass(WEB3AdminMutualCategoryRegistChangeResponse.class);
        // ���M�Ǘ��҃J�e�S���[�o�^�m�F�v���@@�@@  
        regClass(WEB3AdminMutualCategoryRegistConfirmRequest.class);
        // ���M�Ǘ��҃J�e�S���[�o�^�m�F����    
        regClass(WEB3AdminMutualCategoryRegistConfirmResponse.class);
        // ���M�Ǘ��҃J�e�S���[�o�^�����v��    
        regClass(WEB3AdminMutualCategoryRegistCompleteRequest.class);
        // ���M�Ǘ��҃J�e�S���[�o�^��������    
        regClass(WEB3AdminMutualCategoryRegistCompleteResponse.class);
        // ���M�Ǘ��Җ����\�������o�^���͉�ʗv���@@    
        regClass(WEB3AdminMutualDisplayOrderInputRequest.class);
        // ���M�Ǘ��Җ����\�������o�^���͉�ʉ��� 
        regClass(WEB3AdminMutualDisplayOrderInputResponse.class);
        // ���M�Ǘ��Җ����\�������o�^�����v���@@  
        regClass(WEB3AdminMutualDisplayOrderCompleteRequest.class);
        // ���M�Ǘ��Җ����\�������o�^��������   
        regClass(WEB3AdminMutualDisplayOrderCompleteResponse.class);

        //���M�c���Ɖ�
        regClass(WEB3MutualBalanceReferenceRequest.class);
        regClass(WEB3MutualBalanceReferenceResponse.class);
        regClass(WEB3MutualBalanceReferenceTotalRequest.class);
        regClass(WEB3MutualBalanceReferenceTotalResponse.class);
        
        // ���M�Ǘ��җ]�͒�������ꗗ���N�G�X�g
        regClass(WEB3AdminMutualTPACancelListRequest.class);
        // ���M�Ǘ��җ]�͒�������ꗗ���X�|���X
        regClass(WEB3AdminMutualTPACancelListResponse.class);
        // ���M�Ǘ��җ]�͒�������������N�G�X�g�@@  
        regClass(WEB3AdminMutualTPACancelCompleteRequest.class);
        // ���M�Ǘ��җ]�͒�������������X�|���X 
        regClass(WEB3AdminMutualTPACancelCompleteResponse.class);
        
        // ���M�Ǘ��җ]�͒����m�F���N�G�X�g
        regClass(WEB3AdminMutualTPAdjustConfirmRequest.class);
        // ���M�Ǘ��җ]�͒����m�F���X�|���X
        regClass(WEB3AdminMutualTPAdjustConfirmResponse.class);
        // ���M�Ǘ��җ]�͒����������N�G�X�g
        regClass(WEB3AdminMutualTPAdjustCompleteRequest.class);
        // ���M�Ǘ��җ]�͒����������X�|���X
        regClass(WEB3AdminMutualTPAdjustCompleteResponse.class);

        // ���M�莞��z���t���������o�^���̓��N�G�X�g
        regClass(WEB3MutualFixedBuyConditionInputRequest.class);
        // ���M�莞��z���t���������o�^���̓��X�|���X
        regClass(WEB3MutualFixedBuyConditionInputResponse.class);
        // ���M�莞��z���t���������o�^�m�F���N�G�X�g
        regClass(WEB3MutualFixedBuyConditionConfirmRequest.class);
        // ���M�莞��z���t���������o�^�m�F���X�|���X
        regClass(WEB3MutualFixedBuyConditionConfirmResponse.class);
        // ���M�莞��z���t���������o�^�������N�G�X�g
        regClass(WEB3MutualFixedBuyConditionCompleteRequest.class);
        // ���M�莞��z���t���������o�^�������X�|���X
        regClass(WEB3MutualFixedBuyConditionCompleteResponse.class);

        //Handler �̓o�^���� ----------------------

        // ���M�C�O�s��J�����_�[�o�^�n���h���̓o�^ 
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3AdminMutualFrgncalInputRequest.class,
            WEB3MutualFrgncalHandler.class,
            "frgncalInputRequest");     
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3AdminMutualFrgncalReferenceRequest.class,
            WEB3MutualFrgncalHandler.class,
            "searchFrgncalReg");     
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3AdminMutualFrgncalCompleteRequest.class,
            WEB3MutualFrgncalHandler.class,
            "completeFrgncalReg");     
        // �Ǘ��ғ��M���������o�^�n���h���̓o�^
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3AdminMutualConditionsInputRequest.class,
            WEB3AdminMutualConditionsHandler.class,
            "productConditionsRegistInputRequest");     
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3AdminMutualConditionsConfirmRequest.class,
            WEB3AdminMutualConditionsHandler.class,
            "searchProductConditionsRegist");     
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3AdminMutualConditionsCompleteRequest.class,
            WEB3AdminMutualConditionsHandler.class,
            "completeProductConditionsRegist");     
        // �Ǘ��ғ��M���������o�^�Ɖ�n���h���̓o�^ 
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3AdminMutualConditionsRefInputRequest.class,
            WEB3AdminMutualConditionsReferenceHandler.class,
            "productConditionsRegistRefRequest");
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3AdminMutualConditionsReferenceRequest.class,
            WEB3AdminMutualConditionsReferenceHandler.class,
            "searchProductConditionsRegist");
        // ���M���n���h���̓o�^ 
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3MutualSellConfirmRequest.class,
            WEB3MutualSellHandler.class,
            "confirmSell");     
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3MutualSellCompleteRequest.class,
            WEB3MutualSellHandler.class,
            "completeSell");     
        // ���M���抷�ꗗ�Ɖ�n���h���̓o�^
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3MutualSellSwtListRequest.class,
            WEB3MutualSellSwtListInquiryHandler.class,
            "searchSellSwtList");     
        // ���M�����̓n���h���̓o�^ 
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3MutualSellInputRequest.class,
            WEB3MutualSellInputHandler.class,
            "sellInputRequest");     
        // ���M����n���h���̓o�^ 
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3MutualCancelConfirmRequest.class,
            WEB3MutualCancelHandler.class,
            "confirmCancel");     
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3MutualCancelCompleteRequest.class,
            WEB3MutualCancelHandler.class,
            "completeCancel");     
        // ���M�����t�n���h���̓o�^ 
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3MutualCancelAcceptRequest.class,
            WEB3MutualCancelAcceptHandler.class,
            "cancelAcceptRequest");     
        // ���M�抷�n���h���̓o�^
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3MutualSwitchingConfirmRequest.class,
            WEB3MutualSwitchingHandler.class,
            "confirmSwitching");     
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3MutualSwitchingCompleteRequest.class,
            WEB3MutualSwitchingHandler.class,
            "completeSwitching");     
        // ���M�抷���̓n���h���̓o�^
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3MutualSwitchingInputRequest.class,
            WEB3MutualSwitchingInputHandler.class,
            "switchingInputRequest");     
        // ���M������t�n���h���̓o�^
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3MutualOrderAcceptRequest.class,
            WEB3MutualOrderAcceptServiceHandler.class,
            "mutualOrderAcceptRequest");     
        // ���M�����Ɖ�n���h���̓o�^ 
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3MutualOrderReferenceRequest.class,
            WEB3MutualOrderReferenceHandler.class,
            "searchOrder");     
        // ���M���t�ꗗ�Ɖ�n���h���̓o�^ 
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3MutualBuyListRequest.class,
            WEB3MutualBuyListHandler.class,
            "searchOrder");     
        // ���M���t�����n���h���̓o�^ 
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3MutualBuyConfirmRequest.class,
            WEB3MutualBuyHandler.class,
            "confirmBuyOrder");     
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3MutualBuyCompleteRequest.class,
            WEB3MutualBuyHandler.class,
            "completeBuyOrder");     
        // ���M���t�������̓n���h���̓o�^
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3MutualBuyInputRequest.class,
            WEB3MutualBuyInputHandler.class,
            "buyInputRequest");     
        // ���M���������ʒm�n���h���̓o�^ 
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3MutualTradeOrderNotifyRequest.class,
            WEB3MutualTradeOrderNotifyHandler.class,
            "tradeOrderNotifyRequest");     

        // ���M�Ǘ��҃J�e�S���[�o�^�n���h��  �̓o�^ 
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3AdminMutualCategoryRegistInputRequest.class,
            WEB3AdminMutualCategoryRegistHandler.class,
            "getCategoryRegistrInput");     
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3AdminMutualCategoryRegistChangeRequest.class,
            WEB3AdminMutualCategoryRegistHandler.class,
            "getCategoryRegistChangeInput");     
        regHandler(
                WEB3MutualFundAppPlugin.class,
                WEB3AdminMutualCategoryRegistConfirmRequest.class,
                WEB3AdminMutualCategoryRegistHandler.class,
                "confirmCategoryRegistRequest");     
        regHandler(
                WEB3MutualFundAppPlugin.class,
                WEB3AdminMutualCategoryRegistCompleteRequest.class,
                WEB3AdminMutualCategoryRegistHandler.class,
                "completeCategoryRegistRequest");     

        // ���M�Ǘ��Җ����\�������o�^�n���h���̓o�^ 
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3AdminMutualDisplayOrderInputRequest.class,
            WEB3AdminMutualDisplayOrderHandler.class,
            "getProductDisplayOrderInput");     
        regHandler(
                WEB3MutualFundAppPlugin.class,
                WEB3AdminMutualDisplayOrderCompleteRequest.class,
                WEB3AdminMutualDisplayOrderHandler.class,
                "completeAdminMutualDisplayOrderRegistr");     
        
        //���M�c���Ɖ�n���h���̓o�^
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3MutualBalanceReferenceRequest.class,
            WEB3MutualBalanceReferenceHandler.class,
            "getBalanceReference");     
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3MutualBalanceReferenceTotalRequest.class,
            WEB3MutualBalanceReferenceHandler.class,
            "getBalanceReferenceTotal");    
        
        //���M��W�������̓n���h���̓o�^
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3MutualApplyInputRequest.class,
            WEB3MutualRecruitOrderInputHandler.class,
            "RecruitOrderInput");
        //���M��W�����n���h���̓o�^
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3MutualApplyConfirmRequest.class,
            WEB3MutualRecruitOrderHandler.class,
            "RecruitOrderValidation"); 
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3MutualApplyCompleteRequest.class,
            WEB3MutualRecruitOrderHandler.class,
            "RecruitOrderRegister");  
        
        //���M�抷������ꗗ�Ɖ�n���h�� 
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3MutualSwTargetListRequest.class,
            WEB3MutualSwtProductListHandler.class,
            "swtListRequest");   
        
        //���M�Ǘ��җ]�͒�������n���h���̓o�^
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3AdminMutualTPACancelListRequest.class,
            WEB3AdminMutualTPACancelHandler.class,
            "tpACancelList"); 
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3AdminMutualTPACancelCompleteRequest.class,
            WEB3AdminMutualTPACancelHandler.class,
            "tpACancelComplete");  
        
        //���M�Ǘ��җ]�͒����n���h���̓o�^
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3AdminMutualTPAdjustConfirmRequest.class,
            WEB3AdminMutualTPAdjustHandler.class,
            "tpAdjustConfirm"); 
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3AdminMutualTPAdjustCompleteRequest.class,
            WEB3AdminMutualTPAdjustHandler.class,
            "tpAdjustComplete");  
        
        //���M�莞��z���t�V�K�\���n���h���̓o�^
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3MutualFixedBuyApplyInputRequest.class,
            WEB3MutualFixedBuyApplyHandler.class,
            "fixedBuyApplyInput");
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3MutualFixedBuyApplyConfirmRequest.class,
            WEB3MutualFixedBuyApplyHandler.class,
            "fixedBuyApplyValidate");
        
        //���M�莞��z���t�����ꗗ�n���h���̓o�^
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3MutualFixedBuyConditionListRequest.class,
            WEB3MutualFixedBuyConditionListHandler.class,
            "searchOrder");
        
        //�O��MMF������t�n���h���̓o�^
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3MutualFrgnMmfOrderAcceptRequest.class,
            WEB3MutualFrgnMmfOrderAcceptHandler.class,
            "orderAcceptRequest");

        //���M�莞��z���t���������o�^�n���h���̓o�^
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3MutualFixedBuyConditionInputRequest.class,
            WEB3MutualFixedBuyConditionHandler.class,
            "mutualFixedBuyConditionInput");
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3MutualFixedBuyConditionConfirmRequest.class,
            WEB3MutualFixedBuyConditionHandler.class,
            "mutualFixedBuyConditionConfirm");
        regHandler(
            WEB3MutualFundAppPlugin.class,
            WEB3MutualFixedBuyConditionCompleteRequest.class,
            WEB3MutualFixedBuyConditionHandler.class,
            "mutualFixedBuyConditionComplete");

        //------------------------------------
        // RAC-CTX�C���^�Z�v�^��ݒ肷�� 
        //------------------------------------
        // ���M������t�ꌏ
        Services.addInterceptor(
            WEB3MutualOrderAcceptUnitService.class,
            new WEB3MutualFundDescendRacCtxInterceptor());
            
        // ���M�����t�ꌏ
        Services.addInterceptor(
            WEB3MutualCancelAcceptUnitService.class,
            new WEB3MutualFundDescendRacCtxInterceptor());
            
        // ���M���������ʒm�ꌏ
        Services.addInterceptor(
            WEB3MutualTradeOrderNotifyUnitService.class,
            new WEB3MutualFundDescendRacCtxInterceptor());

        log.exiting(METHOD_NAME);

    }
}

@
