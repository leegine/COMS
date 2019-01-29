head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.12.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPAccountTransition.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ڋq���萄��(WEB3TPAccountTransition.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/05 nakazato(ACT) �V�K�쐬
Revesion History : 2007/10/22 �g�E�N�|�i���u�j�d�l�ύX���f��No.214�@@�v�Z����014
*/
package webbroker3.tradingpower.updtpower.settlement;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�ڋq���萄�ځj
 * 
 * �w����̌ڋq���萄�ڂ�\������N���X
 */
public class WEB3TPAccountTransition
{
    /*
     * ���O�o�̓��[�e�B���e�B�[
     */
    private static final WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPAccountTransition.class);

    /**
     * �i�w����j
     */
    private int specifiedDate;

    /**
     * �i�w����O������̌ڋq����c���j
     */
    private double prevDateBalance;

    /**
     * �i�w����̌ڋq����c���j
     */
    private double specifiedDateBalance;

    /**
     * �i�����v�f���v�j
     */
    private double totalReceiptAmount;

    /**
     * �i�o���v�f���v�j
     */
    private double totalPaymentAmount;

    /**
     * �i�������Ƃ̎�����j
     * 
     * �w����Ɏ�n���s�������̖������Ǝ�����I�u�W�F�N�g�̃��X�g
     */
    private List lisSettlementReflectors;

    /**
     * �i�ڋq���萄�ځj<BR>
     * 
     * �R���X�g���N�^�B<BR>
     */
    public WEB3TPAccountTransition()
    {
        //�t�B�[���h�̏�����
        this.specifiedDate = 0;
        this.prevDateBalance = 0.0;
        this.specifiedDateBalance = 0.0;
        this.totalReceiptAmount = 0.0;
        this.totalPaymentAmount = 0.0;
        this.lisSettlementReflectors = new ArrayList();
    }

    /**
     * �icalc���v��S�����j<BR>
     * <BR>
     * 0�␳�L��̓��v��S�������v�Z����B<BR>
     * <BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�]��_�������ρj.doc�v�Q�ƁB<BR>
     * @@return double
     * @@roseuid 40C4296C0172
     */
    public double calcDayTradeRestraint()
    {
        final String STR_METHOD_NAME = "calcDayTradeRestraint()";
        log.entering(STR_METHOD_NAME);

        //0�␳�����̓��v��S�������擾����B
        double l_dblDayTradeRestraint = this.calcDayTradeRestraintForCheck();

        //���v��S������0�␳����B
        l_dblDayTradeRestraint = Math.max(l_dblDayTradeRestraint, 0.0);

        //���v��S������ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_dblDayTradeRestraint;
    }

    /**
     * (calc���v��S�����`0�␳�����`)<BR>
     * <BR>
     * 0�␳�����̓��v��S�������v�Z����B<BR>
     * <BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�]��_�������ρj.doc�v�Q�ƁB<BR>
     * <BR>
     * @@return double
     */
    public double calcDayTradeRestraintForCheck()
    {
        final String STR_METHOD_NAME = "calcDayTradeRestraintForCheck()";
        log.entering(STR_METHOD_NAME);

        //�������Ϗ�񃊃X�g
        List l_lisSettlementReflectors = new ArrayList();
        //���̑��o���v�f
        double l_dblOtherPaymentAmount = this.totalPaymentAmount;

        //�������Ǝ�����̃o�b�t�@@�̈�
        WEB3TPSettlementReflector l_bufSettlementReflector = null;
        //�������Ǝ�����̃��X�g�̗v�f��
        int l_intSize = this.lisSettlementReflectors.size();

        //���Ԃ�0�`�h�v�f��-1�h�ŌJ��Ԃ�����
        for (int index = 0; index < l_intSize; index++)
        {
            //���X�g���A�������Ǝ�����(index)���擾����B
            l_bufSettlementReflector =
                (WEB3TPSettlementReflector)this.lisSettlementReflectors.get(index);

            //�������Ǝ�����(index)���������ϑΏۖ����̎�
            if (l_bufSettlementReflector.validateSettlementFundForDayTrade())
            {
                //�������Ϗ�񃊃X�g�ɖ������Ǝ�����(index)��ǉ�����B
                l_lisSettlementReflectors.add(l_bufSettlementReflector);

                //�������Ǝ�����(index).�������ϔ��t����j���擾����B
                double l_dblIndexSettlementBuyAmount =
                    l_bufSettlementReflector.calcSettlementBuyAmount();

                //���̑��o���v�f�i= ���̑��o���v�f - �������Ǝ�����(index).�������ϔ��t����j���v�Z����B
                l_dblOtherPaymentAmount = l_dblOtherPaymentAmount - l_dblIndexSettlementBuyAmount;
            }
        }

        //�������Ǝ�����̃��X�g�̗v�f���擾
        int l_intSizeArray = l_lisSettlementReflectors.size();

        //�������Ϗ�񃊃X�g��z��ɕϊ�����
        WEB3TPSettlementReflector l_settlementReflectors[] = new WEB3TPSettlementReflector[l_intSizeArray];

        //�z��Ƀ��X�g���i�[����
        for (int RoopCnt = 0; RoopCnt < l_intSizeArray; RoopCnt++)
        {
            l_settlementReflectors[RoopCnt] = (WEB3TPSettlementReflector)l_lisSettlementReflectors.get(RoopCnt);
        }

        //���Ϗ������胋�[���ɏ]���ă\�[�g����B
        this.calcSettlementOrder(l_settlementReflectors);

        //���v��S����
        double l_dblDayTradeRestraint = 0.0;
        //���v��S�����̃o�b�t�@@�̈�
        double l_dblBufDayTradeRestraint = 0.0;
        //�������Ϗ��̗v�f��
        int l_intLength = l_settlementReflectors.length;

        //���v��S�����ɏ����l���Z�b�g����B
        if (l_intLength == 0)
        {
            l_dblDayTradeRestraint = 0.0;
        }
        else
        {
            l_dblDayTradeRestraint = -Double.MAX_VALUE;
        }

        //���Ԃ��h�v�f��-1�h�`0�ŌJ��Ԃ�����
        for (int index = (l_intLength - 1); 0 <= index; index--)
        {
            //���Ԃ��h�v�f��-1�h�̎�
            if (index == (l_intLength - 1))
            {
                //�������ϔ��t���(index)���擾����B
                double l_dblIndexSettlementSellAmount =
                    l_settlementReflectors[index].calcSettlementSellAmount();

                //���v��S����(index)�i= �������ϔ��t���(index) - ���̑��o���v�f�j���v�Z����B
                l_dblBufDayTradeRestraint =
                    l_dblIndexSettlementSellAmount - l_dblOtherPaymentAmount;
            }
            //����ȊO
            else
            {
                //�������ϔ��t���(index)���擾����B
                double l_dblIndexSettlementSellAmount =
                    l_settlementReflectors[index].calcSettlementSellAmount();

                //�������ϔ��t���(index+1)���擾����B
                double l_dblIndexSettlementBuylAmount =
                    l_settlementReflectors[index + 1].calcSettlementBuyAmount();

                //���v��S����(index)�i= �������ϔ��t���(index) - �������ϔ��t���(index+1) + ���v��S����(index+1)�j���v�Z����B
                l_dblBufDayTradeRestraint =
                    l_dblIndexSettlementSellAmount
                        - l_dblIndexSettlementBuylAmount
                        + l_dblBufDayTradeRestraint;
            }

            //���v��S�����Ɠ��v��S����(index)�̑傫��������v��S�����Ƃ��č̗p����B
            l_dblDayTradeRestraint = Math.max(l_dblDayTradeRestraint, l_dblBufDayTradeRestraint);
        }

        //���v��S������ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_dblDayTradeRestraint;
    }

    /**
     * �icalc�������ϔ��t�\���ʁj<BR>
     * 
     * �������ϔ��t�\���ʂ��v�Z����B<BR>
     * <BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�]��_�������ρj.doc�v�Q�ƁB<BR>
     * @@param l_lngOrderFundId - �i��������ID�j
     * @@param l_dblOrderQuantity - �i�������ʁj
     * @@param l_dblLotSize - �i�����P�ʁj
     * @@param l_dblOrderExistQuantity - �i�������������ۗL����<�m��>�j
     * @@return WEB3TPSellOrderPossibleQuantityResult
     * @@roseuid 4100BB0700AB
     */
    public WEB3TPSellOrderPossibleQuantityResult calcSellOrderPossibleQuantity(
        long l_lngOrderFundId,
        double l_dblOrderQuantity,
        double l_dblLotSize,
        double l_dblOrderExistQuantity)
    {
        final String STR_METHOD_NAME = "calcSellOrderPossibleQuantity(long, double, double, double)";
        log.entering(STR_METHOD_NAME);

        //�����������
        WEB3TPSettlementReflector l_orderReflector = null;
        //�������Ϗ�񃊃X�g
        List l_lisSettlementReflectors = new ArrayList();
        //���̑������v�f
        double l_dblOtherReceiptAmount = this.totalReceiptAmount;
        //���t�\����
        double l_dblSellPossQuantity = 0.0;

        //�������Ǝ�����̃o�b�t�@@�̈�
        WEB3TPSettlementReflector l_bufSettlementReflector = null;
        //�������Ǝ�����̃��X�g�̗v�f��
        int l_intSize = this.lisSettlementReflectors.size();

        //���Ԃ�0�`�h�v�f��-1�h�ŌJ��Ԃ�����
        for (int index = 0; index < l_intSize; index++)
        {
            //�������Ǝ�����(index)���擾����B
            l_bufSettlementReflector =
                (WEB3TPSettlementReflector)this.lisSettlementReflectors.get(index);

            //�p�����[�^.����ID�Ɩ������Ǝ�����(index).����ID����������
            if (l_lngOrderFundId == l_bufSettlementReflector.getFundId())
            {
                //�����������ɖ������Ǝ�����(index)��������B
                l_orderReflector = l_bufSettlementReflector;
                //���[�v���甲����B
                index = l_intSize;
            }
        }

        //�������������擾�ł�����
        if (l_orderReflector != null)
        {
            //�����������.�w����̔��t���ʁi�������� + ���t���� + ����蔄�t���ʁj���v�Z����B
            double l_dblSellQuantity =
                l_dblOrderQuantity
                    + l_orderReflector.getSellQuantity()
                    + l_orderReflector.getUnexecutedSellQuantity();

            //�����������.�w����̔��t���ʁiBigDecimal�^�j                   
            BigDecimal l_bdSellQuantity = new BigDecimal(l_dblSellQuantity);
            //�����������.���t���ʁiBigDecimal�^�j
            BigDecimal l_bdBuyQuantity = new BigDecimal(l_orderReflector.getBuyQuantity());
            //�����������.�w����O���ۗL���ʁiBigDecimal�^�j
            BigDecimal l_bdExistQuantity = new BigDecimal(l_orderReflector.getExistQuantity());
            //this.�w����̌ڋq����c���iBigDecimal�^�j
            BigDecimal l_bdSpecifiedDateBalance = new BigDecimal(this.specifiedDateBalance);

            //�����������.���t���ʂ�0�A�܂��́A
            //this.�w����̌ڋq����c����0�ȏォ�A
            //�����������.�w����O���ۗL���ʂ������������.�w����̔��t���ʈȏ�A�܂���
            //this.�w��� = T+5 �̎��i�������ϑΏۊO�j
            if (l_bdBuyQuantity.compareTo(new BigDecimal(0.0)) == 0
                || (l_bdSpecifiedDateBalance.compareTo(new BigDecimal(0.0)) >= 0
                    && l_bdExistQuantity.compareTo(l_bdSellQuantity) >= 0)
                || this.specifiedDate == WEB3TPSpecifiedPointDef.T_5)
            {
                //���t�\���ʁi�w����O���ۗL���� + ���t���ʁ@@- ���t���ʁ@@- ����蔄�t���ʁj���v�Z����B
                l_dblSellPossQuantity =
                    l_orderReflector.getExistQuantity()
                        + l_orderReflector.getBuyQuantity()
                        - l_orderReflector.getSellQuantity()
                        - l_orderReflector.getUnexecutedSellQuantity();

                /*
                 * �������ϔ��t�\���ʌ��ʃI�u�W�F�N�g�֒l���Z�b�g
                 */
                WEB3TPSellOrderPossibleQuantityResult l_result = new WEB3TPSellOrderPossibleQuantityResult();
                l_result.dayTradeFundFlg = false;
                l_result.sellPossQuantity = Math.max(l_dblSellPossQuantity, 0.0);
                l_result.lackAmt = 0.0;

                //�������ϔ��t�\���ʃI�u�W�F�N�g��ԋp����
                log.exiting(STR_METHOD_NAME);
                return l_result;

            }
            //����ȊO�i�������ϑΏہj
            else
            {
                //�������������������Ϗ�񃊃X�g�ɒǉ�����
                l_lisSettlementReflectors.add(l_orderReflector);

                //�����������.�������ϔ��t������擾����B
                double l_dblOrderSettlementSellAmount = l_orderReflector.calcSettlementSellAmount();

                //���̑������v�f�i= ���̑������v�f - �����������.�������ϔ��t����j���v�Z����
                l_dblOtherReceiptAmount = l_dblOtherReceiptAmount - l_dblOrderSettlementSellAmount;
            }
        }
        //�������������擾�ł��Ȃ�������
        else
        {
            //���t�\���ʁi= �p�����[�^.�������������ۗL����<�m��>�j��ԋp����
            l_dblSellPossQuantity = l_dblOrderExistQuantity;

            /*
             * �������ϔ��t�\���ʌ��ʃI�u�W�F�N�g�֒l���Z�b�g
             */
            WEB3TPSellOrderPossibleQuantityResult l_result = new WEB3TPSellOrderPossibleQuantityResult();
            l_result.dayTradeFundFlg = false;
            l_result.sellPossQuantity = Math.max(l_dblSellPossQuantity, 0.0);
            l_result.lackAmt = 0.0;

            //�������ϔ��t�\���ʃI�u�W�F�N�g��ԋp����
            log.exiting(STR_METHOD_NAME);
            return l_result;
        }

        //���Ԃ�0�`�h�v�f��-1�h�ŌJ��Ԃ�����
        for (int index = 0; index < l_intSize; index++)
        {
            //�������Ǝ�����(index)���擾����B
            l_bufSettlementReflector =
                (WEB3TPSettlementReflector)this.lisSettlementReflectors.get(index);

            //�������Ǝ�����(index)���A�������ϑΏۖ����ł���A�����������łȂ���
            if (l_bufSettlementReflector.validateSettlementFundForDayTrade() == true
                && l_bufSettlementReflector.getFundId() != l_lngOrderFundId)
            {
                //�������Ϗ�񃊃X�g�ɖ������Ǝ�����(index)��ǉ�����
                l_lisSettlementReflectors.add(l_bufSettlementReflector);

                //�������Ǝ�����(index).�������ϔ��t������擾����B
                double l_dblIndexSettlementSellAmount =
                    l_bufSettlementReflector.calcSettlementSellAmount();

                //���̑������v�f�i= ���̑������v�f - �������Ǝ�����(index).�������ϔ��t����j���v�Z����B
                l_dblOtherReceiptAmount = l_dblOtherReceiptAmount - l_dblIndexSettlementSellAmount;
            }
        }

        //�������Ϗ�񃊃X�g�̗v�f���̎擾
        int l_intSizeArray = l_lisSettlementReflectors.size();

        //�������Ϗ�񃊃X�g��z��ɕϊ�����
        WEB3TPSettlementReflector l_settlementReflectors[] = new WEB3TPSettlementReflector[l_intSizeArray];

        //�z��Ƀ��X�g���i�[����
        for (int RoopCnt = 0; RoopCnt < l_intSizeArray; RoopCnt++)
        {
            l_settlementReflectors[RoopCnt] = (WEB3TPSettlementReflector)l_lisSettlementReflectors.get(RoopCnt);
        }

        //���Ϗ������胋�[���ɏ]���ă\�[�g����B
        this.calcSettlementOrder(l_settlementReflectors);
        //���񔄕t������̒��������̌��Ϗ��ʂ�\�������Ϗ�������ёւ���B���������̌��Ϗ��ʂ��擾����B
        int l_intOrder =
            this.calcSettlementOrderForOrder(
                l_settlementReflectors,
                l_lngOrderFundId,
                l_dblOtherReceiptAmount);

        //�������ϔ��t�\�z
        double l_dblSettlementPower = Double.MAX_VALUE;
        //�o���\�z
        double l_dblPaymentPower = Double.MAX_VALUE;
        
        //�o���\�z�̃o�b�t�@@�̈�
        double l_dblBufPaymentPower = 0.0;
        //�������Ϗ��̗v�f��
        int l_intLength = l_settlementReflectors.length;

        //���Ԃ�0�`�h�v�f��-1�h�ŌJ��Ԃ�����
        for (int index = 0; index < l_intLength; index++)
        {
            //���Ԃ�0�̎�
            if (index == 0)
            {
                //�������ϔ��t���(index)���擾����B
                double l_dblIndexSettlementBuyAmount =
                    l_settlementReflectors[index].calcSettlementBuyAmount();

                //�o���\�z(index)�i= �w����O������̌ڋq����c���@@+ ���̑������v�f���v - �������ϔ��t���(index)�j���v�Z����B
                l_dblBufPaymentPower =
                    this.prevDateBalance + l_dblOtherReceiptAmount - l_dblIndexSettlementBuyAmount;
            }
            //�ȊO�̎�
            else
            {
                //�������ϔ��t���(index-1)���擾����B
                double l_dblIndexSettlementSellAmount =
                    l_settlementReflectors[index - 1].calcSettlementSellAmount();
                //�������ϔ��t���(index)���擾����B
                double l_dblIndexSettlementBuyAmount =
                    l_settlementReflectors[index].calcSettlementBuyAmount();

                //�o���\�z(index)�i= �o���\�z(index-1) + �������ϔ��t���(index-1) - �������ϔ��t���(index)�j���v�Z����B
                l_dblBufPaymentPower =
                    l_dblBufPaymentPower
                        + l_dblIndexSettlementSellAmount
                        - l_dblIndexSettlementBuyAmount;
            }

            //�o���\�z�Əo���\�z(index)�̏����������o���\�z�Ƃ���
            l_dblPaymentPower = Math.min(l_dblPaymentPower, l_dblBufPaymentPower);

            //���Ԃ��u���������̌��Ϗ��ʁv�ȏ�̎�
            if (l_intOrder <= index)
            {
                //�������ϔ��t�\�z�Əo���\�z(index)�̏����������������ϔ��t�\�z�Ƃ���B
                l_dblSettlementPower = Math.min(l_dblSettlementPower, l_dblBufPaymentPower);
            }

        }

        //���v��S�������擾����B
        double l_dblDayTradeRestraint = this.calcDayTradeRestraint();

        //���񒍕��ȑO�̏o���\�z�i�w����̌ڋq����c�� - ���v��S�����j���v�Z����B
        double l_dblPrevPaymentPower = this.specifiedDateBalance - l_dblDayTradeRestraint;

        //�o���\�z(BigDecimal�^)
        BigDecimal l_bdPaymentPower = new BigDecimal(l_dblPaymentPower);
        //���񒍕��ȑO�̏o���\�z(BigDecimal�^)
        BigDecimal l_bdPrevPaymentPower = new BigDecimal(l_dblPrevPaymentPower);

        //�o���\�z��0�ȏ�A�܂��́A�o���\�z�����񒍕��ȑO�̏o���\�z�ȏ�̎�
        if (l_bdPaymentPower.compareTo(new BigDecimal(0.0)) >= 0
            || l_bdPaymentPower.compareTo(l_bdPrevPaymentPower) >= 0)
        {
            //�����������.�������ϔ��t������擾����
            double l_dblSettlementBuyAmount = l_orderReflector.calcSettlementBuyAmount();
            //���������̔��t�\�z�i=�������ϔ��t�\�z + �����������.�������ϔ��t����j
            double l_dblSellPossPower = l_dblSettlementPower + l_dblSettlementBuyAmount;

            //���������̔��t�\�z(BigDecimal�^)
            BigDecimal l_bdSellPossPower = new BigDecimal(l_dblSellPossPower);
            //�����������.���t���(BigDecimal�^)
            BigDecimal l_bdBuyAmount = new BigDecimal(l_orderReflector.getBuyAmount());
            //�����������.���t����(BigDecimal�^)
            BigDecimal l_bdBuyQuantity = new BigDecimal(l_orderReflector.getBuyQuantity());

            //���������̒P���i=�����������.���t��� / �����������.���t���ʁj
            BigDecimal l_bdUnitPrice =
                l_bdBuyAmount.divide(l_bdBuyQuantity, 10, BigDecimal.ROUND_HALF_EVEN);

            //�������ϔ��t�\���ʁi=���������̔��t�\�z / ���������̒P���j(�����_�ȉ��؎̂�)
            BigDecimal l_bdSettlementSellPossQuantity =
                l_bdSellPossPower.divide(l_bdUnitPrice, 0, BigDecimal.ROUND_FLOOR);

            //�������ϔ��t�\����
            double l_dblSettlementSellPossQuantity = l_bdSettlementSellPossQuantity.doubleValue();

            //�������ϔ��t�\���ʂƒ����������.���t���ʂ̏����������������ϔ��t�\���ʂƂ���  
            l_dblSettlementSellPossQuantity =
                Math.min(l_dblSettlementSellPossQuantity, l_orderReflector.getBuyQuantity());

            //���t�\����(=�������ϔ��t�\���� + �w����O���ۗL���� - ���t���� - ����蔄�t����)���v�Z����B
            l_dblSellPossQuantity =
                l_dblSettlementSellPossQuantity
                    + l_orderReflector.getExistQuantity()
                    - l_orderReflector.getSellQuantity()
                    - l_orderReflector.getUnexecutedSellQuantity();

            //���t�\����(BigDecimal�^)
            BigDecimal l_bdSellPossQuantity = new BigDecimal(l_dblSellPossQuantity);
            //�p�����[�^.�����P��(BigDecimal�^)
            BigDecimal l_bdLotSize = new BigDecimal(l_dblLotSize);

            //�����P�ʊ����ʁi=���t�\���� / �p�����[�^.�����P�ʁj(�����_�ȉ��؎̂�)
            BigDecimal l_bdOrderUnitQuantity =
                l_bdSellPossQuantity.divide(l_bdLotSize, 0, BigDecimal.ROUND_FLOOR);

            //���t�\����(�����P�ʊ����� * �p�����[�^.�����P��)
            l_bdSellPossQuantity = l_bdOrderUnitQuantity.multiply(l_bdLotSize);
            l_dblSellPossQuantity = l_bdSellPossQuantity.doubleValue();

        }
        //�ȊO�̎�
        else
        {
            //���t�\����(�w����O���ۗL���� - ���t���� - ����蔄�t����)���v�Z����
            l_dblSellPossQuantity =
                l_orderReflector.getExistQuantity()
                    - l_orderReflector.getSellQuantity()
                    - l_orderReflector.getUnexecutedSellQuantity();
        }

        /*
         * �a����s���z���v�Z����B
         * 
         * �����v�莑�Y��ʂ��R�[�����ꂽ�i����.��������==Double.MAX_VALUE�j�ꍇ
         * �@@�a����s���z =0 
         */
        double l_dblLackAmt;

        //����.��������==Double.MAX_VALUE�̏ꍇ
        if(l_dblOrderQuantity == Double.MAX_VALUE)
        {
            //�a����s���z = 0
            l_dblLackAmt = 0.0;
        }
        //�ȊO�̏ꍇ
        else
        {
	        //���t�\���� < ����.�������� �̏ꍇ
	        if(l_dblSellPossQuantity < l_dblOrderQuantity)
	        {
	            /*
	             * �a����s���z = 
	             *  TRUNC(
	             * �@@�@@(����.�������� + ���t���� + ����蔄�t���� ? �w����O���ۗL����) 
	             *   �@@�~ (���t��� / ���t����)
	             *�@@) 
	             *  �| �������ϔ��t�\�z - �������ϔ��t���
	             */
	            //���z���� = ����.�������� + ���t���� + ����蔄�t���� ? �w����O���ۗL����
	            double l_dblSellOver = l_dblOrderQuantity
	                    + l_orderReflector.getSellQuantity()
	                    + l_orderReflector.getUnexecutedSellQuantity()
	                    - l_orderReflector.getExistQuantity();
	
	            //�P�� = ���t��� / ���t����
	            double l_dblUnitPrice = l_orderReflector.getBuyAmount()
	                    / l_orderReflector.getBuyQuantity();
	
	            //�a����s���z = TRUNC(���z���� �~ �P��) - �������ϔ��t�\�z - �������ϔ��t���
	            l_dblLackAmt = Math.floor(l_dblSellOver * l_dblUnitPrice)
	                    - l_dblSettlementPower
	                    - l_orderReflector.calcSettlementBuyAmount();
	        }
	        //�ȊO�i���t�\���� >= ����.�������ʁj�̏ꍇ
	        else
	        {
	            //�a����s���z = 0
	            l_dblLackAmt = 0.0;
	        }
        }

        /*
         * �������ϔ��t�\���ʌ��ʃI�u�W�F�N�g�֒l���Z�b�g
         */
        WEB3TPSellOrderPossibleQuantityResult l_result = new WEB3TPSellOrderPossibleQuantityResult();
        l_result.dayTradeFundFlg = true;
        l_result.sellPossQuantity = Math.max(l_dblSellPossQuantity, 0.0);
        l_result.lackAmt = l_dblLackAmt;

        //�������ϔ��t�\���ʃI�u�W�F�N�g��ԋp����
        log.exiting(STR_METHOD_NAME);
        return l_result;
    }

    /**
     * �icalc�������ϔ��t�\�z�j<BR>
     * <BR>
     * �������ϔ��t�\�z���v�Z����B<BR>
     * <BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�]��_�������ρj.doc�v�Q�ƁB<BR>
     * <BR>
     * @@param l_lngOrderFundId - �i��������ID�j
     * @@return double
     */
    public double calcBuyOrderPossibleAmount(long l_lngOrderFundId)
    {
        final String STR_METHOD_NAME = "calcBuyOrderPossibleAmount(long)";
        log.entering(STR_METHOD_NAME);

        /*
         * �������Ƃ̎����񃊃X�g���A����.��������ID�Ɠ������̖������Ǝ�����I�u�W�F�N�g���擾
         */
        //(��������)�������Ǝ�����
        WEB3TPSettlementReflector l_orderReflector = null;

        //�������Ǝ�����X�g�̗v�f����LOOP����            
        for (Iterator l_iter = this.lisSettlementReflectors.iterator(); l_iter.hasNext();)
        {
            //�������Ǝ�����(index)���擾
            WEB3TPSettlementReflector l_element = (WEB3TPSettlementReflector)l_iter.next();

            //����.�������� == �������Ǝ�����(index).����ID�̏ꍇ
            if (l_lngOrderFundId == l_element.getFundId())
            {
                //(��������)�������Ǝ�����ɁA�������Ǝ�����I�u�W�F�N�g(index)����
                l_orderReflector = l_element;
                //���[�v��蔲����
                break;
            }
        }

        /*
         * �������Ϗ�񃊃X�g���쐬����B
         */
        //�������Ϗ�񃊃X�g
        List l_lisSettlementReflectors = new ArrayList();
        //���̑������v�f
        double l_dblOtherReceiptAmount = this.totalReceiptAmount;
        //���v������t���O(true:���v�����/false:����v�����)
        boolean l_blnDayTrade;
        
        //����v������̏ꍇ
        //(��������)�������Ǝ����� == null �܂��́A
        //(���t���� + ����蔄�t����) <= �w����O���ۗL���� �܂��́A
        //this.�w��� = T+5
        if (l_orderReflector == null
            || l_orderReflector.getSellQuantity() + l_orderReflector.getUnexecutedSellQuantity()
                <= l_orderReflector.getExistQuantity()
            || this.specifiedDate == WEB3TPSpecifiedPointDef.T_5)
        {
            //���v������t���O = false
            l_blnDayTrade = false;
        }
        //�ȊO�i���v������j�̏ꍇ
        else
        {
            //���v������t���O = true
            l_blnDayTrade = true;
            //�������Ϗ�񃊃X�g�ɁA(��������)�������Ǝ������ǉ�
            l_lisSettlementReflectors.add(l_orderReflector);
            //���̑������v�f�i= ���̑������v�f - (��������)�������Ǝ�����.�������ϔ��t����j���v�Z����
            l_dblOtherReceiptAmount = l_dblOtherReceiptAmount
                - l_orderReflector.calcSettlementSellAmount();
        }

        //�������Ǝ�����X�g�̗v�f����LOOP����            
        for (Iterator l_iter = this.lisSettlementReflectors.iterator(); l_iter.hasNext();)
        {
            //�������Ǝ�����(index)���擾
            WEB3TPSettlementReflector l_element = (WEB3TPSettlementReflector)l_iter.next();

            //�������Ǝ�����(index)���A�������ϑΏۖ����ł���A�����������łȂ���
            if (l_element.validateSettlementFundForDayTrade() == true
                && l_element.getFundId() != l_lngOrderFundId)
            {
                //�������Ϗ�񃊃X�g�ɖ������Ǝ�����(index)��ǉ�����B
                l_lisSettlementReflectors.add(l_element);
                //���̑������v�f�i= ���̑������v�f - �������Ǝ�����(index).�������ϔ��t����j���v�Z����
                l_dblOtherReceiptAmount =
                    l_dblOtherReceiptAmount - l_element.calcSettlementSellAmount();
            }
        }

        /*
         * �쐬���ꂽ�������Ϗ�񃊃X�g��z��ɕϊ����āA���Ϗ������胋�[���ɏ]���ă\�[�g
         */
        //�������Ϗ��̔z��ɕϊ�
        WEB3TPSettlementReflector l_settlementReflectors[] =
            new WEB3TPSettlementReflector[l_lisSettlementReflectors.size()];
        l_settlementReflectors =
            (WEB3TPSettlementReflector[])l_lisSettlementReflectors.toArray(l_settlementReflectors);

        //���Ϗ������胋�[���ɏ]���ă\�[�g
        this.calcSettlementOrder(l_settlementReflectors);

        /*
         * �������������v������̏ꍇ�A������̌��Ϗ��ʂ�\�������ёւ��A���������̌��Ϗ��ʂ��擾����B
         */
        //���������̌��Ϗ���
        int l_intOrder = Integer.MAX_VALUE;

        //�������������v������̏ꍇ
        if(l_blnDayTrade == true)
        {
            l_intOrder =
                this.calcSettlementOrderForOrder(
                    l_settlementReflectors,
                    l_lngOrderFundId,
                    l_dblOtherReceiptAmount);
        }

        /*
         * �������ϖ������t�\�z���v�Z����B
         */
        //�������ϔ��t�\�z
        double l_dblSettlementPower = Double.MAX_VALUE;
        //�o���\�z
        double l_dblPaymentPower = Double.MAX_VALUE;
        //�o���\�z(n)
        double l_dblBufPaymentPower = 0.0;

        //���Ԃ�0�`�h�v�f��-1�h�ŌJ��Ԃ�����
        for (int index = 0; index < l_settlementReflectors.length; index++)
        {
            //���Ԃ�0�̎�
            if (index == 0)
            {
                //�������ϔ��t���(index)���擾����B
                double l_dblIndexSettlementBuyAmount =
                    l_settlementReflectors[index].calcSettlementBuyAmount();

                //�o���\�z(index)�i= �w����O������̌ڋq����c���@@+ ���̑������v�f���v - �������ϔ��t���(index)�j���v�Z����B
                l_dblBufPaymentPower =
                    this.prevDateBalance + l_dblOtherReceiptAmount - l_dblIndexSettlementBuyAmount;
            }
            //�ȊO�̎�
            else
            {
                //�������ϔ��t���(index-1)���擾����B
                double l_dblIndexSettlementSellAmount =
                    l_settlementReflectors[index - 1].calcSettlementSellAmount();
                //�������ϔ��t���(index)���擾����B
                double l_dblIndexSettlementBuyAmount =
                    l_settlementReflectors[index].calcSettlementBuyAmount();

                //�o���\�z(index)�i= �o���\�z(index-1) + �������ϔ��t���(index-1) - �������ϔ��t���(index)�j���v�Z����B
                l_dblBufPaymentPower =
                    l_dblBufPaymentPower
                        + l_dblIndexSettlementSellAmount
                        - l_dblIndexSettlementBuyAmount;
            }

            //�o���\�z�̍ŏ��l���v�Z����B
            l_dblPaymentPower = Math.min(l_dblPaymentPower, l_dblBufPaymentPower);
            
            //���Ԃ��u���������̌��Ϗ��ʁv�ȏ�̎�
            if (l_intOrder <= index)
            {
                //�������ϔ��t�\�z�Əo���\�z(index)�̏����������������ϔ��t�\�z�Ƃ���B
                l_dblSettlementPower = Math.min(l_dblSettlementPower, l_dblBufPaymentPower);
            }
        }

        /*
         * �������ϔ��t�\�z���v�Z����B
         */
        //���v������̏ꍇ
        if(l_blnDayTrade == true)
        {
            /*
             * (�������ϔ��t�\�z
             * �@@+MAX(���������̍������ϑ����O���t������v - ���������̍������ϑ����O���t������v, 0)
             * ��
             * �w����̌ڋq����c��
             * �Ƃ̏����������������ϔ��t�\�z�Ƃ���B
             */
            l_dblSettlementPower =
                l_dblSettlementPower
                    + Math.max(
                        l_orderReflector.calcExceptSettlementSellAmount()
                            - l_orderReflector.calcExceptSettlementBuyAmount(),
                        0);
            l_dblSettlementPower = Math.min(l_dblSettlementPower, this.specifiedDateBalance);

            //�������ϔ��t�\�z��0�␳����
            l_dblSettlementPower = Math.max(l_dblSettlementPower, 0.0);
        }
        //����v������̏ꍇ
        else
        {
            //�������ϔ��t�\�z = -1
            l_dblSettlementPower = -1;
        }

        /*
         * �o���\�z�`�F�b�N
         */
        //�o���\�z < 0 �̏ꍇ
        if(l_dblPaymentPower < 0)
        {
            //�������ϔ��t�\�z = 0
            l_dblSettlementPower = 0;
        }

        log.exiting(STR_METHOD_NAME);
        //�v�Z�����������ϔ��t�\�z��ԋp����
        return l_dblSettlementPower;
    }

    /**
     * �iget�w����j<BR>
     * 
     * this.�w�����ԋp����B<BR>
     * @@return int
     * @@roseuid 410601E50223
     */
    public int getSpecifiedDate()
    {
        return this.specifiedDate;
    }

    /**
     * �iset�w����j<BR>
     * 
     * �p�����[�^.�w�����this.�w����ɃZ�b�g����B<BR>
     * @@param l_datSpecifiedDate - �i�w����j
     * @@roseuid 410601EE00EB
     */
    public void setSpecifiedDate(int l_intSpecifiedDate)
    {
        this.specifiedDate = l_intSpecifiedDate;
    }

    /**
     * �iget�w����O������̌ڋq����c���j<BR>
     * 
     * this.�w����O������̌ڋq����c����ԋp����B<BR>
     * @@return double
     * @@roseuid 40F744880352
     */
    public double getPrevDateBalance()
    {
        return this.prevDateBalance;
    }

    /**
    * �iset�w����O������̌ڋq����c���j<BR>
    * 
    * �p�����[�^.�w����O������̌ڋq����c����this.�w����O������̌ڋq����c���ɃZ�b�g����B
    * <BR>
    * @@param l_dblPrevDateBalance - �i�w����O������̌ڋq����c���j
    * @@roseuid 40F744940372
    */
    public void setPrevDateBalance(double l_dblPrevDateBalance)
    {
        this.prevDateBalance = l_dblPrevDateBalance;
    }

    /**
     * �iget�w����̌ڋq����c���j<BR>
     *
     * this.�w����̌ڋq����c����ԋp����B<BR>
     * @@return double
     * @@roseuid 4105FF8D00AC
     */
    public double getSpecifiedDateBalance()
    {
        return this.specifiedDateBalance;
    }

    /**
     * �iset�w����̌ڋq����c���j<BR>
     * 
     * �p�����[�^.�w����̌ڋq����c����this.�w����̌ڋq����c���ɃZ�b�g����B<BR>
     * @@param l_dblSpecifiedDateBalance - �i�w����̌ڋq����c���j
     * @@roseuid 4105FF990158
     */
    public void setSpecifiedDateBalance(double l_dblSpecifiedDateBalance)
    {
        this.specifiedDateBalance = l_dblSpecifiedDateBalance;
    }

    /**
     * �iget�����v�f���v�j<BR>
     * 
     * this.�����v�f���v��ԋp����B<BR>
     * @@return double
     * @@roseuid 40F744AF020A
     */
    public double getTotalReceiptAmount()
    {
        return this.totalReceiptAmount;
    }

    /**
     * �iset�����v�f���v�j<BR>
     * 
     * �p�����[�^.�����v�f���v��this.�����v�f���v�ɃZ�b�g����B<BR>
     * @@param l_dblTotalReceiptAmount - �i�����v�f���v�j
     * @@roseuid 40F744B70268
     */
    public void setTotalReceiptAmount(double l_dblTotalReceiptAmount)
    {
        this.totalReceiptAmount = l_dblTotalReceiptAmount;
    }

    /**
     * �iget�o���v�f���v�j<BR>
     * 
     * this.�o���v�f���v��ԋp����B<BR>
     * @@return double
     * @@roseuid 40C42B0B00A7
     */
    public double getTotalPaymentAmount()
    {
        return this.totalPaymentAmount;
    }

    /**
     * �iset�o���v�f���v�j<BR>
     * 
     * �p�����[�^.�o���v�f���v��this.�o���v�f���v�ɃZ�b�g����B<BR>
     * @@param l_dblTotalPaymentAmount - �i�o���v�f���v�j
     * @@roseuid 40C42B7003E3
     */
    public void setTotalPaymentAmount(double l_dblTotalPaymentAmount)
    {
        this.totalPaymentAmount = l_dblTotalPaymentAmount;
    }

    /**
     * �iget�������Ǝ�����j<BR>
     * 
     * this.�������Ǝ������ԋp����B<BR>
     * @@return List
     * @@roseuid 40C42B160078
     */
    public List getLisSettlementReflectors()
    {
        return this.lisSettlementReflectors;
    }

    /**
     * �iset�������Ǝ�����j<BR>
     * 
     * �p�����[�^.�������Ǝ������this.�������Ǝ�����ɃZ�b�g����B<BR>
     * @@param l_lisSettlementReflectors - �i�������Ƃ̎�����j
     * @@roseuid 40C42B810191
     */
    public void setLisSettlementReflectors(List l_lisSettlementReflectors)
    {
        this.lisSettlementReflectors = l_lisSettlementReflectors;
    }

    /**
     * �icalc���Ϗ����j<BR>
     * 
     * ���Ϗ��������肷��B<BR>
     * <BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�]��_�������ρj.doc�v�Q�ƁB<BR>
     * @@param l_settlementReflectors - �i�������ϑΏۂƂȂ�������Ǝ�����̔z��j
     * @@roseuid 40BC6DCC028F
     */
    protected void calcSettlementOrder(WEB3TPSettlementReflector[] l_settlementReflectors)
    {
        //�������Ϗ��̔z��̗v�f�����擾����
        int l_intLength = l_settlementReflectors.length;
        //���Ϗ��ʓ���ւ��t���O
        boolean l_changeFlag = false;

        //���Ԃ�0�`�h�v�f��-1�h�ŌJ��Ԃ�����
        for (int index1 = 0; index1 < l_intLength - 1; index1++)
        {
            for (int index2 = 0; index2 < l_intLength - 1; index2++)
            {

                //�������ϔ������z(index2)
                BigDecimal l_bdCurSettlementDiff =
                    new BigDecimal(l_settlementReflectors[index2].calcSettlementDiff());
                //�������ϔ��t���(index2)
                BigDecimal l_bdCurSettlementBuyAmount =
                    new BigDecimal(l_settlementReflectors[index2].calcSettlementBuyAmount());
                //�������ϔ��t���(index2)
                BigDecimal l_bdCurSettlementSellAmount =
                    new BigDecimal(l_settlementReflectors[index2].calcSettlementSellAmount());

                //�������ϔ������z(index2+1)
                BigDecimal l_bdNextSettlementDiff =
                    new BigDecimal(l_settlementReflectors[index2 + 1].calcSettlementDiff());
                //�������ϔ��t���(index2+1)
                BigDecimal l_bdNextSettlementBuyAmount =
                    new BigDecimal(l_settlementReflectors[index2 + 1].calcSettlementBuyAmount());
                //�������ϔ��t���(index2+1)
                BigDecimal l_bdNextSettlementSellAmount =
                    new BigDecimal(l_settlementReflectors[index2 + 1].calcSettlementSellAmount());

                //�������ϔ������z(index2)���v���X�̎�
                if (l_bdCurSettlementDiff.compareTo(new BigDecimal(0.0)) >= 0)
                {
                    //�������ϔ������z(index2+1)���v���X�̎�
                    if (l_bdNextSettlementDiff.compareTo(new BigDecimal(0.0)) >= 0)
                    {
                        //�������ϔ��t���(index2)���������ϔ��t���(index2+1)���傫����
                        if (l_bdCurSettlementBuyAmount.compareTo(l_bdNextSettlementBuyAmount) > 0)
                        {
                            //���Ϗ��ʓ���ւ��t���O��TRUE���Z�b�g����B
                            l_changeFlag = true;
                        }
                        //�������ϔ��t���(index2)���������ϔ��t���(index2+1)�Ɠ�������
                        else if (
                            l_bdCurSettlementBuyAmount.compareTo(l_bdNextSettlementBuyAmount) == 0)
                        {
                            //�������ϔ��t���(index2)���������ϔ��t���(index2+1)��菬������
                            if (l_bdCurSettlementSellAmount.compareTo(l_bdNextSettlementSellAmount)
                                < 0)
                            {
                                //���Ϗ��ʓ���ւ��t���O��TRUE���Z�b�g����B
                                l_changeFlag = true;
                            }
                            //�������ϔ��t���(index2)���������ϔ��t���(index2+1)�ȏ�̎�
                            else
                            {
                                //���Ϗ��ʓ���ւ��t���O��FALSE���Z�b�g����B
                                l_changeFlag = false;
                            }
                        }
                        //�������ϔ��t���(index2)���������ϔ��t���(index2+1)��菬������
                        else
                        {
                            //���Ϗ��ʓ���ւ��t���O��FALSE���Z�b�g����B
                            l_changeFlag = false;
                        }
                    }
                    //�������ϔ������z(index2+1)���}�C�i�X�̎�
                    else
                    {
                        //���Ϗ��ʓ���ւ��t���O��FALSE���Z�b�g����B
                        l_changeFlag = false;
                    }
                }
                //�������ϔ������z(index2)���}�C�i�X�̎�
                else
                {
                    //�������ϔ������z(index2+1)���v���X�̎�
                    if (l_bdNextSettlementDiff.compareTo(new BigDecimal(0.0)) >= 0)
                    {
                        //���Ϗ��ʓ���ւ��t���O��TRUE���Z�b�g����B
                        l_changeFlag = true;
                    }
                    //�������ϔ������z(index2+1)���}�C�i�X�̎�
                    else
                    {
                        //�������ϔ��t���(index2)���������ϔ��t���(index2+1)��菬������
                        if (l_bdCurSettlementSellAmount.compareTo(l_bdNextSettlementSellAmount)
                            < 0)
                        {
                            //���Ϗ��ʓ���ւ��t���O��TRUE���Z�b�g����B
                            l_changeFlag = true;
                        }
                        //�������ϔ��t���(index2)���������ϔ��t���(index2+1)�Ɠ�������
                        else if (
                            l_bdCurSettlementSellAmount.compareTo(l_bdNextSettlementSellAmount)
                                == 0)
                        {
                            //�������ϔ��t���(index2)���������ϔ��t���(index2+1)���傫����
                            if (l_bdCurSettlementBuyAmount.compareTo(l_bdNextSettlementBuyAmount)
                                > 0)
                            {
                                //���Ϗ��ʓ���ւ��t���O��TRUE���Z�b�g����B
                                l_changeFlag = true;
                            }
                            //�������ϔ��t���(index2)���������ϔ��t���(index2+1)�ȉ��̎�
                            else
                            {
                                //���Ϗ��ʓ���ւ��t���O��FALSE���Z�b�g����B
                                l_changeFlag = false;
                            }
                        }
                        //�������ϔ��t���(index2)���������ϔ��t���(index2+1)���傫����
                        else
                        {
                            //���Ϗ��ʓ���ւ��t���O��FALSE���Z�b�g����B
                            l_changeFlag = false;
                        }
                    }
                }

                //����ւ��t���O��TRUE�̎�
                if (l_changeFlag == true)
                {
                    //�������Ϗ��̔z��̏��ʂ�O�����ւ���B
                    WEB3TPSettlementReflector l_bufSettlementReflector =
                        l_settlementReflectors[index2];
                    l_settlementReflectors[index2] = l_settlementReflectors[index2 + 1];
                    l_settlementReflectors[index2 + 1] = l_bufSettlementReflector;
                }
            }
        }
    }

    /**
     * �icalc���Ϗ���<��������>�j<BR>
     * <BR>
     * ������̒��������̌��Ϗ��ʂ�\�����A���Ϗ�������ёւ���<BR>
     * <BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�]��_�������ρj.doc�v�Q�ƁB<BR>
     * @@param l_settlementReflectors - �i�������ϑΏۂƂȂ�������Ǝ�����j
     * @@param l_lngOrderFundId - �i��������ID�j
     * @@param l_dblOtherReceiptAmount - �i���̑������v�f�j
     * @@return int
     * @@roseuid 4104A81A028E
     */
    protected int calcSettlementOrderForOrder(
        WEB3TPSettlementReflector[] l_settlementReflectors,
        long l_lngOrderFundId,
        double l_dblOtherReceiptAmount)
    {
        //���t���_�c���ɏ����l�i= this.�w����O������̌ڋq����c�� + �p�����[�^.���̑������v�f�j��������B
        double l_dblCurCashBalanceAfterBuy = this.prevDateBalance + l_dblOtherReceiptAmount;
        //�����������̌��Ϗ���
        int l_intOrder = 0;
        //�������Ϗ��̔z��̗v�f�����擾����
        int l_intLength = l_settlementReflectors.length;

        //���Ԃ�0�`�h�v�f��-1�h�ŌJ��Ԃ�����
        for (int index = 0; index < l_intLength; index++)
        {
            //�������ϔ��t����i= �������Ϗ��(index).�������ϔ��t����j���擾����B
            double l_dblSettlementBuyAmount =
                l_settlementReflectors[index].calcSettlementBuyAmount();
            //���t���_�c���i=���t���_�c�� - �������ϔ��t����j���v�Z����B
            l_dblCurCashBalanceAfterBuy = l_dblCurCashBalanceAfterBuy - l_dblSettlementBuyAmount;

            //�������Ϗ��(index).���t���_�c���ɔ��t���_�c����������B
            l_settlementReflectors[index].setCashBalanceAfterBuy(l_dblCurCashBalanceAfterBuy);

            //�������ϔ��t����i= �������Ϗ��(index).�������ϔ��t����j���擾����B
            double l_dblSettlementSellAmount =
                l_settlementReflectors[index].calcSettlementSellAmount();
            //���t���_�c���i=���t���_�c�� + �������ϔ��t����j���v�Z����B
            l_dblCurCashBalanceAfterBuy = l_dblCurCashBalanceAfterBuy + l_dblSettlementSellAmount;

            //����ID�i= �������Ϗ��(index).����ID�j���擾����B
            long l_lngFundId = l_settlementReflectors[index].getFundId();

            //�p�����[�^.��������ID�Ɩ���ID����������
            if (l_lngOrderFundId == l_lngFundId)
            {
                //���Ԃ𒍕������̌��Ϗ��ʂɑ������
                l_intOrder = index;
            }
        }

        //�����������.�������ϔ��t���
        BigDecimal l_bdOrderSettlementSellAmount =
            new BigDecimal(l_settlementReflectors[l_intOrder].calcSettlementSellAmount());
        //���t�㌈�Ϗ��ʇ@@
        int l_intAfterOrder1 = 0;

        //���Ԃ�0�`�h�v�f��-1�h�ŌJ��Ԃ�����
        for (int index = 0; index < l_intLength; index++)
        {
            //�������ϔ������z(index)
            BigDecimal l_bdCurSettlementDiff =
                new BigDecimal(l_settlementReflectors[index].calcSettlementDiff());
            //�������ϔ��t���(index)
            BigDecimal l_bdCurSettlementSellAmount =
                new BigDecimal(l_settlementReflectors[index].calcSettlementSellAmount());

            //���t�㌈�Ϗ��ʇ@@���J�[�\���ʒu�ɂ���B
            l_intAfterOrder1 = index;

            //�������ϔ������z(index)���}�C�i�X����
            //�������ϔ��t���(index)�������������.�������ϔ��t�����菬������
            if (l_bdCurSettlementDiff.compareTo(new BigDecimal(0.0)) < 0
                && l_bdCurSettlementSellAmount.compareTo(l_bdOrderSettlementSellAmount) < 0)
            {
                //���t�㌈�Ϗ��ʇ@@���J�[�\���ʒu�̂P��O�ɂ���B
                l_intAfterOrder1 = index - 1;
                //���[�v���甲����B
                index = l_intLength;
            }

        }

        //���t�㌈�Ϗ��ʇA
        int l_intAfterOrder2 = l_intOrder;
        //�����������.�������ϔ������z
        BigDecimal l_bdOrderSettlementDiff =
            new BigDecimal(l_settlementReflectors[l_intOrder].calcSettlementDiff());

        //�����������.�������ϔ������z��0���傫����
        if (l_bdOrderSettlementDiff.compareTo(new BigDecimal(0.0)) > 0)
        {
            //���Ԃ��h�����������̌��Ϗ���+1�h�`�h�v�f��-1�h�ŌJ��Ԃ�����
            for (int index = l_intOrder + 1; index < l_intLength; index++)
            {
                //���t�㌈�Ϗ��ʇA���J�[�\���ʒu�ɂ���B
                l_intAfterOrder2 = index;

                //���t���_�c��(index)
                BigDecimal l_bdCashBalanceAfterBuy =
                    new BigDecimal(l_settlementReflectors[index].getCashBalanceAfterBuy());

                //���t���_�c��(index) - �����������.�������ϔ������z���v�Z����B
                BigDecimal l_bdProfit = l_bdCashBalanceAfterBuy.subtract(l_bdOrderSettlementDiff);

                //���������̔����v���Ȃ��Ȃ邱�Ƃɂ���āA���֋�����������ꍇ
                if (l_bdProfit.compareTo(new BigDecimal(0.0)) < 0)
                {
                    //���t�㌈�Ϗ��ʇA���J�[�\���ʒu�̂P��O�ɂ���B
                    l_intAfterOrder2 = index - 1;

                    //���[�v���甲����
                    index = l_intLength;
                }
            }
        }

        //���t�㌈�Ϗ��ʇ@@�ƇA�Ƃŏ��������𔄕t�㌈�Ϗ��ʂƂ���B
        int l_intAfterOrder = Math.min(l_intAfterOrder1, l_intAfterOrder2);

        //���t�㌈�Ϗ��ʂƒ��������̌��Ϗ��ʂő傫�����𔄕t�㌈�Ϗ��ʂƂ���
        l_intAfterOrder = Math.max(l_intAfterOrder, l_intOrder);

        //���Ԃ��h�����������̌��Ϗ��ʁh�`�h���t�㌈�Ϗ���-1�h�ŌJ��Ԃ�����
        for (int index = l_intOrder; index < l_intAfterOrder; index++)
        {
            //���Ϗ��ʂ����ւ���B
            WEB3TPSettlementReflector l_bufSettlementReflector = l_settlementReflectors[index];
            l_settlementReflectors[index] = l_settlementReflectors[index + 1];
            l_settlementReflectors[index + 1] = l_bufSettlementReflector;
        }

        //���t�㌈�Ϗ��ʂ�ԋp����B
        return l_intAfterOrder;
    }
}
@
