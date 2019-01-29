head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.53.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToStopOrderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �t�w�l���������T�[�r�XImpl(WEB3ToStopOrderServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/07 ������(���u) �V�K�쐬
                   2006/01/23 ������(�k�����u) �d�l�ύX�E���f��066
*/
package webbroker3.triggerorder.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityTradingModule;
import webbroker3.ifo.WEB3IfoTradingModule;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.triggerorder.service.delegate.WEB3ToStopEquityOrderUnitService;
import webbroker3.triggerorder.service.delegate.WEB3ToStopIfoOrderUnitService;
import webbroker3.triggerorder.service.delegate.WEB3ToStopOrderService;
import webbroker3.util.WEB3LogUtility;


/**
 * (�t�w�l���������T�[�r�XImpl)<BR>
 * �t�w�l���������T�[�r�X�����N���X<BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3ToStopOrderServiceImpl implements WEB3ToStopOrderService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToStopOrderServiceImpl.class);
    
    /**
     * @@roseuid 436ACF70035B
     */
    public WEB3ToStopOrderServiceImpl() 
    {
     
    }
    
    /**
     * (execute�t�w�l��������)<BR>
     * �t�w�l���������������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�t�w�l���������T�[�r�X�jexecute�t�w�l���������v�Q�ƁB<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B<BR>
     * @@param l_lngOrderId - (����ID)<BR>
     * ����ID�B<BR>
     * �i�����Ώۂ̒����̒���ID�j<BR>
     * @@param l_productType - (�����^�C�v)<BR>
     * �����^�C�v�B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 434C794900F1
     */
    public void executeStopOrder(SubAccount l_subAccount, long l_lngOrderId, ProductTypeEnum l_productType) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            "executeStopOrder(SubAccount l_subAccount, long l_lngOrderId, ProductTypeEnum l_productType)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null || l_productType == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        OrderUnit[] l_orderUnits = null;
        //1.1�����P�ʃI�u�W�F�N�g���擾����

        if (ProductTypeEnum.EQUITY.equals(l_productType))
        {
            //1.1.1�����́A�����P�ʃI�u�W�F�N�g���擾����B
            WEB3EquityTradingModule l_tradingModule
                = (WEB3EquityTradingModule) l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_eqOrderManager
                = (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
            
            l_orderUnits = l_eqOrderManager.getOrderUnits(l_lngOrderId);
        }
        else if (ProductTypeEnum.IFO.equals(l_productType))
        {
            //1.1.2�敨OP�́A�����P�ʃI�u�W�F�N�g���擾����B
            WEB3IfoTradingModule l_tradingModule
                = (WEB3IfoTradingModule) l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_opOrderManager 
                = (WEB3OptionOrderManagerImpl) l_tradingModule.getOrderManager();
            l_orderUnits = l_opOrderManager.getOrderUnits(l_lngOrderId);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        //1.2�i����t���[�F�@@�����P�ʂ��擾�ł��Ȃ������ꍇ�j
        if (l_orderUnits == null || l_orderUnits.length == 0)
        {
            //1.2.1log.error()�ɂāA�����P�ʂ��擾�ł��Ȃ��������b�Z�[�W�ƁA
            //����ID�A�⏕����ID�A����ID�A�����^�C�v���o�͂���B
            log.error("����ID = "+ l_subAccount.getAccountId());
            log.error("�⏕����ID = "+ l_subAccount.getSubAccountId());
            log.error("����ID = "+ l_lngOrderId);
            log.error("�����^�C�v = "+ l_productType);
            
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        OrderUnit l_orderUnit = l_orderUnits[0];

        //1.3�i����t���[�F�@@�����̖����^�C�v=="����"�̏ꍇ�j
        if (ProductTypeEnum.EQUITY.equals(l_productType))
        {
            WEB3ToStopEquityOrderUnitService l_orderUnitService = 
                (WEB3ToStopEquityOrderUnitService)Services.getService(WEB3ToStopEquityOrderUnitService.class);
            //1.3.1submitEquityStopOrder(�����P��)
            //���������t�w�l�����𔭒�����B
            //�����͈ȉ��̒ʂ�ɃZ�b�g����B
            //���������P�ʁF�@@�擾���������P�ʃI�u�W�F�N�g
            if (OrderCategEnum.ASSET.equals(l_orderUnit.getOrderCateg()))
            {
                l_orderUnitService.submitEquityStopOrder((EqTypeOrderUnit)l_orderUnit);
            }
            //1.3.2submitMarginOpenContractStopOrder(�����P��)
            //�M�p�V�K���t�w�l�����𔭒�����B
            //�����͈ȉ��̒ʂ�ɃZ�b�g����B
            //���������P�ʁF�@@�擾���������P�ʃI�u�W�F�N�g
            else if (OrderCategEnum.OPEN_MARGIN.equals(l_orderUnit.getOrderCateg()))
            {
                l_orderUnitService.submitMarginOpenContractStopOrder((EqTypeOrderUnit)l_orderUnit);
            }
            //1.3.3submitMarginSettleContractStopOrder(�����P��)
            //�M�p�ԍϋt�w�l�����𔭒�����B
            //�����͈ȉ��̒ʂ�ɃZ�b�g����B
            //���������P�ʁF�@@�擾���������P�ʃI�u�W�F�N�g
            else if (OrderCategEnum.CLOSE_MARGIN.equals(l_orderUnit.getOrderCateg()))
            {
                l_orderUnitService.submitMarginSettleContractStopOrder((EqTypeOrderUnit)l_orderUnit);
            }
        }
        //1.4�i����t���[�F�@@�����̖����^�C�v=="�敨�I�v�V����"�̏ꍇ�j
        else
        {
            WEB3ToStopIfoOrderUnitService l_ifoOrderUnitService = 
                (WEB3ToStopIfoOrderUnitService)Services.getService(WEB3ToStopIfoOrderUnitService.class);
            //1.4.1�敨�V�K���t�w�l�����𔭒�����B
            //�����͈ȉ��̒ʂ�ɃZ�b�g����B
            //�敨OP�����P�ʁF�@@�擾���������P�ʃI�u�W�F�N�g
            if (OrderCategEnum.IDX_FUTURES_OPEN.equals(l_orderUnit.getOrderCateg()))
            {
                l_ifoOrderUnitService.submitFuturesOpenContractStopOrder((IfoOrderUnit)l_orderUnit);
            }
            //1.4.2�敨�ԍϋt�w�l�����𔭒�����B
            //�����͈ȉ��̒ʂ�ɃZ�b�g����B
            //�敨OP�����P�ʁF�@@�擾���������P�ʃI�u�W�F�N�g
            else if (OrderCategEnum.IDX_FUTURES_CLOSE.equals(l_orderUnit.getOrderCateg()))
            {
                l_ifoOrderUnitService.submitFuturesSettleContractStopOrder((IfoOrderUnit)l_orderUnit);
            }
            //1.4.3�I�v�V�����V�K���t�w�l�����𔭒�����B
            //�����͈ȉ��̒ʂ�ɃZ�b�g����B
            //�敨OP�����P�ʁF�@@�擾���������P�ʃI�u�W�F�N�g
            else if (OrderCategEnum.IDX_OPTIONS_OPEN.equals(l_orderUnit.getOrderCateg()))
            {
                l_ifoOrderUnitService.submitOptionOpenContractStopOrder((IfoOrderUnit)l_orderUnit);
            }
            //1.4.4�I�v�V�����ԍϋt�w�l�����𔭒�����B
            //�����͈ȉ��̒ʂ�ɃZ�b�g����B
            //�敨OP�����P�ʁF�@@�擾���������P�ʃI�u�W�F�N�g
            else if (OrderCategEnum.IDX_OPTIONS_CLOSE.equals(l_orderUnit.getOrderCateg()))
            {
                l_ifoOrderUnitService.submitOptionSettleContractStopOrder((IfoOrderUnit)l_orderUnit);
            }
        }
        log.exiting(STR_METHOD_NAME);
        return;
    }
}
@
