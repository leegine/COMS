head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondExecuteUpdateInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����X�V�C���^�Z�v�^(WEB3BondExecuteUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/28 �ęԍg (���u) �V�K�쐬
*/

package webbroker3.bd;

import java.math.BigDecimal;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitParams;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3BondOrderExecStatusDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (�����X�V�C���^�Z�v�^)<BR>
 * �����X�V�C���^�Z�v�^�N���X<BR>
 *
 * @@author �ęԍg(���u)
 * @@version 1.0
 */
public class WEB3BondExecuteUpdateInterceptor extends WEB3AdminBondExecuteCommonInterceptor
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondExecuteUpdateInterceptor.class);

    /**
     * (�����X�V�C���^�Z�v�^)<BR>
     * �R���X�g���N�^<BR>
     */
    public WEB3BondExecuteUpdateInterceptor()
    {

    }

    /**
     * (�i�����P�ʁj�X�V�l��)<BR>
     * �imutate���\�b�h�̎����j<BR>
     * <BR>
     * �����P��Params�Ɋg������(*)�ݒ肵�ԋp����B<BR>
     * <BR>
     * �P�j�g�����ڃZ�b�g<BR>
     * �@@�p�����[�^.�������P��Params�̊g�����ڂɒl���Z�b�g���A�ԋp����B<BR>
     * �@@���ڐݒ���e�́@@�u���������_�������P�ʃe�[�u��DB�X�V�d�l.xls�v�Q�ƁB<BR>
     * <BR>
     * (*) xTrade���W�������ŃZ�b�g���Ȃ��J�X�^�}�C�Y���ځB<BR>
     * @@param l_persistenceType - (arg0)<BR>
     * @@param l_context - (arg1)<BR>
     * @@param l_params - (arg2)<BR>
     * @@return BondOrderUnitParams
     */
    public BondOrderUnitParams mutate(
        OrderManagerPersistenceType l_persistenceType,
        OrderManagerPersistenceContext l_context,
        BondOrderUnitParams l_params)
    {
        final String STR_METHOD_NAME =
            "mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, BondOrderUnitParams)";
        log.entering(STR_METHOD_NAME);

        if (l_params == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        WEB3BondExecuteDateInfo l_bondExecuteDateInfo = this.getBondExecuteDateInfo();
        //��n�� = ���������.get��n��
        l_params.setDeliveryDate(l_bondExecuteDateInfo.getDeliveryDate());
        //���n��n�� = ���������.get���n��n��
        if (l_bondExecuteDateInfo.getForeignDeliveryDate() != null)
        {
            l_params.setForeignDeliveryDate(l_bondExecuteDateInfo.getForeignDeliveryDate());
        }

        WEB3BondEstimatedPriceCalcResult l_bondEstimatedPriceCalcResult =
            this.getBondEstimatedPriceCalcResult();

        //���v�����z = �i�����l�j+ �v�Z�T�[�r�X.calc��������i����,�P��,�������j
        //���ʁ���萔��
        //�P��������n����v�Z����.get�P��
        //������ = �C���^�Z�v�^.get������

        WEB3BondBizLogicProvider l_provider = new WEB3BondBizLogicProvider();
        BigDecimal l_bdExecutedAmount = null;
        try
        {
            l_bdExecutedAmount = l_provider.calcTradingPrice(
                new BigDecimal(String.valueOf(l_params.getExecutedQuantity())),
                l_bondEstimatedPriceCalcResult.getPrice(),
                this.getBondProduct());
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        if (l_bdExecutedAmount != null)
        {
            double l_dblExecutedAmount =
                l_params.getExecutedAmount() + l_bdExecutedAmount.doubleValue();
            l_params.setExecutedAmount(l_dblExecutedAmount);
        }
        //�������敪 = 1�F����
        l_params.setOrderExecStatus(WEB3BondOrderExecStatusDef.EXECUTED);
        //�����P�� = ����n����v�Z����.get�P��
        //���P�� = ����n����v�Z����.get�P��
        BigDecimal l_bdPrice = l_bondEstimatedPriceCalcResult.getPrice();
        if (l_bdPrice != null)
        {
            l_params.setPrice(l_bdPrice.doubleValue());
            l_params.setExecutedPrice(l_bdPrice.doubleValue());
        }
        else
        {
            l_params.setPrice(null);
            l_params.setExecutedPrice(null);
        }
        // ���בփ��[�g = ����n����v�Z����.get�בփ��[�g
        if (l_bondEstimatedPriceCalcResult.getFxRate() != null)
        {
            l_params.setExecFxRate(l_bondEstimatedPriceCalcResult.getFxRate().doubleValue());
        }
        else
        {
            l_params.setExecFxRate(null);
        }
        //��������i�~�݁j = ����n����v�Z����.get��������i�~�݁j
        if (l_bondEstimatedPriceCalcResult.getTradingPrice() != null)
        {
            l_params.setTradingPrice(l_bondEstimatedPriceCalcResult.getTradingPrice().doubleValue());
        }
        else
        {
            l_params.setTradingPrice(null);
        }
        //��������i�O�݁j= ����n����v�Z����.get��������i�O�݁j
        if (l_bondEstimatedPriceCalcResult.getForeignTradePrice() != null)
        {
            l_params.setForeignTradingPrice(l_bondEstimatedPriceCalcResult.getForeignTradePrice().doubleValue());
        }
        else
        {
            l_params.setForeignTradingPrice(null);
        }
        //�o�ߗ��q�i�~�݁j =  ����n����v�Z����.get�o�ߗ��q�i�~�݁j
        if (l_bondEstimatedPriceCalcResult.getAccruedInterest() != null)
        {
            l_params.setAccruedInterest(l_bondEstimatedPriceCalcResult.getAccruedInterest().doubleValue());
        }
        else
        {
            l_params.setAccruedInterest(null);
        }
        //�o�ߗ��q�i�O�݁j= ����n����v�Z����.get�o�ߗ��q�i�O�݁j
        if (l_bondEstimatedPriceCalcResult.getForeignAccruedInterest() != null)
        {
            l_params.setForeignAccruedInterest(l_bondEstimatedPriceCalcResult.getForeignAccruedInterest().doubleValue());
        }
        else
        {
            l_params.setForeignAccruedInterest(null);
        }
        //��n����i�~�݁j=  ����n����v�Z����.get��n����i�~�݁j
        if (l_bondEstimatedPriceCalcResult.getEstimatedPrice() != null)
        {
            l_params.setEstimatedPrice(l_bondEstimatedPriceCalcResult.getEstimatedPrice().doubleValue());
        }
        else
        {
            l_params.setEstimatedPrice(null);
        }
        //��n����i�O�݁j =  ����n����v�Z����.get��n����i�O�݁j
        if (l_bondEstimatedPriceCalcResult.getForeignEstimatedPrice() != null)
        {
            l_params.setForeignEstimatedPrice(l_bondEstimatedPriceCalcResult.getForeignEstimatedPrice().doubleValue());
        }
        else
        {
            l_params.setForeignEstimatedPrice(null);
        }
        //�o�ߓ���         =  ����n����v�Z����.get�o�ߓ���
        if (l_bondEstimatedPriceCalcResult.getElapsedDays() != null)
        {
            l_params.setElapsedDays(l_bondEstimatedPriceCalcResult.getElapsedDays());
        }
        else
        {
            l_params.setElapsedDays(null);
        }
        //�����        =   ����n����v�Z����.get�����
        if (l_bondEstimatedPriceCalcResult.getCalcBaseDays() != null)
        {
            l_params.setCalcBaseDays(l_bondEstimatedPriceCalcResult.getCalcBaseDays());
        }
        else
        {
            l_params.setCalcBaseDays(null);
        }
        //����          =  ���������.get����
        l_params.setExecDate(l_bondExecuteDateInfo.getExecuteDate());

        //���n����      =   ���������.get���n����
        l_params.setForeignExecDate(l_bondExecuteDateInfo.getForeignExecuteDate());

        //������    =  ���������.get������
        l_params.setPaymentDate(l_bondExecuteDateInfo.getPaymentDate());

        log.exiting(STR_METHOD_NAME);
        return l_params;
    }
}
@
