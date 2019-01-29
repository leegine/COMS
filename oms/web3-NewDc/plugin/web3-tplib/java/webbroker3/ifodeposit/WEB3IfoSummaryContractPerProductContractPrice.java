head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.37.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoSummaryContractPerProductContractPrice.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �������P���ʐ敨OP���ʏW�v(WEB3IfoSummaryContractPerProductContractPrice.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15 nakazato(ACT) �V�K�쐬
Revesion History : 2007/07/06 hijikata(SRA) �[��Ή� ���f��No.061�C �v�Z����No.022
Revesion History : 2007/08/02 hijikata(SRA) �[��Ή� �v�Z����No.035
Revesion History : 2007/08/07 k.yamashita(SRA) �[��Ή� �v�Z����No.038
*/
package webbroker3.ifodeposit;

import java.math.BigDecimal;

import webbroker3.ifodeposit.define.WEB3IfoDepositProfitLossCalcDef;

/**
 * (�������P���ʐ敨OP���ʏW�v)<BR>
 * ����ID�A���P�����L�[�ɏW�v����錚�ʏW�v�N���X�B<BR>
 * �]�����v�̎Z�o�ɗp������B<BR>
 *
 *  [�W�v���e]<BR>
 * �@@�E�敨/�I�v�V�����ʁA����/�����ʂ̌�����<BR>
 */
public class WEB3IfoSummaryContractPerProductContractPrice extends WEB3IfoSummaryContract
{

    /**
     * (���P��)<BR>
     */
    private double contractPrice;

    /**
     * (�R���X�g���N�^)
     */
    public WEB3IfoSummaryContractPerProductContractPrice()
    {

    }

    /**
     * (create�������P���ʐ敨OP���ʏW�v)<BR>
     * 
     * �������P���ʐ敨OP���ʏW�v�𐶐�����B<BR>
     * @@return webbroker3.ifodeposit.WEB3IfoSummaryContractPerProductContractPrice
     */
    public static WEB3IfoSummaryContractPerProductContractPrice create()
    {
        return new WEB3IfoSummaryContractPerProductContractPrice();
    }

    /**
     * (calc�������P���ʐ敨�����]�����v)<BR>
     * 
     * 
     * �u�������P���ʐ敨�����]�����v�v��ԋp����B<BR>
     * 
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * @@return double
     */
    public double calcFuturesBuyContractProfitLoss()
    {
        //�������P���ʐ敨�����]�����v
        double l_dblProfitLoss = 0;

        //�敨�̏ꍇ
        if (this.isFutures() == true)
        {
            //����(BigDecimal�^)
            BigDecimal l_bdCurrentPrice = new BigDecimal(Double.toString(this.getCurrentPrice()));
            //���P��(BigDecimal�^)
            BigDecimal l_bdContractPrice = new BigDecimal(this.getContractPrice());
            //�敨��������(BigDecimal�^)
            BigDecimal l_bdBuyQuantity;
            //�敨�̕]�����v�v�Z�ɓ��������܂߂Ȃ��ꍇ
            if(WEB3IfoDepositProfitLossCalcDef.EXCEPT_TODAY_CONTRACT.equals(this.getProfitLossCalcType()))
            {
                l_bdBuyQuantity = new BigDecimal(this.getBuyQuantity() - this.getTodayBuyQuantity());
            }
            else
            {
                l_bdBuyQuantity = new BigDecimal(this.getBuyQuantity());
            }
            //�w���搔(BigDecimal�^)
            BigDecimal l_bdUnitSize = new BigDecimal(this.getUnitSize());

            //�������P���ʐ敨�����]�����v�@@=�@@(�����@@?�@@���P��)�@@�~�@@�敨�������ʁ@@�~�@@�w���搔
            BigDecimal l_bdProfitLoss =
                l_bdCurrentPrice.subtract(l_bdContractPrice).multiply(l_bdBuyQuantity).multiply(
                    l_bdUnitSize);

            //�����_�ȉ��؎̂ď������s��
            l_bdProfitLoss = l_bdProfitLoss.setScale(0, BigDecimal.ROUND_DOWN);
            //�v�Z���������P���ʐ敨�����]�����v��double�ɕϊ�����
            l_dblProfitLoss = l_bdProfitLoss.doubleValue();
        }
        //�ȊO�̏ꍇ(�I�v�V�����̏ꍇ)
        else
        {
            //�������P���ʐ敨�����]�����v��0����
            l_dblProfitLoss = 0;
        }

        //�v�Z�����������P���ʐ敨�����]�����v��ԋp����B
        return l_dblProfitLoss;
    }

    /**
     * (calc�������P���ʐ敨�����]�����v)<BR>
     * 
     * �u�������P���ʐ敨�����]�����v�v��ԋp����B<BR>
     * 
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * @@return double
     */
    public double calcFuturesSellContractProfitLoss()
    {
        //�������P���ʐ敨�����]�����v
        double l_dblProfitLoss = 0;

        //�敨�̏ꍇ
        if (this.isFutures() == true)
        {
            //����(BigDecimal�^)
            BigDecimal l_bdCurrentPrice = new BigDecimal(Double.toString(this.getCurrentPrice()));
            //���P��(BigDecimal�^)
            BigDecimal l_bdContractPrice = new BigDecimal(this.getContractPrice());
            //�敨��������(BigDecimal�^)
            BigDecimal l_bdSellQuantity;
            //�敨�̕]�����v�v�Z�ɓ��������܂߂Ȃ��ꍇ
            if(WEB3IfoDepositProfitLossCalcDef.EXCEPT_TODAY_CONTRACT.equals(this.getProfitLossCalcType()))
            {
                l_bdSellQuantity = new BigDecimal(this.getSellQuantity() - this.getTodaySellQuantity());
            }
            else
            {
                l_bdSellQuantity = new BigDecimal(this.getSellQuantity());
            }
            //�w���搔(BigDecimal�^)
            BigDecimal l_bdUnitSize = new BigDecimal(this.getUnitSize());
            
            //�������P���ʐ敨�����]�����v�@@=�@@(���P�� - ����)�@@�~�@@�敨�������ʁ@@�~�@@�w���搔
            BigDecimal l_bdProfitLoss =
                l_bdContractPrice.subtract(l_bdCurrentPrice).multiply(l_bdSellQuantity).multiply(l_bdUnitSize);

            //�����_�ȉ��؎̂ď������s��
            l_bdProfitLoss = l_bdProfitLoss.setScale(0, BigDecimal.ROUND_DOWN);
            //�v�Z���������P���ʐ敨�����]�����v��double�ɕϊ�����
            l_dblProfitLoss = l_bdProfitLoss.doubleValue();
        }
        //�ȊO�̏ꍇ(�I�v�V�����̏ꍇ)
        else
        {
            //�������P���ʐ敨�����]�����v��0����
            l_dblProfitLoss = 0;
        }

        //�v�Z�����������P���ʐ敨�����]�����v��ԋp����B
        return l_dblProfitLoss;
    }

    /**
     * (calc�������P���ʃI�v�V���������]�����v)<BR>
     * 
     * 
     * �u�������P���ʃI�v�V���������]�����v�v��ԋp����B<BR>
     * 
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * @@return double
     */
    public double calcOptionBuyContractProfitLoss()
    {
        //�������P���ʃI�v�V���������]�����v
        double l_dblProfitLoss = 0;

        //�I�v�V�����̏ꍇ
        if (this.isFutures() == false)
        {
            //����(BigDecimal�^)
            BigDecimal l_bdCurrentPrice = new BigDecimal(Double.toString(this.getCurrentPrice()));
            //���P��(BigDecimal�^)
            BigDecimal l_bdContractPrice = new BigDecimal(this.getContractPrice());
            //�I�v�V������������(BigDecimal�^)
            BigDecimal l_bdBuyQuantity = new BigDecimal(this.getBuyQuantity());
            //�w���搔(BigDecimal�^)
            BigDecimal l_bdUnitSize = new BigDecimal(this.getUnitSize());

            //�������P���ʃI�v�V���������]�����v�@@=�@@(�����@@?�@@���P��)�@@�~�@@�I�v�V�����������ʁ@@�~�@@�w���搔
            BigDecimal l_bdProfitLoss =
                l_bdCurrentPrice.subtract(l_bdContractPrice).multiply(l_bdBuyQuantity).multiply(
                    l_bdUnitSize);

            //�����_�ȉ��؎̂ď������s��
            l_bdProfitLoss = l_bdProfitLoss.setScale(0, BigDecimal.ROUND_DOWN);
            //�v�Z���������P���ʐ敨�����]�����v��double�ɕϊ�����
            l_dblProfitLoss = l_bdProfitLoss.doubleValue();
        }
        //�ȊO�̏ꍇ(�敨�̏ꍇ)
        else
        {
            //�������P���ʃI�v�V���������]�����v��0����
            l_dblProfitLoss = 0;
        }

        //�v�Z�����������P���ʃI�v�V���������]�����v��ԋp����B
        return l_dblProfitLoss;
    }

    /**
     * (calc�������P���ʃI�v�V���������]�����v)<BR>
     * 
     * �u�������P���ʃI�v�V���������]�����v�v��ԋp����B<BR>
     * 
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * @@return double
     */
    public double calcOptionSellContractProfitLoss()
    {
        //�������P���ʃI�v�V���������]�����v
        double l_dblProfitLoss = 0;

        //�I�v�V�����̏ꍇ
        if (this.isFutures() == false)
        {
            //����(BigDecimal�^)
            BigDecimal l_bdCurrentPrice = new BigDecimal(Double.toString(this.getCurrentPrice()));
            //���P��(BigDecimal�^)
            BigDecimal l_bdContractPrice = new BigDecimal(this.getContractPrice());
            //�I�v�V������������(BigDecimal�^)
            BigDecimal l_bdSellQuantity = new BigDecimal(this.getSellQuantity());
            //�w���搔(BigDecimal�^)
            BigDecimal l_bdUnitSize = new BigDecimal(this.getUnitSize());

            //�������P���ʃI�v�V���������]�����v�@@=�@@(���P�� - ����)�@@�~�@@�I�v�V�����������ʁ@@�~�@@�w���搔
            BigDecimal l_bdProfitLoss =
                l_bdContractPrice.subtract(l_bdCurrentPrice).multiply(l_bdSellQuantity).multiply(
                    l_bdUnitSize);

            //�����_�ȉ��؎̂ď������s��
            l_bdProfitLoss = l_bdProfitLoss.setScale(0, BigDecimal.ROUND_DOWN);
            //�v�Z���������P���ʃI�v�V���������]�����v��double�ɕϊ�����
            l_dblProfitLoss = l_bdProfitLoss.doubleValue();
        }
        //�ȊO�̏ꍇ(�敨�̏ꍇ)
        else
        {
            //�������P���ʃI�v�V���������]�����v��0����
            l_dblProfitLoss = 0;
        }

        //�v�Z�����������P���ʃI�v�V���������]�����v��ԋp����B
        return l_dblProfitLoss;
    }

    /**
     * (calc�������P���ʐ敨�����]�����v���؋����s�����m�聄)<BR>
     * 
     * 
     * �u�������P���ʐ敨�����]�����v���؋����s�����m�聄�v��ԋp����B<BR>
     * 
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * @@return double
     */
    public double calcFuturesBuyContractProfitLossTemp()
    {
        //�������P���ʐ敨�����]�����v���؋����s�����m�聄
        double l_dblProfitLossTemp = 0;

        //�敨�̏ꍇ
        if (this.isFutures() == true)
        {
            //�������Z�l  
            double l_dblPrice = this.getCurrentBizDateLiquidationPrice();
            if (l_dblPrice == 0) 
            {
                //�������Z�l==ZERO�̏ꍇ�A����
                l_dblPrice = this.getCurrentPrice();
            }
            //�������Z�l(BigDecimal�^) 
            BigDecimal l_bdPrice = new BigDecimal(Double.toString(l_dblPrice));
            //���P��(BigDecimal�^) 
            BigDecimal l_bdContractPrice = new BigDecimal(this.getContractPrice());
            //�敨��������(BigDecimal�^)
            BigDecimal l_bdBuyQuantity = new BigDecimal(this.getBuyQuantityTemp());

            //�w���搔(BigDecimal�^)
            BigDecimal l_bdUnitSize = new BigDecimal(this.getUnitSize());

            //�������P���ʐ敨�����]�����v���؋����s�����m�聄 = (�������Z�l - ���P��) �~ �敨�������� �~ �w���搔
            BigDecimal l_bdProfitLossTemp =
                l_bdPrice.subtract(l_bdContractPrice).multiply(l_bdBuyQuantity).multiply(
                    l_bdUnitSize);

            //�����_�ȉ��؂�̂�
            l_bdProfitLossTemp = l_bdProfitLossTemp.setScale(0, BigDecimal.ROUND_DOWN);
            //�v�Z���������P���ʐ敨�����]�����v���؋����s�����m�聄��double�ɕϊ�����
            l_dblProfitLossTemp = l_bdProfitLossTemp.doubleValue();
        }
        //�I�v�V�����̏ꍇ
        else
        {
            l_dblProfitLossTemp = 0;
        }

         return l_dblProfitLossTemp;
    }

    /**
     * (calc�������P���ʐ敨�����]�����v���؋����s�����m�聄)<BR>
     * 
     * �u�������P���ʐ敨�����]�����v���؋����s�����m�聄�v��ԋp����B<BR>
     * 
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * @@return double
     */
    public double calcFuturesSellContractProfitLossTemp()
    {
        //�������P���ʐ敨�����]�����v���؋����s�����m�聄
        double l_dblProfitLossTemp = 0;

        //�敨�̏ꍇ
        if (this.isFutures() == true)
        {
            //�������Z�l  
            double l_dblPrice = this.getCurrentBizDateLiquidationPrice();
            if (l_dblPrice == 0) 
            {
                //�������Z�l==ZERO�̏ꍇ�A����
                l_dblPrice = this.getCurrentPrice();
            }
            //�������Z�l(BigDecimal�^) 
            BigDecimal l_bdPrice = new BigDecimal(Double.toString(l_dblPrice));
            //���P��(BigDecimal�^)
            BigDecimal l_bdContractPrice = new BigDecimal(this.getContractPrice());
            //�敨��������(BigDecimal�^)
            BigDecimal l_bdSellQuantity = new BigDecimal(this.getSellQuantityTemp());
			           
            //�w���搔(BigDecimal�^)
            BigDecimal l_bdUnitSize = new BigDecimal(this.getUnitSize());
            
            //�������P���ʐ敨�����]�����v���؋����s�����m�聄 = (���P�� - �������Z�l) �~ �敨�������� �~ �w���搔
            BigDecimal l_bdProfitLossTemp =
                l_bdContractPrice.subtract(l_bdPrice).multiply(l_bdSellQuantity).multiply(l_bdUnitSize);

            //�����_�ȉ��؂�̂�
            l_bdProfitLossTemp = l_bdProfitLossTemp.setScale(0, BigDecimal.ROUND_DOWN);
            //�v�Z���������P���ʐ敨�����]�����v���؋����s�����m�聄��double�ɕϊ�����
            l_dblProfitLossTemp = l_bdProfitLossTemp.doubleValue();
        }
        //�I�v�V�����̏ꍇ
        else
        {
            l_dblProfitLossTemp = 0;
        }

        return l_dblProfitLossTemp;
    }

    /**
     * (get���P��)<BR>
     * 
     * this.���P����ԋp����B<BR>
     * @@return double
     */
    public double getContractPrice()
    {
        return this.contractPrice;
    }

    /**
     * (set���P��)<BR>
     * 
     * ����.���P����this.���P���ɃZ�b�g����B<BR>
     * @@param l_dblContractPrice - (���P��)<BR>
     */
    public void setContractPrice(double l_dblContractPrice)
    {
        this.contractPrice = l_dblContractPrice;
    }
}
@
