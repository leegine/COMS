head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginChangeOpenMarginUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : * �M�p�V�K�������X�V�C���^�Z�v�^<BR>
                 : * �V�K�����������o�^���́ADB�X�V�d�l�J�X�^�}�C�Y�p�̃N���X�B<BR>
                 : *�iEqTypeOrderManagerPersistenceEventInterceptor�̎����j<BR>
                 :(WEB3MarginChangeOpenMarginUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/23 li-songfeng (���u) �V�K�쐬
                   2004/11/05 �@@���@@�C��   
                   2004/12/09 �����iSRA�j�@@�c�Č��Ή��̂��ߏC��
                   2004/12/28 �����iSRA�j�@@JavaDoc�C��
                   2006/11/02 �đo�g (���u) �c�a�X�V�d�lNo.171
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
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3ShortSellOrderFlagDef;
import webbroker3.equity.service.delegate.WEB3EquityFrontOrderService;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�M�p�V�K�������X�V�C���^�Z�v�^�j�B<BR>
 * <BR>
 * �V�K�����������o�^���́ADB�X�V�d�l�J�X�^�}�C�Y�p�̃N���X�B<BR>
 * �iEqTypeOrderManagerPersistenceEventInterceptor�̎����j
 * @@version 1.0
 */
public class WEB3MarginChangeOpenMarginUpdateInterceptor extends WEB3MarginUpdateEventInterceptor 
{
    /**
     * (���O���[�e�B���e�B)<BR>
     */
    private static WEB3LogUtility log =
       WEB3LogUtility.getInstance(WEB3MarginChangeOpenMarginUpdateInterceptor.class);

    
    /**
     * (�M�p�V�K�������������e)<BR>
     * �M�p�V�K�������������e�I�u�W�F�N�g�B<BR>
     */
    private WEB3MarginChangeOrderSpec l_marginChangeOrderSpec;
    
    /**
     * (�󔄂�K���Ώۃt���O�B)<BR>
     * �󔄂�K���Ώۃt���O�B<BR>
     */
    private boolean l_shortSellingRestraint;
    
    /**
     * (�����o�H�敪)<BR>
     */
    private String orderRootDiv = null;
    
