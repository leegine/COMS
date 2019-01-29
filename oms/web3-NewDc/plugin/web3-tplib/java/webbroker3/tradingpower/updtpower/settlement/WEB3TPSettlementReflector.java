head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.12.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPSettlementReflector.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �������Ǝ�����(WEB3TPSettlementReflector.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/03 nakazato(ACT) �V�K�쐬
*/
package webbroker3.tradingpower.updtpower.settlement;

import java.math.BigDecimal;

/**
 * �i�������Ǝ�����j<BR>
 * @@author  nakazato(ACT)
 * @@version 1.0
 */
public class WEB3TPSettlementReflector
{
 
    /**
     * �i����ID�j<BR>
     */
    private long fundId;

    /**
     * �i�w����O���ۗL���ʁj<BR>
     */
    private double existQuantity;

    /**
     * �i���t���ʁj<BR>
     */
    private double buyQuantity;

    /**
     * �i���t����j<BR>
     */
    private double buyAmount;

    /**
     * �i���t���ʁj<BR>
     */
    private double sellQuantity;

    /**
     * �i���t����j<BR>
     */
    private double sellAmount;

    /**
     * �i����蔄�t���ʁj<BR>
     */
    private double unexecutedSellQuantity;

    /**
     * �i���t���_�c���j<BR>
     */
    private double cashBalanceAfterBuy;

    /**
     * �i�������Ǝ�����j<BR>
     * 
     * �R���X�g���N�^<BR>
     * @@roseuid 40C56A9400AB
     */
    public WEB3TPSettlementReflector()
    {
        //�t�B�[���h�̏�����
        this.fundId = 0;
        this.existQuantity = 0.0;
        this.buyQuantity = 0.0;
        this.sellQuantity = 0.0;
        this.buyAmount = 0.0;
        this.sellAmount = 0.0;
        this.unexecutedSellQuantity = 0.0;
        this.cashBalanceAfterBuy = 0.0;
    }

    /**
     * �iget����ID�j<BR>
     * 
     * this.����ID��ԋp����B<BR>
     * @@return long
     * @@roseuid 40BC66A70156
     */
    public long getFundId()
    {
        return this.fundId;
    }

    /**
     * �iset����ID<BR>
     * 
     * �p�����[�^.����ID��this.����ID�ɃZ�b�g����B<BR>
     * @@param l_lngFundId - �i����ID�j
     * @@roseuid 40C51A5003AA
     */
    public void setFundId(long l_lngFundId)
    {
        this.fundId = l_lngFundId;
    }

    /**
     * �iget�w����O���ۗL���ʁj<BR>
     * 
     * this.�w����O���ۗL���ʂ�ԋp����B<BR>
     * @@return double
     * @@roseuid 40C519C10262
     */
    public double getExistQuantity()
    {
        return this.existQuantity;
    }

    /**
     * �iset�w����O���ۗL���ʁj<BR>
     * 
     * �p�����[�^.�w����O���ۗL���ʂ�this.�w����O���ۗL���ʂɃZ�b�g����B<BR>
     * @@param l_dblExistQuantity - �i�w����O���ۗL���ʁj
     * @@roseuid 40C519C9034C
     */
    public void setExistQuantity(double l_dblExistQuantity)
    {
        this.existQuantity = l_dblExistQuantity;
    }

    /**
     * �iget���t���ʁj<BR>
     * 
     * this.���t���ʂ�ԋp����B<BR>
     * @@return double
     * @@roseuid 40C519D00233
     */
    public double getBuyQuantity()
    {
        return this.buyQuantity;
    }

    /**
     * �iset���t���ʁj<BR>
     * 
     * �p�����[�^.���t���ʂ�this.���t���ʂɃZ�b�g����B<BR>
     * @@param l_dblBuyQuantity - �i���t���ʁj
     * @@roseuid 40C519DB03B9
     */
    public void setBuyQuantity(double l_dblBuyQuantity)
    {
        this.buyQuantity = l_dblBuyQuantity;
    }

    /**
     * �iget���t����j<BR>
     * 
     * this.���t�����ԋp����B<BR>
     * @@return double
     * @@roseuid 40C519E1033C
     */
    public double getBuyAmount()
    {
        return this.buyAmount;
    }

    /**
     * �iset���t����j<BR>
     * 
     * �p�����[�^.���t�����this.���t����ɃZ�b�g����B<BR>
     * @@param l_dblBuyAmount - �i���t����j
     * @@roseuid 40C519E80271
     */
    public void setBuyAmount(double l_dblBuyAmount)
    {
        this.buyAmount = l_dblBuyAmount;
    }

    /**
     * �iget���t���ʁj<BR>
     * 
     * this.���t���ʂ�ԋp����B<BR>
     * @@return double
     * @@roseuid 40C51A8D02DF
     */
    public double getSellQuantity()
    {
        return this.sellQuantity;
    }

    /**
     * �iset���t���ʁj<BR>
     * 
     * �p�����[�^.���t���ʂ�this.���t���ʂɃZ�b�g����B<BR>
     * @@param l_dblSellQuantity - �i���t���ʁj
     * @@roseuid 40C51A940158
     */
    public void setSellQuantity(double l_dblSellQuantity)
    {
        this.sellQuantity = l_dblSellQuantity;
    }

    /**
     * �iget���t����j<BR>
     * 
     * this.���t�����ԋp����B<BR>
     * @@return double
     * @@roseuid 40C519F80204
     */
    public double getSellAmount()
    {
        return this.sellAmount;
    }

    /**
     * �iset���t����j<BR>
     * 
     * �p�����[�^.���t�����this.���t����ɃZ�b�g����B<BR>
     * @@param l_dblSellAmount - �i���t����j
     * @@roseuid 40C51A00039A
     */
    public void setSellAmount(double l_dblSellAmount)
    {
        this.sellAmount = l_dblSellAmount;
    }

    /**
     * �iget����蔄�t���ʁj<BR>
     * 
     * this.����蔄�t�����ԋp����B<BR>
     * @@return double
     * @@roseuid 40F64BAA025E
     */
    public double getUnexecutedSellQuantity()
    {
        return this.unexecutedSellQuantity;
    }

    /**
     * �iset����蔄�t���ʁj<BR>
     * 
     * �p�����[�^.����蔄�t�����this.����蔄�t����ɃZ�b�g����B<BR>
     * @@param l_dblUnexecutedSellQuantity - �i����蔄�t����j
     * @@roseuid 40F64BB501C2
     */
    public void setUnexecutedSellQuantity(double l_dblUnexecutedSellQuantity)
    {
        this.unexecutedSellQuantity = l_dblUnexecutedSellQuantity;
    }

    /**
     * �iget���t���_�c���j<BR>
     * 
     * this.���t���_�c����ԋp����B<BR>
     * @@return double
     * @@roseuid 4100B65B0128
     */
    public double getCashBalanceAfterBuy()
    {
        return this.cashBalanceAfterBuy;
    }

    /**
     * �iset���t���_�c���j<BR>
     * 
     * �p�����[�^.���t���_�c����this.���t���_�c���ɃZ�b�g����B<BR>
     * @@param l_dblCashBalanceAfterBuy - �i���t���_�c���j
     * @@roseuid 4100BA540251
     */
    public void setCashBalanceAfterBuy(double l_dblCashBalanceAfterBuy)
    {
        this.cashBalanceAfterBuy = l_dblCashBalanceAfterBuy;
    }

    /**
     * �icalc�������ϔ������z�j<BR>
     * 
     * �������ϔ������z��ԋp����B<BR>
     * <BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�]��_�������ρj.doc�v�Q�ƁB<BR>
     * @@return double
     * @@roseuid 40BC66B201F3
     */
    public double calcSettlementDiff()
    {
        //�������ϔ������z�i= �������ϔ��t��� - �������ϔ��t����j���v�Z����
        double l_dblSettlementDiff =
            this.calcSettlementSellAmount() - this.calcSettlementBuyAmount();

        //�������ϔ������z��ԋp����B
        return l_dblSettlementDiff;
    }

    /**
     * �icalc�������ϔ��t����j<BR>
     * 
     * �������ϔ��t�����ԋp����<BR>
     * <BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�]��_�������ρj.doc�v�Q�ƁB<BR>
     * @@return double
     * @@roseuid 40AADD9C01E9
     */
    public double calcSettlementBuyAmount()
    {
        //�������ϑ����O���t����̂����������̔��t������[���s�\�ȑ��
        //�i=MAX(�i�������ϑ����O���t��� - �������ϑ����O���t����j, 0)�j���v�Z����B
        double l_dblAmount =
            Math.max(
                (this.calcExceptSettlementBuyAmount() - this.calcExceptSettlementSellAmount()),
                0.0);

        //�������ϔ��t����i=�������ϑ������t��� + �h�[���s�\�Ȕ��t����h�j
        double l_dblSettlementBuyAmount =
            this.calcSuitableSettlementBuyAmount() + l_dblAmount;

        //double�^�ɕϊ����č������ϔ��t�����ԋp����B
        return l_dblSettlementBuyAmount;
    }

    /**
     * �icalc�������ϔ��t����j<BR>
     * 
     * �������ϔ��t�����ԋp����B<BR>
     * <BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�]��_�������ρj.doc�v�Q�ƁB<BR>
     * @@return double
     * @@roseuid 40AADDB101F9
     */
    public double calcSettlementSellAmount()
    {
        //�������ϔ��t����i= ���t����j��ԋp
        return this.sellAmount;
    }

    /**
     * �icalc�������ϑ������t����j<BR>
     * 
     * �������ϑ����O���t�����ԋp����B<BR>
     * <BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�]��_�������ρj.doc�v�Q�ƁB<BR>
     * @@return double
     */
    public double calcSuitableSettlementBuyAmount()
    {

        //���t����(BigDecimal�^)
        BigDecimal l_bdBuyQuantity = new BigDecimal(this.buyQuantity);
        //���t���(BigDecimal�^)
        BigDecimal l_bdBuyAmount = new BigDecimal(this.buyAmount);
        //���t����(BigDecimal�^)
        BigDecimal l_bdSellQuantity = new BigDecimal(this.sellQuantity);
        //����蔄�t����(BigDecimal�^)
        BigDecimal l_bdUnexecutedSellQuantity = new BigDecimal(this.unexecutedSellQuantity);
        //�w����O���ۗL����(BigDecimal�^)
        BigDecimal l_bdExistQuantity = new BigDecimal(this.existQuantity);

        //�����܂ޔ��t���ʁi= ���t���� + ����蔄�t���ʁj���v�Z����B
        BigDecimal l_bdSellQuantityIncUnExecuted = l_bdSellQuantity.add(l_bdUnexecutedSellQuantity);

    
        //���t����=0�̎�
        if (l_bdBuyQuantity.compareTo(new BigDecimal(0.0)) == 0)
        {
            //�������ϑ����O���t�����ԋp����B
            return 0.0;
        }
    
        /*
         * �������ϑ������t��� = (�����܂ޔ��t���� - �w����O���ۗL����) * ���t��� / ���t����
         */
        BigDecimal l_bdSuitableBuyAmount =
            l_bdSellQuantityIncUnExecuted.subtract(l_bdExistQuantity).multiply(
                l_bdBuyAmount).divide(
                l_bdBuyQuantity,
                10,
                BigDecimal.ROUND_HALF_EVEN);

        //�����_�ȉ��؏グ���s���B
        l_bdSuitableBuyAmount = l_bdSuitableBuyAmount.setScale(0, BigDecimal.ROUND_UP);

        //double�^�ɕϊ����č������ϑ����O���t�����ԋp����B
        return l_bdSuitableBuyAmount.doubleValue();
    }

    /**
     * �icalc�������ϑ������t����j<BR>
     * 
     * �������ϑ������t�����ԋp����B<BR>
     * <BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�]��_�������ρj.doc�v�Q�ƁB<BR>
     * @@return double
     */
    public double calcSuitableSettlementSellAmount()
    {
        //���t����(BigDecimal�^)
        BigDecimal l_bdSellQuantity = new BigDecimal(this.sellQuantity);
        //���t���(BigDecimal�^)
        BigDecimal l_bdSellAmount = new BigDecimal(this.sellAmount);
        //����蔄�t����(BigDecimal�^)
        BigDecimal l_bdUnexecutedSellQuantity = new BigDecimal(this.unexecutedSellQuantity);
        //�w����O���ۗL����(BigDecimal�^)
        BigDecimal l_bdExistQuantity = new BigDecimal(this.existQuantity);

        //�����܂ޔ��t���ʁi= ���t���� + ����蔄�t���ʁj���v�Z����B
        BigDecimal l_bdSellQuantityIncUnExecuted = l_bdSellQuantity.add(l_bdUnexecutedSellQuantity);

        //���t����=0�̎�
        if (l_bdSellQuantity.compareTo(new BigDecimal(0.0)) == 0)
        {
            //�������ϑ����O���t�����ԋp����B
            return 0.0;
        }

        /*
         * �������ϑ������t��� = (�����܂ޔ��t���� - �w����O���ۗL����) * ���t��� / �����܂ޔ��t����
         */
        BigDecimal l_bdSuitableSellAmount =
            l_bdSellQuantityIncUnExecuted.subtract(l_bdExistQuantity).multiply(
                l_bdSellAmount).divide(
                l_bdSellQuantityIncUnExecuted,
                10,
                BigDecimal.ROUND_HALF_EVEN);

        //�����_�ȉ��؎̂Ă��s���B
        l_bdSuitableSellAmount = l_bdSuitableSellAmount.setScale(0,BigDecimal.ROUND_DOWN);

        //double�^�ɕϊ����č������ϑ������t�����ԋp����B
        return l_bdSuitableSellAmount.doubleValue();

    }

    /**
     * �icalc�������ϑ����O���t����j<BR>
     * 
     * �������ϑ����O���t�����ԋp����B<BR>
     * <BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�]��_�������ρj.doc�v�Q�ƁB<BR>
     * @@return double
     * @@roseuid 40AADDD802C4
     */
    public double calcExceptSettlementBuyAmount()
    {
        /*
         * �������ϑ����O���t��� = ���t��� - �������ϑ������t���
         */
        return this.getBuyAmount() - this.calcSuitableSettlementBuyAmount();
    }

    /**
     * �icalc�������ϑ����O���t����j<BR>
     * 
     * �������ϑ����O���t�����ԋp����B<BR>
     * <BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�]��_�������ρj.doc�v�Q�ƁB<BR>
     * @@return double
     * @@roseuid 40AADDE801AA
     */
    public double calcExceptSettlementSellAmount()
    {
        /*
         * �������ϑ����O���t��� = ���t��� - �������ϑ������t���
         */
        return this.getSellAmount() - this.calcSuitableSettlementSellAmount();
    }

    /**
     * �ivalidate�������ϑΏۖ����j<BR>
     * 
     * ���Y�I�u�W�F�N�g���������ϑΏۖ������ǂ������ʂ���B<BR>
     * <BR>
     * [�ԋp�l]<BR>
     * �@@true�F�@@�������ϑΏ�<BR>
     * �@@false�F�@@�������ϑΏۊO<BR>
     * <BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�]��_�������ρj.doc�v�Q�ƁB<BR>
     * @@return boolean
     * @@roseuid 40F7398D0203
     */
    public boolean validateSettlementFundForDayTrade()
    {
        //���t����(BigDecimal�^)
        BigDecimal l_bdBuyQuantity = new BigDecimal(this.buyQuantity);
        //���t����(BigDecimal�^)
        BigDecimal l_bdSellQuantity = new BigDecimal(this.sellQuantity);
        //����蔄�t����(BigDecimal�^)
        BigDecimal l_bdUnexecutedSellQuantity = new BigDecimal(this.unexecutedSellQuantity);
        //�w����O���ۗL����(BigDecimal�^)
        BigDecimal l_bdExistQuantity = new BigDecimal(this.existQuantity);

        //�����܂ޔ��t���ʁi= ���t���� + ����蔄�t���ʁj���v�Z����B
        BigDecimal l_bdSellQuantityIncUnExecuted = l_bdSellQuantity.add(l_bdUnexecutedSellQuantity);

        //�����܂ޔ��t���ʂ��w����O���ۗL���ʂ��傫���@@���@@���t���ʂ�0���傫����
        if (l_bdSellQuantityIncUnExecuted.compareTo(l_bdExistQuantity) > 0
            && l_bdBuyQuantity.compareTo(new BigDecimal(0.0)) > 0)
        {
            //�������ϑΏہATRUE��ԋp����B
            return true;
        }
        //�ȊO�̎�
        else
        {
            //�������ϑΏۊO�AFALSE��ԋp����B
            return false;
        }
    }
}
@
