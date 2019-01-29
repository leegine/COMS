head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.34.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPCashValuationAfterRepay.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ԍό㑍����(WEB3TPCashValuationAfterRepay.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/11 nakazato(ACT) �V�K�쐬
*/

package webbroker3.tradingpower.updtpower.cash;

import java.util.Date;

import webbroker3.tradingpower.updtpower.WEB3TPAccountInfo;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.updtpower.WEB3TPNewOrderSpec;
import webbroker3.util.WEB3LogUtility;

/**
 * (�ԍό㑍����)
 */
public class WEB3TPCashValuationAfterRepay extends WEB3TPCashValuation
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPCashValuationAfterRepay.class);

    /**
     * @@roseuid 41E383D50326
     */
    public WEB3TPCashValuationAfterRepay()
    {
    }

    /**
     * (static���\�b�h)(create�ԍό㑍����)<BR>
     * <BR>
     * �ԍό㑍�����C���X�^���X�𐶐����ԋp����B<BR>
     * <BR>
     * �P�j�@@������ϐ��ɃZ�b�g����B<BR>
     * �E�ڋq����������.�ڋq����<BR>
     * �E�v�Z����������.�v�Z����<BR>
     * �E���������e=����.���������e<BR>
     * �Q�j�@@�a����������ϐ��ɃZ�b�g����B
     * <BR>
     * �R�j�@@�ԍό�������������ϐ��ɃZ�b�g����B
     * <BR>
     * �S�j�@@�S�����������ϐ��ɃZ�b�g����B
     * <BR>
     * @@param l_accountInfo - (�ڋq����)
     * @@param l_calcCondition - (�]�͌v�Z����)
     * @@param l_newOrderSpecs - (���������e)
     * @@return webbroker3.tradingpower.updtpower.cash.WEB3TPCashValuationAfterRepay
     * @@roseuid 41C91CB0003E
     */
    public static WEB3TPCashValuationAfterRepay createWEB3TPCashValuationAfterRepay(
        WEB3TPAccountInfo l_accountInfo,
        WEB3TPCalcCondition l_calcCondition,
        WEB3TPNewOrderSpec[] l_newOrderSpecs)
    {
        final String STR_METHOD_NAME =
            "createWEB3TPCashValuationAfterRepay(WEB3TPAccountInfo, WEB3TPCalcCondition, WEB3TPNewOrderSpec[])";
        log.entering(STR_METHOD_NAME);

        WEB3TPCashValuationAfterRepay l_instance = new WEB3TPCashValuationAfterRepay();
        l_instance.setAccountInfo(l_accountInfo);
        l_instance.setCalcCondition(l_calcCondition);
        l_instance.setNewOrderSpecs(l_newOrderSpecs);

        l_instance.setTransactionAmount(
            WEB3TPTransactionAmountAfterRepay.createWEB3TPTransactionAmountAfterRepay(l_instance));
        l_instance.setCashBalance(WEB3TPCashBalance.create(l_instance));
        l_instance.setRestraint(WEB3TPRestraint.create(l_instance));

        log.exiting(STR_METHOD_NAME);
        return l_instance;
    }

    /**
     * (do�ԍό㑍�������[�h)<BR>
     * <BR>
     * ���������[�h()���R�[��<BR>
     * <BR>
     * �V�[�P���X�}�u(�ԍό㑍����)do�ԍό㑍�������[�h�v�Q��<BR>
     * <BR>
     * @@roseuid 41C91C7302FD
     */
    public void loadAllAfterRepay()
    {
        final String STR_METHOD_NAME = "loadAllAfterRepay()";
        log.entering(STR_METHOD_NAME);

        this.loadAll();

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (calc�������ԍό��ϑ��v)<BR>
     * <BR>
     * ����ԍϕ��@@�{�@@�����ԍϒ�����
     * <BR>
     * �V�[�P���X�}�u(�ԍό�]�͍X�V)get�������ԍό��ϑ��v�v�Q��<BR>
     * <BR>
     * @@param l_datDate - (�w���)
     * @@return double
     * @@roseuid 41C91ED80167
     */
    public double calcOrderRepaySettleProfitLoss(Date l_datDate)
    {
        final String STR_METHOD_NAME = "calcOrderRepaySettleProfitLoss(Date)";
        log.entering(STR_METHOD_NAME);

        //�ԍό�������I�u�W�F�N�g���擾
        WEB3TPTransactionAmountAfterRepay l_transactionAmountAfterRepay =
            (WEB3TPTransactionAmountAfterRepay)this.getTransactionAmount();

        //�������ԍό��ϑ��v(n)���擾
        double l_dblProfitLoss =
            l_transactionAmountAfterRepay.calcOrderRepaySettleProfitLoss(l_datDate);

        //�������ԍό��ϑ��v(n)��ԋp
        log.exiting(STR_METHOD_NAME);
        return l_dblProfitLoss;
    }

    /**
     * (calc����ԍϕ��ԍό��ϑ��v)<BR>
     * <BR>
     * ����ԍϕ��݂̂̌��ϑ��v<BR>
     * <BR>
     * �V�[�P���X�}�u(�ԍό�]�͍X�V)get����ԍϕ����ϑ��v�v�Q��<BR>
     * <BR>
     * @@param l_datDate - (�w���)
     * @@return Double
     * @@roseuid 41C91F360177
     */
    public double calcCurrOrderRepaySettleProfitLoss(Date l_datDate)
    {
        final String STR_METHOD_NAME = "calcCurrOrderRepaySettleProfitLoss(Date)";
        log.entering(STR_METHOD_NAME);

        //�ԍό�������I�u�W�F�N�g���擾
        WEB3TPTransactionAmountAfterRepay l_transactionAmountAfterRepay =
            (WEB3TPTransactionAmountAfterRepay)this.getTransactionAmount();

        //����ԍϕ��ԍό��ϑ��v(n)���擾
        double l_dblProfitLoss =
            l_transactionAmountAfterRepay.calcCurrOrderRepaySettleProfitLoss(l_datDate);

        //����ԍϕ��ԍό��ϑ��v(n)��ԋp
        log.exiting(STR_METHOD_NAME);
        return l_dblProfitLoss;
    }
}
@
