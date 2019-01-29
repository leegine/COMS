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
filename	WEB3EquityChangeConfirmInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �������������m��C���^�Z�v�^(WEB3EquityChangeConfirmInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/27 �� �� (���u) �V�K�쐬
                   2004/05/27 �� �� (���u) mutate���\�b�h����
                   2004/05/27 Ḗ@@�� (���u) mutate���\�b�h���� 
                   2004/11/02 �@@�� �C��
                   2005/01/06 �����a��(SRA) JavaDoc�C��
*/
package webbroker3.equity;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderManager;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�������������m��C���^�Z�v�^�j�B<BR>
 * <BR>
 * �ȉ��̃P�[�X�Ŏg�p����A�����f�[�^�X�V�d�l�J�X�^�}�C�Y�p�̃N���X�B<BR>
 * <BR>
 * �E�s��ǌ�`�����J�z�܂ł̊Ԃ́A�s�ꑗ�M�ϒ����ɑ΂���������e���A�������s���<BR>
 * �󂯓����ꂽ���̂Ƃ��Ċm�肷��B<BR>
 * @@author lufaxu
 * @@version 1.0
 */
public class WEB3EquityChangeConfirmInterceptor
    extends WEB3EquityOrderManagerPersistenceEventInterceptor
{

    /**
     * (���O���[�e�B���e�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityChangeConfirmInterceptor.class);

    /**
     * (�������������m��C���^�Z�v�^)<BR>
     * �R���X�g���N�^�B<BR>
     * @@return webbroker3.equity.WEB3EquityOrderChangeConfirmInterceptor
     * @@roseuid 407A179503B1
     */
    public WEB3EquityChangeConfirmInterceptor()
    {

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
     * �i������Ԃ��h�����ς݁i�ύX�����j�FMODIFIED�h�ɊY������ꍇ�̂݁A<BR>
     * ���������{����B<BR>
     * �@@�ȊO�́A���������̂܂ܕԋp���������I������j<BR>
     * �@@�����P��Params�̒l���u�����ρi�ύX�����j�v�̏�Ԃɐݒ肵�ԋp����B<BR>
     * <BR>
     * �X�V���e�́A�u��������_���������P�ʃe�[�u��.xls�v��<BR>
     * �u�i������������ DB�X�V���e[�����m��]�j���������P�ʃe�[�u���v�V�[�g�Q�ƁB<BR>
     * @@param l_updateType - (�X�V�^�C�v)<BR>
     * INSERT�܂��́AUPDATE�B<BR>
     * <BR>
     * OrderManagerPersistenceType�ɂĒ萔��`�B<BR>
     * 
     * @@param l_context - (����)<BR>
     * �iOrderManagerPersistenceContext�ɂĒ萔��`�j
     * @@param l_orderUnitParams - (�����P��Params)<BR>
     * ���������P�ʂ��ێ����鍀�ڂ̃p�����[�^�N���X�B
     * @@return EqtypeOrderUnitParams
     * @@roseuid 406D06D803C6
     */
    public EqtypeOrderUnitParams mutate(
        OrderManagerPersistenceType l_updateType, 
        OrderManagerPersistenceContext l_context, 
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
        if (OrderStatusEnum.MODIFIED.equals(l_orderUnitParams.getOrderStatus()))
        {
            double l_dblConfirmedOrderPrice = 0.0;
            double l_dblConfirmedEstimatedPrice = 0.0;
            EqTypeExecutionConditionType l_confirmedExecConditionType = null;
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            EqTypeOrderManager l_orderMgr = (EqTypeOrderManager) l_tradingMod.getOrderManager();               
            OrderUnit l_orderUnit = null;
            try 
            {
                l_orderUnit =
                    l_orderMgr.getOrderUnit(l_orderUnitParams.getOrderUnitId());
            }
            catch (NotFoundException l_nfe) 
            {
                log.debug(l_nfe.getMessage());
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);
            }
            log.debug("order unit id:"+ l_orderUnit.getOrderUnitId());
            //set order status
            l_orderUnitParams.setOrderStatus(OrderStatusEnum.MODIFIED);
            //���������E����敪���Z�b�g
            if (l_orderUnit.isUnexecuted())
            {
                //�S����������
                log.debug("isUnexecuted true: executed_quantity��Null�̏ꍇ");
                l_orderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.CHANGED);
            }
            else
            {
                //�ꕔ��������
                log.debug("false: ����ȊO�̏ꍇ");
                l_orderUnitParams.setModifyCancelType(WEB3ModifyCancelTypeDef.PARTIALLY_CHANGED);
            }
            EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow) l_orderUnit.getDataSourceObject();
            //�s�ꂩ��m�F�ς݂̒����P��
            l_dblConfirmedOrderPrice = l_orderUnitRow.getPrice();
            if (l_orderUnitRow.getConfirmedOrderPriceIsNull())
            {
                l_orderUnitParams.setConfirmedOrderPrice(null);
            }
            else
            {
                l_orderUnitParams.setConfirmedOrderPrice(l_dblConfirmedOrderPrice);
            }
            //�s�ꂩ��m�F�ς݂̊T�Z��n���
            l_dblConfirmedEstimatedPrice =
                l_orderUnitRow.getEstimatedPrice();
            if (l_orderUnitRow.getEstimatedPriceIsNull())
            {
                l_orderUnitParams.setConfirmedEstimatedPrice(null);   
            }
            else
            {
                l_orderUnitParams.setConfirmedEstimatedPrice(
                    l_dblConfirmedEstimatedPrice);
            }
            //�s�ꂩ��m�F�ς݂̎��s����
            l_confirmedExecConditionType =
                l_orderUnitRow.getExecutionConditionType();
            l_orderUnitParams.setConfirmedExecConditionType(
                l_confirmedExecConditionType);
            //�����G���[���R�R�[�h���Z�b�g
            l_orderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
        }
        log.exiting(STR_METHOD_NAME);
        return l_orderUnitParams;
    }
}
@
