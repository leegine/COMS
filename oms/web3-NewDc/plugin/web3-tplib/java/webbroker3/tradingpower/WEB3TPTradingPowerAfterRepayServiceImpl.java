head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.55.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTradingPowerAfterRepayServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ԍό�]�̓T�[�r�XImpl(WEB3TPTradingPowerAfterRepayServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/11 nakazato(ACT) �V�K�쐬
*/

package webbroker3.tradingpower;

import java.util.List;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.tradingpower.define.WEB3TPAttentionObjectionTypeDef;
import webbroker3.tradingpower.define.WEB3TPMarkToMarketDivDef;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.updtpower.WEB3TPNewOrderSpec;
import webbroker3.util.WEB3LogUtility;

/**
 * (�ԍό�]�̓T�[�r�XImpl)
 */
public class WEB3TPTradingPowerAfterRepayServiceImpl implements WEB3TPTradingPowerAfterRepayService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPTradingPowerAfterRepayServiceImpl.class);

    /**
     * @@roseuid 41E384280038
     */
    public WEB3TPTradingPowerAfterRepayServiceImpl()
    {

    }

    /**
     * (create�ԍό㎑�Y�]�͏��<�M�p�ڋq>)<BR>
     * <BR>
     * �ԍό�]�̓I�u�W�F�N�g���쐬����B<BR>
     * <BR>
     * �V�[�P���X�}�u(�ԍό�]�̓T�[�r�XImpl)create�ԍό㎑�Y�]�͏��v�Q��<BR>
     * <BR>
     * @@param l_subAccount
     * @@param l_orderSpecIntercepter
     * @@param l_orderSpec
     * @@return webbroker3.tradingpower.WEB3TPTradingPowerCalcMarginAfterRepay
     * @@roseuid 41E384280076
     */
    public WEB3TPTradingPowerCalcMarginAfterRepay createWEB3TPTradingPowerCalcAfterRepay(
        WEB3GentradeSubAccount l_subAccount,
        Object l_orderSpecIntercepter,
        Object l_orderSpec)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "WEB3TPTradingPowerAfterRepayServiceImpl.createWEB3TPTradingPowerCalcAfterRepay(WEB3GentradeSubAccount, Object, Object)";
        log.entering(STR_METHOD_NAME);

        //����.�⏕������null�̏ꍇ
        if (l_subAccount == null)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                STR_METHOD_NAME);
        }

        //����ID���擾
        long l_lngAccountId = l_subAccount.getAccountId();

        //�ڋq�I�u�W�F�N�g���擾
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        //�M�p�����J�݋敪���擾
        boolean l_blnMargin =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //�����ڋq�̏ꍇ
        if (l_blnMargin == false)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                STR_METHOD_NAME);
        }

        try
        {
            //�]�͌v�Z�����I�u�W�F�N�g�𐶐�
            WEB3TPCalcCondition l_calcCondition =
                WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

            //���������e�̔z��
            WEB3TPNewOrderSpec[] l_newOrderSpecs = null;

            //����.�������e��null�̏ꍇ
            if (l_orderSpec != null)
            {
                //���������e�I�u�W�F�N�g�𐶐�
                WEB3TPNewOrderSpec l_newOrderSpec =
                    WEB3TPNewOrderSpec.create(l_subAccount, l_orderSpecIntercepter, l_orderSpec);
                //���������e�̔z����쐬����
                l_newOrderSpecs = new WEB3TPNewOrderSpec[] { l_newOrderSpec };
            }

            //�ԍό�]�͍X�V�I�u�W�F�N�g�𐶐�
            WEB3TPTradingPowerUpdAfterRepay l_tpUpdAfterRepay =
                new WEB3TPTradingPowerUpdAfterRepay(
                    l_lngAccountId,
                    l_blnMargin,
                    l_calcCondition,
                    l_newOrderSpecs);

            /*
             * �]�͌v�Z����Params�I�u�W�F�N�g���擾
             */
            List l_updResults =
                l_tpUpdAfterRepay.calcTradingpowerUpdResultMargin(WEB3TPMarkToMarketDivDef.NORMAL);

            //�������ԍό��ϑ��v
            double[] l_orderProfitLoss = new double[6];
            double[] l_currProfitLoss = new double[6];

            //�w���(T+0�`T+5)�͈̔͂�LOOP����
            for (int index = WEB3TPSpecifiedPointDef.T_0;
                index <= WEB3TPSpecifiedPointDef.T_5;
                index++)
            {
                //�w����̔������ԍό��ϑ��v���擾
                l_orderProfitLoss[index] = l_tpUpdAfterRepay.getOrderRepaySettleProfitLoss(index);
                //�w����̍���ԍϕ����ϑ��v���擾
                l_currProfitLoss[index] =
                    l_tpUpdAfterRepay.getCurrOrderRepaySettleProfitLoss(index);
            }

            //�ԍό㎑�Y�]�͏��<�M�p�ڋq>�I�u�W�F�N�g�𐶐�����B
            WEB3TPTradingPowerCalcMarginAfterRepay l_tpCalcMarginAfterRepay =
                new WEB3TPTradingPowerCalcMarginAfterRepay(
                    l_updResults,
                    l_calcCondition,
                    l_orderProfitLoss,
                    l_currProfitLoss);

            //���������ԍό㎑�Y�]�͏��<�M�p�ڋq>�I�u�W�F�N�g��ԋp����
            log.exiting(STR_METHOD_NAME);
            return l_tpCalcMarginAfterRepay;
        }
        catch (WEB3BaseRuntimeException baseRunEx)
        {
            //�G���[���X���[
            log.error(baseRunEx.getErrorMessage());
            throw new WEB3SystemLayerException(
                baseRunEx.getErrorInfo(),
                baseRunEx.getErrorMethod(),
                baseRunEx.getErrorMessage(),
                baseRunEx.getException());
        }
        catch (Exception e)
        {
            //�G���[���X���[
            log.error(e.getMessage());
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
    }

    /**
     * (create�ԍώ����ӕ���)<BR>
     * <BR>
     * �ԍώ����ӕ����I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �V�[�P���X�}�u(�ԍό�]�̓T�[�r�XImpl)create�ԍώ����ӕ����v�Q��<BR>
     * <BR>
     * @@param l_subAccount
     * @@param l_orderSpecIntercepter
     * @@param l_orderSpec
     * @@return webbroker3.tradingpower.WEB3TPAttentionObjection
     * @@roseuid 41E3842801ED
     */
    public WEB3TPAttentionObjection createWEB3TPAttentionObjection(
        WEB3GentradeSubAccount l_subAccount,
        Object l_orderSpecIntercepter,
        Object l_orderSpec)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "WEB3TPTradingPowerAfterRepayServiceImpl.createWEB3TPAttentionObjection(WEB3GentradeSubAccount, Object, Object)";
        log.entering(STR_METHOD_NAME);

        //����.�⏕������null�̏ꍇ
        if (l_subAccount == null)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                STR_METHOD_NAME);
        }

        //����ID���擾
        long l_lngAccountId = l_subAccount.getAccountId();

        //�ڋq�I�u�W�F�N�g���擾
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        //�M�p�����J�݋敪���擾
        boolean l_blnMargin =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //�����ڋq�̏ꍇ
        if (l_blnMargin == false)
        {
            //�G���[���X���[
            log.error("illegal Argument");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                STR_METHOD_NAME);
        }

        try
        {
            //�]�͌v�Z�����I�u�W�F�N�g�𐶐�
            WEB3TPCalcCondition l_calcCondition =
                WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

            //�]�͌v�Z����(List)���擾
            List l_resultMargin =
                WEB3TPTradingPowerCalcMargin.findCalcResultMarginParams(l_lngAccountId);

            //�ԍϑO�̎��Y�]�͏��<�M�p�ڋq>�I�u�W�F�N�g�𐶐�
            WEB3TPTradingPowerCalcMargin l_tpCalcMargin =
                new WEB3TPTradingPowerCalcMargin(l_resultMargin, l_calcCondition);

            //�ԍϑO���������t���O
            boolean l_blnDemandBeforeRepay = false;
            //�w���(T+0�`T+5)�͈̔͂�LOOP����
            for (int index = WEB3TPSpecifiedPointDef.T_0;
                index <= WEB3TPSpecifiedPointDef.T_5;
                index++)
            {
                //�����������������Ă���(���������z(n) > 0) �̏ꍇ
                if (l_tpCalcMargin.calcDemandamount(index) > 0)
                {
                    l_blnDemandBeforeRepay = true;
                }
            }

            //���������e�̔z��
            WEB3TPNewOrderSpec[] l_newOrderSpecs = null;

            //����.�������e��null�̏ꍇ
            if (l_orderSpec != null)
            {
                //���������e�I�u�W�F�N�g�𐶐�
                WEB3TPNewOrderSpec l_newOrderSpec =
                    WEB3TPNewOrderSpec.create(l_subAccount, l_orderSpecIntercepter, l_orderSpec);
                //���������e�̔z����쐬����
                l_newOrderSpecs = new WEB3TPNewOrderSpec[] { l_newOrderSpec };
            }

            //�ԍό�]�͍X�V�I�u�W�F�N�g�𐶐�
            WEB3TPTradingPowerUpdAfterRepay l_tpUpdAfterRepay =
                new WEB3TPTradingPowerUpdAfterRepay(
                    l_lngAccountId,
                    l_blnMargin,
                    l_calcCondition,
                    l_newOrderSpecs);

            /*
             * �]�͌v�Z����Params�I�u�W�F�N�g���擾
             */
            List l_updResults =
                l_tpUpdAfterRepay.calcTradingpowerUpdResultMargin(WEB3TPMarkToMarketDivDef.NORMAL);

            //�ԍό�̎��Y�]�͏��<�M�p�ڋq>�I�u�W�F�N�g�𐶐�
            l_tpCalcMargin = new WEB3TPTradingPowerCalcMargin(l_updResults, l_calcCondition);

            //�ԍό���������t���O
            boolean l_blnDemandAfterRepay = false;
            //(���߂�)�ԍό���������z
            double l_dblDemandamount = 0;
            //�w���(T+0�`T+5)�͈̔͂�LOOP����
            for (int index = WEB3TPSpecifiedPointDef.T_5;
                index >= WEB3TPSpecifiedPointDef.T_0;
                index--)
            {
                //��������(n)
                double l_dblBufDemandamount = l_tpCalcMargin.calcDemandamount(index);

                //�����������������Ă���(���������z(n) > 0) �̏ꍇ
                if (l_dblBufDemandamount > 0)
                {
                    l_blnDemandAfterRepay = true;
                    l_dblDemandamount = l_dblBufDemandamount;
                }
            }

            //�ԍώ����ӕ����I�u�W�F�N�g�𐶐�
            WEB3TPAttentionObjection l_attentionObjection = new WEB3TPAttentionObjection();

            //[�ԍϑO���������F�� or �~�A�ԍό���������F�~]
            if (l_blnDemandAfterRepay == false)
            {
                //���ӕ����\���敪   �� ���ӕ����\���敪Def.���ӕ����\������
                l_attentionObjection.attentionObjectionType =
                    WEB3TPAttentionObjectionTypeDef.NO_ATTENTION;
                //���������z      �� 0
                l_attentionObjection.demandAmount = 0;
            }
            //[�ԍϑO���������F���A�ԍό���������F��]
            else if (l_blnDemandBeforeRepay == true && l_blnDemandAfterRepay == true)
            {
                //���ӕ����\���敪   �� ���ӕ����\���敪Def.���ӕ����\���L��(�ԍϑO���������L��)
                l_attentionObjection.attentionObjectionType =
                    WEB3TPAttentionObjectionTypeDef.ATTENTION_BEFORE_REPAY;
                //���������z      �� (���߂�)�ԍό���������z
                l_attentionObjection.demandAmount = l_dblDemandamount;
            }
            //[�ԍϑO���������F�~�A�ԍό���������F��]
            else if (l_blnDemandBeforeRepay == false && l_blnDemandAfterRepay == true)
            {
                //���ӕ����\���敪   �� ���ӕ����\���敪Def.���ӕ����\���L��(�ԍϑO������������)
                l_attentionObjection.attentionObjectionType =
                    WEB3TPAttentionObjectionTypeDef.ATTENTION_AFTER_REPAY;
                //���������z      �� (���߂�)�ԍό���������z
                l_attentionObjection.demandAmount = l_dblDemandamount;
            }

            //���������ԍώ����ӕ����I�u�W�F�N�g��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return l_attentionObjection;
        }
        catch (WEB3BaseRuntimeException baseRunEx)
        {
            //�G���[���X���[
            log.error(baseRunEx.getErrorMessage());
            throw new WEB3SystemLayerException(
                baseRunEx.getErrorInfo(),
                baseRunEx.getErrorMethod(),
                baseRunEx.getErrorMessage(),
                baseRunEx.getException());
        }
        catch (Exception e)
        {
            //�G���[���X���[
            log.error(e.getMessage());
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                STR_METHOD_NAME,
                e.getMessage(),
                e);
        }
    }
}
@
