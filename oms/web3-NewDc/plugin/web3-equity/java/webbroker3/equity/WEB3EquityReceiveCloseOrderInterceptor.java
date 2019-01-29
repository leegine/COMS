head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityReceiveCloseOrderInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �������������C���^�Z�v�^(WEB3EquityReceiveCloseOrderInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/25 ���j (���u) �V�K�쐬
Revesion History : 2004/09/20 羐� (���u) �C��
Revesion History : 2005/01/05 ���� (SRA) JavaDoc�C��
Revesion History : 2006/11/28 �đo�g (���u) �c�a�X�V�d�lNo.188
*/
package webbroker3.equity;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.equity.define.WEB3EquityWlimitEnableStatusDivDef;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�������������C���^�Z�v�^�j�B<BR>
 * <BR>
 * ���������P�ʁA�������������e�[�u���̃J�X�^�}�C�Y���ڂɎ������̒l���Z�b�g����B
 * @@author lufaxu
 * @@version 1.0
 */
public class WEB3EquityReceiveCloseOrderInterceptor
    extends WEB3EquityOrderManagerPersistenceEventInterceptor
{
    /**
     * �i���O�o�̓��[�e�B���e�B�j�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityReceiveCloseOrderInterceptor.class);
    
    /**
     * �i�R���X�g���N�^�j�B<BR>
     * <BR>
     * �C���X�^���X�𐶐����A�v���p�e�B�Ɉ������Z�b�g���ĕԋp����B
     * @@param isCloseOrderCancel �iis��������j
     * @@param l_strErrMessage �i�G���[���b�Z�[�W�j<BR>
     * �@@�@@�@@���������ʒm�L���[Params.�G���[���b�Z�[�W���Z�b�g�B
     * @@param l_dblEstimatedPrice - (��n���)<BR>
     * �Ώے����P�ʂ̎�n������Z�b�g����B<BR>
     * @@param l_dblPrice - (�����P��)<BR>
     * �����P���B<BR>
     * @@roseuid 4143D80100C1
     */
    public WEB3EquityReceiveCloseOrderInterceptor(
        boolean isCloseOrderCancel,
        String l_strErrMessage,
        double l_dblEstimatedPrice,
        double l_dblPrice) 
    {
        this.isCloseOrderCancel = isCloseOrderCancel;
        this.errorCode = l_strErrMessage;
        this.estimatedPrice = l_dblEstimatedPrice;
        this.price = l_dblPrice;
    }
    
    /**
     * �iis��������j�B<BR>
     * <BR>
     * ����������ǂ�����ێ�����v���p�e�B�B<BR>
     * �@@�|��������̏ꍇtrue<BR>
     * �@@�|�����ʒm�̏ꍇfalse
     */
    private boolean isCloseOrderCancel;
    
    /**
     * �i�G���[�R�[�h�j�B<BR>
     * �y���������ʒm�L���[�e�[�u���z�G���[���b�Z�[�W ��ݒ�B
     */
    private String errorCode;
    
    /**
     * (��n���)�B<BR>
     * ��n����B
     */
    private double estimatedPrice;
    
    /**
     * (�����P��)�B<BR>
     * �����P���B
     */
    private double price;
    
    /**
     * �i�X�V�l�ݒ�j�B<BR>
     * <BR>
     * �imutate���\�b�h�̎����j<BR>
     * �����P��Params�Ɋg������(*)�ݒ肵�ԋp����B<BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB<BR>
     * (*) xTrade�}�j���A���u�X�V���e�̃J�X�^�}�C�Y�v�Q�ƁB<BR>
     * �P�j �g�����ڃZ�b�g
     *    �X�V���e�́A�u�����ʒm_���������P�ʃe�[�u���d�l.xls�v���Q�ƁB
     * @@param l_updateType
     * @@param l_manage
     * @@param l_orderUnitParams
     * @@return EqtypeOrderUnitParams
     * @@roseuid 4143D80100D5
     */
    public EqtypeOrderUnitParams mutate(
        OrderManagerPersistenceType l_updateType,
        OrderManagerPersistenceContext l_manage,
        EqtypeOrderUnitParams l_orderUnitParams)
    {
        final String STR_METHOD_NAME = 
            "mutate(OrderManagerPersistenceType,OrderManagerPersistenceContext,EqtypeOrderUnitParams)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnitParams == null)
        {
            throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." +  STR_METHOD_NAME);
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderManager.toOrderUnit(l_orderUnitParams);

        //�����ʒm�敪=="�������"�����~�b�g�����L��(*1)�̏ꍇ
        //(*1)�����f�[�^�A�_�v�^.get�v�w�l�p�L����ԋ敪()��"���~�b�g�����L��"�̏ꍇ�A���~�b�g�����L���B
        //�����ɐݒ肷�钍���P�ʂɂ́A�X�V�O�̒����P�ʂ��w�肷��
        //�iEqtypeOrderUnitParams���g�����������}�l�[�W��.toOrderUnit()�ɂĒ����P�ʌ^�ɂ���j
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
        if (WEB3EquityWlimitEnableStatusDivDef.LIMIT_ENABLE.equals(l_strWLimitEnableStatusDiv)
            && this.isCloseOrderCancel)
        {
            //����������  �X�V�O�̔�������   �ȊO�̏ꍇ�F�i�����l�j
            l_orderUnitParams.setOrgOrderConditionType(l_orderUnitParams.getOrderConditionType());
            //�������������Z�q  �X�V�O�̔����������Z�q    �ȊO�̏ꍇ�F�i�����l�j
            l_orderUnitParams.setOrgOrderCondOperator(l_orderUnitParams.getOrderCondOperator());
            //���t�w�l��l   �X�V�O�̋t�w�l��l    �ȊO�̏ꍇ�F�i�����l�j
            if (l_orderUnitParams.getStopOrderPriceIsNull())
            {
                l_orderUnitParams.setOrgStopOrderPrice(null);
            }
            else
            {
                l_orderUnitParams.setOrgStopOrderPrice(l_orderUnitParams.getStopOrderPrice());
            }
            //���iW�w�l�j�����w�l   �X�V�O�́iW�w�l�j�����w�l     �ȊO�̏ꍇ�F�i�����l�j
            if (l_orderUnitParams.getWLimitPriceIsNull())
            {
                l_orderUnitParams.setOrgWLimitPrice(null);
            }
            else
            {
                l_orderUnitParams.setOrgWLimitPrice(l_orderUnitParams.getWLimitPrice());
            }
            //���iW�w�l�j���s����   �X�V�O�́iW�w�l�j���s����     �ȊO�̏ꍇ�F�i�����l�j
            l_orderUnitParams.setOrgWLimitExecCondType(l_orderUnitParams.getWLimitExecCondType());
            //�iW�w�l�j���s����   null  �ȊO�̏ꍇ�F�i�����l�j
            l_orderUnitParams.setWLimitExecCondType(null);
            //��������  �@@0:DEFAULT  �ȊO�̏ꍇ�F�i�����l�j
            l_orderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.DEFAULT);
            //�����������Z�q   null  �ȊO�̏ꍇ�F�i�����l�j
            l_orderUnitParams.setOrderCondOperator(null);
            //�t�w�l��l    null  �ȊO�̏ꍇ�F�i�����l�j
            l_orderUnitParams.setStopOrderPrice(null);
            //�iW�w�l�j�����w�l     null  �ȊO�̏ꍇ�F�i�����l�j
            l_orderUnitParams.setWLimitPrice(null);
            //���N�G�X�g�^�C�v    5:����   �ȊO�̏ꍇ�F�i�����l�j
            l_orderUnitParams.setRequestType(WEB3RequestTypeDef.INVALIDATE);
        }
        //�����P��
        l_orderUnitParams.setPrice(this.price);
        //�T�Z��n���
        l_orderUnitParams.setEstimatedPrice(this.estimatedPrice);
        if (!l_orderUnitParams.getConfirmedQuantityIsNull())
        {
            //�s�ꂩ��m�F�ς̒����P��
            l_orderUnitParams.setConfirmedOrderPrice(this.price);
            //�s�ꂩ��m�F�ς݂̊T�Z��n���
            l_orderUnitParams.setConfirmedEstimatedPrice(this.estimatedPrice);
        }
        //�����G���[���R�R�[�h
        l_orderUnitParams.setErrorReasonCode(this.errorCode);
        
        log.exiting(STR_METHOD_NAME);
        return l_orderUnitParams;
    }
}
@
