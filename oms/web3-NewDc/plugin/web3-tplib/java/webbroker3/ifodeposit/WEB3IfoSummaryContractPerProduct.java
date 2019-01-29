head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.38.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoSummaryContractPerProduct.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����ʐ敨OP���ʏW�v(WEB3IfoSummaryContractPerProduct.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/14 nakazato(ACT) �V�K�쐬
Revesion History : 2007/07/06 hijikata(SRA) �[��Ή� 
                        ���f��No.061�D
                        �v�Z����No.022, No.024, No.026
Revesion History : 2007/08/02 hijikata(SRA) �[��Ή� �v�Z����No.035
*/

package webbroker3.ifodeposit;

import java.math.BigDecimal;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.ErrorInfo;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.ifodeposit.define.WEB3IfoReservedDateDef;

/**
 * (�����ʐ敨OP���ʏW�v)<BR>
 * ����ID���L�[�ɏW�v����錚�ʏW�v�N���X�B<BR>
 * �l�b�g�I�v�V�������l���z�A����сASPAN�؋����̎Z�o�ɗp������B<BR>
 * [�W�v���e]<BR>
 * �@@�E�敨/�I�v�V�����ʁA����/�����ʂ̌�����<BR>
 * �@@�E�敨/�I�v�V�����ʁA����/�����ʂ̒�������<BR>
 * 
 * @@author  nakazato(ACT)
 */
public class WEB3IfoSummaryContractPerProduct extends WEB3IfoSummaryContract
{
    /**
     * (������������[T+0])<BR>
     */
    private double currentBizDateBuyOrderQuantity = 0;

    /**
     * ������������[T+1]
     */
    private double nextBizDateBuyOrderQuantity = 0;

    /**
     * ������������[T+0]
     */
    private double currentBizDateSellOrderQuantity = 0;

    /**
     * ������������[T+1]
     */
    private double nextBizDateSellOrderQuantity = 0;

    /**
     * (�R���X�g���N�^)
     */
    public WEB3IfoSummaryContractPerProduct()
    {

    }

    /**
     * (create�����ʐ敨OP���ʏW�v)<BR>
     * 
     * �����ʐ敨OP���ʏW�v�𐶐�����B<BR>
     * @@return webbroker3.ifodeposit.WEB3IfoSummaryContractPerProduct
     */
    public static WEB3IfoSummaryContractPerProduct create()
    {
        return new WEB3IfoSummaryContractPerProduct();
    }

    /**
     * (add��������)<BR>
     * 
     * ����/�����ł��邩�A�����������ł��邩�ɂ��ƂÂ��A�Y�����钍�����ʂ̉��Z�������s��<BR>
     * 
     * �P�j�@@����[T+0](����.is���� == true && ����.������ == 
     * ����.�c�Ɠ�[T+0])�̏ꍇ<BR>
     * �@@�@@�@@this.������������[T+0] = this.get������������[T+0]( ) + ����.����<BR>
     * 
     * �Q�j�@@����[T+1](����.is���� == true && ����.������ == 
     * ����.�c�Ɠ�[T+1])�̏ꍇ<BR>
     * �@@�@@�@@this.������������[T+1] = this.get������������[T+1]( ) + ����.����<BR>
     * 
     * �R�j�@@����[T+0](����.is���� == false && ����.������ == 
     * ����.�c�Ɠ�<BR>[T+0])�̏ꍇ<BR>
     * �@@�@@�@@this.������������[T+0] = this.get������������[T+0]( ) + ����.����<BR>
     * 
     * �S�j�@@����[T+1](����.is���� == false && ����.������ == 
     * ����.�c�Ɠ�<BR>[T+1])�̏ꍇ<BR>
     * �@@�@@�@@this.������������[T+1] = this.get������������[T+1]( ) + ����.����<BR>
     * @@param l_blnIsBuy - (is����)<BR>
     * 
     * ���ʂ������ł���ꍇ�Atrue�B�ȊO�Afalse�B<BR>
     * @@param l_datOrderBizDate - �������B
     * @@param l_datCurrentBizDate - (�c�Ɠ�[T+0])
     * @@param l_datNextBizDate - (�c�Ɠ�[T+1])
     * @@param l_dblQuantity - ���ʁB
     */
    public void addOrderQuantity(
        boolean l_blnIsBuy,
        Date l_datOrderBizDate,
        Date l_datCurrentBizDate,
        Date l_datNextBizDate,
        double l_dblQuantity)
    {
        final String STR_METHOD_NAME = "addOrderQuantity()";

        //����[T+0]�̎�
        if (l_blnIsBuy == true && l_datOrderBizDate.equals(l_datCurrentBizDate))
        {
            //this.������������[T+0] = this.get������������[T+0]( ) + ����.����
            this.currentBizDateBuyOrderQuantity =
                this.currentBizDateBuyOrderQuantity + l_dblQuantity;
        }
        //����[T+1]�̎�
        else if (l_blnIsBuy == true && l_datOrderBizDate.equals(l_datNextBizDate))
        {
            //this.������������[T+1] = this.get������������[T+1]( ) + ����.����
            this.nextBizDateBuyOrderQuantity = this.nextBizDateBuyOrderQuantity + l_dblQuantity;
        }
        //����[T+0]�̎�
        else if (l_blnIsBuy == false && l_datOrderBizDate.equals(l_datCurrentBizDate))
        {
            //this.������������[T+0] = this.get������������[T+0]( ) + ����.����
            this.currentBizDateSellOrderQuantity =
                this.currentBizDateSellOrderQuantity + l_dblQuantity;
        }
        //����[T+1]�̎�
        else if (l_blnIsBuy == false && l_datOrderBizDate.equals(l_datNextBizDate))
        {
            //this.������������[T+1] = this.get������������[T+1]( ) + ����.����
            this.nextBizDateSellOrderQuantity = this.nextBizDateSellOrderQuantity + l_dblQuantity;
        }
        //�ȊO�̎�
        else
        {
        }
    }

    /**
     * (calc��������)<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�������ʁv���Z�o����B<BR>
     * 
     * �P�j�@@n == 0�̏ꍇ(����.�w��� == 0)<BR>
     * �@@�@@�@@this.get��������( )<BR>
     *       + this.get������������[T+0]( )��ԋp����B<BR>
     * 
     * �Q�j�@@n == 1�A�܂���2�̏ꍇ(����.�w��� != 0)<BR>
     * �@@�@@�@@this.get��������( )<BR>
     *       + this.get������������[T+0]( )<BR>
     *       + this.get������������[T+1]( )��ԋp����B<BR>
     * @@param l_intReservedDate - (�w���)<BR>
     * 
     * 0�A1�A2�̂����ꂩ�̒l�B<BR>
     * @@return double
     */
    public double calcBuyContractQuantity(int l_intReservedDate)
    {
        //��������
        double l_dblBuyContractQuantity = 0;

        //����.�w�����0�̎�
        if (l_intReservedDate == WEB3IfoReservedDateDef.T_0)
        {
            //�������� = this.get��������( ) + this.get������������[T+0]( )
            l_dblBuyContractQuantity =
                this.getBuyQuantity() + this.getCurrentBizDateBuyOrderQuantity();
        }
        //����.�w�����1�܂���2�̎�
        else if (
            l_intReservedDate == WEB3IfoReservedDateDef.T_1
                || l_intReservedDate == WEB3IfoReservedDateDef.T_2)
        {
            //�������� = this.get��������( ) + this.get������������[T+0]( ) + this.get������������[T+1]( )
            l_dblBuyContractQuantity =
                this.getBuyQuantity()
                    + this.getCurrentBizDateBuyOrderQuantity()
                    + this.getNextBizDateBuyOrderQuantity();
        }
        //�ȊO�̎�
        else
        {
            //�������ʂ�0����
            l_dblBuyContractQuantity = 0;
        }

        //�v�Z�����������ʂ�ԋp����B
        return l_dblBuyContractQuantity;
    }

    /**
     * (calc��������)<BR>
     * 
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�������ʁv���Z�o����B<BR>
     * 
     * �P�j�@@n == 0�̏ꍇ(����.�w��� == 0)<BR>
     * �@@�@@�@@this.get��������( )<BR>
     *       + this.get������������[T+0]( )��ԋp����B<BR>
     * 
     * �Q�j�@@n == 1�A�܂���2�̏ꍇ(����.�w��� != 0)<BR>
     * �@@�@@�@@this.get��������( )<BR>
     *       + this.get������������[T+0]( )<BR>
     *       + this.get������������[T+1]( )��ԋp����B<BR>
     * @@param l_intReservedDate - (�w���)<BR>
     * 
     * 0�A1�A2�̂����ꂩ�̒l�B<BR>
     * @@return double
     */
    public double calcSellContractQuantity(int l_intReservedDate)
    {
        //��������
        double l_dblSellContractQuantity = 0;

        //����.�w�����0�̎�
        if (l_intReservedDate == WEB3IfoReservedDateDef.T_0)
        {
            //�������� = this.get��������( ) + this.get������������[T+0]( )
            l_dblSellContractQuantity =
                this.getSellQuantity() + this.getCurrentBizDateSellOrderQuantity();
        }
        //����.�w�����1�܂���2�̎�
        else if (
            l_intReservedDate == WEB3IfoReservedDateDef.T_1
                || l_intReservedDate == WEB3IfoReservedDateDef.T_2)
        {
            //�������� = this.get��������( ) + this.get������������[T+0]( ) + this.get������������[T+1]( )
            l_dblSellContractQuantity =
                this.getSellQuantity()
                    + this.getCurrentBizDateSellOrderQuantity()
                    + this.getNextBizDateSellOrderQuantity();
        }
        //�ȊO�̎�
        else
        {
            //�������ʂ�0����
            l_dblSellContractQuantity = 0;
        }

        //�v�Z�����������ʂ�ԋp����B
        return l_dblSellContractQuantity;
    }

    /**
     * (calc�����ʃI�v�V����������)<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�����ʃI�v�V�����������v��ԋp����B<BR>
     * 
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * @@param l_intReservedDate - (�w���)<BR>
     * 
     * 0�A1�A2�̂����ꂩ�̒l�B<BR>
     * @@return double
     */
    public double calcOptionBuyContractQuantity(int l_intReservedDate)
    {
        //�����ʃI�v�V����������
        double l_dblOptionBuyContractQuantity = 0;

        //�敨�̎�
        if (this.isFutures() == true)
        {
            //�����ʃI�v�V�����������ʂ�0��������
            l_dblOptionBuyContractQuantity = 0;
        }
        //�I�v�V�����̎�
        else
        {
            //����.�w�����0�̎�
            if (l_intReservedDate == WEB3IfoReservedDateDef.T_0)
            {
                //�����ʃI�v�V�����������@@=�@@�����ʐ敨OP���ʏW�v.get��������()
                //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ �����ʐ敨OP���ʏW�v.get������������[T+0]()
                l_dblOptionBuyContractQuantity =
                    this.getBuyQuantity() + this.getCurrentBizDateBuyOrderQuantity();
            }
            //����.�w�����1�܂���2�̎�
            else if (
                l_intReservedDate == WEB3IfoReservedDateDef.T_1
                    || l_intReservedDate == WEB3IfoReservedDateDef.T_2)
            {
                //�����ʃI�v�V�����������@@=�@@�����ʐ敨OP���ʏW�v.get��������()
                //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ �����ʐ敨OP���ʏW�v.get������������[T+0]()
                //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ �����ʐ敨OP���ʏW�v.get������������[T+1]()
                l_dblOptionBuyContractQuantity =
                    this.getBuyQuantity()
                        + this.getCurrentBizDateBuyOrderQuantity()
                        + this.getNextBizDateBuyOrderQuantity();

            }
            //�ȊO�̎�
            else
            {
                //�����ʃI�v�V�����������ʂ�0��������
                l_dblOptionBuyContractQuantity = 0;
            }
        }

        //�v�Z���������ʃI�v�V�����������ʂ�ԋp����
        return l_dblOptionBuyContractQuantity;
    }

    /**
     * (calc�����ʃI�v�V����������)<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�����ʃI�v�V�����������v��ԋp����B<BR>
     * 
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * @@param l_intReservedDate - (�w���)<BR>
     * 
     * 0�A1�A2�̂����ꂩ�̒l�B<BR>
     * @@return double
     */
    public double calcOptionSellContractQuantity(int l_intReservedDate)
    {
        //�����ʃI�v�V����������
        double l_dblOptionSellContractQuantity = 0;

        //�敨�̎�
        if (this.isFutures() == true)
        {
            //�����ʃI�v�V�����������ʂ�0��������
            l_dblOptionSellContractQuantity = 0;
        }
        //�I�v�V�����̎�
        else
        {
            //����.�w�����0�A1�A2�̎�
            if (l_intReservedDate == WEB3IfoReservedDateDef.T_0
                || l_intReservedDate == WEB3IfoReservedDateDef.T_1
                || l_intReservedDate == WEB3IfoReservedDateDef.T_2)
            {
                //�����ʃI�v�V�����������@@=�@@�����ʐ敨OP���ʏW�v.get��������()
                l_dblOptionSellContractQuantity = this.getSellQuantity();
            }
            //�ȊO�̎�
            else
            {
                //�����ʃI�v�V�����������ʂ�0��������                
                l_dblOptionSellContractQuantity = 0;
            }
        }

        //�v�Z���������ʃI�v�V�����������ʂ�ԋp����
        return l_dblOptionSellContractQuantity;
    }

    /**
     * (calc�����ʃI�v�V���������ߌ��ʐ�)<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�����ʃI�v�V���������ߌ��ʐ��v��ԋp����B<BR>
     * 
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * @@param l_intReservedDate - (�w����j<BR>
     * 
     * 0�A1�A2�̂����ꂩ�̒l�B<BR>
     * 
     * @@return double
     */
    public double calcOptionBuyOverContractQuantity(int l_intReservedDate)
    {
        //�����ʃI�v�V���������ߌ��ʐ�
        double l_dblOptionBuyOverContractQuantity = 0;

        //����.�w�����0�A1�A2�̎�
        if (l_intReservedDate == WEB3IfoReservedDateDef.T_0
            || l_intReservedDate == WEB3IfoReservedDateDef.T_1
            || l_intReservedDate == WEB3IfoReservedDateDef.T_2)
        {
            //�����ʃI�v�V���������ߌ��ʐ��@@=�@@Max(0, (�����ʃI�v�V����������(n) �| �����ʃI�v�V����������(n)))
            l_dblOptionBuyOverContractQuantity =
                Math.max(
                    0,
                    this.calcOptionBuyContractQuantity(l_intReservedDate)
                        - this.calcOptionSellContractQuantity(l_intReservedDate));
        }
        //�ȊO�̎�
        else
        {
            //�����ʃI�v�V���������ߌ��ʐ���0����
            l_dblOptionBuyOverContractQuantity = 0;
        }

        return l_dblOptionBuyOverContractQuantity;
    }

    /**
     * (calc�����ʃI�v�V���������ߌ��ʐ�)<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�����ʃI�v�V���������ߌ��ʐ��v��ԋp����B<BR>
     * 
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * @@param l_intReservedDate - (�w���)<BR>
     * 
     * 0�A1�A2�̂����ꂩ�̒l�B<BR>
     * @@return double
     */
    public double calcOptionSellOverContractQuantity(int l_intReservedDate)
    {
        //�����ʃI�v�V���������ߌ��ʐ�
        double l_dblOptionSellOverContractQuantity = 0;

        //����.�w�����0�A1�A2�̎�
        if (l_intReservedDate == WEB3IfoReservedDateDef.T_0
            || l_intReservedDate == WEB3IfoReservedDateDef.T_1
            || l_intReservedDate == WEB3IfoReservedDateDef.T_2)
        {
            //�����ʃI�v�V���������ߌ��ʐ��@@=�@@Max(0, (�����ʃI�v�V����������(n) �| �����ʃI�v�V����������(n)))
            l_dblOptionSellOverContractQuantity =
                Math.max(
                    0,
                    this.calcOptionSellContractQuantity(l_intReservedDate)
                        - this.calcOptionBuyContractQuantity(l_intReservedDate));
        }
        //�ȊO�̎�
        else
        {
            //�����ʃI�v�V���������ߌ��ʐ���0����
            l_dblOptionSellOverContractQuantity = 0;
        }

        return l_dblOptionSellOverContractQuantity;
    }

    /**
     * (calc�����ʔ��I�v�V�������l)<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�����ʔ��I�v�V�������l�v��ԋp����B<BR>
     * 
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * @@param l_intReservedDate - (�w���)<BR>
     * 
     * 0�A1�A2�̂����ꂩ�̒l�B<BR>
     * @@return double
     */
    public double calcBuyOptionValue(int l_intReservedDate)
    {
        //�����ʔ��I�v�V�������l
        double l_dblBuyOptionValue = 0;

        //����.�w�����0�A1�A2�̎�
        if (l_intReservedDate == WEB3IfoReservedDateDef.T_0
            || l_intReservedDate == WEB3IfoReservedDateDef.T_1
            || l_intReservedDate == WEB3IfoReservedDateDef.T_2)
        {

            //�����ʔ����ߌ��ʐ�(n)
            BigDecimal l_bdOverContract =
                new BigDecimal(this.calcOptionBuyOverContractQuantity(l_intReservedDate));
            //����
            BigDecimal l_bdCurrentPrice = new BigDecimal(Double.toString(this.getCurrentPrice()));
            //�w���搔
            BigDecimal l_bdUnitSize = new BigDecimal(this.getUnitSize());

            //�����ʔ��I�v�V�������l�@@=�@@�����ʃI�v�V���������ߌ��ʐ�(n)�@@�~�@@�����@@�~�@@�w���搔
            BigDecimal l_bdBuyOptionValue =
                l_bdOverContract.multiply(l_bdCurrentPrice).multiply(l_bdUnitSize);
                
            //�����_�ȉ��؎̂ď������s��
            l_bdBuyOptionValue = l_bdBuyOptionValue.setScale(0, BigDecimal.ROUND_DOWN);
            //�v�Z���������ʔ��I�v�V�������l(BigDecimal�^)��double�^�ɕϊ�����
            l_dblBuyOptionValue = l_bdBuyOptionValue.doubleValue();
        }
        //�ȊO�̎�
        else
        {
            //�����ʔ��I�v�V�������l��0��������
            l_dblBuyOptionValue = 0;
        }

        return l_dblBuyOptionValue;
    }

    /**
     * (calc�����ʔ��I�v�V�������l)<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�����ʔ��I�v�V�������l�v��ԋp����B<BR>
     * 
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * @@param l_intReservedDate - (�w���)<BR>
     * 
     * 0�A1�A2�̂����ꂩ�̒l�B<BR>
     * @@return double
     */
    public double calcSellOptionValue(int l_intReservedDate)
    {
        //�����ʔ��I�v�V�������l
        double l_dblSellOptionValue = 0;

        //����.�w�����0�A1�A2�̎�
        if (l_intReservedDate == WEB3IfoReservedDateDef.T_0
            || l_intReservedDate == WEB3IfoReservedDateDef.T_1
            || l_intReservedDate == WEB3IfoReservedDateDef.T_2)
        {

            //�����ʔ����ߌ��ʐ�(n)
            BigDecimal l_bdOverContract =
                new BigDecimal(this.calcOptionSellOverContractQuantity(l_intReservedDate));
            //����
            BigDecimal l_bdCurrentPrice = new BigDecimal(Double.toString(this.getCurrentPrice()));
            //�w���搔
            BigDecimal l_bdUnitSize = new BigDecimal(this.getUnitSize());

            //�����ʔ��I�v�V�������l�@@=�@@�����ʃI�v�V���������ߌ��ʐ�(n)�@@�~�@@�����@@�~�@@�w���搔
            BigDecimal l_bdSellOptionValue =
                l_bdOverContract.multiply(l_bdCurrentPrice).multiply(l_bdUnitSize);
                
            //�����_�ȉ��؎̂ď������s��
            l_bdSellOptionValue = l_bdSellOptionValue.setScale(0, BigDecimal.ROUND_DOWN);
            //�v�Z���������ʔ��I�v�V�������l(BigDecimal�^)��double�^�ɕϊ�����
            l_dblSellOptionValue = l_bdSellOptionValue.doubleValue();
        }
        //�ȊO�̎�
        else
        {
            //�����ʔ��I�v�V�������l��0��������
            l_dblSellOptionValue = 0;
        }

        return l_dblSellOptionValue;
    }


   /**
     * (calc�����ʃI�v�V�������������؋����s�����m�聄 )<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�����ʃI�v�V�������������؋����s�����m�聄 �v��ԋp����B<BR>
     * 
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * @@param l_intReservedDate - (�w���)<BR>
     * 
     * 0�A1�A2�̂����ꂩ�̒l�B<BR>
     * @@return double
     */
    public double calcOptionBuyContractQuantityTemp(int l_intReservedDate)
    {
        //�w������͈͊O�̏ꍇ(n��0�ȏ�2�ȉ��łȂ��ꍇ)
        if (l_intReservedDate < WEB3IfoReservedDateDef.T_0
            || l_intReservedDate > WEB3IfoReservedDateDef.T_2)
        {
            //0��ԋp����B
            return 0;
        }

        //�����ʃI�v�V�������������؋����s�����m�聄 
        double l_dblOptionBuyContractQuantityTemp = 0;

        //�敨�̏ꍇ
        if (this.isFutures() == true)
        {
            //�����ʃI�v�V�����������ʁ��؋����s�����m�聄 = 0
            l_dblOptionBuyContractQuantityTemp = 0;
        }
        //�I�v�V�����̏ꍇ
        else
        {
            //�����ʃI�v�V�������������؋����s�����m�聄
            //    = �������ʁ��؋����s�����m�聄
           l_dblOptionBuyContractQuantityTemp =
                    this.getBuyQuantityTemp();
        }

        //�v�Z���������ʃI�v�V�����������ʁ��؋����s�����m�聄 ��ԋp����
        return l_dblOptionBuyContractQuantityTemp;
    }

    /**
     * (calc�����ʃI�v�V�������������؋����s�����m�聄)<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�����ʃI�v�V�������������؋����s�����m�聄�v��ԋp����B<BR>
     * 
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * @@param l_intReservedDate - (�w���)<BR>
     * 
     * 0�A1�A2�̂����ꂩ�̒l�B<BR>
     * @@return double
     */
    public double calcOptionSellContractQuantityTemp(int l_intReservedDate)
    {
        //�w������͈͊O�̏ꍇ(n��0�ȏ�2�ȉ��łȂ��ꍇ)
        if (l_intReservedDate < WEB3IfoReservedDateDef.T_0
            || l_intReservedDate > WEB3IfoReservedDateDef.T_2)
        {
            //0��ԋp����B
            return 0;
        }

        //�����ʃI�v�V�������������؋����s�����m�聄
        double l_dblOptionSellContractQuantityTemp = 0;

        //�敨�̏ꍇ
        if (this.isFutures() == true)
        {
            //�����ʃI�v�V�����������ʁ��؋����s�����m�聄 = 0
            l_dblOptionSellContractQuantityTemp = 0;
        }
        //�I�v�V�����̏ꍇ
        else
        {
            //�����ʃI�v�V�������������؋����s�����m�聄 = 
            //  �������ʁ��؋����s�����m�聄
            l_dblOptionSellContractQuantityTemp = 
                this.getSellQuantityTemp();
        }

        //�v�Z���������ʃI�v�V�����������ʂ����؋����s�����m�聄�ԋp����
        return l_dblOptionSellContractQuantityTemp;
    }

    /**
     * (calc�����ʃI�v�V���������ߌ��ʐ����؋����s�����m�聄)<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�����ʃI�v�V���������ߌ��ʐ����؋����s�����m�聄�v��ԋp����B<BR>
     * 
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * @@param l_intReservedDate - (�w����j<BR>
     * 
     * 0�A1�A2�̂����ꂩ�̒l�B<BR>
     * 
     * @@return double
     */
    public double calcOptionBuyOverContractQuantityTemp(int l_intReservedDate)
    {
        //�w������͈͊O�̏ꍇ(n��0�ȏ�2�ȉ��łȂ��ꍇ)
        if (l_intReservedDate < WEB3IfoReservedDateDef.T_0
            || l_intReservedDate > WEB3IfoReservedDateDef.T_2)
        {
            //0��ԋp����B
            return 0;
        }
        
        //�����ʃI�v�V���������ߌ��ʐ����؋����s�����m�聄
        double l_dblOptionBuyOverContractQuantityTemp = 0;

        //�����ʃI�v�V���������ߌ��ʐ����؋����s�����m�聄 = 
        //    Max(0, (�����ʃI�v�V�������������؋����s�����m�聄 - �����ʃI�v�V�������������؋����s�����m�聄 (n)))
        l_dblOptionBuyOverContractQuantityTemp =
            Math.max(
                0,
                (this.calcOptionBuyContractQuantityTemp(l_intReservedDate)
                    - this.calcOptionSellContractQuantityTemp(l_intReservedDate)));

        return l_dblOptionBuyOverContractQuantityTemp;
    }

    /**
     * (calc�����ʃI�v�V���������ߌ��ʐ����؋����s�����m�聄)<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�����ʃI�v�V���������ߌ��ʐ����؋����s�����m�聄�v��ԋp����B<BR>
     * 
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * @@param l_intReservedDate - (�w���)<BR>
     * 
     * 0�A1�A2�̂����ꂩ�̒l�B<BR>
     * @@return double
     */
    public double calcOptionSellOverContractQuantityTemp(int l_intReservedDate)
    {
        //�w������͈͊O�̏ꍇ(n��0�ȏ�2�ȉ��łȂ��ꍇ)
        if (l_intReservedDate < WEB3IfoReservedDateDef.T_0
            || l_intReservedDate > WEB3IfoReservedDateDef.T_2)
        {
            //0��ԋp����B
            return 0;
        }

        //�����ʃI�v�V���������ߌ��ʐ����؋����s�����m�聄
        double l_dblOptionSellOverContractQuantityTemp = 0;

        //�����ʃI�v�V���������ߌ��ʐ����؋����s�����m�聄 = 
        //    Max(0, (�����ʃI�v�V�������������؋����s�����m�聄 - �����ʃI�v�V�������������؋����s�����m�聄 (n)))
        l_dblOptionSellOverContractQuantityTemp =
            Math.max(
                0,
                (this.calcOptionSellContractQuantityTemp(l_intReservedDate)
                    - this.calcOptionBuyContractQuantityTemp(l_intReservedDate)));

        return l_dblOptionSellOverContractQuantityTemp;
    }

    /**
     * (calc�����ʔ��I�v�V�������l���؋����s�����m�聄)<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�����ʔ��I�v�V�������l���؋����s�����m�聄�v��ԋp����B<BR>
     * 
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * @@param l_intReservedDate - (�w���)<BR>
     * 
     * 0�A1�A2�̂����ꂩ�̒l�B<BR>
     * @@return double
     */
    public double calcBuyOptionValueTemp(int l_intReservedDate)
    {
        //�w������͈͊O�̏ꍇ(n��0�ȏ�2�ȉ��łȂ��ꍇ)
        if (l_intReservedDate < WEB3IfoReservedDateDef.T_0
            || l_intReservedDate > WEB3IfoReservedDateDef.T_2)
        {
            //0��ԋp����B
            return 0;
        }

        //�����ʔ��I�v�V�������l���؋����s�����m�聄
        double l_dblBuyOptionValueTemp = 0;

        //�����ʔ����ߌ��ʐ����؋����s�����m�聄
        BigDecimal l_bdOverContractTemp =
            new BigDecimal(this.calcOptionBuyOverContractQuantityTemp(l_intReservedDate));

        //�������Z�l  
        double l_dblPrice = this.getCurrentBizDateLiquidationPrice();
        if (l_dblPrice == 0) 
        {
            //�������Z�l==ZERO�̏ꍇ�A����
            l_dblPrice = this.getCurrentPrice();
        }
        //�������Z�l(BigDecimal�^) 
        BigDecimal l_bdPrice = new BigDecimal(Double.toString(l_dblPrice));
        //�w���搔
        BigDecimal l_bdUnitSize = new BigDecimal(this.getUnitSize());

        //�����ʔ��I�v�V�������l���؋����s�����m�聄 = 
        //    �����ʃI�v�V���������ߌ��ʐ����؋����s�����m�聄�~�������Z�l�~�w���搔
        BigDecimal l_bdBuyOptionValueTemp =
            l_bdOverContractTemp.multiply(l_bdPrice).multiply(l_bdUnitSize);
             
        //�����_�ȉ��؂�̂�
        l_bdBuyOptionValueTemp = l_bdBuyOptionValueTemp.setScale(0, BigDecimal.ROUND_DOWN);

        //�v�Z���������ʔ��I�v�V�������l(BigDecimal�^)��double�^�ɕϊ�����
        l_dblBuyOptionValueTemp = l_bdBuyOptionValueTemp.doubleValue();

        return l_dblBuyOptionValueTemp;
    }

    /**
     * (calc�����ʔ��I�v�V�������l���؋����s�����m�聄)<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�����ʔ��I�v�V�������l���؋����s�����m�聄�v��ԋp����B<BR>
     * 
     * (*) �v�Z�̏ڍׂ́A�u��{�݌v�v�Z�����i�؋����j.doc�v�Q�ƁB<BR>
     * @@param l_intReservedDate - (�w���)<BR>
     * 
     * 0�A1�A2�̂����ꂩ�̒l�B<BR>
     * @@return double
     */
    public double calcSellOptionValueTemp(int l_intReservedDate)
    {
        //�w������͈͊O�̏ꍇ(n��0�ȏ�2�ȉ��łȂ��ꍇ)
        if (l_intReservedDate < WEB3IfoReservedDateDef.T_0
            || l_intReservedDate > WEB3IfoReservedDateDef.T_2)
        {
            //0��ԋp����B
            return 0;
        }

        //�����ʔ��I�v�V�������l���؋����s�����m�聄
        double l_dblSellOptionValueTemp = 0;

        //�����ʔ����ߌ��ʐ����؋����s�����m�聄
        BigDecimal l_bdOverContractTemp =
            new BigDecimal(this.calcOptionSellOverContractQuantityTemp(l_intReservedDate));
        //�������Z�l  
        double l_dblPrice = this.getCurrentBizDateLiquidationPrice();
        if (l_dblPrice == 0) 
        {
            //�������Z�l==ZERO�̏ꍇ�A����
            l_dblPrice = this.getCurrentPrice();
        }
        //�������Z�l(BigDecimal�^) 
        BigDecimal l_bdPrice = new BigDecimal(Double.toString(l_dblPrice));
        //�w���搔
        BigDecimal l_bdUnitSize = new BigDecimal(this.getUnitSize());
        //�����ʔ��I�v�V�������l���؋����s�����m�聄 =
        //�����ʃI�v�V���������ߌ��ʐ����؋����s�����m�聄 �~ �������Z�l �~ �w���搔
        BigDecimal l_bdSellOptionValueTemp =
            l_bdOverContractTemp.multiply(l_bdPrice).multiply(l_bdUnitSize);
                
        //�����_�ȉ��؂�̂�
        l_bdSellOptionValueTemp = l_bdSellOptionValueTemp.setScale(0, BigDecimal.ROUND_DOWN);
        //�v�Z���������ʔ��I�v�V�������l(BigDecimal�^)��double�^�ɕϊ�����
        l_dblSellOptionValueTemp = l_bdSellOptionValueTemp.doubleValue();

        return l_dblSellOptionValueTemp;
    }
    
    /**
     * (get������������[T+0])<BR>
     * 
     * this.������������[T+0]��ԋp����B<BR>
     * @@return double
     */
    public double getCurrentBizDateBuyOrderQuantity()
    {
        return this.currentBizDateBuyOrderQuantity;
    }

    /**
     * (get������������[T+1])<BR>
     * 
     * this.������������[T+1]��ԋp����B<BR>
     * @@return double
     */
    public double getNextBizDateBuyOrderQuantity()
    {
        return this.nextBizDateBuyOrderQuantity;
    }

    /**
     * (get������������[T+0])<BR>
     * 
     * this.������������[T+0]��ԋp����B<BR>
     * @@return double
     */
    public double getCurrentBizDateSellOrderQuantity()
    {
        return this.currentBizDateSellOrderQuantity;
    }

    /**
     * (get������������[T+1])<BR>
     * 
     * this.������������[T+1]��ԋp����B<BR>
     * @@return double
     */
    public double getNextBizDateSellOrderQuantity()
    {
        return this.nextBizDateSellOrderQuantity;
    }

    /**
     * (set������������[T+0])<BR>
     * 
     * ����.������������[T+0]��this.������������[T+0]�ɃZ�b�g����B<BR>
     * @@param l_dblCurrentBizDateBuyOrderQuantity - ������������[T+0]
     */
    public void setCurrentBizDateBuyOrderQuantity(double l_dblCurrentBizDateBuyOrderQuantity)
    {
        this.currentBizDateBuyOrderQuantity = l_dblCurrentBizDateBuyOrderQuantity;
    }

    /**
     * (set������������[T+1])<BR>
     * 
     * ����.������������[T+1]��this.������������[T+1]�ɃZ�b�g����B<BR>
     * @@param l_dblNextBizDateBuyOrderQuantity - ������������[T+1]
     */
    public void setNextBizDateBuyOrderQuantity(double l_dblNextBizDateBuyOrderQuantity)
    {
        this.nextBizDateBuyOrderQuantity = l_dblNextBizDateBuyOrderQuantity;
    }

    /**
     * (set������������[T+0])<BR>
     * 
     * ����.������������[T+0]��this.������������[T+0]�ɃZ�b�g����B<BR>
     * @@param l_dblCurrentBizDateSellOrderQuantity - ������������[T+0]
     */
    public void setCurrentBizDateSellOrderQuantity(double l_dblCurrentBizDateSellOrderQuantity)
    {
        this.currentBizDateSellOrderQuantity = l_dblCurrentBizDateSellOrderQuantity;
    }

    /**
     * (set������������[T+1])<BR>
     * 
     * ����.������������[T+1]��this.������������[T+1]�ɃZ�b�g����B<BR>
     * @@param l_dblNextBizDateSellOrderQuantity - ������������[T+1]
     */
    public void setNextBizDateSellOrderQuantity(double l_dblNextBizDateSellOrderQuantity)
    {
        this.nextBizDateSellOrderQuantity = l_dblNextBizDateSellOrderQuantity;
    }

}
@
