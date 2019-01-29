head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginChangeCloseMarginUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p�ԍϒ����X�V�C���^�Z�v�^(WEB3MarginChangeCloseMarginUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/20 ������ (���u) �V�K�쐬
Revesion History : 2004/12/09 �����@@ (SRA)�@@�c�Č��Ή�
Revesion History : 2006/11/02 �đo�g (���u) �c�a�X�V�d�lNo.171
*/

package webbroker3.equity;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderEventTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.equity.service.delegate.WEB3EquityFrontOrderService;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.util.WEB3LogUtility;


/**
 * �i�M�p�ԍϒ����X�V�C���^�Z�v�^�j�B<BR>
 * <BR>
 * �ԍϒ��������o�^���́ADB�X�V�d�l�J�X�^�}�C�Y�p�̃N���X�B<BR>
 * �iEqTypeOrderManagerPersistenceEventInterceptor�̎����j
 * @@version 1.0
 */
public class WEB3MarginChangeCloseMarginUpdateInterceptor extends WEB3MarginUpdateEventInterceptor 
{ 
    /**
     * (���O���[�e�B���e�B)<BR>
     */
    private static WEB3LogUtility log =
      WEB3LogUtility.getInstance(WEB3MarginChangeCloseMarginUpdateInterceptor.class);

    /**
     * (�M�p�ԍϒ����������e)<BR>
     * �M�p�ԍϒ����������e�I�u�W�F�N�g�B<BR>
     */
    private WEB3MarginChangeSettleContractOrderSpec l_creditCloseMarginChangeUpdateSpec;
    
    /**
     * (�T�Z���ϑ��v����v�Z����)<BR>
     * �T�Z���ϑ��v����v�Z���ʃI�u�W�F�N�g�B<BR>
     * �i�����P���A�T�Z��n����̐ݒ�Ɏg�p�j<BR>
     */
    private WEB3EquityRealizedProfitAndLossPrice l_equityRealizedProfitAndLossPrice;
    
    /**
     * (�����o�H�敪)<BR>
     */
    private String orderRootDiv = null;
    
    /**
     * (�㗝���͎�)<BR>
     */
    private WEB3GentradeTrader trader = null;
    
    /**
     * (�M�p�ԍϒ����X�V�C���^�Z�v�^)<BR>
     * �R���X�g���N�^�B<BR>
     * �����Ɏw�肳�ꂽ�I�u�W�F�N�g���A�����̃v���p�e�B�ɐݒ肷��B<BR>
     * @@param l_creditCloseMarginChangeUpdateSpec - �M�p�ԍϒ����������e�I�u�W�F�N�g�B<BR>
     * @@param l_equityRealizedProfitAndLossPrice - �T�Z���ϑ��v����v�Z���ʃI�u�W�F�N�g�B<BR>
     * @@param    l_orderRootdiv - �����o�H�敪<BR>
     * @@param    l_trader       - ����<BR>
     */
    public WEB3MarginChangeCloseMarginUpdateInterceptor( 
        WEB3MarginChangeSettleContractOrderSpec l_creditCloseMarginChangeUpdateSpec, 
        WEB3EquityRealizedProfitAndLossPrice l_equityRealizedProfitAndLossPrice,
        String l_strOrderRootdiv,
        WEB3GentradeTrader l_trader) 
    {
        this.l_creditCloseMarginChangeUpdateSpec = l_creditCloseMarginChangeUpdateSpec;
        this.l_equityRealizedProfitAndLossPrice = l_equityRealizedProfitAndLossPrice;
        this.orderRootDiv = l_strOrderRootdiv;
        this.trader = l_trader;
    }
    
    /**
     * (�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j<BR>
     * �����P��Params�Ɋg������(*)�ݒ肵�ԋp����B<BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB<BR>
     * (*) xTrade�}�j���A���u�X�V���e�̃J�X�^�}�C�Y�v�Q�ƁB<BR>
     * <BR>
     * �P�j �M�p�ԍϒ������e����<BR>
     * �@@�Ethis.�M�p�ԍϒ����������e�v���p�e�B�A
     * �@@�Ethis.�T�Z���ϑ��v����v�Z���ʃv���p�e�B
     * �@@��L�̂����ꂩ�P�ł�null�̏ꍇ�́A�p�����[�^.�����P��Params��ԋp���A�������I������B<BR>
     * <BR>
     * �Q�j �g�����ڃZ�b�g<BR>
     * �@@this.�M�p�ԍϒ����������e�v���p�e�B����A�p�����[�^.�����P��Params�̊g�����ڂɒl���Z�b�g���A�ԋp����B<BR>
     * <BR>
     * �X�V���e��DB�ݒ�_���u�M�p�ԍϒ���_���������P�ʃe�[�u��.xls�v�Q�ƁB<BR>
     * @@param l_updateType - �X�V�^�C�v<BR>
     * INSERT�܂��́AUPDATE�B<BR>
     * 
     * OrderManagerPersistenceType�ɂĒ萔��`�B<BR>
     * <BR>
     * @@param l_process - ����<BR>
     * �iOrderManagerPersistenceContext�ɂĒ萔��`�j<BR>
     * <BR>
     * @@param l_orderUnitParams - �����P��Params<BR>
     * ���������P�ʂ��ێ����鍀�ڂ̃p�����[�^�N���X�B<BR>
     * @@return EqtypeOrderUnitParams
     * @@roseuid 40C6C3F001D4
     */
    public EqtypeOrderUnitParams mutate(OrderManagerPersistenceType l_updateType, 
            OrderManagerPersistenceContext l_process, 
            EqtypeOrderUnitParams l_orderUnitParams) 
    {
        final String STR_METHOD_NAME = "�����P��Params�Ɋg������:mutate()";
        log.entering(STR_METHOD_NAME);
        
        //�M�p�ԍϒ������e����<BR>
        //�@@�Ethis.�M�p�ԍϒ����������e�v���p�e�B�A
        //�@@�Ethis.�T�Z���ϑ��v����v�Z���ʃv���p�e�B
        //�@@��L�̂����ꂩ�P�ł�null�̏ꍇ�́A�p�����[�^.�����P��Params��ԋp���A�������I������
        if (this.l_creditCloseMarginChangeUpdateSpec == null 
            || this.l_equityRealizedProfitAndLossPrice == null)
        {
            return l_orderUnitParams;
        }

        //���� = �C���^�Z�v�^�̃v���p�e�B.�㗝���͎�.�����ID
        //  ���C���^�Z�v�^�̃v���p�e�B.�㗝���͎�==null�̏ꍇ�́Anull���Z�b�g�B
        if (this.trader == null)
        {
            l_orderUnitParams.setTraderId(null);
        }
        else
        {
            l_orderUnitParams.setTraderId(this.trader.getTraderId());
        }

        //���s����
        l_orderUnitParams.setExecutionConditionType(this.l_creditCloseMarginChangeUpdateSpec.getModifiedExecutionCondition());

        //�l�i����
        l_orderUnitParams.setPriceConditionType(this.l_creditCloseMarginChangeUpdateSpec.getModifiedPriceConditionType());
        
        //�����������擾
        String l_strOrderConditionType = l_orderUnitParams.getOrderConditionType();

        //�����������Z�q�A�t�w�l��l
        if (WEB3OrderingConditionDef.DEFAULT.equals(l_strOrderConditionType))
        {
            l_orderUnitParams.setOrderCondOperator(null);
            l_orderUnitParams.setStopOrderPrice(null);
        }
        else
        {
            l_orderUnitParams.setOrderCondOperator(this.l_creditCloseMarginChangeUpdateSpec.getModifiedOrderCondOperator());
            l_orderUnitParams.setStopOrderPrice(this.l_creditCloseMarginChangeUpdateSpec.getModifiedStopOrderPrice());    
        }

        //�iW�w�l�j�����w�l
        if (WEB3OrderingConditionDef.DEFAULT.equals(l_strOrderConditionType)
            || WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            l_orderUnitParams.setWLimitPrice(null);
        }
        else
        {
            l_orderUnitParams.setWLimitPrice(this.l_creditCloseMarginChangeUpdateSpec.getModifiedWLimitPrice());
        }

        //�����������t
        l_orderUnitParams.setExpirationDate(this.l_creditCloseMarginChangeUpdateSpec.getModifiedExpirationDate());

        //�����P��
        l_orderUnitParams.setPrice(l_equityRealizedProfitAndLossPrice.getCalcUnitPrice());

        //�T�Z��n���
        l_orderUnitParams.setEstimatedPrice(l_equityRealizedProfitAndLossPrice.getEstimatedRealizedProfitAndLossAmount());

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);        
        WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        OrderUnit l_orderUnit = l_orderManager.toOrderUnit(l_orderUnitParams);
 
        // �s�ꂩ��m�F�ς݂̐���==Double.NaN�i���s�ꖢ���M�j            
        if (l_orderUnitParams.getConfirmedQuantityIsNull())
        {
            // �������
            // �p�����[�^.�����P��Params.������ԁiorderStatus�j�ɁhMODIFIED�h���Z�b�g
            l_orderUnitParams.setOrderStatus(
                  OrderStatusEnum.MODIFIED);

            //���������E����敪
		    //this.isUnexecuted( )==true�̏ꍇ�́A7�F�S�����������B
		    if (l_orderUnit.isUnexecuted())
		    {
			  l_orderUnitParams.setModifyCancelType(
				  WEB3ModifyCancelTypeDef.CHANGED);
		    }
		    //    this.isUnexecuted( )==false�̏ꍇ�́A6�F�ꕔ���������B
		    else
		    {
			  l_orderUnitParams.setModifyCancelType(
				  WEB3ModifyCancelTypeDef.PARTIALLY_CHANGED);
		    }
        }
        // �s�ꂩ��m�F�ς݂̐���!=Double.NaN�i���s�ꑗ�M�ρj
        else
        {
            // �������
            //�p�����[�^.�����P��Params.������ԁiorderStatus�j�ɁhMODIFY_ACCEPTED�h���Z�b�g
            l_orderUnitParams.setOrderStatus(
                OrderStatusEnum.MODIFY_ACCEPTED);
                            
            //���������E����敪 = 5:������
			l_orderUnitParams.setModifyCancelType(
			    WEB3ModifyCancelTypeDef.CHANGING);
        }

        //�����o�H�敪 = �C���^�Z�v�^�̃v���p�e�B.�����o�H�敪
        l_orderUnitParams.setOrderRootDiv(this.orderRootDiv);
        
        //�����o�H�敪
        WEB3EquityFrontOrderService l_frontOrderService =
            (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
        String l_strSubmitOrderRouteDiv = null;
        try
        {
            l_strSubmitOrderRouteDiv =
                l_frontOrderService.getChangeSubmitOrderRouteDiv(
                    (EqTypeOrderUnit)l_orderUnit);
        }
        catch (WEB3BaseException l_wbe)
        {
            log.error(l_wbe.getMessage(), l_wbe);
            throw new WEB3BaseRuntimeException(
                l_wbe.getErrorInfo(), 
                this.getClass().getName() + STR_METHOD_NAME, 
                l_wbe.getMessage(), l_wbe);
        }
        l_orderUnitParams.setSubmitOrderRouteDiv(l_strSubmitOrderRouteDiv);
        
        // �����G���[���R�R�[�h
        l_orderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
        
        //����Rev.
        String l_strChangeOrderRev = null;
        try
        {
            l_strChangeOrderRev =
                l_frontOrderService.getChangeOrderRev((EqTypeOrderUnit)l_orderUnit);
        }
        catch (WEB3BaseException l_wbe)
        {
            log.error(l_wbe.getMessage(), l_wbe);
            throw new WEB3BaseRuntimeException(
                l_wbe.getErrorInfo(), 
                this.getClass().getName() + STR_METHOD_NAME, 
                l_wbe.getMessage(), l_wbe);
        }
        l_orderUnitParams.setOrderRev(l_strChangeOrderRev);

        //�iW�w�l�j���s����
        //�M�p�ԍϒ����������e.get������iW�w�l�j���s����( )
        //�������A�����������i0�FDEFAULT�i�����w��Ȃ��j�A1�F�t�w�l�j�̏ꍇ�́Anull�B
        if (WEB3OrderingConditionDef.DEFAULT.equals(l_orderUnitParams.getOrderConditionType())
            || WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_orderUnitParams.getOrderConditionType()))
        {
            l_orderUnitParams.setWLimitExecCondType(null);
        }
        else
        {
            l_orderUnitParams.setWLimitExecCondType(
                this.l_creditCloseMarginChangeUpdateSpec.getModifiedWlimitExecCondType());
        }
        log.exiting(STR_METHOD_NAME);
        return l_orderUnitParams;
    }
    
    /**
     * (�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j<BR>
     * ��������Params�Ɋg������(*)�ݒ肵�ԋp����B<BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB<BR>
     * <BR>
     * �P�j�@@�����P�ʃI�u�W�F�N�g�擾<BR>
     * <BR>
     * �@@�����̒����P��Params.�����P�ʂh�c�ɊY�����钍���P�ʃI�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �R�j�@@�g�����ڃZ�b�g<BR>
     * �@@�p�����[�^.��������Params�̊g�����ڂɁA�����P�ʃI�u�W�F�N�g�̓������ڂ���l���Z�b�g���A
     * �@@�ԋp����B<BR>
     * �@@�������C�x���g�^�C�v�ɂ́h2�F�ύX�����h���Z�b�g����B<BR>
     * <BR>
     * �X�V���e��DB�ݒ�_���u�M�p�ԍϒ���_�������������e�[�u��.xls�v�Q�ƁB<BR>
     * @@param l_updateType - �X�V�^�C�v<BR>
     * INSERT�܂��́AUPDATE�B<BR>
     * 
     * OrderManagerPersistenceType�ɂĒ萔��`�B<BR>
     * <BR>
     * @@param l_process - ����<BR>
     * �iOrderManagerPersistenceContext�ɂĒ萔��`�j<BR>
     * <BR>
     * @@param l_orderHistoryParams - ������������Params<BR>
     * ���������������ێ����鍀�ڂ̃p�����[�^�N���X�B<BR>
     * @@return EqtypeOrderActionParams
     * @@roseuid 40F22A74006A
     */
    public EqtypeOrderActionParams mutate(
        OrderManagerPersistenceType l_updateType, 
        OrderManagerPersistenceContext l_process, 
        EqtypeOrderActionParams l_orderHistoryParams) 
    {
        final String STR_METHOD_NAME = "��������Params�Ɋg������:mutate()";
        log.entering(STR_METHOD_NAME);
        
        //�|super.mutate()�����{����B
        EqtypeOrderActionParams l_orderActionParams =
            super.mutate(
                l_updateType,
                l_process,
                l_orderHistoryParams);
        
        //�����C�x���g�^�C�v = 2:�ύX����
        l_orderHistoryParams.setOrderEventType(OrderEventTypeEnum.CHANGE);
        
        log.exiting(STR_METHOD_NAME);
        return l_orderHistoryParams;
    }
}
@
