head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.38.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoCustomerTransfer.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �敨OP�ڋq�ړ����׃N���X(WEB3IfoCustomerTransfer.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/10/21 �R�c�@@��i(FLJ) �V�K�쐬
 */
package webbroker3.ifodeposit;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;

import webbroker3.util.WEB3DateUtility;

/**
 * (�敨OP�ڋq�ړ�����)<BR>
 * �ڋq�ړ����ׂ�\���N���X�B<BR>
 * 
 * @@author Takuji Yamada (FLJ)
 */
public class WEB3IfoCustomerTransfer
{

    /**
     * �c��[T+0..2]
     */
    private double[] balances;

    /**
     * (�U�֊z[T+0])<BR>
     * 
     * �U�֊z[T+0]�B<BR>
     * �؋��������ւ̐U�ւ̓v���X�A<BR>
     * �a��������ւ̐U�ւ̓}�C�i�X�Ƃ���B<BR>
     */
    private double currentBizDateTransferAmount = 0;

    /**
     * (�U�֊z[T+1]�B)<BR>
     * 
     * �؋��������ւ̐U�ւ̓v���X�A<BR>
     * �a��������ւ̐U�ւ̓}�C�i�X�Ƃ���B<BR>
     */
    private double nextBizDateTransferAmount = 0;

    /**
     * �敨���ϑ��v[T+1]�B
     */
    private double nextBizDateFuturesCloseProfitLoss = 0;

    /**
     * �敨���ϑ��v[T+2]�B
     */
    private double next2BizDateFuturesCloseProfitLoss = 0;

    /**
     * �I�v�V������n���[T+1]�B
     */
    private double nextBizDateOptionNetAmount = 0;

    /**
     * �I�v�V������n���[T+2]�B
     */
    private double next2BizDateOptionNetAmount = 0;

    /**
     * �I�v�V���������T�Z��n���[T+1]�B
     */
    private double nextBizDateOptionBuyEstimatedNetAmount = 0;

    /**
     * �I�v�V���������T�Z��n���[T+2]�B
     */
    private double next2BizDateOptionBuyEstimatedNetAmount = 0;
    
    /**
     * �����z[T+0]�B<BR>
     * 
     *T+0�ɏ؋��������ւ̐U�ւ��s�������z�B<BR>
     */
    private double currentBizDateCashinAmount = 0;
    
    /**
     * �����z[T+1]�B<BR>
     * 
     *T+1�ɏ؋��������ւ̐U�ւ��s�������z�B<BR>
     */
    private double nextBizDateCashinAmount = 0;
    
    /**
     * �o���z[T+0]�B<BR>
     * 
     *T+0�ɗa��������ւ̐U�ւ��s�������z�B<BR>
     */
    private double currentBizDateCashoutAmount = 0;
    
    /**
     * �o���z[T+1]�B<BR>
     * 
     *T+1�ɗa��������ւ̐U�ւ��s�������z�B<BR>
     */
    private double nextBizDateCashoutAmount = 0;

    public WEB3IfoDepositCalc theWEB3IfoDepositCalc;

    /**
     * (�敨OP�ڋq�ړ�����)<BR>
     * 
     * �R���X�g���N�^�B<BR>
     * @@roseuid 4158C516026E
     */
    public WEB3IfoCustomerTransfer()
    {

    }

    /**
     * (set�c��[T+0..2])<BR>
     * 
     * ����.�c��[T+0..2]��this.�c��[T+0..2]�ɃZ�b�g����B<BR>
     * @@param l_balance - (�c��[T+0..2])<BR>
     * 
     * �c��[T+0]<BR>
     * �c��[T+1]<BR>
     * �c��[T+2]<BR>
     * �̔z��B<BR>
     * @@roseuid 41132B7301BE
     */
    public void setBalances(double[] l_balances)
    {
        balances = l_balances;
    }

    /**
     * (add�敨���ϑ��v[T+1])<BR>
     * 
     * this.�敨���ϑ��v[T+1]�Ɉ���.�敨���ϑ��v[T+1]�����Z����B<BR>
     * 
     * this.�敨���ϑ��v[T+1] = this.�敨���ϑ��v[T+1] + ����.�敨���ϑ��v[T+1] <BR>
     * @@param l_dblNextBizDateFutureCloseProfitLoss - �敨���ϑ��v[T+1]�B
     * @@roseuid 41132D8501AE
     */
    public void addNextBizDateFuturesCloseProfitLoss(double l_dblNextBizDateFutureCloseProfitLoss)
    {
        nextBizDateFuturesCloseProfitLoss
            += l_dblNextBizDateFutureCloseProfitLoss;
    }

    /**
     * (add�敨���ϑ��v[T+2])<BR>
     * 
     * this.�敨���ϑ��v[T+2]�Ɉ���.�敨���ϑ��v[T+2]�����Z����B<BR>
     * 
     * this.�敨���ϑ��v[T+2] = this.�敨���ϑ��v[T+2] + ����.�敨���ϑ��v[T+2] <BR>
     * @@param l_dblNext2BizDateFutureCloseProfitLoss - �敨���ϑ��v[T+2]�B
     */
    public void addNext2BizDateFuturesCloseProfitLoss(double l_dblNext2BizDateFutureCloseProfitLoss)
    {
        next2BizDateFuturesCloseProfitLoss
            += l_dblNext2BizDateFutureCloseProfitLoss;
    }

    /**
     * (add�I�v�V������n���[T+1])<BR>
     * 
     * this.�I�v�V������n���[T+1]�Ɉ���.�I�v�V������n���[T+1]�����Z����B<BR>
     * 
     * this.�I�v�V������n���[T+1] = this.�I�v�V������n���[T+1] + 
     * ����.�I�v�V������n���[T+1]<BR>
     * @@param l_dblNextOptionNetAmount - �I�v�V������n���[T+1]�B
     * @@roseuid 41132DAB01BE
     */
    public void addNextBizDateOptionNetAmount(double l_dblNextOptionNetAmount)
    {
        nextBizDateOptionNetAmount += l_dblNextOptionNetAmount;
    }

    /**
     * (add�I�v�V������n���[T+2])<BR>
     * 
     * this.�I�v�V������n���[T+2]�Ɉ���.�I�v�V������n���[T+2]�����Z����B<BR>
     * 
     * this.�I�v�V������n���[T+2] = this.�I�v�V������n���[T+2] + 
     * ����.�I�v�V������n���[T+2]<BR>
     * @@param l_dblNext2OptionNetAmount - �I�v�V������n���[T+2]�B
     */
    public void addNext2BizDateOptionNetAmount(double l_dblNext2OptionNetAmount)
    {
        next2BizDateOptionNetAmount += l_dblNext2OptionNetAmount;
    }


    /**
     * (add�I�v�V���������T�Z��n���[T+1])<BR>
     * 
     * this.�I�v�V���������T�Z��n���[T+1]�Ɉ���.�����I�v�V�����T�Z��n���[T+1]�����Z
     * ����B<BR>
     * 
     * this.�I�v�V���������T�Z��n���[T+1] = this.�I�v�V���������T�Z��n���[T+1] + 
     * ����.�I�v�V���������T�Z��n���[T+1]<BR>
     * @@param l_dblNextBizDateOptionBuyEstimateNetAmount - 
     * �I�v�V���������T�Z��n���[T+1]
     * @@roseuid 41132DCB03A2
     */
    public void addNextBizDateOptionBuyEstimatedNetAmount(double l_dblNextBizDateOptionBuyEstimatedNetAmount)
    {
        nextBizDateOptionBuyEstimatedNetAmount
            += l_dblNextBizDateOptionBuyEstimatedNetAmount;
    }

    /**
     * (add�I�v�V���������T�Z��n���[T+2])<BR>
     * 
     * this.�I�v�V���������T�Z��n���[T+2]�Ɉ���.�I�v�V���������T�Z��n���[T+2]�����Z
     * ����B<BR>
     * 
     * this.�I�v�V���������T�Z��n���[T+2] = this.�I�v�V���������T�Z��n���[T+2] + 
     * ����.�I�v�V���������T�Z��n���[T+2]<BR>
     * @@param l_dblNext2BizDateOptionBuyEstimatedNetAmount - 
     * �I�v�V���������T�Z��n���[T+2]
     * @@roseuid 4119BDEF034E
     */
    public void addNext2BizDateOptionBuyEstimatedNetAmount(double l_dblNext2BizDateOptionBuyEstimatedNetAmount)
    {
        next2BizDateOptionBuyEstimatedNetAmount
            += l_dblNext2BizDateOptionBuyEstimatedNetAmount;
    }

    /**
     * (get�c��)<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�ɑΉ�����c����ԋp����B<BR>
     * 
     * �P�j�@@�����̎w����`�F�b�N���s���B<BR>
     * �@@�@@�@@�w������͈͊O�̏ꍇ(n��0�ȏ�2�ȉ��łȂ��ꍇ)�A0��ԋp����B<BR>
     * 
     * �Q�j�@@�c���̎擾���s���B<BR>
     * �@@�@@�@@n == 0�̏ꍇ�Athis.�c��[T+0..2][0]��ԋp����B<BR>
     * �@@�@@�@@n == 1�̏ꍇ�Athis.�c��[T+0..2][1]��ԋp����B<BR>
     * �@@�@@�@@n == 2�̏ꍇ�Athis.�c��[T+0..2][2]��ԋp����B<BR>
     * @@param l_intReservedDate - (�w���)<BR>
     * 
     * 0�A1�A2�̂����ꂩ�̒l�B<BR>
     * @@return double
     * @@roseuid 41132BB803B2
     */
    public double getBalance(int l_intReservedDate)
    {
        switch (l_intReservedDate)
        {
            case 0 :
                return balances[0];
            case 1 :
                return balances[1];
            case 2 :
                return balances[2];
            default :
                return 0.0;
        }
    }

    /**
     * (get�U�֊z)<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�U�֊z�v��ԋp����B<BR>
     * 
     * �P�j�@@�����̎w����`�F�b�N���s���B<BR>
     * �@@�@@�@@�w������͈͊O�̏ꍇ(n��0�ȏ�2�ȉ��łȂ��ꍇ)�A0��ԋp����B<BR>
     * 
     * �Q�j�@@n == 0�̏ꍇ(����.�w��� == 0)<BR>
     * �@@�@@�@@this.get�U�֊z[T+0]( )��ԋp����B<BR>
     * 
     * �R�j�@@n == 1�A�܂���2�̏ꍇ(����.�w��� != 0)<BR>
     * �@@�@@�@@this.get�U�֊z[T+0]( )<BR>
     *       + this.get�U�֊z[T+1]( )��ԋp����B<BR>
     * @@param l_intReservedDate - (�w���)<BR>
     * 0�A1�A2�̂����ꂩ�̒l�B<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils.Double
     * @@roseuid 414047F6038F
     */
    public double getTransferAmount(int l_intReservedDate)
    {
        switch (l_intReservedDate)
        {
            case 0 :
                return getCurrentBizDateTransferAmount();
            case 1 :
            case 2 :
                return (
                    getCurrentBizDateTransferAmount()
                        + getNextBizDateTransferAmount());
            default :
                return 0.0;
        }
    }

    /**
     * (get�U�֊z[T+0])<BR>
     * 
     * this.�U�֊z[T+0]��ԋp����B<BR>
     * @@return double
     * @@roseuid 412C79A402EC
     */
    public double getCurrentBizDateTransferAmount()
    {
        return currentBizDateTransferAmount;
    }

    /**
     * (get�U�֊z[T+1])<BR>
     * 
     * this.�U�֊z[T+1]��ԋp����B<BR>
     * @@return double
     * @@roseuid 414047B000EE
     */
    public double getNextBizDateTransferAmount()
    {
        return nextBizDateTransferAmount;
    }

    /**
     * (get�敨���ϑ��v)<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�敨���ϑ��v�v��ԋp����B<BR>
     * 
     * �P�j�@@�����̎w����`�F�b�N���s���B<BR>
     * �@@�@@�@@�w������͈͊O�̏ꍇ(n��1�ȏ�2�ȉ��łȂ��ꍇ)�A0��ԋp����B<BR>
     * 
     * �Q�j�@@n == 1�̏ꍇ <BR>
     *       this.get�敨���ϑ��v[T+1]( )��ԋp����B<BR> 
     *
     * �R�j�@@n == 2�̏ꍇ <BR>
     *       this.get�敨���ϑ��v[T+1]( )+this.get�敨���ϑ��v[T+2]( )��ԋp����B<BR>
     * 
     * @@param l_intReservedDate - (�w���)<BR>
     * 
     * 1�A2�̂����ꂩ�̒l�B<BR>
     * @@return double
     * @@roseuid 411089CC02D6
     */
    public double getFuturesCloseProfitLoss(int l_intReservedDate)
    {
        switch (l_intReservedDate)
        {
            case 1 :
                return getNextBizDateFuturesCloseProfitLoss();
            case 2 :
                return (getNextBizDateFuturesCloseProfitLoss() + 
                          getNext2BizDateFuturesCloseProfitLoss());
            default :
                return 0.0;
        }
    }

    /**
     * (get�敨���ϑ��v[T+1])<BR>
     * 
     * this.�敨���ϑ��v[T+1]��ԋp����B<BR>
     * @@return double
     * @@roseuid 412C78C70078
     */
    public double getNextBizDateFuturesCloseProfitLoss()
    {
        return nextBizDateFuturesCloseProfitLoss;
    }

    /**
     * (get�敨���ϑ��v[T+2])<BR>
     * 
     * this.�敨���ϑ��v[T+2]��ԋp����B<BR>
     * @@return double
     */
    public double getNext2BizDateFuturesCloseProfitLoss()
    {
        return next2BizDateFuturesCloseProfitLoss;
    }

    /**
     * (get�I�v�V������n���)<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�I�v�V������n����v��ԋp����B<BR>
     * 
     * �P�j�@@�����̎w����`�F�b�N���s���B<BR>
     * �@@�@@�@@�w������͈͊O�̏ꍇ(n��1�ȏ�2�ȉ��łȂ��ꍇ)�A0��ԋp����B<BR>
     * 
     * �Q�j�@@n == 1�̏ꍇ <BR>
     *     �@@this.get�I�v�V������n���[T+1]( )��ԋp����B <BR>
     *
     * �R�j�@@n == 2�̏ꍇ <BR>
     *     �@@this.get�I�v�V������n���[T+1]( )�@@+�@@this.get�I�v�V������n���[T+2]( )��ԋp����B<BR>
     * 
     * @@param l_intReservedDate - (�w���)<BR>
     * 
     * 1�A2�̂����ꂩ�̒l�B<BR>
     * @@return double
     * @@roseuid 412C79440349
     */
    public double getOptionNetAmount(int l_intReservedDate)
    {
        switch (l_intReservedDate)
        {
            case 1 :
                return getNextBizDateOptionNetAmount();
            case 2 :
                return (getNextBizDateOptionNetAmount() + 
                          getNext2BizDateOptionNetAmount());
            default :
                return 0.0;
        }
    }

    /**
     * (get�I�v�V������n���[T+1])<BR>
     * 
     * this.�I�v�V������n���[T+1]��ԋp����B<BR>
     * @@return double
     * @@roseuid 41132C9B0085
     */
    public double getNextBizDateOptionNetAmount()
    {
        return nextBizDateOptionNetAmount;
    }

    /**
     * (get�I�v�V������n���[T+2])<BR>
     * 
     * this.�I�v�V������n���[T+2]��ԋp����B<BR>
     * @@return double
     */
    public double getNext2BizDateOptionNetAmount()
    {
        return next2BizDateOptionNetAmount;
    }

    /**
     * (get�I�v�V���������T�Z��n���)<BR>
     * 
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�u�I�v�V���������T�Z��n����v��ԋp����B<BR>
     * 
     * �P�j�@@�����̎w����`�F�b�N���s���B<BR>
     * �@@�@@�@@�w������͈͊O�̏ꍇ(n��1�ȏ�2�ȉ��łȂ��ꍇ)�A0��ԋp����B<BR>
     * 
     * �Q�j�@@n == 1�̏ꍇ(����.�w��� == 1)<BR>
     * �@@�@@�@@�@@this.get�I�v�V���������T�Z��n���[T+1]( )��ԋp����B<BR>
     * 
     * �R�j�@@n == 2�̏ꍇ(����.�w��� == 2)<BR>
     * �@@�@@�@@�@@this.get�I�v�V���������T�Z��n���[T+1]( )<BR>
     *       + this.get�I�v�V���������T�Z��n���[T+2]( )��ԋp����B<BR>
     * @@param l_indReservedDate - (�w���)<BR>
     * 1�A2�̂����ꂩ�̒l�B<BR>
     * @@return double
     * @@roseuid 412C6A2600C2
     */
    public double getOptionBuyEstimatedNetAmount(int l_intReservedDate)
    {
        switch (l_intReservedDate)
        {
            case 1 :
                return getNextBizDateOptionBuyEstimatedNetAmount();
            case 2 :
                return (
                    getNextBizDateOptionBuyEstimatedNetAmount()
                        + getNext2BizDateOptionBuyEstimatedNetAmount());
            default :
                return 0.0;
        }
    }

    /**
     * (get�I�v�V���������T�Z��n���[T+1])<BR>
     * 
     * this.�I�v�V���������T�Z��n���[T+1]��ԋp����B<BR>
     * @@return double
     * @@roseuid 41132CB70373
     */
    public double getNextBizDateOptionBuyEstimatedNetAmount()
    {
        return nextBizDateOptionBuyEstimatedNetAmount;
    }

    /**
     * (get�I�v�V���������T�Z��n���[T+2])<BR>
     * 
     * this.�I�v�V���������T�Z��n���[T+2]��ԋp����B<BR>
     * @@return double
     * @@roseuid 4118604801D0
     */
    public double getNext2BizDateOptionBuyEstimatedNetAmount()
    {
        return next2BizDateOptionBuyEstimatedNetAmount;
    }
    
    /**
     * (add�����z)<BR>
     * 
     * ��n�������ł��邩�ɂ��ƂÂ��A�Y����������z�̉��Z�������s���B<BR>
     * 
     * �؋��������ւ̐U�ւ̏ꍇ(����.������� == 
     * (�h�U�֒����i�a������犔��؋����j�h�j)�̂݁A<BR>
     * �ȉ��̐U�֊z�̉��Z���s���B<BR>
     * 
     * �������U�ւ̏ꍇ(����.��n�� == ����.�c�Ɠ�[T+0])<BR>
     * �@@�@@this.�����z[T+0] += ����.�U�֊z<BR>
     * 
     * �������U�ւ̏ꍇ(����.��n�� != ����.�c�Ɠ�[T+0])<BR>
     * �@@�@@this.�����z[T+1] += ����.�U�֊z<BR>
     * @@param l_orderType - (�������)
     * @@param l_dblTransferAmount - �U�֊z�B
     * @@param l_datDeliveryDate - ��n���B
     * @@param l_datCurrentBizDate - �c�Ɠ�[T+0]�B
     */
    public void addCashinAmount(
        OrderTypeEnum l_orderType,
        double l_dblTransferAmount,
        Date l_datDeliveryDate,
        Date l_datCurrentBizDate)
    {
        if (OrderTypeEnum
            .FROM_DEPOSIT_AMOUNT_MARGIN
            .equals(l_orderType))
        {
            if (WEB3DateUtility
                .compareToDay(l_datDeliveryDate, l_datCurrentBizDate)
                == 0)
            {
                currentBizDateCashinAmount += l_dblTransferAmount;
            } else
            {
                nextBizDateCashinAmount += l_dblTransferAmount;
            }
        }
    }
    
    /**
     * (add�o���z)<BR>
     * 
     * ��n�������ł��邩�ɂ��ƂÂ��A�Y������o���z�̉��Z�������s���B<BR>
     * 
     * �a��������ւ̐U�ւ̏ꍇ(����.������� == 
     * (�h�U�֒����i����؋�������a����j�h�j)�̂݁A<BR>
     * �ȉ��̐U�֊z�̌��Z���s���B<BR>
     * 
     * �������U�ւ̏ꍇ(����.��n�� == ����.�c�Ɠ�[T+0])<BR>
     * �@@�@@this.�o���z[T+0] += ����.�U�֊z<BR>
     * 
     * �������U�ւ̏ꍇ(����.��n�� != ����.�c�Ɠ�[T+0])<BR>
     * �@@�@@this.�o���z[T+1] += ����.�U�֊z<BR>
     * @@param l_orderType - (�������)
     * @@param l_dblTransferAmount - �U�֊z�B
     * @@param l_datDeliveryDate - ��n���B
     * @@param l_datCurrentBizDate - �c�Ɠ�[T+0]�B
     */
    public void addCashoutAmount(
        OrderTypeEnum l_orderType,
        double l_dblTransferAmount,
        Date l_datDeliveryDate,
        Date l_datCurrentBizDate)
    {
        if (OrderTypeEnum
            .MARGIN_FROM_DEPOSIT_AMOUNT
            .equals(l_orderType))
        {
            if (WEB3DateUtility
                .compareToDay(l_datDeliveryDate, l_datCurrentBizDate)
                == 0)
            {
                currentBizDateCashoutAmount += l_dblTransferAmount;
            } else
            {
                nextBizDateCashoutAmount += l_dblTransferAmount;
            }
        }
    }
    
    /**
     * (get�����z[T+0])<BR>
     * 
     * this.�����z[T+0]��ԋp����B<BR>
     * @@return double
     */
    public double getCurrentBizDateCashinAmount()
    {
        return currentBizDateCashinAmount;
    }
    
    /**
     * (get�����z[T+1])<BR>
     * 
     * this.�����z[T+1]��ԋp����B<BR>
     * @@return double
     */
    public double getNextBizDateCashinAmount()
    {
        return nextBizDateCashinAmount;
    }
    
    /**
     * (get�o���z[T+0])<BR>
     * 
     * this.�o���z[T+0]��ԋp����B<BR>
     * @@return double
     */
    public double getCurrentBizDateCashoutAmount()
    {
        return currentBizDateCashoutAmount;
    }
    
    /**
     * (get�o���z[T+1])<BR>
     * 
     * this.�o���z[T+1]��ԋp����B<BR>
     * @@return double
     */
    public double getNextBizDateCashoutAmount()
    {
        return nextBizDateCashoutAmount;
    }
    
    /**
     * (set�U�֊z[T+0])<BR>
     * 
     * ����.�U�֊z[T+0]��this.�U�֊z[T+0]�ɃZ�b�g����B<BR>
     * @@param l_dblCurrentBizDateTransferAmount - (�U�֊z[T+0]�B)<BR>
     */
    public void setCurrentBizDateTransferAmount(double l_dblCurrentBizDateTransferAmount)
    {
        currentBizDateTransferAmount = l_dblCurrentBizDateTransferAmount;
    }
    
    /**
     * (set�U�֊z[T+1])<BR>
     * 
     * ����.�U�֊z[T+1]��this.�U�֊z[T+1]�ɃZ�b�g����B<BR>
     * @@param l_dblNextBizDateTransferAmount - (�U�֊z[T+1]�B)<BR>
     */
    public void setNextBizDateTransferAmount(double l_dblNextBizDateTransferAmount)
    {
        nextBizDateTransferAmount = l_dblNextBizDateTransferAmount;
    }
    
}
@
