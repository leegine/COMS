head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.50.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccOrderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �A�����������T�[�r�XImpl(WEB3ToSuccOrderServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/17 ������(���u) �V�K�쐬
Revesion History : 2008/05/05 ����(���u) �d�l�ύX�@@���f��No.312�A314
*/

package webbroker3.triggerorder.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderManager;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.triggerorder.WEB3ToSuccEqTypeOrderUnitImpl;
import webbroker3.triggerorder.WEB3ToSuccIfoOrderUnitImpl;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitRow;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitRow;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccEquityOrderUnitService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccIfoOrderUnitService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOrderService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;

/**
 * (�A�����������T�[�r�XImpl)<BR>
 * �A�����������T�[�r�X�����N���X�B<BR>
 * <BR>
 * @@author ������ <BR>
 * @@version 1.0<BR>
 */
public class WEB3ToSuccOrderServiceImpl implements WEB3ToSuccOrderService 
{

    /**
     * ���O�I�u�W�F�N�g
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
    	WEB3ToSuccOrderServiceImpl.class);

    /**
     * @@roseuid 4348E5B000FA
     */
    public WEB3ToSuccOrderServiceImpl() 
    {
     
    }
    
    /**
     * (execute�A����������)<BR>
     * �A�����������������s���B<BR>
     * �V�[�P���X�}�u�i�A�����������T�[�r�X�jexecute�A�����������v���Q�ƁB<BR>
     *  ======================================================== <BR>
     *  �V�[�P���X�}�u�i�A�����������T�[�r�X�jexecute�A�����������v <BR> 
     *  1.5.1�u�e�������S�����v�̗�O��throw���G���[�I��<BR> 
     *   class: WEB3BusinessLayerException <BR>
     *   tag: BUSINESS_ERROR_02242<BR>
     *  ========================================================== <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B<BR>
     * @@param l_lngSubOrderId - (�\�񒍕��̒���ID)<BR>
     * ����ID�B<BR>
     * �i�����Ώۂ̗\�񒍕��̒���ID�j<BR>
     * @@param l_productType - (�����^�C�v)<BR>
     * �����^�C�v�B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 43215FFA02C5
     */
    public void executeSuccOrder(SubAccount l_subAccount, long l_lngSubOrderId, ProductTypeEnum l_productType) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "executeSuccOrder(SubAccount, long, ProductTypeEnum)";
        log.entering(STR_METHOD_NAME);

        try
		{
            //1.1 getOrderUnit(ProductTypeEnum, long)
            //�����Ώۂ̎q�����̒����P�ʃI�u�W�F�N�g���擾����B 
            //�����͈ȉ��̒ʂ�ɃZ�b�g����B 
            //  �����^�C�v�F�@@�����̖����^�C�v 
            //  ����ID�F�@@�����̒���ID
            WEB3ToSuccOrderManagerImpl l_orderManager = 
            	(WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
            OrderUnit l_orderUnit = l_orderManager.getOrderUnit(l_productType, l_lngSubOrderId);

            //1.2 �擾�����q����.�����L����ԁ�OPEN
            if (!OrderOpenStatusEnum.OPEN.equals(l_orderUnit.getOrderOpenStatus()))
            {
                log.exiting(STR_METHOD_NAME);

            	//1.2.1 return
            	return;
            }
            
            //1.3 �e�����̒����P�ʃI�u�W�F�N�g���擾����
            //1.3.1 �����́A�e�����̒����P�ʃI�u�W�F�N�g���擾����B
            //�����͈ȉ��̒ʂ�ɃZ�b�g����B 
            //  �e�����̒����P��ID�F�@@�擾�����\�񒍕��P��.�e�����̒����P��ID
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

            EqTypeOrderUnit l_eqTypeOrderUnit = null;
            if (ProductTypeEnum.EQUITY.equals(l_productType))
            {
                WEB3EquityOrderManager l_equityOrderManager =
                    (WEB3EquityOrderManager)l_finApp.getTradingModule(l_productType).getOrderManager();
                RsvEqOrderUnitRow l_orderUnitRow = (RsvEqOrderUnitRow)l_orderUnit.getDataSourceObject();
                l_eqTypeOrderUnit = (EqTypeOrderUnit)l_equityOrderManager.getOrderUnit(l_orderUnitRow.getParentOrderUnitId());
            }

            //1.3.2 �敨OP�́A�e�����̒����P�ʃI�u�W�F�N�g���擾����B
            //�����͈ȉ��̒ʂ�ɃZ�b�g����B 
            //  �e�����̒����P��ID�F�@@�擾�����\�񒍕��P��.�e�����̒����P��ID
            IfoOrderUnit l_ifoOrderUnit = null;
            if (ProductTypeEnum.IFO.equals(l_productType))
            {
                IfoOrderManager l_ifoOrderManager =
                    (IfoOrderManager)l_finApp.getTradingModule(l_productType).getOrderManager();
                RsvIfoOrderUnitRow l_orderUnitRow = (RsvIfoOrderUnitRow)l_orderUnit.getDataSourceObject();
                l_ifoOrderUnit = (IfoOrderUnit)l_ifoOrderManager.getOrderUnit(l_orderUnitRow.getParentOrderUnitId());
            }

            //1.4  isFullyExecuted( )
            boolean l_blnIsFullyExecuted = false;
            if (ProductTypeEnum.EQUITY.equals(l_productType))
            {
                l_blnIsFullyExecuted = l_eqTypeOrderUnit.isFullyExecuted();
            }
            else if (ProductTypeEnum.IFO.equals(l_productType))
            {
                l_blnIsFullyExecuted = l_ifoOrderUnit.isFullyExecuted();
            }

            //1.5 ����t���[�F�@@�e���� �� �S�����̏ꍇ
            //1.5.1�u�e�������S�����v�̗�O��throw���G���[�I��
		    //   class: WEB3BusinessLayerException
		    //   tag: BUSINESS_ERROR_02242
            if (!l_blnIsFullyExecuted)
            {
                log.debug("�e���� �� �S�����̏ꍇ�B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02242,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            //1.6 ����t���[�F�@@�����̖����^�C�v=="����"�̏ꍇ
            if (ProductTypeEnum.EQUITY.equals(l_productType))
            {
                WEB3ToSuccEquityOrderUnitService l_orderUnitService =
                    (WEB3ToSuccEquityOrderUnitService)Services.getService(
                        WEB3ToSuccEquityOrderUnitService.class);

                if (OrderCategEnum.ASSET.equals(l_orderUnit.getOrderCateg()))
                {
                    //1.6.1 submit������������(�����\�񒍕��P��)
                    // �����͈ȉ��̒ʂ�ɃZ�b�g����
                    // �����\�񒍕��P�ʁF�@@�擾�����q�����I�u�W�F�N�g
                    l_orderUnitService.submitEquityOrder((WEB3ToSuccEqTypeOrderUnitImpl)l_orderUnit);
                }
                else if (OrderCategEnum.OPEN_MARGIN.equals(l_orderUnit.getOrderCateg()))
                {
                    //1.6.2 submit�M�p�V�K������(�����\�񒍕��P��)
                    l_orderUnitService.submitMarginOpenContractOrder((WEB3ToSuccEqTypeOrderUnitImpl)l_orderUnit);
                }
                else if (OrderCategEnum.CLOSE_MARGIN.equals(l_orderUnit.getOrderCateg()))
                {
                    //1.6.3 submit�M�p�ԍϒ���(�����\�񒍕��P��)
                    l_orderUnitService.submitMarginSettleContractOrder((WEB3ToSuccEqTypeOrderUnitImpl)l_orderUnit);
                }
                else if (OrderCategEnum.SWAP_MARGIN.equals(l_orderUnit.getOrderCateg()))
                {
                    //1.6.4 submit�M�p�������n����(�����\�񒍕��P��)
                    l_orderUnitService.submitMarginSwapContractOrder((WEB3ToSuccEqTypeOrderUnitImpl)l_orderUnit);
                }
            }

            //����t���[�F�@@�����̖����^�C�v=="�敨OP"�̏ꍇ
            if (ProductTypeEnum.IFO.equals(l_productType))
            {
                WEB3ToSuccIfoOrderUnitService l_toSuccIfoOrderUnit =
                    (WEB3ToSuccIfoOrderUnitService)Services.getService(
                            WEB3ToSuccIfoOrderUnitService.class);

                if (OrderCategEnum.IDX_FUTURES_OPEN.equals(l_orderUnit.getOrderCateg()))
                {
                    //submit�敨�V�K������(�敨OP�\�񒍕��P��Impl)
                    l_toSuccIfoOrderUnit.submitFuturesOpenContractOrder((WEB3ToSuccIfoOrderUnitImpl)l_orderUnit);
                }
                else if (OrderCategEnum.IDX_FUTURES_CLOSE.equals(l_orderUnit.getOrderCateg()))
                {
                    //submit�敨�ԍϒ���(�敨OP�\�񒍕��P��Impl)
                    l_toSuccIfoOrderUnit.submitFuturesSettleContractOrder((WEB3ToSuccIfoOrderUnitImpl)l_orderUnit);
                }
                else if (OrderCategEnum.IDX_OPTIONS_OPEN.equals(l_orderUnit.getOrderCateg()))
                {
                    //submitOP�V�K������(�敨OP�\�񒍕��P��Impl)
                    l_toSuccIfoOrderUnit.submitOptionsOpenContractOrder((WEB3ToSuccIfoOrderUnitImpl)l_orderUnit);
                }
                else if (OrderCategEnum.IDX_OPTIONS_CLOSE.equals(l_orderUnit.getOrderCateg()))
                {
                    //submitOP�ԍϒ���(�敨OP�\�񒍕��P��Impl)
                    l_toSuccIfoOrderUnit.submitOptionsSettleContractOrder((WEB3ToSuccIfoOrderUnitImpl)l_orderUnit);
                }
            }
		}

        catch (NotFoundException l_nfe)
        {
            log.error(l_nfe.getMessage(), l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
