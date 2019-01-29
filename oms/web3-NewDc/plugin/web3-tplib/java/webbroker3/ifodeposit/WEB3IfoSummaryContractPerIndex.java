head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.38.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoSummaryContractPerIndex.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �w���ʐ敨OP���ʏW�v(WEB3IfoSummaryContractPerIndex.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 nakazato(ACT) �V�K�쐬
Revesion History : 2007/07/06 hijikata(SRA) �[��Ή� 
                            ���f��No.061�F, No.067, No.074�@@
                            �v�Z����No.022, No.024, No.026
Revesion History : 2007/08/02 hijikata(SRA) �[��Ή� �v�Z����No.035
*/

package webbroker3.ifodeposit;

import java.math.BigDecimal;
import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;

import webbroker3.ifodeposit.define.WEB3IfoReservedDateDef;


/**
 * (�w���ʐ敨OP���ʏW�v)<BR>
 * �@@�����Y�����R�[�h���L�[�ɏW�v����錚�ʏW�v�N���X�B<BR>
 * �@@�Ȉ�SPAN�؋����̎Z�o�ɗp������B<BR>
 * �@@[�W�v���e]<BR>
 * �@@�@@�E�敨/�I�v�V�����v�b�g/�I�v�V�����R�[���ʁA�敨����/�����ʂ̌�����<BR>
 * �@@�@@�E�敨/�I�v�V�����v�b�g/�I�v�V�����R�[���ʁA�敨����/�����ʂ̒�������<BR>
 * �@@�@@���I�v�V���������ɂ��ẮA�Ȉ�SPAN�؋����̎Z�o�Ɏg�p���Ȃ����߁A�W�v���s��Ȃ�<BR>
 * 
 * @@author  nakazato(ACT)
 */
public class WEB3IfoSummaryContractPerIndex
{
    /**
     * (�����Y�����R�[�h)
     */
    private String targetProductCode;

    /**
     * (�K��؋���)
     */
    private double ifoDepositPerUnit;

    /**
     * (�K��؋������b�h)
     */
    private double ifoDepositPerUnitRed;

    /**
     * (�K��؋������؋����s�����m�聄)
     */
    private double ifoDepositPerUnitTemp = 0;
    
    /**
     * (�敨��������)
     */
    private double futuresBuyContractQuantity = 0;

    /**
     * (�敨��������)
     */
    private double futuresSellContractQuantity = 0;

    /**
     * (�I�v�V�����v�b�g��������)
     */
    private double optionPutSellContractQuantity = 0;

    /**
     * (�I�v�V�����R�[����������)
     */
    private double optionCallSellContractQuantity = 0;

    /**
     * (�敨������������[T+0])
     */
    private double currentBizDateFuturesBuyOrderQuantity = 0;

    /**
     * (�敨������������[T+1])
     */
    private double nextBizDateFuturesBuyOrderQuantity = 0;

    /**
     * (�敨������������[T+0]
     */
    private double currentBizDateFuturesSellOrderQuantity = 0;

    /**
     * (�敨������������[T+1])
     */
    private double nextBizDateFuturesSellOrderQuantity = 0;

    /**
     * (�I�v�V�����v�b�g������������[T+0])
     */
    private double currentBizDateOptionPutSellOrderQuantity = 0;

    /**
     * (�I�v�V�����v�b�g������������[T+1])
     */
    private double nextBizDateOptionPutSellOrderQuantity = 0;

    /**
     * (�I�v�V�����R�[��������������[T+0])
     */
    private double currentBizDateOptionCallSellOrderQuantity = 0;

    /**
     * (�I�v�V�����R�[��������������[T+1])
     */
    private double nextBizDateOptionCallSellOrderQuantity = 0;

    /**
     * (�敨�������ʁ��؋����s�����m�聄)
     */
    private double futuresBuyContractQuantityTemp = 0;

    /**
     * (�敨�������ʁ��؋����s�����m�聄)
     */
    private double futuresSellContractQuantityTemp = 0;

    /**
     * (�I�v�V�����v�b�g�������ʁ��؋����s�����m�聄)
     */
    private double optionPutSellContractQuantityTemp = 0;

    /**
     * (�I�v�V�����R�[���������ʁ��؋����s�����m�聄)
     */
    private double optionCallSellContractQuantityTemp = 0;

    /**
     * (�R���X�g���N�^)<BR>
     */
    public WEB3IfoSummaryContractPerIndex()
    {

    }

    /**
     * (create�w���ʐ敨OP���ʏW�v)<BR>
     * 
     * �w���ʐ敨OP���ʏW�v�𐶐�����B<BR>
     * @@return webbroker3.ifodeposit.WEB3IfoSummaryContractPerIndex
     * @@roseuid 4119854B0116
     */
    public static WEB3IfoSummaryContractPerIndex create()
    {
        return new WEB3IfoSummaryContractPerIndex();
    }

    /**
     * (add������)<BR>
     * 
     * �E�敨/�v�b�g�I�v�V����/�R�[���I�v�V�����ł��邩<BR>
     * �E����/�����ł��邩<BR>
     * �ɂ��ƂÂ��A�Y�����錚���ʂ̉��Z�������s���B<BR>
     * 
     * �P�j�@@�敨�����̏ꍇ<BR>
     * �@@�@@�@@(����.�敨�I�v�V�������i�敪 == �h�敨�h && ����.is���� == true)<BR>
     * 
     * �@@�@@�@@    this.�敨�������� = this.get�敨��������( ) + ����.����<BR>
     * 
     * �Q�j�@@�敨�����̏ꍇ<BR>
     * �@@�@@�@@(����.�敨�I�v�V�������i�敪 == �h�敨�h && ����.is���� == false)<BR>
     * 
     * �@@�@@�@@    this.�敨�������� = this.get�敨��������( ) + ����.����<BR>
     * 
     * �R�j�@@�I�v�V�����v�b�g�����̏ꍇ<BR>
     * �@@�@@�@@(����.�敨�I�v�V�������i�敪 == �h�v�b�g�I�v�V�����h && <BR> 
     *        ����.is���� == false)<BR>
     *  
     * �@@�@@�@@     this.�I�v�V�����v�b�g�������� =<BR>
     *                this.get�I�v�V�����v�b�g��������( ) + ����.����<BR>
     *
     * �S�j�@@�I�v�V�����R�[�������̏ꍇ<BR>
     * �@@�@@�@@(����.�敨�I�v�V�������i�敪 == �h�R�[���I�v�V�����h && <BR>
     *        ����.is���� == false)<BR>
     * 
     * �@@�@@�@@    this.�I�v�V�����R�[���������� = <BR>
     *               this.get�I�v�V�����R�[����������( ) + ����.����<BR>
     * 
     * @@param l_ifoDerivativeType - (�敨�I�v�V�������i�敪)<BR>
     * 
     * 1�F�敨<BR>
     * 2�F�R�[���I�v�V����<BR>
     * 3�F�v�b�g�I�v�V����<BR>
     * @@param l_blnIsBuy - (is����)<BR>
     * 
     * ���ʂ������ł���ꍇ�Atrue�B�ȊO�Afalse�B<BR>
     * @@param l_dblQuantity - ����<BR>
     * 
     */
    public void addContractQuantity(
        IfoDerivativeTypeEnum l_ifoDerivativeType,
        boolean l_blnIsBuy,
        double l_dblQuantity)
    {
        final String STR_METHOD_NAME = "addContractQuantity()";

        //�敨�����̏ꍇ
        if (l_ifoDerivativeType.intValue() == IfoDerivativeTypeEnum.IntValues.FUTURES
            && l_blnIsBuy == true)
        {
            //this.�敨�������� = this.get�敨��������( ) + ����.����
            this.futuresBuyContractQuantity = this.futuresBuyContractQuantity + l_dblQuantity;
        }
        //�敨�����̏ꍇ
        else if (
            l_ifoDerivativeType.intValue() == IfoDerivativeTypeEnum.IntValues.FUTURES
                && l_blnIsBuy == false)
        {
            //this.�敨�������� = this.get�敨��������( ) + ����.����
            this.futuresSellContractQuantity = this.futuresSellContractQuantity + l_dblQuantity;
        }
        //�I�v�V�����v�b�g�����̏ꍇ
        else if (
            l_ifoDerivativeType.intValue() == IfoDerivativeTypeEnum.IntValues.PUT_OPTIONS
                && l_blnIsBuy == false)
        {
            //this.�I�v�V�����v�b�g�������� = this.get�I�v�V�����v�b�g��������( ) + ����.����
            this.optionPutSellContractQuantity = this.optionPutSellContractQuantity + l_dblQuantity;
        }
        //�I�v�V�����R�[�������̏ꍇ
        else if (
            l_ifoDerivativeType.intValue() == IfoDerivativeTypeEnum.IntValues.CALL_OPTIONS
                && l_blnIsBuy == false)
        {
            //this.�I�v�V�����R�[���������� = this.get�I�v�V�����R�[����������( ) + ����.����
            this.optionCallSellContractQuantity =
                this.optionCallSellContractQuantity + l_dblQuantity;
        }
        //�ȊO�̎�
        else
        {
            //�����s��Ȃ�
        }
    }

    /**
     * (add�����ʁ��؋����s�����m�聄)<BR>
     * 
     * �E�敨/�v�b�g�I�v�V����/�R�[���I�v�V�����ł��邩<BR>
     * �E����/�����ł��邩<BR>
     * �ɂ��ƂÂ��A�Y�����錚���ʁ��؋����s�����m�聄�̉��Z�������s���B<BR>
     * 
     * �P�j�@@�敨�����̏ꍇ<BR>
     * �@@�@@�@@(����.�敨�I�v�V�������i�敪 == �h�敨�h && ����.is���� == true)<BR>
     * 
     * �@@�@@�@@this.�敨�������ʁ��؋����s�����m�聄 = <BR>
     *            this.get�敨�������ʁ��؋����s�����m�聄( ) + ����.����<BR>
     * 
     * �Q�j�@@�敨�����̏ꍇ<BR>
     * �@@�@@�@@(����.�敨�I�v�V�������i�敪 == �h�敨�h && ����.is���� == false)<BR>
     * 
     * �@@�@@�@@this.�敨�������ʁ��؋����s�����m�聄 = 
     *           this.get�敨�������ʁ��؋����s�����m�聄( ) + ����.����<BR>
     * 
     * �R�j�@@�I�v�V�����v�b�g�����̏ꍇ<BR>
     * �@@�@@�@@(����.�敨�I�v�V�������i�敪 == �h�v�b�g�I�v�V�����h && <BR>
     *        ����.is���� == false)<BR>
     *  
     * �@@�@@�@@this.�I�v�V�����v�b�g�������ʁ��؋����s�����m�聄 = 
     *           this.get�I�v�V�����v�b�g�������ʁ��؋����s�����m�聄( ) + ����.����<BR>
     *  
     * �S�j�@@�I�v�V�����R�[�������̏ꍇ<BR>
     * �@@�@@�@@(����.�敨�I�v�V�������i�敪 == �h�R�[���I�v�V�����h && <BR>
     *        ����.is���� == false)<BR>
     * 
     * �@@�@@�@@this.�I�v�V�����R�[���������ʁ��؋����s�����m�聄 = 
     *           this.get�I�v�V�����R�[���������ʁ��؋����s�����m�聄( ) + ����.����<BR>
     * 
     * @@param l_ifoDerivativeType - (�敨�I�v�V�������i�敪)<BR>
     * 
     * 1�F�敨<BR>
     * 2�F�R�[���I�v�V����<BR>
     * 3�F�v�b�g�I�v�V����<BR>
     * @@param l_blnIsBuy - (is����)<BR>
     * 
     * ���ʂ������ł���ꍇ�Atrue�B�ȊO�Afalse�B<BR>
     * @@param l_dblQuantity - ����<BR>
     * 
     */
    public void addContractQuantityTemp(
        IfoDerivativeTypeEnum l_ifoDerivativeType,
        boolean l_blnIsBuy,
        double l_dblQuantity)
    {
        //�敨�����̏ꍇ
        if (l_ifoDerivativeType.intValue() == IfoDerivativeTypeEnum.IntValues.FUTURES && 
            l_blnIsBuy == true)
        {
            //this.�敨�������ʁ��؋����s�����m�聄 = this.get�敨�������ʁ��؋����s�����m�聄( ) + ����.����
            this.futuresBuyContractQuantityTemp = 
                this.getFuturesBuyContractQuantityTemp() + l_dblQuantity;
        }
        //�敨�����̏ꍇ
        else if (
            l_ifoDerivativeType.intValue() == IfoDerivativeTypeEnum.IntValues.FUTURES && 
            l_blnIsBuy == false)
        {
            //this.�敨�������ʁ��؋����s�����m�聄 = this.get�敨�������ʁ��؋����s�����m�聄( ) + ����.����
            this.futuresSellContractQuantityTemp = 
                this.getFuturesSellContractQuantityTemp() + l_dblQuantity;
        }
        //�I�v�V�����v�b�g�����̏ꍇ
        else if (
            l_ifoDerivativeType.intValue() == IfoDerivativeTypeEnum.IntValues.PUT_OPTIONS && 
            l_blnIsBuy == false)
        {
            //this.�I�v�V�����v�b�g�������ʁ��؋����s�����m�聄 = this.get�I�v�V�����v�b�g�������ʁ��؋����s�����m�聄( ) + ����.����
            this.optionPutSellContractQuantityTemp = 
                this.getOptionPutSellContractQuantityTemp() + l_dblQuantity;
        }
        //�I�v�V�����R�[�������̏ꍇ
        else if (
            l_ifoDerivativeType.intValue() == IfoDerivativeTypeEnum.IntValues.CALL_OPTIONS && 
            l_blnIsBuy == false)
            {
                //this.�I�v�V�����R�[���������ʁ��؋����s�����m�聄 = this.get�I�v�V�����R�[���������ʁ��؋����s�����m�聄( ) + ����.����
                this.optionCallSellContractQuantityTemp =
                    this.getOptionCallSellContractQuantityTemp() + l_dblQuantity;
        }
    }

    /**
     * (add��������)<BR>
     * 
     * �E�敨/�v�b�g�I�v�V����/�R�[���I�v�V�����ł��邩<BR>
     * �E����/�����ł��邩<BR>
     * �E�����������ł��邩<BR>
     * �ɂ��ƂÂ��A�Y�����钍�����ʂ̉��Z�������s���B<BR>
     * 
     * �P�j�@@�敨����[T+0]�̏ꍇ<BR>
     * �@@�@@�@@(����.�敨�I�v�V�������i�敪 == �h�敨�h  && ����.is���� == true && 
     * ����.������ == ����.�c�Ɠ�[T+0])<BR>
     * 
     * �@@�@@�@@this.�敨������������[T+0] = this.get�敨������������[T+0]( ) + 
     * ����.����<BR>
     * 
     * �Q�j�@@�敨����[T+1]�̏ꍇ<BR>
     * �@@�@@�@@(����.�敨�I�v�V�������i�敪 == �h�敨�h  && ����.is���� == true && 
     * ����.������ == ����.�c�Ɠ�[T+1])<BR>
     * 
     * �@@�@@�@@this.�敨������������[T+1] = this.get�敨������������[T+1]( ) + 
     * ����.����<BR>
     * 
     * �R�j�@@�敨����[T+0]�̏ꍇ<BR>
     * �@@�@@�@@(����.�敨�I�v�V�������i�敪 == �h�敨�h  && ����.is���� == false && 
     * ����.������ == ����.�c�Ɠ�[T+0])<BR>
     * 
     * �@@�@@�@@this.�敨������������[T+0] = this.get�敨������������[T+0]( ) + 
     * ����.����<BR>
     * 
     * �S�j�@@�敨����[T+1]�̏ꍇ<BR>
     * �@@�@@�@@(����.�敨�I�v�V�������i�敪 == �h�敨�h  && ����.is���� == false && 
     * ����.������ == ����.�c�Ɠ�[T+1])<BR>
     * 
     * �@@�@@�@@this.�敨������������[T+1] = this.get�敨������������[T+1]( ) + 
     * ����.����<BR>
     * 
     * �T�j�@@�I�v�V�����v�b�g����[T+0]�̏ꍇ<BR>
     * �@@�@@�@@(����.�敨�I�v�V�������i�敪 == �h�v�b�g�I�v�V�����h  && ����.is���� == 
     * false && ����.������ == ����.�c�Ɠ�[T+0])<BR>
     * 
     * �@@�@@�@@this.�I�v�V�����v�b�g������������[T+0] = 
     * this.get�I�v�V�����v�b�g������������[T+0]( ) + ����.����<BR>
     * 
     * �U�j�@@�I�v�V�����v�b�g����[T+1]�̏ꍇ<BR>
     * �@@�@@�@@(����.�敨�I�v�V�������i�敪 == �h�v�b�g�I�v�V�����h  && ����.is���� == 
     * false && ����.������ == ����.�c�Ɠ�[T+1])<BR>
     * 
     * �@@�@@�@@this.�I�v�V�����v�b�g������������[T+1] = 
     * this.get�I�v�V�����v�b�g������������[T+1]( ) + ����.����<BR>
     * 
     * �V�j�@@�I�v�V�����R�[������[T+0]�̏ꍇ<BR>
     * �@@�@@�@@(����.�敨�I�v�V�������i�敪 == �h�R�[���I�v�V�����h  && ����.is���� == 
     * false && ����.������ == ����.�c�Ɠ�[T+0])<BR>
     * 
     * �@@�@@�@@this.�I�v�V�����R�[��������������[T+0] = 
     * this.get�I�v�V�����R�[��������������[T+0]( ) + ����.����<BR>
     * 
     * �W�j�@@�I�v�V�����R�[������[T+1]�̏ꍇ<BR>
     * �@@�@@�@@(����.�敨�I�v�V�������i�敪 == �h�R�[���I�v�V�����h  && ����.is���� == 
     * false && ����.������ == ����.�c�Ɠ�[T+1])<BR>
     * 
     * �@@�@@�@@this.�I�v�V�����R�[��������������[T+1] = 
     * this.get�I�v�V�����R�[��������������[T+1]( ) + ����.����<BR>
     * @@param l_ifoDerivativeType - (�敨�I�v�V�������i�敪)<BR>
     * 
     * 1�F�敨<BR>
     * 2�F�R�[���I�v�V����<BR>
     * 3�F�v�b�g�I�v�V����<BR>
     * @@param l_blnIsBuy - (is����)<BR>
     * 
     * ���ʂ������ł���ꍇ�Atrue�B�ȊO�Afalse�B<BR>
     * @@param l_datOrderDate - �������B
     * @@param l_datCurrentBizDate - �c�Ɠ�[T+0]�B
     * @@param l_datNextBizDate - �c�Ɠ�[T+1]�B
     * @@param l_dblQuantity - ���ʁB
     */
    public void addOrderQuantity(
        IfoDerivativeTypeEnum l_ifoDerivativeType,
        boolean l_blnIsBuy,
        Date l_datOrderDate,
        Date l_datCurrentBizDate,
        Date l_datNextBizDate,
        double l_dblQuantity)
    {
        final String STR_METHOD_NAME = "addOrderQuantity()";

        //�敨����[T+0]�̏ꍇ
        if (l_ifoDerivativeType.intValue() == IfoDerivativeTypeEnum.IntValues.FUTURES
            && l_blnIsBuy == true
            && l_datOrderDate.equals(l_datCurrentBizDate))
        {
            //this.�敨������������[T+0] = this.get�敨������������[T+0]( ) + ����.����
            this.currentBizDateFuturesBuyOrderQuantity =
                this.currentBizDateFuturesBuyOrderQuantity + l_dblQuantity;
        }
        //�敨����[T+1]�̏ꍇ
        else if (
            l_ifoDerivativeType.intValue() == IfoDerivativeTypeEnum.IntValues.FUTURES
                && l_blnIsBuy == true
                && l_datOrderDate.equals(l_datNextBizDate))
        {
            //this.�敨������������[T+1] = this.get�敨������������[T+1]( ) + ����.����
            this.nextBizDateFuturesBuyOrderQuantity =
                this.nextBizDateFuturesBuyOrderQuantity + l_dblQuantity;
        }
        //�敨����[T+0]�̏ꍇ
        else if (
            l_ifoDerivativeType.intValue() == IfoDerivativeTypeEnum.IntValues.FUTURES
                && l_blnIsBuy == false
                && l_datOrderDate.equals(l_datCurrentBizDate))
        {
            //this.�敨������������[T+0] = this.get�敨������������[T+0]( ) + ����.����
            this.currentBizDateFuturesSellOrderQuantity =
                this.currentBizDateFuturesSellOrderQuantity + l_dblQuantity;
        }
        //�敨����[T+1]�̏ꍇ
        else if (
            l_ifoDerivativeType.intValue() == IfoDerivativeTypeEnum.IntValues.FUTURES
                && l_blnIsBuy == false
                && l_datOrderDate.equals(l_datNextBizDate))
        {
            //this.�敨������������[T+1] = this.get�敨������������[T+1]( ) + ����.����
            this.nextBizDateFuturesSellOrderQuantity =
                this.nextBizDateFuturesSellOrderQuantity + l_dblQuantity;
        }
        //�I�v�V�����v�b�g����[T+0]�̏ꍇ
        else if (
            l_ifoDerivativeType.intValue() == IfoDerivativeTypeEnum.IntValues.PUT_OPTIONS
                && l_blnIsBuy == false
                && l_datOrderDate.equals(l_datCurrentBizDate))
        {
            //this.�I�v�V�����v�b�g������������[T+0] = this.get�I�v�V�����v�b�g������������[T+0]( ) + ����.����
            this.currentBizDateOptionPutSellOrderQuantity =
                this.currentBizDateOptionPutSellOrderQuantity + l_dblQuantity;
        }
        //�I�v�V�����v�b�g����[T+1]�̏ꍇ
        else if (
            l_ifoDerivativeType.intValue() == IfoDerivativeTypeEnum.IntValues.PUT_OPTIONS
                && l_blnIsBuy == false
                && l_datOrderDate.equals(l_datNextBizDate))
        {
            //this.�I�v�V�����v�b�g������������[T+1] = this.get�I�v�V�����v�b�g������������[T+1]( ) + ����.����
            this.nextBizDateOptionPutSellOrderQuantity =
                this.nextBizDateOptionPutSellOrderQuantity + l_dblQuantity;
        }

        //�I�v�V�����R�[������[T+0]�̏ꍇ
        else if (
            l_ifoDerivativeType.intValue() == IfoDerivativeTypeEnum.IntValues.CALL_OPTIONS
                && l_blnIsBuy == false
                && l_datOrderDate.equals(l_datCurrentBizDate))
        {
            //this.�I�v�V�����R�[��������������[T+0] = this.get�I�v�V�����R�[��������������[T+0]( ) + ����.����
            this.currentBizDateOptionCallSellOrderQuantity =
                this.currentBizDateOptionCallSellOrderQuantity + l_dblQuantity;
        }
        //�I�v�V�����R�[������[T+1]�̏ꍇ
        else if (
            l_ifoDerivativeType.intValue() == IfoDerivativeTypeEnum.IntValues.CALL_OPTIONS
                && l_blnIsBuy == false
                && l_datOrderDate.equals(l_datNextBizDate))
        {
            //this.�I�v�V�����R�[��������������[T+1] = this.get�I�v�V�����R�[��������������[T+1]( ) + ����.����
            this.nextBizDateOptionCallSellOrderQuantity =
                this.nextBizDateOptionCallSellOrderQuantity + l_dblQuantity;
        }
        //�ȊO(�I�v�V��������)�̎�
        else
        {
            //�������Ȃ�
        }
    }

    /**
     * (calc�w���ʔ��|�W�V��������)<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�w���ʔ��|�W�V�������ʁv��ԋp����B<BR>
     * 
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * @@param l_intReservedDate - (�w���)<BR>
     * 
     * 0�A1�A2�̂����ꂩ�̒l�B<BR>
     * @@return double
     */
    public double calcBuyContractQty(int l_intReservedDate)
    {
        final String STR_METHOD_NAME = "calcBuyContractQty()";

        //�w���ʔ��|�W�V��������(n)
        double l_dblBuyPosition = 0;

        //����.�w�����0�̎�
        if (l_intReservedDate == WEB3IfoReservedDateDef.T_0)
        {
            //�w���ʔ��|�W�V��������(n)  =�@@�w���ʐ敨OP���ʏW�v.get�敨��������()
            //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ �w���ʐ敨OP���ʏW�v.get�敨������������[T+0]()
            //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ �w���ʐ敨OP���ʏW�v.get�I�v�V�����v�b�g��������( )
            //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ �w���ʐ敨OP���ʏW�v.get�I�v�V�����v�b�g������������[T+0]( )           
            l_dblBuyPosition =
                this.futuresBuyContractQuantity
                    + this.currentBizDateFuturesBuyOrderQuantity
                    + this.optionPutSellContractQuantity
                    + this.currentBizDateOptionPutSellOrderQuantity;

        }
        //����.�w�����1�܂���2�̎�
        else if (
            l_intReservedDate == WEB3IfoReservedDateDef.T_1
                || l_intReservedDate == WEB3IfoReservedDateDef.T_2)
        {
            //�w���ʔ��|�W�V��������(n)�@@=�@@�w���ʐ敨OP���ʏW�v.get�敨��������()
            //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ �w���ʐ敨OP���ʏW�v.get�敨������������[T+0]()
            //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ �w���ʐ敨OP���ʏW�v.get�敨������������[T+1]()
            //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ �w���ʐ敨OP���ʏW�v.get�I�v�V�����v�b�g��������( )
            //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ �w���ʐ敨OP���ʏW�v.get�I�v�V�����v�b�g������������[T+0]( )
            //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ �w���ʐ敨OP���ʏW�v.get�I�v�V�����v�b�g������������[T+1]( )
            l_dblBuyPosition =
                this.futuresBuyContractQuantity
                    + this.currentBizDateFuturesBuyOrderQuantity
                    + this.nextBizDateFuturesBuyOrderQuantity
                    + this.optionPutSellContractQuantity
                    + this.currentBizDateOptionPutSellOrderQuantity
                    + this.nextBizDateOptionPutSellOrderQuantity;

        }
        //����ȊO
        else
        {
            //�w���ʔ��|�W�V��������(n)��0����
            l_dblBuyPosition = 0;
        }

        //�v�Z�����w���ʔ��|�W�V��������(n)��ԋp����B
        return l_dblBuyPosition;
    }

    /**
     * (calc�w���ʔ��|�W�V��������)<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�w���ʔ��|�W�V�������ʁv��ԋp����B<BR>
     * 
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * @@param l_intReservedDate - (�w���)<BR>
     * 
     * 0�A1�A2�̂����ꂩ�̒l�B<BR>
     * @@return double
     */
    public double calcSellContractQty(int l_intReservedDate)
    {
        final String STR_METHOD_NAME = "calcSellContractQty()";

        //�w���ʔ��|�W�V��������(n)
        double l_dblSellPosition = 0;

        //����.�w�����0�̎�
        if (l_intReservedDate == WEB3IfoReservedDateDef.T_0)
        {
            //�w���ʔ��|�W�V��������(n)�@@=�@@�w���ʐ敨OP���ʏW�v.get�敨��������()
            //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ �w���ʐ敨OP���ʏW�v.get�敨������������[T+0]()
            //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ �w���ʐ敨OP���ʏW�v.get�I�v�V�����R�[����������( )
            //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ �w���ʐ敨OP���ʏW�v.get�I�v�V�����R�[��������������[T+0]( )
            l_dblSellPosition =
                this.futuresSellContractQuantity
                    + this.currentBizDateFuturesSellOrderQuantity
                    + this.optionCallSellContractQuantity
                    + this.currentBizDateOptionCallSellOrderQuantity;

        }
        //����.�w�����1�܂���2�̎�
        else if (
            l_intReservedDate == WEB3IfoReservedDateDef.T_1
                || l_intReservedDate == WEB3IfoReservedDateDef.T_2)
        {
            //�w���ʔ��|�W�V��������(n)�@@=�@@�w���ʐ敨OP���ʏW�v.get�敨��������()
            //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ �w���ʐ敨OP���ʏW�v.get�敨������������[T+0]()
            //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ �w���ʐ敨OP���ʏW�v.get�敨������������[T+1]()
            //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ �w���ʐ敨OP���ʏW�v.get�I�v�V�����R�[����������( )
            //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ �w���ʐ敨OP���ʏW�v.get�I�v�V�����R�[��������������[T+0]( )
            //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ �w���ʐ敨OP���ʏW�v.get�I�v�V�����R�[��������������[T+1]( )
            l_dblSellPosition =
                this.futuresSellContractQuantity
                    + this.currentBizDateFuturesSellOrderQuantity
                    + this.nextBizDateFuturesSellOrderQuantity
                    + this.optionCallSellContractQuantity
                    + this.currentBizDateOptionCallSellOrderQuantity
                    + this.nextBizDateOptionCallSellOrderQuantity;

        }
        //����ȊO
        else
        {
            //�w���ʔ��|�W�V��������(n)��0����
            l_dblSellPosition = 0;
        }

        //�v�Z�����w���ʔ��|�W�V��������(n)��ԋp����B
        return l_dblSellPosition;
    }

    /**
     * (calc�w���ʒ��������|�W�V��������)<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�w���ʒ��������|�W�V�������ʁv��ԋp����B<BR>
     * 
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * @@param l_intReservedDate - (�w���)<br>
     * 
     * 0�A1�A2�̂����ꂩ�̒l�B<br>
     * @@return double
     */
    public double calcBuyOrderQty(int l_intReservedDate)
    {
        final String STR_METHOD_NAME = "calcBuyOrderQty()";

        //�w���ʒ��������|�W�V��������(n)
        double l_dblOrderBuyPosition = 0;

        //����.�w�����0�̎�
        if (l_intReservedDate == WEB3IfoReservedDateDef.T_0)
        {
            //�w���ʒ��������|�W�V��������(n)�@@=�@@�w���ʐ敨OP���ʏW�v.get�敨������������[T+0]()
            //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ �w���ʐ敨OP���ʏW�v.get�I�v�V�����v�b�g������������[T+0]( )
            l_dblOrderBuyPosition =
                this.currentBizDateFuturesBuyOrderQuantity
                    + this.currentBizDateOptionPutSellOrderQuantity;

        }
        //����.�w�����1�܂���2�̎�
        else if (
            l_intReservedDate == WEB3IfoReservedDateDef.T_1
                || l_intReservedDate == WEB3IfoReservedDateDef.T_2)
        {
            //�w���ʒ��������|�W�V��������(n)�@@=�@@�w���ʐ敨OP���ʏW�v.get�敨������������[T+0]()
            //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ �w���ʐ敨OP���ʏW�v.get�敨������������[T+1]()
            //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ �w���ʐ敨OP���ʏW�v.get�I�v�V�����v�b�g������������[T+0]( )
            //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ �w���ʐ敨OP���ʏW�v.get�I�v�V�����v�b�g������������[T+1]( )
            l_dblOrderBuyPosition =
                this.currentBizDateFuturesBuyOrderQuantity
                    + this.nextBizDateFuturesBuyOrderQuantity
                    + this.currentBizDateOptionPutSellOrderQuantity
                    + this.nextBizDateOptionPutSellOrderQuantity;
        }
        //����ȊO
        else
        {
            //�w���ʒ��������|�W�V��������(n)��0����
            l_dblOrderBuyPosition = 0;
        }

        //�v�Z�����w���ʒ��������|�W�V��������(n)�ԋp����B
        return l_dblOrderBuyPosition;
    }

    /**
     * (calc�w���ʒ��������|�W�V��������)<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�w���ʒ��������|�W�V�������ʁv��ԋp����B<BR>
     * 
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * @@param l_intReservedDate - (�w���)<BR>
     * 
     * 0�A1�A2�̂����ꂩ�̒l�B<BR>
     * @@return double
     */
    public double calcSellOrderQty(int l_intReservedDate)
    {
        final String STR_METHOD_NAME = "calcSellOrderQty()";

        //�w���ʒ��������|�W�V��������(n)
        double l_dblOrderSellPosition = 0;

        //����.�w�����0�̎�
        if (l_intReservedDate == WEB3IfoReservedDateDef.T_0)
        {
            //�w���ʒ��������|�W�V��������(n)�@@=�@@�w���ʐ敨OP���ʏW�v.get�敨������������[T+0]()
            //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ �w���ʐ敨OP���ʏW�v.get�I�v�V�����R�[��������������[T+0]( )
            l_dblOrderSellPosition =
                this.currentBizDateFuturesSellOrderQuantity
                    + this.currentBizDateOptionCallSellOrderQuantity;
        }
        //����.�w�����1�܂���2�̎�
        else if (
            l_intReservedDate == WEB3IfoReservedDateDef.T_1
                || l_intReservedDate == WEB3IfoReservedDateDef.T_2)
        {
            //�w���ʒ��������|�W�V��������(n)�@@=�@@�w���ʐ敨OP���ʏW�v.get�敨������������[T+0]()
            //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ �w���ʐ敨OP���ʏW�v.get�敨������������[T+1]()
            //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ �w���ʐ敨OP���ʏW�v.get�I�v�V�����R�[������������[T+0]( )
            //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ �w���ʐ敨OP���ʏW�v.get�I�v�V�����R�[��������������[T+1]( )
            l_dblOrderSellPosition =
                this.currentBizDateFuturesSellOrderQuantity
                    + this.nextBizDateFuturesSellOrderQuantity
                    + this.currentBizDateOptionCallSellOrderQuantity
                    + this.nextBizDateOptionCallSellOrderQuantity;
        }
        //����ȊO
        else
        {
            //�w���ʒ��������|�W�V��������(n)��0����
            l_dblOrderSellPosition = 0;
        }

        //�v�Z�����w���ʒ��������|�W�V��������(n)�ԋp����B
        return l_dblOrderSellPosition;
    }

    /**
     * (calc�w���ʔ��|�W�V�������z )<BR>
     * <BR>
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�w���ʔ��|�W�V�������z�v��ԋp����B <BR>
     * <BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * <BR>
     * @@param l_intReservedDate - (�w���)<BR>
     * 0�A1�A2�̂����ꂩ�̒l�B<BR>
     * @@return double
     */
    public double calcPossibleBuyAmt(int l_intReservedDate)
    {
        /*
         * �����̎w����`�F�b�N���s���B
         */
        //�w������͈͊O�̏ꍇ(n��0�ȏ�2�ȉ��łȂ��ꍇ)
        //����.�w�����0�A1�A2�����ꂩ�̎�
        if (l_intReservedDate < WEB3IfoReservedDateDef.T_0
            || l_intReservedDate > WEB3IfoReservedDateDef.T_2)
        {
            //0��ԋp����B
            return 0;
        }

        /*
         * �w���ʔ��|�W�V�������z(n)���v�Z����B
         * 
         * �w���ʔ��|�W�V�������z(n)�@@=�@@�w���ʔ��|�W�V��������(n) �~ �K��؋���
         * ��   n�́A�����̎w����B
         * ��   �v�Z���Ɏg�p����e�l�̎擾���@@
         *        �E�w���ʔ��|�W�V��������(n) �E�E�E�w���ʐ敨OP���ʏW�v.calc�w���ʔ��|�W�V��������(n)
         *        �E�K��؋��� �E�E�E�w���ʐ敨OP���ʏW�v.get�K��؋���()
         */
        //�w���ʔ��|�W�V�������z
        double l_dblPossAmt = this.calcBuyContractQty(l_intReservedDate) * this.getIfoDepositPerUnit();

        /*
         * �v�Z�����w���ʔ��|�W�V�������z(n)��ԋp����B
         */
        return l_dblPossAmt;
    }

    /**
     * (calc�w���ʔ��|�W�V�������z)<BR>
     * <BR>
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�w���ʔ��|�W�V�������z�v��ԋp����B <BR>
     * <BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * <BR>
     * @@param l_intReservedDate - (�w���)<BR>
     * 0�A1�A2�̂����ꂩ�̒l�B<BR>
     * @@return double
     */
    public double calcPossibleSellAmt(int l_intReservedDate)
    {
        /*
         * �����̎w����`�F�b�N���s���B
         */
        //�w������͈͊O�̏ꍇ(n��0�ȏ�2�ȉ��łȂ��ꍇ)
        //����.�w�����0�A1�A2�����ꂩ�̎�
        if (l_intReservedDate < WEB3IfoReservedDateDef.T_0
            || l_intReservedDate > WEB3IfoReservedDateDef.T_2)
        {
            //0��ԋp����B
            return 0;
        }

        /*
         * �w���ʔ��|�W�V�������z(n)���v�Z����B
         * 
         * �w���ʔ��|�W�V�������z(n)�@@=�@@�w���ʔ��|�W�V��������(n) �~ �K��؋���
         * ��   n�́A�����̎w����B
         * ��   �v�Z���Ɏg�p����e�l�̎擾���@@
         *        �E�w���ʔ��|�W�V��������(n) �E�E�E�w���ʐ敨OP���ʏW�v.calc�w���ʔ��|�W�V��������(n)
         *        �E�K��؋��� �E�E�E�w���ʐ敨OP���ʏW�v.get�K��؋���()
         */
        //�w���ʔ��|�W�V�������z
        double l_dblPossAmt = this.calcSellContractQty(l_intReservedDate) * this.getIfoDepositPerUnit();

        /*
         * �v�Z�����w���ʔ��|�W�V�������z(n)��ԋp����B
         */
        return l_dblPossAmt;
    }

    /**
     * (calc�w���ʔ��|�W�V�������z���b�h)<BR>
     * <BR>
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�w���ʔ��|�W�V�������z���b�h�v��ԋp����B <BR>
     * <BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * <BR>
     * @@param l_intReservedDate - (�w���)<BR>
     * 0�A1�A2�̂����ꂩ�̒l�B<BR>
     * @@return double
     */
    public double calcPossibleBuyAmtRed(int l_intReservedDate)
    {
        /*
         * �����̎w����`�F�b�N���s���B
         */
        //�w������͈͊O�̏ꍇ(n��0�ȏ�2�ȉ��łȂ��ꍇ)
        //����.�w�����0�A1�A2�����ꂩ�̎�
        if (l_intReservedDate < WEB3IfoReservedDateDef.T_0
            || l_intReservedDate > WEB3IfoReservedDateDef.T_2)
        {
            //0��ԋp����B
            return 0;
        }

        /*
         * �w���ʔ��|�W�V�������z(n)���v�Z����B
         * 
         * �w���ʔ��|�W�V�������z(n)�@@=�@@�w���ʔ��|�W�V��������(n) �~ �K��؋������b�h
         * ��   n�́A�����̎w����B
         * ��   �v�Z���Ɏg�p����e�l�̎擾���@@
         *        �E�w���ʔ��|�W�V��������(n) �E�E�E�w���ʐ敨OP���ʏW�v.calc�w���ʔ��|�W�V��������(n)
         *        �E�K��؋������b�h �E�E�E�w���ʐ敨OP���ʏW�v.get�K��؋������b�h()
         */
        //�w���ʔ��|�W�V�������z���b�h
        double l_dblPossAmt = this.calcBuyContractQty(l_intReservedDate) * this.getIfoDepositPerUnitRed();

        /*
         * �v�Z�����w���ʔ��|�W�V�������z���b�h(n)��ԋp����B
         */
        return l_dblPossAmt;
    }

    /**
     * (calc�w���ʔ��|�W�V�������z���b�h )<BR>
     * <BR>
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�w���ʔ��|�W�V�������z���b�h�v��ԋp����B <BR>
     * <BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * <BR>
     * @@param l_intReservedDate - (�w���)<BR>
     * 0�A1�A2�̂����ꂩ�̒l�B<BR>
     * @@return double
     */
    public double calcPossibleSellAmtRed(int l_intReservedDate)
    {
        /*
         * �����̎w����`�F�b�N���s���B
         */
        //�w������͈͊O�̏ꍇ(n��0�ȏ�2�ȉ��łȂ��ꍇ)
        //����.�w�����0�A1�A2�����ꂩ�̎�
        if (l_intReservedDate < WEB3IfoReservedDateDef.T_0
            || l_intReservedDate > WEB3IfoReservedDateDef.T_2)
        {
            //0��ԋp����B
            return 0;
        }

        /*
         * �w���ʔ��|�W�V�������z���b�h(n)���v�Z����B
         * 
         * �w���ʔ��|�W�V�������z(n)�@@=�@@�w���ʔ��|�W�V��������(n) �~ �K��؋������b�h
         * ��   n�́A�����̎w����B
         * ��   �v�Z���Ɏg�p����e�l�̎擾���@@
         *        �E�w���ʔ��|�W�V��������(n) �E�E�E�w���ʐ敨OP���ʏW�v.calc�w���ʔ��|�W�V��������(n)
         *        �E�K��؋������b�h �E�E�E�w���ʐ敨OP���ʏW�v.get�K��؋���()���b�h
         */
        //�w���ʔ��|�W�V�������z���b�h
        double l_dblPossAmt = this.calcSellContractQty(l_intReservedDate) * this.getIfoDepositPerUnitRed();

        /*
         * �v�Z�����w���ʔ��|�W�V�������z���b�h(n)��ԋp����B
         */
        return l_dblPossAmt;
    }

    /**
     * (calc�w���ʔ��|�W�V�������ʁ��؋����s�����m�聄)<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�w���ʔ��|�W�V�������ʁ��؋����s�����m�聄�v��ԋp����B<BR>
     * 
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * @@param l_intReservedDate - (�w���)<BR>
     * 
     * 0�A1�A2�̂����ꂩ�̒l�B<BR>
     * @@return double
     */
    public double calcBuyContractQtyTemp(int l_intReservedDate)
    {
        //�w������͈͊O�̏ꍇ(n��0�ȏ�2�ȉ��łȂ��ꍇ)
        if (l_intReservedDate < WEB3IfoReservedDateDef.T_0
            || l_intReservedDate > WEB3IfoReservedDateDef.T_2)
        {
            //0��ԋp����B
            return 0;
        }

        //�w���ʔ��|�W�V�������ʁ��؋����s�����m�聄
        double l_dblBuyPositionTemp = 0;

        //�w���ʔ��|�W�V�������ʁ��؋����s�����m�聄 = 
        //   �w���ʐ敨OP���ʏW�v.get�敨�������ʁ��؋����s�����m�聄 ()
        //    + �w���ʐ敨OP���ʏW�v.get�I�v�V�����v�b�g�������ʁ��؋����s�����m�聄 ()
        l_dblBuyPositionTemp =
            this.getFuturesBuyContractQuantityTemp()
            + this.getOptionPutSellContractQuantityTemp();

        //�v�Z�����w���ʔ��|�W�V�������ʁ��؋����s�����m�聄(n)��ԋp����B
        return l_dblBuyPositionTemp;
    }

    /**
     * (calc�w���ʔ��|�W�V��������)���؋����s�����m�聄<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�w���ʔ��|�W�V�������ʁ��؋����s�����m�聄�v��ԋp����B<BR>
     * 
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * @@param l_intReservedDate - (�w���)<BR>
     * 
     * 0�A1�A2�̂����ꂩ�̒l�B<BR>
     * @@return double
     */
    public double calcSellContractQtyTemp(int l_intReservedDate)
    {
        //�w������͈͊O�̏ꍇ(n��0�ȏ�2�ȉ��łȂ��ꍇ)
        if (l_intReservedDate < WEB3IfoReservedDateDef.T_0
            || l_intReservedDate > WEB3IfoReservedDateDef.T_2)
        {
            //0��ԋp����B
            return 0;
        }

        //�w���ʔ��|�W�V�������ʁ��؋����s�����m�聄
        double l_dblSellPositionTemp = 0;

        //�w���ʔ��|�W�V�������ʁ��؋����s�����m�聄  = 
        //    �w���ʐ敨OP���ʏW�v.get�敨�������ʁ��؋����s�����m�聄 () 
        //    + �w���ʐ敨OP���ʏW�v.get�I�v�V�����R�[���������ʁ��؋����s�����m�聄 ()
        l_dblSellPositionTemp =
            this.getFuturesSellContractQuantityTemp()
            + this.getOptionCallSellContractQuantityTemp();

        //�v�Z�����w���ʔ��|�W�V�������ʁ��؋����s�����m�聄��ԋp����B
        return l_dblSellPositionTemp;
    }

   /**
     * (calc�w���ʔ��|�W�V�������z���؋����s�����m�聄 )<BR>
     * <BR>
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�w���ʔ��|�W�V�������z���؋����s�����m�聄�v��ԋp����B <BR>
     * <BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * <BR>
     * @@param l_intReservedDate - (�w���)<BR>
     * 0�A1�A2�̂����ꂩ�̒l�B<BR>
     * @@return double
     */
    public double calcPossibleBuyAmtTemp(int l_intReservedDate)
    {
        //�w������͈͊O�̏ꍇ(n��0�ȏ�2�ȉ��łȂ��ꍇ)
        if (l_intReservedDate < WEB3IfoReservedDateDef.T_0
            || l_intReservedDate > WEB3IfoReservedDateDef.T_2)
        {
            //0��ԋp����B
            return 0;
        }

        //�w���ʔ��|�W�V�������z���؋����s�����m�聄 =
        //    �w���ʔ��|�W�V�������ʁ��؋����s�����m�聄 �~ �K��؋������؋����s�����m�聄
        double l_dblPossAmtTemp = 
            this.calcBuyContractQtyTemp(l_intReservedDate) * this.getIfoDepositPerUnitTemp();
         
        return l_dblPossAmtTemp;
    }

    /**
     * (calc�w���ʔ��|�W�V�������z���؋����s�����m�聄)<BR>
     * <BR>
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�w���ʔ��|�W�V�������z���؋����s�����m�聄�v��ԋp����B <BR>
     * <BR>
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * <BR>
     * @@param l_intReservedDate - (�w���)<BR>
     * 0�A1�A2�̂����ꂩ�̒l�B<BR>
     * @@return double
     */
    public double calcPossibleSellAmtTemp(int l_intReservedDate)
    {
        //�w������͈͊O�̏ꍇ(n��0�ȏ�2�ȉ��łȂ��ꍇ)
        if (l_intReservedDate < WEB3IfoReservedDateDef.T_0
            || l_intReservedDate > WEB3IfoReservedDateDef.T_2)
        {
            //0��ԋp����B
            return 0;
        }

        //�w���ʔ��|�W�V�������z���؋����s�����m�聄 =
        //    �w���ʔ��|�W�V�������ʁ��؋����s�����m�聄 �~ �K��؋������؋����s�����m�聄
        double l_dblPossAmtTemp = 
            this.calcSellContractQtyTemp(l_intReservedDate) * this.getIfoDepositPerUnitTemp();
         
        return l_dblPossAmtTemp;
    }

    /**
     * (get�����Y�����R�[�h)<BR>
     * 
     * this.�����Y�����R�[�h��ԋp����B<BR>
     * @@return double
     */
    public String getTargetProductCode()
    {
        return this.targetProductCode;
    }

    /**
     * (get�K��؋���)<BR>
     * 
     * this.�K��؋�����ԋp����B<BR>
     * @@return double
     */
    public double getIfoDepositPerUnit()
    {
        return this.ifoDepositPerUnit;
    }

    /**
     * (get�K��؋������b�h)<BR>
     * 
     * this.�K��؋������b�h��ԋp����B<BR>
     * @@return double
     */
    public double getIfoDepositPerUnitRed()
    {
        return this.ifoDepositPerUnitRed;
    }

    /**
     * (get�敨��������)<BR>
     * 
     * this.�敨�������ʂ�ԋp����B<BR>
     * @@return double
     */
    public double getFuturesBuyContractQuantity()
    {
        return this.futuresBuyContractQuantity;
    }

    /**
     * (get�敨��������)<BR>
     * 
     * this.�敨�������ʂ�ԋp����B<BR>
     * @@return double
     */
    public double getFuturesSellContractQuantity()
    {
        return this.futuresSellContractQuantity;
    }

    /**
     * (get�I�v�V�����v�b�g��������)<BR>
     * 
     * this.�I�v�V�����v�b�g�������ʂ�ԋp����B<BR>
     * @@return double
     */
    public double getOptionPutSellContractQuantity()
    {
        return this.optionPutSellContractQuantity;
    }

    /**
     * (get�I�v�V�����R�[����������)<BR>
     * 
     * this.�I�v�V�����R�[���������ʂ�ԋp����B<BR>
     * @@return double
     */
    public double getOptionCallSellContractQuantity()
    {
        return this.optionCallSellContractQuantity;
    }

    /**
     * (get�敨������������[T+0])<BR>
     * 
     * this.�敨������������[T+0]��ԋp����B<BR>
     * @@return double
     */
    public double getCurrentBizDateFuturesBuyOrderQuantity()
    {
        return this.currentBizDateFuturesBuyOrderQuantity;
    }

    /**
     * (get�敨������������[T+1])<BR>
     * 
     * this.�敨������������[T+1]��ԋp����B<BR>
     * @@return double
     */
    public double getNextBizDateFuturesBuyOrderQuantity()
    {
        return this.nextBizDateFuturesBuyOrderQuantity;
    }

    /**
     * (get�敨������������[T+0])<BR>
     * 
     * this.�敨������������[T+0]��ԋp����B<BR>
     * @@return double
     */
    public double getCurrentBizDateFuturesSellOrderQuantity()
    {
        return this.currentBizDateFuturesSellOrderQuantity;
    }

    /**
     * (get�敨������������[T+1])<BR>
     * 
     * this.�敨������������[T+1]��ԋp����B<BR>
     * @@return double
     */
    public double getNextBizDateFuturesSellOrderQuantity()
    {
        return this.nextBizDateFuturesSellOrderQuantity;
    }

    /**
     * (get�I�v�V�����v�b�g������������[T+0])<BR>
     * 
     * this.�I�v�V�����v�b�g������������[T+0]��ԋp����B<BR>
     * @@return double
     */
    public double getCurrentBizDateOptionPutSellOrderQuantity()
    {
        return this.currentBizDateOptionPutSellOrderQuantity;
    }

    /**
     * (get�I�v�V�����v�b�g������������[T+1])<BR>
     * 
     * this.�I�v�V�����v�b�g������������[T+1]��ԋp����B<BR>
     * @@return double
     */
    public double getNextBizDateOptionPutSellOrderQuantity()
    {
        return this.nextBizDateOptionPutSellOrderQuantity;
    }

    /**
     * (get�I�v�V�����R�[��������������[T+0])<BR>
     * 
     * this.�I�v�V�����R�[��������������[T+0]��ԋp����B<BR>
     * @@return double
     */
    public double getCurrentBizDateOptionCallSellOrderQuantity()
    {
        return this.currentBizDateOptionCallSellOrderQuantity;
    }

    /**
     * (get�I�v�V�����R�[��������������[T+1])<BR>
     * 
     * this.�I�v�V�����R�[��������������[T+1]��ԋp����B<BR>
     * @@return double
     */
    public double getNextBizDateOptionCallSellOrderQuantity()
    {
        return this.nextBizDateOptionCallSellOrderQuantity;
    }

    /**
     * (set�����Y�����R�[�h)<BR>
     * 
     * ����.�����Y�����R�[�h��this.�����Y�����R�[�h�ɃZ�b�g����B<BR>
     * @@param l_strTargetProductCode - �����Y�����R�[�h
     */
    public void setTargetProductCode(String l_strTargetProductCode)
    {
        this.targetProductCode = l_strTargetProductCode;
    }

    /**
     * (set�K��؋���)<BR>
     * 
     * ����.�K��؋�����this.�K��؋����ɃZ�b�g����B<BR>
     * @@param l_dblIfoDepositPerUnit - �K��؋���
     */
    public void setIfoDepositPerUnit(double l_dblIfoDepositPerUnit)
    {
        this.ifoDepositPerUnit = l_dblIfoDepositPerUnit;
    }

    /**
     * (set�K��؋������b�h)<BR>
     * 
     * ����.�K��؋������b�h��this.�K��؋������b�h�ɃZ�b�g����B<BR>
     * @@param l_dblIfoDepositPerUnitRed - �K��؋������b�h
     */
    public void setIfoDepositPerUnitRed(double l_dblIfoDepositPerUnitRed)
    {
        this.ifoDepositPerUnitRed = l_dblIfoDepositPerUnitRed;
    }

    /**
     * (set�K��؋������؋����s�����m�聄)<BR>
     * 
     * ����.�K��؋������؋����s�����m�聄��this.�K��؋������؋����s�����m�聄�ɃZ�b�g����B<BR>
     * @@param l_dblIfoDepositPerUnitTemp  - �K��؋������؋����s�����m�聄
     */
    public void setIfoDepositPerUnitTemp(double l_dblIfoDepositPerUnitTemp)
    {
        this.ifoDepositPerUnitTemp = l_dblIfoDepositPerUnitTemp;
    }    

    /**
     * (set�敨��������)<BR>
     * 
     * ����.�敨�������ʂ�this.�敨�������ʂɃZ�b�g����B<BR>
     * @@param l_dblFuturesBuyContractQuantity - �敨��������
     */
    public void setFuturesBuyContractQuantity(double l_dblFuturesBuyContractQuantity)
    {
        this.futuresBuyContractQuantity = l_dblFuturesBuyContractQuantity;
    }

    /**
     * (set�敨��������)<BR>
     * 
     * ����.�敨�������ʂ�this.�敨�������ʂɃZ�b�g����B<BR>
     * @@param l_dblFuturesSellContractQuantity - �敨��������
     */
    public void setFuturesSellContractQuantity(double l_dblFuturesSellContractQuantity)
    {
        this.futuresSellContractQuantity = l_dblFuturesSellContractQuantity;
    }

    /**
     * (set�I�v�V�����R�[����������)<BR>
     * 
     * ����.�I�v�V�����R�[���������ʂ�this.�I�v�V�����R�[���������ʂɃZ�b�g����B<BR>
     * @@param l_dblOptionCallSellContractQuantity - �I�v�V�����R�[����������
     */
    public void setOptionCallSellContractQuantity(double l_dblOptionCallSellContractQuantity)
    {
        this.optionCallSellContractQuantity = l_dblOptionCallSellContractQuantity;
    }

    /**
     * (set�I�v�V�����v�b�g��������)<BR>
     * 
     * ����.�I�v�V�����v�b�g�������ʂ�this.�I�v�V�����v�b�g�������ʂɃZ�b�g����B<BR>
     * @@param l_dblOptionPutSellContractQuantity - �I�v�V�����v�b�g��������
     */
    public void setOptionPutSellContractQuantity(double l_dblOptionPutSellContractQuantity)
    {
        this.optionPutSellContractQuantity = l_dblOptionPutSellContractQuantity;
    }

    /**
     * (set�敨������������[T+0])<BR>
     * 
     * ����.�敨������������[T+0]��this.�敨������������[T+0]�ɃZ�b�g����B<BR>
     * @@param l_dblCurrentBizDateFuturesBuyOrderQuantity - �敨������������[T+0]
     */
    public void setCurrentBizDateFuturesBuyOrderQuantity(double l_dblCurrentBizDateFuturesBuyOrderQuantity)
    {
        this.currentBizDateFuturesBuyOrderQuantity = l_dblCurrentBizDateFuturesBuyOrderQuantity;
    }

    /**
     * (set�敨������������[T+1])<BR>
     * 
     * ����.�敨������������[T+1]��this.�敨������������[T+1]�ɃZ�b�g����B<BR>
     * @@param l_dblNextBizDateFuturesBuyOrderQuantity - �敨������������[T+1]
     */
    public void setNextBizDateFuturesBuyOrderQuantity(double l_dblNextBizDateFuturesBuyOrderQuantity)
    {
        this.nextBizDateFuturesBuyOrderQuantity = l_dblNextBizDateFuturesBuyOrderQuantity;
    }

    /**
     * (set�敨������������[T+0])<BR>
     * 
     * ����.�敨������������[T+0]��this.�敨������������[T+0]�ɃZ�b�g����B<BR>
     * @@param l_dblCurrentBizDateFuturesSellOrderQuantity - �敨������������[T+0]
     */
    public void setCurrentBizDateFuturesSellOrderQuantity(double l_dblCurrentBizDateFuturesSellOrderQuantity)
    {
        this.currentBizDateFuturesSellOrderQuantity = l_dblCurrentBizDateFuturesSellOrderQuantity;
    }

    /**
     * (set�敨������������[T+1])<BR>
     * 
     * ����.�敨������������[T+1]��this.�敨������������[T+1]�ɃZ�b�g����B<BR>
     * @@param l_dblNextBizDateFuturesSellOrderQuantity - �敨������������[T+1]
     */
    public void setNextBizDateFuturesSellOrderQuantity(double l_dblNextBizDateFuturesSellOrderQuantity)
    {
        this.nextBizDateFuturesSellOrderQuantity = l_dblNextBizDateFuturesSellOrderQuantity;
    }

    /**
     * (set�I�v�V�����v�b�g������������[T+0])<BR>
     * 
     * ����.�I�v�V�����v�b�g������������[T+0]��this.�I�v�V�����v�b�g������������[T+0]�ɃZ�b�g����B<BR>
     * @@param l_dblCurrentBizDateOptionPutSellOrderQuantity - �I�v�V�����v�b�g������������[T+0]
     */
    public void setCurrentBizDateOptionPutSellOrderQuantity(double l_dblCurrentBizDateOptionPutSellOrderQuantity)
    {
        this.currentBizDateOptionPutSellOrderQuantity =
            l_dblCurrentBizDateOptionPutSellOrderQuantity;
    }

    /**
     * (set�I�v�V�����v�b�g������������[T+1])<BR>
     * 
     * ����.�I�v�V�����v�b�g������������[T+1]��this.�I�v�V�����v�b�g������������[T+1]�ɃZ�b�g����B<BR>
     * @@param l_dblNextBizDateOptionPutSellOrderQuantity - �I�v�V�����v�b�g������������[T+1]
     */
    public void setNextBizDateOptionPutSellOrderQuantity(double l_dblNextBizDateOptionPutSellOrderQuantity)
    {
        this.nextBizDateOptionPutSellOrderQuantity = l_dblNextBizDateOptionPutSellOrderQuantity;
    }

    /**
     * (set�I�v�V�����R�[��������������[T+0])<BR>
     * 
     * ����.�I�v�V�����R�[��������������[T+0]��this.�I�v�V�����R�[��������������[T+0]�ɃZ�b�g����B<BR>
     * @@param l_dblCurrentBizDateOptionCallSellOrderQuantity - �I�v�V�����R�[��������������[T+0]
     */
    public void setCurrentBizDateOptionCallSellOrderQuantity(double l_dblCurrentBizDateOptionCallSellOrderQuantity)
    {
        this.currentBizDateOptionCallSellOrderQuantity =
            l_dblCurrentBizDateOptionCallSellOrderQuantity;
    }

    /**
     * (set�I�v�V�����R�[��������������[T+1])<BR>
     * 
     * ����.�I�v�V�����R�[��������������[T+1]��this.�I�v�V�����R�[��������������[T+1]�ɃZ�b�g����B<BR>
     * @@param l_dblNextBizDateOptionCallSellOrderQuantity - �I�v�V�����R�[��������������[T+1]
     */
    public void setNextBizDateOptionCallSellOrderQuantity(double l_dblNextBizDateOptionCallSellOrderQuantity)
    {
        this.nextBizDateOptionCallSellOrderQuantity = l_dblNextBizDateOptionCallSellOrderQuantity;
    }

    /**
     * (get�K��؋������؋����s�����m�聄)<BR>
     * 
     * this.�K��؋������؋����s�����m�聄��ԋp����B<BR>
     * @@return double
     */
    public double getIfoDepositPerUnitTemp()
    {
        return this.ifoDepositPerUnitTemp;
    }   

    /**
     * (get�敨�������ʁ��؋����s�����m�聄)<BR>
     * 
     * this.�敨�������ʁ��؋����s�����m�聄��ԋp����B<BR>
     * @@return double
     */
    public double getFuturesBuyContractQuantityTemp()
    {
        return this.futuresBuyContractQuantityTemp;
    }

    /**
     * (get�敨�������ʁ��؋����s�����m�聄)<BR>
     * 
     * this.�敨�������ʁ��؋����s�����m�聄��ԋp����B<BR>
     * @@return double
     */
    public double getFuturesSellContractQuantityTemp()
    {
        return this.futuresSellContractQuantityTemp;
    }

    /**
     * (get�I�v�V�����v�b�g�������ʁ��؋����s�����m�聄)<BR>
     * 
     * this.�I�v�V�����v�b�g�������ʁ��؋����s�����m�聄��ԋp����B<BR>
     * @@return double
     */
    public double getOptionPutSellContractQuantityTemp()
    {
        return this.optionPutSellContractQuantityTemp;
    }

    /**
     * (get�I�v�V�����R�[���������ʁ��؋����s�����m�聄)<BR>
     * 
     * this.�I�v�V�����R�[���������ʁ��؋����s�����m�聄��ԋp����B<BR>
     * @@return double
     */
    public double getOptionCallSellContractQuantityTemp()
    {
        return this.optionCallSellContractQuantityTemp;
    }

}
@