    /**
     * (�㗝���͎�)<BR>
     */
    private WEB3GentradeTrader trader = null;
    

    
    /**
     * �i�M�p�V�K�������X�V�C���^�Z�v�^�j�B<BR>
     * �R���X�g���N�^�B<BR>
     * �����Ɏw�肳�ꂽ�I�u�W�F�N�g���A�����̃v���p�e�B�ɐݒ肷��B<BR>
     * <BR>
     * @@param marginChangeOrderSpec
     * �@@�@@�@@                    - �M�p�V�K�������������e�I�u�W�F�N�g<BR>
     * @@param shortSellingRestraint
     *                           - �󔄂�K���Ώۃt���O<BR>
     * �@@�@@�@@�󔄂�K���Ώۃt���O�B
     * @@param    l_orderRootdiv - �����o�H�敪<BR>
     * @@param    l_trader       - ����<BR>
     */
    public WEB3MarginChangeOpenMarginUpdateInterceptor(
        WEB3MarginChangeOrderSpec l_marginChangeOrderSpecParam,
        boolean l_shortSellingRestraintParam,
        String l_strOrderRootdiv,
        WEB3GentradeTrader l_trader)
    {
        this.l_marginChangeOrderSpec = l_marginChangeOrderSpecParam;
        this.l_shortSellingRestraint = l_shortSellingRestraintParam;
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
     * �P�j �M�p�����������e����<BR>
     * �@@this.�M�p�V�K�������������e�v���p�e�B��null�̏ꍇ�́A
     * �@@�p�����[�^.�����P��Params��ԋp���A�������I������B<BR>
     * <BR>
     * �Q�j �g�����ڃZ�b�g<BR>
     * �@@this.�M�p�V�K�������������e�v���p�e�B����<BR>
     * �@@�p�����[�^.�����P��Params�̊g�����ڂɒl���Z�b�g���A�ԋp����B<BR>
     * <BR>
     * �X�V���e�́ADB�ݒ�_���u�M�p�V�K������_���������P�ʃe�[�u���d�l.xls�v�Q�ƁB<BR>
     * <BR>
     * @@param l_updateType �X�V�^�C�v<BR>
     * INSERT�܂��́AUPDATE�B<BR>
     * OrderManagerPersistenceType�ɂĒ萔��`�B
     * @@param l_process ����<BR>
     * �iOrderManagerPersistenceContext�ɂĒ萔��`�j
     * @@param l_orderUnitParams �����P��Params<BR>
     * ���������P�ʂ��ێ����鍀�ڂ̃p�����[�^�N���X�B
     * @@return EqtypeOrderUnitParams
     * @@roseuid 40E91EE1033C
     */
    public EqtypeOrderUnitParams mutate(OrderManagerPersistenceType l_updateType, 
            OrderManagerPersistenceContext l_process, 
            EqtypeOrderUnitParams l_orderUnitParams) 
    {
        final String STR_METHOD_NAME =
              getClass().getName()
              + ".mutate(OrderManagerPersistenceType,"
              + "OrderManagerPersistenceContext "
              + "EqtypeOrderUnitParams)";
        log.entering(STR_METHOD_NAME);
        if (l_orderUnitParams == null)
        {
            log.error(
                STR_METHOD_NAME,
                new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                        this.getClass().getName() + STR_METHOD_NAME));
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,this.getClass().getName()+  "." + STR_METHOD_NAME);          
        }
        // ChangeOrderSpec == null
        if (this.l_marginChangeOrderSpec == null)
        {
            return l_orderUnitParams;
        }
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

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

        // ���s����    
        l_orderUnitParams.setExecutionConditionType(l_marginChangeOrderSpec.getModifiedExecutionType());
        
        // �l�i����
        l_orderUnitParams.setPriceConditionType(this.l_marginChangeOrderSpec.getModifiedPriceConditionType());
        
        // �����������Z�q
        //�t�w�l��l 
        String l_strOrderConditionType = l_orderUnitParams.getOrderConditionType(); 
        if (WEB3OrderingConditionDef.DEFAULT.equals(l_strOrderConditionType))
        {
            l_orderUnitParams.setOrderCondOperator(null);
            l_orderUnitParams.setStopOrderPrice(null);
        }
        else
        {
            l_orderUnitParams.setOrderCondOperator(l_marginChangeOrderSpec.getModifiedOrderCondOperator());
            l_orderUnitParams.setStopOrderPrice(l_marginChangeOrderSpec.getModifiedStopOrderPrice());
        }
        //�iW�w�l�j�����w�l 
        if (WEB3OrderingConditionDef.DEFAULT.equals(l_strOrderConditionType)
            || WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            l_orderUnitParams.setWLimitPrice(null);
        }
        else
        {
            l_orderUnitParams.setWLimitPrice(l_marginChangeOrderSpec.getModifiedWLimitPrice());
        }
        //�����������t
        l_orderUnitParams.setExpirationDate(l_marginChangeOrderSpec.getModifiedExpirationDate());
        //�����P��
        l_orderUnitParams.setPrice(l_marginChangeOrderSpec.getModifiedCalcUnitPrice());
        //�T�Z��n���
        l_orderUnitParams.setEstimatedPrice(l_marginChangeOrderSpec.getModifiedContractAmount());
        
        //�󔄃t���O
        // �s�ꂩ��m�F�ς݂̐��ʁ��s�ꖢ���M�̏ꍇ�ȉ��̂Ƃ���ɕҏW
        if (l_orderUnitParams.getConfirmedQuantityIsNull())
        {        
            //�󔄂�K���Ώۃt���O == true�̏ꍇ�́A5�i�h���i�K���Ώہh�j
            if (this.l_shortSellingRestraint)
            {
                l_orderUnitParams.setShortSellOrderFlag(WEB3ShortSellOrderFlagDef.PRICE_RESTRAINT);
            }
            //�󔄂�K���Ώۃt���O == false�̏ꍇ�́A�u�����N�i�h�ΏۊO�h�j
            else
            {
                l_orderUnitParams.setShortSellOrderFlag(WEB3ShortSellOrderFlagDef.EXCEPT_OBJECT);                
            }
        }
        
        WEB3EquityOrderManager l_equityOrderManager =
            (WEB3EquityOrderManager)l_finApp
                .getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        OrderUnit l_orderUnit = l_equityOrderManager.toOrderUnit(l_orderUnitParams);        

		// �s�ꂩ��m�F�ς݂̐���==Double.NaN�i���s�ꖢ���M�j            
		if (l_orderUnitParams.getConfirmedQuantityIsNull())
		{
			// �������
			// �p�����[�^.�����P��Params.������ԁiorderStatus�j�ɁhMODIFIED�h���Z�b�g
			l_orderUnitParams.setOrderStatus(
				OrderStatusEnum.MODIFIED);

			//���������E����敪
			//    this.isUnexecuted( )==true�̏ꍇ�́A7�F�S�����������B
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
                
			// ���������E����敪 = 5:������
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

        //�����G���[���R�R�[�h
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
        
        // ����������
        // this.�s�ꂩ��m�F�ς̐��� == Double.NaN�i=�s�ꖢ���M�j�̏ꍇ
        if (l_orderUnitParams.getConfirmedQuantityIsNull())
        {
            l_orderUnitParams.setOriginalQuantity(l_orderUnitParams.getQuantity());
        }

        //�iW�w�l�j���s����
        //�M�p�V�K�������������e.get������iW�w�l�j���s����( )
        //�������A�����������i0�FDEFAULT�i�����w��Ȃ��j�A1�F�t�w�l�j�̏ꍇ�́Anull�B
        if (WEB3OrderingConditionDef.DEFAULT.equals(l_orderUnitParams.getOrderConditionType())
            || WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_orderUnitParams.getOrderConditionType()))
        {
            l_orderUnitParams.setWLimitExecCondType(null);
        }
        else
        {
            l_orderUnitParams.setWLimitExecCondType(
                this.l_marginChangeOrderSpec.getModifiedWlimitExecCondType());
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
     * �X�V���e�́ADB�ݒ�_���u�M�p�V�K������_�������������e�[�u���d�l.xls�v�Q�ƁB<BR>
     * <BR>
     * @@param l_updateType �X�V�^�C�v<BR>
     * INSERT�܂��́AUPDATE�B<BR>
     * OrderManagerPersistenceType�ɂĒ萔��`�B
     * @@param l_process ����<BR>
     * �iOrderManagerPersistenceContext�ɂĒ萔��`�j
     * @@param l_orderHistoryParams ������������Params<BR>
     * ���������������ێ����鍀�ڂ̃p�����[�^�N���X�B
     * @@return EqtypeOrderActionParams
     * @@roseuid 40F229A501C2
     */
    public EqtypeOrderActionParams mutate(OrderManagerPersistenceType l_updateType, 
        OrderManagerPersistenceContext l_process, 
        EqtypeOrderActionParams l_orderHistoryParams) 
    {
        final String STR_METHOD_NAME =
            getClass().getName()
            + ".mutate(OrderManagerPersistenceType "
            + "l_orderManagerPersistenceType, "
            + "OrderManagerPersistenceContext "
            + "l_orderManagerPersistenceContext, "
            + "EqtypeOrderActionParams l_ifoOrderUnitParams)";
        log.entering(STR_METHOD_NAME);

        //�|super.mutate()�����{����B
        EqtypeOrderActionParams l_orderActionParams =
            super.mutate(
                l_updateType,
                l_process,
                l_orderHistoryParams);
        
        //�����C�x���g�^�C�v
        l_orderHistoryParams.setOrderEventType(OrderEventTypeEnum.CHANGE);

        log.exiting(STR_METHOD_NAME);
        return l_orderHistoryParams;
    }
}
@
