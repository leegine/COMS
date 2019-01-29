head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.53.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTradingPowerUpdAfterRepay.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ԍό�]�͍X�V(WEB3TPTradingPowerUpdAfterRepay.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/11 nakazato(ACT) �V�K�쐬
*/

package webbroker3.tradingpower;

import java.util.Date;
import java.util.List;

import webbroker3.tradingpower.updtpower.WEB3TPAccountInfo;
import webbroker3.tradingpower.updtpower.WEB3TPNewOrderSpec;
import webbroker3.tradingpower.updtpower.WEB3TPPersistentDataManager;
import webbroker3.tradingpower.updtpower.asset.WEB3TPSecurityValuation;
import webbroker3.tradingpower.updtpower.cash.WEB3TPCashValuationAfterRepay;
import webbroker3.tradingpower.updtpower.contract.WEB3TPContractInfoAfterRepay;
import webbroker3.tradingpower.updtpower.settlement.WEB3TPSettlement;
import webbroker3.util.WEB3LogUtility;

/**
 * (�ԍό�]�͍X�V)
 */
public class WEB3TPTradingPowerUpdAfterRepay extends WEB3TPTradingPowerUpd
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPTradingPowerUpdAfterRepay.class);

    /**
     * @@roseuid 41E3842B00D4
     */
    public WEB3TPTradingPowerUpdAfterRepay()
    {

    }

    /**
     * (�R���X�g���N�^)<BR>
     * <BR>
     * �V�[�P���X�}�u(�ԍό�]�͍X�V)�R���X�g���N�^�v�Q��<BR>
     * <BR>
     * @@param l_lngAccountId - (����ID)
     * @@param l_blnMarginFlag - (�M�p�ڋq�t���O)
     * @@param l_calcCondition - (�]�͌v�Z����)
     * @@param l_newOrderSpecs - (���������e)
     * @@roseuid 41BFE12803B9
     */
    public WEB3TPTradingPowerUpdAfterRepay(
        long l_lngAccountId,
        boolean l_blnMarginFlag,
        WEB3TPCalcCondition l_calcCondition,
        WEB3TPNewOrderSpec[] l_newOrderSpecs)
    {
        final String STR_METHOD_NAME =
            "WEB3TPTradingPowerUpdAfterRepay(long, boolean, WEB3TPCalcCondition, WEB3TPNewOrderSpec[])";
        log.entering(STR_METHOD_NAME);

        /*
         * �ڋq�����I�u�W�F�N�g����       
         */
        WEB3TPAccountInfo l_accountInfo = WEB3TPAccountInfo.create(l_lngAccountId, l_blnMarginFlag);

        this.accountInfo = l_accountInfo;
        this.calcCondition = l_calcCondition;
        this.newOrderSpecs = l_newOrderSpecs;

        List l_todaysEquityOrders = WEB3TPPersistentDataManager.getInstance().getTodaysEquityOrders(
            l_accountInfo, l_calcCondition);
        /*
         * �ԍό㌚�ʏ��I�u�W�F�N�g�쐬
         */
        WEB3TPContractInfoAfterRepay l_contractInfo =
            WEB3TPContractInfoAfterRepay.createWEB3TPContractInfoAfterRepay(
                this.accountInfo,
                this.calcCondition,
                this.newOrderSpecs);
        l_contractInfo.setTodaysEquityOrders(l_todaysEquityOrders);

        //�ԍό㌚�ʏ�񃍁[�h
        l_contractInfo.loadContractInfoAfterRepay();
        this.contractInfo = l_contractInfo;

        /*
         * �،��]���I�u�W�F�N�g�쐬
         */
        WEB3TPSecurityValuation l_securityValuation =
            WEB3TPSecurityValuation.create(
                this.accountInfo,
                this.calcCondition,
                this.newOrderSpecs,
                l_contractInfo);
        l_securityValuation.setTodaysEquityOrders(l_todaysEquityOrders);

        //�������Ə،��]���z���[�h
        l_securityValuation.doSecurityValuationPerProductLoad();
        this.securityValuation = l_securityValuation;

        /*
         * �ԍό㑍�����I�u�W�F�N�g�쐬
         */
        WEB3TPCashValuationAfterRepay l_cashValuation =
            WEB3TPCashValuationAfterRepay.createWEB3TPCashValuationAfterRepay(
                this.accountInfo,
                this.calcCondition,
                this.newOrderSpecs);
        l_cashValuation.setTodaysEquityOrders(l_todaysEquityOrders);

        //�ԍό㑍�������[�h
        l_cashValuation.loadAllAfterRepay();
        this.cashValuation = l_cashValuation;

        /*
         * �������σI�u�W�F�N�g�𐶐�
         */
        this.settlement =
            new WEB3TPSettlement(this.cashValuation, this.securityValuation, this.calcCondition);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get�������ԍό��ϑ��v)<BR>
     * <BR>
     * �V�[�P���X�}�u(�ԍό�]�͍X�V)get�������ԍό��ϑ��v�v�Q��<BR>
     * <BR>
     * @@param l_intSpecifiedPoint - (�w���)
     * @@return double
     * @@roseuid 41C7AB8F02C8
     */
    public double getOrderRepaySettleProfitLoss(int l_intSpecifiedPoint)
    {
        final String STR_METHOD_NAME = "getOrderRepaySettleProfitLoss(int)";
        log.entering(STR_METHOD_NAME);

        //�w����ɑΉ�����c�Ɠ����擾
        Date l_datDate = this.calcCondition.getBizDate(l_intSpecifiedPoint);

        //this.��������ԍό㑍�����ɃL���X�g
        WEB3TPCashValuationAfterRepay l_cashValuationAfterRepay =
            (WEB3TPCashValuationAfterRepay)this.cashValuation;

        //�w����̔������ԍό��ϑ��v��ԋp
        log.exiting(STR_METHOD_NAME);
        return l_cashValuationAfterRepay.calcOrderRepaySettleProfitLoss(l_datDate);
    }

    /**
     * (get����ԍϕ��ԍό��ϑ��v)<BR>
     * <BR>
     * �V�[�P���X�}�u(�ԍό�]�͍X�V)get����ԍϕ����ϑ��v�v�Q��<BR>
     * <BR>
     * @@param l_intSpecifiedPoint - (�w���)
     * @@return double
     * @@roseuid 41C7AB9D00D4
     */
    public double getCurrOrderRepaySettleProfitLoss(int l_intSpecifiedPoint)
    {
        final String STR_METHOD_NAME = "getCurrOrderRepaySettleProfitLoss(int)";
        log.entering(STR_METHOD_NAME);

        //�w����ɑΉ�����c�Ɠ����擾
        Date l_datDate = this.calcCondition.getBizDate(l_intSpecifiedPoint);

        //this.��������ԍό㑍�����ɃL���X�g
        WEB3TPCashValuationAfterRepay l_cashValuationAfterRepay =
            (WEB3TPCashValuationAfterRepay)this.cashValuation;

        //�w����̍��񔭒����ԍό��ϑ��v��ԋp
        log.exiting(STR_METHOD_NAME);
        return l_cashValuationAfterRepay.calcCurrOrderRepaySettleProfitLoss(l_datDate);
    }
}
@
