head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.54.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : Webbroker3-TriggerOrder �v���O�C���N���X(WEB3ToSuccAppPlugin.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/18 �s�p (���u) �V�K�쐬 
Revesion History : 2006/01/23 �s�p�i���u�j��QU02732�Ή�
Revesion History : 2006/01/24 �s�p�i���u�j�d�l�ύX�E���f��066
Revesion History : 2006/02/07 ������i���u�j�d�l�ύX�E���f��091,092                   
Revesion History : 2006/02/19 ��猁i���u�j�d�l�ύX�E���f��094,097~099
Revesion History : 2006/03/09 ��O���i���u�j�d�l�ύX�E���f��109,110,120~125
Revesion History : 2006/08/31 �юu�́i���u�j�d�l�ύX�E���f��157
Revesion History : 2006/11/27 ���G�� (���u)�d�l�ύX�E���f��176
Revesion History : 2006/11/27 ���G�� (���u)�d�l�ύX�E���f��184
Revesion History : 2008/03/25 �g�E�N�| (���u) �yIFO�z�A�������Ή�
Revesion History : 2008/04/11 ��іQ (���u)�d�l�ύX�E���f��277
Revesion History : 2008/04/18 �И��� (���u)�yIFO�z�A�������Ή�
Revesion History : 2008/05/06 �g�E�N�| (���u)�yIFO�z�A�������Ή�
*/
package webbroker3.triggerorder;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;

import webbroker3.common.WEB3LogSysTimeInterceptor;
import webbroker3.equity.WEB3EquityChangeOrderInputServiceInterceptor;
import webbroker3.equity.WEB3EquityOrderBuyInputServiceInterceptor;
import webbroker3.equity.WEB3EquityOrderServiceInterceptor;
import webbroker3.equity.WEB3EquitySellOrderInputServiceInterceptor;
import webbroker3.equity.WEB3MarginChangeCloseMarginInputServiceInterceptor;
import webbroker3.equity.WEB3MarginChangeOpenMarginInputServiceInterceptor;
import webbroker3.equity.WEB3MarginCloseMarginInputServiceInterceptor;
import webbroker3.equity.WEB3MarginCloseMarginServiceInterceptor;
import webbroker3.equity.WEB3MarginOpenMarginInputServiceInterceptor;
import webbroker3.equity.WEB3MarginOpenMarginServiceInterceptor;
import webbroker3.equity.WEB3MarginSwapMarginInputServiceInterceptor;
import webbroker3.equity.WEB3MarginSwapMarginServiceInterceptor;
import webbroker3.ifo.WEB3FuturesOpenContractInputServiceInterceptor;
import webbroker3.ifo.WEB3FuturesOpenContractServiceInterceptor;
import webbroker3.ifo.WEB3FuturesSettleContractInputServiceInterceptor;
import webbroker3.ifo.WEB3FuturesSettleContractOrderServiceInterceptor;
import webbroker3.ifo.WEB3IfoAppPlugin;
import webbroker3.ifo.WEB3OptionOpenContractInputServiceInterceptor;
import webbroker3.ifo.WEB3OptionOpenContractOrderServiceInterceptor;
import webbroker3.ifo.WEB3OptionSettleContractInputServiceInterceptor;
import webbroker3.ifo.WEB3OptionSettleContractOrderServiceInterceptor;
import webbroker3.mqgateway.WEB3MQGatewayInterceptor;
import webbroker3.triggerorder.base.WEB3ToSuccBaseAppPlugin;
import webbroker3.triggerorder.handler.WEB3FuturesOrderCarryOverHandler;
import webbroker3.triggerorder.handler.WEB3OptionOrderCarryOverHandler;
import webbroker3.triggerorder.handler.WEB3ToIfoManualOrderHandler;
import webbroker3.triggerorder.handler.WEB3ToStopEquityManualOrderHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccAdditionalContentsSelectHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccEquityCancelOrderHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccEquityChangeOrderHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccEquityChangeOrderInputHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccEquityManualOrderHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccEquityOrderBuyInputHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccEquityOrderHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccEquitySellOrderInputHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccFuturesCancelOrderHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccFuturesChangeClosingContractHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccFuturesChangeClosingContractInputHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccFuturesChangeOpenContractHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccFuturesChangeOpenContractInputHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccFuturesOpenContractHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccFuturesOpenContractInputHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccFuturesSettleContractInputHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccFuturesSettleContractOrderHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccMarginCancelHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccMarginChangeCloseMarginHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccMarginChangeCloseMarginInputHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccMarginChangeOpenMarginHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccMarginChangeOpenMarginInputHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccMarginCloseMarginHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccMarginCloseMarginInputHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccMarginOpenMarginHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccMarginOpenMarginInputHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccMarginSwapMarginHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccMarginSwapMarginInputHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccOptionCancelOrderHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccOptionChangeClosingContractHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccOptionChangeClosingContractInputHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccOptionChangeOpenContractHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccOptionChangeOpenContractInputHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccOptionOpenContractInputHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccOptionOpenContractOrderHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccOptionSettleContractHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccOptionSettleContractInputServiceHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccSettingContentsConfirmHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccSettingListHandler;
import webbroker3.triggerorder.handler.WEB3ToWLimitEquityManualSwitchHandler;
import webbroker3.triggerorder.message.*;
import webbroker3.triggerorder.service.delegate.WEB3FuturesOrderCarryOverService;
import webbroker3.triggerorder.service.delegate.WEB3FuturesOrderCarryOverUnitService;
import webbroker3.triggerorder.service.delegate.WEB3OptionOrderCarryOverService;
import webbroker3.triggerorder.service.delegate.WEB3OptionOrderCarryOverUnitService;
import webbroker3.triggerorder.service.delegate.WEB3ToIfoManualOrderService;
import webbroker3.triggerorder.service.delegate.WEB3ToIfoManualOrderUnitService;
import webbroker3.triggerorder.service.delegate.WEB3ToStopEquityManualOrderMainService;
import webbroker3.triggerorder.service.delegate.WEB3ToStopEquityManualOrderUnitService;
import webbroker3.triggerorder.service.delegate.WEB3ToStopEquityOrderUnitService;
import webbroker3.triggerorder.service.delegate.WEB3ToStopIfoOrderUnitService;
import webbroker3.triggerorder.service.delegate.WEB3ToStopOrderService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccAdditionalContentsSelectService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccDataGettingService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccEquityCancelOrderService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccEquityChangeOrderInputService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccEquityChangeOrderService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccEquityManualOrderMainService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccEquityManualOrderUnitService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccEquityOrderBuyInputService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccEquityOrderService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccEquityOrderUnitService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccEquitySellOrderInputService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesCancelOrderService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesChangeClosingContractInputService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesChangeClosingContractService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesChangeOpenContractInputService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesChangeOpenContractService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesOpenContractInputService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesOpenContractService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesSettleContractInputService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesSettleContractOrderService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccIfoOrderCarryOverService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccIfoOrderUnitService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginCancelService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginChangeCloseMarginInputService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginChangeCloseMarginService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginChangeOpenMarginInputService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginChangeOpenMarginService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginCloseMarginInputService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginCloseMarginService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginOpenMarginInputService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginOpenMarginService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginSwapMarginInputService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginSwapMarginService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionCancelOrderService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionChangeClosingContractInputService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionChangeClosingContractService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionChangeOpenContractInputService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionChangeOpenContractService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionOpenContractInputService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionOpenContractOrderService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionSettleContractInputService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionSettleContractOrderService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOrderService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccSettingContentsConfirmService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccSettingListService;
import webbroker3.triggerorder.service.delegate.WEB3ToWLimitEquityManualSwitchMainService;
import webbroker3.triggerorder.service.delegate.WEB3ToWLimitEquityManualSwitchUnitService;
import webbroker3.triggerorder.service.delegate.WEB3ToWLimitEquitySwitchUnitService;
import webbroker3.triggerorder.service.delegate.WEB3ToWLimitIfoSwitchUnitService;
import webbroker3.triggerorder.service.delegate.WEB3ToWLimitMarginCloseMarginSwitchUnitService;
import webbroker3.triggerorder.service.delegate.WEB3ToWLimitMarginOpenMarginSwitchUnitService;
import webbroker3.triggerorder.service.delegate.WEB3ToWLimitSwitchService;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3FuturesOrderCarryOverServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3FuturesOrderCarryOverUnitServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3OptionOrderCarryOverServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3OptionOrderCarryOverUnitServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToIfoManualOrderServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToIfoManualOrderUnitServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToStopEquityManualOrderMainServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToStopEquityManualOrderUnitServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToStopEquityOrderUnitServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToStopIfoOrderUnitServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToStopOrderServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccAdditionalContentsSelectServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccDataGettingServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccEquityCancelOrderServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccEquityChangeOrderInputServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccEquityChangeOrderServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccEquityManualOrderMainServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccEquityManualOrderUnitServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccEquityOrderBuyInputServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccEquityOrderServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccEquityOrderUnitServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccEquitySellOrderInputServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesCancelOrderServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesChangeClosingContractInputServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesChangeClosingContractServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesChangeOpenContractInputServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesChangeOpenContractServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesOpenContractInputServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesOpenContractServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesSettleContractInputServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesSettleContractOrderServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccIfoOrderCarryOverServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccIfoOrderUnitServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccMarginCancelServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccMarginChangeCloseMarginInputServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccMarginChangeCloseMarginServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccMarginChangeOpenMarginInputServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccMarginChangeOpenMarginServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccMarginCloseMarginInputServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccMarginCloseMarginServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccMarginOpenMarginInputServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccMarginOpenMarginServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccMarginSwapMarginInputServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccMarginSwapMarginServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionCancelOrderServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionChangeClosingContractInputServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionChangeClosingContractServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionChangeOpenContractInputServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionChangeOpenContractServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionOpenContractInputServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionOpenContractOrderServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionSettleContractInputServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionSettleContractOrderServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOrderServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccSettingContentsConfirmServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccSettingListServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToWLimitEquityManualSwitchMainServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToWLimitEquityManualSwitchUnitServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToWLimitEquitySwitchUnitServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToWLimitIfoSwitchUnitServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToWLimitMarginCloseMarginSwitchUnitServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToWLimitMarginOpenMarginSwitchUnitServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToWLimitSwitchServiceImpl;
import webbroker3.util.WEB3LogUtility;

/**
 * Webbroker3-TriggerOrder �v���O�C���N���X<BR>
 *   
 * @@author �s�p
 * @@version 1.0
 */
public class WEB3ToSuccAppPlugin extends Plugin
{
    /**
     * ���O���[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccAppPlugin.class);

    /**
     * �R���X�g���N�^�B
     */
    public WEB3ToSuccAppPlugin()
    {
    }

    /**
     * �v���O�C���G���g���[�|�C���g�B
     */
    public static void plug() throws Exception
    {
        String METHOD_NAME = "plug()";
        log.entering(METHOD_NAME);
        
        plug(WEB3ToSuccAppPlugin.class);
        
        log.exiting(METHOD_NAME);
    }
    
    /**
     * �v���O�C�������B
     */
    public static void onPlug() throws Exception 
    {
        String METHOD_NAME = "onPlug()";
        log.entering(METHOD_NAME);
        
        //���̃v���O�C������ɓǂݍ��ޕK�v�̂���v���O�C���̎w��B
        //install the system plugins that we need
        KernelPlugin.plug();

        WEB3ToSuccBaseAppPlugin.plug();
        
        //Service �̓o�^
        //�ݒ���e�m�F�T�[�r�X
        Services.registerService(
            WEB3ToSuccSettingContentsConfirmService.class,
            new WEB3ToSuccSettingContentsConfirmServiceImpl());
        
        //�ǉ����e�I���T�[�r�X
        Services.registerService(
            WEB3ToSuccAdditionalContentsSelectService.class,
            new WEB3ToSuccAdditionalContentsSelectServiceImpl());
            
        //�A���ݒ�ꗗ�T�[�r�X
        Services.registerService(
            WEB3ToSuccSettingListService.class,
            new WEB3ToSuccSettingListServiceImpl());
            
        //�A�������f�[�^�擾�T�[�r�X     
        Services.registerService(
            WEB3ToSuccDataGettingService.class,
            new WEB3ToSuccDataGettingServiceImpl());
        
        //�A���������������ꌏ�T�[�r�X
        Services.registerService(
            WEB3ToSuccEquityOrderUnitService.class,
            new WEB3ToSuccEquityOrderUnitServiceImpl()); 
        
        //�A�����������T�[�r�X
        Services.registerService(
            WEB3ToSuccOrderService.class,
            new WEB3ToSuccOrderServiceImpl()); 
            
        //�t�w�l�������������ꌏ�T�[�r�X 
        Services.registerService(
            WEB3ToStopEquityOrderUnitService.class,
            new WEB3ToStopEquityOrderUnitServiceImpl()); 
        
        //�t�w�l���������T�[�r�X 
        Services.registerService(
            WEB3ToStopOrderService.class,
            new WEB3ToStopOrderServiceImpl());
            
        //�i�A���j���������T�[�r�X 
        Services.registerService(
            WEB3ToSuccEquityOrderService.class,
            new WEB3ToSuccEquityOrderServiceImpl()); 
            
        //�i�A���j�����������t�������̓T�[�r�X 
        Services.registerService(
            WEB3ToSuccEquityOrderBuyInputService.class,
            new WEB3ToSuccEquityOrderBuyInputServiceImpl()); 
            
        //�i�A���j�����������t���̓T�[�r�X    
        Services.registerService(
            WEB3ToSuccEquitySellOrderInputService.class,
            new WEB3ToSuccEquitySellOrderInputServiceImpl()); 
            
        //�i�A���j������������T�[�r�X
        Services.registerService(
            WEB3ToSuccEquityCancelOrderService.class,
            new WEB3ToSuccEquityCancelOrderServiceImpl()); 
        
        //�i�A���j�������������������̓T�[�r�X�C���^�t�F�[�X
        Services.registerService(
            WEB3ToSuccEquityChangeOrderInputService.class,
            new WEB3ToSuccEquityChangeOrderInputServiceImpl()); 
            
        //�i�A���j�������������T�[�r�X
        Services.registerService(
            WEB3ToSuccEquityChangeOrderService.class,
            new WEB3ToSuccEquityChangeOrderServiceImpl()); 
            
        //�i�A���j�M�p����������n�T�[�r�X
        Services.registerService(
            WEB3ToSuccMarginSwapMarginService.class, 
            new WEB3ToSuccMarginSwapMarginServiceImpl());

        //�i�A���j�M�p����������n���̓T�[�r�X
        Services.registerService(
            WEB3ToSuccMarginSwapMarginInputService.class,
            new WEB3ToSuccMarginSwapMarginInputServiceImpl());

        //�i�A���j�M�p�������T�[�r�X
        Services.registerService(
            WEB3ToSuccMarginCancelService.class, 
            new WEB3ToSuccMarginCancelServiceImpl());

        //�i�A���j�M�p����V�K���T�[�r�X
        Services.registerService(
            WEB3ToSuccMarginOpenMarginService.class, 
            new WEB3ToSuccMarginOpenMarginServiceImpl());

        //�i�A���j�M�p����V�K�����̓T�[�r�X
        Services.registerService(
            WEB3ToSuccMarginOpenMarginInputService.class,
            new WEB3ToSuccMarginOpenMarginInputServiceImpl());

        //�i�A���j�M�p��������V�K���T�[�r�X
        Services.registerService(
            WEB3ToSuccMarginChangeOpenMarginService.class,
            new WEB3ToSuccMarginChangeOpenMarginServiceImpl());

        //�i�A���j�M�p��������V�K�����̓T�[�r�X
        Services.registerService(
            WEB3ToSuccMarginChangeOpenMarginInputService.class,
            new WEB3ToSuccMarginChangeOpenMarginInputServiceImpl());

        //�i�A���j�M�p��������ԍσT�[�r�X
        Services.registerService(
            WEB3ToSuccMarginChangeCloseMarginService.class,
            new WEB3ToSuccMarginChangeCloseMarginServiceImpl());

        //�i�A���j�M�p��������ԍϓ��̓T�[�r�X
        Services.registerService(
            WEB3ToSuccMarginChangeCloseMarginInputService.class,
            new WEB3ToSuccMarginChangeCloseMarginInputServiceImpl());

        //�i�A���j�M�p����ԍσT�[�r�X
        Services.registerService(
            WEB3ToSuccMarginCloseMarginService.class,
            new WEB3ToSuccMarginCloseMarginServiceImpl());

        //�i�A���j�M�p����ԍϓ��̓T�[�r�X
        Services.registerService(
            WEB3ToSuccMarginCloseMarginInputService.class,
            new WEB3ToSuccMarginCloseMarginInputServiceImpl());
            
        //�t�w�l�����敨OP�����ꌏ�T�[�r�X
        Services.registerService(
            WEB3ToStopIfoOrderUnitService.class,
            new WEB3ToStopIfoOrderUnitServiceImpl());
            
        //�敨OP�蓮����UnitService
        Services.registerService(
            WEB3ToIfoManualOrderUnitService.class,
            new WEB3ToIfoManualOrderUnitServiceImpl());
        
        //�敨OP�蓮�����T�[�r�X
        Services.registerService(
            WEB3ToIfoManualOrderService.class,
            new WEB3ToIfoManualOrderServiceImpl());
            
        //�����t�w�l�����蓮�������C���T�[�r�X
        Services.registerService(
            WEB3ToStopEquityManualOrderMainService.class,
            new WEB3ToStopEquityManualOrderMainServiceImpl());
        
        //�����A�������蓮�������C���T�[�r�X
        Services.registerService(
            WEB3ToSuccEquityManualOrderMainService.class,
            new WEB3ToSuccEquityManualOrderMainServiceImpl());

        //
        //�����t�w�l�����蓮����UnitService
        Services.registerService(
                WEB3ToStopEquityManualOrderUnitService.class,
            new WEB3ToStopEquityManualOrderUnitServiceImpl());
        
        //�����A�������蓮����UnitService
        Services.registerService(
                WEB3ToSuccEquityManualOrderUnitService.class,
            new WEB3ToSuccEquityManualOrderUnitServiceImpl());
        
        //W�w�l�����ؑ֏����T�[�r�X
        Services.registerService(
            WEB3ToWLimitSwitchService.class,
            new WEB3ToWLimitSwitchServiceImpl());

        //W�w�l�����敨OP�ؑֈꌏ�T�[�r�X
        Services.registerService(
            WEB3ToWLimitIfoSwitchUnitService.class,
            new WEB3ToWLimitIfoSwitchUnitServiceImpl());

        //W�w�l�������������ؑֈꌏ�T�[�r�X
        Services.registerService(
            WEB3ToWLimitEquitySwitchUnitService.class,
            new WEB3ToWLimitEquitySwitchUnitServiceImpl());

        //W�w�l�����M�p����ԍϐؑֈꌏ�T�[�r�X
        Services.registerService(
            WEB3ToWLimitMarginCloseMarginSwitchUnitService.class,
            new WEB3ToWLimitMarginCloseMarginSwitchUnitServiceImpl());

        //W�w�l�����M�p����V�K���ؑֈꌏ�T�[�r�X
        Services.registerService(
            WEB3ToWLimitMarginOpenMarginSwitchUnitService.class,
            new WEB3ToWLimitMarginOpenMarginSwitchUnitServiceImpl());

        //����W�w�l�����蓮�ؑփ��C���T�[�r�X
        Services.registerService(
            WEB3ToWLimitEquityManualSwitchMainService.class,
            new WEB3ToWLimitEquityManualSwitchMainServiceImpl());

        //����W�w�l�����蓮�ؑ�UnitService
        Services.registerService(
            WEB3ToWLimitEquityManualSwitchUnitService.class,
            new WEB3ToWLimitEquityManualSwitchUnitServiceImpl());

        //�i�A���j�敨�����V�K���T�[�r�X
        Services.registerService(
            WEB3ToSuccFuturesChangeOpenContractService.class,
            new WEB3ToSuccFuturesChangeOpenContractServiceImpl());

        //�i�A���j�敨�����V�K�����̓T�[�r�X
        Services.registerService(
            WEB3ToSuccFuturesChangeOpenContractInputService.class,
            new WEB3ToSuccFuturesChangeOpenContractInputServiceImpl());

        //�i�A���j�敨�����ԍσT�[�r�X
        Services.registerService(
            WEB3ToSuccFuturesChangeClosingContractService.class,
            new WEB3ToSuccFuturesChangeClosingContractServiceImpl());

        //�i�A���j�敨��������T�[�r�X
        Services.registerService(
            WEB3ToSuccFuturesCancelOrderService.class,
            new WEB3ToSuccFuturesCancelOrderServiceImpl());

        //�i�A���j�敨�V�K�������T�[�r�X
        Services.registerService(
            WEB3ToSuccFuturesOpenContractService.class,
            new WEB3ToSuccFuturesOpenContractServiceImpl());

        //�i�A���j�敨�V�K�����̓T�[�r�X
        Services.registerService(
            WEB3ToSuccFuturesOpenContractInputService.class,
            new WEB3ToSuccFuturesOpenContractInputServiceImpl());

        //�i�A���j�敨�����ԍϓ��̓T�[�r�X
        Services.registerService(
            WEB3ToSuccFuturesChangeClosingContractInputService.class,
            new WEB3ToSuccFuturesChangeClosingContractInputServiceImpl());

        //�i�A���j�敨�ԍϒ����T�[�r�X
        Services.registerService(
            WEB3ToSuccFuturesSettleContractOrderService.class,
            new WEB3ToSuccFuturesSettleContractOrderServiceImpl());

        //�i�A���j�敨�ԍϓ��̓T�[�r�X
        Services.registerService(
            WEB3ToSuccFuturesSettleContractInputService.class,
            new WEB3ToSuccFuturesSettleContractInputServiceImpl());

        //�i�A���jOP��������T�[�r�X
        Services.registerService(
            WEB3ToSuccOptionCancelOrderService.class,
            new WEB3ToSuccOptionCancelOrderServiceImpl());

        //�i�A���j�I�v�V�����V�K�������T�[�r�X
        Services.registerService(
            WEB3ToSuccOptionOpenContractOrderService.class,
            new WEB3ToSuccOptionOpenContractOrderServiceImpl());

        //�i�A���jOP�V�K�����̓T�[�r�X
        Services.registerService(
            WEB3ToSuccOptionOpenContractInputService.class,
            new WEB3ToSuccOptionOpenContractInputServiceImpl());

        //�i�A���jOP�����V�K���T�[�r�X
        Services.registerService(
            WEB3ToSuccOptionChangeOpenContractService.class,
            new WEB3ToSuccOptionChangeOpenContractServiceImpl());

        //�i�A���jOP�����V�K�����̓T�[�r�X
        Services.registerService(
            WEB3ToSuccOptionChangeOpenContractInputService.class,
            new WEB3ToSuccOptionChangeOpenContractInputServiceImpl());

        //�i�A���jOP�����ԍσT�[�r�X
        Services.registerService(
            WEB3ToSuccOptionChangeClosingContractService.class,
            new WEB3ToSuccOptionChangeClosingContractServiceImpl());

        //�i�A���jOP�����ԍϓ��̓T�[�r�X 
        Services.registerService(
            WEB3ToSuccOptionChangeClosingContractInputService.class,
            new WEB3ToSuccOptionChangeClosingContractInputServiceImpl());

        //�i�A���jOP�ԍϒ����T�[�r�X
        Services.registerService(
            WEB3ToSuccOptionSettleContractOrderService.class,
            new WEB3ToSuccOptionSettleContractOrderServiceImpl());

        //�i�A���jOP�ԍϓ��̓T�[�r�X
        Services.registerService(
            WEB3ToSuccOptionSettleContractInputService.class,
            new WEB3ToSuccOptionSettleContractInputServiceImpl());

        //OP�����J�z�T�[�r�X
        Services.registerService(
            WEB3OptionOrderCarryOverService.class,
            new WEB3OptionOrderCarryOverServiceImpl());
        
        //�敨OP�A�������J�z�T�[�r�X
        Services.registerService(
            WEB3ToSuccIfoOrderCarryOverService.class,
            new WEB3ToSuccIfoOrderCarryOverServiceImpl());

        //OP�����J�z�ꌏ�T�[�r�X
        Services.registerService(
            WEB3OptionOrderCarryOverUnitService.class,
            new WEB3OptionOrderCarryOverUnitServiceImpl());

        //�敨�����J�z�T�[�r�X
        Services.registerService(
            WEB3FuturesOrderCarryOverService.class,
            new WEB3FuturesOrderCarryOverServiceImpl());

        //�敨�����J�z�ꌏ�T�[�r�X
        Services.registerService(
            WEB3FuturesOrderCarryOverUnitService.class,
            new WEB3FuturesOrderCarryOverUnitServiceImpl());

        //�A�������敨OP�����ꌏ�T�[�r�X
        Services.registerService(
            WEB3ToSuccIfoOrderUnitService.class,
            new WEB3ToSuccIfoOrderUnitServiceImpl());

        // Service.execute()�Ăяo���O���  ----------------------
        // �����J�n�����Ə����I�����������O�o�͂���悤�ɐݒ肷��

        //�ݒ���e�m�F�T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccSettingContentsConfirmService.class,
            new WEB3LogSysTimeInterceptor());
        
        //�ǉ����e�I���T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccAdditionalContentsSelectService.class,
            new WEB3LogSysTimeInterceptor());            
            
        //�A���ݒ�ꗗ�T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccSettingListService.class,
            new WEB3LogSysTimeInterceptor());            
            
        //�A�������f�[�^�擾�T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccDataGettingService.class,
            new WEB3LogSysTimeInterceptor());     
        
        //�A���������������ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccEquityOrderUnitService.class,
            new WEB3LogSysTimeInterceptor());
            
        //�A�����������T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccOrderService.class,
            new WEB3LogSysTimeInterceptor());
            
        //�t�w�l�������������ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3ToStopEquityOrderUnitService.class,
            new WEB3LogSysTimeInterceptor());
            
        //�t�w�l���������T�[�r�X 
        Services.addInterceptor(
            WEB3ToStopOrderService.class,
            new WEB3LogSysTimeInterceptor());
            
        //�i�A���j���������T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccEquityOrderService.class,
            new WEB3LogSysTimeInterceptor()); 
            
        //�i�A���j�����������t�������̓T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccEquityOrderBuyInputService.class,
            new WEB3LogSysTimeInterceptor()); 
            
        //�i�A���j�����������t���̓T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccEquitySellOrderInputService.class,
            new WEB3LogSysTimeInterceptor());    
            
        //�i�A���j������������T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccEquityCancelOrderService.class,
            new WEB3LogSysTimeInterceptor()); 
        
        //�i�A���j�������������������̓T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
            WEB3ToSuccEquityChangeOrderInputService.class,
            new WEB3LogSysTimeInterceptor()); 
            
        //�i�A���j�������������T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccEquityChangeOrderService.class,
            new WEB3LogSysTimeInterceptor()); 
       
        //�i�A���j�M�p����������n�T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccMarginSwapMarginService.class,
            new WEB3LogSysTimeInterceptor());
        
        //�i�A���j�M�p����������n���̓T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccMarginSwapMarginInputService.class,
            new WEB3LogSysTimeInterceptor());
        
        //�i�A���j�M�p�������T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccMarginCancelService.class,
            new WEB3LogSysTimeInterceptor());

        //�i�A���j�M�p����V�K���T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccMarginOpenMarginService.class,
            new WEB3LogSysTimeInterceptor());
        
        //�i�A���j�M�p����V�K�����̓T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccMarginOpenMarginInputService.class,
            new WEB3LogSysTimeInterceptor());
        
        //�i�A���j�M�p��������V�K���T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccMarginChangeOpenMarginService.class,
            new WEB3LogSysTimeInterceptor());
        
        //�i�A���j�M�p��������V�K�����̓T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccMarginChangeOpenMarginInputService.class,
            new WEB3LogSysTimeInterceptor());
        
        //�i�A���j�M�p��������ԍσT�[�r�X
        Services.addInterceptor(
            WEB3ToSuccMarginChangeCloseMarginService.class,
            new WEB3LogSysTimeInterceptor());
        
        //�i�A���j�M�p��������ԍϓ��̓T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccMarginChangeCloseMarginInputService.class,
            new WEB3LogSysTimeInterceptor());
        
        //�i�A���j�M�p����ԍσT�[�r�X
        Services.addInterceptor(
            WEB3ToSuccMarginCloseMarginService.class,
            new WEB3LogSysTimeInterceptor());
        
        //�i�A���j�M�p����ԍϓ��̓T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccMarginCloseMarginInputService.class,
            new WEB3LogSysTimeInterceptor());
            
        //�i�t�w�l�����敨OP�����ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3ToStopIfoOrderUnitService.class,
            new WEB3LogSysTimeInterceptor());
       
        //�敨OP�蓮�����T�[�r�X
        Services.addInterceptor(
            WEB3ToIfoManualOrderService.class,
            new WEB3LogSysTimeInterceptor());
        
        //�敨OP�蓮����UnitService
        Services.addInterceptor(
            WEB3ToIfoManualOrderUnitService.class,
            new WEB3LogSysTimeInterceptor());
        
        //�����t�w�l�����蓮����UnitService
        Services.addInterceptor(
            WEB3ToStopEquityManualOrderUnitService.class,
            new WEB3LogSysTimeInterceptor());
        
        //�����t�w�l�����蓮�������C���T�[�r�X
        Services.addInterceptor(
            WEB3ToStopEquityManualOrderMainService.class,
            new WEB3LogSysTimeInterceptor());
        
        //�����A�������蓮����UnitService
        Services.addInterceptor(
            WEB3ToSuccEquityManualOrderUnitService.class,
            new WEB3LogSysTimeInterceptor());
        
        //�����A�������蓮�������C���T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccEquityManualOrderMainService.class,
            new WEB3LogSysTimeInterceptor());
        
        //W�w�l�����ؑ֏����T�[�r�X
        Services.addInterceptor(
            WEB3ToWLimitSwitchService.class,
            new WEB3LogSysTimeInterceptor());

        //W�w�l�����敨OP�ؑֈꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3ToWLimitIfoSwitchUnitService.class,
            new WEB3LogSysTimeInterceptor());

        //W�w�l�������������ؑֈꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3ToWLimitEquitySwitchUnitService.class,
            new WEB3LogSysTimeInterceptor());

        //W�w�l�����M�p����ԍϐؑֈꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3ToWLimitMarginCloseMarginSwitchUnitService.class,
            new WEB3LogSysTimeInterceptor());

        //W�w�l�����M�p����V�K���ؑֈꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3ToWLimitMarginOpenMarginSwitchUnitService.class,
            new WEB3LogSysTimeInterceptor());

        //����W�w�l�����蓮�ؑփ��C���T�[�r�X
        Services.addInterceptor(
            WEB3ToWLimitEquityManualSwitchMainService.class,
            new WEB3LogSysTimeInterceptor());

        //����W�w�l�����蓮�ؑ�UnitService
        Services.addInterceptor(
            WEB3ToWLimitEquityManualSwitchUnitService.class,
            new WEB3LogSysTimeInterceptor());
        
        //�i�A���j�敨�����V�K���T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccFuturesChangeOpenContractService.class,
            new WEB3LogSysTimeInterceptor());

        //�i�A���j�敨�����V�K�����̓T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccFuturesChangeOpenContractInputService.class,
            new WEB3LogSysTimeInterceptor());

        //�i�A���j�敨�����ԍσT�[�r�X
        Services.addInterceptor(
            WEB3ToSuccFuturesChangeClosingContractService.class,
            new WEB3LogSysTimeInterceptor());

        //�i�A���j�敨��������T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccFuturesCancelOrderService.class,
            new WEB3LogSysTimeInterceptor());

        //�i�A���j�敨�V�K�������T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccFuturesOpenContractService.class,
            new WEB3LogSysTimeInterceptor());

        //�i�A���j�敨�V�K�����̓T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccFuturesOpenContractInputService.class,
            new WEB3LogSysTimeInterceptor());

        //�i�A���j�敨�����ԍϓ��̓T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccFuturesChangeClosingContractInputService.class,
            new WEB3LogSysTimeInterceptor());

        //�i�A���j�敨�ԍϒ����T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccFuturesSettleContractOrderService.class,
            new WEB3LogSysTimeInterceptor());

        //�i�A���j�敨�ԍϓ��̓T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccFuturesSettleContractInputService.class,
            new WEB3LogSysTimeInterceptor());

        //�i�A���jOP��������T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccOptionCancelOrderService.class,
            new WEB3LogSysTimeInterceptor());

        //�i�A���j�I�v�V�����V�K�������T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccOptionOpenContractOrderService.class,
            new WEB3LogSysTimeInterceptor());

        //�i�A���jOP�V�K�����̓T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccOptionOpenContractInputService.class,
            new WEB3LogSysTimeInterceptor());

        //�i�A���jOP�����V�K���T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccOptionChangeOpenContractService.class,
            new WEB3LogSysTimeInterceptor());

        //�i�A���jOP�����V�K�����̓T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccOptionChangeOpenContractInputService.class,
            new WEB3LogSysTimeInterceptor());

        //�i�A���jOP�����ԍσT�[�r�X
        Services.addInterceptor(
            WEB3ToSuccOptionChangeClosingContractService.class,
            new WEB3LogSysTimeInterceptor());

        //�i�A���jOP�����ԍϓ��̓T�[�r�X 
        Services.addInterceptor(
            WEB3ToSuccOptionChangeClosingContractInputService.class,
            new WEB3LogSysTimeInterceptor());

        //�i�A���jOP�ԍϒ����T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccOptionSettleContractOrderService.class,
            new WEB3LogSysTimeInterceptor());

        //�i�A���jOP�ԍϓ��̓T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccOptionSettleContractInputService.class,
            new WEB3LogSysTimeInterceptor());

        //OP�����J�z�T�[�r�X
        Services.addInterceptor(
            WEB3OptionOrderCarryOverService.class,
            new WEB3LogSysTimeInterceptor());

        //�敨OP�A�������J�z�T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccIfoOrderCarryOverService.class,
            new WEB3LogSysTimeInterceptor());

        //OP�����J�z�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3OptionOrderCarryOverUnitService.class,
            new WEB3LogSysTimeInterceptor());
        
        //�敨�����J�z�T�[�r�X
        Services.addInterceptor(
            WEB3FuturesOrderCarryOverService.class,
            new WEB3LogSysTimeInterceptor());

        //�敨�����J�z�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3FuturesOrderCarryOverUnitService.class,
            new WEB3LogSysTimeInterceptor());

        //Service �� ServiceInterceptor ��ݒ肷�� ----------------------
        //�ݒ���e�m�F�T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccSettingContentsConfirmService.class,
            new WEB3ToSuccServiceInterceptor());   
            
        //�ǉ����e�I���T�[�r�X          
        Services.addInterceptor(
            WEB3ToSuccAdditionalContentsSelectService.class,
            new WEB3ToSuccServiceInterceptor());  
             
        //�A���ݒ�ꗗ�T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccSettingListService.class,
            new WEB3ToSuccServiceInterceptor());            
        
        //�A���������������ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccEquityOrderUnitService.class,
            new WEB3ToSuccEquityOrderUnitServiceInterceptor());

        //�A�����������T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccOrderService.class,
            new WEB3ToSuccOrderServiceInterceptor());
            
        //�t�w�l�������������ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3ToStopEquityOrderUnitService.class,
            new WEB3ToStopEquityOrderUnitServiceInterceptor());
        
        //�t�w�l���������T�[�r�X 
        Services.addInterceptor(
            WEB3ToStopOrderService.class,
            new WEB3ToStopOrderServiceInterceptor());
                
        //�i�A���j���������T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccEquityOrderService.class,
            new WEB3EquityOrderServiceInterceptor()); 
            
        //�i�A���j�����������t�������̓T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccEquityOrderBuyInputService.class,
            new WEB3EquityOrderBuyInputServiceInterceptor()); 
            
        //�i�A���j�����������t���̓T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccEquitySellOrderInputService.class,
            new WEB3EquitySellOrderInputServiceInterceptor());
            
        //�i�A���j������������T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccEquityCancelOrderService.class,
            new WEB3ToSuccEquityCancelOrderServiceInterceptor()); 
        
        //�i�A���j�������������������̓T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
            WEB3ToSuccEquityChangeOrderInputService.class,
            new WEB3EquityChangeOrderInputServiceInterceptor()); 
            
        //�i�A���j�������������T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccEquityChangeOrderService.class,
            new WEB3ToSuccEquityChangeOrderServiceInterceptor()); 
            
        //�i�A���j�M�p����������n�T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccMarginSwapMarginService.class,
            new WEB3MarginSwapMarginServiceInterceptor());
        
        //�i�A���j�M�p����������n���̓T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccMarginSwapMarginInputService.class,
            new WEB3MarginSwapMarginInputServiceInterceptor());
        
        //�i�A���j�M�p�������T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccMarginCancelService.class,
            new WEB3ToSuccMarginCancelServiceInterceptor());

        //�i�A���j�M�p����V�K���T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccMarginOpenMarginService.class,
            new WEB3MarginOpenMarginServiceInterceptor());
        
        //�i�A���j�M�p����V�K�����̓T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccMarginOpenMarginInputService.class,
            new WEB3MarginOpenMarginInputServiceInterceptor());
        
        //�i�A���j�M�p��������V�K���T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccMarginChangeOpenMarginService.class,
            new WEB3ToSuccMarginChangeOpenMarginServiceInterceptor());
        
        //�i�A���j�M�p��������V�K�����̓T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccMarginChangeOpenMarginInputService.class,
            new WEB3MarginChangeOpenMarginInputServiceInterceptor());
        
        //�i�A���j�M�p��������ԍσT�[�r�X
        Services.addInterceptor(
            WEB3ToSuccMarginChangeCloseMarginService.class,
            new WEB3ToSuccMarginChangeCloseMarginServiceInterceptor());
        
        //�i�A���j�M�p��������ԍϓ��̓T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccMarginChangeCloseMarginInputService.class,
            new WEB3MarginChangeCloseMarginInputServiceInterceptor());
        
        //�i�A���j�M�p����ԍσT�[�r�X
        Services.addInterceptor(
            WEB3ToSuccMarginCloseMarginService.class,
            new WEB3MarginCloseMarginServiceInterceptor());
        
        //�i�A���j�M�p����ԍϓ��̓T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccMarginCloseMarginInputService.class,
            new WEB3MarginCloseMarginInputServiceInterceptor());
        
        //�i�t�w�l�����敨OP�����ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3ToStopIfoOrderUnitService.class,
            new WEB3ToStopIfoOrderUnitServiceInterceptor());
            
        //�敨OP�蓮����UnitService
        Services.addInterceptor(
            WEB3ToIfoManualOrderUnitService.class,
            new WEB3ToIfoManualOrderUnitServiceInterceptor());
        
        //�����A�������蓮�������C���T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccEquityManualOrderMainService.class,
            new WEB3ToEquityManualOrderMainServiceInterceptor());
        
        //�����t�w�l�����蓮�������C���T�[�r�X
        Services.addInterceptor(
            WEB3ToStopEquityManualOrderMainService.class,
            new WEB3ToEquityManualOrderMainServiceInterceptor());

        //�����A�������蓮����UnitService
        Services.addInterceptor(
            WEB3ToSuccEquityManualOrderUnitService.class,
            new WEB3ToEquityManualOrderUnitServiceInterceptor());
        
        //�����t�w�l�����蓮����UnitService
        Services.addInterceptor(
            WEB3ToStopEquityManualOrderUnitService.class,
            new WEB3ToEquityManualOrderUnitServiceInterceptor());

        //W�w�l�����ؑ֏����T�[�r�X
        Services.addInterceptor(
            WEB3ToWLimitSwitchService.class,
            new WEB3ToWLimitSwitchServiceInterceptor());

        //W�w�l�����敨OP�ؑֈꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3ToWLimitIfoSwitchUnitService.class,
            new WEB3ToWLimitIfoSwitchUnitServiceInterceptor());

        //W�w�l�������������ؑֈꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3ToWLimitEquitySwitchUnitService.class,
            new WEB3ToWLimitEqTypeSwitchUnitServiceInterceptor());

        //W�w�l�����M�p����ԍϐؑֈꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3ToWLimitMarginCloseMarginSwitchUnitService.class,
            new WEB3ToWLimitEqTypeSwitchUnitServiceInterceptor());

        //W�w�l�����M�p����V�K���ؑֈꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3ToWLimitMarginOpenMarginSwitchUnitService.class,
            new WEB3ToWLimitEqTypeSwitchUnitServiceInterceptor());

        //����W�w�l�����蓮�ؑփ��C���T�[�r�X
        Services.addInterceptor(
            WEB3ToWLimitEquityManualSwitchMainService.class,
            new WEB3ToEquityManualOrderMainServiceInterceptor());

        //����W�w�l�����蓮�ؑ�UnitService
        Services.addInterceptor(
            WEB3ToWLimitEquityManualSwitchUnitService.class,
            new WEB3ToEquityManualOrderUnitServiceInterceptor());
        
        //�i�A���j�敨�����V�K���T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccFuturesChangeOpenContractService.class,
            new WEB3ToSuccFuturesChangeOpenContractServiceInterceptor());

        //�i�A���j�敨�����V�K�����̓T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccFuturesChangeOpenContractInputService.class,
            new WEB3ToSuccFuturesChangeOpenContractInputServiceInterceptor());

        //�i�A���j�敨�����ԍσT�[�r�X
        Services.addInterceptor(
            WEB3ToSuccFuturesChangeClosingContractService.class,
            new WEB3ToSuccFuturesChangeClosingContractServiceInterceptor());

        //�i�A���j�敨��������T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccFuturesCancelOrderService.class,
            new WEB3ToSuccFuturesCancelOrderServiceInterceptor());

        //�i�A���j�敨�V�K�������T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccFuturesOpenContractService.class,
            new WEB3FuturesOpenContractServiceInterceptor());

        //�i�A���j�敨�V�K�����̓T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccFuturesOpenContractInputService.class,
            new WEB3FuturesOpenContractInputServiceInterceptor());

        //�i�A���j�敨�����ԍϓ��̓T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccFuturesChangeClosingContractInputService.class,
            new WEB3ToSuccFuturesChangeClosingContractInputServiceInterceptor());

        //�i�A���j�敨�ԍϒ����T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccFuturesSettleContractOrderService.class,
            new WEB3FuturesSettleContractOrderServiceInterceptor());

        //�i�A���j�敨�ԍϓ��̓T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccFuturesSettleContractInputService.class,
            new WEB3FuturesSettleContractInputServiceInterceptor());
        
        //�i�A���jOP��������T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccOptionCancelOrderService.class,
            new WEB3ToSuccOptionCancelOrderServiceInterceptor());

        //�i�A���j�I�v�V�����V�K�������T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccOptionOpenContractOrderService.class,
            new WEB3OptionOpenContractOrderServiceInterceptor());

        //�i�A���jOP�V�K�����̓T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccOptionOpenContractInputService.class,
            new WEB3OptionOpenContractInputServiceInterceptor());

        //�i�A���jOP�����V�K���T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccOptionChangeOpenContractService.class,
            new WEB3ToSuccOptionChangeOpenContractServiceInterceptor());

        //�i�A���jOP�����V�K�����̓T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccOptionChangeOpenContractInputService.class,
            new WEB3ToSuccOptionChangeOpenContractInputServiceInterceptor());

        //�i�A���jOP�����ԍσT�[�r�X
        Services.addInterceptor(
            WEB3ToSuccOptionChangeClosingContractService.class,
            new WEB3ToSuccOptionChangeClosingContractServiceInterceptor());

        //�i�A���jOP�����ԍϓ��̓T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccOptionChangeClosingContractInputService.class,
            new WEB3ToSuccOptionChangeClosingContractInputServiceInterceptor());

        //�i�A���jOP�ԍϒ����T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccOptionSettleContractOrderService.class,
            new WEB3OptionSettleContractOrderServiceInterceptor());

        //�i�A���jOP�ԍϓ��̓T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccOptionSettleContractInputService.class,
            new WEB3OptionSettleContractInputServiceInterceptor());

        //OP�����J�z�T�[�r�X�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3OptionOrderCarryOverUnitService.class,
            new WEB3OptionOrderCarryOverUnitServiceInterceptor());

        //OP�����J�z�T�[�r�X
        Services.addInterceptor(
            WEB3OptionOrderCarryOverService.class,
            new WEB3IfoOrderCarryOverMainServiceInterceptor());

        //�敨�����J�z�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3FuturesOrderCarryOverUnitService.class,
            new WEB3FuturesOrderCarryOverUnitServiceInterceptor());
        //�敨�����J�z�T�[�r�X
        Services.addInterceptor(
            WEB3FuturesOrderCarryOverService.class,
            new WEB3IfoOrderCarryOverMainServiceInterceptor());

        //�A�������敨OP�����ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccIfoOrderUnitService.class,
            new WEB3ToSuccIfoOrderUnitServiceInterceptor());

        // Service �� Interceptor �ݒ菈�� ----------------------
        // �����g�����U�N�V�����ݒ�
        //�ݒ���e�m�F�T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccSettingContentsConfirmService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //�ǉ����e�I���T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccAdditionalContentsSelectService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));            
            
        //�A���ݒ�ꗗ�T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccSettingListService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));            
            
        //�A�������f�[�^�擾�T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccDataGettingService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));     
        
        //�A���������������ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccEquityOrderUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
            
        //�A�����������T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccOrderService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));
            
        //�t�w�l�������������ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3ToStopEquityOrderUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));
            
        //�t�w�l���������T�[�r�X 
        Services.addInterceptor(
            WEB3ToStopOrderService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
            
        //�i�A���j���������T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccEquityOrderService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING)); 
            
        //�i�A���j�����������t�������̓T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccEquityOrderBuyInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING)); 
            
        //�i�A���j�����������t���̓T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccEquitySellOrderInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING)); 
        
        //�i�A���j������������T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccEquityCancelOrderService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING)); 
        
        //�i�A���j�������������������̓T�[�r�X�C���^�t�F�[�X
        Services.addInterceptor(
            WEB3ToSuccEquityChangeOrderInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING)); 
            
        //�i�A���j�������������T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccEquityChangeOrderService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING)); 
                
        //�i�A���j�M�p����������n�T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccMarginSwapMarginService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //�i�A���j�M�p����������n���̓T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccMarginSwapMarginInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //�i�A���j�M�p�������T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccMarginCancelService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�i�A���j�M�p����V�K���T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccMarginOpenMarginService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //�i�A���j�M�p����V�K�����̓T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccMarginOpenMarginInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //�i�A���j�M�p��������V�K���T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccMarginChangeOpenMarginService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //�i�A���j�M�p��������V�K�����̓T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccMarginChangeOpenMarginInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //�i�A���j�M�p��������ԍσT�[�r�X
        Services.addInterceptor(
            WEB3ToSuccMarginChangeCloseMarginService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //�i�A���j�M�p��������ԍϓ��̓T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccMarginChangeCloseMarginInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //�i�A���j�M�p����ԍσT�[�r�X
        Services.addInterceptor(
            WEB3ToSuccMarginCloseMarginService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //�i�A���j�M�p����ԍϓ��̓T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccMarginCloseMarginInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�i�t�w�l�����敨OP�����ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3ToStopIfoOrderUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));
            
        //�敨OP�蓮����UnitService
        Services.addInterceptor(
            WEB3ToIfoManualOrderUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));
        
        //�����A�������蓮����UnitService
        Services.addInterceptor(
            WEB3ToSuccEquityManualOrderUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));
        //�����t�w�l�����蓮����UnitService
        Services.addInterceptor(
                WEB3ToStopEquityManualOrderUnitService.class,
                new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));
           
        //W�w�l�����ؑ֏����T�[�r�X
        Services.addInterceptor(
            WEB3ToWLimitSwitchService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //W�w�l�����敨OP�ؑֈꌏ�T�[�r�X 
        Services.addInterceptor(
            WEB3ToWLimitIfoSwitchUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //����W�w�l�����蓮�ؑ�UnitService
        Services.addInterceptor(
            WEB3ToWLimitEquityManualSwitchUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //W�w�l�������������ؑֈꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3ToWLimitEquitySwitchUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //W�w�l�����M�p����ԍϐؑֈꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3ToWLimitMarginCloseMarginSwitchUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //W�w�l�����M�p����V�K���ؑֈꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3ToWLimitMarginOpenMarginSwitchUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //�i�A���j�敨�����V�K���T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccFuturesChangeOpenContractService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�i�A���j�敨�����V�K�����̓T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccFuturesChangeOpenContractInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�i�A���j�敨�����ԍσT�[�r�X
        Services.addInterceptor(
            WEB3ToSuccFuturesChangeClosingContractService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�i�A���j�敨��������T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccFuturesCancelOrderService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�i�A���j�敨�V�K�������T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccFuturesOpenContractService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�i�A���j�敨�V�K�����̓T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccFuturesOpenContractInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�i�A���j�敨�����ԍϓ��̓T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccFuturesChangeClosingContractInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�i�A���j�敨�ԍϒ����T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccFuturesSettleContractOrderService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�i�A���j�敨�ԍϓ��̓T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccFuturesSettleContractInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�i�A���jOP��������T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccOptionCancelOrderService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�i�A���j�I�v�V�����V�K�������T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccOptionOpenContractOrderService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�i�A���jOP�V�K�����̓T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccOptionOpenContractInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�i�A���jOP�����V�K���T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccOptionChangeOpenContractService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�i�A���jOP�����V�K�����̓T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccOptionChangeOpenContractInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�i�A���jOP�����ԍσT�[�r�X
        Services.addInterceptor(
            WEB3ToSuccOptionChangeClosingContractService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�i�A���jOP�����ԍϓ��̓T�[�r�X 
        Services.addInterceptor(
            WEB3ToSuccOptionChangeClosingContractInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�i�A���jOP�ԍϒ����T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccOptionSettleContractOrderService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�i�A���jOP�ԍϓ��̓T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccOptionSettleContractInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //OP�����J�z�T�[�r�X
        Services.addInterceptor(
            WEB3OptionOrderCarryOverService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //�敨OP�A�������J�z�T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccIfoOrderCarryOverService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //OP�����J�z�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3OptionOrderCarryOverUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�敨�����J�z�T�[�r�X
        Services.addInterceptor(
            WEB3FuturesOrderCarryOverService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //�敨�����J�z�ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3FuturesOrderCarryOverUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //�A�������敨OP�����ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccIfoOrderUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // MQGatewayInterceptor�̐ݒ� ---------------
        //�A�����������T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccOrderService.class,
            new WEB3MQGatewayInterceptor());
            
        //�t�w�l�������������ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3ToStopEquityOrderUnitService.class,
            new WEB3MQGatewayInterceptor());
            
        //�t�w�l�����敨OP�����ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3ToStopIfoOrderUnitService.class,
            new WEB3MQGatewayInterceptor());
            
        //�i�A���j�M�p����������n�T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccMarginSwapMarginService.class,
            new WEB3MQGatewayInterceptor());
        
        //�i�A���j�M�p����V�K���T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccMarginOpenMarginService.class,
            new WEB3MQGatewayInterceptor());
        
        //�i�A���j�M�p��������V�K���T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccMarginChangeOpenMarginService.class,
            new WEB3MQGatewayInterceptor());
        
        //�i�A���j�M�p��������ԍσT�[�r�X
        Services.addInterceptor(
            WEB3ToSuccMarginChangeCloseMarginService.class,
            new WEB3MQGatewayInterceptor());
        
        //�i�A���j�M�p����ԍσT�[�r�X
        Services.addInterceptor(
            WEB3ToSuccMarginCloseMarginService.class,
            new WEB3MQGatewayInterceptor());

        //�@@W�w�l�����敨OP�ؑֈꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3ToWLimitIfoSwitchUnitService.class,
            new WEB3MQGatewayInterceptor());

        //W�w�l�������������ؑֈꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3ToWLimitEquitySwitchUnitService.class,
            new WEB3MQGatewayInterceptor());

        //W�w�l�����M�p����V�K���ؑֈꌏ�T�[�r�XImpl
        Services.addInterceptor(
            WEB3ToWLimitMarginOpenMarginSwitchUnitService.class,
            new WEB3MQGatewayInterceptor());

        //W�w�l�����M�p����ԍϐؑֈꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3ToWLimitMarginCloseMarginSwitchUnitService.class,
            new WEB3MQGatewayInterceptor());

        //�i�A���j�敨�����V�K���T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccFuturesChangeOpenContractService.class,
            new WEB3MQGatewayInterceptor());

        //�i�A���j�敨�����V�K�����̓T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccFuturesChangeOpenContractInputService.class,
            new WEB3MQGatewayInterceptor());

        //�i�A���j�敨�����ԍσT�[�r�X
        Services.addInterceptor(
            WEB3ToSuccFuturesChangeClosingContractService.class,
            new WEB3MQGatewayInterceptor());

        //�i�A���j�敨��������T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccFuturesCancelOrderService.class,
            new WEB3MQGatewayInterceptor());

        //�i�A���j�敨�V�K�������T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccFuturesOpenContractService.class,
            new WEB3MQGatewayInterceptor());

        //�i�A���j�敨�V�K�����̓T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccFuturesOpenContractInputService.class,
            new WEB3MQGatewayInterceptor());

        //�i�A���j�敨�����ԍϓ��̓T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccFuturesChangeClosingContractInputService.class,
            new WEB3MQGatewayInterceptor());

        //�i�A���j�敨�ԍϒ����T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccFuturesSettleContractOrderService.class,
            new WEB3MQGatewayInterceptor());

        //�i�A���j�敨�ԍϓ��̓T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccFuturesSettleContractInputService.class,
            new WEB3MQGatewayInterceptor());

        //�i�A���jOP��������T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccOptionCancelOrderService.class,
            new WEB3MQGatewayInterceptor());

        //�i�A���j�I�v�V�����V�K�������T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccOptionOpenContractOrderService.class,
            new WEB3MQGatewayInterceptor());

        //�i�A���jOP�V�K�����̓T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccOptionOpenContractInputService.class,
            new WEB3MQGatewayInterceptor());

        //�i�A���jOP�����V�K���T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccOptionChangeOpenContractService.class,
            new WEB3MQGatewayInterceptor());

        //�i�A���jOP�����V�K�����̓T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccOptionChangeOpenContractInputService.class,
            new WEB3MQGatewayInterceptor());

        //�i�A���jOP�����ԍσT�[�r�X
        Services.addInterceptor(
            WEB3ToSuccOptionChangeClosingContractService.class,
            new WEB3MQGatewayInterceptor());

        //�i�A���jOP�����ԍϓ��̓T�[�r�X 
        Services.addInterceptor(
            WEB3ToSuccOptionChangeClosingContractInputService.class,
            new WEB3MQGatewayInterceptor());

        //�i�A���jOP�ԍϒ����T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccOptionSettleContractOrderService.class,
            new WEB3MQGatewayInterceptor());

        //�i�A���jOP�ԍϓ��̓T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccOptionSettleContractInputService.class,
            new WEB3MQGatewayInterceptor());

        //OP�����J�z�T�[�r�X
        Services.addInterceptor(
            WEB3OptionOrderCarryOverService.class,
            new WEB3MQGatewayInterceptor());
       
        //�敨OP�A�������J�z�T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccIfoOrderCarryOverService.class,
            new WEB3MQGatewayInterceptor());

        //�敨�����J�z�T�[�r�X
        Services.addInterceptor(
            WEB3FuturesOrderCarryOverService.class,
            new WEB3MQGatewayInterceptor());

        //�A�������敨OP�����ꌏ�T�[�r�X
        Services.addInterceptor(
            WEB3ToSuccIfoOrderUnitService.class,
            new WEB3MQGatewayInterceptor());

        //Message �̓o�^���� ----------------------
        //�A���ݒ�ꗗ���N�G�X�g
        regClass(WEB3SuccSettingListRequest.class);
        
        //�A���ݒ�ꗗ���X�|���X
        regClass(WEB3SuccSettingListResponse.class);
        
        //�ݒ���e�m�F���N�G�X�g
        regClass(WEB3SuccSettingContentConfirmRequest.class);
        
        //�ݒ���e�m�F���X�|���X
        regClass(WEB3SuccSettingContentConfirmResponse.class);
        
        //�ǉ����e�I�����N�G�X�g
        regClass(WEB3SuccAdditionalContentSelectRequest.class);
        
        //�ǉ����e�I�����X�|���X
        regClass(WEB3SuccAdditionalContentSelectResponse.class);
        
        //�i�A���j�����������t�������̓��N�G�X�g
        regClass(WEB3SuccEquityBuyInputRequest.class);
                 
        //�i�A���j�����������t�������̓��X�|���X         
        regClass(WEB3SuccEquityBuyInputResponse.class);
        
        //�i�A���j�����������t�������̓��N�G�X�g         
        regClass(WEB3SuccEquitySellInputRequest.class);
        
        //�i�A���j�����������t�������̓��X�|���X         
        regClass(WEB3SuccEquitySellInputResponse.class);
        
        //�i�A���j�����������t�����m�F���N�G�X�g         
        regClass(WEB3SuccEquityBuyConfirmRequest.class);
        
        //�i�A���j�����������t�����m�F���X�|���X         
        regClass(WEB3SuccEquityBuyConfirmResponse.class);
        
        //�i�A���j�����������t�����m�F���N�G�X�g         
        regClass(WEB3SuccEquitySellConfirmRequest.class);
        
        //�i�A���j�����������t�����m�F���X�|���X         
        regClass(WEB3SuccEquitySellConfirmResponse.class);
        
        //�i�A���j�����������t�����������N�G�X�g         
        regClass(WEB3SuccEquityBuyCompleteRequest.class);
        
        //�i�A���j�����������t�����������X�|���X         
        regClass(WEB3SuccEquityBuyCompleteResponse.class);
        
        //�i�A���j�����������t�����������N�G�X�g         
        regClass(WEB3SuccEquitySellCompleteRequest.class);
        
        //�i�A���j�����������t�����������X�|���X
        regClass(WEB3SuccEquitySellCompleteResponse.class);           
        
        //�i�A���j����������������m�F���N�G�X�g
        regClass(WEB3SuccEquityCancelConfirmRequest.class);      
        
        //�i�A���j����������������m�F���X�|���X
        regClass(WEB3SuccEquityCancelConfirmResponse.class);
        
        //�i�A���j����������������������N�G�X�g  
        regClass(WEB3SuccEquityCancelCompleteRequest.class);
           
        //�i�A���j����������������������X�|���X
        regClass(WEB3SuccEquityCancelCompleteResponse.class);

        //�i�A���j�������������������̓��N�G�X�g
        regClass(WEB3SuccEquityChangeInputRequest.class);
        
        //�i�A���j�������������������̓��X�|���X
        regClass(WEB3SuccEquityChangeInputResponse.class);
                
        //�i�A���j�����������������m�F���N�G�X�g
        regClass(WEB3SuccEquityChangeConfirmRequest.class);
        
        //�i�A���j�����������������m�F���X�|���X
        regClass(WEB3SuccEquityChangeConfirmResponse.class);
        
        //�i�A���j�����������������������N�G�X�g
        regClass(WEB3SuccEquityChangeCompleteRequest.class);
        
        //�i�A���j�����������������������X�|���X 
        regClass(WEB3SuccEquityChangeCompleteResponse.class);        
        
        //�i�A���j�M�p����V�K���������̓��N�G�X�g
        regClass(WEB3SuccMarginOpenInputRequest.class);
        
        //�i�A���j�M�p����V�K���������̓��X�|���X
        regClass(WEB3SuccMarginOpenInputResponse.class);
        
        //�i�A���j�M�p����V�K�������m�F���N�G�X�g
        regClass(WEB3SuccMarginOpenConfirmRequest.class);
    
        //�i�A���j�M�p����V�K�������m�F���X�|���X
        regClass(WEB3SuccMarginOpenConfirmResponse.class);
        
        //�i�A���j�M�p����V�K�������������N�G�X�g
        regClass(WEB3SuccMarginOpenCompleteRequest.class);
        
        //�i�A���j�M�p����V�K�������������X�|���X
        regClass(WEB3SuccMarginOpenCompleteResponse.class);
        
        //�i�A���j�M�p����ԍϒ������̓��N�G�X�g
        regClass(WEB3SuccMarginCloseInputRequest.class);
        
        //�i�A���j�M�p����ԍϒ������̓��X�|���X
        regClass(WEB3SuccMarginCloseInputResponse.class);
        
        //�i�A���j�M�p����ԍϒ����m�F���N�G�X�g
        regClass(WEB3SuccMarginCloseConfirmRequest.class);
        
        // �i�A���j�M�p����ԍϒ����m�F���X�|���X
        regClass(WEB3SuccMarginCloseConfirmResponse.class);
        
        //�i�A���j�M�p����ԍϒ����������N�G�X�g
        regClass(WEB3SuccMarginCloseCompleteRequest.class);
        
        //�i�A���j�M�p����ԍϒ����������X�|���X
        regClass(WEB3SuccMarginCloseCompleteResponse.class);
        
        //�i�A���j�M�p����������n�������̓��N�G�X�g
        regClass(WEB3SuccMarginSwapInputRequest.class);
        
        //�i�A���j�M�p����������n�������̓��X�|���X
        regClass(WEB3SuccMarginSwapInputResponse.class);
        
        //�i�A���j�M�p����������n�����m�F���N�G�X�g
        regClass(WEB3SuccMarginSwapConfirmRequest.class);
        
        //�i�A���j�M�p����������n�����m�F���X�|���X
        regClass(WEB3SuccMarginSwapConfirmResponse.class);
        
        //�i�A���j�M�p����������n�����������N�G�X�g
        regClass(WEB3SuccMarginSwapCompleteRequest.class);
        
        //�i�A���j�M�p����������n�����������X�|���X
        regClass(WEB3SuccMarginSwapCompleteResponse.class);
        
        //�i�A���j�M�p��������V�K�����̓��N�G�X�g
        regClass(WEB3SuccMarginOpenChangeInputRequest.class);
        
        //�i�A���j�M�p��������V�K�����̓��X�|���X
        regClass(WEB3SuccMarginOpenChangeInputResponse.class);
        
        //�i�A���j�M�p������������V�K���m�F���N�G�X�g
        regClass(WEB3SuccMarginOpenChangeConfirmRequest.class);
        
        //�i�A���j�M�p������������V�K���m�F���X�|���X
        regClass(WEB3SuccMarginOpenChangeConfirmResponse.class);
        
        //�i�A���j�M�p������������V�K���������N�G�X�g
        regClass(WEB3SuccMarginOpenChangeCompleteRequest.class);
        
        //�i�A���j�M�p������������V�K���������X�|���X
        regClass(WEB3SuccMarginOpenChangeCompleteResponse.class);
        
        //�i�A���j�M�p��������ԍϓ��̓��N�G�X�g
        regClass(WEB3SuccMarginCloseChangeInputRequest.class);
        
        //�i�A���j�M�p��������ԍϓ��̓��X�|���X
        regClass(WEB3SuccMarginCloseChangeInputResponse.class);
        
        //�i�A���j�M�p������������ԍϊm�F���N�G�X�g
        regClass(WEB3SuccMarginCloseChangeConfirmRequest.class);
        
        //�i�A���j�M�p������������ԍϊm�F���X�|���X
        regClass(WEB3SuccMarginCloseChangeConfirmResponse.class);
        
        //�i�A���j�M�p������������ԍϊ������N�G�X�g
        regClass(WEB3SuccMarginCloseChangeCompleteRequest.class);
        
        //�i�A���j�M�p������������ԍϊ������X�|���X
        regClass(WEB3SuccMarginCloseChangeCompleteResponse.class);
        
        //�i�A���j�M�p�����������m�F���N�G�X�g
        regClass(WEB3SuccMarginCancelConfirmRequest.class);
        
        //�i�A���j�M�p�����������m�F���X�|���X
        regClass(WEB3SuccMarginCancelConfirmResponse.class);
        
        //�i�A���j�M�p�����������������N�G�X�g
        regClass(WEB3SuccMarginCancelCompleteRequest.class);
        
        //�i�A���j�M�p�����������������X�|���X
        regClass(WEB3SuccMarginCancelCompleteResponse.class);

        //�敨OP�蓮�����m�F���N�G�X�g
        regClass(WEB3FuturesOptionsManualConfirmRequest.class);
        
        //�敨OP�蓮�����m�F���X�|���X
        regClass(WEB3FuturesOptionsManualConfirmResponse.class);
        
        //�敨OP�蓮�����������N�G�X�g
        regClass(WEB3FuturesOptionsManualCompleteRequest.class);
        
        //�敨OP�蓮�����������X�|���X
        regClass(WEB3FuturesOptionsManualCompleteResponse.class);
        
        //�����蓮�������ʃ��X�|���X
        regClass(WEB3EquityManualCommonResponse.class);
        
        //�����t�w�l�����蓮�����m�F���N�G�X�g
        regClass(WEB3EquityStopManualConfirmRequest.class);
        
        //�����t�w�l�����蓮�����������N�G�X�g
        regClass(WEB3EquityStopManualCompleteRequest.class);
        
        //�����蓮�����m�F���N�G�X�g
        regClass(WEB3EquityManualConfirmRequest.class);
        
        //�����蓮�����m�F���X�|���X
        regClass(WEB3EquityManualConfirmResponse.class);
        
        //�����蓮�����������N�G�X�g
        regClass(WEB3EquityManualCompleteRequest.class);
        
        //�����蓮�����������X�|���X
        regClass(WEB3EquityManualCompleteResponse.class);
        
        //�����A�������蓮�����m�F���N�G�X�g
        regClass(WEB3EquitySuccManualConfirmRequest.class);
        
        //�����A�������蓮�����������N�G�X�g
        regClass(WEB3EquitySuccManualCompleteRequest.class);

        //����W�w�l�����蓮�ؑ֊m�F���N�G�X�g
        regClass(WEB3EquityWLimitManualConfirmRequest.class);

        //����W�w�l�����蓮�ؑ֊������N�G�X�g
        regClass(WEB3EquityWLimitManualCompleteRequest.class);

        //�i�A���j�����w���敨��������m�F���N�G�X�g
        regClass(WEB3SuccFuturesCancelConfirmRequest.class);

        //�i�A���j�����w���敨��������m�F���X�|���X
        regClass(WEB3SuccFuturesCancelConfirmResponse.class);

        //�i�A���j�����w���敨��������������N�G�X�g
        regClass(WEB3SuccFuturesCancelCompleteRequest.class);

        //�i�A���j�����w���敨��������������X�|���X
        regClass(WEB3SuccFuturesCancelCompleteResponse.class);

        //�i�A���j�����w���敨�V�K�������m�F���N�G�X�g
        regClass(WEB3SuccFuturesOpenConfirmRequest.class);

        //�i�A���j�����w���敨�V�K�������m�F���X�|���X
        regClass(WEB3SuccFuturesOpenConfirmResponse.class);

        //�i�A���j�����w���敨�V�K�������������N�G�X�g
        regClass(WEB3SuccFuturesOpenCompleteRequest.class);

        //�i�A���j�����w���敨�V�K�������������X�|���X
        regClass(WEB3SuccFuturesOpenCompleteResponse.class);

        //�i�A���j�����w���敨�V�K���������͉�ʃ��N�G�X�g
        regClass(WEB3SuccFuturesOpenInputRequest.class);

        //�i�A���j�����w���敨�V�K���������͉�ʃ��X�|���X
        regClass(WEB3SuccFuturesOpenInputResponse.class);

        //�i�A���j�����w���敨�����V�K���m�F���N�G�X�g
        regClass(WEB3SuccFuturesOpenChangeConfirmRequest.class);

        //�i�A���j�����w���敨�����V�K���m�F���X�|���X
        regClass(WEB3SuccFuturesOpenChangeConfirmResponse.class);

        //�i�A���j�����w���敨�����V�K���������N�G�X�g
        regClass(WEB3SuccFuturesOpenChangeCompleteRequest.class);

        //�i�A���j�����w���敨�����V�K���������X�|���X
        regClass(WEB3SuccFuturesOpenChangeCompleteResponse.class);

        //�i�A���j�����w���敨�����V�K�����͉�ʃ��N�G�X�g
        regClass(WEB3SuccFuturesOpenChangeInputRequest.class);

        //�i�A���j�����w���敨�����V�K�����͉�ʃ��X�|���X
        regClass(WEB3SuccFuturesOpenChangeInputResponse.class);

        //�i�A���j�����w���敨�����ԍϊm�F���N�G�X�g
        regClass(WEB3SuccFuturesCloseChangeInputRequest.class);

        //�i�A���j�����w���敨�����ԍϊm�F���X�|���X
        regClass(WEB3SuccFuturesCloseChangeInputResponse.class);

        //�i�A���j�����w���敨�����ԍϊ������N�G�X�g
        regClass(WEB3SuccFuturesCloseChangeConfirmRequest.class);

        //�i�A���j�����w���敨�����ԍϊ������X�|���X
        regClass(WEB3SuccFuturesCloseChangeConfirmResponse.class);

        //�i�A���j�����w���敨�����ԍϓ��͉�ʃ��N�G�X�g
        regClass(WEB3SuccFuturesCloseChangeCompleteRequest.class);

        //�i�A���j�����w���敨�����ԍϓ��͉�ʃ��X�|���X
        regClass(WEB3SuccFuturesCloseChangeCompleteResponse.class);

        //�i�A���j�����w���敨�ԍϒ����m�F���N�G�X�g
        regClass(WEB3SuccFuturesCloseConfirmRequest.class);

        //�i�A���j�����w���敨�ԍϒ����m�F���X�|���X
        regClass(WEB3SuccFuturesCloseConfirmResponse.class);

        //�i�A���j�����w���敨�ԍϒ����������N�G�X�g
        regClass(WEB3SuccFuturesCloseCompleteRequest.class);

        //�i�A���j�����w���敨�ԍϒ����������X�|���X
        regClass(WEB3SuccFuturesCloseCompleteResponse.class);

        //�i�A���j�����w���敨�ԍϓ��͉�ʃ��N�G�X�g
        regClass(WEB3SuccFuturesCloseInputRequest.class);

        //�i�A���j�����w���敨�ԍϓ��͉�ʃ��X�|���X
        regClass(WEB3SuccFuturesCloseInputResponse.class);

        //�����w��OP�����J�z���N�G�X�g
        regClass(WEB3OptionsOrderCarryOverRequest.class);
        //�����w��OP�����J�z���X�|���X
        regClass(WEB3OptionsOrderCarryOverResponse.class);

        //�����w���敨�����J�z���N�G�X�g
        regClass(WEB3FuturesOrderCarryOverRequest.class);
        //�����w���敨�����J�z���X�|���X
        regClass(WEB3FuturesOrderCarryOverResponse.class);
        
        //�i�A���j�����w���I�v�V������������m�F���N�G�X�g
        regClass(WEB3SuccOptionsCancelConfirmRequest.class);
        //�i�A���j�����w���I�v�V������������m�F���X�|���X
        regClass(WEB3SuccOptionsCancelConfirmResponse.class);

        //�i�A���j�����w���I�v�V������������������N�G�X�g
        regClass(WEB3SuccOptionsCancelCompleteRequest.class);
        //�i�A���j�����w���I�v�V������������������X�|���X
        regClass(WEB3SuccOptionsCancelCompleteResponse.class);

        //�i�A���j�����w���I�v�V�����V�K�������m�F���N�G�X�g
        regClass(WEB3SuccOptionsOpenConfirmRequest.class);
        //�i�A���j�����w���I�v�V�����V�K�������m�F���X�|���X
        regClass(WEB3SuccOptionsOpenConfirmResponse.class);

        //�i�A���j�����w���I�v�V�����V�K�������������N�G�X�g
        regClass(WEB3SuccOptionsOpenCompleteRequest.class);
        //�i�A���j�����w���I�v�V�����V�K�������������X�|���X
        regClass(WEB3SuccOptionsOpenCompleteResponse.class);

        //�i�A���j�����w���I�v�V�����V�K���������͉�ʃ��N�G�X�g
        regClass(WEB3SuccOptionsOpenInputRequest.class);
        //�i�A���j�����w���I�v�V�����V�K���������͉�ʃ��X�|���X
        regClass(WEB3SuccOptionsOpenInputResponse.class);

        //�i�A���j�����w���I�v�V���������V�K���m�F���N�G�X�g
        regClass(WEB3SuccOptionsOpenChangeConfirmRequest.class);
        //�i�A���j�����w���I�v�V���������V�K���m�F���X�|���X
        regClass(WEB3SuccOptionsOpenChangeConfirmResponse.class);

        //�i�A���j�����w���I�v�V���������V�K���������N�G�X�g
        regClass(WEB3SuccOptionsOpenChangeCompleteRequest.class);
        //�i�A���j�����w���I�v�V���������V�K���������X�|���X
        regClass(WEB3SuccOptionsOpenChangeCompleteResponse.class);

        //�i�A���j�����w���I�v�V���������V�K�����͉�ʃ��N�G�X�g
        regClass(WEB3SuccOptionsOpenChangeInputRequest.class);
        //�i�A���j�����w���I�v�V���������V�K�����͉�ʃ��X�|���X
        regClass(WEB3SuccOptionsOpenChangeInputResponse.class);

        //�i�A���j�����w���I�v�V���������ԍϊm�F���N�G�X�g
        regClass(WEB3SuccOptionsCloseChangeConfirmRequest.class);
        //�i�A���j�����w���I�v�V���������ԍϊm�F���X�|���X
        regClass(WEB3SuccOptionsCloseChangeConfirmResponse.class);

        //�i�A���j�����w���I�v�V���������ԍϊ������N�G�X�g
        regClass(WEB3SuccOptionsCloseChangeCompleteRequest.class);
        //�i�A���j�����w���I�v�V���������ԍϊ������X�|���X
        regClass(WEB3SuccOptionsCloseChangeCompleteResponse.class);

        //�i�A���j�����w���I�v�V���������ԍϓ��͉�ʃ��N�G�X�g
        regClass(WEB3SuccOptionsCloseChangeInputRequest.class);
        //�i�A���j�����w���I�v�V���������ԍϓ��͉�ʃ��X�|���X
        regClass(WEB3SuccOptionsCloseChangeInputResponse.class);

        //�i�A���j�����w���I�v�V�����ԍϒ����m�F���N�G�X�g
        regClass(WEB3SuccOptionsCloseConfirmRequest.class);
        //�i�A���j�����w���I�v�V�����ԍϒ����m�F���X�|���X
        regClass(WEB3SuccOptionsCloseConfirmResponse.class);

        //�i�A���j�����w���I�v�V�����ԍϒ����������N�G�X�g
        regClass(WEB3SuccOptionsCloseCompleteRequest.class);
        //�i�A���j�����w���I�v�V�����ԍϒ����������X�|���X
        regClass(WEB3SuccOptionsCloseCompleteResponse.class);

        //�i�A���j�����w���I�v�V�����ԍϓ��͉�ʃ��N�G�X�g
        regClass(WEB3SuccOptionsCloseInputRequest.class);
        //�i�A���j�����w���I�v�V�����ԍϓ��͉�ʃ��X�|���X
        regClass(WEB3SuccOptionsCloseInputResponse.class);

        //Handler �̓o�^����
        //�ݒ���e�m�F�n���h�� 
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccSettingContentConfirmRequest.class,
            WEB3ToSuccSettingContentsConfirmHandler.class,
            "getConfirmScreen");
            
        //�ǉ����e�I���n���h�� 
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccAdditionalContentSelectRequest.class,
            WEB3ToSuccAdditionalContentsSelectHandler.class,
            "getSelectScreen");
            
        //�A���ݒ�ꗗ�n���h�� 
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccSettingListRequest.class,
            WEB3ToSuccSettingListHandler.class,
            "getListScreen");
            
        //�i�A���j�����������t�������̓n���h��
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccEquityBuyInputRequest.class,
            WEB3ToSuccEquityOrderBuyInputHandler.class,
            "getInputScreen");
            
        //�i�A���j�����������t���̓n���h��
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccEquitySellInputRequest.class,
            WEB3ToSuccEquitySellOrderInputHandler.class,
            "getInputScreen");
        
        //�i�A���j���������n���h��
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccEquityBuyConfirmRequest.class,
            WEB3ToSuccEquityOrderHandler.class,
            "confirmBuyOrder");
            
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccEquityBuyCompleteRequest.class,
            WEB3ToSuccEquityOrderHandler.class,
            "completeBuyOrder");
            
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccEquitySellConfirmRequest.class,
            WEB3ToSuccEquityOrderHandler.class,
            "confirmSellOrder");
            
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccEquitySellCompleteRequest.class,
            WEB3ToSuccEquityOrderHandler.class,
            "completeSellOrder");                
                
        //�i�A���j������������n���h��
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccEquityCancelConfirmRequest.class,
            WEB3ToSuccEquityCancelOrderHandler.class,
            "confirmCancelOrder"); 
            
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccEquityCancelCompleteRequest.class,
            WEB3ToSuccEquityCancelOrderHandler.class,
            "completeCancelOrder");
        
        //�i�A���j�������������������̓n���h��
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccEquityChangeInputRequest.class,
            WEB3ToSuccEquityChangeOrderInputHandler.class,
            "getChangeInputScreen"); 
            
        //�i�A���j�������������n���h��
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccEquityChangeConfirmRequest.class,
            WEB3ToSuccEquityChangeOrderHandler.class,
            "confirmChangeOrder"); 
            
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccEquityChangeCompleteRequest.class,
            WEB3ToSuccEquityChangeOrderHandler.class,
            "completeChangeOrder"); 

        //�i�A���j�M�p����������n�n���h��
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccMarginSwapConfirmRequest.class,
            WEB3ToSuccMarginSwapMarginHandler.class,
            "confirmOrder");
             
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccMarginSwapCompleteRequest.class,
            WEB3ToSuccMarginSwapMarginHandler.class,
            "completeOrder");

        //�i�A���j�M�p����������n���̓n���h��
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccMarginSwapInputRequest.class,
            WEB3ToSuccMarginSwapMarginInputHandler.class,
            "getSwapMarginInputScreen");
        
        //�i�A���j�M�p�������n���h��
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccMarginCancelConfirmRequest.class,
            WEB3ToSuccMarginCancelHandler.class,
            "confirmCancel");
        
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccMarginCancelCompleteRequest.class,
            WEB3ToSuccMarginCancelHandler.class,
            "completeCancel");
        
        //�i�A���j�M�p����V�K���n���h��
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccMarginOpenConfirmRequest.class,
            WEB3ToSuccMarginOpenMarginHandler.class,
            "confirmOrder");
        
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccMarginOpenCompleteRequest.class,
            WEB3ToSuccMarginOpenMarginHandler.class,
            "completeOrder");
        
        //�i�A���j�M�p����V�K�����̓n���h��
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccMarginOpenInputRequest.class,
            WEB3ToSuccMarginOpenMarginInputHandler.class,
            "getOpenMarginInputScreen");
        
        //�i�A���j�M�p��������V�K���n���h��
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccMarginOpenChangeConfirmRequest.class,
            WEB3ToSuccMarginChangeOpenMarginHandler.class,
            "confirmOpenMarginChange");
        
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccMarginOpenChangeCompleteRequest.class,
            WEB3ToSuccMarginChangeOpenMarginHandler.class,
            "completeOpenMarginChange");
        
        //�i�A���j�M�p��������V�K�����̓n���h��
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccMarginOpenChangeInputRequest.class,
            WEB3ToSuccMarginChangeOpenMarginInputHandler.class,
            "getOpenMarginChangeInputScreen");
        
        //�i�A���j�M�p��������ԍσn���h��
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccMarginCloseChangeConfirmRequest.class,
            WEB3ToSuccMarginChangeCloseMarginHandler.class,
            "confirmCloseMarginChange");
        
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccMarginCloseChangeCompleteRequest.class,
            WEB3ToSuccMarginChangeCloseMarginHandler.class,
            "completeCloseMarginChange");
        
        //�i�A���j�M�p��������ԍϓ��̓n���h��
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccMarginCloseChangeInputRequest.class,
            WEB3ToSuccMarginChangeCloseMarginInputHandler.class,
            "getCloseMarginChangeInputScreen");
        
        //�i�A���j�M�p����ԍσn���h��
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccMarginCloseConfirmRequest.class,
            WEB3ToSuccMarginCloseMarginHandler.class,
            "confirmCloseMargin");

        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccMarginCloseCompleteRequest.class,
            WEB3ToSuccMarginCloseMarginHandler.class,
            "completeCloseMargin");
        
        //�i�A���j�M�p����ԍϓ��̓n���h��
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccMarginCloseInputRequest.class,
            WEB3ToSuccMarginCloseMarginInputHandler.class,
            "getCloseMarginChangeInputScreen");
                         
        //�敨OP�蓮�����n���h��
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3FuturesOptionsManualConfirmRequest.class,
            WEB3ToIfoManualOrderHandler.class,
            "confirmManual");
        
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3FuturesOptionsManualCompleteRequest.class,
            WEB3ToIfoManualOrderHandler.class,
            "completeManual");
        
        //�����t�w�l�����蓮�����n���h��
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3EquityStopManualConfirmRequest.class,
            WEB3ToStopEquityManualOrderHandler.class,
            "confirmManualOrder");
        
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3EquityStopManualCompleteRequest.class,
            WEB3ToStopEquityManualOrderHandler.class,
            "completeManualOrder");
        
        //�����A�������蓮�����n���h��
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3EquitySuccManualConfirmRequest.class,
            WEB3ToSuccEquityManualOrderHandler.class,
            "confirmManualOrder");
        
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3EquitySuccManualCompleteRequest.class,
            WEB3ToSuccEquityManualOrderHandler.class,
            "completeManualOrder");

        //����W�w�l�����蓮�ؑփn���h��
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3EquityWLimitManualConfirmRequest.class,
            WEB3ToWLimitEquityManualSwitchHandler.class,
            "confirmManualOrder");

        //����W�w�l�����蓮�ؑփn���h��
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3EquityWLimitManualCompleteRequest.class,
            WEB3ToWLimitEquityManualSwitchHandler.class,
            "completeManualOrder");

        //�i�A���j�敨�����V�K���n���h��
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccFuturesOpenChangeConfirmRequest.class,
            WEB3ToSuccFuturesChangeOpenContractHandler.class,
            "confirmChangeOpenContract");

        //�i�A���j�敨�����V�K���n���h��
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccFuturesOpenChangeCompleteRequest.class,
            WEB3ToSuccFuturesChangeOpenContractHandler.class,
            "completeChangeOpenContract");

        //�i�A���j�敨�����V�K�����̓n���h��
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccFuturesOpenChangeInputRequest.class,
            WEB3ToSuccFuturesChangeOpenContractInputHandler.class,
            "changeOpenContractInputRequest");

        //�i�A���j�敨�����ԍσn���h��
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccFuturesCloseChangeConfirmRequest.class,
            WEB3ToSuccFuturesChangeClosingContractHandler.class,
            "confirmChangeClosingContract");

        //�i�A���j�敨�����ԍσn���h��
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccFuturesCloseChangeCompleteRequest.class,
            WEB3ToSuccFuturesChangeClosingContractHandler.class,
            "completeChangeClosingContract");

        //�i�A���j�敨�����ԍϓ��̓n���h��
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccFuturesCloseChangeInputRequest.class,
            WEB3ToSuccFuturesChangeClosingContractInputHandler.class,
            "closeChangeInputRequest");

        //�i�A���j�敨�V�K�������n���h��
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccFuturesOpenConfirmRequest.class,
            WEB3ToSuccFuturesOpenContractHandler.class,
            "confirmOrder");

        //�i�A���j�敨�V�K�������n���h��
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccFuturesOpenCompleteRequest.class,
            WEB3ToSuccFuturesOpenContractHandler.class,
            "completeOrder");

        //�i�A���j�敨�V�K�����̓n���h��
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccFuturesOpenInputRequest.class,
            WEB3ToSuccFuturesOpenContractInputHandler.class,
            "openContractInputRequest");

        //�i�A���j�敨�ԍϒ����n���h��
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccFuturesCloseConfirmRequest.class,
            WEB3ToSuccFuturesSettleContractOrderHandler.class,
            "confirmSettleContract");

        //�i�A���j�敨�ԍϒ����n���h��
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccFuturesCloseCompleteRequest.class,
            WEB3ToSuccFuturesSettleContractOrderHandler.class,
            "completeSettleContract");

        //�i�A���j�敨�ԍϓ��̓n���h��
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccFuturesCloseInputRequest.class,
            WEB3ToSuccFuturesSettleContractInputHandler.class,
            "getSettleContractInputScreen");

        //�i�A���j�敨��������n���h��
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccFuturesCancelConfirmRequest.class,
            WEB3ToSuccFuturesCancelOrderHandler.class,
            "confirmCancel");

        //�i�A���j�敨��������n���h��
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccFuturesCancelCompleteRequest.class,
            WEB3ToSuccFuturesCancelOrderHandler.class,
            "completeCancel");
        
        // �i�A���jOP��������n���h�� 
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccOptionsCancelConfirmRequest.class,
            WEB3ToSuccOptionCancelOrderHandler.class,
            "confirmCancel");

        // �i�A���jOP��������n���h�� 
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccOptionsCancelCompleteRequest.class,
            WEB3ToSuccOptionCancelOrderHandler.class,
            "completeCancel");

        //�i�A���jOP�V�K�������n���h�� 
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccOptionsOpenConfirmRequest.class,
            WEB3ToSuccOptionOpenContractOrderHandler.class,
            "confirmOrder");
 
        //�i�A���jOP�V�K�������n���h�� 
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccOptionsOpenCompleteRequest.class,
            WEB3ToSuccOptionOpenContractOrderHandler.class,
            "completeOrder");

        //�i�A���jOP�V�K�����̓n���h�� 
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccOptionsOpenInputRequest.class,
            WEB3ToSuccOptionOpenContractInputHandler.class,
            "openContractInputRequest");

        //�i�A���jOP�����V�K���n���h��
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccOptionsOpenChangeConfirmRequest.class,
            WEB3ToSuccOptionChangeOpenContractHandler.class,
            "confirmChangeOpenContract");

        //�i�A���jOP�����V�K���n���h��
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccOptionsOpenChangeCompleteRequest.class,
            WEB3ToSuccOptionChangeOpenContractHandler.class,
            "completeChangeOpenContract");

        //�i�A���jOP�����V�K�����̓n���h��
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccOptionsOpenChangeInputRequest.class,
            WEB3ToSuccOptionChangeOpenContractInputHandler.class,
            "changeOpenContractInputRequest");

        //�i�A���jOP�����ԍσn���h��
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccOptionsCloseChangeConfirmRequest.class,
            WEB3ToSuccOptionChangeClosingContractHandler.class,
            "confirmChangeClosingContract");

        //�i�A���jOP�����ԍσn���h��
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccOptionsCloseChangeCompleteRequest.class,
            WEB3ToSuccOptionChangeClosingContractHandler.class,
            "completeChangeClosingContract");

        //�i�A���jOP�����ԍϓ��̓n���h��
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccOptionsCloseChangeInputRequest.class,
            WEB3ToSuccOptionChangeClosingContractInputHandler.class,
            "changeCloseInputRequest");

        //�i�A���jOP�ԍϒ����n���h��
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccOptionsCloseConfirmRequest.class,
            WEB3ToSuccOptionSettleContractHandler.class,
            "confirmSettleContract");

        //�i�A���jOP�ԍϒ����n���h��
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccOptionsCloseCompleteRequest.class,
            WEB3ToSuccOptionSettleContractHandler.class,
            "completeSettleContract");

        //�i�A���jOP�ԍϓ��̓n���h��
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccOptionsCloseInputRequest.class,
            WEB3ToSuccOptionSettleContractInputServiceHandler.class,
            "getSettleContractInputScreen");

        //OP�����J�z���N�G�X�g�p�n���h��
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3OptionsOrderCarryOverRequest.class,
            WEB3OptionOrderCarryOverHandler.class,
            "orderCarryOverRequest");

        //�敨�����J�z���N�G�X�g�p�n���h��
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3FuturesOrderCarryOverRequest.class,
            WEB3FuturesOrderCarryOverHandler.class,
            "orderCarryOverRequest");

        log.exiting(METHOD_NAME);
    }
}
@
