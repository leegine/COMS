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
filename	WEB3EquityReceiveCancelInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ������������ʒm�C���^�Z�v�^(WEB3EquityReceiveCancelInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/03 ���j (���u) �V�K�쐬
Revesion History : 2004/12/10 ���� (SRA) �c�Č��Ή�
Revesion History : 2005/01/05 ���� (SRA) JavaDoc�C��
Revesion History : 2006/11/28 �đo�g (���u) �c�a�X�V�d�lNo.186,No.187
*/
package webbroker3.equity;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

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
import webbroker3.equity.define.WEB3EquityWlimitEnableStatusDivDef;
import webbroker3.util.WEB3LogUtility;

/**
 * �i������������ʒm�C���^�Z�v�^�j�B
 * @@version 1.0
 */
public class WEB3EquityReceiveCancelInterceptor
    extends WEB3EquityOrderManagerPersistenceEventInterceptor
{
    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityReceiveCancelInterceptor.class);
    
    /**
     * (������������ʒm�L���[Params)<BR>
     */
    private HostEqtypeOrderClmdReceiptParams hostEqtypeOrderClmdReceiptParams;

    /**
     * (��������ʒm���e)<BR>
     */
    private WEB3EquityReceiveCancelSpec receiveCancelSpec;

    /**
     * @@roseuid 40A99E0601E4<BR>
     */
    public WEB3EquityReceiveCancelInterceptor()
    {

    }

    /**
     * (������������ʒm�C���^�Z�v�^)<BR>
     * 
     * �R���X�g���N�^�B<BR>
     * �����̊�����������ʒm�L���[Params�I�u�W�F�N�g���A�v���p�e�B�ɐݒ肷��B<BR>
     * @@param l_quoteParams - ������������ʒm�L���[Params<BR>
     * @@return webbroker3.equity.WEB3EquityReceiveCancelInterceptor<BR>
     * @@roseuid 403DE09901AF<BR>
     */
    public WEB3EquityReceiveCancelInterceptor(HostEqtypeOrderClmdReceiptParams l_hostEqtypeOrderClmdReceiptParams)
    {
        this.hostEqtypeOrderClmdReceiptParams = l_hostEqtypeOrderClmdReceiptParams;
    }

    /**
     * (�X�V�l�ݒ�)<BR>
     * 
     * �imutate���\�b�h�̎����j<BR>
     * �����P��Params�Ɋg������(*)�ݒ肵�ԋp����B<BR>
     * 
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB<BR>
     * (*) xTrade�}�j���A���u�X�V���e�̃J�X�^�}�C�Y�v�Q�ƁB<BR>
     * 
     * �P�j �g�����ڃZ�b�g<BR>
     * �i������Ԃ��h�����ς݁i��������j�FCANCELLED�h�܂��́h�������s�i��������j�FNOT<BR>
     * _CANCELLED�h�̏ꍇ�̂ݏ��������{����B<BR>
     * �@@�ȊO�́A���������̂܂ܕԋp���������I������j<BR>
     * �@@�����P��Params�̒l���u����ʒm�v�̏�Ԃɐݒ肵�ԋp����B<BR>
     * 
     * �X�V���e�́A�u������������ʒm_���������P�ʃe�[�u��.xls�v�Q�ƁB<BR>
     * <BR>
     * @@param l_orderManagerPersistenceType - (�X�V�^�C�v)<BR>
     * INSERT�܂��́AUPDATE�B<BR>
     * OrderManagerPersistenceType�ɂĒ萔��`�B<BR> 
     * @@param l_orderManagerPersistenceContext - (����)<BR>
     * �iOrderManagerPersistenceContext�ɂĒ萔��`�j<BR>
     * @@param l_orderUnitParams - (�����P��Params)<BR>
     * ���������P�ʂ��ێ����鍀�ڂ̃p�����[�^�N���X�B<BR>
     * @@return EqtypeOrderUnitParams<BR>
     * @@roseuid 403DE09901AB<BR>
     */
    public EqtypeOrderUnitParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        EqtypeOrderUnitParams l_orderUnitParams)
    {
        final String STR_METHOD_NAME = 
            "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, EqtypeOrderUnitParams)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderManager.toOrderUnit(l_orderUnitParams);

        OrderStatusEnum l_orderStatus = l_orderUnitParams.getOrderStatus();
        if (OrderStatusEnum.CANCELLED.equals(l_orderStatus) ||
            OrderStatusEnum.NOT_CANCELLED.equals(l_orderStatus))
        {
            //�w�l
            l_orderUnitParams.setLimitPrice(
                this.hostEqtypeOrderClmdReceiptParams.getModifiedLimitPrice());

            //���s����
            l_orderUnitParams.setExecutionConditionType(
                this.receiveCancelSpec.getChangeAfterExecCond());
            
            //�l�i����
            l_orderUnitParams.setPriceConditionType(
                this.receiveCancelSpec.getChangeAfterPriceConditionType());

            //������s�����~�b�g�����L��(*1�j�̏ꍇ
            //(*1)
            //�ʒm�L���[.��������ʒm�敪="������s"�̏ꍇ�A������s�B
            //�����f�[�^�A�_�v�^.get�v�w�l�p�L����ԋ敪()��"���~�b�g�����L��"�̏ꍇ�A
            //�@@���~�b�g�����L���B
            //�����ɐݒ肷�钍���P�ʂɂ́A�X�V�O�̒����P�ʂ��w�肷��
            boolean l_blnCanmodReceiptType = false;
            if (WEB3CanmodReceiptTypeDef.CANCEL_FAILED.equals(
                this.hostEqtypeOrderClmdReceiptParams.getCanmodReceiptType()))
            {
                l_blnCanmodReceiptType = true;
            }
            String l_strWLimitEnableStatusDiv = null;
            try
            {
                l_strWLimitEnableStatusDiv = WEB3EquityDataAdapter.getWLimitEnableStatusDiv(l_orderUnit);
            }
            catch(WEB3BaseException l_exc)
            {
                log.debug(STR_METHOD_NAME, l_exc);
                throw new WEB3BaseRuntimeException(
                    l_exc.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_exc.getMessage(),
                    l_exc);
            }
            boolean l_blnStopEnableType = false;
            if (WEB3EquityWlimitEnableStatusDivDef.LIMIT_ENABLE.equals(l_strWLimitEnableStatusDiv))
            {
                l_blnStopEnableType = true;
            }
            if (l_blnCanmodReceiptType && l_blnStopEnableType)
            {
                //����������  �X�V�O�̔�������  �ȊO�A�i�����l�j
                l_orderUnitParams.setOrgOrderConditionType(l_orderUnitParams.getOrderConditionType());
                //�������������Z�q  �X�V�O�̔����������Z�q  �ȊO�A�i�����l�j
                l_orderUnitParams.setOrgOrderCondOperator(l_orderUnitParams.getOrderCondOperator());
                //���t�w�l��l  �X�V�O�̋t�w�l��l  �ȊO�A�i�����l�j
                if (l_orderUnitParams.getStopOrderPriceIsNull())
                {
                    l_orderUnitParams.setOrgStopOrderPrice(null);
                }
                else
                {
                    l_orderUnitParams.setOrgStopOrderPrice(l_orderUnitParams.getStopOrderPrice());
                }
                //���iW�w�l�j�����w�l  �X�V�O�́iW�w�l�j�����w�l  �ȊO�A�i�����l�j
                if (l_orderUnitParams.getWLimitPriceIsNull())
                {
                    l_orderUnitParams.setOrgWLimitPrice(null);
                }
                else
                {
                    l_orderUnitParams.setOrgWLimitPrice(l_orderUnitParams.getWLimitPrice());
                }
                //���iW�w�l�j���s����  �X�V�O�́iW�w�l�j���s����  �ȊO�A�i�����l�j
                l_orderUnitParams.setOrgWLimitExecCondType(l_orderUnitParams.getWLimitExecCondType());
                //�iW�w�l�j���s����  null  �ȊO�A�i�����l�j
                l_orderUnitParams.setWLimitExecCondType(null);
                //�������� 0�FDEFAULT�i�����w��Ȃ��j �ȊO�A�i�����l�j
                l_orderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.DEFAULT);
                //�����������Z�q null �ȊO�A�i�����l�j
                l_orderUnitParams.setOrderCondOperator(null);
                //�t�w�l��l null �ȊO�A�i�����l�j
                l_orderUnitParams.setStopOrderPrice(null);
                //�iW�w�l�j�����w�l null �ȊO�A�i�����l�j
                l_orderUnitParams.setWLimitPrice(null);
                //���N�G�X�g�^�C�v  5:����  �ȊO�A�i�����l�j
                l_orderUnitParams.setRequestType(WEB3RequestTypeDef.INVALIDATE);
            }
            //�s�ꂩ��m�F�ς݂̎w�l
            l_orderUnitParams.setConfirmedPrice(
                this.hostEqtypeOrderClmdReceiptParams.getModifiedLimitPrice());

            //�����P��  ��������ʒm���e.�����P��
            l_orderUnitParams.setPrice(this.receiveCancelSpec.getLimitPrice());

            //�T�Z��n���
            l_orderUnitParams.setEstimatedPrice(
                this.receiveCancelSpec.getEstimatedPrice());
            
            //���������E����敪
            String l_strModifiedResult = this.hostEqtypeOrderClmdReceiptParams.getModifiedResult();
            if (WEB3ModifiedResultDef.ALL_CANCEL.equals(l_strModifiedResult))
            {
                l_orderUnitParams.setModifyCancelType(
                    WEB3ModifyCancelTypeDef.CANCELED);
            }
            else if (WEB3ModifiedResultDef.PARTIALLY_CANCEL.equals(l_strModifiedResult) ||
                     WEB3ModifiedResultDef.PARTIALLY_NOT_CANCEL_NO_EXECUTED.equals(l_strModifiedResult) ||
                     WEB3ModifiedResultDef.PARTIALLY_NOT_CANCEL_EXECUTED.equals(l_strModifiedResult))
            {
                l_orderUnitParams.setModifyCancelType(
                    WEB3ModifyCancelTypeDef.PART_CANCELED);
            }
            else
            {
                l_orderUnitParams.setModifyCancelType(
                    WEB3ModifyCancelTypeDef.CANCEL_ERROR);
            }
            
            //�����o�H�敪
            if (this.hostEqtypeOrderClmdReceiptParams.getModSubmitOrderRouteDiv() != null)
            {
                l_orderUnitParams.setSubmitOrderRouteDiv(
                    this.hostEqtypeOrderClmdReceiptParams.getModSubmitOrderRouteDiv());
            }

            //�s�ꂩ��m�F�ς݂̒����P��   ��������ʒm���e.�����P��
            l_orderUnitParams.setConfirmedOrderPrice(this.receiveCancelSpec.getLimitPrice());

            //�s�ꂩ��m�F�ς݂̊T�Z��n���
            l_orderUnitParams.setConfirmedEstimatedPrice(
                this.receiveCancelSpec.getEstimatedPrice());
            
            //�s�ꂩ��m�F�ς݂̎��s����
            l_orderUnitParams.setConfirmedExecConditionType(
                this.receiveCancelSpec.getChangeAfterExecCond());
            
            //�s�ꂩ��m�F�ς݂̒l�i����
            l_orderUnitParams.setConfirmedPriceConditionType(
                this.receiveCancelSpec.getChangeAfterPriceConditionType());
                
            //����������
            try 
            {
                //���ݎ����ɊY������󔄂�K�����ԃe�[�u��.�󔄂萔�ʌv����@@=="�ǈ����Ōv��"�̏ꍇ�A
                //������������ʒm�L���[�e�[�u��.�����㐔�ʁ�0�N���A�����
                String l_strShortSelCountMethodDiv =
                    l_orderManager.getShortSellingRestraintTime().getShortSellingCountMethodDiv();
                if (WEB3ShortSellingCountMethodDivDef.COUNT_AS_CLOSE.equals(l_strShortSelCountMethodDiv))
                {
                    l_orderUnitParams.setOriginalQuantity(
                        this.hostEqtypeOrderClmdReceiptParams.getModifiedQuantity());                
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
     * (get��������ʒm���e)<BR>
     * 
     * ��������ʒm���e�I�u�W�F�N�g���擾����B<BR>
     * @@roseuid 4045847702A0<BR>
     */
    public WEB3EquityReceiveCancelSpec getReceiveCancelSpec()
    {
        return this.receiveCancelSpec;
    }

    /**
     * (set��������ʒm���e)<BR>
     * 
     * ��������ʒm���e�I�u�W�F�N�g���Z�b�g����B<BR>
     * @@roseuid 40458477030D<BR>
     */
    public void setReceiveCancelSpec(WEB3EquityReceiveCancelSpec l_equityReceiveCancelSpec)
    {
        this.receiveCancelSpec = l_equityReceiveCancelSpec;
    }
}
@
