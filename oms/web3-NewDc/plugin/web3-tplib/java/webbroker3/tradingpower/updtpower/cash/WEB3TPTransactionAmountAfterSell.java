head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.36.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTransactionAmountAfterSell.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : ���t�������(WEB3TPTransactionAmountAfterSell.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/09/08 nakazato(DIR-ST) �V�K�쐬
                    2006/09/15 �Ԑi�@@  (���u)   ���f��No.32
 Revesion History : 2010/01/11 ���g�@@  (���u)   ���f��No.426,No.427
 */
package webbroker3.tradingpower.updtpower.cash;

import java.math.BigDecimal;
import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;

import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.define.WEB3TPSellOrderPriceDivDef;
import webbroker3.tradingpower.updtpower.WEB3TPNewOrderSpec;
import webbroker3.tradingpower.util.ToStringBuilder;
import webbroker3.tradingpower.util.ToStringUtils;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (���t�������)
 * ���������N���X�̊g���N���X
 */
public class WEB3TPTransactionAmountAfterSell extends WEB3TPTransactionAmount
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3TPTransactionAmountAfterSell.class);

    /**
     * (��������ID)
     */
    private long orderProductId;

    /**
     * (�f�t�H���g�R���X�g���N�^)
     */
    public WEB3TPTransactionAmountAfterSell()
    {
        super();
    }

    /**
     * (static���\�b�h)(create���t�������)
     * 
     * ���t��������I�u�W�F�N�g�𐶐����ԋp����B
     * 
     * �P�j���t��������I�u�W�F�N�g�𐶐�����B
     * �@@-�f�t�H���g�R���X�g���N�^���R�[��
     * 
     * �Q�j�����������t��������I�u�W�F�N�g�̃v���p�e�B�ɒl���Z�b�g����B
     * 
     * �@@���ڋq�������Z�b�g
     * �@@�@@-���t��������I�u�W�F�N�g.set�ڋq����()���R�[��
     * 
     * �@@�@@[����]�@@
     * �@@�@@�@@�ڋq�����F����.������.get�ڋq����()�̖߂�l
     * �@@
     * �@@���v�Z�������Z�b�g
     * �@@�@@-���t��������I�u�W�F�N�g.set�]�͌v�Z�������R�[��
     * 
     * �@@�@@[����]
     * �@@�@@�@@�]�͌v�Z�����F����.������.get�]�͌v�Z�����̖߂�l
     * 
     * �@@�����������e���Z�b�g
     * �@@�@@-���t��������I�u�W�F�N�g.set���������e()���R�[��
     * 
     * �@@�@@[����]
     * �@@�@@�@@���������e�F����.������.get���������e�̖߂�l
     * 
     * �@@����������ID���Z�b�g
     * �@@�@@-���t��������I�u�W�F�N�gset��������ID()���R�[��
     * 
     * �@@�@@[����]
     * �@@�@@�@@��������ID�F����.��������ID
     * 
     * �R�j�����������t��������I�u�W�F�N�g�̕ԋp����B
     * 
     * �@@[�ԋp�l]
     * �@@�@@�����������t��������I�u�W�F�N�g
     * 
     * @@param l_valuation - (������)
     * @@param l_orderProductId - (��������ID)
     * @@return webbroker3.tradingpower.updtpower.cash.WEB3TPTransactionAmountSellOrder
     */
    public static WEB3TPTransactionAmountAfterSell createWEB3TPTransactionAmountAfterSell(
        WEB3TPCashValuation l_valuation,
        long l_orderProductId)
    {
        final String STR_METHOD_NAME = "WEB3TPTransactionAmountSellOrder.createWEB3TPTransactionAmountAfterSell(WEB3TPCashValuation, long)";
        log.entering(STR_METHOD_NAME);

        WEB3TPTransactionAmountAfterSell l_instance = new WEB3TPTransactionAmountAfterSell();

        //�ڋq�������Z�b�g
        l_instance.setAccountInfo(l_valuation.getAccountInfo());
        //�v�Z�������Z�b�g
        l_instance.setCalcCondition(l_valuation.getCalcCondition());
        //���������e���Z�b�g
        l_instance.setNewOrderSpecs(l_valuation.getNewOrderSpecs());
        //��������ID���Z�b�g
        l_instance.setOrderProductId(l_orderProductId);

        log.exiting(STR_METHOD_NAME);
        return l_instance;
    }

    /**
     * (create������(��������:EqtypeOrderUnitRow))<BR>
     * ���X�[�p�[�N���X(�F������).create������(��������:EqtypeOrderUnitRow)�̃I�[�o�[���C�h<BR>
     * ���������t�����̖�������v�㕔�����X�[�o�[�N���X�ƈقȂ�B<BR>
     * <BR>
     * �V�[�P���X�}�u(���t�������)create������(EqtypeOrderUnitRow)�v�Q�� <BR>
     * <BR>
     * <BR>
     * <BR>
     * @@param l_eqtypeOrderUnitRow
     * @@return webbroker3.tradingpower.updtpower.cash.WEB3TPTransactionReflector
     */
    protected WEB3TPTransactionReflector[] createWEB3TPTransactionReflector(
        EqtypeOrderUnitRow l_eqtypeOrderUnitRow)
    {
        final String STR_METHOD_NAME = "WEB3TPTransactionAmountSellOrder.createWEB3TPTransactionReflector(l_eqtypeOrderUnitRow)";
        log.entering(STR_METHOD_NAME);

        /*
         * 1.1 ���X�v���t�@@�����X�e�[�u�����u����蔄�t�P���敪�v���擾����B
         */
        String l_strInstBran = this.getCalcCondition().getInstBranCalcCondition(
            WEB3TPCalcCondition.SELLORDER_PRICE_DIV);

        //���ϐ���
        double l_dblExecutedQuantity = 0;
        //���ϑ��
        double l_dblExecutedAmount = 0;
        //����萔�� 
        double l_dblUnexecutedQuantity = 0;
        //�������
        double l_dblUnexecutedAmount = 0;

        //�g�����U�N�V����������
        Date l_tranDate = WEB3DateUtility.getDate(l_eqtypeOrderUnitRow.getBizDate(), "yyyyMMdd");
        //��n��
        Date l_deliveryDate = l_eqtypeOrderUnitRow.getDeliveryDate();
        //�g�����U�N�V�����^�C�v
        FinTransactionType l_tranType = l_eqtypeOrderUnitRow.getOrderType().toFinTransactionType();
        //1.2���v����
        int l_intCashDir = WEB3TPTransactionReflector.getCashDir(l_tranType);

        //1.3 [a. �������������t���� ���@@���������Ɠ������̏ꍇ]
        // �i����.��������.�����L����� �I= 2:CLOSE and 
        // �@@����.��������.������� == 2�F���������� and 
        // �@@����.��������.����ID = this.get��������ID()�j
        if(!OrderOpenStatusEnum.CLOSED.equals(l_eqtypeOrderUnitRow.getOrderOpenStatus())
            && FinTransactionType.EQTYPE_EQUITY_SELL.equals(l_tranType)
            && l_eqtypeOrderUnitRow.getProductId() == this.getOrderProductId())
        {
            //1.3.1 [b. �����]���̏ꍇ]
            //(�P�j�̖߂�l == 1:QUOTE_PRICE�j
            if(WEB3TPSellOrderPriceDivDef.QUOTE_PRICE.equals(l_strInstBran) == true)
            {
                //�g���ϐ��ʁh = ����.��������.��������
                l_dblExecutedQuantity = l_eqtypeOrderUnitRow.getQuantity();
                //�g���ϑ���h = ����.��������.�T�Z��n���
                l_dblExecutedAmount = l_eqtypeOrderUnitRow.getEstimatedPrice();
                //�g����萔�ʁh = 0
                l_dblUnexecutedQuantity = 0;
                //�g��������h = 0
                l_dblUnexecutedAmount = 0;
            }
            //1.3.2  [b. �ȊO�i0�~�]���j�̏ꍇ]
            else
            {
                //�g���ϐ��ʁh = ����.��������.��������
                l_dblExecutedQuantity = l_eqtypeOrderUnitRow.getQuantity();
                //�g���ϑ���h = �g��n����h
                l_dblExecutedAmount = getNetAmountTotal(l_eqtypeOrderUnitRow);
                //�g����萔�ʁh = 0
                l_dblUnexecutedQuantity = 0;
                //�g��������h = 0
                l_dblUnexecutedAmount = 0;
            }
        }
        //1.4 [a. �ȊO�̏ꍇ]
        else
        {
            /*
             * �@@�g���ϐ��ʁh���擾����B
             */
            //�g���ϐ��ʁh = ����.��������.��萔��
            l_dblExecutedQuantity = l_eqtypeOrderUnitRow.getExecutedQuantity();

            /*
             * �A�g����萔�ʁh���擾����B
             */
            //1.4.1 [b. ����.���������������ƂȂ��Ă���ꍇ]
            //�i����.��������.�����L����� == 2:�N���[�Y�j
            if(OrderOpenStatusEnum.CLOSED.equals(l_eqtypeOrderUnitRow.getOrderOpenStatus()))
            {
                //�g����萔�ʁh = 0
                l_dblUnexecutedQuantity = 0;
            }
            //[b. �ȊO�i����.�����������L���j�̏ꍇ]
            else
            {
                //�g����萔�ʁh = ����.��������.�������� - ����.��������.��萔��
                l_dblUnexecutedQuantity = l_eqtypeOrderUnitRow.getQuantity()
                    - l_eqtypeOrderUnitRow.getExecutedQuantity();
            }

            /*
             * �B�g���ϑ���h�A�g��������h���擾����B
             */
            //1.4.2 [b. �S����� �̏ꍇ]
            //�i�g����萔�ʁh == 0 �j
            if(l_dblUnexecutedQuantity == 0)
            {
                //�g���ϑ���h = �g��n����h
                l_dblExecutedAmount = getNetAmountTotal(l_eqtypeOrderUnitRow);
                //�g��������h = 0
                l_dblUnexecutedAmount = 0;
            }
            //1.4.3  [b. �ȊO�i�����܂��͈ꕔ���j �̏ꍇ]
            else
            {
                //�T�Z��n���
                double l_dblEstimatedPrice = l_eqtypeOrderUnitRow.getEstimatedPrice();

                //[c. �������� ���� ���n���� �̏ꍇ]
                //�i����.��������.������� == 7�F�������� or 8�F���n�����j
                if(FinTransactionType.EQTYPE_SWAP_MARGIN_LONG.equals(l_tranType)
                    || FinTransactionType.EQTYPE_SWAP_MARGIN_SHORT.equals(l_tranType))
                {
                    //�g���ϑ���h = ����.��������.�T�Z��n���
                    l_dblExecutedAmount = l_dblEstimatedPrice * l_intCashDir;
                    //�g��������h = 0
                    l_dblUnexecutedAmount = 0;
                }
                //[c. �~�j�����t���� �̏ꍇ]
                //�i����.��������.������� == 101�F�����~�j���������j
                else if(FinTransactionType.EQTYPE_MINI_STOCK_BUY.equals(l_tranType))
                {
                    //�g���ϑ���h = 0
                    l_dblExecutedAmount = 0;
                    //�g��������h = ����.��������.�T�Z��n���
                    l_dblUnexecutedAmount = l_dblEstimatedPrice * l_intCashDir;
                }
                //[c. �ȊO�i�������t�����A�������t�����A�M�p�ԍϒ����A�~�j�����t�����j �̏ꍇ]
                else
                {
                    //�g���ϑ���h = �g��n����h
                    l_dblExecutedAmount = getNetAmountTotal(l_eqtypeOrderUnitRow);

                    //[d. �������t���� �̏ꍇ]
                    //�i����.��������.������� == 1�F�����������j
                    if(FinTransactionType.EQTYPE_EQUITY_BUY.equals(l_tranType))
                    {
                        //�g��������h = (����.��������.�T�Z��n��� - ��Βl(�g���ϑ���h))  �~ -1
                        l_dblUnexecutedAmount = (l_dblExecutedQuantity == 0) ? l_dblEstimatedPrice
                            * l_intCashDir : (l_dblEstimatedPrice - Math.abs(l_dblExecutedAmount))
                            * l_intCashDir;
                    }
                    //[d. �ȊO�i���������ȊO�̌������t�����A�M�p�ԍϒ����A�~�j�����t�����j�̏ꍇ]
                    else
                    {
                        //�g��������h = 0
                        l_dblUnexecutedAmount = 0;
                    }
                }
            }
        }

        //1.5 [a.���񒍕������t�����̏ꍇ]
        //(����.��������.�����P��ID == -1 && ����.��������.������� == 2:����������)
        if(l_eqtypeOrderUnitRow.getOrderUnitId() == WEB3TPNewOrderSpec.DEFAULT_NEW_ID
            && FinTransactionType.EQTYPE_EQUITY_SELL.equals(l_tranType))
        {
            //�����P��
            double l_dblPrice = 0;

            //[�����]���̏ꍇ]
            //(�P�j�̖߂�l == 1:QUOTE_PRICE�j
            if(WEB3TPSellOrderPriceDivDef.QUOTE_PRICE.equals(l_strInstBran) == true)
            {
                //�����P�� = ����.��������.�����P��
                l_dblPrice = l_eqtypeOrderUnitRow.getPrice();
            }
            //[�ȊO�i0�~�]���j�̏ꍇ]
            else
            {
                //�����P�� = 0
                l_dblPrice = 0;
            }

            //1.5.1 ������I�u�W�F�N�g��ԋp����
            log.exiting(STR_METHOD_NAME);
            return new WEB3TPTransactionReflector[]{WEB3TPTransactionReflectorNewSellOrder.createWEB3TPTransactionReflectorNewSellOrder(
                getCalcCondition(),
                l_eqtypeOrderUnitRow.getProductType(),
                l_eqtypeOrderUnitRow.getProductId(),
                l_tranType,
                l_tranDate,
                l_dblUnexecutedQuantity,
                l_dblUnexecutedAmount,
                l_dblExecutedQuantity,
                l_dblExecutedAmount,
                l_deliveryDate,
                l_eqtypeOrderUnitRow.getTaxType(),
                l_dblPrice)};
        }
        //1.6 [a.�ȊO�i���������j�̏ꍇ]
        //�@@-������.create������()���R�[������@@
        else
        {
            //1.6.1 ������I�u�W�F�N�g��ԋp����
            log.exiting(STR_METHOD_NAME);
            return new WEB3TPTransactionReflector[]{WEB3TPTransactionReflector.create(
                getCalcCondition(),
                l_eqtypeOrderUnitRow.getProductType(),
                l_eqtypeOrderUnitRow.getProductId(),
                l_tranType,
                l_tranDate,
                l_dblUnexecutedQuantity,
                l_dblUnexecutedAmount,
                l_dblExecutedQuantity,
                l_dblExecutedAmount,
                l_deliveryDate,
                l_eqtypeOrderUnitRow.getTaxType())};
        }
    }

    /**
     * (get��������ID)
     * 
     * this.��������ID��ԋp����B
     * 
     * @@return long
     */
    public long getOrderProductId()
    {
        return this.orderProductId;
    }

    /**
     * (set��������ID)
     * 
     * ����.��������ID��this.��������ID�ɃZ�b�g����B
     * 
     * @@param l_orderProductId - (��������ID)
     */
    public void setOrderProductId(long l_orderProductId)
    {
        this.orderProductId = l_orderProductId;
    }

    /**
     * (create������)<BR>
     * (create������(�O������:FeqOrderUnitRow))<BR>
     * ���X�[�p�[�N���X(�F������).create������(�O������:EqtypeOrderUnitRow)�̃I�[�o�[���C�h<BR>
     * ���O�����t�����̖�������v�㕔�����X�[�o�[�N���X�ƈقȂ�B<BR>
     * <BR>
     * �V�[�P���X�}�u(���t�������)create������(FeqOrderUnitRow)�v�Q��<BR>
     * @@param l_feqOrderUnitRow - (�O������)<BR>
     * �O������<BR>
     * @@return WEB3TPTransactionReflector
     */
    protected WEB3TPTransactionReflector createWEB3TPTransactionReflector(FeqOrderUnitRow l_feqOrderUnitRow)
    {
        final String STR_METHOD_NAME = "createTransactionReflector(FeqOrderUnitRow)";
        log.entering(STR_METHOD_NAME);

        //get��Е��X�ʗ]�͌v�Z����
        //String:"sellorder.price.div"
        String l_strInstBran = this.getCalcCondition().getInstBranCalcCondition(
            WEB3TPCalcCondition.SELLORDER_PRICE_DIV);

        //get���v����(�g�����U�N�V�����^�C�v : FinTransactionType)
        //FinTransactionType = ����.�O������.get�������().toFinTransactionType()�̖߂�l
        FinTransactionType l_tranType = l_feqOrderUnitRow.getOrderType().toFinTransactionType();
        int l_intCashDir = WEB3TPTransactionReflector.getCashDir(l_tranType);

        //���ϐ���
        double l_dblExecutedQuantity = 0;
        //���ϑ��
        double l_dblExecutedAmount = 0;
        //����萔�� 
        double l_dblUnexecutedQuantity = 0;
        //�������
        double l_dblUnexecutedAmount = 0;

        if(OrderOpenStatusEnum.OPEN.equals(l_feqOrderUnitRow.getOrderOpenStatus())
            && FinTransactionType.EQTYPE_FEQ_SELL.equals(l_tranType)
            && l_feqOrderUnitRow.getProductId() == this.getOrderProductId())
        {
            if(WEB3TPSellOrderPriceDivDef.QUOTE_PRICE.equals(l_strInstBran))
            {
                l_dblExecutedQuantity = l_feqOrderUnitRow.getQuantity();
                l_dblExecutedAmount = l_feqOrderUnitRow.getEstimatedPrice();
                l_dblUnexecutedQuantity = 0;
                l_dblUnexecutedAmount = 0;
            }
            else
            {
                l_dblExecutedQuantity = l_feqOrderUnitRow.getQuantity();
                l_dblExecutedAmount = this.getNetAmountTotal(l_feqOrderUnitRow);
                l_dblUnexecutedQuantity = 0;
                l_dblUnexecutedAmount = 0;
            }
        }
        else
        {
            //�g���ϐ��ʁh = ����.�O������.��萔��
            l_dblExecutedQuantity = l_feqOrderUnitRow.getExecutedQuantity();

            //[b. ����.�O�������������ƂȂ��Ă���ꍇ]
            //�i����.�O������.�����L����� == 2:�N���[�Y�j
            if(OrderOpenStatusEnum.CLOSED.equals(l_feqOrderUnitRow.getOrderOpenStatus()))
            {
                //�g����萔�ʁh = 0
                l_dblUnexecutedQuantity = 0;
            }
            //[b. �ȊO�i����.�O���������L���j�̏ꍇ]
            else
            {
                if (l_feqOrderUnitRow.getConfirmedQuantityIsNull())
                {
                    //�g����萔�ʁh = ����.�O������.��������
                    l_dblUnexecutedQuantity = l_feqOrderUnitRow.getQuantity();
                }
                else
                {
                    //�g����萔�ʁh = ����.�O������.�s�ꂩ��m�F�ς݂̐��� - ����.�O������.��萔��
                    l_dblUnexecutedQuantity =
                        l_feqOrderUnitRow.getConfirmedQuantity()
                        - l_feqOrderUnitRow.getExecutedQuantity();
                }
            }

            //[b. �S����� �̏ꍇ]
            //�i�g����萔�ʁh == 0 �j
            if(GtlUtils.Double.isZero(l_dblUnexecutedQuantity))
            {
                //�g���ϑ���h = get���ώ�n������v
                l_dblExecutedAmount = this.getNetAmountTotal(l_feqOrderUnitRow);
                //�g��������h = 0
                l_dblUnexecutedAmount = 0;
            }
            //[b. �ȊO�i�����܂��͈ꕔ���j �̏ꍇ]
            else
            {
                //�g���ϑ���h = get���ώ�n������v                
                l_dblExecutedAmount = this.getNetAmountTotal(l_feqOrderUnitRow);

                if(FinTransactionType.EQTYPE_FEQ_BUY.equals(l_tranType))
                {
                    if (l_dblUnexecutedQuantity > 0)
                    {
                        BigDecimal l_bdEstimatedPrice = new BigDecimal(
                            Double.toString(l_feqOrderUnitRow.getEstimatedPrice()));
                        BigDecimal l_bdExecutedAmount = new BigDecimal(
                            Double.toString(Math.abs(l_dblExecutedAmount)));
                        l_dblUnexecutedAmount =
                            l_bdEstimatedPrice.subtract(l_bdExecutedAmount).doubleValue() * l_intCashDir;
                    }
                    else
                    {
                        l_dblUnexecutedAmount = 0;
                    }
                }
                else
                {
                    l_dblUnexecutedAmount = 0;
                }
            }
        }

        //�g�����U�N�V����������
        Date l_tranDate = WEB3DateUtility.getDate(
            l_feqOrderUnitRow.getBizDate(),
            WEB3GentradeTimeDef.DATE_FORMAT_YMD);
        //��n��
        Date l_deliveryDate = l_feqOrderUnitRow.getDeliveryDate();
        //[a.���񒍕������t�����̏ꍇ]
        //(����.�O������.�����P��ID == -1 && ����.�O������.������� == 702)
        if(l_feqOrderUnitRow.getOrderUnitId() == WEB3TPNewOrderSpec.DEFAULT_NEW_ID
            && FinTransactionType.EQTYPE_FEQ_SELL.equals(l_tranType))
        {
            
            //create���񔄕t������������
            //�]�͌v�Z���� = this.get�]�͌v�Z����()
            //ProductTypeEnum = ����.�O������.get�����^�C�v()
            //long = ����.�O������.get����ID()
            //FinTransactionType = ����.�O������.get�������().toFinTransactionType()
            //Date = ����.�O������.get������()
            //double = �v�Z����"����萔��"
            //double = �v�Z����"�������"
            //double = �v�Z����"���ϐ���"
            //double = �v�Z����"���ϑ��"
            //Date = ����.�O������.get��n��()
            //TaxTypeEnum = ����.�O������.get�ŋ敪()
            //double = 0
            log.exiting(STR_METHOD_NAME);
            return WEB3TPTransactionReflectorNewSellOrder.createWEB3TPTransactionReflectorNewSellOrder(
                this.getCalcCondition(),
                l_feqOrderUnitRow.getProductType(),
                l_feqOrderUnitRow.getProductId(),
                l_tranType,
                l_tranDate,
                l_dblUnexecutedQuantity,
                l_dblUnexecutedAmount,
                l_dblExecutedQuantity,
                l_dblExecutedAmount,
                l_deliveryDate,
                l_feqOrderUnitRow.getTaxType(),
                0D);
        }
        //[a.�ȊO�i���������j�̏ꍇ]
        //�@@-������.create������()���R�[������@@
        else
        {
            //create������
            //�]�͌v�Z���� = this.get�]�͌v�Z����()
            //ProductTypeEnum = ����.�O������.get�����^�C�v()
            //long = ����.�O������.get����ID()
            //FinTransactionType = ����.�O������.get�������().toFinTransactionType()
            //Date = ����.�O������.get������()
            //double = �v�Z����"����萔��"
            //double = �v�Z����"�������"
            //double = �v�Z����"���ϐ���"
            //double = �v�Z����"���ϑ��"
            //Date = ����.��������.get��n��()
            //TaxTypeEnum = ����.�O������.get�ŋ敪()
            log.exiting(STR_METHOD_NAME);
            return WEB3TPTransactionReflector.create(
                this.getCalcCondition(),
                l_feqOrderUnitRow.getProductType(),
                l_feqOrderUnitRow.getProductId(),
                l_tranType,
                l_tranDate,
                l_dblUnexecutedQuantity,
                l_dblUnexecutedAmount,
                l_dblExecutedQuantity,
                l_dblExecutedAmount,
                l_deliveryDate,
                l_feqOrderUnitRow.getTaxType());
        }
    }

    /**
     * ���̃I�u�W�F�N�g�̕�����\����Ԃ��B<BR>
     * 
     * @@return String
     */
    public String toString()
    {
        ToStringBuilder l_builder = ToStringUtils.newToStringBuilder(this);
        l_builder.appendSuper(super.toString());
        l_builder.append("orderProductId", this.getOrderProductId());

        return l_builder.toString();
    }
}@
