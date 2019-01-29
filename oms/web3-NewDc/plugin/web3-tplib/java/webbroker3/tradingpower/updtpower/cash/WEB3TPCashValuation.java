head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.36.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPCashValuation.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3TPAccumulatedCapitalGainTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2004/08/02 �x�� �a��(FLJ) �V�K�쐬
                   2006/09/11 ���G�� (���u) ���f��No.012
Revision History : 2009/12/15 �����F ���f��No.414 417
Revision History : 2010/01/15 ���g ���f��No.442
*/

package webbroker3.tradingpower.updtpower.cash;

import java.math.BigDecimal;
import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.updtpower.WEB3TPAccountInfo;
import webbroker3.tradingpower.updtpower.WEB3TPAssetValuation;
import webbroker3.tradingpower.updtpower.WEB3TPNewOrderSpec;
import webbroker3.tradingpower.util.ToStringUtils;

/**
 * (������)<BR>
 * �������̐��ڂ�\�����܂��B
 */
public class WEB3TPCashValuation
    extends WEB3TPAssetValuation
{
    /**
     * (�a���)
     */
    private WEB3TPCashBalance cashBalance;
    
    /**
     * (������)
     */
    private WEB3TPTransactionAmount transactionAmount;
    
    /**
     * (�S����)
     */
    private WEB3TPRestraint restraint;

    /**
     * @@roseuid 4104B4E600A6
     */
    public WEB3TPCashValuation()
    {
    }

    /**
     * (create������)<BR>
     * �������C���X�^���X�𐶐����ԋp����B<BR>
     * <BR>
     * �P�j�@@������ϐ��ɃZ�b�g����B<BR>
     * �E�ڋq����������.�ڋq����<BR>
     * �E�v�Z����������.�v�Z����<BR>
     * �E���������e=����.���������e<BR>
     * �Q�j�@@�a����������ϐ��ɃZ�b�g����B
     * <BR>
     * �R�j�@@�������������ϐ��ɃZ�b�g����B
     * <BR>
     * �S�j�@@�S�����������ϐ��ɃZ�b�g����B
     * <BR>
     * @@param l_accountInfo - (�ڋq����)
     * @@param l_calcCondition - (�v�Z����)
     * @@param l_newOrderSpecs - (���������e)
     * @@return WEB3TPCashValuation
     * @@roseuid 40F3CCE20030
     */
    public static WEB3TPCashValuation create(WEB3TPAccountInfo l_accountInfo,
                                             WEB3TPCalcCondition l_calcCondition,
                                             WEB3TPNewOrderSpec[] l_newOrderSpecs)
    {
        WEB3TPCashValuation l_instance = new WEB3TPCashValuation();
        l_instance.setAccountInfo(l_accountInfo);
        l_instance.setCalcCondition(l_calcCondition);
        l_instance.setNewOrderSpecs(l_newOrderSpecs);

        l_instance.setTransactionAmount(WEB3TPTransactionAmount.create(l_instance));
        l_instance.setCashBalance(WEB3TPCashBalance.create(l_instance));
        l_instance.setRestraint(WEB3TPRestraint.create(l_instance));
        return l_instance;
    }

    /**
     * (calc�a����c��)<BR>
     * �a���.calc�a����c��()���ĂԁB<BR>
     * @@param l_datDate - (�w���)
     * @@return double
     * @@roseuid 40B3E7F700CD
     */
    public double calcCashBalance(Date l_datDate)
    {
        return cashBalance.calcCashBalance(l_datDate);
    }

    /**
     * (calc�����������)<BR>
     * ������.calc�������()���ĂԁB<BR>
     * @@param l_datDate - (�w���)
     * @@return double
     * @@roseuid 40B4035F036D
     */
    public double calcTodaysUnexecutedTotal(Date l_datDate)
    {
        return transactionAmount.calcTodaysUnexecutedTotal(l_datDate);
    }

    /**
     * (calc�������ϑ��)<BR>
     * ������.calc���ϑ��()���ĂԁB<BR>
     * @@param l_datDate - (�w���)
     * @@return double
     * @@roseuid 40C81E3B0349
     */
    public double calcTodaysExecutedTotal(Date l_datDate)
    {
        return transactionAmount.calcTodaysExecutedTotal(l_datDate);
    }

    /**
     * (calc���̑��S����)<BR>
     * �S����.calc���̑��S����()���ĂԁB<BR>
     * @@param l_datDate - (�w���)
     * @@return double
     * @@roseuid 40B403890050
     */
    public double calcOtherRestraintsTotal(Date l_datDate)
    {
        return restraint.calcOtherRestraints(l_datDate);
    }

    /**
     * (calc�a����S�ۏo���S����)<BR> 
     * <BR>
     * ����.�w����̗a����S�ۏo���S������ԋp����B<BR> 
     * <BR>
     * �P�j����.�w����̗a����S�ۏo���S�������擾����B<BR> 
     * <BR>
     * �@@[a.�،��S�ۃ��[�����{�ڋq�̏ꍇ] <BR>
     * �@@(this.get�]�͌v�Z����().is�،��S�ۃ��[���敪 == true)<BR> 
     * �@@�@@�|"�a����S�ۏo���S����" = this.�S����.calc�a����S�ۏo���S����(:Date = ����.�w���)<BR> 
     * <BR>
     * �@@[a.�ȊO(�،��S�ۃ��[�������{�j�̏ꍇ] <BR>
     * �@@�@@�|"�a����S�ۏo���S����" = 0 <BR>
     * <BR>
     * �Q�j�擾�����a����S�ۏo���S������ԋp����B <BR>
     * <BR>
     * �@@�ԋp�l�F"�a����S�ۏo���S����" <BR>
     * <BR>
     * @@param l_datDate - (�w���)
     * @@return double
     */
    public double caleCashDepositRestraint(Date l_datDate)
    {
        //[a.�،��S�ۃ��[�����{�ڋq�̏ꍇ]
        //(this.get�]�͌v�Z����().is�،��S�ۃ��[���敪 == true)
        //�|"�a����S�ۏo���S����" = this.�S����.calc�a����S�ۏo���S����(:Date = ����.�w���)
        boolean l_blnSecuredLoanSecAccOpenDiv = 
            this.getCalcCondition().isSecuredLoanSecAccOpenDiv();
        
        double l_dblCashDepositRestraint = 0;
        if (l_blnSecuredLoanSecAccOpenDiv)
        {
            l_dblCashDepositRestraint= 
                this.restraint.calcCashDepositRestraint(l_datDate);
        }
        //[a.�ȊO(�،��S�ۃ��[�������{�j�̏ꍇ]
        //�|"�a����S�ۏo���S����" = 0 
        //�擾�����a����S�ۏo���S������ԋp����B
        return l_dblCashDepositRestraint;
    }
    
    /**
     * (calc�o���z)<BR>
     * ������.calc�o���z()���ĂԁB<BR>
     * @@param l_datDate - (�w���)
     * @@return double
     */
    public double calcCashOut(Date l_datDate)
    {
        return transactionAmount.calcCashOut(l_datDate);
    }
    
    /**
     * (get�a���)<BR>
     * �a�����Ԃ��B<BR>
     * @@return webbroker3.tradingpower.updtpower.cash.WEB3TPCashBalance
     * @@roseuid 4100E5740059
     */
    public WEB3TPCashBalance getCashBalance()
    {
        return cashBalance;
    }

    /**
     * (set�a���)<BR>
     * ������a����ɃZ�b�g����B<BR>
     * @@param l_cashBalance - (�a���)
     * @@roseuid 4100E57900F5
     */
    public void setCashBalance(WEB3TPCashBalance l_cashBalance)
    {
        cashBalance = l_cashBalance;
    }

    /**
     * (get������)<BR>
     * ��������Ԃ��B<BR>
     * @@return webbroker3.tradingpower.updtpower.cash.WEB3TPTransactionAmount
     * @@roseuid 4100E58F0105
     */
    public WEB3TPTransactionAmount getTransactionAmount()
    {
        return transactionAmount;
    }

    /**
     * (set������)<BR>
     * �������������ɃZ�b�g����B<BR>
     * @@param l_transactionAmount - �i�������j
     * @@roseuid 4100E59E0105
     */
    public void setTransactionAmount(WEB3TPTransactionAmount l_transactionAmount)
    {
        transactionAmount = l_transactionAmount;
    }

    /**
     * (get�S����)<BR>
     * �S������Ԃ��B<BR>
     * @@return webbroker3.tradingpower.updtpower.cash.WEB3TPRestraint
     * @@roseuid 4100E5A60124
     */
    public WEB3TPRestraint getRestraint()
    {
        return restraint;
    }

    /**
     * (set�S����)<BR>
     * �������S�����ɃZ�b�g����B<BR>
     * @@param l_restraint - �i�S�����j
     * @@roseuid 4100E58F01FF
     */
    public void setRestraint(WEB3TPRestraint l_restraint)
    {
        restraint = l_restraint;
    }

    /**
     * (do���������[�h)<BR>
     * �����֘A�̃f�[�^�����ׂă��[�h����B<BR>
     * <BR>
     * �ȉ��̏��Ń��[�h���\�b�h���ĂԁB<BR>
     * ������.do�����񃍁[�h()<BR>
     * �a���.do�a������[�h()<BR>
     * �S����.do���̑��S�������[�h()<BR>
     * @@roseuid 40C7E97300E8
     */
    public void loadAll()
    {
        transactionAmount.setTodaysEquityOrders(this.getTodaysEquityOrders());
        transactionAmount.load();
        cashBalance.setTodaysEquityOrders(this.getTodaysEquityOrders());
        cashBalance.load();
        restraint.setTodaysEquityOrders(this.getTodaysEquityOrders());
        restraint.load();
    }

    /**
     * (calc�o���S����)<BR>
     * �@@�����o���z���擾����B�@@ <BR>
     * �o���z = ABS(this.������.calc�����o���z�i�j)<BR>
     * <BR>
     * �A�����a����c���iT+1�j(*)���v�Z����B <BR>
     * (*)T+1�����_�̊m��a���(�ڋq����c��+�ۏ؋��c��)�ɁA���������������������l <BR>
     * <BR>
     * [�v�Z��] <BR>
     * �����a����c��(T+1) <BR>
     * = �����D�a����c��(T+1) - �����D�������ϑ��(T+1) - <BR>
     * �@@�����D�����������(T+1) - �����D�������������S����(T+1) <BR>
     * <BR>
     * <BR>
     * �BT+1�̒����_�̊m��M�p�ۏ؋��c�����擾����B <BR>
     * <BR>
     * �m��M�p�ۏ؋��c��(T+1) =this.�a���.calc�m��ۏ؋�(:Date = T+1) <BR>
     * <BR>
     * <BR>
     * �C�ڋq����c������M�p�ۏ؋��c���ւ̓���(T+0)�����n���܂ł̐U�֊z�̍��v(�ȉ��A�U�֊z)���擾����B <BR>
     * <BR>
     * �U�֊z�@@= this.������. calc�����ȍ~�a����ۏ؋��Ԃ̐U�֊z() <BR>
     * <BR>
     * �D�����ڋq����c�����v�Z����B <BR>
     * �m�v�Z���n <BR>
     * �����ڋq����c��(T+1) = �����a����c��(T+1) + �U�֊z - �m��M�p�ۏ؋��c��(T+1) <BR>
     * <BR>
     * �E�������ڋq����c��(T+1)��0 �ȏ�̏ꍇ�� <BR>
     * �o���S���� �� 0<BR>
     * �������ڋq����c��(T+1)��0 ��菬�����ȉ��̏ꍇ�� <BR>
     * �o���S���� �� MIN(�@@�Ōv�Z�����o���z�AABS(�����ڋq����c��(T+1)))<BR>
     * @@param l_dblCashBalance - (�a����c��(T+1))<BR>
     * @@param l_dblUnexecutedAmount - (�������(T+1))<BR>
     * @@param l_dblExecutedAmount - (���ϑ��(T+1))<BR>
     * @@param l_dblTodayDepFundRestraint - (���������Ώۖ����S����(T+1))<BR>
     * @@return double
     * @@throws WEB3BaseException
     */
    public double calcCashoutRestraint(
        double l_dblCashBalance, double l_dblUnexecutedAmount, double l_dblExecutedAmount, double l_dblTodayDepFundRestraint)
        throws WEB3BaseException
    {
        double l_dblCashoutRestraint = 0;

        //�@@�����o���z���擾����B
        //�o���z = ABS(this.������.calc�����o���z�i�j)
        BigDecimal l_bdCashoutAmount = new BigDecimal(Math.abs(transactionAmount.clacNextBizDateCashoutAmount()) + "");

        //�A�����a����c���iT+1�j(*)���v�Z����B
        //(*)T+1�����_�̊m��a���(�ڋq����c��+�ۏ؋��c��)�ɁA���������������������l
        //[�v�Z��]
        //�����a����c��(T+1)
        //= �����D�a����c��(T+1) - �����D�������ϑ��(T+1) -
        //�����D�����������(T+1) - �����D�������������S����(T+1)
        BigDecimal l_bdCashBalance = new BigDecimal(l_dblCashBalance + "");
        BigDecimal l_bdUnexecutedAmount = new BigDecimal(l_dblUnexecutedAmount + "");
        BigDecimal l_bdExecutedAmount = new BigDecimal(l_dblExecutedAmount + "");
        BigDecimal l_bdOtherRestraint = new BigDecimal(l_dblTodayDepFundRestraint + "");
        BigDecimal l_bdActualAccountBalance =
            l_bdCashBalance.subtract(l_bdUnexecutedAmount).subtract(
                l_bdExecutedAmount).subtract(l_bdOtherRestraint);

        //�BT+1�̒����_�̊m��M�p�ۏ؋��c�����擾����B
        //�m��M�p�ۏ؋��c��(T+1) =this.�a���.calc�m��ۏ؋�(:Date = T+1)
        Date l_datBizDate = this.getCalcCondition().getBizDate(WEB3TPSpecifiedPointDef.T_1);
        BigDecimal l_bdMarginCashBalance = new BigDecimal(this.cashBalance.calcFixedDeposit(l_datBizDate) + "");

        //�C�ڋq����c������M�p�ۏ؋��c���ւ̓���(T+0)�����n���܂ł̐U�֊z�̍��v(�ȉ��A�U�֊z)���擾����B
        //�U�֊z�@@= this.������. calc�����ȍ~�a����ۏ؋��Ԃ̐U�֊z()
        BigDecimal l_bdTransferBalance = new BigDecimal(this.transactionAmount.calcTodayMarginDepositTransferAmount() + "");

        //�D�����ڋq����c�����v�Z����B
        //�����ڋq����c��(T+1) = �����a����c��(T+1) + �U�֊z - �m��M�p�ۏ؋��c��(T+1)
        BigDecimal l_bdActualCashBalance = l_bdActualAccountBalance.add(l_bdTransferBalance).subtract(l_bdMarginCashBalance);

        //�E�������ڋq����c��(T+1)��0 �ȏ�̏ꍇ��
        //�o���S���� �� 0
        //�������ڋq����c��(T+1)��0 ��菬�����ȉ��̏ꍇ��
        //�o���S���� �� MIN(�@@�Ōv�Z�����o���z�AABS(�����ڋq����c��(T+1)))
        if (l_bdActualCashBalance.doubleValue() >= 0)
        {
            l_dblCashoutRestraint = 0;
        }
        else
        {
            l_dblCashoutRestraint =
                Math.min(
                    l_bdCashoutAmount.doubleValue(),
                    Math.abs(l_bdActualCashBalance.doubleValue()));
        }
        //�I�u�H�Ōv�Z�����o���S�����v��ԋp����B
        return l_dblCashoutRestraint;
    }

    /**
     * ���̃I�u�W�F�N�g�̕�����\����Ԃ��B<BR>
     * 
     * @@return String
     */
    public String toString()
    {
        return ToStringUtils.newToStringBuilder(this)
            .append("cashBalance", getCashBalance())
            .append("transactionAmount", getTransactionAmount())
            .append("restraint", getRestraint())
            .toString();
            
    }            
            


}
@
