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
filename	WEB3EquityCancelOrderConfirmInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ������������m��C���^�Z�v�^(WEB3EquityCancelOrderConfirmInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/26 �� �� (���u) mutate���\�b�h�̎�������
                   2004/9/22 Ḗ@@�� (���u) mutate ���\�b�h��
                   2004/12/08 �����iSRA�j �c�Č��Ή��@@�X�V�l�ݒ�(OrderUnit)���\�b�h�E�E�u�������ʁv�J�X�^�}�C�Y�̍폜
                   2005/01/06 �����a��(SRA) JavaDoc�C��
*/
package webbroker3.equity;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.util.WEB3LogUtility;

/**
 * �i������������m��C���^�Z�v�^�j�B<BR>
 * <BR>
 * �ȉ��̃P�[�X�Ŏg�p����A�����f�[�^�X�V�d�l�J�X�^�}�C�Y�p�̃N���X�B<BR>
 * �s��ǌ�`�����J�z�܂ł̊Ԃ́A�s�ꑗ�M�ϒ����ɑ΂��������A<BR>
 * ������s��Ɏ󂯓����ꂽ���̂Ƃ��Ċm�肷��B<BR>
 * @@author lufaxu
 * @@version 1.0
 */
public class WEB3EquityCancelOrderConfirmInterceptor
    extends WEB3EquityOrderManagerPersistenceEventInterceptor
{

    /**
     * (���O���[�e�B���e�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3EquityCancelOrderConfirmInterceptor.class);

    /**
     * (������������m��C���^�Z�v�^)<BR>
     * �R���X�g���N�^�B<BR>
     * @@return webbroker3.equity.WEB3EquityOrderCancelConfirmInterceptor
     * @@roseuid 407A17C10140
     */
    public WEB3EquityCancelOrderConfirmInterceptor()
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
     * �i������Ԃ��h�����ς݁i��������j�FCANCELLED�h�ɊY������ꍇ�̂݁A<BR>
     * ���������{����B<BR>
     * �@@�ȊO�́A���������̂܂ܕԋp���������I������j<BR>
     * �@@�����P��Params�̒l���u�����ρi��������j�v�̏�Ԃɐݒ肵�ԋp����B<BR>
     * <BR>
     * �X�V���e�́A�u�������_���������P�ʃe�[�u��.xls�v��<BR>
     * �u�i����������� DB�X�V���e[����m��]�j���������P�ʃe�[�u���v�V�[�g�Q�ƁB<BR>
     * <BR>
     * @@param l_updateType - (�X�V�^�C�v)<BR>
     * INSERT�܂��́AUPDATE�B <BR>
     * EqTypeOrderManagerPersistenceType�ɂĒ萔��`�B<BR>
     * <BR> 
     * @@param l_manage - (����)<BR>
     * �iEqTypeOrderManagerPersistenceContext�ɂĒ萔��`�j<BR>
     * <BR>
     * @@param l_orderUnitParams - (�����P��Params)<BR>
     * ���������P�ʂ��ێ����鍀�ڂ̃p�����[�^�N���X�B<BR>
     * @@return EqtypeOrderUnitParams
     * @@roseuid 4143CD7B0154
     */
    public EqtypeOrderUnitParams mutate(
        OrderManagerPersistenceType l_updateType,
        OrderManagerPersistenceContext l_manage,
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
        // ������Ԃ��h�����ς݁i��������j�FCANCELLED�h�ɊY������ꍇ�̂ݏ��������{�B
        if (OrderStatusEnum.CANCELLED.equals(l_orderUnitParams.getOrderStatus()))
        {

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            OrderManager l_orderMgr = l_tradingMod.getOrderManager();

            OrderUnit l_orderUnit = null;
            try 
            {
                l_orderUnit =
                    l_orderMgr.getOrderUnit(l_orderUnitParams.getOrderUnitId());
            } 
            catch (NotFoundException l_nfe) 
            {
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe); 
            }
            
            //���������E����敪���Z�b�g        
            if (l_orderUnit.isUnexecuted())
            {
                //�S���������3
                log.debug("isUnexecuted true: executed_quantity��Null�̏ꍇ");
                l_orderUnitParams.setModifyCancelType(
                    WEB3ModifyCancelTypeDef.CANCELED);
            }
            else
            {
                //�����
                log.debug("false: ����ȊO�̏ꍇ");
                l_orderUnitParams.setModifyCancelType(
                    WEB3ModifyCancelTypeDef.PART_CANCELED);
            }
            
            //�s�ꂩ��m�F�ς݂̊T�Z��n������Z�b�g
            l_orderUnitParams.setConfirmedEstimatedPrice(l_orderUnitParams.getEstimatedPrice());
            
            //�����G���[���R�R�[�h���Z�b�g0000
            l_orderUnitParams.setErrorReasonCode(
                WEB3ErrorReasonCodeDef.NORMAL);
            log.debug("�����G���[���R�R�[�h" +l_orderUnitParams.getErrorReasonCode());
        }
        log.exiting(STR_METHOD_NAME);
        return l_orderUnitParams;
    }
}
@
