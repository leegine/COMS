head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.35.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTransactionAmountAfterRepay.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ԍό������(WEB3TPTransactionAmountAfterRepay.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/11 nakazato(ACT) �V�K�쐬
                   2006/09/15 �Ԑi�@@  (���u)���f��No.30
*/

package webbroker3.tradingpower.updtpower.cash;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;

import webbroker3.tradingpower.updtpower.WEB3TPNewOrderSpec;
import webbroker3.tradingpower.updtpower.WEB3TPPersistentDataManager;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�ԍό������)
 */
public class WEB3TPTransactionAmountAfterRepay extends WEB3TPTransactionAmount
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPTransactionAmountAfterRepay.class);

    /**
     * @@roseuid 41E383D600D4
     */
    public WEB3TPTransactionAmountAfterRepay()
    {
    }

    /**
     * (create�ԍό������)<BR>
     * <BR>
     * �ԍό�������𐶐����A�ԋp����B<BR>
     * <BR>
     * �P�j�@@�C���X�^���X�𐶐�<BR>
     * �Q�j�@@�l��ݒ�<BR>
     * �@@�ڋq��񁁈���.�ԍό㑍����.get�ڋq���()<BR>
     * �@@�v�Z����������.�ԍό㑍����.get�v�Z����()<BR>
     * �@@���������e������.�ԍό㑍����.get���������e()<BR> 
     * �R�j�@@�C���X�^���X��ԋp<BR>
     *
     * @@param l_valuation (������)
     * @@return WEB3TPTransactionAmount
     */
    public static WEB3TPTransactionAmountAfterRepay createWEB3TPTransactionAmountAfterRepay(WEB3TPCashValuationAfterRepay l_valuation)
    {
        final String STR_METHOD_NAME =
            "createWEB3TPTransactionAmountAfterRepay(WEB3TPCashValuationAfterRepay)";
        log.entering(STR_METHOD_NAME);

        WEB3TPTransactionAmountAfterRepay l_instance = new WEB3TPTransactionAmountAfterRepay();
        l_instance.setAccountInfo(l_valuation.getAccountInfo());
        l_instance.setCalcCondition(l_valuation.getCalcCondition());
        l_instance.setNewOrderSpecs(l_valuation.getNewOrderSpecs());

        log.exiting(STR_METHOD_NAME);
        return l_instance;
    }

    /**
     * (calc�������ԍό��ϑ��v)<BR>
     * <BR>
     * ����ԍϕ��@@�{�@@�����ԍϒ�����<BR>
     * <BR>
     * �V�[�P���X�}�u(�ԍό�]�͍X�V)get�������ԍό��ϑ��v�v�Q��<BR>
     * <BR>
     * @@param l_datDate - (�w���)
     * @@return double
     * @@roseuid 41CB7E3D02E9
     */
    public double calcOrderRepaySettleProfitLoss(Date l_datDate)
    {
        final String STR_METHOD_NAME = "calcOrderRepaySettleProfitLoss(Date)";
        log.entering(STR_METHOD_NAME);

        //�������ԍό��ϑ��v(n)
        double l_dblOrderRepaySettleProfitLoss = 0;

        //������ꗗ<����>���擾
        List l_trans = this.getTodaysTransactions();

        //������ꗗ<����>�̗v�f����LOOP
        for (Iterator l_iter = l_trans.iterator(); l_iter.hasNext();)
        {
            //���X�g���A�I�u�W�F�N�g���擾
            Object l_element = (Object)l_iter.next();

            //�ԍό������I�u�W�F�N�g�̏ꍇ
            if (l_element instanceof WEB3TPTransactionReflectorAfterRepay)
            {
                WEB3TPTransactionReflectorAfterRepay l_tpReflectorAfterRepay =
                    (WEB3TPTransactionReflectorAfterRepay)l_element;

                //����.�w������A�ϓ����f���������ꍇ
                //(is�ϓ����ԓ�() = true)
                if (l_tpReflectorAfterRepay.isDuringReflectTime(l_datDate) == true)
                {
                    //�����ԍό��ϑ��v���擾
                    double l_dblUnexecuted =
                        l_tpReflectorAfterRepay.getUnexecutedRepaySettleProfitLoss();

                    //���񔭒����ԍό��ϑ��v���擾
                    double l_dblCurrOrder =
                        l_tpReflectorAfterRepay.getCurrOrderRepaySettleProfitLoss();

                    /*
                     * �������ԍό��ϑ��v(n) = SUM(�����ԍό��ϑ��v + ���񔭒����ԍό��ϑ��v)
                     */
                    l_dblOrderRepaySettleProfitLoss =
                        l_dblOrderRepaySettleProfitLoss + l_dblUnexecuted + l_dblCurrOrder;
                }
            }
        }

        //�v�Z�����������ԍό��ϑ��v(n)��ԋp
        log.exiting(STR_METHOD_NAME);
        return l_dblOrderRepaySettleProfitLoss;
    }

    /**
     * (calc����ԍϕ��ԍό��ϑ��v)
     * 
     * ����ԍϕ��݂̂̌��ϑ��v
     * @@param l_datDate - (�w���)
     * @@return double
     * @@roseuid 41CB7E3D0308
     */
    public double calcCurrOrderRepaySettleProfitLoss(Date l_datDate)
    {
        final String STR_METHOD_NAME = "calcCurrOrderRepaySettleProfitLoss(Date)";
        log.entering(STR_METHOD_NAME);

        //����ԍϕ��ԍό��ϑ��v(n)
        double l_dblCurrOrderRepaySettleProfitLoss = 0;

        //������ꗗ<����>���擾
        List l_trans = this.getTodaysTransactions();

        //������ꗗ<����>�̗v�f����LOOP
        for (Iterator l_iter = l_trans.iterator(); l_iter.hasNext();)
        {
            //���X�g���A�I�u�W�F�N�g���擾
            Object l_element = (Object)l_iter.next();

            //�ԍό������I�u�W�F�N�g�̏ꍇ
            if (l_element instanceof WEB3TPTransactionReflectorAfterRepay)
            {
                WEB3TPTransactionReflectorAfterRepay l_tpReflectorAfterRepay =
                    (WEB3TPTransactionReflectorAfterRepay)l_element;

                //����.�w������A�ϓ����f���������ꍇ
                //(is�ϓ����ԓ�() = true)
                if (l_tpReflectorAfterRepay.isDuringReflectTime(l_datDate) == true)
                {
                    //���񔭒����ԍό��ϑ��v���擾
                    double l_dblCurrOrder =
                        l_tpReflectorAfterRepay.getCurrOrderRepaySettleProfitLoss();

                    /*
                     * ����ԍϕ��ԍό��ϑ��v(n) = SUM(���񔭒����ԍό��ϑ��v)
                     */
                    l_dblCurrOrderRepaySettleProfitLoss =
                        l_dblCurrOrderRepaySettleProfitLoss + l_dblCurrOrder;
                }
            }
        }

        //�v�Z��������ԍϕ��ԍό��ϑ��v(n)��ԋp
        log.exiting(STR_METHOD_NAME);
        return l_dblCurrOrderRepaySettleProfitLoss;
    }

    /**
     * (�I�[�o�[���C�h)(do�M�p�ԍώ����񃍁[�h<����>)<BR>
     * <BR>
     * �M�p�ԍώ�����<����>�����[�h����B<BR>
     * <BR>
     * �V�[�P���X�}�u(�ԍό������)do�M�p�ԍώ����񃍁[�h<����>�v�Q��<BR>
     * <BR>
     * @@roseuid 41E37ECE00D4
     */
    protected void loadTodaysCloseMarginTransactions()
    {
        final String STR_METHOD_NAME = "loadTodaysCloseMarginTransactions()";
        log.entering(STR_METHOD_NAME);

        List l_rows =
            WEB3TPPersistentDataManager.getInstance().getTodaysCloseMarginOrdersAfterRepay(this);
        loadTodaysEqtypeTransactions(l_rows);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (�I�[�o�[���C�h)(do���������񃍁[�h<����>)<BR>
     * <BR>
     * ���������̎�����<����>�����[�h����B<BR>
     * <BR>
     * �V�[�P���X�}�u(�ԍό������)do�M�p�ԍώ����񃍁[�h<����>�v�Q��<BR>
     * <BR>
     * @@param l_eqtypeOrderUnitRows (���������P�ʂ̃��X�g)
     */
    protected void loadTodaysEqtypeTransactions(List l_rows)
    {
        final String STR_METHOD_NAME = "loadTodaysEqtypeTransactions(List)";
        log.entering(STR_METHOD_NAME);

        if (l_rows != null)
        {
            for (Iterator l_iter = l_rows.iterator(); l_iter.hasNext();)
            {
                //���������P��ROW���擾
                EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_iter.next();
                //�����J�e�S�����擾
                OrderTypeEnum l_orderTypeEnum = l_orderUnitRow.getOrderType();

                //������
                WEB3TPTransactionReflector[] l_tranRefs;

                //�����^�C�v �� �M�p���ԍρ@@�܂��́@@�M�p���ԍς̏ꍇ
                if (OrderTypeEnum.CLOSE_MARGIN_LONG.equals(l_orderTypeEnum)
                    || OrderTypeEnum.CLOSE_MARGIN_SHORT.equals(l_orderTypeEnum))
                {
                    l_tranRefs = createWEB3TPTransactionReflectorAfterRepay(l_orderUnitRow);
                }
                //����ȊO
                else
                {
                    l_tranRefs = createWEB3TPTransactionReflector(l_orderUnitRow);
                }

                for (int i = 0; i < l_tranRefs.length; i++)
                {
                    addTodaysTransaction(l_tranRefs[i]);
                }
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (�I�[�o�[���C�h)(add������<����>)<BR>
     * <BR>
     * ������������ꗗ<����>�ɒǉ�����B<BR>
     * <BR>
     * �V�[�P���X�}�u(�ԍό������)do�M�p�ԍώ����񃍁[�h<����>�v�Q��<BR>
     * <BR>
     * @@param transaction - (������)
     */
    protected void addTodaysTransaction(WEB3TPTransactionReflector l_transaction)
    {
        final String STR_METHOD_NAME = "addTodaysTransaction(WEB3TPTransactionReflector)";
        log.entering(STR_METHOD_NAME);

        if (l_transaction instanceof WEB3TPTransactionReflector
            || l_transaction instanceof WEB3TPTransactionReflectorAfterRepay)
        {
            this.getTodaysTransactions().add(l_transaction);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create�ԍό������)<BR>
     * <BR>
     * ����.�����������<BR> 
     * �ԍό��������쐬���ԋp����B<BR> 
     * <BR>
     * �V�[�P���X�}�u(�ԍό������)create�ԍό������v�Q��<BR>
     * <BR>
     * @@param l_row - (��������)
     * @@return webbroker3.tradingpower.updtpower.cash.WEB3TPTransactionReflectorAfterRepay[]
     */
    protected WEB3TPTransactionReflectorAfterRepay[] createWEB3TPTransactionReflectorAfterRepay(EqtypeOrderUnitRow l_row)
    {
        final String STR_METHOD_NAME =
            "createWEB3TPTransactionReflectorAfterRepay(EqtypeOrderUnitRow)";
        log.entering(STR_METHOD_NAME);

        //����萔��
        double l_dblUnexecutedQuantity = 0;
        //���ϐ���
        double l_dblExecutedQuantity = 0;
        //�������
        double l_dblUnexecutedAmount = 0;
        //���ϑ��
        double l_dblExecutedAmount = 0;

        //����ԍϕ��ԍό��ϑ��v
        double l_dblCurrOrderRepaySettleProfitLoss = 0;
        //����蕪�ԍό��ϑ��v
        double l_dblUnexecutedRepaySettleProfitLoss = 0;

        //���������擾
        Date l_tranDate = WEB3DateUtility.getDate(l_row.getBizDate(), "yyyyMMdd");
        //��n�����擾
        Date l_deliveryDate = l_row.getDeliveryDate();
        //�g�����U�N�V�����^�C�v���擾
        FinTransactionType l_tranType = l_row.getOrderType().toFinTransactionType();
        //�g�����U�N�V�����^�C�v�ʂ̑��v�������擾
        int l_intCashDir = WEB3TPTransactionReflector.getCashDir(l_tranType);

        //�S���̏ꍇ
        if (OrderOpenStatusEnum.CLOSED.equals(l_row.getOrderOpenStatus()))
        {
            //����萔�� = 0
            l_dblUnexecutedQuantity = 0;
            //������� = 0
            l_dblUnexecutedAmount = 0;

            //���ϐ��� = �����P�ʃe�[�u��.��萔��
            l_dblExecutedQuantity = l_row.getExecutedQuantity();
            //���ϑ�� = SUM(�����g�����U�N�V�����e�[�u��.��n���)
            l_dblExecutedAmount = getNetAmountTotal(l_row);
        }
        //����肠�邢�͈ꕔ���̏ꍇ
        else
        {
            //�T�Z��n������擾
            double l_dblEstimatedPrice = l_row.getEstimatedPrice();

            /*
             * �M�p�ԍς̏ꍇ[���������e�[�u��.������ʁ��M�p���ԍρA�M�p���ԍς̏ꍇ]
             * �@@�E����萔�� �� 0�@@
             * �@@�E������� �� 0
             * �@@�E���ϐ��� �� ���������e�[�u��.��������
             * �@@�E���ϑ�� �� ���������e�[�u��.�T�Z��n���z
             * 
             * �@@�i���j����ԍϒ����̏ꍇ(���������e�[�u��.�����P��ID == -1)
             * �@@�@@�E����ԍϕ��ԍό��ϑ��v �� ���������e�[�u��.�T�Z��n���z
             * �@@�@@�E����蕪�ԍό��ϑ��v �� 0
             * �@@�i���j���������ԍϒ����̏ꍇ(���������e�[�u��.�����P��ID �� -1)
             * �@@�@@�E����ԍϕ��ԍό��ϑ��v �� 0
             * �@@�@@�E����蕪�ԍό��ϑ��v �� ���������e�[�u��.�T�Z��n���z �| ��n���
             * 
             */
            //����萔�� = 0
            l_dblUnexecutedQuantity = 0;
            //������� = 0
            l_dblUnexecutedAmount = 0;
            //���ϐ��� = �����P�ʃe�[�u��.��������
            l_dblExecutedQuantity =
                l_row.getConfirmedQuantityIsNull()
                    ? l_row.getQuantity()
                    : l_row.getConfirmedQuantity();
            //���ϑ�� = �T�Z��n���
            l_dblExecutedAmount = l_dblEstimatedPrice * l_intCashDir;

            //����ԍϒ����̏ꍇ(���������e�[�u��.�����P��ID == -1)
            if (l_row.getOrderUnitId() == WEB3TPNewOrderSpec.DEFAULT_NEW_ID)
            {
                //����ԍϕ��ԍό��ϑ��v �� ���������e�[�u��.�T�Z��n���z
                l_dblCurrOrderRepaySettleProfitLoss = l_dblEstimatedPrice * l_intCashDir;
                //����蕪�ԍό��ϑ��v �� 0
                l_dblUnexecutedRepaySettleProfitLoss = 0;
            }
            //���������ԍϒ����̏ꍇ(���������e�[�u��.�����P��ID �� -1)
            else
            {
                //����ԍϕ��ԍό��ϑ��v �� 0
                l_dblCurrOrderRepaySettleProfitLoss = 0;
                //����蕪�ԍό��ϑ��v �� ���������e�[�u��.�T�Z��n���z �| ��n���
                l_dblUnexecutedRepaySettleProfitLoss =
                    (l_row.getExecutedQuantity() == 0)
                        ? l_dblEstimatedPrice * l_intCashDir
                        : (l_dblEstimatedPrice - getNetAmountTotal(l_row)) * l_intCashDir;
            }
        }

        /*
         * �ԍό������I�u�W�F�N�g�𐶐����ԋp����B
         */
        log.exiting(STR_METHOD_NAME);
        return new WEB3TPTransactionReflectorAfterRepay[] {
             WEB3TPTransactionReflectorAfterRepay.createWEB3TPTransactionReflectorAftreRepay(
                getCalcCondition(),
                l_row.getProductType(),
                l_row.getProductId(),
                l_tranType,
                l_tranDate,
                l_dblUnexecutedQuantity,
                l_dblUnexecutedAmount,
                l_dblExecutedQuantity,
                l_dblExecutedAmount,
                l_deliveryDate,
                l_row.getTaxType(),
                l_dblUnexecutedRepaySettleProfitLoss,
                l_dblCurrOrderRepaySettleProfitLoss)};
    }
}
@
