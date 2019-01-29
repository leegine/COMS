head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.45.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : Webbroker3-Ifo �v���O�C���N���X(WEB3IfoAppPlugin.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/15 ����� (���u) �V�K�쐬
Revesion History : 2004/07/27 ���Ō� (���u) WEB3ProtoQuotePlugin.onPlug()��ǉ�
Revesion History : 2004/07/29 ���Ō� (���u) WEB3OptionOrderCarryOverUnitServiceInterceptor��Plug��ǉ�
Revesion History : 2004/07/29 ���Ō� (���u) �Ή��o�b�O�@@WEB3_IFO_UT-000069
Revesion History : 2004/07/29 ���Ō� (���u) �Ή��o�b�O�@@WEB3_IFO_UT-000101'
Revesion History : 2007/02/01 ���G�� (���u) ���f��No.587
Revision History : 2007/06/22 ��іQ (���u) ���f��No.669
Revesion History : 2008/04/11 ��іQ (���u) ���f��No.846
*/

package webbroker3.ifo;

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

import webbroker3.ifo.data.WEB3IfoMasterDatabaseExtensions;
import webbroker3.ifo.data.WEB3IfoSessionDatabaseExtensions;
import webbroker3.ifo.handler.WEB3FuturesCancelOrderHandler;
import webbroker3.ifo.handler.WEB3FuturesChangeCancelNotifyHandler;
import webbroker3.ifo.handler.WEB3FuturesChangeClosingContractHandler;
import webbroker3.ifo.handler.WEB3FuturesChangeClosingContractInputHandler;
import webbroker3.ifo.handler.WEB3FuturesChangeOpenContractHandler;
import webbroker3.ifo.handler.WEB3FuturesChangeOpenContractInputHandler;
import webbroker3.ifo.handler.WEB3FuturesContractInquiryHandler;
import webbroker3.ifo.handler.WEB3FuturesExecuteReferenceHandler;
import webbroker3.ifo.handler.WEB3FuturesIndividualSettleContractListHandler;
import webbroker3.ifo.handler.WEB3FuturesOpenContractHandler;
import webbroker3.ifo.handler.WEB3FuturesOpenContractInputHandler;
import webbroker3.ifo.handler.WEB3FuturesOrderExecNotifyHandler;
import webbroker3.ifo.handler.WEB3FuturesOrderNotifyHandler;
import webbroker3.ifo.handler.WEB3FuturesSettleContractInputHandler;
import webbroker3.ifo.handler.WEB3FuturesSettleContractOrderHandler;
import webbroker3.ifo.handler.WEB3IfoBalanceReferenceHandler;
import webbroker3.ifo.handler.WEB3IfoChangeCancelOrderAcceptHandler;
import webbroker3.ifo.handler.WEB3IfoCloseNotifyHandler;
import webbroker3.ifo.handler.WEB3IfoExecuteEndNotifyHandler;
import webbroker3.ifo.handler.WEB3IfoOrderAcceptHandler;
import webbroker3.ifo.handler.WEB3OptionCancelOrderHandler;
import webbroker3.ifo.handler.WEB3OptionChangeCancelNotifyHandler;
import webbroker3.ifo.handler.WEB3OptionChangeClosingContractHandler;
import webbroker3.ifo.handler.WEB3OptionChangeClosingContractInputHandler;
import webbroker3.ifo.handler.WEB3OptionChangeOpenContractHandler;
import webbroker3.ifo.handler.WEB3OptionChangeOpenContractInputHandler;
import webbroker3.ifo.handler.WEB3OptionContractInquiryHandler;
import webbroker3.ifo.handler.WEB3OptionIndividualSettleContractListServiceHandler;
import webbroker3.ifo.handler.WEB3OptionOpenContractInputHandler;
import webbroker3.ifo.handler.WEB3OptionOpenContractOrderHandler;
import webbroker3.ifo.handler.WEB3OptionOrderExecNotifyHandler;
import webbroker3.ifo.handler.WEB3OptionOrderExecutedInquiryHandler;
import webbroker3.ifo.handler.WEB3OptionSettleContractHandler;
import webbroker3.ifo.handler.WEB3OptionSettleContractInputServiceHandler;
import webbroker3.ifo.handler.WEB3OptionsOrderNotifyHandler;
import webbroker3.ifo.marketadaptor.WEB3IfoMarketAdaptorAppPlugin;
import webbroker3.ifo.message.*;
import webbroker3.ifo.service.delegate.WEB3FuturesCancelOrderService;
import webbroker3.ifo.service.delegate.WEB3FuturesChangeCancelNotifyService;
import webbroker3.ifo.service.delegate.WEB3FuturesChangeCancelNotifyUnitService;
import webbroker3.ifo.service.delegate.WEB3FuturesChangeClosingContractInputService;
import webbroker3.ifo.service.delegate.WEB3FuturesChangeClosingContractService;
import webbroker3.ifo.service.delegate.WEB3FuturesChangeOpenContractInputService;
import webbroker3.ifo.service.delegate.WEB3FuturesChangeOpenContractService;
import webbroker3.ifo.service.delegate.WEB3FuturesContractInquiryService;
import webbroker3.ifo.service.delegate.WEB3FuturesExecuteReferenceService;
import webbroker3.ifo.service.delegate.WEB3FuturesIndividualSettleContractListService;
import webbroker3.ifo.service.delegate.WEB3FuturesOpenContractInputService;
import webbroker3.ifo.service.delegate.WEB3FuturesOpenContractService;
import webbroker3.ifo.service.delegate.WEB3FuturesOrderExecNotifyService;
import webbroker3.ifo.service.delegate.WEB3FuturesOrderExecNotifyUnitService;
import webbroker3.ifo.service.delegate.WEB3FuturesOrderNotifyService;
import webbroker3.ifo.service.delegate.WEB3FuturesOrderNotifyUnitService;
import webbroker3.ifo.service.delegate.WEB3FuturesSettleContractInputService;
import webbroker3.ifo.service.delegate.WEB3FuturesSettleContractOrderService;
import webbroker3.ifo.service.delegate.WEB3IfoBalanceReferenceService;
import webbroker3.ifo.service.delegate.WEB3IfoChangeCancelOrderAcceptService;
import webbroker3.ifo.service.delegate.WEB3IfoChangeCancelOrderAcceptUnitService;
import webbroker3.ifo.service.delegate.WEB3IfoCloseNotifyService;
import webbroker3.ifo.service.delegate.WEB3IfoCloseNotifyUnitService;
import webbroker3.ifo.service.delegate.WEB3IfoExecuteEndNotifyService;
import webbroker3.ifo.service.delegate.WEB3IfoExecuteEndNotifyUnitService;
import webbroker3.ifo.service.delegate.WEB3IfoExecutedMailSendService;
import webbroker3.ifo.service.delegate.WEB3IfoFrontOrderService;
import webbroker3.ifo.service.delegate.WEB3IfoOrderAcceptService;
import webbroker3.ifo.service.delegate.WEB3IfoOrderAcceptUnitService;
import webbroker3.ifo.service.delegate.WEB3OptionCancelOrderService;
import webbroker3.ifo.service.delegate.WEB3OptionChangeCancelNotifyService;
import webbroker3.ifo.service.delegate.WEB3OptionChangeCancelNotifyUnitService;
import webbroker3.ifo.service.delegate.WEB3OptionChangeClosingContractInputService;
import webbroker3.ifo.service.delegate.WEB3OptionChangeClosingContractService;
import webbroker3.ifo.service.delegate.WEB3OptionChangeOpenContractInputService;
import webbroker3.ifo.service.delegate.WEB3OptionChangeOpenContractService;
import webbroker3.ifo.service.delegate.WEB3OptionContractInquiryService;
import webbroker3.ifo.service.delegate.WEB3OptionIndividualSettleContractListService;
import webbroker3.ifo.service.delegate.WEB3OptionOpenContractInputService;
import webbroker3.ifo.service.delegate.WEB3OptionOpenContractOrderService;
import webbroker3.ifo.service.delegate.WEB3OptionOrderExecNotifyService;
import webbroker3.ifo.service.delegate.WEB3OptionOrderExecNotifyUnitService;
import webbroker3.ifo.service.delegate.WEB3OptionOrderExecutedInquiryService;
import webbroker3.ifo.service.delegate.WEB3OptionSettleContractInputService;
import webbroker3.ifo.service.delegate.WEB3OptionSettleContractOrderService;
import webbroker3.ifo.service.delegate.WEB3OptionsOrderNotifyService;
import webbroker3.ifo.service.delegate.WEB3OptionsOrderNotifyUnitService;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesCancelOrderServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesChangeCancelNotifyServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesChangeCancelNotifyUnitServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesChangeClosingContractInputServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesChangeClosingContractServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesChangeOpenContractInputServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesChangeOpenContractServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesContractInquiryServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesExecuteReferenceServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesIndividualSettleContractListServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesOpenContractInputServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesOpenContractServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesOrderExecNotifyServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesOrderExecNotifyUnitServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesOrderNotifyServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesOrderNotifyUnitServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesSettleContractInputServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesSettleContractOrderServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3IfoBalanceReferenceServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3IfoChangeCancelOrderAcceptServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3IfoChangeCancelOrderAcceptUnitServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3IfoCloseNotifyServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3IfoCloseNotifyUnitServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3IfoExecuteEndNotifyServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3IfoExecuteEndNotifyUnitServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3IfoExecutedMailSendServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3IfoOrderAcceptServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3IfoOrderAcceptUnitServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionCancelOrderServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionChangeCancelNotifyServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionChangeCancelNotifyUnitServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionChangeClosingContractInputServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionChangeClosingContractServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionChangeOpenContractInputServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionChangeOpenContractServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionContractInquiryServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionIndividualSettleContractListServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionOpenContractInputServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionOpenContractOrderServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionOrderExecNotifyServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionOrderExecNotifyUnitServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionOrderExecutedInquiryServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionSettleContractInputServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionSettleContractOrderServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionsOrderNotifyServiceImpl;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionsOrderNotifyUnitServiceImpl;
import webbroker3.mqgateway.WEB3MQGatewayInterceptor;


/**
 * Webbroker3-Ifo �v���O�C���N���X�B
 *
 * @@author �����(���u)
 * @@version 1.0
 */
public final class WEB3IfoAppPlugin extends Plugin
{
    /**
     * ���O���[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoAppPlugin.class);

    /**
     * �R���X�g���N�^�B
     */
    public WEB3IfoAppPlugin()
    {
        String METHOD_NAME = "WEB3IfoAppPlugin()";
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

        plug(WEB3IfoAppPlugin.class);

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
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        // �g���g�����U�N�V�����E�}�l�[�W���[��
        // �I�[�o�[���C�h���\�b�h���������ߊg��������W���[���N���X���쐬��
        // �g��������W���[���N���X���Őݒ�
        try
        {
            l_finApp.uninstallTradingModule("xb-ifo-pdbt");
        }
        catch (NotInstalledException l_ex)
        {
            log.debug(l_ex.getMessage());
        }

        try
        {
            log.debug("Installing TradingModule : web3-xbifo");
            l_finApp.installTradingModule(new WEB3IfoTradingModule());
            log.debug("Installed TradingModule : web3-xbifo");
        }
        catch (AlreadyInstalledException l_ex)
        {
            log.debug(l_ex.getMessage());
        }

        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

        // �g���v���_�N�g�E�}�l�[�W���[
        l_tradingModule.overrideProductManager(new WEB3IfoProductManagerImpl());

        // �v�Z�T�[�r�X�N���X
        l_tradingModule.overrideBizLogicProvider(new WEB3IfoBizLogicProvider());

        // �g�������}�l�[�W��
        l_tradingModule.overrideOrderManager(new WEB3FuturesOrderManagerImpl());

        // �|�W�V�����}�l�[�W��
        l_tradingModule.overridePositionManager(new WEB3IfoPositionManagerImpl());

        // ���������R���ʃ`�F�b�N
        new WEB3IfoOrderManagerReusableValidations().register();

        // Adapter�̓o�^ --------------------
        // �s�ꃊ�N�G�X�g���M�T�[�r�X
        log.debug("XBIFO: �s��Adapter�̓o�^");
        WEB3IfoMarketAdaptorAppPlugin.plug();

        // DatabaseExtensions �̃v���O�C������ ----------------------
        WEB3IfoMasterDatabaseExtensions.plug();
        WEB3IfoSessionDatabaseExtensions.plug();

        //---------------------- 1 Service �̓o�^���� ----------------------
        //---�敨OP���ʃT�[�r�X---
        //�敨OP������t�T�[�r�X
        Services.registerService(
            WEB3IfoOrderAcceptService.class,
            new WEB3IfoOrderAcceptServiceImpl());

        //�敨OP������t�ꌏ�T�[�r�X
        Services.registerService(
            WEB3IfoOrderAcceptUnitService.class,
            new WEB3IfoOrderAcceptUnitServiceImpl());

        //�敨OP���������t�T�[�r�X
        Services.registerService(
            WEB3IfoChangeCancelOrderAcceptService.class,
            new WEB3IfoChangeCancelOrderAcceptServiceImpl());

        //�敨OP���������t�ꌏ�T�[�r�X
        Services.registerService(
            WEB3IfoChangeCancelOrderAcceptUnitService.class,
            new WEB3IfoChangeCancelOrderAcceptUnitServiceImpl());

        //�敨OP�����ʒm�T�[�r�X
        Services.registerService(
            WEB3IfoCloseNotifyService.class,
            new WEB3IfoCloseNotifyServiceImpl());

        //�敨OP�����ʒm�ꌏ�T�[�r�X
        Services.registerService(
            WEB3IfoCloseNotifyUnitService.class,
            new WEB3IfoCloseNotifyUnitServiceImpl());

        //�敨OP�o���I���ʒm�T�[�r�X
        Services.registerService(
            WEB3IfoExecuteEndNotifyService.class,
            new WEB3IfoExecuteEndNotifyServiceImpl());

        //�敨OP�o���I���ʒm�ꌏ�T�[�r�X
        Services.registerService(
            WEB3IfoExecuteEndNotifyUnitService.class,
            new WEB3IfoExecuteEndNotifyUnitServiceImpl());

        //�敨OP��胁�[�����M�T�[�r�X
        Services.registerService(
            WEB3IfoExecutedMailSendService.class,
            new WEB3IfoExecutedMailSendServiceImpl());

        //�敨OP�c���Ɖ�T�[�r�X
        Services.registerService(
            WEB3IfoBalanceReferenceService.class,
            new WEB3IfoBalanceReferenceServiceImpl());

        //�敨OP�����T�[�r�X
        Services.registerService(
            WEB3IfoFrontOrderService.class,
            new WEB3IfoFrontOrderServiceImpl());

        //---OP�T�[�r�X---
        //OP�o���ʒm�T�[�r�X
        Services.registerService(
            WEB3OptionOrderExecNotifyService.class,
            new WEB3OptionOrderExecNotifyServiceImpl());

        //OP�o���ʒm�ꌏ�T�[�r�X
        Services.registerService(
            WEB3OptionOrderExecNotifyUnitService.class,
            new WEB3OptionOrderExecNotifyUnitServiceImpl());

        //OP��������ʒm�T�[�r�X
        Services.registerService(
            WEB3OptionChangeCancelNotifyService.class,
            new WEB3OptionChangeCancelNotifyServiceImpl());

        //OP��������ʒm�ꌏ�T�[�r�X
        Services.registerService(
            WEB3OptionChangeCancelNotifyUnitService.class,
            new WEB3OptionChangeCancelNotifyUnitServiceImpl());

        //OP�����ʒm�T�[�r�X
        Services.registerService(
            WEB3OptionsOrderNotifyService.class,
            new WEB3OptionsOrderNotifyServiceImpl());

        //OP�����ʒm�ꌏ�T�[�r�X
        Services.registerService(
            WEB3OptionsOrderNotifyUnitService.class,
            new WEB3OptionsOrderNotifyUnitServiceImpl());

        //OP�V�K�����̓T�[�r�X
        Services.registerService(
            WEB3OptionOpenContractInputService.class,
            new WEB3OptionOpenContractInputServiceImpl());

        //OP�V�K�������T�[�r�X
        Services.registerService(
            WEB3OptionOpenContractOrderService.class,
            new WEB3OptionOpenContractOrderServiceImpl());

        //OP�����V�K�����̓T�[�r�X
        Services.registerService(
            WEB3OptionChangeOpenContractInputService.class,
            new WEB3OptionChangeOpenContractInputServiceImpl());

        //OP�����V�K���T�[�r�X
        Services.registerService(
            WEB3OptionChangeOpenContractService.class,
            new WEB3OptionChangeOpenContractServiceImpl());

        //OP�ʕԍψꗗ�\���T�[�r�X
        Services.registerService(
            WEB3OptionIndividualSettleContractListService.class,
            new WEB3OptionIndividualSettleContractListServiceImpl());

        //OP�ԍϓ��̓T�[�r�X
        Services.registerService(
            WEB3OptionSettleContractInputService.class,
            new WEB3OptionSettleContractInputServiceImpl());

        //OP�ԍϒ����T�[�r�X
        Services.registerService(
            WEB3OptionSettleContractOrderService.class,
            new WEB3OptionSettleContractOrderServiceImpl());

        //OP�����ԍϓ��̓T�[�r�X
        Services.registerService(
            WEB3OptionChangeClosingContractInputService.class,
            new WEB3OptionChangeClosingContractInputServiceImpl());

        //OP�����ԍσT�[�r�X
        Services.registerService(
            WEB3OptionChangeClosingContractService.class,
            new WEB3OptionChangeClosingContractServiceImpl());

        //OP��������T�[�r�X
        Services.registerService(
            WEB3OptionCancelOrderService.class,
            new WEB3OptionCancelOrderServiceImpl());

        //OP�������Ɖ�T�[�r�X
        Services.registerService(
            WEB3OptionOrderExecutedInquiryService.class,
            new WEB3OptionOrderExecutedInquiryServiceImpl());

        //OP���ʏƉ�T�[�r�X
        Services.registerService(
            WEB3OptionContractInquiryService.class,
            new WEB3OptionContractInquiryServiceImpl());

        //---�敨�T�[�r�X---
        //�敨�o���ʒm�T�[�r�X
        Services.registerService(
            WEB3FuturesOrderExecNotifyService.class,
            new WEB3FuturesOrderExecNotifyServiceImpl());

        //�敨�o���ʒm�ꌏ�T�[�r�X
        Services.registerService(
            WEB3FuturesOrderExecNotifyUnitService.class,
            new WEB3FuturesOrderExecNotifyUnitServiceImpl());

        //�敨��������ʒm�T�[�r�X
        Services.registerService(
            WEB3FuturesChangeCancelNotifyService.class,
            new WEB3FuturesChangeCancelNotifyServiceImpl());

        //�敨��������ʒm�ꌏ�T�[�r�X
        Services.registerService(
            WEB3FuturesChangeCancelNotifyUnitService.class,
            new WEB3FuturesChangeCancelNotifyUnitServiceImpl());

        //�敨�����ʒm�T�[�r�X
        Services.registerService(
            WEB3FuturesOrderNotifyService.class,
            new WEB3FuturesOrderNotifyServiceImpl());

        //�敨�����ʒm�ꌏ�T�[�r�X
        Services.registerService(
            WEB3FuturesOrderNotifyUnitService.class,
            new WEB3FuturesOrderNotifyUnitServiceImpl());

        //�敨�V�K�����̓T�[�r�X
        Services.registerService(
            WEB3FuturesOpenContractInputService.class,
            new WEB3FuturesOpenContractInputServiceImpl());

        //�敨�V�K�������T�[�r�X
        Services.registerService(
            WEB3FuturesOpenContractService.class,
            new WEB3FuturesOpenContractServiceImpl());

        //�敨�����V�K�����̓T�[�r�X
        Services.registerService(
            WEB3FuturesChangeOpenContractInputService.class,
            new WEB3FuturesChangeOpenContractInputServiceImpl());

        //�敨�����V�K���T�[�r�X
        Services.registerService(
            WEB3FuturesChangeOpenContractService.class,
            new WEB3FuturesChangeOpenContractServiceImpl());

        //�敨�ʕԍψꗗ�\���T�[�r�X
        Services.registerService(
            WEB3FuturesIndividualSettleContractListService.class,
            new WEB3FuturesIndividualSettleContractListServiceImpl());

        //�敨�ԍϓ��̓T�[�r�X
        Services.registerService(
            WEB3FuturesSettleContractInputService.class,
            new WEB3FuturesSettleContractInputServiceImpl());

        //�敨�ԍϒ����T�[�r�X
        Services.registerService(
            WEB3FuturesSettleContractOrderService.class,
            new WEB3FuturesSettleContractOrderServiceImpl());

        //�敨�����ԍϓ��̓T�[�r�X
        Services.registerService(
            WEB3FuturesChangeClosingContractInputService.class,
            new WEB3FuturesChangeClosingContractInputServiceImpl());

        //�敨�����ԍσT�[�r�X
        Services.registerService(
            WEB3FuturesChangeClosingContractService.class,
            new WEB3FuturesChangeClosingContractServiceImpl());

        //�敨��������T�[�r�X
        Services.registerService(
            WEB3FuturesCancelOrderService.class,
            new WEB3FuturesCancelOrderServiceImpl());

        //�敨�������Ɖ�T�[�r�X
        Services.registerService(
            WEB3FuturesExecuteReferenceService.class,
            new WEB3FuturesExecuteReferenceServiceImpl());

        //�敨���ʏƉ�T�[�r�X
        Services.registerService(
            WEB3FuturesContractInquiryService.class,
            new WEB3FuturesContractInquiryServiceImpl());

        //---------------------- 2 ���O�o�͐ݒ菈�� ----------------------
        //(Service.execute()�Ăяo���O��ɏ����J�n�����Ə����I�����������O�o�͂���)

        //---�敨OP���ʃT�[�r�X---
        //�敨OP������t�T�[�r�X
        Services.addInterceptor(
            WEB3IfoOrderAcceptService.class,
            new WEB3LogSysTimeInterceptor());

        //�敨OP������t�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3IfoOrderAcceptUnitService.class,
            new WEB3LogSysTimeInterceptor());

        //�敨OP���������t�T�[�r�X
        Services.addInterceptor(
            WEB3IfoChangeCancelOrderAcceptService.class,
            new WEB3LogSysTimeInterceptor());

        //�敨OP���������t�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3IfoChangeCancelOrderAcceptUnitService.class,
            new WEB3LogSysTimeInterceptor());

        //�敨OP�����ʒm�T�[�r�X
        Services.addInterceptor(
            WEB3IfoCloseNotifyService.class,
            new WEB3LogSysTimeInterceptor());

        //�敨OP�����ʒm�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3IfoCloseNotifyUnitService.class,
            new WEB3LogSysTimeInterceptor());

        //�敨OP�o���I���ʒm�T�[�r�X
        Services.addInterceptor(
            WEB3IfoExecuteEndNotifyService.class,
            new WEB3LogSysTimeInterceptor());

        //�敨OP�o���I���ʒm�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3IfoExecuteEndNotifyUnitService.class,
            new WEB3LogSysTimeInterceptor());

        //�敨OP��胁�[�����M�T�[�r�X
        Services.addInterceptor(
            WEB3IfoExecutedMailSendService.class,
            new WEB3LogSysTimeInterceptor());

        //�敨OP�c���Ɖ�T�[�r�X
        Services.addInterceptor(
            WEB3IfoBalanceReferenceService.class,
            new WEB3LogSysTimeInterceptor());

        //---OP�T�[�r�X---
        //OP�o���ʒm�T�[�r�X
        Services.addInterceptor(
            WEB3OptionOrderExecNotifyService.class,
            new WEB3LogSysTimeInterceptor());

        //OP�o���ʒm�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3OptionOrderExecNotifyUnitService.class,
            new WEB3LogSysTimeInterceptor());

        //OP��������ʒm�T�[�r�X
        Services.addInterceptor(
            WEB3OptionChangeCancelNotifyService.class,
            new WEB3LogSysTimeInterceptor());

        //OP��������ʒm�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3OptionChangeCancelNotifyUnitService.class,
            new WEB3LogSysTimeInterceptor());

        //OP�����ʒm�T�[�r�X
        Services.addInterceptor(
            WEB3OptionsOrderNotifyService.class,
            new WEB3LogSysTimeInterceptor());

        //OP�����ʒm�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3OptionsOrderNotifyUnitService.class,
            new WEB3LogSysTimeInterceptor());

        //OP�V�K�����̓T�[�r�X
        Services.addInterceptor(
            WEB3OptionOpenContractInputService.class,
            new WEB3LogSysTimeInterceptor());

        //OP�V�K�������T�[�r�X
        Services.addInterceptor(
            WEB3OptionOpenContractOrderService.class,
            new WEB3LogSysTimeInterceptor());

        //OP�����V�K�����̓T�[�r�X
        Services.addInterceptor(
            WEB3OptionChangeOpenContractInputService.class,
            new WEB3LogSysTimeInterceptor());

        //OP�����V�K���T�[�r�X
        Services.addInterceptor(
            WEB3OptionChangeOpenContractService.class,
            new WEB3LogSysTimeInterceptor());

        //OP�ʕԍψꗗ�\���T�[�r�X
        Services.addInterceptor(
            WEB3OptionIndividualSettleContractListService.class,
            new WEB3LogSysTimeInterceptor());

        //OP�ԍϓ��̓T�[�r�X
        Services.addInterceptor(
            WEB3OptionSettleContractInputService.class,
            new WEB3LogSysTimeInterceptor());

        //OP�ԍϒ����T�[�r�X
        Services.addInterceptor(
            WEB3OptionSettleContractOrderService.class,
            new WEB3LogSysTimeInterceptor());

        //OP�����ԍϓ��̓T�[�r�X
        Services.addInterceptor(
            WEB3OptionChangeClosingContractInputService.class,
            new WEB3LogSysTimeInterceptor());

        //OP�����ԍσT�[�r�X
        Services.addInterceptor(
            WEB3OptionChangeClosingContractService.class,
            new WEB3LogSysTimeInterceptor());

        //OP��������T�[�r�X
        Services.addInterceptor(
            WEB3OptionCancelOrderService.class,
            new WEB3LogSysTimeInterceptor());

        //OP�������Ɖ�T�[�r�X
        Services.addInterceptor(
            WEB3OptionOrderExecutedInquiryService.class,
            new WEB3LogSysTimeInterceptor());

        //OP���ʏƉ�T�[�r�X
        Services.addInterceptor(
            WEB3OptionContractInquiryService.class,
            new WEB3LogSysTimeInterceptor());

        //---�敨�T�[�r�X---
        //�敨�o���ʒm�T�[�r�X
        Services.addInterceptor(
            WEB3FuturesOrderExecNotifyService.class,
            new WEB3LogSysTimeInterceptor());

        //�敨�o���ʒm�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3FuturesOrderExecNotifyUnitService.class,
            new WEB3LogSysTimeInterceptor());

        //�敨��������ʒm�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3FuturesChangeCancelNotifyUnitService.class,
            new WEB3LogSysTimeInterceptor());

        //�敨�����ʒm�T�[�r�X
        Services.addInterceptor(
            WEB3FuturesOrderNotifyService.class,
            new WEB3LogSysTimeInterceptor());

        //�敨�����ʒm�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3FuturesOrderNotifyUnitService.class,
            new WEB3LogSysTimeInterceptor());

        //�敨�V�K�����̓T�[�r�X
        Services.addInterceptor(
            WEB3FuturesOpenContractInputService.class,
            new WEB3LogSysTimeInterceptor());

        //�敨�V�K�������T�[�r�X
        Services.addInterceptor(
            WEB3FuturesOpenContractService.class,
            new WEB3LogSysTimeInterceptor());

        //�敨�����V�K�����̓T�[�r�X
        Services.addInterceptor(
            WEB3FuturesChangeOpenContractInputService.class,
            new WEB3LogSysTimeInterceptor());

        //�敨�����V�K���T�[�r�X
        Services.addInterceptor(
            WEB3FuturesChangeOpenContractService.class,
            new WEB3LogSysTimeInterceptor());

        //�敨�ʕԍψꗗ�\���T�[�r�X
        Services.addInterceptor(
            WEB3FuturesIndividualSettleContractListService.class,
            new WEB3LogSysTimeInterceptor());

        //�敨�ԍϓ��̓T�[�r�X
        Services.addInterceptor(
            WEB3FuturesSettleContractInputService.class,
            new WEB3LogSysTimeInterceptor());

        //�敨�ԍϒ����T�[�r�X
        Services.addInterceptor(
            WEB3FuturesSettleContractOrderService.class,
            new WEB3LogSysTimeInterceptor());

        //�敨�����ԍϓ��̓T�[�r�X
        Services.addInterceptor(
            WEB3FuturesChangeClosingContractInputService.class,
            new WEB3LogSysTimeInterceptor());

        //�敨�����ԍσT�[�r�X
        Services.addInterceptor(
            WEB3FuturesChangeClosingContractService.class,
            new WEB3LogSysTimeInterceptor());

        //�敨��������T�[�r�X
        Services.addInterceptor(
            WEB3FuturesCancelOrderService.class,
            new WEB3LogSysTimeInterceptor());

        //�敨�������Ɖ�T�[�r�X
        Services.addInterceptor(
            WEB3FuturesExecuteReferenceService.class,
            new WEB3LogSysTimeInterceptor());

        //�敨���ʏƉ�T�[�r�X
        Services.addInterceptor(
            WEB3FuturesContractInquiryService.class,
            new WEB3LogSysTimeInterceptor());

        //---------------------- 3 Service �� ServiceInterceptor ��ݒ肷�� ----------------------
        //---�敨OP���ʃT�[�r�X---

        //�敨OP������t�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3IfoOrderAcceptUnitService.class,
            new WEB3IfoOrderAcceptUnitServiceInterceptor());

        //�敨OP���������t�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3IfoChangeCancelOrderAcceptUnitService.class,
            new WEB3IfoChangeCancelOrderAcceptUnitServiceInterceptor());

        //�敨OP�����ʒm�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3IfoCloseNotifyUnitService.class,
            new WEB3IfoCloseNotifyUnitServiceInterceptor());

        //�敨OP�o���I���ʒm�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3IfoExecuteEndNotifyUnitService.class,
            new WEB3IfoExecuteEndNotifyUnitServiceInterceptor());

        //�敨OP�c���Ɖ�T�[�r�X
        Services.addInterceptor(
            WEB3IfoBalanceReferenceService.class,
            new WEB3IfoBalanceReferenceServiceInterceptor());

        //---OP�T�[�r�X---
        //OP�o���ʒm�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3OptionOrderExecNotifyUnitService.class,
            new WEB3OptionOrderExecutedNotifyUnitServiceInterceptor());

        //OP��������ʒm�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3OptionChangeCancelNotifyUnitService.class,
            new WEB3OptionChangeCancelNotifyUnitServiceInterceptor());

        //OP�����ʒm�T�[�r�X
        Services.addInterceptor(
            WEB3OptionsOrderNotifyUnitService.class,
            new WEB3OptionsOrderNotifyUnitServiceInterceptor());

        //OP�V�K�����̓T�[�r�X
        Services.addInterceptor(
            WEB3OptionOpenContractInputService.class,
            new WEB3OptionOpenContractInputServiceInterceptor());

        //OP�V�K�������T�[�r�X
        Services.addInterceptor(
            WEB3OptionOpenContractOrderService.class,
            new WEB3OptionOpenContractOrderServiceInterceptor());

        //OP�����V�K�����̓T�[�r�X
        Services.addInterceptor(
            WEB3OptionChangeOpenContractInputService.class,
            new WEB3OptionChangeOpenContractInputServiceInterceptor());

        //OP�����V�K���T�[�r�X
        Services.addInterceptor(
            WEB3OptionChangeOpenContractService.class,
            new WEB3OptionChangeOpenContractServiceInterceptor());

        //OP�ʕԍψꗗ�\���T�[�r�X
        Services.addInterceptor(
            WEB3OptionIndividualSettleContractListService.class,
            new WEB3OptionIndividualSettleContractListServiceInterceptor());

        //OP�ԍϓ��̓T�[�r�X
        Services.addInterceptor(
            WEB3OptionSettleContractInputService.class,
            new WEB3OptionSettleContractInputServiceInterceptor());

        //OP�ԍϒ����T�[�r�X
        Services.addInterceptor(
            WEB3OptionSettleContractOrderService.class,
            new WEB3OptionSettleContractOrderServiceInterceptor());

        //OP�����ԍϓ��̓T�[�r�X
        Services.addInterceptor(
            WEB3OptionChangeClosingContractInputService.class,
            new WEB3OptionChangeClosingContractInputServiceInterceptor());

        //OP�����ԍσT�[�r�X
        Services.addInterceptor(
            WEB3OptionChangeClosingContractService.class,
            new WEB3OptionChangeClosingContractServiceInterceptor());

        //OP��������T�[�r�X
        Services.addInterceptor(
            WEB3OptionCancelOrderService.class,
            new WEB3OptionCancelOrderServiceInterceptor());

        //OP�������Ɖ�T�[�r�X
        Services.addInterceptor(
            WEB3OptionOrderExecutedInquiryService.class,
            new WEB3OptionOrderExecutedInquiryServiceInterceptor());

        //OP���ʏƉ�T�[�r�X
        Services.addInterceptor(
            WEB3OptionContractInquiryService.class,
            new WEB3OptionContractInquiryServiceInterceptor());

        //---�敨�T�[�r�X---
        //�敨�o���ʒm�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3FuturesOrderExecNotifyUnitService.class,
            new WEB3FuturesOrderExecNotifyUnitServiceInterceptor());

        //�敨��������ʒm�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3FuturesChangeCancelNotifyUnitService.class,
            new WEB3FuturesChangeCancelNotifyUnitServiceInterceptor());

        //�敨�����ʒm�T�[�r�X
        Services.addInterceptor(
            WEB3FuturesOrderNotifyUnitService.class,
            new WEB3FuturesOrderNotifyUnitServiceInterceptor());

        //�敨�V�K�����̓T�[�r�X
        Services.addInterceptor(
            WEB3FuturesOpenContractInputService.class,
            new WEB3FuturesOpenContractInputServiceInterceptor());

        //�敨�V�K�������T�[�r�X
        Services.addInterceptor(
            WEB3FuturesOpenContractService.class,
            new WEB3FuturesOpenContractServiceInterceptor());

        //�敨�����V�K�����̓T�[�r�X
        Services.addInterceptor(
            WEB3FuturesChangeOpenContractInputService.class,
            new WEB3FuturesChangeOpenContractInputServiceInterceptor());

        //�敨�����V�K���T�[�r�X
        Services.addInterceptor(
            WEB3FuturesChangeOpenContractService.class,
            new WEB3FuturesChangeOpenContractServiceInterceptor());

        //�敨�ʕԍψꗗ�\���T�[�r�X
        Services.addInterceptor(
            WEB3FuturesIndividualSettleContractListService.class,
            new WEB3FuturesIndividualSettleContractListServiceInterceptor());

        //�敨�ԍϓ��̓T�[�r�X
        Services.addInterceptor(
            WEB3FuturesSettleContractInputService.class,
            new WEB3FuturesSettleContractInputServiceInterceptor());

        //�敨�ԍϒ����T�[�r�X
        Services.addInterceptor(
            WEB3FuturesSettleContractOrderService.class,
            new WEB3FuturesSettleContractOrderServiceInterceptor());

        //�敨�����ԍϓ��̓T�[�r�X
        Services.addInterceptor(
            WEB3FuturesChangeClosingContractInputService.class,
            new WEB3FuturesChangeClosingContractInputServiceInterceptor());

        //�敨�����ԍσT�[�r�X
        Services.addInterceptor(
            WEB3FuturesChangeClosingContractService.class,
            new WEB3FuturesChangeClosingContractServiceInterceptor());

        //�敨��������T�[�r�X
        Services.addInterceptor(
            WEB3FuturesCancelOrderService.class,
            new WEB3FuturesCancelOrderServiceInterceptor());

        //�敨�������Ɖ�T�[�r�X
        Services.addInterceptor(
            WEB3FuturesExecuteReferenceService.class,
            new WEB3FuturesExecuteReferenceServiceInterceptor());

        //�敨���ʏƉ�T�[�r�X
        Services.addInterceptor(
            WEB3FuturesContractInquiryService.class,
            new WEB3FuturesContractInquiryServiceInterceptor());

        //---------------------- 4 Service �� Interceptor �ݒ菈��(�����g�����U�N�V�����ݒ�) ----------------------
        //---�敨OP���ʃT�[�r�X---
        //�敨OP������t�T�[�r�X
        Services.addInterceptor(
            WEB3IfoOrderAcceptService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //�敨OP������t�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3IfoOrderAcceptUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //�敨OP���������t�T�[�r�X
        Services.addInterceptor(
            WEB3IfoChangeCancelOrderAcceptService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //�敨OP���������t�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3IfoChangeCancelOrderAcceptUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //�敨OP�����ʒm�T�[�r�X
        Services.addInterceptor(
            WEB3IfoCloseNotifyService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //�敨OP�����ʒm�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3IfoCloseNotifyUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�敨OP�o���I���ʒm�T�[�r�X
        Services.addInterceptor(
            WEB3IfoExecuteEndNotifyService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //�敨OP�o���I���ʒm�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3IfoExecuteEndNotifyUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //�敨OP��胁�[�����M�T�[�r�X
        Services.addInterceptor(
            WEB3IfoExecutedMailSendService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //---OP�T�[�r�X---
        //OP�o���ʒm�T�[�r�X
        Services.addInterceptor(
            WEB3OptionOrderExecNotifyService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //OP�o���ʒm�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3OptionOrderExecNotifyUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //OP��������ʒm�T�[�r�X
        Services.addInterceptor(
            WEB3OptionChangeCancelNotifyService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //OP��������ʒm�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3OptionChangeCancelNotifyUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //OP�����ʒm�T�[�r�X
        Services.addInterceptor(
            WEB3OptionsOrderNotifyService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //OP�����ʒm�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3OptionsOrderNotifyUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //OP�V�K�������T�[�r�X
        Services.addInterceptor(
            WEB3OptionOpenContractOrderService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //OP�����V�K���T�[�r�X
        Services.addInterceptor(
            WEB3OptionChangeOpenContractService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //OP�ԍϒ����T�[�r�X
        Services.addInterceptor(
            WEB3OptionSettleContractOrderService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //OP�����ԍσT�[�r�X
        Services.addInterceptor(
            WEB3OptionChangeClosingContractService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //OP��������T�[�r�X
        Services.addInterceptor(
            WEB3OptionCancelOrderService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //---�敨�T�[�r�X---
        //�敨�o���ʒm�T�[�r�X
        Services.addInterceptor(
            WEB3FuturesOrderExecNotifyService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //�敨�o���ʒm�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3FuturesOrderExecNotifyUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�敨��������ʒm�T�[�r�X
        Services.addInterceptor(
            WEB3FuturesChangeCancelNotifyService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //�敨��������ʒm�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3FuturesChangeCancelNotifyUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�敨�����ʒm�T�[�r�X
        Services.addInterceptor(
            WEB3FuturesOrderNotifyService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //�敨�����ʒm�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3FuturesOrderNotifyUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //�敨�V�K�������T�[�r�X
        Services.addInterceptor(
            WEB3FuturesOpenContractService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //�敨�����V�K���T�[�r�X
        Services.addInterceptor(
            WEB3FuturesChangeOpenContractService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //�敨�ԍϒ����T�[�r�X
        Services.addInterceptor(
            WEB3FuturesSettleContractOrderService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //�敨�����ԍσT�[�r�X
        Services.addInterceptor(
            WEB3FuturesChangeClosingContractService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //�敨��������T�[�r�X
        Services.addInterceptor(
            WEB3FuturesCancelOrderService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //----------------------  MQ-Gateway�C���^�Z�v�^�̐ݒ�  ----------------------
        //---OP�T�[�r�X---
        //OP�V�K�������T�[�r�X
        Services.addInterceptor(
            WEB3OptionOpenContractOrderService.class,
            new WEB3MQGatewayInterceptor());

        //OP�����V�K�������T�[�r�X
        Services.addInterceptor(
            WEB3OptionChangeOpenContractService.class,
            new WEB3MQGatewayInterceptor());

        //OP�ԍϒ����T�[�r�X
        Services.addInterceptor(
            WEB3OptionSettleContractOrderService.class,
            new WEB3MQGatewayInterceptor());

        //OP�����ԍϒ����T�[�r�X
        Services.addInterceptor(
            WEB3OptionChangeClosingContractService.class,
            new WEB3MQGatewayInterceptor());

        //OP��������T�[�r�X
        Services.addInterceptor(
            WEB3OptionCancelOrderService.class,
            new WEB3MQGatewayInterceptor());

        //---�敨�T�[�r�X---
        //�敨�V�K�������T�[�r�X
        Services.addInterceptor(
            WEB3FuturesOpenContractService.class,
             new WEB3MQGatewayInterceptor());

        //�敨�����V�K�������T�[�r�X
        Services.addInterceptor(
            WEB3FuturesChangeOpenContractService.class,
            new WEB3MQGatewayInterceptor());

        //�敨�ԍϒ����T�[�r�X
        Services.addInterceptor(
             WEB3FuturesSettleContractOrderService.class,
            new WEB3MQGatewayInterceptor());

        //�敨�����ԍϒ����T�[�r�X
        Services.addInterceptor(
            WEB3FuturesChangeClosingContractService.class,
            new WEB3MQGatewayInterceptor());

        //�敨��������T�[�r�X
        Services.addInterceptor(
            WEB3FuturesCancelOrderService.class,
            new WEB3MQGatewayInterceptor());

        //---------------------- 5 Message �̓o�^���� ----------------------
        //---�敨OP���ʃT�[�r�X---
        //�敨OP������t���N�G�X�g
        regClass(WEB3IfoOrderAcceptRequest.class);
        //�敨OP������t���X�|���X
        regClass(WEB3IfoOrderAcceptResponse.class);

        //�敨OP���������t���N�G�X�g
        regClass(WEB3IfoChangeCancelAcceptRequest.class);
        //�敨OP���������t���X�|���X
        regClass(WEB3IfoChangeCancelAcceptResponse.class);

        //�敨OP�����ʒm���N�G�X�g�N���X
        regClass(WEB3IfoCloseOrderRequest.class);
        //�敨OP�����ʒm���X�|���X�N���X
        regClass(WEB3IfoCloseOrderResponse.class);

        //�敨OP�o���I���ʒm���N�G�X�g�N���X
        regClass(WEB3IfoExecEndNotifyRequest.class);
        //�敨OP�o���I���ʒm���X�|���X�N���X
        regClass(WEB3IfoExecEndNotifyResponse.class);

        //�敨OP�c���Ɖ�N�G�X�g�N���X
        regClass(WEB3FuturesOptionsBalanceReferenceRequest.class);
        //�敨OP�c���Ɖ�X�|���X�N���X
        regClass(WEB3FuturesOptionsBalanceReferenceResponse.class);

        //�敨OP�c���Ɖ�c�����v���N�G�X�g�N���X
        regClass(WEB3FuturesOptionsBalanceReferenceTotalRequest.class);
        //�敨OP�c���Ɖ�c�����v���X�|���X�N���X
        regClass(WEB3FuturesOptionsBalanceReferenceTotalResponse.class);

        //---OP�T�[�r�X---
        //�����w��OP�o���ʒm���N�G�X�g
        regClass(WEB3OptionOrderExecNotifyRequest.class);
        //�����w��OP�o���ʒm���X�|���X
        regClass(WEB3OptionOrderExecNotifyResponse.class);

        //�����w��OP��������ʒm���N�G�X�g
        regClass(WEB3OptionsChangeCancelNotifyRequest.class);
        //�����w��OP��������ʒm���X�|���X
        regClass(WEB3OptionsChangeCancelNotifyResponse.class);

        //�����w��OP�����ʒm���N�G�X�g
        regClass(WEB3OptionsOrderNotifyRequest.class);
        //�����w��OP�����ʒm���X�|���X
        regClass(WEB3OptionsOrderNotifyResponse.class);

        //�����w��OP���ʓ��̓��N�G�X
        regClass(WEB3OptionsCommonRequest.class);
        //�����w��OP���ʓ��̓��X�|���X
        regClass(WEB3OptionsCommonResponse.class);

        //�����w��OP�V�K�����������I����ʃ��N�G�X�g
        regClass(WEB3OptionsProductSelectRequest.class);
        //�����w��OP�V�K�����������I����ʃ��X�|���X
        regClass(WEB3OptionsProductSelectResponse.class);

        //�����w��OP�V�K���������͉�ʃ��N�G�X�g
        regClass(WEB3OptionsOpenMarginInputRequest.class);
        //�����w��OP�V�K���������͉�ʃ��X�|���X
        regClass(WEB3OptionsOpenMarginInputResponse.class);

        //�����w��OP�V�K�������m�F���N�G�X�g
        regClass(WEB3OptionsOpenMarginConfirmRequest.class);
        //�����w��OP�V�K�������m�F���X�|���X
        regClass(WEB3OptionsOpenMarginConfirmResponse.class);

        //�����w��OP�V�K�������������N�G�X�g
        regClass(WEB3OptionsOpenMarginCompleteRequest.class);
        //�����w��OP�V�K�������������X�|���X
        regClass(WEB3OptionsOpenMarginCompleteResponse.class);

        //�����w��OP�����V�K�����͉�ʃ��N�G�X�g
        regClass(WEB3OptionsOpenMarginChangeInputRequest.class);
        //�����w��OP�����V�K�����͉�ʃ��X�|���X
        regClass(WEB3OptionsOpenMarginChangeInputResponse.class);

        //�����w��OP�����V�K���m�F���N�G�X�g
        regClass(WEB3OptionsOpenMarginChangeConfirmRequest.class);
        //�����w��OP�����V�K���m�F���X�|���X
        regClass(WEB3OptionsOpenMarginChangeConfirmResponse.class);

        //�����w��OP�����V�K���������N�G�X�g
        regClass(WEB3OptionsOpenMarginChangeCompleteRequest.class);
        //�����w��OP�����V�K���������X�|���X
        regClass(WEB3OptionsOpenMarginChangeCompleteResponse.class);

        //�����w��OP�ʕԍψꗗ��ʕ\�����N�G�X�g
        regClass(WEB3OptionsIndividualCloseMarginListRequest.class);
        //�����w��OP�ʕԍψꗗ��ʕ\�����X�|���X
        regClass(WEB3OptionsIndividualCloseMarginListResponse.class);

        //�����w��OP�ԍψꗗ��ʕ\�����N�G�X�g
        regClass(WEB3OptionsCloseMarginListRequest.class);
        //�����w��OP�ԍψꗗ��ʕ\�����X�|���X
        regClass(WEB3OptionsCloseMarginListResponse.class);

        //�����w��OP�ԍϓ��͉�ʃ��N�G�X�g
        regClass(WEB3OptionsCloseMarginInputRequest.class);
        //�����w��OP�ԍϓ��͉�ʃ��X�|���X
        regClass(WEB3OptionsCloseMarginInputResponse.class);

        //�����w��OP�ԍϊm�F���N�G�X�g
        regClass(WEB3OptionsCloseMarginConfirmRequest.class);
        //�����w��OP�ԍϊm�F���X�|���X
        regClass(WEB3OptionsCloseMarginConfirmResponse.class);

        //�����w��OP�ԍϊ������N�G�X�g
        regClass(WEB3OptionsCloseMarginCompleteRequest.class);
        //�����w��OP�ԍϊ������X�|���X
        regClass(WEB3OptionsCloseMarginCompleteResponse.class);

        //�����w��OP��������m�F���N�G�X�g
        regClass(WEB3OptionsCancelConfirmRequest.class);
        //�����w��OP��������m�F���X�|���X
        regClass(WEB3OptionsCancelConfirmResponse.class);

        //�����w��OP��������������N�G�X�g
        regClass(WEB3OptionsCancelCompleteRequest.class);
        //�����w��OP��������������X�|���X
        regClass(WEB3OptionsCancelCompleteResponse.class);

        //�����w��OP�����ԍϊ������N�G�X�g
        regClass(WEB3OptionsCloseMarginChangeCompleteRequest.class);
        //�����w��OP�����ԍϊ������X�|���X
        regClass(WEB3OptionsCloseMarginChangeCompleteResponse.class);

        //�����w��OP�����ԍϊm�F���N�G�X�g
        regClass(WEB3OptionsCloseMarginChangeConfirmRequest.class);
        //�����w��OP�����ԍϊm�F���X�|���X
        regClass(WEB3OptionsCloseMarginChangeConfirmResponse.class);

        //�����w��OP�����ԍϓ��͉�ʃ��N�G�X�g
        regClass(WEB3OptionsCloseMarginChangeInputRequest.class);
        //�����w��OP�����ԍϓ��͉�ʃ��X�|���X
        regClass(WEB3OptionsCloseMarginChangeInputResponse.class);

        //�����w��OP�������Ɖ�N�G�X�g
        regClass(WEB3OptionsExecuteReferenceRequest.class);
        //�����w��OP�������Ɖ�X�|���X
        regClass(WEB3OptionsExecuteReferenceResponse.class);

        //�����w��OP�����������ڍ׃��N�G�X�g
        regClass(WEB3OptionsExecuteDetailsRequest.class);
        //�����w��OP�����������ڍ׃��X�|���X
        regClass(WEB3OptionsExecuteDetailsResponse.class);

        //�����w��OP���������Ɖ�N�G�X�g
        regClass(WEB3OptionsOrderHistoryRequest.class);
        //�����w��OP���������Ɖ�X�|���X
        regClass(WEB3OptionsOrderHistoryResponse.class);

        //�����w��OP�ԍό��ʈꗗ���N�G�X�g
        regClass(WEB3OptionsCloseMarginContractListRequest.class);
        //�����w��OP�ԍό��ʈꗗ���X�|���X
        regClass(WEB3OptionsCloseMarginContractListResponse.class);

        //�����w��OP���ʏƉ�N�G�X�g
        regClass(WEB3OptionsContractReferenceRequest.class);
        //�����w��OP���ʏƉ�X�|���X
        regClass(WEB3OptionsContractReferenceResponse.class);

        //---�敨�T�[�r�X---
        //�����w���敨�o���ʒm���N�G�X�g
        regClass(WEB3FuturesOrderExecNotifyRequest.class);
        //�����w���敨�o���ʒm���X�|���X
        regClass(WEB3FuturesOrderExecNotifyResponse.class);

        //�����w���敨��������ʒm���N�G�X�g
        regClass(WEB3FuturesChangeCancelNotifyRequest.class);
        //�����w���敨��������ʒm���X�|���X
        regClass(WEB3FuturesChangeCancelNotifyResponse.class);

        //�����w���敨�����ʒm���N�G�X�g
        regClass(WEB3FuturesOrderNotifyRequest.class);
        //�����w���敨�����ʒm���X�|���X
        regClass(WEB3FuturesOrderNotifyResponse.class);

        //�����w���敨���ʓ��̓��N�G�X�g
        regClass(WEB3FuturesCommonRequest.class);
        //�����w���敨���ʓ��̓��X�|���X
        regClass(WEB3FuturesCommonResponse.class);

        //�����w���敨�V�K�����������I����ʃ��N�G�X�g
        regClass(WEB3FuturesProductSelectRequest.class);
        //�����w���敨�V�K�����������I����ʃ��X�|���X
        regClass(WEB3FuturesProductSelectResponse.class);

        //�����w���敨�V�K���������͉�ʃ��N�G�X�g
        regClass(WEB3FuturesOpenMarginInputRequest.class);
        //�����w���敨�V�K���������͉�ʃ��X�|���X
        regClass(WEB3FuturesOpenMarginInputResponse.class);

        //�����w���敨�V�K�������m�F���N�G�X�g
        regClass(WEB3FuturesOpenMarginConfirmRequest.class);
        //�����w���敨�V�K�������m�F���X�|���X
        regClass(WEB3FuturesOpenMarginConfirmResponse.class);

        //�����w���敨�V�K�������������N�G�X�g
        regClass(WEB3FuturesOpenMarginCompleteRequest.class);
        //�����w���敨�V�K�������������X�|���X
        regClass(WEB3FuturesOpenMarginCompleteResponse.class);

        //�����w���敨�����V�K�����͉�ʃ��N�G�X�g
        regClass(WEB3FuturesOpenMarginChangeInputRequest.class);
        //�����w���敨�����V�K�����͉�ʃ��X�|���X
        regClass(WEB3FuturesOpenMarginChangeInputResponse.class);

        //�����w���敨�����V�K���m�F���N�G�X�g
        regClass(WEB3FuturesOpenMarginChangeConfirmRequest.class);
        //�����w���敨�����V�K���m�F���X�|���X
        regClass(WEB3FuturesOpenMarginChangeConfirmResponse.class);

        //�����w���敨�����V�K���������N�G�X�g
        regClass(WEB3FuturesOpenMarginChangeCompleteRequest.class);
        //�����w���敨�����V�K���������X�|���X
        regClass(WEB3FuturesOpenMarginChangeCompleteResponse.class);

        //�����w���敨�ʕԍψꗗ��ʕ\�����N�G�X�g
        regClass(WEB3FuturesIndividualCloseMarginListRequest.class);
        //�����w���敨�ʕԍψꗗ��ʕ\�����X�|���X
        regClass(WEB3FuturesIndividualCloseMarginListResponse.class);

        //�����w���敨�ԍψꗗ��ʕ\�����N�G�X�g
        regClass(WEB3FuturesCloseMarginListRequest.class);
        //�����w���敨�ԍψꗗ��ʕ\�����X�|���X
        regClass(WEB3FuturesCloseMarginListResponse.class);

        //�����w���敨�ԍϓ��͉�ʃ��N�G�X�g
        regClass(WEB3FuturesCloseMarginInputRequest.class);
        //�����w���敨�ԍϓ��͉�ʃ��X�|���X
        regClass(WEB3FuturesCloseMarginInputResponse.class);

        //�����w���敨�ԍϒ����m�F���N�G�X�g
        regClass(WEB3FuturesCloseMarginConfirmRequest.class);
        //�����w���敨�ԍϒ����m�F���X�|���X
        regClass(WEB3FuturesCloseMarginConfirmResponse.class);

        //�����w���敨�ԍϒ����������N�G�X�g
        regClass(WEB3FuturesCloseMarginCompleteRequest.class);
        //�����w���敨�ԍϒ����������X�|���X
        regClass(WEB3FuturesCloseMarginCompleteResponse.class);

        //�����w���敨�����ԍϓ��͉�ʃ��N�G�X�g
        regClass(WEB3FuturesCloseMarginChangeInputRequest.class);
        //�����w���敨�����ԍϓ��͉�ʃ��X�|���X
        regClass(WEB3FuturesCloseMarginChangeInputResponse.class);

        //�����w���敨�����ԍϊm�F���N�G�X�g
        regClass(WEB3FuturesCloseMarginChangeConfirmRequest.class);
        //�����w���敨�����ԍϊm�F���X�|���X
        regClass(WEB3FuturesCloseMarginChangeConfirmResponse.class);

        //�����w���敨�����ԍϊ������N�G�X�g
        regClass(WEB3FuturesCloseMarginChangeCompleteRequest.class);
        //�����w���敨�����ԍϊ������X�|���X
        regClass(WEB3FuturesCloseMarginChangeCompleteResponse.class);

        //�����w���敨��������m�F���N�G�X�g
        regClass(WEB3FuturesCancelConfirmRequest.class);
        //�����w���敨��������m�F���X�|���X
        regClass(WEB3FuturesCancelConfirmResponse.class);

        //�����w���敨��������������N�G�X�g
        regClass(WEB3FuturesCancelCompleteRequest.class);
        //�����w���敨��������������X�|���X
        regClass(WEB3FuturesCancelCompleteResponse.class);

        //�����w���敨�������Ɖ�N�G�X�g
        regClass(WEB3FuturesExecuteReferenceRequest.class);
        //�����w���敨�������Ɖ�X�|���X
        regClass(WEB3FuturesExecuteReferenceResponse.class);

        //�����w���敨���������Ɖ�N�G�X�g
        regClass(WEB3FuturesOrderHistoryRequest.class);
        //�����w���敨���������Ɖ�X�|���X
        regClass(WEB3FuturesOrderHistoryResponse.class);

        //�����w���敨�����������ڍ׃��N�G�X�g
        regClass(WEB3FuturesExecuteDetailsRequest.class);
        //�����w���敨�����������ڍ׃��X�|���X
        regClass(WEB3FuturesExecuteDetailsResponse.class);

        //�����w���敨�ԍό��ʈꗗ���N�G�X�g
        regClass(WEB3FuturesCloseMarginContractListRequest.class);
        //�����w���敨�ԍό��ʈꗗ���X�|���X
        regClass(WEB3FuturesCloseMarginContractListResponse.class);

        //�����w���敨���ʏƉ�N�G�X�g
        regClass(WEB3FuturesContractReferenceRequest.class);
        //�����w���敨���ʏƉ�X�|���X
        regClass(WEB3FuturesContractReferenceResponse.class);

        //---------------------- 6 Handler �̓o�^���� ----------------------
        //---�敨OP���ʃT�[�r�X---
        //�敨OP������t���N�G�X�g�p�n���h��
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3IfoOrderAcceptRequest.class,
            WEB3IfoOrderAcceptHandler.class,
            "optionOrderAcceptRequest");

        //�敨OP���������t���N�G�X�g�p�n���h��
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3IfoChangeCancelAcceptRequest.class,
            WEB3IfoChangeCancelOrderAcceptHandler.class,
            "ifoChangeCancelOrderAcceptRequest");

        //�敨OP�����ʒm���N�G�X�g�p�n���h��
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3IfoCloseOrderRequest.class,
            WEB3IfoCloseNotifyHandler.class,
            "ifoCloseNotifyRequest");

        //�敨OP�o���I���ʒm���N�G�X�g�p�n���h��
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3IfoExecEndNotifyRequest.class,
            WEB3IfoExecuteEndNotifyHandler.class,
            "executeEndNotifyRequest");

        //�敨OP�c���Ɖ�N�G�X�g�p�n���h��(get�c���Ɖ�)
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3FuturesOptionsBalanceReferenceRequest.class,
            WEB3IfoBalanceReferenceHandler.class,
            "getBalanceReference");

        //�敨OP�c���Ɖ�N�G�X�g�p�n���h��(get�c�����v)
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3FuturesOptionsBalanceReferenceTotalRequest.class,
            WEB3IfoBalanceReferenceHandler.class,
            "getBalanceReferenceTotal");

        //---OP�T�[�r�X---
        //OP�o���ʒm���N�G�X�g�p�n���h��
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3OptionOrderExecNotifyRequest.class,
            WEB3OptionOrderExecNotifyHandler.class,
            "optionOrderExecNotifyRequest");

        //OP��������ʒm���N�G�X�g�p�n���h��
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3OptionsChangeCancelNotifyRequest.class,
            WEB3OptionChangeCancelNotifyHandler.class,
            "optionChangeCancelNotifyRequest");

        //OP�����ʒm���N�G�X�g�p�n���h��
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3OptionsOrderNotifyRequest.class,
            WEB3OptionsOrderNotifyHandler.class,
            "optionsOrderNotifyRequest");

        //OP�V�K�����������I����ʃ��N�G�X�g�p�n���h��
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3OptionsProductSelectRequest.class,
            WEB3OptionOpenContractInputHandler.class,
            "openContractProductSelectRequest");

        //OP�V�K�����̓��N�G�X�g�p�n���h��
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3OptionsOpenMarginInputRequest.class,
            WEB3OptionOpenContractInputHandler.class,
            "openContractInputRequest");

        //OP�V�K�������m�F���N�G�X�g�p�n���h��
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3OptionsOpenMarginConfirmRequest.class,
            WEB3OptionOpenContractOrderHandler.class,
            "confirmOrder");

        //OP�V�K�������������N�G�X�g�p�n���h��
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3OptionsOpenMarginCompleteRequest.class,
            WEB3OptionOpenContractOrderHandler.class,
            "completeOrder");

        //OP�����V�K�����̓��N�G�X�g�p�n���h��
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3OptionsOpenMarginChangeInputRequest.class,
            WEB3OptionChangeOpenContractInputHandler.class,
            "changeOpenContractInputRequest");

        //OP�����V�K���m�F���N�G�X�g�p�n���h��
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3OptionsOpenMarginChangeConfirmRequest.class,
            WEB3OptionChangeOpenContractHandler.class,
            "confirmChangeOpenContract");

        //OP�����V�K���������N�G�X�g�p�n���h��
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3OptionsOpenMarginChangeCompleteRequest.class,
            WEB3OptionChangeOpenContractHandler.class,
            "completeChangeOpenContract");

        //OP�ʕԍψꗗ�\�����N�G�X�g�p�n���h��
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3OptionsIndividualCloseMarginListRequest.class,
            WEB3OptionIndividualSettleContractListServiceHandler.class,
            "getIndividualSettleContractList");

        //OP�ԍψꗗ���N�G�X�g�p�n���h��
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3OptionsCloseMarginListRequest.class,
            WEB3OptionSettleContractInputServiceHandler.class,
            "getSettleContractList");

        //OP�ԍϓ��̓��N�G�X�g�p�n���h��
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3OptionsCloseMarginInputRequest.class,
            WEB3OptionSettleContractInputServiceHandler.class,
            "getSettleContractInputScreen");

        //OP�ԍϊm�F���N�G�X�g�p�n���h��
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3OptionsCloseMarginConfirmRequest.class,
            WEB3OptionSettleContractHandler.class,
            "confirmSettleContract");

        //OP�ԍϊ������N�G�X�g�p�n���h��
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3OptionsCloseMarginCompleteRequest.class,
            WEB3OptionSettleContractHandler.class,
            "completeSettleContract");

        //OP�����ԍϓ��̓��N�G�X�g�p�n���h��
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3OptionsCloseMarginChangeInputRequest.class,
            WEB3OptionChangeClosingContractInputHandler.class,
            "changeClosingContractInputRequest");

        //OP�����ԍϊm�F���N�G�X�g�p�n���h��
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3OptionsCloseMarginChangeConfirmRequest.class,
            WEB3OptionChangeClosingContractHandler.class,
            "confirmChangeClosingContract");

        //OP�����ԍϊ������N�G�X�g�p�n���h��
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3OptionsCloseMarginChangeCompleteRequest.class,
            WEB3OptionChangeClosingContractHandler.class,
            "completeChangeClosingContract");

        //OP����m�F���N�G�X�g�p�n���h��
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3OptionsCancelConfirmRequest.class,
            WEB3OptionCancelOrderHandler.class,
            "confirmCancel");

        //OP����������N�G�X�g�p�n���h��
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3OptionsCancelCompleteRequest.class,
            WEB3OptionCancelOrderHandler.class,
            "completeCancel");

        //OP�������Ɖ�N�G�X�g�p�n���h��
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3OptionsExecuteReferenceRequest.class,
            WEB3OptionOrderExecutedInquiryHandler.class,
            "getOrderExecutedInquiry");

        //OP�������ڍ׃��N�G�X�g�p�n���h��
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3OptionsExecuteDetailsRequest.class,
            WEB3OptionOrderExecutedInquiryHandler.class,
            "getOrderExecutedDetail");

        //OP�����������N�G�X�g�p�n���h��
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3OptionsOrderHistoryRequest.class,
            WEB3OptionOrderExecutedInquiryHandler.class,
            "getOrderActionInquiry");

        //OP�ԍό��ʈꗗ���N�G�X�g�p�n���h��
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3OptionsCloseMarginContractListRequest.class,
            WEB3OptionOrderExecutedInquiryHandler.class,
            "getSettleContractList");

        //OP���ʏƉ�N�G�X�g�p�n���h��
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3OptionsContractReferenceRequest.class,
            WEB3OptionContractInquiryHandler.class,
            "getContract");

        //---�敨�T�[�r�X---
        //�敨�o���ʒm���N�G�X�g�p�n���h��
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3FuturesOrderExecNotifyRequest.class,
            WEB3FuturesOrderExecNotifyHandler.class,
            "futuresOrderExecNotifyRequest");

        //�敨��������ʒm���N�G�X�g�p�n���h��
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3FuturesChangeCancelNotifyRequest.class,
            WEB3FuturesChangeCancelNotifyHandler.class,
            "WEB3FuturesChangeCancelNotifyRequest");

        //�敨�����ʒm���N�G�X�g�p�n���h��
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3FuturesOrderNotifyRequest.class,
            WEB3FuturesOrderNotifyHandler.class,
            "futuresOrderNotifyRequest");

        //�敨�V�K�����������I����ʃ��N�G�X�g�p�n���h��
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3FuturesProductSelectRequest.class,
            WEB3FuturesOpenContractInputHandler.class,
            "openMarginProductSelectRequest");

        //�敨�V�K�����̓��N�G�X�g�p�n���h��
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3FuturesOpenMarginInputRequest.class,
            WEB3FuturesOpenContractInputHandler.class,
            "openMarginInputRequest");

        //�敨�V�K���m�F���N�G�X�g�p�n���h��
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3FuturesOpenMarginConfirmRequest.class,
            WEB3FuturesOpenContractHandler.class,
            "confirmOrder");

        //�敨�V�K���������N�G�X�g�p�n���h��
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3FuturesOpenMarginCompleteRequest.class,
            WEB3FuturesOpenContractHandler.class,
            "completeOrder");

        //�敨�����V�K�����̓��N�G�X�g�p�n���h��
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3FuturesOpenMarginChangeInputRequest.class,
            WEB3FuturesChangeOpenContractInputHandler.class,
            "openMarginChangeInputRequest");

        //�敨�����V�K���m�F���N�G�X�g�p�n���h��
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3FuturesOpenMarginChangeConfirmRequest.class,
            WEB3FuturesChangeOpenContractHandler.class,
            "confirmOpenMarginChange");

        //�敨�����V�K���������N�G�X�g�p�n���h��
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3FuturesOpenMarginChangeCompleteRequest.class,
            WEB3FuturesChangeOpenContractHandler.class,
            "completeOpenMarginChange");

        //�敨�ʕԍψꗗ�\�����N�G�X�g�p�n���h��
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3FuturesIndividualCloseMarginListRequest.class,
            WEB3FuturesIndividualSettleContractListHandler.class,
            "getIndividualCloseMarginList");

        //�敨�ԍψꗗ���N�G�X�g�p�n���h��
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3FuturesCloseMarginListRequest.class,
            WEB3FuturesSettleContractInputHandler.class,
            "getCloseMarginList");

        //�敨�ԍϓ��̓��N�G�X�g�p�n���h��
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3FuturesCloseMarginInputRequest.class,
            WEB3FuturesSettleContractInputHandler.class,
            "getCloseMarginInput");

        //�敨�ԍϊm�F���N�G�X�g�p�n���h��
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3FuturesCloseMarginConfirmRequest.class,
            WEB3FuturesSettleContractOrderHandler.class,
            "confirmCloseMargin");

        //�敨�ԍϊ������N�G�X�g�p�n���h��
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3FuturesCloseMarginCompleteRequest.class,
            WEB3FuturesSettleContractOrderHandler.class,
            "completeCloseMargin");

        //�敨�����ԍϓ��̓��N�G�X�g�p�n���h��
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3FuturesCloseMarginChangeInputRequest.class,
            WEB3FuturesChangeClosingContractInputHandler.class,
            "WEB3FuturesCloseMarginChangeInputRequest");

        //�敨�����ԍϊm�F���N�G�X�g�p�n���h��
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3FuturesCloseMarginChangeConfirmRequest.class,
            WEB3FuturesChangeClosingContractHandler.class,
            "confirmChangeClosingContract");

        //�敨�����ԍϊ������N�G�X�g�p�n���h��
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3FuturesCloseMarginChangeCompleteRequest.class,
            WEB3FuturesChangeClosingContractHandler.class,
            "completeChangeClosingContract");

        //�敨����m�F���N�G�X�g�p�n���h��
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3FuturesCancelConfirmRequest.class,
            WEB3FuturesCancelOrderHandler.class,
            "confirmCancel");

        //�敨����������N�G�X�g�p�n���h��
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3FuturesCancelCompleteRequest.class,
            WEB3FuturesCancelOrderHandler.class,
            "completeCancel");

        //�敨�������Ɖ�N�G�X�g�p�n���h��
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3FuturesExecuteReferenceRequest.class,
            WEB3FuturesExecuteReferenceHandler.class,
            "getOrderExecutedInquiry");

        //�敨�������ڍ׃��N�G�X�g�p�n���h��
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3FuturesExecuteDetailsRequest.class,
            WEB3FuturesExecuteReferenceHandler.class,
            "getOrderExecutedDetail");

        //�敨�����������N�G�X�g�p�n���h��
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3FuturesOrderHistoryRequest.class,
            WEB3FuturesExecuteReferenceHandler.class,
            "getOrderActionInquiry");

        //�敨�ԍό��ʈꗗ���N�G�X�g�p�n���h��
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3FuturesCloseMarginContractListRequest.class,
            WEB3FuturesExecuteReferenceHandler.class,
            "getSettleContractList");

        //�敨���ʏƉ�N�G�X�g�p�n���h��
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3FuturesContractReferenceRequest.class,
            WEB3FuturesContractInquiryHandler.class,
            "getContract");

        //---------------------- RAC-CTX�C���^�Z�v�^�̐ݒ� ----------------------
        // �敨�EOP������t�ꌏ
        Services.addInterceptor(
            WEB3IfoOrderAcceptUnitService.class,
            new WEB3IfoDescendRacCtxInterceptor());

        // �敨�EOP���������t�ꌏ
        Services.addInterceptor(
            WEB3IfoChangeCancelOrderAcceptUnitService.class,
            new WEB3IfoDescendRacCtxInterceptor());

        // �敨�EOP�����ʒm�ꌏ
        Services.addInterceptor(
            WEB3IfoCloseNotifyUnitService.class,
            new WEB3IfoDescendRacCtxInterceptor());

        // �敨OP�o���I���ʒm�ꌏ
        Services.addInterceptor(
            WEB3IfoExecuteEndNotifyUnitService.class,
            new WEB3IfoDescendRacCtxInterceptor());

        // OP�o���ʒm�ꌏ
        Services.addInterceptor(
            WEB3OptionOrderExecNotifyUnitService.class,
            new WEB3IfoDescendRacCtxInterceptor());

        // OP�����E����ʒm�ꌏ
        Services.addInterceptor(
            WEB3OptionChangeCancelNotifyUnitService.class,
            new WEB3IfoDescendRacCtxInterceptor());

        // OP�����ʒm�ꌏ
        Services.addInterceptor(
            WEB3OptionsOrderNotifyUnitService.class,
            new WEB3IfoDescendRacCtxInterceptor());

        // �敨�o���ʒm�ꌏ
        Services.addInterceptor(
            WEB3FuturesOrderExecNotifyUnitService.class,
            new WEB3IfoDescendRacCtxInterceptor());

        // �敨�����E����ʒm�ꌏ
        Services.addInterceptor(
            WEB3FuturesChangeCancelNotifyUnitService.class,
            new WEB3IfoDescendRacCtxInterceptor());

        // �敨�����ʒm�ꌏ
        Services.addInterceptor(
            WEB3FuturesOrderNotifyUnitService.class,
            new WEB3IfoDescendRacCtxInterceptor());

        log.exiting(METHOD_NAME);
    }
}
@
