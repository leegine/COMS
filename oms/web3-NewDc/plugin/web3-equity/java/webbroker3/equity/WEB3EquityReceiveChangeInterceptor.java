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
filename	WEB3EquityReceiveChangeInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �������������ʒm�C���^�Z�v�^(WEB3EquityReceiveChangeInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/03 ���j (���u) �V�K�쐬
Revesion History : 2004/12/10 ���� (SRA) �c�Č��Ή�
Revesion History : 2005/01/05 ���� (SRA) JavaDoc�C��
Revesion History : 2006/11/03 �đo�g (���u) �c�a�X�V�d�lNo.179
Revesion History : 2006/11/28 �đo�g (���u) �c�a�X�V�d�lNo.185
*/
package webbroker3.equity;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3CanmodReceiptTypeDef;
import webbroker3.common.define.WEB3ModifiedResultDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.common.define.WEB3ShortSellingCountMethodDivDef;
import webbroker3.equity.data.HostEqtypeOrderClmdReceiptParams;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�������������ʒm�C���^�Z�v�^�j�B<BR>
 * <BR>
 * �ȉ��̃P�[�X�Ŏg�p����A�����f�[�^�X�V�d�l�J�X�^�}�C�Y�p�̃N���X�B<BR>
 * <BR>
 * �EWebBroker���̒��������ɑ΂���A�ʒm��M���̒����P�ʂ̍X�V<BR>
 * �ESONAR���̒��������y�ђʒm��M���̒����P�ʂ̍X�V
 * @@version 1.0
 */
public class WEB3EquityReceiveChangeInterceptor
    extends WEB3EquityOrderManagerPersistenceEventInterceptor
{
    /**
     * (���O�o�̓��[�e�B���e�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityReceiveChangeInterceptor.class);

    /**
     * (������������ʒm�L���[Params)<BR>
     */
    private HostEqtypeOrderClmdReceiptParams changeCancelNotifyQueueParams;

    /**
     * (���������ʒm���e)<BR>
     */
    private WEB3EquityReceiveChangeSpec changeNotifySpec;

    public WEB3EquityReceiveChangeInterceptor()
    {

    }
    
    /**
     * (�������������ʒm�C���^�Z�v�^)<BR>
     * �R���X�g���N�^�B<BR>
     * �����̊�����������ʒm�L���[Params�I�u�W�F�N�g���A�v���p�e�B�ɐݒ肷��B<BR>
     * <BR>
     * @@param l_changeCancelNotifyQueueParams - ������������ʒm�L���[Params<BR>
     * @@return webbroker3.equity.WEB3EquityOrderReceiveChangeInterceptor<BR>
     * @@roseuid 4039F7390118<BR>
     */
    public WEB3EquityReceiveChangeInterceptor(HostEqtypeOrderClmdReceiptParams l_hostEqtypeOrderClmdReceiptParams)
    {
        this.changeCancelNotifyQueueParams = l_hostEqtypeOrderClmdReceiptParams;
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
     * �i������Ԃ��h�����ς݁i�ύX�����j�FMODIFIED�h�܂��́h�������s�i�ύX�����j�FNOT_<BR>
     * MODIFIED�h�ɊY������ꍇ�̂݁A���������{����B�ȊO�́A���������̂܂ܕԋp��������<BR>
     * �I������j<BR>
     * �@@�����P��Params�̒l���u�����ʒm�v�̏�Ԃɐݒ肵�ԋp����B<BR>
     * <BR>
     * �X�V���e�́A�u������������ʒm_���������P�ʃe�[�u��.xls�v�Q�ƁB<BR>
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
     * @@return com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams<BR>
     * @@roseuid 4039F7390114<BR>
     */
    public EqtypeOrderUnitParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        EqtypeOrderUnitParams l_orderUnitParams)
    {
        final String STR_METHOD_NAME =
            "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, EqtypeOrderUnitParams)";
        log.entering(STR_METHOD_NAME);

        OrderStatusEnum l_orderStatus = l_orderUnitParams.getOrderStatus();
        if (OrderStatusEnum.MODIFIED.equals(l_orderStatus) ||
            OrderStatusEnum.NOT_MODIFIED.equals(l_orderStatus))
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderManager.toOrderUnit(l_orderUnitParams);
            EqTypeOrderUnit l_orderUnitBefore = null;
            try
            {
                l_orderUnitBefore =
                    (EqTypeOrderUnit)l_orderManager.getOrderUnit(l_orderUnitParams.getOrderUnitId());
            }
            catch (NotFoundException l_nfe)
            {
                log.error(STR_METHOD_NAME, l_nfe);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);
            }
            EqtypeOrderUnitRow l_orderUnitRow =
                (EqtypeOrderUnitRow)l_orderUnitBefore.getDataSourceObject();
            EqtypeOrderUnitParams l_orderUnitParamBefore = new EqtypeOrderUnitParams(l_orderUnitRow);
            boolean l_blnStopOderSwitching = false;
            try
            {
                l_blnStopOderSwitching = l_orderManager.isStopOrderSwitching(l_orderUnit);
            }
            catch (WEB3BaseException l_exc)
            {
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_exc.getMessage(),
                    l_exc);
            }

            //����������������ʒm�L���[�e�[�u��.��������ʒm�敪
            String l_strCanmodReceiptType = 
                this.changeCancelNotifyQueueParams.getCanmodReceiptType();

            //��������
            l_orderUnitParams.setQuantity(
                this.changeCancelNotifyQueueParams.getModifiedQuantity());

            //�w�l
            l_orderUnitParams.setLimitPrice(
                this.changeCancelNotifyQueueParams.getModifiedLimitPrice());

            //���s����
            l_orderUnitParams.setExecutionConditionType(
                this.changeNotifySpec.getChangeAfterExecCondType());
                
            //�l�i����
            l_orderUnitParams.setPriceConditionType(
                this.changeNotifySpec.getChangeAfterPriceConditionType());


            //�X�g�b�v�����ؑ֒�(*1�j�̏ꍇ
            //(*1)�g�����������}�l�[�W��.is�X�g�b�v�����ؑ֒�()��true�̏ꍇ�A�X�g�b�v�����ؑ֒��B
            //�����ɐݒ肷�钍���P�ʂɂ́A�X�V�O�̒����P�ʂ��w�肷��
            //�iEqtypeOrderUnitParams���g�����������}�l�[�W��.toOrderUnit()�ɂĒ����P�ʌ^�ɂ���j
            //(*2)�X�g�b�v�����ؑ֒�(*1)�A���A����������������ʒm�L���[�e�[�u��.
            //��������ʒm�敪 == �h���������h�̏ꍇ�A�X�g�b�v�����ؑ�OK�B
            if (l_blnStopOderSwitching)
            {
                //���������� �X�V�O�̔��������ȊO�A�i�����l�j
                l_orderUnitParams.setOrgOrderConditionType(
                    l_orderUnitParamBefore.getOrderConditionType());

                //�������������Z�q  �X�V�O�̔����������Z�q�ȊO�A�i�����l�j
                l_orderUnitParams.setOrgOrderCondOperator(
                    l_orderUnitParamBefore.getOrderCondOperator());

                //���t�w�l��l  �X�V�O�̋t�w�l��l �ȊO�A�i�����l�j
                if (l_orderUnitParamBefore.getStopOrderPriceIsNull())
                {
                    l_orderUnitParams.setOrgStopOrderPrice(null);
                }
                else
                {
                    l_orderUnitParams.setOrgStopOrderPrice(
                        l_orderUnitParamBefore.getStopOrderPrice());
                }

                //���iW�w�l�j�����w�l �X�V�O�́iW�w�l�j�����w�l �ȊO�A�i�����l�j
                if (l_orderUnitParamBefore.getWLimitPriceIsNull())
                {
                    l_orderUnitParams.setOrgWLimitPrice(null);
                }
                else
                {
                    l_orderUnitParams.setOrgWLimitPrice(
                        l_orderUnitParamBefore.getWLimitPrice());
                }

                //���iW�w�l�j���s����  �X�V�O�́iW�w�l�j���s���� �ȊO�A�i�����l�j
                l_orderUnitParams.setOrgWLimitExecCondType(
                    l_orderUnitParamBefore.getWLimitExecCondType());

                //�iW�w�l�j���s���� null  �ȊO�A�i�����l�j
                l_orderUnitParams.setWLimitExecCondType(null);

                //�X�g�b�v�����ؑ�OK(*2�j
                if (WEB3CanmodReceiptTypeDef.CHANGED.equals(l_strCanmodReceiptType))
                {
                    //�iW�w�l�j�֑ؑO�w�l �X�V�O�̎s�ꂩ��m�F�ς݂̎w�l �ȊO�A�i�����l�j
                    if (l_orderUnitParamBefore.getConfirmedPriceIsNull())
                    {
                        l_orderUnitParams.setWLimitBeforeLimitPrice(null);
                    }
                    else
                    {
                        l_orderUnitParams.setWLimitBeforeLimitPrice(
                            l_orderUnitParamBefore.getConfirmedPrice());
                    }
    
                    //�iW�w�l�j�֑ؑO���s���� �X�V�O�̎s�ꂩ��m�F�ς݂̎��s���� �ȊO�A�i�����l�j
                    l_orderUnitParams.setWLimitBeforeExecCondType(
                        l_orderUnitParamBefore.getConfirmedExecConditionType());
                }

                //�������� 0�FDEFAULT�i�����w��Ȃ��j �ȊO�A�i�����l�j
                l_orderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.DEFAULT);

                //�����������Z�q null �ȊO�A�i�����l�j
                l_orderUnitParams.setOrderCondOperator(null);

                //�t�w�l��l null �ȊO�A�i�����l�j
                l_orderUnitParams.setStopOrderPrice(null);

                //�iW�w�l�j�����w�l null �ȊO�A�i�����l�j
                l_orderUnitParams.setWLimitPrice(null);
            }

            //�s�ꂩ��m�F�ς݂̐���
            l_orderUnitParams.setConfirmedQuantity(
                this.changeCancelNotifyQueueParams.getModifiedQuantity());

            //�s�ꂩ��m�F�ς݂̎w�l
            l_orderUnitParams.setConfirmedPrice(
                this.changeCancelNotifyQueueParams.getModifiedLimitPrice());
                
            //�����L�����
            if (OrderStatusEnum.MODIFIED.equals(l_orderStatus))
            {
                if (l_orderUnitParams.getConfirmedQuantity() > l_orderUnitParams.getExecutedQuantity())
                {
                    l_orderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
                }
                else
                {
                    l_orderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
                }
            }
            
            //�����P��
            l_orderUnitParams.setPrice(this.changeNotifySpec.getLimitPrice());

            //�T�Z��n���
            l_orderUnitParams.setEstimatedPrice(
                this.changeNotifySpec.getEstimateDeliveryAmount());

            //���������E����敪
            String l_strModifiedResult =
                this.changeCancelNotifyQueueParams.getModifiedResult();
            if (WEB3ModifiedResultDef.ALL_CHANGED_NO_EXECUTED.equals(l_strModifiedResult) ||
                WEB3ModifiedResultDef.ALL_CHANGED_PARTIALLY_EXECUTED.equals(l_strModifiedResult))
            {
                //�X�g�b�v�����ؑ֒�(*1)
                if (l_blnStopOderSwitching)
                {
                    //C:W�w�l�����S���ؑ֊���
                    l_orderUnitParams.setModifyCancelType(
                        WEB3ModifyCancelTypeDef.W_LIMIT_TRANSFERED);
                }
                else
                {
                    // �ȊO�A7:�S����������
                    l_orderUnitParams.setModifyCancelType(
                        WEB3ModifyCancelTypeDef.CHANGED);
                }
            }
            else if (WEB3ModifiedResultDef.PARTIALLY_CHANGED.equals(l_strModifiedResult) ||
                 WEB3ModifiedResultDef.PARTIALLY_NOT_CHANGED_NO_EXECUTED.equals(l_strModifiedResult) ||
                 WEB3ModifiedResultDef.PARTIALLY_NOT_CHANGED_PARTIALLY_EXECUTED.equals(l_strModifiedResult))
            {
                //�X�g�b�v�����ؑ֒�(*1)
                if (l_blnStopOderSwitching)
                {
                    //B:W�w�l�����ꕔ�ؑ֊���
                    l_orderUnitParams.setModifyCancelType(
                        WEB3ModifyCancelTypeDef.W_LIMIT_PARTIALLY_TRANSFERED);
                }
                else
                {
                    //�ȊO�A6:�ꕔ��������
                    l_orderUnitParams.setModifyCancelType(
                        WEB3ModifyCancelTypeDef.PARTIALLY_CHANGED);
                }
            }
            else
            {
                //�X�g�b�v�����ؑ֒�(*1)
                if (l_blnStopOderSwitching)
                {
                    //D:W�w�l�����ؑ֎��s
                    l_orderUnitParams.setModifyCancelType(
                        WEB3ModifyCancelTypeDef.W_LIMIT_TRANSFER_ERROR);
                }
                else
                {
                    //�ȊO�A8:�������s
                    l_orderUnitParams.setModifyCancelType(
                        WEB3ModifyCancelTypeDef.CHANGE_ERROR);
                }
            }

            //�����o�H�敪
            if (this.changeCancelNotifyQueueParams.getModSubmitOrderRouteDiv() != null)
            {
                l_orderUnitParams.setSubmitOrderRouteDiv(
                    this.changeCancelNotifyQueueParams.getModSubmitOrderRouteDiv());
            }
            
            //�s�ꂩ��m�F�ς݂̒����P��
            l_orderUnitParams.setConfirmedOrderPrice(
                this.changeNotifySpec.getLimitPrice());
            
            //�s�ꂩ��m�F�ς݂̊T�Z��n���
            l_orderUnitParams.setConfirmedEstimatedPrice(
                this.changeNotifySpec.getEstimateDeliveryAmount());

            //�s�ꂩ��m�F�ς݂̎��s����            
            l_orderUnitParams.setConfirmedExecConditionType(
                this.changeNotifySpec.getChangeAfterExecCondType());

            //���N�G�X�g�^�C�v
            //�X�g�b�v�����ؑ֒�(*1)�̏ꍇ�A
            if (l_blnStopOderSwitching)
            {
                if (WEB3CanmodReceiptTypeDef.CHANGED.equals(l_strCanmodReceiptType))
                {
                    //����������������ʒm�L���[�e�[�u��.��������ʒm�敪 == �h���������h
                    // �ł���΁A�h2�F�ؑ֊����h
                    l_orderUnitParams.setRequestType(WEB3RequestTypeDef.TRANSFERED);
                }
                else if (WEB3CanmodReceiptTypeDef.CHANGE_FAILED.equals(l_strCanmodReceiptType))
                {
                    //����������������ʒm�L���[�e�[�u��.��������ʒm�敪 == �h�������s�h
                    //  �ł���΁A�h5�F�����h
                    l_orderUnitParams.setRequestType(WEB3RequestTypeDef.INVALIDATE);
                }
            }

            //�s�ꂩ��m�F�ς݂̒l�i����
            l_orderUnitParams.setConfirmedPriceConditionType(
                this.changeNotifySpec.getChangeAfterPriceConditionType());
            
            //�s�ꂩ��m�F�ς݂̒���Rev.
            l_orderUnitParams.setConfirmedOrderRev(
                this.changeNotifySpec.getChangeAfterOrderRev());
                
            //����������
            try 
            {
                //���ݎ����ɊY������󔄂�K�����ԃe�[�u��.�󔄂萔�ʌv����@@=="�ǈ����Ōv��"�̏ꍇ�A
                //������������ʒm�L���[�e�[�u��.�����㐔��
                String l_strShortSelCountMethodDiv =
                    l_orderManager.getShortSellingRestraintTime().getShortSellingCountMethodDiv();
                if (WEB3ShortSellingCountMethodDivDef.COUNT_AS_CLOSE.equals(l_strShortSelCountMethodDiv))
                {
                    l_orderUnitParams.setOriginalQuantity(
                        this.changeCancelNotifyQueueParams.getModifiedQuantity());                
                }
            } 
            catch (WEB3BaseException e) 
            {
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    e.getMessage(),
                    e);
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_orderUnitParams;
    }

    /**
     * (get���������ʒm���e)<BR>
     * ���������ʒm���e�I�u�W�F�N�g���擾����B<BR>
     * @@return WEB3EquityReceiveChangeSpec<BR>
     * @@roseuid 4045841B038A<BR>
     */
    public WEB3EquityReceiveChangeSpec getEquityChangeNotifySpec()
    {
        return this.changeNotifySpec;
    }

    /**
     * (set���������ʒm���e)<BR>
     * ���������ʒm���e�I�u�W�F�N�g���Z�b�g����B<BR>
     * @@param l_changeNotifySpec - (���������ʒm���e)<BR>
     * ���������ʒm���e�I�u�W�F�N�g<BR>
     * @@roseuid 4045841C0000<BR>
     */
    public void setEquityChangeNotifySpec(WEB3EquityReceiveChangeSpec l_receiveChangeSpec)
    {
        this.changeNotifySpec = l_receiveChangeSpec;
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
     * �Q�j�@@super.mutate(EqtypeOrderActionParams)���R�[������B<BR>
     * <BR>
     * �R�j�@@xTrade�W�����ڂ̍X�V�d�l���J�X�^�}�C�Y����B<BR>
     * �@@�@@��xTrade�W�������ł́A<BR>
     * �@@�@@���������s���ɂ͒�������ʒm���s���O�̒����P�ʂ̒l���ݒ肳��Ă��܂����߁B<BR>
     * <BR>
     * �@@�@@�����P���iprice�j�F�@@�����P��.�w�l ���Z�b�g�B<BR>
     * �@@�@@�������ʁiquantity�j�F�@@�����P�ʂ̓����ڂ̒l���Z�b�g�B<BR>
     * �@@�@@�s��Ɗm�F�ς̎w�l�iconfirmed_price�j�F�@@�����P�ʂ̓����ڂ̒l���Z�b�g�B<BR>
     * �@@�@@�s��Ɗm�F�ς̐��ʁiconfirmed_quantity�j�F�@@�����P�ʂ̓����ڂ̒l���Z�b�g�B<BR>
     * <BR>
     * @@param l_orderManagerPersistenceType - (�X�V�^�C�v)<BR>
     * INSERT�܂��́AUPDATE�B
     * @@param l_orderManagerPersistenceContext - (����)<BR>
     * �iEqTypeOrderManagerPersistenceContext�ɂĒ萔��`�j
     * @@param l_orderActionParams - (������������Params)<BR>
     * ���������������ێ����鍀�ڂ̃p�����[�^�N���X�B
     * @@return EqtypeOrderActionParams
     */
    public EqtypeOrderActionParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        EqtypeOrderActionParams l_orderActionParams)
    {
        final String STR_METHOD_NAME = 
            "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, EqtypeOrderActionParams)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager = (WEB3EquityOrderManager)l_tradingMod.getOrderManager();
        EqTypeOrderUnit l_orderUnit = null;
        try
        {
            l_orderUnit =
                (EqTypeOrderUnit)l_orderManager.getOrderUnit(
                    l_orderActionParams.getOrderUnitId());
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        
        l_orderActionParams = super.mutate(
            l_orderManagerPersistenceType,
            l_orderManagerPersistenceContext,
            l_orderActionParams);
        
        l_orderActionParams.setPrice(l_orderUnitRow.getLimitPrice());
        l_orderActionParams.setQuantity(l_orderUnitRow.getQuantity());
        l_orderActionParams.setConfirmedPrice(l_orderUnitRow.getConfirmedPrice());
        l_orderActionParams.setConfirmedQuantity(l_orderUnitRow.getConfirmedQuantity());
        
        log.entering(STR_METHOD_NAME);
        return l_orderActionParams;
    }
}
@
