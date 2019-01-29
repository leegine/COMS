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
filename	WEB3EquityCarryOverCloseInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������J�z�����C���^�Z�v�^(WEB3EquityCarryOverCloseInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/03 ��� (���u) �V�K�쐬
                 : 2004/11/8  �@@���@@�C��
                   2005/01/06 �����a��(SRA) JavaDoc�C��
                   2006/07/19 ���@@�r�@@(���u)�@@ �d�l�ύX�@@���f��955
*/
package webbroker3.equity;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

/**
 * �i���������J�z�����C���^�Z�v�^�j�B<BR>
 * <BR>
 * �J�z������������DB�X�V�d�l�J�X�^�}�C�Y�p�N���X<BR>
 *�i�J�z�G���[���ɁA�J�z�G���[�̌����̋L�^���s���B�j
 * @@author lufaxu
 * @@version 1.0
 */
public class WEB3EquityCarryOverCloseInterceptor
    extends WEB3EquityOrderManagerPersistenceEventInterceptor
{
    /**
     * (���O���[�e�B���e�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3EquityCarryOverCloseInterceptor.class);

    /**
     * (�����G���[���R�R�[�h) <BR>
     */
    private String orderErrReasonCd;

    /**
     * (�R���X�g���N�^�B) <BR>
     * <BR>
     * �����̒����G���[���R�R�[�h���A�v���p�e�B�ɐݒ肷��B <BR>
     * @@param l_strOrderErrReasonCd - �����G���[���R�R�[�h <BR>
     * @@return WEB3EquityCarryOverCloseInterceptor
     * @@roseuid 4085D1600307
     */
    public WEB3EquityCarryOverCloseInterceptor(String l_strOrderErrReasonCd)
    {
        this.orderErrReasonCd = l_strOrderErrReasonCd;
    }

    /**
     * (�X�V�l�ݒ�) <BR>
     * �imutate���\�b�h�̎����j <BR>
     * �����P��Params�Ɋg������(*)�ݒ肵�ԋp����B <BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB <BR>
     * (*) xTrade�}�j���A���u�X�V���e�̃J�X�^�}�C�Y�v�Q�ƁB <BR>
     * <BR>
     * �P�j �g�����ڃZ�b�g <BR>
     * �X�V���e�́A�u�����J�z_���������P�ʃe�[�u��.xls�v�� <BR>
     * �u�i�����J�z[����]�j�����P�ʃe�[�u���v�V�[�g�Q�ƁB <BR>
     * <BR>
     * @@param l_orderManagerPersistenceType - (�X�V�^�C�v) <BR>
     * INSERT�܂��́AUPDATE�B <BR>
     * <BR>
     * OrderManagerPersistenceType�ɂĒ萔��`�B <BR>
     * <BR>
     * @@param l_orderManagerPersistenceContext - (����) <BR>
     * �iOrderManagerPersistenceContext�ɂĒ萔��`�j <BR>
     * @@param l_eqtypeOrderUnitParams - (�����P��Params) <BR>
     * ���������P�ʂ��ێ����鍀�ڂ̃p�����[�^�N���X�B <BR>
     * @@return EqtypeOrderUnitParams
     * @@roseuid 4085D0B801CF
     */
    public EqtypeOrderUnitParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType,
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        EqtypeOrderUnitParams l_eqtypeOrderUnitParams)
    {
        final String STR_METHOD_NAME = 
            "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, EqtypeOrderUnitParams) ";
        
        log.entering(STR_METHOD_NAME);
        if(l_eqtypeOrderUnitParams == null)
        {
            throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        l_eqtypeOrderUnitParams.setErrorReasonCode(this.orderErrReasonCd);

        log.exiting(STR_METHOD_NAME);
        return l_eqtypeOrderUnitParams;
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
     * �����̒����P��Params.����ID�A<BR>
     * �����P�ʂh�c�ɊY�����钍���P�ʃI�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �Q�j�@@super.mutate(EqtypeOrderActionParams)���R�[������B<BR>
     * �R�j�@@�g�����ڃZ�b�g<BR>
     * �@@�������G���[���R�R�[�h<BR>
     * �@@�@@[this.�����G���[���R�R�[�h != null�̏ꍇ]<BR>
     * �@@�@@�@@�p�����[�^.��������Params.�����G���[���R�R�[�h��<BR>
     * this.�����G���[���R�R�[�h���Z�b�g����B<BR>
     * �@@�@@[��L�ȊO�̏ꍇ]<BR>
     * �@@�@@�@@�p�����[�^.��������Params.�����G���[���R�R�[�h��<BR>
     * �h0000:����h���Z�b�g����B<BR>
     * �@@<BR>
     * �@@�����̑��̍��ځ@@�@@<BR>�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@
     * �@@�p�����[�^.��������Params�̊g�����ڂɁA<BR>
     * �����P�ʃI�u�W�F�N�g�̓������ڂ���l���Z�b�g���A�ԋp����B<BR>
     * @@param l_orderManagerPersistenceType - (�X�V�^�C�v)<BR>
     * INSERT�܂��́AUPDATE�B<BR>
     * <BR>
     * OrderManagerPersistenceType�ɂĒ萔��`�B<BR>
     * 
     * @@param l_orderManagerPersistenceContext - (����)<BR>
     * �iOrderManagerPersistenceContext�ɂĒ萔��`�j
     * @@param l_eqtypeOrderActionParams - (������������Params)<BR>
     * ���������������ێ����鍀�ڂ̃p�����[�^�N���X�B
     * @@return EqtypeOrderActionParams
     * @@roseuid 4110B6F7003B
     */
    public EqtypeOrderActionParams mutate(
        OrderManagerPersistenceType l_orderManagerPersistenceType, 
        OrderManagerPersistenceContext l_orderManagerPersistenceContext,
        EqtypeOrderActionParams l_eqtypeOrderActionParams) 
    {
        final String STR_METHOD_NAME = 
            "mutate(OrderManagerPersistenceType,OrderManagerPersistenceContext,EqtypeOrderActionParams)";
        log.entering(STR_METHOD_NAME);
        
        // �����P�ʎ擾
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        OrderManager l_orderMgr = l_tradingMod.getOrderManager();
        EqtypeOrderUnitRow l_orderUnitRow = null;

        try
        {
            OrderUnit l_orderUnit = l_orderMgr.getOrderUnit(l_eqtypeOrderActionParams.order_unit_id);
            l_orderUnitRow = (EqtypeOrderUnitParams) l_orderUnit.getDataSourceObject();
        }
        catch (NotFoundException l_nfe)
        {
            WEB3BaseRuntimeException l_runtimeException = new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
            log.error(STR_METHOD_NAME,l_runtimeException);
            throw l_runtimeException;
        }
        
        //super.mutate(EqtypeOrderActionParams)���R�[������B
        l_eqtypeOrderActionParams =
            super.mutate(
                l_orderManagerPersistenceType,
                l_orderManagerPersistenceContext,
                l_eqtypeOrderActionParams);
        
        // ����҂h�c�i�����P�ʃe�[�u���D����҂h�c�j
        if (l_orderUnitRow.getTraderIdIsNull())
        {
            l_eqtypeOrderActionParams.setTraderId(null);
        }
        else
        {
            l_eqtypeOrderActionParams.setTraderId(l_orderUnitRow.getTraderId());
        }
        
        // �l�i�����i�����P�ʃe�[�u���j
        l_eqtypeOrderActionParams.setPriceConditionType(l_orderUnitRow.getPriceConditionType());
        
        // �������� (�����P�ʃe�[�u��.��������)
        l_eqtypeOrderActionParams.setOrderConditionType(
            l_orderUnitRow.getOrderConditionType()
            );

        // �����������Z�q (�����P�ʃe�[�u��.�����������Z�q)
        l_eqtypeOrderActionParams.setOrderCondOperator(
            l_orderUnitRow.getOrderCondOperator()
            );

        //�t�w�l��l
        if (l_orderUnitRow.getStopOrderPriceIsNull())
        {
            l_eqtypeOrderActionParams.setStopOrderPrice(null);
        }
        else
        {
            l_eqtypeOrderActionParams.setStopOrderPrice(
                l_orderUnitRow.getStopOrderPrice());
        }

        //�iW�w�l�j�����w�l
        if (l_orderUnitRow.getWLimitPriceIsNull())
        {
            l_eqtypeOrderActionParams.setWLimitPrice(null);
        }
        else
        {
            l_eqtypeOrderActionParams.setWLimitPrice(
            l_orderUnitRow.getWLimitPrice());
        }

        // �����������t (�����P�ʃe�[�u��.�����������t)
        l_eqtypeOrderActionParams.setExpirationDate(
            l_orderUnitRow.getExpirationDate()
            );

        // �T�Z��n��� (���������P�ʃe�[�u��.�T�Z��n���)
        if (l_orderUnitRow.getEstimatedPriceIsNull())
        {
            l_eqtypeOrderActionParams.setEstimatedPrice(null);    
        }
        else
        {
            l_eqtypeOrderActionParams.setEstimatedPrice(
                l_orderUnitRow.getEstimatedPrice()
                );    
        }


        // ���������E����敪 (���������P�ʃe�[�u��.���������E����敪)
        l_eqtypeOrderActionParams.setModifyCancelType(
            l_orderUnitRow.getModifyCancelType()
            );

        // �����o�H�敪�i�����P�ʃe�[�u���D�����o�H�敪�j
        l_eqtypeOrderActionParams.setOrderRootDiv(
            l_orderUnitRow.getOrderRootDiv());

        // ���Ϗ��� (���������P�ʃe�[�u��.���Ϗ���)
        l_eqtypeOrderActionParams.setClosingOrderType(
            l_orderUnitRow.getClosingOrderType()
            );

        // �����G���[���R�R�[�h (���������P�ʃe�[�u��.�����G���[���R�R�[�h)
        if (this.orderErrReasonCd != null)
        {
            l_eqtypeOrderActionParams.setErrorReasonCode(this.orderErrReasonCd);
        }
        else
        {    
            l_eqtypeOrderActionParams.setErrorReasonCode(
                WEB3ErrorReasonCodeDef.NORMAL
                );
        }
        // ���N�G�X�g�^�C�v (���������P�ʃe�[�u��.���N�G�X�g�^�C�v)
        l_eqtypeOrderActionParams.setRequestType(
            l_orderUnitRow.getRequestType()
            );
        log.exiting(STR_METHOD_NAME);
        return l_eqtypeOrderActionParams;
    }
}
@
