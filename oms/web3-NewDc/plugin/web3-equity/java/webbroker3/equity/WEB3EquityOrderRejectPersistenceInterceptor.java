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
filename	WEB3EquityOrderRejectPersistenceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����������������t�C���^�Z�v�^(WEB3EquityOrderRejectPersistenceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/27 ����� (���u) �V�K�쐬
Revesion History : 2004/9/27 Ḗ@@�� (���u) mutate ���\�b�h�̊���
                   2004/11/1 �@@���@@�C��   
                   2004/12/10 �����iSRA�j �C��
                   2005/01/05 �����a��(SRA) JavaDoc�C��
                   2006/11/02 ������@@(���u)���f��No.1022
                   2006/11/03 �đo�g (���u) �c�a�X�V�d�lNo.175
                   2006/11/28 �đo�g (���u) ���f��No1060,�c�a�X�V�d�lNo184,No191
*/
package webbroker3.equity;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderManager;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AcceptStatusDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.equity.data.HostEqtypeOrderAcceptParams;
import webbroker3.equity.define.WEB3EquityWlimitEnableStatusDivDef;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�����������������t�C���^�Z�v�^�j�B<BR>
 * <BR>
 * �����̒��������t�T�[�r�X�ɂ����āA<BR>
 * �����G���[�^����G���[����DB�ݒ�d�l���J�X�^�}�C�Y���邽�߂̃C���^�Z�v�^�B<BR>
 * <BR>
 * �������������t�Ŏg�p����B
 * @@author lufaxu
 * @@version 1.0
 */
public class WEB3EquityOrderRejectPersistenceInterceptor
    extends WEB3EquityOrderManagerPersistenceEventInterceptor
{

    /**
     * (���O�o�̓��[�e�B���e�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3EquityOrderRejectPersistenceInterceptor.class);

    /**
     * (����������t�L���[Params) <BR>
     * �y����������t�L���[�e�[�u���z�̂P���R�[�h�B<BR>
     */
    private HostEqtypeOrderAcceptParams hostEqtypeOrderAcceptParams;

    /**
     * (�T�Z����v�Z����)<BR>
     * �T�Z����v�Z���ʃI�u�W�F�N�g<BR>
     */
    private WEB3EquityEstimatedPrice eqyityEstimatedPrice;

    /**
     * �i�����������������t�C���^�Z�v�^�j<BR>
     * <BR>
     * �R���X�g���N�^�B<BR>
     */
    public WEB3EquityOrderRejectPersistenceInterceptor()
    {
        super();
    }

    /**
     * �i�����������������t�C���^�Z�v�^�j<BR>
     * �R���X�g���N�^�B<BR>
     * <BR>
     * @@param l_hostEqtypeOrderAcceptParams - (����������t�L���[Params)<BR>
     * �y����������t�L���[�e�[�u���z�̂P���R�[�h�B<BR>
     */
    public WEB3EquityOrderRejectPersistenceInterceptor(HostEqtypeOrderAcceptParams l_hostEqtypeOrderAcceptParams)
    {
        this.hostEqtypeOrderAcceptParams = l_hostEqtypeOrderAcceptParams;
    }

    /**
     * (�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j<BR>
     * �����P��Params�Ɋg������(*)�ݒ肵�ԋp����B<BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB<BR>
     * (*) xTrade�}�j���A���u�X�V���e�̃J�X�^�}�C�Y�v�Q�ƁB<BR>
     * <BR>
     * �P�j �g�����ڃZ�b�g<BR>
     * �i������Ԃ��h�������s�i�ύX�����j�FNOT_MODIFIED�h �܂��� <BR>
     * �h�������s�i��������j�FNOT_CANCELLED�h �̏ꍇ�̂ݏ��������{����B<BR>
     * �@@�ȊO�́A���������̂܂ܕԋp���������I������j<BR>
     * <BR>
     * �X�V���e�́A�u�������������t_���������P�ʃe�[�u��.xls�v��<BR>
     * �u�i�������������t[������t�G���[]�j���������P�ʃe�[�u���v�V�[�g�A<BR>
     * �u�i�������������t[�����t�G���[]�j���������P�ʃe�[�u���v�V�[�g�Q�ƁB<BR>
     * <BR>
     * @@param l_orderManagerPersistenceType - (�X�V�^�C�v)<BR>
     * INSERT�܂��́AUPDATE�B<BR>
     * OrderManagerPersistenceType�ɂĒ萔��`�B<BR>
     * <BR>
     * @@param l_orderManagerPersistenceContext - (����)<BR>
     * �iOrderManagerPersistenceContext�ɂĒ萔��`�j<BR>
     * <BR>
     * @@param l_orderUnitParams - (�����P��Params)<BR>
     * ���������P�ʂ��ێ����鍀�ڂ̃p�����[�^�N���X�B<BR>
     * @@return EqtypeOrderUnitParams<BR>
     * @@roseuid 4035E715036B<BR>
     */
    public EqtypeOrderUnitParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        EqtypeOrderUnitParams l_orderUnitParams)
    {
        
        final String STR_METHOD_NAME = 
            "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, EqtypeOrderUnitParams)";        
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnitParams == null)
        {
            throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderManager.toOrderUnit(l_orderUnitParams);
        boolean l_blnStopOderSwitching = false;
        String l_strWLimitEnableStatusDiv = null;
        try
        {
            l_blnStopOderSwitching = l_orderManager.isStopOrderSwitching(l_orderUnit);
            l_strWLimitEnableStatusDiv =
                WEB3EquityDataAdapter.getWLimitEnableStatusDiv(l_orderUnit);
        }
        catch (WEB3BaseException l_exc)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_exc.getMessage(),
                l_exc);
        }

        //�i������Ԃ��h�������s�i�ύX�����j�FNOT_MODIFIED�h 
        if (OrderStatusEnum.NOT_MODIFIED.equals(l_orderUnitParams.getOrderStatus()))
        {
            // ���s����
            l_orderUnitParams.setExecutionConditionType(l_orderUnitParams.getConfirmedExecConditionType());
            
            // �l�i����
			l_orderUnitParams.setPriceConditionType(l_orderUnitParams.getConfirmedPriceConditionType());
 
            //�X�g�b�v�����ؑ֒�(*1)�̏ꍇ�A
            //�@@(*1)�g�����������}�l�[�W��.is�X�g�b�v�����ؑ֒�()��true�̏ꍇ�A�X�g�b�v�����ؑ֒��B
            //�@@�����ɐݒ肷�钍���P�ʂɂ́A�X�V�O�̒����P�ʂ��w�肷��
            //�iEqtypeOrderUnitParams���g�����������}�l�[�W��.toOrderUnit()�ɂĒ����P�ʌ^�ɂ���j
            if (l_blnStopOderSwitching)
            {
                //���������� �@@�X�V�O�̔�������  �ȊO�A�i�����l�j
                l_orderUnitParams.setOrgOrderConditionType(l_orderUnitParams.getOrderConditionType());
                //�������������Z�q �@@�X�V�O�̔����������Z�q   �ȊO�A�@@�i�����l�j
                l_orderUnitParams.setOrgOrderCondOperator(l_orderUnitParams.getOrderCondOperator());
                //���t�w�l��l  �X�V�O�̋t�w�l��l  �ȊO�A�@@�i�����l�j
                if (l_orderUnitParams.getStopOrderPriceIsNull())
                {
                    l_orderUnitParams.setOrgStopOrderPrice(null);
                }
                else
                {
                    l_orderUnitParams.setOrgStopOrderPrice(l_orderUnitParams.getStopOrderPrice());
                }
                //���iW�w�l�j�����w�l  �X�V�O�́iW�w�l�j�����w�l  �ȊO�A�@@�i�����l�j
                if (l_orderUnitParams.getWLimitPriceIsNull())
                {
                    l_orderUnitParams.setOrgWLimitPrice(null);
                }
                else
                {
                    l_orderUnitParams.setOrgWLimitPrice(l_orderUnitParams.getWLimitPrice());
                }
                //���iW�w�l�j���s����   �X�V�O�́iW�w�l�j���s���� �ȊO�A�i�����l�j
                l_orderUnitParams.setOrgWLimitExecCondType(l_orderUnitParams.getWLimitExecCondType());
                //�iW�w�l�j���s����  �@@null  �ȊO�A�@@�i�����l�j
                l_orderUnitParams.setWLimitExecCondType(null);
                //�������� �@@0�FDEFAULT �ȊO�A�i�����l�j
                l_orderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.DEFAULT);
                //�����������Z�q  null �ȊO�A�i�����l�j
                l_orderUnitParams.setOrderCondOperator(null);
                //�t�w�l��l   null �ȊO�A�i�����l�j
                l_orderUnitParams.setStopOrderPrice(null);
                //�iW�w�l�j�����w�l   null �ȊO�A�i�����l�j
                l_orderUnitParams.setWLimitPrice(null);
                //�T�Z����v�Z���ʂ�null�ȊO�̏ꍇ
                if (this.eqyityEstimatedPrice != null)
                {
                    double l_dbCalcUnitPrice = this.eqyityEstimatedPrice.getCalcUnitPrice();
                    double l_dbEstimateDeliveryAmount = this.eqyityEstimatedPrice.getEstimateDeliveryAmount();
                    //�����P�� �T�Z����v�Z����.get�v�Z�P��
                    l_orderUnitParams.setPrice(l_dbCalcUnitPrice);
                    // �T�Z��n���  �T�Z����v�Z����.get�T�Z��n���
                    l_orderUnitParams.setEstimatedPrice(l_dbEstimateDeliveryAmount);
                    //�s�ꂩ��m�F�ς݂̒����P��  �T�Z����v�Z����.get�v�Z�P��  �ȊO�A�i�����l)
                    l_orderUnitParams.setConfirmedOrderPrice(l_dbCalcUnitPrice);
                    //�s�ꂩ��m�F�ς݂̊T�Z��n���  �T�Z����v�Z����.get�T�Z��n���  �ȊO�A�i�����l)
                    l_orderUnitParams.setConfirmedEstimatedPrice(l_dbEstimateDeliveryAmount);
                }
                //���������E����敪 D�FW�w�l�����ؑ֎��s
                l_orderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.W_LIMIT_TRANSFER_ERROR);
                //���N�G�X�g�^�C�v �@@5:���� �ȊO�A�i�����l)
                l_orderUnitParams.setRequestType(WEB3RequestTypeDef.INVALIDATE);
            }
            else
            {
                //���������E����敪 �ȊO�A8:�������s
                l_orderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.CHANGE_ERROR);
            }

            if (!l_blnStopOderSwitching || this.eqyityEstimatedPrice == null)
            {
                //�����P��  �ȊO�A�@@�s�ꂩ��m�F�ς݂̒����P��
                if (l_orderUnitParams.getConfirmedOrderPriceIsNull())
                {
                    l_orderUnitParams.setPrice(null);
                }
                else
                {
                    l_orderUnitParams.setPrice(l_orderUnitParams.getConfirmedOrderPrice());
                }

                //�T�Z��n���  �ȊO�A  �@@�s�ꂩ��m�F�ς݂̊T�Z��n���
                if (l_orderUnitParams.getConfirmedEstimatedPriceIsNull())
                {
                    l_orderUnitParams.setEstimatedPrice(null);
                }
                else
                {
                    l_orderUnitParams.setEstimatedPrice(l_orderUnitParams.getConfirmedEstimatedPrice());
                }
            }

            // �����G���[���R�R�[�h
            l_orderUnitParams.setErrorReasonCode(
                this.hostEqtypeOrderAcceptParams.getErrorMessage());

            log.exiting(STR_METHOD_NAME);
            return l_orderUnitParams;
        }
        
        //�h�������s�i��������j�FNOT_CANCELLED�h �̏ꍇ
        if (OrderStatusEnum.NOT_CANCELLED.equals(l_orderUnitParams.getOrderStatus()))
        {
            //���~�b�g�����L��(*1)�̏ꍇ
            //(*1)�����f�[�^�A�_�v�^.get�v�w�l�p�L����ԋ敪()��"���~�b�g�����L��"�̏ꍇ�A���~�b�g�����L���B
            //�@@�����ɐݒ肷�钍���P�ʂɂ́A�X�V�O�̒����P�ʂ��w�肷��
            //�@@�iEqtypeOrderUnitParams���g�����������}�l�[�W��.toOrderUnit()�ɂĒ����P�ʌ^�ɂ���j
            if (WEB3EquityWlimitEnableStatusDivDef.LIMIT_ENABLE.equals(l_strWLimitEnableStatusDiv))
            {
                //�@@����������  �X�V�O�̔�������  �ȊO�A�i�����l�j
                l_orderUnitParams.setOrgOrderConditionType(l_orderUnitParams.getOrderConditionType());
                //�@@�������������Z�q   �@@�X�V�O�̔����������Z�q   �ȊO�A�i�����l�j
                l_orderUnitParams.setOrgOrderCondOperator(l_orderUnitParams.getOrderCondOperator());
                //�@@���t�w�l��l   �@@�X�V�O�̋t�w�l��l     �ȊO�A�i�����l�j
                if (l_orderUnitParams.getStopOrderPriceIsNull())
                {
                    l_orderUnitParams.setOrgStopOrderPrice(null);
                }
                else
                {
                    l_orderUnitParams.setOrgStopOrderPrice(l_orderUnitParams.getStopOrderPrice());
                }
                //�@@���iW�w�l�j�����w�l    �X�V�O�́iW�w�l�j�����w�l    �ȊO�A�i�����l�j
                if (l_orderUnitParams.getWLimitPriceIsNull())
                {
                    l_orderUnitParams.setOrgWLimitPrice(null);
                }
                else
                {
                    l_orderUnitParams.setOrgWLimitPrice(l_orderUnitParams.getWLimitPrice());
                }
                //�@@���iW�w�l�j���s����  �X�V�O�́iW�w�l�j���s����   �ȊO�A�i�����l�j
                l_orderUnitParams.setOrgWLimitExecCondType(l_orderUnitParams.getWLimitExecCondType());
                //�@@�iW�w�l�j���s����   null   �ȊO�A�i�����l�j
                l_orderUnitParams.setWLimitExecCondType(null);
                //�@@��������      �@@0�FDEFAULT     �ȊO�A�i�����l�j
                l_orderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.DEFAULT);
                //�@@�����������Z�q   null   �ȊO�A�i�����l�j
                l_orderUnitParams.setOrderCondOperator(null);
                //�@@�t�w�l��l    null   �ȊO�A�i�����l�j
                l_orderUnitParams.setStopOrderPrice(null);
                //�@@�iW�w�l�j�����w�l   null   �ȊO�A�i�����l�j
                l_orderUnitParams.setWLimitPrice(null);
                //�T�Z����v�Z���ʂ�null�ȊO�̏ꍇ
                if (this.eqyityEstimatedPrice != null)
                {
                    double l_dbCalcUnitPrice = this.eqyityEstimatedPrice.getCalcUnitPrice();
                    double l_dbEstimateDeliveryAmount = this.eqyityEstimatedPrice.getEstimateDeliveryAmount();
                    //�@@�����P��  �T�Z����v�Z����.get�v�Z�P��  �ȊO�A�i�����l�j
                    l_orderUnitParams.setPrice(l_dbCalcUnitPrice);
                    //�@@�T�Z��n���  �T�Z����v�Z����.get�T�Z��n���
                    l_orderUnitParams.setEstimatedPrice(l_dbEstimateDeliveryAmount);
                    //�@@�s�ꂩ��m�F�ς݂̒����P��  �T�Z����v�Z����.get�v�Z�P��  �ȊO�A�i�����l�j
                    l_orderUnitParams.setConfirmedOrderPrice(l_dbCalcUnitPrice);
                    //�@@�s�ꂩ��m�F�ς݂̊T�Z��n���   �T�Z����v�Z����.get�T�Z��n���  �ȊO�A�i�����l�j
                    l_orderUnitParams.setConfirmedEstimatedPrice(l_dbEstimateDeliveryAmount);
                }
                //���N�G�X�g�^�C�v  5:����  �ȊO�A�i�����l�j
                l_orderUnitParams.setRequestType(WEB3RequestTypeDef.INVALIDATE);
            }

            if (!l_blnStopOderSwitching || this.eqyityEstimatedPrice == null)
            {
                //�T�Z��n���  �ȊO�A�s�ꂩ��m�F�ς݂̊T�Z��n���
                if (l_orderUnitParams.getConfirmedEstimatedPriceIsNull())
                {
                    l_orderUnitParams.setEstimatedPrice(null);
                }
                else
                {
                    l_orderUnitParams.setEstimatedPrice(l_orderUnitParams.getConfirmedEstimatedPrice());
                }
            }
            
            // ���������E����敪
            l_orderUnitParams.setModifyCancelType(
                WEB3ModifyCancelTypeDef.CANCEL_ERROR);

            // �����G���[���R�R�[�h
            l_orderUnitParams.setErrorReasonCode(
                this.hostEqtypeOrderAcceptParams.getErrorMessage());
               
            return l_orderUnitParams;
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
     * �����̒����P��Params.����ID�A�����P�ʂh�c�ɊY�����钍���P�ʃI�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �Q�j�@@�g�����ڃZ�b�g<BR>
     * �@@�������G���[���R�R�[�h<BR>
     * �@@�@@[this.����������t�L���[Params.������t���� == �h������t�����h�̏ꍇ]<BR>
     * �@@�@@�@@�p�����[�^.��������Params.�����G���[���R�R�[�h�ɁA�h0000:����h���Z�b�g����B<BR>
     * �@@�@@[��L�ȊO�̏ꍇ]<BR>
     * �@@�@@�@@�p�����[�^.��������Params.�����G���[���R�R�[�h�ɁA�����P�ʃI�u�W�F�N�g�̓������ڂ���l���Z�b�g����B<BR>
     * �@@<BR>
     * �@@�����̑��̍��ځ@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@<BR>
     * �@@�p�����[�^.��������Params�̊g�����ڂɁA�����P�ʃI�u�W�F�N�g�̓������ڂ���l���Z�b�g���A�ԋp����B<BR>
     * <BR>
     * �R�j�@@�������s�̏ꍇ�i�p�����[�^.��������Params.�������=="�������s�i�ύX�����j"�̏ꍇ�̂݁A<BR>
     * �@@�@@xTrade�W�����ڂ̍X�V�d�l���J�X�^�}�C�Y����B<BR>
     * �@@�@@��xTrade�W�������ł́A�������s���ɂ�<BR>
     * �@@�@@���������s�������s���O�̒����P�ʂ̒l���ݒ肳��Ă��܂����߁B<BR>
     * <BR>
     * �@@�@@�����P���iprice�j�F�@@�����P��.�w�l ���Z�b�g�B<BR>
     * �@@�@@�������ʁiquantity�j�F�@@�����P�ʂ̓����ڂ̒l���Z�b�g�B<BR>
     * �@@�@@�s��Ɗm�F�ς̎w�l�iconfirmed_price�j�F�@@�����P�ʂ̓����ڂ̒l���Z�b�g�B<BR>
     * �@@�@@�s��Ɗm�F�ς̐��ʁiconfirmed_quantity�j�F�@@�����P�ʂ̓����ڂ̒l���Z�b�g�B<BR>
     * <BR>
     * @@param l_orderManagerPersistenceType - (�X�V�^�C�v)<BR>
     * INSERT�܂��́AUPDATE�B<BR>
     * EqTypeOrderManagerPersistenceType�ɂĒ萔��`�B<BR>
     * <BR>
     * @@param l_orderManagerPersistenceContext - (����)<BR>
     * �iEqTypeOrderManagerPersistenceContext�ɂĒ萔��`�j<BR>
     * <BR>
     * @@param l_orderActionParams - (������������Params)<BR>
     * ���������������ێ����鍀�ڂ̃p�����[�^�N���X�B<BR>
     * @@return EqtypeOrderActionParams
     * @@roseuid 4110B7CC004A
     */
    public EqtypeOrderActionParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        EqtypeOrderActionParams l_orderActionParams)
    {
        final String STR_METHOD_NAME = 
            "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, EqtypeOrderActionParams)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderActionParams == null)
        {
            throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        EqTypeOrderManager l_orderManager = (EqTypeOrderManager)l_tradingMod.getOrderManager();               
        EqTypeOrderUnit l_orderUnit = null;
        try 
        {
            l_orderUnit =
                (EqTypeOrderUnit)l_orderManager.getOrderUnit(
                    l_orderActionParams.getOrderUnitId());
        } 
        catch (NotFoundException l_ex) 
        {
            log.error(l_ex.getMessage() , l_ex);
            throw new WEB3BaseRuntimeException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80017,
            this.getClass().getName() + "." + STR_METHOD_NAME);     
        }
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();

        l_orderActionParams =
            super.mutate(
                l_orderManagerPersistenceType,
                l_orderManagerPersistenceContext,
                l_orderActionParams);
        
        if (WEB3AcceptStatusDef.OVER.equals(this.hostEqtypeOrderAcceptParams.getAcceptStatus()))
        {
            l_orderActionParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);    
        }
        else 
        {
            l_orderActionParams.setErrorReasonCode(l_orderUnitRow.getErrorReasonCode());    
        }
        
        if (OrderStatusEnum.NOT_MODIFIED.equals(l_orderActionParams.getOrderStatus()))
        {
            l_orderActionParams.setPrice(l_orderUnitRow.getLimitPrice());
            l_orderActionParams.setQuantity(l_orderUnitRow.getQuantity());
            l_orderActionParams.setConfirmedPrice(l_orderUnitRow.getConfirmedPrice());
            l_orderActionParams.setConfirmedQuantity(l_orderUnitRow.getConfirmedQuantity());
        }
        
        return l_orderActionParams;
    }

    /**
     * (get�T�Z����v�Z����)<BR>
     * �T�Z����v�Z���ʂ��擾����B<BR>
     * @@return WEB3EquityEstimatedPrice
     */
    public WEB3EquityEstimatedPrice getEquityEstimatedPrice()
    {
        return this.eqyityEstimatedPrice;
    }

    /**
     * (set�T�Z����v�Z����)<BR>
     * �T�Z����v�Z���ʂ��Z�b�g����B<BR>
     * <BR>
     * [����]<BR>
     * �@@�������A�v���p�e�B�u�T�Z����v�Z���ʁv�ɃZ�b�g����B<BR>
     * @@param l_equityEstimatedPrice - (�T�Z����v�Z����)<BR>
     * �T�Z����v�Z����<BR>
     */
    public void setEquityEstimatedPrice(WEB3EquityEstimatedPrice l_equityEstimatedPrice)
    {
        this.eqyityEstimatedPrice = l_equityEstimatedPrice;
    }
}
@
