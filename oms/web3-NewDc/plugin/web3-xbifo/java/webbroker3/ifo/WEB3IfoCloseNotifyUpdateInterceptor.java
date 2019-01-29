head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoCloseNotifyUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�����ʒm�X�V�C���^�Z�v�^(WEB3IfoCloseNotifyUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/7/26 �юu�� (���u) �V�K�쐬
                 : 2006/11/30 ����(���u) �d�l�ύX���f��No.586, �c�a�X�V�d�lNo.128
*/

package webbroker3.ifo;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CloseNotifyTypeDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.ifo.define.WEB3IfoWLimitEnableStatusDivDef;
import webbroker3.util.WEB3LogUtility;



/**
 * (�敨OP�����ʒm�X�V�C���^�Z�v�^)<BR>
 * �敨OP�����ʒm�X�V�C���^�Z�v�^�N���X
 * 
 * @@author  �юu��
 * @@version 1.0
 */
public class WEB3IfoCloseNotifyUpdateInterceptor extends WEB3IfoUpdateInterceptor 
{   
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoChangeConfirmUpdateInterceptor.class);

    /**
     * (�����ʒm�敪)<BR>
     * �����ʒm�敪
     */
    private String closeNotifyType;

    /**
     * (�敨OP�����ʒm�X�V�C���^�Z�v�^)<BR>
     * �R���X�g���N�^
     * @@return webbroker3.ifo.WEB3IfoCloseNotifyUpdateInterceptor
     * @@roseuid 44A8F3F101E2
     */
    public WEB3IfoCloseNotifyUpdateInterceptor() 
    {
     
    }
    
    /**
     * (�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j<BR>
     * ��������Params�Ɋg������(*)�ݒ肵�ԋp����B<BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB<BR>
     * <BR>
     * �P�j�@@super.mutate(IfoOrderActionParams)���R�[������B<BR>
     * <BR>
     * �Q�j�@@�g�����ڃZ�b�g<BR>
     * �@@�����G���[���R�R�[�h�F<BR>
     * �@@�@@�p�����[�^.��������Params.�����G���[���R�R�[�h�Ɂh0000:����h���Z�b�g����B <BR>
     * <BR>
     * @@param l_updateType - (�X�V�^�C�v)<BR>
     * INSERT�܂��́AUPDATE�B<BR>
     * <BR>
     * OrderManagerPersistenceType�ɂĒ萔��`�B<BR>
     * @@param l_dealing - (����)<BR>
     * �iOrderManagerPersistenceContext�ɂĒ萔��`�j<BR>
     * @@param l_ifoOrderActionParams - (��������Params)<BR>
     * �����������ێ����鍀�ڂ̃p�����[�^�N���X�B<BR>
     * @@return IfoOrderActionParams
     * @@roseuid 44A8F3F101D2
     */
    public IfoOrderActionParams mutate(
        OrderManagerPersistenceType l_updateType, 
        OrderManagerPersistenceContext l_dealing, 
        IfoOrderActionParams l_ifoOrderActionParams) 
    {
        final String STR_METHOD_NAME =
            "mutate(OrderManagerPersistenceType l_updateType, "
                + "OrderManagerPersistenceContext l_context, "
                + "IfoOrderActionParams l_ifoOrderActionParams)";
        log.entering(STR_METHOD_NAME);
        if (l_ifoOrderActionParams == null)
        {
            log.error(
                STR_METHOD_NAME, 
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                    this.getClass().getName() + "." + STR_METHOD_NAME));
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }          
        
        //�P�j�@@super.mutate(IfoOrderActionParams)���R�[������B
        super.mutate(l_updateType, l_dealing, l_ifoOrderActionParams);
        
        //�Q�j�@@�g�����ڃZ�b�g
        //�����G���[���R�R�[�h�F�p�����[�^.��������Params.�����G���[���R�R�[�h�Ɂh0000:����h���Z�b�g����B
        l_ifoOrderActionParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
        
        log.exiting(STR_METHOD_NAME);
        return l_ifoOrderActionParams;
    }

    /**
     * (�X�V�l�ݒ�)<BR>
     * �imutate���\�b�h�̎����j <BR>
     * �����P��Params�Ɋg������(*)�ݒ肵�ԋp����B <BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB <BR>
     * <BR>
     * �X�V���e�́A <BR>
     * DB�X�V�d�l <BR>
     * �u��OP�����ʒm_�����P�ʃe�[�u���d�l.xls�v�Q�ƁB<BR>
     * @@param l_updateType - (�X�V�^�C�v)<BR>
     * INSERT�܂��́AUPDATE�B <BR>
     * <BR>
     * OrderManagerPersistenceType�ɂĒ萔��`�B
     * @@param l_dealing - (����)<BR>
     * �iOrderManagerPersistenceContext�ɂĒ萔��`�j
     * @@param l_ifoOrderUnitParams - (�����P��Params)<BR>
     * �����P�ʍs�I�u�W�F�N�g
     */
    public IfoOrderUnitParams mutate(
        OrderManagerPersistenceType l_updateType,
        OrderManagerPersistenceContext l_dealing,
        IfoOrderUnitParams l_ifoOrderUnitParams)
    {
        final String STR_METHOD_NAME =
            "mutate(OrderManagerPersistenceType, "
                + "OrderManagerPersistenceContext, "
                + "IfoOrderUnitParams)";
        log.entering(STR_METHOD_NAME);

        try
        {
            //���@@(*1)�敨OP�f�[�^�A�_�v�^.get�v�w�l�p�L����ԋ敪()��
            //"���~�b�g�����L��"�̏ꍇ�A���~�b�g�����L���B
            //�����ɐݒ肷�钍���P�ʂɂ́A�X�V�O�̒����P�ʂ��w�肷��
            //�iIfoOrderUnitParams��OP�����}�l�[�W��.toOrderUnit()�ɂĒ����P�ʌ^�ɂ���j
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3OptionOrderManagerImpl l_opOrderMgr = (WEB3OptionOrderManagerImpl)
                l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
            IfoOrderUnit l_orderUnit =
                (IfoOrderUnit)l_opOrderMgr.getOrderUnit(l_ifoOrderUnitParams.getOrderUnitId());
            IfoOrderUnitRow l_orderUnitRow = 
                (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
            IfoOrderUnitParams l_beforeOrderUnitParams = new IfoOrderUnitParams(l_orderUnitRow);

            String l_strWLimitEnableStatusDiv =
                WEB3IfoDataAdapter.getWLimitEnableStatusDiv(l_orderUnit);

            //�����ʒm�敪=="�������"�����~�b�g�����L��(*1)�̏ꍇ
            if (WEB3CloseNotifyTypeDef.CLOSE_CANCEL.equals(this.getCloseNotifyType())
                && WEB3IfoWLimitEnableStatusDivDef.LIMIT_ENABLE.equals(l_strWLimitEnableStatusDiv))
            {
                //��������
                //�����ʒm�敪=="�������"�����~�b�g�����L��(*1�j�̏ꍇ�A0�FDEFAULT�i�����w��Ȃ��j
                //�ȊO�A�i�����l�j
                l_ifoOrderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.DEFAULT);

                //�����������Z�q
                //�����ʒm�敪=="�������"�����~�b�g�����L��(*1�j�̏ꍇ�Anull
                //�ȊO�A�i�����l�j
                l_ifoOrderUnitParams.setOrderCondOperator(null);

                //�t�w�l��l�^�C�v
                //�����ʒm�敪=="�������"�����~�b�g�����L��(*1�j�̏ꍇ�Anull
                //�ȊO�A�i�����l�j
                l_ifoOrderUnitParams.setStopPriceType(null);

                //�t�w�l��l
                //�����ʒm�敪=="�������"�����~�b�g�����L��(*1�j�̏ꍇ�Anull
                //�ȊO�A�i�����l�j
                l_ifoOrderUnitParams.setStopOrderPrice(null);

                //�iW�w�l�j�����w�l
                //�����ʒm�敪=="�������"�����~�b�g�����L��(*1�j�̏ꍇ�Anull
                //�ȊO�A�i�����l�j
                l_ifoOrderUnitParams.setWLimitPrice(null);

                //����������
                //�����ʒm�敪=="�������"�����~�b�g�����L��(*1�j�̏ꍇ�A�X�V�O�̔�������
                //�ȊO�A�i�����l�j
                l_ifoOrderUnitParams.setOrgOrderConditionType(
                    l_beforeOrderUnitParams.getOrderConditionType());

                //�������������Z�q
                //�����ʒm�敪=="�������"�����~�b�g�����L��(*1�j�̏ꍇ�A�X�V�O�̔����������Z�q
                //�ȊO�A�i�����l�j
                l_ifoOrderUnitParams.setOrgOrderCondOperator(
                    l_beforeOrderUnitParams.getOrderCondOperator());

                //���t�w�l��l�^�C�v
                //�����ʒm�敪=="�������"�����~�b�g�����L��(*1�j�̏ꍇ�A�X�V�O�̋t�w�l��l�^�C�v
                //�ȊO�A�i�����l�j
                l_ifoOrderUnitParams.setOrgStopPriceType(
                    l_beforeOrderUnitParams.getStopPriceType());

                //���t�w�l��l
                //�����ʒm�敪=="�������"�����~�b�g�����L��(*1�j�̏ꍇ�A�X�V�O�̋t�w�l��l
                //�ȊO�A�i�����l�j
                if (l_beforeOrderUnitParams.getStopOrderPriceIsNull())
                {                    
                    l_ifoOrderUnitParams.setOrgStopOrderPrice(null);
                }
                else
                {
                    l_ifoOrderUnitParams.setOrgStopOrderPrice(
                        l_beforeOrderUnitParams.getStopOrderPrice());
                }

                //���iW�w�l�j�����w�l
                //�����ʒm�敪=="�������"�����~�b�g�����L��(*1�j�̏ꍇ�A�X�V�O�́iW�w�l�j�����w�l
                //�ȊO�A�i�����l�j
                if (l_beforeOrderUnitParams.getWLimitPriceIsNull())
                {
                    l_ifoOrderUnitParams.setOrgWLimitPrice(null);
                }
                else
                {
                    l_ifoOrderUnitParams.setOrgWLimitPrice(
                        l_beforeOrderUnitParams.getWLimitPrice());
                }

                //���iW�w�l�j���s����
                //�����ʒm�敪=="�������"�����~�b�g�����L��(*1�j�̏ꍇ�A�X�V�O�́iW�w�l�j���s����
                //�ȊO�A�i�����l�j
                l_ifoOrderUnitParams.setOrgWLimitExecCondType(
                    l_beforeOrderUnitParams.getWLimitExecCondType());

                //�iW�w�l�j���s����
                //�����ʒm�敪=="�������"�����~�b�g�����L��(*1�j�̏ꍇ�Anull
                //�ȊO�A�i�����l�j
                l_ifoOrderUnitParams.setWLimitExecCondType(null);

                //���N�G�X�g�^�C�v
                //�����ʒm�敪=="�������"�����~�b�g�����L��(*1�j�̏ꍇ�@@5:����
                l_ifoOrderUnitParams.setRequestType(WEB3RequestTypeDef.INVALIDATE);
            }
        }
        catch (NotFoundException l_nfe)
        {
            log.error("�����P�ʂ��擾�ł��܂���ł����B" + "�����P��ID="
                + l_ifoOrderUnitParams.getOrderUnitId(), l_nfe);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME + l_ex.getErrorMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_ifoOrderUnitParams;
    }

    /**
     * (set�����ʒm�敪)<BR>
     * �����̎����ʒm�敪��this.�����ʒm�敪�ɃZ�b�g����B<BR>
     * @@param l_strCloseNotifyType - (�����ʒm�敪)<BR>
     * �����ʒm�敪
     */
    public void setCloseNotifyType(String l_strCloseNotifyType)
    {
        this.closeNotifyType = l_strCloseNotifyType;
    }

    /**
     * (get�����ʒm�敪)<BR>
     * this.�����ʒm�敪��ԋp����B<BR>
     * @@return String
     */
    public String getCloseNotifyType()
    {
        return this.closeNotifyType;
    }
}
@
