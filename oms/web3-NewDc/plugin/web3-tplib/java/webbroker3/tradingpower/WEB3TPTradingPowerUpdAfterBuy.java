head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.54.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTradingPowerUpdAfterBuy.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �l�b�g�g���[�h�V�X�e���J����
 File Name        : ���t��]�͍X�V(WEB3TPTradingPowerUpdAfterBuy.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/03/31 nakazato(DIR-ST) �V�K�쐬
 */
package webbroker3.tradingpower;

import java.util.List;

import webbroker3.tradingpower.updtpower.WEB3TPAccountInfo;
import webbroker3.tradingpower.updtpower.WEB3TPNewOrderSpec;
import webbroker3.tradingpower.updtpower.WEB3TPPersistentDataManager;
import webbroker3.tradingpower.updtpower.asset.WEB3TPSecurityValuationAfterBuy;
import webbroker3.tradingpower.updtpower.cash.WEB3TPCashValuation;
import webbroker3.tradingpower.updtpower.contract.WEB3TPContractInfo;
import webbroker3.tradingpower.updtpower.settlement.WEB3TPSettlement;
import webbroker3.util.WEB3LogUtility;

/**
 * (���t��]�͍X�V)
 * ���]�͍X�V�N���X�̊g���N���X
 * ���������������̎���]�̓`�F�b�N���Ɏg�p����B
 */
public class WEB3TPTradingPowerUpdAfterBuy extends WEB3TPTradingPowerUpd
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3TPTradingPowerUpdAfterBuy.class);

    /**
     * (�f�t�H���g�R���X�g���N�^)
     */
    public WEB3TPTradingPowerUpdAfterBuy()
    {
        super();
    }

    /**
     * (�R���X�g���N�^)
     * 
     * ���V�[�P���X�}�u(���t��]�͍X�V)�R���X�g���N�^�v�Q��
     * 
     * @@param l_lngAccountId - (����ID)
     * @@param l_blnMarginFlag - (�M�p�ڋq�t���O)
     * @@param l_calcCondition - (�]�͌v�Z����)
     * @@param l_newOrderSpecs - (���������e)
     */
    public WEB3TPTradingPowerUpdAfterBuy(
            long l_lngAccountId,
            boolean l_blnMarginFlag,
            WEB3TPCalcCondition l_calcCondition,
            WEB3TPNewOrderSpec[] l_newOrderSpecs)
    {
        final String STR_METHOD_NAME = "WEB3TPTradingPowerUpdAfterBuy(long, boolean, WEB3TPCalcCondition, WEB3TPNewOrderSpec[])";
        log.entering(STR_METHOD_NAME);

        /*
         * �ڋq�����I�u�W�F�N�g����       
         */
        WEB3TPAccountInfo l_accountInfo = WEB3TPAccountInfo.create(l_lngAccountId, l_blnMarginFlag);

        this.accountInfo = l_accountInfo;
        this.calcCondition = l_calcCondition;
        this.newOrderSpecs = l_newOrderSpecs;

        /*
         * �������������ꗗ���擾
         */
        List l_todaysEquityOrders = WEB3TPPersistentDataManager.getInstance().getTodaysEquityOrders(
                l_accountInfo,
                l_calcCondition);
        /*
         * ���ʏ��I�u�W�F�N�g�쐬
         */
        WEB3TPContractInfo l_contractInfo = WEB3TPContractInfo.create(
                this.accountInfo,
                this.calcCondition,
                this.newOrderSpecs);
        l_contractInfo.setTodaysEquityOrders(l_todaysEquityOrders);

        //���ʏ�񃍁[�h
        l_contractInfo.loadContractInfo();
        this.contractInfo = l_contractInfo;

        /*
         * ���t��،��]���I�u�W�F�N�g�쐬
         */
        WEB3TPSecurityValuationAfterBuy l_securityValuation = WEB3TPSecurityValuationAfterBuy.createWEB3TPSecurityValuationAfterBuy(
                this.accountInfo,
                this.calcCondition,
                this.newOrderSpecs,
                l_contractInfo);
        l_securityValuation.setTodaysEquityOrders(l_todaysEquityOrders);

        //�������Ə،��]���z���[�h
        l_securityValuation.doSecurityValuationPerProductLoad();
        this.securityValuation = l_securityValuation;

        /*
         * �������I�u�W�F�N�g�쐬
         */
        WEB3TPCashValuation l_cashValuation = WEB3TPCashValuation.create(
                this.accountInfo,
                this.calcCondition,
                this.newOrderSpecs);
        l_cashValuation.setTodaysEquityOrders(l_todaysEquityOrders);

        //���������[�h
        l_cashValuation.loadAll();
        this.cashValuation = l_cashValuation;

        /*
         * �������σI�u�W�F�N�g�𐶐�
         */
        this.settlement = new WEB3TPSettlement(
                this.cashValuation,
                this.securityValuation,
                this.calcCondition);

        log.exiting(STR_METHOD_NAME);
    }
}@
